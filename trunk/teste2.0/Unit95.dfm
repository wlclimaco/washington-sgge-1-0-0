object Form95: TForm95
  Left = 352
  Top = 254
  BorderStyle = bsNone
  Caption = 'Form95'
  ClientHeight = 198
  ClientWidth = 373
  Color = clBtnFace
  Font.Charset = DEFAULT_CHARSET
  Font.Color = clWindowText
  Font.Height = -11
  Font.Name = 'MS Sans Serif'
  Font.Style = []
  OldCreateOrder = False
  Position = poScreenCenter
  PixelsPerInch = 96
  TextHeight = 13
  object ProgressBar1: TProgressBar
    Left = 0
    Top = 168
    Width = 369
    Height = 17
    TabOrder = 0
  end
  object Timer1: TTimer
    Interval = 10000
    OnTimer = Timer1Timer
    Top = 8
  end
end
