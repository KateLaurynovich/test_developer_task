import application.models.*;
import application.dataBase.DataBaseResult;
import application.dataBase.TypeAttachment;
import application.pageObject.InfoCategory;
import application.pageObject.MainPage;
import application.pageObject.ProjectPage;
import application.pageObject.TestInfoPage;
import application.pageObject.listGroup.Category;
import application.requestAPI.baseRequestApi.MethodsApi;
import application.requestAPI.requestAPI.ListTestes;
import application.requestAPI.requestAPI.Token;
import application.requestTestRail.run.CaseResultRequest;
import application.requestTestRail.run.RunModel;
import application.requestTestRail.run.StatusEnum;
import application.serializableObject.TestSerializable;
import application.utils.SessionCreator;
import application.utils.HostNameIP;
import application.utils.ListTestsUI;
import application.utils.ParserJSON;
import framework.browsers.BrowserActions;
import framework.cloudinary.CloudPhoto;
import framework.cloudinary.CloudinaryRes;
import framework.cookies.CookieManager;
import framework.utils.files.CompareFile;
import framework.utils.files.EqualsPicture;
import framework.utils.files.FileManager;
import framework.utils.files.FileToBytes;
import framework.logger.MyLogger;
import framework.utils.PropertiesManager;
import framework.utils.RandomGeneration;
import framework.utils.RegEx;
import org.openqa.selenium.Cookie;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.util.List;

import static org.testng.Assert.*;

public class TestTD {
    private static MyLogger LOGGER = new MyLogger();

    @BeforeTest
    private void setUp() {
        PropertiesManager.loadProperties("config");
        PropertiesManager.loadProperties("test");
        BrowserActions.getImplicitlyWait();
        BrowserActions.getPageLoadTimeout();
        BrowserActions.getSetScriptTimeout();
    }

    @AfterTest
    private void afterTest() {
        BrowserActions.quitBrowser();
        FileManager.deleteDownloadFile(PropertiesManager.getProperties("pathToScreen"));
        FileManager.deleteDownloadFile(PropertiesManager.getProperties("pathToFinalScreen"));
        FileManager.deleteDownloadFile(PropertiesManager.getProperties("pathSave"));
    }

    @Test
    public void testTD() {

        LOGGER.step(1, "By request to API, get a token according to the option number");
        TokenModel tokenModel = new TokenModel(Token.getToken(MethodsApi.GET_TOKEN));
        assertNotNull(tokenModel.getToken(), "token is null");

        LOGGER.step(2, "Go to the site. Pass the necessary authorization. " +
                "Use a cookie to pass the token generated in step 1 (token parameter).");
        BrowserActions.goToPage(PropertiesManager.getProperties("URL"));
        Cookie cookie = new Cookie("token", tokenModel.getToken());
        CookieManager.addCookie(cookie);
        BrowserActions.refresh();
        MainPage mainPage = new MainPage();
        assertEquals(mainPage.getFooter().getVersion(), (PropertiesManager.getProperties("version")),
                "Invalid variant number in footer");

        LOGGER.step(3, "Go to the Nexage project page. A request to api to get a list of tests in JSON format.");
        mainPage.getListGroup().clickCategory(Category.NEXAGE);
        List<TestSerializable> testSerializable = ParserJSON.parseJSONToListObject(ListTestes.getListTests(), TestSerializable.class);
        ListTestsUI.sortCollection(testSerializable);
        ProjectPage projectPage = new ProjectPage();
        List<TestSerializable> tests = ListTestsUI.getListTestUI(projectPage.getTestField().getTests());
            assertEquals(tests.toString(), ListTestsUI.sortCollection(tests).toString(), "collection is not sorted");
            assertTrue(ListTestsUI.contains(tests, testSerializable),
                    "tests do not match those tests who returned API: ");

        LOGGER.step(4, "Return to the previous page in the browser (project page). Click on + Add." );
        BrowserActions.backBrowser();
        ProjectModel projectModel = new ProjectModel("newProject_" + RandomGeneration.randomString(3));
        mainPage.clickAddBtn();
        LOGGER.step(4, "Enter the name of the project, and save. To close the add project window, call the closePopUp() js method. Refresh the page");
        mainPage.switchToIFrame();
        mainPage.getFrame().submitProjectInput(projectModel.getName());
        mainPage.getFrame().clickSaveBtn();
            assertTrue(mainPage.getFrame().isSuccessMsg(projectModel.getName()), "message is not successful");
        BrowserActions.switchToDefaultContent();
        mainPage.closePopUp();
            assertTrue(mainPage.isModalContent(), "the project add window hasn't closed");
        BrowserActions.refresh();
            assertTrue(mainPage.getListGroup().isCategory(projectModel.getName()), "project " + projectModel.getName() + "is not exist");

        LOGGER.step(5, "Go to the page of the created project. Add a test through the database (along with the log and screenshot of the current page).");
        projectModel.setId(mainPage.getListGroup().getCategoryId(projectModel.getName()));
        BrowserActions.takeScreenshot(PropertiesManager.getProperties("pathToScreen"));
        SessionModel sessionModel = SessionCreator.createSession();
        TestModel testModel = new TestModel(
                PropertiesManager.getProperties("testName"),
                this.getClass().getMethods().toString(),
                projectModel.getId(),
                sessionModel.getId(),
                HostNameIP.getHostName());
        testModel.setSessionId(sessionModel.getId());
        testModel.setId(DataBaseResult.getTestID(testModel));
        DataBaseResult.addAttachment(testModel, PropertiesManager.getProperties("pathToLog"), TypeAttachment.TEXT);
        DataBaseResult.addAttachment(testModel, PropertiesManager.getProperties("pathToScreen"), TypeAttachment.IMAGE);

        LOGGER.step(6, "Go to the page of the created test. Check the information is correct." +
                " All fields have the correct value. Screenshot corresponds to the sent");
        mainPage.getListGroup().clickCategory(projectModel.getName());
        projectPage.clickTest(testModel);
        TestInfoPage testInfoPage = new TestInfoPage();
        TestModel testModelWeb = new TestModel(
                testInfoPage.getInfo(InfoCategory.TEST_NAME),
                testInfoPage.getInfo(InfoCategory.TEST_METHOD),
                RegEx.findMatcher("\\d{1,6}", testInfoPage.getProjectId(projectModel)),
                RegEx.findMatcher("\\d{1,6}", testInfoPage.getSessionId(sessionModel)),
                testInfoPage.getInfo(InfoCategory.ENV));
            assertEquals(testModel, testModelWeb, "fields have incorrect values.");
        FileToBytes.convertBase64StringToPng(testInfoPage.getHrefImg(), PropertiesManager.getProperties("pathSave"));
            assertEquals(EqualsPicture.compareImages(PropertiesManager.getProperties("pathSave"), PropertiesManager.getProperties("pathToScreen")), CompareFile.FULL.getTitle(),
                "images are not equals");
    }

    @AfterMethod
    public void afterMethod(ITestResult result) {
        RunModel runModel = new RunModel();
        runModel.setLogin(PropertiesManager.getProperties("userRail"));
        runModel.setPassword(PropertiesManager.getProperties("passwordRail"));
        runModel.setCaseId(PropertiesManager.getProperties("caseId"));
        runModel.setRunId(PropertiesManager.getProperties("runId"));
        BrowserActions.takeScreenshot(PropertiesManager.getProperties("pathToFinalScreen"));
        if (result.getStatus() == ITestResult.SUCCESS) {
            CloudPhoto cloudPhoto = CloudinaryRes.getResultUploadFile(PropertiesManager.getProperties("pathToFinalScreen"), PropertiesManager.getProperties("CloudName"),
                    PropertiesManager.getProperties("APIKey"), PropertiesManager.getProperties("APISecret"));
            CaseResultRequest.postCaseResult(runModel, StatusEnum.PASSED.getTitle(), cloudPhoto);

        } else if (result.getStatus() == ITestResult.FAILURE) {
            CloudPhoto cloudPhoto = CloudinaryRes.getResultUploadFile(PropertiesManager.getProperties("pathToFinalScreen"), PropertiesManager.getProperties("CloudName"),
                    PropertiesManager.getProperties("APIKey"), PropertiesManager.getProperties("APISecret"));
            CaseResultRequest.postCaseResult(runModel, StatusEnum.FAILED.getTitle(), cloudPhoto);
        }
    }
}


