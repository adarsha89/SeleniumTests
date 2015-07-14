/*
 * Designation Of Employees
 */
package net.project.dataAccess;

// TODO: Auto-generated Javadoc
/**
 * The Enum Designation.
 */
public enum Designation
{
  
  /** The junior associate. */
  JUNIOR_ASSOCIATE("JUNIOR ASSOCIATE"), 
 /** The associate. */
 ASSOCIATE("ASSOCIATE"), 
 /** The senior associate. */
 SENIOR_ASSOCIATE("SENIOR ASSOCIATE"), 
 /** The manager. */
 MANAGER("MANAGER"), 
 /** The senior manager. */
 SENIOR_MANAGER("SENIOR MANAGER"), 
 /** The director. */
 DIRECTOR("DIRECTOR"), 
 /** The vp. */
 VP("VP"), 
 /** The ceo. */
 CEO("CEO");

  /** The designation. */
  private String designation;

  /**
   * Instantiates a new designation.
   *
   * @param enteredDesignation the entered designation
   */
  private Designation(String enteredDesignation) { this.designation = enteredDesignation; }


  /**
   * Gets the designation value.
   *
   * @return the designation value
   */
  public String getDesignationValue()
  {
    return this.designation;
  }
  
  /**
   * Sets the designation value.
   *
   * @param enteredDesignation the new designation value
   */
  public void setDesignationValue(String enteredDesignation)
  {
	  this.designation = enteredDesignation; 
  }
  
  /**
   * From string.
   *
   * @param designationString the designation string
   * @return the designation
   */
  public static Designation fromString(String designationString) {
	    if (designationString != null) {
	      for (Designation b : Designation.values()) {
	        if (designationString.equalsIgnoreCase(b.designation)) {
	          return b;
	        }
	      }
	    }
	    return null;
	  }
}