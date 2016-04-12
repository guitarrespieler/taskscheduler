import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;

public class SJFScheduler implements Scheduler{
	PriorityQueue<Task> tasks = new PriorityQueue<Task>(Task.CpuBurstComparator);
	
	/**
	 * Adds a new task to the scheduler's list.
	 * @param newTask - the Task you want to add.
	 */
	public void addTask(Task newTask) {
		tasks.add(newTask);
	}

	/**
	 * *return - name of the running task.
	 */
	public String runTask() {
		if(tasks.isEmpty())
			return "";				//return if there is no task to run
		
		Task task = tasks.poll();	//getting the next task from the end of the queue
									//the queue sorts itself when poll called
									//and the comparator was given to the constructor,
									//so it will do the thing
		
		
		//this SJF is not preemptive - 
		//the task runs until it is done.
		while(task.getCpuBurst() != 0){
			task.run(1);
			MainScheduler.incCounter(); //increments the main counter
		}
				
		if(task.getCpuBurst() == 0){
			task.setWaitingTime(MainScheduler.counter - 	//counting waiting time
					task.getInitialCpuBurst() -
					task.getStartTime());	
			task.setEndTime(MainScheduler.counter);			//setting the endtime of the task
		}
		return task.getName();
	}
	public boolean isEmpty() {
		return tasks.isEmpty();
	}
}