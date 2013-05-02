object Form1: TForm1
  Left = 269
  Top = 149
  Caption = 'ACBrNFe - Demonstra'#231#227'o'
  ClientHeight = 680
  ClientWidth = 899
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
    Width = 899
    Height = 680
    ActivePage = TabSheet15
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
        Width = 891
        Height = 36
        Align = alBottom
        TabOrder = 1
        DesignSize = (
          891
          36)
        object BtnExcel: TBrvBitBtn
          Left = 755
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
        Width = 891
        Height = 41
        Align = alTop
        TabOrder = 2
      end
      object Panel10: TPanel
        Left = 185
        Top = 41
        Width = 706
        Height = 575
        Align = alClient
        TabOrder = 3
        object BrvDbGrid2: TBrvDbGrid
          Left = 1
          Top = 1
          Width = 704
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
        Width = 722
        Height = 652
        Align = alClient
        TabOrder = 0
        object Panel3: TPanel
          Left = 1
          Top = 1
          Width = 720
          Height = 650
          Align = alClient
          TabOrder = 0
          object PageControl3: TPageControl
            Left = 1
            Top = 1
            Width = 718
            Height = 648
            ActivePage = TabSheet12
            Align = alClient
            TabOrder = 0
            object TabSheet12: TTabSheet
              Caption = 'Busca Manifesto'
              ImageIndex = 1
              object Panel4: TPanel
                Left = 0
                Top = 0
                Width = 710
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
                Width = 710
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
                Width = 710
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
                Width = 710
                Height = 620
                ActivePage = TabSheet11
                Align = alClient
                TabOrder = 0
                object TabSheet5: TTabSheet
                  Caption = 'Respostas'
                  object MemoResp: TMemo
                    Left = 0
                    Top = 0
                    Width = 702
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
                    Width = 702
                    Height = 592
                    Align = alClient
                    TabOrder = 0
                    ExplicitHeight = 552
                    ControlData = {
                      4C0000008E4800002F3D00000000000000000000000000000000000000000000
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
                    Width = 702
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
                    Width = 702
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
                    Width = 702
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
                    Width = 702
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
                    Width = 702
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
          OnClick = btnConsultarReciboClick
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
          OnClick = btnManifDestConfirmacaoClick
        end
        object btnEnviarEmail: TButton
          Left = 9
          Top = 219
          Width = 151
          Height = 25
          Caption = 'Enviar NFe Email'
          TabOrder = 6
          OnClick = btnEnviarEmailClick
        end
        object btnCartadeCorrecao: TButton
          Left = 9
          Top = 19
          Width = 151
          Height = 25
          Caption = 'Carta de Corre'#231#227'o'
          TabOrder = 7
          OnClick = btnCartadeCorrecaoClick
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
        Width = 722
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
            Width = 714
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
            Width = 714
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
            Width = 714
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
            Width = 714
            Height = 624
            ActivePage = TabSheet24
            Align = alClient
            TabOrder = 0
            object TabSheet17: TTabSheet
              Caption = 'Respostas'
              object Memo2: TMemo
                Left = 0
                Top = 0
                Width = 706
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
                Width = 706
                Height = 596
                Align = alClient
                TabOrder = 0
                ExplicitWidth = 682
                ExplicitHeight = 550
                ControlData = {
                  4C000000F8480000993D00000000000000000000000000000000000000000000
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
                Width = 706
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
                Width = 706
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
                Width = 706
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
                Width = 706
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
                Width = 706
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
        Width = 891
        Height = 652
        ActivePage = TabSheet27
        Align = alClient
        TabOrder = 0
        object TabSheet27: TTabSheet
          Caption = 'Configura'#231#245'es'
          OnEnter = tsNFeEnter
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
            Top = 416
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
            Top = 457
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
            Top = 441
            Width = 120
            Height = 13
            Caption = 'Diretorio para Exporta'#231#227'o'
          end
          object Label79: TLabel
            Left = 3
            Top = 400
            Width = 119
            Height = 13
            Caption = 'Diretorio para Importa'#231#227'o'
          end
          object Label80: TLabel
            Left = 390
            Top = 442
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
              OnClick = sbtnGetCertClick
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
            Top = 272
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
            Top = 294
            Width = 206
            Height = 105
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
            Left = 413
            Top = 0
            Width = 273
            Height = 273
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
              Left = 9
              Top = 225
              Width = 61
              Height = 13
              Caption = 'C'#243'd. Cidade '
            end
            object Label101: TLabel
              Left = 76
              Top = 225
              Width = 33
              Height = 13
              Caption = 'Cidade'
            end
            object Label102: TLabel
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
          object GroupBox23: TGroupBox
            Left = 215
            Top = 0
            Width = 194
            Height = 273
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
            Top = 399
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
            Top = 416
            Width = 315
            Height = 21
            TabOrder = 7
          end
          object Edit3: TEdit
            Left = 3
            Top = 456
            Width = 315
            Height = 21
            TabOrder = 8
          end
          object BitBtn2: TBitBtn
            Left = 479
            Top = 455
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
            Top = 405
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
            Top = 424
            Width = 96
            Height = 21
            TabOrder = 10
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
        Width = 891
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
        Width = 891
        Height = 303
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
        Width = 891
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
        Width = 891
        Height = 67
        Align = alBottom
        TabOrder = 2
      end
      object Panel18: TPanel
        Left = 0
        Top = 611
        Width = 891
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
          887
          37)
        object LblQtReg: TLabel
          Left = 8
          Top = 8
          Width = 54
          Height = 13
          Caption = 'LblQtReg'
        end
        object BtnEnviar: TBrvBitBtn
          Left = 761
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
        Top = 408
        Width = 891
        Height = 46
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
          887
          42)
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
          Left = 516
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
          Width = 234
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
          Left = 620
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
          Left = 752
          Top = -2
          Width = 121
          Height = 34
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
        Width = 706
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
        Width = 722
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
            Width = 714
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
            Width = 714
            Height = 559
            ActivePage = TabSheet37
            Align = alClient
            TabOrder = 1
            object TabSheet31: TTabSheet
              Caption = 'Respostas'
              object Memo1: TMemo
                Left = 0
                Top = 0
                Width = 706
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
                Width = 706
                Height = 531
                Align = alClient
                TabOrder = 0
                ExplicitHeight = 596
                ControlData = {
                  4C000000F8480000E13600000000000000000000000000000000000000000000
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
                Width = 706
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
                Width = 706
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
                Width = 706
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
                Width = 706
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
                Width = 706
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
    end
    object TabSheet25: TTabSheet
      Caption = 'Gerar NFSe'
      ImageIndex = 8
    end
    object TabSheet28: TTabSheet
      Caption = 'Gerar Conhecimento'
      ImageIndex = 9
    end
    object TabSheet30: TTabSheet
      Caption = 'Configura'#231#227'o Boletos'
      ImageIndex = 10
    end
  end
  object OpenDialog1: TOpenDialog
    DefaultExt = '*-nfe.XML'
    Filter = 
      'Arquivos NFE (*-nfe.XML)|*-nfe.XML|Arquivos XML (*.XML)|*.XML|To' +
      'dos os Arquivos (*.*)|*.*'
    Title = 'Selecione a NFe'
    Left = 558
    Top = 430
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
    Left = 607
    Top = 400
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
    Left = 702
    Top = 368
  end
  object BrvXMLNFE: TBrvXML
    BrGerarBanco = True
    BrQtdePagDanfe = 0
    Left = 656
    Top = 368
  end
  object DataSource1: TDataSource
    Left = 320
    Top = 376
  end
  object xml: TClientDataSet
    Aggregates = <>
    Params = <>
    ProviderName = 'DataSetProvider1'
    Left = 416
    Top = 376
  end
  object PopupMenu1: TPopupMenu
    Left = 656
    Top = 432
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
    Left = 608
    Top = 368
  end
  object PopupMenu2: TPopupMenu
    Left = 584
    Top = 352
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
    DataSet = SQLTable1
    Left = 368
    Top = 376
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
    Left = 272
    Top = 376
  end
  object DataSetProvider1: TDataSetProvider
    DataSet = SQLDataSet1
    Options = [poFetchBlobsOnDemand, poAllowCommandText, poUseQuoteChar]
    Left = 360
    Top = 232
  end
  object SQLDataSet1: TSQLDataSet
    SchemaName = 'sysdba'
    CommandText = 'select *  from NOTAS'
    DbxCommandType = 'Dbx.SQL'
    DataSource = DataSource2
    MaxBlobSize = -1
    Params = <>
    SQLConnection = SQLConnection1
    Left = 440
    Top = 168
    object SQLDataSet1XMOTIVO: TStringField
      FieldName = 'XMOTIVO'
      Size = 99
    end
    object SQLDataSet1CHNFE: TStringField
      FieldName = 'CHNFE'
      Required = True
      Size = 50
    end
    object SQLDataSet1DHRECBTO: TStringField
      FieldName = 'DHRECBTO'
      Size = 50
    end
    object SQLDataSet1NPROT: TStringField
      FieldName = 'NPROT'
      Size = 50
    end
    object SQLDataSet1IDE_MOD: TStringField
      FieldName = 'IDE_MOD'
      Size = 5
    end
    object SQLDataSet1IDE_NATOP: TStringField
      FieldName = 'IDE_NATOP'
      Size = 99
    end
    object SQLDataSet1IDE_NNF: TStringField
      FieldName = 'IDE_NNF'
    end
    object SQLDataSet1IDE_SERIE: TStringField
      FieldName = 'IDE_SERIE'
    end
    object SQLDataSet1IDE_TPIMP: TStringField
      FieldName = 'IDE_TPIMP'
    end
    object SQLDataSet1IDE_TPEMIS: TStringField
      FieldName = 'IDE_TPEMIS'
    end
    object SQLDataSet1IDE_CDV: TStringField
      FieldName = 'IDE_CDV'
    end
    object SQLDataSet1IDE_TPAMB: TStringField
      FieldName = 'IDE_TPAMB'
    end
    object SQLDataSet1IDE_DEMI: TStringField
      FieldName = 'IDE_DEMI'
    end
    object SQLDataSet1IDE_DSAIENT: TStringField
      FieldName = 'IDE_DSAIENT'
    end
    object SQLDataSet1IDE_HSAIENT: TStringField
      FieldName = 'IDE_HSAIENT'
    end
    object SQLDataSet1EMIT_XNOME: TStringField
      FieldName = 'EMIT_XNOME'
      Size = 99
    end
    object SQLDataSet1EMIT_CNPJ: TStringField
      FieldName = 'EMIT_CNPJ'
      Size = 30
    end
    object SQLDataSet1EMIT_IE: TStringField
      FieldName = 'EMIT_IE'
      Size = 30
    end
    object SQLDataSet1EMIT_CRT: TStringField
      FieldName = 'EMIT_CRT'
      Size = 5
    end
    object SQLDataSet1ENDEREMIT_XLGR: TStringField
      FieldName = 'ENDEREMIT_XLGR'
      Size = 99
    end
    object SQLDataSet1ENDEREMIT_NRO: TStringField
      FieldName = 'ENDEREMIT_NRO'
    end
    object SQLDataSet1ENDEREMIT_XBAIRRO: TStringField
      FieldName = 'ENDEREMIT_XBAIRRO'
      Size = 99
    end
    object SQLDataSet1ENDEREMIT_XMUN: TStringField
      FieldName = 'ENDEREMIT_XMUN'
      Size = 99
    end
    object SQLDataSet1ENDEREMIT_CEP: TStringField
      FieldName = 'ENDEREMIT_CEP'
    end
    object SQLDataSet1ENDEREMIT_FONE: TStringField
      FieldName = 'ENDEREMIT_FONE'
    end
    object SQLDataSet1ENDEREMIT_UF: TStringField
      FieldName = 'ENDEREMIT_UF'
      Size = 5
    end
    object SQLDataSet1DEST_XNOME: TStringField
      FieldName = 'DEST_XNOME'
      Size = 99
    end
    object SQLDataSet1DEST_CNPJ: TStringField
      FieldName = 'DEST_CNPJ'
      Size = 30
    end
    object SQLDataSet1DEST_CPF: TStringField
      FieldName = 'DEST_CPF'
      Size = 30
    end
    object SQLDataSet1DEST_IE: TStringField
      FieldName = 'DEST_IE'
      Size = 30
    end
    object SQLDataSet1DEST_EMAIL: TStringField
      FieldName = 'DEST_EMAIL'
      Size = 99
    end
    object SQLDataSet1ENDERDEST_XLGR: TStringField
      FieldName = 'ENDERDEST_XLGR'
      Size = 99
    end
    object SQLDataSet1ENDERDEST_NRO: TStringField
      FieldName = 'ENDERDEST_NRO'
    end
    object SQLDataSet1ENDERDEST_XBAIRRO: TStringField
      FieldName = 'ENDERDEST_XBAIRRO'
      Size = 99
    end
    object SQLDataSet1ENDERDEST_XMUN: TStringField
      FieldName = 'ENDERDEST_XMUN'
      Size = 99
    end
    object SQLDataSet1ENDERDEST_CEP: TStringField
      FieldName = 'ENDERDEST_CEP'
    end
    object SQLDataSet1ENDERDEST_FONE: TStringField
      FieldName = 'ENDERDEST_FONE'
      Size = 30
    end
    object SQLDataSet1ENDERDEST_UF: TStringField
      FieldName = 'ENDERDEST_UF'
      Size = 5
    end
    object SQLDataSet1XML: TStringField
      FieldName = 'XML'
      Size = 10000
    end
    object SQLDataSet1STMANIFESTO: TIntegerField
      FieldName = 'STMANIFESTO'
    end
  end
  object SQLTable1: TSQLTable
    Active = True
    MaxBlobSize = -1
    SQLConnection = SQLConnection1
    TableName = 'NOTAS'
    Left = 280
    Top = 168
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
    Left = 728
    Top = 432
  end
  object DataSetProvider2: TDataSetProvider
    Left = 360
    Top = 168
  end
end
