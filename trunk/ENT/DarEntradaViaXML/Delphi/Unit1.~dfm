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
      Height = 48
      Align = alTop
      TabOrder = 0
      object btnImprimir: TButton
        Left = 200
        Top = 12
        Width = 177
        Height = 25
        Caption = 'Imprimir'
        TabOrder = 1
        OnClick = btnImprimirClick
      end
      object btnValidarXML: TButton
        Left = 17
        Top = 14
        Width = 177
        Height = 25
        Caption = 'Validar XML'
        TabOrder = 0
        OnClick = btnValidarXMLClick
      end
      object btnImportarXML: TButton
        Left = 385
        Top = 10
        Width = 177
        Height = 26
        Caption = 'Importar XML'
        TabOrder = 2
        OnClick = btnImportarXMLClick
      end
    end
    object PageControl2: TPageControl
      Left = 1
      Top = 472
      Width = 872
      Height = 85
      ActivePage = TabSheet9
      Align = alBottom
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
          Height = 57
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
    Left = 737
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
    Left = 697
    Top = 97
  end
  object Entradas: TIBTable
    Database = IBDatabase1
    Transaction = IBTransaction1
    Active = True
    BufferChunks = 1000
    CachedUpdates = True
    FieldDefs = <
      item
        Name = 'DCNUMERO'
        Attributes = [faRequired]
        DataType = ftInteger
      end
      item
        Name = 'DCSERIE'
        Attributes = [faRequired]
        DataType = ftString
        Size = 3
      end
      item
        Name = 'DCORDEM'
        Attributes = [faRequired]
        DataType = ftString
        Size = 2
      end
      item
        Name = 'DCTIPO'
        Attributes = [faRequired]
        DataType = ftString
        Size = 6
      end
      item
        Name = 'NATUREZA'
        DataType = ftInteger
      end
      item
        Name = 'VLNOTA'
        DataType = ftFloat
      end
      item
        Name = 'VLICMS'
        DataType = ftFloat
      end
      item
        Name = 'VLIPI'
        DataType = ftFloat
      end
      item
        Name = 'VLFRETE'
        DataType = ftFloat
      end
      item
        Name = 'CFOP'
        DataType = ftFloat
      end
      item
        Name = 'DTENTRADA'
        DataType = ftDate
      end
      item
        Name = 'DTEMISSAO'
        DataType = ftDate
      end
      item
        Name = 'TRANSPORTADO'
        DataType = ftFloat
      end
      item
        Name = 'CDPEDIDO'
        DataType = ftFloat
      end
      item
        Name = 'IDNFENTRADAS'
        DataType = ftInteger
      end
      item
        Name = 'VLST'
        DataType = ftFloat
      end
      item
        Name = 'TPSITUACAO'
        DataType = ftString
        Size = 1
      end
      item
        Name = 'CDFORNECEDOR'
        DataType = ftInteger
      end>
    IndexDefs = <
      item
        Name = 'RDB$PRIMARY27'
        Fields = 'DCNUMERO;DCSERIE;DCORDEM;DCTIPO'
        Options = [ixPrimary, ixUnique]
      end
      item
        Name = 'RDB$FOREIGN32'
        Fields = 'NATUREZA'
      end>
    StoreDefs = True
    TableName = 'NFENTRADAS'
    Left = 697
    Top = 33
    object EntradasDCNUMERO: TIntegerField
      FieldName = 'DCNUMERO'
    end
    object EntradasDCSERIE: TIBStringField
      FieldName = 'DCSERIE'
      Size = 3
    end
    object EntradasDCORDEM: TIBStringField
      FieldName = 'DCORDEM'
      Size = 2
    end
    object EntradasDCTIPO: TIBStringField
      FieldName = 'DCTIPO'
      Size = 6
    end
    object EntradasNATUREZA: TIntegerField
      FieldName = 'NATUREZA'
    end
    object EntradasVLNOTA: TFloatField
      FieldName = 'VLNOTA'
    end
    object EntradasVLICMS: TFloatField
      FieldName = 'VLICMS'
    end
    object EntradasVLIPI: TFloatField
      FieldName = 'VLIPI'
    end
    object EntradasVLFRETE: TFloatField
      FieldName = 'VLFRETE'
    end
    object EntradasCFOP: TFloatField
      FieldName = 'CFOP'
    end
    object EntradasDTENTRADA: TDateField
      FieldName = 'DTENTRADA'
    end
    object EntradasDTEMISSAO: TDateField
      FieldName = 'DTEMISSAO'
    end
    object EntradasTRANSPORTADO: TFloatField
      FieldName = 'TRANSPORTADO'
    end
    object EntradasCDPEDIDO: TFloatField
      FieldName = 'CDPEDIDO'
    end
    object EntradasIDNFENTRADAS: TIntegerField
      FieldName = 'IDNFENTRADAS'
    end
    object EntradasVLST: TFloatField
      FieldName = 'VLST'
    end
    object EntradasTPSITUACAO: TIBStringField
      FieldName = 'TPSITUACAO'
      Size = 1
    end
    object EntradasCDFORNECEDOR: TIntegerField
      FieldName = 'CDFORNECEDOR'
    end
  end
  object EntradasItens: TIBTable
    Database = IBDatabase1
    Transaction = IBTransaction1
    Active = True
    BufferChunks = 1000
    CachedUpdates = True
    FieldDefs = <
      item
        Name = 'DCNUMERO'
        DataType = ftInteger
      end
      item
        Name = 'DCSERIE'
        DataType = ftString
        Size = 3
      end
      item
        Name = 'DCORDEM'
        DataType = ftString
        Size = 2
      end
      item
        Name = 'DCTIPO'
        DataType = ftString
        Size = 6
      end
      item
        Name = 'CODPRO'
        DataType = ftInteger
      end
      item
        Name = 'QUANTIDADE'
        DataType = ftFloat
      end
      item
        Name = 'IPI'
        DataType = ftFloat
      end
      item
        Name = 'ST'
        DataType = ftFloat
      end
      item
        Name = 'ICMS'
        DataType = ftFloat
      end
      item
        Name = 'VLUNITARIO'
        DataType = ftFloat
      end
      item
        Name = 'VLDESCONTO'
        DataType = ftFloat
      end
      item
        Name = 'CDFORNECEDOR'
        DataType = ftInteger
      end
      item
        Name = 'CDPRODUTO'
        DataType = ftInteger
      end>
    StoreDefs = True
    TableName = 'NFENTRADASITENS'
    Left = 737
    Top = 33
    object EntradasItensDCNUMERO: TIntegerField
      FieldName = 'DCNUMERO'
    end
    object EntradasItensDCSERIE: TIBStringField
      FieldName = 'DCSERIE'
      Size = 3
    end
    object EntradasItensDCORDEM: TIBStringField
      FieldName = 'DCORDEM'
      Size = 2
    end
    object EntradasItensDCTIPO: TIBStringField
      FieldName = 'DCTIPO'
      Size = 6
    end
    object EntradasItensCODPRO: TIntegerField
      FieldName = 'CODPRO'
    end
    object EntradasItensQUANTIDADE: TFloatField
      FieldName = 'QUANTIDADE'
    end
    object EntradasItensIPI: TFloatField
      FieldName = 'IPI'
    end
    object EntradasItensST: TFloatField
      FieldName = 'ST'
    end
    object EntradasItensICMS: TFloatField
      FieldName = 'ICMS'
    end
    object EntradasItensVLUNITARIO: TFloatField
      FieldName = 'VLUNITARIO'
    end
    object EntradasItensVLDESCONTO: TFloatField
      FieldName = 'VLDESCONTO'
    end
    object EntradasItensCDFORNECEDOR: TIntegerField
      FieldName = 'CDFORNECEDOR'
    end
    object EntradasItensCDPRODUTO: TIntegerField
      FieldName = 'CDPRODUTO'
    end
  end
  object Query1: TIBQuery
    Database = IBDatabase1
    Transaction = IBTransaction1
    BufferChunks = 1000
    CachedUpdates = True
    Left = 649
    Top = 9
  end
  object IBDatabase1: TIBDatabase
    Connected = True
    DatabaseName = 'C:\NOVA.GDB'
    Params.Strings = (
      'user_name=sysdba'
      'password=masterkey'
      'lc_ctype=ASCII')
    LoginPrompt = False
    IdleTimer = 0
    SQLDialect = 3
    TraceFlags = []
    Left = 577
    Top = 9
  end
  object IBTransaction1: TIBTransaction
    Active = True
    DefaultDatabase = IBDatabase1
    AutoStopAction = saNone
    Left = 609
    Top = 9
  end
end
