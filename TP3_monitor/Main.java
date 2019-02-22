
public class Main
{
    public static void main(String[] args)
    {
        Bal bal = new Bal();
        Producer prod = new Producer(bal);
        Consumer cons = new Consumer(bal);

        System.out.println("-------------");

        prod.start();
        cons.start();
    }
}
