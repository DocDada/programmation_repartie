/**
 *
 */
import java.io.*;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.BufferedReader;
import java.lang.String;

//class Exclusion {};

public class Affichage extends Thread {

    String texte;
    // pour que la variable/verrou soit accessible à toutes les
    // instances de la classe
    //static Exclusion mutex = new Exclusion();
    static semaphoreBinaire mutex = new semaphoreBinaire(1);

    public Affichage(String txt) {
        texte = txt;
    }

    public void run() {
        //synchronized(mutex) {
            mutex.syncWait();
            System.out.println("J'entre en section critique");

            for (int i = 0; i != texte.length(); i++){
                // on affiche le texte, caractère par caractère
                System.out.print(texte.charAt(i));

                try {
                    sleep(100);
                }
                catch (InterruptedException e) {};
            }

            mutex.syncSignal();
            System.out.println("\nJe sors de section critique");
        //}// synchronized()
    }// run()
}// Thread

