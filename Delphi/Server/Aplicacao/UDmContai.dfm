object DmContai: TDmContai
  OldCreateOrder = False
  OnCreate = DataModuleCreate
  Height = 371
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
  object DSAuthenticationManager: TDSAuthenticationManager
    OnUserAuthenticate = DSAuthenticationManagerUserAuthenticate
    OnUserAuthorize = DSAuthenticationManagerUserAuthorize
    Roles = <>
    Left = 96
    Top = 125
  end
  object DscLogos: TDSServerClass
    OnGetClass = DscLogosGetClass
    Server = DSServer
    LifeCycle = 'Session'
    Left = 200
    Top = 9
  end
  object DscRW: TDSServerClass
    OnGetClass = DscRWGetClass
    Server = DSServer
    LifeCycle = 'Session'
    Left = 200
    Top = 64
  end
  object DscSis: TDSServerClass
    OnGetClass = DscSisGetClass
    Server = DSServer
    LifeCycle = 'Session'
    Left = 200
    Top = 120
  end
  object DscTra: TDSServerClass
    OnGetClass = DscTraGetClass
    Server = DSServer
    LifeCycle = 'Session'
    Left = 256
    Top = 9
  end
  object DscCon: TDSServerClass
    OnGetClass = DscConGetClass
    Server = DSServer
    LifeCycle = 'Session'
    Left = 256
    Top = 64
  end
  object DscWms: TDSServerClass
    OnGetClass = DscWmsGetClass
    Server = DSServer
    LifeCycle = 'Session'
    Left = 200
    Top = 176
  end
  object DscAdm: TDSServerClass
    OnGetClass = DscAdmGetClass
    Server = DSServer
    LifeCycle = 'Session'
    Left = 256
    Top = 176
  end
end
