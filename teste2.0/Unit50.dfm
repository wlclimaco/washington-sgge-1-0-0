inherited FRMPLCONTA: TFRMPLCONTA
  Width = 496
  Height = 282
  Caption = 'FRMPLCONTA'
  PixelsPerInch = 96
  TextHeight = 13
  inherited Panel1: TPanel
    Width = 488
    Height = 140
    object Label1: TLabel
      Left = 24
      Top = 16
      Width = 43
      Height = 13
      Caption = 'CODPLC'
      FocusControl = DBEdit1
    end
    object Label2: TLabel
      Left = 24
      Top = 56
      Width = 62
      Height = 13
      Caption = 'DESCRICAO'
      FocusControl = DBEdit2
    end
    object Label3: TLabel
      Left = 24
      Top = 96
      Width = 38
      Height = 13
      Caption = 'ESTILO'
      FocusControl = DBEdit3
    end
    object DBEdit1: TDBEdit
      Left = 24
      Top = 32
      Width = 173
      Height = 21
      DataField = 'CODPLC'
      DataSource = DataModule2.dsDSPLCONTA
      TabOrder = 0
    end
    object DBEdit2: TDBEdit
      Left = 24
      Top = 72
      Width = 290
      Height = 21
      DataField = 'DESCRICAO'
      DataSource = DataModule2.dsDSPLCONTA
      TabOrder = 1
    end
    object DBEdit3: TDBEdit
      Left = 24
      Top = 112
      Width = 108
      Height = 21
      DataField = 'ESTILO'
      DataSource = DataModule2.dsDSPLCONTA
      TabOrder = 2
    end
  end
  inherited Panel2: TPanel
    Top = 197
    Width = 488
    inherited DBButton4: TDBButton
      Left = 400
      DataSource = DataModule2.dsDSPLCONTA
    end
    inherited DBButton5: TDBButton
      Left = 320
      Enabled = True
      DataSource = DataModule2.dsDSPLCONTA
    end
  end
  inherited Panel3: TPanel
    Width = 488
    inherited DBButton1: TDBButton
      Enabled = True
      DataSource = DataModule2.dsDSPLCONTA
    end
    inherited DBButton2: TDBButton
      Enabled = True
      DataSource = DataModule2.dsDSPLCONTA
    end
    inherited DBButton3: TDBButton
      Enabled = True
      DataSource = DataModule2.dsDSPLCONTA
    end
    inherited DBNavigator1: TDBNavigator
      DataSource = DataModule2.dsDSPLCONTA
      Hints.Strings = ()
    end
  end
end
