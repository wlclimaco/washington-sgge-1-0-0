object FrmErrCon: TFrmErrCon
  Left = 0
  Top = 0
  BorderIcons = [biSystemMenu]
  BorderStyle = bsSingle
  Caption = 'FrmErrCon - Erro de conex'#227'o'
  ClientHeight = 188
  ClientWidth = 363
  Color = clBtnFace
  Font.Charset = DEFAULT_CHARSET
  Font.Color = clWindowText
  Font.Height = -11
  Font.Name = 'Tahoma'
  Font.Style = []
  FormStyle = fsStayOnTop
  OldCreateOrder = False
  Position = poScreenCenter
  PixelsPerInch = 96
  TextHeight = 13
  object PnlFundo: TPanel
    Left = 0
    Top = 0
    Width = 363
    Height = 154
    Align = alClient
    BorderStyle = bsSingle
    TabOrder = 0
    object MenDsMsgErr: TMemo
      Left = 1
      Top = 1
      Width = 357
      Height = 113
      TabStop = False
      Align = alClient
      Color = clBtnFace
      Font.Charset = DEFAULT_CHARSET
      Font.Color = clRed
      Font.Height = -16
      Font.Name = 'MS Sans Serif'
      Font.Style = [fsBold]
      ParentFont = False
      ReadOnly = True
      TabOrder = 0
    end
    object Panel1: TPanel
      Left = 1
      Top = 114
      Width = 357
      Height = 35
      Align = alBottom
      TabOrder = 1
      object LblDsTempo: TLabel
        Left = 5
        Top = 3
        Width = 226
        Height = 13
        Caption = 'A aplica'#231#227'o encerrar'#225' em: 00 segundos'
        Font.Charset = DEFAULT_CHARSET
        Font.Color = clWindowText
        Font.Height = -11
        Font.Name = 'MS Sans Serif'
        Font.Style = [fsBold]
        ParentFont = False
      end
      object PgbTempo: TProgressBar
        Left = 1
        Top = 18
        Width = 355
        Height = 16
        Align = alBottom
        Max = 10
        Position = 10
        MarqueeInterval = 1
        Step = 1
        TabOrder = 0
      end
    end
  end
  object PnlBaixo: TPanel
    Left = 0
    Top = 154
    Width = 363
    Height = 34
    Align = alBottom
    BorderStyle = bsSingle
    TabOrder = 1
    object Label2: TLabel
      Left = 6
      Top = 6
      Width = 190
      Height = 13
      Caption = 'Clique em OK para finalizar agora'
      Font.Charset = DEFAULT_CHARSET
      Font.Color = clWindowText
      Font.Height = -11
      Font.Name = 'MS Sans Serif'
      Font.Style = [fsBold]
      ParentFont = False
    end
    object BbtOk: TBitBtn
      Left = 202
      Top = 3
      Width = 75
      Height = 25
      DoubleBuffered = True
      Kind = bkOK
      NumGlyphs = 2
      ParentDoubleBuffered = False
      TabOrder = 0
      OnClick = BbtOkClick
    end
  end
  object Timer: TTimer
    Enabled = False
    OnTimer = TimerTimer
    Left = 256
    Top = 40
  end
end
