create table Artistes(
	id int PRIMARY KEY AUTO_INCREMENT,
	nom varchar(255),
	isMembre boolean,
	photo varchar(255)
);

create table Albums(
	id int PRIMARY KEY AUTO_INCREMENT,
	id_Artiste int,
	titre varchar(255),
	genre varchar(255),
	anneeSortie date,
	imageCouverture varchar(255),
	CONSTRAINT check_genre
  		CHECK (genre IN ('Classique', 'Hip-Hop', 'Rock', 'Folk', 'Jazz'))
);