object FrmActCurrencyEdit: TFrmActCurrencyEdit
  Left = 480
  Top = 232
  BorderStyle = bsToolWindow
  Caption = 'Calculadora'
  ClientHeight = 166
  ClientWidth = 123
  Color = clBtnFace
  Font.Charset = DEFAULT_CHARSET
  Font.Color = clWindowText
  Font.Height = -11
  Font.Name = 'MS Sans Serif'
  Font.Style = []
  FormStyle = fsStayOnTop
  KeyPreview = True
  OldCreateOrder = False
  OnCreate = FormCreate
  OnDeactivate = FormDeactivate
  OnKeyPress = FormKeyPress
  PixelsPerInch = 96
  TextHeight = 13
  object PanBack: TPanel
    Left = 0
    Top = 0
    Width = 123
    Height = 166
    Align = alClient
    BevelOuter = bvNone
    BorderWidth = 2
    Ctl3D = True
    ParentCtl3D = False
    TabOrder = 0
    OnMouseDown = PanMouseDown
    OnMouseMove = PanMouseMove
    object BtnCE: TSpeedButton
      Left = 2
      Top = 26
      Width = 27
      Height = 19
      Caption = 'CE'
      Font.Charset = DEFAULT_CHARSET
      Font.Color = clRed
      Font.Height = -11
      Font.Name = 'MS Sans Serif'
      Font.Style = []
      Layout = blGlyphBottom
      ParentFont = False
      OnClick = BtnCEClick
    end
    object BtnC: TSpeedButton
      Left = 33
      Top = 26
      Width = 27
      Height = 19
      Caption = 'C'
      Font.Charset = DEFAULT_CHARSET
      Font.Color = clRed
      Font.Height = -11
      Font.Name = 'MS Sans Serif'
      Font.Style = []
      Layout = blGlyphBottom
      ParentFont = False
      OnClick = BtnCClick
    end
    object BtnSgn: TSpeedButton
      Left = 64
      Top = 26
      Width = 27
      Height = 19
      Caption = #177
      Font.Charset = DEFAULT_CHARSET
      Font.Color = clBlue
      Font.Height = -16
      Font.Name = 'MS Sans Serif'
      Font.Style = []
      Layout = blGlyphBottom
      ParentFont = False
      OnClick = BtnSgnClick
    end
    object BtnPerc: TSpeedButton
      Left = 95
      Top = 26
      Width = 27
      Height = 19
      Caption = '%'
      Font.Charset = DEFAULT_CHARSET
      Font.Color = clBlue
      Font.Height = -11
      Font.Name = 'MS Sans Serif'
      Font.Style = []
      Layout = blGlyphBottom
      ParentFont = False
      OnClick = BtnPercClick
    end
    object Btn7: TSpeedButton
      Left = 2
      Top = 48
      Width = 27
      Height = 19
      Caption = '7'
      Font.Charset = DEFAULT_CHARSET
      Font.Color = clWindowText
      Font.Height = -11
      Font.Name = 'MS Sans Serif'
      Font.Style = [fsBold]
      Layout = blGlyphBottom
      ParentFont = False
      OnClick = BtnNumClick
    end
    object Btn8: TSpeedButton
      Left = 33
      Top = 48
      Width = 27
      Height = 19
      Caption = '8'
      Font.Charset = DEFAULT_CHARSET
      Font.Color = clWindowText
      Font.Height = -11
      Font.Name = 'MS Sans Serif'
      Font.Style = [fsBold]
      Layout = blGlyphBottom
      ParentFont = False
      OnClick = BtnNumClick
    end
    object Btn9: TSpeedButton
      Left = 64
      Top = 48
      Width = 27
      Height = 19
      Caption = '9'
      Font.Charset = DEFAULT_CHARSET
      Font.Color = clWindowText
      Font.Height = -11
      Font.Name = 'MS Sans Serif'
      Font.Style = [fsBold]
      Layout = blGlyphBottom
      ParentFont = False
      OnClick = BtnNumClick
    end
    object BtnMult: TSpeedButton
      Left = 95
      Top = 48
      Width = 27
      Height = 19
      Caption = #215
      Font.Charset = DEFAULT_CHARSET
      Font.Color = clBlue
      Font.Height = -16
      Font.Name = 'MS Sans Serif'
      Font.Style = []
      Layout = blGlyphBottom
      ParentFont = False
      OnClick = BtnBaseOpClick
    end
    object Btn4: TSpeedButton
      Left = 2
      Top = 70
      Width = 27
      Height = 19
      Caption = '4'
      Font.Charset = DEFAULT_CHARSET
      Font.Color = clWindowText
      Font.Height = -11
      Font.Name = 'MS Sans Serif'
      Font.Style = [fsBold]
      Layout = blGlyphBottom
      ParentFont = False
      OnClick = BtnNumClick
    end
    object Btn5: TSpeedButton
      Left = 33
      Top = 70
      Width = 27
      Height = 19
      Caption = '5'
      Font.Charset = DEFAULT_CHARSET
      Font.Color = clWindowText
      Font.Height = -11
      Font.Name = 'MS Sans Serif'
      Font.Style = [fsBold]
      Layout = blGlyphBottom
      ParentFont = False
      OnClick = BtnNumClick
    end
    object Btn6: TSpeedButton
      Left = 64
      Top = 70
      Width = 27
      Height = 19
      Caption = '6'
      Font.Charset = DEFAULT_CHARSET
      Font.Color = clWindowText
      Font.Height = -11
      Font.Name = 'MS Sans Serif'
      Font.Style = [fsBold]
      Layout = blGlyphBottom
      ParentFont = False
      OnClick = BtnNumClick
    end
    object BtnDiv: TSpeedButton
      Left = 95
      Top = 70
      Width = 27
      Height = 19
      Caption = #247
      Font.Charset = DEFAULT_CHARSET
      Font.Color = clBlue
      Font.Height = -16
      Font.Name = 'MS Sans Serif'
      Font.Style = [fsBold]
      Layout = blGlyphBottom
      ParentFont = False
      OnClick = BtnBaseOpClick
    end
    object Btn1: TSpeedButton
      Left = 2
      Top = 92
      Width = 27
      Height = 19
      Caption = '1'
      Font.Charset = DEFAULT_CHARSET
      Font.Color = clWindowText
      Font.Height = -11
      Font.Name = 'MS Sans Serif'
      Font.Style = [fsBold]
      Layout = blGlyphBottom
      ParentFont = False
      OnClick = BtnNumClick
    end
    object Btn2: TSpeedButton
      Left = 33
      Top = 92
      Width = 27
      Height = 19
      Caption = '2'
      Font.Charset = DEFAULT_CHARSET
      Font.Color = clWindowText
      Font.Height = -11
      Font.Name = 'MS Sans Serif'
      Font.Style = [fsBold]
      Layout = blGlyphBottom
      ParentFont = False
      OnClick = BtnNumClick
    end
    object Btn3: TSpeedButton
      Left = 64
      Top = 92
      Width = 27
      Height = 19
      Caption = '3'
      Font.Charset = DEFAULT_CHARSET
      Font.Color = clWindowText
      Font.Height = -11
      Font.Name = 'MS Sans Serif'
      Font.Style = [fsBold]
      Layout = blGlyphBottom
      ParentFont = False
      OnClick = BtnNumClick
    end
    object BtnSub: TSpeedButton
      Left = 95
      Top = 92
      Width = 27
      Height = 19
      Caption = '-'
      Font.Charset = DEFAULT_CHARSET
      Font.Color = clBlue
      Font.Height = -16
      Font.Name = 'MS Sans Serif'
      Font.Style = [fsBold]
      Layout = blGlyphBottom
      ParentFont = False
      OnClick = BtnBaseOpClick
    end
    object Btn0: TSpeedButton
      Left = 2
      Top = 114
      Width = 27
      Height = 19
      Caption = '0'
      Font.Charset = DEFAULT_CHARSET
      Font.Color = clWindowText
      Font.Height = -11
      Font.Name = 'MS Sans Serif'
      Font.Style = [fsBold]
      Layout = blGlyphBottom
      ParentFont = False
      OnClick = BtnNumClick
    end
    object BtnSep: TSpeedButton
      Left = 33
      Top = 114
      Width = 27
      Height = 19
      Caption = #183
      Font.Charset = DEFAULT_CHARSET
      Font.Color = clWindowText
      Font.Height = -19
      Font.Name = 'MS Sans Serif'
      Font.Style = [fsBold]
      Layout = blGlyphTop
      ParentFont = False
      OnClick = BtnNumClick
    end
    object BtnRes: TSpeedButton
      Left = 64
      Top = 114
      Width = 27
      Height = 19
      Caption = '='
      Font.Charset = DEFAULT_CHARSET
      Font.Color = clMaroon
      Font.Height = -16
      Font.Name = 'MS Sans Serif'
      Font.Style = [fsBold]
      Layout = blGlyphBottom
      ParentFont = False
      OnClick = BtnResClick
    end
    object BtnAdd: TSpeedButton
      Left = 95
      Top = 114
      Width = 27
      Height = 19
      Caption = '+'
      Font.Charset = DEFAULT_CHARSET
      Font.Color = clBlue
      Font.Height = -16
      Font.Name = 'MS Sans Serif'
      Font.Style = []
      Layout = blGlyphBottom
      ParentFont = False
      OnClick = BtnBaseOpClick
    end
    object BtnCancel: TSpeedButton
      Left = 2
      Top = 145
      Width = 58
      Height = 19
      Caption = '&Cancelar'
      Layout = blGlyphTop
      Margin = 0
      OnClick = BtnCancelClick
    end
    object BtnOK: TSpeedButton
      Left = 64
      Top = 145
      Width = 58
      Height = 19
      Caption = '&OK'
      Layout = blGlyphTop
      Margin = 0
      OnClick = BtnOKClick
    end
    object Bevel1: TBevel
      Left = 2
      Top = 138
      Width = 120
      Height = 2
    end
    object PanDisplay: TPanel
      Left = 2
      Top = 2
      Width = 119
      Height = 20
      Align = alTop
      Alignment = taRightJustify
      BevelOuter = bvLowered
      BorderWidth = 3
      Caption = '0'
      Color = clWindow
      Font.Charset = ANSI_CHARSET
      Font.Color = clMenuText
      Font.Height = -11
      Font.Name = 'MS Sans Serif'
      Font.Style = [fsBold]
      ParentFont = False
      TabOrder = 0
      OnMouseDown = PanMouseDown
      OnMouseMove = PanMouseMove
    end
  end
end
