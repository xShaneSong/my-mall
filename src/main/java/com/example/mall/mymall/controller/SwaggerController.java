package com.example.mall.mymall.controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.List;

import com.example.mall.mymall.entity.User;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.v3.oas.annotations.parameters.RequestBody;

@RestController
public class SwaggerController {
    
    static Map<Integer, User> usersMap = Collections.synchronizedMap(
        new HashMap<Integer, User>());

    static {
        User user = new User();
        user.setId(1);
        user.setName("mymall");
        user.setPassword("111111");
        usersMap.put(1, user);
        
        User user2 = new User();
        user2.setId(2);
        user2.setName("mymall2");
        user.setPassword("222222");
        usersMap.put(2, user2);
    }

    @ApiOperation(value = "Get User List", notes = "")
    @GetMapping("/users")
    public List<User> getUserList() {
        List<User> users = new ArrayList<User>(usersMap.values());
        return users;
    }

    @ApiOperation(value = "Add user", notes = "")
    @ApiImplicitParam(name = "user", value = "user entity", required = true, dataType = "User")
    @PostMapping("/users")
    public String postUser(@RequestBody User user) {
        usersMap.put(user.getId(), user);
        return "Add complete.";
    }

    @ApiOperation(value = "Get user detail info", notes = "")
    @ApiImplicitParam(name = "id", value = "user id", required = true, dataType = "int")
    @GetMapping("/users/id")
    public User getUser(@PathVariable Integer id) {
        return usersMap.get(id);
    }
    
    @ApiOperation(value = "Update user detail info", notes = "") 
    @ApiImplicitParams({
        @ApiImplicitParam(name = "id", value = "user id", required = true, dataType = "int"),
        @ApiImplicitParam(name = "user", value = "user entity", required = true, dataType = "User")
    })
    @PutMapping("/users/{id}")
    public String putUser(@PathVariable Integer id, @RequestBody User user) {
        User tempUser = usersMap.get(id);
        tempUser.setName(user.getName());
        tempUser.setPassword(user.getPassword());
        usersMap.put(id, tempUser);
        return "Update complete.";
    }

    @ApiOperation(value = "delete user", notes = "")
    @ApiImplicitParam(name = "id", value = "user id", required = true, dataType = "int")
    @DeleteMapping("/users/{id}")
    public String deleteUser(@PathVariable Integer id) {
        usersMap.remove(id);
        return "Delete complete.";
    }
}
