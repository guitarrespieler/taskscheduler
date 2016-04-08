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
	 * *return - number of tasks run( 0 - 0 task started run)
	 */
	public int runTask(int counter) {
		if(tasks.isEmpty())
			return 0;
		return 1;
		
	}

}
