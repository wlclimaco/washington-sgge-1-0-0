inherited ModeloForm11: TModeloForm11
  Left = 256
  Top = 147
  Width = 475
  Height = 528
  Caption = 'ModeloForm11'
  OldCreateOrder = True
  OnCreate = FormCreate
  PixelsPerInch = 96
  TextHeight = 13
  inherited Panel1: TPanel
    Width = 467
  end
  inherited Panel2: TPanel
    Left = 340
    Height = 437
    inherited SpeedButton1: TSpeedButton
      Left = 15
      OnClick = SpeedButton1Click
    end
    inherited SpeedButton2: TSpeedButton
      OnClick = SpeedButton2Click
    end
    inherited SpeedButton3: TSpeedButton
      OnClick = SpeedButton3Click
    end
    inherited SpeedButton4: TSpeedButton
      OnClick = SpeedButton4Click
    end
    inherited SpeedButton5: TSpeedButton
      OnClick = SpeedButton5Click
    end
    inherited SpeedButton6: TSpeedButton
      OnClick = SpeedButton6Click
    end
    inherited SpeedButton7: TSpeedButton
      OnClick = SpeedButton7Click
    end
    inherited SpeedButton8: TSpeedButton
      OnClick = SpeedButton8Click
    end
    inherited SpeedButton9: TSpeedButton
      Top = 201
      OnClick = SpeedButton9Click
    end
    object SpeedButton11: TSpeedButton
      Left = 16
      Top = 247
      Width = 89
      Height = 22
      Caption = 'Lan'#231'ar'
      OnClick = SpeedButton11Click
    end
  end
  inherited Panel3: TPanel
    Width = 340
    Height = 437
    object Label1: TLabel
      Left = 160
      Top = 9
      Width = 11
      Height = 13
      Caption = 'ID'
    end
    object Label2: TLabel
      Left = 158
      Top = 49
      Width = 95
      Height = 13
      Caption = 'DATAMOVIMENTO'
    end
    object Label3: TLabel
      Left = 160
      Top = 89
      Width = 35
      Height = 13
      Caption = 'TOTAL'
    end
    object Label4: TLabel
      Left = 160
      Top = 130
      Width = 53
      Height = 13
      Caption = 'DINHEIRO'
    end
    object Label5: TLabel
      Left = 162
      Top = 169
      Width = 45
      Height = 13
      Caption = 'CHEQUE'
    end
    object Label6: TLabel
      Left = 162
      Top = 209
      Width = 98
      Height = 13
      Caption = 'CARTAO_CREDITO'
    end
    object Label7: TLabel
      Left = 162
      Top = 249
      Width = 90
      Height = 13
      Caption = 'CARTAO_DEBITO'
    end
    object Label8: TLabel
      Left = 162
      Top = 289
      Width = 75
      Height = 13
      Caption = 'PROMISSORIA'
    end
    object TXTID: TActNumberEdit
      Left = 160
      Top = 24
      Width = 121
      Height = 21
      Alignment = taLeftJustify
      ColorOnFocus = 16311512
      ColorOnNotFocus = clWindow
      EditLabel.Width = 32
      EditLabel.Height = 13
      EditLabel.Caption = 'TXTID'
      TabOrder = 0
      Value = 0
    end
    object TXTDTMOVIMENTO: TActDateEdit
      Left = 159
      Top = 67
      Width = 121
      Height = 21
      ColorOnFocus = 16311512
      ColorOnNotFocus = clWindow
      EditLabel.Width = 102
      EditLabel.Height = 13
      EditLabel.Caption = 'TXTDTMOVIMENTO'
      TabOrder = 1
      Text = 'TXTDTMOVIMENTO'
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
    object TXTTOTAL: TActCurrencyEdit
      Left = 158
      Top = 107
      Width = 121
      Height = 21
      ColorOnFocus = 16311512
      ColorOnNotFocus = clWindow
      EditLabel.Width = 56
      EditLabel.Height = 13
      EditLabel.Caption = 'TXTTOTAL'
      TabOrder = 2
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
    object TXTDINHEIRO: TActCurrencyEdit
      Left = 160
      Top = 146
      Width = 121
      Height = 21
      ColorOnFocus = 16311512
      ColorOnNotFocus = clWindow
      EditLabel.Width = 74
      EditLabel.Height = 13
      EditLabel.Caption = 'TXTDINHEIRO'
      TabOrder = 3
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
    object TXTCHEQUE: TActCurrencyEdit
      Left = 162
      Top = 185
      Width = 121
      Height = 21
      ColorOnFocus = 16311512
      ColorOnNotFocus = clWindow
      EditLabel.Width = 66
      EditLabel.Height = 13
      EditLabel.Caption = 'TXTCHEQUE'
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
    object TXTCREDITO: TActCurrencyEdit
      Left = 162
      Top = 225
      Width = 121
      Height = 21
      ColorOnFocus = 16311512
      ColorOnNotFocus = clWindow
      EditLabel.Width = 69
      EditLabel.Height = 13
      EditLabel.Caption = 'TXTCREDITO'
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
    object TXTDEBITO: TActCurrencyEdit
      Left = 162
      Top = 266
      Width = 121
      Height = 21
      ColorOnFocus = 16311512
      ColorOnNotFocus = clWindow
      EditLabel.Width = 61
      EditLabel.Height = 13
      EditLabel.Caption = 'TXTDEBITO'
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
    object TXTPROMISSORIA: TActCurrencyEdit
      Left = 162
      Top = 307
      Width = 121
      Height = 21
      ColorOnFocus = 16311512
      ColorOnNotFocus = clWindow
      EditLabel.Width = 96
      EditLabel.Height = 13
      EditLabel.Caption = 'TXTPROMISSORIA'
      TabOrder = 7
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
    object GroupBox1: TGroupBox
      Left = 8
      Top = 360
      Width = 97
      Height = 49
      Caption = 'Soma Cupons'
      TabOrder = 8
      object Label9: TLabel
        Left = 8
        Top = 24
        Width = 65
        Height = 16
        Caption = 'Label9'
        Font.Charset = DEFAULT_CHARSET
        Font.Color = clRed
        Font.Height = -13
        Font.Name = 'MS Sans Serif'
        Font.Style = [fsBold]
        ParentFont = False
      end
    end
    object GroupBox2: TGroupBox
      Left = 120
      Top = 360
      Width = 97
      Height = 49
      Caption = 'Redu'#231#227'o '
      TabOrder = 9
      object Label10: TLabel
        Left = 8
        Top = 24
        Width = 48
        Height = 16
        Caption = 'Label9'
        Font.Charset = DEFAULT_CHARSET
        Font.Color = clRed
        Font.Height = -13
        Font.Name = 'MS Sans Serif'
        Font.Style = [fsBold]
        ParentFont = False
      end
    end
    object GroupBox3: TGroupBox
      Left = 224
      Top = 360
      Width = 97
      Height = 49
      Caption = 'Diferen'#231'a'
      TabOrder = 10
      object Label11: TLabel
        Left = 8
        Top = 24
        Width = 48
        Height = 16
        Caption = 'Label9'
        Font.Charset = DEFAULT_CHARSET
        Font.Color = clRed
        Font.Height = -13
        Font.Name = 'MS Sans Serif'
        Font.Style = [fsBold]
        ParentFont = False
      end
    end
  end
  object IBQuery1: TIBQuery
    Database = DataModule3.IBDatabase1
    Transaction = DataModule3.IBTransaction1
    BufferChunks = 1000
    CachedUpdates = False
    SQL.Strings = (
      'select *from movimentodiario')
    UpdateObject = IBUpdateSQL1
    Left = 98
    Top = 16
    object IBQuery1ID: TIntegerField
      FieldName = 'ID'
      Origin = 'MOVIMENTODIARIO.ID'
    end
    object IBQuery1DATAMOVIMENTO: TDateField
      FieldName = 'DATAMOVIMENTO'
      Origin = 'MOVIMENTODIARIO.DATAMOVIMENTO'
    end
    object IBQuery1TOTAL: TFloatField
      FieldName = 'TOTAL'
      Origin = 'MOVIMENTODIARIO.TOTAL'
    end
    object IBQuery1DINHEIRO: TFloatField
      FieldName = 'DINHEIRO'
      Origin = 'MOVIMENTODIARIO.DINHEIRO'
    end
    object IBQuery1CHEQUE: TFloatField
      FieldName = 'CHEQUE'
      Origin = 'MOVIMENTODIARIO.CHEQUE'
    end
    object IBQuery1CARTAO_CREDITO: TFloatField
      FieldName = 'CARTAO_CREDITO'
      Origin = 'MOVIMENTODIARIO.CARTAO_CREDITO'
    end
    object IBQuery1CARTAO_DEBITO: TFloatField
      FieldName = 'CARTAO_DEBITO'
      Origin = 'MOVIMENTODIARIO.CARTAO_DEBITO'
    end
    object IBQuery1PROMISSORIA: TFloatField
      FieldName = 'PROMISSORIA'
      Origin = 'MOVIMENTODIARIO.PROMISSORIA'
    end
  end
  object DataSource1: TDataSource
    DataSet = IBQuery1
    Left = 229
    Top = 13
  end
  object IBQInsertUpDel: TIBQuery
    Database = DataModule3.IBDatabase1
    Transaction = DataModule3.IBTransaction1
    BufferChunks = 1000
    CachedUpdates = False
    Left = 31
    Top = 15
  end
  object IBQInicial: TIBQuery
    Database = DataModule3.IBDatabase1
    Transaction = DataModule3.IBTransaction1
    BufferChunks = 1000
    CachedUpdates = False
    Left = 64
    Top = 15
  end
  object IBUpdateSQL1: TIBUpdateSQL
    RefreshSQL.Strings = (
      'Select '
      'from movimentodiario '
      'where'
      '  DATAMOVIMENTO = :DATAMOVIMENTO')
    ModifySQL.Strings = (
      'update movimentodiario'
      'set'
      '  ID = :ID,'
      '  DATAMOVIMENTO = :DATAMOVIMENTO,'
      '  TOTAL = :TOTAL,'
      '  DINHEIRO = :DINHEIRO,'
      '  CHEQUE = :CHEQUE,'
      '  CARTAO_CREDITO = :CARTAO_CREDITO,'
      '  CARTAO_DEBITO = :CARTAO_DEBITO,'
      '  PROMISSORIA = :PROMISSORIA'
      'where'
      '  DATAMOVIMENTO = :OLD_DATAMOVIMENTO')
    InsertSQL.Strings = (
      'insert into movimentodiario'
      
        '  (ID, DATAMOVIMENTO, TOTAL, DINHEIRO, CHEQUE, CARTAO_CREDITO, C' +
        'ARTAO_DEBITO, '
      '   PROMISSORIA)'
      'values'
      
        '  (:ID, :DATAMOVIMENTO, :TOTAL, :DINHEIRO, :CHEQUE, :CARTAO_CRED' +
        'ITO, :CARTAO_DEBITO, '
      '   :PROMISSORIA)')
    DeleteSQL.Strings = (
      'delete from movimentodiario'
      'where'
      '  DATAMOVIMENTO = :OLD_DATAMOVIMENTO')
    Left = 193
    Top = 12
  end
  object IBQuery2: TIBQuery
    Database = DataModule3.IBDatabase1
    Transaction = DataModule3.IBTransaction1
    BufferChunks = 1000
    CachedUpdates = False
    Left = 134
    Top = 16
  end
  object IBQuery3: TIBQuery
    Database = DataModule3.IBDatabase1
    Transaction = DataModule3.IBTransaction1
    BufferChunks = 1000
    CachedUpdates = False
    Left = 262
    Top = 16
  end
  object IBQuery4: TIBQuery
    Database = DataModule3.IBDatabase1
    Transaction = DataModule3.IBTransaction1
    BufferChunks = 1000
    CachedUpdates = False
    Left = 328
    Top = 16
  end
end
