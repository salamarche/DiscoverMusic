DROP TABLE IF EXISTS artist_engagement;
DROP TABLE IF EXISTS artist;
DROP TABLE IF EXISTS user;

create table artist
(
    id            int auto_increment
        primary key,
    soundcloud_id varchar(100) null,
    artist_name   varchar(100) null,
    location      varchar(100) null,
    avatar_url    varchar(500) null,
    description   varchar(500) null

);

create table user
(
    id        int auto_increment
        primary key,
    user_name varchar(100) not null,
    email     varchar(100) not null,
    password  char(64)     not null,
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

