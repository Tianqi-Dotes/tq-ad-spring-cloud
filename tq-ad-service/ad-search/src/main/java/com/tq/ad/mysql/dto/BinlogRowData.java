package com.tq.ad.mysql.dto;

import com.github.shyiko.mysql.binlog.event.EventType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Map;

@Data
public class BinlogRowData {

    private JsonTable jsonTable;
    private EventType eventType;
    private List<Map<String,String>> after;

    private List<Map<String,String>> before;

}
