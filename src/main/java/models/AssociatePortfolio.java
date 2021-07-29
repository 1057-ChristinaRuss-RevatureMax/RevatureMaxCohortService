package models;

public class AssociatePortfolio {
    private String bio;
    private String favoriteTechnology;
    private String preference;
    private String salesForceId;

    public AssociatePortfolio(String bio, String favoriteTechnology, String preference, String salesForceId) {
        this.bio = bio;
        this.favoriteTechnology = favoriteTechnology;
        this.preference = preference;
        this.salesForceId = salesForceId;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public String getFavoriteTechnology() {
        return favoriteTechnology;
    }

    public void setFavoriteTechnology(String favoriteTechnology) {
        this.favoriteTechnology = favoriteTechnology;
    }

    public String getPreference() {
        return preference;
    }

    public String setPreference(String preference) {
        if(preference.length() > 15) {
            return "Characters too long";
        } else {
            this.preference = preference;
            return "Successfully set preference";
        }
    }

    public String getSalesForceId() {
        return salesForceId;
    }

    public String setSalesForceId(String salesForceId) {
        if(salesForceId.length() > 10 || salesForceId.isEmpty()) {
            return "Invalid ID";
        } else {
            this.salesForceId = salesForceId;
            return "Successfully set Salesforce ID";
        }
    }
}