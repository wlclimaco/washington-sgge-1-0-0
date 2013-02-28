inherited FRMCSLANCAM: TFRMCSLANCAM
  Left = 227
  Top = 50
  Width = 621
  Height = 560
  Caption = 'FRMCSLANCAM'
  OldCreateOrder = True
  PixelsPerInch = 96
  TextHeight = 13
  inherited Panel1: TPanel
    Width = 613
    Height = 418
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
      Width = 52
      Height = 13
      Caption = 'SEQUENC'
      FocusControl = DBEdit2
    end
    object Label3: TLabel
      Left = 24
      Top = 88
      Width = 29
      Height = 13
      Caption = 'DATA'
      FocusControl = DBEdit3
    end
    object Label4: TLabel
      Left = 24
      Top = 128
      Width = 52
      Height = 13
      Caption = 'CODBANC'
      FocusControl = DBEdit4
    end
    object Label5: TLabel
      Left = 24
      Top = 168
      Width = 37
      Height = 13
      Caption = 'BANCO'
      FocusControl = DBEdit5
    end
    object Label6: TLabel
      Left = 24
      Top = 208
      Width = 37
      Height = 13
      Caption = 'AGENC'
      FocusControl = DBEdit6
    end
    object Label7: TLabel
      Left = 24
      Top = 248
      Width = 44
      Height = 13
      Caption = 'CCONTA'
      FocusControl = DBEdit7
    end
    object Label8: TLabel
      Left = 24
      Top = 288
      Width = 43
      Height = 13
      Caption = 'CODPLC'
      FocusControl = DBEdit8
    end
    object Label9: TLabel
      Left = 24
      Top = 328
      Width = 62
      Height = 13
      Caption = 'DESCRICAO'
      FocusControl = DBEdit9
    end
    object Label10: TLabel
      Left = 24
      Top = 368
      Width = 38
      Height = 13
      Caption = 'ESTILO'
      FocusControl = DBEdit10
    end
    object Label11: TLabel
      Left = 200
      Top = 8
      Width = 33
      Height = 13
      Caption = 'HISTO'
      FocusControl = DBEdit11
    end
    object Label12: TLabel
      Left = 200
      Top = 48
      Width = 38
      Height = 13
      Caption = 'PAGAM'
      FocusControl = DBEdit12
    end
    object Label13: TLabel
      Left = 200
      Top = 88
      Width = 36
      Height = 13
      Caption = 'RECEB'
      FocusControl = DBEdit13
    end
    object Label14: TLabel
      Left = 200
      Top = 128
      Width = 36
      Height = 13
      Caption = 'SALDO'
      FocusControl = DBEdit14
    end
    object Label15: TLabel
      Left = 200
      Top = 168
      Width = 60
      Height = 13
      Caption = 'FLAG_EXCL'
      FocusControl = DBEdit15
    end
    object DBEdit1: TDBEdit
      Left = 24
      Top = 24
      Width = 134
      Height = 21
      DataField = 'CODIGO'
      DataSource = DataModule2.dsCSLANCAM
      TabOrder = 0
    end
    object DBEdit2: TDBEdit
      Left = 24
      Top = 64
      Width = 134
      Height = 21
      DataField = 'SEQUENC'
      DataSource = DataModule2.dsCSLANCAM
      TabOrder = 1
    end
    object DBEdit3: TDBEdit
      Left = 24
      Top = 104
      Width = 134
      Height = 21
      DataField = 'DATA'
      DataSource = DataModule2.dsCSLANCAM
      TabOrder = 2
    end
    object DBEdit4: TDBEdit
      Left = 24
      Top = 144
      Width = 134
      Height = 21
      DataField = 'CODBANC'
      DataSource = DataModule2.dsCSLANCAM
      TabOrder = 3
    end
    object DBEdit5: TDBEdit
      Left = 24
      Top = 184
      Width = 134
      Height = 21
      DataField = 'BANCO'
      DataSource = DataModule2.dsCSLANCAM
      TabOrder = 4
    end
    object DBEdit6: TDBEdit
      Left = 24
      Top = 224
      Width = 108
      Height = 21
      DataField = 'AGENC'
      DataSource = DataModule2.dsCSLANCAM
      TabOrder = 5
    end
    object DBEdit7: TDBEdit
      Left = 24
      Top = 264
      Width = 134
      Height = 21
      DataField = 'CCONTA'
      DataSource = DataModule2.dsCSLANCAM
      TabOrder = 6
    end
    object DBEdit8: TDBEdit
      Left = 24
      Top = 304
      Width = 95
      Height = 21
      DataField = 'CODPLC'
      DataSource = DataModule2.dsCSLANCAM
      TabOrder = 7
    end
    object DBEdit9: TDBEdit
      Left = 24
      Top = 344
      Width = 238
      Height = 21
      DataField = 'DESCRICAO'
      DataSource = DataModule2.dsCSLANCAM
      TabOrder = 8
    end
    object DBEdit10: TDBEdit
      Left = 24
      Top = 384
      Width = 17
      Height = 21
      DataField = 'ESTILO'
      DataSource = DataModule2.dsCSLANCAM
      TabOrder = 9
    end
    object DBEdit11: TDBEdit
      Left = 200
      Top = 24
      Width = 394
      Height = 21
      DataField = 'HISTO'
      DataSource = DataModule2.dsCSLANCAM
      TabOrder = 10
    end
    object DBEdit12: TDBEdit
      Left = 200
      Top = 64
      Width = 134
      Height = 21
      DataField = 'PAGAM'
      DataSource = DataModule2.dsCSLANCAM
      TabOrder = 11
    end
    object DBEdit13: TDBEdit
      Left = 200
      Top = 104
      Width = 134
      Height = 21
      DataField = 'RECEB'
      DataSource = DataModule2.dsCSLANCAM
      TabOrder = 12
    end
    object DBEdit14: TDBEdit
      Left = 200
      Top = 144
      Width = 134
      Height = 21
      DataField = 'SALDO'
      DataSource = DataModule2.dsCSLANCAM
      TabOrder = 13
    end
    object DBEdit15: TDBEdit
      Left = 200
      Top = 184
      Width = 17
      Height = 21
      DataField = 'FLAG_EXCL'
      DataSource = DataModule2.dsCSLANCAM
      TabOrder = 14
    end
  end
  inherited Panel2: TPanel
    Top = 475
    Width = 613
    inherited DBButton4: TDBButton
      Left = 520
      DataSource = DataModule2.dsCSLANCAM
    end
    inherited DBButton5: TDBButton
      Left = 448
      Enabled = True
      DataSource = DataModule2.dsCSLANCAM
    end
  end
  inherited Panel3: TPanel
    Width = 613
    inherited DBButton1: TDBButton
      Enabled = True
      DataSource = DataModule2.dsCSLANCAM
    end
    inherited DBButton2: TDBButton
      Enabled = True
      DataSource = DataModule2.dsCSLANCAM
    end
    inherited DBButton3: TDBButton
      Enabled = True
      DataSource = DataModule2.dsCSLANCAM
    end
    inherited DBNavigator1: TDBNavigator
      DataSource = DataModule2.dsCSLANCAM
      Hints.Strings = ()
    end
  end
end
