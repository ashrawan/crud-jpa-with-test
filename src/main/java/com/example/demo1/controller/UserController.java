package com.example.demo1.controller;

import com.example.demo1.model.User;
import com.example.demo1.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
public class UserController {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserController.class);

    private final UserService userService;

    public UserController(final UserService userService) {
        this.userService = userService;
    }

    /**
     * Returns list of all users.
     * Http Get Method must be specified.
     * Url must be set on - server-url/base-path/users
     * The data is returned in JSON format
     *
     * @return List of users in JSON format
     */
    @GetMapping("/users")
    public ResponseEntity<List<User>> retrieveAllUsers() {

        LOGGER.info("Returning all the User´s");
        List<User> userList = userService.getAllUsers();
        if(userList==null || userList.size()==0 ){
            return new ResponseEntity(userList,HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<List<User>>(userList, HttpStatus.OK);
    }


    /**
     * Returns single user.
     * Http Get Method must be specified.
     * Url must be set on - server-url/base-path/users/{id}
     * The data is returned in JSON format
     *
     * @param id id of user that we want to retrieve
     * @return List of users in JSON format
     */
    @GetMapping("/users/{id}")
    public ResponseEntity<User> retrieveUser(@PathVariable long id) {

        User userRe = userService.getUserById(id);
        if(userRe == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<User>(userRe, HttpStatus.OK);
    }


    /**
     * Returns Created user.
     * Http Post Method must be specified.
     * Url must be set on - server-url/base-path/users
     * The data is returned in JSON format
     *
     * @param user json data specifying user to add
     * @return Created user in JSON format
     */
    @PostMapping("/users")
    public ResponseEntity<User> createUser(@RequestBody @Valid User user) {

        User userRe = userService.createUser(user);
        if(userRe == null ){
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
        return new ResponseEntity<User>(userRe, HttpStatus.OK);

    }

    /**
     * Returns Updated user.
     * Http Put Method must be specified.
     * Url must be set on - server-url/base-path/users/{id}
     * The data is returned in JSON format
     *
     * @param user json data specifying user to update
     * @return Created user in JSON format
     */
    @PutMapping("/users/{id}")
    public ResponseEntity<Boolean> updateUser(@RequestBody @Valid User user, @PathVariable long id) {

        boolean b=userService.updateUser(user, id);
        if(b==false){
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
        return new ResponseEntity<>(b, HttpStatus.OK);

    }

    /**
     * Returns Deleted user.
     * Http Delete Method must be specified.
     * Url must be set on - server-url/base-path/users/{id}
     * The data is returned in JSON format
     *
     * @param id id of user that we want to delete
     * @return Deleted user in JSON format
     */
    @DeleteMapping("/users/{id}")
    public ResponseEntity<Boolean> deleteUser(@PathVariable long id) {

        boolean b=userService.deleteUser(id);
        if(b==false){
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
        return new ResponseEntity<>(b, HttpStatus.OK);
    }
}