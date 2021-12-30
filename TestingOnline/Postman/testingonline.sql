-- phpMyAdmin SQL Dump
-- version 5.1.1
-- https://www.phpmyadmin.net/
--
-- Máy chủ: 127.0.0.1
-- Thời gian đã tạo: Th12 30, 2021 lúc 06:35 AM
-- Phiên bản máy phục vụ: 10.4.20-MariaDB
-- Phiên bản PHP: 8.0.9

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Cơ sở dữ liệu: `testingonline`
--

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `candidate`
--

CREATE TABLE `candidate` (
  `id` int(11) NOT NULL,
  `id_test` int(11) DEFAULT NULL,
  `name` varchar(50) CHARACTER SET utf8 DEFAULT NULL,
  `level` int(11) DEFAULT NULL,
  `phone` varchar(45) COLLATE utf8_unicode_ci DEFAULT NULL,
  `email` varchar(45) COLLATE utf8_unicode_ci DEFAULT NULL,
  `position` varchar(45) CHARACTER SET utf8 DEFAULT NULL,
  `test_time` datetime DEFAULT NULL,
  `english_mark` float DEFAULT NULL,
  `coding_mark` float DEFAULT NULL,
  `knowledge_mark` float DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Đang đổ dữ liệu cho bảng `candidate`
--

INSERT INTO `candidate` (`id`, `id_test`, `name`, `level`, `phone`, `email`, `position`, `test_time`, `english_mark`, `coding_mark`, `knowledge_mark`) VALUES
(1, NULL, 'Le Thi Z', 1, '651651', 'B@gmail.com', NULL, '2022-01-04 00:00:00', 0, 0, 0),
(2, NULL, 'Tran Van C', 2, '65165', 'C@gmail.com', NULL, NULL, 0, 0, 0),
(3, NULL, 'Le Van A', 2, NULL, NULL, NULL, NULL, 0, 0, 0),
(5, NULL, 'Le Van B', 2, NULL, 'B1@gmail.com', NULL, NULL, 0, 0, 0),
(6, NULL, 'Le Van B', 2, '651653', 'B2@gmail.com', NULL, NULL, 0, 0, 0);

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `e_question`
--

CREATE TABLE `e_question` (
  `id` int(11) NOT NULL,
  `answer` varchar(255) CHARACTER SET utf8 DEFAULT NULL,
  `image` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `id_question` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `levels`
--

CREATE TABLE `levels` (
  `id` int(11) NOT NULL,
  `name` varchar(10) COLLATE utf8_unicode_ci DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Đang đổ dữ liệu cho bảng `levels`
--

INSERT INTO `levels` (`id`, `name`) VALUES
(1, 'Fresher De'),
(2, 'Junior Dev'),
(3, 'Senior Dev');

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `mc_question`
--

CREATE TABLE `mc_question` (
  `id` int(11) NOT NULL,
  `answer` varchar(255) CHARACTER SET utf8 DEFAULT NULL,
  `istrue` int(11) DEFAULT NULL,
  `img` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `id_question` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `question`
--

CREATE TABLE `question` (
  `id` int(11) NOT NULL,
  `type` int(11) DEFAULT NULL,
  `subject` int(11) DEFAULT NULL,
  `content` varchar(255) CHARACTER SET utf8 DEFAULT NULL,
  `level` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `staff`
--

CREATE TABLE `staff` (
  `id` int(11) NOT NULL,
  `name` varchar(255) CHARACTER SET utf8 DEFAULT NULL,
  `username` varchar(45) COLLATE utf8_unicode_ci NOT NULL,
  `password` varchar(45) COLLATE utf8_unicode_ci NOT NULL,
  `email` varchar(45) COLLATE utf8_unicode_ci DEFAULT NULL,
  `department` varchar(50) CHARACTER SET utf8 DEFAULT NULL,
  `image` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Đang đổ dữ liệu cho bảng `staff`
--

INSERT INTO `staff` (`id`, `name`, `username`, `password`, `email`, `department`, `image`) VALUES
(1, 'Nguyen Van A', 'nguyena', '123', NULL, NULL, NULL),
(2, 'thong', 'thongtpvinh3', '123', NULL, NULL, NULL),
(3, 'Hai', 'haitpvinh3', '123', NULL, NULL, NULL);

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `subjects`
--

CREATE TABLE `subjects` (
  `id` int(11) NOT NULL,
  `name` varchar(45) CHARACTER SET utf8 DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Đang đổ dữ liệu cho bảng `subjects`
--

INSERT INTO `subjects` (`id`, `name`) VALUES
(1, 'English Test'),
(2, 'Coding Test'),
(3, 'Knowledge Test');

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `test`
--

CREATE TABLE `test` (
  `id` int(11) NOT NULL,
  `code_test` varchar(10) COLLATE utf8_unicode_ci DEFAULT NULL,
  `subject` int(11) DEFAULT NULL,
  `level` int(11) DEFAULT NULL,
  `time` time DEFAULT NULL,
  `id_question` int(11) DEFAULT NULL,
  `id_candidate` int(11) DEFAULT NULL,
  `name` varchar(45) CHARACTER SET utf8 DEFAULT NULL,
  `marks` float DEFAULT NULL,
  `type` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `is_done` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Đang đổ dữ liệu cho bảng `test`
--

INSERT INTO `test` (`id`, `code_test`, `subject`, `level`, `time`, `id_question`, `id_candidate`, `name`, `marks`, `type`, `is_done`) VALUES
(1, 'ALO123', 1, 1, NULL, NULL, 1, NULL, NULL, NULL, 1),
(2, 'ALO124', 2, 1, NULL, NULL, 1, NULL, NULL, NULL, 0),
(3, 'ALO125', 3, 1, NULL, NULL, 1, NULL, NULL, NULL, 0),
(4, 'OLA123', 1, 3, NULL, NULL, 2, NULL, NULL, NULL, 0);

--
-- Chỉ mục cho các bảng đã đổ
--

--
-- Chỉ mục cho bảng `candidate`
--
ALTER TABLE `candidate`
  ADD PRIMARY KEY (`id`),
  ADD KEY `fk_candidate_level` (`level`),
  ADD KEY `fk_candidate_test` (`id_test`);

--
-- Chỉ mục cho bảng `e_question`
--
ALTER TABLE `e_question`
  ADD PRIMARY KEY (`id`),
  ADD KEY `fk_equestion_answer` (`id_question`);

--
-- Chỉ mục cho bảng `levels`
--
ALTER TABLE `levels`
  ADD PRIMARY KEY (`id`);

--
-- Chỉ mục cho bảng `mc_question`
--
ALTER TABLE `mc_question`
  ADD PRIMARY KEY (`id`),
  ADD KEY `fk_mcquestion_answer` (`id_question`);

--
-- Chỉ mục cho bảng `question`
--
ALTER TABLE `question`
  ADD PRIMARY KEY (`id`),
  ADD KEY `fk_question_level` (`level`),
  ADD KEY `fk_question_subject` (`subject`);

--
-- Chỉ mục cho bảng `staff`
--
ALTER TABLE `staff`
  ADD PRIMARY KEY (`id`);

--
-- Chỉ mục cho bảng `subjects`
--
ALTER TABLE `subjects`
  ADD PRIMARY KEY (`id`);

--
-- Chỉ mục cho bảng `test`
--
ALTER TABLE `test`
  ADD PRIMARY KEY (`id`),
  ADD KEY `fk_test_question` (`id_question`),
  ADD KEY `fk_test_subject` (`subject`);

--
-- AUTO_INCREMENT cho các bảng đã đổ
--

--
-- AUTO_INCREMENT cho bảng `candidate`
--
ALTER TABLE `candidate`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;

--
-- AUTO_INCREMENT cho bảng `e_question`
--
ALTER TABLE `e_question`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT cho bảng `levels`
--
ALTER TABLE `levels`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT cho bảng `mc_question`
--
ALTER TABLE `mc_question`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT cho bảng `question`
--
ALTER TABLE `question`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT cho bảng `staff`
--
ALTER TABLE `staff`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT cho bảng `subjects`
--
ALTER TABLE `subjects`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT cho bảng `test`
--
ALTER TABLE `test`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- Các ràng buộc cho các bảng đã đổ
--

--
-- Các ràng buộc cho bảng `candidate`
--
ALTER TABLE `candidate`
  ADD CONSTRAINT `fk_candidate_level` FOREIGN KEY (`level`) REFERENCES `levels` (`id`),
  ADD CONSTRAINT `fk_candidate_test` FOREIGN KEY (`id_test`) REFERENCES `test` (`id`);

--
-- Các ràng buộc cho bảng `e_question`
--
ALTER TABLE `e_question`
  ADD CONSTRAINT `fk_equestion_answer` FOREIGN KEY (`id_question`) REFERENCES `question` (`id`);

--
-- Các ràng buộc cho bảng `mc_question`
--
ALTER TABLE `mc_question`
  ADD CONSTRAINT `fk_mcquestion_answer` FOREIGN KEY (`id_question`) REFERENCES `question` (`id`);

--
-- Các ràng buộc cho bảng `question`
--
ALTER TABLE `question`
  ADD CONSTRAINT `fk_question_level` FOREIGN KEY (`level`) REFERENCES `levels` (`id`),
  ADD CONSTRAINT `fk_question_subject` FOREIGN KEY (`subject`) REFERENCES `subjects` (`id`);

--
-- Các ràng buộc cho bảng `test`
--
ALTER TABLE `test`
  ADD CONSTRAINT `fk_test_question` FOREIGN KEY (`id_question`) REFERENCES `question` (`id`),
  ADD CONSTRAINT `fk_test_subject` FOREIGN KEY (`subject`) REFERENCES `subjects` (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
