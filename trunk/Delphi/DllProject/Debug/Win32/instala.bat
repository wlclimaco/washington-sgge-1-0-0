

if EXIST %windir%\SysWOW64 goto Win64

:Win32
ECHO *** Copiando as DLLs ***
if NOT EXIST %windir%\System32\Project1.dll copy Project1.dll %windir%\System32
ECHO *** Registrando as DLLs ***
regsvr32 %windir%\System32\Project1.dll /s
goto end

:Win64
ECHO *** Copiando as DLLs x64 ***
if NOT EXIST %windir%\SysWOW64\Project1.dll copy Project1.dll %windir%\SysWOW64

ECHO *** Registrando as DLLs x64 ***
regsvr32 %windir%\SysWOW64\Project1.dll /s

goto end

:end

pause
