package xyz.mryyaos.blog.service;

import org.springframework.web.bind.annotation.RequestParam;

public interface UserService {
  /**
   * 通过Id查找用户信息
   *
   * @param userId userId
   */
  String queryUserById(@RequestParam String userId);
}
