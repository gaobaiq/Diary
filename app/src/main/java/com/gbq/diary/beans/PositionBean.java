package com.gbq.diary.beans;

import com.gbq.diary.enums.PositionType;

/**
 * 类说明：
 * Author: Kuzan
 * Date: 2017/5/26 15:45.
 */
public class PositionBean {
    String title;
    String remarks;
    PositionType type;

    @Override
    public String toString() {
        return "PositionBean{" +
                "title='" + title + '\'' +
                ", remarks='" + remarks + '\'' +
                ", type=" + type +
                '}';
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public PositionType getType() {
        return type;
    }

    public void setType(PositionType type) {
        this.type = type;
    }
}
