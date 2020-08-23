package com.yoa.base.model;

public class ReturnDTO {
    private int code;
    private String error;
    private Object message;

    public ReturnDTO() {

    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public Object getMessage() {
        return message;
    }

    public void setMessage(Object message) {
        this.message = message;
    }

    public ReturnDTO success(){
        return success("success");
    }

    public ReturnDTO success(Object message){
        this.code=0;
        this.error="";
        this.message=message;
        return this;
    }

    public ReturnDTO fail(String error){
        return fail(-1,error,"");
    }

    public ReturnDTO fail(int code,String error){
        return fail(code,error,"");
    }

    public ReturnDTO fail(int code,String error,Object message){
        this.code=code;
        this.error=error;
        this.message=message;
        return this;
    }
}

