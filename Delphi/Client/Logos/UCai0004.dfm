inherited Cai0004: TCai0004
  Caption = 'Cai0004 - Valores Default'
  ClientHeight = 118
  OnCreate = FormCreate
  ExplicitWidth = 434
  ExplicitHeight = 143
  PixelsPerInch = 96
  TextHeight = 13
  inherited Panel1: TPanel
    Height = 118
    ExplicitHeight = 118
    object Label1: TLabel [0]
      Left = 13
      Top = 8
      Width = 54
      Height = 13
      Caption = 'Opera'#231#227'o'
      Font.Charset = DEFAULT_CHARSET
      Font.Color = clBlue
      Font.Height = -11
      Font.Name = 'Tahoma'
      Font.Style = [fsBold]
      ParentFont = False
    end
    object Label2: TLabel [1]
      Left = 13
      Top = 34
      Width = 29
      Height = 13
      Caption = 'Valor'
      Font.Charset = DEFAULT_CHARSET
      Font.Color = clBlue
      Font.Height = -11
      Font.Name = 'Tahoma'
      Font.Style = [fsBold]
      ParentFont = False
    end
    object LblVrFixo: TLabel [2]
      Left = 88
      Top = 56
      Width = 32
      Height = 13
      Caption = 'Outro'
      Font.Charset = DEFAULT_CHARSET
      Font.Color = clBlack
      Font.Height = -11
      Font.Name = 'Tahoma'
      Font.Style = [fsBold]
      ParentFont = False
    end
    inherited Panel2: TPanel
      Top = 81
      ExplicitTop = 81
      inherited BbtOk: TBrvBitBtn
        Left = 72
        OnClick = BbtOkClick
        ExplicitLeft = 72
      end
      inherited BbtCancel: TBrvBitBtn
        Left = 176
        ExplicitLeft = 176
      end
      object BrvBitBtn1: TBrvBitBtn
        Left = 270
        Top = 3
        Width = 75
        Height = 26
        Caption = 'Limpar'
        DoubleBuffered = True
        NumGlyphs = 2
        ParentDoubleBuffered = False
        TabOrder = 2
        OnClick = BrvBitBtn1Click
        BrTipoBotao = BrBtnLimpar
      end
    end
    object CbxTpOperac: TBrvComboBox
      Left = 88
      Top = 5
      Width = 329
      Height = 21
      Style = csDropDownList
      TabOrder = 1
    end
    object CbxVrDefaul: TBrvComboBox
      Left = 88
      Top = 29
      Width = 329
      Height = 21
      Style = csDropDownList
      TabOrder = 2
      OnChange = CbxVrDefaulChange
    end
    object EdtVrDefaul: TEdit
      Left = 120
      Top = 54
      Width = 297
      Height = 21
      TabOrder = 3
    end
  end
end
