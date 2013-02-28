object Form93: TForm93
  Left = 221
  Top = 133
  Width = 783
  Height = 540
  Caption = 'Form93'
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
    Top = -8
    Width = 794
    Height = 1123
    DataSource = Form842.DataSource2
    Font.Charset = DEFAULT_CHARSET
    Font.Color = clBlack
    Font.Height = -13
    Font.Name = 'Arial'
    Font.Style = []
    Margins.LeftMargin = 2.000000000000000000
    Margins.TopMargin = 2.000000000000000000
    Margins.RightMargin = 2.000000000000000000
    Margins.BottomMargin = 2.000000000000000000
    ExpressionParser = RLExpressionParser1
    object RLBand1: TRLBand
      Left = 8
      Top = 8
      Width = 778
      Height = 40
      BandType = btHeader
      object RLLabel14: TRLLabel
        Left = 130
        Top = 4
        Width = 518
        Height = 32
        Align = faCenter
        Caption = 'RELAT'#211'RIO DE SAIDA DE PRODUTOS'
        Font.Charset = DEFAULT_CHARSET
        Font.Color = clBlack
        Font.Height = -27
        Font.Name = 'Arial'
        Font.Style = [fsBold]
        ParentFont = False
      end
    end
    object RLBand2: TRLBand
      Left = 8
      Top = 75
      Width = 778
      Height = 32
      object RLDBText1: TRLDBText
        Left = 88
        Top = 8
        Width = 45
        Height = 16
        DataField = 'Codpro'
        DataSource = Form842.DataSource2
      end
      object RLDBText2: TRLDBText
        Left = 144
        Top = 8
        Width = 47
        Height = 16
        DataField = 'produto'
        DataSource = Form842.DataSource2
      end
      object RLDBText3: TRLDBText
        Left = 384
        Top = 8
        Width = 67
        Height = 16
        DataField = 'quantidade'
        DataSource = Form842.DataSource2
      end
      object rldbtxtvlproduto: TRLDBText
        Left = 424
        Top = 8
        Width = 55
        Height = 16
        DataField = 'vlproduto'
        DataSource = Form842.DataSource2
      end
      object RLDBText4: TRLDBText
        Left = 288
        Top = 8
        Width = 63
        Height = 16
        DataField = 'Cdproduto'
        DataSource = Form842.DataSource2
      end
      object RLDBText5: TRLDBText
        Left = 536
        Top = 8
        Width = 95
        Height = 16
        DataField = 'PRECOVENDA'
        DataSource = Form842.DataSource2
      end
      object RLDBText6: TRLDBText
        Left = 592
        Top = 8
        Width = 44
        Height = 16
        DataField = 'Vlicms'
        DataSource = Form842.DataSource2
      end
      object RLDBText7: TRLDBText
        Left = 632
        Top = 8
        Width = 44
        Height = 16
        DataField = 'AQUIS'
        DataSource = Form842.DataSource2
      end
      object RLDBText8: TRLDBText
        Left = 680
        Top = 8
        Width = 48
        Height = 16
        DataField = 'CUSTO'
        DataSource = Form842.DataSource2
      end
      object rldbtxtDTMOVIMENTO: TRLDBText
        Left = 8
        Top = 8
        Width = 99
        Height = 16
        DataField = 'DTMOVIMENTO'
        DataSource = Form842.DataSource2
      end
      object RLDBText9: TRLDBText
        Left = 736
        Top = 8
        Width = 67
        Height = 16
        DataField = 'ESTOQUE'
        DataSource = Form842.DataSource2
      end
      object RLDBResult3: TRLDBResult
        Left = 488
        Top = 8
        Width = 182
        Height = 16
        DataFormula = 'quantidade  * vlproduto'
        DataSource = Form842.DataSource2
        HoldStyle = hsCopyHeight
        Info = riSum
        ResetAfterPrint = True
      end
    end
    object RLBand3: TRLBand
      Left = 8
      Top = 107
      Width = 778
      Height = 32
      BandType = btColumnFooter
      object RLSystemInfo1: TRLSystemInfo
        Left = 32
        Top = 8
        Width = 37
        Height = 16
        Info = itNow
      end
      object RLDBResult1: TRLDBResult
        Left = 488
        Top = 8
        Width = 170
        Height = 16
        DataFormula = 'quantidade*vlproduto'
        DataSource = Form842.DataSource2
        Info = riSum
      end
      object RLLabel13: TRLLabel
        Left = 384
        Top = 8
        Width = 88
        Height = 16
        Caption = 'Total Vendas :'
      end
    end
    object RLBand4: TRLBand
      Left = 8
      Top = 48
      Width = 778
      Height = 27
      BandType = btTitle
      object RLLabel1: TRLLabel
        Left = 0
        Top = 8
        Width = 68
        Height = 16
        Caption = 'Data venda'
      end
      object RLLabel2: TRLLabel
        Left = 88
        Top = 8
        Width = 47
        Height = 16
        Caption = 'cod pro'
      end
      object RLLabel3: TRLLabel
        Left = 152
        Top = 8
        Width = 109
        Height = 16
        Caption = 'Descri'#231#227'o produto'
      end
      object RLLabel4: TRLLabel
        Left = 288
        Top = 8
        Width = 30
        Height = 16
        Caption = 'REF'
      end
      object RLLabel5: TRLLabel
        Left = 384
        Top = 8
        Width = 39
        Height = 16
        Caption = 'Quant'
      end
      object RLLabel6: TRLLabel
        Left = 424
        Top = 8
        Width = 59
        Height = 16
        Caption = 'vl produto'
      end
      object RLLabel7: TRLLabel
        Left = 536
        Top = 8
        Width = 54
        Height = 16
        Caption = 'P Venda'
      end
      object RLLabel8: TRLLabel
        Left = 592
        Top = 8
        Width = 36
        Height = 16
        Caption = 'ICMS'
      end
      object RLLabel9: TRLLabel
        Left = 632
        Top = 8
        Width = 44
        Height = 16
        Caption = 'AQUIS'
      end
      object RLLabel10: TRLLabel
        Left = 680
        Top = 8
        Width = 38
        Height = 16
        Caption = 'Custo'
      end
      object RLLabel11: TRLLabel
        Left = 736
        Top = 8
        Width = 38
        Height = 16
        Caption = 'Estoq'
      end
      object RLLabel12: TRLLabel
        Left = 488
        Top = 8
        Width = 38
        Height = 16
        Caption = 'Soma'
      end
    end
  end
  object RLExpressionParser1: TRLExpressionParser
    Left = 568
    Top = 16
  end
end
