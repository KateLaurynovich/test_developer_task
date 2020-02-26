package application.dataBase;

import application.models.SessionModel;
import application.models.TestModel;
import framework.dataBase.DataBase;
import framework.utils.files.FileToBytes;
import framework.utils.RegEx;

import java.io.File;

public class DataBaseResult {

    public static String getSessionID(SessionModel session) {
        DataBase.sendQuery(Query.addSession(session));
        return RegEx.findMatcher("\\d{1,6}", DataBase.sendQuerySelect(Query.getSessionId(session)).toString());
    }

    public static String getSessionTime(SessionModel session) {
        return RegEx.findMatcher("[\\d-\\d\\s:.]{21}", DataBase.sendQuerySelect(Query.getSessionTime(session)).toString());
    }

    public static String getTestID(TestModel testModel) {
        DataBase.sendQuery(Query.addTest(testModel));
        return RegEx.findMatcher("\\d{1,6}", DataBase.sendQuerySelect(Query.getTestId(testModel)).toString());
    }

    public static void addAttachment(TestModel testModel, String path, String type) {
        DataBase.sendQueryAtt(Query.addAttachment(testModel, type), FileToBytes.convertFileContentToBlob(new File(path)));
    }
}
