inherited FRMLOJAS: TFRMLOJAS
  Width = 582
  Height = 347
  Caption = 'FRMLOJAS'
  OldCreateOrder = True
  PixelsPerInch = 96
  TextHeight = 13
  inherited Panel1: TPanel
    Width = 440
    Height = 248
    object Label1: TLabel
      Left = 40
      Top = 16
      Width = 42
      Height = 13
      Caption = 'CODLOJ'
      FocusControl = DBEdit1
    end
    object Label2: TLabel
      Left = 40
      Top = 56
      Width = 26
      Height = 13
      Caption = 'LOJA'
      FocusControl = DBEdit2
    end
    object Label3: TLabel
      Left = 40
      Top = 96
      Width = 31
      Height = 13
      Caption = 'SIGLA'
      FocusControl = DBEdit3
    end
    object DBEdit1: TDBEdit
      Left = 40
      Top = 32
      Width = 134
      Height = 21
      DataField = 'CODLOJ'
      DataSource = DataModule2.dsLOJAS
      TabOrder = 0
    end
    object DBEdit2: TDBEdit
      Left = 40
      Top = 72
      Width = 199
      Height = 21
      DataField = 'LOJA'
      DataSource = DataModule2.dsLOJAS
      TabOrder = 1
    end
    object DBEdit3: TDBEdit
      Left = 40
      Top = 112
      Width = 82
      Height = 21
      DataField = 'SIGLA'
      DataSource = DataModule2.dsLOJAS
      TabOrder = 2
    end
  end
  inherited Panel3: TPanel
    Width = 574
    object Label4: TLabel
      Left = 32
      Top = 24
      Width = 128
      Height = 24
      Caption = 'Cadastro Loja'
      Font.Charset = DEFAULT_CHARSET
      Font.Color = clWindowText
      Font.Height = -19
      Font.Name = 'MS Sans Serif'
      Font.Style = [fsBold]
      ParentFont = False
    end
  end
  inherited Panel2: TPanel
    Left = 440
    Width = 134
    Height = 248
    inherited DBButton1: TDBButton
      Enabled = True
      DataSource = DataModule2.dsLOJAS
    end
    inherited DBButton2: TDBButton
      DataSource = DataModule2.dsLOJAS
    end
    inherited DBButton3: TDBButton
      Enabled = True
      DataSource = DataModule2.dsLOJAS
    end
    inherited DBButton4: TDBButton
      Enabled = True
      DataSource = DataModule2.dsLOJAS
    end
    inherited DBButton5: TDBButton
      DataSource = DataModule2.dsLOJAS
    end
    inherited DBNavigator1: TDBNavigator
      DataSource = DataModule2.dsLOJAS
      Hints.Strings = ()
    end
  end
end
