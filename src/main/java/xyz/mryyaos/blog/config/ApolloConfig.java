package xyz.mryyaos.blog.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author Mr.Yyaosen
 * @version 1.0
 * @date 2019/6/26 0:06
 */
@Configuration
// @EnableApolloConfig
public class ApolloConfig {

  @Bean
  public CommonConfig commonConfig() {
    return new CommonConfig();
  }
}
