object FRMCONSCEP: TFRMCONSCEP
  Left = 256
  Top = 140
  Width = 783
  Height = 540
  Caption = 'FRMCONSCEP'
  Color = clBtnFace
  Font.Charset = DEFAULT_CHARSET
  Font.Color = clWindowText
  Font.Height = -11
  Font.Name = 'MS Sans Serif'
  Font.Style = []
  OldCreateOrder = False
  PixelsPerInch = 96
  TextHeight = 13
  object Panel1: TPanel
    Left = 0
    Top = 0
    Width = 775
    Height = 131
    Align = alTop
    TabOrder = 0
    object GroupBox1: TGroupBox
      Left = -1
      Top = 84
      Width = 773
      Height = 44
      Caption = 'Ordenar'
      TabOrder = 0
      object RadioButton7: TRadioButton
        Left = 27
        Top = 16
        Width = 151
        Height = 17
        Caption = 'TIPO LOGRADOURO'
        TabOrder = 0
      end
      object RadioButton8: TRadioButton
        Left = 162
        Top = 16
        Width = 113
        Height = 17
        Caption = 'LOGRADOURO'
        TabOrder = 1
      end
      object RadioButton9: TRadioButton
        Left = 283
        Top = 16
        Width = 113
        Height = 17
        Caption = 'BAIRRO'
        TabOrder = 2
      end
      object RadioButton10: TRadioButton
        Left = 390
        Top = 18
        Width = 113
        Height = 17
        Caption = 'CIDADE'
        TabOrder = 3
      end
      object RadioButton11: TRadioButton
        Left = 508
        Top = 18
        Width = 113
        Height = 17
        Caption = 'UF'
        TabOrder = 4
      end
      object rb1: TRadioButton
        Left = 621
        Top = 18
        Width = 113
        Height = 17
        Caption = 'CEP'
        Checked = True
        TabOrder = 5
        TabStop = True
      end
    end
    object GroupBox2: TGroupBox
      Left = -1
      Top = 43
      Width = 773
      Height = 44
      Caption = 'Filtrar'
      TabOrder = 1
      object RadioButton1: TRadioButton
        Left = 27
        Top = 16
        Width = 151
        Height = 17
        Caption = 'TIPO LOGRADOURO'
        TabOrder = 0
      end
      object RadioButton2: TRadioButton
        Left = 162
        Top = 16
        Width = 113
        Height = 17
        Caption = 'LOGRADOURO'
        TabOrder = 1
      end
      object RadioButton3: TRadioButton
        Left = 283
        Top = 16
        Width = 113
        Height = 17
        Caption = 'BAIRRO'
        TabOrder = 2
      end
      object RadioButton4: TRadioButton
        Left = 390
        Top = 18
        Width = 113
        Height = 17
        Caption = 'CIDADE'
        TabOrder = 3
      end
      object RadioButton5: TRadioButton
        Left = 508
        Top = 18
        Width = 113
        Height = 17
        Caption = 'UF'
        TabOrder = 4
      end
      object RadioButton6: TRadioButton
        Left = 621
        Top = 18
        Width = 113
        Height = 17
        Caption = 'CEP'
        Checked = True
        TabOrder = 5
        TabStop = True
      end
    end
    object GroupBox3: TGroupBox
      Left = -1
      Top = 1
      Width = 773
      Height = 44
      Caption = 'Buscar'
      TabOrder = 2
      object SpeedButton1: TSpeedButton
        Left = 447
        Top = 16
        Width = 73
        Height = 22
        Caption = '&BUSCAR'
        OnClick = SpeedButton1Click
      end
      object Edit1: TEdit
        Left = 207
        Top = 14
        Width = 218
        Height = 21
        TabOrder = 0
      end
    end
  end
  object Panel2: TPanel
    Left = 0
    Top = 131
    Width = 775
    Height = 375
    Align = alClient
    TabOrder = 1
    object DBGrid1: TDBGrid
      Left = 1
      Top = 1
      Width = 773
      Height = 373
      Align = alClient
      DataSource = DataSource1
      TabOrder = 0
      TitleFont.Charset = DEFAULT_CHARSET
      TitleFont.Color = clWindowText
      TitleFont.Height = -11
      TitleFont.Name = 'MS Sans Serif'
      TitleFont.Style = []
      OnDrawColumnCell = DBGrid1DrawColumnCell
      OnDblClick = DBGrid1DblClick
    end
  end
  object IBQuery1: TIBQuery
    Database = FRMCADPROD.IBDatabase1
    Transaction = FRMCADPROD.IBTransaction1
    BufferChunks = 1000
    CachedUpdates = False
    SQL.Strings = (
      '')
    Left = 54
    Top = 10
  end
  object DataSource1: TDataSource
    DataSet = IBQuery1
    Left = 90
    Top = 11
  end
end
