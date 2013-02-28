object DataModule1: TDataModule1
  OldCreateOrder = False
  Left = 371
  Top = 273
  Height = 342
  Width = 383
  object DBPrincipal: TIBDatabase
    Connected = True
    DatabaseName = 'C:\NOVA.GDB'
    Params.Strings = (
      'user_name=sysdba'
      'password=masterkey'
      'lc_ctype=ASCII')
    LoginPrompt = False
    DefaultTransaction = IBTransaction1
    IdleTimer = 0
    SQLDialect = 3
    TraceFlags = []
    Left = 38
    Top = 17
  end
  object IBTransaction1: TIBTransaction
    Active = True
    DefaultDatabase = DBPrincipal
    AutoStopAction = saNone
    Left = 35
    Top = 74
  end
  object qryprodutos1: TIBQuery
    Database = DBPrincipal
    Transaction = IBTransaction1
    BufferChunks = 1000
    CachedUpdates = False
    Left = 106
    Top = 24
  end
  object qryMovEstoque: TIBQuery
    Database = DBPrincipal
    Transaction = IBTransaction1
    BufferChunks = 1000
    CachedUpdates = False
    Left = 103
    Top = 81
  end
end
