package models;

import java.util.Arrays;

public class AssociateDto {
    private String batchId;
    private String email;
    private FlagDto flag;
    private String id;
    private String name;
    private String trainingStatus;


    public AssociateDto(String batchId, String email, FlagDto flag, String id, String name, String trainingStatus) {
        this.batchId = batchId;
        this.email = email;
        this.flag = flag;
        this.id = id;
        this.name = name;
        this.trainingStatus = trainingStatus;
    }

    public AssociateDto() {

    }


    public String getBatchId() {
        return batchId;
    }

    public void setBatchId(String batchId) {
        this.batchId = batchId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }


    public FlagDto getFlag() {
        return flag;
    }

    public void setFlag(FlagDto flag) {

    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTrainingStatus() {
        return trainingStatus;
    }

    public void setTrainingStatus(String trainingStatus) {
        this.trainingStatus = trainingStatus;
    }

    @Override
    public String toString() {
        return "{" +
                "batchId=':'" + batchId + '\'' +
                ", email=':'" + email + '\'' +
                ", flag=':'" + flag +
                ", id=':'" + id + '\'' +
                ", name=':'" + name + '\'' +
                ", trainingStatus=':'" + trainingStatus + '\'' +
                '}';
    }
}