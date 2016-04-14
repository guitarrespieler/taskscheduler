import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	public static void main(String args[]){
		
		MainScheduler utemezo = new MainScheduler();
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		String s;
		
		try {
			while((s = br.readLine()) != null && s.length() != 0){
				String[] strings = s.split(",");
				String name = strings[0];
				int prior = Integer.parseInt(strings[1]);
				int starttime = (Integer.parseInt(strings[2])) + 1;
				int burst = Integer.parseInt(strings[3]);
				utemezo.addTask(new Task(name,prior,starttime,burst));				
			}
		} catch (NumberFormatException e) {
			e.printStackTrace();
			System.out.println("There went something wrong during parsing number. Maybe wrong input.");
		} catch (IOException e) {
			e.printStackTrace();
		}
		utemezo.start();		
	}
}
