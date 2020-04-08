package com.tq.ad.index.creativeunit;

import com.tq.ad.index.IndexAware;
import com.tq.ad.utils.CommonUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentSkipListSet;

@Component
@Slf4j
public class CreativeUnitIndex implements IndexAware<String,CreativeUnitObject> {

    //反向索引 key adId-unitId到id
    private static Map<String, CreativeUnitObject> objectMap;
    //key adId v unitId set
    private static Map<Long,Set<Long>> creativeUnitMap;
    //unitId adId set
    private static Map<Long,Set<Long>> unitCreativeMap;

    static {
        objectMap=new ConcurrentHashMap<>();
        creativeUnitMap=new ConcurrentHashMap<>();
        unitCreativeMap=new ConcurrentHashMap<>();
    }

    @Override
    public CreativeUnitObject get(String key) {
        return objectMap.get(key);
    }

    @Override
    public void add(String key, CreativeUnitObject value) {

        log.info("creative unit index, before add : {}",objectMap);

        objectMap.put(key,value);
        Set<Long> unitIds= creativeUnitMap.get(value.getAdId());
        if(CollectionUtils.isEmpty(unitIds)){
            unitIds=new ConcurrentSkipListSet<>();
            creativeUnitMap.put(value.getAdId(),unitIds);
        }
        unitIds.add(value.getUnitId());
        Set<Long> creativeSet=unitCreativeMap.get(value.getUnitId());
        if(CollectionUtils.isEmpty(creativeSet)){
            creativeSet=new ConcurrentSkipListSet<>();
            creativeUnitMap.put(value.getUnitId(),creativeSet);
        }
        creativeSet.add(value.getAdId());

        log.info("creative unit index, after add : {}",objectMap);

    }

    @Override
    public void update(String key, CreativeUnitObject value) {
        log.error("creative index not support update");
    }

    @Override
    public void delete(String key, CreativeUnitObject value) {
        log.info("creative unit index, before delete : {}",objectMap);

        objectMap.remove(key);

        Set<Long> unitIds= creativeUnitMap.get(value.getAdId());
        if(!CollectionUtils.isEmpty(unitIds)){
            unitIds.remove(value.getUnitId());
        }
        Set<Long> creativeSet=unitCreativeMap.get(value.getUnitId());
        if(!CollectionUtils.isEmpty(creativeSet)){
            creativeSet.remove(value.getAdId());
        }
        log.info("creative unit index, after delete : {}",objectMap);
    }
}
