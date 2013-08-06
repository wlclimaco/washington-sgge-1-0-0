inherited Cai0002: TCai0002
  Caption = 'Cai0002 - Altera'#231#227'o de senha'
  ClientHeight = 90
  ClientWidth = 236
  ExplicitWidth = 242
  ExplicitHeight = 122
  PixelsPerInch = 96
  TextHeight = 13
  inherited Panel1: TPanel
    Width = 236
    Height = 90
    ExplicitWidth = 236
    ExplicitHeight = 90
    object Label1: TLabel [0]
      Left = 10
      Top = 6
      Width = 65
      Height = 13
      Caption = 'Nova senha'
      Font.Charset = DEFAULT_CHARSET
      Font.Color = clWindowText
      Font.Height = -11
      Font.Name = 'Tahoma'
      Font.Style = [fsBold]
      ParentFont = False
    end
    object Label3: TLabel [1]
      Left = 10
      Top = 30
      Width = 71
      Height = 13
      Caption = 'Confirma'#231#227'o'
      Font.Charset = DEFAULT_CHARSET
      Font.Color = clWindowText
      Font.Height = -11
      Font.Name = 'Tahoma'
      Font.Style = [fsBold]
      ParentFont = False
    end
    inherited Panel2: TPanel
      Top = 53
      Width = 230
      TabOrder = 2
      ExplicitTop = 53
      ExplicitWidth = 230
      inherited BbtOk: TBrvBitBtn
        Left = 29
        Width = 81
        Default = True
        OnClick = BbtOkClick
        ExplicitLeft = 29
        ExplicitWidth = 81
      end
      inherited BbtCancel: TBrvBitBtn
        Left = 117
        Width = 81
        ExplicitLeft = 117
        ExplicitWidth = 81
      end
    end
    object EdtDsNewSe1: TEdit
      Left = 104
      Top = 2
      Width = 121
      Height = 21
      CharCase = ecLowerCase
      MaxLength = 10
      PasswordChar = '*'
      TabOrder = 0
    end
    object EdtDsNewSe2: TEdit
      Left = 104
      Top = 26
      Width = 121
      Height = 21
      CharCase = ecLowerCase
      MaxLength = 10
      PasswordChar = '*'
      TabOrder = 1
    end
  end
  object BrvSenha: TBrvSenha
    Codigo = 0
    Left = 160
    Top = 8
  end
end
