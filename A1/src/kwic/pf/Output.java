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
 *  Purpose: Output prints the data from its input pipe on the standard output
 * 
 *  Created: 24 Sep 2002 
 * 
 *  $Id$
 * 
 *  Description:
 *    Output prints the data from its input pipe on the standard output
 * </file>
*/

package kwic.pf;

/*
 * $Log$
*/

import java.io.IOException;

/**
 *  Output prints the data from its input pipe on the standard output
 *  @author  dhelic
 *  @version $Id$
*/

public class Output extends Filter{

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
 * Default constructor
 * @param input Input Pipe
 */

  public Output(Pipe input){
    super(input, null);
  }

//----------------------------------------------------------------------
/**
 * Methods
 *
 */
//----------------------------------------------------------------------

//----------------------------------------------------------------------
/**
 * This method writes the data from the input pipe on the standard output.
 * @return void
 */

  protected void transform(){
    try{
      int c = input_.read();
      while(c != -1){
        System.out.print((char) c);
        c = input_.read();
      }
    }catch(IOException exc){
      exc.printStackTrace();
      System.err.println("KWIC Error: Broken pipe");
      System.exit(1);      
    }
  }

//----------------------------------------------------------------------
/**
 * Inner classes
 *
 */
//----------------------------------------------------------------------

}
