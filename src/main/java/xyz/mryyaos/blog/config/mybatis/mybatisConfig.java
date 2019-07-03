package xyz.mryyaos.blog.config.mybatis;

import com.alibaba.fastjson.serializer.SerializeConfig;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.serializer.ToStringSerializer;
import com.alibaba.fastjson.support.config.FastJsonConfig;
import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter;
import com.baomidou.mybatisplus.annotation.FieldStrategy;
import com.baomidou.mybatisplus.core.MybatisConfiguration;
import com.baomidou.mybatisplus.core.config.GlobalConfig;
import com.baomidou.mybatisplus.core.injector.ISqlInjector;
import com.baomidou.mybatisplus.core.toolkit.GlobalConfigUtils;
import com.baomidou.mybatisplus.extension.MybatisMapWrapperFactory;
import com.baomidou.mybatisplus.extension.injector.LogicSqlInjector;
import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import com.baomidou.mybatisplus.extension.plugins.PerformanceInterceptor;
import com.baomidou.mybatisplus.extension.plugins.SqlExplainInterceptor;
import com.baomidou.mybatisplus.extension.spring.MybatisSqlSessionFactoryBean;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import javax.sql.DataSource;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.type.JdbcType;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.autoconfigure.http.HttpMessageConverters;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
/**
 * mbp config
 *
 * @author jsen
 * @since 2018-04-08
 */
@Configuration
@MapperScan("xyz.mryyaos.blog.mapper")
public class mybatisConfig {
  @Bean
  @ConfigurationProperties("spring.datasource")
  public DataSource dataSource() {
    return DataSourceBuilder.create().type(com.alibaba.druid.pool.DruidDataSource.class).build();
  }

  @Bean("sqlSessionFactory")
  public SqlSessionFactory sqlSessionFactory() throws Exception {

    MybatisSqlSessionFactoryBean sqlSessionFactory = new MybatisSqlSessionFactoryBean();
    sqlSessionFactory.setPlugins(new Interceptor[] {paginationInterceptor()});

    sqlSessionFactory.setMapperLocations(
        new PathMatchingResourcePatternResolver().getResources("classpath:mapper/*Mapper.xml"));
    sqlSessionFactory.setTypeAliasesPackage("xyz.mryyaos.blog.entity");

    sqlSessionFactory.setDataSource(dataSource());
    MybatisConfiguration configuration = new MybatisConfiguration();
    configuration.setMapUnderscoreToCamelCase(true);

    configuration.setJdbcTypeForNull(JdbcType.NULL);
    // 注册Map 下划线转驼峰
    configuration.setObjectWrapperFactory(new MybatisMapWrapperFactory());

    sqlSessionFactory.setConfiguration(configuration);

    GlobalConfig globalConfig = GlobalConfigUtils.defaults();
    globalConfig.setSqlParserCache(true);
    globalConfig.getDbConfig().setColumnLike(false);
    globalConfig.getDbConfig().setLogicDeleteValue("0");
    globalConfig.getDbConfig().setLogicNotDeleteValue("1");
    /*
     * 当更新数据库的时候设置字段为null可以更新
     */
    globalConfig.getDbConfig().setFieldStrategy(FieldStrategy.IGNORED);
    sqlSessionFactory.setGlobalConfig(globalConfig);

    return sqlSessionFactory.getObject();
  }

  /** 分页插件，自动识别数据库类型 多租户，请参考官网【插件扩展】 */
  @Bean
  public PaginationInterceptor paginationInterceptor() {
    PaginationInterceptor page = new PaginationInterceptor();
    page.setDialectType("mysql");
    return page;
  }

  @Bean
  public ISqlInjector sqlInjector() {
    return new LogicSqlInjector();
  }

  @Bean
  @Profile({"dev"})
  public PerformanceInterceptor performanceInterceptor() {
    return new PerformanceInterceptor();
  }

  @Bean
  public SqlExplainInterceptor sqlExplainInterceptor() {
    return new SqlExplainInterceptor();
  }

  @Bean
  public HttpMessageConverters fastJsonHttpMessageConverters() {

    FastJsonHttpMessageConverter fastConverter = new FastJsonHttpMessageConverter();
    FastJsonConfig fastJsonConfig = new FastJsonConfig();
    fastJsonConfig.setSerializerFeatures(
        SerializerFeature.DisableCircularReferenceDetect,
        SerializerFeature.WriteMapNullValue,
        SerializerFeature.WriteNullNumberAsZero,
        SerializerFeature.WriteNullStringAsEmpty,
        SerializerFeature.WriteNullListAsEmpty,
        SerializerFeature.WriteNullBooleanAsFalse,
        SerializerFeature.WriteNonStringKeyAsString,
        SerializerFeature.BrowserCompatible);

    // 解决Long转json精度丢失的问题
    SerializeConfig serializeConfig = SerializeConfig.globalInstance;
    serializeConfig.put(BigInteger.class, ToStringSerializer.instance);
    serializeConfig.put(Long.class, ToStringSerializer.instance);
    serializeConfig.put(Long.TYPE, ToStringSerializer.instance);
    fastJsonConfig.setSerializeConfig(serializeConfig);

    // 处理中文乱码问题
    List<MediaType> fastMediaTypes = new ArrayList<>();
    fastMediaTypes.add(MediaType.APPLICATION_JSON_UTF8);

    fastConverter.setSupportedMediaTypes(fastMediaTypes);
    fastConverter.setFastJsonConfig(fastJsonConfig);

    return new HttpMessageConverters((HttpMessageConverter<?>) fastConverter);
  }
}
