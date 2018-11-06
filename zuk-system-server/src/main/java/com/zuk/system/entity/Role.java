package com.zuk.system.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.zuk.core.util.GenPrimaryKeyUtils;

@Entity
@Table(name = "system_role")
@JsonInclude(JsonInclude.Include.ALWAYS)
public class Role implements Serializable {

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
	 * 角色编码-Role Code
	 */
	@Column(name = "code")
	private String code;

	/**
	 * 角色名称-Role Name
	 */
	@Column(name = "name")
	private String name;

	/**
	 * 描述-Description
	 */
	@Column(name = "description")
	private String description;

	public Role() {
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
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

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

}
