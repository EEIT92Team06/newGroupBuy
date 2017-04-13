package spring.setters;

import java.io.IOException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import org.hibernate.SessionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;

import wish.model.WishInterestService;
import wish.model.WishMsgService;
import wish.model.WishPictureService;
import wish.model.WishPoolService;
import wish.model.dao.WishInterestDAOHibernate;
import wish.model.dao.WishMsgDAOHibernate;
import wish.model.dao.WishPictureDAOHibernate;
import wish.model.dao.WishPoolDAOHibernate;

@Configuration
public class WishJavaConfiguration {
	
	@Bean
	public DataSource dataSource(){
		try {
			Context ctx = new InitialContext();
			return (DataSource) ctx.lookup("java:comp/env/jdbc/groupbuy");
		} catch(NamingException e) {
			e.printStackTrace();
		}
		return null;  
	}
	
	@Bean
	public SessionFactory sessionFactory() throws IOException{
		LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();
		sessionFactory.setDataSource(dataSource());
		Resource resource = new ClassPathResource("hibernate.cfg.xml");
		sessionFactory.setConfigLocation(resource);
		sessionFactory.afterPropertiesSet();
		return sessionFactory.getObject();
	}
	
	@Bean
	public WishPoolDAOHibernate wishPoolDAOHibernate() throws IOException{
		WishPoolDAOHibernate wishPoolDAOJdbc = new WishPoolDAOHibernate(sessionFactory());
		return wishPoolDAOJdbc;
	}
	

	@Bean
	public WishPictureDAOHibernate wishPictureDAOHibernate() throws IOException{
		WishPictureDAOHibernate wishPictureDAOJdbc = new WishPictureDAOHibernate(sessionFactory());
	    return wishPictureDAOJdbc;
	}
	
	@Bean
	public WishInterestDAOHibernate wishInterestDAOHibernate() throws IOException{
		WishInterestDAOHibernate wishInterestDAOHibernate = new WishInterestDAOHibernate(sessionFactory());
		return wishInterestDAOHibernate;
	}
	
	@Bean
	public WishMsgDAOHibernate wishMsgDAOHibernate() throws IOException{
		WishMsgDAOHibernate wishMsgDAOHibernate= new WishMsgDAOHibernate(sessionFactory());
		return wishMsgDAOHibernate;
	}
	
	@Bean
	public WishPoolService wishPoolService() throws IOException{
		WishPoolService wishService = new WishPoolService(wishPoolDAOHibernate());
		return wishService;
	}
	
	@Bean
	public WishPictureService wishPictureService() throws IOException{
		WishPictureService wishPictureService = new WishPictureService(wishPictureDAOHibernate());
		return wishPictureService;
	}
	
	@Bean
	public WishInterestService wishInterestService() throws IOException{
		WishInterestService wishInterestService = new WishInterestService(wishInterestDAOHibernate());
		return wishInterestService;
	}
	
	@Bean
	public WishMsgService wishMsgService() throws IOException{
		WishMsgService wishMsgService = new WishMsgService(wishMsgDAOHibernate());
		return wishMsgService;
	}
}
