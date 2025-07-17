package com.example.Java_API.controller;

import com.example.Java_API.dto.response.UserResponseDTO;
import com.example.Java_API.dto.resquest.UserRequestDTO;
import com.example.Java_API.enity.User;
import com.example.Java_API.mapper.UserMapper;
import com.example.Java_API.response.SuccessResponse;
import com.example.Java_API.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping(path = "")
    public SuccessResponse<UserResponseDTO> add(@RequestBody @Valid UserRequestDTO request){
        User user = UserMapper.INSTANCE.toModel(request);
        user = this.userService.save(user);
        return SuccessResponse.of(UserMapper.INSTANCE.toResponse(user));
    }

    @GetMapping(path = "")
    public SuccessResponse<List<User>> getAll(){
        return SuccessResponse.of(this.userService.findAll());
    }

    @GetMapping(path = "/{id}")
    public SuccessResponse<UserResponseDTO> findById(@PathVariable(name="id") Long id){
        return SuccessResponse.of(UserMapper.INSTANCE.toResponse(this.userService.findById(id)));
    }

    @GetMapping(path = "/search")
    public List<User> search(@RequestParam(name = "name") String name){
        return this.userService.findByName(name);
    }

    @PutMapping(path = "")
    public User update(@RequestBody User newUser){
        return this.userService.update(newUser);
    }

    @DeleteMapping(path = "/{id}")
    public Boolean delete(@PathVariable(name = "id") final Long id){
        return this.userService.delete(id);
    }
}
