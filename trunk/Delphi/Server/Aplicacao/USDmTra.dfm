object SDmTra: TSDmTra
  OldCreateOrder = False
  Height = 344
  Width = 660
  object QcEntMer: TBrvDataSet
    GetMetadata = False
    CommandText = 'select * from S001 where cdusuari is null'
    DbxCommandType = 'Dbx.SQL'
    MaxBlobSize = -1
    Params = <>
    SQLConnection = SqlConnTra
    BrShowFieldName = False
    BrQueryCode = 0
    BrDicionario = DmDicion.BrvDicionario
    BrType = VqNormal
    BrUserCode = 0
    Left = 16
    Top = 56
  end
  object DcEntMer: TBrvProvider
    DataSet = QcEntMer
    Options = [poAllowCommandText, poUseQuoteChar]
    Left = 67
    Top = 56
  end
  object SqlConnTra: TBrvConnection
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
    AfterConnect = SqlConnTraAfterConnect
    Left = 46
    Top = 6
  end
  object QcAtend: TBrvDataSet
    GetMetadata = False
    CommandText = 'select * from S001 where cdusuari is null'
    DbxCommandType = 'Dbx.SQL'
    MaxBlobSize = -1
    Params = <>
    SQLConnection = SqlConnTra
    BrShowFieldName = False
    BrQueryCode = 0
    BrDicionario = DmDicion.BrvDicionario
    BrType = VqNormal
    BrUserCode = 0
    Left = 136
    Top = 56
  end
  object DcAtend: TBrvProvider
    DataSet = QcAtend
    Options = [poAllowCommandText, poUseQuoteChar]
    Left = 187
    Top = 56
  end
  object QcAtenCon: TBrvDataSet
    GetMetadata = False
    CommandText = 'select * from S001 where cdusuari is null'
    DbxCommandType = 'Dbx.SQL'
    MaxBlobSize = -1
    Params = <>
    SQLConnection = SqlConnTra
    BrShowFieldName = False
    BrQueryCode = 0
    BrDicionario = DmDicion.BrvDicionario
    BrType = VqNormal
    BrUserCode = 0
    Left = 136
    Top = 104
  end
  object DcAtenCon: TBrvProvider
    DataSet = QcAtenCon
    Options = [poAllowCommandText, poUseQuoteChar]
    Left = 187
    Top = 104
  end
  object QcOcorrenc: TBrvDataSet
    GetMetadata = False
    CommandText = 'select * from S001 where cdusuari is null'
    DbxCommandType = 'Dbx.SQL'
    MaxBlobSize = -1
    Params = <>
    SQLConnection = SqlConnTra
    BrShowFieldName = False
    BrQueryCode = 0
    BrDicionario = DmDicion.BrvDicionario
    BrType = VqNormal
    BrUserCode = 0
    Left = 136
    Top = 152
  end
  object DcOcorrenc: TBrvProvider
    DataSet = QcOcorrenc
    Options = [poAllowCommandText, poUseQuoteChar]
    Left = 187
    Top = 152
  end
  object QExecute: TBrvDataSet
    GetMetadata = False
    CommandText = 'select * from S001 where cdusuari is null'
    DbxCommandType = 'Dbx.SQL'
    MaxBlobSize = -1
    Params = <>
    SQLConnection = SqlConnTra
    BrShowFieldName = False
    BrQueryCode = 0
    BrDicionario = DmDicion.BrvDicionario
    BrType = VqNormal
    BrUserCode = 0
    Left = 328
    Top = 16
  end
  object QcCarga: TBrvDataSet
    GetMetadata = False
    CommandText = 'select * from S014 where nmtabela is null'
    DbxCommandType = 'Dbx.SQL'
    MaxBlobSize = -1
    Params = <>
    SQLConnection = SqlConnTra
    BrShowFieldName = False
    BrQueryCode = 0
    BrDicionario = DmDicion.BrvDicionario
    BrType = VqNormal
    BrUserCode = 0
    Left = 272
    Top = 66
  end
  object DcCarga: TBrvProvider
    DataSet = QcCarga
    Options = [poAllowCommandText, poUseQuoteChar]
    Left = 301
    Top = 66
  end
  object QpCarga: TBrvClientDataSet
    Aggregates = <>
    Params = <>
    ProviderName = 'DcCarga'
    BrShowFieldName = False
    BrQueryCode = 0
    BrDicionario = DmDicion.BrvDicionario
    BrType = VqNormal
    BrGravaLog = True
    BrFormName = 'SdmSis'
    BrUserCode = 0
    Left = 330
    Top = 66
  end
  object QcCargaEmp: TBrvDataSet
    GetMetadata = False
    CommandText = 'select * from S014 where nmtabela is null'
    DbxCommandType = 'Dbx.SQL'
    MaxBlobSize = -1
    Params = <>
    SQLConnection = SqlConnTra
    BrShowFieldName = False
    BrQueryCode = 0
    BrDicionario = DmDicion.BrvDicionario
    BrType = VqNormal
    BrUserCode = 0
    Left = 272
    Top = 114
  end
  object DcCargaEmp: TBrvProvider
    DataSet = QcCargaEmp
    Options = [poAllowCommandText, poUseQuoteChar]
    Left = 301
    Top = 114
  end
  object QpCargaEmp: TBrvClientDataSet
    Aggregates = <>
    Params = <>
    ProviderName = 'DcCargaEmp'
    BrShowFieldName = False
    BrQueryCode = 0
    BrDicionario = DmDicion.BrvDicionario
    BrType = VqNormal
    BrGravaLog = True
    BrFormName = 'SdmSis'
    BrUserCode = 0
    Left = 330
    Top = 114
  end
  object QCMovCtrc: TBrvDataSet
    GetMetadata = False
    DbxCommandType = 'Dbx.SQL'
    MaxBlobSize = -1
    Params = <>
    SQLConnection = SqlConnTra
    BrShowFieldName = False
    BrQueryCode = 0
    BrDicionario = DmDicion.BrvDicionario
    BrType = VqNormal
    BrUserCode = 0
    Left = 272
    Top = 158
  end
  object DCMovCtrc: TBrvProvider
    DataSet = QCMovCtrc
    Options = [poAllowCommandText, poUseQuoteChar]
    Left = 301
    Top = 158
  end
  object CCMovCtrc: TBrvClientDataSet
    Aggregates = <>
    Params = <>
    ProviderName = 'DCMovCtrc'
    BrShowFieldName = False
    BrQueryCode = 151
    BrDicionario = DmDicion.BrvDicionario
    BrType = VqNormal
    BrGravaLog = True
    BrFormName = 'SdmSis'
    BrUserCode = 0
    Left = 330
    Top = 158
  end
  object QcOcor: TBrvDataSet
    GetMetadata = False
    CommandText = 'select * from S014 where nmtabela is null'
    DbxCommandType = 'Dbx.SQL'
    MaxBlobSize = -1
    Params = <>
    SQLConnection = SqlConnTra
    BrShowFieldName = False
    BrQueryCode = 0
    BrDicionario = DmDicion.BrvDicionario
    BrType = VqNormal
    BrUserCode = 0
    Left = 272
    Top = 210
  end
  object DpOcor: TBrvProvider
    DataSet = QcOcor
    Options = [poAllowCommandText, poUseQuoteChar]
    Left = 301
    Top = 210
  end
  object CcOcor: TBrvClientDataSet
    Aggregates = <>
    Params = <>
    ProviderName = 'DpOcor'
    BrShowFieldName = False
    BrQueryCode = 0
    BrDicionario = DmDicion.BrvDicionario
    BrType = VqNormal
    BrGravaLog = True
    BrFormName = 'SdmSis'
    BrUserCode = 0
    Left = 330
    Top = 210
  end
  object QpCTRC: TBrvDataSet
    GetMetadata = False
    DbxCommandType = 'Dbx.SQL'
    MaxBlobSize = -1
    Params = <>
    SQLConnection = SqlConnTra
    BrShowFieldName = False
    BrQueryCode = 0
    BrDicionario = DmDicion.BrvDicionario
    BrType = VqNormal
    BrUserCode = 0
    Left = 272
    Top = 258
  end
  object DpCTRC: TBrvProvider
    DataSet = QpCTRC
    Options = [poAllowCommandText, poUseQuoteChar]
    Left = 301
    Top = 258
  end
  object CcCTRC: TBrvClientDataSet
    Aggregates = <>
    Params = <>
    ProviderName = 'DpCTRC'
    BrShowFieldName = False
    BrQueryCode = 169
    BrDicionario = DmDicion.BrvDicionario
    BrType = VqNormal
    BrGravaLog = True
    BrFormName = 'SdmSis'
    BrUserCode = 0
    Left = 330
    Top = 258
  end
  object CcT019: TBrvClientDataSet
    Aggregates = <>
    Params = <>
    ProviderName = 'DcT019'
    BrShowFieldName = False
    BrQueryCode = 0
    BrDicionario = DmDicion.BrvDicionario
    BrType = VqNormal
    BrGravaLog = True
    BrUserCode = 0
    Left = 504
    Top = 256
  end
  object DcT019: TBrvProvider
    DataSet = QcT019
    Options = [poAllowCommandText, poUseQuoteChar]
    Left = 464
    Top = 256
  end
  object QcT019: TBrvDataSet
    GetMetadata = False
    CommandText = 'Select * From T019 Where 1=2'
    DbxCommandType = 'Dbx.SQL'
    MaxBlobSize = -1
    Params = <>
    SQLConnection = SqlConnTra
    BrShowFieldName = False
    BrQueryCode = 0
    BrDicionario = DmDicion.BrvDicionario
    BrType = VqNormal
    BrUserCode = 0
    Left = 424
    Top = 256
  end
  object BrvContabil: TBrvContabil
    BrDicionario = DmDicion.BrvDicionario
    SQLConnection = SqlConnTra
    Left = 72
    Top = 224
  end
  object QCAceCarga: TBrvDataSet
    GetMetadata = False
    CommandText = '0'
    DbxCommandType = 'Dbx.SQL'
    MaxBlobSize = -1
    Params = <>
    SQLConnection = SqlConnTra
    BrShowFieldName = False
    BrQueryCode = 0
    BrDicionario = DmDicion.BrvDicionario
    BrType = VqNormal
    BrUserCode = 0
    Left = 424
    Top = 48
  end
  object DCAceCarga: TBrvProvider
    DataSet = QCAceCarga
    Options = [poAllowCommandText, poUseQuoteChar]
    Left = 452
    Top = 48
  end
  object CCAceCarga: TBrvClientDataSet
    Aggregates = <>
    Params = <>
    ProviderName = 'DCAceCarga'
    BrShowFieldName = False
    BrQueryCode = 207
    BrDicionario = DmDicion.BrvDicionario
    BrType = VqNormal
    BrGravaLog = False
    BrUserCode = 0
    Left = 480
    Top = 48
  end
  object QCConCor: TBrvDataSet
    GetMetadata = False
    CommandText = '0'
    DbxCommandType = 'Dbx.SQL'
    MaxBlobSize = -1
    Params = <>
    SQLConnection = SqlConnTra
    BrShowFieldName = False
    BrQueryCode = 0
    BrDicionario = DmDicion.BrvDicionario
    BrType = VqNormal
    BrUserCode = 0
    Left = 424
    Top = 76
  end
  object DCConCor: TBrvProvider
    DataSet = QCConCor
    Options = [poAllowCommandText, poUseQuoteChar]
    Left = 452
    Top = 76
  end
  object CCConCor: TBrvClientDataSet
    Aggregates = <>
    Params = <>
    ProviderName = 'DCConCor'
    BrShowFieldName = False
    BrQueryCode = 208
    BrDicionario = DmDicion.BrvDicionario
    BrType = VqNormal
    BrGravaLog = False
    BrUserCode = 0
    Left = 480
    Top = 76
  end
end
