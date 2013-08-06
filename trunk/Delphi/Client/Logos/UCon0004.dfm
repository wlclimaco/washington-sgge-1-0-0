inherited Con0004: TCon0004
  Left = 0
  Top = 0
  Caption = 'Con0004'
  ClientHeight = 659
  ClientWidth = 1081
  WindowState = wsMaximized
  OnResize = FormResize
  ExplicitWidth = 1089
  ExplicitHeight = 686
  PixelsPerInch = 96
  TextHeight = 13
  object Label13: TLabel [0]
    Left = 11
    Top = 120
    Width = 69
    Height = 13
    Caption = 'Destinat'#225'rio'
    Font.Charset = DEFAULT_CHARSET
    Font.Color = clBlack
    Font.Height = -11
    Font.Name = 'MS Sans Serif'
    Font.Style = [fsBold]
    ParentFont = False
  end
  inherited PnlFundo: TPanel [1]
    Width = 1081
    Height = 629
    ExplicitWidth = 1081
    ExplicitHeight = 629
    object pnlMestre: TPanel
      Left = 1
      Top = 1
      Width = 1075
      Height = 232
      Align = alTop
      BevelEdges = [beBottom]
      BevelKind = bkSoft
      BevelOuter = bvNone
      Caption = ' '
      ParentBackground = False
      TabOrder = 0
      DesignSize = (
        1075
        230)
      object Label2: TLabel
        Left = 8
        Top = 6
        Width = 49
        Height = 13
        Caption = 'Empresa'
        Font.Charset = DEFAULT_CHARSET
        Font.Color = clBlack
        Font.Height = -11
        Font.Name = 'MS Sans Serif'
        Font.Style = [fsBold]
        ParentFont = False
      end
      object Label1: TLabel
        Left = 292
        Top = 196
        Width = 34
        Height = 13
        Caption = 'CTRC'
        Font.Charset = DEFAULT_CHARSET
        Font.Color = clBlack
        Font.Height = -11
        Font.Name = 'MS Sans Serif'
        Font.Style = [fsBold]
        ParentFont = False
      end
      object Label11: TLabel
        Left = 8
        Top = 33
        Width = 62
        Height = 13
        Caption = 'Remetente'
        Font.Charset = DEFAULT_CHARSET
        Font.Color = clBlack
        Font.Height = -11
        Font.Name = 'MS Sans Serif'
        Font.Style = [fsBold]
        ParentFont = False
      end
      object Label4: TLabel
        Left = 8
        Top = 60
        Width = 69
        Height = 13
        Caption = 'Destinat'#225'rio'
        Font.Charset = DEFAULT_CHARSET
        Font.Color = clBlack
        Font.Height = -11
        Font.Name = 'MS Sans Serif'
        Font.Style = [fsBold]
        ParentFont = False
      end
      object Label6: TLabel
        Left = 403
        Top = 196
        Width = 28
        Height = 13
        Caption = 'Nota'
        Font.Charset = DEFAULT_CHARSET
        Font.Color = clBlack
        Font.Height = -11
        Font.Name = 'MS Sans Serif'
        Font.Style = [fsBold]
        ParentFont = False
      end
      object Label3: TLabel
        Left = 8
        Top = 169
        Width = 53
        Height = 13
        Caption = 'Motorista'
        Color = clBtnFace
        Font.Charset = DEFAULT_CHARSET
        Font.Color = clBlack
        Font.Height = -11
        Font.Name = 'MS Sans Serif'
        Font.Style = [fsBold]
        ParentColor = False
        ParentFont = False
      end
      object Label8: TLabel
        Left = 174
        Top = 196
        Width = 20
        Height = 13
        Caption = 'At'#233
        Font.Charset = DEFAULT_CHARSET
        Font.Color = clBlack
        Font.Height = -11
        Font.Name = 'MS Sans Serif'
        Font.Style = [fsBold]
        ParentFont = False
      end
      object Label9: TLabel
        Left = 8
        Top = 196
        Width = 47
        Height = 13
        Caption = 'Emiss'#227'o'
        Font.Charset = DEFAULT_CHARSET
        Font.Color = clBlack
        Font.Height = -11
        Font.Name = 'MS Sans Serif'
        Font.Style = [fsBold]
        ParentFont = False
      end
      object Label7: TLabel
        Left = 508
        Top = 196
        Width = 37
        Height = 13
        Caption = 'Fatura'
        Font.Charset = DEFAULT_CHARSET
        Font.Color = clBlack
        Font.Height = -11
        Font.Name = 'MS Sans Serif'
        Font.Style = [fsBold]
        ParentFont = False
      end
      object Label10: TLabel
        Left = 8
        Top = 142
        Width = 45
        Height = 13
        Caption = 'Ve'#237'culo'
        Color = clBtnFace
        Font.Charset = DEFAULT_CHARSET
        Font.Color = clBlack
        Font.Height = -11
        Font.Name = 'MS Sans Serif'
        Font.Style = [fsBold]
        ParentColor = False
        ParentFont = False
      end
      object Label5: TLabel
        Left = 8
        Top = 115
        Width = 34
        Height = 13
        Caption = 'Carga'
        Font.Charset = DEFAULT_CHARSET
        Font.Color = clBlack
        Font.Height = -11
        Font.Name = 'MS Sans Serif'
        Font.Style = [fsBold]
        ParentFont = False
      end
      object Label12: TLabel
        Left = 623
        Top = 196
        Width = 26
        Height = 13
        Caption = 'RPS'
        Font.Charset = DEFAULT_CHARSET
        Font.Color = clBlack
        Font.Height = -11
        Font.Name = 'MS Sans Serif'
        Font.Style = [fsBold]
        ParentFont = False
      end
      object LblCdTitula: TLabel
        Left = 8
        Top = 87
        Width = 50
        Height = 13
        Caption = 'Tomador'
        Font.Charset = DEFAULT_CHARSET
        Font.Color = clBlack
        Font.Height = -11
        Font.Name = 'MS Sans Serif'
        Font.Style = [fsBold]
        ParentFont = False
      end
      object EdtCdEmpres: TBrvEditNum
        Left = 80
        Top = 3
        Width = 89
        Height = 21
        Font.Charset = ANSI_CHARSET
        Font.Color = clWindowText
        Font.Height = -11
        Font.Name = 'MS Sans Serif'
        Font.Style = []
        ParentFont = False
        TabOrder = 0
        Text = '0'
        BrvReadOnlyColor = 14541539
        BrAlignment = taRightJustify
        BrCasasDecimais = 0
        BrSepararMilhar = False
        BrAsInteger = 0
        BrAsFloatSQL = '0'
        BrVisibleButton = True
        BrFunctionButton = TpConsulta
        BrOnBeforeConsulta = EdtCdEmpresBrOnBeforeConsulta
        BrConfigurar.Strings = (
          'EdtCdEmpres;CdEmpres;S;S;'
          'EdtDsEmpres;DsEmpres;S;N;')
        BrDicionario = DmSrvApl.BrvDicionario
        BrQueryCode = 12
        BrRecordar = False
      end
      object EdtDsEmpres: TBrvRetCon
        Left = 175
        Top = 3
        Width = 891
        Height = 21
        TabStop = False
        Anchors = [akLeft, akTop, akRight]
        BevelInner = bvLowered
        BevelKind = bkFlat
        BevelWidth = 2
        BorderStyle = bsNone
        Font.Charset = ANSI_CHARSET
        Font.Color = clWindowText
        Font.Height = -11
        Font.Name = 'MS Sans Serif'
        Font.Style = []
        ParentColor = True
        ParentFont = False
        ReadOnly = True
        TabOrder = 1
      end
      object EdtCdCtrc: TBrvEditNum
        Left = 328
        Top = 193
        Width = 69
        Height = 21
        Font.Charset = DEFAULT_CHARSET
        Font.Color = clWindowText
        Font.Height = -11
        Font.Name = 'MS Sans Serif'
        Font.Style = []
        ParentFont = False
        TabOrder = 14
        Text = '0'
        BrvReadOnlyColor = 14541539
        BrAlignment = taRightJustify
        BrCasasDecimais = 0
        BrSepararMilhar = False
        BrAsInteger = 0
        BrAsFloatSQL = '0'
        BrVisibleButton = False
        BrFunctionButton = TpConsulta
        BrConfigurar.Strings = (
          'EdtCdCtrc;CdCTRC;S;S;'
          '')
        BrDicionario = DmSrvApl.BrvDicionario
        BrQueryCode = 0
        BrRecordar = False
      end
      object EdtRsRemete: TBrvRetCon
        Left = 175
        Top = 30
        Width = 891
        Height = 21
        TabStop = False
        Anchors = [akLeft, akTop, akRight]
        AutoSize = False
        BevelInner = bvLowered
        BevelKind = bkFlat
        BevelWidth = 2
        BorderStyle = bsNone
        Font.Charset = ANSI_CHARSET
        Font.Color = clWindowText
        Font.Height = -11
        Font.Name = 'MS Sans Serif'
        Font.Style = []
        ParentColor = True
        ParentFont = False
        ReadOnly = True
        TabOrder = 3
      end
      object EdtCdDestin: TBrvEditNum
        Left = 80
        Top = 57
        Width = 88
        Height = 21
        Font.Charset = ANSI_CHARSET
        Font.Color = clWindowText
        Font.Height = -11
        Font.Name = 'MS Sans Serif'
        Font.Style = []
        ParentFont = False
        TabOrder = 4
        Text = '0'
        BrvReadOnlyColor = 14541539
        BrAlignment = taRightJustify
        BrCasasDecimais = 0
        BrSepararMilhar = False
        BrAsInteger = 0
        BrAsFloatSQL = '0'
        BrVisibleButton = True
        BrFunctionButton = TpConsulta
        BrConfigurar.Strings = (
          'EdtCdDestin;CdTitula;S;S;'
          'EdtRsDestin;RsTitula;S;N')
        BrDicionario = DmSrvApl.BrvDicionario
        BrQueryCode = 15
        BrRecordar = False
      end
      object EdtRsDestin: TBrvRetCon
        Left = 174
        Top = 57
        Width = 892
        Height = 21
        TabStop = False
        Anchors = [akLeft, akTop, akRight]
        AutoSize = False
        BevelInner = bvLowered
        BevelKind = bkFlat
        BevelWidth = 2
        BorderStyle = bsNone
        Font.Charset = ANSI_CHARSET
        Font.Color = clWindowText
        Font.Height = -11
        Font.Name = 'MS Sans Serif'
        Font.Style = []
        ParentColor = True
        ParentFont = False
        ReadOnly = True
        TabOrder = 5
      end
      object EdtCdCarga: TBrvEditNum
        Left = 80
        Top = 112
        Width = 88
        Height = 21
        Font.Charset = ANSI_CHARSET
        Font.Color = clWindowText
        Font.Height = -11
        Font.Name = 'MS Sans Serif'
        Font.Style = []
        ParentFont = False
        TabOrder = 6
        Text = '0'
        BrvReadOnlyColor = 14541539
        BrAlignment = taRightJustify
        BrCasasDecimais = 0
        BrSepararMilhar = False
        BrAsInteger = 0
        BrAsFloatSQL = '0'
        BrVisibleButton = True
        BrFunctionButton = TpConsulta
        BrOnBeforeConsulta = EdtCdCargaBrOnBeforeConsulta
        BrConfigurar.Strings = (
          'EdtCdCarga;CdCarga;S;S;'
          'EdtDsDescar;DsDescar;S;N;')
        BrDicionario = DmSrvApl.BrvDicionario
        BrQueryCode = 25
        BrRecordar = False
      end
      object EdtDsDescar: TBrvRetCon
        Left = 174
        Top = 112
        Width = 892
        Height = 21
        TabStop = False
        Anchors = [akLeft, akTop, akRight]
        BevelInner = bvLowered
        BevelKind = bkFlat
        BevelWidth = 2
        BorderStyle = bsNone
        Font.Charset = ANSI_CHARSET
        Font.Color = clWindowText
        Font.Height = -11
        Font.Name = 'MS Sans Serif'
        Font.Style = []
        ParentColor = True
        ParentFont = False
        ReadOnly = True
        TabOrder = 7
      end
      object EdtNrNotFis: TBrvEditNum
        Left = 433
        Top = 193
        Width = 69
        Height = 21
        Font.Charset = DEFAULT_CHARSET
        Font.Color = clWindowText
        Font.Height = -11
        Font.Name = 'MS Sans Serif'
        Font.Style = []
        ParentFont = False
        TabOrder = 15
        Text = '0'
        BrvReadOnlyColor = 14541539
        BrAlignment = taRightJustify
        BrCasasDecimais = 0
        BrSepararMilhar = False
        BrAsInteger = 0
        BrAsFloatSQL = '0'
        BrVisibleButton = False
        BrFunctionButton = TpConsulta
        BrDicionario = DmSrvApl.BrvDicionario
        BrQueryCode = 0
        BrRecordar = False
      end
      object EdtNmMotori: TBrvRetCon
        Left = 174
        Top = 166
        Width = 770
        Height = 21
        TabStop = False
        Anchors = [akLeft, akTop, akRight]
        AutoSize = False
        BevelInner = bvLowered
        BevelKind = bkFlat
        BevelWidth = 2
        BorderStyle = bsNone
        Font.Charset = ANSI_CHARSET
        Font.Color = clWindowText
        Font.Height = -11
        Font.Name = 'MS Sans Serif'
        Font.Style = []
        ParentColor = True
        ParentFont = False
        ReadOnly = True
        TabOrder = 11
      end
      object EdtCdMotori: TBrvEditNum
        Left = 80
        Top = 166
        Width = 88
        Height = 21
        Font.Charset = ANSI_CHARSET
        Font.Color = clWindowText
        Font.Height = -11
        Font.Name = 'MS Sans Serif'
        Font.Style = []
        ParentFont = False
        TabOrder = 10
        Text = '0'
        OnChange = EdtCdVeiculChange
        BrvReadOnlyColor = 14541539
        BrAlignment = taRightJustify
        BrCasasDecimais = 0
        BrSepararMilhar = False
        BrAsInteger = 0
        BrAsFloatSQL = '0'
        BrVisibleButton = True
        BrFunctionButton = TpConsulta
        BrConfigurar.Strings = (
          'EdtCdMotori;CdMotori;S;S;'
          'EdtNmMotori;NmMotori;S;N;')
        BrDicionario = DmSrvApl.BrvDicionario
        BrQueryCode = 17
        BrRecordar = False
      end
      object EdtDtInicio: TBrvEditDate
        Tag = 2
        Left = 79
        Top = 193
        Width = 89
        Height = 21
        EditMask = '99/99/9999;1; '
        MaxLength = 10
        TabOrder = 12
        Text = '  /  /    '
        BrvReadOnlyColor = 14541539
        BrDataValida = False
        BrDataVazia = True
        BrFunctionButton = TVdData
        BrAlignment = taLeftJustify
        BrDicionario = DmSrvApl.BrvDicionario
        BrRecordar = False
      end
      object EdtDtFinal: TBrvEditDate
        Tag = 3
        Left = 200
        Top = 193
        Width = 89
        Height = 21
        EditMask = '99/99/9999;1; '
        MaxLength = 10
        TabOrder = 13
        Text = '  /  /    '
        BrvReadOnlyColor = 14541539
        BrDataValida = False
        BrDataVazia = True
        BrFunctionButton = TVdData
        BrAlignment = taLeftJustify
        BrDicionario = DmSrvApl.BrvDicionario
        BrRecordar = False
      end
      object EdtNrFatura: TBrvEditNum
        Left = 548
        Top = 193
        Width = 69
        Height = 21
        Font.Charset = DEFAULT_CHARSET
        Font.Color = clWindowText
        Font.Height = -11
        Font.Name = 'MS Sans Serif'
        Font.Style = []
        ParentFont = False
        TabOrder = 16
        Text = '0'
        BrvReadOnlyColor = 14541539
        BrAlignment = taRightJustify
        BrCasasDecimais = 0
        BrSepararMilhar = False
        BrAsInteger = 0
        BrAsFloatSQL = '0'
        BrVisibleButton = False
        BrFunctionButton = TpConsulta
        BrDicionario = DmSrvApl.BrvDicionario
        BrQueryCode = 0
        BrRecordar = False
      end
      object EdtCdVeicul: TBrvEditNum
        Left = 80
        Top = 139
        Width = 88
        Height = 21
        Font.Charset = ANSI_CHARSET
        Font.Color = clWindowText
        Font.Height = -11
        Font.Name = 'MS Sans Serif'
        Font.Style = []
        ParentFont = False
        TabOrder = 8
        Text = '0'
        OnChange = EdtCdVeiculChange
        BrvReadOnlyColor = 14541539
        BrAlignment = taRightJustify
        BrCasasDecimais = 0
        BrSepararMilhar = False
        BrAsInteger = 0
        BrAsFloatSQL = '0'
        BrVisibleButton = True
        BrFunctionButton = TpConsulta
        BrConfigurar.Strings = (
          'EdtCdVeicul;CdVeicul;S;S;'
          'EdtDsVeicul;NrPlaVei;S;N;')
        BrDicionario = DmSrvApl.BrvDicionario
        BrQueryCode = 20
        BrRecordar = False
      end
      object EdtDsVeicul: TBrvRetCon
        Left = 174
        Top = 139
        Width = 770
        Height = 21
        TabStop = False
        Anchors = [akLeft, akTop, akRight]
        AutoSize = False
        BevelInner = bvLowered
        BevelKind = bkFlat
        BevelWidth = 2
        BorderStyle = bsNone
        Font.Charset = ANSI_CHARSET
        Font.Color = clWindowText
        Font.Height = -11
        Font.Name = 'MS Sans Serif'
        Font.Style = []
        ParentColor = True
        ParentFont = False
        ReadOnly = True
        TabOrder = 9
      end
      object BtnLocali: TBrvBitBtn
        Left = 948
        Top = 192
        Width = 118
        Height = 25
        Hint = 'Localizar'
        Anchors = [akTop, akRight]
        Caption = 'Localizar'
        DoubleBuffered = True
        Font.Charset = DEFAULT_CHARSET
        Font.Color = clWindowText
        Font.Height = -11
        Font.Name = 'Tahoma'
        Font.Style = [fsBold]
        Glyph.Data = {
          96090000424D9609000000000000360000002800000028000000140000000100
          1800000000006009000000000000000000000000000000000000008080008080
          0080800080800080800080800080800080800080800080800080800080800080
          8000808000808000808000808000808000808000808000808000808000808000
          8080008080008080008080008080008080008080008080008080008080008080
          0080800080800080800080800080800080800080800080800080800080800080
          8000808000808000808000808000808000808000808000808000808000808000
          8080008080008080008080008080008080008080008080008080008080008080
          0080800080800080800080800080800080800080800080800080800080800080
          8000808000808000808000808000808000808000808000808000808000808000
          8080008080008080008080008080008080008080008080008080008080008080
          0080800080800080800080800080800080800080800080800080800080800080
          8000808000808000808000808000808000808000808000808000808000808000
          8080008080AD4416B95E3CB95E3CB95E3CB95E3CB46C49008080008080008080
          008080008080008080B95E3CB95E3CB95E3CB95E3CB46C490080800080800080
          80646360808080808080808080808080C0C0C000808000808000808000808000
          8080008080808080808080808080808080C0C0C0008080008080008080AD4416
          B95E3CE8D1C6B95E3CB95E3CB46C490080800080800080800080800080800080
          80B95E3CE8D1C6B95E3CB95E3CB46C49008080008080008080646360808080E8
          D1C6808080808080C0C0C0008080008080008080008080008080008080808080
          E8D1C6808080808080C0C0C0008080008080008080AD4416B95E3CE8D1C6B95E
          3CB95E3CB46C49008080008080008080008080008080008080B95E3CE8D1C6B9
          5E3CB95E3CB46C49008080008080008080646360808080E8D1C6808080808080
          C0C0C0008080008080008080008080008080008080808080E8D1C68080808080
          80C0C0C0008080008080008080AD4416B95E3CE8D1C6B95E3CB95E3CB46C4900
          8080008080008080008080008080008080B95E3CE8D1C6B95E3CB95E3CB46C49
          008080008080008080646360808080E8D1C6808080808080C0C0C00080800080
          80008080008080008080008080808080E8D1C6808080808080C0C0C000808000
          8080008080AD4416B95E3CB95E3CB95E3CB95E3CB95E3CB95E3CB46C49008080
          008080B95E3CB95E3CB95E3CB95E3CB95E3CB95E3CB46C490080800080800080
          80646360808080808080808080808080808080808080C0C0C000808000808080
          8080808080808080808080808080808080C0C0C0008080008080008080AD4416
          B95E3CB95E3CE8D1C6B95E3CB95E3CB95E3CB46C49B95E3CB95E3CB95E3CE8D1
          C6B95E3CB95E3CB95E3CB95E3CB46C4900808000808000808064636080808080
          8080E8D1C6808080808080808080C0C0C0808080808080808080E8D1C6808080
          808080808080808080C0C0C0008080008080008080AD4416B95E3CB95E3CE8D1
          C6B95E3CB95E3CB95E3C808080B95E3CB95E3CB95E3CE8D1C6B95E3CB95E3CB9
          5E3CB95E3CB46C49008080008080008080646360808080808080E8D1C6808080
          808080808080808080808080808080808080E8D1C68080808080808080808080
          80C0C0C0008080008080008080AD4416B95E3CB95E3CE8D1C6B95E3CB95E3CB9
          5E3C808080B95E3CB95E3CB95E3CE8D1C6B95E3CB95E3CB95E3CB95E3CB46C49
          008080008080008080646360808080808080E8D1C68080808080808080808080
          80808080808080808080E8D1C6808080808080808080808080C0C0C000808000
          8080008080008080AD4416B95E3CB95E3CB95E3CB95E3CB95E3CB46C49B95E3C
          B95E3CB95E3CB95E3CB95E3CB95E3CB95E3CB95E3C0080800080800080800080
          80008080646360808080808080808080808080808080C0C0C080808080808080
          8080808080808080808080808080808080008080008080008080008080008080
          008080AD4416B95E3CE8D1C6B95E3CB95E3CB46C49008080AD4416B95E3CE8D1
          C6B95E3CB95E3CB46C4900808000808000808000808000808000808000808064
          6360808080E8D1C6808080808080C0C0C0008080646360808080E8D1C6808080
          808080C0C0C0008080008080008080008080008080008080008080AD4416B95E
          3CB95E3CB95E3CB95E3CB46C49008080AD4416B95E3CB95E3CB95E3CB95E3CB4
          6C49008080008080008080008080008080008080008080646360808080808080
          808080808080C0C0C0008080646360808080808080808080808080C0C0C00080
          80008080008080008080008080008080008080008080AD4416B95E3CB95E3CB9
          5E3C008080008080008080AD4416B95E3CB95E3CB95E3C008080008080008080
          0080800080800080800080800080806463606463608080808080808080800080
          8000808000808064636080808080808080808000808000808000808000808000
          8080008080008080008080008080AD4416B95E3CE8D1C6B95E3C008080008080
          008080AD4416B95E3CE8D1C6B95E3C0080800080800080800080800080800080
          80008080008080008080646360808080E8D1C680808000808000808000808064
          6360808080E8D1C6808080008080008080008080008080008080008080008080
          008080008080AD4416B95E3CB95E3CB95E3C008080008080008080AD4416B95E
          3CB95E3CB95E3C00808000808000808000808000808000808000808000808000
          8080646360808080808080808080008080008080008080646360808080808080
          8080800080800080800080800080800080800080800080800080800080800080
          80AD4416AD4416AD4416008080008080008080AD4416AD4416AD4416AD441600
          8080008080008080008080008080008080008080008080008080008080646360
          6463606463600080800080800080800080806463606463606463600080800080
          8000808000808000808000808000808000808000808000808000808000808000
          8080008080008080008080008080008080008080008080008080008080008080
          0080800080800080800080800080800080800080800080800080800080800080
          8000808000808000808000808000808000808000808000808000808000808000
          8080008080008080008080008080008080008080008080008080008080008080
          0080800080800080800080800080800080800080800080800080800080800080
          8000808000808000808000808000808000808000808000808000808000808000
          8080008080008080008080008080008080008080008080008080}
        NumGlyphs = 2
        ParentDoubleBuffered = False
        ParentFont = False
        ParentShowHint = False
        ShowHint = True
        TabOrder = 18
        OnClick = BrvBitBtn1Click
        BrTipoBotao = BrBtnLocalizar
      end
      object EdtCdRemete: TBrvEditNum
        Left = 81
        Top = 30
        Width = 88
        Height = 21
        Font.Charset = ANSI_CHARSET
        Font.Color = clWindowText
        Font.Height = -11
        Font.Name = 'MS Sans Serif'
        Font.Style = []
        ParentFont = False
        TabOrder = 2
        Text = '0'
        BrvReadOnlyColor = 14541539
        BrAlignment = taRightJustify
        BrCasasDecimais = 0
        BrSepararMilhar = False
        BrAsInteger = 0
        BrAsFloatSQL = '0'
        BrVisibleButton = True
        BrFunctionButton = TpConsulta
        BrConfigurar.Strings = (
          'EdtCdRemete;CdTitula;S;S;'
          'EdtRsRemete;RsTitula;S;N')
        BrDicionario = DmSrvApl.BrvDicionario
        BrQueryCode = 15
        BrRecordar = False
      end
      object EdtNrRps: TBrvEditNum
        Left = 651
        Top = 193
        Width = 69
        Height = 21
        Font.Charset = DEFAULT_CHARSET
        Font.Color = clWindowText
        Font.Height = -11
        Font.Name = 'MS Sans Serif'
        Font.Style = []
        ParentFont = False
        TabOrder = 17
        Text = '0'
        BrvReadOnlyColor = 14541539
        BrAlignment = taRightJustify
        BrCasasDecimais = 0
        BrSepararMilhar = False
        BrAsInteger = 0
        BrAsFloatSQL = '0'
        BrVisibleButton = False
        BrFunctionButton = TpConsulta
        BrDicionario = DmSrvApl.BrvDicionario
        BrQueryCode = 0
        BrRecordar = False
      end
      object RdgCarga: TRadioGroup
        Left = 948
        Top = 139
        Width = 118
        Height = 48
        Anchors = [akTop, akRight]
        Caption = 'Carga'
        Columns = 2
        Enabled = False
        ItemIndex = 0
        Items.Strings = (
          #218'ltima'
          'Todas')
        TabOrder = 19
      end
      object EdtCdTitula: TBrvEditNum
        Left = 79
        Top = 84
        Width = 89
        Height = 21
        TabOrder = 20
        Text = '0'
        BrvReadOnlyColor = 14541539
        BrAlignment = taRightJustify
        BrCasasDecimais = 0
        BrSepararMilhar = False
        BrAsInteger = 0
        BrAsFloatSQL = '0'
        BrVisibleButton = True
        BrFunctionButton = TpConsulta
        BrConfigurar.Strings = (
          'EdtCdTitula;CdTitula;S;S;'
          'LblRsTitula;RsTitula;S;N;')
        BrDicionario = DmSrvApl.BrvDicionario
        BrQueryCode = 15
        BrRecordar = False
      end
      object LblRsTitula: TBrvRetCon
        Left = 174
        Top = 84
        Width = 892
        Height = 21
        TabStop = False
        Anchors = [akLeft, akTop, akRight]
        AutoSize = False
        BevelInner = bvLowered
        BevelKind = bkFlat
        BevelWidth = 2
        BorderStyle = bsNone
        Font.Charset = DEFAULT_CHARSET
        Font.Color = clWindowText
        Font.Height = -11
        Font.Name = 'MS Sans Serif'
        Font.Style = [fsBold]
        ParentColor = True
        ParentFont = False
        ReadOnly = True
        TabOrder = 21
      end
    end
    object BdgRegistros: TBrvDbGrid
      AlignWithMargins = True
      Left = 4
      Top = 236
      Width = 1069
      Height = 366
      BrShowMemo = True
      BrReadOnlyStyle = [fsItalic]
      BrReadOnlyColor = clMaroon
      Align = alClient
      DataSource = DsT011
      Options = [dgTitles, dgIndicator, dgColumnResize, dgColLines, dgRowLines, dgTabs, dgAlwaysShowSelection, dgConfirmDelete, dgCancelOnExit]
      PopupMenu = PopDetCTRC
      TabOrder = 1
      TitleFont.Charset = DEFAULT_CHARSET
      TitleFont.Color = clWindowText
      TitleFont.Height = -11
      TitleFont.Name = 'Tahoma'
      TitleFont.Style = []
      Visible = False
      OnDblClick = BdgRegistrosDblClick
      BrDicionario = DmSrvApl.BrvDicionario
      BrDrawColumn.Strings = (
        'N'#227'o remova essas duas linhas de formata'#231#227'o:'
        'CampoTabela;Operador;ValorComparativo;Cor;')
      BrGradeZebrada = True
      Columns = <
        item
          Expanded = False
          FieldName = 'CdEmpres'
          Title.Caption = 'Empresa'
          Width = 68
          Visible = True
          BrConsulta = 0
          BrPermissao = []
          BrValueHalfChecked = False
          BrSaveOnClick = False
        end
        item
          Expanded = False
          FieldName = 'DtEmissa'
          Title.Caption = 'Dt. Emiss'#227'o'
          Width = 68
          Visible = True
          BrConsulta = 0
          BrPermissao = []
          BrValueHalfChecked = False
          BrSaveOnClick = False
        end
        item
          Expanded = False
          FieldName = 'CdCTRC'
          Title.Caption = 'CTRC'
          Width = 68
          Visible = True
          BrConsulta = 0
          BrPermissao = []
          BrValueHalfChecked = False
          BrSaveOnClick = False
        end
        item
          Expanded = False
          FieldName = 'NrSeriNf'
          Title.Caption = 'N'#186' S'#233'rie'
          Width = 68
          Visible = True
          BrConsulta = 0
          BrPermissao = []
          BrValueHalfChecked = False
          BrSaveOnClick = False
        end
        item
          Expanded = False
          FieldName = 'NrRps'
          Title.Caption = 'RPS'
          Visible = True
          BrConsulta = 0
          BrPermissao = []
          BrValueHalfChecked = False
          BrSaveOnClick = False
        end
        item
          Expanded = False
          FieldName = 'StCtrc'
          Title.Caption = 'Situa'#231#227'o'
          Width = 68
          Visible = True
          BrConsulta = 0
          BrPermissao = []
          BrValueHalfChecked = False
          BrSaveOnClick = False
        end
        item
          Expanded = False
          FieldName = 'DtColeta'
          Title.Caption = 'Dt. Coleta'
          Width = 68
          Visible = True
          BrConsulta = 0
          BrPermissao = []
          BrValueHalfChecked = False
          BrSaveOnClick = False
        end
        item
          Expanded = False
          FieldName = 'DtMovto'
          Title.Caption = 'Dt. Movimento'
          Width = 112
          Visible = True
          BrConsulta = 0
          BrPermissao = []
          BrValueHalfChecked = False
          BrSaveOnClick = False
        end
        item
          Expanded = False
          FieldName = 'DtPreEnt'
          Title.Caption = 'Dt. Previs'#227'o'
          Width = 68
          Visible = True
          BrConsulta = 0
          BrPermissao = []
          BrValueHalfChecked = False
          BrSaveOnClick = False
        end
        item
          Expanded = False
          FieldName = 'DtEntreg'
          Title.Caption = 'Dt. Entrega'
          Width = 68
          Visible = True
          BrConsulta = 0
          BrPermissao = []
          BrValueHalfChecked = False
          BrSaveOnClick = False
        end
        item
          Expanded = False
          FieldName = 'DtEntMot'
          Title.Caption = 'Dt. Rastreador'
          Width = 112
          Visible = True
          BrConsulta = 0
          BrPermissao = []
          BrValueHalfChecked = False
          BrSaveOnClick = False
        end
        item
          Expanded = False
          FieldName = 'CdCarga'
          Title.Caption = 'Cd. Carga'
          Width = 68
          Visible = True
          BrConsulta = 0
          BrPermissao = []
          BrValueHalfChecked = False
          BrSaveOnClick = False
        end
        item
          Expanded = False
          FieldName = 'CdVeicul'
          Title.Caption = 'Cd. Ve'#237'culo'
          Width = 68
          Visible = True
          BrConsulta = 0
          BrPermissao = []
          BrValueHalfChecked = False
          BrSaveOnClick = False
        end
        item
          Expanded = False
          FieldName = 'NrFatura'
          Title.Caption = 'Nr. Fatura'
          Width = 68
          Visible = True
          BrConsulta = 0
          BrPermissao = []
          BrValueHalfChecked = False
          BrSaveOnClick = False
        end
        item
          Expanded = False
          FieldName = 'RsRemete'
          Title.Caption = 'Remetente'
          Width = 168
          Visible = True
          BrConsulta = 0
          BrPermissao = []
          BrValueHalfChecked = False
          BrSaveOnClick = False
        end
        item
          Expanded = False
          FieldName = 'RsDestin'
          Title.Caption = 'Destinat'#225'rio'
          Width = 168
          Visible = True
          BrConsulta = 0
          BrPermissao = []
          BrValueHalfChecked = False
          BrSaveOnClick = False
        end
        item
          Expanded = False
          FieldName = 'NmCidDes'
          Title.Caption = 'Cidade Destinat'#225'rio'
          Width = 168
          Visible = True
          BrConsulta = 0
          BrPermissao = []
          BrValueHalfChecked = False
          BrSaveOnClick = False
        end
        item
          Expanded = False
          FieldName = 'CdEstRem'
          Title.Caption = 'Cd. Estado'
          Width = 68
          Visible = True
          BrConsulta = 0
          BrPermissao = []
          BrValueHalfChecked = False
          BrSaveOnClick = False
        end
        item
          Alignment = taRightJustify
          Expanded = False
          FieldName = 'VrMercad'
          Title.Caption = 'Vlr. Mercadoria'
          Width = 68
          Visible = True
          BrConsulta = 0
          BrPermissao = []
          BrValueHalfChecked = False
          BrSaveOnClick = False
        end
        item
          Alignment = taRightJustify
          Expanded = False
          FieldName = 'NrPeso'
          Title.Caption = 'Peso'
          Width = 68
          Visible = True
          BrConsulta = 0
          BrPermissao = []
          BrValueHalfChecked = False
          BrSaveOnClick = False
        end
        item
          Alignment = taRightJustify
          Expanded = False
          FieldName = 'NrChvCte'
          Title.Caption = 'Chave CTe'
          Width = 212
          Visible = True
          BrConsulta = 0
          BrPermissao = []
          BrValueHalfChecked = False
          BrSaveOnClick = False
        end>
    end
    object StbDetalhes: TStatusBar
      Left = 1
      Top = 605
      Width = 1075
      Height = 19
      Panels = <
        item
          Width = 50
        end
        item
          Width = 180
        end
        item
          Width = 180
        end
        item
          Width = 180
        end>
    end
  end
  inherited NavBarra: TBrvDbNavCop [2]
    Width = 1081
    ExplicitWidth = 1081
    inherited BrvSpeedButton1: TBrvSpeedButton
      Left = 1054
      ExplicitLeft = 912
    end
    inherited SbtAjuda: TBrvSpeedButton
      Left = 1029
      ExplicitLeft = 887
    end
  end
  inherited PopCfgFrm: TPopupMenu
    Left = 328
    Top = 88
  end
  inherited ImlPopFrm: TImageList
    Height = 16
    Width = 16
    Left = 288
    Top = 88
    Bitmap = {
      494C0101030005001C0010001000FFFFFFFFFF10FFFFFFFFFFFFFFFF424D3600
      0000000000003600000028000000400000001000000001002000000000000010
      0000000000000000000000000000000000000000000000000000000000000000
      0000000000000000000000000000000000000000000000000000000000000000
      0000000000000000000000000000000000000000000000000000000000000000
      0000000000000000000000000000000000000000000000000000000000000000
      0000000000000000000000000000000000000000000000000000000000000000
      0000000000000000000000000000000000000000000000000000000000000000
      0000000000000000000000000000000000000000000000000000000000000000
      0000000000000000000000000000000000000000000000000000000000000000
      0000000000000000000000000000000000000000000000000000000000000000
      0000000000000000000000000000000000000000000000000000000000000000
      0000000000000000000000000000000000000000000000000000000000000000
      0000000000000000000000000000000000000000000000000000000000000000
      0000000000000000000000000000000000000000000000000000000000000000
      0000000000000000000000000000000000000000000000000000000000000000
      0000000000000000000000000000000000000000000000000000000000000000
      0000000000000000000000000000000000000000000000000000000000000000
      0000000000000000000000000000000000000000000000000000000000000000
      00000000000000000000000000007F007F007F007F007F7F7F00000000000000
      0000000000000000000000000000000000000000000000000000000000000000
      0000000000000000000000000000000000000000000000000000000000000000
      00000000000000000000000000000000000000000000000000007F7F7F000000
      0000000000000000000000000000000000000000000000000000000000000000
      0000000000000000000000000000000000000000000000000000000000000000
      0000000000000000000000000000000000000000000000000000000000000000
      0000000000000000000000000000000000000000000000000000000000000000
      0000000000007F007F007F007F00FFFFFF00FFFFFF00BFBFBF007F7F7F000000
      000000000000000000000000000000000000000000000000000000000000FFFF
      FF00FFFFFF00FFFFFF00FFFFFF00FFFFFF00FFFFFF00FFFFFF00FFFFFF00FFFF
      FF00FFFFFF0000000000000000000000000000000000000000007F7F7F00FFFF
      FF00FFFFFF00FFFFFF00FFFFFF00FFFFFF00FFFFFF00FFFFFF00FFFFFF00FFFF
      FF00000000000000000000000000000000000000000000000000000000000000
      0000000000000000000000000000000000000000000000000000000000000000
      0000000000000000000000000000000000000000000000000000000000007F00
      7F007F007F00FFFFFF00FFFFFF000000000000000000BFBFBF00BFBFBF007F7F
      7F0000000000000000000000000000000000000000000000000000000000FFFF
      FF00FFFFFF00FFFFFF00FFFFFF00FFFFFF00FFFFFF00FFFFFF00FFFFFF00FFFF
      FF00FFFFFF0000000000000000000000000000000000000000007F7F7F00FFFF
      FF00FFFFFF00FFFFFF00FFFFFF00FFFFFF00FFFFFF00FFFFFF00FFFFFF00FFFF
      FF00000000000000000000000000000000000000000000000000000000000000
      0000000000000000000000000000000000000000000000000000000000000000
      000000000000000000000000000000000000000000007F007F007F007F00FFFF
      FF00FFFFFF0000000000000000007F007F007F007F0000000000BFBFBF00BFBF
      BF007F7F7F00000000000000000000000000000000000000000000000000FFFF
      FF00FFFFFF00FFFFFF0000000000FFFFFF00FFFFFF00FFFFFF00FFFFFF00FFFF
      FF00FFFFFF0000000000000000000000000000000000000000007F7F7F007F00
      00007F0000007F0000007F0000007F0000007F0000007F0000007F0000007F00
      0000000000000000000000000000000000000000000000000000000000000000
      0000000000000000000000000000000000000000000000000000000000000000
      000000000000000000000000000000000000000000007F007F00FFFFFF000000
      0000000000007F007F007F007F007F007F007F007F007F007F0000000000BFBF
      BF00BFBFBF007F7F7F000000000000000000000000000000000000000000FFFF
      FF00FFFFFF0000000000000000000000000000000000FFFFFF00FFFFFF00FFFF
      FF00FFFFFF0000000000000000000000000000000000000000007F7F7F007F00
      0000FFFFFF00FFFFFF00FFFFFF00FFFFFF00FFFFFF00FFFFFF00FFFFFF007F00
      0000000000000000000000000000000000000000000000000000000000000000
      0000000000000000000000000000000000000000000000000000000000000000
      0000000000000000000000000000000000000000000000000000000000007F00
      7F007F007F007F007F00007F7F0000FFFF007F007F007F007F007F007F000000
      0000BFBFBF00BFBFBF007F7F7F0000000000000000000000000000000000FFFF
      FF00FFFFFF0000000000FFFFFF00000000000000000000000000FFFFFF00FFFF
      FF00FFFFFF0000000000000000000000000000000000000000007F7F7F007F00
      00007F0000007F0000007F0000007F0000007F0000007F0000007F0000007F00
      0000000000000000000000000000000000000000000000000000000000000000
      0000000000000000000000000000000000000000000000000000000000000000
      000000000000000000000000000000000000000000007F007F007F007F007F00
      7F007F007F007F007F007F007F00007F7F007F007F007F007F007F007F007F00
      7F0000000000BFBFBF000000000000000000000000000000000000000000FFFF
      FF00FFFFFF0000000000FFFFFF00FFFFFF000000000000000000FFFFFF00FFFF
      FF00FFFFFF0000000000000000000000000000000000000000007F7F7F00FFFF
      FF00FFFFFF00FFFFFF00FFFFFF00FFFFFF00FFFFFF00FFFFFF00FFFFFF00FFFF
      FF00000000000000000000000000000000000000000000000000000000000000
      0000000000000000000000000000000000000000000000000000000000000000
      0000000000000000000000000000000000000000000000000000FFFFFF007F00
      7F007F007F007F007F007F007F007F007F0000FFFF0000FFFF007F007F007F00
      7F007F007F00000000000000000000000000000000000000000000000000FFFF
      FF00FFFFFF00FFFFFF00FFFFFF00FFFFFF00FFFFFF000000000000000000FFFF
      FF00FFFFFF0000000000000000000000000000000000000000007F7F7F00FFFF
      FF00FFFFFF00FFFFFF00FFFFFF00FFFFFF00FFFFFF00FFFFFF00FFFFFF00FFFF
      FF00000000000000000000000000000000000000000000000000000000000000
      0000000000000000000000000000000000000000000000000000000000000000
      000000000000000000000000000000000000000000000000000000000000FFFF
      FF007F007F007F007F007F007F007F007F007F007F00007F7F0000FFFF0000FF
      FF007F007F007F007F000000000000000000000000000000000000000000FFFF
      FF00FFFFFF00FFFFFF00FFFFFF00FFFFFF00FFFFFF00FFFFFF0000000000FFFF
      FF00FFFFFF0000000000000000000000000000000000000000007F7F7F00FFFF
      FF007F0000007F0000007F0000007F000000FFFFFF00FFFFFF00FFFFFF00FFFF
      FF00000000000000000000000000000000000000000000000000000000000000
      0000000000000000000000000000000000000000000000000000000000000000
      0000000000000000000000000000000000000000000000000000000000000000
      0000FFFFFF007F007F007F007F007F007F00007F7F007F007F0000FFFF0000FF
      FF007F007F007F007F007F007F0000000000000000000000000000000000FFFF
      FF00FFFFFF00FFFFFF00FFFFFF00FFFFFF00FFFFFF00FFFFFF00FFFFFF00FFFF
      FF00FFFFFF0000000000000000000000000000000000000000007F7F7F00FFFF
      FF00FFFFFF00FFFFFF00FFFFFF00FFFFFF00FFFFFF00FFFFFF00FFFFFF00FFFF
      FF00000000000000000000000000000000000000000000000000000000000000
      0000000000000000000000000000000000000000000000000000000000000000
      0000000000000000000000000000000000000000000000000000000000000000
      000000000000FFFFFF007F007F007F007F0000FFFF0000FFFF0000FFFF007F00
      7F007F007F007F007F000000000000000000000000000000000000000000FFFF
      FF00FFFFFF00FFFFFF00FFFFFF00FFFFFF00FFFFFF00FFFFFF00FFFFFF00FFFF
      FF00FFFFFF0000000000000000000000000000000000000000007F7F7F00FFFF
      FF007F0000007F0000007F0000007F0000007F000000FFFFFF00FFFFFF00FFFF
      FF00000000000000000000000000000000000000000000000000000000000000
      0000000000000000000000000000000000000000000000000000000000000000
      0000000000000000000000000000000000000000000000000000000000000000
      00000000000000000000FFFFFF007F007F007F007F007F007F007F007F007F00
      7F00000000000000000000000000000000000000000000000000000000000000
      0000000000000000000000000000000000000000000000000000000000000000
      00000000000000000000000000000000000000000000000000007F7F7F00FFFF
      FF00FFFFFF00FFFFFF00FFFFFF00FFFFFF00FFFFFF00FFFFFF00FFFFFF00FFFF
      FF00000000000000000000000000000000000000000000000000000000000000
      0000000000000000000000000000000000000000000000000000000000000000
      0000000000000000000000000000000000000000000000000000000000000000
      0000000000000000000000000000FFFFFF007F007F007F007F00000000000000
      0000000000000000000000000000000000000000000000000000000000000000
      0000000000000000000000000000000000000000000000000000000000000000
      00000000000000000000000000000000000000000000000000007F7F7F007F7F
      7F007F7F7F007F7F7F007F7F7F007F7F7F007F7F7F007F7F7F007F7F7F007F7F
      7F007F7F7F000000000000000000000000000000000000000000000000000000
      0000000000000000000000000000000000000000000000000000000000000000
      0000000000000000000000000000000000000000000000000000000000000000
      0000000000000000000000000000000000000000000000000000000000000000
      0000000000000000000000000000000000000000000000000000000000000000
      0000000000000000000000000000000000000000000000000000000000000000
      0000000000000000000000000000000000000000000000000000000000000000
      0000000000000000000000000000000000000000000000000000000000000000
      0000000000000000000000000000000000000000000000000000000000000000
      0000000000000000000000000000000000000000000000000000000000000000
      000000000000000000000000000000000000424D3E000000000000003E000000
      2800000040000000100000000100010000000000800000000000000000000000
      000000000000000000000000FFFFFF00FFFFFFFFFFFF0000FE3FFFFFFFFF0000
      F81FC003C0070000E00FC003C00700008007C003C00700000003C003C0070000
      0001C003C00700000000C003C00700000001C003C00700008001C003C0070000
      C001C003C0070000E000C003C0070000F000C003C0070000F803C003C0070000
      FC0FFFFFC0070000FE3FFFFFFFFF000000000000000000000000000000000000
      000000000000}
  end
  inherited LspS049: TBrvListParam
    Left = 256
    Top = 88
  end
  object DsT011: TDataSource
    DataSet = CPT011
    Left = 425
    Top = 307
  end
  object CPT011: TBrvClientDataSet
    Aggregates = <>
    Params = <>
    ProviderName = 'DcEntMer'
    RemoteServer = DmSrvApl.PvcSDmTra
    BrShowFieldName = False
    BrQueryCode = 45
    BrDicionario = DmSrvApl.BrvDicionario
    BrType = VqNormal
    BrGravaLog = True
    BrUserCode = 0
    Left = 487
    Top = 307
  end
  object PopDetCTRC: TPopupMenu
    Left = 568
    Top = 344
    object Detalhar1: TMenuItem
      Caption = 'Detalhar'
      OnClick = Detalhar1Click
    end
  end
end
