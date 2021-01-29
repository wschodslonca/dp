package com.project.dp.Services;

import com.project.dp.Entities.Users;
import com.project.dp.Exceptions.Classes.NoSuchUserException;
import com.project.dp.Exceptions.Classes.UserAlreadyExistsException;
import com.project.dp.Repositories.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UsersServiceImpl implements UsersService{

    private final UsersRepository usersRepository;

    @Autowired
    public UsersServiceImpl(UsersRepository usersRepository) {
        this.usersRepository = usersRepository;
    }

    @Override
    public Users getUser(Long userId) throws NoSuchUserException{
        Optional<Users> userOptional = this.usersRepository.findById(userId);
        if (userOptional.isEmpty()){
            throw new NoSuchUserException();
        }
        return userOptional.get();
    }

    @Override
    public List<Users> getAllUsers() {
        return this.usersRepository.findAll();
    }

    @Override
    public Users addUser(Users user) throws UserAlreadyExistsException {
        if (this.usersRepository.findByLogin(user.getLogin()) != null){
            throw new UserAlreadyExistsException();
        }
        return this.usersRepository.save(user);
    }

    @Override
    public void deleteUser(Long userId) throws NoSuchUserException {
        Optional<Users> userOptional = this.usersRepository.findById(userId);
        if (userOptional.isEmpty()){
            throw new NoSuchUserException();
        }
        else{
            Users user = userOptional.get();
            this.usersRepository.delete(user);
        }
    }
}
