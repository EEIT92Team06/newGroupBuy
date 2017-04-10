package model.hibernate;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebInitParam;

import org.hibernate.SessionFactory;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

@WebFilter(urlPatterns={"/*"},
		   initParams={@WebInitParam(name="sessionFactoryBeanName",value="sessionFactory")})
public class OpenSessionInViewFilter implements Filter {
	private SessionFactory sessionFactory;
	private FilterConfig fileterConfig;
	@Override
	public void init(FilterConfig config) throws ServletException {
		this.fileterConfig = config;
		String sessionFactoryBeanName = config.getInitParameter("sessionFactoryBeanName");
		ServletContext application = config.getServletContext();
		WebApplicationContext context = WebApplicationContextUtils.getWebApplicationContext(application);
		sessionFactory = (SessionFactory) context.getBean(sessionFactoryBeanName);
	}
	
	@Override
	public void destroy() {
		
	}
	@Override
	public void doFilter(ServletRequest req, ServletResponse resp,
			FilterChain chain) throws IOException, ServletException {
		try {
			System.out.println("here");
			//pre-processing
			sessionFactory.getCurrentSession().beginTransaction();			//pre-processing
			System.out.println("here123");
			chain.doFilter(req, resp);
			//post-processing
			sessionFactory.getCurrentSession().getTransaction().commit();	//post-processing
		} catch (Exception e) {
			sessionFactory.getCurrentSession().getTransaction().rollback();
			e.printStackTrace();
			chain.doFilter(req, resp);
		}
	}
	

}
