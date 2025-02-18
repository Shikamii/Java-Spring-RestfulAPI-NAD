package vn.hoidanit.jobhunter.controller;

import java.util.List;

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

  // @GetMapping("/user/create")
  @PostMapping("/user")
  public User createNewUser(@RequestBody User postManUser) {
    User ericUser = this.userService.handleCreateUser(postManUser);
    return ericUser;
  }

  @DeleteMapping("/user/{id}")
  public String deleteUser(@PathVariable("id") Long id) {
    this.userService.handleDeleteUser(id);
    return "Delete user";
  }

  // fetch user by id
  @GetMapping("/user/{id}")
  public User getUser(@PathVariable("id") Long id) {
    return this.userService.fetchUserById(id);
  }

  // fetch all user
  @GetMapping("/user")
  public List<User> getAllUser() {
    return this.userService.fetchAllUser();
  }

  // update a user
  @PutMapping("/user")
  public User updateUser(@RequestBody User user) {
    User updateUser = this.userService.handleUpdateUser(user);
    return updateUser;
  }
}
