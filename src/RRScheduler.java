import java.util.ArrayList;

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
		
		Task task = tasks.remove(0);
		int x = timeSlice;
		while(x != 0){
			task.run(1);
			MainScheduler.counter++;
			x--;
			if(task.getCpuBurst()== 0){
				task.setWaitingTime(MainScheduler.counter - 	//counting waiting time
						task.getInitialCpuBurst() -
						task.getStartTime());	
				task.setEndTime(MainScheduler.counter);			//setting the endtime of the task
				returnvalue = returnvalue + task.getName();
				
				if(x == 0){ // && task.getCpuBurst()== 0
					MainScheduler.isTaskArrived();
					return returnvalue;
				}
				if(!tasks.isEmpty() && x != 0){// && task.getCpuBurst()== 0
					task = tasks.remove(0);	//one more task to run, we have some time!
					int y = 2;
					while(y != 0)
					{
						task.run(1);
						MainScheduler.counter++;
						y--;
						
						if(task.getCpuBurst() == 0){
							task.setWaitingTime(MainScheduler.counter - 	//counting waiting time
									task.getInitialCpuBurst() -
									task.getStartTime());	
							task.setEndTime(MainScheduler.counter);			//setting the endtime of the task
							MainScheduler.isTaskArrived();
							return returnvalue + task.getName();
						}
						if(task.getCpuBurst() != 0 && y == 0){
							tasks.add(task);	
							MainScheduler.isTaskArrived();
							return returnvalue + task.getName();
						}
						MainScheduler.isTaskArrived();
					}															
					return returnvalue + task.getName();
				}					
				else if(tasks.isEmpty() && x != 0)// && task.getCpuBurst()== 0
					return returnvalue;
			}
			if((task.getCpuBurst() > 0) && (x == 0)){
				tasks.add(task);								//put it back to the queue
				MainScheduler.isTaskArrived();
				return returnvalue + task.getName();
			}			
		}		
		return returnvalue;
	}
	public boolean isEmpty() {
		return tasks.isEmpty();
	}

}
