package com.lyq.servlet.session;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * 提供缓存api
 */
public class CacheManager {
    //存放缓存数据
    private Map<String, Cache> cacheMap = new HashMap<String, Cache>();

    public void put(String key, Object value) {
        put(key, value, null);
    }

    public synchronized void put(String key, Object value, Long timeOut) {
        Cache cache = new Cache();
        cache.setKey(key);
        cache.setValue(value);
        if (timeOut != null) {
            //保存的是整个毫秒数
            cache.setTimeOut(System.currentTimeMillis() + timeOut);
        }
        cacheMap.put(key, cache);
    }


    public synchronized void del(String key) {
        cacheMap.remove(key);
    }

    public synchronized Object get(String key) {
        Object obj = null;
        Cache cache = cacheMap.get(key);
        if (cache != null) {
            obj = cache.getValue();
        }
        return obj;
    }

    public synchronized void remove(String key) {
        System.out.println("正在删除");
        cacheMap.remove(key);
    }

    /**
     * 定时检查删除有效期的值
     */
    public synchronized void checkValidityData() {
        for (String key : cacheMap.keySet()) {
            Cache cache = cacheMap.get(key);
            if (cache == null) {
                break;
            }
            //之前存放的毫秒数
            Long timeOut = cache.getTimeOut();
            //当前时间的毫秒数
            long currentTimeMillis = System.currentTimeMillis();
            if ((currentTimeMillis - timeOut) > 0) {
                remove(key);
            }
        }
    }

    public static void main(String[] args) {
        final CacheManager cacheManager = new CacheManager();
        cacheManager.put("userName", "123", 5000l);
        System.out.println("保存成功");

        ScheduledExecutorService scheduledThreadPool = Executors.newScheduledThreadPool(5);
        scheduledThreadPool.schedule(new Runnable() {
            public void run() {
                cacheManager.checkValidityData();
            }
        }, 5000, TimeUnit.MILLISECONDS);
        try {
            Thread.sleep(5001);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        String value = (String) cacheManager.get("userName");
        System.out.println("userName:" + value);
    }
}
