object FrmImport: TFrmImport
  Left = 127
  Top = 185
  BorderIcons = []
  BorderStyle = bsSingle
  Caption = 'Importa'#231#227'o de dados'
  ClientHeight = 113
  ClientWidth = 505
  Color = clBtnFace
  Font.Charset = DEFAULT_CHARSET
  Font.Color = clWindowText
  Font.Height = -11
  Font.Name = 'MS Sans Serif'
  Font.Style = []
  KeyPreview = True
  OldCreateOrder = False
  Position = poScreenCenter
  PixelsPerInch = 96
  TextHeight = 13
  object Panel1: TPanel
    Left = 0
    Top = 0
    Width = 505
    Height = 113
    Align = alClient
    BorderStyle = bsSingle
    TabOrder = 0
    object Label1: TLabel
      Left = 8
      Top = 8
      Width = 105
      Height = 13
      Caption = 'Arquivo de Origem'
      Font.Charset = DEFAULT_CHARSET
      Font.Color = clWindowText
      Font.Height = -11
      Font.Name = 'MS Sans Serif'
      Font.Style = [fsBold]
      ParentFont = False
    end
    object EdtNmOrigem: TBrvEdit
      Left = 120
      Top = 5
      Width = 377
      Height = 21
      TabOrder = 0
      BrVisibleButton = True
      BrFunctionButton = VeArquivo
      BrAlignment = taLeftJustify
      BrvQueryCode = 0
    end
    object BitBtn1: TBrvBitBtn
      Left = 160
      Top = 80
      Width = 75
      Height = 25
      Caption = '&Ok'
      Default = True
      DoubleBuffered = True
      NumGlyphs = 2
      ParentDoubleBuffered = False
      TabOrder = 1
      OnClick = BitBtn1Click
      BrTipoBotao = BrBtnOk
    end
    object BitBtn2: TBrvBitBtn
      Left = 240
      Top = 80
      Width = 75
      Height = 25
      Caption = '&Cancelar'
      DoubleBuffered = True
      ModalResult = 2
      NumGlyphs = 2
      ParentDoubleBuffered = False
      TabOrder = 2
      BrTipoBotao = BrBtnCancel
    end
    object RgpOperac: TRadioGroup
      Left = 8
      Top = 32
      Width = 489
      Height = 43
      Caption = 'Opera'#231#227'o'
      Columns = 3
      Font.Charset = DEFAULT_CHARSET
      Font.Color = clWindowText
      Font.Height = -11
      Font.Name = 'MS Sans Serif'
      Font.Style = [fsBold]
      ItemIndex = 0
      Items.Strings = (
        'Inserir no final'
        'Atualizar e inserir'
        'Somente atualizar')
      ParentFont = False
      TabOrder = 3
    end
  end
end
