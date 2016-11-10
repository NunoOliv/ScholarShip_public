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


package mpi;
import java.util.Random;
import mpi.*;

public class JGFSparseMatmultBench extends SparseMatmult{ 

  public static int nprocess;
  public static int rank;
  private int size; 
  private static final long RANDOM_SEED = 10101010;

  private static final int datasizes_M[]   = {50000, 100000, 500000, 1000000, 1500000, 2000000};  
  private static final int datasizes_N[]   = {50000, 100000, 500000, 1000000, 1500000, 2000000};
  private static final int datasizes_nz[]  = {250000,500000,2500000, 5000000,7500000,10000000};
  private static final int SPARSE_NUM_ITER 	= 200;

  Random R = new Random(RANDOM_SEED);

  double [] x; 
  double [] y; 
  double [] p_y; 
  double [] val = null; 
  int [] col = null;
  int [] row = null;
  double [] buf_val = null; 
  int [] buf_col = null;
  int [] buf_row = null;

  int p_datasizes_nz,ref_p_datasizes_nz,rem_p_datasizes_nz;

  public JGFSparseMatmultBench(int np, int r) 
  {
        nprocess=np;
        rank=r;
  }

  public void JGFsetsize(int size){
    this.size = size;

  }

  public void JGFinitialise() throws MPIException{


/* Determine the size of the arrays row,val and col on each
   process. Note that the array size on process (nprocess-1) may
   be smaller than the other array sizes. 
*/

  p_datasizes_nz = (datasizes_nz[size] + nprocess -1) /nprocess;
  ref_p_datasizes_nz = p_datasizes_nz;
  rem_p_datasizes_nz = p_datasizes_nz - ((p_datasizes_nz*nprocess) - datasizes_nz[size]);
  if(rank==(nprocess-1)){
   if((p_datasizes_nz*(rank+1)) > datasizes_nz[size]) {
     p_datasizes_nz = rem_p_datasizes_nz;
   }
  }

/* Initialise the arrays val,col,row. Create full sizes arrays on process 0 */

  x 		= RandomVector(datasizes_N[size], R);
  y 		= new double[datasizes_M[size]];
  p_y 	= new double[datasizes_M[size]];

  val 	= new double	[p_datasizes_nz];
  col 	= new int	[p_datasizes_nz];
  row 	= new int	[p_datasizes_nz];

  if(rank==0) 
  {
    buf_val = new double	[datasizes_nz[size]];
    buf_col = new int	[datasizes_nz[size]];
    buf_row = new int	[datasizes_nz[size]];
  }

/* Initialize arrays val,col,row on process 0 and send the data to 
the other processes 
*/


	  /**
	   * Bruno Medeiros:
	   * In the original JGF version the second loop came first, thus it was generated
	   * first the values for the master, then it was generated the values for the slaves
	   * and send to the slave using MPI.COMM_WORLD.Ssend(buf_row,(k*ref_p_datasizes_nz), p_datasizes_nz,MPI.INT,k,1);
	   * I swap the order and now we can use the row, col and val arrays as buffer for the send
	   * without using offsets.
	   */
  if(rank==0) 
  {	
	  	// Slave values generation
	    for(int k=0;k<nprocess;k++) 
	    {
		      if(k==nprocess-1) 
		      {
		    	  		p_datasizes_nz = rem_p_datasizes_nz;
		      } 
		      for (int i=0; i<p_datasizes_nz; i++) 
		      {
			    	  	buf_row[i + k*ref_p_datasizes_nz] = row[i] = Math.abs(R.nextInt()) % datasizes_M[size];
			    	  	buf_col[i + k*ref_p_datasizes_nz] = col[i] = Math.abs(R.nextInt()) % datasizes_N[size];
			    	  	buf_val[i + k*ref_p_datasizes_nz] = val[i] = R.nextDouble();
		      }
		      if(k!= 0)
		      {
		    	  		MPI.COMM_WORLD.send(row,p_datasizes_nz,MPI.INT,k,1);
		    	  		MPI.COMM_WORLD.send(col ,p_datasizes_nz,MPI.INT,k,2);
		    	  		MPI.COMM_WORLD.send(val,p_datasizes_nz,MPI.DOUBLE,k,3);
		      }
	    }
	    p_datasizes_nz = ref_p_datasizes_nz; 
	    // master generation
	    for (int i=0; i<p_datasizes_nz; i++) 
	    {
	        row[i] = buf_row[i]; 
	        col[i] = buf_col[i];
	        val[i] = buf_val[i];
	    }	
  	} 
  	else 
  	{
  		MPI.COMM_WORLD.recv(row,p_datasizes_nz,MPI.INT,0,1);
  		MPI.COMM_WORLD.recv(col,p_datasizes_nz,MPI.INT,0,2);
  		MPI.COMM_WORLD.recv(val,p_datasizes_nz,MPI.DOUBLE,0,3);
  	}
  }
 
  public void JGFkernel() throws MPIException
  {
	  SparseMatmult.test(y, val, row, col, x, SPARSE_NUM_ITER, buf_row, p_y);
  }

  public void JGFvalidate(){

    if(rank==0) {
      double refval[] = {75.02484945753453,150.0130719633895,749.5245870753752};
      double dev = Math.abs(ytotal - refval[size]);
      if (dev > 1.0e-12 ){
        System.out.println("Validation failed");
        System.out.println("ytotal = " + ytotal + "  " + dev + "  " + size);
      }
    }

  }

  public void JGFtidyup(){
   System.gc();
  }  



  public void JGFrun(final boolean validation, final int size) throws MPIException
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

        private static double[] RandomVector(int N, java.util.Random R)
        {
                double A[] = new double[N];

                for (int i=0; i<N; i++)
                        A[i] = R.nextDouble() * 1e-6;

                return A;
        }


}
