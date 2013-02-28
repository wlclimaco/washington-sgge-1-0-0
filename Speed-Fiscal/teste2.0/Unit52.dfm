inherited FRMPRECOS: TFRMPRECOS
  Left = 255
  Top = 131
  Height = 739
  Caption = 'FRMPRECOS'
  PixelsPerInch = 96
  TextHeight = 13
  inherited Panel1: TPanel
    Height = 597
    object Label1: TLabel
      Left = 24
      Top = 0
      Width = 46
      Height = 13
      Caption = 'CODPRO'
      FocusControl = DBEdit1
    end
    object Label2: TLabel
      Left = 24
      Top = 40
      Width = 42
      Height = 13
      Caption = 'CODIGO'
      FocusControl = DBEdit2
    end
    object Label3: TLabel
      Left = 24
      Top = 80
      Width = 54
      Height = 13
      Caption = 'PRODUTO'
      FocusControl = DBEdit3
    end
    object Label4: TLabel
      Left = 24
      Top = 120
      Width = 21
      Height = 13
      Caption = 'REF'
      FocusControl = DBEdit4
    end
    object Label5: TLabel
      Left = 24
      Top = 160
      Width = 34
      Height = 13
      Caption = 'CLASS'
      FocusControl = DBEdit5
    end
    object Label6: TLabel
      Left = 24
      Top = 200
      Width = 34
      Height = 13
      Caption = 'LOCAL'
      FocusControl = DBEdit6
    end
    object Label7: TLabel
      Left = 24
      Top = 240
      Width = 32
      Height = 13
      Caption = 'LOJA1'
      FocusControl = DBEdit7
    end
    object Label8: TLabel
      Left = 24
      Top = 280
      Width = 35
      Height = 13
      Caption = 'ESTQ1'
      FocusControl = DBEdit8
    end
    object Label9: TLabel
      Left = 24
      Top = 320
      Width = 31
      Height = 13
      Caption = 'INVE1'
      FocusControl = DBEdit9
    end
    object Label10: TLabel
      Left = 24
      Top = 360
      Width = 32
      Height = 13
      Caption = 'INDC1'
      FocusControl = DBEdit10
    end
    object Label11: TLabel
      Left = 24
      Top = 400
      Width = 35
      Height = 13
      Caption = 'PREC1'
      FocusControl = DBEdit11
    end
    object Label12: TLabel
      Left = 416
      Top = 8
      Width = 27
      Height = 13
      Caption = 'TAB1'
      FocusControl = DBEdit12
    end
    object Label13: TLabel
      Left = 416
      Top = 48
      Width = 32
      Height = 13
      Caption = 'LOJA2'
      FocusControl = DBEdit13
    end
    object Label14: TLabel
      Left = 416
      Top = 88
      Width = 35
      Height = 13
      Caption = 'ESTQ2'
      FocusControl = DBEdit14
    end
    object Label15: TLabel
      Left = 416
      Top = 128
      Width = 31
      Height = 13
      Caption = 'INVE2'
      FocusControl = DBEdit15
    end
    object Label16: TLabel
      Left = 416
      Top = 168
      Width = 32
      Height = 13
      Caption = 'INDC2'
      FocusControl = DBEdit16
    end
    object Label17: TLabel
      Left = 416
      Top = 208
      Width = 35
      Height = 13
      Caption = 'PREC2'
      FocusControl = DBEdit17
    end
    object Label18: TLabel
      Left = 416
      Top = 248
      Width = 27
      Height = 13
      Caption = 'TAB2'
      FocusControl = DBEdit18
    end
    object Label19: TLabel
      Left = 416
      Top = 288
      Width = 32
      Height = 13
      Caption = 'LOJA3'
      FocusControl = DBEdit19
    end
    object Label20: TLabel
      Left = 416
      Top = 328
      Width = 35
      Height = 13
      Caption = 'ESTQ3'
      FocusControl = DBEdit20
    end
    object Label21: TLabel
      Left = 416
      Top = 368
      Width = 31
      Height = 13
      Caption = 'INVE3'
      FocusControl = DBEdit21
    end
    object Label22: TLabel
      Left = 416
      Top = 408
      Width = 32
      Height = 13
      Caption = 'INDC3'
      FocusControl = DBEdit22
    end
    object Label23: TLabel
      Left = 416
      Top = 448
      Width = 35
      Height = 13
      Caption = 'PREC3'
      FocusControl = DBEdit23
    end
    object Label24: TLabel
      Left = 416
      Top = 488
      Width = 27
      Height = 13
      Caption = 'TAB3'
      FocusControl = DBEdit24
    end
    object Label25: TLabel
      Left = 416
      Top = 528
      Width = 60
      Height = 13
      Caption = 'FLAG_EXCL'
      FocusControl = DBEdit25
    end
    object DBEdit1: TDBEdit
      Left = 24
      Top = 16
      Width = 134
      Height = 21
      DataField = 'CODPRO'
      DataSource = DataModule2.dsPRECOS
      TabOrder = 0
    end
    object DBEdit2: TDBEdit
      Left = 24
      Top = 56
      Width = 134
      Height = 21
      DataField = 'CODIGO'
      DataSource = DataModule2.dsPRECOS
      TabOrder = 1
    end
    object DBEdit3: TDBEdit
      Left = 24
      Top = 96
      Width = 199
      Height = 21
      DataField = 'PRODUTO'
      DataSource = DataModule2.dsPRECOS
      TabOrder = 2
    end
    object DBEdit4: TDBEdit
      Left = 24
      Top = 136
      Width = 199
      Height = 21
      DataField = 'REF'
      DataSource = DataModule2.dsPRECOS
      TabOrder = 3
    end
    object DBEdit5: TDBEdit
      Left = 24
      Top = 176
      Width = 134
      Height = 21
      DataField = 'CLASS'
      DataSource = DataModule2.dsPRECOS
      TabOrder = 4
    end
    object DBEdit6: TDBEdit
      Left = 24
      Top = 216
      Width = 69
      Height = 21
      DataField = 'LOCAL'
      DataSource = DataModule2.dsPRECOS
      TabOrder = 5
    end
    object DBEdit7: TDBEdit
      Left = 24
      Top = 256
      Width = 82
      Height = 21
      DataField = 'LOJA1'
      DataSource = DataModule2.dsPRECOS
      TabOrder = 6
    end
    object DBEdit8: TDBEdit
      Left = 24
      Top = 296
      Width = 134
      Height = 21
      DataField = 'ESTQ1'
      DataSource = DataModule2.dsPRECOS
      TabOrder = 7
    end
    object DBEdit9: TDBEdit
      Left = 24
      Top = 336
      Width = 134
      Height = 21
      DataField = 'INVE1'
      DataSource = DataModule2.dsPRECOS
      TabOrder = 8
    end
    object DBEdit10: TDBEdit
      Left = 24
      Top = 376
      Width = 134
      Height = 21
      DataField = 'INDC1'
      DataSource = DataModule2.dsPRECOS
      TabOrder = 9
    end
    object DBEdit11: TDBEdit
      Left = 24
      Top = 416
      Width = 134
      Height = 21
      DataField = 'PREC1'
      DataSource = DataModule2.dsPRECOS
      TabOrder = 10
    end
    object DBEdit12: TDBEdit
      Left = 416
      Top = 24
      Width = 134
      Height = 21
      DataField = 'TAB1'
      DataSource = DataModule2.dsPRECOS
      TabOrder = 11
    end
    object DBEdit13: TDBEdit
      Left = 416
      Top = 64
      Width = 82
      Height = 21
      DataField = 'LOJA2'
      DataSource = DataModule2.dsPRECOS
      TabOrder = 12
    end
    object DBEdit14: TDBEdit
      Left = 416
      Top = 104
      Width = 134
      Height = 21
      DataField = 'ESTQ2'
      DataSource = DataModule2.dsPRECOS
      TabOrder = 13
    end
    object DBEdit15: TDBEdit
      Left = 416
      Top = 144
      Width = 134
      Height = 21
      DataField = 'INVE2'
      DataSource = DataModule2.dsPRECOS
      TabOrder = 14
    end
    object DBEdit16: TDBEdit
      Left = 416
      Top = 184
      Width = 134
      Height = 21
      DataField = 'INDC2'
      DataSource = DataModule2.dsPRECOS
      TabOrder = 15
    end
    object DBEdit17: TDBEdit
      Left = 416
      Top = 224
      Width = 134
      Height = 21
      DataField = 'PREC2'
      DataSource = DataModule2.dsPRECOS
      TabOrder = 16
    end
    object DBEdit18: TDBEdit
      Left = 416
      Top = 264
      Width = 134
      Height = 21
      DataField = 'TAB2'
      DataSource = DataModule2.dsPRECOS
      TabOrder = 17
    end
    object DBEdit19: TDBEdit
      Left = 416
      Top = 304
      Width = 82
      Height = 21
      DataField = 'LOJA3'
      DataSource = DataModule2.dsPRECOS
      TabOrder = 18
    end
    object DBEdit20: TDBEdit
      Left = 416
      Top = 344
      Width = 134
      Height = 21
      DataField = 'ESTQ3'
      DataSource = DataModule2.dsPRECOS
      TabOrder = 19
    end
    object DBEdit21: TDBEdit
      Left = 416
      Top = 384
      Width = 134
      Height = 21
      DataField = 'INVE3'
      DataSource = DataModule2.dsPRECOS
      TabOrder = 20
    end
    object DBEdit22: TDBEdit
      Left = 416
      Top = 424
      Width = 134
      Height = 21
      DataField = 'INDC3'
      DataSource = DataModule2.dsPRECOS
      TabOrder = 21
    end
    object DBEdit23: TDBEdit
      Left = 416
      Top = 464
      Width = 134
      Height = 21
      DataField = 'PREC3'
      DataSource = DataModule2.dsPRECOS
      TabOrder = 22
    end
    object DBEdit24: TDBEdit
      Left = 416
      Top = 504
      Width = 134
      Height = 21
      DataField = 'TAB3'
      DataSource = DataModule2.dsPRECOS
      TabOrder = 23
    end
    object DBEdit25: TDBEdit
      Left = 416
      Top = 544
      Width = 17
      Height = 21
      DataField = 'FLAG_EXCL'
      DataSource = DataModule2.dsPRECOS
      TabOrder = 24
    end
  end
  inherited Panel2: TPanel
    Top = 654
    inherited DBButton4: TDBButton
      DataSource = DataModule2.dsPRECOS
    end
    inherited DBButton5: TDBButton
      Enabled = True
      DataSource = DataModule2.dsPRECOS
    end
  end
  inherited Panel3: TPanel
    inherited DBButton1: TDBButton
      Enabled = True
      DataSource = DataModule2.dsPRECOS
    end
    inherited DBButton2: TDBButton
      Enabled = True
      DataSource = DataModule2.dsPRECOS
    end
    inherited DBButton3: TDBButton
      Enabled = True
      DataSource = DataModule2.dsPRECOS
    end
    inherited DBNavigator1: TDBNavigator
      DataSource = DataModule2.dsPRECOS
      Hints.Strings = ()
    end
  end
end
