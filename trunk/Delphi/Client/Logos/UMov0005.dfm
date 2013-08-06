inherited Mov0005: TMov0005
  Left = 391
  Top = 173
  Caption = 'Mov0005 - Lan'#231'amentos cont'#225'beis (diversos)'
  ClientHeight = 423
  ClientWidth = 716
  FormStyle = fsNormal
  Visible = False
  ExplicitTop = -42
  ExplicitWidth = 724
  ExplicitHeight = 450
  PixelsPerInch = 96
  TextHeight = 13
  inherited NavBarra: TBrvDbNavCop
    Width = 716
    ExplicitWidth = 716
    inherited BrvSpeedButton1: TBrvSpeedButton
      Left = 689
      ExplicitLeft = 689
    end
    inherited SbtAjuda: TBrvSpeedButton
      Left = 664
      ExplicitLeft = 664
    end
  end
  inherited PnlFundo: TPanel
    Width = 716
    Height = 393
    ExplicitWidth = 716
    ExplicitHeight = 393
    object PnlCabeca: TPanel
      Left = 1
      Top = 1
      Width = 710
      Height = 41
      Align = alTop
      BorderStyle = bsSingle
      Font.Charset = DEFAULT_CHARSET
      Font.Color = clWindowText
      Font.Height = -16
      Font.Name = 'MS Sans Serif'
      Font.Style = [fsBold]
      ParentFont = False
      TabOrder = 0
      object Label2: TLabel
        Left = 257
        Top = 8
        Width = 83
        Height = 20
        Caption = 'Contas de'
      end
      object LblDsDebCre: TLabel
        Left = 345
        Top = 8
        Width = 111
        Height = 20
        Caption = 'debito/credito'
      end
    end
    object Panel1: TPanel
      Left = 1
      Top = 352
      Width = 710
      Height = 36
      Align = alBottom
      BorderStyle = bsSingle
      Font.Charset = DEFAULT_CHARSET
      Font.Color = clWindowText
      Font.Height = -16
      Font.Name = 'MS Sans Serif'
      Font.Style = [fsBold]
      ParentFont = False
      TabOrder = 1
      DesignSize = (
        706
        32)
      object Label1: TLabel
        Left = 533
        Top = 7
        Width = 30
        Height = 13
        Anchors = [akTop, akRight]
        Caption = 'Total'
        Font.Charset = DEFAULT_CHARSET
        Font.Color = clWindowText
        Font.Height = -11
        Font.Name = 'MS Sans Serif'
        Font.Style = [fsBold]
        ParentFont = False
        ExplicitLeft = 541
      end
      object BrvBitBtn1: TBrvBitBtn
        Left = 12
        Top = 4
        Width = 90
        Height = 25
        Caption = 'Limpar'
        DoubleBuffered = True
        Font.Charset = DEFAULT_CHARSET
        Font.Color = clWindowText
        Font.Height = -11
        Font.Name = 'Tahoma'
        Font.Style = [fsBold]
        NumGlyphs = 2
        ParentDoubleBuffered = False
        ParentFont = False
        TabOrder = 0
        OnClick = BrvBitBtn1Click
        BrTipoBotao = BrBtnLimpar
      end
      object BrvBitBtn2: TBrvBitBtn
        Left = 108
        Top = 4
        Width = 90
        Height = 25
        Caption = 'Ok'
        DoubleBuffered = True
        Font.Charset = DEFAULT_CHARSET
        Font.Color = clWindowText
        Font.Height = -11
        Font.Name = 'Tahoma'
        Font.Style = [fsBold]
        NumGlyphs = 2
        ParentDoubleBuffered = False
        ParentFont = False
        TabOrder = 1
        OnClick = BrvBitBtn2Click
        BrTipoBotao = BrBtnOk
      end
      object EdtTtRateio: TBrvEditNum
        Left = 576
        Top = 4
        Width = 121
        Height = 21
        TabStop = False
        Font.Charset = DEFAULT_CHARSET
        Font.Color = clWindowText
        Font.Height = -11
        Font.Name = 'MS Sans Serif'
        Font.Style = [fsBold]
        ParentColor = True
        ParentFont = False
        ReadOnly = True
        TabOrder = 2
        Text = '0,00'
        BrAlignment = taRightJustify
        BrCasasDecimais = 2
        BrSepararMilhar = False
        BrAsInteger = 0
        BrAsFloatSQL = '0.00'
        BrVisibleButton = False
        BrFunctionButton = TpConsulta
        BrQueryCode = 0
        BrRecordar = False
      end
      object BrvBitBtn3: TBrvBitBtn
        Left = 202
        Top = 4
        Width = 90
        Height = 25
        Caption = 'Cancelar'
        DoubleBuffered = True
        Font.Charset = DEFAULT_CHARSET
        Font.Color = clWindowText
        Font.Height = -11
        Font.Name = 'Tahoma'
        Font.Style = [fsBold]
        NumGlyphs = 2
        ParentDoubleBuffered = False
        ParentFont = False
        TabOrder = 3
        OnClick = BrvBitBtn3Click
        BrTipoBotao = BrBtnCancel
      end
    end
    object DBGLancto: TBrvDbGrid
      Left = 1
      Top = 42
      Width = 710
      Height = 310
      BrShowMemo = True
      BrReadOnlyStyle = [fsItalic]
      BrReadOnlyColor = clMaroon
      Align = alClient
      DataSource = DsTemp
      TabOrder = 2
      TitleFont.Charset = DEFAULT_CHARSET
      TitleFont.Color = clWindowText
      TitleFont.Height = -11
      TitleFont.Name = 'Tahoma'
      TitleFont.Style = []
      OnColExit = DBGLanctoColExit
      BrDicionario = DmSrvApl.BrvDicionario
      BrDrawColumn.Strings = (
        'N'#227'o remova essas duas linhas de formata'#231#227'o:'
        'CampoTabela;Operador;ValorComparativo;Cor;')
      Columns = <
        item
          ButtonStyle = cbsConsulta
          Expanded = False
          FieldName = 'NrConta'
          Title.Caption = 'Conta'
          Title.Font.Charset = DEFAULT_CHARSET
          Title.Font.Color = clBlue
          Title.Font.Height = -11
          Title.Font.Name = 'MS Sans Serif'
          Title.Font.Style = [fsBold]
          Width = 58
          Visible = True
          BrOnBeforeConsul = DBGLanctoColumns0BrOnBeforeConsul
          BrOnAfterConsul = DBGLanctoColumns0BrOnAfterConsul
          BrConsulta = 0
          BrPermissao = []
          BrValueHalfChecked = False
          BrSaveOnClick = False
        end
        item
          ButtonStyle = cbsEllipsis
          Expanded = False
          FieldName = 'NrClassi'
          ReadOnly = True
          Title.Caption = 'Classifica'#231#227'o'
          Title.Font.Charset = DEFAULT_CHARSET
          Title.Font.Color = clBlue
          Title.Font.Height = -11
          Title.Font.Name = 'MS Sans Serif'
          Title.Font.Style = [fsBold]
          Width = 103
          Visible = True
          BrConsulta = 0
          BrPermissao = []
          BrValueHalfChecked = False
          BrSaveOnClick = False
        end
        item
          Expanded = False
          FieldName = 'NmConta'
          ReadOnly = True
          Title.Caption = 'Nome'
          Title.Font.Charset = DEFAULT_CHARSET
          Title.Font.Color = clWindowText
          Title.Font.Height = -11
          Title.Font.Name = 'MS Sans Serif'
          Title.Font.Style = [fsBold]
          Width = 242
          Visible = True
          BrConsulta = 0
          BrPermissao = []
          BrValueHalfChecked = False
          BrSaveOnClick = False
        end
        item
          Expanded = False
          FieldName = 'VrLancto'
          Title.Caption = 'Valor'
          Title.Font.Charset = DEFAULT_CHARSET
          Title.Font.Color = clBlue
          Title.Font.Height = -11
          Title.Font.Name = 'MS Sans Serif'
          Title.Font.Style = [fsBold]
          Visible = True
          BrConsulta = 0
          BrPermissao = []
          BrValueHalfChecked = False
          BrSaveOnClick = False
        end
        item
          ButtonStyle = cbsConsulta
          Expanded = False
          FieldName = 'CdHistor'
          Title.Caption = 'Hist'#243'rico'
          Title.Font.Charset = DEFAULT_CHARSET
          Title.Font.Color = clBlue
          Title.Font.Height = -11
          Title.Font.Name = 'MS Sans Serif'
          Title.Font.Style = [fsBold]
          Width = 54
          Visible = True
          BrConsulta = 36
          BrConfigurar.Strings = (
            'CdHistor;CdHistor;S;S;'
            'DsHistor;DsHistor;S;N;')
          BrPermissao = []
          BrValueHalfChecked = False
          BrSaveOnClick = False
        end
        item
          Expanded = False
          FieldName = 'DsHistor'
          ReadOnly = True
          Title.Caption = 'Desc. hist'#243'rico'
          Title.Font.Charset = DEFAULT_CHARSET
          Title.Font.Color = clWindowText
          Title.Font.Height = -11
          Title.Font.Name = 'MS Sans Serif'
          Title.Font.Style = [fsBold]
          Visible = True
          BrConsulta = 0
          BrPermissao = []
          BrValueHalfChecked = False
          BrSaveOnClick = False
        end
        item
          Expanded = False
          FieldName = 'DsComHis'
          Title.Caption = 'Complemento'
          Title.Font.Charset = DEFAULT_CHARSET
          Title.Font.Color = clWindowText
          Title.Font.Height = -11
          Title.Font.Name = 'MS Sans Serif'
          Title.Font.Style = [fsBold]
          Visible = True
          BrConsulta = 0
          BrPermissao = []
          BrValueHalfChecked = False
          BrSaveOnClick = False
        end>
    end
  end
  object DsTemp: TDataSource
    DataSet = TblTemp
    Left = 365
    Top = 191
  end
  object TblTemp: TClientDataSet
    Aggregates = <>
    Params = <>
    BeforeEdit = TblTempBeforeEdit
    BeforePost = TblTempBeforePost
    AfterPost = TblTempAfterPost
    AfterCancel = TblTempAfterCancel
    BeforeDelete = TblTempBeforeDelete
    AfterDelete = TblTempAfterDelete
    Left = 408
    Top = 190
    object TblTempNrConta: TIntegerField
      FieldName = 'NrConta'
    end
    object TblTempNmConta: TStringField
      FieldName = 'NmConta'
      Size = 130
    end
    object TblTempVrLancto: TFloatField
      FieldName = 'VrLancto'
      DisplayFormat = '########0.00'
    end
    object TblTempNrClassi: TStringField
      FieldName = 'NrClassi'
    end
    object TblTempCdHistor: TIntegerField
      FieldName = 'CdHistor'
    end
    object TblTempDsHistor: TStringField
      FieldName = 'DsHistor'
      Size = 40
    end
    object TblTempDsComHis: TMemoField
      FieldName = 'DsComHis'
      BlobType = ftMemo
    end
  end
end
