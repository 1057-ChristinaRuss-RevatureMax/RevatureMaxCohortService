package models;

import java.util.Arrays;

public class Batch {
        private AssociateAssignment[] associateAssignments;
        private String batchId;
        private int currentWeek;
        private models.EmployeeAssignment[] employeeAssignments;
        private String endDate;
        private int goodGrade;
        private int id;
        private String location;
        private String name;
        private int passingGrade;
        private String skill;
        private String startDate;
        private String type;

        public Batch(AssociateAssignment[] associateAssignments, String batchId, int currentWeek, models.EmployeeAssignment[] employeeAssignments, String endDate, int goodGrade, int id, String location, String name, int passingGrade, String skill, String startDate, String type) {
            this.associateAssignments = associateAssignments;
            this.batchId = batchId;
            this.currentWeek = currentWeek;
            this.employeeAssignments = employeeAssignments;
            this.endDate = endDate;
            this.goodGrade = goodGrade;
            this.id = id;
            this.location = location;
            this.name = name;
            this.passingGrade = passingGrade;
            this.skill = skill;
            this.startDate = startDate;
            this.type = type;
        }

    public AssociateAssignment[] getAssociateAssignments() {
        return associateAssignments;
    }

    public void setAssociateAssignments(AssociateAssignment[] associateAssignments) {
        this.associateAssignments = associateAssignments;
    }

    public String getBatchId() {
        return batchId;
    }

    public void setBatchId(String batchId) {
        this.batchId = batchId;
    }

    public int getCurrentWeek() {
        return currentWeek;
    }

    public void setCurrentWeek(int currentWeek) {
        this.currentWeek = currentWeek;
    }

    public EmployeeAssignment[] getEmployeeAssignments() {
        return employeeAssignments;
    }

    public void setEmployeeAssignments(EmployeeAssignment[] employeeAssignments) {
        this.employeeAssignments = employeeAssignments;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public int getGoodGrade() {
        return goodGrade;
    }

    public void setGoodGrade(int goodGrade) {
        this.goodGrade = goodGrade;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPassingGrade() {
        return passingGrade;
    }

    public void setPassingGrade(int passingGrade) {
        this.passingGrade = passingGrade;
    }

    public String getSkill() {
        return skill;
    }

    public void setSkill(String skill) {
        this.skill = skill;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "{" +
                "'associateAssignments':'" + Arrays.toString(associateAssignments) +
                ", 'batchId=':'" + batchId + '\'' +
                ", 'currentWeek=':'" + currentWeek +
                ", 'employeeAssignments=':'" + Arrays.toString(employeeAssignments) +
                ", 'endDate=':'" + endDate + '\'' +
                ", 'goodGrade=':'" + goodGrade +
                ", 'id=':'" + id +
                ", 'location=':'" + location + '\'' +
                ", 'name'=':'" + name + '\'' +
                ", 'passingGrade=':'" + passingGrade +
                ", 'skill=':'" + skill + '\'' +
                ", 'startDate=':'" + startDate + '\'' +
                ", 'type=':'" + type + '\'' +
                '}';
    }
}