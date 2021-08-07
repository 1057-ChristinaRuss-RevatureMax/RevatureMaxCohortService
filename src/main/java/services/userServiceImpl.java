package services;

import com.google.gson.JsonArray;

import dao.AssociateDaoImpl;
import dao.AssociatePortfolioDaoImpl;
import dao.apiDAO;
import dao.apiDAOimpl;
import models.Associate;
import models.AssociatePortfolio;

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


    @Override
    public boolean checkSession(String username){
        return this.apidao.checkSession(username);
    }

    @Override
    public boolean checkSessionTrainer(String username) {
        return this.apidao.checkSessionTrainer(username);
    }

    @Override
    public boolean passwordChange(String username, String newPassword) {

        return this.apidao.passwordChange(username, newPassword);
    }
    
    public Associate getUserBySalesForceId(String salesforceId){
        return(this.associatedao.getAssociateBySalesforce(salesforceId));
    }

    public AssociatePortfolio getPortfolioBySalesForceId(String salesforceId){
        return(this.portfoliodao.getPortfolioBySalesforce(salesforceId));
    }

    public String getBatchID(String email){
        return(this.associatedao.getBatchID(email));
    }

}
