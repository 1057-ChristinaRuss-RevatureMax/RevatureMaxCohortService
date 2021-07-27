package user;

public class Associate {
    private String firstname;
    private String lastname;
    private String email;
    private int salesforceId;
    private boolean flag;

    public Associate(String firstname, String lastname, String email, int salesforceId, boolean flag){
        this.firstname = firstname;
        this.lastname = lastname;
        this.email = email;
        this.salesforceId = salesforceId;
        this.flag = flag;
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

    public int getSalesforceId() {
        return salesforceId;
    }

    public void setSalesforceId(int salesforceId) {
        this.salesforceId = salesforceId;
    }

    public boolean isFlag() {
        return flag;
    }

    public void setFlag(boolean flag) {
        this.flag = flag;
    }
}