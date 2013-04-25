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
  object Panel1: TPanel
    Left = 0
    Top = 0
    Width = 169
    Height = 604
    Align = alLeft
    TabOrder = 0
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
  object Panel2: TPanel
    Left = 169
    Top = 0
    Width = 705
    Height = 604
    Align = alClient
    TabOrder = 1
    object Panel3: TPanel
      Left = 1
      Top = 1
      Width = 703
      Height = 432
      Align = alTop
      TabOrder = 0
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
              Top = 56
              Width = 31
              Height = 13
              Caption = 'Senha'
            end
            object sbtnCaminhoCert: TSpeedButton
              Left = 175
              Top = 34
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
              Top = 96
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
              Top = 32
              Width = 161
              Height = 21
              TabOrder = 0
            end
            object edtSenha: TEdit
              Left = 8
              Top = 72
              Width = 161
              Height = 21
              PasswordChar = '*'
              TabOrder = 1
            end
            object edtNumSerie: TEdit
              Left = 8
              Top = 112
              Width = 161
              Height = 21
              TabOrder = 2
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
          object Panel5: TPanel
            Left = 0
            Top = 361
            Width = 693
            Height = 41
            Align = alBottom
            TabOrder = 0
            object BrvBitBtn5: TBrvBitBtn
              Left = 5
              Top = 6
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
            object BrvBitBtn6: TBrvBitBtn
              Left = 143
              Top = 6
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
              TabOrder = 1
              BrTipoBotao = BrBtnNone
            end
            object BrvBitBtn7: TBrvBitBtn
              Left = 295
              Top = 6
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
              TabOrder = 2
              BrTipoBotao = BrBtnNone
            end
            object BrvBitBtn8: TBrvBitBtn
              Left = 431
              Top = 6
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
              TabOrder = 3
              BrTipoBotao = BrBtnNone
            end
          end
          object Panel4: TPanel
            Left = 0
            Top = 0
            Width = 693
            Height = 49
            Align = alTop
            TabOrder = 1
          end
          object BrvDbGrid1: TBrvDbGrid
            Left = 0
            Top = 49
            Width = 693
            Height = 312
            BrShowMemo = True
            BrReadOnlyStyle = [fsItalic]
            BrReadOnlyColor = clMaroon
            Align = alClient
            DataSource = DataSource1
            Options = [dgTitles, dgIndicator, dgColumnResize, dgColLines, dgRowLines, dgTabs, dgRowSelect, dgAlwaysShowSelection, dgConfirmDelete, dgCancelOnExit, dgMultiSelect]
            PopupMenu = PopupMenu1
            TabOrder = 2
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
                FieldName = 'chNFe'
                Visible = True
                BrConsulta = 0
                BrPermissao = []
                BrValueHalfChecked = False
                BrSaveOnClick = False
              end
              item
                Expanded = False
                FieldName = 'ide_nNF'
                Visible = True
                BrConsulta = 0
                BrPermissao = []
                BrValueHalfChecked = False
                BrSaveOnClick = False
              end
              item
                Expanded = False
                FieldName = 'ide_serie'
                Visible = True
                BrConsulta = 0
                BrPermissao = []
                BrValueHalfChecked = False
                BrSaveOnClick = False
              end
              item
                Expanded = False
                FieldName = 'ide_dEmi'
                Visible = True
                BrConsulta = 0
                BrPermissao = []
                BrValueHalfChecked = False
                BrSaveOnClick = False
              end
              item
                Expanded = False
                FieldName = 'ide_dSaiEnt'
                Visible = True
                BrConsulta = 0
                BrPermissao = []
                BrValueHalfChecked = False
                BrSaveOnClick = False
              end
              item
                Expanded = False
                FieldName = 'emit_CNPJ'
                Visible = True
                BrConsulta = 0
                BrPermissao = []
                BrValueHalfChecked = False
                BrSaveOnClick = False
              end
              item
                Expanded = False
                FieldName = 'emit_xNome'
                Visible = True
                BrConsulta = 0
                BrPermissao = []
                BrValueHalfChecked = False
                BrSaveOnClick = False
              end
              item
                Expanded = False
                FieldName = 'nProt'
                Visible = True
                BrConsulta = 0
                BrPermissao = []
                BrValueHalfChecked = False
                BrSaveOnClick = False
              end
              item
                Expanded = False
                FieldName = 'xMotivo'
                Visible = True
                BrConsulta = 0
                BrPermissao = []
                BrValueHalfChecked = False
                BrSaveOnClick = False
              end
              item
                Expanded = False
                FieldName = 'Xml'
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
    object PageControl2: TPageControl
      Left = 1
      Top = 439
      Width = 703
      Height = 164
      ActivePage = TabSheet10
      Align = alBottom
      TabOrder = 1
      object TabSheet5: TTabSheet
        Caption = 'Respostas'
        object MemoResp: TMemo
          Left = 0
          Top = 0
          Width = 695
          Height = 136
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
          Width = 695
          Height = 136
          Align = alClient
          TabOrder = 0
          ExplicitWidth = 571
          ExplicitHeight = 263
          ControlData = {
            4C000000D54700000E0E00000000000000000000000000000000000000000000
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
          Width = 695
          Height = 136
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
          Width = 695
          Height = 136
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
          Top = 0
          Width = 695
          Height = 136
          Align = alClient
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
          Width = 695
          Height = 136
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
          Width = 695
          Height = 136
          Align = alClient
          Indent = 19
          TabOrder = 0
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
    Left = 310
    Top = 46
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
    Left = 367
    Top = 48
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
    Left = 582
    Top = 32
  end
  object BrvXMLNFE: TBrvXML
    BrGerarBanco = True
    BrQtdePagDanfe = 0
    Left = 680
    Top = 96
  end
  object SQLConnection1: TSQLConnection
    ConnectionName = 'FBCONNECTION'
    DriverName = 'Firebird'
    GetDriverFunc = 'getSQLDriverINTERBASE'
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
      'trim char=False')
    VendorLib = 'fbclient.DLL'
    Left = 768
    Top = 128
  end
  object DataSource1: TDataSource
    Left = 440
    Top = 32
  end
  object xml: TClientDataSet
    Aggregates = <>
    Params = <>
    Left = 368
    Top = 152
  end
  object PopupMenu1: TPopupMenu
    Left = 528
    Top = 200
    object Detalhar1: TMenuItem
      Caption = 'Detalhar'
    end
    object Imprimir1: TMenuItem
      Caption = 'Imprimir'
    end
  end
end
