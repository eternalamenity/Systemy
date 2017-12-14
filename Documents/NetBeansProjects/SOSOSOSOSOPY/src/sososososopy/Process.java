package sososososopy;


public class Process {
    private PCB blok;

    
    
    public Process(int id, String name) {//proces bezczynności, Filip na starcie robi część roboty automatycznie       
        blok=new PCB();
        blok.ID=id;
        blok.process_name=name;
        
        blok.table_size=1;//wiem już teraz, ile będzie zajmował proces bezczynności
        blok.base_priority=blok.current_priority=6;//priorytet procesu bezczynności
        blok.which_file=" ";//umówiony sygnał dla FILIPA
        
        /***********BLOKFILIPA.loadToFile(which_file);*/
        
        //W POWYŻSZEJ METODZIE FILIP NADAJE ODPOWIEDNIE WARTOŚCI ZMIENNYM PAGE_TABLE[]; I WHERE_IN_FILE;
        
        blok.process_state=0;
    }
    
    
    public Process(int id, String name, int how_long, String which_file) {//pozostałe procesy
        blok=new PCB();
        blok.ID=id;//z pomocą podanych zmiennych inicjalizuję 5 pól w PCB
        blok.process_name=name;
        blok.which_file=which_file;
        blok.how_long=how_long;
        if((how_long%16)!=0){
            blok.table_size=how_long/16+1;
        }
        else{
            blok.table_size=how_long/16;
        }
        
        if(id%3==0){//pamiętaj, że proces bezczynności sprawi, że pierwsze dwa będą zwykłe
            blok.base_priority=blok.current_priority=2;//że procesy Czasu Rzeczywistego
        }
        else{
            blok.base_priority=blok.current_priority=5;//że zwykłe
        }
        
        /***********BLOKFILIPA.loadToFile(which_file);*/
        
        //W POWYŻSZEJ METODZIE FILIP NADAJE ODPOWIEDNIE WARTOŚCI POLOM PAGE_TABLE[]; I WHERE_IN_FILE;
        
        blok.process_state=0;//GOTOWY
    }

    public PCB get_PCB() {
        return blok;
    }
    public void set_PCB(PCB pcb){
        blok=pcb;
    }
    public int get_ID() {
        return blok.ID;
    }
    public String get_process_name() {
        return blok.process_name;
    }
    public int get_process_state() {
        return blok.process_state;
    }
    public void set_process_state(int i){
        blok.process_state=i;
    }
    
    public int get_base_priority() {
        return blok.base_priority;
    }
    public int get_current_priority() {
        return blok.current_priority;
    }
    public void set_priority(int i){
        if(blok.current_priority==0||blok.current_priority==1||blok.current_priority==2){
            if(i==0||i==1||i==2){
                blok.current_priority=i;
            }
        }
        if(blok.current_priority==3||blok.current_priority==4||blok.current_priority==5){
            if(i==3||i==4||i==5){
                blok.current_priority=i;
            }
        }
    }
    public int get_counter() {//zlicza WSZYSTKIE rozkazy wykonane od narodzin tego procesu
        return blok.current_priority;
    }
    public void set_counter(int i){
        blok.counter=i;
    }
    public void increase_counter(){
        blok.counter=blok.counter+1;
    }
    
    
    //CZAREK
    public int get_vruntime() {
        return blok.vruntime;
    }
    public int get_birth_time() {//ten cały czas urodzenia
        return blok.birth_time;
    }
    public int get_com_executed() {//ile rozkazów z TEGO procesu zostało wykonanych na razie
        return blok.com_executed;
    }

    
    
    
    //JĘDRZEJ
    public int get_R1() {
        return blok.R1;
    }
    public int get_R2() {
        return blok.R2;
    }
    public int get_R3() {
        return blok.R3;
    }
    public int get_R4() {
        return blok.R4;
    }
    
    
    //FILIP
    public /*Wiersz*/int[] get_page_table() {
        return blok.page_table;
    }
    public int get_how_long() {
        return blok.how_long;
    }
    public int get_table_size() {
        return blok.table_size;
    }
    public int get_where_in_file() {
        return blok.where_in_file;
    }
    public String get_which_file() {
        return blok.which_file;
    }
    
    
    //SZYMON
    public String get_statement() {
        return blok.statement;
    }
    public void set_statement(String i){//to można wywalić, ale Szymon chciał
        blok.statement=i;
    }
    
    
    //EWA
    public String show_info() {
        String s=new String();
        s=s+"Proces "+blok.process_name+" o ID="+blok.ID+" i priorytecie="+blok.current_priority+", bedacy w stanie ";
        if(blok.process_state==0) {
            s=s+"''GOTOWY''";
        }
        else{
            if(blok.process_state==1){ 
                s=s+"''OCZEKUJACY''";
            }
            else{
                if(blok.process_state==2) {
                    s=s+"''ZAKONCZONY''";
                }
                else {
                    s=s+"z jakiegos powodu nieznanym";
                }
            }
        }
        return s;
    }
    
    
}
