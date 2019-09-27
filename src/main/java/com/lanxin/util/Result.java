package com.lanxin.util;

/**
 * Created by 86187 on 2019/8/19.
 */
public class Result {


    private  Integer code;
    private String msg;
    private Object data;


    public static Result ok(){

        Result result=new Result();
        result.setCode(200);
        result.setMsg("操作成功");

        return result;
    }

    public static Result ok(Object data){

        Result result=new Result();
        result.setCode(200);
        result.setMsg("操作成功");
        result.setData(data);

        return result;
    }

    public static Result failed(){

        Result result=new Result();
        result.setCode(200);
        result.setMsg("操作成功");

        return result;
    }


    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
