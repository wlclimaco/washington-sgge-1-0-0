inherited Mov0010: TMov0010
  Left = 388
  Top = 144
  Caption = 'Mov0010 - Faturar Movimento '
  ClientHeight = 505
  ClientWidth = 788
  Position = poDesktopCenter
  ExplicitWidth = 796
  ExplicitHeight = 532
  PixelsPerInch = 96
  TextHeight = 13
  inherited NavBarra: TBrvDbNavCop
    Width = 788
    ExplicitWidth = 788
    inherited BrvSpeedButton1: TBrvSpeedButton
      Left = 761
      ExplicitLeft = 707
    end
    inherited SbtAjuda: TBrvSpeedButton
      Left = 736
      ExplicitLeft = 682
    end
  end
  inherited PnlFundo: TPanel
    Top = 84
    Width = 788
    Height = 387
    Visible = False
    ExplicitTop = 84
    ExplicitWidth = 788
    ExplicitHeight = 387
    object PnlRodapeTotal: TPanel
      Left = 1
      Top = 332
      Width = 782
      Height = 50
      Align = alBottom
      BorderStyle = bsSingle
      TabOrder = 0
      object GbxTotMarc: TGroupBox
        Left = 391
        Top = 1
        Width = 386
        Height = 44
        Align = alRight
        Caption = 'Documentos marcados para faturamento:'
        Font.Charset = DEFAULT_CHARSET
        Font.Color = clWindowText
        Font.Height = -11
        Font.Name = 'Tahoma'
        Font.Style = [fsBold]
        ParentFont = False
        TabOrder = 0
        object Label2: TLabel
          Left = 8
          Top = 21
          Width = 65
          Height = 13
          Caption = 'Quantidade'
        end
        object Label4: TLabel
          Left = 263
          Top = 21
          Width = 29
          Height = 13
          Caption = 'Valor'
        end
        object Label8: TLabel
          Left = 133
          Top = 21
          Width = 27
          Height = 13
          Caption = 'Desc'
        end
        object EdtQtMarcar: TBrvEditNum
          Left = 76
          Top = 16
          Width = 37
          Height = 21
          TabStop = False
          BevelKind = bkFlat
          BorderStyle = bsNone
          Color = clBtnFace
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
          BrQueryCode = 0
          BrRecordar = False
        end
        object EdtVrMarcar: TBrvEditNum
          Left = 298
          Top = 16
          Width = 83
          Height = 21
          TabStop = False
          BevelKind = bkFlat
          BorderStyle = bsNone
          Color = clBtnFace
          ReadOnly = True
          TabOrder = 1
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
        object EdtVrDescMa: TBrvEditNum
          Left = 165
          Top = 16
          Width = 83
          Height = 21
          TabStop = False
          BevelKind = bkFlat
          BorderStyle = bsNone
          Color = clBtnFace
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
      end
      object GbxTotDesmarc: TGroupBox
        Left = 1
        Top = 1
        Width = 384
        Height = 44
        Align = alLeft
        Caption = 'Documentos desmarcados para faturamento:'
        Font.Charset = DEFAULT_CHARSET
        Font.Color = clWindowText
        Font.Height = -11
        Font.Name = 'Tahoma'
        Font.Style = [fsBold]
        ParentFont = False
        TabOrder = 1
        object Label6: TLabel
          Left = 8
          Top = 20
          Width = 65
          Height = 13
          Caption = 'Quantidade'
        end
        object Label7: TLabel
          Left = 266
          Top = 21
          Width = 29
          Height = 13
          Caption = 'Valor'
        end
        object Label3: TLabel
          Left = 133
          Top = 21
          Width = 27
          Height = 13
          Caption = 'Desc'
        end
        object EdtQtDesmar: TBrvEditNum
          Left = 79
          Top = 16
          Width = 37
          Height = 21
          TabStop = False
          BevelKind = bkFlat
          BorderStyle = bsNone
          Color = clBtnFace
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
          BrQueryCode = 0
          BrRecordar = False
        end
        object EdtVrDesmar: TBrvEditNum
          Left = 298
          Top = 16
          Width = 83
          Height = 21
          TabStop = False
          BevelKind = bkFlat
          BorderStyle = bsNone
          Color = clBtnFace
          ReadOnly = True
          TabOrder = 1
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
        object EdtVrDescDe: TBrvEditNum
          Left = 165
          Top = 16
          Width = 83
          Height = 21
          TabStop = False
          BevelKind = bkFlat
          BorderStyle = bsNone
          Color = clBtnFace
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
      end
    end
    object PnlGroup1: TPanel
      Left = 1
      Top = 1
      Width = 782
      Height = 331
      Align = alClient
      BevelOuter = bvNone
      TabOrder = 1
      object PnlDadosFat: TPanel
        Left = 0
        Top = 0
        Width = 782
        Height = 113
        Align = alTop
        BorderStyle = bsSingle
        TabOrder = 0
        object Label5: TLabel
          Left = 8
          Top = 11
          Width = 114
          Height = 13
          Caption = 'Cliente Faturamento'
          Font.Charset = DEFAULT_CHARSET
          Font.Color = clBlue
          Font.Height = -11
          Font.Name = 'MS Sans Serif'
          Font.Style = [fsBold]
          ParentFont = False
        end
        object Label10: TLabel
          Left = 8
          Top = 58
          Width = 60
          Height = 13
          Caption = 'Pr'#233' Fatura'
          Font.Charset = DEFAULT_CHARSET
          Font.Color = clBlack
          Font.Height = -11
          Font.Name = 'MS Sans Serif'
          Font.Style = [fsBold]
          ParentFont = False
        end
        object Label12: TLabel
          Left = 8
          Top = 82
          Width = 130
          Height = 13
          Caption = 'N'#250'mero do Documento'
          Font.Charset = DEFAULT_CHARSET
          Font.Color = clBlack
          Font.Height = -11
          Font.Name = 'MS Sans Serif'
          Font.Style = [fsBold]
          ParentFont = False
        end
        object Label13: TLabel
          Left = 8
          Top = 34
          Width = 120
          Height = 13
          Caption = 'Data de Faturamento'
          Font.Charset = DEFAULT_CHARSET
          Font.Color = clBlue
          Font.Height = -11
          Font.Name = 'MS Sans Serif'
          Font.Style = [fsBold]
          ParentFont = False
        end
        object EdtCdCliFat: TBrvEditNum
          Left = 141
          Top = 7
          Width = 89
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
            'EdtCdCliFat;CDTitula;S;S;'
            'LblRsCliFat;RsTitula;S;N;')
          BrDicionario = DmSrvApl.BrvDicionario
          BrQueryCode = 15
          BrRecordar = False
        end
        object LblRsCliFat: TBrvRetCon
          Left = 233
          Top = 9
          Width = 376
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
        object EdtDtFatura: TBrvEditDate
          Left = 141
          Top = 31
          Width = 89
          Height = 21
          EditMask = '99/99/9999;1; '
          MaxLength = 10
          TabOrder = 2
          Text = '  /  /    '
          BrDataValida = False
          BrDataVazia = True
          BrFunctionButton = TVdData
          BrAlignment = taLeftJustify
          BrDicionario = DmSrvApl.BrvDicionario
          BrRecordar = False
        end
        object CmbPreFat: TComboBox
          Left = 141
          Top = 55
          Width = 89
          Height = 21
          Style = csDropDownList
          TabOrder = 3
          OnChange = CmbPreFatChange
        end
        object EdtNrFatura: TBrvEditNum
          Left = 141
          Top = 79
          Width = 89
          Height = 21
          TabOrder = 4
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
      object GrdFatura: TBrvDbGrid
        Left = 0
        Top = 113
        Width = 782
        Height = 218
        BrShowMemo = True
        BrReadOnlyStyle = []
        BrReadOnlyColor = clBlack
        Align = alClient
        DataSource = DtsFatura
        PopupMenu = PopSnTodos
        TabOrder = 1
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
            ButtonStyle = cbsNone
            Expanded = False
            FieldName = 'SnMarcad'
            Title.Alignment = taCenter
            Title.Caption = 'R'
            Title.Font.Charset = SYMBOL_CHARSET
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
            FieldName = 'DsModeNf'
            Font.Charset = DEFAULT_CHARSET
            Font.Color = clWindowText
            Font.Height = -11
            Font.Name = 'MS Sans Serif'
            Font.Style = []
            ReadOnly = True
            Title.Caption = 'Modelo'
            Title.Font.Charset = DEFAULT_CHARSET
            Title.Font.Color = clWindowText
            Title.Font.Height = -11
            Title.Font.Name = 'Tahoma'
            Title.Font.Style = [fsBold]
            Width = 48
            Visible = True
            BrConsulta = 0
            BrPermissao = []
            BrValueHalfChecked = False
            BrSaveOnClick = False
          end
          item
            ButtonStyle = cbsEllipsis
            Expanded = False
            FieldName = 'NrSeriNf'
            ReadOnly = True
            Title.Caption = 'S'#233'rie'
            Title.Font.Charset = DEFAULT_CHARSET
            Title.Font.Color = clWindowText
            Title.Font.Height = -11
            Title.Font.Name = 'Tahoma'
            Title.Font.Style = [fsBold]
            Width = 33
            Visible = True
            BrConsulta = 0
            BrPermissao = []
            BrValueHalfChecked = False
            BrSaveOnClick = False
          end
          item
            Expanded = False
            FieldName = 'NrNota'
            ReadOnly = True
            Title.Caption = 'N'#250'mero NF'
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
            FieldName = 'CTRC'
            ReadOnly = True
            Title.Font.Charset = DEFAULT_CHARSET
            Title.Font.Color = clWindowText
            Title.Font.Height = -11
            Title.Font.Name = 'Tahoma'
            Title.Font.Style = [fsBold]
            Width = 38
            Visible = True
            BrConsulta = 0
            BrPermissao = []
            BrValueHalfChecked = False
            BrSaveOnClick = False
          end
          item
            Expanded = False
            FieldName = 'DtEmiNot'
            ReadOnly = True
            Title.Caption = 'Emiss'#227'o'
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
            FieldName = 'DtEntreg'
            ReadOnly = True
            Title.Caption = 'Entrega'
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
            FieldName = 'VrConNot'
            Font.Charset = DEFAULT_CHARSET
            Font.Color = clWindowText
            Font.Height = -11
            Font.Name = 'MS Sans Serif'
            Font.Style = []
            ReadOnly = True
            Title.Caption = 'Valor'
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
            FieldName = 'VrDescon'
            Title.Caption = 'Desconto'
            Title.Font.Charset = DEFAULT_CHARSET
            Title.Font.Color = clWindowText
            Title.Font.Height = -11
            Title.Font.Name = 'Tahoma'
            Title.Font.Style = [fsBold]
            Width = 73
            Visible = True
            BrConsulta = 0
            BrPermissao = []
            BrValueHalfChecked = False
            BrSaveOnClick = False
          end
          item
            Expanded = False
            FieldName = 'DsStNota'
            ReadOnly = True
            Title.Caption = 'Status da NF'
            Title.Font.Charset = DEFAULT_CHARSET
            Title.Font.Color = clWindowText
            Title.Font.Height = -11
            Title.Font.Name = 'Tahoma'
            Title.Font.Style = [fsBold]
            Width = 186
            Visible = True
            BrConsulta = 0
            BrPermissao = []
            BrValueHalfChecked = False
            BrSaveOnClick = False
          end>
      end
    end
  end
  object PnlCabeca: TPanel [2]
    Left = 0
    Top = 30
    Width = 788
    Height = 54
    Align = alTop
    TabOrder = 2
    object Label1: TLabel
      Left = 8
      Top = 8
      Width = 49
      Height = 13
      Caption = 'Empresa'
      Font.Charset = DEFAULT_CHARSET
      Font.Color = clBlue
      Font.Height = -11
      Font.Name = 'Tahoma'
      Font.Style = [fsBold]
      ParentFont = False
    end
    object LblNrPlano: TLabel
      Left = 535
      Top = 6
      Width = 50
      Height = 13
      Caption = 'LblNrPlano'
      Visible = False
    end
    object LblCliFor: TLabel
      Left = 8
      Top = 36
      Width = 40
      Height = 13
      Caption = 'Cliente'
      Font.Charset = DEFAULT_CHARSET
      Font.Color = clBlue
      Font.Height = -11
      Font.Name = 'MS Sans Serif'
      Font.Style = [fsBold]
      ParentFont = False
    end
    object LblTpFatCtr: TLabel
      Left = 535
      Top = 30
      Width = 56
      Height = 13
      Caption = 'LblTpFatCtr'
      Visible = False
    end
    object EdtCdEmpres: TBrvEditNum
      Left = 61
      Top = 6
      Width = 89
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
        'EdtDsEmpres;RsEmpres;S;N;'
        'LblNrPlano;NrPlano;S;N;')
      BrDicionario = DmSrvApl.BrvDicionario
      BrQueryCode = 12
      BrRecordar = False
    end
    object EdtDsEmpres: TBrvRetCon
      Left = 153
      Top = 6
      Width = 376
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
    object EdtCdTitula: TBrvEditNum
      Left = 61
      Top = 28
      Width = 89
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
        'EdtCdTitula;CDTitula;S;S;'
        'LblRsTitula;RsTitula;S;N;'
        'LblTpFatCtr;TpFatCtr;S;N;')
      BrDicionario = DmSrvApl.BrvDicionario
      BrQueryCode = 15
      BrRecordar = False
    end
    object LblRsTitula: TBrvRetCon
      Left = 153
      Top = 30
      Width = 376
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
  end
  object PnlOperac: TPanel [3]
    Left = 0
    Top = 471
    Width = 788
    Height = 34
    Align = alBottom
    BorderStyle = bsSingle
    TabOrder = 3
    object BtnAbrEmp: TBrvBitBtn
      Left = 491
      Top = 3
      Width = 90
      Height = 25
      Hint = 'Localizar'
      Caption = 'Buscar'
      DoubleBuffered = True
      Font.Charset = DEFAULT_CHARSET
      Font.Color = clWindowText
      Font.Height = -11
      Font.Name = 'Tahoma'
      Font.Style = [fsBold]
      Glyph.Data = {
        96090000424D9609000000000000360000002800000028000000140000000100
        1800000000006009000000000000000000000000000000000000008080008080
        0080800080800080800080800080800080800080800080800080800080800080
        8000808000808000808000808000808000808000808000808000808000808000
        8080008080008080008080008080008080008080008080008080008080008080
        0080800080800080800080800080800080800080800080800080800080800080
        8000808000808000808000808000808000808000808000808000808000808000
        8080008080008080008080008080008080008080008080008080008080008080
        0080800080800080800080800080800080800080800080800080800080800080
        8000808000808000808000808000808000808000808000808000808000808000
        8080008080008080008080008080008080008080008080008080008080008080
        0080800080800080800080800080800080800080800080800080800080800080
        8000808000808000808000808000808000808000808000808000808000808000
        8080008080AD4416B95E3CB95E3CB95E3CB95E3CB46C49008080008080008080
        008080008080008080B95E3CB95E3CB95E3CB95E3CB46C490080800080800080
        80646360808080808080808080808080C0C0C000808000808000808000808000
        8080008080808080808080808080808080C0C0C0008080008080008080AD4416
        B95E3CE8D1C6B95E3CB95E3CB46C490080800080800080800080800080800080
        80B95E3CE8D1C6B95E3CB95E3CB46C49008080008080008080646360808080E8
        D1C6808080808080C0C0C0008080008080008080008080008080008080808080
        E8D1C6808080808080C0C0C0008080008080008080AD4416B95E3CE8D1C6B95E
        3CB95E3CB46C49008080008080008080008080008080008080B95E3CE8D1C6B9
        5E3CB95E3CB46C49008080008080008080646360808080E8D1C6808080808080
        C0C0C0008080008080008080008080008080008080808080E8D1C68080808080
        80C0C0C0008080008080008080AD4416B95E3CE8D1C6B95E3CB95E3CB46C4900
        8080008080008080008080008080008080B95E3CE8D1C6B95E3CB95E3CB46C49
        008080008080008080646360808080E8D1C6808080808080C0C0C00080800080
        80008080008080008080008080808080E8D1C6808080808080C0C0C000808000
        8080008080AD4416B95E3CB95E3CB95E3CB95E3CB95E3CB95E3CB46C49008080
        008080B95E3CB95E3CB95E3CB95E3CB95E3CB95E3CB46C490080800080800080
        80646360808080808080808080808080808080808080C0C0C000808000808080
        8080808080808080808080808080808080C0C0C0008080008080008080AD4416
        B95E3CB95E3CE8D1C6B95E3CB95E3CB95E3CB46C49B95E3CB95E3CB95E3CE8D1
        C6B95E3CB95E3CB95E3CB95E3CB46C4900808000808000808064636080808080
        8080E8D1C6808080808080808080C0C0C0808080808080808080E8D1C6808080
        808080808080808080C0C0C0008080008080008080AD4416B95E3CB95E3CE8D1
        C6B95E3CB95E3CB95E3C808080B95E3CB95E3CB95E3CE8D1C6B95E3CB95E3CB9
        5E3CB95E3CB46C49008080008080008080646360808080808080E8D1C6808080
        808080808080808080808080808080808080E8D1C68080808080808080808080
        80C0C0C0008080008080008080AD4416B95E3CB95E3CE8D1C6B95E3CB95E3CB9
        5E3C808080B95E3CB95E3CB95E3CE8D1C6B95E3CB95E3CB95E3CB95E3CB46C49
        008080008080008080646360808080808080E8D1C68080808080808080808080
        80808080808080808080E8D1C6808080808080808080808080C0C0C000808000
        8080008080008080AD4416B95E3CB95E3CB95E3CB95E3CB95E3CB46C49B95E3C
        B95E3CB95E3CB95E3CB95E3CB95E3CB95E3CB95E3C0080800080800080800080
        80008080646360808080808080808080808080808080C0C0C080808080808080
        8080808080808080808080808080808080008080008080008080008080008080
        008080AD4416B95E3CE8D1C6B95E3CB95E3CB46C49008080AD4416B95E3CE8D1
        C6B95E3CB95E3CB46C4900808000808000808000808000808000808000808064
        6360808080E8D1C6808080808080C0C0C0008080646360808080E8D1C6808080
        808080C0C0C0008080008080008080008080008080008080008080AD4416B95E
        3CB95E3CB95E3CB95E3CB46C49008080AD4416B95E3CB95E3CB95E3CB95E3CB4
        6C49008080008080008080008080008080008080008080646360808080808080
        808080808080C0C0C0008080646360808080808080808080808080C0C0C00080
        80008080008080008080008080008080008080008080AD4416B95E3CB95E3CB9
        5E3C008080008080008080AD4416B95E3CB95E3CB95E3C008080008080008080
        0080800080800080800080800080806463606463608080808080808080800080
        8000808000808064636080808080808080808000808000808000808000808000
        8080008080008080008080008080AD4416B95E3CE8D1C6B95E3C008080008080
        008080AD4416B95E3CE8D1C6B95E3C0080800080800080800080800080800080
        80008080008080008080646360808080E8D1C680808000808000808000808064
        6360808080E8D1C6808080008080008080008080008080008080008080008080
        008080008080AD4416B95E3CB95E3CB95E3C008080008080008080AD4416B95E
        3CB95E3CB95E3C00808000808000808000808000808000808000808000808000
        8080646360808080808080808080008080008080008080646360808080808080
        8080800080800080800080800080800080800080800080800080800080800080
        80AD4416AD4416AD4416008080008080008080AD4416AD4416AD4416AD441600
        8080008080008080008080008080008080008080008080008080008080646360
        6463606463600080800080800080800080806463606463606463600080800080
        8000808000808000808000808000808000808000808000808000808000808000
        8080008080008080008080008080008080008080008080008080008080008080
        0080800080800080800080800080800080800080800080800080800080800080
        8000808000808000808000808000808000808000808000808000808000808000
        8080008080008080008080008080008080008080008080008080008080008080
        0080800080800080800080800080800080800080800080800080800080800080
        8000808000808000808000808000808000808000808000808000808000808000
        8080008080008080008080008080008080008080008080008080}
      NumGlyphs = 2
      ParentDoubleBuffered = False
      ParentFont = False
      ParentShowHint = False
      ShowHint = True
      TabOrder = 0
      OnClick = BtnAbrEmpClick
      BrTipoBotao = BrBtnLocalizar
    end
    object BtnFecEmp: TBrvBitBtn
      Left = 587
      Top = 3
      Width = 90
      Height = 25
      Hint = 'Cancelar'
      Caption = 'Cancelar'
      DoubleBuffered = True
      Enabled = False
      Font.Charset = DEFAULT_CHARSET
      Font.Color = clWindowText
      Font.Height = -11
      Font.Name = 'Tahoma'
      Font.Style = [fsBold]
      Glyph.Data = {
        96090000424D9609000000000000360000002800000028000000140000000100
        18000000000060090000C40E0000C40E00000000000000000000008080008080
        0080800080800080800080800080800080800048AE004AAB004AAA0047AB0080
        8000808000808000808000808000808000808000808000808000808000808000
        8080008080008080008080008080676060606060606060655F5F008080008080
        0080800080800080800080800080800080800080800080800080800080800080
        80008080006DFF096FE81F77DC2A7ED72979CE1C6AC20553B000328B00808000
        8080008080008080008080008080008080008080008080008080008080008080
        9882828C86868D8D8D9090908B8B8B7F7F7F6E67675D47470080800080800080
        800080800080800080800080800080800080800080800072FF1281FF66ACF6AB
        D0F7D1E6F9E3F0FBE1EFFCCCE4F8A1C8F05796DC0654B6003586008080008080
        0080800080800080800080800080800080808D8787A09696BDBDBDD8D8D8EBEB
        EBF5F5F5F5F5F5E8E8E8D2D2D2A9A9A97268684F4A4A00808000808000808000
        80800080800080800080800075FF449DFFD0E7FEFFFFFFFFFFFFFFFFFEEEF5FE
        F9FBFEFFFFFFFFFFFFF9FDFFBED9F52A72C7003B8D0080800080800080800080
        80008080008080878686B5B2B2EEEEEEFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFF
        FFFFFFFFFFFFFFFFE8E8E88A87874D4D4D008080008080008080008080008080
        007AFF57AAFFF5FBFFFFFFFFF2F6FE75B0F41273E91272E71271E81170E781B5
        F3FFFFFFFFFFFFE9F3FD2D75C9003689008080008080008080008080908A8ABF
        BCBCFCFCFCFFFFFFFFFFFFC3C3C38E8E8E8C8C8C8D8D8D8B8B8BC9C9C9FFFFFF
        FFFFFFF5F5F58E8B8B504A4A008080008080008080008080419FFFF1F8FFFFFF
        FFC1DDFB197DF00065EA056DE91073EA036AE70066E60057E3257FEAC7DDF9FF
        FFFFCEE3F50F5DBE008080008080008080008080BBB1B1FBFBFBFFFFFFF0F0F0
        9494947E7E7E8686868C8C8C848484828282747474979797F3F3F3FFFFFFEBEB
        EB7C72722071710080800080801186FF9CCDFFFFFFFFE1EFFD2084F44795F14C
        98F21377ED0870EA0D71E90E71E94792ED0D6EE61E7BEAEBF3FEFFFFFF71A5DC
        0049B9008080008080AF9999DADADAFFFFFFFDFDFD999999A8A8A8ACACAC8F8F
        8F8888888B8B8B8A8A8AA9A9A9888888939393FFFFFFFFFFFFB5B5B5755E5E00
        80800080803498FFDDEDFFFFFFFF65ACF9127BF2B5D6FBFFFFFF64A7F3066EEC
        0F75EC8CBCF4FBFDFF4892EE0059E478B1F2FFFFFFC0D9F11469D10080800080
        80B1AAAAF5F5F5FFFFFFBCBCBC929292E4E4E4FFFFFFB8B8B88888888C8C8CCD
        CDCDFFFFFFAAAAAA757575C4C4C4FFFFFFE0E0E0878080306D6D1589FF5EAEFF
        FFFFFFFAFDFF2C8CF61D83F5318EF5ADD1FBF0F7FD4C9AF361A5F4FFFFFF8DBD
        F51375EA0169E81877EAF9FBFEEFF5FB3388E10053CEB4A194BCBCBCFFFFFFFF
        FFFFA1A1A1979797A2A2A2E0E0E0FEFEFEAFAFAFB8B8B8FFFFFFCECECE8D8D8D
        838383919191FFFFFFFCFCFC9A9A9A736C6C1789FF84C0FFFFFFFFB4D8FE2A8E
        F92B8FF71A84F62488F6A5CDFAF2F7FEE1EEFD66A7F30D75ED0C72EB096FEA10
        73EAC9E0FAFFFFFF4C9EEF0067F4AEA091CCCCCCFFFFFFE4E4E4A1A1A19F9F9F
        9797979D9D9DDEDEDEFDFDFDFAFAFABABABA8C8C8C8A8A8A8888888C8C8CEBEB
        EBFFFFFFACACAC8080802691FFA5D2FFFFFFFFABD4FE2D91FC288EFA2F90F917
        83F787BEFAFFFFFFF8FBFE4899F40C77EF1277EE0D73EB1376EDB7D6F9FFFFFF
        53A5F9006EFAB6A798DCDCDCFFFFFFDFDFDFA4A4A49F9F9FA2A2A2969696CFCF
        CFFFFFFFFDFDFDAEAEAE8E8E8E8F8F8F8B8B8B8F8F8FE1E1E1FFFFFFB3B3B384
        84842E95FFB2D8FFFFFFFFCEE5FF3597FC2990FC3193FB87C0FCE5F1FE8AC0FA
        A6CFFBF0F6FE6BACF61B80F11077EF1A7CEED8E9FBFFFFFF47A1FA006EFEBBAC
        9CE2E2E2FFFFFFF1F1F1A8A8A8A1A1A1A6A6A6CECECEF8F8F8D2D2D2DFDFDFFF
        FFFFBDBDBD9595958E8E8E949494F3F3F3FFFFFFB0B0B085858549A3FFA7D3FF
        FFFFFFFFFFFF419EFD2E94FDA1CFFDFFFFFF87C0FC1784F8268AF8B1D4FCFFFF
        FF529FF60B77F0358EF4FFFFFFE6F3FF2E91FD006DFFD4BCACDFDFDFFFFFFFFF
        FFFFAEAEAEA5A5A5DCDCDCFFFFFFCECECE9797979E9E9EE3E3E3FFFFFFB0B0B0
        8C8C8CA3A3A3FFFFFFFAFAFAA5A5A59087870080808AC4FFFBFDFFFFFFFFABD5
        FF1F8EFE9DCEFDA0CFFE3395FB288EFA248BFA3593F9B5D7FC5AA5F8006EF2B1
        D5FCFFFFFFB2D9FF0D7FFF008080008080DED5D5FFFFFFFFFFFFE4E4E49E9E9E
        DADADADBDBDBA7A7A7A0A0A09D9D9DA6A6A6E5E5E5B6B6B6848484E6E6E6FFFF
        FFE0E0E09E969600808000808082BFFFE3F1FFFFFFFFFFFFFF60AEFF228FFE31
        97FF2D93FD2F94FC2C90FB268DFA288DF90D7DF69BCBFDFFFFFFFDFEFF5EADFF
        0078FF008080008080EBD3D3F8F8F8FFFFFFFFFFFFC0C0C0A0A0A0A7A7A7A4A4
        A4A4A4A4A3A3A39E9E9EA0A0A0909090DEDEDEFFFFFFFFFFFFBEBEBEA48C8C00
        8080008080008080ADD6FFFEFEFFFFFFFFFFFFFF8FC5FF359AFF3096FF2C93FE
        2A92FE2C92FC2F93FBA0CDFDFFFFFFFFFFFFA3D0FF007DFF0080800080800080
        80008080F4E7E7FFFFFFFFFFFFFFFFFFD5D5D5A9A9A9A7A7A7A4A4A4A3A3A3A4
        A4A4A3A3A3DDDDDDFFFFFFFFFFFFDDDDDD9B8E8E008080008080008080008080
        75B9F7D0E7FFFFFFFFFFFFFFFFFFFFE8F4FF96CAFF7DBDFF7EBDFE9DCEFEF3F9
        FFFFFFFFFFFFFFC1E0FF1C8CFF007FF7008080008080008080008080C1C9C9F7
        F2F2FFFFFFFFFFFFFFFFFFF9F9F9D5D5D5C8C8C8C8C8C8D9D9D9FDFDFDFFFFFF
        FFFFFFE6E6E6A39E9E0080800080800080800080800080800080807ABBF7BCDD
        FFFCFEFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFEBF5FF98CBFF1D
        8CFF0480F7008080008080008080008080008080008080C3CBCBEEE9E9FFFFFF
        FFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFBFBFBDADADAAD9F9F878F
        8F00808000808000808000808000808000808000808093C8FF9FCFFFC9E4FFEE
        F7FFFDFDFFFFFFFFF9FCFFE5F1FFB6DAFF66B2FF228FFF008080008080008080
        008080008080008080008080008080008080DED9D9EDE0E0EDEDEDFFFFFFFFFF
        FFFFFFFFFFFFFFFCFCFCE7E7E7CCC3C3BAA2A200808000808000808000808000
        808000808000808000808000808000808000808097CAFF77BAFF7FBEFF83C0FF
        70B6FF56AAFF3B9CFF0080800080800080800080800080800080800080800080
        80008080008080008080008080008080E7DCDCCACACACECECED0D0D0C7C7C7BA
        BABAB6AEAE008080008080008080008080008080008080008080}
      NumGlyphs = 2
      ParentDoubleBuffered = False
      ParentFont = False
      ParentShowHint = False
      ShowHint = True
      TabOrder = 1
      OnClick = BtnFecEmpClick
      BrTipoBotao = BrBtnCancel
    end
    object BtnFatura: TBrvBitBtn
      Left = 683
      Top = 3
      Width = 90
      Height = 25
      Hint = 'Novo'
      Caption = 'Faturar'
      DoubleBuffered = True
      Enabled = False
      Font.Charset = DEFAULT_CHARSET
      Font.Color = clWindowText
      Font.Height = -11
      Font.Name = 'Tahoma'
      Font.Style = [fsBold]
      Glyph.Data = {
        96090000424D9609000000000000360000002800000028000000140000000100
        18000000000060090000C40E0000C40E00000000000000000000008080008080
        0080800080800080800080800080800080800080800080800080800080800080
        8000808000808000808000808000808000808000808000808000808000808000
        8080008080008080008080008080008080008080008080008080008080008080
        0080800080800080800080800080800080800080800080800080800080800080
        80008080008080008080B77B37B27B3ABF7A3400808000808000808000808000
        8080008080008080008080008080008080008080008080008080008080008080
        0080800080808173737C72728772720080800080800080800080800080800080
        800080800080800080800080800080800080800080800080800080800080808A
        7F4FAD7E42A77E44AA7E43AB7E42008080008080008080008080008080008080
        0080800080800080800080800080800080800080800080800080806279797C77
        777676767977777A777700808000808000808000808000808000808000808000
        8080008080008080008080008080BC8344A3834DA5834CB28347AB834AAB834A
        AB834AAC8349A3834DA8834BB88345BB83450080800080800080800080800080
        80008080008080008080877A7A757B7B787C7C807B7B7B7B7B7B7B7B7D7C7C7C
        7B7B757B7B797B7B857A7A877B7B008080008080008080008080008080008080
        008080BE894CAF8850B28850B28850AF8850AF8850AF8850AF8850B08850B288
        50B18850AF8850B3884FB4884F0080800080800080800080800080800080808C
        8181818181828080828080818181818181808080818181808080828080838181
        808080848181848080008080008080008080008080008080008080C09056B48F
        59B48F59B58F59B78F58C49156C69156C69156BB9058B68F58B48F59B48F59B7
        8F58B88F580080800080800080800080800080800080808F8787878787878787
        8787878987879388889488889488888C87878887878787878787878987878987
        87008080008080008080008080008080008080C3945CB7935EB8935EB9935EBC
        945EB28850B28850B28850B28850B8935EB9935EB7935EC3945CBD9662008080
        008080008080008080008080008080948C8C8B8B8B8B8B8B8C8B8B8E8B8B1D82
        820F80800C80807089898C8B8B8B8A8A8B8B8B948C8C34848400808000808000
        8080008080008080BD9662C69761BA9563BD9662BF9663B28850008080008080
        008080008080BD9662C89761BA9563BC9662C69661BD96620080800080800080
        80008080008080968E8E8E8E8E908E8E918E8E007C7C007F7F4D888824838300
        7B7B488888988F8F8D8D8D8F8E8E968E8E1482820080800080800080809B946B
        BD9966BD9966BC9966D19C63BD9662008080008080CB9A63C499650080800080
        80C09965BE9965BC9966BF9965C49A64BD966200808000808000808091919192
        91919191919B9B9B1B83834087879691919C9292979191848F8F007C7C939191
        9391919191919391919791916F8C8C008080008080D49E66C09B68BE9B69C49C
        68C69E6CBD9662008080CC9D67BE9B69C29B68BA9B69008080BD9662CA9D67BE
        9B69BE9B69C69C68BD9662008080008080A395959493939393939894949B9B9B
        007E7E4889899E9494929292969393919393007C7C3F88889C94949393939393
        93989393799090008080008080D2A06BC09D6CC09D6CC69E6CC69E6CBD966200
        8080D1A06BC19D6CC69E6CC69E6C008080BD9662CC9F6BC09D6CC09D6CC99F6B
        BD9662008080008080A397979595959595959A96969B9B9B007E7E508C8CA297
        979696969A96969A9696007C7C4089899F97979595959695959C969683939300
        8080008080C9A16FCAA16FC19F6FC19F6FD7A36DBD9662008080008080CCA16F
        C6A06F008080008080C5A06FC4A06FC2A06FC3A06FC7A06FBD96620080800080
        809D98989E9999979797979797A99A9A238585007F7F8E9696A099999B989832
        8787007C7C9A98989998989998989897979C9898008080008080008080008080
        BD9662CBA372C3A273C6A372C69E6CBD9662008080008080008080008080BD96
        62D2A572C3A273C6A372BBA174BD9662008080008080008080008080829696A0
        9B9B9B9B9B9C9A9A9E9B9B007979007B7B308888007B7B007A7A4D8D8DA69C9C
        9A9A9A9C9A9A939999008080008080008080008080008080008080C4A475C9A4
        75C7A475C8A475CBA575BD9662BD9662BD9662BD9662C8A475C8A475C7A475CA
        A475C0A3750080800080800080800080800080800080809A9C9C9D9C9C9C9C9C
        9E9D9D9F9C9C2487871584841483837D96969C9C9C9D9C9C9C9C9C9E9C9C979B
        9B008080008080008080008080008080008080D6A976C7A677C7A677C8A677CB
        A777D8A976DAAA76DAAA76CEA777C9A677C7A677C7A677CBA777C1A577008080
        008080008080008080008080008080AAA0A09E9E9E9E9E9E9E9E9EA19F9FACA1
        A1ADA1A1ADA1A1A49F9F9F9E9E9E9E9E9E9E9EA19F9F999D9D00808000808000
        8080008080008080008080DAAB7AC9A87ACAA87ACCA97ACAA87AC9A87AC9A87A
        C9A87AC9A87ACAA87ACCA97ACAA87ACDA97AD0A97A0080800080800080800080
        80008080008080ADA3A3A0A0A0A1A0A0A2A0A0A1A0A0A0A0A0A0A0A0A0A0A0A1
        A1A1A1A0A0A4A2A2A1A0A0A3A1A1A6A2A2008080008080008080008080008080
        008080008080DDAD7BD1AB7BC0A77CC7A97CCCAA7BCAA97BCAA97BCDAA7BCEAA
        7BC0A77CD2AB7BDFAE7B00808000808000808000808000808000808000808000
        8080B0A5A5A7A3A399A0A09FA1A1A3A2A2A1A1A1A1A1A1A4A2A2A4A2A29AA0A0
        A7A3A3B2A5A50080800080800080800080800080800080800080800080800080
        80008080008080BD9662DAAE7DCBAB7DCDAB7DD2AC7DBD966200808000808000
        8080008080008080008080008080008080008080008080008080008080008080
        008080529292AFA5A5A3A3A3A4A3A3A9A4A41785850080800080800080800080
        8000808000808000808000808000808000808000808000808000808000808000
        8080D4AD7DDEAE7DE3AF7DA8A37E008080008080008080008080008080008080
        008080008080008080008080008080008080008080008080008080008080A9A4
        A4B1A6A6B5A7A7869D9D00808000808000808000808000808000808000808000
        8080008080008080008080008080008080008080008080008080008080008080
        0080800080800080800080800080800080800080800080800080800080800080
        8000808000808000808000808000808000808000808000808000808000808000
        8080008080008080008080008080008080008080008080008080}
      NumGlyphs = 2
      ParentDoubleBuffered = False
      ParentFont = False
      ParentShowHint = False
      ShowHint = True
      TabOrder = 2
      OnClick = BtnFaturaClick
      BrTipoBotao = BrBtnConfig
    end
  end
  inherited PopCfgFrm: TPopupMenu
    Left = 632
    Top = 0
  end
  inherited ImlPopFrm: TImageList
    Left = 664
    Top = 0
    Bitmap = {
      494C010103000500240014001400FFFFFFFFFF10FFFFFFFFFFFFFFFF424D3600
      0000000000003600000028000000500000001400000001002000000000000019
      0000000000000000000000000000000000000000000000000000000000000000
      0000000000000000000000000000000000000000000000000000000000000000
      0000000000000000000000000000000000000000000000000000000000000000
      0000000000000000000000000000000000000000000000000000000000000000
      0000000000000000000000000000000000000000000000000000000000000000
      000000000000000000000000000000000000000000000000000000000000B08B
      7B00BC888700BA868600BA868500BA868500BA868500BA868400BA858400BA85
      8400BA858400BA858400BA858400BA858400BB868400B28E7900000000000000
      0000000000000000000000000000000000000000000000000000000000000000
      0000000000000000000000000000000000000000000000000000000000000000
      0000000000000000000000000000000000000000000000000000000000000000
      000000000000000000008D7B47007E784700AC763300B0773500AF773400AC75
      3300827947006C7D550000000000000000000000000000000000000000000000
      0000000000000000000000000000000000000000000000000000000000000000
      0000000000000000000000000000000000000000000000000000000000000000
      000000000000000000000000000000000000000000000000000000000000DDCC
      AE00FFF1D100FCE7C400FBE4BB00FAE1B400F8DDAD00F7DAA500F5D69C00F5D5
      9900F5D59900F5D59900F5D59900F5D59900F8DA9B00D0B58300000000000000
      0000000000000000000000000000000000000000000000000000000000000000
      0000000000000000000000000000000000000000000000000000000000000000
      0000000000000000000000000000000000000000000000000000000000000000
      000000000000BB783300AA7B3D00C29F6F00D0BA9800D7C5A700D4C1A200CCB5
      9000BC956200AB763500C67A3300000000000000000000000000000000000000
      000000000000000000000000000000000000000000000000000000BB6D0000C4
      6800009676000000000000000000000000000000000000000000000000000000
      000000000000000000000000000000000000000000000000000000000000D7C7
      AE00F9E9D000CFB68D00CFB68D00CFB68D00F1D6AF00CFB68D00CFB68D00CFB6
      8D00CFB68D00EDCB9600CFB68D00CFB68D00CFB68D00CDB08200000000000000
      0000000000000000000000000000000000000000000000000000000000000000
      0000000000000000000000000000000000000000000000000000000000000000
      0000000000000000000000000000000000000000000000000000000000000000
      0000B57C3B00C2A67A00E9DFCD00F1EBDE00F1EDE000F1ECDF00F1ECDF00F1ED
      E000F0EBDE00E0D2BA00B7936200B77D3C000000000000000000000000000000
      0000000000000000000000000000000000000000000002C46F0001BD6E0000BB
      6E0000BD6A00009A750000000000000000000000000000000000000000000000
      000000000000000000000000000000000000000000000000000000000000D8C9
      B100FBEDD600F5E2CA00F4DEC300F4DCBD00F2D9B600F1D5AF00F0D3A700F0CF
      A100EECD9B00EDCB9600EECC9600EECC9700F1D09800CDB08200000000000000
      0000000000000000000000000000000000000000000000000000000000000000
      0000000000000000000000000000000000000000000000000000000000000000
      000000000000000000000000000000000000000000000000000000000000BA83
      4400C9AF8800F4F2E600F0EBDE00EEE9DA00EFEADD00F2EEE300F4F1E600EEE9
      DA00EEE9DB00F1EDE100EFEADD00BB986500BC83450000000000000000000000
      00000000000000000000000000000000000006C1750004C2720002BE700001BC
      6F0000BA6D0000C0680000927800000000000000000000000000000000000000
      000000000000000000000000000000000000000000000000000000000000DACB
      B600FCF0DD00CFB68D00CFB68D00CFB68D00CFB68D00CFB68D00CFB68D00CFB6
      8D00CFB68D00CFB68D00CFB68D00CFB68D00CFB68D00CDB08200000000000000
      0000000000000000000000000000000000000000000000000000000000000000
      0000000000000000000000000000000000000000000000000000000000000000
      0000000000000000000000000000000000000000000000000000CA8B4B00CBB1
      8800F6F4EB00F0EBDE00F0EADD00F1ECE000EBE1D200A5793C00B7946200F3ED
      E100F0EBDE00F0EADD00F2EDE100F2ECE000BE9B6900D68F4C00000000000000
      00000000000000000000000000000EC9770009C4750006C2750004C0720002BE
      710001BC6F0000BA6D0000C16900009A76000000000000000000000000000000
      000000000000000000000000000000000000000000000000000000000000DBCE
      BB00FDF4E600F7E9D900F7E5D000F6E3CC00F3DFC500F4DCBE00F3D9B500F2D6
      B000F1D3AA00F0D0A300EDCB9A00EDCB9600F1D09800CDB08200000000000000
      0000000000000000000000000000000000000000000000000000000000000000
      0000000000000000000000000000000000000000000000000000000000000000
      000000000000000000000000000000000000000000009A8F6100BC925A00EDE7
      D800F3EFE400F1ECE100F1ECE100F2EEE300F0E9DC00C3A67C00D1BC9B00F4F0
      E500F1EDE100F1ECE100F1ECE100F5F2E800E0D3BA00BC8C5100000000000000
      0000000000000000000017CA7B0011C879000DC3770008C3760005C1740004C1
      720001BE700000BB6E0000BB6D0000BD69000096770000000000000000000000
      000000000000000000000000000000000000000000000000000000000000DDD3
      C100FDF7ED00CFB68D00CFB68D00CFB68D00CFB68D00CFB68D00CFB68D00CFB6
      8D00CFB68D00CFB68D00CFB68D00CFB68D00CFB68D00CDB08200000000000000
      0000000000000000000000000000000000000000000000000000000000000000
      0000000000000000000000000000000000000000000000000000000000000000
      00000000000000000000000000000000000000000000AD905E00D1B69000F6F3
      EB00F3EFE600F3EFE500F3EFE500F3F0E600F4F0E600DDCEB200EBE2D200F5F2
      EA00F3EFE500F3EFE500F3EFE500F3F0E600F5F2E900C7A06F00999065000000
      00000000000024CB7D001DCB7D0016C87B0012C679000CC4780009C5760007C7
      750004C0720002BE700001BC6F0000BA6D0000C1690000967600000000000000
      000000000000000000000000000000000000000000000000000000000000E0D7
      C500FEFBF300F9EFE600F8ECDD00F8E9D800F6E6D100F5E2CA00F5DFC200F4DC
      BC00F2D9B600F1D5AF00F0D3A800F0D0A000F1D19B00CDB08200000000000000
      0000000000000000000000000000000000000000000000000000000000000000
      0000000000000000000000000000000000000000000000000000000000000000
      00000000000000000000000000000000000000000000CC975E00E1D2BA00F8F6
      F000F5F2E900F5F2E900F5F2E900F6F3EB00F1ECE000BD996700CAAE8500F9F8
      F200F6F2EA00F5F2E900F5F2E900F6F2E900F8F5EF00D7BB9600969065000000
      00000000000030D4810023CB7F001DC97D0017C87B0012CA79000BB579000000
      000007C7740004C0730002BE710001BB6F0000BB6E0000BD6A00009A75000000
      000000000000000000000000000000000000000000000000000000000000E3DC
      CB00FFFFFA00CFB68D00CFB68D00CFB68D00CFB68D00CFB68D00CFB68D00CFB6
      8D00CFB68D00CFB68D00CFB68D00CFB68D00CFB68D00CCB08400000000000000
      0000000000000000000000000000000000000000000000000000000000000000
      0000000000000000000000000000000000000000000000000000000000000000
      00000000000000000000000000000000000000000000CC9B6400E9DDCC00F8F7
      F100F6F3EC00F6F3EC00F6F3EC00F6F4ED00F8F7F100D1B89300B7925A00DFCE
      B400FCFDFB00F6F3EC00F6F3EC00F6F3EC00F9F9F400E0C7A800979165000000
      00000000000030CC82002CD0810023CA7E001FCD7C0014C07C00000000000000
      00000000000007CB730004C0720002BE700001BC6F0000BA6D0000C068000092
      780000000000000000000000000000000000000000000000000000000000E5DF
      CF00FFFFFF00FEF8F300FAF3EB00F9F0E600F8ECDD00F8E8D800F7E5CF00F5E2
      CA00F4DFC200F3DCBD00F2D8B600F1D6AD00F4D7A900CDB38900000000000000
      0000000000000000000000000000000000000000000000000000000000000000
      0000000000000000000000000000000000000000000000000000000000000000
      00000000000000000000000000000000000000000000CE9E6800EADECD00FAF8
      F300F8F5EE00F8F5EE00F8F6F000FBFBF700FAF7F200FEFEFC00CFB38C00BC95
      6000DECCB100FAF8F300F8F5EF00F8F5EE00FBFBF600E0C8A900999369000000
      000000000000000000002FC9830031D782001FC27E0000000000000000000000
      0000000000000000000007CC740004C0720002BE710001BC6F0000BA6D0000C1
      6900009A7600000000000000000000000000000000000000000000000000E7E0
      CF00FFFFFF00CFB68D00CFB68D00CFB68D00CFB68D00CFB68D00CFB68D00CFB6
      8D00CFB68D00CFB68D00CFB68D00CFB68D00CFB68D00CFB68D00000000000000
      0000000000000000000000000000000000000000000000000000000000000000
      0000000000000000000000000000000000000000000000000000000000000000
      00000000000000000000000000000000000000000000D19E6800E2D3BC00FBFB
      F800F8F6F100F9F8F200F5F1E800CAAB8000F1EADE00FBFBF700FBF9F500CEB3
      8A00C19D6B00F7F4EE00F9F8F200F9F7F100FAFAF500DABE9A009B966D000000
      0000000000000000000000000000000000000000000000000000000000000000
      000000000000000000000000000006C7740004C1720001BE700000BB6E0000BB
      6D0000BD6900009677000000000000000000000000000000000000000000E9E1
      D000FFFFFF00FFFFFF00FFFDFC00FDF9F500FBF4ED00FAF1E700F9ECDD00F8E9
      D700F7E5D000F6E2C900F5DEC200F5DDBC00F6DDB700C6B38E00000000000000
      0000000000000000000000000000000000000000000000000000000000000000
      0000000000000000000000000000000000000000000000000000000000000000
      00000000000000000000000000000000000000000000BA9D6D00D7BD9900FCFC
      F900FAF8F400FBF9F600F4EEE500C0996300D4B99500FFFFFF00FFFFFF00D5BA
      9600C39E6900F7F4EF00FAF9F500FAF9F500FBF9F600CEA87900AB9D72000000
      0000000000000000000000000000000000000000000000000000000000000000
      00000000000000000000000000000000000007CC740004C0720002BE700001BC
      6F0000BA6D0000C169000098760000000000000000000000000000000000EBE3
      D000FFFFFF00FFFFFF00FFFFFF00FFFEFC00FDF9F300FBF4ED00FAEFE400F9ED
      DD00F8E9D600F7E4CF00F7E4CB00F1DCBF00DBC9AD00A8A08300000000000000
      0000000000000000000000000000000000000000000000000000000000000000
      0000000000000000000000000000000000000000000000000000000000000000
      000000000000000000000000000000000000000000007F967800CEA16D00F4EE
      E500FDFCFA00FBF9F600FDFCFA00E1CEB400C6A27100CEAF8500CEAF8500C6A2
      7100E0CCB000FCFBFA00FBF9F600FEFEFE00E6D6C000DEA26A00000000000000
      0000000000000000000000000000000000000000000000000000000000000000
      0000000000000000000000000000000000000000000007C7740004C0730002BE
      710001BB6F0000BD6D0000B36D0000000000000000000000000000000000EDE5
      D100FFFFFF00CFB68D00CFB68D00CFB68D00CFB68D00CFB68D00CFB68D00CFB6
      8D00FAEDDE00FFF5E200F5EBD700D0C6B500BCB6A400A09E8500000000000000
      0000000000000000000000000000000000000000000000000000000000000000
      0000000000000000000000000000000000000000000000000000000000000000
      0000000000000000000000000000000000000000000000000000ECAA7000D6BC
      9700FFFFFF00FDFCFA00FCFBF800FDFDFC00EEE3D300DDC8AA00DDC7A900ECE2
      D200FDFCFA00FCFBF800FEFEFD00F8F4EF00D4AD7D00929B7700000000000000
      0000000000000000000000000000000000000000000000000000000000000000
      000000000000000000000000000000000000000000000000000007CB730004C0
      720002BE700002C66C000000000000000000000000000000000000000000EFE6
      D200FFFFFF00CFB68D00FFFFFF00FFFFFF00FFFFFF00FFFEFC00FCF6F100CFB6
      8D00FAF0E400C8A39600AA7E7800A27C7B00A37E7E00A18A6F00000000000000
      0000000000000000000000000000000000000000000000000000000000000000
      0000000000000000000000000000000000000000000000000000000000000000
      000000000000000000000000000000000000000000000000000000000000CFA4
      7100D8BD9A00FFFFFF00FFFFFF00FDFDFB00FFFFFF00FFFFFF00FFFFFF00FFFF
      FF00FDFDFC00FFFFFF00F9F6F200CDAB7D00DAA9750000000000000000000000
      0000000000000000000000000000000000000000000000000000000000000000
      00000000000000000000000000000000000000000000000000000000000007CE
      740005C77100000000000000000000000000000000000000000000000000F1E9
      D300FFFFFF00CFB68D00FFFFFF00FFFFFF00FFFFFF00FFFFFF00FFFCFB00CFB6
      8D00F4EBE300B88D8700E1B38500EFB36100F5AD3C00D2965700000000000000
      0000000000000000000000000000000000000000000000000000000000000000
      0000000000000000000000000000000000000000000000000000000000000000
      000000000000000000000000000000000000000000000000000000000000B8A6
      7C00D0A67300D5B99400F2EBE000FFFFFF00FFFFFF00FFFFFF00FFFFFF00FFFF
      FF00FFFFFF00EADCCB00D6B08300DBAA76000000000000000000000000000000
      0000000000000000000000000000000000000000000000000000000000000000
      0000000000000000000000000000000000000000000000000000000000000000
      000000000000000000000000000000000000000000000000000000000000F1EA
      D300FFFFFF00CFB68D00CFB68D00CFB68D00CFB68D00CFB68D00CFB68D00CFB6
      8D00F6EFEB00BA8F8900E8BD8B00F8C26200D5986C0000000000000000000000
      0000000000000000000000000000000000000000000000000000000000000000
      0000000000000000000000000000000000000000000000000000000000000000
      0000000000000000000000000000000000000000000000000000000000000000
      000000000000F1B07800D1A67200D9B99000E7CFB300EBD6BF00EBD5BD00E4CB
      AC00D4B18500E3AA7300959E7B00000000000000000000000000000000000000
      0000000000000000000000000000000000000000000000000000000000000000
      0000000000000000000000000000000000000000000000000000000000000000
      000000000000000000000000000000000000000000000000000000000000F4EE
      DA00FFFFFF00FFFFFF00FFFFFF00FFFFFF00FFFFFF00FFFFFF00FFFFFF00FFFF
      FF00F9F7F700BB928E00F0C68700CC9D71000000000000000000000000000000
      0000000000000000000000000000000000000000000000000000000000000000
      0000000000000000000000000000000000000000000000000000000000000000
      0000000000000000000000000000000000000000000000000000000000000000
      00000000000000000000859B7E00A8A17A00A39E7700A29D7600A29D7600A39F
      7800B2A47B000000000000000000000000000000000000000000000000000000
      0000000000000000000000000000000000000000000000000000000000000000
      0000000000000000000000000000000000000000000000000000000000000000
      000000000000000000000000000000000000000000000000000000000000E7EB
      AD00F1F8CF00F0F5CC00EEF4CC00EDF2CC00EBF0CA00E9EFCA00E6EDCA00E5EC
      CB00DDE1C000B29C7800BAAE6B00000000000000000000000000000000000000
      0000000000000000000000000000000000000000000000000000000000000000
      0000000000000000000000000000000000000000000000000000000000000000
      0000000000000000000000000000000000000000000000000000000000000000
      0000000000000000000000000000000000000000000000000000000000000000
      0000000000000000000000000000000000000000000000000000000000000000
      0000000000000000000000000000000000000000000000000000000000000000
      0000000000000000000000000000000000000000000000000000000000000000
      0000000000000000000000000000000000000000000000000000000000000000
      0000000000000000000000000000000000000000000000000000000000000000
      0000000000000000000000000000000000000000000000000000000000000000
      0000000000000000000000000000000000000000000000000000000000000000
      0000000000000000000000000000000000000000000000000000000000000000
      000000000000000000000000000000000000424D3E000000000000003E000000
      2800000050000000140000000100010000000000F00000000000000000000000
      000000000000000000000000FFFFFF00FFFFFFFFFFE0003000000000FC03FFFF
      FFE0003000000000F801FFC7FFE0003000000000F000FF83FFE0003000000000
      E0007F01FFE0003000000000C0003E00FFE000300000000080003C007FE00030
      00000000800018003FE0003000000000800018101FE000300000000080001838
      0FE000300000000080001C7C07E000300000000080001FFE03E0003000000000
      80001FFF01E000300000000080003FFF81E0003000000000C0003FFFC3E00030
      00000000E0007FFFE7E0003000000000E000FFFFFFE0007000000000F801FFFF
      FFE000F000000000FC07FFFFFFE001F000000000FFFFFFFFFFFFFFF000000000
      00000000000000000000000000000000000000000000}
  end
  inherited LspS049: TBrvListParam
    Left = 696
    Top = 0
  end
  object PopSnTodos: TPopupMenu
    Left = 418
    Top = 134
    object MarcarTodos1: TMenuItem
      Caption = 'Marcar Todos'
      OnClick = MarcarTodos1Click
    end
    object Desmarcartodos1: TMenuItem
      Caption = 'Desmarcar todos'
      OnClick = Desmarcartodos1Click
    end
  end
  object CdsFatura: TBrvClientDataSet
    Aggregates = <>
    Params = <>
    BeforeInsert = CdsFaturaBeforeInsert
    AfterPost = CdsFaturaAfterPost
    BeforeDelete = CdsFaturaBeforeInsert
    BrShowFieldName = False
    BrQueryCode = 0
    BrDicionario = DmSrvApl.BrvDicionario
    BrType = VqNormal
    BrGravaLog = False
    BrUserCode = 0
    Left = 530
    Top = 134
  end
  object DtsFatura: TDataSource
    DataSet = CdsFatura
    Left = 586
    Top = 134
  end
  object CdsNotas: TBrvClientDataSet
    Aggregates = <>
    Params = <>
    BrShowFieldName = False
    BrQueryCode = 0
    BrDicionario = DmSrvApl.BrvDicionario
    BrType = VqNormal
    BrGravaLog = False
    BrUserCode = 0
    Left = 474
    Top = 134
    object CdsNotasDsModeNf: TStringField
      FieldName = 'DsModeNf'
      Size = 3
    end
    object CdsNotasNrSeriNf: TStringField
      FieldName = 'NrSeriNf'
      Size = 6
    end
    object CdsNotasNrNota: TIntegerField
      FieldName = 'NrNota'
    end
  end
  object CdsNotasTemp: TClientDataSet
    Aggregates = <>
    Params = <>
    Left = 640
    Top = 136
  end
end
