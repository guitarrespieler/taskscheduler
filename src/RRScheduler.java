import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class RRScheduler implements Scheduler{
	ArrayList<Task> tasks;
	int timeSlice;
	
	RRScheduler(int timeslice){
		tasks = new ArrayList<Task>();
		timeSlice = timeslice;
	}
	public void addTask(Task newTask) {
		tasks.add(newTask);		
	}

	/**
	 * *return - name of the running task
	 */
	public String runTask() {
		String returnvalue = "";
		if(tasks.isEmpty())
			return returnvalue;									//return if there is no task to run
		
		Task task = tasks.get(0);
		
//		int cpuburstTemp = task.getCpuBurst();
//		if (cpuburstTemp >= timeSlice){
//			int i = timeSlice;
//			while(i != 0){
//				task.run(1);
//				MainScheduler.incCounter();
//				i--;
//			}
//			if(task.getCpuBurst() > 0)
//				tasks.addLast(task);							//put it back to the queue
//			if(task.getCpuBurst() == 0){
//				task.setWaitingTime(MainScheduler.counter - 	//counting waiting time
//						task.getInitialCpuBurst() -
//						task.getStartTime());	
//				task.setEndTime(MainScheduler.counter);			//setting the endtime of the task
//			}
//		}
//		else if(cpuburstTemp < timeSlice){
			int x = timeSlice;
			while(x != 0){
				task.run(1);
				MainScheduler.incCounter();
				x--;
				if(task.getCpuBurst()== 0 && x != 0){
					task.setWaitingTime(MainScheduler.counter - 	//counting waiting time
							task.getInitialCpuBurst() -
							task.getStartTime());	
					task.setEndTime(MainScheduler.counter);			//setting the endtime of the task
					returnvalue = returnvalue + task.getName();
					
					task = tasks.get(0);//one more task to run, we have some time!
				}
				if(task.getCpuBurst() > 0 && x == 0){
					tasks.add(task);							//put it back to the queue
					returnvalue = returnvalue + task.getName();
				}					
			}
//		}
		
		return returnvalue;
	}
	public boolean isEmpty() {
		return tasks.isEmpty();
	}

}
