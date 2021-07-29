package user;

import models.AssociateAssignment;

public class Associate {
    private String email;
    private String firstname;
    private boolean flag;
    private String lastname;
    private int salesforceId;
    private AssociateAssignment trainingAssignments;

    public Associate(String email, String firstname, boolean flag, String lastname, int salesforceId, AssociateAssignment trainingAssignments) {
        this.email = email;
        this.firstname = firstname;
        this.flag = flag;
        this.lastname = lastname;
        this.salesforceId = salesforceId;
        this.trainingAssignments = trainingAssignments;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public boolean isFlag() {
        return flag;
    }

    public void setFlag(boolean flag) {
        this.flag = flag;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public int getSalesforceId() {
        return salesforceId;
    }

    public void setSalesforceId(int salesforceId) {
        this.salesforceId = salesforceId;
    }

    public AssociateAssignment getTrainingAssignments() {
        return trainingAssignments;
    }

    public void setTrainingAssignments(AssociateAssignment trainingAssignments) {
        this.trainingAssignments = trainingAssignments;
    }

    @Override
    public String toString() {
        return "Associate{" +
                "email='" + email + '\'' +
                ", firstname='" + firstname + '\'' +
                ", flag=" + flag +
                ", lastname='" + lastname + '\'' +
                ", salesforceId=" + salesforceId +
                ", trainingAssignments=" + trainingAssignments +
                '}';
    }
}