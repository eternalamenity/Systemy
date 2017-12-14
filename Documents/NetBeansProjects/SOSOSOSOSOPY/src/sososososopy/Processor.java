package sososososopy;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Random;
import java.util.Scanner;

/**
 *
 * @author Cezary
 */
    public class Processor {
//trzeba przerobic na arraylist
  int target_latency=20;
int minimum_granularity = 2; 
     public LinkedList<PCB> Runnable_proces_Queue = new LinkedList();  // Tablica kolejek dla zadań aktywnych
   
     void boringmode(PCB ref){ref.state="Running";}
    
    void Add_to_Runnable_proces_Queue(PCB ref){

     //priorytety od 0 do 5
			
                        if(Runnable_proces_Queue.isEmpty())
                        {
                            ref.vruntime=20;//
                            Runnable_proces_Queue.add(ref);
                        
                        }else {
                              //Collections.sort(Runnable_proces_Queue,new Priority_Comparator());
                              ref.vruntime= Runnable_proces_Queue.get(Runnable_proces_Queue.size()-1).vruntime;
                            Runnable_proces_Queue.add(ref);
                    
                        }
                         Collections.sort(Runnable_proces_Queue,new Priority_Comparator());
                         
		if(Runnable_proces_Queue.get(0).state!="Running"){
                       Runnable_proces_Queue.get(0).state="Running";
                         Running.running=Runnable_proces_Queue.get(0); //wybrany_przez_procesor do zmiennej globalnej
                }
                
                System.out.println("cała kolejka procesow gotowych po dodaniu nowego PCB");
                
                for(Iterator<PCB> it=Runnable_proces_Queue.iterator();it.hasNext();)
                 {
                     PCB elem=it.next();
                     System.out.println(elem.name+" vruntime:"+elem.vruntime+" stan:"+elem.state+" liczba wyk rozk"+elem.liczba_wyk_rozk);
                 }
        }

   void scheduler(){ // jesli przekroczy czas 2  
      System.out.println("wywolanie schedulera");//zmiana vruntime dla calej kolejki gotowych, ew wywlaszczenie
       if(!Runnable_proces_Queue.isEmpty()){//try catch i uwzglednij max granulality

     
      
           int prior=0;//tzn w przykladzie (6-3)+(6-2)+(6-5)=8      
           PCB zm=Collections.max(Runnable_proces_Queue, new Current_Priority_cmp());
                  int a=1+zm.current_priority;//Runnable_proces_Queue.get(Runnable_proces_Queue.size()-1).current_priority;
      for(int i=0;i<Runnable_proces_Queue.size();++i){

              
              int b=Runnable_proces_Queue.get(i).current_priority;
              prior+=a-b;
            
      }  //System.out.println("Prior:"+prior);
      
      //wlasciwe liczenie vruntime
          Collections.sort(Runnable_proces_Queue,new Priority_Comparator());

               //obliczanie vruntime
                for(int i=0;i<Runnable_proces_Queue.size();++i){
                //(najwiekszy priorytet+1-najmniejszy)*(20/prior) zaokroglone do inta
                  int b=Runnable_proces_Queue.get(i).current_priority;
                    // System.out.println("a:"+a+"  b:"+b);
                      Runnable_proces_Queue.get(i).vruntime=(int)Math.round((a-b)*(20.0/prior));//Czy tak można?
                     //  System.out.println(Runnable_proces_Queue.get(i).name+"vruntime bez castow"+((a-b)*(20.0/prior)));
                           //  System.out.println(Runnable_proces_Queue.get(i).name+"vruntime z castem"+Math.round((a-b)*(20.0/prior)));
                      Runnable_proces_Queue.get(i).state="ready";
      
      }
         Collections.sort(Runnable_proces_Queue,new Priority_Comparator());
         
       //zmiana statusu na running       
       Runnable_proces_Queue.get(0).state="Running";
       Running.running=Runnable_proces_Queue.get(0); //wybrany_przez_procesor do zmiennej globalnej
 
       System.out.println("cała kolejka procesow gotowych po wywołaniu SCHEDULERA ");
    for(Iterator<PCB> it=Runnable_proces_Queue.iterator();it.hasNext();)
                 {
                      PCB elem=it.next();
                     System.out.println(elem.name+" vruntime:"+elem.vruntime+" stan:"+elem.state+" liczba wyk rozk"+elem.liczba_wyk_rozk);
                 }
       }
   }
    void Delete_from_Runnable_proces_Queue(PCB ref){
         System.out.println("ZDJĘCIE Z KOLEJKI PROCESOW GOTOWYCH procesu"+ref.name+" stan"+ref.state+" liczba wyk rozk"+ref.liczba_wyk_rozk);
        Runnable_proces_Queue.remove(ref); 
    
    }
    public static void main(String[] args) {
        
        Process p1=new Process(new PCB(5,"P1",0));
        Process p2=new Process(new PCB(1,"P2",1));
        Process p3=new Process(new PCB(3,"P3",2));
        p1.pcb.state="ready";
          p2.pcb.state="ready";
            p3.pcb.state="ready";
       Processor procesor=new  Processor();
       procesor.Add_to_Runnable_proces_Queue(p1.pcb);
        procesor.Add_to_Runnable_proces_Queue(p2.pcb);
          procesor.Add_to_Runnable_proces_Queue(p3.pcb); 
          
      
           /*Collections.sort(procesor.Runnable_proces_Queue,new Current_Priority_cmp());
                for(int i=0;i<procesor.Runnable_proces_Queue.size();++i){
                  System.out.println(procesor.Runnable_proces_Queue.get(i).name+" vruntime:"+procesor.Runnable_proces_Queue.get(i).vruntime+" priorytet:"+procesor.Runnable_proces_Queue.get(i).current_priority+" liczba wyk rozk"+procesor.Runnable_proces_Queue.get(i).liczba_wyk_rozk);*/
      
      
           
          Scanner sc = new Scanner(System.in);
     
    int licznik=4;
 while(true){
     
      System.out.println("//0 dodaj nowy//1wykonaj ?0/1");    
              int zmienna = sc.nextInt();
      switch(zmienna){
      case 0:
          do{                           //priorytet
          Process p=new Process(new PCB(sc.nextInt(),"P"+licznik,3));      
           p.pcb.state="ready";
               procesor.Add_to_Runnable_proces_Queue(p.pcb);
               
             System.out.println("Czy kolejny proces ma byc dodany w tym samym momencie?0/1");
      }while(sc.nextInt()==1);
             ++licznik;
        
        
          break;
      case 1: //1 niech najmniejszy wykona swoj vruntime ustalamy przy tym czy wykonał wszystki rozkazy czy nie
      Running.running.liczba_wyk_rozk+=Running.running.vruntime;
        System.out.println("Czy proces wykonał wszystkie rozkazy?0/1");
        if(sc.nextInt()==1){
                 Running.running.state="terminated";
                 procesor.Delete_from_Runnable_proces_Queue(Running.running); //terminated 
                 procesor.scheduler();
          
        }else   procesor.scheduler();
          
          break;
      default:
        }
    }
 
 
 }
 }

