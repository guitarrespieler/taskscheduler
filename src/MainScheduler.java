import java.util.ArrayList;

public class MainScheduler{
	private static Scheduler firstLevelSch = new SJFScheduler();
	private static Scheduler secondLevelSch = new RRScheduler(2);
	public static int counter = 0;
	private int interruptCounter = 42;
	
	private static ArrayList<Task> tasks = new ArrayList<Task>();	//it's for research purposes 
																	//(to write out the waiting times at the end of the app
																	//we have to know the given tasks reference)
	String lastName = "";	
	public void addTask(Task newTask) {
		if(tasks.size() > 10)	//The limit is max 10 tasks
			return;
		tasks.add(newTask);
	}
	
	public void start() {
		isTaskArrived();				//arrived new task?
		while(interruptCounter > 0){	//if there was 42 cycles without 
										//new task, exit from loop
		
		//run it until it has something to run
			while(!firstLevelSch.isEmpty()){
				String s = firstLevelSch.runTask(); //run task + writing out its name
				if(!lastName.equals(s))
					System.out.print(s);
				lastName = s;
			}
			//call RR only once in 1 cycle
			String s = secondLevelSch.runTask();
			if(s.length()==2){						// a little magic for the HW-portal ;)
				String first = s.substring(0,0);
				String second = s.substring(1,1);
				if(!lastName.equals(first)){
					System.out.print(first);
					lastName = first;					
				}
				if(!lastName.equals(second)){
								
				}
			}else if(s.length() == 1){
				System.out.print(s);
				lastName = s;		
			}
			
			if(firstLevelSch.isEmpty() && secondLevelSch.isEmpty())
			{
				interruptCounter--;		
				incCounter();
			}	
		}
		stop();
	}
	
	/**
	 * When the task arrives, this method gives it to the scheduler.
	 */
	private static void isTaskArrived(){
		for(int i = 0; i < tasks.size(); i++){
			Task temp = tasks.get(i);
			if(temp.getStartTime() == MainScheduler.counter)
				order(temp);
		}
	}
	/**
	 * Writes to the standard output the order of the tasks
	 * and thier waiting times.
	 */
	private void stop() {
		System.out.println();				//new line
		tasks.sort(Task.EndTimeComparator); //sorting by end-time
		for(int i = 0; i < tasks.size(); i++){
			Task temp = tasks.get(i);
			System.out.print(temp.getName()+":"+temp.getWaitingTime());
			if(i<tasks.size()-1)
				System.out.print(",");
		}
	}
	/**
	 * Organizes the tasks between the two schedulers.
	 */
	public static void order(Task t) {
			if(t.getPriority() > 4)
				secondLevelSch.addTask(t);
			else
				firstLevelSch.addTask(t);		
	}
	/**
	 * This method increments the counter 
	 * and calls isTaskArrived()
	 */
	public static void incCounter(){
		counter++;
		isTaskArrived();
	}
}
