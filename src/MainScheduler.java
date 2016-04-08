import java.util.ArrayList;

public class MainScheduler implements Scheduler{
	private SJFScheduler firstLevelSch = new SJFScheduler();
	private RRScheduler secondLevelSch = new RRScheduler();
	private ArrayList<Task> tasks = new ArrayList<Task>(10);
	
	private int interruptCounter = 0;
	private ArrayList<Task> run = new ArrayList();
	
	public void addTask(Task newTask) {
		if(tasks.size() > 10)	//10 taszknál több nem fér be, kilépünk
			return;
		tasks.add(newTask);
	}
	@Override
	public Task getNext() {
		if (tasks.size() == 0)
			return null;
		return tasks.get(0);
	}
	
	public void start() {
		order();
		firstLevelSch.order();
		secondLevelSch.order();
		
		while(interruptCounter < 42){	//if there was 42 cycles without 
										//new task, exit
			Task temp1 = firstLevelSch.getNext();
			if(temp1 != null)
				run(temp1);
			Task temp2 = secondLevelSch.getNext();
			if(temp2 != null)
				run(temp2);
			if(temp1 == null && temp2 == null)
				interruptCounter++;
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
		for(int i = 0; i < run.size(); i++){
			Task temp = run.get(i);
			System.out.print(temp.getName()+":"+temp.getWaitingTime());
			if(i<run.size()-1)
				System.out.print(",");
		}
	}
	/**
	 * Organizes the tasks between the two scheduler.
	 */
	public void order() {
		for(int i = 0; i < tasks.size(); i++){
			Task temp = tasks.get(i);
			if(temp.getPriority() > 4)
				secondLevelSch.addTask(temp);
			else
				firstLevelSch.addTask(temp);
		}		
	}
	public void run(Task t){
		run.add(t);
	}
	

}
