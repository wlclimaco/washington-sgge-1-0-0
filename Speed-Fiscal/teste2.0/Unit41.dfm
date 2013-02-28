inherited FRMEMPREGAD: TFRMEMPREGAD
  Left = 320
  Top = 195
  Width = 538
  Height = 369
  Caption = 'FRMEMPREGAD'
  OldCreateOrder = True
  PixelsPerInch = 96
  TextHeight = 13
  inherited Panel1: TPanel
    Width = 403
    Height = 270
    object Label1: TLabel
      Left = 56
      Top = 8
      Width = 46
      Height = 13
      Caption = 'CODEMP'
      FocusControl = DBEdit1
    end
    object Label2: TLabel
      Left = 56
      Top = 48
      Width = 32
      Height = 13
      Caption = 'NOME'
      FocusControl = DBEdit2
    end
    object Label3: TLabel
      Left = 56
      Top = 88
      Width = 38
      Height = 13
      Caption = 'CARGO'
      FocusControl = DBEdit3
    end
    object Label4: TLabel
      Left = 56
      Top = 128
      Width = 31
      Height = 13
      Caption = 'SIGLA'
      FocusControl = DBEdit4
    end
    object Label5: TLabel
      Left = 56
      Top = 168
      Width = 56
      Height = 13
      Caption = 'COMISSAO'
      FocusControl = DBEdit5
    end
    object DBEdit1: TDBEdit
      Left = 56
      Top = 24
      Width = 160
      Height = 21
      DataField = 'CODEMP'
      DataSource = DataModule2.dsEMPREGAD
      TabOrder = 0
    end
    object DBEdit2: TDBEdit
      Left = 56
      Top = 64
      Width = 316
      Height = 21
      DataField = 'NOME'
      DataSource = DataModule2.dsEMPREGAD
      TabOrder = 1
    end
    object DBEdit3: TDBEdit
      Left = 56
      Top = 104
      Width = 238
      Height = 21
      DataField = 'CARGO'
      DataSource = DataModule2.dsEMPREGAD
      TabOrder = 2
    end
    object DBEdit4: TDBEdit
      Left = 56
      Top = 144
      Width = 147
      Height = 21
      DataField = 'SIGLA'
      DataSource = DataModule2.dsEMPREGAD
      TabOrder = 3
    end
    object DBEdit5: TDBEdit
      Left = 56
      Top = 184
      Width = 160
      Height = 21
      DataField = 'COMISSAO'
      DataSource = DataModule2.dsEMPREGAD
      TabOrder = 4
    end
  end
  inherited Panel3: TPanel
    Width = 530
    object Label6: TLabel
      Left = 24
      Top = 24
      Width = 211
      Height = 24
      Caption = 'Cadastro Funcionarios'
      Font.Charset = DEFAULT_CHARSET
      Font.Color = clWindowText
      Font.Height = -19
      Font.Name = 'MS Sans Serif'
      Font.Style = [fsBold]
      ParentFont = False
    end
  end
  inherited Panel2: TPanel
    Left = 403
    Width = 127
    Height = 270
    inherited DBButton1: TDBButton
      Enabled = True
      DataSource = DataModule2.dsEMPREGAD
    end
    inherited DBButton2: TDBButton
      DataSource = DataModule2.dsEMPREGAD
    end
    inherited DBButton3: TDBButton
      Enabled = True
      DataSource = DataModule2.dsEMPREGAD
    end
    inherited DBButton4: TDBButton
      Enabled = True
      DataSource = DataModule2.dsEMPREGAD
    end
    inherited DBButton5: TDBButton
      DataSource = DataModule2.dsEMPREGAD
    end
    inherited DBNavigator1: TDBNavigator
      Left = 25
      DataSource = DataModule2.dsEMPREGAD
      Hints.Strings = ()
    end
  end
end
