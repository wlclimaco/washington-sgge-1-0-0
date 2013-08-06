object DmDicion: TDmDicion
  OldCreateOrder = False
  OnCreate = DataModuleCreate
  Height = 236
  Width = 368
  object BrvDicionario: TBrvDicionario
    SQLConnection = SqlDicion
    UserCode = 0
    UserGroupAdm = False
    AlterConfirm = False
    NumeroAcesso = 0
    CodigoBanco = 0
    Camada = Server
    TipoBancoDados = BcoOracle
    FormatoDataBanco = 'dd/mm/yyyy'
    VersaoCliente = 0
    VersaoClienteSub = 0
    VersaoServidor = 2
    VersaoServidorSub = 1
    Left = 24
    Top = 16
  end
  object SqlDicion: TBrvConnection
    DriverName = 'ORACLE'
    GetDriverFunc = 'getSQLDriverORACLE'
    LibraryName = 'dbxora.dll'
    LoginPrompt = False
    Params.Strings = (
      'drivername=ORACLE'
      'Database=dbvital'
      'User_Name=vitalsis'
      'Password=vital'
      'blobsize=-1'
      'localecode=0000'
      'isolationlevel=ReadCommitted'
      'rowsetsize=20'
      'os authentication=False'
      'multiple transaction=False'
      'trim char=False'
      'decimal separator=.')
    TableScope = [tsTable]
    VendorLib = 'oci.dll'
    AfterConnect = SqlDicionAfterConnect
    Left = 110
    Top = 14
  end
end
