object Form68: TForm68
  Left = 202
  Top = 186
  Width = 705
  Height = 480
  Caption = 'Form68'
  Color = clBtnFace
  Font.Charset = DEFAULT_CHARSET
  Font.Color = clWindowText
  Font.Height = -11
  Font.Name = 'MS Sans Serif'
  Font.Style = []
  OldCreateOrder = False
  PixelsPerInch = 96
  TextHeight = 13
  object DBGrid1: TDBGrid
    Left = 0
    Top = 49
    Width = 697
    Height = 356
    Align = alClient
    DataSource = DataModule2.dsCLIENTES
    TabOrder = 0
    TitleFont.Charset = DEFAULT_CHARSET
    TitleFont.Color = clWindowText
    TitleFont.Height = -11
    TitleFont.Name = 'MS Sans Serif'
    TitleFont.Style = []
    OnDblClick = DBGrid1DblClick
    Columns = <
      item
        Expanded = False
        FieldName = 'CODCLI'
        Width = 43
        Visible = True
      end
      item
        Expanded = False
        FieldName = 'FANTASIA'
        Width = 206
        Visible = True
      end
      item
        Expanded = False
        FieldName = 'RAZAO'
        Width = 262
        Visible = True
      end
      item
        Expanded = False
        FieldName = 'ENDERECO'
        Width = 272
        Visible = True
      end
      item
        Expanded = False
        FieldName = 'FONE'
        Width = 84
        Visible = True
      end
      item
        Expanded = False
        FieldName = 'CGC'
        Visible = True
      end>
  end
  object Panel1: TPanel
    Left = 0
    Top = 0
    Width = 697
    Height = 49
    Align = alTop
    Color = clWhite
    TabOrder = 1
  end
  object Panel2: TPanel
    Left = 0
    Top = 405
    Width = 697
    Height = 41
    Align = alBottom
    Color = clWhite
    TabOrder = 2
  end
end
