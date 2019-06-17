# Jeu : 
## 1 Joueur
Snake est un jeu composé de plusieurs niveaux.
Dans chaque niveau le serpent (le gros truc vert) doit attraper le poussin rouge (le petit machin rouge) pour gagner le niveau et passer au niveau suivant. 
Dans cette tâche ô combien pénible il est aidé par des poussins jaunes qui se laissent gentiment bouffer par lui. 
En en mangeant un le serpent grandit d'une case. Ce qui lui permet de plus facilement bloquer la trajectoire du poussin rouge.
Mais en contrepartie ça lui donne aussi plus de chance de perdre vu que la seule manière de perdre un niveau c'est que le serpent touche une partie de son corps et donc se bouffe lui même.
Rem : Le Serpent peut se "bouffer la queue" vu qu'il avance en même temps que sa queue avance. 
## 2 joueurs
Les serpents travaillent en équipe pour tuer le poussin rouge. Si l'un touche le corps de l'autre ils perdent tout les 2.
Les règles ci-dessus sont toujours en application mis à part que les 2 serpents peuvent se marcher sur la queue et se toucher la tête.

## Fin du jeu :

Pour finir le jeu il faut impérativement finir tous les niveaux existants.

## Score : 

Quand on mange un poussin jaune on gagne 10 points et quand on finit un niveau on gagne 200 points.

## Contrôles pour 1 joueur ou pour plusieurs joueurs en réseau :

Le serpent se dirige avec les touches directionnelles.

## Contrôles pour 2 joueurs :

Le premier serpent se dirige avec les touches directionnelles.
Le second serpent se dirige avec les touches "T F G et V" qui correspondent respectivement aux mouvements "Haut Gauche Droite et Bas".

## Les Boutons : 
Appuyer sur le bouton avec la tête de serpent pour fermer le jeu.

Appuyer sur le bouton New Game pour lancer la partie. Le jeu se lance vraiment après que l'on ai appuyé 1 fois sur les touches directionnelles
Appuyer sur le bouton Pause stoppe le jeu, appuyer dessus une seconde fois réactive le jeu.
Appuyer sur le bouton Music sert à arrêter la musique, appuyer dessus une seconde fois reprend la musique.
Appuyer sur le bouton Save pour sauver la partie en cours (Il met le jeu en pause automatiquement). Il faut introduire le nom sous lequel la partie sera sauvée.
Appuyer sur le bouton Load pour charger une partie déjà sauvée (Il met aussi le jeu en pause automatiquement). Il faut introduire le même nom donné à la sauvegarde.

## Le Jeu Réseau (Serveur / Client)
Prototype basique Client/Serveur
On lance le serveur sur un pc.
On lance le client sur un autre pc et on le lie au premier en entrant l'adresse IP.






