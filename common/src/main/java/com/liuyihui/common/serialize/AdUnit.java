package com.liuyihui.common.serialize;

import java.io.Serializable;
import java.util.List;

public class AdUnit implements Serializable {

	/** 广告id */
	private Long id = null;
	/** 投放广告名称 */
	private String name = null;
	/** 审核记录列表 */
	List<AdUnitAuditAbstract> auditList = null;
	/** 屏幕素材是否完成 */
	Boolean isScreenComplete = null;
	/** 公司标识 */
	private Long companyId = null;
	/** 公司名称 */
	private String companyName = null;
	/** 客户标识 */
	private Long customerId = null;
	/** 客户名称 */
	private String customerName = null;
	/** 客户联系人 */
	private String customerContacts = null;
	/** 客户联系电话 */
	private String customerPhone = null;
	/** 开始日期 */
	private String beginDate = null;
	/** 结束日期 */
	private String endDate = null;
	/** 播放时长 */
	private Integer playTime = null;
	/** 广告类型(1:新闻;2:商业广告;3:打底广告) */
	private Short adType = null;
	/** 广告类型描述 */
	private String adTypeDesc = null;
	/** 单日投放次数 */
	private Integer dayPlayTimes = null;
	/** 投放时间间隔 */
	private Integer timeInterval = null;
	/** 投放状态（1创建中 2审核中 3审核通过 4审核不通过 5投放中 6投放结束） */
	private Short putStatus = null;
	/** 投放状态 */
	private String putStatusDesc = null;
	/** 状态 */
	private String status = null;
	/** 启用/停用 */
	private Boolean isEnable = null;
	/** 广告位数量 */
	private Integer positionCount = null;
	/** 播放总次数数量 */
	private Integer playTimes = null;
	/** 播放总时长 */
	private Integer playDuration = null;
	/** 创建时间 */
	private String createdTime = null;
	/** 修改时间 */
	private String modifiedTime = null;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<AdUnitAuditAbstract> getAuditList() {
		return auditList;
	}

	public void setAuditList(List<AdUnitAuditAbstract> auditList) {
		this.auditList = auditList;
	}

	public Boolean getScreenComplete() {
		return isScreenComplete;
	}

	public void setScreenComplete(Boolean screenComplete) {
		isScreenComplete = screenComplete;
	}

	public Long getCompanyId() {
		return companyId;
	}

	public void setCompanyId(Long companyId) {
		this.companyId = companyId;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public Long getCustomerId() {
		return customerId;
	}

	public void setCustomerId(Long customerId) {
		this.customerId = customerId;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public String getCustomerContacts() {
		return customerContacts;
	}

	public void setCustomerContacts(String customerContacts) {
		this.customerContacts = customerContacts;
	}

	public String getCustomerPhone() {
		return customerPhone;
	}

	public void setCustomerPhone(String customerPhone) {
		this.customerPhone = customerPhone;
	}

	public String getBeginDate() {
		return beginDate;
	}

	public void setBeginDate(String beginDate) {
		this.beginDate = beginDate;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	public Integer getPlayTime() {
		return playTime;
	}

	public void setPlayTime(Integer playTime) {
		this.playTime = playTime;
	}

	public Short getAdType() {
		return adType;
	}

	public void setAdType(Short adType) {
		this.adType = adType;
	}

	public String getAdTypeDesc() {
		return adTypeDesc;
	}

	public void setAdTypeDesc(String adTypeDesc) {
		this.adTypeDesc = adTypeDesc;
	}

	public Integer getDayPlayTimes() {
		return dayPlayTimes;
	}

	public void setDayPlayTimes(Integer dayPlayTimes) {
		this.dayPlayTimes = dayPlayTimes;
	}

	public Integer getTimeInterval() {
		return timeInterval;
	}

	public void setTimeInterval(Integer timeInterval) {
		this.timeInterval = timeInterval;
	}

	public Short getPutStatus() {
		return putStatus;
	}

	public void setPutStatus(Short putStatus) {
		this.putStatus = putStatus;
	}

	public String getPutStatusDesc() {
		return putStatusDesc;
	}

	public void setPutStatusDesc(String putStatusDesc) {
		this.putStatusDesc = putStatusDesc;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Boolean getEnable() {
		return isEnable;
	}

	public void setEnable(Boolean enable) {
		isEnable = enable;
	}

	public Integer getPositionCount() {
		return positionCount;
	}

	public void setPositionCount(Integer positionCount) {
		this.positionCount = positionCount;
	}

	public Integer getPlayTimes() {
		return playTimes;
	}

	public void setPlayTimes(Integer playTimes) {
		this.playTimes = playTimes;
	}

	public Integer getPlayDuration() {
		return playDuration;
	}

	public void setPlayDuration(Integer playDuration) {
		this.playDuration = playDuration;
	}

	public String getCreatedTime() {
		return createdTime;
	}

	public void setCreatedTime(String createdTime) {
		this.createdTime = createdTime;
	}

	public String getModifiedTime() {
		return modifiedTime;
	}

	public void setModifiedTime(String modifiedTime) {
		this.modifiedTime = modifiedTime;
	}
}