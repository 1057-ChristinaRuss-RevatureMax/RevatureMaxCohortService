package user;

public class Associate {
    private String email;
    private String firstname;
    private boolean flag;
    private String lastname;
    private int salesforceId;
    private AssociateAssignment trainingAssignments;

    public Associate(String email, String firstname, boolean flag, String lastname, int salesforceId, AssociateAssignment trainingAssignments) {
        this.email = email;
        this.firstname = firstname;
        this.flag = flag;
        this.lastname = lastname;
        this.salesforceId = salesforceId;
        this.trainingAssignments = trainingAssignments;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public boolean isFlag() {
        return flag;
    }

    public void setFlag(boolean flag) {
        this.flag = flag;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public int getSalesforceId() {
        return salesforceId;
    }

    public void setSalesforceId(int salesforceId) {
        this.salesforceId = salesforceId;
    }

    public AssociateAssignment getTrainingAssignments() {
        return trainingAssignments;
    }

    public void setTrainingAssignments(AssociateAssignment trainingAssignments) {
        this.trainingAssignments = trainingAssignments;
    }
}

    class Batch {
        private AssociateAssignment[] associateAssignments;
        private String batchId;
        private int currentWeek;
        private EmployeeAssignment[] employeeAssignments;
        private String endDate;
        private int goodGrade;
        private int id;
        private String location;
        private String name;
        private int passingGrade;
        private String skill;
        private String startDate;
        private String type;

        public Batch(AssociateAssignment[] associateAssignments, String batchId, int currentWeek, EmployeeAssignment[] employeeAssignments, String endDate, int goodGrade, int id, String location, String name, int passingGrade, String skill, String startDate, String type) {
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

        public EmployeeAssignment[] setEmployeeAssignments(EmployeeAssignment[] employeeAssignments) {
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
}

class AssociateAssignment{
    private boolean active;
    private Associate associate;
    private Associate.Batch batch;
    private String endDate;
    private String startDate;
    private String trainingStatus;

    public AssociateAssignment(boolean active, Associate associate, Associate.Batch batch, String endDate, String startDate, String trainingStatus) {
        this.active = active;
        this.associate = associate;
        this.batch = batch;
        this.endDate = endDate;
        this.startDate = startDate;
        this.trainingStatus = trainingStatus;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public Associate getAssociate() {
        return associate;
    }

    public void setAssociate(Associate associate) {
        this.associate = associate;
    }

    public Associate.Batch getBatch() {
        return batch;
    }

    public void setBatch(Associate.Batch batch) {
        this.batch = batch;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getTrainingStatus() {
        return trainingStatus;
    }

    public void setTrainingStatus(String trainingStatus) {
        this.trainingStatus = trainingStatus;
    }
}