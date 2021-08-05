package services;

import dao.AssociateDaoImpl;
import dao.AssociatePortfolioDaoImpl;
import dao.apiDAO;
import dao.apiDAOimpl;
import models.Associate;

public class userServiceImpl implements userService {

    apiDAO apidao;
    AssociateDaoImpl associatedao;
    AssociatePortfolioDaoImpl portfoliodao;

    public userServiceImpl() {
        this.apidao = new apiDAOimpl();
        this.associatedao = new AssociateDaoImpl();
        this.portfoliodao = new AssociatePortfolioDaoImpl();
    }
    @Override
    public String getSalesForceId(String email){
        Associate associate = this.associatedao.getAssociateByEmail(email);
        return associate.getSalesforceId();

    }

    @Override
    public boolean loginUser(String username, String password) {

        return this.apidao.loginUser(username, password);
    }
    @Override
    public void editUser(String salesforceId, String firstname, String lastname, String email, String bio, String favoriteTech, String preference){
        this.associatedao.updateAssociateFirstname(salesforceId, firstname);
        this.associatedao.updateAssociateLastname(salesforceId, lastname);
        this.associatedao.updateAssociateEmail(salesforceId, email);
        this.portfoliodao.updateBio(salesforceId, bio);
        this.portfoliodao.updatePreference(salesforceId, preference);
        this.portfoliodao.updateFavoriteTechnologies(salesforceId, favoriteTech);
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