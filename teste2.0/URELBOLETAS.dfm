object RelBoletas: TRelBoletas
  Left = 178
  Top = 297
  Width = 820
  Height = 480
  Caption = 'RelBoletas'
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
    Left = -2
    Top = -5
    Width = 794
    Height = 1123
    DataSource = DataModule2.dsBANCOS
    Font.Charset = DEFAULT_CHARSET
    Font.Color = clBlack
    Font.Height = -13
    Font.Name = 'Arial'
    Font.Style = []
    object RLBand1: TRLBand
      Left = 38
      Top = 78
      Width = 718
      Height = 51
      BandType = btTitle
      object RLLabel2: TRLLabel
        Left = 16
        Top = 16
        Width = 57
        Height = 16
        Caption = 'CODPLC'
      end
      object RLLabel3: TRLLabel
        Left = 112
        Top = 16
        Width = 55
        Height = 16
        Caption = 'CODIGO'
      end
      object RLLabel4: TRLLabel
        Left = 208
        Top = 16
        Width = 61
        Height = 16
        Caption = 'DOCNUM'
      end
      object RLLabel5: TRLLabel
        Left = 280
        Top = 16
        Width = 108
        Height = 16
        Caption = 'DOCSEQUENCIA'
      end
      object RLLabel6: TRLLabel
        Left = 400
        Top = 16
        Width = 47
        Height = 16
        Caption = 'JUROS'
      end
      object RLLabel7: TRLLabel
        Left = 456
        Top = 16
        Width = 78
        Height = 16
        Caption = 'ENCARGOS'
      end
      object RLLabel8: TRLLabel
        Left = 544
        Top = 16
        Width = 32
        Height = 16
        Caption = 'OBS'
      end
    end
    object RLBand2: TRLBand
      Left = 38
      Top = 38
      Width = 718
      Height = 40
      BandType = btHeader
      object RLLabel1: TRLLabel
        Left = 287
        Top = 6
        Width = 143
        Height = 34
        Align = faCenterBottom
        Caption = 'BOLETAS'
        Font.Charset = DEFAULT_CHARSET
        Font.Color = clBlack
        Font.Height = -29
        Font.Name = 'Arial'
        Font.Style = [fsBold]
        ParentFont = False
      end
    end
    object RLBand3: TRLBand
      Left = 38
      Top = 129
      Width = 718
      Height = 24
      object RLDBText1: TRLDBText
        Left = 16
        Top = 5
        Width = 57
        Height = 16
        DataField = 'CODPLC'
        DataSource = DataModule2.dsBOLETAS
      end
      object RLDBText2: TRLDBText
        Left = 112
        Top = 3
        Width = 55
        Height = 16
        DataField = 'CODIGO'
        DataSource = DataModule2.dsBOLETAS
      end
      object RLDBText3: TRLDBText
        Left = 208
        Top = 4
        Width = 61
        Height = 16
        DataField = 'DOCNUM'
        DataSource = DataModule2.dsBOLETAS
      end
      object RLDBText4: TRLDBText
        Left = 280
        Top = 4
        Width = 69
        Height = 16
        DataField = 'DOCSEQU'
        DataSource = DataModule2.dsBOLETAS
      end
      object RLDBText5: TRLDBText
        Left = 400
        Top = 5
        Width = 47
        Height = 16
        DataField = 'JUROS'
        DataSource = DataModule2.dsBOLETAS
      end
      object RLDBText7: TRLDBText
        Left = 457
        Top = 4
        Width = 78
        Height = 16
        DataField = 'ENCARGOS'
        DataSource = DataModule2.dsBOLETAS
      end
      object RLDBText8: TRLDBText
        Left = 545
        Top = 5
        Width = 39
        Height = 16
        DataField = 'OBS2'
        DataSource = DataModule2.dsBOLETAS
      end
    end
    object RLBand4: TRLBand
      Left = 38
      Top = 153
      Width = 718
      Height = 40
      BandType = btSummary
      object RLSystemInfo1: TRLSystemInfo
        Left = 584
        Top = 24
        Width = 121
        Height = 16
      end
    end
  end
end
