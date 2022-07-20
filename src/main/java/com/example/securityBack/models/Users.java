package com.example.securityBack.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Set;

@Entity(name="users")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Users{
	@Id
	@SequenceGenerator(
			name = "user_sequence",sequenceName = "user_sequence",allocationSize = 1
	)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_sequence")
	@Column(name="userID")
    private long id;
	@Column(name="userName", length=80, nullable=false, unique=true)
	private String userName;
	@Column(name="password", nullable=false)
	private String password;
	@ManyToMany
	private Set<Roles> roles;
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@Column(name="lastLogin")
	private Timestamp lastLogin;
	@Column(name="isActive", columnDefinition="boolean default true")
	private boolean isActive=true;			//falsee-inactive, true-active

	public Users(long id) {
		this.id = id;
	}
}