.open db/bibliotheque.db

.header on
.mode column

DROP TABLE Albums;
DROP TABLE Artistes;

CREATE TABLE Artistes (
	id integer primary key autoincrement,
	nom varchar(255),
	isMembre boolean,
	photo varchar(255)
);

INSERT INTO Artistes (nom, isMembre, photo) VALUES ("Gregson Destin", true, "src\\images\\default.png");
INSERT INTO Artistes (nom, isMembre, photo) VALUES ("Isaac David Zolana", true, "src\\images\\default.png");
INSERT INTO Artistes (nom, isMembre, photo) VALUES ("Drake", false, "src\\images\\default.png");
INSERT INTO Artistes (nom, isMembre, photo) VALUES ("Test Suppression", false, "src\\images\\default.png");

SELECT * FROM Artistes;

CREATE TABLE Albums(
	id INTEGER PRIMARY KEY autoincrement,
	idArtiste INTEGER,
	titre VARCHAR(255),
	genre VARCHAR(255),
	anneeSortie INTEGER,
	photo varchar(255),
	CONSTRAINT fk_Artistes_Albums FOREIGN KEY("idArtiste")
		REFERENCES Artistes("id")
);

INSERT INTO Albums (idArtiste, titre, genre, anneeSortie, photo) VALUES (1, "Procastination, my nation", "Hip Hop", 2010, "src\\images\\default.png");
INSERT INTO Albums (idArtiste, titre, genre, anneeSortie, photo) VALUES (1, "The Support", "Hip Hop", 2010, "src\\images\\default.png");

INSERT INTO Albums (idArtiste, titre, genre, anneeSortie, photo) VALUES (2, "Confusion", "Jazz", 2013, "src\\images\\default.png");
INSERT INTO Albums (idArtiste, titre, genre, anneeSortie, photo) VALUES (2, "Imagination", "Jazz", 2015, "src\\images\\default.png");

INSERT INTO Albums (idArtiste, titre, genre, anneeSortie, photo) VALUES (3, "If you're reading this", "Hip Hop", 2015, "src\\images\\albums\\Drake album.jpg");

INSERT INTO Albums (idArtiste, titre, genre, anneeSortie, photo) VALUES (4, "If you're reading this", "Hip Hop", 2015, "src\\images\\default.png");

SELECT * FROM Albums;

SELECT * FROM Artistes;
SELECT * FROM Albums;