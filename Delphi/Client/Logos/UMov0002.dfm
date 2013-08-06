inherited Mov0002: TMov0002
  Left = 336
  Top = 129
  Caption = 'Mov0002 - Montar Carga'
  ClientHeight = 514
  ClientWidth = 961
  Position = poDesktopCenter
  ExplicitTop = -58
  ExplicitWidth = 969
  ExplicitHeight = 541
  PixelsPerInch = 96
  TextHeight = 13
  inherited NavBarra: TBrvDbNavCop
    Width = 961
    ExplicitWidth = 961
    inherited BrvSpeedButton1: TBrvSpeedButton
      Left = 929
      ExplicitLeft = 929
    end
    inherited SbtAjuda: TBrvSpeedButton
      Left = 904
      ExplicitLeft = 904
    end
  end
  inherited PnlFundo: TPanel
    Width = 961
    Height = 484
    ExplicitWidth = 961
    ExplicitHeight = 484
    object PgcFundo: TPageControl
      Left = 1
      Top = 1
      Width = 955
      Height = 478
      ActivePage = TbsFiltro
      Align = alClient
      TabOrder = 0
      TabStop = False
      object TbsFiltro: TTabSheet
        Caption = 'TbsFiltro'
        object PnlEmpres: TPanel
          Left = 0
          Top = 0
          Width = 947
          Height = 40
          Align = alTop
          BorderStyle = bsSingle
          TabOrder = 0
          DesignSize = (
            943
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
            Left = 216
            Top = 8
            Width = 613
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
            Left = 835
            Top = 6
            Width = 100
            Height = 25
            Hint = 'Confirmar'
            Anchors = [akTop, akRight]
            Caption = '&Prosseguir'
            DoubleBuffered = True
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
              8000808000808000808000808000808000808000808000808000808000808000
              8080008080008080008080008080008080008080008080008080008080008080
              0080800080800080800080800080800080800080800080800080800080800080
              8000808000808000808000808000808000808000808000808000808000BB6D00
              C468009676008080008080008080008080008080008080008080008080008080
              008080008080008080008080008080008080008080008080A6A6A6ACACAC8C8C
              8C00808000808000808000808000808000808000808000808000808000808000
              808000808000808000808000808000808002C46F01BD6E00BB6E00BD6A009A75
              0080800080800080800080800080800080800080800080800080800080800080
              80008080008080008080008080AEAEAEA9A9A9A6A6A6A8A8A88E8E8E00808000
              8080008080008080008080008080008080008080008080008080008080008080
              00808000808006C17504C27202BE7001BC6F00BA6D00C0680092780080800080
              8000808000808000808000808000808000808000808000808000808000808000
              8080ACACACADADADA9A9A9A7A7A7A6A6A6AAAAAA888888008080008080008080
              0080800080800080800080800080800080800080800080800080800EC97709C4
              7506C27504C07202BE7101BC6F00BA6D00C169009A7600808000808000808000
              8080008080008080008080008080008080008080008080B4B4B4AFAFAFADADAD
              ABABABA9A9A9A8A8A8A6A6A6A9A9A98E8E8E0080800080800080800080800080
              8000808000808000808000808000808017CA7B11C8790DC37708C37605C17404
              C17201BE7000BB6E00BB6D00BD69009677008080008080008080008080008080
              008080008080008080008080B5B5B5B3B3B3AFAFAFAEAEAEACACACACACACA9A9
              A9A6A6A6A5A5A5A8A8A88B8B8B00808000808000808000808000808000808000
              808000808024CB7D1DCB7D16C87B12C6790CC47809C57607C77504C07202BE70
              01BC6F00BA6D00C1690096760080800080800080800080800080800080800080
              80B6B6B6B6B6B6B3B3B3B1B1B1AFAFAFAFAFAFB1B1B1ABABABAAAAAAA7A7A7A6
              A6A6A9A9A98B8B8B00808000808000808000808000808000808000808030D481
              23CB7F1DC97D17C87B12CA790BB57900808007C77404C07302BE7101BB6F00BB
              6E00BD6A009A75008080008080008080008080008080008080BEBEBEB6B6B6B4
              B4B4B3B3B3B5B5B5A4A4A4008080B1B1B1ABABABAAAAAAA7A7A7A6A6A6A7A7A7
              8E8E8E00808000808000808000808000808000808030CC822CD08123CA7E1FCD
              7C14C07C00808000808000808007CB7304C07202BE7001BC6F00BA6D00C06800
              9278008080008080008080008080008080B7B7B7BABABAB5B5B5B7B7B7ADADAD
              008080008080008080B3B3B3ABABABA9A9A9A7A7A7A5A5A5AAAAAA8989890080
              800080800080800080800080800080802FC98331D7821FC27E00808000808000
              808000808000808007CC7404C07202BE7101BC6F00BA6D00C169009A76008080
              008080008080008080008080B4B4B4C0C0C0AEAEAE0080800080800080800080
              80008080B4B4B4ABABABAAAAAAA7A7A7A6A6A6A9A9A98F8F8F00808000808000
              8080008080008080008080008080008080008080008080008080008080008080
              00808006C77404C17201BE7000BB6E00BB6D00BD690096770080800080800080
              80008080008080008080008080008080008080008080008080008080008080B1
              B1B1ACACACA9A9A9A6A6A6A6A6A6A8A8A88C8C8C008080008080008080008080
              00808000808000808000808000808000808000808000808000808000808007CC
              7404C07202BE7001BC6F00BA6D00C16900987600808000808000808000808000
              8080008080008080008080008080008080008080008080008080B4B4B4ABABAB
              A9A9A9A7A7A7A5A5A5AAAAAA8D8D8D0080800080800080800080800080800080
              8000808000808000808000808000808000808000808000808007C77404C07302
              BE7101BB6F00BD6D00B36D008080008080008080008080008080008080008080
              008080008080008080008080008080008080008080B1B1B1ACACACAAAAAAA7A7
              A7A7A7A7A1A1A100808000808000808000808000808000808000808000808000
              808000808000808000808000808000808000808007CB7304C07202BE7002C66C
              0080800080800080800080800080800080800080800080800080800080800080
              80008080008080008080008080008080B3B3B3ABABABA9A9A9AEAEAE00808000
              8080008080008080008080008080008080008080008080008080008080008080
              00808000808000808000808000808007CE7405C7710080800080800080800080
              8000808000808000808000808000808000808000808000808000808000808000
              8080008080008080008080B6B6B6B0B0B0008080008080008080008080008080
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
            OnClick = BtnProsseClick
            BrTipoBotao = BrBtnOk
          end
        end
        object PnlFiltro: TPanel
          Left = 0
          Top = 40
          Width = 947
          Height = 410
          Align = alClient
          BorderStyle = bsSingle
          TabOrder = 1
          Visible = False
          DesignSize = (
            943
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
            Left = 216
            Top = 83
            Width = 719
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
            TabOrder = 7
          end
          object LblRsDestin: TBrvRetCon
            Left = 216
            Top = 59
            Width = 719
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
            TabOrder = 5
          end
          object LblRsTomado: TBrvRetCon
            Left = 216
            Top = 35
            Width = 719
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
          object LblDsPraca: TBrvRetCon
            Left = 216
            Top = 11
            Width = 719
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
          object EdtCdRota: TBrvEditNum
            Left = 122
            Top = 11
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
            Left = 276
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
            TabOrder = 6
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
            Top = 370
            Width = 941
            Height = 35
            Align = alBottom
            BorderStyle = bsSingle
            Font.Charset = DEFAULT_CHARSET
            Font.Color = clWindowText
            Font.Height = -11
            Font.Name = 'MS Sans Serif'
            Font.Style = [fsBold]
            ParentFont = False
            TabOrder = 13
            object BrvBitBtn1: TBrvBitBtn
              Left = 728
              Top = 3
              Width = 100
              Height = 25
              Hint = 'Localizar'
              Align = alCustom
              Anchors = [akTop, akRight]
              Caption = '&Pesquisar'
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
              OnClick = BrvBitBtn1Click
              BrTipoBotao = BrBtnLocalizar
            end
            object BtnCancelar: TBrvBitBtn
              Left = 832
              Top = 3
              Width = 100
              Height = 25
              Hint = 'Cancelar'
              Align = alCustom
              Anchors = [akTop, akRight]
              Caption = '&Cancelar'
              DoubleBuffered = True
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
              OnClick = BtnCancelarClick
              BrTipoBotao = BrBtnCancel
            end
          end
          object CblCdEstado: TBrvCheckListBox
            Left = 122
            Top = 160
            Width = 813
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
            Width = 813
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
            TabOrder = 10
          end
          object GroupBox10: TGroupBox
            Left = 122
            Top = 271
            Width = 813
            Height = 94
            Caption = 'Vencimento dos conhecimentos em carga e n'#227'o entregues'
            Font.Charset = DEFAULT_CHARSET
            Font.Color = clWindowText
            Font.Height = -11
            Font.Name = 'Tahoma'
            Font.Style = []
            ParentFont = False
            TabOrder = 12
            DesignSize = (
              813
              94)
            object Label20: TLabel
              Left = 24
              Top = 21
              Width = 4
              Height = 16
              Caption = #39
              DragCursor = crHandPoint
              Font.Charset = DEFAULT_CHARSET
              Font.Color = clWindowText
              Font.Height = -13
              Font.Name = 'Tahoma'
              Font.Style = [fsBold, fsUnderline]
              ParentFont = False
              OnClick = Label20Click
            end
            object Label21: TLabel
              Left = 24
              Top = 42
              Width = 4
              Height = 16
              Caption = #39
              DragCursor = crHandPoint
              Font.Charset = DEFAULT_CHARSET
              Font.Color = clWindowText
              Font.Height = -13
              Font.Name = 'Tahoma'
              Font.Style = [fsBold, fsUnderline]
              ParentFont = False
              OnClick = Label21Click
            end
            object Label23: TLabel
              Left = 24
              Top = 63
              Width = 4
              Height = 16
              Caption = #39
              DragCursor = crHandPoint
              Font.Charset = DEFAULT_CHARSET
              Font.Color = clWindowText
              Font.Height = -13
              Font.Name = 'Tahoma'
              Font.Style = [fsBold, fsUnderline]
              ParentFont = False
              OnClick = Label23Click
            end
            object BrvBitBtn16: TBrvBitBtn
              Left = 718
              Top = 60
              Width = 85
              Height = 25
              Hint = 'Cancelar'
              Anchors = [akTop, akRight]
              Caption = '&Atualizar'
              DoubleBuffered = True
              Font.Charset = DEFAULT_CHARSET
              Font.Color = clWindowText
              Font.Height = -11
              Font.Name = 'Tahoma'
              Font.Style = [fsBold]
              Glyph.Data = {
                96090000424D9609000000000000360000002800000028000000140000000100
                18000000000060090000C40E0000C40E00000000000000000000FFFFFFFFFFFF
                FFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFF
                FFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFF
                FFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFF
                FFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFF
                FFFFFFFFFFFFFF74C96358C45958C45958C45958C459FFFFFFFFFFFFFFFFFFFF
                FFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFF
                FFFFFFA99B9B969797969797969797969797FFFFFFFFFFFFFFFFFFFFFFFFFFFF
                FFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFF74C96358C45973
                C75E61C55B5FC55B5FC55B6BC65D79C75E6FCE6DFFFFFFFFFFFFFFFFFFFFFFFF
                FFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFA99B9B969797AB9B9B9D98
                989C98989C9898A59A9AB09B9BA8A7A7FFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFF
                FFFFFFFFFFFFFFFFFFFFFFFFFFFF74C96370C75E5EC65C5DC65D5CC65C5CC65C
                5CC65C5CC65C5CC65C67C65D6BC75EFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFF
                FFFFFFFFFFFFFFFFFFFFA99B9BA99B9B9B99999A9A9A99999999999999999999
                9999999999A29A9AA59B9BFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFF
                FFFFFF74C9636FC75F5DC65D5DC65D5DC65D5DC65D5CC65C5DC65D5DC65D5DC6
                5D5DC65D5FC65D83C963FFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFA9
                9B9BA89C9C9A9A9A9A9A9A9A9A9A9A9A9A9999999A9A9A9A9A9A9A9A9A9A9A9A
                9C9A9AB79F9FFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFF74C96374C96361C8
                615FC75F61C86161C86161C75F64C96261C75F5FC75F61C8615FC75F5FC75F61
                C75F70C963FFFFFFFFFFFFFFFFFFFFFFFFFFFFFFA99B9BAC9F9F9D9D9D9C9C9C
                9D9D9D9D9D9D9D9C9CA09E9E9D9C9C9C9C9C9D9D9D9C9C9C9C9C9C9D9C9CA99F
                9FFFFFFFFFFFFFFFFFFFFFFFFFFFFFFF61C96266C96361C86162C96262C96269
                CA646CCA6459C86162C96270CB6662C96261C86162C96262C96271CB666FCE6D
                FFFFFFFFFFFFFFFFFFFFFFFF9D9E9EA19F9F9D9D9D9E9E9E9E9E9EA4A0A0A6A0
                A0979D9D9E9E9EA9A1A19E9E9E9D9D9D9E9E9E9E9E9EAAA1A1A8A7A7FFFFFFFF
                FFFFFFFFFF3EC75E80CD6B66CB6664CA6466CB666BCB663DC65CFFFFFFFFFFFF
                FFFFFF20C1548BCE6D7CCC6971CC686BCB6671CB676FCE6DFFFFFFFFFFFFFFFF
                FFB5A5A5B5A5A5A1A1A1A0A0A0A1A1A1A5A1A17D9999FFFFFFFFFFFFFFFFFF5A
                9292BCA7A7B2A4A4AAA3A3A5A1A1AAA2A2A8A7A7FFFFFFFFFFFFFFFFFF3EC75E
                70CC6967CB6769CC6969CC6871CC69FFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFF
                FFFFFFFF1FC2564AC86144C75F58CA64FFFFFFFFFFFFFFFFFF7E9B9BA9A4A4A2
                A2A2A4A4A4A4A3A3AAA4A4FFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFF
                599494899D9D849C9C96A0A0FFFFFFFFFFFFFFFFFF47C96271CE6C6BCD6B6BCD
                6B6FCE6C61CC68FFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFF
                FFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFF879E9EAAA6A6A5A5A5A5A5A5A8A6A6
                9DA3A3FFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFF
                FFFFFFFFFFFFFFFFFFFFFFFFFF54CB6774CF6F6FCF6F6DCE6D70CF6F6DCE6DFF
                FFFFFFFFFFFFFFFF6FCE6D97D3778CD2758CD2758BD1748CD2759AD3776FCE6D
                FFFFFFFFFFFFFFFFFF92A2A2ACA8A8A8A8A8A7A7A7A9A8A8A7A7A7FFFFFFFFFF
                FFFFFFFFA8A7A7C4AEAEBDADADBDADADBCACACBDADADC6AEAEA8A7A7FFFFFFFF
                FFFFFFFFFF27C75E88D37771D07171D07170D0707FD17423C459FFFFFFFFFFFF
                FFFFFF56CC6977D17371D07171D07170D0707FD1746FCE6DFFFFFFFFFFFFFFFF
                FF92A2A2BAAEAEAAAAAAAAAAAAA9A9A9B4ACAC5E9797FFFFFFFFFFFFFFFFFF94
                A4A4AEABABAAAAAAAAAAAAA9A9A9B4ACACA8A7A7FFFFFFFFFFFFFFFFFFFFFFFF
                A0D78074D17475D27575D27575D27585D37871D17415C25539CA6483D47977D3
                7775D27575D27575D27583D3786FCE6DFFFFFFFFFFFFFFFFFFFFFFFFCAB5B5AC
                ACACADADADADADADADADADB8AFAFAAACAC4A939379A0A0B7B0B0AEAEAEADADAD
                ADADADADADADB7AFAFA8A7A7FFFFFFFFFFFFFFFFFFFFFFFF27C75E88D57C78D3
                7878D37878D37879D4797CD47988D57C82D47B78D37878D37878D37878D37878
                D37889D67E6FCE6DFFFFFFFFFFFFFFFFFFFFFFFF649B9BBAB2B2AFAFAFAFAFAF
                AFAFAFB0B0B0B2B0B0BAB2B2B6B1B1AFAFAFAFAFAFAFAFAFAFAFAFAFAFAFBBB3
                B3A8A7A7FFFFFFFFFFFFFFFFFFFFFFFFFFFFFF94D78282D67E7BD47B7BD47B7B
                D47B7BD47B7BD47B7BD47B7BD47B7BD47B7BD47B7BD47B82D67E8BD67F6FCE6D
                FFFFFFFFFFFFFFFFFFFFFFFFFFFFFFC2B6B6B6B3B3B1B1B1B1B1B1B1B1B1B1B1
                B1B1B1B1B1B1B1B1B1B1B1B1B1B1B1B1B1B1B1B6B3B3BCB4B4A8A7A7FFFFFFFF
                FFFFFFFFFFFFFFFFFFFFFFFFFFFF7ED67E86D67F7FD67F7ED67E7ED67E7FD67F
                7ED67E7FD67F7FD67F82D67F97D9854ACF6F9ED9866FCE6DFFFFFFFFFFFFFFFF
                FFFFFFFFFFFFFFFFFFFFB3B3B3B9B4B4B4B4B4B3B3B3B3B3B3B4B4B4B3B3B3B4
                B4B4B4B4B4B6B4B4C4B8B889A8A8C9B9B9A8A7A7FFFFFFFFFFFFFFFFFFFFFFFF
                FFFFFFFFFFFFFFFFFF8ED9858ED98592D98683D78280D78080D7808ED98595DA
                8886D8831DC65CFFFFFF67D4796FCE6DFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFF
                FFFFFFFFFFBEB8B8BEB8B8C1B9B9B7B6B6B5B5B5B5B5B5BEB8B8C3BABAB9B7B7
                569999FFFFFFA2B0B0A8A7A7FFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFF
                FFFFFFFFFFFFFF7BD47B97DA88A2DB8BAFDD8F7BD47B26C861FFFFFFFFFFFFFF
                FFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFF
                FFFFFFB1B1B1C4BABACBBCBCD3BFBFB1B1B1629D9DFFFFFFFFFFFFFFFFFFFFFF
                FFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFF
                FFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFF
                FFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFF
                FFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFF
                FFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFF
                FFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFF
                FFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFF
                FFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFF}
              NumGlyphs = 2
              ParentDoubleBuffered = False
              ParentFont = False
              ParentShowHint = False
              ShowHint = True
              TabOrder = 0
              OnClick = BrvBitBtn16Click
              BrTipoBotao = BrBtnAtualizar
            end
          end
        end
      end
      object TbsProces: TTabSheet
        Caption = 'TbsProces'
        ImageIndex = 1
        object Splitter2: TSplitter
          Left = 0
          Top = 150
          Width = 947
          Height = 5
          Cursor = crVSplit
          Align = alTop
          ExplicitTop = 153
          ExplicitWidth = 924
        end
        object Panel3: TPanel
          Left = 0
          Top = 0
          Width = 947
          Height = 150
          Align = alTop
          BorderStyle = bsSingle
          TabOrder = 0
          object Panel10: TPanel
            Left = 1
            Top = 1
            Width = 941
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
            Width = 941
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
          Width = 947
          Height = 271
          ActivePage = TbsTomado
          Align = alClient
          TabOrder = 2
          object TbsTomado: TTabSheet
            Caption = 'TbsTomado'
            ImageIndex = 2
            object Panel4: TPanel
              Left = 0
              Top = 157
              Width = 939
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
                935
                82)
              object LblQtTomado: TLabel
                Left = 854
                Top = 4
                Width = 76
                Height = 13
                Alignment = taRightJustify
                Anchors = [akTop, akRight]
                Caption = 'LblQtTomado'
              end
              object Panel12: TPanel
                Left = 1
                Top = 52
                Width = 933
                Height = 29
                Align = alBottom
                BevelOuter = bvNone
                BiDiMode = bdLeftToRight
                Font.Charset = DEFAULT_CHARSET
                Font.Color = clWindowText
                Font.Height = -11
                Font.Name = 'MS Sans Serif'
                Font.Style = [fsBold]
                ParentBiDiMode = False
                ParentFont = False
                TabOrder = 2
                object BrvBitBtn6: TBrvBitBtn
                  Left = 829
                  Top = 0
                  Width = 100
                  Height = 25
                  Hint = 'Voltar'
                  Align = alCustom
                  Anchors = [akTop, akRight]
                  Caption = '&Voltar'
                  DoubleBuffered = True
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
                    8000808000808000808000808000808000808000808000808000808000808000
                    8080008080008080008080008080008080008080008080008080008080008080
                    0080800080800080800080800080800080800080800080800080800080800080
                    8000808000808000808000808000808000808000808000808000808000808000
                    8080637D5BD67B2F008080008080008080008080008080008080008080008080
                    0080800080800080800080800080800080800080800080800080800080804579
                    7997727200808000808000808000808000808000808000808000808000808000
                    808000808000808000808000808000808000808000808065815FB88143C08141
                    0080800080800080800080800080800080800080800080800080800080800080
                    80008080008080008080008080008080008080487C7C8378788A797900808000
                    8080008080008080008080008080008080008080008080008080008080008080
                    0080800080800080800080805B8467C28748AF874EC488480080800080800080
                    8000808000808000808000808000808000808000808000808000808000808000
                    80800080800080804280808D7E7E8080808F7F7F008080008080008080008080
                    0080800080800080800080800080800080800080800080800080800080800080
                    80778A65C78F53B48E58B48E58C28F5400808000808000808000808000808000
                    8080008080008080008080008080008080008080008080008080008080598484
                    9486868686868686869187870080800080800080800080800080800080800080
                    800080800080800080800080800080800080800080805F8A6ECB955AB7935EB7
                    935EB7935EB7935EC5945BC5945BC5945BC5945BC5945BC5945BC5945BC5945B
                    D496580080800080800080800080800080804986869A8C8C8B8B8B8B8B8B8A8A
                    8A8B8B8B968C8C968C8C968C8C958B8B968C8C968C8C968C8C968C8CA08C8C00
                    80800080800080800080806E8C6FCF985FBA9563BA9563BA9563BA9563BA9563
                    BA9563BA9563BA9563BA9563BA9563BA9563BA9563BA9563C997600080800080
                    800080800080805488889D8F8F8E8E8E8D8D8D8E8E8E8E8E8E8D8D8D8D8D8D8D
                    8D8D8D8D8D8E8E8E8D8D8D8D8D8D8E8E8E8D8D8D988E8E008080008080008080
                    708F71CD9B64BD9966BD9966BD9966BD9966BD9966BD9966BD9966BD9966BD99
                    66BD9966BD9966BD9966BD9966BD9966CB9B64008080008080008080568A8A9D
                    9292919191919191919191919191919191919191919191919191919191919191
                    9191919191919191919191919C92920080800080806E9073D49F67BE9C6ABE9C
                    6ABE9C6ABE9C6ABE9C6ABE9C6ABE9C6ABE9C6ABE9C6ABE9C6ABE9C6ABE9C6ABE
                    9C6ABE9C6ABE9C6ACD9E68008080008080568C8CA59696949494949494949494
                    9494949494949494949494949494949494949494949393939494949393939494
                    949494949F9595008080008080709175D7A26BC19E6DC19E6DC19E6DC19E6DC1
                    9E6DC19E6DC19E6DC19E6DC19E6DC19E6DC19E6DC19E6DC19E6DC19E6DC19E6D
                    D0A16C008080008080578D8DA899999796969696969696969696969696969696
                    96969696969696969696969696969696969696969696969696969696A2989800
                    8080008080008080739377D3A46FC3A170C2A170C2A170C2A170C2A170C2A170
                    C2A170C2A170C2A170C2A170C2A170C2A170C2A170C2A170D1A46F0080800080
                    800080805A8F8FA69B9B99989899999999999999999999999998989899999999
                    9999999999999999999999999999989898999999A59B9B008080008080008080
                    008080759578DCA872C5A474C5A474C5A474C5A474C5A474C5A474C5A474C5A4
                    74C5A474C5A474C5A474C5A474C5A474D4A6730080800080800080800080805C
                    9090AD9E9E9B9B9B9B9B9B9C9C9C9C9C9C9C9C9C9C9C9C9C9C9C9C9C9C9C9C9C
                    9C9C9C9B9B9B9C9C9C9C9C9CA89E9E0080800080800080800080800080806894
                    7ADEAA76C8A677C8A677C8A677C8A677D8A976D8A976D8A976D8A976D8A976D8
                    A976D8A976D8A976E7AC76008080008080008080008080008080528F8FAFA1A1
                    9E9E9E9E9E9E9E9E9E9E9E9EAAA0A0AAA0A0ABA1A1AAA0A0AAA0A0ABA1A1AAA0
                    A0AAA0A0B7A3A3008080008080008080008080008080008080869A7CDFAC79C9
                    A779C9A779D9AA79008080008080008080008080008080008080008080008080
                    008080008080008080008080008080008080008080699494B1A3A39F9F9FA0A0
                    A0ACA2A200808000808000808000808000808000808000808000808000808000
                    808000808000808000808000808000808000808069957EE1AE7CCAA97CE3AE7C
                    0080800080800080800080800080800080800080800080800080800080800080
                    80008080008080008080008080008080539191B4A5A5A1A1A1B5A5A500808000
                    8080008080008080008080008080008080008080008080008080008080008080
                    00808000808000808000808000808078997EDDAE7DE5B07D0080800080800080
                    8000808000808000808000808000808000808000808000808000808000808000
                    8080008080008080008080619595B0A5A5B7A7A7008080008080008080008080
                    0080800080800080800080800080800080800080800080800080800080800080
                    8000808000808000808079997FFFB77D00808000808000808000808000808000
                    8080008080008080008080008080008080008080008080008080008080008080
                    008080008080609595D2ADAD0080800080800080800080800080800080800080
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
                  TabOrder = 2
                  OnClick = BrvBitBtn6Click
                  BrTipoBotao = BrBtnSetaEsquerda
                end
                object BrvBitBtn7: TBrvBitBtn
                  Left = 621
                  Top = 0
                  Width = 100
                  Height = 25
                  Hint = 'Detalhar'
                  Align = alCustom
                  Anchors = [akTop, akRight]
                  Caption = '&Detalhar'
                  DoubleBuffered = True
                  Font.Charset = DEFAULT_CHARSET
                  Font.Color = clWindowText
                  Font.Height = -11
                  Font.Name = 'Tahoma'
                  Font.Style = [fsBold]
                  Glyph.Data = {
                    96090000424D9609000000000000360000002800000028000000140000000100
                    1800000000006009000000000000000000000000000000000000008080182A2A
                    251A1A0B52520080800080800080800080800080800080800080800080800080
                    800080800080800080800080800080800080800080800080802D2D2D20202050
                    5050008080008080008080008080008080008080008080008080008080008080
                    008080008080008080008080008080008080104B4B2E24243B3B3B2F1F1F0C51
                    5100808000808000808000808000808000808000808000808000808000808000
                    80800080800080800080800080804A4A4A2929293E3E3E252525505050008080
                    0080800080800080800080800080800080800080800080800080800080800080
                    800080800080800080801259594234345B5B5B5B5B5B3524240B555500808000
                    8080008080008080008080008080008080008080008080008080008080008080
                    0080800080805757573838385C5C5C5B5B5B2A2A2A5454540080800080800080
                    8000808000808000808000808000808000808000808000808000808000808000
                    80800080802B50505045456363635E5E5E3A28280C5959008080008080008080
                    0080800080800080800080800080800080800080800080800080800080800080
                    804F4F4F4747476363635F5F5F2D2D2D57575700808000808000808000808000
                    8080008080008080008080008080008080008080008080008080008080008080
                    315454584D4D6868686261613B2D310D5D5D00808061632159672C855A005C66
                    295F64230080800080800080800080800080800080800080800080805353534E
                    4E4E6868686161613232325A5A5A0080805959595D5D5D5454545C5C5C5B5B5B
                    0080800080800080800080800080800080800080800080800080803857575D53
                    5365676D564F3D7A570689600586650C8B67098B6B0B8C680987650B89600576
                    6212008080008080008080008080008080008080008080575757545454686868
                    4D4D4D5252525A5A5A5D5D5D5F5F5F6262626060605D5D5D5A5A5A5959590080
                    800080800080800080800080800080800080800080803E5B5E5E534081640889
                    6A1191710D8B6A04997F2F9D87419B81358C6C0A9170098C6B0F8C6206008080
                    0080800080800080800080800080800080805B5B5B5151515B5B5B6161616767
                    676161617474747C7C7C7676766363636666666262625B5B5B00808000808000
                    80800080800080800080800080800080807E661090711396740B927A2DD4CDB4
                    E3DECCE7E2D2E4DFCEDBD4BFA38F528E6C019473149264010080800080800080
                    800080800080800080800080805D5D5D6868686A6A6A707070C7C7C7DADADADE
                    DEDEDBDBDBCFCFCF8585856363636969695D5D5D008080008080008080008080
                    0080800080800080809E720B9F7C118D7428EAE5D3E6DFC8E3DCC4E3DBC3E3DB
                    C3E5DDC6ECE6D5A49157967308A0771000808000808000808000808000808000
                    80800080806969697171716A6A6AE0E0E0DADADAD6D6D6D5D5D5D5D5D5D7D7D7
                    E2E2E28787876969696D6D6D6565650080800080800080800080800080800080
                    80A88216886600E5DEC8E8E0C7E5DDC2E5DCC2E5DCC2E5DCC2E5DDC2E7DEC4EB
                    E5D191751FA58116A16C02008080008080008080008080008080008080777777
                    5D5D5DD9D9D9DADADAD6D6D6D6D6D6D6D6D6D6D6D6D7D7D7D8D8D8E0E0E06B6B
                    6B7575756464640080800080800080800080800080808D7D22AE87139C853FF3
                    ECD4E9DFC2EAE2C7EBE3C9ECE4CBECE4CAEBE2C8E9E0C2EFE8CEB5A470A17E0E
                    B47F0B0080800080800080800080800080807171717B7B7B7B7B7BE7E7E7D9D9
                    D9DCDCDCDDDDDDDEDEDEDEDEDEDCDCDCDADADAE2E2E29A9A9A7373737575757D
                    7D7D008080008080008080008080B98612B18B14AA9860F4EBD0F0E8CEF0E8CF
                    F0E8CFF0E8CFF0E8CFF0E8CFF0E8CFF1E8CDC7BA91A07C0BBF8F160080800080
                    800080800080800080807B7B7B7E7E7E8E8E8EE5E5E5E2E2E2E3E3E3E2E2E2E3
                    E3E3E2E2E2E2E2E2E2E2E2E3E3E3B1B1B1717171838383686868008080008080
                    008080008080BD8E17BC9416AA975BFAF2DAF4ECD4F4ECD3F4ECD3F4ECD3F4EC
                    D3F4ECD3F4ECD3F9F1D9C8B98CA9840ECA981700808000808000808000808000
                    80808282828686868D8D8DEDEDEDE7E7E7E6E6E6E7E7E7E6E6E6E6E6E6E7E7E7
                    E7E7E7ECECECB0B0B07878788B8B8B6B6B6B008080008080008080008080A18D
                    26CD9F1C9C8030FFFBE8F7EFD7F6EFD6F7EFD6F6EFD6F7EFD6F7EFD6F7EFD6FF
                    F9E4B39F62BC9416D19610008080008080008080008080008080808080919191
                    757575F7F7F7EAEAEAEAEAEAE9E9E9EAEAEAE9E9E9EAEAEAE9E9E9F5F5F59494
                    948686868A8A8A7D7D7D008080008080008080008080008080DFAE22A37D07E7
                    DEC3FFF8E3FAF2DAF9F2DAFAF2DAFAF2DAFAF2DAFCF5DFF7F1DDA0801CD8AB22
                    B389150080800080800080800080800080807171719E9E9E727272D8D8D8F3F3
                    F3ECECECEDEDEDEDEDEDEDEDEDEDEDEDF1F1F1ECECEC7474749C9C9C7D7D7D00
                    8080008080008080008080008080008080EBB11ED7AB22977A1FF7F1E2FFFCEA
                    FDF5E0FCF4DEFCF6E0FFFAE6FFFDEFA28C46C89E18F5B9210080800080800080
                    80008080008080008080008080A2A2A29C9C9C707070EEEEEEF8F8F8F0F0F0F0
                    F0F0F1F1F1F6F6F6FAFAFA818181909090A9A9A96D6D6D008080008080008080
                    008080008080008080008080FFC727D6A91EA38525D2C7A5FCF8E8FFFFF6FFFC
                    EEDCD4B9AA8F3FCA9D16F8C329B98E1600808000808000808000808000808000
                    8080008080008080B5B5B59A9A9A797979BFBFBFF5F5F5FDFDFDF9F9F9CECECE
                    8383838F8F8FB1B1B18181810080800080800080800080800080800080800080
                    80008080008080FFD228E6B626C69A16B18E21A38421AD8B21C09617E1B121FF
                    CC29CEA41C008080008080008080008080008080008080008080008080008080
                    008080BEBEBEA6A6A68C8C8C8181817878787E7E7E898989A1A1A1BABABA9595
                    9500808000808000808000808000808000808000808000808000808000808000
                    8080FFC81BFFDB2CFDC527F5C129F9C327FFD72BFFD4228A9130008080008080
                    008080008080008080008080008080008080008080008080008080008080B6B6
                    B6C6C6C6B3B3B3B0B0B0B2B2B2C3C3C3C0C0C081818100808000808000808000
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
                  OnClick = BrvBitBtn7Click
                  BrTipoBotao = BrBtnPesqui
                end
                object BrvBitBtn11: TBrvBitBtn
                  Left = 725
                  Top = 0
                  Width = 100
                  Height = 25
                  Hint = 'Carga'
                  Align = alCustom
                  Anchors = [akTop, akRight]
                  Caption = '&Carga'
                  DoubleBuffered = True
                  Font.Charset = DEFAULT_CHARSET
                  Font.Color = clWindowText
                  Font.Height = -11
                  Font.Name = 'Tahoma'
                  Font.Style = [fsBold]
                  Glyph.Data = {
                    96090000424D9609000000000000360000002800000028000000140000000100
                    18000000000060090000C30E0000C30E00000000000000000000408080408080
                    4080804080804080804080804080804080804080804080804080804080804080
                    8040808040808040808040808040808040808040808040808040808040808040
                    8080408080408080408080408080408080408080408080408080408080408080
                    408080408080408080408080408080408080408080408080A7AF85D2B587CCB5
                    87CCB586CAB486D3B587D3B587D8B588D3B587D8B588D3B587D8B587D3B487D3
                    B487DBB5889DAC824080804080804080804080804A92928FA2A288A0A0869E9E
                    869E9E859D9D839B9B8199998199998098987F97977E96967D95957C94948396
                    96438B8B408080408080408080A7AF85DBB588DBB588DBB588DBB588DBB588D8
                    B588DBB588DBB588DBB588DBB588DBB588DBB588DBB588DBB588DBB588D8B587
                    9DAC824080804080804B9393C3B1B1AEAAAAAEAAAAACA8A8ABA7A7A9A5A5A8A4
                    A4A6A2A2A4A0A0A39F9FA39F9FA19D9DA09C9C9F9B9B9E9A9AAF9D9D438B8B40
                    8080408080D5B588DBB588DBB588D3B587D3B587D8B588D8B588DBB588DBB588
                    DBB588DBB588DBB588DBB588DBB588DBB588DBB588DBB588D8B5874080804080
                    8093A6A6B2AEAEABABABA9A9A9A9A9A9A7A7A7A6A6A6A3A3A3A3A3A3A1A1A1A0
                    A0A09F9F9F9E9E9E9C9C9C9C9C9C9A9A9A9E9A9A839696408080408080CFB587
                    DBB588DBB588D0B687DBB588DBB588DBB588DBB588DBB588DBB588D3B586D3B5
                    86D0B485D3B586DBB588DBB588DBB588D3B4874080804080808DA5A5B3AFAFAC
                    ACACAAAAAAA1A1A19E9E9E9E9E9E9C9C9C9A9A9A979797969696959595939393
                    9393939C9C9C9A9A9A9F9B9B7D9595408080408080D3B587DBB588DBB588DBB5
                    88FFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFD3
                    B586DBB588DBB588D3B4874080804080808FA7A7B4B0B0AEAEAEA5A5A5F8F8F8
                    FFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFF9F9F99494949D9D
                    9DA19D9D7D9595408080408080DBB588DBB588DBB588D2B587FFFFFFFFFFFFFF
                    FFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFD0B485DBB588DBB588
                    D8B58740808040808091A9A9B6B2B2AEAEAEA4A4A4FFFFFFF8F8F8F9F9F9F8F8
                    F8FAFAFAF9F9F9FAFAFAF9F9F9FAFAFAFFFFFF9292929C9C9CA29E9E7E969640
                    8080408080DBB588DBB588DBB588D5B587FFFFFFFFFFFFFFFFFFFFFFFFFFFFFF
                    FFFFFFFFFFFFFFFFFFFFFFFFFFFFFFD3B586DBB588DBB588D3B5874080804080
                    8091A9A9B7B3B3B0B0B0A5A5A5FFFFFFFAFAFAFAFAFAFAFAFAF9F9F9FAFAFAFA
                    FAFAFAFAFAFAFAFAFFFFFF9494949E9E9EA29E9E7F9797408080408080DBB588
                    DBB588DBB588D6B587FFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFF
                    FFFFFFFFFFFFFFD8B587DBB588DBB588D8B58840808040808092AAAAB8B4B4B2
                    B2B2A7A7A7FFFFFFFAFAFAFBFBFBFAFAFAFAFAFAFBFBFBFAFAFAFBFBFBFAFAFA
                    FFFFFF979797A0A0A0A4A0A0809898408080408080DBB588DBB588DBB588DBB5
                    88FFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFDA
                    B588D7B587DBB588D3B58740808040808093ABABBAB6B6B3B3B3A8A8A8FFFFFF
                    FBFBFBFCFCFCFBFBFBFBFBFBFBFBFBFBFBFBFBFBFBFBFBFBFFFFFF979797A0A0
                    A0A5A1A1809898408080408080DBB588DBB588DBB588DBB588FFFFFFFFFFFFFF
                    FFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFDBB588DBB588DBB588
                    DAB58840808040808095ADADBBB7B7B4B4B4A9A9A9FFFFFFFDFDFDFCFCFCFDFD
                    FDFCFCFCFDFDFDFBFBFBFDFDFDFCFCFCFFFFFF999999A3A3A3A6A2A2829A9A40
                    8080408080DBB588DBB588DBB588DBB588FFFFFFFFFFFFFFFFFFFFFFFFFFFFFF
                    FFFFFFFFFFFFFFFFFFFFFFFFFFFFFFDBB588DBB588DBB588DBB5884080804080
                    8095ADADBCB8B8B5B5B5AAAAAAFFFFFFFDFDFDFDFDFDFCFCFCFDFDFDFDFDFDFC
                    FCFCFCFCFCFEFEFEFFFFFF9B9B9BA3A3A3A8A4A4839B9B408080408080DBB588
                    DBB588DBB588DBB588FFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFF
                    FFFFFFFFFFFFFFDBB588DBB588DBB588DBB58840808040808095ADADBEBABAB6
                    B6B6ACACACFFFFFFFEFEFEFEFEFEFDFDFDFEFEFEFEFEFEFDFDFDFEFEFEFDFDFD
                    FFFFFF9C9C9CA6A6A6A9A5A5839B9B408080408080DBB588D3B688DBB588D6B5
                    87FFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFDB
                    B588DBB588DBB588DAB58840808040808097AFAFBFBBBBB8B8B8ADADADFFFFFF
                    FEFEFEFEFEFEFFFFFFFEFEFEFEFEFEFEFEFEFFFFFFFEFEFEFFFFFF9E9E9EA8A8
                    A8ABA7A7869E9E408080408080D6B688DBB588D8B688DBB588FFFFFFFFFFFFFF
                    FFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFDBB588DBB588DBB588
                    DAB58840808040808097AFAFBFBBBBB8B8B8B0B0B0FEFEFEFFFFFFFFFFFFFFFF
                    FFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFA0A0A0A8A8A8ACA8A8869E9E40
                    8080408080D8B588DBB588DBB588DBB588DBB588DBB588DBB588DBB588DBB588
                    DBB588DBB588DBB588DBB588DBB588DBB588DBB588DBB588DBB5884080804080
                    8099B1B1C1BDBDBABABAB9B9B9B0B0B0ADADADACACACAAAAAAA9A9A9A8A8A8A6
                    A6A6A5A5A5A3A3A3A3A3A3ABABABAAAAAAAEAAAA879F9F408080408080D8B588
                    DBB588DBB588DBB588D8B588DBB588DBB588DBB588DBB588D8B588DBB588DBB5
                    88DBB588DBB588DBB588DBB588DBB588DAB588408080408080A1B4B4C2BEBEBA
                    BABABBBBBBB8B8B8B7B7B7B6B6B6B5B5B5B3B3B3B3B3B3B2B2B2B1B1B1AEAEAE
                    ADADADADADADAAAAAAAFABAB8FA2A2408080408080A2AF86CABA88DBB588DBB5
                    88DBB588DBB588DBB588DBB588DBB588DBB588DBB588DBB588DBB588DBB588DB
                    B588DBB588DBB588189E7B408080408080539B9BD9C7C7C2BEBEC1BDBDBFBBBB
                    BFBBBBBDB9B9BCB8B8BBB7B7BAB6B6B9B5B5B7B3B3B6B2B2B3AFAFB3AFAFB1AD
                    ADC3B1B1499191408080408080408080A2AF87D8B588D5B588DBB588DBB588DB
                    B588DBB588DBB588DBB588DBB588DBB588DBB588DBB588DBB588D0B5871A9F7C
                    408080408080408080408080529B9BA1B4B498B0B098B0B097AFAF96AEAE95AD
                    AD93ABAB94ACAC92AAAA92AAAA90A8A88FA7A78EA6A694A7A74B949440808040
                    8080408080408080408080408080408080408080408080408080408080408080
                    4080804080804080804080804080804080804080804080804080804080804080
                    8040808040808040808040808040808040808040808040808040808040808040
                    8080408080408080408080408080408080408080408080408080}
                  NumGlyphs = 2
                  ParentDoubleBuffered = False
                  ParentFont = False
                  ParentShowHint = False
                  ShowHint = True
                  TabOrder = 1
                  OnClick = BrvBitBtn9Click
                  BrTipoBotao = BrBtnCarga
                end
              end
              object GroupBox6: TGroupBox
                Left = 6
                Top = 4
                Width = 523
                Height = 36
                Caption = 'Geral:'
                Font.Charset = DEFAULT_CHARSET
                Font.Color = clWindowText
                Font.Height = -11
                Font.Name = 'MS Sans Serif'
                Font.Style = [fsBold]
                ParentFont = False
                TabOrder = 0
                object Label54: TLabel
                  Left = 32
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
                  Left = 136
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
                  Left = 297
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
                  Left = 72
                  Top = 10
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
                  Left = 172
                  Top = 10
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
                  Left = 392
                  Top = 10
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
                Top = 41
                Width = 523
                Height = 36
                Caption = 'Marcados:'
                Font.Charset = DEFAULT_CHARSET
                Font.Color = clWindowText
                Font.Height = -11
                Font.Name = 'MS Sans Serif'
                Font.Style = [fsBold]
                ParentFont = False
                TabOrder = 1
                object Label61: TLabel
                  Left = 32
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
                object Label62: TLabel
                  Left = 136
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
                object Label63: TLabel
                  Left = 297
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
                object EdtTtCtToMa: TBrvEditNum
                  Left = 72
                  Top = 10
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
                  Left = 171
                  Top = 10
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
                  Left = 392
                  Top = 10
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
              Width = 939
              Height = 157
              BrShowMemo = True
              BrReadOnlyStyle = []
              BrReadOnlyColor = clBlack
              Align = alClient
              DataSource = DtsTomado
              Options = [dgEditing, dgTitles, dgIndicator, dgColumnResize, dgColLines, dgRowLines, dgTabs, dgCancelOnExit]
              PopupMenu = PopTomado
              TabOrder = 0
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
              BrGradeZebrada = False
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
            object DbgRotas: TBrvDbGrid
              Left = 0
              Top = 0
              Width = 939
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
              BrGradeZebrada = False
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
              Width = 939
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
                935
                82)
              object LblQtRotas: TLabel
                Left = 866
                Top = 4
                Width = 64
                Height = 13
                Alignment = taRightJustify
                Anchors = [akTop, akRight]
                Caption = 'LblQtRotas'
              end
              object Panel13: TPanel
                Left = 1
                Top = 52
                Width = 933
                Height = 29
                Align = alBottom
                BevelOuter = bvNone
                BiDiMode = bdLeftToRight
                Font.Charset = DEFAULT_CHARSET
                Font.Color = clWindowText
                Font.Height = -11
                Font.Name = 'MS Sans Serif'
                Font.Style = [fsBold]
                ParentBiDiMode = False
                ParentFont = False
                TabOrder = 2
                object BrvBitBtn13: TBrvBitBtn
                  Left = 725
                  Top = 0
                  Width = 100
                  Height = 25
                  Hint = 'Carga'
                  Align = alCustom
                  Anchors = [akTop, akRight]
                  Caption = '&Carga'
                  DoubleBuffered = True
                  Font.Charset = DEFAULT_CHARSET
                  Font.Color = clWindowText
                  Font.Height = -11
                  Font.Name = 'Tahoma'
                  Font.Style = [fsBold]
                  Glyph.Data = {
                    96090000424D9609000000000000360000002800000028000000140000000100
                    18000000000060090000C30E0000C30E00000000000000000000408080408080
                    4080804080804080804080804080804080804080804080804080804080804080
                    8040808040808040808040808040808040808040808040808040808040808040
                    8080408080408080408080408080408080408080408080408080408080408080
                    408080408080408080408080408080408080408080408080A7AF85D2B587CCB5
                    87CCB586CAB486D3B587D3B587D8B588D3B587D8B588D3B587D8B587D3B487D3
                    B487DBB5889DAC824080804080804080804080804A92928FA2A288A0A0869E9E
                    869E9E859D9D839B9B8199998199998098987F97977E96967D95957C94948396
                    96438B8B408080408080408080A7AF85DBB588DBB588DBB588DBB588DBB588D8
                    B588DBB588DBB588DBB588DBB588DBB588DBB588DBB588DBB588DBB588D8B587
                    9DAC824080804080804B9393C3B1B1AEAAAAAEAAAAACA8A8ABA7A7A9A5A5A8A4
                    A4A6A2A2A4A0A0A39F9FA39F9FA19D9DA09C9C9F9B9B9E9A9AAF9D9D438B8B40
                    8080408080D5B588DBB588DBB588D3B587D3B587D8B588D8B588DBB588DBB588
                    DBB588DBB588DBB588DBB588DBB588DBB588DBB588DBB588D8B5874080804080
                    8093A6A6B2AEAEABABABA9A9A9A9A9A9A7A7A7A6A6A6A3A3A3A3A3A3A1A1A1A0
                    A0A09F9F9F9E9E9E9C9C9C9C9C9C9A9A9A9E9A9A839696408080408080CFB587
                    DBB588DBB588D0B687DBB588DBB588DBB588DBB588DBB588DBB588D3B586D3B5
                    86D0B485D3B586DBB588DBB588DBB588D3B4874080804080808DA5A5B3AFAFAC
                    ACACAAAAAAA1A1A19E9E9E9E9E9E9C9C9C9A9A9A979797969696959595939393
                    9393939C9C9C9A9A9A9F9B9B7D9595408080408080D3B587DBB588DBB588DBB5
                    88FFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFD3
                    B586DBB588DBB588D3B4874080804080808FA7A7B4B0B0AEAEAEA5A5A5F8F8F8
                    FFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFF9F9F99494949D9D
                    9DA19D9D7D9595408080408080DBB588DBB588DBB588D2B587FFFFFFFFFFFFFF
                    FFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFD0B485DBB588DBB588
                    D8B58740808040808091A9A9B6B2B2AEAEAEA4A4A4FFFFFFF8F8F8F9F9F9F8F8
                    F8FAFAFAF9F9F9FAFAFAF9F9F9FAFAFAFFFFFF9292929C9C9CA29E9E7E969640
                    8080408080DBB588DBB588DBB588D5B587FFFFFFFFFFFFFFFFFFFFFFFFFFFFFF
                    FFFFFFFFFFFFFFFFFFFFFFFFFFFFFFD3B586DBB588DBB588D3B5874080804080
                    8091A9A9B7B3B3B0B0B0A5A5A5FFFFFFFAFAFAFAFAFAFAFAFAF9F9F9FAFAFAFA
                    FAFAFAFAFAFAFAFAFFFFFF9494949E9E9EA29E9E7F9797408080408080DBB588
                    DBB588DBB588D6B587FFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFF
                    FFFFFFFFFFFFFFD8B587DBB588DBB588D8B58840808040808092AAAAB8B4B4B2
                    B2B2A7A7A7FFFFFFFAFAFAFBFBFBFAFAFAFAFAFAFBFBFBFAFAFAFBFBFBFAFAFA
                    FFFFFF979797A0A0A0A4A0A0809898408080408080DBB588DBB588DBB588DBB5
                    88FFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFDA
                    B588D7B587DBB588D3B58740808040808093ABABBAB6B6B3B3B3A8A8A8FFFFFF
                    FBFBFBFCFCFCFBFBFBFBFBFBFBFBFBFBFBFBFBFBFBFBFBFBFFFFFF979797A0A0
                    A0A5A1A1809898408080408080DBB588DBB588DBB588DBB588FFFFFFFFFFFFFF
                    FFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFDBB588DBB588DBB588
                    DAB58840808040808095ADADBBB7B7B4B4B4A9A9A9FFFFFFFDFDFDFCFCFCFDFD
                    FDFCFCFCFDFDFDFBFBFBFDFDFDFCFCFCFFFFFF999999A3A3A3A6A2A2829A9A40
                    8080408080DBB588DBB588DBB588DBB588FFFFFFFFFFFFFFFFFFFFFFFFFFFFFF
                    FFFFFFFFFFFFFFFFFFFFFFFFFFFFFFDBB588DBB588DBB588DBB5884080804080
                    8095ADADBCB8B8B5B5B5AAAAAAFFFFFFFDFDFDFDFDFDFCFCFCFDFDFDFDFDFDFC
                    FCFCFCFCFCFEFEFEFFFFFF9B9B9BA3A3A3A8A4A4839B9B408080408080DBB588
                    DBB588DBB588DBB588FFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFF
                    FFFFFFFFFFFFFFDBB588DBB588DBB588DBB58840808040808095ADADBEBABAB6
                    B6B6ACACACFFFFFFFEFEFEFEFEFEFDFDFDFEFEFEFEFEFEFDFDFDFEFEFEFDFDFD
                    FFFFFF9C9C9CA6A6A6A9A5A5839B9B408080408080DBB588D3B688DBB588D6B5
                    87FFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFDB
                    B588DBB588DBB588DAB58840808040808097AFAFBFBBBBB8B8B8ADADADFFFFFF
                    FEFEFEFEFEFEFFFFFFFEFEFEFEFEFEFEFEFEFFFFFFFEFEFEFFFFFF9E9E9EA8A8
                    A8ABA7A7869E9E408080408080D6B688DBB588D8B688DBB588FFFFFFFFFFFFFF
                    FFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFDBB588DBB588DBB588
                    DAB58840808040808097AFAFBFBBBBB8B8B8B0B0B0FEFEFEFFFFFFFFFFFFFFFF
                    FFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFA0A0A0A8A8A8ACA8A8869E9E40
                    8080408080D8B588DBB588DBB588DBB588DBB588DBB588DBB588DBB588DBB588
                    DBB588DBB588DBB588DBB588DBB588DBB588DBB588DBB588DBB5884080804080
                    8099B1B1C1BDBDBABABAB9B9B9B0B0B0ADADADACACACAAAAAAA9A9A9A8A8A8A6
                    A6A6A5A5A5A3A3A3A3A3A3ABABABAAAAAAAEAAAA879F9F408080408080D8B588
                    DBB588DBB588DBB588D8B588DBB588DBB588DBB588DBB588D8B588DBB588DBB5
                    88DBB588DBB588DBB588DBB588DBB588DAB588408080408080A1B4B4C2BEBEBA
                    BABABBBBBBB8B8B8B7B7B7B6B6B6B5B5B5B3B3B3B3B3B3B2B2B2B1B1B1AEAEAE
                    ADADADADADADAAAAAAAFABAB8FA2A2408080408080A2AF86CABA88DBB588DBB5
                    88DBB588DBB588DBB588DBB588DBB588DBB588DBB588DBB588DBB588DBB588DB
                    B588DBB588DBB588189E7B408080408080539B9BD9C7C7C2BEBEC1BDBDBFBBBB
                    BFBBBBBDB9B9BCB8B8BBB7B7BAB6B6B9B5B5B7B3B3B6B2B2B3AFAFB3AFAFB1AD
                    ADC3B1B1499191408080408080408080A2AF87D8B588D5B588DBB588DBB588DB
                    B588DBB588DBB588DBB588DBB588DBB588DBB588DBB588DBB588D0B5871A9F7C
                    408080408080408080408080529B9BA1B4B498B0B098B0B097AFAF96AEAE95AD
                    AD93ABAB94ACAC92AAAA92AAAA90A8A88FA7A78EA6A694A7A74B949440808040
                    8080408080408080408080408080408080408080408080408080408080408080
                    4080804080804080804080804080804080804080804080804080804080804080
                    8040808040808040808040808040808040808040808040808040808040808040
                    8080408080408080408080408080408080408080408080408080}
                  NumGlyphs = 2
                  ParentDoubleBuffered = False
                  ParentFont = False
                  ParentShowHint = False
                  ShowHint = True
                  TabOrder = 1
                  OnClick = BrvBitBtn9Click
                  BrTipoBotao = BrBtnCarga
                end
                object BrvBitBtn2: TBrvBitBtn
                  Left = 829
                  Top = 0
                  Width = 100
                  Height = 25
                  Hint = 'Voltar'
                  Align = alCustom
                  Anchors = [akTop, akRight]
                  Caption = '&Voltar'
                  DoubleBuffered = True
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
                    8000808000808000808000808000808000808000808000808000808000808000
                    8080008080008080008080008080008080008080008080008080008080008080
                    0080800080800080800080800080800080800080800080800080800080800080
                    8000808000808000808000808000808000808000808000808000808000808000
                    8080637D5BD67B2F008080008080008080008080008080008080008080008080
                    0080800080800080800080800080800080800080800080800080800080804579
                    7997727200808000808000808000808000808000808000808000808000808000
                    808000808000808000808000808000808000808000808065815FB88143C08141
                    0080800080800080800080800080800080800080800080800080800080800080
                    80008080008080008080008080008080008080487C7C8378788A797900808000
                    8080008080008080008080008080008080008080008080008080008080008080
                    0080800080800080800080805B8467C28748AF874EC488480080800080800080
                    8000808000808000808000808000808000808000808000808000808000808000
                    80800080800080804280808D7E7E8080808F7F7F008080008080008080008080
                    0080800080800080800080800080800080800080800080800080800080800080
                    80778A65C78F53B48E58B48E58C28F5400808000808000808000808000808000
                    8080008080008080008080008080008080008080008080008080008080598484
                    9486868686868686869187870080800080800080800080800080800080800080
                    800080800080800080800080800080800080800080805F8A6ECB955AB7935EB7
                    935EB7935EB7935EC5945BC5945BC5945BC5945BC5945BC5945BC5945BC5945B
                    D496580080800080800080800080800080804986869A8C8C8B8B8B8B8B8B8A8A
                    8A8B8B8B968C8C968C8C968C8C958B8B968C8C968C8C968C8C968C8CA08C8C00
                    80800080800080800080806E8C6FCF985FBA9563BA9563BA9563BA9563BA9563
                    BA9563BA9563BA9563BA9563BA9563BA9563BA9563BA9563C997600080800080
                    800080800080805488889D8F8F8E8E8E8D8D8D8E8E8E8E8E8E8D8D8D8D8D8D8D
                    8D8D8D8D8D8E8E8E8D8D8D8D8D8D8E8E8E8D8D8D988E8E008080008080008080
                    708F71CD9B64BD9966BD9966BD9966BD9966BD9966BD9966BD9966BD9966BD99
                    66BD9966BD9966BD9966BD9966BD9966CB9B64008080008080008080568A8A9D
                    9292919191919191919191919191919191919191919191919191919191919191
                    9191919191919191919191919C92920080800080806E9073D49F67BE9C6ABE9C
                    6ABE9C6ABE9C6ABE9C6ABE9C6ABE9C6ABE9C6ABE9C6ABE9C6ABE9C6ABE9C6ABE
                    9C6ABE9C6ABE9C6ACD9E68008080008080568C8CA59696949494949494949494
                    9494949494949494949494949494949494949494949393939494949393939494
                    949494949F9595008080008080709175D7A26BC19E6DC19E6DC19E6DC19E6DC1
                    9E6DC19E6DC19E6DC19E6DC19E6DC19E6DC19E6DC19E6DC19E6DC19E6DC19E6D
                    D0A16C008080008080578D8DA899999796969696969696969696969696969696
                    96969696969696969696969696969696969696969696969696969696A2989800
                    8080008080008080739377D3A46FC3A170C2A170C2A170C2A170C2A170C2A170
                    C2A170C2A170C2A170C2A170C2A170C2A170C2A170C2A170D1A46F0080800080
                    800080805A8F8FA69B9B99989899999999999999999999999998989899999999
                    9999999999999999999999999999989898999999A59B9B008080008080008080
                    008080759578DCA872C5A474C5A474C5A474C5A474C5A474C5A474C5A474C5A4
                    74C5A474C5A474C5A474C5A474C5A474D4A6730080800080800080800080805C
                    9090AD9E9E9B9B9B9B9B9B9C9C9C9C9C9C9C9C9C9C9C9C9C9C9C9C9C9C9C9C9C
                    9C9C9C9B9B9B9C9C9C9C9C9CA89E9E0080800080800080800080800080806894
                    7ADEAA76C8A677C8A677C8A677C8A677D8A976D8A976D8A976D8A976D8A976D8
                    A976D8A976D8A976E7AC76008080008080008080008080008080528F8FAFA1A1
                    9E9E9E9E9E9E9E9E9E9E9E9EAAA0A0AAA0A0ABA1A1AAA0A0AAA0A0ABA1A1AAA0
                    A0AAA0A0B7A3A3008080008080008080008080008080008080869A7CDFAC79C9
                    A779C9A779D9AA79008080008080008080008080008080008080008080008080
                    008080008080008080008080008080008080008080699494B1A3A39F9F9FA0A0
                    A0ACA2A200808000808000808000808000808000808000808000808000808000
                    808000808000808000808000808000808000808069957EE1AE7CCAA97CE3AE7C
                    0080800080800080800080800080800080800080800080800080800080800080
                    80008080008080008080008080008080539191B4A5A5A1A1A1B5A5A500808000
                    8080008080008080008080008080008080008080008080008080008080008080
                    00808000808000808000808000808078997EDDAE7DE5B07D0080800080800080
                    8000808000808000808000808000808000808000808000808000808000808000
                    8080008080008080008080619595B0A5A5B7A7A7008080008080008080008080
                    0080800080800080800080800080800080800080800080800080800080800080
                    8000808000808000808079997FFFB77D00808000808000808000808000808000
                    8080008080008080008080008080008080008080008080008080008080008080
                    008080008080609595D2ADAD0080800080800080800080800080800080800080
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
                  TabOrder = 2
                  OnClick = BrvBitBtn2Click
                  BrTipoBotao = BrBtnSetaEsquerda
                end
                object BrvBitBtn3: TBrvBitBtn
                  Left = 621
                  Top = 0
                  Width = 100
                  Height = 25
                  Hint = 'Detalhar'
                  Align = alCustom
                  Anchors = [akTop, akRight]
                  Caption = '&Detalhar'
                  DoubleBuffered = True
                  Font.Charset = DEFAULT_CHARSET
                  Font.Color = clWindowText
                  Font.Height = -11
                  Font.Name = 'Tahoma'
                  Font.Style = [fsBold]
                  Glyph.Data = {
                    96090000424D9609000000000000360000002800000028000000140000000100
                    1800000000006009000000000000000000000000000000000000008080182A2A
                    251A1A0B52520080800080800080800080800080800080800080800080800080
                    800080800080800080800080800080800080800080800080802D2D2D20202050
                    5050008080008080008080008080008080008080008080008080008080008080
                    008080008080008080008080008080008080104B4B2E24243B3B3B2F1F1F0C51
                    5100808000808000808000808000808000808000808000808000808000808000
                    80800080800080800080800080804A4A4A2929293E3E3E252525505050008080
                    0080800080800080800080800080800080800080800080800080800080800080
                    800080800080800080801259594234345B5B5B5B5B5B3524240B555500808000
                    8080008080008080008080008080008080008080008080008080008080008080
                    0080800080805757573838385C5C5C5B5B5B2A2A2A5454540080800080800080
                    8000808000808000808000808000808000808000808000808000808000808000
                    80800080802B50505045456363635E5E5E3A28280C5959008080008080008080
                    0080800080800080800080800080800080800080800080800080800080800080
                    804F4F4F4747476363635F5F5F2D2D2D57575700808000808000808000808000
                    8080008080008080008080008080008080008080008080008080008080008080
                    315454584D4D6868686261613B2D310D5D5D00808061632159672C855A005C66
                    295F64230080800080800080800080800080800080800080800080805353534E
                    4E4E6868686161613232325A5A5A0080805959595D5D5D5454545C5C5C5B5B5B
                    0080800080800080800080800080800080800080800080800080803857575D53
                    5365676D564F3D7A570689600586650C8B67098B6B0B8C680987650B89600576
                    6212008080008080008080008080008080008080008080575757545454686868
                    4D4D4D5252525A5A5A5D5D5D5F5F5F6262626060605D5D5D5A5A5A5959590080
                    800080800080800080800080800080800080800080803E5B5E5E534081640889
                    6A1191710D8B6A04997F2F9D87419B81358C6C0A9170098C6B0F8C6206008080
                    0080800080800080800080800080800080805B5B5B5151515B5B5B6161616767
                    676161617474747C7C7C7676766363636666666262625B5B5B00808000808000
                    80800080800080800080800080800080807E661090711396740B927A2DD4CDB4
                    E3DECCE7E2D2E4DFCEDBD4BFA38F528E6C019473149264010080800080800080
                    800080800080800080800080805D5D5D6868686A6A6A707070C7C7C7DADADADE
                    DEDEDBDBDBCFCFCF8585856363636969695D5D5D008080008080008080008080
                    0080800080800080809E720B9F7C118D7428EAE5D3E6DFC8E3DCC4E3DBC3E3DB
                    C3E5DDC6ECE6D5A49157967308A0771000808000808000808000808000808000
                    80800080806969697171716A6A6AE0E0E0DADADAD6D6D6D5D5D5D5D5D5D7D7D7
                    E2E2E28787876969696D6D6D6565650080800080800080800080800080800080
                    80A88216886600E5DEC8E8E0C7E5DDC2E5DCC2E5DCC2E5DCC2E5DDC2E7DEC4EB
                    E5D191751FA58116A16C02008080008080008080008080008080008080777777
                    5D5D5DD9D9D9DADADAD6D6D6D6D6D6D6D6D6D6D6D6D7D7D7D8D8D8E0E0E06B6B
                    6B7575756464640080800080800080800080800080808D7D22AE87139C853FF3
                    ECD4E9DFC2EAE2C7EBE3C9ECE4CBECE4CAEBE2C8E9E0C2EFE8CEB5A470A17E0E
                    B47F0B0080800080800080800080800080807171717B7B7B7B7B7BE7E7E7D9D9
                    D9DCDCDCDDDDDDDEDEDEDEDEDEDCDCDCDADADAE2E2E29A9A9A7373737575757D
                    7D7D008080008080008080008080B98612B18B14AA9860F4EBD0F0E8CEF0E8CF
                    F0E8CFF0E8CFF0E8CFF0E8CFF0E8CFF1E8CDC7BA91A07C0BBF8F160080800080
                    800080800080800080807B7B7B7E7E7E8E8E8EE5E5E5E2E2E2E3E3E3E2E2E2E3
                    E3E3E2E2E2E2E2E2E2E2E2E3E3E3B1B1B1717171838383686868008080008080
                    008080008080BD8E17BC9416AA975BFAF2DAF4ECD4F4ECD3F4ECD3F4ECD3F4EC
                    D3F4ECD3F4ECD3F9F1D9C8B98CA9840ECA981700808000808000808000808000
                    80808282828686868D8D8DEDEDEDE7E7E7E6E6E6E7E7E7E6E6E6E6E6E6E7E7E7
                    E7E7E7ECECECB0B0B07878788B8B8B6B6B6B008080008080008080008080A18D
                    26CD9F1C9C8030FFFBE8F7EFD7F6EFD6F7EFD6F6EFD6F7EFD6F7EFD6F7EFD6FF
                    F9E4B39F62BC9416D19610008080008080008080008080008080808080919191
                    757575F7F7F7EAEAEAEAEAEAE9E9E9EAEAEAE9E9E9EAEAEAE9E9E9F5F5F59494
                    948686868A8A8A7D7D7D008080008080008080008080008080DFAE22A37D07E7
                    DEC3FFF8E3FAF2DAF9F2DAFAF2DAFAF2DAFAF2DAFCF5DFF7F1DDA0801CD8AB22
                    B389150080800080800080800080800080807171719E9E9E727272D8D8D8F3F3
                    F3ECECECEDEDEDEDEDEDEDEDEDEDEDEDF1F1F1ECECEC7474749C9C9C7D7D7D00
                    8080008080008080008080008080008080EBB11ED7AB22977A1FF7F1E2FFFCEA
                    FDF5E0FCF4DEFCF6E0FFFAE6FFFDEFA28C46C89E18F5B9210080800080800080
                    80008080008080008080008080A2A2A29C9C9C707070EEEEEEF8F8F8F0F0F0F0
                    F0F0F1F1F1F6F6F6FAFAFA818181909090A9A9A96D6D6D008080008080008080
                    008080008080008080008080FFC727D6A91EA38525D2C7A5FCF8E8FFFFF6FFFC
                    EEDCD4B9AA8F3FCA9D16F8C329B98E1600808000808000808000808000808000
                    8080008080008080B5B5B59A9A9A797979BFBFBFF5F5F5FDFDFDF9F9F9CECECE
                    8383838F8F8FB1B1B18181810080800080800080800080800080800080800080
                    80008080008080FFD228E6B626C69A16B18E21A38421AD8B21C09617E1B121FF
                    CC29CEA41C008080008080008080008080008080008080008080008080008080
                    008080BEBEBEA6A6A68C8C8C8181817878787E7E7E898989A1A1A1BABABA9595
                    9500808000808000808000808000808000808000808000808000808000808000
                    8080FFC81BFFDB2CFDC527F5C129F9C327FFD72BFFD4228A9130008080008080
                    008080008080008080008080008080008080008080008080008080008080B6B6
                    B6C6C6C6B3B3B3B0B0B0B2B2B2C3C3C3C0C0C081818100808000808000808000
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
                  OnClick = BrvBitBtn3Click
                  BrTipoBotao = BrBtnPesqui
                end
              end
              object GroupBox2: TGroupBox
                Left = 6
                Top = 4
                Width = 523
                Height = 36
                Caption = 'Geral:'
                Font.Charset = DEFAULT_CHARSET
                Font.Color = clWindowText
                Font.Height = -11
                Font.Name = 'MS Sans Serif'
                Font.Style = [fsBold]
                ParentFont = False
                TabOrder = 0
                object Label2: TLabel
                  Left = 32
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
                  Left = 136
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
                  Left = 297
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
                  Left = 72
                  Top = 10
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
                  Left = 172
                  Top = 10
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
                  Left = 392
                  Top = 10
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
                Top = 41
                Width = 523
                Height = 36
                Caption = 'Marcados:'
                Font.Charset = DEFAULT_CHARSET
                Font.Color = clWindowText
                Font.Height = -11
                Font.Name = 'MS Sans Serif'
                Font.Style = [fsBold]
                ParentFont = False
                TabOrder = 1
                object Label8: TLabel
                  Left = 32
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
                object Label14: TLabel
                  Left = 136
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
                object Label15: TLabel
                  Left = 297
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
                object EdtTtCtRoMa: TBrvEditNum
                  Left = 72
                  Top = 10
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
                  Left = 172
                  Top = 10
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
                  Left = 392
                  Top = 10
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
            object DbgCidade: TBrvDbGrid
              Left = 0
              Top = 0
              Width = 939
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
              BrGradeZebrada = False
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
              Width = 939
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
                935
                82)
              object LblQtCidade: TLabel
                Left = 860
                Top = 4
                Width = 70
                Height = 13
                Alignment = taRightJustify
                Anchors = [akTop, akRight]
                Caption = 'LblQtCidade'
              end
              object Panel14: TPanel
                Left = 1
                Top = 52
                Width = 933
                Height = 29
                Align = alBottom
                BevelOuter = bvNone
                BiDiMode = bdLeftToRight
                Font.Charset = DEFAULT_CHARSET
                Font.Color = clWindowText
                Font.Height = -11
                Font.Name = 'MS Sans Serif'
                Font.Style = [fsBold]
                ParentBiDiMode = False
                ParentFont = False
                TabOrder = 2
                object BrvBitBtn4: TBrvBitBtn
                  Left = 829
                  Top = 0
                  Width = 100
                  Height = 25
                  Hint = 'Voltar'
                  Align = alCustom
                  Anchors = [akTop, akRight]
                  Caption = '&Voltar'
                  DoubleBuffered = True
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
                    8000808000808000808000808000808000808000808000808000808000808000
                    8080008080008080008080008080008080008080008080008080008080008080
                    0080800080800080800080800080800080800080800080800080800080800080
                    8000808000808000808000808000808000808000808000808000808000808000
                    8080637D5BD67B2F008080008080008080008080008080008080008080008080
                    0080800080800080800080800080800080800080800080800080800080804579
                    7997727200808000808000808000808000808000808000808000808000808000
                    808000808000808000808000808000808000808000808065815FB88143C08141
                    0080800080800080800080800080800080800080800080800080800080800080
                    80008080008080008080008080008080008080487C7C8378788A797900808000
                    8080008080008080008080008080008080008080008080008080008080008080
                    0080800080800080800080805B8467C28748AF874EC488480080800080800080
                    8000808000808000808000808000808000808000808000808000808000808000
                    80800080800080804280808D7E7E8080808F7F7F008080008080008080008080
                    0080800080800080800080800080800080800080800080800080800080800080
                    80778A65C78F53B48E58B48E58C28F5400808000808000808000808000808000
                    8080008080008080008080008080008080008080008080008080008080598484
                    9486868686868686869187870080800080800080800080800080800080800080
                    800080800080800080800080800080800080800080805F8A6ECB955AB7935EB7
                    935EB7935EB7935EC5945BC5945BC5945BC5945BC5945BC5945BC5945BC5945B
                    D496580080800080800080800080800080804986869A8C8C8B8B8B8B8B8B8A8A
                    8A8B8B8B968C8C968C8C968C8C958B8B968C8C968C8C968C8C968C8CA08C8C00
                    80800080800080800080806E8C6FCF985FBA9563BA9563BA9563BA9563BA9563
                    BA9563BA9563BA9563BA9563BA9563BA9563BA9563BA9563C997600080800080
                    800080800080805488889D8F8F8E8E8E8D8D8D8E8E8E8E8E8E8D8D8D8D8D8D8D
                    8D8D8D8D8D8E8E8E8D8D8D8D8D8D8E8E8E8D8D8D988E8E008080008080008080
                    708F71CD9B64BD9966BD9966BD9966BD9966BD9966BD9966BD9966BD9966BD99
                    66BD9966BD9966BD9966BD9966BD9966CB9B64008080008080008080568A8A9D
                    9292919191919191919191919191919191919191919191919191919191919191
                    9191919191919191919191919C92920080800080806E9073D49F67BE9C6ABE9C
                    6ABE9C6ABE9C6ABE9C6ABE9C6ABE9C6ABE9C6ABE9C6ABE9C6ABE9C6ABE9C6ABE
                    9C6ABE9C6ABE9C6ACD9E68008080008080568C8CA59696949494949494949494
                    9494949494949494949494949494949494949494949393939494949393939494
                    949494949F9595008080008080709175D7A26BC19E6DC19E6DC19E6DC19E6DC1
                    9E6DC19E6DC19E6DC19E6DC19E6DC19E6DC19E6DC19E6DC19E6DC19E6DC19E6D
                    D0A16C008080008080578D8DA899999796969696969696969696969696969696
                    96969696969696969696969696969696969696969696969696969696A2989800
                    8080008080008080739377D3A46FC3A170C2A170C2A170C2A170C2A170C2A170
                    C2A170C2A170C2A170C2A170C2A170C2A170C2A170C2A170D1A46F0080800080
                    800080805A8F8FA69B9B99989899999999999999999999999998989899999999
                    9999999999999999999999999999989898999999A59B9B008080008080008080
                    008080759578DCA872C5A474C5A474C5A474C5A474C5A474C5A474C5A474C5A4
                    74C5A474C5A474C5A474C5A474C5A474D4A6730080800080800080800080805C
                    9090AD9E9E9B9B9B9B9B9B9C9C9C9C9C9C9C9C9C9C9C9C9C9C9C9C9C9C9C9C9C
                    9C9C9C9B9B9B9C9C9C9C9C9CA89E9E0080800080800080800080800080806894
                    7ADEAA76C8A677C8A677C8A677C8A677D8A976D8A976D8A976D8A976D8A976D8
                    A976D8A976D8A976E7AC76008080008080008080008080008080528F8FAFA1A1
                    9E9E9E9E9E9E9E9E9E9E9E9EAAA0A0AAA0A0ABA1A1AAA0A0AAA0A0ABA1A1AAA0
                    A0AAA0A0B7A3A3008080008080008080008080008080008080869A7CDFAC79C9
                    A779C9A779D9AA79008080008080008080008080008080008080008080008080
                    008080008080008080008080008080008080008080699494B1A3A39F9F9FA0A0
                    A0ACA2A200808000808000808000808000808000808000808000808000808000
                    808000808000808000808000808000808000808069957EE1AE7CCAA97CE3AE7C
                    0080800080800080800080800080800080800080800080800080800080800080
                    80008080008080008080008080008080539191B4A5A5A1A1A1B5A5A500808000
                    8080008080008080008080008080008080008080008080008080008080008080
                    00808000808000808000808000808078997EDDAE7DE5B07D0080800080800080
                    8000808000808000808000808000808000808000808000808000808000808000
                    8080008080008080008080619595B0A5A5B7A7A7008080008080008080008080
                    0080800080800080800080800080800080800080800080800080800080800080
                    8000808000808000808079997FFFB77D00808000808000808000808000808000
                    8080008080008080008080008080008080008080008080008080008080008080
                    008080008080609595D2ADAD0080800080800080800080800080800080800080
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
                  TabOrder = 2
                  OnClick = BrvBitBtn4Click
                  BrTipoBotao = BrBtnSetaEsquerda
                end
                object BrvBitBtn5: TBrvBitBtn
                  Left = 621
                  Top = 0
                  Width = 100
                  Height = 25
                  Hint = 'Detalhar'
                  Align = alCustom
                  Anchors = [akTop, akRight]
                  Caption = '&Detalhar'
                  DoubleBuffered = True
                  Font.Charset = DEFAULT_CHARSET
                  Font.Color = clWindowText
                  Font.Height = -11
                  Font.Name = 'Tahoma'
                  Font.Style = [fsBold]
                  Glyph.Data = {
                    96090000424D9609000000000000360000002800000028000000140000000100
                    1800000000006009000000000000000000000000000000000000008080182A2A
                    251A1A0B52520080800080800080800080800080800080800080800080800080
                    800080800080800080800080800080800080800080800080802D2D2D20202050
                    5050008080008080008080008080008080008080008080008080008080008080
                    008080008080008080008080008080008080104B4B2E24243B3B3B2F1F1F0C51
                    5100808000808000808000808000808000808000808000808000808000808000
                    80800080800080800080800080804A4A4A2929293E3E3E252525505050008080
                    0080800080800080800080800080800080800080800080800080800080800080
                    800080800080800080801259594234345B5B5B5B5B5B3524240B555500808000
                    8080008080008080008080008080008080008080008080008080008080008080
                    0080800080805757573838385C5C5C5B5B5B2A2A2A5454540080800080800080
                    8000808000808000808000808000808000808000808000808000808000808000
                    80800080802B50505045456363635E5E5E3A28280C5959008080008080008080
                    0080800080800080800080800080800080800080800080800080800080800080
                    804F4F4F4747476363635F5F5F2D2D2D57575700808000808000808000808000
                    8080008080008080008080008080008080008080008080008080008080008080
                    315454584D4D6868686261613B2D310D5D5D00808061632159672C855A005C66
                    295F64230080800080800080800080800080800080800080800080805353534E
                    4E4E6868686161613232325A5A5A0080805959595D5D5D5454545C5C5C5B5B5B
                    0080800080800080800080800080800080800080800080800080803857575D53
                    5365676D564F3D7A570689600586650C8B67098B6B0B8C680987650B89600576
                    6212008080008080008080008080008080008080008080575757545454686868
                    4D4D4D5252525A5A5A5D5D5D5F5F5F6262626060605D5D5D5A5A5A5959590080
                    800080800080800080800080800080800080800080803E5B5E5E534081640889
                    6A1191710D8B6A04997F2F9D87419B81358C6C0A9170098C6B0F8C6206008080
                    0080800080800080800080800080800080805B5B5B5151515B5B5B6161616767
                    676161617474747C7C7C7676766363636666666262625B5B5B00808000808000
                    80800080800080800080800080800080807E661090711396740B927A2DD4CDB4
                    E3DECCE7E2D2E4DFCEDBD4BFA38F528E6C019473149264010080800080800080
                    800080800080800080800080805D5D5D6868686A6A6A707070C7C7C7DADADADE
                    DEDEDBDBDBCFCFCF8585856363636969695D5D5D008080008080008080008080
                    0080800080800080809E720B9F7C118D7428EAE5D3E6DFC8E3DCC4E3DBC3E3DB
                    C3E5DDC6ECE6D5A49157967308A0771000808000808000808000808000808000
                    80800080806969697171716A6A6AE0E0E0DADADAD6D6D6D5D5D5D5D5D5D7D7D7
                    E2E2E28787876969696D6D6D6565650080800080800080800080800080800080
                    80A88216886600E5DEC8E8E0C7E5DDC2E5DCC2E5DCC2E5DCC2E5DDC2E7DEC4EB
                    E5D191751FA58116A16C02008080008080008080008080008080008080777777
                    5D5D5DD9D9D9DADADAD6D6D6D6D6D6D6D6D6D6D6D6D7D7D7D8D8D8E0E0E06B6B
                    6B7575756464640080800080800080800080800080808D7D22AE87139C853FF3
                    ECD4E9DFC2EAE2C7EBE3C9ECE4CBECE4CAEBE2C8E9E0C2EFE8CEB5A470A17E0E
                    B47F0B0080800080800080800080800080807171717B7B7B7B7B7BE7E7E7D9D9
                    D9DCDCDCDDDDDDDEDEDEDEDEDEDCDCDCDADADAE2E2E29A9A9A7373737575757D
                    7D7D008080008080008080008080B98612B18B14AA9860F4EBD0F0E8CEF0E8CF
                    F0E8CFF0E8CFF0E8CFF0E8CFF0E8CFF1E8CDC7BA91A07C0BBF8F160080800080
                    800080800080800080807B7B7B7E7E7E8E8E8EE5E5E5E2E2E2E3E3E3E2E2E2E3
                    E3E3E2E2E2E2E2E2E2E2E2E3E3E3B1B1B1717171838383686868008080008080
                    008080008080BD8E17BC9416AA975BFAF2DAF4ECD4F4ECD3F4ECD3F4ECD3F4EC
                    D3F4ECD3F4ECD3F9F1D9C8B98CA9840ECA981700808000808000808000808000
                    80808282828686868D8D8DEDEDEDE7E7E7E6E6E6E7E7E7E6E6E6E6E6E6E7E7E7
                    E7E7E7ECECECB0B0B07878788B8B8B6B6B6B008080008080008080008080A18D
                    26CD9F1C9C8030FFFBE8F7EFD7F6EFD6F7EFD6F6EFD6F7EFD6F7EFD6F7EFD6FF
                    F9E4B39F62BC9416D19610008080008080008080008080008080808080919191
                    757575F7F7F7EAEAEAEAEAEAE9E9E9EAEAEAE9E9E9EAEAEAE9E9E9F5F5F59494
                    948686868A8A8A7D7D7D008080008080008080008080008080DFAE22A37D07E7
                    DEC3FFF8E3FAF2DAF9F2DAFAF2DAFAF2DAFAF2DAFCF5DFF7F1DDA0801CD8AB22
                    B389150080800080800080800080800080807171719E9E9E727272D8D8D8F3F3
                    F3ECECECEDEDEDEDEDEDEDEDEDEDEDEDF1F1F1ECECEC7474749C9C9C7D7D7D00
                    8080008080008080008080008080008080EBB11ED7AB22977A1FF7F1E2FFFCEA
                    FDF5E0FCF4DEFCF6E0FFFAE6FFFDEFA28C46C89E18F5B9210080800080800080
                    80008080008080008080008080A2A2A29C9C9C707070EEEEEEF8F8F8F0F0F0F0
                    F0F0F1F1F1F6F6F6FAFAFA818181909090A9A9A96D6D6D008080008080008080
                    008080008080008080008080FFC727D6A91EA38525D2C7A5FCF8E8FFFFF6FFFC
                    EEDCD4B9AA8F3FCA9D16F8C329B98E1600808000808000808000808000808000
                    8080008080008080B5B5B59A9A9A797979BFBFBFF5F5F5FDFDFDF9F9F9CECECE
                    8383838F8F8FB1B1B18181810080800080800080800080800080800080800080
                    80008080008080FFD228E6B626C69A16B18E21A38421AD8B21C09617E1B121FF
                    CC29CEA41C008080008080008080008080008080008080008080008080008080
                    008080BEBEBEA6A6A68C8C8C8181817878787E7E7E898989A1A1A1BABABA9595
                    9500808000808000808000808000808000808000808000808000808000808000
                    8080FFC81BFFDB2CFDC527F5C129F9C327FFD72BFFD4228A9130008080008080
                    008080008080008080008080008080008080008080008080008080008080B6B6
                    B6C6C6C6B3B3B3B0B0B0B2B2B2C3C3C3C0C0C081818100808000808000808000
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
                  OnClick = BrvBitBtn5Click
                  BrTipoBotao = BrBtnPesqui
                end
                object BrvBitBtn12: TBrvBitBtn
                  Left = 725
                  Top = 0
                  Width = 100
                  Height = 25
                  Hint = 'Carga'
                  Align = alCustom
                  Anchors = [akTop, akRight]
                  Caption = '&Carga'
                  DoubleBuffered = True
                  Font.Charset = DEFAULT_CHARSET
                  Font.Color = clWindowText
                  Font.Height = -11
                  Font.Name = 'Tahoma'
                  Font.Style = [fsBold]
                  Glyph.Data = {
                    96090000424D9609000000000000360000002800000028000000140000000100
                    18000000000060090000C30E0000C30E00000000000000000000408080408080
                    4080804080804080804080804080804080804080804080804080804080804080
                    8040808040808040808040808040808040808040808040808040808040808040
                    8080408080408080408080408080408080408080408080408080408080408080
                    408080408080408080408080408080408080408080408080A7AF85D2B587CCB5
                    87CCB586CAB486D3B587D3B587D8B588D3B587D8B588D3B587D8B587D3B487D3
                    B487DBB5889DAC824080804080804080804080804A92928FA2A288A0A0869E9E
                    869E9E859D9D839B9B8199998199998098987F97977E96967D95957C94948396
                    96438B8B408080408080408080A7AF85DBB588DBB588DBB588DBB588DBB588D8
                    B588DBB588DBB588DBB588DBB588DBB588DBB588DBB588DBB588DBB588D8B587
                    9DAC824080804080804B9393C3B1B1AEAAAAAEAAAAACA8A8ABA7A7A9A5A5A8A4
                    A4A6A2A2A4A0A0A39F9FA39F9FA19D9DA09C9C9F9B9B9E9A9AAF9D9D438B8B40
                    8080408080D5B588DBB588DBB588D3B587D3B587D8B588D8B588DBB588DBB588
                    DBB588DBB588DBB588DBB588DBB588DBB588DBB588DBB588D8B5874080804080
                    8093A6A6B2AEAEABABABA9A9A9A9A9A9A7A7A7A6A6A6A3A3A3A3A3A3A1A1A1A0
                    A0A09F9F9F9E9E9E9C9C9C9C9C9C9A9A9A9E9A9A839696408080408080CFB587
                    DBB588DBB588D0B687DBB588DBB588DBB588DBB588DBB588DBB588D3B586D3B5
                    86D0B485D3B586DBB588DBB588DBB588D3B4874080804080808DA5A5B3AFAFAC
                    ACACAAAAAAA1A1A19E9E9E9E9E9E9C9C9C9A9A9A979797969696959595939393
                    9393939C9C9C9A9A9A9F9B9B7D9595408080408080D3B587DBB588DBB588DBB5
                    88FFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFD3
                    B586DBB588DBB588D3B4874080804080808FA7A7B4B0B0AEAEAEA5A5A5F8F8F8
                    FFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFF9F9F99494949D9D
                    9DA19D9D7D9595408080408080DBB588DBB588DBB588D2B587FFFFFFFFFFFFFF
                    FFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFD0B485DBB588DBB588
                    D8B58740808040808091A9A9B6B2B2AEAEAEA4A4A4FFFFFFF8F8F8F9F9F9F8F8
                    F8FAFAFAF9F9F9FAFAFAF9F9F9FAFAFAFFFFFF9292929C9C9CA29E9E7E969640
                    8080408080DBB588DBB588DBB588D5B587FFFFFFFFFFFFFFFFFFFFFFFFFFFFFF
                    FFFFFFFFFFFFFFFFFFFFFFFFFFFFFFD3B586DBB588DBB588D3B5874080804080
                    8091A9A9B7B3B3B0B0B0A5A5A5FFFFFFFAFAFAFAFAFAFAFAFAF9F9F9FAFAFAFA
                    FAFAFAFAFAFAFAFAFFFFFF9494949E9E9EA29E9E7F9797408080408080DBB588
                    DBB588DBB588D6B587FFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFF
                    FFFFFFFFFFFFFFD8B587DBB588DBB588D8B58840808040808092AAAAB8B4B4B2
                    B2B2A7A7A7FFFFFFFAFAFAFBFBFBFAFAFAFAFAFAFBFBFBFAFAFAFBFBFBFAFAFA
                    FFFFFF979797A0A0A0A4A0A0809898408080408080DBB588DBB588DBB588DBB5
                    88FFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFDA
                    B588D7B587DBB588D3B58740808040808093ABABBAB6B6B3B3B3A8A8A8FFFFFF
                    FBFBFBFCFCFCFBFBFBFBFBFBFBFBFBFBFBFBFBFBFBFBFBFBFFFFFF979797A0A0
                    A0A5A1A1809898408080408080DBB588DBB588DBB588DBB588FFFFFFFFFFFFFF
                    FFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFDBB588DBB588DBB588
                    DAB58840808040808095ADADBBB7B7B4B4B4A9A9A9FFFFFFFDFDFDFCFCFCFDFD
                    FDFCFCFCFDFDFDFBFBFBFDFDFDFCFCFCFFFFFF999999A3A3A3A6A2A2829A9A40
                    8080408080DBB588DBB588DBB588DBB588FFFFFFFFFFFFFFFFFFFFFFFFFFFFFF
                    FFFFFFFFFFFFFFFFFFFFFFFFFFFFFFDBB588DBB588DBB588DBB5884080804080
                    8095ADADBCB8B8B5B5B5AAAAAAFFFFFFFDFDFDFDFDFDFCFCFCFDFDFDFDFDFDFC
                    FCFCFCFCFCFEFEFEFFFFFF9B9B9BA3A3A3A8A4A4839B9B408080408080DBB588
                    DBB588DBB588DBB588FFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFF
                    FFFFFFFFFFFFFFDBB588DBB588DBB588DBB58840808040808095ADADBEBABAB6
                    B6B6ACACACFFFFFFFEFEFEFEFEFEFDFDFDFEFEFEFEFEFEFDFDFDFEFEFEFDFDFD
                    FFFFFF9C9C9CA6A6A6A9A5A5839B9B408080408080DBB588D3B688DBB588D6B5
                    87FFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFDB
                    B588DBB588DBB588DAB58840808040808097AFAFBFBBBBB8B8B8ADADADFFFFFF
                    FEFEFEFEFEFEFFFFFFFEFEFEFEFEFEFEFEFEFFFFFFFEFEFEFFFFFF9E9E9EA8A8
                    A8ABA7A7869E9E408080408080D6B688DBB588D8B688DBB588FFFFFFFFFFFFFF
                    FFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFDBB588DBB588DBB588
                    DAB58840808040808097AFAFBFBBBBB8B8B8B0B0B0FEFEFEFFFFFFFFFFFFFFFF
                    FFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFA0A0A0A8A8A8ACA8A8869E9E40
                    8080408080D8B588DBB588DBB588DBB588DBB588DBB588DBB588DBB588DBB588
                    DBB588DBB588DBB588DBB588DBB588DBB588DBB588DBB588DBB5884080804080
                    8099B1B1C1BDBDBABABAB9B9B9B0B0B0ADADADACACACAAAAAAA9A9A9A8A8A8A6
                    A6A6A5A5A5A3A3A3A3A3A3ABABABAAAAAAAEAAAA879F9F408080408080D8B588
                    DBB588DBB588DBB588D8B588DBB588DBB588DBB588DBB588D8B588DBB588DBB5
                    88DBB588DBB588DBB588DBB588DBB588DAB588408080408080A1B4B4C2BEBEBA
                    BABABBBBBBB8B8B8B7B7B7B6B6B6B5B5B5B3B3B3B3B3B3B2B2B2B1B1B1AEAEAE
                    ADADADADADADAAAAAAAFABAB8FA2A2408080408080A2AF86CABA88DBB588DBB5
                    88DBB588DBB588DBB588DBB588DBB588DBB588DBB588DBB588DBB588DBB588DB
                    B588DBB588DBB588189E7B408080408080539B9BD9C7C7C2BEBEC1BDBDBFBBBB
                    BFBBBBBDB9B9BCB8B8BBB7B7BAB6B6B9B5B5B7B3B3B6B2B2B3AFAFB3AFAFB1AD
                    ADC3B1B1499191408080408080408080A2AF87D8B588D5B588DBB588DBB588DB
                    B588DBB588DBB588DBB588DBB588DBB588DBB588DBB588DBB588D0B5871A9F7C
                    408080408080408080408080529B9BA1B4B498B0B098B0B097AFAF96AEAE95AD
                    AD93ABAB94ACAC92AAAA92AAAA90A8A88FA7A78EA6A694A7A74B949440808040
                    8080408080408080408080408080408080408080408080408080408080408080
                    4080804080804080804080804080804080804080804080804080804080804080
                    8040808040808040808040808040808040808040808040808040808040808040
                    8080408080408080408080408080408080408080408080408080}
                  NumGlyphs = 2
                  ParentDoubleBuffered = False
                  ParentFont = False
                  ParentShowHint = False
                  ShowHint = True
                  TabOrder = 1
                  OnClick = BrvBitBtn9Click
                  BrTipoBotao = BrBtnCarga
                end
              end
              object GroupBox1: TGroupBox
                Left = 6
                Top = 4
                Width = 523
                Height = 36
                Caption = 'Geral:'
                Font.Charset = DEFAULT_CHARSET
                Font.Color = clWindowText
                Font.Height = -11
                Font.Name = 'MS Sans Serif'
                Font.Style = [fsBold]
                ParentFont = False
                TabOrder = 0
                object Label4: TLabel
                  Left = 32
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
                  Left = 136
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
                  Left = 297
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
                  Left = 72
                  Top = 10
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
                  Left = 172
                  Top = 10
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
                  Left = 392
                  Top = 10
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
                Top = 41
                Width = 523
                Height = 36
                Caption = 'Marcados:'
                Font.Charset = DEFAULT_CHARSET
                Font.Color = clWindowText
                Font.Height = -11
                Font.Name = 'MS Sans Serif'
                Font.Style = [fsBold]
                ParentFont = False
                TabOrder = 1
                object Label43: TLabel
                  Left = 32
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
                object Label45: TLabel
                  Left = 136
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
                object Label46: TLabel
                  Left = 297
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
                object EdtTtCtCiMa: TBrvEditNum
                  Left = 72
                  Top = 10
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
                  Left = 172
                  Top = 10
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
                  Left = 392
                  Top = 10
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
            object Panel5: TPanel
              Left = 0
              Top = 157
              Width = 939
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
                935
                82)
              object LblQtCTRC: TLabel
                Left = 866
                Top = 4
                Width = 64
                Height = 13
                Alignment = taRightJustify
                Anchors = [akTop, akRight]
                Caption = 'LblQtCTRC'
              end
              object Panel15: TPanel
                Left = 1
                Top = 52
                Width = 933
                Height = 29
                Align = alBottom
                BevelOuter = bvNone
                BiDiMode = bdLeftToRight
                Font.Charset = DEFAULT_CHARSET
                Font.Color = clWindowText
                Font.Height = -11
                Font.Name = 'MS Sans Serif'
                Font.Style = [fsBold]
                ParentBiDiMode = False
                ParentFont = False
                TabOrder = 2
                object BrvBitBtn9: TBrvBitBtn
                  Left = 725
                  Top = 0
                  Width = 100
                  Height = 25
                  Hint = 'Carga'
                  Align = alCustom
                  Anchors = [akTop, akRight]
                  Caption = '&Carga'
                  DoubleBuffered = True
                  Font.Charset = DEFAULT_CHARSET
                  Font.Color = clWindowText
                  Font.Height = -11
                  Font.Name = 'Tahoma'
                  Font.Style = [fsBold]
                  Glyph.Data = {
                    96090000424D9609000000000000360000002800000028000000140000000100
                    18000000000060090000C30E0000C30E00000000000000000000408080408080
                    4080804080804080804080804080804080804080804080804080804080804080
                    8040808040808040808040808040808040808040808040808040808040808040
                    8080408080408080408080408080408080408080408080408080408080408080
                    408080408080408080408080408080408080408080408080A7AF85D2B587CCB5
                    87CCB586CAB486D3B587D3B587D8B588D3B587D8B588D3B587D8B587D3B487D3
                    B487DBB5889DAC824080804080804080804080804A92928FA2A288A0A0869E9E
                    869E9E859D9D839B9B8199998199998098987F97977E96967D95957C94948396
                    96438B8B408080408080408080A7AF85DBB588DBB588DBB588DBB588DBB588D8
                    B588DBB588DBB588DBB588DBB588DBB588DBB588DBB588DBB588DBB588D8B587
                    9DAC824080804080804B9393C3B1B1AEAAAAAEAAAAACA8A8ABA7A7A9A5A5A8A4
                    A4A6A2A2A4A0A0A39F9FA39F9FA19D9DA09C9C9F9B9B9E9A9AAF9D9D438B8B40
                    8080408080D5B588DBB588DBB588D3B587D3B587D8B588D8B588DBB588DBB588
                    DBB588DBB588DBB588DBB588DBB588DBB588DBB588DBB588D8B5874080804080
                    8093A6A6B2AEAEABABABA9A9A9A9A9A9A7A7A7A6A6A6A3A3A3A3A3A3A1A1A1A0
                    A0A09F9F9F9E9E9E9C9C9C9C9C9C9A9A9A9E9A9A839696408080408080CFB587
                    DBB588DBB588D0B687DBB588DBB588DBB588DBB588DBB588DBB588D3B586D3B5
                    86D0B485D3B586DBB588DBB588DBB588D3B4874080804080808DA5A5B3AFAFAC
                    ACACAAAAAAA1A1A19E9E9E9E9E9E9C9C9C9A9A9A979797969696959595939393
                    9393939C9C9C9A9A9A9F9B9B7D9595408080408080D3B587DBB588DBB588DBB5
                    88FFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFD3
                    B586DBB588DBB588D3B4874080804080808FA7A7B4B0B0AEAEAEA5A5A5F8F8F8
                    FFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFF9F9F99494949D9D
                    9DA19D9D7D9595408080408080DBB588DBB588DBB588D2B587FFFFFFFFFFFFFF
                    FFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFD0B485DBB588DBB588
                    D8B58740808040808091A9A9B6B2B2AEAEAEA4A4A4FFFFFFF8F8F8F9F9F9F8F8
                    F8FAFAFAF9F9F9FAFAFAF9F9F9FAFAFAFFFFFF9292929C9C9CA29E9E7E969640
                    8080408080DBB588DBB588DBB588D5B587FFFFFFFFFFFFFFFFFFFFFFFFFFFFFF
                    FFFFFFFFFFFFFFFFFFFFFFFFFFFFFFD3B586DBB588DBB588D3B5874080804080
                    8091A9A9B7B3B3B0B0B0A5A5A5FFFFFFFAFAFAFAFAFAFAFAFAF9F9F9FAFAFAFA
                    FAFAFAFAFAFAFAFAFFFFFF9494949E9E9EA29E9E7F9797408080408080DBB588
                    DBB588DBB588D6B587FFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFF
                    FFFFFFFFFFFFFFD8B587DBB588DBB588D8B58840808040808092AAAAB8B4B4B2
                    B2B2A7A7A7FFFFFFFAFAFAFBFBFBFAFAFAFAFAFAFBFBFBFAFAFAFBFBFBFAFAFA
                    FFFFFF979797A0A0A0A4A0A0809898408080408080DBB588DBB588DBB588DBB5
                    88FFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFDA
                    B588D7B587DBB588D3B58740808040808093ABABBAB6B6B3B3B3A8A8A8FFFFFF
                    FBFBFBFCFCFCFBFBFBFBFBFBFBFBFBFBFBFBFBFBFBFBFBFBFFFFFF979797A0A0
                    A0A5A1A1809898408080408080DBB588DBB588DBB588DBB588FFFFFFFFFFFFFF
                    FFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFDBB588DBB588DBB588
                    DAB58840808040808095ADADBBB7B7B4B4B4A9A9A9FFFFFFFDFDFDFCFCFCFDFD
                    FDFCFCFCFDFDFDFBFBFBFDFDFDFCFCFCFFFFFF999999A3A3A3A6A2A2829A9A40
                    8080408080DBB588DBB588DBB588DBB588FFFFFFFFFFFFFFFFFFFFFFFFFFFFFF
                    FFFFFFFFFFFFFFFFFFFFFFFFFFFFFFDBB588DBB588DBB588DBB5884080804080
                    8095ADADBCB8B8B5B5B5AAAAAAFFFFFFFDFDFDFDFDFDFCFCFCFDFDFDFDFDFDFC
                    FCFCFCFCFCFEFEFEFFFFFF9B9B9BA3A3A3A8A4A4839B9B408080408080DBB588
                    DBB588DBB588DBB588FFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFF
                    FFFFFFFFFFFFFFDBB588DBB588DBB588DBB58840808040808095ADADBEBABAB6
                    B6B6ACACACFFFFFFFEFEFEFEFEFEFDFDFDFEFEFEFEFEFEFDFDFDFEFEFEFDFDFD
                    FFFFFF9C9C9CA6A6A6A9A5A5839B9B408080408080DBB588D3B688DBB588D6B5
                    87FFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFDB
                    B588DBB588DBB588DAB58840808040808097AFAFBFBBBBB8B8B8ADADADFFFFFF
                    FEFEFEFEFEFEFFFFFFFEFEFEFEFEFEFEFEFEFFFFFFFEFEFEFFFFFF9E9E9EA8A8
                    A8ABA7A7869E9E408080408080D6B688DBB588D8B688DBB588FFFFFFFFFFFFFF
                    FFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFDBB588DBB588DBB588
                    DAB58840808040808097AFAFBFBBBBB8B8B8B0B0B0FEFEFEFFFFFFFFFFFFFFFF
                    FFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFA0A0A0A8A8A8ACA8A8869E9E40
                    8080408080D8B588DBB588DBB588DBB588DBB588DBB588DBB588DBB588DBB588
                    DBB588DBB588DBB588DBB588DBB588DBB588DBB588DBB588DBB5884080804080
                    8099B1B1C1BDBDBABABAB9B9B9B0B0B0ADADADACACACAAAAAAA9A9A9A8A8A8A6
                    A6A6A5A5A5A3A3A3A3A3A3ABABABAAAAAAAEAAAA879F9F408080408080D8B588
                    DBB588DBB588DBB588D8B588DBB588DBB588DBB588DBB588D8B588DBB588DBB5
                    88DBB588DBB588DBB588DBB588DBB588DAB588408080408080A1B4B4C2BEBEBA
                    BABABBBBBBB8B8B8B7B7B7B6B6B6B5B5B5B3B3B3B3B3B3B2B2B2B1B1B1AEAEAE
                    ADADADADADADAAAAAAAFABAB8FA2A2408080408080A2AF86CABA88DBB588DBB5
                    88DBB588DBB588DBB588DBB588DBB588DBB588DBB588DBB588DBB588DBB588DB
                    B588DBB588DBB588189E7B408080408080539B9BD9C7C7C2BEBEC1BDBDBFBBBB
                    BFBBBBBDB9B9BCB8B8BBB7B7BAB6B6B9B5B5B7B3B3B6B2B2B3AFAFB3AFAFB1AD
                    ADC3B1B1499191408080408080408080A2AF87D8B588D5B588DBB588DBB588DB
                    B588DBB588DBB588DBB588DBB588DBB588DBB588DBB588DBB588D0B5871A9F7C
                    408080408080408080408080529B9BA1B4B498B0B098B0B097AFAF96AEAE95AD
                    AD93ABAB94ACAC92AAAA92AAAA90A8A88FA7A78EA6A694A7A74B949440808040
                    8080408080408080408080408080408080408080408080408080408080408080
                    4080804080804080804080804080804080804080804080804080804080804080
                    8040808040808040808040808040808040808040808040808040808040808040
                    8080408080408080408080408080408080408080408080408080}
                  NumGlyphs = 2
                  ParentDoubleBuffered = False
                  ParentFont = False
                  ParentShowHint = False
                  ShowHint = True
                  TabOrder = 0
                  OnClick = BrvBitBtn9Click
                  BrTipoBotao = BrBtnCarga
                end
                object BrvBitBtn8: TBrvBitBtn
                  Left = 829
                  Top = 0
                  Width = 100
                  Height = 25
                  Hint = 'Voltar'
                  Align = alCustom
                  Anchors = [akTop, akRight]
                  Caption = '&Voltar'
                  DoubleBuffered = True
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
                    8000808000808000808000808000808000808000808000808000808000808000
                    8080008080008080008080008080008080008080008080008080008080008080
                    0080800080800080800080800080800080800080800080800080800080800080
                    8000808000808000808000808000808000808000808000808000808000808000
                    8080637D5BD67B2F008080008080008080008080008080008080008080008080
                    0080800080800080800080800080800080800080800080800080800080804579
                    7997727200808000808000808000808000808000808000808000808000808000
                    808000808000808000808000808000808000808000808065815FB88143C08141
                    0080800080800080800080800080800080800080800080800080800080800080
                    80008080008080008080008080008080008080487C7C8378788A797900808000
                    8080008080008080008080008080008080008080008080008080008080008080
                    0080800080800080800080805B8467C28748AF874EC488480080800080800080
                    8000808000808000808000808000808000808000808000808000808000808000
                    80800080800080804280808D7E7E8080808F7F7F008080008080008080008080
                    0080800080800080800080800080800080800080800080800080800080800080
                    80778A65C78F53B48E58B48E58C28F5400808000808000808000808000808000
                    8080008080008080008080008080008080008080008080008080008080598484
                    9486868686868686869187870080800080800080800080800080800080800080
                    800080800080800080800080800080800080800080805F8A6ECB955AB7935EB7
                    935EB7935EB7935EC5945BC5945BC5945BC5945BC5945BC5945BC5945BC5945B
                    D496580080800080800080800080800080804986869A8C8C8B8B8B8B8B8B8A8A
                    8A8B8B8B968C8C968C8C968C8C958B8B968C8C968C8C968C8C968C8CA08C8C00
                    80800080800080800080806E8C6FCF985FBA9563BA9563BA9563BA9563BA9563
                    BA9563BA9563BA9563BA9563BA9563BA9563BA9563BA9563C997600080800080
                    800080800080805488889D8F8F8E8E8E8D8D8D8E8E8E8E8E8E8D8D8D8D8D8D8D
                    8D8D8D8D8D8E8E8E8D8D8D8D8D8D8E8E8E8D8D8D988E8E008080008080008080
                    708F71CD9B64BD9966BD9966BD9966BD9966BD9966BD9966BD9966BD9966BD99
                    66BD9966BD9966BD9966BD9966BD9966CB9B64008080008080008080568A8A9D
                    9292919191919191919191919191919191919191919191919191919191919191
                    9191919191919191919191919C92920080800080806E9073D49F67BE9C6ABE9C
                    6ABE9C6ABE9C6ABE9C6ABE9C6ABE9C6ABE9C6ABE9C6ABE9C6ABE9C6ABE9C6ABE
                    9C6ABE9C6ABE9C6ACD9E68008080008080568C8CA59696949494949494949494
                    9494949494949494949494949494949494949494949393939494949393939494
                    949494949F9595008080008080709175D7A26BC19E6DC19E6DC19E6DC19E6DC1
                    9E6DC19E6DC19E6DC19E6DC19E6DC19E6DC19E6DC19E6DC19E6DC19E6DC19E6D
                    D0A16C008080008080578D8DA899999796969696969696969696969696969696
                    96969696969696969696969696969696969696969696969696969696A2989800
                    8080008080008080739377D3A46FC3A170C2A170C2A170C2A170C2A170C2A170
                    C2A170C2A170C2A170C2A170C2A170C2A170C2A170C2A170D1A46F0080800080
                    800080805A8F8FA69B9B99989899999999999999999999999998989899999999
                    9999999999999999999999999999989898999999A59B9B008080008080008080
                    008080759578DCA872C5A474C5A474C5A474C5A474C5A474C5A474C5A474C5A4
                    74C5A474C5A474C5A474C5A474C5A474D4A6730080800080800080800080805C
                    9090AD9E9E9B9B9B9B9B9B9C9C9C9C9C9C9C9C9C9C9C9C9C9C9C9C9C9C9C9C9C
                    9C9C9C9B9B9B9C9C9C9C9C9CA89E9E0080800080800080800080800080806894
                    7ADEAA76C8A677C8A677C8A677C8A677D8A976D8A976D8A976D8A976D8A976D8
                    A976D8A976D8A976E7AC76008080008080008080008080008080528F8FAFA1A1
                    9E9E9E9E9E9E9E9E9E9E9E9EAAA0A0AAA0A0ABA1A1AAA0A0AAA0A0ABA1A1AAA0
                    A0AAA0A0B7A3A3008080008080008080008080008080008080869A7CDFAC79C9
                    A779C9A779D9AA79008080008080008080008080008080008080008080008080
                    008080008080008080008080008080008080008080699494B1A3A39F9F9FA0A0
                    A0ACA2A200808000808000808000808000808000808000808000808000808000
                    808000808000808000808000808000808000808069957EE1AE7CCAA97CE3AE7C
                    0080800080800080800080800080800080800080800080800080800080800080
                    80008080008080008080008080008080539191B4A5A5A1A1A1B5A5A500808000
                    8080008080008080008080008080008080008080008080008080008080008080
                    00808000808000808000808000808078997EDDAE7DE5B07D0080800080800080
                    8000808000808000808000808000808000808000808000808000808000808000
                    8080008080008080008080619595B0A5A5B7A7A7008080008080008080008080
                    0080800080800080800080800080800080800080800080800080800080800080
                    8000808000808000808079997FFFB77D00808000808000808000808000808000
                    8080008080008080008080008080008080008080008080008080008080008080
                    008080008080609595D2ADAD0080800080800080800080800080800080800080
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
                  TabOrder = 1
                  OnClick = BrvBitBtn8Click
                  BrTipoBotao = BrBtnSetaEsquerda
                end
              end
              object GroupBox8: TGroupBox
                Left = 6
                Top = 4
                Width = 523
                Height = 36
                Caption = 'Geral:'
                Font.Charset = DEFAULT_CHARSET
                Font.Color = clWindowText
                Font.Height = -11
                Font.Name = 'MS Sans Serif'
                Font.Style = [fsBold]
                ParentFont = False
                TabOrder = 0
                object Label6: TLabel
                  Left = 32
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
                  Left = 136
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
                  Left = 297
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
                  Left = 72
                  Top = 10
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
                  Left = 172
                  Top = 10
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
                  Left = 392
                  Top = 10
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
                Top = 41
                Width = 523
                Height = 36
                Caption = 'Marcados:'
                Font.Charset = DEFAULT_CHARSET
                Font.Color = clWindowText
                Font.Height = -11
                Font.Name = 'MS Sans Serif'
                Font.Style = [fsBold]
                ParentFont = False
                TabOrder = 1
                object Label49: TLabel
                  Left = 32
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
                object Label50: TLabel
                  Left = 136
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
                object Label53: TLabel
                  Left = 297
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
                object EdtTtCtCtMa: TBrvEditNum
                  Left = 72
                  Top = 10
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
                  Left = 172
                  Top = 10
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
                  Left = 392
                  Top = 10
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
              Width = 939
              Height = 157
              BrShowMemo = True
              BrReadOnlyStyle = []
              BrReadOnlyColor = clBlack
              Align = alClient
              DataSource = DtsCTRC
              Options = [dgEditing, dgTitles, dgIndicator, dgColumnResize, dgColLines, dgRowLines, dgTabs, dgCancelOnExit]
              PopupMenu = PopCtrc
              TabOrder = 0
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
              BrGradeZebrada = False
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
          Width = 947
          Height = 24
          Align = alTop
          BorderStyle = bsSingle
          Font.Charset = DEFAULT_CHARSET
          Font.Color = clWindowText
          Font.Height = -11
          Font.Name = 'Tahoma'
          Font.Style = [fsBold]
          ParentFont = False
          TabOrder = 1
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
            Width = 103
            Height = 13
            Caption = 'Entrega agendada'
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
        object Panel6: TPanel
          Left = 0
          Top = 393
          Width = 947
          Height = 57
          Align = alBottom
          BorderStyle = bsSingle
          Font.Charset = DEFAULT_CHARSET
          Font.Color = clWindowText
          Font.Height = -11
          Font.Name = 'MS Sans Serif'
          Font.Style = [fsBold]
          ParentFont = False
          TabOrder = 3
          DesignSize = (
            943
            53)
          object LblQtCarga: TLabel
            Left = 872
            Top = 5
            Width = 64
            Height = 13
            Alignment = taRightJustify
            Anchors = [akTop, akRight]
            Caption = 'LblQtCarga'
          end
          object Panel16: TPanel
            Left = 1
            Top = 23
            Width = 941
            Height = 29
            Align = alBottom
            BevelOuter = bvNone
            BiDiMode = bdLeftToRight
            Font.Charset = DEFAULT_CHARSET
            Font.Color = clWindowText
            Font.Height = -11
            Font.Name = 'MS Sans Serif'
            Font.Style = [fsBold]
            ParentBiDiMode = False
            ParentFont = False
            TabOrder = 1
            ExplicitTop = 52
            ExplicitWidth = 933
            object BrvBitBtn10: TBrvBitBtn
              Left = 835
              Top = 0
              Width = 100
              Height = 25
              Hint = 'Voltar'
              Align = alCustom
              Anchors = [akTop, akRight]
              Caption = '&Voltar'
              DoubleBuffered = True
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
                8000808000808000808000808000808000808000808000808000808000808000
                8080008080008080008080008080008080008080008080008080008080008080
                0080800080800080800080800080800080800080800080800080800080800080
                8000808000808000808000808000808000808000808000808000808000808000
                8080637D5BD67B2F008080008080008080008080008080008080008080008080
                0080800080800080800080800080800080800080800080800080800080804579
                7997727200808000808000808000808000808000808000808000808000808000
                808000808000808000808000808000808000808000808065815FB88143C08141
                0080800080800080800080800080800080800080800080800080800080800080
                80008080008080008080008080008080008080487C7C8378788A797900808000
                8080008080008080008080008080008080008080008080008080008080008080
                0080800080800080800080805B8467C28748AF874EC488480080800080800080
                8000808000808000808000808000808000808000808000808000808000808000
                80800080800080804280808D7E7E8080808F7F7F008080008080008080008080
                0080800080800080800080800080800080800080800080800080800080800080
                80778A65C78F53B48E58B48E58C28F5400808000808000808000808000808000
                8080008080008080008080008080008080008080008080008080008080598484
                9486868686868686869187870080800080800080800080800080800080800080
                800080800080800080800080800080800080800080805F8A6ECB955AB7935EB7
                935EB7935EB7935EC5945BC5945BC5945BC5945BC5945BC5945BC5945BC5945B
                D496580080800080800080800080800080804986869A8C8C8B8B8B8B8B8B8A8A
                8A8B8B8B968C8C968C8C968C8C958B8B968C8C968C8C968C8C968C8CA08C8C00
                80800080800080800080806E8C6FCF985FBA9563BA9563BA9563BA9563BA9563
                BA9563BA9563BA9563BA9563BA9563BA9563BA9563BA9563C997600080800080
                800080800080805488889D8F8F8E8E8E8D8D8D8E8E8E8E8E8E8D8D8D8D8D8D8D
                8D8D8D8D8D8E8E8E8D8D8D8D8D8D8E8E8E8D8D8D988E8E008080008080008080
                708F71CD9B64BD9966BD9966BD9966BD9966BD9966BD9966BD9966BD9966BD99
                66BD9966BD9966BD9966BD9966BD9966CB9B64008080008080008080568A8A9D
                9292919191919191919191919191919191919191919191919191919191919191
                9191919191919191919191919C92920080800080806E9073D49F67BE9C6ABE9C
                6ABE9C6ABE9C6ABE9C6ABE9C6ABE9C6ABE9C6ABE9C6ABE9C6ABE9C6ABE9C6ABE
                9C6ABE9C6ABE9C6ACD9E68008080008080568C8CA59696949494949494949494
                9494949494949494949494949494949494949494949393939494949393939494
                949494949F9595008080008080709175D7A26BC19E6DC19E6DC19E6DC19E6DC1
                9E6DC19E6DC19E6DC19E6DC19E6DC19E6DC19E6DC19E6DC19E6DC19E6DC19E6D
                D0A16C008080008080578D8DA899999796969696969696969696969696969696
                96969696969696969696969696969696969696969696969696969696A2989800
                8080008080008080739377D3A46FC3A170C2A170C2A170C2A170C2A170C2A170
                C2A170C2A170C2A170C2A170C2A170C2A170C2A170C2A170D1A46F0080800080
                800080805A8F8FA69B9B99989899999999999999999999999998989899999999
                9999999999999999999999999999989898999999A59B9B008080008080008080
                008080759578DCA872C5A474C5A474C5A474C5A474C5A474C5A474C5A474C5A4
                74C5A474C5A474C5A474C5A474C5A474D4A6730080800080800080800080805C
                9090AD9E9E9B9B9B9B9B9B9C9C9C9C9C9C9C9C9C9C9C9C9C9C9C9C9C9C9C9C9C
                9C9C9C9B9B9B9C9C9C9C9C9CA89E9E0080800080800080800080800080806894
                7ADEAA76C8A677C8A677C8A677C8A677D8A976D8A976D8A976D8A976D8A976D8
                A976D8A976D8A976E7AC76008080008080008080008080008080528F8FAFA1A1
                9E9E9E9E9E9E9E9E9E9E9E9EAAA0A0AAA0A0ABA1A1AAA0A0AAA0A0ABA1A1AAA0
                A0AAA0A0B7A3A3008080008080008080008080008080008080869A7CDFAC79C9
                A779C9A779D9AA79008080008080008080008080008080008080008080008080
                008080008080008080008080008080008080008080699494B1A3A39F9F9FA0A0
                A0ACA2A200808000808000808000808000808000808000808000808000808000
                808000808000808000808000808000808000808069957EE1AE7CCAA97CE3AE7C
                0080800080800080800080800080800080800080800080800080800080800080
                80008080008080008080008080008080539191B4A5A5A1A1A1B5A5A500808000
                8080008080008080008080008080008080008080008080008080008080008080
                00808000808000808000808000808078997EDDAE7DE5B07D0080800080800080
                8000808000808000808000808000808000808000808000808000808000808000
                8080008080008080008080619595B0A5A5B7A7A7008080008080008080008080
                0080800080800080800080800080800080800080800080800080800080800080
                8000808000808000808079997FFFB77D00808000808000808000808000808000
                8080008080008080008080008080008080008080008080008080008080008080
                008080008080609595D2ADAD0080800080800080800080800080800080800080
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
              TabOrder = 2
              OnClick = BrvBitBtn10Click
              BrTipoBotao = BrBtnSetaEsquerda
            end
            object BrvBitBtn14: TBrvBitBtn
              Left = 627
              Top = 0
              Width = 100
              Height = 25
              Hint = 'Salvar'
              Align = alCustom
              Anchors = [akTop, akRight]
              Caption = '&Salvar'
              DoubleBuffered = True
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
                008080008080008080008080008080008080008080008080008080008080587A
                579F9A73CADED2A4A8888277447E723EC9DDD1C2D3C3C2D3C3C2D3C3C9DDD17E
                723E867C4B897C4A6C7D550080800080800080800080800080803C7575809595
                C3DBDB8BA3A3597171546C6CC1D9D9B8D0D0B7CFCFB8D0D0C1D9D9536B6B5F77
                776075754C787800808000808000808000808063805DB77936C7A577FFFBEFD3
                B58FA87839A37130FFF9EDF9ECDAF9ECDAF9ECDAFFF9EDA37130AC7F42B27F40
                8C7F4E008080008080008080008080477B7B807272A29E9EFBF7F7B4B0B07470
                706D6969F9F5F5ECE8E8ECE8E8ECE8E8FAF6F66D69697A76767F777764797900
                8080008080008080668261BA8446A97F44C5A87FFFFEF7D1BA96A77D42A37638
                FEFCF4F5EFE1F5EFE1F5EFE1FEFCF4A37638AC844BB184498C83550080800080
                800080804A7E7E867B7B777777A4A4A4FBFBFBB4B4B47575756E6E6EFAFAFAEC
                ECECECECECECECECFAFAFA6E6E6E7D7D7D7F7B7B657D7D008080008080008080
                CC8A49B08951AC844AC8AE86FFFFFECFB894A275359D6F2CFFFFFCF8F3E9F8F3
                E9F8F3E9FFFFFCA67C3EAF8951B589508F875A00808000808000808096818181
                81817C7C7CA8A8A8FFFFFFB3B3B36C6C6C656565FFFFFFF1F1F1F2F2F2F1F1F1
                FEFEFE737373818181868282688080008080008080008080C99257B5905BB28B
                54D0B793FFFFFFFFFFFFFFFFFFFFFFFFFFFFFEFFFFFEFFFFFEFFFFFEFFFFFFAC
                8348B5905BBB915A938D62008080008080008080978989888888838383B1B1B1
                FFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFF7A7A7A8888
                888D89896E8686008080008080008080CD955CB8935FB8925EBD9A69C6A97EC5
                A77BC5A77BC5A77BC5A77BC5A77BC5A77BC5A77BC7AA80B6905BB8935FBE945E
                9690660080800080800080809B8D8D8B8B8B8B8B8B939393A2A2A2A0A0A0A0A0
                A0A0A0A0A0A0A0A0A0A0A0A0A0A0A0A0A3A3A38888888B8B8B908C8C71898900
                8080008080008080CE9960BA9763BA9763B99561B8935EB8935EB8935EB8935E
                B8935EB8935EB8935EB8935EB8935EBA9763BA9763C097629793690080800080
                800080809E90908F8F8F8F8F8F8D8D8D8B8B8B8B8B8B8B8B8B8A8A8A8B8B8B8B
                8B8B8B8B8B8B8B8B8B8B8B8F8F8F8F8F8F938F8F748C8C008080008080008080
                D19C64BD9967BD9967BD9967BD9967BD9967BD9967BD9967BD9967BD9967BD99
                67BD9967BD9967BD9967BD9967C29A669A946B008080008080008080A1939391
                9191919191919191919191919191919191919191919191919191919191919191
                919191919191919191959191768E8E008080008080008080D39F67BE9C6ABE9C
                6AB9945FB8935DB8935DB8935DB8935DB8935DB8935DB8935DB8935DB8935DBB
                9864BE9C6AC49D699A976E008080008080008080A496969494949494948C8C8C
                8A8A8A8A8A8A8A8A8A8A8A8A8A8A8A8989898A8A8A8A8A8A8A8A8A9090909393
                93999595789090008080008080008080D6A26BC19E6DBC9763F1E8D4F6F1E2F6
                F0E0F6F0E0F6F0E0F6F0E0F6F0E0F6F0E0F6F0E0F9F4E6D9C3A0BE9A67C79F6D
                9D9971008080008080008080A799999696968E8E8EE3E3E3EEEEEEECECECECEC
                ECEBEBEBECECECEBEBEBECECECEBEBEBF1F1F1BBBBBB9393939B97977A929200
                8080008080008080D8A46FC2A070BD9864F7F2E4F0E8D5F0E8D5F0E8D5F0E8D5
                F0E8D5F0E8D5F0E8D5F0E8D5F2EBDAE2D1B4BF9B68C8A1709E9A730080800080
                80008080A99B9B9898988F8F8FEFEFEFE4E4E4E4E4E4E4E4E4E4E4E4E3E3E3E3
                E3E3E3E3E3E4E4E4E7E7E7CCCCCC9292929D99997C9494008080008080008080
                D9A772C4A374BE9A67F8F3E7F3EADAF3EADAF3EADAF3EADAF3EADAF3EADAF3EA
                DAF3EADAF5EEDFE2D2B7C09E6CCAA4739F9C76008080008080008080AC9E9E9B
                9B9B929292F0F0F0E6E6E6E8E8E8E6E6E6E6E6E6E7E7E7E7E7E7E7E7E7E8E8E8
                EAEAEACDCDCD949494A09C9C7E9696008080008080008080DCA975C7A576C19C
                69FAF7EDF5EEE0F5EEE0F5EEE0F5EEE0F5EEE0F5EEE0F5EEE0F5EEE0F7F1E5E5
                D5BBC39F6ECDA676A19E78008080008080008080AEA0A09D9D9D939393F4F4F4
                EBEBEBEBEBEBEBEBEBEBEBEBEBEBEBEBEBEBEBEBEBEBEBEBEEEEEED0D0D09898
                98A29E9E809898008080008080008080DEAC77C8A778C29E6AFDFAF3F7F1E5F7
                F1E5F7F1E5F7F1E5F7F1E5F7F1E5F7F1E5F7F1E5F9F4EAE7D7C0C4A170CEA878
                A3A07A008080008080008080B1A3A39F9F9F959595F8F8F8EFEFEFEEEEEEEEEE
                EEEEEEEEEEEEEEEFEFEFEFEFEFEEEEEEF1F1F1D3D3D3999999A4A0A081999900
                8080008080008080E0AD7BCAA97BC4A06DFEFDF7F8F3E9F8F3E9F8F3E9F8F3E9
                F8F3E9F8F3E9F8F3E9F8F3E9FAF7EFE9DAC3C6A372D0AA7BA4A27C0080800080
                80008080B3A5A5A1A1A1979797FBFBFBF1F1F1F2F2F2F0F0F0F2F2F2F1F1F1F1
                F1F1F1F1F1F1F1F1F4F4F4D5D5D59C9C9CA6A2A2839B9B008080008080008080
                E1AF7CCBAA7CC5A16EFFFFFAF9F5ECF9F5ECF9F5ECF9F5ECF9F5ECF9F5ECF9F5
                ECF9F5ECFCF9F1E9DCC6C7A574D1AC7CA4A27D008080008080008080B4A6A6A2
                A2A2989898FDFDFDF3F3F3F4F4F4F3F3F3F4F4F4F3F3F3F3F3F3F3F3F3F3F3F3
                F7F7F7D8D8D89C9C9CA7A3A3849C9C008080008080008080E8B17DD2AC7ECCA2
                6FFFFFFFFFFFFCFFFFFCFFFFFCFFFFFCFFFFFCFFFFFCFFFFFCFFFFFCFFFFFFF4
                E4D1CEA675D8AD7DAAA37F008080008080008080B9A7A7A8A4A49F9B9BFFFFFF
                FFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFE3DFDFA19D
                9DADA5A5879C9C008080008080008080B6A67EA5A27EA5A17CA8A683A7A582A7
                A582A7A582A7A582A7A582A7A582A7A582A7A582A7A582A6A481A5A27DAAA37E
                859B7F008080008080008080919F9F849C9C839B9B88A0A0879F9F88A0A0879F
                9F879F9F879F9F879F9F88A0A0879F9F879F9F879F9F839B9B889D9D6B979700
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
              OnClick = BrvBitBtn14Click
              BrTipoBotao = BrBtnSalvar
            end
            object BrvBitBtn15: TBrvBitBtn
              Left = 731
              Top = 0
              Width = 100
              Height = 25
              Hint = 'Finalizar'
              Align = alCustom
              Anchors = [akTop, akRight]
              Caption = '&Finalizar'
              DoubleBuffered = True
              Font.Charset = DEFAULT_CHARSET
              Font.Color = clWindowText
              Font.Height = -11
              Font.Name = 'Tahoma'
              Font.Style = [fsBold]
              Glyph.Data = {
                96090000424D9609000000000000360000002800000028000000140000000100
                18000000000060090000C30E0000C30E00000000000000000000408080408080
                4080804080804080804080804080804080804080804080804080804080804080
                8040808040808040808040808040808040808040808040808040808040808040
                8080408080408080408080408080408080408080408080408080408080408080
                408080408080408080408080408080408080408080408080A7AF85D2B587CCB5
                87CCB586CAB486D3B587D3B587D8B588D3B587D8B588D3B587D8B587D3B487D3
                B487DBB5889DAC824080804080804080804080804A92928FA2A288A0A0869E9E
                869E9E859D9D839B9B8199998199998098987F97977E96967D95957C94948396
                96438B8B408080408080408080A7AF85DBB588DBB588DBB588DBB588DBB588D8
                B588DBB588DBB588DBB588DBB588DBB588DBB588DBB588DBB588DBB588D8B587
                9DAC824080804080804B9393C3B1B1AEAAAAAEAAAAACA8A8ABA7A7A9A5A5A8A4
                A4A6A2A2A4A0A0A39F9FA39F9FA19D9DA09C9C9F9B9B9E9A9AAF9D9D438B8B40
                8080408080D5B588DBB588DBB588D3B587D3B587D8B588D8B588DBB588DBB588
                DBB588DBB588DBB588DBB588DBB588DBB588DBB588DBB588D8B5874080804080
                8093A6A6B2AEAEABABABA9A9A9A9A9A9A7A7A7A6A6A6A3A3A3A3A3A3A1A1A1A0
                A0A09F9F9F9E9E9E9C9C9C9C9C9C9A9A9A9E9A9A839696408080408080CFB587
                DBB588DBB588D0B687DBB588DBB588DBB588DBB588DBB588DBB588D3B586D3B5
                86D0B485D3B586DBB588DBB588DBB588D3B4874080804080808DA5A5B3AFAFAC
                ACACAAAAAAA1A1A19E9E9E9E9E9E9C9C9C9A9A9A979797969696959595939393
                9393939C9C9C9A9A9A9F9B9B7D9595408080408080D3B587DBB588DBB588DBB5
                88FFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFD3
                B586DBB588DBB588D3B4874080804080808FA7A7B4B0B0AEAEAEA5A5A5F8F8F8
                FFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFF9F9F99494949D9D
                9DA19D9D7D9595408080408080DBB588DBB588DBB588D2B587FFFFFFFFFFFFFF
                FFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFD0B485DBB588DBB588
                D8B58740808040808091A9A9B6B2B2AEAEAEA4A4A4FFFFFFF8F8F8F9F9F9F8F8
                F8FAFAFAF9F9F9FAFAFAF9F9F9FAFAFAFFFFFF9292929C9C9CA29E9E7E969640
                8080408080DBB588DBB588DBB588D5B587FFFFFFFFFFFFFFFFFFFFFFFFFFFFFF
                FFFFFFFFFFFFFFFFFFFFFFFFFFFFFFD3B586DBB588DBB588D3B5874080804080
                8091A9A9B7B3B3B0B0B0A5A5A5FFFFFFFAFAFAFAFAFAFAFAFAF9F9F9FAFAFAFA
                FAFAFAFAFAFAFAFAFFFFFF9494949E9E9EA29E9E7F9797408080408080DBB588
                DBB588DBB588D6B587FFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFF
                FFFFFFFFFFFFFFD8B587DBB588DBB588D8B58840808040808092AAAAB8B4B4B2
                B2B2A7A7A7FFFFFFFAFAFAFBFBFBFAFAFAFAFAFAFBFBFBFAFAFAFBFBFBFAFAFA
                FFFFFF979797A0A0A0A4A0A0809898408080408080DBB588DBB588DBB588DBB5
                88FFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFDA
                B588D7B587DBB588D3B58740808040808093ABABBAB6B6B3B3B3A8A8A8FFFFFF
                FBFBFBFCFCFCFBFBFBFBFBFBFBFBFBFBFBFBFBFBFBFBFBFBFFFFFF979797A0A0
                A0A5A1A1809898408080408080DBB588DBB588DBB588DBB588FFFFFFFFFFFFFF
                FFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFDBB588DBB588DBB588
                DAB58840808040808095ADADBBB7B7B4B4B4A9A9A9FFFFFFFDFDFDFCFCFCFDFD
                FDFCFCFCFDFDFDFBFBFBFDFDFDFCFCFCFFFFFF999999A3A3A3A6A2A2829A9A40
                8080408080DBB588DBB588DBB588DBB588FFFFFFFFFFFFFFFFFFFFFFFFFFFFFF
                FFFFFFFFFFFFFFFFFFFFFFFFFFFFFFDBB588DBB588DBB588DBB5884080804080
                8095ADADBCB8B8B5B5B5AAAAAAFFFFFFFDFDFDFDFDFDFCFCFCFDFDFDFDFDFDFC
                FCFCFCFCFCFEFEFEFFFFFF9B9B9BA3A3A3A8A4A4839B9B408080408080DBB588
                DBB588DBB588DBB588FFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFF
                FFFFFFFFFFFFFFDBB588DBB588DBB588DBB58840808040808095ADADBEBABAB6
                B6B6ACACACFFFFFFFEFEFEFEFEFEFDFDFDFEFEFEFEFEFEFDFDFDFEFEFEFDFDFD
                FFFFFF9C9C9CA6A6A6A9A5A5839B9B408080408080DBB588D3B688DBB588D6B5
                87FFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFDB
                B588DBB588DBB588DAB58840808040808097AFAFBFBBBBB8B8B8ADADADFFFFFF
                FEFEFEFEFEFEFFFFFFFEFEFEFEFEFEFEFEFEFFFFFFFEFEFEFFFFFF9E9E9EA8A8
                A8ABA7A7869E9E408080408080D6B688DBB588D8B688DBB588FFFFFFFFFFFFFF
                FFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFDBB588DBB588DBB588
                DAB58840808040808097AFAFBFBBBBB8B8B8B0B0B0FEFEFEFFFFFFFFFFFFFFFF
                FFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFA0A0A0A8A8A8ACA8A8869E9E40
                8080408080D8B588DBB588DBB588DBB588DBB588DBB588DBB588DBB588DBB588
                DBB588DBB588DBB588DBB588DBB588DBB588DBB588DBB588DBB5884080804080
                8099B1B1C1BDBDBABABAB9B9B9B0B0B0ADADADACACACAAAAAAA9A9A9A8A8A8A6
                A6A6A5A5A5A3A3A3A3A3A3ABABABAAAAAAAEAAAA879F9F408080408080D8B588
                DBB588DBB588DBB588D8B588DBB588DBB588DBB588DBB588D8B588DBB588DBB5
                88DBB588DBB588DBB588DBB588DBB588DAB588408080408080A1B4B4C2BEBEBA
                BABABBBBBBB8B8B8B7B7B7B6B6B6B5B5B5B3B3B3B3B3B3B2B2B2B1B1B1AEAEAE
                ADADADADADADAAAAAAAFABAB8FA2A2408080408080A2AF86CABA88DBB588DBB5
                88DBB588DBB588DBB588DBB588DBB588DBB588DBB588DBB588DBB588DBB588DB
                B588DBB588DBB588189E7B408080408080539B9BD9C7C7C2BEBEC1BDBDBFBBBB
                BFBBBBBDB9B9BCB8B8BBB7B7BAB6B6B9B5B5B7B3B3B6B2B2B3AFAFB3AFAFB1AD
                ADC3B1B1499191408080408080408080A2AF87D8B588D5B588DBB588DBB588DB
                B588DBB588DBB588DBB588DBB588DBB588DBB588DBB588DBB588D0B5871A9F7C
                408080408080408080408080529B9BA1B4B498B0B098B0B097AFAF96AEAE95AD
                AD93ABAB94ACAC92AAAA92AAAA90A8A88FA7A78EA6A694A7A74B949440808040
                8080408080408080408080408080408080408080408080408080408080408080
                4080804080804080804080804080804080804080804080804080804080804080
                8040808040808040808040808040808040808040808040808040808040808040
                8080408080408080408080408080408080408080408080408080}
              NumGlyphs = 2
              ParentDoubleBuffered = False
              ParentFont = False
              ParentShowHint = False
              ShowHint = True
              TabOrder = 1
              OnClick = BrvBitBtn15Click
              BrTipoBotao = BrBtnCarga
            end
          end
          object GroupBox4: TGroupBox
            Left = 6
            Top = 5
            Width = 448
            Height = 43
            Align = alCustom
            Caption = 'Totais:'
            Font.Charset = DEFAULT_CHARSET
            Font.Color = clWindowText
            Font.Height = -11
            Font.Name = 'MS Sans Serif'
            Font.Style = [fsBold]
            ParentFont = False
            TabOrder = 0
            object Label38: TLabel
              Left = 263
              Top = 18
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
              Left = 350
              Top = 18
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
              Left = 158
              Top = 18
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
              Left = 125
              Top = 18
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
              Left = 8
              Top = 18
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
              Left = 41
              Top = 18
              Width = 70
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
        end
        object Panel7: TPanel
          Left = 0
          Top = 0
          Width = 947
          Height = 34
          Align = alTop
          BorderStyle = bsSingle
          Font.Charset = DEFAULT_CHARSET
          Font.Color = clWindowText
          Font.Height = -11
          Font.Name = 'MS Sans Serif'
          Font.Style = [fsBold]
          ParentFont = False
          TabOrder = 0
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
              96090000424D9609000000000000360000002800000028000000140000000100
              18000000000060090000C40E0000C40E00000000000000000000008080008080
              0080800080800080800080800080800080800080800080800080800080800080
              8000808000808000808000808000808000808000808000808000808000808000
              8080008080008080008080008080008080008080008080008080008080008080
              0080800080800080800080800080800080800080800080800080800080800080
              80008080008080F0D8DCD3C4C6C1AAA9066E6E4C8787D9C5C5F6E2E2E9D6D6C3
              C0C0008080008080008080008080008080008080008080008080008080008080
              65A0A0EFDADAD2C5C5C1A9A9066E6E4C8787D9C5C5F3E5E5E7D8D8C3C0C00080
              80008080008080008080008080008080008080008080008080E5D4D4F9F3F3FF
              FFFFD0CCCCBDB8B8443B3A443D3D6565658F8C8CBBB5B5DEDCDCF0E1E1AEC9C9
              008080008080008080008080008080008080008080E6D3D3FAF3F3FFFFFFCECE
              CEBABABA453A3A443D3D6565658D8D8DB8B8B8DEDCDCF0E1E1AEC9C900808000
              8080008080008080008080BEBEBEF7EBEBFEF8F8FFFFFFFFFEFECAC8C8BCB9B9
              766F763E3F3E2A2A2A262626393A3A656565969696C8BEBE0080800080800080
              80008080008080BEBEBEF6ECECFBFBFBFFFFFFFFFFFFC9C9C9BBBBBB7272723E
              3E3E2A2A2A2626263A3A3A656565969696C8BDBD008080008080008080ABACAC
              DBDEDEFCF9F9FEFFFFFFFEFEE3E4E4BCBCBC9D9D9DA3A3A3B0B0B1B4B1B1A29E
              9E7778784D4D4D212121202020807070008080008080008080ABACACDBDEDEFC
              F9F9FFFFFFFEFEFEE4E4E4BCBCBC9D9D9DA3A3A3B0B0B0B3B3B3A0A0A0777777
              4D4D4D2121212020207D7272008080008080008080D1C1C1FFFEFEF1F0F0D8D9
              D9AEADADAAA3A3CBCCCCC7C7C7A9A9A9A29A9A9C9A9AA9A0A0B7B5B5BFC0C0AD
              ADAD848484998A8A008080008080008080D1C1C1FFFEFEF0F0F0D8D8D8AEAEAE
              A6A6A6CCCCCCC7C7C7A9A9A99E9E9E9A9A9AA5A5A5B6B6B6C0C0C0ADADAD8484
              84978C8C008080008080008080C5B7B7D5D0D0BBB4B4ABABABCBC5C5DCDDDDE5
              E3E3F2F2F2EAEBEBD4D5D5C6C1C1B2B4B4AAA5A59E9D9DA6A3A3B1B0B0BAAEAE
              008080008080008080C5B7B7D2D2D2B7B7B7AAAAAAC8C8C8DDDDDDE4E4E4F2F2
              F2EBEBEBD5D5D5C4C4C4B3B3B3A7A7A79E9E9EA4A4A4B0B0B0B9AEAE00808000
              8080008080B9AAAAB6B4B4C3C4C4D8D8D8DCDDDDD5D6D6EDEAEAE9E8E8E4DAE5
              E5E5E5E9EAEAE4E4E4D7D7D7C8C9C9BBB6B6B1B0B0B3A5A50080800080800080
              80B8ABABB5B5B5C3C3C3D8D8D8DCDCDCD5D5D5EBEBEBE8E8E8DFDFDFE5E5E5E9
              E9E9E4E4E4D7D7D7C8C8C8B8B8B8B0B0B0B1A7A7008080008080008080CDB7B7
              D8D8D8D8D8D8D6D6D6D3D3D3E6E6E6E8E8E8B5B7B589B989B8B4B8B4B6B7C1C4
              C4CECFCFDADBDBE1E1E1DBDBDBCCC2C2008080008080008080CEB7B7D8D8D8D8
              D8D8D6D6D6D3D3D3E6E6E6E8E8E8B6B6B6A1A1A1B6B6B6B5B5B5C2C2C2CFCFCF
              DADADAE1E1E1DBDBDBCCC2C2008080008080008080008080EDDBDBE1DEDED5D5
              D5EDEDEDDCDCDCB0B0B0DBD8DBC9F6C9EBE9EDE6D1CED4B3AEAFACACAAA1A1B0
              B1B1C6C5C5D7C3C3008080008080008080008080EDDBDBE1DEDED5D5D5EDEDED
              DCDCDCB0B0B0D9D9D9DEDEDEEAEAEADADADAC2C2C2AEAEAEA5A5A5B1B1B1C6C5
              C5D7C3C3008080008080008080008080008080B2B9B9D1D0D0BBBBBBB1ADADE5
              E0E0F7F8F8FBF7FBEFEFEFE7E9E9EAEDEEE8E8E8DEDFDFCDCBCBAFA1A1008080
              008080008080008080008080008080B2B8B8D0D0D0BBBBBBB0B0B0E3E3E3F7F7
              F7FAFAFAEFEFEFE8E8E8ECECECE8E8E8DFDFDFCECCCCACA5A500808000808000
              8080008080008080008080008080959393DAD9D9E6E8E9B8BABBB0B2B2CED0D0
              E1E2E3E0E1E1E1E2E2E0E0E0DCD5D5CBC0C00080800080800080800080800080
              80008080008080008080909797DBD8D8E8E8E8BABABAB1B1B1CFCFCFE2E2E2E1
              E1E1E1E1E1E0E0E0DCD5D5CAC2C2008080008080008080008080008080008080
              008080008080008080FFFCFCFBEFE8E7D9D2D9CBC8DAD0CDE5DBD8DDDADCDADB
              DCDFD2D25E969600808000808000808000808000808000808000808000808000
              8080008080008080F1F1F1DCDCDCD0D0D0D3D3D3DEDEDEDDDDDDDBDBDBDFD2D2
              5E96960080800080800080800080800080800080800080800080800080800080
              80FFD8D0FFE7DCFFE0D3FFDCCEFFD4C5FECCBEFBCDBFFFDAD07CACAD00808000
              8080008080008080008080008080008080008080008080008080008080F4E4E4
              EFEFEFEAEAEAE6E6E6E3E3E3DEDEDEDEDEDEF4E7E779AFAF0080800080800080
              80008080008080008080008080008080008080008080008080FFD0C6FFE7DFFF
              DDD1FFD9C8FFCEBCFFC4B2FFC2A9899E95008080008080008080008080008080
              008080008080008080008080008080008080008080EEDEDEF0F0F0E7E7E7E2E2
              E2DEDEDED8D8D8DDD6D672ABAB00808000808000808000808000808000808000
              8080008080008080008080008080008080FFDAD2FFE7DEFFDDD2FFD9C9FFCEBD
              FFC5B3FFC4AB7899920080800080800080800080800080800080800080800080
              80008080008080008080008080F3E3E3F0F0F0E8E8E8E2E2E2DFDFDFD8D8D8DF
              D7D764A5A5008080008080008080008080008080008080008080008080008080
              008080008080008080FFD8CFFFE7DEFFDDD2FFD9C9FFCEBDFFC4B3FFC6B583A0
              9E00808000808000808000808000808000808000808000808000808000808000
              8080008080F1E1E1F0F0F0E8E8E8E2E2E2DFDFDFD8D8D8E7DDDD72AEAE008080
              0080800080800080800080800080800080800080800080800080800080800080
              80FFE7DDFFE5DCFFDDD2FFD9C9FFCEBDFFCAB5D9B4AF00808000808000808000
              8080008080008080008080008080008080008080008080008080008080F6EDED
              EEEEEEE8E8E8E2E2E2DFDFDFDDDADABDCBCB0080800080800080800080800080
              80008080008080008080008080008080008080008080D9C3BDFFFFF7FFE8DDFF
              E5D4FFDBCAFFD8C7FFC3B6BBAEA7008080008080008080008080008080008080
              008080008080008080008080008080008080C7CECEFFFFFFEFEFEFEAEAEAE4E4
              E4E4E4E4E2DADAA2C0C000808000808000808000808000808000808000808000
              8080008080008080008080008080EECEC5E9CAC9E8C8BEE8C4BAE8BEB6EBB8AF
              E4B7B00080800080800080800080800080800080800080800080800080800080
              80008080008080008080DCD7D7D9D8D8D3D3D3D1D1D1CFCFCFCFCCCCC5D0D000
              8080008080008080008080008080008080008080008080008080}
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
          Width = 947
          Height = 159
          ActivePage = TbsDadBas
          Align = alTop
          TabOrder = 1
          object TbsDadBas: TTabSheet
            Caption = 'Dados b'#225'sicos'
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
            object Label28: TLabel
              Left = 8
              Top = 36
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
              Top = 56
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
              Top = 56
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
              Top = 80
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
            object EdtKmPreCar: TBrvEditNum
              Left = 108
              Top = 53
              Width = 66
              Height = 21
              TabOrder = 3
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
              Top = 53
              Width = 121
              Height = 21
              EditMask = '99/99/9999;1; '
              MaxLength = 10
              TabOrder = 4
              Text = '  /  /    '
              BrDataValida = False
              BrDataVazia = True
              BrFunctionButton = TVdData
              BrAlignment = taLeftJustify
              BrDicionario = DmSrvApl.BrvDicionario
              BrRecordar = False
            end
            object EdtCdEmpDes: TBrvEditNum
              Left = 108
              Top = 78
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
              Top = 78
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
              TabOrder = 6
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
          Width = 947
          Height = 200
          BrShowMemo = True
          BrReadOnlyStyle = []
          BrReadOnlyColor = clBlack
          Align = alClient
          DataSource = DtsCarga
          TabOrder = 2
          TitleFont.Charset = DEFAULT_CHARSET
          TitleFont.Color = clWindowText
          TitleFont.Height = -11
          TitleFont.Name = 'Tahoma'
          TitleFont.Style = []
          BrDrawColumn.Strings = (
            'N'#227'o remova essas duas linhas de formata'#231#227'o:'
            'CampoTabela;Operador;ValorComparativo;Cor;'
            'SNNotLib;=;N;clGray;')
          BrGradeZebrada = True
          Columns = <
            item
              Alignment = taRightJustify
              Expanded = False
              FieldName = 'CdEmpAtu'
              ReadOnly = True
              Title.Caption = 'Emp'
              Title.Font.Charset = DEFAULT_CHARSET
              Title.Font.Color = clWindowText
              Title.Font.Height = -11
              Title.Font.Name = 'Tahoma'
              Title.Font.Style = [fsBold]
              Width = 29
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
              Width = 48
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
              Width = 39
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
              Width = 47
              Visible = True
              BrConsulta = 0
              BrPermissao = []
              BrValueHalfChecked = False
              BrSaveOnClick = False
            end
            item
              Expanded = False
              FieldName = 'NrNotas'
              Title.Caption = 'Nota(s)'
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
              Expanded = False
              FieldName = 'RsDestin'
              Title.Caption = 'Destinat'#225'rio'
              Title.Font.Charset = DEFAULT_CHARSET
              Title.Font.Color = clWindowText
              Title.Font.Height = -11
              Title.Font.Name = 'Tahoma'
              Title.Font.Style = [fsBold]
              Width = 184
              Visible = True
              BrConsulta = 0
              BrPermissao = []
              BrValueHalfChecked = False
              BrSaveOnClick = False
            end
            item
              Expanded = False
              FieldName = 'EdDestin'
              Title.Caption = 'Endere'#231'o do destinat'#225'rio'
              Title.Font.Charset = DEFAULT_CHARSET
              Title.Font.Color = clWindowText
              Title.Font.Height = -11
              Title.Font.Name = 'Tahoma'
              Title.Font.Style = [fsBold]
              Width = 203
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
              Width = 248
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
            end
            item
              Expanded = False
              FieldName = 'RsTomado'
              Title.Caption = 'Tomador'
              Title.Font.Charset = DEFAULT_CHARSET
              Title.Font.Color = clWindowText
              Title.Font.Height = -11
              Title.Font.Name = 'Tahoma'
              Title.Font.Style = [fsBold]
              Width = 232
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
  inherited PopCfgFrm: TPopupMenu
    Left = 16
    Top = 312
  end
  inherited ImlPopFrm: TImageList
    Left = 48
    Top = 280
    Bitmap = {
      494C010103000500700014001400FFFFFFFFFF10FFFFFFFFFFFFFFFF424D3600
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
    Left = 16
    Top = 280
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
    Left = 286
    Top = 341
  end
  object DtsRotas: TDataSource
    DataSet = CdsRotas
    Left = 314
    Top = 341
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
    Left = 286
    Top = 369
  end
  object DtsCidade: TDataSource
    DataSet = CdsCidade
    Left = 314
    Top = 369
  end
  object CdsTotal: TClientDataSet
    Aggregates = <>
    Params = <>
    Left = 864
    Top = 328
  end
  object PopRotas: TPopupMenu
    Left = 97
    Top = 327
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
    Left = 125
    Top = 327
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
    Left = 314
    Top = 313
  end
  object PopTomado: TPopupMenu
    Left = 153
    Top = 327
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
    Left = 286
    Top = 313
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
    Left = 286
    Top = 401
  end
  object DtsCTRC: TDataSource
    DataSet = CdsCTRC
    Left = 314
    Top = 400
  end
  object PopCtrc: TPopupMenu
    Left = 181
    Top = 327
    object MenuItem5: TMenuItem
      Caption = 'Marcar Todas'
      OnClick = MenuItem5Click
    end
    object MenuItem6: TMenuItem
      Caption = 'Desmarcar Todas'
      OnClick = MenuItem6Click
    end
    object N1: TMenuItem
      Caption = '-'
    end
    object Detalhar1: TMenuItem
      Caption = 'Detalhar Conhecimento'
      OnClick = Detalhar1Click
    end
    object ReprogramarDatadeEntrega1: TMenuItem
      Caption = 'Reprogramar Data de Entrega'
      OnClick = ReprogramarDatadeEntrega1Click
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
  object BrvAlertProgress: TBrvAlertProgress
    Left = 432
  end
  object BrvServerProgress: TBrvServerProgress
    Left = 368
  end
  object CdsInfVFut: TClientDataSet
    Aggregates = <>
    Params = <>
    Left = 8
  end
  object DtsInfVFut: TDataSource
    DataSet = CdsInfVFut
    Left = 40
    Top = 1
  end
  object CdsInfVOnt: TClientDataSet
    Aggregates = <>
    Params = <>
    Left = 69
  end
  object DtsInfVOnt: TDataSource
    DataSet = CdsInfVOnt
    Left = 98
    Top = 1
  end
  object CdsInfVHj: TClientDataSet
    Aggregates = <>
    Params = <>
    Left = 133
  end
  object DtsInfVHj: TDataSource
    DataSet = CdsInfVHj
    Left = 162
    Top = 1
  end
end
