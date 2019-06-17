package xyz.mryyaos.blog.controller.blogs;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 *  @author 尧尧森
 *  @date 2019-6-18 1:00:00
 */
@RestController
@RequestMapping("/api/user")
public class UserController {

  private UserService userService;

  /**
   * 获取用户信息
   * @param userId 用户编号
   */
  @GetMapping(value = "check")
  public void getUserById(@RequestParam String userId){
    userService.queryUserById(userId);
  }
}