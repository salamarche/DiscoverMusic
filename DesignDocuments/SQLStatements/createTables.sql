create table artist
(
    id          int auto_increment
        primary key,
    spotify_id  varchar(100) null,
    artist_name varchar(100) null,
    avatar_url  varchar(500) null,
    description varchar(500) null,
    href        varchar(100) null
);

create table country
(
    id           int          not null
        primary key,
    country_name varchar(100) null,
    iso3         char(3)      null
);

create table region
(
    id          int          not null
        primary key,
    region_name varchar(100) null,
    country_id  int          null,
    constraint countryId_fk
        foreign key (country_id) references country (id)
);

create table city
(
    id        int          not null
        primary key,
    city_name varchar(100) null,
    lat       varchar(20)  null,
    lng       varchar(20)  null,
    region_id int          null,
    constraint regionId_fk
        foreign key (region_id) references region (id)
);

create table artist_location
(
    artist_id int not null,
    city_id   int not null,
    primary key (artist_id, city_id),
    constraint Location_cityId_fk
        foreign key (city_id) references city (id),
    constraint location_artistId_fk
        foreign key (artist_id) references artist (id)
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
    id             int auto_increment
        primary key,
    artist_id      int      not null,
    user_id        int      not null,
    engagementDate datetime null,
    constraint unique_artist_user
        unique (artist_id, user_id),
    constraint engagement_artistId_fk
        foreign key (artist_id) references artist (id),
    constraint engagement_userId_fk
        foreign key (user_id) references user (id)
);


