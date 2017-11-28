package com.liuyihui.common.http;

import org.junit.Before;
import org.junit.Test;

public class TestHttpService {


    @Before
    public void bef() {
    }

    @Test
    public void testhttpGetRequest() {
        String ur = "http://www.baidu.com";
        String result = HttpService.getInstance().getJsonFromUrl(ur);
        System.out.println(result);
    }

}
