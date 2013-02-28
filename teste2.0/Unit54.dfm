inherited FRMPW_GRUPO: TFRMPW_GRUPO
  Width = 499
  Height = 299
  Caption = 'FRMPW_GRUPO'
  PixelsPerInch = 96
  TextHeight = 13
  inherited Panel1: TPanel
    Width = 491
    Height = 157
    object Label1: TLabel
      Left = 48
      Top = 16
      Width = 63
      Height = 13
      Caption = 'PW_GRUPO'
      FocusControl = DBEdit1
    end
    object Label2: TLabel
      Left = 48
      Top = 56
      Width = 79
      Height = 13
      Caption = 'PW_NOGRUPO'
      FocusControl = DBEdit2
    end
    object DBEdit1: TDBEdit
      Left = 48
      Top = 32
      Width = 56
      Height = 21
      DataField = 'PW_GRUPO'
      DataSource = DataModule2.dsPWGRUPOS
      TabOrder = 0
    end
    object DBEdit2: TDBEdit
      Left = 48
      Top = 72
      Width = 199
      Height = 21
      DataField = 'PW_NOGRUPO'
      DataSource = DataModule2.dsPWGRUPOS
      TabOrder = 1
    end
  end
  inherited Panel2: TPanel
    Top = 214
    Width = 491
    inherited DBButton4: TDBButton
      Left = 400
      DataSource = DataModule2.dsPWGRUPOS
    end
    inherited DBButton5: TDBButton
      Left = 320
      Enabled = True
      DataSource = DataModule2.dsPWGRUPOS
    end
  end
  inherited Panel3: TPanel
    Width = 491
    inherited DBButton1: TDBButton
      Enabled = True
      DataSource = DataModule2.dsPWGRUPOS
    end
    inherited DBButton2: TDBButton
      Enabled = True
      DataSource = DataModule2.dsPWGRUPOS
    end
    inherited DBButton3: TDBButton
      Enabled = True
      DataSource = DataModule2.dsPWGRUPOS
    end
    inherited DBNavigator1: TDBNavigator
      DataSource = DataModule2.dsPWGRUPOS
      Hints.Strings = ()
    end
  end
end
