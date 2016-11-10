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


package seq;
import java.util.Random;

public class JGFSparseMatmultBench extends SparseMatmult
{ 

	private int size; 
	private static final long RANDOM_SEED = 10101010;

	  /**
	   * Bruno M. The last 3 input do not existed in the Original
	   * JGF version. They were added to this version and the method
	   * "validation" does not deal with them.
	   */
	
	  private static final int datasizes_M[]   = {50000, 100000, 500000, 1000000, 1500000, 2000000};  
	  private static final int datasizes_N[]   = {50000, 100000, 500000, 1000000, 1500000, 2000000};
	  private static final int datasizes_nz[]  = {250000,500000,2500000, 5000000,7500000,10000000};
	  private static final int SPARSE_NUM_ITER 	= 200;
	  Random R = new Random(RANDOM_SEED);

	double [] x; 
	double [] y; 
	double [] val; 
	int [] col;
	int [] row;

  public void JGFsetsize(int size){
    this.size = size;

  }

  public void JGFinitialise()
  {

	  x = RandomVector(datasizes_N[size], R);
	  y = new double[datasizes_M[size]];
	
	  val = new double[datasizes_nz[size]];
	  col = new int[datasizes_nz[size]];
	  row = new int[datasizes_nz[size]];
	
	    for (int i=0; i<datasizes_nz[size]; i++) {
	
	        // generate random row index (0, M-1)
	        row[i] = Math.abs(R.nextInt()) % datasizes_M[size];
	
	        // generate random column index (0, N-1)
	        col[i] = Math.abs(R.nextInt()) % datasizes_N[size];
	
	        val[i] = R.nextDouble();
	
	    }

  }
 
  public void JGFkernel(){

    SparseMatmult.test(y, val, row, col, x, SPARSE_NUM_ITER);

  }

  public void JGFvalidate()
  {
	  
    double refval[] = {75.02484945753453,150.0130719633895,749.5245870753752};
    double dev = Math.abs(ytotal - refval[size]);
    if (dev > 1.0e-12 )
    {
	      System.out.println("Validation failed");
	      System.out.println("ytotal = " + ytotal + "  " + dev + "  " + size);
    }
  }

  public void JGFtidyup(){
//	  System.gc();
  }  



  public void JGFrun(boolean validation, int size)
  {
	  JGFsetsize			(size); 
	  JGFinitialise		(); 
	  JGFkernel			(); 
	  if(validation)
	  {
		  JGFvalidate	(); 
	  }
	  JGFtidyup			(); 
  }

  private static double[] RandomVector(int N, java.util.Random R)
  {
                double A[] = new double[N];

                for (int i=0; i<N; i++)
                        A[i] = R.nextDouble() * 1e-6;

                return A;
  }
}
