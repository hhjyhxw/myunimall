package com.icloud.basecommon.model;

import lombok.Data;

@Data
public class MallResponse {
    private String code;
    private String message;
    private Object data;

    public MallResponse ok(){
        this.setCode("0000");
        this.setMessage("成功");
        this.setData(true);
        return this;
    }

    public MallResponse ok(Object obj){
        this.setCode("0000");
        this.setMessage("成功");
        this.setData(obj);
        return this;
    }

    public MallResponse error(){
        this.setCode("0001");
        this.setMessage("失败");
        this.setData(false);
        return this;
    }


    public MallResponse okOrError(Boolean result){
        return result==true?this.ok():this.error();
    }
}
