package models;


public class EmployeeAssignment {
        private Batch batch;
        private String deletedAt;
        private Employee employee;
        private String role;

        public EmployeeAssignment(Batch batch, String deletedAt, Employee employee, String role) {
            this.batch = batch;
            this.deletedAt = deletedAt;
            this.employee = employee;
            this.role = role;
        }

        public Batch getBatch() {
            return batch;
        }

        public void setBatch(Batch batch) {
            this.batch = batch;
        }

        public String getDeletedAt() {
            return deletedAt;
        }

        public void setDeletedAt(String deletedAt) {
            this.deletedAt = deletedAt;
        }

        public Employee getEmployee() {
            return employee;
        }

        public void setEmployee(Employee employee) {
            this.employee = employee;
        }

        public String getRole() {
            return role;
        }

        public void setRole(String role) {
            this.role = role;
        }

    @Override
    public String toString() {
        return "{" +
                "batch=':'" + batch +
                ", deletedAt=':'" + deletedAt + '\'' +
                ", employee=':'" + employee +
                ", role=':'" + role + '\'' +
                '}';
    }
}