package com.zuk.system.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.zuk.core.util.GenPrimaryKeyUtils;

@Entity
@Table(name = "system_resource")
@JsonInclude(JsonInclude.Include.ALWAYS)
public class Resource implements Serializable {

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
	 * 父级资源-Parent Resource
	 */
	@Column(name = "parent_id", length = 32)
	private String parentId;

	/**
	 * 类型（目录、菜单、按钮）-Type
	 */
	@Column(name = "type")
	private String type;

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
	 * URL-URL
	 */
	@Column(name = "url")
	private String url;

	/**
	 * 图标-icon
	 */
	@Column(name = "icon")
	private String icon;

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

	public Resource() {
	}

	public Resource(String id, String parentId, String type, String code, String name, String url, String icon,
			Integer status, String remark) {
		this.id = id;
		this.parentId = parentId;
		this.type = type;
		this.code = code;
		this.name = name;
		this.url = url;
		this.icon = icon;
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

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
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

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
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
