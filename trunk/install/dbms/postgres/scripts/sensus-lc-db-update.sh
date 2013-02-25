echo off
echo "******************************************************************************"
echo "* This batch file sets up all necessary database artifacts for Sensus LC 1.0.1"
echo "******************************************************************************"
echo "Press enter to continue"; read line

./db-setup/lc-update.sh postgres postgres /tmp/dbms/postgres/scripts/db-setup /usr/pgsql-9.0/bin
