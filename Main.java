package com.company;

import java.util.Scanner;

abstract class Transport{
    protected int hp;
    void buy(){
        System.out.println("Вы купили велосипед ");
    }
    public double get_sum(){
        return 1;
    }
    Transport(){

    }
    Transport(int hp){
        this.hp=hp;
    }
}

class Unicycle extends Transport implements Main.Ride, Main.Repair {
    private int num_of_wheels=1;
    Main.Colour col;
    Main.Firm fi;
    private double diam;
    Unicycle(Main.Colour color, Main.Firm firm, int hp, double diam){
        super(hp);
        this.diam=diam;
        col=color;
        fi=firm;
    }
    public double get_diam(){
        return diam;
    }
    public void set_hp(int a){
        hp = a;
    }
    public double get_sum(){
        return diam;
    }
    @Override
    public void go(){
        System.out.println("Кататься на моноцикле очень сложно");
    }
    @Override
    public void repair(){
        set_hp(100);
        System.out.println("Моноцикл починен");
    }
}

class Bicycle extends Transport implements Main.Ride, Main.Repair {
    private int num_of_wheels=2;
    private Main.Colour col;
    private Main.Firm fi;
    private double diam,diam1;
    Bicycle(Main.Colour col, Main.Firm fi, int hp, double diam, double diam1){
        super(hp);
        this.diam=diam;
        this.diam1=diam1;
        this.col=col;
        this.fi=fi;
    }
    public void set_hp(int a){
        hp = a;
    }
    public double get_diam(){
        return diam;
    }
    public double get_diam1(){
        return diam1;
    }
    public double get_sum(){
        return diam+diam1;
    }
    @Override
    public void go(){
        System.out.println("Кататься на велосипеде не очень сложно");
    }
    @Override
    public void repair(){
        set_hp(100);
        System.out.println("Велосипед починен");
    }
}

class Tricycle extends Transport implements Main.Ride, Main.Repair {
    private int num_of_wheels=3;
    private Main.Colour col;
    private Main.Firm fi;
    private double diam,diam1,diam2;
    Tricycle(Main.Colour col, Main.Firm fi, int hp, double diam, double diam1, double diam2){
        super(hp);
        this.diam=diam;
        this.diam1=diam1;
        this.diam2=diam2;
        this.col=col;
        this.fi=fi;
    }
    public void set_hp(int a){
        hp = a;
    }
    public double get_diam(){
        return diam;
    }
    public double get_diam1(){
        return diam1;
    }
    public double get_diam2(){
        return diam2;
    }
    public double get_sum(){
        return diam+diam1+diam2;
    }
    @Override
    public void go(){
        System.out.println("Кататься на трицикле вообще не сложно");
    }
    @Override
    public void repair(){
        set_hp(100);
        System.out.println("Трицикл починен");
    }
}

class Workshop{
    static void rep (Transport transp){
        if(transp instanceof Unicycle){
            ((Unicycle)transp).repair();
        }
        if(transp instanceof Bicycle){             //  <-- Класс который ремнтирует любой транспорт
            ((Bicycle)transp).repair();
        }
        if(transp instanceof Tricycle){
            ((Tricycle)transp).repair();
        }
    }
}
public class Main {
enum Firm {BMX,BMV,PORSCHE,FERRARI,ZHIGULI}
enum Colour { RED,GREEN,BLACK,YELLOW,WHITE}
    interface Ride{
    void go();
    }
    interface Repair{
    void repair();
    }
    static Colour rez_c(int a){
        switch (a) {
            case 0 : return Colour.WHITE;
            case 1 : return Colour.RED;
            case 2 : return Colour.BLACK;
            case 3 : return Colour.GREEN;
            case 4 : return Colour.YELLOW;
        };
        return null;
    }
    static Firm rez_f(int a){
        switch (a) {
            case 0 : return Firm.BMX;
            case 1 : return Firm.BMV;
            case 2 : return Firm.FERRARI;
            case 3 : return Firm.PORSCHE;
            case 4 : return Firm.ZHIGULI;
        };
        return null;
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Сколько велосипедов сгенерировать?");
        int n = sc.nextInt();
        Transport[] Array = new Transport[n];
        for(int i = 0; i < n; i++){
            int r = (int)(Math.random()*3);
            int c = (int)(Math.random()*5);
            Colour c1 = rez_c(c);
            int f = (int)(Math.random()*5);
            Firm f1 = rez_f(f);
            int hp1 = (int)Math.random()*100;
            double d = Math.random()*10;
            double d1 = Math.random()*10;
            double d2 = Math.random()*10;
            switch (r){
                case 0:
                    Unicycle uni = new Unicycle(c1,f1,hp1,d);
                    Array[i]=uni;
                    break;
                case 1:
                    Bicycle bic = new Bicycle(c1,f1,hp1,d,d1);
                    Array[i]=bic;
                    break;
                case 2:
                    Tricycle tri = new Tricycle(c1,f1,hp1,d,d1,d2);
                    Array[i]=tri;
                    break;
            }
        }
        System.out.println("\t" + "\t");
        System.out.println("Ремонтируем все моноциклы");
        for(int i = 0;i<n;i++){
            if(Array[i] instanceof Unicycle){
                ((Unicycle) Array[i]).repair();
            }
        }
        System.out.println("\t" + "\t");
        System.out.println("Сумма диаметров колес до сортировки");
        for(int i = 0;i<n;i++){
            System.out.println(Array[i].get_sum());
        }
        for(int i = 0;i<n;i++) {
            for (int j = 1; j < n; j++) {
                if (Array[j].get_sum() > Array[j - 1].get_sum()) {
                    Transport transp = Array[j];
                    Array[j] = Array[j - 1];
                    Array[j - 1] = transp;

                }
            }
        }
        System.out.println("\t" + "\t");
        System.out.println("После сортировки");
        for(int i = 0;i<n;i++){
            System.out.println(Array[i].get_sum());
        }
    }
}
