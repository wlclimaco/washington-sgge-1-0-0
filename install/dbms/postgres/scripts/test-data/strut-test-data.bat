echo MAKE SURE YOU HAVE A DIRECTORY %4 or C:\Program Files (x86)\PostgreSQL\9.0\bin
echo off
set PGPASSWORD=%2
IF EXIST %4 cd %4
goto end
cd C:\Program Files (x86)\PostgreSQL\9.0\bin
:end
echo on
psql -d lc -U %1 -f %3//add-lc-test-data.sql
psql -d lc -U %1 -f %3//add-lc-model-number-data.sql
psql -d lc -U %1 -f %3//insert-property-value.sql
psql -d lc -U %1 -f %3//insert-operational-data.sql
psql -d lc -U %1 -f %3//add-lc-test-data-analytics.sql
psql -d lc -U %1 -f %3//insert-by-lat-lng.sql
psql -d lc -U %1 -f %3//update-smartpoint-rni-id.sql
psql -d lc -U %1 -f %3//portland-data.sql
psql -d lc -U %1 -f %3//insert-light-property-city-value.sql
psql -d lc -U %1 -f %3//insert-light-time-zone-value.sql
psql -d lc -U %1 -f %3//add-lc-test-data-add-schedule-membership.sql
psql -d lc -U %1 -f %3//update-center.sql
psql -d lc -U %1 -f %3//dashboard-resume-data.sql
psql -d lc -U %1 -f %3//drop-temp-functions.sql
Pause
Pause