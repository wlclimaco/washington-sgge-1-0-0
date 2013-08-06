inherited Con0002: TCon0002
  Caption = 'Con0002'
  ClientHeight = 489
  ClientWidth = 837
  ExplicitWidth = 845
  ExplicitHeight = 516
  PixelsPerInch = 96
  TextHeight = 13
  inherited NavBarra: TBrvDbNavCop
    Width = 837
    ExplicitWidth = 837
    inherited BrvSpeedButton1: TBrvSpeedButton
      Left = 810
      ExplicitLeft = 821
    end
    inherited SbtAjuda: TBrvSpeedButton
      Left = 785
      ExplicitLeft = 796
    end
  end
  inherited PnlFundo: TPanel
    Width = 837
    Height = 459
    ParentBackground = False
    ExplicitWidth = 837
    ExplicitHeight = 459
    object pnlMestre: TPanel
      Left = 1
      Top = 1
      Width = 831
      Height = 240
      Align = alTop
      BevelEdges = [beBottom]
      BevelKind = bkSoft
      BevelOuter = bvNone
      Caption = ' '
      ParentBackground = False
      TabOrder = 0
      DesignSize = (
        831
        238)
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
        Left = 633
        Top = 6
        Width = 34
        Height = 13
        Anchors = [akTop, akRight]
        Caption = 'CTRC'
        Font.Charset = DEFAULT_CHARSET
        Font.Color = clBlack
        Font.Height = -11
        Font.Name = 'MS Sans Serif'
        Font.Style = [fsBold]
        ParentFont = False
        ExplicitLeft = 615
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
      object Label5: TLabel
        Left = 8
        Top = 87
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
      object Label6: TLabel
        Left = 633
        Top = 60
        Width = 28
        Height = 13
        Anchors = [akTop, akRight]
        Caption = 'Nota'
        Font.Charset = DEFAULT_CHARSET
        Font.Color = clBlack
        Font.Height = -11
        Font.Name = 'MS Sans Serif'
        Font.Style = [fsBold]
        ParentFont = False
        Visible = False
        ExplicitLeft = 615
      end
      object Label3: TLabel
        Left = 8
        Top = 114
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
      object Label9: TLabel
        Left = 633
        Top = 33
        Width = 71
        Height = 13
        Anchors = [akTop, akRight]
        Caption = 'Atendimento'
        Color = clBtnFace
        Font.Charset = DEFAULT_CHARSET
        Font.Color = clBlack
        Font.Height = -11
        Font.Name = 'MS Sans Serif'
        Font.Style = [fsBold]
        ParentColor = False
        ParentFont = False
        ExplicitLeft = 615
      end
      object Label10: TLabel
        Left = 8
        Top = 140
        Width = 44
        Height = 13
        Caption = 'Usu'#225'rio'
        Color = clBtnFace
        Font.Charset = DEFAULT_CHARSET
        Font.Color = clBlack
        Font.Height = -11
        Font.Name = 'MS Sans Serif'
        Font.Style = [fsBold]
        ParentColor = False
        ParentFont = False
      end
      object Label12: TLabel
        Left = 633
        Top = 87
        Width = 26
        Height = 13
        Anchors = [akTop, akRight]
        Caption = 'RPS'
        Font.Charset = DEFAULT_CHARSET
        Font.Color = clBlack
        Font.Height = -11
        Font.Name = 'MS Sans Serif'
        Font.Style = [fsBold]
        ParentFont = False
        Visible = False
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
        Width = 452
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
        Left = 733
        Top = 3
        Width = 88
        Height = 21
        Anchors = [akTop, akRight]
        Font.Charset = DEFAULT_CHARSET
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
        BrOnBeforeConsulta = EdtCdCtrcBrOnBeforeConsulta
        BrConfigurar.Strings = (
          'EdtCdCtrc;CdCtrc;S;S;'
          '')
        BrDicionario = DmSrvApl.BrvDicionario
        BrQueryCode = 11
        BrRecordar = False
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
        TabOrder = 3
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
          'EdtCdRemete;CdClient;S;S;'
          'EdtRsRemete;Rsclient;S;N')
        BrDicionario = DmSrvApl.BrvDicionario
        BrQueryCode = 15
        BrRecordar = False
      end
      object EdtRsRemete: TBrvRetCon
        Left = 175
        Top = 30
        Width = 452
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
        TabOrder = 4
      end
      object EdtCdDestin: TBrvEditNum
        Left = 81
        Top = 57
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
        BrConfigurar.Strings = (
          'EdtCdDestin;CdClient;S;S;'
          'EdtRsDestin;Rsclient;S;N')
        BrDicionario = DmSrvApl.BrvDicionario
        BrQueryCode = 15
        BrRecordar = False
      end
      object EdtRsDestin: TBrvRetCon
        Left = 175
        Top = 57
        Width = 452
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
        TabOrder = 7
      end
      object EdtCdCarga: TBrvEditNum
        Left = 81
        Top = 84
        Width = 88
        Height = 21
        Font.Charset = ANSI_CHARSET
        Font.Color = clWindowText
        Font.Height = -11
        Font.Name = 'MS Sans Serif'
        Font.Style = []
        ParentFont = False
        TabOrder = 9
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
          'EdtCdCarga;CdCarga;S;S;'
          'EdtDsDescar;DsDescar;S;N;')
        BrDicionario = DmSrvApl.BrvDicionario
        BrQueryCode = 24
        BrRecordar = False
      end
      object EdtDsDescar: TBrvRetCon
        Left = 175
        Top = 84
        Width = 452
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
        TabOrder = 10
      end
      object BrvEditNum1: TBrvEditNum
        Left = 733
        Top = 57
        Width = 88
        Height = 21
        Anchors = [akTop, akRight]
        Font.Charset = DEFAULT_CHARSET
        Font.Color = clWindowText
        Font.Height = -11
        Font.Name = 'MS Sans Serif'
        Font.Style = []
        ParentFont = False
        TabOrder = 8
        Text = '0'
        Visible = False
        BrvReadOnlyColor = 14541539
        BrAlignment = taRightJustify
        BrCasasDecimais = 0
        BrSepararMilhar = False
        BrAsInteger = 0
        BrAsFloatSQL = '0'
        BrVisibleButton = False
        BrFunctionButton = TpConsulta
        BrConfigurar.Strings = (
          'EdtCdCtrc;CdCtrc;S;S;'
          '')
        BrDicionario = DmSrvApl.BrvDicionario
        BrQueryCode = 11
        BrRecordar = False
      end
      object BtnLocali: TBrvBitBtn
        Left = 731
        Top = 209
        Width = 90
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
        OnClick = BtnLocaliClick
        BrTipoBotao = BrBtnLocalizar
      end
      object EdtNmMotori: TBrvRetCon
        Left = 175
        Top = 111
        Width = 646
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
        TabOrder = 13
      end
      object GroupBox1: TGroupBox
        Left = 334
        Top = 163
        Width = 393
        Height = 71
        Anchors = [akLeft, akTop, akRight]
        Caption = 'Pesquisar pelo texto do :'
        TabOrder = 17
        DesignSize = (
          393
          71)
        object rbAtendimento: TRadioButton
          Left = 7
          Top = 20
          Width = 88
          Height = 17
          Caption = 'Atendimento'
          Checked = True
          TabOrder = 0
          TabStop = True
        end
        object rbOcorrencia: TRadioButton
          Left = 101
          Top = 20
          Width = 74
          Height = 17
          Caption = 'Ocorr'#234'ncia'
          TabOrder = 1
        end
        object rbAssunto: TRadioButton
          Left = 181
          Top = 20
          Width = 66
          Height = 17
          Caption = 'Assunto'
          TabOrder = 2
        end
        object EdtTexto: TBrvEdit
          Left = 7
          Top = 43
          Width = 379
          Height = 21
          Anchors = [akLeft, akTop, akRight]
          TabOrder = 3
          TextHint = 'text hint'
          BrvReadOnlyColor = 14541539
          BrVisibleButton = False
          BrFunctionButton = VeConsulta
          BrAlignment = taLeftJustify
          BrDicionario = DmSrvApl.BrvDicionario
          BrvQueryCode = 0
          BrRecordar = False
        end
      end
      object EdtCdMotori: TBrvEditNum
        Left = 81
        Top = 111
        Width = 88
        Height = 21
        Font.Charset = ANSI_CHARSET
        Font.Color = clWindowText
        Font.Height = -11
        Font.Name = 'MS Sans Serif'
        Font.Style = []
        ParentFont = False
        TabOrder = 12
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
          'EdtCdMotori;CdMotori;S;S;'
          'EdtNmMotori;NmMotori;S;N;')
        BrDicionario = DmSrvApl.BrvDicionario
        BrQueryCode = 17
        BrRecordar = False
      end
      object EdtNrAtendi: TBrvEditNum
        Left = 733
        Top = 30
        Width = 88
        Height = 21
        Anchors = [akTop, akRight]
        Font.Charset = ANSI_CHARSET
        Font.Color = clWindowText
        Font.Height = -11
        Font.Name = 'MS Sans Serif'
        Font.Style = []
        ParentFont = False
        TabOrder = 5
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
          'EdtCdMotori;CdMotori;S;S;'
          'EdtNmMotori;NmMotori;S;N;')
        BrDicionario = DmSrvApl.BrvDicionario
        BrQueryCode = 17
        BrRecordar = False
      end
      object GroupBox2: TGroupBox
        Left = 8
        Top = 163
        Width = 321
        Height = 71
        Caption = 'Per'#237'odo'
        TabOrder = 16
        DesignSize = (
          321
          71)
        object Label8: TLabel
          Left = 168
          Top = 46
          Width = 49
          Height = 13
          Anchors = [akTop, akRight]
          Caption = 'Dt. Final'
          Font.Charset = DEFAULT_CHARSET
          Font.Color = clBlack
          Font.Height = -11
          Font.Name = 'MS Sans Serif'
          Font.Style = [fsBold]
          ParentFont = False
        end
        object Label7: TLabel
          Left = 16
          Top = 46
          Width = 52
          Height = 13
          Anchors = [akTop, akRight]
          Caption = 'Dt.Inicial'
          Font.Charset = DEFAULT_CHARSET
          Font.Color = clBlack
          Font.Height = -11
          Font.Name = 'MS Sans Serif'
          Font.Style = [fsBold]
          ParentFont = False
        end
        object EdtFinal: TBrvEditDate
          Tag = 3
          Left = 223
          Top = 43
          Width = 88
          Height = 21
          Anchors = [akTop, akRight]
          EditMask = '99/99/9999;1; '
          MaxLength = 10
          TabOrder = 3
          Text = '  /  /    '
          BrvReadOnlyColor = 14541539
          BrDataValida = False
          BrDataVazia = True
          BrFunctionButton = TVdData
          BrAlignment = taLeftJustify
          BrDicionario = DmSrvApl.BrvDicionario
          BrRecordar = False
        end
        object EdtInicio: TBrvEditDate
          Tag = 2
          Left = 74
          Top = 43
          Width = 88
          Height = 21
          Anchors = [akTop, akRight]
          EditMask = '99/99/9999;1; '
          MaxLength = 10
          TabOrder = 2
          Text = '  /  /    '
          BrvReadOnlyColor = 14541539
          BrDataValida = False
          BrDataVazia = True
          BrFunctionButton = TVdData
          BrAlignment = taLeftJustify
          BrDicionario = DmSrvApl.BrvDicionario
          BrRecordar = False
        end
        object rbAbertura: TRadioButton
          Left = 16
          Top = 20
          Width = 73
          Height = 17
          Caption = 'Abertura'
          Checked = True
          TabOrder = 0
          TabStop = True
        end
        object rbFechamento: TRadioButton
          Left = 104
          Top = 20
          Width = 113
          Height = 17
          Caption = 'Fechamento'
          TabOrder = 1
        end
      end
      object EdtCdUsuari: TBrvEditNum
        Left = 81
        Top = 136
        Width = 88
        Height = 21
        Font.Charset = ANSI_CHARSET
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
        BrVisibleButton = True
        BrFunctionButton = TpConsulta
        BrConfigurar.Strings = (
          'EdtCdUsuari;CdUsuari;S;S;'
          'EdtNmComUsu;NmComUsu;S;N;')
        BrDicionario = DmSrvApl.BrvDicionario
        BrQueryCode = 32
        BrRecordar = False
      end
      object EdtNmComUsu: TBrvRetCon
        Left = 175
        Top = 137
        Width = 646
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
        TabOrder = 15
      end
      object EdtNrRps: TBrvEditNum
        Left = 733
        Top = 84
        Width = 88
        Height = 21
        Anchors = [akTop, akRight]
        Font.Charset = DEFAULT_CHARSET
        Font.Color = clWindowText
        Font.Height = -11
        Font.Name = 'MS Sans Serif'
        Font.Style = []
        ParentFont = False
        TabOrder = 11
        Text = '0'
        Visible = False
        BrvReadOnlyColor = 14541539
        BrAlignment = taRightJustify
        BrCasasDecimais = 0
        BrSepararMilhar = False
        BrAsInteger = 0
        BrAsFloatSQL = '0'
        BrVisibleButton = False
        BrFunctionButton = TpConsulta
        BrConfigurar.Strings = (
          'EdtCdCtrc;CdCtrc;S;S;'
          '')
        BrDicionario = DmSrvApl.BrvDicionario
        BrQueryCode = 11
        BrRecordar = False
      end
    end
    object bdgRegistros: TBrvDbGrid
      Left = 1
      Top = 241
      Width = 831
      Height = 213
      BrShowMemo = True
      BrReadOnlyStyle = []
      BrReadOnlyColor = clBlack
      Align = alClient
      DataSource = DsT010
      Options = [dgTitles, dgIndicator, dgColumnResize, dgColLines, dgRowLines, dgTabs, dgConfirmDelete, dgCancelOnExit]
      TabOrder = 1
      TitleFont.Charset = DEFAULT_CHARSET
      TitleFont.Color = clWindowText
      TitleFont.Height = -11
      TitleFont.Name = 'Tahoma'
      TitleFont.Style = []
      OnDblClick = bdgRegistrosDblClick
      BrDicionario = DmSrvApl.BrvDicionario
      BrDrawColumn.Strings = (
        'N'#227'o remova essas duas linhas de formata'#231#227'o:'
        'CampoTabela;Operador;ValorComparativo;Cor;')
      BrGradeZebrada = True
      Columns = <
        item
          Expanded = False
          FieldName = 'NrAtendi'
          Title.Caption = 'Atendimento'
          Width = 108
          Visible = True
          BrConsulta = 0
          BrPermissao = []
          BrValueHalfChecked = False
          BrSaveOnClick = False
        end
        item
          Expanded = False
          FieldName = 'DsTipAte'
          Title.Caption = 'Tipo de Atendimento'
          Width = 131
          Visible = True
          BrConsulta = 0
          BrPermissao = []
          BrValueHalfChecked = False
          BrSaveOnClick = False
        end
        item
          Expanded = False
          FieldName = 'DsAtendi'
          Title.Caption = 'Descri'#231#227'o do Atendimento'
          Width = 260
          Visible = True
          BrConsulta = 0
          BrPermissao = []
          BrValueHalfChecked = False
          BrSaveOnClick = False
        end
        item
          Expanded = False
          FieldName = 'DtAbertu'
          Title.Caption = 'Dt. Abertura'
          Width = 118
          Visible = True
          BrConsulta = 0
          BrPermissao = []
          BrValueHalfChecked = False
          BrSaveOnClick = False
        end
        item
          Expanded = False
          FieldName = 'DtFecham'
          Title.Caption = 'Dt. Fechamento'
          Width = 139
          Visible = True
          BrConsulta = 0
          BrPermissao = []
          BrValueHalfChecked = False
          BrSaveOnClick = False
        end>
    end
  end
  inherited ImlPopFrm: TImageList
    Height = 16
    Width = 16
    Bitmap = {
      494C010103000500100010001000FFFFFFFFFF10FFFFFFFFFFFFFFFF424D3600
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
  object DsT010: TDataSource
    DataSet = CPT010
    Left = 425
    Top = 307
  end
  object CPT010: TBrvClientDataSet
    Aggregates = <>
    Params = <>
    ProviderName = 'DcEntMer'
    RemoteServer = DmSrvApl.PvcSDmTra
    BrShowFieldName = False
    BrQueryCode = 44
    BrDicionario = DmSrvApl.BrvDicionario
    BrType = VqNormal
    BrGravaLog = True
    BrUserCode = 0
    Left = 487
    Top = 307
  end
  object BrvServerProgress: TBrvServerProgress
    Left = 520
    Top = 304
  end
end
