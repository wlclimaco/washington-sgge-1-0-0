
echo Cleaning up webapps

del /s /q C:\Users\QATEmployee\.ivy2\cache\ftosta\qat-sample-sysmgmt-interface\jars\*.*
copy C:\QATEclipseWorkSpace\sysmgmt-interface\dist\qat-sample-sysmgmt-interface-0.2.0.jar C:\Users\QATEmployee\.ivy2\cache\ftosta\qat-sample-sysmgmt-interface\jars

del /s /q C:\Users\QATEmployee\.ivy2\cache\ftosta\qat-sample-sysmgmt-implementation\jars\*.*
copy C:\QATEclipseWorkSpace\sysmgmt-implementation\dist\qat-sample-sysmgmt-implementation-0.2.0.jar C:\Users\QATEmployee\.ivy2\cache\ftosta\qat-sample-sysmgmt-implementation\jars

