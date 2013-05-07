object Form1: TForm1
  Left = 269
  Top = 149
  Caption = 'ACBrNFe - Demonstra'#231#227'o'
  ClientHeight = 680
  ClientWidth = 980
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
  object Label40: TLabel
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
  object Label57: TLabel
    Left = 277
    Top = 62
    Width = 154
    Height = 13
    Caption = 'Total Opera'#231#227'o Confirmada'
    Font.Charset = DEFAULT_CHARSET
    Font.Color = clWindowText
    Font.Height = -11
    Font.Name = 'Tahoma'
    Font.Style = [fsBold]
    ParentFont = False
  end
  object PageControl1: TPageControl
    Left = 0
    Top = 0
    Width = 980
    Height = 680
    ActivePage = TabSheet14
    Align = alClient
    TabOrder = 0
    object TabSheet14: TTabSheet
      Caption = 'Pagina Inicial'
      object Panel7: TPanel
        Left = 0
        Top = 41
        Width = 185
        Height = 575
        Align = alLeft
        TabOrder = 0
        object GroupBox1: TGroupBox
          Left = 8
          Top = 6
          Width = 171
          Height = 107
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
          Left = 9
          Top = 114
          Width = 171
          Height = 87
          Caption = 'Data Emiss'#227'o'
          TabOrder = 1
          object Label31: TLabel
            Left = 7
            Top = 11
            Width = 53
            Height = 13
            Caption = 'Data Inicial'
          end
          object Label32: TLabel
            Left = 7
            Top = 46
            Width = 48
            Height = 13
            Caption = 'Data Final'
          end
          object BrvEditDate1: TBrvEditDate
            Left = 7
            Top = 26
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
            Top = 59
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
          Left = 9
          Top = 201
          Width = 171
          Height = 46
          Caption = 'Chave Acesso'
          TabOrder = 2
          object BrvEditNum1: TBrvEditNum
            Left = 3
            Top = 18
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
          Left = 9
          Top = 317
          Width = 171
          Height = 99
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
        object GroupBox10: TGroupBox
          Left = 8
          Top = 422
          Width = 171
          Height = 65
          Caption = 'Envio'
          TabOrder = 4
          object CheckBox7: TCheckBox
            Left = 8
            Top = 14
            Width = 97
            Height = 14
            Caption = 'Trasmitida'
            Checked = True
            State = cbChecked
            TabOrder = 0
          end
          object CheckBox8: TCheckBox
            Left = 8
            Top = 30
            Width = 97
            Height = 14
            Caption = 'N'#227'o Transmitida'
            Checked = True
            State = cbChecked
            TabOrder = 1
          end
          object CheckBox15: TCheckBox
            Left = 8
            Top = 47
            Width = 97
            Height = 14
            Caption = 'Camcelada'
            Checked = True
            State = cbChecked
            TabOrder = 2
          end
        end
        object GroupBox11: TGroupBox
          Left = 8
          Top = 253
          Width = 171
          Height = 58
          Caption = 'Tipo NFe'
          TabOrder = 5
          object CheckBox5: TCheckBox
            Left = 6
            Top = 17
            Width = 97
            Height = 17
            Caption = 'Entrada'
            Checked = True
            State = cbChecked
            TabOrder = 0
          end
          object CheckBox6: TCheckBox
            Left = 6
            Top = 35
            Width = 97
            Height = 17
            Caption = 'Saida'
            Checked = True
            State = cbChecked
            TabOrder = 1
          end
        end
        object GroupBox12: TGroupBox
          Left = 8
          Top = 493
          Width = 171
          Height = 70
          Caption = 'Tipo'
          TabOrder = 6
          object CheckBox9: TCheckBox
            Left = 8
            Top = 14
            Width = 97
            Height = 17
            Caption = 'NFe'
            Checked = True
            State = cbChecked
            TabOrder = 0
          end
          object CheckBox10: TCheckBox
            Left = 8
            Top = 32
            Width = 61
            Height = 17
            Caption = 'CTe'
            Checked = True
            State = cbChecked
            TabOrder = 1
          end
          object CheckBox11: TCheckBox
            Left = 8
            Top = 50
            Width = 61
            Height = 17
            Caption = 'NFSe'
            Checked = True
            State = cbChecked
            TabOrder = 2
          end
        end
      end
      object Panel8: TPanel
        Left = 0
        Top = 616
        Width = 972
        Height = 36
        Align = alBottom
        TabOrder = 1
        DesignSize = (
          972
          36)
        object BtnExcel: TBrvBitBtn
          Left = 836
          Top = 6
          Width = 132
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
          BrTipoBotao = BrBtnExcel
        end
      end
      object Panel9: TPanel
        Left = 0
        Top = 0
        Width = 972
        Height = 41
        Align = alTop
        TabOrder = 2
        object Memo11: TMemo
          Left = 319
          Top = 2
          Width = 108
          Height = 33
          Lines.Strings = (
            'Memo11')
          TabOrder = 0
        end
      end
      object Panel10: TPanel
        Left = 185
        Top = 41
        Width = 787
        Height = 575
        Align = alClient
        TabOrder = 3
        object BrvDbGrid2: TBrvDbGrid
          AlignWithMargins = True
          Left = 4
          Top = 4
          Width = 779
          Height = 411
          BrShowMemo = True
          BrReadOnlyStyle = [fsItalic]
          BrReadOnlyColor = clMaroon
          Align = alTop
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
        object Panel20: TPanel
          Left = 1
          Top = 414
          Width = 704
          Height = 49
          TabOrder = 1
          object BrvBitBtn2: TBrvBitBtn
            Left = 145
            Top = 18
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
            TabOrder = 0
            BrTipoBotao = BrBtnNone
          end
          object BrvBitBtn3: TBrvBitBtn
            Left = 284
            Top = 18
            Width = 132
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
            TabOrder = 1
            BrTipoBotao = BrBtnNone
          end
          object BrvBitBtn4: TBrvBitBtn
            Left = 423
            Top = 18
            Width = 132
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
            TabOrder = 2
            BrTipoBotao = BrBtnNone
          end
          object BrvBitBtn9: TBrvBitBtn
            Left = 560
            Top = 18
            Width = 132
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
            TabOrder = 3
            BrTipoBotao = BrBtnNone
          end
          object BrvBitBtn11: TBrvBitBtn
            Left = 5
            Top = 18
            Width = 133
            Height = 25
            Caption = 'Exportar TXT'
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
        end
        object Panel14: TPanel
          Left = 2
          Top = 464
          Width = 703
          Height = 106
          Align = alCustom
          TabOrder = 2
          object Label41: TLabel
            Left = 35
            Top = 8
            Width = 129
            Height = 13
            Caption = 'Total Ciencia Opera'#231#227'o'
            Font.Charset = DEFAULT_CHARSET
            Font.Color = clWindowText
            Font.Height = -11
            Font.Name = 'Tahoma'
            Font.Style = [fsBold]
            ParentFont = False
          end
          object Label42: TLabel
            Left = 10
            Top = 34
            Width = 154
            Height = 13
            Caption = 'Total Opera'#231#227'o Confirmada'
            Font.Charset = DEFAULT_CHARSET
            Font.Color = clWindowText
            Font.Height = -11
            Font.Name = 'Tahoma'
            Font.Style = [fsBold]
            ParentFont = False
          end
          object Label43: TLabel
            Left = 221
            Top = 8
            Width = 201
            Height = 13
            Caption = 'Total Deconhecimenro da Opera'#231#227'o'
            Font.Charset = DEFAULT_CHARSET
            Font.Color = clWindowText
            Font.Height = -11
            Font.Name = 'Tahoma'
            Font.Style = [fsBold]
            ParentFont = False
          end
          object Label44: TLabel
            Left = 318
            Top = 34
            Width = 111
            Height = 13
            Caption = 'Total Nao Realizada'
            Font.Charset = DEFAULT_CHARSET
            Font.Color = clWindowText
            Font.Height = -11
            Font.Name = 'Tahoma'
            Font.Style = [fsBold]
            ParentFont = False
          end
          object Label45: TLabel
            Left = 570
            Top = 8
            Width = 64
            Height = 13
            Caption = 'Canceladas'
            Font.Charset = DEFAULT_CHARSET
            Font.Color = clWindowText
            Font.Height = -11
            Font.Name = 'Tahoma'
            Font.Style = [fsBold]
            ParentFont = False
          end
          object Label35: TLabel
            Left = 548
            Top = 34
            Width = 86
            Height = 13
            Caption = 'Total Registros'
            Font.Charset = DEFAULT_CHARSET
            Font.Color = clWindowText
            Font.Height = -11
            Font.Name = 'Tahoma'
            Font.Style = [fsBold]
            ParentFont = False
          end
          object Label55: TLabel
            Left = 112
            Top = 59
            Width = 52
            Height = 13
            Caption = 'Total NFe'
            Font.Charset = DEFAULT_CHARSET
            Font.Color = clWindowText
            Font.Height = -11
            Font.Name = 'Tahoma'
            Font.Style = [fsBold]
            ParentFont = False
          end
          object Label56: TLabel
            Left = 376
            Top = 59
            Width = 53
            Height = 13
            Caption = 'Total CTe'
            Font.Charset = DEFAULT_CHARSET
            Font.Color = clWindowText
            Font.Height = -11
            Font.Name = 'Tahoma'
            Font.Style = [fsBold]
            ParentFont = False
          end
          object Label58: TLabel
            Left = 577
            Top = 59
            Width = 59
            Height = 13
            Caption = 'Total NFSe'
            Font.Charset = DEFAULT_CHARSET
            Font.Color = clWindowText
            Font.Height = -11
            Font.Name = 'Tahoma'
            Font.Style = [fsBold]
            ParentFont = False
          end
          object BrvRetCon2: TBrvRetCon
            Left = 170
            Top = 5
            Width = 45
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
          object BrvRetCon3: TBrvRetCon
            Left = 170
            Top = 31
            Width = 45
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
            TabOrder = 1
            Text = '0'
          end
          object BrvRetCon4: TBrvRetCon
            Left = 435
            Top = 5
            Width = 45
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
            TabOrder = 2
            Text = '0'
          end
          object BrvRetCon5: TBrvRetCon
            Left = 435
            Top = 30
            Width = 45
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
            TabOrder = 3
            Text = '0'
          end
          object BrvRetCon1: TBrvRetCon
            Left = 645
            Top = 5
            Width = 45
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
            TabOrder = 4
            Text = '0'
          end
          object EdtQtdeReg: TBrvRetCon
            Left = 645
            Top = 31
            Width = 45
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
            TabOrder = 5
            Text = '0'
          end
          object BrvRetCon6: TBrvRetCon
            Left = 170
            Top = 56
            Width = 45
            Height = 20
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
            TabOrder = 6
            Text = '0'
          end
          object BrvRetCon7: TBrvRetCon
            Left = 437
            Top = 56
            Width = 43
            Height = 20
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
            TabOrder = 7
            Text = '0'
          end
          object BrvRetCon9: TBrvRetCon
            Left = 645
            Top = 56
            Width = 45
            Height = 20
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
            TabOrder = 8
            Text = '0'
          end
        end
      end
    end
    object TabSheet15: TTabSheet
      Caption = 'NFe'
      ImageIndex = 1
      object Panel2: TPanel
        Left = 169
        Top = 0
        Width = 803
        Height = 652
        Align = alClient
        TabOrder = 0
        object Panel3: TPanel
          Left = 1
          Top = 1
          Width = 801
          Height = 650
          Align = alClient
          TabOrder = 0
          object PageControl3: TPageControl
            Left = 1
            Top = 1
            Width = 799
            Height = 648
            ActivePage = TabSheet1
            Align = alClient
            TabOrder = 0
            object TabSheet12: TTabSheet
              Caption = 'Busca Manifesto'
              ImageIndex = 1
              object Panel4: TPanel
                Left = 0
                Top = 0
                Width = 791
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
                Width = 791
                Height = 571
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
              Caption = 'Busca Chave'
              ImageIndex = 2
              object Panel6: TPanel
                Left = 0
                Top = 0
                Width = 791
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
              Caption = 'Retorno'
              ImageIndex = 5
              object PageControl2: TPageControl
                Left = 0
                Top = 0
                Width = 791
                Height = 620
                ActivePage = TabSheet11
                Align = alClient
                TabOrder = 0
                object TabSheet5: TTabSheet
                  Caption = 'Respostas'
                  object MemoResp: TMemo
                    Left = 0
                    Top = 0
                    Width = 783
                    Height = 592
                    Align = alClient
                    ScrollBars = ssVertical
                    TabOrder = 0
                  end
                end
                object TabSheet6: TTabSheet
                  Caption = 'XML Resposta'
                  ImageIndex = 1
                  object WBResposta: TWebBrowser
                    Left = 0
                    Top = 0
                    Width = 783
                    Height = 592
                    Align = alClient
                    TabOrder = 0
                    ExplicitWidth = 702
                    ExplicitHeight = 552
                    ControlData = {
                      4C000000ED5000002F3D00000000000000000000000000000000000000000000
                      000000004C000000000000000000000001000000E0D057007335CF11AE690800
                      2B2E126208000000000000004C0000000114020000000000C000000000000046
                      8000000000000000000000000000000000000000000000000000000000000000
                      00000000000000000100000000000000000000000000000000000000}
                  end
                end
                object TabSheet8: TTabSheet
                  Caption = 'Log'
                  ImageIndex = 2
                  object memoLog: TMemo
                    Left = 0
                    Top = 0
                    Width = 783
                    Height = 592
                    Align = alClient
                    ScrollBars = ssVertical
                    TabOrder = 0
                  end
                end
                object TabSheet9: TTabSheet
                  Caption = 'NFe'
                  ImageIndex = 3
                  object trvwNFe: TTreeView
                    Left = 0
                    Top = 0
                    Width = 783
                    Height = 592
                    Align = alClient
                    Indent = 19
                    TabOrder = 0
                  end
                end
                object TabSheet10: TTabSheet
                  Caption = 'Retorno Completo WS'
                  ImageIndex = 4
                  object memoRespWS: TMemo
                    Left = 0
                    Top = 456
                    Width = 783
                    Height = 136
                    Align = alBottom
                    ScrollBars = ssVertical
                    TabOrder = 0
                  end
                end
                object Dados: TTabSheet
                  Caption = 'Dados'
                  ImageIndex = 5
                  object MemoDados: TMemo
                    Left = 0
                    Top = 0
                    Width = 783
                    Height = 592
                    Align = alClient
                    ScrollBars = ssVertical
                    TabOrder = 0
                  end
                end
                object TabSheet11: TTabSheet
                  Caption = 'RetornoConsulta NFe 2.01'
                  ImageIndex = 6
                  object TreeViewRetornoConsulta: TTreeView
                    Left = 0
                    Top = 0
                    Width = 783
                    Height = 592
                    Align = alClient
                    Indent = 19
                    TabOrder = 0
                  end
                end
              end
            end
          end
        end
      end
      object Panel1: TPanel
        Left = 0
        Top = 0
        Width = 169
        Height = 652
        Align = alLeft
        TabOrder = 1
        object btnStatusServ: TButton
          Left = 9
          Top = 44
          Width = 151
          Height = 25
          Caption = 'Status Servi'#231'o'
          TabOrder = 0
          OnClick = btnStatusServClick
        end
        object btnConsultarChave: TButton
          Left = 9
          Top = 94
          Width = 151
          Height = 25
          Caption = 'Consultar pela Chave'
          TabOrder = 1
          OnClick = btnConsultarChaveClick
        end
        object btnCancelarChave: TButton
          Left = 9
          Top = 119
          Width = 151
          Height = 25
          Caption = 'Cancelamento NFe pela Chave'
          TabOrder = 2
          OnClick = btnCancelarChaveClick
        end
        object btnConsultarRecibo: TButton
          Left = 9
          Top = 144
          Width = 151
          Height = 25
          Caption = 'Consultar Recibo Lote'
          TabOrder = 3
        end
        object btnConsCad: TButton
          Left = 9
          Top = 169
          Width = 151
          Height = 25
          Caption = 'Consulta Cadastro'
          TabOrder = 4
          OnClick = btnConsCadClick
        end
        object btnManifDestConfirmacao: TButton
          Left = 9
          Top = 194
          Width = 151
          Height = 25
          Caption = 'Manif. Dest. - Conf. Opera'#231#227'o'
          TabOrder = 5
        end
        object btnEnviarEmail: TButton
          Left = 9
          Top = 219
          Width = 151
          Height = 25
          Caption = 'Enviar NFe Email'
          TabOrder = 6
        end
        object btnCartadeCorrecao: TButton
          Left = 9
          Top = 19
          Width = 151
          Height = 25
          Caption = 'Carta de Corre'#231#227'o'
          TabOrder = 7
        end
        object btnNfeDestinadas: TButton
          Left = 9
          Top = 244
          Width = 151
          Height = 25
          Caption = 'Consulta NFe Destinadas'
          TabOrder = 8
          OnClick = btnNfeDestinadasClick
        end
        object Button1: TButton
          Left = 9
          Top = 69
          Width = 151
          Height = 25
          Caption = 'Download NFe pela Chave'
          TabOrder = 9
          OnClick = Button1Click
        end
        object Button6: TButton
          Left = 9
          Top = 269
          Width = 151
          Height = 25
          Caption = 'Enviar DPEC'
          TabOrder = 10
        end
        object Button7: TButton
          Left = 9
          Top = 294
          Width = 151
          Height = 25
          Caption = 'Consultar DPEC'
          TabOrder = 11
        end
      end
    end
    object TabSheet2: TTabSheet
      Caption = 'CTe'
      ImageIndex = 2
      object Panel5: TPanel
        Left = 0
        Top = 0
        Width = 169
        Height = 652
        Align = alLeft
        TabOrder = 0
        object Button2: TButton
          Left = 7
          Top = 11
          Width = 151
          Height = 25
          Caption = 'Status Servi'#231'o'
          TabOrder = 0
          OnClick = btnStatusServClick
        end
        object btnConsultar: TButton
          Left = 7
          Top = 37
          Width = 151
          Height = 25
          Caption = 'Consultar carregando XML'
          TabOrder = 1
        end
        object Button3: TButton
          Left = 7
          Top = 63
          Width = 151
          Height = 25
          Caption = 'Consultar pela Chave'
          TabOrder = 2
          OnClick = btnConsultarChaveClick
        end
        object btnCancCTe: TButton
          Left = 7
          Top = 89
          Width = 151
          Height = 25
          Caption = 'Cancelamento CTe com XML'
          TabOrder = 3
        end
        object Button4: TButton
          Left = 7
          Top = 116
          Width = 151
          Height = 25
          Caption = 'Cancelamento CTe pela Chave'
          TabOrder = 4
          OnClick = btnCancelarChaveClick
        end
        object btnGerarPDF: TButton
          Left = 7
          Top = 142
          Width = 151
          Height = 25
          Caption = 'Gerar PDF'
          TabOrder = 5
        end
        object Button5: TButton
          Left = 7
          Top = 168
          Width = 151
          Height = 25
          Caption = 'Imprimir DACTe'
          TabOrder = 6
        end
        object btnEnvDPEC: TButton
          Left = 7
          Top = 194
          Width = 151
          Height = 25
          Caption = 'Enviar DPEC'
          TabOrder = 7
        end
        object btnConsultarDPEC: TButton
          Left = 7
          Top = 220
          Width = 151
          Height = 25
          Caption = 'Consultar DPEC'
          TabOrder = 8
        end
        object btnImportarXML: TButton
          Left = 7
          Top = 246
          Width = 151
          Height = 25
          Caption = 'Importar XML'
          TabOrder = 9
        end
        object btnValidarXML: TButton
          Left = 7
          Top = 272
          Width = 151
          Height = 25
          Caption = 'Validar XML'
          TabOrder = 10
        end
      end
      object PageControl4: TPageControl
        Left = 169
        Top = 0
        Width = 803
        Height = 652
        ActivePage = TabSheet4
        Align = alClient
        TabOrder = 1
        object TabSheet4: TTabSheet
          Caption = 'Busca Manifesto'
          ImageIndex = 1
          object Panel11: TPanel
            Left = 0
            Top = 0
            Width = 795
            Height = 49
            Align = alTop
            TabOrder = 0
            object Label76: TLabel
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
            object Edit35: TEdit
              Left = 127
              Top = 15
              Width = 434
              Height = 21
              TabOrder = 0
            end
          end
          object BrvDbGrid4: TBrvDbGrid
            Left = 0
            Top = 49
            Width = 795
            Height = 575
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
        object TabSheet7: TTabSheet
          Caption = 'Busca Chave'
          ImageIndex = 2
          object Panel12: TPanel
            Left = 0
            Top = 0
            Width = 795
            Height = 65
            Align = alTop
            TabOrder = 0
            object Label77: TLabel
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
            object Edit36: TEdit
              Left = 127
              Top = 23
              Width = 434
              Height = 21
              TabOrder = 0
            end
            object BrvBitBtn5: TBrvBitBtn
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
        object TabSheet16: TTabSheet
          Caption = 'Retorno'
          ImageIndex = 5
          object PageControl5: TPageControl
            Left = 0
            Top = 0
            Width = 795
            Height = 624
            ActivePage = TabSheet24
            Align = alClient
            TabOrder = 0
            object TabSheet17: TTabSheet
              Caption = 'Respostas'
              object Memo2: TMemo
                Left = 0
                Top = 0
                Width = 787
                Height = 596
                Align = alClient
                ScrollBars = ssVertical
                TabOrder = 0
              end
            end
            object TabSheet19: TTabSheet
              Caption = 'XML Resposta'
              ImageIndex = 1
              object WebBrowser1: TWebBrowser
                Left = 0
                Top = 0
                Width = 787
                Height = 596
                Align = alClient
                TabOrder = 0
                ExplicitWidth = 682
                ExplicitHeight = 550
                ControlData = {
                  4C00000057510000993D00000000000000000000000000000000000000000000
                  000000004C000000000000000000000001000000E0D057007335CF11AE690800
                  2B2E126208000000000000004C0000000114020000000000C000000000000046
                  8000000000000000000000000000000000000000000000000000000000000000
                  00000000000000000100000000000000000000000000000000000000}
              end
            end
            object TabSheet20: TTabSheet
              Caption = 'Log'
              ImageIndex = 2
              object Memo3: TMemo
                Left = 0
                Top = 0
                Width = 787
                Height = 596
                Align = alClient
                ScrollBars = ssVertical
                TabOrder = 0
              end
            end
            object TabSheet21: TTabSheet
              Caption = 'NFe'
              ImageIndex = 3
              object TreeView1: TTreeView
                Left = 0
                Top = 0
                Width = 787
                Height = 596
                Align = alClient
                Indent = 19
                TabOrder = 0
              end
            end
            object TabSheet22: TTabSheet
              Caption = 'Retorno Completo WS'
              ImageIndex = 4
              object Memo4: TMemo
                Left = 0
                Top = 460
                Width = 787
                Height = 136
                Align = alBottom
                ScrollBars = ssVertical
                TabOrder = 0
              end
            end
            object TabSheet23: TTabSheet
              Caption = 'Dados'
              ImageIndex = 5
              object Memo5: TMemo
                Left = 0
                Top = 0
                Width = 787
                Height = 596
                Align = alClient
                ScrollBars = ssVertical
                TabOrder = 0
              end
            end
            object TabSheet24: TTabSheet
              Caption = 'RetornoConsulta NFe 2.01'
              ImageIndex = 6
              object TreeView2: TTreeView
                Left = 0
                Top = 0
                Width = 787
                Height = 596
                Align = alClient
                Indent = 19
                TabOrder = 0
              end
            end
          end
        end
      end
    end
    object TabSheet26: TTabSheet
      Caption = 'Configura'#231#245'es'
      ImageIndex = 3
      object PageControl6: TPageControl
        Left = 0
        Top = 0
        Width = 972
        Height = 652
        ActivePage = TabSheet27
        Align = alClient
        TabOrder = 0
        object TabSheet27: TTabSheet
          Caption = 'Configura'#231#245'es'
          object SpeedButton11: TSpeedButton
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
          object SpeedButton12: TSpeedButton
            Left = 324
            Top = 448
            Width = 23
            Height = 22
            Glyph.Data = {
              E6040000424DE604000000000000360000002800000014000000140000000100
              180000000000B0040000C40E0000C40E00000000000000000000008080008080
              0080800080800080800080800080800080800080800080800080800080800080
              8000808000808000808000808000808000808000808000808000808000808000
              8080008080008080008080008080008080008080008080008080008080008080
              0080800080800080800080800080800080800080800080800080800080800080
              8000808000808000808000808000808000808000808000808000808000808000
              8080008080008080008080008080008080008080008080008080008080008080
              0080800080800080800080800080800080800080800080800080800080800080
              8000808000808000808000808000808000000000000000000000000000000000
              0000000000000000000000000000000000008080008080008080008080008080
              008080008080008080008080000000000000007F7F007F7F007F7F007F7F007F
              7F007F7F007F7F007F7F007F7F00000000808000808000808000808000808000
              808000808000808000000000FFFF000000007F7F007F7F007F7F007F7F007F7F
              007F7F007F7F007F7F007F7F0000000080800080800080800080800080800080
              80008080000000FFFFFF00FFFF000000007F7F007F7F007F7F007F7F007F7F00
              7F7F007F7F007F7F007F7F000000008080008080008080008080008080008080
              00000000FFFFFFFFFF00FFFF000000007F7F007F7F007F7F007F7F007F7F007F
              7F007F7F007F7F007F7F000000008080008080008080008080008080000000FF
              FFFF00FFFFFFFFFF00FFFF000000000000000000000000000000000000000000
              00000000000000000000000000808000808000808000808000000000FFFFFFFF
              FF00FFFFFFFFFF00FFFFFFFFFF00FFFFFFFFFF00FFFF00000000808000808000
              8080008080008080008080008080008080008080000000FFFFFF00FFFFFFFFFF
              00FFFFFFFFFF00FFFFFFFFFF00FFFFFFFFFF0000000080800080800080800080
              8000808000808000808000808000808000000000FFFFFFFFFF00FFFF00000000
              0000000000000000000000000000000000008080008080008080008080008080
              0080800080800080800080800080800000000000000000000080800080800080
              8000808000808000808000808000808000000000000000000000808000808000
              8080008080008080008080008080008080008080008080008080008080008080
              0080800080800080800080800080800000000000000080800080800080800080
              8000808000808000808000808000808000808000808000808000808000000000
              8080008080008080000000008080000000008080008080008080008080008080
              0080800080800080800080800080800080800080800080800080800000000000
              0000000000808000808000808000808000808000808000808000808000808000
              8080008080008080008080008080008080008080008080008080008080008080
              0080800080800080800080800080800080800080800080800080800080800080
              8000808000808000808000808000808000808000808000808000808000808000
              8080008080008080008080008080008080008080008080008080008080008080
              0080800080800080800080800080800080800080800080800080800080800080
              80008080008080008080}
          end
          object SpeedButton13: TSpeedButton
            Left = 324
            Top = 489
            Width = 23
            Height = 22
            Glyph.Data = {
              E6040000424DE604000000000000360000002800000014000000140000000100
              180000000000B0040000C40E0000C40E00000000000000000000008080008080
              0080800080800080800080800080800080800080800080800080800080800080
              8000808000808000808000808000808000808000808000808000808000808000
              8080008080008080008080008080008080008080008080008080008080008080
              0080800080800080800080800080800080800080800080800080800080800080
              8000808000808000808000808000808000808000808000808000808000808000
              8080008080008080008080008080008080008080008080008080008080008080
              0080800080800080800080800080800080800080800080800080800080800080
              8000808000808000808000808000808000000000000000000000000000000000
              0000000000000000000000000000000000008080008080008080008080008080
              008080008080008080008080000000000000007F7F007F7F007F7F007F7F007F
              7F007F7F007F7F007F7F007F7F00000000808000808000808000808000808000
              808000808000808000000000FFFF000000007F7F007F7F007F7F007F7F007F7F
              007F7F007F7F007F7F007F7F0000000080800080800080800080800080800080
              80008080000000FFFFFF00FFFF000000007F7F007F7F007F7F007F7F007F7F00
              7F7F007F7F007F7F007F7F000000008080008080008080008080008080008080
              00000000FFFFFFFFFF00FFFF000000007F7F007F7F007F7F007F7F007F7F007F
              7F007F7F007F7F007F7F000000008080008080008080008080008080000000FF
              FFFF00FFFFFFFFFF00FFFF000000000000000000000000000000000000000000
              00000000000000000000000000808000808000808000808000000000FFFFFFFF
              FF00FFFFFFFFFF00FFFFFFFFFF00FFFFFFFFFF00FFFF00000000808000808000
              8080008080008080008080008080008080008080000000FFFFFF00FFFFFFFFFF
              00FFFFFFFFFF00FFFFFFFFFF00FFFFFFFFFF0000000080800080800080800080
              8000808000808000808000808000808000000000FFFFFFFFFF00FFFF00000000
              0000000000000000000000000000000000008080008080008080008080008080
              0080800080800080800080800080800000000000000000000080800080800080
              8000808000808000808000808000808000000000000000000000808000808000
              8080008080008080008080008080008080008080008080008080008080008080
              0080800080800080800080800080800000000000000080800080800080800080
              8000808000808000808000808000808000808000808000808000808000000000
              8080008080008080000000008080000000008080008080008080008080008080
              0080800080800080800080800080800080800080800080800080800000000000
              0000000000808000808000808000808000808000808000808000808000808000
              8080008080008080008080008080008080008080008080008080008080008080
              0080800080800080800080800080800080800080800080800080800080800080
              8000808000808000808000808000808000808000808000808000808000808000
              8080008080008080008080008080008080008080008080008080008080008080
              0080800080800080800080800080800080800080800080800080800080800080
              80008080008080008080}
          end
          object Label78: TLabel
            Left = 3
            Top = 473
            Width = 120
            Height = 13
            Caption = 'Diretorio para Exporta'#231#227'o'
          end
          object Label79: TLabel
            Left = 3
            Top = 432
            Width = 119
            Height = 13
            Caption = 'Diretorio para Importa'#231#227'o'
          end
          object Label80: TLabel
            Left = 390
            Top = 474
            Width = 57
            Height = 13
            Caption = 'Logo Marca'
          end
          object GroupBox18: TGroupBox
            Left = 3
            Top = 3
            Width = 206
            Height = 144
            Caption = 'Certificado'
            TabOrder = 0
            object Label81: TLabel
              Left = 8
              Top = 16
              Width = 41
              Height = 13
              Caption = 'Caminho'
            end
            object Label82: TLabel
              Left = 8
              Top = 93
              Width = 31
              Height = 13
              Caption = 'Senha'
            end
            object SpeedButton14: TSpeedButton
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
            object Label83: TLabel
              Left = 8
              Top = 53
              Width = 79
              Height = 13
              Caption = 'N'#250'mero de S'#233'rie'
            end
            object SpeedButton15: TSpeedButton
              Left = 175
              Top = 67
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
            end
            object edtCaminho: TEdit
              Left = 8
              Top = 30
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
          object GroupBox19: TGroupBox
            Left = 215
            Top = 304
            Width = 471
            Height = 127
            Caption = 'Geral'
            TabOrder = 1
            object Label84: TLabel
              Left = 3
              Top = 78
              Width = 57
              Height = 13
              Caption = 'Logo Marca'
            end
            object SpeedButton16: TSpeedButton
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
            object SpeedButton17: TSpeedButton
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
          end
          object GroupBox20: TGroupBox
            Left = 3
            Top = 153
            Width = 206
            Height = 141
            Caption = 'WebService'
            TabOrder = 2
            object Label85: TLabel
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
          object GroupBox21: TGroupBox
            Left = 3
            Top = 291
            Width = 206
            Height = 135
            Caption = 'Proxy'
            TabOrder = 3
            object Label86: TLabel
              Left = 8
              Top = 16
              Width = 22
              Height = 13
              Caption = 'Host'
            end
            object Label87: TLabel
              Left = 136
              Top = 13
              Width = 25
              Height = 13
              Caption = 'Porta'
            end
            object Label88: TLabel
              Left = 8
              Top = 56
              Width = 36
              Height = 13
              Caption = 'Usu'#225'rio'
            end
            object Label89: TLabel
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
          object GroupBox22: TGroupBox
            Left = 415
            Top = 0
            Width = 273
            Height = 309
            Caption = 'Dados Emissor'
            Color = clBtnFace
            ParentBackground = False
            ParentColor = False
            TabOrder = 4
            object Label90: TLabel
              Left = 7
              Top = 14
              Width = 27
              Height = 13
              Caption = 'CNPJ'
            end
            object Label91: TLabel
              Left = 135
              Top = 14
              Width = 41
              Height = 13
              Caption = 'Insc.Est.'
            end
            object Label92: TLabel
              Left = 7
              Top = 50
              Width = 63
              Height = 13
              Caption = 'Raz'#227'o Social'
            end
            object Label93: TLabel
              Left = 7
              Top = 84
              Width = 40
              Height = 13
              Caption = 'Fantasia'
            end
            object Label94: TLabel
              Left = 7
              Top = 118
              Width = 24
              Height = 13
              Caption = 'Fone'
            end
            object Label95: TLabel
              Left = 135
              Top = 118
              Width = 21
              Height = 13
              Caption = 'CEP'
            end
            object Label96: TLabel
              Left = 8
              Top = 152
              Width = 54
              Height = 13
              Caption = 'Logradouro'
            end
            object Label97: TLabel
              Left = 207
              Top = 152
              Width = 37
              Height = 13
              Caption = 'N'#250'mero'
            end
            object Label98: TLabel
              Left = 8
              Top = 188
              Width = 64
              Height = 13
              Caption = 'Complemento'
            end
            object Label99: TLabel
              Left = 136
              Top = 188
              Width = 27
              Height = 13
              Caption = 'Bairro'
            end
            object Label100: TLabel
              Left = 8
              Top = 264
              Width = 61
              Height = 13
              Caption = 'C'#243'd. Cidade '
            end
            object Label101: TLabel
              Left = 8
              Top = 226
              Width = 33
              Height = 13
              Caption = 'Cidade'
            end
            object Label102: TLabel
              Left = 82
              Top = 264
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
              Left = 8
              Top = 280
              Width = 61
              Height = 21
              TabOrder = 10
            end
            object edtEmitUF: TEdit
              Left = 81
              Top = 280
              Width = 36
              Height = 21
              TabOrder = 11
            end
            object edtEmitCidade: TComboBox
              Left = 7
              Top = 241
              Width = 257
              Height = 21
              TabOrder = 12
              Text = 'Selecione uma Cidade'
              Items.Strings = (
                'Americana/3501608/SP'
                'Ananindeua/1500800/PA'
                'Anapolis/5201108/GO'
                'Aparecida de Goiania/5201405/GO'
                'Araraquara/3503208/SP'
                'Araras/3503307/SP'
                'Arcos/3104205/MG'
                'Bage/4301602/RS'
                'Barbacena/3105608/MG'
                'Barroso/3105905/MG'
                'Barreiras/2903201/BA'
                'Belford Roxo/3300456/RJ'
                'Belo Horizonte/3106200/MG'
                'Bento Gon'#231'alves/4302105/RS'
                'Bertioga/3506359/SP'
                'Betim/3106705/MG'
                'Boa Vista/1400100/RR'
                'Brusque/4202909/SC'
                'Campos dos Goytacazes/3301009/RJ'
                'Canoas/4304606/RS'
                'Capao Bonito/3510203/SP'
                'Caruaru/2604106/PE'
                'Cataguases/3115300/MG'
                'Catanduva/3511102/SP'
                'Cedral/3511300/SP'
                'Chapeco/4204202/SC'
                'Colina/3512001/SP'
                'Contagem/3118601/MG'
                'Cotia/3513009/SP'
                'Cornelio Procopio/4106407/PR'
                'Criciuma/4204608/SC'
                'Cuiaba/5103403/MT'
                'Curitiba/4106902/PR'
                'Diadema/3513801/SP'
                'Duque de Caxias/3301702/RJ'
                'Eunapolis/2910727/BA'
                'Fazenda Rio Grande/4107652/PR'
                'Feira de Santana/2910800/BA'
                'Formiga/3126109/MG'
                'Fortaleza/2304400/CE'
                'Franca/3516200/SP'
                'Francisco Beltrao/4108403/PR'
                'Frederico Westphalen/4308508/RS'
                'Frutal/3127107/MG'
                'Gaspar/4205902/SC'
                'Goiania/5208707/GO'
                'Guaiba/4309308/RS'
                'Guapore/4309407/RS'
                'Guarapari/3202405/ES'
                'Guaratingueta/3518404/SP'
                'Guarulhos/3518800/SP'
                'Hortolandia/3519071/SP'
                'Ijui/4310207/RS'
                'Indaial/4207502/SC'
                'Ipatinga/3131307/MG'
                'Itajai/4208203/SC'
                'Itapema/4208302/SC'
                'Itatinga/3523503/SP'
                'Itu/3523909/SP'
                'Ituporanga/4208500/SC'
                'Jaguariuna/3524709/SP'
                'Jau/3525300/SP'
                'Joacaba/4209003/SC'
                'Joao Pessoa/2507507/PB'
                'Juiz de Fora/3136702/MG'
                'Jundiai/3525904/SP'
                'Lages/4209300/SC'
                'Lagoa Santa/3137601/MG'
                'Maceio/2704302/AL'
                'Manaus/1302603/AM'
                'Marechal Deodoro/3704708/AL'
                'Marica/3302700/RJ'
                'Maringa/4115200/PR'
                'Maua/3529401/SP'
                'Mirassol/3530300/SP'
                'Mococa/3530508/SP'
                'Mogi das Cruzes/3530607/SP'
                'Montes Claros/3143302/MG'
                'Muriae/3143906/MG'
                'Natal/2408102/RN'
                'Novo Hamburgo/4313409/RS'
                'Niteroi/3303302/RJ'
                'Olimpia/3533908/SP'
                'Palhoca/4211900/SC'
                'Para de Minas/3147105/MG'
                'Paranagua/4118204/PR'
                'Paranavai/4118402/PR'
                'Parauapebas/15055306/PA'
                'Patrocinio/3148103/MG'
                'Paulinia/3536505/SP'
                'Pelotas/4314407/RS'
                'Pindamonhangaba/3538006/SP'
                'Pinhalzinho/4212908/SC'
                'Ponta Grossa/4119905/PR'
                'Porto Seguro/2925303/BA'
                'Presidente Prudente/3541406/SP'
                'Presidente Venceslau/3541505/SP'
                'Recife/2611606/PE'
                'Registro/3542602/SP'
                'Ribeirao das Neves/3154606/MG'
                'Ribeirao Preto/3543402/SP'
                'Rio Claro/3543907/SP'
                'Rio de Janeiro/3304557/RJ'
                'Rio do Sul/4214805/SC'
                'Rondonopolis/5107602/MT'
                'Salto/3545209/SP'
                'Santa Luzia/3157807/MG'
                'Santo Andre/3547809/SP'
                'Santos/3548500/SP'
                'Sao Bento do Sul/4215802/SC'
                'Sao Bernardo do Campos/3548708/SP'
                'Sao Borja/4318002/RS'
                'Sao Caetano do Sul/3548807/SP'
                'Sao Carlos/3548906/SP'
                'Sao Joao da Boa Vista/3549102/SP'
                'Sao Jose/4216602/SC'
                'Sao Jose do Rio Pardo/3549706/SP'
                'Sao Jose do Rio Preto/3549805/SP'
                'Sao Jose dos Campos/3549904/SP'
                'Sao Jose dos Pinhais/4125506/PR'
                'Sao Leopoldo/4318705/RS'
                'Sao Lourenco do Oeste/4216909/SC'
                'Sao Miguel do Oeste/4217204/SC'
                'Saquarema/3305505/RJ'
                'Schroeder/4217402/SC'
                'Sinop/5107909/MT'
                'Tangara da Serra/5107958/MT'
                'Tatui/3554003/SP'
                'Telemaco Borba/4127106/PR'
                'Tijucas/4218004/SC'
                'Uba/3169901/MG'
                'Uberaba/3170107/MG'
                'Umuarama/4128104/PR'
                'Uniao da Vitoria/4128203/PR'
                'Urussanga/ 4219002/SC'
                'Vargem Grande do Sul/3556404/SP'
                'Varginha/3170701/MG'
                'Varzea Grande/5108402/MT'
                'Votuporanga/3557105/SP')
            end
          end
          object GroupBox23: TGroupBox
            Left = 215
            Top = 0
            Width = 194
            Height = 309
            Caption = 'Email'
            TabOrder = 5
            object Label103: TLabel
              Left = 8
              Top = 16
              Width = 72
              Height = 13
              Caption = 'Servidor SMTP'
            end
            object Label104: TLabel
              Left = 134
              Top = 16
              Width = 25
              Height = 13
              Caption = 'Porta'
            end
            object Label105: TLabel
              Left = 8
              Top = 56
              Width = 36
              Height = 13
              Caption = 'Usu'#225'rio'
            end
            object Label106: TLabel
              Left = 93
              Top = 56
              Width = 31
              Height = 13
              Caption = 'Senha'
            end
            object Label107: TLabel
              Left = 8
              Top = 96
              Width = 121
              Height = 13
              Caption = 'Assunto do email enviado'
            end
            object Label108: TLabel
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
            object Memo6: TMemo
              Left = 8
              Top = 184
              Width = 177
              Height = 70
              TabOrder = 6
            end
          end
          object RadioGroup2: TRadioGroup
            Left = 479
            Top = 431
            Width = 207
            Height = 50
            Caption = 'Importa'#231#227'o'
            ItemIndex = 0
            Items.Strings = (
              'Importar e trasmitir'
              'So importar')
            TabOrder = 6
          end
          object EdtDsArquiv: TEdit
            Left = 3
            Top = 448
            Width = 315
            Height = 21
            TabOrder = 7
          end
          object Edit3: TEdit
            Left = 3
            Top = 488
            Width = 315
            Height = 21
            TabOrder = 8
          end
          object BitBtn2: TBitBtn
            Left = 479
            Top = 487
            Width = 207
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
            TabOrder = 9
            OnClick = btnSalvarConfigClick
          end
          object RadioGroup12: TRadioGroup
            Left = 353
            Top = 437
            Width = 120
            Height = 74
            Caption = 'Intervalo Importa'#231#227'o'
            ParentShowHint = False
            ShowHint = False
            TabOrder = 11
            WordWrap = True
          end
          object Edit4: TEdit
            Left = 364
            Top = 456
            Width = 96
            Height = 21
            TabOrder = 10
          end
          object gbGeral: TGroupBox
            Left = 692
            Top = 3
            Width = 265
            Height = 314
            Caption = 'Conf NFSe'
            TabOrder = 12
            object Label59: TLabel
              Left = 8
              Top = 56
              Width = 120
              Height = 13
              Caption = 'Logo Marca da Prefeitura'
            end
            object sbtnLogoMarca: TSpeedButton
              Left = 235
              Top = 68
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
              Left = 235
              Top = 148
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
            object lblSchemas: TLabel
              Left = 120
              Top = 16
              Width = 65
              Height = 13
              Caption = 'lblSchemas'
              Font.Charset = DEFAULT_CHARSET
              Font.Color = clRed
              Font.Height = -11
              Font.Name = 'MS Sans Serif'
              Font.Style = [fsBold]
              ParentFont = False
            end
            object sbtSchemas: TSpeedButton
              Left = 234
              Top = 28
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
            end
            object Label60: TLabel
              Left = 8
              Top = 96
              Width = 179
              Height = 13
              Caption = 'Logo Marca do Prestador de Servi'#231'os'
            end
            object sbtnPrestLogo: TSpeedButton
              Left = 234
              Top = 108
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
            end
            object Label61: TLabel
              Left = 8
              Top = 176
              Width = 45
              Height = 13
              Caption = 'Prefeitura'
            end
            object Label62: TLabel
              Left = 8
              Top = 16
              Width = 108
              Height = 13
              Caption = 'Schemas do Provedor:'
            end
            object Label63: TLabel
              Left = 8
              Top = 218
              Width = 31
              Height = 13
              Caption = 'Senha'
            end
            object Label64: TLabel
              Left = 8
              Top = 258
              Width = 36
              Height = 13
              Caption = 'Usu'#225'rio'
            end
            object Edit5: TEdit
              Left = 8
              Top = 72
              Width = 228
              Height = 21
              TabOrder = 1
            end
            object Edit7: TEdit
              Left = 8
              Top = 152
              Width = 228
              Height = 21
              TabOrder = 3
            end
            object CheckBox12: TCheckBox
              Left = 8
              Top = 136
              Width = 209
              Height = 15
              Caption = 'Salvar Arquivos de Envio e Resposta'
              Font.Charset = DEFAULT_CHARSET
              Font.Color = clWindowText
              Font.Height = -11
              Font.Name = 'MS Sans Serif'
              Font.Style = []
              ParentFont = False
              TabOrder = 4
            end
            object edtSchemas: TEdit
              Left = 8
              Top = 32
              Width = 228
              Height = 21
              TabOrder = 0
            end
            object edtPrestLogo: TEdit
              Left = 8
              Top = 112
              Width = 228
              Height = 21
              TabOrder = 2
            end
            object edtPrefeitura: TEdit
              Left = 8
              Top = 192
              Width = 249
              Height = 21
              TabOrder = 5
            end
            object edtSenhaWeb: TEdit
              Left = 8
              Top = 234
              Width = 249
              Height = 21
              PasswordChar = '*'
              TabOrder = 6
            end
            object edtUserWeb: TEdit
              Left = 8
              Top = 272
              Width = 249
              Height = 21
              TabOrder = 7
            end
          end
          object GroupBox45: TGroupBox
            Left = 692
            Top = 323
            Width = 269
            Height = 286
            Caption = 'Dados Boleto'
            Color = clBtnFace
            ParentBackground = False
            ParentColor = False
            TabOrder = 13
            object Label65: TLabel
              Left = 8
              Top = 60
              Width = 77
              Height = 13
              Caption = 'N'#176' de Registros:'
            end
            object Label66: TLabel
              Left = 127
              Top = 60
              Width = 35
              Height = 13
              Caption = 'Layout:'
            end
            object Label67: TLabel
              Left = 8
              Top = 79
              Width = 42
              Height = 13
              Caption = 'Ag'#234'ncia:'
            end
            object Label68: TLabel
              Left = 182
              Top = 78
              Width = 79
              Height = 13
              Caption = 'C'#243'digo Cedente:'
            end
            object Label69: TLabel
              Left = 8
              Top = 120
              Width = 70
              Height = 13
              Caption = 'Nosso N'#250'mero'
            end
            object Label71: TLabel
              Left = 194
              Top = 120
              Width = 46
              Height = 13
              Caption = 'Nr. Docto'
            end
            object Label72: TLabel
              Left = 91
              Top = 79
              Width = 36
              Height = 13
              Caption = 'Carteira'
            end
            object Label73: TLabel
              Left = 102
              Top = 120
              Width = 45
              Height = 13
              Caption = 'Conv'#234'nio'
            end
            object Label75: TLabel
              Left = 7
              Top = 159
              Width = 49
              Height = 13
              Caption = 'Instru'#231#245'es'
            end
            object ComboBox2: TComboBox
              Left = 7
              Top = 30
              Width = 255
              Height = 21
              Style = csDropDownList
              Font.Charset = DEFAULT_CHARSET
              Font.Color = clWindowText
              Font.Height = -11
              Font.Name = 'MS Sans Serif'
              Font.Style = [fsBold]
              ItemIndex = 5
              ParentFont = False
              TabOrder = 0
              Text = '341 - Banco Itau'
              Items.Strings = (
                '001 - Banco do Brasil'
                '033 - Santander Banespa'
                '104 - Caixa Economica'
                '237 - Banco Bradesco'
                '275 - Banco Real'
                '341 - Banco Itau'
                '399 - Banco HSBC'
                '409 - Banco Unicanco'
                '748 - Sicred'
                '756 - Bancoob')
            end
            object CheckBox14: TCheckBox
              Left = 36
              Top = 11
              Width = 45
              Height = 17
              Alignment = taLeftJustify
              Caption = 'Tela:'
              Checked = True
              State = cbChecked
              TabOrder = 1
            end
            object MaskEdit2: TMaskEdit
              Left = 86
              Top = 57
              Width = 32
              Height = 21
              EditMask = '9999'
              MaxLength = 4
              TabOrder = 2
              Text = '1   '
            end
            object ComboBox3: TComboBox
              Left = 169
              Top = 57
              Width = 93
              Height = 21
              Style = csDropDownList
              ItemIndex = 0
              TabOrder = 3
              Text = 'Boleto Padr'#227'o'
              Items.Strings = (
                'Boleto Padr'#227'o'
                'Carn'#234)
            end
            object Edit8: TEdit
              Left = 8
              Top = 93
              Width = 72
              Height = 21
              TabOrder = 4
            end
            object Edit9: TEdit
              Left = 58
              Top = 93
              Width = 27
              Height = 21
              TabOrder = 5
            end
            object Edit10: TEdit
              Left = 189
              Top = 93
              Width = 72
              Height = 21
              TabOrder = 6
            end
            object Edit11: TEdit
              Left = 235
              Top = 93
              Width = 26
              Height = 21
              TabOrder = 7
            end
            object Edit12: TEdit
              Left = 8
              Top = 135
              Width = 88
              Height = 21
              TabOrder = 8
              Text = '522'
            end
            object Edit14: TEdit
              Left = 194
              Top = 135
              Width = 67
              Height = 21
              TabOrder = 9
            end
            object Edit15: TEdit
              Left = 91
              Top = 94
              Width = 85
              Height = 21
              TabOrder = 10
            end
            object Edit16: TEdit
              Left = 102
              Top = 135
              Width = 72
              Height = 21
              TabOrder = 11
            end
            object Memo10: TMemo
              Left = 8
              Top = 176
              Width = 254
              Height = 97
              Lines.Strings = (
                'N'#227'o receber ap'#243's vencimento')
              TabOrder = 12
            end
            object CheckBox36: TCheckBox
              Left = 117
              Top = 11
              Width = 132
              Height = 17
              Alignment = taLeftJustify
              Caption = 'Gerar Arquivo Remessa:'
              Checked = True
              State = cbChecked
              TabOrder = 13
            end
          end
        end
      end
    end
    object TabSheet39: TTabSheet
      Caption = 'Enviar Email'
      ImageIndex = 5
      object Panel16: TPanel
        Left = 0
        Top = 0
        Width = 972
        Height = 105
        Align = alTop
        TabOrder = 0
        object GroupBox13: TGroupBox
          Left = 10
          Top = 0
          Width = 87
          Height = 102
          Caption = 'Tipo NFe'
          TabOrder = 0
          object CheckBox16: TCheckBox
            Left = 6
            Top = 17
            Width = 67
            Height = 17
            Caption = 'Entrada'
            Checked = True
            State = cbChecked
            TabOrder = 0
          end
          object CheckBox17: TCheckBox
            Left = 6
            Top = 35
            Width = 67
            Height = 17
            Caption = 'Saida'
            Checked = True
            State = cbChecked
            TabOrder = 1
          end
        end
        object GroupBox14: TGroupBox
          Left = 103
          Top = 0
          Width = 171
          Height = 104
          Caption = 'Status Manifesto'
          TabOrder = 1
          object CheckBox18: TCheckBox
            Left = 7
            Top = 16
            Width = 146
            Height = 17
            Caption = 'Ci'#234'ncia da Opera'#231#227'o'
            Checked = True
            State = cbChecked
            TabOrder = 0
          end
          object CheckBox19: TCheckBox
            Left = 7
            Top = 39
            Width = 146
            Height = 17
            Caption = 'Confirma'#231#227'o da Opera'#231#227'o'
            TabOrder = 1
          end
          object CheckBox20: TCheckBox
            Left = 7
            Top = 62
            Width = 97
            Height = 17
            Caption = 'Desconhecido'
            TabOrder = 2
          end
          object CheckBox21: TCheckBox
            Left = 7
            Top = 85
            Width = 97
            Height = 17
            Caption = 'N'#227'o Realizado'
            TabOrder = 3
          end
        end
        object GroupBox15: TGroupBox
          Left = 278
          Top = 0
          Width = 171
          Height = 102
          Caption = 'Emiss'#227'o'
          TabOrder = 2
          object Label47: TLabel
            Left = 7
            Top = 11
            Width = 53
            Height = 13
            Caption = 'Data Inicial'
          end
          object Label49: TLabel
            Left = 7
            Top = 46
            Width = 48
            Height = 13
            Caption = 'Data Final'
          end
          object BrvEditDate3: TBrvEditDate
            Left = 7
            Top = 26
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
          object BrvEditDate4: TBrvEditDate
            Left = 7
            Top = 59
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
        object GroupBox16: TGroupBox
          Left = 455
          Top = 1
          Width = 242
          Height = 101
          Caption = 'Chave Acesso'
          TabOrder = 3
          object BrvEditNum4: TBrvEditNum
            Left = 3
            Top = 18
            Width = 230
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
        object GroupBox17: TGroupBox
          Left = 703
          Top = 1
          Width = 180
          Height = 101
          Caption = 'Dados Cliente/fornecedor/Tomador'
          TabOrder = 4
          object Label50: TLabel
            Left = 7
            Top = 32
            Width = 52
            Height = 13
            Caption = 'CNPJ/CPF'
          end
          object BrvEditNum5: TBrvEditNum
            Left = 7
            Top = 51
            Width = 154
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
      end
      object DbgNotas: TBrvDbGrid
        Left = 0
        Top = 105
        Width = 972
        Height = 268
        Margins.Left = 0
        Margins.Top = 0
        Margins.Right = 0
        Margins.Bottom = 0
        BrShowMemo = True
        BrReadOnlyStyle = [fsItalic]
        BrReadOnlyColor = clMaroon
        Align = alClient
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
            Title.Alignment = taCenter
            Title.Caption = 'R'
            Title.Font.Charset = SYMBOL_CHARSET
            Title.Font.Color = clWindowText
            Title.Font.Height = -11
            Title.Font.Name = 'Wingdings 2'
            Title.Font.Style = []
            Width = 20
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
            FieldName = 'DtEmiDoc'
            Title.Caption = 'Data Emiss'#227'o'
            Width = 90
            Visible = True
            BrConsulta = 0
            BrPermissao = []
            BrValueHalfChecked = False
            BrSaveOnClick = False
          end
          item
            Expanded = False
            FieldName = 'CdEmpres'
            Title.Caption = 'Empresa'
            Width = 70
            Visible = True
            BrConsulta = 0
            BrPermissao = []
            BrValueHalfChecked = False
            BrSaveOnClick = False
          end
          item
            Expanded = False
            FieldName = 'RsEmpres'
            Title.Caption = 'Nome da Empresa'
            Width = 250
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
            Width = 75
            Visible = True
            BrConsulta = 0
            BrPermissao = []
            BrValueHalfChecked = False
            BrSaveOnClick = False
          end
          item
            Expanded = False
            FieldName = 'NrSeriNF'
            Title.Caption = 'S'#233'rie'
            Visible = True
            BrConsulta = 0
            BrPermissao = []
            BrValueHalfChecked = False
            BrSaveOnClick = False
          end
          item
            Expanded = False
            FieldName = 'NrDocume'
            Title.Caption = 'N'#186' Documento'
            Width = 95
            Visible = True
            BrConsulta = 0
            BrPermissao = []
            BrValueHalfChecked = False
            BrSaveOnClick = False
          end
          item
            Expanded = False
            FieldName = 'CdTitula'
            Title.Caption = 'Tomador'
            Width = 70
            Visible = True
            BrConsulta = 0
            BrPermissao = []
            BrValueHalfChecked = False
            BrSaveOnClick = False
          end
          item
            Expanded = False
            FieldName = 'RsTitula'
            Title.Caption = 'Nome do Tomador'
            Width = 250
            Visible = True
            BrConsulta = 0
            BrPermissao = []
            BrValueHalfChecked = False
            BrSaveOnClick = False
          end>
      end
      object DbgEmails: TBrvDbGrid
        Left = 0
        Top = 454
        Width = 972
        Height = 90
        BrShowMemo = True
        BrReadOnlyStyle = []
        BrReadOnlyColor = clBlack
        Align = alBottom
        TabOrder = 5
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
            Expanded = False
            FieldName = 'DsEmail'
            Font.Charset = DEFAULT_CHARSET
            Font.Color = clBlack
            Font.Height = -11
            Font.Name = 'MS Sans Serif'
            Font.Style = [fsBold]
            Title.Caption = 'E-mail do Destinat'#225'rio'
            Title.Font.Charset = DEFAULT_CHARSET
            Title.Font.Color = clBlue
            Title.Font.Height = -11
            Title.Font.Name = 'MS Sans Serif'
            Title.Font.Style = [fsBold]
            Width = 720
            Visible = True
            BrConsulta = 0
            BrPermissao = []
            BrValueHalfChecked = False
            BrSaveOnClick = False
          end>
      end
      object mmEmailMsg: TMemo
        Left = 0
        Top = 544
        Width = 972
        Height = 67
        Align = alBottom
        TabOrder = 2
      end
      object Panel18: TPanel
        Left = 0
        Top = 611
        Width = 972
        Height = 41
        Align = alBottom
        BorderStyle = bsSingle
        Font.Charset = DEFAULT_CHARSET
        Font.Color = clWindowText
        Font.Height = -11
        Font.Name = 'MS Sans Serif'
        Font.Style = [fsBold]
        ParentFont = False
        TabOrder = 3
        DesignSize = (
          968
          37)
        object LblQtReg: TLabel
          Left = 8
          Top = 8
          Width = 54
          Height = 13
          Caption = 'LblQtReg'
        end
        object BtnEnviar: TBrvBitBtn
          Left = 802
          Top = 4
          Width = 100
          Height = 25
          Hint = 'Enviar'
          Anchors = [akTop, akRight]
          Caption = '&Enviar'
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
          TabOrder = 0
          BrTipoBotao = BrBtnOk
        end
      end
      object Panel19: TPanel
        Left = 0
        Top = 373
        Width = 972
        Height = 81
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
          968
          77)
        object Label48: TLabel
          Left = 145
          Top = 10
          Width = 125
          Height = 13
          Caption = 'E-mail do Destinat'#225'rio'
          Color = clBtnFace
          Font.Charset = DEFAULT_CHARSET
          Font.Color = clBlack
          Font.Height = -11
          Font.Name = 'MS Sans Serif'
          Font.Style = [fsBold]
          ParentColor = False
          ParentFont = False
        end
        object BrvBitBtn7: TBrvBitBtn
          Left = 581
          Top = 4
          Width = 100
          Height = 25
          Hint = 'Adicionar'
          Anchors = [akTop, akRight]
          Caption = '&Adicionar'
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
          TabOrder = 2
          BrTipoBotao = BrBtnSetaAbaixo
        end
        object BrvEdit1: TBrvEdit
          Left = 276
          Top = 6
          Width = 299
          Height = 21
          Anchors = [akLeft, akTop, akRight]
          TabOrder = 1
          BrvReadOnlyColor = 14541539
          BrVisibleButton = False
          BrFunctionButton = VeConsulta
          BrAlignment = taLeftJustify
          BrvQueryCode = 0
          BrRecordar = False
        end
        object BrvBitBtn8: TBrvBitBtn
          Left = 685
          Top = 4
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
          TabOrder = 3
          BrTipoBotao = BrBtnExcluir
        end
        object BrvBitBtn10: TBrvBitBtn
          Left = 8
          Top = 4
          Width = 120
          Height = 25
          Hint = 'Listar E-mails'
          Caption = '&Listar E-mails'
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
          TabOrder = 0
          BrTipoBotao = BrBtnSetaAbaixo
        end
        object GroupBox31: TGroupBox
          Left = 789
          Top = -1
          Width = 172
          Height = 74
          Caption = 'Anexo'
          TabOrder = 4
          object CheckBox34: TCheckBox
            Left = 6
            Top = 17
            Width = 67
            Height = 13
            Caption = 'XML'
            Checked = True
            State = cbChecked
            TabOrder = 0
          end
          object CheckBox35: TCheckBox
            Left = 62
            Top = 13
            Width = 46
            Height = 17
            Caption = 'PDF'
            Checked = True
            State = cbChecked
            TabOrder = 1
          end
          object CheckBox13: TCheckBox
            Left = 6
            Top = 32
            Width = 46
            Height = 17
            Caption = 'Gerar Boleto'
            Checked = True
            State = cbChecked
            TabOrder = 2
          end
          object ComboBox1: TComboBox
            Left = 6
            Top = 48
            Width = 163
            Height = 21
            Style = csDropDownList
            Font.Charset = DEFAULT_CHARSET
            Font.Color = clWindowText
            Font.Height = -11
            Font.Name = 'MS Sans Serif'
            Font.Style = [fsBold]
            ItemIndex = 5
            ParentFont = False
            TabOrder = 3
            Text = '341 - Banco Itau'
            Items.Strings = (
              '001 - Banco do Brasil'
              '033 - Santander Banespa'
              '104 - Caixa Economica'
              '237 - Banco Bradesco'
              '275 - Banco Real'
              '341 - Banco Itau'
              '399 - Banco HSBC'
              '409 - Banco Unicanco'
              '748 - Sicred'
              '756 - Bancoob')
          end
        end
      end
    end
    object TabSheet40: TTabSheet
      Caption = 'Movimenta'#231#245'es'
      ImageIndex = 6
      object Panel17: TPanel
        Left = 0
        Top = 0
        Width = 185
        Height = 652
        Align = alLeft
        TabOrder = 0
        object GroupBox24: TGroupBox
          Left = 8
          Top = 6
          Width = 171
          Height = 107
          Caption = 'Status Manifesto'
          TabOrder = 0
          object CheckBox22: TCheckBox
            Left = 7
            Top = 16
            Width = 146
            Height = 17
            Caption = 'Ci'#234'ncia da Opera'#231#227'o'
            Checked = True
            State = cbChecked
            TabOrder = 0
          end
          object CheckBox23: TCheckBox
            Left = 7
            Top = 39
            Width = 146
            Height = 17
            Caption = 'Confirma'#231#227'o da Opera'#231#227'o'
            TabOrder = 1
          end
          object CheckBox24: TCheckBox
            Left = 7
            Top = 62
            Width = 97
            Height = 17
            Caption = 'Desconhecido'
            TabOrder = 2
          end
          object CheckBox25: TCheckBox
            Left = 7
            Top = 85
            Width = 97
            Height = 17
            Caption = 'N'#227'o Realizado'
            TabOrder = 3
          end
        end
        object GroupBox25: TGroupBox
          Left = 9
          Top = 114
          Width = 171
          Height = 87
          Caption = 'Data Emiss'#227'o'
          TabOrder = 1
          object Label51: TLabel
            Left = 7
            Top = 11
            Width = 53
            Height = 13
            Caption = 'Data Inicial'
          end
          object Label52: TLabel
            Left = 7
            Top = 46
            Width = 48
            Height = 13
            Caption = 'Data Final'
          end
          object BrvEditDate5: TBrvEditDate
            Left = 7
            Top = 26
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
          object BrvEditDate6: TBrvEditDate
            Left = 7
            Top = 59
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
        object GroupBox26: TGroupBox
          Left = 9
          Top = 200
          Width = 171
          Height = 46
          Caption = 'Chave Acesso'
          TabOrder = 2
          object BrvEditNum6: TBrvEditNum
            Left = 3
            Top = 18
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
        object GroupBox27: TGroupBox
          Left = 9
          Top = 303
          Width = 171
          Height = 99
          Caption = 'Dados'
          TabOrder = 3
          object Label53: TLabel
            Left = 3
            Top = 12
            Width = 78
            Height = 13
            Caption = 'Numero da Nota'
          end
          object Label54: TLabel
            Left = 3
            Top = 50
            Width = 65
            Height = 13
            Caption = 'Serie da Nota'
          end
          object BrvEditNum7: TBrvEditNum
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
          object BrvEditNum8: TBrvEditNum
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
        object GroupBox28: TGroupBox
          Left = 9
          Top = 392
          Width = 171
          Height = 65
          Caption = 'Envio'
          TabOrder = 4
          object CheckBox26: TCheckBox
            Left = 8
            Top = 14
            Width = 97
            Height = 14
            Caption = 'Trasmitida'
            Checked = True
            State = cbChecked
            TabOrder = 0
          end
          object CheckBox27: TCheckBox
            Left = 8
            Top = 30
            Width = 97
            Height = 14
            Caption = 'N'#227'o Transmitida'
            Checked = True
            State = cbChecked
            TabOrder = 1
          end
          object CheckBox28: TCheckBox
            Left = 8
            Top = 47
            Width = 97
            Height = 14
            Caption = 'Camcelada'
            Checked = True
            State = cbChecked
            TabOrder = 2
          end
        end
        object GroupBox29: TGroupBox
          Left = 9
          Top = 245
          Width = 171
          Height = 58
          Caption = 'Tipo NFe'
          TabOrder = 5
          object CheckBox29: TCheckBox
            Left = 6
            Top = 17
            Width = 97
            Height = 17
            Caption = 'Entrada'
            Checked = True
            State = cbChecked
            TabOrder = 0
          end
          object CheckBox30: TCheckBox
            Left = 6
            Top = 35
            Width = 97
            Height = 17
            Caption = 'Saida'
            Checked = True
            State = cbChecked
            TabOrder = 1
          end
        end
        object GroupBox30: TGroupBox
          Left = 9
          Top = 456
          Width = 171
          Height = 70
          Caption = 'Tipo'
          TabOrder = 6
          object CheckBox31: TCheckBox
            Left = 8
            Top = 14
            Width = 97
            Height = 17
            Caption = 'NFe'
            Checked = True
            State = cbChecked
            TabOrder = 0
          end
          object CheckBox32: TCheckBox
            Left = 8
            Top = 32
            Width = 61
            Height = 17
            Caption = 'CTe'
            Checked = True
            State = cbChecked
            TabOrder = 1
          end
          object CheckBox33: TCheckBox
            Left = 8
            Top = 50
            Width = 61
            Height = 17
            Caption = 'NFSe'
            Checked = True
            State = cbChecked
            TabOrder = 2
          end
        end
      end
      object BrvDbGrid8: TBrvDbGrid
        Left = 185
        Top = 0
        Width = 787
        Height = 652
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
    object TabSheet3: TTabSheet
      Caption = 'NFSe'
      ImageIndex = 4
      object Panel13: TPanel
        Left = 0
        Top = 0
        Width = 169
        Height = 652
        Align = alLeft
        TabOrder = 0
        object btnConsultarNFSePeriodo: TButton
          Left = 7
          Top = 43
          Width = 146
          Height = 25
          Caption = 'Consultar NFSe por Per'#237'odo'
          TabOrder = 0
        end
        object btnGerarRPS: TButton
          Left = 7
          Top = 12
          Width = 146
          Height = 25
          Caption = 'Gerar RPS'
          TabOrder = 1
        end
        object btnCancNFSe: TButton
          Left = 7
          Top = 74
          Width = 146
          Height = 25
          Caption = 'Cancelar NFSe'
          TabOrder = 2
        end
        object btnGerarEnviarNFSe: TButton
          Left = 7
          Top = 103
          Width = 146
          Height = 25
          Caption = 'Gerar e Enviar NFSe'
          TabOrder = 3
        end
        object btnConsultarNFSeRPS: TButton
          Left = 7
          Top = 134
          Width = 146
          Height = 25
          Caption = 'Consultar NFSe por RPS'
          TabOrder = 4
        end
        object btnImprimir: TButton
          Left = 7
          Top = 165
          Width = 146
          Height = 25
          Caption = 'Imprimir DANFSe'
          TabOrder = 5
        end
        object btnGerarLoteRPS: TButton
          Left = 7
          Top = 196
          Width = 146
          Height = 25
          Caption = 'Gerar Lote RPS'
          TabOrder = 6
        end
      end
      object PageControl7: TPageControl
        Left = 169
        Top = 0
        Width = 803
        Height = 652
        ActivePage = TabSheet29
        Align = alClient
        TabOrder = 1
        object TabSheet29: TTabSheet
          Caption = 'Busca Chave'
          ImageIndex = 2
          object Panel15: TPanel
            Left = 0
            Top = 0
            Width = 795
            Height = 65
            Align = alTop
            TabOrder = 0
            object Label46: TLabel
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
            object Edit6: TEdit
              Left = 127
              Top = 23
              Width = 434
              Height = 21
              TabOrder = 0
            end
            object BrvBitBtn6: TBrvBitBtn
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
          object PageControl8: TPageControl
            Left = 0
            Top = 65
            Width = 795
            Height = 559
            ActivePage = TabSheet31
            Align = alClient
            TabOrder = 1
            object TabSheet31: TTabSheet
              Caption = 'Respostas'
              object Memo1: TMemo
                Left = 0
                Top = 0
                Width = 787
                Height = 531
                Align = alClient
                ScrollBars = ssVertical
                TabOrder = 0
              end
            end
            object TabSheet32: TTabSheet
              Caption = 'XML Resposta'
              ImageIndex = 1
              object WebBrowser2: TWebBrowser
                Left = 0
                Top = 0
                Width = 787
                Height = 531
                Align = alClient
                TabOrder = 0
                ExplicitWidth = 706
                ExplicitHeight = 596
                ControlData = {
                  4C00000057510000E13600000000000000000000000000000000000000000000
                  000000004C000000000000000000000001000000E0D057007335CF11AE690800
                  2B2E126208000000000000004C0000000114020000000000C000000000000046
                  8000000000000000000000000000000000000000000000000000000000000000
                  00000000000000000100000000000000000000000000000000000000}
              end
            end
            object TabSheet33: TTabSheet
              Caption = 'Log'
              ImageIndex = 2
              object Memo7: TMemo
                Left = 0
                Top = 0
                Width = 787
                Height = 531
                Align = alClient
                ScrollBars = ssVertical
                TabOrder = 0
              end
            end
            object TabSheet34: TTabSheet
              Caption = 'NFe'
              ImageIndex = 3
              object TreeView3: TTreeView
                Left = 0
                Top = 0
                Width = 787
                Height = 531
                Align = alClient
                Indent = 19
                TabOrder = 0
              end
            end
            object TabSheet35: TTabSheet
              Caption = 'Retorno Completo WS'
              ImageIndex = 4
              object Memo8: TMemo
                Left = 0
                Top = 395
                Width = 787
                Height = 136
                Align = alBottom
                ScrollBars = ssVertical
                TabOrder = 0
              end
            end
            object TabSheet36: TTabSheet
              Caption = 'Dados'
              ImageIndex = 5
              object Memo9: TMemo
                Left = 0
                Top = 0
                Width = 787
                Height = 531
                Align = alClient
                ScrollBars = ssVertical
                TabOrder = 0
              end
            end
            object TabSheet37: TTabSheet
              Caption = 'RetornoConsulta NFe 2.01'
              ImageIndex = 6
              object TreeView4: TTreeView
                Left = 0
                Top = 0
                Width = 787
                Height = 531
                Align = alClient
                Indent = 19
                TabOrder = 0
              end
            end
          end
        end
      end
    end
    object TabSheet18: TTabSheet
      Caption = 'Gerar NFe'
      ImageIndex = 7
      object Panel21: TPanel
        Left = 0
        Top = 0
        Width = 972
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
          Width = 37
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
        object BrvLabel50: TBrvLabel
          Left = 8
          Top = 117
          Width = 47
          Height = 13
          Caption = 'Opera'#231#227'o'
        end
        object BrvDbEdit1: TBrvDbEdit
          Left = 77
          Top = 32
          Width = 238
          Height = 21
          DataField = 'CDEMPRES'
          TabOrder = 0
          BrAlignment = taRightJustify
          BrVisibleButton = True
          BrFunctionButton = TpDbConsulta
          BrConfigurar.Strings = (
            'CdEmpres;CdEmpres;S;S;'
            'EdtDsEmpres;DsEmpres;S;N;'
            'EdtCdEstEmp;CdEstado;S;N;'
            'EdtCdEmpEst;CdSeqEst;S;N;'
            'EdtCdGruEmp;CdGruEmp;S;N;')
          BrQueryConsulta = 12
          BrvReadOnlyColor = 14541539
        end
        object BrvDbEdit2: TBrvDbEdit
          Left = 77
          Top = 140
          Width = 87
          Height = 21
          DataField = 'NRNOTA'
          TabOrder = 6
          BrAlignment = taRightJustify
          BrVisibleButton = False
          BrFunctionButton = TpDbConsulta
          BrQueryConsulta = 0
          BrvReadOnlyColor = 14541539
        end
        object BrvDbEdit3: TBrvDbEdit
          Left = 229
          Top = 140
          Width = 60
          Height = 21
          DataField = 'NRSERINF'
          TabOrder = 7
          BrAlignment = taRightJustify
          BrVisibleButton = False
          BrFunctionButton = TpDbConsulta
          BrQueryConsulta = 0
          BrvReadOnlyColor = 14541539
        end
        object EdtRsTitula: TBrvRetCon
          Left = 228
          Top = 59
          Width = 431
          Height = 19
          TabStop = False
          BevelInner = bvLowered
          BevelKind = bkFlat
          BevelWidth = 2
          BorderStyle = bsNone
          ParentColor = True
          ReadOnly = True
          TabOrder = 2
        end
        object BrvDbEdit4: TBrvDbEdit
          Left = 357
          Top = 140
          Width = 60
          Height = 21
          DataField = 'NRSUSENF'
          TabOrder = 8
          BrAlignment = taRightJustify
          BrVisibleButton = False
          BrFunctionButton = TpDbConsulta
          BrQueryConsulta = 0
          BrvReadOnlyColor = 14541539
        end
        object BrvDbEdit8: TBrvDbEdit
          Left = 593
          Top = 140
          Width = 107
          Height = 21
          DataField = 'DTEMINOT'
          TabOrder = 10
          BrAlignment = taRightJustify
          BrVisibleButton = True
          BrFunctionButton = TpDbData
          BrQueryConsulta = 0
          BrvReadOnlyColor = 14541539
        end
        object BrvDbEdit10: TBrvDbEdit
          Left = 756
          Top = 140
          Width = 107
          Height = 21
          DataField = 'DTENTRAD'
          TabOrder = 11
          BrAlignment = taRightJustify
          BrVisibleButton = True
          BrFunctionButton = TpDbData
          BrQueryConsulta = 0
          BrvReadOnlyColor = 14541539
        end
        object BrvDbEdit41: TBrvDbEdit
          Left = 474
          Top = 140
          Width = 60
          Height = 21
          TabOrder = 9
          BrAlignment = taRightJustify
          BrVisibleButton = False
          BrFunctionButton = TpDbConsulta
          BrQueryConsulta = 0
          BrvReadOnlyColor = 14541539
        end
        object BrvDbEdit43: TBrvDbEdit
          Left = 77
          Top = 113
          Width = 87
          Height = 21
          DataField = 'NRSEQFIS'
          TabOrder = 4
          BrAlignment = taRightJustify
          BrVisibleButton = True
          BrFunctionButton = TpDbConsulta
          BrConfigurar.Strings = (
            'NrSeqFis;NrSeqFis;S;S;'
            'CdFiscal;CdFiscal;S;N;'
            'EdtDsFiscal;DsFiscal;S;N;'
            'EdtCdFiscal;CdFiscal;S;N;')
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
          TabOrder = 12
          object CbxSnItens: TCheckBox
            Left = 8
            Top = 18
            Width = 120
            Height = 17
            Caption = 'Nota com itens ?'
            Checked = True
            State = cbChecked
            TabOrder = 0
          end
          object CbxSnConhec: TCheckBox
            Left = 8
            Top = 38
            Width = 169
            Height = 17
            Caption = 'Conhecimento de Frete ?'
            TabOrder = 1
          end
          object CbxSnCreImp: TCheckBox
            Left = 8
            Top = 58
            Width = 121
            Height = 17
            Caption = 'Creditar ICMS ?'
            TabOrder = 2
          end
        end
        object BrvDbEdit42: TBrvDbEdit
          Left = 77
          Top = 59
          Width = 145
          Height = 21
          DataField = 'CJTITULA'
          TabOrder = 1
          BrAlignment = taRightJustify
          BrVisibleButton = True
          BrFunctionButton = TpDbConsulta
          BrConfigurar.Strings = (
            'CjTitula;CjTitula;S;S;'
            'CdTitula;CdTitula;S;N;'
            'EdtRsTitula;RsTitula;S;N;'
            'CdEstEmi;CdEstado;S;N;'
            'EdtCdEstEmi;CdEstado;S;N;')
          BrQueryConsulta = 19
          BrvReadOnlyColor = 14541539
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
          TabOrder = 3
        end
        object EdtDsFiscal: TBrvRetCon
          Left = 252
          Top = 111
          Width = 469
          Height = 19
          TabStop = False
          BevelInner = bvLowered
          BevelKind = bkFlat
          BevelWidth = 2
          BorderStyle = bsNone
          ParentColor = True
          ReadOnly = True
          TabOrder = 5
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
          TabOrder = 13
        end
      end
      object PgcDadosNF: TPageControl
        Left = 0
        Top = 169
        Width = 972
        Height = 449
        ActivePage = TbsImpostos
        Align = alClient
        TabOrder = 1
        object TbsItens: TTabSheet
          Caption = 'Itens da Nota'
          ImageIndex = 10
          object DbgF002: TBrvDbGrid
            Left = 0
            Top = 0
            Width = 964
            Height = 421
            BrShowMemo = True
            BrReadOnlyStyle = []
            BrReadOnlyColor = clMaroon
            Align = alClient
            BorderStyle = bsNone
            Ctl3D = False
            Options = [dgEditing, dgTitles, dgIndicator, dgColLines, dgRowLines, dgTabs, dgConfirmDelete, dgCancelOnExit]
            ParentCtl3D = False
            TabOrder = 0
            TitleFont.Charset = DEFAULT_CHARSET
            TitleFont.Color = clWindowText
            TitleFont.Height = -11
            TitleFont.Name = 'MS Sans Serif'
            TitleFont.Style = []
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
              Width = 42
              Height = 26
              Caption = 'Base de C'#225'lculo'
              WordWrap = True
            end
            object BrvLabel13: TBrvLabel
              Left = 151
              Top = 25
              Width = 37
              Height = 13
              Caption = '% ICMS'
            end
            object BrvLabel14: TBrvLabel
              Left = 295
              Top = 25
              Width = 24
              Height = 13
              Caption = 'Valor'
            end
            object BrvDbEdit11: TBrvDbEdit
              Left = 58
              Top = 21
              Width = 87
              Height = 21
              DataField = 'BSICMS'
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
              Width = 42
              Height = 26
              Caption = 'Base de C'#225'lculo'
              WordWrap = True
            end
            object BrvLabel16: TBrvLabel
              Left = 166
              Top = 25
              Width = 24
              Height = 13
              Caption = 'Valor'
            end
            object BrvDbEdit14: TBrvDbEdit
              Left = 73
              Top = 21
              Width = 87
              Height = 21
              DataField = 'BSSUBTRI'
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
              Width = 24
              Height = 13
              Caption = 'Valor'
            end
            object BrvDbEdit17: TBrvDbEdit
              Left = 41
              Top = 21
              Width = 87
              Height = 21
              DataField = 'VRIPI'
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
              Width = 34
              Height = 13
              Caption = 'Seguro'
            end
            object BrvLabel20: TBrvLabel
              Left = 151
              Top = 25
              Width = 34
              Height = 13
              Caption = 'Isentas'
            end
            object BrvLabel21: TBrvLabel
              Left = 295
              Top = 25
              Width = 31
              Height = 13
              Caption = 'Outras'
            end
            object BrvLabel19: TBrvLabel
              Left = 442
              Top = 17
              Width = 54
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
              Width = 51
              Height = 13
              Caption = 'Valor Frete'
            end
            object BrvDBComboListBox1: TBrvDBComboListBox
              Left = 99
              Top = 21
              Width = 246
              Height = 21
              DataField = 'TPFRETE'
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
              TabOrder = 3
              BrAlignment = taRightJustify
              BrVisibleButton = True
              BrFunctionButton = TpDbConsulta
              BrConfigurar.Strings = (
                'CdTransp;CdTitula;S;S;'
                'EdtRsTransp;RsTitula;S;N;')
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
          object GroupBox32: TGroupBox
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
              Width = 77
              Height = 13
              Caption = 'Base de C'#225'lculo'
            end
            object BrvLabel32: TBrvLabel
              Left = 6
              Top = 52
              Width = 40
              Height = 13
              Caption = 'Al'#237'quota'
            end
            object BrvLabel35: TBrvLabel
              Left = 6
              Top = 79
              Width = 24
              Height = 13
              Caption = 'Valor'
            end
            object BrvDbEdit28: TBrvDbEdit
              Left = 98
              Top = 75
              Width = 87
              Height = 21
              DataField = 'VRPIS'
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
              TabOrder = 1
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
              TabOrder = 0
              BrAlignment = taRightJustify
              BrVisibleButton = False
              BrFunctionButton = TpDbConsulta
              BrQueryConsulta = 0
              BrvReadOnlyColor = 14541539
            end
          end
          object GroupBox33: TGroupBox
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
              Width = 77
              Height = 13
              Caption = 'Base de C'#225'lculo'
            end
            object BrvLabel36: TBrvLabel
              Left = 6
              Top = 52
              Width = 40
              Height = 13
              Caption = 'Al'#237'quota'
            end
            object BrvLabel37: TBrvLabel
              Left = 6
              Top = 79
              Width = 24
              Height = 13
              Caption = 'Valor'
            end
            object BrvDbEdit29: TBrvDbEdit
              Left = 98
              Top = 21
              Width = 87
              Height = 21
              DataField = 'BSCOFINS'
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
              TabOrder = 1
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
              TabOrder = 2
              BrAlignment = taRightJustify
              BrVisibleButton = False
              BrFunctionButton = TpDbConsulta
              BrQueryConsulta = 0
              BrvReadOnlyColor = 14541539
            end
          end
          object GroupBox34: TGroupBox
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
              Width = 77
              Height = 13
              Caption = 'Base de C'#225'lculo'
            end
            object BrvLabel34: TBrvLabel
              Left = 6
              Top = 52
              Width = 40
              Height = 13
              Caption = 'Al'#237'quota'
            end
            object BrvLabel38: TBrvLabel
              Left = 6
              Top = 79
              Width = 24
              Height = 13
              Caption = 'Valor'
            end
            object BrvDbEdit32: TBrvDbEdit
              Left = 98
              Top = 21
              Width = 87
              Height = 21
              DataField = 'BSCSLL'
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
              TabOrder = 1
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
              TabOrder = 2
              BrAlignment = taRightJustify
              BrVisibleButton = False
              BrFunctionButton = TpDbConsulta
              BrQueryConsulta = 0
              BrvReadOnlyColor = 14541539
            end
          end
          object GroupBox35: TGroupBox
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
              Width = 77
              Height = 13
              Caption = 'Base de C'#225'lculo'
            end
            object BrvLabel40: TBrvLabel
              Left = 6
              Top = 52
              Width = 40
              Height = 13
              Caption = 'Al'#237'quota'
            end
            object BrvLabel41: TBrvLabel
              Left = 6
              Top = 79
              Width = 24
              Height = 13
              Caption = 'Valor'
            end
            object BrvDbEdit35: TBrvDbEdit
              Left = 99
              Top = 21
              Width = 87
              Height = 21
              DataField = 'BSIRRF'
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
              TabOrder = 1
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
              TabOrder = 2
              BrAlignment = taRightJustify
              BrVisibleButton = False
              BrFunctionButton = TpDbConsulta
              BrQueryConsulta = 0
              BrvReadOnlyColor = 14541539
            end
          end
          object GroupBox36: TGroupBox
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
              Width = 77
              Height = 13
              Caption = 'Base de C'#225'lculo'
            end
            object BrvLabel43: TBrvLabel
              Left = 6
              Top = 52
              Width = 40
              Height = 13
              Caption = 'Al'#237'quota'
            end
            object BrvLabel44: TBrvLabel
              Left = 6
              Top = 79
              Width = 24
              Height = 13
              Caption = 'Valor'
            end
            object BrvDbEdit38: TBrvDbEdit
              Left = 98
              Top = 21
              Width = 87
              Height = 21
              DataField = 'BSINSS'
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
              TabOrder = 1
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
              TabOrder = 2
              BrAlignment = taRightJustify
              BrVisibleButton = False
              BrFunctionButton = TpDbConsulta
              BrQueryConsulta = 0
              BrvReadOnlyColor = 14541539
            end
          end
          object GroupBox37: TGroupBox
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
              Width = 77
              Height = 13
              Caption = 'Base de C'#225'lculo'
            end
            object BrvLabel46: TBrvLabel
              Left = 6
              Top = 52
              Width = 40
              Height = 13
              Caption = 'Al'#237'quota'
            end
            object BrvLabel47: TBrvLabel
              Left = 6
              Top = 79
              Width = 24
              Height = 13
              Caption = 'Valor'
            end
            object BrvDbEdit22: TBrvDbEdit
              Left = 99
              Top = 21
              Width = 87
              Height = 21
              DataField = 'BSISSQN'
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
              TabOrder = 1
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
              TabOrder = 2
              BrAlignment = taRightJustify
              BrVisibleButton = False
              BrFunctionButton = TpDbConsulta
              BrQueryConsulta = 0
              BrvReadOnlyColor = 14541539
            end
          end
          object GroupBox38: TGroupBox
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
            end
            object CbxCOFINS: TCheckBox
              Left = 6
              Top = 52
              Width = 67
              Height = 17
              Caption = 'COFINS'
              TabOrder = 1
            end
            object CbxCSLL: TCheckBox
              Left = 6
              Top = 79
              Width = 67
              Height = 17
              Caption = 'CSLL'
              TabOrder = 2
            end
            object CbxIRRF: TCheckBox
              Left = 88
              Top = 24
              Width = 67
              Height = 17
              Caption = 'IRRF'
              TabOrder = 3
            end
            object CbxISSQN: TCheckBox
              Left = 88
              Top = 52
              Width = 67
              Height = 17
              Caption = 'ISSQN'
              TabOrder = 4
            end
            object CbxINSS: TCheckBox
              Left = 88
              Top = 79
              Width = 67
              Height = 17
              Caption = 'INSS'
              TabOrder = 5
            end
          end
        end
        object TbsParcela: TTabSheet
          Caption = 'Parcelas'
          ImageIndex = 9
          object DbgN003: TBrvDbGrid
            Left = 0
            Top = 0
            Width = 964
            Height = 387
            BrShowMemo = True
            BrReadOnlyStyle = [fsItalic]
            BrReadOnlyColor = clMaroon
            Align = alClient
            TabOrder = 0
            TitleFont.Charset = DEFAULT_CHARSET
            TitleFont.Color = clWindowText
            TitleFont.Height = -11
            TitleFont.Name = 'MS Sans Serif'
            TitleFont.Style = []
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
          object Panel22: TPanel
            Left = 0
            Top = 387
            Width = 964
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
            Width = 964
            Height = 421
            Align = alClient
            DataField = 'TXDADADI'
            TabOrder = 0
          end
        end
      end
      object Panel23: TPanel
        Left = 0
        Top = 618
        Width = 972
        Height = 34
        Align = alBottom
        BevelOuter = bvNone
        TabOrder = 2
        DesignSize = (
          972
          34)
        object Bevel2: TBevel
          Left = 771
          Top = 4
          Width = 5
          Height = 30
          Shape = bsLeftLine
        end
        object GroupBox39: TGroupBox
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
            Width = 69
            Height = 13
            Caption = 'Total Produtos'
          end
          object BrvLabel28: TBrvLabel
            Left = 433
            Top = 13
            Width = 50
            Height = 13
            Caption = 'Total Nota'
          end
          object BrvDbEdit6: TBrvDbEdit
            Left = 110
            Top = 9
            Width = 87
            Height = 21
            DataField = 'NRPRELAN'
            ReadOnly = True
            TabOrder = 0
            BrAlignment = taRightJustify
            BrVisibleButton = False
            BrFunctionButton = TpDbConsulta
            BrQueryConsulta = 0
            BrvReadOnlyColor = 14541539
          end
          object BrvDbEdit25: TBrvDbEdit
            Left = 528
            Top = 9
            Width = 87
            Height = 21
            DataField = 'VRCONNOT'
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
          Left = 710
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
          BrTipoBotao = BrBtnSalvar
        end
        object BtnCancelar: TBrvBitBtn
          Left = 865
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
          BrTipoBotao = BrBtnCancel
        end
      end
    end
    object TabSheet25: TTabSheet
      Caption = 'Gerar NFSe'
      ImageIndex = 8
    end
    object TabSheet28: TTabSheet
      Caption = 'Gerar Conhecimento'
      ImageIndex = 9
    end
    object TabSheet38: TTabSheet
      Caption = 'Titulares'
      ImageIndex = 11
      object PageControl10: TPageControl
        Left = 0
        Top = 0
        Width = 972
        Height = 652
        ActivePage = TabSheet43
        Align = alClient
        TabOrder = 0
        object TabSheet43: TTabSheet
          Caption = 'Titulares'
          object Panel41: TPanel
            Left = 0
            Top = 0
            Width = 964
            Height = 41
            Align = alTop
            TabOrder = 0
          end
          object Panel42: TPanel
            Left = 0
            Top = 41
            Width = 177
            Height = 583
            Align = alLeft
            TabOrder = 1
            object GroupBox46: TGroupBox
              Left = 3
              Top = 6
              Width = 171
              Height = 107
              Caption = 'Tipo Cliente'
              TabOrder = 0
              object CheckBox37: TCheckBox
                Left = 7
                Top = 16
                Width = 146
                Height = 17
                Caption = 'Fornecedor'
                Checked = True
                State = cbChecked
                TabOrder = 0
              end
              object CheckBox38: TCheckBox
                Left = 7
                Top = 39
                Width = 146
                Height = 17
                Caption = 'Cliente'
                Checked = True
                State = cbChecked
                TabOrder = 1
              end
              object CheckBox39: TCheckBox
                Left = 7
                Top = 62
                Width = 97
                Height = 17
                Caption = 'Transportador'
                Checked = True
                State = cbChecked
                TabOrder = 2
              end
              object CheckBox40: TCheckBox
                Left = 7
                Top = 85
                Width = 97
                Height = 17
                Caption = 'Contador'
                Checked = True
                State = cbChecked
                TabOrder = 3
              end
            end
            object GroupBox47: TGroupBox
              Left = 2
              Top = 114
              Width = 171
              Height = 66
              Caption = 'Status Cliente'
              TabOrder = 1
              object CheckBox41: TCheckBox
                Left = 7
                Top = 16
                Width = 146
                Height = 17
                Caption = 'Ativo'
                Checked = True
                State = cbChecked
                TabOrder = 0
              end
              object CheckBox42: TCheckBox
                Left = 7
                Top = 39
                Width = 146
                Height = 17
                Caption = 'Inativo'
                Checked = True
                State = cbChecked
                TabOrder = 1
              end
            end
            object GroupBox48: TGroupBox
              Left = 2
              Top = 253
              Width = 171
              Height = 129
              Caption = 'Dados'
              TabOrder = 2
              object Label70: TLabel
                Left = 3
                Top = 12
                Width = 65
                Height = 13
                Caption = 'Codigo Titular'
              end
              object Label74: TLabel
                Left = 3
                Top = 50
                Width = 42
                Height = 13
                Caption = 'Cnpj/Cpf'
              end
              object Label111: TLabel
                Left = 3
                Top = 86
                Width = 96
                Height = 13
                Caption = 'Raz'#227'o Social/Nome'
              end
              object BrvEditNum9: TBrvEditNum
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
              object BrvEditNum10: TBrvEditNum
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
              object BrvEditNum13: TBrvEditNum
                Left = 3
                Top = 100
                Width = 158
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
                BrQueryCode = 0
                BrRecordar = False
              end
            end
            object GroupBox49: TGroupBox
              Left = 2
              Top = 181
              Width = 171
              Height = 66
              Caption = 'Pessoa'
              TabOrder = 3
              object CheckBox43: TCheckBox
                Left = 7
                Top = 16
                Width = 146
                Height = 17
                Caption = 'Fisica'
                Checked = True
                State = cbChecked
                TabOrder = 0
              end
              object CheckBox44: TCheckBox
                Left = 7
                Top = 38
                Width = 146
                Height = 17
                Caption = 'Juridica'
                Checked = True
                State = cbChecked
                TabOrder = 1
              end
            end
          end
          object Panel43: TPanel
            Left = 177
            Top = 41
            Width = 787
            Height = 583
            Align = alClient
            TabOrder = 2
            object Label1: TLabel
              Left = 40
              Top = 224
              Width = 53
              Height = 13
              Caption = 'CDTITULA'
              FocusControl = DBEdit1
            end
            object Label2: TLabel
              Left = 40
              Top = 264
              Width = 52
              Height = 13
              Caption = 'TPTITULA'
              FocusControl = DBEdit2
            end
            object Label3: TLabel
              Left = 40
              Top = 304
              Width = 75
              Height = 13
              Caption = 'RAZAOSOCIAL'
              FocusControl = DBEdit3
            end
            object Label4: TLabel
              Left = 40
              Top = 344
              Width = 27
              Height = 13
              Caption = 'CNPJ'
              FocusControl = DBEdit4
            end
            object Label5: TLabel
              Left = 40
              Top = 384
              Width = 63
              Height = 13
              Caption = 'CODCIDADE'
              FocusControl = DBEdit5
            end
            object Label6: TLabel
              Left = 40
              Top = 424
              Width = 12
              Height = 13
              Caption = 'IM'
              FocusControl = DBEdit6
            end
            object Label7: TLabel
              Left = 40
              Top = 464
              Width = 10
              Height = 13
              Caption = 'IE'
              FocusControl = DBEdit7
            end
            object Label8: TLabel
              Left = 40
              Top = 504
              Width = 84
              Height = 13
              Caption = 'NOMEFANTASIA'
              FocusControl = DBEdit8
            end
            object Label9: TLabel
              Left = 40
              Top = 544
              Width = 43
              Height = 13
              Caption = 'STATUS'
              FocusControl = DBEdit9
            end
            object Label10: TLabel
              Left = 40
              Top = 584
              Width = 45
              Height = 13
              Caption = 'CLIENTE'
              FocusControl = DBEdit10
            end
            object Label11: TLabel
              Left = 40
              Top = 624
              Width = 75
              Height = 13
              Caption = 'FORNECEDOR'
              FocusControl = DBEdit11
            end
            object Label12: TLabel
              Left = 40
              Top = 664
              Width = 61
              Height = 13
              Caption = 'CONTADOR'
              FocusControl = DBEdit12
            end
            object Label13: TLabel
              Left = 40
              Top = 704
              Width = 98
              Height = 13
              Caption = 'TRANSPORTADOR'
              FocusControl = DBEdit13
            end
            object Panel44: TPanel
              Left = 1
              Top = 1
              Width = 785
              Height = 184
              Align = alTop
              TabOrder = 0
              object BrvDbGrid7: TBrvDbGrid
                Left = 1
                Top = 1
                Width = 783
                Height = 182
                BrShowMemo = True
                BrReadOnlyStyle = [fsItalic]
                BrReadOnlyColor = clMaroon
                Align = alClient
                DataSource = DSTitula
                Options = [dgEditing, dgTitles, dgIndicator, dgColumnResize, dgColLines, dgRowLines, dgTabs]
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
            object Panel45: TPanel
              Left = 1
              Top = 185
              Width = 785
              Height = 24
              Align = alTop
              TabOrder = 1
              object XDBNavigator4: TXDBNavigator
                Left = 1
                Top = 1
                Width = 783
                Height = 22
                DataSource = DSTitula
                Align = alClient
                TabOrder = 0
              end
            end
            object DBEdit1: TDBEdit
              Left = 40
              Top = 240
              Width = 134
              Height = 21
              DataField = 'CDTITULA'
              DataSource = DSTitula
              TabOrder = 2
            end
            object DBEdit2: TDBEdit
              Left = 40
              Top = 280
              Width = 17
              Height = 21
              DataField = 'TPTITULA'
              DataSource = DSTitula
              TabOrder = 3
            end
            object DBEdit3: TDBEdit
              Left = 40
              Top = 320
              Width = 2604
              Height = 21
              DataField = 'RAZAOSOCIAL'
              DataSource = DSTitula
              TabOrder = 4
            end
            object DBEdit4: TDBEdit
              Left = 40
              Top = 360
              Width = 212
              Height = 21
              DataField = 'CNPJ'
              DataSource = DSTitula
              TabOrder = 5
            end
            object DBEdit5: TDBEdit
              Left = 40
              Top = 400
              Width = 134
              Height = 21
              DataField = 'CODCIDADE'
              DataSource = DSTitula
              TabOrder = 6
            end
            object DBEdit6: TDBEdit
              Left = 40
              Top = 440
              Width = 264
              Height = 21
              DataField = 'IM'
              DataSource = DSTitula
              TabOrder = 7
            end
            object DBEdit7: TDBEdit
              Left = 40
              Top = 480
              Width = 264
              Height = 21
              DataField = 'IE'
              DataSource = DSTitula
              TabOrder = 8
            end
            object DBEdit8: TDBEdit
              Left = 40
              Top = 520
              Width = 17
              Height = 21
              DataField = 'NOMEFANTASIA'
              DataSource = DSTitula
              TabOrder = 9
            end
            object DBEdit9: TDBEdit
              Left = 40
              Top = 560
              Width = 17
              Height = 21
              DataField = 'STATUS'
              DataSource = DSTitula
              TabOrder = 10
            end
            object DBEdit10: TDBEdit
              Left = 40
              Top = 600
              Width = 17
              Height = 21
              DataField = 'CLIENTE'
              DataSource = DSTitula
              TabOrder = 11
            end
            object DBEdit11: TDBEdit
              Left = 40
              Top = 640
              Width = 17
              Height = 21
              DataField = 'FORNECEDOR'
              DataSource = DSTitula
              TabOrder = 12
            end
            object DBEdit12: TDBEdit
              Left = 40
              Top = 680
              Width = 17
              Height = 21
              DataField = 'CONTADOR'
              DataSource = DSTitula
              TabOrder = 13
            end
            object DBEdit13: TDBEdit
              Left = 40
              Top = 720
              Width = 17
              Height = 21
              DataField = 'TRANSPORTADOR'
              DataSource = DSTitula
              TabOrder = 14
            end
          end
        end
        object TabSheet46: TTabSheet
          Caption = 'TabSheet46'
          ImageIndex = 1
          object Panel46: TPanel
            Left = 0
            Top = 0
            Width = 964
            Height = 41
            Align = alTop
            TabOrder = 0
          end
          object Panel47: TPanel
            Left = 0
            Top = 41
            Width = 169
            Height = 583
            Align = alLeft
            TabOrder = 1
          end
          object Panel48: TPanel
            Left = 169
            Top = 41
            Width = 795
            Height = 583
            Align = alClient
            TabOrder = 2
            object Panel49: TPanel
              Left = 1
              Top = 1
              Width = 793
              Height = 184
              Align = alTop
              TabOrder = 0
              object BrvDbGrid9: TBrvDbGrid
                Left = 1
                Top = 1
                Width = 791
                Height = 182
                BrShowMemo = True
                BrReadOnlyStyle = [fsItalic]
                BrReadOnlyColor = clMaroon
                Align = alClient
                DataSource = DataSource1
                Options = [dgEditing, dgTitles, dgIndicator, dgColumnResize, dgColLines, dgRowLines, dgTabs]
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
            object Panel50: TPanel
              Left = 1
              Top = 185
              Width = 793
              Height = 24
              Align = alTop
              TabOrder = 1
              object XDBNavigator5: TXDBNavigator
                Left = 1
                Top = 1
                Width = 791
                Height = 22
                Align = alClient
                TabOrder = 0
              end
            end
          end
        end
      end
    end
    object TabSheet41: TTabSheet
      Caption = 'Produtos'
      ImageIndex = 12
      object PageControl9: TPageControl
        Left = 0
        Top = 0
        Width = 972
        Height = 652
        ActivePage = TabSheet42
        Align = alClient
        TabOrder = 0
        object TabSheet42: TTabSheet
          Caption = 'Cadastro Produto'
          object Panel26: TPanel
            Left = 0
            Top = 41
            Width = 177
            Height = 583
            Align = alLeft
            TabOrder = 0
            object GroupBox50: TGroupBox
              Left = 3
              Top = 6
              Width = 171
              Height = 107
              Caption = 'Tipo Cliente'
              TabOrder = 0
              object CheckBox45: TCheckBox
                Left = 7
                Top = 16
                Width = 146
                Height = 17
                Caption = 'Fornecedor'
                Checked = True
                State = cbChecked
                TabOrder = 0
              end
              object CheckBox46: TCheckBox
                Left = 7
                Top = 39
                Width = 146
                Height = 17
                Caption = 'Cliente'
                Checked = True
                State = cbChecked
                TabOrder = 1
              end
              object CheckBox47: TCheckBox
                Left = 7
                Top = 62
                Width = 97
                Height = 17
                Caption = 'Transportador'
                Checked = True
                State = cbChecked
                TabOrder = 2
              end
              object CheckBox48: TCheckBox
                Left = 7
                Top = 85
                Width = 97
                Height = 17
                Caption = 'Contador'
                Checked = True
                State = cbChecked
                TabOrder = 3
              end
            end
            object GroupBox51: TGroupBox
              Left = 3
              Top = 114
              Width = 171
              Height = 66
              Caption = 'Status Cliente'
              TabOrder = 1
              object CheckBox49: TCheckBox
                Left = 7
                Top = 16
                Width = 146
                Height = 17
                Caption = 'Ativo'
                Checked = True
                State = cbChecked
                TabOrder = 0
              end
              object CheckBox50: TCheckBox
                Left = 7
                Top = 39
                Width = 146
                Height = 17
                Caption = 'Inativo'
                Checked = True
                State = cbChecked
                TabOrder = 1
              end
            end
            object GroupBox52: TGroupBox
              Left = 3
              Top = 181
              Width = 171
              Height = 66
              Caption = 'Pessoa'
              TabOrder = 2
              object CheckBox51: TCheckBox
                Left = 7
                Top = 16
                Width = 146
                Height = 17
                Caption = 'Fisica'
                Checked = True
                State = cbChecked
                TabOrder = 0
              end
              object CheckBox52: TCheckBox
                Left = 7
                Top = 38
                Width = 146
                Height = 17
                Caption = 'Juridica'
                Checked = True
                State = cbChecked
                TabOrder = 1
              end
            end
            object GroupBox53: TGroupBox
              Left = 3
              Top = 253
              Width = 171
              Height = 129
              Caption = 'Dados'
              TabOrder = 3
              object Label109: TLabel
                Left = 3
                Top = 12
                Width = 65
                Height = 13
                Caption = 'Codigo Titular'
              end
              object Label110: TLabel
                Left = 3
                Top = 50
                Width = 42
                Height = 13
                Caption = 'Cnpj/Cpf'
              end
              object Label112: TLabel
                Left = 3
                Top = 86
                Width = 96
                Height = 13
                Caption = 'Raz'#227'o Social/Nome'
              end
              object BrvEditNum11: TBrvEditNum
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
              object BrvEditNum12: TBrvEditNum
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
              object BrvEditNum14: TBrvEditNum
                Left = 3
                Top = 100
                Width = 158
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
                BrQueryCode = 0
                BrRecordar = False
              end
            end
          end
          object Panel27: TPanel
            Left = 177
            Top = 41
            Width = 787
            Height = 583
            Align = alClient
            TabOrder = 1
            object Panel29: TPanel
              Left = 1
              Top = 1
              Width = 785
              Height = 184
              Align = alTop
              TabOrder = 0
              object BrvDbGrid3: TBrvDbGrid
                Left = 1
                Top = 1
                Width = 783
                Height = 182
                BrShowMemo = True
                BrReadOnlyStyle = [fsItalic]
                BrReadOnlyColor = clMaroon
                Align = alClient
                DataSource = DSProduto
                Options = [dgEditing, dgTitles, dgIndicator, dgColumnResize, dgColLines, dgRowLines, dgTabs]
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
            object Panel30: TPanel
              Left = 1
              Top = 185
              Width = 785
              Height = 24
              Align = alTop
              TabOrder = 1
              object XDBNavigator1: TXDBNavigator
                Left = 1
                Top = 1
                Width = 783
                Height = 22
                DataSource = DSProduto
                Align = alClient
                TabOrder = 0
              end
            end
          end
          object Panel28: TPanel
            Left = 0
            Top = 0
            Width = 964
            Height = 41
            Align = alTop
            TabOrder = 2
          end
        end
        object TabSheet44: TTabSheet
          Caption = 'Cadastro Tributa'#231#227'o'
          ImageIndex = 1
          object Panel31: TPanel
            Left = 0
            Top = 0
            Width = 964
            Height = 41
            Align = alTop
            TabOrder = 0
          end
          object Panel32: TPanel
            Left = 0
            Top = 41
            Width = 177
            Height = 583
            Align = alLeft
            TabOrder = 1
            object GroupBox54: TGroupBox
              Left = 3
              Top = 6
              Width = 171
              Height = 107
              Caption = 'Tipo Cliente'
              TabOrder = 0
              object CheckBox53: TCheckBox
                Left = 7
                Top = 16
                Width = 146
                Height = 17
                Caption = 'Fornecedor'
                Checked = True
                State = cbChecked
                TabOrder = 0
              end
              object CheckBox54: TCheckBox
                Left = 7
                Top = 39
                Width = 146
                Height = 17
                Caption = 'Cliente'
                Checked = True
                State = cbChecked
                TabOrder = 1
              end
              object CheckBox55: TCheckBox
                Left = 7
                Top = 62
                Width = 97
                Height = 17
                Caption = 'Transportador'
                Checked = True
                State = cbChecked
                TabOrder = 2
              end
              object CheckBox56: TCheckBox
                Left = 7
                Top = 85
                Width = 97
                Height = 17
                Caption = 'Contador'
                Checked = True
                State = cbChecked
                TabOrder = 3
              end
            end
            object GroupBox55: TGroupBox
              Left = 3
              Top = 114
              Width = 171
              Height = 66
              Caption = 'Status Cliente'
              TabOrder = 1
              object CheckBox57: TCheckBox
                Left = 7
                Top = 16
                Width = 146
                Height = 17
                Caption = 'Ativo'
                Checked = True
                State = cbChecked
                TabOrder = 0
              end
              object CheckBox58: TCheckBox
                Left = 7
                Top = 39
                Width = 146
                Height = 17
                Caption = 'Inativo'
                Checked = True
                State = cbChecked
                TabOrder = 1
              end
            end
            object GroupBox56: TGroupBox
              Left = 3
              Top = 181
              Width = 171
              Height = 66
              Caption = 'Pessoa'
              TabOrder = 2
              object CheckBox59: TCheckBox
                Left = 7
                Top = 16
                Width = 146
                Height = 17
                Caption = 'Fisica'
                Checked = True
                State = cbChecked
                TabOrder = 0
              end
              object CheckBox60: TCheckBox
                Left = 7
                Top = 38
                Width = 146
                Height = 17
                Caption = 'Juridica'
                Checked = True
                State = cbChecked
                TabOrder = 1
              end
            end
            object GroupBox57: TGroupBox
              Left = 3
              Top = 253
              Width = 171
              Height = 129
              Caption = 'Dados'
              TabOrder = 3
              object Label113: TLabel
                Left = 3
                Top = 12
                Width = 65
                Height = 13
                Caption = 'Codigo Titular'
              end
              object Label114: TLabel
                Left = 3
                Top = 50
                Width = 42
                Height = 13
                Caption = 'Cnpj/Cpf'
              end
              object Label115: TLabel
                Left = 3
                Top = 86
                Width = 96
                Height = 13
                Caption = 'Raz'#227'o Social/Nome'
              end
              object BrvEditNum15: TBrvEditNum
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
              object BrvEditNum16: TBrvEditNum
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
              object BrvEditNum17: TBrvEditNum
                Left = 3
                Top = 100
                Width = 158
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
                BrQueryCode = 0
                BrRecordar = False
              end
            end
          end
          object Panel33: TPanel
            Left = 177
            Top = 41
            Width = 787
            Height = 583
            Align = alClient
            TabOrder = 2
            object Panel34: TPanel
              Left = 1
              Top = 1
              Width = 785
              Height = 184
              Align = alTop
              TabOrder = 0
              object BrvDbGrid5: TBrvDbGrid
                Left = 1
                Top = 1
                Width = 783
                Height = 182
                BrShowMemo = True
                BrReadOnlyStyle = [fsItalic]
                BrReadOnlyColor = clMaroon
                Align = alClient
                DataSource = DataSource1
                Options = [dgEditing, dgTitles, dgIndicator, dgColumnResize, dgColLines, dgRowLines, dgTabs]
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
            object Panel35: TPanel
              Left = 1
              Top = 185
              Width = 785
              Height = 24
              Align = alTop
              TabOrder = 1
              object XDBNavigator2: TXDBNavigator
                Left = 1
                Top = 1
                Width = 783
                Height = 22
                Align = alClient
                TabOrder = 0
              end
            end
          end
        end
        object TabSheet45: TTabSheet
          Caption = 'Cadastro Unidade de Medida'
          ImageIndex = 2
          object Panel36: TPanel
            Left = 0
            Top = 0
            Width = 964
            Height = 41
            Align = alTop
            TabOrder = 0
          end
          object Panel37: TPanel
            Left = 0
            Top = 41
            Width = 177
            Height = 583
            Align = alLeft
            TabOrder = 1
            object GroupBox58: TGroupBox
              Left = 3
              Top = 6
              Width = 171
              Height = 107
              Caption = 'Tipo Cliente'
              TabOrder = 0
              object CheckBox61: TCheckBox
                Left = 7
                Top = 16
                Width = 146
                Height = 17
                Caption = 'Fornecedor'
                Checked = True
                State = cbChecked
                TabOrder = 0
              end
              object CheckBox62: TCheckBox
                Left = 7
                Top = 39
                Width = 146
                Height = 17
                Caption = 'Cliente'
                Checked = True
                State = cbChecked
                TabOrder = 1
              end
              object CheckBox63: TCheckBox
                Left = 7
                Top = 62
                Width = 97
                Height = 17
                Caption = 'Transportador'
                Checked = True
                State = cbChecked
                TabOrder = 2
              end
              object CheckBox64: TCheckBox
                Left = 7
                Top = 85
                Width = 97
                Height = 17
                Caption = 'Contador'
                Checked = True
                State = cbChecked
                TabOrder = 3
              end
            end
            object GroupBox59: TGroupBox
              Left = 3
              Top = 114
              Width = 171
              Height = 66
              Caption = 'Status Cliente'
              TabOrder = 1
              object CheckBox65: TCheckBox
                Left = 7
                Top = 16
                Width = 146
                Height = 17
                Caption = 'Ativo'
                Checked = True
                State = cbChecked
                TabOrder = 0
              end
              object CheckBox66: TCheckBox
                Left = 7
                Top = 39
                Width = 146
                Height = 17
                Caption = 'Inativo'
                Checked = True
                State = cbChecked
                TabOrder = 1
              end
            end
            object GroupBox60: TGroupBox
              Left = 3
              Top = 181
              Width = 171
              Height = 66
              Caption = 'Pessoa'
              TabOrder = 2
              object CheckBox67: TCheckBox
                Left = 7
                Top = 16
                Width = 146
                Height = 17
                Caption = 'Fisica'
                Checked = True
                State = cbChecked
                TabOrder = 0
              end
              object CheckBox68: TCheckBox
                Left = 7
                Top = 38
                Width = 146
                Height = 17
                Caption = 'Juridica'
                Checked = True
                State = cbChecked
                TabOrder = 1
              end
            end
            object GroupBox61: TGroupBox
              Left = 3
              Top = 253
              Width = 171
              Height = 129
              Caption = 'Dados'
              TabOrder = 3
              object Label116: TLabel
                Left = 3
                Top = 12
                Width = 65
                Height = 13
                Caption = 'Codigo Titular'
              end
              object Label117: TLabel
                Left = 3
                Top = 50
                Width = 42
                Height = 13
                Caption = 'Cnpj/Cpf'
              end
              object Label118: TLabel
                Left = 3
                Top = 86
                Width = 96
                Height = 13
                Caption = 'Raz'#227'o Social/Nome'
              end
              object BrvEditNum18: TBrvEditNum
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
              object BrvEditNum19: TBrvEditNum
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
              object BrvEditNum20: TBrvEditNum
                Left = 3
                Top = 100
                Width = 158
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
                BrQueryCode = 0
                BrRecordar = False
              end
            end
          end
          object Panel38: TPanel
            Left = 177
            Top = 41
            Width = 787
            Height = 583
            Align = alClient
            TabOrder = 2
            object Panel39: TPanel
              Left = 1
              Top = 1
              Width = 785
              Height = 184
              Align = alTop
              TabOrder = 0
              object BrvDbGrid6: TBrvDbGrid
                Left = 1
                Top = 1
                Width = 783
                Height = 182
                BrShowMemo = True
                BrReadOnlyStyle = [fsItalic]
                BrReadOnlyColor = clMaroon
                Align = alClient
                DataSource = DSUniMed
                Options = [dgEditing, dgTitles, dgIndicator, dgColumnResize, dgColLines, dgRowLines, dgTabs]
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
            object Panel40: TPanel
              Left = 1
              Top = 185
              Width = 785
              Height = 24
              Align = alTop
              TabOrder = 1
              object XDBNavigator3: TXDBNavigator
                Left = 1
                Top = 1
                Width = 783
                Height = 22
                DataSource = DSUniMed
                Align = alClient
                TabOrder = 0
              end
            end
          end
        end
      end
    end
    object TabSheet47: TTabSheet
      Caption = 'Estoque'
      ImageIndex = 13
      object Panel51: TPanel
        Left = 0
        Top = 0
        Width = 169
        Height = 652
        Align = alLeft
        TabOrder = 0
      end
      object Panel52: TPanel
        Left = 169
        Top = 0
        Width = 803
        Height = 652
        Align = alClient
        TabOrder = 1
        object Panel53: TPanel
          Left = 1
          Top = 1
          Width = 801
          Height = 615
          Align = alClient
          TabOrder = 0
          object BrvDbGrid10: TBrvDbGrid
            Left = 1
            Top = 1
            Width = 799
            Height = 613
            BrShowMemo = True
            BrReadOnlyStyle = [fsItalic]
            BrReadOnlyColor = clMaroon
            Align = alClient
            DataSource = DataSource1
            Options = [dgEditing, dgTitles, dgIndicator, dgColumnResize, dgColLines, dgRowLines, dgTabs]
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
        object Panel55: TPanel
          Left = 1
          Top = 616
          Width = 801
          Height = 35
          Align = alBottom
          TabOrder = 1
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
    Left = 782
    Top = 182
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
    Left = 478
    Top = 576
  end
  object BrvXMLNFE: TBrvXML
    BrGerarBanco = True
    BrQtdePagDanfe = 0
    Left = 704
    Top = 32
  end
  object DataSource1: TDataSource
    Left = 432
    Top = 344
  end
  object xml: TClientDataSet
    Aggregates = <>
    Params = <>
    ProviderName = 'DataSetProvider1'
    Left = 304
    Top = 600
    object xmlXMOTIVO: TStringField
      FieldName = 'XMOTIVO'
      Size = 99
    end
    object xmlCHNFE: TStringField
      FieldName = 'CHNFE'
      Required = True
      Size = 50
    end
    object xmlDHRECBTO: TStringField
      FieldName = 'DHRECBTO'
      Size = 50
    end
    object xmlNPROT: TStringField
      FieldName = 'NPROT'
      Size = 50
    end
    object xmlIDE_MOD: TStringField
      FieldName = 'IDE_MOD'
      Size = 5
    end
    object xmlIDE_NATOP: TStringField
      FieldName = 'IDE_NATOP'
      Size = 99
    end
    object xmlIDE_NNF: TStringField
      FieldName = 'IDE_NNF'
    end
    object xmlIDE_SERIE: TStringField
      FieldName = 'IDE_SERIE'
    end
    object xmlIDE_TPIMP: TStringField
      FieldName = 'IDE_TPIMP'
    end
    object xmlIDE_TPEMIS: TStringField
      FieldName = 'IDE_TPEMIS'
    end
    object xmlIDE_CDV: TStringField
      FieldName = 'IDE_CDV'
    end
    object xmlIDE_TPAMB: TStringField
      FieldName = 'IDE_TPAMB'
    end
    object xmlIDE_DEMI: TStringField
      FieldName = 'IDE_DEMI'
    end
    object xmlIDE_DSAIENT: TStringField
      FieldName = 'IDE_DSAIENT'
    end
    object xmlIDE_HSAIENT: TStringField
      FieldName = 'IDE_HSAIENT'
    end
    object xmlEMIT_XNOME: TStringField
      FieldName = 'EMIT_XNOME'
      Size = 99
    end
    object xmlEMIT_CNPJ: TStringField
      FieldName = 'EMIT_CNPJ'
      Size = 30
    end
    object xmlEMIT_IE: TStringField
      FieldName = 'EMIT_IE'
      Size = 30
    end
    object xmlEMIT_CRT: TStringField
      FieldName = 'EMIT_CRT'
      Size = 5
    end
    object xmlENDEREMIT_XLGR: TStringField
      FieldName = 'ENDEREMIT_XLGR'
      Size = 99
    end
    object xmlENDEREMIT_NRO: TStringField
      FieldName = 'ENDEREMIT_NRO'
    end
    object xmlENDEREMIT_XBAIRRO: TStringField
      FieldName = 'ENDEREMIT_XBAIRRO'
      Size = 99
    end
    object xmlENDEREMIT_XMUN: TStringField
      FieldName = 'ENDEREMIT_XMUN'
      Size = 99
    end
    object xmlENDEREMIT_CEP: TStringField
      FieldName = 'ENDEREMIT_CEP'
    end
    object xmlENDEREMIT_FONE: TStringField
      FieldName = 'ENDEREMIT_FONE'
    end
    object xmlENDEREMIT_UF: TStringField
      FieldName = 'ENDEREMIT_UF'
      Size = 5
    end
    object xmlDEST_XNOME: TStringField
      FieldName = 'DEST_XNOME'
      Size = 99
    end
    object xmlDEST_CNPJ: TStringField
      FieldName = 'DEST_CNPJ'
      Size = 30
    end
    object xmlDEST_CPF: TStringField
      FieldName = 'DEST_CPF'
      Size = 30
    end
    object xmlDEST_IE: TStringField
      FieldName = 'DEST_IE'
      Size = 30
    end
    object xmlDEST_EMAIL: TStringField
      FieldName = 'DEST_EMAIL'
      Size = 99
    end
    object xmlENDERDEST_XLGR: TStringField
      FieldName = 'ENDERDEST_XLGR'
      Size = 99
    end
    object xmlENDERDEST_NRO: TStringField
      FieldName = 'ENDERDEST_NRO'
    end
    object xmlENDERDEST_XBAIRRO: TStringField
      FieldName = 'ENDERDEST_XBAIRRO'
      Size = 99
    end
    object xmlENDERDEST_XMUN: TStringField
      FieldName = 'ENDERDEST_XMUN'
      Size = 99
    end
    object xmlENDERDEST_CEP: TStringField
      FieldName = 'ENDERDEST_CEP'
    end
    object xmlENDERDEST_FONE: TStringField
      FieldName = 'ENDERDEST_FONE'
      Size = 30
    end
    object xmlENDERDEST_UF: TStringField
      FieldName = 'ENDERDEST_UF'
      Size = 5
    end
    object xmlXML: TStringField
      FieldName = 'XML'
      Size = 10000
    end
    object xmlSTMANIFESTO: TIntegerField
      FieldName = 'STMANIFESTO'
    end
    object xmlSQLDataSet1: TDataSetField
      FieldName = 'SQLDataSet1'
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
    Left = 776
    Top = 128
  end
  object PopupMenu2: TPopupMenu
    Left = 768
    Top = 24
    object MenuItem1: TMenuItem
      Caption = 'Detalhar'
    end
    object ConfirmarOperao1: TMenuItem
      Caption = 'Confirmar Opera'#231#227'o'
    end
    object CidenciadaOperao1: TMenuItem
      Caption = 'Ciencia da Opera'#231#227'o'
    end
    object DesconhecimentoOperao1: TMenuItem
      Caption = 'Desconhecimento Opera'#231#227'o'
    end
    object OperaoNoRealizada1: TMenuItem
      Caption = 'Opera'#231#227'o N'#227'o Realizada'
    end
    object MenuItem2: TMenuItem
      Caption = 'Imprimir'
    end
    object GerarPDF1: TMenuItem
      Caption = 'Gerar PDF'
    end
    object Deletar1: TMenuItem
      Caption = 'Deletar'
    end
    object EnviarEmail1: TMenuItem
      Caption = 'Enviar Email'
    end
    object CartadeCorreo1: TMenuItem
      Caption = 'Carta de Corre'#231#227'o'
    end
    object Cancelar1: TMenuItem
      Caption = 'Cancelar'
    end
    object EnviarDPEC1: TMenuItem
      Caption = 'Enviar DPEC'
    end
    object ExportarTXT1: TMenuItem
      Caption = 'Exportar TXT'
    end
  end
  object DataSource2: TDataSource
    DataSet = SQLTable3
    Left = 496
    Top = 344
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
    Left = 840
    Top = 232
  end
  object DtTitular: TDataSetProvider
    DataSet = SQLTable2
    Options = []
    Left = 296
    Top = 400
  end
  object SQLDataSet1: TSQLDataSet
    SchemaName = 'sysdba'
    CommandText = 
      'insert into detalhes (CNPJ_CPF, DATAEMISSAO, NRCHADOC, RAZAO, ST' +
      'ATUS, TIPO, XML,xMOTIVO,PROTOCOLO,DATAENVIO,NRNOTA)values (:CNPJ' +
      '_CPF, :DATAEMISSAO,:NRCHADOC,:RAZAO, :STATUS, :TIPO, :XML,:xMOTI' +
      'VO,:PROTOCOLO,:DATAENVIO,:NRNOTA)'
    DbxCommandType = 'Dbx.SQL'
    MaxBlobSize = -1
    Params = <
      item
        DataType = ftUnknown
        Name = 'CNPJ_CPF'
        ParamType = ptInput
      end
      item
        DataType = ftUnknown
        Name = 'DATAEMISSAO'
        ParamType = ptInput
      end
      item
        DataType = ftUnknown
        Name = 'NRCHADOC'
        ParamType = ptInput
      end
      item
        DataType = ftUnknown
        Name = 'RAZAO'
        ParamType = ptInput
      end
      item
        DataType = ftUnknown
        Name = 'STATUS'
        ParamType = ptInput
      end
      item
        DataType = ftUnknown
        Name = 'TIPO'
        ParamType = ptInput
      end
      item
        DataType = ftUnknown
        Name = 'XML'
        ParamType = ptInput
      end
      item
        DataType = ftUnknown
        Name = 'xMOTIVO'
        ParamType = ptInput
      end
      item
        DataType = ftUnknown
        Name = 'PROTOCOLO'
        ParamType = ptInput
      end
      item
        DataType = ftUnknown
        Name = 'DATAENVIO'
        ParamType = ptInput
      end
      item
        DataType = ftUnknown
        Name = 'NRNOTA'
        ParamType = ptInput
      end>
    SQLConnection = SQLConnection1
    Left = 304
    Top = 176
  end
  object SQLTable1: TSQLTable
    MaxBlobSize = -1
    SQLConnection = SQLConnection1
    TableName = 'NOTAS'
    Left = 584
    Top = 280
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
  object Timer1: TTimer
    OnTimer = Timer1Timer
    Left = 592
    Top = 576
  end
  object DataSetProvider2: TDataSetProvider
    Left = 320
    Top = 344
  end
  object PopupMenu1: TPopupMenu
    Left = 552
    Top = 584
    object Detalhar1: TMenuItem
      Caption = 'Detalhar'
    end
    object Imprimir1: TMenuItem
      Caption = 'Imprimir'
    end
  end
  object SqlDSTitula: TSQLDataSet
    SchemaName = 'sysdba'
    CommandText = 'select  *  from titulares'
    DbxCommandType = 'Dbx.SQL'
    MaxBlobSize = -1
    Params = <>
    SQLConnection = SQLConnection1
    Left = 368
    Top = 400
    object SqlDSTitulaCDTITULA: TIntegerField
      FieldName = 'CDTITULA'
      Required = True
    end
    object SqlDSTitulaTPTITULA: TStringField
      FieldName = 'TPTITULA'
      Size = 1
    end
    object SqlDSTitulaRAZAOSOCIAL: TStringField
      FieldName = 'RAZAOSOCIAL'
      Size = 200
    end
    object SqlDSTitulaCNPJ: TStringField
      FieldName = 'CNPJ'
      Size = 16
    end
    object SqlDSTitulaCODCIDADE: TIntegerField
      FieldName = 'CODCIDADE'
    end
    object SqlDSTitulaIM: TStringField
      FieldName = 'IM'
    end
    object SqlDSTitulaIE: TStringField
      FieldName = 'IE'
    end
    object SqlDSTitulaNOMEFANTASIA: TStringField
      FieldName = 'NOMEFANTASIA'
      Size = 1
    end
    object SqlDSTitulaSTATUS: TStringField
      FieldName = 'STATUS'
      Size = 1
    end
    object SqlDSTitulaCLIENTE: TStringField
      FieldName = 'CLIENTE'
      Size = 1
    end
    object SqlDSTitulaFORNECEDOR: TStringField
      FieldName = 'FORNECEDOR'
      Size = 1
    end
    object SqlDSTitulaCONTADOR: TStringField
      FieldName = 'CONTADOR'
      Size = 1
    end
    object SqlDSTitulaTRANSPORTADOR: TStringField
      FieldName = 'TRANSPORTADOR'
      Size = 1
    end
  end
  object DSTitula: TDataSource
    DataSet = ClientDataSet1
    Left = 448
    Top = 400
  end
  object DtProduto: TDataSetProvider
    DataSet = SqlDSProduto
    Options = [poFetchBlobsOnDemand, poAllowCommandText, poUseQuoteChar]
    Left = 288
    Top = 456
  end
  object SqlDSProduto: TSQLDataSet
    SchemaName = 'sysdba'
    CommandText = 'select *  from Produtos'
    DbxCommandType = 'Dbx.SQL'
    MaxBlobSize = -1
    Params = <>
    SQLConnection = SQLConnection1
    Left = 360
    Top = 456
  end
  object DSProduto: TDataSource
    DataSet = SqlDSProduto
    Left = 432
    Top = 456
  end
  object DSUniMed: TDataSource
    DataSet = SqlDSUniMed
    Left = 432
    Top = 512
  end
  object SqlDSUniMed: TSQLDataSet
    SchemaName = 'sysdba'
    CommandText = 'select *  from UNIDADEMEDIDA'
    DbxCommandType = 'Dbx.SQL'
    MaxBlobSize = -1
    Params = <>
    SQLConnection = SQLConnection1
    Left = 360
    Top = 512
  end
  object DTUniMed: TDataSetProvider
    DataSet = SqlDSUniMed
    Options = [poFetchBlobsOnDemand, poAllowCommandText, poUseQuoteChar]
    Left = 280
    Top = 512
  end
  object SQLTable2: TSQLTable
    MaxBlobSize = -1
    SQLConnection = SQLConnection1
    TableName = 'TITULARES'
    Left = 512
    Top = 440
    object SQLTable2CDTITULA: TIntegerField
      FieldName = 'CDTITULA'
      Required = True
    end
    object SQLTable2TPTITULA: TStringField
      FieldName = 'TPTITULA'
      Size = 1
    end
    object SQLTable2RAZAOSOCIAL: TStringField
      FieldName = 'RAZAOSOCIAL'
      Size = 200
    end
    object SQLTable2CNPJ: TStringField
      FieldName = 'CNPJ'
      Size = 16
    end
    object SQLTable2CODCIDADE: TIntegerField
      FieldName = 'CODCIDADE'
    end
    object SQLTable2IM: TStringField
      FieldName = 'IM'
    end
    object SQLTable2IE: TStringField
      FieldName = 'IE'
    end
    object SQLTable2NOMEFANTASIA: TStringField
      FieldName = 'NOMEFANTASIA'
      Size = 1
    end
    object SQLTable2STATUS: TStringField
      FieldName = 'STATUS'
      Size = 1
    end
    object SQLTable2CLIENTE: TStringField
      FieldName = 'CLIENTE'
      Size = 1
    end
    object SQLTable2FORNECEDOR: TStringField
      FieldName = 'FORNECEDOR'
      Size = 1
    end
    object SQLTable2CONTADOR: TStringField
      FieldName = 'CONTADOR'
      Size = 1
    end
    object SQLTable2TRANSPORTADOR: TStringField
      FieldName = 'TRANSPORTADOR'
      Size = 1
    end
  end
  object FBCONNECTION: TSQLConnection
    ConnectionName = 'FBCONNECTION'
    DriverName = 'Firebird'
    GetDriverFunc = 'getSQLDriverINTERBASE'
    LibraryName = 'dbxfb.dll'
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
    Left = 1605
    Top = 1605
  end
  object PRODUTOS: TSQLDataSet
    CommandText = 'PRODUTOS'
    CommandType = ctTable
    DbxCommandType = 'Dbx.Table'
    MaxBlobSize = -1
    Params = <>
    SQLConnection = FBCONNECTION
    Left = 1616
    Top = 1616
  end
  object ClientDataSet1: TClientDataSet
    Active = True
    Aggregates = <>
    Params = <>
    ProviderName = 'DtTitular'
    Left = 584
    Top = 32
  end
  object ClientDataSet2: TClientDataSet
    Active = True
    Aggregates = <>
    Params = <>
    ProviderName = 'DtTitular'
    Left = 456
    Top = 32
  end
  object DataSetProvider1: TDataSetProvider
    DataSet = SQLTable1
    Left = 224
    Top = 600
  end
  object ClientDataSet3: TClientDataSet
    Aggregates = <>
    Params = <>
    ProviderName = 'DataSetProvider1'
    Left = 416
    Top = 200
    object StringField1: TStringField
      FieldName = 'XMOTIVO'
      Size = 99
    end
    object StringField2: TStringField
      FieldName = 'CHNFE'
      Required = True
      Size = 50
    end
    object StringField3: TStringField
      FieldName = 'DHRECBTO'
      Size = 50
    end
    object StringField4: TStringField
      FieldName = 'NPROT'
      Size = 50
    end
    object StringField5: TStringField
      FieldName = 'IDE_MOD'
      Size = 5
    end
    object StringField6: TStringField
      FieldName = 'IDE_NATOP'
      Size = 99
    end
    object StringField7: TStringField
      FieldName = 'IDE_NNF'
    end
    object StringField8: TStringField
      FieldName = 'IDE_SERIE'
    end
    object StringField9: TStringField
      FieldName = 'IDE_TPIMP'
    end
    object StringField10: TStringField
      FieldName = 'IDE_TPEMIS'
    end
    object StringField11: TStringField
      FieldName = 'IDE_CDV'
    end
    object StringField12: TStringField
      FieldName = 'IDE_TPAMB'
    end
    object StringField13: TStringField
      FieldName = 'IDE_DEMI'
    end
    object StringField14: TStringField
      FieldName = 'IDE_DSAIENT'
    end
    object StringField15: TStringField
      FieldName = 'IDE_HSAIENT'
    end
    object StringField16: TStringField
      FieldName = 'EMIT_XNOME'
      Size = 99
    end
    object StringField17: TStringField
      FieldName = 'EMIT_CNPJ'
      Size = 30
    end
    object StringField18: TStringField
      FieldName = 'EMIT_IE'
      Size = 30
    end
    object StringField19: TStringField
      FieldName = 'EMIT_CRT'
      Size = 5
    end
    object StringField20: TStringField
      FieldName = 'ENDEREMIT_XLGR'
      Size = 99
    end
    object StringField21: TStringField
      FieldName = 'ENDEREMIT_NRO'
    end
    object StringField22: TStringField
      FieldName = 'ENDEREMIT_XBAIRRO'
      Size = 99
    end
    object StringField23: TStringField
      FieldName = 'ENDEREMIT_XMUN'
      Size = 99
    end
    object StringField24: TStringField
      FieldName = 'ENDEREMIT_CEP'
    end
    object StringField25: TStringField
      FieldName = 'ENDEREMIT_FONE'
    end
    object StringField26: TStringField
      FieldName = 'ENDEREMIT_UF'
      Size = 5
    end
    object StringField27: TStringField
      FieldName = 'DEST_XNOME'
      Size = 99
    end
    object StringField28: TStringField
      FieldName = 'DEST_CNPJ'
      Size = 30
    end
    object StringField29: TStringField
      FieldName = 'DEST_CPF'
      Size = 30
    end
    object StringField30: TStringField
      FieldName = 'DEST_IE'
      Size = 30
    end
    object StringField31: TStringField
      FieldName = 'DEST_EMAIL'
      Size = 99
    end
    object StringField32: TStringField
      FieldName = 'ENDERDEST_XLGR'
      Size = 99
    end
    object StringField33: TStringField
      FieldName = 'ENDERDEST_NRO'
    end
    object StringField34: TStringField
      FieldName = 'ENDERDEST_XBAIRRO'
      Size = 99
    end
    object StringField35: TStringField
      FieldName = 'ENDERDEST_XMUN'
      Size = 99
    end
    object StringField36: TStringField
      FieldName = 'ENDERDEST_CEP'
    end
    object StringField37: TStringField
      FieldName = 'ENDERDEST_FONE'
      Size = 30
    end
    object StringField38: TStringField
      FieldName = 'ENDERDEST_UF'
      Size = 5
    end
    object StringField39: TStringField
      FieldName = 'XML'
      Size = 10000
    end
    object IntegerField1: TIntegerField
      FieldName = 'STMANIFESTO'
    end
    object DataSetField1: TDataSetField
      FieldName = 'SQLDataSet1'
    end
  end
  object SQLTable3: TSQLTable
    Active = True
    MaxBlobSize = 1
    SQLConnection = SQLConnection1
    TableName = 'DETALHES'
    Left = 640
    Top = 360
    object SQLTable3NRCHADOC: TStringField
      FieldName = 'NRCHADOC'
      Required = True
      Size = 50
    end
    object SQLTable3RAZAO: TStringField
      FieldName = 'RAZAO'
      Size = 200
    end
    object SQLTable3CNPJ_CPF: TStringField
      FieldName = 'CNPJ_CPF'
    end
    object SQLTable3DATAEMISSAO: TDateField
      FieldName = 'DATAEMISSAO'
    end
    object SQLTable3STATUS: TStringField
      FieldName = 'STATUS'
      Size = 1
    end
    object SQLTable3TIPO: TStringField
      FieldName = 'TIPO'
      Size = 1
    end
    object SQLTable3XMOTIVO: TStringField
      FieldName = 'XMOTIVO'
      Size = 255
    end
    object SQLTable3PROTOCOLO: TStringField
      FieldName = 'PROTOCOLO'
      Size = 255
    end
    object SQLTable3DATAENVIO: TDateField
      FieldName = 'DATAENVIO'
    end
    object SQLTable3NRNOTA: TStringField
      FieldName = 'NRNOTA'
      Size = 10
    end
    object SQLTable3XML: TStringField
      FieldName = 'XML'
      Size = 12000
    end
  end
  object SQLDataSet2: TSQLDataSet
    SchemaName = 'sysdba'
    CommandText = 
      'select CNPJ_CPF, DATAEMISSAO, DATAENVIO, NRCHADOC, NRNOTA, PROTO' +
      'COLO, RAZAO, STATUS, TIPO, XMOTIVO  from DETALHES'
    DbxCommandType = 'Dbx.SQL'
    MaxBlobSize = -1
    Params = <>
    SQLConnection = SQLConnection1
    Left = 240
    Top = 272
  end
end
