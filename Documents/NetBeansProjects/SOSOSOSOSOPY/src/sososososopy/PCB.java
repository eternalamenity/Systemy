package sososososopy;


public class PCB {
    
    public int ID;
    public String process_name;
    public int process_state;//0, 1, 2
                             //gotowy<->oczekujący->zakończony
    
    public int base_priority;//LINUX!!! 0,1=CR, 2,3=N, 4-Bezczynności
    public int current_priority;
    public int counter=0; //Zlicza wszystkie rozkazy wykonane od narodzin tego konkretnego procesu. Ja tego używam! W "increase_counters" sprawdzam, czy to pole podzielne przez np. 3. 
    
    //CZAREK
    public int vruntime=2;//przyznany czas wykonania
    public int birth_time=ID;//każdy ma swój, = id
    public int com_executed=0;////ile rozkazów zostało wykonanych, gdy Czarek zauważy, że przekroczy vruntime to wywłaszzany
    //to wyżej jędrzej ma zerować itp.?
    
    //JĘDRZEJ
    public int R1 = 0;
    public int R2 = 0;
    public int R3 = 0;
    public int R4 = 0;
    
    //FILIP
    public /*Wiersz*/Stronica page_table[];//ref do tablicy stronic
    public int how_long; //długość w znaczkach/bajtach, dostaję od Ewy
    public int table_size; //how_long/16 zaokrąglając w górę
    public int where_in_file;//numer bajtu, od którego zaczyna się kod tego procesu w pliku wymiany
    public String which_file;//nazwa pliku .txt(to .txt będzie już uwzględnione w nazwie), który zawiera kod tego procesu
    public Boolean blad;
    //SZYMON
    public String statement;

    
}

