package com.gbq.diary.beans;

import java.io.Serializable;

/**
 * 类说明：
 * Author: Kuzan
 * Date: 2017/5/27 11:05.
 */
public class AuthorBean implements Serializable {
    String name;
    String fullname;
    String github;
    String address;
    String qq;
    String email;
    String des;

    @Override
    public String toString() {
        return "AuthorBean{" +
                "name='" + name + '\'' +
                ", fullname='" + fullname + '\'' +
                ", github='" + github + '\'' +
                ", address='" + address + '\'' +
                ", qq='" + qq + '\'' +
                ", email='" + email + '\'' +
                ", des='" + des + '\'' +
                '}';
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getGithub() {
        return github;
    }

    public void setGithub(String github) {
        this.github = github;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getQq() {
        return qq;
    }

    public void setQq(String qq) {
        this.qq = qq;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDes() {
        return des;
    }

    public void setDes(String des) {
        this.des = des;
    }
}
