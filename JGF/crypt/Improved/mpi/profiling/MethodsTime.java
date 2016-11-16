package profiling;
import java.util.HashMap;


public class MethodsTime {
	
	HashMap <String, Method> timers = new HashMap<String, Method>();
	
	public void addAndStartMethod(String methodName, TimeUnit measure)
	{
		if(timers.containsKey(methodName))
		{
			
		}
		else
		{
			Method method = new Method(methodName, measure);
			timers.put(methodName, method);
			method.startTimer(measure);
		}
	}
	
	public void stopAndPrintMethod(String methodName, boolean printMethodName)
	{
		Method method = timers.get(methodName);
		method.stopTimer();
		method.printTimer(printMethodName);
	}
	

}
