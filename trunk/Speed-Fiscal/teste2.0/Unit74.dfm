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
      Height = 64
      AutoExpand = False
      AutoSize = True
      ColCount = 5
      ColSpacing = 2.000000000000000000
      InsideMargins.LeftMargin = 2.000000000000000000
      InsideMargins.TopMargin = 2.000000000000000000
      InsideMargins.RightMargin = 2.000000000000000000
      Margins.LeftMargin = 2.000000000000000000
      Margins.BottomMargin = 2.000000000000000000
      object RLDBText1: TRLDBText
        Left = 25
        Top = 15
        Width = 28
        Height = 10
        DataField = 'Produto'
        DataSource = Form73.DataSource2
        Font.Charset = DEFAULT_CHARSET
        Font.Color = clBlack
        Font.Height = -8
        Font.Name = 'Arial'
        Font.Style = []
        ParentFont = False
      end
      object rldbtxtCODPRO: TRLDBText
        Left = 23
        Top = 25
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
        Left = 65
        Top = 25
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
        Left = 28
        Top = 42
        Width = 53
        Height = 14
        AutoSize = False
        DataField = 'Preco'
        DataSource = Form73.DataSource2
        Font.Charset = DEFAULT_CHARSET
        Font.Color = clBlack
        Font.Height = -11
        Font.Name = 'Arial'
        Font.Style = []
        ParentFont = False
      end
      object RLLabel1: TRLLabel
        Left = 62
        Top = 25
        Width = 8
        Height = 16
        Caption = '-'
      end
      object RLLabel2: TRLLabel
        Left = 21
        Top = 10
        Width = 16
        Height = 16
        Caption = '   '
      end
      object RLLabel3: TRLLabel
        Left = 19
        Top = 23
        Width = 8
        Height = 16
        Caption = ' '
      end
      object RLLabel4: TRLLabel
        Left = 19
        Top = 39
        Width = 8
        Height = 16
        Caption = ' '
      end
    end
  end
  object RLGraphicStorage1: TRLGraphicStorage
    Left = 536
    Top = 96
  end
  object RLPreviewSetup1: TRLPreviewSetup
    Left = 488
    Top = 104
  end
  object RLPrintDialogSetup1: TRLPrintDialogSetup
    Copies = 0
    PrintToFile = True
    Left = 456
    Top = 104
  end
end
