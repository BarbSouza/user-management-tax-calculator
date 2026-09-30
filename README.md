# 🧾 User Management & Tax Calculator

A console-based Java application where users can register, log in, estimate their Irish income tax, USC and PRSI, and keep a history of their calculations in a MySQL database, with a separate admin role for managing users. Built as a group project for the **Object Oriented Constructs** module (Year 2, CCT College Dublin), integrated with the Databases module.

## Features

**Regular users**
- Sign up and log in
- View and edit their profile (username, password, name, surname; names are auto-capitalised)
- Estimate income tax, USC and PRSI from gross income and tax credits, with each result saved to the database
- View their full calculation history

**Admins**
- Edit their own profile
- View all registered users
- Remove a user (and their tax records)
- Review the tax records in the system

**On first run**, the app creates the database, both tables and a default admin account automatically.

## Tech

Java · JDBC · MySQL · Object-oriented design (inheritance, encapsulation, separate classes per responsibility)

| Class | Responsibility |
|---|---|
| `Main` | Sets up the database and starts the main menu |
| `MainMenu`, `UserMenu`, `AdminMenu` | Console menus for each role |
| `DatabaseConnection` | Connection settings shared by the database classes |
| `DatabaseSetup` | Creates the database, tables and default admin |
| `DatabaseReaderUser`, `DatabaseReaderAdmin`, `DatabaseWriter` | Read and write operations (users and tax records) |
| `User`, `TaxCalculation` | Data models |
| `TaxCalculator` | Tax calculation logic |
| `DataTypeManipulation` | Helper for formatting names |

**Database schema:** a `user` table (with an `admin`/`regular` role) and a `tax_calculations` table linked to it by a foreign key on `userId`.

## How to run

**You need:** a JDK, a MySQL server on `localhost:3306`, and [MySQL Connector/J](https://dev.mysql.com/downloads/connector/j/) (the `.jar` file).

1. Create a MySQL user matching the settings in `DatabaseConnection.java` (username and password `ooc2023`), or change those settings to your own MySQL login.
2. Put the Connector/J `.jar` in the project folder, then compile and run (Windows):
   ```bash
   javac -cp mysql-connector-j.jar *.java
   java -cp ".;mysql-connector-j.jar" Main
   ```
   On macOS/Linux, use `:` instead of `;` in the second command.
3. Log in as the default admin or sign up as a new user.

## Notes and known limitations

This is a Year 2 project, published as it was submitted to show my progress. Things I'd do differently now:

- **SQL injection:** the login, sign-up and profile-update queries build SQL by inserting user input directly into the query string, so they are vulnerable to SQL injection (for example, bypassing the login). Other queries in the project already use `PreparedStatement`, which is the fix.
- **Passwords are stored in plain text.** They should be hashed (e.g. with bcrypt).
- **Hard-coded credentials:** the database login and a default admin account are written into the code. They should come from configuration and be changed on first use.
- **Simplified tax rates:** the calculation uses flat rates (20% income tax, 1.06% USC, 1.9% PRSI) rather than Ireland's real tax bands, so results are estimates.
- Lecturer feedback suggested stronger input validation, more detailed exception handling and fewer repeated database queries.

## Team

Group project by Chrystian Dybas, Bárbara Candido de Souza, Heloísa Eugênio Silva and Matheus.
