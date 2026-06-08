-- documento de insertar valores
use steakgamesDB;

INSERT INTO region (idregion,nombre) VALUES
('1','MEXICO'),('2','PERU'),('3','MIAM'),('4','LOSANGELES'),('5','OCEANIA'),
('6','JAPON'),('7','CHINA'),('8','ALASKA'),('9','POLO SUR'),('10','LONDRES'),
('11','NIGERIA'),('12','Chocó'),('13','Antioquia'),('14','Cundinamarca'),('15','Guainía'),
('16','Bolívar'),('17','Huila'),('18','La Guajira'),('19','Magdalena'),('20','Meta');

INSERT INTO plataforma(idplataforma, nombre) VALUES
(1,'NINTENDO'),(2,'PS4'),(3,'XBOX'),(4,'PC');

INSERT INTO biblioteca (idbiblioteca) VALUES
(1),(2),(3),(4),(5),
(6),(7),(8),(9),(10),
(11),(12),(13),(14),(15),
(16),(17),(18),(19),(20);

INSERT INTO generoVideojuego (idgeneroVideojuego, nombre) VALUES
('1','Accion'),('2','Aventura'),('3','RPG'),('4','Shooter'),('5','Deportes'),
('6','Carreras'),('7','Estrategia'),('8','Simulacion'),('9','Terror'),('10','Puzzle'),
('11','Plataformas'),('12','Sandbox'),('13','MMORPG'),('14','Battle Royale'),('15','Supervivencia'),
('16','Musical'),('17','Arcade'),('18','Educativo'),('19','Lucha'),('20','Indie');

INSERT INTO videojuego
(titulo, precio,direccionArchivo, descripcion, portadaPath, crossplay,  multijugador)
VALUES
('Cyber Legends', 59.99, '/games/cyber_legends.exe', 'Juego de accion futurista en mundo abierto.', '/portadas/cyber_legends.jpg', TRUE, 'Online'),
('Fantasy Quest', 39.99, '/games/fantasy_quest.exe', 'Aventura RPG con exploracion y magia.', '/portadas/fantasy_quest.jpg', FALSE, 'Cooperativo'),
('Speed Racers', 29.99, '/games/speed_racers.exe', 'Juego de carreras con vehiculos deportivos.', '/portadas/speed_racers.jpg', TRUE, 'Online'),
('Battle Arena', 49.99, '/games/battle_arena.exe', 'Combates multijugador competitivos.', '/portadas/battle_arena.jpg', TRUE, 'Online'),
('Zombie Survival', 24.99, '/games/zombie_survival.exe', 'Supervivencia en un mundo postapocaliptico.', '/portadas/zombie_survival.jpg', TRUE, 'Cooperativo'),
('Space Odyssey', 69.99, '/games/space_odyssey.exe', 'Exploracion espacial y misiones galacticas.', '/portadas/space_odyssey.jpg', FALSE, 'Singleplayer'),
('Medieval Kingdom', 44.99, '/games/medieval_kingdom.exe', 'Gestion y estrategia de reinos medievales.', '/portadas/medieval_kingdom.jpg', FALSE, 'Singleplayer'),
('Soccer Champions', 34.99, '/games/soccer_champions.exe', 'Simulador de futbol profesional.', '/portadas/soccer_champions.jpg',  TRUE, 'Online'),
('Mystery Island', 19.99, '/games/mystery_island.exe', 'Aventura de exploracion y acertijos.', '/portadas/mystery_island.jpg',  FALSE, 'Singleplayer'),
('Galaxy Wars', 54.99, '/games/galaxy_wars.exe', 'Batallas espaciales entre imperios.', '/portadas/galaxy_wars.jpg', TRUE, 'Online'),
('Dragon Realms', 45.50, '/games/dragon_realms.exe', 'RPG de fantasia con dragones.', '/portadas/dragon_realms.jpg',  TRUE, 'Cooperativo'),
('Urban Fighters', 27.99, '/games/urban_fighters.exe', 'Juego de lucha en escenarios urbanos.', '/portadas/urban_fighters.jpg', FALSE, 'Local'),
('Pirate Adventure', 32.75, '/games/pirate_adventure.exe', 'Explora mares y busca tesoros.', '/portadas/pirate_adventure.jpg', TRUE, 'Cooperativo'),
('Extreme Sports', 21.99, '/games/extreme_sports.exe', 'Compite en deportes extremos.', '/portadas/extreme_sports.jpg', FALSE, 'Singleplayer'),
('Alien Invasion', 47.99, '/games/alien_invasion.exe', 'Defiende la Tierra de invasores alienigenas.', '/portadas/alien_invasion.jpg', TRUE, 'Online'),
('Farm Life Simulator', 18.50, '/games/farm_life_simulator.exe', 'Gestiona tu propia granja.', '/portadas/farm_life_simulator.jpg', FALSE, 'Singleplayer'),
('Dungeon Escape', 25.99, '/games/dungeon_escape.exe', 'Escapa de peligrosas mazmorras.', '/portadas/dungeon_escape.jpg', FALSE, 'Singleplayer'),
('War Strategy', 52.99, '/games/war_strategy.exe', 'Juego de estrategia militar en tiempo real.', '/portadas/war_strategy.jpg', TRUE, 'Online'),
('Ocean Explorer', 28.49, '/games/ocean_explorer.exe', 'Explora las profundidades del oceano.', '/portadas/ocean_explorer.jpg', FALSE, 'Singleplayer'),
('Super Heroes United', 64.99, '/games/super_heroes_united.exe', 'Accion y aventuras con superheroes.', '/portadas/super_heroes_united.jpg', TRUE, 'Cooperativo');

INSERT INTO usuario (idusuario, nombre, correo, contrasenia, genero, anio, mes, dia, rol, imagePath, region_idregion, biblioteca_idbiblioteca) VALUES
('1','Juan Perez','juan1@mail.com','pass123A','HOMBRE','1998','Enero','01','ADMIN','/img/u1.png',1,1),
('2','Maria Lopez','maria2@mail.com','pass123B','MUJER','1999','Febrero','02','USER','/img/u2.png',1,2),
('3','Carlos Ruiz','carlos3@mail.com','pass123C','HOMBRE','2000','Marzo','03','USER','/img/u3.png',1,3),
('4','Ana Torres','ana4@mail.com','pass123D','MUJER','2001','Abril','04','USER','/img/u4.png',1,4),
('5','Luis Gomez','luis5@mail.com','pass123E','HOMBRE','1997','Mayo','05','ADMIN','/img/u5.png',1,5),
('6','Sofia Diaz','sofia6@mail.com','pass123F','MUJER','1996','Junio','06','USER','/img/u6.png',1,6),
('7','Pedro Castro','pedro7@mail.com','pass123G','HOMBRE','1995','Julio','07','USER','/img/u7.png',1,7),
('8','Elena Vega','elena8@mail.com','pass123H','MUJER','1994','Agosto','08','USER','/img/u8.png',1,8),
('9','Miguel Rios','miguel9@mail.com','pass123I','HOMBRE','1993','Septiembre','09','ADMIN','/img/u9.png',1,9),
('10','Lucia Mora','lucia10@mail.com','pass123J','MUJER','1992','Octubre','10','USER','/img/u10.png',1,10),
('11','Jorge Silva','jorge11@mail.com','pass123K','HOMBRE','1991','Noviembre','11','USER','/img/u11.png',1,11),
('12','Valeria Nunez','valeria12@mail.com','pass123L','MUJER','1990','Diciembre','12','USER','/img/u12.png',1,12),
('13','Ricardo Leon','ricardo13@mail.com','pass123M','HOMBRE','1989','Enero','13','ADMIN','/img/u13.png',1,13),
('14','Camila Reyes','camila14@mail.com','pass123N','MUJER','1988','Febrero','14','USER','/img/u14.png',1,14),
('15','Fernando Cruz','fernando15@mail.com','pass123O','HOMBRE','1987','Marzo','15','USER','/img/u15.png',1,15),
('16','Paula Herrera','paula16@mail.com','pass123P','MUJER','1986','Abril','16','USER','/img/u16.png',1,16),
('17','Diego Flores','diego17@mail.com','pass123Q','HOMBRE','1985','Mayo','17','ADMIN','/img/u17.png',1,17),
('18','Andrea Molina','andrea18@mail.com','pass123R','MUJER','1984','Junio','18','USER','/img/u18.png',1,18),
('19','Roberto Navarro','roberto19@mail.com','pass123S','HOMBRE','1983','Julio','19','USER','/img/u19.png',1,19),
('20','Gabriela Ortiz','gabriela20@mail.com','pass123T','MUJER','1982','Agosto','20','USER','/img/u20.png',1,20);
INSERT INTO videojuego_has_generoVideojuego (videojuego_idvideojuego, generoVideojuego_idgeneroVideojuego
) VALUES
(1,12),(2,5),(3,4),(4,9),(5,14),(6,13),(7,4),(8,1),(9,3),(10,3),(11,17),
(12,15),(13,5),(14,14),(15,3),(16,4),(17,19),(18,20),(19,8),(20,6);
INSERT INTO biblioteca_has_videojuego ( biblioteca_idbiblioteca,    videojuego_idvideojuego
) VALUES
(1,1),(2,2),(3,3),(4,4),(5,5),(6,6),(7,7),(8,8),(9,9),(10,10),(11,11),
(12,12),(13,13),(14,14),(15,15),(16,16),(17,17),(18,18),(19,19),(20,20);
INSERT INTO videojuego_has_plataforma (videojuego_idvideojuego, plataforma_idplataforma) VALUES
(1,1),(2,2),(3,3),(4,4),(5,1),(6,2),(7,3),(8,4),(9,1),(10,2),(11,3),
(12,4),(13,1),(14,2),(15,3),(16,4),(17,1),(18,2),(19,3),(20,4);