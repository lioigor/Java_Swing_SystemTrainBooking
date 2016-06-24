-- phpMyAdmin SQL Dump
-- version 3.5.1
-- http://www.phpmyadmin.net
--
-- Host: 127.0.0.1
-- Generation Time: Jun 24, 2016 at 06:37 PM
-- Server version: 5.5.25
-- PHP Version: 5.3.13

SET SQL_MODE="NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Database: `systemtrainbookingdb`
--

-- --------------------------------------------------------

--
-- Table structure for table `adminaccounts`
--

CREATE TABLE IF NOT EXISTS `adminaccounts` (
  `adminID` int(11) NOT NULL,
  `adminLogin` varchar(48) NOT NULL,
  `adminPassword` varchar(48) NOT NULL,
  PRIMARY KEY (`adminID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `adminaccounts`
--

INSERT INTO `adminaccounts` (`adminID`, `adminLogin`, `adminPassword`) VALUES
(1, 'igor', '2204');

-- --------------------------------------------------------

--
-- Table structure for table `trains`
--

CREATE TABLE IF NOT EXISTS `trains` (
  `numberTrain` int(11) NOT NULL,
  `trainName` varchar(45) NOT NULL,
  `date` varchar(45) NOT NULL,
  `departureTime` varchar(45) NOT NULL,
  `arrivalTime` varchar(45) NOT NULL,
  `dispatchStation` varchar(45) NOT NULL,
  `stationDestination` varchar(45) NOT NULL,
  `price` int(45) NOT NULL,
  PRIMARY KEY (`numberTrain`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Table structure for table `usersaccounts`
--

CREATE TABLE IF NOT EXISTS `usersaccounts` (
  `userID` int(11) NOT NULL,
  `userLogin` varchar(45) NOT NULL,
  `userPassword` varchar(45) NOT NULL,
  `userName` varchar(45) NOT NULL,
  `userSurName` varchar(45) NOT NULL,
  `bankAccount` varchar(45) NOT NULL,
  PRIMARY KEY (`userID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
