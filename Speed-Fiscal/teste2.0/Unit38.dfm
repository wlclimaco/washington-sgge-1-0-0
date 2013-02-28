inherited FRMCOC: TFRMCOC
  Left = 207
  Top = 134
  Height = 666
  Caption = 'FRMCOC'
  OldCreateOrder = True
  PixelsPerInch = 96
  TextHeight = 13
  inherited Panel1: TPanel
    Height = 524
    object Label1: TLabel
      Left = 16
      Top = 0
      Width = 42
      Height = 13
      Caption = 'CODIGO'
      FocusControl = DBEdit1
    end
    object Label2: TLabel
      Left = 16
      Top = 40
      Width = 32
      Height = 13
      Caption = 'NOME'
      FocusControl = DBEdit2
    end
    object Label3: TLabel
      Left = 16
      Top = 80
      Width = 26
      Height = 13
      Caption = 'ICMS'
      FocusControl = DBEdit3
    end
    object Label4: TLabel
      Left = 16
      Top = 120
      Width = 44
      Height = 13
      Caption = 'FUNDES'
      FocusControl = DBEdit4
    end
    object Label5: TLabel
      Left = 16
      Top = 160
      Width = 32
      Height = 13
      Caption = 'COFIN'
      FocusControl = DBEdit5
    end
    object Label6: TLabel
      Left = 16
      Top = 200
      Width = 17
      Height = 13
      Caption = 'PIS'
      FocusControl = DBEdit6
    end
    object Label7: TLabel
      Left = 16
      Top = 240
      Width = 38
      Height = 13
      Caption = 'CONTR'
      FocusControl = DBEdit7
    end
    object Label8: TLabel
      Left = 16
      Top = 280
      Width = 23
      Height = 13
      Caption = 'IRPJ'
      FocusControl = DBEdit8
    end
    object Label9: TLabel
      Left = 16
      Top = 320
      Width = 43
      Height = 13
      Caption = 'MICROG'
      FocusControl = DBEdit9
    end
    object Label10: TLabel
      Left = 16
      Top = 360
      Width = 24
      Height = 13
      Caption = 'ADM'
      FocusControl = DBEdit10
    end
    object Label11: TLabel
      Left = 16
      Top = 400
      Width = 41
      Height = 13
      Caption = 'COMISS'
      FocusControl = DBEdit11
    end
    object Label12: TLabel
      Left = 16
      Top = 440
      Width = 37
      Height = 13
      Caption = 'LUCRO'
      FocusControl = DBEdit12
    end
    object Label13: TLabel
      Left = 392
      Top = 0
      Width = 28
      Height = 13
      Caption = 'BASE'
      FocusControl = DBEdit13
    end
    object Label14: TLabel
      Left = 392
      Top = 40
      Width = 28
      Height = 13
      Caption = 'TAXA'
      FocusControl = DBEdit14
    end
    object Label15: TLabel
      Left = 392
      Top = 80
      Width = 46
      Height = 13
      Caption = 'MARKUP'
      FocusControl = DBEdit15
    end
    object Label16: TLabel
      Left = 392
      Top = 120
      Width = 43
      Height = 13
      Caption = 'AQUISIC'
      FocusControl = DBEdit16
    end
    object Label17: TLabel
      Left = 392
      Top = 160
      Width = 21
      Height = 13
      Caption = 'PVV'
      FocusControl = DBEdit17
    end
    object Label18: TLabel
      Left = 392
      Top = 200
      Width = 31
      Height = 13
      Caption = 'CFIXO'
      FocusControl = DBEdit18
    end
    object Label19: TLabel
      Left = 392
      Top = 240
      Width = 48
      Height = 13
      Caption = 'MARGEM'
      FocusControl = DBEdit19
    end
    object Label20: TLabel
      Left = 392
      Top = 280
      Width = 43
      Height = 13
      Caption = 'FATURA'
      FocusControl = DBEdit20
    end
    object Label21: TLabel
      Left = 392
      Top = 320
      Width = 55
      Height = 13
      Caption = 'CONSUMO'
      FocusControl = DBEdit21
    end
    object Label22: TLabel
      Left = 392
      Top = 360
      Width = 56
      Height = 13
      Caption = 'UNIDADES'
      FocusControl = DBEdit22
    end
    object DBEdit1: TDBEdit
      Left = 16
      Top = 16
      Width = 134
      Height = 21
      DataField = 'CODIGO'
      DataSource = DataModule2.dsCOC
      TabOrder = 0
    end
    object DBEdit2: TDBEdit
      Left = 16
      Top = 56
      Width = 345
      Height = 21
      DataField = 'NOME'
      DataSource = DataModule2.dsCOC
      TabOrder = 1
    end
    object DBEdit3: TDBEdit
      Left = 16
      Top = 96
      Width = 134
      Height = 21
      DataField = 'ICMS'
      DataSource = DataModule2.dsCOC
      TabOrder = 2
    end
    object DBEdit4: TDBEdit
      Left = 16
      Top = 136
      Width = 134
      Height = 21
      DataField = 'FUNDES'
      DataSource = DataModule2.dsCOC
      TabOrder = 3
    end
    object DBEdit5: TDBEdit
      Left = 16
      Top = 176
      Width = 134
      Height = 21
      DataField = 'COFIN'
      DataSource = DataModule2.dsCOC
      TabOrder = 4
    end
    object DBEdit6: TDBEdit
      Left = 16
      Top = 216
      Width = 134
      Height = 21
      DataField = 'PIS'
      DataSource = DataModule2.dsCOC
      TabOrder = 5
    end
    object DBEdit7: TDBEdit
      Left = 16
      Top = 256
      Width = 134
      Height = 21
      DataField = 'CONTR'
      DataSource = DataModule2.dsCOC
      TabOrder = 6
    end
    object DBEdit8: TDBEdit
      Left = 16
      Top = 296
      Width = 134
      Height = 21
      DataField = 'IRPJ'
      DataSource = DataModule2.dsCOC
      TabOrder = 7
    end
    object DBEdit9: TDBEdit
      Left = 16
      Top = 336
      Width = 134
      Height = 21
      DataField = 'MICROG'
      DataSource = DataModule2.dsCOC
      TabOrder = 8
    end
    object DBEdit10: TDBEdit
      Left = 16
      Top = 376
      Width = 134
      Height = 21
      DataField = 'ADM'
      DataSource = DataModule2.dsCOC
      TabOrder = 9
    end
    object DBEdit11: TDBEdit
      Left = 16
      Top = 416
      Width = 134
      Height = 21
      DataField = 'COMISS'
      DataSource = DataModule2.dsCOC
      TabOrder = 10
    end
    object DBEdit12: TDBEdit
      Left = 16
      Top = 456
      Width = 134
      Height = 21
      DataField = 'LUCRO'
      DataSource = DataModule2.dsCOC
      TabOrder = 11
    end
    object DBEdit13: TDBEdit
      Left = 392
      Top = 16
      Width = 134
      Height = 21
      DataField = 'BASE'
      DataSource = DataModule2.dsCOC
      TabOrder = 12
    end
    object DBEdit14: TDBEdit
      Left = 392
      Top = 56
      Width = 134
      Height = 21
      DataField = 'TAXA'
      DataSource = DataModule2.dsCOC
      TabOrder = 13
    end
    object DBEdit15: TDBEdit
      Left = 392
      Top = 96
      Width = 134
      Height = 21
      DataField = 'MARKUP'
      DataSource = DataModule2.dsCOC
      TabOrder = 14
    end
    object DBEdit16: TDBEdit
      Left = 392
      Top = 136
      Width = 134
      Height = 21
      DataField = 'AQUISIC'
      DataSource = DataModule2.dsCOC
      TabOrder = 15
    end
    object DBEdit17: TDBEdit
      Left = 392
      Top = 176
      Width = 134
      Height = 21
      DataField = 'PVV'
      DataSource = DataModule2.dsCOC
      TabOrder = 16
    end
    object DBEdit18: TDBEdit
      Left = 392
      Top = 216
      Width = 134
      Height = 21
      DataField = 'CFIXO'
      DataSource = DataModule2.dsCOC
      TabOrder = 17
    end
    object DBEdit19: TDBEdit
      Left = 392
      Top = 256
      Width = 134
      Height = 21
      DataField = 'MARGEM'
      DataSource = DataModule2.dsCOC
      TabOrder = 18
    end
    object DBEdit20: TDBEdit
      Left = 392
      Top = 296
      Width = 134
      Height = 21
      DataField = 'FATURA'
      DataSource = DataModule2.dsCOC
      TabOrder = 19
    end
    object DBEdit21: TDBEdit
      Left = 392
      Top = 336
      Width = 134
      Height = 21
      DataField = 'CONSUMO'
      DataSource = DataModule2.dsCOC
      TabOrder = 20
    end
    object DBEdit22: TDBEdit
      Left = 392
      Top = 376
      Width = 134
      Height = 21
      DataField = 'UNIDADES'
      DataSource = DataModule2.dsCOC
      TabOrder = 21
    end
  end
  inherited Panel2: TPanel
    Top = 581
    inherited DBButton4: TDBButton
      DataSource = DataModule2.dsCOC
    end
    inherited DBButton5: TDBButton
      Enabled = True
      DataSource = DataModule2.dsCOC
    end
  end
  inherited Panel3: TPanel
    inherited DBButton1: TDBButton
      Enabled = True
      DataSource = DataModule2.dsCOC
    end
    inherited DBButton2: TDBButton
      Enabled = True
      DataSource = DataModule2.dsCOC
    end
    inherited DBButton3: TDBButton
      Enabled = True
      DataSource = DataModule2.dsCOC
    end
    inherited DBNavigator1: TDBNavigator
      DataSource = DataModule2.dsCOC
      Hints.Strings = ()
    end
  end
end
