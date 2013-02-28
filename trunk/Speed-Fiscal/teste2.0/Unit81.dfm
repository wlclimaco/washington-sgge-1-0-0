object Form81: TForm81
  Left = 213
  Top = 153
  Width = 823
  Height = 480
  Caption = 'Form81'
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
    DataSource = DataModule2.dsQUERY9
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
      object RLLabel1: TRLLabel
        Left = 216
        Top = 8
        Width = 194
        Height = 16
        Caption = 'rELA'#199#195'O DE TITULOS PAGOS '
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
        Width = 61
        Height = 16
        Caption = 'NUMERO'
      end
      object RLLabel3: TRLLabel
        Left = 96
        Top = 16
        Width = 52
        Height = 16
        Caption = 'ORDEM'
      end
      object RLLabel4: TRLLabel
        Left = 168
        Top = 16
        Width = 43
        Height = 16
        Caption = 'SERIE'
      end
      object RLLabel5: TRLLabel
        Left = 240
        Top = 16
        Width = 33
        Height = 16
        Caption = 'TIPO'
      end
      object RLLabel6: TRLLabel
        Left = 392
        Top = 16
        Width = 113
        Height = 16
        Caption = 'DT LANCAMENTO'
      end
      object RLLabel7: TRLLabel
        Left = 488
        Top = 16
        Width = 99
        Height = 16
        Caption = 'DT VENCIMENT'
      end
      object RLLabel8: TRLLabel
        Left = 592
        Top = 16
        Width = 61
        Height = 16
        Caption = 'DT PAGA'
      end
      object RLLabel9: TRLLabel
        Left = 664
        Top = 16
        Width = 48
        Height = 16
        Caption = 'VALOR'
      end
      object RLLabel10: TRLLabel
        Left = 312
        Top = 16
        Width = 65
        Height = 16
        Caption = 'PARCELA'
      end
    end
    object RLBand3: TRLBand
      Left = 38
      Top = 105
      Width = 718
      Height = 32
      object RLDBText1: TRLDBText
        Left = 24
        Top = 16
        Width = 63
        Height = 16
        DataField = 'Dcnumero'
        DataSource = DataModule2.dstitulospagar
      end
      object RLDBText2: TRLDBText
        Left = 96
        Top = 16
        Width = 56
        Height = 16
        DataField = 'Dcordem'
        DataSource = DataModule2.dstitulospagar
      end
      object RLDBText3: TRLDBText
        Left = 168
        Top = 16
        Width = 48
        Height = 16
        DataField = 'Dcserie'
        DataSource = DataModule2.dstitulospagar
      end
      object RLDBText4: TRLDBText
        Left = 240
        Top = 16
        Width = 41
        Height = 16
        DataField = 'Dctipo'
        DataSource = DataModule2.dstitulospagar
      end
      object RLDBText5: TRLDBText
        Left = 392
        Top = 16
        Width = 84
        Height = 16
        DataField = 'Dtlancamento'
        DataSource = DataModule2.dstitulospagar
      end
      object RLDBText6: TRLDBText
        Left = 488
        Top = 16
        Width = 82
        Height = 16
        DataField = 'Dtvencimento'
        DataSource = DataModule2.dstitulospagar
      end
      object RLDBText7: TRLDBText
        Left = 600
        Top = 16
        Width = 81
        Height = 16
        DataField = 'Dtpagamento'
        DataSource = DataModule2.dstitulospagar
      end
      object RLDBText8: TRLDBText
        Left = 664
        Top = 16
        Width = 58
        Height = 16
        DataField = 'Vlparcela'
        DataSource = DataModule2.dstitulospagar
      end
      object RLDBText9: TRLDBText
        Left = 312
        Top = 16
        Width = 48
        Height = 16
        DataField = 'Parcela'
        DataSource = DataModule2.dstitulospagar
      end
    end
    object RLBand4: TRLBand
      Left = 38
      Top = 137
      Width = 718
      Height = 32
      BandType = btSummary
      object RLSystemInfo1: TRLSystemInfo
        Left = 304
        Top = 8
        Width = 39
        Height = 16
      end
    end
  end
end
