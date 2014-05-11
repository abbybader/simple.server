simple.server
=============

Basic backend for a "To do" list app

This java/maven project contains everything you need to run a local version of our "To do" list backend.  
It comes prepackaged with a mocked data set that DOES NOT persist from one session to the next; don't be confused if your changes to the data disappear between runs.  This is intended to be used for a front-end coding exercise.

In order to run this server, you need: An installation of JDK 1.6 or later; and an installation of maven.

The code is set up as an Eclipse project, though you may run it however you choose.  For example:
Via command line:  1) Use maven to build the project.  2) Run the server using "java -jar simple.server/target/dist/simple.server.{version}.jar [HTTP_PORT]"

The server will be accessible via http://localhost:{HTTP_PORT} (default is 1500)

API DOCUMENTATION: Provided that you have built the project using maven, you will be able to view the server's API docs at http://localhost:{PORT}/docs

The server is designed to accommodate: Querying for all existing lists; Adding/Deleting/Retrieving/Updating lists; Adding/Deleting/Retrieving/Updating TO DO items within a list.

----

**Note for Java 8 users**

This project uses an [Enunciate](http://enunciate.codehaus.org) plugin to auto-build API docs when the maven builds the project.  Due to a core change in the Java 8 release (apt tool has been removed), the Enunciate plugin will fail.  Java 8 users may either:
- Switch back to Java 7
- Create a Maven profile that uses an older Java version, as explained (here)[https://jira.codehaus.org/browse/ENUNCIATE-701?focusedCommentId=345953&page=com.atlassian.jira.plugin.system.issuetabpanels:comment-tabpanel#comment-345953]
- Disable the Enunciate plugin in the pom.xml file
