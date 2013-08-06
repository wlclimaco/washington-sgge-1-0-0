inherited Mov0015: TMov0015
  Caption = 'Mov0015 - Arquivo Track Trace Bayer'
  ClientHeight = 198
  ClientWidth = 662
  ExplicitWidth = 670
  ExplicitHeight = 225
  PixelsPerInch = 96
  TextHeight = 13
  inherited NavBarra: TBrvDbNavCop
    Width = 662
    ExplicitWidth = 662
    inherited BrvSpeedButton1: TBrvSpeedButton
      Left = 637
      ExplicitLeft = 617
    end
    inherited SbtAjuda: TBrvSpeedButton
      Left = 612
      ExplicitLeft = 592
    end
  end
  inherited PnlFundo: TPanel
    Width = 662
    Height = 168
    ExplicitWidth = 662
    ExplicitHeight = 168
    object Label2: TLabel
      Left = 5
      Top = 9
      Width = 55
      Height = 13
      Caption = 'Armaz'#233'm'
      Font.Charset = DEFAULT_CHARSET
      Font.Color = clBlue
      Font.Height = -11
      Font.Name = 'Tahoma'
      Font.Style = [fsBold]
      ParentFont = False
    end
    object Label1: TLabel
      Left = 5
      Top = 34
      Width = 50
      Height = 13
      Caption = 'Diret'#243'rio'
      Font.Charset = DEFAULT_CHARSET
      Font.Color = clBlue
      Font.Height = -11
      Font.Name = 'Tahoma'
      Font.Style = [fsBold]
      ParentFont = False
    end
    object Label22: TLabel
      Left = 4
      Top = 62
      Width = 60
      Height = 13
      Caption = 'Per'#237'odo de'
      Font.Charset = DEFAULT_CHARSET
      Font.Color = clBlue
      Font.Height = -11
      Font.Name = 'Tahoma'
      Font.Style = [fsBold]
      ParentFont = False
    end
    object Label3: TLabel
      Left = 201
      Top = 62
      Width = 20
      Height = 13
      Caption = 'At'#233
      Font.Charset = DEFAULT_CHARSET
      Font.Color = clBlue
      Font.Height = -11
      Font.Name = 'Tahoma'
      Font.Style = [fsBold]
      ParentFont = False
    end
    object Label4: TLabel
      Left = 5
      Top = 91
      Width = 24
      Height = 13
      Caption = 'Tipo'
      Font.Charset = DEFAULT_CHARSET
      Font.Color = clBlue
      Font.Height = -11
      Font.Name = 'Tahoma'
      Font.Style = [fsBold]
      ParentFont = False
    end
    object EdtCdArmaze: TBrvEdit
      Left = 74
      Top = 4
      Width = 100
      Height = 21
      TabOrder = 0
      BrVisibleButton = True
      BrFunctionButton = VeConsulta
      BrConfigurar.Strings = (
        'EdtCdArmaze;CdArmaze;S;S;'
        'EdtNmArmaze;NmArmaze;S;N;')
      BrAlignment = taLeftJustify
      BrDicionario = DmSrvApl.BrvDicionario
      BrvQueryCode = 42
      BrRecordar = False
    end
    object EdtNmArmaze: TBrvRetCon
      Left = 176
      Top = 4
      Width = 448
      Height = 21
      TabStop = False
      BevelInner = bvLowered
      BevelKind = bkFlat
      BevelWidth = 2
      BorderStyle = bsNone
      ParentColor = True
      ParentShowHint = False
      ReadOnly = True
      ShowHint = False
      TabOrder = 1
    end
    object EdtNmArquiv: TBrvEdit
      Left = 74
      Top = 31
      Width = 550
      Height = 21
      TabOrder = 2
      BrVisibleButton = True
      BrFunctionButton = VeDiretorio
      BrAlignment = taLeftJustify
      BrDicionario = DmSrvApl.BrvDicionario
      BrvQueryCode = 0
      BrRecordar = True
    end
    object EdtDtIni: TBrvEditDate
      Left = 74
      Top = 58
      Width = 121
      Height = 21
      EditMask = '99/99/9999;1; '
      MaxLength = 10
      TabOrder = 3
      Text = '  /  /    '
      BrDataValida = False
      BrDataVazia = True
      BrFunctionButton = TVdData
      BrAlignment = taLeftJustify
      BrDicionario = DmSrvApl.BrvDicionario
      BrRecordar = False
    end
    object EdtDtFim: TBrvEditDate
      Left = 227
      Top = 58
      Width = 121
      Height = 21
      EditMask = '99/99/9999;1; '
      MaxLength = 10
      TabOrder = 4
      Text = '  /  /    '
      BrDataValida = False
      BrDataVazia = True
      BrFunctionButton = TVdData
      BrAlignment = taLeftJustify
      BrDicionario = DmSrvApl.BrvDicionario
      BrRecordar = False
    end
    object BtnImport: TBrvBitBtn
      Left = 529
      Top = 125
      Width = 113
      Height = 30
      Hint = 'Exportar'
      Anchors = [akTop, akRight]
      Caption = '&Processar'
      DoubleBuffered = True
      Font.Charset = DEFAULT_CHARSET
      Font.Color = clWindowText
      Font.Height = -11
      Font.Name = 'Tahoma'
      Font.Style = [fsBold]
      Glyph.Data = {
        96090000424D9609000000000000360000002800000028000000140000000100
        18000000000060090000C40E0000C40E00000000000000000000008080008080
        0080800080800080800080800080800080800080800080800080800080800080
        8000808000808000808000808000808000808000808000808000808000808000
        8080008080008080008080008080008080008080008080008080008080008080
        0080800080800080800080800080800080800080800080800080800080800080
        8000808000808000808000808000808000808000808000808000808000808000
        8080008080008080008080008080008080008080008080008080008080008080
        0080800080800080800080800080800080800080800080800080800080800080
        8000808000808000808000808000808000808000808000808000808000BB6B00
        C367008080008080008080008080008080008080008080008080008080008080
        008080008080008080008080008080008080008080008080A9A6A6C3AAAA0080
        8000808000808000808000808000808000808000808000808000808000808000
        808000808000808000808000808000808001C26D00BC6D00B96C00BC69008080
        0080800080800080800080800080800080800080800080800080800080800080
        80008080008080008080008080B7ABABAAA7A7A5A4A4B1A6A600808000808000
        8080008080008080008080008080008080008080008080008080008080008080
        00808000808002BE7100BF6F00BC6D11C17800B86900BF670080800080800080
        8000808000808000808000808000808000808000808000808000808000808000
        8080A9A9A9ADA9A9A6A6A6ADADADA4A4A4BAA8A8008080008080008080008080
        00808000808000808000808000808000808000808000808000808007C87403C1
        7102BF723BCE9147D09611C07800B76800C06800808000808000808000808000
        8080008080008080008080008080008080008080008080BCB1B1B0ACACAAAAAA
        BBBBBBBDBDBDACACACA3A3A3B9A7A70080800080800080800080800080800080
        800080800080800080800080800080800EC67807C47500C07145D19846D19743
        D09646D09713C17900B86900BC68008080008080008080008080008080008080
        008080008080008080008080B2B0B0B1AEAEABABABC0C0C0BFBFBFBDBDBDBEBE
        BEADADADA3A2A2B2A7A700808000808000808000808000808000808000808000
        808000808015CA7910C8780DC4774DD49D52D59F4ED49D4DD29B4DD29B56D49F
        11C07800B76800C0680080800080800080800080800080800080800080800080
        80BAB5B5B6B2B2AFAFAFC3C3C3C4C4C4C2C2C2C1C1C1C1C1C1C3C3C3ACACACA3
        A2A2BAA8A800808000808000808000808000808000808000808000808016C17C
        0FC87750D59C64D9A85AD8A458D7A258D6A255D5A056D49F5CD6A21BC37E00B8
        6800BC690080800080800080800080800080800080800080809FAEAEB5B1B1C4
        C4C4C9C9C9C7C7C7C6C6C6C5C5C5C4C4C4C3C3C3C5C5C5AFAFAFA3A2A2B1A5A5
        0080800080800080800080800080800080800080800080801ED57B12C7795DD8
        A46ADCAC62D9A861D9A760D8A65ED7A55ED6A465D8A817C27C00B86900BF6700
        8080008080008080008080008080008080008080D1BCBCB2B1B1C8C8C8CDCDCD
        C8C8C8C9C9C9C7C7C7C7C7C7C6C6C6C8C8C8ADADADA3A3A3BAA8A80080800080
        800080800080800080800080800080800080801CD07B03C37066DBA975DEB16D
        DDAE6BDBAD69DAAC69D9AB6DDAAD69D9AA02BB6F00B96B00C068008080008080
        008080008080008080008080008080C5B9B9AFADADCBCBCBCFCFCFCDCDCDCDCD
        CDCBCBCBCBCBCBCACACACBCBCBA6A6A6A4A4A4B9A7A700808000808000808000
        80800080800080800080800080801BCF7A0FC77866DBA97DE0B674DDB273DDB1
        77DEB36BDBAC00BD7000BC6D00BA6D00BA6C00BC680080800080800080800080
        80008080008080008080C4B8B8B2B1B1CBCBCBD2D2D2CECECECECECECFCFCFCB
        CBCBA8A8A8A7A7A7A6A6A6A6A6A6B0A5A5008080008080008080008080008080
        0080800080800080801CD47B0BC6756FDEAE85E2BA80E0B973DEB103BF7200BE
        7002C37300BE7000BB6E00B96C00C26700808000808000808000808000808000
        8080008080D0BBBBB0AFAFCECECED4D4D4D3D3D3CFCFCFABABABA9A9A9ADADAD
        AAAAAAA7A7A7A5A5A5C0A9A90080800080800080800080800080800080800080
        8000808019CD7B0BC67573DEB085E2BA00C07104C17305C67702975A01BA6E01
        BF7000BB6E00C06A008080008080008080008080008080008080008080008080
        BFB6B6B1B0B0D0D0D0D4D4D4AAAAAAACACACB0B0B0868686A6A6A6AAAAAAA7A7
        A7B6A8A80080800080800080800080800080800080800080800080800080801C
        D47B18C87C1CC97F09C37508C27608D88200000000000002D07B00BC6E00C26C
        008080008080008080008080008080008080008080008080008080D1BCBCB4B4
        B4B4B4B4AEAEAEADADADC0C0C0000000000000B9B9B9A7A7A7B9ABAB00808000
        80800080800080800080800080800080800080800080800080801BCF7B13C779
        10C6790EC47709D07F023D25049C5D03C57501BD7001C46D0080800080800080
        80008080008080008080008080008080008080008080C5B9B9B3B2B2B1B1B1AF
        AFAFBBBBBB3737378B8B8BAFAFAFA9A9A9BAACAC008080008080008080008080
        0080800080800080800080800080800080800080801CCF7B14C87A10C6780DC4
        7709D07E06C77804C07302BE7102C46F00808000808000808000808000808000
        8080008080008080008080008080008080C4B8B8B3B2B2B1B1B1AFAFAFBABABA
        B2B2B2ABABABA9A9A9BBADAD0080800080800080800080800080800080800080
        800080800080800080800080800080801ED57C15C77A10C6790DC47709C27607
        C27504C07303C670008080008080008080008080008080008080008080008080
        008080008080008080008080D2BCBCB3B2B2B1B1B1AFAFAFADADADADADADABAB
        ABBDAFAF00808000808000808000808000808000808000808000808000808000
        80800080800080800080801BCE7C17CE7B12CC780EC9760AC87607C77406CD71
        0080800080800080800080800080800080800080800080800080800080800080
        80008080008080C1B8B8C2B7B7C0B6B6BCB2B2BCB2B2BAB0B0CCB4B400808000
        8080008080008080008080008080008080008080008080008080008080008080
        0080800080800080800080800080800080800080800080800080800080800080
        8000808000808000808000808000808000808000808000808000808000808000
        8080008080008080008080008080008080008080008080008080}
      NumGlyphs = 2
      ParentDoubleBuffered = False
      ParentFont = False
      ParentShowHint = False
      ShowHint = True
      TabOrder = 6
      OnClick = BtnImportClick
      BrTipoBotao = BrBtnExport
    end
    object CbxDsTipMov: TBrvComboBox
      Left = 74
      Top = 85
      Width = 120
      Height = 21
      ItemIndex = 0
      TabOrder = 5
      Text = 'Todos'
      Items.Strings = (
        'Todos'
        'Entradas'
        'Sa'#237'das')
      Values.Strings = (
        '1,2,3,4'
        '1,4'
        '2,3')
    end
    object CbxEnvWs: TCheckBox
      Left = 74
      Top = 112
      Width = 167
      Height = 17
      Caption = 'Enviar para WebService?'
      Font.Charset = DEFAULT_CHARSET
      Font.Color = clWindowText
      Font.Height = -11
      Font.Name = 'Tahoma'
      Font.Style = [fsBold]
      ParentFont = False
      TabOrder = 7
    end
    object CbxVisualiza: TCheckBox
      Left = 74
      Top = 135
      Width = 127
      Height = 17
      Caption = 'Visualizar arquivo?'
      Font.Charset = DEFAULT_CHARSET
      Font.Color = clWindowText
      Font.Height = -11
      Font.Name = 'Tahoma'
      Font.Style = [fsBold]
      ParentFont = False
      TabOrder = 8
    end
  end
  object CdsW008: TBrvClientDataSet
    Aggregates = <>
    Params = <>
    BrShowFieldName = False
    BrQueryCode = 0
    BrDicionario = DmSrvApl.BrvDicionario
    BrType = VqNormal
    BrGravaLog = True
    BrUserCode = 0
    Left = 424
    Top = 88
  end
  object HTTPRIO: THTTPRIO
    URL = 
      'https://www.projetodistribuicao.com.br/WSStatuspedido/wstracktra' +
      'ce.asmx'
    HTTPWebNode.Agent = 'Borland SOAP 1.2'
    HTTPWebNode.UseUTF8InHeader = True
    HTTPWebNode.InvokeOptions = [soIgnoreInvalidCerts, soAutoCheckAccessPointViaUDDI]
    HTTPWebNode.WebNodeOptions = []
    Converter.Options = [soSendMultiRefObj, soTryAllSchema, soRootRefNodesToBody, soCacheMimeResponse, soUTF8EncodeXML]
    Left = 488
    Top = 88
  end
  object BrvRelAsc1: TBrvRelAsc
    Linha = 0
    Pagina = 0
    PaginaInicial = 0
    LinhasPorPagina = 60
    ColunasPorLinha = Rel080Col
    GerarCabecalho = True
    Left = 520
    Top = 88
  end
  object BrvAlertProgress: TBrvAlertProgress
    Left = 424
    Top = 120
  end
end
