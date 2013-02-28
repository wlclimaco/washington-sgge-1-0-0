object DataModule3: TDataModule3
  OldCreateOrder = False
  Left = 469
  Top = 314
  Height = 289
  Width = 310
  object IBDatabase1: TIBDatabase
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
    Left = 32
    Top = 16
  end
  object IBTransaction1: TIBTransaction
    Active = False
    DefaultDatabase = IBDatabase1
    AutoStopAction = saNone
    Left = 32
    Top = 72
  end
  object qryprodutos1: TIBQuery
    Database = IBDatabase1
    Transaction = IBTransaction1
    BufferChunks = 1000
    CachedUpdates = False
    Left = 104
    Top = 16
  end
  object qryMovEstoque: TIBQuery
    Database = IBDatabase1
    Transaction = IBTransaction1
    BufferChunks = 1000
    CachedUpdates = False
    Left = 111
    Top = 82
  end
  object IBQuery1: TIBQuery
    Database = IBDatabase1
    Transaction = IBTransaction1
    BufferChunks = 1000
    CachedUpdates = False
    SQL.Strings = (
      'select  *from CLIENTES_FORNECEDOR')
    Left = 181
    Top = 81
  end
end
