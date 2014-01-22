@echo off
SET /A ARGS_COUNT=0    
FOR %%A in (%*) DO SET /A ARGS_COUNT+=1    

IF "%ARGS_COUNT%" NEQ "2" (
	echo ERROR: Parameters are required.
	echo usage: runAllElectric host browser
	echo        Where host is either elara, storm, acme or peco.  If neither then parameter will be used as the host.
	echo        Browser can be "ie" for Internet Explore, "ff" for Firefox
	echo        Example: runAllElectric elara ie 

	exit /b 1
)

call runTest.bat %1 %2 SEC-001_login_testsuite.html
call runTest.bat %1 %2 ECO-001_ecomode_testsuite.html
call runTest.bat %1 %2 GRP-001_groups_testsuite.html
call runTest.bat %1 %2 QUI-001_quicksearch_testsuite.html
call runTest.bat %1 %2 slc_analytics_analytics_testsuite.html
call runTest.bat %1 %2 slc_schedules_event_schedules_testsuite.html		
call runTest.bat %1 %2 slc_schedules_offset_schedules_testsuite.html
call runTest.bat %1 %2 SSC-001_savedsearch_testsuite.html
call runTest.bat %1 %2 SYS-001_systemsettings_testsuite.html
call runTest.bat %1 %2 TAG-001_tags_testsuite.html
call runTest.bat %1 %2 USR-001_users_testsuite.html
call runTest.bat %1 %2 REP-001_dashboard_testsuite.html