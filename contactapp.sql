/*
 Navicat Premium Data Transfer

 Source Server         : LDT
 Source Server Type    : MariaDB
 Source Server Version : 100417
 Source Host           : localhost:3306
 Source Schema         : contactapp

 Target Server Type    : MariaDB
 Target Server Version : 100417
 File Encoding         : 65001

 Date: 25/05/2021 18:33:44
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
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of admin
-- ----------------------------

-- ----------------------------
-- Table structure for announcement
-- ----------------------------
DROP TABLE IF EXISTS `announcement`;
CREATE TABLE `announcement`  (
  `announID` int(11) NOT NULL AUTO_INCREMENT,
  `title` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `announContent` varchar(5000) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `senderName` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `dateSend` date NULL DEFAULT NULL,
  `receiverName` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`announID`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of announcement
-- ----------------------------
INSERT INTO `announcement` VALUES (1, 'tb 1', 'đây là thông báo 1', 'người gửi 1', '2021-05-25', 'người nhận 2');
INSERT INTO `announcement` VALUES (2, 'tb2', 'đây thông báo 2 nè', 'người gửi 2', '2021-05-11', 'người nhận 1');
INSERT INTO `announcement` VALUES (3, 'tb3', 'thông báo 3 là dây', 'người 1', '2021-05-25', 'người nhận 1');

-- ----------------------------
-- Table structure for fee
-- ----------------------------
DROP TABLE IF EXISTS `fee`;
CREATE TABLE `fee`  (
  `feeID` int(11) NOT NULL AUTO_INCREMENT,
  `year` int(11) NULL DEFAULT NULL,
  `money` int(11) NULL DEFAULT NULL,
  `dateUpload` datetime NULL DEFAULT NULL,
  PRIMARY KEY (`feeID`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 7 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of fee
-- ----------------------------
INSERT INTO `fee` VALUES (1, 2019, 800, '2020-01-16 00:00:00');
INSERT INTO `fee` VALUES (2, 2020, 900, '2021-04-09 00:00:00');
INSERT INTO `fee` VALUES (3, 2021, 700, '2022-07-14 00:00:00');
INSERT INTO `fee` VALUES (4, 2021, 8000, '2021-05-25 00:00:00');
INSERT INTO `fee` VALUES (5, 2021, 900, '2021-05-25 00:00:00');
INSERT INTO `fee` VALUES (6, 2021, 1000, '2021-05-25 17:39:12');

-- ----------------------------
-- Table structure for parent
-- ----------------------------
DROP TABLE IF EXISTS `parent`;
CREATE TABLE `parent`  (
  `parentID` int(11) NOT NULL,
  `password` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `parentName` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `dob` date NULL DEFAULT NULL,
  `phonenumber` varchar(11) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `address` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`parentID`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of parent
-- ----------------------------
INSERT INTO `parent` VALUES (1, '1111', 'Nguyễn Văn A', '2021-04-06', '0213216546', '215121');

-- ----------------------------
-- Table structure for parent_announcement
-- ----------------------------
DROP TABLE IF EXISTS `parent_announcement`;
CREATE TABLE `parent_announcement`  (
  `parentID` int(11) NOT NULL,
  `announID` int(11) NOT NULL,
  `isRead` bit(1) NULL DEFAULT NULL,
  PRIMARY KEY (`parentID`, `announID`) USING BTREE,
  INDEX `FK_parentannoun_announcement`(`announID`) USING BTREE,
  CONSTRAINT `FK_parentannoun_announcement` FOREIGN KEY (`announID`) REFERENCES `announcement` (`announID`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `FK_parentannoun_parent` FOREIGN KEY (`parentID`) REFERENCES `parent` (`parentID`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of parent_announcement
-- ----------------------------
INSERT INTO `parent_announcement` VALUES (1, 1, b'0');
INSERT INTO `parent_announcement` VALUES (1, 2, b'0');
INSERT INTO `parent_announcement` VALUES (1, 3, b'0');

-- ----------------------------
-- Table structure for student
-- ----------------------------
DROP TABLE IF EXISTS `student`;
CREATE TABLE `student`  (
  `studentID` int(11) NOT NULL,
  `password` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `studentName` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `dob` date NULL DEFAULT NULL,
  `phonenumber` varchar(11) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `address` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `classname` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `parentID` int(11) NULL DEFAULT NULL,
  PRIMARY KEY (`studentID`) USING BTREE,
  INDEX `FK_student_parent`(`parentID`) USING BTREE,
  CONSTRAINT `FK_student_parent` FOREIGN KEY (`parentID`) REFERENCES `parent` (`parentID`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of student
-- ----------------------------
INSERT INTO `student` VALUES (1, '00000', 'con ông NV A', '2021-02-10', '2sadsdasd', 'sadzxcasdsad', 'CLC2A', 1);
INSERT INTO `student` VALUES (2, '213', 'con ong NV A nhưng bé hơn', '2020-10-28', 'sdasdsad', 'xcx', 'CLC2B', 1);
INSERT INTO `student` VALUES (111, 'test student', 'paswordd', '2020-02-02', '0123456788', 'addressss', 'sdadasdas', 1);

-- ----------------------------
-- Table structure for student_announcement
-- ----------------------------
DROP TABLE IF EXISTS `student_announcement`;
CREATE TABLE `student_announcement`  (
  `studentID` int(11) NOT NULL,
  `announID` int(11) NOT NULL,
  `isRead` bit(1) NULL DEFAULT NULL,
  PRIMARY KEY (`studentID`, `announID`) USING BTREE,
  INDEX `FK_studentannoun_announcement`(`announID`) USING BTREE,
  CONSTRAINT `FK_studentannoun_announcement` FOREIGN KEY (`announID`) REFERENCES `announcement` (`announID`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `FK_studentannoun_student` FOREIGN KEY (`studentID`) REFERENCES `student` (`studentID`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of student_announcement
-- ----------------------------
INSERT INTO `student_announcement` VALUES (1, 1, b'0');
INSERT INTO `student_announcement` VALUES (1, 2, b'0');
INSERT INTO `student_announcement` VALUES (2, 1, b'0');
INSERT INTO `student_announcement` VALUES (2, 3, b'0');

-- ----------------------------
-- Table structure for study
-- ----------------------------
DROP TABLE IF EXISTS `study`;
CREATE TABLE `study`  (
  `studentID` int(11) NOT NULL,
  `subjectID` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `isPaid` bit(1) NULL DEFAULT NULL,
  `scoreMidTerm` float NULL DEFAULT NULL,
  `scoreFinalTerm` float NULL DEFAULT NULL,
  PRIMARY KEY (`studentID`, `subjectID`) USING BTREE,
  INDEX `FK_study_subjectclass`(`subjectID`) USING BTREE,
  CONSTRAINT `FK_study_student` FOREIGN KEY (`studentID`) REFERENCES `student` (`studentID`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `FK_study_subjectclass` FOREIGN KEY (`subjectID`) REFERENCES `subjectclass` (`subjectID`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of study
-- ----------------------------
INSERT INTO `study` VALUES (1, 'DBMS_01CLC', b'0', 7, 8);
INSERT INTO `study` VALUES (1, 'MP_01CLC', b'1', 8, 8);
INSERT INTO `study` VALUES (2, 'MP_01CLC', b'1', 9, 7);

-- ----------------------------
-- Table structure for subjectclass
-- ----------------------------
DROP TABLE IF EXISTS `subjectclass`;
CREATE TABLE `subjectclass`  (
  `subjectID` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `subjectName` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `room` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `credit` int(11) NULL DEFAULT NULL,
  `teacherID` int(11) NULL DEFAULT NULL,
  PRIMARY KEY (`subjectID`) USING BTREE,
  INDEX `FK_subjectclass_teacher`(`teacherID`) USING BTREE,
  CONSTRAINT `FK_subjectclass_teacher` FOREIGN KEY (`teacherID`) REFERENCES `teacher` (`teacherID`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of subjectclass
-- ----------------------------
INSERT INTO `subjectclass` VALUES ('', NULL, NULL, NULL, NULL);
INSERT INTO `subjectclass` VALUES ('DBMS_01CLC', 'DBMS', 'A202', 3, 1);
INSERT INTO `subjectclass` VALUES ('MP_01CLC', 'Mobile Programing', 'A303', 3, 1);

-- ----------------------------
-- Table structure for teacher
-- ----------------------------
DROP TABLE IF EXISTS `teacher`;
CREATE TABLE `teacher`  (
  `teacherID` int(11) NOT NULL,
  `teacherName` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `phonenumber` varchar(11) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `dob` date NULL DEFAULT NULL,
  `address` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`teacherID`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of teacher
-- ----------------------------
INSERT INTO `teacher` VALUES (1, 'Thầy B', '123456789', '2021-03-24', 'sdsad');

-- ----------------------------
-- Table structure for timetable
-- ----------------------------
DROP TABLE IF EXISTS `timetable`;
CREATE TABLE `timetable`  (
  `timetableID` int(11) NOT NULL,
  `subjectID` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `timeStart` datetime NULL DEFAULT NULL,
  `timeEnd` datetime NULL DEFAULT NULL,
  `date` date NULL DEFAULT NULL,
  `isOff` bit(1) NULL DEFAULT NULL,
  PRIMARY KEY (`timetableID`) USING BTREE,
  INDEX `FK_timetable_subjectclass`(`subjectID`) USING BTREE,
  CONSTRAINT `FK_timetable_subjectclass` FOREIGN KEY (`subjectID`) REFERENCES `subjectclass` (`subjectID`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of timetable
-- ----------------------------

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
