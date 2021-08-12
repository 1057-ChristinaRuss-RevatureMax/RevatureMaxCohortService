package models;

public class EmployeePortfolio {
    private int salesforceId;
    private String bio;
    private String location;
    private String technology;

    public EmployeePortfolio(int salesforceId, String bio, String location, String technology){
        this.salesforceId = salesforceId;
        this.bio = bio;
        this.location = location;
        this.technology = technology;
    }

    public EmployeePortfolio() {

    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public String getTechnology() {
        return technology;
    }

    public void setTechnology(String technology) {
        this.technology = technology;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public int getSalesForceId() {
        return salesforceId;
    }


    public String setSalesForceId(String salesforceId) {
        if(String.valueOf(salesforceId).length() > 10 || salesforceId.isEmpty()) {
            return "Invalid ID";
        } else {
            this.salesforceId = Integer.parseInt(salesforceId);
            return "Successfully set Salesforce ID";
        }
    }



}