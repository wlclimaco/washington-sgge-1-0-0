object FrmImpExp: TFrmImpExp
  Left = 325
  Top = 198
  BorderIcons = []
  BorderStyle = bsSingle
  Caption = 'Colunas para exporta'#231#227'o/importa'#231#227'o'
  ClientHeight = 348
  ClientWidth = 225
  Color = clBtnFace
  Font.Charset = DEFAULT_CHARSET
  Font.Color = clWindowText
  Font.Height = -11
  Font.Name = 'MS Sans Serif'
  Font.Style = []
  OldCreateOrder = False
  Position = poScreenCenter
  OnCreate = FormCreate
  OnDestroy = FormDestroy
  PixelsPerInch = 96
  TextHeight = 13
  object Panel1: TPanel
    Left = 0
    Top = 0
    Width = 225
    Height = 348
    Align = alClient
    BorderStyle = bsSingle
    TabOrder = 0
    object Panel2: TPanel
      Left = 1
      Top = 309
      Width = 219
      Height = 34
      Align = alBottom
      BorderStyle = bsSingle
      TabOrder = 0
      object BitBtn1: TBrvBitBtn
        Left = 30
        Top = 2
        Width = 75
        Height = 25
        Caption = '&Ok'
        Default = True
        DoubleBuffered = True
        NumGlyphs = 2
        ParentDoubleBuffered = False
        TabOrder = 0
        OnClick = BitBtn1Click
        BrTipoBotao = BrBtnOk
      end
      object BitBtn2: TBrvBitBtn
        Left = 110
        Top = 2
        Width = 75
        Height = 25
        Caption = '&Cancelar'
        DoubleBuffered = True
        ModalResult = 2
        NumGlyphs = 2
        ParentDoubleBuffered = False
        TabOrder = 1
        BrTipoBotao = BrBtnCancel
      end
    end
    object LcbColunas: TCheckListBox
      Left = 1
      Top = 1
      Width = 219
      Height = 308
      Align = alClient
      ItemHeight = 13
      PopupMenu = PopupMenu1
      TabOrder = 1
    end
  end
  object PopupMenu1: TPopupMenu
    Left = 66
    Top = 74
    object Marcartodos1: TMenuItem
      Caption = '&Marcar todos'
      OnClick = Marcartodos1Click
    end
    object Desmarcartodos1: TMenuItem
      Caption = '&Desmarcar todos'
      OnClick = Desmarcartodos1Click
    end
  end
end
