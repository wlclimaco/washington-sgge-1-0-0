echo MAKE SURE YOU HAVE A DIRECTORY %4 or C:\Program Files (x86)\PostgreSQL\9.0\bin
echo off
set PGPASSWORD=%2
IF EXIST %4 cd %4
goto end
cd C:\Program Files (x86)\PostgreSQL\9.0\bin
:end
echo on
psql -d lc -U %1 -f %3//update-functions.sql
psql -d lc -U %1 -f %3//update-data.sql	
psql -d lc -U %1 -f %3//update-views.sql
psql -d lc -U %1 -f %3//update-tables.sql
psql -d lc -U %1 -f %3//update-triggers.sql
psql -d lc -U %1 -f %3//dashboard-resume-data.sql

Pause