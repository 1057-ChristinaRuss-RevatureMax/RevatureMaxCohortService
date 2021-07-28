package main;

import controllers.*;
import controllers.Controller;


//import static controllers.ControllerUtils.mapFromJson;

public class app {
    public static void main( String[] args){
        Controller.getAllUsers();
        System.out.println(ControllerUtils.JSONconvert(Controller.getAllUsers()));
    }
}