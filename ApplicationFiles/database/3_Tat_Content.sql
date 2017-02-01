create schema MoPlatform;
set search_path to MoPlatform;

CREATE TABLE Author (
  AuthorID BIGSERIAL PRIMARY KEY,
  Code VARCHAR(50) NOT NULL,
  Name VARCHAR(255) NOT NULL,
  CreatedDate TIMESTAMP  NOT NULL,
  ModifiedDate TIMESTAMP DEFAULT NULL,
  UNIQUE (Code),
  UNIQUE (Name)
);

CREATE TABLE BookCategory (
  BookCategoryID BIGSERIAL PRIMARY KEY,
  Code VARCHAR(50) NOT NULL,
  Name VARCHAR(255) NOT NULL,
  CreatedDate TIMESTAMP  NOT NULL,
  ModifiedDate TIMESTAMP DEFAULT NULL,
  UNIQUE (Code),
  UNIQUE (Name)
);

CREATE TABLE Book (
  BookID BIGSERIAL PRIMARY KEY,
  Code VARCHAR(50) NOT NULL,
  Title VARCHAR(255) NOT NULL,
  Description TEXT,
  AuthorID BIGINT NOT NULL,
  FileType VARCHAR(50),
  Tags VARCHAR(255),
  Source VARCHAR(50),
  CoverURL VARCHAR(50) NOT NULL,
  uri VARCHAR(50) NOT NULL,
  alterUri VARCHAR(50) NOT NULL,
  BookCategoryID BIGINT NOT NULL,
  CreatedBy BIGINT NOT NULL,
  CreatedDate TIMESTAMP  NOT NULL,
  ModifiedDate TIMESTAMP DEFAULT NULL,
  UNIQUE (Code),
  UNIQUE (Title)
);
ALTER TABLE Book ADD FOREIGN KEY (AuthorID) REFERENCES Author (AuthorID) ON DELETE NO ACTION;
ALTER TABLE Book ADD FOREIGN KEY (CreatedBy) REFERENCES Users (UserID) ON DELETE NO ACTION;
ALTER TABLE Book ADD FOREIGN KEY (BookCategoryID) REFERENCES BookCategory (BookCategoryID) ON DELETE NO ACTION;


CREATE TABLE NewsCategory (
  NewsCategoryID BIGSERIAL PRIMARY KEY,
  Code VARCHAR(50) NOT NULL,
  Name VARCHAR(255) NOT NULL,
  CreatedDate TIMESTAMP  NOT NULL,
  ModifiedDate TIMESTAMP DEFAULT NULL,
  UNIQUE (Code),
  UNIQUE (Name)
);

CREATE TABLE News (
  NewsID BIGSERIAL PRIMARY KEY,
  Title VARCHAR(255) NOT NULL,
  Description TEXT,
  Thumbnail VARCHAR(50) NOT NULL,
  Content TEXT,
  Tags VARCHAR(255),
  Source VARCHAR(50),
  CreatedBy BIGINT NOT NULL,
  CategoryID BIGINT NOT NULL,
  CreatedDate TIMESTAMP  NOT NULL,
  ModifiedDate TIMESTAMP DEFAULT NULL,
  UNIQUE (Title)
);
ALTER TABLE News ADD FOREIGN KEY (CreatedBy) REFERENCES Users (UserID) ON DELETE NO ACTION;
ALTER TABLE News ADD FOREIGN KEY (CategoryID) REFERENCES NewsCategory (NewsCategoryID) ON DELETE NO ACTION;

CREATE TABLE TipCategory (
  TipCategoryID BIGSERIAL PRIMARY KEY,
  Code VARCHAR(50) NOT NULL,
  Name VARCHAR(255) NOT NULL,
  CreatedDate TIMESTAMP  NOT NULL,
  ModifiedDate TIMESTAMP DEFAULT NULL,
  UNIQUE (Code),
  UNIQUE (Name)
);

CREATE TABLE Tip (
  TipID BIGSERIAL PRIMARY KEY,
  Title VARCHAR(255) NOT NULL,
  Description TEXT,
  Thumbnail VARCHAR(50),
  Content TEXT,
  Tags VARCHAR(255),
  Source VARCHAR(50),
  CreatedBy BIGINT NOT NULL,
  CategoryID BIGINT NOT NULL,
  CreatedDate TIMESTAMP  NOT NULL,
  ModifiedDate TIMESTAMP DEFAULT NULL,
  UNIQUE (Title)
);
ALTER TABLE Tip ADD FOREIGN KEY (CreatedBy) REFERENCES Users (UserID) ON DELETE NO ACTION;
ALTER TABLE Tip ADD FOREIGN KEY (CategoryID) REFERENCES TipCategory (TipCategoryID) ON DELETE NO ACTION;
