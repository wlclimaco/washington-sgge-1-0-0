object Form2: TForm2
  Left = 168
  Top = 322
  Width = 790
  Height = 364
  Caption = 'Form2'
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
    Top = 289
    Width = 782
    Height = 41
    Align = alBottom
    TabOrder = 0
    object SpeedButton1: TSpeedButton
      Left = 708
      Top = 12
      Width = 55
      Height = 22
      OnClick = SpeedButton1Click
    end
  end
  object Panel2: TPanel
    Left = 0
    Top = 0
    Width = 782
    Height = 41
    Align = alTop
    TabOrder = 1
  end
  object ScrollBox1: TScrollBox
    Left = 0
    Top = 41
    Width = 782
    Height = 248
    Align = alClient
    TabOrder = 2
    object DBGrid1: TDBGrid
      Left = 0
      Top = 0
      Width = 778
      Height = 244
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
          Width = 71
          Visible = True
        end
        item
          Expanded = False
          FieldName = 'COD_FILIAL'
          Width = 63
          Visible = True
        end
        item
          Expanded = False
          FieldName = 'NOBANCO'
          Visible = True
        end
        item
          Expanded = False
          FieldName = 'CDAGENCIA'
          Visible = True
        end
        item
          Expanded = False
          FieldName = 'NOCONTA'
          Visible = True
        end
        item
          Expanded = False
          FieldName = 'NOCHEQUE'
          Width = 120
          Visible = True
        end
        item
          Expanded = False
          FieldName = 'DSSERIE'
          Visible = True
        end
        item
          Expanded = False
          FieldName = 'VLCHEQUE'
          Visible = True
        end
        item
          Expanded = False
          FieldName = 'DTMOVIMENTO'
          Width = 90
          Visible = True
        end
        item
          Expanded = False
          FieldName = 'DTPREDATADO'
          Width = 91
          Visible = True
        end
        item
          Expanded = False
          FieldName = 'TPSITUACAO'
          Visible = True
        end
        item
          Expanded = False
          FieldName = 'NOCNPJCPF'
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
      'SELECT *FROM CHEQUES')
    UpdateObject = IBUpdateSQL1
    Left = 662
    Top = 5
    object IBQuery1NOBANCO: TIntegerField
      FieldName = 'NOBANCO'
      Origin = 'CHEQUES.NOBANCO'
      Required = True
    end
    object IBQuery1CDAGENCIA: TIBStringField
      FieldName = 'CDAGENCIA'
      Origin = 'CHEQUES.CDAGENCIA'
      Required = True
    end
    object IBQuery1NOCONTA: TIBStringField
      FieldName = 'NOCONTA'
      Origin = 'CHEQUES.NOCONTA'
      Required = True
    end
    object IBQuery1NOCHEQUE: TIBStringField
      FieldName = 'NOCHEQUE'
      Origin = 'CHEQUES.NOCHEQUE'
      Required = True
      Size = 25
    end
    object IBQuery1COD_PART: TIntegerField
      FieldName = 'COD_PART'
      Origin = 'CHEQUES.COD_PART'
    end
    object IBQuery1VLCHEQUE: TFloatField
      FieldName = 'VLCHEQUE'
      Origin = 'CHEQUES.VLCHEQUE'
    end
    object IBQuery1DTMOVIMENTO: TDateTimeField
      FieldName = 'DTMOVIMENTO'
      Origin = 'CHEQUES.DTMOVIMENTO'
    end
    object IBQuery1DTPREDATADO: TDateTimeField
      FieldName = 'DTPREDATADO'
      Origin = 'CHEQUES.DTPREDATADO'
    end
    object IBQuery1NOCNPJCPF: TIBStringField
      FieldName = 'NOCNPJCPF'
      Origin = 'CHEQUES.NOCNPJCPF'
    end
    object IBQuery1COD_EMPRESA: TIntegerField
      FieldName = 'COD_EMPRESA'
      Origin = 'CHEQUES.COD_EMPRESA'
    end
    object IBQuery1COD_FILIAL: TIntegerField
      FieldName = 'COD_FILIAL'
      Origin = 'CHEQUES.COD_FILIAL'
    end
    object IBQuery1TPSITUACAO: TIBStringField
      FieldName = 'TPSITUACAO'
      Origin = 'CHEQUES.TPSITUACAO'
      Size = 1
    end
    object IBQuery1NMEMITENTE: TIBStringField
      FieldName = 'NMEMITENTE'
      Origin = 'CHEQUES.NMEMITENTE'
      Size = 55
    end
    object IBQuery1DTBAIXA: TDateTimeField
      FieldName = 'DTBAIXA'
      Origin = 'CHEQUES.DTBAIXA'
    end
    object IBQuery1DTDEVOLUCAO: TDateTimeField
      FieldName = 'DTDEVOLUCAO'
      Origin = 'CHEQUES.DTDEVOLUCAO'
    end
    object IBQuery1DSOBSERVACAO: TIBStringField
      FieldName = 'DSOBSERVACAO'
      Origin = 'CHEQUES.DSOBSERVACAO'
      Size = 110
    end
    object IBQuery1DTEMISSAO: TDateTimeField
      FieldName = 'DTEMISSAO'
      Origin = 'CHEQUES.DTEMISSAO'
    end
    object IBQuery1DTBAIXADEVOL: TDateTimeField
      FieldName = 'DTBAIXADEVOL'
      Origin = 'CHEQUES.DTBAIXADEVOL'
    end
    object IBQuery1DTSEGDEV: TDateTimeField
      FieldName = 'DTSEGDEV'
      Origin = 'CHEQUES.DTSEGDEV'
    end
    object IBQuery1DTSEGBAI: TDateTimeField
      FieldName = 'DTSEGBAI'
      Origin = 'CHEQUES.DTSEGBAI'
    end
    object IBQuery1DSSERIE: TIBStringField
      FieldName = 'DSSERIE'
      Origin = 'CHEQUES.DSSERIE'
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
      '  NOBANCO,'
      '  CDAGENCIA,'
      '  NOCONTA,'
      '  DSSERIE,'
      '  NOCHEQUE,'
      '  COD_PART,'
      '  VLCHEQUE,'
      '  DTMOVIMENTO,'
      '  DTPREDATADO,'
      '  NOCNPJCPF,'
      '  COD_EMPRESA,'
      '  COD_FILIAL,'
      '  TPSITUACAO,'
      '  NMEMITENTE,'
      '  DTBAIXA,'
      '  DTDEVOLUCAO,'
      '  DSOBSERVACAO,'
      '  DTEMISSAO,'
      '  DTBAIXADEVOL,'
      '  DTSEGDEV,'
      '  DTSEGBAI'
      'from CHEQUES '
      'where'
      '  NOBANCO = :NOBANCO and'
      '  CDAGENCIA = :CDAGENCIA and'
      '  NOCONTA = :NOCONTA')
    ModifySQL.Strings = (
      'update CHEQUES'
      'set'
      '  NOBANCO = :NOBANCO,'
      '  CDAGENCIA = :CDAGENCIA,'
      '  NOCONTA = :NOCONTA,'
      '  DSSERIE = :DSSERIE,'
      '  NOCHEQUE = :NOCHEQUE,'
      '  COD_PART = :COD_PART,'
      '  VLCHEQUE = :VLCHEQUE,'
      '  DTMOVIMENTO = :DTMOVIMENTO,'
      '  DTPREDATADO = :DTPREDATADO,'
      '  NOCNPJCPF = :NOCNPJCPF,'
      '  COD_EMPRESA = :COD_EMPRESA,'
      '  COD_FILIAL = :COD_FILIAL,'
      '  TPSITUACAO = :TPSITUACAO,'
      '  NMEMITENTE = :NMEMITENTE,'
      '  DTBAIXA = :DTBAIXA,'
      '  DTDEVOLUCAO = :DTDEVOLUCAO,'
      '  DSOBSERVACAO = :DSOBSERVACAO,'
      '  DTEMISSAO = :DTEMISSAO,'
      '  DTBAIXADEVOL = :DTBAIXADEVOL,'
      '  DTSEGDEV = :DTSEGDEV,'
      '  DTSEGBAI = :DTSEGBAI'
      'where'
      '  NOBANCO = :OLD_NOBANCO and'
      '  CDAGENCIA = :OLD_CDAGENCIA and'
      '  NOCONTA = :OLD_NOCONTA')
    InsertSQL.Strings = (
      'insert into CHEQUES'
      '  (NOBANCO, CDAGENCIA, NOCONTA, DSSERIE, NOCHEQUE, COD_PART, '
      'VLCHEQUE, '
      
        '   DTMOVIMENTO, DTPREDATADO, NOCNPJCPF, COD_EMPRESA, COD_FILIAL,' +
        ' '
      'TPSITUACAO, '
      '   NMEMITENTE, DTBAIXA, DTDEVOLUCAO, DSOBSERVACAO, DTEMISSAO, '
      'DTBAIXADEVOL, '
      '   DTSEGDEV, DTSEGBAI)'
      'values'
      
        '  (:NOBANCO, :CDAGENCIA, :NOCONTA, :DSSERIE, :NOCHEQUE, :COD_PAR' +
        'T, '
      ':VLCHEQUE, '
      '   :DTMOVIMENTO, :DTPREDATADO, :NOCNPJCPF, :COD_EMPRESA, '
      ':COD_FILIAL, :TPSITUACAO, '
      
        '   :NMEMITENTE, :DTBAIXA, :DTDEVOLUCAO, :DSOBSERVACAO, :DTEMISSA' +
        'O, '
      ':DTBAIXADEVOL, '
      '   :DTSEGDEV, :DTSEGBAI)')
    DeleteSQL.Strings = (
      'delete from CHEQUES'
      'where'
      '  NOBANCO = :OLD_NOBANCO and'
      '  CDAGENCIA = :OLD_CDAGENCIA and'
      '  NOCONTA = :OLD_NOCONTA')
    Left = 700
    Top = 8
  end
end
