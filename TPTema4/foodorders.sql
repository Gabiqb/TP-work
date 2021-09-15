drop database if exists foodorders;
create database foodorders;
use foodorders;


drop table if exists users;
create table if not exists users(
   ID int unique primary key auto_increment,
	 Nume varchar(50),
	 Prenume varchar(50),
	 nr_tel varchar(20),
	 email varchar(50),
	 parola varchar(100), 
	 adresa varchar(100),
	 functie varchar(50)
);
 
create table if not exists products(
   ID int unique primary key auto_increment,
	 denumire varchar(100),
	 rating decimal(5,2),
	 calorii int,
	 proteine int,
	 grasimi int,
	 carbohidrati int,
	 pret int
	
);

create table if not exists orders(
   ID bigint primary key auto_increment, 
	 Nume_client varchar(50),
	 Prenume_client varchar(50),
	 nr_tel varchar(20),
	 produse text,
	 cantitate text,
	 total int,
	 adresa varchar(100),
	 data_eliberare timestamp,
	 detalii TEXT

);

create table if not exists queue(
   ID bigint primary key auto_increment, 
	 Nume_client varchar(50),
	 Prenume_client varchar(50),
	 nr_tel varchar(20),
	 Nume_agent varchar(50),
	 Prenume_agent varchar(50),
	 produse text,
	 cantitate text,
	 total int,
	 adresa varchar(100),
	 data_eliberare timestamp DEFAULT CURRENT_TIMESTAMP,
	 index(data_eliberare),
	 detalii TEXT

);


 


