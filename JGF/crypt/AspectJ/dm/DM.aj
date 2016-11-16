package dm;

import dmLib.core.aspects.DM_Share;
import dmLib.core.aspects.Dm_Filter;
import dmLib.core.aspects.Dm_Layer;

public final aspect DM extends Dm_Layer {
	
	// Project where the point cuts should work
	public final pointcut project() 	: within(dm.*);
	
	
	 /** 
	  * 	The distributed memory uses the local/ global view approach, 
	  *  thus no need to use parallel for task distribution since we are distributing 
	  *  the data that each process will compute instead of the loop iterations.
	  */
	
	
	private static aspect splitPlain1 extends DM_Share
	{
		pointcut scatter_creation() 	: 	call(private byte [] createPlain1());
		
		pointcut global_view()		: 	call(private void fillPlain1()) || 
										call(public void JGFvalidate());
		
		pointcut before_scatter()   	: 	call(public final void Do());
		
		
		public final int step	(final Object o) {return 8;}
		
		public final void data(Object o)
		{
			((IDEATest) o).plain1 = create_view(((IDEATest) o).plain1); 
		}
	}

	private static aspect splitCrypt1 extends DM_Share
	{
		pointcut scatter_creation() 	: call(private byte [] createCrypt1());
		pointcut global_view()		: call(public void JGFvalidate());
		
		public int step (final Object o) {return 8;}
		
		public final void data(Object o)
		{
			((IDEATest) o).crypt1 = create_view(((IDEATest) o).crypt1); 
		}
	}
	
	private static aspect splitPlain2 extends DM_Share
	{
		pointcut scatter_creation() 	: call(private byte [] createPlain2());
		pointcut gather()			: call(public final void Do());
		
		public int step (final Object o) {return 8;}
		
		public final void data(Object o)
		{
			((IDEATest) o).plain2 = create_view(((IDEATest) o).plain2); 
		}
	}

	private static aspect filter extends Dm_Filter
	{
		pointcut master()	:	(call(private void fillPlain1())) ||
								(call(public void JGFvalidate()));
	}
}
