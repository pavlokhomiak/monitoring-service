CREATE DATABASE `monitor_db` /*!40100 DEFAULT CHARACTER SET utf8 */ /*!80016 DEFAULT ENCRYPTION='N' */;

CREATE TABLE `user` (
                        `id` int NOT NULL AUTO_INCREMENT,
                        `access_token` binary(255) NOT NULL,
                        `deleted` bit(1) NOT NULL,
                        `email` varchar(255) NOT NULL,
                        `name` varchar(255) NOT NULL,
                        PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb3;

CREATE TABLE `monitored_endpoint` (
                                      `id` int NOT NULL AUTO_INCREMENT,
                                      `date_of_creation` datetime(6) NOT NULL,
                                      `date_of_last_check` datetime(6) NOT NULL,
                                      `deleted` bit(1) NOT NULL,
                                      `monitored_interval` int NOT NULL,
                                      `name` varchar(255) NOT NULL,
                                      `url` varchar(255) NOT NULL,
                                      `owner_id` int DEFAULT NULL,
                                      PRIMARY KEY (`id`),
                                      KEY `FKogy5mjyp06bs1lseuaudpxrqd` (`owner_id`),
                                      CONSTRAINT `FKogy5mjyp06bs1lseuaudpxrqd` FOREIGN KEY (`owner_id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb3;

CREATE TABLE `monitoring_result` (
                                     `id` int NOT NULL AUTO_INCREMENT,
                                     `date_of_check` datetime(6) NOT NULL,
                                     `deleted` bit(1) NOT NULL,
                                     `returned_http_status_code` int NOT NULL,
                                     `returned_payload` varchar(255) DEFAULT NULL,
                                     `monitored_endpoint_id` int NOT NULL,
                                     PRIMARY KEY (`id`),
                                     KEY `FKf227t3sw3am5nm01mfw4pu1e7` (`monitored_endpoint_id`),
                                     CONSTRAINT `FKf227t3sw3am5nm01mfw4pu1e7` FOREIGN KEY (`monitored_endpoint_id`) REFERENCES `monitored_endpoint` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=322 DEFAULT CHARSET=utf8mb3;

INSERT INTO user(id, access_token, email, name, deleted)
VALUES(1, '', 'bob@email.com', 'bob', false);
