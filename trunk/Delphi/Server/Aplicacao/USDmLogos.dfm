object SDmLogos: TSDmLogos
  OldCreateOrder = False
  Left = 488
  Top = 157
  Height = 325
  Width = 329
  object CdsTabela: TClientDataSet
    Aggregates = <>
    Params = <>
    ProviderName = 'DataSetProvider1'
    Left = 208
    Top = 72
  end
  object DataSetProvider1: TDataSetProvider
    DataSet = QpHistor
    Left = 128
    Top = 72
  end
  object QpHistor: TSQLDataSet
    SchemaName = 'VITALSIS'
    GetMetadata = False
    CommandText = 'select * from HISTORICO order by cdhistor'
    DbxCommandType = 'Dbx.SQL'
    MaxBlobSize = -1
    Params = <>
    SQLConnection = SqlConnec
    Left = 56
    Top = 72
  end
  object QpTransac: TSQLDataSet
    SchemaName = 'VITALSIS'
    GetMetadata = False
    CommandText = 'select CdHistor, DsHistor from HISTORICO where CdHistor = 1'
    DbxCommandType = 'Dbx.SQL'
    MaxBlobSize = -1
    Params = <>
    SQLConnection = SqlConnec
    Left = 56
    Top = 136
  end
  object DataSetProvider2: TDataSetProvider
    DataSet = QpTransac
    UpdateMode = upWhereChanged
    Left = 128
    Top = 136
  end
  object CdsTransac: TClientDataSet
    Aggregates = <>
    Params = <>
    ProviderName = 'DataSetProvider2'
    Left = 208
    Top = 136
  end
  object ProDireto: TDataSetProvider
    DataSet = QpHistor
    UpdateMode = upWhereChanged
    Left = 128
    Top = 192
  end
  object SqlConnec: TSQLConnection
    DriverName = 'ORACLE'
    GetDriverFunc = 'getSQLDriverORACLE'
    LibraryName = 'dbxora.dll'
    LoginPrompt = False
    Params.Strings = (
      'drivername=ORACLE'
      'Database=dbvital'
      'User_Name=vitalsis'
      'Password=vital'
      'blobsize=-1'
      'localecode=0000'
      'isolationlevel=ReadCommitted'
      'rowsetsize=20'
      'os authentication=False'
      'multiple transaction=False'
      'trim char=False'
      'decimal separator=.')
    TableScope = [tsTable]
    VendorLib = 'oci.dll'
    Left = 46
    Top = 6
  end
end
