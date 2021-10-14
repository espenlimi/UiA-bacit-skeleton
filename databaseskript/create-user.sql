create database if not exists MytestDB;
use MytestDB;
create table if not EXISTS users
(
    Id              integer UNIQUE auto_increment,
    FullName        varchar(255),
    PhoneNumber     varchar(255),
    LoginName       varchar(255) UNIQUE not null,
    Password        varchar(255) not null,
    CONSTRAINT U_User_ID_PK PRIMARY KEY (Id)
);

create table User_roles (
  LoginName         varchar(255) not null,
  role_name         varchar(255) not null,
  primary key (LoginName, role_name)
);

create table Categories (
  Id              integer UNIQUE auto_increment,
  Name              varchar(255) not null,
  ImagePath         varchar(255) not null,
  CONSTRAINT PRIMARY KEY (Id)
);

#inserter en record av en bruker inn i databasen otra.
insert into users (FullName,
                       PhoneNumber,
                       LoginName,
                       Password)
values (
        'espen limi',
        '12345678',
        'esp1',
        '12345');

insert into users (FullName,
                       PhoneNumber,
                       LoginName,
                       Password)
values (
        'espen limi',
        '12345678',
        'esp_admin',
        '12345');

insert into user_roles (LoginName, role_name) values ('esp1','user');
insert into user_roles (LoginName, role_name) values ('esp_admin','administrator');
insert into user_roles (LoginName, role_name) values ('esp_admin','user');

insert into Categories (Name, ImagePath) values("NailGun", "somePath"), ("Div small tools","anotherPath");