/*
 * Employee
 */
package net.project.dataAccess;

import java.sql.Date;

// TODO: Auto-generated Javadoc
/**
 * The Class Employee.
 */
public class Employee
{
  
  /** The emp id. */
  private String empID;
  
  /** The official detail. */
  private OfficialDetails officialDetail;
  
  /** The personal detail. */
  private PersonalDetails personalDetail;

  /**
   * Sets the official details.
   *
   * @param officialDetail the new official details
   */
  public void setOfficialDetails(OfficialDetails officialDetail)
  {
    this.officialDetail = officialDetail;
  }

  /**
   * Gets the official details.
   *
   * @return the official details
   */
  public OfficialDetails getOfficialDetails() {
    return this.officialDetail;
  }

  /**
   * Sets the personal details.
   *
   * @param personalDetail the new personal details
   */
  public void setPersonalDetails(PersonalDetails personalDetail) {
    this.personalDetail = personalDetail;
  }

  /**
   * Gets the personal details.
   *
   * @return the personal details
   */
  public PersonalDetails getPersonalDetails() {
    return this.personalDetail;
  }

  /**
   * Sets the emp id.
   *
   * @param empID the new emp id
   */
  public void setEmpID(String empID) {
    this.empID = empID;
  }

  /**
   * Gets the emp id.
   *
   * @return the emp id
   */
  public String getEmpID() {
    return this.empID;
  }
 
 /**
  * Gets the username.
  *
  * @return the username
  */
 public String getUsername()
 {
	 return this.getOfficialDetails().getUsername();
 }
 
 /**
  * Gets the password.
  *
  * @return the password
  */
 public String getPassword()
	{
		return this.getOfficialDetails().getPassword();
	}
	
	/**
	 * Gets the designation.
	 *
	 * @return the designation
	 */
	public String getDesignation()
	{
		return this.getOfficialDetails().getDesignation().getDesignationValue();
	}
	
	/**
	 * Gets the joining date.
	 *
	 * @return the joining date
	 */
	public String getJoiningDate()
	{
		return this.getOfficialDetails().getJoiningDate().toString();
	}
	
	/**
	 * Gets the company email id.
	 *
	 * @return the company email id
	 */
	public String getCompanyEmailID()
	{
		return this.getOfficialDetails().getCompanyEmailID();
	}
	
	/**
	 * Gets the manager id.
	 *
	 * @return the manager id
	 */
	public String getManagerID()
	{
		return this.getOfficialDetails().getManagerID();
	}
	
	/**
	 * Gets the first name.
	 *
	 * @return the first name
	 */
	public String getFirstName()
	{
		return this.getPersonalDetails().getFirstName();
	}
	
	/**
	 * Gets the last name.
	 *
	 * @return the last name
	 */
	public String getLastName()
	{
		return this.getPersonalDetails().getLastName();
	}
	
	/**
	 * Gets the phone number.
	 *
	 * @return the phone number
	 */
	public int getPhoneNumber()
	{
		return this.getPersonalDetails().getPhoneNumber();
	}
	
	/**
	 * Gets the personal email id.
	 *
	 * @return the personal email id
	 */
	public String getPersonalEmailID()
	{
		return this.getPersonalDetails().getPersonalEmailID();
	}
 
  /**
   * Instantiates a new employee.
   *
   * @param empID the emp id
   * @param username the username
   * @param password the password
   * @param designation the designation
   * @param joiningDate the joining date
   * @param companyEmailID the company email id
   * @param managerID the manager id
   * @param firstName the first name
   * @param lastName the last name
   */
  public  Employee(String empID, String username, String password, String designation, Date joiningDate, String companyEmailID, String managerID, String firstName, String lastName )
  {
	  this.empID=empID;
	  this.officialDetail=new  OfficialDetails( username,  password,  designation,  joiningDate,  companyEmailID,  managerID);
	  this.personalDetail= new PersonalDetails( firstName,  lastName);
  }
  
  /**
   * Gets the single instance of Employee.
   *
   * @return single instance of Employee
   */
  public static Employee getInstance()
  {
	 return new Employee("E_123", "Ad", "Po", "ASSOCIATE", new Date(400L), "a", "M", "Ba", "da");
  }
 
 /* (non-Javadoc)
  * @see java.lang.Object#toString()
  */
 public String toString()
 {
	 String empID=this.empID;
	 String username=this.getOfficialDetails().getUsername();
	 String password=this.getOfficialDetails().getPassword();
	 String designation=this.getOfficialDetails().getDesignation().getDesignationValue();
	 String joiningDate=this.getOfficialDetails().getJoiningDate().toString();
	 String companyEmailID=this.getOfficialDetails().getCompanyEmailID();
	 String managerID=this.getOfficialDetails().getManagerID();
	 String firstName=this.getPersonalDetails().getFirstName();
	 String lastName=this.getPersonalDetails().getLastName();
	 int phoneNumber=this.getPersonalDetails().getPhoneNumber();
	 String personalEmailID=this.getPersonalDetails().getPersonalEmailID();
	 
	 String stringtoreturn=empID+" "+username+" "+password+" "+designation+" "+joiningDate+" "+companyEmailID+" "+managerID+" "+firstName+" "+lastName+" "+phoneNumber+" "+personalEmailID;
	 return stringtoreturn;
 }
}