package truong.capgemini.usermanagement.config;

import java.io.IOException;
import java.util.Properties;

import javax.sql.DataSource;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import truong.capgemini.usermanagement.dao.UserDao;
import truong.capgemini.usermanagement.dao.UserDaoImpl;

@Configuration
@EnableWebMvc
@ComponentScan("truong.capgemini.usermanagement")
@EnableTransactionManagement
@PropertySource("classpath:hibernate-config.properties")
public class AppConfig {

	@Autowired
	private Environment environment;

	@Bean
	public ResourceBundleMessageSource bundleMessageSource() {
		ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();
		return messageSource;
	}

	/**
	 * Config view for spring
	 * @return
	 */
	@Bean(name = "viewResolver")
	public InternalResourceViewResolver getViewResolver() {
		InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
		viewResolver.setPrefix("WEB-INF/pages/");
		viewResolver.setSuffix(".jsp");
		return viewResolver;
	}

	/**
	 * Make the way to connect to mysql
	 * @return
	 */
	@Bean(name = "dataSource")
	public DataSource getDataSource() {
		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		dataSource.setDriverClassName(environment.getProperty("ds.database-driver"));
		dataSource.setUrl(environment.getProperty("ds.url"));
		dataSource.setUsername(environment.getProperty("ds.username"));
		dataSource.setPassword(environment.getProperty("ds.password"));
		return dataSource;
	}
	
	/**
	 * Config hibernate session to mysql
	 * @param dataSource
	 * @return
	 * @throws IOException
	 */
	@Autowired
	@Bean(name = "sessionFactory")
	public SessionFactory getSessionFactory(DataSource dataSource) throws IOException {
		Properties properties = new Properties();
		properties.put("hibernate-dialect", environment.getProperty("hibernate.dialect"));
		properties.put("hibernate.show_sql", environment.getProperty("hibernate.show_sql"));
		properties.put("current_session_context_class", environment.getProperty("current_session_context_class"));

		LocalSessionFactoryBean bean = new LocalSessionFactoryBean();
		bean.setPackagesToScan(new String[] { "truong.capgemini.usermanagement.model" });
		bean.setDataSource(dataSource);
		bean.setHibernateProperties(properties);
		bean.afterPropertiesSet();

		SessionFactory factory = bean.getObject();

		return factory;
	}
	
	/**
	 * Make alive the transaction in hibernate
	 * @param factory
	 * @return
	 */
	
	@Autowired
	@Bean(name = "transactionManager")
	public HibernateTransactionManager getTransactionManager(SessionFactory factory){
		HibernateTransactionManager manager = new HibernateTransactionManager(factory);
		return manager;
	}
	
	@Bean(name = "userDao")
	public UserDao getUserDao(){
		return new UserDaoImpl();
	}
	
}
