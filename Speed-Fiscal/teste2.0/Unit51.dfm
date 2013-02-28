inherited FRMXMATERIA: TFRMXMATERIA
  Width = 543
  Height = 396
  Caption = 'FRMXMATERIA'
  PixelsPerInch = 96
  TextHeight = 13
  inherited Panel1: TPanel
    Width = 535
    Height = 254
    object Label1: TLabel
      Left = 32
      Top = 8
      Width = 46
      Height = 13
      Caption = 'CODMAT'
      FocusControl = DBEdit1
    end
    object Label2: TLabel
      Left = 32
      Top = 48
      Width = 54
      Height = 13
      Caption = 'PRODUTO'
      FocusControl = DBEdit2
    end
    object Label3: TLabel
      Left = 32
      Top = 88
      Width = 21
      Height = 13
      Caption = 'REF'
      FocusControl = DBEdit3
    end
    object Label4: TLabel
      Left = 32
      Top = 128
      Width = 34
      Height = 13
      Caption = 'CLASS'
      FocusControl = DBEdit4
    end
    object Label5: TLabel
      Left = 32
      Top = 168
      Width = 49
      Height = 13
      Caption = 'UNIDADE'
      FocusControl = DBEdit5
    end
    object Label6: TLabel
      Left = 32
      Top = 208
      Width = 34
      Height = 13
      Caption = 'LOCAL'
      FocusControl = DBEdit6
    end
    object DBEdit1: TDBEdit
      Left = 32
      Top = 24
      Width = 160
      Height = 21
      DataField = 'CODMAT'
      DataSource = DataModule2.dsXMATERIA
      TabOrder = 0
    end
    object DBEdit2: TDBEdit
      Left = 32
      Top = 64
      Width = 485
      Height = 21
      DataField = 'PRODUTO'
      DataSource = DataModule2.dsXMATERIA
      TabOrder = 1
    end
    object DBEdit3: TDBEdit
      Left = 32
      Top = 104
      Width = 238
      Height = 21
      DataField = 'REF'
      DataSource = DataModule2.dsXMATERIA
      TabOrder = 2
    end
    object DBEdit4: TDBEdit
      Left = 32
      Top = 144
      Width = 160
      Height = 21
      DataField = 'CLASS'
      DataSource = DataModule2.dsXMATERIA
      TabOrder = 3
    end
    object DBEdit5: TDBEdit
      Left = 32
      Top = 184
      Width = 134
      Height = 21
      DataField = 'UNIDADE'
      DataSource = DataModule2.dsXMATERIA
      TabOrder = 4
    end
    object DBEdit6: TDBEdit
      Left = 32
      Top = 224
      Width = 95
      Height = 21
      DataField = 'LOCAL'
      DataSource = DataModule2.dsXMATERIA
      TabOrder = 5
    end
  end
  inherited Panel2: TPanel
    Top = 311
    Width = 535
    inherited DBButton4: TDBButton
      Left = 448
      DataSource = DataModule2.dsXMATERIA
    end
    inherited DBButton5: TDBButton
      Left = 360
      Enabled = True
      DataSource = DataModule2.dsXMATERIA
    end
  end
  inherited Panel3: TPanel
    Width = 535
    inherited DBButton1: TDBButton
      Enabled = True
      DataSource = DataModule2.dsXMATERIA
    end
    inherited DBButton2: TDBButton
      Enabled = True
      DataSource = DataModule2.dsXMATERIA
    end
    inherited DBButton3: TDBButton
      Enabled = True
      DataSource = DataModule2.dsXMATERIA
    end
    inherited DBNavigator1: TDBNavigator
      DataSource = DataModule2.dsXMATERIA
      Hints.Strings = ()
    end
  end
end
