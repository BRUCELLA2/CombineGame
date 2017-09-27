# CombineGame

CombineGame est un ensemble de jeu dont l'objectif est de trouver une combinaison secrète. La console sert d'interface d'affichage à CombineGame.
La version actuelle de CombineGame propose 2 jeux (More or Less et Mastermind) qui peuvent être joué sous 3 modes (Challengeur, Défenseur, Duel).

## More or Less

Le but du jeu de More or Less est de découvrir une combinaison de chiffres en un nombre limité d'essais. Chaque proposition est comparée à la combinaison à découvrir. Le résultat de cette comparaison est donné sous la forme d'une chaine de caractère de +, de - ou de = selon que le chiffre de la combinaison mystère est supérieur, inférieur ou égale à celui proposé.

* Dans le mode de jeu Challenger, le joueur doit trouver une combinaison générée par l'ordinateur.
* Dans le mode de jeu Defenseur, le joueur fournit une combinaison et l'ordinateur doit essayer de trouver cette combinaison.
* Dans le mode de jeu Duel, le joueur et l'ordinateur s'affronte. Le joueur doit trouver une combinaison générée par l'ordinateur et l'ordinateur doit trouver une combinaison fournit par le joueur.

## Mastermind

Le but du jeu Mastermind est de découvrir une combinaison de chiffres en un nombre limité d'essais. Chaque proposition est comparée à la combinaison à découvrir. Dans ce jeu, le résultat de cette comparaison indique combien de chiffres sont présents et bien positionnés par rapport à la combinaison mystère et  et combien de chiffres sont présents et mal positionnés.

* Dans le mode de jeu Challenger, le joueur doit trouver une combinaison générée par l'ordinateur.
* Dans le mode de jeu Defenseur, le joueur fournit une combinaison et l'ordinateur doit essayer de trouver cette combinaison.
* Dans le mode de jeu Duel, le joueur et l'ordinateur s'affronte. Le joueur doit trouver une combinaison générée par l'ordinateur et l'ordinateur doit trouver une combinaison fournit par le joueur.


# Compilation / Execution

Ce projet utilise Log4j. Les jar nécessaires à son utilisation se trouve dans le dossier /lib. Avant la compilation et l'exécution de CombineGame, il est nécessaire que ces jar soient ajoutés à votre projet dans votre IDE. Le niveau de log est à définir dans le fichier log4j2.xml. Le changement de niveau est dynamique et peut se faire durant l'exécution de CombineGame (avec un délai prévu de maximum 30 secondes). Le fichier de logs se trouvera dans un dossier \logs et se nomme CombineGame.log.

Le fichier config.properties situé dans le dossier /resources permet d'établir divers paramétrage. 

* Le mode développeur permettant de voir la combinaison à découvrir dés le début du jeu s'active en passant le paramètre "DEVELOPER_MODE" à true.
* Le nombre d'essai possibles est à définir avec le paramètre "NB_MAX_TRIES"
* Le nombre de chiffres constituants les combinaisons secrètes des jeux est paramétrable avec le paramètre "NB_DIGITS_MYSTERY"
* La valeur maximale que peut prendre chaque chiffres de la combinaison est définit avec le paramètre "MAX_VALUE_DIGIT" pour le jeu More or Less et "NB_COLORS" pour le jeu Mastermind.

 