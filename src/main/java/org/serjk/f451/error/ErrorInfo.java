package org.serjk.f451.error;

import javax.persistence.Entity;
import java.io.Serializable;
import javax.xml.bind.annotation.XmlRootElement;

public class ErrorInfo {
    private String errorCode;
    private String message;

    public  ErrorInfo(String errorCode, String message){
        this.errorCode =  errorCode;
        this.message =message;
    }

    public  ErrorInfo(){
        this("","");
    }

    public  void setMessage(String message){
        this.message =message;
    }
    public String getMessage (){
        return  message;
    }

    public  void setErrorCode(String errorCode){
        this.errorCode = errorCode;
    }
    public String getErrorCode (){
        return  errorCode;
    }
}
