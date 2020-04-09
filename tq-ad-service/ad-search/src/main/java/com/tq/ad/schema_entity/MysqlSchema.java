package com.tq.ad.schema_entity;

import lombok.Data;

@Data
public class MysqlSchema {

    private String tableSchema;
    private String tableName;
    private String columnName;
    private Integer ordinalPosition;
}
