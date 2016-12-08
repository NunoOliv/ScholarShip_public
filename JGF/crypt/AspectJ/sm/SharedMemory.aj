package sm;


import smLib.core.aspects.Sm_For;
import smLib.core.aspects.Sm_Layer;
import smLib.core.aspects.Sm_Parallel;

final aspect SharedMemory extends Sm_Layer
{	
	// Project where the point cuts should work
	public final pointcut project() 	: within(sm.*);
	
	// Parallel Region
	private static aspect parallelRegion extends Sm_Parallel
	{
		pointcut parallel() : (call(public final void Do())) && project();	
	}
	
	// Parallel For
	private static aspect workSharing extends Sm_For
	{
		// Block static distribution
		pointcut for_static(final int begin, final int end, final int inc) : 
			execution(private void cipher_idea(int, int, int, ..)) 
			&& args(begin,end,inc, ..) 
			&& project();
		
		public boolean  no_wait(){ return true;}
	}

}