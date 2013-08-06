object FrmFiltrar: TFrmFiltrar
  Left = 274
  Top = 157
  BorderIcons = [biSystemMenu]
  BorderStyle = bsToolWindow
  Caption = 'Filtrar'
  ClientHeight = 310
  ClientWidth = 470
  Color = clBtnFace
  Font.Charset = ANSI_CHARSET
  Font.Color = clWindowText
  Font.Height = -13
  Font.Name = 'Times New Roman'
  Font.Style = []
  OldCreateOrder = False
  Position = poScreenCenter
  PixelsPerInch = 96
  TextHeight = 15
  object CoolBar1: TCoolBar
    Left = 0
    Top = 0
    Width = 470
    Height = 269
    Bands = <>
  end
  object StringGrid1: TStringGrid
    Left = 6
    Top = 6
    Width = 456
    Height = 212
    ColCount = 3
    DefaultColWidth = 144
    DefaultRowHeight = 18
    RowCount = 2
    Options = [goFixedVertLine, goFixedHorzLine, goVertLine, goHorzLine, goRangeSelect, goEditing]
    ScrollBars = ssVertical
    TabOrder = 0
    RowHeights = (
      18
      18)
  end
  object CheckBox1: TCheckBox
    Left = 16
    Top = 226
    Width = 137
    Height = 17
    Caption = 'Compara'#231#227'o parcial'
    Checked = True
    Enabled = False
    State = cbChecked
    TabOrder = 1
  end
  object CheckBox2: TCheckBox
    Left = 16
    Top = 246
    Width = 185
    Height = 17
    Caption = 'Ignora mai'#250'scula/min'#250'scula'
    Enabled = False
    TabOrder = 2
  end
  object BitBtn1: TBrvBitBtn
    Left = 142
    Top = 280
    Width = 75
    Height = 25
    Caption = '&Ok'
    Default = True
    DoubleBuffered = True
    ModalResult = 1
    NumGlyphs = 2
    ParentDoubleBuffered = False
    TabOrder = 3
    BrTipoBotao = BrBtnOk
  end
  object BitBtn2: TBrvBitBtn
    Left = 254
    Top = 280
    Width = 75
    Height = 25
    Cancel = True
    Caption = '&Cancela'
    DoubleBuffered = True
    ModalResult = 2
    NumGlyphs = 2
    ParentDoubleBuffered = False
    TabOrder = 4
    BrTipoBotao = BrBtnCancel
  end
end
