# Bank Management System - MCA Minor Project 🏦

**MCA First Year Minor Project**  
A professional Java Swing-based desktop application for managing banking operations. This project simulates an ATM experience, utilizing Java JDBC and MySQL for secure data persistence.

---

## 🎓 Academic Context
- **Course**: Master of Computer Applications (MCA)
- **Year**: First Year
- **Project Type**: Minor Project
- **Developer**: Omkar Bhandalkar

---

## 🌟 Key Features
- **Atmospheric UI**: Classic ATM-styled interface for a realistic user experience.
- **Secure Transactions**: Uses `PreparedStatement` to protect against SQL Injection.
- **Account Locking**: Automatically locks accounts after 3 failed login attempts.
- **Transaction Limits**: Daily withdrawal limit of Rs. 20,000 for enhanced security.
- **Statement Export**: Export transaction history directly to a `.txt` file on your Desktop.
- **Robust Validation**: Built-in email and field validation for accurate data entry.

---

## 🛠️ Prerequisites
Before running this project on another PC, ensure you have the following installed:
1. **Java Development Kit (JDK)**: Version 8 or higher.
2. **MySQL Server**: Installed and running.
3. **IDE (Optional)**: IntelliJ IDEA, Eclipse, or NetBeans for development.

---

## ⚙️ Installation & Setup

### 1. Database Configuration
1. Open your MySQL Command Line or Workbench.
2. Create the database:
   ```sql
   create database bankSystem;
   use bankSystem;
   ```
3. Create the necessary tables:
   ```sql
   -- Signup Table
   create table signup(formno varchar(20), name varchar(20), father_name varchar(20), dob varchar(20), gender varchar(20), email varchar(30), marital_status varchar(20), address varchar(40), city varchar(20), pincode varchar(20), state varchar(20));

   -- Additional Info Table
   create table signuptwo(formno varchar(20), religion varchar(20), category varchar(20), income varchar(20), education varchar(20), occupation varchar(20), pan varchar(20), aadhar varchar(20), seniorcitizen varchar(20), existingaccount varchar(20));

   -- Account Details Table
   create table signupthree(formno varchar(20), account_type varchar(40), card_number varchar(30), pin varchar(10), facility varchar(100));

   -- Login Table
   create table login(formno varchar(20), card_number varchar(30), pin varchar(10), failed_attempts int default 0, is_locked boolean default false);

   -- Transactions Table
   create table bank(pin varchar(10), date varchar(50), type varchar(20), amount varchar(20));
   ```

### 2. Project Setup
1. Copy the project folder to the new PC.
2. Ensure the following JAR files are in the `lib` folder:
   - `mysql-connector-j-8.0.33.jar`
   - `jcalendar-1.4.jar`
3. If your MySQL credentials are different, update them in:
   `src/bank/management/system/Connn.java`

### 3. Running the Project
**Via Command Line:**
1. Navigate to the project root directory.
2. Compile:
   ```bash
   javac -d out -cp "lib/*;src" src/bank/management/system/*.java
   ```
3. Run:
   ```bash
   java -cp "out;lib/*" bank.management.system.Login
   ```

**Via IDE:**
1. Import the project as a Java project.
2. Add all JARs in the `lib` folder to the **Project Structure > Libraries**.
3. Run the `Login.java` file.

---

## 👤 Default Database Credentials
The current implementation uses:
- **Username**: `root`
- **Password**: `Omkar@2004`
- **Database Name**: `bankSystem`

---

## 👤 Author
**Omkar**  
*MCA First Year Student*  
Project developed with focus on Object-Oriented Programming (OOP) and Secure Database Management.
