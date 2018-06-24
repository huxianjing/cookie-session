package com.lyq.servlet.session;

public class SessionUtils {

    private CacheManager cacheManager = new CacheManager();

    /**
     * 初始化cacheManager
     */
    public void init(){
        if(cacheManager != null){
            cacheManager = new CacheManager();
        }
    }

    /**
     * 新增一个session，返回一个sessionId
     */
    public Object   setAttribute(Object value){
        //生成SessionId
        String sessionId = TokenUtils.getToken();
        cacheManager.put(sessionId,value);
        return value;
    }

  /*  public Object getAttribute(Object key){
        cacheManager.get(key);
    }*/
}
