package services;
import dao.EmployeeDaoImpl;
import dao.EmployeePortfolioDao;
import models.Employee;
import models.EmployeePortfolio;

public class employeeServiceImpl {
    EmployeeDaoImpl employeedao;
    EmployeePortfolioDao portfoliodao;

    public employeeServiceImpl(){
        employeedao = new EmployeeDaoImpl();
        portfoliodao = new EmployeePortfolioDao();
    }

    public boolean loginEmployee(String username, String password){
        return employeedao.employeeLogin(username, password);
    }
    public int getSalesForceId(String email){
        return employeedao.getSalesForceId(email);

    }

    public Employee getEmployeeBySalesForceId(int salesforceId){
        return employeedao.getEmployeeBySalesForceId(salesforceId);
    }

    public EmployeePortfolio getPortfolioBySalesForceId(int salesforceId){
        return portfoliodao.getPortfolioBySalesForceId(salesforceId);
    }
    
    public void editEmployee(int salesforceId, String firstname, String lastname, String email, String bio, String technology, String location){
        this.employeedao.updateAssociateFirstname(salesforceId, firstname);
        this.employeedao.updateAssociateLastname(salesforceId, lastname);
        this.employeedao.updateAssociateEmail(salesforceId, email);
        this.portfoliodao.updateBio(salesforceId, bio);
        this.portfoliodao.updateTechnology(salesforceId, technology);
        this.portfoliodao.updateLocation(salesforceId, location);
    }
}
