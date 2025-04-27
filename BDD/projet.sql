-- phpMyAdmin SQL Dump
-- version 5.2.0
-- https://www.phpmyadmin.net/
--
-- Hôte : 127.0.0.1:3306
-- Généré le : dim. 27 avr. 2025 à 20:32
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

-- --------------------------------------------------------

--
-- Structure de la table `adresse`
--

DROP TABLE IF EXISTS `adresse`;
CREATE TABLE IF NOT EXISTS `adresse` (
                                         `ID_Client` int NOT NULL,
                                         `Numero` int NOT NULL,
                                         `Rue` varchar(30) COLLATE utf8mb4_general_ci NOT NULL,
    `Ville` varchar(30) COLLATE utf8mb4_general_ci NOT NULL,
    `Code_Postale` int NOT NULL,
    `Pays` varchar(30) COLLATE utf8mb4_general_ci NOT NULL
    ) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

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
    ) ENGINE=MyISAM AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Déchargement des données de la table `commande`
--

INSERT INTO `commande` (`ID`, `ID_Client`, `ID_Produit`, `Quantite`, `Prix`, `Date`) VALUES
                                                                                         (3, 2, 3, 14, 37, '2025-04-26'),
                                                                                         (5, 2, 4, 1, 33.5, '2025-04-27'),
                                                                                         (1, 2, 1, 3, 77, '2025-04-26'),
                                                                                         (6, 2, 4, 1, 33.5, '2025-04-27'),
                                                                                         (7, 2, 1, 10, 44, '2025-04-27');

-- --------------------------------------------------------

--
-- Structure de la table `produit`
--

DROP TABLE IF EXISTS `produit`;
CREATE TABLE IF NOT EXISTS `produit` (
                                         `ID` int NOT NULL AUTO_INCREMENT COMMENT 'ID Produit',
                                         `Marque` varchar(30) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT 'Marque du produit',
    `Nom` varchar(30) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT 'Nom du produit',
    `Prix` double NOT NULL COMMENT 'Prix du produit',
    `Quantite` int NOT NULL COMMENT 'Quantité du produit',
    `Descritpion` text COLLATE utf8mb4_general_ci NOT NULL COMMENT 'Description du produit',
    `Image` varchar(30) COLLATE utf8mb4_general_ci NOT NULL COMMENT 'Image(s) du produit',
    PRIMARY KEY (`ID`)
    ) ENGINE=MyISAM AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Déchargement des données de la table `produit`
--

INSERT INTO `produit` (`ID`, `Marque`, `Nom`, `Prix`, `Quantite`, `Descritpion`, `Image`) VALUES
                                                                                              (1, 'Maison', 'cuvette', 9.99, 50, 'toilette', 'default.png'),
                                                                                              (2, 'Maison', 'tuyau', 10.05, 100, 'tuyau en fer', 'default.png'),
                                                                                              (3, 'Électronique', 'voiture', 250, 10, 'pasdepermis', 'default.png'),
                                                                                              (4, 'Maison', 'Jardin', 33.5, 8, 'Fleurs', '');

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
                                           PRIMARY KEY (`ID`)
    ) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Structure de la table `utilisateur`
--

DROP TABLE IF EXISTS `utilisateur`;
CREATE TABLE IF NOT EXISTS `utilisateur` (
                                             `ID` int NOT NULL AUTO_INCREMENT COMMENT 'ID de l''utilisateur',
                                             `Mail` varchar(30) COLLATE utf8mb4_general_ci NOT NULL,
    `Mot_De_Passe` varchar(30) COLLATE utf8mb4_general_ci NOT NULL,
    `Nom` varchar(255) COLLATE utf8mb4_general_ci NOT NULL COMMENT 'Nom et Prénom de l''utilisateur',
    `Sexe` tinyint(1) NOT NULL,
    `Admin` tinyint(1) NOT NULL DEFAULT '0',
    PRIMARY KEY (`ID`)
    ) ENGINE=MyISAM AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Déchargement des données de la table `utilisateur`
--

INSERT INTO `utilisateur` (`ID`, `Mail`, `Mot_De_Passe`, `Nom`, `Sexe`, `Admin`) VALUES
                                                                                     (1, 'ouioui', '123', 'test', 1, 1),
                                                                                     (2, 'nonon', '321', 'test2', 1, 0);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
