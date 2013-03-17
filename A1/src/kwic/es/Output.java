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
 *  Name:    Output.java
 * 
 *  Purpose: Output prints sorted lines in a nice format
 * 
 *  Created: 05 Nov 2002 
 * 
 *  $Id$
 * 
 *  Description:
 *    Output prints sorted lines in a nice format
 * </file>
*/

package kwic.es;

/*
 * $Log$
*/

/**
 *  An instance of the Output class prints sorted lines in nice format.
 *  @author  dhelic
 *  @version $Id$
*/

public class Output{

//----------------------------------------------------------------------
/**
 * Fields
 *
 */
//----------------------------------------------------------------------

//----------------------------------------------------------------------
/**
 * Constructors
 *
 */
//----------------------------------------------------------------------

//----------------------------------------------------------------------
/**
 * Methods
 *
 */
//----------------------------------------------------------------------

//----------------------------------------------------------------------
/**
 * Prints the lines at the standard output.
 * @param shift_storage sorted shifts
 * @return void
 */

  public void print(LineStorageWrapper shift_storage){
    
        // iterate through all lines
    for(int i = 0; i < shift_storage.getLineCount(); i++)
      
          // print current line
      System.out.println(shift_storage.getLineAsString(i));
  }

//----------------------------------------------------------------------
/**
 * Inner classes
 *
 */
//----------------------------------------------------------------------

}
