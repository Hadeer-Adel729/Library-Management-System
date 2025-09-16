use library;

-- Insert Users (staff and members)
INSERT INTO Users (name, email, password, phone, address, user_type) VALUES
-- Staff users
('Sarah Johnson', 'sarah.johnson@library.com', 'hashed_password_1', '555-0101', '123 Library Lane, Bookville', 'staff'),
('Michael Chen', 'michael.chen@library.com', 'hashed_password_2', '555-0102', '456 Reading Rd, Bookville', 'staff'),
('Emily Rodriguez', 'emily.rodriguez@library.com', 'hashed_password_3', '555-0103', '789 Novel Ave, Bookville', 'staff'),
('David Kim', 'david.kim@library.com', 'hashed_password_4', '555-0104', '321 Chapter St, Bookville', 'staff'),

-- Member users
('John Smith', 'john.smith@email.com', 'hashed_password_5', '555-0201', '100 Reader Street, Bookville', 'member'),
('Maria Garcia', 'maria.garcia@email.com', 'hashed_password_6', '555-0202', '200 Bookworm Ave, Bookville', 'member'),
('Robert Wilson', 'robert.wilson@email.com', 'hashed_password_7', '555-0203', '300 Page Road, Bookville', 'member'),
('Lisa Thompson', 'lisa.thompson@email.com', 'hashed_password_8', '555-0204', '400 Story Lane, Bookville', 'member'),
('James Brown', 'james.brown@email.com', 'hashed_password_9', '555-0205', '500 Literature Dr, Bookville', 'member'),
('Susan Davis', 'susan.davis@email.com', 'hashed_password_10', '555-0206', '600 Author Way, Bookville', 'member');

-- Insert Staff details
INSERT INTO Staff (id, role, employee_id, is_active) VALUES
(1, 'librarian', 'LIB001', TRUE),
(2, 'librarian', 'LIB002', TRUE),
(3, 'administrator', 'ADM001', TRUE),
(4, 'librarian', 'LIB003', FALSE); -- Inactive staff

-- Insert Member details
INSERT INTO Members (id, membership_start_date, membership_end_date, membership_number) VALUES
(5, '2024-01-15', '2025-01-15', 'MEM001'),
(6, '2024-02-01', '2025-02-01', 'MEM002'),
(7, '2024-01-10', '2025-01-10', 'MEM003'),
(8, '2024-03-15', '2025-03-15', 'MEM004'),
(9, '2024-02-20', '2025-02-20', 'MEM005'),
(10, '2024-01-05', '2025-01-05', 'MEM006');

-- Insert Publishers
INSERT INTO Publishers (name, email, phone, country, city) VALUES
('Penguin Random House', 'contact@penguinrandomhouse.com', '212-782-9000', 'USA', 'New York'),
('HarperCollins', 'info@harpercollins.com', '212-207-7000', 'USA', 'New York'),
('Macmillan Publishers', 'inquiries@macmillan.com', '646-307-5151', 'USA', 'New York'),
('Simon & Schuster', 'customer.service@simonandschuster.com', '212-698-7000', 'USA', 'New York'),
('Oxford University Press', 'enquiry@oup.com', '+44-1865-556767', 'UK', 'Oxford'),
('Cambridge University Press', 'information@cambridge.org', '+44-1223-358331', 'UK', 'Cambridge');

-- Insert Categories (with hierarchical structure)
INSERT INTO Categories (name, parent_id) VALUES
-- Top-level categories
('Fiction', NULL),
('Non-Fiction', NULL),
('Science & Technology', NULL),
('Children', NULL),

-- Subcategories of Fiction
('Science Fiction', 1),
('Fantasy', 1),
('Mystery', 1),
('Romance', 1),

-- Subcategories of Non-Fiction
('Biography', 2),
('History', 2),
('Self-Help', 2),

-- Subcategories of Science & Technology
('Computer Science', 3),
('Mathematics', 3),
('Physics', 3),

-- Subcategories of Children
('Picture Books', 4),
('Young Adult', 4),
('Educational', 4);

-- Insert Authors
INSERT INTO Authors (name, nationality) VALUES
('George Orwell', 'British'),
('J.K. Rowling', 'British'),
('Stephen King', 'American'),
('Isaac Asimov', 'American'),
('J.R.R. Tolkien', 'British'),
('Agatha Christie', 'British'),
('Dan Brown', 'American'),
('Margaret Atwood', 'Canadian'),
('Yuval Noah Harari', 'Israeli'),
('Michelle Obama', 'American'),
('Walter Isaacson', 'American'),
('Malcolm Gladwell', 'Canadian'),
('Andrew Ng', 'American'),
('Stephen Hawking', 'British'),
('Neil deGrasse Tyson', 'American');

-- Insert Books
INSERT INTO Books (category_id, language, publication_year, ISBN, title, edition, summary, cover_url, total_copies, available_copies) VALUES
(5, 'English', '1949', '978-0451524935', '1984', '1st', 'A dystopian social science fiction novel', '/covers/1984.jpg', 3, 2),
(5, 'English', '1951', '978-0553293357', 'Foundation', '1st', 'First book in the Foundation series', '/covers/foundation.jpg', 2, 1),
(6, 'English', '1997', '978-0439064873', 'Harry Potter and the Philosopher''s Stone', '1st', 'First book in the Harry Potter series', '/covers/hp1.jpg', 5, 3),
(6, 'English', '1954', '978-0618640157', 'The Lord of the Rings', '2nd', 'Epic high fantasy novel', '/covers/lotr.jpg', 4, 2),
(7, 'English', '1934', '978-0062073501', 'Murder on the Orient Express', '1st', 'Hercule Poirot mystery novel', '/covers/orient.jpg', 2, 1),
(9, 'English', '2018', '978-0525563239', 'Becoming', '1st', 'Memoir by former First Lady Michelle Obama', '/covers/becoming.jpg', 3, 2),
(10, 'English', '2011', '978-0062060242', 'Steve Jobs', '1st', 'Biography of Apple co-founder Steve Jobs', '/covers/jobs.jpg', 2, 1),
(12, 'English', '2018', '978-0316478526', 'Atomic Habits', '1st', 'Guide to building good habits and breaking bad ones', '/covers/atomic.jpg', 4, 3),
(13, 'English', '2017', '978-0134093413', 'Deep Learning', '1st', 'Textbook on deep learning algorithms', '/covers/deeplearning.jpg', 2, 1),
(14, 'English', '1988', '978-0553380163', 'A Brief History of Time', '1st', 'Popular science book about cosmology', '/covers/time.jpg', 3, 2);

-- Insert Book-Authors relationships
INSERT INTO BookAuthors (book_id, author_id, author_order) VALUES
(1, 1, 1),
(2, 4, 1),
(3, 2, 1),
(4, 5, 1),
(5, 6, 1),
(6, 10, 1),
(7, 11, 1),
(8, 12, 1),
(9, 13, 1),
(10, 14, 1);

-- Insert Book-Publishers relationships
INSERT INTO BookPublishers (book_id, publisher_id) VALUES
(1, 1),
(2, 1),
(3, 2),
(4, 3),
(5, 4),
(6, 1),
(7, 1),
(8, 2),
(9, 5),
(10, 6);

-- Insert Transactions
INSERT INTO Transactions (book_id, member_id, issued_by, issue_date, due_date, return_date, status) VALUES
(1, 5, 1, '2024-03-01', '2024-03-15', '2024-03-10', 'returned'),
(3, 6, 2, '2024-03-05', '2024-03-19', NULL, 'borrowed'),
(4, 7, 1, '2024-03-10', '2024-03-24', NULL, 'borrowed'),
(2, 8, 2, '2024-02-20', '2024-03-05', '2024-03-08', 'returned'),
(5, 9, 1, '2024-03-12', '2024-03-26', NULL, 'borrowed'),
(7, 10, 2, '2024-02-15', '2024-02-29', '2024-02-28', 'returned'),
(10, 5, 1, '2024-03-15', '2024-03-29', NULL, 'borrowed'),
(6, 6, 2, '2024-03-08', '2024-03-22', NULL, 'borrowed'),
(9, 7, 1, '2024-03-03', '2024-03-17', '2024-03-16', 'returned'),
(8, 8, 2, '2024-02-25', '2024-03-10', NULL, 'overdue');