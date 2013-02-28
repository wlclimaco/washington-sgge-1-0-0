object RelBaixas: TRelBaixas
  Left = 170
  Top = 224
  Width = 821
  Height = 480
  Caption = 'RelBaixas'
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
    DataSource = CONSBAIXAS.DataSource1
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
        Width = 51
        Height = 16
        Caption = 'CODIBX'
      end
      object RLLabel3: TRLLabel
        Left = 96
        Top = 16
        Width = 67
        Height = 16
        Caption = 'PRODUTO'
      end
      object RLLabel4: TRLLabel
        Left = 304
        Top = 16
        Width = 30
        Height = 16
        Caption = 'REF'
      end
      object RLLabel5: TRLLabel
        Left = 448
        Top = 16
        Width = 38
        Height = 16
        Caption = 'DATA'
      end
      object RLLabel6: TRLLabel
        Left = 536
        Top = 16
        Width = 46
        Height = 16
        Caption = 'LOCAL'
      end
      object RLLabel7: TRLLabel
        Left = 632
        Top = 16
        Width = 32
        Height = 16
        Caption = 'UNIT'
      end
    end
    object RLBand2: TRLBand
      Left = 38
      Top = 38
      Width = 718
      Height = 40
      BandType = btHeader
      object RLLabel1: TRLLabel
        Left = 309
        Top = 6
        Width = 100
        Height = 34
        Align = faCenterBottom
        Caption = 'Baixas'
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
        Width = 51
        Height = 16
        DataField = 'CODIBX'
        DataSource = CONSBAIXAS.DataSource1
      end
      object RLDBText2: TRLDBText
        Left = 96
        Top = 3
        Width = 67
        Height = 16
        DataField = 'PRODUTO'
        DataSource = CONSBAIXAS.DataSource1
      end
      object RLDBText3: TRLDBText
        Left = 304
        Top = 4
        Width = 30
        Height = 16
        DataField = 'REF'
        DataSource = CONSBAIXAS.DataSource1
      end
      object RLDBText4: TRLDBText
        Left = 448
        Top = 4
        Width = 38
        Height = 16
        DataField = 'DATA'
        DataSource = CONSBAIXAS.DataSource1
      end
      object RLDBText5: TRLDBText
        Left = 536
        Top = 5
        Width = 46
        Height = 16
        DataField = 'LOCAL'
        DataSource = CONSBAIXAS.DataSource1
      end
      object RLDBText6: TRLDBText
        Left = 632
        Top = 5
        Width = 32
        Height = 16
        DataField = 'UNIT'
        DataSource = CONSBAIXAS.DataSource1
      end
    end
    object RLBand4: TRLBand
      Left = 38
      Top = 153
      Width = 718
      Height = 32
      BandType = btSummary
      object RLSystemInfo1: TRLSystemInfo
        Left = 584
        Top = 8
        Width = 121
        Height = 16
      end
    end
  end
  object RLXLSFilter1: TRLXLSFilter
    DisplayName = 'Planilha Excel'
    Left = 544
    Top = 16
  end
end
