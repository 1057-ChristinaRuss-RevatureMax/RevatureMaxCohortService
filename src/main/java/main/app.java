package main;

import com.fasterxml.jackson.databind.ObjectMapper;
import controllers.*;
import controllers.Controller;
import jdk.internal.util.xml.impl.Input;
import controllers.ControllerUtils.*;



//import static controllers.ControllerUtils.mapFromJson;

public class app {
    public static void main( String[] args){
        Controller.getAllUsers();
        String category = "email";
        ControllerUtils.JSONconvert(Controller.getAllUsers(), category);
    }
}