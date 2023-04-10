package board;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.apache.ibatis.session.SqlSessionFactory;
import javax.sql.DataSource;
import org.mybatis.spring.SqlSessionFactoryBean;

@SpringBootApplication
public class UserboardApplication {

	public static void main(String[] args) {
		SpringApplication.run(UserboardApplication.class, args);
	}
	//SqlSessionFactory 설정 
		@Bean
		public SqlSessionFactory sqlSessionFactory(DataSource dataSource) throws Exception{
			SqlSessionFactoryBean sessionFactory=new SqlSessionFactoryBean();
			sessionFactory.setDataSource(dataSource);
			return  sessionFactory.getObject();
		}
}
