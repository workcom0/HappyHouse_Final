drop database happyhousedb;
create database happyhousedb;

use happyhousedb;

-- 멤버 테이블
CREATE TABLE `member` (
  `memberId` varchar(45) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `memberPw` varchar(45) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `name` varchar(45) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `email` varchar(45) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `tel` varchar(45) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `isAdmin` int default 0,
  PRIMARY KEY (`memberId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

insert into member values("admin", "admin", "관리자", "admin@ssafy.com", "010-1234-5678", 1);

select * from member; 

-- 시도 테이블
DROP TABLE IF EXISTS `happyhousedb`.`sido` ;
CREATE TABLE `sido` (
  `sidoCode` varchar(10) NOT NULL,
  `sidoName` varchar(30) DEFAULT NULL,
  PRIMARY KEY (`sidoCode`),
  UNIQUE KEY `sidoName` (`sidoName`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- 구군 테이블
DROP TABLE IF EXISTS `happyhousedb`.`gugun` ;
CREATE TABLE `gugun` (
  `gugunCode` varchar(10) NOT NULL,
  `gugunName` varchar(30) DEFAULT NULL,
  PRIMARY KEY (`gugunCode`),
  UNIQUE KEY `gugunName` (`gugunName`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- 동 테이블
DROP TABLE IF EXISTS `happyhousedb`.`dong` ;
CREATE TABLE `dong` (
  `dongCode` varchar(10) NOT NULL,
  `sidoName` varchar(30) DEFAULT NULL,
  `gugunName` varchar(30) DEFAULT NULL,
  `dongName` varchar(30) DEFAULT NULL,
  PRIMARY KEY (`dongCode`),
  CONSTRAINT `dong_ibfk_1` FOREIGN KEY (`sidoName`) REFERENCES `sido` (`sidoName`),
  CONSTRAINT `dong_ibfk_2` FOREIGN KEY (`gugunName`) REFERENCES `gugun` (`gugunName`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- 주소 테이블
CREATE TABLE `baseaddress` (
  `no` int NOT NULL AUTO_INCREMENT,
  `sidoName` varchar(30) DEFAULT NULL,
  `gugunName` varchar(30) DEFAULT NULL,
  `dongName` varchar(30) DEFAULT NULL,
  `dongCode` varchar(10) DEFAULT NULL,
  `lat` varchar(20) DEFAULT NULL,
  `lng` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`no`),
  CONSTRAINT `baseaddress_ibfk_1` FOREIGN KEY (`dongCode`) REFERENCES `dong` (`dongCode`)
) ENGINE=InnoDB AUTO_INCREMENT=3597 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- 아파트 정보 테이블
CREATE TABLE `houseinfo` (
  `aptCode` int NOT NULL,
  `aptName` varchar(50) DEFAULT NULL,
  `dongCode` varchar(10) DEFAULT NULL,
  `dongName` varchar(30) DEFAULT NULL,
  `buildYear` int DEFAULT NULL,
  `jibun` varchar(30) DEFAULT NULL,
  `lat` varchar(30) DEFAULT NULL,
  `lng` varchar(30) DEFAULT NULL,
  PRIMARY KEY (`aptCode`),
  CONSTRAINT `houseinfo_ibfk_1` FOREIGN KEY (`dongCode`) REFERENCES `dong` (`dongCode`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- 아파트 매매 정보 테이블
CREATE TABLE `housedeal` (
  `no` int NOT NULL AUTO_INCREMENT,
  `aptCode` int DEFAULT NULL,
  `dealAmount` varchar(20) DEFAULT NULL,
  `dealYear` int DEFAULT NULL,
  `dealMonth` int DEFAULT NULL,
  `dealDay` int DEFAULT NULL,
  `area` varchar(30) DEFAULT NULL,
  `floor` varchar(30) DEFAULT NULL,
  `type` int DEFAULT NULL,
  PRIMARY KEY (`no`),
  CONSTRAINT `housedeal_ibfk_1` FOREIGN KEY (`aptCode`) REFERENCES `houseinfo` (`aptCode`)
) ENGINE=InnoDB AUTO_INCREMENT=68865 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- 관심매물 테이블
CREATE TABLE `likedeal` (
  `no` int NOT NULL,
  `memberId` varchar(45) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  PRIMARY KEY (`no`, `memberId`),
  CONSTRAINT `fk_likearea_housedeal` FOREIGN KEY (`no`) REFERENCES `housedeal` (`no`),
  CONSTRAINT `fk_likearea_member` FOREIGN KEY (`memberId`) REFERENCES `member` (`memberId`)
) ENGINE=InnoDB AUTO_INCREMENT=27 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- 공지사항 테이블
CREATE TABLE `notice` (
  `noticeNo` int NOT NULL AUTO_INCREMENT,
  `memberId` varchar(16) DEFAULT NULL,
  `subject` varchar(100) DEFAULT NULL,
  `content` varchar(2000) DEFAULT NULL,
  `regTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`noticeNo`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- 주변 환경 정보 테이블
CREATE TABLE `environment` (
  `no` int NOT NULL AUTO_INCREMENT,
  `company` varchar(30) NOT NULL,
  `dong` varchar(30) DEFAULT NULL,
  `type` varchar(30) DEFAULT NULL,
  `address` varchar(30) DEFAULT NULL,
  PRIMARY KEY (`no`)
) ENGINE=InnoDB AUTO_INCREMENT=1824 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;