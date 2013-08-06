inherited DmTar002: TDmTar002
  OldCreateOrder = True
  Height = 229
  Width = 320
  object IdMessage: TIdMessage
    AttachmentEncoding = 'MIME'
    BccList = <>
    CCList = <>
    Encoding = meMIME
    FromList = <
      item
      end>
    Recipients = <>
    ReplyTo = <>
    ConvertPreamble = True
    Left = 16
    Top = 8
  end
  object IdPOP3: TIdPOP3
    AutoLogin = True
    SASLMechanisms = <>
    Left = 64
    Top = 8
  end
  object CpXML: TBrvClientDataSet
    Aggregates = <>
    Params = <>
    ProviderName = 'DcXML'
    BrShowFieldName = False
    BrQueryCode = 165
    BrDicionario = DmDicion.BrvDicionario
    BrType = VqNormal
    BrGravaLog = False
    BrUserCode = 0
    Left = 72
    Top = 56
  end
  object DcXML: TBrvProvider
    DataSet = QcXML
    Options = [poAllowCommandText, poUseQuoteChar]
    Left = 44
    Top = 56
  end
  object QcXML: TBrvDataSet
    GetMetadata = False
    NumericMapping = True
    DbxCommandType = 'Dbx.SQL'
    MaxBlobSize = -1
    Params = <>
    SQLConnection = DmDicion.SqlDicion
    BrShowFieldName = False
    BrQueryCode = 0
    BrDicionario = DmDicion.BrvDicionario
    BrType = VqNormal
    BrUserCode = 0
    Left = 16
    Top = 56
  end
  object BrvString: TBrvString
    Left = 104
    Top = 56
  end
  object IdSMTP: TIdSMTP
    Port = 587
    SASLMechanisms = <>
    Left = 128
    Top = 8
  end
end
