import java.util.ArrayList;

public class MainScheduler implements Scheduler{
	private SJFScheduler firstLevelSch = new SJFScheduler();
	private RRScheduler secondLevelSch = new RRScheduler();
	private ArrayList<Task> tasks = new ArrayList<Task>(10);

	public void addTask(Task newTask) {
		if(tasks.size() > 10)	//10 taszknál több nem fér be, kilépünk
			return;
		tasks.add(newTask);
	}
	@Override
	public Task getNext() {
		if (tasks.size() == 0)
			return null;
		return tasks.get(0);
	}
	@Override
	public void start() {
		rendez();
		
		while(true)
			;
		
	}
	@Override
	public void rendez() {
		for(int i = 0; i < tasks.size(); i++){
			Task temp = tasks.get(i);
			if(temp.getPriority() > 4)
				secondLevelSch.addTask(temp);
			else
				firstLevelSch.addTask(temp);
		}
			
		
	}
	
	

}
