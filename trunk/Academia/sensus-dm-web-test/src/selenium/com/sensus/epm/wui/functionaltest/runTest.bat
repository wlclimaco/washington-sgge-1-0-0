@echo off
set elarahost=http://elara-web/
set stormhost=https://www.22222.storm.davis.sensus.lab/
set toadhost=http://toad-web/
set beasthost=http://beast-web/
set sunfirehost=http://www.18000.sunfire.davis.sensus.lab/
set cablehost=http://cable-web/
set magnetohost=http://www.14444.magneto.davis.sensus.lab/
set iebrowser=*iexplore
set ffbrowser=*firefox
set chromebrowser=*chrome
set bansheehost=http://banshee-web/
set bishophost=http://bishop-web/
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
if /I "%1" EQU "sunfire" (set host="%sunfirehost%")
if /I "%1" EQU "peco" (set host="%pecohost%")
if /I "%1" EQU "acme" (set host="%acmehost%")
if /I "%1" EQU "toad" (set host="%toadhost%")
if /I "%1" EQU "beast" (set host="%beasthost%")
if /I "%1" EQU "banshee" (set host="%bansheehost%")
if /I "%1" EQU "cable" (set host="%cablehost%")
if /I "%1" EQU "magneto" (set host="%magnetohost%")
if /I "%1" EQU "bishop" (set host="%bishophost%")
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
