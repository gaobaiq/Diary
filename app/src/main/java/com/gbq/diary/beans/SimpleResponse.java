package com.gbq.diary.beans;

import java.io.Serializable;

/**
 * 类说明：
 * Author: Kuzan
 * Date: 2017/5/26 17:50.
 */
public class SimpleResponse implements Serializable {
    private static final long serialVersionUID = -1477609349345966116L;

    public int code;
    public String msg;

    public LzyResponse toLzyResponse() {
        LzyResponse lzyResponse = new LzyResponse();
        lzyResponse.code = code;
        lzyResponse.msg = msg;
        return lzyResponse;
    }
}
