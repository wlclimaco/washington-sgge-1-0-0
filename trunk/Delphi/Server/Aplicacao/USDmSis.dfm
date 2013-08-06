object SDmSis: TSDmSis
  OldCreateOrder = False
  Height = 601
  Width = 525
  object QcTabela: TBrvDataSet
    GetMetadata = False
    CommandText = 'select * from S001 where cdusuari is null'
    DbxCommandType = 'Dbx.SQL'
    MaxBlobSize = -1
    Params = <>
    SQLConnection = SqlConnSis
    BrShowFieldName = False
    BrQueryCode = 0
    BrDicionario = DmDicion.BrvDicionario
    BrType = VqNormal
    BrUserCode = 0
    Left = 16
    Top = 56
  end
  object DcTabela: TBrvProvider
    DataSet = QcTabela
    Options = [poAllowCommandText, poUseQuoteChar]
    Left = 43
    Top = 56
  end
  object QcTabAtr: TBrvDataSet
    GetMetadata = False
    CommandText = 'select * from S001 where cdusuari is null'
    DbxCommandType = 'Dbx.SQL'
    MaxBlobSize = -1
    Params = <>
    SQLConnection = SqlConnSis
    BrShowFieldName = False
    BrQueryCode = 0
    BrDicionario = DmDicion.BrvDicionario
    BrType = VqNormal
    BrUserCode = 0
    Left = 16
    Top = 104
  end
  object DcTabAtr: TBrvProvider
    DataSet = QcTabAtr
    Options = [poAllowCommandText, poUseQuoteChar]
    Left = 44
    Top = 104
  end
  object QcDomAtr: TBrvDataSet
    GetMetadata = False
    CommandText = 'select * from S001 where cdusuari is null'
    DbxCommandType = 'Dbx.SQL'
    MaxBlobSize = -1
    Params = <>
    SQLConnection = SqlConnSis
    BrShowFieldName = False
    BrQueryCode = 0
    BrDicionario = DmDicion.BrvDicionario
    BrType = VqNormal
    BrUserCode = 0
    Left = 16
    Top = 160
  end
  object DcDomAtr: TBrvProvider
    DataSet = QcDomAtr
    Options = [poAllowCommandText, poUseQuoteChar]
    Left = 44
    Top = 160
  end
  object QcChaPri: TBrvDataSet
    GetMetadata = False
    CommandText = 'select * from S001 where cdusuari is null'
    DbxCommandType = 'Dbx.SQL'
    MaxBlobSize = -1
    Params = <>
    SQLConnection = SqlConnSis
    BrShowFieldName = False
    BrQueryCode = 0
    BrDicionario = DmDicion.BrvDicionario
    BrType = VqNormal
    BrUserCode = 0
    Left = 16
    Top = 216
  end
  object DcChaPri: TBrvProvider
    DataSet = QcChaPri
    Options = [poAllowCommandText, poUseQuoteChar]
    Left = 44
    Top = 216
  end
  object SqlConnSis: TBrvConnection
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
    AfterConnect = SqlConnSisAfterConnect
    Left = 46
    Top = 6
  end
  object QExecute: TBrvDataSet
    GetMetadata = False
    CommandText = 'select * from S001 where cdusuari is null'
    DbxCommandType = 'Dbx.SQL'
    MaxBlobSize = -1
    Params = <>
    SQLConnection = SqlConnSis
    BrShowFieldName = False
    BrQueryCode = 0
    BrDicionario = DmDicion.BrvDicionario
    BrType = VqNormal
    BrUserCode = 0
    Left = 336
    Top = 80
  end
  object QcChaEst: TBrvDataSet
    GetMetadata = False
    CommandText = 'select * from S001 where cdusuari is null'
    DbxCommandType = 'Dbx.SQL'
    MaxBlobSize = -1
    Params = <>
    SQLConnection = SqlConnSis
    BrShowFieldName = False
    BrQueryCode = 0
    BrDicionario = DmDicion.BrvDicionario
    BrType = VqNormal
    BrUserCode = 0
    Left = 16
    Top = 272
  end
  object DcChaEst: TBrvProvider
    DataSet = QcChaEst
    Options = [poAllowCommandText, poUseQuoteChar]
    Left = 44
    Top = 272
  end
  object QcColEst: TBrvDataSet
    GetMetadata = False
    CommandText = 'select * from S001 where cdusuari is null'
    DbxCommandType = 'Dbx.SQL'
    MaxBlobSize = -1
    Params = <>
    SQLConnection = SqlConnSis
    BrShowFieldName = False
    BrQueryCode = 0
    BrDicionario = DmDicion.BrvDicionario
    BrType = VqNormal
    BrUserCode = 0
    Left = 160
    Top = 16
  end
  object DcColEst: TBrvProvider
    DataSet = QcColEst
    Options = [poAllowCommandText, poUseQuoteChar]
    Left = 188
    Top = 16
  end
  object CcChaPri: TBrvClientDataSet
    Aggregates = <>
    Params = <>
    ProviderName = 'DcChaPri'
    BrShowFieldName = False
    BrQueryCode = 0
    BrDicionario = DmDicion.BrvDicionario
    BrType = VqNormal
    BrGravaLog = True
    BrUserCode = 0
    Left = 72
    Top = 216
  end
  object QSql: TBrvDataSet
    GetMetadata = False
    CommandText = 'select * from S001 where cdusuari is null'
    DbxCommandType = 'Dbx.SQL'
    MaxBlobSize = -1
    Params = <>
    SQLConnection = SqlConnSis
    BrShowFieldName = False
    BrQueryCode = 0
    BrDicionario = DmDicion.BrvDicionario
    BrType = VqNormal
    BrUserCode = 0
    Left = 160
    Top = 48
  end
  object QcSql: TBrvProvider
    DataSet = QSql
    Options = [poAllowCommandText, poUseQuoteChar]
    Left = 188
    Top = 48
  end
  object QcConfRelUsr: TBrvDataSet
    GetMetadata = False
    CommandText = 'select * from S001 where cdusuari is null'
    DbxCommandType = 'Dbx.SQL'
    MaxBlobSize = -1
    Params = <>
    SQLConnection = SqlConnSis
    BrShowFieldName = False
    BrQueryCode = 0
    BrDicionario = DmDicion.BrvDicionario
    BrType = VqNormal
    BrUserCode = 0
    Left = 312
    Top = 136
  end
  object DcConfRelUsr: TBrvProvider
    DataSet = QcConfRelUsr
    Options = [poAllowCommandText, poUseQuoteChar]
    Left = 344
    Top = 136
  end
  object QcConfColRelUsr: TBrvDataSet
    GetMetadata = False
    CommandText = 'select * from S001 where cdusuari is null'
    DbxCommandType = 'Dbx.SQL'
    MaxBlobSize = -1
    Params = <>
    SQLConnection = SqlConnSis
    BrShowFieldName = False
    BrQueryCode = 0
    BrDicionario = DmDicion.BrvDicionario
    BrType = VqNormal
    BrUserCode = 0
    Left = 312
    Top = 184
  end
  object DcConfColRelUsr: TBrvProvider
    DataSet = QcConfColRelUsr
    Options = [poAllowCommandText, poUseQuoteChar]
    Left = 344
    Top = 184
  end
  object QcSqlUsr: TBrvDataSet
    GetMetadata = False
    CommandText = 'select * from S001 where cdusuari is null'
    DbxCommandType = 'Dbx.SQL'
    MaxBlobSize = -1
    Params = <>
    SQLConnection = SqlConnSis
    BrShowFieldName = False
    BrQueryCode = 0
    BrDicionario = DmDicion.BrvDicionario
    BrType = VqNormal
    BrUserCode = 0
    Left = 160
    Top = 80
  end
  object DcSqlUsr: TBrvProvider
    DataSet = QcSqlUsr
    Options = [poAllowCommandText, poUseQuoteChar]
    Left = 188
    Top = 80
  end
  object QcTrigger: TBrvDataSet
    GetMetadata = False
    CommandText = 'select * from S001 where cdusuari is null'
    DbxCommandType = 'Dbx.SQL'
    MaxBlobSize = -1
    Params = <>
    SQLConnection = SqlConnSis
    BrShowFieldName = False
    BrQueryCode = 0
    BrDicionario = DmDicion.BrvDicionario
    BrType = VqNormal
    BrUserCode = 0
    Left = 160
    Top = 112
  end
  object DcTrigger: TBrvProvider
    DataSet = QcTrigger
    Options = [poAllowCommandText, poUseQuoteChar]
    Left = 188
    Top = 112
  end
  object QcIndice: TBrvDataSet
    GetMetadata = False
    CommandText = 'select * from S001 where cdusuari is null'
    DbxCommandType = 'Dbx.SQL'
    MaxBlobSize = -1
    Params = <>
    SQLConnection = SqlConnSis
    BrShowFieldName = False
    BrQueryCode = 0
    BrDicionario = DmDicion.BrvDicionario
    BrType = VqNormal
    BrUserCode = 0
    Left = 160
    Top = 144
  end
  object DcIndice: TBrvProvider
    DataSet = QcIndice
    Options = [poAllowCommandText, poUseQuoteChar]
    Left = 188
    Top = 144
  end
  object QcColInd: TBrvDataSet
    GetMetadata = False
    CommandText = 'select * from S001 where cdusuari is null'
    DbxCommandType = 'Dbx.SQL'
    MaxBlobSize = -1
    Params = <>
    SQLConnection = SqlConnSis
    BrShowFieldName = False
    BrQueryCode = 0
    BrDicionario = DmDicion.BrvDicionario
    BrType = VqNormal
    BrUserCode = 0
    Left = 160
    Top = 176
  end
  object DcColInd: TBrvProvider
    DataSet = QcColInd
    Options = [poAllowCommandText, poUseQuoteChar]
    Left = 188
    Top = 176
  end
  object QcProced: TBrvDataSet
    GetMetadata = False
    CommandText = 'select * from S001 where cdusuari is null'
    DbxCommandType = 'Dbx.SQL'
    MaxBlobSize = -1
    Params = <>
    SQLConnection = SqlConnSis
    BrShowFieldName = False
    BrQueryCode = 0
    BrDicionario = DmDicion.BrvDicionario
    BrType = VqNormal
    BrUserCode = 0
    Left = 160
    Top = 216
  end
  object DcProced: TBrvProvider
    DataSet = QcProced
    Options = [poAllowCommandText, poUseQuoteChar]
    Left = 188
    Top = 216
  end
  object QcExcess: TBrvDataSet
    GetMetadata = False
    CommandText = 'select * from S025 where nmexcess is null'
    DbxCommandType = 'Dbx.SQL'
    MaxBlobSize = -1
    Params = <>
    SQLConnection = SqlConnSis
    BrShowFieldName = False
    BrQueryCode = 0
    BrDicionario = DmDicion.BrvDicionario
    BrType = VqNormal
    BrUserCode = 0
    Left = 160
    Top = 258
  end
  object DcExcess: TBrvProvider
    DataSet = QcExcess
    Options = [poAllowCommandText, poUseQuoteChar]
    Left = 188
    Top = 258
  end
  object QcForDin: TBrvDataSet
    GetMetadata = False
    CommandText = 'select * from S014 where nmtabela is null'
    DbxCommandType = 'Dbx.SQL'
    MaxBlobSize = -1
    Params = <>
    SQLConnection = SqlConnSis
    BrShowFieldName = False
    BrQueryCode = 0
    BrDicionario = DmDicion.BrvDicionario
    BrType = VqNormal
    BrUserCode = 0
    Left = 160
    Top = 306
  end
  object DcForDin: TBrvProvider
    DataSet = QcForDin
    Options = [poAllowCommandText, poUseQuoteChar]
    Left = 188
    Top = 306
  end
  object QpAtrDin: TBrvClientDataSet
    Aggregates = <>
    Params = <>
    ProviderName = 'DcAtrDin'
    BrShowFieldName = False
    BrQueryCode = 0
    BrDicionario = DmDicion.BrvDicionario
    BrType = VqNormal
    BrGravaLog = False
    BrFormName = 'sDmSis'
    BrUserCode = 0
    Left = 218
    Top = 354
  end
  object QcAtrDin: TBrvDataSet
    GetMetadata = False
    CommandText = 'select * from S014 where nmtabela is null'
    DbxCommandType = 'Dbx.SQL'
    MaxBlobSize = -1
    Params = <>
    SQLConnection = SqlConnSis
    BrShowFieldName = False
    BrQueryCode = 0
    BrDicionario = DmDicion.BrvDicionario
    BrType = VqNormal
    BrUserCode = 0
    Left = 160
    Top = 354
  end
  object DcAtrDin: TBrvProvider
    DataSet = QcAtrDin
    Options = [poAllowCommandText, poUseQuoteChar]
    Left = 188
    Top = 354
  end
  object QpDados: TBrvDataSet
    GetMetadata = False
    DbxCommandType = 'Dbx.SQL'
    MaxBlobSize = -1
    Params = <>
    SQLConnection = SqlConnSis
    BrShowFieldName = False
    BrQueryCode = 0
    BrDicionario = DmDicion.BrvDicionario
    BrType = VqNormal
    BrUserCode = 0
    Left = 256
    Top = 16
  end
  object DpDados: TDataSetProvider
    DataSet = QpDados
    Options = [poAllowCommandText, poUseQuoteChar]
    Left = 282
    Top = 16
  end
  object CpDados: TBrvClientDataSet
    Aggregates = <>
    Params = <>
    ProviderName = 'DpDados'
    BrShowFieldName = False
    BrQueryCode = 0
    BrDicionario = DmDicion.BrvDicionario
    BrType = VqNormal
    BrGravaLog = False
    BrFormName = 'SdmSis'
    BrUserCode = 0
    Left = 310
    Top = 16
  end
  object QcS026: TBrvDataSet
    GetMetadata = False
    CommandText = 'select * from S014 where nmtabela is null'
    DbxCommandType = 'Dbx.SQL'
    MaxBlobSize = -1
    Params = <>
    SQLConnection = SqlConnSis
    BrShowFieldName = False
    BrQueryCode = 0
    BrDicionario = DmDicion.BrvDicionario
    BrType = VqNormal
    BrUserCode = 0
    Left = 280
    Top = 354
  end
  object DcS026: TBrvProvider
    DataSet = QcS026
    Options = [poAllowCommandText, poUseQuoteChar]
    Left = 308
    Top = 354
  end
  object QpS026: TBrvClientDataSet
    Aggregates = <>
    Params = <>
    ProviderName = 'DcS026'
    BrShowFieldName = False
    BrQueryCode = 0
    BrDicionario = DmDicion.BrvDicionario
    BrType = VqNormal
    BrGravaLog = True
    BrFormName = 'SdmSis'
    BrUserCode = 0
    Left = 338
    Top = 354
  end
  object QcSubFor: TBrvDataSet
    GetMetadata = False
    CommandText = 'select * from S014 where nmtabela is null'
    DbxCommandType = 'Dbx.SQL'
    MaxBlobSize = -1
    Params = <>
    SQLConnection = SqlConnSis
    BrShowFieldName = False
    BrQueryCode = 0
    BrDicionario = DmDicion.BrvDicionario
    BrType = VqNormal
    BrUserCode = 0
    Left = 248
    Top = 306
  end
  object DcSubFor: TBrvProvider
    DataSet = QcSubFor
    Options = [poAllowCommandText, poUseQuoteChar]
    Left = 276
    Top = 306
  end
  object QcMenu: TBrvDataSet
    GetMetadata = False
    CommandText = 'select * from S001 where cdusuari is null'
    DbxCommandType = 'Dbx.SQL'
    MaxBlobSize = -1
    Params = <>
    SQLConnection = SqlConnSis
    BrShowFieldName = False
    BrQueryCode = 0
    BrDicionario = DmDicion.BrvDicionario
    BrType = VqNormal
    BrUserCode = 0
    Left = 16
    Top = 328
  end
  object DcMenu: TBrvProvider
    DataSet = QcMenu
    Options = [poAllowCommandText, poUseQuoteChar]
    Left = 52
    Top = 328
  end
  object QcPerMenGru: TBrvDataSet
    GetMetadata = False
    CommandText = 'select * from S001 where cdusuari is null'
    DbxCommandType = 'Dbx.SQL'
    MaxBlobSize = -1
    Params = <>
    SQLConnection = SqlConnSis
    BrShowFieldName = False
    BrQueryCode = 0
    BrDicionario = DmDicion.BrvDicionario
    BrType = VqNormal
    BrUserCode = 0
    Left = 16
    Top = 373
  end
  object DcPerMenGru: TBrvProvider
    DataSet = QcPerMenGru
    Options = [poAllowCommandText, poUseQuoteChar]
    Left = 44
    Top = 373
  end
  object QcPerMenUsu: TBrvDataSet
    GetMetadata = False
    CommandText = 'select * from S001 where cdusuari is null'
    DbxCommandType = 'Dbx.SQL'
    MaxBlobSize = -1
    Params = <>
    SQLConnection = SqlConnSis
    BrShowFieldName = False
    BrQueryCode = 0
    BrDicionario = DmDicion.BrvDicionario
    BrType = VqNormal
    BrUserCode = 0
    Left = 16
    Top = 421
  end
  object DcPerMenUsu: TBrvProvider
    DataSet = QcPerMenUsu
    Options = [poAllowCommandText, poUseQuoteChar]
    Left = 44
    Top = 421
  end
  object QcPerAtrGru: TBrvDataSet
    GetMetadata = False
    CommandText = 'select * from S001 where cdusuari is null'
    DbxCommandType = 'Dbx.SQL'
    MaxBlobSize = -1
    Params = <>
    SQLConnection = SqlConnSis
    BrShowFieldName = False
    BrQueryCode = 0
    BrDicionario = DmDicion.BrvDicionario
    BrType = VqNormal
    BrUserCode = 0
    Left = 16
    Top = 469
  end
  object DcPerAtrGru: TBrvProvider
    DataSet = QcPerAtrGru
    Options = [poAllowCommandText, poUseQuoteChar]
    Left = 44
    Top = 469
  end
  object QcPerAtrUsu: TBrvDataSet
    GetMetadata = False
    CommandText = 'select * from S001 where cdusuari is null'
    DbxCommandType = 'Dbx.SQL'
    MaxBlobSize = -1
    Params = <>
    SQLConnection = SqlConnSis
    BrShowFieldName = False
    BrQueryCode = 0
    BrDicionario = DmDicion.BrvDicionario
    BrType = VqNormal
    BrUserCode = 0
    Left = 16
    Top = 517
  end
  object DcPerAtrUsu: TBrvProvider
    DataSet = QcPerAtrUsu
    Options = [poAllowCommandText, poUseQuoteChar]
    Left = 44
    Top = 517
  end
  object QcImport: TBrvDataSet
    GetMetadata = False
    CommandText = 'select * from S001 where cdusuari is null'
    DbxCommandType = 'Dbx.SQL'
    MaxBlobSize = -1
    Params = <>
    SQLConnection = SqlConnSis
    BrShowFieldName = False
    BrQueryCode = 0
    BrDicionario = DmDicion.BrvDicionario
    BrType = VqNormal
    BrUserCode = 0
    Left = 312
    Top = 488
  end
  object DpImport: TBrvProvider
    DataSet = QcImport
    Options = [poAllowCommandText, poUseQuoteChar]
    Left = 344
    Top = 488
  end
  object DcView: TBrvProvider
    DataSet = QcView
    Options = [poAllowCommandText, poUseQuoteChar]
    Left = 188
    Top = 408
  end
  object QcView: TBrvDataSet
    GetMetadata = False
    CommandText = 'select * from S001 where cdusuari is null'
    DbxCommandType = 'Dbx.SQL'
    MaxBlobSize = -1
    Params = <>
    SQLConnection = SqlConnSis
    BrShowFieldName = False
    BrQueryCode = 0
    BrDicionario = DmDicion.BrvDicionario
    BrType = VqNormal
    BrUserCode = 0
    Left = 160
    Top = 408
  end
  object QcS045: TBrvDataSet
    GetMetadata = False
    CommandText = 'select * from S014 where nmtabela is null'
    DbxCommandType = 'Dbx.SQL'
    MaxBlobSize = -1
    Params = <>
    SQLConnection = SqlConnSis
    BrShowFieldName = False
    BrQueryCode = 0
    BrDicionario = DmDicion.BrvDicionario
    BrType = VqNormal
    BrUserCode = 0
    Left = 280
    Top = 402
  end
  object DcS045: TBrvProvider
    DataSet = QcS045
    Options = [poAllowCommandText, poUseQuoteChar]
    Left = 308
    Top = 402
  end
  object QpS045: TBrvClientDataSet
    Aggregates = <>
    Params = <>
    ProviderName = 'DcS045'
    BrShowFieldName = False
    BrQueryCode = 0
    BrDicionario = DmDicion.BrvDicionario
    BrType = VqNormal
    BrGravaLog = True
    BrFormName = 'SdmSis'
    BrUserCode = 0
    Left = 338
    Top = 402
  end
  object DcValAtr: TBrvProvider
    DataSet = QcValAtr
    Options = [poAllowCommandText, poUseQuoteChar]
    Left = 188
    Top = 472
  end
  object QcValAtr: TBrvDataSet
    GetMetadata = False
    CommandText = 'select * from S001 where cdusuari is null'
    DbxCommandType = 'Dbx.SQL'
    MaxBlobSize = -1
    Params = <>
    SQLConnection = SqlConnSis
    BrShowFieldName = False
    BrQueryCode = 0
    BrDicionario = DmDicion.BrvDicionario
    BrType = VqNormal
    BrUserCode = 0
    Left = 160
    Top = 472
  end
  object QcApoAten: TBrvDataSet
    GetMetadata = False
    CommandText = 
      'Select S049.NrAltera, S049.dtaltera, S049.CdUsuari, S049.nratesa' +
      'b, S049.DsResumo, '#13#10'       S049.nrfordin, S049.nrseqfor, S049.tp' +
      'formul, S049.txaltera,'#13#10'       Coalesce(S014.DSFORDIN, S006.DSFO' +
      'RMUL) As DsFormul, S001.NMCOMUSU'#13#10#13#10'From   S049 S049 '#13#10'       Le' +
      'ft Join S014 S014 on (S049.NRFORDIN = S014.NRFORDIN)'#13#10'       Lef' +
      't Join S006 S006 on (S049.NRSEQFOR = S006.NRSEQFOR And S049.TPFO' +
      'RMUL = S006.TPFORMUL)'#13#10'       Left Join S001 S001 on (S049.CDUSU' +
      'ARI = S001.CDUSUARI)'#13#10#13#10'Where  S049.cdusuari is null'
    DbxCommandType = 'Dbx.SQL'
    MaxBlobSize = -1
    Params = <>
    SQLConnection = SqlConnSis
    BrShowFieldName = False
    BrQueryCode = 0
    BrDicionario = DmDicion.BrvDicionario
    BrType = VqNormal
    BrUserCode = 0
    Left = 312
    Top = 232
  end
  object DcApoAten: TBrvProvider
    DataSet = QcApoAten
    Options = [poAllowCommandText, poUseQuoteChar]
    Left = 344
    Top = 232
  end
  object QcClaRNC: TBrvDataSet
    GetMetadata = False
    DbxCommandType = 'Dbx.SQL'
    MaxBlobSize = -1
    Params = <>
    SQLConnection = SqlConnSis
    BrShowFieldName = False
    BrQueryCode = 0
    BrDicionario = DmDicion.BrvDicionario
    BrType = VqNormal
    BrUserCode = 0
    Left = 160
    Top = 528
  end
  object DcClaRNC: TBrvProvider
    DataSet = QcClaRNC
    Options = [poAllowCommandText, poUseQuoteChar]
    Left = 204
    Top = 528
  end
end
