inherited DmTar003: TDmTar003
  OldCreateOrder = True
  Height = 179
  Width = 254
  object pCpXML: TBrvClientDataSet
    Aggregates = <>
    Params = <>
    ProviderName = 'DcXML'
    BrShowFieldName = False
    BrQueryCode = 224
    BrDicionario = DmDicion.BrvDicionario
    BrType = VqNormal
    BrGravaLog = False
    BrUserCode = 0
    Left = 72
    Top = 56
  end
  object BrvString: TBrvString
    Left = 80
    Top = 16
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
    Left = 139
    Top = 17
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
    Left = 96
    Top = 56
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
    Left = 120
    Top = 56
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
    Left = 72
    Top = 56
  end
end
