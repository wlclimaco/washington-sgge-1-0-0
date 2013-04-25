object Form1: TForm1
  Left = 269
  Top = 149
  Caption = 'ACBrNFe - Demonstra'#231#227'o'
  ClientHeight = 604
  ClientWidth = 874
  Color = clBtnFace
  Font.Charset = DEFAULT_CHARSET
  Font.Color = clWindowText
  Font.Height = -11
  Font.Name = 'MS Sans Serif'
  Font.Style = []
  OldCreateOrder = False
  Position = poScreenCenter
  OnCreate = FormCreate
  PixelsPerInch = 96
  TextHeight = 13
  object Label29: TLabel
    Left = 248
    Top = 40
    Width = 38
    Height = 13
    Caption = 'Label29'
  end
  object PageControl1: TPageControl
    Left = 0
    Top = 0
    Width = 874
    Height = 604
    ActivePage = TabSheet15
    Align = alClient
    TabOrder = 0
    ExplicitLeft = 8
    ExplicitTop = 45
    ExplicitWidth = 849
    ExplicitHeight = 366
    object TabSheet14: TTabSheet
      Caption = 'TabSheet14'
      ExplicitLeft = -100
      ExplicitTop = 80
      ExplicitWidth = 513
      ExplicitHeight = 338
      object Panel7: TPanel
        Left = 0
        Top = 41
        Width = 185
        Height = 494
        Align = alLeft
        TabOrder = 0
        ExplicitLeft = 576
        ExplicitTop = 248
        ExplicitHeight = 41
        object GroupBox1: TGroupBox
          Left = 8
          Top = 6
          Width = 171
          Height = 115
          Caption = 'Status Manifesto'
          TabOrder = 0
          object CheckBox1: TCheckBox
            Left = 7
            Top = 16
            Width = 146
            Height = 17
            Caption = 'Ci'#234'ncia da Opera'#231#227'o'
            Checked = True
            State = cbChecked
            TabOrder = 0
          end
          object CheckBox2: TCheckBox
            Left = 7
            Top = 39
            Width = 146
            Height = 17
            Caption = 'Confirma'#231#227'o da Opera'#231#227'o'
            TabOrder = 1
          end
          object CheckBox3: TCheckBox
            Left = 7
            Top = 62
            Width = 97
            Height = 17
            Caption = 'Desconhecido'
            TabOrder = 2
          end
          object CheckBox4: TCheckBox
            Left = 7
            Top = 85
            Width = 97
            Height = 17
            Caption = 'N'#227'o Realizado'
            TabOrder = 3
          end
        end
        object GroupBox7: TGroupBox
          Left = 8
          Top = 127
          Width = 171
          Height = 143
          Caption = 'Data Emiss'#227'o'
          TabOrder = 1
          object Label31: TLabel
            Left = 7
            Top = 19
            Width = 53
            Height = 13
            Caption = 'Data Inicial'
          end
          object Label32: TLabel
            Left = 7
            Top = 56
            Width = 48
            Height = 13
            Caption = 'Data Final'
          end
          object BrvEditDate1: TBrvEditDate
            Left = 7
            Top = 35
            Width = 154
            Height = 21
            EditMask = '99/99/9999;1; '
            MaxLength = 10
            TabOrder = 0
            Text = '  /  /    '
            BrvReadOnlyColor = 14541539
            BrDataValida = False
            BrDataVazia = True
            BrFunctionButton = TVdData
            BrAlignment = taLeftJustify
            BrRecordar = False
          end
          object BrvEditDate2: TBrvEditDate
            Left = 7
            Top = 71
            Width = 154
            Height = 21
            EditMask = '99/99/9999;1; '
            MaxLength = 10
            TabOrder = 1
            Text = '  /  /    '
            BrvReadOnlyColor = 14541539
            BrDataValida = False
            BrDataVazia = True
            BrFunctionButton = TVdData
            BrAlignment = taLeftJustify
            BrRecordar = False
          end
        end
        object GroupBox8: TGroupBox
          Left = 8
          Top = 276
          Width = 171
          Height = 84
          Caption = 'Chave Acesso'
          TabOrder = 2
          object BrvEditNum1: TBrvEditNum
            Left = 3
            Top = 24
            Width = 158
            Height = 21
            TabOrder = 0
            Text = '0'
            BrvReadOnlyColor = 14541539
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
        end
        object GroupBox9: TGroupBox
          Left = 8
          Top = 366
          Width = 171
          Height = 122
          Caption = 'Dados'
          TabOrder = 3
          object Label33: TLabel
            Left = 3
            Top = 12
            Width = 78
            Height = 13
            Caption = 'Numero da Nota'
          end
          object Label34: TLabel
            Left = 3
            Top = 50
            Width = 65
            Height = 13
            Caption = 'Serie da Nota'
          end
          object BrvEditNum2: TBrvEditNum
            Left = 3
            Top = 29
            Width = 158
            Height = 21
            TabOrder = 0
            Text = '0'
            BrvReadOnlyColor = 14541539
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
          object BrvEditNum3: TBrvEditNum
            Left = 3
            Top = 64
            Width = 158
            Height = 21
            TabOrder = 1
            Text = '0'
            BrvReadOnlyColor = 14541539
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
        end
      end
      object Panel8: TPanel
        Left = 0
        Top = 535
        Width = 866
        Height = 41
        Align = alBottom
        TabOrder = 1
        ExplicitLeft = 560
        ExplicitTop = 328
        ExplicitWidth = 185
        DesignSize = (
          866
          41)
        object Label35: TLabel
          Left = 11
          Top = 17
          Width = 54
          Height = 13
          Caption = 'Registros'
          Font.Charset = DEFAULT_CHARSET
          Font.Color = clWindowText
          Font.Height = -11
          Font.Name = 'Tahoma'
          Font.Style = [fsBold]
          ParentFont = False
        end
        object EdtQtdeReg: TBrvRetCon
          Left = 71
          Top = 13
          Width = 90
          Height = 19
          TabStop = False
          Alignment = taRightJustify
          BevelInner = bvLowered
          BevelKind = bkFlat
          BevelWidth = 2
          BorderStyle = bsNone
          Font.Charset = DEFAULT_CHARSET
          Font.Color = clWindowText
          Font.Height = -11
          Font.Name = 'Tahoma'
          Font.Style = [fsBold]
          ParentColor = True
          ParentFont = False
          ReadOnly = True
          TabOrder = 0
          Text = '0'
        end
        object BtnExcel: TBrvBitBtn
          Left = 760
          Top = 7
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
          TabOrder = 1
          BrTipoBotao = BrBtnExcel
        end
        object BrvBitBtn2: TBrvBitBtn
          Left = 182
          Top = 7
          Width = 132
          Height = 25
          Caption = 'Confirma'#231#227'o'
          DoubleBuffered = True
          Font.Charset = DEFAULT_CHARSET
          Font.Color = clWindowText
          Font.Height = -11
          Font.Name = 'Tahoma'
          Font.Style = [fsBold]
          ParentDoubleBuffered = False
          ParentFont = False
          ParentShowHint = False
          ShowHint = True
          TabOrder = 2
          BrTipoBotao = BrBtnNone
        end
        object BrvBitBtn3: TBrvBitBtn
          Left = 320
          Top = 7
          Width = 146
          Height = 25
          Caption = 'Ci'#234'ncia da oper'
          DoubleBuffered = True
          Font.Charset = DEFAULT_CHARSET
          Font.Color = clWindowText
          Font.Height = -11
          Font.Name = 'Tahoma'
          Font.Style = [fsBold]
          ParentDoubleBuffered = False
          ParentFont = False
          ParentShowHint = False
          ShowHint = True
          TabOrder = 3
          BrTipoBotao = BrBtnNone
        end
        object BrvBitBtn4: TBrvBitBtn
          Left = 472
          Top = 7
          Width = 130
          Height = 25
          Caption = 'Desconhecido'
          DoubleBuffered = True
          Font.Charset = DEFAULT_CHARSET
          Font.Color = clWindowText
          Font.Height = -11
          Font.Name = 'Tahoma'
          Font.Style = [fsBold]
          ParentDoubleBuffered = False
          ParentFont = False
          ParentShowHint = False
          ShowHint = True
          TabOrder = 4
          BrTipoBotao = BrBtnNone
        end
        object BrvBitBtn9: TBrvBitBtn
          Left = 608
          Top = 7
          Width = 146
          Height = 25
          Caption = 'N'#227'o Realizado'
          DoubleBuffered = True
          Font.Charset = DEFAULT_CHARSET
          Font.Color = clWindowText
          Font.Height = -11
          Font.Name = 'Tahoma'
          Font.Style = [fsBold]
          ParentDoubleBuffered = False
          ParentFont = False
          ParentShowHint = False
          ShowHint = True
          TabOrder = 5
          BrTipoBotao = BrBtnNone
        end
      end
      object Panel9: TPanel
        Left = 0
        Top = 0
        Width = 866
        Height = 41
        Align = alTop
        TabOrder = 2
        ExplicitLeft = 472
        ExplicitTop = 360
        ExplicitWidth = 185
      end
      object Panel10: TPanel
        Left = 185
        Top = 41
        Width = 681
        Height = 494
        Align = alClient
        TabOrder = 3
        ExplicitLeft = 472
        ExplicitTop = 232
        ExplicitWidth = 185
        ExplicitHeight = 41
        object BrvDbGrid2: TBrvDbGrid
          Left = 1
          Top = 1
          Width = 679
          Height = 492
          BrShowMemo = True
          BrReadOnlyStyle = [fsItalic]
          BrReadOnlyColor = clMaroon
          Align = alClient
          DataSource = DataSource2
          PopupMenu = PopupMenu2
          TabOrder = 0
          TitleFont.Charset = DEFAULT_CHARSET
          TitleFont.Color = clWindowText
          TitleFont.Height = -11
          TitleFont.Name = 'MS Sans Serif'
          TitleFont.Style = []
          BrDrawColumn.Strings = (
            'N'#227'o remova essas duas linhas de formata'#231#227'o:'
            'CampoTabela;Operador;ValorComparativo;Cor;')
          BrGradeZebrada = True
        end
      end
    end
    object TabSheet15: TTabSheet
      Caption = 'TabSheet15'
      ImageIndex = 1
      ExplicitWidth = 281
      ExplicitHeight = 165
      object Panel2: TPanel
        Left = 169
        Top = 0
        Width = 697
        Height = 576
        Align = alClient
        TabOrder = 0
        ExplicitLeft = 136
        ExplicitTop = -266
        ExplicitWidth = 705
        ExplicitHeight = 604
        object PageControl2: TPageControl
          Left = 1
          Top = 411
          Width = 695
          Height = 164
          ActivePage = TabSheet10
          Align = alBottom
          TabOrder = 0
          ExplicitTop = 439
          ExplicitWidth = 703
          object TabSheet5: TTabSheet
            Caption = 'Respostas'
            ExplicitWidth = 695
            object MemoResp: TMemo
              Left = 0
              Top = 0
              Width = 687
              Height = 136
              Align = alClient
              ScrollBars = ssVertical
              TabOrder = 0
              ExplicitWidth = 695
            end
          end
          object TabSheet6: TTabSheet
            Caption = 'XML Resposta'
            ImageIndex = 1
            ExplicitWidth = 695
            object WBResposta: TWebBrowser
              Left = 0
              Top = 0
              Width = 687
              Height = 136
              Align = alClient
              TabOrder = 0
              ExplicitWidth = 571
              ExplicitHeight = 263
              ControlData = {
                4C000000014700000E0E00000000000000000000000000000000000000000000
                000000004C000000000000000000000001000000E0D057007335CF11AE690800
                2B2E126208000000000000004C0000000114020000000000C000000000000046
                8000000000000000000000000000000000000000000000000000000000000000
                00000000000000000100000000000000000000000000000000000000}
            end
          end
          object TabSheet8: TTabSheet
            Caption = 'Log'
            ImageIndex = 2
            ExplicitWidth = 695
            object memoLog: TMemo
              Left = 0
              Top = 0
              Width = 687
              Height = 136
              Align = alClient
              ScrollBars = ssVertical
              TabOrder = 0
              ExplicitWidth = 695
            end
          end
          object TabSheet9: TTabSheet
            Caption = 'NFe'
            ImageIndex = 3
            ExplicitLeft = 0
            ExplicitTop = 0
            ExplicitWidth = 0
            ExplicitHeight = 0
            object trvwNFe: TTreeView
              Left = 0
              Top = 0
              Width = 687
              Height = 136
              Align = alClient
              Indent = 19
              TabOrder = 0
              ExplicitWidth = 695
            end
          end
          object TabSheet10: TTabSheet
            Caption = 'Retorno Completo WS'
            ImageIndex = 4
            ExplicitWidth = 695
            object memoRespWS: TMemo
              Left = 0
              Top = 0
              Width = 687
              Height = 136
              Align = alClient
              ScrollBars = ssVertical
              TabOrder = 0
              ExplicitWidth = 695
            end
          end
          object Dados: TTabSheet
            Caption = 'Dados'
            ImageIndex = 5
            ExplicitWidth = 695
            object MemoDados: TMemo
              Left = 0
              Top = 0
              Width = 687
              Height = 136
              Align = alClient
              ScrollBars = ssVertical
              TabOrder = 0
              ExplicitWidth = 695
            end
          end
          object TabSheet11: TTabSheet
            Caption = 'RetornoConsulta NFe 2.01'
            ImageIndex = 6
            ExplicitLeft = 0
            ExplicitTop = 0
            ExplicitWidth = 0
            ExplicitHeight = 0
            object TreeViewRetornoConsulta: TTreeView
              Left = 0
              Top = 0
              Width = 687
              Height = 136
              Align = alClient
              Indent = 19
              TabOrder = 0
              ExplicitWidth = 695
            end
          end
        end
        object Panel3: TPanel
          Left = 1
          Top = 1
          Width = 703
          Height = 432
          Align = alCustom
          TabOrder = 1
          object PageControl3: TPageControl
            Left = 1
            Top = 1
            Width = 701
            Height = 430
            ActivePage = TabSheet12
            Align = alClient
            TabOrder = 0
            object tsNFe: TTabSheet
              Caption = 'NF-e'
              OnEnter = tsNFeEnter
              ExplicitLeft = 0
              ExplicitTop = 0
              ExplicitWidth = 0
              ExplicitHeight = 0
              object GroupBox2: TGroupBox
                Left = 3
                Top = 3
                Width = 206
                Height = 144
                Caption = 'Certificado'
                TabOrder = 0
                object Label1: TLabel
                  Left = 8
                  Top = 16
                  Width = 41
                  Height = 13
                  Caption = 'Caminho'
                end
                object Label2: TLabel
                  Left = 8
                  Top = 93
                  Width = 31
                  Height = 13
                  Caption = 'Senha'
                end
                object sbtnCaminhoCert: TSpeedButton
                  Left = 175
                  Top = 27
                  Width = 23
                  Height = 24
                  Glyph.Data = {
                    76010000424D7601000000000000760000002800000020000000100000000100
                    04000000000000010000130B0000130B00001000000000000000000000000000
                    800000800000008080008000000080008000808000007F7F7F00BFBFBF000000
                    FF0000FF000000FFFF00FF000000FF00FF00FFFF0000FFFFFF0033333333B333
                    333B33FF33337F3333F73BB3777BB7777BB3377FFFF77FFFF77333B000000000
                    0B3333777777777777333330FFFFFFFF07333337F33333337F333330FFFFFFFF
                    07333337F3FF3FFF7F333330F00F000F07333337F77377737F333330FFFFFFFF
                    07333FF7F3FFFF3F7FFFBBB0F0000F0F0BB37777F7777373777F3BB0FFFFFFFF
                    0BBB3777F3FF3FFF77773330F00F000003333337F773777773333330FFFF0FF0
                    33333337F3FF7F37F3333330F08F0F0B33333337F7737F77FF333330FFFF003B
                    B3333337FFFF77377FF333B000000333BB33337777777F3377FF3BB3333BB333
                    3BB33773333773333773B333333B3333333B7333333733333337}
                  NumGlyphs = 2
                  OnClick = sbtnCaminhoCertClick
                end
                object Label25: TLabel
                  Left = 8
                  Top = 53
                  Width = 79
                  Height = 13
                  Caption = 'N'#250'mero de S'#233'rie'
                end
                object sbtnGetCert: TSpeedButton
                  Left = 175
                  Top = 117
                  Width = 23
                  Height = 24
                  Glyph.Data = {
                    76010000424D7601000000000000760000002800000020000000100000000100
                    04000000000000010000130B0000130B00001000000000000000000000000000
                    800000800000008080008000000080008000808000007F7F7F00BFBFBF000000
                    FF0000FF000000FFFF00FF000000FF00FF00FFFF0000FFFFFF0033333333B333
                    333B33FF33337F3333F73BB3777BB7777BB3377FFFF77FFFF77333B000000000
                    0B3333777777777777333330FFFFFFFF07333337F33333337F333330FFFFFFFF
                    07333337F3FF3FFF7F333330F00F000F07333337F77377737F333330FFFFFFFF
                    07333FF7F3FFFF3F7FFFBBB0F0000F0F0BB37777F7777373777F3BB0FFFFFFFF
                    0BBB3777F3FF3FFF77773330F00F000003333337F773777773333330FFFF0FF0
                    33333337F3FF7F37F3333330F08F0F0B33333337F7737F77FF333330FFFF003B
                    B3333337FFFF77377FF333B000000333BB33337777777F3377FF3BB3333BB333
                    3BB33773333773333773B333333B3333333B7333333733333337}
                  NumGlyphs = 2
                  OnClick = sbtnGetCertClick
                end
                object edtCaminho: TEdit
                  Left = 8
                  Top = 29
                  Width = 161
                  Height = 21
                  TabOrder = 0
                  Text = '1A834F6D9090F03701881A2E05D0FC72'
                end
                object edtSenha: TEdit
                  Left = 8
                  Top = 112
                  Width = 161
                  Height = 21
                  PasswordChar = '*'
                  TabOrder = 1
                  Text = '1234'
                end
                object edtNumSerie: TEdit
                  Left = 8
                  Top = 69
                  Width = 161
                  Height = 21
                  TabOrder = 2
                  Text = '1A834F6D9090F03701881A2E05D0FC72'
                end
              end
              object GroupBox3: TGroupBox
                Left = 220
                Top = 272
                Width = 466
                Height = 127
                Caption = 'Geral'
                TabOrder = 1
                object Label7: TLabel
                  Left = 3
                  Top = 78
                  Width = 57
                  Height = 13
                  Caption = 'Logo Marca'
                end
                object sbtnLogoMarca: TSpeedButton
                  Left = 230
                  Top = 93
                  Width = 23
                  Height = 24
                  Glyph.Data = {
                    76010000424D7601000000000000760000002800000020000000100000000100
                    04000000000000010000130B0000130B00001000000000000000000000000000
                    800000800000008080008000000080008000808000007F7F7F00BFBFBF000000
                    FF0000FF000000FFFF00FF000000FF00FF00FFFF0000FFFFFF0033333333B333
                    333B33FF33337F3333F73BB3777BB7777BB3377FFFF77FFFF77333B000000000
                    0B3333777777777777333330FFFFFFFF07333337F33333337F333330FFFFFFFF
                    07333337F3FF3FFF7F333330F00F000F07333337F77377737F333330FFFFFFFF
                    07333FF7F3FFFF3F7FFFBBB0F0000F0F0BB37777F7777373777F3BB0FFFFFFFF
                    0BBB3777F3FF3FFF77773330F00F000003333337F773777773333330FFFF0FF0
                    33333337F3FF7F37F3333330F08F0F0B33333337F7737F77FF333330FFFF003B
                    B3333337FFFF77377FF333B000000333BB33337777777F3377FF3BB3333BB333
                    3BB33773333773333773B333333B3333333B7333333733333337}
                  NumGlyphs = 2
                  OnClick = sbtnLogoMarcaClick
                end
                object sbtnPathSalvar: TSpeedButton
                  Left = 230
                  Top = 53
                  Width = 23
                  Height = 24
                  Glyph.Data = {
                    76010000424D7601000000000000760000002800000020000000100000000100
                    04000000000000010000130B0000130B00001000000000000000000000000000
                    800000800000008080008000000080008000808000007F7F7F00BFBFBF000000
                    FF0000FF000000FFFF00FF000000FF00FF00FFFF0000FFFFFF0033333333B333
                    333B33FF33337F3333F73BB3777BB7777BB3377FFFF77FFFF77333B000000000
                    0B3333777777777777333330FFFFFFFF07333337F33333337F333330FFFFFFFF
                    07333337F3FF3FFF7F333330F00F000F07333337F77377737F333330FFFFFFFF
                    07333FF7F3FFFF3F7FFFBBB0F0000F0F0BB37777F7777373777F3BB0FFFFFFFF
                    0BBB3777F3FF3FFF77773330F00F000003333337F773777773333330FFFF0FF0
                    33333337F3FF7F37F3333330F08F0F0B33333337F7737F77FF333330FFFF003B
                    B3333337FFFF77377FF333B000000333BB33337777777F3377FF3BB3333BB333
                    3BB33773333773333773B333333B3333333B7333333733333337}
                  NumGlyphs = 2
                  OnClick = sbtnPathSalvarClick
                end
                object edtLogoMarca: TEdit
                  Left = 3
                  Top = 92
                  Width = 221
                  Height = 21
                  TabOrder = 0
                end
                object edtPathLogs: TEdit
                  Left = 3
                  Top = 59
                  Width = 221
                  Height = 21
                  TabOrder = 1
                end
                object ckSalvar: TCheckBox
                  Left = 259
                  Top = 83
                  Width = 201
                  Height = 15
                  Caption = 'Salvar Arquivos de Envio e Resposta'
                  Font.Charset = DEFAULT_CHARSET
                  Font.Color = clWindowText
                  Font.Height = -11
                  Font.Name = 'MS Sans Serif'
                  Font.Style = []
                  ParentFont = False
                  TabOrder = 2
                end
                object rgTipoDanfe: TRadioGroup
                  Left = 4
                  Top = 11
                  Width = 249
                  Height = 40
                  Caption = 'DANFE'
                  Columns = 2
                  ItemIndex = 0
                  Items.Strings = (
                    'Retrato'
                    'Paisagem')
                  TabOrder = 3
                end
                object rgFormaEmissao: TRadioGroup
                  Left = 259
                  Top = 11
                  Width = 195
                  Height = 65
                  Caption = 'Forma de Emiss'#227'o'
                  Columns = 2
                  ItemIndex = 0
                  Items.Strings = (
                    'Normal'
                    'Conting'#234'ncia'
                    'SCAN'
                    'DPEC'
                    'FSDA')
                  TabOrder = 4
                end
                object btnSalvarConfig: TBitBtn
                  Left = 322
                  Top = 99
                  Width = 132
                  Height = 24
                  Caption = 'Salvar Configura'#231#245'es'
                  DoubleBuffered = True
                  Glyph.Data = {
                    76010000424D7601000000000000760000002800000020000000100000000100
                    04000000000000010000130B0000130B00001000000000000000000000000000
                    800000800000008080008000000080008000808000007F7F7F00BFBFBF000000
                    FF0000FF000000FFFF00FF000000FF00FF00FFFF0000FFFFFF00333333330070
                    7700333333337777777733333333008088003333333377F73377333333330088
                    88003333333377FFFF7733333333000000003FFFFFFF77777777000000000000
                    000077777777777777770FFFFFFF0FFFFFF07F3333337F3333370FFFFFFF0FFF
                    FFF07F3FF3FF7FFFFFF70F00F0080CCC9CC07F773773777777770FFFFFFFF039
                    99337F3FFFF3F7F777F30F0000F0F09999937F7777373777777F0FFFFFFFF999
                    99997F3FF3FFF77777770F00F000003999337F773777773777F30FFFF0FF0339
                    99337F3FF7F3733777F30F08F0F0337999337F7737F73F7777330FFFF0039999
                    93337FFFF7737777733300000033333333337777773333333333}
                  NumGlyphs = 2
                  ParentDoubleBuffered = False
                  TabOrder = 5
                  OnClick = btnSalvarConfigClick
                end
              end
              object GroupBox4: TGroupBox
                Left = 3
                Top = 153
                Width = 206
                Height = 141
                Caption = 'WebService'
                TabOrder = 2
                object Label6: TLabel
                  Left = 8
                  Top = 16
                  Width = 121
                  Height = 13
                  Caption = 'Selecione UF de Destino:'
                  Font.Charset = DEFAULT_CHARSET
                  Font.Color = clWindowText
                  Font.Height = -11
                  Font.Name = 'MS Sans Serif'
                  Font.Style = []
                  ParentFont = False
                end
                object ckVisualizar: TCheckBox
                  Left = 5
                  Top = 118
                  Width = 153
                  Height = 17
                  Caption = 'Visualizar Mensagem'
                  Font.Charset = DEFAULT_CHARSET
                  Font.Color = clWindowText
                  Font.Height = -11
                  Font.Name = 'MS Sans Serif'
                  Font.Style = []
                  ParentFont = False
                  TabOrder = 0
                end
                object cbUF: TComboBox
                  Left = 2
                  Top = 32
                  Width = 198
                  Height = 24
                  Style = csDropDownList
                  Font.Charset = DEFAULT_CHARSET
                  Font.Color = clWindowText
                  Font.Height = -13
                  Font.Name = 'MS Sans Serif'
                  Font.Style = []
                  ItemIndex = 24
                  ParentFont = False
                  TabOrder = 1
                  Text = 'SP'
                  Items.Strings = (
                    'AC'
                    'AL'
                    'AP'
                    'AM'
                    'BA'
                    'CE'
                    'DF'
                    'ES'
                    'GO'
                    'MA'
                    'MT'
                    'MS'
                    'MG'
                    'PA'
                    'PB'
                    'PR'
                    'PE'
                    'PI'
                    'RJ'
                    'RN'
                    'RS'
                    'RO'
                    'RR'
                    'SC'
                    'SP'
                    'SE'
                    'TO')
                end
                object rgTipoAmb: TRadioGroup
                  Left = 5
                  Top = 61
                  Width = 195
                  Height = 52
                  Caption = 'Selecione o Ambiente de Destino'
                  Columns = 2
                  ItemIndex = 0
                  Items.Strings = (
                    'Produ'#231#227'o'
                    'Homologa'#231#227'o')
                  TabOrder = 2
                end
              end
              object gbProxy: TGroupBox
                Left = 3
                Top = 294
                Width = 206
                Height = 105
                Caption = 'Proxy'
                TabOrder = 3
                object Label8: TLabel
                  Left = 8
                  Top = 16
                  Width = 22
                  Height = 13
                  Caption = 'Host'
                end
                object Label9: TLabel
                  Left = 136
                  Top = 13
                  Width = 25
                  Height = 13
                  Caption = 'Porta'
                end
                object Label10: TLabel
                  Left = 8
                  Top = 56
                  Width = 36
                  Height = 13
                  Caption = 'Usu'#225'rio'
                end
                object Label11: TLabel
                  Left = 111
                  Top = 56
                  Width = 31
                  Height = 13
                  Caption = 'Senha'
                end
                object edtProxyHost: TEdit
                  Left = 8
                  Top = 32
                  Width = 124
                  Height = 21
                  TabOrder = 0
                end
                object edtProxyPorta: TEdit
                  Left = 138
                  Top = 32
                  Width = 44
                  Height = 21
                  TabOrder = 1
                end
                object edtProxyUser: TEdit
                  Left = 8
                  Top = 72
                  Width = 97
                  Height = 21
                  TabOrder = 2
                end
                object edtProxySenha: TEdit
                  Left = 111
                  Top = 72
                  Width = 71
                  Height = 21
                  PasswordChar = '*'
                  TabOrder = 3
                end
              end
              object GroupBox6: TGroupBox
                Left = 413
                Top = 0
                Width = 273
                Height = 273
                Caption = 'Dados Emissor'
                Color = clBtnFace
                ParentBackground = False
                ParentColor = False
                TabOrder = 4
                object Label12: TLabel
                  Left = 7
                  Top = 14
                  Width = 27
                  Height = 13
                  Caption = 'CNPJ'
                end
                object Label13: TLabel
                  Left = 135
                  Top = 14
                  Width = 41
                  Height = 13
                  Caption = 'Insc.Est.'
                end
                object Label14: TLabel
                  Left = 7
                  Top = 50
                  Width = 63
                  Height = 13
                  Caption = 'Raz'#227'o Social'
                end
                object Label15: TLabel
                  Left = 7
                  Top = 84
                  Width = 40
                  Height = 13
                  Caption = 'Fantasia'
                end
                object Label24: TLabel
                  Left = 7
                  Top = 118
                  Width = 24
                  Height = 13
                  Caption = 'Fone'
                end
                object Label23: TLabel
                  Left = 135
                  Top = 118
                  Width = 21
                  Height = 13
                  Caption = 'CEP'
                end
                object Label16: TLabel
                  Left = 8
                  Top = 152
                  Width = 54
                  Height = 13
                  Caption = 'Logradouro'
                end
                object Label17: TLabel
                  Left = 207
                  Top = 152
                  Width = 37
                  Height = 13
                  Caption = 'N'#250'mero'
                end
                object Label18: TLabel
                  Left = 8
                  Top = 188
                  Width = 64
                  Height = 13
                  Caption = 'Complemento'
                end
                object Label19: TLabel
                  Left = 136
                  Top = 188
                  Width = 27
                  Height = 13
                  Caption = 'Bairro'
                end
                object Label20: TLabel
                  Left = 9
                  Top = 225
                  Width = 61
                  Height = 13
                  Caption = 'C'#243'd. Cidade '
                end
                object Label21: TLabel
                  Left = 76
                  Top = 225
                  Width = 33
                  Height = 13
                  Caption = 'Cidade'
                end
                object Label22: TLabel
                  Left = 224
                  Top = 228
                  Width = 14
                  Height = 13
                  Caption = 'UF'
                end
                object edtEmitCNPJ: TEdit
                  Left = 7
                  Top = 30
                  Width = 123
                  Height = 21
                  TabOrder = 0
                end
                object edtEmitIE: TEdit
                  Left = 136
                  Top = 30
                  Width = 123
                  Height = 21
                  TabOrder = 1
                end
                object edtEmitRazao: TEdit
                  Left = 7
                  Top = 63
                  Width = 252
                  Height = 21
                  TabOrder = 2
                end
                object edtEmitFantasia: TEdit
                  Left = 7
                  Top = 97
                  Width = 252
                  Height = 21
                  TabOrder = 3
                end
                object edtEmitFone: TEdit
                  Left = 7
                  Top = 131
                  Width = 125
                  Height = 21
                  TabOrder = 4
                end
                object edtEmitCEP: TEdit
                  Left = 136
                  Top = 131
                  Width = 123
                  Height = 21
                  TabOrder = 5
                end
                object edtEmitLogradouro: TEdit
                  Left = 7
                  Top = 165
                  Width = 196
                  Height = 21
                  TabOrder = 6
                end
                object edtEmitNumero: TEdit
                  Left = 209
                  Top = 165
                  Width = 50
                  Height = 21
                  TabOrder = 7
                end
                object edtEmitComp: TEdit
                  Left = 7
                  Top = 203
                  Width = 123
                  Height = 21
                  TabOrder = 8
                end
                object edtEmitBairro: TEdit
                  Left = 133
                  Top = 203
                  Width = 126
                  Height = 21
                  TabOrder = 9
                end
                object edtEmitCodCidade: TEdit
                  Left = 9
                  Top = 241
                  Width = 61
                  Height = 21
                  TabOrder = 10
                end
                object edtEmitCidade: TEdit
                  Left = 75
                  Top = 241
                  Width = 142
                  Height = 21
                  TabOrder = 11
                end
                object edtEmitUF: TEdit
                  Left = 223
                  Top = 240
                  Width = 36
                  Height = 21
                  TabOrder = 12
                end
              end
              object GroupBox5: TGroupBox
                Left = 215
                Top = 0
                Width = 194
                Height = 273
                Caption = 'Email'
                TabOrder = 5
                object Label3: TLabel
                  Left = 8
                  Top = 16
                  Width = 72
                  Height = 13
                  Caption = 'Servidor SMTP'
                end
                object Label4: TLabel
                  Left = 134
                  Top = 16
                  Width = 25
                  Height = 13
                  Caption = 'Porta'
                end
                object Label5: TLabel
                  Left = 8
                  Top = 56
                  Width = 36
                  Height = 13
                  Caption = 'Usu'#225'rio'
                end
                object Label26: TLabel
                  Left = 93
                  Top = 56
                  Width = 31
                  Height = 13
                  Caption = 'Senha'
                end
                object Label27: TLabel
                  Left = 8
                  Top = 96
                  Width = 121
                  Height = 13
                  Caption = 'Assunto do email enviado'
                end
                object Label28: TLabel
                  Left = 8
                  Top = 168
                  Width = 95
                  Height = 13
                  Caption = 'Mensagem do Email'
                end
                object edtSmtpHost: TEdit
                  Left = 5
                  Top = 32
                  Width = 123
                  Height = 21
                  TabOrder = 0
                end
                object edtSmtpPort: TEdit
                  Left = 134
                  Top = 32
                  Width = 51
                  Height = 21
                  TabOrder = 1
                end
                object edtSmtpUser: TEdit
                  Left = 8
                  Top = 72
                  Width = 81
                  Height = 21
                  TabOrder = 2
                end
                object edtSmtpPass: TEdit
                  Left = 95
                  Top = 73
                  Width = 90
                  Height = 21
                  TabOrder = 3
                end
                object edtEmailAssunto: TEdit
                  Left = 8
                  Top = 112
                  Width = 177
                  Height = 21
                  TabOrder = 4
                end
                object cbEmailSSL: TCheckBox
                  Left = 10
                  Top = 144
                  Width = 167
                  Height = 17
                  Caption = 'SMTP exige conex'#227'o segura'
                  TabOrder = 5
                end
                object mmEmailMsg: TMemo
                  Left = 8
                  Top = 184
                  Width = 177
                  Height = 70
                  TabOrder = 6
                end
              end
            end
            object TabSheet12: TTabSheet
              Caption = 'TabSheet12'
              ImageIndex = 1
              object Panel4: TPanel
                Left = 0
                Top = 0
                Width = 693
                Height = 49
                Align = alTop
                TabOrder = 0
                object Label36: TLabel
                  Left = 13
                  Top = 18
                  Width = 104
                  Height = 18
                  Caption = 'Chave da NF-e'
                  Font.Charset = DEFAULT_CHARSET
                  Font.Color = clBlack
                  Font.Height = -13
                  Font.Name = 'Arial Black'
                  Font.Style = []
                  ParentFont = False
                end
                object Edit2: TEdit
                  Left = 127
                  Top = 15
                  Width = 434
                  Height = 21
                  TabOrder = 0
                end
              end
              object BrvDbGrid1: TBrvDbGrid
                Left = 0
                Top = 49
                Width = 693
                Height = 353
                BrShowMemo = True
                BrReadOnlyStyle = [fsItalic]
                BrReadOnlyColor = clMaroon
                Align = alClient
                DataSource = DataSource1
                Options = [dgEditing, dgTitles, dgIndicator, dgColumnResize, dgColLines, dgRowLines, dgTabs]
                TabOrder = 1
                TitleFont.Charset = DEFAULT_CHARSET
                TitleFont.Color = clWindowText
                TitleFont.Height = -11
                TitleFont.Name = 'MS Sans Serif'
                TitleFont.Style = []
                BrDrawColumn.Strings = (
                  'N'#227'o remova essas duas linhas de formata'#231#227'o:'
                  'CampoTabela;Operador;ValorComparativo;Cor;')
                BrGradeZebrada = True
                Columns = <
                  item
                    ButtonStyle = cbsNone
                    Expanded = False
                    FieldName = 'SnMarcad'
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
                    FieldName = 'CHNFE'
                    Visible = True
                    BrConsulta = 0
                    BrPermissao = []
                    BrValueHalfChecked = False
                    BrSaveOnClick = False
                  end
                  item
                    Expanded = False
                    FieldName = 'IDE_NNF'
                    Visible = True
                    BrConsulta = 0
                    BrPermissao = []
                    BrValueHalfChecked = False
                    BrSaveOnClick = False
                  end
                  item
                    Expanded = False
                    FieldName = 'IDE_SERIE'
                    Visible = True
                    BrConsulta = 0
                    BrPermissao = []
                    BrValueHalfChecked = False
                    BrSaveOnClick = False
                  end
                  item
                    Expanded = False
                    FieldName = 'IDE_DEMI'
                    Visible = True
                    BrConsulta = 0
                    BrPermissao = []
                    BrValueHalfChecked = False
                    BrSaveOnClick = False
                  end
                  item
                    Expanded = False
                    FieldName = 'IDE_DSAIENT'
                    Visible = True
                    BrConsulta = 0
                    BrPermissao = []
                    BrValueHalfChecked = False
                    BrSaveOnClick = False
                  end
                  item
                    Expanded = False
                    FieldName = 'EMIT_CNPJ'
                    Visible = True
                    BrConsulta = 0
                    BrPermissao = []
                    BrValueHalfChecked = False
                    BrSaveOnClick = False
                  end
                  item
                    Expanded = False
                    FieldName = 'EMIT_XNOME'
                    Visible = True
                    BrConsulta = 0
                    BrPermissao = []
                    BrValueHalfChecked = False
                    BrSaveOnClick = False
                  end
                  item
                    Expanded = False
                    FieldName = 'NPROT'
                    Visible = True
                    BrConsulta = 0
                    BrPermissao = []
                    BrValueHalfChecked = False
                    BrSaveOnClick = False
                  end
                  item
                    Expanded = False
                    FieldName = 'XMOTIVO'
                    Visible = True
                    BrConsulta = 0
                    BrPermissao = []
                    BrValueHalfChecked = False
                    BrSaveOnClick = False
                  end
                  item
                    Expanded = False
                    FieldName = 'XML'
                    Visible = True
                    BrConsulta = 0
                    BrPermissao = []
                    BrValueHalfChecked = False
                    BrSaveOnClick = False
                  end>
              end
            end
            object TabSheet13: TTabSheet
              Caption = 'TabSheet13'
              ImageIndex = 2
              object Panel6: TPanel
                Left = 0
                Top = 0
                Width = 693
                Height = 65
                Align = alTop
                TabOrder = 0
                object Label30: TLabel
                  Left = 13
                  Top = 26
                  Width = 104
                  Height = 18
                  Caption = 'Chave da NF-e'
                  Font.Charset = DEFAULT_CHARSET
                  Font.Color = clBlack
                  Font.Height = -13
                  Font.Name = 'Arial Black'
                  Font.Style = []
                  ParentFont = False
                end
                object Edit1: TEdit
                  Left = 127
                  Top = 23
                  Width = 434
                  Height = 21
                  TabOrder = 0
                end
                object BrvBitBtn1: TBrvBitBtn
                  Left = 572
                  Top = 23
                  Width = 52
                  Height = 21
                  Caption = '&Buscar'
                  DoubleBuffered = True
                  Font.Charset = DEFAULT_CHARSET
                  Font.Color = clWindowText
                  Font.Height = -11
                  Font.Name = 'Tahoma'
                  Font.Style = [fsBold]
                  ParentDoubleBuffered = False
                  ParentFont = False
                  ParentShowHint = False
                  ShowHint = True
                  TabOrder = 1
                  OnClick = BrvBitBtn1Click
                  BrTipoBotao = BrBtnNone
                end
              end
            end
            object TabSheet1: TTabSheet
              Caption = 'TabSheet1'
              ImageIndex = 5
            end
            object TabSheet2: TTabSheet
              Caption = 'TabSheet2'
              ImageIndex = 6
            end
            object TabSheet3: TTabSheet
              Caption = 'TabSheet3'
              ImageIndex = 7
            end
            object TabSheet4: TTabSheet
              Caption = 'TabSheet4'
              ImageIndex = 8
            end
            object TabSheet7: TTabSheet
              Caption = 'TabSheet7'
              ImageIndex = 9
            end
            object TabSheet16: TTabSheet
              Caption = 'TabSheet16'
              ImageIndex = 10
            end
            object TabSheet17: TTabSheet
              Caption = 'TabSheet17'
              ImageIndex = 11
            end
            object TabSheet18: TTabSheet
              Caption = 'TabSheet18'
              ImageIndex = 12
            end
          end
        end
      end
      object Panel1: TPanel
        Left = 0
        Top = 0
        Width = 169
        Height = 576
        Align = alLeft
        TabOrder = 1
        ExplicitTop = 407
        ExplicitHeight = 866
        object btnStatusServ: TButton
          Left = 9
          Top = 50
          Width = 144
          Height = 25
          Caption = 'Status Servi'#231'o'
          TabOrder = 0
          OnClick = btnStatusServClick
        end
        object btnConsultarChave: TButton
          Left = 9
          Top = 112
          Width = 144
          Height = 25
          Caption = 'Consultar pela Chave'
          TabOrder = 1
          OnClick = btnConsultarChaveClick
        end
        object btnCancelarChave: TButton
          Left = 9
          Top = 143
          Width = 144
          Height = 25
          Caption = 'Cancelamento NFe pela Chave'
          TabOrder = 2
          OnClick = btnCancelarChaveClick
        end
        object btnConsultarRecibo: TButton
          Left = 9
          Top = 174
          Width = 144
          Height = 25
          Caption = 'Consultar Recibo Lote'
          TabOrder = 3
          OnClick = btnConsultarReciboClick
        end
        object btnConsCad: TButton
          Left = 9
          Top = 205
          Width = 144
          Height = 25
          Caption = 'Consulta Cadastro'
          TabOrder = 4
          OnClick = btnConsCadClick
        end
        object btnGerarPDF: TButton
          Left = 9
          Top = 236
          Width = 144
          Height = 25
          Caption = 'Gerar PDF'
          TabOrder = 5
          OnClick = btnGerarPDFClick
        end
        object btnImprimir: TButton
          Left = 9
          Top = 267
          Width = 144
          Height = 25
          Caption = 'Imprimir DANFE'
          TabOrder = 6
          OnClick = btnImprimirClick
        end
        object btnManifDestConfirmacao: TButton
          Left = 9
          Top = 298
          Width = 144
          Height = 25
          Caption = 'Manif. Dest. - Conf. Opera'#231#227'o'
          TabOrder = 7
          OnClick = btnManifDestConfirmacaoClick
        end
        object btnEnviarEmail: TButton
          Left = 9
          Top = 329
          Width = 144
          Height = 25
          Caption = 'Enviar NFe Email'
          TabOrder = 8
          OnClick = btnEnviarEmailClick
        end
        object btnCartadeCorrecao: TButton
          Left = 9
          Top = 360
          Width = 144
          Height = 25
          Caption = 'Carta de Corre'#231#227'o'
          TabOrder = 9
          OnClick = btnCartadeCorrecaoClick
        end
        object btnNfeDestinadas: TButton
          Left = 9
          Top = 391
          Width = 144
          Height = 25
          Caption = 'Consulta NFe Destinadas'
          TabOrder = 10
          OnClick = btnNfeDestinadasClick
        end
        object Button1: TButton
          Left = 9
          Top = 81
          Width = 144
          Height = 25
          Caption = 'Download NFe pela Chave'
          TabOrder = 11
          OnClick = Button1Click
        end
        object Button2: TButton
          Left = 9
          Top = 19
          Width = 144
          Height = 25
          Caption = 'Configura'#231#245'es'
          TabOrder = 12
          OnClick = Button2Click
        end
      end
    end
  end
  object OpenDialog1: TOpenDialog
    DefaultExt = '*-nfe.XML'
    Filter = 
      'Arquivos NFE (*-nfe.XML)|*-nfe.XML|Arquivos XML (*.XML)|*.XML|To' +
      'dos os Arquivos (*.*)|*.*'
    Title = 'Selecione a NFe'
    Left = 286
    Top = 142
  end
  object ACBrNFe1: TACBrNFe
    Configuracoes.Geral.FormaEmissao = teContingencia
    Configuracoes.Geral.PathSalvar = 'C:\Program Files\Borland\Delphi7\Bin\'
    Configuracoes.WebServices.UF = 'SP'
    Configuracoes.WebServices.AguardarConsultaRet = 15000
    Configuracoes.WebServices.IntervaloTentativas = 1000
    Configuracoes.WebServices.AjustaAguardaConsultaRet = True
    OnStatusChange = ACBrNFe1StatusChange
    OnGerarLog = ACBrNFe1GerarLog
    Left = 319
    Top = 120
  end
  object ACBrNFeDANFERave1: TACBrNFeDANFERave
    PathPDF = 'C:\Program Files\Borland\Delphi7\Bin\'
    MostrarPreview = True
    MostrarStatus = True
    TipoDANFE = tiRetrato
    NumCopias = 1
    ImprimirDescPorc = True
    ImprimirTotalLiquido = True
    MargemInferior = 0.800000000000000000
    MargemSuperior = 0.800000000000000000
    MargemEsquerda = 0.600000000000000000
    MargemDireita = 0.510000000000000000
    CasasDecimais._qCom = 2
    CasasDecimais._vUnCom = 2
    ExibirResumoCanhoto = False
    FormularioContinuo = False
    TamanhoFonte_DemaisCampos = 10
    ProdutosPorPagina = 0
    ImprimirDetalhamentoEspecifico = True
    NFeCancelada = False
    LocalImpCanhoto = 0
    RavFile = 
      'D:\delphi\ACBr\trunk\Exemplos\ACBrNFe2\Delphi\Report\NotaFiscalE' +
      'letronica.rav'
    EspessuraBorda = 1
    TamanhoFonte_RazaoSocial = 12
    TamanhoFonte_ANTT = 10
    Left = 310
    Top = 72
  end
  object BrvXMLNFE: TBrvXML
    BrGerarBanco = True
    BrQtdePagDanfe = 0
    Left = 424
    Top = 32
  end
  object DataSource1: TDataSource
    Left = 384
    Top = 216
  end
  object xml: TClientDataSet
    Aggregates = <>
    Params = <>
    ProviderName = 'DataSetProvider1'
    Left = 360
    Top = 16
  end
  object PopupMenu1: TPopupMenu
    Left = 416
    Top = 56
    object Detalhar1: TMenuItem
      Caption = 'Detalhar'
    end
    object Imprimir1: TMenuItem
      Caption = 'Imprimir'
    end
  end
  object mxExcel: TmxDataSetExport
    Captions.Strings = (
      'fsdfd'
      'dds'
      'sdfds'
      'sdf'
      'dsf'
      'dsf'
      'dsdsf'
      'dsf'
      'dfsd'
      'sdfds'
      'sdf'
      'sdf'
      'sdf'
      'sdf'
      'dsf'
      'dsf'
      'dsf'
      'sdf'
      'sdf'
      'dsf'
      'dsf'
      'df'
      'df'
      'df'
      'sdf'
      'dsfds'
      'fd'
      'dsf'
      'df'
      'df'
      'df'
      'ds'
      'fds'
      'fdsf'
      'dsf'
      'sd'
      'fd'
      'f'
      'dsf'
      'ss'
      'd'
      'd'
      'sd')
    DateFormat = 'dd/MM/yyyy'
    TimeFormat = 'hh:mm'
    DateTimeFormat = 'hh:mm dd/MM/yyyy'
    ExportType = xtExcel
    ExportTypes = [xtExcel]
    ExportStyle = xsFile
    HTML.CustomColors.Background = clWhite
    HTML.CustomColors.DefaultLink = clRed
    HTML.CustomColors.DefaultFontFace = 'Arial,Helvetica'
    HTML.CustomColors.VisitedLink = clAqua
    HTML.CustomColors.ActiveLink = clBlue
    HTML.CustomColors.DefaultText = clBlack
    HTML.CustomColors.TableFontColor = clBlack
    HTML.CustomColors.TableFontFace = 'Arial,Helvetica'
    HTML.CustomColors.TableBackground = 16777167
    HTML.CustomColors.TableOddBackground = clWhite
    HTML.CustomColors.HeaderBackground = 3368601
    HTML.CustomColors.HeadersFontColor = clWhite
    HTML.Options = [hoShowGridLines, hoBoldHeaders, hoAutoLink, hoOddRowColoring, hoDisplayTitle]
    HTML.Template = ctStandard
    Messages.Caption = 'Exporting DataSet'
    Messages.CopiedToClipboard = 'Data was copied to clipboard!'
    Messages.CancelCaption = '&Cancel'
    Messages.CreatedText = 'Created:'
    Messages.DocumentFilter.HTML = 'HTML Documents'
    Messages.DocumentFilter.Excel = 'Excel Files'
    Messages.DocumentFilter.Word = 'Word Documents'
    Messages.DocumentFilter.Text = 'Text Files'
    Messages.DocumentFilter.Comma = 'CSV (Comma delimited)'
    Messages.DocumentFilter.Tab = 'Text (Tab delimited)'
    Messages.DocumentFilter.RTF = 'Rich Text Format'
    Messages.DocumentFilter.DIF = 'Data Interchange Format'
    Messages.DocumentFilter.SYLK = 'SYLK Files'
    Messages.ExportCaption = '&Export'
    Messages.ExportToFile = 'Export &to file'
    Messages.FalseText = 'False'
    Messages.Height = 80
    Messages.SaveTitle = 'Save document'
    Messages.SelectFormat = 'E&xport formats:'
    Messages.Text = 'Processing...'
    Messages.TrueText = 'True'
    Messages.Width = 300
    Messages.ViewOnly = '&View only'
    TruncateSymbol = '...'
    RowNumberFormat = '%d'
    DOC_RTF.Template = rtStandard
    DOC_RTF.Options = [roShowGridLines, roOddRowColoring]
    DOC_RTF.CustomSettings.TableBackground = 16777167
    DOC_RTF.CustomSettings.TableOddBackground = clWhite
    DOC_RTF.CustomSettings.HeaderBackground = 3368601
    DOC_RTF.CustomSettings.DefaultFont.Charset = DEFAULT_CHARSET
    DOC_RTF.CustomSettings.DefaultFont.Color = clWindowText
    DOC_RTF.CustomSettings.DefaultFont.Height = -11
    DOC_RTF.CustomSettings.DefaultFont.Name = 'Tahoma'
    DOC_RTF.CustomSettings.DefaultFont.Style = []
    DOC_RTF.CustomSettings.HeaderFont.Charset = DEFAULT_CHARSET
    DOC_RTF.CustomSettings.HeaderFont.Color = clWindowText
    DOC_RTF.CustomSettings.HeaderFont.Height = -11
    DOC_RTF.CustomSettings.HeaderFont.Name = 'Tahoma'
    DOC_RTF.CustomSettings.HeaderFont.Style = [fsBold]
    DOC_RTF.CustomSettings.TableFont.Charset = DEFAULT_CHARSET
    DOC_RTF.CustomSettings.TableFont.Color = clWindowText
    DOC_RTF.CustomSettings.TableFont.Height = -11
    DOC_RTF.CustomSettings.TableFont.Name = 'Tahoma'
    DOC_RTF.CustomSettings.TableFont.Style = []
    DOC_RTF.CellWidth = 1400
    DOC_RTF.TopMargin = 101
    DOC_RTF.BottomMargin = 101
    DOC_RTF.LeftMargin = 461
    DOC_RTF.RightMargin = 562
    EXCEL.Options = [reSetMargins, reUseBorders]
    EXCEL.ColumnWidth = 20
    EXCEL.Protected = False
    EXCEL.Footer = '&P'
    EXCEL.DefaultFont.Charset = DEFAULT_CHARSET
    EXCEL.DefaultFont.Color = clWindowText
    EXCEL.DefaultFont.Height = -11
    EXCEL.DefaultFont.Name = 'Tahoma'
    EXCEL.DefaultFont.Style = []
    EXCEL.HeaderFont.Charset = DEFAULT_CHARSET
    EXCEL.HeaderFont.Color = clWindowText
    EXCEL.HeaderFont.Height = -11
    EXCEL.HeaderFont.Name = 'Tahoma'
    EXCEL.HeaderFont.Style = [fsBold]
    EXCEL.TableFont.Charset = DEFAULT_CHARSET
    EXCEL.TableFont.Color = clWindowText
    EXCEL.TableFont.Height = -11
    EXCEL.TableFont.Name = 'Tahoma'
    EXCEL.TableFont.Style = []
    EXCEL.TopMargin = 0.300000000000000000
    EXCEL.BottomMargin = 0.300000000000000000
    EXCEL.LeftMargin = 0.300000000000000000
    EXCEL.RightMargin = 0.300000000000000000
    Options = [xoClipboardMessage, xoExportInvisibles, xoFooterLine, xoHeaderLine, xoShowExportDate, xoShowHeader, xoShowProgress, xoUseAlignments]
    Version = '2.38'
    Left = 232
    Top = 176
  end
  object PopupMenu2: TPopupMenu
    Left = 536
    Top = 32
    object MenuItem1: TMenuItem
      Caption = 'Detalhar'
    end
    object MenuItem2: TMenuItem
      Caption = 'Imprimir'
    end
    object GerarPDF1: TMenuItem
      Caption = 'Gerar PDF'
    end
    object Cancelar1: TMenuItem
      Caption = 'Cancelar'
    end
    object Deletar1: TMenuItem
      Caption = 'Deletar'
    end
  end
  object DataSource2: TDataSource
    DataSet = SQLTable1
    Left = 320
    Top = 288
  end
  object SQLConnection1: TSQLConnection
    ConnectionName = 'FBCONNECTION'
    DriverName = 'Firebird'
    GetDriverFunc = 'getSQLDriverINTERBASE'
    LibraryName = 'dbxfb.dll'
    LoadParamsOnConnect = True
    LoginPrompt = False
    Params.Strings = (
      'drivername=Firebird'
      
        'Database=C:\Documents and Settings\Administrador\Desktop\Delphi\' +
        'MANIFESTO.fdb'
      'rolename=RoleName'
      'user_name=sysdba'
      'Password=masterkey'
      'sqldialect=3'
      'localecode=0000'
      'blobsize=-1'
      'commitretain=False'
      'waitonlocks=True'
      'isolationlevel=ReadCommitted'
      'trim char=False'
      
        'ConnectionString=drivername=Firebird,Database=C:\Documents and S' +
        'ettings\Administrador\Desktop\Delphi\MANIFESTO.fdb,rolename=Role' +
        'Name,user_name=sysdba,Password=masterkey,sqldialect=3,localecode' +
        '=0000,blobsize=-1,commitretain=False,waitonlocks=True,isolationl' +
        'evel=ReadCommitted,trim char=False')
    VendorLib = 'fbclient.DLL'
    Connected = True
    Left = 568
    Top = 200
  end
  object DataSetProvider1: TDataSetProvider
    DataSet = SQLDataSet1
    Options = [poFetchBlobsOnDemand, poAllowCommandText, poUseQuoteChar]
    Left = 576
    Top = 312
  end
  object SQLDataSet1: TSQLDataSet
    SchemaName = 'sysdba'
    CommandText = 'select *  from NOTAS'
    DbxCommandType = 'Dbx.SQL'
    DataSource = DataSource2
    MaxBlobSize = -1
    Params = <>
    SQLConnection = SQLConnection1
    Left = 568
    Top = 256
  end
  object SQLTable1: TSQLTable
    Active = True
    MaxBlobSize = -1
    SQLConnection = SQLConnection1
    TableName = 'NOTAS'
    Left = 440
    Top = 304
    object SQLTable1XMOTIVO: TStringField
      FieldName = 'XMOTIVO'
      Size = 99
    end
    object SQLTable1CHNFE: TStringField
      FieldName = 'CHNFE'
      Required = True
      Size = 50
    end
    object SQLTable1DHRECBTO: TStringField
      FieldName = 'DHRECBTO'
      Size = 50
    end
    object SQLTable1NPROT: TStringField
      FieldName = 'NPROT'
      Size = 50
    end
    object SQLTable1IDE_MOD: TStringField
      FieldName = 'IDE_MOD'
      Size = 5
    end
    object SQLTable1IDE_NATOP: TStringField
      FieldName = 'IDE_NATOP'
      Size = 99
    end
    object SQLTable1IDE_NNF: TStringField
      FieldName = 'IDE_NNF'
    end
    object SQLTable1IDE_SERIE: TStringField
      FieldName = 'IDE_SERIE'
    end
    object SQLTable1IDE_TPIMP: TStringField
      FieldName = 'IDE_TPIMP'
    end
    object SQLTable1IDE_TPEMIS: TStringField
      FieldName = 'IDE_TPEMIS'
    end
    object SQLTable1IDE_CDV: TStringField
      FieldName = 'IDE_CDV'
    end
    object SQLTable1IDE_TPAMB: TStringField
      FieldName = 'IDE_TPAMB'
    end
    object SQLTable1IDE_DEMI: TStringField
      FieldName = 'IDE_DEMI'
    end
    object SQLTable1IDE_DSAIENT: TStringField
      FieldName = 'IDE_DSAIENT'
    end
    object SQLTable1IDE_HSAIENT: TStringField
      FieldName = 'IDE_HSAIENT'
    end
    object SQLTable1EMIT_XNOME: TStringField
      FieldName = 'EMIT_XNOME'
      Size = 99
    end
    object SQLTable1EMIT_CNPJ: TStringField
      FieldName = 'EMIT_CNPJ'
      Size = 30
    end
    object SQLTable1EMIT_IE: TStringField
      FieldName = 'EMIT_IE'
      Size = 30
    end
    object SQLTable1EMIT_CRT: TStringField
      FieldName = 'EMIT_CRT'
      Size = 5
    end
    object SQLTable1ENDEREMIT_XLGR: TStringField
      FieldName = 'ENDEREMIT_XLGR'
      Size = 99
    end
    object SQLTable1ENDEREMIT_NRO: TStringField
      FieldName = 'ENDEREMIT_NRO'
    end
    object SQLTable1ENDEREMIT_XBAIRRO: TStringField
      FieldName = 'ENDEREMIT_XBAIRRO'
      Size = 99
    end
    object SQLTable1ENDEREMIT_XMUN: TStringField
      FieldName = 'ENDEREMIT_XMUN'
      Size = 99
    end
    object SQLTable1ENDEREMIT_CEP: TStringField
      FieldName = 'ENDEREMIT_CEP'
    end
    object SQLTable1ENDEREMIT_FONE: TStringField
      FieldName = 'ENDEREMIT_FONE'
    end
    object SQLTable1ENDEREMIT_UF: TStringField
      FieldName = 'ENDEREMIT_UF'
      Size = 5
    end
    object SQLTable1DEST_XNOME: TStringField
      FieldName = 'DEST_XNOME'
      Size = 99
    end
    object SQLTable1DEST_CNPJ: TStringField
      FieldName = 'DEST_CNPJ'
      Size = 30
    end
    object SQLTable1DEST_CPF: TStringField
      FieldName = 'DEST_CPF'
      Size = 30
    end
    object SQLTable1DEST_IE: TStringField
      FieldName = 'DEST_IE'
      Size = 30
    end
    object SQLTable1DEST_EMAIL: TStringField
      FieldName = 'DEST_EMAIL'
      Size = 99
    end
    object SQLTable1ENDERDEST_XLGR: TStringField
      FieldName = 'ENDERDEST_XLGR'
      Size = 99
    end
    object SQLTable1ENDERDEST_NRO: TStringField
      FieldName = 'ENDERDEST_NRO'
    end
    object SQLTable1ENDERDEST_XBAIRRO: TStringField
      FieldName = 'ENDERDEST_XBAIRRO'
      Size = 99
    end
    object SQLTable1ENDERDEST_XMUN: TStringField
      FieldName = 'ENDERDEST_XMUN'
      Size = 99
    end
    object SQLTable1ENDERDEST_CEP: TStringField
      FieldName = 'ENDERDEST_CEP'
    end
    object SQLTable1ENDERDEST_FONE: TStringField
      FieldName = 'ENDERDEST_FONE'
      Size = 30
    end
    object SQLTable1ENDERDEST_UF: TStringField
      FieldName = 'ENDERDEST_UF'
      Size = 5
    end
    object SQLTable1XML: TStringField
      FieldName = 'XML'
      Size = 10000
    end
    object SQLTable1STMANIFESTO: TIntegerField
      FieldName = 'STMANIFESTO'
    end
  end
end
