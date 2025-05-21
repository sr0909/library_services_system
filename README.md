# 📚 APU Library Service System

A Java desktop application designed for library staff to manage client registrations, book inventory, borrowing services, fine payments, and personal account management. Developed as the assignment of JP Java Programming course at Asia Pacific University of Technology & Innovation.

---

## 🔧 Technologies Used

- **Java SE** (Swing for GUI)
- **File-based Storage** (Text file input/output)
- **Java Object-Oriented Programming (OOP)** concepts:
  - Inheritance
  - Polymorphism (Overloading and Overriding)
  - Encapsulation
  - Abstract Classes
  - Event and Exception Handling

---

## 👤 User Roles

- **Head Librarian**: Full access, including registering other librarians
- **Librarian**: Restricted access (cannot register other librarians)

---

## ✨ Features

### 🔐 Authentication
- Login system with validation
- Role-based access
- Password change with old password verification

### 🧾 Registration
- Register new librarians (Head librarian only)
- Register new clients (students and staff)
- Input validations for name, gender, email, intake code, phone

### 👥 Manage Clients
- Edit client details (email, phone, role, intake code)
- View borrowing history of each client

### 📚 Manage Books
- Add, edit, delete books
- View borrowing status of all books
- Validations for name, author, date, and publisher

### 🔄 Borrowing Services
- Borrow a book (requires registered client)
- Renew books (only once, extends 7 days)
- Return books

### 💰 Fine Management
- Calculate and accept fine payments
- Validate amount paid and provide change

### 👤 My Account
- Edit profile (name, email, intake code, phone)
- Change password with validation

### 🚪 Exit & Logout
- Log out securely
- Exit system with confirmation

---

## 💡 Assumptions

- A client can **renew a book once only**, for 7 days
- Only **librarians can access the system**
- Clients must visit the counter to borrow, renew, or return books
- Clients and librarians **cannot be deleted via the system**
- Fine payment details are stored and viewable only in text files
