import java.util.LinkedList;
import java.util.PriorityQueue;

public class SJFScheduler implements Scheduler{
	LinkedList<Task> tasks = new LinkedList<Task>();
	
	public void addTask(Task newTask) {
		tasks.add(newTask);
		tasks.sort(Task.CpuBurstComparator);
	}

	/**
	 * *return - number of tasks run( 0 - 0 task started run)
	 */
	public int runTask() {
		if(tasks.isEmpty())
			return 0;				//return if there is no task to run
		
		Task task = tasks.poll();	//getting the next task from the end of the queue
									//the queue sort itself when poll called, 
									//comparator was given to the ctor, so it does the work.
		
		int cpuBurstTemp = task.getCpuBurst();
		
		//this SJF is not preemptive - 
		//the task runs until it is done.
		while(task.getCpuBurst() != 0){
			task.run(1);
			MainScheduler.incCounter(); //increments the main counter
		}
			
		
		MainScheduler.counter+= cpuBurstTemp;
		
		if(task.getCpuBurst() == 0){
			task.setWaitingTime(MainScheduler.counter - 	//counting waiting time
					task.getInitialCpuBurst() -
					task.getStartTime());	
			task.setEndTime(MainScheduler.counter);			//setting the endtime of the task
		}
		return 1;
	}
	public boolean isEmpty() {
		return tasks.isEmpty();
	}
}