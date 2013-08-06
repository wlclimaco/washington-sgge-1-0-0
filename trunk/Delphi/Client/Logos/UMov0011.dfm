inherited Mov0011: TMov0011
  Caption = 'Mov0014 - Importa'#231#227'o de Dados Bayer - Entrada'
  ClientHeight = 531
  ClientWidth = 760
  ExplicitTop = -32
  ExplicitWidth = 768
  ExplicitHeight = 558
  PixelsPerInch = 96
  TextHeight = 13
  inherited NavBarra: TBrvDbNavCop
    Width = 760
    ExplicitWidth = 760
    inherited BrvSpeedButton1: TBrvSpeedButton
      Left = 735
      ExplicitLeft = 630
    end
    inherited SbtAjuda: TBrvSpeedButton
      Left = 710
      ExplicitLeft = 605
    end
  end
  inherited PnlFundo: TPanel
    Top = 127
    Width = 760
    Height = 404
    ExplicitTop = 127
    ExplicitWidth = 760
    ExplicitHeight = 404
    object PnlDados: TPanel
      Left = 1
      Top = 1
      Width = 754
      Height = 398
      Align = alClient
      TabOrder = 0
      Visible = False
      object Splitter1: TSplitter
        Left = 1
        Top = 126
        Width = 752
        Height = 3
        Cursor = crVSplit
        Align = alTop
        ExplicitLeft = 0
        ExplicitTop = 121
        ExplicitWidth = 377
      end
      object Splitter2: TSplitter
        Left = 1
        Top = 259
        Width = 752
        Height = 3
        Cursor = crVSplit
        Align = alBottom
        ExplicitTop = 132
        ExplicitWidth = 156
      end
      object DbgContLog: TDBGrid
        Left = 1
        Top = 262
        Width = 752
        Height = 98
        Align = alBottom
        DataSource = STContLog
        ReadOnly = True
        TabOrder = 0
        TitleFont.Charset = DEFAULT_CHARSET
        TitleFont.Color = clWindowText
        TitleFont.Height = -11
        TitleFont.Name = 'Tahoma'
        TitleFont.Style = []
        Columns = <
          item
            Expanded = False
            FieldName = 'QtProdut'
            Title.Caption = 'Quantidade'
            Title.Font.Charset = DEFAULT_CHARSET
            Title.Font.Color = clWindowText
            Title.Font.Height = -11
            Title.Font.Name = 'Tahoma'
            Title.Font.Style = [fsBold]
            Width = 96
            Visible = True
          end
          item
            Expanded = False
            FieldName = 'DsLote'
            Title.Caption = 'Lote'
            Title.Font.Charset = DEFAULT_CHARSET
            Title.Font.Color = clWindowText
            Title.Font.Height = -11
            Title.Font.Name = 'Tahoma'
            Title.Font.Style = [fsBold]
            Width = 92
            Visible = True
          end
          item
            Expanded = False
            FieldName = 'DtValida'
            Title.Caption = 'Validade'
            Title.Font.Charset = DEFAULT_CHARSET
            Title.Font.Color = clWindowText
            Title.Font.Height = -11
            Title.Font.Name = 'Tahoma'
            Title.Font.Style = [fsBold]
            Width = 110
            Visible = True
          end
          item
            Expanded = False
            FieldName = 'NrNota'
            Visible = False
          end
          item
            Expanded = False
            FieldName = 'CdEmbala'
            Visible = False
          end>
      end
      object Panel1: TPanel
        Left = 1
        Top = 360
        Width = 752
        Height = 37
        Align = alBottom
        Font.Charset = DEFAULT_CHARSET
        Font.Color = clWindowText
        Font.Height = -11
        Font.Name = 'Tahoma'
        Font.Style = [fsBold]
        ParentFont = False
        TabOrder = 1
        DesignSize = (
          752
          37)
        object BtnRetornar: TBrvBitBtn
          Left = 507
          Top = 4
          Width = 113
          Height = 30
          Hint = 'Ir para o primeiro'
          Anchors = [akTop, akRight]
          Caption = '&Retornar'
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
          TabOrder = 0
          OnClick = BtnRetornarClick
          BrTipoBotao = BrBtnSetaEsquerda
        end
        object BtnGravar: TBrvBitBtn
          Left = 626
          Top = 4
          Width = 113
          Height = 30
          Hint = 'Salvar'
          Anchors = [akTop, akRight]
          Caption = '&Gravar'
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
          OnClick = BtnGravarClick
          BrTipoBotao = BrBtnSalvar
        end
      end
      object Panel2: TPanel
        Left = 1
        Top = 1
        Width = 752
        Height = 20
        Align = alTop
        BevelOuter = bvNone
        Caption = 'Notas Fiscais'
        Font.Charset = DEFAULT_CHARSET
        Font.Color = clWindowText
        Font.Height = -11
        Font.Name = 'Tahoma'
        Font.Style = [fsBold]
        ParentFont = False
        TabOrder = 2
      end
      object DbgNotas: TDBGrid
        Left = 1
        Top = 21
        Width = 752
        Height = 105
        Align = alTop
        DataSource = STNotas
        ReadOnly = True
        TabOrder = 3
        TitleFont.Charset = DEFAULT_CHARSET
        TitleFont.Color = clWindowText
        TitleFont.Height = -11
        TitleFont.Name = 'Tahoma'
        TitleFont.Style = []
        OnDrawColumnCell = DbgNotasDrawColumnCell
        Columns = <
          item
            Expanded = False
            FieldName = 'NrNota'
            Title.Caption = 'Nota'
            Title.Font.Charset = DEFAULT_CHARSET
            Title.Font.Color = clWindowText
            Title.Font.Height = -11
            Title.Font.Name = 'Tahoma'
            Title.Font.Style = [fsBold]
            Width = 75
            Visible = True
          end
          item
            Expanded = False
            FieldName = 'NrSerie'
            Title.Caption = 'S'#233'rie'
            Title.Font.Charset = DEFAULT_CHARSET
            Title.Font.Color = clWindowText
            Title.Font.Height = -11
            Title.Font.Name = 'Tahoma'
            Title.Font.Style = [fsBold]
            Width = 40
            Visible = True
          end
          item
            Expanded = False
            FieldName = 'snduplic'
            Title.Caption = 'J'#225' Importada?'
            Title.Font.Charset = DEFAULT_CHARSET
            Title.Font.Color = clWindowText
            Title.Font.Height = -11
            Title.Font.Name = 'Tahoma'
            Title.Font.Style = [fsBold]
            Width = 85
            Visible = True
          end
          item
            Expanded = False
            FieldName = 'CjRemete'
            Title.Caption = 'CNPJ'
            Title.Font.Charset = DEFAULT_CHARSET
            Title.Font.Color = clWindowText
            Title.Font.Height = -11
            Title.Font.Name = 'Tahoma'
            Title.Font.Style = [fsBold]
            Width = 114
            Visible = True
          end
          item
            Expanded = False
            FieldName = 'CdTitula'
            Title.Caption = 'Cliente'
            Title.Font.Charset = DEFAULT_CHARSET
            Title.Font.Color = clWindowText
            Title.Font.Height = -11
            Title.Font.Name = 'Tahoma'
            Title.Font.Style = [fsBold]
            Width = 71
            Visible = True
          end
          item
            Expanded = False
            FieldName = 'RsTitula'
            Title.Caption = 'Raz'#227'o Social'
            Title.Font.Charset = DEFAULT_CHARSET
            Title.Font.Color = clWindowText
            Title.Font.Height = -11
            Title.Font.Name = 'Tahoma'
            Title.Font.Style = [fsBold]
            Width = 352
            Visible = True
          end>
      end
      object Panel4: TPanel
        Left = 1
        Top = 129
        Width = 752
        Height = 20
        Align = alTop
        BevelOuter = bvNone
        Caption = 'Itens das Notas Fiscais'
        Font.Charset = DEFAULT_CHARSET
        Font.Color = clWindowText
        Font.Height = -11
        Font.Name = 'Tahoma'
        Font.Style = [fsBold]
        ParentFont = False
        TabOrder = 4
      end
      object DbgItem: TDBGrid
        Left = 1
        Top = 149
        Width = 752
        Height = 110
        Align = alClient
        DataSource = STIteNota
        ReadOnly = True
        TabOrder = 5
        TitleFont.Charset = DEFAULT_CHARSET
        TitleFont.Color = clWindowText
        TitleFont.Height = -11
        TitleFont.Name = 'Tahoma'
        TitleFont.Style = []
        Columns = <
          item
            Expanded = False
            FieldName = 'CdProdut'
            Title.Caption = 'Produto'
            Title.Font.Charset = DEFAULT_CHARSET
            Title.Font.Color = clWindowText
            Title.Font.Height = -11
            Title.Font.Name = 'Tahoma'
            Title.Font.Style = [fsBold]
            Visible = True
          end
          item
            Expanded = False
            FieldName = 'CdEmbala'
            Title.Caption = 'C'#243'digo da Embalagem'
            Title.Font.Charset = DEFAULT_CHARSET
            Title.Font.Color = clWindowText
            Title.Font.Height = -11
            Title.Font.Name = 'Tahoma'
            Title.Font.Style = [fsBold]
            Width = 165
            Visible = True
          end
          item
            Expanded = False
            FieldName = 'DsProdut'
            Title.Caption = 'Descri'#231#227'o do Produto'
            Title.Font.Charset = DEFAULT_CHARSET
            Title.Font.Color = clWindowText
            Title.Font.Height = -11
            Title.Font.Name = 'Tahoma'
            Title.Font.Style = [fsBold]
            Width = 326
            Visible = True
          end
          item
            Expanded = False
            FieldName = 'QtProdut'
            Title.Caption = 'Quantidade'
            Title.Font.Charset = DEFAULT_CHARSET
            Title.Font.Color = clWindowText
            Title.Font.Height = -11
            Title.Font.Name = 'Tahoma'
            Title.Font.Style = [fsBold]
            Width = 105
            Visible = True
          end
          item
            Expanded = False
            FieldName = 'NrNota'
            Visible = False
          end>
      end
    end
  end
  object PnlArquiv: TPanel [2]
    Left = 0
    Top = 30
    Width = 760
    Height = 97
    Align = alTop
    BorderStyle = bsSingle
    TabOrder = 2
    DesignSize = (
      756
      93)
    object Label1: TLabel
      Left = 5
      Top = 34
      Width = 44
      Height = 13
      Caption = 'Arquivo'
      Font.Charset = DEFAULT_CHARSET
      Font.Color = clWindowText
      Font.Height = -11
      Font.Name = 'Tahoma'
      Font.Style = [fsBold]
      ParentFont = False
    end
    object Label2: TLabel
      Left = 5
      Top = 9
      Width = 55
      Height = 13
      Caption = 'Armaz'#233'm'
      Font.Charset = DEFAULT_CHARSET
      Font.Color = clWindowText
      Font.Height = -11
      Font.Name = 'Tahoma'
      Font.Style = [fsBold]
      ParentFont = False
    end
    object EdtNmArquiv: TBrvEdit
      Left = 66
      Top = 31
      Width = 675
      Height = 21
      TabOrder = 1
      BrvReadOnlyColor = 14541539
      BrVisibleButton = True
      BrFunctionButton = VeArquivo
      BrAlignment = taLeftJustify
      BrDicionario = DmSrvApl.BrvDicionario
      BrvQueryCode = 0
      BrRecordar = True
    end
    object EdtCdArmaze: TBrvEdit
      Left = 66
      Top = 6
      Width = 100
      Height = 21
      TabOrder = 0
      BrvReadOnlyColor = 14541539
      BrVisibleButton = True
      BrFunctionButton = VeConsulta
      BrConfigurar.Strings = (
        'EdtCdArmaze;CdArmaze;S;S;'
        'EdtNmArmaze;NmArmaze;S;N;')
      BrAlignment = taLeftJustify
      BrDicionario = DmSrvApl.BrvDicionario
      BrvQueryCode = 42
      BrRecordar = False
    end
    object EdtNmArmaze: TBrvRetCon
      Left = 168
      Top = 6
      Width = 573
      Height = 21
      TabStop = False
      BevelInner = bvLowered
      BevelKind = bkFlat
      BevelWidth = 2
      BorderStyle = bsNone
      ParentColor = True
      ParentShowHint = False
      ReadOnly = True
      ShowHint = False
      TabOrder = 2
    end
    object BtnImport: TBrvBitBtn
      Left = 612
      Top = 58
      Width = 113
      Height = 30
      Hint = 'Importar'
      Anchors = [akTop, akRight]
      Caption = '&Importar'
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
        008080008080008080008080008080008080008080008080256BFE256BFE256B
        FE256BFE256BFE256BFE256BFE00808000808000808000808000808000808000
        8080008080008080008080008080008080008080999999999999999999999999
        9999999A9A9A9A9A9A0080800080800080800080800080800080800080800080
        800080800080800080800080800080802468FC2468FC2468FC2468FC2468FC24
        68FC2468FC2468FC008080008080008080008080008080008080008080008080
        0080800080800080800080809797979797979898989797979898989797979797
        9797979700808000808000808000808000808000808000808000808000808000
        80800080800080802162F92162F92162F92162F92162F92162F92162F92162F9
        2162F90080800080800080800080800080800080800080800080800080800080
        8000808094949493939393939394949493939393939393939393939394949400
        8080008080008080008080008080008080008080008080008080008080008080
        1D5AF41D5AF41D5AF41D5AF40000001D5AF41D5AF41D5AF41D5AF41D5AF40080
        800080800080800080800080800080800080800080800080800080808E8E8E8E
        8E8E8D8D8D8E8E8E0000008D8D8D8E8E8E8E8E8E8E8E8E8E8E8E008080008080
        0080800080800080800080800080800080800080800080801C56F21C56F21C56
        F20000000000001C56F21C56F21C56F21C56F21C56F21C56F200808000808000
        80800080800080800080800080800080800080808B8B8B8B8B8B8B8B8B000000
        0000008B8B8B8B8B8B8C8C8C8B8B8B8C8C8C8C8C8C0080800080800080800080
        80008080008080008080008080008080194FEE194FEE194FEE194FEE194FEE19
        4FEE194FEE194FEE89A5F789A5F7194FEE194FEE008080008080008080008080
        0080800080800080800080808787878787878787878787878787878787878787
        87878787B9B9B9B9B9B987878787878700808000808000808000808000808000
        8080008080008080184DED184DED184DED184DED184DED184DED184DED85A1F6
        85A1F685A1F6184DED184DED184DED0080800080800080800080800080800080
        80008080868686868686868686858585868686868686868686B5B5B5B6B6B6B5
        B5B5868686858585868686008080008080008080008080008080008080008080
        1649EB1649EB1649EB1649EB1649EB1649EB7D99F47D99F47D99F47D99F47D99
        F47D99F41649EB1649EB00808000808000808000808000808000808084848484
        8484848484838383838383838383B0B0B0B0B0B0B0B0B0B0B0B0B0B0B0AFAFAF
        8383838484840080800080800080800080800080800080800080801649EB1445
        E91445E91445E9718FF2718FF2718FF2718FF2718FF2718FF2718FF2718FF214
        45E91445E9008080008080008080008080008080008080848484818181818181
        828282A9A9A9A9A9A9A9A9A9A9A9A9A9A9A9A9A9A9A9A9A9A9A9A98181818181
        810080800080800080800080800080800080800080801444E81444E81444E86C
        8AF16C8AF16C8AF16C8AF16C8AF16C8AF16C8AF16C8AF16C8AF11444E81444E8
        008080008080008080008080008080008080818181818181818181A5A5A5A6A6
        A6A5A5A5A6A6A6A6A6A6A6A6A6A5A5A5A6A6A6A5A5A581818180808000808000
        80800080800080800080800080800080801649EB1340E61340E61340E6607FEE
        607FEE607FEE607FEE607FEE607FEE607FEE607FEE1340E61340E60080800080
        800080800080800080800080808484847E7E7E7E7E7E7E7E7E9E9E9E9E9E9E9E
        9E9E9E9E9E9E9E9E9E9E9E9E9E9E9E9E9E7E7E7E7F7F7F008080008080008080
        008080008080008080008080123EE5123EE5123EE55A79ED5A79ED5A79ED5A79
        ED5A79ED5A79ED5A79ED123EE5123EE5123EE500808000808000808000808000
        80800080800080807D7D7D7D7D7D7D7D7D9B9B9B9B9B9B9B9B9B9A9A9A9B9B9B
        9B9B9B9B9B9B7D7D7D7D7D7D7D7D7D0080800080800080800080800080800080
        80008080008080113BE4113BE4113BE4113BE44F6FEB4F6FEB4F6FEB4F6FEB11
        3BE4113BE4113BE4008080008080008080008080008080008080008080008080
        0080807C7C7C7C7C7C7C7C7C7C7C7C9595959494949494949595957C7C7C7C7C
        7C7C7C7C00808000808000808000808000808000808000808000808000808000
        80801649EB1039E21039E21039E24767E94767E91039E21039E21039E2008080
        0080800080800080800080800080800080800080800080800080800080808484
        847A7A7A7A7A7A7A7A7A8F8F8F8F8F8F7A7A7A7A7A7A7A7A7A00808000808000
        80800080800080800080800080800080800080800080800080800080800F37E2
        0F37E20F37E24263E90F37E20F37E20F37E20080800080800080800080800080
        800080800080800080800080800080800080800080800080807A7A7A7A7A7A7A
        7A7A8E8E8E7A7A7A7A7A7A7A7A7A008080008080008080008080008080008080
        0080800080800080800080800080800080800080800080801649EB0E35E00E35
        E00E35E00E35E000808000808000808000808000808000808000808000808000
        8080008080008080008080008080008080008080848484787878797979797979
        7878780080800080800080800080800080800080800080800080800080800080
        800080800080800080800080800080800080800E35E00E35E00E35E000808000
        8080008080008080008080008080008080008080008080008080008080008080
        0080800080800080800080800080807878787979797878780080800080800080
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
      TabOrder = 3
      OnClick = BtnImportClick
      BrTipoBotao = BrBtnImport
      ExplicitLeft = 616
    end
  end
  object CTNotas: TClientDataSet
    Aggregates = <>
    Params = <>
    AfterScroll = CTNotasAfterScroll
    Left = 232
    Top = 312
    object CTNotasNrNota: TIntegerField
      FieldName = 'NrNota'
    end
    object CTNotasNrSerie: TStringField
      FieldName = 'NrSerie'
      Size = 3
    end
    object CTNotasCjRemete: TStringField
      FieldName = 'CjRemete'
      Size = 14
    end
    object CTNotasCdTitula: TIntegerField
      FieldName = 'CdTitula'
    end
    object CTNotasRsTitula: TStringField
      FieldName = 'RsTitula'
      Size = 40
    end
    object CTNotasNrFornec: TStringField
      FieldName = 'NrFornec'
      Size = 10
    end
    object CTNotasCjDestin: TStringField
      FieldName = 'CjDestin'
      Size = 14
    end
    object CTNotassnduplic: TStringField
      FieldName = 'snduplic'
      Size = 3
    end
  end
  object CTIteNota: TClientDataSet
    Aggregates = <>
    Params = <>
    AfterScroll = CTIteNotaAfterScroll
    Left = 312
    Top = 312
    object CTIteNotaNrNota: TIntegerField
      FieldName = 'NrNota'
    end
    object CTIteNotaQtProdut: TFloatField
      FieldName = 'QtProdut'
    end
    object CTIteNotaCdProdut: TIntegerField
      FieldName = 'CdProdut'
    end
    object CTIteNotaDsProdut: TStringField
      FieldName = 'DsProdut'
      Size = 50
    end
    object CTIteNotaCdEmbala: TStringField
      FieldName = 'CdEmbala'
    end
  end
  object STNotas: TDataSource
    DataSet = CTNotas
    Left = 256
    Top = 312
  end
  object STIteNota: TDataSource
    DataSet = CTIteNota
    Left = 339
    Top = 312
  end
  object CTProduto: TClientDataSet
    Aggregates = <>
    Params = <>
    Left = 120
    Top = 312
    object CTProdutoCdProdut: TIntegerField
      FieldName = 'CdProdut'
    end
    object CTProdutoDsProdut: TStringField
      FieldName = 'DsProdut'
      Size = 50
    end
    object CTProdutoCdEmbala: TStringField
      FieldName = 'CdEmbala'
    end
  end
  object CPProduto: TClientDataSet
    Aggregates = <>
    Params = <>
    Left = 53
    Top = 312
  end
  object CTTitula: TClientDataSet
    Aggregates = <>
    Params = <>
    Left = 24
    Top = 312
    object CTTitulaCdTitula: TIntegerField
      FieldName = 'CdTitula'
    end
    object CTTitulaRsTitula: TStringField
      FieldName = 'RsTitula'
      Size = 50
    end
    object CTTitulaCjTitula: TStringField
      FieldName = 'CjTitula'
      Size = 14
    end
  end
  object CTParams: TClientDataSet
    Aggregates = <>
    Params = <>
    Left = 184
    Top = 312
    object CTParamsCdArmaze: TIntegerField
      FieldName = 'CdArmaze'
    end
    object CTParamsCdTipAti: TIntegerField
      FieldName = 'CdTipAti'
    end
    object CTParamsCdUsuGer: TIntegerField
      FieldName = 'CdUsuGer'
    end
    object CTParamsCdEmbala: TStringField
      FieldName = 'CdEmbala'
    end
    object CTParamsQtProdut: TFloatField
      FieldName = 'QtProdut'
    end
    object CTParamsNrNfOrig: TIntegerField
      FieldName = 'NrNfOrig'
    end
    object CTParamsNrSeNfOr: TStringField
      FieldName = 'NrSeNfOr'
      Size = 3
    end
    object CTParamsDtValida: TDateField
      FieldName = 'DtValida'
    end
    object CTParamsDsLote: TStringField
      FieldName = 'DsLote'
    end
    object CTParamsQtProCon: TFloatField
      FieldName = 'QtProCon'
    end
  end
  object CTContLog: TClientDataSet
    Aggregates = <>
    Params = <>
    Left = 376
    Top = 312
    object IntegerField1: TIntegerField
      FieldName = 'NrNota'
    end
    object FloatField1: TFloatField
      FieldName = 'QtProdut'
    end
    object StringField2: TStringField
      FieldName = 'DsLote'
      Size = 10
    end
    object DateField1: TDateField
      FieldName = 'DtValida'
    end
    object IntegerField2: TIntegerField
      FieldName = 'CdProdut'
    end
    object CTContLogCdEmbala: TStringField
      FieldName = 'CdEmbala'
    end
  end
  object STContLog: TDataSource
    DataSet = CTContLog
    Left = 403
    Top = 312
  end
  object BrvString: TBrvString
    Left = 464
    Top = 312
  end
  object CdsW007: TClientDataSet
    Aggregates = <>
    Params = <>
    Left = 528
    Top = 312
  end
  object BrvAlertProgress: TBrvAlertProgress
    Left = 592
    Top = 88
  end
  object BrvServerProgress: TBrvServerProgress
    Left = 560
    Top = 88
  end
end
