-- phpMyAdmin SQL Dump
-- version 4.5.1
-- http://www.phpmyadmin.net
--
-- Client :  127.0.0.1
-- Généré le :  Ven 23 Octobre 2020 à 17:45
-- Version du serveur :  10.1.16-MariaDB
-- Version de PHP :  5.6.24

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de données :  `testserie`
--

-- --------------------------------------------------------

--
-- Structure de la table `personnage`
--

CREATE TABLE `personnage` (
  `ID` int(11) NOT NULL,
  `NOM` varchar(40) NOT NULL,
  `ID_SERIE` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Contenu de la table `personnage`
--

INSERT INTO `personnage` (`ID`, `NOM`, `ID_SERIE`) VALUES
(1, 'Jean Neige', 1),
(2, 'Doigts boudinnes', 1),
(3, 'Thirion', 1),
(4, 'Le docteur', 2),
(5, 'Le 2nd docteur', 2),
(6, 'Le 3nd docteur', 2),
(7, 'Renard Meule D''or', 3),
(8, 'Dana Scully', 3),
(9, 'Dolores', 4),
(10, 'Gregory Maison', 5),
(11, 'John Appartement', 5),
(12, 'Sylvie F2bis', 5);

-- --------------------------------------------------------

--
-- Structure de la table `serie`
--

CREATE TABLE `serie` (
  `ID` int(11) NOT NULL,
  `NOM` varchar(40) NOT NULL,
  `GENRE` varchar(40) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Contenu de la table `serie`
--

INSERT INTO `serie` (`ID`, `NOM`, `GENRE`) VALUES
(1, 'Guimauve Throne', 'Fantasy'),
(2, 'Docteur Qui', 'SF'),
(3, 'Y fichier', 'ParanoSF'),
(4, 'Eastworld', 'SF'),
(5, 'Docteur Maison', 'Mobilier Medical');

--
-- Index pour les tables exportées
--

--
-- Index pour la table `personnage`
--
ALTER TABLE `personnage`
  ADD PRIMARY KEY (`ID`),
  ADD KEY `ID_SERIE` (`ID_SERIE`);

--
-- Index pour la table `serie`
--
ALTER TABLE `serie`
  ADD PRIMARY KEY (`ID`);

--
-- AUTO_INCREMENT pour les tables exportées
--

--
-- AUTO_INCREMENT pour la table `personnage`
--
ALTER TABLE `personnage`
  MODIFY `ID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=13;
--
-- AUTO_INCREMENT pour la table `serie`
--
ALTER TABLE `serie`
  MODIFY `ID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;
--
-- Contraintes pour les tables exportées
--

--
-- Contraintes pour la table `personnage`
--
ALTER TABLE `personnage`
  ADD CONSTRAINT `personnage_ibfk_1` FOREIGN KEY (`ID_SERIE`) REFERENCES `serie` (`ID`);

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;