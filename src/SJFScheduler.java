import java.util.ArrayList;

public class SJFScheduler implements Scheduler{
	ArrayList<Task> tasks = new ArrayList<Task>();
	
	/**
	 * Adds a new task to the scheduler's list.
	 * @param newTask - the Task you want to add.
	 */
	public void addTask(Task newTask) {
		tasks.add(newTask); 				//put it into the list
		tasks.sort(Task.CpuBurstComparator);//shorting the list
	}

	/**
	 * @return - name of the running task.
	 */
	public String runTask() {
		if(tasks.isEmpty())
			return "";				//return if there is no task to run
		
		Task task = poll();	//getting the next task from the end of the queue
									//the queue sorts itself when addTask called
				
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
	/**
	 * Own poll method to get the last element
	 * @return
	 */
	private Task poll(){
		Task temp = tasks.get(0);
		tasks.remove(temp);
		return temp;
	}
}