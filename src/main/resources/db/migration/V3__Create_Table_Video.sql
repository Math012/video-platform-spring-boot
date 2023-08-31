CREATE TABLE IF NOT EXISTS `videos` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `title` varchar(255) DEFAULT NULL,
  `date_post` datetime(6) DEFAULT NULL,
  `url_video` varchar(255) NOT NULL,
  `id_user` bigint(20) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB;