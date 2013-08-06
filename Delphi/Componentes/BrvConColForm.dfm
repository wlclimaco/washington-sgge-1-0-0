object FrmConCol: TFrmConCol
  Left = 387
  Top = 130
  BorderIcons = [biSystemMenu]
  BorderStyle = bsSingle
  Caption = 'Visualiza'#231#227'o das colunas'
  ClientHeight = 348
  ClientWidth = 250
  Color = clBtnFace
  Font.Charset = DEFAULT_CHARSET
  Font.Color = clWindowText
  Font.Height = -11
  Font.Name = 'MS Sans Serif'
  Font.Style = []
  KeyPreview = True
  OldCreateOrder = False
  Position = poScreenCenter
  OnKeyPress = FormKeyPress
  PixelsPerInch = 96
  TextHeight = 13
  object Panel1: TPanel
    Left = 0
    Top = 0
    Width = 250
    Height = 348
    Align = alClient
    BorderStyle = bsSingle
    TabOrder = 0
    object Panel2: TPanel
      Left = 1
      Top = 302
      Width = 244
      Height = 41
      Align = alBottom
      BorderStyle = bsSingle
      TabOrder = 0
      object BitBtn1: TBrvBitBtn
        Left = 37
        Top = 8
        Width = 75
        Height = 25
        Caption = '&Ok'
        DoubleBuffered = True
        NumGlyphs = 2
        ParentDoubleBuffered = False
        TabOrder = 0
        OnClick = BitBtn1Click
        BrTipoBotao = BrBtnOk
      end
      object BitBtn2: TBrvBitBtn
        Left = 128
        Top = 8
        Width = 75
        Height = 25
        Cancel = True
        Caption = '&Cancelar'
        DoubleBuffered = True
        ModalResult = 2
        NumGlyphs = 2
        ParentDoubleBuffered = False
        TabOrder = 1
        BrTipoBotao = BrBtnCancel
      end
    end
    object CbxColuna: TCheckListBox
      Left = 1
      Top = 1
      Width = 244
      Height = 301
      Align = alClient
      ItemHeight = 13
      PopupMenu = PopupMenu
      TabOrder = 1
    end
  end
  object PopupMenu: TPopupMenu
    Left = 66
    Top = 34
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
