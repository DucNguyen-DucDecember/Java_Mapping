package com.example.Java_API.service.imple;

import com.example.Java_API.enity.User;
import com.example.Java_API.exception.UserNotFoundException;
import com.example.Java_API.repository.UserRepository;
import com.example.Java_API.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImple implements UserService {
    @Autowired
    private UserRepository userRepository;

    @Override
    public User save(User user){
        return this.userRepository.save(user);
    }

    @Override
    public List<User> findAll(){
        return this.userRepository.findAll();
    }

    @Override
    public User findById(Long id){
        User user = new User();
        user.getProfile().getCccd();
        return this.userRepository.findById(id).orElseThrow(() -> {
            throw new UserNotFoundException("Cannot find with id: " + id);
        });
    }

    @Override
    public List<User> findByName(String name){
//        return this.userRepository.findByName(name);
//        return this.userRepository.findByNameContaining(name);
//        return this.userRepository.findByNameContains(name);
        return this.userRepository.findNameByQuerry("%" + name + "%");
    }

    @Override
    public User update(User newUser){
//        Optional<User> optionalUser = this.userRepository.findById(newUser.getId());
//
//        if(optionalUser.isEmpty()){
//            System.err.println("Cannot find user with: " + newUser.getId());
//            return null;
//        }

        User foundUser = this.userRepository.findById(newUser.getId()).orElseThrow(() -> {
            throw new UserNotFoundException("Cannot find with id: " + newUser.getId());
        });
        foundUser.setEmail(newUser.getEmail());
        foundUser.setName(newUser.getName());

        return this.userRepository.save(foundUser);
    }

    @Override
    public Boolean delete(Long id){
        Optional<User> optionalUser = this.userRepository.findById(id);

        if(optionalUser.isEmpty()){
            System.err.println("Cannot find user with: " + id);
            return false;
        }

        User foundUser = optionalUser.get();

        this.userRepository.delete(foundUser);

//       soft delete
//       foundUser.setDeleted(true)
//       this.userRepository.save(foundUser)

        return true;
    }
}
