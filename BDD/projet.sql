-- phpMyAdmin SQL Dump
-- version 5.2.0
-- https://www.phpmyadmin.net/
--
-- Hôte : 127.0.0.1:3306
-- Généré le : lun. 31 mars 2025 à 07:43
-- Version du serveur : 8.0.31
-- Version de PHP : 8.0.26

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de données : `projet`
--
CREATE DATABASE IF NOT EXISTS projet;
    USE projet;
-- --------------------------------------------------------

--
-- Structure de la table `adresse`
--

DROP TABLE IF EXISTS `adresse`;
CREATE TABLE IF NOT EXISTS `adresse` (
  `ID` int NOT NULL AUTO_INCREMENT COMMENT 'ID Adresse',
  `Numéro` int DEFAULT NULL COMMENT 'Numéro',
  `Rue` varchar(50) DEFAULT NULL COMMENT 'Rue',
  `Code Postal` int NOT NULL COMMENT 'Code Postal',
  `Ville` varchar(50) DEFAULT NULL COMMENT 'Ville',
  `Pays` varchar(50) DEFAULT NULL COMMENT 'Pays',
  PRIMARY KEY (`ID`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- --------------------------------------------------------

--
-- Structure de la table `commande`
--

DROP TABLE IF EXISTS `commande`;
CREATE TABLE IF NOT EXISTS `commande` (
  `ID` int NOT NULL AUTO_INCREMENT COMMENT 'ID de la commande',
  `ID_Client` int NOT NULL COMMENT 'ID du client à l''origine de la commande',
  `ID_Produit` int NOT NULL COMMENT 'ID du produit commandé',
  `Quantite` int NOT NULL COMMENT 'Quantite commandé',
  `Prix` double NOT NULL COMMENT 'Prix de la commande',
  `Date` date NOT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- --------------------------------------------------------

--
-- Structure de la table `produit`
--

DROP TABLE IF EXISTS `produit`;
CREATE TABLE IF NOT EXISTS `produit` (
  `ID` int NOT NULL AUTO_INCREMENT COMMENT 'ID Produit',
  `Marque` varchar(30) DEFAULT NULL COMMENT 'Marque du produit',
  `Nom` varchar(30) DEFAULT NULL COMMENT 'Nom du produit',
  `Prix` double NOT NULL COMMENT 'Prix du produit',
  `Descritpion` text NOT NULL COMMENT 'Description du produit',
  `Image` varchar(30) NOT NULL COMMENT 'Image(s) du produit',
  PRIMARY KEY (`ID`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- --------------------------------------------------------

--
-- Structure de la table `promotion`
--

DROP TABLE IF EXISTS `promotion`;
CREATE TABLE IF NOT EXISTS `promotion` (
  `ID` int NOT NULL AUTO_INCREMENT,
  `ID_Produit` int NOT NULL,
  `Quantite` int NOT NULL,
  `Prix` double NOT NULL,
  `ID_Commande` int NOT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- --------------------------------------------------------

--
-- Structure de la table `utilisateur`
--

DROP TABLE IF EXISTS `utilisateur`;
CREATE TABLE IF NOT EXISTS `utilisateur` (
  `ID` int NOT NULL AUTO_INCREMENT COMMENT 'ID de l''utilisateur',
  `Mail` varchar(30) NOT NULL,
  `Mot_De_Passe` varchar(30) NOT NULL,
  `Admin` tinyint(1) NOT NULL DEFAULT '0',
  PRIMARY KEY (`ID`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
