
public interface Scheduler {
	public void addTask(Task newTask);
	public Task getNext();
	public void start();
	public void rendez();
}
