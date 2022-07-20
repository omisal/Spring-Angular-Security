package com.example.securityBack.services;

import com.example.securityBack.models.Roles;
import com.example.securityBack.repositories.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleService {
	@Autowired
	private RoleRepository roleRepo;

	public List<Roles> getAllRoles() {
		return this.roleRepo.findAll();
	}

	public Roles getRoleByID(int id) {
		return roleRepo.findById(id)
				.orElseThrow(() -> new RuntimeException("This Role does not exist."));
	}

	public Roles newRole(Roles role) {
		if(roleRepo.findByRoleName(role.getRoleName()).isPresent()){
			throw new RuntimeException("Role with name ("+role.getRoleName()+") already exist.");
		}
		return this.roleRepo.save(role);
	}

	public Roles updateRole(int id, Roles data) {
		Roles role = roleRepo.findById(id)
				.orElseThrow(() -> new RuntimeException("This Role does not exist."));
		role.setRoleName(data.getRoleName());
		return this.roleRepo.save(role);
	}

	public Roles deleteRole(int id) {
		Roles role = roleRepo.findById(id)
				.orElseThrow(() -> new RuntimeException("This Role does not exist."));
		return this.roleRepo.save(role);
	}
}