object DmAdm: TDmAdm
  OldCreateOrder = False
  Height = 601
  Width = 704
  object CcF013: TBrvClientDataSet
    Aggregates = <>
    Params = <>
    ProviderName = 'DcF013'
    BrShowFieldName = False
    BrQueryCode = 0
    BrDicionario = DmDicion.BrvDicionario
    BrType = VqNormal
    BrGravaLog = True
    BrUserCode = 0
    Left = 208
    Top = 16
  end
  object DcF013: TBrvProvider
    DataSet = QcF013
    Left = 176
    Top = 16
  end
  object QcF013: TBrvDataSet
    GetMetadata = False
    CommandText = 'SELECT * FROM F013 WHERE 1=2'
    DbxCommandType = 'Dbx.SQL'
    MaxBlobSize = -1
    Params = <>
    SQLConnection = SqlConnAdm
    BrShowFieldName = False
    BrQueryCode = 0
    BrDicionario = DmDicion.BrvDicionario
    BrType = VqNormal
    BrUserCode = 0
    Left = 144
    Top = 16
  end
  object SqlConnAdm: TBrvConnection
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
    AfterConnect = SqlConnAdmAfterConnect
    Left = 40
    Top = 16
  end
  object QpF013: TBrvClientDataSet
    Aggregates = <>
    Params = <>
    BrShowFieldName = False
    BrQueryCode = 0
    BrDicionario = DmDicion.BrvDicionario
    BrType = VqNormal
    BrGravaLog = True
    BrUserCode = 0
    Left = 96
    Top = 16
  end
  object QpF014: TBrvClientDataSet
    Aggregates = <>
    Params = <>
    BrShowFieldName = False
    BrQueryCode = 0
    BrDicionario = DmDicion.BrvDicionario
    BrType = VqNormal
    BrGravaLog = True
    BrUserCode = 0
    Left = 96
    Top = 64
  end
  object QcF014: TBrvDataSet
    GetMetadata = False
    CommandText = 'SELECT * FROM F014 WHERE 1=2'
    DbxCommandType = 'Dbx.SQL'
    MaxBlobSize = -1
    Params = <>
    SQLConnection = SqlConnAdm
    BrShowFieldName = False
    BrQueryCode = 0
    BrDicionario = DmDicion.BrvDicionario
    BrType = VqNormal
    BrUserCode = 0
    Left = 144
    Top = 64
  end
  object CcF014: TBrvClientDataSet
    Aggregates = <>
    Params = <>
    ProviderName = 'DcF014'
    BrShowFieldName = False
    BrQueryCode = 0
    BrDicionario = DmDicion.BrvDicionario
    BrType = VqNormal
    BrGravaLog = True
    BrUserCode = 0
    Left = 208
    Top = 64
  end
  object DcF014: TBrvProvider
    DataSet = QcF014
    Left = 176
    Top = 64
  end
  object QpF015: TBrvClientDataSet
    Aggregates = <>
    Params = <>
    BrShowFieldName = False
    BrQueryCode = 0
    BrDicionario = DmDicion.BrvDicionario
    BrType = VqNormal
    BrGravaLog = True
    BrUserCode = 0
    Left = 96
    Top = 120
  end
  object CcF015: TBrvClientDataSet
    Aggregates = <>
    Params = <>
    ProviderName = 'DcF015'
    BrShowFieldName = False
    BrQueryCode = 0
    BrDicionario = DmDicion.BrvDicionario
    BrType = VqNormal
    BrGravaLog = True
    BrUserCode = 0
    Left = 208
    Top = 120
  end
  object QcF015: TBrvDataSet
    GetMetadata = False
    CommandText = 'SELECT * FROM F015 WHERE 1=2'
    DbxCommandType = 'Dbx.SQL'
    MaxBlobSize = -1
    Params = <>
    SQLConnection = SqlConnAdm
    BrShowFieldName = False
    BrQueryCode = 0
    BrDicionario = DmDicion.BrvDicionario
    BrType = VqNormal
    BrUserCode = 0
    Left = 144
    Top = 120
  end
  object DcF015: TBrvProvider
    DataSet = QcF015
    Left = 176
    Top = 120
  end
  object QcF013Aut: TBrvDataSet
    GetMetadata = False
    DbxCommandType = 'Dbx.SQL'
    MaxBlobSize = -1
    Params = <>
    SQLConnection = SqlConnAdm
    BrShowFieldName = False
    BrQueryCode = 0
    BrDicionario = DmDicion.BrvDicionario
    BrType = VqNormal
    BrUserCode = 0
    Left = 144
    Top = 176
  end
  object CcF013Aut: TBrvClientDataSet
    Aggregates = <>
    Params = <>
    ProviderName = 'DcF013Aut'
    BrShowFieldName = False
    BrQueryCode = 0
    BrDicionario = DmDicion.BrvDicionario
    BrType = VqNormal
    BrGravaLog = True
    BrUserCode = 0
    Left = 208
    Top = 176
  end
  object DcF013Aut: TBrvProvider
    DataSet = QcF013Aut
    Options = [poAllowCommandText, poUseQuoteChar]
    Left = 176
    Top = 176
  end
  object CcF013Lote: TBrvClientDataSet
    Aggregates = <>
    Params = <>
    ProviderName = 'DcF013Lote'
    BrShowFieldName = False
    BrQueryCode = 0
    BrDicionario = DmDicion.BrvDicionario
    BrType = VqNormal
    BrGravaLog = True
    BrUserCode = 0
    Left = 208
    Top = 232
  end
  object QcF013Lote: TBrvDataSet
    GetMetadata = False
    DbxCommandType = 'Dbx.SQL'
    MaxBlobSize = -1
    Params = <>
    SQLConnection = SqlConnAdm
    BrShowFieldName = False
    BrQueryCode = 0
    BrDicionario = DmDicion.BrvDicionario
    BrType = VqNormal
    BrUserCode = 0
    Left = 144
    Top = 232
  end
  object DcF013Lote: TBrvProvider
    DataSet = QcF013Lote
    Options = [poAllowCommandText, poUseQuoteChar]
    Left = 176
    Top = 232
  end
  object QcF012: TBrvDataSet
    GetMetadata = False
    CommandText = 'Select F012.* From F012 Where 1=2'
    DbxCommandType = 'Dbx.SQL'
    MaxBlobSize = -1
    Params = <>
    SQLConnection = SqlConnAdm
    BrShowFieldName = False
    BrQueryCode = 0
    BrDicionario = DmDicion.BrvDicionario
    BrType = VqNormal
    BrUserCode = 0
    Left = 144
    Top = 288
  end
  object CcF012: TBrvClientDataSet
    Aggregates = <>
    Params = <>
    ProviderName = 'DcF012'
    BrShowFieldName = False
    BrQueryCode = 0
    BrDicionario = DmDicion.BrvDicionario
    BrType = VqNormal
    BrGravaLog = True
    BrUserCode = 0
    Left = 208
    Top = 288
  end
  object DcF012: TBrvProvider
    DataSet = QcF012
    Left = 176
    Top = 288
  end
  object QcF012Rec: TBrvDataSet
    GetMetadata = False
    DbxCommandType = 'Dbx.SQL'
    MaxBlobSize = -1
    Params = <>
    SQLConnection = SqlConnAdm
    BrShowFieldName = False
    BrQueryCode = 0
    BrDicionario = DmDicion.BrvDicionario
    BrType = VqNormal
    BrUserCode = 0
    Left = 96
    Top = 336
  end
  object CcF012Rec: TBrvClientDataSet
    Aggregates = <>
    Params = <>
    ProviderName = 'DcF012Rec'
    BrShowFieldName = False
    BrQueryCode = 0
    BrDicionario = DmDicion.BrvDicionario
    BrType = VqNormal
    BrGravaLog = True
    BrUserCode = 0
    Left = 208
    Top = 336
  end
  object DcF012Rec: TBrvProvider
    DataSet = QcF012Rec
    Options = [poAllowCommandText, poUseQuoteChar]
    Left = 152
    Top = 336
  end
  object QpF012: TBrvClientDataSet
    Aggregates = <>
    Params = <>
    BrShowFieldName = False
    BrQueryCode = 0
    BrDicionario = DmDicion.BrvDicionario
    BrType = VqNormal
    BrGravaLog = True
    BrUserCode = 0
    Left = 96
    Top = 288
  end
  object QpF017: TBrvClientDataSet
    Aggregates = <>
    Params = <>
    BrShowFieldName = False
    BrQueryCode = 0
    BrDicionario = DmDicion.BrvDicionario
    BrType = VqNormal
    BrGravaLog = True
    BrUserCode = 0
    Left = 296
    Top = 16
  end
  object QcF017: TBrvDataSet
    GetMetadata = False
    CommandText = 'SELECT * FROM F017 WHERE 1=2'
    DbxCommandType = 'Dbx.SQL'
    MaxBlobSize = -1
    Params = <>
    SQLConnection = SqlConnAdm
    BrShowFieldName = False
    BrQueryCode = 0
    BrDicionario = DmDicion.BrvDicionario
    BrType = VqNormal
    BrUserCode = 0
    Left = 328
    Top = 16
  end
  object CcF017: TBrvClientDataSet
    Aggregates = <>
    Params = <>
    ProviderName = 'DcF017'
    BrShowFieldName = False
    BrQueryCode = 0
    BrDicionario = DmDicion.BrvDicionario
    BrType = VqNormal
    BrGravaLog = True
    BrUserCode = 0
    Left = 440
    Top = 16
  end
  object DcF017: TBrvProvider
    DataSet = QcF017
    Left = 360
    Top = 16
  end
  object CpF018: TBrvClientDataSet
    Aggregates = <>
    Params = <>
    BrShowFieldName = False
    BrQueryCode = 0
    BrDicionario = DmDicion.BrvDicionario
    BrType = VqNormal
    BrGravaLog = True
    BrUserCode = 0
    Left = 488
    Top = 16
  end
  object CpF001: TBrvClientDataSet
    Aggregates = <>
    Params = <>
    BrShowFieldName = False
    BrQueryCode = 0
    BrDicionario = DmDicion.BrvDicionario
    BrType = VqNormal
    BrGravaLog = True
    BrUserCode = 0
    Left = 296
    Top = 64
  end
  object CpF002: TBrvClientDataSet
    Aggregates = <>
    Params = <>
    BrShowFieldName = False
    BrQueryCode = 0
    BrDicionario = DmDicion.BrvDicionario
    BrType = VqNormal
    BrGravaLog = True
    BrUserCode = 0
    Left = 328
    Top = 64
  end
  object CpN002: TBrvClientDataSet
    Aggregates = <>
    Params = <>
    BrShowFieldName = False
    BrQueryCode = 0
    BrDicionario = DmDicion.BrvDicionario
    BrType = VqNormal
    BrGravaLog = True
    BrUserCode = 0
    Left = 360
    Top = 64
  end
  object CpN003: TBrvClientDataSet
    Aggregates = <>
    Params = <>
    BrShowFieldName = False
    BrQueryCode = 0
    BrDicionario = DmDicion.BrvDicionario
    BrType = VqNormal
    BrGravaLog = True
    BrUserCode = 0
    Left = 440
    Top = 64
  end
  object CpParCon: TBrvClientDataSet
    Aggregates = <>
    Params = <>
    BrShowFieldName = False
    BrQueryCode = 0
    BrDicionario = DmDicion.BrvDicionario
    BrType = VqNormal
    BrGravaLog = True
    BrUserCode = 0
    Left = 488
    Top = 64
  end
  object CcF001: TBrvClientDataSet
    Aggregates = <>
    Params = <>
    ProviderName = 'DcF001'
    BrShowFieldName = False
    BrQueryCode = 0
    BrDicionario = DmDicion.BrvDicionario
    BrType = VqNormal
    BrGravaLog = True
    BrUserCode = 0
    Left = 360
    Top = 120
  end
  object DcF001: TBrvProvider
    DataSet = QcF001
    Left = 328
    Top = 120
  end
  object QcF001: TBrvDataSet
    SchemaName = 'vitalsis'
    GetMetadata = False
    CommandText = 
      'Select'#13#10'NRSEQDOC,CDEMPRES,NRNOTA,DSMODENF,NRSERINF,NRSUSENF,CDTR' +
      'ANSP,CJTITULA,CDEVENTO,NRSEQFIS,DTENTRAD,'#13#10'DTEMINOT,NRPRELAN,BSP' +
      'IS,PCPIS,VRPIS,BSCOFINS,PCCOFINS,VRCOFINS,BSICMS,PCALIICM,VRICMS' +
      ',BSISSQN,'#13#10'PCALIISS,VRISSQST,BSIRRF,VRIRRF,BSCSLL,VRCSLL,PCIRPJS' +
      'E,TXDADADI,BSINSS,PCALIINS,VRINSS,BSSUBTRI,'#13#10'VRSUBTRI,VRSEGURO,V' +
      'ROUTNOT,VRISENOT,VRIPI,VRFRETE,VRCONNOT,VRDESACE,SNFREICM,SNFREI' +
      'PI,TPFRETE,'#13#10'STNOTA,CDTITULA,NRCHADOC,SNDESPIC,PCCSLLSE,CDFISCAL' +
      ',CDESTEMI'#13#10'From F001'#13#10'Where 1=2'
    DbxCommandType = 'Dbx.SQL'
    MaxBlobSize = -1
    Params = <>
    SQLConnection = SqlConnAdm
    BrShowFieldName = False
    BrQueryCode = 0
    BrDicionario = DmDicion.BrvDicionario
    BrType = VqNormal
    BrUserCode = 0
    Left = 296
    Top = 120
  end
  object QcF002: TBrvDataSet
    SchemaName = 'vitalsis'
    GetMetadata = False
    CommandText = 
      'SELECT'#13#10'      NRSEQDOC, NRSEQUEN, CDPRODUT, NRSEQFIS, CDOPEFIS,'#13 +
      #10'      QTPRODUT, VRUNITAR, PCDESCON, VRDESCON, VRTOTAL,  VRIPI,'#13 +
      #10'      BSSUBTRI, VRSUBTRI, PCALIISS, BSICMS,   PCALIICM, CDSITTR' +
      'I,'#13#10'      VRICMS,   PCDEBAIC, CDCSTCOF, BSIMPCOF, PCALICOF, VRIM' +
      'PCOF,'#13#10'      CDCSTPIS, BSIMPPIS, PCALIPIS, VRIMPPIS, VRISENTA, V' +
      'ROUTRA,'#13#10'      TPFINENT, CDTITULA, DSMODENF, NRSERINF, NRNOTA, C' +
      'DEMPRES'#13#10#13#10'FROM F002 '#13#10#13#10'WHERE 1=2'
    DbxCommandType = 'Dbx.SQL'
    MaxBlobSize = -1
    Params = <>
    SQLConnection = SqlConnAdm
    BrShowFieldName = False
    BrQueryCode = 0
    BrDicionario = DmDicion.BrvDicionario
    BrType = VqNormal
    BrUserCode = 0
    Left = 296
    Top = 176
  end
  object CcF002: TBrvClientDataSet
    Aggregates = <>
    Params = <>
    ProviderName = 'DcF002'
    BrShowFieldName = False
    BrQueryCode = 0
    BrDicionario = DmDicion.BrvDicionario
    BrType = VqNormal
    BrGravaLog = True
    BrUserCode = 0
    Left = 360
    Top = 176
  end
  object DcF002: TBrvProvider
    DataSet = QcF002
    Left = 328
    Top = 176
  end
  object QcN002: TBrvDataSet
    GetMetadata = False
    CommandText = 'SELECT * FROM N002 WHERE 1=2'
    DbxCommandType = 'Dbx.SQL'
    MaxBlobSize = -1
    Params = <>
    SQLConnection = SqlConnAdm
    BrShowFieldName = False
    BrQueryCode = 0
    BrDicionario = DmDicion.BrvDicionario
    BrType = VqNormal
    BrUserCode = 0
    Left = 440
    Top = 120
  end
  object CcN002: TBrvClientDataSet
    Aggregates = <>
    Params = <>
    ProviderName = 'DcN002'
    BrShowFieldName = False
    BrQueryCode = 0
    BrDicionario = DmDicion.BrvDicionario
    BrType = VqNormal
    BrGravaLog = True
    BrUserCode = 0
    Left = 504
    Top = 120
  end
  object DcN002: TBrvProvider
    DataSet = QcN002
    Left = 472
    Top = 120
  end
  object QcN003: TBrvDataSet
    GetMetadata = False
    CommandText = 'SELECT * FROM N003 WHERE 1=2'
    DbxCommandType = 'Dbx.SQL'
    MaxBlobSize = -1
    Params = <>
    SQLConnection = SqlConnAdm
    BrShowFieldName = False
    BrQueryCode = 0
    BrDicionario = DmDicion.BrvDicionario
    BrType = VqNormal
    BrUserCode = 0
    Left = 440
    Top = 176
  end
  object CcN003: TBrvClientDataSet
    Aggregates = <>
    Params = <>
    ProviderName = 'DcN003'
    BrShowFieldName = False
    BrQueryCode = 0
    BrDicionario = DmDicion.BrvDicionario
    BrType = VqNormal
    BrGravaLog = True
    BrUserCode = 0
    Left = 504
    Top = 176
  end
  object DcN003: TBrvProvider
    DataSet = QcN003
    Left = 472
    Top = 176
  end
  object BrvContabil: TBrvContabil
    BrDicionario = DmDicion.BrvDicionario
    SQLConnection = SqlConnAdm
    Left = 536
    Top = 16
  end
  object DcF013Fecha: TBrvProvider
    DataSet = QcF013Fecha
    Options = [poAllowCommandText, poUseQuoteChar]
    Left = 472
    Top = 232
  end
  object CcF013Fecha: TBrvClientDataSet
    Aggregates = <>
    Params = <>
    ProviderName = 'DcF013Fecha'
    BrShowFieldName = False
    BrQueryCode = 0
    BrDicionario = DmDicion.BrvDicionario
    BrType = VqNormal
    BrGravaLog = True
    BrUserCode = 0
    Left = 504
    Top = 232
  end
  object QcF013Fecha: TBrvDataSet
    GetMetadata = False
    DbxCommandType = 'Dbx.SQL'
    MaxBlobSize = -1
    Params = <>
    SQLConnection = SqlConnAdm
    BrShowFieldName = False
    BrQueryCode = 0
    BrDicionario = DmDicion.BrvDicionario
    BrType = VqNormal
    BrFormName = 'MOV0025'
    BrUserCode = 0
    Left = 440
    Top = 232
  end
  object QcB003: TBrvDataSet
    GetMetadata = False
    DbxCommandType = 'Dbx.SQL'
    MaxBlobSize = -1
    Params = <>
    SQLConnection = SqlConnAdm
    BrShowFieldName = False
    BrQueryCode = 0
    BrDicionario = DmDicion.BrvDicionario
    BrType = VqNormal
    BrUserCode = 0
    Left = 440
    Top = 288
  end
  object CcB003: TBrvClientDataSet
    Aggregates = <>
    Params = <>
    ProviderName = 'DcB003'
    BrShowFieldName = False
    BrQueryCode = 216
    BrDicionario = DmDicion.BrvDicionario
    BrType = VqNormal
    BrGravaLog = True
    BrUserCode = 0
    Left = 504
    Top = 288
  end
  object DcB003: TBrvProvider
    DataSet = QcB003
    Options = [poAllowCommandText, poUseQuoteChar]
    Left = 472
    Top = 288
  end
  object CpB003: TBrvClientDataSet
    Aggregates = <>
    Params = <>
    BrShowFieldName = False
    BrQueryCode = 0
    BrDicionario = DmDicion.BrvDicionario
    BrType = VqNormal
    BrGravaLog = True
    BrUserCode = 0
    Left = 328
    Top = 280
  end
  object CcN006LancNfCc: TBrvClientDataSet
    Aggregates = <>
    Params = <>
    ProviderName = 'DcN006LancNfCc'
    BrShowFieldName = False
    BrQueryCode = 0
    BrDicionario = DmDicion.BrvDicionario
    BrType = VqNormal
    BrGravaLog = True
    BrUserCode = 0
    Left = 360
    Top = 232
  end
  object QcN006LancNfCc: TBrvDataSet
    GetMetadata = False
    CommandText = 'Select * From N006 Where 1=2'
    DbxCommandType = 'Dbx.SQL'
    MaxBlobSize = -1
    Params = <>
    SQLConnection = SqlConnAdm
    BrShowFieldName = False
    BrQueryCode = 0
    BrDicionario = DmDicion.BrvDicionario
    BrType = VqNormal
    BrUserCode = 0
    Left = 296
    Top = 232
  end
  object DcN006LancNfCc: TBrvProvider
    DataSet = QcN006LancNfCc
    Left = 328
    Top = 232
  end
  object CpN006NotaConta: TBrvClientDataSet
    Aggregates = <>
    Params = <>
    BrShowFieldName = False
    BrQueryCode = 0
    BrDicionario = DmDicion.BrvDicionario
    BrType = VqNormal
    BrGravaLog = True
    BrUserCode = 0
    Left = 296
    Top = 280
  end
  object CcN006CanNfCc: TBrvClientDataSet
    Aggregates = <>
    Params = <>
    ProviderName = 'DcN006CanNfCc'
    BrShowFieldName = False
    BrQueryCode = 0
    BrDicionario = DmDicion.BrvDicionario
    BrType = VqNormal
    BrGravaLog = True
    BrUserCode = 0
    Left = 360
    Top = 328
  end
  object QcN006CanNfCc: TBrvDataSet
    GetMetadata = False
    CommandText = 'Select * From N006 Where 1=2'
    DbxCommandType = 'Dbx.SQL'
    MaxBlobSize = -1
    Params = <>
    SQLConnection = SqlConnAdm
    BrShowFieldName = False
    BrQueryCode = 0
    BrDicionario = DmDicion.BrvDicionario
    BrType = VqNormal
    BrUserCode = 0
    Left = 296
    Top = 328
  end
  object DcN006CanNfCc: TBrvProvider
    DataSet = QcN006CanNfCc
    Left = 328
    Top = 328
  end
  object BrvContas: TBrvContas
    BrDicionario = DmDicion.BrvDicionario
    SQLConnection = SqlConnAdm
    BrContabilidade = BrvContabil
    Left = 600
    Top = 16
  end
  object QcQ004: TBrvDataSet
    GetMetadata = False
    DbxCommandType = 'Dbx.SQL'
    MaxBlobSize = -1
    Params = <>
    SQLConnection = SqlConnAdm
    BrShowFieldName = False
    BrQueryCode = 0
    BrDicionario = DmDicion.BrvDicionario
    BrType = VqNormal
    BrUserCode = 0
    Left = 440
    Top = 344
  end
  object DcQ004: TBrvProvider
    DataSet = QcQ004
    Options = [poAllowCommandText, poUseQuoteChar]
    Left = 478
    Top = 344
  end
  object CcQ004: TBrvClientDataSet
    Aggregates = <>
    Params = <>
    ProviderName = 'DcQ004'
    BrShowFieldName = False
    BrQueryCode = 232
    BrDicionario = DmDicion.BrvDicionario
    BrType = VqNormal
    BrGravaLog = True
    BrUserCode = 0
    Left = 516
    Top = 344
  end
  object QcQ005Rem: TBrvDataSet
    GetMetadata = False
    DbxCommandType = 'Dbx.SQL'
    MaxBlobSize = -1
    Params = <>
    SQLConnection = SqlConnAdm
    BrShowFieldName = False
    BrQueryCode = 240
    BrDicionario = DmDicion.BrvDicionario
    BrType = VqNormal
    BrUserCode = 0
    Left = 566
    Top = 400
  end
  object QcQ005: TBrvDataSet
    GetMetadata = False
    DbxCommandType = 'Dbx.SQL'
    MaxBlobSize = -1
    Params = <>
    SQLConnection = SqlConnAdm
    BrShowFieldName = False
    BrQueryCode = 0
    BrDicionario = DmDicion.BrvDicionario
    BrType = VqNormal
    BrUserCode = 0
    Left = 440
    Top = 400
  end
  object DcQ005: TBrvProvider
    DataSet = QcQ005
    Options = [poAllowCommandText, poUseQuoteChar]
    Left = 478
    Top = 400
  end
  object CcQ005: TBrvClientDataSet
    Aggregates = <>
    Params = <>
    ProviderName = 'DcQ005'
    BrShowFieldName = False
    BrQueryCode = 239
    BrDicionario = DmDicion.BrvDicionario
    BrType = VqNormal
    BrGravaLog = True
    BrUserCode = 0
    Left = 516
    Top = 400
  end
  object QcQ006: TBrvDataSet
    GetMetadata = False
    DbxCommandType = 'Dbx.SQL'
    MaxBlobSize = -1
    Params = <>
    SQLConnection = SqlConnAdm
    BrShowFieldName = False
    BrQueryCode = 0
    BrDicionario = DmDicion.BrvDicionario
    BrType = VqNormal
    BrUserCode = 0
    Left = 440
    Top = 456
  end
  object DcQ006: TBrvProvider
    DataSet = QcQ006
    Options = [poAllowCommandText, poUseQuoteChar]
    Left = 478
    Top = 456
  end
  object CcQ006: TBrvClientDataSet
    Aggregates = <>
    Params = <>
    ProviderName = 'DcQ006'
    BrShowFieldName = False
    BrQueryCode = 241
    BrDicionario = DmDicion.BrvDicionario
    BrType = VqNormal
    BrGravaLog = True
    BrUserCode = 0
    Left = 516
    Top = 456
  end
  object QcQ006Rem: TBrvDataSet
    GetMetadata = False
    DbxCommandType = 'Dbx.SQL'
    MaxBlobSize = -1
    Params = <>
    SQLConnection = SqlConnAdm
    BrShowFieldName = False
    BrQueryCode = 242
    BrDicionario = DmDicion.BrvDicionario
    BrType = VqNormal
    BrUserCode = 0
    Left = 566
    Top = 456
  end
  object QcQ007: TBrvDataSet
    GetMetadata = False
    DbxCommandType = 'Dbx.SQL'
    MaxBlobSize = -1
    Params = <>
    SQLConnection = SqlConnAdm
    BrShowFieldName = False
    BrQueryCode = 0
    BrDicionario = DmDicion.BrvDicionario
    BrType = VqNormal
    BrUserCode = 0
    Left = 440
    Top = 511
  end
  object DcQ007: TBrvProvider
    DataSet = QcQ007
    Options = [poAllowCommandText, poUseQuoteChar]
    Left = 478
    Top = 511
  end
  object CcQ007: TBrvClientDataSet
    Aggregates = <>
    Params = <>
    ProviderName = 'DcQ007'
    BrShowFieldName = False
    BrQueryCode = 243
    BrDicionario = DmDicion.BrvDicionario
    BrType = VqNormal
    BrGravaLog = True
    BrUserCode = 0
    Left = 516
    Top = 511
  end
  object QcQ007Rem: TBrvDataSet
    GetMetadata = False
    DbxCommandType = 'Dbx.SQL'
    MaxBlobSize = -1
    Params = <>
    SQLConnection = SqlConnAdm
    BrShowFieldName = False
    BrQueryCode = 244
    BrDicionario = DmDicion.BrvDicionario
    BrType = VqNormal
    BrUserCode = 0
    Left = 566
    Top = 511
  end
  object QcT002: TBrvDataSet
    GetMetadata = False
    DbxCommandType = 'Dbx.SQL'
    MaxBlobSize = -1
    Params = <>
    SQLConnection = SqlConnAdm
    BrShowFieldName = False
    BrQueryCode = 0
    BrDicionario = DmDicion.BrvDicionario
    BrType = VqNormal
    BrUserCode = 0
    Left = 304
    Top = 400
  end
  object CcT021: TBrvClientDataSet
    Aggregates = <>
    Params = <>
    ProviderName = 'DcT021'
    BrShowFieldName = False
    BrQueryCode = 257
    BrDicionario = DmDicion.BrvDicionario
    BrType = VqNormal
    BrGravaLog = True
    BrUserCode = 0
    Left = 380
    Top = 456
  end
  object CcT002: TBrvClientDataSet
    Aggregates = <>
    Params = <>
    ProviderName = 'DcT002'
    BrShowFieldName = False
    BrQueryCode = 256
    BrDicionario = DmDicion.BrvDicionario
    BrType = VqNormal
    BrGravaLog = True
    BrUserCode = 0
    Left = 380
    Top = 400
  end
  object DcT002: TBrvProvider
    DataSet = QcT002
    Options = [poAllowCommandText, poUseQuoteChar]
    Left = 342
    Top = 400
  end
  object QcT021: TBrvDataSet
    GetMetadata = False
    DbxCommandType = 'Dbx.SQL'
    MaxBlobSize = -1
    Params = <>
    SQLConnection = SqlConnAdm
    BrShowFieldName = False
    BrQueryCode = 0
    BrDicionario = DmDicion.BrvDicionario
    BrType = VqNormal
    BrUserCode = 0
    Left = 304
    Top = 456
  end
  object DcT021: TBrvProvider
    DataSet = QcT021
    Options = [poAllowCommandText, poUseQuoteChar]
    Left = 342
    Top = 456
  end
  object QcT008: TBrvDataSet
    GetMetadata = False
    DbxCommandType = 'Dbx.SQL'
    MaxBlobSize = -1
    Params = <>
    SQLConnection = SqlConnAdm
    BrShowFieldName = False
    BrQueryCode = 0
    BrDicionario = DmDicion.BrvDicionario
    BrType = VqNormal
    BrUserCode = 0
    Left = 96
    Top = 496
  end
  object DcT008: TBrvProvider
    DataSet = QcT008
    Options = [poAllowCommandText, poUseQuoteChar]
    Left = 134
    Top = 496
  end
  object CcT008: TBrvClientDataSet
    Aggregates = <>
    Params = <>
    ProviderName = 'DcT008'
    BrShowFieldName = False
    BrQueryCode = 270
    BrDicionario = DmDicion.BrvDicionario
    BrType = VqNormal
    BrGravaLog = True
    BrUserCode = 0
    Left = 172
    Top = 496
  end
  object QcW005: TBrvDataSet
    GetMetadata = False
    DbxCommandType = 'Dbx.SQL'
    MaxBlobSize = -1
    Params = <>
    SQLConnection = SqlConnAdm
    BrShowFieldName = False
    BrQueryCode = 0
    BrDicionario = DmDicion.BrvDicionario
    BrType = VqNormal
    BrUserCode = 0
    Left = 96
    Top = 448
  end
  object DcW005: TBrvProvider
    DataSet = QcW005
    Options = [poAllowCommandText, poUseQuoteChar]
    Left = 134
    Top = 448
  end
  object CcW005: TBrvClientDataSet
    Aggregates = <>
    Params = <>
    ProviderName = 'DcW005'
    BrShowFieldName = False
    BrQueryCode = 272
    BrDicionario = DmDicion.BrvDicionario
    BrType = VqNormal
    BrGravaLog = True
    BrUserCode = 0
    Left = 172
    Top = 448
  end
  object QcS052Upd: TBrvDataSet
    GetMetadata = False
    DbxCommandType = 'Dbx.SQL'
    MaxBlobSize = -1
    Params = <>
    SQLConnection = SqlConnAdm
    BrShowFieldName = False
    BrQueryCode = 274
    BrDicionario = DmDicion.BrvDicionario
    BrType = VqNormal
    BrUserCode = 0
    Left = 96
    Top = 544
  end
  object QcS052: TBrvDataSet
    GetMetadata = False
    DbxCommandType = 'Dbx.SQL'
    MaxBlobSize = -1
    Params = <>
    SQLConnection = SqlConnAdm
    BrShowFieldName = False
    BrQueryCode = 0
    BrDicionario = DmDicion.BrvDicionario
    BrType = VqNormal
    BrUserCode = 0
    Left = 152
    Top = 544
  end
  object DcS052: TBrvProvider
    DataSet = QcS052
    Options = [poAllowCommandText, poUseQuoteChar]
    Left = 190
    Top = 544
  end
  object CcS052: TBrvClientDataSet
    Aggregates = <>
    Params = <>
    ProviderName = 'DcS052'
    BrShowFieldName = False
    BrQueryCode = 275
    BrDicionario = DmDicion.BrvDicionario
    BrType = VqNormal
    BrGravaLog = True
    BrUserCode = 0
    Left = 228
    Top = 544
  end
  object DcF004: TBrvProvider
    DataSet = QcF004
    Options = [poAllowCommandText, poUseQuoteChar]
    Left = 600
    Top = 80
  end
  object CcF004: TBrvClientDataSet
    Aggregates = <>
    Params = <>
    ProviderName = 'DcF004'
    BrShowFieldName = False
    BrQueryCode = 282
    BrDicionario = DmDicion.BrvDicionario
    BrType = VqNormal
    BrGravaLog = True
    BrUserCode = 0
    Left = 640
    Top = 80
  end
  object QcF004: TBrvDataSet
    SchemaName = 'vitalsis'
    GetMetadata = False
    CommandText = 
      'Select'#13#10'NRSEQDOC,CDEMPRES,NRNOTA,DSMODENF,NRSERINF,NRSUSENF,CDTR' +
      'ANSP,CJTITULA,CDEVENTO,NRSEQFIS,DTENTRAD,'#13#10'DTEMINOT,NRPRELAN,BSP' +
      'IS,PCPIS,VRPIS,BSCOFINS,PCCOFINS,VRCOFINS,BSICMS,PCALIICM,VRICMS' +
      ',BSISSQN,'#13#10'PCALIISS,VRISSQST,BSIRRF,VRIRRF,BSCSLL,VRCSLL,PCIRPJS' +
      'E,TXDADADI,BSINSS,PCALIINS,VRINSS,BSSUBTRI,'#13#10'VRSUBTRI,VRSEGURO,V' +
      'ROUTNOT,VRISENOT,VRIPI,VRFRETE,VRCONNOT,VRDESACE,SNFREICM,SNFREI' +
      'PI,TPFRETE,'#13#10'STNOTA,CDTITULA,NRCHADOC,SNDESPIC,PCCSLLSE,CDFISCAL' +
      ',CDESTEMI'#13#10'From F001'#13#10'Where 1=2'
    DbxCommandType = 'Dbx.SQL'
    MaxBlobSize = -1
    Params = <>
    SQLConnection = SqlConnAdm
    BrShowFieldName = False
    BrQueryCode = 0
    BrDicionario = DmDicion.BrvDicionario
    BrType = VqNormal
    BrUserCode = 0
    Left = 560
    Top = 80
  end
  object DcF003: TBrvProvider
    DataSet = QcF003
    Options = [poAllowCommandText, poUseQuoteChar]
    Left = 608
    Top = 136
  end
  object QcF003: TBrvDataSet
    SchemaName = 'vitalsis'
    GetMetadata = False
    CommandText = 
      'Select'#13#10'NRSEQDOC,CDEMPRES,NRNOTA,DSMODENF,NRSERINF,NRSUSENF,CDTR' +
      'ANSP,CJTITULA,CDEVENTO,NRSEQFIS,DTENTRAD,'#13#10'DTEMINOT,NRPRELAN,BSP' +
      'IS,PCPIS,VRPIS,BSCOFINS,PCCOFINS,VRCOFINS,BSICMS,PCALIICM,VRICMS' +
      ',BSISSQN,'#13#10'PCALIISS,VRISSQST,BSIRRF,VRIRRF,BSCSLL,VRCSLL,PCIRPJS' +
      'E,TXDADADI,BSINSS,PCALIINS,VRINSS,BSSUBTRI,'#13#10'VRSUBTRI,VRSEGURO,V' +
      'ROUTNOT,VRISENOT,VRIPI,VRFRETE,VRCONNOT,VRDESACE,SNFREICM,SNFREI' +
      'PI,TPFRETE,'#13#10'STNOTA,CDTITULA,NRCHADOC,SNDESPIC,PCCSLLSE,CDFISCAL' +
      ',CDESTEMI'#13#10'From F001'#13#10'Where 1=2'
    DbxCommandType = 'Dbx.SQL'
    MaxBlobSize = -1
    Params = <>
    SQLConnection = SqlConnAdm
    BrShowFieldName = False
    BrQueryCode = 0
    BrDicionario = DmDicion.BrvDicionario
    BrType = VqNormal
    BrUserCode = 0
    Left = 568
    Top = 136
  end
  object CcF003: TBrvClientDataSet
    Aggregates = <>
    Params = <>
    ProviderName = 'DcF003'
    BrShowFieldName = False
    BrQueryCode = 282
    BrDicionario = DmDicion.BrvDicionario
    BrType = VqNormal
    BrGravaLog = True
    BrUserCode = 0
    Left = 648
    Top = 136
  end
  object QcQ004CadDis: TBrvDataSet
    GetMetadata = False
    DbxCommandType = 'Dbx.SQL'
    MaxBlobSize = -1
    Params = <>
    SQLConnection = SqlConnAdm
    BrShowFieldName = False
    BrQueryCode = 0
    BrDicionario = DmDicion.BrvDicionario
    BrType = VqNormal
    BrUserCode = 0
    Left = 576
    Top = 344
  end
  object DcQ004CadDis: TBrvProvider
    DataSet = QcQ004CadDis
    Options = [poAllowCommandText, poUseQuoteChar]
    Left = 614
    Top = 344
  end
  object CcQ004CadDis: TBrvClientDataSet
    Aggregates = <>
    Params = <>
    ProviderName = 'DcQ004'
    BrShowFieldName = False
    BrQueryCode = 232
    BrDicionario = DmDicion.BrvDicionario
    BrType = VqNormal
    BrGravaLog = True
    BrUserCode = 0
    Left = 652
    Top = 344
  end
end
