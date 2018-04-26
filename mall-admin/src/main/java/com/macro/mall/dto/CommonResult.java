package com.macro.mall.dto;

import com.github.pagehelper.PageInfo;
import com.macro.mall.util.JsonUtil;
import org.springframework.validation.BindingResult;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 通用返回对象
 * Created by macro on 2018/4/26.
 */
public class CommonResult {
    //操作成功
    public static final int SUCCESS = 0;
    //操作失败
    public static final int FAILED = 1;
    //参数校验失败
    public static final int VALIDATE_FAILED = 2;
    //认证失败
    public static final int AUTHENTICATE_FAILED = 3;
    private int code;
    private String message;
    private Object data;

    /**
     * 普通成功返回
     *
     * @param data 获取的数据
     */
    public CommonResult success(Object data) {
        this.code = SUCCESS;
        this.message = "操作成功";
        this.data = data;
        return this;
    }

    /**
     * 返回分页成功数据
     */
    public CommonResult pageSuccess(List data) {
        PageInfo pageInfo = new PageInfo(data);
        long totalPage = pageInfo.getTotal() / pageInfo.getPageSize();
        Map<String, Object> result = new HashMap<>();
        result.put("pageSize", pageInfo.getPageSize());
        result.put("totalPage", totalPage+1);
        result.put("pageNum", pageInfo.getPageNum());
        result.put("list", pageInfo.getList());
        this.code = SUCCESS;
        this.message = "操作成功";
        this.data = result;
        return this;
    }

    /**
     * 普通失败提示信息
     */
    public CommonResult failed() {
        this.code = FAILED;
        this.message = "操作失败";
        return this;
    }

    /**
     * 参数验证失败使用
     *
     * @param message 错误信息
     */
    public CommonResult validateFailed(String message) {
        this.code = VALIDATE_FAILED;
        this.message = message;
        return this;
    }

    /**
     * 参数验证失败使用
     *
     * @param message 错误信息
     */
    public CommonResult authFailed(String message) {
        this.code = AUTHENTICATE_FAILED;
        this.message = "认证失败";
        this.data = message;
        return this;
    }

    /**
     * 参数验证失败使用
     * @param result 错误信息
     */
    public CommonResult validateFailed(BindingResult result) {
        validateFailed(result.getFieldError().getDefaultMessage());
        return this;
    }

    @Override
    public String toString() {
        return JsonUtil.objectToJson(this);
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}