inherited Con0018: TCon0018
  Caption = 'Con0018 - Consulta conta corrente do motorista'
  ClientHeight = 473
  ClientWidth = 842
  Position = poDesktopCenter
  ExplicitWidth = 850
  ExplicitHeight = 500
  PixelsPerInch = 96
  TextHeight = 13
  inherited NavBarra: TBrvDbNavCop
    Width = 842
    ExplicitWidth = 842
    inherited BrvSpeedButton1: TBrvSpeedButton
      Left = 815
      ExplicitLeft = 765
    end
    inherited SbtAjuda: TBrvSpeedButton
      Left = 790
      ExplicitLeft = 740
    end
  end
  inherited PnlFundo: TPanel
    Width = 842
    Height = 443
    ExplicitWidth = 842
    ExplicitHeight = 443
    object PgcFundo: TPageControl
      Left = 1
      Top = 1
      Width = 836
      Height = 437
      ActivePage = TbsFiltro
      Align = alClient
      TabOrder = 0
      TabStop = False
      object TbsFiltro: TTabSheet
        Caption = 'TbsFiltro'
        object Panel1: TPanel
          Left = 0
          Top = 0
          Width = 828
          Height = 409
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
            Top = 370
            Width = 822
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
              818
              30)
            object BtnPesquisar: TBrvBitBtn
              Left = 695
              Top = 2
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
          object Panel7: TPanel
            Left = 1
            Top = 1
            Width = 822
            Height = 369
            Align = alClient
            BorderStyle = bsSingle
            Font.Charset = DEFAULT_CHARSET
            Font.Color = clWindowText
            Font.Height = -11
            Font.Name = 'MS Sans Serif'
            Font.Style = [fsBold]
            ParentFont = False
            TabOrder = 0
            DesignSize = (
              818
              365)
            object Label1: TLabel
              Left = 6
              Top = 10
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
              Top = 64
              Width = 66
              Height = 13
              Caption = 'Data Inicial'
              Font.Charset = DEFAULT_CHARSET
              Font.Color = clBlack
              Font.Height = -11
              Font.Name = 'MS Sans Serif'
              Font.Style = [fsBold]
              ParentFont = False
            end
            object Label6: TLabel
              Left = 6
              Top = 91
              Width = 59
              Height = 13
              Caption = 'Data Final'
              Font.Charset = DEFAULT_CHARSET
              Font.Color = clBlack
              Font.Height = -11
              Font.Name = 'MS Sans Serif'
              Font.Style = [fsBold]
              ParentFont = False
            end
            object Label2: TLabel
              Left = 6
              Top = 37
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
            object Label3: TLabel
              Left = 6
              Top = 118
              Width = 68
              Height = 13
              Caption = 'Movimentos'
              Font.Charset = DEFAULT_CHARSET
              Font.Color = clBlack
              Font.Height = -11
              Font.Name = 'MS Sans Serif'
              Font.Style = [fsBold]
              ParentFont = False
            end
            object EdtDtFinal: TBrvEditDate
              Left = 83
              Top = 88
              Width = 127
              Height = 21
              EditMask = '99/99/9999;1; '
              MaxLength = 10
              TabOrder = 5
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
              Top = 61
              Width = 127
              Height = 21
              EditMask = '99/99/9999;1; '
              MaxLength = 10
              TabOrder = 4
              Text = '  /  /    '
              BrvReadOnlyColor = 14541539
              BrDataValida = False
              BrDataVazia = True
              BrFunctionButton = TVdData
              BrAlignment = taLeftJustify
              BrDicionario = DmSrvApl.BrvDicionario
              BrRecordar = False
            end
            object LblDsEmpres: TBrvRetCon
              Left = 216
              Top = 7
              Width = 579
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
            object RdgTpMovime: TRadioGroup
              Left = 83
              Top = 110
              Width = 127
              Height = 90
              Ctl3D = True
              DoubleBuffered = False
              Font.Charset = DEFAULT_CHARSET
              Font.Color = clWindowText
              Font.Height = -11
              Font.Name = 'MS Sans Serif'
              Font.Style = [fsBold]
              ItemIndex = 0
              Items.Strings = (
                'Encerrados'
                'N'#227'o Encerrados'
                'Todos')
              ParentCtl3D = False
              ParentDoubleBuffered = False
              ParentFont = False
              TabOrder = 6
            end
            object EdtCdMotori: TBrvEditNum
              Left = 83
              Top = 34
              Width = 127
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
                'EdtCdMotori;CdMotori;S;S;'
                'LblNmMotori;NmMotori;S;N;')
              BrDicionario = DmSrvApl.BrvDicionario
              BrQueryCode = 17
              BrRecordar = False
            end
            object LblNmMotori: TBrvRetCon
              Left = 216
              Top = 34
              Width = 579
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
            object EdtCdEmpres: TBrvEditNum
              Left = 83
              Top = 7
              Width = 127
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
          end
        end
      end
      object TbsConsulta: TTabSheet
        Caption = 'TbsConsulta'
        ImageIndex = 1
        ExplicitLeft = 0
        ExplicitTop = 0
        ExplicitWidth = 0
        ExplicitHeight = 0
        object Panel5: TPanel
          Left = 0
          Top = 0
          Width = 828
          Height = 409
          Align = alClient
          BorderStyle = bsSingle
          Font.Charset = DEFAULT_CHARSET
          Font.Color = clWindowText
          Font.Height = -11
          Font.Name = 'MS Sans Serif'
          Font.Style = [fsBold]
          ParentFont = False
          TabOrder = 0
          object Splitter2: TSplitter
            Left = 1
            Top = 205
            Width = 822
            Height = 5
            Cursor = crVSplit
            Align = alBottom
            ExplicitLeft = -2
            ExplicitTop = 213
          end
          object Splitter1: TSplitter
            Left = 1
            Top = 71
            Width = 822
            Height = 5
            Cursor = crVSplit
            Align = alTop
            ExplicitTop = 68
          end
          object Panel6: TPanel
            Left = 1
            Top = 370
            Width = 822
            Height = 34
            Align = alBottom
            BorderStyle = bsSingle
            Font.Charset = DEFAULT_CHARSET
            Font.Color = clWindowText
            Font.Height = -11
            Font.Name = 'MS Sans Serif'
            Font.Style = [fsBold]
            ParentFont = False
            TabOrder = 4
            DesignSize = (
              818
              30)
            object Label4: TLabel
              Left = 6
              Top = 9
              Width = 76
              Height = 13
              Caption = 'Lan'#231'amentos'
              Font.Charset = DEFAULT_CHARSET
              Font.Color = clBlack
              Font.Height = -11
              Font.Name = 'MS Sans Serif'
              Font.Style = [fsBold]
              ParentFont = False
            end
            object Label7: TLabel
              Left = 141
              Top = 9
              Width = 44
              Height = 13
              Caption = 'D'#233'bitos'
              Font.Charset = DEFAULT_CHARSET
              Font.Color = clBlack
              Font.Height = -11
              Font.Name = 'MS Sans Serif'
              Font.Style = [fsBold]
              ParentFont = False
            end
            object Label8: TLabel
              Left = 294
              Top = 9
              Width = 47
              Height = 13
              Caption = 'Cr'#233'ditos'
              Font.Charset = DEFAULT_CHARSET
              Font.Color = clBlack
              Font.Height = -11
              Font.Name = 'MS Sans Serif'
              Font.Style = [fsBold]
              ParentFont = False
            end
            object Label9: TLabel
              Left = 450
              Top = 9
              Width = 33
              Height = 13
              Caption = 'Saldo'
              Font.Charset = DEFAULT_CHARSET
              Font.Color = clBlack
              Font.Height = -11
              Font.Name = 'MS Sans Serif'
              Font.Style = [fsBold]
              ParentFont = False
            end
            object BtnExcel: TBrvBitBtn
              Left = 591
              Top = 2
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
              ExplicitLeft = 599
            end
            object BtnVoltar: TBrvBitBtn
              Left = 695
              Top = 2
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
              ExplicitLeft = 703
            end
            object LblVrLancam: TBrvRetCon
              Left = 86
              Top = 7
              Width = 50
              Height = 16
              Margins.Left = 0
              Margins.Top = 0
              Margins.Right = 0
              Margins.Bottom = 0
              TabStop = False
              Alignment = taRightJustify
              BevelInner = bvNone
              BevelKind = bkFlat
              BevelWidth = 2
              BorderStyle = bsNone
              Color = 14541539
              ReadOnly = True
              TabOrder = 2
              Text = '0'
            end
            object LblVrDebito: TBrvRetCon
              Left = 189
              Top = 7
              Width = 100
              Height = 16
              Margins.Left = 0
              Margins.Top = 0
              Margins.Right = 0
              Margins.Bottom = 0
              TabStop = False
              Alignment = taRightJustify
              BevelInner = bvNone
              BevelKind = bkFlat
              BevelWidth = 2
              BorderStyle = bsNone
              Color = 14541539
              ReadOnly = True
              TabOrder = 3
              Text = '0'
            end
            object LblVrCredit: TBrvRetCon
              Left = 345
              Top = 7
              Width = 100
              Height = 16
              Margins.Left = 0
              Margins.Top = 0
              Margins.Right = 0
              Margins.Bottom = 0
              TabStop = False
              Alignment = taRightJustify
              BevelInner = bvNone
              BevelKind = bkFlat
              BevelWidth = 2
              BorderStyle = bsNone
              Color = 14541539
              ReadOnly = True
              TabOrder = 4
              Text = '0'
            end
            object LblVrSaldo: TBrvRetCon
              Left = 487
              Top = 7
              Width = 100
              Height = 16
              Margins.Left = 0
              Margins.Top = 0
              Margins.Right = 0
              Margins.Bottom = 0
              TabStop = False
              Alignment = taRightJustify
              BevelInner = bvNone
              BevelKind = bkFlat
              BevelWidth = 2
              BorderStyle = bsNone
              Color = 14541539
              ReadOnly = True
              TabOrder = 5
              Text = '0'
            end
          end
          object Panel8: TPanel
            Left = 1
            Top = 1
            Width = 822
            Height = 70
            Align = alTop
            BorderStyle = bsSingle
            TabOrder = 0
            object StgFiltros: TStringGrid
              Left = 1
              Top = 1
              Width = 816
              Height = 64
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
            end
          end
          object Panel2: TPanel
            Left = 1
            Top = 76
            Width = 822
            Height = 109
            Align = alClient
            BorderStyle = bsSingle
            Font.Charset = DEFAULT_CHARSET
            Font.Color = clWindowText
            Font.Height = -11
            Font.Name = 'MS Sans Serif'
            Font.Style = [fsBold]
            ParentFont = False
            TabOrder = 1
            object DbgRegistro: TBrvDbGrid
              Left = 1
              Top = 1
              Width = 816
              Height = 103
              BrShowMemo = True
              BrReadOnlyStyle = []
              BrReadOnlyColor = clBlack
              Align = alClient
              DataSource = DsT009
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
                  FieldName = 'CdMotori'
                  Title.Caption = 'Motorista'
                  Width = 80
                  Visible = True
                  BrConsulta = 0
                  BrPermissao = []
                  BrValueHalfChecked = False
                  BrSaveOnClick = False
                end
                item
                  Expanded = False
                  FieldName = 'NmMotori'
                  Title.Caption = 'Nome do Motorista'
                  Width = 275
                  Visible = True
                  BrConsulta = 0
                  BrPermissao = []
                  BrValueHalfChecked = False
                  BrSaveOnClick = False
                end
                item
                  Expanded = False
                  FieldName = 'VrTotDeb'
                  Title.Caption = 'Total D'#233'bito'
                  Width = 90
                  Visible = True
                  BrConsulta = 0
                  BrPermissao = []
                  BrValueHalfChecked = False
                  BrSaveOnClick = False
                end
                item
                  Expanded = False
                  FieldName = 'VrTotCre'
                  Title.Caption = 'Total Cr'#233'dito'
                  Width = 90
                  Visible = True
                  BrConsulta = 0
                  BrPermissao = []
                  BrValueHalfChecked = False
                  BrSaveOnClick = False
                end
                item
                  Expanded = False
                  FieldName = 'VrSaldo'
                  Title.Caption = 'Saldo'
                  Width = 90
                  Visible = True
                  BrConsulta = 0
                  BrPermissao = []
                  BrValueHalfChecked = False
                  BrSaveOnClick = False
                end
                item
                  Expanded = False
                  FieldName = 'DtAceCar'
                  Title.Caption = #218'ltimo Acerto'
                  Width = 100
                  Visible = True
                  BrConsulta = 0
                  BrPermissao = []
                  BrValueHalfChecked = False
                  BrSaveOnClick = False
                end>
            end
          end
          object Panel3: TPanel
            Left = 1
            Top = 210
            Width = 822
            Height = 160
            Align = alBottom
            BorderStyle = bsSingle
            Font.Charset = DEFAULT_CHARSET
            Font.Color = clWindowText
            Font.Height = -11
            Font.Name = 'MS Sans Serif'
            Font.Style = [fsBold]
            ParentFont = False
            TabOrder = 3
            object DbgDetalhe: TBrvDbGrid
              Left = 1
              Top = 1
              Width = 816
              Height = 154
              BrShowMemo = True
              BrReadOnlyStyle = []
              BrReadOnlyColor = clBlack
              Align = alClient
              DataSource = DsT019
              TabOrder = 0
              TitleFont.Charset = DEFAULT_CHARSET
              TitleFont.Color = clWindowText
              TitleFont.Height = -11
              TitleFont.Name = 'MS Sans Serif'
              TitleFont.Style = [fsBold]
              BrDrawColumn.Strings = (
                'N'#227'o remova essas duas linhas de formata'#231#227'o:'
                'CampoTabela;Operador;ValorComparativo;Cor;')
              BrGradeZebrada = True
              Columns = <
                item
                  Expanded = False
                  FieldName = 'DtLancto'
                  Title.Caption = 'Data Lan'#231'amento'
                  Width = 110
                  Visible = True
                  BrConsulta = 0
                  BrPermissao = []
                  BrValueHalfChecked = False
                  BrSaveOnClick = False
                end
                item
                  Expanded = False
                  FieldName = 'CdHistor'
                  Title.Caption = 'Hist'#243'rico'
                  Width = 80
                  Visible = True
                  BrConsulta = 0
                  BrPermissao = []
                  BrValueHalfChecked = False
                  BrSaveOnClick = False
                end
                item
                  Expanded = False
                  FieldName = 'DsHistor'
                  Title.Caption = 'Descri'#231#227'o do Hist'#243'rico'
                  Width = 250
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
                  FieldName = 'TpHistor'
                  Title.Caption = 'D'#233'bito/Cr'#233'dito'
                  Width = 93
                  Visible = True
                  BrConsulta = 0
                  BrPermissao = []
                  BrValueHalfChecked = False
                  BrSaveOnClick = False
                end
                item
                  Expanded = False
                  FieldName = 'VrLancto'
                  Title.Caption = 'Valor Lan'#231'amento'
                  Width = 112
                  Visible = True
                  BrConsulta = 0
                  BrPermissao = []
                  BrValueHalfChecked = False
                  BrSaveOnClick = False
                end>
            end
          end
          object Panel9: TPanel
            Left = 1
            Top = 185
            Width = 822
            Height = 20
            Align = alBottom
            BorderStyle = bsSingle
            Font.Charset = DEFAULT_CHARSET
            Font.Color = clWindowText
            Font.Height = -11
            Font.Name = 'MS Sans Serif'
            Font.Style = [fsBold]
            ParentFont = False
            TabOrder = 2
            object Label10: TLabel
              Left = 1
              Top = 1
              Width = 45
              Height = 13
              Align = alClient
              Alignment = taCenter
              Caption = 'Detalhe'
              Font.Charset = DEFAULT_CHARSET
              Font.Color = clBlack
              Font.Height = -11
              Font.Name = 'MS Sans Serif'
              Font.Style = [fsBold]
              ParentFont = False
            end
          end
        end
      end
    end
  end
  inherited PopCfgFrm: TPopupMenu
    Left = 320
    Top = 65520
  end
  inherited ImlPopFrm: TImageList
    Left = 376
    Top = 65520
  end
  inherited LspS049: TBrvListParam
    Left = 432
    Top = 65520
  end
  object BrvListParam: TBrvListParam
    Left = 707
    Top = 182
  end
  object BrvServerProgress: TBrvServerProgress
    Left = 625
    Top = 182
  end
  object DsT009: TDataSource
    DataSet = CpT009
    Left = 390
    Top = 182
  end
  object CpT009: TBrvClientDataSet
    Aggregates = <>
    Params = <>
    BeforeInsert = CpT009BeforeInsert
    AfterScroll = CpT009AfterScroll
    BrShowFieldName = False
    BrQueryCode = 38
    BrDicionario = DmSrvApl.BrvDicionario
    BrType = VqNormal
    BrGravaLog = True
    BrUserCode = 0
    Left = 436
    Top = 182
  end
  object DsT019: TDataSource
    DataSet = CpT019
    Left = 502
    Top = 182
  end
  object CpT019: TBrvClientDataSet
    Aggregates = <>
    Params = <>
    ReadOnly = True
    BrShowFieldName = False
    BrQueryCode = 38
    BrDicionario = DmSrvApl.BrvDicionario
    BrType = VqNormal
    BrGravaLog = True
    BrUserCode = 0
    Left = 548
    Top = 182
  end
  object BrvExcel: TBrvExcel
    BrSalvarComo = True
    BrNomePadrao = 'ContaCorrenteMotorista'
    BrAlertProgress = True
    Left = 769
    Top = 182
  end
end
