package sososososopy;
import java.util.LinkedList;

public class Communication {

	private LinkedList<Queue> queues;
	
	
	public Communication()
	{
		queues = new LinkedList<Queue>();
	}
	
	
	public Boolean isQueue(String name)
	{
		for (Queue x : queues)
		{
			if(x.get_process_name() == name)
				return true;
		}
		return false;
	}
	
	public Queue issQueue(String name)
	{
		Queue q1 = null;
		for (Queue x : queues)
		{
			if(x.get_process_name() == name)
				return x;
		}
		return q1;
	}
	
	
	public Queue createQueue(String name)
	{
		if(isQueue(name))
		{
			System.out.println("Potok o danej nazwie juz istnieje!");
			return null;
		}
		Queue que = new Queue(name);
		queues.push(que);
		
		System.out.println("Stworzono potok o danej nazwie " + name + "!");
		return que;
	}
	
	
	public void deleteQueue(String name)
	{
		for (Queue x : queues)
		{
			if(x.get_process_name() == name)
			{
				System.out.println("Usunieto potok o danej nazwie " + x.get_process_name() + "!");
				queues.remove(x);
				return;
			}
		}
		System.out.println("Potok o danej nazwie nie istnieje!");
	}
	
	
	public void write(Process writer, Process reader, String msg)
	{
		
		if(issQueue(reader.get_process_name()) == null)
		{
			
			Queue q1 = createQueue(reader.get_process_name());
			writer.lock();
			//reader.lock();
			q1.getFifo().push(msg);
			queues.push(q1);
		}
		else
		{
			Queue q1 = issQueue(reader.get_process_name());
			writer.lock();
			//this.reader.lock();
			//queues.remove(q1);
			q1.getFifo().push(msg);
			//queues.push(q1);
		}
	}
	
	
	public void read(Proces writer, Proces reader, String msg)
	{
		//this.reader = reader;
		//this.writer = writer;
		//this.writer.setStatement() = msg;
		
		if(issQueue(reader.get_process_name()) == null)
		{
			System.out.println("Nie mozna czytac z kolejki ktora nie istnieje!");
			return;
		}
		
		Queue q1 = issQueue(reader.get_process_name());
		reader.lock();
		//writer.lock();
		reader.setStatement(q1.getFifo().getFirst());
		System.out.println("Proces " + reader.get_process_name() + " odczytal wiadomosc: " + q1.getFifo().getFirst());
		q1.getFifo().pop();
		
		if(q1.getFifo().isEmpty())
		{
			queues.remove(q1);
		}
		
	}
}
