package xyz.mryyaos.blog.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import xyz.mryyaos.blog.entity.User;
import xyz.mryyaos.blog.mapper.UserMapper;
import xyz.mryyaos.blog.service.UserService;
import xyz.mryyaos.blog.utils.ResponseBase;
import xyz.mryyaos.blog.utils.ResponseHelp;

/**
 * @author Mr.Yyaosen
 * @version 1.0
 * @date 2019/6/26 0:06
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

  /**
   * 通过Id查找用户信息
   *
   * @param userId userId
   * @return res
   */
  @Override
  public ResponseBase queryUserById(Long userId) {
    return ResponseHelp.prefabSimpleSucceedData(
        baseMapper.selectOne(new QueryWrapper<>(new User().setId(userId))));
  }
}
