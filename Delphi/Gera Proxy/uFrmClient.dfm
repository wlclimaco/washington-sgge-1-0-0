object FrmClient: TFrmClient
  Left = 297
  Top = 90
  Caption = 'FrmClient'
  ClientHeight = 324
  ClientWidth = 655
  Color = clBtnFace
  Font.Charset = DEFAULT_CHARSET
  Font.Color = clWindowText
  Font.Height = -11
  Font.Name = 'Tahoma'
  Font.Style = []
  OldCreateOrder = False
  PixelsPerInch = 96
  TextHeight = 13
  object LblTransac: TLabel
    Left = 216
    Top = 91
    Width = 51
    Height = 13
    Caption = 'LblTransac'
  end
  object BbtConecta: TBitBtn
    Left = 120
    Top = 24
    Width = 75
    Height = 25
    Caption = 'Desconectado'
    DoubleBuffered = True
    ParentDoubleBuffered = False
    TabOrder = 0
    OnClick = BbtConectaClick
  end
  object BbtDados: TBitBtn
    Left = 120
    Top = 55
    Width = 75
    Height = 25
    Caption = 'Dados'
    DoubleBuffered = True
    ParentDoubleBuffered = False
    TabOrder = 1
    OnClick = BbtDadosClick
  end
  object DBGrid1: TDBGrid
    Left = 0
    Top = 207
    Width = 320
    Height = 120
    DataSource = DataSource1
    TabOrder = 2
    TitleFont.Charset = DEFAULT_CHARSET
    TitleFont.Color = clWindowText
    TitleFont.Height = -11
    TitleFont.Name = 'Tahoma'
    TitleFont.Style = []
  end
  object BbtTransac: TBitBtn
    Left = 120
    Top = 86
    Width = 75
    Height = 25
    Caption = 'Transa'#231#227'o'
    DoubleBuffered = True
    ParentDoubleBuffered = False
    TabOrder = 3
    OnClick = BbtTransacClick
  end
  object BitBtn1: TBitBtn
    Left = 208
    Top = 55
    Width = 75
    Height = 25
    Caption = 'Limpa'
    DoubleBuffered = True
    ParentDoubleBuffered = False
    TabOrder = 4
    OnClick = BitBtn1Click
  end
  object DBGrid2: TDBGrid
    Left = 326
    Top = 207
    Width = 320
    Height = 120
    DataSource = DataSource2
    TabOrder = 5
    TitleFont.Charset = DEFAULT_CHARSET
    TitleFont.Color = clWindowText
    TitleFont.Height = -11
    TitleFont.Name = 'Tahoma'
    TitleFont.Style = []
  end
  object Button1: TButton
    Left = 480
    Top = 176
    Width = 75
    Height = 25
    Caption = 'Button1'
    TabOrder = 6
    OnClick = Button1Click
  end
  object SRVSERVER: TSQLConnection
    ConnectionName = 'SRVSERVER'
    DriverName = 'DATASNAP'
    LoginPrompt = False
    Params.Strings = (
      'DriverUnit=DBXDataSnap'
      'DatasnapContext=datasnap/'
      
        'DriverAssemblyLoader=Borland.Data.TDBXClientDriverLoader,Borland' +
        '.Data.DbxClientDriver,Version=15.0.0.0,Culture=neutral,PublicKey' +
        'Token=91d62ebb5b0d1b1b'
      'drivername=DATASNAP'
      'Port=5001'
      'communicationprotocol=tcp/ip'
      'HostName=192.10.10.77'
      'Filters={}'
      
        'ConnectionString=DriverUnit=DBXDataSnap,DatasnapContext=datasnap' +
        '/,DriverAssemblyLoader=Borland.Data.TDBXClientDriverLoader,Borla' +
        'nd.Data.DbxClientDriver,Version=15.0.0.0,Culture=neutral,PublicK' +
        'eyToken=91d62ebb5b0d1b1b,drivername=DATASNAP,Port=5001,communica' +
        'tionprotocol=tcp/ip,hostname=192.10.10.199,Filters={},Connection' +
        'String=DriverUnit=DBXDataSnap,DatasnapContext=datasnap/,DriverAs' +
        'semblyLoader=Borland.Data.TDBXClientDriverLoader,Borland.Data.Db' +
        'xClientDriver,Version=15.0.0.0,Culture=neutral,PublicKeyToken=91' +
        'd62ebb5b0d1b1b,drivername=DATASNAP,Port=5001,communicationprotoc' +
        'ol=tcp/ip,hostname=192.10.10.199,Filters={}')
    Left = 26
    Top = 10
    UniqueId = '{7D2C92DD-7679-4C7C-996C-AD7D3E9C7324}'
  end
  object ClientDataSet1: TClientDataSet
    Aggregates = <>
    Params = <>
    Left = 280
    Top = 8
  end
  object DataSource1: TDataSource
    DataSet = ClientDataSet1
    Left = 360
    Top = 8
  end
  object ClientDataSet2: TClientDataSet
    Aggregates = <>
    Params = <>
    ProviderName = 'DataSetProvider2'
    RemoteServer = PvcTabela
    AfterPost = ClientDataSet2AfterPost
    Left = 272
    Top = 136
  end
  object DataSource2: TDataSource
    DataSet = ClientDataSet2
    Left = 400
    Top = 136
  end
  object PvcTabela: TDSProviderConnection
    ServerClassName = 'TServerMethods'
    SQLConnection = SRVSERVER
    Left = 24
    Top = 64
  end
end
