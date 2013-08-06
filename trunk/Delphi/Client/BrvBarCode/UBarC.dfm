object FrmBarCode: TFrmBarCode
  Left = 306
  Top = 183
  BorderIcons = [biSystemMenu, biMinimize]
  Caption = 'Impress'#227'o de C'#243'digo de Barras'
  ClientHeight = 97
  ClientWidth = 499
  Color = clBtnFace
  Font.Charset = DEFAULT_CHARSET
  Font.Color = clWindowText
  Font.Height = -11
  Font.Name = 'MS Sans Serif'
  Font.Style = []
  OldCreateOrder = False
  Scaled = False
  OnClose = FormClose
  OnCreate = FormCreate
  PixelsPerInch = 96
  TextHeight = 13
  object Panel1: TPanel
    Left = 0
    Top = 0
    Width = 499
    Height = 97
    Align = alClient
    BorderStyle = bsSingle
    Font.Charset = DEFAULT_CHARSET
    Font.Color = clWindowText
    Font.Height = -11
    Font.Name = 'MS Sans Serif'
    Font.Style = [fsBold]
    ParentFont = False
    TabOrder = 0
    ExplicitHeight = 301
    object Label1: TLabel
      Left = 5
      Top = 12
      Width = 44
      Height = 13
      Caption = 'Arquivo'
    end
    object SpeedButton1: TSpeedButton
      Left = 456
      Top = 8
      Width = 23
      Height = 22
      Glyph.Data = {
        E6040000424DE604000000000000360000002800000014000000140000000100
        180000000000B0040000C40E0000C40E00000000000000000000008080008080
        0080800080800080800080800080800080800080800080800080800080800080
        8000808000808000808000808000808000808000808000808000808000808000
        8080008080008080008080008080008080008080008080008080008080008080
        0080800080800080800080800080800080800080800080800080800080800080
        8000808000808000808000808000808000808000808000808000808000808000
        8080008080008080008080008080008080008080008080008080008080008080
        0080800080800080800080800080800080800080800080800080800080800080
        8000808000808000808000808000808000000000000000000000000000000000
        0000000000000000000000000000000000008080008080008080008080008080
        008080008080008080008080000000000000007F7F007F7F007F7F007F7F007F
        7F007F7F007F7F007F7F007F7F00000000808000808000808000808000808000
        808000808000808000000000FFFF000000007F7F007F7F007F7F007F7F007F7F
        007F7F007F7F007F7F007F7F0000000080800080800080800080800080800080
        80008080000000FFFFFF00FFFF000000007F7F007F7F007F7F007F7F007F7F00
        7F7F007F7F007F7F007F7F000000008080008080008080008080008080008080
        00000000FFFFFFFFFF00FFFF000000007F7F007F7F007F7F007F7F007F7F007F
        7F007F7F007F7F007F7F000000008080008080008080008080008080000000FF
        FFFF00FFFFFFFFFF00FFFF000000000000000000000000000000000000000000
        00000000000000000000000000808000808000808000808000000000FFFFFFFF
        FF00FFFFFFFFFF00FFFFFFFFFF00FFFFFFFFFF00FFFF00000000808000808000
        8080008080008080008080008080008080008080000000FFFFFF00FFFFFFFFFF
        00FFFFFFFFFF00FFFFFFFFFF00FFFFFFFFFF0000000080800080800080800080
        8000808000808000808000808000808000000000FFFFFFFFFF00FFFF00000000
        0000000000000000000000000000000000008080008080008080008080008080
        0080800080800080800080800080800000000000000000000080800080800080
        8000808000808000808000808000808000000000000000000000808000808000
        8080008080008080008080008080008080008080008080008080008080008080
        0080800080800080800080800080800000000000000080800080800080800080
        8000808000808000808000808000808000808000808000808000808000000000
        8080008080008080000000008080000000008080008080008080008080008080
        0080800080800080800080800080800080800080800080800080800000000000
        0000000000808000808000808000808000808000808000808000808000808000
        8080008080008080008080008080008080008080008080008080008080008080
        0080800080800080800080800080800080800080800080800080800080800080
        8000808000808000808000808000808000808000808000808000808000808000
        8080008080008080008080008080008080008080008080008080008080008080
        0080800080800080800080800080800080800080800080800080800080800080
        80008080008080008080}
      OnClick = SpeedButton1Click
    end
    object BtnImprim: TBitBtn
      Left = 406
      Top = 62
      Width = 83
      Height = 25
      Caption = 'Imprimir'
      DoubleBuffered = True
      Glyph.Data = {
        E6040000424DE604000000000000360000002800000014000000140000000100
        180000000000B0040000C40E0000C40E00000000000000000000408080408080
        4080804080804080804080804080804080804080804080804080804080804080
        8040808040808040808040808040808040808040808040808040808040808040
        8080408080408080408080408080408080408080408080408080408080408080
        4080804080804080804080804080804080804080804080804080804080804080
        8040808040808040808040808040808040808040808040808040808040808040
        8080408080408080408080408080408080408080408080408080000000000000
        0000000000000000000000000000000000000000000000000000004080804080
        80408080408080408080408080408080408080000000BFBFBFBFBFBFBFBFBFBF
        BFBFBFBFBFBFBFBFBFBFBFBFBFBFBFBFBF000000BFBFBF000000408080408080
        4080804080804080804080800000000000000000000000000000000000000000
        00000000000000000000000000000000000000BFBFBF00000040808040808040
        8080408080408080000000BFBFBFBFBFBFBFBFBFBFBFBFBFBFBFBFBFBF00FFFF
        00FFFF00FFFFBFBFBFBFBFBF0000000000000000004080804080804080804080
        80408080000000BFBFBFBFBFBFBFBFBFBFBFBFBFBFBFBFBFBF7F7F7F7F7F7F7F
        7F7FBFBFBFBFBFBF000000BFBFBF000000408080408080408080408080408080
        0000000000000000000000000000000000000000000000000000000000000000
        00000000000000BFBFBFBFBFBF000000408080408080408080408080000000BF
        BFBFBFBFBFBFBFBFBFBFBFBFBFBFBFBFBFBFBFBFBFBFBFBFBFBFBFBFBF000000
        BFBFBF000000BFBFBF0000004080804080804080804080804080800000000000
        00000000000000000000000000000000000000000000000000BFBFBF000000BF
        BFBF000000000000408080408080408080408080408080408080000000FFFFFF
        FFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFF000000BFBFBF000000BFBF
        BF000000408080408080408080408080408080408080408080000000FFFFFF00
        0000000000000000000000000000FFFFFF000000000000000000000000408080
        408080408080408080408080408080408080408080000000FFFFFFFFFFFFFFFF
        FFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFF00000040808040808040808040808040
        8080408080408080408080408080408080408080000000FFFFFF000000000000
        000000000000000000FFFFFF0000004080804080804080804080804080804080
        80408080408080408080408080408080000000FFFFFFFFFFFFFFFFFFFFFFFFFF
        FFFFFFFFFFFFFFFFFFFFFF000000408080408080408080408080408080408080
        4080804080804080804080804080800000000000000000000000000000000000
        0000000000000000000040808040808040808040808040808040808040808040
        8080408080408080408080408080408080408080408080408080408080408080
        4080804080804080804080804080804080804080804080804080804080804080
        8040808040808040808040808040808040808040808040808040808040808040
        8080408080408080408080408080408080408080408080408080408080408080
        4080804080804080804080804080804080804080804080804080804080804080
        80408080408080408080}
      ParentDoubleBuffered = False
      TabOrder = 1
      OnClick = BtnImprimClick
    end
    object EdtDsArquiv: TEdit
      Left = 56
      Top = 8
      Width = 401
      Height = 21
      TabOrder = 0
    end
    object QuickRep: TQuickRep
      Left = 0
      Top = 34
      Width = 378
      Height = 180
      Frame.Color = clBlack
      Frame.DrawTop = False
      Frame.DrawBottom = False
      Frame.DrawLeft = False
      Frame.DrawRight = False
      DataSet = TblOpeLog
      Font.Charset = DEFAULT_CHARSET
      Font.Color = clWindowText
      Font.Height = -13
      Font.Name = 'Arial'
      Font.Style = []
      Functions.Strings = (
        'PAGENUMBER'
        'COLUMNNUMBER'
        'REPORTTITLE')
      Functions.DATA = (
        '0'
        '0'
        #39#39)
      Options = [FirstPageHeader, LastPageFooter]
      Page.Columns = 1
      Page.Orientation = poPortrait
      Page.PaperSize = Custom
      Page.Continuous = False
      Page.Values = (
        0.000000000000000000
        475.000000000000000000
        0.000000000000000000
        1000.000000000000000000
        0.000000000000000000
        0.000000000000000000
        0.000000000000000000)
      PrinterSettings.Copies = 1
      PrinterSettings.OutputBin = Auto
      PrinterSettings.Duplex = False
      PrinterSettings.FirstPage = 0
      PrinterSettings.LastPage = 0
      PrinterSettings.UseStandardprinter = False
      PrinterSettings.UseCustomBinCode = False
      PrinterSettings.CustomBinCode = 0
      PrinterSettings.ExtendedDuplex = 0
      PrinterSettings.UseCustomPaperCode = False
      PrinterSettings.CustomPaperCode = 0
      PrinterSettings.PrintMetaFile = False
      PrinterSettings.PrintQuality = 0
      PrinterSettings.Collate = 0
      PrinterSettings.ColorOption = 0
      PrintIfEmpty = True
      SnapToGrid = True
      Units = MM
      Zoom = 100
      PrevFormStyle = fsStayOnTop
      PreviewInitialState = wsMaximized
      PrevInitialZoom = qrZoomToFit
      PreviewDefaultSaveType = stQRP
      PreviewLeft = 0
      PreviewTop = 0
      object QRBand1: TQRBand
        Left = 0
        Top = 0
        Width = 378
        Height = 180
        Frame.Color = clBlack
        Frame.DrawTop = False
        Frame.DrawBottom = False
        Frame.DrawLeft = False
        Frame.DrawRight = False
        AlignToBottom = False
        BeforePrint = QRBand1BeforePrint
        Color = clWhite
        TransparentBand = False
        ForceNewColumn = False
        ForceNewPage = True
        Size.Values = (
          476.250000000000000000
          1000.125000000000000000)
        PreCaluculateBandHeight = False
        KeepOnOnePage = False
        BandType = rbDetail
        object QRDBText1: TQRDBText
          Left = 304
          Top = 4
          Width = 66
          Height = 19
          Frame.Color = clBlack
          Frame.DrawTop = False
          Frame.DrawBottom = False
          Frame.DrawLeft = False
          Frame.DrawRight = False
          Size.Values = (
            50.270833333333330000
            804.333333333333300000
            10.583333333333330000
            174.625000000000000000)
          XLColumn = 0
          Alignment = taRightJustify
          AlignToBand = False
          AutoSize = False
          AutoStretch = False
          Color = clWhite
          DataSet = TblOpeLog
          DataField = 'NrOrdem'
          Font.Charset = ANSI_CHARSET
          Font.Color = clWindowText
          Font.Height = -15
          Font.Name = 'Arial'
          Font.Style = [fsBold]
          ParentFont = False
          Transparent = False
          WordWrap = True
          ExportAs = exptText
          WrapStyle = BreakOnSpaces
          FullJustify = False
          MaxBreakChars = 0
          FontSize = 11
        end
        object QRDBText2: TQRDBText
          Left = 11
          Top = 4
          Width = 46
          Height = 19
          Frame.Color = clBlack
          Frame.DrawTop = False
          Frame.DrawBottom = False
          Frame.DrawLeft = False
          Frame.DrawRight = False
          Size.Values = (
            50.270833333333330000
            29.104166666666670000
            10.583333333333330000
            121.708333333333300000)
          XLColumn = 0
          Alignment = taLeftJustify
          AlignToBand = False
          AutoSize = False
          AutoStretch = False
          Color = clWhite
          DataSet = TblOpeLog
          DataField = 'CdProdut'
          Font.Charset = ANSI_CHARSET
          Font.Color = clWindowText
          Font.Height = -15
          Font.Name = 'Arial'
          Font.Style = [fsBold]
          ParentFont = False
          Transparent = False
          WordWrap = True
          ExportAs = exptText
          WrapStyle = BreakOnSpaces
          FullJustify = False
          MaxBreakChars = 0
          FontSize = 11
        end
        object QRDBText5: TQRDBText
          Left = 113
          Top = 105
          Width = 261
          Height = 75
          Frame.Color = clBlack
          Frame.DrawTop = False
          Frame.DrawBottom = False
          Frame.DrawLeft = False
          Frame.DrawRight = False
          Size.Values = (
            198.437500000000000000
            298.979166666666700000
            277.812500000000000000
            690.562500000000000000)
          XLColumn = 0
          Alignment = taLeftJustify
          AlignToBand = False
          AutoSize = False
          AutoStretch = False
          Color = clWhite
          DataSet = TblOpeLog
          DataField = 'DsEndere'
          Font.Charset = ANSI_CHARSET
          Font.Color = clWindowText
          Font.Height = -48
          Font.Name = 'Arial'
          Font.Style = [fsBold]
          ParentFont = False
          Transparent = False
          WordWrap = True
          ExportAs = exptText
          WrapStyle = BreakOnSpaces
          FullJustify = False
          MaxBreakChars = 0
          FontSize = 36
        end
        object QRDBImage1: TQRDBImage
          Left = 11
          Top = 81
          Width = 93
          Height = 93
          Frame.Color = clBlack
          Frame.DrawTop = False
          Frame.DrawBottom = False
          Frame.DrawLeft = False
          Frame.DrawRight = False
          Size.Values = (
            246.062500000000000000
            29.104166666666670000
            214.312500000000000000
            246.062500000000000000)
          XLColumn = 0
          DataField = 'ImBarCod'
          DataSet = TblOpeLog
        end
        object QRLabel1: TQRLabel
          Left = 278
          Top = 5
          Width = 26
          Height = 17
          Frame.Color = clBlack
          Frame.DrawTop = False
          Frame.DrawBottom = False
          Frame.DrawLeft = False
          Frame.DrawRight = False
          Size.Values = (
            44.979166666666670000
            735.541666666666700000
            13.229166666666670000
            68.791666666666670000)
          XLColumn = 0
          Alignment = taLeftJustify
          AlignToBand = False
          AutoSize = True
          AutoStretch = False
          Caption = 'Ord:'
          Color = clWhite
          Font.Charset = DEFAULT_CHARSET
          Font.Color = clWindowText
          Font.Height = -13
          Font.Name = 'Arial'
          Font.Style = []
          ParentFont = False
          Transparent = False
          WordWrap = True
          ExportAs = exptText
          WrapStyle = BreakOnSpaces
          FontSize = 10
        end
        object QRDBText7: TQRDBText
          Left = 57
          Top = 4
          Width = 221
          Height = 19
          Frame.Color = clBlack
          Frame.DrawTop = False
          Frame.DrawBottom = False
          Frame.DrawLeft = False
          Frame.DrawRight = False
          Size.Values = (
            50.270833333333330000
            150.812500000000000000
            10.583333333333330000
            584.729166666666700000)
          XLColumn = 0
          Alignment = taLeftJustify
          AlignToBand = False
          AutoSize = False
          AutoStretch = False
          Color = clWhite
          DataSet = TblOpeLog
          DataField = 'DsProdut'
          Font.Charset = ANSI_CHARSET
          Font.Color = clWindowText
          Font.Height = -15
          Font.Name = 'Arial'
          Font.Style = [fsBold]
          ParentFont = False
          Transparent = False
          WordWrap = True
          ExportAs = exptText
          WrapStyle = BreakOnSpaces
          FullJustify = False
          MaxBreakChars = 0
          FontSize = 11
        end
        object QRLabel2: TQRLabel
          Left = 11
          Top = 24
          Width = 28
          Height = 17
          Frame.Color = clBlack
          Frame.DrawTop = False
          Frame.DrawBottom = False
          Frame.DrawLeft = False
          Frame.DrawRight = False
          Size.Values = (
            44.979166666666670000
            29.104166666666670000
            63.500000000000000000
            74.083333333333330000)
          XLColumn = 0
          Alignment = taLeftJustify
          AlignToBand = False
          AutoSize = True
          AutoStretch = False
          Caption = 'Mov:'
          Color = clWhite
          Font.Charset = DEFAULT_CHARSET
          Font.Color = clWindowText
          Font.Height = -13
          Font.Name = 'Arial'
          Font.Style = []
          ParentFont = False
          Transparent = False
          WordWrap = True
          ExportAs = exptText
          WrapStyle = BreakOnSpaces
          FontSize = 10
        end
        object QRDBText8: TQRDBText
          Left = 42
          Top = 23
          Width = 128
          Height = 19
          Frame.Color = clBlack
          Frame.DrawTop = False
          Frame.DrawBottom = False
          Frame.DrawLeft = False
          Frame.DrawRight = False
          Size.Values = (
            50.270833333333330000
            111.125000000000000000
            60.854166666666670000
            338.666666666666700000)
          XLColumn = 0
          Alignment = taLeftJustify
          AlignToBand = False
          AutoSize = False
          AutoStretch = False
          Color = clWhite
          DataSet = TblOpeLog
          DataField = 'DtOcorre'
          Font.Charset = ANSI_CHARSET
          Font.Color = clWindowText
          Font.Height = -15
          Font.Name = 'Arial'
          Font.Style = [fsBold]
          ParentFont = False
          Transparent = False
          WordWrap = True
          ExportAs = exptText
          WrapStyle = BreakOnSpaces
          FullJustify = False
          MaxBreakChars = 0
          FontSize = 11
        end
        object QRLabel3: TQRLabel
          Left = 278
          Top = 24
          Width = 26
          Height = 17
          Frame.Color = clBlack
          Frame.DrawTop = False
          Frame.DrawBottom = False
          Frame.DrawLeft = False
          Frame.DrawRight = False
          Size.Values = (
            44.979166666666670000
            735.541666666666700000
            63.500000000000000000
            68.791666666666670000)
          XLColumn = 0
          Alignment = taLeftJustify
          AlignToBand = False
          AutoSize = True
          AutoStretch = False
          Caption = 'Qtd:'
          Color = clWhite
          Font.Charset = DEFAULT_CHARSET
          Font.Color = clWindowText
          Font.Height = -13
          Font.Name = 'Arial'
          Font.Style = []
          ParentFont = False
          Transparent = False
          WordWrap = True
          ExportAs = exptText
          WrapStyle = BreakOnSpaces
          FontSize = 10
        end
        object QrDbtQtProdut: TQRDBText
          Left = 304
          Top = 24
          Width = 49
          Height = 19
          Frame.Color = clBlack
          Frame.DrawTop = False
          Frame.DrawBottom = False
          Frame.DrawLeft = False
          Frame.DrawRight = False
          Size.Values = (
            50.270833333333330000
            804.333333333333300000
            63.500000000000000000
            129.645833333333300000)
          XLColumn = 0
          Alignment = taRightJustify
          AlignToBand = False
          AutoSize = False
          AutoStretch = False
          Color = clWhite
          DataSet = TblOpeLog
          DataField = 'QtProdut'
          Font.Charset = ANSI_CHARSET
          Font.Color = clWindowText
          Font.Height = -15
          Font.Name = 'Arial'
          Font.Style = [fsBold]
          ParentFont = False
          Transparent = False
          WordWrap = True
          ExportAs = exptText
          WrapStyle = BreakOnSpaces
          FullJustify = False
          MaxBreakChars = 0
          FontSize = 11
        end
        object QrDbtCdUnidad: TQRDBText
          Left = 354
          Top = 24
          Width = 21
          Height = 18
          Frame.Color = clBlack
          Frame.DrawTop = False
          Frame.DrawBottom = False
          Frame.DrawLeft = False
          Frame.DrawRight = False
          Size.Values = (
            47.625000000000000000
            936.625000000000000000
            63.500000000000000000
            55.562500000000000000)
          XLColumn = 0
          Alignment = taLeftJustify
          AlignToBand = False
          AutoSize = False
          AutoStretch = False
          Color = clWhite
          DataSet = TblOpeLog
          DataField = 'DsUnidad'
          Font.Charset = ANSI_CHARSET
          Font.Color = clWindowText
          Font.Height = -12
          Font.Name = 'Arial Black'
          Font.Style = [fsBold]
          ParentFont = False
          Transparent = False
          WordWrap = True
          ExportAs = exptText
          WrapStyle = BreakOnSpaces
          FullJustify = False
          MaxBreakChars = 0
          FontSize = 9
        end
        object QRLabel4: TQRLabel
          Left = 11
          Top = 44
          Width = 30
          Height = 17
          Frame.Color = clBlack
          Frame.DrawTop = False
          Frame.DrawBottom = False
          Frame.DrawLeft = False
          Frame.DrawRight = False
          Size.Values = (
            44.979166666666670000
            29.104166666666670000
            116.416666666666700000
            79.375000000000000000)
          XLColumn = 0
          Alignment = taLeftJustify
          AlignToBand = False
          AutoSize = True
          AutoStretch = False
          Caption = 'Lote:'
          Color = clWhite
          Font.Charset = DEFAULT_CHARSET
          Font.Color = clWindowText
          Font.Height = -13
          Font.Name = 'Arial'
          Font.Style = []
          ParentFont = False
          Transparent = False
          WordWrap = True
          ExportAs = exptText
          WrapStyle = BreakOnSpaces
          FontSize = 10
        end
        object QRDBText3: TQRDBText
          Left = 42
          Top = 43
          Width = 128
          Height = 19
          Frame.Color = clBlack
          Frame.DrawTop = False
          Frame.DrawBottom = False
          Frame.DrawLeft = False
          Frame.DrawRight = False
          Size.Values = (
            50.270833333333330000
            111.125000000000000000
            113.770833333333300000
            338.666666666666700000)
          XLColumn = 0
          Alignment = taLeftJustify
          AlignToBand = False
          AutoSize = False
          AutoStretch = False
          Color = clWhite
          DataSet = TblOpeLog
          DataField = 'DsLote'
          Font.Charset = ANSI_CHARSET
          Font.Color = clWindowText
          Font.Height = -15
          Font.Name = 'Arial'
          Font.Style = [fsBold]
          ParentFont = False
          Transparent = False
          WordWrap = True
          ExportAs = exptText
          WrapStyle = BreakOnSpaces
          FullJustify = False
          MaxBreakChars = 0
          FontSize = 11
        end
        object QRLabel5: TQRLabel
          Left = 172
          Top = 44
          Width = 24
          Height = 17
          Frame.Color = clBlack
          Frame.DrawTop = False
          Frame.DrawBottom = False
          Frame.DrawLeft = False
          Frame.DrawRight = False
          Size.Values = (
            44.979166666666670000
            455.083333333333300000
            116.416666666666700000
            63.500000000000000000)
          XLColumn = 0
          Alignment = taCenter
          AlignToBand = False
          AutoSize = True
          AutoStretch = False
          Caption = 'Val:'
          Color = clWhite
          Font.Charset = DEFAULT_CHARSET
          Font.Color = clWindowText
          Font.Height = -13
          Font.Name = 'Arial'
          Font.Style = []
          ParentFont = False
          Transparent = False
          WordWrap = True
          ExportAs = exptText
          WrapStyle = BreakOnSpaces
          FontSize = 10
        end
        object QRDBText4: TQRDBText
          Left = 197
          Top = 43
          Width = 82
          Height = 19
          Frame.Color = clBlack
          Frame.DrawTop = False
          Frame.DrawBottom = False
          Frame.DrawLeft = False
          Frame.DrawRight = False
          Size.Values = (
            50.270833333333330000
            521.229166666666700000
            113.770833333333300000
            216.958333333333300000)
          XLColumn = 0
          Alignment = taLeftJustify
          AlignToBand = False
          AutoSize = False
          AutoStretch = False
          Color = clWhite
          DataSet = TblOpeLog
          DataField = 'DtValida'
          Font.Charset = ANSI_CHARSET
          Font.Color = clWindowText
          Font.Height = -15
          Font.Name = 'Arial'
          Font.Style = [fsBold]
          ParentFont = False
          Transparent = False
          WordWrap = True
          ExportAs = exptText
          WrapStyle = BreakOnSpaces
          FullJustify = False
          MaxBreakChars = 0
          FontSize = 11
        end
        object QRLabel6: TQRLabel
          Left = 279
          Top = 44
          Width = 22
          Height = 17
          Frame.Color = clBlack
          Frame.DrawTop = False
          Frame.DrawBottom = False
          Frame.DrawLeft = False
          Frame.DrawRight = False
          Size.Values = (
            44.979166666666670000
            738.187500000000000000
            116.416666666666700000
            58.208333333333330000)
          XLColumn = 0
          Alignment = taCenter
          AlignToBand = False
          AutoSize = True
          AutoStretch = False
          Caption = 'NF:'
          Color = clWhite
          Font.Charset = DEFAULT_CHARSET
          Font.Color = clWindowText
          Font.Height = -13
          Font.Name = 'Arial'
          Font.Style = []
          ParentFont = False
          Transparent = False
          WordWrap = True
          ExportAs = exptText
          WrapStyle = BreakOnSpaces
          FontSize = 10
        end
        object QRDBText6: TQRDBText
          Left = 310
          Top = 43
          Width = 64
          Height = 19
          Frame.Color = clBlack
          Frame.DrawTop = False
          Frame.DrawBottom = False
          Frame.DrawLeft = False
          Frame.DrawRight = False
          Size.Values = (
            50.270833333333330000
            820.208333333333300000
            113.770833333333300000
            169.333333333333300000)
          XLColumn = 0
          Alignment = taRightJustify
          AlignToBand = False
          AutoSize = False
          AutoStretch = False
          Color = clWhite
          DataSet = TblOpeLog
          DataField = 'NrNota'
          Font.Charset = ANSI_CHARSET
          Font.Color = clWindowText
          Font.Height = -15
          Font.Name = 'Arial'
          Font.Style = [fsBold]
          ParentFont = False
          Transparent = False
          WordWrap = True
          ExportAs = exptText
          WrapStyle = BreakOnSpaces
          FullJustify = False
          MaxBreakChars = 0
          FontSize = 11
        end
        object QRDBText11: TQRDBText
          Left = 305
          Top = 62
          Width = 68
          Height = 43
          Frame.Color = clBlack
          Frame.DrawTop = False
          Frame.DrawBottom = False
          Frame.DrawLeft = False
          Frame.DrawRight = False
          Size.Values = (
            113.770833333333300000
            806.979166666666700000
            164.041666666666700000
            179.916666666666700000)
          XLColumn = 0
          Alignment = taCenter
          AlignToBand = False
          AutoSize = False
          AutoStretch = False
          Color = clBlack
          DataSet = TblOpeLog
          DataField = 'DsSigCid'
          Font.Charset = ANSI_CHARSET
          Font.Color = clWhite
          Font.Height = -35
          Font.Name = 'Courier New'
          Font.Style = [fsBold]
          ParentFont = False
          Transparent = False
          WordWrap = True
          ExportAs = exptText
          WrapStyle = BreakOnSpaces
          FullJustify = False
          MaxBreakChars = 0
          FontSize = 26
        end
        object QRDBText12: TQRDBText
          Left = 11
          Top = 63
          Width = 288
          Height = 19
          Frame.Color = clBlack
          Frame.DrawTop = False
          Frame.DrawBottom = False
          Frame.DrawLeft = False
          Frame.DrawRight = False
          Size.Values = (
            50.270833333333330000
            29.104166666666670000
            166.687500000000000000
            762.000000000000000000)
          XLColumn = 0
          Alignment = taLeftJustify
          AlignToBand = False
          AutoSize = False
          AutoStretch = False
          Color = clWhite
          DataSet = TblOpeLog
          DataField = 'RsClient'
          Font.Charset = ANSI_CHARSET
          Font.Color = clWindowText
          Font.Height = -15
          Font.Name = 'Arial'
          Font.Style = [fsBold]
          ParentFont = False
          Transparent = False
          WordWrap = True
          ExportAs = exptText
          WrapStyle = BreakOnSpaces
          FullJustify = False
          MaxBreakChars = 0
          FontSize = 11
        end
        object QRDBText13: TQRDBText
          Left = 197
          Top = 23
          Width = 81
          Height = 19
          Frame.Color = clBlack
          Frame.DrawTop = False
          Frame.DrawBottom = False
          Frame.DrawLeft = False
          Frame.DrawRight = False
          Size.Values = (
            50.270833333333330000
            521.229166666666700000
            60.854166666666670000
            214.312500000000000000)
          XLColumn = 0
          Alignment = taLeftJustify
          AlignToBand = False
          AutoSize = False
          AutoStretch = False
          Color = clWhite
          DataSet = TblOpeLog
          DataField = 'DsRefCli'
          Font.Charset = ANSI_CHARSET
          Font.Color = clWindowText
          Font.Height = -15
          Font.Name = 'Arial'
          Font.Style = [fsBold]
          ParentFont = False
          Transparent = False
          WordWrap = True
          ExportAs = exptText
          WrapStyle = BreakOnSpaces
          FullJustify = False
          MaxBreakChars = 0
          FontSize = 11
        end
        object QRLabel7: TQRLabel
          Left = 172
          Top = 24
          Width = 24
          Height = 17
          Frame.Color = clBlack
          Frame.DrawTop = False
          Frame.DrawBottom = False
          Frame.DrawLeft = False
          Frame.DrawRight = False
          Size.Values = (
            44.979166666666670000
            455.083333333333300000
            63.500000000000000000
            63.500000000000000000)
          XLColumn = 0
          Alignment = taCenter
          AlignToBand = False
          AutoSize = True
          AutoStretch = False
          Caption = 'Ref:'
          Color = clWhite
          Font.Charset = DEFAULT_CHARSET
          Font.Color = clWindowText
          Font.Height = -13
          Font.Name = 'Arial'
          Font.Style = []
          ParentFont = False
          Transparent = False
          WordWrap = True
          ExportAs = exptText
          WrapStyle = BreakOnSpaces
          FontSize = 10
        end
        object QRDBText14: TQRDBText
          Left = 173
          Top = 88
          Width = 126
          Height = 19
          Frame.Color = clBlack
          Frame.DrawTop = False
          Frame.DrawBottom = False
          Frame.DrawLeft = False
          Frame.DrawRight = False
          Size.Values = (
            50.270833333333330000
            457.729166666666700000
            232.833333333333300000
            333.375000000000000000)
          XLColumn = 0
          Alignment = taLeftJustify
          AlignToBand = False
          AutoSize = False
          AutoStretch = False
          Color = clWhite
          DataSet = TblOpeLog
          DataField = 'DsEndOri'
          Font.Charset = ANSI_CHARSET
          Font.Color = clWindowText
          Font.Height = -15
          Font.Name = 'Arial'
          Font.Style = [fsBold]
          ParentFont = False
          Transparent = False
          WordWrap = True
          ExportAs = exptText
          WrapStyle = BreakOnSpaces
          FullJustify = False
          MaxBreakChars = 0
          FontSize = 11
        end
        object QRLabel8: TQRLabel
          Left = 118
          Top = 88
          Width = 47
          Height = 17
          Frame.Color = clBlack
          Frame.DrawTop = False
          Frame.DrawBottom = False
          Frame.DrawLeft = False
          Frame.DrawRight = False
          Size.Values = (
            44.979166666666670000
            312.208333333333300000
            232.833333333333300000
            124.354166666666700000)
          XLColumn = 0
          Alignment = taLeftJustify
          AlignToBand = False
          AutoSize = True
          AutoStretch = False
          Caption = 'Origem:'
          Color = clWhite
          Font.Charset = DEFAULT_CHARSET
          Font.Color = clWindowText
          Font.Height = -13
          Font.Name = 'Arial'
          Font.Style = []
          ParentFont = False
          Transparent = False
          WordWrap = True
          ExportAs = exptText
          WrapStyle = BreakOnSpaces
          FontSize = 10
        end
      end
    end
  end
  object DlgArquiv: TOpenDialog
    Filter = 'Text File|*.txt|DAT|*.dat|All Files|*.*'
    FilterIndex = 3
    Left = 423
    Top = 35
  end
  object TblOpeLog: TClientDataSet
    Aggregates = <>
    Params = <>
    Left = 394
    Top = 36
    object TblOpeLogCdEmpres: TIntegerField
      FieldName = 'CdEmpres'
    end
    object TblOpeLogCdProdut: TIntegerField
      FieldName = 'CdProdut'
    end
    object TblOpeLogDsProdut: TStringField
      FieldName = 'DsProdut'
      Size = 50
    end
    object TblOpeLogNrOrdem: TIntegerField
      FieldName = 'NrOrdem'
    end
    object TblOpeLogDtOcorre: TDateTimeField
      FieldName = 'DtOcorre'
      DisplayFormat = 'dd/mm/yyyy hh:mm'
    end
    object TblOpeLogDsRefCli: TStringField
      FieldName = 'DsRefCli'
    end
    object TblOpeLogQtProdut: TFloatField
      FieldName = 'QtProdut'
    end
    object TblOpeLogDsUnidad: TStringField
      FieldName = 'DsUnidad'
      Size = 3
    end
    object TblOpeLogDsLote: TStringField
      FieldName = 'DsLote'
    end
    object TblOpeLogDtValida: TDateField
      FieldName = 'DtValida'
    end
    object TblOpeLogNrNota: TIntegerField
      FieldName = 'NrNota'
    end
    object TblOpeLogRsClient: TStringField
      FieldName = 'RsClient'
      Size = 60
    end
    object TblOpeLogDsEndere: TStringField
      FieldName = 'DsEndere'
    end
    object TblOpeLogCdEndere: TIntegerField
      FieldName = 'CdEndere'
    end
    object TblOpeLogDsEndOri: TStringField
      FieldName = 'DsEndOri'
    end
    object TblOpeLogImBarCod: TBlobField
      FieldName = 'ImBarCod'
    end
    object TblOpeLogDsSigCid: TStringField
      FieldName = 'DsSigCid'
      Size = 3
    end
    object TblOpeLogCdClient: TIntegerField
      FieldName = 'CdClient'
    end
  end
  object BrvString: TBrvString
    Left = 456
    Top = 32
  end
end
