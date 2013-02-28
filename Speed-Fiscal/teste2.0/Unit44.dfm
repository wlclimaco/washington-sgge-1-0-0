inherited FRMETIQUETA: TFRMETIQUETA
  Width = 503
  Height = 474
  Caption = 'FRMETIQUETA'
  PixelsPerInch = 96
  TextHeight = 13
  inherited Panel1: TPanel
    Width = 495
    Height = 332
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
      Width = 30
      Height = 13
      Caption = 'QTDE'
      FocusControl = DBEdit2
    end
    object Label3: TLabel
      Left = 32
      Top = 88
      Width = 26
      Height = 13
      Caption = 'LOJA'
      FocusControl = DBEdit3
    end
    object Label4: TLabel
      Left = 32
      Top = 128
      Width = 54
      Height = 13
      Caption = 'PRODUTO'
      FocusControl = DBEdit4
    end
    object Label5: TLabel
      Left = 32
      Top = 168
      Width = 21
      Height = 13
      Caption = 'REF'
      FocusControl = DBEdit5
    end
    object Label6: TLabel
      Left = 32
      Top = 208
      Width = 27
      Height = 13
      Caption = 'TAB1'
      FocusControl = DBEdit6
    end
    object Label7: TLabel
      Left = 32
      Top = 248
      Width = 27
      Height = 13
      Caption = 'TAB2'
      FocusControl = DBEdit7
    end
    object Label8: TLabel
      Left = 32
      Top = 288
      Width = 27
      Height = 13
      Caption = 'TAB3'
      FocusControl = DBEdit8
    end
    object DBEdit1: TDBEdit
      Left = 32
      Top = 24
      Width = 134
      Height = 21
      DataField = 'CODIGO'
      DataSource = DataModule2.dsETIQUETA
      TabOrder = 0
    end
    object DBEdit2: TDBEdit
      Left = 32
      Top = 64
      Width = 134
      Height = 21
      DataField = 'QTDE'
      DataSource = DataModule2.dsETIQUETA
      TabOrder = 1
    end
    object DBEdit3: TDBEdit
      Left = 32
      Top = 104
      Width = 82
      Height = 21
      DataField = 'LOJA'
      DataSource = DataModule2.dsETIQUETA
      TabOrder = 2
    end
    object DBEdit4: TDBEdit
      Left = 32
      Top = 144
      Width = 199
      Height = 21
      DataField = 'PRODUTO'
      DataSource = DataModule2.dsETIQUETA
      TabOrder = 3
    end
    object DBEdit5: TDBEdit
      Left = 32
      Top = 184
      Width = 199
      Height = 21
      DataField = 'REF'
      DataSource = DataModule2.dsETIQUETA
      TabOrder = 4
    end
    object DBEdit6: TDBEdit
      Left = 32
      Top = 224
      Width = 134
      Height = 21
      DataField = 'TAB1'
      DataSource = DataModule2.dsETIQUETA
      TabOrder = 5
    end
    object DBEdit7: TDBEdit
      Left = 32
      Top = 264
      Width = 134
      Height = 21
      DataField = 'TAB2'
      DataSource = DataModule2.dsETIQUETA
      TabOrder = 6
    end
    object DBEdit8: TDBEdit
      Left = 32
      Top = 304
      Width = 134
      Height = 21
      DataField = 'TAB3'
      DataSource = DataModule2.dsETIQUETA
      TabOrder = 7
    end
  end
  inherited Panel2: TPanel
    Top = 389
    Width = 495
    inherited DBButton4: TDBButton
      Left = 400
      DataSource = DataModule2.dsETIQUETA
    end
    inherited DBButton5: TDBButton
      Left = 312
      DataSource = DataModule2.dsETIQUETA
    end
  end
  inherited Panel3: TPanel
    Width = 495
    inherited DBButton1: TDBButton
      Enabled = True
      DataSource = DataModule2.dsETIQUETA
    end
    inherited DBButton2: TDBButton
      DataSource = DataModule2.dsETIQUETA
    end
    inherited DBButton3: TDBButton
      Enabled = True
      DataSource = DataModule2.dsETIQUETA
    end
    inherited DBNavigator1: TDBNavigator
      DataSource = DataModule2.dsETIQUETA
      Hints.Strings = ()
    end
  end
end
