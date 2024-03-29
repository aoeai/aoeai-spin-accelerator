CREATE TABLE `satest_problem` (
  `id` int(6) NOT NULL AUTO_INCREMENT,
  `problem` varchar(500) NOT NULL COMMENT '问题',
  `problem_en` varchar(500) NOT NULL COMMENT '问题的英文版',
  `difficulty` tinyint(4) NOT NULL DEFAULT '1' COMMENT '难度 1：简单；2：中等；3困难',
  `tag` varchar(200) NOT NULL COMMENT '类型 （类型i的集合，例如 1,2)',
  `char_test` char(10) DEFAULT NULL,
  `varchar_test` varchar(20) DEFAULT NULL,
  `nvarchar_test` varchar(60) CHARACTER SET utf8 DEFAULT NULL,
  `text_test` text,
  `longtext_test` longtext,
  `tinyint_test` tinyint(1) DEFAULT NULL,
  `smallint_test` smallint(3) DEFAULT NULL,
  `int_test` int(11) DEFAULT NULL,
  `bigint_test` bigint(10) DEFAULT NULL,
  `float_test` float DEFAULT NULL,
  `decimal_test` decimal(10,2) DEFAULT NULL,
  `date_test` date DEFAULT NULL,
  `datetime_test` datetime DEFAULT NULL,
  `timestamp_test` timestamp(2) NULL DEFAULT NULL,
  `hump_name_test` varchar(255) DEFAULT NULL COMMENT '驼峰命名测试',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='LeetCode问题'