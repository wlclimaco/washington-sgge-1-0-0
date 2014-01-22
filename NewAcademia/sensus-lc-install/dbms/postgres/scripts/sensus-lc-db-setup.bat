echo off
echo *************************************************************************
echo * This batch file sets up all necessary database artifacts for Sensus LC.
echo * By default, it assumes qat/qat as userid/pwd to log in to Postgres,
echo * C://dbms//postgres//scripts//db-setup as the folder with the scripts and
echo * C://Program Files//PostgreSQL//9.2//bin as the Postgres bin folder.
echo * You can change the default data by editing this file.
echo *************************************************************************
pause

.//db-setup//lc-strut.bat qat qat C://dbms//postgres//scripts//db-setup "C://Program Files//PostgreSQL//9.2//bin"