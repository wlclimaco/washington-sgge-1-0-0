inherited FRMENTRADAS: TFRMENTRADAS
  Left = 239
  Top = 198
  Height = 674
  Caption = 'FRMENTRADAS'
  OldCreateOrder = True
  PixelsPerInch = 96
  TextHeight = 13
  inherited Panel1: TPanel
    Height = 532
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
      Width = 45
      Height = 13
      Caption = 'CODENT'
      FocusControl = DBEdit2
    end
    object Label3: TLabel
      Left = 32
      Top = 88
      Width = 54
      Height = 13
      Caption = 'PRODUTO'
      FocusControl = DBEdit3
    end
    object Label4: TLabel
      Left = 32
      Top = 128
      Width = 21
      Height = 13
      Caption = 'REF'
      FocusControl = DBEdit4
    end
    object Label5: TLabel
      Left = 32
      Top = 168
      Width = 47
      Height = 13
      Caption = 'DOCESTI'
      FocusControl = DBEdit5
    end
    object Label6: TLabel
      Left = 32
      Top = 208
      Width = 48
      Height = 13
      Caption = 'DOCNUM'
      FocusControl = DBEdit6
    end
    object Label7: TLabel
      Left = 32
      Top = 248
      Width = 53
      Height = 13
      Caption = 'DOCSEQU'
      FocusControl = DBEdit7
    end
    object Label8: TLabel
      Left = 32
      Top = 288
      Width = 46
      Height = 13
      Caption = 'CODPRO'
      FocusControl = DBEdit8
    end
    object Label9: TLabel
      Left = 32
      Top = 328
      Width = 36
      Height = 13
      Caption = 'QTDE1'
      FocusControl = DBEdit9
    end
    object Label10: TLabel
      Left = 32
      Top = 368
      Width = 36
      Height = 13
      Caption = 'QTDE2'
      FocusControl = DBEdit10
    end
    object Label11: TLabel
      Left = 32
      Top = 408
      Width = 36
      Height = 13
      Caption = 'QTDE3'
      FocusControl = DBEdit11
    end
    object Label12: TLabel
      Left = 32
      Top = 448
      Width = 26
      Height = 13
      Caption = 'UNIT'
      FocusControl = DBEdit12
    end
    object Label13: TLabel
      Left = 32
      Top = 488
      Width = 60
      Height = 13
      Caption = 'FLAG_EXCL'
      FocusControl = DBEdit13
    end
    object DBEdit1: TDBEdit
      Left = 32
      Top = 24
      Width = 95
      Height = 21
      DataField = 'CODPLC'
      DataSource = DataModule2.dsENTRADAS
      TabOrder = 0
    end
    object DBEdit2: TDBEdit
      Left = 32
      Top = 64
      Width = 134
      Height = 21
      DataField = 'CODENT'
      DataSource = DataModule2.dsENTRADAS
      TabOrder = 1
    end
    object DBEdit3: TDBEdit
      Left = 32
      Top = 104
      Width = 199
      Height = 21
      DataField = 'PRODUTO'
      DataSource = DataModule2.dsENTRADAS
      TabOrder = 2
    end
    object DBEdit4: TDBEdit
      Left = 32
      Top = 144
      Width = 199
      Height = 21
      DataField = 'REF'
      DataSource = DataModule2.dsENTRADAS
      TabOrder = 3
    end
    object DBEdit5: TDBEdit
      Left = 32
      Top = 184
      Width = 30
      Height = 21
      DataField = 'DOCESTI'
      DataSource = DataModule2.dsENTRADAS
      TabOrder = 4
    end
    object DBEdit6: TDBEdit
      Left = 32
      Top = 224
      Width = 134
      Height = 21
      DataField = 'DOCNUM'
      DataSource = DataModule2.dsENTRADAS
      TabOrder = 5
    end
    object DBEdit7: TDBEdit
      Left = 32
      Top = 264
      Width = 17
      Height = 21
      DataField = 'DOCSEQU'
      DataSource = DataModule2.dsENTRADAS
      TabOrder = 6
    end
    object DBEdit8: TDBEdit
      Left = 32
      Top = 304
      Width = 134
      Height = 21
      DataField = 'CODPRO'
      DataSource = DataModule2.dsENTRADAS
      TabOrder = 7
    end
    object DBEdit9: TDBEdit
      Left = 32
      Top = 344
      Width = 134
      Height = 21
      DataField = 'QTDE1'
      DataSource = DataModule2.dsENTRADAS
      TabOrder = 8
    end
    object DBEdit10: TDBEdit
      Left = 32
      Top = 384
      Width = 134
      Height = 21
      DataField = 'QTDE2'
      DataSource = DataModule2.dsENTRADAS
      TabOrder = 9
    end
    object DBEdit11: TDBEdit
      Left = 32
      Top = 424
      Width = 134
      Height = 21
      DataField = 'QTDE3'
      DataSource = DataModule2.dsENTRADAS
      TabOrder = 10
    end
    object DBEdit12: TDBEdit
      Left = 32
      Top = 464
      Width = 134
      Height = 21
      DataField = 'UNIT'
      DataSource = DataModule2.dsENTRADAS
      TabOrder = 11
    end
    object DBEdit13: TDBEdit
      Left = 32
      Top = 504
      Width = 17
      Height = 21
      DataField = 'FLAG_EXCL'
      DataSource = DataModule2.dsENTRADAS
      TabOrder = 12
    end
  end
  inherited Panel2: TPanel
    Top = 589
    inherited DBButton4: TDBButton
      DataSource = DataModule2.dsENTRADAS
    end
    inherited DBButton5: TDBButton
      DataSource = DataModule2.dsENTRADAS
    end
  end
  inherited Panel3: TPanel
    inherited DBButton1: TDBButton
      DataSource = DataModule2.dsENTRADAS
    end
    inherited DBButton2: TDBButton
      DataSource = DataModule2.dsENTRADAS
    end
    inherited DBButton3: TDBButton
      DataSource = DataModule2.dsENTRADAS
    end
    inherited DBNavigator1: TDBNavigator
      Top = -40
      Hints.Strings = ()
    end
    object DBNavigator2: TDBNavigator
      Left = 256
      Top = 16
      Width = 224
      Height = 33
      DataSource = DataModule2.dsENTRADAS
      VisibleButtons = [nbFirst, nbPrior, nbNext, nbLast]
      TabOrder = 4
    end
  end
end
