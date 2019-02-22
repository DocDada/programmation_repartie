# programmation_repartie



## TP1

### Introduction

Comment exécuter plusieurs tâches en même temps ?


Création d'un Thread, avec la classe *Thread*. On associe à un Thread un objet.
La méthode *start()* exécute le Thread. Si l'objet a implémenté l'interface *Runnable* (avec la méthode *run()*), la méthode *run()* est appelée.
Note : l'appel de la méthode run() exécute le code de la méthode, mais ne crée le thread.
Le Thread peut être arreté/bloqué avec la méthode *suspend()* (déprécié) et relancé avec la méthode *resume()*.
Une autre façon de créer un Thread, est pour l'objet d'hériter de Thread (en implémentant la méthode *run()*).

Un processus a 4 états :
- **prêt** : ressources disponibles => *start()*
- **bloqué** : ressources indisponibles, attente d'accès à une section critique, vérification d'une condition, mise en someille (*synchronized*, *wait()*, *sleep()*)
- **élu** : débloquage, exécution => *signal()*
- mort : processus tué, ou méthode *run()* terminée

Un processus est un programme en cours d'exécution. Il possède son propre espace d'adressage/espace mémoire.
Un thread est un processus avec un espace mémoire partagé (dit processus "léger").

Documentation java :
- Thread : https://docs.oracle.com/javase/7/docs/api/java/lang/Thread.html
- Runnable : https://docs.oracle.com/javase/7/docs/api/java/lang/Runnable.html



## TP2

### Introduction

 Comment gérer l'accès à une ressource
qui peut être utlisée par plusieurs tâches en même temps ?


Création de sémaphore/mutex et d'une section critique. La section critique est
entouré du bloc :
```Java
synchronized(mutex) {
    // section critique
}
```
Dans *synchronized()*, les appels des méthodes *Wait()* et *Signal()* sont automatiques.
On peut toutefois entourer la section critique, sans avoir besoin du bloc *synchronized()*.
Pour ce faire, on appelle des méthodes type wait et signal qui doivent spécifier dans leur en-tête :
```Java
public synchronized void syncSignal() {
    if (++valeur > 0) notifyAll();
}

public synchronized void syncWait() {
    try {
        while (valeur <= 0) {
            wait();
        }
        valeur--;
    }
    catch (InterruptedException e) {}
}

// Utilisation des méthodes
public static void main(String[] args) {
    syncWait();
    /* section critique */
    syncSignal();
}
```


- wait() => P() (décrémentation du sémaphore)
- Signal() => V() (incrémentation du sémaphore)

Une **section critique** est un bloc de code pouvant être exécuté par plusieurs threads.
Un **ressource critique** est une ressource accessible pouvant être accessible par plusieurs thread à la fois (i.e. STDIN).
Un **sémaphore** est un verrou, qui limite l'accès à un bloc de code, une ressource.
Utilisé lorsque la ressource est critique. On utilise autant
de sémpahore que de ressources.
Pour une ressource : sémpahore **binaire**. Pour plusieurs : sémaphore **général**.





## TP3

### Introduction

Comment conceptualiser intelligemment l'accès à une ressource ?

Implémentation du **Design Pattern Producteur/Consommateur**.
Le **Producer** écrit dans la ressource (ici la boîte à lettres). Le **Consumer** lit dans la ressource.
Le Producer et le Consumer sont des tâches, donc des Threads.
La ressource, critique, n'est pas directement accessible par le Producer et le Consumer.

```Java
public class Main
{
    public static void main(String[] args)
    {
        try
        {
            Bal bal = new Bal();
            Producer prod = new Producer(bal);
            Consumer cons = new Consumer(bal);

            prod.start();
            cons.start();

            prod.join();
            cons.join();
        }
        catch (InterruptedException e) {}
    }
}
```

Pour synchroniser les accès, on appose le mot-clef synchronized aux en-têtes des méthodes.
Un Producer ne peut écrire dans la ressource que si elle n'est pas utilisée par un Consumer, et inversement.

```Java
public synchronized boolean DEPOSER(char letter) {
    /* ... */
}

public synchronized char RETIRER() {
    /* ... */
}
```


Pour terminer proprement la lecture et l'écriture, la ressource peut avoir un champs booléen indiquant son utilisation. Les méthodes DEPOSER et RETIRER retournent une valeur pour indiquer la terminaison des processus.


### Sources
- Cours de José Paumard :
http://blog.paumard.org/cours/java-api/chap05-concurrent.html
- Cours de Dufaut Thomas
- Cours de Calcado Fabien
