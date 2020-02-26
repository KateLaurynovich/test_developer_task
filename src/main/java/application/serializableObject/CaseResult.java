package application.serializableObject;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
import java.util.Objects;

public class CaseResult implements Serializable {
    private Integer id;
    @JsonProperty ("test_id")
    private Integer testId;
    @JsonProperty ("status_id")
    private Integer statusId;
    @JsonProperty ("created_by")
    private Integer createdBy;
    @JsonProperty ("created_on")
    private Integer createdOn;
    @JsonProperty ("assignedto_id")
    private Integer assignedtoId;
    private String comment;
    private String version;
    private String elapsed;
    private String defects;
    @JsonProperty ("custom_step_results")
    private String customStepResults;
    @JsonProperty ("attachment_ids")
    private Integer[] attachmentIds;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getTestId() {
        return testId;
    }

    public void setTestId(Integer testId) {
        this.testId = testId;
    }

    public Integer getStatusId() {
        return statusId;
    }

    public void setStatusId(Integer statusId) {
        this.statusId = statusId;
    }

    public Integer getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(Integer createdBy) {
        this.createdBy = createdBy;
    }

    public Integer getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(Integer createdOn) {
        this.createdOn = createdOn;
    }

    public Integer getAssignedtoId() {
        return assignedtoId;
    }

    public void setAssignedtoId(Integer assignedtoId) {
        this.assignedtoId = assignedtoId;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getElapsed() {
        return elapsed;
    }

    public void setElapsed(String elapsed) {
        this.elapsed = elapsed;
    }

    public String getDefects() {
        return defects;
    }

    public void setDefects(String defects) {
        this.defects = defects;
    }

    public String getCustomStepResults() {
        return customStepResults;
    }

    public void setCustomStepResults(String customStepResults) {
        this.customStepResults = customStepResults;
    }

    public Integer[] getAttachmentIds() {
        return attachmentIds;
    }

    public void setAttachmentIds(Integer[] attachmentIds) {
        this.attachmentIds = attachmentIds;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CaseResult that = (CaseResult) o;
        return id.equals(that.id) &&
                testId.equals(that.testId) &&
                statusId.equals(that.statusId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, testId, statusId);
    }

    @Override
    public String toString() {
        return "CaseResult{" +
                "id=" + id +
                ", testId=" + testId +
                ", statusId=" + statusId +
                '}';
    }
}
