package com.tq.ad.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.netflix.zuul.filters.support.FilterConstants;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class PreRequestFilter extends ZuulFilter {

    @Override
    public String filterType() {
        return FilterConstants.PRE_TYPE;
    }

    @Override
    public int filterOrder() {//数字越小 优先级越高
        return 0;
    }

    @Override
    public boolean shouldFilter() {//是否执行
        return true;
    }

    @Override
    public Object run() throws ZuulException {//具体操作

        RequestContext context=RequestContext.getCurrentContext();
        context.set("startTime",System.currentTimeMillis());

        return null;
    }
}
