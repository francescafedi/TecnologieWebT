package Beans;

public class CounterResult {
	
	private int serverCounter = 0;
	private int beanCounter = 0;
	private long serverTime = 0;
	private long beanTime = 0;
	
	public int getServerCounter() {
		return serverCounter;
	}
	public void setServerCounter(int serverCounter) {
		this.serverCounter = serverCounter;
	}
	public int getBeanCounter() {
		return beanCounter;
	}
	public void setBeanCounter(int beanCounter) {
		this.beanCounter = beanCounter;
	}
	public long getServerTime() {
		return serverTime;
	}
	public void setServerTime(long serverTime) {
		this.serverTime = serverTime;
	}
	public long getBeanTime() {
		return beanTime;
	}
	public void setBeanTime(long beanTime) {
		this.beanTime = beanTime;
	}
	public CounterResult() {
		super();
	}
	@Override
	public String toString() {
		return "CounterResult [serverCounter=" + serverCounter
				+ ", beanCounter=" + beanCounter + ", serverTime=" + serverTime
				+ ", beanTime=" + beanTime + "]";
	}
	
	
	

}
