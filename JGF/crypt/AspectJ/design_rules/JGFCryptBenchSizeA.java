package design_rules;
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

/**
 * 
 * @author Bruno Medeiros
 * 
 * This code is the result of reusing the CPRYT case study from JGF Benchmarks with 
 * the application of the design rules to introduce parallelism using Aspects.
 * 
 * This code also has some minor modification namely more input sizes.
 * 
 */


public class JGFCryptBenchSizeA
{ 
	  public static void main(String argv[])
	  {
		  // If the user wants to validate the simulation 
		  final boolean validation	= (argv.length > 0) && (argv[0].equals("-v"));		
		  final int size				= (argv.length > 1) ? Integer.parseInt(argv[1]) : 0;	// The input size
		  
		  (new JGFCryptBench()).JGFrun(validation, size);
	  }
}
