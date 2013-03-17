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
 *  Name:    CircularShifter.java
 * 
 *  Purpose: Produces circular shifts of input lines
 * 
 *  Created: 23 Sep 2002 
 * 
 *  $Id$
 * 
 *  Description:
 *    Produces circular shifts of input lines
 * </file>
*/

package kwic.pf;

/*
 * $Log$
*/

import java.io.IOException;
import java.io.CharArrayWriter;
import java.util.StringTokenizer;

/**
 *  CircularShifter filter produces circular shifts of a set 
 *  of lines passed to it. The set of lines is read from the input pipe of this
 *  filter. The produced shifts are written out to the output pipe
 *  of this filter.
 *  @author  dhelic
 *  @version $Id$
*/

public class CircularShifter extends Filter{

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
 * @param input input pipe
 * @param output output pipe
 */

  public CircularShifter(Pipe input, Pipe output){
    super(input, output);
  }

//----------------------------------------------------------------------
/**
 * Methods
 *
 */
//----------------------------------------------------------------------

//----------------------------------------------------------------------
/**
 * Produces all circular shifts of lines in a given set, read from the input pipe.
 * @return void
 */

  protected void transform(){
    try{
      
          // keeps the characters
      CharArrayWriter writer = new CharArrayWriter();
      
      int c = input_.read();
      while(c != -1){
        
            // line has been read
        if(((char) c) == '\n'){
          
              // current line
          String line = writer.toString();
          
              // convert the current line in array of words
          StringTokenizer tokenizer = new StringTokenizer(line);
          String[] words = new String[tokenizer.countTokens()];
          int i = 0;
          while(tokenizer.hasMoreTokens())
            words[i++] = tokenizer.nextToken();
          
              // iterate through all words of the current line
          for(i = 0; i < words.length; i++){
            
                // make a new shift
            String shift = "";
            for(int j = i; j < (words.length + i); j++){
              shift += words[j % words.length];
              if(j < (words.length + i - 1))
                shift += " ";
            }
            shift += '\n';
            
                // convert the shift into char array and write it to the output
            char[] chars = shift.toCharArray();
            for(int j = 0; j < chars.length; j++)
              output_.write(chars[j]);
          }     
          
              // reset the character buffer
          writer.reset();
        }else
          writer.write(c);
        
        c = input_.read();
      }

          // close the pipe
      output_.closeWriter();
    }catch(IOException exc){
      exc.printStackTrace();
      System.err.println("KWIC Error: Could not make circular shifts.");
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
