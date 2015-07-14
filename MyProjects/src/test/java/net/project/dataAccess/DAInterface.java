/*
 * Data Access Interface
 */
package net.project.dataAccess;
 
import java.sql.Date;
import java.util.List;
// TODO: Auto-generated Javadoc
/**
 * @author Adarsha
 * The  Data Access Interface : DAInterface.
 */
public interface DAInterface {

	/**
	 * Creates the employee.
	 *
	 * @param employee the employee
	 * @return true, if successful
	 * @throws Exception the exception
	 */
	public boolean createEmployee(Employee employee) throws Exception;
	
	/**
	 * Update employee.
	 *
	 * @param employeeID the employee id
	 * @param password the password
	 * @param designation the designation
	 * @param managerID the manager id
	 * @return the int
	 * @throws Exception the exception
	 */
	public int updateEmployee(String employeeID,String password,String designation,String managerID) throws Exception;
	
	/**
	 * Delete employee.
	 *
	 * @param empID the emp id
	 * @param username the username
	 * @param designation the designation
	 * @param joiningDate the joining date
	 * @param companyEmailID the company email id
	 * @param managerID the manager id
	 * @param firstName the first name
	 * @param lastName the last name
	 * @return the int
	 */
	public int deleteEmployee(String empID,String username, String designation, Date joiningDate, String companyEmailID, String managerID,String firstName, String lastName);
	
	/**
	 * Retrieve employee.
	 *
	 * @param empID the employee id
	 * @param username the username
	 * @param password the password
	 * @param designation the designation
	 * @param joiningDate the joining date
	 * @param companyEmailID the company email id
	 * @param managerID the manager id
	 * @param firstName the first name
	 * @param lastName the last name
	 * @return the list of employees
	 */
	public List<Employee> retrieveEmployee(String empID,String username, String password, String designation, Date joiningDate, String companyEmailID, String managerID,String firstName, String lastName); 		

}
