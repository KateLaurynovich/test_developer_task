package application.requestTestRail.run;


import application.requestTestRail.baseRequest.BaseRequest;
import application.requestTestRail.baseRequest.Params;
import application.serializableObject.CaseResult;
import application.utils.JsonGenerate;
import com.fasterxml.jackson.databind.ObjectMapper;
import framework.api.HttpConnection;
import framework.cloudinary.CloudPhoto;
import framework.logger.MyLogger;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.io.IOException;
import java.io.StringReader;

public class CaseResultRequest {

    private static final MyLogger LOGGER = new MyLogger();

    public static CaseResult postCaseResult(RunModel runModel, int statusId, CloudPhoto cloudPhoto) {
        String requestBody = JsonGenerate.create()
                .add("status_id", statusId)
                .add("comment", cloudPhoto.getUrl())
                .build();
        JSONObject responseJson = HttpConnection.getJSONObject(HttpConnection.postResponseAuth(BaseRequest.getRequest(
                Params.create().add(RunMethods.ADD_RESULT_FOR_CASE.getTitle())
                        .add(runModel.getRunId())
                        .add(runModel.getCaseId())), runModel.getLogin(), runModel.getPassword(), requestBody));
        return getCaseResult(responseJson);
    }

    private static CaseResult getCaseResult(JSONObject jsonObject) {
        StringReader reader = new StringReader(jsonObject.toString());
        CaseResult caseResult = null;
        ObjectMapper mapper = new ObjectMapper();
        try {
            caseResult = mapper.readValue(reader, CaseResult.class);
        } catch (IOException e) {
            LOGGER.error("IOException", e);
        }
        LOGGER.info("caseResult  = ", caseResult);
        return caseResult;
    }

    private static CaseResult getCaseResultGet(JSONArray jsonArray) {
        CaseResult caseResult = null;
        for (Object js : jsonArray) {
            StringReader reader = new StringReader(js.toString());
            ObjectMapper mapper = new ObjectMapper();
            try {
                caseResult = mapper.readValue(reader, CaseResult.class);
            } catch (IOException e) {
                LOGGER.error("IOException", e);
            }
            LOGGER.info("caseResult  = ", caseResult);
        }
        return caseResult;
    }
}
