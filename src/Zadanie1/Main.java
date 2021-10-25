package Zadanie1;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] arg) throws IOException{

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String path = path(reader);
        String znak = menu(reader);
        while (!znak.equals("d")){

            switch (znak) {
                case "a":
                    wypisz_all(path);
                    break;
                case "b":
                    dodaj(reader,path);
                    break;
                case "c":
                    wypisz_select(path,reader);
                    break;


            }
            znak = menu(reader);
        }



    }

    public static void wypisz_select(String path,BufferedReader reader) throws IOException{
        String s = readFile(path);
        System.out.print("Podaj nazwisko:");
        String name = reader.readLine();

        String[] split = s.split(";");
        String[] komunikaty = new String[]{"Imię: ","Nazwisko: ", "Tel.: ","e-mai: "};
        for(int i=1;i< split.length-1;i+=4){
            if(split[i].equals(name)){
                for(int j=0;j< komunikaty.length;j++){
                    System.out.print(komunikaty[j]+split[i-1+j]+"\n");
                }
                System.out.print("\n");
            }

        }
    }


    public static void wypisz_all(String path){
        String s = readFile(path);

        String[] split = s.split(";");
        String[] komunikaty = new String[]{"Imię: ","Nazwisko: ", "Tel.: ","e-mai: "};
        int counter =0;
        for(int i =0;i< split.length-1;i++){
            System.out.print(komunikaty[counter]+split[i]+"\n");
            counter++;
            if(counter ==4){
                counter=0;
                System.out.print("\n");
            }
        }
    }

    public static void dodaj(BufferedReader reader, String nazwa) throws IOException{
        String to_add ="";
        String tel;
        System.out.print("Imię:");
        to_add += reader.readLine();
        System.out.print("Nazwisko:");
        to_add += ";"+reader.readLine() + ";";
        System.out.print("Tel.:");
        tel = reader.readLine();
        for(int i=0;i<tel.length();i++){
            if( i==3 || i == 6){
                to_add+="-";
            }
            to_add+=tel.charAt(i);
        }
        System.out.print("e-mail:");
        to_add += ";" + reader.readLine()+";";

        writeFile(nazwa, to_add);

    }

    public static String path(BufferedReader reader) throws IOException{
        String ścieżka;
        System.out.print("Czy chcesz zmienić ścieżkę pliku z wizytówkami y/n:");
        List<String> allow = Arrays.asList("y","n");
        String znak = reader.readLine();

        if(!allow.contains(znak)){
            System.out.print("Zła opcja");
            path(reader);
        }

        if(znak.equals("y")){
            System.out.print("Podaj nową ścieżkę:\n");
            ścieżka = reader.readLine();
        }
        else{
            ścieżka = "wizytówki.csv";
            File tmp = new File(ścieżka);
            if(!tmp.exists()){
                tmp.createNewFile();
            }
        }
        return ścieżka;
    }

    public static String menu(BufferedReader reader) throws IOException{
        System.out.print("a. Wyświetlenie wszystkich wizytówek\n");
        System.out.print("b. Dodanie nowej wizytówki\n");
        System.out.print("c. Wyświetlenie wizytówki dla osób o określonym nazwisku\n");
        System.out.print("d. Zakończenie działania Programu\n");

        String znak = reader.readLine();

        List<String> allow = Arrays.asList("a","b","c","d");

        if(!allow.contains(znak)){
            System.out.print("Zła opcja\n");
            menu(reader);
        }

        return znak;
    }


    public static String readFile(String fileName)
    {
        String result = "";
        try
        {
            File myFile = new File(fileName);
            Scanner myScanner = new Scanner(myFile);
            while ( myScanner.hasNextLine() )
            {
                result += (myScanner.nextLine() + "\r\n");
            }
            myScanner.close();
        }
        catch (FileNotFoundException e)
        {
            System.out.println("Ooops... Wystąpił błąd: ");
            e.printStackTrace();
        }
        return result;
    }

    public static void writeFile(String fileName, String line)
    {
        try
        {
            FileWriter fw = new FileWriter(fileName, true);
            fw.write(line);
            fw.close();
        }
        catch (IOException e)
        {
            System.out.println("Ooops... Wystąpił błąd: ");
            e.printStackTrace();
        }


    }

}

