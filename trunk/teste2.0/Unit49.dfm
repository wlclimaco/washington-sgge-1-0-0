inherited FRMMOVCAIXA: TFRMMOVCAIXA
  Width = 529
  Height = 394
  Caption = 'FRMMOVCAIXA'
  PixelsPerInch = 96
  TextHeight = 13
  inherited Panel1: TPanel
    Width = 521
    Height = 252
    object Label1: TLabel
      Left = 24
      Top = 8
      Width = 42
      Height = 13
      Caption = 'CODIGO'
      FocusControl = DBEdit1
    end
    object Label2: TLabel
      Left = 24
      Top = 48
      Width = 49
      Height = 13
      Caption = 'PERIODO'
      FocusControl = DBEdit2
    end
    object Label3: TLabel
      Left = 24
      Top = 88
      Width = 56
      Height = 13
      Caption = 'ANTERIOR'
      FocusControl = DBEdit3
    end
    object Label4: TLabel
      Left = 24
      Top = 128
      Width = 55
      Height = 13
      Caption = 'RECEBIDO'
      FocusControl = DBEdit4
    end
    object Label5: TLabel
      Left = 24
      Top = 168
      Width = 37
      Height = 13
      Caption = 'PAGOS'
      FocusControl = DBEdit5
    end
    object Label6: TLabel
      Left = 24
      Top = 208
      Width = 35
      Height = 13
      Caption = 'ATUAL'
      FocusControl = DBEdit6
    end
    object DBEdit1: TDBEdit
      Left = 24
      Top = 24
      Width = 134
      Height = 21
      DataField = 'CODIGO'
      DataSource = DataModule2.dsMOVCAIXA
      TabOrder = 0
    end
    object DBEdit2: TDBEdit
      Left = 24
      Top = 64
      Width = 251
      Height = 21
      DataField = 'PERIODO'
      DataSource = DataModule2.dsMOVCAIXA
      TabOrder = 1
    end
    object DBEdit3: TDBEdit
      Left = 24
      Top = 104
      Width = 134
      Height = 21
      DataField = 'ANTERIOR'
      DataSource = DataModule2.dsMOVCAIXA
      TabOrder = 2
    end
    object DBEdit4: TDBEdit
      Left = 24
      Top = 144
      Width = 134
      Height = 21
      DataField = 'RECEBIDO'
      DataSource = DataModule2.dsMOVCAIXA
      TabOrder = 3
    end
    object DBEdit5: TDBEdit
      Left = 24
      Top = 184
      Width = 134
      Height = 21
      DataField = 'PAGOS'
      DataSource = DataModule2.dsMOVCAIXA
      TabOrder = 4
    end
    object DBEdit6: TDBEdit
      Left = 24
      Top = 224
      Width = 134
      Height = 21
      DataField = 'ATUAL'
      DataSource = DataModule2.dsMOVCAIXA
      TabOrder = 5
    end
  end
  inherited Panel2: TPanel
    Top = 309
    Width = 521
    inherited DBButton4: TDBButton
      Left = 416
      DataSource = DataModule2.dsMOVCAIXA
    end
    inherited DBButton5: TDBButton
      Left = 328
      Enabled = True
      DataSource = DataModule2.dsMOVCAIXA
    end
  end
  inherited Panel3: TPanel
    Width = 521
    inherited DBButton1: TDBButton
      Enabled = True
      DataSource = DataModule2.dsMOVCAIXA
    end
    inherited DBButton2: TDBButton
      Enabled = True
      DataSource = DataModule2.dsMOVCAIXA
    end
    inherited DBButton3: TDBButton
      Enabled = True
      DataSource = DataModule2.dsMOVCAIXA
    end
    inherited DBNavigator1: TDBNavigator
      DataSource = DataModule2.dsMOVCAIXA
      Hints.Strings = ()
    end
  end
end
