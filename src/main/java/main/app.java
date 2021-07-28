package main;

import controllers.*;
import dao.apiDAO;


//import static controllers.ControllerUtils.mapFromJson;

public class app {
    public static void main( String[] args){
        apiDAO.getAllUsers();
        System.out.println(apiUtils.JSONconvert(apiDAO.getAllUsers()));
    }
}