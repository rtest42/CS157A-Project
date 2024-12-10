# CS157A Project - Movie Rental and Review System

## Overview

The **Movie Rental and Review System** is designed to manage rental processing and return handling of movies. The system contains a MySQL database of movies which will allow for browsing and personalized ratings for each movie. JDBC is used for database connectivity, while Java Servlets connect the front end to back end logic. The front end is built with JSP and CSS, and the server deployed is Apache Tomcat. Eclipse IDE is utilized for managing the project and simplifying the installation process via Maven.

### Requirements
- IDE: Eclipse 2024-12
- Java Version: JDK 21
- Server: Apache Tomcat v11.0
- Database Connection: MySQL Workbench and/or Installer 8.0.40

### Step-by-Step Setup Instructions

#### **Step 1: Clone the Repository**

```
git clone https://github.com/rtest42/CS157A-Project
```

#### **Step 2: Setup MySQL Database**

If you do not have MySQL Workbench installed, download it from their official website [here](https://dev.mysql.com/downloads/workbench/).

Ensure a successful connection with the hostname `127.0.0.1`, port `3306`, username `root`, and password `password`.  [MySQL Installer](https://dev.mysql.com/downloads/installer/) may be required to set up a connection. Select `MySQL Server 8.0.40` during the installation process and set the password to `password`.

#### **Step 3: Configure the Project**

For simplification, run [Eclipse](https://www.eclipse.org/downloads/packages/). Import a project as an existing Maven Project. Click on `File` → `Import...` → `Existing Maven Projects` and select the root directory as the project folder that was downloaded. Ensure a valid `pom.xml` file shows up to manage dependencies.

#### **Step 4: Initialize Databases**

Run `InitializeDatabase.java` and `PopulateDatabase.java` located in the package `edu.sjsu.cs157a.util` as Java applications in succession. The console should print out successful messages.

#### **Step 5: Run the Application**

Right click the root directory (`jdbc-webapp`) and select `Run As` → `Run on Server`. If Apache Tomcat 11.0 is not installed, manually define a new server and search for `Tomcat v11.0`. Define a directory to install the server. The application should run shortly after. For subsequent runs, choose an existing server and select `Tomcat v11.0`.

### Configurations

Database connections are defined in `JDBCUtil.java` in package `edu.sjsu.cs157a.util`.
Edit the URL, username, and password as necessary.
```java
package edu.sjsu.cs157a.util;  

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class JDBCUtil {
	// Edit the URL, username, and password here
	private static final String URL = "jdbc:mysql://localhost:3306/jdbc-webapp";
	private static final String USERNAME = "root";
	private static final String PASSWORD = "password";

	public static Connection getConnection() throws ClassNotFoundException, SQLException {
		Class.forName("com.mysql.cj.jdbc.Driver");
		return DriverManager.getConnection(URL, USERNAME, PASSWORD);
	}
}
```
The CSV file, `movies.csv`, in the `resources` folder, contains fifteen movies to populate the table `Movies` with. The first row, which defines the attributes, are used for annotations and are ignored. Data may be edited to define different movies to put in the database. `PopulateDatabase.java` batches statements and inserts rows from the CSV file.

The Java version specification is defined in `pom.xml`:
```
<maven.compiler.source>21</maven.compiler.source>
<maven.compiler.target>21</maven.compiler.target>
```
An older Java version may not work. Maven dependencies include the latest versions along with Tomcat, which requires a minimum specific Java version.