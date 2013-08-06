inherited Mov0025: TMov0025
  Caption = 'MOV0025 - Entrada de Notas'
  ClientHeight = 523
  ClientWidth = 912
  Font.Style = [fsBold]
  OnCloseQuery = FormCloseQuery
  ExplicitWidth = 920
  ExplicitHeight = 550
  PixelsPerInch = 96
  TextHeight = 13
  inherited NavBarra: TBrvDbNavCop
    Width = 912
    ExplicitWidth = 912
    inherited BrvSpeedButton1: TBrvSpeedButton
      Left = 885
      ExplicitLeft = 878
    end
    inherited SbtAjuda: TBrvSpeedButton
      Left = 860
      ExplicitLeft = 853
    end
  end
  inherited PnlFundo: TPanel
    Width = 912
    Height = 493
    ExplicitWidth = 912
    ExplicitHeight = 493
    object PgcNF: TPageControl
      Left = 1
      Top = 1
      Width = 906
      Height = 487
      ActivePage = TbsNota
      Align = alClient
      TabOrder = 0
      Visible = False
      object TbsPreLan: TTabSheet
        Caption = 'TbsPreLan'
        object TPanel
          Left = 0
          Top = 0
          Width = 898
          Height = 34
          Align = alTop
          BevelOuter = bvNone
          Caption = 'Pr'#233'-Lan'#231'amentos'
          Font.Charset = DEFAULT_CHARSET
          Font.Color = clWindowText
          Font.Height = -11
          Font.Name = 'Tahoma'
          Font.Style = [fsBold]
          ParentFont = False
          TabOrder = 0
        end
        object DbgF013: TBrvDbGrid
          Left = 0
          Top = 34
          Width = 898
          Height = 391
          BrShowMemo = False
          BrReadOnlyStyle = []
          BrReadOnlyColor = clMaroon
          Align = alClient
          DataSource = DsF013
          TabOrder = 1
          TitleFont.Charset = DEFAULT_CHARSET
          TitleFont.Color = clWindowText
          TitleFont.Height = -11
          TitleFont.Name = 'Tahoma'
          TitleFont.Style = [fsBold]
          OnDblClick = DbgF013DblClick
          BrDicionario = DmSrvApl.BrvDicionario
          BrDrawColumn.Strings = (
            'N'#227'o remova essas duas linhas de formata'#231#227'o:'
            'CampoTabela;Operador;ValorComparativo;Cor;')
          BrGradeZebrada = False
          Columns = <
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
              Width = 80
              Visible = True
              BrConsulta = 0
              BrPermissao = []
              BrValueHalfChecked = False
              BrSaveOnClick = False
            end
            item
              Expanded = False
              FieldName = 'CjTitula'
              Title.Caption = 'CNPJ'
              Width = 117
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
              Width = 133
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
        object PnlControl: TPanel
          Left = 0
          Top = 425
          Width = 898
          Height = 34
          Align = alBottom
          BevelOuter = bvNone
          TabOrder = 2
          DesignSize = (
            898
            34)
          object Label1: TLabel
            Left = 359
            Top = 12
            Width = 126
            Height = 13
            Anchors = [akTop, akRight]
            Caption = 'Forma de Lan'#231'amento'
            Font.Charset = DEFAULT_CHARSET
            Font.Color = clWindowText
            Font.Height = -11
            Font.Name = 'Tahoma'
            Font.Style = [fsBold]
            ParentFont = False
          end
          object Bevel1: TBevel
            Left = 771
            Top = 6
            Width = 5
            Height = 30
            Shape = bsLeftLine
          end
          object BtnManual: TBrvBitBtn
            Left = 491
            Top = 6
            Width = 132
            Height = 25
            Hint = 'Ir para o pr'#243'ximo'
            Anchors = [akTop, akRight]
            Caption = 'Manual'
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
            TabOrder = 0
            OnClick = BtnManualClick
            BrTipoBotao = BrBtnProxim
          end
          object BtnLancar: TBrvBitBtn
            Left = 629
            Top = 6
            Width = 132
            Height = 25
            Anchors = [akTop, akRight]
            Caption = 'Pr'#233' Lan'#231'amento'
            DoubleBuffered = True
            Font.Charset = DEFAULT_CHARSET
            Font.Color = clWindowText
            Font.Height = -11
            Font.Name = 'Tahoma'
            Font.Style = [fsBold]
            Glyph.Data = {
              96090000424D9609000000000000360000002800000028000000140000000100
              18000000000060090000C40E0000C40E00000000000000000000008080008080
              008080B08B7BBC8887BA8686BA8685BA8685BA8685BA8684BA8584BA8584BA85
              84BA8584BA8584BA8584BB8684B28E7900808000808000808000808000808094
              A494A1A1A19F9F9F9F9F9F9F9F9F9F9F9F9E9E9E9E9E9E9E9E9E9E9E9E9E9E9E
              9E9E9E9E9E9E9F9F9F94AA94008080008080008080008080008080DDCCAEFFF1
              D1FCE7C4FBE4BBFAE1B4F8DDADF7DAA5F5D69CF5D599F5D599F5D599F5D599F5
              D599F8DA9BD0B583008080008080008080008080008080C2D2C2E9E9E9DFDFDF
              DBDBDBD6D6D6D3D3D3CFCFCFC8C8C8C7C7C7C7C7C7C7C7C7C7C7C7C7C7C7CACA
              CAA5BAA5008080008080008080008080008080D7C7AEF9E9D0F4DFC3F4DBBCF3
              DAB7F1D6AFF0D2A9F0CFA0EFCC9AEECC97EDCB96EDCB96EDCB96F1D098CDB082
              008080008080008080008080008080C1D1C1E5E5E5DADADAD8D8D8D5D5D5D0D0
              D0CCCCCCC7C7C7C4C4C4C1C1C1C1C1C1C1C1C1C1C1C1C4C4C4A3B8A300808000
              8080008080008080008080D8C9B1FBEDD6F5E2CAF4DEC3F4DCBDF2D9B6F1D5AF
              F0D3A7F0CFA1EECD9BEDCB96EECC96EECC97F1D098CDB0820080800080800080
              80008080008080C2D2C2E8E8E8DEDEDEDBDBDBD9D9D9D3D3D3CFCFCFCBCBCBC9
              C9C9C4C4C4C1C1C1C2C2C2C2C2C2C4C4C4A3B8A3008080008080008080008080
              008080DACBB6FCF0DDF7E5D0F5E2C8F4DFC4F3DCBCF2D8B5F1D5ADF0D2A7F0D0
              A1EECD9AEDCB95EECC96F1D098CDB082008080008080008080008080008080C5
              D5C5ECECECE3E3E3DFDFDFDCDCDCD8D8D8D4D4D4CFCFCFCCCCCCC9C9C9C3C3C3
              C1C1C1C1C1C1C4C4C4A3B8A3008080008080008080008080008080DBCEBBFDF4
              E6F7E9D9F7E5D0F6E3CCF3DFC5F4DCBEF3D9B5F2D6B0F1D3AAF0D0A3EDCB9AED
              CB96F1D098CDB082008080008080008080008080008080C8D8C8F1F1F1E7E7E7
              E3E3E3E0E0E0DCDCDCD8D8D8D3D3D3D0D0D0CDCDCDC9C9C9C4C4C4C1C1C1C4C4
              C4A3B8A3008080008080008080008080008080DDD3C1FDF7ED000000F8E8D600
              0000F5E3CA000000F4DBBB000000F1D6B0000000000000000000F1D098CDB082
              008080008080008080008080008080CCDCCCF4F4F4000000E7E7E7000000DFDF
              DF000000D7D7D7000000D1D1D1000000000000000000C4C4C4A3B8A300808000
              8080008080008080008080E0D7C5FEFBF3000000F8ECDD000000F6E6D1000000
              F5DFC2000000F2D9B6000000F0D3A8F0D0A0F1D19BCDB0820080800080800080
              80008080008080D0E0D0F9F9F9000000EBEBEB000000E3E3E3000000DADADA00
              0000D4D4D4000000CCCCCCC7C7C7C6C6C6A3B8A3008080008080008080008080
              008080E3DCCBFFFFFAFBF3EC000000FAEEDEF7E8D8000000F5E2C8000000F5DC
              BD000000F0D6AFF0D3A6F3D3A2CCB084008080008080008080008080008080D5
              E5D5FDFDFDF3F3F3000000ECECECE7E7E7000000DEDEDE000000D7D7D7000000
              CFCFCFCBCBCBCACACAA5BAA5008080008080008080008080008080E5DFCFFFFF
              FF000000FAF3EB000000F8ECDD000000000000000000F4DFC2000000F2D8B6F1
              D6ADF4D7A9CDB389008080008080008080008080008080D7E7D7FFFFFF000000
              F3F3F3000000EBEBEB000000000000000000DCDCDC000000D4D4D4CFCFCFCECE
              CEA7BDA7008080008080008080008080008080E7E0CFFFFFFF000000FDF8F200
              0000FAF0E6000000F8E9D6000000F6E3CB000000F4DCBDF3D9B4F4DAB0CFB68D
              008080008080008080008080008080D9E9D9FFFFFF000000F8F8F8000000EFEF
              EF000000E7E7E7000000DFDFDF000000D8D8D8D3D3D3D2D2D2ABC0AB00808000
              8080008080008080008080E9E1D0FFFFFFFFFFFFFFFDFCFDF9F5FBF4EDFAF1E7
              F9ECDDF8E9D7F7E5D0F6E2C9F5DEC2F5DDBCF6DDB7C6B38E0080800080800080
              80008080008080DAEADAFFFFFFFFFFFFFDFDFDF9F9F9F3F3F3F0F0F0EAEAEAE8
              E8E8E4E4E4DFDFDFDBDBDBD8D8D8D7D7D7A7BCA7008080008080008080008080
              008080EBE3D0FFFFFFFFFFFFFFFFFFFFFEFCFDF9F3FBF4EDFAEFE4F9EDDDF8E9
              D6F7E4CFF7E4CBF1DCBFDBC9ADA8A083008080008080008080008080008080DB
              EBDBFFFFFFFFFFFFFEFEFEFCFCFCF7F7F7F4F4F4EEEEEEEBEBEBE7E7E7E2E2E2
              E0E0E0D8D8D8C4C4C492A792008080008080008080008080008080EDE5D1FFFF
              FFFFFFFFFFFFFFFFFFFFFEFDFBFCF8F4FBF3EBF9F0E4FAEDDEFFF5E2F5EBD7D0
              C6B5BCB6A4A09E85008080008080008080008080008080DDECDDFFFFFFFFFFFF
              FFFFFFFFFFFFFDFDFDF8F8F8F2F2F2EEEEEEECECECF1F1F1E6E6E6C2C2C2B1B1
              B190A590008080008080008080008080008080EFE6D2FFFFFFFFFFFFFFFFFFFF
              FFFFFFFFFFFFFEFCFCF6F1FCF4ECFAF0E4C8A396AA7E78A27C7BA37E7EA18A6F
              008080008080008080008080008080DEEEDEFFFFFFFFFFFFFFFFFFFFFFFFFFFF
              FFFDFDFDF5F5F5F3F3F3EEEEEEAFAFAF9090908F8F8F909090859D8500808000
              8080008080008080008080F1E9D3FFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFF
              FFFCFBFFFDF7F4EBE3B88D87E1B385EFB361F5AD3CD296570080800080800080
              80008080008080DFEFDFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFDFDFDFA
              FAFAECECEC9E9E9EB4B4B4A7A7A7999F9994A194008080008080008080008080
              008080F1EAD3FFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFEFFFFFFF6EF
              EBBA8F89E8BD8BF8C262D5986C008080008080008080008080008080008080E0
              F0E0FFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFF0F0F0A1A1A1
              BABABAADB3ADA0A0A0008080008080008080008080008080008080F4EEDAFFFF
              FFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFF9F7F7BB928EF0C687CC
              9D71008080008080008080008080008080008080008080E7F7E7FFFFFFFFFFFF
              FFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFAFAFAA4A4A4BBBDBB9EAA9E0080
              80008080008080008080008080008080008080E7EBADF1F8CFF0F5CCEEF4CCED
              F2CCEBF0CAE9EFCAE6EDCAE5ECCBDDE1C0B29C78BAAE6B008080008080008080
              008080008080008080008080008080B2DCB2BED8BEBDD7BDBDD7BDBDD7BDBDD7
              BDBDD7BDBDD7BDBDD8BDBCD7BC93AE9392C39200808000808000808000808000
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
            OnClick = BtnLancarClick
            BrTipoBotao = BrBtnXML
          end
          object BtnAtualizar: TBrvBitBtn
            Left = 782
            Top = 6
            Width = 107
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
            ParentShowHint = False
            ShowHint = True
            TabOrder = 2
            OnClick = BtnAtualizarClick
            BrTipoBotao = BrBtnAtualizar
          end
        end
      end
      object TbsNota: TTabSheet
        Caption = 'TbsNota'
        ImageIndex = 1
        object Panel1: TPanel
          Left = 0
          Top = 0
          Width = 898
          Height = 169
          Align = alTop
          BevelKind = bkSoft
          BevelOuter = bvNone
          TabOrder = 0
          object BrvLabel1: TBrvLabel
            Left = 8
            Top = 35
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
          object BrvLabel3: TBrvLabel
            Left = 8
            Top = 62
            Width = 64
            Height = 13
            Caption = 'Fornecedor'
            Font.Charset = DEFAULT_CHARSET
            Font.Color = clBlue
            Font.Height = -11
            Font.Name = 'Tahoma'
            Font.Style = [fsBold]
            ParentFont = False
          end
          object BrvLabel4: TBrvLabel
            Left = 8
            Top = 144
            Width = 42
            Height = 13
            Caption = 'N'#186' Nota'
            Font.Charset = DEFAULT_CHARSET
            Font.Color = clBlue
            Font.Height = -11
            Font.Name = 'Tahoma'
            Font.Style = [fsBold]
            ParentFont = False
          end
          object BrvLabel5: TBrvLabel
            Left = 194
            Top = 144
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
          object BrvLabel6: TBrvLabel
            Left = 298
            Top = 144
            Width = 53
            Height = 13
            Caption = 'Sub S'#233'rie'
            Font.Charset = DEFAULT_CHARSET
            Font.Color = clBlue
            Font.Height = -11
            Font.Name = 'Tahoma'
            Font.Style = [fsBold]
            ParentFont = False
          end
          object BrvLabel7: TBrvLabel
            Left = 8
            Top = 90
            Width = 39
            Height = 13
            Caption = 'Evento'
            Font.Charset = DEFAULT_CHARSET
            Font.Color = clBlue
            Font.Height = -11
            Font.Name = 'Tahoma'
            Font.Style = [fsBold]
            ParentFont = False
          end
          object BrvLabel8: TBrvLabel
            Left = 541
            Top = 144
            Width = 46
            Height = 13
            Caption = 'Emiss'#227'o'
            Font.Charset = DEFAULT_CHARSET
            Font.Color = clBlue
            Font.Height = -11
            Font.Name = 'Tahoma'
            Font.Style = [fsBold]
            ParentFont = False
          end
          object BrvLabel10: TBrvLabel
            Left = 706
            Top = 144
            Width = 44
            Height = 13
            Caption = 'Entrada'
          end
          object BrvLabel11: TBrvLabel
            Left = 427
            Top = 144
            Width = 41
            Height = 13
            Caption = 'Modelo'
            Font.Charset = DEFAULT_CHARSET
            Font.Color = clBlue
            Font.Height = -11
            Font.Name = 'Tahoma'
            Font.Style = [fsBold]
            ParentFont = False
          end
          object BrvLabel29: TBrvLabel
            Left = 252
            Top = 9
            Width = 63
            Height = 13
            Caption = 'Chave NF-e'
          end
          object BrvLabel50: TBrvLabel
            Left = 8
            Top = 117
            Width = 54
            Height = 13
            Caption = 'Opera'#231#227'o'
          end
          object BrvLabel24: TBrvLabel
            Left = 8
            Top = 9
            Width = 57
            Height = 13
            Caption = 'Finalidade'
            Font.Charset = DEFAULT_CHARSET
            Font.Color = clBlue
            Font.Height = -11
            Font.Name = 'Tahoma'
            Font.Style = [fsBold]
            ParentFont = False
          end
          object BrvDbEdit1: TBrvDbEdit
            Left = 77
            Top = 32
            Width = 87
            Height = 21
            DataField = 'CDEMPRES'
            DataSource = DsF001
            TabOrder = 2
            BrAlignment = taRightJustify
            BrVisibleButton = True
            BrFunctionButton = TpDbConsulta
            BrOnBeforeConsulta = BrvDbEdit1BrOnBeforeConsulta
            BrConfigurar.Strings = (
              'CdEmpres;CdEmpres;S;S;'
              'EdtDsEmpres;DsEmpres;S;N;'
              'EdtCdEstEmp;CdEstado;S;N;'
              'EdtCdEmpEst;CdSeqEst;S;N;'
              'EdtCdGruEmp;CdGruEmp;S;N;')
            BrDicionario = DmSrvApl.BrvDicionario
            BrQueryConsulta = 12
            BrvReadOnlyColor = 14541539
          end
          object EdtDsEmpres: TBrvRetCon
            Left = 170
            Top = 32
            Width = 489
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
          object BrvDbEdit2: TBrvDbEdit
            Left = 77
            Top = 140
            Width = 87
            Height = 21
            DataField = 'NRNOTA'
            DataSource = DsF001
            TabOrder = 15
            BrAlignment = taRightJustify
            BrVisibleButton = False
            BrFunctionButton = TpDbConsulta
            BrDicionario = DmSrvApl.BrvDicionario
            BrQueryConsulta = 0
            BrvReadOnlyColor = 14541539
          end
          object BrvDbEdit3: TBrvDbEdit
            Left = 229
            Top = 140
            Width = 60
            Height = 21
            DataField = 'NRSERINF'
            DataSource = DsF001
            TabOrder = 16
            BrAlignment = taRightJustify
            BrVisibleButton = False
            BrFunctionButton = TpDbConsulta
            BrDicionario = DmSrvApl.BrvDicionario
            BrQueryConsulta = 0
            BrvReadOnlyColor = 14541539
          end
          object EdtRsTitula: TBrvRetCon
            Left = 321
            Top = 59
            Width = 338
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
          object BrvDbEdit4: TBrvDbEdit
            Left = 357
            Top = 140
            Width = 60
            Height = 21
            DataField = 'NRSUSENF'
            DataSource = DsF001
            TabOrder = 17
            BrAlignment = taRightJustify
            BrVisibleButton = False
            BrFunctionButton = TpDbConsulta
            BrDicionario = DmSrvApl.BrvDicionario
            BrQueryConsulta = 0
            BrvReadOnlyColor = 14541539
          end
          object BrvDbEdit5: TBrvDbEdit
            Left = 228
            Top = 59
            Width = 87
            Height = 21
            DataField = 'CDTITULA'
            DataSource = DsF001
            TabOrder = 6
            BrAlignment = taRightJustify
            BrVisibleButton = True
            BrFunctionButton = TpDbConsulta
            BrConfigurar.Strings = (
              'CdTitula;CdTitula;S;S;'
              'CjTitula;CjTitula;S;N;'
              'EdtRsTitula;RsTitula;S;N;'
              'CdEstEmi;CdEstado;S;N;'
              'EdtCdEstEmi;CdEstado;S;N;')
            BrDicionario = DmSrvApl.BrvDicionario
            BrQueryConsulta = 19
            BrvReadOnlyColor = 14541539
          end
          object BrvDbEdit7: TBrvDbEdit
            Left = 77
            Top = 86
            Width = 87
            Height = 21
            DataField = 'CDEVENTO'
            DataSource = DsF001
            TabOrder = 9
            BrAlignment = taRightJustify
            BrVisibleButton = True
            BrFunctionButton = TpDbConsulta
            BrConfigurar.Strings = (
              'CDEVENTO;CDEVENTO;S;S;'
              'EdtDsEvento;DSEVENTO;S;N;'
              'EdtStGerDup;STGERDUP;S;N;'
              'EdtCdForPag;CdForPag;S;N;')
            BrDicionario = DmSrvApl.BrvDicionario
            BrQueryConsulta = 56
            BrvReadOnlyColor = 14541539
          end
          object EdtDsEvento: TBrvRetCon
            Left = 170
            Top = 86
            Width = 528
            Height = 19
            TabStop = False
            BevelInner = bvLowered
            BevelKind = bkFlat
            BevelWidth = 2
            BorderStyle = bsNone
            ParentColor = True
            ReadOnly = True
            TabOrder = 10
          end
          object BrvDbEdit8: TBrvDbEdit
            Left = 593
            Top = 140
            Width = 107
            Height = 21
            DataField = 'DTEMINOT'
            DataSource = DsF001
            TabOrder = 19
            BrAlignment = taRightJustify
            BrVisibleButton = True
            BrFunctionButton = TpDbData
            BrDicionario = DmSrvApl.BrvDicionario
            BrQueryConsulta = 0
            BrvReadOnlyColor = 14541539
          end
          object BrvDbEdit10: TBrvDbEdit
            Left = 756
            Top = 140
            Width = 107
            Height = 21
            DataField = 'DTENTRAD'
            DataSource = DsF001
            TabOrder = 20
            BrAlignment = taRightJustify
            BrVisibleButton = True
            BrFunctionButton = TpDbData
            BrDicionario = DmSrvApl.BrvDicionario
            BrQueryConsulta = 0
            BrvReadOnlyColor = 14541539
          end
          object BrvDbEdit41: TBrvDbEdit
            Left = 474
            Top = 140
            Width = 60
            Height = 21
            TabOrder = 18
            BrAlignment = taRightJustify
            BrVisibleButton = False
            BrFunctionButton = TpDbConsulta
            BrDicionario = DmSrvApl.BrvDicionario
            BrQueryConsulta = 0
            BrvReadOnlyColor = 14541539
          end
          object BrvDbEdit43: TBrvDbEdit
            Left = 77
            Top = 113
            Width = 87
            Height = 21
            DataField = 'NRSEQFIS'
            DataSource = DsF001
            TabOrder = 13
            BrAlignment = taRightJustify
            BrVisibleButton = True
            BrFunctionButton = TpDbConsulta
            BrConfigurar.Strings = (
              'NrSeqFis;NrSeqFis;S;S;'
              'CdFiscal;CdFiscal;S;N;'
              'EdtDsFiscal;DsFiscal;S;N;'
              'EdtCdFiscal;CdFiscal;S;N;')
            BrDicionario = DmSrvApl.BrvDicionario
            BrQueryConsulta = 57
            BrvReadOnlyColor = 14541539
          end
          object GroupBox2: TGroupBox
            Left = 704
            Top = 0
            Width = 185
            Height = 80
            Caption = 'Op'#231#245'es'
            Ctl3D = False
            ParentCtl3D = False
            TabOrder = 21
            object CbxSnItens: TCheckBox
              Left = 8
              Top = 18
              Width = 120
              Height = 17
              Caption = 'Nota com itens ?'
              Checked = True
              State = cbChecked
              TabOrder = 0
              OnClick = CbxSnItensClick
            end
            object CbxSnConhec: TCheckBox
              Left = 8
              Top = 38
              Width = 169
              Height = 17
              Caption = 'Conhecimento de Frete ?'
              TabOrder = 1
              OnClick = CbxSnConhecClick
            end
            object CbxSnCreImp: TCheckBox
              Left = 8
              Top = 58
              Width = 121
              Height = 17
              Caption = 'Creditar ICMS ?'
              TabOrder = 2
              OnClick = CbxSnItensClick
            end
          end
          object EdtStGerDup: TBrvRetCon
            Left = 678
            Top = 86
            Width = 20
            Height = 19
            TabStop = False
            BevelInner = bvLowered
            BevelKind = bkFlat
            BevelWidth = 2
            BorderStyle = bsNone
            ParentColor = True
            ReadOnly = True
            TabOrder = 12
            Visible = False
          end
          object EdtCdForPag: TBrvRetCon
            Left = 659
            Top = 86
            Width = 20
            Height = 19
            TabStop = False
            BevelInner = bvLowered
            BevelKind = bkFlat
            BevelWidth = 2
            BorderStyle = bsNone
            ParentColor = True
            ReadOnly = True
            TabOrder = 11
            Visible = False
          end
          object EdtNrChave: TBrvEdit
            Left = 321
            Top = 5
            Width = 377
            Height = 21
            TabOrder = 1
            OnExit = EdtNrChaveExit
            BrvReadOnlyColor = 14541539
            BrVisibleButton = False
            BrFunctionButton = VeConsulta
            BrAlignment = taLeftJustify
            BrvQueryCode = 0
            BrRecordar = False
          end
          object BrvDbEdit42: TBrvDbEdit
            Left = 77
            Top = 59
            Width = 145
            Height = 21
            DataField = 'CJTITULA'
            DataSource = DsF001
            TabOrder = 5
            BrAlignment = taRightJustify
            BrVisibleButton = True
            BrFunctionButton = TpDbConsulta
            BrConfigurar.Strings = (
              'CjTitula;CjTitula;S;S;'
              'CdTitula;CdTitula;S;N;'
              'EdtRsTitula;RsTitula;S;N;'
              'CdEstEmi;CdEstado;S;N;'
              'EdtCdEstEmi;CdEstado;S;N;')
            BrDicionario = DmSrvApl.BrvDicionario
            BrQueryConsulta = 19
            BrvReadOnlyColor = 14541539
          end
          object EdtCdEstEmp: TBrvRetCon
            Left = 658
            Top = 32
            Width = 40
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
          object CbxFinali: TBrvComboBox
            Left = 77
            Top = 5
            Width = 145
            Height = 21
            ItemIndex = 0
            TabOrder = 0
            Text = 'Consumo'
            Items.Strings = (
              'Consumo'
              'Insumo'
              'Ativo'
              'Outros')
            Values.Strings = (
              'C'
              'I'
              'A'
              'O')
          end
          object EdtCdEstEmi: TBrvRetCon
            Left = 658
            Top = 59
            Width = 40
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
          object EdtCdEmpEst: TBrvRetCon
            Left = 678
            Top = 32
            Width = 20
            Height = 19
            TabStop = False
            BevelInner = bvLowered
            BevelKind = bkFlat
            BevelWidth = 2
            BorderStyle = bsNone
            ParentColor = True
            ReadOnly = True
            TabOrder = 22
            Visible = False
          end
          object EdtDsFiscal: TBrvRetCon
            Left = 229
            Top = 113
            Width = 469
            Height = 19
            TabStop = False
            BevelInner = bvLowered
            BevelKind = bkFlat
            BevelWidth = 2
            BorderStyle = bsNone
            ParentColor = True
            ReadOnly = True
            TabOrder = 14
          end
          object EdtCdFiscal: TBrvRetCon
            Left = 170
            Top = 113
            Width = 53
            Height = 19
            TabStop = False
            BevelInner = bvLowered
            BevelKind = bkFlat
            BevelWidth = 2
            BorderStyle = bsNone
            ParentColor = True
            ReadOnly = True
            TabOrder = 23
          end
          object EdtCdGruEmp: TBrvRetCon
            Left = 634
            Top = 32
            Width = 25
            Height = 19
            TabStop = False
            BevelInner = bvLowered
            BevelKind = bkFlat
            BevelWidth = 2
            BorderStyle = bsNone
            ParentColor = True
            ReadOnly = True
            TabOrder = 24
            Visible = False
          end
        end
        object PgcDadosNF: TPageControl
          Left = 0
          Top = 169
          Width = 898
          Height = 256
          ActivePage = TbsImpostos
          Align = alClient
          TabOrder = 1
          object TbsItens: TTabSheet
            Caption = 'Itens da Nota'
            ImageIndex = 10
            object DbgF002: TBrvDbGrid
              Left = 0
              Top = 0
              Width = 890
              Height = 228
              BrShowMemo = True
              BrReadOnlyStyle = []
              BrReadOnlyColor = clMaroon
              Align = alClient
              BorderStyle = bsNone
              Ctl3D = False
              DataSource = DsF002
              Options = [dgEditing, dgTitles, dgIndicator, dgColLines, dgRowLines, dgTabs, dgConfirmDelete, dgCancelOnExit]
              ParentCtl3D = False
              TabOrder = 0
              TitleFont.Charset = DEFAULT_CHARSET
              TitleFont.Color = clWindowText
              TitleFont.Height = -11
              TitleFont.Name = 'Tahoma'
              TitleFont.Style = [fsBold]
              BrDicionario = DmSrvApl.BrvDicionario
              BrDrawColumn.Strings = (
                'N'#227'o remova essas duas linhas de formata'#231#227'o:'
                'CampoTabela;Operador;ValorComparativo;Cor;')
              BrGradeZebrada = False
              Columns = <
                item
                  ButtonStyle = cbsConsulta
                  Expanded = False
                  FieldName = 'CdProdut'
                  Title.Caption = 'Produto'
                  Width = 70
                  Visible = True
                  BrConsulta = 28
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
                  ReadOnly = True
                  Title.Caption = 'Descri'#231#227'o'
                  Width = 250
                  Visible = True
                  BrConsulta = 0
                  BrPermissao = []
                  BrValueHalfChecked = False
                  BrSaveOnClick = False
                end
                item
                  Expanded = False
                  FieldName = 'NRSEQFIS'
                  Title.Caption = 'C'#243'digo Fiscal'
                  Width = 76
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
                  FieldName = 'VrUnitar'
                  Title.Caption = 'Valor Unit'#225'rio'
                  Width = 80
                  Visible = True
                  BrConsulta = 0
                  BrPermissao = []
                  BrValueHalfChecked = False
                  BrSaveOnClick = False
                end
                item
                  Expanded = False
                  FieldName = 'PCDESCON'
                  Title.Caption = '% Desconto'
                  Width = 80
                  Visible = True
                  BrConsulta = 0
                  BrPermissao = []
                  BrValueHalfChecked = False
                  BrSaveOnClick = False
                end
                item
                  Expanded = False
                  FieldName = 'VrDescon'
                  Title.Caption = 'Vr. Desconto'
                  Width = 80
                  Visible = True
                  BrConsulta = 0
                  BrPermissao = []
                  BrValueHalfChecked = False
                  BrSaveOnClick = False
                end
                item
                  Expanded = False
                  FieldName = 'VRTOTAL'
                  Title.Caption = 'Valor Total'
                  Width = 80
                  Visible = True
                  BrConsulta = 0
                  BrPermissao = []
                  BrValueHalfChecked = False
                  BrSaveOnClick = False
                end
                item
                  Expanded = False
                  FieldName = 'VrIPI'
                  Title.Caption = 'Valor IPI'
                  Width = 80
                  Visible = True
                  BrConsulta = 0
                  BrPermissao = []
                  BrValueHalfChecked = False
                  BrSaveOnClick = False
                end
                item
                  Expanded = False
                  FieldName = 'BSSUBTRI'
                  Title.Caption = 'Base ST'
                  Width = 80
                  Visible = True
                  BrConsulta = 0
                  BrPermissao = []
                  BrValueHalfChecked = False
                  BrSaveOnClick = False
                end
                item
                  Expanded = False
                  FieldName = 'VRSUBTRI'
                  Title.Caption = 'Valor ST'
                  Width = 80
                  Visible = True
                  BrConsulta = 0
                  BrPermissao = []
                  BrValueHalfChecked = False
                  BrSaveOnClick = False
                end
                item
                  Expanded = False
                  FieldName = 'PCALIISS'
                  Title.Caption = '% ISSQN'
                  Width = 80
                  Visible = True
                  BrConsulta = 0
                  BrPermissao = []
                  BrValueHalfChecked = False
                  BrSaveOnClick = False
                end
                item
                  Expanded = False
                  FieldName = 'BSICMS'
                  Title.Caption = 'Base ICMS'
                  Width = 80
                  Visible = True
                  BrConsulta = 0
                  BrPermissao = []
                  BrValueHalfChecked = False
                  BrSaveOnClick = False
                end
                item
                  Expanded = False
                  FieldName = 'CDSITTRI'
                  Title.Caption = 'CTS ICMS'
                  Visible = True
                  BrConsulta = 0
                  BrPermissao = []
                  BrValueHalfChecked = False
                  BrSaveOnClick = False
                end
                item
                  Expanded = False
                  FieldName = 'PCALIICM'
                  Title.Caption = '% ICMS'
                  Visible = True
                  BrConsulta = 0
                  BrPermissao = []
                  BrValueHalfChecked = False
                  BrSaveOnClick = False
                end
                item
                  Expanded = False
                  FieldName = 'VRICMS'
                  Title.Caption = 'Valor ICMS'
                  Width = 80
                  Visible = True
                  BrConsulta = 0
                  BrPermissao = []
                  BrValueHalfChecked = False
                  BrSaveOnClick = False
                end
                item
                  Expanded = False
                  FieldName = 'CDCSTCOF'
                  Title.Caption = 'CST COFINS'
                  Width = 80
                  Visible = True
                  BrConsulta = 0
                  BrPermissao = []
                  BrValueHalfChecked = False
                  BrSaveOnClick = False
                end
                item
                  Expanded = False
                  FieldName = 'BSIMPCOF'
                  Title.Caption = 'Base COFINS'
                  Width = 80
                  Visible = True
                  BrConsulta = 0
                  BrPermissao = []
                  BrValueHalfChecked = False
                  BrSaveOnClick = False
                end
                item
                  Expanded = False
                  FieldName = 'PCALICOF'
                  Title.Caption = '% COFINS'
                  Width = 80
                  Visible = True
                  BrConsulta = 0
                  BrPermissao = []
                  BrValueHalfChecked = False
                  BrSaveOnClick = False
                end
                item
                  Expanded = False
                  FieldName = 'VRIMPCOF'
                  Title.Caption = 'Valor COFINS'
                  Width = 80
                  Visible = True
                  BrConsulta = 0
                  BrPermissao = []
                  BrValueHalfChecked = False
                  BrSaveOnClick = False
                end
                item
                  Expanded = False
                  FieldName = 'CDCSTPIS'
                  Title.Caption = 'CST PIS'
                  Width = 80
                  Visible = True
                  BrConsulta = 0
                  BrPermissao = []
                  BrValueHalfChecked = False
                  BrSaveOnClick = False
                end
                item
                  Expanded = False
                  FieldName = 'BSIMPPIS'
                  Title.Caption = 'Base PIS'
                  Width = 80
                  Visible = True
                  BrConsulta = 0
                  BrPermissao = []
                  BrValueHalfChecked = False
                  BrSaveOnClick = False
                end
                item
                  Expanded = False
                  FieldName = 'PCALIPIS'
                  Title.Caption = '% PIS'
                  Width = 80
                  Visible = True
                  BrConsulta = 0
                  BrPermissao = []
                  BrValueHalfChecked = False
                  BrSaveOnClick = False
                end
                item
                  Expanded = False
                  FieldName = 'VRIMPPIS'
                  Title.Caption = 'Valor PIS'
                  Width = 80
                  Visible = True
                  BrConsulta = 0
                  BrPermissao = []
                  BrValueHalfChecked = False
                  BrSaveOnClick = False
                end
                item
                  Expanded = False
                  FieldName = 'VRISENTA'
                  Title.Caption = 'Valor Isento'
                  Width = 80
                  Visible = True
                  BrConsulta = 0
                  BrPermissao = []
                  BrValueHalfChecked = False
                  BrSaveOnClick = False
                end
                item
                  Expanded = False
                  FieldName = 'VROUTRA'
                  Title.Caption = 'Valor Outras'
                  Width = 80
                  Visible = True
                  BrConsulta = 0
                  BrPermissao = []
                  BrValueHalfChecked = False
                  BrSaveOnClick = False
                end
                item
                  Expanded = False
                  FieldName = 'TPFINENT'
                  Title.Caption = 'Finalidade'
                  Visible = True
                  BrConsulta = 0
                  BrPermissao = []
                  BrValueHalfChecked = False
                  BrSaveOnClick = False
                end>
            end
          end
          object TbsImpostos: TTabSheet
            Caption = 'Impostos\Outros'
            ImageIndex = 2
            object GroupBox3: TGroupBox
              Left = 3
              Top = 0
              Width = 430
              Height = 50
              Caption = 'ICMS'
              Ctl3D = True
              ParentCtl3D = False
              TabOrder = 0
              object BrvLabel12: TBrvLabel
                Left = 9
                Top = 17
                Width = 47
                Height = 26
                Caption = 'Base de C'#225'lculo'
                WordWrap = True
              end
              object BrvLabel13: TBrvLabel
                Left = 151
                Top = 25
                Width = 45
                Height = 13
                Caption = '% ICMS'
              end
              object BrvLabel14: TBrvLabel
                Left = 295
                Top = 25
                Width = 29
                Height = 13
                Caption = 'Valor'
              end
              object BrvDbEdit11: TBrvDbEdit
                Left = 58
                Top = 21
                Width = 87
                Height = 21
                DataField = 'BSICMS'
                DataSource = DsF001
                TabOrder = 0
                BrAlignment = taRightJustify
                BrVisibleButton = False
                BrFunctionButton = TpDbConsulta
                BrQueryConsulta = 0
                BrvReadOnlyColor = 14541539
              end
              object BrvDbEdit12: TBrvDbEdit
                Left = 202
                Top = 21
                Width = 87
                Height = 21
                DataField = 'PCALIICM'
                DataSource = DsF001
                TabOrder = 1
                BrAlignment = taRightJustify
                BrVisibleButton = False
                BrFunctionButton = TpDbConsulta
                BrQueryConsulta = 0
                BrvReadOnlyColor = 14541539
              end
              object BrvDbEdit13: TBrvDbEdit
                Left = 340
                Top = 21
                Width = 87
                Height = 21
                DataField = 'VRICMS'
                DataSource = DsF001
                TabOrder = 2
                BrAlignment = taRightJustify
                BrVisibleButton = False
                BrFunctionButton = TpDbConsulta
                BrQueryConsulta = 0
                BrvReadOnlyColor = 14541539
              end
            end
            object GroupBox4: TGroupBox
              Left = 439
              Top = 0
              Width = 291
              Height = 50
              Caption = 'S&ubstitui'#231#227'o tribut'#225'ria'
              Ctl3D = True
              ParentCtl3D = False
              TabOrder = 1
              object BrvLabel15: TBrvLabel
                Left = 6
                Top = 17
                Width = 47
                Height = 26
                Caption = 'Base de C'#225'lculo'
                WordWrap = True
              end
              object BrvLabel16: TBrvLabel
                Left = 166
                Top = 25
                Width = 29
                Height = 13
                Caption = 'Valor'
              end
              object BrvDbEdit14: TBrvDbEdit
                Left = 73
                Top = 21
                Width = 87
                Height = 21
                DataField = 'BSSUBTRI'
                DataSource = DsF001
                TabOrder = 0
                BrAlignment = taRightJustify
                BrVisibleButton = False
                BrFunctionButton = TpDbConsulta
                BrQueryConsulta = 0
                BrvReadOnlyColor = 14541539
              end
              object BrvDbEdit15: TBrvDbEdit
                Left = 201
                Top = 21
                Width = 87
                Height = 21
                DataField = 'VRSUBTRI'
                DataSource = DsF001
                TabOrder = 1
                BrAlignment = taRightJustify
                BrVisibleButton = False
                BrFunctionButton = TpDbConsulta
                BrQueryConsulta = 0
                BrvReadOnlyColor = 14541539
              end
            end
            object GroupBox5: TGroupBox
              Left = 736
              Top = 0
              Width = 138
              Height = 50
              Caption = 'IPI'
              Ctl3D = True
              ParentCtl3D = False
              TabOrder = 2
              object BrvLabel18: TBrvLabel
                Left = 6
                Top = 25
                Width = 29
                Height = 13
                Caption = 'Valor'
              end
              object BrvDbEdit17: TBrvDbEdit
                Left = 41
                Top = 21
                Width = 87
                Height = 21
                DataField = 'VRIPI'
                DataSource = DsF001
                TabOrder = 0
                BrAlignment = taRightJustify
                BrVisibleButton = False
                BrFunctionButton = TpDbConsulta
                BrQueryConsulta = 0
                BrvReadOnlyColor = 14541539
              end
            end
            object GroupBox6: TGroupBox
              Left = 3
              Top = 55
              Width = 871
              Height = 50
              Caption = 'Outras'
              Ctl3D = True
              ParentCtl3D = False
              TabOrder = 3
              object BrvLabel17: TBrvLabel
                Left = 6
                Top = 25
                Width = 40
                Height = 13
                Caption = 'Seguro'
              end
              object BrvLabel20: TBrvLabel
                Left = 151
                Top = 25
                Width = 43
                Height = 13
                Caption = 'Isentas'
              end
              object BrvLabel21: TBrvLabel
                Left = 295
                Top = 25
                Width = 38
                Height = 13
                Caption = 'Outras'
              end
              object BrvLabel19: TBrvLabel
                Left = 442
                Top = 17
                Width = 61
                Height = 26
                Caption = 'Despesas Acess'#243'rias '
                WordWrap = True
              end
              object BrvDbEdit16: TBrvDbEdit
                Left = 58
                Top = 21
                Width = 87
                Height = 21
                DataField = 'VRSEGURO'
                DataSource = DsF001
                TabOrder = 0
                BrAlignment = taRightJustify
                BrVisibleButton = False
                BrFunctionButton = TpDbConsulta
                BrQueryConsulta = 0
                BrvReadOnlyColor = 14541539
              end
              object BrvDbEdit19: TBrvDbEdit
                Left = 202
                Top = 21
                Width = 87
                Height = 21
                DataField = 'VRISENOT'
                DataSource = DsF001
                TabOrder = 1
                BrAlignment = taRightJustify
                BrVisibleButton = False
                BrFunctionButton = TpDbConsulta
                BrQueryConsulta = 0
                BrvReadOnlyColor = 14541539
              end
              object BrvDbEdit20: TBrvDbEdit
                Left = 339
                Top = 21
                Width = 87
                Height = 21
                DataField = 'VROUTNOT'
                DataSource = DsF001
                TabOrder = 2
                BrAlignment = taRightJustify
                BrVisibleButton = False
                BrFunctionButton = TpDbConsulta
                BrQueryConsulta = 0
                BrvReadOnlyColor = 14541539
              end
              object DBCheckBox1: TDBCheckBox
                Left = 608
                Top = 25
                Width = 220
                Height = 17
                Caption = 'Despesas Acess'#243'rias incide ICMS ?'
                DataField = 'SNDESPIC'
                DataSource = DsF001
                TabOrder = 3
                ValueChecked = 'True'
                ValueUnchecked = 'False'
              end
              object BrvDbEdit18: TBrvDbEdit
                Left = 508
                Top = 21
                Width = 87
                Height = 21
                DataField = 'VRDESACE'
                DataSource = DsF001
                TabOrder = 4
                BrAlignment = taRightJustify
                BrVisibleButton = False
                BrFunctionButton = TpDbConsulta
                BrQueryConsulta = 0
                BrvReadOnlyColor = 14541539
              end
            end
            object GrbTransp: TGroupBox
              Left = 3
              Top = 111
              Width = 871
              Height = 114
              Caption = 'Transportador'
              Ctl3D = True
              ParentCtl3D = False
              TabOrder = 4
              object BrvLabel22: TBrvLabel
                Left = 6
                Top = 25
                Width = 81
                Height = 13
                Caption = 'Tipo de Frente'
                Font.Charset = DEFAULT_CHARSET
                Font.Color = clBlue
                Font.Height = -11
                Font.Name = 'Tahoma'
                Font.Style = [fsBold]
                ParentFont = False
              end
              object BrvLabel9: TBrvLabel
                Left = 6
                Top = 52
                Width = 82
                Height = 13
                Caption = 'Transportador'
                Font.Charset = DEFAULT_CHARSET
                Font.Color = clBlack
                Font.Height = -11
                Font.Name = 'Tahoma'
                Font.Style = [fsBold]
                ParentFont = False
              end
              object BrvLabel23: TBrvLabel
                Left = 6
                Top = 79
                Width = 62
                Height = 13
                Caption = 'Valor Frete'
              end
              object BrvDBComboListBox1: TBrvDBComboListBox
                Left = 99
                Top = 21
                Width = 246
                Height = 21
                DataField = 'TPFRETE'
                DataSource = DsF001
                Items.Strings = (
                  'CIF - Por conta do fornecedor'
                  'FOB - Por conta da empresa'
                  'NOTA - Destacado na nota')
                TabOrder = 0
                Values.Strings = (
                  'C'
                  'F'
                  'N')
              end
              object DBCheckBox2: TDBCheckBox
                Left = 351
                Top = 25
                Width = 121
                Height = 17
                Caption = 'Frete incide IPI ?'
                DataField = 'SNFREIPI'
                DataSource = DsF001
                TabOrder = 1
                ValueChecked = 'True'
                ValueUnchecked = 'False'
              end
              object DBCheckBox3: TDBCheckBox
                Left = 478
                Top = 25
                Width = 160
                Height = 17
                Caption = 'Frete incide base ICMS?'
                DataField = 'SNFREICM'
                DataSource = DsF001
                TabOrder = 2
                ValueChecked = 'True'
                ValueUnchecked = 'False'
              end
              object BrvDbEdit9: TBrvDbEdit
                Left = 99
                Top = 48
                Width = 86
                Height = 21
                DataField = 'CDTRANSP'
                DataSource = DsF001
                TabOrder = 3
                BrAlignment = taRightJustify
                BrVisibleButton = True
                BrFunctionButton = TpDbConsulta
                BrConfigurar.Strings = (
                  'CdTransp;CdTitula;S;S;'
                  'EdtRsTransp;RsTitula;S;N;')
                BrDicionario = DmSrvApl.BrvDicionario
                BrQueryConsulta = 50
                BrvReadOnlyColor = 14541539
              end
              object EdtRsTransp: TBrvRetCon
                Left = 192
                Top = 48
                Width = 506
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
              object BrvDbEdit21: TBrvDbEdit
                Left = 99
                Top = 75
                Width = 86
                Height = 21
                DataField = 'VRFRETE'
                DataSource = DsF001
                TabOrder = 5
                BrAlignment = taRightJustify
                BrVisibleButton = False
                BrFunctionButton = TpDbConsulta
                BrQueryConsulta = 0
                BrvReadOnlyColor = 14541539
              end
            end
          end
          object TbsRetencao: TTabSheet
            Caption = 'Reten'#231#227'o'
            ImageIndex = 6
            object GroupBox8: TGroupBox
              Left = 207
              Top = 0
              Width = 198
              Height = 105
              Caption = 'PIS'
              Ctl3D = True
              ParentCtl3D = False
              TabOrder = 1
              object BrvLabel30: TBrvLabel
                Left = 6
                Top = 25
                Width = 87
                Height = 13
                Caption = 'Base de C'#225'lculo'
              end
              object BrvLabel32: TBrvLabel
                Left = 6
                Top = 52
                Width = 47
                Height = 13
                Caption = 'Al'#237'quota'
              end
              object BrvLabel35: TBrvLabel
                Left = 6
                Top = 79
                Width = 29
                Height = 13
                Caption = 'Valor'
              end
              object BrvDbEdit28: TBrvDbEdit
                Left = 98
                Top = 75
                Width = 87
                Height = 21
                DataField = 'VRPIS'
                DataSource = DsF001
                TabOrder = 2
                BrAlignment = taRightJustify
                BrVisibleButton = False
                BrFunctionButton = TpDbConsulta
                BrQueryConsulta = 0
                BrvReadOnlyColor = 14541539
              end
              object BrvDbEdit27: TBrvDbEdit
                Left = 98
                Top = 48
                Width = 87
                Height = 21
                DataField = 'PCPIS'
                DataSource = DsF001
                TabOrder = 1
                OnExit = BrvDbEdit27Exit
                BrAlignment = taRightJustify
                BrVisibleButton = False
                BrFunctionButton = TpDbConsulta
                BrQueryConsulta = 0
                BrvReadOnlyColor = 14541539
              end
              object BrvDbEdit26: TBrvDbEdit
                Left = 98
                Top = 21
                Width = 87
                Height = 21
                DataField = 'BSPIS'
                DataSource = DsF001
                TabOrder = 0
                BrAlignment = taRightJustify
                BrVisibleButton = False
                BrFunctionButton = TpDbConsulta
                BrQueryConsulta = 0
                BrvReadOnlyColor = 14541539
              end
            end
            object GroupBox9: TGroupBox
              Left = 411
              Top = 0
              Width = 198
              Height = 105
              Caption = 'COFINS'
              Ctl3D = True
              ParentCtl3D = False
              TabOrder = 2
              object BrvLabel33: TBrvLabel
                Left = 6
                Top = 25
                Width = 87
                Height = 13
                Caption = 'Base de C'#225'lculo'
              end
              object BrvLabel36: TBrvLabel
                Left = 6
                Top = 52
                Width = 47
                Height = 13
                Caption = 'Al'#237'quota'
              end
              object BrvLabel37: TBrvLabel
                Left = 6
                Top = 79
                Width = 29
                Height = 13
                Caption = 'Valor'
              end
              object BrvDbEdit29: TBrvDbEdit
                Left = 98
                Top = 21
                Width = 87
                Height = 21
                DataField = 'BSCOFINS'
                DataSource = DsF001
                TabOrder = 0
                BrAlignment = taRightJustify
                BrVisibleButton = False
                BrFunctionButton = TpDbConsulta
                BrQueryConsulta = 0
                BrvReadOnlyColor = 14541539
              end
              object BrvDbEdit30: TBrvDbEdit
                Left = 98
                Top = 48
                Width = 87
                Height = 21
                DataField = 'PCCOFINS'
                DataSource = DsF001
                TabOrder = 1
                OnExit = BrvDbEdit30Exit
                BrAlignment = taRightJustify
                BrVisibleButton = False
                BrFunctionButton = TpDbConsulta
                BrQueryConsulta = 0
                BrvReadOnlyColor = 14541539
              end
              object BrvDbEdit31: TBrvDbEdit
                Left = 98
                Top = 75
                Width = 87
                Height = 21
                DataField = 'VRCOFINS'
                DataSource = DsF001
                TabOrder = 2
                BrAlignment = taRightJustify
                BrVisibleButton = False
                BrFunctionButton = TpDbConsulta
                BrQueryConsulta = 0
                BrvReadOnlyColor = 14541539
              end
            end
            object GroupBox10: TGroupBox
              Left = 615
              Top = 0
              Width = 198
              Height = 105
              Caption = 'CSLL'
              Ctl3D = True
              ParentCtl3D = False
              TabOrder = 3
              object BrvLabel31: TBrvLabel
                Left = 6
                Top = 25
                Width = 87
                Height = 13
                Caption = 'Base de C'#225'lculo'
              end
              object BrvLabel34: TBrvLabel
                Left = 6
                Top = 52
                Width = 47
                Height = 13
                Caption = 'Al'#237'quota'
              end
              object BrvLabel38: TBrvLabel
                Left = 6
                Top = 79
                Width = 29
                Height = 13
                Caption = 'Valor'
              end
              object BrvDbEdit32: TBrvDbEdit
                Left = 98
                Top = 21
                Width = 87
                Height = 21
                DataField = 'BSCSLL'
                DataSource = DsF001
                TabOrder = 0
                BrAlignment = taRightJustify
                BrVisibleButton = False
                BrFunctionButton = TpDbConsulta
                BrQueryConsulta = 0
                BrvReadOnlyColor = 14541539
              end
              object BrvDbEdit33: TBrvDbEdit
                Left = 98
                Top = 48
                Width = 87
                Height = 21
                DataField = 'PCCSLL'
                DataSource = DsF001
                TabOrder = 1
                OnExit = BrvDbEdit33Exit
                BrAlignment = taRightJustify
                BrVisibleButton = False
                BrFunctionButton = TpDbConsulta
                BrQueryConsulta = 0
                BrvReadOnlyColor = 14541539
              end
              object BrvDbEdit34: TBrvDbEdit
                Left = 98
                Top = 75
                Width = 87
                Height = 21
                DataField = 'VRCSLL'
                DataSource = DsF001
                TabOrder = 2
                BrAlignment = taRightJustify
                BrVisibleButton = False
                BrFunctionButton = TpDbConsulta
                BrQueryConsulta = 0
                BrvReadOnlyColor = 14541539
              end
            end
            object GroupBox11: TGroupBox
              Left = 207
              Top = 111
              Width = 198
              Height = 105
              Caption = 'IRRF'
              Ctl3D = True
              ParentCtl3D = False
              TabOrder = 4
              object BrvLabel39: TBrvLabel
                Left = 6
                Top = 25
                Width = 87
                Height = 13
                Caption = 'Base de C'#225'lculo'
              end
              object BrvLabel40: TBrvLabel
                Left = 6
                Top = 52
                Width = 47
                Height = 13
                Caption = 'Al'#237'quota'
              end
              object BrvLabel41: TBrvLabel
                Left = 6
                Top = 79
                Width = 29
                Height = 13
                Caption = 'Valor'
              end
              object BrvDbEdit35: TBrvDbEdit
                Left = 99
                Top = 21
                Width = 87
                Height = 21
                DataField = 'BSIRRF'
                DataSource = DsF001
                TabOrder = 0
                BrAlignment = taRightJustify
                BrVisibleButton = False
                BrFunctionButton = TpDbConsulta
                BrQueryConsulta = 0
                BrvReadOnlyColor = 14541539
              end
              object BrvDbEdit37: TBrvDbEdit
                Left = 99
                Top = 48
                Width = 87
                Height = 21
                DataField = 'PCIRRF'
                DataSource = DsF001
                TabOrder = 1
                OnExit = BrvDbEdit37Exit
                BrAlignment = taRightJustify
                BrVisibleButton = False
                BrFunctionButton = TpDbConsulta
                BrQueryConsulta = 0
                BrvReadOnlyColor = 14541539
              end
              object BrvDbEdit36: TBrvDbEdit
                Left = 99
                Top = 75
                Width = 87
                Height = 21
                DataField = 'VRIRRF'
                DataSource = DsF001
                TabOrder = 2
                BrAlignment = taRightJustify
                BrVisibleButton = False
                BrFunctionButton = TpDbConsulta
                BrQueryConsulta = 0
                BrvReadOnlyColor = 14541539
              end
            end
            object GroupBox12: TGroupBox
              Left = 615
              Top = 111
              Width = 198
              Height = 105
              Caption = 'INSS'
              Ctl3D = True
              ParentCtl3D = False
              TabOrder = 6
              object BrvLabel42: TBrvLabel
                Left = 6
                Top = 25
                Width = 87
                Height = 13
                Caption = 'Base de C'#225'lculo'
              end
              object BrvLabel43: TBrvLabel
                Left = 6
                Top = 52
                Width = 47
                Height = 13
                Caption = 'Al'#237'quota'
              end
              object BrvLabel44: TBrvLabel
                Left = 6
                Top = 79
                Width = 29
                Height = 13
                Caption = 'Valor'
              end
              object BrvDbEdit38: TBrvDbEdit
                Left = 98
                Top = 21
                Width = 87
                Height = 21
                DataField = 'BSINSS'
                DataSource = DsF001
                TabOrder = 0
                BrAlignment = taRightJustify
                BrVisibleButton = False
                BrFunctionButton = TpDbConsulta
                BrQueryConsulta = 0
                BrvReadOnlyColor = 14541539
              end
              object BrvDbEdit39: TBrvDbEdit
                Left = 99
                Top = 48
                Width = 87
                Height = 21
                DataField = 'PCALIINS'
                DataSource = DsF001
                TabOrder = 1
                OnExit = BrvDbEdit39Exit
                BrAlignment = taRightJustify
                BrVisibleButton = False
                BrFunctionButton = TpDbConsulta
                BrQueryConsulta = 0
                BrvReadOnlyColor = 14541539
              end
              object BrvDbEdit40: TBrvDbEdit
                Left = 99
                Top = 75
                Width = 87
                Height = 21
                DataField = 'VRINSS'
                DataSource = DsF001
                TabOrder = 2
                BrAlignment = taRightJustify
                BrVisibleButton = False
                BrFunctionButton = TpDbConsulta
                BrQueryConsulta = 0
                BrvReadOnlyColor = 14541539
              end
            end
            object GroupBox13: TGroupBox
              Left = 411
              Top = 111
              Width = 198
              Height = 105
              Caption = 'ISSQN'
              Ctl3D = True
              ParentCtl3D = False
              TabOrder = 5
              object BrvLabel45: TBrvLabel
                Left = 6
                Top = 25
                Width = 87
                Height = 13
                Caption = 'Base de C'#225'lculo'
              end
              object BrvLabel46: TBrvLabel
                Left = 6
                Top = 52
                Width = 47
                Height = 13
                Caption = 'Al'#237'quota'
              end
              object BrvLabel47: TBrvLabel
                Left = 6
                Top = 79
                Width = 29
                Height = 13
                Caption = 'Valor'
              end
              object BrvDbEdit22: TBrvDbEdit
                Left = 99
                Top = 21
                Width = 87
                Height = 21
                DataField = 'BSISSQN'
                DataSource = DsF001
                TabOrder = 0
                BrAlignment = taRightJustify
                BrVisibleButton = False
                BrFunctionButton = TpDbConsulta
                BrQueryConsulta = 0
                BrvReadOnlyColor = 14541539
              end
              object BrvDbEdit23: TBrvDbEdit
                Left = 99
                Top = 48
                Width = 87
                Height = 21
                DataField = 'PCALIISS'
                DataSource = DsF001
                TabOrder = 1
                OnExit = BrvDbEdit23Exit
                BrAlignment = taRightJustify
                BrVisibleButton = False
                BrFunctionButton = TpDbConsulta
                BrQueryConsulta = 0
                BrvReadOnlyColor = 14541539
              end
              object BrvDbEdit24: TBrvDbEdit
                Left = 99
                Top = 75
                Width = 87
                Height = 21
                DataField = 'VRISSQST'
                DataSource = DsF001
                TabOrder = 2
                BrAlignment = taRightJustify
                BrVisibleButton = False
                BrFunctionButton = TpDbConsulta
                BrQueryConsulta = 0
                BrvReadOnlyColor = 14541539
              end
            end
            object GroupBox14: TGroupBox
              Left = 3
              Top = 0
              Width = 198
              Height = 105
              Caption = 'Impostos Retidos - Calcular'
              Ctl3D = True
              ParentCtl3D = False
              TabOrder = 0
              object CbxPIS: TCheckBox
                Left = 6
                Top = 25
                Width = 67
                Height = 17
                Caption = 'PIS'
                TabOrder = 0
                OnClick = CbxPISClick
              end
              object CbxCOFINS: TCheckBox
                Left = 6
                Top = 52
                Width = 67
                Height = 17
                Caption = 'COFINS'
                TabOrder = 1
                OnClick = CbxCOFINSClick
              end
              object CbxCSLL: TCheckBox
                Left = 6
                Top = 79
                Width = 67
                Height = 17
                Caption = 'CSLL'
                TabOrder = 2
                OnClick = CbxCSLLClick
              end
              object CbxIRRF: TCheckBox
                Left = 88
                Top = 24
                Width = 67
                Height = 17
                Caption = 'IRRF'
                TabOrder = 3
                OnClick = CbxIRRFClick
              end
              object CbxISSQN: TCheckBox
                Left = 88
                Top = 52
                Width = 67
                Height = 17
                Caption = 'ISSQN'
                TabOrder = 4
                OnClick = CbxISSQNClick
              end
              object CbxINSS: TCheckBox
                Left = 88
                Top = 79
                Width = 67
                Height = 17
                Caption = 'INSS'
                TabOrder = 5
                OnClick = CbxINSSClick
              end
            end
          end
          object TbsContabil: TTabSheet
            Caption = 'Cont'#225'bil'
            ImageIndex = 8
            object DbgB004: TBrvDbGrid
              Left = 0
              Top = 0
              Width = 890
              Height = 128
              BrShowMemo = True
              BrReadOnlyStyle = [fsBold]
              BrReadOnlyColor = clMaroon
              Align = alClient
              DataSource = DsB004
              TabOrder = 0
              TitleFont.Charset = DEFAULT_CHARSET
              TitleFont.Color = clWindowText
              TitleFont.Height = -11
              TitleFont.Name = 'Tahoma'
              TitleFont.Style = [fsBold]
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
                  FieldName = 'NrConCre'
                  ReadOnly = True
                  Title.Caption = 'Conta Cr'#233'dito'
                  Width = 80
                  Visible = True
                  BrOnBeforeConsul = DbgF014Columns2BrOnBeforeConsul
                  BrOnAfterConsul = DbgF014Columns2BrOnAfterConsul
                  BrConsulta = 53
                  BrConfigurar.Strings = (
                    'NrConCre;NRCONTA;S;S;'
                    'NmConCre;NMCONTA;S;N;'
                    'NrClaCre;NRCLASSI;S;N;')
                  BrPermissao = []
                  BrValueHalfChecked = False
                  BrSaveOnClick = False
                end
                item
                  Expanded = False
                  FieldName = 'NmConCre'
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
                  FieldName = 'NrConDeb'
                  Title.Caption = 'Conta D'#233'bito'
                  Width = 80
                  Visible = True
                  BrOnBeforeConsul = DbgB004Columns4BrOnBeforeConsul
                  BrOnAfterConsul = DbgB004Columns4BrOnAfterConsul
                  BrConsulta = 53
                  BrConfigurar.Strings = (
                    'NrConDeb;NRCONTA;S;S;'
                    'NmConDeb;NMCONTA;S;N;'
                    'NrClaDeb;NRCLASSI;S;N;')
                  BrPermissao = []
                  BrValueHalfChecked = False
                  BrSaveOnClick = False
                end
                item
                  Expanded = False
                  FieldName = 'NmConDeb'
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
                  FieldName = 'vrlancto'
                  Title.Caption = 'Valor'
                  Width = 80
                  Visible = True
                  BrConsulta = 0
                  BrPermissao = []
                  BrValueHalfChecked = False
                  BrSaveOnClick = False
                end
                item
                  Expanded = False
                  FieldName = 'NrLancto'
                  Title.Caption = 'Lan'#231'amento'
                  Visible = False
                  BrConsulta = 0
                  BrPermissao = []
                  BrValueHalfChecked = False
                  BrSaveOnClick = False
                end>
            end
            object DbgB008: TBrvDbGrid
              Left = 0
              Top = 128
              Width = 890
              Height = 100
              BrShowMemo = True
              BrReadOnlyStyle = [fsBold]
              BrReadOnlyColor = clMaroon
              Align = alBottom
              DataSource = DsB008
              TabOrder = 1
              TitleFont.Charset = DEFAULT_CHARSET
              TitleFont.Color = clWindowText
              TitleFont.Height = -11
              TitleFont.Name = 'Tahoma'
              TitleFont.Style = [fsBold]
              BrDicionario = DmSrvApl.BrvDicionario
              BrDrawColumn.Strings = (
                'N'#227'o remova essas duas linhas de formata'#231#227'o:'
                'CampoTabela;Operador;ValorComparativo;Cor;')
              BrGradeZebrada = False
              Columns = <
                item
                  Expanded = False
                  FieldName = 'NrLancto'
                  Title.Caption = 'Lan'#231'amento'
                  Visible = False
                  BrConsulta = 0
                  BrPermissao = []
                  BrValueHalfChecked = False
                  BrSaveOnClick = False
                end
                item
                  ButtonStyle = cbsConsulta
                  Expanded = False
                  FieldName = 'CdCenCus'
                  Title.Caption = 'Centro de Custo'
                  Width = 100
                  Visible = True
                  BrConsulta = 35
                  BrConfigurar.Strings = (
                    'CdCenCus;CDCENCUS;S;S;'
                    'DsCenCus;DSCENCUS;S;N;')
                  BrPermissao = []
                  BrValueHalfChecked = False
                  BrSaveOnClick = False
                end
                item
                  Expanded = False
                  FieldName = 'DsCenCus'
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
          object TbsParcela: TTabSheet
            Caption = 'Parcelas'
            ImageIndex = 9
            object DbgN003: TBrvDbGrid
              Left = 0
              Top = 0
              Width = 890
              Height = 194
              BrShowMemo = True
              BrReadOnlyStyle = [fsItalic]
              BrReadOnlyColor = clMaroon
              Align = alClient
              DataSource = DsN003
              TabOrder = 0
              TitleFont.Charset = DEFAULT_CHARSET
              TitleFont.Color = clWindowText
              TitleFont.Height = -11
              TitleFont.Name = 'Tahoma'
              TitleFont.Style = [fsBold]
              BrDicionario = DmSrvApl.BrvDicionario
              BrDrawColumn.Strings = (
                'N'#227'o remova essas duas linhas de formata'#231#227'o:'
                'CampoTabela;Operador;ValorComparativo;Cor;')
              BrGradeZebrada = False
              Columns = <
                item
                  Expanded = False
                  FieldName = 'NrOrdem'
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
                  Title.Caption = 'Valor'
                  Width = 80
                  Visible = True
                  BrConsulta = 0
                  BrPermissao = []
                  BrValueHalfChecked = False
                  BrSaveOnClick = False
                end
                item
                  Expanded = False
                  FieldName = 'CdBarra'
                  Title.Caption = 'C'#243'digo de Barras'
                  Width = 350
                  Visible = True
                  BrConsulta = 0
                  BrPermissao = []
                  BrValueHalfChecked = False
                  BrSaveOnClick = False
                end>
            end
            object Panel2: TPanel
              Left = 0
              Top = 194
              Width = 890
              Height = 34
              Align = alBottom
              BevelOuter = bvNone
              TabOrder = 1
              object BtnGerParcel: TBrvBitBtn
                Left = 5
                Top = 5
                Width = 130
                Height = 25
                Caption = 'Gerar Parcelas'
                DoubleBuffered = True
                Font.Charset = DEFAULT_CHARSET
                Font.Color = clWindowText
                Font.Height = -11
                Font.Name = 'Tahoma'
                Font.Style = [fsBold]
                Glyph.Data = {
                  E6040000424DE604000000000000360000002800000014000000140000000100
                  180000000000B0040000C40E0000C40E00000000000000000000008080008080
                  0080800080800080800080800080800080800080800080800080800080800080
                  8000808000808000808000808000808000808000808000808000808000808000
                  8080917C47837846837947847A49837846857B49837846837947847A49837846
                  8A7B47577E5D008080008080008080008080008080008080008080008080BA7B
                  37C6A475C19C6BB68C54C6A375B2864DC5A273C19C6BB68C54C6A375BC8A4F70
                  7E55008080008080008080008080008080008080008080008080B87F3ED2B996
                  CAAF87B99766D2B996B48F5AD0B793CAAF87B99766D2B996C0945F70815A0080
                  80008080008080008080008080008080008080008080BD8547CBB089C5A87DB9
                  9764CBB089B4905CCAAF87C5A87DB99764CBB089C0956072845F008080008080
                  008080008080008080008080008080008080C38D51D0B892CAB086BF9E6ED0B8
                  92BA9765CFB690CAB086BF9E6ED0B892C69C6A76886600808000808000808000
                  8080008080008080008080008080C69055DBC6A7D3BC98C4A579DBC6A7BF9D6E
                  D9C4A4D3BC98C4A579DBC6A7CBA474788B680080800080800080800080800080
                  80008080008080008080C9955BCDB28AC9AC81C09F70CDB28ABD9B6ACCB188C9
                  AC81C09F70CDB28AC8A06D798D6B008080008080008080008080008080008080
                  008080008080CA955CE4D7BEDDCAADCBAF87E4D7BEC6A67AE3D4BBDDCAADCBAF
                  87E4D7BED2AD817A8E6C00808000808000808000808000808000808000808000
                  8080CE9C64C8AC80C6A87BC1A070C8AC80BF9E6CC8AB7FC6A87BC1A070C8AC80
                  CAA26F7C9170008080008080008080008080008080008080008080008080CF9A
                  62EFE5D5E6D7C0D2B893EFE5D4CBAD84EDE3D1E6D7C0D2B893EFE5D4D8B58D7D
                  916F008080008080008080008080008080008080008080008080D4A26CCAAD82
                  C8AA7DC5A475CAAD82C3A273CAAC81C8AA7DC5A475CAAD82CEA5747F95750080
                  80008080008080008080008080008080008080008080D6A672BE9A67BE9A67BF
                  9B68BE9A67BF9B68BE9A67BE9A67BF9B68BE9A67CBA16E819778008080008080
                  008080008080008080008080008080008080D5A16AF4EDDEEFE6D3EFE6D3EFE6
                  D3EFE6D3EFE6D3EFE6D3EFE6D3F2EADADEBD9680957500808000808000808000
                  8080008080008080008080008080D5A26BFAF7ECF4EEDFF4EEDFF4EEDFF4EEDF
                  F4EEDFF4EEDFF4EEDFF8F3E7E1C29B8196760080800080800080800080800080
                  80008080008080008080D7A46DFEFCF6F8F3E8F8F3E8F8F3E8F8F3E8F8F3E8F8
                  F3E8F8F3E8FCF9F1E3C4A1829778008080008080008080008080008080008080
                  008080008080D8A56EFFFFFFFDFBF5FDFBF5FDFBF5FDFBF5FDFBF5FDFBF5FDFB
                  F5FFFFFEE6C9A683987800808000808000808000808000808000808000808000
                  8080E2AB76EDD8BFEAD3B8EAD3B8EAD3B8EAD3B8EAD3B8EAD3B8EAD3B8ECD6BC
                  E4BB92899B7C008080008080008080008080008080008080008080008080B4A5
                  7EA29E77A29E77A29E77A29E77A29E77A29E77A29E77A29E77A29E77ABA27B6C
                  967F008080008080008080008080008080008080008080008080008080008080
                  0080800080800080800080800080800080800080800080800080800080800080
                  80008080008080008080}
                ParentDoubleBuffered = False
                ParentFont = False
                ParentShowHint = False
                ShowHint = True
                TabOrder = 0
                OnClick = BtnGerParcelClick
                BrTipoBotao = BrBtnCalcul
              end
              object BtnCancelParcel: TBrvBitBtn
                Left = 141
                Top = 5
                Width = 130
                Height = 25
                Hint = 'Fechar'
                Caption = 'Cancelar Parcelas'
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
                  008080008080008080008080008080008080008080008080065FA90B40D10B43
                  CE0B43CE0B43CE0B43CE0B43CE0B43CE0B43CE0B43CE0B43CE0B43CE0B43CE0B
                  43CE0B40D1065FA9008080008080008080008080347C7C667979627A7A617979
                  617979617979617979627A7A627A7A617979617979627A7A627A7A617979677A
                  7A357D7D008080008080008080065FA90F2AED0E32E20E32E20E32E20E32E20E
                  32E20E32E20E32E20E32E20E32E20E32E20E32E20E32E20E32E20E32E20F2AED
                  065FA9008080008080347C7C8876767C78787C78787C78787B77777B77777B77
                  777B77777C78787C78787C78787B77777B77777B77777B7777887676347C7C00
                  80800080800C41D30F33E40E36E10E36E10E36E10E36E10E36E10E36E10E36E1
                  0E36E10E36E10E36E10E36E10E36E10E36E10E36E10F33E40C41D30080800080
                  80677A7A7C78787979797A7A7A79797979797979797979797979797979797978
                  78787979797979797878787878787878787C7878677A7A0080800080800C45D0
                  1036E40F38E10F38E10F38E10F38E10F38E10F38E10F38E10F38E10F38E10F38
                  E10F38E10F38E10F38E10F38E11036E40C45D0008080008080637B7B7E7A7A79
                  79797A7A7A7A7A7A7979797979797A7A7A7979797979797A7A7A7A7A7A797979
                  7979797A7A7A7979797D7979637B7B0080800080800D47D01038E6103AE3103A
                  E3103AE30C37E2002DE10834E20F39E30F3AE30A35E2002DE10B36E2103AE310
                  3AE3103AE31038E60D47D0008080008080637B7B7F7B7B7B7B7B7B7B7B7B7B7B
                  7979797373737777777B7B7B7B7B7B7878787474747979797B7B7B7B7B7B7B7B
                  7B7F7B7B637B7B0080800080800F49D2123AE7113CE4113CE40E39E42048E6A4
                  B4F64466EB0532E30633E33459E9A9B8F72B52E70D38E4113CE4113CE4123AE7
                  0F49D2008080008080657D7D807C7C7C7C7C7C7C7C7B7B7B828282C6C6C69191
                  917676767777778A8A8AC9C9C98787877A7A7A7C7C7C7C7C7C807C7C657D7D00
                  80800080800F4BD2133CE8123EE5123EE50634E4819AF1FFFFFFF9F9FF5072EC
                  395FE9F2F4FFFFFFFFA3B4F60332E3123EE5123EE5133CE80F4BD20080800080
                  80667E7E817D7D7D7D7D7D7D7D787878B1B1B1FFFFFFF8F8F89898988D8D8DF4
                  F4F4FFFFFFC4C4C47676767D7D7D7D7D7D827E7E667E7E0080800080800F4ED3
                  1340EA1342E71342E70F3FE61E4BE8D9E2FDFDFDFFF4F7FFEDF1FEFCFDFFEAEE
                  FE2D56EA0D3EE61342E71342E71340EA0F4ED3008080008080677F7F837F7F7F
                  7F7F8080807D7D7D838383E6E6E6FCFCFCF7F7F7F3F3F3FCFCFCEFEFEF888888
                  7C7C7C8080807F7F7F837F7F677F7F008080008080104FD51442EB1344E81344
                  E81344E80B3DE71948E9D9E1FCFBFBFFFAFBFFE9EDFD2855EB093CE71344E813
                  44E81344E81442EB104FD5008080008080688080858181818181808080808080
                  7C7C7C808080E7E7E7FBFBFBFCFCFCF0F0F08888887B7B7B8080808181818080
                  808480806880800080800080801152D61546ED1547EA1547EA1547EA0A3FE943
                  6BEEF8F9FEFFFFFFFFFFFFFDFEFF597DF0083DE91447EA1547EA1547EA1546ED
                  1152D60080800080806A82828682828282828282828282827D7D7D959595FAFA
                  FAFEFEFEFEFEFEFDFDFDA0A0A07C7C7C8282828282828383838783836A828200
                  80800080801254D71749EF164AEB164AEB1046EB3A66EEFFFFFFFFFFFFF0F2FE
                  DFE7FCFFFFFFFFFFFF5076F00E44EB164AEB164AEB1749EF1254D70080800080
                  806B8383888484848484848484828282929292FEFEFEFFFFFFF5F5F5EAEAEAFF
                  FFFFFFFFFF9D9D9D8181818383838484848985856B83830080800080801457D9
                  194CF0184DED184DED0C44EC83A0F5FFFFFFF7F9FE3161EE2054EDE9EEFDFFFF
                  FFA7BBF80942EC184DED184DED194CF01457D90080800080806D85858A868686
                  8686858585808080B7B7B7FFFFFFF9F9F98F8F8F888888F0F0F0FFFFFFCACACA
                  7F7F7F8585858686868A86866D8585008080008080155ADB1B50F31A51F01A51
                  F01850F01A51F07293F62A5DF1114AEF124BEF2157F07696F62056F0174FF01A
                  51F01A51F01B50F3155ADB0080800080806F87878D8989898989898989878787
                  888888AEAEAE8F8F8F8484848484848B8B8BAFAFAF8A8A8A8787878989898989
                  898D89896E86860080800080801760DD1D57F61C58F31C58F31C58F31A57F212
                  50F21855F21C58F31C58F31955F21150F21956F21C58F31C58F31C58F31D57F6
                  1760DD008080008080718989918D8D8C8C8C8D8D8D8D8D8D8B8B8B8888888A8A
                  8A8C8C8C8D8D8D8B8B8B8787878B8B8B8B8B8B8C8C8C8B8B8B908C8C71898900
                  80800080801966E1205EFB1F5FF71F5FF71F5FF71F5FF71F5FF71F5FF71F5FF7
                  1F5FF71F5FF71F5FF71F5FF71F5FF71F5FF71F5FF7205EFB1966E10080800080
                  80778F8F96929291919191919191919191919192929291919191919191919191
                  9191929292929292919191929292929292969292778F8F0080800080801D69E8
                  2363FE2264FA2264FA2264FA2264FA2264FA2264FA2264FA2264FA2264FA2264
                  FA2264FA2264FA2264FA2264FA2363FE1D69E80080800080807F92929A969695
                  9595959595949494949494959595949494959595949494959595959595959595
                  9595959494949494949A96967F92920080800080801075B62966FF2569FF2569
                  FF2569FF2569FF2569FF2569FF2569FF2569FF2569FF2569FF2569FF2569FF25
                  69FF2569FF2966FF1075B6008080008080418989AE9C9C9D99999C98989D9999
                  9C98989C98989C98989C98989D99999C98989D99999C98989D99999C98989C98
                  98AE9C9C4189890080800080800080801078B7206FEC1E70E71E70E71E70E71E
                  70E71E70E71E70E71E70E71E70E71E70E71E70E71E70E71E70E7206FEC1078B7
                  008080008080008080008080438B8B8497977E96967D95957D95957E96967E96
                  967D95957E96967E96967D95957E96967D95957E9696849797438B8B00808000
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
                OnClick = BtnCancelParcelClick
                BrTipoBotao = BrBtnFechar
              end
            end
          end
          object TbsDadAdi: TTabSheet
            Caption = 'Dados Adicionais'
            ImageIndex = 1
            object DBMemo1: TDBMemo
              Left = 0
              Top = 0
              Width = 890
              Height = 228
              Align = alClient
              DataField = 'TXDADADI'
              DataSource = DsF001
              TabOrder = 0
            end
          end
        end
        object Panel3: TPanel
          Left = 0
          Top = 425
          Width = 898
          Height = 34
          Align = alBottom
          BevelOuter = bvNone
          TabOrder = 2
          DesignSize = (
            898
            34)
          object Bevel2: TBevel
            Left = 771
            Top = 4
            Width = 5
            Height = 30
            Shape = bsLeftLine
          end
          object GroupBox1: TGroupBox
            Left = 0
            Top = 0
            Width = 625
            Height = 33
            TabOrder = 0
            object BrvLabel27: TBrvLabel
              Left = 10
              Top = 13
              Width = 94
              Height = 13
              Caption = 'Pr'#233'-Lan'#231'amento'
              Font.Charset = DEFAULT_CHARSET
              Font.Color = clBlack
              Font.Height = -11
              Font.Name = 'Tahoma'
              Font.Style = [fsBold]
              ParentFont = False
            end
            object BrvLabel2: TBrvLabel
              Left = 220
              Top = 13
              Width = 83
              Height = 13
              Caption = 'Total Produtos'
            end
            object BrvLabel28: TBrvLabel
              Left = 433
              Top = 13
              Width = 58
              Height = 13
              Caption = 'Total Nota'
            end
            object BrvDbEdit6: TBrvDbEdit
              Left = 110
              Top = 9
              Width = 87
              Height = 21
              DataField = 'NRPRELAN'
              DataSource = DsF001
              ReadOnly = True
              TabOrder = 0
              BrAlignment = taRightJustify
              BrVisibleButton = False
              BrFunctionButton = TpDbConsulta
              BrDicionario = DmSrvApl.BrvDicionario
              BrQueryConsulta = 0
              BrvReadOnlyColor = 14541539
            end
            object BrvDbEdit25: TBrvDbEdit
              Left = 528
              Top = 9
              Width = 87
              Height = 21
              DataField = 'VRCONNOT'
              DataSource = DsF001
              TabOrder = 1
              BrAlignment = taRightJustify
              BrVisibleButton = False
              BrFunctionButton = TpDbConsulta
              BrQueryConsulta = 0
              BrvReadOnlyColor = 14541539
            end
            object EdtTtProdut: TBrvEditNum
              Left = 309
              Top = 9
              Width = 100
              Height = 21
              TabOrder = 2
              Text = '0,0000'
              BrvReadOnlyColor = 14541539
              BrAlignment = taRightJustify
              BrCasasDecimais = 4
              BrSepararMilhar = False
              BrAsInteger = 0
              BrAsFloatSQL = '0.0000'
              BrVisibleButton = False
              BrFunctionButton = TpConsulta
              BrQueryCode = 0
              BrRecordar = False
            end
          end
          object BtnSalvar: TBrvBitBtn
            Left = 629
            Top = 6
            Width = 132
            Height = 25
            Hint = 'Salvar'
            Anchors = [akTop, akRight]
            Caption = 'Gravar Entrada'
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
            TabOrder = 1
            OnClick = BtnSalvarClick
            BrTipoBotao = BrBtnSalvar
          end
          object BtnCancelar: TBrvBitBtn
            Left = 784
            Top = 6
            Width = 107
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
            TabOrder = 2
            OnClick = BtnCancelarClick
            BrTipoBotao = BrBtnCancel
          end
        end
      end
    end
  end
  inherited PopCfgFrm: TPopupMenu
    Top = 0
  end
  inherited ImlPopFrm: TImageList
    Left = 40
    Top = 0
    Bitmap = {
      494C010103000500300014001400FFFFFFFFFF10FFFFFFFFFFFFFFFF424D3600
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
    Left = 72
    Top = 0
  end
  object CpF013: TBrvClientDataSet
    Aggregates = <>
    Params = <>
    BrShowFieldName = False
    BrQueryCode = 0
    BrDicionario = DmSrvApl.BrvDicionario
    BrType = VqNormal
    BrGravaLog = True
    BrUserCode = 0
    Left = 104
  end
  object DsF013: TDataSource
    DataSet = CpF013
    Left = 136
  end
  object CcF001: TBrvClientDataSet
    Aggregates = <>
    Params = <>
    BrShowFieldName = False
    BrQueryCode = 0
    BrDicionario = DmSrvApl.BrvDicionario
    BrType = VqNormal
    BrGravaLog = True
    BrUserCode = 0
    Left = 168
  end
  object DsF001: TDataSource
    DataSet = CcF001
    Left = 200
  end
  object CcF002: TBrvClientDataSet
    Aggregates = <>
    Params = <>
    BeforePost = CcF002BeforePost
    AfterPost = CcF002AfterPost
    BrShowFieldName = False
    BrQueryCode = 0
    BrDicionario = DmSrvApl.BrvDicionario
    BrType = VqNormal
    BrGravaLog = True
    BrUserCode = 0
    Left = 232
  end
  object DsF002: TDataSource
    DataSet = CcF002
    Left = 264
  end
  object CcN003: TBrvClientDataSet
    Aggregates = <>
    Params = <>
    BrShowFieldName = False
    BrQueryCode = 0
    BrDicionario = DmSrvApl.BrvDicionario
    BrType = VqNormal
    BrGravaLog = True
    BrUserCode = 0
    Left = 296
  end
  object DsN003: TDataSource
    DataSet = CcN003
    Left = 328
  end
  object CcB004: TBrvClientDataSet
    Aggregates = <>
    Params = <>
    AfterInsert = CcB004AfterInsert
    AfterScroll = CcB004AfterScroll
    BrShowFieldName = False
    BrQueryCode = 0
    BrDicionario = DmSrvApl.BrvDicionario
    BrType = VqNormal
    BrGravaLog = True
    BrUserCode = 0
    Left = 360
  end
  object DsB004: TDataSource
    DataSet = CcB004
    Left = 392
  end
  object CcB008: TBrvClientDataSet
    Aggregates = <>
    Params = <>
    AfterInsert = CcB008AfterInsert
    BeforePost = CcB008BeforePost
    BrShowFieldName = False
    BrQueryCode = 0
    BrDicionario = DmSrvApl.BrvDicionario
    BrType = VqNormal
    BrGravaLog = True
    BrUserCode = 0
    Left = 424
  end
  object DsB008: TDataSource
    DataSet = CcB008
    Left = 456
  end
  object BrvDigito: TBrvDigito
    Modulo = DvModulo11
    DigitoIgualZero = '0'
    DigitoMaiorNove = '0'
    QuantidadeDigitos = 1
    Left = 488
  end
  object CcN002: TBrvClientDataSet
    Aggregates = <>
    Params = <>
    BrShowFieldName = False
    BrQueryCode = 0
    BrDicionario = DmSrvApl.BrvDicionario
    BrType = VqNormal
    BrGravaLog = True
    BrUserCode = 0
    Left = 520
  end
  object BrvString: TBrvString
    Left = 552
  end
  object CpNfeDet: TBrvClientDataSet
    Aggregates = <>
    Params = <>
    BrShowFieldName = False
    BrQueryCode = 0
    BrDicionario = DmSrvApl.BrvDicionario
    BrType = VqNormal
    BrGravaLog = True
    BrUserCode = 0
    Left = 584
  end
  object CpNFePro: TBrvClientDataSet
    Aggregates = <>
    Params = <>
    BrShowFieldName = False
    BrQueryCode = 0
    BrDicionario = DmSrvApl.BrvDicionario
    BrType = VqNormal
    BrGravaLog = True
    BrUserCode = 0
    Left = 616
  end
  object CpNFeFat: TBrvClientDataSet
    Aggregates = <>
    Params = <>
    BrShowFieldName = False
    BrQueryCode = 0
    BrDicionario = DmSrvApl.BrvDicionario
    BrType = VqNormal
    BrGravaLog = True
    BrUserCode = 0
    Left = 648
  end
  object BrvXMLNFE: TBrvXML
    BrCdsNfeDet = CpNfeDet
    BrCdsNfeFat = CpNFeFat
    BrCdsNfePro = CpNFePro
    BrGerarBanco = True
    BrQtdePagDanfe = 0
    Left = 680
  end
  object QpXml: TBrvClientDataSet
    Aggregates = <>
    Params = <>
    BrShowFieldName = False
    BrQueryCode = 0
    BrDicionario = DmSrvApl.BrvDicionario
    BrType = VqNormal
    BrGravaLog = True
    BrUserCode = 0
    Left = 712
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
    Left = 744
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
    Left = 776
    Top = 48
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
    Left = 808
  end
  object CpB012: TBrvClientDataSet
    Aggregates = <>
    Params = <>
    BrShowFieldName = False
    BrQueryCode = 0
    BrDicionario = DmSrvApl.BrvDicionario
    BrType = VqNormal
    BrGravaLog = True
    BrUserCode = 0
    Left = 840
  end
  object CcB002: TBrvClientDataSet
    Aggregates = <>
    Params = <>
    BrShowFieldName = False
    BrQueryCode = 0
    BrDicionario = DmSrvApl.BrvDicionario
    BrType = VqNormal
    BrGravaLog = True
    BrUserCode = 0
    Left = 168
    Top = 32
  end
  object CcB004FinNot: TBrvClientDataSet
    Aggregates = <>
    Params = <>
    BrShowFieldName = False
    BrQueryCode = 0
    BrDicionario = DmSrvApl.BrvDicionario
    BrType = VqNormal
    BrGravaLog = True
    BrUserCode = 0
    Left = 200
    Top = 32
  end
  object CcB008FinNot: TBrvClientDataSet
    Aggregates = <>
    Params = <>
    BrShowFieldName = False
    BrQueryCode = 0
    BrDicionario = DmSrvApl.BrvDicionario
    BrType = VqNormal
    BrGravaLog = True
    BrUserCode = 0
    Left = 232
    Top = 32
  end
  object BrvServerProgress: TBrvServerProgress
    Left = 736
    Top = 144
  end
  object CpS004: TBrvClientDataSet
    Aggregates = <>
    Params = <>
    BrShowFieldName = False
    BrQueryCode = 0
    BrDicionario = DmSrvApl.BrvDicionario
    BrType = VqNormal
    BrGravaLog = True
    BrUserCode = 0
    Left = 840
    Top = 144
  end
end
