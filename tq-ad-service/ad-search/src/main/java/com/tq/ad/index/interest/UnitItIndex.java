package com.tq.ad.index.interest;

import com.tq.ad.index.IndexAware;
import com.tq.ad.utils.CommonUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentSkipListSet;

@Component
@Slf4j
public class UnitItIndex implements IndexAware<String, Set<Long>> {

    //反向索引 tag到id
    private static Map<String,Set<Long>> itUnitMap;

    private static Map<Long,Set<String>> unitItMap;

    static {
        itUnitMap=new ConcurrentHashMap<>();
        unitItMap=new ConcurrentHashMap<>();
    }

    @Override
    public Set<Long> get(String key) {
        return itUnitMap.get(key);
    }

    @Override
    public void add(String key, Set<Long> value) {
        log.info("unit it index, before add : {}",unitItMap);

        Set<Long> unitIdSet= CommonUtils.getOrCreate(key,itUnitMap, ConcurrentSkipListSet::new);
        unitIdSet.addAll(value);

        for(Long unitId:value){
            Set<String> keywordSet=CommonUtils.getOrCreate(unitId,unitItMap,ConcurrentSkipListSet::new);
            keywordSet.add(key);
        }

        log.info("unit it index, after add : {}",unitItMap);

    }

    @Override
    public void update(String key, Set<Long> value) {
        log.error("interest index not support update");
    }

    @Override
    public void delete(String key, Set<Long> value) {
        log.info("unit it index, before delete : {}",unitItMap);

        Set<Long> unitIds=CommonUtils.getOrCreate(key,itUnitMap,ConcurrentSkipListSet::new);
        unitIds.removeAll(value);

        for(Long unitId:value){
            Set<String> keywords=CommonUtils.getOrCreate(unitId,unitItMap ,ConcurrentSkipListSet::new);
            keywords.remove(key);
        }
        log.info("unit it index, after delete : {}",unitItMap);

    }

    public boolean match(Long unitId, List<String> itTags){

        if(unitItMap.containsKey(unitId)&&
                CollectionUtils.isNotEmpty(unitItMap.get(unitId))){
            Set<String> unitIts=unitItMap.get(unitId);
            return CollectionUtils.isSubCollection(itTags,unitIts);
        }

        return false;
    }
}
