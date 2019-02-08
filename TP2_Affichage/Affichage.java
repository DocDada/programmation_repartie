/**
 *
 */
import java.io.*;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.BufferedReader;
import java.lang.String;

public class Affichage extends Thread {

    String texte;
    // pour que la variable/verrou soit accessible à toutes les
    // instances de la classe
    static semaphoreBinaire mutex = new semaphoreBinaire(0);

    public Affichage (String txt) {texte=txt;}

    public void run() {

        synchronized(mutex) {
            for (int i = 0; i < texte.length(); i++){
                // on affiche le texte, caractère par caractère
                // section critique
                System.out.print(texte.charAt(i));
                try { sleep(100); }
                catch (InterruptedException e) {};
            }
        }// synchronized()
    }// run()
}// Thread

