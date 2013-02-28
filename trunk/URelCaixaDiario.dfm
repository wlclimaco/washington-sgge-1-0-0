object RelMovDiario: TRelMovDiario
  Left = 251
  Top = 184
  Width = 356
  Height = 178
  Caption = 'RelMovDiario'
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
    Width = 348
    Height = 144
    Align = alClient
    TabOrder = 0
    object Label1: TLabel
      Left = 72
      Top = 24
      Width = 55
      Height = 13
      Caption = 'DT INICIAL'
    end
    object Label2: TLabel
      Left = 72
      Top = 72
      Width = 48
      Height = 13
      Caption = 'DT FINAL'
    end
    object SpeedButton1: TSpeedButton
      Left = 256
      Top = 112
      Width = 81
      Height = 22
      Caption = 'OK'
      OnClick = SpeedButton1Click
    end
    object MaskEdit1: TMaskEdit
      Left = 136
      Top = 24
      Width = 120
      Height = 21
      EditMask = '!99/99/0000;1;_'
      MaxLength = 10
      TabOrder = 0
      Text = '  /  /    '
    end
    object MaskEdit2: TMaskEdit
      Left = 136
      Top = 64
      Width = 120
      Height = 21
      EditMask = '!99/99/0000;1;_'
      MaxLength = 10
      TabOrder = 1
      Text = '  /  /    '
    end
  end
  object IBQuery1: TIBQuery
    Database = DataModule1.DBPrincipal
    Transaction = DataModule1.IBTransaction1
    BufferChunks = 1000
    CachedUpdates = False
    SQL.Strings = (
      
        'SELECT *FROM MOVIMENTODIARIO WHERE DATAMOVIMENTO BETWEEN :DTINIC' +
        'IAL AND :DTFINAL order by DATAMOVIMENTO')
    Left = 336
    Top = 24
    ParamData = <
      item
        DataType = ftUnknown
        Name = 'DTINICIAL'
        ParamType = ptUnknown
      end
      item
        DataType = ftUnknown
        Name = 'DTFINAL'
        ParamType = ptUnknown
      end>
  end
  object DataSource1: TDataSource
    DataSet = IBQuery1
    Left = 376
    Top = 24
  end
end
