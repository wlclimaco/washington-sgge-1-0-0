object Form1: TForm1
  Left = 224
  Top = 177
  Width = 882
  Height = 592
  Caption = 'ACBrNFe'
  Color = clBtnFace
  Font.Charset = DEFAULT_CHARSET
  Font.Color = clWindowText
  Font.Height = -11
  Font.Name = 'MS Sans Serif'
  Font.Style = []
  OldCreateOrder = False
  OnCreate = FormCreate
  PixelsPerInch = 96
  TextHeight = 13
  object Panel2: TPanel
    Left = 0
    Top = 0
    Width = 874
    Height = 558
    Align = alClient
    TabOrder = 0
    object Panel3: TPanel
      Left = 1
      Top = 1
      Width = 872
      Height = 192
      Align = alTop
      TabOrder = 0
      object btnImprimir: TButton
        Left = 16
        Top = 36
        Width = 177
        Height = 25
        Caption = 'Imprimir'
        TabOrder = 1
        OnClick = btnImprimirClick
      end
      object btnValidarXML: TButton
        Left = 17
        Top = 6
        Width = 177
        Height = 25
        Caption = 'Validar XML'
        TabOrder = 0
        OnClick = btnValidarXMLClick
      end
      object btnImportarXML: TButton
        Left = 17
        Top = 66
        Width = 177
        Height = 25
        Caption = 'Importar XML'
        TabOrder = 2
        OnClick = btnImportarXMLClick
      end
    end
    object PageControl2: TPageControl
      Left = 1
      Top = 193
      Width = 872
      Height = 364
      ActivePage = TabSheet9
      Align = alClient
      TabOrder = 1
      object TabSheet5: TTabSheet
        Caption = 'Respostas'
        object MemoResp: TMemo
          Left = 0
          Top = 0
          Width = 861
          Height = 336
          Align = alClient
          TabOrder = 0
        end
      end
      object TabSheet6: TTabSheet
        Caption = 'XML Resposta'
        ImageIndex = 1
        object WBResposta: TWebBrowser
          Left = 0
          Top = 0
          Width = 861
          Height = 336
          Align = alClient
          TabOrder = 0
          ControlData = {
            4C000000FD580000BA2200000000000000000000000000000000000000000000
            000000004C000000000000000000000001000000E0D057007335CF11AE690800
            2B2E126208000000000000004C0000000114020000000000C000000000000046
            8000000000000000000000000000000000000000000000000000000000000000
            00000000000000000100000000000000000000000000000000000000}
        end
      end
      object TabSheet8: TTabSheet
        Caption = 'Log'
        ImageIndex = 2
        object memoLog: TMemo
          Left = 0
          Top = 0
          Width = 861
          Height = 336
          Align = alClient
          ScrollBars = ssVertical
          TabOrder = 0
        end
      end
      object TabSheet9: TTabSheet
        Caption = 'NFe'
        ImageIndex = 3
        object trvwNFe: TTreeView
          Left = 0
          Top = 0
          Width = 864
          Height = 336
          Align = alClient
          Indent = 19
          TabOrder = 0
        end
      end
    end
  end
  object OpenDialog1: TOpenDialog
    DefaultExt = '*-nfe.XML'
    Filter = 
      'Arquivos NFE (*-nfe.XML)|*-nfe.XML|Arquivos XML (*.XML)|*.XML|To' +
      'dos os Arquivos (*.*)|*.*'
    Title = 'Selecione a NFe'
    Left = 672
    Top = 152
  end
  object ACBrNFe1: TACBrNFe
    Configuracoes.Geral.FormaEmissao = teContingencia
    Configuracoes.Geral.PathSalvar = 'C:\Program Files\Borland\Delphi7\Bin\'
    Configuracoes.WebServices.UF = 'SP'
    Configuracoes.WebServices.AguardarConsultaRet = 15000
    Configuracoes.WebServices.IntervaloTentativas = 1000
    Configuracoes.WebServices.AjustaAguardaConsultaRet = True
    Configuracoes.Arquivos.Salvar = True
    Configuracoes.Arquivos.PastaMensal = True
    Configuracoes.Arquivos.AdicionarLiteral = True
    Configuracoes.Arquivos.EmissaoPathNFe = True
    Configuracoes.Arquivos.PathNFe = 'D:\delphi\ACBr\trunk\ACBrNFePCN\ACBrNFeMonitor\Arqs'
    Configuracoes.Arquivos.PathCan = 'D:\delphi\ACBr\trunk\ACBrNFePCN\ACBrNFeMonitor\Arqs'
    Configuracoes.Arquivos.PathInu = 'D:\delphi\ACBr\trunk\ACBrNFePCN\ACBrNFeMonitor\Arqs'
    Configuracoes.Arquivos.PathDPEC = 'D:\delphi\ACBr\trunk\ACBrNFePCN\ACBrNFeMonitor\Arqs'
    OnStatusChange = ACBrNFe1StatusChange
    DANFE = ACBrNFeDANFERave1
    OnGerarLog = ACBrNFe1GerarLog
    Left = 707
    Top = 153
  end
  object ACBrNFeDANFERave1: TACBrNFeDANFERave
    ACBrNFe = ACBrNFe1
    PathPDF = 'C:\Program Files\Borland\Delphi7\Bin\'
    ImprimirHoraSaida = False
    MostrarPreview = True
    MostrarStatus = True
    TipoDANFE = tiRetrato
    NumCopias = 1
    ImprimirDescPorc = True
    ImprimirTotalLiquido = False
    MargemInferior = 0.800000000000000000
    MargemSuperior = 0.800000000000000000
    MargemEsquerda = 0.600000000000000000
    MargemDireita = 0.510000000000000000
    CasasDecimais._qCom = 2
    CasasDecimais._vUnCom = 2
    ExibirResumoCanhoto = False
    FormularioContinuo = False
    TamanhoFonte_DemaisCampos = 10
    ProdutosPorPagina = 0
    RavFile = 
      'D:\delphi\ACBr\trunk\Exemplos\ACBrNFe\Delphi\Report\NotaFiscalEl' +
      'etronica.rav'
    EspessuraBorda = 1
    Left = 742
    Top = 153
  end
  object Database1: TDatabase
    AliasName = 'laluna'
    Connected = True
    DatabaseName = 'laluna3'
    LoginPrompt = False
    SessionName = 'Default'
    Left = 793
    Top = 25
  end
  object EntradasItens: TTable
    Active = True
    CachedUpdates = True
    DatabaseName = 'laluna3'
    TableName = 'nfentradasitens.db'
    UpdateObject = UpdateSQL1
    Left = 801
    Top = 65
    object EntradasItensDcnumero: TFloatField
      FieldName = 'Dcnumero'
    end
    object EntradasItensDcserie: TStringField
      FieldName = 'Dcserie'
      Size = 3
    end
    object EntradasItensDcordem: TStringField
      FieldName = 'Dcordem'
      Size = 2
    end
    object EntradasItensDctipo: TStringField
      FieldName = 'Dctipo'
      Size = 6
    end
    object EntradasItensCdfornecedor: TFloatField
      FieldName = 'Cdfornecedor'
    end
    object EntradasItensCdproduto: TFloatField
      FieldName = 'Cdproduto'
    end
    object EntradasItensQuantidade: TFloatField
      FieldName = 'Quantidade'
    end
    object EntradasItensIpi: TCurrencyField
      FieldName = 'Ipi'
    end
    object EntradasItensSt: TCurrencyField
      FieldName = 'St'
    end
    object EntradasItensIcms: TCurrencyField
      FieldName = 'Icms'
    end
    object EntradasItensVlunitario: TCurrencyField
      FieldName = 'Vlunitario'
    end
    object EntradasItensVldesconto: TCurrencyField
      FieldName = 'Vldesconto'
    end
  end
  object Entradas: TTable
    Active = True
    CachedUpdates = True
    DatabaseName = 'laluna3'
    TableName = 'nfentradas.DB'
    UpdateObject = UpdateSQL2
    Left = 769
    Top = 65
    object EntradasDcnumero: TFloatField
      FieldName = 'Dcnumero'
    end
    object EntradasDcserie: TStringField
      FieldName = 'Dcserie'
      Size = 3
    end
    object EntradasDcordem: TStringField
      FieldName = 'Dcordem'
      Size = 2
    end
    object EntradasDctipo: TStringField
      FieldName = 'Dctipo'
      Size = 6
    end
    object EntradasVlnota: TCurrencyField
      FieldName = 'Vlnota'
    end
    object EntradasVlicms: TCurrencyField
      FieldName = 'Vlicms'
    end
    object EntradasVlipi: TCurrencyField
      FieldName = 'Vlipi'
    end
    object EntradasVlfrete: TCurrencyField
      FieldName = 'Vlfrete'
    end
    object EntradasCdfornecedor: TFloatField
      FieldName = 'Cdfornecedor'
    end
    object EntradasCfop: TFloatField
      FieldName = 'Cfop'
    end
    object EntradasDtentrada: TDateField
      FieldName = 'Dtentrada'
    end
    object EntradasDtemissao: TDateField
      FieldName = 'Dtemissao'
    end
    object EntradasTransportado: TFloatField
      FieldName = 'Transportado'
    end
    object EntradasCdpedido: TFloatField
      FieldName = 'Cdpedido'
    end
    object EntradasNatureza: TFloatField
      FieldName = 'Natureza'
    end
    object EntradasIdnfentradas: TIntegerField
      FieldName = 'Idnfentradas'
    end
    object EntradasVlst: TCurrencyField
      FieldName = 'Vlst'
    end
    object EntradasTpsituacao: TStringField
      FieldName = 'Tpsituacao'
      Size = 1
    end
  end
  object Query1: TQuery
    DatabaseName = 'laluna3'
    Left = 729
    Top = 65
  end
  object UpdateSQL1: TUpdateSQL
    ModifySQL.Strings = (
      'update "nfentradasitens.db"'
      'set'
      '  Dcnumero = :Dcnumero,'
      '  Dcserie = :Dcserie,'
      '  Dcordem = :Dcordem,'
      '  Dctipo = :Dctipo,'
      '  Cdfornecedor = :Cdfornecedor,'
      '  Cdproduto = :Cdproduto,'
      '  Quantidade = :Quantidade,'
      '  Ipi = :Ipi,'
      '  St = :St,'
      '  Icms = :Icms,'
      '  Vlunitario = :Vlunitario,'
      '  Vldesconto = :Vldesconto'
      'where'
      '  Dcnumero = :OLD_Dcnumero and'
      '  Dcserie = :OLD_Dcserie and'
      '  Dcordem = :OLD_Dcordem and'
      '  Dctipo = :OLD_Dctipo and'
      '  Cdfornecedor = :OLD_Cdfornecedor and'
      '  Cdproduto = :OLD_Cdproduto and'
      '  Quantidade = :OLD_Quantidade and'
      '  Ipi = :OLD_Ipi and'
      '  St = :OLD_St and'
      '  Icms = :OLD_Icms and'
      '  Vlunitario = :OLD_Vlunitario and'
      '  Vldesconto = :OLD_Vldesconto')
    InsertSQL.Strings = (
      'insert into "nfentradasitens.db"'
      
        '  (Dcnumero, Dcserie, Dcordem, Dctipo, Cdfornecedor, Cdproduto, ' +
        'Quantidade, '
      '   Ipi, St, Icms, Vlunitario, Vldesconto)'
      'values'
      
        '  (:Dcnumero, :Dcserie, :Dcordem, :Dctipo, :Cdfornecedor, :Cdpro' +
        'duto, :Quantidade, '
      '   :Ipi, :St, :Icms, :Vlunitario, :Vldesconto)')
    DeleteSQL.Strings = (
      'delete from "nfentradasitens.db"'
      'where'
      '  Dcnumero = :OLD_Dcnumero and'
      '  Dcserie = :OLD_Dcserie and'
      '  Dcordem = :OLD_Dcordem and'
      '  Dctipo = :OLD_Dctipo and'
      '  Cdfornecedor = :OLD_Cdfornecedor and'
      '  Cdproduto = :OLD_Cdproduto and'
      '  Quantidade = :OLD_Quantidade and'
      '  Ipi = :OLD_Ipi and'
      '  St = :OLD_St and'
      '  Icms = :OLD_Icms and'
      '  Vlunitario = :OLD_Vlunitario and'
      '  Vldesconto = :OLD_Vldesconto')
    Left = 801
    Top = 97
  end
  object UpdateSQL2: TUpdateSQL
    ModifySQL.Strings = (
      'update "nfentradas.DB"'
      'set'
      '  Dcnumero = :Dcnumero,'
      '  Dcserie = :Dcserie,'
      '  Dcordem = :Dcordem,'
      '  Dctipo = :Dctipo,'
      '  Vlnota = :Vlnota,'
      '  Vlicms = :Vlicms,'
      '  Vlipi = :Vlipi,'
      '  Vlfrete = :Vlfrete,'
      '  Cdfornecedor = :Cdfornecedor,'
      '  Cfop = :Cfop,'
      '  Dtentrada = :Dtentrada,'
      '  Dtemissao = :Dtemissao,'
      '  Transportado = :Transportado,'
      '  Cdpedido = :Cdpedido,'
      '  Natureza = :Natureza,'
      '  Idnfentradas = :Idnfentradas,'
      '  Vlst = :Vlst,'
      '  Tpsituacao = :Tpsituacao'
      'where'
      '  Dcnumero = :OLD_Dcnumero')
    InsertSQL.Strings = (
      'insert into "nfentradas.DB"'
      
        '  (Dcnumero, Dcserie, Dcordem, Dctipo, Vlnota, Vlicms, Vlipi, Vl' +
        'frete, '
      
        '   Cdfornecedor, Cfop, Dtentrada, Dtemissao, Transportado, Cdped' +
        'ido, Natureza, '
      '   Idnfentradas, Vlst, Tpsituacao)'
      'values'
      
        '  (:Dcnumero, :Dcserie, :Dcordem, :Dctipo, :Vlnota, :Vlicms, :Vl' +
        'ipi, :Vlfrete, '
      
        '   :Cdfornecedor, :Cfop, :Dtentrada, :Dtemissao, :Transportado, ' +
        ':Cdpedido, '
      '   :Natureza, :Idnfentradas, :Vlst, :Tpsituacao)')
    DeleteSQL.Strings = (
      'delete from "nfentradas.DB"'
      'where'
      '  Dcnumero = :OLD_Dcnumero')
    Left = 769
    Top = 97
  end
end
