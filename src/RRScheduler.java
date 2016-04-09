import java.util.PriorityQueue;

public class RRScheduler implements Scheduler{
	PriorityQueue<Task> tasks;
	int timeSlice;
	
	RRScheduler(int timeslice){
		tasks = new PriorityQueue<Task>();
		timeSlice = timeslice;
	}
	public void addTask(Task newTask) {
		tasks.add(newTask);
	}

	/**
	 * @param counter - the point of time when it called
	 * *return - number of tasks run( 0 - 0 task started run)
	 */
	public int runTask(int counter) {
		if(tasks.isEmpty())
			return 0;	//return if there is no task to run
		
		Task task = tasks.poll();
		
		task.run(2);	//this RR Scheduler is preemptive - 
						//it gives for the task 2 slices of time
						//to run
		if(task.getCpuBurst() <= 0){
			task.setWaitingTime(counter - task.getInitialCpuBurst() + task.getStartTime());//counting waiting time
			task.setEndTime(counter);	//setting the endtime of the task
		}
			
		else if(task.getCpuBurst() > 0)
			addTask(task);	//put back to the end of the queue
		
		return 1;
	}

}
