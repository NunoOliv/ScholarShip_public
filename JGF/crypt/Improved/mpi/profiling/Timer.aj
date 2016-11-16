package profiling;

import mpi.MPI;
import mpi.MPIException;

public aspect Timer extends PointcutsSpecification{

	@SuppressWarnings("unused")
	private static final int NANOSECONDS = 0;
	private static final int MILLISECONDS = 1;
	private static final int SECONDS = 2;
		
	void around () : getTime()
	{
		TimeUnit timeUnit = new TimeUnit(MILLISECONDS,SECONDS);
		
		
		boolean printMethod = false;
		
		String methodName = thisJoinPointStaticPart.toShortString();
		MethodsTime methodsAndTimers = getMethodsTimers();

		
		try 
		{		
			MPI.COMM_WORLD.barrier();
			methodsAndTimers.addAndStartMethod(methodName, timeUnit);
			proceed();
			MPI.COMM_WORLD.barrier();
			methodsAndTimers.stopAndPrintMethod(methodName, printMethod);
		} 
		catch (MPIException e) {}
		
		
	}
	
}
