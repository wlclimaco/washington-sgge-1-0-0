inherited Form70: TForm70
  Left = 326
  Top = 207
  Width = 562
  Height = 346
  Caption = 'Form70'
  OldCreateOrder = True
  PixelsPerInch = 96
  TextHeight = 13
  inherited Panel1: TPanel
    Width = 426
    Height = 247
    object Label1: TLabel
      Left = 16
      Top = 16
      Width = 43
      Height = 13
      Caption = 'Natureza'
      FocusControl = DBEdit1
    end
    object Label2: TLabel
      Left = 16
      Top = 56
      Width = 48
      Height = 13
      Caption = 'Descricao'
      FocusControl = DBEdit2
    end
    object DBEdit1: TDBEdit
      Left = 16
      Top = 32
      Width = 134
      Height = 21
      DataField = 'Natureza'
      DataSource = DataSource1
      TabOrder = 0
    end
    object DBEdit2: TDBEdit
      Left = 16
      Top = 72
      Width = 369
      Height = 21
      DataField = 'Descricao'
      DataSource = DataSource1
      TabOrder = 1
    end
  end
  inherited Panel3: TPanel
    Width = 554
    object Label3: TLabel
      Left = 24
      Top = 24
      Width = 217
      Height = 29
      Caption = 'Cadastro Natureza'
      Font.Charset = DEFAULT_CHARSET
      Font.Color = clWindowText
      Font.Height = -24
      Font.Name = 'MS Sans Serif'
      Font.Style = [fsBold]
      ParentFont = False
    end
  end
  inherited Panel2: TPanel
    Left = 426
    Width = 128
    Height = 247
    inherited DBButton1: TDBButton
      Enabled = True
      DataSource = DataSource1
    end
    inherited DBButton2: TDBButton
      DataSource = DataSource1
    end
    inherited DBButton3: TDBButton
      Enabled = True
      DataSource = DataSource1
    end
    inherited DBButton4: TDBButton
      Enabled = True
      DataSource = DataSource1
    end
    inherited DBButton5: TDBButton
      DataSource = DataSource1
    end
    inherited DBNavigator1: TDBNavigator
      DataSource = DataSource1
      Hints.Strings = ()
    end
  end
  object DataSource1: TDataSource
    DataSet = DataModule2.tblnatureza
    Left = 232
    Top = 72
  end
end
