package sososososopy;
import java.util.List;
import java.util.LinkedList;



public class PCB {
    
    //FILIP (pozostałe rzeczy dla Filipa niżej)
    public static List<PCB> all_PCB=new LinkedList<>();//ta Filipowa lista specjalnej troski, żeby w każdym bloku były wszystkie inne bloki
    public PCB() {//konstruktor
        all_PCB.add(this);
    }



//-> tam, gdzie to nie ma jeszcze znaczenia, ustawiam wszystko na "-1" żeby nie było crashy całego programu jak o puste pole ktoś zapyta i żeby od razu było wiadomo o co chodzi jak "-1" wyświetli nam program
    
    
    public int ID=-1;
    public String name="-1";
    public int state=-1;//0, 1, 2
                        //gotowy, oczekujący, zakończony
                        //0->1, 0->2, 1->0, 1->2  KAŻDA ZMIANA MOŻLIWA
    
    public int base_priority=-1;//LINUX!!! 0,1,2=CR, 3,4,5=N, 6-Bezczynności (NIZSZY=WAZNIEJSZY)
    public int current_priority=-1;
    public int how_hungry=0; //Zlicza wszystkie rozkazy (ale nie swoje!) wykonane od narodzin tego konkretnego procesu. Ja tego używam! W "increase_counters" sprawdzam, czy to pole podzielne przez np. 3. 
    
    //CZAREK
    public int vruntime=2;//przyznany czas wykonania
    public int birth_time=ID;//każdy ma swój czas narodzenia, Czarek tego jakoś używa
    public int com_executed=0;////ile rozkazów zostało wykonanych, gdy Czarek zauważy, że przekroczy vruntime to wywłaszzany
    //Czarek to wyżej Jędrzej ma zerować itp.? Znaczy z ciekawości chciałbym jak to działa :D Zrobiłem sety i gety jak coś (są w Proces)
    
    //JĘDRZEJ
    public int R1 = 0;
    public int R2 = 0;
    public int R3 = 0;
    public int R4 = 0;
    
    //FILIP
    public Stronica page_table[];//ref do tablicy stronic
    public int how_long=-1; //długość w znaczkach/bajtach, dostaję od Ewy
    public int table_size=-1; //how_long/16 zaokrąglając w górę
    public int where_in_file=-1;//numer bajtu, od którego zaczyna się kod tego procesu w pliku wymiany
    public String which_file="-1";//nazwa pliku .txt(to .txt będzie już uwzględnione w nazwie), który zawiera kod tego procesu - dostaje od Ewy
    public Boolean blad=false;//Filip nie wiem po co ci to ale chyba tego chciałes tak?
    //Filipowa lista statyczna na górze (na początku tej klasy)

    //SZYMON
    public String statement="-1";//na wszelki wypadek, "-1" to znak, że ktos bawi się pustymi polami np. Bartoszek 
    
    //PIOTREK
    public boolean if_lock=false;//że niezablokowany


    
}


