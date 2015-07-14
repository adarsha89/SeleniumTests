package net.project.dataAccess;

// TODO: Auto-generated Javadoc
/**
 * The Class PersonalDetails.
 */
public class PersonalDetails
{
	
	/** The first name. */
	private String firstName;
	
	/** The last name. */
	private String lastName;
	
	/** The phone number. */
	private int phoneNumber;
	
	/** The personal email id. */
	private String personalEmailID;
  
  
  /**
   * Sets the first name.
   *
   * @param firstName the new first name
   */
  public void setFirstName(String firstName)
  {
    this.firstName = firstName;
  }

  /**
   * Gets the first name.
   *
   * @return the first name
   */
  public String getFirstName()
  {
    return this.firstName;
  }

  /**
   * Sets the last name.
   *
   * @param lastName the new last name
   */
  public void setLastName(String lastName)
  {
    this.lastName = lastName;
  }

  /**
   * Gets the last name.
   *
   * @return the last name
   */
  public String getLastName()
  {
    return this.lastName;
  } 
  
  /**
   * Sets the phone number.
   *
   * @param phoneNumber the new phone number
   */
  public void setPhoneNumber(int phoneNumber)
  {
    this.phoneNumber = phoneNumber;
  }

  /**
   * Gets the phone number.
   *
   * @return the phone number
   */
  public int getPhoneNumber()
  {
    return this.phoneNumber;
  } 
  
  /**
   * Sets the personal email id.
   *
   * @param personalEmailID the new personal email id
   */
  public void setPersonalEmailID(String personalEmailID)
  {
    this.personalEmailID = personalEmailID;
  }

  /**
   * Gets the personal email id.
   *
   * @return the personal email id
   */
  public String getPersonalEmailID()
  {
    return this.personalEmailID;
  }
  
  /**
   * Instantiates a new personal details.
   *
   * @param firstName the first name
   * @param lastName the last name
   */
  public PersonalDetails(String firstName, String lastName)
  {
	  this.firstName=firstName;
	  this.lastName=lastName;
	  
  }
}

