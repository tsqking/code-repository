package com.clps.tmp.common.util;

import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;

public class BtTableUtil {
	/**
	 * 封装BootStrap请求数据
	 * */
	public Map<String,Object> getParamers(HttpServletRequest request) {
        Map<String, String[]> req = request.getParameterMap();
        if ((req != null) && (!req.isEmpty())) {  
            Map<String, Object> dataMap = new HashMap<String, Object>();
            Collection<?> keys = req.keySet();
            for (Iterator<?> i = keys.iterator(); i.hasNext();) {
                String key =  i.next().toString();
                Object value = req.get(key);
                Object v = null;
                if ((value.getClass().isArray()) && (((Object[]) value).length == 1)) {
                    v = ((Object[]) value)[0];
                } else {
                    v = value;
                }
                if ((v != null) && ((v instanceof String))) {
                    String s = ((String) v).trim();
                    if (s.length() > 0) {
                        dataMap.put(key, s);
                    }
                }
            }
            return dataMap;
        }
        return null;
    
	}
} 
