inherited Con0023: TCon0023
  Caption = 'Con0023'
  ClientHeight = 362
  ClientWidth = 592
  ExplicitWidth = 600
  ExplicitHeight = 389
  PixelsPerInch = 96
  TextHeight = 13
  inherited NavBarra: TBrvDbNavCop
    Width = 592
    ExplicitWidth = 592
    inherited BrvSpeedButton1: TBrvSpeedButton
      Left = 565
      ExplicitLeft = 567
    end
    inherited SbtAjuda: TBrvSpeedButton
      Left = 540
      ExplicitLeft = 542
    end
  end
  inherited PnlFundo: TPanel
    Width = 592
    Height = 332
    ExplicitWidth = 592
    ExplicitHeight = 332
    object PnlFiltros: TPanel
      Left = 1
      Top = 1
      Width = 586
      Height = 326
      Align = alClient
      BorderStyle = bsSingle
      Font.Charset = DEFAULT_CHARSET
      Font.Color = clWindowText
      Font.Height = -11
      Font.Name = 'Tahoma'
      Font.Style = []
      ParentFont = False
      TabOrder = 0
      DesignSize = (
        582
        322)
      object Label4: TLabel
        Left = 6
        Top = 10
        Width = 38
        Height = 13
        Caption = 'N'#186' RNC'
        Font.Charset = DEFAULT_CHARSET
        Font.Color = clBlack
        Font.Height = -11
        Font.Name = 'MS Sans Serif'
        Font.Style = []
        ParentFont = False
      end
      object Label1: TLabel
        Left = 453
        Top = 10
        Width = 23
        Height = 13
        Anchors = [akTop, akRight]
        Caption = 'Data'
        Font.Charset = DEFAULT_CHARSET
        Font.Color = clBlack
        Font.Height = -11
        Font.Name = 'MS Sans Serif'
        Font.Style = []
        ParentFont = False
        ExplicitLeft = 455
      end
      object Label2: TLabel
        Left = 6
        Top = 36
        Width = 41
        Height = 13
        Caption = 'Emitente'
        Font.Charset = DEFAULT_CHARSET
        Font.Color = clBlack
        Font.Height = -11
        Font.Name = 'MS Sans Serif'
        Font.Style = []
        ParentFont = False
      end
      object Label6: TLabel
        Left = 6
        Top = 132
        Width = 56
        Height = 13
        Caption = 'Destinat'#225'rio'
        Font.Charset = DEFAULT_CHARSET
        Font.Color = clBlack
        Font.Height = -11
        Font.Name = 'MS Sans Serif'
        Font.Style = []
        ParentFont = False
      end
      object BrvDBRetCon1: TBrvDBRetCon
        Left = 69
        Top = 7
        Width = 70
        Height = 19
        TabStop = False
        BevelInner = bvLowered
        BevelKind = bkFlat
        BevelWidth = 2
        BorderStyle = bsNone
        DataField = 'NrRNC'
        DataSource = DsQ004
        ParentColor = True
        ReadOnly = True
        TabOrder = 0
      end
      object BrvDBRetCon2: TBrvDBRetCon
        Left = 484
        Top = 7
        Width = 90
        Height = 19
        TabStop = False
        Anchors = [akTop, akRight]
        BevelInner = bvLowered
        BevelKind = bkFlat
        BevelWidth = 2
        BorderStyle = bsNone
        DataField = 'DtEmiRNC'
        DataSource = DsQ004
        ParentColor = True
        ReadOnly = True
        TabOrder = 1
      end
      object BrvDBRetCon3: TBrvDBRetCon
        Left = 69
        Top = 33
        Width = 70
        Height = 19
        TabStop = False
        BevelInner = bvLowered
        BevelKind = bkFlat
        BevelWidth = 2
        BorderStyle = bsNone
        DataField = 'CdUsuEmi'
        DataSource = DsQ004
        ParentColor = True
        ReadOnly = True
        TabOrder = 2
      end
      object BrvDBRetCon4: TBrvDBRetCon
        Left = 145
        Top = 33
        Width = 429
        Height = 19
        TabStop = False
        Anchors = [akLeft, akTop, akRight]
        BevelInner = bvLowered
        BevelKind = bkFlat
        BevelWidth = 2
        BorderStyle = bsNone
        DataField = 'NmUsuEmi'
        DataSource = DsQ004
        ParentColor = True
        ReadOnly = True
        TabOrder = 3
      end
      object GroupBox1: TGroupBox
        Left = 6
        Top = 54
        Width = 568
        Height = 68
        Anchors = [akLeft, akTop, akRight]
        Caption = 'Local'
        TabOrder = 4
        DesignSize = (
          568
          68)
        object Label3: TLabel
          Left = 7
          Top = 17
          Width = 20
          Height = 13
          Caption = 'Filial'
          Font.Charset = DEFAULT_CHARSET
          Font.Color = clBlack
          Font.Height = -11
          Font.Name = 'MS Sans Serif'
          Font.Style = []
          ParentFont = False
        end
        object Label5: TLabel
          Left = 7
          Top = 43
          Width = 25
          Height = 13
          Caption = 'Setor'
          Font.Charset = DEFAULT_CHARSET
          Font.Color = clBlack
          Font.Height = -11
          Font.Name = 'MS Sans Serif'
          Font.Style = []
          ParentFont = False
        end
        object BrvDBRetCon6: TBrvDBRetCon
          Left = 139
          Top = 14
          Width = 419
          Height = 19
          TabStop = False
          Anchors = [akLeft, akTop, akRight]
          BevelInner = bvLowered
          BevelKind = bkFlat
          BevelWidth = 2
          BorderStyle = bsNone
          DataField = 'DsEmpres'
          DataSource = DsQ004
          ParentColor = True
          ReadOnly = True
          TabOrder = 0
        end
        object BrvDBRetCon5: TBrvDBRetCon
          Left = 63
          Top = 14
          Width = 70
          Height = 19
          TabStop = False
          BevelInner = bvLowered
          BevelKind = bkFlat
          BevelWidth = 2
          BorderStyle = bsNone
          DataField = 'CdEmpres'
          DataSource = DsQ004
          ParentColor = True
          ReadOnly = True
          TabOrder = 1
        end
        object BrvDBRetCon7: TBrvDBRetCon
          Left = 63
          Top = 40
          Width = 70
          Height = 19
          TabStop = False
          BevelInner = bvLowered
          BevelKind = bkFlat
          BevelWidth = 2
          BorderStyle = bsNone
          DataField = 'CdSetor'
          DataSource = DsQ004
          ParentColor = True
          ReadOnly = True
          TabOrder = 2
        end
        object BrvDBRetCon8: TBrvDBRetCon
          Left = 139
          Top = 40
          Width = 419
          Height = 19
          TabStop = False
          Anchors = [akLeft, akTop, akRight]
          BevelInner = bvLowered
          BevelKind = bkFlat
          BevelWidth = 2
          BorderStyle = bsNone
          DataField = 'DsSetor'
          DataSource = DsQ004
          ParentColor = True
          ReadOnly = True
          TabOrder = 3
        end
      end
      object BrvDBRetCon9: TBrvDBRetCon
        Left = 69
        Top = 129
        Width = 70
        Height = 19
        TabStop = False
        BevelInner = bvLowered
        BevelKind = bkFlat
        BevelWidth = 2
        BorderStyle = bsNone
        DataField = 'CdUsuDes'
        DataSource = DsQ004
        ParentColor = True
        ReadOnly = True
        TabOrder = 5
      end
      object BrvDBRetCon10: TBrvDBRetCon
        Left = 145
        Top = 129
        Width = 429
        Height = 19
        TabStop = False
        Anchors = [akLeft, akTop, akRight]
        BevelInner = bvLowered
        BevelKind = bkFlat
        BevelWidth = 2
        BorderStyle = bsNone
        DataField = 'NmUsuDes'
        DataSource = DsQ004
        ParentColor = True
        ReadOnly = True
        TabOrder = 6
      end
      object PgcDetalRNC: TPageControl
        AlignWithMargins = True
        Left = 4
        Top = 156
        Width = 574
        Height = 162
        ActivePage = TbsNaoCon
        Align = alBottom
        Anchors = [akLeft, akTop, akRight, akBottom]
        MultiLine = True
        TabHeight = 26
        TabOrder = 7
        object TbsNaoCon: TTabSheet
          Caption = 'N'#227'o Conformidade'
          object Panel1: TPanel
            Left = 0
            Top = 0
            Width = 566
            Height = 126
            Align = alClient
            BorderStyle = bsSingle
            Enabled = False
            Font.Charset = DEFAULT_CHARSET
            Font.Color = clWindowText
            Font.Height = -11
            Font.Name = 'Tahoma'
            Font.Style = []
            ParentFont = False
            TabOrder = 0
            DesignSize = (
              562
              122)
            object Label7: TLabel
              Left = 8
              Top = 11
              Width = 62
              Height = 13
              Caption = 'Classifica'#231#227'o'
              Font.Charset = DEFAULT_CHARSET
              Font.Color = clBlack
              Font.Height = -11
              Font.Name = 'MS Sans Serif'
              Font.Style = []
              ParentFont = False
            end
            object BrvDBRetCon11: TBrvDBRetCon
              Left = 76
              Top = 9
              Width = 70
              Height = 19
              TabStop = False
              BevelInner = bvLowered
              BevelKind = bkFlat
              BevelWidth = 2
              BorderStyle = bsNone
              DataField = 'CdClaRnc'
              DataSource = DsQ004
              ParentColor = True
              ReadOnly = True
              TabOrder = 0
            end
            object BrvDBRetCon12: TBrvDBRetCon
              Left = 228
              Top = 9
              Width = 326
              Height = 19
              TabStop = False
              Anchors = [akLeft, akTop, akRight]
              BevelInner = bvLowered
              BevelKind = bkFlat
              BevelWidth = 2
              BorderStyle = bsNone
              DataField = 'DsClaRnc'
              DataSource = DsQ004
              ParentColor = True
              ReadOnly = True
              TabOrder = 1
            end
            object BrvDBRetCon13: TBrvDBRetCon
              Left = 152
              Top = 9
              Width = 70
              Height = 19
              TabStop = False
              BevelInner = bvLowered
              BevelKind = bkFlat
              BevelWidth = 2
              BorderStyle = bsNone
              DataField = 'DsContro'
              DataSource = DsQ004
              ParentColor = True
              ReadOnly = True
              TabOrder = 2
            end
            object DBMemo1: TDBMemo
              Left = 8
              Top = 37
              Width = 546
              Height = 75
              Anchors = [akLeft, akTop, akRight]
              Color = clBtnFace
              Ctl3D = False
              DataField = 'TxRnc'
              DataSource = DsQ004
              ParentCtl3D = False
              TabOrder = 3
            end
          end
        end
        object TbsDadCom: TTabSheet
          Caption = 'Dados Complementares'
          ImageIndex = 4
          object Panel2: TPanel
            Left = 0
            Top = 0
            Width = 566
            Height = 126
            Align = alClient
            BorderStyle = bsSingle
            Enabled = False
            Font.Charset = DEFAULT_CHARSET
            Font.Color = clWindowText
            Font.Height = -11
            Font.Name = 'Tahoma'
            Font.Style = []
            ParentFont = False
            TabOrder = 0
            DesignSize = (
              562
              122)
            object Label8: TLabel
              Left = 8
              Top = 11
              Width = 72
              Height = 13
              Caption = 'Transportadora'
              Font.Charset = DEFAULT_CHARSET
              Font.Color = clBlack
              Font.Height = -11
              Font.Name = 'MS Sans Serif'
              Font.Style = []
              ParentFont = False
            end
            object Label9: TLabel
              Left = 8
              Top = 39
              Width = 43
              Height = 13
              Caption = 'Motorista'
              Font.Charset = DEFAULT_CHARSET
              Font.Color = clBlack
              Font.Height = -11
              Font.Name = 'MS Sans Serif'
              Font.Style = []
              ParentFont = False
            end
            object Label10: TLabel
              Left = 8
              Top = 67
              Width = 27
              Height = 13
              Caption = 'Placa'
              Font.Charset = DEFAULT_CHARSET
              Font.Color = clBlack
              Font.Height = -11
              Font.Name = 'MS Sans Serif'
              Font.Style = []
              ParentFont = False
            end
            object Label11: TLabel
              Left = 8
              Top = 95
              Width = 24
              Height = 13
              Caption = 'Frota'
              Font.Charset = DEFAULT_CHARSET
              Font.Color = clBlack
              Font.Height = -11
              Font.Name = 'MS Sans Serif'
              Font.Style = []
              ParentFont = False
            end
            object BrvDBRetCon14: TBrvDBRetCon
              Left = 87
              Top = 9
              Width = 70
              Height = 19
              TabStop = False
              BevelInner = bvLowered
              BevelKind = bkFlat
              BevelWidth = 2
              BorderStyle = bsNone
              DataField = 'CdTransp'
              DataSource = DsQ004
              ParentColor = True
              ReadOnly = True
              TabOrder = 0
            end
            object BrvDBRetCon15: TBrvDBRetCon
              Left = 163
              Top = 9
              Width = 391
              Height = 19
              TabStop = False
              Anchors = [akLeft, akTop, akRight]
              BevelInner = bvLowered
              BevelKind = bkFlat
              BevelWidth = 2
              BorderStyle = bsNone
              DataField = 'RsTitula'
              DataSource = DsQ004
              ParentColor = True
              ReadOnly = True
              TabOrder = 1
            end
            object BrvDBRetCon16: TBrvDBRetCon
              Left = 87
              Top = 37
              Width = 70
              Height = 19
              TabStop = False
              BevelInner = bvLowered
              BevelKind = bkFlat
              BevelWidth = 2
              BorderStyle = bsNone
              DataField = 'CdMotori'
              DataSource = DsQ004
              ParentColor = True
              ReadOnly = True
              TabOrder = 2
            end
            object BrvDBRetCon17: TBrvDBRetCon
              Left = 163
              Top = 37
              Width = 391
              Height = 19
              TabStop = False
              Anchors = [akLeft, akTop, akRight]
              BevelInner = bvLowered
              BevelKind = bkFlat
              BevelWidth = 2
              BorderStyle = bsNone
              DataField = 'NmMotori'
              DataSource = DsQ004
              ParentColor = True
              ReadOnly = True
              TabOrder = 3
            end
            object BrvDBRetCon18: TBrvDBRetCon
              Left = 87
              Top = 65
              Width = 70
              Height = 19
              TabStop = False
              BevelInner = bvLowered
              BevelKind = bkFlat
              BevelWidth = 2
              BorderStyle = bsNone
              DataField = 'NrPlaVei'
              DataSource = DsQ004
              ParentColor = True
              ReadOnly = True
              TabOrder = 4
            end
            object BrvDBRetCon19: TBrvDBRetCon
              Left = 87
              Top = 93
              Width = 70
              Height = 19
              TabStop = False
              BevelInner = bvLowered
              BevelKind = bkFlat
              BevelWidth = 2
              BorderStyle = bsNone
              DataField = 'CdVeicul'
              DataSource = DsQ004
              ParentColor = True
              ReadOnly = True
              TabOrder = 5
            end
          end
        end
        object TbsProEnv: TTabSheet
          Caption = 'Produto(s) Envolvido(s)'
          ImageIndex = 1
          object DbgProdutos: TBrvDbGrid
            Left = 0
            Top = 0
            Width = 566
            Height = 126
            BrShowMemo = True
            BrReadOnlyStyle = [fsItalic]
            BrReadOnlyColor = clMaroon
            Align = alClient
            Color = clBtnFace
            DataSource = DsQ005
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
            BrGradeZebrada = True
            Columns = <
              item
                Expanded = False
                FieldName = 'CdProdut'
                Title.Caption = 'Produto'
                Width = 75
                Visible = True
                BrConsulta = 0
                BrConfigurar.Strings = (
                  'CdProdut;CdProdut;S;S;'
                  'DsProdut;DsProdut;S;N;')
                BrPermissao = []
                BrValueHalfChecked = False
                BrSaveOnClick = False
              end
              item
                Expanded = False
                FieldName = 'DsProdut'
                Title.Caption = 'Descri'#231#227'o do Produto'
                Width = 250
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
                Width = 75
                Visible = True
                BrConsulta = 0
                BrPermissao = []
                BrValueHalfChecked = False
                BrSaveOnClick = False
              end
              item
                Expanded = False
                FieldName = 'NrLote'
                Title.Caption = 'N'#186' Lote'
                Width = 100
                Visible = True
                BrConsulta = 0
                BrPermissao = []
                BrValueHalfChecked = False
                BrSaveOnClick = False
              end
              item
                Expanded = False
                FieldName = 'DtVencto'
                Title.Caption = 'Data Vencimento'
                Width = 110
                Visible = True
                BrConsulta = 0
                BrPermissao = []
                BrValueHalfChecked = False
                BrSaveOnClick = False
              end
              item
                Expanded = False
                FieldName = 'NrNota'
                Title.Caption = 'Nota Fiscal'
                Width = 80
                Visible = True
                BrConsulta = 0
                BrPermissao = []
                BrValueHalfChecked = False
                BrSaveOnClick = False
              end
              item
                Expanded = False
                FieldName = 'CdTitula'
                Title.Caption = 'Cliente'
                Width = 75
                Visible = True
                BrConsulta = 0
                BrPermissao = []
                BrValueHalfChecked = False
                BrSaveOnClick = False
              end
              item
                Expanded = False
                FieldName = 'RsTitula'
                Title.Caption = 'Nome do Cliente'
                Width = 275
                Visible = True
                BrConsulta = 0
                BrPermissao = []
                BrValueHalfChecked = False
                BrSaveOnClick = False
              end>
          end
        end
        object TabParInt: TTabSheet
          Caption = 'Partes Interessadas'
          ImageIndex = 6
          object DbgUsuarios: TBrvDbGrid
            Left = 0
            Top = 0
            Width = 566
            Height = 126
            BrShowMemo = True
            BrReadOnlyStyle = [fsItalic]
            BrReadOnlyColor = clMaroon
            Align = alClient
            Color = clBtnFace
            DataSource = DsQ006
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
            BrGradeZebrada = True
            Columns = <
              item
                Expanded = False
                FieldName = 'CdUsuari'
                Title.Caption = 'C'#243'digo'
                Width = 75
                Visible = True
                BrConsulta = 0
                BrConfigurar.Strings = (
                  'CdUsuari;CdUsuari;S;S;'
                  'NmComUsu;NmComUsu;S;N;'
                  'DsEmail;DsEmail;S;N;')
                BrPermissao = []
                BrValueHalfChecked = False
                BrSaveOnClick = False
              end
              item
                Expanded = False
                FieldName = 'NmComUsu'
                Title.Caption = 'Nome Completo'
                Width = 400
                Visible = True
                BrConsulta = 0
                BrPermissao = []
                BrValueHalfChecked = False
                BrSaveOnClick = False
              end>
          end
        end
        object TbsAnexos: TTabSheet
          Caption = 'Anexos'
          ImageIndex = 4
          object DbgAnexos: TBrvDbGrid
            Left = 0
            Top = 0
            Width = 566
            Height = 126
            BrShowMemo = True
            BrReadOnlyStyle = [fsItalic]
            BrReadOnlyColor = clMaroon
            Align = alClient
            Color = clBtnFace
            DataSource = DsQ007
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
            BrGradeZebrada = False
            Columns = <
              item
                Expanded = False
                FieldName = 'NmArquiv'
                ReadOnly = False
                Title.Caption = 'Arquivo'
                Width = 600
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
  end
  inherited PopCfgFrm: TPopupMenu
    Left = 425
    Top = 0
  end
  inherited ImlPopFrm: TImageList
    Left = 440
    Top = 0
  end
  inherited LspS049: TBrvListParam
    Left = 455
    Top = 0
  end
  object DsQ004: TDataSource
    DataSet = CpQ004
    Left = 70
  end
  object CpQ004: TBrvClientDataSet
    Aggregates = <>
    Params = <>
    ReadOnly = True
    BrShowFieldName = False
    BrQueryCode = 259
    BrDicionario = DmSrvApl.BrvDicionario
    BrType = VqNormal
    BrGravaLog = True
    BrUserCode = 0
    Left = 18
  end
  object CpQ007: TBrvClientDataSet
    Aggregates = <>
    FieldDefs = <>
    IndexDefs = <
      item
        Name = 'CpQ007Index1'
      end>
    Params = <>
    ReadOnly = True
    StoreDefs = True
    BrShowFieldName = False
    BrQueryCode = 0
    BrDicionario = DmSrvApl.BrvDicionario
    BrType = VqNormal
    BrGravaLog = True
    BrUserCode = 0
    Left = 312
  end
  object DsQ007: TDataSource
    DataSet = CpQ007
    Left = 361
  end
  object CpQ005: TBrvClientDataSet
    Aggregates = <>
    Params = <>
    ReadOnly = True
    BrShowFieldName = False
    BrQueryCode = 0
    BrDicionario = DmSrvApl.BrvDicionario
    BrType = VqNormal
    BrGravaLog = True
    BrUserCode = 0
    Left = 121
    Top = 65535
  end
  object DsQ005: TDataSource
    DataSet = CpQ005
    Left = 171
    Top = 65535
  end
  object CpQ006: TBrvClientDataSet
    Aggregates = <>
    Params = <>
    ReadOnly = True
    BrShowFieldName = False
    BrQueryCode = 0
    BrDicionario = DmSrvApl.BrvDicionario
    BrType = VqNormal
    BrGravaLog = True
    BrUserCode = 0
    Left = 217
    Top = 65535
  end
  object DsQ006: TDataSource
    DataSet = CpQ006
    Left = 267
    Top = 65535
  end
  object BrvListParam: TBrvListParam
    Left = 504
    Top = 142
  end
  object BrvServerProgress: TBrvServerProgress
    Left = 505
    Top = 190
  end
end
