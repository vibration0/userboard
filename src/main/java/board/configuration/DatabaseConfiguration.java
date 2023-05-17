package board.configuration;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@PropertySource("classpath:application.properties")
public class DatabaseConfiguration {
	
	@Autowired
	private ApplicationContext applicationContext;
	//SqlSessionFactory 설정 
			@Bean
			public SqlSessionFactory sqlSessionFactory(DataSource dataSource) throws Exception{
				
				SqlSessionFactoryBean sqlsessionFactoryBean=new SqlSessionFactoryBean();
				sqlsessionFactoryBean.setDataSource(dataSource);
				sqlsessionFactoryBean.setMapperLocations
				(applicationContext.getResources("classpath:/mapper/**/sql-*.xml"));
				return sqlsessionFactoryBean.getObject();
		}
			
			 @Bean
			 public SqlSessionTemplate sqlSessionTemplate(SqlSessionFactory sqlSessionFactory) {
			 
			 return new SqlSessionTemplate(sqlSessionFactory);
		}
			 
			 @Bean
			 @ConfigurationProperties(prefix="mybatis.configuration")
			 public org.apache.ibatis.session.Configuration mybatisConfig() {
			 return new org.apache.ibatis.session.Configuration();
		}
			 
			 @Bean
			 public BCryptPasswordEncoder passwordEncoder() {
			  return new BCryptPasswordEncoder();
		}
			
}
