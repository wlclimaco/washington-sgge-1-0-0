object FrmConsSub_Grupo: TFrmConsSub_Grupo
  Left = 187
  Top = 285
  Width = 600
  Height = 224
  Caption = 'FrmConsSub_Grupo'
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
    Width = 592
    Height = 190
    Align = alClient
    TabOrder = 0
    object DBGrid1: TDBGrid
      Left = 1
      Top = 1
      Width = 590
      Height = 188
      Align = alClient
      DataSource = DataSource1
      TabOrder = 0
      TitleFont.Charset = DEFAULT_CHARSET
      TitleFont.Color = clWindowText
      TitleFont.Height = -11
      TitleFont.Name = 'MS Sans Serif'
      TitleFont.Style = []
      OnDblClick = DBGrid1DblClick
      Columns = <
        item
          Expanded = False
          FieldName = 'SUB_GRUPO'
          Visible = True
        end
        item
          Expanded = False
          FieldName = 'GRUPO'
          Visible = True
        end
        item
          Expanded = False
          FieldName = 'DSSUB_GRUPO'
          Width = 278
          Visible = True
        end>
    end
  end
  object IBQuery1: TIBQuery
    Database = DataModule1.DBPrincipal
    Transaction = DataModule1.IBTransaction1
    Active = True
    BufferChunks = 1000
    CachedUpdates = False
    SQL.Strings = (
      'SELECT *FROM sub_grupo where grupo = :grupo'
      ' ')
    Left = 107
    Top = 21
    ParamData = <
      item
        DataType = ftUnknown
        Name = 'grupo'
        ParamType = ptUnknown
      end>
  end
  object DataSource1: TDataSource
    DataSet = IBQuery1
    Left = 110
    Top = 51
  end
end
