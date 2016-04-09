import java.util.Comparator;

public class Task {
	private String name = "";		//name of the task ( for example: "A")
	private int priority = 0;		//the task's priority(0..9)
	private int startTime = 0;		//the time when the task arrives
	private int cpuBurst = 0;		//changes dinamically
	private int initialCpuBurst = 0;//never changes
	private int waitingTime = 0;	//number of cycles the task has waited
	
	public static Comparator<Task> CpuBurstComparator = new Comparator<Task>(){
		public int compare(Task t1, Task t2){
			Integer i1 = t1.cpuBurst;
			Integer i2 = t2.cpuBurst;
			return i2.compareTo(i1);
		}		
	};
	
	public int getInitialCpuBurst() {
		return initialCpuBurst;
	}


	public void setInitialCpuBurst(int initialCpuBurst) {
		this.initialCpuBurst = initialCpuBurst;
	}
	

	
	/**
	 * This method simulates the real tasks' runtime.
	 * @param runningtime - the number of time task can run
	 */
	public void run(int runningtime){
		int counter = 0;
		while(counter != 500*runningtime)
			counter++;//doing some stuff...
		cpuBurst-= runningtime;
		System.out.print(name);//writing out it's name
	}
	
	
	public int getWaitingTime() {
		return waitingTime;
	}

	public void setWaitingTime(int waitingTime) {
		this.waitingTime = waitingTime;
	}

	/**
	 * A taszk konstruktora, a kapott paraméterekbõl
	 * létrehozza a kívánt taszk-példányt.
	 * @param taskname - a taszk neve
	 * @param prior - a taszk priorítása(0-9)
	 * @param starttime - a taszk indítási ideje, a következõ idõszeletben már futhat(>=0)
	 * @param burst - a taszk löketideje (>=1)
	 */
	Task(String taskname, int prior, int starttime,int initburst){
		name = taskname;
		priority = prior;
		startTime = starttime;
		initialCpuBurst = initburst;
		cpuBurst = initialCpuBurst;
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
