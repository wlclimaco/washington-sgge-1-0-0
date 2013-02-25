echo MAKE SURE YOU HAVE A DIRECTORY %4 or C:\Program Files (x86)\PostgreSQL\9.0\bin
echo off
set PGPASSWORD=%2
IF EXIST %4 cd %4
goto end
cd C:\Program Files (x86)\PostgreSQL\9.0\bin
:end
echo on
dropdb --username %1 lc
createdb --username=%1 --owner=%1 --template=template_postgis --encoding=UTF8 --tablespace=pg_default lc
psql -d lc -U %1 -f %3//table-func.sql
psql -d lc -U %1 -f %3//create-tables.sql
psql -d lc -U %1 -f %3//create-views.sql
psql -d lc -U %1 -f %3//create-functions.sql
psql -d lc -U %1 -f %3//add-triggers.sql
psql -d lc -U %1 -f %3//add-lc-code-table-data.sql
psql -d lc -U %1 -f %3//add-lc-model-number-data.sql
psql -d lc -U %1 -f %3//dashboard-resume-data.sql

pause