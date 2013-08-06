object SDmRW: TSDmRW
  OldCreateOrder = False
  Height = 304
  Width = 410
  object SqlConnRW: TBrvConnection
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
    AfterConnect = SqlConnRWAfterConnect
    Left = 46
    Top = 6
  end
  object QpDados: TSQLDataSet
    GetMetadata = False
    DbxCommandType = 'Dbx.SQL'
    MaxBlobSize = -1
    Params = <>
    SQLConnection = SqlConnRW
    Left = 48
    Top = 56
  end
  object DpDados: TDataSetProvider
    DataSet = QpDados
    Options = [poAllowCommandText, poUseQuoteChar]
    Left = 128
    Top = 56
  end
  object CpDados: TClientDataSet
    Aggregates = <>
    Params = <>
    ProviderName = 'DpDados'
    Left = 192
    Top = 56
  end
  object CpUsuario: TBrvClientDataSet
    Aggregates = <>
    Params = <>
    ProviderName = 'DpUsuario'
    BrShowFieldName = False
    BrQueryCode = 0
    BrType = VqNormal
    BrGravaLog = True
    BrUserCode = 0
    Left = 192
    Top = 112
  end
  object QpUsuario: TBrvDataSet
    GetMetadata = False
    DbxCommandType = 'Dbx.SQL'
    MaxBlobSize = -1
    Params = <>
    SQLConnection = SqlConnRW
    BrShowFieldName = False
    BrQueryCode = 0
    BrType = VqNormal
    BrUserCode = 0
    Left = 48
    Top = 112
  end
  object DpUsuario: TDataSetProvider
    DataSet = QpUsuario
    Left = 128
    Top = 112
  end
  object BrvOracle: TBrvOracle
    Left = 312
    Top = 56
  end
  object CdsDirSer: TClientDataSet
    Aggregates = <>
    Params = <>
    Left = 192
    Top = 192
  end
  object CdsDirCli: TClientDataSet
    Aggregates = <>
    Params = <>
    Left = 256
    Top = 192
  end
  object QExecute: TBrvDataSet
    GetMetadata = False
    CommandText = 'select * from S001 where cdusuari is null'
    DbxCommandType = 'Dbx.SQL'
    MaxBlobSize = -1
    Params = <>
    SQLConnection = SqlConnRW
    BrShowFieldName = False
    BrQueryCode = 0
    BrDicionario = DmDicion.BrvDicionario
    BrType = VqNormal
    BrUserCode = 0
    Left = 336
    Top = 144
  end
end
