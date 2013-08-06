object FrmConexa: TFrmConexa
  Left = 206
  Top = 237
  BorderIcons = []
  BorderStyle = bsSingle
  Caption = 'FrmConexa - Conex'#227'o'
  ClientHeight = 51
  ClientWidth = 428
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
  object Panel1: TPanel
    Left = 0
    Top = 0
    Width = 428
    Height = 51
    Align = alClient
    BorderStyle = bsSingle
    TabOrder = 0
    object LblProces: TLabel
      Left = 8
      Top = 8
      Width = 54
      Height = 13
      Caption = 'LblProces'
      Font.Charset = DEFAULT_CHARSET
      Font.Color = clWindowText
      Font.Height = -11
      Font.Name = 'Tahoma'
      Font.Style = [fsBold]
      ParentFont = False
    end
    object PgbProgre: TProgressBar
      Left = 8
      Top = 24
      Width = 410
      Height = 16
      Max = 10000000
      Step = 1
      TabOrder = 0
    end
  end
  object BrvConexao: TBrvSrvConexao
    BrEnderecoSite = 'http://www.bravolog.com.br/srvconexa/'
    BrNumeroPorta = 0
    Left = 72
    Top = 8
  end
  object Timer: TTimer
    OnTimer = TimerTimer
    Left = 392
    Top = 8
  end
  object TmrConexa: TTimer
    Enabled = False
    Interval = 5000
    OnTimer = TmrConexaTimer
    Left = 352
    Top = 8
  end
  object TmrRecImp: TTimer
    Enabled = False
    OnTimer = TmrRecImpTimer
    Left = 304
    Top = 8
  end
end
