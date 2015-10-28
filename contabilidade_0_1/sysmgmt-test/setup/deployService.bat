cd C:\Apache\apache-tomcat-7.0.47\bin
echo Cleaning up webapps
del C:\Apache\apache-tomcat-7.0.47\webapps\qat-sysmgmt-sample.war
del /s /q C:\Apache\apache-tomcat-7.0.47\temp\*.*
del /s /q C:\Apache\apache-tomcat-7.0.47\logs\*.*

rmdir /s /q C:\Apache\apache-tomcat-7.0.47\work\Catalina

rmdir /s /q C:\Apache\apache-tomcat-7.0.47\webapps\qat-sysmgmt-sample

echo
echo
echo Deploying new files
copy C:\QATEclipseWorkSpace\sysmgmt-web\dist\qat-sysmgmt-sample.war  C:\Apache\apache-tomcat-7.0.47\webapps\

echo
echo

