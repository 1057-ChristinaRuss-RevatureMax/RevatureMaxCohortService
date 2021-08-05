package services;
import dao.EmployeeDaoImpl;
import dao.EmployeePortfolioDao;

public class employeeServiceImpl {
    EmployeeDaoImpl employeedao;
    EmployeePortfolioDao portfoliodao;

    public employeeServiceImpl(){
        employeedao = new EmployeeDaoImpl();
        portfoliodao = new EmployeePortfolioDao();
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
