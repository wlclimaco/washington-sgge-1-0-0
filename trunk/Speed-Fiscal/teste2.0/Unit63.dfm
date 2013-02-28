object Form63: TForm63
  Left = 207
  Top = 172
  Width = 696
  Height = 465
  Caption = 'RelClientes'
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
    Top = -16
    Width = 794
    Height = 1123
    AdjustableMargins = True
    DataSource = CONSCLIENTES.DataSource1
    Font.Charset = DEFAULT_CHARSET
    Font.Color = clBlack
    Font.Height = -13
    Font.Name = 'Arial'
    Font.Style = []
    PageSetup.PaperSize = fpA4_Transverse
    object RLBand1: TRLBand
      Left = 38
      Top = 38
      Width = 718
      Height = 35
      BandType = btHeader
    end
    object RLBand2: TRLBand
      Left = 38
      Top = 73
      Width = 718
      Height = 40
      BandType = btTitle
    end
    object RLBand3: TRLBand
      Left = 38
      Top = 113
      Width = 718
      Height = 32
      object RLDBText1: TRLDBText
        Left = 8
        Top = 8
        Width = 51
        Height = 16
        DataField = 'CODCLI'
        DataSource = CONSCLIENTES.DataSource1
      end
      object RLDBText2: TRLDBText
        Left = 64
        Top = 8
        Width = 48
        Height = 16
        DataField = 'RAZAO'
        DataSource = CONSCLIENTES.DataSource1
      end
      object RLDBText3: TRLDBText
        Left = 192
        Top = 8
        Width = 67
        Height = 16
        DataField = 'FANTASIA'
        DataSource = CONSCLIENTES.DataSource1
      end
      object RLDBText4: TRLDBText
        Left = 320
        Top = 8
        Width = 32
        Height = 16
        DataField = 'CGC'
        DataSource = CONSCLIENTES.DataSource1
      end
      object RLDBText5: TRLDBText
        Left = 384
        Top = 8
        Width = 77
        Height = 16
        DataField = 'ENDERECO'
        DataSource = CONSCLIENTES.DataSource1
      end
      object RLDBText6: TRLDBText
        Left = 472
        Top = 8
        Width = 70
        Height = 16
        DataField = 'MUNICIPIO'
        DataSource = CONSCLIENTES.DataSource1
      end
      object RLDBText7: TRLDBText
        Left = 544
        Top = 8
        Width = 53
        Height = 16
        DataField = 'BAIRRO'
        DataSource = CONSCLIENTES.DataSource1
      end
      object RLDBText8: TRLDBText
        Left = 600
        Top = 8
        Width = 21
        Height = 16
        DataField = 'UF'
        DataSource = CONSCLIENTES.DataSource1
      end
      object RLDBText9: TRLDBText
        Left = 632
        Top = 8
        Width = 31
        Height = 16
        DataField = 'CEP'
        DataSource = CONSCLIENTES.DataSource1
      end
    end
  end
  object RLXLSFilter1: TRLXLSFilter
    DisplayName = 'Planilha Excel'
    Left = 96
    Top = 16
  end
  object RLPreviewSetup1: TRLPreviewSetup
    Left = 469
    Top = 16
  end
end
