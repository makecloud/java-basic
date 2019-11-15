package com.liuyihui.common.serialize;

import java.io.Serializable;
import java.util.List;

public class Page<T> implements Serializable {

	/**
	 * @Fields serialVersionUID
	 */

	private static final long serialVersionUID = 1L;
	/** 分页数量 */
	private Integer totalCount = null;
	/** 列表数据 */
	private List<T> list = null;

	/**
	 * @return totalCount
	 */
	public Integer getTotalCount() {
		return totalCount;
	}

	/**
	 * <p>设置 totalCount</p>
	 * 
	 * @param totalCount
	 */
	public void setTotalCount(Integer totalCount) {
		this.totalCount = totalCount;
	}

	/**
	 * @return list
	 */
	public List<T> getList() {
		return list;
	}

	/**
	 * <p>设置 list</p>
	 * 
	 * @param list
	 */
	public void setList(List<T> list) {
		this.list = list;
	}

}