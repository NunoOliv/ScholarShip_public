/**************************************************************************
*                                                                         *
*         Java Grande Forum Benchmark Suite - Thread Version 1.0          *
*                                                                         *
*                            produced by                                  *
*                                                                         *
*                  Java Grande Benchmarking Project                       *
*                                                                         *
*                                at                                       *
*                                                                         *
*                Edinburgh Parallel Computing Centre                      *
*                                                                         * 
*                email: epcc-javagrande@epcc.ed.ac.uk                     *
*                                                                         *
*                                                                         *
*      This version copyright (c) The University of Edinburgh, 2001.      *
*                         All rights reserved.                            *
*                                                                         *
**************************************************************************/


package smJGF;

public class JGFCryptBench extends IDEATest { 

  private int size; 
  //private int datasizes[]={3000000,20000000,50000000};
  // Bruno
  private int datasizes[]={3000000,20000000,50000000, 200000000,500000000, 900000000}; 
  public static int nthreads;

@SuppressWarnings("static-access")
public JGFCryptBench(int nthreads)
{
	this.nthreads=nthreads;
}


  public void JGFsetsize(int size)
  {
    this.size = size;
  }

  public void JGFinitialise()
  {
    array_rows = datasizes[size];
    buildTestData();
  }
 
  public void JGFkernel()
  {
    Do(); 
  }

  public void JGFvalidate()
  {
    boolean error = false; 
    
    for (int i = 0; i < array_rows; i++)
    {
      error = (plain1 [i] != plain2 [i]); 
      if (error)
      {
		System.out.println("Validation failed");
		System.out.println("Original Byte " + i + " = " + plain1[i]); 
		System.out.println("Encrypted Byte " + i + " = " + crypt1[i]); 
		System.out.println("Decrypted Byte " + i + " = " + plain2[i]); 
      }
    }
  }


  public void JGFtidyup()
  {
	    freeTestData(); 
  }  



  public void JGFrun(boolean validation, int size)
  {
	JGFsetsize(size); 
    JGFinitialise(); 
    JGFkernel(); 
    
    if(validation)
    {
    		JGFvalidate(); 
    }
    JGFtidyup(); 
  }
}
