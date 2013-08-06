object FrmCalDias: TFrmCalDias
  Left = 366
  Top = 138
  BorderIcons = [biSystemMenu]
  Caption = 'C'#225'lculo de dias'
  ClientHeight = 96
  ClientWidth = 178
  Color = clBtnFace
  Font.Charset = DEFAULT_CHARSET
  Font.Color = clWindowText
  Font.Height = -11
  Font.Name = 'MS Sans Serif'
  Font.Style = []
  OldCreateOrder = False
  Position = poScreenCenter
  PixelsPerInch = 96
  TextHeight = 13
  object Panel1: TPanel
    Left = 0
    Top = 0
    Width = 178
    Height = 96
    Align = alClient
    BevelInner = bvLowered
    TabOrder = 0
    DesignSize = (
      178
      96)
    object LblInicial: TLabel
      Left = 8
      Top = 22
      Width = 66
      Height = 13
      Caption = 'Data Inicial'
      Font.Charset = DEFAULT_CHARSET
      Font.Color = clWindowText
      Font.Height = -11
      Font.Name = 'MS Sans Serif'
      Font.Style = [fsBold]
      ParentFont = False
    end
    object LblFinal: TLabel
      Left = 8
      Top = 45
      Width = 59
      Height = 13
      Caption = 'Data Final'
      Font.Charset = DEFAULT_CHARSET
      Font.Color = clWindowText
      Font.Height = -11
      Font.Name = 'MS Sans Serif'
      Font.Style = [fsBold]
      ParentFont = False
    end
    object BtnRetornaDias: TBitBtn
      Left = 39
      Top = 66
      Width = 101
      Height = 25
      Anchors = [akLeft, akTop, akRight, akBottom]
      Caption = 'Retorna Dias'
      DoubleBuffered = True
      Kind = bkOK
      NumGlyphs = 2
      ParentDoubleBuffered = False
      TabOrder = 2
      OnClick = BtnRetornaDiasClick
    end
    object EdtDtFim: TBrvEditDate
      Left = 81
      Top = 40
      Width = 90
      Height = 21
      EditMask = '99/99/9999;1; '
      MaxLength = 10
      TabOrder = 1
      Text = '  /  /    '
      BrDataValida = False
      BrDataVazia = True
      BrFunctionButton = TVdData
      BrAlignment = taLeftJustify
    end
    object EdtDtIni: TBrvEditDate
      Left = 81
      Top = 16
      Width = 90
      Height = 21
      EditMask = '99/99/9999;1; '
      MaxLength = 10
      TabOrder = 0
      Text = '  /  /    '
      BrDataValida = False
      BrDataVazia = True
      BrFunctionButton = TVdData
      BrAlignment = taLeftJustify
    end
  end
  object Extenso1: TBrvExtenso
    Moeda = 'Real'
    PluralMoeda = 'Meses'
    Fracao = 'centavo'
    PluralFracao = 'centavos'
    Numero = '0,00'
    Acentuar = False
    Left = 8
    Top = 64
  end
end
