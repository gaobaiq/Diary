package com.gbq.diary.beans;

import java.io.Serializable;

/**
 * 类说明：
 * Author: Kuzan
 * Date: 2017/5/26 17:51.
 */
public class LzyResponse<T> implements Serializable {
    private static final long serialVersionUID = 5213230387175987834L;

    public int code;
    public String msg;
    public T data;
}
