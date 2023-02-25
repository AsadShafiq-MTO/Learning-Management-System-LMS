package com.lmsportal.service;

import java.util.List;
import java.util.Set;
import com.lmsportal.model.Register;
import com.lmsportal.model.Role;

public interface RoleService {    

    //Get All Roles
    public List<Role> findAll(); 

    //Get Role By Id
    public Role findById(int id);

    //Delete Role
    public void delete(int id);

    //Update Role
    public void save(Role role);

    //Assign Role to User
    public void assignUserRole(Integer userId, Integer roleId); 

    //Unassign Role to User
    public void unassignUserRole(Integer userId, Integer roleId); 

    public Set<Role> getUserRoles(Register register); 

    public Set<Role> geUserRoles(Register register);

    public List<Role> getUserNotRoles(Register register);


}
