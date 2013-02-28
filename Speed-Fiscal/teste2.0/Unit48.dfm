inherited FRMMERCAD: TFRMMERCAD
  Width = 500
  Height = 473
  Caption = 'FRMMERCAD'
  PixelsPerInch = 96
  TextHeight = 13
  inherited Panel1: TPanel
    Width = 492
    Height = 331
    object Label1: TLabel
      Left = 24
      Top = 8
      Width = 23
      Height = 13
      Caption = 'COD'
      FocusControl = DBEdit1
    end
    object Label2: TLabel
      Left = 24
      Top = 48
      Width = 46
      Height = 13
      Caption = 'CODMAT'
      FocusControl = DBEdit2
    end
    object Label3: TLabel
      Left = 24
      Top = 88
      Width = 54
      Height = 13
      Caption = 'PRODUTO'
      FocusControl = DBEdit3
    end
    object Label4: TLabel
      Left = 24
      Top = 128
      Width = 21
      Height = 13
      Caption = 'REF'
      FocusControl = DBEdit4
    end
    object Label5: TLabel
      Left = 24
      Top = 168
      Width = 30
      Height = 13
      Caption = 'QTDE'
      FocusControl = DBEdit5
    end
    object Label6: TLabel
      Left = 24
      Top = 208
      Width = 26
      Height = 13
      Caption = 'UNIT'
      FocusControl = DBEdit6
    end
    object Label7: TLabel
      Left = 24
      Top = 248
      Width = 35
      Height = 13
      Caption = 'TOTAL'
      FocusControl = DBEdit7
    end
    object Label8: TLabel
      Left = 24
      Top = 288
      Width = 60
      Height = 13
      Caption = 'FLAG_EXCL'
      FocusControl = DBEdit8
    end
    object DBEdit1: TDBEdit
      Left = 24
      Top = 24
      Width = 134
      Height = 21
      DataField = 'COD'
      DataSource = DataModule2.dsMERCAD
      TabOrder = 0
    end
    object DBEdit2: TDBEdit
      Left = 24
      Top = 64
      Width = 134
      Height = 21
      DataField = 'CODMAT'
      DataSource = DataModule2.dsMERCAD
      TabOrder = 1
    end
    object DBEdit3: TDBEdit
      Left = 24
      Top = 104
      Width = 199
      Height = 21
      DataField = 'PRODUTO'
      DataSource = DataModule2.dsMERCAD
      TabOrder = 2
    end
    object DBEdit4: TDBEdit
      Left = 24
      Top = 144
      Width = 199
      Height = 21
      DataField = 'REF'
      DataSource = DataModule2.dsMERCAD
      TabOrder = 3
    end
    object DBEdit5: TDBEdit
      Left = 24
      Top = 184
      Width = 134
      Height = 21
      DataField = 'QTDE'
      DataSource = DataModule2.dsMERCAD
      TabOrder = 4
    end
    object DBEdit6: TDBEdit
      Left = 24
      Top = 224
      Width = 134
      Height = 21
      DataField = 'UNIT'
      DataSource = DataModule2.dsMERCAD
      TabOrder = 5
    end
    object DBEdit7: TDBEdit
      Left = 24
      Top = 264
      Width = 134
      Height = 21
      DataField = 'TOTAL'
      DataSource = DataModule2.dsMERCAD
      TabOrder = 6
    end
    object DBEdit8: TDBEdit
      Left = 24
      Top = 304
      Width = 17
      Height = 21
      DataField = 'FLAG_EXCL'
      DataSource = DataModule2.dsMERCAD
      TabOrder = 7
    end
  end
  inherited Panel2: TPanel
    Top = 388
    Width = 492
    inherited DBButton4: TDBButton
      Left = 408
      DataSource = DataModule2.dsMERCAD
    end
    inherited DBButton5: TDBButton
      Left = 328
      Enabled = True
      DataSource = DataModule2.dsMERCAD
    end
  end
  inherited Panel3: TPanel
    Width = 492
    inherited DBButton1: TDBButton
      Enabled = True
      DataSource = DataModule2.dsMERCAD
    end
    inherited DBButton2: TDBButton
      Enabled = True
      DataSource = DataModule2.dsMERCAD
    end
    inherited DBButton3: TDBButton
      Enabled = True
      DataSource = DataModule2.dsMERCAD
    end
    inherited DBNavigator1: TDBNavigator
      DataSource = DataModule2.dsMERCAD
      Hints.Strings = ()
    end
  end
end
