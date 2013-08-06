inherited Mov0001: TMov0001
  Left = 266
  Top = 185
  Caption = 'Mov0001 - Data de entrega da mercadoria'
  ClientHeight = 575
  ClientWidth = 876
  ExplicitWidth = 892
  ExplicitHeight = 613
  PixelsPerInch = 96
  TextHeight = 13
  inherited NavBarra: TBrvDbNavCop
    Width = 876
    ExplicitWidth = 876
    inherited BrvSpeedButton1: TBrvSpeedButton
      Left = 851
      ExplicitLeft = 819
    end
    inherited SbtAjuda: TBrvSpeedButton
      Left = 826
      ExplicitLeft = 794
    end
  end
  inherited PnlFundo: TPanel
    Width = 876
    Height = 545
    ExplicitWidth = 876
    ExplicitHeight = 545
    object PnlCabeca: TPanel
      Left = 1
      Top = 1
      Width = 870
      Height = 55
      Align = alTop
      TabOrder = 0
      DesignSize = (
        870
        55)
      object Label1: TLabel
        Left = 8
        Top = 7
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
      object Label47: TLabel
        Left = 255
        Top = 33
        Width = 52
        Height = 13
        Caption = 'N'#186' CTRC'
        Font.Charset = DEFAULT_CHARSET
        Font.Color = clBlack
        Font.Height = -11
        Font.Name = 'MS Sans Serif'
        Font.Style = [fsBold]
        ParentFont = False
      end
      object Label2: TLabel
        Left = 147
        Top = 33
        Width = 30
        Height = 13
        Caption = 'S'#233'rie'
        Font.Charset = DEFAULT_CHARSET
        Font.Color = clBlack
        Font.Height = -11
        Font.Name = 'MS Sans Serif'
        Font.Style = [fsBold]
        ParentFont = False
      end
      object Label3: TLabel
        Left = 488
        Top = 33
        Width = 64
        Height = 13
        Caption = 'Per'#237'odo de'
        Font.Charset = DEFAULT_CHARSET
        Font.Color = clBlack
        Font.Height = -11
        Font.Name = 'MS Sans Serif'
        Font.Style = [fsBold]
        ParentFont = False
      end
      object Label4: TLabel
        Left = 653
        Top = 33
        Width = 19
        Height = 13
        Caption = 'at'#233
        Font.Charset = DEFAULT_CHARSET
        Font.Color = clBlack
        Font.Height = -11
        Font.Name = 'MS Sans Serif'
        Font.Style = [fsBold]
        ParentFont = False
      end
      object Label26: TLabel
        Left = 653
        Top = 7
        Width = 34
        Height = 13
        Caption = 'Carga'
        Font.Charset = DEFAULT_CHARSET
        Font.Color = clBlack
        Font.Height = -11
        Font.Name = 'MS Sans Serif'
        Font.Style = [fsBold]
        ParentFont = False
      end
      object Label25: TLabel
        Left = 385
        Top = 33
        Width = 26
        Height = 13
        Caption = 'RPS'
        Font.Charset = DEFAULT_CHARSET
        Font.Color = clBlack
        Font.Height = -11
        Font.Name = 'MS Sans Serif'
        Font.Style = [fsBold]
        ParentFont = False
      end
      object Label27: TLabel
        Left = 8
        Top = 33
        Width = 62
        Height = 13
        Caption = 'Modelo NF'
        Font.Charset = DEFAULT_CHARSET
        Font.Color = clBlack
        Font.Height = -11
        Font.Name = 'MS Sans Serif'
        Font.Style = [fsBold]
        ParentFont = False
      end
      object EdtCdEmpres: TBrvEditNum
        Left = 76
        Top = 4
        Width = 65
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
      object EdtCdCtrc: TBrvEditNum
        Left = 313
        Top = 29
        Width = 65
        Height = 21
        TabOrder = 5
        Text = '0'
        BrvReadOnlyColor = 14541539
        BrAlignment = taRightJustify
        BrCasasDecimais = 0
        BrSepararMilhar = False
        BrAsInteger = 0
        BrAsFloatSQL = '0'
        BrVisibleButton = False
        BrFunctionButton = TpConsulta
        BrOnBeforeConsulta = EdtCdCtrcBrOnBeforeConsulta
        BrOnAfterConsulta = EdtCdCtrcBrOnAfterConsulta
        BrConfigurar.Strings = (
          'Edtcdctrc;CDCTRC;S;S;'
          'Edtcdserie;NrSeriNf;S;S; '
          'EdtCdEmpres;CdEmpres;S;S;')
        BrDicionario = DmSrvApl.BrvDicionario
        BrQueryCode = 0
        BrRecordar = False
      end
      object EdtCdSerie: TBrvEdit
        Left = 183
        Top = 29
        Width = 65
        Height = 21
        TabOrder = 4
        BrvReadOnlyColor = 14541539
        BrVisibleButton = False
        BrFunctionButton = VeConsulta
        BrAlignment = taLeftJustify
        BrDicionario = DmSrvApl.BrvDicionario
        BrvQueryCode = 0
        BrRecordar = False
      end
      object BotLocali: TBrvBitBtn
        Left = 774
        Top = 25
        Width = 90
        Height = 25
        Hint = 'Localizar'
        Anchors = [akTop, akRight]
        Caption = 'Localizar'
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
        TabOrder = 9
        OnClick = BotLocaliClick
        BrTipoBotao = BrBtnLocalizar
      end
      object EdtDtInicio: TBrvEditDate
        Left = 557
        Top = 29
        Width = 90
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
      object EdtDtFinal: TBrvEditDate
        Left = 678
        Top = 29
        Width = 90
        Height = 21
        EditMask = '99/99/9999;1; '
        MaxLength = 10
        TabOrder = 8
        Text = '  /  /    '
        BrvReadOnlyColor = 14541539
        BrDataValida = False
        BrDataVazia = True
        BrFunctionButton = TVdData
        BrAlignment = taLeftJustify
        BrDicionario = DmSrvApl.BrvDicionario
        BrRecordar = False
      end
      object EdtCdCarga: TBrvEditNum
        Left = 693
        Top = 4
        Width = 75
        Height = 21
        TabOrder = 2
        Text = '0'
        BrvReadOnlyColor = 14541539
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
      object EdtNrRps: TBrvEditNum
        Left = 417
        Top = 29
        Width = 65
        Height = 21
        TabOrder = 6
        Text = '0'
        BrvReadOnlyColor = 14541539
        BrAlignment = taRightJustify
        BrCasasDecimais = 0
        BrSepararMilhar = False
        BrAsInteger = 0
        BrAsFloatSQL = '0'
        BrVisibleButton = False
        BrFunctionButton = TpConsulta
        BrOnBeforeConsulta = EdtCdCtrcBrOnBeforeConsulta
        BrConfigurar.Strings = (
          'Edtcdctrc;CDCTRC;S;S;'
          'Edtcdserie;NrSeriNf;S;S; '
          'EdtCdEmpres;CdEmpres;S;S;')
        BrDicionario = DmSrvApl.BrvDicionario
        BrQueryCode = 0
        BrRecordar = False
      end
      object LblDsEmpres: TBrvRetCon
        Left = 147
        Top = 4
        Width = 500
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
        TabOrder = 1
      end
      object EdtDsModeNF: TBrvEdit
        Left = 76
        Top = 29
        Width = 65
        Height = 21
        TabOrder = 3
        BrvReadOnlyColor = 14541539
        BrVisibleButton = False
        BrFunctionButton = VeConsulta
        BrAlignment = taLeftJustify
        BrDicionario = DmSrvApl.BrvDicionario
        BrvQueryCode = 0
        BrRecordar = False
      end
    end
    object PnlDados: TPanel
      Left = 1
      Top = 56
      Width = 870
      Height = 484
      Align = alClient
      TabOrder = 1
      Visible = False
      object PnlSalva: TPanel
        Left = 1
        Top = 447
        Width = 868
        Height = 36
        Align = alBottom
        BorderStyle = bsSingle
        TabOrder = 0
        DesignSize = (
          864
          32)
        object BotSalvar: TBrvBitBtn
          Left = 669
          Top = 4
          Width = 90
          Height = 25
          Hint = 'Salvar'
          Anchors = [akTop, akRight]
          Caption = 'Salvar'
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
          OnClick = BotSalvarClick
          BrTipoBotao = BrBtnSalvar
        end
        object BotCancel: TBrvBitBtn
          Left = 763
          Top = 4
          Width = 90
          Height = 25
          Hint = 'Cancelar'
          Anchors = [akTop, akRight]
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
          OnClick = BotCancelClick
          BrTipoBotao = BrBtnCancel
        end
      end
      object DbgDados: TBrvDbGrid
        Left = 1
        Top = 1
        Width = 868
        Height = 105
        BrShowMemo = True
        BrReadOnlyStyle = []
        BrReadOnlyColor = clBlack
        Align = alTop
        Anchors = [akLeft, akTop, akRight, akBottom]
        DataSource = DsT002
        ReadOnly = True
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
        BrGradeZebrada = True
        Columns = <
          item
            Expanded = False
            FieldName = 'CdEmpres'
            Title.Caption = 'Empresa'
            Title.Font.Charset = DEFAULT_CHARSET
            Title.Font.Color = clWindowText
            Title.Font.Height = -11
            Title.Font.Name = 'Tahoma'
            Title.Font.Style = [fsBold]
            Width = 52
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
            Title.Font.Charset = DEFAULT_CHARSET
            Title.Font.Color = clWindowText
            Title.Font.Height = -11
            Title.Font.Name = 'Tahoma'
            Title.Font.Style = [fsBold]
            Width = 57
            Visible = True
            BrConsulta = 0
            BrPermissao = []
            BrValueHalfChecked = False
            BrSaveOnClick = False
          end
          item
            Expanded = False
            FieldName = 'DsModeNF'
            Title.Caption = 'Modelo NF'
            Title.Font.Charset = DEFAULT_CHARSET
            Title.Font.Color = clWindowText
            Title.Font.Height = -11
            Title.Font.Name = 'Tahoma'
            Title.Font.Style = [fsBold]
            Width = 65
            Visible = True
            BrConsulta = 0
            BrPermissao = []
            BrValueHalfChecked = False
            BrSaveOnClick = False
          end
          item
            Expanded = False
            FieldName = 'NrSeriNf'
            Title.Caption = 'S'#233'rie'
            Title.Font.Charset = DEFAULT_CHARSET
            Title.Font.Color = clWindowText
            Title.Font.Height = -11
            Title.Font.Name = 'Tahoma'
            Title.Font.Style = [fsBold]
            Width = 34
            Visible = True
            BrConsulta = 0
            BrPermissao = []
            BrValueHalfChecked = False
            BrSaveOnClick = False
          end
          item
            Expanded = False
            FieldName = 'DtEmissa'
            Title.Caption = 'Emissao'
            Title.Font.Charset = DEFAULT_CHARSET
            Title.Font.Color = clWindowText
            Title.Font.Height = -11
            Title.Font.Name = 'Tahoma'
            Title.Font.Style = [fsBold]
            Width = 61
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
            FieldName = 'NrRps'
            Title.Caption = 'RPS'
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
            FieldName = 'DtEntreg'
            Title.Caption = 'Entrega'
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
            FieldName = 'CdVeicul'
            Title.Caption = 'Ve'#237'culo'
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
            FieldName = 'NrPlavei'
            Title.Caption = 'Placa'
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
            FieldName = 'RsRemete'
            Title.Caption = 'Remetente'
            Title.Font.Charset = DEFAULT_CHARSET
            Title.Font.Color = clWindowText
            Title.Font.Height = -11
            Title.Font.Name = 'Tahoma'
            Title.Font.Style = [fsBold]
            Width = 229
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
            Width = 255
            Visible = True
            BrConsulta = 0
            BrPermissao = []
            BrValueHalfChecked = False
            BrSaveOnClick = False
          end>
      end
      object GroupBox1: TGroupBox
        Left = 435
        Top = 171
        Width = 434
        Height = 154
        Align = alClient
        Caption = 'Destinat'#225'rio'
        Font.Charset = DEFAULT_CHARSET
        Font.Color = clWindowText
        Font.Height = -11
        Font.Name = 'Tahoma'
        Font.Style = [fsBold]
        ParentFont = False
        TabOrder = 2
        object Label11: TLabel
          Left = 5
          Top = 18
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
        object Label13: TLabel
          Left = 5
          Top = 45
          Width = 55
          Height = 13
          Caption = 'Endere'#231'o'
          Font.Charset = DEFAULT_CHARSET
          Font.Color = clWindowText
          Font.Height = -11
          Font.Name = 'MS Sans Serif'
          Font.Style = [fsBold]
          ParentFont = False
        end
        object Label16: TLabel
          Left = 5
          Top = 72
          Width = 34
          Height = 13
          Caption = 'Bairro'
          Font.Charset = DEFAULT_CHARSET
          Font.Color = clWindowText
          Font.Height = -11
          Font.Name = 'MS Sans Serif'
          Font.Style = [fsBold]
          ParentFont = False
        end
        object Label17: TLabel
          Left = 5
          Top = 99
          Width = 57
          Height = 13
          Caption = 'Munic'#237'pio'
          Font.Charset = DEFAULT_CHARSET
          Font.Color = clWindowText
          Font.Height = -11
          Font.Name = 'MS Sans Serif'
          Font.Style = [fsBold]
          ParentFont = False
        end
        object Label22: TLabel
          Left = 5
          Top = 126
          Width = 77
          Height = 13
          Caption = 'C.N.P.J (M.F)'
          Font.Charset = DEFAULT_CHARSET
          Font.Color = clWindowText
          Font.Height = -11
          Font.Name = 'MS Sans Serif'
          Font.Style = [fsBold]
          ParentFont = False
        end
        object Label19: TLabel
          Left = 324
          Top = 99
          Width = 23
          Height = 13
          Caption = 'Est:'
          Font.Charset = DEFAULT_CHARSET
          Font.Color = clWindowText
          Font.Height = -11
          Font.Name = 'MS Sans Serif'
          Font.Style = [fsBold]
          ParentFont = False
        end
        object Label24: TLabel
          Left = 266
          Top = 126
          Width = 13
          Height = 13
          Caption = 'IE'
          Font.Charset = DEFAULT_CHARSET
          Font.Color = clWindowText
          Font.Height = -11
          Font.Name = 'MS Sans Serif'
          Font.Style = [fsBold]
          ParentFont = False
        end
        object BrvDBRetCon16: TBrvDBRetCon
          Left = 91
          Top = 15
          Width = 332
          Height = 21
          Margins.Left = 0
          Margins.Top = 0
          Margins.Right = 0
          Margins.Bottom = 0
          TabStop = False
          BevelInner = bvLowered
          BevelKind = bkFlat
          BevelWidth = 2
          BorderStyle = bsNone
          DataField = 'RsDestin'
          DataSource = DsT002
          Font.Charset = DEFAULT_CHARSET
          Font.Color = clWindowText
          Font.Height = -11
          Font.Name = 'Tahoma'
          Font.Style = [fsBold]
          ParentColor = True
          ParentFont = False
          ReadOnly = True
          TabOrder = 0
        end
        object BrvDBRetCon17: TBrvDBRetCon
          Left = 91
          Top = 42
          Width = 332
          Height = 21
          Margins.Left = 0
          Margins.Top = 0
          Margins.Right = 0
          Margins.Bottom = 0
          TabStop = False
          BevelInner = bvLowered
          BevelKind = bkFlat
          BevelWidth = 2
          BorderStyle = bsNone
          DataField = 'EdDestin'
          DataSource = DsT002
          Font.Charset = DEFAULT_CHARSET
          Font.Color = clWindowText
          Font.Height = -11
          Font.Name = 'Tahoma'
          Font.Style = [fsBold]
          ParentColor = True
          ParentFont = False
          ReadOnly = True
          TabOrder = 1
        end
        object BrvDBRetCon18: TBrvDBRetCon
          Left = 91
          Top = 69
          Width = 332
          Height = 21
          Margins.Left = 0
          Margins.Top = 0
          Margins.Right = 0
          Margins.Bottom = 0
          TabStop = False
          BevelInner = bvLowered
          BevelKind = bkFlat
          BevelWidth = 2
          BorderStyle = bsNone
          DataField = 'BiDestin'
          DataSource = DsT002
          Font.Charset = DEFAULT_CHARSET
          Font.Color = clWindowText
          Font.Height = -11
          Font.Name = 'Tahoma'
          Font.Style = [fsBold]
          ParentColor = True
          ParentFont = False
          ReadOnly = True
          TabOrder = 2
        end
        object BrvDBRetCon19: TBrvDBRetCon
          Left = 91
          Top = 96
          Width = 226
          Height = 21
          Margins.Left = 0
          Margins.Top = 0
          Margins.Right = 0
          Margins.Bottom = 0
          TabStop = False
          BevelInner = bvLowered
          BevelKind = bkFlat
          BevelWidth = 2
          BorderStyle = bsNone
          DataField = 'NmCidDes'
          DataSource = DsT002
          Font.Charset = DEFAULT_CHARSET
          Font.Color = clWindowText
          Font.Height = -11
          Font.Name = 'Tahoma'
          Font.Style = [fsBold]
          ParentColor = True
          ParentFont = False
          ReadOnly = True
          TabOrder = 3
        end
        object BrvDBRetCon20: TBrvDBRetCon
          Left = 91
          Top = 123
          Width = 167
          Height = 21
          Margins.Left = 0
          Margins.Top = 0
          Margins.Right = 0
          Margins.Bottom = 0
          TabStop = False
          BevelInner = bvLowered
          BevelKind = bkFlat
          BevelWidth = 2
          BorderStyle = bsNone
          DataField = 'CgcDesti'
          DataSource = DsT002
          Font.Charset = DEFAULT_CHARSET
          Font.Color = clWindowText
          Font.Height = -11
          Font.Name = 'Tahoma'
          Font.Style = [fsBold]
          ParentColor = True
          ParentFont = False
          ReadOnly = True
          TabOrder = 4
        end
        object BrvDBRetCon21: TBrvDBRetCon
          Left = 353
          Top = 96
          Width = 70
          Height = 21
          Margins.Left = 0
          Margins.Top = 0
          Margins.Right = 0
          Margins.Bottom = 0
          TabStop = False
          BevelInner = bvLowered
          BevelKind = bkFlat
          BevelWidth = 2
          BorderStyle = bsNone
          DataField = 'CdEstDes'
          DataSource = DsT002
          Font.Charset = DEFAULT_CHARSET
          Font.Color = clWindowText
          Font.Height = -11
          Font.Name = 'Tahoma'
          Font.Style = [fsBold]
          ParentColor = True
          ParentFont = False
          ReadOnly = True
          TabOrder = 5
        end
        object BrvDBRetCon22: TBrvDBRetCon
          Left = 285
          Top = 123
          Width = 138
          Height = 21
          Margins.Left = 0
          Margins.Top = 0
          Margins.Right = 0
          Margins.Bottom = 0
          TabStop = False
          BevelInner = bvLowered
          BevelKind = bkFlat
          BevelWidth = 2
          BorderStyle = bsNone
          DataField = 'IeDestin'
          DataSource = DsT002
          Font.Charset = DEFAULT_CHARSET
          Font.Color = clWindowText
          Font.Height = -11
          Font.Name = 'Tahoma'
          Font.Style = [fsBold]
          ParentColor = True
          ParentFont = False
          ReadOnly = True
          TabOrder = 6
        end
      end
      object Panel1: TPanel
        Left = 1
        Top = 325
        Width = 868
        Height = 122
        Align = alBottom
        BorderStyle = bsSingle
        TabOrder = 3
        object Label14: TLabel
          Left = 3
          Top = 9
          Width = 66
          Height = 13
          Caption = 'Dt. Entrega'
          Font.Charset = DEFAULT_CHARSET
          Font.Color = clBlack
          Font.Height = -11
          Font.Name = 'MS Sans Serif'
          Font.Style = [fsBold]
          ParentFont = False
        end
        object Label15: TLabel
          Left = 3
          Top = 36
          Width = 84
          Height = 13
          Caption = 'Dt. Rastreador'
          Font.Charset = DEFAULT_CHARSET
          Font.Color = clBlack
          Font.Height = -11
          Font.Name = 'MS Sans Serif'
          Font.Style = [fsBold]
          ParentFont = False
        end
        object Label18: TLabel
          Left = 3
          Top = 61
          Width = 75
          Height = 13
          Caption = 'Observa'#231#245'es'
          Font.Charset = DEFAULT_CHARSET
          Font.Color = clWindowText
          Font.Height = -11
          Font.Name = 'MS Sans Serif'
          Font.Style = [fsBold]
          ParentFont = False
        end
        object Label20: TLabel
          Left = 286
          Top = 9
          Width = 67
          Height = 13
          Caption = 'Ocorr'#234'ncia '
          Font.Charset = DEFAULT_CHARSET
          Font.Color = clBlack
          Font.Height = -11
          Font.Name = 'MS Sans Serif'
          Font.Style = [fsBold]
          ParentFont = False
        end
        object Label21: TLabel
          Left = 286
          Top = 36
          Width = 95
          Height = 13
          Caption = 'Dt. Prevista Ent.'
          Font.Charset = DEFAULT_CHARSET
          Font.Color = clBlack
          Font.Height = -11
          Font.Name = 'MS Sans Serif'
          Font.Style = [fsBold]
          ParentFont = False
        end
        object MemObEntreg: TDBMemo
          Left = 1
          Top = 75
          Width = 862
          Height = 42
          Align = alBottom
          DataField = 'OBENTREG'
          DataSource = DsT002
          TabOrder = 0
        end
        object EdtDtEntreg: TBrvDbEdit
          Left = 98
          Top = 6
          Width = 145
          Height = 21
          DataField = 'DtEntreg'
          DataSource = DsT002
          TabOrder = 1
          BrAlignment = taRightJustify
          BrVisibleButton = True
          BrFunctionButton = TpDbData
          BrDicionario = DmSrvApl.BrvDicionario
          BrQueryConsulta = 0
          BrvReadOnlyColor = 14541539
        end
        object EdtDtEntMot: TBrvDbEdit
          Left = 98
          Top = 33
          Width = 145
          Height = 21
          DataField = 'DtEntMot'
          DataSource = DsT002
          TabOrder = 2
          BrAlignment = taRightJustify
          BrVisibleButton = True
          BrFunctionButton = TpDbDataHora
          BrDicionario = DmSrvApl.BrvDicionario
          BrQueryConsulta = 0
          BrvReadOnlyColor = 14541539
        end
        object EdtCdOcorre: TBrvDbEdit
          Left = 390
          Top = 6
          Width = 70
          Height = 21
          DataField = 'CdOcorre'
          DataSource = DsT002
          TabOrder = 3
          BrAlignment = taRightJustify
          BrVisibleButton = True
          BrFunctionButton = TpDbConsulta
          BrConfigurar.Strings = (
            'CdOcorre;CdOcorre;S;S;'
            'DsOcorr;DsOcorr;S;N;')
          BrDicionario = DmSrvApl.BrvDicionario
          BrQueryConsulta = 13
          BrvReadOnlyColor = 14541539
        end
        object EdtDtPreEnt: TBrvDbEdit
          Left = 390
          Top = 33
          Width = 121
          Height = 21
          DataField = 'DtPreEnt'
          DataSource = DsT002
          TabOrder = 4
          BrAlignment = taRightJustify
          BrVisibleButton = True
          BrFunctionButton = TpDbData
          BrDicionario = DmSrvApl.BrvDicionario
          BrQueryConsulta = 0
          BrvReadOnlyColor = 14541539
        end
        object BtnApaDtE: TBrvBitBtn
          Left = 243
          Top = 4
          Width = 24
          Height = 24
          DoubleBuffered = True
          Font.Charset = DEFAULT_CHARSET
          Font.Color = clWindowText
          Font.Height = -11
          Font.Name = 'Tahoma'
          Font.Style = [fsBold]
          Glyph.Data = {
            96090000424D9609000000000000360000002800000028000000140000000100
            18000000000060090000C40E0000C40E00000000000000000000008080008080
            008080008080008080008084247E71397E69008083007F800080800080800080
            8000808000808000808000808000808000808000808000808000808000808000
            8080008080008080197C7C287B7B008080008080008080008080008080008080
            008080008080008080008080008080008080008080008080008080008080007F
            8431806EB47E3EBD7D3A717F57007F85007F8000808000808000808000808000
            8080008080008080008080008080008080008080008080008080008080237D7D
            7F75758675755079790080800080800080800080800080800080800080800080
            80008080008080008080008080008080008080007F83268074C78340AC8349AB
            834AB98345858256007E88007F80008080008080008080008080008080008080
            008080008080008080008080008080007F7F1C7F7F8F7A7A7C7B7B7B7B7B857A
            7A607D7D007F7F007F7F00808000808000808000808000808000808000808000
            8080008080008080007E83398371C08A4DB18952B08952B08952B08952BE8A4E
            7C8760007F82007F800080800080800080800080800080800080800080800080
            80008080007F7F2A80808E82828281818181818282828282828D82825B818100
            7F7F007F7F008080008080008080008080008080008080008080008080007E82
            298378C69156B7905AB5905BB5905BB5905BB5905BB5905BC59157808C66007C
            85007F80008080008080008080008080008080008080008080007E7E1E828294
            88888A8888888888888888888888888888888888948989608686007E7E007F7F
            008080008080008080008080008080008080007E82378676D7975BBA9560B994
            61B99461B99461B99461B99461B99461B99461C9965D95936300847A00807F00
            8080008080008080008080008080007E7E2A8484A38E8E8D8C8C8C8C8C8C8C8C
            8C8C8C8C8C8C8C8C8C8C8C8C8C8C8C988D8D6E8B8B008080007F7F0080800080
            800080800080800080802A857AC99962BD9764BB9764BB9764BB9764BB9764BB
            9764BB9764BB9764BB9764C49C5EAA867A094DBF00867800807F008080008080
            008080007F7F2584849A9090908F8F8F8F8F8F8F8F8F8F8F8F8F8F8F8F8F8F8F
            8F8F8F8F8F8F8F91919191868650797900808000808000808000808000808000
            8080428A78D79E65BE9B68BE9B68BE9B68BE9B68BE9B68BE9B68BE9B68BE9B68
            C9A160A28B7B0E36E00B2CEB0B46CA008C6F00807F008080008080007F7F3A87
            87A6959593939393939393939393939393939393939393939393939395959588
            88887575758277775E7A7A008080008080008080008080008080007E81819472
            D0A06AC09E6CC09E6CC09E6CC09E6CC09E6CC09E6CCAA465998885173CDD0934
            E60F38E21132EA0B4DC400847900807F008080007F7F007E7E658F8FA3989896
            96969696969696969595959696969696969999998989897777777979797A7A7A
            847979567C7C008080008080008080008080007F80007D81979973D2A26EC2A0
            6FC2A06FC2A06FC2A06FCDA668B599780D39E40A37E7103BE3103BE3103BE311
            34EC0B50C6008A6F00807F008080007F7F007D7D769393A59A9A989898989898
            9898989898989B9B9B8F8F8F7777777C7C7C7B7B7B7B7B7B7B7B7B867A7A577D
            7D008080008080008080008080007F80007A828A9977D6A672C4A373C4A373D0
            AA6C9E8E8C0F3DE60D3BE9123EE5123EE5123EE5123EE5123EE51338EE0E4DCF
            00857600807F008080007F7F007B7B6D9393A99E9E9B9B9B9B9B9B9E9E9E8E8E
            8E7A7A7A7D7D7D7D7D7D7D7D7D7D7D7D7D7D7D7D7D7D897E7E617E7E00808000
            8080008080008080007F80007D808B9A79D7A975CFAA70AA96881946E30D3FEB
            1242E71242E71242E71242E71242E71242E71242E7143CF10C57C50084780080
            80008080007F7F007E7E6E9494ABA0A0A0A0A09393937E7E7E7F7F7F7F7F7F7F
            7F7F8080807F7F7F7F7F7F7F7F7F7F7F7F8B7F7F558080007F7F008080008080
            008080007F80007981A0A177B6988E1546EA0E43ED1446E91446E91446E91446
            E91446E91446E91446E91446E91543EF134CDF00857600808000808000808000
            7F7F007A7A7D9999A196967E7E7E818181818181818181818181828282828282
            818181818181828282888282758181007F7F008080008080008080008080007F
            80008278105CC81444F8174BEC174BEC174BEC174BEC174BEC174BEC174BEC17
            4BEC1749EF174AEE017C8600817D008080008080008080008080007F7F007F7F
            5982829085858585858585858484848484848484848585858484848585858885
            85878585078080007F7F00808000808000808000808000808000807F00847613
            59D61A4AF7184EEE184EEE184EEE184EEE184EEE184EEE194DF11B4AF8017D86
            00827B008080008080008080008080008080008080007F7F007F7F6986869287
            878686868787878787878787878585858686868A8787928787078080007F7F00
            808000808000808000808000808000808000808000807F00876D1563D01F54FD
            1C57F31C57F31C57F31C57F31D57F61D58F300808200817B0080800080800080
            80008080008080008080008080008080007F7F007E7E6288889A8E8E8B8B8B8B
            8B8B8C8C8C8D8D8D8F8C8C8C8C8C028080007F7F008080008080008080008080
            00808000808000808000808000808000807F0082781769D3235DFF2060F72060
            F7215FFA235DFF007F8100817B00808000808000808000808000808000808000
            8080008080008080008080007F7F007F7F668C8C9D9393929292929292969292
            9F9393018080007F7F0080800080800080800080800080800080800080800080
            8000808000808000808000807F00846B1B6DE02766FF2567FF2568FE027E8700
            817A008080008080008080008080008080008080008080008080008080008080
            008080008080007F7F007C7C759292A499999E98989A9797088181007F7F0080
            8000808000808000808000808000808000808000808000808000808000808000
            808000808000807F0082741973D4226EF2027F8700817A008080008080008080
            0080800080800080800080800080800080800080800080800080800080800080
            80007F7F007D7D6791918B9797088181007E7E00808000808000808000808000
            8080008080008080008080008080008080008080008080008080008080008080
            00807F00817600817400807D0080800080800080800080800080800080800080
            80008080008080008080008080008080008080008080008080008080007F7F00
            7E7E007D7D007F7F008080008080008080008080008080008080}
          NumGlyphs = 2
          ParentDoubleBuffered = False
          ParentFont = False
          ParentShowHint = False
          ShowHint = True
          TabOrder = 5
          OnClick = BtnApaDtEClick
          BrTipoBotao = BrBtnApagar
        end
        object BtnApaDtM: TBrvBitBtn
          Left = 243
          Top = 28
          Width = 24
          Height = 24
          DoubleBuffered = True
          Font.Charset = DEFAULT_CHARSET
          Font.Color = clWindowText
          Font.Height = -11
          Font.Name = 'Tahoma'
          Font.Style = [fsBold]
          Glyph.Data = {
            96090000424D9609000000000000360000002800000028000000140000000100
            18000000000060090000C40E0000C40E00000000000000000000008080008080
            008080008080008080008084247E71397E69008083007F800080800080800080
            8000808000808000808000808000808000808000808000808000808000808000
            8080008080008080197C7C287B7B008080008080008080008080008080008080
            008080008080008080008080008080008080008080008080008080008080007F
            8431806EB47E3EBD7D3A717F57007F85007F8000808000808000808000808000
            8080008080008080008080008080008080008080008080008080008080237D7D
            7F75758675755079790080800080800080800080800080800080800080800080
            80008080008080008080008080008080008080007F83268074C78340AC8349AB
            834AB98345858256007E88007F80008080008080008080008080008080008080
            008080008080008080008080008080007F7F1C7F7F8F7A7A7C7B7B7B7B7B857A
            7A607D7D007F7F007F7F00808000808000808000808000808000808000808000
            8080008080008080007E83398371C08A4DB18952B08952B08952B08952BE8A4E
            7C8760007F82007F800080800080800080800080800080800080800080800080
            80008080007F7F2A80808E82828281818181818282828282828D82825B818100
            7F7F007F7F008080008080008080008080008080008080008080008080007E82
            298378C69156B7905AB5905BB5905BB5905BB5905BB5905BC59157808C66007C
            85007F80008080008080008080008080008080008080008080007E7E1E828294
            88888A8888888888888888888888888888888888948989608686007E7E007F7F
            008080008080008080008080008080008080007E82378676D7975BBA9560B994
            61B99461B99461B99461B99461B99461B99461C9965D95936300847A00807F00
            8080008080008080008080008080007E7E2A8484A38E8E8D8C8C8C8C8C8C8C8C
            8C8C8C8C8C8C8C8C8C8C8C8C8C8C8C988D8D6E8B8B008080007F7F0080800080
            800080800080800080802A857AC99962BD9764BB9764BB9764BB9764BB9764BB
            9764BB9764BB9764BB9764C49C5EAA867A094DBF00867800807F008080008080
            008080007F7F2584849A9090908F8F8F8F8F8F8F8F8F8F8F8F8F8F8F8F8F8F8F
            8F8F8F8F8F8F8F91919191868650797900808000808000808000808000808000
            8080428A78D79E65BE9B68BE9B68BE9B68BE9B68BE9B68BE9B68BE9B68BE9B68
            C9A160A28B7B0E36E00B2CEB0B46CA008C6F00807F008080008080007F7F3A87
            87A6959593939393939393939393939393939393939393939393939395959588
            88887575758277775E7A7A008080008080008080008080008080007E81819472
            D0A06AC09E6CC09E6CC09E6CC09E6CC09E6CC09E6CCAA465998885173CDD0934
            E60F38E21132EA0B4DC400847900807F008080007F7F007E7E658F8FA3989896
            96969696969696969595959696969696969999998989897777777979797A7A7A
            847979567C7C008080008080008080008080007F80007D81979973D2A26EC2A0
            6FC2A06FC2A06FC2A06FCDA668B599780D39E40A37E7103BE3103BE3103BE311
            34EC0B50C6008A6F00807F008080007F7F007D7D769393A59A9A989898989898
            9898989898989B9B9B8F8F8F7777777C7C7C7B7B7B7B7B7B7B7B7B867A7A577D
            7D008080008080008080008080007F80007A828A9977D6A672C4A373C4A373D0
            AA6C9E8E8C0F3DE60D3BE9123EE5123EE5123EE5123EE5123EE51338EE0E4DCF
            00857600807F008080007F7F007B7B6D9393A99E9E9B9B9B9B9B9B9E9E9E8E8E
            8E7A7A7A7D7D7D7D7D7D7D7D7D7D7D7D7D7D7D7D7D7D897E7E617E7E00808000
            8080008080008080007F80007D808B9A79D7A975CFAA70AA96881946E30D3FEB
            1242E71242E71242E71242E71242E71242E71242E7143CF10C57C50084780080
            80008080007F7F007E7E6E9494ABA0A0A0A0A09393937E7E7E7F7F7F7F7F7F7F
            7F7F8080807F7F7F7F7F7F7F7F7F7F7F7F8B7F7F558080007F7F008080008080
            008080007F80007981A0A177B6988E1546EA0E43ED1446E91446E91446E91446
            E91446E91446E91446E91446E91543EF134CDF00857600808000808000808000
            7F7F007A7A7D9999A196967E7E7E818181818181818181818181828282828282
            818181818181828282888282758181007F7F008080008080008080008080007F
            80008278105CC81444F8174BEC174BEC174BEC174BEC174BEC174BEC174BEC17
            4BEC1749EF174AEE017C8600817D008080008080008080008080007F7F007F7F
            5982829085858585858585858484848484848484848585858484848585858885
            85878585078080007F7F00808000808000808000808000808000807F00847613
            59D61A4AF7184EEE184EEE184EEE184EEE184EEE184EEE194DF11B4AF8017D86
            00827B008080008080008080008080008080008080007F7F007F7F6986869287
            878686868787878787878787878585858686868A8787928787078080007F7F00
            808000808000808000808000808000808000808000807F00876D1563D01F54FD
            1C57F31C57F31C57F31C57F31D57F61D58F300808200817B0080800080800080
            80008080008080008080008080008080007F7F007E7E6288889A8E8E8B8B8B8B
            8B8B8C8C8C8D8D8D8F8C8C8C8C8C028080007F7F008080008080008080008080
            00808000808000808000808000808000807F0082781769D3235DFF2060F72060
            F7215FFA235DFF007F8100817B00808000808000808000808000808000808000
            8080008080008080008080007F7F007F7F668C8C9D9393929292929292969292
            9F9393018080007F7F0080800080800080800080800080800080800080800080
            8000808000808000808000807F00846B1B6DE02766FF2567FF2568FE027E8700
            817A008080008080008080008080008080008080008080008080008080008080
            008080008080007F7F007C7C759292A499999E98989A9797088181007F7F0080
            8000808000808000808000808000808000808000808000808000808000808000
            808000808000807F0082741973D4226EF2027F8700817A008080008080008080
            0080800080800080800080800080800080800080800080800080800080800080
            80007F7F007D7D6791918B9797088181007E7E00808000808000808000808000
            8080008080008080008080008080008080008080008080008080008080008080
            00807F00817600817400807D0080800080800080800080800080800080800080
            80008080008080008080008080008080008080008080008080008080007F7F00
            7E7E007D7D007F7F008080008080008080008080008080008080}
          NumGlyphs = 2
          ParentDoubleBuffered = False
          ParentFont = False
          ParentShowHint = False
          ShowHint = True
          TabOrder = 6
          OnClick = BtnApaDtMClick
          BrTipoBotao = BrBtnApagar
        end
        object BtnApaDtP: TBrvBitBtn
          Left = 511
          Top = 31
          Width = 24
          Height = 24
          DoubleBuffered = True
          Font.Charset = DEFAULT_CHARSET
          Font.Color = clWindowText
          Font.Height = -11
          Font.Name = 'Tahoma'
          Font.Style = [fsBold]
          Glyph.Data = {
            96090000424D9609000000000000360000002800000028000000140000000100
            18000000000060090000C40E0000C40E00000000000000000000008080008080
            008080008080008080008084247E71397E69008083007F800080800080800080
            8000808000808000808000808000808000808000808000808000808000808000
            8080008080008080197C7C287B7B008080008080008080008080008080008080
            008080008080008080008080008080008080008080008080008080008080007F
            8431806EB47E3EBD7D3A717F57007F85007F8000808000808000808000808000
            8080008080008080008080008080008080008080008080008080008080237D7D
            7F75758675755079790080800080800080800080800080800080800080800080
            80008080008080008080008080008080008080007F83268074C78340AC8349AB
            834AB98345858256007E88007F80008080008080008080008080008080008080
            008080008080008080008080008080007F7F1C7F7F8F7A7A7C7B7B7B7B7B857A
            7A607D7D007F7F007F7F00808000808000808000808000808000808000808000
            8080008080008080007E83398371C08A4DB18952B08952B08952B08952BE8A4E
            7C8760007F82007F800080800080800080800080800080800080800080800080
            80008080007F7F2A80808E82828281818181818282828282828D82825B818100
            7F7F007F7F008080008080008080008080008080008080008080008080007E82
            298378C69156B7905AB5905BB5905BB5905BB5905BB5905BC59157808C66007C
            85007F80008080008080008080008080008080008080008080007E7E1E828294
            88888A8888888888888888888888888888888888948989608686007E7E007F7F
            008080008080008080008080008080008080007E82378676D7975BBA9560B994
            61B99461B99461B99461B99461B99461B99461C9965D95936300847A00807F00
            8080008080008080008080008080007E7E2A8484A38E8E8D8C8C8C8C8C8C8C8C
            8C8C8C8C8C8C8C8C8C8C8C8C8C8C8C988D8D6E8B8B008080007F7F0080800080
            800080800080800080802A857AC99962BD9764BB9764BB9764BB9764BB9764BB
            9764BB9764BB9764BB9764C49C5EAA867A094DBF00867800807F008080008080
            008080007F7F2584849A9090908F8F8F8F8F8F8F8F8F8F8F8F8F8F8F8F8F8F8F
            8F8F8F8F8F8F8F91919191868650797900808000808000808000808000808000
            8080428A78D79E65BE9B68BE9B68BE9B68BE9B68BE9B68BE9B68BE9B68BE9B68
            C9A160A28B7B0E36E00B2CEB0B46CA008C6F00807F008080008080007F7F3A87
            87A6959593939393939393939393939393939393939393939393939395959588
            88887575758277775E7A7A008080008080008080008080008080007E81819472
            D0A06AC09E6CC09E6CC09E6CC09E6CC09E6CC09E6CCAA465998885173CDD0934
            E60F38E21132EA0B4DC400847900807F008080007F7F007E7E658F8FA3989896
            96969696969696969595959696969696969999998989897777777979797A7A7A
            847979567C7C008080008080008080008080007F80007D81979973D2A26EC2A0
            6FC2A06FC2A06FC2A06FCDA668B599780D39E40A37E7103BE3103BE3103BE311
            34EC0B50C6008A6F00807F008080007F7F007D7D769393A59A9A989898989898
            9898989898989B9B9B8F8F8F7777777C7C7C7B7B7B7B7B7B7B7B7B867A7A577D
            7D008080008080008080008080007F80007A828A9977D6A672C4A373C4A373D0
            AA6C9E8E8C0F3DE60D3BE9123EE5123EE5123EE5123EE5123EE51338EE0E4DCF
            00857600807F008080007F7F007B7B6D9393A99E9E9B9B9B9B9B9B9E9E9E8E8E
            8E7A7A7A7D7D7D7D7D7D7D7D7D7D7D7D7D7D7D7D7D7D897E7E617E7E00808000
            8080008080008080007F80007D808B9A79D7A975CFAA70AA96881946E30D3FEB
            1242E71242E71242E71242E71242E71242E71242E7143CF10C57C50084780080
            80008080007F7F007E7E6E9494ABA0A0A0A0A09393937E7E7E7F7F7F7F7F7F7F
            7F7F8080807F7F7F7F7F7F7F7F7F7F7F7F8B7F7F558080007F7F008080008080
            008080007F80007981A0A177B6988E1546EA0E43ED1446E91446E91446E91446
            E91446E91446E91446E91446E91543EF134CDF00857600808000808000808000
            7F7F007A7A7D9999A196967E7E7E818181818181818181818181828282828282
            818181818181828282888282758181007F7F008080008080008080008080007F
            80008278105CC81444F8174BEC174BEC174BEC174BEC174BEC174BEC174BEC17
            4BEC1749EF174AEE017C8600817D008080008080008080008080007F7F007F7F
            5982829085858585858585858484848484848484848585858484848585858885
            85878585078080007F7F00808000808000808000808000808000807F00847613
            59D61A4AF7184EEE184EEE184EEE184EEE184EEE184EEE194DF11B4AF8017D86
            00827B008080008080008080008080008080008080007F7F007F7F6986869287
            878686868787878787878787878585858686868A8787928787078080007F7F00
            808000808000808000808000808000808000808000807F00876D1563D01F54FD
            1C57F31C57F31C57F31C57F31D57F61D58F300808200817B0080800080800080
            80008080008080008080008080008080007F7F007E7E6288889A8E8E8B8B8B8B
            8B8B8C8C8C8D8D8D8F8C8C8C8C8C028080007F7F008080008080008080008080
            00808000808000808000808000808000807F0082781769D3235DFF2060F72060
            F7215FFA235DFF007F8100817B00808000808000808000808000808000808000
            8080008080008080008080007F7F007F7F668C8C9D9393929292929292969292
            9F9393018080007F7F0080800080800080800080800080800080800080800080
            8000808000808000808000807F00846B1B6DE02766FF2567FF2568FE027E8700
            817A008080008080008080008080008080008080008080008080008080008080
            008080008080007F7F007C7C759292A499999E98989A9797088181007F7F0080
            8000808000808000808000808000808000808000808000808000808000808000
            808000808000807F0082741973D4226EF2027F8700817A008080008080008080
            0080800080800080800080800080800080800080800080800080800080800080
            80007F7F007D7D6791918B9797088181007E7E00808000808000808000808000
            8080008080008080008080008080008080008080008080008080008080008080
            00807F00817600817400807D0080800080800080800080800080800080800080
            80008080008080008080008080008080008080008080008080008080007F7F00
            7E7E007D7D007F7F008080008080008080008080008080008080}
          NumGlyphs = 2
          ParentDoubleBuffered = False
          ParentFont = False
          ParentShowHint = False
          ShowHint = True
          TabOrder = 7
          OnClick = BtnApaDtPClick
          BrTipoBotao = BrBtnApagar
        end
        object BrvDBRetCon23: TBrvDBRetCon
          Left = 466
          Top = 6
          Width = 389
          Height = 21
          Margins.Left = 0
          Margins.Top = 0
          Margins.Right = 0
          Margins.Bottom = 0
          TabStop = False
          BevelInner = bvLowered
          BevelKind = bkFlat
          BevelWidth = 2
          BorderStyle = bsNone
          DataField = 'DsOcorr'
          DataSource = DsT002
          Font.Charset = DEFAULT_CHARSET
          Font.Color = clWindowText
          Font.Height = -11
          Font.Name = 'Tahoma'
          Font.Style = [fsBold]
          ParentColor = True
          ParentFont = False
          ReadOnly = True
          TabOrder = 8
        end
      end
      object GroupBox2: TGroupBox
        Left = 1
        Top = 171
        Width = 434
        Height = 154
        Align = alLeft
        Caption = 'Remetente'
        Font.Charset = DEFAULT_CHARSET
        Font.Color = clWindowText
        Font.Height = -11
        Font.Name = 'Tahoma'
        Font.Style = [fsBold]
        ParentFont = False
        TabOrder = 4
        object Label5: TLabel
          Left = 5
          Top = 18
          Width = 62
          Height = 13
          Caption = 'Remetente'
          Font.Charset = DEFAULT_CHARSET
          Font.Color = clBlack
          Font.Height = -11
          Font.Name = 'MS Sans Serif'
          Font.Style = [fsBold]
          ParentFont = False
        end
        object Label6: TLabel
          Left = 5
          Top = 45
          Width = 55
          Height = 13
          Caption = 'Endere'#231'o'
          Font.Charset = DEFAULT_CHARSET
          Font.Color = clWindowText
          Font.Height = -11
          Font.Name = 'MS Sans Serif'
          Font.Style = [fsBold]
          ParentFont = False
        end
        object Label7: TLabel
          Left = 5
          Top = 72
          Width = 34
          Height = 13
          Caption = 'Bairro'
          Font.Charset = DEFAULT_CHARSET
          Font.Color = clWindowText
          Font.Height = -11
          Font.Name = 'MS Sans Serif'
          Font.Style = [fsBold]
          ParentFont = False
        end
        object Label8: TLabel
          Left = 5
          Top = 99
          Width = 57
          Height = 13
          Caption = 'Munic'#237'pio'
          Font.Charset = DEFAULT_CHARSET
          Font.Color = clWindowText
          Font.Height = -11
          Font.Name = 'MS Sans Serif'
          Font.Style = [fsBold]
          ParentFont = False
        end
        object Label10: TLabel
          Left = 5
          Top = 126
          Width = 77
          Height = 13
          Caption = 'C.N.P.J (M.F)'
          Font.Charset = DEFAULT_CHARSET
          Font.Color = clWindowText
          Font.Height = -11
          Font.Name = 'MS Sans Serif'
          Font.Style = [fsBold]
          ParentFont = False
        end
        object Label9: TLabel
          Left = 324
          Top = 99
          Width = 23
          Height = 13
          Caption = 'Est:'
          Font.Charset = DEFAULT_CHARSET
          Font.Color = clWindowText
          Font.Height = -11
          Font.Name = 'MS Sans Serif'
          Font.Style = [fsBold]
          ParentFont = False
        end
        object Label12: TLabel
          Left = 266
          Top = 126
          Width = 13
          Height = 13
          Caption = 'IE'
          Font.Charset = DEFAULT_CHARSET
          Font.Color = clWindowText
          Font.Height = -11
          Font.Name = 'MS Sans Serif'
          Font.Style = [fsBold]
          ParentFont = False
        end
        object BrvDBRetCon9: TBrvDBRetCon
          Left = 91
          Top = 15
          Width = 332
          Height = 21
          Margins.Left = 0
          Margins.Top = 0
          Margins.Right = 0
          Margins.Bottom = 0
          TabStop = False
          BevelInner = bvLowered
          BevelKind = bkFlat
          BevelWidth = 2
          BorderStyle = bsNone
          DataField = 'RsRemete'
          DataSource = DsT002
          Font.Charset = DEFAULT_CHARSET
          Font.Color = clWindowText
          Font.Height = -11
          Font.Name = 'Tahoma'
          Font.Style = [fsBold]
          ParentColor = True
          ParentFont = False
          ReadOnly = True
          TabOrder = 0
        end
        object BrvDBRetCon10: TBrvDBRetCon
          Left = 91
          Top = 42
          Width = 332
          Height = 21
          Margins.Left = 0
          Margins.Top = 0
          Margins.Right = 0
          Margins.Bottom = 0
          TabStop = False
          BevelInner = bvLowered
          BevelKind = bkFlat
          BevelWidth = 2
          BorderStyle = bsNone
          DataField = 'EdRemete'
          DataSource = DsT002
          Font.Charset = DEFAULT_CHARSET
          Font.Color = clWindowText
          Font.Height = -11
          Font.Name = 'Tahoma'
          Font.Style = [fsBold]
          ParentColor = True
          ParentFont = False
          ReadOnly = True
          TabOrder = 1
        end
        object BrvDBRetCon11: TBrvDBRetCon
          Left = 91
          Top = 69
          Width = 332
          Height = 21
          Margins.Left = 0
          Margins.Top = 0
          Margins.Right = 0
          Margins.Bottom = 0
          TabStop = False
          BevelInner = bvLowered
          BevelKind = bkFlat
          BevelWidth = 2
          BorderStyle = bsNone
          DataField = 'BiRemete'
          DataSource = DsT002
          Font.Charset = DEFAULT_CHARSET
          Font.Color = clWindowText
          Font.Height = -11
          Font.Name = 'Tahoma'
          Font.Style = [fsBold]
          ParentColor = True
          ParentFont = False
          ReadOnly = True
          TabOrder = 2
        end
        object BrvDBRetCon12: TBrvDBRetCon
          Left = 91
          Top = 96
          Width = 226
          Height = 21
          Margins.Left = 0
          Margins.Top = 0
          Margins.Right = 0
          Margins.Bottom = 0
          TabStop = False
          BevelInner = bvLowered
          BevelKind = bkFlat
          BevelWidth = 2
          BorderStyle = bsNone
          DataField = 'NmCidRem'
          DataSource = DsT002
          Font.Charset = DEFAULT_CHARSET
          Font.Color = clWindowText
          Font.Height = -11
          Font.Name = 'Tahoma'
          Font.Style = [fsBold]
          ParentColor = True
          ParentFont = False
          ReadOnly = True
          TabOrder = 3
        end
        object BrvDBRetCon13: TBrvDBRetCon
          Left = 353
          Top = 96
          Width = 70
          Height = 21
          Margins.Left = 0
          Margins.Top = 0
          Margins.Right = 0
          Margins.Bottom = 0
          TabStop = False
          BevelInner = bvLowered
          BevelKind = bkFlat
          BevelWidth = 2
          BorderStyle = bsNone
          DataField = 'CdEstRem'
          DataSource = DsT002
          Font.Charset = DEFAULT_CHARSET
          Font.Color = clWindowText
          Font.Height = -11
          Font.Name = 'Tahoma'
          Font.Style = [fsBold]
          ParentColor = True
          ParentFont = False
          ReadOnly = True
          TabOrder = 4
        end
        object BrvDBRetCon14: TBrvDBRetCon
          Left = 91
          Top = 123
          Width = 167
          Height = 21
          Margins.Left = 0
          Margins.Top = 0
          Margins.Right = 0
          Margins.Bottom = 0
          TabStop = False
          BevelInner = bvLowered
          BevelKind = bkFlat
          BevelWidth = 2
          BorderStyle = bsNone
          DataField = 'CgcRemet'
          DataSource = DsT002
          Font.Charset = DEFAULT_CHARSET
          Font.Color = clWindowText
          Font.Height = -11
          Font.Name = 'Tahoma'
          Font.Style = [fsBold]
          ParentColor = True
          ParentFont = False
          ReadOnly = True
          TabOrder = 5
        end
        object BrvDBRetCon15: TBrvDBRetCon
          Left = 285
          Top = 123
          Width = 138
          Height = 21
          Margins.Left = 0
          Margins.Top = 0
          Margins.Right = 0
          Margins.Bottom = 0
          TabStop = False
          BevelInner = bvLowered
          BevelKind = bkFlat
          BevelWidth = 2
          BorderStyle = bsNone
          DataField = 'IeRemete'
          DataSource = DsT002
          Font.Charset = DEFAULT_CHARSET
          Font.Color = clWindowText
          Font.Height = -11
          Font.Name = 'Tahoma'
          Font.Style = [fsBold]
          ParentColor = True
          ParentFont = False
          ReadOnly = True
          TabOrder = 6
        end
      end
      object Panel2: TPanel
        Left = 1
        Top = 106
        Width = 868
        Height = 65
        Align = alTop
        BorderStyle = bsSingle
        TabOrder = 5
        object Label23: TLabel
          Left = 3
          Top = 9
          Width = 68
          Height = 13
          Caption = 'Dt. Emiss'#227'o'
          Font.Charset = DEFAULT_CHARSET
          Font.Color = clBlack
          Font.Height = -11
          Font.Name = 'MS Sans Serif'
          Font.Style = [fsBold]
          ParentFont = False
        end
        object Label43: TLabel
          Left = 3
          Top = 36
          Width = 45
          Height = 13
          Caption = 'Ve'#237'culo'
          Font.Charset = DEFAULT_CHARSET
          Font.Color = clWindowText
          Font.Height = -11
          Font.Name = 'MS Sans Serif'
          Font.Style = [fsBold]
          ParentFont = False
        end
        object Label44: TLabel
          Left = 200
          Top = 36
          Width = 55
          Height = 13
          Caption = 'N'#186' Placa '
          Font.Charset = DEFAULT_CHARSET
          Font.Color = clWindowText
          Font.Height = -11
          Font.Name = 'MS Sans Serif'
          Font.Style = [fsBold]
          ParentFont = False
        end
        object Label50: TLabel
          Left = 200
          Top = 9
          Width = 85
          Height = 13
          Caption = 'Frete Pago por'
          Font.Charset = DEFAULT_CHARSET
          Font.Color = clBlack
          Font.Height = -11
          Font.Name = 'MS Sans Serif'
          Font.Style = [fsBold]
          ParentFont = False
        end
        object Label48: TLabel
          Left = 413
          Top = 9
          Width = 51
          Height = 13
          Caption = 'Situa'#231#227'o'
          Font.Charset = DEFAULT_CHARSET
          Font.Color = clWindowText
          Font.Height = -11
          Font.Name = 'MS Sans Serif'
          Font.Style = [fsBold]
          ParentFont = False
        end
        object Label46: TLabel
          Left = 413
          Top = 36
          Width = 17
          Height = 13
          Caption = 'UF'
          Font.Charset = DEFAULT_CHARSET
          Font.Color = clWindowText
          Font.Height = -11
          Font.Name = 'MS Sans Serif'
          Font.Style = [fsBold]
          ParentFont = False
        end
        object Label49: TLabel
          Left = 592
          Top = 9
          Width = 57
          Height = 13
          Caption = 'Cd. Fiscal'
          Font.Charset = DEFAULT_CHARSET
          Font.Color = clWindowText
          Font.Height = -11
          Font.Name = 'MS Sans Serif'
          Font.Style = [fsBold]
          ParentFont = False
        end
        object Label51: TLabel
          Left = 592
          Top = 36
          Width = 55
          Height = 13
          Caption = 'Fatura N'#186
          Font.Charset = DEFAULT_CHARSET
          Font.Color = clWindowText
          Font.Height = -11
          Font.Name = 'MS Sans Serif'
          Font.Style = [fsBold]
          ParentFont = False
        end
        object BrvDBRetCon1: TBrvDBRetCon
          Left = 77
          Top = 6
          Width = 110
          Height = 21
          Margins.Left = 0
          Margins.Top = 0
          Margins.Right = 0
          Margins.Bottom = 0
          TabStop = False
          BevelInner = bvLowered
          BevelKind = bkFlat
          BevelWidth = 2
          BorderStyle = bsNone
          DataField = 'DtEmissa'
          DataSource = DsT002
          Font.Charset = DEFAULT_CHARSET
          Font.Color = clWindowText
          Font.Height = -11
          Font.Name = 'Tahoma'
          Font.Style = [fsBold]
          ParentColor = True
          ParentFont = False
          ReadOnly = True
          TabOrder = 0
        end
        object BrvDBRetCon2: TBrvDBRetCon
          Left = 77
          Top = 33
          Width = 110
          Height = 21
          Margins.Left = 0
          Margins.Top = 0
          Margins.Right = 0
          Margins.Bottom = 0
          TabStop = False
          BevelInner = bvLowered
          BevelKind = bkFlat
          BevelWidth = 2
          BorderStyle = bsNone
          DataField = 'DsVeicul'
          DataSource = DsT002
          Font.Charset = DEFAULT_CHARSET
          Font.Color = clWindowText
          Font.Height = -11
          Font.Name = 'Tahoma'
          Font.Style = [fsBold]
          ParentColor = True
          ParentFont = False
          ReadOnly = True
          TabOrder = 1
        end
        object BrvDBRetCon3: TBrvDBRetCon
          Left = 291
          Top = 6
          Width = 110
          Height = 21
          Margins.Left = 0
          Margins.Top = 0
          Margins.Right = 0
          Margins.Bottom = 0
          TabStop = False
          BevelInner = bvLowered
          BevelKind = bkFlat
          BevelWidth = 2
          BorderStyle = bsNone
          DataField = 'SnFrete'
          DataSource = DsT002
          Font.Charset = DEFAULT_CHARSET
          Font.Color = clWindowText
          Font.Height = -11
          Font.Name = 'Tahoma'
          Font.Style = [fsBold]
          ParentColor = True
          ParentFont = False
          ReadOnly = True
          TabOrder = 2
        end
        object BrvDBRetCon4: TBrvDBRetCon
          Left = 291
          Top = 33
          Width = 110
          Height = 21
          Margins.Left = 0
          Margins.Top = 0
          Margins.Right = 0
          Margins.Bottom = 0
          TabStop = False
          BevelInner = bvLowered
          BevelKind = bkFlat
          BevelWidth = 2
          BorderStyle = bsNone
          DataField = 'NRPlavei'
          DataSource = DsT002
          Font.Charset = DEFAULT_CHARSET
          Font.Color = clWindowText
          Font.Height = -11
          Font.Name = 'Tahoma'
          Font.Style = [fsBold]
          ParentColor = True
          ParentFont = False
          ReadOnly = True
          TabOrder = 3
        end
        object BrvDBRetCon5: TBrvDBRetCon
          Left = 470
          Top = 6
          Width = 110
          Height = 21
          Margins.Left = 0
          Margins.Top = 0
          Margins.Right = 0
          Margins.Bottom = 0
          TabStop = False
          BevelInner = bvLowered
          BevelKind = bkFlat
          BevelWidth = 2
          BorderStyle = bsNone
          DataField = 'StCtrc'
          DataSource = DsT002
          Font.Charset = DEFAULT_CHARSET
          Font.Color = clWindowText
          Font.Height = -11
          Font.Name = 'Tahoma'
          Font.Style = [fsBold]
          ParentColor = True
          ParentFont = False
          ReadOnly = True
          TabOrder = 4
        end
        object BrvDBRetCon6: TBrvDBRetCon
          Left = 470
          Top = 33
          Width = 110
          Height = 21
          Margins.Left = 0
          Margins.Top = 0
          Margins.Right = 0
          Margins.Bottom = 0
          TabStop = False
          BevelInner = bvLowered
          BevelKind = bkFlat
          BevelWidth = 2
          BorderStyle = bsNone
          DataField = 'CdEstVei'
          DataSource = DsT002
          Font.Charset = DEFAULT_CHARSET
          Font.Color = clWindowText
          Font.Height = -11
          Font.Name = 'Tahoma'
          Font.Style = [fsBold]
          ParentColor = True
          ParentFont = False
          ReadOnly = True
          TabOrder = 5
        end
        object BrvDBRetCon7: TBrvDBRetCon
          Left = 655
          Top = 6
          Width = 110
          Height = 21
          Margins.Left = 0
          Margins.Top = 0
          Margins.Right = 0
          Margins.Bottom = 0
          TabStop = False
          BevelInner = bvLowered
          BevelKind = bkFlat
          BevelWidth = 2
          BorderStyle = bsNone
          DataField = 'CdFiscal'
          DataSource = DsT002
          Font.Charset = DEFAULT_CHARSET
          Font.Color = clWindowText
          Font.Height = -11
          Font.Name = 'Tahoma'
          Font.Style = [fsBold]
          ParentColor = True
          ParentFont = False
          ReadOnly = True
          TabOrder = 6
        end
        object BrvDBRetCon8: TBrvDBRetCon
          Left = 655
          Top = 33
          Width = 110
          Height = 21
          Margins.Left = 0
          Margins.Top = 0
          Margins.Right = 0
          Margins.Bottom = 0
          TabStop = False
          BevelInner = bvLowered
          BevelKind = bkFlat
          BevelWidth = 2
          BorderStyle = bsNone
          DataField = 'NRFatura'
          DataSource = DsT002
          Font.Charset = DEFAULT_CHARSET
          Font.Color = clWindowText
          Font.Height = -11
          Font.Name = 'Tahoma'
          Font.Style = [fsBold]
          ParentColor = True
          ParentFont = False
          ReadOnly = True
          TabOrder = 7
        end
      end
    end
  end
  inherited PopCfgFrm: TPopupMenu
    Left = 119
    Top = 129
  end
  inherited ImlPopFrm: TImageList
    Height = 16
    Width = 16
    Left = 63
    Top = 129
    Bitmap = {
      494C0101030005001C0010001000FFFFFFFFFF10FFFFFFFFFFFFFFFF424D3600
      0000000000003600000028000000400000001000000001002000000000000010
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
      0000000000000000000000000000000000000000000000000000000000000000
      0000000000000000000000000000000000000000000000000000000000000000
      0000000000000000000000000000000000000000000000000000000000000000
      0000000000000000000000000000000000000000000000000000000000000000
      0000000000000000000000000000000000000000000000000000000000000000
      00000000000000000000000000007F007F007F007F007F7F7F00000000000000
      0000000000000000000000000000000000000000000000000000000000000000
      0000000000000000000000000000000000000000000000000000000000000000
      00000000000000000000000000000000000000000000000000007F7F7F000000
      0000000000000000000000000000000000000000000000000000000000000000
      0000000000000000000000000000000000000000000000000000000000000000
      0000000000000000000000000000000000000000000000000000000000000000
      0000000000000000000000000000000000000000000000000000000000000000
      0000000000007F007F007F007F00FFFFFF00FFFFFF00BFBFBF007F7F7F000000
      000000000000000000000000000000000000000000000000000000000000FFFF
      FF00FFFFFF00FFFFFF00FFFFFF00FFFFFF00FFFFFF00FFFFFF00FFFFFF00FFFF
      FF00FFFFFF0000000000000000000000000000000000000000007F7F7F00FFFF
      FF00FFFFFF00FFFFFF00FFFFFF00FFFFFF00FFFFFF00FFFFFF00FFFFFF00FFFF
      FF00000000000000000000000000000000000000000000000000000000000000
      0000000000000000000000000000000000000000000000000000000000000000
      0000000000000000000000000000000000000000000000000000000000007F00
      7F007F007F00FFFFFF00FFFFFF000000000000000000BFBFBF00BFBFBF007F7F
      7F0000000000000000000000000000000000000000000000000000000000FFFF
      FF00FFFFFF00FFFFFF00FFFFFF00FFFFFF00FFFFFF00FFFFFF00FFFFFF00FFFF
      FF00FFFFFF0000000000000000000000000000000000000000007F7F7F00FFFF
      FF00FFFFFF00FFFFFF00FFFFFF00FFFFFF00FFFFFF00FFFFFF00FFFFFF00FFFF
      FF00000000000000000000000000000000000000000000000000000000000000
      0000000000000000000000000000000000000000000000000000000000000000
      000000000000000000000000000000000000000000007F007F007F007F00FFFF
      FF00FFFFFF0000000000000000007F007F007F007F0000000000BFBFBF00BFBF
      BF007F7F7F00000000000000000000000000000000000000000000000000FFFF
      FF00FFFFFF00FFFFFF0000000000FFFFFF00FFFFFF00FFFFFF00FFFFFF00FFFF
      FF00FFFFFF0000000000000000000000000000000000000000007F7F7F007F00
      00007F0000007F0000007F0000007F0000007F0000007F0000007F0000007F00
      0000000000000000000000000000000000000000000000000000000000000000
      0000000000000000000000000000000000000000000000000000000000000000
      000000000000000000000000000000000000000000007F007F00FFFFFF000000
      0000000000007F007F007F007F007F007F007F007F007F007F0000000000BFBF
      BF00BFBFBF007F7F7F000000000000000000000000000000000000000000FFFF
      FF00FFFFFF0000000000000000000000000000000000FFFFFF00FFFFFF00FFFF
      FF00FFFFFF0000000000000000000000000000000000000000007F7F7F007F00
      0000FFFFFF00FFFFFF00FFFFFF00FFFFFF00FFFFFF00FFFFFF00FFFFFF007F00
      0000000000000000000000000000000000000000000000000000000000000000
      0000000000000000000000000000000000000000000000000000000000000000
      0000000000000000000000000000000000000000000000000000000000007F00
      7F007F007F007F007F00007F7F0000FFFF007F007F007F007F007F007F000000
      0000BFBFBF00BFBFBF007F7F7F0000000000000000000000000000000000FFFF
      FF00FFFFFF0000000000FFFFFF00000000000000000000000000FFFFFF00FFFF
      FF00FFFFFF0000000000000000000000000000000000000000007F7F7F007F00
      00007F0000007F0000007F0000007F0000007F0000007F0000007F0000007F00
      0000000000000000000000000000000000000000000000000000000000000000
      0000000000000000000000000000000000000000000000000000000000000000
      000000000000000000000000000000000000000000007F007F007F007F007F00
      7F007F007F007F007F007F007F00007F7F007F007F007F007F007F007F007F00
      7F0000000000BFBFBF000000000000000000000000000000000000000000FFFF
      FF00FFFFFF0000000000FFFFFF00FFFFFF000000000000000000FFFFFF00FFFF
      FF00FFFFFF0000000000000000000000000000000000000000007F7F7F00FFFF
      FF00FFFFFF00FFFFFF00FFFFFF00FFFFFF00FFFFFF00FFFFFF00FFFFFF00FFFF
      FF00000000000000000000000000000000000000000000000000000000000000
      0000000000000000000000000000000000000000000000000000000000000000
      0000000000000000000000000000000000000000000000000000FFFFFF007F00
      7F007F007F007F007F007F007F007F007F0000FFFF0000FFFF007F007F007F00
      7F007F007F00000000000000000000000000000000000000000000000000FFFF
      FF00FFFFFF00FFFFFF00FFFFFF00FFFFFF00FFFFFF000000000000000000FFFF
      FF00FFFFFF0000000000000000000000000000000000000000007F7F7F00FFFF
      FF00FFFFFF00FFFFFF00FFFFFF00FFFFFF00FFFFFF00FFFFFF00FFFFFF00FFFF
      FF00000000000000000000000000000000000000000000000000000000000000
      0000000000000000000000000000000000000000000000000000000000000000
      000000000000000000000000000000000000000000000000000000000000FFFF
      FF007F007F007F007F007F007F007F007F007F007F00007F7F0000FFFF0000FF
      FF007F007F007F007F000000000000000000000000000000000000000000FFFF
      FF00FFFFFF00FFFFFF00FFFFFF00FFFFFF00FFFFFF00FFFFFF0000000000FFFF
      FF00FFFFFF0000000000000000000000000000000000000000007F7F7F00FFFF
      FF007F0000007F0000007F0000007F000000FFFFFF00FFFFFF00FFFFFF00FFFF
      FF00000000000000000000000000000000000000000000000000000000000000
      0000000000000000000000000000000000000000000000000000000000000000
      0000000000000000000000000000000000000000000000000000000000000000
      0000FFFFFF007F007F007F007F007F007F00007F7F007F007F0000FFFF0000FF
      FF007F007F007F007F007F007F0000000000000000000000000000000000FFFF
      FF00FFFFFF00FFFFFF00FFFFFF00FFFFFF00FFFFFF00FFFFFF00FFFFFF00FFFF
      FF00FFFFFF0000000000000000000000000000000000000000007F7F7F00FFFF
      FF00FFFFFF00FFFFFF00FFFFFF00FFFFFF00FFFFFF00FFFFFF00FFFFFF00FFFF
      FF00000000000000000000000000000000000000000000000000000000000000
      0000000000000000000000000000000000000000000000000000000000000000
      0000000000000000000000000000000000000000000000000000000000000000
      000000000000FFFFFF007F007F007F007F0000FFFF0000FFFF0000FFFF007F00
      7F007F007F007F007F000000000000000000000000000000000000000000FFFF
      FF00FFFFFF00FFFFFF00FFFFFF00FFFFFF00FFFFFF00FFFFFF00FFFFFF00FFFF
      FF00FFFFFF0000000000000000000000000000000000000000007F7F7F00FFFF
      FF007F0000007F0000007F0000007F0000007F000000FFFFFF00FFFFFF00FFFF
      FF00000000000000000000000000000000000000000000000000000000000000
      0000000000000000000000000000000000000000000000000000000000000000
      0000000000000000000000000000000000000000000000000000000000000000
      00000000000000000000FFFFFF007F007F007F007F007F007F007F007F007F00
      7F00000000000000000000000000000000000000000000000000000000000000
      0000000000000000000000000000000000000000000000000000000000000000
      00000000000000000000000000000000000000000000000000007F7F7F00FFFF
      FF00FFFFFF00FFFFFF00FFFFFF00FFFFFF00FFFFFF00FFFFFF00FFFFFF00FFFF
      FF00000000000000000000000000000000000000000000000000000000000000
      0000000000000000000000000000000000000000000000000000000000000000
      0000000000000000000000000000000000000000000000000000000000000000
      0000000000000000000000000000FFFFFF007F007F007F007F00000000000000
      0000000000000000000000000000000000000000000000000000000000000000
      0000000000000000000000000000000000000000000000000000000000000000
      00000000000000000000000000000000000000000000000000007F7F7F007F7F
      7F007F7F7F007F7F7F007F7F7F007F7F7F007F7F7F007F7F7F007F7F7F007F7F
      7F007F7F7F000000000000000000000000000000000000000000000000000000
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
      2800000040000000100000000100010000000000800000000000000000000000
      000000000000000000000000FFFFFF00FFFFFFFFFFFF0000FE3FFFFFFFFF0000
      F81FC003C0070000E00FC003C00700008007C003C00700000003C003C0070000
      0001C003C00700000000C003C00700000001C003C00700008001C003C0070000
      C001C003C0070000E000C003C0070000F000C003C0070000F803C003C0070000
      FC0FFFFFC0070000FE3FFFFFFFFF000000000000000000000000000000000000
      000000000000}
  end
  object QcT002: TBrvClientDataSet [4]
    Aggregates = <>
    CommandText = 'Select * From S011 Where NmTabela is null'
    Params = <>
    BeforeInsert = QcT002BeforeInsert
    OnReconcileError = QcT002ReconcileError
    BrShowFieldName = False
    BrQueryCode = 33
    BrDicionario = DmSrvApl.BrvDicionario
    BrType = VqNormal
    BrGravaLog = True
    BrUserCode = 0
    Left = 623
    Top = 130
  end
  object DsT002: TDataSource [5]
    DataSet = QcT002
    OnStateChange = DsT002StateChange
    Left = 665
    Top = 130
  end
  inherited LspS049: TBrvListParam
    Left = 15
    Top = 129
  end
  object BrvServerProgress: TBrvServerProgress
    Left = 547
    Top = 129
  end
end
