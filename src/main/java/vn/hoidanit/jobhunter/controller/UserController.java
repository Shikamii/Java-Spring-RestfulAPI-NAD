package vn.hoidanit.jobhunter.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import vn.hoidanit.jobhunter.domain.User;
import vn.hoidanit.jobhunter.service.UserService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;

@RestController
public class UserController {

  private final UserService userService;

  public UserController(UserService userService) {
    this.userService = userService;
  }

  // @GetMapping("/users/create")
  // public String CreateNewUser() {
  // User user = new User();
  // user.setName("Eric");
  // user.setEmail("afjalfj@gmail.com");
  // user.setPassword("123456");
  // this.userService.handleCreateUser(user);
  // return "Create new user";
  // }

  // create a new user
  @PostMapping("/users")
  public ResponseEntity<User> createNewUser(@RequestBody User postManUser) {
    User ericUser = this.userService.handleCreateUser(postManUser);
    // return ericUser; // phản hồi cho phía client
    return ResponseEntity.status(HttpStatus.CREATED).body(ericUser);
  }

  // delete a user
  @DeleteMapping("/users/{id}")
  public ResponseEntity<String> deleteUser(@PathVariable("id") Long id) {
    this.userService.handleDeleteUser(id);
    // return "Delete user";
    return ResponseEntity.status(HttpStatus.OK).body("Delete user");
  }

  // fetch user by id
  @GetMapping("/users/{id}")
  public ResponseEntity<User> getUser(@PathVariable("id") Long id) {
    // return
    return ResponseEntity.status(HttpStatus.OK).body(this.userService.fetchUserById(id));
  }

  // fetch all user
  @GetMapping("/users")
  public ResponseEntity<List<User>> getAllUser() {
    // return this.userService.fetchAllUser();
    return ResponseEntity.status(HttpStatus.OK).body(this.userService.fetchAllUser());
  }

  // update a user
  @PutMapping("/users")
  public ResponseEntity<User> updateUser(@RequestBody User user) {
    User updateUser = this.userService.handleUpdateUser(user);
    // return updateUser;
    return ResponseEntity.status(HttpStatus.OK).body(updateUser);
  }
}
