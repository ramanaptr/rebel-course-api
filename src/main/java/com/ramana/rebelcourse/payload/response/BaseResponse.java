package com.ramana.rebelcourse.payload.response;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Getter;
import lombok.Setter;
import org.postgresql.util.PSQLException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

/**
 * code example:
 * 4 = unauthorized
 * 3 = failed
 * 2 = not found
 * 1 = server error
 * 0 = success
 */

@Setter
@Getter
@JsonPropertyOrder({
        "code",
        "msg",
        "data"
})
public class BaseResponse<T> {

    @JsonIgnore
    protected final static Logger log = LoggerFactory.getLogger(BaseResponse.class);
    @JsonIgnore
    protected final static String SUCCESS = "Success";

    private long code = 3;

    private String msg = "";

    private List<String> messages = new ArrayList<>();

    private T data;

    public static <E> BaseResponse<E> getInstance() {
        return new BaseResponse<E>();
    }

    public static <E> BaseResponse<E> setAsFailed() {
        log.error("Maaf, terjadi kesalahan");
        BaseResponse<E> baseResponse = BaseResponse.getInstance();
        baseResponse.setCode(3);
        baseResponse.setMsg("Maaf, terjadi kesalahan");
        baseResponse.setData(null);
        return baseResponse;
    }

    public static <E> BaseResponse<E> setAsFailed(List<String> msgList) {
        log.error("Maaf, terjadi kesalahan");
        BaseResponse<E> baseResponse = BaseResponse.getInstance();
        baseResponse.setCode(3);
        baseResponse.setMsg("Maaf, terjadi kesalahan");
        baseResponse.setData(null);
        baseResponse.setMessages(msgList);
        return baseResponse;
    }

    public static <E> BaseResponse<E> setAsFailed(Exception e) {
        e.printStackTrace();
        log.error("Maaf, terjadi kesalahan | Error:" + e.getMessage() + " | Caused " + e.getCause());
        BaseResponse<E> baseResponse = BaseResponse.getInstance();
        baseResponse.setCode(3);
        baseResponse.setMsg("Maaf, terjadi kesalahan | Error: " + e.getMessage());
        baseResponse.setData(null);
        return baseResponse;
    }

    public static <E> BaseResponse<E> setAsFailed(String message, Exception e) {
        e.printStackTrace();
        log.error(message + " | Error: " + e.getMessage() + " | Caused " + e.getCause());
        BaseResponse<E> baseResponse = BaseResponse.getInstance();
        baseResponse.setCode(3);
        baseResponse.setMsg(message + " | Error: " + e.getMessage());
        baseResponse.setData(null);
        return baseResponse;
    }

    public static <E> BaseResponse<E> setAsFailed(String message) {
        log.error(message);
        BaseResponse<E> baseResponse = BaseResponse.getInstance();
        baseResponse.setCode(3);
        baseResponse.setMsg(message);
        baseResponse.setData(null);
        return baseResponse;
    }

    public static <E> BaseResponse<E> setAsFailed(String message, List<String> messages) {
        log.error(message);
        BaseResponse<E> baseResponse = BaseResponse.getInstance();
        baseResponse.setCode(3);
        baseResponse.setMsg(message);
        baseResponse.setData(null);
        baseResponse.setMessages(messages);
        return baseResponse;
    }

    public static <E> BaseResponse<E> setAsSuccess(E data) {
        log.info(SUCCESS);
        BaseResponse<E> baseResponse = BaseResponse.getInstance();
        baseResponse.setCode(0);
        baseResponse.setMsg(SUCCESS);
        baseResponse.setData(data);
        return baseResponse;
    }

    public static <E> BaseResponse<E> setAsSuccess(String message) {
        log.info(message);
        BaseResponse<E> baseResponse = BaseResponse.getInstance();
        baseResponse.setCode(0);
        baseResponse.setMsg(message);
        baseResponse.setData(null);
        return baseResponse;
    }

    public static <E> BaseResponse<E> setAsSuccess() {
        log.info(SUCCESS);
        BaseResponse<E> baseResponse = BaseResponse.getInstance();
        baseResponse.setCode(0);
        baseResponse.setMsg(SUCCESS);
        baseResponse.setData(null);
        return baseResponse;
    }

    public static <E> BaseResponse<E> setAsSuccess(E data, String message) {
        log.info(message);
        BaseResponse<E> baseResponse = BaseResponse.getInstance();
        baseResponse.setCode(0);
        baseResponse.setMsg(message);
        baseResponse.setData(data);
        return baseResponse;
    }

    public static <E> BaseResponse<E> setAsNotFound() {
        log.error("Data tidak ditemukan");
        BaseResponse<E> baseResponse = BaseResponse.getInstance();
        baseResponse.setCode(2);
        baseResponse.setMsg("Data tidak ditemukan");
        baseResponse.setData(null);
        return baseResponse;
    }

    public static <E> BaseResponse<E> setAsNotFound(String message) {
        log.error(message);
        BaseResponse<E> baseResponse = BaseResponse.getInstance();
        baseResponse.setCode(2);
        baseResponse.setMsg(message);
        baseResponse.setData(null);
        return baseResponse;
    }

    public static <E> BaseResponse<E> setAsNotFound(E data, String message) {
        log.error(message);
        BaseResponse<E> baseResponse = BaseResponse.getInstance();
        baseResponse.setCode(2);
        baseResponse.setMsg(message);
        baseResponse.setData(data);
        return baseResponse;
    }

    public static <E> BaseResponse<E> setUnauthorizedResponse(String message) {
        log.error(message);
        BaseResponse<E> baseResponse = BaseResponse.getInstance();
        baseResponse.setCode(4);
        baseResponse.setMsg(message);
        baseResponse.setData(null);
        return baseResponse;
    }
}