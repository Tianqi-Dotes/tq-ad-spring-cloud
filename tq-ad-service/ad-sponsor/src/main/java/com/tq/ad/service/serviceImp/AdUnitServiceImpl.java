package com.tq.ad.service.serviceImp;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.tq.ad.constant.ExceptionConstants;
import com.tq.ad.entity.AdCreative;
import com.tq.ad.entity.AdUnit;
import com.tq.ad.entity.unit_condition.AdUnitDistrict;
import com.tq.ad.entity.unit_condition.AdUnitIt;
import com.tq.ad.entity.unit_condition.AdUnitKeyword;
import com.tq.ad.entity.unit_condition.CreativeUnit;
import com.tq.ad.exception.AdException;
import com.tq.ad.mapper.*;
import com.tq.ad.service.*;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.tq.ad.vo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static com.tq.ad.vo.AdUnitKeywordRequest.*;

/**
 * <p>
 * 推广单元表 服务实现类
 * </p>
 *
 * @author tq
 * @since 2020-04-02
 */
@Service
public class AdUnitServiceImpl extends ServiceImpl<AdUnitMapper, AdUnit> implements IAdUnitService {

    @Autowired
    AdPlanMapper adPlanMapper;
    @Autowired
    IAdUnitKeywordService keywordService;
    @Autowired
    IAdUnitItService itService;
    @Autowired
    IAdUnitDistrictService districtService;
    @Autowired
    IAdCreativeService adCreativeService;
    @Autowired
    ICreativeUnitService creativeUnitService;

    @Override
    public AdUnitResponse createAdUnit(AdUnitRequest request) throws AdException {

        if(!request.createValidation()){
            throw new AdException(ExceptionConstants.ErrorMsg.REQUEST_PARAMS_ERROR);
        }
        if(adPlanMapper.selectById(request.getPlanId())==null){
            throw new AdException(ExceptionConstants.ErrorMsg.CANT_FIND_DATA);
        }

        QueryWrapper<AdUnit> queryWrapper=new QueryWrapper<>();
        queryWrapper.eq("plan_id",request.getPlanId());
        queryWrapper.eq("unit_name",request.getUnitName());
        if(this.baseMapper.selectOne(queryWrapper)!=null){
            throw new AdException(ExceptionConstants.ErrorMsg.SAME_PLAN_SAME_PLAN_UNIT_ERROR);
        }
        AdUnit newUnit=new AdUnit(request.getPlanId(),request.getUnitName(),request.getPositionType(),request.getBudget());
        this.baseMapper.insert(newUnit);


        return new AdUnitResponse(newUnit.getId(),newUnit.getUnitName());
    }

    @Override
    public AdUnitKeywordResponse createUnitKeyword(AdUnitKeywordRequest request) throws AdException {

         List<Long> ids= request.getList().stream().map(UnitKeyword::getUnitId)
         .collect(Collectors.toList());
        if(!isRelatedUnitExist(ids)){
            throw new AdException(ExceptionConstants.ErrorMsg.REQUEST_PARAMS_ERROR);
        }

        List<AdUnitKeyword> keywords=new ArrayList<>();
        request.getList().stream().forEach(keyword -> keywords.add(new AdUnitKeyword(keyword.getUnitId(),keyword.getKeyword())));
        keywordService.saveBatch(keywords);

        List<Long> newIds=Collections.emptyList();
        keywords.stream().map(AdUnitKeyword::getId).forEach(id->newIds.add(id));

        return new AdUnitKeywordResponse(newIds);
    }

    @Override
    public AdUnitItResponse createUnitIt(AdUnitItRequest request) throws AdException {

        List<Long> ids= request.getUnitIts().stream().map(AdUnitItRequest.UnitIt::getUnitId)
                .collect(Collectors.toList());
        if(!isRelatedUnitExist(ids)){
            throw new AdException(ExceptionConstants.ErrorMsg.REQUEST_PARAMS_ERROR);
        }
        List<AdUnitIt> its=new ArrayList<>();
        request.getUnitIts().stream().forEach(it -> its.add(new AdUnitIt(it.getUnitId(),it.getItTag())));
        itService.saveBatch(its);

        List<Long> newIds=Collections.emptyList();
        its.stream().map(AdUnitIt::getId).forEach(id->newIds.add(id));

        return new AdUnitItResponse(newIds);
    }

    @Override
    public AdUnitDistrictResponse createUnitDistrict(AdUnitDistrictRequest request) throws AdException {
        List<Long> ids= request.getDistricts().stream().map(AdUnitDistrictRequest.UnitDistrict::getUnitId)
                .collect(Collectors.toList());
        if(!isRelatedUnitExist(ids)){
            throw new AdException(ExceptionConstants.ErrorMsg.REQUEST_PARAMS_ERROR);
        }
        List<AdUnitDistrict> districts=new ArrayList<>();
        request.getDistricts().stream().forEach(district -> districts.add(new AdUnitDistrict(district.getUnitId(),district.getProvince(),district.getCity())));
        districtService.saveBatch(districts);

        List<Long> newIds=Collections.emptyList();
        districts.stream().map(AdUnitDistrict::getId).forEach(id->newIds.add(id));

        return new AdUnitDistrictResponse(newIds);
    }

    @Override
    public CreativeUnitResponse createCreativeUnit(CreativeUnitRequest request) throws AdException {

        List<Long> unitIds=request.getItemList().stream().map(CreativeUnitRequest.CreativeUnitItem::getUnitId)
                .collect(Collectors.toList());
        List<Long> creativeIds=request.getItemList().stream().map(CreativeUnitRequest.CreativeUnitItem::getCreativeId)
                .collect(Collectors.toList());
        if(!(isRelatedUnitExist(unitIds)&&isRelatedCreativeExist(creativeIds))){
            throw new AdException(ExceptionConstants.ErrorMsg.REQUEST_PARAMS_ERROR);
        }

        List<CreativeUnit> list=new ArrayList<>();
        request.getItemList().stream().forEach(creativeUnitItem -> list.add(new CreativeUnit(creativeUnitItem.getCreativeId(),creativeUnitItem.getUnitId())));
        creativeUnitService.saveBatch(list);

        List<Long> newIds= list.stream().map(CreativeUnit::getId).collect(Collectors.toList());
        return new CreativeUnitResponse(newIds);
    }

    private boolean isRelatedUnitExist(List<Long> ids){

        if(CollectionUtils.isEmpty(ids)) {
            return false;
        }
        QueryWrapper<AdUnit> queryWrapper=new QueryWrapper<>();
        queryWrapper.in("id",ids);
        return this.baseMapper.selectList(queryWrapper).size()==
                new HashSet<>(ids).size();
    }
    private boolean isRelatedCreativeExist(List<Long> ids){

        if(CollectionUtils.isEmpty(ids)) {
            return false;
        }
        QueryWrapper<AdCreative> queryWrapper=new QueryWrapper<>();
        queryWrapper.in("id",ids);
        return adCreativeService.list(queryWrapper).size()==
                new HashSet<>(ids).size();
    }
}
