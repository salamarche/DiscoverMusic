use test_music;

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
                         country_name varchar(100),
                         iso3 char(3)
);

create table region (
                        id INT primary key,
                        region_name varchar(100),
                        country_id int
);

create table city (
                      id INT primary key,
                      city_name varchar(100),
                      lat varchar(20),
                      lng varchar(20),
                      region_id int
);

ALTER TABLE region
    ADD constraint countryId_fk
        foreign key (country_id) references country(id);

ALTER TABLE city
    add constraint regionId_fk
        foreign key (region_id) references region(id);

/* ARTISTS */
create table artist
(
    id            int auto_increment
        primary key,
    spotify_id varchar(100) null,
    artist_name   varchar(100) null,
    avatar_url    varchar(500) null,
    description   varchar(500) null

);

create table artist_location
(
    artist_id int not null,
    city_id int not null,
    primary key (artist_id, city_id),

    constraint location_artistId_fk
        foreign key (artist_id) references artist (id),

    constraint Location_cityId_fk
        foreign key (city_id) references city (id)
);

create table user
(
    id        int auto_increment
        primary key,
    user_name varchar(100) not null,
    email     varchar(100) not null,
    user_role varchar(100) not null
);

create table artist_engagement
(
    artist_id       int      null,
    user_id         int      null,
    engagementDate datetime null,
    constraint engagement_artistId_fk
        foreign key (artist_id) references artist (id),
    constraint enagement_userId_fk
        foreign key (user_id) references user (id)
);

