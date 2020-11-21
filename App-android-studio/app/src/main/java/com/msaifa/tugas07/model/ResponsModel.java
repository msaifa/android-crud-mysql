package com.msaifa.tugas07.model;

import java.util.List;

public class ResponsModel {

    String  message;
    int status ;
    List<DataModel> data;

    public List<DataModel> getData() {
        return data;
    }

    public void setResult(List<DataModel> result) {
        this.data = result;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
