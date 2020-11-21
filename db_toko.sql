-- phpMyAdmin SQL Dump
-- version 5.0.2
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Nov 21, 2020 at 05:55 AM
-- Server version: 10.4.13-MariaDB
-- PHP Version: 7.4.7

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `db_toko`
--

-- --------------------------------------------------------

--
-- Table structure for table `mas_user`
--

CREATE TABLE `mas_user` (
  `uid` int(11) NOT NULL,
  `unama` varchar(100) NOT NULL,
  `uemail` varchar(50) NOT NULL,
  `utelp` varchar(20) NOT NULL,
  `ujk` varchar(20) NOT NULL,
  `ualamat` text NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `mas_user`
--

INSERT INTO `mas_user` (`uid`, `unama`, `uemail`, `utelp`, `ujk`, `ualamat`) VALUES
(2, 'Saif Alikhan', 'msaifa.071@gmail.com', '08976666495', 'Laki Laki', 'Sidoarjo'),
(10, 'Saif Alikhan', 'msaifa.07@gmail.com', 'Sidoarjo', 'Laki Laki', 'Sidoarjo'),
(18, 'Asip', 'saifpin@gmail.com', '0879785', 'Laki Laki', 'Wanita'),
(19, 'Alikhan', 'saif.asip@gmail.com', '08977456', 'Wanita', 'sda');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `mas_user`
--
ALTER TABLE `mas_user`
  ADD PRIMARY KEY (`uid`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `mas_user`
--
ALTER TABLE `mas_user`
  MODIFY `uid` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=22;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
