object Form4: TForm4
  Left = -14
  Top = 91
  Width = 1036
  Height = 613
  Caption = 'Form4'
  Color = clBtnFace
  Font.Charset = DEFAULT_CHARSET
  Font.Color = clWindowText
  Font.Height = -11
  Font.Name = 'MS Sans Serif'
  Font.Style = []
  OldCreateOrder = False
  PixelsPerInch = 96
  TextHeight = 13
  object DBGrid1: TDBGrid
    Left = 0
    Top = 97
    Width = 1028
    Height = 482
    Align = alClient
    DataSource = DataSource1
    TabOrder = 0
    TitleFont.Charset = DEFAULT_CHARSET
    TitleFont.Color = clWindowText
    TitleFont.Height = -11
    TitleFont.Name = 'MS Sans Serif'
    TitleFont.Style = []
    OnDblClick = DBGrid1DblClick
  end
  object Panel1: TPanel
    Left = 0
    Top = 0
    Width = 1028
    Height = 97
    Align = alTop
    TabOrder = 1
    object SpeedButton1: TSpeedButton
      Left = 552
      Top = 64
      Width = 153
      Height = 22
      Caption = '&BUSCAR'
      OnClick = SpeedButton1Click
    end
    object GroupBox1: TGroupBox
      Left = 40
      Top = 8
      Width = 225
      Height = 83
      Caption = 'OPERA'#199#195'O'
      TabOrder = 0
      object RadioButton1: TRadioButton
        Left = 24
        Top = 16
        Width = 113
        Height = 17
        Caption = 'TITULOS PAGAR'
        Checked = True
        TabOrder = 0
        TabStop = True
        OnClick = RadioButton1Click
      end
      object RadioButton2: TRadioButton
        Left = 24
        Top = 40
        Width = 129
        Height = 17
        Caption = 'TITULOS RECEBER'
        TabOrder = 1
        OnClick = RadioButton2Click
      end
      object RadioButton6: TRadioButton
        Left = 24
        Top = 60
        Width = 113
        Height = 19
        Caption = 'AMBOS'
        TabOrder = 2
      end
    end
    object GroupBox2: TGroupBox
      Left = 272
      Top = 11
      Width = 265
      Height = 80
      Caption = 'FILTRO'
      TabOrder = 1
      object RadioButton3: TRadioButton
        Left = 24
        Top = 16
        Width = 113
        Height = 17
        Caption = 'PARA PAGAR'
        Checked = True
        TabOrder = 0
        TabStop = True
      end
      object RadioButton4: TRadioButton
        Left = 24
        Top = 36
        Width = 113
        Height = 17
        Caption = 'PAGOS'
        TabOrder = 1
      end
      object RadioButton5: TRadioButton
        Left = 24
        Top = 56
        Width = 113
        Height = 17
        Caption = 'AMBOS'
        TabOrder = 2
      end
    end
  end
  object IBQuery1: TIBQuery
    Database = Form1.IBDatabase1
    Transaction = Form1.IBTransaction1
    BufferChunks = 1000
    CachedUpdates = False
    SQL.Strings = (
      'select *from titulospagar2 where status= '#39'A'#39)
    Left = 652
    Top = 10
  end
  object DataSource1: TDataSource
    DataSet = IBQuery1
    Left = 620
    Top = 8
  end
end
