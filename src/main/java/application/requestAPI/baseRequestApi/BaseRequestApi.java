package application.requestAPI.baseRequestApi;

import framework.logger.MyLogger;
import framework.utils.PropertiesManager;


public class BaseRequestApi {
    private static final MyLogger LOGGER = new MyLogger();

    public static String getRequest(MethodsApi method, ParamsApi paramsApi) {
        String link = PropertiesManager.getProperties("API") + method.getTitle() + paramsApi.build();
        LOGGER.info(link);
        return link;
    }

    public static String getRequest(ParamsApi paramsApi) {
        String link = PropertiesManager.getProperties("URL") + paramsApi.build();
        LOGGER.info(link);
        return link;
    }
}
