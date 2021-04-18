DROP TABLE IF EXISTS artist_location;
DROP TABLE IF EXISTS artist_engagement;
DROP TABLE IF EXISTS artist;
DROP TABLE IF EXISTS user;
DROP TABLE IF EXISTS city;
DROP TABLE IF EXISTS region;
DROP TABLE IF EXISTS country;

/* LOCATIONS */
create table country (
                         id INT primary key,
                         countryName varchar(100),
                         iso3 char(3)
);

create table region (
                        id INT primary key,
                        regionName varchar(100),
                        countryId int
);

create table city (
                      id INT primary key,
                      cityName varchar(100),
                      lat varchar(20),
                      lng varchar(20),
                      regionId int
);

ALTER TABLE region
    ADD constraint fk_countryId
        foreign key (countryId) references country(id);

ALTER TABLE city
    add constraint fk_regionId
        foreign key (regionId) references region(id);

/* ARTISTS */
create table artist
(
    id            int auto_increment
        primary key,
    spotifyId varchar(100) null,
    artistName   varchar(100) null,
    avatarUrl    varchar(500) null,
    description   varchar(500) null

);

create table artist_location
(
    artistId int not null,
    cityId int not null,
    primary key (artistId, cityId),

    constraint artistId_fk
        foreign key (artistId) references artist (id),

    constraint cityId_fk
        foreign key (cityId) references city (id)
);

create table user
(
    id        int auto_increment
        primary key,
    userName varchar(100) not null,
    email     varchar(100) not null,
    userRole varchar(100) not null
);

create table artist_engagement
(
    artistId       int      null,
    userId         int      null,
    engagementDate datetime null,
    constraint FK_artist_id
        foreign key (artistId) references artist (id),
    constraint FK_user_id
        foreign key (userId) references user (id)
);

