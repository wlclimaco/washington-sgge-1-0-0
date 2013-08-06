object ServerContainer2: TServerContainer2
  OldCreateOrder = False
  DisplayName = 'ServerContainer2'
  OnStart = ServiceStart
  Height = 271
  Width = 415
  object DSServer: TDSServer
    AutoStart = True
    HideDSAdmin = False
    Left = 96
    Top = 11
  end
  object DSTCPServerTransport: TDSTCPServerTransport
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
    HttpPort = 8080
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
  object DSServerClass: TDSServerClass
    OnGetClass = DSServerClassGetClass
    Server = DSServer
    LifeCycle = 'Session'
    Left = 200
    Top = 11
  end
end
