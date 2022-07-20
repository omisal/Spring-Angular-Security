package com.example.securityBack.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity(name="roles")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Roles {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="roleID")
    private int id;
	@Column(name="roleName", nullable=false, unique=true)
	private String roleName;

	public Roles(int id) {
		this.id = id;
	}
}