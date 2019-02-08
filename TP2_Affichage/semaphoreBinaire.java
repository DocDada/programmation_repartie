
public final class semaphoreBinaire extends semaphore {
    public semaphoreBinaire(int valeurInitiale){
        // un mutex ne prend que 2 valeurs : 0 ou 1
        super((valeurInitiale != 0) ? 1:0);
        //System.out.print(valeurInitiale);
    }
    public final synchronized void syncSignal(){
        super.syncSignal();
        //System.out.print(valeur);
        if (valeur>1) valeur = 1;
    }
}

