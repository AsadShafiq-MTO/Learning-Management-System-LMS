package com.lmsportal.service;

import java.util.List;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.lmsportal.model.Register;
import com.lmsportal.model.Role;
import com.lmsportal.repository.RegisterRepo;
import com.lmsportal.repository.RoleRepo;

@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleRepo roleRepo;

    @Autowired
    private RegisterRepo registerRepo;

    //Get All Roles
	@Override
    public List<Role> findAll() {
        return roleRepo.findAll();
    }

    //Get Role By Id
	@Override
    public Role findById(int id) {
        return roleRepo.findById(id).orElse(null);
    }

    //Delete Role
	@Override
    public void delete(int id) {
        roleRepo.deleteById(id);
    }

    //Update Role
	@Override
    public void save(Role role) {
        roleRepo.save(role);
    }

    //Assign Role to User
	@Override
    public void assignUserRole(Integer userId, Integer roleId) {
        Register register = registerRepo.findById(userId).orElse(null);
        Role role = roleRepo.findById(roleId).orElse(null);
        Set<Role> userRoles = register.getRoles();
        userRoles.add(role);
        register.setRoles(userRoles);
        registerRepo.save(register);
    }

    //Unassign Role to User
	@Override
    public void unassignUserRole(Integer userId, Integer roleId) {
        Register register = registerRepo.findById(userId).orElse(null);
        register.getRoles().removeIf(x -> x.getId() == roleId);
        registerRepo.save(register);
    }

	@Override
    public Set<Role> getUserRoles(Register register) {
        return register.getRoles();
    }
     
	@Override
    public Set<Role> geUserRoles(Register register) {
        return register.getRoles();
    }
    
	@Override
    public List<Role> getUserNotRoles(Register register) {
        return roleRepo.getUserNotRoles(register.getId());
    }
}

