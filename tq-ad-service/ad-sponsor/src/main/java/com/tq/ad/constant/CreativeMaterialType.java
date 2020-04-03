package com.tq.ad.constant;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum  CreativeMaterialType {

    JPG(1,"jpg"),
    PNG(2,"png"),

    MP4(3,"mp4"),
    AVI(4,"avi"),

    TXT(5,"txt");

    private int type;
    private String desc;

}
