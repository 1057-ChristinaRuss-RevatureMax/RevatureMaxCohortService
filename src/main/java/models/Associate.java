package models;

import java.util.Arrays;

public class Associate {
    private String email;
    private String firstname;
    private Flag flag;
    private String lastname;
    private String salesforceId;
    private AssociateAssignment[] associateAssignments;
    private String password;

    public Associate(String email, String firstname, Flag flag, String lastname, String salesforceId, AssociateAssignment[] associateAssignments) {
        this.email = email;
        this.firstname = firstname;
        this.flag = flag;
        this.lastname = lastname;
        this.salesforceId = salesforceId;
        this.associateAssignments = associateAssignments;
    }

    public Associate() {

    }

    public Associate(String email, String firstname, Flag flag, String lastname, String salesforceId) {
        this.email = email;
        this.firstname = firstname;
        this.flag = flag;
        this.lastname = lastname;
        this.salesforceId = salesforceId;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword(){
        return password;
    }

    public void setPassword(String password){
        this.password = password;
    }

    public String getSalesforceId() {
        return salesforceId;
    }

    public void setSalesforceId(String salesforceId) {
        this.salesforceId = salesforceId;
    }

    public AssociateAssignment[] getAssociateAssignments() {
        return associateAssignments;
    }

    public void setAssociateAssignments(AssociateAssignment[] associateAssignments) {
        this.associateAssignments = associateAssignments;
    }

    public Flag getFlag() {return flag;}

    public void setFlag(Flag flag) {this.flag = flag;}

    @Override
    public String toString() {
        return "Associate{" +
                "email='" + email + '\'' +
                ", firstname='" + firstname + '\'' +
                ", flag=" + flag +
                ", lastname='" + lastname + '\'' +
                ", salesforceId='" + salesforceId + '\'' +
                ", associateAssignments=" + Arrays.toString(associateAssignments) +
                '}';
    }
}