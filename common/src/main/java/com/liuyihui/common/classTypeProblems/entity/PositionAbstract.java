package com.liuyihui.common.classTypeProblems.entity;

import java.io.Serializable;
import java.util.List;

/**
 * 媒体位摘要类
 *
 * @author yinjy
 */
public class PositionAbstract extends Position implements Serializable {

    /** 媒体位标识 */
    private Long id = null;
    /** 广告位编码 */
    private String code = null;
    /** 广告位名称 */
    private String name = null;
    /** 地址描述 */
    private String address = null;
    /** 经度 */
    private Double longitude = null;
    /** 纬度 */
    private Double latitude = null;
    /** 媒体位图片 */
    private List<String> imageUrlList = null;
    /** 工作状态(1:未上架;2:已上架; 3:故障中) */
    private String workStatus = null;
    /** 审核状态(0:未提交审核; 1:待审核; 2:审核通过; 3:审核未通过) */
    private String auditStatus = null;
    /** 分辨率名称 */
    private String resolutionName = null;
    /** 播控器编码 */
    private String playerCode = null;
    /** 监拍图片数量 */
    private Integer effectImageNum = null;
    /** 截屏数量 */
    private Integer snapshotNum = null;


    /**
     * 获取媒体位标识
     *
     * @return 媒体位标识
     */
    public Long getId() {
        return id;
    }

    /**
     * 设置媒体位标识
     *
     * @param id 媒体位标识
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * 获取广告位编码
     *
     * @return 广告位编码
     */
    public String getCode() {
        return code;
    }

    /**
     * 设置广告位编码
     *
     * @param code 广告位编码
     */
    public void setCode(String code) {
        this.code = code;
    }

    /**
     * 获取广告位名称
     *
     * @return 广告位名称
     */
    public String getName() {
        return name;
    }

    /**
     * 设置广告位名称
     *
     * @param name 广告位名称
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * 获取地址描述
     *
     * @return 地址描述
     */
    public String getAddress() {
        return address;
    }

    /**
     * 设置地址描述
     *
     * @param address 地址描述
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * 获取经度
     *
     * @return 经度
     */
    public Double getLongitude() {
        return longitude;
    }

    /**
     * 设置经度
     *
     * @param longitude 经度
     */
    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    /**
     * 获取纬度
     *
     * @return 纬度
     */
    public Double getLatitude() {
        return latitude;
    }

    /**
     * 设置纬度
     *
     * @param latitude 纬度
     */
    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    /**
     * 获取媒体位图片
     *
     * @return 媒体位图片
     */
    public List<String> getImageUrlList() {
        return imageUrlList;
    }

    /**
     * 设置媒体位图片
     *
     * @param imageUrlList 媒体位图片
     */
    public void setImageUrlList(List<String> imageUrlList) {
        this.imageUrlList = imageUrlList;
    }

    /**
     * 获取工作状态(1:未上架;2:已上架; 3:故障中)
     *
     * @return 工作状态(1:未上架;2:已上架; 3:故障中)
     */
    public String getWorkStatus() {
        return workStatus;
    }

    /**
     * 设置工作状态(1:未上架;2:已上架; 3:故障中)
     *
     * @param workStatus 工作状态(1:未上架;2:已上架; 3:故障中)
     */
    public void setWorkStatus(String workStatus) {
        this.workStatus = workStatus;
    }

    /**
     * 获取审核状态(0:未提交审核; 1:待审核; 2:审核通过; 3:审核未通过)
     *
     * @return 审核状态(0:未提交审核; 1:待审核; 2:审核通过; 3:审核未通过)
     */
    public String getAuditStatus() {
        return auditStatus;
    }

    /**
     * 设置审核状态(0:未提交审核; 1:待审核; 2:审核通过; 3:审核未通过)
     *
     * @param auditStatus 审核状态(0:未提交审核; 1:待审核; 2:审核通过; 3:审核未通过)
     */
    public void setAuditStatus(String auditStatus) {
        this.auditStatus = auditStatus;
    }

    /**
     * 获取分辨率名称
     *
     * @return 分辨率名称
     */
    public String getResolutionName() {
        return resolutionName;
    }

    /**
     * 设置分辨率名称
     *
     * @param resolutionName 分辨率名称
     */
    public void setResolutionName(String resolutionName) {
        this.resolutionName = resolutionName;
    }

    /**
     * 获取播控器编码
     *
     * @return 播控器编码
     */
    public String getPlayerCode() {
        return playerCode;
    }

    /**
     * 设置播控器编码
     *
     * @param playerCode 播控器编码
     */
    public void setPlayerCode(String playerCode) {
        this.playerCode = playerCode;
    }

    /**
     * 获取监拍图片数量
     *
     * @return 监拍图片数量
     */
    public Integer getEffectImageNum() {
        return effectImageNum;
    }

    /**
     * 设置监拍图片数量
     *
     * @param effectImageNum 监拍图片数量
     */
    public void setEffectImageNum(Integer effectImageNum) {
        this.effectImageNum = effectImageNum;
    }

    /**
     * 获取截屏数量
     *
     * @return 截屏数量
     */
    public Integer getSnapshotNum() {
        return snapshotNum;
    }

    /**
     * 设置截屏数量
     *
     * @param snapshotNum 截屏数量
     */
    public void setSnapshotNum(Integer snapshotNum) {
        this.snapshotNum = snapshotNum;
    }

    @Override
    public String toString() {
        return this.getName();
    }
}
