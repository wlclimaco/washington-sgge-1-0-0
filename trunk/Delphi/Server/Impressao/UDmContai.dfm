object DmContai: TDmContai
  OldCreateOrder = False
  OnCreate = DataModuleCreate
  Height = 340
  Width = 415
  object DSServer: TDSServer
    AutoStart = False
    HideDSAdmin = False
    Left = 96
    Top = 11
  end
  object DSTCPServerTransport: TDSTCPServerTransport
    Port = 123
    PoolSize = 0
    Server = DSServer
    BufferKBSize = 32
    Filters = <>
    AuthenticationManager = DSAuthenticationManager
    Left = 96
    Top = 73
  end
  object DSHTTPService: TDSHTTPService
    DSContext = 'datasnap/'
    RESTContext = 'rest/'
    CacheContext = 'cache/'
    Server = DSServer
    DSHostname = 'localhost'
    DSPort = 211
    Filters = <>
    AuthenticationManager = DSAuthenticationManager
    CredentialsPassThrough = False
    SessionTimeout = 1200000
    HttpPort = 8088
    Active = False
    Left = 96
    Top = 135
  end
  object DSAuthenticationManager: TDSAuthenticationManager
    OnUserAuthenticate = DSAuthenticationManagerUserAuthenticate
    OnUserAuthorize = DSAuthenticationManagerUserAuthorize
    Roles = <>
    Left = 96
    Top = 197
  end
  object DscImp: TDSServerClass
    OnGetClass = DscImpGetClass
    Server = DSServer
    LifeCycle = 'Session'
    Left = 200
    Top = 19
  end
  object DscRW: TDSServerClass
    OnGetClass = DscRWGetClass
    Server = DSServer
    LifeCycle = 'Session'
    Left = 200
    Top = 67
  end
end
