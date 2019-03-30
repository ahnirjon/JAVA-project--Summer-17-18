-- phpMyAdmin SQL Dump
-- version 4.8.2
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Sep 04, 2018 at 01:30 AM
-- Server version: 10.1.34-MariaDB
-- PHP Version: 7.2.7

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `d1t1`
--

-- --------------------------------------------------------

--
-- Table structure for table `book`
--

CREATE TABLE `book` (
  `bookTitle` varchar(25) NOT NULL,
  `authorName` varchar(25) NOT NULL,
  `publicationYear` int(11) NOT NULL,
  `availableQuantity` int(11) NOT NULL,
  `bookId` varchar(6) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `book`
--

INSERT INTO `book` (`bookTitle`, `authorName`, `publicationYear`, `availableQuantity`, `bookId`) VALUES
('', 'sdsd', 2345, 223, ''),
('physics', 'Antoniyo', 2003, 1, '003'),
('science', 'Alex Hunter', 2000, 4, '007'),
('Java', 'Sir. Niloy', 2018, 15, '11');

-- --------------------------------------------------------

--
-- Table structure for table `borrowinfo`
--

CREATE TABLE `borrowinfo` (
  `borrowId` varchar(14) NOT NULL,
  `bookId` varchar(6) NOT NULL,
  `userId` varchar(14) NOT NULL,
  `borrowDate` varchar(10) NOT NULL,
  `returnDate` varchar(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `borrowinfo`
--

INSERT INTO `borrowinfo` (`borrowId`, `bookId`, `userId`, `borrowDate`, `returnDate`) VALUES
('1092018235936', '007', 'U-001', '2018-09-03', '2018-9-10'),
('1092018235947', '003', 'U-001', '2018-09-03', '2018-9-10'),
('1192018000008', '007', 'U-001', '2018-09-04', '2018-9-11');

-- --------------------------------------------------------

--
-- Table structure for table `customer`
--

CREATE TABLE `customer` (
  `userId` varchar(14) NOT NULL,
  `customerName` varchar(12) NOT NULL,
  `phoneNumber` int(11) NOT NULL,
  `address` varchar(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `customer`
--

INSERT INTO `customer` (`userId`, `customerName`, `phoneNumber`, `address`) VALUES
('20180831235927', 'Nirjon', 1, '179/4 Narayanganj'),
('20180901001412', 'Richardlison', 1921558955, '123 popcorn '),
('20180901001529', 'Richardlison', 5665655, 'popcorn street'),
('20180901001920', 'Alex morgan', 4545544, 'huawei Street'),
('U-0000', 'HUrrah', 454564, 'sdsdsd'),
('U-001', 'AH Nirjon', 26555445, 'Edge 52 street'),
('U-002', '8596', 5, '898'),
('U-004', '9', 5, '-');

-- --------------------------------------------------------

--
-- Table structure for table `employee`
--

CREATE TABLE `employee` (
  `userId` varchar(14) NOT NULL,
  `employeeName` varchar(30) NOT NULL,
  `phoneNumber` int(11) NOT NULL,
  `role` varchar(15) NOT NULL,
  `salary` float NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `employee`
--

INSERT INTO `employee` (`userId`, `employeeName`, `phoneNumber`, `role`, `salary`) VALUES
('0001', 'Mr.jorina', 265485489, 'General', 12000),
('0046', 'Anika Ahmed', 54646465, 'Manager', 56789),
('0047', 'Sanjida', 546464, 'Librarian', 1000000),
('1234', 'ah!', 12334, 'Boss', 9923),
('90', 'johny gaddar', 1921882735, 'Headmaster', 5);

-- --------------------------------------------------------

--
-- Table structure for table `login`
--

CREATE TABLE `login` (
  `userId` varchar(14) NOT NULL,
  `password` varchar(15) NOT NULL,
  `status` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `login`
--

INSERT INTO `login` (`userId`, `password`, `status`) VALUES
('0001', '0001', 2),
('0045', '0045', 2),
('0046', '0046', 2),
('0047', '0047', 2),
('20180901001920', '004', 1),
('90', '90', 2),
('L-001', '001', 0),
('U-0000', '', 1),
('U-001', '001', 1),
('U-002', '2', 1),
('U-004', '', 1);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `book`
--
ALTER TABLE `book`
  ADD PRIMARY KEY (`bookId`);

--
-- Indexes for table `borrowinfo`
--
ALTER TABLE `borrowinfo`
  ADD PRIMARY KEY (`borrowId`);

--
-- Indexes for table `customer`
--
ALTER TABLE `customer`
  ADD PRIMARY KEY (`userId`);

--
-- Indexes for table `employee`
--
ALTER TABLE `employee`
  ADD PRIMARY KEY (`userId`);

--
-- Indexes for table `login`
--
ALTER TABLE `login`
  ADD UNIQUE KEY `userId` (`userId`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
