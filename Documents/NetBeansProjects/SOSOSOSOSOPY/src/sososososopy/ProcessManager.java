package sososososopy;
import java.util.LinkedList;
import java.util.List;

public class ProcessManager {
    private int new_ID;
    private List<Process> waiting_normal_processes=new LinkedList<>();
    private List<Process> ready_normal_processes=new LinkedList<>();
    private List<Process> waiting_real_processes=new LinkedList<>();
    private List<Process> ready_real_processes=new LinkedList<>();
    private List<Process> all_processes=new LinkedList<>();
    
    public ProcessManager() {
        new_ID=0;
        ready_normal_processes.add(new Process(new_ID, "PB"));
        all_processes.add(new Process(new_ID, "PB"));
        new_ID++;
        //CZAREK.Add_to_Runnable_proces_Queue(ready_normal_processes.get(0).get_PCB());
        //Running=ready_normal_processes.get(0);
                
    }
    
    public void new_process(String name, int how_long, String which_file){
        int w=0;
        for(Process p:all_processes){
            if(p.get_process_name()==name){
                w=1;
            }
        }
        Process p1=new Process(new_ID, name, how_long, which_file);
        new_ID++;
        
        if(w==0){
            /////////////////////////////
            if(p1.get_base_priority()==0||p1.get_base_priority()==1||p1.get_base_priority()==2){
                ready_real_processes.add(p1);
                all_processes.add(p1);
                //CZAREK.Add_to_Runnable_proces_Queue(p1.get_PCB());
            }
            if(p1.get_base_priority()==3||p1.get_base_priority()==4||p1.get_base_priority()==5){
                ready_normal_processes.add(p1);
                all_processes.add(p1);
                //CZAREK.Add_to_Runnable_proces_Queue(p1.get_PCB());
            }
        }
        else{//nazwa się powtarza
            delete_process(p1.get_process_name());//Czarek musi być gotowy na to, że tego procesu u niego nie ma-nie może być, bo wtedy on ma dwa o tej samej nazwie -> któy on usunie? nie wiem, ale usuwa właśnie po nazwie
            //EWA.wyswietl("Proces o takiej nazwie już istnieje"); 
        }    
    }
    
    public void delete_process(String name){
        Process p1=new Process(0,"",0,"");//proces taki nie będzie nigdzie, bo priorytet zero jest dla bezczynności, a on ma inne pozostałe parametry
        for(Process p:all_processes){
            if(p.get_process_name()==name){
                p1=new Process(p.get_ID(), p.get_process_name(), p.get_how_long(), p.get_which_file());
            }
        }
        if (p1.get_process_name()=="PB"){
            //EWA.wyswietl("Nie pozwole na usuniecie procesu bezczynnosci");
        }   
        else{//jeśli to nie jest proces bezczynności, to pozwól usunąć
            //CZAREK.Take_off_Runnable_proces_Queue(p1.get_PCB());
            //FILIP.freeMem();
            for(Process p: ready_normal_processes){
                if(p.get_process_name()=="name"){
                    ready_normal_processes.remove(p);
                }
            }
            for(Process p: waiting_normal_processes){
                if(p.get_process_name()=="name"){
                    waiting_normal_processes.remove(p);
                }
            }
            for(Process p: ready_real_processes){
                if(p.get_process_name()=="name"){
                    ready_real_processes.remove(p);
                }
            }
            for(Process p: waiting_real_processes){
                if(p.get_process_name()=="name"){
                    waiting_real_processes.remove(p);
                }
            }
            for(Process p: all_processes){
                if(p.get_process_name()=="name"){
                    all_processes.remove(p);
                }
            }
        }
    }//CZAREK JUŻ BĘDZIE O WSZYSTKIM WIEDZIAŁ
    
    public int get_process_state(int ID){
        for(Process p:all_processes){
            if(p.get_ID()==ID){
                return p.get_process_state();
            }
        }
        //EWA.wyswietl("Proces o podanym ID nie zostal znaleziony");
        return 2;//chętniej dałbym tu np. -5 ale innym może nie działać
    }
    
    public void set_process_state(int id, int new_state){
        if(new_state!=0&&new_state!=1&&new_state!=2){
            //EWA.wyswietl("Podano zly numer stanu");
        }
        else{
            for(Process p:ready_normal_processes){
                if(p.get_ID()==id){
                    p.set_process_state(new_state);
                    
                    if(new_state==0){}
                    if(new_state==1){
                        waiting_normal_processes.add(p);
                        ready_normal_processes.remove(p);
                        //CZAREK.Take_off_Runnable_proces_Queue(p.get_PCB());
                    }
                    if(new_state==2){
                        delete_process(p.get_process_name());//wywoła też kogo trzeba
                    }  
                }
            }
            for(Process p:waiting_normal_processes){
                if(p.get_ID()==id){
                    p.set_process_state(new_state);
                    
                    if(new_state==0){
                        ready_normal_processes.add(p);                       
                        waiting_normal_processes.remove(p);
                        //CZAREK.Add_to_Runnable_proces_Queue(p.get_PCB());

                    }
                    if(new_state==1){}
                    if(new_state==2){
                        delete_process(p.get_process_name());//wywoła też kogo trzeba
                    }  
                }
            }
            for(Process p:ready_real_processes){
                if(p.get_ID()==id){
                    p.set_process_state(new_state);
                    
                    if(new_state==0){}
                    if(new_state==1){
                        waiting_real_processes.add(p);
                        ready_real_processes.remove(p);
                        //CZAREK.Take_off_Runnable_proces_Queue(p.get_PCB());
                    }
                    if(new_state==2){
                        delete_process(p.get_process_name());//wywoła też kogo trzeba
                    }  
                }
            }
            for(Process p:waiting_real_processes){
                if(p.get_ID()==id){
                    p.set_process_state(new_state);
                    
                    if(new_state==0){
                        ready_real_processes.add(p);                       
                        waiting_real_processes.remove(p);
                        //CZAREK.Add_to_Runnable_proces_Queue(p.get_PCB());
                    }
                    if(new_state==1){}
                    if(new_state==2){
                        delete_process(p.get_process_name());//wywoła też kogo trzeba
                    }  
                }
            }
            for(Process p:all_processes){//ta lista przechowuje zarówno gotowe jak i czekające
                if(p.get_ID()==id){
                    p.set_process_state(new_state);
                    if(new_state==0){}
                    if(new_state==1){}
                    if(new_state==2){
                        delete_process(p.get_process_name());//wywoła też kogo trzeba
                    }  
                }
            }  
        }
    }
    
    public void running_change(PCB pcb){//???WSZYSCY po każdej zmianie running muszą mi przesłać jego blok, bym go uaktualnił np. running wykonany już do połowy, potem każą MI zmienić jej stan na waiting - usuwam ją od Czarka
        for(Process p:ready_normal_processes){
            if(p.get_ID()==pcb.ID){
                p.set_PCB(pcb);
            }
        }
        for(Process p:waiting_normal_processes){
            if(p.get_ID()==pcb.ID){
                p.set_PCB(pcb);
            }
        }
        for(Process p:ready_real_processes){
            if(p.get_ID()==pcb.ID){
                p.set_PCB(pcb);
            }
        }
        for(Process p:waiting_real_processes){
            if(p.get_ID()==pcb.ID){
                p.set_PCB(pcb);
            }
        }
        for(Process p:all_processes){
            if(p.get_ID()==pcb.ID){
                p.set_PCB(pcb);
            }
        }
    }
    
    public PCB get_PCB(int id){
        for(Process p:all_processes){
            if(p.get_ID()==id){
                return p.get_PCB();
            }
        }
        return all_processes.get(0).get_PCB();//jak nie znajdzie to zwróci blok procesu bezczynności - żeby FILIPowi nie skraszowało
    }
    
    public String show_info(String name){
        for(Process p:all_processes){
            if(p.get_process_name()==name){
                return p.show_info();
            }
        }
        return "Proces o podanej nazwie nie zostal znaleziony";
    }
    
    public void increase_counters(){
        
        for(Process p: ready_normal_processes){//wszędzie -> poza running! Ona nie jest głodzona! I dawać Czarkowi znać, że trzeba zaktualizować
            //if(p.get_process_name()!=Running.get_process_name()) {
                
                //p.increase_counter();
                //if(p.get_counter()=>5){
                    //p.set_counter(p.get_counter()-5);
                    //p.set_priority(p.get_current_priority()-1);//jak za bardzo by zmieniło (poza przedział) to nic się nie wykona
                    //CZAREK.Add_to_Runnable_proces_Queue(p.get_PCB());//No Czarek musi się zorientować, że zmienił się priorytet -> ON MUSI TO ZAKTUALIZOWAC
                //}
            //}
        }
        for(Process p: waiting_normal_processes){
            //if(p.get_process_name()!=Running.get_process_name()) {
                //p.increase_counter();
                //if(p.get_counter()=>5){
                    //p.set_counter(p.get_counter()-5);
                    //p.set_priority(p.get_current_priority()-1);//jak za bardzo by zmieniło (poza przedział) to nic się nie wykona
                    //CZAREK.Add_to_Runnable_proces_Queue(p.get_PCB());//No Czarek musi się zorientować, że zmienił się priorytet -> ON MUSI TO ZAKTUALIZOWAC
                //}
            //}
        }
        for(Process p: ready_real_processes){
            //if(p.get_process_name()!=Running.get_process_name()) {
                //p.increase_counter();
                //if(p.get_counter()=>5){
                    //p.set_counter(p.get_counter()-5);
                    //p.set_priority(p.get_current_priority()-1);//jak za bardzo by zmieniło (poza przedział) to nic się nie wykona
                    //CZAREK.Add_to_Runnable_proces_Queue(p.get_PCB());//No Czarek musi się zorientować, że zmienił się priorytet -> ON MUSI TO ZAKTUALIZOWAC
                //}
            //}
        }
        for(Process p: waiting_real_processes){
            //if(p.get_process_name()!=Running.get_process_name()) {
                //p.increase_counter();
                //if(p.get_counter()=>5){
                    //p.set_counter(p.get_counter()-5);
                    //p.set_priority(p.get_current_priority()-1);//jak za bardzo by zmieniło (poza przedział) to nic się nie wykona
                    //CZAREK.Add_to_Runnable_proces_Queue(p.get_PCB());//No Czarek musi się zorientować, że zmienił się priorytet -> ON MUSI TO ZAKTUALIZOWAC
                //}
            //}
        }
        for(Process p: all_processes){
            //if(p.get_process_name()!=Running.get_process_name()) {
                //p.increase_counter();
                //if(p.get_counter()=>5){
                    //p.set_counter(p.get_counter()-5);
                    //p.set_priority(p.get_current_priority()-1);//jak za bardzo by zmieniło (poza przedział) to nic się nie wykona
                    //CZAREK.Add_to_Runnable_proces_Queue(p.get_PCB());//No Czarek musi się zorientować, że zmienił się priorytet -> ON MUSI TO ZAKTUALIZOWAC
                //}
            //}
        }        
    }
    
    public Process get_process(String name){
        for(Process p:all_processes){
            if(p.get_process_name()==name){
                return p;
            }
        }
        return new Process(0, "", 0, "");
    }
    
    
    
}
