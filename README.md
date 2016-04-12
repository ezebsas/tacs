TP TACS - Be a Good API Citizen [![Travis Build Status](https://travis-ci.org/tferraro/tacs.svg?branch=master)](https://travis-ci.org/tferraro/tacs)
================================

- Running the app
```
1. Clone repository onto local machine
2. Change to application directory
3. run mvn clean install tomcat:run-war
```
- Configuring Proyect in Eclipse Mars
```
1. Install Eclipse Mars with m2 plugging [Maven Plugging].
2. Import Proyect as a Existing Maven Proyect.
3. Check that the Project Facets are configured correctly.
```
- Running Proyect in Eclipse Mars
```
1. Go to Configure Run Configuration
2. Create a Maven Build
3. Configurate base directory as: '${project_loc}'
4. Configurate Goal as: 'tomcat:run-war'
```


The OpenShift `jbossews` cartridge documentation can be found at:

http://openshift.github.io/documentation/oo_cartridge_guide.html#tomcat

