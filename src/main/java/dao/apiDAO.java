package dao;

import java.util.Map;
/*
 * define how the implementations are going to be for the DAO
 */

public interface apiDAO {
    boolean loginUser(String username, String password);
}