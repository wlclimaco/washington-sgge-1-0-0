object RelApuSaidas: TRelApuSaidas
  Left = 181
  Top = 236
  Width = 357
  Height = 186
  Caption = 'Rel Apura'#231#227'o Saidas'
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
    Width = 349
    Height = 152
    Align = alClient
    TabOrder = 0
    object Label1: TLabel
      Left = 64
      Top = 16
      Width = 243
      Height = 24
      Caption = 'Relatorio de cupom fiscal '
      Font.Charset = DEFAULT_CHARSET
      Font.Color = clWindowText
      Font.Height = -19
      Font.Name = 'MS Sans Serif'
      Font.Style = [fsBold]
      ParentFont = False
    end
    object Label2: TLabel
      Left = 32
      Top = 70
      Width = 27
      Height = 24
      Caption = 'De'
      Font.Charset = DEFAULT_CHARSET
      Font.Color = clWindowText
      Font.Height = -19
      Font.Name = 'MS Sans Serif'
      Font.Style = [fsBold]
      ParentFont = False
    end
    object Label3: TLabel
      Left = 192
      Top = 72
      Width = 12
      Height = 24
      Caption = 'a'
      Font.Charset = DEFAULT_CHARSET
      Font.Color = clWindowText
      Font.Height = -19
      Font.Name = 'MS Sans Serif'
      Font.Style = [fsBold]
      ParentFont = False
    end
    object SpeedButton1: TSpeedButton
      Left = 264
      Top = 112
      Width = 65
      Height = 22
      Caption = 'Gerar'
      OnClick = SpeedButton1Click
    end
    object DatePickerEditor1: TDatePickerEditor
      Left = 64
      Top = 72
      Width = 121
      Height = 20
      Alignment = taLeftJustify
      BorderStyle = bsSingle
      Margin = 0
      ParentColor = False
      TabOrder = 0
      TabStop = True
      VerticalAlignment = vaMiddle
      AutoSelect = False
      ReadOnly = False
      ShowPreview = False
      Text = '25/09/2011'
      Style = dsDropDown
      Date = 40811.000000000000000000
    end
    object DatePickerEditor2: TDatePickerEditor
      Left = 210
      Top = 72
      Width = 121
      Height = 20
      Alignment = taLeftJustify
      BorderStyle = bsSingle
      Margin = 0
      ParentColor = False
      TabOrder = 1
      TabStop = True
      VerticalAlignment = vaMiddle
      AutoSelect = False
      ReadOnly = False
      ShowPreview = False
      Text = '25/09/2011'
      Style = dsDropDown
      Date = 40811.000000000000000000
    end
  end
  object QryConsProd: TIBQuery
    Database = DataModule1.DBPrincipal
    Transaction = DataModule1.IBTransaction1
    BufferChunks = 1000
    CachedUpdates = False
    SQL.Strings = (
      'select *from Prod_serv where Cod_produto = :CodProd')
    Left = 384
    Top = 8
    ParamData = <
      item
        DataType = ftUnknown
        Name = 'CodProd'
        ParamType = ptUnknown
      end>
  end
  object IBQuery1: TIBQuery
    Database = DataModule1.DBPrincipal
    Transaction = DataModule1.IBTransaction1
    BufferChunks = 1000
    CachedUpdates = False
    SQL.Strings = (
      'select *from Prod_serv where Cod_produto = :CodProd')
    Left = 384
    Top = 8
    ParamData = <
      item
        DataType = ftUnknown
        Name = 'CodProd'
        ParamType = ptUnknown
      end>
  end
  object IBQuery2: TIBQuery
    Database = DataModule1.DBPrincipal
    Transaction = DataModule1.IBTransaction1
    BufferChunks = 1000
    CachedUpdates = False
    SQL.Strings = (
      'select *from Prod_serv where Cod_produto = :CodProd')
    Left = 384
    Top = 8
    ParamData = <
      item
        DataType = ftUnknown
        Name = 'CodProd'
        ParamType = ptUnknown
      end>
  end
  object IBQuery3: TIBQuery
    Database = DataModule1.DBPrincipal
    Transaction = DataModule1.IBTransaction1
    BufferChunks = 1000
    CachedUpdates = False
    SQL.Strings = (
      
        'select n.coo,n.noitem,n.cdproduto,p.descr_produto,n.codpro,n.vlp' +
        'roduto,n.quantidade from nfsaidasitens n left join prod_serv p o' +
        'n n.cdproduto = p.ref where n.coo in (select ni.coo from nfsaida' +
        's2 ni where'
      'ni.dtmovimento between :dtinicial and :dtfinal)'
      'order by n.coo, n.noitem')
    Left = 8
    Top = 8
    ParamData = <
      item
        DataType = ftUnknown
        Name = 'dtinicial'
        ParamType = ptUnknown
      end
      item
        DataType = ftUnknown
        Name = 'dtfinal'
        ParamType = ptUnknown
      end>
  end
  object DataSource1: TDataSource
    DataSet = IBQuery3
    Left = 8
    Top = 48
  end
end
