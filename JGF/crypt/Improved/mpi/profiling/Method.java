package profiling;

public class Method {
	
	long begin;
	long end;
	long timer;
	String name;
	TimeUnit timeUnit;
	
	Method(String name, TimeUnit timeUnit)
	{
		begin = end = timer = 0;
		this.name = name;
		this.timeUnit = timeUnit;
	}
	
	public void startTimer(TimeUnit measure)
	{	
		begin = timeUnit.getActualTime();
	}
	
	public void stopTimer()
	{
		end = timeUnit.getActualTime();
		timer = end - begin;
	}

	public void printTimer(boolean printMethodName){
		
		double actualtime = timeUnit.convertTimer(timer);
		
		if(printMethodName)
		{
			System.out.println("Method : "+name+"> Time :"+actualtime);
		}
		else
		{
			System.out.println("Time:"+actualtime);
		}
		
	}
}
