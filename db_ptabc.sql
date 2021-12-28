-- phpMyAdmin SQL Dump
-- version 5.0.4
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Dec 28, 2021 at 04:24 PM
-- Server version: 10.4.16-MariaDB
-- PHP Version: 7.4.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `db_ptabc`
--

-- --------------------------------------------------------

--
-- Table structure for table `data_pegawai`
--

CREATE TABLE `data_pegawai` (
  `no_pegawai` int(5) NOT NULL,
  `nama_pegawai` varchar(40) NOT NULL,
  `jabatan` varchar(20) NOT NULL,
  `gaji_pokok` int(10) NOT NULL,
  `jumlah_hari_masuk` int(5) NOT NULL,
  `total_gaji` int(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `data_pegawai`
--

INSERT INTO `data_pegawai` (`no_pegawai`, `nama_pegawai`, `jabatan`, `gaji_pokok`, `jumlah_hari_masuk`, `total_gaji`) VALUES
(1111, 'Farhan', 'Direktur', 5000000, 29, 4833334),
(2222, 'Budi', 'Manajer', 3500000, 27, 3150000),
(3333, 'Dwi', 'Administrasi', 2750000, 28, 2566667),
(4444, 'Indra', 'Karyawan', 2000000, 29, 1933334),
(5555, 'Maya', 'Karyawan', 2000000, 26, 1733334);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `data_pegawai`
--
ALTER TABLE `data_pegawai`
  ADD PRIMARY KEY (`no_pegawai`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
