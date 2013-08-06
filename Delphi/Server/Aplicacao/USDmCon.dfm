object SDmCon: TSDmCon
  OldCreateOrder = False
  Height = 501
  Width = 661
  object SqlConnCon: TBrvConnection
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
    AfterConnect = SqlConnConAfterConnect
    Left = 46
    Top = 6
  end
  object QExecute: TBrvDataSet
    GetMetadata = False
    CommandText = 'select * from S001 where cdusuari is null'
    DbxCommandType = 'Dbx.SQL'
    MaxBlobSize = -1
    Params = <>
    SQLConnection = SqlConnCon
    BrShowFieldName = False
    BrQueryCode = 0
    BrDicionario = DmDicion.BrvDicionario
    BrType = VqNormal
    BrUserCode = 0
    Left = 328
    Top = 16
  end
  object QpBem: TClientDataSet
    Aggregates = <>
    Params = <>
    Left = 56
    Top = 64
  end
  object CpLanBem: TBrvClientDataSet
    Aggregates = <>
    Params = <>
    ProviderName = 'DcLanBem'
    BrShowFieldName = False
    BrQueryCode = 0
    BrDicionario = DmDicion.BrvDicionario
    BrType = VqNormal
    BrGravaLog = False
    BrUserCode = 0
    Left = 72
    Top = 216
  end
  object DcLanBem: TBrvProvider
    DataSet = QcLanBem
    Options = [poAllowCommandText, poUseQuoteChar]
    Left = 44
    Top = 216
  end
  object QcLanBem: TBrvDataSet
    GetMetadata = False
    CommandText = 'select * from S001 where cdusuari is null'
    DbxCommandType = 'Dbx.SQL'
    MaxBlobSize = -1
    Params = <>
    SQLConnection = SqlConnCon
    BrShowFieldName = False
    BrQueryCode = 0
    BrDicionario = DmDicion.BrvDicionario
    BrType = VqNormal
    BrUserCode = 0
    Left = 16
    Top = 216
  end
  object CpXML: TBrvClientDataSet
    Aggregates = <>
    Params = <>
    ProviderName = 'DcLanBem'
    BrShowFieldName = False
    BrQueryCode = 0
    BrDicionario = DmDicion.BrvDicionario
    BrType = VqNormal
    BrGravaLog = False
    BrUserCode = 0
    Left = 72
    Top = 168
  end
  object DcXML: TBrvProvider
    DataSet = QcXML
    Options = [poAllowCommandText, poUseQuoteChar]
    Left = 44
    Top = 168
  end
  object QcXML: TBrvDataSet
    GetMetadata = False
    CommandText = 'select * from S001 where cdusuari is null'
    DbxCommandType = 'Dbx.SQL'
    MaxBlobSize = -1
    Params = <>
    SQLConnection = SqlConnCon
    BrShowFieldName = False
    BrQueryCode = 0
    BrDicionario = DmDicion.BrvDicionario
    BrType = VqNormal
    BrUserCode = 0
    Left = 16
    Top = 168
  end
  object QcLanPis: TBrvDataSet
    GetMetadata = False
    DbxCommandType = 'Dbx.SQL'
    MaxBlobSize = -1
    Params = <>
    SQLConnection = SqlConnCon
    BrShowFieldName = False
    BrQueryCode = 0
    BrDicionario = DmDicion.BrvDicionario
    BrType = VqNormal
    BrUserCode = 0
    Left = 16
    Top = 244
  end
  object DcLanPis: TBrvProvider
    DataSet = QcLanPis
    Options = [poAllowCommandText, poUseQuoteChar]
    Left = 44
    Top = 244
  end
  object CcLanPis: TBrvClientDataSet
    Aggregates = <>
    Params = <>
    ProviderName = 'DcLanPis'
    BrShowFieldName = False
    BrQueryCode = 71
    BrDicionario = DmDicion.BrvDicionario
    BrType = VqNormal
    BrGravaLog = False
    BrUserCode = 0
    Left = 72
    Top = 244
  end
  object DCItensPlano: TBrvProvider
    DataSet = QCItensPlano
    Options = [poAllowCommandText, poUseQuoteChar]
    Left = 44
    Top = 292
  end
  object QCItensPlano: TBrvDataSet
    GetMetadata = False
    CommandText = 'select * from S001 where cdusuari is null'
    DbxCommandType = 'Dbx.SQL'
    MaxBlobSize = -1
    Params = <>
    SQLConnection = SqlConnCon
    BrShowFieldName = False
    BrQueryCode = 0
    BrDicionario = DmDicion.BrvDicionario
    BrType = VqNormal
    BrUserCode = 0
    Left = 16
    Top = 292
  end
  object DCClassifica: TBrvProvider
    DataSet = QCClassifica
    Options = [poAllowCommandText, poUseQuoteChar]
    Left = 44
    Top = 335
  end
  object QCClassifica: TBrvDataSet
    GetMetadata = False
    CommandText = 'select * from S001 where cdusuari is null'
    DbxCommandType = 'Dbx.SQL'
    MaxBlobSize = -1
    Params = <>
    SQLConnection = SqlConnCon
    BrShowFieldName = False
    BrQueryCode = 0
    BrDicionario = DmDicion.BrvDicionario
    BrType = VqNormal
    BrUserCode = 0
    Left = 16
    Top = 335
  end
  object QCProxConta: TBrvDataSet
    GetMetadata = False
    CommandText = 'select * from S001 where cdusuari is null'
    DbxCommandType = 'Dbx.SQL'
    MaxBlobSize = -1
    Params = <>
    SQLConnection = SqlConnCon
    BrShowFieldName = False
    BrQueryCode = 0
    BrDicionario = DmDicion.BrvDicionario
    BrType = VqNormal
    BrUserCode = 0
    Left = 16
    Top = 383
  end
  object DCProxConta: TBrvProvider
    DataSet = QCProxConta
    Options = [poAllowCommandText, poUseQuoteChar]
    Left = 44
    Top = 383
  end
  object QpPlanoContas: TBrvClientDataSet
    Aggregates = <>
    Params = <>
    BrShowFieldName = False
    BrQueryCode = 0
    BrDicionario = DmDicion.BrvDicionario
    BrType = VqNormal
    BrGravaLog = False
    BrUserCode = 0
    Left = 166
    Top = 64
  end
  object BrvExport: TBrvExport
    BrDicionario = DmDicion.BrvDicionario
    Left = 544
    Top = 120
  end
  object DcPlano: TBrvProvider
    DataSet = QcPlano
    Options = [poAllowCommandText, poUseQuoteChar]
    Left = 196
    Top = 212
  end
  object QcPlano: TBrvDataSet
    GetMetadata = False
    CommandText = 'select * from S001 where cdusuari is null'
    DbxCommandType = 'Dbx.SQL'
    MaxBlobSize = -1
    Params = <>
    SQLConnection = SqlConnCon
    BrShowFieldName = False
    BrQueryCode = 0
    BrDicionario = DmDicion.BrvDicionario
    BrType = VqNormal
    BrUserCode = 0
    Left = 168
    Top = 212
  end
  object DcCenCusCre: TBrvProvider
    DataSet = QcCenCusCre
    Options = [poAllowCommandText, poUseQuoteChar]
    Left = 196
    Top = 164
  end
  object QcCenCusCre: TBrvDataSet
    GetMetadata = False
    CommandText = 'select * from S001 where cdusuari is null'
    DbxCommandType = 'Dbx.SQL'
    MaxBlobSize = -1
    Params = <>
    SQLConnection = SqlConnCon
    BrShowFieldName = False
    BrQueryCode = 0
    BrDicionario = DmDicion.BrvDicionario
    BrType = VqNormal
    BrUserCode = 0
    Left = 168
    Top = 164
  end
  object DcCenCusDeb: TBrvProvider
    DataSet = QcCenCusDeb
    Options = [poAllowCommandText, poUseQuoteChar]
    Left = 196
    Top = 116
  end
  object QcCenCusDeb: TBrvDataSet
    GetMetadata = False
    CommandText = 'select * from S001 where cdusuari is null'
    DbxCommandType = 'Dbx.SQL'
    MaxBlobSize = -1
    Params = <>
    SQLConnection = SqlConnCon
    BrShowFieldName = False
    BrQueryCode = 0
    BrDicionario = DmDicion.BrvDicionario
    BrType = VqNormal
    BrUserCode = 0
    Left = 168
    Top = 116
  end
  object QcPerCon: TBrvDataSet
    GetMetadata = False
    DbxCommandType = 'Dbx.SQL'
    MaxBlobSize = -1
    Params = <>
    SQLConnection = SqlConnCon
    BrShowFieldName = False
    BrQueryCode = 0
    BrDicionario = DmDicion.BrvDicionario
    BrType = VqNormal
    BrUserCode = 0
    Left = 168
    Top = 364
  end
  object DcPerCon: TBrvProvider
    DataSet = QcPerCon
    Options = [poAllowCommandText, poUseQuoteChar]
    Left = 196
    Top = 364
  end
  object CcPerCon: TBrvClientDataSet
    Aggregates = <>
    Params = <>
    ProviderName = 'DcPerCon'
    BrShowFieldName = False
    BrQueryCode = 100
    BrDicionario = DmDicion.BrvDicionario
    BrType = VqNormal
    BrGravaLog = False
    BrUserCode = 0
    Left = 224
    Top = 364
  end
  object QcSaldoCenCus: TBrvDataSet
    GetMetadata = False
    DbxCommandType = 'Dbx.SQL'
    MaxBlobSize = -1
    Params = <>
    SQLConnection = SqlConnCon
    BrShowFieldName = False
    BrQueryCode = 0
    BrDicionario = DmDicion.BrvDicionario
    BrType = VqNormal
    BrUserCode = 0
    Left = 168
    Top = 412
  end
  object DcSaldoCenCus: TBrvProvider
    DataSet = QcSaldoCenCus
    Options = [poAllowCommandText, poUseQuoteChar]
    Left = 196
    Top = 412
  end
  object CcSaldoCenCus: TBrvClientDataSet
    Aggregates = <>
    Params = <>
    ProviderName = 'DcSaldoCenCus'
    BrShowFieldName = False
    BrQueryCode = 103
    BrDicionario = DmDicion.BrvDicionario
    BrType = VqNormal
    BrGravaLog = False
    BrUserCode = 0
    Left = 224
    Top = 412
  end
  object BrvContabil: TBrvContabil
    BrDicionario = DmDicion.BrvDicionario
    SQLConnection = SqlConnCon
    Left = 408
    Top = 312
  end
  object BrvString: TBrvString
    Left = 472
    Top = 224
  end
  object CPPlaEmp: TClientDataSet
    Aggregates = <>
    Params = <>
    Left = 328
    Top = 408
  end
  object QcB012: TBrvDataSet
    GetMetadata = False
    CommandText = 
      'Select B012.CdEmpres, B012.NrSeqPar, B012.DsParame, B012.NrSeqFo' +
      'r, '#13#10'       B012.TpFormul, B012.NrPlano , B001.DsPlano , B012.Cd' +
      'Histor, G008.DsHistor, '#13#10'       B012.NrConCre, B002C.NmConta  As' +
      ' NmContaC  , B012.NrConDeb, '#13#10'       B002D.NmConta  As NmContaD ' +
      ' , B012.CdCeCuCr, B006C.DsCenCus As DsCenCusC ,'#13#10'       B012.CdC' +
      'eCuDe, B006D.DsCenCus As DsCenCusD'#13#10#13#10'From B012 B012 '#13#10'     Left' +
      ' Join B001 B001  on (B012.NrPlano  = B001.NrPlano)'#13#10'     Left Jo' +
      'in G008 G008  on (G008.CdHistor = B012.CdHistor)'#13#10'     Left Join' +
      ' B002 B002C on (B012.NrPlano  = B002C.NrPlano and B012.NrConCre ' +
      '= B002C.NrConta)'#13#10'     Left Join B002 B002D on (B012.NrPlano  = ' +
      'B002D.NrPlano and B012.NrConDeb = B002D.NrConta)'#13#10'     Left Join' +
      ' B006 B006C on (B012.CdCeCuCr = B006C.CdCenCus)'#13#10'     Left Join ' +
      'B006 B006D on (B012.CdCeCuDe = B006D.CdCenCus)'#13#10#13#10'Where B012.TpF' +
      'ormul is null'
    DbxCommandType = 'Dbx.SQL'
    MaxBlobSize = -1
    Params = <>
    SQLConnection = SqlConnCon
    BrShowFieldName = False
    BrQueryCode = 0
    BrDicionario = DmDicion.BrvDicionario
    BrType = VqNormal
    BrUserCode = 0
    Left = 168
    Top = 280
  end
  object DpB012: TBrvProvider
    DataSet = QcB012
    Options = [poAllowCommandText, poUseQuoteChar]
    Left = 216
    Top = 280
  end
end
