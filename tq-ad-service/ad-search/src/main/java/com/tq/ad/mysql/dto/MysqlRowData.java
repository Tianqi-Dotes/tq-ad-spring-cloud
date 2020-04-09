package com.tq.ad.mysql.dto;

import com.tq.ad.mysql.constant.OpType;
import lombok.Data;

import java.util.List;
import java.util.Map;

@Data
public class MysqlRowData {

    private String tableName;

    private String level;

    private OpType type;

    private List<Map<String,String>> fieldVlaueMap;
}
