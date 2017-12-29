package sososososopy;
import java.util.LinkedList;
import java.util.List;




public class ProcesManager {
    private int new_ID=0;
    private List<Proces> waiting_normal_processes=new LinkedList<>();
    private List<Proces> ready_normal_processes=new LinkedList<>();
    private List<Proces> waiting_real_processes=new LinkedList<>();
    private List<Proces> ready_real_processes=new LinkedList<>();
    private List<Proces> all_processes=new LinkedList<>();
    
    public ProcesManager() {
        ready_normal_processes.add(new Proces(new_ID, "PB"));
        all_processes.add(new Proces(new_ID, "PB"));
        new_ID++;
        Processor.Add_to_Runnable_proces_Queue(all_processes.get(0).get_PCB());//ref zadziała jak trzeba
        Running.running=all_processes.get(0).get_PCB();//to bedzie ten nasz proces bezczynności
                
    }
    
    public void new_proces(String name, int how_long, String which_file) throws Exception {
        int w=0;
        for(Proces p:all_processes){
            if(p.get_name()==name){
                w=1;
            }
        }
        
        if(w==0){//nazwa się nie powtarza
            Proces p1=new Proces(new_ID, name, how_long, which_file);
            new_ID++;
            
            if(p1.get_base_priority()==0||p1.get_base_priority()==1||p1.get_base_priority()==2){
                ready_real_processes.add(p1);
                all_processes.add(p1);
                Processor.Add_to_Runnable_proces_Queue(p1.get_PCB());
            }
            if(p1.get_base_priority()==3||p1.get_base_priority()==4||p1.get_base_priority()==5){
                ready_normal_processes.add(p1);
                all_processes.add(p1);
                Processor.Add_to_Runnable_proces_Queue(p1.get_PCB());
            }
        }
        else{//że nazwa się powtarza
            throw new Exception("Prosze podac inna nazwe, ta jest juz zajeta!"); 
        }    
    }
    
    
    public void delete_proces(String name) throws Exception {//NO I TUTAJ MILA COŚ JEST ŻLE//jeśli to czytacie znaczy już jest ok
        int w=0;//flaga "czy znaleziono" taki proces
        for(Proces p:all_processes){
            if(p.get_name()==name){
                w=1;//ze znaleziono taki proces
                p.set_state(2);//zakonczony, właściwie mógłbym usunąć i ten stan
                if (p.get_name()=="PB"){//jeśli użytkownik chce usunąć proces bezczynności, to nakrzycz na niego i nic nie rób
                    throw new Exception("Nie wolno krzywdzic procesu bezczynnosci!");
                }   
                else{//jeśli to nie jest proces bezczynności, to pozwól usunąć
                    Processor.Delete_from_Runnable_proces_Queue(p.get_PCB());
                    Zarzadzanie_pamiecia.freeMem();
                    
                    
                 
                    for(Proces p2: ready_normal_processes){
                        if(p2.get_name()==name){
                            ready_normal_processes.remove(p2);
                            break;
                        }
                    }
                    for(Proces p2: waiting_normal_processes){
                        if(p2.get_name()==name){
                            waiting_normal_processes.remove(p2);
                            break;
                        }
                    }
                    for(Proces p2: ready_real_processes){
                        if(p2.get_name()==name){
                            ready_real_processes.remove(p2);
                            break;
                        }
                    }
                    for(Proces p2: waiting_real_processes){
                        if(p2.get_name()==name){
                            waiting_real_processes.remove(p2);
                            break;
                        }
                    }
                    for(Proces p2: all_processes){
                        if(p2.get_name()==name){
                            all_processes.remove(p2);
                            break;
                        }
                    }
                }         
            }
        }
        if(w==0){//że taki proces w ogóle nie istnieje
            throw new Exception("Taki proces nie istnieje");
        }
    }//CZAREK JUŻ BĘDZIE O WSZYSTKIM WIEDZIAŁ
    
    
    
    public int get_state(int ID) throws Exception {
        int w=0;
        for(Proces p:all_processes){
            if(p.get_ID()==ID){
                w=1;
                return p.get_state();//metoda o tej samej nazwie ale PROCESU a nie MENEDŻERA
            }
        }
        if(w==0){//że jednak nie znalazło
            throw new Exception("Nie ma takiego procesu!");//Shell musi powiadomić użytkownika
        }
        return -1;//to i tak nigdy się nie wykona ale bez tego program się czepia
    }
    
    public void set_state(int id, int new_state) throws Exception {
        if(new_state!=0&&new_state!=1&&new_state!=2){//jeśli nowy stan poza zakresem
            throw new Exception("Podano zly nowy stan (podaj 0, 1 lub 2!)");//Shell musi powiadomić użytkownika
        }
        else{//jeśli nowy stan to 0, 1 albo 2 (czyli jakis istniejący)
            //0->1, 0->2, 1->0, 1->2  KAŻDA ZMIANA MOŻLIWA

            for(Proces p:ready_normal_processes){//nie wiem, czy tyle tego musi być, no ale dla pewności niech będzie - zaszkodzic nie może
                if(p.get_ID()==id){
                    p.set_state(new_state);
                    
                    if(new_state==0){}
                    if(new_state==1){
                        waiting_normal_processes.add(p);
                        ready_normal_processes.remove(p);//to nawet nie zadziała więc pozdro600
                        Processor.Delete_from_Runnable_proces_Queue(p.get_PCB());
                        break;//bez tego pętla "out of range"
                    }
                    if(new_state==2){
                        delete_proces(p.get_name());//wywoła też kogo trzeba
                        break;
                    }  
                }
            }
            for(Proces p:waiting_normal_processes){
                if(p.get_ID()==id){
                    p.set_state(new_state);
                    
                    if(new_state==0){
                        ready_normal_processes.add(p);                       
                        waiting_normal_processes.remove(p);
                        Processor.Add_to_Runnable_proces_Queue(p.get_PCB());
                        break;
                    }
                    if(new_state==1){}
                    if(new_state==2){
                        delete_proces(p.get_name());//wywoła też kogo trzeba
                        break;
                    }  
                }
            }
            for(Proces p:ready_real_processes){
                if(p.get_ID()==id){
                    p.set_state(new_state);
                    
                    if(new_state==0){}
                    if(new_state==1){
                        waiting_real_processes.add(p);
                        ready_real_processes.remove(p);
                        Processor.Delete_from_Runnable_proces_Queue(p.get_PCB());
                        break;
                    }
                    if(new_state==2){
                        delete_proces(p.get_name());//wywoła też kogo trzeba
                        break;
                    }  
                }
            }
            for(Proces p:waiting_real_processes){
                if(p.get_ID()==id){
                    p.set_state(new_state);
                    
                    if(new_state==0){
                        ready_real_processes.add(p);                       
                        waiting_real_processes.remove(p);
                        Processor.Add_to_Runnable_proces_Queue(p.get_PCB());
                        break;
                    }
                    if(new_state==1){}
                    if(new_state==2){
                        delete_proces(p.get_name());//wywoła też kogo trzeba
                        break;
                    }  
                }
            }
            for(Proces p:all_processes){//ta lista przechowuje zarówno gotowe jak i czekające
                if(p.get_ID()==id){
                    p.set_state(new_state);
                    if(new_state==0){}
                    if(new_state==1){}
                    if(new_state==2){
                        delete_proces(p.get_name());//wywoła też kogo trzeba
                        break;
                    }  
                }
            }  
        }
    }
    
    
    public PCB get_PCB(int id) throws Exception {
        for(Proces p:all_processes){
            if(p.get_ID()==id){
                return p.get_PCB();//taka sama nazwa metody, ale to juz jest metoda PROCESU a nie MENEDŻERA :D
            }
        }
        throw new Exception("Proces o podanym ID nie istnieje!");
    }
    
    public String show_info(String name){
        for(Proces p:all_processes){
            if(p.get_name()==name){
                return p.show_info();//jak wyżej, ta sama nzwa ale inne argumenty=metoda PROCESU a nie MENEDŻERA
            }
        }
        return "Proces o podanej nazwie nie zostal znaleziony";
    }
    
    public void increase_counters(){//Jędrzej wywołuje za każdym razem, gdy wykona jakiś rozkaz
                                    
        for(Proces p: all_processes){//wszystkie procesy poza running - on nie jest głodzony!!!
            if(p.get_name()!=Running.running.name) {
                p.increase_how_hungry();
                if(p.get_how_hungry()>=5){
                    p.set_how_hungry(p.get_how_hungry()-5);
                    if(p.get_current_priority()==1||p.get_current_priority()==2||p.get_current_priority()==4||p.get_current_priority()==5){//jeśli można prawidłowo zmniejszyć priorytet
                        try{
                            p.set_current_priority(p.get_current_priority()-1);//zmiana poza przedział nic nie zrobi
                        }
                        catch(Exception e){}//Po prostu się nie wykona, ja nie mogę nic wyswietlić a poza tym to i tak byłby mój błąd w kodzie, a nie błąd użytkownika
                    }
                }
            }
        }
    }      
    
        
    public Proces get_proces(String name) throws Exception {
        for(Proces p:all_processes){
            if(p.get_name()==name){
                return p;
            }
        }
        throw new Exception("Nie ma takiego procesu!");
    }
    
    
    
}
