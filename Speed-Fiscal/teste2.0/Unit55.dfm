inherited FRMPWUSUA: TFRMPWUSUA
  Left = 306
  Top = 230
  Width = 491
  Height = 433
  Caption = 'FRMPWUSUA'
  PixelsPerInch = 96
  TextHeight = 13
  inherited Panel1: TPanel
    Width = 483
    Height = 291
    object Label1: TLabel
      Left = 32
      Top = 8
      Width = 63
      Height = 13
      Caption = 'PW_GRUPO'
      FocusControl = DBEdit1
    end
    object Label2: TLabel
      Left = 32
      Top = 48
      Width = 66
      Height = 13
      Caption = 'PW_CODIGO'
      FocusControl = DBEdit2
    end
    object Label3: TLabel
      Left = 32
      Top = 88
      Width = 56
      Height = 13
      Caption = 'PW_NOME'
      FocusControl = DBEdit3
    end
    object Label4: TLabel
      Left = 32
      Top = 128
      Width = 55
      Height = 13
      Caption = 'PW_NIVEL'
      FocusControl = DBEdit4
    end
    object Label5: TLabel
      Left = 32
      Top = 168
      Width = 46
      Height = 13
      Caption = 'PW_OBS'
      FocusControl = DBEdit5
    end
    object Label6: TLabel
      Left = 32
      Top = 208
      Width = 52
      Height = 13
      Caption = 'PW_PASS'
      FocusControl = DBEdit6
    end
    object Label7: TLabel
      Left = 32
      Top = 248
      Width = 60
      Height = 13
      Caption = 'FLAG_EXCL'
      FocusControl = DBEdit7
    end
    object DBEdit1: TDBEdit
      Left = 32
      Top = 24
      Width = 56
      Height = 21
      DataField = 'PW_GRUPO'
      DataSource = DataModule2.dsPWUSUA
      TabOrder = 0
    end
    object DBEdit2: TDBEdit
      Left = 32
      Top = 64
      Width = 56
      Height = 21
      DataField = 'PW_CODIGO'
      DataSource = DataModule2.dsPWUSUA
      TabOrder = 1
    end
    object DBEdit3: TDBEdit
      Left = 32
      Top = 104
      Width = 199
      Height = 21
      DataField = 'PW_NOME'
      DataSource = DataModule2.dsPWUSUA
      TabOrder = 2
    end
    object DBEdit4: TDBEdit
      Left = 32
      Top = 144
      Width = 17
      Height = 21
      DataField = 'PW_NIVEL'
      DataSource = DataModule2.dsPWUSUA
      TabOrder = 3
    end
    object DBEdit5: TDBEdit
      Left = 32
      Top = 184
      Width = 199
      Height = 21
      DataField = 'PW_OBS'
      DataSource = DataModule2.dsPWUSUA
      TabOrder = 4
    end
    object DBEdit6: TDBEdit
      Left = 32
      Top = 224
      Width = 82
      Height = 21
      DataField = 'PW_PASS'
      DataSource = DataModule2.dsPWUSUA
      TabOrder = 5
    end
    object DBEdit7: TDBEdit
      Left = 32
      Top = 264
      Width = 17
      Height = 21
      DataField = 'FLAG_EXCL'
      DataSource = DataModule2.dsPWUSUA
      TabOrder = 6
    end
  end
  inherited Panel2: TPanel
    Top = 348
    Width = 483
    inherited DBButton4: TDBButton
      Left = 376
      DataSource = DataModule2.dsPWUSUA
    end
    inherited DBButton5: TDBButton
      Left = 288
      Enabled = True
      DataSource = DataModule2.dsPWUSUA
    end
  end
  inherited Panel3: TPanel
    Width = 483
    inherited DBButton1: TDBButton
      Enabled = True
      DataSource = DataModule2.dsPWUSUA
    end
    inherited DBButton2: TDBButton
      Enabled = True
      DataSource = DataModule2.dsPWUSUA
    end
    inherited DBButton3: TDBButton
      Enabled = True
      DataSource = DataModule2.dsPWUSUA
    end
    inherited DBNavigator1: TDBNavigator
      DataSource = DataModule2.dsPWUSUA
      Hints.Strings = ()
    end
  end
end
