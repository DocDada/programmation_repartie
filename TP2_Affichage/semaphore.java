public abstract class semaphore {

    protected int valeur=0;

    protected semaphore (int valeurInitiale){
        // la valeeur d'un sémpahore est supérieur à 0
        valeur = valeurInitiale>0 ? valeurInitiale:0;
    }

    public synchronized void syncWait(){
        try {
            // tant que le sempahore est nul, on attend
            while(valeur<=0){
                wait();
        }
            valeur--;
        } catch(InterruptedException e){}
    }

    public synchronized void syncSignal(){
        if(++valeur > 0) notifyAll();
    }
}
