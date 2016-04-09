import java.util.ArrayList;

public class RRScheduler implements Scheduler{
	ArrayList<Task> tasks = new ArrayList(10);
	@Override
	public void addTask(Task newTask) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void order() {
		// TODO Auto-generated method stub
		
	}

	/**
	 * @param counter - the point of time when it called
	 * *return - number of tasks run( 0 - 0 task started run)
	 */
	public int runTask(int counter) {
		if(tasks.isEmpty())
			return 0;	//return if there is no task to run
		
		Task task = tasks.get(tasks.size()-1);
		
		task.run(2);	//this RR Scheduler is preemptive - 
						//it gives for the task 2 slices of time
						//to run
		if(task.getCpuBurst() == 0)
			task.setWaitingTime(counter - task.getInitialCpuBurst() + task.getStartTime());//counting waiting time
		return 1;
	}

}
