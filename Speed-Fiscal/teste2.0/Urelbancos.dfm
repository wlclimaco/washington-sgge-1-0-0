object RelBancos: TRelBancos
  Left = 186
  Top = 254
  Width = 816
  Height = 480
  Caption = 'RelBancos'
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
        Width = 78
        Height = 16
        Caption = 'CODBANCO'
      end
      object RLLabel3: TRLLabel
        Left = 112
        Top = 16
        Width = 50
        Height = 16
        Caption = 'BANCO'
      end
      object RLLabel4: TRLLabel
        Left = 208
        Top = 16
        Width = 62
        Height = 16
        Caption = 'AGENCIA'
      end
      object RLLabel5: TRLLabel
        Left = 312
        Top = 16
        Width = 48
        Height = 16
        Caption = 'CONTA'
      end
      object RLLabel6: TRLLabel
        Left = 384
        Top = 16
        Width = 35
        Height = 16
        Caption = 'LIMIT'
      end
      object RLLabel7: TRLLabel
        Left = 464
        Top = 16
        Width = 47
        Height = 16
        Caption = 'SDANT'
      end
      object RLLabel8: TRLLabel
        Left = 536
        Top = 16
        Width = 50
        Height = 16
        Caption = 'DEPOS'
      end
      object RLLabel9: TRLLabel
        Left = 616
        Top = 16
        Width = 68
        Height = 16
        Caption = 'RETIRADA'
      end
    end
    object RLBand2: TRLBand
      Left = 38
      Top = 38
      Width = 718
      Height = 40
      BandType = btHeader
      object RLLabel1: TRLLabel
        Left = 294
        Top = 6
        Width = 130
        Height = 34
        Align = faCenterBottom
        Caption = 'BANCOS'
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
        Width = 59
        Height = 16
        DataField = 'CODBAN'
        DataSource = DataModule2.dsBANCOS
      end
      object RLDBText2: TRLDBText
        Left = 112
        Top = 3
        Width = 50
        Height = 16
        DataField = 'BANCO'
        DataSource = DataModule2.dsBANCOS
      end
      object RLDBText3: TRLDBText
        Left = 208
        Top = 4
        Width = 50
        Height = 16
        DataField = 'AGENC'
        DataSource = DataModule2.dsBANCOS
      end
      object RLDBText4: TRLDBText
        Left = 312
        Top = 4
        Width = 57
        Height = 16
        DataField = 'CCONTA'
        DataSource = DataModule2.dsBANCOS
      end
      object RLDBText5: TRLDBText
        Left = 384
        Top = 5
        Width = 35
        Height = 16
        DataField = 'LIMIT'
        DataSource = DataModule2.dsBANCOS
      end
      object RLDBText6: TRLDBText
        Left = 464
        Top = 5
        Width = 47
        Height = 16
        DataField = 'SDANT'
        DataSource = DataModule2.dsBANCOS
      end
      object RLDBText7: TRLDBText
        Left = 537
        Top = 4
        Width = 50
        Height = 16
        DataField = 'DEPOS'
        DataSource = DataModule2.dsBANCOS
      end
      object RLDBText8: TRLDBText
        Left = 617
        Top = 5
        Width = 41
        Height = 16
        DataField = 'RETIR'
        DataSource = DataModule2.dsBANCOS
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
end
