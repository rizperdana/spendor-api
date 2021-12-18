package spendor.core.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import spendor.core.model.User;
import spendor.core.repository.UserRepository;

@CrossOrigin(origins = "http://localhost:8081")
@RestController
@RequestMapping("/api")
public class UserController {
  @Autowired
  UserRepository userRepository;

  @GetMapping("/users")
  public ResponseEntity<List<User>> getAllUsers(@RequestParam(required = false) String username) {
    try {
      List<User> users = new ArrayList<User>();

      if (username == null)
        userRepository.findAll().forEach(users::add);
      else
        userRepository.findByUsernameContaining(username).forEach(users::add);

      if (users.isEmpty()) {
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
      }

      return new ResponseEntity<>(users, HttpStatus.OK);
    } catch (Exception e) {
      return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

  @GetMapping("/users/{id}")
  public ResponseEntity<User> getUserById(@PathVariable("id") String id) {
    Optional<User> userData = userRepository.findById(id);

    if (userData.isPresent()) {
      return new ResponseEntity<>(userData.get(), HttpStatus.OK);
    } else {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
  }

  @GetMapping("/users/active")
  public ResponseEntity<List<User>> findByActive() {
    try {
      List<User> users = userRepository.findByActive(true);
      
      if (users.isEmpty()) {
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
      }
      return new ResponseEntity<>(users, HttpStatus.OK); 
    } catch (Exception e ) {
      return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

  @PostMapping("/users")
  public ResponseEntity<User> createUser(@RequestBody User user) {
    try {
      User _user = userRepository.save(new User(
        user.getUsername(),
        user.getFullname(),
        user.getEmail(),
        user.getPassword(),
        user.getPhone(),
        false
      ));
      return new ResponseEntity<>(_user, HttpStatus.CREATED);
    } catch (Exception e) {
      return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

  @PutMapping("/users/{id}")
  public ResponseEntity<User> updateUser(@PathVariable("id") String id, @RequestBody User user) {
    Optional<User> userData = userRepository.findById(id);

    if (userData.isPresent()) {
      User _user = userData.get();
      _user.setUsername(user.getUsername());
      _user.setFullname(user.getFullname());
      _user.setEmail(user.getEmail());
      _user.setPassword(user.getPassword());
      _user.setPhone(user.getPhone());
      _user.setActive(user.getActive());
      return new ResponseEntity<>(userRepository.save(_user), HttpStatus.OK);
    } else {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
  }

  @DeleteMapping("/users/{id}")
  public ResponseEntity<HttpStatus> deleteUser(@PathVariable("id") String id) {
    try {
      userRepository.deleteById(id);
      return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    } catch (Exception e) {
      return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }
}
