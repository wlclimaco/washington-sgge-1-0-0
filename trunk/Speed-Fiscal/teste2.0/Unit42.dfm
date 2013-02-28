inherited FRMENDERECO: TFRMENDERECO
  Left = 189
  Top = 149
  Height = 634
  Caption = 'FRMENDERECO'
  OldCreateOrder = True
  PixelsPerInch = 96
  TextHeight = 13
  inherited Panel1: TPanel
    Height = 535
    object Label1: TLabel
      Left = 24
      Top = 8
      Width = 39
      Height = 13
      Caption = 'CODCLI'
      FocusControl = DBEdit1
    end
    object Label2: TLabel
      Left = 24
      Top = 48
      Width = 42
      Height = 13
      Caption = 'CODIGO'
      FocusControl = DBEdit2
    end
    object Label3: TLabel
      Left = 24
      Top = 88
      Width = 54
      Height = 13
      Caption = 'SITUACAO'
      FocusControl = DBEdit3
    end
    object Label4: TLabel
      Left = 24
      Top = 128
      Width = 37
      Height = 13
      Caption = 'RAZAO'
      FocusControl = DBEdit4
    end
    object Label5: TLabel
      Left = 24
      Top = 168
      Width = 60
      Height = 13
      Caption = 'ENDERECO'
      FocusControl = DBEdit5
    end
    object Label6: TLabel
      Left = 24
      Top = 208
      Width = 41
      Height = 13
      Caption = 'BAIRRO'
      FocusControl = DBEdit6
    end
    object Label7: TLabel
      Left = 24
      Top = 248
      Width = 56
      Height = 13
      Caption = 'MUNICIPIO'
      FocusControl = DBEdit7
    end
    object Label8: TLabel
      Left = 24
      Top = 288
      Width = 14
      Height = 13
      Caption = 'UF'
      FocusControl = DBEdit8
    end
    object Label9: TLabel
      Left = 24
      Top = 328
      Width = 21
      Height = 13
      Caption = 'CEP'
      FocusControl = DBEdit9
    end
    object Label10: TLabel
      Left = 24
      Top = 368
      Width = 29
      Height = 13
      Caption = 'FONE'
      FocusControl = DBEdit10
    end
    object Label11: TLabel
      Left = 24
      Top = 408
      Width = 49
      Height = 13
      Caption = 'FONEFAX'
      FocusControl = DBEdit11
    end
    object Label12: TLabel
      Left = 24
      Top = 448
      Width = 60
      Height = 13
      Caption = 'FLAG_EXCL'
      FocusControl = DBEdit12
    end
    object DBEdit1: TDBEdit
      Left = 24
      Top = 24
      Width = 134
      Height = 21
      DataField = 'CODCLI'
      DataSource = DataModule2.dsENDERECO
      TabOrder = 0
    end
    object DBEdit2: TDBEdit
      Left = 24
      Top = 64
      Width = 134
      Height = 21
      DataField = 'CODIGO'
      DataSource = DataModule2.dsENDERECO
      TabOrder = 1
    end
    object DBEdit3: TDBEdit
      Left = 24
      Top = 104
      Width = 134
      Height = 21
      DataField = 'SITUACAO'
      DataSource = DataModule2.dsENDERECO
      TabOrder = 2
    end
    object DBEdit4: TDBEdit
      Left = 24
      Top = 144
      Width = 524
      Height = 21
      DataField = 'RAZAO'
      DataSource = DataModule2.dsENDERECO
      TabOrder = 3
    end
    object DBEdit5: TDBEdit
      Left = 24
      Top = 184
      Width = 524
      Height = 21
      DataField = 'ENDERECO'
      DataSource = DataModule2.dsENDERECO
      TabOrder = 4
    end
    object DBEdit6: TDBEdit
      Left = 24
      Top = 224
      Width = 199
      Height = 21
      DataField = 'BAIRRO'
      DataSource = DataModule2.dsENDERECO
      TabOrder = 5
    end
    object DBEdit7: TDBEdit
      Left = 24
      Top = 264
      Width = 264
      Height = 21
      DataField = 'MUNICIPIO'
      DataSource = DataModule2.dsENDERECO
      TabOrder = 6
    end
    object DBEdit8: TDBEdit
      Left = 24
      Top = 304
      Width = 30
      Height = 21
      DataField = 'UF'
      DataSource = DataModule2.dsENDERECO
      TabOrder = 7
    end
    object DBEdit9: TDBEdit
      Left = 24
      Top = 344
      Width = 108
      Height = 21
      DataField = 'CEP'
      DataSource = DataModule2.dsENDERECO
      TabOrder = 8
    end
    object DBEdit10: TDBEdit
      Left = 24
      Top = 384
      Width = 173
      Height = 21
      DataField = 'FONE'
      DataSource = DataModule2.dsENDERECO
      TabOrder = 9
    end
    object DBEdit11: TDBEdit
      Left = 24
      Top = 424
      Width = 173
      Height = 21
      DataField = 'FONEFAX'
      DataSource = DataModule2.dsENDERECO
      TabOrder = 10
    end
    object DBEdit12: TDBEdit
      Left = 24
      Top = 464
      Width = 17
      Height = 21
      DataField = 'FLAG_EXCL'
      DataSource = DataModule2.dsENDERECO
      TabOrder = 11
    end
  end
  inherited Panel3: TPanel
    object Label13: TLabel
      Left = 32
      Top = 24
      Width = 191
      Height = 24
      Caption = 'Cadastro Endere'#231'os'
      Font.Charset = DEFAULT_CHARSET
      Font.Color = clWindowText
      Font.Height = -19
      Font.Name = 'MS Sans Serif'
      Font.Style = [fsBold]
      ParentFont = False
    end
  end
  inherited Panel2: TPanel
    Height = 535
    inherited DBButton1: TDBButton
      Enabled = True
      DataSource = DataModule2.dsENDERECO
    end
    inherited DBButton2: TDBButton
      DataSource = DataModule2.dsENDERECO
    end
    inherited DBButton3: TDBButton
      Enabled = True
      DataSource = DataModule2.dsENDERECO
    end
    inherited DBButton4: TDBButton
      Enabled = True
      DataSource = DataModule2.dsENDERECO
    end
    inherited DBButton5: TDBButton
      DataSource = DataModule2.dsENDERECO
    end
    inherited DBNavigator1: TDBNavigator
      DataSource = DataModule2.dsENDERECO
      Hints.Strings = ()
    end
  end
end
