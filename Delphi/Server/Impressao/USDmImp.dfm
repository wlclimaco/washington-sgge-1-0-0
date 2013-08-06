object SDmImp: TSDmImp
  OldCreateOrder = False
  Height = 601
  Width = 525
  object QcTabela: TBrvDataSet
    GetMetadata = False
    CommandText = 'select * from S001 where cdusuari is null'
    DbxCommandType = 'Dbx.SQL'
    MaxBlobSize = -1
    Params = <>
    SQLConnection = SqlConnImp
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
  object SqlConnImp: TBrvConnection
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
    AfterConnect = SqlConnImpAfterConnect
    Left = 46
    Top = 6
  end
  object QExecute: TBrvDataSet
    GetMetadata = False
    CommandText = 'select * from S001 where cdusuari is null'
    DbxCommandType = 'Dbx.SQL'
    MaxBlobSize = -1
    Params = <>
    SQLConnection = SqlConnImp
    BrShowFieldName = False
    BrQueryCode = 0
    BrDicionario = DmDicion.BrvDicionario
    BrType = VqNormal
    BrUserCode = 0
    Left = 400
    Top = 8
  end
  object CdsPDF: TClientDataSet
    Aggregates = <>
    Params = <>
    Left = 144
    Top = 160
    object CdsPDFBiRelato: TBlobField
      FieldName = 'BiRelato'
    end
  end
  object CdsNmRelat: TClientDataSet
    Aggregates = <>
    Params = <>
    Left = 144
    Top = 208
  end
end
