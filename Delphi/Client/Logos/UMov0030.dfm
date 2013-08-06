inherited Mov0030: TMov0030
  Caption = 'Mov0030 - Registro de N'#227'o Conformidade (RNC) "FO/05"'
  ClientHeight = 473
  ClientWidth = 692
  Position = poDesktopCenter
  ExplicitTop = 6
  ExplicitWidth = 700
  ExplicitHeight = 500
  PixelsPerInch = 96
  TextHeight = 13
  inherited NavBarra: TBrvDbNavCop
    Width = 692
    ExplicitWidth = 692
    inherited BrvSpeedButton1: TBrvSpeedButton
      Left = 665
      ExplicitLeft = 815
    end
    inherited SbtAjuda: TBrvSpeedButton
      Left = 640
      ExplicitLeft = 790
    end
  end
  inherited PnlFundo: TPanel
    Width = 692
    Height = 443
    ExplicitWidth = 692
    ExplicitHeight = 395
    object PnlRegistro: TPanel
      Left = 1
      Top = 41
      Width = 686
      Height = 397
      Align = alClient
      BevelOuter = bvNone
      Font.Charset = DEFAULT_CHARSET
      Font.Color = clWindowText
      Font.Height = -11
      Font.Name = 'MS Sans Serif'
      Font.Style = [fsBold]
      ParentFont = False
      TabOrder = 0
      Visible = False
      ExplicitHeight = 349
      object PnlCabeca: TPanel
        Left = 0
        Top = 0
        Width = 686
        Height = 112
        Align = alTop
        BorderStyle = bsSingle
        Font.Charset = DEFAULT_CHARSET
        Font.Color = clWindowText
        Font.Height = -11
        Font.Name = 'MS Sans Serif'
        Font.Style = [fsBold]
        ParentFont = False
        TabOrder = 0
        DesignSize = (
          682
          108)
        object Label2: TLabel
          Left = 6
          Top = 10
          Width = 50
          Height = 13
          Caption = 'Emitente'
          Font.Charset = DEFAULT_CHARSET
          Font.Color = clBlack
          Font.Height = -11
          Font.Name = 'MS Sans Serif'
          Font.Style = [fsBold]
          ParentFont = False
        end
        object Label13: TLabel
          Left = 566
          Top = 10
          Width = 28
          Height = 13
          Anchors = [akTop, akRight]
          Caption = 'Data'
          Font.Charset = DEFAULT_CHARSET
          Font.Color = clBlack
          Font.Height = -11
          Font.Name = 'MS Sans Serif'
          Font.Style = [fsBold]
          ParentFont = False
        end
        object LblNmUsuEmi: TBrvRetCon
          AlignWithMargins = True
          Left = 157
          Top = 7
          Width = 403
          Height = 21
          TabStop = False
          Anchors = [akLeft, akTop, akRight]
          BevelInner = bvLowered
          BevelKind = bkFlat
          BevelWidth = 2
          BorderStyle = bsNone
          ParentColor = True
          ReadOnly = True
          TabOrder = 1
        end
        object EdtCdUsuEmi: TBrvDbEdit
          Left = 81
          Top = 7
          Width = 70
          Height = 21
          TabStop = False
          Color = 14541539
          DataField = 'CdUsuEmi'
          DataSource = DsQ004
          ReadOnly = True
          TabOrder = 0
          BrAlignment = taRightJustify
          BrVisibleButton = False
          BrFunctionButton = TpDbConsulta
          BrConfigurar.Strings = (
            'CdSetor;CdSetor;S;S;'
            'LblDsSetor;DsSetor;S;N;')
          BrDicionario = DmSrvApl.BrvDicionario
          BrQueryConsulta = 0
        end
        object EdtDtEmiRnc: TBrvDbEdit
          AlignWithMargins = True
          Left = 595
          Top = 7
          Width = 80
          Height = 21
          TabStop = False
          Anchors = [akTop, akRight]
          Color = 14541539
          DataField = 'DtEmiRnc'
          DataSource = DsQ004
          ReadOnly = True
          TabOrder = 2
          BrAlignment = taRightJustify
          BrVisibleButton = False
          BrFunctionButton = TpDbData
          BrConfigurar.Strings = (
            'EmiRnc,DtEmiRnc;S;S;')
          BrDicionario = DmSrvApl.BrvDicionario
          BrQueryConsulta = 0
        end
        object GroupBox1: TGroupBox
          AlignWithMargins = True
          Left = 6
          Top = 30
          Width = 669
          Height = 71
          Margins.Left = 0
          Margins.Top = 0
          Margins.Right = 0
          Margins.Bottom = 0
          Align = alCustom
          Anchors = [akLeft, akTop, akRight]
          Caption = 'Local Destino'
          TabOrder = 3
          DesignSize = (
            669
            71)
          object LblCdEmpres: TLabel
            Left = 8
            Top = 18
            Width = 27
            Height = 13
            Caption = 'Filial'
            Color = clBtnFace
            Font.Charset = DEFAULT_CHARSET
            Font.Color = clBlue
            Font.Height = -11
            Font.Name = 'MS Sans Serif'
            Font.Style = [fsBold]
            ParentColor = False
            ParentFont = False
          end
          object LblCdSetor: TLabel
            Left = 8
            Top = 45
            Width = 31
            Height = 13
            Caption = 'Setor'
            Color = clBtnFace
            Font.Charset = DEFAULT_CHARSET
            Font.Color = clBlue
            Font.Height = -11
            Font.Name = 'MS Sans Serif'
            Font.Style = [fsBold]
            ParentColor = False
            ParentFont = False
          end
          object EdtCdEmpres: TBrvDbEdit
            Left = 75
            Top = 14
            Width = 70
            Height = 21
            DataField = 'CdEmpres'
            DataSource = DsQ004
            TabOrder = 0
            BrAlignment = taRightJustify
            BrVisibleButton = True
            BrFunctionButton = TpDbConsulta
            BrConfigurar.Strings = (
              'CdEmpres;CdEmpres;S;S;'
              'LblDsEmpres;DsEmpres;S;N;'
              '')
            BrDicionario = DmSrvApl.BrvDicionario
            BrQueryConsulta = 10
          end
          object EdtCdSetor: TBrvDbEdit
            Left = 75
            Top = 41
            Width = 70
            Height = 21
            DataField = 'CdSetor'
            DataSource = DsQ004
            TabOrder = 2
            BrAlignment = taRightJustify
            BrVisibleButton = True
            BrFunctionButton = TpDbConsulta
            BrConfigurar.Strings = (
              'CdSetor;CdSetor;S;S;'
              'LblDsSetor;DsSetor;S;N;')
            BrDicionario = DmSrvApl.BrvDicionario
            BrQueryConsulta = 69
          end
          object LblDsEmpres: TBrvRetCon
            AlignWithMargins = True
            Left = 151
            Top = 14
            Width = 509
            Height = 21
            TabStop = False
            Anchors = [akLeft, akTop, akRight]
            BevelInner = bvLowered
            BevelKind = bkFlat
            BevelWidth = 2
            BorderStyle = bsNone
            ParentColor = True
            ReadOnly = True
            TabOrder = 1
          end
          object LblDsSetor: TBrvRetCon
            AlignWithMargins = True
            Left = 151
            Top = 41
            Width = 403
            Height = 21
            TabStop = False
            Anchors = [akLeft, akTop, akRight]
            BevelInner = bvLowered
            BevelKind = bkFlat
            BevelWidth = 2
            BorderStyle = bsNone
            ParentColor = True
            ReadOnly = True
            TabOrder = 3
          end
          object BtnProsseg2: TBrvBitBtn
            AlignWithMargins = True
            Left = 560
            Top = 39
            Width = 100
            Height = 25
            Hint = 'Prosseguir'
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
            TabOrder = 4
            OnClick = BtnProsseg2Click
            BrTipoBotao = BrBtnOk
          end
        end
      end
      object Panel6: TPanel
        Left = 0
        Top = 362
        Width = 686
        Height = 35
        Align = alBottom
        BorderStyle = bsSingle
        Font.Charset = DEFAULT_CHARSET
        Font.Color = clWindowText
        Font.Height = -11
        Font.Name = 'MS Sans Serif'
        Font.Style = [fsBold]
        ParentFont = False
        TabOrder = 2
        ExplicitTop = 314
        DesignSize = (
          682
          31)
        object BtnCancelar: TBrvBitBtn
          AlignWithMargins = True
          Left = 575
          Top = 3
          Width = 100
          Height = 25
          Hint = 'Cancelar'
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
        object BtnGravar: TBrvBitBtn
          AlignWithMargins = True
          Left = 471
          Top = 3
          Width = 100
          Height = 25
          Hint = 'Gravar'
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
          TabOrder = 0
          OnClick = BtnGravarClick
          BrTipoBotao = BrBtnSalvar
        end
      end
      object PnlDesRNC: TPanel
        Left = 0
        Top = 112
        Width = 686
        Height = 250
        Align = alClient
        BorderStyle = bsSingle
        Font.Charset = DEFAULT_CHARSET
        Font.Color = clWindowText
        Font.Height = -11
        Font.Name = 'MS Sans Serif'
        Font.Style = [fsBold]
        ParentFont = False
        TabOrder = 1
        Visible = False
        ExplicitHeight = 202
        object Panel2: TPanel
          Left = 1
          Top = 1
          Width = 680
          Height = 34
          Align = alTop
          BevelOuter = bvNone
          Font.Charset = DEFAULT_CHARSET
          Font.Color = clWindowText
          Font.Height = -11
          Font.Name = 'MS Sans Serif'
          Font.Style = [fsBold]
          ParentFont = False
          TabOrder = 0
          DesignSize = (
            680
            34)
          object LblCdUsuDes: TLabel
            Left = 5
            Top = 9
            Width = 69
            Height = 13
            Caption = 'Destinat'#225'rio'
            Color = clBtnFace
            Font.Charset = DEFAULT_CHARSET
            Font.Color = clBlue
            Font.Height = -11
            Font.Name = 'MS Sans Serif'
            Font.Style = [fsBold]
            ParentColor = False
            ParentFont = False
          end
          object LblDsLogDes: TBrvRetCon
            Left = 157
            Top = 6
            Width = 385
            Height = 21
            TabStop = False
            Anchors = [akLeft, akTop, akRight]
            BevelInner = bvLowered
            BevelKind = bkFlat
            BevelWidth = 2
            BorderStyle = bsNone
            ParentColor = True
            ReadOnly = True
            TabOrder = 2
            Visible = False
          end
          object EdtCdUsuDes: TBrvDbEdit
            Left = 80
            Top = 6
            Width = 70
            Height = 21
            Color = clWhite
            DataField = 'CdUsuDes'
            DataSource = DsQ004
            TabOrder = 0
            BrAlignment = taRightJustify
            BrVisibleButton = True
            BrFunctionButton = TpDbConsulta
            BrOnBeforeConsulta = EdtCdUsuDesBrOnBeforeConsulta
            BrConfigurar.Strings = (
              'CdUsuDes;CdUsuari;S;S;'
              'LblNmUsuDes;NmComUsu;S;N;'
              'LblDsLogDes;DsLogin;S;N;')
            BrDicionario = DmSrvApl.BrvDicionario
            BrQueryConsulta = 73
          end
          object LblNmUsuDes: TBrvRetCon
            AlignWithMargins = True
            Left = 156
            Top = 6
            Width = 518
            Height = 21
            TabStop = False
            Anchors = [akLeft, akTop, akRight]
            BevelInner = bvLowered
            BevelKind = bkFlat
            BevelWidth = 2
            BorderStyle = bsNone
            ParentColor = True
            ReadOnly = True
            TabOrder = 1
          end
        end
        object PgcDados: TPageControl
          AlignWithMargins = True
          Left = 1
          Top = 35
          Width = 680
          Height = 210
          Margins.Left = 0
          Margins.Top = 0
          Margins.Right = 0
          Margins.Bottom = 0
          ActivePage = TbsNaoCon
          Align = alClient
          Font.Charset = DEFAULT_CHARSET
          Font.Color = clBlack
          Font.Height = -11
          Font.Name = 'MS Sans Serif'
          Font.Style = [fsBold]
          ParentFont = False
          TabHeight = 26
          TabOrder = 1
          TabStop = False
          ExplicitHeight = 162
          object TbsNaoCon: TTabSheet
            Caption = 'N'#227'o Conformidade'
            Font.Charset = DEFAULT_CHARSET
            Font.Color = clBlue
            Font.Height = -11
            Font.Name = 'MS Sans Serif'
            Font.Style = [fsBold]
            ImageIndex = 2
            ParentFont = False
            ExplicitHeight = 126
            object LblTxRnc: TLabel
              AlignWithMargins = True
              Left = 1
              Top = 42
              Width = 670
              Height = 15
              Margins.Left = 1
              Margins.Top = 1
              Margins.Right = 1
              Margins.Bottom = 1
              Align = alTop
              Caption = 'Texto da N'#227'o Conformidade'
              Color = clBtnFace
              Constraints.MinHeight = 15
              Font.Charset = DEFAULT_CHARSET
              Font.Color = clBlue
              Font.Height = -11
              Font.Name = 'MS Sans Serif'
              Font.Style = [fsBold]
              ParentColor = False
              ParentFont = False
              Layout = tlCenter
              ExplicitLeft = 3
              ExplicitTop = 44
              ExplicitWidth = 159
            end
            object MemTxRnc: TDBMemo
              AlignWithMargins = True
              Left = 0
              Top = 58
              Width = 672
              Height = 116
              Margins.Left = 0
              Margins.Top = 0
              Margins.Right = 0
              Margins.Bottom = 0
              Align = alClient
              DataField = 'TxRnc'
              DataSource = DsQ004
              Font.Charset = DEFAULT_CHARSET
              Font.Color = clBlack
              Font.Height = -11
              Font.Name = 'MS Sans Serif'
              Font.Style = [fsBold]
              ParentFont = False
              TabOrder = 1
              ExplicitTop = 41
              ExplicitHeight = 85
            end
            object Panel5: TPanel
              Left = 0
              Top = 0
              Width = 672
              Height = 41
              Align = alTop
              BorderStyle = bsSingle
              Font.Charset = DEFAULT_CHARSET
              Font.Color = clWindowText
              Font.Height = -11
              Font.Name = 'MS Sans Serif'
              Font.Style = [fsBold]
              ParentFont = False
              TabOrder = 0
              DesignSize = (
                668
                37)
              object LblCdClaRnc: TLabel
                Left = 6
                Top = 11
                Width = 76
                Height = 13
                Caption = 'Classifica'#231#227'o'
                Color = clBtnFace
                Font.Charset = DEFAULT_CHARSET
                Font.Color = clBlue
                Font.Height = -11
                Font.Name = 'MS Sans Serif'
                Font.Style = [fsBold]
                ParentColor = False
                ParentFont = False
              end
              object LblDsClaRnc: TBrvRetCon
                AlignWithMargins = True
                Left = 240
                Top = 8
                Width = 419
                Height = 21
                TabStop = False
                Anchors = [akLeft, akTop, akRight]
                BevelInner = bvLowered
                BevelKind = bkFlat
                BevelWidth = 2
                BorderStyle = bsNone
                ParentColor = True
                ReadOnly = True
                TabOrder = 0
              end
              object LblDsContro: TBrvRetCon
                Left = 164
                Top = 8
                Width = 70
                Height = 21
                TabStop = False
                Alignment = taRightJustify
                BevelInner = bvLowered
                BevelKind = bkFlat
                BevelWidth = 2
                BorderStyle = bsNone
                ParentColor = True
                ReadOnly = True
                TabOrder = 1
              end
              object EdtCdClaRnc: TBrvDbEdit
                Left = 88
                Top = 8
                Width = 70
                Height = 21
                DataField = 'CdClaRnc'
                DataSource = DsQ004
                TabOrder = 2
                OnDragDrop = EdtCdClaRncDragDrop
                OnDragOver = EdtCdClaRncDragOver
                OnExit = EdtCdClaRncExit
                BrAlignment = taRightJustify
                BrVisibleButton = True
                BrFunctionButton = TpDbConsulta
                BrOnBeforeConsulta = EdtCdClaRncBrOnBeforeConsulta
                BrDicionario = DmSrvApl.BrvDicionario
                BrQueryConsulta = 0
              end
            end
          end
          object TbsDadCom: TTabSheet
            Caption = 'Dados Complementares'
            ImageIndex = 4
            ExplicitHeight = 126
            object Panel1: TPanel
              Left = 0
              Top = 0
              Width = 672
              Height = 174
              Align = alClient
              BorderStyle = bsSingle
              Font.Charset = DEFAULT_CHARSET
              Font.Color = clWindowText
              Font.Height = -11
              Font.Name = 'MS Sans Serif'
              Font.Style = [fsBold]
              ParentFont = False
              TabOrder = 0
              ExplicitHeight = 126
              DesignSize = (
                668
                170)
              object Label8: TLabel
                Left = 7
                Top = 11
                Width = 87
                Height = 13
                Caption = 'Transportadora'
                Font.Charset = DEFAULT_CHARSET
                Font.Color = clBlack
                Font.Height = -11
                Font.Name = 'MS Sans Serif'
                Font.Style = [fsBold]
                ParentFont = False
              end
              object Label10: TLabel
                Left = 7
                Top = 39
                Width = 53
                Height = 13
                Caption = 'Motorista'
                Font.Charset = DEFAULT_CHARSET
                Font.Color = clBlack
                Font.Height = -11
                Font.Name = 'MS Sans Serif'
                Font.Style = [fsBold]
                ParentFont = False
              end
              object Label12: TLabel
                Left = 7
                Top = 67
                Width = 33
                Height = 13
                Caption = 'Placa'
                Font.Charset = DEFAULT_CHARSET
                Font.Color = clBlack
                Font.Height = -11
                Font.Name = 'MS Sans Serif'
                Font.Style = [fsBold]
                ParentFont = False
              end
              object Label5: TLabel
                Left = 7
                Top = 95
                Width = 30
                Height = 13
                Caption = 'Frota'
                Font.Charset = DEFAULT_CHARSET
                Font.Color = clBlack
                Font.Height = -11
                Font.Name = 'MS Sans Serif'
                Font.Style = [fsBold]
                ParentFont = False
              end
              object EdtCdTitula: TBrvDbEdit
                Left = 101
                Top = 8
                Width = 80
                Height = 21
                DataField = 'CdTransp'
                DataSource = DsQ004
                TabOrder = 0
                BrAlignment = taRightJustify
                BrVisibleButton = True
                BrFunctionButton = TpDbConsulta
                BrConfigurar.Strings = (
                  'CdTransp;CdTitula;S;S;'
                  'LblRsTitula;RsTitula;S;N;')
                BrDicionario = DmSrvApl.BrvDicionario
                BrQueryConsulta = 65
              end
              object LblRsTitula: TBrvRetCon
                Left = 187
                Top = 8
                Width = 472
                Height = 21
                TabStop = False
                Anchors = [akLeft, akTop, akRight]
                BevelInner = bvLowered
                BevelKind = bkFlat
                BevelWidth = 2
                BorderStyle = bsNone
                ParentColor = True
                ReadOnly = True
                TabOrder = 1
              end
              object EdtCdMotori: TBrvDbEdit
                Left = 101
                Top = 36
                Width = 80
                Height = 21
                DataField = 'CdMotori'
                DataSource = DsQ004
                TabOrder = 2
                BrAlignment = taRightJustify
                BrVisibleButton = True
                BrFunctionButton = TpDbConsulta
                BrConfigurar.Strings = (
                  'CdMotori;CdMotori;S;S;'
                  'NmMotori;NmMotori;S;N;'
                  'CdVeicul;CdVeicul;S;N;'
                  'NrPlaVei;NrPlaVei;S;N;')
                BrDicionario = DmSrvApl.BrvDicionario
                BrQueryConsulta = 66
              end
              object EdtNmMotori: TBrvDbEdit
                Left = 187
                Top = 36
                Width = 472
                Height = 21
                Anchors = [akLeft, akTop, akRight]
                Color = clWhite
                DataField = 'NmMotori'
                DataSource = DsQ004
                TabOrder = 3
                OnKeyPress = EdtNmMotoriKeyPress
                BrAlignment = taLeftJustify
                BrVisibleButton = False
                BrFunctionButton = TpDbConsulta
                BrDicionario = DmSrvApl.BrvDicionario
                BrQueryConsulta = 0
              end
              object EdtNrPlaVei: TBrvDbEdit
                Left = 101
                Top = 64
                Width = 80
                Height = 21
                Color = clWhite
                DataField = 'NrPlaVei'
                DataSource = DsQ004
                TabOrder = 4
                BrAlignment = taLeftJustify
                BrVisibleButton = False
                BrFunctionButton = TpDbConsulta
                BrConfigurar.Strings = (
                  'NrPlaVei;NrPlaVei;S;S;')
                BrDicionario = DmSrvApl.BrvDicionario
                BrQueryConsulta = 0
              end
              object EdtCdVeicul: TBrvDbEdit
                Left = 101
                Top = 92
                Width = 80
                Height = 21
                DataField = 'CdVeicul'
                DataSource = DsQ004
                TabOrder = 5
                BrAlignment = taRightJustify
                BrVisibleButton = True
                BrFunctionButton = TpDbConsulta
                BrConfigurar.Strings = (
                  'CdVeicul;CdVeicul;S;S;')
                BrDicionario = DmSrvApl.BrvDicionario
                BrQueryConsulta = 67
              end
            end
          end
          object TbsProEnv: TTabSheet
            Caption = 'Produto(s) Envolvido(s)'
            ImageIndex = 2
            ExplicitHeight = 126
            object DbgProdutos: TBrvDbGrid
              Left = 0
              Top = 0
              Width = 672
              Height = 174
              Margins.Left = 0
              Margins.Top = 0
              Margins.Right = 0
              Margins.Bottom = 0
              BrShowMemo = True
              BrReadOnlyStyle = []
              BrReadOnlyColor = clBlack
              Align = alClient
              DataSource = DsQ005
              TabOrder = 0
              TitleFont.Charset = DEFAULT_CHARSET
              TitleFont.Color = clBlack
              TitleFont.Height = -11
              TitleFont.Name = 'MS Sans Serif'
              TitleFont.Style = [fsBold]
              OnKeyPress = DbgProdutosKeyPress
              BrDicionario = DmSrvApl.BrvDicionario
              BrDrawColumn.Strings = (
                'N'#227'o remova essas duas linhas de formata'#231#227'o:'
                'CampoTabela;Operador;ValorComparativo;Cor;')
              BrGradeZebrada = True
              Columns = <
                item
                  ButtonStyle = cbsConsulta
                  Expanded = False
                  FieldName = 'CdProdut'
                  Title.Caption = 'Produto'
                  Width = 75
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
                  Title.Caption = 'Descri'#231#227'o do Produto'
                  Width = 250
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
                  Width = 75
                  Visible = True
                  BrConsulta = 0
                  BrPermissao = []
                  BrValueHalfChecked = False
                  BrSaveOnClick = False
                end
                item
                  Expanded = False
                  FieldName = 'NrLote'
                  Title.Caption = 'N'#186' Lote'
                  Width = 100
                  Visible = True
                  BrConsulta = 0
                  BrPermissao = []
                  BrValueHalfChecked = False
                  BrSaveOnClick = False
                end
                item
                  Expanded = False
                  FieldName = 'DtVencto'
                  Title.Caption = 'Data Vencimento'
                  Width = 110
                  Visible = True
                  BrConsulta = 0
                  BrPermissao = []
                  BrValueHalfChecked = False
                  BrSaveOnClick = False
                end
                item
                  Expanded = False
                  FieldName = 'NrNota'
                  Title.Caption = 'Nota Fiscal'
                  Width = 80
                  Visible = True
                  BrConsulta = 0
                  BrPermissao = []
                  BrValueHalfChecked = False
                  BrSaveOnClick = False
                end
                item
                  ButtonStyle = cbsConsulta
                  Expanded = False
                  FieldName = 'CdTitula'
                  Title.Caption = 'Cliente'
                  Width = 75
                  Visible = True
                  BrConsulta = 26
                  BrConfigurar.Strings = (
                    'CdTitula;CdTitula;S;S;'
                    'RsTitula;RsTitula;S;N;')
                  BrPermissao = []
                  BrValueHalfChecked = False
                  BrSaveOnClick = False
                end
                item
                  Expanded = False
                  FieldName = 'RsTitula'
                  Title.Caption = 'Nome do Cliente'
                  Width = 275
                  Visible = True
                  BrConsulta = 0
                  BrPermissao = []
                  BrValueHalfChecked = False
                  BrSaveOnClick = False
                end>
            end
          end
          object TbsParInt: TTabSheet
            Caption = 'Pessoas Interessadas'
            ImageIndex = 1
            ExplicitHeight = 126
            object DbgUsuarios: TBrvDbGrid
              Left = 0
              Top = 0
              Width = 672
              Height = 174
              Margins.Left = 0
              Margins.Top = 0
              Margins.Right = 0
              Margins.Bottom = 0
              BrShowMemo = True
              BrReadOnlyStyle = [fsItalic]
              BrReadOnlyColor = clMaroon
              Align = alClient
              DataSource = DsQ006
              TabOrder = 0
              TitleFont.Charset = DEFAULT_CHARSET
              TitleFont.Color = clBlack
              TitleFont.Height = -11
              TitleFont.Name = 'MS Sans Serif'
              TitleFont.Style = [fsBold]
              OnKeyPress = DbgUsuariosKeyPress
              BrDicionario = DmSrvApl.BrvDicionario
              BrDrawColumn.Strings = (
                'N'#227'o remova essas duas linhas de formata'#231#227'o:'
                'CampoTabela;Operador;ValorComparativo;Cor;')
              BrGradeZebrada = True
              Columns = <
                item
                  ButtonStyle = cbsConsulta
                  Expanded = False
                  FieldName = 'CdUsuari'
                  Title.Caption = 'C'#243'digo'
                  Width = 75
                  Visible = True
                  BrConsulta = 61
                  BrConfigurar.Strings = (
                    'CdUsuari;CdUsuari;S;S;'
                    'NmComUsu;NmComUsu;S;N;'
                    'DsLogin;DsLogin;S;N;')
                  BrPermissao = []
                  BrValueHalfChecked = False
                  BrSaveOnClick = False
                end
                item
                  Expanded = False
                  FieldName = 'NmComUsu'
                  Title.Caption = 'Nome Completo'
                  Width = 400
                  Visible = True
                  BrConsulta = 0
                  BrPermissao = []
                  BrValueHalfChecked = False
                  BrSaveOnClick = False
                end>
            end
          end
          object TbsAnexos: TTabSheet
            Caption = 'Anexos'
            ImageIndex = 3
            ExplicitHeight = 126
            object Panel7: TPanel
              Left = 0
              Top = 0
              Width = 672
              Height = 41
              Align = alTop
              BorderStyle = bsSingle
              Font.Charset = DEFAULT_CHARSET
              Font.Color = clWindowText
              Font.Height = -11
              Font.Name = 'MS Sans Serif'
              Font.Style = [fsBold]
              ParentFont = False
              TabOrder = 0
              DesignSize = (
                668
                37)
              object LblArquivo: TLabel
                Left = 6
                Top = 11
                Width = 44
                Height = 13
                Caption = 'Arquivo'
                Color = clBtnFace
                Font.Charset = DEFAULT_CHARSET
                Font.Color = clBlack
                Font.Height = -11
                Font.Name = 'MS Sans Serif'
                Font.Style = [fsBold]
                ParentColor = False
                ParentFont = False
              end
              object BtnAnexar: TBrvBitBtn
                AlignWithMargins = True
                Left = 455
                Top = 6
                Width = 100
                Height = 25
                Hint = 'Anexar'
                Anchors = [akTop, akRight]
                Caption = '&Anexar'
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
                  80008080008080008080008080D8AD7DD8AD7D00808000808000808000808000
                  8080008080008080008080008080008080008080008080008080008080008080
                  008080008080008080ACA5A5ACA5A50080800080800080800080800080800080
                  8000808000808000808000808000808000808000808000808000808000808000
                  8080DEAF7DCFAB7DCFAB7DDEAF7D008080008080008080008080008080008080
                  008080008080008080008080008080008080008080008080008080008080B2A6
                  A6A6A3A3A6A4A4B3A7A700808000808000808000808000808000808000808000
                  8080008080008080008080008080008080008080008080DDAD7BCDAA7BCAA97B
                  CAA97BCDAA7BDDAD7B0080800080800080800080800080800080800080800080
                  80008080008080008080008080008080008080B0A5A5A4A2A2A1A1A1A1A1A1A4
                  A2A2B0A5A5008080008080008080008080008080008080008080008080008080
                  008080008080008080008080D3AA7ACDA97AC9A87AC9A87AC9A87AC9A87ACDA9
                  7AD3AA7A00808000808000808000808000808000808000808000808000808000
                  8080008080008080A9A3A3A3A0A0A0A0A0A1A1A1A0A0A0A0A0A0A4A1A1A8A2A2
                  0080800080800080800080800080800080800080800080800080800080800080
                  80EEAE76CAA777C7A677C7A677C7A677C7A677C7A677C7A677CAA777EEAE7600
                  8080008080008080008080008080008080008080008080008080008080BDA4A4
                  A09E9E9F9F9F9E9E9E9E9E9E9E9E9E9E9E9E9E9E9EA09F9FBDA4A40080800080
                  80008080008080008080008080008080008080008080D0A674C9A475C7A475C7
                  A475C7A475C7A475C7A475C7A475C7A475C7A475C9A475D0A674008080008080
                  008080008080008080008080008080008080A39D9D9E9C9C9C9C9C9D9D9D9C9C
                  9C9C9C9C9C9C9C9C9C9C9C9C9C9C9C9C9E9C9CA39D9D00808000808000808000
                  8080008080008080008080D5A672C7A372C3A273C3A273C3A273C3A273C3A273
                  C3A273C3A273C3A273C3A273C3A273C7A372D5A6720080800080800080800080
                  80008080008080A99D9D9D9B9B9A9A9A9A9A9A9A9A9A9B9B9B9A9A9A9A9A9A9B
                  9B9B9B9B9B9A9A9A9A9A9A9D9B9BA99D9D008080008080008080008080008080
                  E6A56CD3A26DD0A26ECBA16EC19F6FC19F6FC19F6FC19F6FC19F6FC19F6FC19F
                  6FC19F6FCBA16ED0A26ED3A26DE6A56C008080008080008080008080B59D9DA5
                  9999A399999F9898979797979797979797979797979797979797979797979797
                  9F9898A39999A69A9AB49C9C0080800080800080800080800080800080800080
                  80008080C99F6BC09D6CC09D6CC09D6CC09D6CC09D6CC09D6CC99F6B00808000
                  8080008080008080008080008080008080008080008080008080008080008080
                  9D96969595959696969595959595959595959595959D96960080800080800080
                  80008080008080008080008080008080008080008080008080008080CD9D67BE
                  9B69BE9B69BE9B69BE9B69BE9B69BE9B69CD9D67008080008080008080008080
                  0080800080800080800080800080800080800080800080809E94949393939393
                  939393939393939393939393939E949400808000808000808000808000808000
                  8080008080008080008080008080008080008080CB9B64BC9966BC9966BC9966
                  BC9966BC9966BC9966CB9B640080800080800080800080800080800080800080
                  800080800080800080800080800080809C929291919191919191919191919190
                  90909191919C9292008080008080008080008080008080008080008080008080
                  008080008080008080008080C99760BA9563BA9563BA9563BA9563BA9563BA95
                  63C9976000808000808000808000808000808000808000808000808000808000
                  8080008080008080998F8F8D8D8D8D8D8D8E8E8E8D8D8D8E8E8E8E8E8E998F8F
                  0080800080800080800080800080800080800080800080800080800080800080
                  80008080C6955CB7935EB7935EB7935EB7935EB7935EB7935EC6955C00808000
                  8080008080008080008080008080008080008080008080008080008080008080
                  968C8C8B8B8B8B8B8B8C8C8C8B8B8B8B8B8B8B8B8B958B8B0080800080800080
                  80008080008080008080008080008080008080008080008080008080C39056B4
                  8F59B48F59B48F59B48F59B48F59B48F59C39056008080008080008080008080
                  0080800080800080800080800080800080800080800080809187878787878787
                  8787878787878787878787878791878700808000808000808000808000808000
                  8080008080008080008080008080008080008080BD894DAF8850AF8850AF8850
                  AF8850AF8850AF8850BD894D0080800080800080800080800080800080800080
                  800080800080800080800080800080808A808081818180808080808080808081
                  81818080808B8181008080008080008080008080008080008080008080008080
                  008080008080008080008080B88346AB834AAB834AAB834AAB834AAB834AAB83
                  4AB8834600808000808000808000808000808000808000808000808000808000
                  8080008080008080857B7B7C7C7C7C7C7C7B7B7B7B7B7B7B7B7B7B7B7B857B7B
                  0080800080800080800080800080800080800080800080800080800080800080
                  80008080BA7E3DAD7E42AD7E42AD7E42AD7E42AD7E42AD7E42BA7E3D00808000
                  8080008080008080008080008080008080008080008080008080008080008080
                  8476767B77777A76767A76767B77777A76767A76768476760080800080800080
                  800080800080800080800080800080800080800080800080800080808F7C4886
                  7C4B867C4B867C4B867C4B867C4B867C4B8F7C48008080008080008080008080
                  0080800080800080800080800080800080800080800080806576765F77775E76
                  765F77775E76765E76765F777765757500808000808000808000808000808000
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
                OnClick = BtnAnexarClick
                BrTipoBotao = BrBtnSetaAbaixo
              end
              object EdtArquivo: TBrvEdit
                Left = 56
                Top = 8
                Width = 393
                Height = 21
                Anchors = [akLeft, akTop, akRight]
                TabOrder = 0
                BrVisibleButton = True
                BrFunctionButton = VeArquivo
                BrAlignment = taLeftJustify
                BrDicionario = DmSrvApl.BrvDicionario
                BrvQueryCode = 0
                BrRecordar = False
              end
              object BtnRemover: TBrvBitBtn
                AlignWithMargins = True
                Left = 559
                Top = 6
                Width = 100
                Height = 25
                Hint = 'Remover'
                Anchors = [akTop, akRight]
                Caption = '&Remover'
                DoubleBuffered = True
                Font.Charset = DEFAULT_CHARSET
                Font.Color = clWindowText
                Font.Height = -11
                Font.Name = 'Tahoma'
                Font.Style = [fsBold]
                Glyph.Data = {
                  96090000424D9609000000000000360000002800000028000000140000000100
                  18000000000060090000C40E0000C40E00000000000000000000008080008080
                  0080800080800080800080800000B3003A760000670000670000660000650000
                  B3363ABD00808000808000808000808000808000808000808000808000808000
                  8080008080008080008080979090867F7F7F7F7F7F7F7F867F7F979090979090
                  0080800080800080800080800080800080800080800080800080800080800080
                  800050940000B300009F1214981C1D911C1D890E0E7900006B00006F004E7B00
                  8080008080008080008080008080008080008080008080008080008080008080
                  AA94949790909393939494949292928A8A8A8781819882829790900080800080
                  800080800080800080800080800080800080800080800001C30206C24448C78A
                  91D7B8BCE3C7CAE8C8C9E8B0B1DA7A7BBA36378A00006A000064008080008080
                  0080800080800080800080800080800080809E9898A59B9BAEAEAEC5C5C5D6D6
                  D6DCDCDCDCDCDCD2D2D2BABABA999999979090857E7E00808000808000808000
                  80800080800080800080800007CA1B26CBBBC0EEECEDFAFFFFFFFFFFFFFFFFFF
                  FFFFFFFFFFFFFFFFFFE7E6F4A4A4CB04016C0000690080800080800080800080
                  80008080008080989898A7A4A4DADADAEBEBEBF6F6F6FAFAFAFBFBFBFCFCFCFA
                  FAFAF4F4F4E8E8E8CACACA8683837F7E7E008080008080008080008080008080
                  000AD81E30D8DFE4F7FFFFFFFFFFFFF5F8FD9E9FDC6E6EC46D6FC7ACADDDFFFF
                  FFFFFFFFFFFFFFC0C0DC06036C00006A008080008080008080008080A39D9DAC
                  A9A9E7E7E7F7F7F7F9F9F9EEEEEECCCCCCB9B9B9B9B9B9D1D1D1F4F4F4FAFAFA
                  F8F8F8D6D6D68784848680800080800080800080800080801929E7D8DDFBFFFF
                  FFFFFFFF8F96E10208B20004AD0406AD0A0E9F0000941B1BA3AAAADDFFFFFFFF
                  FFFFB2B2D200006D004C7A008080008080008080B6ACACE4E4E4F7F7F7F1F1F1
                  CACACA9696969292929696969393938E8E8E9A9A9AD0D0D0F4F4F4F8F8F8D0D0
                  D08E8383979090008080008080000DF68192EFFFFFFFFFFFFF7C89E50000BF0F
                  19BF202FCD151AC23C3DB23031AA17179F0000949C9CD7FFFFFFF0F0F84A4A97
                  00007E008080008080BEA8A8CACACAF4F4F4F7F7F7C6C6C69494949E9E9EA5A5
                  A59F9F9FA5A5A5A0A0A09797978D8D8DCBCBCBFAFAFAEBEBEBA3A3A39C868600
                  8080004DBB162DF0C1CCF8FFFFFFD4DBF80007D30013CD0A1BC80B15C4131FC1
                  2225AB1A1AA11010A0000095000095EFEFF8FFFFFF9C9CC8050086002D7F639F
                  95B4ADADDEDEDEF7F7F7E4E4E49A9A9A9C9C9C9E9E9E9D9D9D9F9F9F9D9D9D98
                  98989595958C8C8C8C8C8CEBEBEBF7F7F7C7C7C7928B8B4783830020F93354F3
                  EEF1FFFFFFFF4C6AF0000CDE001AD80006CE0713C5141FBA171AA6090AA10000
                  A00000A000008D7776C8FFFFFFD5D5EB2322A3000065C3BDAEB6B6B6ECECECF7
                  F7F7BCBCBC9D9D9D9D9D9D9999999C9C9C9E9E9E9898989393938D8D8D8E8E8E
                  858585BDBDBDFAFAFAE1E1E19B9B9B8680800024F95172F8FFFFFFF9FBFF113B
                  F50424EBB3BFF6DDDEF9D2D4F7CCCCEDC9C9ECD0D1EFDFDFF5999CDD00009A2A
                  2BACFFFFFFF1F3FB363ABD000066BCBCADC0C0C0F6F6F6EFEFEFACACACA7A7A7
                  DADADAE6E6E6E3E3E3DFDFDFDCDCDCE0E0E0E5E5E5CBCBCB8E8E8EA0A0A0F9F9
                  F9EDEDEDA8A8A88080800134FE7791FDFFFFFFEAEFFF0B3BFE082DF6DFE5FDFF
                  FFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFBEC2EC0000A71B22B0FFFFFFF8F8FC
                  3C44BC0000A5C1C0ACCBCBCBF7F7F7EBEBEBADADADAAAAAAE8E8E8F7F7F7F5F5
                  F5F6F6F6F6F6F6F5F5F5F9F9F9DBDBDB9393939C9C9CF6F6F6EFEFEFA9A9A990
                  9090335CFF87A0FFFFFFFFFFFFFF1948FF002CFE708DFD8D9EF99DA7F2ABB4EF
                  A7ABED8994E88E97E7616ED70000AF3A44C3FFFFFFEDEFF9333ABD0000A9D0CF
                  BBD0D0D0F5F5F5F2F2F2B2B2B2AAAAAAC9C9C9D0D0D0D2D2D2D5D5D5D4D4D4CB
                  CBCBCCCCCCBABABA939393ABABABF8F8F8EBEBEBA7A7A7989191264CFF869DFF
                  FFFFFFFFFFFF6E8DFF0019FF002BFE001DF7011CE8112DE10117D70010D20005
                  CB0010C70000B8989FE1FFFFFFCBD0F21D1ABD002A92D7C7BBCFCFCFF2F2F2F6
                  F6F6C9C9C9A5A5A5A7A7A7A4A4A4A5A5A5A7A7A7A0A0A09B9B9B999999989898
                  909090CDCDCDF9F9F9E0E0E0A8A1A14C8888008080748AFFF6F7FFFFFFFFF6F8
                  FF012EFF0024FF0A36FE1236FC183DF40015E20A26E0001CDB0007CF1A2DD1FF
                  FFFFFFFFFF8C96E20000C1008080008080D6CDCDEFEFEFF4F4F4EEEEEEAAAAAA
                  A8A8A8ADADADAFAFAFAFAFAFA2A2A2A5A5A5A1A1A19A9A9AA6A6A6F3F3F3F5F5
                  F5CACACAB09999008080008080162DF0D6DDFFFFFFFFFFFFFFB7C6FF0029FF00
                  21FF123EFF1B48FF1432E90228E8000AE20A26E0D2D9F9FFFFFFEAECFA3838D2
                  004A94008080008080EDD5D5E6E6E6F4F4F4F5F5F5DEDEDEA9A9A9A7A7A7B0B0
                  B0B4B4B4AAAAAAA4A4A49E9E9EA5A5A5E4E4E4F8F8F8EBEBEBB8AEAE00808000
                  8080008080008080162DF0FBFCFFFFFFFFFFFFFFE3E9FF4C6DFF0A33FF0023FF
                  0224FD0E33F8617BF5F2F6FFFFFFFFFFFFFF8088E40000C60080800080800080
                  80008080EADDDDF0F0F0F2F2F2F5F5F5EAEAEAC0C0C0ADADADABABABABABABAD
                  ADADC3C3C3EEEEEEF7F7F7F3F3F3CAC7C79E9898008080008080008080008080
                  008080162DF0FFFFFFFFFFFFFFFFFFFFFFFFD4DDFFB0C1FFB4C4FEDEE5FFFFFF
                  FFFFFFFFFFFFFF8A94ED0010CF008080008080008080008080008080C3CBCBE7
                  E2E2F1F1F1F3F3F3F4F4F4F3F3F3E5E5E5DBDBDBDDDDDDE8E8E8F5F5F5F6F6F6
                  F3F3F3CFCCCC9B9C9C008080008080008080008080008080008080008080162D
                  F0F5F7FEFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFED3DAFB6C80EE00
                  0BDC008080008080008080008080008080008080008080C4CCCCF1E4E4EFEFEF
                  F3F3F3F3F3F3F4F4F4F5F5F5F5F5F5F5F5F5F2F2F2E4E4E4C5C4C4A59E9E0080
                  80008080008080008080008080008080008080008080008080162DF0162DF0E4
                  EAFFF9F9FFF9FAFFF0F3FFD0D9FD8298F92E49F4000CF7008080008080008080
                  008080008080008080008080008080008080008080F8E0E0EBE2E2EAEAEAEFEF
                  EFF0F0F0EEEEEEE4E4E4CDCDCDBEB5B5C0A7A700808000808000808000808000
                  8080008080008080008080008080008080008080008080748AFF6D8AFF718EFF
                  5D7DFE3B5FFA3350FC0080800080800080800080800080800080800080800080
                  80008080008080008080008080008080008080DAD1D1C9C9C9CACACAC4C4C4B9
                  B9B9C1B8B8008080008080008080008080008080008080008080}
                NumGlyphs = 2
                ParentDoubleBuffered = False
                ParentFont = False
                ParentShowHint = False
                ShowHint = True
                TabOrder = 2
                OnClick = BtnRemoverClick
                BrTipoBotao = BrBtnExcluir
              end
            end
            object DbgAnexos: TBrvDbGrid
              Left = 0
              Top = 41
              Width = 672
              Height = 133
              Margins.Left = 0
              Margins.Top = 0
              Margins.Right = 0
              Margins.Bottom = 0
              BrShowMemo = True
              BrReadOnlyStyle = [fsItalic]
              BrReadOnlyColor = clMaroon
              Align = alClient
              DataSource = DsQ007
              ReadOnly = True
              TabOrder = 1
              TitleFont.Charset = DEFAULT_CHARSET
              TitleFont.Color = clBlack
              TitleFont.Height = -11
              TitleFont.Name = 'MS Sans Serif'
              TitleFont.Style = [fsBold]
              BrDicionario = DmSrvApl.BrvDicionario
              BrDrawColumn.Strings = (
                'N'#227'o remova essas duas linhas de formata'#231#227'o:'
                'CampoTabela;Operador;ValorComparativo;Cor;')
              BrGradeZebrada = False
              Columns = <
                item
                  Expanded = False
                  FieldName = 'NmArquiv'
                  Title.Caption = 'Arquivo'
                  Width = 600
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
    object Panel9: TPanel
      Left = 1
      Top = 1
      Width = 686
      Height = 40
      Align = alTop
      BorderStyle = bsSingle
      Font.Charset = DEFAULT_CHARSET
      Font.Color = clWindowText
      Font.Height = -11
      Font.Name = 'MS Sans Serif'
      Font.Style = [fsBold]
      ParentFont = False
      TabOrder = 1
      DesignSize = (
        682
        36)
      object LblNrRNC: TLabel
        Left = 6
        Top = 10
        Width = 45
        Height = 13
        Caption = 'N'#186' RNC'
        Color = clBtnFace
        Font.Charset = DEFAULT_CHARSET
        Font.Color = clBlue
        Font.Height = -11
        Font.Name = 'MS Sans Serif'
        Font.Style = [fsBold]
        ParentColor = False
        ParentFont = False
      end
      object BtnProsseg1: TBrvBitBtn
        AlignWithMargins = True
        Left = 575
        Top = 6
        Width = 100
        Height = 25
        Hint = 'Prosseguir'
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
        TabOrder = 1
        OnClick = BtnProsseg1Click
        BrTipoBotao = BrBtnOk
      end
      object EdtNrRnc: TBrvEditNum
        Left = 81
        Top = 7
        Width = 70
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
          'EdtNrRnc;NrRnc;S;S;')
        BrDicionario = DmSrvApl.BrvDicionario
        BrQueryCode = 68
        BrRecordar = False
      end
    end
  end
  inherited PopCfgFrm: TPopupMenu
    Left = 256
    Top = 65520
  end
  inherited ImlPopFrm: TImageList
    Left = 312
    Top = 65520
    Bitmap = {
      494C010103000500180014001400FFFFFFFFFF10FFFFFFFFFFFFFFFF424D3600
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
    Left = 368
    Top = 65520
  end
  object CpQ006: TBrvClientDataSet
    Aggregates = <>
    Params = <>
    AfterInsert = CpQ006AfterInsert
    BrShowFieldName = False
    BrQueryCode = 0
    BrDicionario = DmSrvApl.BrvDicionario
    BrType = VqNormal
    BrGravaLog = True
    BrUserCode = 0
    Left = 577
    Top = 333
  end
  object DsQ006: TDataSource
    DataSet = CpQ006
    Left = 627
    Top = 333
  end
  object CpQ005: TBrvClientDataSet
    Aggregates = <>
    Params = <>
    BeforePost = CpQ005BeforePost
    BrShowFieldName = False
    BrQueryCode = 0
    BrDicionario = DmSrvApl.BrvDicionario
    BrType = VqNormal
    BrGravaLog = True
    BrUserCode = 0
    Left = 473
    Top = 333
  end
  object DsQ005: TDataSource
    DataSet = CpQ005
    Left = 523
    Top = 333
  end
  object CcParams: TClientDataSet
    Aggregates = <>
    Params = <>
    Left = 392
    Top = 382
    object CcParamsXmlQ004: TMemoField
      FieldName = 'XmlQ004'
      BlobType = ftMemo
    end
    object CcParamsXmlQ005: TMemoField
      FieldName = 'XmlQ005'
      BlobType = ftMemo
    end
    object CcParamsXmlQ006: TMemoField
      FieldName = 'XmlQ006'
      BlobType = ftMemo
    end
    object CcParamsXmlAnexos: TBlobField
      FieldName = 'XmlQ007'
    end
    object CcParamsNmForm: TStringField
      FieldName = 'NmForm'
    end
  end
  object CpQ004: TBrvClientDataSet
    Aggregates = <>
    Params = <>
    BrShowFieldName = False
    BrQueryCode = 0
    BrDicionario = DmSrvApl.BrvDicionario
    BrType = VqNormal
    BrGravaLog = True
    BrUserCode = 0
    Left = 369
    Top = 333
  end
  object DsQ004: TDataSource
    DataSet = CpQ004
    Left = 419
    Top = 333
  end
  object CpQ007: TBrvClientDataSet
    Aggregates = <>
    FieldDefs = <>
    IndexDefs = <
      item
        Name = 'CpQ007Index1'
      end>
    Params = <>
    StoreDefs = True
    BeforeInsert = CpQ007BeforeInsert
    AfterPost = CpQ007AfterPost
    BrShowFieldName = False
    BrQueryCode = 0
    BrDicionario = DmSrvApl.BrvDicionario
    BrType = VqNormal
    BrGravaLog = True
    BrUserCode = 0
    Left = 266
    Top = 333
  end
  object DsQ007: TDataSource
    DataSet = CpQ007
    Left = 315
    Top = 333
  end
  object BrvListParam: TBrvListParam
    Left = 195
    Top = 383
  end
  object BrvServerProgress: TBrvServerProgress
    Left = 112
    Top = 383
  end
  object IdMsgSend: TIdMessage
    AttachmentEncoding = 'MIME'
    BccList = <>
    CCList = <>
    Encoding = meMIME
    FromList = <
      item
      end>
    Recipients = <>
    ReplyTo = <>
    ConvertPreamble = True
    Left = 312
    Top = 384
  end
  object SMTP: TIdSMTP
    SASLMechanisms = <>
    Left = 262
    Top = 384
  end
  object CdsClassRNC: TClientDataSet
    Aggregates = <>
    Params = <>
    Left = 269
    Top = 102
  end
end
