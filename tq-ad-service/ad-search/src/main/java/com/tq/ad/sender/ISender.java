package com.tq.ad.sender;

import com.tq.ad.mysql.dto.MysqlRowData;

public interface ISender {

    void sender(MysqlRowData rowData);
}
