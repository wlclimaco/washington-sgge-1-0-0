
How to run Selenium test cases from the command line.
1.	Using a windows command prompt
		Note If you are running on IE9 open cmd.exe with Administrador and make sure if protected mode was disabled;
2.  Navigate into either the "unittest" or "functionaltest" folder.
3.  Execute the runTest.bat script with the following parameters:
    runTest host browser suite-filename
	     Where host is either elara, storm, acme or peco.  
		Browser can be "ie" for Internet Explore, "ff" for Firefox
		suite-filename must be a valid filename. Do not prefix with ./ or .\
		Example: runtest elara ie dm_user_settings_testsuite.html
	
If you need to run the test on a different machine from elara, storm, acme or peco
then key in as the host parameter the full URL to the machine.
	Example: https://www.22222.mymachine.davis.sensus.lab/
	Note the full URL must be entered and the URL should NOT contain the application name, /dm or /Dicionario.
	This is just the host name.
	Currently elara points to this URL: http://elara-web/
	and storm points to this URL: https://www.22222.storm.davis.sensus.lab/
	
The test suite will run and when complete a browser window will appear with the results report.
If for some reason the tests are failing or hanging you can press ctrl-c in the command prompt window to terminate the tests.
Note you may have to press ctrl-c several times.

Common problems:
1.	Java not installed: Contact your desktop support desk and ask about getting Java installed so it can be executed from the command-line.
2.	Test suite file error: Make sure you are in the "unittest" or "functionaltest" folder and that the test file name was entered correctly.
	Make sure you don't prefix the filename with "./" or ".\"
3.	Test suite hangs or does not complete:  Press ctrl-c several time to abort the test.  If the problem continues contact support.
4.	When I press ctrl-c it prompts me to "Terminate batch job (Y/N)?":  Answer N so the results file will be launced.
4.	What happened to the results?: The results for each suite are placed into the same folder where the test-suite file resides prefixed with "results." 
	For example: results.dm_user_settings_testsuite.html
5.	Something about jar not found or user-extensions not found: Make sure when you downloaded the tests from SVN you included the WUI folder which contains this file and several other required files.
