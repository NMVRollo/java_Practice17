package com.example.Practice17.controller;

import com.example.Practice17.models.Dog;
import com.example.Practice17.models.User;
import com.example.Practice17.service.DogService;
import com.example.Practice17.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class Controller {

    @Autowired
    private DogService dogService;
    @Autowired
    private UserService userService;

    @GetMapping(value = "/dog/{dogId}/user")
    public User getDogUser(@PathVariable("dogId") Long dogId){
        return dogService.getUserByDog(dogId);
    }

    @GetMapping
    public List<Dog> getAllDogs() {
        return dogService.getAllDogs();
    }

    @GetMapping("/filtered/dog")
    public List<Dog> getFilteredDogs(@RequestParam("field") String field, @RequestParam("value") String value) {
        return dogService.filteredDogList(field, value);
    }

    @GetMapping("/filtered/user")
    public List<User> getFilteredUsers(@RequestParam("field") String field, @RequestParam("value") String value) {
        return userService.filteredUserList(field, value);
    }

}
