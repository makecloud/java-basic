package com.liuyihui.common.io;

import com.google.gson.Gson;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class GsonUtil {

    @Test
    public void testGsonCoverList() {
        List<UploadFaceInfo> uploadFaceInfos = new ArrayList<>();
        UploadFaceInfo uploadFaceInfo = new UploadFaceInfo();
        uploadFaceInfo.setAge(1);
        uploadFaceInfo.setGender(9.1);
        uploadFaceInfo.setpId(2);
        uploadFaceInfo.settId(1);
        uploadFaceInfo.setTs(241543);
        uploadFaceInfos.add(uploadFaceInfo);

        uploadFaceInfo = new UploadFaceInfo();
        uploadFaceInfo.setAge(1);
        uploadFaceInfo.setGender(9.1);
        uploadFaceInfo.setpId(2);
        uploadFaceInfo.settId(2);
        uploadFaceInfo.setTs(241543);
        uploadFaceInfos.add(uploadFaceInfo);

        String facedata = new Gson().toJson(uploadFaceInfos);

        System.out.println(facedata);
    }
}
