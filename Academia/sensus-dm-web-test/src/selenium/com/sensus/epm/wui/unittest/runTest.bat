@echo off
set elarahost=http://elara-web/
set stormhost=https://www.22222.storm.davis.sensus.lab/
set pecohost=http://mallet-app.davis.sensus.lab:8080/
set acmehost=http://acme-app.davis.sensus.lab:8080
set iebrowser=*iexplore
set ffbrowser=*firefox
set chromebrowser=*chrome

SET /A ARGS_COUNT=0    
FOR %%A in (%*) DO SET /A ARGS_COUNT+=1    

IF "%ARGS_COUNT%" NEQ "3" (
	echo ERROR: Parameters are required.
	echo usage: runTest host browser suite-filename
	echo        Where host is either elara, storm, acme or peco.  If neither then parameter will be used as the host.
	echo        Browser can be "ie" for Internet Explore, "ff" for Firefox
	echo        suite-filename must be a valid filename. Do not prefix with ./ or .\
	echo        Example: runtest elara ie dm_user_settings_testsuite.html

	exit /b 1
)

set host=
if /I "%1" EQU "elara" (set host="%elarahost%")
if /I "%1" EQU "storm" (set host="%stormhost%")
if /I "%1" EQU "peco" (set host="%pecohost%")
if /I "%1" EQU "acme" (set host="%acmehost%")
if not defined host (set host="%1")

set browser=
if /I "%2" EQU "ie" (set browser="%iebrowser%")
if /I "%2" EQU "ff" (set browser="%ffbrowser%")
if not defined browser (
	echo ERROR: Invalid browser parameter[%2%], must be either "ie" or "ff"
	exit /b 1
)

if not exist %3 (
	echo ERROR: Test suite file[%3] does not exist.
	exit /b 1
)

set resultsfile=results.%3
set testsuitefile=%CD%\%3

echo.
echo Running tests, %3 on host:%host%, results file will be %resultsfile%
echo.
java -jar ../selenium-server-standalone-2.25.0.jar -multiwindow -userExtensions ../user-extensions.js -htmlSuite "%browser%" "%host%" "%testsuitefile%" "%resultsfile%"

if ERRORLEVEL 1 (
	echo.
	echo Some tests failed :(
) ELSE (
	echo.
	echo All tests passed !!!
)

if exist %resultsfile% (
	echo Launching results file. %resultsfile%
	start %resultsfile%
 )
