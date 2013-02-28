object RelCap: TRelCap
  Left = 160
  Top = 141
  Width = 859
  Height = 473
  Caption = 'RelCap'
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
    Width = 1219
    Height = 1683
    DataSource = CONSCAP.DataSource1
    Font.Charset = DEFAULT_CHARSET
    Font.Color = clBlack
    Font.Height = -13
    Font.Name = 'Arial'
    Font.Style = []
    PageSetup.PaperSize = fpA3_Extra
    object RLBand1: TRLBand
      Left = 38
      Top = 78
      Width = 1143
      Height = 51
      BandType = btTitle
      object RLLabel2: TRLLabel
        Left = 8
        Top = 16
        Width = 57
        Height = 16
        Caption = 'CODPLC'
      end
      object RLLabel3: TRLLabel
        Left = 72
        Top = 16
        Width = 80
        Height = 16
        Caption = 'DESCRI'#199#195'O'
      end
      object RLLabel4: TRLLabel
        Left = 248
        Top = 16
        Width = 51
        Height = 16
        Caption = 'CODCLI'
      end
      object RLLabel5: TRLLabel
        Left = 328
        Top = 16
        Width = 99
        Height = 16
        Caption = 'RAZ'#195'O SOCIAL'
      end
      object RLLabel6: TRLLabel
        Left = 440
        Top = 16
        Width = 61
        Height = 16
        Caption = 'DOCNUM'
      end
      object RLLabel7: TRLLabel
        Left = 512
        Top = 16
        Width = 61
        Height = 16
        Caption = 'DOCTIPO'
      end
      object RLLabel8: TRLLabel
        Left = 584
        Top = 16
        Width = 82
        Height = 16
        Caption = 'DOC SEQUE'
      end
      object RLLabel9: TRLLabel
        Left = 680
        Top = 16
        Width = 32
        Height = 16
        Caption = 'OBS'
      end
      object RLLabel10: TRLLabel
        Left = 800
        Top = 16
        Width = 93
        Height = 16
        Caption = 'LAN'#199'AMENTO'
      end
      object RLLabel11: TRLLabel
        Left = 904
        Top = 16
        Width = 64
        Height = 16
        Caption = 'EMISS'#195'O'
      end
      object RLLabel12: TRLLabel
        Left = 976
        Top = 16
        Width = 48
        Height = 16
        Caption = 'VALOR'
      end
      object RLLabel13: TRLLabel
        Left = 1044
        Top = 16
        Width = 91
        Height = 16
        Caption = 'VENCIMENTO'
      end
    end
    object RLBand2: TRLBand
      Left = 38
      Top = 38
      Width = 1143
      Height = 40
      BandType = btHeader
      object RLLabel1: TRLLabel
        Left = 530
        Top = 6
        Width = 83
        Height = 34
        Align = faCenterBottom
        Caption = 'C.A.P'
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
      Width = 1143
      Height = 24
      object RLDBText1: TRLDBText
        Left = 8
        Top = 5
        Width = 57
        Height = 16
        DataField = 'CODPLC'
        DataSource = CONSCAP.DataSource1
      end
      object RLDBText2: TRLDBText
        Left = 72
        Top = 3
        Width = 80
        Height = 16
        DataField = 'DESCRICAO'
        DataSource = CONSCAP.DataSource1
      end
      object RLDBText3: TRLDBText
        Left = 248
        Top = 4
        Width = 51
        Height = 16
        DataField = 'CODCLI'
        DataSource = CONSCAP.DataSource1
      end
      object RLDBText4: TRLDBText
        Left = 328
        Top = 4
        Width = 48
        Height = 16
        DataField = 'RAZAO'
        DataSource = CONSCAP.DataSource1
      end
      object RLDBText5: TRLDBText
        Left = 440
        Top = 5
        Width = 61
        Height = 16
        DataField = 'DOCNUM'
        DataSource = CONSCAP.DataSource1
      end
      object RLDBText6: TRLDBText
        Left = 512
        Top = 5
        Width = 60
        Height = 16
        DataField = 'DOCESTI'
        DataSource = CONSCAP.DataSource1
      end
      object RLDBText7: TRLDBText
        Left = 586
        Top = 5
        Width = 69
        Height = 16
        DataField = 'DOCSEQU'
        DataSource = CONSCAP.DataSource1
      end
      object RLDBText8: TRLDBText
        Left = 680
        Top = 4
        Width = 32
        Height = 16
        DataField = 'OBS'
        DataSource = CONSCAP.DataSource1
      end
      object RLDBText9: TRLDBText
        Left = 803
        Top = 5
        Width = 63
        Height = 16
        DataField = 'DATLANC'
        DataSource = CONSCAP.DataSource1
      end
      object RLDBText10: TRLDBText
        Left = 904
        Top = 5
        Width = 61
        Height = 16
        DataField = 'DATEMIS'
        DataSource = CONSCAP.DataSource1
      end
      object RLDBText11: TRLDBText
        Left = 977
        Top = 5
        Width = 61
        Height = 16
        DataField = 'VALEMIS'
        DataSource = CONSCAP.DataSource1
        DisplayMask = '$'
      end
      object RLDBText12: TRLDBText
        Left = 1047
        Top = 4
        Width = 65
        Height = 16
        DataField = 'DATVENC'
        DataSource = CONSCAP.DataSource1
      end
    end
    object RLBand4: TRLBand
      Left = 38
      Top = 153
      Width = 1143
      Height = 40
      BandType = btSummary
      object RLSystemInfo1: TRLSystemInfo
        Left = 1016
        Top = 16
        Width = 121
        Height = 16
      end
    end
  end
  object RLXLSFilter1: TRLXLSFilter
    DisplayName = 'Planilha Excel'
    Left = 648
    Top = 16
  end
end
