package github.ittranslationclub.mybatis.start.datasource;

import github.ittranslationclub.mybatis.start.datasource.config.DataSourceProperties;
import github.ittranslationclub.mybatis.start.datasource.config.JdbcDynamicDataSourceProvider;
import github.ittranslationclub.mybatis.start.datasource.config.LastParamDsProcessor;
import com.baomidou.dynamic.datasource.creator.DefaultDataSourceCreator;
import com.baomidou.dynamic.datasource.processor.DsProcessor;
import com.baomidou.dynamic.datasource.provider.DynamicDataSourceProvider;
import org.jasypt.encryption.StringEncryptor;
import org.springframework.boot.autoconfigure.AutoConfigureBefore;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * <p>
 * 自定义动态数据源切换配置, 从数据库中获取数据源信息
 * 重点:
 * https://juejin.cn/post/7135699186070552590
 * 配置参考:
 * https://www.jianshu.com/p/ab6dbe9e7bd5
 * <p>
 * https://github.com/baomidou/dynamic-datasource-spring-boot-starter/blob/master/test/javax/src/test/java/com/baomidou/dynamic/datasource/test/javax/LoadDatasourceFromJDBCTest.java
 * <p>
 * 手动切换数据源
 * DynamicDataSourceContextHolder.push("slave");
 *
 * @author hehedada
 */
@Configuration(proxyBeanMethods = false)
@AutoConfigureBefore(DataSourceAutoConfiguration.class)
@EnableConfigurationProperties(DataSourceProperties.class)
public class DynamicDataSourceAutoConfiguration {

    @Bean
    public DynamicDataSourceProvider dynamicDataSourceProvider(StringEncryptor stringEncryptor,
                                                               DataSourceProperties properties,
                                                               DefaultDataSourceCreator defaultDataSourceCreator) {
        return new JdbcDynamicDataSourceProvider(stringEncryptor, properties);
    }

	@Bean
	public DsProcessor dsProcessor() {
		return new LastParamDsProcessor();
	}

}
