-- Bank Management System Database Setup
-- Run this script in MySQL Workbench

-- Create database
DROP DATABASE IF EXISTS bankSystem;
CREATE DATABASE bankSystem;
USE bankSystem;

-- Table for Page 1 signup (Personal Details)
CREATE TABLE signup (
    formno VARCHAR(20) PRIMARY KEY,
    name VARCHAR(100),
    fname VARCHAR(100),
    dob VARCHAR(50),
    gender VARCHAR(10),
    email VARCHAR(100),
    marital VARCHAR(20),
    address VARCHAR(200),
    city VARCHAR(50),
    pincode VARCHAR(10),
    state VARCHAR(50)
);

-- Table for Page 2 signup (Additional Details)
CREATE TABLE signuptwo (
    formno VARCHAR(20),
    religion VARCHAR(30),
    category VARCHAR(30),
    income VARCHAR(40),
    education VARCHAR(40),
    occupation VARCHAR(40),
    pan VARCHAR(20),
    aadhar VARCHAR(20),
    senior_citizen VARCHAR(10),
    existing_account VARCHAR(10),
    FOREIGN KEY (formno) REFERENCES signup(formno)
);

-- Table for Page 3 signup (Account Details)
CREATE TABLE signupthree (
    formno VARCHAR(20),
    account_type VARCHAR(50),
    card_number VARCHAR(20),
    pin VARCHAR(10),
    facilities VARCHAR(200),
    FOREIGN KEY (formno) REFERENCES signup(formno)
);

-- Table for login credentials
CREATE TABLE login (
    formno VARCHAR(20),
    card_number VARCHAR(20) PRIMARY KEY,
    pin VARCHAR(10),
    failed_attempts INT DEFAULT 0,
    is_locked BOOLEAN DEFAULT FALSE,
    FOREIGN KEY (formno) REFERENCES signup(formno)
);

-- Table for admin credentials
CREATE TABLE admin (
    username VARCHAR(50) PRIMARY KEY,
    password VARCHAR(50)
);

-- Insert default admin
INSERT INTO admin (username, password) VALUES ('admin', 'admin123');

-- Table for bank transactions
CREATE TABLE bank (
    pin VARCHAR(10),
    date VARCHAR(100),
    type VARCHAR(30),
    amount VARCHAR(20)
);

-- Verify tables created
SHOW TABLES;

-- Display success message
SELECT 'Database bankSystem updated with admin table' AS Status;
