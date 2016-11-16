package mpi.JFG.improved;
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


import mpi.MPI;
import mpi.MPIException;

public class JGFCryptBenchSizeA{ 

  public static int nprocess;
  public static int rank;

  public static void main(String argv[]) throws MPIException
  {
	  /* Initialise MPI */
     MPI.Init(argv);
     rank 		= MPI.COMM_WORLD.getRank();
     nprocess 	= MPI.COMM_WORLD.getSize();

	 final boolean validation	= (argv.length > 0) && (argv[0].equals("-v"));		// If the user wants to validate the simulation 
	 final int size				= (argv.length > 1) ? Integer.parseInt(argv[1]) : 0;	// The number of particles


	 (new JGFCryptBench(nprocess,rank)).JGFrun(validation,size);

    /* Finalise MPI */
     MPI.Finalize();
  }
}


