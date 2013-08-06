object Cai0003: TCai0003
  Left = 0
  Top = 0
  BorderIcons = [biSystemMenu, biMaximize]
  Caption = 'Cai0003 - Tab Order da Grade'
  ClientHeight = 223
  ClientWidth = 431
  Color = clBtnFace
  Font.Charset = DEFAULT_CHARSET
  Font.Color = clWindowText
  Font.Height = -11
  Font.Name = 'Tahoma'
  Font.Style = []
  OldCreateOrder = False
  Position = poScreenCenter
  PixelsPerInch = 96
  TextHeight = 13
  object PnlFundo: TPanel
    Left = 0
    Top = 0
    Width = 431
    Height = 223
    Align = alClient
    BorderStyle = bsSingle
    TabOrder = 0
    ExplicitWidth = 394
    ExplicitHeight = 166
    object DbgTabOrd: TDBGrid
      Left = 1
      Top = 17
      Width = 425
      Height = 167
      Align = alClient
      TabOrder = 0
      TitleFont.Charset = DEFAULT_CHARSET
      TitleFont.Color = clWindowText
      TitleFont.Height = -11
      TitleFont.Name = 'Tahoma'
      TitleFont.Style = []
    end
    object Panel1: TPanel
      Left = 1
      Top = 1
      Width = 425
      Height = 16
      Align = alTop
      Caption = 'Clique no t'#237'tulo da coluna e arraste para alterar a posi'#231#227'o'
      Font.Charset = DEFAULT_CHARSET
      Font.Color = clBlue
      Font.Height = -11
      Font.Name = 'MS Sans Serif'
      Font.Style = [fsBold]
      ParentFont = False
      TabOrder = 1
      ExplicitWidth = 388
    end
    object PnlBaixo: TPanel
      Left = 1
      Top = 184
      Width = 425
      Height = 34
      Align = alBottom
      BorderStyle = bsSingle
      TabOrder = 2
      ExplicitLeft = 0
      ExplicitTop = 166
      ExplicitWidth = 394
      DesignSize = (
        421
        30)
      object BrvBitBtn1: TBrvBitBtn
        Left = 94
        Top = 2
        Width = 109
        Height = 25
        Anchors = [akTop]
        Caption = 'Confirmar'
        DoubleBuffered = True
        ModalResult = 1
        NumGlyphs = 2
        ParentDoubleBuffered = False
        TabOrder = 0
        BrTipoBotao = BrBtnOk
      end
      object BrvBitBtn2: TBrvBitBtn
        Left = 216
        Top = 3
        Width = 109
        Height = 25
        Anchors = [akTop]
        Caption = 'Cancelar'
        DoubleBuffered = True
        ModalResult = 2
        NumGlyphs = 2
        ParentDoubleBuffered = False
        TabOrder = 1
        BrTipoBotao = BrBtnCancel
      end
    end
  end
end
