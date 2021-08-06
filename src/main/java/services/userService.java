package services;

import models.Associate;
import models.AssociatePortfolio;
import java.util.ArrayList;
import java.util.Map;

import com.google.gson.JsonArray;


public interface userService {


    boolean loginUser(String username, String password);
    boolean checkSession(String username);
    boolean checkSessionTrainer(String username);
    boolean passwordChange(String username, String newPassword);
    void editUser(String salesforceId, String firstname, String lastname, String email, String bio, JsonArray favoriteTech, String preference);
    String getSalesForceId(String email);
    Associate getUserBySalesForceId(String salesforceId);
    AssociatePortfolio getPortfolioBySalesForceId(String salesforceId);
    String getBatchID(String username);

//    void signUpUser(String username, String password, String email);

}