/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sososososopy;


import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.NoSuchFileException;
import java.util.Scanner;
import java.util.HashMap;



public class Shell {
    
    
    private int count(String file) throws FileNotFoundException{      ////Zwraca liczbę znaczkow w programie
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
    
    private void Separate(String line){ ///////////Rozdzielanie
    if(line.length()>2){
    Command =line.substring(0,2);
    Command = Command.toUpperCase();
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
    
      private boolean isAllowed(){ ///////Czy dane sa podane prawidlowo
      if (!Commands.containsKey(Command)) return false;
      if (Commands.get(Command)==0 && Parameters==null) return true;
      if (Commands.get(Command)==0 && Parameters!=null) return false;
      if (Parameters.length!=Commands.get(Command)) return false;
      return true;
      }
      
      
      private void draw() throws FileNotFoundException{
      
      fileLogo = new File("logo.txt");
      
      Scanner inL = new Scanner(fileLogo);
     while(inL.hasNextLine())  {  ////Dopoki plik nie jest pusty
                      
                       String logoLine = inL.nextLine();   ////////Wczytujemy polecenie
                      
                      System.out.println(logoLine);
     }
      System.out.println();
      } //////end logo
      
      
    
        private String Command;
        private String Parameter;
        private String[] Parameters;  ///maksymalnie 3 parametry
        private String PowtCom; 
        private HashMap<String, Integer> Commands;
         
         
         private FileSystem disc;
         private ProcessManager ProcessManager;
         private Zarzadzanie_pamiecia Memory;
         private File fileScript;
         private File fileLogo;
         
         
	 public Shell () throws InterruptedException, FileNotFoundException, IOException {
             
             
            disc = new FileSystem(32,256).create();
            ProcessManager = new ProcessManager(); 
            Memory = new Zarzadzanie_pamiecia();
            //////////Tu sie kazdy musi wywolac razem z parametrami
            
            
            
            
            
            
             
             
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
             Commands.put("RC",1); ///Przeczytaj komunikat
             Commands.put("EX",0); ///Zamknij system
             
             
             ////////////////////Ekran powitalny
             
             try { draw();}
             catch (FileNotFoundException e){
             System.out.println("Wystapil błąd z wczytaniem loga.");}
             
             
             
            Scanner console = new Scanner(System.in);           
            do {
            System.out.println("Prosze podac plik ze skryptem.");
            String inputScript = console.next();
            fileScript = new File(inputScript);
            }while(!fileScript.exists()||fileScript.isDirectory());
            Scanner in = new Scanner(fileScript);  ///////////Zawartosc pliku ze skryptami

            
                 while(in.hasNextLine())  {  ////Dopoki plik nie jest pusty
                      
                       String line = in.nextLine();   ////////Wczytujemy polecenie
                      
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
                      
                      
                      
                       
                       switch(Command){             ///osługa komendy
                           case "CP" : { ///Utworz proces
                               int a;
                               
                               String inputScript2 = Parameters[2];
                                fileScript = new File(inputScript2);
                                if(!fileScript.exists()||fileScript.isDirectory()){
                                    inputScript2=inputScript2+".txt";
                                    fileScript = new File(inputScript2);
                                    if(!fileScript.exists()||fileScript.isDirectory()){
                                System.out.println("Plik o podanej nazwie nie istnieje. Nie można utworzyć procesu. Nastąpi teraz przejście do nastepnej komendy");
                                break; }
                                Parameters[2]=inputScript2;
                                }
                               
                               try{
                               a = Integer.parseInt(Parameters[1]);
                               ProcessManager.new_process(Parameters[0],a,Parameters[2]);
                               }catch(NumberFormatException e){
                               System.out.println("Podana wielkość jest nieprawidłowa. Parametr drugi być liczbą. Nastąpi teraz przejście do następnej komendy.");}
                               /////////ZAKLADAM ZE TU MILA TEZ MI COS RZUCI ALBO SAM OGARNIE
                               break;}
                           case "DP" : { ///Usuń proces
                               ProcessManager.delete_process(Parameters[0]); 
                               ///////////////ZAKLADAM ZE JAKIS WYJATEK MI TU MILA RZUCI OR SMTH
                               break;}
                           case "BC" : { ///Pokaż blok procesu
                               System.out.println(ProcessManager.show_info(Parameters[0]));
                               break;}
                           case "GO" : { ///Wykonaj rozkaz
                               System.out.println("test GO"); ////////Do ogarniecia
                               
                               
                               
                               break;}
                           case "MC" : { ///Pokaz pamiec
                               
                               System.out.println(Memory.getRAM());  ///////Do ogarniecia
                               
                               break;}
                           case "MF" : { ///Utworz plik
                               try{
                               disc.saveOnDisc(Parameters[0], Parameters[1]);
                               break;}catch(IOException e){System.out.println("Nie udało się utworzyć pliku");}
                           }
                           case "SF" : { ///Wyswietl plik
                               try{
                               System.out.println(disc.readFile(Parameters[0]));}
                               catch(NoSuchFileException e){
                               System.out.println("Nie można wyświetlić. Plik o nazwie \""+ Parameters[0] + " \" nie istnieje.");
                               }
                               break;}
                           case "DF" : { ///Usuń plik
                               if (disc.removeFile(Parameters[0])==true){
                               System.out.println("Usunięto plik!");
                               } else {
                               System.out.println("Usunięcie pliku nie powiodło się, ponieważ już nie istnieje. ");}
                               break;}
                           case "EF" : { ///Edytuj plik
                               System.out.println("test EF");
                               
                               
                               break;}
                           case "SC" : { ///Wyswietl tablice Fat
                               disc.showClusters();
                               break;}
                           case "CC" : { ///Przypisz komunikat do procesu
                               System.out.println("test CC");
                               
                               
                               
                               break;}
                           case "RC" : { ///Przeczytaj komunikat
                               System.out.println("test RC");
                               
                               
                               
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
