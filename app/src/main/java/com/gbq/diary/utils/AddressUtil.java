package com.gbq.diary.utils;

import com.gbq.diary.beans.CityBean;
import com.gbq.diary.beans.ProvinceBean;

import java.util.ArrayList;
import java.util.List;

/**
 * 类说明：
 * Author: Kuzan
 * Date: 2017/12/25 16:36.
 */
public class AddressUtil {

    public static List<ProvinceBean> getAddress() {
        List<ProvinceBean> list = new ArrayList<>();
        List<CityBean> cityList = new ArrayList<>();

        ProvinceBean bean = new ProvinceBean();
        CityBean cityBean = new CityBean();
        cityBean.setId(10001L);
        cityBean.setName("广州");
        cityList.add(cityBean);

        cityBean = new CityBean();
        cityBean.setId(10002L);
        cityBean.setName("深圳");
        cityList.add(cityBean);

        cityBean = new CityBean();
        cityBean.setId(10003L);
        cityBean.setName("珠海");
        cityList.add(cityBean);

        bean.setId(11001L);
        bean.setName("广东省");
        bean.setCityList(cityList);
        list.add(bean);

        cityList = new ArrayList<>();
        cityBean = new CityBean();
        cityBean.setId(10004L);
        cityBean.setName("东城区");
        cityList.add(cityBean);

        cityBean = new CityBean();
        cityBean.setId(10005L);
        cityBean.setName("西城区");
        cityList.add(cityBean);

        cityBean = new CityBean();
        cityBean.setId(10006L);
        cityBean.setName("朝阳区");
        cityList.add(cityBean);

        bean = new ProvinceBean();
        bean.setId(11002L);
        bean.setName("北京市");
        bean.setCityList(cityList);
        list.add(bean);

        cityList = new ArrayList<>();
        cityBean = new CityBean();
        cityBean.setId(10007L);
        cityBean.setName("玄武区");
        cityList.add(cityBean);

        cityBean = new CityBean();
        cityBean.setId(10008L);
        cityBean.setName("鼓楼区");
        cityList.add(cityBean);

        cityBean = new CityBean();
        cityBean.setId(10009L);
        cityBean.setName("建邺区");
        cityList.add(cityBean);

        bean = new ProvinceBean();
        bean.setId(11003L);
        bean.setName("南京市");
        bean.setCityList(cityList);
        list.add(bean);

        return list;
    }
}
