object Form109: TForm109
  Left = 221
  Top = 184
  Width = 783
  Height = 540
  VertScrollBar.Position = 54
  Caption = 'Form109'
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
    Top = -54
    Width = 794
    Height = 1123
    DataSource = Form108.DataSource1
    Font.Charset = DEFAULT_CHARSET
    Font.Color = clBlack
    Font.Height = -13
    Font.Name = 'Arial'
    Font.Style = []
    object RLBand2: TRLBand
      Left = 38
      Top = 38
      Width = 718
      Height = 32
      BandType = btHeader
      object RLLabel1: TRLLabel
        Left = 116
        Top = 0
        Width = 485
        Height = 29
        Align = faCenterTop
        Caption = 'RELAT'#211'RIO VENDAS POR VENDEDORAS'
        Font.Charset = DEFAULT_CHARSET
        Font.Color = clBlack
        Font.Height = -24
        Font.Name = 'Arial'
        Font.Style = [fsBold]
        ParentFont = False
      end
    end
    object RLBand3: TRLBand
      Left = 38
      Top = 70
      Width = 718
      Height = 26
      BandType = btColumnHeader
      object RLLabel2: TRLLabel
        Left = 40
        Top = 8
        Width = 38
        Height = 16
        Caption = 'DATA'
      end
      object RLLabel3: TRLLabel
        Left = 112
        Top = 8
        Width = 55
        Height = 16
        Caption = 'CODIGO'
      end
      object RLLabel4: TRLLabel
        Left = 192
        Top = 8
        Width = 47
        Height = 16
        Caption = 'NOME '
      end
      object RLLabel5: TRLLabel
        Left = 480
        Top = 8
        Width = 118
        Height = 16
        Caption = 'vALOR VENDA DIA'
      end
    end
    object RLBand4: TRLBand
      Left = 38
      Top = 96
      Width = 718
      Height = 32
      object RLDBText1: TRLDBText
        Left = 40
        Top = 8
        Width = 29
        Height = 16
        DataField = 'data'
        DataSource = Form108.DataSource1
      end
      object RLDBText2: TRLDBText
        Left = 111
        Top = 8
        Width = 68
        Height = 16
        DataField = 'CODVEND'
        DataSource = Form108.DataSource1
      end
      object RLDBText3: TRLDBText
        Left = 192
        Top = 8
        Width = 36
        Height = 16
        DataField = 'nome'
        DataSource = Form108.DataSource1
      end
      object RLDBText4: TRLDBText
        Left = 480
        Top = 8
        Width = 29
        Height = 16
        DataField = 'total'
        DataSource = Form108.DataSource1
      end
    end
    object RLBand5: TRLBand
      Left = 38
      Top = 128
      Width = 718
      Height = 25
      BandType = btColumnFooter
      object RLSystemInfo1: TRLSystemInfo
        Left = 304
        Top = 8
        Width = 87
        Height = 16
        Info = itPageNumber
      end
      object RLSystemInfo2: TRLSystemInfo
        Left = 21
        Top = 3
        Width = 37
        Height = 16
        Info = itNow
      end
    end
  end
end
