package com.bushuev.med.hospital_admin.service;

import com.bushuev.med.hospital_admin.entity.User;
import com.bushuev.med.hospital_admin.repos.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    private final UserRepository userRepository;
    @Autowired
    public UserService(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    public List<User> findAll(){
        return userRepository.findAll();
    }

    public User findUserById(Long id){
        return userRepository.findById(id).get();
    }

    public boolean addUser(User user) {
        User userFromDB = userRepository.findByName(user.getName());

        if (userFromDB != null) {
            userFromDB.setName(user.getName());
            userFromDB.setSurname(user.getSurname());

            userRepository.save(userFromDB);

            return false;
        }

        userRepository.save(user);

        return true;
    }

    public void saveUser(User user){
        userRepository.save(user);
    }

    public void deleteUserById(Long userId) {
        userRepository.deleteById(userId);
    }
}
