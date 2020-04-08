package com.tq.ad.handler;

import com.alibaba.fastjson.JSON;
import com.tq.ad.dump.table.*;
import com.tq.ad.index.DataTable;
import com.tq.ad.index.IndexAware;
import com.tq.ad.index.adplan.AdPlanIndex;
import com.tq.ad.index.adplan.AdPlanObject;
import com.tq.ad.index.adunit.AdUnitIndex;
import com.tq.ad.index.adunit.AdUnitObject;
import com.tq.ad.index.creative.CreativeIndex;
import com.tq.ad.index.creative.CreativeObject;
import com.tq.ad.index.creativeunit.CreativeUnitIndex;
import com.tq.ad.index.creativeunit.CreativeUnitObject;
import com.tq.ad.index.district.UnitDistrictIndex;
import com.tq.ad.mysql.constant.OpType;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.omg.CORBA.PRIVATE_MEMBER;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

@Slf4j
public class AdLevelDataHandler {

    public static void handleLvl2(AdPlanTable adPlanTable,OpType type) {

        AdPlanObject planObject=new AdPlanObject(adPlanTable.getId(),adPlanTable.getUserId(),adPlanTable.getPlanStatus()
        , adPlanTable.getStartDate(),adPlanTable.getEndDate());

        hanldeBinlogEvent(DataTable.of(AdPlanIndex.class),planObject.getPlanId(),planObject,type);
    }

    public static void handleLvl2(AdCreativeTable creativeTable, OpType type) {

        CreativeObject creativeObject=new CreativeObject(creativeTable.getAdId(),creativeTable.getName(),creativeTable.getType()
                , creativeTable.getMaterialType(),creativeTable.getHeight(),creativeTable.getWidth(),creativeTable.getAuditStatus(),creativeTable.getAdUrl());

        hanldeBinlogEvent(DataTable.of(CreativeIndex.class),creativeObject.getAdId(),creativeObject,type);
    }

    public static void handleLvl3(AdUnitTable adUnitTable, OpType type) {

        AdPlanObject adPlanObject=DataTable.of(AdPlanIndex.class).get(adUnitTable.getPlanId());
        if(adPlanObject==null){
            log.error("handler lvl3 found AdPlanObject havent create:{}",JSON.toJSONString(adUnitTable));
            return;
        }
        AdUnitObject adUnitObject=new AdUnitObject(adUnitTable.getUnitId(),adUnitTable.getUnitStatus(),adUnitTable.getPositionType(),
                adUnitTable.getPlanId(),adPlanObject);

        hanldeBinlogEvent(DataTable.of(AdUnitIndex.class),adUnitObject.getUnitId(),adUnitObject,type);
    }

    public static void handleLvl3(AdCreativeUnitTable adCreativeUnitTable, OpType type) {

        if(type==OpType.UPDATE){
            log.error("not support update");
            return;
        }
        AdUnitObject adUnitObject=DataTable.of(AdUnitIndex.class).get(adCreativeUnitTable.getUnitId());
        CreativeObject creativeObject=DataTable.of(CreativeIndex.class).get(adCreativeUnitTable.getAdId());

        if(adUnitObject==null||creativeObject==null){
            log.error("handler lvl3 found AdUnit or Creative havent create:{}", JSON.toJSONString(adCreativeUnitTable));
            return;
        }
        CreativeUnitObject creativeUnitObject=new CreativeUnitObject(adCreativeUnitTable.getAdId(),adCreativeUnitTable.getUnitId());

        hanldeBinlogEvent(DataTable.of(CreativeUnitIndex.class),creativeUnitObject.getAdId()+"-"+creativeUnitObject.getUnitId(),creativeUnitObject,type);
    }

    public static void handleLvl4(AdUnitDistrictTable adUnitDistrictTable, OpType type) {

        if(type==OpType.UPDATE){
            log.error("not support update");
            return;
        }

        AdUnitObject unitObject= DataTable.of(AdUnitIndex.class).get(adUnitDistrictTable.getUnitId());
        if(unitObject==null){
            log.error("handler lvl4 found AdUnit haven't create:{}", JSON.toJSONString(adUnitDistrictTable));
            return;
        }

        String key=adUnitDistrictTable.getProvince()+"-"+adUnitDistrictTable.getCity();
        Set<Long> value=new HashSet<>(Collections.singleton(adUnitDistrictTable.getUnitId()));

        hanldeBinlogEvent(DataTable.of(UnitDistrictIndex.class),key,value,type);
    }



    private static <K,V> void hanldeBinlogEvent(IndexAware<K,V> indexAware, K key, V value, OpType type){

        switch (type){
            case ADD:
                indexAware.add(key,value);
                break;
            case UPDATE:
                indexAware.update(key,value);
                break;
            case DELETE:
                indexAware.delete(key,value);
                break;
            default:
                break;
        }
    }


}
