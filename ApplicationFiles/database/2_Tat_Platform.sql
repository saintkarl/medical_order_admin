create schema MoPlatform;
set search_path to MoPlatform;

CREATE TABLE Permission (
  PermissionID BIGSERIAL PRIMARY KEY,
  Code VARCHAR(50) NOT NULL,
  Name VARCHAR(255) NOT NULL,
  CreatedDate TIMESTAMP  NOT NULL,
  ModifiedDate TIMESTAMP DEFAULT NULL,
  UNIQUE (Code),
  UNIQUE (Name)
);

CREATE TABLE UserGroup (
  UserGroupID BIGSERIAL NOT NULL PRIMARY KEY,
  Code VARCHAR(100) NOT NULL,
  Name VARCHAR(255) NOT NULL,
  CreatedDate TIMESTAMP NOT NULL,
  ModifiedDate TIMESTAMP ,
  UNIQUE(Code),
  UNIQUE(Name)
);

CREATE TABLE UserGroupACL (
  UserGroupACLID BIGSERIAL PRIMARY KEY,
  UserGroupID BIGINT NOT NULL,
  PermissionID BIGINT NOT NULL,
  CreatedDate TIMESTAMP NOT NULL,
  ModifiedDate TIMESTAMP,
  UNIQUE(UserGroupID, PermissionID),
  FOREIGN KEY (UserGroupID) REFERENCES UserGroup(UserGroupID) ON DELETE NO ACTION ON UPDATE NO ACTION,
  FOREIGN KEY (PermissionID) REFERENCES Permission(PermissionID) ON DELETE NO ACTION ON UPDATE NO ACTION
);

CREATE TABLE Users (
  UserID BIGSERIAL NOT NULL PRIMARY KEY,
  Username VARCHAR(255) NOT NULL,
  Password VARCHAR(255),
  FirstName VARCHAR(100),
  LastName VARCHAR(100),
  Email VARCHAR(255),
  Code VARCHAR(255),
  PhoneNumber VARCHAR(20),
  Status INTEGER NOT NULL DEFAULT 0,
  CreatedDate TIMESTAMP NOT NULL,
  ModifiedDate TIMESTAMP,
  UserGroupID BIGINT NOT NULL,
  Role VARCHAR(255),
  UNIQUE (UserName),
  UNIQUE (Email)
);

ALTER TABLE Users ADD FOREIGN KEY (UserGroupID) REFERENCES UserGroup (UserGroupID) ON DELETE NO ACTION;

CREATE TABLE Role (
  RoleID BIGSERIAL PRIMARY KEY,
  Code VARCHAR(50) NOT NULL,
  Name VARCHAR(255) NOT NULL,
  CreatedDate TIMESTAMP  NOT NULL,
  ModifiedDate TIMESTAMP DEFAULT NULL,
  UNIQUE (Code),
  UNIQUE (Name)
);

CREATE TABLE RoleACL (
  RoleACLID BIGSERIAL PRIMARY KEY,
  RoleID BIGINT NOT NULL,
  PermissionID BIGINT NOT NULL,
  CreatedDate TIMESTAMP NOT NULL,
  ModifiedDate TIMESTAMP,
  UNIQUE(RoleID, PermissionID),
  FOREIGN KEY (RoleID) REFERENCES Role(RoleID) ON DELETE NO ACTION ON UPDATE NO ACTION,
  FOREIGN KEY (PermissionID) REFERENCES Permission(PermissionID) ON DELETE NO ACTION ON UPDATE NO ACTION
);

CREATE TABLE UserRole (
  UserRoleID BIGSERIAL PRIMARY KEY,
  UserID BIGINT NOT NULL,
  RoleID BIGINT NOT NULL,
  CreatedDate TIMESTAMP  NOT NULL,
  ModifiedDate TIMESTAMP DEFAULT NULL,
  UNIQUE (UserID, RoleID),
  FOREIGN KEY (UserID) REFERENCES Users(UserID) ON DELETE CASCADE ON UPDATE NO ACTION,
  FOREIGN KEY (RoleID) REFERENCES Role(RoleID) ON DELETE NO ACTION ON UPDATE NO ACTION
);

CREATE TABLE UserDemographic (
  UserDemographicID BIGSERIAL NOT NULL PRIMARY KEY,
  UserID BIGINT NOT NULL,
  Sex VARCHAR(1),
  BirthDay DATE,
  PlaceOfBirth VARCHAR(255),
  Avatar VARCHAR(255),
  ProfileURL VARCHAR(255),
  CreatedDate TIMESTAMP NOT NULL,
  ModifiedDate TIMESTAMP,
  UNIQUE(UserID)
);
ALTER TABLE UserDemographic ADD FOREIGN KEY (UserID) REFERENCES Users (UserID) ON DELETE NO ACTION;
ALTER TABLE UserDemographic ADD FOREIGN KEY (CityOfBirthID) REFERENCES City (CityID) ON DELETE NO ACTION;

INSERT INTO UserGroup(UserGroupID, Code, Name, CreatedDate, ModifiedDate) values (1,'ADMIN','ADMIN',NOW(),NOW());

-- INSERT INTO users(UserID, Username, Password, FirstName, LastName, Email, Code, PhoneNumber, Status, CreatedDate, ModifiedDate, UserGroupID) VALUES (1, 'admin','NHeKr5howj0=', 'Admin','Admin','admin@admin.com','Admin','0969906805','1',NOW(),NOW(), 1);
INSERT INTO users(UserID, Username, Password, FirstName, LastName, Email, Code, PhoneNumber, Status, CreatedDate, ModifiedDate, UserGroupID) VALUES (1, 'admin','99SLI553amM=', 'Admin','Admin','admin@admin.com','Admin','0969906805','1',NOW(),NOW(), 1);

