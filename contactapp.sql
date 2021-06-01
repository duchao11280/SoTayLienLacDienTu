/*
 Navicat Premium Data Transfer

 Source Server         : localhost_3306
 Source Server Type    : MySQL
 Source Server Version : 100417
 Source Host           : localhost:3306
 Source Schema         : contactapp

 Target Server Type    : MySQL
 Target Server Version : 100417
 File Encoding         : 65001

 Date: 01/06/2021 11:30:11
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for admin
-- ----------------------------
DROP TABLE IF EXISTS `admin`;
CREATE TABLE `admin`  (
  `adminID` varchar(11) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `password` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `adminName` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`adminID`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of admin
-- ----------------------------
INSERT INTO `admin` VALUES ('01', 'e1adc3949ba59abbe56e057f2f883e', 'System Admin 01');
INSERT INTO `admin` VALUES ('02', 'e1adc3949ba59abbe56e057f2f883e', 'System Admin 02');

-- ----------------------------
-- Table structure for announcement
-- ----------------------------
DROP TABLE IF EXISTS `announcement`;
CREATE TABLE `announcement`  (
  `announID` int NOT NULL AUTO_INCREMENT,
  `title` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `announContent` varchar(5000) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `senderName` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `dateSend` date NULL DEFAULT NULL,
  `receiverName` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`announID`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 7 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of announcement
-- ----------------------------
INSERT INTO `announcement` VALUES (1, 'Thong bao nhap hoc', 'Tat ca cac sinh vien nhap hoc vao ngay 30/02/2021.\nCac em tap trung tai san truong luc 07h30.', 'System Admin 01', '2021-06-01', 'All');
INSERT INTO `announcement` VALUES (2, 'Thong bao nghi hoc', 'Lop nghi mot buoi, thay ban di choi nha may dua.\nO nha on bai dzui dze, ve thay cho kiem tra 15p lay hen.\nIu thung may dua nha.\nChut! Chut!', 'System Admin 01', '2021-06-01', 'ECOM430984_01CLC');
INSERT INTO `announcement` VALUES (3, 'Thong bao kiem tra', 'Hello may dua, lai la thay Ec dzui dze cua may dua day, \nNhu da hua, chieu 05/06/2021, thay da di choi dia roi ne may dua.Chieu nay kiem tra 15p lay mot cot diem.\nNoi dung la tat ca nhung gi da hoc tu dau nam den gio.\nThuong may dua nha.!', 'System Admin 01', '2021-06-01', 'ECOM430984_01CLC');
INSERT INTO `announcement` VALUES (4, 'Nhac dong hoc phi', 'Dong hoc phi hoc ki 2 nam hoc 2020-2021.\nHan cuoi: 05/06/2021.', 'System Admin 01', '2021-06-01', 'All');
INSERT INTO `announcement` VALUES (5, 'Xac nhan diem ren luyen', 'Sinh vien xac nhan diem ren luyen. Sinh vien khong xac nhan diem ren luyen se bi tru diem ren luyen. Lieu hon nha!\nHan cuoi: 05/06/2021.', 'System Admin 01', '2021-06-01', 'All');
INSERT INTO `announcement` VALUES (6, 'Thong bao bao cao do an', 'Bao cao do an vao 2 ngay 03/06/2021  va 05/06/2021', 'System Admin 01', '2021-06-01', 'MOPR331279_03CLC');

-- ----------------------------
-- Table structure for fee
-- ----------------------------
DROP TABLE IF EXISTS `fee`;
CREATE TABLE `fee`  (
  `feeID` int NOT NULL AUTO_INCREMENT,
  `year` int NULL DEFAULT NULL,
  `money` int NULL DEFAULT NULL,
  `dateUpload` datetime(0) NULL DEFAULT NULL,
  PRIMARY KEY (`feeID`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 7 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of fee
-- ----------------------------
INSERT INTO `fee` VALUES (1, 2019, 800, '2020-01-16 00:00:00');
INSERT INTO `fee` VALUES (2, 2020, 900, '2021-04-09 00:00:00');
INSERT INTO `fee` VALUES (3, 2021, 700, '2022-07-14 00:00:00');
INSERT INTO `fee` VALUES (4, 2021, 80, '2021-05-25 00:00:00');
INSERT INTO `fee` VALUES (5, 2021, 900, '2021-05-25 00:00:00');
INSERT INTO `fee` VALUES (6, 2021, 1000, '2021-05-25 17:39:12');

-- ----------------------------
-- Table structure for parent
-- ----------------------------
DROP TABLE IF EXISTS `parent`;
CREATE TABLE `parent`  (
  `parentID` int NOT NULL,
  `password` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `parentName` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `dob` date NULL DEFAULT NULL,
  `phonenumber` varchar(11) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `address` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`parentID`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of parent
-- ----------------------------
INSERT INTO `parent` VALUES (18110101, 'e1adc3949ba59abbe56e057f2f883e', 'Nguyễn Văn An', '1978-02-01', '0587123123', '05,Đường Trần Quang Diệu, Quận 3, Thành phố Hồ Chí Minh');
INSERT INTO `parent` VALUES (18110102, 'e1adc3949ba59abbe56e057f2f883e', 'Nguyễn Thị Bình', '1980-01-01', '0912323442', '15, Đường Trần Quang Diệu, Quận 3, Thành phố Hồ Chí Minh');
INSERT INTO `parent` VALUES (18110103, 'e1adc3949ba59abbe56e057f2f883e', 'Trịnh Kim Chi', '1985-05-02', '0923654544', 'hẻm 451 Hai Bà Trưng, Quận 3, Thành phố Hồ Chí Minh');
INSERT INTO `parent` VALUES (18110104, 'e1adc3949ba59abbe56e057f2f883e', 'Võ Nguyễn Ánh Dương', '1978-05-09', '0587125456', '19, Đường Nguyễn Trãi, Phường Nguyễn Cư Trinh, Quận 1, Thành phố Hồ Chí Minh');
INSERT INTO `parent` VALUES (18110105, 'e1adc3949ba59abbe56e057f2f883e', 'Trần Minh Hoàng', '1969-12-31', '0763654126', '72, Đường Cách Mạng Tháng Tám, Phường Bến Thành, Quận 1, Thành phố Hồ Chí Minh');

-- ----------------------------
-- Table structure for parent_announcement
-- ----------------------------
DROP TABLE IF EXISTS `parent_announcement`;
CREATE TABLE `parent_announcement`  (
  `parentID` int NOT NULL,
  `announID` int NOT NULL,
  `isRead` bit(1) NULL DEFAULT NULL,
  PRIMARY KEY (`parentID`, `announID`) USING BTREE,
  INDEX `FK_parentannoun_announcement`(`announID`) USING BTREE,
  CONSTRAINT `FK_parentannoun_announcement` FOREIGN KEY (`announID`) REFERENCES `announcement` (`announID`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `FK_parentannoun_parent` FOREIGN KEY (`parentID`) REFERENCES `parent` (`parentID`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of parent_announcement
-- ----------------------------
INSERT INTO `parent_announcement` VALUES (18110101, 1, b'0');
INSERT INTO `parent_announcement` VALUES (18110101, 2, b'0');
INSERT INTO `parent_announcement` VALUES (18110101, 3, b'0');
INSERT INTO `parent_announcement` VALUES (18110101, 4, b'0');
INSERT INTO `parent_announcement` VALUES (18110101, 5, b'0');
INSERT INTO `parent_announcement` VALUES (18110101, 6, b'0');
INSERT INTO `parent_announcement` VALUES (18110102, 1, b'0');
INSERT INTO `parent_announcement` VALUES (18110102, 2, b'0');
INSERT INTO `parent_announcement` VALUES (18110102, 3, b'0');
INSERT INTO `parent_announcement` VALUES (18110102, 4, b'0');
INSERT INTO `parent_announcement` VALUES (18110102, 5, b'0');
INSERT INTO `parent_announcement` VALUES (18110102, 6, b'0');
INSERT INTO `parent_announcement` VALUES (18110103, 1, b'0');
INSERT INTO `parent_announcement` VALUES (18110103, 2, b'0');
INSERT INTO `parent_announcement` VALUES (18110103, 3, b'0');
INSERT INTO `parent_announcement` VALUES (18110103, 4, b'0');
INSERT INTO `parent_announcement` VALUES (18110103, 5, b'0');
INSERT INTO `parent_announcement` VALUES (18110103, 6, b'0');
INSERT INTO `parent_announcement` VALUES (18110104, 1, b'0');
INSERT INTO `parent_announcement` VALUES (18110104, 2, b'0');
INSERT INTO `parent_announcement` VALUES (18110104, 3, b'0');
INSERT INTO `parent_announcement` VALUES (18110104, 4, b'0');
INSERT INTO `parent_announcement` VALUES (18110104, 5, b'0');
INSERT INTO `parent_announcement` VALUES (18110104, 6, b'0');
INSERT INTO `parent_announcement` VALUES (18110105, 1, b'0');
INSERT INTO `parent_announcement` VALUES (18110105, 2, b'0');
INSERT INTO `parent_announcement` VALUES (18110105, 3, b'0');
INSERT INTO `parent_announcement` VALUES (18110105, 4, b'0');
INSERT INTO `parent_announcement` VALUES (18110105, 5, b'0');
INSERT INTO `parent_announcement` VALUES (18110105, 6, b'0');

-- ----------------------------
-- Table structure for student
-- ----------------------------
DROP TABLE IF EXISTS `student`;
CREATE TABLE `student`  (
  `studentID` int NOT NULL,
  `password` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `studentName` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `dob` date NULL DEFAULT NULL,
  `phonenumber` varchar(11) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `address` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `classname` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `parentID` int NULL DEFAULT NULL,
  PRIMARY KEY (`studentID`) USING BTREE,
  INDEX `FK_student_parent`(`parentID`) USING BTREE,
  CONSTRAINT `FK_student_parent` FOREIGN KEY (`parentID`) REFERENCES `parent` (`parentID`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of student
-- ----------------------------
INSERT INTO `student` VALUES (18110101, 'e1adc3949ba59abbe56e057f2f883e', 'Nguyễn Ngọc Minh Anh', '2000-02-06', '0976546123', 'kí túc xá khu B, đại học quốc gia TPHCM, Đông Hòa, Dĩ An, Bình Dương', '181101CL2A', 18110101);
INSERT INTO `student` VALUES (18110102, 'e1adc3949ba59abbe56e057f2f883e', 'Trần Đức Anh', '2000-01-05', '0578763652', 'kí túc xá D1, Số 01 Võ Văn Ngân, phường linh Chiểu, quận Thủ Đức', '181101CL2B', 18110102);
INSERT INTO `student` VALUES (18110103, 'e1adc3949ba59abbe56e057f2f883e', 'Võ Anh Minh', '2000-12-01', '0975355265', 'kí túc xá D2, Số 484 Lê Văn Việt, phường Tăng Nhơn Phú A, quận 9', '181101CL1C', 18110103);
INSERT INTO `student` VALUES (18110104, 'e1adc3949ba59abbe56e057f2f883e', 'Nguyễn Trần Thành Long', '2000-06-08', '0915353744', 'Đường Cách Mạng Tháng Tám, Phường Bến Thành, Quận 1, Thành phố Hồ Chí Minh', '181101CL2B', 18110104);
INSERT INTO `student` VALUES (18110105, 'e1adc3949ba59abbe56e057f2f883e', 'Trần Thông Thiên Giáo Chủ', '2000-02-05', '0932764512', NULL, '181101CL1A', 18110105);

-- ----------------------------
-- Table structure for student_announcement
-- ----------------------------
DROP TABLE IF EXISTS `student_announcement`;
CREATE TABLE `student_announcement`  (
  `studentID` int NOT NULL,
  `announID` int NOT NULL,
  `isRead` bit(1) NULL DEFAULT NULL,
  PRIMARY KEY (`studentID`, `announID`) USING BTREE,
  INDEX `FK_studentannoun_announcement`(`announID`) USING BTREE,
  CONSTRAINT `FK_studentannoun_announcement` FOREIGN KEY (`announID`) REFERENCES `announcement` (`announID`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `FK_studentannoun_student` FOREIGN KEY (`studentID`) REFERENCES `student` (`studentID`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of student_announcement
-- ----------------------------

-- ----------------------------
-- Table structure for study
-- ----------------------------
DROP TABLE IF EXISTS `study`;
CREATE TABLE `study`  (
  `studentID` int NOT NULL,
  `subjectID` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `isPaid` bit(1) NULL DEFAULT NULL,
  `scoreMidTerm` float NULL DEFAULT NULL,
  `scoreFinalTerm` float NULL DEFAULT NULL,
  PRIMARY KEY (`studentID`, `subjectID`) USING BTREE,
  INDEX `FK_study_subjectclass`(`subjectID`) USING BTREE,
  CONSTRAINT `FK_study_student` FOREIGN KEY (`studentID`) REFERENCES `student` (`studentID`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `FK_study_subjectclass` FOREIGN KEY (`subjectID`) REFERENCES `subjectclass` (`subjectID`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of study
-- ----------------------------
INSERT INTO `study` VALUES (18110101, 'DBMS330284_02CLC', b'0', 5, 8);
INSERT INTO `study` VALUES (18110101, 'ECOM430984_01CLC', b'1', 6, 6);
INSERT INTO `study` VALUES (18110101, 'MOPR331279_03CLC', b'1', 7, 8);
INSERT INTO `study` VALUES (18110102, 'ECOM430984_01CLC', b'0', 8, 8);
INSERT INTO `study` VALUES (18110102, 'MOPR331279_03CLC', b'0', 8, 7);
INSERT INTO `study` VALUES (18110102, 'OOSD330879_03CLC', b'1', 8, 9);
INSERT INTO `study` VALUES (18110102, 'WIPR230579E_01CLC', b'0', 9, 9);
INSERT INTO `study` VALUES (18110103, 'ECOM430984_01CLC', b'1', 9, 7);
INSERT INTO `study` VALUES (18110103, 'MOPR331279_03CLC', b'0', 9, 8);
INSERT INTO `study` VALUES (18110103, 'WIPR230579E_01CLC', b'1', 7, 8);
INSERT INTO `study` VALUES (18110104, 'ECOM430984_01CLC', b'1', 0, 9);
INSERT INTO `study` VALUES (18110104, 'MOPR331279_03CLC', b'1', 1, 7);
INSERT INTO `study` VALUES (18110104, 'WIPR230579E_01CLC', b'0', 6, 8);
INSERT INTO `study` VALUES (18110105, 'ECOM430984_01CLC', b'0', 7, 8);
INSERT INTO `study` VALUES (18110105, 'MOPR331279_03CLC', b'1', 9, 8);
INSERT INTO `study` VALUES (18110105, 'WIPR230579E_01CLC', b'0', 4, 7);

-- ----------------------------
-- Table structure for subjectclass
-- ----------------------------
DROP TABLE IF EXISTS `subjectclass`;
CREATE TABLE `subjectclass`  (
  `subjectID` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `subjectName` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `room` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `credit` int NULL DEFAULT NULL,
  `teacherID` int NULL DEFAULT NULL,
  PRIMARY KEY (`subjectID`) USING BTREE,
  INDEX `FK_subjectclass_teacher`(`teacherID`) USING BTREE,
  CONSTRAINT `FK_subjectclass_teacher` FOREIGN KEY (`teacherID`) REFERENCES `teacher` (`teacherID`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of subjectclass
-- ----------------------------
INSERT INTO `subjectclass` VALUES ('DBMS330284_02CLC', 'Hệ quản trị cơ sở dữ liệu', 'A5-402', 3, 3);
INSERT INTO `subjectclass` VALUES ('ECOM430984_01CLC', 'Thương mại điện tử', 'A4-402', 2, 2);
INSERT INTO `subjectclass` VALUES ('MOPR331279_03CLC', 'Lập trinh di động', 'A303', 3, 1);
INSERT INTO `subjectclass` VALUES ('OOSD330879_03CLC', 'Thiết kế phần mềm hướng đối tượng', 'A5-401', 3, 5);
INSERT INTO `subjectclass` VALUES ('WIPR230579E_01CLC', 'Lập trình Window', 'A3-401', 3, 4);

-- ----------------------------
-- Table structure for teacher
-- ----------------------------
DROP TABLE IF EXISTS `teacher`;
CREATE TABLE `teacher`  (
  `teacherID` int NOT NULL,
  `teacherName` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `phonenumber` varchar(11) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `dob` date NULL DEFAULT NULL,
  `address` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`teacherID`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of teacher
-- ----------------------------
INSERT INTO `teacher` VALUES (1, 'Nguyễn Trần Thi Anh', '093456789', '1975-04-30', '72, Đường Cách Mạng Tháng Tám, Phường Bến Thành, Quận 1, Thành phố Hồ Chí Minh');
INSERT INTO `teacher` VALUES (2, 'Trần Công Vinh', '098765777', '1972-07-12', '72, Đường Cách Mạng Tháng Tám, Phường Bến Thành, Quận 1, Thành phố Hồ Chí Minh');
INSERT INTO `teacher` VALUES (3, 'Trịnh Minh Ngọc', '098765432', '1980-09-04', '720 Điện Biên Phủ, p. 22 trung tâm quận Bình Thạnh.');
INSERT INTO `teacher` VALUES (4, 'Võ Văn Thịnh', '09847553', '1987-01-02', '720 Điện Biên Phủ, p. 22 trung tâm quận Bình Thạnh.');
INSERT INTO `teacher` VALUES (5, 'Trần Anh Tú', '05727652', '1978-09-12', '720 Điện Biên Phủ, p. 22 trung tâm quận Bình Thạnh.');

-- ----------------------------
-- Table structure for timetable
-- ----------------------------
DROP TABLE IF EXISTS `timetable`;
CREATE TABLE `timetable`  (
  `timetableID` int NOT NULL,
  `subjectID` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `timeStart` datetime(0) NULL DEFAULT NULL,
  `timeEnd` datetime(0) NULL DEFAULT NULL,
  `date` date NULL DEFAULT NULL,
  `isOff` bit(1) NULL DEFAULT NULL,
  PRIMARY KEY (`timetableID`) USING BTREE,
  INDEX `FK_timetable_subjectclass`(`subjectID`) USING BTREE,
  CONSTRAINT `FK_timetable_subjectclass` FOREIGN KEY (`subjectID`) REFERENCES `subjectclass` (`subjectID`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of timetable
-- ----------------------------
INSERT INTO `timetable` VALUES (1, 'WIPR230579E_01CLC', '2021-06-03 07:00:00', '2021-06-03 10:00:00', '2021-06-02', b'0');
INSERT INTO `timetable` VALUES (2, 'WIPR230579E_01CLC', '2021-06-10 07:00:00', '2021-06-10 10:00:00', '2021-06-10', b'0');
INSERT INTO `timetable` VALUES (3, 'WIPR230579E_01CLC', '2021-06-17 07:00:00', '2021-06-17 10:00:00', '2021-06-17', b'0');
INSERT INTO `timetable` VALUES (4, 'DBMS330284_02CLC', '2021-06-04 07:00:00', '2021-06-04 10:00:00', '2021-06-04', b'0');
INSERT INTO `timetable` VALUES (5, 'MOPR331279_03CLC', '2021-06-02 07:00:00', '2021-06-02 10:00:00', '2021-06-01', b'1');
INSERT INTO `timetable` VALUES (6, 'MOPR331279_03CLC', '2021-06-09 07:00:00', '2021-06-09 10:00:00', '2021-06-09', b'1');
INSERT INTO `timetable` VALUES (7, 'MOPR331279_03CLC', '2021-06-16 07:00:00', '2021-06-16 10:00:00', '2021-06-16', b'1');
INSERT INTO `timetable` VALUES (8, 'DBMS330284_02CLC', '2021-06-11 07:00:00', '2021-06-11 10:00:00', '2021-06-11', b'0');
INSERT INTO `timetable` VALUES (9, 'DBMS330284_02CLC', '2021-06-18 07:00:00', '2021-06-18 10:00:00', '2021-06-18', b'1');
INSERT INTO `timetable` VALUES (10, 'ECOM430984_01CLC', '2021-06-05 07:00:00', '2021-06-05 10:00:00', '2021-06-05', b'1');
INSERT INTO `timetable` VALUES (11, 'ECOM430984_01CLC', '2021-06-12 07:00:00', '2021-06-12 10:00:00', '2021-06-12', b'0');
INSERT INTO `timetable` VALUES (12, 'ECOM430984_01CLC', '2021-06-19 07:00:00', '2021-06-19 10:00:00', '2021-06-19', b'0');
INSERT INTO `timetable` VALUES (13, 'OOSD330879_03CLC', '2021-06-05 07:00:00', '2021-06-05 10:00:00', '2021-06-06', b'1');
INSERT INTO `timetable` VALUES (14, 'OOSD330879_03CLC', '2021-06-13 07:00:00', '2021-06-13 10:00:00', '2021-06-13', b'0');
INSERT INTO `timetable` VALUES (15, 'OOSD330879_03CLC', '2021-06-20 07:00:00', '2021-06-20 10:00:00', '2021-06-20', b'0');

-- ----------------------------
-- Procedure structure for sp_addNewFee
-- ----------------------------
DROP PROCEDURE IF EXISTS `sp_addNewFee`;
delimiter ;;
CREATE PROCEDURE `sp_addNewFee`(IN number int)
begin
	INSERT into fee (`year`,money,dateUpload) VALUES(YEAR(NOW()),number, Now());

end
;;
delimiter ;

-- ----------------------------
-- Procedure structure for sp_getAllAnnounByParentID
-- ----------------------------
DROP PROCEDURE IF EXISTS `sp_getAllAnnounByParentID`;
delimiter ;;
CREATE PROCEDURE `sp_getAllAnnounByParentID`(IN id int(11))
begin
	select announcement.announID, title, announContent, senderName, isRead, dateSend, receiverName
	from announcement,parent_announcement
	where announcement.announID = parent_announcement.announID and parent_announcement.parentID = id;
end
;;
delimiter ;

-- ----------------------------
-- Procedure structure for sp_getAllAnnounByStudentID
-- ----------------------------
DROP PROCEDURE IF EXISTS `sp_getAllAnnounByStudentID`;
delimiter ;;
CREATE PROCEDURE `sp_getAllAnnounByStudentID`(IN id int(11))
begin
	select announcement.announID, title, announContent, senderName, isRead, dateSend, receiverName
	from announcement,student_announcement
	where announcement.announID = student_announcement.announID and studentID = id;
end
;;
delimiter ;

-- ----------------------------
-- Procedure structure for sp_getAllStudentBySubjectID
-- ----------------------------
DROP PROCEDURE IF EXISTS `sp_getAllStudentBySubjectID`;
delimiter ;;
CREATE PROCEDURE `sp_getAllStudentBySubjectID`(IN sid VARCHAR(100))
begin
	select study.studentID, student.`password`, studentName, dob, phonenumber, address, classname,parentID
	FROM	student,subjectclass,study
	where student.studentID = study.studentID and study.subjectID = subjectclass.subjectID and study.subjectID = sid;
end
;;
delimiter ;

-- ----------------------------
-- Procedure structure for sp_getAllSubjectByParentOfStudentID
-- ----------------------------
DROP PROCEDURE IF EXISTS `sp_getAllSubjectByParentOfStudentID`;
delimiter ;;
CREATE PROCEDURE `sp_getAllSubjectByParentOfStudentID`(IN uid int)
begin
	select study.subjectID, subjectName, credit,room, isPaid, scoreMidTerm, scoreFinalTerm,teacher.teacherID, teacherName
	from subjectclass,study,student,teacher
	where subjectclass.subjectID = study.subjectID and study.studentID = student.studentID and student.parentID = uid and teacher.teacherID = subjectclass.teacherID;
end
;;
delimiter ;

-- ----------------------------
-- Procedure structure for sp_getAllSubjectByStudentID
-- ----------------------------
DROP PROCEDURE IF EXISTS `sp_getAllSubjectByStudentID`;
delimiter ;;
CREATE PROCEDURE `sp_getAllSubjectByStudentID`(IN uid int)
begin
	select study.subjectID, subjectName, credit,room, isPaid, scoreMidTerm, scoreFinalTerm,teacher.teacherID, teacherName
	from subjectclass,study,student,teacher
	where subjectclass.subjectID = study.subjectID and study.studentID = student.studentID and student.studentID = uid and teacher.teacherID = subjectclass.teacherID;
end
;;
delimiter ;

-- ----------------------------
-- Procedure structure for sp_getDetailAnnounByParentID
-- ----------------------------
DROP PROCEDURE IF EXISTS `sp_getDetailAnnounByParentID`;
delimiter ;;
CREATE PROCEDURE `sp_getDetailAnnounByParentID`(IN pid int(11), aid int(11))
begin
	select announcement.announID, title, announContent, senderName, isRead, dateSend, receiverName
	from announcement,parent_announcement
	where announcement.announID = parent_announcement.announID and parent_announcement.parentID = pid and parent_announcement.announID=aid;
end
;;
delimiter ;

-- ----------------------------
-- Procedure structure for sp_getDetailAnnounByStudentID
-- ----------------------------
DROP PROCEDURE IF EXISTS `sp_getDetailAnnounByStudentID`;
delimiter ;;
CREATE PROCEDURE `sp_getDetailAnnounByStudentID`(IN sid int(11), aid int(11))
begin
	select announcement.announID, title, announContent, senderName, isRead
	from announcement,student_announcement
	where announcement.announID = student_announcement.announID and studentID= sid and student_announcement.announID=aid;
end
;;
delimiter ;

-- ----------------------------
-- Procedure structure for sp_getDetailSubclass
-- ----------------------------
DROP PROCEDURE IF EXISTS `sp_getDetailSubclass`;
delimiter ;;
CREATE PROCEDURE `sp_getDetailSubclass`(IN uid int,sid VARCHAR(100))
begin
	select study.subjectID, subjectName, credit,room, isPaid, scoreMidTerm, scoreFinalTerm,teacher.teacherID, teacherName
	from subjectclass,study,student,teacher
	where subjectclass.subjectID = study.subjectID and study.studentID = student.studentID and student.studentID = uid and teacher.teacherID = subjectclass.teacherID and subjectclass.subjectID=sid;
end
;;
delimiter ;

-- ----------------------------
-- Procedure structure for sp_getScheduleByStudentAndDate
-- ----------------------------
DROP PROCEDURE IF EXISTS `sp_getScheduleByStudentAndDate`;
delimiter ;;
CREATE PROCEDURE `sp_getScheduleByStudentAndDate`(IN idd int(11), dt varchar(30))
begin
    Select timetable.timetableID,timetable.subjectID, timetable.date, DATE_FORMAT(timetable.timeStart, '%Hg%i') as tmStart, DATE_FORMAT(timetable.timeEnd, '%Hg%i')tmEnd, subjectclass.subjectName, subjectclass.room, subjectclass.credit,(SELECT  teacher.teacherName from teacher,subjectclass WHERE subjectclass.teacherID = teacher.teacherID lIMIT 1) as teachername,IF((hour(TIMEDIFF(time(timetable.timeStart),'07:00:00'))+1)<1,1,If((hour(TIMEDIFF(time(timetable.timeStart),'07:00:00'))+1)>12,12,(hour(TIMEDIFF(time(timetable.timeStart),'07:00:00'))+1))) as tietbd, IF((hour(TIMEDIFF(time(timetable.timeEnd),'07:00:00'))+1)<1,1,If((hour(TIMEDIFF(time(timetable.timeEnd),'07:00:00'))+1)>12,12,(hour(TIMEDIFF(time(timetable.timeEnd),'07:00:00'))+1))) as tietkt
from timetable,subjectclass,study
 WHERE timetable.subjectID = subjectclass.subjectID and study.subjectID = subjectclass.subjectID and study.studentID = idd and timetable.isOff=0 and timetable.date = dt;
end
;;
delimiter ;

-- ----------------------------
-- Procedure structure for sp_UpdateOff
-- ----------------------------
DROP PROCEDURE IF EXISTS `sp_UpdateOff`;
delimiter ;;
CREATE PROCEDURE `sp_UpdateOff`(IN id int(11))
begin
IF ((select isOff from timetable where timetableID = id)=true) THEN
	Update timetable set isOff=false where timetableID = id;
ELSE
	Update timetable set isOff=true where timetableID = id;
END IF;
end
;;
delimiter ;

-- ----------------------------
-- Procedure structure for sp_UpdatePay
-- ----------------------------
DROP PROCEDURE IF EXISTS `sp_UpdatePay`;
delimiter ;;
CREATE PROCEDURE `sp_UpdatePay`(IN uID int(11),sID VARCHAR(100))
begin
IF ((select isPaid from study where studentID = uID and subjectID = sID)=true) THEN
	Update study set isPaid=false where studentID = uID and subjectID = sID;
ELSE
	Update study set isPaid=true where studentID = uID and subjectID = sID;
END IF;
end
;;
delimiter ;

SET FOREIGN_KEY_CHECKS = 1;
