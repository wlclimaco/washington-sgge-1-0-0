object Form74: TForm74
  Left = 233
  Top = 158
  Width = 684
  Height = 480
  Caption = 'Form74'
  Color = clBtnFace
  Font.Charset = DEFAULT_CHARSET
  Font.Color = clWindowText
  Font.Height = -11
  Font.Name = 'MS Sans Serif'
  Font.Style = []
  OldCreateOrder = False
  PixelsPerInch = 96
  TextHeight = 13
  object RLReport1: TRLReport
    Left = 0
    Top = 0
    Width = 658
    Height = 888
    DataSource = Form73.DataSource2
    Font.Charset = DEFAULT_CHARSET
    Font.Color = clBlack
    Font.Height = -13
    Font.Name = 'Arial'
    Font.Style = []
    Margins.LeftMargin = 13.000000000000000000
    Margins.TopMargin = 1.000000000000000000
    Margins.RightMargin = 12.000000000000000000
    Margins.BottomMargin = 1.000000000000000000
    PageSetup.PaperSize = fpA5_Extra
    object RLDetailGrid1: TRLDetailGrid
      Left = 49
      Top = 4
      Width = 564
      Height = 61
      ColCount = 5
      ColSpacing = 1.000000000000000000
      object RLDBText1: TRLDBText
        Left = 8
        Top = 8
        Width = 40
        Height = 14
        DataField = 'Produto'
        DataSource = Form73.DataSource2
        Font.Charset = DEFAULT_CHARSET
        Font.Color = clBlack
        Font.Height = -11
        Font.Name = 'Arial'
        Font.Style = []
        ParentFont = False
      end
      object rldbtxtCODPRO: TRLDBText
        Left = 8
        Top = 40
        Width = 38
        Height = 14
        DataField = 'Codpro'
        DataSource = Form73.DataSource2
        Font.Charset = DEFAULT_CHARSET
        Font.Color = clBlack
        Font.Height = -11
        Font.Name = 'Arial'
        Font.Style = []
        ParentFont = False
      end
      object RLDBText3: TRLDBText
        Left = 8
        Top = 24
        Width = 32
        Height = 14
        DataField = 'Codcli'
        DataSource = Form73.DataSource2
        Font.Charset = DEFAULT_CHARSET
        Font.Color = clBlack
        Font.Height = -11
        Font.Name = 'Arial'
        Font.Style = []
        ParentFont = False
      end
      object RLDBText2: TRLDBText
        Left = 40
        Top = 40
        Width = 69
        Height = 16
        DataField = 'Preco'
        DataSource = Form73.DataSource2
        Font.Charset = DEFAULT_CHARSET
        Font.Color = clBlack
        Font.Height = -13
        Font.Name = 'Arial'
        Font.Style = [fsBold]
        ParentFont = False
        Text = 'FRM'
      end
    end
  end
end