object ConsLoja: TConsLoja
  Left = 216
  Top = 176
  Width = 492
  Height = 337
  Caption = 'ConsLoja'
  Color = clBtnFace
  Font.Charset = DEFAULT_CHARSET
  Font.Color = clWindowText
  Font.Height = -11
  Font.Name = 'MS Sans Serif'
  Font.Style = []
  OldCreateOrder = False
  PixelsPerInch = 96
  TextHeight = 13
  object DBGrid1: TDBGrid
    Left = 0
    Top = 113
    Width = 484
    Height = 149
    Align = alClient
    DataSource = DataModule2.dsLOJAS
    TabOrder = 0
    TitleFont.Charset = DEFAULT_CHARSET
    TitleFont.Color = clWindowText
    TitleFont.Height = -11
    TitleFont.Name = 'MS Sans Serif'
    TitleFont.Style = []
  end
  object Panel1: TPanel
    Left = 0
    Top = 0
    Width = 484
    Height = 113
    Align = alTop
    Color = clWhite
    TabOrder = 1
    object Label1: TLabel
      Left = 24
      Top = 16
      Width = 56
      Height = 16
      Caption = 'BUSCAR'
      Font.Charset = DEFAULT_CHARSET
      Font.Color = clWindowText
      Font.Height = -13
      Font.Name = 'MS Sans Serif'
      Font.Style = []
      ParentFont = False
    end
    object SpeedButton1: TSpeedButton
      Left = 372
      Top = 12
      Width = 77
      Height = 23
      Caption = '&Buscar'
      Glyph.Data = {
        9E060000424D9E0600000000000036040000280000001A000000160000000100
        08000000000068020000C40E0000C40E000000010000000000003F180E00F0AB
        7500D75B34001C4A8D00B2401F0053535300B0ADAB008C8C8D0000174600F6DE
        A500E59C6A00377ECB00CFB09500D4BCB500DE855800C0671200872E16008854
        47003B3D4400F8E5B5002962A900F1D58D00797B7D00BE6B43002D272700D67A
        4C004294E700E0DEDC00C7512D000D2D6400FCEBC600C371480073737300F7AF
        79003B86D600BFBDBC00873C2600D6734800FFCC9900A4A4A100772E1C00DA91
        6200F3D69400EDA26D00BC4C2800FDEECE0074210A0082878B0081493B00153E
        7C00C98A580066666600C2623B00DAAC840085645B00F8BD9200999999006B2E
        2000F4EBE3002D6BB500DFD3C6006B1A07009D31120021559900B8580800E969
        3F00CC663300DD825300EEEEEE002E354500F6DD9B00FCBD84000A204E008483
        8200622F2400B6917700B7B5B400EBC58F00CCCCCC00993E2000C65A3300A259
        3700EEA572009278710064180600ED9E6B00B6583300BC4924005B5B5B00B67F
        5A00489DF200F7B47C0095939100783B2C00976D5B00CF6B4200952E1000C5C5
        C500AEA5A200F7E0AD00DD673C00F8C497004D4949001D2A4D00E1C38900DF8B
        5A00968A7E00E6E6E6007524100020203F00F4D38500FFFFFF00F5AA7500E694
        6300CE623A00F9E6BC00408DDE00C5722300D77D51009B472F003173BF00472A
        2200A4391900A8948F006C1F0A00D7D6D600F8B88100FFFFFF00000000000000
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
        00000000000000000000000000000000000000000000000000006F6F6F6F6F6F
        6F6F6F6F6F6F6F6F6F6F6F6F6F6F6F6F6F6F6F6F00006F6F6F6F230C6F6A076F
        6F6F6F6F6F6F6F2D0668446F6F6F6F6F00006F6F6F4E60607C3B78086A446F6F
        6F7D3333382F20334E6F6F6F00006F6F6F51770F1D225A143108456823330638
        38290C6805336F6F00006F6F6F77601C1D3F225A5A781408664E4E386E264668
        465E386F00006F6F6F601004451D311478745A223168235B265B265B265B056F
        00006F6F6F544260417730451D31145A33386E68466846684626294E00006F6F
        7D6076601C421C0F1C6045050668265B262673467346384E00006F6F68604177
        604242514242514E4E68266846262D4E462D6A6F00006F6F5E0F76424A427642
        42421168065B26267346732D7346076F00006F6F51764376425443424376422F
        6868462646732D2D2D382D6F00006F6F10437632765110427632765106682646
        734673460C4E6F6F00006F4E0F2955764329424A607741763233686846684638
        686F6F6F00006F297655765576437629764277601C437638380C075B5E6F6F6F
        00006F7755295529552955295529552942775529552926265B4E6F6F00006F04
        375529555555295529552943764277433726262626516F6F000068425B295B29
        5B2955295B295529552955514077550C26260C6F00007655375B375537553755
        29553755295529550C6F23297751446F0000295B5B0C5B295B5555295B295B29
        5B295B29736F6F6F6F6F6F6F00006F42375B375B76297D684B42295B37553742
        6F6F6F6F6F6F6F6F00006F6F6829295E2D6F6F6F6F6F7338294243386F6F6F6F
        6F6F6F6F00006F6F6F6F6F6F6F6F6F6F6F6F6F6F6F6F446F6F6F6F6F6F6F6F6F
        0000}
    end
    object Edit1: TEdit
      Left = 88
      Top = 12
      Width = 273
      Height = 21
      TabOrder = 0
    end
    object GroupBox1: TGroupBox
      Left = 1
      Top = 56
      Width = 482
      Height = 56
      Align = alBottom
      Caption = 'TIPO'
      TabOrder = 1
      object RadioButton1: TRadioButton
        Left = 16
        Top = 24
        Width = 105
        Height = 17
        Caption = 'CODIGO LOJA'
        TabOrder = 0
      end
      object RadioButton2: TRadioButton
        Left = 136
        Top = 24
        Width = 113
        Height = 17
        Caption = 'LOJA'
        TabOrder = 1
      end
      object RadioButton3: TRadioButton
        Left = 200
        Top = 24
        Width = 113
        Height = 17
        Caption = 'SIGLA'
        TabOrder = 2
      end
      object RadioButton4: TRadioButton
        Left = 272
        Top = 24
        Width = 153
        Height = 17
        Caption = 'CNPJ'
        TabOrder = 3
      end
    end
  end
  object Panel2: TPanel
    Left = 0
    Top = 262
    Width = 484
    Height = 41
    Align = alBottom
    Color = clWhite
    TabOrder = 2
    object SpeedButton2: TSpeedButton
      Left = 288
      Top = 8
      Width = 89
      Height = 26
      Caption = '&Relat'#243'rio'
      Glyph.Data = {
        16060000424D1606000000000000360400002800000014000000180000000100
        080000000000E0010000C40E0000C40E00000001000000010000BE703600BC6C
        3400BA673100C3C3C300E9E9E900E5E5E500E6E6E600E7E7E700EAEAEA00E8E8
        E800F2F2F200EDEDED00EEEEEE00EFEFEF00E4E4E400E2E2E200DFDFDF00F5F5
        F500E1E1E100DEDEDE00F0F0F000E0E0E000F3F3F300E3E3E300F4F4F400F7F7
        F700DCDCDC00DADADA00F8F8F800DBDBDB00D7D7D700F6F6F600D6D6D600D8D8
        D800DDDDDD00D9D9D900FAFAFA00CF964900D29D4D00CD914700D19B4B00D098
        4A00CE944800D5D5D500FBFBFB00D4D4D400AAAAAA00ABABAB00A6A6A600A7A7
        A700A8A8A800ACACAC00A4A4A400FCFCFC00A9A9A900D3D3D300C1773A00C580
        3E00C27A3B00C0753900BB693300BD6E3500C7844000B9653100CC8F4500CA8A
        4300C8874200BF733700C47D3C00CB8D4400CE914700D1984A00D29B4B00D39D
        4D00D0964900CF944800ADADAD00B2B2B200B5B5B500C0C0C000FEFEFE00C5C5
        C500D2D2D200BCBCBC00B0B0B000CDCDCD00CFCFCF00AFAFAF00AEAEAE00BE6E
        3500B3B3B300CB8A4300BC693300C1C1C100BA653100CBCBCB00BFBFBF00BDBD
        BD00C9874200C7823F00B1B1B100C9C9C900C2773A00C57D3C00C37A3B00C7C7
        C700C0733700C8844000B7B7B700C6803E00CC8D4400BBBBBB00C4C4C400CD8F
        4500B4B4B400CCCCCC00CACACA00C8C8C800C6C6C600BEBEBE00BABABA00ECEC
        EC00F9F9F900EBEBEB00C6823F00F1F1F100DCA96E00FFFFFF00000000000000
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
        00000000000000000000000000000000000000000000000000007F7F7F7F7F7F
        7F0C7F7F7F0C7F7F7F0C7F7F7F7F220C2E6F6F6F6F6F736F6F73737373737373
        220C7F2E7373227322730C730C730C220C730C22730C7F6F7373227322732273
        220C2273220C220C730C7F6F73736F2E6F2E6F2E7E2E7E6F7E6F0C0C73227F6F
        73732E023F0259025959594059402E0C730C7F2E73736F277E7E7E2E7E7E7E2E
        7E7E22220C7F7F6F7373736F6F6F6F6F737E736F737E220C227F7F6F73227E3F
        593F403F403F404040407E0C0C7F7F6F73222E406D7E7E7E7E7E7E7E2E7E730C
        227F7F6F73730C220C227F227F0C7F220C730C0C0C7F7F6F730C2222220C220C
        220C0C7E59672E7F227F7F6F22226F2E7E0C7E2E22227F6F7E7E7E0C7F7F7F6F
        220C2E7E7E226D7E2E7F220C220C220C0C7F7F6F22227E7E402E407E7E737F2E
        40407E0C0C7F7F6F220C2E7E6F487E226D7E0C6F7E406F7F227F7F6F0C226F7E
        7F597E737E7E0C0C7F0C7F0C7F7F7F6F220C2E400C6F7E7E2E40737E59406F7F
        0C7F7F6F0C0C7E730C0C7E730C73226F40407E0C0C7F7F73220C0C0C220C0C0C
        0C0C0C7F7F7F0C7F0C7F7F6F0C220C0C7F0C7F0C7F0C7F0C7F0C7F7F7F7F7F6F
        220C0C0C0C0C0C0C0C0C7F0C0C7F7F7F0C7F7F6F0C730C220C227F220C0C7F22
        0C0C7F737F7F7F737373227373732273737322737373227F7F7F}
    end
    object SpeedButton3: TSpeedButton
      Left = 384
      Top = 8
      Width = 81
      Height = 25
      Caption = '&Sair'
      Glyph.Data = {
        9E050000424D9E05000000000000360400002800000012000000120000000100
        08000000000068010000130B0000130B00000001000000000000000000000000
        80000080000000808000800000008000800080800000C0C0C000C0DCC000F0CA
        A6000020400000206000002080000020A0000020C0000020E000004000000040
        20000040400000406000004080000040A0000040C0000040E000006000000060
        20000060400000606000006080000060A0000060C0000060E000008000000080
        20000080400000806000008080000080A0000080C0000080E00000A0000000A0
        200000A0400000A0600000A0800000A0A00000A0C00000A0E00000C0000000C0
        200000C0400000C0600000C0800000C0A00000C0C00000C0E00000E0000000E0
        200000E0400000E0600000E0800000E0A00000E0C00000E0E000400000004000
        20004000400040006000400080004000A0004000C0004000E000402000004020
        20004020400040206000402080004020A0004020C0004020E000404000004040
        20004040400040406000404080004040A0004040C0004040E000406000004060
        20004060400040606000406080004060A0004060C0004060E000408000004080
        20004080400040806000408080004080A0004080C0004080E00040A0000040A0
        200040A0400040A0600040A0800040A0A00040A0C00040A0E00040C0000040C0
        200040C0400040C0600040C0800040C0A00040C0C00040C0E00040E0000040E0
        200040E0400040E0600040E0800040E0A00040E0C00040E0E000800000008000
        20008000400080006000800080008000A0008000C0008000E000802000008020
        20008020400080206000802080008020A0008020C0008020E000804000008040
        20008040400080406000804080008040A0008040C0008040E000806000008060
        20008060400080606000806080008060A0008060C0008060E000808000008080
        20008080400080806000808080008080A0008080C0008080E00080A0000080A0
        200080A0400080A0600080A0800080A0A00080A0C00080A0E00080C0000080C0
        200080C0400080C0600080C0800080C0A00080C0C00080C0E00080E0000080E0
        200080E0400080E0600080E0800080E0A00080E0C00080E0E000C0000000C000
        2000C0004000C0006000C0008000C000A000C000C000C000E000C0200000C020
        2000C0204000C0206000C0208000C020A000C020C000C020E000C0400000C040
        2000C0404000C0406000C0408000C040A000C040C000C040E000C0600000C060
        2000C0604000C0606000C0608000C060A000C060C000C060E000C0800000C080
        2000C0804000C0806000C0808000C080A000C080C000C080E000C0A00000C0A0
        2000C0A04000C0A06000C0A08000C0A0A000C0A0C000C0A0E000C0C00000C0C0
        2000C0C04000C0C06000C0C08000C0C0A000F0FBFF00A4A0A000808080000000
        FF0000FF000000FFFF00FF000000FF00FF00FFFF0000FFFFFF00F69E0D010101
        0101010101010101010DA6F60000A64C010101010101010101010101010D01A6
        00000D0101010B0B01010101010101010D010D0D000001010101010C01010101
        01010D010D0D0D0D00000101010156AFA74C01014DA7A74E010D0D0D00000101
        01014DEFF6A60101AFF6EF4E0D0E0D0D0000010101010155EFF65556F6F6550D
        0E0E0D0D00000101010101019FF6EFF6F6A6010E0E0E0D0D0000010101010D01
        0DAFFFF6AF0D0E0E0E0E0E0D000001010C010D0101A7FFF6A70E0E0D0E0F0E0D
        0000010D0D0D4E019EF6F608F65E0E4F0E0E4E0D00000D4E564E4D55EFF69E9E
        F6F6560E4F4F4F0E00004D56565555EFF6EF4E4DAFF6EF565757570D00004D5E
        5E565EAFAF565E565EAEAE56575F5F0D00004D9E9E9F5F5E5E9F5F9F9F9F9E9F
        9F9F9F56000056A7A7A7A7A7A7A7A7A7A7A7A7A7A7A79F560000A6A6A7A7A7A7
        A7A7A7A7A7A7A7A7A7AF9EA70000F6A656555D5556565656565656565656AEF6
        0000}
    end
  end
end
