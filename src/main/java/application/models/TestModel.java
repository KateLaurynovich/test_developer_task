package application.models;

import java.util.Objects;

public class TestModel {
    private String id;
    private String projectId;
    private String testName;
    private String methodName;
    private String env;
    private String startTime;
    private String sessionId;

    public TestModel(String testName, String methodName, String projectId, String sessionId, String env) {
        this.projectId = projectId;
        this.testName = testName;
        this.methodName = methodName;
        this.sessionId = sessionId;
        this.env = env;
    }

    public String getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id.toString();
    }

    public String getProjectId() {
        return projectId;
    }

    public void setProjectId(String projectId) {
        this.projectId = projectId;
    }

    public String getTestName() {
        return testName;
    }

    public void setTestName(String testName) {
        this.testName = testName;
    }

    public String getMethodName() {
        return methodName;
    }

    public void setMethodName(String methodName) {
        this.methodName = methodName;
    }

    public String getEnv() {
        return env;
    }

    public void setEnv(String env) {
        this.env = env;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }

    @Override
    public String toString() {
        return "TestModel{" +
                "id='" + id + '\'' +
                ", projectId='" + projectId + '\'' +
                ", testName='" + testName + '\'' +
                ", methodName='" + methodName + '\'' +
                ", env='" + env + '\'' +
                ", session=" + sessionId +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TestModel testModel = (TestModel) o;
        return Objects.equals(projectId, testModel.projectId) &&
                Objects.equals(testName, testModel.testName) &&
                Objects.equals(methodName, testModel.methodName) &&
                Objects.equals(env, testModel.env) &&
                Objects.equals(sessionId, testModel.sessionId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(projectId, testName, methodName, env, sessionId);
    }
}
