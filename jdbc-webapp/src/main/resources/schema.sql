BEGIN;

DROP TABLE IF EXISTS Reviews;
DROP TABLE IF EXISTS Rentals;
DROP TABLE IF EXISTS Users;
DROP TABLE IF EXISTS Movies;

CREATE TABLE IF NOT EXISTS Movies (
    MovieID INTEGER PRIMARY KEY AUTO_INCREMENT,
    Title VARCHAR(255) NOT NULL,
    Director VARCHAR(255),
    Genre VARCHAR(100),
    ReleaseYear INT,
    Rating DECIMAL(2,1),
    Description TEXT
);

CREATE TABLE IF NOT EXISTS Users (
    UserID INTEGER PRIMARY KEY AUTO_INCREMENT,
    FirstName VARCHAR(100) NOT NULL,
    LastName VARCHAR(100) NOT NULL,
    Email VARCHAR(255) UNIQUE NOT NULL,
    Phone VARCHAR(20),
    Password VARCHAR(255),
    JoinDate DATE DEFAULT (CURRENT_DATE)
);

CREATE TABLE IF NOT EXISTS Rentals (
    RentalID INTEGER PRIMARY KEY AUTO_INCREMENT,
    MovieID INTEGER NOT NULL,
    UserID INTEGER NOT NULL,
    StartDate DATE NOT NULL DEFAULT (CURRENT_DATE),
    ReturnDate DATE NOT NULL DEFAULT ((CURRENT_DATE) + INTERVAL 3 DAY),
    Returned BOOLEAN DEFAULT FALSE,
    FOREIGN KEY (MovieID) REFERENCES Movies(MovieID) ON DELETE CASCADE,
    FOREIGN KEY (UserID) REFERENCES Users(UserID) ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS Reviews (
    ReviewID INTEGER PRIMARY KEY AUTO_INCREMENT,
    UserID INTEGER NOT NULL,
    MovieID INTEGER NOT NULL,
    Rating DECIMAL(2,1) NOT NULL,
    Comment TEXT,
    FOREIGN KEY (MovieID) REFERENCES Movies(MovieID) ON DELETE CASCADE,
    FOREIGN KEY (UserID) REFERENCES Users(UserID) ON DELETE CASCADE
);

INSERT INTO Movies (Title, Director, Genre, ReleaseYear, Rating, Description) 
VALUES ("a title", "a director", "a genre", 2024, 5.0, "BLA BLA BLA");

INSERT INTO Movies (Title, Director, Genre, ReleaseYear, Rating, Description) 
VALUES ("IDK???Title", "some director", "pop", 6969, 1.0, "BLA BLA 2");

INSERT INTO Users (FirstName, LastName, Email, Phone, Password) 
VALUES ("Royce", "Li", "admin@admin.com", "000-000-0000", "password");

COMMIT;
