object ConsForn: TConsForn
  Left = 192
  Top = 114
  Width = 783
  Height = 540
  Caption = 'ConsForn'
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
    object GroupBox2: TGroupBox
      Left = -1
      Top = 44
      Width = 773
      Height = 44
      Caption = 'Filtrar'
      TabOrder = 0
      object RadioButton1: TRadioButton
        Left = 27
        Top = 16
        Width = 151
        Height = 17
        Caption = 'NOME'
        TabOrder = 0
      end
      object RadioButton2: TRadioButton
        Left = 101
        Top = 16
        Width = 113
        Height = 17
        Caption = 'CPF/CNPJ'
        TabOrder = 1
      end
      object RadioButton3: TRadioButton
        Left = 192
        Top = 16
        Width = 113
        Height = 17
        Caption = 'IE/RG'
        TabOrder = 2
      end
      object RadioButton4: TRadioButton
        Left = 275
        Top = 15
        Width = 113
        Height = 17
        Caption = 'TIPO PESSOA'
        TabOrder = 3
      end
      object RadioButton5: TRadioButton
        Left = 379
        Top = 15
        Width = 113
        Height = 17
        Caption = 'REPRESENTANTE'
        TabOrder = 4
      end
      object RadioButton6: TRadioButton
        Left = 503
        Top = 15
        Width = 113
        Height = 17
        Caption = 'RAMO ATIVIDADE'
        TabOrder = 5
      end
      object RadioButton12: TRadioButton
        Left = 628
        Top = 15
        Width = 113
        Height = 17
        Caption = 'TUDO'
        Checked = True
        TabOrder = 6
        TabStop = True
      end
    end
    object GroupBox3: TGroupBox
      Left = -1
      Top = 1
      Width = 773
      Height = 44
      Caption = 'Buscar'
      TabOrder = 1
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
    Database = DataModule1.DBPrincipal
    Transaction = DataModule1.IBTransaction1
    Active = True
    BufferChunks = 1000
    CachedUpdates = False
    SQL.Strings = (
      'select * from CLIENTES_FORNECEDOR order by cod_part')
    Left = 54
    Top = 10
  end
  object DataSource1: TDataSource
    DataSet = IBQuery1
    Left = 90
    Top = 11
  end
end
