object DmFis: TDmFis
  OldCreateOrder = False
  Height = 190
  Width = 281
  object ACBrNFe: TACBrNFe
    Configuracoes.Geral.PathSalvar = 'C:\Arquivos de programas\Embarcadero\RAD Studio\8.0\bin\'
    Configuracoes.WebServices.UF = 'SP'
    Configuracoes.WebServices.AguardarConsultaRet = 0
    Configuracoes.WebServices.IntervaloTentativas = 0
    Configuracoes.WebServices.AjustaAguardaConsultaRet = False
    Left = 16
    Top = 16
  end
  object BrvXML: TBrvXML
    BrCdsNfeDet = QpXML
    BrCdsNfeFat = CcNfeFat
    BrCdsNfePro = CcNFeProd
    BrGerarBanco = True
    BrQtdePagDanfe = 0
    Left = 56
    Top = 14
  end
  object QpXML: TBrvClientDataSet
    Aggregates = <>
    Params = <>
    BrShowFieldName = False
    BrQueryCode = 0
    BrDicionario = DmSrvApl.BrvDicionario
    BrType = VqNormal
    BrGravaLog = False
    BrUserCode = 0
    Left = 16
    Top = 70
  end
  object CcNFeProd: TBrvClientDataSet
    Aggregates = <>
    Params = <>
    BrShowFieldName = False
    BrQueryCode = 0
    BrDicionario = DmSrvApl.BrvDicionario
    BrType = VqNormal
    BrGravaLog = False
    BrUserCode = 0
    Left = 16
    Top = 118
  end
  object CcNfeFat: TBrvClientDataSet
    Aggregates = <>
    Params = <>
    BrShowFieldName = False
    BrQueryCode = 0
    BrDicionario = DmSrvApl.BrvDicionario
    BrType = VqNormal
    BrGravaLog = False
    BrUserCode = 0
    Left = 56
    Top = 70
  end
  object gBrvString: TBrvString
    Left = 120
    Top = 32
  end
end
