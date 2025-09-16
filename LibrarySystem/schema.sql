create database library;

use library;


-- Users table (for all system users including staff and members)
CREATE TABLE Users(
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    email VARCHAR(255) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL,
    phone VARCHAR(255),
    address VARCHAR(255),
    user_type ENUM('staff', 'member') NOT NULL
);

-- Staff details (extends Users)
CREATE TABLE Staff(
    id INT PRIMARY KEY,
    role ENUM('librarian', 'administrator') NOT NULL,
    employee_id VARCHAR(255) UNIQUE NOT NULL,
    is_active BOOLEAN DEFAULT TRUE,
    FOREIGN KEY (id) REFERENCES Users(id) ON DELETE CASCADE
);

-- Member details (extends Users)
CREATE TABLE Members(
    id INT PRIMARY KEY,
    membership_start_date DATE NOT NULL,
    membership_end_date DATE NOT NULL,
    membership_number VARCHAR(255) UNIQUE NOT NULL,
    FOREIGN KEY (id) REFERENCES Users(id) ON DELETE CASCADE
);

-- Publisher table
CREATE TABLE Publishers(
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    email VARCHAR(255),
    phone VARCHAR(255),
    country VARCHAR(255),
    city VARCHAR(255)
);

-- Category table 
CREATE TABLE Categories(
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    parent_id INT NULL, -- References another category in this same table
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (parent_id) REFERENCES Categories(id)   -- Self-referencing foreign key
);

-- Author table
CREATE TABLE Authors(
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    nationality VARCHAR(255)
);

-- Book table
CREATE TABLE Books(
    id INT AUTO_INCREMENT PRIMARY KEY,
    category_id INT,
    language VARCHAR(100),
    publication_year YEAR,
    ISBN VARCHAR(20) NOT NULL UNIQUE,
    title VARCHAR(255) NOT NULL,
    edition VARCHAR(50),
    summary TEXT,
    cover_url VARCHAR(255),
    total_copies INT DEFAULT 1,
    available_copies INT DEFAULT 1,
    FOREIGN KEY (category_id) REFERENCES Categories(id)
);

-- Book-Authors table (many-to-many)
CREATE TABLE BookAuthors(
    book_id INT NOT NULL,
    author_id INT NOT NULL,
    author_order INT NOT NULL DEFAULT 1,
    PRIMARY KEY (book_id, author_id),
    FOREIGN KEY (book_id) REFERENCES Books(id) ON DELETE CASCADE,
    FOREIGN KEY (author_id) REFERENCES Authors(id) ON DELETE CASCADE
);

-- Book-Publishers table (many-to-many)
CREATE TABLE BookPublishers(
    book_id INT NOT NULL,
    publisher_id INT NOT NULL,
    PRIMARY KEY (book_id, publisher_id),
    FOREIGN KEY (book_id) REFERENCES Books(id) ON DELETE CASCADE,
    FOREIGN KEY (publisher_id) REFERENCES Publishers(id) ON DELETE CASCADE
);

-- Transactions table
CREATE TABLE Transactions(
    id INT AUTO_INCREMENT PRIMARY KEY,
    book_id INT NOT NULL,
    member_id INT NOT NULL,
    issued_by INT NOT NULL, -- staff who issued the book
    issue_date DATE NOT NULL,
    due_date DATE NOT NULL,
    return_date DATE NULL,
    status ENUM('available' , 'borrowed', 'returned', 'overdue') NOT NULL DEFAULT 'available',
    FOREIGN KEY (book_id) REFERENCES Books(id),
    FOREIGN KEY (member_id) REFERENCES Members(id),
    FOREIGN KEY (issued_by) REFERENCES Staff(id)
);

-- Create indexes for better performance
CREATE INDEX idx_books_title ON Books(title);
CREATE INDEX idx_books_isbn ON Books(ISBN);
CREATE INDEX idx_books_publication_year ON Books(publication_year);
CREATE INDEX idx_users_email ON Users(email);
-- CREATE INDEX idx_transactions_due_date ON Transactions(due_date);
CREATE INDEX idx_transactions_status ON Transactions(status);
CREATE INDEX idx_members_membership_number ON Members(membership_number);