package com.ezen.mall.web.common.core.controller;

import java.util.HashMap;
import java.util.Map;

/**
 * 데이터 저장소
 */
public class Model {

    private Map<String, Object> map;

    public Model(){
        map = new HashMap<>();
    }

    public Model(Map<String, Object> map){
        this.map = map;
    }

    public Map<String, Object> getMap() {
        return map;
    }

    public void setMap(Map<String, Object> map) {
        this.map = map;
    }

    @Override
    public String toString() {
        return "Model{" +
                "map=" + map +
                '}';
    }

    public void setAttribute(String key, Object value){
        map.put(key, value);
    }

    public Object getAttribute(String key){
        return map.get(key);
    }
}
