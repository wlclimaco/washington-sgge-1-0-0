object FrmConsulta: TFrmConsulta
  Left = 189
  Top = 103
  BorderIcons = [biSystemMenu]
  BorderStyle = bsSingle
  Caption = 'Consulta'
  ClientHeight = 388
  ClientWidth = 591
  Color = clBtnFace
  Font.Charset = DEFAULT_CHARSET
  Font.Color = clWindowText
  Font.Height = -11
  Font.Name = 'MS Sans Serif'
  Font.Style = []
  KeyPreview = True
  OldCreateOrder = False
  Position = poScreenCenter
  OnClose = FormClose
  OnCreate = FormCreate
  OnKeyPress = FormKeyPress
  PixelsPerInch = 96
  TextHeight = 13
  object PnlFiltro: TPanel
    Left = 0
    Top = 33
    Width = 591
    Height = 97
    Align = alTop
    BorderStyle = bsSingle
    TabOrder = 0
    object Label1: TLabel
      Left = 6
      Top = 14
      Width = 68
      Height = 13
      Caption = 'Ordenar por'
      Font.Charset = DEFAULT_CHARSET
      Font.Color = clWindowText
      Font.Height = -11
      Font.Name = 'MS Sans Serif'
      Font.Style = [fsBold]
      ParentFont = False
    end
    object Label6: TLabel
      Left = 310
      Top = 14
      Width = 26
      Height = 13
      Caption = 'Tipo'
      Font.Charset = DEFAULT_CHARSET
      Font.Color = clWindowText
      Font.Height = -11
      Font.Name = 'MS Sans Serif'
      Font.Style = [fsBold]
      ParentFont = False
    end
    object CbxOrdem: TBrvComboBox
      Left = 92
      Top = 11
      Width = 210
      Height = 21
      Style = csDropDownList
      TabOrder = 1
      OnChange = CbxOrdemChange
    end
    object Panel2: TPanel
      Left = 1
      Top = 37
      Width = 585
      Height = 55
      Align = alBottom
      BevelOuter = bvNone
      TabOrder = 0
      object GroupBox1: TGroupBox
        Left = 0
        Top = 0
        Width = 305
        Height = 55
        Align = alLeft
        Caption = ' 1'#186' filtro '
        Font.Charset = DEFAULT_CHARSET
        Font.Color = clWindowText
        Font.Height = -11
        Font.Name = 'MS Sans Serif'
        Font.Style = [fsBold]
        ParentFont = False
        TabOrder = 0
        object Label2: TLabel
          Left = 3
          Top = 14
          Width = 40
          Height = 13
          Caption = 'Coluna'
          Font.Charset = DEFAULT_CHARSET
          Font.Color = clWindowText
          Font.Height = -11
          Font.Name = 'MS Sans Serif'
          Font.Style = [fsBold]
          ParentFont = False
        end
        object LblTpWhere: TLabel
          Left = 3
          Top = 36
          Width = 81
          Height = 13
          Caption = 'Maior ou igual'
          Font.Charset = DEFAULT_CHARSET
          Font.Color = clWindowText
          Font.Height = -11
          Font.Name = 'MS Sans Serif'
          Font.Style = [fsBold]
          ParentFont = False
        end
        object EdtNrLocali: TBrvEditNum
          Left = 191
          Top = 16
          Width = 97
          Height = 21
          Font.Charset = DEFAULT_CHARSET
          Font.Color = clWindowText
          Font.Height = -11
          Font.Name = 'MS Sans Serif'
          Font.Style = []
          ParentFont = False
          TabOrder = 1
          Text = '0,00'
          Visible = False
          OnKeyPress = EdtDsLocaliKeyPress
          BrAlignment = taRightJustify
          BrCasasDecimais = 2
          BrSepararMilhar = False
          BrAsInteger = 0
          BrAsFloatSQL = '0.00'
          BrVisibleButton = False
          BrFunctionButton = TpConsulta
          BrQueryCode = 0
          BrRecordar = False
        end
        object CbxDsLocali: TBrvComboBox
          Left = 32
          Top = 8
          Width = 210
          Height = 21
          Style = csDropDownList
          Font.Charset = DEFAULT_CHARSET
          Font.Color = clWindowText
          Font.Height = -11
          Font.Name = 'MS Sans Serif'
          Font.Style = []
          ParentFont = False
          TabOrder = 3
          OnChange = CbxDsLocaliChange
        end
        object EdtDtLocali: TBrvEditDate
          Left = 62
          Top = 16
          Width = 90
          Height = 21
          EditMask = '99/99/9999;1; '
          Font.Charset = DEFAULT_CHARSET
          Font.Color = clWindowText
          Font.Height = -11
          Font.Name = 'MS Sans Serif'
          Font.Style = []
          MaxLength = 10
          ParentFont = False
          TabOrder = 0
          Text = '  /  /    '
          Visible = False
          OnKeyPress = EdtDsLocaliKeyPress
          BrDataValida = False
          BrDataVazia = True
          BrFunctionButton = TVdData
          BrAlignment = taLeftJustify
          BrRecordar = False
        end
        object EdtDsLocali: TEdit
          Left = 91
          Top = 30
          Width = 210
          Height = 21
          Font.Charset = DEFAULT_CHARSET
          Font.Color = clWindowText
          Font.Height = -11
          Font.Name = 'MS Sans Serif'
          Font.Style = []
          ParentFont = False
          TabOrder = 2
          OnKeyPress = EdtDsLocaliKeyPress
        end
        object CbxPesqui: TComboBox
          Left = 91
          Top = 8
          Width = 210
          Height = 21
          Style = csDropDownList
          Font.Charset = DEFAULT_CHARSET
          Font.Color = clWindowText
          Font.Height = -11
          Font.Name = 'MS Sans Serif'
          Font.Style = []
          ParentFont = False
          TabOrder = 4
          OnChange = CbxPesquiChange
        end
      end
      object GroupBox2: TGroupBox
        Left = 305
        Top = 0
        Width = 280
        Height = 55
        Align = alClient
        Caption = ' 2'#186' filtro '
        Font.Charset = DEFAULT_CHARSET
        Font.Color = clWindowText
        Font.Height = -11
        Font.Name = 'MS Sans Serif'
        Font.Style = [fsBold]
        ParentFont = False
        TabOrder = 1
        object Label4: TLabel
          Left = 4
          Top = 14
          Width = 40
          Height = 13
          Caption = 'Coluna'
          Font.Charset = DEFAULT_CHARSET
          Font.Color = clWindowText
          Font.Height = -11
          Font.Name = 'MS Sans Serif'
          Font.Style = [fsBold]
          ParentFont = False
        end
        object Label5: TLabel
          Left = 4
          Top = 36
          Width = 29
          Height = 13
          Caption = 'Igual'
          Font.Charset = DEFAULT_CHARSET
          Font.Color = clWindowText
          Font.Height = -11
          Font.Name = 'MS Sans Serif'
          Font.Style = [fsBold]
          ParentFont = False
        end
        object CbxDsLocal2: TBrvComboBox
          Left = 64
          Top = 24
          Width = 210
          Height = 21
          Style = csDropDownList
          Font.Charset = DEFAULT_CHARSET
          Font.Color = clWindowText
          Font.Height = -11
          Font.Name = 'MS Sans Serif'
          Font.Style = []
          ParentFont = False
          TabOrder = 3
          OnChange = CbxDsLocal2Change
        end
        object EdtDtLocal2: TBrvEditDate
          Left = 62
          Top = 32
          Width = 90
          Height = 21
          EditMask = '99/99/9999;1; '
          Font.Charset = DEFAULT_CHARSET
          Font.Color = clWindowText
          Font.Height = -11
          Font.Name = 'MS Sans Serif'
          Font.Style = []
          MaxLength = 10
          ParentFont = False
          TabOrder = 0
          Text = '  /  /    '
          Visible = False
          OnChange = EdtDsLocal2Change
          OnKeyPress = EdtDsLocaliKeyPress
          BrDataValida = False
          BrDataVazia = True
          BrFunctionButton = TVdData
          BrAlignment = taLeftJustify
          BrRecordar = False
        end
        object EdtNrLocal2: TBrvEditNum
          Left = 159
          Top = 16
          Width = 97
          Height = 21
          Font.Charset = DEFAULT_CHARSET
          Font.Color = clWindowText
          Font.Height = -11
          Font.Name = 'MS Sans Serif'
          Font.Style = []
          ParentFont = False
          TabOrder = 1
          Text = '0,00'
          Visible = False
          OnChange = EdtDsLocal2Change
          OnKeyPress = EdtDsLocaliKeyPress
          BrAlignment = taRightJustify
          BrCasasDecimais = 2
          BrSepararMilhar = False
          BrAsInteger = 0
          BrAsFloatSQL = '0.00'
          BrVisibleButton = False
          BrFunctionButton = TpConsulta
          BrQueryCode = 0
          BrRecordar = False
        end
        object EdtDsLocal2: TEdit
          Left = 63
          Top = 30
          Width = 210
          Height = 21
          Font.Charset = DEFAULT_CHARSET
          Font.Color = clWindowText
          Font.Height = -11
          Font.Name = 'MS Sans Serif'
          Font.Style = []
          ParentFont = False
          TabOrder = 2
          OnChange = EdtDsLocal2Change
          OnKeyPress = EdtDsLocaliKeyPress
        end
        object CbxPesqu2: TComboBox
          Left = 63
          Top = 8
          Width = 210
          Height = 21
          Style = csDropDownList
          Font.Charset = DEFAULT_CHARSET
          Font.Color = clWindowText
          Font.Height = -11
          Font.Name = 'MS Sans Serif'
          Font.Style = []
          ParentFont = False
          TabOrder = 4
          OnChange = CbxPesqu2Change
        end
      end
    end
    object CbxTpWhere: TComboBox
      Left = 369
      Top = 11
      Width = 210
      Height = 21
      Style = csDropDownList
      TabOrder = 2
      OnChange = CbxTpWhereChange
      Items.Strings = (
        'Maior ou igual'
        'Menor ou igual'
        'Igual'
        'Maior'
        'Menor'
        'Qualquer parte'
        'Iniciando')
    end
  end
  object DbgConsul: TBrvDbGrid
    Left = 0
    Top = 130
    Width = 591
    Height = 258
    BrShowMemo = True
    BrReadOnlyStyle = [fsItalic]
    BrReadOnlyColor = clMaroon
    Align = alClient
    DataSource = DtsConsul
    TabOrder = 1
    TitleFont.Charset = DEFAULT_CHARSET
    TitleFont.Color = clWindowText
    TitleFont.Height = -11
    TitleFont.Name = 'MS Sans Serif'
    TitleFont.Style = []
    OnDblClick = DbgConsulDblClick
    OnKeyPress = DbgConsulKeyPress
    OnTitleClick = DbgConsulTitleClick
    BrDrawColumn.Strings = (
      'N'#227'o remova essas duas linhas de formata'#231#227'o:'
      'CampoTabela;Operador;ValorComparativo;Cor;')
    BrGradeZebrada = True
  end
  object DbNavCop: TBrvDbNavCop
    Left = 0
    Top = 0
    Width = 591
    Height = 33
    Align = alTop
    object btnFechar: TSpeedButton
      Left = 558
      Top = 4
      Width = 29
      Height = 26
      Hint = 'Sair'
      Flat = True
      Glyph.Data = {
        46010000424D460100000000000076000000280000001C0000000D0000000100
        040000000000D000000000000000000000001000000000000000000000000000
        80000080000000808000800000008000800080800000C0C0C000808080000000
        FF0000FF000000FFFF00FF000000FF00FF00FFFF0000FFFFFF00333333333333
        3333333333333333000033333333333333333FF33333FF330000333003333300
        3333833F333833F3000033300033300033338333F38333F30000333300030003
        3333383338333F330000333330000033333333833333F3330000333333000333
        33333338333F33330000333330000033333333833333F3330000333300030003
        3333383338333F33000033300033300033338333F38333F30000333003333300
        3333833F333833F3000033333333333333333883333388330000333333333333
        33333333333333330000}
      NumGlyphs = 2
      ParentShowHint = False
      ShowHint = True
      OnClick = btnFecharClick
    end
    object BtnColuna: TSpeedButton
      Left = 529
      Top = 4
      Width = 29
      Height = 26
      Hint = 'Visualizar colunas'
      Flat = True
      Glyph.Data = {
        F6060000424DF606000000000000360000002800000018000000180000000100
        180000000000C0060000C40E0000C40E00000000000000000000408080408080
        4080804080804080804080804080804080804080804080804080804080804080
        8040808040808040808040808040808040808040808040808040808040808040
        8080408080408080408080408080408080408080408080408080408080408080
        4080804080804080804080804080804080804080804080804080804080804080
        8040808040808040808040808040808040808040808040808040808000000000
        0000000000000000000000408080408080408080408080408080408080000000
        0000000000000000000000004080804080804080804080804080804080804080
        80408080000000FFFFFF00000000000000000040808040808040808040808040
        8080408080000000FFFFFF000000000000000000408080408080408080408080
        408080408080408080408080000000FFFFFF0000000000000000004080804080
        80408080408080408080408080000000FFFFFF00000000000000000040808040
        8080408080408080408080408080408080408080000000FFFFFF000000000000
        000000408080408080408080408080408080408080000000FFFFFF0000000000
        0000000040808040808040808040808040808040808040808040808000000000
        0000000000000000000000000000000000408080408080000000000000000000
        0000000000000000000000004080804080804080804080804080804080804080
        80408080000000000000FFFFFF00000000000000000000000000000000000000
        0000FFFFFF000000000000000000000000000000408080408080408080408080
        408080408080408080408080000000000000FFFFFF000000000000000000CED3
        D6000000000000000000FFFFFF00000000000000000000000000000040808040
        8080408080408080408080408080408080408080000000000000FFFFFF000000
        000000000000CED3D6000000000000000000FFFFFF0000000000000000000000
        0000000040808040808040808040808040808040808040808040808040808000
        0000000000000000000000000000000000000000000000000000000000000000
        0000000000000000004080804080804080804080804080804261634261634261
        63426163426163426163000000FFFFFF00000000000000000042616342616300
        0000FFFFFF000000000000000000408080408080408080408080408080408080
        426163CED3D6426163FFFFFFFFFFFFFFFFFF0000000000000000000000000000
        00FFFFFFFFFFFF00000000000000000000000000000040808040808040808040
        8080408080408080426163FFFFFF426163FFFFFFCED3D6CED3D6CED3D6000000
        000000000000CED3D6CED3D6CED3D6FFFFFF0000000000000000004080804080
        80408080408080408080408080408080426163CED3D6426163FFFFFFFFFFFFFF
        FFFFFFFFFF000000FFFFFF000000FFFFFFFFFFFFFFFFFFFFFFFF000000FFFFFF
        000000408080408080408080408080408080408080408080426163FFFFFF4261
        63FFFFFFCED3D6CED3D6CED3D6000000000000000000CED3D6CED3D6CED3D6FF
        FFFF000000000000000000408080408080408080408080408080408080408080
        426163CED3D6426163FFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFF
        FFFFFFFFFFFFFFFFFFFF42616340808040808040808040808040808040808040
        8080408080408080426163FFFFFF426163FFFFFFCED3D6CED3D6CED3D6CED3D6
        FFFFFFCED3D6CED3D6CED3D6CED3D6FFFFFF4261634080804080804080804080
        80408080408080408080408080408080426163CED3D6426163FFFFFFFFFFFFFF
        FFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFF426163408080
        4080804080804080804080804080804080804080804080804261634261634261
        6342616342616342616342616342616342616342616342616342616342616342
        6163426163408080408080408080408080408080408080408080408080408080
        426163CED3D6426163FFFFFFCED3D6CED3D6CED3D6CED3D6FFFFFFCED3D6CED3
        D6CED3D6CED3D6CED3D642616340808040808040808040808040808040808040
        8080408080408080426163426163426163426163426163426163426163426163
        4261634261634261634261634261634261634261634080804080804080804080
        8040808040808040808040808040808040808040808040808040808040808040
        8080408080408080408080408080408080408080408080408080408080408080
        4080804080804080804080804080804080804080804080804080804080804080
        8040808040808040808040808040808040808040808040808040808040808040
        8080408080408080408080408080408080408080408080408080}
      ParentShowHint = False
      ShowHint = True
      OnClick = BtnColunaClick
    end
    object CbxMaiMin: TCheckBox
      Left = 6
      Top = 7
      Width = 209
      Height = 17
      TabStop = False
      Caption = 'Ignorar mai'#250'sculas e min'#250'sculas'
      Checked = True
      Font.Charset = DEFAULT_CHARSET
      Font.Color = clWindowText
      Font.Height = -11
      Font.Name = 'MS Sans Serif'
      Font.Style = [fsBold]
      ParentFont = False
      State = cbChecked
      TabOrder = 0
      OnClick = CbxMaiMinClick
    end
  end
  object DtsConsul: TDataSource
    DataSet = CsConsul
    Left = 535
    Top = 344
  end
  object CsConsul: TBrvClientDataSet
    Aggregates = <>
    Params = <>
    ProviderName = 'DpConsul'
    ReadOnly = True
    BrShowFieldName = False
    BrQueryCode = 0
    BrType = VqNormal
    BrGravaLog = True
    BrUserCode = 0
    Left = 504
    Top = 344
  end
  object DsConsul: TBrvDataSet
    GetMetadata = False
    NumericMapping = True
    DbxCommandType = 'Dbx.SQL'
    MaxBlobSize = -1
    Params = <>
    BrShowFieldName = False
    BrQueryCode = 0
    BrType = VqNormal
    BrUserCode = 0
    Left = 336
    Top = 336
  end
end
