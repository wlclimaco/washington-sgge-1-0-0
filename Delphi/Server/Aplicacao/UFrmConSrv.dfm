object FrmConSrv: TFrmConSrv
  Left = 551
  Top = 234
  BorderIcons = []
  BorderStyle = bsSingle
  Caption = 'FrmConSrv - Conex'#227'o'
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
      Max = 30
      Step = 1
      TabOrder = 0
    end
  end
  object BrvConexao: TBrvSrvConexao
    BrEnderecoSite = 'http://192.10.10.77/srvconexa/'
    BrNumeroPorta = 0
    Left = 176
    Top = 8
  end
  object TCPClient: TBrvTCPClient
    ConnectTimeout = 0
    IPVersion = Id_IPv4
    Port = 0
    ReadTimeout = -1
    OnExecute = TCPClientExecute
    Left = 112
    Top = 8
  end
  object TmrConexa: TTimer
    Enabled = False
    Interval = 5000
    OnTimer = TmrConexaTimer
    Left = 368
    Top = 8
  end
  object Timer: TTimer
    OnTimer = TimerTimer
    Left = 312
    Top = 8
  end
end
