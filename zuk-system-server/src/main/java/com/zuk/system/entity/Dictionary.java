package com.zuk.system.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.zuk.core.util.GenPrimaryKeyUtils;

@Entity
@Table(name = "system_dictionary")
@JsonInclude(JsonInclude.Include.ALWAYS)
public class Dictionary implements Serializable {

	/**
	 * 序列号
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * id
	 */
	@Id
	@Column(name = "id", length = 32)
	private String id = GenPrimaryKeyUtils.getUUID();

	/**
	 * 父级字典-Parent Dictionary
	 */
	@Column(name = "parent_id", length = 32)
	private String parentId;

	/**
	 * 层次编码-Layer Code
	 */
	@Column(name = "code")
	private String code;

	/**
	 * 名称-Name
	 */
	@Column(name = "name")
	private String name;

	/**
	 * 值-Value
	 */
	@Column(name = "value")
	private String value;

	/**
	 * 状态-Status
	 */
	@Column(name = "status")
	private Integer status;

	/**
	 * 描述-Remark
	 */
	@Column(name = "remark")
	private String remark;

	public Dictionary() {
	}

	public Dictionary(String id, String parentId, String code, String name, String value, Integer status,
			String remark) {
		this.id = id;
		this.parentId = parentId;
		this.code = code;
		this.name = name;
		this.value = value;
		this.status = status;
		this.remark = remark;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getParentId() {
		return parentId;
	}

	public void setParentId(String parentId) {
		this.parentId = parentId;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

}
