#!/bin/sh

echo "*************************************************************************"
echo "* This batch file sets up all necessary database artifacts for Sensus LC."
echo "* By default, the following parameters are used"
echo "-------------------------------------------------------------------------"
echo "* userid/password         - postgres, postgres"
echo "* DB setup scripts folder - /tmp/dbms/postgres/scripts/db-setup"
echo "* Postgres binaries       - /usr/pgsql-9.1/bin or /usr/pgsql-9.0/bin"
echo "-------------------------------------------------------------------------"
echo "* You can change the default data by editing this file."
echo "*************************************************************************"
echo "Press enter to continue"; read line

if [ -d /usr/pgsql-9.1/bin ] ; then
  PGDIR=/usr/pgsql-9.1/bin
elif [ -d /usr/pgsql-9.0/bin ] ; then
  PGDIR=/usr/pgsql-9.0/bin
else
  echo "Unable to find the postgres install directory"
  exit 1
fi

echo "Using postgres binary directory ${PGDIR}"
./db-setup/lc-strut.sh postgres postgres /tmp/dbms/postgres/scripts/db-setup ${PGDIR}
