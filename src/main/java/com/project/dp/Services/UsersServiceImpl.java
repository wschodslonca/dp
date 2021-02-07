package com.project.dp.Services;

import com.project.dp.Entities.Users;
import com.project.dp.Exceptions.Classes.NoSuchUserException;
import com.project.dp.Exceptions.Classes.UserAlreadyExistsException;
import com.project.dp.Dao.UsersDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UsersServiceImpl implements UsersService{

    private final UsersDao usersDao;

    @Autowired
    public UsersServiceImpl(UsersDao usersDao) {
        this.usersDao = usersDao;
    }

    @Override
    public Users getUser(Long userId) throws NoSuchUserException{
        Optional<Users> userOptional = this.usersDao.findById(userId);
        if (userOptional.isEmpty()){
            throw new NoSuchUserException();
        }
        return userOptional.get();
    }

    @Override
    public List<Users> getAllUsers() {
        return this.usersDao.findAll();
    }

    @Override
    public Users addUser(Users user) throws UserAlreadyExistsException {
        if (this.usersDao.findByLogin(user.getLogin()) != null){
            throw new UserAlreadyExistsException();
        }
        return this.usersDao.save(user);
    }

    @Override
    public void deleteUser(Long userId) throws NoSuchUserException {
        Optional<Users> userOptional = this.usersDao.findById(userId);
        if (userOptional.isEmpty()){
            throw new NoSuchUserException();
        }
        else{
            Users user = userOptional.get();
            this.usersDao.delete(user);
        }
    }

    @Override
    public Users findByLogin(String login){
        return this.usersDao.findByLogin(login);
    }

    @Override
    public List<Users> findAllByRoleId(Long roleId) {
        return this.usersDao.findAllByRoleId(roleId);
    }

}
