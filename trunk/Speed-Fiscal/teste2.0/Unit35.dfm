inherited FRMCAP: TFRMCAP
  Left = 166
  Top = 116
  Height = 660
  Caption = 'FRMCAP'
  OldCreateOrder = True
  PixelsPerInch = 96
  TextHeight = 13
  inherited Panel1: TPanel
    Height = 518
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
      Width = 37
      Height = 13
      Caption = 'RAZAO'
      FocusControl = DBEdit5
    end
    object Label6: TLabel
      Left = 24
      Top = 208
      Width = 47
      Height = 13
      Caption = 'DOCESTI'
      FocusControl = DBEdit6
    end
    object Label7: TLabel
      Left = 24
      Top = 248
      Width = 48
      Height = 13
      Caption = 'DOCNUM'
      FocusControl = DBEdit7
    end
    object Label8: TLabel
      Left = 24
      Top = 288
      Width = 53
      Height = 13
      Caption = 'DOCSEQU'
      FocusControl = DBEdit8
    end
    object Label9: TLabel
      Left = 24
      Top = 328
      Width = 22
      Height = 13
      Caption = 'OBS'
      FocusControl = DBEdit9
    end
    object Label10: TLabel
      Left = 24
      Top = 368
      Width = 50
      Height = 13
      Caption = 'DATLANC'
      FocusControl = DBEdit10
    end
    object Label11: TLabel
      Left = 24
      Top = 408
      Width = 48
      Height = 13
      Caption = 'DATEMIS'
      FocusControl = DBEdit11
    end
    object Label12: TLabel
      Left = 24
      Top = 448
      Width = 46
      Height = 13
      Caption = 'VALEMIS'
      FocusControl = DBEdit12
    end
    object Label13: TLabel
      Left = 280
      Top = 368
      Width = 51
      Height = 13
      Caption = 'DATVENC'
      FocusControl = DBEdit13
    end
    object Label14: TLabel
      Left = 280
      Top = 408
      Width = 52
      Height = 13
      Caption = 'DATPGTO'
      FocusControl = DBEdit14
    end
    object Label15: TLabel
      Left = 280
      Top = 448
      Width = 50
      Height = 13
      Caption = 'VALPGTO'
      FocusControl = DBEdit15
    end
    object DBEdit1: TDBEdit
      Left = 24
      Top = 24
      Width = 121
      Height = 21
      DataField = 'CODPLC'
      DataSource = DataModule2.dsCAP
      TabOrder = 0
    end
    object DBEdit2: TDBEdit
      Left = 24
      Top = 64
      Width = 368
      Height = 21
      DataField = 'DESCRICAO'
      DataSource = DataModule2.dsCAP
      TabOrder = 1
    end
    object DBEdit3: TDBEdit
      Left = 24
      Top = 104
      Width = 108
      Height = 21
      DataField = 'ESTILO'
      DataSource = DataModule2.dsCAP
      TabOrder = 2
    end
    object DBEdit4: TDBEdit
      Left = 24
      Top = 144
      Width = 160
      Height = 21
      DataField = 'CODCLI'
      DataSource = DataModule2.dsCAP
      TabOrder = 3
    end
    object DBEdit5: TDBEdit
      Left = 24
      Top = 184
      Width = 628
      Height = 21
      DataField = 'RAZAO'
      DataSource = DataModule2.dsCAP
      TabOrder = 4
    end
    object DBEdit6: TDBEdit
      Left = 24
      Top = 224
      Width = 134
      Height = 21
      DataField = 'DOCESTI'
      DataSource = DataModule2.dsCAP
      TabOrder = 5
    end
    object DBEdit7: TDBEdit
      Left = 24
      Top = 264
      Width = 160
      Height = 21
      DataField = 'DOCNUM'
      DataSource = DataModule2.dsCAP
      TabOrder = 6
    end
    object DBEdit8: TDBEdit
      Left = 24
      Top = 304
      Width = 147
      Height = 21
      DataField = 'DOCSEQU'
      DataSource = DataModule2.dsCAP
      TabOrder = 7
    end
    object DBEdit9: TDBEdit
      Left = 24
      Top = 344
      Width = 472
      Height = 21
      DataField = 'OBS'
      DataSource = DataModule2.dsCAP
      TabOrder = 8
    end
    object DBEdit10: TDBEdit
      Left = 24
      Top = 384
      Width = 160
      Height = 21
      DataField = 'DATLANC'
      DataSource = DataModule2.dsCAP
      TabOrder = 9
    end
    object DBEdit11: TDBEdit
      Left = 24
      Top = 424
      Width = 160
      Height = 21
      DataField = 'DATEMIS'
      DataSource = DataModule2.dsCAP
      TabOrder = 10
    end
    object DBEdit12: TDBEdit
      Left = 24
      Top = 464
      Width = 160
      Height = 21
      DataField = 'VALEMIS'
      DataSource = DataModule2.dsCAP
      TabOrder = 11
    end
    object DBEdit13: TDBEdit
      Left = 280
      Top = 384
      Width = 160
      Height = 21
      DataField = 'DATVENC'
      DataSource = DataModule2.dsCAP
      TabOrder = 12
    end
    object DBEdit14: TDBEdit
      Left = 280
      Top = 424
      Width = 160
      Height = 21
      DataField = 'DATPGTO'
      DataSource = DataModule2.dsCAP
      TabOrder = 13
    end
    object DBEdit15: TDBEdit
      Left = 280
      Top = 464
      Width = 160
      Height = 21
      DataField = 'VALPGTO'
      DataSource = DataModule2.dsCAP
      TabOrder = 14
    end
  end
  inherited Panel2: TPanel
    Top = 575
    inherited DBButton4: TDBButton
      DataSource = DataModule2.dsCAP
    end
    inherited DBButton5: TDBButton
      Enabled = True
      DataSource = DataModule2.dsCAP
    end
  end
  inherited Panel3: TPanel
    inherited DBButton1: TDBButton
      Enabled = True
      DataSource = DataModule2.dsCAP
    end
    inherited DBButton2: TDBButton
      Enabled = True
      DataSource = DataModule2.dsCAP
    end
    inherited DBButton3: TDBButton
      Enabled = True
      DataSource = DataModule2.dsCAP
    end
    inherited DBNavigator1: TDBNavigator
      DataSource = DataModule2.dsCAP
      Hints.Strings = ()
    end
  end
end
