package vn.hoidanit.jobhunter.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import vn.hoidanit.jobhunter.domain.User;
import vn.hoidanit.jobhunter.repository.UserRepository;

@Service
public class UserService {
  private final UserRepository userRepository;

  public UserService(UserRepository userRepository) {
    this.userRepository = userRepository;
  }

  public User handleCreateUser(User user) {
    return this.userRepository.save(user);
  }

  public void handleDeleteUser(long id) {
    this.userRepository.deleteById(id);
  }

  // public User handleGetUser(long id) {
  // return this.userRepository.findById(id).orElse(null);
  // }

  public User fetchUserById(long id) {
    Optional<User> userOptional = this.userRepository.findById(id);
    if (userOptional.isPresent()) {
      return userOptional.get();
    }
    return null;
  }

  public List<User> fetchAllUser() {
    return this.userRepository.findAll();
  }

  public User handleUpdateUser(User reqUser) {
    // cách 1
    // Optional<User> userOptional = this.userRepository.findById(reqUser.getId());
    // if (userOptional.isPresent()) {
    // User user = userOptional.get();
    // user.setName(reqUser.getName());
    // user.setEmail(reqUser.getEmail());
    // user.setPassword(reqUser.getPassword());
    // return this.userRepository.save(user);
    // }
    // return null;
    // cách 2 tận dụng hàm fetchUserById để lấy ra user dựa vào id cần update
    User currentUser = this.fetchUserById(reqUser.getId());
    if (currentUser != null) {
      currentUser.setName(reqUser.getName());
      currentUser.setEmail(reqUser.getEmail());
      currentUser.setPassword(reqUser.getPassword());
      // update user
      return this.userRepository.save(currentUser);
    }
    return currentUser; // trả ra user mới đã được update cho client
  }
}
