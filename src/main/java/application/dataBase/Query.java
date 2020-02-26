package application.dataBase;

import application.models.SessionModel;
import application.models.TestModel;

public class Query {

    public static String addTest(TestModel testModel) {
        return "INSERT INTO test(test.name, test.project_id, test.method_name, test.session_id, test.env )" +
                String.format("VALUES('%s', %s, '%s', %s, '%s');",
                        testModel.getTestName(), testModel.getProjectId(), testModel.getMethodName(), testModel.getSessionId(), testModel.getEnv());
    }

    public static String addSession(SessionModel session) {
        return "INSERT INTO session(session.session_key, session.build_number)" +
                String.format("VALUES('%s', 1);", session.getSessionKey());
    }

    public static String getSessionId(SessionModel session) {
        return String.format("SELECT id FROM session WHERE session.session_key = '%s';", session.getSessionKey());
    }

    public static String getSessionTime(SessionModel session) {
        return String.format("SELECT session.created_time FROM session WHERE session.session_key = '%s';", session.getSessionKey());
    }

    public static String getTestId(TestModel testModel) {
        return String.format("SELECT id FROM test WHERE test.name = '%s' AND test.project_id = %s", testModel.getTestName(), testModel.getProjectId());
    }

    public static String addAttachment(TestModel testModel, String type) {
        return String.format("INSERT INTO attachment(attachment.content, attachment.content_type, attachment.test_id) VALUES( ?, '%s', %s);", type, testModel.getId());
    }
}
