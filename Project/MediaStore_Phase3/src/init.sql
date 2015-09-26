create table users
(
   user_id varchar(30) not null,
   name varchar(30),
   credit double,
   purchase_num smallint,
   address varchar(30),
   constraint pk_users primary key (user_id)
);


create table medias
(
    media_id smallint not null,
    available smallint,
    type varchar(30),
    name varchar(30),
    author varchar(30),
    time varchar(30),
    genre varchar(30),
    rank smallint,
    price double,
    sold_num smallint,
    rate_num smallint,
    rate double,
    rate_total double,
    year_released smallint,
    constraint pk_medias primary key (media_id)
);

create table purchases
(
    user_id varchar(30) references users(user_id),
    media_id smallint references medias(media_id)
);

create table rates
(
    media_id smallint references medias(media_id),
    user_id varchar(30) references users(user_id)
);


INSERT INTO users (user_id, name, credit, address) 
VALUES ('jiz51180', 'Jie Zheng', 200, 'Burrowes Street, State College');
INSERT INTO users (user_id, name, credit, address) 
VALUES ('jiz51181', 'Jie Zheng', 200, 'Burrowes Street, State College');
INSERT INTO users (user_id, name, credit, address) 
VALUES ('jiz51182', 'Jie Zheng', 200, 'Burrowes Street, State College');


INSERT INTO medias (media_id ,type, name, author, time, genre , rank , price ,year_released ) 
VALUES (0, 'movie', 'Skyfall0', 'Sam Mendes', '2:30:00', 'Action & Adverture', 1,14.99,2012);

INSERT INTO medias (media_id, type, name, author, time, genre , rank , price ,year_released ) 
VALUES (1,'movie', 'Skyfall1', 'Sam Mendes', '2:30:00', 'Action & Adverture', 1,14.99,2012);

INSERT INTO medias (media_id, type, name, author, time, genre , rank , price , year_released ) 
VALUES (2,'movie', 'Skyfall2', 'Sam Mendes', '2:30:00', 'Action & Adverture', 1,14.99,2012);

INSERT INTO medias (media_id, type, name, author, time, genre , rank , price , year_released ) 
VALUES (3, 'music', 'Two Lanes of Freedom0', 'Tim MCGraw', '43:24', 'Country', 1,13.99,0);

INSERT INTO medias (media_id ,type, name, author, time, genre , rank , price , year_released ) 
VALUES (4,'music', 'Two Lanes of Freedom1', 'Tim MCGraw', '43:24', 'Country', 1,13.99,0);

INSERT INTO medias (media_id ,type, name, author, time, genre , rank , price , year_released ) 
VALUES (5, 'music', 'Two Lanes of Freedom2', 'Tim MCGraw', '43:24', 'Country', 1,13.99,0);
INSERT INTO medias (media_id ,type, name, author, time, genre , rank , price , year_released ) 
VALUES (6, 'audioBook', 'American Sniper0', 'Chris Kyle', '10:22', 'Biography', 1,21.95,0);

INSERT INTO medias (media_id, type, name, author, time, genre , rank , price , year_released ) 
VALUES (7, 'audioBook', 'American Sniper1', 'Chris Kyle', '10:22', 'Biography', 1,21.95,0);

INSERT INTO medias (media_id ,type, name, author, time, genre , rank , price , year_released ) 
VALUES (8,'audioBook', 'American Sniper2', 'Chris Kyle', '10:22', 'Biography', 1,21.95,0);

update medias set available=1,sold_num=0,rate_num=0,rate=0,rate_total=0;
update users set purchase_num=0;
