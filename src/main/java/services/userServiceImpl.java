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


    @Override
    public boolean checkSession(String username){
        return this.apidao.checkSession(username);
    }

    @Override
    public boolean passwordChange(String username, String newPassword) {

        return this.apidao.passwordChange(username, newPassword);
    }

}
