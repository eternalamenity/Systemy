package sososososopy;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Stronicowanie {

    public static void main(String[] args) throws IOException {
        Blok_Kontrolny blok1= new Blok_Kontrolny(1,"plik1.txt");
        Blok_Kontrolny blok2= new Blok_Kontrolny(2,"plik2.txt");
        Blok_Kontrolny blok3= new Blok_Kontrolny(3,"plik3.txt");
        List <Blok_Kontrolny> list = new LinkedList<>();
        list.add(blok1);
        list.add(blok2);
        list.add(blok3);
        blok1.setA(list);
        blok2.setA(list);
        blok3.setA(list);
        Zarzadzanie_pamiecia a=new Zarzadzanie_pamiecia();
        a.loadToFile("plik1.txt", blok1);
        a.loadToFile("plik.2.txt", blok2);
        a.loadToFile("plik.3.txt", blok3);
        System.out.println();
        System.out.println(blok1.getLiczbaZn()+"&"+blok1.getMiejsceWpliku());
        System.out.println(blok2.getLiczbaZn()+"&"+blok2.getMiejsceWpliku());
        System.out.println(blok3.getLiczbaZn()+"&"+blok3.getMiejsceWpliku());
        a.setBlok(blok1);
        System.out.println(a.readMemory(0)); // fifo 0
        System.out.println(a.readMemory(30)); // fifo 0 1
        System.out.println(a.readMemory(40)); // fifo 0 1 2
        System.out.println(a.readMemory(60)); //fifo 0 1 2 3
        a.writeMemory(0, 'k');
        a.writeMemory(1, 'u');
        a.writeMemory(2, 'p');
        a.writeMemory(3, 'a');
        a.setBlok(blok2);
        System.out.println(a.readMemory(0)); // fifo 0 1 2 3 0
        System.out.println(a.readMemory(1)); // fifo 0 1 2 3 0
        System.out.println(a.readMemory(2)); // fifo 0 1 2 3 0
        System.out.println(a.readMemory(33)); // fifo 0 1 2 3 0 2
        System.out.println(a.readMemory(17)); // fifo 0 1 2 3 0 2 1
        System.out.println(a.readMemory(56)); // fifo 0 1 2 3 0 2 1 3
        a.setBlok(blok1);
        
        System.out.println(a.readMemory(70));
        System.out.println(a.readMemory(43));
        System.out.println(a.readMemory(27));
        a.setBlok(blok3);
        System.out.println(a.readMemory(0));
        System.out.println(a.readMemory(65));
        a.freeMem();
        a.setBlok(blok2);
        a.freeMem();
        a.setBlok(blok1);
        a.freeMem();
      //  a.saveFrameToDisc(fdf, 20);
//        a.setBlok(blok1);
//        for(int i=0; i<96; i++){
//        System.out.print(a.readMemory(i));
//        }
//        a.setBlok(blok2);
//        for(int i=0; i<64; i++){
//        System.out.print(a.readMemory(i));
//        }
//        a.setBlok(blok3);
//           for(int i=0; i<96; i++){
//        System.out.print(a.readMemory(i));
//        }
//        System.out.println("1");
//        System.out.println(a.readMemory(70));
//        System.out.println("2");
//        System.out.println(a.readMemory(17));
//        System.out.println("3");
//        System.out.println(a.readMemory(33));
//        System.out.println("4");
//        System.out.println(a.readMemory(49));
//        a.setBlok(blok2);
//        System.out.println("5");
//        System.out.println(a.readMemory(1));
//        System.out.println("6");
//        System.out.println(a.readMemory(17));
//        a.setBlok(blok3);
//        System.out.println("7");
//        System.out.println(a.readMemory(1));
//        System.out.println("8");
//        System.out.println(a.readMemory(30));
//        System.out.println("9");
//        System.out.println("Odczytanoooo " +a.readMemory(50));
   // a.clearFile();
    }

}
