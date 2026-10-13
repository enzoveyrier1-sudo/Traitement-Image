PPM Image Processor — Java
Projet académique réalisé dans le cadre de l'UE NFA032 (L3 Informatique, CNAM).
Application Java de traitement d'images au format PPM (Portable PixMap), manipulant les données image octet par octet via une liste chaînée compressée (RLE).


Fonctionnalités
#
Opération
Description
1 Afficher la taille
Largeur × hauteur en pixels
2 Noir et blanc
Remplacement RGB par la moyenne des trois composantes
3 Foncer
Diminue la composante colorimétrique choisie (R / G / B)
4 Éclaircir
Augmente la composante colorimétrique choisie (R / G / B)
5 Enregistrer
Écriture du fichier .ppm modifié sur disque
6 Quitter
Ferme le menu



Architecture
src/
Main.java              Point d'entrée — instancie Menu et lance afficherMenu()
    Pixel.java             Couleur d'un pixel (R, G, B) et opérations colorimétriques
    Segment.java           Nœud de liste chaînée : pixel + nombre d'occurrences + suivant
    Image.java             Métadonnées image + tête de liste chaînée de Segment
    OperationImage.java    Interface Java déclarant les opérations disponibles
    Menu.java              Implémente OperationImage — I/O fichier + menu interactif


Structure de données — compression RLE
L'image n'est pas stockée pixel par pixel. Les pixels consécutifs de même couleur sont regroupés en segments, formant une liste chaînée compressée (Run-Length Encoding).

Image [largeur=4, hauteur=1, valeurMax=255]

  └─► Segment [pixel=(0,0,0), nombre=3, suivant=...]

        └─► Segment [pixel=(255,0,0), nombre=1, suivant=null]


Contraintes techniques respectées (barème NFA032)
Liste chaînée native — pas de LinkedList Java ni d'autre collection de la bibliothèque standard
Récursivité sur toutes les opérations de transformation (noirEtBlancRec, foncerRec, eclaircirRec)
Encapsulation stricte : tous les attributs en private, @Override systématique, this explicite
Interface Java (OperationImage) séparant le contrat des opérations de leur implémentation
Exceptions personnalisées (CouleurInvalide, ValeurInvalide) avec validation des saisies utilisateur
I/O bas niveau octet par octet — FileInputStream / FileOutputStream uniquement, sans aucune classe utilitaire de haut niveau


Format PPM supporté
Format P3 (ASCII) :

P3

# nom_du_fichier

<largeur> <hauteur>

<valeurMax>

R G B  R G B  R G B ...

La lecture est entièrement manuelle (sans Scanner) : saut de lignes d'en-tête, parsing des entiers caractère par caractère, reconstruction des pixels et compression à la volée dans la liste chaînée.


Lancer le projet
Prérequis : Java 11+ et la classe utilitaire Terminal.java (fournie par le CNAM).

# Compiler depuis la racine du projet
javac *.java

# Lancer
java Main

Le programme charge obligatoirement un fichier .ppm au démarrage, puis propose le menu d'opérations.


Exemple de session
Donnez le nom du fichier à ouvrir : exemple2.ppm

=== Menu ===

1- Afficher la taille de l'image

2- Mettre l'image en noir et blanc

3- Foncer une tonalité

4- Éclaircir une tonalité

5- Enregistrer l'image

6- Fermer le menu


Votre choix : 3
Quelle dominante voulez-vous foncer ? (R/G/B) : R
De combien ? : 30

Votre choix : 5
Nom du fichier de sortie : sortie.ppm
Fichier enregistré.


Compétences mobilisées
POO avancée — encapsulation, interface, polymorphisme, gestion d'exceptions
Structures de données — liste chaînée native, algorithme de compression RLE
Récursivité sur structures chaînées
I/O bas niveau — lecture/écriture binaire octet par octet en Java
Conception logicielle — séparation des responsabilités, respect d'un contrat d'interface



Projet NFA032 — CNAM L3 Informatique, 2026
Enzo Veyrier
