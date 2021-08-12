package models;

public class EmployeeDB {
    private int salesforceId;
    private String firstName;
    private String lastName;
    private String email;
    private String password;

    public EmployeeDB(int salesforceId, String firstName, String lastName, String email, String password) {
        this.salesforceId = salesforceId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
    }

    public EmployeeDB() {

    }



    public int getSalesforceId() {
        return salesforceId;
    }

    public void setSalesforceId(int salesforceId) {
        this.salesforceId = salesforceId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "employeeDB{" +
                "salesforceId=" + salesforceId +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}