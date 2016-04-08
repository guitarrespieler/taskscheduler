
public interface Scheduler {
	public void addTask(Task newTask);
	public void order();
	public int runTask(int counter);
}
