package seq;
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



public class JGFSparseMatmultBenchSizeA{ 

  public static void main(String argv[]){
	  
	  	// If the user wants to validate the simulation and the dim problem
	  	final boolean validation	= (argv.length > 0) && (argv[0].equals("-v"));		
	  	final int size			= (argv.length > 1) ? Integer.parseInt(argv[1]) : 0; 

	  	(new JGFSparseMatmultBench()).JGFrun(validation, size);
  }
}
