package dao;

import models.Associate;

public interface AssociateDao {

    void createOne(String salesforceId, String firstName, String lastName, String email, String pass_word);

}