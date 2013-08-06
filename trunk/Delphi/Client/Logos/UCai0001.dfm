inherited Cai0001: TCai0001
  Left = 856
  Top = 299
  Caption = 'Cai0001 - Autentica'#231#227'o'
  ClientHeight = 103
  ClientWidth = 220
  Color = clBtnFace
  Ctl3D = True
  FormStyle = fsStayOnTop
  OnShow = FormShow
  ExplicitWidth = 226
  ExplicitHeight = 128
  PixelsPerInch = 96
  TextHeight = 13
  inherited Panel1: TPanel
    Width = 220
    Height = 103
    ExplicitWidth = 220
    ExplicitHeight = 103
    object Label1: TLabel [0]
      Left = 10
      Top = 10
      Width = 30
      Height = 13
      Caption = 'Login'
      Font.Charset = DEFAULT_CHARSET
      Font.Color = clWindowText
      Font.Height = -11
      Font.Name = 'Tahoma'
      Font.Style = [fsBold]
      ParentFont = False
    end
    object Label2: TLabel [1]
      Left = 10
      Top = 38
      Width = 35
      Height = 13
      Caption = 'Senha'
      Font.Charset = DEFAULT_CHARSET
      Font.Color = clWindowText
      Font.Height = -11
      Font.Name = 'Tahoma'
      Font.Style = [fsBold]
      ParentFont = False
    end
    inherited Panel2: TPanel
      Top = 66
      Width = 214
      TabOrder = 2
      ExplicitTop = 66
      ExplicitWidth = 214
      inherited BbtOk: TBrvBitBtn
        Left = 15
        Width = 91
        Default = True
        OnClick = BbtOkClick
        ExplicitLeft = 15
        ExplicitWidth = 91
      end
      inherited BbtCancel: TBrvBitBtn
        Left = 109
        Width = 90
        ExplicitLeft = 109
        ExplicitWidth = 90
      end
    end
    object EdtDsLogin: TBrvEdit
      Left = 51
      Top = 8
      Width = 150
      Height = 21
      CharCase = ecLowerCase
      MaxLength = 20
      TabOrder = 0
      Text = 'wilson.filho'
      BrVisibleButton = False
      BrFunctionButton = VeConsulta
      BrAlignment = taLeftJustify
      BrvQueryCode = 0
      BrRecordar = True
    end
    object EdtDsSenha: TEdit
      Left = 51
      Top = 35
      Width = 149
      Height = 21
      MaxLength = 10
      PasswordChar = '*'
      TabOrder = 1
    end
  end
  object BrvSenha: TBrvSenha
    Codigo = 0
    Left = 168
    Top = 8
  end
end
