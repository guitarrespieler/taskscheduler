import java.util.Comparator;

public class Task {
	private String name = "";			//name of the task ( for example: "A")
	private int priority = 0;			//the task's priority(0..9)
	private int startTime = 0;			//the time when the task arrives
	private int remainingCpuBurst = 0;	//changes dinamically
	private int initialCpuBurst = 0;	//never changes
	private int waitingTime = 0;		//number of cycles the task has waited
	private int endTime = 0;			//the time task ends its run
	
	/**
	 * Ctor of the task, it creates the 
	 * wanted task-object from the given params.
	 * @param taskname - name of the task
	 * @param prior - priority of the task(0-9)
	 * @param starttime - the time when the task arrives(>=0)
	 * @param burst - CPU-burst of the task (>=1)
	 */
	Task(String taskname, int prior, int starttime,int initburst){
		name = taskname;
		priority = prior;
		startTime = starttime;
		initialCpuBurst = initburst;
		remainingCpuBurst = initialCpuBurst;
	}
	/**
	 * This method simulates the real tasks' runtime.
	 * @param runningtime - the number of timeslices the task can run
	 */
	public void run(int runningtime){
		int counter = 0;
		while(runningtime != 0 && remainingCpuBurst != 0){
			while(counter != 50*runningtime)	//doing some stuff...
				counter++;
			counter = 0;
			remainingCpuBurst--;				//if equals 0, break
			runningtime--;						//if equals 0, break
		}
		System.out.print(name);					//writing out it's own name
	}
	
	public static Comparator<Task> CpuBurstComparator = new Comparator<Task>(){
		public int compare(Task t1, Task t2){
			Integer i1 = t1.remainingCpuBurst;
			Integer i2 = t2.remainingCpuBurst;
			return i2.compareTo(i1);
		}		
	};	
	public static Comparator<Task> EndTimeComparator = new Comparator<Task>(){
		public int compare(Task t1, Task t2){
			Integer i1 = t1.endTime;
			Integer i2 = t2.endTime;
			return i2.compareTo(i1);
		}		
	};	
	public int getInitialCpuBurst() {
		return initialCpuBurst;
	}
	public void setInitialCpuBurst(int initialCpuBurst) {
		this.initialCpuBurst = initialCpuBurst;
	}	
	public int getWaitingTime() {
		return waitingTime;
	}
	public void setWaitingTime(int waitingTime) {
		this.waitingTime = waitingTime;
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
		return remainingCpuBurst;
	}
	public void setCpuBurst(int cpuBurst) {
		this.remainingCpuBurst = cpuBurst;
	}
	public int getEndTime() {
		return endTime;
	}
	public void setEndTime(int endTime) {
		this.endTime = endTime;
	}
	
	
}
