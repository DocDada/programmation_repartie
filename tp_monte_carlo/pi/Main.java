package pi;

import java.util.concurrent.ExecutionException;

public class Main {

    public static void main(String[] args) {
        PiMonteCarlo PiVal;
        long startTime = 0;
        long stopTime = 0;
        double value = 0;
        long moy = 0;
        long times = 16;
        /*
        for (int i = 1; i != times+1; i++) {
            PiVal = new PiMonteCarlo(1000000);
            startTime = System.currentTimeMillis();
            value = PiVal.getPi();
            stopTime = System.currentTimeMillis();
            
            moy += stopTime - startTime;
            
        }
        moy /= times;

        System.out.println("Average Duration: " + moy + "ms");
        
        moy = 0;*/
        
        try {
            for (int i = 2, j = 80000; i < times+1; i*=2, j/=2) {
                startTime = System.currentTimeMillis();
                new Master().doRun(j, i);
                stopTime = System.currentTimeMillis();
                
                System.out.println(j + " | " + i + " | " + (stopTime - startTime));
                
                moy += stopTime - startTime;                
            }
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (ExecutionException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        moy /= times;

        System.out.println("Average Duration: " + moy + "ms");
        
        
        // Strong scalability : acceleration
        try {
            for (int i = 2, j = 100000; i < times+1; i*=2) {
                startTime = System.currentTimeMillis();
                new Master().doRun(j, i);
                stopTime = System.currentTimeMillis();
                
                System.out.println(j + " | " + i + " | " + (stopTime - startTime));
                
            }
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (ExecutionException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        
        
     // Precision
        try {
            for (int i = 2, j = 100000; i < times+1; i*=2) {
                startTime = System.currentTimeMillis();
                new Master().doRun(j, i);
                stopTime = System.currentTimeMillis();
                
                System.out.println(j + " | " + i + " | " + (stopTime - startTime));
                
            }
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (ExecutionException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        
    }

}
