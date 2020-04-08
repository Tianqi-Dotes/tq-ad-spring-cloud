package com.tq.ad.index.district;


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
public class UnitDistrictIndex implements IndexAware<String,Set<Long>> {

    //反向索引 key province-city到id
    private static Map<String, Set<Long>> districtUnitMap;

    private static Map<Long,Set<String>> unitDistrictMap;

    static {
        districtUnitMap=new ConcurrentHashMap<>();
        unitDistrictMap=new ConcurrentHashMap<>();
    }

    @Override
    public Set<Long> get(String key) {
        return districtUnitMap.get(key);
    }

    @Override
    public void add(String key, Set<Long> value) {
        log.info("unit district index, before add : {}",unitDistrictMap);

        Set<Long> unitIdSet= CommonUtils.getOrCreate(key,districtUnitMap, ConcurrentSkipListSet::new);
        unitIdSet.addAll(value);

        for(Long unitId:value){
            Set<String> keywordSet=CommonUtils.getOrCreate(unitId,unitDistrictMap,ConcurrentSkipListSet::new);
            keywordSet.add(key);
        }

        log.info("unit district index, after add : {}",unitDistrictMap);

    }

    @Override
    public void update(String key, Set<Long> value) {
        log.error("district index not support update");
    }

    @Override
    public void delete(String key, Set<Long> value) {
        log.info("unit district index, before delete : {}",unitDistrictMap);

        Set<Long> unitIds=CommonUtils.getOrCreate(key,districtUnitMap,ConcurrentSkipListSet::new);
        unitIds.removeAll(value);

        for(Long unitId:value){
            Set<String> keywords=CommonUtils.getOrCreate(unitId,unitDistrictMap ,ConcurrentSkipListSet::new);
            keywords.remove(key);
        }
        log.info("unit district index, after delete : {}",unitDistrictMap);
    }

    public boolean match(Long unitId, List<String> itTags){

        if(unitDistrictMap.containsKey(unitId)&&
                CollectionUtils.isNotEmpty(unitDistrictMap.get(unitId))){
            Set<String> unitDistricts=unitDistrictMap.get(unitId);
            return CollectionUtils.isSubCollection(itTags,unitDistricts);
        }

        return false;
    }
}
