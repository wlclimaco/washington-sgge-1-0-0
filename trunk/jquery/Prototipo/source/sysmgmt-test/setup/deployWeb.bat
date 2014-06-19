cd C:\Apache\apache-tomcat-7.0.47\bin
echo Cleaning up webapps
del C:\Apache\apache-tomcat-7.0.47\webapps\qat-webdaptive.war
del /s /q C:\Apache\apache-tomcat-7.0.47\temp\*.*
del /s /q C:\Apache\apache-tomcat-7.0.47\logs\*.*

rmdir /s /q C:\Apache\apache-tomcat-7.0.47\work\Catalina

rmdir /s /q C:\Apache\apache-tomcat-7.0.47\webapps\qat-webdaptive

echo
echo
echo Deploying new files
copy C:\QATEclipseWorkSpace\webdaptive-web\dist\qat-webdaptive.war  C:\Apache\apache-tomcat-7.0.47\webapps\

echo
echo
echo Starting tomcat...

