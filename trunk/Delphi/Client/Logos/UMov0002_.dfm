inherited Mov0002: TMov0002
  Left = 336
  Top = 129
  Caption = 'Mov0002 - Montar Carga'
  ClientHeight = 514
  ClientWidth = 964
  Position = poDesktopCenter
  ExplicitWidth = 980
  ExplicitHeight = 552
  PixelsPerInch = 96
  TextHeight = 13
  inherited NavBarra: TBrvDbNavCop
    Width = 964
    ExplicitWidth = 964
    inherited BrvSpeedButton1: TBrvSpeedButton
      Left = 937
      ExplicitLeft = 911
    end
    inherited SbtAjuda: TBrvSpeedButton
      Left = 912
      ExplicitLeft = 886
    end
    object GbxProces: TGroupBox
      Left = 1
      Top = 1
      Width = 477
      Height = 28
      Align = alLeft
      Caption = 'Processando. Aguarde ...'
      Font.Charset = DEFAULT_CHARSET
      Font.Color = clWindowText
      Font.Height = -11
      Font.Name = 'MS Sans Serif'
      Font.Style = [fsBold]
      ParentFont = False
      TabOrder = 0
      Visible = False
      object PgbProces: TProgressBar
        Left = 2
        Top = 15
        Width = 473
        Height = 11
        Align = alClient
        Step = 1
        TabOrder = 0
      end
    end
  end
  inherited PnlFundo: TPanel
    Width = 964
    Height = 484
    ExplicitWidth = 964
    ExplicitHeight = 484
    object PgcFundo: TPageControl
      Left = 1
      Top = 1
      Width = 958
      Height = 478
      ActivePage = TbsCarga
      Align = alClient
      TabOrder = 0
      object TbsFiltro: TTabSheet
        Caption = 'TbsFiltro'
        ExplicitLeft = 0
        ExplicitTop = 0
        ExplicitWidth = 0
        ExplicitHeight = 0
        object PnlEmpres: TPanel
          Left = 0
          Top = 0
          Width = 950
          Height = 40
          Align = alTop
          BorderStyle = bsSingle
          TabOrder = 0
          DesignSize = (
            946
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
            BrOnBeforeConsulta = EdtCdEmpresBrOnBeforeConsulta
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
            Width = 581
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
            ExplicitWidth = 589
          end
          object BtnProsse: TBrvBitBtn
            Left = 796
            Top = 7
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
            OnClick = BtnProsseClick
            BrTipoBotao = BrBtnOk
            ExplicitLeft = 804
          end
        end
        object PnlFiltro: TPanel
          Left = 0
          Top = 40
          Width = 950
          Height = 410
          Align = alClient
          BorderStyle = bsSingle
          TabOrder = 1
          Visible = False
          DesignSize = (
            946
            406)
          object Label12: TLabel
            Left = 4
            Top = 157
            Width = 40
            Height = 13
            Caption = 'Estado'
            Font.Charset = DEFAULT_CHARSET
            Font.Color = clBlue
            Font.Height = -11
            Font.Name = 'MS Sans Serif'
            Font.Style = [fsBold]
            ParentFont = False
          end
          object Label1: TLabel
            Left = 6
            Top = 14
            Width = 28
            Height = 13
            Caption = 'Rota'
            Color = clBtnFace
            Font.Charset = DEFAULT_CHARSET
            Font.Color = clBlack
            Font.Height = -11
            Font.Name = 'MS Sans Serif'
            Font.Style = [fsBold]
            ParentColor = False
            ParentFont = False
          end
          object Label13: TLabel
            Left = 5
            Top = 38
            Width = 50
            Height = 13
            Caption = 'Tomador'
            Font.Charset = DEFAULT_CHARSET
            Font.Color = clBlack
            Font.Height = -11
            Font.Name = 'MS Sans Serif'
            Font.Style = [fsBold]
            ParentFont = False
          end
          object Label11: TLabel
            Left = 5
            Top = 62
            Width = 69
            Height = 13
            Caption = 'Destinat'#225'rio'
            Font.Charset = DEFAULT_CHARSET
            Font.Color = clBlack
            Font.Height = -11
            Font.Name = 'MS Sans Serif'
            Font.Style = [fsBold]
            ParentFont = False
          end
          object Label57: TLabel
            Left = 4
            Top = 84
            Width = 40
            Height = 13
            Caption = 'Cidade'
            Font.Charset = DEFAULT_CHARSET
            Font.Color = clWindowText
            Font.Height = -11
            Font.Name = 'MS Sans Serif'
            Font.Style = [fsBold]
            ParentFont = False
          end
          object Label22: TLabel
            Left = 4
            Top = 111
            Width = 116
            Height = 13
            Caption = 'Entrega prevista de '
            Font.Charset = DEFAULT_CHARSET
            Font.Color = clWindowText
            Font.Height = -11
            Font.Name = 'MS Sans Serif'
            Font.Style = [fsBold]
            ParentFont = False
          end
          object Label24: TLabel
            Left = 250
            Top = 111
            Width = 19
            Height = 13
            Caption = 'at'#233
            Font.Charset = DEFAULT_CHARSET
            Font.Color = clWindowText
            Font.Height = -11
            Font.Name = 'MS Sans Serif'
            Font.Style = [fsBold]
            ParentFont = False
          end
          object Label26: TLabel
            Left = 3
            Top = 135
            Width = 30
            Height = 13
            Caption = 'Tipo '
            Font.Charset = DEFAULT_CHARSET
            Font.Color = clBlue
            Font.Height = -11
            Font.Name = 'MS Sans Serif'
            Font.Style = [fsBold]
            ParentFont = False
          end
          object LblNmCidade: TBrvRetCon
            Left = 212
            Top = 83
            Width = 725
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
            TabOrder = 0
          end
          object LblRsDestin: TBrvRetCon
            Left = 212
            Top = 59
            Width = 725
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
          object LblRsTomado: TBrvRetCon
            Left = 212
            Top = 35
            Width = 725
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
            TabOrder = 2
          end
          object LblDsPraca: TBrvRetCon
            Left = 212
            Top = 11
            Width = 725
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
            TabOrder = 3
          end
          object EdtCdRota: TBrvEditNum
            Left = 122
            Top = 11
            Width = 88
            Height = 21
            TabOrder = 4
            Text = '0'
            BrAlignment = taRightJustify
            BrCasasDecimais = 0
            BrSepararMilhar = False
            BrAsInteger = 0
            BrAsFloatSQL = '0'
            BrVisibleButton = True
            BrFunctionButton = TpConsulta
            BrOnBeforeConsulta = EdtCdRotaBrOnBeforeConsulta
            BrConfigurar.Strings = (
              'EdtCdRota;CdRota;S;S;'
              'LblDsPraca;DsPraca;S;N;')
            BrDicionario = DmSrvApl.BrvDicionario
            BrQueryCode = 14
            BrRecordar = False
          end
          object EdtCdTomado: TBrvEditNum
            Left = 122
            Top = 35
            Width = 88
            Height = 21
            TabOrder = 5
            Text = '0'
            BrAlignment = taRightJustify
            BrCasasDecimais = 0
            BrSepararMilhar = False
            BrAsInteger = 0
            BrAsFloatSQL = '0'
            BrVisibleButton = True
            BrFunctionButton = TpConsulta
            BrConfigurar.Strings = (
              'EdtCdTomado;CdTitula;S;S;'
              'LblRsTomado;RsTitula;S;N;')
            BrDicionario = DmSrvApl.BrvDicionario
            BrQueryCode = 15
            BrRecordar = False
          end
          object EdtCdDestin: TBrvEditNum
            Left = 122
            Top = 59
            Width = 88
            Height = 21
            TabOrder = 6
            Text = '0'
            BrAlignment = taRightJustify
            BrCasasDecimais = 0
            BrSepararMilhar = False
            BrAsInteger = 0
            BrAsFloatSQL = '0'
            BrVisibleButton = True
            BrFunctionButton = TpConsulta
            BrConfigurar.Strings = (
              'EdtCdDestin;CdTitula;S;S;'
              'LblRsDestin;RsTitula;S;N')
            BrDicionario = DmSrvApl.BrvDicionario
            BrQueryCode = 15
            BrRecordar = False
          end
          object EdtDtPreIni: TBrvEditDate
            Left = 122
            Top = 108
            Width = 121
            Height = 21
            EditMask = '99/99/9999;1; '
            MaxLength = 10
            TabOrder = 8
            Text = '  /  /    '
            BrDataValida = False
            BrDataVazia = True
            BrFunctionButton = TVdData
            BrAlignment = taLeftJustify
            BrDicionario = DmSrvApl.BrvDicionario
            BrRecordar = False
          end
          object EdtDtPreFim: TBrvEditDate
            Left = 275
            Top = 107
            Width = 121
            Height = 21
            EditMask = '99/99/9999;1; '
            MaxLength = 10
            TabOrder = 9
            Text = '  /  /    '
            BrDataValida = False
            BrDataVazia = True
            BrFunctionButton = TVdData
            BrAlignment = taLeftJustify
            BrDicionario = DmSrvApl.BrvDicionario
            BrRecordar = False
          end
          object EdtCpCidade: TBrvEdit
            Left = 122
            Top = 83
            Width = 88
            Height = 21
            TabOrder = 7
            BrVisibleButton = True
            BrFunctionButton = VeConsulta
            BrConfigurar.Strings = (
              'EdtCpCidade;CpCidade;S;S;'
              'LblNmCidade;NmCidade@-#CdEstado@-;S;N;')
            BrAlignment = taLeftJustify
            BrDicionario = DmSrvApl.BrvDicionario
            BrvQueryCode = 16
            BrRecordar = False
          end
          object Panel1: TPanel
            Left = 1
            Top = 371
            Width = 944
            Height = 34
            Align = alBottom
            BorderStyle = bsSingle
            Font.Charset = DEFAULT_CHARSET
            Font.Color = clWindowText
            Font.Height = -11
            Font.Name = 'MS Sans Serif'
            Font.Style = [fsBold]
            ParentFont = False
            TabOrder = 10
            DesignSize = (
              940
              30)
            object BrvBitBtn1: TBrvBitBtn
              Left = 655
              Top = 3
              Width = 113
              Height = 25
              Anchors = [akTop, akRight]
              Caption = '&Pesquisar'
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
              BrTipoBotao = BrBtnLocalizar
              ExplicitLeft = 663
            end
            object BtnVoltar: TBrvBitBtn
              Left = 772
              Top = 3
              Width = 113
              Height = 25
              Anchors = [akTop, akRight]
              Caption = '&Voltar'
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
              OnClick = BtnVoltarClick
              BrTipoBotao = BrBtnCancel
              ExplicitLeft = 780
            end
          end
          object CblCdEstado: TBrvCheckListBox
            Left = 122
            Top = 160
            Width = 789
            Height = 105
            Columns = 4
            Font.Charset = ANSI_CHARSET
            Font.Color = clWindowText
            Font.Height = -11
            Font.Name = 'MS Sans Serif'
            Font.Style = [fsBold]
            ItemHeight = 13
            Items.Strings = (
              'AC Acre'
              'AL Alagoas'
              'AP Amapa'
              'AM Amazonas'
              'BA Bahia'
              'CE Ceara'
              'DF Distrito Federal'
              'ES Espirito Santo'
              'GO Goias'
              'MA Maranh'#227'o'
              'MT Mato Grosso'
              'MS Mato Grosso do Sul'
              'MG Minas Gerais'
              'PA Para'
              'PB Paraiba'
              'PR Parana'
              'PE Pernambuco'
              'PI Piaui'
              'RJ Rio de Janeiro'
              'RN Rio Grande do Norte'
              'RS Rio Grande do Sul'
              'RO Rond'#244'nia'
              'RR Roraima'
              'SC Santa Catarina'
              'SP S'#227'o Paulo'
              'SE Sergipe'
              'TO Tocantins')
            ParentFont = False
            TabOrder = 11
            Values.Strings = (
              'AC'
              'AL'
              'AP'
              'AM'
              'BA'
              'CE'
              'DF'
              'ES'
              'GO'
              'MA'
              'MT'
              'MS'
              'MG'
              'PA'
              'PB'
              'PR'
              'PE'
              'PI'
              'RJ'
              'RN'
              'RS'
              'RO'
              'RR'
              'SC'
              'SP'
              'SE'
              'TO')
          end
          object CblTpTransp: TBrvCheckListBox
            Left = 122
            Top = 135
            Width = 789
            Height = 19
            Columns = 4
            Font.Charset = ANSI_CHARSET
            Font.Color = clWindowText
            Font.Height = -11
            Font.Name = 'MS Sans Serif'
            Font.Style = [fsBold]
            ItemHeight = 13
            Items.Strings = (
              'Venda'
              'Transfer'#234'ncia'
              'Devolu'#231#227'o'
              'Complemento')
            ParentFont = False
            TabOrder = 12
          end
        end
      end
      object TbsProces: TTabSheet
        Caption = 'TbsProces'
        ImageIndex = 1
        ExplicitLeft = 0
        ExplicitTop = 0
        ExplicitWidth = 0
        ExplicitHeight = 0
        object Splitter2: TSplitter
          Left = 0
          Top = 150
          Width = 950
          Height = 5
          Cursor = crVSplit
          Align = alTop
          ExplicitTop = 153
          ExplicitWidth = 924
        end
        object Panel3: TPanel
          Left = 0
          Top = 0
          Width = 950
          Height = 150
          Align = alTop
          BorderStyle = bsSingle
          TabOrder = 0
          object Panel10: TPanel
            Left = 1
            Top = 1
            Width = 944
            Height = 19
            Align = alTop
            BorderStyle = bsSingle
            Caption = 'Filtros'
            Font.Charset = DEFAULT_CHARSET
            Font.Color = clWindowText
            Font.Height = -11
            Font.Name = 'MS Sans Serif'
            Font.Style = [fsBold]
            ParentFont = False
            TabOrder = 0
          end
          object DbgFiltro: TDBGrid
            Left = 1
            Top = 20
            Width = 944
            Height = 125
            Align = alClient
            Color = clBtnFace
            DataSource = DtsFiltro1
            Font.Charset = DEFAULT_CHARSET
            Font.Color = clWindowText
            Font.Height = -11
            Font.Name = 'MS Sans Serif'
            Font.Style = [fsBold]
            ParentFont = False
            ReadOnly = True
            TabOrder = 1
            TitleFont.Charset = DEFAULT_CHARSET
            TitleFont.Color = clWindowText
            TitleFont.Height = -11
            TitleFont.Name = 'MS Sans Serif'
            TitleFont.Style = []
            Columns = <
              item
                Expanded = False
                FieldName = 'NmFiltro'
                Title.Caption = 'Nome do Filtro'
                Title.Font.Charset = DEFAULT_CHARSET
                Title.Font.Color = clWindowText
                Title.Font.Height = -11
                Title.Font.Name = 'MS Sans Serif'
                Title.Font.Style = [fsBold]
                Width = 161
                Visible = True
              end
              item
                Expanded = False
                FieldName = 'DsFiltro'
                Title.Caption = 'Descri'#231#227'o do Filtro'
                Title.Font.Charset = DEFAULT_CHARSET
                Title.Font.Color = clWindowText
                Title.Font.Height = -11
                Title.Font.Name = 'MS Sans Serif'
                Title.Font.Style = [fsBold]
                Width = 722
                Visible = True
              end>
          end
        end
        object PgcProces: TPageControl
          Left = 0
          Top = 179
          Width = 950
          Height = 271
          ActivePage = TbsTomado
          Align = alClient
          TabOrder = 1
          object TbsTomado: TTabSheet
            Caption = 'TbsTomado'
            ImageIndex = 2
            ExplicitLeft = 0
            ExplicitTop = 0
            ExplicitWidth = 0
            ExplicitHeight = 0
            object Panel4: TPanel
              Left = 0
              Top = 157
              Width = 942
              Height = 86
              Align = alBottom
              BorderStyle = bsSingle
              Font.Charset = DEFAULT_CHARSET
              Font.Color = clWindowText
              Font.Height = -11
              Font.Name = 'MS Sans Serif'
              Font.Style = [fsBold]
              ParentFont = False
              TabOrder = 0
              ExplicitLeft = 1
              ExplicitTop = 163
              DesignSize = (
                938
                82)
              object BrvBitBtn6: TBrvBitBtn
                Left = 587
                Top = 52
                Width = 113
                Height = 25
                Anchors = [akTop, akRight]
                Caption = 'Voltar'
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
                OnClick = BrvBitBtn6Click
                BrTipoBotao = BrBtnPrimei
                ExplicitLeft = 595
              end
              object BrvBitBtn7: TBrvBitBtn
                Left = 700
                Top = 52
                Width = 113
                Height = 25
                Anchors = [akTop, akRight]
                Caption = 'Detalhar'
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
                OnClick = BrvBitBtn7Click
                BrTipoBotao = BrBtnPesqui
                ExplicitLeft = 708
              end
              object BrvBitBtn11: TBrvBitBtn
                Left = 813
                Top = 52
                Width = 113
                Height = 25
                Anchors = [akTop, akRight]
                Caption = 'Carga'
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
                OnClick = BrvBitBtn9Click
                BrTipoBotao = BrBtnCarga
                ExplicitLeft = 821
              end
              object GroupBox6: TGroupBox
                Left = 6
                Top = 4
                Width = 553
                Height = 36
                Caption = 'Geral:'
                Font.Charset = DEFAULT_CHARSET
                Font.Color = clWindowText
                Font.Height = -11
                Font.Name = 'MS Sans Serif'
                Font.Style = [fsBold]
                ParentFont = False
                TabOrder = 3
                object Label54: TLabel
                  Left = 6
                  Top = 14
                  Width = 34
                  Height = 13
                  Caption = 'CTRC'
                  Font.Charset = DEFAULT_CHARSET
                  Font.Color = clWindowText
                  Font.Height = -11
                  Font.Name = 'MS Sans Serif'
                  Font.Style = [fsBold]
                  ParentFont = False
                end
                object Label59: TLabel
                  Left = 159
                  Top = 14
                  Width = 29
                  Height = 13
                  Caption = 'Peso'
                  Font.Charset = DEFAULT_CHARSET
                  Font.Color = clWindowText
                  Font.Height = -11
                  Font.Name = 'MS Sans Serif'
                  Font.Style = [fsBold]
                  ParentFont = False
                end
                object Label60: TLabel
                  Left = 326
                  Top = 14
                  Width = 90
                  Height = 13
                  Caption = 'Vr. Mercadorias'
                  Font.Charset = DEFAULT_CHARSET
                  Font.Color = clWindowText
                  Font.Height = -11
                  Font.Name = 'MS Sans Serif'
                  Font.Style = [fsBold]
                  ParentFont = False
                end
                object EdtTtCtrTom: TBrvEditNum
                  Left = 51
                  Top = 11
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
                  BrOnBeforeConsulta = EdtCdRotaBrOnBeforeConsulta
                  BrConfigurar.Strings = (
                    'EdtCdRota;CdRota;S;S;'
                    'LblDsPraca;DsPraca;S;N;')
                  BrDicionario = DmSrvApl.BrvDicionario
                  BrQueryCode = 14
                  BrRecordar = False
                end
                object EdtTtPesTom: TBrvEditNum
                  Left = 195
                  Top = 11
                  Width = 120
                  Height = 21
                  TabStop = False
                  BevelInner = bvLowered
                  BevelKind = bkFlat
                  BevelWidth = 2
                  BorderStyle = bsNone
                  ParentColor = True
                  ReadOnly = True
                  TabOrder = 1
                  Text = '0,00'
                  BrAlignment = taRightJustify
                  BrCasasDecimais = 2
                  BrSepararMilhar = True
                  BrAsInteger = 0
                  BrAsFloatSQL = '0.00'
                  BrVisibleButton = False
                  BrFunctionButton = TpConsulta
                  BrOnBeforeConsulta = EdtCdRotaBrOnBeforeConsulta
                  BrConfigurar.Strings = (
                    'EdtCdRota;CdRota;S;S;'
                    'LblDsPraca;DsPraca;S;N;')
                  BrDicionario = DmSrvApl.BrvDicionario
                  BrQueryCode = 14
                  BrRecordar = False
                end
                object EdtTtMerTom: TBrvEditNum
                  Left = 421
                  Top = 11
                  Width = 120
                  Height = 21
                  TabStop = False
                  BevelInner = bvLowered
                  BevelKind = bkFlat
                  BevelWidth = 2
                  BorderStyle = bsNone
                  ParentColor = True
                  ReadOnly = True
                  TabOrder = 2
                  Text = '0,00'
                  BrAlignment = taRightJustify
                  BrCasasDecimais = 2
                  BrSepararMilhar = True
                  BrAsInteger = 0
                  BrAsFloatSQL = '0.00'
                  BrVisibleButton = False
                  BrFunctionButton = TpConsulta
                  BrOnBeforeConsulta = EdtCdRotaBrOnBeforeConsulta
                  BrConfigurar.Strings = (
                    'EdtCdRota;CdRota;S;S;'
                    'LblDsPraca;DsPraca;S;N;')
                  BrDicionario = DmSrvApl.BrvDicionario
                  BrQueryCode = 14
                  BrRecordar = False
                end
              end
              object GroupBox7: TGroupBox
                Left = 6
                Top = 40
                Width = 553
                Height = 36
                Caption = 'Marcados:'
                Font.Charset = DEFAULT_CHARSET
                Font.Color = clWindowText
                Font.Height = -11
                Font.Name = 'MS Sans Serif'
                Font.Style = [fsBold]
                ParentFont = False
                TabOrder = 4
                object Label61: TLabel
                  Left = 6
                  Top = 15
                  Width = 34
                  Height = 13
                  Caption = 'CTRC'
                  Font.Charset = DEFAULT_CHARSET
                  Font.Color = clWindowText
                  Font.Height = -11
                  Font.Name = 'MS Sans Serif'
                  Font.Style = [fsBold]
                  ParentFont = False
                end
                object Label62: TLabel
                  Left = 159
                  Top = 15
                  Width = 29
                  Height = 13
                  Caption = 'Peso'
                  Font.Charset = DEFAULT_CHARSET
                  Font.Color = clWindowText
                  Font.Height = -11
                  Font.Name = 'MS Sans Serif'
                  Font.Style = [fsBold]
                  ParentFont = False
                end
                object Label63: TLabel
                  Left = 326
                  Top = 15
                  Width = 90
                  Height = 13
                  Caption = 'Vr. Mercadorias'
                  Font.Charset = DEFAULT_CHARSET
                  Font.Color = clWindowText
                  Font.Height = -11
                  Font.Name = 'MS Sans Serif'
                  Font.Style = [fsBold]
                  ParentFont = False
                end
                object EdtTtCtToMa: TBrvEditNum
                  Left = 51
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
                  BrOnBeforeConsulta = EdtCdRotaBrOnBeforeConsulta
                  BrConfigurar.Strings = (
                    'EdtCdRota;CdRota;S;S;'
                    'LblDsPraca;DsPraca;S;N;')
                  BrDicionario = DmSrvApl.BrvDicionario
                  BrQueryCode = 14
                  BrRecordar = False
                end
                object EdtTtPeToMa: TBrvEditNum
                  Left = 194
                  Top = 12
                  Width = 120
                  Height = 21
                  TabStop = False
                  BevelInner = bvLowered
                  BevelKind = bkFlat
                  BevelWidth = 2
                  BorderStyle = bsNone
                  ParentColor = True
                  ReadOnly = True
                  TabOrder = 1
                  Text = '0,00'
                  BrAlignment = taRightJustify
                  BrCasasDecimais = 2
                  BrSepararMilhar = True
                  BrAsInteger = 0
                  BrAsFloatSQL = '0.00'
                  BrVisibleButton = False
                  BrFunctionButton = TpConsulta
                  BrOnBeforeConsulta = EdtCdRotaBrOnBeforeConsulta
                  BrConfigurar.Strings = (
                    'EdtCdRota;CdRota;S;S;'
                    'LblDsPraca;DsPraca;S;N;')
                  BrDicionario = DmSrvApl.BrvDicionario
                  BrQueryCode = 14
                  BrRecordar = False
                end
                object EdtTtMeToMa: TBrvEditNum
                  Left = 421
                  Top = 12
                  Width = 120
                  Height = 21
                  TabStop = False
                  BevelInner = bvLowered
                  BevelKind = bkFlat
                  BevelWidth = 2
                  BorderStyle = bsNone
                  ParentColor = True
                  ReadOnly = True
                  TabOrder = 2
                  Text = '0,00'
                  BrAlignment = taRightJustify
                  BrCasasDecimais = 2
                  BrSepararMilhar = True
                  BrAsInteger = 0
                  BrAsFloatSQL = '0.00'
                  BrVisibleButton = False
                  BrFunctionButton = TpConsulta
                  BrOnBeforeConsulta = EdtCdRotaBrOnBeforeConsulta
                  BrConfigurar.Strings = (
                    'EdtCdRota;CdRota;S;S;'
                    'LblDsPraca;DsPraca;S;N;')
                  BrDicionario = DmSrvApl.BrvDicionario
                  BrQueryCode = 14
                  BrRecordar = False
                end
              end
            end
            object DbgTomado: TBrvDbGrid
              Left = 0
              Top = 0
              Width = 942
              Height = 157
              BrShowMemo = True
              BrReadOnlyStyle = []
              BrReadOnlyColor = clBlack
              Align = alClient
              DataSource = DtsTomado
              Options = [dgEditing, dgTitles, dgIndicator, dgColumnResize, dgColLines, dgRowLines, dgTabs, dgCancelOnExit]
              PopupMenu = PopTomado
              TabOrder = 1
              TitleFont.Charset = DEFAULT_CHARSET
              TitleFont.Color = clWindowText
              TitleFont.Height = -11
              TitleFont.Name = 'Tahoma'
              TitleFont.Style = []
              OnCellClick = DbgTomadoCellClick
              BrDicionario = DmSrvApl.BrvDicionario
              BrDrawColumn.Strings = (
                'N'#227'o remova essas duas linhas de formata'#231#227'o:'
                'CampoTabela;Operador;ValorComparativo;Cor;'
                'StVencim;=;2;clBlue;'
                'StVencim;=;1;clYellow;'
                'StVencim;=;3;clRed;')
              Columns = <
                item
                  ButtonStyle = cbsNone
                  Expanded = False
                  FieldName = 'SnMarcad'
                  Title.Alignment = taCenter
                  Title.Caption = 'R'
                  Title.Font.Charset = DEFAULT_CHARSET
                  Title.Font.Color = clWindowText
                  Title.Font.Height = -11
                  Title.Font.Name = 'Wingdings 2'
                  Title.Font.Style = [fsBold]
                  Width = 20
                  Visible = True
                  BrConsulta = 0
                  BrPermissao = []
                  BrValueChecked = 'S'
                  BrValueUnchecked = 'N'
                  BrValueHalfChecked = False
                  BrSaveOnClick = True
                end
                item
                  Expanded = False
                  FieldName = 'CdTomado'
                  Font.Charset = DEFAULT_CHARSET
                  Font.Color = clBlack
                  Font.Height = -11
                  Font.Name = 'Tahoma'
                  Font.Style = []
                  ReadOnly = True
                  Title.Caption = 'Tomador'
                  Title.Font.Charset = DEFAULT_CHARSET
                  Title.Font.Color = clWindowText
                  Title.Font.Height = -11
                  Title.Font.Name = 'Tahoma'
                  Title.Font.Style = [fsBold]
                  Width = 54
                  Visible = True
                  BrConsulta = 0
                  BrPermissao = []
                  BrValueHalfChecked = False
                  BrSaveOnClick = False
                end
                item
                  Expanded = False
                  FieldName = 'NmTomado'
                  Font.Charset = DEFAULT_CHARSET
                  Font.Color = clBlack
                  Font.Height = -11
                  Font.Name = 'Tahoma'
                  Font.Style = []
                  ReadOnly = True
                  Title.Caption = 'Raz'#227'o social do tomador'
                  Title.Font.Charset = DEFAULT_CHARSET
                  Title.Font.Color = clWindowText
                  Title.Font.Height = -11
                  Title.Font.Name = 'Tahoma'
                  Title.Font.Style = [fsBold]
                  Width = 306
                  Visible = True
                  BrConsulta = 0
                  BrPermissao = []
                  BrValueHalfChecked = False
                  BrSaveOnClick = False
                end
                item
                  Expanded = False
                  FieldName = 'NmCidade'
                  Font.Charset = DEFAULT_CHARSET
                  Font.Color = clBlack
                  Font.Height = -11
                  Font.Name = 'Tahoma'
                  Font.Style = []
                  ReadOnly = True
                  Title.Caption = 'Cidade'
                  Title.Font.Charset = DEFAULT_CHARSET
                  Title.Font.Color = clWindowText
                  Title.Font.Height = -11
                  Title.Font.Name = 'Tahoma'
                  Title.Font.Style = [fsBold]
                  Width = 183
                  Visible = True
                  BrConsulta = 0
                  BrPermissao = []
                  BrValueHalfChecked = False
                  BrSaveOnClick = False
                end
                item
                  Expanded = False
                  FieldName = 'CdEstado'
                  Font.Charset = DEFAULT_CHARSET
                  Font.Color = clBlack
                  Font.Height = -11
                  Font.Name = 'Tahoma'
                  Font.Style = []
                  ReadOnly = True
                  Title.Caption = 'UF'
                  Title.Font.Charset = DEFAULT_CHARSET
                  Title.Font.Color = clWindowText
                  Title.Font.Height = -11
                  Title.Font.Name = 'Tahoma'
                  Title.Font.Style = [fsBold]
                  Visible = True
                  BrConsulta = 0
                  BrPermissao = []
                  BrValueHalfChecked = False
                  BrSaveOnClick = False
                end
                item
                  Expanded = False
                  FieldName = 'QtCtrc'
                  Font.Charset = DEFAULT_CHARSET
                  Font.Color = clBlack
                  Font.Height = -11
                  Font.Name = 'Tahoma'
                  Font.Style = []
                  ReadOnly = True
                  Title.Caption = 'CTRC'
                  Title.Font.Charset = DEFAULT_CHARSET
                  Title.Font.Color = clWindowText
                  Title.Font.Height = -11
                  Title.Font.Name = 'Tahoma'
                  Title.Font.Style = [fsBold]
                  Width = 50
                  Visible = True
                  BrConsulta = 0
                  BrPermissao = []
                  BrValueHalfChecked = False
                  BrSaveOnClick = False
                end
                item
                  Expanded = False
                  FieldName = 'QtDiaVen'
                  Font.Charset = DEFAULT_CHARSET
                  Font.Color = clBlack
                  Font.Height = -11
                  Font.Name = 'Tahoma'
                  Font.Style = []
                  ReadOnly = True
                  Title.Caption = 'Dias p/ Vencto'
                  Title.Font.Charset = DEFAULT_CHARSET
                  Title.Font.Color = clWindowText
                  Title.Font.Height = -11
                  Title.Font.Name = 'Tahoma'
                  Title.Font.Style = [fsBold]
                  Visible = True
                  BrConsulta = 0
                  BrPermissao = []
                  BrValueHalfChecked = False
                  BrSaveOnClick = False
                end
                item
                  Expanded = False
                  FieldName = 'DtPreEnt'
                  Font.Charset = DEFAULT_CHARSET
                  Font.Color = clBlack
                  Font.Height = -11
                  Font.Name = 'Tahoma'
                  Font.Style = []
                  ReadOnly = True
                  Title.Caption = 'Menor Vencto'
                  Title.Font.Charset = DEFAULT_CHARSET
                  Title.Font.Color = clWindowText
                  Title.Font.Height = -11
                  Title.Font.Name = 'Tahoma'
                  Title.Font.Style = [fsBold]
                  Visible = True
                  BrConsulta = 0
                  BrPermissao = []
                  BrValueHalfChecked = False
                  BrSaveOnClick = False
                end
                item
                  Expanded = False
                  FieldName = 'NrPeso'
                  Font.Charset = DEFAULT_CHARSET
                  Font.Color = clBlack
                  Font.Height = -11
                  Font.Name = 'Tahoma'
                  Font.Style = []
                  ReadOnly = True
                  Title.Caption = 'Peso'
                  Title.Font.Charset = DEFAULT_CHARSET
                  Title.Font.Color = clWindowText
                  Title.Font.Height = -11
                  Title.Font.Name = 'Tahoma'
                  Title.Font.Style = [fsBold]
                  Width = 70
                  Visible = True
                  BrConsulta = 0
                  BrPermissao = []
                  BrValueHalfChecked = False
                  BrSaveOnClick = False
                end
                item
                  Expanded = False
                  FieldName = 'VrMercad'
                  Font.Charset = DEFAULT_CHARSET
                  Font.Color = clBlack
                  Font.Height = -11
                  Font.Name = 'Tahoma'
                  Font.Style = []
                  ReadOnly = True
                  Title.Caption = 'Vr. Mercadoria'
                  Title.Font.Charset = DEFAULT_CHARSET
                  Title.Font.Color = clWindowText
                  Title.Font.Height = -11
                  Title.Font.Name = 'Tahoma'
                  Title.Font.Style = [fsBold]
                  Visible = True
                  BrConsulta = 0
                  BrPermissao = []
                  BrValueHalfChecked = False
                  BrSaveOnClick = False
                end>
            end
          end
          object TbsRota: TTabSheet
            Caption = 'TbsRota'
            ExplicitLeft = 0
            ExplicitTop = 0
            ExplicitWidth = 0
            ExplicitHeight = 0
            object DbgRotas: TBrvDbGrid
              Left = 0
              Top = 0
              Width = 942
              Height = 157
              BrShowMemo = True
              BrReadOnlyStyle = []
              BrReadOnlyColor = clBlack
              Align = alClient
              DataSource = DtsRotas
              Options = [dgEditing, dgTitles, dgIndicator, dgColumnResize, dgColLines, dgRowLines, dgTabs, dgCancelOnExit]
              PopupMenu = PopRotas
              TabOrder = 0
              TitleFont.Charset = DEFAULT_CHARSET
              TitleFont.Color = clWindowText
              TitleFont.Height = -11
              TitleFont.Name = 'Tahoma'
              TitleFont.Style = []
              OnCellClick = DbgRotasCellClick
              BrDicionario = DmSrvApl.BrvDicionario
              BrDrawColumn.Strings = (
                'N'#227'o remova essas duas linhas de formata'#231#227'o:'
                'CampoTabela;Operador;ValorComparativo;Cor;'
                'StVencim;=;2;clBlue;'
                'StVencim;=;1;clYellow;'
                'StVencim;=;3;clRed;')
              Columns = <
                item
                  ButtonStyle = cbsNone
                  Expanded = False
                  FieldName = 'SnMarcad'
                  Title.Alignment = taCenter
                  Title.Caption = 'R'
                  Title.Font.Charset = DEFAULT_CHARSET
                  Title.Font.Color = clWindowText
                  Title.Font.Height = -11
                  Title.Font.Name = 'Wingdings 2'
                  Title.Font.Style = [fsBold]
                  Width = 20
                  Visible = True
                  BrConsulta = 0
                  BrPermissao = []
                  BrValueChecked = 'S'
                  BrValueUnchecked = 'N'
                  BrValueHalfChecked = False
                  BrSaveOnClick = True
                end
                item
                  Expanded = False
                  FieldName = 'CdRota'
                  ReadOnly = True
                  Title.Caption = 'Rota'
                  Title.Font.Charset = DEFAULT_CHARSET
                  Title.Font.Color = clWindowText
                  Title.Font.Height = -11
                  Title.Font.Name = 'Tahoma'
                  Title.Font.Style = [fsBold]
                  Width = 41
                  Visible = True
                  BrConsulta = 0
                  BrPermissao = []
                  BrValueHalfChecked = False
                  BrSaveOnClick = False
                end
                item
                  Expanded = False
                  FieldName = 'DsPraca'
                  ReadOnly = True
                  Title.Caption = 'Pra'#231'a'
                  Title.Font.Charset = DEFAULT_CHARSET
                  Title.Font.Color = clWindowText
                  Title.Font.Height = -11
                  Title.Font.Name = 'Tahoma'
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
                  FieldName = 'QtCtrc'
                  ReadOnly = True
                  Title.Caption = 'Qtde CTRC'
                  Title.Font.Charset = DEFAULT_CHARSET
                  Title.Font.Color = clWindowText
                  Title.Font.Height = -11
                  Title.Font.Name = 'Tahoma'
                  Title.Font.Style = [fsBold]
                  Width = 68
                  Visible = True
                  BrConsulta = 0
                  BrPermissao = []
                  BrValueHalfChecked = False
                  BrSaveOnClick = False
                end
                item
                  Expanded = False
                  FieldName = 'QtDiaVen'
                  ReadOnly = True
                  Title.Caption = 'Dias p/ Vencto'
                  Title.Font.Charset = DEFAULT_CHARSET
                  Title.Font.Color = clWindowText
                  Title.Font.Height = -11
                  Title.Font.Name = 'Tahoma'
                  Title.Font.Style = [fsBold]
                  Width = 87
                  Visible = True
                  BrConsulta = 0
                  BrPermissao = []
                  BrValueHalfChecked = False
                  BrSaveOnClick = False
                end
                item
                  Expanded = False
                  FieldName = 'DtPreEnt'
                  Title.Caption = 'Menor Vencto'
                  Title.Font.Charset = DEFAULT_CHARSET
                  Title.Font.Color = clWindowText
                  Title.Font.Height = -11
                  Title.Font.Name = 'Tahoma'
                  Title.Font.Style = [fsBold]
                  Width = 94
                  Visible = True
                  BrConsulta = 0
                  BrPermissao = []
                  BrValueHalfChecked = False
                  BrSaveOnClick = False
                end
                item
                  Alignment = taRightJustify
                  Expanded = False
                  FieldName = 'NrPeso'
                  ReadOnly = True
                  Title.Caption = 'Peso'
                  Title.Font.Charset = DEFAULT_CHARSET
                  Title.Font.Color = clWindowText
                  Title.Font.Height = -11
                  Title.Font.Name = 'Tahoma'
                  Title.Font.Style = [fsBold]
                  Width = 80
                  Visible = True
                  BrConsulta = 0
                  BrPermissao = []
                  BrValueHalfChecked = False
                  BrSaveOnClick = False
                end
                item
                  Alignment = taRightJustify
                  Expanded = False
                  FieldName = 'VrMercad'
                  ReadOnly = True
                  Title.Caption = 'Vr. Mercadoria'
                  Title.Font.Charset = DEFAULT_CHARSET
                  Title.Font.Color = clWindowText
                  Title.Font.Height = -11
                  Title.Font.Name = 'Tahoma'
                  Title.Font.Style = [fsBold]
                  Width = 95
                  Visible = True
                  BrConsulta = 0
                  BrPermissao = []
                  BrValueHalfChecked = False
                  BrSaveOnClick = False
                end>
            end
            object Panel18: TPanel
              Left = 0
              Top = 157
              Width = 942
              Height = 86
              Align = alBottom
              BorderStyle = bsSingle
              Font.Charset = DEFAULT_CHARSET
              Font.Color = clWindowText
              Font.Height = -11
              Font.Name = 'MS Sans Serif'
              Font.Style = [fsBold]
              ParentFont = False
              TabOrder = 1
              DesignSize = (
                938
                82)
              object BrvBitBtn2: TBrvBitBtn
                Left = 587
                Top = 52
                Width = 113
                Height = 25
                Anchors = [akTop, akRight]
                Caption = 'Voltar'
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
                OnClick = BrvBitBtn2Click
                BrTipoBotao = BrBtnPrimei
                ExplicitLeft = 595
              end
              object BrvBitBtn3: TBrvBitBtn
                Left = 700
                Top = 52
                Width = 113
                Height = 25
                Anchors = [akTop, akRight]
                Caption = 'Detalhar'
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
                OnClick = BrvBitBtn3Click
                BrTipoBotao = BrBtnPesqui
                ExplicitLeft = 708
              end
              object BrvBitBtn13: TBrvBitBtn
                Left = 814
                Top = 52
                Width = 113
                Height = 25
                Anchors = [akTop, akRight]
                Caption = 'Carga'
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
                OnClick = BrvBitBtn9Click
                BrTipoBotao = BrBtnCarga
                ExplicitLeft = 822
              end
              object GroupBox2: TGroupBox
                Left = 6
                Top = 4
                Width = 551
                Height = 36
                Caption = 'Geral:'
                Font.Charset = DEFAULT_CHARSET
                Font.Color = clWindowText
                Font.Height = -11
                Font.Name = 'MS Sans Serif'
                Font.Style = [fsBold]
                ParentFont = False
                TabOrder = 3
                object Label2: TLabel
                  Left = 6
                  Top = 14
                  Width = 34
                  Height = 13
                  Caption = 'CTRC'
                  Font.Charset = DEFAULT_CHARSET
                  Font.Color = clWindowText
                  Font.Height = -11
                  Font.Name = 'MS Sans Serif'
                  Font.Style = [fsBold]
                  ParentFont = False
                end
                object Label3: TLabel
                  Left = 159
                  Top = 14
                  Width = 29
                  Height = 13
                  Caption = 'Peso'
                  Font.Charset = DEFAULT_CHARSET
                  Font.Color = clWindowText
                  Font.Height = -11
                  Font.Name = 'MS Sans Serif'
                  Font.Style = [fsBold]
                  ParentFont = False
                end
                object Label5: TLabel
                  Left = 326
                  Top = 14
                  Width = 90
                  Height = 13
                  Caption = 'Vr. Mercadorias'
                  Font.Charset = DEFAULT_CHARSET
                  Font.Color = clWindowText
                  Font.Height = -11
                  Font.Name = 'MS Sans Serif'
                  Font.Style = [fsBold]
                  ParentFont = False
                end
                object EdtTtCtrRot: TBrvEditNum
                  Left = 51
                  Top = 11
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
                  BrOnBeforeConsulta = EdtCdRotaBrOnBeforeConsulta
                  BrConfigurar.Strings = (
                    'EdtCdRota;CdRota;S;S;'
                    'LblDsPraca;DsPraca;S;N;')
                  BrDicionario = DmSrvApl.BrvDicionario
                  BrQueryCode = 14
                  BrRecordar = False
                end
                object EdtTtPesRot: TBrvEditNum
                  Left = 195
                  Top = 11
                  Width = 120
                  Height = 21
                  TabStop = False
                  BevelInner = bvLowered
                  BevelKind = bkFlat
                  BevelWidth = 2
                  BorderStyle = bsNone
                  ParentColor = True
                  ReadOnly = True
                  TabOrder = 1
                  Text = '0,00'
                  BrAlignment = taRightJustify
                  BrCasasDecimais = 2
                  BrSepararMilhar = True
                  BrAsInteger = 0
                  BrAsFloatSQL = '0.00'
                  BrVisibleButton = False
                  BrFunctionButton = TpConsulta
                  BrOnBeforeConsulta = EdtCdRotaBrOnBeforeConsulta
                  BrConfigurar.Strings = (
                    'EdtCdRota;CdRota;S;S;'
                    'LblDsPraca;DsPraca;S;N;')
                  BrDicionario = DmSrvApl.BrvDicionario
                  BrQueryCode = 14
                  BrRecordar = False
                end
                object EdtTtMerRot: TBrvEditNum
                  Left = 421
                  Top = 11
                  Width = 120
                  Height = 21
                  TabStop = False
                  BevelInner = bvLowered
                  BevelKind = bkFlat
                  BevelWidth = 2
                  BorderStyle = bsNone
                  ParentColor = True
                  ReadOnly = True
                  TabOrder = 2
                  Text = '0,00'
                  BrAlignment = taRightJustify
                  BrCasasDecimais = 2
                  BrSepararMilhar = True
                  BrAsInteger = 0
                  BrAsFloatSQL = '0.00'
                  BrVisibleButton = False
                  BrFunctionButton = TpConsulta
                  BrOnBeforeConsulta = EdtCdRotaBrOnBeforeConsulta
                  BrConfigurar.Strings = (
                    'EdtCdRota;CdRota;S;S;'
                    'LblDsPraca;DsPraca;S;N;')
                  BrDicionario = DmSrvApl.BrvDicionario
                  BrQueryCode = 14
                  BrRecordar = False
                end
              end
              object GroupBox3: TGroupBox
                Left = 6
                Top = 40
                Width = 551
                Height = 36
                Caption = 'Marcados:'
                Font.Charset = DEFAULT_CHARSET
                Font.Color = clWindowText
                Font.Height = -11
                Font.Name = 'MS Sans Serif'
                Font.Style = [fsBold]
                ParentFont = False
                TabOrder = 4
                object Label8: TLabel
                  Left = 6
                  Top = 15
                  Width = 34
                  Height = 13
                  Caption = 'CTRC'
                  Font.Charset = DEFAULT_CHARSET
                  Font.Color = clWindowText
                  Font.Height = -11
                  Font.Name = 'MS Sans Serif'
                  Font.Style = [fsBold]
                  ParentFont = False
                end
                object Label14: TLabel
                  Left = 159
                  Top = 15
                  Width = 29
                  Height = 13
                  Caption = 'Peso'
                  Font.Charset = DEFAULT_CHARSET
                  Font.Color = clWindowText
                  Font.Height = -11
                  Font.Name = 'MS Sans Serif'
                  Font.Style = [fsBold]
                  ParentFont = False
                end
                object Label15: TLabel
                  Left = 326
                  Top = 15
                  Width = 90
                  Height = 13
                  Caption = 'Vr. Mercadorias'
                  Font.Charset = DEFAULT_CHARSET
                  Font.Color = clWindowText
                  Font.Height = -11
                  Font.Name = 'MS Sans Serif'
                  Font.Style = [fsBold]
                  ParentFont = False
                end
                object EdtTtCtRoMa: TBrvEditNum
                  Left = 51
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
                  BrOnBeforeConsulta = EdtCdRotaBrOnBeforeConsulta
                  BrConfigurar.Strings = (
                    'EdtCdRota;CdRota;S;S;'
                    'LblDsPraca;DsPraca;S;N;')
                  BrDicionario = DmSrvApl.BrvDicionario
                  BrQueryCode = 14
                  BrRecordar = False
                end
                object EdtTtPeRoMa: TBrvEditNum
                  Left = 194
                  Top = 12
                  Width = 120
                  Height = 21
                  TabStop = False
                  BevelInner = bvLowered
                  BevelKind = bkFlat
                  BevelWidth = 2
                  BorderStyle = bsNone
                  ParentColor = True
                  ReadOnly = True
                  TabOrder = 1
                  Text = '0,00'
                  BrAlignment = taRightJustify
                  BrCasasDecimais = 2
                  BrSepararMilhar = True
                  BrAsInteger = 0
                  BrAsFloatSQL = '0.00'
                  BrVisibleButton = False
                  BrFunctionButton = TpConsulta
                  BrOnBeforeConsulta = EdtCdRotaBrOnBeforeConsulta
                  BrConfigurar.Strings = (
                    'EdtCdRota;CdRota;S;S;'
                    'LblDsPraca;DsPraca;S;N;')
                  BrDicionario = DmSrvApl.BrvDicionario
                  BrQueryCode = 14
                  BrRecordar = False
                end
                object EdtTtMeRoMa: TBrvEditNum
                  Left = 421
                  Top = 12
                  Width = 120
                  Height = 21
                  TabStop = False
                  BevelInner = bvLowered
                  BevelKind = bkFlat
                  BevelWidth = 2
                  BorderStyle = bsNone
                  ParentColor = True
                  ReadOnly = True
                  TabOrder = 2
                  Text = '0,00'
                  BrAlignment = taRightJustify
                  BrCasasDecimais = 2
                  BrSepararMilhar = True
                  BrAsInteger = 0
                  BrAsFloatSQL = '0.00'
                  BrVisibleButton = False
                  BrFunctionButton = TpConsulta
                  BrOnBeforeConsulta = EdtCdRotaBrOnBeforeConsulta
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
          object TbsCidade: TTabSheet
            Caption = 'TbsCidade'
            ImageIndex = 1
            ExplicitLeft = 0
            ExplicitTop = 0
            ExplicitWidth = 0
            ExplicitHeight = 0
            object DbgCidade: TBrvDbGrid
              Left = 0
              Top = 0
              Width = 942
              Height = 157
              BrShowMemo = True
              BrReadOnlyStyle = []
              BrReadOnlyColor = clBlack
              Align = alClient
              DataSource = DtsCidade
              Options = [dgEditing, dgTitles, dgIndicator, dgColumnResize, dgColLines, dgRowLines, dgTabs, dgCancelOnExit]
              PopupMenu = PopCidade
              TabOrder = 0
              TitleFont.Charset = DEFAULT_CHARSET
              TitleFont.Color = clWindowText
              TitleFont.Height = -11
              TitleFont.Name = 'Tahoma'
              TitleFont.Style = []
              OnCellClick = DbgCidadeCellClick
              BrDicionario = DmSrvApl.BrvDicionario
              BrDrawColumn.Strings = (
                'N'#227'o remova essas duas linhas de formata'#231#227'o:'
                'CampoTabela;Operador;ValorComparativo;Cor;'
                'StVencim;=;2;clBlue;'
                'StVencim;=;1;clYellow;'
                'StVencim;=;3;clRed;')
              Columns = <
                item
                  ButtonStyle = cbsNone
                  Expanded = False
                  FieldName = 'SnMarcad'
                  Title.Alignment = taCenter
                  Title.Caption = 'R'
                  Title.Font.Charset = DEFAULT_CHARSET
                  Title.Font.Color = clWindowText
                  Title.Font.Height = -11
                  Title.Font.Name = 'Wingdings 2'
                  Title.Font.Style = [fsBold]
                  Width = 20
                  Visible = True
                  BrConsulta = 0
                  BrPermissao = []
                  BrValueChecked = 'S'
                  BrValueUnchecked = 'N'
                  BrValueHalfChecked = False
                  BrSaveOnClick = True
                end
                item
                  Expanded = False
                  FieldName = 'CpCidade'
                  ReadOnly = True
                  Title.Caption = 'CEP'
                  Title.Font.Charset = DEFAULT_CHARSET
                  Title.Font.Color = clWindowText
                  Title.Font.Height = -11
                  Title.Font.Name = 'Tahoma'
                  Title.Font.Style = [fsBold]
                  Width = 41
                  Visible = True
                  BrConsulta = 0
                  BrPermissao = []
                  BrValueHalfChecked = False
                  BrSaveOnClick = False
                end
                item
                  Expanded = False
                  FieldName = 'NmCidade'
                  ReadOnly = True
                  Title.Caption = 'Cidade'
                  Title.Font.Charset = DEFAULT_CHARSET
                  Title.Font.Color = clWindowText
                  Title.Font.Height = -11
                  Title.Font.Name = 'Tahoma'
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
                  FieldName = 'CdEstado'
                  ReadOnly = True
                  Title.Caption = 'UF'
                  Title.Font.Charset = DEFAULT_CHARSET
                  Title.Font.Color = clWindowText
                  Title.Font.Height = -11
                  Title.Font.Name = 'Tahoma'
                  Title.Font.Style = [fsBold]
                  Width = 68
                  Visible = True
                  BrConsulta = 0
                  BrPermissao = []
                  BrValueHalfChecked = False
                  BrSaveOnClick = False
                end
                item
                  Expanded = False
                  FieldName = 'QtCtrc'
                  ReadOnly = True
                  Title.Caption = 'CTRC'
                  Title.Font.Charset = DEFAULT_CHARSET
                  Title.Font.Color = clWindowText
                  Title.Font.Height = -11
                  Title.Font.Name = 'Tahoma'
                  Title.Font.Style = [fsBold]
                  Width = 80
                  Visible = True
                  BrConsulta = 0
                  BrPermissao = []
                  BrValueHalfChecked = False
                  BrSaveOnClick = False
                end
                item
                  Expanded = False
                  FieldName = 'QtDiaVen'
                  ReadOnly = True
                  Title.Caption = 'Dias p/ Vencto'
                  Title.Font.Charset = DEFAULT_CHARSET
                  Title.Font.Color = clWindowText
                  Title.Font.Height = -11
                  Title.Font.Name = 'Tahoma'
                  Title.Font.Style = [fsBold]
                  Width = 86
                  Visible = True
                  BrConsulta = 0
                  BrPermissao = []
                  BrValueHalfChecked = False
                  BrSaveOnClick = False
                end
                item
                  Expanded = False
                  FieldName = 'DtPreEnt'
                  Title.Caption = 'Menor Vencto'
                  Title.Font.Charset = DEFAULT_CHARSET
                  Title.Font.Color = clWindowText
                  Title.Font.Height = -11
                  Title.Font.Name = 'Tahoma'
                  Title.Font.Style = [fsBold]
                  Width = 91
                  Visible = True
                  BrConsulta = 0
                  BrPermissao = []
                  BrValueHalfChecked = False
                  BrSaveOnClick = False
                end
                item
                  Expanded = False
                  FieldName = 'NrPeso'
                  ReadOnly = True
                  Title.Caption = 'Peso'
                  Title.Font.Charset = DEFAULT_CHARSET
                  Title.Font.Color = clWindowText
                  Title.Font.Height = -11
                  Title.Font.Name = 'Tahoma'
                  Title.Font.Style = [fsBold]
                  Width = 93
                  Visible = True
                  BrConsulta = 0
                  BrPermissao = []
                  BrValueHalfChecked = False
                  BrSaveOnClick = False
                end
                item
                  Expanded = False
                  FieldName = 'VrMercad'
                  ReadOnly = True
                  Title.Caption = 'Vr. Mercadoria'
                  Title.Font.Charset = DEFAULT_CHARSET
                  Title.Font.Color = clWindowText
                  Title.Font.Height = -11
                  Title.Font.Name = 'Tahoma'
                  Title.Font.Style = [fsBold]
                  Width = 95
                  Visible = True
                  BrConsulta = 0
                  BrPermissao = []
                  BrValueHalfChecked = False
                  BrSaveOnClick = False
                end
                item
                  Expanded = False
                  FieldName = 'CdRota'
                  Title.Caption = 'Rota'
                  Title.Font.Charset = DEFAULT_CHARSET
                  Title.Font.Color = clWindowText
                  Title.Font.Height = -11
                  Title.Font.Name = 'Tahoma'
                  Title.Font.Style = [fsBold]
                  Width = 49
                  Visible = True
                  BrConsulta = 0
                  BrPermissao = []
                  BrValueHalfChecked = False
                  BrSaveOnClick = False
                end
                item
                  Expanded = False
                  FieldName = 'DsPraca'
                  Title.Caption = 'Praca'
                  Title.Font.Charset = DEFAULT_CHARSET
                  Title.Font.Color = clWindowText
                  Title.Font.Height = -11
                  Title.Font.Name = 'Tahoma'
                  Title.Font.Style = [fsBold]
                  Width = 213
                  Visible = True
                  BrConsulta = 0
                  BrPermissao = []
                  BrValueHalfChecked = False
                  BrSaveOnClick = False
                end>
            end
            object Panel2: TPanel
              Left = 0
              Top = 157
              Width = 942
              Height = 86
              Align = alBottom
              BorderStyle = bsSingle
              Font.Charset = DEFAULT_CHARSET
              Font.Color = clWindowText
              Font.Height = -11
              Font.Name = 'MS Sans Serif'
              Font.Style = [fsBold]
              ParentFont = False
              TabOrder = 1
              DesignSize = (
                938
                82)
              object BrvBitBtn4: TBrvBitBtn
                Left = 587
                Top = 52
                Width = 113
                Height = 25
                Anchors = [akTop, akRight]
                Caption = 'Voltar'
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
                OnClick = BrvBitBtn4Click
                BrTipoBotao = BrBtnPrimei
                ExplicitLeft = 595
              end
              object BrvBitBtn5: TBrvBitBtn
                Left = 700
                Top = 52
                Width = 113
                Height = 25
                Anchors = [akTop, akRight]
                Caption = 'Detalhar'
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
                OnClick = BrvBitBtn5Click
                BrTipoBotao = BrBtnPesqui
                ExplicitLeft = 708
              end
              object BrvBitBtn12: TBrvBitBtn
                Left = 813
                Top = 52
                Width = 113
                Height = 25
                Anchors = [akTop, akRight]
                Caption = 'Carga'
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
                OnClick = BrvBitBtn9Click
                BrTipoBotao = BrBtnCarga
                ExplicitLeft = 821
              end
              object GroupBox1: TGroupBox
                Left = 6
                Top = 4
                Width = 548
                Height = 36
                Caption = 'Geral:'
                Font.Charset = DEFAULT_CHARSET
                Font.Color = clWindowText
                Font.Height = -11
                Font.Name = 'MS Sans Serif'
                Font.Style = [fsBold]
                ParentFont = False
                TabOrder = 3
                object Label4: TLabel
                  Left = 6
                  Top = 14
                  Width = 34
                  Height = 13
                  Caption = 'CTRC'
                  Font.Charset = DEFAULT_CHARSET
                  Font.Color = clWindowText
                  Font.Height = -11
                  Font.Name = 'MS Sans Serif'
                  Font.Style = [fsBold]
                  ParentFont = False
                end
                object Label7: TLabel
                  Left = 159
                  Top = 14
                  Width = 29
                  Height = 13
                  Caption = 'Peso'
                  Font.Charset = DEFAULT_CHARSET
                  Font.Color = clWindowText
                  Font.Height = -11
                  Font.Name = 'MS Sans Serif'
                  Font.Style = [fsBold]
                  ParentFont = False
                end
                object Label9: TLabel
                  Left = 326
                  Top = 14
                  Width = 90
                  Height = 13
                  Caption = 'Vr. Mercadorias'
                  Font.Charset = DEFAULT_CHARSET
                  Font.Color = clWindowText
                  Font.Height = -11
                  Font.Name = 'MS Sans Serif'
                  Font.Style = [fsBold]
                  ParentFont = False
                end
                object EdtTtCtrCid: TBrvEditNum
                  Left = 52
                  Top = 11
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
                  BrOnBeforeConsulta = EdtCdRotaBrOnBeforeConsulta
                  BrConfigurar.Strings = (
                    'EdtCdRota;CdRota;S;S;'
                    'LblDsPraca;DsPraca;S;N;')
                  BrDicionario = DmSrvApl.BrvDicionario
                  BrQueryCode = 14
                  BrRecordar = False
                end
                object EdtTtPesCid: TBrvEditNum
                  Left = 194
                  Top = 11
                  Width = 120
                  Height = 21
                  TabStop = False
                  BevelInner = bvLowered
                  BevelKind = bkFlat
                  BevelWidth = 2
                  BorderStyle = bsNone
                  ParentColor = True
                  ReadOnly = True
                  TabOrder = 1
                  Text = '0,00'
                  BrAlignment = taRightJustify
                  BrCasasDecimais = 2
                  BrSepararMilhar = True
                  BrAsInteger = 0
                  BrAsFloatSQL = '0.00'
                  BrVisibleButton = False
                  BrFunctionButton = TpConsulta
                  BrOnBeforeConsulta = EdtCdRotaBrOnBeforeConsulta
                  BrConfigurar.Strings = (
                    'EdtCdRota;CdRota;S;S;'
                    'LblDsPraca;DsPraca;S;N;')
                  BrDicionario = DmSrvApl.BrvDicionario
                  BrQueryCode = 14
                  BrRecordar = False
                end
                object EdtTtMerCid: TBrvEditNum
                  Left = 421
                  Top = 11
                  Width = 120
                  Height = 21
                  TabStop = False
                  BevelInner = bvLowered
                  BevelKind = bkFlat
                  BevelWidth = 2
                  BorderStyle = bsNone
                  ParentColor = True
                  ReadOnly = True
                  TabOrder = 2
                  Text = '0,00'
                  BrAlignment = taRightJustify
                  BrCasasDecimais = 2
                  BrSepararMilhar = True
                  BrAsInteger = 0
                  BrAsFloatSQL = '0.00'
                  BrVisibleButton = False
                  BrFunctionButton = TpConsulta
                  BrOnBeforeConsulta = EdtCdRotaBrOnBeforeConsulta
                  BrConfigurar.Strings = (
                    'EdtCdRota;CdRota;S;S;'
                    'LblDsPraca;DsPraca;S;N;')
                  BrDicionario = DmSrvApl.BrvDicionario
                  BrQueryCode = 14
                  BrRecordar = False
                end
              end
              object GroupBox5: TGroupBox
                Left = 6
                Top = 40
                Width = 548
                Height = 36
                Caption = 'Marcados:'
                Font.Charset = DEFAULT_CHARSET
                Font.Color = clWindowText
                Font.Height = -11
                Font.Name = 'MS Sans Serif'
                Font.Style = [fsBold]
                ParentFont = False
                TabOrder = 4
                object Label43: TLabel
                  Left = 6
                  Top = 15
                  Width = 34
                  Height = 13
                  Caption = 'CTRC'
                  Font.Charset = DEFAULT_CHARSET
                  Font.Color = clWindowText
                  Font.Height = -11
                  Font.Name = 'MS Sans Serif'
                  Font.Style = [fsBold]
                  ParentFont = False
                end
                object Label45: TLabel
                  Left = 159
                  Top = 15
                  Width = 29
                  Height = 13
                  Caption = 'Peso'
                  Font.Charset = DEFAULT_CHARSET
                  Font.Color = clWindowText
                  Font.Height = -11
                  Font.Name = 'MS Sans Serif'
                  Font.Style = [fsBold]
                  ParentFont = False
                end
                object Label46: TLabel
                  Left = 326
                  Top = 15
                  Width = 90
                  Height = 13
                  Caption = 'Vr. Mercadorias'
                  Font.Charset = DEFAULT_CHARSET
                  Font.Color = clWindowText
                  Font.Height = -11
                  Font.Name = 'MS Sans Serif'
                  Font.Style = [fsBold]
                  ParentFont = False
                end
                object EdtTtCtCiMa: TBrvEditNum
                  Left = 51
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
                  BrOnBeforeConsulta = EdtCdRotaBrOnBeforeConsulta
                  BrConfigurar.Strings = (
                    'EdtCdRota;CdRota;S;S;'
                    'LblDsPraca;DsPraca;S;N;')
                  BrDicionario = DmSrvApl.BrvDicionario
                  BrQueryCode = 14
                  BrRecordar = False
                end
                object EdtTtPeCiMa: TBrvEditNum
                  Left = 194
                  Top = 12
                  Width = 120
                  Height = 21
                  TabStop = False
                  BevelInner = bvLowered
                  BevelKind = bkFlat
                  BevelWidth = 2
                  BorderStyle = bsNone
                  ParentColor = True
                  ReadOnly = True
                  TabOrder = 1
                  Text = '0,00'
                  BrAlignment = taRightJustify
                  BrCasasDecimais = 2
                  BrSepararMilhar = True
                  BrAsInteger = 0
                  BrAsFloatSQL = '0.00'
                  BrVisibleButton = False
                  BrFunctionButton = TpConsulta
                  BrOnBeforeConsulta = EdtCdRotaBrOnBeforeConsulta
                  BrConfigurar.Strings = (
                    'EdtCdRota;CdRota;S;S;'
                    'LblDsPraca;DsPraca;S;N;')
                  BrDicionario = DmSrvApl.BrvDicionario
                  BrQueryCode = 14
                  BrRecordar = False
                end
                object EdtTtMeCiMa: TBrvEditNum
                  Left = 421
                  Top = 12
                  Width = 120
                  Height = 21
                  TabStop = False
                  BevelInner = bvLowered
                  BevelKind = bkFlat
                  BevelWidth = 2
                  BorderStyle = bsNone
                  ParentColor = True
                  ReadOnly = True
                  TabOrder = 2
                  Text = '0,00'
                  BrAlignment = taRightJustify
                  BrCasasDecimais = 2
                  BrSepararMilhar = True
                  BrAsInteger = 0
                  BrAsFloatSQL = '0.00'
                  BrVisibleButton = False
                  BrFunctionButton = TpConsulta
                  BrOnBeforeConsulta = EdtCdRotaBrOnBeforeConsulta
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
          object TbsCTRC: TTabSheet
            Caption = 'TbsCTRC'
            ImageIndex = 3
            ExplicitLeft = 0
            ExplicitTop = 0
            ExplicitWidth = 0
            ExplicitHeight = 0
            object Panel5: TPanel
              Left = 0
              Top = 157
              Width = 942
              Height = 86
              Align = alBottom
              BorderStyle = bsSingle
              Font.Charset = DEFAULT_CHARSET
              Font.Color = clWindowText
              Font.Height = -11
              Font.Name = 'MS Sans Serif'
              Font.Style = [fsBold]
              ParentFont = False
              TabOrder = 0
              DesignSize = (
                938
                82)
              object BrvBitBtn8: TBrvBitBtn
                Left = 587
                Top = 52
                Width = 113
                Height = 25
                Anchors = [akTop, akRight]
                Caption = 'Voltar'
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
                OnClick = BrvBitBtn8Click
                BrTipoBotao = BrBtnPrimei
                ExplicitLeft = 595
              end
              object BrvBitBtn9: TBrvBitBtn
                Left = 813
                Top = 52
                Width = 113
                Height = 25
                Anchors = [akTop, akRight]
                Caption = 'Carga'
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
                OnClick = BrvBitBtn9Click
                BrTipoBotao = BrBtnCarga
                ExplicitLeft = 821
              end
              object GroupBox8: TGroupBox
                Left = 6
                Top = 4
                Width = 548
                Height = 36
                Caption = 'Geral:'
                Font.Charset = DEFAULT_CHARSET
                Font.Color = clWindowText
                Font.Height = -11
                Font.Name = 'MS Sans Serif'
                Font.Style = [fsBold]
                ParentFont = False
                TabOrder = 2
                object Label6: TLabel
                  Left = 6
                  Top = 14
                  Width = 34
                  Height = 13
                  Caption = 'CTRC'
                  Font.Charset = DEFAULT_CHARSET
                  Font.Color = clWindowText
                  Font.Height = -11
                  Font.Name = 'MS Sans Serif'
                  Font.Style = [fsBold]
                  ParentFont = False
                end
                object Label17: TLabel
                  Left = 159
                  Top = 14
                  Width = 29
                  Height = 13
                  Caption = 'Peso'
                  Font.Charset = DEFAULT_CHARSET
                  Font.Color = clWindowText
                  Font.Height = -11
                  Font.Name = 'MS Sans Serif'
                  Font.Style = [fsBold]
                  ParentFont = False
                end
                object Label18: TLabel
                  Left = 326
                  Top = 14
                  Width = 90
                  Height = 13
                  Caption = 'Vr. Mercadorias'
                  Font.Charset = DEFAULT_CHARSET
                  Font.Color = clWindowText
                  Font.Height = -11
                  Font.Name = 'MS Sans Serif'
                  Font.Style = [fsBold]
                  ParentFont = False
                end
                object EdtTtCtrCtr: TBrvEditNum
                  Left = 51
                  Top = 11
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
                  BrOnBeforeConsulta = EdtCdRotaBrOnBeforeConsulta
                  BrConfigurar.Strings = (
                    'EdtCdRota;CdRota;S;S;'
                    'LblDsPraca;DsPraca;S;N;')
                  BrDicionario = DmSrvApl.BrvDicionario
                  BrQueryCode = 14
                  BrRecordar = False
                end
                object EdtTtPesCtr: TBrvEditNum
                  Left = 194
                  Top = 11
                  Width = 120
                  Height = 21
                  TabStop = False
                  BevelInner = bvLowered
                  BevelKind = bkFlat
                  BevelWidth = 2
                  BorderStyle = bsNone
                  ParentColor = True
                  ReadOnly = True
                  TabOrder = 1
                  Text = '0,00'
                  BrAlignment = taRightJustify
                  BrCasasDecimais = 2
                  BrSepararMilhar = True
                  BrAsInteger = 0
                  BrAsFloatSQL = '0.00'
                  BrVisibleButton = False
                  BrFunctionButton = TpConsulta
                  BrOnBeforeConsulta = EdtCdRotaBrOnBeforeConsulta
                  BrConfigurar.Strings = (
                    'EdtCdRota;CdRota;S;S;'
                    'LblDsPraca;DsPraca;S;N;')
                  BrDicionario = DmSrvApl.BrvDicionario
                  BrQueryCode = 14
                  BrRecordar = False
                end
                object EdtTtMerCtr: TBrvEditNum
                  Left = 421
                  Top = 11
                  Width = 120
                  Height = 21
                  TabStop = False
                  BevelInner = bvLowered
                  BevelKind = bkFlat
                  BevelWidth = 2
                  BorderStyle = bsNone
                  ParentColor = True
                  ReadOnly = True
                  TabOrder = 2
                  Text = '0,00'
                  BrAlignment = taRightJustify
                  BrCasasDecimais = 2
                  BrSepararMilhar = True
                  BrAsInteger = 0
                  BrAsFloatSQL = '0.00'
                  BrVisibleButton = False
                  BrFunctionButton = TpConsulta
                  BrOnBeforeConsulta = EdtCdRotaBrOnBeforeConsulta
                  BrConfigurar.Strings = (
                    'EdtCdRota;CdRota;S;S;'
                    'LblDsPraca;DsPraca;S;N;')
                  BrDicionario = DmSrvApl.BrvDicionario
                  BrQueryCode = 14
                  BrRecordar = False
                end
              end
              object GroupBox9: TGroupBox
                Left = 6
                Top = 40
                Width = 548
                Height = 36
                Caption = 'Marcados:'
                Font.Charset = DEFAULT_CHARSET
                Font.Color = clWindowText
                Font.Height = -11
                Font.Name = 'MS Sans Serif'
                Font.Style = [fsBold]
                ParentFont = False
                TabOrder = 3
                object Label49: TLabel
                  Left = 6
                  Top = 15
                  Width = 34
                  Height = 13
                  Caption = 'CTRC'
                  Font.Charset = DEFAULT_CHARSET
                  Font.Color = clWindowText
                  Font.Height = -11
                  Font.Name = 'MS Sans Serif'
                  Font.Style = [fsBold]
                  ParentFont = False
                end
                object Label50: TLabel
                  Left = 159
                  Top = 15
                  Width = 29
                  Height = 13
                  Caption = 'Peso'
                  Font.Charset = DEFAULT_CHARSET
                  Font.Color = clWindowText
                  Font.Height = -11
                  Font.Name = 'MS Sans Serif'
                  Font.Style = [fsBold]
                  ParentFont = False
                end
                object Label53: TLabel
                  Left = 326
                  Top = 15
                  Width = 90
                  Height = 13
                  Caption = 'Vr. Mercadorias'
                  Font.Charset = DEFAULT_CHARSET
                  Font.Color = clWindowText
                  Font.Height = -11
                  Font.Name = 'MS Sans Serif'
                  Font.Style = [fsBold]
                  ParentFont = False
                end
                object EdtTtCtCtMa: TBrvEditNum
                  Left = 51
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
                  BrOnBeforeConsulta = EdtCdRotaBrOnBeforeConsulta
                  BrConfigurar.Strings = (
                    'EdtCdRota;CdRota;S;S;'
                    'LblDsPraca;DsPraca;S;N;')
                  BrDicionario = DmSrvApl.BrvDicionario
                  BrQueryCode = 14
                  BrRecordar = False
                end
                object EdtTtPeCtMa: TBrvEditNum
                  Left = 194
                  Top = 12
                  Width = 120
                  Height = 21
                  TabStop = False
                  BevelInner = bvLowered
                  BevelKind = bkFlat
                  BevelWidth = 2
                  BorderStyle = bsNone
                  ParentColor = True
                  ReadOnly = True
                  TabOrder = 1
                  Text = '0,00'
                  BrAlignment = taRightJustify
                  BrCasasDecimais = 2
                  BrSepararMilhar = True
                  BrAsInteger = 0
                  BrAsFloatSQL = '0.00'
                  BrVisibleButton = False
                  BrFunctionButton = TpConsulta
                  BrOnBeforeConsulta = EdtCdRotaBrOnBeforeConsulta
                  BrConfigurar.Strings = (
                    'EdtCdRota;CdRota;S;S;'
                    'LblDsPraca;DsPraca;S;N;')
                  BrDicionario = DmSrvApl.BrvDicionario
                  BrQueryCode = 14
                  BrRecordar = False
                end
                object EdtTtMeCtMa: TBrvEditNum
                  Left = 421
                  Top = 12
                  Width = 120
                  Height = 21
                  TabStop = False
                  BevelInner = bvLowered
                  BevelKind = bkFlat
                  BevelWidth = 2
                  BorderStyle = bsNone
                  ParentColor = True
                  ReadOnly = True
                  TabOrder = 2
                  Text = '0,00'
                  BrAlignment = taRightJustify
                  BrCasasDecimais = 2
                  BrSepararMilhar = True
                  BrAsInteger = 0
                  BrAsFloatSQL = '0.00'
                  BrVisibleButton = False
                  BrFunctionButton = TpConsulta
                  BrOnBeforeConsulta = EdtCdRotaBrOnBeforeConsulta
                  BrConfigurar.Strings = (
                    'EdtCdRota;CdRota;S;S;'
                    'LblDsPraca;DsPraca;S;N;')
                  BrDicionario = DmSrvApl.BrvDicionario
                  BrQueryCode = 14
                  BrRecordar = False
                end
              end
            end
            object DbgCtrc: TBrvDbGrid
              Left = 0
              Top = 0
              Width = 942
              Height = 157
              BrShowMemo = True
              BrReadOnlyStyle = []
              BrReadOnlyColor = clBlack
              Align = alClient
              DataSource = DtsCTRC
              Options = [dgEditing, dgTitles, dgIndicator, dgColumnResize, dgColLines, dgRowLines, dgTabs, dgCancelOnExit]
              PopupMenu = PopCtrc
              TabOrder = 1
              TitleFont.Charset = DEFAULT_CHARSET
              TitleFont.Color = clWindowText
              TitleFont.Height = -11
              TitleFont.Name = 'Tahoma'
              TitleFont.Style = []
              OnCellClick = DbgCtrcCellClick
              BrDicionario = DmSrvApl.BrvDicionario
              BrDrawColumn.Strings = (
                'N'#227'o remova essas duas linhas de formata'#231#227'o:'
                'CampoTabela;Operador;ValorComparativo;Cor;'
                'StVencim;=;3;clRed;'
                'StVencim;=;1;clYellow;'
                'StVencim;=;2;clBlue;')
              Columns = <
                item
                  ButtonStyle = cbsNone
                  Expanded = False
                  FieldName = 'SnMarcad'
                  Title.Alignment = taCenter
                  Title.Caption = 'R'
                  Title.Font.Charset = DEFAULT_CHARSET
                  Title.Font.Color = clWindowText
                  Title.Font.Height = -11
                  Title.Font.Name = 'Wingdings 2'
                  Title.Font.Style = [fsBold]
                  Width = 20
                  Visible = True
                  BrConsulta = 0
                  BrPermissao = []
                  BrValueChecked = 'S'
                  BrValueUnchecked = 'N'
                  BrValueHalfChecked = False
                  BrSaveOnClick = True
                end
                item
                  Expanded = False
                  FieldName = 'CdClient'
                  ReadOnly = True
                  Title.Caption = 'Tomador'
                  Title.Font.Charset = DEFAULT_CHARSET
                  Title.Font.Color = clWindowText
                  Title.Font.Height = -11
                  Title.Font.Name = 'Tahoma'
                  Title.Font.Style = [fsBold]
                  Width = 54
                  Visible = True
                  BrConsulta = 0
                  BrPermissao = []
                  BrValueHalfChecked = False
                  BrSaveOnClick = False
                end
                item
                  Expanded = False
                  FieldName = 'RsClient'
                  ReadOnly = True
                  Title.Caption = 'Raz'#227'o social do tomador'
                  Title.Font.Charset = DEFAULT_CHARSET
                  Title.Font.Color = clWindowText
                  Title.Font.Height = -11
                  Title.Font.Name = 'Tahoma'
                  Title.Font.Style = [fsBold]
                  Width = 291
                  Visible = True
                  BrConsulta = 0
                  BrPermissao = []
                  BrValueHalfChecked = False
                  BrSaveOnClick = False
                end
                item
                  Expanded = False
                  FieldName = 'CdRota'
                  Title.Caption = 'Rota'
                  Title.Font.Charset = DEFAULT_CHARSET
                  Title.Font.Color = clWindowText
                  Title.Font.Height = -11
                  Title.Font.Name = 'Tahoma'
                  Title.Font.Style = [fsBold]
                  Width = 39
                  Visible = True
                  BrConsulta = 0
                  BrPermissao = []
                  BrValueHalfChecked = False
                  BrSaveOnClick = False
                end
                item
                  Expanded = False
                  FieldName = 'EdDestin'
                  Title.Caption = 'Endere'#231'o do Destinat'#225'rio/Entrega'
                  Title.Font.Charset = DEFAULT_CHARSET
                  Title.Font.Color = clWindowText
                  Title.Font.Height = -11
                  Title.Font.Name = 'Tahoma'
                  Title.Font.Style = [fsBold]
                  Width = 216
                  Visible = True
                  BrConsulta = 0
                  BrPermissao = []
                  BrValueHalfChecked = False
                  BrSaveOnClick = False
                end
                item
                  Expanded = False
                  FieldName = 'NmCidDes'
                  ReadOnly = True
                  Title.Caption = 'Cidade'
                  Title.Font.Charset = DEFAULT_CHARSET
                  Title.Font.Color = clWindowText
                  Title.Font.Height = -11
                  Title.Font.Name = 'Tahoma'
                  Title.Font.Style = [fsBold]
                  Width = 183
                  Visible = True
                  BrConsulta = 0
                  BrPermissao = []
                  BrValueHalfChecked = False
                  BrSaveOnClick = False
                end
                item
                  Expanded = False
                  FieldName = 'CdEstDes'
                  ReadOnly = True
                  Title.Caption = 'UF'
                  Title.Font.Charset = DEFAULT_CHARSET
                  Title.Font.Color = clWindowText
                  Title.Font.Height = -11
                  Title.Font.Name = 'Tahoma'
                  Title.Font.Style = [fsBold]
                  Width = 23
                  Visible = True
                  BrConsulta = 0
                  BrPermissao = []
                  BrValueHalfChecked = False
                  BrSaveOnClick = False
                end
                item
                  Expanded = False
                  FieldName = 'CdEmpres'
                  Title.Caption = 'Empresa'
                  Title.Font.Charset = DEFAULT_CHARSET
                  Title.Font.Color = clWindowText
                  Title.Font.Height = -11
                  Title.Font.Name = 'Tahoma'
                  Title.Font.Style = [fsBold]
                  Visible = True
                  BrConsulta = 0
                  BrPermissao = []
                  BrValueHalfChecked = False
                  BrSaveOnClick = False
                end
                item
                  Expanded = False
                  FieldName = 'CdCtrc'
                  ReadOnly = True
                  Title.Caption = 'CTRC'
                  Title.Font.Charset = DEFAULT_CHARSET
                  Title.Font.Color = clWindowText
                  Title.Font.Height = -11
                  Title.Font.Name = 'Tahoma'
                  Title.Font.Style = [fsBold]
                  Width = 50
                  Visible = True
                  BrConsulta = 0
                  BrPermissao = []
                  BrValueHalfChecked = False
                  BrSaveOnClick = False
                end
                item
                  Expanded = False
                  FieldName = 'NrNota'
                  Title.Caption = 'Nota(s)'
                  Title.Font.Charset = DEFAULT_CHARSET
                  Title.Font.Color = clWindowText
                  Title.Font.Height = -11
                  Title.Font.Name = 'Tahoma'
                  Title.Font.Style = [fsBold]
                  Width = 92
                  Visible = True
                  BrConsulta = 0
                  BrPermissao = []
                  BrValueHalfChecked = False
                  BrSaveOnClick = False
                end
                item
                  Expanded = False
                  FieldName = 'DtPreEnt'
                  Title.Caption = 'Dt. Entrega'
                  Title.Font.Charset = DEFAULT_CHARSET
                  Title.Font.Color = clWindowText
                  Title.Font.Height = -11
                  Title.Font.Name = 'Tahoma'
                  Title.Font.Style = [fsBold]
                  Width = 72
                  Visible = True
                  BrConsulta = 0
                  BrPermissao = []
                  BrValueHalfChecked = False
                  BrSaveOnClick = False
                end
                item
                  Expanded = False
                  FieldName = 'QtDiaVen'
                  Title.Caption = 'Dias p/ Vencto'
                  Title.Font.Charset = DEFAULT_CHARSET
                  Title.Font.Color = clWindowText
                  Title.Font.Height = -11
                  Title.Font.Name = 'Tahoma'
                  Title.Font.Style = [fsBold]
                  Width = 88
                  Visible = True
                  BrConsulta = 0
                  BrPermissao = []
                  BrValueHalfChecked = False
                  BrSaveOnClick = False
                end
                item
                  Expanded = False
                  FieldName = 'NrPeso'
                  ReadOnly = True
                  Title.Caption = 'Peso'
                  Title.Font.Charset = DEFAULT_CHARSET
                  Title.Font.Color = clWindowText
                  Title.Font.Height = -11
                  Title.Font.Name = 'Tahoma'
                  Title.Font.Style = [fsBold]
                  Width = 85
                  Visible = True
                  BrConsulta = 0
                  BrPermissao = []
                  BrValueHalfChecked = False
                  BrSaveOnClick = False
                end
                item
                  Expanded = False
                  FieldName = 'VrMercad'
                  ReadOnly = True
                  Title.Caption = 'Vr. Mercadoria'
                  Title.Font.Charset = DEFAULT_CHARSET
                  Title.Font.Color = clWindowText
                  Title.Font.Height = -11
                  Title.Font.Name = 'Tahoma'
                  Title.Font.Style = [fsBold]
                  Width = 95
                  Visible = True
                  BrConsulta = 0
                  BrPermissao = []
                  BrValueHalfChecked = False
                  BrSaveOnClick = False
                end
                item
                  Expanded = False
                  FieldName = 'QtMetCub'
                  Title.Caption = 'Volume'
                  Title.Font.Charset = DEFAULT_CHARSET
                  Title.Font.Color = clWindowText
                  Title.Font.Height = -11
                  Title.Font.Name = 'Tahoma'
                  Title.Font.Style = [fsBold]
                  Width = 76
                  Visible = True
                  BrConsulta = 0
                  BrPermissao = []
                  BrValueHalfChecked = False
                  BrSaveOnClick = False
                end
                item
                  Expanded = False
                  FieldName = 'NrPesLot'
                  Title.Caption = 'P. Lota'#231#227'o'
                  Title.Font.Charset = DEFAULT_CHARSET
                  Title.Font.Color = clWindowText
                  Title.Font.Height = -11
                  Title.Font.Name = 'Tahoma'
                  Title.Font.Style = [fsBold]
                  Width = 86
                  Visible = True
                  BrConsulta = 0
                  BrPermissao = []
                  BrValueHalfChecked = False
                  BrSaveOnClick = False
                end
                item
                  Expanded = False
                  FieldName = 'CdDestin'
                  ReadOnly = True
                  Title.Caption = 'Destinat'#225'rio'
                  Title.Font.Charset = DEFAULT_CHARSET
                  Title.Font.Color = clWindowText
                  Title.Font.Height = -11
                  Title.Font.Name = 'Tahoma'
                  Title.Font.Style = [fsBold]
                  Width = 76
                  Visible = True
                  BrConsulta = 0
                  BrPermissao = []
                  BrValueHalfChecked = False
                  BrSaveOnClick = False
                end
                item
                  Expanded = False
                  FieldName = 'RsDestin'
                  ReadOnly = True
                  Title.Caption = 'Raz'#227'o social do destinat'#225'rio'
                  Title.Font.Charset = DEFAULT_CHARSET
                  Title.Font.Color = clWindowText
                  Title.Font.Height = -11
                  Title.Font.Name = 'Tahoma'
                  Title.Font.Style = [fsBold]
                  Width = 345
                  Visible = True
                  BrConsulta = 0
                  BrPermissao = []
                  BrValueHalfChecked = False
                  BrSaveOnClick = False
                end
                item
                  Expanded = False
                  FieldName = 'DsPraca'
                  Title.Caption = 'Pra'#231'a principal da rota'
                  Title.Font.Charset = DEFAULT_CHARSET
                  Title.Font.Color = clWindowText
                  Title.Font.Height = -11
                  Title.Font.Name = 'Tahoma'
                  Title.Font.Style = [fsBold]
                  Width = 213
                  Visible = True
                  BrConsulta = 0
                  BrPermissao = []
                  BrValueHalfChecked = False
                  BrSaveOnClick = False
                end>
            end
          end
        end
        object Panel26: TPanel
          Left = 0
          Top = 155
          Width = 950
          Height = 24
          Align = alTop
          BorderStyle = bsSingle
          Font.Charset = DEFAULT_CHARSET
          Font.Color = clWindowText
          Font.Height = -11
          Font.Name = 'Tahoma'
          Font.Style = [fsBold]
          ParentFont = False
          TabOrder = 2
          object Label64: TLabel
            Left = 20
            Top = 4
            Width = 68
            Height = 13
            Caption = 'N'#227'o vencido'
          end
          object Label66: TLabel
            Left = 253
            Top = 4
            Width = 50
            Height = 13
            Caption = 'Vencidos'
          end
          object Label65: TLabel
            Left = 481
            Top = 4
            Width = 109
            Height = 13
            Caption = 'Entrega combinada'
          end
          object Label80: TLabel
            Left = 715
            Top = 4
            Width = 166
            Height = 13
            Caption = 'Entrega combinada e vencida'
          end
          object Panel8: TPanel
            Left = 3
            Top = 3
            Width = 15
            Height = 15
            Color = clWindow
            ParentBackground = False
            TabOrder = 0
          end
          object Panel11: TPanel
            Left = 232
            Top = 3
            Width = 15
            Height = 15
            Color = clYellow
            ParentBackground = False
            TabOrder = 1
          end
          object Panel9: TPanel
            Left = 460
            Top = 3
            Width = 15
            Height = 15
            Color = clBlue
            ParentBackground = False
            TabOrder = 2
          end
          object Panel27: TPanel
            Left = 694
            Top = 3
            Width = 15
            Height = 15
            Color = clRed
            ParentBackground = False
            TabOrder = 3
          end
        end
      end
      object TbsCarga: TTabSheet
        Caption = 'TbsCarga'
        ImageIndex = 2
        ExplicitLeft = 0
        ExplicitTop = 0
        ExplicitWidth = 0
        ExplicitHeight = 0
        object Panel6: TPanel
          Left = 0
          Top = 416
          Width = 950
          Height = 34
          Align = alBottom
          BorderStyle = bsSingle
          Font.Charset = DEFAULT_CHARSET
          Font.Color = clWindowText
          Font.Height = -11
          Font.Name = 'MS Sans Serif'
          Font.Style = [fsBold]
          ParentFont = False
          TabOrder = 0
          DesignSize = (
            946
            30)
          object BrvBitBtn10: TBrvBitBtn
            Left = 551
            Top = 2
            Width = 113
            Height = 25
            Anchors = [akTop, akRight]
            Caption = 'Voltar'
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
            OnClick = BrvBitBtn10Click
            BrTipoBotao = BrBtnPrimei
            ExplicitLeft = 559
          end
          object GroupBox4: TGroupBox
            Left = 1
            Top = 1
            Width = 477
            Height = 28
            Align = alLeft
            Caption = 'Totais:'
            Font.Charset = DEFAULT_CHARSET
            Font.Color = clWindowText
            Font.Height = -11
            Font.Name = 'MS Sans Serif'
            Font.Style = [fsBold]
            ParentFont = False
            TabOrder = 1
            object Label38: TLabel
              Left = 294
              Top = 11
              Width = 64
              Height = 13
              Caption = 'Mercadoria'
              Font.Charset = DEFAULT_CHARSET
              Font.Color = clWindowText
              Font.Height = -11
              Font.Name = 'MS Sans Serif'
              Font.Style = [fsBold]
              ParentFont = False
            end
            object LblTtMerCar: TLabel
              Left = 381
              Top = 11
              Width = 90
              Height = 13
              Alignment = taRightJustify
              AutoSize = False
              Caption = '0,00'
              Font.Charset = DEFAULT_CHARSET
              Font.Color = clMaroon
              Font.Height = -11
              Font.Name = 'MS Sans Serif'
              Font.Style = [fsBold]
              ParentFont = False
            end
            object LblTtPesCar: TLabel
              Left = 199
              Top = 11
              Width = 90
              Height = 13
              Alignment = taRightJustify
              AutoSize = False
              Caption = '0,00'
              Font.Charset = DEFAULT_CHARSET
              Font.Color = clMaroon
              Font.Height = -11
              Font.Name = 'MS Sans Serif'
              Font.Style = [fsBold]
              ParentFont = False
            end
            object Label41: TLabel
              Left = 166
              Top = 11
              Width = 29
              Height = 13
              Caption = 'Peso'
              Font.Charset = DEFAULT_CHARSET
              Font.Color = clWindowText
              Font.Height = -11
              Font.Name = 'MS Sans Serif'
              Font.Style = [fsBold]
              ParentFont = False
            end
            object Label42: TLabel
              Left = 6
              Top = 12
              Width = 34
              Height = 13
              Caption = 'CTRC'
              Font.Charset = DEFAULT_CHARSET
              Font.Color = clWindowText
              Font.Height = -11
              Font.Name = 'MS Sans Serif'
              Font.Style = [fsBold]
              ParentFont = False
            end
            object LblTtCtrCar: TLabel
              Left = 39
              Top = 12
              Width = 90
              Height = 13
              Alignment = taRightJustify
              AutoSize = False
              Caption = '0,00'
              Font.Charset = DEFAULT_CHARSET
              Font.Color = clMaroon
              Font.Height = -11
              Font.Name = 'MS Sans Serif'
              Font.Style = [fsBold]
              ParentFont = False
            end
          end
          object BrvBitBtn14: TBrvBitBtn
            Left = 664
            Top = 2
            Width = 113
            Height = 25
            Anchors = [akTop, akRight]
            Caption = 'Salvar'
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
            OnClick = BrvBitBtn14Click
            BrTipoBotao = BrBtnSalvar
            ExplicitLeft = 672
          end
          object BrvBitBtn15: TBrvBitBtn
            Left = 777
            Top = 2
            Width = 113
            Height = 25
            Anchors = [akTop, akRight]
            Caption = 'Finalizar'
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
            OnClick = BrvBitBtn15Click
            BrTipoBotao = BrBtnCarga
            ExplicitLeft = 785
          end
        end
        object Panel7: TPanel
          Left = 0
          Top = 0
          Width = 950
          Height = 34
          Align = alTop
          BorderStyle = bsSingle
          Font.Charset = DEFAULT_CHARSET
          Font.Color = clWindowText
          Font.Height = -11
          Font.Name = 'MS Sans Serif'
          Font.Style = [fsBold]
          ParentFont = False
          TabOrder = 1
          object Label10: TLabel
            Left = 6
            Top = 7
            Width = 33
            Height = 13
            Caption = 'Carga'
            Font.Charset = DEFAULT_CHARSET
            Font.Color = clBlack
            Font.Height = -11
            Font.Name = 'Tahoma'
            Font.Style = [fsBold]
            ParentFont = False
          end
          object SbtImprim: TBrvSpeedButton
            Left = 121
            Top = 3
            Width = 25
            Height = 25
            Hint = 'Imprimir'
            Flat = True
            Glyph.Data = {
              56070000424D5607000000000000360400002800000028000000140000000100
              0800000000002003000000000000000000000001000000000000000000000000
              80000080000000808000800000008000800080800000C0C0C000C0DCC000F0CA
              A6000020400000206000002080000020A0000020C0000020E000004000000040
              20000040400000406000004080000040A0000040C0000040E000006000000060
              20000060400000606000006080000060A0000060C0000060E000008000000080
              20000080400000806000008080000080A0000080C0000080E00000A0000000A0
              200000A0400000A0600000A0800000A0A00000A0C00000A0E00000C0000000C0
              200000C0400000C0600000C0800000C0A00000C0C00000C0E00000E0000000E0
              200000E0400000E0600000E0800000E0A00000E0C00000E0E000400000004000
              20004000400040006000400080004000A0004000C0004000E000402000004020
              20004020400040206000402080004020A0004020C0004020E000404000004040
              20004040400040406000404080004040A0004040C0004040E000406000004060
              20004060400040606000406080004060A0004060C0004060E000408000004080
              20004080400040806000408080004080A0004080C0004080E00040A0000040A0
              200040A0400040A0600040A0800040A0A00040A0C00040A0E00040C0000040C0
              200040C0400040C0600040C0800040C0A00040C0C00040C0E00040E0000040E0
              200040E0400040E0600040E0800040E0A00040E0C00040E0E000800000008000
              20008000400080006000800080008000A0008000C0008000E000802000008020
              20008020400080206000802080008020A0008020C0008020E000804000008040
              20008040400080406000804080008040A0008040C0008040E000806000008060
              20008060400080606000806080008060A0008060C0008060E000808000008080
              20008080400080806000808080008080A0008080C0008080E00080A0000080A0
              200080A0400080A0600080A0800080A0A00080A0C00080A0E00080C0000080C0
              200080C0400080C0600080C0800080C0A00080C0C00080C0E00080E0000080E0
              200080E0400080E0600080E0800080E0A00080E0C00080E0E000C0000000C000
              2000C0004000C0006000C0008000C000A000C000C000C000E000C0200000C020
              2000C0204000C0206000C0208000C020A000C020C000C020E000C0400000C040
              2000C0404000C0406000C0408000C040A000C040C000C040E000C0600000C060
              2000C0604000C0606000C0608000C060A000C060C000C060E000C0800000C080
              2000C0804000C0806000C0808000C080A000C080C000C080E000C0A00000C0A0
              2000C0A04000C0A06000C0A08000C0A0A000C0A0C000C0A0E000C0C00000C0C0
              2000C0C04000C0C06000C0C08000C0C0A000F0FBFF00A4A0A000808080000000
              FF0000FF000000FFFF00FF000000FF00FF00FFFF0000FFFFFF00646464646464
              6464646464646464646464646464646464646464646464646464646464646464
              6464646464646464646464646464646464646464646464646464646464646464
              6464646464646464646464646464646464646464646464646464646464646464
              646464FFFFFFFFFFFFFFFFFFFFFF646464646464646400000000000000000000
              00646464646464646464A4A4A4A4A4A4A4A4A4A4A408FF646464646464000707
              0707070707070700070064646464646464A4FFFFFFFFFFFFFFFFFFA4FFA408FF
              646464640000000000000000000000000007006464646464A4A4A4A4A4A4A4A4
              A4A4A4A4A4FFA4FF6464646400070707070707FBFBFB07070000006464646464
              A4FF08080808080808080808A4A4A4FF6464646400070707070707A4A4A40707
              0007006464646464A4FFFFFFFFFFFFFFFFFFFFFFA4FFA408FF64646400000000
              00000000000000000007070064646464A4A4A4A4A4A4A4A4A4A4A4A4A408FFA4
              FF6464640007070707070707070707000700070064646464A408FFFFFFFFFFFF
              FFFFFFA408A408A4FF6464646400000000000000000000070007000064646464
              64A4A4A4A4A4A4A4A4A4A408A408A4A4FF646464646400FFFFFFFFFFFFFFFF00
              07000700646464646464A408FF08FFFFFFFFFFA4FFA4FFA46464646464646400
              FF0000000000FF000000006464646464646464A4FFA4A4A4A4A408A4A4A4A464
              6464646464646400FFFFFFFFFFFFFFFF0064646464646464646464A408FF08FF
              FFFFFFFFA4FF6464646464646464646400FF0000000000FF0064646464646464
              64646464A4FFA4A4A4A4A408A4086464646464646464646400FFFFFFFFFFFFFF
              FF0064646464646464646464A408FFFFFFFFFFFFFFA464646464646464646464
              640000000000000000006464646464646464646464A4A4A4A4A4A4A4A4A46464
              6464646464646464646464646464646464646464646464646464646464646464
              6464646464646464646464646464646464646464646464646464646464646464
              6464646464646464646464646464646464646464646464646464646464646464
              6464646464646464646464646464646464646464646464646464}
            NumGlyphs = 2
            ParentShowHint = False
            ShowHint = True
            Visible = False
            OnClick = SbtImprimClick
            BrTipoBotao = BrBtnImprim
          end
          object EdtCdCarga: TBrvEditNum
            Left = 50
            Top = 4
            Width = 66
            Height = 21
            TabStop = False
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
            BrDicionario = DmSrvApl.BrvDicionario
            BrQueryCode = 0
            BrRecordar = False
          end
        end
        object PgcCarga: TPageControl
          Left = 0
          Top = 34
          Width = 950
          Height = 159
          ActivePage = TbsDadBas
          Align = alTop
          TabOrder = 2
          object TbsDadBas: TTabSheet
            Caption = 'Dados b'#225'sicos'
            ExplicitLeft = 0
            ExplicitTop = 0
            ExplicitWidth = 0
            ExplicitHeight = 0
            object Label16: TLabel
              Left = 8
              Top = 5
              Width = 94
              Height = 13
              Caption = 'Descri'#231#227'o carga'
              Font.Charset = DEFAULT_CHARSET
              Font.Color = clBlue
              Font.Height = -11
              Font.Name = 'MS Sans Serif'
              Font.Style = [fsBold]
              ParentFont = False
            end
            object Label19: TLabel
              Left = 560
              Top = 5
              Width = 39
              Height = 13
              Caption = 'Malote'
              Font.Charset = DEFAULT_CHARSET
              Font.Color = clBlue
              Font.Height = -11
              Font.Name = 'MS Sans Serif'
              Font.Style = [fsBold]
              ParentFont = False
            end
            object Label20: TLabel
              Left = 8
              Top = 34
              Width = 92
              Height = 13
              Caption = 'Tipo do Ve'#237'culo'
              Font.Charset = DEFAULT_CHARSET
              Font.Color = clBlack
              Font.Height = -11
              Font.Name = 'MS Sans Serif'
              Font.Style = [fsBold]
              ParentFont = False
            end
            object Label23: TLabel
              Left = 560
              Top = 32
              Width = 29
              Height = 13
              Caption = 'Peso'
              Font.Charset = DEFAULT_CHARSET
              Font.Color = clBlack
              Font.Height = -11
              Font.Name = 'MS Sans Serif'
              Font.Style = [fsBold]
              ParentFont = False
            end
            object Label25: TLabel
              Left = 732
              Top = 34
              Width = 42
              Height = 13
              Caption = 'Volume'
              Font.Charset = DEFAULT_CHARSET
              Font.Color = clBlack
              Font.Height = -11
              Font.Name = 'MS Sans Serif'
              Font.Style = [fsBold]
              ParentFont = False
            end
            object Label28: TLabel
              Left = 8
              Top = 63
              Width = 54
              Height = 13
              Caption = 'Previs'#227'o:'
              Font.Charset = DEFAULT_CHARSET
              Font.Color = clBlack
              Font.Height = -11
              Font.Name = 'MS Sans Serif'
              Font.Style = [fsBold, fsUnderline]
              ParentFont = False
            end
            object Label29: TLabel
              Left = 193
              Top = 83
              Width = 90
              Height = 13
              Caption = 'Data de retorno'
              Font.Charset = DEFAULT_CHARSET
              Font.Color = clBlack
              Font.Height = -11
              Font.Name = 'MS Sans Serif'
              Font.Style = [fsBold]
              ParentFont = False
            end
            object Label32: TLabel
              Left = 8
              Top = 83
              Width = 84
              Height = 13
              Caption = 'Quilometragem'
              Font.Charset = DEFAULT_CHARSET
              Font.Color = clBlack
              Font.Height = -11
              Font.Name = 'MS Sans Serif'
              Font.Style = [fsBold]
              ParentFont = False
            end
            object Label40: TLabel
              Left = 8
              Top = 107
              Width = 94
              Height = 13
              Caption = 'Empresa destino'
              Font.Charset = DEFAULT_CHARSET
              Font.Color = clBlack
              Font.Height = -11
              Font.Name = 'MS Sans Serif'
              Font.Style = [fsBold]
              ParentFont = False
            end
            object Label67: TLabel
              Left = 732
              Top = 5
              Width = 85
              Height = 13
              Caption = 'Peso comiss'#227'o'
              Font.Charset = DEFAULT_CHARSET
              Font.Color = clBlack
              Font.Height = -11
              Font.Name = 'MS Sans Serif'
              Font.Style = [fsBold]
              ParentFont = False
            end
            object LblDsTpVeic: TBrvRetCon
              Left = 177
              Top = 29
              Width = 368
              Height = 21
              TabStop = False
              AutoSize = False
              BevelInner = bvLowered
              BevelKind = bkFlat
              BevelWidth = 2
              BorderStyle = bsNone
              Font.Charset = DEFAULT_CHARSET
              Font.Color = clBlack
              Font.Height = -11
              Font.Name = 'MS Sans Serif'
              Font.Style = [fsBold]
              ParentColor = True
              ParentFont = False
              ReadOnly = True
              TabOrder = 8
            end
            object EdtDsCarga: TEdit
              Left = 108
              Top = 2
              Width = 437
              Height = 21
              CharCase = ecUpperCase
              MaxLength = 20
              TabOrder = 0
            end
            object EdtNrMalote: TBrvEditNum
              Left = 620
              Top = 2
              Width = 97
              Height = 21
              TabOrder = 1
              Text = '0'
              BrAlignment = taRightJustify
              BrCasasDecimais = 0
              BrSepararMilhar = False
              BrAsInteger = 0
              BrAsFloatSQL = '0'
              BrVisibleButton = False
              BrFunctionButton = TpConsulta
              BrDicionario = DmSrvApl.BrvDicionario
              BrQueryCode = 0
              BrRecordar = False
            end
            object EdtTpVeicul: TBrvEditNum
              Left = 108
              Top = 29
              Width = 67
              Height = 21
              TabOrder = 3
              Text = '0'
              BrAlignment = taRightJustify
              BrCasasDecimais = 0
              BrSepararMilhar = False
              BrAsInteger = 0
              BrAsFloatSQL = '0'
              BrVisibleButton = True
              BrFunctionButton = TpConsulta
              BrConfigurar.Strings = (
                'EdtTpVeicul;TpVeicul;S;S;'
                'LblDsTpVeic;DsTpVeic;S;N;'
                'EdtQtCapPes;QtCapPes;S;N;'
                'EdtQtCapVol;QtCapVol;S;N;')
              BrDicionario = DmSrvApl.BrvDicionario
              BrQueryCode = 18
              BrRecordar = False
            end
            object EdtKmPreCar: TBrvEditNum
              Left = 108
              Top = 80
              Width = 66
              Height = 21
              TabOrder = 6
              Text = '0'
              BrAlignment = taRightJustify
              BrCasasDecimais = 0
              BrSepararMilhar = False
              BrAsInteger = 0
              BrAsFloatSQL = '0'
              BrVisibleButton = False
              BrFunctionButton = TpConsulta
              BrDicionario = DmSrvApl.BrvDicionario
              BrQueryCode = 0
              BrRecordar = False
            end
            object EdtDtPreRet: TBrvEditDate
              Left = 306
              Top = 80
              Width = 121
              Height = 21
              EditMask = '99/99/9999;1; '
              MaxLength = 10
              TabOrder = 7
              Text = '  /  /    '
              BrDataValida = False
              BrDataVazia = True
              BrFunctionButton = TVdData
              BrAlignment = taLeftJustify
              BrDicionario = DmSrvApl.BrvDicionario
              BrRecordar = False
            end
            object EdtQtCapPes: TBrvRetCon
              Left = 620
              Top = 29
              Width = 97
              Height = 21
              TabStop = False
              Alignment = taRightJustify
              BevelInner = bvLowered
              BevelKind = bkFlat
              BevelWidth = 2
              BorderStyle = bsNone
              ParentColor = True
              ReadOnly = True
              TabOrder = 4
              Text = '0,00'
            end
            object EdtQtCapVol: TBrvRetCon
              Left = 831
              Top = 29
              Width = 97
              Height = 21
              TabStop = False
              Alignment = taRightJustify
              BevelInner = bvLowered
              BevelKind = bkFlat
              BevelWidth = 2
              BorderStyle = bsNone
              ParentColor = True
              ReadOnly = True
              TabOrder = 5
              Text = '0,00'
            end
            object EdtCdEmpDes: TBrvEditNum
              Left = 108
              Top = 105
              Width = 67
              Height = 21
              TabOrder = 9
              Text = '0'
              BrAlignment = taRightJustify
              BrCasasDecimais = 0
              BrSepararMilhar = False
              BrAsInteger = 0
              BrAsFloatSQL = '0'
              BrVisibleButton = True
              BrFunctionButton = TpConsulta
              BrOnBeforeConsulta = EdtCdEmpresBrOnBeforeConsulta
              BrConfigurar.Strings = (
                'EdtCdEmpDes;CdEmpres;S;S;'
                'LblDsEmpDes;DsEmpres;S;N;')
              BrDicionario = DmSrvApl.BrvDicionario
              BrQueryCode = 12
              BrRecordar = False
            end
            object LblDsEmpDes: TBrvRetCon
              Left = 177
              Top = 105
              Width = 751
              Height = 21
              TabStop = False
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
              TabOrder = 10
            end
            object EdtNrPesCom: TBrvEditNum
              Left = 831
              Top = 3
              Width = 97
              Height = 21
              TabOrder = 2
              Text = '0'
              BrAlignment = taRightJustify
              BrCasasDecimais = 0
              BrSepararMilhar = False
              BrAsInteger = 0
              BrAsFloatSQL = '0'
              BrVisibleButton = False
              BrFunctionButton = TpConsulta
              BrDicionario = DmSrvApl.BrvDicionario
              BrQueryCode = 0
              BrRecordar = False
            end
          end
          object TabSheet2: TTabSheet
            Caption = 'Escolta'
            ImageIndex = 1
            ExplicitLeft = 0
            ExplicitTop = 0
            ExplicitWidth = 0
            ExplicitHeight = 0
            object Label56: TLabel
              Left = 8
              Top = 2
              Width = 26
              Height = 13
              Caption = 'Tipo'
              Font.Charset = DEFAULT_CHARSET
              Font.Color = clBlack
              Font.Height = -11
              Font.Name = 'MS Sans Serif'
              Font.Style = [fsBold]
              ParentFont = False
            end
            object Label58: TLabel
              Left = 8
              Top = 26
              Width = 49
              Height = 13
              Caption = 'Empresa'
              Font.Charset = DEFAULT_CHARSET
              Font.Color = clBlack
              Font.Height = -11
              Font.Name = 'MS Sans Serif'
              Font.Style = [fsBold]
              ParentFont = False
            end
            object LblNmEmpEsc: TBrvRetCon
              Left = 137
              Top = 24
              Width = 310
              Height = 21
              TabStop = False
              AutoSize = False
              BevelInner = bvLowered
              BevelKind = bkFlat
              BevelWidth = 2
              BorderStyle = bsNone
              Font.Charset = DEFAULT_CHARSET
              Font.Color = clBlack
              Font.Height = -11
              Font.Name = 'MS Sans Serif'
              Font.Style = [fsBold]
              ParentColor = True
              ParentFont = False
              ReadOnly = True
              TabOrder = 2
            end
            object EdtCdEmpEsc: TBrvEditNum
              Left = 67
              Top = 24
              Width = 67
              Height = 21
              TabOrder = 1
              Text = '0'
              BrAlignment = taRightJustify
              BrCasasDecimais = 0
              BrSepararMilhar = False
              BrAsInteger = 0
              BrAsFloatSQL = '0'
              BrVisibleButton = True
              BrFunctionButton = TpConsulta
              BrConfigurar.Strings = (
                'EdtCdEmpEsc;CdTitula;S;S;'
                'LblNmEmpEsc;RsTitula;S;N;')
              BrDicionario = DmSrvApl.BrvDicionario
              BrQueryCode = 19
              BrRecordar = False
            end
            object CbxTpEscolt: TBrvComboBox
              Left = 67
              Top = 0
              Width = 145
              Height = 21
              Style = csDropDownList
              TabOrder = 0
            end
          end
          object TabSheet3: TTabSheet
            Caption = 'Ve'#237'culos e motoristas'
            ImageIndex = 2
            ExplicitLeft = 0
            ExplicitTop = 0
            ExplicitWidth = 0
            ExplicitHeight = 0
            object Label44: TLabel
              Left = -1
              Top = 22
              Width = 12
              Height = 13
              Caption = ' 1'
              Font.Charset = DEFAULT_CHARSET
              Font.Color = clBlack
              Font.Height = -11
              Font.Name = 'MS Sans Serif'
              Font.Style = [fsBold]
              ParentFont = False
            end
            object Label47: TLabel
              Left = 4
              Top = 49
              Width = 8
              Height = 13
              Caption = '2'
              Font.Charset = DEFAULT_CHARSET
              Font.Color = clBlack
              Font.Height = -11
              Font.Name = 'MS Sans Serif'
              Font.Style = [fsBold]
              ParentFont = False
            end
            object Label51: TLabel
              Left = 4
              Top = 76
              Width = 8
              Height = 13
              Caption = '3'
              Font.Charset = DEFAULT_CHARSET
              Font.Color = clBlack
              Font.Height = -11
              Font.Name = 'MS Sans Serif'
              Font.Style = [fsBold]
              ParentFont = False
            end
            object Label55: TLabel
              Left = 3
              Top = 103
              Width = 8
              Height = 13
              Caption = '4'
              Font.Charset = DEFAULT_CHARSET
              Font.Color = clBlack
              Font.Height = -11
              Font.Name = 'MS Sans Serif'
              Font.Style = [fsBold]
              ParentFont = False
            end
            object Label68: TLabel
              Left = 18
              Top = 3
              Width = 45
              Height = 13
              Caption = 'Ve'#237'culo'
              Font.Charset = DEFAULT_CHARSET
              Font.Color = clBlack
              Font.Height = -11
              Font.Name = 'MS Sans Serif'
              Font.Style = [fsBold]
              ParentFont = False
            end
            object Label69: TLabel
              Left = 371
              Top = 3
              Width = 57
              Height = 13
              Caption = 'Motorista '
              Font.Charset = DEFAULT_CHARSET
              Font.Color = clBlack
              Font.Height = -11
              Font.Name = 'MS Sans Serif'
              Font.Style = [fsBold]
              ParentFont = False
            end
            object Label70: TLabel
              Left = 730
              Top = 3
              Width = 29
              Height = 13
              Caption = 'Peso'
              Font.Charset = DEFAULT_CHARSET
              Font.Color = clBlack
              Font.Height = -11
              Font.Name = 'MS Sans Serif'
              Font.Style = [fsBold]
              ParentFont = False
            end
            object Label71: TLabel
              Left = 837
              Top = 3
              Width = 42
              Height = 13
              Caption = 'Volume'
              Font.Charset = DEFAULT_CHARSET
              Font.Color = clBlack
              Font.Height = -11
              Font.Name = 'MS Sans Serif'
              Font.Style = [fsBold]
              ParentFont = False
            end
            object LblNmMotor3: TBrvRetCon
              Left = 440
              Top = 100
              Width = 280
              Height = 21
              TabStop = False
              AutoSize = False
              BevelInner = bvLowered
              BevelKind = bkFlat
              BevelWidth = 2
              BorderStyle = bsNone
              Font.Charset = DEFAULT_CHARSET
              Font.Color = clBlack
              Font.Height = -11
              Font.Name = 'MS Sans Serif'
              Font.Style = [fsBold]
              ParentColor = True
              ParentFont = False
              ReadOnly = True
              TabOrder = 15
            end
            object LblDsVeicu3: TBrvRetCon
              Left = 86
              Top = 100
              Width = 275
              Height = 21
              TabStop = False
              AutoSize = False
              BevelInner = bvLowered
              BevelKind = bkFlat
              BevelWidth = 2
              BorderStyle = bsNone
              Font.Charset = DEFAULT_CHARSET
              Font.Color = clBlack
              Font.Height = -11
              Font.Name = 'MS Sans Serif'
              Font.Style = [fsBold]
              ParentColor = True
              ParentFont = False
              ReadOnly = True
              TabOrder = 14
            end
            object LblNmMotor2: TBrvRetCon
              Left = 440
              Top = 73
              Width = 280
              Height = 21
              TabStop = False
              AutoSize = False
              BevelInner = bvLowered
              BevelKind = bkFlat
              BevelWidth = 2
              BorderStyle = bsNone
              Font.Charset = DEFAULT_CHARSET
              Font.Color = clBlack
              Font.Height = -11
              Font.Name = 'MS Sans Serif'
              Font.Style = [fsBold]
              ParentColor = True
              ParentFont = False
              ReadOnly = True
              TabOrder = 13
            end
            object LblDsVeicu2: TBrvRetCon
              Left = 86
              Top = 73
              Width = 275
              Height = 21
              TabStop = False
              AutoSize = False
              BevelInner = bvLowered
              BevelKind = bkFlat
              BevelWidth = 2
              BorderStyle = bsNone
              Font.Charset = DEFAULT_CHARSET
              Font.Color = clBlack
              Font.Height = -11
              Font.Name = 'MS Sans Serif'
              Font.Style = [fsBold]
              ParentColor = True
              ParentFont = False
              ReadOnly = True
              TabOrder = 12
            end
            object LblNmMotor1: TBrvRetCon
              Left = 440
              Top = 46
              Width = 280
              Height = 21
              TabStop = False
              AutoSize = False
              BevelInner = bvLowered
              BevelKind = bkFlat
              BevelWidth = 2
              BorderStyle = bsNone
              Font.Charset = DEFAULT_CHARSET
              Font.Color = clBlack
              Font.Height = -11
              Font.Name = 'MS Sans Serif'
              Font.Style = [fsBold]
              ParentColor = True
              ParentFont = False
              ReadOnly = True
              TabOrder = 11
            end
            object LblDsVeicu1: TBrvRetCon
              Left = 86
              Top = 46
              Width = 275
              Height = 21
              TabStop = False
              AutoSize = False
              BevelInner = bvLowered
              BevelKind = bkFlat
              BevelWidth = 2
              BorderStyle = bsNone
              Font.Charset = DEFAULT_CHARSET
              Font.Color = clBlack
              Font.Height = -11
              Font.Name = 'MS Sans Serif'
              Font.Style = [fsBold]
              ParentColor = True
              ParentFont = False
              ReadOnly = True
              TabOrder = 10
            end
            object LblNmMotori: TBrvRetCon
              Left = 440
              Top = 19
              Width = 280
              Height = 21
              TabStop = False
              AutoSize = False
              BevelInner = bvLowered
              BevelKind = bkFlat
              BevelWidth = 2
              BorderStyle = bsNone
              Font.Charset = DEFAULT_CHARSET
              Font.Color = clBlack
              Font.Height = -11
              Font.Name = 'MS Sans Serif'
              Font.Style = [fsBold]
              ParentColor = True
              ParentFont = False
              ReadOnly = True
              TabOrder = 9
            end
            object LblDsVeicul: TBrvRetCon
              Left = 86
              Top = 19
              Width = 275
              Height = 21
              TabStop = False
              AutoSize = False
              BevelInner = bvLowered
              BevelKind = bkFlat
              BevelWidth = 2
              BorderStyle = bsNone
              Font.Charset = DEFAULT_CHARSET
              Font.Color = clBlack
              Font.Height = -11
              Font.Name = 'MS Sans Serif'
              Font.Style = [fsBold]
              ParentColor = True
              ParentFont = False
              ReadOnly = True
              TabOrder = 8
            end
            object EdtCdMotori: TBrvEditNum
              Left = 371
              Top = 19
              Width = 67
              Height = 21
              TabOrder = 1
              Text = '0'
              BrAlignment = taRightJustify
              BrCasasDecimais = 0
              BrSepararMilhar = False
              BrAsInteger = 0
              BrAsFloatSQL = '0'
              BrVisibleButton = True
              BrFunctionButton = TpConsulta
              BrConfigurar.Strings = (
                'EdtCdMotori;CdMotori;S;S;'
                'LblNmMotori;NmMotori;S;N;')
              BrDicionario = DmSrvApl.BrvDicionario
              BrQueryCode = 17
              BrRecordar = False
            end
            object EdtCdVeicul: TBrvEditNum
              Left = 17
              Top = 19
              Width = 67
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
                'EdtCdVeicul;CdVeicul;S;S;'
                'LblDsVeicul;DsVeicul;S;N;'
                'EdtQtPeso1;QtCapPes;S;N;'
                'EdtQtVolum1;QtCapVol;S;N;')
              BrDicionario = DmSrvApl.BrvDicionario
              BrQueryCode = 20
              BrRecordar = False
            end
            object EdtCdMotor1: TBrvEditNum
              Left = 371
              Top = 46
              Width = 67
              Height = 21
              TabOrder = 3
              Text = '0'
              BrAlignment = taRightJustify
              BrCasasDecimais = 0
              BrSepararMilhar = False
              BrAsInteger = 0
              BrAsFloatSQL = '0'
              BrVisibleButton = True
              BrFunctionButton = TpConsulta
              BrConfigurar.Strings = (
                'EdtCdMotor1;CdMotori;S;S;'
                'LblNmMotor1;NmMotori;S;N;')
              BrDicionario = DmSrvApl.BrvDicionario
              BrQueryCode = 17
              BrRecordar = False
            end
            object EdtCdVeicu1: TBrvEditNum
              Left = 18
              Top = 46
              Width = 67
              Height = 21
              TabOrder = 2
              Text = '0'
              BrAlignment = taRightJustify
              BrCasasDecimais = 0
              BrSepararMilhar = False
              BrAsInteger = 0
              BrAsFloatSQL = '0'
              BrVisibleButton = True
              BrFunctionButton = TpConsulta
              BrConfigurar.Strings = (
                'EdtCdVeicu1;CdVeicul;S;S;'
                'LblDsVeicu1;DsVeicul;S;N;'
                'EdtQtPeso2;QtPeso;S;N;'
                'EdtQtVolum2;QtVolume;S;N;')
              BrDicionario = DmSrvApl.BrvDicionario
              BrQueryCode = 20
              BrRecordar = False
            end
            object EdtCdMotor2: TBrvEditNum
              Left = 371
              Top = 73
              Width = 67
              Height = 21
              TabOrder = 5
              Text = '0'
              BrAlignment = taRightJustify
              BrCasasDecimais = 0
              BrSepararMilhar = False
              BrAsInteger = 0
              BrAsFloatSQL = '0'
              BrVisibleButton = True
              BrFunctionButton = TpConsulta
              BrConfigurar.Strings = (
                'EdtCdMotor2;CdMotori;S;S;'
                'LblNmMotor2;NmMotori;S;N;')
              BrDicionario = DmSrvApl.BrvDicionario
              BrQueryCode = 17
              BrRecordar = False
            end
            object EdtCdVeicu2: TBrvEditNum
              Left = 18
              Top = 73
              Width = 67
              Height = 21
              TabOrder = 4
              Text = '0'
              BrAlignment = taRightJustify
              BrCasasDecimais = 0
              BrSepararMilhar = False
              BrAsInteger = 0
              BrAsFloatSQL = '0'
              BrVisibleButton = True
              BrFunctionButton = TpConsulta
              BrConfigurar.Strings = (
                'EdtCdVeicu2;CdVeicul;S;S;'
                'LblDsVeicu2;DsVeicul;S;N;'
                'EdtQtPeso3;QtPeso;S;N;'
                'EdtQtVolum3;QtVolume;S;N;')
              BrDicionario = DmSrvApl.BrvDicionario
              BrQueryCode = 20
              BrRecordar = False
            end
            object EdtCdMotor3: TBrvEditNum
              Left = 371
              Top = 100
              Width = 67
              Height = 21
              TabOrder = 7
              Text = '0'
              BrAlignment = taRightJustify
              BrCasasDecimais = 0
              BrSepararMilhar = False
              BrAsInteger = 0
              BrAsFloatSQL = '0'
              BrVisibleButton = True
              BrFunctionButton = TpConsulta
              BrConfigurar.Strings = (
                'EdtCdMotor3;CdMotori;S;S;'
                'LblNmMotor3;NmMotori;S;N;')
              BrDicionario = DmSrvApl.BrvDicionario
              BrQueryCode = 17
              BrRecordar = False
            end
            object EdtCdVeicu3: TBrvEditNum
              Left = 17
              Top = 100
              Width = 67
              Height = 21
              TabOrder = 6
              Text = '0'
              BrAlignment = taRightJustify
              BrCasasDecimais = 0
              BrSepararMilhar = False
              BrAsInteger = 0
              BrAsFloatSQL = '0'
              BrVisibleButton = True
              BrFunctionButton = TpConsulta
              BrConfigurar.Strings = (
                'EdtCdVeicu3;CdVeicul;S;S;'
                'LblDsVeicu3;DsVeicul;S;N;'
                'EdtQtPeso4;QtPeso;S;N;'
                'EdtQtVolum4;QtVolume;S;N;')
              BrDicionario = DmSrvApl.BrvDicionario
              BrQueryCode = 20
              BrRecordar = False
            end
            object EdtQtPeso1: TBrvRetCon
              Left = 730
              Top = 19
              Width = 97
              Height = 21
              TabStop = False
              Alignment = taRightJustify
              BevelInner = bvLowered
              BevelKind = bkFlat
              BevelWidth = 2
              BorderStyle = bsNone
              ParentColor = True
              ReadOnly = True
              TabOrder = 16
              Text = '0,00'
            end
            object EdtQtVolum1: TBrvRetCon
              Left = 837
              Top = 19
              Width = 97
              Height = 21
              TabStop = False
              Alignment = taRightJustify
              BevelInner = bvLowered
              BevelKind = bkFlat
              BevelWidth = 2
              BorderStyle = bsNone
              ParentColor = True
              ReadOnly = True
              TabOrder = 17
              Text = '0,00'
            end
            object EdtQtPeso2: TBrvRetCon
              Left = 730
              Top = 46
              Width = 97
              Height = 21
              TabStop = False
              Alignment = taRightJustify
              BevelInner = bvLowered
              BevelKind = bkFlat
              BevelWidth = 2
              BorderStyle = bsNone
              ParentColor = True
              ReadOnly = True
              TabOrder = 18
              Text = '0,00'
            end
            object EdtQtVolum2: TBrvRetCon
              Left = 837
              Top = 46
              Width = 97
              Height = 21
              TabStop = False
              Alignment = taRightJustify
              BevelInner = bvLowered
              BevelKind = bkFlat
              BevelWidth = 2
              BorderStyle = bsNone
              ParentColor = True
              ReadOnly = True
              TabOrder = 19
              Text = '0,00'
            end
            object EdtQtPeso3: TBrvRetCon
              Left = 730
              Top = 73
              Width = 97
              Height = 21
              TabStop = False
              Alignment = taRightJustify
              BevelInner = bvLowered
              BevelKind = bkFlat
              BevelWidth = 2
              BorderStyle = bsNone
              ParentColor = True
              ReadOnly = True
              TabOrder = 20
              Text = '0,00'
            end
            object EdtQtVolum3: TBrvRetCon
              Left = 837
              Top = 73
              Width = 97
              Height = 21
              TabStop = False
              Alignment = taRightJustify
              BevelInner = bvLowered
              BevelKind = bkFlat
              BevelWidth = 2
              BorderStyle = bsNone
              ParentColor = True
              ReadOnly = True
              TabOrder = 21
              Text = '0,00'
            end
            object EdtQtPeso4: TBrvRetCon
              Left = 730
              Top = 100
              Width = 97
              Height = 21
              TabStop = False
              Alignment = taRightJustify
              BevelInner = bvLowered
              BevelKind = bkFlat
              BevelWidth = 2
              BorderStyle = bsNone
              ParentColor = True
              ReadOnly = True
              TabOrder = 22
              Text = '0,00'
            end
            object EdtQtVolum4: TBrvRetCon
              Left = 837
              Top = 100
              Width = 97
              Height = 21
              TabStop = False
              Alignment = taRightJustify
              BevelInner = bvLowered
              BevelKind = bkFlat
              BevelWidth = 2
              BorderStyle = bsNone
              ParentColor = True
              ReadOnly = True
              TabOrder = 23
              Text = '0,00'
            end
          end
        end
        object DbgConhec: TBrvDbGrid
          Left = 0
          Top = 193
          Width = 950
          Height = 223
          BrShowMemo = True
          BrReadOnlyStyle = []
          BrReadOnlyColor = clBlack
          Align = alClient
          DataSource = DtsCarga
          TabOrder = 3
          TitleFont.Charset = DEFAULT_CHARSET
          TitleFont.Color = clWindowText
          TitleFont.Height = -11
          TitleFont.Name = 'Tahoma'
          TitleFont.Style = []
          BrDrawColumn.Strings = (
            'N'#227'o remova essas duas linhas de formata'#231#227'o:'
            'CampoTabela;Operador;ValorComparativo;Cor;')
          Columns = <
            item
              Alignment = taRightJustify
              Expanded = False
              FieldName = 'CdEmpAtu'
              ReadOnly = True
              Title.Caption = 'Empresa'
              Title.Font.Charset = DEFAULT_CHARSET
              Title.Font.Color = clWindowText
              Title.Font.Height = -11
              Title.Font.Name = 'Tahoma'
              Title.Font.Style = [fsBold]
              Width = 52
              Visible = True
              BrConsulta = 0
              BrPermissao = []
              BrValueHalfChecked = False
              BrSaveOnClick = False
            end
            item
              Expanded = False
              FieldName = 'DsModeNf'
              ReadOnly = True
              Title.Caption = 'Modelo'
              Title.Font.Charset = DEFAULT_CHARSET
              Title.Font.Color = clWindowText
              Title.Font.Height = -11
              Title.Font.Name = 'Tahoma'
              Title.Font.Style = [fsBold]
              Width = 45
              Visible = True
              BrConsulta = 0
              BrPermissao = []
              BrValueHalfChecked = False
              BrSaveOnClick = False
            end
            item
              Expanded = False
              FieldName = 'NrSeriNf'
              ReadOnly = True
              Title.Caption = 'S'#233'rie'
              Title.Font.Charset = DEFAULT_CHARSET
              Title.Font.Color = clWindowText
              Title.Font.Height = -11
              Title.Font.Name = 'Tahoma'
              Title.Font.Style = [fsBold]
              Width = 34
              Visible = True
              BrConsulta = 0
              BrPermissao = []
              BrValueHalfChecked = False
              BrSaveOnClick = False
            end
            item
              Alignment = taRightJustify
              ButtonStyle = cbsEllipsis
              Expanded = False
              FieldName = 'CDCTRC'
              ReadOnly = True
              Title.Caption = 'CTRC'
              Title.Font.Charset = DEFAULT_CHARSET
              Title.Font.Color = clWindowText
              Title.Font.Height = -11
              Title.Font.Name = 'Tahoma'
              Title.Font.Style = [fsBold]
              Width = 50
              Visible = True
              BrConsulta = 0
              BrPermissao = []
              BrValueHalfChecked = False
              BrSaveOnClick = False
            end
            item
              Expanded = False
              FieldName = 'DsCidDes'
              ReadOnly = True
              Title.Caption = 'Cidade'
              Title.Font.Charset = DEFAULT_CHARSET
              Title.Font.Color = clWindowText
              Title.Font.Height = -11
              Title.Font.Name = 'Tahoma'
              Title.Font.Style = [fsBold]
              Width = 374
              Visible = True
              BrConsulta = 0
              BrPermissao = []
              BrValueHalfChecked = False
              BrSaveOnClick = False
            end
            item
              Alignment = taRightJustify
              Expanded = False
              FieldName = 'VrTotPre'
              ReadOnly = True
              Title.Caption = 'Vr. CTRC'
              Title.Font.Charset = DEFAULT_CHARSET
              Title.Font.Color = clWindowText
              Title.Font.Height = -11
              Title.Font.Name = 'Tahoma'
              Title.Font.Style = [fsBold]
              Width = 90
              Visible = True
              BrConsulta = 0
              BrPermissao = []
              BrValueHalfChecked = False
              BrSaveOnClick = False
            end
            item
              Alignment = taRightJustify
              Expanded = False
              FieldName = 'NRPESO'
              ReadOnly = True
              Title.Caption = 'Peso'
              Title.Font.Charset = DEFAULT_CHARSET
              Title.Font.Color = clWindowText
              Title.Font.Height = -11
              Title.Font.Name = 'Tahoma'
              Title.Font.Style = [fsBold]
              Width = 90
              Visible = True
              BrConsulta = 0
              BrPermissao = []
              BrValueHalfChecked = False
              BrSaveOnClick = False
            end
            item
              Alignment = taRightJustify
              Expanded = False
              FieldName = 'VRMERCAD'
              ReadOnly = True
              Title.Caption = 'Vr. Mercadoria'
              Title.Font.Charset = DEFAULT_CHARSET
              Title.Font.Color = clWindowText
              Title.Font.Height = -11
              Title.Font.Name = 'Tahoma'
              Title.Font.Style = [fsBold]
              Width = 98
              Visible = True
              BrConsulta = 0
              BrPermissao = []
              BrValueHalfChecked = False
              BrSaveOnClick = False
            end
            item
              Expanded = False
              FieldName = 'NrOrdCar'
              Title.Caption = 'Ordem'
              Title.Font.Charset = DEFAULT_CHARSET
              Title.Font.Color = clWindowText
              Title.Font.Height = -11
              Title.Font.Name = 'Tahoma'
              Title.Font.Style = [fsBold]
              Width = 45
              Visible = True
              BrConsulta = 0
              BrPermissao = []
              BrValueHalfChecked = False
              BrSaveOnClick = False
            end
            item
              Expanded = False
              FieldName = 'VrMaxCar'
              ReadOnly = True
              Title.Alignment = taCenter
              Title.Caption = 'Ap'#243'lice'
              Title.Font.Charset = DEFAULT_CHARSET
              Title.Font.Color = clWindowText
              Title.Font.Height = -11
              Title.Font.Name = 'Tahoma'
              Title.Font.Style = [fsBold]
              Width = 100
              Visible = True
              BrConsulta = 0
              BrPermissao = []
              BrValueHalfChecked = False
              BrSaveOnClick = False
            end>
        end
      end
    end
  end
  object DtsFiltro1: TDataSource
    DataSet = CdsFiltro1
    Left = 584
  end
  object CdsRotas: TClientDataSet
    Aggregates = <>
    Params = <>
    BeforeInsert = CdsRotasBeforeDelete
    BeforeDelete = CdsRotasBeforeDelete
    Left = 310
    Top = 397
  end
  object DtsRotas: TDataSource
    DataSet = CdsRotas
    Left = 338
    Top = 397
  end
  object CdsFiltro1: TClientDataSet
    Aggregates = <>
    Params = <>
    Left = 552
    object CdsFiltro1NmFiltro: TStringField
      DisplayWidth = 20
      FieldName = 'NmFiltro'
    end
    object CdsFiltro1DsFiltro: TStringField
      DisplayWidth = 200
      FieldName = 'DsFiltro'
      Size = 200
    end
  end
  object CdsFiltro2: TClientDataSet
    Aggregates = <>
    Params = <>
    Left = 624
    object StringField1: TStringField
      DisplayWidth = 20
      FieldName = 'NmFiltro'
    end
    object StringField2: TStringField
      DisplayWidth = 200
      FieldName = 'DsFiltro'
      Size = 200
    end
  end
  object DtsFiltro2: TDataSource
    DataSet = CdsFiltro2
    Left = 664
  end
  object CdsCidade: TClientDataSet
    Aggregates = <>
    Params = <>
    BeforeInsert = CdsRotasBeforeDelete
    BeforeDelete = CdsRotasBeforeDelete
    Left = 310
    Top = 425
  end
  object DtsCidade: TDataSource
    DataSet = CdsCidade
    Left = 338
    Top = 425
  end
  object CdsTotal: TClientDataSet
    Aggregates = <>
    Params = <>
    Left = 864
    Top = 328
  end
  object PopRotas: TPopupMenu
    Left = 25
    Top = 399
    object MarcarTodas1: TMenuItem
      Caption = 'Marcar Todas'
      OnClick = MarcarTodas1Click
    end
    object DesmarcarTodas1: TMenuItem
      Caption = 'Desmarcar Todas'
      OnClick = DesmarcarTodas1Click
    end
  end
  object PopCidade: TPopupMenu
    Left = 53
    Top = 399
    object MenuItem1: TMenuItem
      Caption = 'Marcar Todas'
      OnClick = MenuItem1Click
    end
    object MenuItem2: TMenuItem
      Caption = 'Desmarcar Todas'
      OnClick = MenuItem2Click
    end
  end
  object CdsPesCTRC: TClientDataSet
    Aggregates = <>
    Params = <>
    Left = 520
    Top = 56
  end
  object DtsTomado: TDataSource
    DataSet = CdsTomado
    Left = 338
    Top = 369
  end
  object PopTomado: TPopupMenu
    Left = 81
    Top = 399
    object MenuItem3: TMenuItem
      Caption = 'Marcar Todas'
      OnClick = MenuItem3Click
    end
    object MenuItem4: TMenuItem
      Caption = 'Desmarcar Todas'
      OnClick = MenuItem4Click
    end
  end
  object CdsFiltro3: TClientDataSet
    Aggregates = <>
    Params = <>
    Left = 704
    object StringField3: TStringField
      DisplayWidth = 20
      FieldName = 'NmFiltro'
    end
    object StringField4: TStringField
      DisplayWidth = 200
      FieldName = 'DsFiltro'
      Size = 200
    end
  end
  object DtsFiltro3: TDataSource
    DataSet = CdsFiltro3
    Left = 744
  end
  object CdsTomado: TClientDataSet
    Aggregates = <>
    Params = <>
    BeforeInsert = CdsRotasBeforeDelete
    BeforeDelete = CdsRotasBeforeDelete
    Left = 310
    Top = 369
    object StringField5: TStringField
      DisplayWidth = 1
      FieldName = 'SnMarcad'
      Size = 1
    end
    object CdsTomadoCdTomado: TIntegerField
      FieldName = 'CdTomado'
    end
    object CdsTomadoNmTomado: TStringField
      FieldName = 'NmTomado'
      Size = 60
    end
    object CdsTomadoNmCidade: TStringField
      FieldName = 'NmCidade'
      Size = 40
    end
    object CdsTomadoCdEstado: TStringField
      FieldName = 'CdEstado'
      Size = 2
    end
    object CdsTomadoQtCtrc: TIntegerField
      FieldName = 'QtCtrc'
    end
    object CdsTomadoNrPeso: TFloatField
      FieldName = 'NrPeso'
    end
    object CdsTomadoVrMercad: TFloatField
      FieldName = 'VrMercad'
    end
    object CdsTomadoQtDiaVen: TIntegerField
      FieldName = 'QtDiaVen'
    end
    object CdsTomadoDtPreEnt: TDateField
      FieldName = 'DtPreEnt'
    end
    object CdsTomadoCdCtrc: TMemoField
      FieldName = 'CdCtrc'
      BlobType = ftMemo
    end
    object CdsTomadoCdRota: TMemoField
      FieldName = 'CdRota'
      BlobType = ftMemo
    end
    object CdsTomadoStVencim: TStringField
      FieldName = 'StVencim'
      Size = 1
    end
  end
  object CdsCTRC: TClientDataSet
    Aggregates = <>
    Params = <>
    BeforeInsert = CdsRotasBeforeDelete
    BeforeDelete = CdsRotasBeforeDelete
    Left = 310
    Top = 457
  end
  object DtsCTRC: TDataSource
    DataSet = CdsCTRC
    Left = 338
    Top = 456
  end
  object PopCtrc: TPopupMenu
    Left = 109
    Top = 399
    object MenuItem5: TMenuItem
      Caption = 'Marcar Todas'
      OnClick = MenuItem5Click
    end
    object MenuItem6: TMenuItem
      Caption = 'Desmarcar Todas'
      OnClick = MenuItem6Click
    end
  end
  object CdsFiltro4: TClientDataSet
    Aggregates = <>
    Params = <>
    Left = 776
    object StringField6: TStringField
      DisplayWidth = 20
      FieldName = 'NmFiltro'
    end
    object StringField7: TStringField
      DisplayWidth = 200
      FieldName = 'DsFiltro'
      Size = 200
    end
  end
  object DtsFiltro4: TDataSource
    DataSet = CdsFiltro4
    Left = 816
  end
  object DtsCarga: TDataSource
    DataSet = CdsCarga
    Left = 616
    Top = 328
  end
  object CdsCarga: TClientDataSet
    Aggregates = <>
    Params = <>
    Left = 560
    Top = 328
  end
  object CdsRota: TClientDataSet
    Aggregates = <>
    Params = <>
    Left = 560
    Top = 56
  end
  object BrvGerRel: TBrvGeraRelatorio
    BrDicionario = DmSrvApl.BrvDicionario
    SQLConnectionImp = DmSrvApl.SrvImpres
    BrCdsData = CdsCarga
    Left = 512
    Top = 65535
  end
  object CdsTotMar: TClientDataSet
    Aggregates = <>
    Params = <>
    Left = 856
  end
  object CdsParams: TClientDataSet
    Aggregates = <>
    Params = <>
    Left = 472
    object CdsParamsTpVeicul: TIntegerField
      FieldName = 'TpVeicul'
    end
    object CdsParamsCdVeicul: TStringField
      FieldName = 'CdVeicul'
      Size = 40
    end
    object CdsParamsCdsCtrc: TBlobField
      FieldName = 'CdsCtrc'
    end
  end
end
