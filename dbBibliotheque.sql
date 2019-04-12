create table Artistes(

	id int PRIMARY KEY AUTO_INCREMENT,
	nomArtiste varchar(255),
	isMembre boolean

);

create table Albums(

	id int,
	titre varchar(255),
	genre varchar(255),
	CONSTRAINT check_genre
  		CHECK (genre IN ('Classique', 'Hip-Hop', 'Rock', 'Folk', 'Jazz'))

);