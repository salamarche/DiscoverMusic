DROP TABLE IF EXISTS country;
DROP TABLE IF EXISTS region;
DROP TABLE IF EXISTS city;

DROP TABLE IF EXISTS artist_engagement;
DROP TABLE IF EXISTS artist;
DROP TABLE IF EXISTS user;

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
    spotify_id varchar(100) null,
    artist_name   varchar(100) null,
    city_id       int null,
    avatar_url    varchar(500) null,
    description   varchar(500) null,

    constraint FK_location_id
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
    engagement_date datetime null,
    constraint FK_artist_id
        foreign key (artist_id) references artist (id),
    constraint FK_user_id
        foreign key (user_id) references user (id)
);

