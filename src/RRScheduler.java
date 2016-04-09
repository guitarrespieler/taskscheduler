import java.util.LinkedList;
import java.util.Queue;

public class RRScheduler implements Scheduler{
	Queue<Task> tasks;
	int timeSlice;
	
	RRScheduler(int timeslice){
		tasks = new LinkedList<Task>();
		timeSlice = timeslice;
	}
	public void addTask(Task newTask) {
		tasks.add(newTask);
	}

	/**
	 * @param counter - the point of time when it called
	 * *return - number of tasks run( 0 - 0 task started run)
	 */
	public int runTask() {
		if(tasks.isEmpty())
			return 0;						//return if there is no task to run

		Task task = tasks.poll();
		
		int cpuburstTemp = task.getCpuBurst();
		if (cpuburstTemp >= timeSlice){
			task.run(timeSlice);			//this RR Scheduler is preemptive - 
											//it gives for the task 2 slices of time
											//to run
			MainScheduler.counter+= timeSlice;
		}
		else if(cpuburstTemp < timeSlice){
//			if(tasks.isEmpty()){
				task.run(cpuburstTemp);		
				MainScheduler.counter+= cpuburstTemp;	
//			}
//			else if(!tasks.isEmpty()){
//				this.addTask(task);			//put it back to the end of the queue,don't run
//				return 0;
//			}
		}
		if(task.getCpuBurst() == 0){
			task.setWaitingTime(MainScheduler.counter - 	//counting waiting time
					task.getInitialCpuBurst() +
					task.getStartTime());	
			task.setEndTime(MainScheduler.counter);			//setting the endtime of the task
		}
			
		else if(task.getCpuBurst() > 0)
			addTask(task);									//put back to the end of the queue
		
		return 1;
	}

}
