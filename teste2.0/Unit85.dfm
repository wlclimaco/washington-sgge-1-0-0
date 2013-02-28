object Form85: TForm85
  Left = 211
  Top = 157
  Width = 813
  Height = 480
  Caption = 'Form85'
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
    DataSource = Form84.DataSource2
    Font.Charset = DEFAULT_CHARSET
    Font.Color = clBlack
    Font.Height = -13
    Font.Name = 'Arial'
    Font.Style = []
    object RLBand1: TRLBand
      Left = 38
      Top = 38
      Width = 718
      Height = 43
      BandType = btHeader
      object RLLabel1: TRLLabel
        Left = 320
        Top = 16
        Width = 150
        Height = 16
        Caption = 'SAIDAS DE PRODUTOS'
      end
    end
    object RLBand2: TRLBand
      Left = 38
      Top = 81
      Width = 718
      Height = 40
      BandType = btTitle
      object RLLabel2: TRLLabel
        Left = 16
        Top = 16
        Width = 60
        Height = 16
        Caption = 'CODPRO'
      end
      object RLLabel3: TRLLabel
        Left = 104
        Top = 16
        Width = 30
        Height = 16
        Caption = 'REF'
      end
      object RLLabel4: TRLLabel
        Left = 200
        Top = 16
        Width = 87
        Height = 16
        Caption = 'DATA VENDA'
      end
      object RLLabel5: TRLLabel
        Left = 328
        Top = 16
        Width = 105
        Height = 16
        Caption = 'N'#186' DOCUMENTO'
      end
      object RLLabel6: TRLLabel
        Left = 520
        Top = 16
        Width = 87
        Height = 16
        Caption = 'QUANTIDADE'
      end
      object RLLabel7: TRLLabel
        Left = 616
        Top = 16
        Width = 83
        Height = 16
        Caption = 'VL UNITARIO'
      end
    end
    object RLBand3: TRLBand
      Left = 38
      Top = 121
      Width = 718
      Height = 40
      object RLDBText1: TRLDBText
        Left = 24
        Top = 16
        Width = 45
        Height = 16
        DataField = 'Codpro'
        DataSource = Form84.DataSource2
      end
      object RLDBText2: TRLDBText
        Left = 104
        Top = 16
        Width = 63
        Height = 16
        DataField = 'Cdproduto'
        DataSource = Form84.DataSource2
      end
      object RLDBText3: TRLDBText
        Left = 200
        Top = 16
        Width = 99
        Height = 16
        DataField = 'DTMOVIMENTO'
        DataSource = Form84.DataSource2
      end
      object RLDBText4: TRLDBText
        Left = 328
        Top = 16
        Width = 27
        Height = 16
        DataField = 'Coo'
        DataSource = Form84.DataSource2
      end
      object RLDBText5: TRLDBText
        Left = 520
        Top = 16
        Width = 70
        Height = 16
        DataField = 'Quantidade'
        DataSource = Form84.DataSource2
      end
      object RLDBText6: TRLDBText
        Left = 616
        Top = 16
        Width = 59
        Height = 16
        DataField = 'Vlproduto'
        DataSource = Form84.DataSource2
      end
    end
    object RLBand4: TRLBand
      Left = 38
      Top = 161
      Width = 718
      Height = 24
      BandType = btSummary
      object RLSystemInfo1: TRLSystemInfo
        Left = 361
        Top = 6
        Width = 39
        Height = 14
      end
    end
  end
end
