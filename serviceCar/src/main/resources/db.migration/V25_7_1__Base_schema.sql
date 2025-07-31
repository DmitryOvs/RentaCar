create schema if not exists catalog;

CREATE TABLE IF NOT EXISTS catalog.`t_car_rent` (
                                                    `id` int NOT NULL AUTO_INCREMENT,
                                                    `c_brand` varchar(20) NOT NULL,
                                                    `c_model` varchar(50) NOT NULL,
                                                    `c_registration_number` varchar(9) NOT NULL,
                                                    `c_seats` int DEFAULT NULL,
                                                    `c_rent_cost` int DEFAULT NULL,
                                                    `c_type_car` varchar(12) DEFAULT NULL,
                                                    `c_gear` varchar(9) DEFAULT NULL,
                                                    `c_fuel` varchar(6) DEFAULT NULL,
                                                    PRIMARY KEY (`id`),
                                                    CONSTRAINT `t_car_rent_chk_1` CHECK (((length(`c_brand`) > 2) and (length(`c_brand`) < 20))),
                                                    CONSTRAINT `t_car_rent_chk_2` CHECK (((length(`c_model`) > 3) and (length(`c_model`) < 50))),
                                                    CONSTRAINT `t_car_rent_chk_3` CHECK (((`c_seats` > 2) and (`c_seats` < 9))),
                                                    CONSTRAINT `t_car_rent_chk_4` CHECK ((`c_rent_cost` > 50))
);



