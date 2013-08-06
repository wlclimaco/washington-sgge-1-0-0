object DmSrvApl: TDmSrvApl
  OldCreateOrder = False
  OnCreate = DataModuleCreate
  Height = 290
  Width = 470
  object PvcSDmSis: TDSProviderConnection
    ServerClassName = 'TSDmSis'
    SQLConnection = SrvAplica
    Left = 32
    Top = 64
  end
  object SrvAplica: TSQLConnection
    DriverName = 'DataSnap'
    LoginPrompt = False
    Params.Strings = (
      'DriverName=DataSnap'
      'HostName=192.10.10.67'
      'port=5001')
    Left = 29
    Top = 5
  end
  object BrvlIP: TBrvlIP
    BrComputadorLocal = False
    Left = 368
    Top = 16
  end
  object BrvDicionario: TBrvDicionario
    SQLConnection = SrvAplica
    UserCode = 0
    UserGroupAdm = False
    AlterConfirm = False
    NumeroAcesso = 0
    CodigoBanco = 0
    Camada = Client
    TipoBancoDados = BcoFirebird
    VersaoCliente = 2
    VersaoClienteSub = 2
    VersaoServidor = 0
    VersaoServidorSub = 0
    Left = 368
    Top = 64
  end
  object PvcSDmTra: TDSProviderConnection
    ServerClassName = 'TSDmTra'
    SQLConnection = SrvAplica
    Left = 104
    Top = 64
  end
  object SrvImpres: TSQLConnection
    ConnectionName = 'SrvImpres'
    DriverName = 'Datasnap'
    LoginPrompt = False
    Params.Strings = (
      'DriverUnit=DBXDataSnap'
      'HostName=localhost'
      'Port=211'
      'CommunicationProtocol=tcp/ip'
      'DatasnapContext=datasnap/'
      
        'DriverAssemblyLoader=Borland.Data.TDBXClientDriverLoader,Borland' +
        '.Data.DbxClientDriver,Version=15.0.0.0,Culture=neutral,PublicKey' +
        'Token=91d62ebb5b0d1b1b'
      'Filters={}')
    Left = 29
    Top = 133
  end
  object PvcSDmCon: TDSProviderConnection
    ServerClassName = 'TSDmCon'
    SQLConnection = SrvAplica
    Left = 168
    Top = 64
  end
  object PvcSDmWms: TDSProviderConnection
    ServerClassName = 'TSDmWms'
    SQLConnection = SrvAplica
    Left = 232
    Top = 64
  end
end
