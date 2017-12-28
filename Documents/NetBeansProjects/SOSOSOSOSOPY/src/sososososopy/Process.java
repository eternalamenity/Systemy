package sososososopy;



public class Proces {
    private PCB block;

    
    public Proces(int id, String name) {//proces bezczynności, Filip na starcie robi część roboty automatycznie       
        block=new PCB();
        block.ID=id;
        block.name=name;//on dostanie z ProcesManager name="PB"
        
        block.table_size=1;//wiem już teraz, ile będzie zajmował proces bezczynności
        block.base_priority=block.current_priority=6;//priorytet procesu bezczynności
        block.which_file=" ";//umówiony sygnał dla FILIPA
        
        Zarzadzanie_pamiecia.loadToFile(block.which_file);//w tej metodzie Filip nadaje odpowiednie wartości zmiennym page_table[] i where_in_file
        
        block.state=0;//że gotowy
        
        //reszta wymaganych operacji wykonywana w metodzie ProcesManager, ze wzgledu na inny charakter działań
    }
    
    
    public Proces(int id, String name, int how_long, String which_file) {//pozostałe procesy
        block=new PCB();
        block.ID=id;//z pomocą podanych zmiennych inicjalizuję 5 pól w PCB
        block.name=name;//to ma byc logiczne?
        block.which_file=which_file;
        block.how_long=how_long;
        if((how_long%16)!=0){
            block.table_size=how_long/16+1;
        }
        else{
            block.table_size=how_long/16;
        }
        
        if(id%3==0){//pamiętaj, że proces bezczynności sprawi, że pierwsze dwa będą zwykłe
            block.base_priority=block.current_priority=2;//że procesy Czasu Rzeczywistego
        }
        else{
            block.base_priority=block.current_priority=5;//że zwykłe
        }
        
        Zarzadzanie_pamiecia.loadToFile(block.which_file);//w tej metodzie Filip nadaje odpowiednie wartości zmiennym page_table[] i where_in_file
        
        block.state=0;//GOTOWY
        
        //reszta operacji wykonywana w metodzie ProcesManager, ze wzgledu na inny charakter działań
    }

    
    //WAZNE!!!//wszędzie gdzie nie zwracam całego obiektu musi być set (żaden String ani Integer niewiele tu pomoże, tu chodzi chyba o to, że ten ktoś dziala na kopii obiektu, ale na oryginałach jego wnętrza/pól - masz surowego Stringa=kopia i tak)
    
    public PCB get_PCB() {//kimkolwiek jestes ty używający tej metody przeczytaj powyższy komentarz (jak nie zrozumiesz to napisz do mnie)
        return block;
    }
    //SET niepotrzebny, w tym przypadku GET zadziała jak referencja
    
    public int get_ID() {
        return block.ID;
    }
    //SET nie ma, id się nie zmienia
    
    public String get_name() {
        return block.name;
    }
    //SET nie ma, bo imienia się nie zmienia
    
    public int get_state() {
        return block.state;
    }
    public void set_state(int i){
        block.state=i;
    }
    
    public int get_base_priority() {
        return block.base_priority;
    }
    //SET nie ma, bo bazowego priorytetu się nie zmienia 
    
    public int get_current_priority() {
        return block.current_priority;
    }
    public void set_current_priority(int i) throws Exception {
        if(block.base_priority==0||block.base_priority==1||block.base_priority==2){
            if(i==0||i==1||i==2){
                block.current_priority=i;
            }
            else{
                throw new Exception("Nie mozna zmienic typu procesu!");
            }
        }
        if(block.base_priority==3||block.base_priority==4||block.base_priority==5){
            if(i==3||i==4||i==5){
                block.current_priority=i;
            }
            else{
                throw new Exception("Nie mozna zmienic typu procesu!");//Shell musi poinformowac Bartoszka, że podał złą nazwę
            }
        }
    }
    
    public int get_how_hungry() {//zlicza wszystkie (ale nie swoje) rozkazy wykonane od narodzin tego procesu
        return block.how_hungry;
    }
    public void set_how_hungry(int i){
        block.how_hungry=i;
    }
    public void increase_how_hungry(){
        block.how_hungry=block.how_hungry+1;
    }
    

    
    
    //CZAREK
    public int get_vruntime() {
        return block.vruntime;
    }
    public void set_vruntime(int i){
        block.vruntime=i;
    }
    public int get_birth_time() {//ten cały czas urodzenia
        return block.birth_time;
    }
    public void set_birth_time(int i){
        block.birth_time=i;
    }
    public int get_com_executed() {//ile rozkazów z TEGO procesu zostało wykonanych na razie
        return block.com_executed;
    }
    public void set_com_executed(int i){
        block.com_executed=i;
    }

    
    
    
    //JĘDRZEJ
    public int get_R1() {
        return block.R1;
    }
    public void set_R1(int i){
        block.R1=i;
    }
    public int get_R2() {
        return block.R2;
    }
    public void set_R2(int i){
        block.R2=i;
    }
    public int get_R3() {
        return block.R3;
    }
    public void set_R3(int i){
        block.R3=i;
    }
    public int get_R4() {
        return block.R4;
    }
    public void set_R4(int i){
        block.R4=i;
    }
    
    
    
    
    //FILIP
    public Stronica[] get_page_table() {
        return block.page_table;
    }
    //SET niepotrzebny, bo Wiersz to obiekt? No ale z testowania tablic obiektów wynika, że sam get całkowicie wystarczy
    public int get_how_long() {
        return block.how_long;
    }
    //SET niepotrzebny, tego się nie zmienia
    public int get_table_size() {
        return block.table_size;
    }
    //SET niepotrzebny, tego się nie zmienia
    public int get_where_in_file() {
        return block.where_in_file;
    }
    public void set_where_in_file(int i){
        block.where_in_file=i;
    }
    public String get_which_file() {
        return block.which_file;
    }
    public void set_which_file(String i){//To, że to string niewiele pomaga. Z naukowych badań doktora Mila wynika, że i tak w GET pracujemy na kopii
        block.which_file=i;
    }  
    
    
    
    
    //SZYMON
    public String get_statement() {
        return block.statement;
    }
    public void set_statement(String i){//bo String to i tak zawsze kopia
        block.statement=i;
    }
    
    
    
    
    //PIOTREK
    public boolean get_if_lock(){
        return block.if_lock;
    }
    public void set_if_lock(int i){
        if(i==0) {
            block.if_lock=false;
        }
        else block.if_lock=true;
    }
    
    
    
    
    //EWA
    public String show_info() {
        String s=new String();
        s="Proces "+block.name+" o ID="+block.ID+" i priorytecie="+block.current_priority+", bedacy w stanie ";
        if(block.state==0) {
            s=s+"''GOTOWY''";
        }
        else{
            if(block.state==1){ 
                s=s+"''OCZEKUJACY''";
            }
            else{
                if(block.state==2) {
                    s=s+"''ZAKONCZONY''";
                }
                else {
                    s=s+"-1";
                }
            }
        }
        return s;
    }
    
    
    
}




