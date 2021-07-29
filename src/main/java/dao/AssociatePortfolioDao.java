package dao;
import models.Associate;

public interface AssociatePortfolioDao{

    void updateBio(String salesforceId, String bio);

    void updatePreference(String salesforceId, String preference);

    void updateFavoriteTechnologies(String salesforceId, String technologies);

}