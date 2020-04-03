package com.tq.ad.service.serviceImp;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.netflix.discovery.CommonConstants;
import com.tq.ad.constant.CommonStatus;
import com.tq.ad.constant.ExceptionConstants;
import com.tq.ad.entity.AdUser;
import com.tq.ad.exception.AdException;
import com.tq.ad.mapper.AdUserMapper;
import com.tq.ad.service.IAdUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.tq.ad.util.CommonUtils;
import com.tq.ad.vo.CreateUserRequest;
import com.tq.ad.vo.CreateUserResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * <p>
 * 用户信息表 服务实现类
 * </p>
 *
 * @author tq
 * @since 2020-04-02
 */
@Service
@Slf4j
public class AdUserServiceImpl extends ServiceImpl<AdUserMapper, AdUser> implements IAdUserService {


    @Override
    @Transactional
    public CreateUserResponse createNewUser(CreateUserRequest request) throws AdException {

        if(!request.validate()){
           throw new AdException(ExceptionConstants.ErrorMsg.REQUEST_PARAMS_ERROR);
        }

        QueryWrapper<AdUser> wrapper=new QueryWrapper<>();
        wrapper.eq("username",request.getUsername());
        if(this.baseMapper.selectOne(wrapper)!=null){
            throw new AdException(ExceptionConstants.ErrorMsg.SAME_NAME_ERROR);
        }
        AdUser newUser=new AdUser(request.getUsername(), CommonUtils.md5(request.getUsername()));
        this.baseMapper.insert(newUser);

        newUser =this.baseMapper.selectById(newUser.getId());
        return new CreateUserResponse(newUser.getId(),newUser.getUsername(),newUser.getToken(),newUser.getCreateTime(),
                newUser.getUpdateTime());
    }

}
