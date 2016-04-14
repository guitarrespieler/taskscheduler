package balint.balinthazi;

public class Task {
	private char name;
	private int prior;
	private int tstart;
	private int cpub;
	private int tspent;
	private int started=0;
	
	public Task(){name = ' ';prior = 0;tstart=0;cpub=0;}
	
	public Task(char n,int p, int t, int c){name=n;prior=p;tstart=t;cpub=c;}
	
	public char getName() {return name;}
	
	public int getPrior() {return prior;}
	
	public int getStart() {return tstart;}

	public int getBurst() {return cpub;}
	
	public void addTime() {tspent+=1;}
	
	public int getTimeS() {return tspent;}
	
	public boolean ready() {return tspent == cpub;}
	public boolean nowStarted() {return tspent == 0;}
	
	public void setStarted(int num) {started = num;}
	public int getStarted() { return started;}
	
}