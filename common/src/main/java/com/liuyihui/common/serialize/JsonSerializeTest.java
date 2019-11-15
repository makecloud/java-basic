package com.liuyihui.common.serialize;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.alibaba.fastjson.asm.Type;
import com.alibaba.fastjson.parser.Feature;
import com.google.gson.JsonObject;
import org.junit.Test;

import java.util.List;

/**
 * use Alibaba FastJson
 */
public class JsonSerializeTest {


    private String pagejson =
            "{\n" +
                    "    \"totalCount\" : 13,\n" +
                    "    \"list\" : [ {\n" +
                    "        \"id\" : 47,\n" +
                    "        \"name\" : \"测试屏幕\",\n" +
                    "        \"isScreenComplete\" : false,\n" +
                    "        \"companyId\" : 12,\n" +
                    "        \"companyName\" : \"saas\",\n" +
                    "        \"customerId\" : 4,\n" +
                    "        \"customerName\" : \"saas客户1\",\n" +
                    "        \"beginDate\" : \"2019-07-16\",\n" +
                    "        \"endDate\" : \"2019-08-31\",\n" +
                    "        \"playTime\" : 15,\n" +
                    "        \"adType\" : 1,\n" +
                    "        \"adTypeDesc\" : \"商业广告\",\n" +
                    "        \"dayPlayTimes\" : 120,\n" +
                    "        \"timeInterval\" : -1,\n" +
                    "        \"putStatus\" : 1,\n" +
                    "        \"putStatusDesc\" : \"创建中\",\n" +
                    "        \"status\" : \"创建中\",\n" +
                    "        \"isEnable\" : true,\n" +
                    "        \"positionCount\" : 1,\n" +
                    "        \"playTimes\" : 0,\n" +
                    "        \"playDuration\" : 0,\n" +
                    "        \"createdTime\" : \"2019-07-16 16:07:44\",\n" +
                    "        \"modifiedTime\" : \"2019-07-16 16:07:47\"\n" +
                    "        }, {\n" +
                    "            \"id\" : 31,\n" +
                    "        \"name\" : \"jy\",\n" +
                    "        \"isScreenComplete\" : true,\n" +
                    "        \"companyId\" : 12,\n" +
                    "        \"companyName\" : \"saas\",\n" +
                    "        \"customerId\" : 4,\n" +
                    "        \"customerName\" : \"saas客户1\",\n" +
                    "        \"beginDate\" : \"2019-07-15\",\n" +
                    "        \"endDate\" : \"2019-07-30\",\n" +
                    "        \"playTime\" : 15,\n" +
                    "        \"adType\" : 1,\n" +
                    "        \"adTypeDesc\" : \"商业广告\",\n" +
                    "        \"dayPlayTimes\" : 200,\n" +
                    "        \"timeInterval\" : -1,\n" +
                    "        \"putStatus\" : 4,\n" +
                    "        \"putStatusDesc\" : \"审核不通过\",\n" +
                    "        \"status\" : \"审核不通过\",\n" +
                    "        \"isEnable\" : true,\n" +
                    "        \"positionCount\" : 2,\n" +
                    "        \"playTimes\" : 0,\n" +
                    "        \"playDuration\" : 0,\n" +
                    "        \"createdTime\" : \"2019-07-15 15:51:09\",\n" +
                    "        \"modifiedTime\" : \"2019-08-16 17:46:11\"\n" +
                    "        }\n" +
                    "    ]}";

    /**
     * 测试json串转为带泛型参数的实体类对象
     * <p>
     * 测试包含数组的json串转为包含list的实体类对象
     */
    @Test
    public void test() {
        Page<AdUnit> adUnitPage;
        adUnitPage = JSON.parseObject(pagejson, new TypeReference<Page<AdUnit>>() {
        });

        AdUnit adUnit = adUnitPage.getList().get(0);
        System.out.println(adUnit);
    }


    /**
     * 测试使用fastjson一个方法既能转为object又能转为list
     */
    public void testFastJsonConvertObjectAndListInOne() {

    }
}
