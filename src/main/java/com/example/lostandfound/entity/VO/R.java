package com.example.lostandfound.entity.VO;

import lombok.Data;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
@Data
public class R {

    private Integer code;
    private boolean success;
    private String message;
    private Map<String, Object> data = new HashMap<>();

    private R() {
    }

    public static R ok() {
        R r = new R();
        r.setCode(200);
        r.setMessage("成功");
        r.setSuccess(true);
        return r;
    }

    public static R error() {
        R r = new R();
        r.setCode(400);
        r.setMessage("失败");
        r.setSuccess(false);
        return r;
    }


    public R code(int code) {

//        this.setCode(code);
        this.code = code;
        return this;
    }

    public R message(String message) {
        this.message = message;
        return this;
    }

    public R success(boolean success) {
        this.success = success;
        return this;
    }

    public R data(String key, Object obj) {
        this.data.put(key, obj);
        return this;
    }

    public R data(HashMap<String, Object> data) {
        this.setData(data);
        return this;
    }
}
