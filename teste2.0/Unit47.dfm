inherited FRMMATERIAL: TFRMMATERIAL
  Caption = 'FRMMATERIAL'
  PixelsPerInch = 96
  TextHeight = 13
  inherited Panel1: TPanel
    object Label1: TLabel
      Left = 32
      Top = 8
      Width = 42
      Height = 13
      Caption = 'CODIGO'
      FocusControl = DBEdit1
    end
    object Label2: TLabel
      Left = 32
      Top = 48
      Width = 46
      Height = 13
      Caption = 'CODPRO'
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
      Width = 25
      Height = 13
      Caption = 'TIPO'
      FocusControl = DBEdit5
    end
    object Label6: TLabel
      Left = 32
      Top = 208
      Width = 36
      Height = 13
      Caption = 'VALOR'
      FocusControl = DBEdit6
    end
    object Label7: TLabel
      Left = 32
      Top = 248
      Width = 30
      Height = 13
      Caption = 'QTDE'
      FocusControl = DBEdit7
    end
    object Label8: TLabel
      Left = 32
      Top = 288
      Width = 45
      Height = 13
      Caption = 'PARCIAL'
      FocusControl = DBEdit8
    end
    object Label9: TLabel
      Left = 32
      Top = 328
      Width = 60
      Height = 13
      Caption = 'FLAG_EXCL'
      FocusControl = DBEdit9
    end
    object DBEdit1: TDBEdit
      Left = 32
      Top = 24
      Width = 134
      Height = 21
      DataField = 'CODIGO'
      DataSource = DataModule2.dsMATERIAL
      TabOrder = 0
    end
    object DBEdit2: TDBEdit
      Left = 32
      Top = 64
      Width = 134
      Height = 21
      DataField = 'CODPRO'
      DataSource = DataModule2.dsMATERIAL
      TabOrder = 1
    end
    object DBEdit3: TDBEdit
      Left = 32
      Top = 104
      Width = 199
      Height = 21
      DataField = 'PRODUTO'
      DataSource = DataModule2.dsMATERIAL
      TabOrder = 2
    end
    object DBEdit4: TDBEdit
      Left = 32
      Top = 144
      Width = 199
      Height = 21
      DataField = 'REF'
      DataSource = DataModule2.dsMATERIAL
      TabOrder = 3
    end
    object DBEdit5: TDBEdit
      Left = 32
      Top = 184
      Width = 69
      Height = 21
      DataField = 'TIPO'
      DataSource = DataModule2.dsMATERIAL
      TabOrder = 4
    end
    object DBEdit6: TDBEdit
      Left = 32
      Top = 224
      Width = 134
      Height = 21
      DataField = 'VALOR'
      DataSource = DataModule2.dsMATERIAL
      TabOrder = 5
    end
    object DBEdit7: TDBEdit
      Left = 32
      Top = 264
      Width = 134
      Height = 21
      DataField = 'QTDE'
      DataSource = DataModule2.dsMATERIAL
      TabOrder = 6
    end
    object DBEdit8: TDBEdit
      Left = 32
      Top = 304
      Width = 134
      Height = 21
      DataField = 'PARCIAL'
      DataSource = DataModule2.dsMATERIAL
      TabOrder = 7
    end
    object DBEdit9: TDBEdit
      Left = 32
      Top = 344
      Width = 17
      Height = 21
      DataField = 'FLAG_EXCL'
      DataSource = DataModule2.dsMATERIAL
      TabOrder = 8
    end
  end
  inherited Panel2: TPanel
    inherited DBButton4: TDBButton
      DataSource = DataModule2.dsMATERIAL
    end
    inherited DBButton5: TDBButton
      DataSource = DataModule2.dsMATERIAL
    end
  end
  inherited Panel3: TPanel
    inherited DBButton1: TDBButton
      Enabled = True
      DataSource = DataModule2.dsMATERIAL
    end
    inherited DBButton2: TDBButton
      DataSource = DataModule2.dsMATERIAL
    end
    inherited DBButton3: TDBButton
      Enabled = True
      DataSource = DataModule2.dsMATERIAL
    end
    inherited DBNavigator1: TDBNavigator
      DataSource = DataModule2.dsMATERIAL
      Hints.Strings = ()
    end
  end
end
