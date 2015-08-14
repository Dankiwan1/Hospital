-- phpMyAdmin SQL Dump
-- version 4.0.4.1
-- http://www.phpmyadmin.net
--
-- Host: 127.0.0.1
-- Generation Time: Apr 23, 2015 at 01:29 AM
-- Server version: 5.5.32
-- PHP Version: 5.4.19

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Database: `mertmerghospital`
--
CREATE DATABASE IF NOT EXISTS `mertmerghospital` DEFAULT CHARACTER SET latin1 COLLATE latin1_swedish_ci;
USE `mertmerghospital`;

-- --------------------------------------------------------

--
-- Table structure for table `hospitalmedicine`
--

CREATE TABLE IF NOT EXISTS `hospitalmedicine` (
  `MEDICINE_CODE` varchar(250) NOT NULL,
  `MEDICINE_INITIALS` varchar(250) NOT NULL,
  `MEDICINE_NAME` varchar(250) NOT NULL,
  `MEDICINE_QUANTITY` varchar(100) DEFAULT NULL,
  `MEDICINE_COST` int(250) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `hospitalmedicine`
--

INSERT INTO `hospitalmedicine` (`MEDICINE_CODE`, `MEDICINE_INITIALS`, `MEDICINE_NAME`, `MEDICINE_QUANTITY`, `MEDICINE_COST`) VALUES
('M.M.C T001', 'P.A', 'Panadol', '100gm', 10),
('M.M.C T002', 'A.P.C', 'APC', '50gm', 15),
('M.M.C T001', 'P.A', 'Panadol', '100gm', 10),
('M.M.C T002', 'A.P.C', 'APC', '50gm', 15);

-- --------------------------------------------------------

--
-- Table structure for table `hospitaltests`
--

CREATE TABLE IF NOT EXISTS `hospitaltests` (
  `TEST_CODE` varchar(50) NOT NULL,
  `TEST_NAME` varchar(100) NOT NULL,
  `TEST_INITIALS` varchar(20) NOT NULL,
  `TEST_COST` int(100) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Dumping data for table `hospitaltests`
--

INSERT INTO `hospitaltests` (`TEST_CODE`, `TEST_NAME`, `TEST_INITIALS`, `TEST_COST`) VALUES
('M.M.C 001', 'Blood stain', 'BS', 150),
('M.M.C 002', 'Blood Grouping', 'BG', 200),
('M.M.C003', 'Blood sugar', 'BSS', 200),
('M.M.C 004', 'Brecellin Test', 'BCT', 300),
('M.M.C 005', 'Cholestrol', 'CHL', 1000),
('M.M.C 006', 'ESR', 'ESR', 250),
('M.M.C 007', 'Gram stain &wep prep', 'GM.WP', 300),
('M.M.C 008', 'HB', 'HB', 300),
('M.M.C 009', 'H.pylori', 'H.Pylori', 500),
('M.M.C 010', 'Hepatitis B', 'H.P.B', 500),
('M.M.C 010', 'Malaria Test Kit', 'MTK', 250),
('M.M.C 001', 'Blood stain', 'BS', 150),
('M.M.C 002', 'Blood Grouping', 'BG', 200),
('M.M.C003', 'Blood sugar', 'BSS', 200),
('M.M.C 004', 'Brecellin Test', 'BCT', 300),
('M.M.C 005', 'Cholestrol', 'CHL', 1000),
('M.M.C 006', 'ESR', 'ESR', 250),
('M.M.C 007', 'Gram stain &wep prep', 'GM.WP', 300),
('M.M.C 008', 'HB', 'HB', 300),
('M.M.C 009', 'H.pylori', 'H.Pylori', 500),
('M.M.C 010', 'Hepatitis B', 'H.P.B', 500),
('M.M.C 010', 'Malaria Test Kit', 'MTK', 250);

-- --------------------------------------------------------

--
-- Table structure for table `hospital_patient_category`
--

CREATE TABLE IF NOT EXISTS `hospital_patient_category` (
  `Category_id` int(11) NOT NULL AUTO_INCREMENT,
  `Category_name` varchar(30) NOT NULL,
  PRIMARY KEY (`Category_id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=10 ;

--
-- Dumping data for table `hospital_patient_category`
--

INSERT INTO `hospital_patient_category` (`Category_id`, `Category_name`) VALUES
(1, 'Laboratory'),
(2, 'Child Welfare Clinic (CWC)'),
(3, 'Family Planning'),
(4, 'Antenatal'),
(5, 'Postnatal'),
(6, 'Out Patient <5'),
(7, 'Out Patient > 5'),
(8, 'VCT '),
(9, 'Tettenus');

-- --------------------------------------------------------

--
-- Table structure for table `patient`
--

CREATE TABLE IF NOT EXISTS `patient` (
  `PATIENT_ID` int(10) NOT NULL AUTO_INCREMENT,
  `TITLE` varchar(10) NOT NULL,
  `FIRST_NAME` varchar(20) NOT NULL,
  `LAST_NAME` varchar(20) NOT NULL,
  `GENDER` varchar(20) NOT NULL,
  `EMAIL` varchar(50) DEFAULT NULL,
  `TELPHONE` varchar(60) DEFAULT NULL,
  `MOBILE_NO` varchar(60) DEFAULT NULL,
  `REGISTRATION_DATE` date DEFAULT NULL,
  `TIMESTAMP` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`PATIENT_ID`),
  KEY `PATIENT_ID` (`PATIENT_ID`)
) ENGINE=MyISAM  DEFAULT CHARSET=latin1 AUTO_INCREMENT=8 ;

--
-- Dumping data for table `patient`
--

INSERT INTO `patient` (`PATIENT_ID`, `TITLE`, `FIRST_NAME`, `LAST_NAME`, `GENDER`, `EMAIL`, `TELPHONE`, `MOBILE_NO`, `REGISTRATION_DATE`, `TIMESTAMP`) VALUES
(1, 'Miss.', 'Mary', 'Wangui', 'Female', 'mary@gmail.com', '12345556', '0734123456', '2014-04-02', '2014-04-03 10:42:44'),
(2, 'Mr.', 'Daniel', 'Kihia', 'Male', 'dankiwan1@gmail.com', '0724955506', '0724955506', '2014-04-03', '2014-04-03 12:08:39'),
(3, 'Mr.', 'Gilbert', 'Byegon', 'Male', 'gkbyegon@gmail.com', '0723046533', '0723046533', '2014-04-06', '2014-04-06 16:00:29'),
(4, 'Mr.', 'Peter', 'Ndumbu', 'Male', '', '07263762', '07246782', '2014-04-07', '2014-04-07 02:57:11'),
(5, 'Mr.', 'Julius', 'Maina', 'Male', 'maina@yahoo.com', '0724955506', '0736282728', '2014-04-07', '2014-04-07 02:58:38'),
(6, 'Mrs.', 'Milkah', 'Wanjiru', 'Female', '', '08393783', '03838388', '2014-04-07', '2014-04-07 07:33:10'),
(7, 'Mr.', 'Peter', 'Wanyonyi', 'Male', '', '0747348736', '0747348736', '2014-04-07', '2014-04-07 08:49:01');

-- --------------------------------------------------------

--
-- Table structure for table `patient_billing`
--

CREATE TABLE IF NOT EXISTS `patient_billing` (
  `PATIENT_ID` int(11) NOT NULL,
  `DATE` date NOT NULL,
  `CONSULTATION_FEE` decimal(10,2) NOT NULL DEFAULT '0.00',
  `MEDICAL_FEE` decimal(10,2) NOT NULL DEFAULT '0.00',
  `LAB_FEE` decimal(10,2) NOT NULL DEFAULT '0.00',
  KEY `PATIENT_ID` (`PATIENT_ID`),
  KEY `PATIENT_ID_2` (`PATIENT_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `patient_billing`
--

INSERT INTO `patient_billing` (`PATIENT_ID`, `DATE`, `CONSULTATION_FEE`, `MEDICAL_FEE`, `LAB_FEE`) VALUES
(1, '2014-04-02', '300.00', '0.00', '0.00'),
(2, '2014-04-03', '300.00', '45.00', '500.00'),
(3, '2014-04-06', '300.00', '15.00', '200.00'),
(4, '2014-04-07', '300.00', '30.00', '500.00'),
(5, '2014-04-07', '300.00', '0.00', '0.00'),
(6, '2014-04-07', '300.00', '20.00', '1000.00'),
(7, '2014-04-07', '300.00', '60.00', '300.00');

-- --------------------------------------------------------

--
-- Table structure for table `patient_category`
--

CREATE TABLE IF NOT EXISTS `patient_category` (
  `PATIENT_ID` int(15) NOT NULL,
  `PATIENT_CATEGORY` varchar(100) NOT NULL,
  `GENDER` varchar(30) NOT NULL,
  `MARITAL_STATUS` varchar(20) NOT NULL,
  `BLOOD_GROUP` varchar(10) NOT NULL,
  `TOWN` varchar(20) NOT NULL,
  `VILLAGE` varchar(20) NOT NULL,
  `REGISTRATION_DATE` varchar(30) NOT NULL,
  `AGE` int(11) NOT NULL,
  `STATUS` varchar(15) NOT NULL,
  `IMAGE` varchar(30) DEFAULT NULL,
  KEY `PATIENT_ID` (`PATIENT_ID`),
  KEY `PATIENT_ID_2` (`PATIENT_ID`),
  KEY `PATIENT_ID_3` (`PATIENT_ID`),
  KEY `PATIENT_ID_4` (`PATIENT_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `patient_category`
--

INSERT INTO `patient_category` (`PATIENT_ID`, `PATIENT_CATEGORY`, `GENDER`, `MARITAL_STATUS`, `BLOOD_GROUP`, `TOWN`, `VILLAGE`, `REGISTRATION_DATE`, `AGE`, `STATUS`, `IMAGE`) VALUES
(1, 'Antenatal', 'Female', 'Single', 'B-', 'Nyahururu', 'Kiamwangi', '2014/4/2', 35, 'First-Time', 'null'),
(2, 'Laboratory', 'Male', 'Single', 'B+', 'Nairobi', 'Kimathi', '2014/4/3', 24, 'First-Time', 'null'),
(3, 'Out Patient <5', 'Male', 'Married', 'O+', 'Bomet', 'Bomet', '2014/4/6', 22, 'First-Time', 'null'),
(4, 'Laboratory', 'Male', 'Single', 'B+', 'Nairobi', 'Westlands', '2014/4/7', 24, 'First-Time', 'null'),
(5, 'Postnatal', 'Male', 'Married', 'AB-', 'Kiriaini', 'Kagioini', '2014/4/7', 24, 'First-Time', 'null'),
(6, 'Laboratory', 'Female', 'Married', 'B-', 'Kakamega', 'kilifi', '2014/4/7', 22, 'First-Time', 'null'),
(7, 'Laboratory', 'Male', 'Single', 'B-', 'Kirinyaga', 'Watiri', '2014/4/7', 24, 'First-Time', 'null');

-- --------------------------------------------------------

--
-- Table structure for table `patient_diagnosis`
--

CREATE TABLE IF NOT EXISTS `patient_diagnosis` (
  `PATIENT_ID` int(11) NOT NULL,
  `SYMPTOMS` varchar(200) NOT NULL,
  `TEST_RECOMMEND` varchar(249) NOT NULL DEFAULT '0',
  `DIAGNOSIS` varchar(200) NOT NULL,
  `DATE` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `DATE1` date DEFAULT NULL,
  KEY `PATIENT_ID` (`PATIENT_ID`),
  KEY `PATIENT_ID_2` (`PATIENT_ID`),
  KEY `PATIENT_ID_3` (`PATIENT_ID`),
  KEY `PATIENT_ID_4` (`PATIENT_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `patient_diagnosis`
--

INSERT INTO `patient_diagnosis` (`PATIENT_ID`, `SYMPTOMS`, `TEST_RECOMMEND`, `DIAGNOSIS`, `DATE`, `DATE1`) VALUES
(1, 'Fever\r\nheadache', 'Malaria Test Kit', '', '2014-04-03 11:02:28', '2014-04-03'),
(2, 'Headache', 'Hepatitis B', 'Low blood in the system', '2014-04-03 12:11:18', '2014-04-03'),
(3, 'Fever,high blood pressure', 'Blood sugar', 'you have low blood sugar', '2014-04-06 16:13:36', '2014-04-06'),
(4, 'Backache', 'Hepatitis B', 'You are diagnosed with ', '2014-04-07 03:01:48', '2014-04-07'),
(6, 'stomach ache', 'Cholestrol', 'Diagnosed with common cold', '2014-04-07 07:35:25', '2014-04-07'),
(7, 'Headache', 'HB', 'you have malaria', '2014-04-07 08:52:47', '2014-04-07');

-- --------------------------------------------------------

--
-- Table structure for table `patient_diet_advice`
--

CREATE TABLE IF NOT EXISTS `patient_diet_advice` (
  `PATIENT_ID` int(11) NOT NULL,
  `DATE` date NOT NULL,
  `ADVICE` varchar(255) DEFAULT NULL,
  KEY `PATIENT_ID` (`PATIENT_ID`),
  KEY `PATIENT_ID_2` (`PATIENT_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `patient_injection`
--

CREATE TABLE IF NOT EXISTS `patient_injection` (
  `PATIENT_ID` int(11) NOT NULL,
  `NAME` varchar(250) DEFAULT NULL,
  `DATE` date NOT NULL,
  `DESCRIPTION` varchar(250) DEFAULT NULL,
  `INJECTION_DATE` varchar(150) DEFAULT NULL,
  `STATUS` varchar(20) DEFAULT NULL,
  KEY `PATIENT_ID` (`PATIENT_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `patient_lab_bill`
--

CREATE TABLE IF NOT EXISTS `patient_lab_bill` (
  `PATIENT_ID` int(11) NOT NULL,
  `TEST_NAME` varchar(50) DEFAULT NULL,
  `LAB_FEE` float(10,2) DEFAULT NULL,
  `DATE` date NOT NULL,
  KEY `PATIENT_ID` (`PATIENT_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `patient_lab_bill`
--

INSERT INTO `patient_lab_bill` (`PATIENT_ID`, `TEST_NAME`, `LAB_FEE`, `DATE`) VALUES
(1, 'Malaria Test Kit', 250.00, '2014-04-03'),
(2, 'Hepatitis B', 500.00, '2014-04-03'),
(3, 'Blood sugar', 200.00, '2014-04-06'),
(4, 'Hepatitis B', 500.00, '2014-04-07'),
(6, 'Cholestrol', 1000.00, '2014-04-07'),
(7, 'HB', 300.00, '2014-04-07');

-- --------------------------------------------------------

--
-- Table structure for table `patient_medicine`
--

CREATE TABLE IF NOT EXISTS `patient_medicine` (
  `PATIENT_ID` int(50) NOT NULL,
  `MEDICINE_CODE` varchar(100) DEFAULT NULL,
  `DATE` date DEFAULT NULL,
  `MEDICINE_NAME` varchar(100) DEFAULT NULL,
  `DRUG_QUANTITY` varchar(100) NOT NULL,
  `ITEMS` int(10) NOT NULL,
  `DOSAGE` varchar(100) NOT NULL,
  `ADMINISTRATION` varchar(10) NOT NULL,
  `FREQUENCY` varchar(20) NOT NULL,
  `MEDICINE_DESCRIPTION` varchar(255) DEFAULT NULL,
  `Status` varchar(30) DEFAULT 'Not Given',
  KEY `PATIENT_ID` (`PATIENT_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `patient_medicine`
--

INSERT INTO `patient_medicine` (`PATIENT_ID`, `MEDICINE_CODE`, `DATE`, `MEDICINE_NAME`, `DRUG_QUANTITY`, `ITEMS`, `DOSAGE`, `ADMINISTRATION`, `FREQUENCY`, `MEDICINE_DESCRIPTION`, `Status`) VALUES
(1, 'M.M.C T002', '2014-04-03', 'APC', '50gm', 3, '1tb', 'Orally', 'Once Daily', '', 'pending'),
(2, 'M.M.C T002', '2014-04-03', 'APC', '50gm', 3, '1tb', 'Orally', 'Once Daily', '', 'given'),
(3, 'M.M.C T002', '2014-04-06', 'APC', '50gm', 1, '1tb', 'Orally', 'Once Daily', '', 'given'),
(4, 'M.M.C T002', '2014-04-07', 'APC', '50gm', 1, '1tb', 'Orally', 'Once Daily', '', 'given'),
(4, 'M.M.C T002', '2014-04-07', 'APC', '50gm', 1, '1tb', 'Orally', 'Once Daily', '', 'given'),
(6, 'M.M.C T001', '2014-04-07', 'Panadol', '100gm', 2, '1tb', 'Orally', 'Once Daily', '', 'given'),
(7, 'M.M.C T002', '2014-04-07', 'APC', '50gm', 4, '1tb', 'Orally', 'Once Daily', '', 'given');

-- --------------------------------------------------------

--
-- Table structure for table `patient_medicine_bill`
--

CREATE TABLE IF NOT EXISTS `patient_medicine_bill` (
  `PATIENT_ID` int(11) NOT NULL,
  `DATE` date NOT NULL,
  `MEDICINE_NAME` varchar(250) NOT NULL,
  `M_COST` float(10,2) NOT NULL,
  KEY `PATIENT_ID` (`PATIENT_ID`),
  KEY `PATIENT_ID_2` (`PATIENT_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `patient_medicine_bill`
--

INSERT INTO `patient_medicine_bill` (`PATIENT_ID`, `DATE`, `MEDICINE_NAME`, `M_COST`) VALUES
(1, '2014-04-03', 'APC', 45.00),
(2, '2014-04-03', 'APC', 45.00),
(3, '2014-04-06', 'APC', 15.00),
(4, '2014-04-07', 'APC', 15.00),
(4, '2014-04-07', 'APC', 15.00),
(6, '2014-04-07', 'Panadol', 20.00),
(7, '2014-04-07', 'APC', 60.00);

-- --------------------------------------------------------

--
-- Table structure for table `patient_observation`
--

CREATE TABLE IF NOT EXISTS `patient_observation` (
  `PATIENT_ID` int(11) NOT NULL,
  `BODY_TEMPERATURE` double DEFAULT NULL,
  `BLOOD_PRESSURE` varchar(30) DEFAULT NULL,
  `PULSE_RATE` double DEFAULT NULL,
  `WEIGHT` double DEFAULT NULL,
  `DATE` date DEFAULT NULL,
  `TIME_STAMP` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `REMARKS` varchar(240) DEFAULT NULL,
  KEY `PATIENT_ID` (`PATIENT_ID`),
  KEY `PATIENT_ID_2` (`PATIENT_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `patient_observation`
--

INSERT INTO `patient_observation` (`PATIENT_ID`, `BODY_TEMPERATURE`, `BLOOD_PRESSURE`, `PULSE_RATE`, `WEIGHT`, `DATE`, `TIME_STAMP`, `REMARKS`) VALUES
(1, 37, '120/80', 72, 67, '2014-04-02', '2014-04-02 15:01:04', 'Normal Condition'),
(2, 37, '120/80', 72, 68, '2014-04-03', '2014-04-03 12:09:36', 'Normal condition'),
(3, 38, '120/80', 72, 61, '2014-04-06', '2014-04-06 16:05:08', 'urgent attention'),
(4, 38, '120/81', 72, 67, '2014-04-07', '2014-04-07 02:59:25', 'Quick attention'),
(6, 37, '120/80', 72, 60, '2014-04-07', '2014-04-07 07:33:49', ''),
(7, 37, '120/80', 72, 67, '2014-04-07', '2014-04-07 08:50:08', 'Normal condition');

-- --------------------------------------------------------

--
-- Table structure for table `patient_prescription`
--

CREATE TABLE IF NOT EXISTS `patient_prescription` (
  `PATIENT_ID` int(100) NOT NULL,
  `MEDICINE` int(100) NOT NULL,
  `INJECTION` int(100) NOT NULL,
  `DIET_ADVICE` varchar(245) NOT NULL,
  KEY `PATIENT_ID` (`PATIENT_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `patient_test`
--

CREATE TABLE IF NOT EXISTS `patient_test` (
  `PATIENT_ID` int(11) NOT NULL,
  `TEST_CODE` varchar(50) DEFAULT NULL,
  `TEST_NAME` varchar(250) DEFAULT NULL,
  `DESCRIPTION` varchar(249) DEFAULT NULL,
  `DATE` date DEFAULT NULL,
  `STATUS` varchar(30) DEFAULT NULL,
  KEY `PATIENT_ID` (`PATIENT_ID`),
  KEY `PATIENT_ID_2` (`PATIENT_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `patient_test`
--

INSERT INTO `patient_test` (`PATIENT_ID`, `TEST_CODE`, `TEST_NAME`, `DESCRIPTION`, `DATE`, `STATUS`) VALUES
(1, 'M.M.C 010', 'Malaria Test Kit', 'Check the malaria parisites', '2014-04-03', 'carried out'),
(2, 'M.M.C 010', 'Hepatitis B', '', '2014-04-03', 'carried out'),
(3, 'M.M.C003', 'Blood sugar', 'check for blood sugar', '2014-04-06', 'carried out'),
(4, 'M.M.C 010', 'Hepatitis B', 'Check on the ..', '2014-04-07', 'carried out'),
(6, 'M.M.C 005', 'Cholestrol', '', '2014-04-07', 'carried out'),
(7, 'M.M.C 008', 'HB', '', '2014-04-07', 'carried out');

-- --------------------------------------------------------

--
-- Table structure for table `payment`
--

CREATE TABLE IF NOT EXISTS `payment` (
  `Receipt_No` int(30) NOT NULL AUTO_INCREMENT,
  `PATIENT_ID` int(30) NOT NULL,
  `Date` date NOT NULL,
  `Amount` float(10,2) NOT NULL,
  PRIMARY KEY (`Receipt_No`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=7 ;

--
-- Dumping data for table `payment`
--

INSERT INTO `payment` (`Receipt_No`, `PATIENT_ID`, `Date`, `Amount`) VALUES
(1, 2, '2014-04-03', 845.00),
(2, 2, '2014-04-03', 2.00),
(3, 3, '2014-04-06', 515.00),
(4, 4, '2014-04-07', 830.00),
(5, 6, '2014-04-07', 1320.00),
(6, 7, '2014-04-07', 660.00);

-- --------------------------------------------------------

--
-- Table structure for table `tests`
--

CREATE TABLE IF NOT EXISTS `tests` (
  `PATIENT_ID` int(11) NOT NULL,
  `TEST_NAME` varchar(200) NOT NULL,
  `TEST_RESULTS` varchar(200) NOT NULL,
  `OTHER_DETAILS` varchar(200) NOT NULL,
  `DATE` date NOT NULL,
  KEY `PATIENT_ID` (`PATIENT_ID`),
  KEY `PATIENT_ID_2` (`PATIENT_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `tests`
--

INSERT INTO `tests` (`PATIENT_ID`, `TEST_NAME`, `TEST_RESULTS`, `OTHER_DETAILS`, `DATE`) VALUES
(1, 'Malaria Test Kit', 'Few Traces of parasites', 'Low immune system', '2014-04-03'),
(2, 'Hepatitis B', 'Low ksiej of jdj', '', '2014-04-03'),
(3, 'Blood sugar', 'low level blood sugar', '', '2014-04-06'),
(4, 'Hepatitis B', 'High level of ', '', '2014-04-07'),
(6, 'Cholestrol', 'high level of cholestrol', '', '2014-04-07'),
(7, 'HB', 'Lovel level of bllood', '', '2014-04-07');

-- --------------------------------------------------------

--
-- Table structure for table `user`
--

CREATE TABLE IF NOT EXISTS `user` (
  `USER_ID` int(10) NOT NULL,
  `TITLE` varchar(20) NOT NULL,
  `FIRST_NAME` varchar(15) NOT NULL,
  `LAST_NAME` varchar(15) NOT NULL,
  `CATEGORY` varchar(15) NOT NULL,
  `username` varchar(20) NOT NULL,
  `password` varchar(20) NOT NULL,
  `location` varchar(20) NOT NULL,
  `MOBILE_NO` int(15) NOT NULL,
  `Gender` varchar(20) NOT NULL,
  `IMAGE` blob NOT NULL,
  `status` varchar(5) NOT NULL DEFAULT 'no',
  PRIMARY KEY (`USER_ID`),
  UNIQUE KEY `username` (`username`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Dumping data for table `user`
--

INSERT INTO `user` (`USER_ID`, `TITLE`, `FIRST_NAME`, `LAST_NAME`, `CATEGORY`, `username`, `password`, `location`, `MOBILE_NO`, `Gender`, `IMAGE`, `status`) VALUES
(20, 'DR.', 'Daniel', 'Kihia', 'Admin', 'Admin', 'Admin', 'Kahawa', 724955506, 'Male', 0x6e756c6c, 'no');

-- --------------------------------------------------------

--
-- Table structure for table `user_log`
--

CREATE TABLE IF NOT EXISTS `user_log` (
  `USER_ID` int(100) NOT NULL,
  `Status` varchar(30) NOT NULL,
  `login_time` datetime DEFAULT NULL,
  `logout_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `user_log`
--

INSERT INTO `user_log` (`USER_ID`, `Status`, `login_time`, `logout_time`) VALUES
(20, 'Logged out', '2014-04-03 11:09:32', '2014-04-03 08:12:15'),
(20, 'Logged out', '2014-04-03 11:15:57', '2014-04-03 08:20:34'),
(20, 'Logged out', '2014-04-03 11:21:01', '2014-04-03 08:52:38'),
(20, 'Logged out', '2014-04-03 11:52:54', '2014-04-03 09:34:05'),
(20, 'Logged out', '2014-04-03 00:34:30', '2014-04-03 09:35:16'),
(20, 'Logged out', '2014-04-03 00:35:35', '2014-04-03 09:49:10'),
(20, 'Logged out', '2014-04-03 00:49:27', '2014-04-03 09:50:28'),
(20, 'Logged out', '2014-04-03 00:51:33', '2014-04-03 09:52:27'),
(20, 'Logged out', '2014-04-03 00:54:24', '2014-04-03 10:01:59'),
(20, 'Logged out', '2014-04-03 01:02:19', '2014-04-03 10:04:43'),
(20, 'Logged out', '2014-04-03 01:05:28', '2014-04-03 10:07:56'),
(20, 'Logged out', '2014-04-03 01:08:18', '2014-04-03 10:36:39'),
(20, 'Logged out', '2014-04-03 01:37:48', '2014-04-03 10:41:53'),
(20, 'Logged out', '2014-04-03 01:42:15', '2014-04-03 10:46:23'),
(20, 'Logged out', '2014-04-03 01:46:40', '2014-04-03 10:49:27'),
(20, 'Logged out', '2014-04-03 01:49:42', '2014-04-03 10:51:48'),
(20, 'Logged out', '2014-04-03 01:52:53', '2014-04-03 12:06:24'),
(20, 'Logged out', '2014-04-03 03:07:08', '2014-04-03 12:18:10'),
(20, 'Logged out', '2014-04-03 03:18:25', '2014-04-03 12:19:35'),
(20, 'Logged out', '2014-04-03 03:19:49', '2014-04-03 12:31:29'),
(20, 'Logged out', '2014-04-03 03:31:47', '2014-04-03 12:35:07'),
(20, 'Logged out', '2014-04-03 03:35:23', '2014-04-03 12:36:17'),
(20, 'Logged out', '2014-04-03 03:36:33', '2014-04-03 12:38:32'),
(14, 'Logged out', '2014-04-03 03:38:45', '2014-04-03 12:42:54'),
(14, 'Logged out', '2014-04-03 03:43:05', '2014-04-03 12:43:32'),
(14, 'Logged out', '2014-04-03 03:43:27', '2014-04-03 12:43:32'),
(20, 'Logged out', '2014-04-03 03:55:10', '2014-04-03 12:55:37'),
(20, 'Logged out', '2014-04-03 03:55:53', '2014-04-03 12:56:24'),
(20, 'Logged out', '2014-04-03 03:56:41', '2014-04-03 12:56:57'),
(20, 'Logged out', '2014-04-03 04:10:47', '2014-04-03 13:53:24'),
(20, 'Logged out', '2014-04-03 04:12:26', '2014-04-03 13:53:24'),
(20, 'Logged out', '2014-04-03 04:13:32', '2014-04-03 13:53:24'),
(20, 'Logged out', '2014-04-03 04:53:56', '2014-04-03 13:54:09'),
(20, 'Logged out', '2014-04-03 04:56:00', '2014-04-03 13:56:06'),
(20, 'Logged out', '2014-04-03 04:57:32', '2014-04-03 13:57:39'),
(20, 'Logged out', '2014-04-03 04:59:23', '2014-04-03 13:59:29'),
(14, 'Logged out', '2014-04-03 04:59:42', '2014-04-03 13:59:49'),
(20, 'Logged out', '2014-04-03 05:00:56', '2014-04-03 14:01:02'),
(20, 'Logged out', '2014-04-03 05:01:22', '2014-04-03 14:01:28'),
(12, 'Logged out', '2014-04-03 05:09:12', '2014-04-03 14:12:47'),
(20, 'Logged out', '2014-04-03 05:10:59', '2014-04-03 14:11:05'),
(12, 'Logged out', '2014-04-03 05:12:21', '2014-04-03 14:12:47'),
(20, 'Logged out', '2014-04-03 05:13:02', '2014-04-07 02:50:10'),
(20, 'Logged out', '2014-04-06 06:49:22', '2014-04-07 02:50:10'),
(20, 'Logged out', '2014-04-07 05:40:43', '2014-04-07 02:50:10'),
(20, 'Logged out', '2014-04-07 05:50:26', '2014-04-07 02:55:30'),
(20, 'Logged out', '2014-04-07 05:55:46', '2014-04-07 07:24:08'),
(20, 'Logged out', '2014-04-07 10:32:12', '2014-04-07 07:37:33'),
(20, 'Logged out', '2014-04-07 11:18:28', '2014-04-07 08:22:18'),
(20, 'Logged out', '2014-04-07 11:38:04', '2014-04-07 08:47:30'),
(20, 'Logged out', '2014-04-07 11:38:56', '2014-04-07 08:47:30'),
(20, 'Logged out', '2014-04-07 11:48:02', '2014-04-07 12:48:28'),
(20, 'Logged out', '2014-04-10 00:09:45', '2014-04-10 11:18:01'),
(20, 'Logged out', '2014-04-10 02:18:26', '2014-04-10 14:43:48'),
(20, 'Logged out', '2014-04-10 05:44:13', '2014-04-10 14:44:24'),
(12, 'Logged out', '2014-04-10 05:44:48', '2014-04-10 14:49:26'),
(20, 'Logged out', '2014-04-10 05:51:25', '2014-04-10 14:53:11'),
(12, 'Logged out', '2014-04-10 05:53:23', '2014-04-10 15:11:40'),
(20, 'Logged out', '2014-04-10 06:11:58', '2014-04-10 15:14:34'),
(20, 'Logged out', '2014-04-10 06:14:26', '2014-04-10 15:14:34'),
(12, 'Logged out', '2014-04-10 06:14:49', '2014-04-10 15:16:53'),
(16, 'Logged out', '2014-04-10 06:17:10', '2014-04-10 15:18:02'),
(20, 'Logged out', '2014-04-10 06:18:19', '2014-04-10 15:18:28'),
(16, 'Logged out', '2014-04-10 06:18:35', '2014-04-10 15:38:10'),
(12, 'Logged out', '2014-04-10 06:21:21', '2014-04-10 15:30:00'),
(12, 'Logged out', '2014-04-10 06:30:34', '2014-04-10 15:35:27'),
(20, 'Logged out', '2014-04-10 06:36:04', '2014-04-10 15:36:10'),
(16, 'Logged out', '2014-04-10 06:36:22', '2014-04-10 15:38:10'),
(16, 'Logged out', '2014-04-10 06:38:27', '2014-04-10 15:40:10'),
(16, 'Logged out', '2014-04-10 06:40:38', '2014-04-10 15:41:01'),
(20, 'Logged out', '2014-04-10 06:41:11', '2014-04-10 15:42:47'),
(16, 'Logged out', '2014-04-10 06:43:05', '2014-04-10 15:43:58'),
(16, 'Logged out', '2014-04-10 06:44:17', '2014-04-10 15:44:32'),
(20, 'Logged out', '2014-04-10 06:44:40', '2014-04-10 15:44:56'),
(12, 'Logged out', '2014-04-10 06:45:07', '2014-04-10 15:46:32'),
(20, 'Logged out', '2014-04-10 06:46:41', '2014-04-10 15:49:47'),
(20, 'Logged out', '2014-04-11 00:03:23', '2014-04-11 09:05:31'),
(20, 'Logged out', '2014-04-11 00:05:15', '2014-04-11 09:05:31'),
(20, 'Logged out', '2015-04-20 01:40:42', '2015-04-20 20:41:56'),
(20, 'Logged out', '2015-04-20 01:45:05', '2015-04-20 20:45:33'),
(20, 'Logged out', '2015-04-21 08:56:22', '2015-04-21 15:58:49'),
(20, 'Logged out', '2015-04-21 08:59:00', '2015-04-21 16:01:54'),
(20, 'Logged out', '2015-04-21 09:11:08', '2015-04-21 16:11:36'),
(20, 'Logged out', '2015-04-21 09:17:07', '2015-04-21 16:18:13'),
(20, 'Logged out', '2015-04-21 09:19:56', '2015-04-21 16:20:51');

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
