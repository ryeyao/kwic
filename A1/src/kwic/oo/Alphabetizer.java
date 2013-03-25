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
 *  Created: 23 Sep 2002 
 * 
 *  $Id$
 * 
 *  Description:
 *    Sorts circular shifts alphabetically
 * </file>
*/

package kwic.oo;

/*
 * $Log$
*/

/**
 *  An object of the Alphabetizer class sorts all lines, that it gets
 *  from an instance of the CircularShifter class. Methods to access the
 *  set of sorted lines are provided by the Alphabetizer class as well.
 *  @author  dhelic
 *  @version $Id$
*/

public class Alphabetizer{

//----------------------------------------------------------------------
/**
 * Fields
 *
 */
//----------------------------------------------------------------------

/**
 * Array holding sorted indices of lines
 *
 */

  private int sorted_[];

/**
 * CircularShifter that provides lines
 *
 */

  private CircularShifter shifter_ = new CircularShifter();

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
 * Sorts all lines provided by shifter alphabetically.
 * @param shifter the source of lines
 * @return void
 */

  public void alpha(CircularShifter shifter){

        // keep the shifter instance to be able to return sorted lines
    shifter_ = shifter;

        // initialize the index array
    sorted_ = new int[shifter_.getLineCount()];
    for(int i = 0; i < sorted_.length; i++)
      sorted_[i] = i;

        // heap sort algorithm
        // heap is a complete tree where the value of a node
        // is greater than the value of its children
        // heap sort algortihm first creates the heap
        // and then removes the root (== greatest value) of the heap
        // finally the heap property should be reconstructed in the
        // remaining tree

        // we represent the heap in the memory as an array
        // where the children of the i-th node have
        // indices equal to 2*i and 2*i+1

        // create heap
    for(int i = (sorted_.length / 2 - 1); i >= 0; i--)
      shiftDown(i, sorted_.length);

        // remove the root and recreate the heap
    for(int i = (sorted_.length - 1); i >= 1; i--){
      
          // remove the root
      int tmp = sorted_[0];
      sorted_[0] = sorted_[i];
      sorted_[i] = tmp;
      
          // recreate the heap
      shiftDown(0, i);      
      }
  }

//----------------------------------------------------------------------
/**
 * This method builds and reconstucts the heap for the heap sort algorithm.
 * @param int root, int bottom
 * @return void
 */

  private void shiftDown(int root, int bottom){    
    int max_child = root * 2 + 1;

    while(max_child < bottom){
      if((max_child + 1) < bottom)
        if(shifter_.getLineAsString(sorted_[max_child + 1]).compareTo(shifter_.getLineAsString(sorted_[max_child])) > 0)
          max_child++;

      if(shifter_.getLineAsString(sorted_[root]).compareTo(shifter_.getLineAsString(sorted_[max_child])) < 0){
        int tmp = sorted_[root];
        sorted_[root] = sorted_[max_child];
        sorted_[max_child] = tmp;
        root = max_child;
        max_child = root * 2 + 1;
      }else
        break;
    }    
  }

//----------------------------------------------------------------------
/**
 * Gets the line from the specified position.
 * String array representing the line is returned.
 * @param line line index
 * @return String[]
 * @see #getLineAsString
 */

  public String[] getLine(int line){
    return shifter_.getLine(sorted_[line]);
  }

//----------------------------------------------------------------------
/**
 * Gets the line from the specified position.
 * String representing the line is returned.
 * @param line line index
 * @return String[]
 * @see #getLine
 */

  public String getLineAsString(int line){
    return shifter_.getLineAsString(sorted_[line]);
  }

//----------------------------------------------------------------------
/**
 * Gets the number of lines
 * @return int
 */

  public int getLineCount(){
//	  if (shifter_ == null) {
//		  return 0;
//	  }
    return shifter_.getLineCount();
  }

//----------------------------------------------------------------------
/**
 * Inner classes
 *
 */
//----------------------------------------------------------------------

}
