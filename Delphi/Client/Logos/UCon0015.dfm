inherited Con0015: TCon0015
  Caption = 'Con0015 - Consulta Ativo Imobilizado'
  ClientHeight = 393
  ClientWidth = 845
  Position = poDesktopCenter
  ExplicitWidth = 853
  ExplicitHeight = 420
  PixelsPerInch = 96
  TextHeight = 13
  inherited NavBarra: TBrvDbNavCop
    Width = 845
    ExplicitWidth = 845
    inherited BrvSpeedButton1: TBrvSpeedButton
      Left = 818
      ExplicitLeft = 913
    end
    inherited SbtAjuda: TBrvSpeedButton
      Left = 793
      ExplicitLeft = 888
    end
  end
  inherited PnlFundo: TPanel
    Width = 845
    Height = 363
    TabOrder = 0
    ExplicitWidth = 845
    ExplicitHeight = 363
    object PgcFundo: TPageControl
      Left = 1
      Top = 1
      Width = 839
      Height = 357
      ActivePage = TbsConsulta
      Align = alClient
      TabOrder = 0
      object TbsFiltro: TTabSheet
        Caption = 'TbsFiltro'
        object Panel4: TPanel
          Left = 0
          Top = 0
          Width = 831
          Height = 329
          Align = alClient
          BorderStyle = bsSingle
          Font.Charset = DEFAULT_CHARSET
          Font.Color = clWindowText
          Font.Height = -11
          Font.Name = 'MS Sans Serif'
          Font.Style = [fsBold]
          ParentFont = False
          TabOrder = 0
          object Panel1: TPanel
            Left = 1
            Top = 289
            Width = 825
            Height = 35
            Align = alBottom
            BorderStyle = bsSingle
            Font.Charset = DEFAULT_CHARSET
            Font.Color = clWindowText
            Font.Height = -11
            Font.Name = 'MS Sans Serif'
            Font.Style = [fsBold]
            ParentFont = False
            TabOrder = 0
            DesignSize = (
              821
              31)
            object BtnPesquisa: TBrvBitBtn
              Left = 710
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
              OnClick = BtnPesquisaClick
              BrTipoBotao = BrBtnLocalizar
            end
          end
          object Panel7: TPanel
            Left = 1
            Top = 1
            Width = 825
            Height = 288
            Align = alClient
            BorderStyle = bsSingle
            Font.Charset = DEFAULT_CHARSET
            Font.Color = clWindowText
            Font.Height = -11
            Font.Name = 'MS Sans Serif'
            Font.Style = [fsBold]
            ParentFont = False
            TabOrder = 1
            object LblCdEmpres: TLabel
              AlignWithMargins = True
              Left = 4
              Top = 4
              Width = 813
              Height = 15
              Align = alTop
              Caption = 'Empresa(s)'
              Constraints.MinHeight = 15
              Font.Charset = DEFAULT_CHARSET
              Font.Color = clBlue
              Font.Height = -11
              Font.Name = 'MS Sans Serif'
              Font.Style = [fsBold]
              ParentFont = False
              Transparent = True
              Layout = tlCenter
              ExplicitWidth = 63
            end
            object Panel6: TPanel
              Left = 1
              Top = 153
              Width = 819
              Height = 130
              Align = alBottom
              BevelOuter = bvNone
              Font.Charset = DEFAULT_CHARSET
              Font.Color = clWindowText
              Font.Height = -11
              Font.Name = 'MS Sans Serif'
              Font.Style = [fsBold]
              ParentFont = False
              TabOrder = 0
              DesignSize = (
                819
                130)
              object LblCdGruBem: TLabel
                Left = 3
                Top = 12
                Width = 81
                Height = 13
                Caption = 'Grupo de Bem'
                Color = clBtnFace
                Font.Charset = DEFAULT_CHARSET
                Font.Color = clBlack
                Font.Height = -11
                Font.Name = 'MS Sans Serif'
                Font.Style = [fsBold]
                ParentColor = False
                ParentFont = False
                Transparent = True
              end
              object LblCdBem: TLabel
                Left = 3
                Top = 42
                Width = 25
                Height = 13
                Caption = 'Bem'
                Font.Charset = DEFAULT_CHARSET
                Font.Color = clBlack
                Font.Height = -11
                Font.Name = 'MS Sans Serif'
                Font.Style = [fsBold]
                ParentFont = False
                Transparent = True
              end
              object LblDtBase: TLabel
                Left = 3
                Top = 72
                Width = 60
                Height = 13
                Caption = 'Data Base'
                Font.Charset = DEFAULT_CHARSET
                Font.Color = clBlue
                Font.Height = -11
                Font.Name = 'MS Sans Serif'
                Font.Style = [fsBold]
                ParentFont = False
                Transparent = True
              end
              object LblDtAnoEnt: TLabel
                Left = 3
                Top = 102
                Width = 89
                Height = 13
                Caption = 'Ano da Entrada'
                Font.Charset = DEFAULT_CHARSET
                Font.Color = clBlue
                Font.Height = -11
                Font.Name = 'MS Sans Serif'
                Font.Style = [fsBold]
                ParentFont = False
                Transparent = True
              end
              object EdtCdGruBem: TBrvEditNum
                Left = 104
                Top = 9
                Width = 88
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
                BrConfigurar.Strings = (
                  'EdtCdGruBem;CdGruBem;S;S;'
                  'LblDsGruBem;DsGruBem;S;N;')
                BrDicionario = DmSrvApl.BrvDicionario
                BrQueryCode = 30
                BrRecordar = False
              end
              object LblDsGruBem: TBrvRetCon
                Left = 198
                Top = 9
                Width = 615
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
              object EdtCdBem: TBrvEditNum
                Left = 104
                Top = 39
                Width = 88
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
                  'EdtCdBem;CdBem;S;S;'
                  'LblDsBem;DsProdut;S;N;')
                BrDicionario = DmSrvApl.BrvDicionario
                BrQueryCode = 51
                BrRecordar = False
              end
              object LblDsBem: TBrvRetCon
                Left = 198
                Top = 39
                Width = 615
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
              object EdtDtBase: TBrvMesAno
                Left = 104
                Top = 69
                Width = 88
                Height = 21
                Alignment = taLeftJustify
                EditMask = '99/9999;1; '
                MaxLength = 7
                TabOrder = 4
                Text = '  /    '
                OnChange = EdtDtBaseChange
                BrvReadOnlyColor = 14541539
                DataValida = False
                DataVazia = True
                AsMes = 0
                AsAno = 0
                AsDiaFinal = 0
                FunctionButton = TVMAData
                BrDicionario = DmSrvApl.BrvDicionario
                BrRecordar = False
              end
              object EdtDtAnoEnt: TBrvEditNum
                Left = 104
                Top = 99
                Width = 88
                Height = 21
                TabOrder = 5
                Text = '0'
                OnChange = EdtDtAnoEntChange
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
            end
            object CblCdEmpres: TBrvCheckListBox
              Left = 1
              Top = 22
              Width = 819
              Height = 131
              Align = alClient
              Columns = 4
              Font.Charset = ANSI_CHARSET
              Font.Color = clWindowText
              Font.Height = -11
              Font.Name = 'MS Sans Serif'
              Font.Style = [fsBold]
              ItemHeight = 13
              ParentFont = False
              TabOrder = 1
            end
          end
        end
      end
      object TbsConsulta: TTabSheet
        Caption = 'TbsConsulta'
        ImageIndex = 1
        object Panel3: TPanel
          Left = 0
          Top = 0
          Width = 831
          Height = 329
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
            Top = 91
            Width = 825
            Height = 5
            Cursor = crVSplit
            Align = alTop
            ExplicitTop = 151
            ExplicitWidth = 772
          end
          object Panel2: TPanel
            Left = 1
            Top = 289
            Width = 825
            Height = 35
            Align = alBottom
            BorderStyle = bsSingle
            Font.Charset = DEFAULT_CHARSET
            Font.Color = clWindowText
            Font.Height = -11
            Font.Name = 'MS Sans Serif'
            Font.Style = [fsBold]
            ParentFont = False
            TabOrder = 0
            DesignSize = (
              821
              31)
            object LblQtReg: TLabel
              Left = 8
              Top = 9
              Width = 54
              Height = 13
              Caption = 'LblQtReg'
            end
            object BtnExcel: TBrvBitBtn
              Left = 606
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
            object BtnVoltar: TBrvBitBtn
              Left = 710
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
          end
          object Panel5: TPanel
            Left = 1
            Top = 1
            Width = 825
            Height = 90
            Align = alTop
            BorderStyle = bsSingle
            TabOrder = 1
            object StgFiltros: TStringGrid
              Left = 1
              Top = 1
              Width = 819
              Height = 84
              Align = alClient
              ColCount = 2
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
              ColWidths = (
                128
                575)
            end
          end
          object DbgFiltro: TBrvDbGrid
            Left = 1
            Top = 96
            Width = 825
            Height = 193
            BrShowMemo = True
            BrReadOnlyStyle = [fsItalic]
            BrReadOnlyColor = clMaroon
            Align = alClient
            DataSource = DtsP002
            TabOrder = 2
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
                FieldName = 'DtEntrad'
                Title.Caption = 'Data da Entrada'
                Width = 105
                Visible = True
                BrConsulta = 0
                BrPermissao = []
                BrValueHalfChecked = False
                BrSaveOnClick = False
              end
              item
                Expanded = False
                FieldName = 'CdBem'
                Title.Caption = 'Bem'
                Width = 75
                Visible = True
                BrConsulta = 0
                BrPermissao = []
                BrValueHalfChecked = False
                BrSaveOnClick = False
              end
              item
                Expanded = False
                FieldName = 'CdProdut'
                Title.Caption = 'Produto'
                Width = 75
                Visible = True
                BrConsulta = 0
                BrPermissao = []
                BrValueHalfChecked = False
                BrSaveOnClick = False
              end
              item
                Expanded = False
                FieldName = 'DsProdut'
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
                FieldName = 'CdFornec'
                Title.Caption = 'Fornecedor'
                Width = 75
                Visible = True
                BrConsulta = 0
                BrPermissao = []
                BrValueHalfChecked = False
                BrSaveOnClick = False
              end
              item
                Expanded = False
                FieldName = 'RsTitula'
                Title.Caption = 'Nome do Fornecedor'
                Width = 250
                Visible = True
                BrConsulta = 0
                BrPermissao = []
                BrValueHalfChecked = False
                BrSaveOnClick = False
              end
              item
                Expanded = False
                FieldName = 'VrBem'
                Title.Caption = 'Valor do Bem'
                Width = 115
                Visible = True
                BrConsulta = 0
                BrPermissao = []
                BrValueHalfChecked = False
                BrSaveOnClick = False
              end
              item
                Expanded = False
                FieldName = 'VrBemDep'
                Title.Caption = 'Valor Depreci'#225'vel'
                Width = 115
                Visible = True
                BrConsulta = 0
                BrPermissao = []
                BrValueHalfChecked = False
                BrSaveOnClick = False
              end
              item
                Expanded = False
                FieldName = 'VrDeprec'
                Title.Caption = 'Valor Depreciado'
                Width = 115
                Visible = True
                BrConsulta = 0
                BrPermissao = []
                BrValueHalfChecked = False
                BrSaveOnClick = False
              end
              item
                Expanded = False
                FieldName = 'QtParcela'
                Title.Caption = 'Parcelas Deprec.'
                Width = 115
                Visible = True
                BrConsulta = 0
                BrPermissao = []
                BrValueHalfChecked = False
                BrSaveOnClick = False
              end
              item
                Expanded = False
                FieldName = 'aVrDeprec'
                Title.Caption = 'Valor a Depreciar'
                Width = 115
                Visible = True
                BrConsulta = 0
                BrPermissao = []
                BrValueHalfChecked = False
                BrSaveOnClick = False
              end
              item
                Expanded = False
                FieldName = 'VrImpPis'
                Title.Caption = 'Valor PIS'
                Width = 90
                Visible = True
                BrConsulta = 0
                BrPermissao = []
                BrValueHalfChecked = False
                BrSaveOnClick = False
              end
              item
                Expanded = False
                FieldName = 'VrPisApr'
                Title.Caption = 'PIS Aproveitado'
                Width = 115
                Visible = True
                BrConsulta = 0
                BrPermissao = []
                BrValueHalfChecked = False
                BrSaveOnClick = False
              end
              item
                Expanded = False
                FieldName = 'QtParPis'
                Title.Caption = 'Parcela PIS Aprov.'
                Width = 115
                Visible = True
                BrConsulta = 0
                BrPermissao = []
                BrValueHalfChecked = False
                BrSaveOnClick = False
              end
              item
                Expanded = False
                FieldName = 'aVrPisApr'
                Title.Caption = 'PIS a Aproveitar'
                Width = 115
                Visible = True
                BrConsulta = 0
                BrPermissao = []
                BrValueHalfChecked = False
                BrSaveOnClick = False
              end
              item
                Expanded = False
                FieldName = 'VrImpCof'
                Title.Caption = 'Valor Cofins'
                Width = 90
                Visible = True
                BrConsulta = 0
                BrPermissao = []
                BrValueHalfChecked = False
                BrSaveOnClick = False
              end
              item
                Expanded = False
                FieldName = 'VrCofApr'
                Title.Caption = 'Cofins Aproveitado'
                Width = 115
                Visible = True
                BrConsulta = 0
                BrPermissao = []
                BrValueHalfChecked = False
                BrSaveOnClick = False
              end
              item
                Expanded = False
                FieldName = 'QtParCof'
                Title.Caption = 'Parcela Cof. Aprov.'
                Width = 115
                Visible = True
                BrConsulta = 0
                BrPermissao = []
                BrValueHalfChecked = False
                BrSaveOnClick = False
              end
              item
                Expanded = False
                FieldName = 'aVrCofApr'
                Title.Caption = 'Cofins a Aproveitar'
                Width = 115
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
  inherited PopCfgFrm: TPopupMenu
    Left = 376
    Top = 0
  end
  inherited ImlPopFrm: TImageList
    Left = 456
    Top = 0
  end
  inherited LspS049: TBrvListParam
    Left = 416
    Top = 0
  end
  object CcP002: TClientDataSet
    Aggregates = <>
    Params = <>
    ReadOnly = True
    Left = 528
    Top = 80
  end
  object DtsP002: TDataSource
    DataSet = CcP002
    Left = 568
    Top = 80
  end
  object BrvExcel: TBrvExcel
    BrSalvarComo = True
    BrNomePadrao = 'AtivoImobilizado'
    BrAlertProgress = True
    Left = 616
    Top = 80
  end
  object BrvListParam: TBrvListParam
    Left = 656
    Top = 80
  end
  object BrvServerProgress: TBrvServerProgress
    Left = 704
    Top = 80
  end
end
