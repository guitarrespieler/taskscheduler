import java.util.ArrayList;

public class MainScheduler{
	private Scheduler firstLevelSch = new SJFScheduler();
	private Scheduler secondLevelSch = new RRScheduler();
	private int counter = 0;
	
	private ArrayList<Task> tasks = new ArrayList<Task>(10);
	
	private Task idle = new Task("Idle",0,0,42);
	private ArrayList<Task> run = new ArrayList<Task>();
	
	public void addTask(Task newTask) {
		if(tasks.size() > 10)	//10 taszknál több nem fér be, kilépünk
			return;
		order(newTask);
	}
	
	public void start() {
		
		firstLevelSch.order();
		secondLevelSch.order();
		
		while(idle.getCpuBurst() > 0){	//if there was 42 cycles without 
										//new task, exit from loop
			int sjf = firstLevelSch.runTask();
			
			int rr = secondLevelSch.runTask();
			
			if(sjf == 0 && rr == 0)
//				idle.run();					//There is nothing to run, call idle task
		}
		stop();
	}

	/**
	 * Writes to the standard output the order of the tasks.
	 */
	private void stop() {
		for(int i = 0; i < run.size(); i++)
			System.out.print(run.get(i).getName());
		System.out.println();
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
	/**
	 * Puts the given task into the run list
	 * @param t
	 */
	public void run(Task t){
		run.add(t);
	}
}
