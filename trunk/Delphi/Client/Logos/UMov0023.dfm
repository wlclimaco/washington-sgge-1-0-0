inherited Mov0023: TMov0023
  Caption = 'MOV0023 - Montagem de Lote'
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
      ExplicitLeft = 715
    end
    inherited SbtAjuda: TBrvSpeedButton
      Left = 694
      ExplicitLeft = 684
    end
  end
  inherited PnlFundo: TPanel
    Width = 752
    Height = 443
    ExplicitWidth = 752
    ExplicitHeight = 443
    object Splitter1: TSplitter
      Left = 1
      Top = 169
      Width = 746
      Height = 3
      Cursor = crVSplit
      Align = alTop
      ExplicitWidth = 175
    end
    object PnlCab: TPanel
      Left = 1
      Top = 1
      Width = 746
      Height = 168
      Align = alTop
      BevelKind = bkSoft
      BevelOuter = bvNone
      TabOrder = 0
      object DbgF013: TBrvDbGrid
        Left = 0
        Top = 0
        Width = 742
        Height = 164
        BrShowMemo = False
        BrReadOnlyStyle = []
        BrReadOnlyColor = clMaroon
        Align = alClient
        DataSource = DsF013
        PopupMenu = PopF013
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
            ButtonStyle = cbsNone
            Expanded = False
            FieldName = 'SnCheck'
            Title.Caption = '[ x ]'
            Title.Font.Charset = DEFAULT_CHARSET
            Title.Font.Color = clWindowText
            Title.Font.Height = -11
            Title.Font.Name = 'Tahoma'
            Title.Font.Style = [fsBold]
            Width = 27
            Visible = True
            BrConsulta = 0
            BrPermissao = []
            BrValueChecked = 'S'
            BrValueUnchecked = 'N'
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
            FieldName = 'CdTitula'
            Title.Caption = 'Fornecedor'
            Width = 60
            Visible = True
            BrConsulta = 0
            BrPermissao = []
            BrValueHalfChecked = False
            BrSaveOnClick = False
          end
          item
            Expanded = False
            FieldName = 'NmTitula'
            Title.Caption = 'Nome Fornecedor'
            Width = 200
            Visible = True
            BrConsulta = 0
            BrPermissao = []
            BrValueHalfChecked = False
            BrSaveOnClick = False
          end
          item
            Expanded = False
            FieldName = 'DsLogin'
            Title.Caption = 'Usu'#225'rio'
            Width = 95
            Visible = True
            BrConsulta = 0
            BrPermissao = []
            BrValueHalfChecked = False
            BrSaveOnClick = False
          end
          item
            Expanded = False
            FieldName = 'DtInserc'
            Title.Caption = 'Inser'#231#227'o'
            Width = 119
            Visible = True
            BrConsulta = 0
            BrPermissao = []
            BrValueHalfChecked = False
            BrSaveOnClick = False
          end
          item
            Expanded = False
            FieldName = 'NrNota'
            Title.Caption = 'Nota'
            Width = 82
            Visible = True
            BrConsulta = 0
            BrPermissao = []
            BrValueHalfChecked = False
            BrSaveOnClick = False
          end>
      end
    end
    object PnlControl: TPanel
      Left = 1
      Top = 404
      Width = 746
      Height = 34
      Align = alBottom
      BevelOuter = bvNone
      TabOrder = 1
      DesignSize = (
        746
        34)
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
        TabOrder = 0
        object LblTtCcPag: TLabel
          Left = 442
          Top = 15
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
        object BrvLabel1: TBrvLabel
          Left = 176
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
      object BtnMontaLote: TBrvBitBtn
        Left = 548
        Top = 5
        Width = 90
        Height = 25
        Anchors = [akTop, akRight]
        Caption = 'Gerar Lote'
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
        TabOrder = 1
        OnClick = BtnMontaLoteClick
        BrTipoBotao = BrBtnSalvar
      end
      object BtnAtualizar: TBrvBitBtn
        Left = 644
        Top = 5
        Width = 90
        Height = 25
        Anchors = [akTop, akRight]
        Caption = 'Atualizar'
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
        TabOrder = 2
        OnClick = BtnAtualizarClick
        BrTipoBotao = BrBtnAtualizar
      end
    end
    object PgcDet: TPageControl
      Left = 1
      Top = 172
      Width = 746
      Height = 232
      ActivePage = TbsContabil
      Align = alClient
      TabOrder = 2
      object TbsContabil: TTabSheet
        Caption = 'Contabilidade'
        object Splitter2: TSplitter
          Left = 0
          Top = 105
          Width = 738
          Height = 3
          Cursor = crVSplit
          Align = alBottom
          ExplicitTop = 0
          ExplicitWidth = 84
        end
        object DbgF014: TBrvDbGrid
          Left = 0
          Top = 0
          Width = 738
          Height = 105
          BrShowMemo = True
          BrReadOnlyStyle = [fsItalic]
          BrReadOnlyColor = clMaroon
          Align = alClient
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
              FieldName = 'vrlancto'
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
          Top = 108
          Width = 738
          Height = 96
          BrShowMemo = True
          BrReadOnlyStyle = [fsItalic]
          BrReadOnlyColor = clMaroon
          Align = alBottom
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
              Expanded = False
              FieldName = 'CdCenCus'
              Title.Caption = 'Centro de Custo'
              Width = 85
              Visible = True
              BrConsulta = 0
              BrPermissao = []
              BrValueHalfChecked = False
              BrSaveOnClick = False
            end
            item
              Expanded = False
              FieldName = 'DsCenCus'
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
        ExplicitLeft = 0
        ExplicitTop = 0
        ExplicitWidth = 0
        ExplicitHeight = 0
        object DbgF015: TBrvDbGrid
          Left = 0
          Top = 0
          Width = 738
          Height = 204
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
  end
  object CpF013: TBrvClientDataSet
    Aggregates = <>
    Params = <>
    AfterScroll = CpF013AfterScroll
    BrShowFieldName = False
    BrQueryCode = 0
    BrDicionario = DmSrvApl.BrvDicionario
    BrType = VqNormal
    BrGravaLog = True
    BrUserCode = 0
    Left = 40
    Top = 72
  end
  object DsF013: TDataSource
    DataSet = CpF013
    Left = 72
    Top = 72
  end
  object CpF014: TBrvClientDataSet
    Aggregates = <>
    Params = <>
    AfterScroll = CpF014AfterScroll
    BrShowFieldName = False
    BrQueryCode = 0
    BrDicionario = DmSrvApl.BrvDicionario
    BrType = VqNormal
    BrGravaLog = True
    BrUserCode = 0
    Left = 40
    Top = 104
  end
  object DsF014: TDataSource
    DataSet = CpF014
    Left = 72
    Top = 104
  end
  object CpF015: TBrvClientDataSet
    Aggregates = <>
    Params = <>
    BrShowFieldName = False
    BrQueryCode = 0
    BrDicionario = DmSrvApl.BrvDicionario
    BrType = VqNormal
    BrGravaLog = True
    BrUserCode = 0
    Left = 40
    Top = 136
  end
  object DsF015: TDataSource
    DataSet = CpF015
    Left = 72
    Top = 136
  end
  object PopF013: TPopupMenu
    Left = 104
    Top = 72
    object odos1: TMenuItem
      Caption = 'Todos'
      OnClick = odos1Click
    end
    object Nenhum1: TMenuItem
      Caption = 'Nenhum'
      OnClick = Nenhum1Click
    end
  end
  object DsF017: TDataSource
    DataSet = CpF017
    Left = 72
    Top = 168
  end
  object CpF017: TBrvClientDataSet
    Aggregates = <>
    Params = <>
    BrShowFieldName = False
    BrQueryCode = 0
    BrDicionario = DmSrvApl.BrvDicionario
    BrType = VqNormal
    BrGravaLog = True
    BrUserCode = 0
    Left = 40
    Top = 168
  end
end
