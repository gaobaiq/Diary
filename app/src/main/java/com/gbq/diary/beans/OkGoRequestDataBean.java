package com.gbq.diary.beans;

import java.io.Serializable;

/**
 * 类说明：
 * Author: Kuzan
 * Date: 2017/5/26 18:00.
 */
public class OkGoRequestDataBean implements Serializable {

    String method;
    String ip;
    String url;
    String des;
    String upload;
    AuthorBean author;

    @Override
    public String toString() {
        return "OkGoRequestDataBean{" +
                "method='" + method + '\'' +
                ", ip='" + ip + '\'' +
                ", url='" + url + '\'' +
                ", des='" + des + '\'' +
                ", upload='" + upload + '\'' +
                ", author=" + author +
                '}';
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getDes() {
        return des;
    }

    public void setDes(String des) {
        this.des = des;
    }

    public String getUpload() {
        return upload;
    }

    public void setUpload(String upload) {
        this.upload = upload;
    }

    public AuthorBean getAuthor() {
        return author;
    }

    public void setAuthor(AuthorBean author) {
        this.author = author;
    }
}
