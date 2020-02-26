package application.requestAPI.requestAPI;

import application.requestAPI.baseRequestApi.BaseRequestApi;
import application.requestAPI.baseRequestApi.MethodsApi;
import application.requestAPI.baseRequestApi.ParamsApi;
import framework.api.HttpConnection;
import framework.utils.PropertiesManager;


public class ListTestes {

    public static String getListTests () {
        ParamsApi paramsApi = ParamsApi.create().add("projectId", PropertiesManager.getProperties("projectId"));
        String postToken = BaseRequestApi.getRequest(MethodsApi.TEST_GET_JSON, paramsApi);
        String listTests = HttpConnection.getStringResponse(postToken);
        return listTests;
    }
}
