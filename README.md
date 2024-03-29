Stephen Key

Chad Marshall

## Web-based Search KWIC System

**Process Architecture (Division of Work)**

![Screenshot](https://github.com/LordFreezer/KWIC-Web-App-With-MySQL-On-Tomcat/blob/ASSETS/Untitled.png)

In terms of how work was divided between teammates, it was mostly a matter of which tasks required the most attention at the time and who was first available to start working on them. In terms of design process and communication, teammates immediately worked on refactoring the previously designed Shared Data architecture to meet the specified requirements. Teammates would send written updates to each other before making changes that would affect other components, and if an in-depth explanation over any topic was required, teammates would hold a meeting whenever both were available.

**Requirement Specification (Use Case and Sequence Diagrams):**

Use case diagram

![Screenshot](https://github.com/LordFreezer/KWIC-Web-App-With-MySQL-On-Tomcat/blob/ASSETS/Untitled1.png)

A system admin has permission to write to the system&#39;s data to add lines. An outside client may only search the current data set for certain keywords.

Sequence Diagrams

![Screenshot](https://github.com/LordFreezer/KWIC-Web-App-With-MySQL-On-Tomcat/blob/ASSETS/Untitled2.png)

![Screenshot](https://github.com/LordFreezer/KWIC-Web-App-With-MySQL-On-Tomcat/blob/ASSETS/Untitled3.png)

The system&#39;s use as a web service makes it portable, easily understandable, and user friendly.

**Architecture Specification (Component Diagram):**

![Screenshot](https://github.com/LordFreezer/KWIC-Web-App-With-MySQL-On-Tomcat/blob/ASSETS/Untitled4.png)

The system&#39;s &quot;Circulate Input&quot; function uses the Shared Data Architectural style. The user interface takes in a String input and passes it to the Input component which stores the input as a LineStorage Object. This LineStorage Object is read by a Circular Shifter which creates a list of virtual shifted lines. These lines are read by an Alphabetizer which virtually sorts them in a specific alphabetical order (a\&lt;A\&lt;b\&lt;B\&lt;…\&lt;y\&lt;Y\&lt;z\&lt;Z). These lines are stored in the database.

The system&#39;s &quot;Search Keywords&quot; function is the main function of the MicroMiner component. The user interface takes in a String input of keywords and passes it to the MicroMiner. The Microminer searches the stored lines for these keywords and returns the lines that contain all the provided keywords. These lines are displayed to the user.

**Advantages:**

- The system is scalable. Since the system runs as a web service, multiple users can easily access the stored data.
- The system&#39;s information is stable. Since a component implementing ILineSet hides its data from other components, only sharing it through provided methods, it is less likely that a certain operation will cause an unwanted change in the systems data.

**Disadvantages:**

- The system is complex. Several components making multiple character requests down a line makes it difficult to understand exactly how the end result is achieved.

**Design Specification (Class Diagram):**

![Screenshot](https://github.com/LordFreezer/KWIC-Web-App-With-MySQL-On-Tomcat/blob/ASSETS/Untitled5.png)

# **User Manual**

**The Environment**

The system was coded through Eclipse and is designed to run as an Eclipse project. However, the projects internal classes can be implemented on any engine that accepts Java. To run the project, a few things are needed. First, the IDE must be able to interface between webpages, servers, and databases on those servers. To accomplish this, an installation of Eclipse for Web Developers is not required but recommended because it has most of the settings and component available out of the box. Instead of Eclipse for Web Developers, Eclipse for Java Developers was used. Eclipse was upgraded to Oxygen version mainly to gain access to Tomcat server functionality. Many packages from the Helios repository were added to provide many other Web Developer functionalities. From the Eclipse Marketplace, Eclipse JST Server Adapters and Web Developer Tools were also added. Finally a mysql-connector jar file was needed to bridge the gap between our application and the MySQL server.

**The Architecture**

To achieve Three-Tiered architecture, we used Apache Tomcat version 8.5 as our Application Server that locally hosted our webpages in our frontend and executed our Java code in the backend. For our database, we chose to run a MySQL server and used MySQL Workbench to interface with it.

**The Search Side of Things**

When the user navigates to the webpage, they are greeted with empty boxes and a prompt to search.

![Screenshot](https://github.com/LordFreezer/KWIC-Web-App-With-MySQL-On-Tomcat/blob/ASSETS/Untitled6.png)

The database has been prepopulated (by using our application) with test data provided by the instructor. Here is what the table currently looks like. We will discuss the flow of data and how we populated the database when we reach the database section.

![Screenshot](https://github.com/LordFreezer/KWIC-Web-App-With-MySQL-On-Tomcat/blob/ASSETS/Untitled7.png)

By entering keywords and pressing &quot;Submit&quot;, the keywords are cross-referenced with alphabetically sorted circularly shifted set of lines to find a match. The indices of the data are noted and used to map shifted-alphabetized descriptions to the original description and url pairs. The Microminer performs this cross-referencing and builds the roadmap back to the original lines.

![Screenshot](https://github.com/LordFreezer/KWIC-Web-App-With-MySQL-On-Tomcat/blob/ASSETS/Untitled8.png)

The &quot;Submit&quot; sends a request to the same page with the parameters grabbed by from the search textbox and begins the process of shifting-alphabetizing. If the user enters a keyword that would produce no results, the program can check for that by looking at the size of the index list. Hence if the index list is empty, then no results were found.

![Screenshot](https://github.com/LordFreezer/KWIC-Web-App-With-MySQL-On-Tomcat/blob/ASSETS/Untitled9.png)

The &quot;Clear&quot; button reloads the webpage and wipes the parameters from the url. Naturally at the end of the search process, several lists are cleared of their data so that subsequent searches can be done.

**The Admin Side of Things**

When a user goes to the admin page, they are greeted to a textbox prompting them to enter description and url pairs.

![Screenshot](https://github.com/LordFreezer/KWIC-Web-App-With-MySQL-On-Tomcat/blob/ASSETS/Untitled10.png)

When the user enters input in the form of: DESCRIPTION URL and presses &quot;Submit&quot;, the textbox text is parsed from a single large string to DLine objects for every newline in the large string. Once parsing is complete a POST request is sent to the MySQL database for later use in the Search Side of Things. Lets show data actually being stored.

![Screenshot](https://github.com/LordFreezer/KWIC-Web-App-With-MySQL-On-Tomcat/blob/ASSETS/Untitled11.png)

Next, we can hit submit and check the response!

![Screenshot](https://github.com/LordFreezer/KWIC-Web-App-With-MySQL-On-Tomcat/blob/ASSETS/Untitled12.png)

As you can see, Update.java was used to post the data to the MySQL database. Lets take a look at the table!

![Screenshot](https://github.com/LordFreezer/KWIC-Web-App-With-MySQL-On-Tomcat/blob/ASSETS/Untitled13.png)

The database has been updated! To test the new description and url pairs, lets enter a few searches back on the search page.

![Screenshot](https://github.com/LordFreezer/KWIC-Web-App-With-MySQL-On-Tomcat/blob/ASSETS/Untitled14.png)

And another…

![Screenshot](https://github.com/LordFreezer/KWIC-Web-App-With-MySQL-On-Tomcat/blob/ASSETS/Untitled15.png)

Finally, we can clear the page as we always have done before a new search.

![Screenshot](https://github.com/LordFreezer/KWIC-Web-App-With-MySQL-On-Tomcat/blob/ASSETS/Untitled16.png)
