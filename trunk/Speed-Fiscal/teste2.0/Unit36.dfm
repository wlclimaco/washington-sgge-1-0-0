inherited FRMCAR: TFRMCAR
  Left = 125
  Top = 170
  Width = 881
  Height = 614
  Caption = 'FRMCAR'
  OldCreateOrder = True
  PixelsPerInch = 96
  TextHeight = 13
  inherited Panel1: TPanel
    Width = 873
    Height = 472
    object Label1: TLabel
      Left = 24
      Top = 8
      Width = 43
      Height = 13
      Caption = 'CODPLC'
      FocusControl = DBEdit1
    end
    object Label2: TLabel
      Left = 24
      Top = 48
      Width = 62
      Height = 13
      Caption = 'DESCRICAO'
      FocusControl = DBEdit2
    end
    object Label3: TLabel
      Left = 24
      Top = 88
      Width = 38
      Height = 13
      Caption = 'ESTILO'
      FocusControl = DBEdit3
    end
    object Label4: TLabel
      Left = 24
      Top = 128
      Width = 39
      Height = 13
      Caption = 'CODCLI'
      FocusControl = DBEdit4
    end
    object Label5: TLabel
      Left = 24
      Top = 168
      Width = 24
      Height = 13
      Caption = 'COM'
      FocusControl = DBEdit5
    end
    object Label6: TLabel
      Left = 24
      Top = 208
      Width = 37
      Height = 13
      Caption = 'RAZAO'
      FocusControl = DBEdit6
    end
    object Label7: TLabel
      Left = 24
      Top = 248
      Width = 46
      Height = 13
      Caption = 'CODEMP'
      FocusControl = DBEdit7
    end
    object Label8: TLabel
      Left = 24
      Top = 288
      Width = 31
      Height = 13
      Caption = 'SIGLA'
      FocusControl = DBEdit8
    end
    object Label9: TLabel
      Left = 24
      Top = 328
      Width = 56
      Height = 13
      Caption = 'COMISSAO'
      FocusControl = DBEdit9
    end
    object Label10: TLabel
      Left = 24
      Top = 368
      Width = 30
      Height = 13
      Caption = 'PGTO'
      FocusControl = DBEdit10
    end
    object Label11: TLabel
      Left = 24
      Top = 408
      Width = 47
      Height = 13
      Caption = 'DOCESTI'
      FocusControl = DBEdit11
    end
    object Label12: TLabel
      Left = 472
      Top = 8
      Width = 48
      Height = 13
      Caption = 'DOCNUM'
      FocusControl = DBEdit12
    end
    object Label13: TLabel
      Left = 472
      Top = 48
      Width = 53
      Height = 13
      Caption = 'DOCSEQU'
      FocusControl = DBEdit13
    end
    object Label14: TLabel
      Left = 472
      Top = 88
      Width = 22
      Height = 13
      Caption = 'OBS'
      FocusControl = DBEdit14
    end
    object Label15: TLabel
      Left = 472
      Top = 128
      Width = 50
      Height = 13
      Caption = 'DATLANC'
      FocusControl = DBEdit15
    end
    object Label16: TLabel
      Left = 472
      Top = 168
      Width = 48
      Height = 13
      Caption = 'DATEMIS'
      FocusControl = DBEdit16
    end
    object Label17: TLabel
      Left = 472
      Top = 208
      Width = 46
      Height = 13
      Caption = 'VALEMIS'
      FocusControl = DBEdit17
    end
    object Label18: TLabel
      Left = 472
      Top = 248
      Width = 51
      Height = 13
      Caption = 'DATVENC'
      FocusControl = DBEdit18
    end
    object Label19: TLabel
      Left = 472
      Top = 288
      Width = 51
      Height = 13
      Caption = 'DATRCBT'
      FocusControl = DBEdit19
    end
    object Label20: TLabel
      Left = 472
      Top = 328
      Width = 49
      Height = 13
      Caption = 'VALRCBT'
      FocusControl = DBEdit20
    end
    object Label21: TLabel
      Left = 472
      Top = 368
      Width = 51
      Height = 13
      Caption = 'VALCOMS'
      FocusControl = DBEdit21
    end
    object DBEdit1: TDBEdit
      Left = 24
      Top = 24
      Width = 95
      Height = 21
      DataField = 'CODPLC'
      DataSource = DataModule2.dsCAR
      TabOrder = 0
    end
    object DBEdit2: TDBEdit
      Left = 24
      Top = 64
      Width = 238
      Height = 21
      DataField = 'DESCRICAO'
      DataSource = DataModule2.dsCAR
      TabOrder = 1
    end
    object DBEdit3: TDBEdit
      Left = 24
      Top = 104
      Width = 17
      Height = 21
      DataField = 'ESTILO'
      DataSource = DataModule2.dsCAR
      TabOrder = 2
    end
    object DBEdit4: TDBEdit
      Left = 24
      Top = 144
      Width = 134
      Height = 21
      DataField = 'CODCLI'
      DataSource = DataModule2.dsCAR
      TabOrder = 3
    end
    object DBEdit5: TDBEdit
      Left = 24
      Top = 184
      Width = 43
      Height = 21
      DataField = 'COM'
      DataSource = DataModule2.dsCAR
      TabOrder = 4
    end
    object DBEdit6: TDBEdit
      Left = 24
      Top = 224
      Width = 417
      Height = 21
      DataField = 'RAZAO'
      DataSource = DataModule2.dsCAR
      TabOrder = 5
    end
    object DBEdit7: TDBEdit
      Left = 24
      Top = 264
      Width = 134
      Height = 21
      DataField = 'CODEMP'
      DataSource = DataModule2.dsCAR
      TabOrder = 6
    end
    object DBEdit8: TDBEdit
      Left = 24
      Top = 304
      Width = 69
      Height = 21
      DataField = 'SIGLA'
      DataSource = DataModule2.dsCAR
      TabOrder = 7
    end
    object DBEdit9: TDBEdit
      Left = 24
      Top = 344
      Width = 134
      Height = 21
      DataField = 'COMISSAO'
      DataSource = DataModule2.dsCAR
      TabOrder = 8
    end
    object DBEdit10: TDBEdit
      Left = 24
      Top = 384
      Width = 134
      Height = 21
      DataField = 'PGTO'
      DataSource = DataModule2.dsCAR
      TabOrder = 9
    end
    object DBEdit11: TDBEdit
      Left = 24
      Top = 424
      Width = 30
      Height = 21
      DataField = 'DOCESTI'
      DataSource = DataModule2.dsCAR
      TabOrder = 10
    end
    object DBEdit12: TDBEdit
      Left = 472
      Top = 24
      Width = 134
      Height = 21
      DataField = 'DOCNUM'
      DataSource = DataModule2.dsCAR
      TabOrder = 11
    end
    object DBEdit13: TDBEdit
      Left = 472
      Top = 64
      Width = 17
      Height = 21
      DataField = 'DOCSEQU'
      DataSource = DataModule2.dsCAR
      TabOrder = 12
    end
    object DBEdit14: TDBEdit
      Left = 472
      Top = 104
      Width = 394
      Height = 21
      DataField = 'OBS'
      DataSource = DataModule2.dsCAR
      TabOrder = 13
    end
    object DBEdit15: TDBEdit
      Left = 472
      Top = 144
      Width = 134
      Height = 21
      DataField = 'DATLANC'
      DataSource = DataModule2.dsCAR
      TabOrder = 14
    end
    object DBEdit16: TDBEdit
      Left = 472
      Top = 184
      Width = 134
      Height = 21
      DataField = 'DATEMIS'
      DataSource = DataModule2.dsCAR
      TabOrder = 15
    end
    object DBEdit17: TDBEdit
      Left = 472
      Top = 224
      Width = 134
      Height = 21
      DataField = 'VALEMIS'
      DataSource = DataModule2.dsCAR
      TabOrder = 16
    end
    object DBEdit18: TDBEdit
      Left = 472
      Top = 264
      Width = 134
      Height = 21
      DataField = 'DATVENC'
      DataSource = DataModule2.dsCAR
      TabOrder = 17
    end
    object DBEdit19: TDBEdit
      Left = 472
      Top = 304
      Width = 134
      Height = 21
      DataField = 'DATRCBT'
      DataSource = DataModule2.dsCAR
      TabOrder = 18
    end
    object DBEdit20: TDBEdit
      Left = 472
      Top = 344
      Width = 134
      Height = 21
      DataField = 'VALRCBT'
      DataSource = DataModule2.dsCAR
      TabOrder = 19
    end
    object DBEdit21: TDBEdit
      Left = 472
      Top = 384
      Width = 134
      Height = 21
      DataField = 'VALCOMS'
      DataSource = DataModule2.dsCAR
      TabOrder = 20
    end
  end
  inherited Panel2: TPanel
    Top = 529
    Width = 873
    inherited DBButton4: TDBButton
      Left = 768
      DataSource = DataModule2.dsCAR
    end
    inherited DBButton5: TDBButton
      Left = 688
      Enabled = True
      DataSource = DataModule2.dsCAR
    end
  end
  inherited Panel3: TPanel
    Width = 873
    inherited DBButton1: TDBButton
      Enabled = True
      DataSource = DataModule2.dsCAR
    end
    inherited DBButton2: TDBButton
      Enabled = True
      DataSource = DataModule2.dsCAR
    end
    inherited DBButton3: TDBButton
      Enabled = True
      DataSource = DataModule2.dsCAR
    end
    inherited DBNavigator1: TDBNavigator
      DataSource = DataModule2.dsCAR
      Hints.Strings = ()
    end
  end
end
