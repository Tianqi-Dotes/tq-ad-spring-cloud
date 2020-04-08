package com.tq.ad.index;


import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.core.PriorityOrdered;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class DataTable implements ApplicationContextAware, PriorityOrdered {

    private static ApplicationContext applicationContext;
    public static final Map<Class,Object> dataTableMap=new ConcurrentHashMap<>();
    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {

        DataTable.applicationContext=applicationContext;
    }

    @Override
    //加载bean的顺序
    public int getOrder() {
        return PriorityOrdered.HIGHEST_PRECEDENCE;
    }

    public static <T> T of(Class<T> clz){
        T instance = (T) dataTableMap.get(clz);
        if(instance!=null){
            return instance;
        }
        dataTableMap.put(clz,bean(clz));
        return (T) dataTableMap.get(clz);
    }

    private static <T> T bean(String beanName){
        return (T)applicationContext.getBean(beanName);
    }
    private static <T> T bean(Class clz){
        return (T)applicationContext.getBean(clz);
    }


}
