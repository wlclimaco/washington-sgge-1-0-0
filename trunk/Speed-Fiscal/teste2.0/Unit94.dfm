object Form94: TForm94
  Left = 220
  Top = 167
  Width = 829
  Height = 602
  Caption = 'Form94'
  Color = clBtnFace
  Font.Charset = DEFAULT_CHARSET
  Font.Color = clWindowText
  Font.Height = -11
  Font.Name = 'MS Sans Serif'
  Font.Style = []
  OldCreateOrder = False
  OnActivate = FormActivate
  PixelsPerInch = 96
  TextHeight = 13
  object Query1: TQuery
    Active = True
    DatabaseName = 'laluna1'
    SQL.Strings = (
      'select dtmovimento,sum(vlproduto*quantidade) from nfsaidas2 '
      'group by dtmovimento')
    Left = 472
    Top = 24
  end
end
