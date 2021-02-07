package com.project.dp.Services;

import com.project.dp.Entities.Roles;
import com.project.dp.Exceptions.Classes.NoSuchRoleException;
import com.project.dp.Exceptions.Classes.RoleAlreadyExistsException;
import com.project.dp.Dao.RolesDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RolesServiceImpl implements RolesService{

    private final RolesDao rolesDao;

    @Autowired
    public RolesServiceImpl(RolesDao rolesDao) {
        this.rolesDao = rolesDao;
    }

    @Override
    public Roles getRole(Long roleId) throws NoSuchRoleException {
        Optional<Roles> roleOptional = this.rolesDao.findById(roleId);
        if (roleOptional.isEmpty()){
            throw new NoSuchRoleException();
        }
        return roleOptional.get();
    }

    @Override
    public List<Roles> getAllRoles() {
        return this.rolesDao.findAll();
    }

    @Override
    public Roles addRole(Roles role) throws RoleAlreadyExistsException {
        if (this.rolesDao.findByRoleName(role.getRoleName()) != null){
            throw new RoleAlreadyExistsException();
        }
        return this.rolesDao.save(role);
    }

    @Override
    public void deleteRole(Long roleId) throws NoSuchRoleException {
        Optional<Roles> roleOptional = this.rolesDao.findById(roleId);
        if (roleOptional.isEmpty()){
            throw new NoSuchRoleException();
        }
        else{
            Roles role = roleOptional.get();
            this.rolesDao.delete(role);
        }
    }

    @Override
    public List<Roles> findAllByParentId(Long parentId) {
        return this.rolesDao.findAllByParentId(parentId);
    }
}
