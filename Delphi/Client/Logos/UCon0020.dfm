inherited Con0020: TCon0020
  Caption = 'Con0020 - Consulta de Atendimento ao Cliente'
  ClientHeight = 373
  ClientWidth = 792
  Position = poDesktopCenter
  ExplicitWidth = 800
  ExplicitHeight = 400
  PixelsPerInch = 96
  TextHeight = 13
  inherited NavBarra: TBrvDbNavCop
    Width = 792
    ExplicitWidth = 792
    inherited BrvSpeedButton1: TBrvSpeedButton
      Left = 765
      ExplicitLeft = 765
    end
    inherited SbtAjuda: TBrvSpeedButton
      Left = 740
      ExplicitLeft = 740
    end
  end
  inherited PnlFundo: TPanel
    Width = 792
    Height = 343
    ExplicitWidth = 792
    ExplicitHeight = 343
    object PgcFundo: TPageControl
      Left = 1
      Top = 1
      Width = 786
      Height = 337
      ActivePage = TbsConsulta
      Align = alClient
      TabOrder = 0
      TabStop = False
      object TbsFiltro: TTabSheet
        Caption = 'TbsFiltro'
        object Panel1: TPanel
          Left = 0
          Top = 0
          Width = 778
          Height = 309
          Align = alClient
          BorderStyle = bsSingle
          Font.Charset = DEFAULT_CHARSET
          Font.Color = clWindowText
          Font.Height = -11
          Font.Name = 'MS Sans Serif'
          Font.Style = [fsBold]
          ParentFont = False
          TabOrder = 0
          object Panel4: TPanel
            Left = 1
            Top = 270
            Width = 772
            Height = 34
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
              768
              30)
            object BtnPesquisar: TBrvBitBtn
              Left = 645
              Top = 3
              Width = 100
              Height = 25
              Hint = 'Pesquisar'
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
              OnClick = BtnPesquisarClick
              BrTipoBotao = BrBtnLocalizar
            end
          end
          object PnlFiltros: TPanel
            Left = 1
            Top = 1
            Width = 772
            Height = 269
            Align = alClient
            BorderStyle = bsSingle
            Font.Charset = DEFAULT_CHARSET
            Font.Color = clWindowText
            Font.Height = -11
            Font.Name = 'Tahoma'
            Font.Style = [fsBold]
            ParentFont = False
            TabOrder = 0
            DesignSize = (
              768
              265)
            object LblEmpres: TLabel
              Left = 6
              Top = 14
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
            object Label5: TLabel
              Left = 6
              Top = 110
              Width = 66
              Height = 13
              Caption = 'Data Inicial'
              Font.Charset = DEFAULT_CHARSET
              Font.Color = clBlue
              Font.Height = -11
              Font.Name = 'MS Sans Serif'
              Font.Style = [fsBold]
              ParentFont = False
            end
            object Label6: TLabel
              Left = 6
              Top = 142
              Width = 59
              Height = 13
              Caption = 'Data Final'
              Font.Charset = DEFAULT_CHARSET
              Font.Color = clBlue
              Font.Height = -11
              Font.Name = 'MS Sans Serif'
              Font.Style = [fsBold]
              ParentFont = False
            end
            object Label4: TLabel
              Left = 6
              Top = 46
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
            object Label1: TLabel
              Left = 6
              Top = 78
              Width = 59
              Height = 13
              Caption = 'Atendente'
              Font.Charset = DEFAULT_CHARSET
              Font.Color = clBlack
              Font.Height = -11
              Font.Name = 'MS Sans Serif'
              Font.Style = [fsBold]
              ParentFont = False
            end
            object EdtCdUsuari: TBrvEditNum
              Left = 83
              Top = 75
              Width = 100
              Height = 21
              TabOrder = 4
              Text = '0'
              BrvReadOnlyColor = 14541539
              BrAlignment = taRightJustify
              BrCasasDecimais = 0
              BrSepararMilhar = False
              BrAsInteger = 0
              BrAsFloatSQL = '0'
              BrVisibleButton = True
              BrFunctionButton = TpConsulta
              BrConfigurar.Strings = (
                'EdtCdUsuari;CdUsuari;S;S;'
                'LblNmComUsu;NmComUsu;S;N;')
              BrDicionario = DmSrvApl.BrvDicionario
              BrQueryCode = 61
              BrRecordar = False
            end
            object EdtCdTomado: TBrvEditNum
              Left = 83
              Top = 43
              Width = 100
              Height = 21
              TabOrder = 2
              Text = '0'
              BrvReadOnlyColor = 14541539
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
            object EdtDtFinal: TBrvEditDate
              Left = 83
              Top = 139
              Width = 100
              Height = 21
              EditMask = '99/99/9999;1; '
              MaxLength = 10
              TabOrder = 7
              Text = '  /  /    '
              BrvReadOnlyColor = 14541539
              BrDataValida = False
              BrDataVazia = True
              BrFunctionButton = TVdData
              BrAlignment = taLeftJustify
              BrDicionario = DmSrvApl.BrvDicionario
              BrRecordar = False
            end
            object EdtDtInicia: TBrvEditDate
              Left = 83
              Top = 107
              Width = 100
              Height = 21
              EditMask = '99/99/9999;1; '
              MaxLength = 10
              TabOrder = 6
              Text = '  /  /    '
              BrvReadOnlyColor = 14541539
              BrDataValida = False
              BrDataVazia = True
              BrFunctionButton = TVdData
              BrAlignment = taLeftJustify
              BrDicionario = DmSrvApl.BrvDicionario
              BrRecordar = False
            end
            object EdtCdEmpres: TBrvEditNum
              Left = 83
              Top = 11
              Width = 100
              Height = 21
              TabOrder = 0
              Text = '0'
              BrvReadOnlyColor = 14541539
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
              Left = 189
              Top = 11
              Width = 556
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
              Left = 189
              Top = 43
              Width = 556
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
            object LblNmComUsu: TBrvRetCon
              Left = 189
              Top = 75
              Width = 556
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
          end
        end
      end
      object TbsConsulta: TTabSheet
        Caption = 'TbsConsulta'
        ImageIndex = 1
        object Panel5: TPanel
          Left = 0
          Top = 0
          Width = 778
          Height = 309
          Align = alClient
          BorderStyle = bsSingle
          Font.Charset = DEFAULT_CHARSET
          Font.Color = clWindowText
          Font.Height = -11
          Font.Name = 'MS Sans Serif'
          Font.Style = [fsBold]
          ParentFont = False
          TabOrder = 0
          object Splitter1: TSplitter
            Left = 1
            Top = 91
            Width = 772
            Height = 5
            Cursor = crVSplit
            Align = alTop
            ExplicitTop = 68
            ExplicitWidth = 822
          end
          object Panel6: TPanel
            Left = 1
            Top = 270
            Width = 772
            Height = 34
            Align = alBottom
            BorderStyle = bsSingle
            Font.Charset = DEFAULT_CHARSET
            Font.Color = clWindowText
            Font.Height = -11
            Font.Name = 'MS Sans Serif'
            Font.Style = [fsBold]
            ParentFont = False
            TabOrder = 2
            DesignSize = (
              768
              30)
            object LblQtReg: TLabel
              Left = 8
              Top = 8
              Width = 54
              Height = 13
              Caption = 'LblQtReg'
            end
            object BtnVoltar: TBrvBitBtn
              Left = 645
              Top = 3
              Width = 100
              Height = 25
              Hint = 'Voltar'
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
              OnClick = BtnVoltarClick
              BrTipoBotao = BrBtnSetaEsquerda
            end
            object BtnExcel: TBrvBitBtn
              Left = 541
              Top = 3
              Width = 100
              Height = 25
              Hint = 'Excel'
              Anchors = [akTop, akRight]
              Caption = '&Excel'
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
                008080008080008080008080008080008080008080007D80007A7C007A7C007A
                7C007A7C007A7C007A7C007A7C007A7C007A7C007A7C007A7C007A7C007A7C00
                7A7C007A7C007E80008080008080008080007E7E007A7A007A7A007A7A007A7A
                007A7A007A7A007A7A007A7A007A7A007A7A007A7A007A7A007A7A007A7A007B
                7B007F7F008080008080007D8027937A65B2A65FB0A35FB0A35FB0A35FB0A35F
                B0A35FB0A35FB0A35FB0A35FB0A35FB0A35FB0A35FB0A361B0A45DAE9E0F8876
                007E80008080007E7E418D8D70AEAE6CACAC6BABAB6BABAB6BABAB6BABAB6BAB
                AB6BABAB6BABAB6BABAB6BABAB6BABAB6BABAB6CACAC6EAAAA238383007F7F00
                8080007A7E69B5899CCF9F81C18B84C28E83C28E83C28E83C28E83C28E83C28E
                83C28E83C28E83C28E83C28E84C38E79BD85B0D8AD319978007C80008080007B
                7B97A9A9CEC2C2BAB2B2BCB4B4BCB4B4BCB4B4BCB4B4BCB4B4BCB4B4BCB4B4BC
                B4B4BCB4B4BCB4B4BDB5B5B6AEAEDCCCCC549090007D7D008080007A7E60B185
                AFD9B6FFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFF
                FFFFFFFFFFFFFFF0F7F2A7D4AC2E9779007C80008080007B7B8CA4A4D0CCCCFF
                FFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFF
                FFFFFFF6F6F6CFC7C74F8F8F007D7D008080007A7E60B185ACD7B3FEFFFEFFFF
                FFF4FAF5A7D4B2ECF6EFFFFFFFFFFFFFFFFFFFD0E7D5B4DABEFFFFFFFFFFFFE5
                F2E8A6D3AB2E9779007C80008080007B7B8CA4A4CFCBCBFFFFFFFFFFFFF8F8F8
                CACACAF4F4F4FFFFFFFFFFFFFFFFFFE2E2E2D2D2D2FFFFFFFFFFFFEFEFEFCFC7
                C74F8F8F007D7D008080007A7E60B185ACD7B3FEFFFEFFFFFFFFFFFF75BC8770
                B982FFFFFFFFFFFFF9FCFA47A45DABD5B5FFFFFFFFFFFFE5F2E8A6D3AB2E9779
                007C80008080007B7B8CA4A4D0CCCCFFFFFFFFFFFFFFFFFFADADADABABABFFFF
                FFFFFFFFFAFAFA929292CDCDCDFFFFFFFFFFFFEFEFEFCEC6C64F8F8F007D7D00
                8080007A7E60B185ACD7B3FEFFFEFFFFFFFFFFFFFFFFFF389E51CAE5D0FFFFFF
                88C49761B175FFFFFFFFFFFFFFFFFFE5F2E8A6D3AB2E9779007C80008080007B
                7B8CA4A4CFCBCBFFFFFFFFFFFFFFFFFFFFFFFF8A8A8AE0E0E0FFFFFFB8B8B8A1
                A1A1FFFFFFFFFFFFFFFFFFEFEFEFCEC6C64F8F8F007D7D008080007A7E60B185
                ACD7B3FEFFFEFFFFFFFFFFFFFFFFFFCBE5D14EA965E4F2E7329A4DEAF4EDFFFF
                FFFFFFFFFFFFFFE5F2E8A6D3AB2E9779007C80008080007B7B8CA4A4CFCBCBFF
                FFFFFFFFFFFFFFFFFFFFFFDFDFDF979797EFEFEF868686F2F2F2FFFFFFFFFFFF
                FFFFFFEFEFEFCEC6C64F8F8F007D7D008080007A7E60B185ACD7B3FEFFFEFFFF
                FFFFFFFFFFFFFFFFFFFF89C5982D9747AFD7B9FFFFFFFFFFFFFFFFFFFFFFFFE5
                F2E8A6D3AB2E9779007C80008080007B7B8CA4A4CFCBCBFFFFFFFFFFFFFFFFFF
                FFFFFFFFFFFFB9B9B9848484CFCFCFFFFFFFFFFFFFFFFFFFFFFFFFEFEFEFCEC6
                C6509090007D7D008080007A7E60B185ACD7B3FEFFFEFFFFFFFFFFFFFFFFFFFF
                FFFF92CAA0289543B8DCC1FFFFFFFFFFFFFFFFFFFFFFFFE5F2E8A6D3AB2E9779
                007C80008080007B7B8CA4A4CFCBCBFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFBEBE
                BE808080D5D5D5FFFFFFFFFFFFFFFFFFFFFFFFEFEFEFCFC7C74E8E8E007D7D00
                8080007A7E60B185ACD7B3FEFFFEFFFFFFFFFFFFFFFFFFDDEEE146A45DDAEDDE
                389D52EDF6EFFFFFFFFFFFFFFFFFFFE5F2E8A6D3AB2E9779007C80008080007B
                7B8CA4A4D0CCCCFFFFFFFFFFFFFFFFFFFFFFFFEAEAEA929292E7E7E78A8A8AF4
                F4F4FFFFFFFFFFFFFFFFFFEFEFEFCEC6C64F8F8F007D7D008080007A7E60B185
                ACD7B3FEFFFEFFFFFFFFFFFFFFFFFF4EA864A6D4B2FFFFFF8DC79C67B57AFFFF
                FFFFFFFFFFFFFFE5F2E8A6D3AB2E9779007C80008080007B7B8CA4A4D0CCCCFF
                FFFFFFFFFFFFFFFFFFFFFF979797C9C9C9FFFFFFBBBBBBA5A5A5FFFFFFFFFFFF
                FFFFFFEFEFEFCEC6C64F8F8F007D7D008080007A7E60B186AAD6B1FDFEFDFFFF
                FFFFFFFF9ED0AB48A55FFFFFFFFFFFFFFDFEFD4CA763AFD8BAFFFFFFFFFFFFE5
                F2E8A6D3AB2E9779007C80008080007B7B93A5A5CECACAFEFEFEFFFFFFFFFFFF
                C6C6C6939393FFFFFFFFFFFFFEFEFE959595CFCFCFFFFFFFFFFFFFEFEFEFCFC7
                C74F8F8F007D7D008080007B804EA870A9D5B2EAF5EDFFFFFFFFFFFFAFD8BAD9
                ECDEFFFFFFFFFFFFFFFFFFD5EADBB8DCC1FFFFFFFFFFFFE5F2E8A6D3AB2E9779
                007C80008080007D7D389191D5CBCBF2F2F2FFFFFFFFFFFFD1D1D1E8E8E8FFFF
                FFFFFFFFFFFFFFE5E5E5D5D5D5FFFFFFFFFFFFEFEFEFCEC6C64F8F8F007D7D00
                8080007F7F007B7CC0E1B88AC598D2E9D8FFFFFFFFFFFFFFFFFFFFFFFFFFFFFF
                FFFFFFFFFFFFFFFFFFFFFFFFFFFFFFF0F7F2A7D4AC2E9779007C80008080007F
                7F007D7DE9D4D4B9B7B7E4E4E4FFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFF
                FFFFFFFFFFFFFFFFFFFFFFF6F6F6CFC7C74F8F8F007D7D008080007F80007D7E
                289469B9DDC0B1D9B485C38E85C38F85C48F85C48F85C48F85C48F85C48F85C4
                8F85C48F87C4907ABE85B0D8AD319979007C80008080007F7F007D7D578A8AD6
                D3D3D8CECEBDB5B5BEB6B6BEB6B6BEB6B6BEB6B6BEB6B6BEB6B6BEB6B6BEB6B6
                BEB6B6B6AEAEDBCBCB549090007D7D008080008080007F80007B81007B7540A1
                8368B3AB67B3AA67B3AA67B3AA67B3AA67B3AA67B3AA67B3AA67B3AA67B3AA69
                B4AC65B3A6108876007E80008080008080007F7F007D7D0079795E989870B0B0
                70B0B070B0B070B0B070B0B070B0B06FAFAF70B0B06FAFAF70B0B071B1B172AE
                AE238383007F7F008080008080008080008080007F80007B7E007A7B00797B00
                797B00797B00797B00797B00797B00797B00797B00797B00797B007A7C007E80
                008080008080008080008080008080007F7F007C7C007A7A007A7A007A7A007A
                7A007A7A007A7A007A7A007A7A007A7A007A7A007A7A007A7A007F7F00808000
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
              OnClick = BtnExcelClick
              BrTipoBotao = BrBtnExcel
            end
          end
          object Panel8: TPanel
            Left = 1
            Top = 1
            Width = 772
            Height = 90
            Align = alTop
            BorderStyle = bsSingle
            TabOrder = 0
            object StgFiltros: TStringGrid
              Left = 1
              Top = 1
              Width = 766
              Height = 84
              Align = alClient
              ColCount = 2
              DefaultColWidth = 170
              DefaultRowHeight = 15
              FixedCols = 0
              RowCount = 1
              FixedRows = 0
              Font.Charset = DEFAULT_CHARSET
              Font.Color = clWindowText
              Font.Height = -9
              Font.Name = 'MS Sans Serif'
              Font.Style = [fsBold]
              ParentFont = False
              TabOrder = 0
              ExplicitTop = 3
            end
          end
          object Panel3: TPanel
            Left = 1
            Top = 96
            Width = 772
            Height = 174
            Align = alClient
            BorderStyle = bsSingle
            Font.Charset = DEFAULT_CHARSET
            Font.Color = clWindowText
            Font.Height = -11
            Font.Name = 'MS Sans Serif'
            Font.Style = [fsBold]
            ParentFont = False
            TabOrder = 1
            object DbgLsAteCli: TBrvDbGrid
              Left = 1
              Top = 1
              Width = 766
              Height = 168
              BrShowMemo = True
              BrReadOnlyStyle = [fsBold]
              BrReadOnlyColor = clBlack
              Align = alClient
              DataSource = DsT010
              TabOrder = 0
              TitleFont.Charset = DEFAULT_CHARSET
              TitleFont.Color = clWindowText
              TitleFont.Height = -11
              TitleFont.Name = 'MS Sans Serif'
              TitleFont.Style = [fsBold]
              BrDicionario = DmSrvApl.BrvDicionario
              BrDrawColumn.Strings = (
                'N'#227'o remova essas duas linhas de formata'#231#227'o:'
                'CampoTabela;Operador;ValorComparativo;Cor;')
              BrGradeZebrada = True
              Columns = <
                item
                  Expanded = False
                  FieldName = 'NrAtendi'
                  Title.Caption = 'Atendimento'
                  Width = 80
                  Visible = True
                  BrConsulta = 0
                  BrPermissao = []
                  BrValueHalfChecked = False
                  BrSaveOnClick = False
                end
                item
                  Expanded = False
                  FieldName = 'DsAtendi'
                  Title.Caption = 'Descri'#231#227'o do Atendimento'
                  Width = 325
                  Visible = True
                  BrConsulta = 0
                  BrPermissao = []
                  BrValueHalfChecked = False
                  BrSaveOnClick = False
                end
                item
                  Expanded = False
                  FieldName = 'CdTipAte'
                  Title.Caption = 'Tipo'
                  Width = 50
                  Visible = True
                  BrConsulta = 0
                  BrPermissao = []
                  BrValueHalfChecked = False
                  BrSaveOnClick = False
                end
                item
                  Expanded = False
                  FieldName = 'DsTipAte'
                  Title.Caption = 'Descri'#231#227'o do Tipo'
                  Width = 200
                  Visible = True
                  BrConsulta = 0
                  BrPermissao = []
                  BrValueHalfChecked = False
                  BrSaveOnClick = False
                end
                item
                  Expanded = False
                  FieldName = 'CdUsuari'
                  Title.Caption = 'Atendente'
                  Width = 70
                  Visible = True
                  BrConsulta = 0
                  BrPermissao = []
                  BrValueHalfChecked = False
                  BrSaveOnClick = False
                end
                item
                  Expanded = False
                  FieldName = 'NmComUsu'
                  Title.Caption = 'Nome da Atendente'
                  Width = 200
                  Visible = True
                  BrConsulta = 0
                  BrPermissao = []
                  BrValueHalfChecked = False
                  BrSaveOnClick = False
                end
                item
                  Expanded = False
                  FieldName = 'CdCarga'
                  Title.Caption = 'Carga'
                  Width = 80
                  Visible = True
                  BrConsulta = 0
                  BrPermissao = []
                  BrValueHalfChecked = False
                  BrSaveOnClick = False
                end
                item
                  Expanded = False
                  FieldName = 'DtAbertu'
                  Title.Caption = 'Data Abertura'
                  Width = 135
                  Visible = True
                  BrConsulta = 0
                  BrPermissao = []
                  BrValueHalfChecked = False
                  BrSaveOnClick = False
                end
                item
                  Expanded = False
                  FieldName = 'DtFecham'
                  Title.Caption = 'Data Fechamento'
                  Width = 135
                  Visible = True
                  BrConsulta = 0
                  BrPermissao = []
                  BrValueHalfChecked = False
                  BrSaveOnClick = False
                end
                item
                  Expanded = False
                  FieldName = 'TxAtendi'
                  Title.Caption = 'Texto do Atendimento'
                  Width = 500
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
                  Width = 60
                  Visible = True
                  BrConsulta = 0
                  BrPermissao = []
                  BrValueHalfChecked = False
                  BrSaveOnClick = False
                end
                item
                  Expanded = False
                  FieldName = 'CdCtrc'
                  Title.Caption = 'CTRC'
                  Width = 80
                  Visible = True
                  BrConsulta = 0
                  BrPermissao = []
                  BrValueHalfChecked = False
                  BrSaveOnClick = False
                end
                item
                  Expanded = False
                  FieldName = 'CdRemete'
                  Title.Caption = 'Remetente'
                  Width = 80
                  Visible = True
                  BrConsulta = 0
                  BrPermissao = []
                  BrValueHalfChecked = False
                  BrSaveOnClick = False
                end
                item
                  Expanded = False
                  FieldName = 'RsRemete'
                  Title.Caption = 'Nome do Remetente'
                  Width = 275
                  Visible = True
                  BrConsulta = 0
                  BrPermissao = []
                  BrValueHalfChecked = False
                  BrSaveOnClick = False
                end
                item
                  Expanded = False
                  FieldName = 'CdDestin'
                  Title.Caption = 'Destinat'#225'rio'
                  Width = 80
                  Visible = True
                  BrConsulta = 0
                  BrPermissao = []
                  BrValueHalfChecked = False
                  BrSaveOnClick = False
                end
                item
                  Expanded = False
                  FieldName = 'RsDestin'
                  Title.Caption = 'Nome do Destinat'#225'rio'
                  Width = 275
                  Visible = True
                  BrConsulta = 0
                  BrPermissao = []
                  BrValueHalfChecked = False
                  BrSaveOnClick = False
                end
                item
                  Expanded = False
                  FieldName = 'DtEmissa'
                  Title.Caption = 'Data Emiss'#227'o'
                  Width = 90
                  Visible = True
                  BrConsulta = 0
                  BrPermissao = []
                  BrValueHalfChecked = False
                  BrSaveOnClick = False
                end
                item
                  Expanded = False
                  FieldName = 'TxOcorre'
                  Title.Caption = 'Ocorr'#234'ncia'
                  Width = 325
                  Visible = True
                  BrConsulta = 0
                  BrPermissao = []
                  BrValueHalfChecked = False
                  BrSaveOnClick = False
                end
                item
                  Expanded = False
                  FieldName = 'DtOcorre'
                  Title.Caption = 'Data Ocorr'#234'ncia'
                  Width = 135
                  Visible = True
                  BrConsulta = 0
                  BrPermissao = []
                  BrValueHalfChecked = False
                  BrSaveOnClick = False
                end
                item
                  Expanded = False
                  FieldName = 'NrNota'
                  Title.Caption = 'N'#186' Nota Fiscal'
                  Width = 90
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
  end
  inherited PopCfgFrm: TPopupMenu
    Left = 472
    Top = 8
  end
  inherited ImlPopFrm: TImageList
    Left = 536
    Top = 0
  end
  inherited LspS049: TBrvListParam
    Left = 592
    Top = 0
  end
  object BrvExcel: TBrvExcel
    BrSalvarComo = True
    BrNomePadrao = 'AtendimentoCliente'
    BrAlertProgress = True
    Left = 305
    Top = 246
  end
  object BrvListParam: TBrvListParam
    Left = 243
    Top = 246
  end
  object BrvServerProgress: TBrvServerProgress
    Left = 161
    Top = 246
  end
  object CpT010: TBrvClientDataSet
    Aggregates = <>
    Params = <>
    ReadOnly = True
    BrShowFieldName = False
    BrQueryCode = 259
    BrDicionario = DmSrvApl.BrvDicionario
    BrType = VqNormal
    BrGravaLog = True
    BrUserCode = 0
    Left = 84
    Top = 246
  end
  object DsT010: TDataSource
    DataSet = CpT010
    Left = 38
    Top = 246
  end
  object mxExcel: TmxDataSetExport
    Captions.Strings = (
      'fsdfd'
      'dds'
      'sdfds'
      'sdf'
      'dsf'
      'dsf'
      'dsdsf'
      'dsf'
      'dfsd'
      'sdfds'
      'sdf'
      'sdf'
      'sdf'
      'sdf'
      'dsf'
      'dsf'
      'dsf'
      'sdf'
      'sdf'
      'dsf'
      'dsf'
      'df'
      'df'
      'df'
      'sdf'
      'dsfds'
      'fd'
      'dsf'
      'df'
      'df'
      'df'
      'ds'
      'fds'
      'fdsf'
      'dsf'
      'sd'
      'fd'
      'f'
      'dsf'
      'ss'
      'd'
      'd'
      'sd')
    DateFormat = 'dd/MM/yyyy'
    TimeFormat = 'hh:mm'
    DateTimeFormat = 'hh:mm dd/MM/yyyy'
    ExportType = xtExcel
    ExportTypes = [xtExcel]
    ExportStyle = xsFile
    HTML.CustomColors.Background = clWhite
    HTML.CustomColors.DefaultLink = clRed
    HTML.CustomColors.DefaultFontFace = 'Arial,Helvetica'
    HTML.CustomColors.VisitedLink = clAqua
    HTML.CustomColors.ActiveLink = clBlue
    HTML.CustomColors.DefaultText = clBlack
    HTML.CustomColors.TableFontColor = clBlack
    HTML.CustomColors.TableFontFace = 'Arial,Helvetica'
    HTML.CustomColors.TableBackground = 16777167
    HTML.CustomColors.TableOddBackground = clWhite
    HTML.CustomColors.HeaderBackground = 3368601
    HTML.CustomColors.HeadersFontColor = clWhite
    HTML.Options = [hoShowGridLines, hoBoldHeaders, hoAutoLink, hoOddRowColoring, hoDisplayTitle]
    HTML.Template = ctStandard
    Messages.Caption = 'Exporting DataSet'
    Messages.CopiedToClipboard = 'Data was copied to clipboard!'
    Messages.CancelCaption = '&Cancel'
    Messages.CreatedText = 'Created:'
    Messages.DocumentFilter.HTML = 'HTML Documents'
    Messages.DocumentFilter.Excel = 'Excel Files'
    Messages.DocumentFilter.Word = 'Word Documents'
    Messages.DocumentFilter.Text = 'Text Files'
    Messages.DocumentFilter.Comma = 'CSV (Comma delimited)'
    Messages.DocumentFilter.Tab = 'Text (Tab delimited)'
    Messages.DocumentFilter.RTF = 'Rich Text Format'
    Messages.DocumentFilter.DIF = 'Data Interchange Format'
    Messages.DocumentFilter.SYLK = 'SYLK Files'
    Messages.ExportCaption = '&Export'
    Messages.ExportToFile = 'Export &to file'
    Messages.FalseText = 'False'
    Messages.Height = 80
    Messages.SaveTitle = 'Save document'
    Messages.SelectFormat = 'E&xport formats:'
    Messages.Text = 'Processing...'
    Messages.TrueText = 'True'
    Messages.Width = 300
    Messages.ViewOnly = '&View only'
    TruncateSymbol = '...'
    RowNumberFormat = '%d'
    DOC_RTF.Template = rtStandard
    DOC_RTF.Options = [roShowGridLines, roOddRowColoring]
    DOC_RTF.CustomSettings.TableBackground = 16777167
    DOC_RTF.CustomSettings.TableOddBackground = clWhite
    DOC_RTF.CustomSettings.HeaderBackground = 3368601
    DOC_RTF.CustomSettings.DefaultFont.Charset = DEFAULT_CHARSET
    DOC_RTF.CustomSettings.DefaultFont.Color = clWindowText
    DOC_RTF.CustomSettings.DefaultFont.Height = -11
    DOC_RTF.CustomSettings.DefaultFont.Name = 'Tahoma'
    DOC_RTF.CustomSettings.DefaultFont.Style = []
    DOC_RTF.CustomSettings.HeaderFont.Charset = DEFAULT_CHARSET
    DOC_RTF.CustomSettings.HeaderFont.Color = clWindowText
    DOC_RTF.CustomSettings.HeaderFont.Height = -11
    DOC_RTF.CustomSettings.HeaderFont.Name = 'Tahoma'
    DOC_RTF.CustomSettings.HeaderFont.Style = [fsBold]
    DOC_RTF.CustomSettings.TableFont.Charset = DEFAULT_CHARSET
    DOC_RTF.CustomSettings.TableFont.Color = clWindowText
    DOC_RTF.CustomSettings.TableFont.Height = -11
    DOC_RTF.CustomSettings.TableFont.Name = 'Tahoma'
    DOC_RTF.CustomSettings.TableFont.Style = []
    DOC_RTF.CellWidth = 1400
    DOC_RTF.TopMargin = 101
    DOC_RTF.BottomMargin = 101
    DOC_RTF.LeftMargin = 461
    DOC_RTF.RightMargin = 562
    EXCEL.Options = [reSetMargins, reUseBorders]
    EXCEL.ColumnWidth = 20
    EXCEL.Protected = False
    EXCEL.Footer = '&P'
    EXCEL.DefaultFont.Charset = DEFAULT_CHARSET
    EXCEL.DefaultFont.Color = clWindowText
    EXCEL.DefaultFont.Height = -11
    EXCEL.DefaultFont.Name = 'Tahoma'
    EXCEL.DefaultFont.Style = []
    EXCEL.HeaderFont.Charset = DEFAULT_CHARSET
    EXCEL.HeaderFont.Color = clWindowText
    EXCEL.HeaderFont.Height = -11
    EXCEL.HeaderFont.Name = 'Tahoma'
    EXCEL.HeaderFont.Style = [fsBold]
    EXCEL.TableFont.Charset = DEFAULT_CHARSET
    EXCEL.TableFont.Color = clWindowText
    EXCEL.TableFont.Height = -11
    EXCEL.TableFont.Name = 'Tahoma'
    EXCEL.TableFont.Style = []
    EXCEL.TopMargin = 0.300000000000000000
    EXCEL.BottomMargin = 0.300000000000000000
    EXCEL.LeftMargin = 0.300000000000000000
    EXCEL.RightMargin = 0.300000000000000000
    Options = [xoClipboardMessage, xoExportInvisibles, xoFooterLine, xoHeaderLine, xoShowExportDate, xoShowHeader, xoShowProgress, xoUseAlignments]
    Version = '2.38'
    DataSet = CpT010
    Left = 400
  end
end
