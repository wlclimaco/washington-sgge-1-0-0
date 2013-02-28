object Form99: TForm99
  Left = 223
  Top = 191
  Width = 788
  Height = 478
  Caption = 'Form99'
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
    DataSource = Form98.DataSource1
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
        Left = 277
        Top = 13
        Width = 163
        Height = 16
        Align = faCenter
        Caption = 'ENTRADA DE PRODUTOS'
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
        Width = 78
        Height = 16
        Caption = 'Numero nota'
      end
      object RLLabel3: TRLLabel
        Left = 96
        Top = 16
        Width = 34
        Height = 16
        Caption = 'Serie'
      end
      object RLLabel4: TRLLabel
        Left = 152
        Top = 16
        Width = 33
        Height = 16
        Caption = 'TIPO'
      end
      object RLLabel5: TRLLabel
        Left = 192
        Top = 16
        Width = 85
        Height = 16
        Caption = 'DT ENTRADA'
      end
      object RLLabel6: TRLLabel
        Left = 280
        Top = 16
        Width = 63
        Height = 16
        Caption = 'CD PROD'
      end
      object RLLabel7: TRLLabel
        Left = 352
        Top = 16
        Width = 30
        Height = 16
        Caption = 'REF'
      end
      object RLLabel8: TRLLabel
        Left = 432
        Top = 16
        Width = 67
        Height = 16
        Caption = 'PRODUTO'
      end
      object RLLabel9: TRLLabel
        Left = 624
        Top = 16
        Width = 87
        Height = 16
        Caption = 'QUANTIDADE'
      end
    end
    object RLBand3: TRLBand
      Left = 38
      Top = 113
      Width = 718
      Height = 24
      object RLDBText1: TRLDBText
        Left = 16
        Top = 8
        Width = 63
        Height = 16
        DataField = 'Dcnumero'
        DataSource = Form98.DataSource1
      end
      object RLDBText2: TRLDBText
        Left = 96
        Top = 8
        Width = 48
        Height = 16
        DataField = 'Dcserie'
        DataSource = Form98.DataSource1
      end
      object RLDBText3: TRLDBText
        Left = 144
        Top = 8
        Width = 41
        Height = 16
        DataField = 'Dctipo'
        DataSource = Form98.DataSource1
      end
      object RLDBText4: TRLDBText
        Left = 192
        Top = 8
        Width = 60
        Height = 16
        DataField = 'Dtentrada'
        DataSource = Form98.DataSource1
      end
      object RLDBText5: TRLDBText
        Left = 280
        Top = 8
        Width = 63
        Height = 16
        DataField = 'Cdproduto'
        DataSource = Form98.DataSource1
      end
      object RLDBText6: TRLDBText
        Left = 432
        Top = 8
        Width = 67
        Height = 16
        DataField = 'PRODUTO'
        DataSource = Form98.DataSource1
      end
      object RLDBText7: TRLDBText
        Left = 352
        Top = 8
        Width = 30
        Height = 16
        DataField = 'REF'
        DataSource = Form98.DataSource1
      end
      object RLDBText8: TRLDBText
        Left = 624
        Top = 8
        Width = 70
        Height = 16
        DataField = 'Quantidade'
        DataSource = Form98.DataSource1
      end
    end
    object RLBand4: TRLBand
      Left = 38
      Top = 137
      Width = 718
      Height = 32
      BandType = btColumnFooter
      object RLSystemInfo1: TRLSystemInfo
        Left = 16
        Top = 13
        Width = 37
        Height = 16
        Info = itNow
      end
      object RLSystemInfo2: TRLSystemInfo
        Left = 296
        Top = 8
        Width = 195
        Height = 16
        Info = itPagePreview
      end
    end
  end
end
