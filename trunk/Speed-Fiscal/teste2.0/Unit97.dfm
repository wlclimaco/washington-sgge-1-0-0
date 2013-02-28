object Form97: TForm97
  Left = 215
  Top = 154
  Width = 783
  Height = 540
  Caption = 'Form97'
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
    Font.Charset = DEFAULT_CHARSET
    Font.Color = clBlack
    Font.Height = -13
    Font.Name = 'Arial'
    Font.Style = []
    ExpressionParser = RLExpressionParser1
    object RLBand1: TRLBand
      Left = 38
      Top = 38
      Width = 718
      Height = 43
      BandType = btHeader
      object RLLabel1: TRLLabel
        Left = 168
        Top = 0
        Width = 381
        Height = 32
        Align = faCenterTop
        Caption = 'Rela'#231#227'o de notas de entrada'
        Font.Charset = DEFAULT_CHARSET
        Font.Color = clBlack
        Font.Height = -27
        Font.Name = 'Arial'
        Font.Style = [fsBold]
        ParentFont = False
      end
    end
    object RLBand2: TRLBand
      Left = 38
      Top = 81
      Width = 718
      Height = 32
      BandType = btTitle
      object RLLabel2: TRLLabel
        Left = 16
        Top = 16
        Width = 54
        Height = 16
        Caption = 'n'#186' Nota f'
      end
      object RLLabel3: TRLLabel
        Left = 88
        Top = 16
        Width = 34
        Height = 16
        Caption = 'Serie'
      end
      object RLLabel4: TRLLabel
        Left = 144
        Top = 16
        Width = 43
        Height = 16
        Caption = 'Ordem'
      end
      object RLLabel5: TRLLabel
        Left = 208
        Top = 16
        Width = 28
        Height = 16
        Caption = 'Tipo'
      end
      object RLLabel6: TRLLabel
        Left = 272
        Top = 16
        Width = 85
        Height = 16
        Caption = 'Data entradas'
      end
      object RLLabel7: TRLLabel
        Left = 360
        Top = 16
        Width = 40
        Height = 16
        Caption = 'CFOP'
      end
      object RLLabel8: TRLLabel
        Left = 416
        Top = 16
        Width = 69
        Height = 16
        Caption = 'Fornecedor'
      end
      object RLLabel9: TRLLabel
        Left = 504
        Top = 16
        Width = 65
        Height = 16
        Caption = 'Valor Nota'
      end
      object RLLabel10: TRLLabel
        Left = 568
        Top = 16
        Width = 52
        Height = 16
        Caption = 'Vl ICMS'
      end
      object RLLabel11: TRLLabel
        Left = 624
        Top = 16
        Width = 35
        Height = 16
        Caption = 'Vl IPI'
      end
      object RLLabel12: TRLLabel
        Left = 672
        Top = 16
        Width = 36
        Height = 16
        Caption = 'Vl ST'
      end
    end
    object RLBand3: TRLBand
      Left = 38
      Top = 113
      Width = 718
      Height = 32
      object RLDBText1: TRLDBText
        Left = 16
        Top = 8
        Width = 63
        Height = 16
        DataField = 'Dcnumero'
        DataSource = Form96.DataSource1
      end
      object RLDBText2: TRLDBText
        Left = 88
        Top = 8
        Width = 48
        Height = 16
        DataField = 'Dcserie'
        DataSource = Form96.DataSource1
      end
      object RLDBText3: TRLDBText
        Left = 144
        Top = 8
        Width = 56
        Height = 16
        DataField = 'Dcordem'
        DataSource = Form96.DataSource1
      end
      object RLDBText4: TRLDBText
        Left = 208
        Top = 8
        Width = 41
        Height = 16
        DataField = 'Dctipo'
        DataSource = Form96.DataSource1
      end
      object RLDBText5: TRLDBText
        Left = 272
        Top = 8
        Width = 60
        Height = 16
        DataField = 'Dtentrada'
        DataSource = Form96.DataSource1
      end
      object RLDBText6: TRLDBText
        Left = 360
        Top = 8
        Width = 30
        Height = 16
        DataField = 'Cfop'
        DataSource = Form96.DataSource1
      end
      object RLDBText7: TRLDBText
        Left = 416
        Top = 8
        Width = 80
        Height = 16
        DataField = 'Cdfornecedor'
        DataSource = Form96.DataSource1
      end
      object RLDBText8: TRLDBText
        Left = 504
        Top = 8
        Width = 41
        Height = 16
        DataField = 'Vlnota'
        DataSource = Form96.DataSource1
      end
      object RLDBText9: TRLDBText
        Left = 568
        Top = 8
        Width = 44
        Height = 16
        DataField = 'Vlicms'
        DataSource = Form96.DataSource1
      end
      object RLDBText10: TRLDBText
        Left = 624
        Top = 8
        Width = 29
        Height = 16
        DataField = 'Vlipi'
        DataSource = Form96.DataSource1
      end
      object RLDBText11: TRLDBText
        Left = 672
        Top = 8
        Width = 27
        Height = 16
        DataField = 'Vlst'
        DataSource = Form96.DataSource1
      end
    end
    object RLBand4: TRLBand
      Left = 38
      Top = 145
      Width = 718
      Height = 32
      BandType = btColumnFooter
      object RLSystemInfo1: TRLSystemInfo
        Left = 16
        Top = 8
        Width = 37
        Height = 16
        Info = itNow
      end
      object RLSystemInfo2: TRLSystemInfo
        Left = 328
        Top = 8
        Width = 87
        Height = 16
        Info = itPageNumber
      end
      object RLDBResult1: TRLDBResult
        Left = 504
        Top = 8
        Width = 80
        Height = 16
        DataField = 'Vlnota'
        DataSource = Form96.DataSource1
        Info = riSum
      end
    end
  end
  object RLExpressionParser1: TRLExpressionParser
    Left = 632
    Top = 16
  end
end
