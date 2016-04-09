import java.util.ArrayList;

public class SJFScheduler implements Scheduler{
	ArrayList<Task> tasks = new ArrayList();
	@Override
	public void addTask(Task newTask) {
		// TODO Auto-generated method stub
	}

	@Override
	public void order() {
		// TODO Auto-generated method stub
		
	}

	/**
	 * *return - number of tasks run( 0 - 0 task started run)
	 */
	public int runTask(int counter) {
		if(tasks.isEmpty())
			return 0;	//return if there is no task to run
		Task task = tasks.get(tasks.size()-1);//getting the next task
				
		task.run(task.getCpuBurst());	//this SJF is not preemptive - 
							//the task runs until it is done.
		if(task.getCpuBurst() == 0)
			task.setWaitingTime(counter - task.getInitialCpuBurst() + task.getStartTime());
		return 1;
	}
}