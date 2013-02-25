echo off
echo *************************************************************************
echo * This batch file loads test data Sensus LC.
echo * By default, it assumes qat/qat as userid/pwd to log in to Postgres,
echo * C://dbms//postgres//scripts//test-data as the folder with the scripts and
echo * C://Program Files//PostgreSQL//9.0//bin as the Postgres bin folder.
echo * You can change the default data by editing this file.
echo *************************************************************************
pause


.//test-data//strut-test-data.bat qat qat C://dbms//postgres//scripts//test-data "C://Program Files//PostgreSQL//9.0//bin"