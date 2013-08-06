inherited Cad0008: TCad0008
  Left = 390
  Top = 165
  Caption = 'Cad0008 - Contas Cont'#225'beis'
  ClientHeight = 453
  ClientWidth = 763
  Position = poDesigned
  ExplicitWidth = 771
  ExplicitHeight = 480
  PixelsPerInch = 96
  TextHeight = 13
  inherited NavBarra: TBrvDbNavCop
    Width = 763
    ExplicitWidth = 763
    inherited BrvSpeedButton1: TBrvSpeedButton
      Left = 736
      ExplicitLeft = 736
    end
    inherited SbtAjuda: TBrvSpeedButton
      Left = 711
      ExplicitLeft = 711
    end
    object CbxNumAut: TCheckBox
      Left = 9
      Top = 5
      Width = 248
      Height = 17
      Caption = 'Gerar numera'#231#227'o autom'#225'ticamente'
      Enabled = False
      Font.Charset = DEFAULT_CHARSET
      Font.Color = clWindowText
      Font.Height = -11
      Font.Name = 'MS Sans Serif'
      Font.Style = [fsBold]
      ParentFont = False
      TabOrder = 0
    end
  end
  inherited PnlFundo: TPanel
    Width = 763
    Height = 423
    ExplicitTop = 30
    ExplicitWidth = 763
    ExplicitHeight = 423
    object Splitter1: TSplitter
      Left = 311
      Top = 1
      Height = 385
      Align = alRight
      ExplicitLeft = 460
      ExplicitTop = 4
      ExplicitHeight = 374
    end
    object PnlRodape: TPanel
      Left = 1
      Top = 386
      Width = 757
      Height = 32
      Align = alBottom
      BorderStyle = bsSingle
      TabOrder = 0
      object BotExpand: TBitBtn
        Left = 2
        Top = 1
        Width = 90
        Height = 25
        Caption = '&Expandir'
        DoubleBuffered = True
        Font.Charset = DEFAULT_CHARSET
        Font.Color = clWindowText
        Font.Height = -11
        Font.Name = 'MS Sans Serif'
        Font.Style = [fsBold]
        Glyph.Data = {
          AA040000424DAA04000000000000360000002800000013000000130000000100
          18000000000074040000C40E0000C40E00000000000000000000FFFFFFFFFFFF
          FFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFF
          FFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFF000000FFFFFFFFFFFFFFFFFFFF
          FFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFF
          FFFFFFFFFFFFFFFFFFFFFFFFFFFFFF000000FFFFFFFFFFFFFFFFFFFFFFFFFFFF
          FFFFFFFFFFFFFFFFFFFF000000000000000000FFFFFFFFFFFFFFFFFFFFFFFFFF
          FFFFFFFFFFFFFFFFFFFFFF000000FFFFFFFFFFFFFFFFFF000000000000000000
          000000FFFFFF000000FFFFFF000000FFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFF
          FFFFFFFFFFFFFF000000FFFFFFFFFFFFFFFFFF000000FFFFFFFFFFFFFFFFFFFF
          FFFF000000000000000000FFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFF
          FFFFFF000000FFFFFFFFFFFFFFFFFF000000FFFFFFFFFFFFFFFFFFFFFFFFFFFF
          FFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFF00
          0000FFFFFFFFFFFFFFFFFF000000FFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFF
          FFFFFFFFFFFFFFFFFFFFFFFF000000000000000000FFFFFFFFFFFF000000FFFF
          FFFFFFFFFFFFFF000000FFFFFFFFFFFFFFFFFFFFFFFFFFFFFF00000000000000
          0000000000FFFFFF000000FFFFFF000000FFFFFFFFFFFF000000FFFFFFFFFFFF
          FFFFFF000000FFFFFFFFFFFFFFFFFFFFFFFFFFFFFF000000FFFFFFFFFFFFFFFF
          FFFFFFFF000000000000000000FFFFFFFFFFFF000000FFFFFFFFFFFFFFFFFF00
          0000FFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFF
          FFFFFFFFFFFFFFFFFFFFFFFFFFFFFF000000FFFFFFFFFFFFFFFFFF000000FFFF
          FFFFFFFFFFFFFFFFFFFF000000000000000000FFFFFFFFFFFFFFFFFFFFFFFFFF
          FFFFFFFFFFFFFFFFFFFFFF000000FFFFFFFFFFFFFFFFFF000000000000000000
          000000FFFFFF000000FFFFFF000000FFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFF
          FFFFFFFFFFFFFF000000FFFFFFFFFFFFFFFFFF000000FFFFFFFFFFFFFFFFFFFF
          FFFF000000000000000000FFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFF
          FFFFFF000000FFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFF
          FFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFF00
          0000FFFFFFFFFFFF000000000000000000FFFFFFFFFFFFFFFFFFFFFFFFFFFFFF
          FFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFF000000FFFF
          FFFFFFFF000000FFFFFF000000FFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFF
          FFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFF000000FFFFFFFFFFFF
          000000000000000000FFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFF
          FFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFF000000FFFFFFFFFFFFFFFFFFFF
          FFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFF
          FFFFFFFFFFFFFFFFFFFFFFFFFFFFFF000000FFFFFFFFFFFFFFFFFFFFFFFFFFFF
          FFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFF
          FFFFFFFFFFFFFFFFFFFFFF000000}
        ParentDoubleBuffered = False
        ParentFont = False
        TabOrder = 0
        OnClick = BotExpandClick
      end
      object BotColaps: TBitBtn
        Left = 95
        Top = 1
        Width = 90
        Height = 25
        Caption = '&Recolher'
        DoubleBuffered = True
        Font.Charset = DEFAULT_CHARSET
        Font.Color = clWindowText
        Font.Height = -11
        Font.Name = 'MS Sans Serif'
        Font.Style = [fsBold]
        Glyph.Data = {
          AA040000424DAA04000000000000360000002800000013000000130000000100
          18000000000074040000C40E0000C40E00000000000000000000FFFFFFFFFFFF
          FFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFF
          FFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFF000000FFFFFFFFFFFFFFFFFFFF
          FFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFF
          FFFFFFFFFFFFFFFFFFFFFFFFFFFFFF000000FFFFFFFFFFFFFFFFFFFFFFFFFFFF
          FFFFFFFFFFFFFFFFFFFF000000000000000000FFFFFFFFFFFFFFFFFFFFFFFFFF
          FFFFFFFFFFFFFFFFFFFFFF000000FFFFFFFFFFFFFFFFFF000000000000000000
          000000FFFFFF000000FFFFFF000000FFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFF
          FFFFFFFFFFFFFF000000FFFFFFFFFFFFFFFFFF000000FFFFFFFFFFFFFFFFFFFF
          FFFF000000000000000000FFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFF
          FFFFFF000000FFFFFFFFFFFFFFFFFF000000FFFFFFFFFFFFFFFFFFFFFFFFFFFF
          FFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFF00
          0000FFFFFFFFFFFFFFFFFF000000FFFFFFFFFFFFFFFFFFFFFFFF000000000000
          000000FFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFF000000FFFF
          FFFFFFFFFFFFFF000000000000000000000000FFFFFF000000FFFFFF000000FF
          FFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFF000000FFFFFFFFFFFF
          FFFFFF000000FFFFFFFFFFFFFFFFFFFFFFFF000000000000000000FFFFFFFFFF
          FFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFF000000FFFFFFFFFFFFFFFFFF00
          0000FFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFF
          FFFFFFFFFFFFFFFFFFFFFFFFFFFFFF000000FFFFFFFFFFFFFFFFFF000000FFFF
          FFFFFFFFFFFFFFFFFFFF000000000000000000FFFFFFFFFFFFFFFFFFFFFFFFFF
          FFFFFFFFFFFFFFFFFFFFFF000000FFFFFFFFFFFFFFFFFF000000000000000000
          000000FFFFFF000000FFFFFF000000FFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFF
          FFFFFFFFFFFFFF000000FFFFFFFFFFFFFFFFFF000000FFFFFFFFFFFFFFFFFFFF
          FFFF000000000000000000FFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFF
          FFFFFF000000FFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFF
          FFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFF00
          0000FFFFFFFFFFFF000000000000000000FFFFFFFFFFFFFFFFFFFFFFFFFFFFFF
          FFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFF000000FFFF
          FFFFFFFF000000FFFFFF000000FFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFF
          FFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFF000000FFFFFFFFFFFF
          000000000000000000FFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFF
          FFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFF000000FFFFFFFFFFFFFFFFFFFF
          FFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFF
          FFFFFFFFFFFFFFFFFFFFFFFFFFFFFF000000FFFFFFFFFFFFFFFFFFFFFFFFFFFF
          FFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFF
          FFFFFFFFFFFFFFFFFFFFFF000000}
        ParentDoubleBuffered = False
        ParentFont = False
        TabOrder = 1
        OnClick = BotColapsClick
      end
      object BrvBitBtn1: TBrvBitBtn
        Left = 188
        Top = 1
        Width = 90
        Height = 25
        Caption = 'Imprimir'
        DoubleBuffered = True
        Font.Charset = DEFAULT_CHARSET
        Font.Color = clWindowText
        Font.Height = -11
        Font.Name = 'Tahoma'
        Font.Style = [fsBold]
        NumGlyphs = 2
        ParentDoubleBuffered = False
        ParentFont = False
        TabOrder = 2
        OnClick = BrvBitBtn1Click
        BrTipoBotao = BrBtnImprim
      end
    end
    object PnlConta: TPanel
      Left = 314
      Top = 1
      Width = 444
      Height = 385
      Align = alRight
      TabOrder = 1
      object LblClassi: TLabel
        Left = 10
        Top = 9
        Width = 76
        Height = 13
        Caption = 'Classifica'#231#227'o'
        FocusControl = DBENrClassi
        Font.Charset = DEFAULT_CHARSET
        Font.Color = clWindowText
        Font.Height = -11
        Font.Name = 'MS Sans Serif'
        Font.Style = [fsBold]
        ParentFont = False
      end
      object Label3: TLabel
        Left = 10
        Top = 34
        Width = 34
        Height = 13
        Caption = 'Conta'
        FocusControl = DBENrConta
        Font.Charset = DEFAULT_CHARSET
        Font.Color = clBlue
        Font.Height = -11
        Font.Name = 'MS Sans Serif'
        Font.Style = [fsBold]
        ParentFont = False
      end
      object Label4: TLabel
        Left = 10
        Top = 58
        Width = 88
        Height = 13
        Caption = 'Nome da Conta'
        FocusControl = DBENmConta
        Font.Charset = DEFAULT_CHARSET
        Font.Color = clBlue
        Font.Height = -11
        Font.Name = 'MS Sans Serif'
        Font.Style = [fsBold]
        ParentFont = False
      end
      object LblPrefix: TLabel
        Left = 254
        Top = 9
        Width = 34
        Height = 13
        Caption = 'Prefixo'
        Visible = False
      end
      object Label1: TLabel
        Left = 10
        Top = 80
        Width = 71
        Height = 13
        Caption = 'Conta P.C.R'
        Font.Charset = DEFAULT_CHARSET
        Font.Color = clBlack
        Font.Height = -11
        Font.Name = 'MS Sans Serif'
        Font.Style = [fsBold]
        ParentFont = False
      end
      object Label8: TLabel
        Left = 10
        Top = 247
        Width = 93
        Height = 13
        Caption = 'C'#243'digo natureza'
        Font.Charset = DEFAULT_CHARSET
        Font.Color = clBlack
        Font.Height = -11
        Font.Name = 'MS Sans Serif'
        Font.Style = [fsBold]
        ParentFont = False
      end
      object Label5: TLabel
        Left = 11
        Top = 382
        Width = 38
        Height = 13
        Caption = 'Rateio'
        Font.Charset = DEFAULT_CHARSET
        Font.Color = clWindowText
        Font.Height = -11
        Font.Name = 'MS Sans Serif'
        Font.Style = [fsBold]
        ParentFont = False
        Visible = False
      end
      object Label9: TLabel
        Left = 128
        Top = 379
        Width = 10
        Height = 13
        Caption = '%'
        Font.Charset = DEFAULT_CHARSET
        Font.Color = clWindowText
        Font.Height = -11
        Font.Name = 'MS Sans Serif'
        Font.Style = [fsBold]
        ParentFont = False
        Visible = False
      end
      object LblSulfix: TLabel
        Left = 321
        Top = 9
        Width = 32
        Height = 13
        Caption = 'Sulfixo'
        Visible = False
      end
      object Label2: TLabel
        Left = 10
        Top = 103
        Width = 65
        Height = 13
        Caption = 'Grupo DRE'
        Font.Charset = DEFAULT_CHARSET
        Font.Color = clBlack
        Font.Height = -11
        Font.Name = 'MS Sans Serif'
        Font.Style = [fsBold]
        ParentFont = False
      end
      object Label10: TLabel
        Left = 9
        Top = 127
        Width = 85
        Height = 13
        Caption = 'Grupo Balan'#231'o'
        Font.Charset = DEFAULT_CHARSET
        Font.Color = clBlack
        Font.Height = -11
        Font.Name = 'MS Sans Serif'
        Font.Style = [fsBold]
        ParentFont = False
        Visible = False
      end
      object BrvDBRetCon2: TBrvDBRetCon
        Left = 55
        Top = 377
        Width = 67
        Height = 19
        TabStop = False
        BevelInner = bvLowered
        BevelKind = bkFlat
        BevelWidth = 2
        BorderStyle = bsNone
        DataField = 'PcRateio'
        DataSource = DsItensPlano
        ParentColor = True
        ReadOnly = True
        TabOrder = 17
        Visible = False
      end
      object DBENrClassi: TDBEdit
        Left = 104
        Top = 5
        Width = 140
        Height = 21
        DataField = 'NRCLASSI'
        DataSource = DsItensPlano
        ReadOnly = True
        TabOrder = 0
      end
      object DBENrConta: TDBEdit
        Left = 104
        Top = 29
        Width = 73
        Height = 21
        DataField = 'NRCONTA'
        DataSource = DsItensPlano
        MaxLength = 6
        TabOrder = 1
      end
      object DBENmConta: TDBEdit
        Left = 104
        Top = 52
        Width = 324
        Height = 21
        DataField = 'NMCONTA'
        DataSource = DsItensPlano
        TabOrder = 2
      end
      object BrvDbEdit1: TBrvDbEdit
        Left = 104
        Top = 74
        Width = 73
        Height = 21
        DataField = 'NrConRef'
        DataSource = DsItensPlano
        TabOrder = 3
        BrAlignment = taRightJustify
        BrVisibleButton = True
        BrFunctionButton = TpDbConsulta
        BrConfigurar.Strings = (
          'NrConRef;NrConRef;S;S;'
          'NmConRef;NmConta;S;N;'
          '')
        BrDicionario = DmSrvApl.BrvDicionario
        BrQueryConsulta = 33
      end
      object DBCBSnRecLan: TDBCheckBox
        Left = 11
        Top = 179
        Width = 145
        Height = 17
        Caption = 'Recebe Lan'#231'amento'
        DataField = 'SNRECLAN'
        DataSource = DsItensPlano
        Font.Charset = DEFAULT_CHARSET
        Font.Color = clWindowText
        Font.Height = -11
        Font.Name = 'MS Sans Serif'
        Font.Style = [fsBold]
        ParentFont = False
        TabOrder = 4
        ValueChecked = 'S'
        ValueUnchecked = 'N'
      end
      object DBCBSnBalPat: TDBCheckBox
        Left = 11
        Top = 200
        Width = 233
        Height = 17
        Caption = 'Conta entra no Balan'#231'o Patrimonial'
        DataField = 'SNBALPAT'
        DataSource = DsItensPlano
        Font.Charset = DEFAULT_CHARSET
        Font.Color = clWindowText
        Font.Height = -11
        Font.Name = 'MS Sans Serif'
        Font.Style = [fsBold]
        ParentFont = False
        TabOrder = 5
        ValueChecked = 'S'
        ValueUnchecked = 'N'
      end
      object DBCBSnConCen: TDBCheckBox
        Left = 11
        Top = 222
        Width = 166
        Height = 17
        Caption = 'Recebe centro de custo'
        DataField = 'SNCONCEN'
        DataSource = DsItensPlano
        Font.Charset = DEFAULT_CHARSET
        Font.Color = clWindowText
        Font.Height = -11
        Font.Name = 'MS Sans Serif'
        Font.Style = [fsBold]
        ParentFont = False
        TabOrder = 6
        ValueChecked = 'S'
        ValueUnchecked = 'N'
      end
      object CmbCdNatCon: TBrvDBComboListBox
        Left = 104
        Top = 244
        Width = 145
        Height = 21
        DataField = 'CdNatCon'
        DataSource = DsItensPlano
        TabOrder = 7
      end
      object DBRTpNatOpe: TDBRadioGroup
        Left = 8
        Top = 318
        Width = 353
        Height = 35
        Caption = ' Natureza '
        Columns = 2
        DataField = 'TPNATOPE'
        DataSource = DsItensPlano
        Font.Charset = DEFAULT_CHARSET
        Font.Color = clWindowText
        Font.Height = -11
        Font.Name = 'MS Sans Serif'
        Font.Style = [fsBold]
        Items.Strings = (
          'Credora'
          'Devedora')
        ParentBackground = True
        ParentFont = False
        TabOrder = 8
        Values.Strings = (
          'C'
          'D')
        Visible = False
      end
      object DBCBSnRatear: TDBCheckBox
        Left = 11
        Top = 357
        Width = 145
        Height = 17
        Caption = 'Conta Rateada'
        DataField = 'SNRATEAR'
        DataSource = DsItensPlano
        Font.Charset = DEFAULT_CHARSET
        Font.Color = clWindowText
        Font.Height = -11
        Font.Name = 'MS Sans Serif'
        Font.Style = [fsBold]
        ParentFont = False
        TabOrder = 9
        ValueChecked = 'S'
        ValueUnchecked = 'N'
        Visible = False
      end
      object BntAltRat: TBitBtn
        Left = 183
        Top = 370
        Width = 163
        Height = 25
        Caption = '&Atualizar rateio'
        DoubleBuffered = True
        Font.Charset = DEFAULT_CHARSET
        Font.Color = clWindowText
        Font.Height = -11
        Font.Name = 'MS Sans Serif'
        Font.Style = [fsBold]
        Glyph.Data = {
          DE010000424DDE01000000000000760000002800000024000000120000000100
          0400000000006801000000000000000000001000000000000000000000000000
          80000080000000808000800000008000800080800000C0C0C000808080000000
          FF0000FF000000FFFF00FF000000FF00FF00FFFF0000FFFFFF00333333444444
          33333333333F8888883F33330000324334222222443333388F3833333388F333
          000032244222222222433338F8833FFFFF338F3300003222222AAAAA22243338
          F333F88888F338F30000322222A33333A2224338F33F8333338F338F00003222
          223333333A224338F33833333338F38F00003222222333333A444338FFFF8F33
          3338888300003AAAAAAA33333333333888888833333333330000333333333333
          333333333333333333FFFFFF000033333333333344444433FFFF333333888888
          00003A444333333A22222438888F333338F3333800003A2243333333A2222438
          F38F333333833338000033A224333334422224338338FFFFF8833338000033A2
          22444442222224338F3388888333FF380000333A2222222222AA243338FF3333
          33FF88F800003333AA222222AA33A3333388FFFFFF8833830000333333AAAAAA
          3333333333338888883333330000333333333333333333333333333333333333
          0000}
        NumGlyphs = 2
        ParentDoubleBuffered = False
        ParentFont = False
        TabOrder = 10
        Visible = False
        OnClick = BntAltRatClick
      end
      object BtnGravar: TBrvBitBtn
        Left = 64
        Top = 285
        Width = 90
        Height = 25
        Caption = 'Gravar'
        DoubleBuffered = True
        Enabled = False
        Font.Charset = DEFAULT_CHARSET
        Font.Color = clWindowText
        Font.Height = -11
        Font.Name = 'Tahoma'
        Font.Style = [fsBold]
        NumGlyphs = 2
        ParentDoubleBuffered = False
        ParentFont = False
        TabOrder = 11
        OnClick = BtnGravarClick
        BrTipoBotao = BrBtnSalvar
      end
      object BtnCancel: TBrvBitBtn
        Left = 168
        Top = 285
        Width = 90
        Height = 25
        Caption = 'Cancelar'
        DoubleBuffered = True
        Enabled = False
        Font.Charset = DEFAULT_CHARSET
        Font.Color = clWindowText
        Font.Height = -11
        Font.Name = 'Tahoma'
        Font.Style = [fsBold]
        NumGlyphs = 2
        ParentDoubleBuffered = False
        ParentFont = False
        TabOrder = 12
        OnClick = BtnCancelClick
        BrTipoBotao = BrBtnCancel
      end
      object BotExclui: TBrvBitBtn
        Left = 272
        Top = 285
        Width = 90
        Height = 25
        Caption = 'Excluir'
        DoubleBuffered = True
        Enabled = False
        Font.Charset = DEFAULT_CHARSET
        Font.Color = clWindowText
        Font.Height = -11
        Font.Name = 'Tahoma'
        Font.Style = [fsBold]
        NumGlyphs = 2
        ParentDoubleBuffered = False
        ParentFont = False
        TabOrder = 13
        OnClick = BotExcluiClick
        BrTipoBotao = BrBtnExcluir
      end
      object MKENrClassi: TMaskEdit
        Left = 292
        Top = 5
        Width = 20
        Height = 23
        Font.Charset = ANSI_CHARSET
        Font.Color = clWindowText
        Font.Height = -12
        Font.Name = 'Courier New'
        Font.Style = []
        ParentFont = False
        ReadOnly = True
        TabOrder = 14
        Visible = False
      end
      object BrvDBRetCon1: TBrvDBRetCon
        Left = 177
        Top = 74
        Width = 251
        Height = 19
        TabStop = False
        BevelInner = bvLowered
        BevelKind = bkFlat
        BevelWidth = 2
        BorderStyle = bsNone
        DataField = 'NmConRef'
        DataSource = DsItensPlano
        ParentColor = True
        ReadOnly = True
        TabOrder = 15
      end
      object PnlRateio: TPanel
        Left = 8
        Top = 401
        Width = 425
        Height = 372
        TabOrder = 16
        Visible = False
        object DBGRateio: TDBGrid
          Left = 1
          Top = 29
          Width = 423
          Height = 301
          Align = alClient
          DataSource = DsTemp
          Options = [dgEditing, dgTitles, dgIndicator, dgColumnResize, dgColLines, dgRowLines, dgTabs, dgCancelOnExit]
          TabOrder = 0
          TitleFont.Charset = DEFAULT_CHARSET
          TitleFont.Color = clWindowText
          TitleFont.Height = -11
          TitleFont.Name = 'Tahoma'
          TitleFont.Style = []
          Columns = <
            item
              Expanded = False
              FieldName = 'NmConta'
              ReadOnly = True
              Title.Caption = 'Conta'
              Width = 256
              Visible = True
            end
            item
              Expanded = False
              FieldName = 'PcRateio'
              Title.Caption = '% Rateio'
              Visible = True
            end>
        end
        object Panel1: TPanel
          Left = 1
          Top = 1
          Width = 423
          Height = 28
          Align = alTop
          BorderStyle = bsSingle
          Caption = 'Atualizar Rateio'
          Font.Charset = DEFAULT_CHARSET
          Font.Color = clWindowText
          Font.Height = -19
          Font.Name = 'MS Sans Serif'
          Font.Style = [fsBold]
          ParentFont = False
          TabOrder = 1
        end
        object Panel2: TPanel
          Left = 1
          Top = 330
          Width = 423
          Height = 41
          Align = alBottom
          BorderStyle = bsSingle
          TabOrder = 2
          object Label6: TLabel
            Left = 227
            Top = 12
            Width = 30
            Height = 13
            Caption = 'Total'
            Font.Charset = DEFAULT_CHARSET
            Font.Color = clWindowText
            Font.Height = -11
            Font.Name = 'MS Sans Serif'
            Font.Style = [fsBold]
            ParentFont = False
          end
          object Label7: TLabel
            Left = 327
            Top = 12
            Width = 10
            Height = 13
            Caption = '%'
            Font.Charset = DEFAULT_CHARSET
            Font.Color = clWindowText
            Font.Height = -11
            Font.Name = 'MS Sans Serif'
            Font.Style = [fsBold]
            ParentFont = False
          end
          object SttTtRateio: TStaticText
            Left = 262
            Top = 7
            Width = 63
            Height = 18
            Alignment = taRightJustify
            AutoSize = False
            BorderStyle = sbsSunken
            Caption = '0,00'
            Font.Charset = DEFAULT_CHARSET
            Font.Color = clWindowText
            Font.Height = -11
            Font.Name = 'MS Sans Serif'
            Font.Style = [fsBold]
            ParentFont = False
            TabOrder = 0
          end
          object BntOkRate: TBitBtn
            Left = 4
            Top = 9
            Width = 75
            Height = 24
            Caption = '&Ok'
            DoubleBuffered = True
            Glyph.Data = {
              DE010000424DDE01000000000000760000002800000024000000120000000100
              0400000000006801000000000000000000001000000000000000000000000000
              80000080000000808000800000008000800080800000C0C0C000808080000000
              FF0000FF000000FFFF00FF000000FF00FF00FFFF0000FFFFFF00333333333333
              3333333333333333333333330000333333333333333333333333F33333333333
              00003333344333333333333333388F3333333333000033334224333333333333
              338338F3333333330000333422224333333333333833338F3333333300003342
              222224333333333383333338F3333333000034222A22224333333338F338F333
              8F33333300003222A3A2224333333338F3838F338F33333300003A2A333A2224
              33333338F83338F338F33333000033A33333A222433333338333338F338F3333
              0000333333333A222433333333333338F338F33300003333333333A222433333
              333333338F338F33000033333333333A222433333333333338F338F300003333
              33333333A222433333333333338F338F00003333333333333A22433333333333
              3338F38F000033333333333333A223333333333333338F830000333333333333
              333A333333333333333338330000333333333333333333333333333333333333
              0000}
            NumGlyphs = 2
            ParentDoubleBuffered = False
            TabOrder = 1
            OnClick = BntOkRateClick
          end
        end
      end
      object BrvDbEdit2: TBrvDbEdit
        Left = 104
        Top = 97
        Width = 73
        Height = 21
        DataField = 'NrIteDRE'
        DataSource = DsItensPlano
        TabOrder = 18
        BrAlignment = taRightJustify
        BrVisibleButton = True
        BrFunctionButton = TpDbConsulta
        BrConfigurar.Strings = (
          'NrPlaCon;NrPlaCon;N;S;'
          'NrIteDRE;NrIteDRE;S;S;'
          'DsIteDRE;DsIteDRE;S;N;')
        BrDicionario = DmSrvApl.BrvDicionario
        BrQueryConsulta = 52
      end
      object BrvDbEdit3: TBrvDbEdit
        Left = 103
        Top = 121
        Width = 73
        Height = 21
        DataSource = DsItensPlano
        TabOrder = 19
        Visible = False
        BrAlignment = taRightJustify
        BrVisibleButton = True
        BrFunctionButton = TpDbConsulta
        BrConfigurar.Strings = (
          'NrConRef;NrConRef;S;S;'
          'NmConRef;NmConta;S;N;'
          '')
        BrDicionario = DmSrvApl.BrvDicionario
        BrQueryConsulta = 33
      end
      object BrvDBRetCon4: TBrvDBRetCon
        Left = 176
        Top = 121
        Width = 251
        Height = 19
        TabStop = False
        BevelInner = bvLowered
        BevelKind = bkFlat
        BevelWidth = 2
        BorderStyle = bsNone
        DataField = 'NmConRef'
        DataSource = DsItensPlano
        ParentColor = True
        ReadOnly = True
        TabOrder = 20
        Visible = False
      end
      object BrvDBRetCon3: TBrvDBRetCon
        Left = 177
        Top = 97
        Width = 251
        Height = 19
        TabStop = False
        BevelInner = bvLowered
        BevelKind = bkFlat
        BevelWidth = 2
        BorderStyle = bsNone
        DataField = 'DsIteDRE'
        DataSource = DsItensPlano
        ParentColor = True
        ReadOnly = True
        TabOrder = 21
      end
    end
    object TrvPlano: TTreeView
      Left = 1
      Top = 1
      Width = 310
      Height = 385
      Align = alClient
      Indent = 16
      PopupMenu = PopPlano
      ReadOnly = True
      TabOrder = 2
      OnChange = TrvPlanoChange
    end
  end
  object PopPlano: TPopupMenu
    OnPopup = PopPlanoPopup
    Left = 242
    Top = 43
    object Novo1: TMenuItem
      Caption = '&Novo item de conta'
      ShortCut = 16462
      OnClick = Novo1Click
    end
    object nOvaconta1: TMenuItem
      Caption = 'n&Ova conta'
      ShortCut = 16463
      OnClick = nOvaconta1Click
    end
    object N1: TMenuItem
      Caption = '-'
    end
    object Excluir1: TMenuItem
      Caption = '&Excluir'
      ShortCut = 16452
      OnClick = Excluir1Click
    end
  end
  object CCItensPlano: TBrvClientDataSet
    Aggregates = <>
    CommandText = 'Select * From S008 Where NmTabela is null'
    Params = <>
    ProviderName = 'DCItensPlano'
    RemoteServer = DmSrvApl.PvcSDmCon
    BrShowFieldName = False
    BrQueryCode = 73
    BrDicionario = DmSrvApl.BrvDicionario
    BrType = VqNormal
    BrGravaLog = True
    BrUserCode = 0
    Left = 32
    Top = 48
  end
  object DsItensPlano: TDataSource
    DataSet = CCItensPlano
    OnStateChange = DsItensPlanoStateChange
    Left = 112
    Top = 46
  end
  object QCClassifica: TBrvClientDataSet
    Aggregates = <>
    CommandText = 'Select * From S008 Where NmTabela is null'
    Params = <>
    ProviderName = 'DCClassifica'
    RemoteServer = DmSrvApl.PvcSDmCon
    BrShowFieldName = False
    BrQueryCode = 74
    BrDicionario = DmSrvApl.BrvDicionario
    BrType = VqNormal
    BrGravaLog = True
    BrUserCode = 0
    Left = 32
    Top = 96
  end
  object QcProxConta: TBrvClientDataSet
    Aggregates = <>
    CommandText = 'Select * From S008 Where NmTabela is null'
    Params = <>
    ProviderName = 'DCClassifica'
    RemoteServer = DmSrvApl.PvcSDmCon
    BrShowFieldName = False
    BrQueryCode = 75
    BrDicionario = DmSrvApl.BrvDicionario
    BrType = VqNormal
    BrGravaLog = True
    BrUserCode = 0
    Left = 32
    Top = 152
  end
  object TblTemp: TClientDataSet
    Aggregates = <>
    FieldDefs = <>
    IndexDefs = <
      item
        Name = 'TblTempIndex2'
        Fields = 'CdPlaCon;NrClassi'
        Options = [ixUnique]
      end>
    Params = <>
    StoreDefs = True
    AfterInsert = TblTempAfterInsert
    BeforeEdit = TblTempBeforeEdit
    AfterPost = TblTempAfterPost
    BeforeDelete = TblTempBeforeDelete
    Left = 32
    Top = 208
    object TblTempCdPlaCon: TIntegerField
      FieldName = 'CdPlaCon'
    end
    object TblTempNrClassi: TStringField
      FieldName = 'NrClassi'
    end
    object TblTempNmConta: TStringField
      FieldName = 'NmConta'
      Size = 60
    end
    object TblTempPcRateio: TFloatField
      FieldName = 'PcRateio'
    end
  end
  object QPPlaConRateio: TBrvClientDataSet
    Aggregates = <>
    CommandText = 'Select * From S008 Where NmTabela is null'
    Params = <>
    ProviderName = 'DCClassifica'
    RemoteServer = DmSrvApl.PvcSDmCon
    BrShowFieldName = False
    BrQueryCode = 75
    BrDicionario = DmSrvApl.BrvDicionario
    BrType = VqNormal
    BrGravaLog = True
    BrUserCode = 0
    Left = 120
    Top = 96
  end
  object DsTemp: TDataSource
    DataSet = TblTemp
    Left = 94
    Top = 211
  end
  object BrvGerRel: TBrvGeraRelatorio
    BrDicionario = DmSrvApl.BrvDicionario
    SQLConnectionImp = DmSrvApl.SrvImpres
    BrCdsData = CdsImprim
    Left = 664
    Top = 144
  end
  object BrvExport: TBrvExport
    BrDicionario = DmSrvApl.BrvDicionario
    Left = 128
    Top = 326
  end
  object CdsImprim: TClientDataSet
    Aggregates = <>
    Params = <>
    Left = 40
    Top = 310
    object CdsImprimDsPlano: TMemoField
      FieldName = 'DsPlano'
      BlobType = ftMemo
    end
  end
end
