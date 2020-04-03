package com.tq.ad.service.serviceImp;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.tq.ad.constant.CommonStatus;
import com.tq.ad.constant.ExceptionConstants;
import com.tq.ad.entity.AdPlan;
import com.tq.ad.entity.AdUser;
import com.tq.ad.exception.AdException;
import com.tq.ad.mapper.AdPlanMapper;
import com.tq.ad.mapper.AdUserMapper;
import com.tq.ad.service.IAdPlanService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.tq.ad.util.CommonUtils;
import com.tq.ad.vo.AdPlanGetRequest;
import com.tq.ad.vo.AdPlanRequest;
import com.tq.ad.vo.AdPlanResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.time.LocalDateTime;
import java.util.List;

/**
 * <p>
 * 推广计划表 服务实现类
 * </p>
 *
 * @author tq
 * @since 2020-04-02
 */
@Service
public class AdPlanServiceImpl extends ServiceImpl<AdPlanMapper, AdPlan> implements IAdPlanService {

    @Autowired
    AdUserMapper adUserMapper;

    @Override
    public AdPlanResponse createNewPlan(AdPlanRequest request) throws AdException {

        if(!request.saveValidation()){
            throw new AdException(ExceptionConstants.ErrorMsg.REQUEST_PARAMS_ERROR);
        }
        AdUser user = adUserMapper.selectById(request.getUserId());
        if(user==null){
            throw new AdException(ExceptionConstants.ErrorMsg.CANT_FIND_DATA);
        }

        QueryWrapper<AdPlan> wrapper=new QueryWrapper<>();
        wrapper.eq("plan_name",request.getPlanName());
        wrapper.eq("user_id",request.getUserId());

        AdPlan oldPlan=this.baseMapper.selectOne(wrapper);
        if(oldPlan!=null){
            throw new AdException(ExceptionConstants.ErrorMsg.SAME_USER_SAME_PLAN_ERROR);
        }
        AdPlan newPlan=new AdPlan(request.getUserId(),request.getPlanName(), CommonUtils.convertStringToDate(request.getStartDate())
                ,CommonUtils.convertStringToDate(request.getEndDate()));
        this.baseMapper.insert(newPlan);



        return new AdPlanResponse(newPlan.getId(),newPlan.getPlanName());
    }

    @Override
    public List<AdPlan> getPlansByIds(AdPlanGetRequest request) throws AdException {

        if(!request.validation()){
            throw new AdException(ExceptionConstants.ErrorMsg.REQUEST_PARAMS_ERROR);
        }


        QueryWrapper<AdPlan> wrapper=new QueryWrapper<>();
        wrapper.eq("user_id",request.getUserId());
        wrapper.in("id",request.getIds());

        return this.baseMapper.selectList(wrapper);
    }

    @Override
    public AdPlanResponse updatePlan(AdPlanRequest request) throws AdException {

        if(!request.updateValidation()){
            throw new AdException(ExceptionConstants.ErrorMsg.REQUEST_PARAMS_ERROR);
        }
        QueryWrapper<AdPlan> wrapper=new QueryWrapper<>();
        wrapper.eq("user_id",request.getUserId());
        wrapper.eq("id",request.getId());
        if(this.baseMapper.selectOne(wrapper)==null){
            throw new AdException(ExceptionConstants.ErrorMsg.CANT_FIND_DATA);
        }
        UpdateWrapper<AdPlan> updateWrapper=new UpdateWrapper<>();
        updateWrapper.eq("id",request.getId());

        if(!StringUtils.isEmpty(request.getPlanName())){
            updateWrapper.set("plan_name",request.getPlanName());
        }
        if(!StringUtils.isEmpty(request.getStartDate())){
            updateWrapper.set("start_date", CommonUtils.convertStringToDate(request.getStartDate()));
        }
        if(!StringUtils.isEmpty(request.getEndDate())){
            updateWrapper.set("end_date", CommonUtils.convertStringToDate(request.getEndDate()));
        }
        updateWrapper.set("update_time", LocalDateTime.now());

        AdPlan plan=new AdPlan();
        this.baseMapper.update(plan,updateWrapper);
        return new AdPlanResponse(plan.getId(),plan.getPlanName());
    }

    @Override
    @Transactional
    public void deletePlan(AdPlanRequest request) throws AdException {

        if(!request.deleteValidation()){
            throw new AdException(ExceptionConstants.ErrorMsg.REQUEST_PARAMS_ERROR);
        }
        QueryWrapper<AdPlan> wrapper=new QueryWrapper<>();
        wrapper.eq("user_id",request.getUserId());
        wrapper.eq("id",request.getId());
        AdPlan plan= this.baseMapper.selectOne(wrapper);
        if(plan==null){
            throw new AdException(ExceptionConstants.ErrorMsg.CANT_FIND_DATA);
        }
        /*plan.setPlanStatus(CommonStatus.INVALID.getStatus());
        plan.setUpdateTime(LocalDateTime.now());*/

        UpdateWrapper<AdPlan> updateWrapper=new UpdateWrapper<>();
        updateWrapper.eq("id",request.getId());
        updateWrapper.set("plan_status", CommonStatus.INVALID.getStatus());
        updateWrapper.set("update_time", LocalDateTime.now());

        this.baseMapper.update(plan,updateWrapper);
    }
}
