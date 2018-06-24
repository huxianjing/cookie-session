package com.lyq.servlet.session;

/**
 * 缓存实体类
 */
public class Cache {

    private String key;

    private Object value;

    //有效期
    private Long timeOut;

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public Object getValue() {
        return value;
    }

    public void setValue(Object value) {
        this.value = value;
    }

    public Long getTimeOut() {
        return timeOut;
    }

    public void setTimeOut(Long timeOut) {
        this.timeOut = timeOut;
    }
}
