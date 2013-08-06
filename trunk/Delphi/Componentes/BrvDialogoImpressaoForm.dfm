object FrmDlgImpres: TFrmDlgImpres
  Left = 190
  Top = 249
  BorderIcons = [biSystemMenu]
  BorderStyle = bsSingle
  Caption = 'Imprimir'
  ClientHeight = 133
  ClientWidth = 472
  Color = clBtnFace
  Font.Charset = DEFAULT_CHARSET
  Font.Color = clWindowText
  Font.Height = -11
  Font.Name = 'MS Sans Serif'
  Font.Style = []
  KeyPreview = True
  OldCreateOrder = False
  Position = poScreenCenter
  OnCreate = FormCreate
  PixelsPerInch = 96
  TextHeight = 13
  object Panel1: TPanel
    Left = 0
    Top = 0
    Width = 472
    Height = 133
    Align = alClient
    BorderStyle = bsSingle
    TabOrder = 0
    object BotImprim: TBrvBitBtn
      Left = 310
      Top = 97
      Width = 75
      Height = 25
      Caption = '&Ok'
      Default = True
      DoubleBuffered = True
      ModalResult = 1
      NumGlyphs = 2
      ParentDoubleBuffered = False
      TabOrder = 0
      BrTipoBotao = BrBtnOk
    end
    object SbtCancel: TBrvBitBtn
      Left = 389
      Top = 97
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
    object GbxImpres: TGroupBox
      Left = 1
      Top = 1
      Width = 466
      Height = 45
      Align = alTop
      Caption = '  Impressora  '
      Font.Charset = DEFAULT_CHARSET
      Font.Color = clWindowText
      Font.Height = -11
      Font.Name = 'MS Sans Serif'
      Font.Style = [fsBold]
      ParentFont = False
      TabOrder = 2
      object Label1: TLabel
        Left = 12
        Top = 20
        Width = 33
        Height = 13
        Caption = 'Nome'
        Font.Charset = DEFAULT_CHARSET
        Font.Color = clWindowText
        Font.Height = -11
        Font.Name = 'MS Sans Serif'
        Font.Style = [fsBold]
        ParentFont = False
      end
      object CbxImpres: TComboBox
        Left = 65
        Top = 15
        Width = 390
        Height = 21
        Style = csDropDownList
        Font.Charset = DEFAULT_CHARSET
        Font.Color = clWindowText
        Font.Height = -11
        Font.Name = 'MS Sans Serif'
        Font.Style = []
        ParentFont = False
        TabOrder = 0
      end
    end
    object GbxCoipas: TGroupBox
      Left = 233
      Top = 46
      Width = 233
      Height = 43
      Caption = '  C'#243'pias  '
      Font.Charset = DEFAULT_CHARSET
      Font.Color = clWindowText
      Font.Height = -11
      Font.Name = 'MS Sans Serif'
      Font.Style = [fsBold]
      ParentFont = False
      TabOrder = 3
      object Label2: TLabel
        Left = 8
        Top = 19
        Width = 103
        Height = 13
        Caption = 'N'#250'mero de c'#243'pias'
      end
      object SdtNrCopias: TSpinEdit
        Left = 123
        Top = 15
        Width = 60
        Height = 22
        Font.Charset = DEFAULT_CHARSET
        Font.Color = clWindowText
        Font.Height = -11
        Font.Name = 'MS Sans Serif'
        Font.Style = []
        MaxValue = 250
        MinValue = 1
        ParentFont = False
        TabOrder = 0
        Value = 1
      end
    end
    object GbxProprie: TGroupBox
      Left = 1
      Top = 46
      Width = 224
      Height = 43
      Caption = '  Propriedades  '
      Font.Charset = DEFAULT_CHARSET
      Font.Color = clWindowText
      Font.Height = -11
      Font.Name = 'MS Sans Serif'
      Font.Style = [fsBold]
      ParentFont = False
      TabOrder = 4
      object CbxConden: TCheckBox
        Left = 11
        Top = 19
        Width = 150
        Height = 17
        Caption = 'Imprimir condensado'
        TabOrder = 0
      end
    end
  end
end
