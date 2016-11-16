package profiling;


public abstract aspect MethodTimeExecution {

	private MethodsTime methodsTimers = new MethodsTime();
	abstract pointcut getTime();
	
	public MethodsTime getMethodsTimers(){
		return methodsTimers;
	}
}
