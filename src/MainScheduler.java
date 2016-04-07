import java.util.ArrayList;

public class MainScheduler implements Scheduler{
	private SJFScheduler firstLevelSch = new SJFScheduler();
	private RRScheduler secondLevelSch = new RRScheduler();
	private ArrayList<Task> readyTasks = new ArrayList<Task>(10);

	public void addTask(Task newTask) {
		if(readyTasks.size() > 10)	//10 taszknál több nem fér be, kilépünk
			return;
		readyTasks.add(newTask);
	}
	@Override
	public Task getNext() {
		if (readyTasks.size() == 0)
			return null;
		return readyTasks.get(0);
	}
	
	

}
