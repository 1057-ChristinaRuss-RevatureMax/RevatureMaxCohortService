public class EmployeePortfolio {
    private String salesforceId;
    private String bio;
    private String location;
    private String technology;
    private String password;

    public EmployeePortfolio(String salesforceId, String bio, String location, String technology){
        this.salesforceId = salesforceId;
        this.bio = bio;
        this.location = location;
        this.technology = technology;
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

    public String getSalesForceId() {
        return salesforceId;
    }

    public String getPassword(){
        return password;
    }
    
    public void setPassword(String password){
        this.password = password;
    }

    public String setSalesForceId(String salesforceId) {
        if(salesforceId.length() > 10 || salesforceId.isEmpty()) {
            return "Invalid ID";
        } else {
            this.salesforceId = salesforceId;
            return "Successfully set Salesforce ID";
        }
    }
    


}
