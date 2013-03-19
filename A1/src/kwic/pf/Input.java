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
 *  Name:    Input.java
 * 
 *  Purpose: Input reads and parses the KWIC input file
 * 
 *  Created: 20 Sep 2002 
 * 
 *  $Id$
 * 
 *  Description:
 *    Input reads and parses the KWIC input file
 * </file>
*/

package kwic.pf;

/*
 * $Log$
*/

import java.io.IOException;
import java.io.InputStream;

/**
 *  Input module is a filter that is responsible for reading and parsing the content of 
 *  a KWIC input file. The format of the KWIC input file is as follows:
 *  <ul>
 *  <li>Lines are separated by the line separator character(s) (on Unix '\n', on Windows '\r\n')
 *  <li>Each line consists of a number of words. Words are delimited by any number and combination
 *  of the space chracter (' ') and the horizontal tabulation chracter ('\t').
 *  </ul>
 *  The data is parsed in the following way:
 *  <ul>
 *  <li>All multiple line separators are replaced with a single line separator ('\n')
 *  <li>All mulitple word delimiters are replaced with a single word delimiter (' ')
 *  </ul>
 *  The parsed data is written to the outpup pipe of this filter, for further processing
 *  by the next filter in the pipeline: CircularShifter filter.
 *  @author  dhelic
 *  @version $Id$
*/

public class Input extends Filter{

//----------------------------------------------------------------------
/**
 * Fields
 *
 */
//----------------------------------------------------------------------

/**
 * File stream of the KWIC input file
 *
 */

  private InputStream in_;

//----------------------------------------------------------------------
/**
 * Constructors
 *
 */
//----------------------------------------------------------------------

//----------------------------------------------------------------------
/**
 * Default constructor
 * @param in input file
 * @param output output pipe
 */

  public Input(InputStream in, Pipe output){
    super(null, output);
    in_ = in;
  }

//----------------------------------------------------------------------
/**
 * Methods
 *
 */
//----------------------------------------------------------------------

//----------------------------------------------------------------------
/**
 * This method reads and parses a KWIC input file. If an I/O exception occurs
 * during the execution of this method, an error message is shown and program
 * exits. The parsed data is written to the output pipe.
 * @return void
 */

  protected void transform(){
    try{
      boolean is_new_line = false;      
      boolean is_new_word = false;
      boolean is_line_started = false;
      
      int c = in_.read();
      while(c != -1){
//    	  System.out.println("char read :" + (char)c);
        switch((byte) c){
        case '\n':          
          is_new_line = true;
          break;
        case ' ':
          is_new_word = true;
          break;
        case '\t':
          is_new_word = true;
          break;
        case '\r':
          break;
        default:
          if(is_new_line){
            output_.write('\n');
            is_new_line = false;
            is_line_started = false;
          }
          if(is_new_word){
            if(is_line_started)
              output_.write(' ');
            is_new_word = false;
          }
          output_.write(c);
          is_line_started = true;
          break;
        }        
        c = in_.read();
      }
      
          // terminate the last line
      output_.write('\n');
      
          // close the pipe
      output_.closeWriter();
    }catch(IOException exc){
      exc.printStackTrace();
      System.err.println("KWIC Error: Could not read the input file.");
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
