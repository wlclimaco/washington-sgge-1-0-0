#!/bin/sh
#
#
if [ $(id -u) != "0" ]; then

	echo -------------------
	echo PLEASE RUN THE SCRIPT WITH SUPER USER RIGHTS
	echo -------------------

	exit 2

fi

ANT_HOME=ant

export ANT_HOME;
echo --------------------------
echo ANT_HOME AND MODIFY PATH
echo --------------------------
echo 
echo $ANT_HOME
echo
envparam=$1
echo $envparam

export PATH=${ANT_HOME}/bin:${PATH}

echo --------------------------
echo CREATE TOMSLC USER ACCOUNT IF NOT PRESENT
echo --------------------------
echo 

if [[ `grep -c tomslc /etc/passwd` -eq 0 ]]
then
   echo
   echo "creating new tomslc user" 
   echo
   useradd tomslc
 
else
   echo
   echo "tomslc user exists no action done"
   echo
fi

echo

echo -n "Enter your dbms userid press, default is slc_slcdb  [ENTER]: "
read user_id
user_id="${user_id:=slc_slcdb}"
echo $user_id
echo -n "Enter your dbms password press [ENTER]: "
read user_pass
echo $user_pass

echo -------------------------------
echo INSTALL TOMCAT 7 and SLC APPLICATION
echo -------------------------------

ant -f install-slc.xml -Duser_id=$user_id -Duser_pass=$user_pass

echo -------------------------------
echo Change owner to tomslc
echo -------------------------------

chown -R tomslc /opt/flexnet-slc 

echo ------------------------
echo SLC INSTALL COMPLETED
echo ------------------------

