package services;

import dao.AssociateDaoImpl;
import dao.apiDAO;
import dao.apiDAOimpl;
import models.Associate;

public class userServiceImpl implements userService {

    apiDAO apidao;
    AssociateDaoImpl associatedao;

    public userServiceImpl() {
        this.apidao = new apiDAOimpl();
        this.associatedao = new AssociateDaoImpl();
    }
    @Override
    public String getSalesForceId(String email){
        return("test");
    }

    @Override
    public boolean loginUser(String username, String password) {

        return this.apidao.loginUser(username, password);
    }
    @Override
    public void editUser(String salesforceId, String firstname, String lastname, String email, String bio, String favoriteTech, String preference){


    }

}

//    @Override
//    public void signUpUser(String username, String password, String email) {
//
//    }
//
//    @Override
//    public boolean encode(String filepath, String normal_img, String ext, String encoded_img, String message, int user_id) {
//        return false;
//    }
//
//    @Override
//    public String decode(String filepath, String filename) {
//        return null;
//    }
//
//    @Override
//    public int getUserId(String username) {
//        return 0;
//    }
//
//    @Override
//    public int getNextImgId() {
//        return 0;
//    }
//
//    @Override
//    public void changepw(int user_id, String password) {
//
//    }