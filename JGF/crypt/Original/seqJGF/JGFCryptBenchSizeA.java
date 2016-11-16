package seqJGF;
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

public class JGFCryptBenchSizeA
{ 
	  public static void main(String argv[])
	  {
		
		  final boolean validation	= (argv.length > 0) && (argv[0].equals("-v"));		// If the user wants to validate the simulation 
		  final int size				= (argv.length > 1) ? Integer.parseInt(argv[1]) : 0;	// The number of particles
		  
		  JGFCryptBench cb = new JGFCryptBench(); 
		  cb.JGFrun(validation, size);
	  }
}
