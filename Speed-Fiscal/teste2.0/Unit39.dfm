inherited FRMCXBANCOS: TFRMCXBANCOS
  Width = 503
  Height = 339
  Caption = 'FRMCXBANCOS'
  OldCreateOrder = True
  PixelsPerInch = 96
  TextHeight = 13
  inherited Panel1: TPanel
    Width = 495
    Height = 197
    object Label1: TLabel
      Left = 48
      Top = 24
      Width = 45
      Height = 13
      Caption = 'CODBAN'
      FocusControl = DBEdit1
    end
    object Label2: TLabel
      Left = 48
      Top = 64
      Width = 37
      Height = 13
      Caption = 'BANCO'
      FocusControl = DBEdit2
    end
    object Label3: TLabel
      Left = 48
      Top = 104
      Width = 37
      Height = 13
      Caption = 'AGENC'
      FocusControl = DBEdit3
    end
    object Label4: TLabel
      Left = 48
      Top = 144
      Width = 44
      Height = 13
      Caption = 'CCONTA'
      FocusControl = DBEdit4
    end
    object DBEdit1: TDBEdit
      Left = 48
      Top = 40
      Width = 134
      Height = 21
      DataField = 'CODBAN'
      DataSource = DataModule2.dsCXBANCOS
      TabOrder = 0
    end
    object DBEdit2: TDBEdit
      Left = 48
      Top = 80
      Width = 134
      Height = 21
      DataField = 'BANCO'
      DataSource = DataModule2.dsCXBANCOS
      TabOrder = 1
    end
    object DBEdit3: TDBEdit
      Left = 48
      Top = 120
      Width = 108
      Height = 21
      DataField = 'AGENC'
      DataSource = DataModule2.dsCXBANCOS
      TabOrder = 2
    end
    object DBEdit4: TDBEdit
      Left = 48
      Top = 160
      Width = 134
      Height = 21
      DataField = 'CCONTA'
      DataSource = DataModule2.dsCXBANCOS
      TabOrder = 3
    end
  end
  inherited Panel2: TPanel
    Top = 254
    Width = 495
    inherited DBButton4: TDBButton
      Left = 400
      DataSource = DataModule2.dsCXBANCOS
    end
    inherited DBButton5: TDBButton
      Left = 312
      Enabled = True
      DataSource = DataModule2.dsCXBANCOS
    end
  end
  inherited Panel3: TPanel
    Width = 495
    inherited DBButton1: TDBButton
      Enabled = True
      DataSource = DataModule2.dsCXBANCOS
    end
    inherited DBButton2: TDBButton
      Enabled = True
      DataSource = DataModule2.dsCXBANCOS
    end
    inherited DBButton3: TDBButton
      Enabled = True
      DataSource = DataModule2.dsCXBANCOS
    end
    inherited DBNavigator1: TDBNavigator
      DataSource = DataModule2.dsCXBANCOS
      Hints.Strings = ()
    end
  end
end
