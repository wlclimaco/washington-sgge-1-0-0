object Form105: TForm105
  Left = 238
  Top = 164
  Width = 783
  Height = 540
  Caption = 'Form105'
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
    DataSource = Form106.DataSource2
    Font.Charset = DEFAULT_CHARSET
    Font.Color = clBlack
    Font.Height = -13
    Font.Name = 'Arial'
    Font.Style = []
    object RLBand1: TRLBand
      Left = 38
      Top = 78
      Width = 718
      Height = 35
      BandType = btTitle
    end
    object RLBand2: TRLBand
      Left = 38
      Top = 38
      Width = 718
      Height = 40
      BandType = btHeader
    end
    object RLBand4: TRLBand
      Left = 38
      Top = 193
      Width = 718
      Height = 40
      BandType = btColumnFooter
    end
    object RLSubDetail1: TRLSubDetail
      Left = 38
      Top = 113
      Width = 718
      Height = 80
      DataSource = Form106.DataSource1
      object RLBand3: TRLBand
        Left = 0
        Top = 0
        Width = 718
        Height = 33
        Computable = False
        object RLDBText1: TRLDBText
          Left = 24
          Top = 8
          Width = 93
          Height = 16
          DataField = 'Datamovimento'
          DataSource = Form106.DataSource2
        end
      end
      object RLDBText2: TRLDBText
        Left = 32
        Top = 48
        Width = 14
        Height = 16
        DataField = 'Id'
        DataSource = Form106.DataSource1
      end
    end
  end
end
