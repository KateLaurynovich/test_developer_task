package application.serializableObject;

import java.io.Serializable;
import java.util.Comparator;
import java.util.Objects;

public class TestSerializable implements Serializable {
    private String duration;
    private String method;
    private String name;
    private String startTime;
    private String endTime;
    private String status;

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public TestSerializable(String duration, String method, String name, String startTime, String endTime, String status) {
        this.duration = duration;
        this.method = method;
        this.name = name;
        this.startTime = startTime;
        this.endTime = endTime;
        this.status = status;
    }

    public TestSerializable() {
    }

    @Override
    public String toString() {
        return "TestSerializable{" +
                "duration='" + duration + '\'' +
                ", method='" + method + '\'' +
                ", name='" + name + '\'' +
                ", startTime='" + startTime + '\'' +
                ", endTime='" + endTime + '\'' +
                ", status='" + status + '\'' +
                '}';
    }

    public static Comparator<TestSerializable> COMPARE_BY_START_TIME = new Comparator<TestSerializable>() {
        public int compare(TestSerializable one, TestSerializable other) {
            return one.startTime.compareTo(other.startTime);
        }
    };

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TestSerializable that = (TestSerializable) o;
        return duration.equals(that.duration) &&
                method.equals(that.method) &&
                name.equals(that.name) &&
                startTime.equals(that.startTime) &&
                endTime.equals(that.endTime) &&
                status.toUpperCase().equals(that.status.toUpperCase());
    }

    @Override
    public int hashCode() {
        return Objects.hash(duration, method, name, startTime, endTime, status);
    }
}
