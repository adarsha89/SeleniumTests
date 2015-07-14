/*
 * Data Access Utility
 */
package net.project.dataAccess;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

// TODO: Auto-generated Javadoc
/**
 * @author Adarsha
 * The Class DataAccessUtility.
 */
@Configuration
public class DataAccessUtility {

/** The named parameter jdbc template. */
static NamedParameterJdbcTemplate namedParameterJdbcTemplate;
	
	
	/**
	 * Named parameter jdbc template.
	 *
	 * @return the named parameter jdbc template
	 */
	@Bean
	public NamedParameterJdbcTemplate namedParameterJdbcTemplate()
	{
		DriverManagerDataSource driverManagerDataSource= new DriverManagerDataSource();
		driverManagerDataSource.setDriverClassName("com.mysql.jdbc.Driver");
		driverManagerDataSource.setUrl("jdbc:mysql://ec2-50-19-213-178.compute-1.amazonaws.com:3306/employeedata");
		driverManagerDataSource.setUsername("adarsha89");
		driverManagerDataSource.setPassword("adarshashetty1989");
		
		namedParameterJdbcTemplate= new NamedParameterJdbcTemplate(driverManagerDataSource);
		return namedParameterJdbcTemplate;
	}
	
	/**
	 * Gets the named parameter jdbc template.
	 *
	 * @return the named parameter jdbc template
	 */
	public static NamedParameterJdbcTemplate getNamedParameterJdbcTemplate()
	{
		return namedParameterJdbcTemplate;
	}
	
}
