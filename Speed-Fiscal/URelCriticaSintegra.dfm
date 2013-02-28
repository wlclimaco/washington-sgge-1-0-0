object RelCriticaSintegra: TRelCriticaSintegra
  Left = 245
  Top = 216
  Width = 789
  Height = 516
  Caption = 'RelCriticaSintegra'
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
    DataSource = Form82.DataSource1
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
      BandType = btTitle
    end
    object RLBand2: TRLBand
      Left = 38
      Top = 73
      Width = 718
      Height = 24
      BandType = btColumnHeader
    end
    object RLBand3: TRLBand
      Left = 38
      Top = 97
      Width = 718
      Height = 40
      object RLDBText1: TRLDBText
        Left = 40
        Top = 16
        Width = 105
        Height = 16
        DataField = 'DTMOVIMENTO'
        DataSource = Form82.DataSource1
      end
      object RLDBText2: TRLDBText
        Left = 192
        Top = 16
        Width = 53
        Height = 16
        DataField = 'CODSIN'
        DataSource = Form82.DataSource1
      end
      object RLDBText3: TRLDBText
        Left = 328
        Top = 16
        Width = 67
        Height = 16
        DataField = 'PRODUTO'
        DataSource = Form82.DataSource1
      end
    end
    object RLBand4: TRLBand
      Left = 38
      Top = 137
      Width = 718
      Height = 40
      BandType = btColumnFooter
      object RLSystemInfo1: TRLSystemInfo
        Left = 592
        Top = 16
        Width = 89
        Height = 16
      end
    end
  end
end
