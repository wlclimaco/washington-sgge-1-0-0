object FrmCadTitPa: TFrmCadTitPa
  Left = 99
  Top = 166
  Width = 865
  Height = 410
  Caption = 'FrmCadTitPa'
  Color = clBtnFace
  Font.Charset = DEFAULT_CHARSET
  Font.Color = clWindowText
  Font.Height = -11
  Font.Name = 'MS Sans Serif'
  Font.Style = []
  OldCreateOrder = False
  PixelsPerInch = 96
  TextHeight = 13
  object Panel1: TPanel
    Left = 0
    Top = 0
    Width = 857
    Height = 376
    Align = alClient
    TabOrder = 0
    object GroupBox1: TGroupBox
      Left = 6
      Top = -3
      Width = 843
      Height = 324
      TabOrder = 0
      object SpeedButton1: TSpeedButton
        Left = 736
        Top = 287
        Width = 91
        Height = 27
        Caption = '&GRAVAR'
        OnClick = SpeedButton1Click
      end
      object TXTEMPRESA: TActNumberEdit
        Left = 94
        Top = 19
        Width = 121
        Height = 21
        Alignment = taLeftJustify
        ColorOnFocus = 16311512
        ColorOnNotFocus = clWindow
        NotNull = True
        EditLabel.Width = 78
        EditLabel.Height = 13
        EditLabel.Caption = 'COD EMPRESA'
        TabOrder = 0
        Value = 0
      end
      object TXTFILIAL: TActNumberEdit
        Left = 94
        Top = 45
        Width = 121
        Height = 21
        Alignment = taLeftJustify
        ColorOnFocus = 16311512
        ColorOnNotFocus = clWindow
        NotNull = True
        EditLabel.Width = 57
        EditLabel.Height = 13
        EditLabel.Caption = 'COD FILIAL'
        TabOrder = 1
        Value = 0
      end
      object TXTDATPARC: TActDateEdit
        Left = 94
        Top = 98
        Width = 121
        Height = 21
        ColorOnFocus = 16311512
        ColorOnNotFocus = clWindow
        NotNull = True
        EditLabel.Width = 67
        EditLabel.Height = 13
        EditLabel.Caption = 'DATA 1'#170' PAR'
        TabOrder = 3
        Text = 'TXTDATPARC'
        DateText = '00/00/0000'
        DateValue = -693594.000000000000000000
        Glyph.Data = {
          56060000424D560600000000000036000000280000001C0000000E0000000100
          2000000000002006000000000000000000000000000000000000C0C0C000C0C0
          C000C0C0C000C0C0C000C0C0C000C0C0C000C0C0C000C0C0C000C0C0C000C0C0
          C000C0C0C000C0C0C000C0C0C000C0C0C000C0C0C000C0C0C000C0C0C000C0C0
          C000C0C0C000C0C0C000C0C0C000C0C0C000C0C0C000C0C0C000C0C0C000C0C0
          C000C0C0C000C0C0C000C0C0C000C0C0C000C0C0C000C0C0C000C0C0C000C0C0
          C000C0C0C000C0C0C000C0C0C000C0C0C000C0C0C000C0C0C000C0C0C000C0C0
          C000C0C0C000C0C0C000C0C0C000C0C0C000C0C0C000C0C0C000C0C0C000C0C0
          C000C0C0C000C0C0C000C0C0C000C0C0C000C0C0C000C0C0C000C0C0C0000080
          8000008080000080800000808000008080000080800000808000008080000080
          80000080800000808000C0C0C000C0C0C000C0C0C00080808000808080008080
          8000808080008080800080808000808080008080800080808000808080008080
          8000C0C0C000C0C0C000C0C0C00080808000FFFFFF000000000000000000FFFF
          FF000000000000000000FFFFFF00000000000000000000808000C0C0C000C0C0
          C000C0C0C00080808000FFFFFF008080800080808000FFFFFF00808080008080
          8000FFFFFF00808080008080800080808000C0C0C000C0C0C000C0C0C0008080
          8000FFFFFF00C0C0C000C0C0C000FFFFFF00C0C0C000C0C0C000FFFFFF00C0C0
          C000C0C0C00000808000C0C0C000C0C0C000C0C0C00080808000FFFFFF00C0C0
          C000C0C0C000FFFFFF00C0C0C000C0C0C000FFFFFF00C0C0C000C0C0C0008080
          8000C0C0C000C0C0C000C0C0C00080808000FFFFFF000000000000000000FFFF
          FF000000FF000000FF00FFFFFF000000FF000000FF0000808000C0C0C000C0C0
          C000C0C0C00080808000FFFFFF008080800080808000FFFFFF00808080008080
          8000FFFFFF00808080008080800080808000C0C0C000C0C0C000C0C0C0008080
          8000FFFFFF00C0C0C000C0C0C000FFFFFF00C0C0C000C0C0C000FFFFFF00C0C0
          C000C0C0C00000808000C0C0C000C0C0C000C0C0C00080808000FFFFFF00C0C0
          C000C0C0C000FFFFFF00C0C0C000C0C0C000FFFFFF00C0C0C000C0C0C0008080
          8000C0C0C000C0C0C000C0C0C00080808000FFFFFF000000000000000000FFFF
          FF000000000000000000FFFFFF00000000000000000000808000C0C0C000C0C0
          C000C0C0C00080808000FFFFFF008080800080808000FFFFFF00808080008080
          8000FFFFFF00808080008080800080808000C0C0C000C0C0C000C0C0C0008080
          8000FFFFFF00C0C0C000C0C0C000FFFFFF00C0C0C000C0C0C000FFFFFF00C0C0
          C000C0C0C00000808000C0C0C000C0C0C000C0C0C00080808000FFFFFF00C0C0
          C000C0C0C000FFFFFF00C0C0C000C0C0C000FFFFFF00C0C0C000C0C0C0008080
          8000C0C0C000C0C0C000C0C0C00080808000FF000000FF000000FF000000FF00
          0000FF000000FF000000FF000000FF000000FF00000000808000C0C0C000C0C0
          C000C0C0C00080808000C0C0C000C0C0C000C0C0C000C0C0C000C0C0C000C0C0
          C000C0C0C000C0C0C000C0C0C00080808000C0C0C000C0C0C000C0C0C0008080
          8000FF000000FFFFFF00FFFFFF00FFFFFF00FFFFFF00FFFFFF00FFFFFF00FFFF
          FF00FF00000000808000C0C0C000C0C0C000C0C0C00080808000C0C0C000FFFF
          FF00FFFFFF00FFFFFF00FFFFFF00FFFFFF00FFFFFF00FFFFFF00C0C0C0008080
          8000C0C0C000C0C0C000C0C0C00080808000FF000000FF000000FF000000FF00
          0000FF000000FF000000FF000000FF000000FF00000000808000C0C0C000C0C0
          C000C0C0C00080808000C0C0C000C0C0C000C0C0C000C0C0C000C0C0C000C0C0
          C000C0C0C000C0C0C000C0C0C00080808000C0C0C000C0C0C000C0C0C0008080
          8000808080008080800080808000808080008080800080808000808080008080
          80008080800000808000C0C0C000C0C0C000C0C0C00080808000808080008080
          8000808080008080800080808000808080008080800080808000808080008080
          8000C0C0C000C0C0C000C0C0C000C0C0C000C0C0C000C0C0C000C0C0C000C0C0
          C000C0C0C000C0C0C000C0C0C000C0C0C000C0C0C000C0C0C000C0C0C000C0C0
          C000C0C0C000C0C0C000C0C0C000C0C0C000C0C0C000C0C0C000C0C0C000C0C0
          C000C0C0C000C0C0C000C0C0C000C0C0C000C0C0C000C0C0C000}
      end
      object TXTVALOR: TActCurrencyEdit
        Left = 395
        Top = 18
        Width = 121
        Height = 21
        ColorOnFocus = 16311512
        ColorOnNotFocus = clWindow
        EditLabel.Width = 74
        EditLabel.Height = 13
        EditLabel.Caption = 'VALOR TOTAL'
        TabOrder = 4
        Glyph.Data = {
          7E050000424D7E0500000000000036000000280000001A0000000D0000000100
          2000000000004805000000000000000000000000000000000000FFC0C000FFC0
          C000FFC0C000FFC0C000FFC0C000FFC0C000FFC0C000FFC0C000FFC0C000FFC0
          C000FFC0C000FFC0C000FFC0C000FFC0C000FFC0C000FFC0C000FFC0C000FFC0
          C000FFC0C000FFC0C000FFC0C000FFC0C000FFC0C000FFC0C000FFC0C000FFC0
          C000FFC0C0008040400080404000804040008040400080404000804040008040
          4000804040008040400080404000FFC0C000FFC0C000FFC0C000FFFFFF008080
          8000808080008080800080808000808080008080800080808000808080008080
          8000FFC0C000FFC0C000FFC0C000FFC00000FF406000FF406000FF406000FF40
          6000FF406000FF406000FF406000FF40600080404000FFC0C000FFC0C000FFC0
          C000FFFFFF00FFC0C000FFC0C000FFC0C000FFC0C000FFC0C000FFC0C000FFC0
          C000FFC0C00080808000FFC0C000FFC0C000FFC0C000FFC00000FF406000FFC0
          0000FFFFFF00FFC00000FFFFFF00FFC00000FFFFFF00FF40600080404000FFC0
          C000FFC0C000FFC0C000FFFFFF00FFC0C00080808000FFFFFF0080808000FFFF
          FF0080808000FFFFFF00FFC0C00080808000FFC0C000FFC0C000FFC0C000FFC0
          0000FF406000FF406000FF406000FF406000FF406000FF406000FF406000FF40
          600080404000FFC0C000FFC0C000FFC0C000FFFFFF00FFC0C000FFC0C000FFC0
          C000FFC0C000FFC0C000FFC0C000FFC0C000FFC0C00080808000FFC0C000FFC0
          C000FFC0C000FFC00000FF406000FFC00000FFFFFF00FFC00000FFFFFF00FFC0
          0000FFFFFF00FF40600080404000FFC0C000FFC0C000FFC0C000FFFFFF00FFC0
          C00080808000FFFFFF0080808000FFFFFF0080808000FFFFFF00FFC0C0008080
          8000FFC0C000FFC0C000FFC0C000FFC00000FF406000FF406000FF406000FF40
          6000FF406000FF406000FF406000FF40600080404000FFC0C000FFC0C000FFC0
          C000FFFFFF00FFC0C000FFC0C000FFC0C000FFC0C000FFC0C000FFC0C000FFC0
          C000FFC0C00080808000FFC0C000FFC0C000FFC0C000FFC00000FF4060004000
          0000FFC0C000FFC0C000FFC0C000FFC0C000FFC0C000FF40600080404000FFC0
          C000FFC0C000FFC0C000FFFFFF00FFC0C00080808000FFFFFF00FFFFFF00FFFF
          FF00FFFFFF00FFFFFF00FFC0C00080808000FFC0C000FFC0C000FFC0C000FFC0
          0000FF40600040000000FFFFFF00FFFFFF00FFFFFF00FFFFFF00FFC0C000FF40
          600080404000FFC0C000FFC0C000FFC0C000FFFFFF00FFC0C00080808000FFC0
          C000FFC0C000FFC0C000FFC0C000FFFFFF00FFC0C00080808000FFC0C000FFC0
          C000FFC0C000FFC00000FF406000400000004000000040000000400000004000
          000040000000FF40600080404000FFC0C000FFC0C000FFC0C000FFFFFF00FFC0
          C000808080008080800080808000808080008080800080808000FFC0C0008080
          8000FFC0C000FFC0C000FFC0C000FFC00000FF406000FF406000FF406000FF40
          6000FF406000FF406000FF406000FF40600080404000FFC0C000FFC0C000FFC0
          C000FFFFFF00FFC0C000FFC0C000FFC0C000FFC0C000FFC0C000FFC0C000FFC0
          C000FFC0C00080808000FFC0C000FFC0C000FFC0C000FFC00000FFC00000FFC0
          0000FFC00000FFC00000FFC00000FFC00000FFC00000FFC0000080404000FFC0
          C000FFC0C000FFC0C000FFFFFF00FFFFFF00FFFFFF00FFFFFF00FFFFFF00FFFF
          FF00FFFFFF00FFFFFF00FFFFFF00FFFFFF00FFC0C000FFC0C000FFC0C000FFC0
          C000FFC0C000FFC0C000FFC0C000FFC0C000FFC0C000FFC0C000FFC0C000FFC0
          C000FFC0C000FFC0C000FFC0C000FFC0C000FFC0C000FFC0C000FFC0C000FFC0
          C000FFC0C000FFC0C000FFC0C000FFC0C000FFC0C000FFC0C000FFC0C000FFC0
          C000}
      end
      object TXTDESC: TActCurrencyEdit
        Left = 395
        Top = 47
        Width = 121
        Height = 21
        ColorOnFocus = 16311512
        ColorOnNotFocus = clWindow
        EditLabel.Width = 60
        EditLabel.Height = 13
        EditLabel.Caption = 'DESCONTO'
        TabOrder = 5
        Glyph.Data = {
          7E050000424D7E0500000000000036000000280000001A0000000D0000000100
          2000000000004805000000000000000000000000000000000000FFC0C000FFC0
          C000FFC0C000FFC0C000FFC0C000FFC0C000FFC0C000FFC0C000FFC0C000FFC0
          C000FFC0C000FFC0C000FFC0C000FFC0C000FFC0C000FFC0C000FFC0C000FFC0
          C000FFC0C000FFC0C000FFC0C000FFC0C000FFC0C000FFC0C000FFC0C000FFC0
          C000FFC0C0008040400080404000804040008040400080404000804040008040
          4000804040008040400080404000FFC0C000FFC0C000FFC0C000FFFFFF008080
          8000808080008080800080808000808080008080800080808000808080008080
          8000FFC0C000FFC0C000FFC0C000FFC00000FF406000FF406000FF406000FF40
          6000FF406000FF406000FF406000FF40600080404000FFC0C000FFC0C000FFC0
          C000FFFFFF00FFC0C000FFC0C000FFC0C000FFC0C000FFC0C000FFC0C000FFC0
          C000FFC0C00080808000FFC0C000FFC0C000FFC0C000FFC00000FF406000FFC0
          0000FFFFFF00FFC00000FFFFFF00FFC00000FFFFFF00FF40600080404000FFC0
          C000FFC0C000FFC0C000FFFFFF00FFC0C00080808000FFFFFF0080808000FFFF
          FF0080808000FFFFFF00FFC0C00080808000FFC0C000FFC0C000FFC0C000FFC0
          0000FF406000FF406000FF406000FF406000FF406000FF406000FF406000FF40
          600080404000FFC0C000FFC0C000FFC0C000FFFFFF00FFC0C000FFC0C000FFC0
          C000FFC0C000FFC0C000FFC0C000FFC0C000FFC0C00080808000FFC0C000FFC0
          C000FFC0C000FFC00000FF406000FFC00000FFFFFF00FFC00000FFFFFF00FFC0
          0000FFFFFF00FF40600080404000FFC0C000FFC0C000FFC0C000FFFFFF00FFC0
          C00080808000FFFFFF0080808000FFFFFF0080808000FFFFFF00FFC0C0008080
          8000FFC0C000FFC0C000FFC0C000FFC00000FF406000FF406000FF406000FF40
          6000FF406000FF406000FF406000FF40600080404000FFC0C000FFC0C000FFC0
          C000FFFFFF00FFC0C000FFC0C000FFC0C000FFC0C000FFC0C000FFC0C000FFC0
          C000FFC0C00080808000FFC0C000FFC0C000FFC0C000FFC00000FF4060004000
          0000FFC0C000FFC0C000FFC0C000FFC0C000FFC0C000FF40600080404000FFC0
          C000FFC0C000FFC0C000FFFFFF00FFC0C00080808000FFFFFF00FFFFFF00FFFF
          FF00FFFFFF00FFFFFF00FFC0C00080808000FFC0C000FFC0C000FFC0C000FFC0
          0000FF40600040000000FFFFFF00FFFFFF00FFFFFF00FFFFFF00FFC0C000FF40
          600080404000FFC0C000FFC0C000FFC0C000FFFFFF00FFC0C00080808000FFC0
          C000FFC0C000FFC0C000FFC0C000FFFFFF00FFC0C00080808000FFC0C000FFC0
          C000FFC0C000FFC00000FF406000400000004000000040000000400000004000
          000040000000FF40600080404000FFC0C000FFC0C000FFC0C000FFFFFF00FFC0
          C000808080008080800080808000808080008080800080808000FFC0C0008080
          8000FFC0C000FFC0C000FFC0C000FFC00000FF406000FF406000FF406000FF40
          6000FF406000FF406000FF406000FF40600080404000FFC0C000FFC0C000FFC0
          C000FFFFFF00FFC0C000FFC0C000FFC0C000FFC0C000FFC0C000FFC0C000FFC0
          C000FFC0C00080808000FFC0C000FFC0C000FFC0C000FFC00000FFC00000FFC0
          0000FFC00000FFC00000FFC00000FFC00000FFC00000FFC0000080404000FFC0
          C000FFC0C000FFC0C000FFFFFF00FFFFFF00FFFFFF00FFFFFF00FFFFFF00FFFF
          FF00FFFFFF00FFFFFF00FFFFFF00FFFFFF00FFC0C000FFC0C000FFC0C000FFC0
          C000FFC0C000FFC0C000FFC0C000FFC0C000FFC0C000FFC0C000FFC0C000FFC0
          C000FFC0C000FFC0C000FFC0C000FFC0C000FFC0C000FFC0C000FFC0C000FFC0
          C000FFC0C000FFC0C000FFC0C000FFC0C000FFC0C000FFC0C000FFC0C000FFC0
          C000}
      end
      object TXTDIAS: TActNumberEdit
        Left = 395
        Top = 99
        Width = 121
        Height = 21
        Alignment = taLeftJustify
        ColorOnFocus = 16311512
        ColorOnNotFocus = clWindow
        EditLabel.Width = 150
        EditLabel.Height = 13
        EditLabel.Caption = 'QNT DIAS ENTRE PARCELAS'
        TabOrder = 7
        Value = 0
      end
      object TXTACRES: TActCurrencyEdit
        Left = 395
        Top = 74
        Width = 121
        Height = 21
        ColorOnFocus = 16311512
        ColorOnNotFocus = clWindow
        EditLabel.Width = 63
        EditLabel.Height = 13
        EditLabel.Caption = 'ACRESCIMO'
        TabOrder = 6
        Glyph.Data = {
          7E050000424D7E0500000000000036000000280000001A0000000D0000000100
          2000000000004805000000000000000000000000000000000000FFC0C000FFC0
          C000FFC0C000FFC0C000FFC0C000FFC0C000FFC0C000FFC0C000FFC0C000FFC0
          C000FFC0C000FFC0C000FFC0C000FFC0C000FFC0C000FFC0C000FFC0C000FFC0
          C000FFC0C000FFC0C000FFC0C000FFC0C000FFC0C000FFC0C000FFC0C000FFC0
          C000FFC0C0008040400080404000804040008040400080404000804040008040
          4000804040008040400080404000FFC0C000FFC0C000FFC0C000FFFFFF008080
          8000808080008080800080808000808080008080800080808000808080008080
          8000FFC0C000FFC0C000FFC0C000FFC00000FF406000FF406000FF406000FF40
          6000FF406000FF406000FF406000FF40600080404000FFC0C000FFC0C000FFC0
          C000FFFFFF00FFC0C000FFC0C000FFC0C000FFC0C000FFC0C000FFC0C000FFC0
          C000FFC0C00080808000FFC0C000FFC0C000FFC0C000FFC00000FF406000FFC0
          0000FFFFFF00FFC00000FFFFFF00FFC00000FFFFFF00FF40600080404000FFC0
          C000FFC0C000FFC0C000FFFFFF00FFC0C00080808000FFFFFF0080808000FFFF
          FF0080808000FFFFFF00FFC0C00080808000FFC0C000FFC0C000FFC0C000FFC0
          0000FF406000FF406000FF406000FF406000FF406000FF406000FF406000FF40
          600080404000FFC0C000FFC0C000FFC0C000FFFFFF00FFC0C000FFC0C000FFC0
          C000FFC0C000FFC0C000FFC0C000FFC0C000FFC0C00080808000FFC0C000FFC0
          C000FFC0C000FFC00000FF406000FFC00000FFFFFF00FFC00000FFFFFF00FFC0
          0000FFFFFF00FF40600080404000FFC0C000FFC0C000FFC0C000FFFFFF00FFC0
          C00080808000FFFFFF0080808000FFFFFF0080808000FFFFFF00FFC0C0008080
          8000FFC0C000FFC0C000FFC0C000FFC00000FF406000FF406000FF406000FF40
          6000FF406000FF406000FF406000FF40600080404000FFC0C000FFC0C000FFC0
          C000FFFFFF00FFC0C000FFC0C000FFC0C000FFC0C000FFC0C000FFC0C000FFC0
          C000FFC0C00080808000FFC0C000FFC0C000FFC0C000FFC00000FF4060004000
          0000FFC0C000FFC0C000FFC0C000FFC0C000FFC0C000FF40600080404000FFC0
          C000FFC0C000FFC0C000FFFFFF00FFC0C00080808000FFFFFF00FFFFFF00FFFF
          FF00FFFFFF00FFFFFF00FFC0C00080808000FFC0C000FFC0C000FFC0C000FFC0
          0000FF40600040000000FFFFFF00FFFFFF00FFFFFF00FFFFFF00FFC0C000FF40
          600080404000FFC0C000FFC0C000FFC0C000FFFFFF00FFC0C00080808000FFC0
          C000FFC0C000FFC0C000FFC0C000FFFFFF00FFC0C00080808000FFC0C000FFC0
          C000FFC0C000FFC00000FF406000400000004000000040000000400000004000
          000040000000FF40600080404000FFC0C000FFC0C000FFC0C000FFFFFF00FFC0
          C000808080008080800080808000808080008080800080808000FFC0C0008080
          8000FFC0C000FFC0C000FFC0C000FFC00000FF406000FF406000FF406000FF40
          6000FF406000FF406000FF406000FF40600080404000FFC0C000FFC0C000FFC0
          C000FFFFFF00FFC0C000FFC0C000FFC0C000FFC0C000FFC0C000FFC0C000FFC0
          C000FFC0C00080808000FFC0C000FFC0C000FFC0C000FFC00000FFC00000FFC0
          0000FFC00000FFC00000FFC00000FFC00000FFC00000FFC0000080404000FFC0
          C000FFC0C000FFC0C000FFFFFF00FFFFFF00FFFFFF00FFFFFF00FFFFFF00FFFF
          FF00FFFFFF00FFFFFF00FFFFFF00FFFFFF00FFC0C000FFC0C000FFC0C000FFC0
          C000FFC0C000FFC0C000FFC0C000FFC0C000FFC0C000FFC0C000FFC0C000FFC0
          C000FFC0C000FFC0C000FFC0C000FFC0C000FFC0C000FFC0C000FFC0C000FFC0
          C000FFC0C000FFC0C000FFC0C000FFC0C000FFC0C000FFC0C000FFC0C000FFC0
          C000}
      end
      object txtparcela: TActNumberEdit
        Left = 94
        Top = 71
        Width = 121
        Height = 21
        Alignment = taLeftJustify
        ColorOnFocus = 16311512
        ColorOnNotFocus = clWindow
        EditLabel.Width = 64
        EditLabel.Height = 13
        EditLabel.Caption = 'N'#186' PARCELA'
        TabOrder = 2
        Value = 0
      end
      object TXTFORNECEDOR: TActResultEdit
        Left = 395
        Top = 127
        Width = 121
        Height = 21
        Alignment = taLeftJustify
        ColorOnFocus = 16311512
        ColorOnNotFocus = clWindow
        NotNull = True
        EditLabel.Width = 75
        EditLabel.Height = 13
        EditLabel.Caption = 'FORNECEDOR'
        TabOrder = 8
        Value = 0
        Glyph.Data = {
          46030000424D460300000000000036000000280000000E0000000E0000000100
          2000000000001003000000000000000000000000000000000000808000008080
          0000808000008080000080800000808000008080000080800000808000008080
          0000808000008080000080800000808000008080000080800000808000008080
          0000808000008080000080800000808000008080000080800000808000008080
          0000808000008080000080800000808000008080000080800000808000008080
          0000808000008080000080800000808000008080000080800000808000008080
          0000808000000000000000000000808000008080000000000000000000008080
          0000808000000000000000000000808000008080000080800000808000000000
          0000000000008080000080800000000000000000000080800000808000000000
          0000000000008080000080800000808000008080000080800000808000008080
          0000808000008080000080800000808000008080000080800000808000008080
          0000808000008080000080800000808000008080000080800000808000008080
          0000808000008080000080800000808000008080000080800000808000008080
          0000808000008080000080800000808000008080000080800000808000008080
          0000808000008080000080800000808000008080000080800000808000008080
          0000808000008080000080800000808000008080000080800000808000008080
          0000808000008080000080800000808000008080000080800000808000008080
          0000808000008080000080800000808000008080000080800000808000008080
          0000808000008080000080800000808000008080000080800000808000008080
          0000808000008080000080800000808000008080000080800000808000008080
          0000808000008080000080800000808000008080000080800000808000008080
          0000808000008080000080800000808000008080000080800000808000008080
          0000808000008080000080800000808000008080000080800000808000008080
          0000808000008080000080800000808000008080000080800000808000008080
          0000808000008080000080800000808000008080000080800000808000008080
          00008080000080800000}
        NumGlyphs = 1
      end
      object GroupBox2: TGroupBox
        Left = 16
        Top = 168
        Width = 809
        Height = 105
        Caption = 'OBSERVA'#199#213'ES'
        TabOrder = 9
        object Memo1: TMemo
          Left = 2
          Top = 15
          Width = 805
          Height = 88
          Align = alClient
          Lines.Strings = (
            '')
          TabOrder = 0
        end
      end
    end
  end
  object QryTiTULOS: TIBQuery
    Database = DataModule1.DBPrincipal
    Transaction = DataModule1.IBTransaction1
    BufferChunks = 1000
    CachedUpdates = False
    Left = 256
    Top = 27
  end
  object DataSource1: TDataSource
    DataSet = IBQuery1
    Left = 259
    Top = 65
  end
  object IBQuery1: TIBQuery
    Database = DataModule1.DBPrincipal
    Transaction = DataModule1.IBTransaction1
    Active = True
    BufferChunks = 1000
    CachedUpdates = False
    SQL.Strings = (
      'select *from titulospagar2 where status = '#39'D'#39)
    UpdateObject = IBUpdateSQL1
    Left = 299
    Top = 63
    object IBQuery1DCNUMERO: TIntegerField
      FieldName = 'DCNUMERO'
      Origin = 'TITULOSPAGAR2.DCNUMERO'
    end
    object IBQuery1DCSERIE: TIBStringField
      FieldName = 'DCSERIE'
      Origin = 'TITULOSPAGAR2.DCSERIE'
      Size = 3
    end
    object IBQuery1DCORDEM: TIBStringField
      FieldName = 'DCORDEM'
      Origin = 'TITULOSPAGAR2.DCORDEM'
      Size = 2
    end
    object IBQuery1DCTIPO: TIBStringField
      FieldName = 'DCTIPO'
      Origin = 'TITULOSPAGAR2.DCTIPO'
      Size = 6
    end
    object IBQuery1PARCELA: TFloatField
      FieldName = 'PARCELA'
      Origin = 'TITULOSPAGAR2.PARCELA'
    end
    object IBQuery1DTVENCIMENTO: TDateField
      FieldName = 'DTVENCIMENTO'
      Origin = 'TITULOSPAGAR2.DTVENCIMENTO'
    end
    object IBQuery1DTLANCAMENTO: TDateField
      FieldName = 'DTLANCAMENTO'
      Origin = 'TITULOSPAGAR2.DTLANCAMENTO'
    end
    object IBQuery1STATUS: TIBStringField
      FieldName = 'STATUS'
      Origin = 'TITULOSPAGAR2.STATUS'
      Size = 1
    end
    object IBQuery1TPSITUACAO: TIBStringField
      FieldName = 'TPSITUACAO'
      Origin = 'TITULOSPAGAR2.TPSITUACAO'
      Size = 1
    end
    object IBQuery1VLPARCELA: TFloatField
      FieldName = 'VLPARCELA'
      Origin = 'TITULOSPAGAR2.VLPARCELA'
    end
    object IBQuery1FORNECEDOR: TFloatField
      FieldName = 'FORNECEDOR'
      Origin = 'TITULOSPAGAR2.FORNECEDOR'
    end
    object IBQuery1OBS: TIBStringField
      FieldName = 'OBS'
      Origin = 'TITULOSPAGAR2.OBS'
      Size = 255
    end
    object IBQuery1DATAPAGAMENTO: TDateField
      FieldName = 'DATAPAGAMENTO'
      Origin = 'TITULOSPAGAR2.DATAPAGAMENTO'
    end
    object IBQuery1TIPO_TITULO: TIntegerField
      FieldName = 'TIPO_TITULO'
      Origin = 'TITULOSPAGAR2.TIPO_TITULO'
    end
    object IBQuery1COD_EMPRESA: TIntegerField
      FieldName = 'COD_EMPRESA'
      Origin = 'TITULOSPAGAR2.COD_EMPRESA'
    end
    object IBQuery1COD_FILIAL: TIntegerField
      FieldName = 'COD_FILIAL'
      Origin = 'TITULOSPAGAR2.COD_FILIAL'
    end
    object IBQuery1COD_CONTA: TIntegerField
      FieldName = 'COD_CONTA'
      Origin = 'TITULOSPAGAR2.COD_CONTA'
    end
    object IBQuery1COD_TITULO: TIntegerField
      FieldName = 'COD_TITULO'
      Origin = 'TITULOSPAGAR2.COD_TITULO'
    end
    object IBQuery1VALORPAGO: TFloatField
      FieldName = 'VALORPAGO'
      Origin = 'TITULOSPAGAR2.VALORPAGO'
    end
    object IBQuery1OPER_TITULO: TIBStringField
      FieldName = 'OPER_TITULO'
      Origin = 'TITULOSPAGAR2.OPER_TITULO'
      Size = 1
    end
    object IBQuery1ID_MOV_DIARIO: TIntegerField
      FieldName = 'ID_MOV_DIARIO'
      Origin = 'TITULOSPAGAR2.ID_MOV_DIARIO'
    end
    object IBQuery1NOCHEQUE: TIBStringField
      FieldName = 'NOCHEQUE'
      Origin = 'TITULOSPAGAR2.NOCHEQUE'
      Size = 15
    end
    object IBQuery1DESCONTO: TFloatField
      FieldName = 'DESCONTO'
      Origin = 'TITULOSPAGAR2.DESCONTO'
    end
    object IBQuery1JUROS: TFloatField
      FieldName = 'JUROS'
      Origin = 'TITULOSPAGAR2.JUROS'
    end
    object IBQuery1DATA_APROV_PAG: TDateTimeField
      FieldName = 'DATA_APROV_PAG'
      Origin = 'TITULOSPAGAR2.DATA_APROV_PAG'
    end
    object IBQuery1RESP_APROV_PAG: TIntegerField
      FieldName = 'RESP_APROV_PAG'
      Origin = 'TITULOSPAGAR2.RESP_APROV_PAG'
    end
    object IBQuery1DATA_PAG: TDateTimeField
      FieldName = 'DATA_PAG'
      Origin = 'TITULOSPAGAR2.DATA_PAG'
    end
    object IBQuery1RESP_PAG: TIntegerField
      FieldName = 'RESP_PAG'
      Origin = 'TITULOSPAGAR2.RESP_PAG'
    end
  end
  object IBUpdateSQL1: TIBUpdateSQL
    RefreshSQL.Strings = (
      'Select '
      'from titulospagar2 '
      'where'
      '  COD_TITULO = :COD_TITULO')
    ModifySQL.Strings = (
      'update titulospagar2'
      'set'
      '  DCNUMERO = :DCNUMERO,'
      '  DCSERIE = :DCSERIE,'
      '  DCORDEM = :DCORDEM,'
      '  DCTIPO = :DCTIPO,'
      '  PARCELA = :PARCELA,'
      '  DTVENCIMENTO = :DTVENCIMENTO,'
      '  DTLANCAMENTO = :DTLANCAMENTO,'
      '  STATUS = :STATUS,'
      '  TPSITUACAO = :TPSITUACAO,'
      '  VLPARCELA = :VLPARCELA,'
      '  FORNECEDOR = :FORNECEDOR,'
      '  OBS = :OBS,'
      '  DATAPAGAMENTO = :DATAPAGAMENTO,'
      '  TIPO_TITULO = :TIPO_TITULO,'
      '  COD_EMPRESA = :COD_EMPRESA,'
      '  COD_FILIAL = :COD_FILIAL,'
      '  COD_CONTA = :COD_CONTA,'
      '  COD_TITULO = :COD_TITULO,'
      '  VALORPAGO = :VALORPAGO,'
      '  OPER_TITULO = :OPER_TITULO,'
      '  ID_MOV_DIARIO = :ID_MOV_DIARIO,'
      '  NOCHEQUE = :NOCHEQUE,'
      '  DESCONTO = :DESCONTO,'
      '  JUROS = :JUROS'
      'where'
      '  COD_TITULO = :OLD_COD_TITULO')
    InsertSQL.Strings = (
      'insert into titulospagar2'
      
        '  (DCNUMERO, DCSERIE, DCORDEM, DCTIPO, PARCELA, DTVENCIMENTO, DT' +
        'LANCAMENTO, '
      
        '   STATUS, TPSITUACAO, VLPARCELA, FORNECEDOR, OBS, DATAPAGAMENTO' +
        ', TIPO_TITULO, '
      
        '   COD_EMPRESA, COD_FILIAL, COD_CONTA, COD_TITULO, VALORPAGO, OP' +
        'ER_TITULO, '
      '   ID_MOV_DIARIO, NOCHEQUE, DESCONTO, JUROS)'
      'values'
      
        '  (:DCNUMERO, :DCSERIE, :DCORDEM, :DCTIPO, :PARCELA, :DTVENCIMEN' +
        'TO, :DTLANCAMENTO, '
      
        '   :STATUS, :TPSITUACAO, :VLPARCELA, :FORNECEDOR, :OBS, :DATAPAG' +
        'AMENTO, '
      
        '   :TIPO_TITULO, :COD_EMPRESA, :COD_FILIAL, :COD_CONTA, :COD_TIT' +
        'ULO, :VALORPAGO, '
      '   :OPER_TITULO, :ID_MOV_DIARIO, :NOCHEQUE, :DESCONTO, :JUROS)')
    DeleteSQL.Strings = (
      'delete from titulospagar2'
      'where'
      '  COD_TITULO = :OLD_COD_TITULO')
    Left = 297
    Top = 26
  end
end
