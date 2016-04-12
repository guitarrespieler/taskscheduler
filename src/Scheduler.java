
public interface Scheduler {
	public void addTask(Task newTask);
	public int runTask();
	public boolean isEmpty();
}
