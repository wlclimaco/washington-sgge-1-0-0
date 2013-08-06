inherited Mov0014: TMov0014
  Caption = 'Mov0014 - Importa'#231#227'o de Dados Bayer - Sa'#237'da'
  ClientHeight = 531
  ClientWidth = 740
  ExplicitWidth = 748
  ExplicitHeight = 558
  PixelsPerInch = 96
  TextHeight = 13
  inherited NavBarra: TBrvDbNavCop
    Width = 740
    ExplicitWidth = 740
    inherited BrvSpeedButton1: TBrvSpeedButton
      Left = 715
      ExplicitLeft = 682
    end
    inherited SbtAjuda: TBrvSpeedButton
      Left = 690
      ExplicitLeft = 657
    end
  end
  inherited PnlFundo: TPanel
    Width = 740
    Height = 501
    ExplicitWidth = 740
    ExplicitHeight = 501
    object PnlArquiv: TPanel
      Left = 1
      Top = 1
      Width = 734
      Height = 97
      Align = alTop
      BorderStyle = bsSingle
      TabOrder = 0
      DesignSize = (
        730
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
        Width = 652
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
        Width = 550
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
        Left = 597
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
        ExplicitLeft = 601
      end
    end
    object PnlDados: TPanel
      Left = 1
      Top = 98
      Width = 734
      Height = 398
      Align = alClient
      TabOrder = 1
      Visible = False
      object Splitter1: TSplitter
        Left = 1
        Top = 126
        Width = 732
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
        Width = 732
        Height = 3
        Cursor = crVSplit
        Align = alBottom
        ExplicitTop = 132
        ExplicitWidth = 156
      end
      object DbgContLog: TDBGrid
        Left = 1
        Top = 262
        Width = 732
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
            Width = 100
            Visible = True
          end
          item
            Expanded = False
            FieldName = 'CdEmbala'
            Title.Caption = 'Embalagem'
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
          end>
      end
      object Panel1: TPanel
        Left = 1
        Top = 360
        Width = 732
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
          732
          37)
        object BtnRetornar: TBrvBitBtn
          Left = 487
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
            008080008080008080008080008080008080A431009B3300983000A32F000080
            8000808000808000808000808000808000808000808000808000808000808000
            80800080800080800080804B87879790908E8E8E8D8D8D968F8F4C8888008080
            0080800080800080800080800080800080800080800080800080800080800080
            80008080C23600B44C14B15C2DB46233AE5B31A34621A13000AD1A0000808000
            8080008080008080008080008080008080008080008080008080008080008080
            AC9696A49D9DA3A3A3A6A6A6A4A4A49B9B9B999292A38D8D0080800080800080
            80008080008080008080008080008080008080008080B03E00CC6D35D9B090EC
            D7C7F6EBE4F9F2EEF7F0EAEDDCD0DBB8A4BA7559A228009B2400008080008080
            008080008080008080008080008080008080959090B7ADADC9C9C9DDDDDDE8E8
            E8ECECECEAEAEAE0E0E0CFCFCFB1B1B19A8F8F928C8C00808000808000808000
            8080008080008080008080AE4900D79C6DFAF4EFFFFFFFFFFFFFFFFFFFFFFFFF
            FFFFFFFFFFFFFFFFFFFCF9F8E0C3B09928009328000080800080800080800080
            80008080008080929292C2BFBFECECECF6F6F6F8F8F8F9F9F9FAFAFAFAFAFAF8
            F8F8F7F7F7EFEFEFD4D4D4918E8E8B8B8B008080008080008080008080008080
            BD4D00E1B083FFFFFFFFFFFFFFFFFFEDDBCFCC987DBB7859C3876CE2C5B7FFFF
            FFFFFFFFFFFFFFE5CABC9928009920000080800080800080800080809A9494CC
            C9C9F3F3F3F7F7F7F7F7F7E1E1E1C0C0C0B1B1B1B9B9B9D6D6D6F4F4F4F8F8F8
            F7F7F7D8D8D8918E8E928C8C008080008080008080008080E7A063FFFFFFFFFF
            FFF5E9DDB7642A992700972A009426008E1C008914009A3408DDBDAEFFFFFFFF
            FFFFE1C2B2A32800008080008080008080008080CBC1C1F3F3F3F7F7F7E6E6E6
            A4A4A48787878A8A8A8A8A8A888888838383929292D3D3D3F5F5F5F8F8F8D4D4
            D49A8F8F008080008080008080E9710EF6E8D8FFFFFFFBF8F3B96010B15418B0
            530AA94700A543009E3A00962F00912200942A01DEBEADFFFFFFFDFBF8BC785A
            AE1A00008080008080C1AAAAE5E5E5F7F7F7EDEDED9D9D9D9D9D9D9898989292
            929292929191918D8D8D8B8B8B8E8E8ED2D2D2F8F8F8EFEFEFB2B2B2A48D8D00
            8080666F34E4A15AFFFFFFFFFFFFD59B54BD6206F5E9DCC67E45AC4800AB4900
            9E3200A74B19D2A48DBC7858973004FFFFFFFFFFFFDDBAA4A2300053522D0080
            80C4BEBEF6F6F6F7F7F7B8B8B89B9B9BE5E5E5AFAFAF9191919393938D8D8D9A
            9A9AC7C7C7B0B0B08F8F8FF4F4F4F7F7F7CFCFCF9993934C8888CF6600E8C69B
            FFFFFFFAF1E3C56A05CB7E22FFFFFFCD8C52AC4300A83D00C37F53EFDCD1FFFF
            FFD1A286830900E2C7B6FFFFFFEEDDD2A54822A62900A4AAB1D0D0D0F8F8F8E8
            E8E89D9D9DA7A7A7F5F5F5B5B5B58F8F8F8D8D8DB2B2B2E0E0E0F9F9F9C3C3C3
            828282D5D5D5F8F8F8E1E1E19B9B9B979191CA7310F3E1C8FFFFFFEBC893C86A
            00D08525FEFDFDCE8A4CBE6718E6C8ABFFFFFFFFFFFFFFFFFFD09F7E8B1900C4
            8768FFFFFFF7F0EAB05D31992500A3AFB6DFDFDFF9F9F9CFCFCF9A9A9AA9A9A9
            F1F1F1B4B4B49F9F9FD5D5D5F2F2F2F4F4F4F8F8F8C2C2C2878787B8B8B8FAFA
            FAEAEAEAA3A3A38E8E8ED58C37F9F0E3FFFFFFEABF7AD07300D78D26FCFAF7D9
            9E59EDD3B4FFFFFFFFFFFFFFFFFFFFFFFFD4A37E972600BF7D54FFFFFFFAF3EF
            B56634992700B2BFC6E8E8E8F8F8F8C7C7C79C9C9CA9A9A9EFEFEFBABABAD9D9
            D9F6F6F6F6F6F6F2F2F2F7F7F7C3C3C38B8B8BB0B0B0FAFAFAECECECA5A5A58D
            8D8DDB9B4BFCF6ECFFFFFFF1D5A8D37700DB9324FEFEFDD8974AC05E00D69856
            F6EADCFFFFFFFFFFFFD7A87E9B2A00D19E79FFFFFFF7EDE6B35F2EA42600BAC6
            CEEBEBEBF7F7F7D6D6D69E9E9EABABABF0F0F0B5B5B5959595B8B8B8E6E6E6F5
            F5F5F9F9F9C4C4C48A8A8AC1C1C1F9F9F9E9E9E9A3A3A3979090EB9C3CFBF3E9
            FFFFFFFCFAF4D7830DDB9320FFFFFFDEA355C86B00C16000C7741DE4BE95FFFF
            FEDCB18A9F2D00EEDCCDFFFFFFEDD9C8B64D14484C26CDC4C9EAEAEAF4F4F4EF
            EFEFA3A3A3AAAAAAF7F7F7BABABA989898959595A4A4A4CDCDCDF2F2F2C8C8C8
            888888E0E0E0F8F8F8DEDEDEA59E9E4B8787008080FFE8CCFFFFFFFFFFFFEBC3
            83D27B00F2D8ADDE9A37D07800CD7700C46900C16501D18F49C6792DC07127FF
            FFFFFFFFFFDCB492C53A00008080008080EFE6E6F3F3F3F6F6F6C9C9C99D9D9D
            D8D8D8B1B1B19D9D9D9D9D9D9999999B9B9BB4B4B4A7A7A7A5A5A5F7F7F7F7F7
            F7CBCBCBAD9797008080008080CF6600FFFFFFFFFFFFFFFFFFDE9B38CF6A00D4
            7C00D58100D37E00CC7700C76D00BC5400C0660AF7EBDEFFFFFFFAF4EFCE7335
            008080008080008080F0D8D8F3F3F3F3F3F3F7F7F7B2B2B29999999F9F9FA0A0
            A09F9F9F9C9C9C9A9A9A9292929D9D9DE6E6E6F7F7F7ECECECB8AEAE00808000
            8080008080CF6600FFFFFFFFFFFFFFFFFFFFFFFFEBC688D88818D47C01D37B00
            D37900D07D0DDFA75DFEFAF4FFFFFFFFFFFFD99E6FB542000080800080800080
            80008080F5E7E7F3F3F3F3F3F3F7F7F7CBCBCBA8A8A8A0A0A09E9E9EA0A0A0A2
            A2A2BCBCBCEFEFEFF7F7F7F3F3F3C3C0C0979191008080008080008080008080
            CF6600FFF0DBFFFFFFFFFFFFFFFFFFFDFAF4F1D4A9EABE7DEECB95FAF1E3FFFF
            FFFFFFFFFFFFFFE2B083B14E00008080008080008080008080008080B6BDBDEE
            E9E9F4F4F4F3F3F3F6F6F6EEEEEED6D6D6C8C8C8D0D0D0E9E9E9F6F6F6F6F6F6
            F4F4F4CBC8C8939393008080008080008080008080008080008080CF6600FEE7
            CBFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFF8EEE3DCA76EBF
            5100008080008080008080008080008080008080008080B5BCBCE9E4E4F4F4F4
            F3F3F3F4F4F4F6F6F6F7F7F7F7F7F7F6F6F6F5F5F5E9E9E9C2C0C09A94940080
            80008080008080008080008080008080008080008080CF6600CF6600F8EBD7FE
            FDFBFFFFFFFFFFFFFFFEFDF7ECDDE7C293DC8330E35900008080008080008080
            008080008080008080008080008080008080BAB5B5E2D5D5E5E5E5F1F1F1F3F3
            F3F4F4F4F1F1F1E7E7E7CECECEB9B0B0B59C9C00808000808000808000808000
            8080008080008080008080008080008080008080EE941EDE9E45DFA458DDA259
            D68F3FCD7D1DD56C000080800080800080800080800080800080800080800080
            80008080008080008080008080008080BBAFAFB6B6B6BABABABBBBBBB1B1B1A6
            A6A6A89F9F008080008080008080008080008080008080008080}
          NumGlyphs = 2
          ParentDoubleBuffered = False
          ParentFont = False
          ParentShowHint = False
          ShowHint = True
          TabOrder = 0
          OnClick = BtnRetornarClick
          BrTipoBotao = BrBtnPrimei
        end
        object BtnGravar: TBrvBitBtn
          Left = 606
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
        Width = 732
        Height = 20
        Align = alTop
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
        Width = 732
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
            FieldName = 'NrFornec'
            Title.Caption = 'Remessa'
            Title.Font.Charset = DEFAULT_CHARSET
            Title.Font.Color = clWindowText
            Title.Font.Height = -11
            Title.Font.Name = 'Tahoma'
            Title.Font.Style = [fsBold]
            Width = 100
            Visible = True
          end
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
            Width = 300
            Visible = True
          end
          item
            Expanded = False
            FieldName = 'CjDestin'
            Title.Caption = 'Destinat'#225'rio'
            Title.Font.Charset = DEFAULT_CHARSET
            Title.Font.Color = clWindowText
            Title.Font.Height = -11
            Title.Font.Name = 'Tahoma'
            Title.Font.Style = [fsBold]
            Width = 114
            Visible = True
          end>
      end
      object Panel4: TPanel
        Left = 1
        Top = 129
        Width = 732
        Height = 20
        Align = alTop
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
        Width = 732
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
            Width = 100
            Visible = True
          end
          item
            Expanded = False
            FieldName = 'CdEmbala'
            Title.Caption = 'Embalagem'
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
  object CPProduto: TClientDataSet
    Aggregates = <>
    Params = <>
    Left = 53
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
  object STNotas: TDataSource
    DataSet = CTNotas
    Left = 256
    Top = 312
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
  object STIteNota: TDataSource
    DataSet = CTIteNota
    Left = 339
    Top = 312
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
    Left = 480
    Top = 96
  end
  object BrvServerProgress: TBrvServerProgress
    Left = 512
    Top = 96
  end
end
