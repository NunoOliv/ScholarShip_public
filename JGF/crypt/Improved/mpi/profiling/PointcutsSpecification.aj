package profiling;

public abstract aspect PointcutsSpecification extends MethodTimeExecution{

	pointcut getTime () :  call(public void JGFkernel());
}
