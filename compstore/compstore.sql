-- phpMyAdmin SQL Dump
-- version 3.5.1
-- http://www.phpmyadmin.net
--
-- Värd: localhost
-- Skapad: 16 maj 2013 kl 08:56
-- Serverversion: 5.5.24-log
-- PHP-version: 5.3.13

SET SQL_MODE="NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Databas: `compstore`
--

-- --------------------------------------------------------

--
-- Tabellstruktur `components`
--

CREATE TABLE IF NOT EXISTS `components` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) COLLATE utf8_swedish_ci NOT NULL,
  `type` int(10) NOT NULL,
  `price` int(10) NOT NULL,
  `qoh` int(10) NOT NULL,
  `description` text COLLATE utf8_swedish_ci NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 COLLATE=utf8_swedish_ci AUTO_INCREMENT=25 ;

--
-- Dumpning av Data i tabell `components`
--

INSERT INTO `components` (`id`, `name`, `type`, `price`, `qoh`, `description`) VALUES
(1, 'Super Gaming Motherboard', 1, 2500, 20, ''),
(2, 'Normal Motherboard', 1, 1800, 20, ''),
(3, 'Gaming Graphix Super', 3, 2200, 16, ''),
(4, 'Clear Media Graphics', 3, 2000, 27, ''),
(5, 'Budget Graphics Card', 3, 1400, 54, ''),
(6, 'Budget Processor', 2, 700, 23, ''),
(7, 'Normal Processor', 2, 1100, 35, ''),
(8, 'Fast Processor', 2, 1700, 23, ''),
(9, '1 * 2 GB RAM', 4, 500, 23, ''),
(10, '2 * 2 GB RAM', 4, 900, 13, ''),
(11, '4 * 2 GB RAM', 4, 1500, 12, ''),
(12, '4 * 4 GB RAM', 4, 2300, 7, ''),
(13, '500 GB - 7200 RPM', 5, 400, 46, ''),
(14, '1 TB - 7200 RPM', 5, 800, 35, ''),
(15, '2 TB - 7200 RPM', 5, 1100, 35, ''),
(16, '250 GB - SSD', 5, 1000, 23, ''),
(17, '500 GB - SSD', 5, 1500, 12, ''),
(18, 'Soft Sound', 6, 700, 34, ''),
(19, 'Cool Sound', 6, 1500, 34, ''),
(20, '100 Mbit Network', 7, 400, 5, ''),
(21, '1000 Mbit Network', 7, 700, 12, ''),
(22, 'Black Chassi', 8, 500, 23, ''),
(23, 'Black Chassi w. Plexi window', 8, 850, 14, ''),
(24, 'Cool Lit Chassi', 8, 1200, 7, '');

-- --------------------------------------------------------

--
-- Tabellstruktur `component_types`
--

CREATE TABLE IF NOT EXISTS `component_types` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) COLLATE utf8_swedish_ci NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 COLLATE=utf8_swedish_ci AUTO_INCREMENT=9 ;

--
-- Dumpning av Data i tabell `component_types`
--

INSERT INTO `component_types` (`id`, `name`) VALUES
(1, 'Moderkort'),
(2, 'Processor'),
(3, 'Grafikkort'),
(4, 'RAM-minne'),
(5, 'Hårddisk'),
(6, 'Ljudkort'),
(7, 'Nätverkskort'),
(8, 'Chassi');

-- --------------------------------------------------------

--
-- Tabellstruktur `computers`
--

CREATE TABLE IF NOT EXISTS `computers` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) COLLATE utf8_swedish_ci NOT NULL,
  `description` text COLLATE utf8_swedish_ci NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 COLLATE=utf8_swedish_ci AUTO_INCREMENT=5 ;

--
-- Dumpning av Data i tabell `computers`
--

INSERT INTO `computers` (`id`, `name`, `description`) VALUES
(1, 'Hemsurfdator', 'Dator som är bra för hemanvändare som inte har några stora krav på datorn.'),
(2, 'Arbetsstation', 'Dator som är bra för personer som arbetar mycket hemifrån.'),
(3, 'Mediadator', 'Dator som är bra på att hålla på med media, rendera bild och film, samt leka med grafik.'),
(4, 'Speldator', 'Dator som är bra för den som spelar mycket datorspel och därför kräver bra prestanda.');

-- --------------------------------------------------------

--
-- Tabellstruktur `cpu_comp`
--

CREATE TABLE IF NOT EXISTS `cpu_comp` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `computer_id` int(10) NOT NULL,
  `component_id` int(10) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 COLLATE=utf8_swedish_ci AUTO_INCREMENT=33 ;

--
-- Dumpning av Data i tabell `cpu_comp`
--

INSERT INTO `cpu_comp` (`id`, `computer_id`, `component_id`) VALUES
(1, 1, 2),
(2, 1, 6),
(3, 1, 5),
(4, 1, 9),
(5, 1, 13),
(6, 2, 2),
(7, 2, 7),
(8, 2, 10),
(9, 2, 5),
(10, 2, 14),
(11, 2, 22),
(12, 1, 22),
(15, 3, 1),
(16, 3, 4),
(17, 3, 8),
(18, 3, 11),
(19, 3, 15),
(20, 3, 19),
(21, 3, 20),
(22, 3, 23),
(23, 4, 1),
(24, 4, 3),
(25, 4, 8),
(26, 4, 12),
(27, 4, 15),
(28, 4, 17),
(29, 4, 19),
(30, 4, 21),
(31, 4, 24),
(32, 4, 15);

-- --------------------------------------------------------

--
-- Tabellstruktur `customers`
--

CREATE TABLE IF NOT EXISTS `customers` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `username` varchar(50) COLLATE utf8_swedish_ci NOT NULL,
  `password` varchar(50) COLLATE utf8_swedish_ci NOT NULL,
  `Name` varchar(50) COLLATE utf8_swedish_ci NOT NULL,
  `Address` varchar(50) COLLATE utf8_swedish_ci NOT NULL,
  `Mail` varchar(50) COLLATE utf8_swedish_ci NOT NULL,
  `Phone` varchar(15) COLLATE utf8_swedish_ci NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 COLLATE=utf8_swedish_ci AUTO_INCREMENT=3 ;

--
-- Dumpning av Data i tabell `customers`
--

INSERT INTO `customers` (`id`, `username`, `password`, `Name`, `Address`, `Mail`, `Phone`) VALUES
(1, 'blueprint', 'm0ng0b4rn', 'Martin Björling', 'Luthagsesplanaden 91, 75271 Uppsala', 'martinbjorling@gmail.com', '0737565044'),
(2, 'jocklas', '1377', 'Joakim Ejenstam', 'Rackarbergsgatan 28, 75232 Uppsala', 'joakim.ejenstam@gmail.com', '0701757379');

-- --------------------------------------------------------

--
-- Tabellstruktur `orders`
--

CREATE TABLE IF NOT EXISTS `orders` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `customer_id` int(10) NOT NULL,
  `computer_id` int(10) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 COLLATE=utf8_swedish_ci AUTO_INCREMENT=3 ;

--
-- Dumpning av Data i tabell `orders`
--

INSERT INTO `orders` (`id`, `customer_id`, `computer_id`) VALUES
(1, 1, 4),
(2, 2, 3);

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
