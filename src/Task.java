
public class Task {
	private String name = "";
	private int priority = 0;
	private int startTime = 0;
	private int cpuBurst = 0;
	private int waitingTime = 0;	

	public void run(int runningtime){
		int counter = 0;
		while(counter != 500*runningtime)
			counter++;//doing some stuff...
		cpuBurst-= runningtime;
	}
	
	
	public int getWaitingTime() {
		return waitingTime;
	}

	public void setWaitingTime(int waitingTime) {
		this.waitingTime = waitingTime;
	}

	/**
	 * A taszk konstruktora, a kapott param�terekb�l
	 * l�trehozza a k�v�nt taszk-p�ld�nyt.
	 * @param taskname - a taszk neve
	 * @param prior - a taszk prior�t�sa(0-9)
	 * @param starttime - a taszk ind�t�si ideje, a k�vetkez� id�szeletben m�r futhat(>=0)
	 * @param burst - a taszk l�ketideje (>=1)
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
