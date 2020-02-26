package application.requestTestRail.run;

import framework.utils.PropertiesManager;

public class RunModel {
    private String login;
    private String password;
    private String caseId;
    private String runId;

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getCaseId() {
        return caseId;
    }

    public void setCaseId(String caseId) {
        this.caseId = caseId;
    }

    public String getRunId() {
        return runId;
    }

    public void setRunId(String runId) {
        this.runId = runId;
    }

    public RunModel(String login, String password, String caseId, String runId) {
        this.login = login;
        this.password = password;
        this.caseId = caseId;
        this.runId = runId;
    }

    public RunModel() {
    }
}
