object Form10: TForm10
  Left = 173
  Top = 183
  Width = 988
  Height = 716
  Caption = 'Form10'
  Color = clBtnFace
  Font.Charset = DEFAULT_CHARSET
  Font.Color = clWindowText
  Font.Height = -11
  Font.Name = 'MS Sans Serif'
  Font.Style = []
  OldCreateOrder = False
  OnActivate = FormActivate
  PixelsPerInch = 96
  TextHeight = 13
  object Panel1: TPanel
    Left = 0
    Top = 0
    Width = 980
    Height = 57
    Align = alTop
    TabOrder = 0
    object Label2: TLabel
      Left = 392
      Top = 20
      Width = 32
      Height = 13
      Caption = 'Label2'
    end
    object ActResultEdit1: TActResultEdit
      Left = 175
      Top = 16
      Width = 209
      Height = 21
      Alignment = taLeftJustify
      ColorOnFocus = 16311512
      ColorOnNotFocus = clWindow
      EditLabel.Width = 125
      EditLabel.Height = 13
      EditLabel.Caption = 'FORNECEDOR/CLIENTE'
      TabOrder = 0
      OnChange = ActResultEdit1Change
      Value = 0
      Glyph.Data = {
        9A020000424D9A0200000000000036000000280000000C000000110000000100
        1800000000006402000000000000000000000000000000000000FFFFFFFFFFFF
        FFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFF
        FFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFF
        FFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFF
        FFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFF
        FFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFF
        FFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFF000000
        000000000000FFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFF0000
        00000000FFFFFF000000000000FFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFF00
        0000000000FFFFFFFFFFFFFFFFFF000000000000FFFFFFFFFFFFFFFFFFFFFFFF
        000000000000FFFFFFFFFFFFFFFFFFFFFFFFFFFFFF000000000000FFFFFFFFFF
        FFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFF
        FFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFF
        FFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFF000000000000000000FFFF
        FFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFF000000000000FFFFFF00
        0000000000FFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFF000000000000FFFFFF
        FFFFFFFFFFFF000000000000FFFFFFFFFFFFFFFFFFFFFFFF000000000000FFFF
        FFFFFFFFFFFFFFFFFFFFFFFFFF000000000000FFFFFFFFFFFFFFFFFFFFFFFFFF
        FFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFF
        FFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFF}
      NumGlyphs = 1
      OnButtonClick = ActResultEdit1ButtonClick
    end
  end
  object Panel2: TPanel
    Left = 836
    Top = 57
    Width = 144
    Height = 625
    Align = alRight
    TabOrder = 1
    object SpeedButton1: TSpeedButton
      Left = 24
      Top = 64
      Width = 97
      Height = 22
      Caption = 'Anterior <'
      OnClick = SpeedButton1Click
    end
    object SpeedButton2: TSpeedButton
      Left = 24
      Top = 88
      Width = 97
      Height = 22
      Caption = 'Ultimo'
      OnClick = SpeedButton2Click
    end
    object SpeedButton3: TSpeedButton
      Left = 24
      Top = 112
      Width = 97
      Height = 22
      Caption = 'Inserir'
      OnClick = SpeedButton3Click
    end
    object SpeedButton4: TSpeedButton
      Left = 24
      Top = 136
      Width = 97
      Height = 22
      Caption = 'Alterar'
      OnClick = SpeedButton4Click
    end
    object SpeedButton5: TSpeedButton
      Left = 24
      Top = 160
      Width = 97
      Height = 22
      Caption = 'Deletar'
      OnClick = SpeedButton5Click
    end
    object SpeedButton6: TSpeedButton
      Left = 24
      Top = 184
      Width = 97
      Height = 22
      Caption = 'Limpar'
      OnClick = SpeedButton6Click
    end
    object SpeedButton7: TSpeedButton
      Left = 24
      Top = 208
      Width = 97
      Height = 22
      Caption = 'Ajuda'
    end
    object SpeedButton8: TSpeedButton
      Left = 24
      Top = 232
      Width = 97
      Height = 22
      Caption = 'Buscar'
    end
    object SpeedButton9: TSpeedButton
      Left = 24
      Top = 256
      Width = 97
      Height = 22
      Caption = 'Sair'
    end
    object SpeedButton10: TSpeedButton
      Left = 24
      Top = 40
      Width = 97
      Height = 22
      Caption = 'Proximo >'
      OnClick = SpeedButton10Click
    end
    object SpeedButton11: TSpeedButton
      Left = 24
      Top = 16
      Width = 97
      Height = 22
      Caption = 'Primeiro'
      OnClick = SpeedButton11Click
    end
  end
  object ScrollBox1: TScrollBox
    Left = 0
    Top = 57
    Width = 836
    Height = 625
    VertScrollBar.Position = 310
    Align = alClient
    Color = clWhite
    ParentColor = False
    TabOrder = 2
    object GroupBox1: TGroupBox
      Left = 16
      Top = -230
      Width = 785
      Height = 41
      TabOrder = 0
      object TXTRAZAO: TActEdit
        Left = 104
        Top = 9
        Width = 281
        Height = 21
        Alignment = taLeftJustify
        ColorOnFocus = 16311512
        ColorOnNotFocus = clWindow
        EditLabel.Width = 78
        EditLabel.Height = 13
        EditLabel.Caption = 'RAZ'#195'O SOCIAL'
        TabOrder = 0
      end
      object TXTFANTASIA: TActEdit
        Left = 456
        Top = 9
        Width = 321
        Height = 21
        Alignment = taLeftJustify
        ColorOnFocus = 16311512
        ColorOnNotFocus = clWindow
        EditLabel.Width = 52
        EditLabel.Height = 13
        EditLabel.Caption = 'FANTASIA'
        TabOrder = 1
      end
    end
    object GroupBox2: TGroupBox
      Left = 16
      Top = -182
      Width = 297
      Height = 348
      Caption = 'TIPO FORNECEDOR/CLIENTE'
      TabOrder = 1
      object Label1: TLabel
        Left = 160
        Top = 272
        Width = 25
        Height = 13
        Caption = 'DIAS'
        Visible = False
      end
      object CBFORNECEDOR: TCheckBox
        Left = 32
        Top = 24
        Width = 97
        Height = 17
        Caption = 'FORNECEDOR'
        TabOrder = 0
      end
      object CDbLIENTE: TCheckBox
        Left = 32
        Top = 56
        Width = 97
        Height = 17
        Caption = 'CLIENTE'
        TabOrder = 1
      end
      object CBFISICA: TCheckBox
        Left = 32
        Top = 88
        Width = 97
        Height = 17
        Caption = 'FISICA'
        TabOrder = 2
        OnClick = CBFISICAClick
      end
      object CBJURIDICA: TCheckBox
        Left = 32
        Top = 120
        Width = 97
        Height = 17
        Caption = 'JURIDICA'
        TabOrder = 3
        OnClick = CBJURIDICAClick
      end
      object CBOUTRA: TCheckBox
        Left = 32
        Top = 152
        Width = 97
        Height = 17
        Caption = 'OUTRAS'
        TabOrder = 4
      end
      object TXTCNPJ: TActMaskEdit
        Left = 96
        Top = 176
        Width = 169
        Height = 21
        Alignment = taLeftJustify
        ColorOnFocus = 16311512
        ColorOnNotFocus = clWindow
        NotNull = True
        EditLabel.Width = 52
        EditLabel.Height = 13
        EditLabel.Caption = 'CPF/CNPJ'
        MaxLength = 18
        TabOrder = 5
        EditMask = '!99.999.999/9999-99;0;_'
      end
      object CBAGROPtRANS: TCheckBox
        Left = 32
        Top = 208
        Width = 97
        Height = 17
        Caption = 'AGROP/TRANSP'
        TabOrder = 6
      end
      object ActButton1: TActButton
        Left = 152
        Top = 312
        Width = 137
        Height = 25
        Caption = 'REGIME TRIBUTARIO'
        TabOrder = 7
        ResourceID = 0
      end
      object CDREPRESENTANTE: TCheckBox
        Left = 32
        Top = 232
        Width = 129
        Height = 17
        Caption = 'REPRESENTANTE'
        TabOrder = 8
        OnClick = CDREPRESENTANTEClick
      end
      object TXTINERVALO: TActEdit
        Left = 104
        Top = 265
        Width = 49
        Height = 21
        Alignment = taLeftJustify
        ColorOnFocus = 16311512
        ColorOnNotFocus = clWindow
        EditLabel.Width = 61
        EditLabel.Height = 13
        EditLabel.Caption = 'INTERVALO'
        TabOrder = 9
        Visible = False
      end
    end
    object GroupBox3: TGroupBox
      Left = 328
      Top = -178
      Width = 473
      Height = 301
      Caption = 'DADOS CONTABEIS'
      TabOrder = 2
      object Label6: TLabel
        Left = 243
        Top = 236
        Width = 32
        Height = 13
        Caption = 'Label6'
      end
      object Label7: TLabel
        Left = 242
        Top = 266
        Width = 32
        Height = 13
        Caption = 'Label7'
      end
      object TXTBANCO: TActEdit
        Left = 152
        Top = 17
        Width = 233
        Height = 21
        Alignment = taLeftJustify
        ColorOnFocus = 16311512
        ColorOnNotFocus = clWindow
        EditLabel.Width = 37
        EditLabel.Height = 13
        EditLabel.Caption = 'BANCO'
        TabOrder = 0
      end
      object TXTCONTA: TActEdit
        Left = 152
        Top = 43
        Width = 233
        Height = 21
        Alignment = taLeftJustify
        ColorOnFocus = 16311512
        ColorOnNotFocus = clWindow
        EditLabel.Width = 37
        EditLabel.Height = 13
        EditLabel.Caption = 'CONTA'
        TabOrder = 1
      end
      object CheckBox7: TCheckBox
        Left = 24
        Top = 112
        Width = 193
        Height = 17
        Caption = 'CONTA IMP NO REG ENTRADA'
        TabOrder = 3
      end
      object CheckBox8: TCheckBox
        Left = 224
        Top = 112
        Width = 193
        Height = 17
        Caption = 'CONTA IMP NO REG SAIDA'
        TabOrder = 4
      end
      object TXTIE: TActMaskEdit
        Left = 152
        Top = 136
        Width = 185
        Height = 21
        Alignment = taLeftJustify
        ColorOnFocus = 16311512
        ColorOnNotFocus = clWindow
        EditLabel.Width = 118
        EditLabel.Height = 13
        EditLabel.Caption = 'INSCRI'#199#195'O ESTADUAL'
        TabOrder = 5
      end
      object TXTSERIE: TActEdit
        Left = 152
        Top = 171
        Width = 41
        Height = 21
        Alignment = taLeftJustify
        ColorOnFocus = 16311512
        ColorOnNotFocus = clWindow
        EditLabel.Width = 49
        EditLabel.Height = 13
        EditLabel.Caption = 'SERIE NF'
        TabOrder = 6
      end
      object TXTMODELONF: TActEdit
        Left = 294
        Top = 171
        Width = 41
        Height = 21
        Alignment = taLeftJustify
        ColorOnFocus = 16311512
        ColorOnNotFocus = clWindow
        EditLabel.Width = 63
        EditLabel.Height = 13
        EditLabel.Caption = 'MODELO NF'
        TabOrder = 7
      end
      object TXTCODIGOMUNICIPAL: TActEdit
        Left = 151
        Top = 204
        Width = 89
        Height = 21
        Alignment = taLeftJustify
        ColorOnFocus = 16311512
        ColorOnNotFocus = clWindow
        EditLabel.Width = 101
        EditLabel.Height = 13
        EditLabel.Caption = 'CODIGO MUNICIPIO'
        TabOrder = 8
      end
      object TXTAGENCIA: TActEdit
        Left = 152
        Top = 73
        Width = 233
        Height = 21
        Alignment = taLeftJustify
        ColorOnFocus = 16311512
        ColorOnNotFocus = clWindow
        EditLabel.Width = 47
        EditLabel.Height = 13
        EditLabel.Caption = 'AGENCIA'
        TabOrder = 2
      end
      object TXTRAMO: TActResultEdit
        Left = 152
        Top = 233
        Width = 87
        Height = 21
        Alignment = taLeftJustify
        ColorOnFocus = 16311512
        ColorOnNotFocus = clWindow
        EditLabel.Width = 92
        EditLabel.Height = 13
        EditLabel.Caption = 'RAMO ATIVIDADE'
        TabOrder = 9
        OnChange = TXTRAMOChange
        Value = 0
        Glyph.Data = {
          46030000424D460300000000000036000000280000000E0000000E0000000100
          2000000000001003000000000000000000000000000000000000808000008080
          0000808000008080000080800000808000008080000080800000808000008080
          0000808000008080000080800000808000008080000080800000808000008080
          0000808000008080000080800000808000008080000080800000808000008080
          0000808000008080000080800000808000008080000080800000808000008080
          0000808000008080000080800000808000008080000080800000808000008080
          0000808000000000000000000000808000008080000000000000000000008080
          0000808000000000000000000000808000008080000080800000808000000000
          0000000000008080000080800000000000000000000080800000808000000000
          0000000000008080000080800000808000008080000080800000808000008080
          0000808000008080000080800000808000008080000080800000808000008080
          0000808000008080000080800000808000008080000080800000808000008080
          0000808000008080000080800000808000008080000080800000808000008080
          0000808000008080000080800000808000008080000080800000808000008080
          0000808000008080000080800000808000008080000080800000808000008080
          0000808000008080000080800000808000008080000080800000808000008080
          0000808000008080000080800000808000008080000080800000808000008080
          0000808000008080000080800000808000008080000080800000808000008080
          0000808000008080000080800000808000008080000080800000808000008080
          0000808000008080000080800000808000008080000080800000808000008080
          0000808000008080000080800000808000008080000080800000808000008080
          0000808000008080000080800000808000008080000080800000808000008080
          0000808000008080000080800000808000008080000080800000808000008080
          0000808000008080000080800000808000008080000080800000808000008080
          0000808000008080000080800000808000008080000080800000808000008080
          00008080000080800000}
        NumGlyphs = 1
        OnButtonClick = TXTRAMOButtonClick
      end
      object TXTIND_FORN: TActResultEdit
        Left = 150
        Top = 260
        Width = 88
        Height = 21
        Alignment = taLeftJustify
        ColorOnFocus = 16311512
        ColorOnNotFocus = clWindow
        EditLabel.Width = 97
        EditLabel.Height = 13
        EditLabel.Caption = 'IND.FORNECEDOR'
        TabOrder = 10
        OnChange = TXTIND_FORNChange
        Value = 0
        Glyph.Data = {
          46030000424D460300000000000036000000280000000E0000000E0000000100
          2000000000001003000000000000000000000000000000000000808000008080
          0000808000008080000080800000808000008080000080800000808000008080
          0000808000008080000080800000808000008080000080800000808000008080
          0000808000008080000080800000808000008080000080800000808000008080
          0000808000008080000080800000808000008080000080800000808000008080
          0000808000008080000080800000808000008080000080800000808000008080
          0000808000000000000000000000808000008080000000000000000000008080
          0000808000000000000000000000808000008080000080800000808000000000
          0000000000008080000080800000000000000000000080800000808000000000
          0000000000008080000080800000808000008080000080800000808000008080
          0000808000008080000080800000808000008080000080800000808000008080
          0000808000008080000080800000808000008080000080800000808000008080
          0000808000008080000080800000808000008080000080800000808000008080
          0000808000008080000080800000808000008080000080800000808000008080
          0000808000008080000080800000808000008080000080800000808000008080
          0000808000008080000080800000808000008080000080800000808000008080
          0000808000008080000080800000808000008080000080800000808000008080
          0000808000008080000080800000808000008080000080800000808000008080
          0000808000008080000080800000808000008080000080800000808000008080
          0000808000008080000080800000808000008080000080800000808000008080
          0000808000008080000080800000808000008080000080800000808000008080
          0000808000008080000080800000808000008080000080800000808000008080
          0000808000008080000080800000808000008080000080800000808000008080
          0000808000008080000080800000808000008080000080800000808000008080
          0000808000008080000080800000808000008080000080800000808000008080
          00008080000080800000}
        NumGlyphs = 1
        OnButtonClick = TXTIND_FORNButtonClick
      end
    end
    object GroupBox4: TGroupBox
      Left = 327
      Top = 123
      Width = 473
      Height = 41
      Caption = 'RETEN'#199#195'O ESTABELECIMENTO RESPONSAVEL'
      TabOrder = 3
      object TXTTIPO: TActEdit
        Left = 88
        Top = 16
        Width = 33
        Height = 21
        Alignment = taLeftJustify
        ColorOnFocus = 16311512
        ColorOnNotFocus = clWindow
        EditLabel.Width = 25
        EditLabel.Height = 13
        EditLabel.Caption = 'TIPO'
        TabOrder = 0
      end
    end
    object GroupBox5: TGroupBox
      Left = 16
      Top = 324
      Width = 785
      Height = 255
      TabOrder = 4
      object TXTIM: TActMaskEdit
        Left = 144
        Top = 20
        Width = 185
        Height = 21
        Alignment = taLeftJustify
        ColorOnFocus = 16311512
        ColorOnNotFocus = clWindow
        EditLabel.Width = 119
        EditLabel.Height = 13
        EditLabel.Caption = 'INSTRI'#199#195'O MUNICIPAL'
        TabOrder = 0
      end
      object TXTSUFRAMA: TActMaskEdit
        Left = 408
        Top = 18
        Width = 124
        Height = 21
        Alignment = taLeftJustify
        ColorOnFocus = 16311512
        ColorOnNotFocus = clWindow
        EditLabel.Width = 52
        EditLabel.Height = 13
        EditLabel.Caption = 'SUFRAMA'
        TabOrder = 1
      end
      object TXTTIPOLOGRADOURO: TActEdit
        Left = 144
        Top = 49
        Width = 105
        Height = 21
        Alignment = taLeftJustify
        ColorOnFocus = 16311512
        ColorOnNotFocus = clWindow
        EditLabel.Width = 105
        EditLabel.Height = 13
        EditLabel.Caption = 'TIPO LOGRADOURO'
        TabOrder = 3
      end
      object TXTLOGRADOURO: TActMaskEdit
        Left = 360
        Top = 49
        Width = 265
        Height = 21
        Alignment = taLeftJustify
        ColorOnFocus = 16311512
        ColorOnNotFocus = clWindow
        EditLabel.Width = 77
        EditLabel.Height = 13
        EditLabel.Caption = 'LOGRADOURO'
        TabOrder = 4
      end
      object TXTBAIRRO: TActEdit
        Left = 144
        Top = 81
        Width = 209
        Height = 21
        Alignment = taLeftJustify
        ColorOnFocus = 16311512
        ColorOnNotFocus = clWindow
        EditLabel.Width = 41
        EditLabel.Height = 13
        EditLabel.Caption = 'BAIRRO'
        TabOrder = 6
      end
      object TXTTELEFONE: TActMaskEdit
        Left = 145
        Top = 118
        Width = 103
        Height = 21
        Alignment = taLeftJustify
        ColorOnFocus = 16311512
        ColorOnNotFocus = clWindow
        EditLabel.Width = 56
        EditLabel.Height = 13
        EditLabel.Caption = 'TELEFONE'
        MaxLength = 13
        TabOrder = 10
        Text = '((()()()-()-()-()-()-()-()-()-()-()-()-)-----    '
        EditMask = '!\(99\)0000-0000;1;_'
      end
      object TXTCONTATO: TActEdit
        Left = 410
        Top = 193
        Width = 143
        Height = 21
        Alignment = taLeftJustify
        ColorOnFocus = 16311512
        ColorOnNotFocus = clWindow
        EditLabel.Width = 52
        EditLabel.Height = 13
        EditLabel.Caption = 'CONTATO'
        TabOrder = 15
      end
      object TXTTELCONTATO: TActMaskEdit
        Left = 648
        Top = 192
        Width = 100
        Height = 21
        Alignment = taLeftJustify
        ColorOnFocus = 16311512
        ColorOnNotFocus = clWindow
        EditLabel.Width = 75
        EditLabel.Height = 13
        EditLabel.Caption = 'TEL CONTATO'
        MaxLength = 14
        TabOrder = 16
        Text = ' ---)-)-)(-)(-)(-)(-)(-)(-)-)-----    '
        EditMask = '!\(999\)0000-0000;1;_'
      end
      object TXTEMAIL: TActEdit
        Left = 144
        Top = 154
        Width = 273
        Height = 21
        Alignment = taLeftJustify
        ColorOnFocus = 16311512
        ColorOnNotFocus = clWindow
        EditLabel.Width = 32
        EditLabel.Height = 13
        EditLabel.Caption = 'EMAIL'
        TabOrder = 12
      end
      object TXTEMAILNFe: TActEdit
        Left = 495
        Top = 153
        Width = 257
        Height = 21
        Alignment = taLeftJustify
        ColorOnFocus = 16311512
        ColorOnNotFocus = clWindow
        EditLabel.Width = 55
        EditLabel.Height = 13
        EditLabel.Caption = 'EMAIL NFe'
        TabOrder = 13
      end
      object TXTALVARA: TActMaskEdit
        Left = 144
        Top = 189
        Width = 148
        Height = 21
        Alignment = taLeftJustify
        ColorOnFocus = 16311512
        ColorOnNotFocus = clWindow
        EditLabel.Width = 57
        EditLabel.Height = 13
        EditLabel.Caption = 'N'#186' ALVAR'#193
        TabOrder = 14
      end
      object txtnum: TActMaskEdit
        Left = 680
        Top = 49
        Width = 73
        Height = 21
        Alignment = taLeftJustify
        ColorOnFocus = 16311512
        ColorOnNotFocus = clWindow
        EditLabel.Width = 12
        EditLabel.Height = 13
        EditLabel.Caption = 'N'#186
        TabOrder = 5
      end
      object TXTFAX: TActMaskEdit
        Left = 411
        Top = 118
        Width = 127
        Height = 21
        Alignment = taLeftJustify
        ColorOnFocus = 16311512
        ColorOnNotFocus = clWindow
        EditLabel.Width = 20
        EditLabel.Height = 13
        EditLabel.Caption = 'FAX'
        MaxLength = 13
        TabOrder = 11
        Text = '((()()()-()-()-()-()-()-()-()-()-()-()-)-----    '
        EditMask = '!\(99\)0000-0000;1;_'
      end
      object TXTSITE: TActEdit
        Left = 144
        Top = 223
        Width = 609
        Height = 21
        Alignment = taLeftJustify
        ColorOnFocus = 16311512
        ColorOnNotFocus = clWindow
        EditLabel.Width = 24
        EditLabel.Height = 13
        EditLabel.Caption = 'SITE'
        TabOrder = 17
      end
      object TXTCIDADE: TActEdit
        Left = 411
        Top = 80
        Width = 167
        Height = 21
        Alignment = taLeftJustify
        ColorOnFocus = 16311512
        ColorOnNotFocus = clWindow
        EditLabel.Width = 40
        EditLabel.Height = 13
        EditLabel.Caption = 'CIDADE'
        TabOrder = 7
      end
      object TXTCEP: TActResultEdit
        Left = 596
        Top = 20
        Width = 154
        Height = 21
        Alignment = taLeftJustify
        ColorOnFocus = 16311512
        ColorOnNotFocus = clWindow
        OnExit = TXTCEPExit
        EditLabel.Width = 21
        EditLabel.Height = 13
        EditLabel.Caption = 'CEP'
        TabOrder = 2
        Value = 0
        Glyph.Data = {
          9A020000424D9A0200000000000036000000280000000C000000110000000100
          1800000000006402000000000000000000000000000000000000FFFFFFFFFFFF
          FFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFF
          FFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFF
          FFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFF
          FFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFF
          FFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFF
          FFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFF000000
          000000000000FFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFF0000
          00000000FFFFFF000000000000FFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFF00
          0000000000FFFFFFFFFFFFFFFFFF000000000000FFFFFFFFFFFFFFFFFFFFFFFF
          000000000000FFFFFFFFFFFFFFFFFFFFFFFFFFFFFF000000000000FFFFFFFFFF
          FFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFF
          FFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFF
          FFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFF000000000000000000FFFF
          FFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFF000000000000FFFFFF00
          0000000000FFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFF000000000000FFFFFF
          FFFFFFFFFFFF000000000000FFFFFFFFFFFFFFFFFFFFFFFF000000000000FFFF
          FFFFFFFFFFFFFFFFFFFFFFFFFF000000000000FFFFFFFFFFFFFFFFFFFFFFFFFF
          FFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFF
          FFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFF}
        NumGlyphs = 1
        OnButtonClick = TXTCEPButtonClick
      end
      object TXTUF: TActEdit
        Left = 608
        Top = 81
        Width = 33
        Height = 21
        Alignment = taLeftJustify
        ColorOnFocus = 16311512
        ColorOnNotFocus = clWindow
        EditLabel.Width = 14
        EditLabel.Height = 13
        EditLabel.Caption = 'UF'
        TabOrder = 8
      end
      object TXTCOD_PAIS: TActEdit
        Left = 708
        Top = 83
        Width = 41
        Height = 21
        Alignment = taLeftJustify
        ColorOnFocus = 16311512
        ColorOnNotFocus = clWindow
        EditLabel.Width = 50
        EditLabel.Height = 13
        EditLabel.Caption = 'COD PAIS'
        TabOrder = 9
      end
    end
    object GroupBox6: TGroupBox
      Left = 16
      Top = -302
      Width = 787
      Height = 65
      TabOrder = 5
      object Label3: TLabel
        Left = 232
        Top = 13
        Width = 32
        Height = 13
        Caption = 'Label2'
      end
      object Label4: TLabel
        Left = 231
        Top = 38
        Width = 32
        Height = 13
        Caption = 'Label2'
      end
      object TXTEMPRESA: TActResultEdit
        Left = 144
        Top = 10
        Width = 81
        Height = 21
        Alignment = taLeftJustify
        ColorOnFocus = 16311512
        ColorOnNotFocus = clWindow
        EditLabel.Width = 52
        EditLabel.Height = 13
        EditLabel.Caption = 'EMPRESA'
        TabOrder = 0
        OnChange = TXTEMPRESAChange
        Value = 0
        Glyph.Data = {
          9A020000424D9A0200000000000036000000280000000C000000110000000100
          1800000000006402000000000000000000000000000000000000FFFFFFFFFFFF
          FFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFF
          FFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFF
          FFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFF
          FFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFF
          FFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFF
          FFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFF000000
          000000000000FFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFF0000
          00000000FFFFFF000000000000FFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFF00
          0000000000FFFFFFFFFFFFFFFFFF000000000000FFFFFFFFFFFFFFFFFFFFFFFF
          000000000000FFFFFFFFFFFFFFFFFFFFFFFFFFFFFF000000000000FFFFFFFFFF
          FFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFF
          FFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFF
          FFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFF000000000000000000FFFF
          FFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFF000000000000FFFFFF00
          0000000000FFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFF000000000000FFFFFF
          FFFFFFFFFFFF000000000000FFFFFFFFFFFFFFFFFFFFFFFF000000000000FFFF
          FFFFFFFFFFFFFFFFFFFFFFFFFF000000000000FFFFFFFFFFFFFFFFFFFFFFFFFF
          FFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFF
          FFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFF}
        NumGlyphs = 1
        OnButtonClick = TXTEMPRESAButtonClick
      end
      object TXTFILIAL: TActResultEdit
        Left = 144
        Top = 34
        Width = 81
        Height = 21
        Alignment = taLeftJustify
        ColorOnFocus = 16311512
        ColorOnNotFocus = clWindow
        EditLabel.Width = 31
        EditLabel.Height = 13
        EditLabel.Caption = 'FILIAL'
        TabOrder = 1
        OnChange = TXTFILIALChange
        Value = 0
        Glyph.Data = {
          9A020000424D9A0200000000000036000000280000000C000000110000000100
          1800000000006402000000000000000000000000000000000000FFFFFFFFFFFF
          FFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFF
          FFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFF
          FFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFF
          FFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFF
          FFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFF
          FFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFF000000
          000000000000FFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFF0000
          00000000FFFFFF000000000000FFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFF00
          0000000000FFFFFFFFFFFFFFFFFF000000000000FFFFFFFFFFFFFFFFFFFFFFFF
          000000000000FFFFFFFFFFFFFFFFFFFFFFFFFFFFFF000000000000FFFFFFFFFF
          FFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFF
          FFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFF
          FFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFF000000000000000000FFFF
          FFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFF000000000000FFFFFF00
          0000000000FFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFF000000000000FFFFFF
          FFFFFFFFFFFF000000000000FFFFFFFFFFFFFFFFFFFFFFFF000000000000FFFF
          FFFFFFFFFFFFFFFFFFFFFFFFFF000000000000FFFFFFFFFFFFFFFFFFFFFFFFFF
          FFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFF
          FFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFF}
        NumGlyphs = 1
        OnButtonClick = TXTFILIALButtonClick
      end
      object GroupBox8: TGroupBox
        Left = 635
        Top = 6
        Width = 113
        Height = 53
        Caption = 'STATUS'
        TabOrder = 2
        object RadioButton1: TRadioButton
          Left = 13
          Top = 16
          Width = 76
          Height = 17
          Caption = 'ATIVO'
          TabOrder = 0
        end
        object RadioButton2: TRadioButton
          Left = 12
          Top = 34
          Width = 76
          Height = 17
          Caption = 'INATIVO'
          TabOrder = 1
        end
      end
    end
    object GroupBox7: TGroupBox
      Left = 17
      Top = 580
      Width = 785
      Height = 41
      Caption = 'REPRESENTANTE'
      TabOrder = 6
      object Label5: TLabel
        Left = 396
        Top = 19
        Width = 32
        Height = 13
        Caption = 'Label2'
      end
      object TXTCODREPR: TActResultEdit
        Left = 184
        Top = 16
        Width = 209
        Height = 21
        Alignment = taLeftJustify
        ColorOnFocus = 16311512
        ColorOnNotFocus = clWindow
        EditLabel.Width = 140
        EditLabel.Height = 13
        EditLabel.Caption = 'C'#211'DIGO REPRESENTANTE'
        TabOrder = 0
        OnChange = TXTCODREPRChange
        Value = 0
        Glyph.Data = {
          9A020000424D9A0200000000000036000000280000000C000000110000000100
          1800000000006402000000000000000000000000000000000000FFFFFFFFFFFF
          FFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFF
          FFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFF
          FFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFF
          FFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFF
          FFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFF
          FFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFF000000
          000000000000FFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFF0000
          00000000FFFFFF000000000000FFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFF00
          0000000000FFFFFFFFFFFFFFFFFF000000000000FFFFFFFFFFFFFFFFFFFFFFFF
          000000000000FFFFFFFFFFFFFFFFFFFFFFFFFFFFFF000000000000FFFFFFFFFF
          FFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFF
          FFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFF
          FFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFF000000000000000000FFFF
          FFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFF000000000000FFFFFF00
          0000000000FFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFF000000000000FFFFFF
          FFFFFFFFFFFF000000000000FFFFFFFFFFFFFFFFFFFFFFFF000000000000FFFF
          FFFFFFFFFFFFFFFFFFFFFFFFFF000000000000FFFFFFFFFFFFFFFFFFFFFFFFFF
          FFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFF
          FFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFF}
        NumGlyphs = 1
      end
    end
    object GroupBox9: TGroupBox
      Left = 18
      Top = 170
      Width = 784
      Height = 147
      Caption = 'MARCA'
      TabOrder = 7
      object SpeedButton12: TSpeedButton
        Left = 728
        Top = 128
        Width = 41
        Height = 14
        OnClick = SpeedButton12Click
      end
      object DBGrid1: TDBGrid
        Left = 16
        Top = 17
        Width = 754
        Height = 104
        DataSource = DataSource1
        TabOrder = 0
        TitleFont.Charset = DEFAULT_CHARSET
        TitleFont.Color = clWindowText
        TitleFont.Height = -11
        TitleFont.Name = 'MS Sans Serif'
        TitleFont.Style = []
        OnKeyPress = DBGrid1KeyPress
        Columns = <
          item
            Expanded = False
            FieldName = 'COD_MARCA'
            Visible = True
          end
          item
            Expanded = False
            FieldName = 'MARCA'
            Width = 275
            Visible = True
          end
          item
            Expanded = False
            FieldName = 'COD_FORNECEDOR'
            Width = 116
            Visible = True
          end
          item
            Expanded = False
            Width = 269
            Visible = True
          end>
      end
    end
  end
  object IBQuery1: TIBQuery
    Database = DataModule1.DBPrincipal
    Transaction = DataModule1.IBTransaction1
    BufferChunks = 1000
    CachedUpdates = False
    Left = 576
    Top = 15
  end
  object IBQuery2: TIBQuery
    Database = DataModule1.DBPrincipal
    Transaction = DataModule1.IBTransaction1
    BufferChunks = 1000
    CachedUpdates = False
    Left = 545
    Top = 13
  end
  object IBQuery3: TIBQuery
    Database = DataModule1.DBPrincipal
    Transaction = DataModule1.IBTransaction1
    BufferChunks = 1000
    CachedUpdates = False
    SQL.Strings = (
      'select * from CLIENTES_FORNECEDOR')
    UpdateObject = IBUpdateSQL1
    Left = 617
    Top = 15
  end
  object IBQuery4: TIBQuery
    Database = DataModule1.DBPrincipal
    Transaction = DataModule1.IBTransaction1
    BufferChunks = 1000
    CachedUpdates = False
    SQL.Strings = (
      'SELECT *FROM IND_FORNECEDOR WHERE CODIGO = :CODIGO')
    Left = 653
    Top = 18
    ParamData = <
      item
        DataType = ftUnknown
        Name = 'CODIGO'
        ParamType = ptUnknown
      end>
  end
  object IBUpdateSQL1: TIBUpdateSQL
    RefreshSQL.Strings = (
      'Select '
      '  COD_PART,'
      '  NOME,'
      '  COD_PAIS,'
      '  CNPJ,'
      '  CPF,'
      '  IE,'
      '  COD_MUN,'
      '  SUFRAMA,'
      '  ENDE,'
      '  NUM,'
      '  COMPL,'
      '  BAIRRO,'
      '  CLIENTE,'
      '  FORNECEDOR,'
      '  CONTA,'
      '  BANCO,'
      '  EMAIL,'
      '  EMAIL_NFE,'
      '  REPRESENTANTE,'
      '  INTERVALO_REPR,'
      '  SITE,'
      '  DATA_CADASTRO,'
      '  TELEFONE,'
      '  FAX,'
      '  AGENCIA,'
      '  TRANSPORTADOR,'
      '  CEP,'
      '  EMPRESA,'
      '  FILIAL,'
      '  TELCONTATO,'
      '  CONTATO,'
      '  RAZAO,'
      '  FANTASIA,'
      '  STATUS,'
      '  UF'
      'from CLIENTES_FORNECEDOR '
      'where'
      '  COD_PART = :COD_PART')
    ModifySQL.Strings = (
      'update CLIENTES_FORNECEDOR'
      'set'
      '  NOME = :NOME,'
      '  COD_PAIS = :COD_PAIS,'
      '  CNPJ = :CNPJ,'
      '  CPF = :CPF,'
      '  IE = :IE,'
      '  COD_MUN = :COD_MUN,'
      '  SUFRAMA = :SUFRAMA,'
      '  ENDE = :ENDE,'
      '  NUM = :NUM,'
      '  COMPL = :COMPL,'
      '  BAIRRO = :BAIRRO,'
      '  CLIENTE = :CLIENTE,'
      '  FORNECEDOR = :FORNECEDOR,'
      '  CONTA = :CONTA,'
      '  BANCO = :BANCO,'
      '  EMAIL = :EMAIL,'
      '  EMAIL_NFE = :EMAIL_NFE,'
      '  REPRESENTANTE = :REPRESENTANTE,'
      '  INTERVALO_REPR = :INTERVALO_REPR,'
      '  SITE = :SITE,'
      '  DATA_CADASTRO = :DATA_CADASTRO,'
      '  TELEFONE = :TELEFONE,'
      '  FAX = :FAX,'
      '  AGENCIA = :AGENCIA,'
      '  TRANSPORTADOR = :TRANSPORTADOR,'
      '  CEP = :CEP,'
      '  EMPRESA = :EMPRESA,'
      '  FILIAL = :FILIAL,'
      '  TELCONTATO = :TELCONTATO,'
      '  CONTATO = :CONTATO,'
      '  RAZAO = :RAZAO,'
      '  FANTASIA = :FANTASIA,'
      '  STATUS = :STATUS,'
      '  UF = :UF'
      'where'
      '  COD_PART = :OLD_COD_PART')
    InsertSQL.Strings = (
      'insert into CLIENTES_FORNECEDOR'
      
        '  (NOME, COD_PAIS, CNPJ, CPF, IE, COD_MUN, SUFRAMA, ENDE, NUM, C' +
        'OMPL, '
      'BAIRRO, '
      '   CLIENTE, FORNECEDOR, CONTA, BANCO, EMAIL, EMAIL_NFE, '
      'REPRESENTANTE, '
      '   INTERVALO_REPR, SITE, DATA_CADASTRO, TELEFONE, FAX, AGENCIA, '
      'TRANSPORTADOR, '
      '   CEP, EMPRESA, FILIAL, TELCONTATO, CONTATO, RAZAO, FANTASIA, '
      'STATUS, '
      '   UF)'
      'values'
      
        '  (:NOME, :COD_PAIS, :CNPJ, :CPF, :IE, :COD_MUN, :SUFRAMA, :ENDE' +
        ', :NUM, '
      
        '   :COMPL, :BAIRRO, :CLIENTE, :FORNECEDOR, :CONTA, :BANCO, :EMAI' +
        'L, '
      ':EMAIL_NFE, '
      '   :REPRESENTANTE, :INTERVALO_REPR, :SITE, :DATA_CADASTRO, '
      ':TELEFONE, :FAX, '
      
        '   :AGENCIA, :TRANSPORTADOR, :CEP, :EMPRESA, :FILIAL, :TELCONTAT' +
        'O, '
      ':CONTATO, '
      '   :RAZAO, :FANTASIA, :STATUS, :UF)')
    DeleteSQL.Strings = (
      'delete from CLIENTES_FORNECEDOR'
      'where'
      '  COD_PART = :OLD_COD_PART')
    Left = 703
    Top = 15
  end
  object QryForn: TIBQuery
    Database = DataModule1.DBPrincipal
    Transaction = DataModule1.IBTransaction1
    BufferChunks = 1000
    CachedUpdates = False
    SQL.Strings = (
      
        'select *from  from CLIENTES_FORNECEDOR where COD_PART = :CODPART' +
        '1')
    Left = 437
    Top = 16
    ParamData = <
      item
        DataType = ftUnknown
        Name = 'CODPART1'
        ParamType = ptUnknown
      end>
  end
  object QryEmpr: TIBQuery
    Database = DataModule1.DBPrincipal
    Transaction = DataModule1.IBTransaction1
    BufferChunks = 1000
    CachedUpdates = False
    SQL.Strings = (
      'select *from CLIENTES_FORNECEDOR where COD_PART = :CODPART')
    Left = 477
    Top = 16
    ParamData = <
      item
        DataType = ftUnknown
        Name = 'CODPART'
        ParamType = ptUnknown
      end>
  end
  object QryFilial: TIBQuery
    Database = DataModule1.DBPrincipal
    Transaction = DataModule1.IBTransaction1
    BufferChunks = 1000
    CachedUpdates = False
    SQL.Strings = (
      'select * from RAMO_ATIVIDADE WHERE RAMO = :RAMO')
    Left = 507
    Top = 16
    ParamData = <
      item
        DataType = ftUnknown
        Name = 'RAMO'
        ParamType = ptUnknown
      end>
  end
  object IBQuery5: TIBQuery
    Database = FRMCADPROD.IBDatabase1
    Transaction = FRMCADPROD.IBTransaction1
    BufferChunks = 1000
    CachedUpdates = False
    SQL.Strings = (
      'SELECT *FROM MARCA')
    UpdateObject = IBUpdateSQL2
    Left = 869
    Top = 368
    object IBQuery5COD_MARCA: TIntegerField
      FieldName = 'COD_MARCA'
      Origin = 'MARCA.COD_MARCA'
      Required = True
    end
    object IBQuery5MARCA: TIBStringField
      FieldName = 'MARCA'
      Origin = 'MARCA.MARCA'
      Size = 120
    end
    object IBQuery5COD_FORNECEDOR: TIntegerField
      FieldName = 'COD_FORNECEDOR'
      Origin = 'MARCA.COD_FORNECEDOR'
    end
  end
  object IBUpdateSQL2: TIBUpdateSQL
    RefreshSQL.Strings = (
      'Select '
      '  COD_MARCA,'
      '  MARCA,'
      '  COD_FORNECEDOR'
      'from MARCA '
      'where'
      '  COD_MARCA = :COD_MARCA')
    ModifySQL.Strings = (
      'update MARCA'
      'set'
      '  COD_MARCA = :COD_MARCA,'
      '  MARCA = :MARCA,'
      '  COD_FORNECEDOR = :COD_FORNECEDOR'
      'where'
      '  COD_MARCA = :OLD_COD_MARCA')
    InsertSQL.Strings = (
      'insert into MARCA'
      '  (COD_MARCA, MARCA, COD_FORNECEDOR)'
      'values'
      '  (:COD_MARCA, :MARCA, :COD_FORNECEDOR)')
    DeleteSQL.Strings = (
      'delete from MARCA'
      'where'
      '  COD_MARCA = :OLD_COD_MARCA')
    Left = 870
    Top = 402
  end
  object DataSource1: TDataSource
    DataSet = IBQuery5
    Left = 905
    Top = 369
  end
  object PopupMenu1: TPopupMenu
    Left = 338
    Top = 529
    object INSERIR1: TMenuItem
      Caption = 'INSERIR'
      OnClick = INSERIR1Click
    end
    object ALTERAR1: TMenuItem
      Caption = 'ALTERAR'
      Enabled = False
    end
    object EXCLUIR1: TMenuItem
      Caption = 'EXCLUIR'
    end
  end
  object IBQuery6: TIBQuery
    Database = DataModule1.DBPrincipal
    Transaction = DataModule1.IBTransaction1
    BufferChunks = 1000
    CachedUpdates = False
    SQL.Strings = (
      
        'select *from  from CLIENTES_FORNECEDOR where COD_PART = :CODPART' +
        '1')
    Left = 405
    Top = 16
    ParamData = <
      item
        DataType = ftUnknown
        Name = 'CODPART1'
        ParamType = ptUnknown
      end>
  end
end
