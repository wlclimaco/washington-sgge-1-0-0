inherited DmTar004: TDmTar004
  OldCreateOrder = True
  Height = 191
  Width = 254
  object pCpXML: TBrvClientDataSet
    Aggregates = <>
    Params = <>
    ProviderName = 'DcXML'
    BrShowFieldName = False
    BrQueryCode = 0
    BrDicionario = DmDicion.BrvDicionario
    BrType = VqNormal
    BrGravaLog = False
    BrUserCode = 0
    Left = 104
    Top = 128
  end
  object BrvString: TBrvString
    Left = 56
    Top = 40
  end
  object ACBrNFe: TACBrNFe
    Configuracoes.Geral.FormaEmissao = teContingencia
    Configuracoes.Geral.PathSalvar = 'C:\Program Files\Borland\Delphi7\Bin\'
    Configuracoes.Geral.PathSchemas = 
      'C:\Sistema\Componente\Componente_ACBR\Exemplos\ACBrNFe2\Delphi\S' +
      'chemas'
    Configuracoes.WebServices.UF = 'SP'
    Configuracoes.WebServices.AguardarConsultaRet = 15000
    Configuracoes.WebServices.IntervaloTentativas = 1000
    Configuracoes.WebServices.AjustaAguardaConsultaRet = True
    Configuracoes.Certificados.NumeroSerie = '2A1D452AD1171260CC95E2A61F686E79'
    Configuracoes.Certificados.Senha = '1234567890'
    Left = 147
    Top = 81
  end
  object DcXML: TBrvProvider
    DataSet = QcXML
    Options = [poAllowCommandText, poUseQuoteChar]
    Left = 68
    Top = 120
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
    Left = 24
    Top = 120
  end
  object CpConChv: TBrvClientDataSet
    Aggregates = <>
    Params = <>
    ProviderName = 'DcXML'
    BrShowFieldName = False
    BrQueryCode = 224
    BrDicionario = DmDicion.BrvDicionario
    BrType = VqNormal
    BrGravaLog = False
    BrUserCode = 0
    Left = 152
    Top = 128
  end
  object CPEmpresa: TBrvClientDataSet
    Aggregates = <>
    Params = <>
    BrShowFieldName = False
    BrQueryCode = 0
    BrDicionario = DmDicion.BrvDicionario
    BrType = VqNormal
    BrGravaLog = True
    BrUserCode = 0
    Left = 200
    Top = 88
  end
  object CPChvAce: TBrvClientDataSet
    Aggregates = <>
    Params = <>
    BrShowFieldName = False
    BrQueryCode = 226
    BrDicionario = DmDicion.BrvDicionario
    BrType = VqNormal
    BrGravaLog = True
    BrUserCode = 0
    Left = 144
    Top = 24
  end
  object CpXML: TBrvClientDataSet
    Aggregates = <>
    Params = <>
    ProviderName = 'DcXML'
    BrShowFieldName = False
    BrQueryCode = 0
    BrDicionario = DmDicion.BrvDicionario
    BrType = VqNormal
    BrGravaLog = False
    BrUserCode = 0
    Left = 96
    Top = 72
  end
end
