inherited Mov0021: TMov0021
  Caption = 'MOV0021 - Pr'#233' Lan'#231'amento NF Entrada'
  ClientHeight = 473
  ClientWidth = 752
  ExplicitWidth = 760
  ExplicitHeight = 500
  PixelsPerInch = 96
  TextHeight = 13
  inherited NavBarra: TBrvDbNavCop
    Width = 752
    ExplicitWidth = 752
    inherited BrvSpeedButton1: TBrvSpeedButton
      Left = 725
      ExplicitLeft = 637
    end
    inherited SbtAjuda: TBrvSpeedButton
      Left = 700
      ExplicitLeft = 612
    end
  end
  inherited PnlFundo: TPanel
    Width = 752
    Height = 443
    ExplicitWidth = 752
    ExplicitHeight = 443
    object PnlCab: TPanel
      Left = 1
      Top = 1
      Width = 746
      Height = 112
      Align = alTop
      BevelKind = bkSoft
      BevelOuter = bvNone
      TabOrder = 0
      DesignSize = (
        742
        108)
      object BrvLabel3: TBrvLabel
        Left = 11
        Top = 85
        Width = 63
        Height = 13
        Caption = 'Chave NF-e'
        Font.Charset = DEFAULT_CHARSET
        Font.Color = clBlack
        Font.Height = -11
        Font.Name = 'Tahoma'
        Font.Style = [fsBold]
        ParentFont = False
      end
      object BrvLabel1: TBrvLabel
        Left = 12
        Top = 58
        Width = 26
        Height = 13
        Caption = 'Nota'
        Font.Charset = DEFAULT_CHARSET
        Font.Color = clBlue
        Font.Height = -11
        Font.Name = 'Tahoma'
        Font.Style = [fsBold]
        ParentFont = False
      end
      object LblEmpres: TLabel
        Left = 12
        Top = 31
        Width = 65
        Height = 13
        Caption = 'Fornecedor'
        Font.Charset = DEFAULT_CHARSET
        Font.Color = clBlue
        Font.Height = -11
        Font.Name = 'MS Sans Serif'
        Font.Style = [fsBold]
        ParentFont = False
      end
      object BrvLabel2: TBrvLabel
        Left = 12
        Top = 4
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
      object EdtNrNota: TBrvEditNum
        Left = 80
        Top = 55
        Width = 100
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
        BrQueryCode = 0
        BrRecordar = False
      end
      object EdtCdTitula: TBrvEditNum
        Left = 80
        Top = 28
        Width = 100
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
          'EdtCdTitula;CdTitula;S;S;'
          'LblRsTitula;RsTitula;S;N;')
        BrDicionario = DmSrvApl.BrvDicionario
        BrQueryCode = 19
        BrRecordar = False
      end
      object EdtCdEmpres: TBrvEdit
        Left = 80
        Top = 1
        Width = 100
        Height = 21
        Alignment = taRightJustify
        TabOrder = 0
        Text = '0'
        BrVisibleButton = True
        BrFunctionButton = VeConsulta
        BrOnBeforeConsulta = EdtCdEmpresBrOnBeforeConsulta
        BrConfigurar.Strings = (
          'EdtCdEmpres;CdEmpres;S;S;'
          'LblDsEmpres;DsEmpres;S;N;')
        BrAlignment = taLeftJustify
        BrDicionario = DmSrvApl.BrvDicionario
        BrvQueryCode = 12
        BrRecordar = False
      end
      object LblDsEmpres: TBrvRetCon
        Left = 186
        Top = 1
        Width = 527
        Height = 19
        TabStop = False
        Anchors = [akLeft, akTop, akRight]
        BevelInner = bvLowered
        BevelKind = bkFlat
        BevelWidth = 2
        BorderStyle = bsNone
        ParentColor = True
        ReadOnly = True
        TabOrder = 5
      end
      object LblRsTitula: TBrvRetCon
        Left = 186
        Top = 28
        Width = 527
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
        TabOrder = 6
      end
      object BtnIniciar: TBrvBitBtn
        Left = 623
        Top = 79
        Width = 90
        Height = 25
        Hint = 'Ir para o pr'#243'ximo'
        Anchors = [akTop, akRight]
        Caption = 'Iniciar'
        DoubleBuffered = True
        Font.Charset = DEFAULT_CHARSET
        Font.Color = clWindowText
        Font.Height = -11
        Font.Name = 'Tahoma'
        Font.Style = [fsBold]
        Glyph.Data = {
          96090000424D9609000000000000360000002800000028000000140000000100
          18000000000060090000C40E0000C40E00000000000000000000008080008080
          0080800080800080800080800080801C513B002400002F000037000039001B5C
          3A00808000808000808000808000808000808000808000808000808000808000
          80800080800080800080806785858D8A8A8989898B8B8B908D8D688686008080
          0080800080800080800080800080800080800080800080800080800080800080
          80008080001B000238001F541F2F69313470382B6C2D176214005A001F714700
          80800080800080800080800080800080800080800080800080800080805A8383
          948989938F8F999999A1A1A1A4A4A4A1A1A1A09C9CA398985D85850080800080
          80008080008080008080008080008080008080008080002100003100568057A2
          BEA3CEDFCFE9F1E9EDF4EDE2EDE3C6DCC892BE953B8E3A006C00008080008080
          0080800080800080800080800080800080808C8888928D8DAEAEAECACACADADA
          DAE3E3E3E5E5E5E1E1E1D8D8D8C7C7C7B3AEAE98979700808000808000808000
          8080008080008080008080002900003600AFC7B0F7FAF7FFFFFFFFFFFFFFFFFF
          FFFFFFFFFFFFFFFFFFFFFFFFEFF6F173B4770077000080800080800080800080
          800080800080808989898E8C8CCECECEE8E8E8F0F0F0F1F1F1F2F2F2F3F3F3F2
          F2F2F0F0F0EFEFEFE6E6E6C0C0C0A09B9B979B9B008080008080008080008080
          002500003800B8CDB8FFFFFFFFFFFFFFFFFFB9CDB86C986F5A8D5C7EA980D0E0
          D0FFFFFFFFFFFFFFFFFF8AC58E0082000080800080800080800080808D8A8A8E
          8C8CD2D2D2F0F0F0F1F1F1EEEEEED2D2D2B7B7B7B1B1B1BEBEBEDBDBDBF0F0F0
          EFEFEFECECECC7C7C7A49D9D008080008080008080008080003300B0C7B2FFFF
          FFFFFFFFB2C8B20A520D003400003800004800005000005500328C37E1EFE1FF
          FFFFFFFFFF72C1780092000080800080805B8383938D8DD0D0D0F0F0F0EDEDED
          D0D0D09595958787878989898C8C8C8E8E8E8D8D8DA8A8A8E2E2E2EFEFEFEDED
          EDC2C2C2B1A4A4008080008080002600578258F6FAF7FFFFFFB1C8B100440000
          3D00619262669C6A005A00036B0A087712007100148B1AF5FAF6FFFFFFDCF0DE
          23A62A278756008080978C8CAEAEAEE8E8E8F1F1F1CFCFCF8D8D8D8B8B8BB3B3
          B3B7B7B79292929898989C9C9C969696A2A2A2E9E9E9EEEEEEE1E1E1B2ADAD67
          8D8E09532B024500A3BFA4FFFFFFFFFFFF0B530E0045000049007AA67BFFFFFF
          7BB07F047309017A070B891700810064C06DFFFFFFFFFFFF6AC47100A0086886
          86959292CBCBCBEFEFEFEDEDED9595958E8E8E8E8E8EBEBEBEEEEEEEC0C0C09A
          9A9A9B9B9BA0A0A0989898BEBEBEEFEFEFEEEEEEBFBFBFBBAEB3003600206321
          D1E1D2FFFFFFB9CEB90035000554080052006EA370FFFFFFFFFFFF96C99A0986
          1200890908971410A31DE4F6E7FFFFFFA2DBA7019E0F928F8F9C9C9CDBDBDBF1
          F1F1D2D2D28787879393938F8F8FBBBBBBEBEBEBEDEDEDCACACA9F9F9F9C9C9C
          A1A1A1A6A6A6E3E3E3F0F0F0D0D0D0B7AEB7004B00317A35EBF2EBFFFFFF6B97
          6E004100055D0A005B006EA972FFFFFEFFFFFFFFFFFFC8E6CB31A93B079F1501
          9E0E99D89FFFFFFFCAEACE07A014929292A5A5A5E5E5E5F2F2F2B6B6B68C8C8C
          959595929292BBBBBBEAEAEAECECECEDEDEDDADADAAEAEAEA4A4A4A2A2A2CECE
          CEF0F0F0DCDCDCB9AEB800540037863DF0F6F1FFFFFF598E5D004E0008680F00
          660070B174FFFEFEFFFFFFFFFFFFFFFFFFA2DCA708A214009C0C83CE89FFFFFF
          E3F5E421A82B949494A8A8A8E6E6E6F2F2F2B1B1B18F8F8F989898959595BCBC
          BCEAEAEAEBEBEBECECECF1F1F1D0D0D0A5A5A5A1A1A1C7C7C7F0F0F0E2E2E2C0
          B5C00058002E8432E7F1E8FFFFFF7EAD8100530008721100700171B575FFFEFE
          FFFFFFF5FBF581CE880CA11A09A116029E11AEE0B2FFFFFFEAF7EB2FAF389A96
          96A6A6A6E3E3E3F1F1F1C0C0C08F8F8F9B9B9B979797BEBEBEEAEAEAEDEDEDE8
          E8E8C7C7C7A5A5A5A4A4A4A3A3A3D4D4D4EFEFEFE4E4E4C6B9C5046725197A19
          CBE2CDFFFFFFD1E3D1005900087911007A0270BA76FFFFFFF2F9F25EC167009C
          0A07A01406A0131BA827F5FBF5FFFFFFE3F4E42DAE386C8A8AA5A2A2DADADAF0
          F0F0DBDBDB8E8E8E9C9C9C999999C0C0C0EEEEEEE6E6E6BDBDBDA1A1A1A4A4A4
          A3A3A3A9A9A9E7E7E7EDEDEDE2E2E2C2B7BE00808000730098C89AFFFFFFFFFF
          FF348F3900740000840684C68AF1F9F138B442009D0D0AA1170FA31C00940092
          D598FFFFFFFFFFFFC5E9C81CAC23008080A99E9ECACACAEFEFEFEFEFEFAAAAAA
          9797979B9B9BC5C5C5E7E7E7B2B2B2A2A2A2A5A5A5A6A6A69A9A9ACCCCCCEEEE
          EEEDEDEDDBDBDB0080800080801E78483D9D3FF0F7F1FFFFFFE0EFE1148D1B00
          840042B24C2AAE35009C0B0BA21807A01400930043B64DFFFFFFFFFFFFFFFFFF
          85D4850080800080805E8787B7B2B2E6E6E6EFEFEFE3E3E3A2A2A2989898B3B3
          B3AEAEAEA0A0A0A5A5A5A3A3A39B9B9BB6B6B6EFEFEFEBEBEBEDEDEDD4CDCD00
          8080008080008080007E0076BF79FFFFFFFFFFFFF7FDF864C26D09A012009B08
          009D0D019E0F1BA8298FD596FFFFFFFFFFFFFFFFFFCCEDCD3FB8470080800080
          800080809E9A9AC4C3C3EBEBEBEFEFEFE9E9E9BEBEBEA5A5A5A0A0A0A2A2A2A2
          A2A2AAAAAACACACAEEEEEEECECECEDEDEDE0DEDEB8B6B6008080008080008080
          008080008A0689CE8EFFFFFFFFFFFFFFFFFFE5F5E69AD8A183D089ADE0B2F5FB
          F5FFFFFFFFFFFFFFFFFFE5F7E46EC4790080800080800080800080800080809C
          9C9CCBCACAECECECEEEEEEEFEFEFE3E3E3CECECEC7C7C7D3D3D3E8E8E8EFEFEF
          EBEBEBECECECE8E5E5BBBEBE0080800080800080800080800080800080800094
          0177C97DE5F5E7FFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFE0
          F5E078C783008080008080008080008080008080008080008080A29F9FC5C4C4
          E3E3E3EDEDEDEDEDEDEFEFEFEFEFEFEEEEEEECECECEBEBEBEDEDEDE6E4E4BDC0
          C000808000808000808000808000808000808000808000808000A50047BD4DB1
          E1B4ECF6EDFFFFFFFFFFFFFFFFFFFFFFFFF3FAF3BAEAB960C566008080008080
          008080008080008080008080008080008080008080B7AAAABFBABAD5D5D5E6E6
          E6EAEAEAECECECEDEDEDECECECE7E7E7E2DCDCC2C0C000808000808000808000
          80800080800080800080800080800080800080802B8B591CAC2448B95272C979
          91D4958AD29076CB7D58C45C0080800080800080800080800080800080800080
          80008080008080008080008080008080008080B2ADADB6B6B6C3C3C3CBCBCBC9
          C9C9C4C4C4C6C0C0008080008080008080008080008080008080}
        NumGlyphs = 2
        ParentDoubleBuffered = False
        ParentFont = False
        ParentShowHint = False
        ShowHint = True
        TabOrder = 4
        OnClick = BtnIniciarClick
        BrTipoBotao = BrBtnProxim
      end
      object EdtChaNFe: TEdit
        Left = 80
        Top = 82
        Width = 537
        Height = 21
        Anchors = [akLeft, akTop, akRight]
        TabOrder = 3
        OnExit = EdtChaNFeExit
      end
    end
    object PnlDet: TPanel
      Left = 1
      Top = 113
      Width = 746
      Height = 325
      Align = alClient
      TabOrder = 1
      Visible = False
      object PgcDet: TPageControl
        Left = 1
        Top = 1
        Width = 744
        Height = 289
        ActivePage = TbsContabil
        Align = alClient
        TabOrder = 0
        object TbsContabil: TTabSheet
          Caption = 'Contabilidade\Centro de Custo'
          object Splitter1: TSplitter
            Left = 0
            Top = 161
            Width = 736
            Height = 3
            Cursor = crVSplit
            Align = alTop
            ExplicitWidth = 100
          end
          object DbgF014: TBrvDbGrid
            Left = 0
            Top = 0
            Width = 736
            Height = 161
            BrShowMemo = True
            BrReadOnlyStyle = [fsItalic]
            BrReadOnlyColor = clMaroon
            Align = alTop
            DataSource = DsF014
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
                ButtonStyle = cbsConsulta
                Expanded = False
                FieldName = 'NrPlano'
                Title.Caption = 'Plano'
                Width = 65
                Visible = True
                BrOnAfterConsul = DbgF014Columns0BrOnAfterConsul
                BrConsulta = 34
                BrConfigurar.Strings = (
                  'NRPLANO;NRPLANO;S;S;'
                  'DSPLANO;DSPLANO;S;N;')
                BrPermissao = []
                BrValueHalfChecked = False
                BrSaveOnClick = False
              end
              item
                Expanded = False
                FieldName = 'DsPlano'
                ReadOnly = True
                Title.Caption = 'Descri'#231#227'o Plano'
                Width = 200
                Visible = True
                BrConsulta = 0
                BrPermissao = []
                BrValueHalfChecked = False
                BrSaveOnClick = False
              end
              item
                ButtonStyle = cbsConsulta
                Expanded = False
                FieldName = 'NrConta'
                Title.Caption = 'Conta'
                Width = 65
                Visible = True
                BrOnBeforeConsul = DbgF014Columns2BrOnBeforeConsul
                BrOnAfterConsul = DbgF014Columns2BrOnAfterConsul
                BrConsulta = 53
                BrConfigurar.Strings = (
                  'NRCONTA;NRCONTA;S;S;'
                  'NMCONTA;NMCONTA;S;N;')
                BrPermissao = []
                BrValueHalfChecked = False
                BrSaveOnClick = False
              end
              item
                Expanded = False
                FieldName = 'NmConta'
                ReadOnly = True
                Title.Caption = 'Descri'#231#227'o Conta'
                Width = 200
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
                Width = 65
                Visible = True
                BrOnAfterConsul = DbgF014Columns4BrOnAfterConsul
                BrConsulta = 54
                BrConfigurar.Strings = (
                  'CDHISTOR;CDHISTOR;S;S;'
                  'DSHISTOR;DSHISTOR;S;N;')
                BrPermissao = []
                BrValueHalfChecked = False
                BrSaveOnClick = False
              end
              item
                Expanded = False
                FieldName = 'DsHistor'
                ReadOnly = True
                Title.Caption = 'Descri'#231#227'o Hist'#243'rico'
                Width = 200
                Visible = True
                BrConsulta = 0
                BrPermissao = []
                BrValueHalfChecked = False
                BrSaveOnClick = False
              end
              item
                Alignment = taRightJustify
                Expanded = False
                FieldName = 'VrLancto'
                Title.Caption = 'Valor'
                Width = 80
                Visible = True
                BrConsulta = 0
                BrPermissao = []
                BrValueHalfChecked = False
                BrSaveOnClick = False
              end>
          end
          object DbgF017: TBrvDbGrid
            Left = 0
            Top = 164
            Width = 736
            Height = 97
            BrShowMemo = True
            BrReadOnlyStyle = [fsItalic]
            BrReadOnlyColor = clMaroon
            Align = alClient
            DataSource = DsF017
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
                ButtonStyle = cbsConsulta
                Expanded = False
                FieldName = 'CdCenCus'
                Title.Caption = 'Centro de Custo'
                Width = 85
                Visible = True
                BrOnAfterConsul = DbgF017Columns0BrOnAfterConsul
                BrConsulta = 35
                BrConfigurar.Strings = (
                  'CdCenCus;CDCENCUS;S;S;'
                  'DSCENCUS;DSCENCUS;S;N;')
                BrPermissao = []
                BrValueHalfChecked = False
                BrSaveOnClick = False
              end
              item
                Expanded = False
                FieldName = 'DsCenCus'
                ReadOnly = True
                Title.Caption = 'Descri'#231#227'o Centro de Custo'
                Width = 250
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
                Width = 80
                Visible = True
                BrConsulta = 0
                BrPermissao = []
                BrValueHalfChecked = False
                BrSaveOnClick = False
              end>
          end
        end
        object TbsCtPag: TTabSheet
          Caption = 'Contas a Pagar'
          ImageIndex = 1
          object BrvDbGrid2: TBrvDbGrid
            Left = 0
            Top = 0
            Width = 736
            Height = 261
            BrShowMemo = True
            BrReadOnlyStyle = [fsItalic]
            BrReadOnlyColor = clMaroon
            Align = alClient
            DataSource = DsF015
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
                FieldName = 'NrParcel'
                Title.Caption = 'Parcela'
                Width = 65
                Visible = True
                BrConsulta = 0
                BrPermissao = []
                BrValueHalfChecked = False
                BrSaveOnClick = False
              end
              item
                Expanded = False
                FieldName = 'DtVencto'
                Title.Caption = 'Vencimento'
                Width = 120
                Visible = True
                BrConsulta = 0
                BrPermissao = []
                BrValueHalfChecked = False
                BrSaveOnClick = False
              end
              item
                Expanded = False
                FieldName = 'VrDocto'
                Title.Caption = 'Valor do Documento'
                Width = 120
                Visible = True
                BrConsulta = 0
                BrPermissao = []
                BrValueHalfChecked = False
                BrSaveOnClick = False
              end>
          end
        end
      end
      object PnlControl: TPanel
        Left = 1
        Top = 290
        Width = 744
        Height = 34
        Align = alBottom
        BevelOuter = bvNone
        TabOrder = 1
        DesignSize = (
          744
          34)
        object BtnGravar: TBrvBitBtn
          Left = 544
          Top = 5
          Width = 90
          Height = 25
          Hint = 'Salvar'
          Anchors = [akTop, akRight]
          Caption = 'Gravar'
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
          OnClick = BtnGravarClick
          BrTipoBotao = BrBtnSalvar
        end
        object BtnCancel: TBrvBitBtn
          Left = 640
          Top = 5
          Width = 90
          Height = 25
          Hint = 'Cancelar'
          Anchors = [akTop, akRight]
          Caption = 'Cancelar'
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
          OnClick = BtnCancelClick
          BrTipoBotao = BrBtnCancel
        end
        object GroupBox4: TGroupBox
          Left = 0
          Top = 0
          Width = 538
          Height = 34
          Align = alLeft
          Caption = 'Totais:'
          Font.Charset = DEFAULT_CHARSET
          Font.Color = clWindowText
          Font.Height = -11
          Font.Name = 'MS Sans Serif'
          Font.Style = [fsBold]
          ParentFont = False
          TabOrder = 2
          object LblTtCcPag: TLabel
            Left = 442
            Top = 15
            Width = 85
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
            Left = 352
            Top = 15
            Width = 88
            Height = 13
            Caption = 'Contas a Pagar'
            Font.Charset = DEFAULT_CHARSET
            Font.Color = clWindowText
            Font.Height = -11
            Font.Name = 'MS Sans Serif'
            Font.Style = [fsBold]
            ParentFont = False
          end
          object Label42: TLabel
            Left = 6
            Top = 15
            Width = 78
            Height = 13
            Caption = 'Contabilidade'
            Font.Charset = DEFAULT_CHARSET
            Font.Color = clWindowText
            Font.Height = -11
            Font.Name = 'MS Sans Serif'
            Font.Style = [fsBold]
            ParentFont = False
          end
          object LblTtContabil: TLabel
            Left = 84
            Top = 15
            Width = 85
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
          object BrvLabel4: TBrvLabel
            Left = 181
            Top = 15
            Width = 74
            Height = 13
            Caption = 'Centro Custo'
          end
          object LblTtCenCus: TBrvLabel
            Left = 258
            Top = 15
            Width = 85
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
    end
  end
  inherited PopCfgFrm: TPopupMenu
    Left = 16
    Top = 0
  end
  inherited ImlPopFrm: TImageList
    Left = 48
    Top = 0
  end
  inherited LspS049: TBrvListParam
    Left = 80
    Top = 0
  end
  object CcF013: TBrvClientDataSet
    Aggregates = <>
    Params = <>
    BrShowFieldName = False
    BrQueryCode = 0
    BrDicionario = DmSrvApl.BrvDicionario
    BrType = VqNormal
    BrGravaLog = True
    BrUserCode = 0
    Left = 24
    Top = 232
  end
  object CcF014: TBrvClientDataSet
    Aggregates = <>
    Params = <>
    BeforeInsert = CcF014BeforeInsert
    AfterInsert = CcF014AfterInsert
    BeforePost = CcF014BeforePost
    AfterPost = CcF014AfterPost
    AfterDelete = CcF014AfterDelete
    AfterScroll = CcF014AfterScroll
    BrShowFieldName = False
    BrQueryCode = 0
    BrDicionario = DmSrvApl.BrvDicionario
    BrType = VqNormal
    BrGravaLog = True
    BrUserCode = 0
    Left = 24
    Top = 264
  end
  object DsF014: TDataSource
    DataSet = CcF014
    Left = 56
    Top = 264
  end
  object CcF015: TBrvClientDataSet
    Aggregates = <>
    Params = <>
    BeforePost = CcF015BeforePost
    AfterPost = CcF015AfterPost
    AfterDelete = CcF015AfterDelete
    BrShowFieldName = False
    BrQueryCode = 0
    BrDicionario = DmSrvApl.BrvDicionario
    BrType = VqNormal
    BrGravaLog = True
    BrUserCode = 0
    Left = 24
    Top = 296
  end
  object DsF015: TDataSource
    DataSet = CcF015
    Left = 56
    Top = 296
  end
  object ApplicationEvents1: TApplicationEvents
    OnException = ApplicationEvents1Exception
    Left = 24
    Top = 328
  end
  object CcF017: TBrvClientDataSet
    Aggregates = <>
    Params = <>
    AfterInsert = CcF017AfterInsert
    AfterPost = CcF017AfterPost
    AfterDelete = CcF017AfterDelete
    BrShowFieldName = False
    BrQueryCode = 0
    BrDicionario = DmSrvApl.BrvDicionario
    BrType = VqNormal
    BrGravaLog = True
    BrUserCode = 0
    Left = 24
    Top = 200
  end
  object DsF017: TDataSource
    DataSet = CcF017
    Left = 56
    Top = 200
  end
end
