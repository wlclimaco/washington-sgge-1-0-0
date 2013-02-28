object Form115: TForm115
  Left = 212
  Top = 152
  Width = 783
  Height = 540
  Caption = 'Form115'
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
    Width = 794
    Height = 1123
    DataSource = Form842.DataSource2
    Font.Charset = DEFAULT_CHARSET
    Font.Color = clBlack
    Font.Height = -13
    Font.Name = 'Arial'
    Font.Style = []
    object RLBand1: TRLBand
      Left = 38
      Top = 38
      Width = 718
      Height = 35
      BandType = btHeader
      object RLLabel1: TRLLabel
        Left = 236
        Top = 0
        Width = 246
        Height = 29
        Align = faCenterTop
        Caption = 'SAIDA DE PRODUTO'
        Font.Charset = DEFAULT_CHARSET
        Font.Color = clBlack
        Font.Height = -24
        Font.Name = 'Arial'
        Font.Style = [fsBold]
        ParentFont = False
      end
    end
    object RLBand2: TRLBand
      Left = 38
      Top = 73
      Width = 718
      Height = 32
      BandType = btColumnHeader
      object RLLabel2: TRLLabel
        Left = 24
        Top = 16
        Width = 42
        Height = 16
        Caption = 'DATA '
      end
      object RLLabel3: TRLLabel
        Left = 104
        Top = 16
        Width = 64
        Height = 16
        Caption = 'COD PRO'
      end
      object RLLabel4: TRLLabel
        Left = 176
        Top = 16
        Width = 30
        Height = 16
        Caption = 'REF'
      end
      object RLLabel5: TRLLabel
        Left = 300
        Top = 16
        Width = 67
        Height = 16
        Caption = 'PRODUTO'
      end
      object RLLabel6: TRLLabel
        Left = 608
        Top = 16
        Width = 91
        Height = 16
        Caption = 'QUANTIDADE '
      end
    end
    object RLBand3: TRLBand
      Left = 38
      Top = 105
      Width = 718
      Height = 24
      object RLDBText1: TRLDBText
        Left = 24
        Top = 4
        Width = 77
        Height = 16
        DataField = 'dtmovimento'
        DataSource = Form842.DataSource2
      end
      object RLDBText2: TRLDBText
        Left = 104
        Top = 3
        Width = 60
        Height = 16
        DataField = 'CODPRO'
        DataSource = Form842.DataSource2
      end
      object RLDBText3: TRLDBText
        Left = 176
        Top = 3
        Width = 18
        Height = 16
        DataField = 'ref'
        DataSource = Form842.DataSource2
      end
      object RLDBText4: TRLDBText
        Left = 300
        Top = 3
        Width = 67
        Height = 16
        DataField = 'PRODUTO'
        DataSource = Form842.DataSource2
      end
      object RLDBText5: TRLDBText
        Left = 608
        Top = 3
        Width = 29
        Height = 16
        DataField = 'total'
        DataSource = Form842.DataSource2
      end
    end
    object RLBand4: TRLBand
      Left = 38
      Top = 129
      Width = 718
      Height = 32
      BandType = btColumnFooter
      object RLSystemInfo1: TRLSystemInfo
        Left = 608
        Top = 8
        Width = 37
        Height = 16
        Info = itNow
      end
      object RLSystemInfo2: TRLSystemInfo
        Left = 272
        Top = 8
        Width = 195
        Height = 16
        Info = itPagePreview
      end
    end
  end
end
