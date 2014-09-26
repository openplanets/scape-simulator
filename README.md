# The SCAPE Simulation environment 

The SCAPE simulation environment is divided into two independent projects: 

* simulation engine 
* eclipse plugin

## simulation engine

simulation engine provides a simple, generic discrete event simulation engine 

### Requirements 
* Maven 3 

To create a package withouth dependencies run

 ```shell
mvn clean install 
 ```

To create a package with all the dependencies included run

 ```shell
mvn assembly:assembly 
 ```

## eclipse plugin 

Eclipse plugin project contains an implementation of the domain specific language for 
repository simulation 

### Requirements 
* Eclipse IDE for Java and DSL Developers (tested on Eclipse Kepler https://www.eclipse.org/downloads/packages/eclipse-ide-java-and-dsl-developers/keplersr2)
* XText (http://www.eclipse.org/Xtext/) it is part of the Eclipse IDE for Java and DSL Developers (tested with version 2.4)
* Maven 3 


### Strcuture of the project

* plugins
	* eu.scape_project.pw.simulator - project containing DSL defenition
	* eu.scape_project.pw.simulator.ui - project containing text editor and charts 
* features
* releng
	* eu.scape_project.pw.simulator.updatesite - Maven generates an update site in this folder 

