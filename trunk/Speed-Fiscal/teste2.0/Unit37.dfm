inherited FRMCLIENTES: TFRMCLIENTES
  Left = 22
  Top = 124
  Width = 1091
  Height = 615
  Caption = 'FRMCLIENTES'
  OldCreateOrder = True
  PixelsPerInch = 96
  TextHeight = 13
  inherited Panel1: TPanel
    Width = 952
    Height = 516
    object Label1: TLabel
      Left = 32
      Top = 8
      Width = 39
      Height = 13
      Caption = 'CODCLI'
      FocusControl = DBEdit1
    end
    object Label2: TLabel
      Left = 32
      Top = 48
      Width = 54
      Height = 13
      Caption = 'SITUACAO'
      FocusControl = DBEdit2
    end
    object Label3: TLabel
      Left = 32
      Top = 88
      Width = 43
      Height = 13
      Caption = 'STATUS'
      FocusControl = DBEdit3
    end
    object Label4: TLabel
      Left = 32
      Top = 128
      Width = 37
      Height = 13
      Caption = 'RAZAO'
      FocusControl = DBEdit4
    end
    object Label5: TLabel
      Left = 32
      Top = 168
      Width = 24
      Height = 13
      Caption = 'COM'
      FocusControl = DBEdit5
    end
    object Label6: TLabel
      Left = 32
      Top = 208
      Width = 31
      Height = 13
      Caption = 'SIGLA'
      FocusControl = DBEdit6
    end
    object Label7: TLabel
      Left = 32
      Top = 248
      Width = 52
      Height = 13
      Caption = 'FANTASIA'
      FocusControl = DBEdit7
    end
    object Label8: TLabel
      Left = 32
      Top = 288
      Width = 22
      Height = 13
      Caption = 'CGC'
      FocusControl = DBEdit8
    end
    object Label9: TLabel
      Left = 32
      Top = 328
      Width = 26
      Height = 13
      Caption = 'INCR'
      FocusControl = DBEdit9
    end
    object Label10: TLabel
      Left = 32
      Top = 368
      Width = 60
      Height = 13
      Caption = 'ENDERECO'
      FocusControl = DBEdit10
    end
    object Label11: TLabel
      Left = 32
      Top = 408
      Width = 41
      Height = 13
      Caption = 'BAIRRO'
      FocusControl = DBEdit11
    end
    object Label12: TLabel
      Left = 560
      Top = 8
      Width = 56
      Height = 13
      Caption = 'MUNICIPIO'
      FocusControl = DBEdit12
    end
    object Label13: TLabel
      Left = 560
      Top = 48
      Width = 14
      Height = 13
      Caption = 'UF'
      FocusControl = DBEdit13
    end
    object Label14: TLabel
      Left = 560
      Top = 88
      Width = 21
      Height = 13
      Caption = 'CEP'
      FocusControl = DBEdit14
    end
    object Label15: TLabel
      Left = 560
      Top = 128
      Width = 29
      Height = 13
      Caption = 'FONE'
      FocusControl = DBEdit15
    end
    object Label16: TLabel
      Left = 560
      Top = 168
      Width = 49
      Height = 13
      Caption = 'FONEFAX'
      FocusControl = DBEdit16
    end
    object Label17: TLabel
      Left = 560
      Top = 208
      Width = 49
      Height = 13
      Caption = 'FONECEL'
      FocusControl = DBEdit17
    end
    object Label18: TLabel
      Left = 560
      Top = 248
      Width = 52
      Height = 13
      Caption = 'CONTATO'
      FocusControl = DBEdit18
    end
    object Label19: TLabel
      Left = 560
      Top = 288
      Width = 32
      Height = 13
      Caption = 'EMAIL'
      FocusControl = DBEdit19
    end
    object Label20: TLabel
      Left = 560
      Top = 328
      Width = 24
      Height = 13
      Caption = 'SITE'
      FocusControl = DBEdit20
    end
    object Label21: TLabel
      Left = 560
      Top = 368
      Width = 22
      Height = 13
      Caption = 'OBS'
      FocusControl = DBMemo1
    end
    object DBEdit1: TDBEdit
      Left = 32
      Top = 24
      Width = 160
      Height = 21
      DataField = 'CODCLI'
      DataSource = DataModule2.dsCLIENTES
      TabOrder = 0
    end
    object DBEdit2: TDBEdit
      Left = 32
      Top = 64
      Width = 160
      Height = 21
      DataField = 'SITUACAO'
      DataSource = DataModule2.dsCLIENTES
      TabOrder = 1
    end
    object DBEdit3: TDBEdit
      Left = 32
      Top = 104
      Width = 238
      Height = 21
      DataField = 'STATUS'
      DataSource = DataModule2.dsCLIENTES
      TabOrder = 2
    end
    object DBEdit4: TDBEdit
      Left = 32
      Top = 144
      Width = 417
      Height = 21
      DataField = 'RAZAO'
      DataSource = DataModule2.dsCLIENTES
      TabOrder = 3
    end
    object DBEdit5: TDBEdit
      Left = 32
      Top = 184
      Width = 69
      Height = 21
      DataField = 'COM'
      DataSource = DataModule2.dsCLIENTES
      TabOrder = 4
    end
    object DBEdit6: TDBEdit
      Left = 32
      Top = 224
      Width = 95
      Height = 21
      DataField = 'SIGLA'
      DataSource = DataModule2.dsCLIENTES
      TabOrder = 5
    end
    object DBEdit7: TDBEdit
      Left = 32
      Top = 264
      Width = 409
      Height = 21
      DataField = 'FANTASIA'
      DataSource = DataModule2.dsCLIENTES
      TabOrder = 6
    end
    object DBEdit8: TDBEdit
      Left = 32
      Top = 304
      Width = 225
      Height = 21
      DataField = 'CGC'
      DataSource = DataModule2.dsCLIENTES
      TabOrder = 7
    end
    object DBEdit9: TDBEdit
      Left = 32
      Top = 344
      Width = 225
      Height = 21
      DataField = 'INCR'
      DataSource = DataModule2.dsCLIENTES
      TabOrder = 8
    end
    object DBEdit10: TDBEdit
      Left = 32
      Top = 384
      Width = 417
      Height = 21
      DataField = 'ENDERECO'
      DataSource = DataModule2.dsCLIENTES
      TabOrder = 9
    end
    object DBEdit11: TDBEdit
      Left = 32
      Top = 424
      Width = 238
      Height = 21
      DataField = 'BAIRRO'
      DataSource = DataModule2.dsCLIENTES
      TabOrder = 10
    end
    object DBEdit12: TDBEdit
      Left = 560
      Top = 24
      Width = 316
      Height = 21
      DataField = 'MUNICIPIO'
      DataSource = DataModule2.dsCLIENTES
      TabOrder = 11
    end
    object DBEdit13: TDBEdit
      Left = 560
      Top = 64
      Width = 43
      Height = 21
      DataField = 'UF'
      DataSource = DataModule2.dsCLIENTES
      TabOrder = 12
    end
    object DBEdit14: TDBEdit
      Left = 560
      Top = 104
      Width = 134
      Height = 21
      DataField = 'CEP'
      DataSource = DataModule2.dsCLIENTES
      TabOrder = 13
    end
    object DBEdit15: TDBEdit
      Left = 560
      Top = 144
      Width = 212
      Height = 21
      DataField = 'FONE'
      DataSource = DataModule2.dsCLIENTES
      TabOrder = 14
    end
    object DBEdit16: TDBEdit
      Left = 560
      Top = 184
      Width = 212
      Height = 21
      DataField = 'FONEFAX'
      DataSource = DataModule2.dsCLIENTES
      TabOrder = 15
    end
    object DBEdit17: TDBEdit
      Left = 560
      Top = 224
      Width = 212
      Height = 21
      DataField = 'FONECEL'
      DataSource = DataModule2.dsCLIENTES
      TabOrder = 16
    end
    object DBEdit18: TDBEdit
      Left = 560
      Top = 264
      Width = 355
      Height = 21
      DataField = 'CONTATO'
      DataSource = DataModule2.dsCLIENTES
      TabOrder = 17
    end
    object DBEdit19: TDBEdit
      Left = 560
      Top = 304
      Width = 356
      Height = 21
      DataField = 'EMAIL'
      DataSource = DataModule2.dsCLIENTES
      TabOrder = 18
    end
    object DBEdit20: TDBEdit
      Left = 560
      Top = 344
      Width = 358
      Height = 21
      DataField = 'SITE'
      DataSource = DataModule2.dsCLIENTES
      TabOrder = 19
    end
    object DBMemo1: TDBMemo
      Left = 560
      Top = 384
      Width = 361
      Height = 89
      DataField = 'OBS'
      DataSource = DataModule2.dsCLIENTES
      TabOrder = 20
    end
  end
  inherited Panel3: TPanel
    Width = 1083
    object Label22: TLabel
      Left = 32
      Top = 24
      Width = 164
      Height = 24
      Caption = 'Cadastro Clientes'
      Font.Charset = DEFAULT_CHARSET
      Font.Color = clWindowText
      Font.Height = -19
      Font.Name = 'MS Sans Serif'
      Font.Style = [fsBold]
      ParentFont = False
    end
  end
  inherited Panel2: TPanel
    Left = 952
    Width = 131
    Height = 516
    inherited DBButton1: TDBButton
      Enabled = True
      DataSource = DataModule2.dsCLIENTES
    end
    inherited DBButton2: TDBButton
      DataSource = DataModule2.dsCLIENTES
    end
    inherited DBButton3: TDBButton
      Enabled = True
      DataSource = DataModule2.dsCLIENTES
    end
    inherited DBButton4: TDBButton
      Enabled = True
      DataSource = DataModule2.dsCLIENTES
    end
    inherited DBButton5: TDBButton
      DataSource = DataModule2.dsCLIENTES
    end
    inherited DBNavigator1: TDBNavigator
      DataSource = DataModule2.dsCLIENTES
      Hints.Strings = ()
    end
  end
end
