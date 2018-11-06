package com.zuk.system.entity;

import java.io.Serializable;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonInclude;

@Entity
@Table(name = "system_role_resource")
@JsonInclude(JsonInclude.Include.ALWAYS)
public class RoleResource implements Serializable {

	/**
	 * 序列号
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * PK
	 */
	@EmbeddedId
	private RoleResourcePK pk;

	public RoleResource() {
	}

	public RoleResourcePK getPk() {
		return pk;
	}

	public void setPk(RoleResourcePK pk) {
		this.pk = pk;
	}

}
