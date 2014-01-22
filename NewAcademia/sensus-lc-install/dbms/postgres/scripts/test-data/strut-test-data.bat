echo MAKE SURE YOU HAVE A DIRECTORY %4 or C:\Program Files (x86)\PostgreSQL\9.2\bin
echo off
set PGPASSWORD=%2
IF EXIST %4 cd %4
goto end
cd C:\Program Files (x86)\PostgreSQL\9.2\bin
:end
echo on
psql -d lc -U %1 -f %3//add-light-data.sql
Pause