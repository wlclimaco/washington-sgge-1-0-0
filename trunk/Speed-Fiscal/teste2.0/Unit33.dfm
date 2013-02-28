inherited FRMBANCOS: TFRMBANCOS
  Caption = 'FRMBANCOS'
  OldCreateOrder = True
  PixelsPerInch = 96
  TextHeight = 13
  inherited Panel1: TPanel
    object Label1: TLabel
      Left = 24
      Top = 8
      Width = 45
      Height = 13
      Caption = 'CODBAN'
      FocusControl = DBEdit1
    end
    object Label2: TLabel
      Left = 24
      Top = 48
      Width = 37
      Height = 13
      Caption = 'BANCO'
      FocusControl = DBEdit2
    end
    object Label3: TLabel
      Left = 24
      Top = 88
      Width = 37
      Height = 13
      Caption = 'AGENC'
      FocusControl = DBEdit3
    end
    object Label4: TLabel
      Left = 24
      Top = 128
      Width = 44
      Height = 13
      Caption = 'CCONTA'
      FocusControl = DBEdit4
    end
    object Label5: TLabel
      Left = 24
      Top = 168
      Width = 28
      Height = 13
      Caption = 'LIMIT'
      FocusControl = DBEdit5
    end
    object Label6: TLabel
      Left = 24
      Top = 208
      Width = 37
      Height = 13
      Caption = 'SDANT'
      FocusControl = DBEdit6
    end
    object Label7: TLabel
      Left = 24
      Top = 248
      Width = 37
      Height = 13
      Caption = 'DEPOS'
      FocusControl = DBEdit7
    end
    object Label8: TLabel
      Left = 24
      Top = 288
      Width = 33
      Height = 13
      Caption = 'RETIR'
      FocusControl = DBEdit8
    end
    object Label9: TLabel
      Left = 24
      Top = 328
      Width = 37
      Height = 13
      Caption = 'SDATU'
      FocusControl = DBEdit9
    end
    object DBEdit1: TDBEdit
      Left = 24
      Top = 24
      Width = 134
      Height = 21
      DataField = 'CODBAN'
      DataSource = DataModule2.dsBANCOS
      TabOrder = 0
    end
    object DBEdit2: TDBEdit
      Left = 24
      Top = 64
      Width = 134
      Height = 21
      DataField = 'BANCO'
      DataSource = DataModule2.dsBANCOS
      TabOrder = 1
    end
    object DBEdit3: TDBEdit
      Left = 24
      Top = 104
      Width = 108
      Height = 21
      DataField = 'AGENC'
      DataSource = DataModule2.dsBANCOS
      TabOrder = 2
    end
    object DBEdit4: TDBEdit
      Left = 24
      Top = 144
      Width = 134
      Height = 21
      DataField = 'CCONTA'
      DataSource = DataModule2.dsBANCOS
      TabOrder = 3
    end
    object DBEdit5: TDBEdit
      Left = 24
      Top = 184
      Width = 134
      Height = 21
      DataField = 'LIMIT'
      DataSource = DataModule2.dsBANCOS
      TabOrder = 4
    end
    object DBEdit6: TDBEdit
      Left = 24
      Top = 224
      Width = 134
      Height = 21
      DataField = 'SDANT'
      DataSource = DataModule2.dsBANCOS
      TabOrder = 5
    end
    object DBEdit7: TDBEdit
      Left = 24
      Top = 264
      Width = 134
      Height = 21
      DataField = 'DEPOS'
      DataSource = DataModule2.dsBANCOS
      TabOrder = 6
    end
    object DBEdit8: TDBEdit
      Left = 24
      Top = 304
      Width = 134
      Height = 21
      DataField = 'RETIR'
      DataSource = DataModule2.dsBANCOS
      TabOrder = 7
    end
    object DBEdit9: TDBEdit
      Left = 24
      Top = 344
      Width = 134
      Height = 21
      DataField = 'SDATU'
      DataSource = DataModule2.dsBANCOS
      TabOrder = 8
    end
  end
  inherited Panel2: TPanel
    inherited DBButton4: TDBButton
      DataSource = DataModule2.dsBANCOS
    end
    inherited DBButton5: TDBButton
      Enabled = True
      DataSource = DataModule2.dsBANCOS
    end
  end
  inherited Panel3: TPanel
    inherited DBButton1: TDBButton
      Enabled = True
      DataSource = DataModule2.dsBANCOS
    end
    inherited DBButton2: TDBButton
      Enabled = True
      DataSource = DataModule2.dsBANCOS
    end
    inherited DBButton3: TDBButton
      Enabled = True
      DataSource = DataModule2.dsBANCOS
    end
    inherited DBNavigator1: TDBNavigator
      DataSource = DataModule2.dsBANCOS
      Hints.Strings = ()
    end
  end
end
