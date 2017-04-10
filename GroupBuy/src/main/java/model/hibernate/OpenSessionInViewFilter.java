package model.hibernate;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;

import org.hibernate.SessionFactory;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import model.FriendService;

@WebFilter(
		urlPatterns={"/*"}
)
public class OpenSessionInViewFilter implements Filter {
	@Override
	public void destroy() {
		
	}
	@Override
	public void doFilter(ServletRequest req, ServletResponse resp,
			FilterChain chain) throws IOException, ServletException {
		try {
			sessionFactory.getCurrentSession().beginTransaction();			//pre-processing
			chain.doFilter(req, resp);
			sessionFactory.getCurrentSession().getTransaction().commit();	//post-processing
		} catch (Exception e) {
			sessionFactory.getCurrentSession().getTransaction().rollback();
			e.printStackTrace();
			chain.doFilter(req, resp);
		}
	}
	
	private FilterConfig fileterConfig;
	private SessionFactory sessionFactory;
	@Override
	public void init(FilterConfig config) throws ServletException {
		this.fileterConfig = config;
		WebApplicationContext context = WebApplicationContextUtils.getWebApplicationContext(config.getServletContext());
		sessionFactory = (SessionFactory) context.getBean("sessionFactory");
	}
}
