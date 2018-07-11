package com.sawelly.fpog.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * 基础代码实体继承此类
 * @author XiangWeiWei
 *
 */
public abstract class BaseCommonEntity implements Serializable {

	protected Integer currentPage = 1;
	protected Integer pageSize = 15;
	protected Integer start;
	protected Integer limit;

	protected Integer creatorId;
	protected Date createDate;
	protected Integer modifierId;
	protected Date modifyDate;

	private String json;
	
	public Integer getCreatorId() {
		return creatorId;
	}

	public void setCreatorId(Integer creatorId) {
		this.creatorId = creatorId;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public Integer getModifierId() {
		return modifierId;
	}

	public void setModifierId(Integer modifierId) {
		this.modifierId = modifierId;
	}

	public Date getModifyDate() {
		return modifyDate;
	}

	public void setModifyDate(Date modifyDate) {
		this.modifyDate = modifyDate;
	}

	public Integer getCurrentPage() {
		return currentPage == null ? 1 : currentPage;
	}

	public void setCurrentPage(Integer currentPage) {
		this.currentPage = currentPage;
	}

	public Integer getPageSize() {
		return pageSize == null ? 20 : pageSize;
	}

	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}

	public String getJson() {
		return json;
	}

	public void setJson(String json) {
		this.json = json;
	}


	public Integer getStart() {
		return start == null ? 0 : start; // 默认0
	}

	public void setStart(Integer start) {
		this.start = start;
	}

	public Integer getLimit() {
		// 缺省10条记录
		return limit == null ? 10 : limit;
	}

	public void setLimit(Integer limit) {
		this.limit = limit;
	}

//	public String getCreateDateStr() {
//		if (null == this.createDate) {
//			return "";
//		}
//		// return DateUtils.howLongAgo(this.createDate);
//		return new PrettyTime(Locale.SIMPLIFIED_CHINESE).format(createDate);
//	}
}
