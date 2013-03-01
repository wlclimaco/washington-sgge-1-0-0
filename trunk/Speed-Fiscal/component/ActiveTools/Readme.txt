CellSouce Pack
Copyright (c) Berg
www.bergsoftware.net

CellSource Pack is a update for GridView component from Berg.
GridView component can be downloaded from http://www.bergsoftware.net/components/

Included in CellSoucePack:
- TDataCellSource component
- TFileCellSource (development)
- TFtpCellSource (development)

Installation:
1. First check if you have latest version of GridView component
2. Unzip archive into desired folder
3. Run your version of Delphi and choose File -> Open
4. Find and open cellsource_run.dpk file in \packages folder
5. Click Compile, then Install
6. Close package
7. Add path to \source folder in Library

Description:
TDataCellSource:
 Fill associated GridView component with records from DataSet.
 You need to set "Grid" property to desired GridView component and "DataSet" property to desired TDataSet ancestor.
 Then set "Activate" property to "True" to fill grid with records from DataSet.
 If there is no columns in Grid, DataCellSource will automatically add columns to display all fields.
 If you want to disable some column for using records turn off "coPublicUsing" flag in column "Options" property.

www.bergsoftware.net