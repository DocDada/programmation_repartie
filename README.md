# programmation_repartie

## TP1

Création d'un Thread, avec la classe *Thread*. On associe à un Thread un objet.
La méthode *start()* exécute le Thread. Si l'objet a implémenté l'interface *Runnable* (avec la méthode *run()*), la méthode *run()* est appelée.
Note : l'appel de la méthode run() exécute le code de la méthode, mais ne crée le thread.
Le Thread peut être arreté/bloqué avec la méthode *suspend()* (déprécié) et relancé avec la méthode *resume()*.
Une autre façon de créer un Thread, est pour l'objet d'hériter de Thread (en implémentant la méthode *run()*).

Un processus a 3 états : **prêt**, **bloqué** et **élu**.
Un processus est un programme en cours d'exécution. Il possède son propre espace d'adressage/espace mémoire.
Un thread est un processus avec un espace mémoire partagé (dit processus "léger").

Documentation java :
- Thread : https://docs.oracle.com/javase/7/docs/api/java/lang/Thread.html
- Runnable : https://docs.oracle.com/javase/7/docs/api/java/lang/Runnable.html



## TP2

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
public synchronized void nomMethode() {
    // some code
}

// main
waitMethode();
// section critique
signalMethode();
```


- wait() => P() (décrémentation du sémaphore)
- Signal() => V() (incrémentation du sémaphore)

Une **section critique** est un bloc de code exécuté par un thread à la fois.
Un **ressource critique** est une ressource accessible par un thread à la fois (ici STDIN).
Un **sémaphore** est un verrou, qui limite l'accès à un bloc de code, une ressource.
Utilisé lorsque la ressource est partagée par plusieurs threads. On utilise autant
de sémpahore que de ressources.
Pour une ressource : sémpahore **binaire**. Pour plusieurs : sémaphore **général**.

