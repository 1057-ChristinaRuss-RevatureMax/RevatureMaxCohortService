package services;

import dao.apiDAO;
import dao.apiDAOimpl;
import models.Associate;

public class userServiceImpl implements userService {

    apiDAO apidao;

    public userServiceImpl() {
        this.apidao = new apiDAOimpl();
    }

    @Override
    public boolean loginUser(String username, String password) {

        return this.apidao.loginUser(username, password);
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
