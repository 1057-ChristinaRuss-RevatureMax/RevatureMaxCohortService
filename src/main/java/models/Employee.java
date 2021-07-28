package models;

import java.util.Arrays;

public class Employee {
        private String email;
        private String firstName;
        private String lastName;
        private EmployeeAssignment[] trainingBatches;

        public Employee(String email, String firstName, String lastName, EmployeeAssignment[] trainingBatches) {
            this.email = email;
            this.firstName = firstName;
            this.lastName = lastName;
            this.trainingBatches = trainingBatches;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public String getFirstName() {
            return firstName;
        }

        public void setFirstName(String firstName) {
            this.firstName = firstName;
        }

        public String getLastName() {
            return lastName;
        }

        public void setLastName(String lastName) {
            this.lastName = lastName;
        }

        public EmployeeAssignment[] getTrainingBatches() {
            return trainingBatches;
        }

        public void setTrainingBatches(EmployeeAssignment[] trainingBatches) {
            this.trainingBatches = trainingBatches;
        }

    @Override
    public String toString() {
        return "{" +
                "email=':'" + email + '\'' +
                ", firstName=':'" + firstName + '\'' +
                ", lastName=':'" + lastName + '\'' +
                ", trainingBatches':'" + Arrays.toString(trainingBatches) +
                '}';
    }
}
