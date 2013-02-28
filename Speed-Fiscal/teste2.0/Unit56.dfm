inherited FRMTRASNF: TFRMTRASNF
  Height = 598
  Caption = 'FRMTRASNF'
  PixelsPerInch = 96
  TextHeight = 13
  inherited Panel1: TPanel
    Height = 456
    object Label1: TLabel
      Left = 16
      Top = 8
      Width = 45
      Height = 13
      Caption = 'CODTRA'
      FocusControl = DBEdit1
    end
    object Label2: TLabel
      Left = 16
      Top = 48
      Width = 54
      Height = 13
      Caption = 'PRODUTO'
      FocusControl = DBEdit2
    end
    object Label3: TLabel
      Left = 16
      Top = 88
      Width = 21
      Height = 13
      Caption = 'REF'
      FocusControl = DBEdit3
    end
    object Label4: TLabel
      Left = 16
      Top = 128
      Width = 46
      Height = 13
      Caption = 'CODPRO'
      FocusControl = DBEdit4
    end
    object Label5: TLabel
      Left = 16
      Top = 168
      Width = 32
      Height = 13
      Caption = 'LOJA1'
      FocusControl = DBEdit5
    end
    object Label6: TLabel
      Left = 16
      Top = 208
      Width = 36
      Height = 13
      Caption = 'ENTR1'
      FocusControl = DBEdit6
    end
    object Label7: TLabel
      Left = 16
      Top = 248
      Width = 31
      Height = 13
      Caption = 'SAID1'
      FocusControl = DBEdit7
    end
    object Label8: TLabel
      Left = 16
      Top = 288
      Width = 35
      Height = 13
      Caption = 'ESTQ1'
      FocusControl = DBEdit8
    end
    object Label9: TLabel
      Left = 16
      Top = 328
      Width = 32
      Height = 13
      Caption = 'LOJA2'
      FocusControl = DBEdit9
    end
    object Label10: TLabel
      Left = 16
      Top = 368
      Width = 36
      Height = 13
      Caption = 'ENTR2'
      FocusControl = DBEdit10
    end
    object Label11: TLabel
      Left = 16
      Top = 408
      Width = 31
      Height = 13
      Caption = 'SAID2'
      FocusControl = DBEdit11
    end
    object Label12: TLabel
      Left = 432
      Top = 16
      Width = 35
      Height = 13
      Caption = 'ESTQ2'
      FocusControl = DBEdit12
    end
    object Label13: TLabel
      Left = 432
      Top = 56
      Width = 32
      Height = 13
      Caption = 'LOJA3'
      FocusControl = DBEdit13
    end
    object Label14: TLabel
      Left = 432
      Top = 96
      Width = 36
      Height = 13
      Caption = 'ENTR3'
      FocusControl = DBEdit14
    end
    object Label15: TLabel
      Left = 432
      Top = 136
      Width = 31
      Height = 13
      Caption = 'SAID3'
      FocusControl = DBEdit15
    end
    object Label16: TLabel
      Left = 432
      Top = 176
      Width = 35
      Height = 13
      Caption = 'ESTQ3'
      FocusControl = DBEdit16
    end
    object Label17: TLabel
      Left = 432
      Top = 216
      Width = 29
      Height = 13
      Caption = 'DATA'
      FocusControl = DBEdit17
    end
    object DBEdit1: TDBEdit
      Left = 16
      Top = 24
      Width = 134
      Height = 21
      DataField = 'CODTRA'
      DataSource = DataModule2.dsTRASFER
      TabOrder = 0
    end
    object DBEdit2: TDBEdit
      Left = 16
      Top = 64
      Width = 199
      Height = 21
      DataField = 'PRODUTO'
      DataSource = DataModule2.dsTRASFER
      TabOrder = 1
    end
    object DBEdit3: TDBEdit
      Left = 16
      Top = 104
      Width = 199
      Height = 21
      DataField = 'REF'
      DataSource = DataModule2.dsTRASFER
      TabOrder = 2
    end
    object DBEdit4: TDBEdit
      Left = 16
      Top = 144
      Width = 134
      Height = 21
      DataField = 'CODPRO'
      DataSource = DataModule2.dsTRASFER
      TabOrder = 3
    end
    object DBEdit5: TDBEdit
      Left = 16
      Top = 184
      Width = 82
      Height = 21
      DataField = 'LOJA1'
      DataSource = DataModule2.dsTRASFER
      TabOrder = 4
    end
    object DBEdit6: TDBEdit
      Left = 16
      Top = 224
      Width = 134
      Height = 21
      DataField = 'ENTR1'
      DataSource = DataModule2.dsTRASFER
      TabOrder = 5
    end
    object DBEdit7: TDBEdit
      Left = 16
      Top = 264
      Width = 134
      Height = 21
      DataField = 'SAID1'
      DataSource = DataModule2.dsTRASFER
      TabOrder = 6
    end
    object DBEdit8: TDBEdit
      Left = 16
      Top = 304
      Width = 134
      Height = 21
      DataField = 'ESTQ1'
      DataSource = DataModule2.dsTRASFER
      TabOrder = 7
    end
    object DBEdit9: TDBEdit
      Left = 16
      Top = 344
      Width = 82
      Height = 21
      DataField = 'LOJA2'
      DataSource = DataModule2.dsTRASFER
      TabOrder = 8
    end
    object DBEdit10: TDBEdit
      Left = 16
      Top = 384
      Width = 134
      Height = 21
      DataField = 'ENTR2'
      DataSource = DataModule2.dsTRASFER
      TabOrder = 9
    end
    object DBEdit11: TDBEdit
      Left = 16
      Top = 424
      Width = 134
      Height = 21
      DataField = 'SAID2'
      DataSource = DataModule2.dsTRASFER
      TabOrder = 10
    end
    object DBEdit12: TDBEdit
      Left = 432
      Top = 32
      Width = 134
      Height = 21
      DataField = 'ESTQ2'
      DataSource = DataModule2.dsTRASFER
      TabOrder = 11
    end
    object DBEdit13: TDBEdit
      Left = 432
      Top = 72
      Width = 82
      Height = 21
      DataField = 'LOJA3'
      DataSource = DataModule2.dsTRASFER
      TabOrder = 12
    end
    object DBEdit14: TDBEdit
      Left = 432
      Top = 112
      Width = 134
      Height = 21
      DataField = 'ENTR3'
      DataSource = DataModule2.dsTRASFER
      TabOrder = 13
    end
    object DBEdit15: TDBEdit
      Left = 432
      Top = 152
      Width = 134
      Height = 21
      DataField = 'SAID3'
      DataSource = DataModule2.dsTRASFER
      TabOrder = 14
    end
    object DBEdit16: TDBEdit
      Left = 432
      Top = 192
      Width = 134
      Height = 21
      DataField = 'ESTQ3'
      DataSource = DataModule2.dsTRASFER
      TabOrder = 15
    end
    object DBEdit17: TDBEdit
      Left = 432
      Top = 232
      Width = 134
      Height = 21
      DataField = 'DATA'
      DataSource = DataModule2.dsTRASFER
      TabOrder = 16
    end
  end
  inherited Panel2: TPanel
    Top = 513
    inherited DBButton4: TDBButton
      DataSource = DataModule2.dsTRASFER
    end
    inherited DBButton5: TDBButton
      Enabled = True
      DataSource = DataModule2.dsTRASFER
    end
  end
  inherited Panel3: TPanel
    inherited DBButton1: TDBButton
      Enabled = True
      DataSource = DataModule2.dsTRASFER
    end
    inherited DBButton2: TDBButton
      Enabled = True
      DataSource = DataModule2.dsTRASFER
    end
    inherited DBButton3: TDBButton
      Enabled = True
      DataSource = DataModule2.dsTRASFER
    end
    inherited DBNavigator1: TDBNavigator
      DataSource = DataModule2.dsTRASFER
      Hints.Strings = ()
    end
  end
end
