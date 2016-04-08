
public class Task {
	private String name = "";
	private int priority = 0;
	private int startTime = 0;
	private int cpuBurst = 0;
	private int waitingTime = 0;

	/**
	 * A taszk konstruktora, a kapott paraméterekbõl
	 * létrehozza a kívánt taszk-példányt.
	 * @param taskname - a taszk neve
	 * @param prior - a taszk priorítása(0-9)
	 * @param starttime - a taszk indítási ideje, a következõ idõszeletben már futhat(>=0)
	 * @param burst - a taszk löketideje (>=1)
	 */
	Task(String taskname, int prior, int starttime,int burst){
		name = taskname;
		priority = prior;
		startTime = starttime;
		cpuBurst = burst;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getPriority() {
		return priority;
	}
	public void setPriority(int priority) {
		this.priority = priority;
	}
	public int getStartTime() {
		return startTime;
	}
	public void setStartTime(int startTime) {
		this.startTime = startTime;
	}
	public int getCpuBurst() {
		return cpuBurst;
	}
	public void setCpuBurst(int cpuBurst) {
		this.cpuBurst = cpuBurst;
	}
}
