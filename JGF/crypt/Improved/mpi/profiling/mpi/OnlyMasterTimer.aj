package profiling.mpi;

import profiling.PointcutsSpecification;
import mpi.MPI;
import mpi.MPIException;


public aspect OnlyMasterTimer extends PointcutsSpecification
{

		declare precedence : profiling.*, myMpiProcesses.*;
		
		pointcut onlyMasterMPI() :
			( 
					call(public void stopAndPrintMethod(String, boolean))
			);

		
		void around () : onlyMasterMPI()
		{
			try 
			{
				
				if(MPI.COMM_WORLD.getRank() == 0)
				{
					proceed();
				}
			} 
			catch (MPIException e) 
			{
				e.printStackTrace();
			}
		}
}
