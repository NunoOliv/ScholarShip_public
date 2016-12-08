/**************************************************************************
*                                                                         *
*             Java Grande Forum Benchmark Suite - Version 2.0             *
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
*      This version copyright (c) The University of Edinburgh, 1999.      *
*                         All rights reserved.                            *
*                                                                         *
**************************************************************************/


package sm;


public class JGFCryptBench extends IDEATest{ 

  private int size; 
  private int datasizes[]={	3000000, 20000000, 50000000, 		// Original 
		  					200000000, 500000000, 900000000}; 	// extra
  
  
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
	  double start = System.currentTimeMillis();  
	  Do(); 
	  double end  = System.currentTimeMillis();
	  System.out.println((end-start) / 1000.0);
  }

  public void JGFvalidate()
  {
	 for (int i = 0; i < array_rows; i++)
	  {
		  if ((plain1 [i] != plain2 [i]))
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



  public void JGFrun(boolean isToValidate, int size)
  {  
	  JGFsetsize(size);
	  JGFinitialise();
	  JGFkernel(); 
	    
	  if(isToValidate)
	  {
		  JGFvalidate(); 
	  }
	//  JGFtidyup(); 
  }
}
