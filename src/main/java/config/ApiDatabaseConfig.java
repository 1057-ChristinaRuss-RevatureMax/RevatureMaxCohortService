package config;
import org.json.JSONArray;
import org.json.JSONObject;
import dao.apiDAOimpl;
import controllers.apiUtils;
import models.Associate;
import java.util.ArrayList;
import dao.AssociateDaoImpl;
import dao.AssociatePortfolioDaoImpl;

public class ApiDatabaseConfig {

    public static void initAssociates(){
        AssociateDaoImpl associatedb = new AssociateDaoImpl();
        AssociatePortfolioDaoImpl portfoliodb = new AssociatePortfolioDaoImpl();
        ArrayList userList = new ArrayList<Associate>();
        String parambody = apiDAOimpl.getAllUsers();
        userList = apiUtils.JSONConvertAssociate(parambody);


        //void createOne(String salesforceId, String firstName, String lastName, String email, String pass_word);
        for(int i=0; i<userList.size(); i++){
            JSONObject user = new JSONObject(userList.get(i));
            associatedb.createOne(user.getString("salesforceId"),user.getString("firstname"),user.getString("lastname"),user.getString("email"),"password");
            portfoliodb.createOne(user.getString("salesforceId"));
            
        }
        
    }
}