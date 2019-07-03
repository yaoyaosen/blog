package xyz.mryyaos.blog.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import xyz.mryyaos.blog.entity.User;

/**
 * @author Mr.Yyaosen
 * @version 1.0
 * @date 2019/6/26 0:06
 */
public interface UserMapper extends BaseMapper<User> {
  /**
   * Mapper 接口
   *
   * @author 尧尧森
   * @since 2019-06-18
   */
  interface AccountAuthorityMapper extends BaseMapper<User> {}
}
