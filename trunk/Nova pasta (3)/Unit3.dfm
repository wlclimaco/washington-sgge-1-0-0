object Form3: TForm3
  Left = 177
  Top = 306
  Width = 783
  Height = 280
  Caption = 'Form3'
  Color = clBtnFace
  Font.Charset = DEFAULT_CHARSET
  Font.Color = clWindowText
  Font.Height = -11
  Font.Name = 'MS Sans Serif'
  Font.Style = []
  OldCreateOrder = False
  OnCreate = FormCreate
  PixelsPerInch = 96
  TextHeight = 13
  object Panel1: TPanel
    Left = 0
    Top = 205
    Width = 775
    Height = 41
    Align = alBottom
    TabOrder = 0
    object SpeedButton1: TSpeedButton
      Left = 708
      Top = 11
      Width = 55
      Height = 22
    end
  end
  object Panel2: TPanel
    Left = 0
    Top = 0
    Width = 775
    Height = 41
    Align = alTop
    TabOrder = 1
  end
  object ScrollBox1: TScrollBox
    Left = 0
    Top = 41
    Width = 775
    Height = 164
    Align = alClient
    TabOrder = 2
    object DBGrid1: TDBGrid
      Left = 0
      Top = 0
      Width = 771
      Height = 160
      Align = alClient
      DataSource = DataSource1
      TabOrder = 0
      TitleFont.Charset = DEFAULT_CHARSET
      TitleFont.Color = clWindowText
      TitleFont.Height = -11
      TitleFont.Name = 'MS Sans Serif'
      TitleFont.Style = []
      Columns = <
        item
          Expanded = False
          FieldName = 'COD_EMPRESA'
          Width = 57
          Visible = True
        end
        item
          Expanded = False
          FieldName = 'COD_FILIAL'
          Width = 71
          Visible = True
        end
        item
          Expanded = False
          FieldName = 'COD_TITULO'
          Width = 74
          Visible = True
        end
        item
          Expanded = False
          FieldName = 'NOPARCELA'
          Visible = True
        end
        item
          Expanded = False
          FieldName = 'CDTITULAR'
          Width = 84
          Visible = True
        end
        item
          Expanded = False
          FieldName = 'DTLANCAMENTO'
          Width = 97
          Visible = True
        end
        item
          Expanded = False
          FieldName = 'DTVENCIMENTO'
          Width = 91
          Visible = True
        end
        item
          Expanded = False
          FieldName = 'VLTITULO'
          Visible = True
        end
        item
          Expanded = False
          FieldName = 'VLDESCONTO'
          Visible = True
        end
        item
          Expanded = False
          FieldName = 'NOCONTA'
          Visible = True
        end
        item
          Expanded = False
          FieldName = 'DSESPECIE'
          Visible = True
        end>
    end
  end
  object IBQuery1: TIBQuery
    Database = Form1.IBDatabase1
    Transaction = Form1.IBTransaction1
    BufferChunks = 1000
    CachedUpdates = False
    SQL.Strings = (
      'SELECT *from TITULOSRECEBER')
    UpdateObject = IBUpdateSQL1
    Left = 662
    Top = 5
    object IBQuery1COD_EMPRESA: TIntegerField
      FieldName = 'COD_EMPRESA'
      Origin = 'TITULOSRECEBER.COD_EMPRESA'
      Required = True
    end
    object IBQuery1COD_FILIAL: TIntegerField
      FieldName = 'COD_FILIAL'
      Origin = 'TITULOSRECEBER.COD_FILIAL'
      Required = True
    end
    object IBQuery1COD_TITULO: TIntegerField
      FieldName = 'COD_TITULO'
      Origin = 'TITULOSRECEBER.COD_TITULO'
      Required = True
    end
    object IBQuery1NOPARCELA: TIntegerField
      FieldName = 'NOPARCELA'
      Origin = 'TITULOSRECEBER.NOPARCELA'
      Required = True
    end
    object IBQuery1CDTITULAR: TIntegerField
      FieldName = 'CDTITULAR'
      Origin = 'TITULOSRECEBER.CDTITULAR'
    end
    object IBQuery1DSESPECIE: TIBStringField
      FieldName = 'DSESPECIE'
      Origin = 'TITULOSRECEBER.DSESPECIE'
      Required = True
      Size = 15
    end
    object IBQuery1VLTITULO: TFloatField
      FieldName = 'VLTITULO'
      Origin = 'TITULOSRECEBER.VLTITULO'
    end
    object IBQuery1DTLANCAMENTO: TDateTimeField
      FieldName = 'DTLANCAMENTO'
      Origin = 'TITULOSRECEBER.DTLANCAMENTO'
    end
    object IBQuery1HOLANCAMENTO: TIBStringField
      FieldName = 'HOLANCAMENTO'
      Origin = 'TITULOSRECEBER.HOLANCAMENTO'
      Size = 8
    end
    object IBQuery1DTVENCIMENTO: TDateTimeField
      FieldName = 'DTVENCIMENTO'
      Origin = 'TITULOSRECEBER.DTVENCIMENTO'
    end
    object IBQuery1DTLIMITE: TDateTimeField
      FieldName = 'DTLIMITE'
      Origin = 'TITULOSRECEBER.DTLIMITE'
    end
    object IBQuery1DTDESCONTO: TDateTimeField
      FieldName = 'DTDESCONTO'
      Origin = 'TITULOSRECEBER.DTDESCONTO'
    end
    object IBQuery1VLDESCONTO: TFloatField
      FieldName = 'VLDESCONTO'
      Origin = 'TITULOSRECEBER.VLDESCONTO'
    end
    object IBQuery1VLJURODIA: TFloatField
      FieldName = 'VLJURODIA'
      Origin = 'TITULOSRECEBER.VLJURODIA'
    end
    object IBQuery1VLMULTA: TFloatField
      FieldName = 'VLMULTA'
      Origin = 'TITULOSRECEBER.VLMULTA'
    end
    object IBQuery1NOBANCO: TIntegerField
      FieldName = 'NOBANCO'
      Origin = 'TITULOSRECEBER.NOBANCO'
    end
    object IBQuery1CDAGENCIA: TIBStringField
      FieldName = 'CDAGENCIA'
      Origin = 'TITULOSRECEBER.CDAGENCIA'
    end
    object IBQuery1NOCONTA: TIntegerField
      FieldName = 'NOCONTA'
      Origin = 'TITULOSRECEBER.NOCONTA'
    end
    object IBQuery1NOSSONUMERO: TFloatField
      FieldName = 'NOSSONUMERO'
      Origin = 'TITULOSRECEBER.NOSSONUMERO'
    end
    object IBQuery1DGNOSSONUMERO: TIBStringField
      FieldName = 'DGNOSSONUMERO'
      Origin = 'TITULOSRECEBER.DGNOSSONUMERO'
      Size = 1
    end
    object IBQuery1CDREPRESENTANTE: TIntegerField
      FieldName = 'CDREPRESENTANTE'
      Origin = 'TITULOSRECEBER.CDREPRESENTANTE'
    end
    object IBQuery1OBSTITULO: TIBStringField
      FieldName = 'OBSTITULO'
      Origin = 'TITULOSRECEBER.OBSTITULO'
      Size = 50
    end
    object IBQuery1STTITULO: TIBStringField
      FieldName = 'STTITULO'
      Origin = 'TITULOSRECEBER.STTITULO'
      Size = 1
    end
    object IBQuery1STATUS: TIBStringField
      FieldName = 'STATUS'
      Origin = 'TITULOSRECEBER.STATUS'
      Size = 1
    end
    object IBQuery1CARTEIRA: TIBStringField
      FieldName = 'CARTEIRA'
      Origin = 'TITULOSRECEBER.CARTEIRA'
      Size = 5
    end
  end
  object DataSource1: TDataSource
    DataSet = IBQuery1
    Left = 628
    Top = 6
  end
  object IBUpdateSQL1: TIBUpdateSQL
    RefreshSQL.Strings = (
      'Select '
      '  COD_EMPRESA,'
      '  COD_FILIAL,'
      '  COD_TITULO,'
      '  NOPARCELA,'
      '  CDTITULAR,'
      '  DSESPECIE,'
      '  VLTITULO,'
      '  DTLANCAMENTO,'
      '  HOLANCAMENTO,'
      '  DTVENCIMENTO,'
      '  DTLIMITE,'
      '  DTDESCONTO,'
      '  VLDESCONTO,'
      '  VLJURODIA,'
      '  VLMULTA,'
      '  NOBANCO,'
      '  CDAGENCIA,'
      '  NOCONTA,'
      '  NOSSONUMERO,'
      '  DGNOSSONUMERO,'
      '  CDREPRESENTANTE,'
      '  OBSTITULO,'
      '  STTITULO,'
      '  STATUS,'
      '  CARTEIRA'
      'from TITULOSRECEBER '
      'where'
      '  COD_EMPRESA = :COD_EMPRESA and'
      '  COD_FILIAL = :COD_FILIAL and'
      '  COD_TITULO = :COD_TITULO')
    ModifySQL.Strings = (
      'update TITULOSRECEBER'
      'set'
      '  COD_EMPRESA = :COD_EMPRESA,'
      '  COD_FILIAL = :COD_FILIAL,'
      '  COD_TITULO = :COD_TITULO,'
      '  NOPARCELA = :NOPARCELA,'
      '  CDTITULAR = :CDTITULAR,'
      '  DSESPECIE = :DSESPECIE,'
      '  VLTITULO = :VLTITULO,'
      '  DTLANCAMENTO = :DTLANCAMENTO,'
      '  HOLANCAMENTO = :HOLANCAMENTO,'
      '  DTVENCIMENTO = :DTVENCIMENTO,'
      '  DTLIMITE = :DTLIMITE,'
      '  DTDESCONTO = :DTDESCONTO,'
      '  VLDESCONTO = :VLDESCONTO,'
      '  VLJURODIA = :VLJURODIA,'
      '  VLMULTA = :VLMULTA,'
      '  NOBANCO = :NOBANCO,'
      '  CDAGENCIA = :CDAGENCIA,'
      '  NOCONTA = :NOCONTA,'
      '  NOSSONUMERO = :NOSSONUMERO,'
      '  DGNOSSONUMERO = :DGNOSSONUMERO,'
      '  CDREPRESENTANTE = :CDREPRESENTANTE,'
      '  OBSTITULO = :OBSTITULO,'
      '  STTITULO = :STTITULO,'
      '  STATUS = :STATUS,'
      '  CARTEIRA = :CARTEIRA'
      'where'
      '  COD_EMPRESA = :OLD_COD_EMPRESA and'
      '  COD_FILIAL = :OLD_COD_FILIAL and'
      '  COD_TITULO = :OLD_COD_TITULO')
    InsertSQL.Strings = (
      'insert into TITULOSRECEBER'
      
        '  (COD_EMPRESA, COD_FILIAL, COD_TITULO, NOPARCELA, CDTITULAR, DS' +
        'ESPECIE, '
      
        '   VLTITULO, DTLANCAMENTO, HOLANCAMENTO, DTVENCIMENTO, DTLIMITE,' +
        ' DTDESCONTO, '
      
        '   VLDESCONTO, VLJURODIA, VLMULTA, NOBANCO, CDAGENCIA, NOCONTA, ' +
        'NOSSONUMERO, '
      
        '   DGNOSSONUMERO, CDREPRESENTANTE, OBSTITULO, STTITULO, STATUS, ' +
        'CARTEIRA)'
      'values'
      
        '  (:COD_EMPRESA, :COD_FILIAL, :COD_TITULO, :NOPARCELA, :CDTITULA' +
        'R, :DSESPECIE, '
      
        '   :VLTITULO, :DTLANCAMENTO, :HOLANCAMENTO, :DTVENCIMENTO, :DTLI' +
        'MITE, :DTDESCONTO, '
      
        '   :VLDESCONTO, :VLJURODIA, :VLMULTA, :NOBANCO, :CDAGENCIA, :NOC' +
        'ONTA, :NOSSONUMERO, '
      
        '   :DGNOSSONUMERO, :CDREPRESENTANTE, :OBSTITULO, :STTITULO, :STA' +
        'TUS, :CARTEIRA)')
    DeleteSQL.Strings = (
      'delete from TITULOSRECEBER'
      'where'
      '  COD_EMPRESA = :OLD_COD_EMPRESA and'
      '  COD_FILIAL = :OLD_COD_FILIAL and'
      '  COD_TITULO = :OLD_COD_TITULO')
    Left = 700
    Top = 8
  end
end
