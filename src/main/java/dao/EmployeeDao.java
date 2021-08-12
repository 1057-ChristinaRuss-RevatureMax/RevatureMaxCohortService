package dao;

import models.EmployeeDB;

public interface EmployeeDao {

    EmployeeDB getEmployeeBySalesforce(int salesforceId);
}
