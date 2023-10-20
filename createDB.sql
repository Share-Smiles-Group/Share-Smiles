-- Create database
CREATE DATABASE ShareSmiles;

USE ShareSmiles;

-- Create User table
CREATE TABLE User (
    UserId int NOT NULL AUTO_INCREMENT,
    UserName varchar(255) NOT NULL,
    Password varchar(255) NOT NULL,
    Email varchar(255) NOT NULL UNIQUE,
    PRIMARY KEY (UserId)
);

-- Create Topic table
CREATE TABLE Topic (
    TopicId int NOT NULL AUTO_INCREMENT,
    TopicName varchar(255) NOT NULL UNIQUE,
    PRIMARY KEY (TopicId)
);

-- Create Post table
CREATE TABLE Post (
    PostId int NOT NULL AUTO_INCREMENT,
    PostName varchar(255) NOT NULL,
    Heat int NOT NULL,
    Topic int NOT NULL,
    Creator int NOT NULL,
    Created_at DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
    PRIMARY KEY (PostId),
    FOREIGN KEY (Topic) REFERENCES Topic(TopicId),
    FOREIGN KEY (Creator) REFERENCES User(UserId)
);

-- Create Comment table
CREATE TABLE Comment (
    CommentId int NOT NULL AUTO_INCREMENT,
    Content varchar(1000) NOT NULL,
    Created_at DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
    Post int NOT NULL,
    Sender int NOT NULL,
    PRIMARY KEY (CommentId),
    FOREIGN KEY (Post) REFERENCES Post(PostId),
    FOREIGN Key (Sender) REFERENCES User(UserId)
);