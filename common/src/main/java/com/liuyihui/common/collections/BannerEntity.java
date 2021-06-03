package com.liuyihui.common.collections;

import java.io.Serializable;

/**
 * 金吉鸟banner
 */
public class BannerEntity implements Serializable {

    private Long matId = null;
    /**
     * 1图片 2视频 3音频 4网页
     */
    private Short matType = null;
    private String matMD5 = null;
    private Integer matSize = null;

    public BannerEntity(Long matId, Short matType, String matMD5, Integer matSize) {
        this.matId = matId;
        this.matType = matType;
        this.matMD5 = matMD5;
        this.matSize = matSize;
    }

    public Long getMatId() {
        return matId;
    }

    public void setMatId(Long matId) {
        this.matId = matId;
    }

    public Short getMatType() {
        return matType;
    }

    public void setMatType(Short matType) {
        this.matType = matType;
    }

    public String getMatMD5() {
        return matMD5;
    }

    public void setMatMD5(String matMD5) {
        this.matMD5 = matMD5;
    }

    public Integer getMatSize() {
        return matSize;
    }

    public void setMatSize(Integer matSize) {
        this.matSize = matSize;
    }


    @Override
    public boolean equals(Object obj) {
        if (obj instanceof BannerEntity) {
            BannerEntity b = (BannerEntity) obj;
            return this.matId.equals(b.matId) && this.matType.equals(b.matType) && this.matMD5.equals(b.matMD5) && this.matSize.equals(b.matSize);
        } else {
            return false;
        }
    }
}
