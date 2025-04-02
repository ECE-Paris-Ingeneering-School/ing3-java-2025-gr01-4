# 🛒 ing3-java-2025-gr01-4 🛒

![JAVA](https://img.shields.io/badge/Java-ED8B00?style=for-the-badge&logo=openjdk&logoColor=white)![MYSQL](https://img.shields.io/badge/MySQL-00000F?style=for-the-badge&logo=mysql&logoColor=white)

<br/>

Projet de Java de l'équipe 4 du groupe 1 composé de Minh-Duc Phan, Jerry Cheng, Pierre-Louis Charbonnier et William BENOIT


Ce projet à pour objectif de créer un code d'achat en ligne avec interface graphique coder uniquement en JAVA avec du
MYSQL pour gérer la database.

<br/>

### 💻 Structure of the project 💻

<br/>

```
├── /Controleur
│   ├── CommandeController.java
│   ├── MainController.java
│   ├── ProduitController.java
│   ├── UtilisateurController.java
│   └── main.java
├── /Dao
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
└── /Dao
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

La database de ce projet est composé de 4 classes : Utilisateurs, Produits, Promotions et Commandes

<figure style="align: center">
  <img src="BDD/Modèle Entité-Association.png" alt="Database" style="width: 70%; max-width: 800px;"/>
</figure>

## 📁 Diagramme de classe 📁

<br/>

Voici le diagramme de classe de ce projet :

<figure style="align: center">
  <img src="BDD/Diagramme de Classe.png" alt="DiagrammeDeClasse" style="width: 50%; max-width: 800px;"/>
</figure>