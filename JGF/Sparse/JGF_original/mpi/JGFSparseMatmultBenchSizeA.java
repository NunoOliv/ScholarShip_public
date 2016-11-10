package mpi;

import mpi.MPI;
import mpi.MPIException;

/**************************************************************************
*                                                                         *
*             Java Grande Forum Benchmark Suite - MPJ Version 1.0         *
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


public class JGFSparseMatmultBenchSizeA
{ 

  public static int nprocess;
  public static int rank;

  public static void main(String argv[]) throws MPIException
  {

	  /* Initialise MPI */
	  	MPI.Init(argv);
	  	rank = MPI.COMM_WORLD.getRank();
	  	nprocess = MPI.COMM_WORLD.getSize();

	  	final boolean validation	= (argv.length > 0) && (argv[0].equals("-v"));		
	  	final int size			= (argv.length > 1) ? Integer.parseInt(argv[1]) : 0; 

	  	
	  	(new JGFSparseMatmultBench(nprocess,rank)).JGFrun(validation, size);

	  	MPI.Finalize();
 
  }
}


