package models;

public class Associate {
    private String firstname;
    private String lastname;
    private String email;
    private String salesforceId;
    private String password;
//    private String flag;

    public Associate(String firstname, String lastname, String email, String salesforceId) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.email = email;
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

    public String getSalesforceId() {
        return salesforceId;
    }

    public void setSalesforceId(String salesforceId) {
        this.salesforceId = salesforceId;
    }

    public String getPassword(){
        return password;
    }
    
    public void setPassword(String password){
        this.password = password;
    }

    @Override
    public String toString() {
        return "{" +
                "'firstname':'" + firstname + '\'' +
                ", 'lastname':'" + lastname + '\'' +
                ", 'email':'" + email + '\'' +
                ", 'salesforceId':'" + salesforceId + '\'' +
                '}';
    }
}