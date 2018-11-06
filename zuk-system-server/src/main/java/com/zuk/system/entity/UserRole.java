package com.zuk.system.entity;

import java.io.Serializable;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonInclude;

@Entity
@Table(name = "system_user_role")
@JsonInclude(JsonInclude.Include.ALWAYS)
public class UserRole implements Serializable {

	/**
	 * 序列号
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * PK
	 */
	@EmbeddedId
	private UserRolePK pk;

	public UserRole() {
	}

	public UserRolePK getPk() {
		return pk;
	}

	public void setPk(UserRolePK pk) {
		this.pk = pk;
	}

}
