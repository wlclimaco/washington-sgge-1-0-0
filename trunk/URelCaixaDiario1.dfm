object RelCaxDIa: TRelCaxDIa
  Left = 192
  Top = 114
  Width = 696
  Height = 480
  Caption = 'RelCaxDIa'
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
    Left = -8
    Top = -72
    Width = 794
    Height = 1123
    DataSource = RelMovDiario.DataSource1
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
        Left = 256
        Top = 16
        Width = 207
        Height = 16
        Caption = 'RELATORIO MOVIMENTO DIARIO'
      end
    end
    object RLBand2: TRLBand
      Left = 38
      Top = 81
      Width = 718
      Height = 32
      BandType = btColumnHeader
      object RLLabel2: TRLLabel
        Left = 24
        Top = 8
        Width = 16
        Height = 16
        Caption = 'ID'
      end
      object RLLabel3: TRLLabel
        Left = 64
        Top = 8
        Width = 121
        Height = 16
        Caption = 'DATA MOVIMENTO'
      end
      object RLLabel4: TRLLabel
        Left = 200
        Top = 8
        Width = 65
        Height = 16
        Caption = 'DINHEIRO'
      end
      object RLLabel5: TRLLabel
        Left = 272
        Top = 8
        Width = 70
        Height = 16
        Caption = 'CARTAO C'
      end
      object RLLabel6: TRLLabel
        Left = 344
        Top = 8
        Width = 70
        Height = 16
        Caption = 'CARTAO D'
      end
      object RLLabel7: TRLLabel
        Left = 424
        Top = 8
        Width = 95
        Height = 16
        Caption = 'PROMISSORIA'
      end
      object RLLabel8: TRLLabel
        Left = 528
        Top = 8
        Width = 59
        Height = 16
        Caption = 'CHEQUE'
      end
      object RLLabel9: TRLLabel
        Left = 592
        Top = 8
        Width = 44
        Height = 16
        Caption = 'TOTAL'
      end
    end
    object RLBand3: TRLBand
      Left = 38
      Top = 113
      Width = 718
      Height = 24
      object RLDBText1: TRLDBText
        Left = 11
        Top = 6
        Width = 16
        Height = 16
        DataField = 'ID'
        DataSource = RelMovDiario.DataSource1
      end
      object RLDBText2: TRLDBText
        Left = 67
        Top = 4
        Width = 117
        Height = 16
        DataField = 'DATAMOVIMENTO'
        DataSource = RelMovDiario.DataSource1
      end
      object RLDBText3: TRLDBText
        Left = 201
        Top = 4
        Width = 65
        Height = 16
        DataField = 'DINHEIRO'
        DataSource = RelMovDiario.DataSource1
      end
      object RLDBText4: TRLDBText
        Left = 277
        Top = 4
        Width = 120
        Height = 16
        DataField = 'CARTAO_CREDITO'
        DataSource = RelMovDiario.DataSource1
      end
      object RLDBText5: TRLDBText
        Left = 350
        Top = 3
        Width = 111
        Height = 16
        DataField = 'CARTAO_DEBITO'
        DataSource = RelMovDiario.DataSource1
      end
      object RLDBText6: TRLDBText
        Left = 427
        Top = 3
        Width = 95
        Height = 16
        DataField = 'PROMISSORIA'
        DataSource = RelMovDiario.DataSource1
      end
      object RLDBText7: TRLDBText
        Left = 531
        Top = 3
        Width = 59
        Height = 16
        DataField = 'CHEQUE'
        DataSource = RelMovDiario.DataSource1
      end
      object RLDBText8: TRLDBText
        Left = 594
        Top = 0
        Width = 44
        Height = 16
        DataField = 'TOTAL'
        DataSource = RelMovDiario.DataSource1
      end
    end
    object RLBand4: TRLBand
      Left = 38
      Top = 137
      Width = 718
      Height = 24
      BandType = btColumnFooter
      object RLSystemInfo1: TRLSystemInfo
        Left = 289
        Top = 5
        Width = 39
        Height = 16
      end
    end
  end
end
