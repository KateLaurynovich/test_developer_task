package application.models;

import java.util.Objects;

public class SessionModel {
    private String id;
    private String sessionKey;
    private String createdTime;

    public SessionModel(String sessionKey) {
        this.sessionKey = sessionKey;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSessionKey() {
        return sessionKey;
    }

    public void setSessionKey(String sessionKey) {
        this.sessionKey = sessionKey;
    }

    public String getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(String createdTime) {
        this.createdTime = createdTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SessionModel that = (SessionModel) o;
        return id.equals(that.id) &&
                sessionKey.equals(that.sessionKey) &&
                createdTime.equals(that.createdTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, sessionKey, createdTime);
    }

    @Override
    public String toString() {
        return "SessionModel{" +
                "id='" + id + '\'' +
                ", sessionKey='" + sessionKey + '\'' +
                ", createdTime='" + createdTime + '\'' +
                '}';
    }
}
