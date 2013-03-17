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
 *  Name:    Alphabetizer.java
 * 
 *  Purpose: Sorts circular shifts alphabetically
 * 
 *  Created: 05 Nov 2002 
 * 
 *  $Id$
 * 
 *  Description:
 *    Sorts circular shifts alphabetically
 * </file>
*/

package kwic.es;

/*
 * $Log$
*/

import java.util.Observable;
import java.util.Observer;

/**
 *  Similarly to CircularShifter class, Alphabetizer class implemets the "Observer" 
 *  part of the standard "Observable"-"Observer" mechanism as well. However, an object
 *  of Alphabetizer class "observes" a LineStorageWrapper object, which keeps
 *  circular shifts, whereas an object of CircularShifter class "observes" a LineStorageWrapper
 *  object, which keeps original lines from a KWIC input file. Thus, an instance of Alphabetizer
 *  class declares its interest in tracking changes in an object of LineStorageWrapper
 *  class holding circular shifts. Therefore, any event produced and sent by that 
 *  LineStorageWrapper object whenever its internal state is changed (i.e., whenever a new circular 
 *  shift has been added) is catched by Alphabetizer object. In turn, this leads to 
 *  sorting the circular shifts alphabetically.
 *  @author  dhelic
 *  @version $Id$
*/

public class Alphabetizer implements Observer{

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
 */

  public void update(Observable observable, Object arg){
    
        // cast to the line storage object
    LineStorageWrapper shifts = (LineStorageWrapper) observable;

        // cast to the event object
    LineStorageChangeEvent event = (LineStorageChangeEvent) arg;

        // take actions depending on the type of the change
    switch(event.getType()){

          // if this is an ADD change sort shifts
    case LineStorageChangeEvent.ADD:
      
          // get the count of shifts
      int count = shifts.getLineCount();
      
          // get the last added line
      String shift = shifts.getLineAsString(count - 1);
          
          // iterate through all shifts and insert the new shift on the proper
          // position
      for(int i = 0; i < (count - 1); i++){
        
            // if the new shift is "less than" the current shift insert it
            // on the position of the current shift
        if(shift.compareTo(shifts.getLineAsString(i)) <= 0){
           shifts.insertLine(shifts.getLine(count - 1), i);

               // delete the new shift from the last position
               // the last position is now equal to count since
               // we inserted the new shift once more
           shifts.deleteLine(count);
           break;
        }           
      }
      break;
    default:
      break;      
    }
  }

//----------------------------------------------------------------------
/**
 * Inner classes
 *
 */
//----------------------------------------------------------------------

}
