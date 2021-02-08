package com.project.dp.UnitTests;

import com.project.dp.Dao.RolesDao;
import com.project.dp.Entities.Roles;
import com.project.dp.Exceptions.Classes.NoSuchRoleException;
import com.project.dp.Exceptions.Classes.RoleAlreadyExistsException;
import com.project.dp.Services.RolesServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.List;
import java.util.Optional;

import static com.project.dp.DataProviders.RolesServiceUnitTestDataProvider.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.when;

public class RolesServiceUnitTest {

    @InjectMocks
    RolesServiceImpl rolesService;

    @Mock
    RolesDao rolesDao;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void getRoleTest(){
        when(rolesDao.findById(roleId)).thenReturn(java.util.Optional.ofNullable(role));
        Roles role1 = this.rolesService.getRole(roleId);
        assertNotNull(role1);
        assertEquals(1L, role1.getRoleId());
        assertEquals("test_role",role1.getRoleName());
        assertEquals(0L,role1.getParentId());
    }

    @Test
    void getRoleNoSuchRoleExceptionTest(){
        when(rolesDao.findById(roleId)).thenReturn(Optional.empty());
        assertThrows(NoSuchRoleException.class, () -> rolesService.getRole(roleId));
    }

    @Test
    void getAllRoles(){
        when(rolesDao.findAll()).thenReturn(roles);
        List<Roles> rolesEntityList = this.rolesService.getAllRoles();
        assertEquals(role,rolesEntityList.get(0));
        assertEquals(role1,rolesEntityList.get(1));
    }

    @Test
    void createRoleTest(){
        when(rolesDao.findByRoleName("test_role")).thenReturn(role);
        when(rolesDao.findByRoleName("new_role")).thenReturn(null);

        when(rolesDao.save(any(Roles.class))).thenReturn(created_role);
        Roles newRole = this.rolesService.addRole(created_role);
        assertNotNull(newRole);
        assertEquals(4L, newRole.getRoleId());
        assertEquals("new_role",newRole.getRoleName());
        assertEquals(3L,newRole.getParentId());


    }
    @Test
    void createRoleAlreadyExistsExceptionTest(){
        when(rolesDao.findByRoleName("test_role")).thenReturn(role);
        assertThrows(RoleAlreadyExistsException.class, () -> rolesService.addRole(role));
    }

    @Test
    void deleteRoleTest() {
        Optional<Roles> optionalRole = Optional.of(role);

        when(rolesDao.findById(roleId)).thenReturn(optionalRole);

        rolesService.deleteRole(roleId);

        Mockito.verify(rolesDao, times(1)).delete(optionalRole.get());
    }

    @Test
    void deleteRoleNoSuchRoleExceptionTest(){
        when(rolesDao.findById(roleId)).thenReturn(Optional.empty());
        assertThrows(NoSuchRoleException.class, () -> rolesService.deleteRole(roleId));
    }
}
