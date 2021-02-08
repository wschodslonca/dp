package com.project.dp.UnitTests;

import com.project.dp.Dao.UsersDao;
import com.project.dp.Entities.Users;
import com.project.dp.Exceptions.Classes.NoSuchUserException;
import com.project.dp.Exceptions.Classes.UserAlreadyExistsException;
import com.project.dp.Services.UsersServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.List;
import java.util.Optional;

import static com.project.dp.DataProviders.UsersServiceUnitTestDataProvider.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.when;
public class UsersServiceUnitTest {

    @InjectMocks
    UsersServiceImpl usersService;

    @Mock
    UsersDao usersDao;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }


    @Test
    void getUserTest(){
        when(usersDao.findById(userId)).thenReturn(java.util.Optional.ofNullable(user));
        Users user1 = this.usersService.getUser(userId);
        assertNotNull(user1);
        assertEquals(1, user1.getUserId());
        assertEquals("test_login",user1.getLogin());
        assertEquals("test_password",user1.getPassword());
        assertEquals(1L,user1.getEmployeeId());
        assertEquals(1L,user1.getRoleId());
    }

    @Test
    void getUserNoSuchUserExceptionTest(){
        when(usersDao.findById(userId)).thenReturn(Optional.empty());
        assertThrows(NoSuchUserException.class, () -> usersService.getUser(userId));
    }

    @Test
    void getAllUsers(){
        when(usersDao.findAll()).thenReturn(users);
        List<Users> usersEntityList = this.usersService.getAllUsers();
        assertEquals(user,usersEntityList.get(0));
        assertEquals(user1,usersEntityList.get(1));
    }

    @Test
    void createUserTest(){
        when(usersDao.findByLogin("test_login")).thenReturn(user);
        when(usersDao.findByLogin("new_login")).thenReturn(null);

        when(usersDao.save(any(Users.class))).thenReturn(created_user);
        Users newUser = this.usersService.addUser(created_user);
        assertNotNull(newUser);
        assertEquals(4L, newUser.getUserId());
        assertEquals("new_login",newUser.getLogin());
        assertEquals("new_password",newUser.getPassword());
        assertEquals(4L,newUser.getEmployeeId());
        assertEquals(4L,newUser.getRoleId());


    }
    @Test
    void createUserAlreadyExistsExceptionTest(){
        when(usersDao.findByLogin("test_login")).thenReturn(user);
        assertThrows(UserAlreadyExistsException.class, () -> usersService.addUser(user));
    }

    @Test
    void deleteUserTest() {
        Optional<Users> optionalUser = Optional.of(user);

        when(usersDao.findById(userId)).thenReturn(optionalUser);

        usersService.deleteUser(userId);

        Mockito.verify(usersDao, times(1)).delete(optionalUser.get());
    }

    @Test
    void deleteUserNoSuchUserExceptionTest(){
        when(usersDao.findById(userId)).thenReturn(Optional.empty());
        assertThrows(NoSuchUserException.class, () -> usersService.deleteUser(userId));
    }
}
