
package sososososopy;


import java.util.LinkedList;
import java.util.Queue;

public class MutexLocks 
{
   public String name;
	private boolean state;
	private Process lockedProcess;
	private Queue<Process> waitingProcesses;
	
	public MutexLocks(String name) {
		this.name = name;
		this.setState(false);
		waitingProcesses = new LinkedList<Process>();
	}
	
	public boolean getState()  {
		return state;
	}
	
	public void setState(boolean state)  {
		this.state = state;
	}
	
	public void displayLockedProcess() {
		System.out.println(lockedProcess.get_process_name());
	}
	
	public void lock(Process processToLock) {
		if(!getState()) {
			//zamek jest otwarty, proces zamyka zamek i rusza dalej
			setState(true);
			this.lockedProcess = processToLock;
		} else {
			//zamek jest zamkniety wiec proces wedruje do kolejki i ustawiany jest jego bit blocked
			waitingProcesses.offer(processToLock);
			processToLock.SetBlocked(true);  // to ustawiamy na true zeby wiadomo bylo ze proces jest zablokowany
			processToLock.SetState(1); // to jest metoda od prcesow i nie wiem jeszcze jak oznaczyc proces czekajacy
			
		}
	}
	
	public void unlock(Process processToUnlock) {
		//odblokowuje zamek i jezli kolejka nie jest pusta to zeruje bit blocked pierwszego oczekujacego procesu.
		if(processToUnlock.GetID() == this.lockedProcess.GetID()) {
			this.setState(false);
			this.lockedProcess = null;
			if(!waitingProcesses.isEmpty()) 
                        {
				waitingProcesses.peek().SetState(0); //to jest metoda od prcesow i tu zmieniamy procesowi na szczycie kolejki procesow czekajacych stan na ready
				waitingProcesses.poll().SetBlocked(false); // to ustawiamy na false zeby wiadomo bylo ze proces juz nie jest zablokowany (funkcja poll() zwraca gore kolejki procesow czekajacych i usuwa go z niej)
			}
		}
	} 
    
    
}
