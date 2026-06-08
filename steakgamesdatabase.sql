CREATE DATABASE IF NOT exists steakgamesDB;
use steakgamesDB;
drop tables region, plataforma, videojuego_has_plataforma, videojuego_has_generoVideojuego, biblioteca_has_videojuego, biblioteca, usuario, generoVideojuego, videojuego;
-- creacion de tabla de regiones disponibles
create table if not exists region(
	idregion int unique auto_increment not null primary key,
	nombre varchar(45) not null
);
-- creacion de tabla de biblioteca, cada usuario tiene UNA biblioteca propia
create table if not exists biblioteca(
	idbiblioteca INT unique auto_increment not null primary key,
    nombre varchar(45)
);
create table if not exists plataforma(
	idplataforma INT unique auto_increment not null primary key,
    nombre varchar(20)
);
-- creacion de tabla de usuario, todas sus atributos necesarios
create table if not exists usuario(
	idusuario INT unique auto_increment not null primary key,
    nombre varchar(45) not null,
    correo varchar(45) not null,
    contrasenia varchar(100) unique not null,
    genero enum('HOMBRE','MUJER') not null,
    anio varchar(4) not null,
    mes varchar(12) not null,
    dia varchar(2) not null,
    rol varchar(15) not null,
    imagePath varchar(100),
    region_idregion int not null,
    biblioteca_idbiblioteca int,
    foreign key (region_idregion) references region(idregion),
    foreign key (biblioteca_idbiblioteca) references biblioteca(idbiblioteca)
);
create table if not exists generoVideojuego(
	idgeneroVideojuego int unique auto_increment not null primary key,
    nombre varchar(45) not null
);
 create table if not exists videojuego(
	idvideojuego int unique auto_increment not null primary key,
    titulo varchar(45) not null,
    precio float not null,
    direccionArchivo varchar(100) not null,
    descripcion varchar(150),
    portadaPath varchar(100),
    crossplay boolean,
    multijugador varchar(20)
 );

create table if not exists videojuego_has_generoVideojuego(
	videojuego_idvideojuego int,
    generoVideojuego_idgeneroVideojuego int,
    foreign key (videojuego_idvideojuego) references videojuego(idvideojuego),
    foreign key (generoVideojuego_idgeneroVideojuego) references generoVideojuego(idgeneroVideojuego)
);

create table if not exists biblioteca_has_videojuego(
	biblioteca_idbiblioteca int,
    videojuego_idvideojuego int,
    foreign key (biblioteca_idbiblioteca) references biblioteca(idbiblioteca),
    foreign key (videojuego_idvideojuego) references videojuego(idvideojuego)
);

create table if not exists videojuego_has_plataforma(
	videojuego_idvideojuego int,
    plataforma_idplataforma int,
    foreign key (videojuego_idvideojuego) references videojuego(idvideojuego),
    foreign key (plataforma_idplataforma) references plataforma(idplataforma)
);