inherited FRMINDICE: TFRMINDICE
  Width = 506
  Height = 313
  Caption = 'FRMINDICE'
  PixelsPerInch = 96
  TextHeight = 13
  inherited Panel1: TPanel
    Width = 498
    Height = 171
    object Label1: TLabel
      Left = 24
      Top = 8
      Width = 42
      Height = 13
      Caption = 'CODIND'
      FocusControl = DBEdit1
    end
    object Label2: TLabel
      Left = 24
      Top = 48
      Width = 36
      Height = 13
      Caption = 'INDICE'
      FocusControl = DBEdit2
    end
    object Label3: TLabel
      Left = 24
      Top = 88
      Width = 36
      Height = 13
      Caption = 'VALOR'
      FocusControl = DBEdit3
    end
    object Label4: TLabel
      Left = 24
      Top = 128
      Width = 29
      Height = 13
      Caption = 'DATA'
      FocusControl = DBEdit4
    end
    object DBEdit1: TDBEdit
      Left = 24
      Top = 24
      Width = 134
      Height = 21
      DataField = 'CODIND'
      DataSource = DataModule2.dsINDICE
      TabOrder = 0
    end
    object DBEdit2: TDBEdit
      Left = 24
      Top = 64
      Width = 82
      Height = 21
      DataField = 'INDICE'
      DataSource = DataModule2.dsINDICE
      TabOrder = 1
    end
    object DBEdit3: TDBEdit
      Left = 24
      Top = 104
      Width = 134
      Height = 21
      DataField = 'VALOR'
      DataSource = DataModule2.dsINDICE
      TabOrder = 2
    end
    object DBEdit4: TDBEdit
      Left = 24
      Top = 144
      Width = 134
      Height = 21
      DataField = 'DATA'
      DataSource = DataModule2.dsINDICE
      TabOrder = 3
    end
  end
  inherited Panel2: TPanel
    Top = 228
    Width = 498
    inherited DBButton4: TDBButton
      Left = 408
      DataSource = DataModule2.dsINDICE
    end
    inherited DBButton5: TDBButton
      Left = 328
      Enabled = True
      DataSource = DataModule2.dsINDICE
    end
  end
  inherited Panel3: TPanel
    Width = 498
    inherited DBButton1: TDBButton
      Enabled = True
      DataSource = DataModule2.dsINDICE
    end
    inherited DBButton2: TDBButton
      Enabled = True
      DataSource = DataModule2.dsINDICE
    end
    inherited DBButton3: TDBButton
      Enabled = True
      DataSource = DataModule2.dsINDICE
    end
    inherited DBNavigator1: TDBNavigator
      DataSource = DataModule2.dsINDICE
      Hints.Strings = ()
    end
  end
end
