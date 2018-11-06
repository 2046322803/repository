package com.zuk.system.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.zuk.core.util.GenPrimaryKeyUtils;

@Entity
@Table(name = "system_user")
@JsonInclude(JsonInclude.Include.ALWAYS)
public class User implements Serializable {

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
	 * 用户名-Name
	 */
	@Column(name = "name")
	private String name;

	/**
	 * 密码-Password
	 */
	@Column(name = "password")
	private String password;

	public User() {
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}
