-- Libro
INSERT INTO libros (titulo, autor, editorial, fecha_publicacion, sinopsis, portada) VALUES ('La historia del loco', 'John Katzenbach', 'De Bolsillo', '2004-06-15', '', '');
INSERT INTO libros (titulo, autor, editorial, fecha_publicacion, sinopsis, portada) VALUES ('El capital', 'Karl Marx', 'Fondo de Cultura Económica (FCE)', '1867-01-01', '', '');
INSERT INTO libros (titulo, autor, editorial, fecha_publicacion, sinopsis, portada) VALUES ('Sistemas Operativos Modernos', 'Andrew S. Tanenbaum', 'Pearson Prentice Hall', '1992-01-01', '', '');
INSERT INTO libros (titulo, autor, editorial, fecha_publicacion, sinopsis, portada) VALUES ('El Quijote de la Mancha', 'Miguel de Cervantes Saavedra', 'Grupo Editorial Tomo', '1605-01-01', '', '');
INSERT INTO libros (titulo, autor, editorial, fecha_publicacion, sinopsis, portada) VALUES ('El Psicoanalista', 'John Katzenbach', 'Ediciones B', '2003-01-01', '', '');
INSERT INTO libros (titulo, autor, editorial, fecha_publicacion, sinopsis, portada) VALUES ('Rayuela', 'Julio Cortázar', 'Punto de lectura', '1963-06-28', '', '');
INSERT INTO libros (titulo, autor, editorial, fecha_publicacion, sinopsis, portada) VALUES ('El Decamerón', 'Boccaccio', 'Porrúa', '1352-01-01', '', '');
INSERT INTO libros (titulo, autor, editorial, fecha_publicacion, sinopsis, portada) VALUES ('Las buenas conciencias', 'Carlos Fuentes', 'Punto de lectura', '1959-01-01', '', '');
INSERT INTO libros (titulo, autor, editorial, fecha_publicacion, sinopsis, portada) VALUES ('365 días para conocer la historia de México', 'Alejandro Rosas', 'mr ediciones', '2011-08-01', '', '');
INSERT INTO libros (titulo, autor, editorial, fecha_publicacion, sinopsis, portada) VALUES ('1984', 'George Orwell', 'Grupo Editorial Exodo', '1949-06-08', '', '');
INSERT INTO libros (titulo, autor, editorial, fecha_publicacion, sinopsis, portada) VALUES ('Los cuadernos de don Rigoberto', 'Mario Vargas Llosa', 'De bolsillo', '1997-01-01', '', '');
INSERT INTO libros (titulo, autor, editorial, fecha_publicacion, sinopsis, portada) VALUES ('El extraño caso del Dr. Jekyll y Mr. Hyde', 'Robert Louis Stevenson', 'Casa editorial Boek México', '1886-01-05', '', '');


-- Localizaciones
INSERT INTO localizaciones (estante, sala, librero, id_libro) VALUES ('S1', 'L1', 'E1', 1);
INSERT INTO localizaciones (estante, sala, librero, id_libro) VALUES ('S2', 'L3', 'E6', 2);
INSERT INTO localizaciones (estante, sala, librero, id_libro) VALUES ('S1', 'L2', 'E3', 3);
INSERT INTO localizaciones (estante, sala, librero, id_libro) VALUES ('S3', 'L5', 'E10', 4);
INSERT INTO localizaciones (estante, sala, librero, id_libro) VALUES ('S2', 'L4', 'E7', 5);

-- Salas
INSERT INTO salas (sala) VALUES ('S1');
INSERT INTO salas (sala) VALUES ('S2');
INSERT INTO salas (sala) VALUES ('S3');

-- Libreros
INSERT INTO libreros (sala_id, librero) VALUES (1, 'L1');
INSERT INTO libreros (sala_id, librero) VALUES (1, 'L2');
INSERT INTO libreros (sala_id, librero) VALUES (2, 'L3');
INSERT INTO libreros (sala_id, librero) VALUES (2, 'L4');
INSERT INTO libreros (sala_id, librero) VALUES (3, 'L5');
INSERT INTO libreros (sala_id, librero) VALUES (3, 'L6');

-- Estantes
INSERT INTO estantes (librero_id, estante) VALUES (1, 'E1');
INSERT INTO estantes (librero_id, estante) VALUES (1, 'E2');
INSERT INTO estantes (librero_id, estante) VALUES (2, 'E3');
INSERT INTO estantes (librero_id, estante) VALUES (2, 'E4');
INSERT INTO estantes (librero_id, estante) VALUES (3, 'E5');
INSERT INTO estantes (librero_id, estante) VALUES (3, 'E6');
INSERT INTO estantes (librero_id, estante) VALUES (4, 'E7');
INSERT INTO estantes (librero_id, estante) VALUES (4, 'E8');
INSERT INTO estantes (librero_id, estante) VALUES (5, 'E9');
INSERT INTO estantes (librero_id, estante) VALUES (5, 'E10');
INSERT INTO estantes (librero_id, estante) VALUES (6, 'E11');
INSERT INTO estantes (librero_id, estante) VALUES (6, 'E12');