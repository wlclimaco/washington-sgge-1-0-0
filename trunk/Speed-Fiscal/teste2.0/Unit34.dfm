inherited FRMBOLETAS: TFRMBOLETAS
  Left = 224
  Top = 138
  Height = 564
  Caption = 'FRMBOLETAS'
  OldCreateOrder = True
  PixelsPerInch = 96
  TextHeight = 13
  inherited Panel1: TPanel
    Height = 422
    object Label1: TLabel
      Left = 32
      Top = 8
      Width = 43
      Height = 13
      Caption = 'CODPLC'
      FocusControl = DBEdit1
    end
    object Label2: TLabel
      Left = 32
      Top = 48
      Width = 42
      Height = 13
      Caption = 'CODIGO'
      FocusControl = DBEdit2
    end
    object Label3: TLabel
      Left = 32
      Top = 88
      Width = 48
      Height = 13
      Caption = 'DOCNUM'
      FocusControl = DBEdit3
    end
    object Label4: TLabel
      Left = 32
      Top = 128
      Width = 53
      Height = 13
      Caption = 'DOCSEQU'
      FocusControl = DBEdit4
    end
    object Label5: TLabel
      Left = 32
      Top = 168
      Width = 15
      Height = 13
      Caption = 'NP'
      FocusControl = DBEdit5
    end
    object Label6: TLabel
      Left = 32
      Top = 208
      Width = 14
      Height = 13
      Caption = 'N1'
      FocusControl = DBEdit6
    end
    object Label7: TLabel
      Left = 32
      Top = 248
      Width = 36
      Height = 13
      Caption = 'JUROS'
      FocusControl = DBEdit7
    end
    object Label8: TLabel
      Left = 32
      Top = 288
      Width = 60
      Height = 13
      Caption = 'ENCARGOS'
      FocusControl = DBEdit8
    end
    object Label9: TLabel
      Left = 32
      Top = 328
      Width = 28
      Height = 13
      Caption = 'OBS2'
      FocusControl = DBEdit9
    end
    object Label10: TLabel
      Left = 32
      Top = 368
      Width = 60
      Height = 13
      Caption = 'FLAG_EXCL'
      FocusControl = DBEdit10
    end
    object DBEdit1: TDBEdit
      Left = 32
      Top = 24
      Width = 95
      Height = 21
      DataField = 'CODPLC'
      DataSource = DataModule2.dsBOLETAS
      TabOrder = 0
    end
    object DBEdit2: TDBEdit
      Left = 32
      Top = 64
      Width = 134
      Height = 21
      DataField = 'CODIGO'
      DataSource = DataModule2.dsBOLETAS
      TabOrder = 1
    end
    object DBEdit3: TDBEdit
      Left = 32
      Top = 104
      Width = 134
      Height = 21
      DataField = 'DOCNUM'
      DataSource = DataModule2.dsBOLETAS
      TabOrder = 2
    end
    object DBEdit4: TDBEdit
      Left = 32
      Top = 144
      Width = 17
      Height = 21
      DataField = 'DOCSEQU'
      DataSource = DataModule2.dsBOLETAS
      TabOrder = 3
    end
    object DBEdit5: TDBEdit
      Left = 32
      Top = 184
      Width = 30
      Height = 21
      DataField = 'NP'
      DataSource = DataModule2.dsBOLETAS
      TabOrder = 4
    end
    object DBEdit6: TDBEdit
      Left = 32
      Top = 224
      Width = 17
      Height = 21
      DataField = 'N1'
      DataSource = DataModule2.dsBOLETAS
      TabOrder = 5
    end
    object DBEdit7: TDBEdit
      Left = 32
      Top = 264
      Width = 134
      Height = 21
      DataField = 'JUROS'
      DataSource = DataModule2.dsBOLETAS
      TabOrder = 6
    end
    object DBEdit8: TDBEdit
      Left = 32
      Top = 304
      Width = 134
      Height = 21
      DataField = 'ENCARGOS'
      DataSource = DataModule2.dsBOLETAS
      TabOrder = 7
    end
    object DBEdit9: TDBEdit
      Left = 32
      Top = 344
      Width = 654
      Height = 21
      DataField = 'OBS2'
      DataSource = DataModule2.dsBOLETAS
      TabOrder = 8
    end
    object DBEdit10: TDBEdit
      Left = 32
      Top = 384
      Width = 17
      Height = 21
      DataField = 'FLAG_EXCL'
      DataSource = DataModule2.dsBOLETAS
      TabOrder = 9
    end
  end
  inherited Panel2: TPanel
    Top = 479
    inherited DBButton4: TDBButton
      DataSource = DataModule2.dsBOLETAS
    end
    inherited DBButton5: TDBButton
      Enabled = True
      DataSource = DataModule2.dsBOLETAS
    end
  end
  inherited Panel3: TPanel
    inherited DBButton1: TDBButton
      Enabled = True
      DataSource = DataModule2.dsBOLETAS
    end
    inherited DBButton2: TDBButton
      Enabled = True
      DataSource = DataModule2.dsBOLETAS
    end
    inherited DBButton3: TDBButton
      Enabled = True
      DataSource = DataModule2.dsBOLETAS
    end
    inherited DBNavigator1: TDBNavigator
      DataSource = DataModule2.dsBOLETAS
      Hints.Strings = ()
    end
  end
end
