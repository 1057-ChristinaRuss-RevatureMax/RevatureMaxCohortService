package services;

import models.Associate;
import java.util.ArrayList;
import java.util.Map;


public interface userService {


    boolean loginUser(String username, String password);
    boolean checkSession(String username);

//    void signUpUser(String username, String password, String email);

}