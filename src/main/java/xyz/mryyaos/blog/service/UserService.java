package xyz.mryyaos.blog.service;

import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.web.bind.annotation.RequestParam;
import xyz.mryyaos.blog.entity.User;
import xyz.mryyaos.blog.utils.ResponseBase;

/**
 * @author Mr.Yyaosen
 * @version 1.0
 * @date 2019/6/26 0:06
 */
public interface UserService extends IService<User> {
  /**
   * 通过Id查找用户信息
   *
   * @param userId userId
   * @return res
   */
  ResponseBase queryUserById(@RequestParam Long userId);
}
