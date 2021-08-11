package models;

public class AssociateAssignment {
    private boolean active;
    private Associate associate;
    private Batch batch;
    private String endDate;
    private String startDate;
    private String trainingStatus;

    public AssociateAssignment(boolean active, Associate associate, Batch batch, String endDate, String startDate, String trainingStatus) {
        this.active = active;
        this.associate = associate;
        this.batch = batch;
        this.endDate = endDate;
        this.startDate = startDate;
        this.trainingStatus = trainingStatus;
    }

    public AssociateAssignment() {
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public Associate getAssociate() {
        return associate;
    }

    public void setAssociate(Associate associate) {
        this.associate = associate;
    }

    public Batch getBatch() {
        return batch;
    }

    public void setBatch(Batch batch) {
        this.batch = batch;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getTrainingStatus() {
        return trainingStatus;
    }

    public void setTrainingStatus(String trainingStatus) {
        this.trainingStatus = trainingStatus;
    }

    @Override
    public String toString() {
        return "AssociateAssignment{" +
                "active=" + active +
                ", associate=" + associate +
                ", batch=" + batch +
                ", endDate='" + endDate + '\'' +
                ", startDate='" + startDate + '\'' +
                ", trainingStatus='" + trainingStatus + '\'' +
                '}';
    }
}