package com.tq.ad.index.creative;

import com.tq.ad.index.IndexAware;
import com.tq.ad.index.adunit.AdUnitObject;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Component
@Slf4j
public class CreativeIndex implements IndexAware<Long,CreativeObject> {

    private static Map<Long, CreativeObject> objectMap;

    static {
        objectMap=new ConcurrentHashMap<>();
    }

    @Override
    public CreativeObject get(Long key) {
        return objectMap.get(key);
    }

    @Override
    public void add(Long key, CreativeObject value) {
        log.info("before add creative: {}",objectMap);
        objectMap.put(key,value);
        log.info("after add creative: {}",objectMap);
    }

    @Override
    public void update(Long key, CreativeObject value) {
        log.info("before update creative: {}",objectMap);
        CreativeObject old =objectMap.get(key);
        if(old==null){
            objectMap.put(key,value);
        }else {
            old.update(value);
        }
        log.info("after update creative: {}",objectMap);
    }

    @Override
    public void delete(Long key, CreativeObject value) {
        log.info("before delete creative: {}",objectMap);
        objectMap.remove(key);
        log.info("after delete creative: {}",objectMap);
    }
}
