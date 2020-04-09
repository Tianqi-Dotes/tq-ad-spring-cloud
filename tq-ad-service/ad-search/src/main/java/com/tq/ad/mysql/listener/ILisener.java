package com.tq.ad.mysql.listener;

import com.tq.ad.mysql.dto.BinlogRowData;

public interface ILisener {

    void register();

    void onEvent(BinlogRowData binlogRowData);
}
