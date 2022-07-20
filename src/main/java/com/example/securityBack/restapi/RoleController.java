package com.example.securityBack.restapi;

import com.example.securityBack.models.Roles;
import com.example.securityBack.restapi.api.RoleAPI;
import com.example.securityBack.services.RoleService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

@Api(value = "Role API Doc")
public class RoleController implements RoleAPI {
	@Autowired
    private RoleService roleService;

	@Override
	public ResponseEntity<?> newRole(Roles role) {
		try{
    		role = this.roleService.newRole(role);
			return new ResponseEntity<>(role, HttpStatus.CREATED);
    	}
    	catch(RuntimeException ex){
			return new ResponseEntity<>(ex.getMessage(), HttpStatus.CONFLICT);
    	}
	}

	@Override
	public ResponseEntity<?> updateRole(int id, Roles role){
    	try{
			role = this.roleService.updateRole(id, role);
			return new ResponseEntity<>(role, HttpStatus.OK);
    	}
    	catch(RuntimeException ex){
			return new ResponseEntity<>(ex.getMessage(), HttpStatus.CONFLICT);
    	}
    }

	@Override
	public ResponseEntity<?> deleteRole(int id){
		try{
			Roles role = this.roleService.deleteRole(id);
			return new ResponseEntity<>(role, HttpStatus.OK);
		}
		catch(RuntimeException ex){
			return new ResponseEntity<>(ex.getMessage(), HttpStatus.CONFLICT);
		}
	}

	@Override
	public ResponseEntity<?> getRoles(){
		List<Roles> response = this.roleService.getAllRoles();
		return new ResponseEntity<>(response, HttpStatus.OK);
    }

	@Override
	public ResponseEntity<?> getRole(int id){
		try{
			Roles response = roleService.getRoleByID(id);
			return new ResponseEntity<>(response, HttpStatus.OK);
		}
		catch(RuntimeException ex){
			return new ResponseEntity<>(ex.getMessage(), HttpStatus.CONFLICT);
		}
	}
}