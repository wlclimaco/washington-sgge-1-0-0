inherited Con0012: TCon0012
  Caption = 'Con0012 - Cargas Pendentes'
  ClientHeight = 482
  ClientWidth = 778
  ExplicitWidth = 794
  ExplicitHeight = 520
  PixelsPerInch = 96
  TextHeight = 13
  inherited NavBarra: TBrvDbNavCop
    Width = 778
    ExplicitWidth = 778
    inherited BrvSpeedButton1: TBrvSpeedButton
      Left = 751
      ExplicitLeft = 751
    end
    inherited SbtAjuda: TBrvSpeedButton
      Left = 726
      ExplicitLeft = 726
    end
  end
  inherited PnlFundo: TPanel
    Top = 70
    Width = 778
    Height = 412
    ExplicitWidth = 778
    ExplicitHeight = 452
    object Panel1: TPanel
      Left = 1
      Top = 1
      Width = 772
      Height = 406
      Align = alClient
      BorderStyle = bsSingle
      Caption = 'Panel1'
      TabOrder = 0
      ExplicitTop = 97
      ExplicitHeight = 350
    end
  end
  object PnlEmpres: TPanel
    Left = 0
    Top = 30
    Width = 778
    Height = 40
    Align = alTop
    BorderStyle = bsSingle
    TabOrder = 2
    ExplicitLeft = -146
    ExplicitTop = 0
    ExplicitWidth = 924
    DesignSize = (
      774
      36)
    object LblEmpres: TLabel
      Left = 5
      Top = 13
      Width = 49
      Height = 13
      Caption = 'Empresa'
      Font.Charset = DEFAULT_CHARSET
      Font.Color = clBlue
      Font.Height = -11
      Font.Name = 'MS Sans Serif'
      Font.Style = [fsBold]
      ParentFont = False
    end
    object EdtCdEmpres: TBrvEditNum
      Left = 122
      Top = 8
      Width = 88
      Height = 21
      TabOrder = 0
      Text = '0'
      BrAlignment = taRightJustify
      BrCasasDecimais = 0
      BrSepararMilhar = False
      BrAsInteger = 0
      BrAsFloatSQL = '0'
      BrVisibleButton = True
      BrFunctionButton = TpConsulta
      BrConfigurar.Strings = (
        'EdtCdEmpres;CdEmpres;S;S;'
        'LblDsEmpres;DsEmpres;S;N;')
      BrDicionario = DmSrvApl.BrvDicionario
      BrQueryCode = 12
      BrRecordar = False
    end
    object LblDsEmpres: TBrvRetCon
      Left = 212
      Top = 8
      Width = 434
      Height = 21
      TabStop = False
      Anchors = [akLeft, akTop, akRight]
      AutoSize = False
      BevelInner = bvLowered
      BevelKind = bkFlat
      BevelWidth = 2
      BorderStyle = bsNone
      Font.Charset = DEFAULT_CHARSET
      Font.Color = clWindowText
      Font.Height = -11
      Font.Name = 'MS Sans Serif'
      Font.Style = [fsBold]
      ParentColor = True
      ParentFont = False
      ReadOnly = True
      TabOrder = 1
    end
    object BtnProsse: TBrvBitBtn
      Left = 652
      Top = 4
      Width = 113
      Height = 25
      Anchors = [akTop, akRight]
      Caption = '&Prosseguir'
      DoubleBuffered = True
      Font.Charset = DEFAULT_CHARSET
      Font.Color = clWindowText
      Font.Height = -11
      Font.Name = 'Tahoma'
      Font.Style = [fsBold]
      NumGlyphs = 2
      ParentDoubleBuffered = False
      ParentFont = False
      TabOrder = 2
      BrTipoBotao = BrBtnOk
    end
  end
end
