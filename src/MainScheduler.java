import java.util.ArrayList;

public class MainScheduler{
	private Scheduler firstLevelSch = new SJFScheduler();
	private Scheduler secondLevelSch = new RRScheduler(2);
	public static int counter = 0;
	private int interruptCounter = 42;
	
	private ArrayList<Task> tasks = new ArrayList<Task>();	//it's for research purposes 
															//(to write out the waiting times at the end of the app
															//we have to know the given tasks reference)
		
	public void addTask(Task newTask) {
		if(tasks.size() > 10)	//10 taszknál több nem fér be, kilépünk
			return;
		tasks.add(newTask);
	}
	
	public void start() {
		
		int sjf = 1;
		int rr = 1;		
		
		while(interruptCounter > 0){	//if there was 42 cycles without 
										//new task, exit from loop
		
		isTaskArrived();				//arrived new task?
			
		//run it until it has something to run
			while(sjf != 0){
				sjf = firstLevelSch.runTask();
			}
			
			
			isTaskArrived();			//arrived new task?
			rr = secondLevelSch.runTask();
			
			if(sjf == 0 && rr == 0)
				interruptCounter--;			
		}
		stop();
	}
	
	/**
	 * Contains the last time when this method called
	 */
	private int lastcounter = 0;
	/**
	 * When the task arrives, give it to the scheduler.
	 */
	private void isTaskArrived(){
		for(int i = 0; i < tasks.size(); i++){
			Task temp = tasks.get(i);
			if((temp.getStartTime() <= MainScheduler.counter)&& (temp.getCpuBurst() >= lastcounter))
				order(temp);
		}
		lastcounter = MainScheduler.counter;
	}
	/**
	 * Writes to the standard output the order of the tasks.
	 */
	private void stop() {
		System.out.println();				//new line
		tasks.sort(Task.EndTimeComparator); //sorting b end-time
		for(int i = 0; i < tasks.size(); i++){
			Task temp = tasks.get(i);
			System.out.print(temp.getName()+":"+temp.getWaitingTime());
			if(i<tasks.size()-1)
				System.out.print(",");
		}
	}
	/**
	 * Organizes the tasks between the two scheduler.
	 */
	public void order(Task t) {
			if(t.getPriority() > 4)
				secondLevelSch.addTask(t);
			else
				firstLevelSch.addTask(t);		
	}
//	/**
//	 * Puts the given task into the run list
//	 * @param t
//	 */
//	public void run(Task t){
//		run.add(t);
//	}
}
