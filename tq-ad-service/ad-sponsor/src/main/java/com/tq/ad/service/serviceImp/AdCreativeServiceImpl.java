package com.tq.ad.service.serviceImp;

import com.tq.ad.entity.AdCreative;
import com.tq.ad.mapper.AdCreativeMapper;
import com.tq.ad.service.IAdCreativeService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.tq.ad.vo.CreateUserRequest;
import com.tq.ad.vo.CreativeRequest;
import com.tq.ad.vo.CreativeResponse;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 创意表 服务实现类
 * </p>
 *
 * @author tq
 * @since 2020-04-02
 */
@Service
public class AdCreativeServiceImpl extends ServiceImpl<AdCreativeMapper, AdCreative> implements IAdCreativeService {

    @Override
    public CreativeResponse createCreative(CreativeRequest request) {

        AdCreative creative=request.convertToEntity();
        this.baseMapper.insert(creative);

        return new CreativeResponse(creative.getId(),creative.getName());
    }
}
