package com.gbq.diary.beans;

import java.util.List;

/**
 * 类说明：
 * Author: Kuzan
 * Date: 2017/12/25 16:35.
 */
public class ProvinceBean {
    long id;
    String name;
    List<CityBean> cityList;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<CityBean> getCityList() {
        return cityList;
    }

    public void setCityList(List<CityBean> cityList) {
        this.cityList = cityList;
    }
}
