package balint.balinthazi;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.StringTokenizer;

public class Main {
	static ArrayList<Task> taskInput= new ArrayList <Task>(); 
	static ArrayList<Task> sjf= new ArrayList <Task>();
	static ArrayList<Task> rr= new ArrayList <Task>();

	public static void main(String[] args) {
		ArrayList<Task> finishedTasks= new ArrayList <Task>();
		ArrayList<Character> cpuOrder= new ArrayList <Character>();

        InputStreamReader isr = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(isr);

         try {
              String line = null;
              while (!(line = br.readLine()).equals("")){
            	  StringTokenizer st = new StringTokenizer(line,",");
            	  taskInput.add(new Task(st.nextToken().charAt(0),Integer.parseInt(st.nextToken()),Integer.parseInt(st.nextToken()),Integer.parseInt(st.nextToken())));
              }
         }
         catch(IOException e){
        	 e.printStackTrace();
         }
         
         int i = 0;
         TaskInputSortTasks(i);
         while(!taskInput.isEmpty()| !sjf.isEmpty() | !rr.isEmpty()) {
        	         	 
        	 if(!sjf.isEmpty()){
         		 Task temp = sjf.get(0);
         		 temp.setStarted(i-temp.getStart());
         		 i += temp.getBurst();
         		 TaskInputSortTasks(i);
         		 cpuOrder.add(temp.getName());
         		 finishedTasks.add(temp);
         		 sjf.remove(temp);
         	 } 
         	 else {
         		if(!rr.isEmpty()){
	         		Task temp = rr.get(0);
	         		temp.setStarted(i-temp.getStart()-temp.getTimeS());
	         		i += 1;
	         		TaskInputSortTasks(i);
	         		cpuOrder.add(temp.getName());
	         		temp.addTime();
	         		
	         		if(temp.ready()){
	         			finishedTasks.add(temp);
	         			rr.remove(temp);
	         		}
	         		else{        				         		
		         		if(!sjf.isEmpty()){
			         		rr.add(temp);
			         		rr.remove(0);
		         		}	         		
		         		else{
		         			i += 1;
		         			TaskInputSortTasks(i);
		         			temp.addTime();
		         			if(temp.ready()){
			         			finishedTasks.add(temp);
			         			rr.remove(temp);
			         		}
		         			else{
		         				rr.add(temp);
				         		rr.remove(0);
		         			}
		         		}
	         		}
	         		
	         		
         		}
         	else{i++;TaskInputSortTasks(i);}
         	}
         }
         
	     for (i =0; i<cpuOrder.size();i++){
	        if(i>0){
	        	if(cpuOrder.get(i-1)!=cpuOrder.get(i))System.out.print(cpuOrder.get(i));
	        }
	        else System.out.print(cpuOrder.get(i));
	     }
	         
	     System.out.print("\n");
	         
	     for (i=0; i<finishedTasks.size();i++){
	    	 if(i>0)
	    		 System.out.print(","+finishedTasks.get(i).getName()+":"+finishedTasks.get(i).getStarted());
	    	 else 
	    		 System.out.print(finishedTasks.get(i).getName()+":"+finishedTasks.get(i).getStarted());
	     }
         
	     return;
		}
	
	
	public static void TaskInputSortTasks(int num){
		for(Task task:taskInput){
       	 if(task.getPrior()<=4 & task.getStart()<=num) {
       		 sjf.add(task);
       	 }
       	 if(task.getPrior()>=5 & task.getStart()<=num){
       		 rr.add(task);
       	 }
   	 } 
   	 
   	 Collections.sort(sjf ,new Comparator<Task>(){
   		 public int compare(Task a, Task b){
   			 return a.getBurst()-b.getBurst();
   		 }
   	 });
   	 
   	 for(Task task : sjf){
   		 taskInput.remove(task);
   	 }
   	 
   	 for(Task task : rr){
   		 taskInput.remove(task);
   	 }
		
	}
}
