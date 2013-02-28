object Form112: TForm112
  Left = 224
  Top = 170
  Width = 783
  Height = 540
  Caption = 'Form112'
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
    DataSource = Form111.DataSource1
    Font.Charset = DEFAULT_CHARSET
    Font.Color = clBlack
    Font.Height = -13
    Font.Name = 'Arial'
    Font.Style = []
    ExpressionParser = RLExpressionParser1
    object RLBand1: TRLBand
      Left = 38
      Top = 65
      Width = 718
      Height = 24
      BandType = btColumnHeader
      object RLLabel1: TRLLabel
        Left = 42
        Top = 0
        Width = 31
        Height = 16
        Caption = 'Data'
      end
      object RLLabel2: TRLLabel
        Left = 152
        Top = 0
        Width = 130
        Height = 16
        Caption = 'MOVIMENTO DIARIO'
      end
      object RLLabel3: TRLLabel
        Left = 304
        Top = 0
        Width = 79
        Height = 16
        Caption = 'VENDA ECF'
      end
      object RLLabel4: TRLLabel
        Left = 512
        Top = 0
        Width = 78
        Height = 16
        Caption = 'DIFEREN'#199#195
      end
    end
    object RLBand3: TRLBand
      Left = 38
      Top = 89
      Width = 718
      Height = 24
      object RLDBText1: TRLDBText
        Left = 40
        Top = 8
        Width = 91
        Height = 16
        DataField = 'datamovimento'
        DataSource = Form111.DataSource1
      end
      object RLDBText2: TRLDBText
        Left = 152
        Top = 8
        Width = 29
        Height = 16
        DataField = 'total'
        DataSource = Form111.DataSource1
      end
      object RLDBText3: TRLDBText
        Left = 304
        Top = 8
        Width = 66
        Height = 16
        DataField = 'vendabruta'
        DataSource = Form111.DataSource1
      end
      object RLDBText4: TRLDBText
        Left = 512
        Top = 8
        Width = 155
        Height = 16
        DataField = 'SUM OF  total - vendabrut'
        DataSource = Form111.DataSource1
      end
    end
    object RLBand4: TRLBand
      Left = 38
      Top = 38
      Width = 718
      Height = 27
      BandType = btHeader
      object RLLabel5: TRLLabel
        Left = 202
        Top = 0
        Width = 313
        Height = 22
        Align = faCenterTop
        Caption = 'RELAT'#211'RIO APURA'#199#195'O VENDAS'
        Font.Charset = DEFAULT_CHARSET
        Font.Color = clBlack
        Font.Height = -19
        Font.Name = 'Arial'
        Font.Style = [fsBold]
        ParentFont = False
      end
    end
    object RLBand5: TRLBand
      Left = 38
      Top = 113
      Width = 718
      Height = 29
      BandType = btColumnFooter
      object RLDBResult1: TRLDBResult
        Left = 152
        Top = 8
        Width = 68
        Height = 16
        DataField = 'total'
        DataSource = Form111.DataSource1
        Info = riSum
      end
      object RLDBResult2: TRLDBResult
        Left = 304
        Top = 8
        Width = 105
        Height = 16
        DataField = 'vendabruta'
        DataSource = Form111.DataSource1
        Info = riSum
      end
      object RLDBResult3: TRLDBResult
        Left = 512
        Top = 8
        Width = 194
        Height = 16
        DataField = 'SUM OF  total - vendabrut'
        DataSource = Form111.DataSource1
        Info = riSum
      end
    end
  end
  object RLExpressionParser1: TRLExpressionParser
    Left = 616
    Top = 16
  end
end
