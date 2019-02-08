# programmation_repartie

## TP1

Création d'un Thread, avec la classe *Thread*. On associe à un Thread un objet.
La méthode *start()* lance/élit le Thread. Si l'objet a implémenté l'interface *Runnable* (avec la méthode *run()*), la méthode *run()* est appelée.
Le Thread peut être arreté/bloqué avec la méthode *suspend()* (déprécié) et relancé avec la méthode *resume()*.
Une autre façon de créer un Thread, est pour l'objet d'hériter de Thread (en implémentant la méthode *run()*).

Un processus a 3 états : prêt, bloqué et élu.

Documentation java :
Thread - https://docs.oracle.com/javase/7/docs/api/java/lang/Thread.html
Runnable - https://docs.oracle.com/javase/7/docs/api/java/lang/Runnable.html
