/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sososososopy;


import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;
import java.util.HashMap;
/**
 *
 * @author Lena
 */


public class Shell {
    
    
    public void Print(String string){ /////////Bo mila jest spierdolony i ma jakieś głupie życzenia
        System.out.println(string);
    }
     
    
    int count(String file) throws FileNotFoundException{      ////Zwraca liczbę znaczkow w programie
            File fileS = new File(file);
            Scanner in = new Scanner(fileS);  
             int lines = 0;
                 int chars = 0;
                 while(in.hasNextLine())  { 
                      lines++;
                      String line = in.nextLine();  
                       chars += line.length();                     
    }
                 chars+=lines;
                   return chars;}
    
    void Separate(String line){ ///////////Rozdzielanie
    if(line.length()>2){
    Command =line.substring(0,2);
    Parameter = line.substring(3,line.length());
    Parameters = Parameter.split(" ");  
//    for ( String ss : Parameters) {
//       System.out.println(ss);
//    }
    } else{
    if (line.length()<3){
        Command=line;
        Parameters=null;
    }
    }
    } /////end rozdzielania
    
      boolean isAllowed(){ ///////Czy dane sa podane prawidlowo
      if (!Commands.containsKey(Command)) return false;
      if (Commands.get(Command)==0 && Parameters==null) return true;
      if (Commands.get(Command)==0 && Parameters!=null) return false;
      if (Parameters.length!=Commands.get(Command)) return false;
      return true;
      }
    
        String Command;
        String Parameter;
        String[] Parameters;  ///maksymalnie 3 parametry
        String PowtCom; 
         private  HashMap<String, Integer> Commands;
          File fileScript;
	 public Shell () throws InterruptedException, FileNotFoundException, IOException {
             
             Commands = new HashMap<>(); ///tworzymy liste zawierajaca komendy oraz liczbe parametrow
             Commands.put("CP",3); ///Utworz proces
             Commands.put("DP",1); ///Usuń proces
             Commands.put("BC",1); ///Pokaż blok procesu
             Commands.put("GO",0); ///Wykonaj rozkaz
             Commands.put("MC",2); ///Pokaz pamiec
             Commands.put("MF",2); ///Utworz plik
             Commands.put("SF",1); ///Wyswietl plik
             Commands.put("DF",1); ///Usuń plik
             Commands.put("EF",1); ///Edytuj plik
             Commands.put("SC",0); ///Wyswietl tablice Fat
             Commands.put("CC",3); ///Przypisz komunikat do procesu
             Commands.put("EX",0); ///Zamknij system
             
             
             ////////////////////Ekran powitalny jakies pierdopierdu podaj nazwe pliku ze skryptem
             
            Scanner console = new Scanner(System.in);           
            do {
            
            System.out.println("Prosze podac plik ze skryptem.");
            String inputScript = console.next();
            fileScript = new File(inputScript);
            }while(!fileScript.exists()||fileScript.isDirectory());
            Scanner in = new Scanner(fileScript);  ///////////Zawartosc pliku ze skryptami

            
                 while(in.hasNextLine())  {  ////Dopoki plik nie jest pusty
                      
                       String line = in.nextLine();   ////////Wczytujemy polecenie
                     /// line = line.toLowerCase(); ///////wszystko malymi literami
                      
                      Separate(line);
                      
                      
                     ///////////IsAllowed - czy komenda jest poprawna
                       ///////////Jak nie jest to obslugujemy (prosimy o podanie jeszcze raz line ew czy skipnac?
                       while(isAllowed()==false){
                       System.out.println("Komenda >> " + line + " << jest nieprawidłowa.");
                       System.out.println("Naciśnij E, jeśli chcesz wyjść z programu.");
                       System.out.println("Naciśnij P, jesli chcesz ją pominąć i przejść do dalszej części skryptu.");
                       PowtCom=console.next();
                       PowtCom=PowtCom.toUpperCase();
                       
                       if(PowtCom.charAt(0)=='E'){
                           
                           System.exit(0);} 
                       else {
                       line = in.nextLine();
                       Separate(line);
                       }
                       }
                      
                      
                      
                       ///////////IsAllowed - czy komenda jest poprawna
                       ///////////Jak nie jest to obslugujemy (prosimy o podanie jeszcze raz line ew czy skipnac?
                       
                       switch(Command){             ///osługa komendy
                           case "CP" : { ///Utworz proces
                               System.out.println("test CP");
                               break;}
                           case "DP" : { ///Usuń proces
                               System.out.println("test DP");
                               break;}
                           case "BC" : { ///Pokaż blok procesu
                               System.out.println("test BC");
                               break;}
                           case "GO" : { ///Wykonaj rozkaz
                               System.out.println("test GO");
                               break;}
                           case "MC" : { ///Pokaz pamiec
                               System.out.println("test MC");
                               break;}
                           case "MF" : { ///Utworz plik
                               System.out.println("test MF");
                               break;}
                           case "SF" : { ///Wyswietl plik
                               System.out.println("test SF");
                               break;}
                           case "DF" : { ///Usuń plik
                               System.out.println("test DF");
                               break;}
                           case "EF" : { ///Edytuj plik
                               System.out.println("test EF");
                               break;}
                           case "SC" : { ///Wyswietl tablice Fat
                               System.out.println("test SC");
                               break;}
                           case "CC" : { ///Przypisz komunikat do procesu
                               System.out.println("test CC");
                               break;}
                           
                           case "EX" : { ///Zamknij system
                               System.out.println("Dziekujemy za skorzystanie z systemu!");
                               System.out.println("Do widzenia!");
                               System.in.read();
                               System.exit(0); }
                       }
                       System.in.read(); ////pauza po obsłużeniu
    }
                 System.out.println("Nieoczekiwany koniec skryptu. System teraz się wyłączy.");
                 System.out.println("Dziekujemy za skorzystanie!");
                 
                 System.in.read();
         }

   
}
                
	
