# Distributed Computing



Repain Paul

DUT INFO 2

2018 - 2019

IUT de Vélizy


<hr />


Sommaire

[TP1](#tp1)

[TP2](#tp2)

[TP3](#tp3)

[TP4](#tp4)

[Sources](#sources)


<hr />

## TP1



Problématique : Comment exécuter plusieurs tâches en même temps ?



- Création d'un thread, lancement
- Etats d'un processus
- Rappels et définitions
- Diagramme UML de l'application



Création d'un Thread, avec la classe *Thread*. On associe à un Thread un objet, on le passe en argument à l'appel de son constructeur.
```Java
/* Création d'un Thread */
Thread task = new Thread(new UnMobile(LARGEUR, HAUTEUR));
```
La méthode *start()* exécute le Thread. Si l'objet a implémenté l'interface *Runnable* (avec la méthode *run()*), la méthode *run()* est appelée.
```Java
/* Lancement d'un Thread */
task.start();
```
Note : l'appel de la méthode run() exécute le code de la méthode, mais ne crée pas le thread !
Le Thread peut être arreté/bloqué avec la méthode *suspend()* (déprécié) et relancé avec la méthode *resume()*.
Une autre façon de créer un *Thread*, est pour une classe d'hériter de *Thread*.
*Thread* étend la classe *Runnable*, donc la classe qui hérite de *Thread* doit implémenter la méthode *run()*.

Un processus a 4 états :
- **prêt** : ressources disponibles => *start()*
- **bloqué** : ressources indisponibles, attente d'accès à une section critique, vérification d'une condition, mise en someille (*synchronized*, *wait()*, *sleep()*)
- **élu** : débloquage, exécution => *signal()*
- mort : processus tué, ou méthode *run()* terminée

Un **processus** est un programme en cours d'exécution. Il possède son propre espace d'adressage/espace mémoire.
Un **thread** est un processus avec un espace mémoire partagé (dit processus "léger").

<figure>
    <figcaption>Figure 1 : Diagramme UML du TP1, déplacement de mobiles dans une fenêtre, mobiles attachés à des Threads</figcaption>
    
![Diagramme UML du TP1](https://github.com/Poulpy/programmation_repartie/blob/master/tp1.png?raw=true "Figure 1")
</figure>


Documentation java :
- [Thread](https://docs.oracle.com/javase/7/docs/api/java/lang/Thread.html)
- [Runnable](https://docs.oracle.com/javase/7/docs/api/java/lang/Runnable.html)

<hr />


## TP2


Problématique : Comment gérer l'accès à une ressource qui peut être utlisée par plusieurs tâches en même temps ?


- Création d'une section critique avec *synchronized*
- Création d'un sémaphore et appels des méthodes *wait()* et *signal()*


Création d'une section critique. La section critique est entourée du bloc *synchronized()* :

```Java
synchronized(mutex) {
    /* section critique */
}
```

synchronized() rend le bloc de code entouré **atomique**.<br />
L'objet passé en paramètre fait office de clé. Un processus ne rentre dans le bloc que s'il a la clé.
L'objet est accessible par tous processus, donc déclaré *static*. Il s'agit d'un sémaphore binaire.

```Java
private static Object mutex = new Object();// objet quelconque
```

Dans *synchronized()*, les appels des méthodes *wait()* et *signal()* sont automatiques (début et fin de bloc).
On donc entourer la section critique, sans avoir besoin du bloc *synchronized()*, en utilisant les méthodes
*wait()* et *signal()* (*notifyAll()*).<br />
Toutefois ces méthodes ne sont pas atomiques. On peut donc les rendre atomiques en les appelant dans des méthodes
*synchronized*. Ces méthodes sont définies dans une interface semaphore :

```Java

/* Interface semaphore */
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

/* Classe Affichage */
/* Utilisation des méthodes */
public vois run() {
    static semaphoreBinaire mutex = new semaphoreBinaire(1);
    mutex.syncWait();
    /* section critique */
    mutex.syncSignal();
}
```


- *wait()* => *P()* -> décrémentation du sémaphore
- *signal()* => *V()* -> incrémentation du sémaphore

Une **section critique** est un bloc de code devant être exécuté par un seul thread.<br />
Un **ressource critique** est une ressource accessible devant être accessible par plusieurs thread à la fois (i.e. STDIN).<br />
Une opération **atomique** est une tâche qui ne peut être interrompue.<br />
Un **sémaphore** est un verrou, qui limite l'accès à un bloc de code, une ressource.<br />
Utilisé lorsque la ressource est critique. On utilise autant de sémpahore que de ressources.
Pour une ressource : sémpahore **binaire**. Pour plusieurs : sémaphore **général**.


<hr />


## TP3

#### Introduction

Problématique : Comment conceptualiser intelligemment l'accès à une ressource ?


- Fonctionnement du Design Pattern Producteur/Consommateur
- Implémentation des méthodes de lecture et d'écriture dans la ressource critique

###### Rappel de cours

Les files d'attentes sont modélisées en Java par l'interface **BlockingQueue**.
BlockingQueue a plusieurs implémentations : **ArrayBlockingQueue** et **LinkedBlockingQueue**.<br />



Implémentation du **Design Pattern Producteur/Consommateur** :mailbox_with_no_mail:<br />
Le **Producer** écrit dans la ressource. Le **Consumer** lit dans la ressource.
La Bal (Boîte à lettres) contient la ressource écrite par Le Producer et lue par le Consumer.

Le Producer et le Consumer sont des tâches, donc des Threads.<br />
La ressource, critique, n'est pas directement accessible par le Producer et le Consumer.

```Java
/* Lancement des Threads */
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

La Bal est composée d'une file d'attente, **ArrayBlockingQueue**, avec une taille limité.
Pour déposer et retirer des lettres on utilise des méthodes déjà implémentées dans la classe, *put()* et *take()*. On dépose une lettre que si la file n'est pas pleine, et on retire une lettre que si elle n'est pas
vide.

```Java
/* Monitor */
public class Bal
{
    private int nbLetters = 15;
    private BlockingQueue<Character> letters;

    public Bal()
    {
        letters = new ArrayBlockingQueue<Character>(nbLetters);
    }

    public void deposer(char letter) throws InterruptedException
    {
        letters.put(letter);
    }

    public char retirer() throws InterruptedException
    {
        return letters.take();
    }
}
```




Un Producer ne peut écrire dans la ressource que si elle n'est pas actuellement utilisée par un Consumer, et inversement.<br />
Pour quitter la tâche d'écriture, l'utilisateur entre 'q'. La tâche d'écriture s'arrête quand la lettre 'q' est lue (donc pas forcément en même temps).


```Java
/* Classe Consumer */
public class Consumer extends Thread
{
    private Bal bal;// boite à lettres

    public Consumer(Bal bal)
    {
        this.bal = bal;
    }

    public void run()
    {
        char lettre = 'a';
        try
        {
            while (lettre != 'q')
            {
                // Le lecteur attend un peu pour pouvoir lire
                Thread.sleep(5000);
                lettre = bal.retirer();
                System.out.println("Lettre " + lettre + " lue");
            }
        }
        catch (InterruptedException e) {}
    }
}
```

<figure>
<figcaption>Figure 2 : Diagramme UML du TP3, Design Pattern Producteur/Consommateur (précédente implémentation)</figcaption>


![Diagramme UML du TP3](https://github.com/Poulpy/programmation_repartie/blob/master/tp3.png?raw=true "Figure 2")


</figure>

<hr />

## TP 4

#### Introduction

Problématique : Comment appliquer la méthode de Monte-Carlo à la programmation parallèle ?

<figure>
<figcaption>
Représentation du calcul de la valeur de &pi par rapport du nombre de points aléatoires étant contenus dans un quart de cercle, l'ensemble des possibles étant un carré de côté R.
</figcaption>

<img src="https://upload.wikimedia.org/wikipedia/commons/thumb/b/b6/Montecarlo-valeur-pi.svg/320px-Montecarlo-valeur-pi.svg.png" alt="Méthode de Monte-Carlo" />
</figure>

###### Rappel de cours

2 modèles de parallèlisme

**Parallèlisme de tâches**<br />
- décomposition d'une tâche en sous-tâches
- variable protégées entre tâches ou communication par message entre tâches

**Parallèlisme de données**<br />
- manipulation de structures homogènes (tableau)
- haut niveau d'expression du parallèlisme
- découpement des structures sur les processus
- restructuration des données par communication

Paradigmes de programmation<br />
Structures d'algorithme pour l'exécution sur une matière parallèle. Un programme est la combinaison de plusieurs paradigmes.
- parallèlisme de phase
- itération parallèle
- pipeline
- divide & conquer
- maître / esclave
- client / serveur





<hr />

#### Sources

- [Cours de José Paumard](http://blog.paumard.org/cours/java-api/chap05-concurrent.html)
- Cours de Dufaud Thomas
- Cours de Calcado Fabien
- [Lien vers le repository Git](https://github.com/Poulpy/programmation_repartie)
- [Article wikipédia sur la méthode de Monte-Carlo](https://fr.wikipedia.org/wiki/M%C3%A9thode_de_Monte-Carlo)
