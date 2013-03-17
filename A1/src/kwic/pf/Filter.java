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
 *  Name:    Filter.java
 * 
 *  Purpose: Filter is a component that has two pipes: an input and an output pipe.
 * 
 *  Created: 20 Sep 2002 
 * 
 *  $Id$
 * 
 *  Description:
 *    Filter is a component that has two pipes: an input and an output pipe.
 * </file>
*/

package kwic.pf;

/*
 * $Log$
*/

/**
 *  Filter is a component that has two pipes: an input and an output pipe.
 *  A filter object reads data from the input pipe, transform it and writes the transformed
 *  data to the output pipe. We may connect filters in sequences by declaring an output
 *  pipe of the first filter to be the input pipe of the second filter. Thus, in that way
 *  the transformed data of the first filter becomes the input data of the second filter.
 *  Each filter runs in its own thread.
 *  @author  dhelic
 *  @version $Id$
*/

abstract public class Filter implements Runnable{

//----------------------------------------------------------------------
/**
 * Fields
 *
 */
//----------------------------------------------------------------------

/**
 * Input pipe
 *
 */

  protected Pipe input_;

/**
 * Output pipe
 *
 */ 

  protected Pipe output_;

/**
 * Thread control flag
 *
 */
  
  private boolean is_started_ = false;

//----------------------------------------------------------------------
/**
 * Constructors
 *
 */
//----------------------------------------------------------------------

//----------------------------------------------------------------------
/**
 * @param input Input pipe
 * @param output Output pipe
 */

  public Filter(Pipe input, Pipe output){
    input_ = input;
    output_ = output;
  }

//----------------------------------------------------------------------
/**
 * Methods
 *
 */
//----------------------------------------------------------------------

//----------------------------------------------------------------------
/**
 * Starts the thread of this filter
 * @return void
 */

  public void start(){
    if(!is_started_){
      is_started_ = true;
      Thread thread = new Thread(this);
      thread.start();
    }
  }

//----------------------------------------------------------------------
/**
 * Stops the thread of this filter
 * @return void
 */

  public void stop(){
    is_started_ = false;
  }

//----------------------------------------------------------------------
/**
 * Thread run method
 * @return void
 */

  public void run(){
    transform();
  }

//----------------------------------------------------------------------
/**
 * This method transforms the data from the input pipe and writes the
 * transformed data into output pipe.
 * @return void
 */

  abstract protected void transform();

//----------------------------------------------------------------------
/**
 * Inner classes
 *
 */
//----------------------------------------------------------------------

}
