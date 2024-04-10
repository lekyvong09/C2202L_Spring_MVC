package com.ray.crm.config;

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

//<servlet>
//	<servlet-name>dispatcher</servlet-name>
//	<servlet-class>org.springframework.web.servlet.DispatcherServlet</servlet-class>
//	<init-param>
//		<param-name>contextConfigLocation</param-name>
//		<param-value>/WEB-INF/spring-mvc-hibernate.xml</param-value>
//	</init-param>
//	<load-on-startup>1</load-on-startup>
//</servlet>
//
//<servlet-mapping>
//	<servlet-name>dispatcher</servlet-name>
//	<url-pattern>/</url-pattern>
//</servlet-mapping>

public class DispatcherServletInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {

	@Override
	protected Class<?>[] getRootConfigClasses() {
		return null;
	}

	@Override
	protected Class<?>[] getServletConfigClasses() {
		return new Class[] { HibernateConfig.class };
	}

	@Override
	protected String[] getServletMappings() {
		return new String[] { "/" };
	}

}
