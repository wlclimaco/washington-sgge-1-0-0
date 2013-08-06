inherited Mov0019: TMov0019
  Caption = 'Mov0019'
  ClientHeight = 461
  ClientWidth = 772
  ExplicitWidth = 780
  ExplicitHeight = 488
  PixelsPerInch = 96
  TextHeight = 13
  inherited NavBarra: TBrvDbNavCop
    Width = 772
    ExplicitWidth = 772
    inherited BrvSpeedButton1: TBrvSpeedButton
      Left = 745
      ExplicitLeft = 745
    end
    inherited SbtAjuda: TBrvSpeedButton
      Left = 720
      ExplicitLeft = 720
    end
  end
  inherited PnlFundo: TPanel
    Top = 97
    Width = 772
    Height = 364
    ExplicitTop = 97
    ExplicitWidth = 772
    ExplicitHeight = 364
    object PgcAnalise: TPageControl
      Left = 1
      Top = 1
      Width = 766
      Height = 316
      ActivePage = TbsDiretorio
      Align = alClient
      TabOrder = 0
      object TbsDiretorio: TTabSheet
        Caption = 'Diret'#243'rio'
        TabVisible = False
        object Panel1: TPanel
          Left = 0
          Top = 0
          Width = 758
          Height = 306
          Align = alClient
          TabOrder = 0
          object Label1: TLabel
            Left = 8
            Top = 5
            Width = 29
            Height = 13
            Caption = 'Local'
            Font.Charset = DEFAULT_CHARSET
            Font.Color = clBlue
            Font.Height = -11
            Font.Name = 'Tahoma'
            Font.Style = [fsBold]
            ParentFont = False
          end
          object EdtLocal: TBrvEdit
            Left = 8
            Top = 24
            Width = 737
            Height = 21
            TabOrder = 0
            Text = 'C:\MapasCuiaba\Sa'#237'da\28102012'
            BrVisibleButton = True
            BrFunctionButton = VeDiretorio
            BrAlignment = taLeftJustify
            BrvQueryCode = 0
            BrRecordar = True
          end
        end
      end
      object TbsOperacao: TTabSheet
        Caption = 'Opera'#231#227'o'
        ImageIndex = 1
        TabVisible = False
        object Panel2: TPanel
          Left = 0
          Top = 0
          Width = 758
          Height = 306
          Align = alClient
          BevelOuter = bvNone
          TabOrder = 0
          DesignSize = (
            758
            306)
          object RdgOper: TRadioGroup
            Left = 32
            Top = 24
            Width = 701
            Height = 129
            Anchors = [akLeft, akTop, akRight]
            Caption = 'Seleciopne a Opera'#231#227'o'
            Font.Charset = DEFAULT_CHARSET
            Font.Color = clWindowText
            Font.Height = -16
            Font.Name = 'MS Sans Serif'
            Font.Style = [fsBold]
            ItemIndex = 0
            Items.Strings = (
              'Entrada'
              'Sa'#237'da')
            ParentFont = False
            TabOrder = 0
          end
        end
      end
      object TbsProcessamento: TTabSheet
        Caption = 'Processamento'
        ImageIndex = 2
        TabVisible = False
        object Panel3: TPanel
          Left = 0
          Top = 0
          Width = 758
          Height = 306
          Align = alClient
          TabOrder = 0
          object Splitter1: TSplitter
            Left = 1
            Top = 121
            Width = 756
            Height = 3
            Cursor = crVSplit
            Align = alTop
            ExplicitLeft = 17
            ExplicitWidth = 168
          end
          object FileListBox: TFileListBox
            Left = 1
            Top = 124
            Width = 0
            Height = 181
            Align = alLeft
            ItemHeight = 13
            TabOrder = 0
            Visible = False
          end
          object BrvDbGrid: TBrvDbGrid
            Left = 1
            Top = 124
            Width = 756
            Height = 181
            BrShowMemo = True
            BrReadOnlyStyle = [fsItalic]
            BrReadOnlyColor = clMaroon
            Align = alClient
            DataSource = DtsLogOper
            TabOrder = 1
            TitleFont.Charset = DEFAULT_CHARSET
            TitleFont.Color = clWindowText
            TitleFont.Height = -11
            TitleFont.Name = 'Tahoma'
            TitleFont.Style = []
            BrDrawColumn.Strings = (
              'N'#227'o remova essas duas linhas de formata'#231#227'o:'
              'CampoTabela;Operador;ValorComparativo;Cor;')
            BrGradeZebrada = True
            Columns = <
              item
                Expanded = False
                FieldName = 'NrMapa'
                Title.Caption = 'Mapa'
                Visible = True
                BrConsulta = 0
                BrPermissao = []
                BrValueHalfChecked = False
                BrSaveOnClick = False
              end
              item
                Expanded = False
                FieldName = 'DtProcess'
                Title.Caption = 'Processamento'
                Visible = True
                BrConsulta = 0
                BrPermissao = []
                BrValueHalfChecked = False
                BrSaveOnClick = False
              end
              item
                Expanded = False
                FieldName = 'CdUsuari'
                Title.Caption = 'Usu'#225'rio'
                Visible = True
                BrConsulta = 0
                BrPermissao = []
                BrValueHalfChecked = False
                BrSaveOnClick = False
              end
              item
                Expanded = False
                FieldName = 'SnCancela'
                PickList.Strings = (
                  'Sim'
                  'N'#227'o')
                Title.Caption = 'Cancelamento'
                Visible = True
                BrConsulta = 0
                BrPickValue.Strings = (
                  'S'
                  'N')
                BrPermissao = []
                BrValueHalfChecked = False
                BrSaveOnClick = False
              end
              item
                Expanded = False
                FieldName = 'QtSemID'
                Title.Caption = 'Qtde Sem Identifica'#231#227'o'
                Visible = True
                BrConsulta = 0
                BrPermissao = []
                BrValueHalfChecked = False
                BrSaveOnClick = False
              end
              item
                Expanded = False
                FieldName = 'QtRegLis'
                Title.Caption = 'Registros da Lista'
                Visible = True
                BrConsulta = 0
                BrPermissao = []
                BrValueHalfChecked = False
                BrSaveOnClick = False
              end
              item
                Expanded = False
                FieldName = 'CdClient'
                Title.Caption = 'Cliente'
                Visible = True
                BrConsulta = 0
                BrPermissao = []
                BrValueHalfChecked = False
                BrSaveOnClick = False
              end
              item
                Expanded = False
                FieldName = 'SnLista'
                PickList.Strings = (
                  'Sim'
                  'N'#227'o')
                Title.Caption = 'Lista'
                Width = 40
                Visible = True
                BrConsulta = 0
                BrPickValue.Strings = (
                  'S'
                  'N')
                BrPermissao = []
                BrValueHalfChecked = False
                BrSaveOnClick = False
              end>
          end
          object BrvDbGrid1: TBrvDbGrid
            Left = 1
            Top = 1
            Width = 756
            Height = 120
            BrShowMemo = True
            BrReadOnlyStyle = [fsItalic]
            BrReadOnlyColor = clMaroon
            Align = alTop
            DataSource = DtsMapa
            PopupMenu = PopupMenu
            TabOrder = 2
            TitleFont.Charset = DEFAULT_CHARSET
            TitleFont.Color = clWindowText
            TitleFont.Height = -11
            TitleFont.Name = 'Tahoma'
            TitleFont.Style = []
            BrDrawColumn.Strings = (
              'N'#227'o remova essas duas linhas de formata'#231#227'o:'
              'CampoTabela;Operador;ValorComparativo;Cor;')
            BrGradeZebrada = True
            Columns = <
              item
                Expanded = False
                FieldName = 'NrMapa'
                Title.Caption = 'Mapa'
                Visible = True
                BrConsulta = 0
                BrPermissao = []
                BrValueHalfChecked = False
                BrSaveOnClick = False
              end
              item
                Expanded = False
                FieldName = 'NrFinaliza'
                Title.Caption = 'Finaliza'#231#245'es'
                Visible = True
                BrConsulta = 0
                BrPermissao = []
                BrValueHalfChecked = False
                BrSaveOnClick = False
              end
              item
                Expanded = False
                FieldName = 'NrFecha'
                Title.Caption = 'Fechamentos'
                Width = 73
                Visible = True
                BrConsulta = 0
                BrPermissao = []
                BrValueHalfChecked = False
                BrSaveOnClick = False
              end
              item
                Expanded = False
                FieldName = 'NrCancel'
                Title.Caption = 'Cancelamentos'
                Width = 79
                Visible = True
                BrConsulta = 0
                BrPermissao = []
                BrValueHalfChecked = False
                BrSaveOnClick = False
              end
              item
                Expanded = False
                FieldName = 'StOpeLog'
                Title.Caption = 'Situa'#231#227'o'
                Width = 150
                Visible = True
                BrConsulta = 0
                BrPermissao = []
                BrValueHalfChecked = False
                BrSaveOnClick = False
              end>
          end
        end
      end
      object TbsResultado: TTabSheet
        Caption = 'Resultado'
        ImageIndex = 3
        TabVisible = False
        object Panel4: TPanel
          Left = 0
          Top = 0
          Width = 758
          Height = 306
          Align = alClient
          TabOrder = 0
        end
      end
    end
    object PnlNext1: TPanel
      Left = 1
      Top = 317
      Width = 766
      Height = 42
      Align = alBottom
      BorderStyle = bsSingle
      TabOrder = 1
      DesignSize = (
        762
        38)
      object BtnNext: TBitBtn
        Left = 641
        Top = 6
        Width = 75
        Height = 25
        Anchors = [akTop, akRight]
        Caption = 'Pr'#243'ximo >>'
        DoubleBuffered = True
        ParentDoubleBuffered = False
        TabOrder = 0
        OnClick = BtnNextClick
      end
      object BtnVoltar: TBitBtn
        Left = 560
        Top = 6
        Width = 75
        Height = 25
        Anchors = [akTop, akRight]
        Caption = '<<Voltar'
        DoubleBuffered = True
        ParentDoubleBuffered = False
        TabOrder = 1
        Visible = False
        OnClick = BtnVoltarClick
      end
      object GroupBox7: TGroupBox
        Left = 9
        Top = -1
        Width = 553
        Height = 36
        Caption = 'Estat'#237'sticas'
        Font.Charset = DEFAULT_CHARSET
        Font.Color = clWindowText
        Font.Height = -11
        Font.Name = 'MS Sans Serif'
        Font.Style = [fsBold]
        ParentFont = False
        TabOrder = 2
        object Label61: TLabel
          Left = 78
          Top = 15
          Width = 54
          Height = 13
          Caption = 'Registros'
          Font.Charset = DEFAULT_CHARSET
          Font.Color = clWindowText
          Font.Height = -11
          Font.Name = 'MS Sans Serif'
          Font.Style = [fsBold]
          ParentFont = False
        end
        object Label2: TLabel
          Left = 206
          Top = 15
          Width = 106
          Height = 13
          Caption = 'Registros Filtrados'
          Font.Charset = DEFAULT_CHARSET
          Font.Color = clWindowText
          Font.Height = -11
          Font.Name = 'MS Sans Serif'
          Font.Style = [fsBold]
          ParentFont = False
        end
        object EdtTTReg: TBrvEditNum
          Left = 138
          Top = 12
          Width = 60
          Height = 21
          TabStop = False
          BevelInner = bvLowered
          BevelKind = bkFlat
          BevelWidth = 2
          BorderStyle = bsNone
          ParentColor = True
          ReadOnly = True
          TabOrder = 0
          Text = '0'
          BrAlignment = taRightJustify
          BrCasasDecimais = 0
          BrSepararMilhar = False
          BrAsInteger = 0
          BrAsFloatSQL = '0'
          BrVisibleButton = False
          BrFunctionButton = TpConsulta
          BrConfigurar.Strings = (
            'EdtCdRota;CdRota;S;S;'
            'LblDsPraca;DsPraca;S;N;')
          BrDicionario = DmSrvApl.BrvDicionario
          BrQueryCode = 14
          BrRecordar = False
        end
        object EdtTTRegFil: TBrvEditNum
          Left = 314
          Top = 12
          Width = 60
          Height = 21
          TabStop = False
          BevelInner = bvLowered
          BevelKind = bkFlat
          BevelWidth = 2
          BorderStyle = bsNone
          ParentColor = True
          ReadOnly = True
          TabOrder = 1
          Text = '0'
          BrAlignment = taRightJustify
          BrCasasDecimais = 0
          BrSepararMilhar = False
          BrAsInteger = 0
          BrAsFloatSQL = '0'
          BrVisibleButton = False
          BrFunctionButton = TpConsulta
          BrConfigurar.Strings = (
            'EdtCdRota;CdRota;S;S;'
            'LblDsPraca;DsPraca;S;N;')
          BrDicionario = DmSrvApl.BrvDicionario
          BrQueryCode = 14
          BrRecordar = False
        end
      end
    end
  end
  object PnlFiltros: TPanel [2]
    Left = 0
    Top = 30
    Width = 772
    Height = 67
    Align = alTop
    TabOrder = 2
    Visible = False
    object StgFiltros: TStringGrid
      Left = 1
      Top = 1
      Width = 770
      Height = 65
      Align = alClient
      ColCount = 2
      FixedCols = 0
      RowCount = 2
      TabOrder = 0
      ColWidths = (
        168
        464)
    end
  end
  object BrvAlertProgress: TBrvAlertProgress
    Left = 720
    Top = 88
  end
  object CcLogOper: TClientDataSet
    Aggregates = <>
    Params = <>
    Left = 720
    Top = 120
    object CcLogOperNrMapa: TIntegerField
      FieldName = 'NrMapa'
    end
    object CcLogOperDtProcess: TDateTimeField
      FieldName = 'DtProcess'
    end
    object CcLogOperCdUsuari: TIntegerField
      FieldName = 'CdUsuari'
    end
    object CcLogOperSnCancela: TStringField
      FieldName = 'SnCancela'
      Size = 1
    end
    object CcLogOperQtSemID: TIntegerField
      FieldName = 'QtSemID'
    end
    object CcLogOperDsListPro: TStringField
      FieldName = 'DsListPro'
      Size = 500
    end
    object CcLogOperCdClient: TIntegerField
      FieldName = 'CdClient'
    end
    object CcLogOperQtRegLis: TIntegerField
      FieldName = 'QtRegLis'
    end
    object CcLogOperSnLista: TStringField
      FieldName = 'SnLista'
      Size = 1
    end
  end
  object DtsLogOper: TDataSource
    DataSet = CcLogOper
    Left = 720
    Top = 152
  end
  object CcW005: TClientDataSet
    Aggregates = <>
    Params = <>
    Left = 720
    Top = 184
  end
  object CcW008: TClientDataSet
    Aggregates = <>
    Params = <>
    Left = 720
    Top = 216
  end
  object CcMapa: TClientDataSet
    Aggregates = <>
    Params = <>
    AfterScroll = CcMapaAfterScroll
    Left = 720
    Top = 248
    object CcMapaNrMapa: TIntegerField
      FieldName = 'NrMapa'
    end
    object CcMapaNrFinaliza: TIntegerField
      FieldName = 'NrFinaliza'
    end
    object CcMapaNrFecha: TIntegerField
      FieldName = 'NrFecha'
    end
    object CcMapaNrCancel: TIntegerField
      FieldName = 'NrCancel'
    end
    object CcMapaStOpeLog: TStringField
      FieldName = 'StOpeLog'
      Size = 1
    end
  end
  object DtsMapa: TDataSource
    DataSet = CcMapa
    Left = 720
    Top = 280
  end
  object PopupMenu: TPopupMenu
    OnPopup = PopupMenuPopup
    Left = 720
    Top = 56
    object MultiplasFinalizaes1: TMenuItem
      Caption = 'Multiplas Finaliza'#231#245'es'
      OnClick = MultiplasFinalizaes1Click
    end
    object Aguardando1: TMenuItem
      Caption = 'Aguardando'
      OnClick = Aguardando1Click
    end
    object FinalizarOperao1: TMenuItem
      Caption = 'Finalizar Opera'#231#227'o'
      Visible = False
      OnClick = FinalizarOperao1Click
    end
  end
  object BrvListParam: TBrvListParam
    Left = 344
    Top = 128
  end
end
