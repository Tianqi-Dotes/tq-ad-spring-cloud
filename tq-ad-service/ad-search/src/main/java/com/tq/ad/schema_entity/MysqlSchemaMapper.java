package com.tq.ad.schema_entity;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface MysqlSchemaMapper {

    List<MysqlSchema> queryMysqlSchemaColumn(@Param("tableName")String tableName);
}
