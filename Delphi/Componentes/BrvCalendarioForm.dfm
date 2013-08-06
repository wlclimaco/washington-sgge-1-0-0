object FrmCalendar: TFrmCalendar
  Left = 318
  Top = 182
  BorderIcons = [biSystemMenu, biMaximize]
  Caption = 'Calend'#225'rio'
  ClientHeight = 189
  ClientWidth = 189
  Color = clBtnFace
  Font.Charset = DEFAULT_CHARSET
  Font.Color = clWindowText
  Font.Height = -11
  Font.Name = 'MS Sans Serif'
  Font.Style = []
  KeyPreview = True
  OldCreateOrder = False
  Position = poScreenCenter
  OnClose = FormClose
  OnKeyPress = FormKeyPress
  OnKeyUp = FormKeyUp
  PixelsPerInch = 96
  TextHeight = 13
  object MthCalend: TMonthCalendar
    Left = 0
    Top = 0
    Width = 189
    Height = 153
    Align = alClient
    Date = 37155.353985231480000000
    PopupMenu = PopFuncao
    TabOrder = 0
    WeekNumbers = True
    OnDblClick = BbtDtRetornClick
  end
  object PnlRodape: TPanel
    Left = 0
    Top = 153
    Width = 189
    Height = 36
    Align = alBottom
    BorderStyle = bsSingle
    TabOrder = 1
    object BbtDtRetorn: TBitBtn
      Left = 33
      Top = 4
      Width = 125
      Height = 25
      Hint = 'Retornar Data (F9)'
      Caption = '&Retornar data'
      DoubleBuffered = True
      Kind = bkOK
      NumGlyphs = 2
      ParentDoubleBuffered = False
      ParentShowHint = False
      ShowHint = True
      TabOrder = 0
      OnClick = BbtDtRetornClick
    end
  end
  object PopFuncao: TPopupMenu
    Left = 164
    Top = 150
    object Adicionardias: TMenuItem
      Caption = 'Adicionar dias'
      OnClick = AdicionardiasClick
    end
    object Subtrairdias: TMenuItem
      Caption = 'Subtrair dias '
      OnClick = SubtrairdiasClick
    end
    object Dthoje: TMenuItem
      Caption = 'Data de hoje'
      OnClick = DthojeClick
    end
  end
end
