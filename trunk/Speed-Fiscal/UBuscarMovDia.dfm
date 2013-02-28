object BusMovDiario: TBusMovDiario
  Left = 192
  Top = 114
  Width = 696
  Height = 480
  Caption = 'Buscar Mov Diario'
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
    Width = 688
    Height = 65
    Align = alTop
    TabOrder = 0
    object GroupBox3: TGroupBox
      Left = -1
      Top = 1
      Width = 773
      Height = 44
      Caption = 'Buscar'
      TabOrder = 0
      object SpeedButton1: TSpeedButton
        Left = 495
        Top = 16
        Width = 82
        Height = 22
        Caption = '&BUSCAR'
        OnClick = SpeedButton1Click
      end
      object Label1: TLabel
        Left = 156
        Top = 19
        Width = 14
        Height = 13
        Caption = 'De'
      end
      object Label2: TLabel
        Left = 333
        Top = 19
        Width = 7
        Height = 13
        Caption = 'A'
      end
      object MaskEdit1: TMaskEdit
        Left = 192
        Top = 16
        Width = 120
        Height = 21
        EditMask = '!99/99/0000;1;_'
        MaxLength = 10
        TabOrder = 0
        Text = '  /  /    '
      end
      object MaskEdit2: TMaskEdit
        Left = 360
        Top = 16
        Width = 120
        Height = 21
        EditMask = '!99/99/0000;1;_'
        MaxLength = 10
        TabOrder = 1
        Text = '  /  /    '
      end
      object CheckBox1: TCheckBox
        Left = 32
        Top = 16
        Width = 97
        Height = 17
        Caption = 'Buscar Todas'
        TabOrder = 2
      end
    end
  end
  object Panel2: TPanel
    Left = 0
    Top = 65
    Width = 688
    Height = 381
    Align = alClient
    TabOrder = 1
    object DBGrid1: TDBGrid
      Left = 1
      Top = 1
      Width = 686
      Height = 379
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
    Active = True
    BufferChunks = 1000
    CachedUpdates = False
    SQL.Strings = (
      'select * from movimentodiario')
    Left = 126
    Top = 34
  end
  object DataSource1: TDataSource
    DataSet = IBQuery1
    Left = 90
    Top = 35
  end
end
