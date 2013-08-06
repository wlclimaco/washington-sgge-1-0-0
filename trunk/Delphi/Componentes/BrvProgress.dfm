object FrmProgress: TFrmProgress
  Left = 280
  Top = 240
  BorderIcons = []
  BorderStyle = bsSingle
  ClientHeight = 28
  ClientWidth = 435
  Color = clBtnFace
  Font.Charset = DEFAULT_CHARSET
  Font.Color = clWindowText
  Font.Height = -11
  Font.Name = 'MS Sans Serif'
  Font.Style = []
  OldCreateOrder = False
  Position = poScreenCenter
  Visible = True
  OnCreate = FormCreate
  PixelsPerInch = 96
  TextHeight = 13
  object BtnCancelar: TBrvBitBtn
    Left = 357
    Top = 2
    Width = 76
    Height = 25
    Caption = 'Cancelar'
    DoubleBuffered = True
    NumGlyphs = 2
    ParentDoubleBuffered = False
    TabOrder = 1
    Visible = False
    OnClick = BtnCancelarClick
    BrTipoBotao = BrBtnCancel
  end
  object Gauge: TBrvGauge
    Left = 9
    Top = 7
    Width = 342
    Height = 15
    TabOrder = 0
    BrProcessMessages = False
  end
end
