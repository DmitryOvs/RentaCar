create schema if not exists user_manager;

create table if not exists user_manager.t_user
(
    `id`         int NOT NULL AUTO_INCREMENT,
    `c_username` varchar(50) not null unique,
    `c_password` varchar(32),
    PRIMARY KEY (`id`),
    CONSTRAINT `t_user_1` CHECK (length(trim(`c_username`)) > 3)
);

create table if not exists user_manager.t_authority
(
    `id`         int NOT NULL AUTO_INCREMENT,
    `c_authority` varchar(50) not null unique,
    PRIMARY KEY (`id`),
    CONSTRAINT `t_authority_1` CHECK (length(trim(`c_authority`)) > 3)
);

create table if not exists user_manager.t_user_authority
(
    `id`         int NOT NULL AUTO_INCREMENT,
    `id_user` int not null references user_manager.t_user(`id`),
    `id_authority` int not null references user_manager.t_authority(`id`),
    PRIMARY KEY (`id`),
    CONSTRAINT uk_user_authority unique (`id_user`, `id_authority`)

);

SHOW GRANTS FOR 'manager'@'172.17.0.1'
