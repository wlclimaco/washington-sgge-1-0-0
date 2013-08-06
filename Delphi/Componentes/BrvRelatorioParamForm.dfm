object RelParame: TRelParame
  Left = 561
  Top = 126
  BorderIcons = [biSystemMenu]
  BorderStyle = bsSingle
  Caption = 'Escolha dos Campos'
  ClientHeight = 446
  ClientWidth = 351
  Color = clBtnFace
  Font.Charset = DEFAULT_CHARSET
  Font.Color = clWindowText
  Font.Height = -11
  Font.Name = 'MS Sans Serif'
  Font.Style = []
  KeyPreview = True
  OldCreateOrder = False
  Position = poScreenCenter
  OnCloseQuery = FormCloseQuery
  OnKeyUp = FormKeyUp
  PixelsPerInch = 96
  TextHeight = 13
  object PnlFundo: TPanel
    Left = 0
    Top = 0
    Width = 351
    Height = 446
    Align = alClient
    BorderStyle = bsSingle
    TabOrder = 0
    object GbxColunas: TGroupBox
      Left = 1
      Top = 47
      Width = 345
      Height = 241
      Align = alTop
      Caption = 'Colunas'
      Font.Charset = DEFAULT_CHARSET
      Font.Color = clBlue
      Font.Height = -11
      Font.Name = 'MS Sans Serif'
      Font.Style = [fsBold]
      ParentFont = False
      TabOrder = 0
      object ClbColuna: TCheckListBox
        Left = 2
        Top = 15
        Width = 235
        Height = 224
        Align = alLeft
        Font.Charset = DEFAULT_CHARSET
        Font.Color = clWindowText
        Font.Height = -11
        Font.Name = 'MS Sans Serif'
        Font.Style = []
        ItemHeight = 13
        ParentFont = False
        TabOrder = 0
        OnClick = ClbColunaClick
      end
      object GbxPosica: TGroupBox
        Left = 240
        Top = 8
        Width = 101
        Height = 69
        Caption = 'Posi'#231#227'o'
        Font.Charset = DEFAULT_CHARSET
        Font.Color = clBlack
        Font.Height = -11
        Font.Name = 'MS Sans Serif'
        Font.Style = [fsBold]
        ParentFont = False
        TabOrder = 1
        object SbtAcima: TSpeedButton
          Left = 5
          Top = 14
          Width = 93
          Height = 25
          Caption = '&Acima'
          Flat = True
          Font.Charset = DEFAULT_CHARSET
          Font.Color = clWindowText
          Font.Height = -11
          Font.Name = 'MS Sans Serif'
          Font.Style = []
          Glyph.Data = {
            76010000424D7601000000000000760000002800000020000000100000000100
            04000000000000010000120B0000120B00001000000000000000000000000000
            800000800000008080008000000080008000808000007F7F7F00BFBFBF000000
            FF0000FF000000FFFF00FF000000FF00FF00FFFF0000FFFFFF00333333000333
            3333333333777F33333333333309033333333333337F7F333333333333090333
            33333333337F7F33333333333309033333333333337F7F333333333333090333
            33333333337F7F33333333333309033333333333FF7F7FFFF333333000090000
            3333333777737777F333333099999990333333373F3333373333333309999903
            333333337F33337F33333333099999033333333373F333733333333330999033
            3333333337F337F3333333333099903333333333373F37333333333333090333
            33333333337F7F33333333333309033333333333337373333333333333303333
            333333333337F333333333333330333333333333333733333333}
          NumGlyphs = 2
          ParentFont = False
          OnClick = SbtAcimaClick
        end
        object SbtAbaixo: TSpeedButton
          Left = 5
          Top = 39
          Width = 93
          Height = 25
          Caption = 'A&baixo'
          Flat = True
          Font.Charset = DEFAULT_CHARSET
          Font.Color = clWindowText
          Font.Height = -11
          Font.Name = 'MS Sans Serif'
          Font.Style = []
          Glyph.Data = {
            76010000424D7601000000000000760000002800000020000000100000000100
            04000000000000010000120B0000120B00001000000000000000000000000000
            800000800000008080008000000080008000808000007F7F7F00BFBFBF000000
            FF0000FF000000FFFF00FF000000FF00FF00FFFF0000FFFFFF00333333303333
            333333333337F33333333333333033333333333333373F333333333333090333
            33333333337F7F33333333333309033333333333337373F33333333330999033
            3333333337F337F33333333330999033333333333733373F3333333309999903
            333333337F33337F33333333099999033333333373333373F333333099999990
            33333337FFFF3FF7F33333300009000033333337777F77773333333333090333
            33333333337F7F33333333333309033333333333337F7F333333333333090333
            33333333337F7F33333333333309033333333333337F7F333333333333090333
            33333333337F7F33333333333300033333333333337773333333}
          NumGlyphs = 2
          ParentFont = False
          OnClick = SbtAbaixoClick
        end
      end
      object GbxMarcar: TGroupBox
        Left = 240
        Top = 77
        Width = 101
        Height = 69
        BiDiMode = bdLeftToRight
        Caption = 'Marcar'
        Font.Charset = DEFAULT_CHARSET
        Font.Color = clBlack
        Font.Height = -11
        Font.Name = 'MS Sans Serif'
        Font.Style = [fsBold]
        ParentBiDiMode = False
        ParentFont = False
        TabOrder = 2
        object SbtTodos: TSpeedButton
          Left = 5
          Top = 14
          Width = 93
          Height = 25
          Caption = '&Todos'
          Flat = True
          Font.Charset = DEFAULT_CHARSET
          Font.Color = clWindowText
          Font.Height = -11
          Font.Name = 'MS Sans Serif'
          Font.Style = []
          Glyph.Data = {
            F2010000424DF201000000000000760000002800000024000000130000000100
            0400000000007C01000000000000000000001000000000000000000000000000
            80000080000000808000800000008000800080800000C0C0C000808080000000
            FF0000FF000000FFFF00FF000000FF00FF00FFFF0000FFFFFF00333334433333
            3333333333388F3333333333000033334224333333333333338338F333333333
            0000333422224333333333333833338F33333333000033422222243333333333
            83333338F3333333000034222A22224333333338F33F33338F33333300003222
            A2A2224333333338F383F3338F33333300003A2A222A222433333338F8333F33
            38F33333000034A22222A22243333338833333F3338F333300004222A2222A22
            2433338F338F333F3338F3330000222A3A2224A22243338F3838F338F3338F33
            0000A2A333A2224A2224338F83338F338F3338F300003A33333A2224A2224338
            333338F338F3338F000033333333A2224A2243333333338F338F338F00003333
            33333A2224A2233333333338F338F83300003333333333A2224A333333333333
            8F338F33000033333333333A222433333333333338F338F30000333333333333
            A224333333333333338F38F300003333333333333A223333333333333338F8F3
            000033333333333333A3333333333333333383330000}
          NumGlyphs = 2
          ParentFont = False
          OnClick = SbtTodosClick
        end
        object SbtNenhum: TSpeedButton
          Left = 5
          Top = 39
          Width = 93
          Height = 25
          Caption = '&Nenhum'
          Flat = True
          Font.Charset = DEFAULT_CHARSET
          Font.Color = clWindowText
          Font.Height = -11
          Font.Name = 'MS Sans Serif'
          Font.Style = []
          Glyph.Data = {
            DE010000424DDE01000000000000760000002800000024000000120000000100
            0400000000006801000000000000000000001000000000000000000000000000
            80000080000000808000800000008000800080800000C0C0C000808080000000
            FF0000FF000000FFFF00FF000000FF00FF00FFFF0000FFFFFF00333333333333
            3333333333333FFFFF333333000033333388888833333333333F888888FFF333
            000033338811111188333333338833FFF388FF33000033381119999111833333
            38F338888F338FF30000339119933331111833338F388333383338F300003391
            13333381111833338F8F3333833F38F3000039118333381119118338F38F3338
            33F8F38F000039183333811193918338F8F333833F838F8F0000391833381119
            33918338F8F33833F8338F8F000039183381119333918338F8F3833F83338F8F
            000039183811193333918338F8F833F83333838F000039118111933339118338
            F3833F83333833830000339111193333391833338F33F8333FF838F300003391
            11833338111833338F338FFFF883F83300003339111888811183333338FF3888
            83FF83330000333399111111993333333388FFFFFF8833330000333333999999
            3333333333338888883333330000333333333333333333333333333333333333
            0000}
          NumGlyphs = 2
          ParentFont = False
          OnClick = SbtNenhumClick
        end
      end
      object RgbFolha: TRadioGroup
        Left = 240
        Top = 192
        Width = 101
        Height = 46
        Caption = 'Folha'
        Font.Charset = DEFAULT_CHARSET
        Font.Color = clBlack
        Font.Height = -11
        Font.Name = 'MS Sans Serif'
        Font.Style = [fsBold]
        ItemIndex = 0
        Items.Strings = (
          'Retrato'
          'Paisagem')
        ParentFont = False
        TabOrder = 3
        OnClick = ClbColunaClick
      end
      object RgbImpres: TRadioGroup
        Left = 242
        Top = 145
        Width = 101
        Height = 46
        Caption = 'Impress'#227'o'
        Font.Charset = DEFAULT_CHARSET
        Font.Color = clBlack
        Font.Height = -11
        Font.Name = 'MS Sans Serif'
        Font.Style = [fsBold]
        ItemIndex = 0
        Items.Strings = (
          'Gr'#225'fica'
          'Caracter')
        ParentFont = False
        TabOrder = 4
        OnClick = RgbImpresClick
      end
    end
    object GbxLayout: TGroupBox
      Left = 1
      Top = 1
      Width = 345
      Height = 46
      Align = alTop
      Caption = 'Layout'
      Font.Charset = DEFAULT_CHARSET
      Font.Color = clWindowText
      Font.Height = -11
      Font.Name = 'MS Sans Serif'
      Font.Style = [fsBold]
      ParentFont = False
      TabOrder = 1
      object SbtDelLay: TSpeedButton
        Left = 317
        Top = 16
        Width = 23
        Height = 22
        Hint = 'Excluir layout'
        Glyph.Data = {
          DE010000424DDE01000000000000760000002800000024000000120000000100
          0400000000006801000000000000000000001000000000000000000000000000
          80000080000000808000800000008000800080800000C0C0C000808080000000
          FF0000FF000000FFFF00FF000000FF00FF00FFFF0000FFFFFF00333333333333
          333333333333333333333333000033338833333333333333333F333333333333
          0000333911833333983333333388F333333F3333000033391118333911833333
          38F38F333F88F33300003339111183911118333338F338F3F8338F3300003333
          911118111118333338F3338F833338F3000033333911111111833333338F3338
          3333F8330000333333911111183333333338F333333F83330000333333311111
          8333333333338F3333383333000033333339111183333333333338F333833333
          00003333339111118333333333333833338F3333000033333911181118333333
          33338333338F333300003333911183911183333333383338F338F33300003333
          9118333911183333338F33838F338F33000033333913333391113333338FF833
          38F338F300003333333333333919333333388333338FFF830000333333333333
          3333333333333333333888330000333333333333333333333333333333333333
          0000}
        NumGlyphs = 2
        ParentShowHint = False
        ShowHint = True
        OnClick = SbtDelLayClick
      end
      object CbxLayout: TComboBox
        Left = 4
        Top = 16
        Width = 310
        Height = 21
        Style = csDropDownList
        Font.Charset = DEFAULT_CHARSET
        Font.Color = clWindowText
        Font.Height = -11
        Font.Name = 'MS Sans Serif'
        Font.Style = []
        ParentFont = False
        TabOrder = 0
        OnChange = CbxLayoutChange
      end
    end
    object GbxTitulo: TGroupBox
      Left = 1
      Top = 288
      Width = 345
      Height = 41
      Align = alTop
      Caption = 'T'#237'tulo'
      Font.Charset = DEFAULT_CHARSET
      Font.Color = clWindowText
      Font.Height = -11
      Font.Name = 'MS Sans Serif'
      Font.Style = [fsBold]
      ParentFont = False
      TabOrder = 2
      object EdtDsTitulo: TEdit
        Left = 4
        Top = 14
        Width = 334
        Height = 21
        Font.Charset = DEFAULT_CHARSET
        Font.Color = clWindowText
        Font.Height = -11
        Font.Name = 'MS Sans Serif'
        Font.Style = []
        ParentFont = False
        TabOrder = 0
        OnKeyPress = EdtDsTituloKeyPress
      end
    end
    object GbxObserv: TGroupBox
      Left = 1
      Top = 329
      Width = 345
      Height = 112
      Align = alClient
      Caption = ' Complemento do t'#237'tulo'
      Font.Charset = DEFAULT_CHARSET
      Font.Color = clWindowText
      Font.Height = -11
      Font.Name = 'MS Sans Serif'
      Font.Style = [fsBold]
      ParentFont = False
      TabOrder = 3
      object MemObserv: TMemo
        Left = 2
        Top = 15
        Width = 341
        Height = 65
        Align = alTop
        Font.Charset = DEFAULT_CHARSET
        Font.Color = clWindowText
        Font.Height = -11
        Font.Name = 'MS Sans Serif'
        Font.Style = []
        ParentFont = False
        ReadOnly = True
        ScrollBars = ssHorizontal
        TabOrder = 0
      end
      object SbtOk: TBrvBitBtn
        Left = 74
        Top = 83
        Width = 75
        Height = 25
        Caption = '&Ok'
        Default = True
        DoubleBuffered = True
        Font.Charset = DEFAULT_CHARSET
        Font.Color = clWindowText
        Font.Height = -11
        Font.Name = 'MS Sans Serif'
        Font.Style = []
        NumGlyphs = 2
        ParentDoubleBuffered = False
        ParentFont = False
        TabOrder = 1
        OnClick = SbtOkClick
        BrTipoBotao = BrBtnOk
      end
      object SbtCancel: TBrvBitBtn
        Left = 176
        Top = 83
        Width = 75
        Height = 25
        Cancel = True
        Caption = '&Cancelar'
        DoubleBuffered = True
        Font.Charset = DEFAULT_CHARSET
        Font.Color = clWindowText
        Font.Height = -11
        Font.Name = 'MS Sans Serif'
        Font.Style = []
        ModalResult = 2
        NumGlyphs = 2
        ParentDoubleBuffered = False
        ParentFont = False
        TabOrder = 2
        BrTipoBotao = BrBtnCancel
      end
    end
  end
  object CcConfRelUsr: TBrvClientDataSet
    Aggregates = <>
    Params = <>
    ProviderName = 'DcConfRelUsr'
    RemoteServer = DmSrvApl.PvcSDmSis
    BrShowFieldName = False
    BrQueryCode = 0
    BrType = VqNormal
    Left = 181
    Top = 117
  end
  object CcConfColRelUsr: TBrvClientDataSet
    Aggregates = <>
    Params = <>
    ProviderName = 'DcConfColRelUsr'
    RemoteServer = DmSrvApl.PvcSDmSis
    BrShowFieldName = False
    BrQueryCode = 0
    BrType = VqNormal
    Left = 181
    Top = 177
  end
end
