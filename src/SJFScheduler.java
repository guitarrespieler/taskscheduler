import java.util.ArrayList;

public class SJFScheduler implements Scheduler{
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
	 * *return - number of tasks run( 0 - 0 task started run)
	 */
	public int runTask(int counter) {
		if(tasks.isEmpty())
			return 0;
		Task task = tasks.get(tasks.size()-1);
		int cpuburst = task.getCpuBurst();
		
		task.run(cpuburst);	//this SJF is not preemptive - 
							//the task runs until it is done.		
		return 1;
		
	}

}
