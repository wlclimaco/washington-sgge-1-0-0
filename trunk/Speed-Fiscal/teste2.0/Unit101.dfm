object Form91: TForm91
  Left = 192
  Top = 114
  Width = 696
  Height = 480
  Caption = 'Rel TitulosPagar01'
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
    DataSource = Form88.DataSource1
    Font.Charset = DEFAULT_CHARSET
    Font.Color = clBlack
    Font.Height = -13
    Font.Name = 'Arial'
    Font.Style = []
    object RLBand1: TRLBand
      Left = 38
      Top = 134
      Width = 718
      Height = 27
      BandType = btSummary
    end
    object RLBand2: TRLBand
      Left = 38
      Top = 38
      Width = 718
      Height = 32
      BandType = btHeader
      object RLLabel1: TRLLabel
        Left = 195
        Top = 0
        Width = 327
        Height = 32
        Align = faCenterTop
        Caption = 'Relatorio Titulos pagar  '
        Font.Charset = DEFAULT_CHARSET
        Font.Color = clBlack
        Font.Height = -27
        Font.Name = 'Arial'
        Font.Style = [fsBold]
        ParentFont = False
      end
    end
    object RLBand3: TRLBand
      Left = 38
      Top = 70
      Width = 718
      Height = 32
      BandType = btTitle
      object RLLabel2: TRLLabel
        Left = 24
        Top = 16
        Width = 39
        Height = 16
        Caption = 'N'#186' NF'
      end
      object RLLabel3: TRLLabel
        Left = 88
        Top = 16
        Width = 69
        Height = 16
        Caption = 'Fornecedor'
      end
      object RLLabel4: TRLLabel
        Left = 312
        Top = 16
        Width = 34
        Height = 16
        Caption = 'Valor'
      end
      object RLLabel5: TRLLabel
        Left = 400
        Top = 16
        Width = 100
        Height = 16
        Caption = 'Data vencimento'
      end
      object RLLabel6: TRLLabel
        Left = 488
        Top = 16
        Width = 48
        Height = 16
        Caption = 'Parcela'
      end
      object RLLabel7: TRLLabel
        Left = 552
        Top = 16
        Width = 42
        Height = 16
        Caption = 'Status'
      end
    end
    object RLBand4: TRLBand
      Left = 38
      Top = 102
      Width = 718
      Height = 32
      object RLDBText1: TRLDBText
        Left = 16
        Top = 16
        Width = 63
        Height = 16
        DataField = 'Dcnumero'
        DataSource = Form88.DataSource1
      end
      object RLDBText2: TRLDBText
        Left = 88
        Top = 16
        Width = 69
        Height = 16
        DataField = 'Fornecedor'
        DataSource = Form88.DataSource1
      end
      object RLDBText3: TRLDBText
        Left = 312
        Top = 16
        Width = 58
        Height = 16
        DataField = 'Vlparcela'
        DataSource = Form88.DataSource1
      end
      object RLDBText4: TRLDBText
        Left = 400
        Top = 16
        Width = 82
        Height = 16
        DataField = 'Dtvencimento'
        DataSource = Form88.DataSource1
      end
      object RLDBText5: TRLDBText
        Left = 488
        Top = 16
        Width = 48
        Height = 16
        DataField = 'Parcela'
        DataSource = Form88.DataSource1
      end
      object RLDBText6: TRLDBText
        Left = 552
        Top = 16
        Width = 42
        Height = 16
        DataField = 'Status'
        DataSource = Form88.DataSource1
      end
    end
  end
end
