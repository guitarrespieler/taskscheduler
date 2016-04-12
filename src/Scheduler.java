
public interface Scheduler {
	public void addTask(Task newTask);
	public String runTask();
	public boolean isEmpty();
}
