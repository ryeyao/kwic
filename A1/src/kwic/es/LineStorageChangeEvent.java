// -*- Java -*-
/*
 * <copyright>
 * 
 *  Copyright (c) 2002
 *  Institute for Information Processing and Computer Supported New Media (IICM),
 *  Graz University of Technology, Austria.
 * 
 * </copyright>
 * 
 * <file>
 * 
 *  Name:    LineStorageChangeEvent.java
 * 
 *  Purpose: Class representing change events in the system
 * 
 *  Created: 05 Nov 2002 
 * 
 *  $Id$
 * 
 *  Description:
 *    Class representing change events in the system
 * </file>
*/

package kwic.es;

/*
 * $Log$
*/

/**
 *  LineStorageChangeEvent class represents a change event occuring in 
 *  a LineStorageWrapper object. Thus, whenever a LineStorageWrapper object
 *  changes an event object is created (an instance of LineStorageChangeEvent
 *  class) and sent to all observers of the LineStorageWrapper objects. This
 *  event contains all information about the change that was made. Thus, the
 *  type of the change is described (e.g. "Add", "Delete", etc.), as well as
 *  additional parameters of the change, which are needed to describe the change
 *  completely (e.g. the deleted line is included into the "Delete" event).
 *  @author  dhelic
*/

public class LineStorageChangeEvent{

//----------------------------------------------------------------------
/**
 * Fields
 *
 */
//----------------------------------------------------------------------

/**
 * Constants for different change types
 *
 */

  public static final int ADD = 0;
  public static final int DELETE = 1;
  public static final int INSERT = 2;

/**
 * The type of the change
 *
 */

  private int type_;

/**
 * Argument of the change, e.g. line that was involved in the change
 *
 */

  private String arg_;

//----------------------------------------------------------------------
/**
 * Constructors
 *
 */
//----------------------------------------------------------------------

//----------------------------------------------------------------------
/**
 * Creates a new event object with the specified change type
 * @param type change type
 */

  public LineStorageChangeEvent(int type){
    type_ = type;
  }

//----------------------------------------------------------------------
/**
 * Creates a new event object with the specified change type and
 * the specified argument of the change
 * @param type change type
 * @param arg change argument
 */

  public LineStorageChangeEvent(int type, String arg){
    type_ = type;
    arg_ = arg;
  }

//----------------------------------------------------------------------
/**
 * Methods
 *
 */
//----------------------------------------------------------------------

//----------------------------------------------------------------------
/**
 * Sets the change type
 * @param type change type
 * @return void
 * @see #getType
 */

  public void setType(int type){
    type_ = type;
  }

//----------------------------------------------------------------------
/**
 * Gets the change type
 * @return int
 * @see #setType
 */

  public int getType(){
    return type_;
  }

//----------------------------------------------------------------------
/**
 * Sets the change argument
 * @param arg change argument
 * @return void
 * @see #getArg
 */

  public void setArg(String arg){
    arg_ = arg;
  }

//----------------------------------------------------------------------
/**
 * Gets the change argument
 * @return String
 * @see #setArg
 */

  public String getArg(){
    return arg_;
  }

//----------------------------------------------------------------------
/**
 * Inner classes
 *
 */
//----------------------------------------------------------------------

}
