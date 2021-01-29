package com.project.dp.Services;

import com.project.dp.Entities.Roles;
import com.project.dp.Exceptions.Classes.NoSuchRoleException;
import com.project.dp.Exceptions.Classes.RoleAlreadyExistsException;
import com.project.dp.Repositories.RolesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RolesServiceImpl implements RolesService{

    private final RolesRepository rolesRepository;

    @Autowired
    public RolesServiceImpl(RolesRepository rolesRepository) {
        this.rolesRepository = rolesRepository;
    }

    @Override
    public Roles getRole(Long roleId) throws NoSuchRoleException {
        Optional<Roles> roleOptional = this.rolesRepository.findById(roleId);
        if (roleOptional.isEmpty()){
            throw new NoSuchRoleException();
        }
        return roleOptional.get();
    }

    @Override
    public List<Roles> getAllRoles() {
        return this.rolesRepository.findAll();
    }

    @Override
    public Roles addRole(Roles role) throws RoleAlreadyExistsException {
        if (this.rolesRepository.findByRoleName(role.getRoleName()) != null){
            throw new RoleAlreadyExistsException();
        }
        return this.rolesRepository.save(role);
    }

    @Override
    public void deleteRole(Long roleId) throws NoSuchRoleException {
        Optional<Roles> roleOptional = this.rolesRepository.findById(roleId);
        if (roleOptional.isEmpty()){
            throw new NoSuchRoleException();
        }
        else{
            Roles role = roleOptional.get();
            this.rolesRepository.delete(role);
        }
    }
}
