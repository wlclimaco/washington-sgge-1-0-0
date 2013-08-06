object SDmWms: TSDmWms
  OldCreateOrder = False
  Height = 347
  Width = 449
  object SqlConnWMS: TBrvConnection
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
    AfterConnect = SqlConnWMSAfterConnect
    Left = 46
    Top = 6
  end
  object QcOpeLog: TBrvDataSet
    GetMetadata = False
    DbxCommandType = 'Dbx.SQL'
    MaxBlobSize = -1
    Params = <>
    SQLConnection = SqlConnWMS
    BrShowFieldName = False
    BrQueryCode = 0
    BrDicionario = DmDicion.BrvDicionario
    BrType = VqNormal
    BrUserCode = 0
    Left = 112
    Top = 4
  end
  object DcOpeLog: TBrvProvider
    DataSet = QcOpeLog
    Options = [poAllowCommandText, poUseQuoteChar]
    Left = 140
    Top = 4
  end
  object CcOpeLog: TBrvClientDataSet
    Aggregates = <>
    Params = <>
    ProviderName = 'DcOpeLog'
    BrShowFieldName = False
    BrQueryCode = 120
    BrDicionario = DmDicion.BrvDicionario
    BrType = VqNormal
    BrGravaLog = False
    BrUserCode = 0
    Left = 168
    Top = 4
  end
  object QExecute: TBrvDataSet
    GetMetadata = False
    CommandText = 'select * from S001 where cdusuari is null'
    DbxCommandType = 'Dbx.SQL'
    MaxBlobSize = -1
    Params = <>
    SQLConnection = SqlConnWMS
    BrShowFieldName = False
    BrQueryCode = 0
    BrDicionario = DmDicion.BrvDicionario
    BrType = VqNormal
    BrUserCode = 0
    Left = 48
    Top = 64
  end
end
