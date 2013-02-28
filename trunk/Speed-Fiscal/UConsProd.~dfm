object Form15: TForm15
  Left = 207
  Top = 143
  Width = 783
  Height = 540
  Caption = 'Form15'
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
      object CheckBox1: TCheckBox
        Left = 49
        Top = 16
        Width = 97
        Height = 17
        Caption = 'COD PRODUTO'
        Checked = True
        State = cbChecked
        TabOrder = 0
      end
      object CheckBox2: TCheckBox
        Left = 157
        Top = 14
        Width = 97
        Height = 17
        Caption = 'REF'
        TabOrder = 1
      end
      object CheckBox3: TCheckBox
        Left = 253
        Top = 14
        Width = 97
        Height = 17
        Caption = 'DESCRI'#199#195'O'
        TabOrder = 2
      end
      object CheckBox4: TCheckBox
        Left = 347
        Top = 12
        Width = 97
        Height = 17
        Caption = 'DERIVA'#199#195'O'
        TabOrder = 3
      end
      object CheckBox5: TCheckBox
        Left = 447
        Top = 12
        Width = 97
        Height = 17
        Caption = 'NCM'
        TabOrder = 4
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
        Left = 50
        Top = 16
        Width = 113
        Height = 17
        Caption = 'COD PRODUTO'
        TabOrder = 0
      end
      object RadioButton2: TRadioButton
        Left = 156
        Top = 18
        Width = 113
        Height = 17
        Caption = 'REF'
        TabOrder = 1
      end
      object RadioButton3: TRadioButton
        Left = 221
        Top = 17
        Width = 113
        Height = 17
        Caption = 'DESCRI'#199#195'O'
        TabOrder = 2
      end
      object RadioButton4: TRadioButton
        Left = 347
        Top = 18
        Width = 113
        Height = 17
        Caption = 'DERIVA'#199#195'O'
        TabOrder = 3
      end
      object RadioButton5: TRadioButton
        Left = 449
        Top = 16
        Width = 113
        Height = 17
        Caption = 'NCM'
        TabOrder = 4
      end
      object RadioButton6: TRadioButton
        Left = 509
        Top = 19
        Width = 113
        Height = 17
        Caption = 'TUDO'
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
