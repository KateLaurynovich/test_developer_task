package application.requestAPI.requestAPI;

import application.requestAPI.baseRequestApi.BaseRequestApi;
import application.requestAPI.baseRequestApi.MethodsApi;
import application.requestAPI.baseRequestApi.ParamsApi;
import framework.api.HttpConnection;
import framework.logger.MyLogger;
import framework.utils.PropertiesManager;


public class Token {
    private static final MyLogger LOGGER = new MyLogger();

    public static String getToken (MethodsApi method) {
        ParamsApi paramsApi = ParamsApi.create().add("variant", PropertiesManager.getProperties("projectId"));
        String postToken = BaseRequestApi.getRequest(method, paramsApi);
        String  token = HttpConnection.getStringResponse(postToken);
        LOGGER.info("token = " + token);
        return token;
    }
}
