package github.ittranslationclub.mybatis.start.datasource.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

import javax.sql.DataSource;

/**
 * @author lengleng
 * @date 2019-05-14
 * <p>
 */
@Data
@ConfigurationProperties("spring.datasource")
public class DataSourceProperties {

	/**
	 * 用户名
	 */
	private String username;

	/**
	 * 密码
	 */
	private String password;

	/**
	 * jdbcurl
	 */
	private String url;

	/**
	 * 驱动类型
	 */
	private String driverClassName;

	/**
	 * 驱动类型
	 */
	private Class<? extends DataSource> type;

	/**
	 * 查询数据源的SQL
	 */
	private String queryDsSql = "select * from conf_datasource where del_flag = 0";

}
