package com.liuyihui.common.serialize;

import java.io.Serializable;

public class AdUnitAuditAbstract implements Serializable {

	/** 广告投放广告标识 */
	private Long adUnitId = null;
	/** 广告投放广告名称 */
	private String adUnitName = null;
	/** 是否通过(0:未通过 1:通过) */
	private Boolean isPass = null;
	/** 备注 */
	private String remark = null;
	/** 创建时间 */
	private String createdTime = null;
	/** 审核员标识 */
	private Long auditorId = null;
	/** 审核员名称 */
	private String auditorName = null;

	public Long getAdUnitId() {
		return adUnitId;
	}

	public void setAdUnitId(Long adUnitId) {
		this.adUnitId = adUnitId;
	}

	public String getAdUnitName() {
		return adUnitName;
	}

	public void setAdUnitName(String adUnitName) {
		this.adUnitName = adUnitName;
	}

	public Boolean getPass() {
		return isPass;
	}

	public void setPass(Boolean pass) {
		isPass = pass;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getCreatedTime() {
		return createdTime;
	}

	public void setCreatedTime(String createdTime) {
		this.createdTime = createdTime;
	}

	public Long getAuditorId() {
		return auditorId;
	}

	public void setAuditorId(Long auditorId) {
		this.auditorId = auditorId;
	}

	public String getAuditorName() {
		return auditorName;
	}

	public void setAuditorName(String auditorName) {
		this.auditorName = auditorName;
	}
}