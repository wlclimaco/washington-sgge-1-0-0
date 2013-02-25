echo off
echo "*************************************************************************"
echo "* This batch file loads test data Sensus LC."
echo "* By default, it assumes qat/qat as userid/pwd to log in to Postgres,"
echo "* /dbms/postgres/scripts/test-data as the folder with the scripts and"
echo "* /PostgreSQL/9.0/bin as the Postgres bin folder."
echo "* You can change the default data by editing this file."
echo "*************************************************************************"
echo "Press enter to continue"; read line

./test-data/strut-test-data.sh postgres postgres /tmp/dbms/postgres/scripts/test-data /usr/pgsql-9.0/bin
