inherited Mov0013: TMov0013
  Caption = 'Mov0013 - Detalhamento de Opera'#231#227'o Log'#237'stica para An'#225'lise'
  ClientHeight = 571
  ClientWidth = 940
  FormStyle = fsNormal
  Position = poOwnerFormCenter
  Visible = False
  ExplicitWidth = 948
  ExplicitHeight = 605
  PixelsPerInch = 96
  TextHeight = 13
  inherited NavBarra: TBrvDbNavCop
    Width = 940
    ExplicitWidth = 940
    inherited BrvSpeedButton1: TBrvSpeedButton
      Left = 915
      ExplicitLeft = 915
    end
    inherited SbtAjuda: TBrvSpeedButton
      Left = 890
      ExplicitLeft = 890
    end
  end
  inherited PnlFundo: TPanel
    Width = 940
    Height = 541
    ExplicitWidth = 940
    ExplicitHeight = 541
    object Splitter3: TSplitter
      Left = 1
      Top = 273
      Width = 934
      Height = 3
      Cursor = crVSplit
      Align = alTop
      ExplicitTop = 305
      ExplicitWidth = 136
    end
    object PnlOperacao: TPanel
      AlignWithMargins = True
      Left = 4
      Top = 492
      Width = 928
      Height = 41
      Align = alBottom
      BevelEdges = [beTop]
      BevelKind = bkTile
      BevelOuter = bvNone
      TabOrder = 0
      DesignSize = (
        928
        39)
      object Label13: TLabel
        Left = 8
        Top = 13
        Width = 62
        Height = 13
        Caption = 'Somat'#243'ria:'
        Font.Charset = DEFAULT_CHARSET
        Font.Color = clBlue
        Font.Height = -11
        Font.Name = 'Tahoma'
        Font.Style = [fsBold]
        ParentFont = False
      end
      object BtnCancel: TBrvBitBtn
        Left = 836
        Top = 8
        Width = 90
        Height = 25
        Anchors = [akTop, akRight]
        Caption = 'Retornar'
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
        OnClick = BtnCancelClick
        BrTipoBotao = BrBtnPrimei
      end
      object BtnSalvar: TBrvBitBtn
        Left = 644
        Top = 8
        Width = 90
        Height = 25
        Anchors = [akTop, akRight]
        Caption = 'Aprovar'
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
        OnClick = BtnSalvarClick
        BrTipoBotao = BrBtnSalvar
      end
      object BtnRefazer: TBrvBitBtn
        Left = 486
        Top = 8
        Width = 152
        Height = 25
        Anchors = [akTop, akRight]
        Caption = 'Refazer Atividade'
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
        OnClick = BtnRefazerClick
        BrTipoBotao = BrBtnAtualizar
      end
      object BtnCancelar: TBrvBitBtn
        Left = 740
        Top = 8
        Width = 90
        Height = 25
        Anchors = [akTop, akRight]
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
        OnClick = BtnCancelarClick
        BrTipoBotao = BrBtnCancel
      end
      object EdtQtProduto: TBrvRetCon
        Left = 76
        Top = 10
        Width = 100
        Height = 19
        TabStop = False
        BevelInner = bvLowered
        BevelKind = bkFlat
        BevelWidth = 2
        BorderStyle = bsNone
        ParentColor = True
        ReadOnly = True
        TabOrder = 4
      end
    end
    object Panel3: TPanel
      Left = 1
      Top = 276
      Width = 934
      Height = 213
      Align = alClient
      TabOrder = 1
      object PgcColeta: TPageControl
        Left = 1
        Top = 1
        Width = 932
        Height = 211
        ActivePage = TbsConfAtual
        Align = alClient
        TabOrder = 0
        object TbsConfAtual: TTabSheet
          Caption = 'Controles Log'#237'sticos Conferidos'
          object DbgW008: TBrvDbGrid
            Left = 0
            Top = 0
            Width = 924
            Height = 183
            BrShowMemo = True
            BrReadOnlyStyle = [fsItalic]
            BrReadOnlyColor = clMaroon
            Align = alClient
            DataSource = DtsW008
            ReadOnly = True
            TabOrder = 0
            TitleFont.Charset = DEFAULT_CHARSET
            TitleFont.Color = clWindowText
            TitleFont.Height = -11
            TitleFont.Name = 'Tahoma'
            TitleFont.Style = []
            BrDicionario = DmSrvApl.BrvDicionario
            BrDrawColumn.Strings = (
              'N'#227'o remova essas duas linhas de formata'#231#227'o:'
              'CampoTabela;Operador;ValorComparativo;Cor;')
            Columns = <
              item
                Expanded = False
                FieldName = 'DsLote'
                Title.Caption = 'Lote'
                Width = 100
                Visible = True
                BrConsulta = 0
                BrPermissao = []
                BrValueHalfChecked = False
                BrSaveOnClick = False
              end
              item
                Expanded = False
                FieldName = 'DtProduc'
                Title.Caption = 'Produ'#231#227'o'
                Width = 80
                Visible = True
                BrConsulta = 0
                BrPermissao = []
                BrValueHalfChecked = False
                BrSaveOnClick = False
              end
              item
                Expanded = False
                FieldName = 'DtValida'
                Title.Caption = 'Validade'
                Width = 80
                Visible = True
                BrConsulta = 0
                BrPermissao = []
                BrValueHalfChecked = False
                BrSaveOnClick = False
              end
              item
                Expanded = False
                FieldName = 'NrSSCC'
                Title.Caption = 'SSCC'
                Width = 150
                Visible = True
                BrConsulta = 0
                BrPermissao = []
                BrValueHalfChecked = False
                BrSaveOnClick = False
              end
              item
                Expanded = False
                FieldName = 'DtColeta'
                Title.Caption = 'Coleta'
                Width = 120
                Visible = True
                BrConsulta = 0
                BrPermissao = []
                BrValueHalfChecked = False
                BrSaveOnClick = False
              end
              item
                Expanded = False
                FieldName = 'QtProdut'
                Title.Caption = 'Quantidade'
                Width = 80
                Visible = True
                BrConsulta = 0
                BrPermissao = []
                BrValueHalfChecked = False
                BrSaveOnClick = False
              end>
          end
        end
        object TabSheet2: TTabSheet
          Caption = 'Hist'#243'rico de Controles Log'#237'sticos Conferidos'
          ImageIndex = 1
          object DbgW008H: TBrvDbGrid
            Left = 0
            Top = 0
            Width = 924
            Height = 183
            BrShowMemo = True
            BrReadOnlyStyle = [fsItalic]
            BrReadOnlyColor = clMaroon
            Align = alClient
            DataSource = DtsW008H
            ReadOnly = True
            TabOrder = 0
            TitleFont.Charset = DEFAULT_CHARSET
            TitleFont.Color = clWindowText
            TitleFont.Height = -11
            TitleFont.Name = 'Tahoma'
            TitleFont.Style = []
            BrDicionario = DmSrvApl.BrvDicionario
            BrDrawColumn.Strings = (
              'N'#227'o remova essas duas linhas de formata'#231#227'o:'
              'CampoTabela;Operador;ValorComparativo;Cor;')
            Columns = <
              item
                Expanded = False
                FieldName = 'DsLote'
                Title.Caption = 'Lote'
                Width = 100
                Visible = True
                BrConsulta = 0
                BrPermissao = []
                BrValueHalfChecked = False
                BrSaveOnClick = False
              end
              item
                Expanded = False
                FieldName = 'DtProduc'
                Title.Caption = 'Produ'#231#227'o'
                Width = 80
                Visible = True
                BrConsulta = 0
                BrPermissao = []
                BrValueHalfChecked = False
                BrSaveOnClick = False
              end
              item
                Expanded = False
                FieldName = 'DtValida'
                Title.Caption = 'Validade'
                Width = 80
                Visible = True
                BrConsulta = 0
                BrPermissao = []
                BrValueHalfChecked = False
                BrSaveOnClick = False
              end
              item
                Expanded = False
                FieldName = 'NrSSCC'
                Title.Caption = 'SSCC'
                Width = 150
                Visible = True
                BrConsulta = 0
                BrPermissao = []
                BrValueHalfChecked = False
                BrSaveOnClick = False
              end
              item
                Expanded = False
                FieldName = 'DtColeta'
                Title.Caption = 'Coleta'
                Width = 120
                Visible = True
                BrConsulta = 0
                BrPermissao = []
                BrValueHalfChecked = False
                BrSaveOnClick = False
              end
              item
                Expanded = False
                FieldName = 'QtProdut'
                Title.Caption = 'Quantidade'
                Width = 80
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
    object Panel4: TPanel
      Left = 1
      Top = 92
      Width = 934
      Height = 181
      Align = alTop
      TabOrder = 2
      object Splitter2: TSplitter
        Left = 507
        Top = 1
        Height = 179
        Align = alRight
        ExplicitLeft = 408
        ExplicitTop = 32
        ExplicitHeight = 100
      end
      object Panel5: TPanel
        Left = 510
        Top = 1
        Width = 423
        Height = 179
        Align = alRight
        TabOrder = 0
        object Label10: TLabel
          Left = 1
          Top = 1
          Width = 421
          Height = 13
          Align = alTop
          Caption = 'Controles Log'#237'sticos Esperados'
          Font.Charset = DEFAULT_CHARSET
          Font.Color = clWindowText
          Font.Height = -11
          Font.Name = 'Tahoma'
          Font.Style = [fsBold]
          ParentFont = False
          ExplicitWidth = 174
        end
        object DbgW007: TBrvDbGrid
          Left = 1
          Top = 14
          Width = 421
          Height = 164
          BrShowMemo = True
          BrReadOnlyStyle = []
          BrReadOnlyColor = clMaroon
          Align = alClient
          DataSource = DtsW007
          ReadOnly = True
          TabOrder = 0
          TitleFont.Charset = DEFAULT_CHARSET
          TitleFont.Color = clWindowText
          TitleFont.Height = -11
          TitleFont.Name = 'Tahoma'
          TitleFont.Style = []
          BrDicionario = DmSrvApl.BrvDicionario
          BrDrawColumn.Strings = (
            'N'#227'o remova essas duas linhas de formata'#231#227'o:'
            'CampoTabela;Operador;ValorComparativo;Cor;')
          Columns = <
            item
              Expanded = False
              FieldName = 'DsLote'
              Title.Caption = 'Lote'
              Width = 100
              Visible = True
              BrConsulta = 0
              BrPermissao = []
              BrValueHalfChecked = False
              BrSaveOnClick = False
            end
            item
              Expanded = False
              FieldName = 'DtValida'
              Title.Caption = 'Validade'
              Width = 80
              Visible = True
              BrConsulta = 0
              BrPermissao = []
              BrValueHalfChecked = False
              BrSaveOnClick = False
            end
            item
              Expanded = False
              FieldName = 'QtProdut'
              Title.Caption = 'Quantidade'
              Width = 80
              Visible = True
              BrConsulta = 0
              BrPermissao = []
              BrValueHalfChecked = False
              BrSaveOnClick = False
            end>
        end
      end
      object Panel6: TPanel
        Left = 1
        Top = 1
        Width = 506
        Height = 179
        Align = alClient
        TabOrder = 1
        object Label9: TLabel
          Left = 1
          Top = 1
          Width = 504
          Height = 13
          Align = alTop
          Caption = 'Itens da Atividade'
          Font.Charset = DEFAULT_CHARSET
          Font.Color = clWindowText
          Font.Height = -11
          Font.Name = 'Tahoma'
          Font.Style = [fsBold]
          ParentFont = False
          ExplicitWidth = 104
        end
        object DbgW006: TBrvDbGrid
          Left = 1
          Top = 14
          Width = 504
          Height = 164
          BrShowMemo = True
          BrReadOnlyStyle = []
          BrReadOnlyColor = clMaroon
          Align = alClient
          Color = clWhite
          DataSource = DtsW006
          ReadOnly = True
          TabOrder = 0
          TitleFont.Charset = DEFAULT_CHARSET
          TitleFont.Color = clWindowText
          TitleFont.Height = -11
          TitleFont.Name = 'Tahoma'
          TitleFont.Style = []
          OnDrawColumnCell = DbgW006DrawColumnCell
          BrDicionario = DmSrvApl.BrvDicionario
          BrDrawColumn.Strings = (
            'N'#227'o remova essas duas linhas de formata'#231#227'o:'
            'CampoTabela;Operador;ValorComparativo;Cor;')
          Columns = <
            item
              Expanded = False
              FieldName = 'CdEmbala'
              Title.Caption = 'Item'
              Width = 80
              Visible = True
              BrConsulta = 0
              BrPermissao = []
              BrValueHalfChecked = False
              BrSaveOnClick = False
            end
            item
              Expanded = False
              FieldName = 'DsProdut'
              Title.Caption = 'Descri'#231#227'o'
              Width = 200
              Visible = True
              BrConsulta = 0
              BrPermissao = []
              BrValueHalfChecked = False
              BrSaveOnClick = False
            end
            item
              Expanded = False
              FieldName = 'QtProdut'
              Title.Caption = 'Quantidade'
              Width = 80
              Visible = True
              BrConsulta = 0
              BrPermissao = []
              BrValueHalfChecked = False
              BrSaveOnClick = False
            end
            item
              Expanded = False
              FieldName = 'DsIteOpe'
              Title.Caption = 'Situa'#231#227'o'
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
    object Panel7: TPanel
      AlignWithMargins = True
      Left = 4
      Top = 4
      Width = 928
      Height = 85
      Align = alTop
      BevelEdges = [beTop]
      BevelKind = bkTile
      BevelOuter = bvNone
      TabOrder = 3
      object Label1: TLabel
        Left = 8
        Top = 10
        Width = 54
        Height = 13
        Caption = 'Opera'#231#227'o'
        Font.Charset = DEFAULT_CHARSET
        Font.Color = clBlue
        Font.Height = -11
        Font.Name = 'Tahoma'
        Font.Style = [fsBold]
        ParentFont = False
      end
      object Label2: TLabel
        Left = 8
        Top = 35
        Width = 92
        Height = 13
        Caption = 'Usu'#225'rio Gerador'
        Font.Charset = DEFAULT_CHARSET
        Font.Color = clBlue
        Font.Height = -11
        Font.Name = 'Tahoma'
        Font.Style = [fsBold]
        ParentFont = False
      end
      object Label3: TLabel
        Left = 8
        Top = 60
        Width = 99
        Height = 13
        Caption = 'Usu'#225'rio Operador'
        Font.Charset = DEFAULT_CHARSET
        Font.Color = clBlue
        Font.Height = -11
        Font.Name = 'Tahoma'
        Font.Style = [fsBold]
        ParentFont = False
      end
      object Label4: TLabel
        Left = 216
        Top = 10
        Width = 47
        Height = 13
        Caption = 'Gera'#231#227'o'
        Font.Charset = DEFAULT_CHARSET
        Font.Color = clBlue
        Font.Height = -11
        Font.Name = 'Tahoma'
        Font.Style = [fsBold]
        ParentFont = False
      end
      object Label5: TLabel
        Left = 216
        Top = 35
        Width = 70
        Height = 13
        Caption = 'Inicializa'#231#227'o'
        Font.Charset = DEFAULT_CHARSET
        Font.Color = clBlue
        Font.Height = -11
        Font.Name = 'Tahoma'
        Font.Style = [fsBold]
        ParentFont = False
      end
      object Label6: TLabel
        Left = 216
        Top = 60
        Width = 62
        Height = 13
        Caption = 'Finaliza'#231#227'o'
        Font.Charset = DEFAULT_CHARSET
        Font.Color = clBlue
        Font.Height = -11
        Font.Name = 'Tahoma'
        Font.Style = [fsBold]
        ParentFont = False
      end
      object Label7: TLabel
        Left = 448
        Top = 10
        Width = 60
        Height = 13
        Caption = 'Nota Fiscal'
        Font.Charset = DEFAULT_CHARSET
        Font.Color = clBlue
        Font.Height = -11
        Font.Name = 'Tahoma'
        Font.Style = [fsBold]
        ParentFont = False
      end
      object Label8: TLabel
        Left = 448
        Top = 35
        Width = 29
        Height = 13
        Caption = 'S'#233'rie'
        Font.Charset = DEFAULT_CHARSET
        Font.Color = clBlue
        Font.Height = -11
        Font.Name = 'Tahoma'
        Font.Style = [fsBold]
        ParentFont = False
      end
      object Label11: TLabel
        Left = 448
        Top = 60
        Width = 102
        Height = 13
        Caption = 'Sem Identifica'#231#227'o'
        Font.Charset = DEFAULT_CHARSET
        Font.Color = clBlue
        Font.Height = -11
        Font.Name = 'Tahoma'
        Font.Style = [fsBold]
        ParentFont = False
      end
      object Label12: TLabel
        Left = 662
        Top = 10
        Width = 52
        Height = 13
        Caption = 'Remessa'
        Font.Charset = DEFAULT_CHARSET
        Font.Color = clBlue
        Font.Height = -11
        Font.Name = 'Tahoma'
        Font.Style = [fsBold]
        ParentFont = False
      end
      object EdtNrOpeLog: TBrvRetCon
        Left = 113
        Top = 7
        Width = 93
        Height = 19
        TabStop = False
        BevelInner = bvLowered
        BevelKind = bkFlat
        BevelWidth = 2
        BorderStyle = bsNone
        ParentColor = True
        ReadOnly = True
        TabOrder = 0
      end
      object EdtNmUsuGer: TBrvRetCon
        Left = 113
        Top = 32
        Width = 93
        Height = 19
        TabStop = False
        BevelInner = bvLowered
        BevelKind = bkFlat
        BevelWidth = 2
        BorderStyle = bsNone
        ParentColor = True
        ReadOnly = True
        TabOrder = 1
      end
      object EdtNmUsuExe: TBrvRetCon
        Left = 113
        Top = 57
        Width = 93
        Height = 19
        TabStop = False
        BevelInner = bvLowered
        BevelKind = bkFlat
        BevelWidth = 2
        BorderStyle = bsNone
        ParentColor = True
        ReadOnly = True
        TabOrder = 2
      end
      object EdtDtGeraca: TBrvRetCon
        Left = 292
        Top = 7
        Width = 150
        Height = 19
        TabStop = False
        BevelInner = bvLowered
        BevelKind = bkFlat
        BevelWidth = 2
        BorderStyle = bsNone
        ParentColor = True
        ReadOnly = True
        TabOrder = 3
      end
      object EdtDtIniOpe: TBrvRetCon
        Left = 292
        Top = 32
        Width = 150
        Height = 19
        TabStop = False
        BevelInner = bvLowered
        BevelKind = bkFlat
        BevelWidth = 2
        BorderStyle = bsNone
        ParentColor = True
        ReadOnly = True
        TabOrder = 4
      end
      object EdtDtFimOpe: TBrvRetCon
        Left = 292
        Top = 57
        Width = 150
        Height = 19
        TabStop = False
        BevelInner = bvLowered
        BevelKind = bkFlat
        BevelWidth = 2
        BorderStyle = bsNone
        ParentColor = True
        ReadOnly = True
        TabOrder = 5
      end
      object EdtNrNfOrig: TBrvRetCon
        Left = 556
        Top = 7
        Width = 100
        Height = 19
        TabStop = False
        BevelInner = bvLowered
        BevelKind = bkFlat
        BevelWidth = 2
        BorderStyle = bsNone
        ParentColor = True
        ReadOnly = True
        TabOrder = 6
      end
      object EdtNrSENFOR: TBrvRetCon
        Left = 556
        Top = 32
        Width = 100
        Height = 19
        TabStop = False
        BevelInner = bvLowered
        BevelKind = bkFlat
        BevelWidth = 2
        BorderStyle = bsNone
        ParentColor = True
        ReadOnly = True
        TabOrder = 7
      end
      object EdtQtProSid: TBrvRetCon
        Left = 556
        Top = 57
        Width = 100
        Height = 19
        TabStop = False
        BevelInner = bvLowered
        BevelKind = bkFlat
        BevelWidth = 2
        BorderStyle = bsNone
        ParentColor = True
        ReadOnly = True
        TabOrder = 8
      end
      object EdtNrFornec: TBrvRetCon
        Left = 746
        Top = 7
        Width = 100
        Height = 19
        TabStop = False
        BevelInner = bvLowered
        BevelKind = bkFlat
        BevelWidth = 2
        BorderStyle = bsNone
        ParentColor = True
        ReadOnly = True
        TabOrder = 9
      end
      object EdtDsTipAti: TBrvRetCon
        Left = 662
        Top = 32
        Width = 184
        Height = 19
        TabStop = False
        BevelInner = bvLowered
        BevelKind = bkFlat
        BevelWidth = 2
        BorderStyle = bsNone
        Font.Charset = DEFAULT_CHARSET
        Font.Color = clMaroon
        Font.Height = -11
        Font.Name = 'Tahoma'
        Font.Style = []
        ParentColor = True
        ParentFont = False
        ReadOnly = True
        TabOrder = 10
      end
    end
  end
  object DtsW006: TDataSource
    DataSet = CdsW006
    Left = 80
    Top = 176
  end
  object CdsW006: TClientDataSet
    Aggregates = <>
    Params = <>
    AfterScroll = CdsW006AfterScroll
    Left = 24
    Top = 176
  end
  object CdsW007: TClientDataSet
    Aggregates = <>
    Params = <>
    AfterScroll = CdsW007AfterScroll
    Left = 424
    Top = 168
  end
  object DtsW007: TDataSource
    DataSet = CdsW007
    Left = 472
    Top = 168
  end
  object DtsW008: TDataSource
    DataSet = CdsW008
    Left = 88
    Top = 392
  end
  object CdsW008: TBrvClientDataSet
    Aggregates = <>
    Params = <>
    BrShowFieldName = False
    BrQueryCode = 0
    BrDicionario = DmSrvApl.BrvDicionario
    BrType = VqNormal
    BrGravaLog = True
    BrUserCode = 0
    Left = 24
    Top = 392
  end
  object CdsW008H: TBrvClientDataSet
    Aggregates = <>
    Params = <>
    BrShowFieldName = False
    BrQueryCode = 0
    BrDicionario = DmSrvApl.BrvDicionario
    BrType = VqNormal
    BrGravaLog = True
    BrUserCode = 0
    Left = 24
    Top = 448
  end
  object DtsW008H: TDataSource
    DataSet = CdsW008H
    Left = 88
    Top = 448
  end
  object BrvLogScreen: TBrvLogScreen
    Left = 552
    Top = 240
  end
end
