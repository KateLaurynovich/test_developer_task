package application.requestTestRail.baseRequest;


import framework.logger.MyLogger;
import framework.utils.PropertiesManager;

public class BaseRequest {
    private static final MyLogger LOGGER = new MyLogger();

    public static String getRequest(Params params) {
        String link = PropertiesManager.getProperties("testRailURL") + params.build();
        LOGGER.info(link);
        return link;
    }
}
