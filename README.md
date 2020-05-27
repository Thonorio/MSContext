#Context Programing Linux Server

## Installation guide
Before you start, you should make sure the following requirements are met: 
* Postgres Installed 
* NodeJs Installed
* JDK 11 Install


### Database
Once that is done you should execute the script `databaseInit.sh` present at the root of this project. This will create a user, a database, and give privileges to the user.

The command is
````
sudo sh databaseInit.sh 
````

### WebServer
You can now execute this project

### Ble Listener
To have the ble listener functionality you should execute the file `bluetoothListener.js`.
This may require the installation of a couple of libs. If this is the case the following command should do the trick
````
sudo npm install pg
sudo npm install noble
````

Once that is done you can start the script with
````
sudo nodejs bluetoothListener.js
```` 





  

