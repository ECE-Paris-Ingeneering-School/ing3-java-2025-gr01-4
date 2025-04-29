# 🛒 ing3-java-2025-gr01-4 🛒

![JAVA](https://img.shields.io/badge/Java-ED8B00?style=for-the-badge&logo=openjdk&logoColor=white)![MYSQL](https://img.shields.io/badge/MySQL-00000F?style=for-the-badge&logo=mysql&logoColor=white)

<br/>

<figure style="align: center">
  <img src="Logo Vulpixia.jpg" alt="Logo" style="width: 30%; max-width: 800px;"/>
</figure>

Projet de Java de l'équipe 4 du groupe 1 composé de Minh-Duc PHAN, Jerry CHENG, Pierre-Louis CHARBONNIER et William BENOIT


Ce projet à pour objectif de créer une application d'achat en ligne avec interface graphique coder uniquement en JAVA  et du
MYSQL pour gérer la database.

<br/>

## 💻 Structure of the project 💻

<br/>

```
├── /Controleur
│   ├── CommandeController.java
│   ├── MainController.java
│   ├── ProduitController.java
│   ├── UtilisateurController.java
│   └── main.java
├── /DAO
│   ├── CommandeDAO.java
│   ├── CommandeDAOImpl.java
│   ├── DatabaseConnection.java
│   ├── ProduitDAO.java
│   ├── ProduitDAOImpl.java
│   ├── PromotionDAO.java
│   ├── PromotionDAOImpl.java
│   ├── UtilisateurDAO.java
│   └── UtilisateurDAOImpl.java
├── /Modele
│   ├── Commande.java
│   ├── Produit.java
│   ├── Utilisateur.java
│   └── Promotion.java
└── /Vue
    ├── CommandeView.java
    ├── MainView.java
    ├── ProduitView.java
    ├── PromotionView.java
    ├── UtilisateurView.java
    └── vue.java

```

<br/>

## 📁 Database 📁

<br/>

La database de ce projet est composé de 5 classes : Utilisateurs, Adresses, Produits, Promotions et Commandes.

<figure style="align: center">
  <img src="BDD/Modèle Entité-Association.png" alt="Database" style="width: 70%; max-width: 800px;"/>
</figure>

## 📁 Diagramme de classe 📁

<br/>

Le projet est composé de 4 packages : Modèle, Vue, Controleur et DAO.
Voici le diagramme de classe de ce projet :

<figure style="align: center">
  <img src="BDD/Diagramme de Classe.png" alt="DiagrammeDeClasse" style="width: 70%; max-width: 800px;"/>
</figure>
