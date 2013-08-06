object FrmMail: TFrmMail
  Left = 154
  Top = 137
  Caption = 'Mail'
  ClientHeight = 422
  ClientWidth = 544
  Color = clBtnFace
  Font.Charset = DEFAULT_CHARSET
  Font.Color = clWindowText
  Font.Height = -11
  Font.Name = 'MS Sans Serif'
  Font.Style = []
  OldCreateOrder = False
  Position = poScreenCenter
  OnClose = FormClose
  PixelsPerInch = 96
  TextHeight = 13
  object GroupBox1: TGroupBox
    Left = 0
    Top = 0
    Width = 544
    Height = 41
    Align = alTop
    Caption = 'De: usuario@provedor'
    TabOrder = 0
    DesignSize = (
      544
      41)
    object EdtDs_de: TEdit
      Left = 2
      Top = 17
      Width = 538
      Height = 21
      Anchors = [akLeft, akTop, akRight]
      TabOrder = 0
    end
  end
  object GroupBox2: TGroupBox
    Left = 0
    Top = 82
    Width = 544
    Height = 296
    Align = alTop
    Caption = 'Mensagem'
    TabOrder = 2
    object Memo1: TMemo
      Left = 2
      Top = 15
      Width = 540
      Height = 279
      Align = alClient
      TabOrder = 0
    end
  end
  object GroupBox3: TGroupBox
    Left = 0
    Top = 378
    Width = 544
    Height = 44
    Align = alClient
    Caption = 'A'#231#227'o'
    TabOrder = 3
    object BitBtn1: TBitBtn
      Left = 385
      Top = 15
      Width = 75
      Height = 25
      Caption = '&Enviar'
      DoubleBuffered = True
      Kind = bkAll
      NumGlyphs = 2
      ParentDoubleBuffered = False
      TabOrder = 0
      OnClick = BitBtn1Click
    end
    object BitBtn2: TBitBtn
      Left = 466
      Top = 15
      Width = 75
      Height = 25
      Caption = 'Fechar'
      DoubleBuffered = True
      Kind = bkAbort
      NumGlyphs = 2
      ParentDoubleBuffered = False
      TabOrder = 1
      OnClick = BitBtn2Click
    end
    object Panel2: TPanel
      Left = 2
      Top = 15
      Width = 377
      Height = 27
      Align = alLeft
      TabOrder = 2
      object LblDsProces: TLabel
        Left = 4
        Top = 5
        Width = 144
        Height = 13
        AutoSize = False
        Caption = 'LblDsProces'
        Font.Charset = DEFAULT_CHARSET
        Font.Color = clWindowText
        Font.Height = -11
        Font.Name = 'MS Sans Serif'
        Font.Style = [fsBold]
        ParentFont = False
      end
      object PgbProgres: TProgressBar
        Left = 172
        Top = 1
        Width = 204
        Height = 25
        Align = alRight
        Max = 4
        Step = 1
        TabOrder = 0
      end
    end
  end
  object GroupBox4: TGroupBox
    Left = 0
    Top = 41
    Width = 544
    Height = 41
    Align = alTop
    Caption = 'Assunto'
    TabOrder = 1
    DesignSize = (
      544
      41)
    object EdtDs_Assunto: TEdit
      Left = 2
      Top = 17
      Width = 538
      Height = 21
      Anchors = [akLeft, akTop, akRight]
      TabOrder = 0
    end
  end
  object IdMessage: TIdMessage
    AttachmentEncoding = 'MIME'
    BccList = <>
    CCList = <>
    Encoding = meMIME
    FromList = <
      item
      end>
    Recipients = <>
    ReplyTo = <>
    ConvertPreamble = True
    Left = 24
    Top = 120
  end
  object IdPOP3: TIdPOP3
    AutoLogin = True
    SASLMechanisms = <>
    Left = 72
    Top = 120
  end
  object IdSMTP: TIdSMTP
    SASLMechanisms = <>
    Left = 120
    Top = 120
  end
end
