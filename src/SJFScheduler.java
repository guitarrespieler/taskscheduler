import java.util.ArrayList;
import java.util.PriorityQueue;

public class SJFScheduler implements Scheduler{
	PriorityQueue<Task> tasks = new PriorityQueue<Task>(Task.CpuBurstComparator);
	
	public void addTask(Task newTask) {
		tasks.add(newTask);
	}

	/**
	 * *return - number of tasks run( 0 - 0 task started run)
	 */
	public int runTask(int counter) {
		if(tasks.isEmpty())
			return 0;	//return if there is no task to run
		Task task = tasks.poll();	//getting the next task from the end of the queue
									//the queue sort itself when poll called, 
									//comparator was given to the ctor, so it does the work.
				
		task.run(task.getCpuBurst());	//this SJF is not preemptive - 
										//the task runs until it is done.
		if(task.getCpuBurst() == 0)
			task.setWaitingTime(counter - task.getInitialCpuBurst() + task.getStartTime());
		return 1;
	}
}