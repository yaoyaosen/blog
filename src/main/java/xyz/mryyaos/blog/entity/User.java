package xyz.mryyaos.blog.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import java.time.LocalDateTime;

/**
 * @author 尧尧森
 * @since 2019-06-18
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class User implements Serializable {

  private static final long serialVersionUID = 1L;
  /**
   * 主键Id
   */
  @TableId(value = "id", type = IdType.AUTO)
  private Long id;
  /**
   * 用户名
   */
  private String userName;
  /**
   * 性别
   */
  private Integer gender;
  /**
   * 电话
   */
  private String phone;
  /**
   * 微信
   */
  private String weixin;
  /**
   * QQ
   */
  private String qq;
  /**
   * email
   */
  private String email;
  /**
   * 创建时间
   */
  private LocalDateTime createDate;
  /**
   * 修改时间
   */
  private LocalDateTime updateDate;
  /**
   * 状态
   */
  private String status;
  /**
   * 权限级别
   */
  private String power;

}