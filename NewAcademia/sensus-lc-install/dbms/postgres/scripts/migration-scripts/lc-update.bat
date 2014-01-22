echo MAKE SURE YOU HAVE A DIRECTORY %4 or C://Program Files//PostgreSQL//9.2//bin
echo off
set PGPASSWORD=%2
IF EXIST %4 cd %4
goto end
cd C://Program Files//PostgreSQL//9.2//bin
:end
echo on
psql -d lc -U %1 -f %3//01-update-tables-1.1-to-1.2.sql
psql -d lc -U %1 -f %3//02-create-functions-1.1-to-1.2.sql
psql -d lc -U %1 -f %3//03-update-functions-1.1-to-1.2.sql
psql -d lc -U %1 -f %3//04-update-data-1.1-to-1.2.sql
psql -d lc -U %1 -f %3//05-create-views-1.1-to-1.2.sql
psql -d lc -U %1 -f %3//06-update-views-1.1-to-1.2.sql
psql -d lc -U %1 -f %3//07-update-triggers-1.1-to-1.2.sql
psql -d lc -U %1 -f %3//08-dashboard-resume-data-1.1-to-1.2.sql
psql -d lc -U %1 -f %3//09-data-migration-1.1-to-1.2.sql
psql -d lc -U %1 -f %3//10-remove-views-triggers-procedures-1.2_old-to-1.2_new.sql
psql -d lc -U %1 -f %3//11-update-tables-1.2_old-to-1.2_new.sql
psql -d lc -U %1 -f %3//12-create-views-1.2_old-to-1.2_new.sql
psql -d lc -U %1 -f %3//13-create-functions-1.2_old-to-1.2_new.sql
psql -d lc -U %1 -f %3//14-data-migration-1.2_old-to-1.2_new.sql
psql -d lc -U %1 -f %3//15-data-migration-1.2_old-to-1.2_new.sql
psql -d lc -U %1 -f %3//16-add-triggers-1.2_old-to-1.2_new.sql
psql -d lc -U %1 -f %3//17-remove-obsolete-data-1.2_old-to-1.2_new.sql
psql -d lc -U %1 -f %3//18-add-new-data-1.2_old-to-1.2_new.sql
Pause