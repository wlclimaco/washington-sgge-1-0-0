echo MAKE SURE YOU HAVE A DIRECTORY %4 or C://Program Files//PostgreSQL//9.2//bin
echo off
set PGPASSWORD=%2
IF EXIST %4 cd %4
goto end
cd C://Program Files//PostgreSQL//9.2//bin
:end
echo on
dropdb --username %1 lc
createdb --username=%1 --owner=%1 --encoding=UTF8 --tablespace=pg_default lc
psql -d lc -U %1 -f %3//table-func.sql
psql -d lc -U %1 -f %3//create-tables.sql
psql -d lc -U %1 -f %3//create-views.sql
psql -d lc -U %1 -f %3//create-functions.sql
psql -d lc -U %1 -f %3//add-triggers.sql
psql -d lc -U %1 -f %3//add-lc-code-table-data.sql
psql -d lc -U %1 -f %3//dashboard-resume-data.sql

pause