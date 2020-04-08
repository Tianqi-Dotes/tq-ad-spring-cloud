package com.tq.ad.index.keyword;

import com.tq.ad.index.IndexAware;
import com.tq.ad.index.adplan.AdPlanObject;
import com.tq.ad.utils.CommonUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentSkipListSet;

@Component
@Slf4j
public class UnitKeywordIndex implements IndexAware<String, Set<Long>> {

    private static Map<String, Set<Long>> keywordUnitMap;//反向索引
    private static Map<Long, Set<String>> unitKeywordMap;//反向索引

    static {
        keywordUnitMap=new ConcurrentHashMap<>();
        unitKeywordMap=new ConcurrentHashMap<>();
    }

    @Override
    public Set<Long> get(String key) {

        if(StringUtils.isEmpty(key)){
            return Collections.emptySet();
        }
        Set<Long> result=keywordUnitMap.get(key);
        if(result==null){
            return Collections.emptySet();
        }
        return result;
    }

    @Override
    public void add(String key, Set<Long> value) {


        log.info("unitkeyword index, before add : {}",unitKeywordMap);

        Set<Long> unitIdSet= CommonUtils.getOrCreate(key,keywordUnitMap, ConcurrentSkipListSet::new);
        unitIdSet.addAll(value);

        for(Long unitId:value){
            Set<String> keywordSet=CommonUtils.getOrCreate(unitId,unitKeywordMap,ConcurrentSkipListSet::new);
            keywordSet.add(key);
        }

        log.info("unitkeyword index, after add : {}",unitKeywordMap);
    }

    @Override
    public void update(String key, Set<Long> value) {
        log.error("keyword index not support update");
    }

    @Override
    public void delete(String key, Set<Long> value) {
        log.info("unitkeyword index, before delete : {}",unitKeywordMap);

        Set<Long> unitIds=CommonUtils.getOrCreate(key,keywordUnitMap,ConcurrentSkipListSet::new);
        unitIds.removeAll(value);

        for(Long unitId:value){
            Set<String> keywords=CommonUtils.getOrCreate(unitId,unitKeywordMap,ConcurrentSkipListSet::new);
            keywords.remove(key);
        }
        log.info("unitkeyword index, after delete : {}",unitKeywordMap);
    }

    public boolean match(Long unitId, List<String> keywords){

        if(unitKeywordMap.containsKey(unitId)&&
                CollectionUtils.isNotEmpty(unitKeywordMap.get(unitId))){
            Set<String> unitKeywords=unitKeywordMap.get(unitId);
            return CollectionUtils.isSubCollection(keywords,unitKeywords);
        }

        return false;
    }
}
