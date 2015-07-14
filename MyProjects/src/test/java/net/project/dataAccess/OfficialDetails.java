/*
 *  Official Details
 */
package net.project.dataAccess;

import java.sql.Date;

// TODO: Auto-generated Javadoc
/**
 * The Class OfficialDetails.
 */
public class OfficialDetails
{
	
	/** The username. */
	private String username;
	
	/** The password. */
	private String password;
  
  /** The designation. */
  private Designation designation;
  
  /** The joining date. */
  public Date joiningDate;
  
  /** The company email id. */
  private String companyEmailID;
  
  /** The manager id. */
  private String managerID;
  
  /** The password change status. */
  private boolean passwordChangeStatus;

  /**
   * Sets the username.
   *
   * @param username the new username
   */
  public void setUsername(String username)
  {
    this.username = username;
  }

  /**
   * Gets the username.
   *
   * @return the username
   */
  public String getUsername() {
    return this.username;
  }

  /**
   * Sets the password.
   *
   * @param password the new password
   */
  public void setPassword(String password) {
    this.password = password;
  }

  /**
   * Gets the password.
   *
   * @return the password
   */
  public String getPassword() {
    return this.password;
  }

  /**
   * Sets the joining date.
   *
   * @param joiningDate the new joining date
   */
  public void setJoiningDate(Date joiningDate) {
    this.joiningDate = joiningDate;
  }

  /**
   * Gets the joining date.
   *
   * @return the joining date
   */
  public Date getJoiningDate() {
    return this.joiningDate;
  }

  /**
   * Sets the company email id.
   *
   * @param companyEmailID the new company email id
   */
  public void setCompanyEmailID(String companyEmailID) {
    this.companyEmailID = companyEmailID;
  }

  /**
   * Gets the company email id.
   *
   * @return the company email id
   */
  public String getCompanyEmailID() {
    return this.companyEmailID;
  }

  /**
   * Sets the manager id.
   *
   * @param managerID the new manager id
   */
  public void setManagerID(String managerID) {
    this.managerID = managerID;
  }

  /**
   * Gets the manager id.
   *
   * @return the manager id
   */
  public String getManagerID() {
    return this.managerID;
  }

  /**
   * Sets the password change status.
   *
   * @param passwordChangeStatus the new password change status
   */
  public void setPasswordChangeStatus(boolean passwordChangeStatus) {
    this.passwordChangeStatus = passwordChangeStatus;
  }

  /**
   * Gets the password change status.
   *
   * @return the password change status
   */
  public boolean getPasswordChangeStatus() {
    return this.passwordChangeStatus;
  }

  /**
   * Sets the designation.
   *
   * @param designation the new designation
   */
  public void setDesignation(Designation designation) {
    this.designation = designation;
  }

  /**
   * Gets the designation.
   *
   * @return the designation
   */
  public Designation getDesignation() {
    return this.designation;
  }
  
  /**
   * Instantiates a new official details.
   *
   * @param username the username
   * @param password the password
   * @param designation the designation
   * @param joiningDate the joining date
   * @param companyEmailID the company email id
   * @param managerID the manager id
   */
  public OfficialDetails(String username, String password, String designation, Date joiningDate, String companyEmailID, String managerID)
  {
	  this.username=username;
	  this.password=password;
	  this.designation=Designation.valueOf(designation);
	  this.joiningDate=joiningDate;
	  this.companyEmailID=companyEmailID;
	  this.managerID=managerID;
	  
  }
}