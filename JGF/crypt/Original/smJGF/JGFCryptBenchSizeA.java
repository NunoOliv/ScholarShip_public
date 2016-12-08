package smJGF;
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



public class JGFCryptBenchSizeA
{ 

  public static int nthreads;

  public static void main(String argv[])
  {


	  	final boolean validation		= (argv.length > 0) && (argv[0].equals("-v"));		// If the user wants to validate the simulation 
	  	final int size				= (argv.length > 1) ? Integer.parseInt(argv[1]) : 5;	// The number of particles
	  	nthreads 					= (argv.length > 2) ? Integer.parseInt(argv[2]) : 2; // The number of threads
  
	  	JGFCryptBench cb = new JGFCryptBench(nthreads); 
		cb.JGFrun(validation,size);
  }
}


