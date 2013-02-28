inherited FRMVENDAS: TFRMVENDAS
  Left = 248
  Top = 58
  Height = 716
  Caption = 'FRMVENDAS'
  PixelsPerInch = 96
  TextHeight = 13
  inherited Panel1: TPanel
    Height = 574
    object Label1: TLabel
      Left = 16
      Top = 8
      Width = 23
      Height = 13
      Caption = 'COD'
      FocusControl = DBEdit1
    end
    object Label2: TLabel
      Left = 16
      Top = 48
      Width = 32
      Height = 13
      Caption = 'NOME'
      FocusControl = DBEdit2
    end
    object Label3: TLabel
      Left = 16
      Top = 88
      Width = 48
      Height = 13
      Caption = 'EMISSAO'
      FocusControl = DBEdit3
    end
    object Label4: TLabel
      Left = 16
      Top = 128
      Width = 38
      Height = 13
      Caption = 'FORMA'
      FocusControl = DBEdit4
    end
    object Label5: TLabel
      Left = 16
      Top = 168
      Width = 35
      Height = 13
      Caption = 'TOTAL'
      FocusControl = DBEdit5
    end
    object Label6: TLabel
      Left = 16
      Top = 208
      Width = 13
      Height = 13
      Caption = 'P1'
      FocusControl = DBEdit6
    end
    object Label7: TLabel
      Left = 16
      Top = 248
      Width = 24
      Height = 13
      Caption = 'DIA1'
      FocusControl = DBEdit7
    end
    object Label8: TLabel
      Left = 16
      Top = 288
      Width = 28
      Height = 13
      Caption = 'DAT1'
      FocusControl = DBEdit8
    end
    object Label9: TLabel
      Left = 16
      Top = 328
      Width = 26
      Height = 13
      Caption = 'VAL1'
      FocusControl = DBEdit9
    end
    object Label10: TLabel
      Left = 16
      Top = 368
      Width = 13
      Height = 13
      Caption = 'P2'
      FocusControl = DBEdit10
    end
    object Label11: TLabel
      Left = 16
      Top = 408
      Width = 24
      Height = 13
      Caption = 'DIA2'
      FocusControl = DBEdit11
    end
    object Label12: TLabel
      Left = 16
      Top = 448
      Width = 28
      Height = 13
      Caption = 'DAT2'
      FocusControl = DBEdit12
    end
    object Label13: TLabel
      Left = 16
      Top = 488
      Width = 26
      Height = 13
      Caption = 'VAL2'
      FocusControl = DBEdit13
    end
    object Label14: TLabel
      Left = 16
      Top = 528
      Width = 13
      Height = 13
      Caption = 'P3'
      FocusControl = DBEdit14
    end
    object Label15: TLabel
      Left = 376
      Top = 96
      Width = 24
      Height = 13
      Caption = 'DIA3'
      FocusControl = DBEdit15
    end
    object Label16: TLabel
      Left = 376
      Top = 136
      Width = 28
      Height = 13
      Caption = 'DAT3'
      FocusControl = DBEdit16
    end
    object Label17: TLabel
      Left = 376
      Top = 176
      Width = 26
      Height = 13
      Caption = 'VAL3'
      FocusControl = DBEdit17
    end
    object DBEdit1: TDBEdit
      Left = 16
      Top = 24
      Width = 134
      Height = 21
      DataField = 'COD'
      DataSource = DataModule2.dsVENDAS
      TabOrder = 0
    end
    object DBEdit2: TDBEdit
      Left = 16
      Top = 64
      Width = 524
      Height = 21
      DataField = 'NOME'
      DataSource = DataModule2.dsVENDAS
      TabOrder = 1
    end
    object DBEdit3: TDBEdit
      Left = 16
      Top = 104
      Width = 134
      Height = 21
      DataField = 'EMISSAO'
      DataSource = DataModule2.dsVENDAS
      TabOrder = 2
    end
    object DBEdit4: TDBEdit
      Left = 16
      Top = 144
      Width = 289
      Height = 21
      DataField = 'FORMA'
      DataSource = DataModule2.dsVENDAS
      TabOrder = 3
    end
    object DBEdit5: TDBEdit
      Left = 16
      Top = 184
      Width = 134
      Height = 21
      DataField = 'TOTAL'
      DataSource = DataModule2.dsVENDAS
      TabOrder = 4
    end
    object DBEdit6: TDBEdit
      Left = 16
      Top = 224
      Width = 134
      Height = 21
      DataField = 'P1'
      DataSource = DataModule2.dsVENDAS
      TabOrder = 5
    end
    object DBEdit7: TDBEdit
      Left = 16
      Top = 264
      Width = 134
      Height = 21
      DataField = 'DIA1'
      DataSource = DataModule2.dsVENDAS
      TabOrder = 6
    end
    object DBEdit8: TDBEdit
      Left = 16
      Top = 304
      Width = 134
      Height = 21
      DataField = 'DAT1'
      DataSource = DataModule2.dsVENDAS
      TabOrder = 7
    end
    object DBEdit9: TDBEdit
      Left = 16
      Top = 344
      Width = 134
      Height = 21
      DataField = 'VAL1'
      DataSource = DataModule2.dsVENDAS
      TabOrder = 8
    end
    object DBEdit10: TDBEdit
      Left = 16
      Top = 384
      Width = 134
      Height = 21
      DataField = 'P2'
      DataSource = DataModule2.dsVENDAS
      TabOrder = 9
    end
    object DBEdit11: TDBEdit
      Left = 16
      Top = 424
      Width = 134
      Height = 21
      DataField = 'DIA2'
      DataSource = DataModule2.dsVENDAS
      TabOrder = 10
    end
    object DBEdit12: TDBEdit
      Left = 16
      Top = 464
      Width = 134
      Height = 21
      DataField = 'DAT2'
      DataSource = DataModule2.dsVENDAS
      TabOrder = 11
    end
    object DBEdit13: TDBEdit
      Left = 16
      Top = 504
      Width = 134
      Height = 21
      DataField = 'VAL2'
      DataSource = DataModule2.dsVENDAS
      TabOrder = 12
    end
    object DBEdit14: TDBEdit
      Left = 16
      Top = 544
      Width = 134
      Height = 21
      DataField = 'P3'
      DataSource = DataModule2.dsVENDAS
      TabOrder = 13
    end
    object DBEdit15: TDBEdit
      Left = 376
      Top = 112
      Width = 134
      Height = 21
      DataField = 'DIA3'
      DataSource = DataModule2.dsVENDAS
      TabOrder = 14
    end
    object DBEdit16: TDBEdit
      Left = 376
      Top = 152
      Width = 134
      Height = 21
      DataField = 'DAT3'
      DataSource = DataModule2.dsVENDAS
      TabOrder = 15
    end
    object DBEdit17: TDBEdit
      Left = 376
      Top = 192
      Width = 134
      Height = 21
      DataField = 'VAL3'
      DataSource = DataModule2.dsVENDAS
      TabOrder = 16
    end
  end
  inherited Panel2: TPanel
    Top = 631
    inherited DBButton4: TDBButton
      DataSource = DataModule2.dsVENDAS
    end
    inherited DBButton5: TDBButton
      Enabled = True
      DataSource = DataModule2.dsVENDAS
    end
  end
  inherited Panel3: TPanel
    inherited DBButton1: TDBButton
      Enabled = True
      DataSource = DataModule2.dsVENDAS
    end
    inherited DBButton2: TDBButton
      Enabled = True
      DataSource = DataModule2.dsVENDAS
    end
    inherited DBButton3: TDBButton
      Enabled = True
      DataSource = DataModule2.dsVENDAS
    end
    inherited DBNavigator1: TDBNavigator
      DataSource = DataModule2.dsVENDAS
      Hints.Strings = ()
    end
  end
end
