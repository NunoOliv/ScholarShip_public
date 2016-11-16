package profiling;

public class TimeUnit 
{
	private static final int NANOSECONDS = 0;
	private static final int MILLISECONDS = 1;
	private static final int SECONDS = 2;
	
	public int measureUnit;
	public int measurePrint;
	
	TimeUnit(int measureUnit, int measurePrint)
	{
		this.measureUnit = measureUnit;
		this.measurePrint = measurePrint;
	}
	
	public long getActualTime(){
		
		return (measureUnit == NANOSECONDS) 
				?  System.nanoTime() : 
				   System.currentTimeMillis();
	}
	
	public double convertTimer(long time){
		long actualTime = time;
		
		if(measurePrint == SECONDS)
		{
			if(measureUnit == MILLISECONDS) return (actualTime  / 1000.0);
			if(measureUnit == NANOSECONDS)  return (actualTime / 1000000000.0);
		}
		if(measurePrint == MILLISECONDS)
		{
			if(measureUnit == SECONDS) 		return actualTime * 1000;
			if(measureUnit == MILLISECONDS) return actualTime;
		}
		if(measurePrint == NANOSECONDS)
		{
			if(measureUnit == SECONDS)		return actualTime * 1000000000;
			if(measureUnit == MILLISECONDS)	return actualTime * 1000000;
		}
		
		return actualTime;
	}
}
