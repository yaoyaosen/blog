package xyz.mryyaos.blog.config;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;

/**
 * @author Mr.Yyaosen
 * @version 1.0
 * @date 2019/6/26 0:11
 */
@Data
public class CommonConfig {

  @Value("${url:www.baidu.com}")
  private String url;
}
