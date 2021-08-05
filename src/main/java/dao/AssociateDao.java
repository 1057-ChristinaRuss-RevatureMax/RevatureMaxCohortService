
package dao;

import models.Associate;

public interface AssociateDao {

    void createAssociate(String salesforceId, String firstName, String lastName, String email, String pass_word);
    Associate getAssociateBySalesforce(String salesforceId);
    Associate getAssociateByEmail(String email);

}