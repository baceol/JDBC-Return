-- phpMyAdmin SQL Dump
-- version 5.0.2
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Oct 26, 2020 at 09:14 AM
-- Server version: 10.4.14-MariaDB
-- PHP Version: 7.4.10

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `football`
--

-- --------------------------------------------------------

--
-- Table structure for table `club`
--

CREATE TABLE `club` (
  `Id` int(11) NOT NULL,
  `Name` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `club`
--

INSERT INTO `club` (`Id`, `Name`) VALUES
(1, 'Select All'),
(2, 'Real Madrid'),
(3, 'PSG'),
(4, 'Arsenal'),
(5, 'Atletico Madrid');

-- --------------------------------------------------------

--
-- Table structure for table `matchresult`
--

CREATE TABLE `matchresult` (
  `Id` int(11) NOT NULL,
  `Id_team1` int(11) NOT NULL,
  `Id_team2` int(11) NOT NULL,
  `Score1` int(11) NOT NULL,
  `Score2` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `matchresult`
--

INSERT INTO `matchresult` (`Id`, `Id_team1`, `Id_team2`, `Score1`, `Score2`) VALUES
(1, 2, 5, 3, 7),
(2, 3, 5, 3, 2),
(3, 2, 4, 5, 4),
(4, 4, 3, 2, 2),
(5, 3, 2, 3, 0),
(6, 5, 4, 1, 0);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `club`
--
ALTER TABLE `club`
  ADD PRIMARY KEY (`Id`);

--
-- Indexes for table `matchresult`
--
ALTER TABLE `matchresult`
  ADD PRIMARY KEY (`Id`),
  ADD KEY `Team_1` (`Id_team1`),
  ADD KEY `Team_2` (`Id_team2`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `club`
--
ALTER TABLE `club`
  MODIFY `Id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;

--
-- AUTO_INCREMENT for table `matchresult`
--
ALTER TABLE `matchresult`
  MODIFY `Id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=10;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `matchresult`
--
ALTER TABLE `matchresult`
  ADD CONSTRAINT `Team_1` FOREIGN KEY (`Id_team1`) REFERENCES `club` (`Id`),
  ADD CONSTRAINT `Team_2` FOREIGN KEY (`Id_team2`) REFERENCES `club` (`Id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
