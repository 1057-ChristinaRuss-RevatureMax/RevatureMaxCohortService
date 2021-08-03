package services;

import models.Associate;
import java.util.ArrayList;
import java.util.Map;


public interface userService {


    boolean loginUser(String username, String password);
    void editUser(String salesforceId, String firstname, String lastname, String email, String bio, String favoriteTech, String preference);
    String getSalesForceId(String email);

//    void signUpUser(String username, String password, String email);

}