-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Jul 23, 2026 at 05:57 PM
-- Server version: 10.4.32-MariaDB
-- PHP Version: 8.2.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `attendance_management_system`
--

-- --------------------------------------------------------

--
-- Table structure for table `admin`
--

CREATE TABLE `admin` (
  `admin_id` int(11) NOT NULL,
  `admin_name` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `admin`
--

INSERT INTO `admin` (`admin_id`, `admin_name`, `email`) VALUES
(1, 'Nimal Perera', 'nimal@usjp.lk'),
(2, 'Kamal Silva', 'kamal@usjp.lk');

-- --------------------------------------------------------

--
-- Table structure for table `attendance`
--

CREATE TABLE `attendance` (
  `attendance_id` int(11) NOT NULL,
  `session_id` int(11) NOT NULL,
  `student_id` int(11) NOT NULL,
  `status` enum('Pending','Present','Absent') NOT NULL DEFAULT 'Pending',
  `marked_time` datetime DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `attendance`
--

INSERT INTO `attendance` (`attendance_id`, `session_id`, `student_id`, `status`, `marked_time`) VALUES
(1, 1, 5, 'Present', '2026-07-20 08:32:10'),
(2, 2, 5, 'Present', '2026-07-21 08:31:05'),
(3, 5, 5, 'Absent', NULL),
(4, 3, 17, 'Present', '2026-07-20 10:32:50'),
(5, 4, 17, 'Absent', NULL);

-- --------------------------------------------------------

--
-- Table structure for table `attendance_session`
--

CREATE TABLE `attendance_session` (
  `session_id` int(11) NOT NULL,
  `session_code` varchar(20) NOT NULL,
  `session_date` date NOT NULL,
  `start_time` time NOT NULL,
  `end_time` time NOT NULL,
  `lecturer_id` int(11) NOT NULL,
  `subject_id` int(11) NOT NULL,
  `class_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `attendance_session`
--

INSERT INTO `attendance_session` (`session_id`, `session_code`, `session_date`, `start_time`, `end_time`, `lecturer_id`, `subject_id`, `class_id`) VALUES
(1, 'CS101-001', '2026-07-20', '08:00:00', '10:30:00', 11, 1, 1),
(2, 'CS101-002', '2026-07-21', '08:00:00', '10:15:00', 11, 1, 1),
(3, 'CS105-001', '2026-07-20', '10:30:00', '12:00:00', 9, 5, 3),
(4, 'CS105-002', '2026-07-22', '10:30:00', '12:00:00', 9, 5, 3),
(5, 'CS107-001', '2026-07-23', '01:30:00', '03:30:00', 11, 7, 1);

-- --------------------------------------------------------

--
-- Table structure for table `class`
--

CREATE TABLE `class` (
  `class_id` int(11) NOT NULL,
  `class_name` varchar(255) DEFAULT NULL,
  `program` varchar(255) DEFAULT NULL,
  `year` int(11) NOT NULL,
  `stream` varchar(255) DEFAULT NULL,
  `semester` int(11) NOT NULL,
  `academic_year` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `class`
--

INSERT INTO `class` (`class_id`, `class_name`, `program`, `year`, `stream`, `semester`, `academic_year`) VALUES
(1, 'CS 1st Year', 'General', 1, 'Computer Science', 1, '2026'),
(2, 'CS 2nd Year', 'General', 2, 'Computer Science', 1, '2026'),
(3, 'CS 3rd Year General', 'General', 3, 'Computer Science', 1, '2026'),
(4, 'CS 3rd Year Special', 'Special', 3, 'Computer Science', 1, '2026'),
(5, 'CS 4th Year Special', 'Special', 4, 'Computer Science', 1, '2026'),
(6, 'IT 1st Year', 'General', 1, 'Information Technology', 1, '2026'),
(7, 'IT 2nd Year', 'General', 2, 'Information Technology', 1, '2026'),
(8, 'IT 3rd Year', 'General', 3, 'Information Technology', 1, '2026');

-- --------------------------------------------------------

--
-- Table structure for table `enrollment`
--

CREATE TABLE `enrollment` (
  `enrollment_id` int(11) NOT NULL,
  `student_id` int(11) NOT NULL,
  `class_id` int(11) NOT NULL,
  `enroll_date` date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `enrollment`
--

INSERT INTO `enrollment` (`enrollment_id`, `student_id`, `class_id`, `enroll_date`) VALUES
(1, 5, 1, '2026-01-15'),
(2, 17, 3, '2026-01-18');

-- --------------------------------------------------------

--
-- Table structure for table `lecturer`
--

CREATE TABLE `lecturer` (
  `lecturer_id` int(11) NOT NULL,
  `lecturer_name` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `lecturer`
--

INSERT INTO `lecturer` (`lecturer_id`, `lecturer_name`, `email`) VALUES
(3, 'Dr. Nuwan Fernando', 'nuwan@usjp.lk'),
(4, 'Ms. Dilani Jayasinghe', 'dilani@usjp.lk'),
(7, 'Mr. M.D.R. Perera', 'mdr.perera@sjp.ac.lk'),
(8, 'Mrs. W.M.K.S. Illmini', 'wmks.illmini@sjp.ac.lk'),
(9, 'Ms. N.H. Wanigasingha', 'nh.wanigasingha@sjp.ac.lk'),
(10, 'Mrs. Surani Perera', 'surani.perera@sjp.ac.lk'),
(11, 'Mr. Tisura Ambuldeniya', 'tisura.ambuldeniya@sjp.ac.lk'),
(12, 'Miss Lavanka Harshani', 'lavanka.harshani@sjp.ac.lk'),
(13, 'Dr. K.A. Silva', 'ka.silva@sjp.ac.lk'),
(14, 'Mr. D.M. Fernando', 'dm.fernando@sjp.ac.lk'),
(15, 'Mrs. P.N. Jayasinghe', 'pn.jayasinghe@sjp.ac.lk'),
(16, 'Ms. H.M. Wickramasinghe', 'hm.wickramasinghe@sjp.ac.lk');

-- --------------------------------------------------------

--
-- Table structure for table `student`
--

CREATE TABLE `student` (
  `student_id` int(11) NOT NULL,
  `registration_no` varchar(255) DEFAULT NULL,
  `student_name` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `class_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `student`
--

INSERT INTO `student` (`student_id`, `registration_no`, `student_name`, `email`, `class_id`) VALUES
(5, 'AS20240001', 'Binuri Vihangi', 'binuri@sjp.ac.lk', 1),
(17, 'AS2023530', 'Gagan Kavishka', 'gagan.kavishka@gmail.com', 3);

-- --------------------------------------------------------

--
-- Table structure for table `student_subject`
--

CREATE TABLE `student_subject` (
  `student_subject_id` int(11) NOT NULL,
  `student_id` int(11) NOT NULL,
  `subject_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `student_subject`
--

INSERT INTO `student_subject` (`student_subject_id`, `student_id`, `subject_id`) VALUES
(1, 5, 1),
(2, 5, 2),
(3, 5, 3),
(4, 5, 4),
(5, 5, 5),
(6, 5, 6),
(7, 5, 7),
(8, 17, 5),
(9, 17, 6),
(10, 17, 7),
(11, 17, 8);

-- --------------------------------------------------------

--
-- Table structure for table `subject`
--

CREATE TABLE `subject` (
  `subject_id` int(11) NOT NULL,
  `subject_code` varchar(255) DEFAULT NULL,
  `subject_name` varchar(255) DEFAULT NULL,
  `credits` int(11) NOT NULL,
  `lecturer_id` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `subject`
--

INSERT INTO `subject` (`subject_id`, `subject_code`, `subject_name`, `credits`, `lecturer_id`) VALUES
(1, 'CS101', 'C Programming', 2, 11),
(2, 'CS102', 'Programming Fundamentals', 2, 4),
(3, 'CS103', 'Object-Oriented Programming', 2, 7),
(4, 'CS104', 'Data Structures and Algorithms', 2, 8),
(5, 'CS105', 'Database Management Systems', 2, 9),
(6, 'CS106', 'Software Development', 2, 10),
(7, 'CS107', 'Software Engineering', 2, 11),
(8, 'CS108', 'Computer System Architecture', 2, 12),
(9, 'CS109', 'Operating Systems', 2, 13),
(10, 'CS110', 'Computer Networks', 2, 14),
(11, 'CS111', 'Web Development', 2, 15),
(12, 'CS112', 'Full Stack Development', 2, 16),
(13, 'CS113', 'Mobile Application Development Lab', 1, 7),
(14, 'CS114', 'Cyber Security Practical', 1, 8),
(15, 'CS115', 'Information Security Lab', 1, 9),
(16, 'CS116', 'Human Computer Interaction', 1, 10);

-- --------------------------------------------------------

--
-- Table structure for table `user`
--

CREATE TABLE `user` (
  `user_id` int(11) NOT NULL,
  `username` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL,
  `role` enum('Admin','Lecturer','Student') NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `user`
--

INSERT INTO `user` (`user_id`, `username`, `password`, `role`) VALUES
(1, 'admin1', 'admin123', 'Admin'),
(2, 'admin2', 'admin456', 'Admin'),
(3, 'lecturer1', 'lect123', 'Lecturer'),
(4, 'lecturer2', 'lect456', 'Lecturer'),
(5, 'student1', 'stud123', 'Student'),
(7, 'mdr.perera', '123456', 'Lecturer'),
(8, 'wmks.illmini', '123457', 'Lecturer'),
(9, 'nh.wanigasingha', '123458', 'Lecturer'),
(10, 'surani.perera', '123459', 'Lecturer'),
(11, 'tisura', '123450', 'Lecturer'),
(12, 'lavanka', '123451', 'Lecturer'),
(13, 'ka.silva', '123452', 'Lecturer'),
(14, 'dm.fernando', '123453', 'Lecturer'),
(15, 'pn.jayasinghe', '123454', 'Lecturer'),
(16, 'hm.wickramasinghe', '123455', 'Lecturer'),
(17, 'gugu', 'gugu15', 'Student');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `admin`
--
ALTER TABLE `admin`
  ADD PRIMARY KEY (`admin_id`),
  ADD UNIQUE KEY `email` (`email`);

--
-- Indexes for table `attendance`
--
ALTER TABLE `attendance`
  ADD PRIMARY KEY (`attendance_id`),
  ADD UNIQUE KEY `uq_session_student` (`session_id`,`student_id`),
  ADD KEY `fk_attendance_student` (`student_id`);

--
-- Indexes for table `attendance_session`
--
ALTER TABLE `attendance_session`
  ADD PRIMARY KEY (`session_id`),
  ADD UNIQUE KEY `session_code` (`session_code`),
  ADD KEY `fk_session_lecturer` (`lecturer_id`),
  ADD KEY `fk_session_subject` (`subject_id`),
  ADD KEY `fk_session_class` (`class_id`);

--
-- Indexes for table `class`
--
ALTER TABLE `class`
  ADD PRIMARY KEY (`class_id`);

--
-- Indexes for table `enrollment`
--
ALTER TABLE `enrollment`
  ADD PRIMARY KEY (`enrollment_id`),
  ADD UNIQUE KEY `uq_student_class` (`student_id`,`class_id`),
  ADD KEY `fk_enrollment_class` (`class_id`);

--
-- Indexes for table `lecturer`
--
ALTER TABLE `lecturer`
  ADD PRIMARY KEY (`lecturer_id`),
  ADD UNIQUE KEY `email` (`email`);

--
-- Indexes for table `student`
--
ALTER TABLE `student`
  ADD PRIMARY KEY (`student_id`),
  ADD UNIQUE KEY `registration_no` (`registration_no`),
  ADD UNIQUE KEY `email` (`email`),
  ADD KEY `fk_student_class` (`class_id`);

--
-- Indexes for table `student_subject`
--
ALTER TABLE `student_subject`
  ADD PRIMARY KEY (`student_subject_id`),
  ADD UNIQUE KEY `uq_student_subject` (`student_id`,`subject_id`),
  ADD KEY `fk_student_subject_subject` (`subject_id`);

--
-- Indexes for table `subject`
--
ALTER TABLE `subject`
  ADD PRIMARY KEY (`subject_id`),
  ADD UNIQUE KEY `subject_code` (`subject_code`),
  ADD KEY `fk_subject_lecturer` (`lecturer_id`);

--
-- Indexes for table `user`
--
ALTER TABLE `user`
  ADD PRIMARY KEY (`user_id`),
  ADD UNIQUE KEY `username` (`username`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `attendance`
--
ALTER TABLE `attendance`
  MODIFY `attendance_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- AUTO_INCREMENT for table `attendance_session`
--
ALTER TABLE `attendance_session`
  MODIFY `session_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- AUTO_INCREMENT for table `class`
--
ALTER TABLE `class`
  MODIFY `class_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=9;

--
-- AUTO_INCREMENT for table `enrollment`
--
ALTER TABLE `enrollment`
  MODIFY `enrollment_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT for table `student_subject`
--
ALTER TABLE `student_subject`
  MODIFY `student_subject_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=12;

--
-- AUTO_INCREMENT for table `subject`
--
ALTER TABLE `subject`
  MODIFY `subject_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=18;

--
-- AUTO_INCREMENT for table `user`
--
ALTER TABLE `user`
  MODIFY `user_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=20;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `admin`
--
ALTER TABLE `admin`
  ADD CONSTRAINT `fk_admin_user` FOREIGN KEY (`admin_id`) REFERENCES `user` (`user_id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `attendance`
--
ALTER TABLE `attendance`
  ADD CONSTRAINT `fk_attendance_session` FOREIGN KEY (`session_id`) REFERENCES `attendance_session` (`session_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `fk_attendance_student` FOREIGN KEY (`student_id`) REFERENCES `student` (`student_id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `attendance_session`
--
ALTER TABLE `attendance_session`
  ADD CONSTRAINT `fk_session_class` FOREIGN KEY (`class_id`) REFERENCES `class` (`class_id`) ON UPDATE CASCADE,
  ADD CONSTRAINT `fk_session_lecturer` FOREIGN KEY (`lecturer_id`) REFERENCES `lecturer` (`lecturer_id`) ON UPDATE CASCADE,
  ADD CONSTRAINT `fk_session_subject` FOREIGN KEY (`subject_id`) REFERENCES `subject` (`subject_id`) ON UPDATE CASCADE;

--
-- Constraints for table `enrollment`
--
ALTER TABLE `enrollment`
  ADD CONSTRAINT `fk_enrollment_class` FOREIGN KEY (`class_id`) REFERENCES `class` (`class_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `fk_enrollment_student` FOREIGN KEY (`student_id`) REFERENCES `student` (`student_id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `lecturer`
--
ALTER TABLE `lecturer`
  ADD CONSTRAINT `fk_lecturer_user` FOREIGN KEY (`lecturer_id`) REFERENCES `user` (`user_id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `student`
--
ALTER TABLE `student`
  ADD CONSTRAINT `fk_student_class` FOREIGN KEY (`class_id`) REFERENCES `class` (`class_id`) ON UPDATE CASCADE,
  ADD CONSTRAINT `fk_student_user` FOREIGN KEY (`student_id`) REFERENCES `user` (`user_id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `student_subject`
--
ALTER TABLE `student_subject`
  ADD CONSTRAINT `fk_student_subject_student` FOREIGN KEY (`student_id`) REFERENCES `student` (`student_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `fk_student_subject_subject` FOREIGN KEY (`subject_id`) REFERENCES `subject` (`subject_id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `subject`
--
ALTER TABLE `subject`
  ADD CONSTRAINT `fk_subject_lecturer` FOREIGN KEY (`lecturer_id`) REFERENCES `lecturer` (`lecturer_id`) ON UPDATE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
