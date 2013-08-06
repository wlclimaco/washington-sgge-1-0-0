object Form1: TForm1
  Left = 406
  Top = 154
  Width = 698
  Height = 460
  Caption = 'SAT Teste - Projeto ACBr'
  Color = clBtnFace
  Font.Charset = DEFAULT_CHARSET
  Font.Color = clWindowText
  Font.Height = -11
  Font.Name = 'Tahoma'
  Font.Style = []
  Menu = MainMenu1
  OldCreateOrder = True
  OnCreate = FormCreate
  PixelsPerInch = 96
  TextHeight = 13
  object Splitter1: TSplitter
    Left = 0
    Top = 184
    Width = 682
    Height = 5
    Cursor = crVSplit
    Align = alTop
  end
  object PageControl1: TPageControl
    Left = 0
    Top = 189
    Width = 682
    Height = 213
    ActivePage = tsRecebido
    Align = alClient
    TabOrder = 1
    object tsLog: TTabSheet
      Caption = 'Log de Comandos'
      object mResposta: TMemo
        Left = 0
        Top = 29
        Width = 674
        Height = 156
        Align = alBottom
        Anchors = [akLeft, akTop, akRight, akBottom]
        ScrollBars = ssVertical
        TabOrder = 0
      end
    end
    object tsGerado: TTabSheet
      Caption = 'XML Gerado'
      object mVenda: TMemo
        Left = 0
        Top = 160
        Width = 674
        Height = 25
        Align = alBottom
        ScrollBars = ssVertical
        TabOrder = 0
      end
      object wbVenda: TWebBrowser
        Left = 0
        Top = 0
        Width = 674
        Height = 160
        Align = alClient
        TabOrder = 1
        ControlData = {
          4C000000A9450000891000000000000000000000000000000000000000000000
          000000004C000000000000000000000001000000E0D057007335CF11AE690800
          2B2E126208000000000000004C0000000114020000000000C000000000000046
          8000000000000000000000000000000000000000000000000000000000000000
          00000000000000000100000000000000000000000000000000000000}
      end
    end
    object tsRecebido: TTabSheet
      Caption = 'XML Recebido'
      object mCupom: TMemo
        Left = 0
        Top = 160
        Width = 674
        Height = 25
        Align = alBottom
        ScrollBars = ssVertical
        TabOrder = 0
      end
      object wbCupom: TWebBrowser
        Left = 0
        Top = 0
        Width = 674
        Height = 160
        Align = alClient
        TabOrder = 1
        ControlData = {
          4C000000A9450000891000000000000000000000000000000000000000000000
          000000004C000000000000000000000001000000E0D057007335CF11AE690800
          2B2E126208000000000000004C0000000114020000000000C000000000000046
          8000000000000000000000000000000000000000000000000000000000000000
          00000000000000000100000000000000000000000000000000000000}
      end
    end
  end
  object Panel1: TPanel
    Left = 0
    Top = 0
    Width = 682
    Height = 184
    Align = alTop
    TabOrder = 0
    object gpOperacao: TGroupBox
      Left = 1
      Top = 1
      Width = 145
      Height = 182
      Align = alLeft
      Caption = 'Inicializa'#231#227'o'
      TabOrder = 0
      object bInicializar: TButton
        Left = 16
        Top = 52
        Width = 105
        Height = 33
        Caption = 'Inicializar'
        TabOrder = 0
        OnClick = bInicializarClick
      end
      object cbxModelo: TComboBox
        Left = 16
        Top = 20
        Width = 105
        Height = 21
        Style = csDropDownList
        ItemHeight = 13
        TabOrder = 1
        OnChange = cbxModeloChange
      end
      object btSalvarParams: TButton
        Left = 16
        Top = 139
        Width = 105
        Height = 25
        Caption = 'Salvar Params'
        TabOrder = 2
        OnClick = btSalvarParamsClick
      end
      object btLerParams: TButton
        Left = 16
        Top = 107
        Width = 105
        Height = 25
        Caption = 'Ler Params'
        TabOrder = 3
        OnClick = btLerParamsClick
      end
    end
    object GroupBox1: TGroupBox
      Left = 146
      Top = 1
      Width = 535
      Height = 182
      Align = alClient
      Caption = 'Configura'#231#227'o'
      TabOrder = 1
      object PageControl2: TPageControl
        Left = 2
        Top = 15
        Width = 531
        Height = 165
        ActivePage = tsDadosSAT
        Align = alClient
        TabOrder = 0
        object tsDadosSAT: TTabSheet
          Caption = 'Dados do SAT CFe'
          DesignSize = (
            523
            137)
          object Label9: TLabel
            Left = 26
            Top = 15
            Width = 42
            Height = 13
            Alignment = taRightJustify
            Caption = 'Arq.Log:'
            Color = clBtnFace
            ParentColor = False
          end
          object SbArqLog: TSpeedButton
            Left = 192
            Top = 32
            Width = 24
            Height = 22
            Caption = '...'
            OnClick = SbArqLogClick
          end
          object Label10: TLabel
            Left = 233
            Top = 15
            Width = 46
            Height = 13
            Alignment = taRightJustify
            Caption = 'Path DLL:'
            Color = clBtnFace
            ParentColor = False
          end
          object Label1: TLabel
            Left = 23
            Top = 64
            Width = 93
            Height = 13
            Caption = 'C'#243'digo de Ativa'#231#227'o'
            Color = clBtnFace
            ParentColor = False
          end
          object Label4: TLabel
            Left = 200
            Top = 64
            Width = 36
            Height = 13
            Caption = 'C'#243'd.UF'
            Color = clBtnFace
            ParentColor = False
          end
          object Label3: TLabel
            Left = 262
            Top = 64
            Width = 52
            Height = 13
            Caption = 'Num.Caixa'
            Color = clBtnFace
            ParentColor = False
          end
          object Label6: TLabel
            Left = 357
            Top = 64
            Width = 45
            Height = 13
            Caption = 'Ambiente'
            Color = clBtnFace
            ParentColor = False
          end
          object Label7: TLabel
            Left = 357
            Top = 16
            Width = 26
            Height = 13
            Caption = 'Porta'
          end
          object edLog: TEdit
            Left = 24
            Top = 32
            Width = 163
            Height = 21
            Cursor = crIBeam
            TabOrder = 0
          end
          object edPathDLL: TEdit
            Left = 230
            Top = 32
            Width = 115
            Height = 21
            Cursor = crIBeam
            Anchors = [akLeft, akTop, akRight]
            TabOrder = 1
          end
          object edtCodigoAtivacao: TEdit
            Left = 23
            Top = 80
            Width = 164
            Height = 21
            TabOrder = 2
          end
          object edtCodUF: TEdit
            Left = 200
            Top = 80
            Width = 49
            Height = 21
            TabOrder = 3
          end
          object seNumeroCaixa: TSpinEdit
            Left = 262
            Top = 80
            Width = 58
            Height = 22
            MaxValue = 999
            MinValue = 1
            TabOrder = 4
            Value = 1
          end
          object cbxAmbiente: TComboBox
            Left = 357
            Top = 80
            Width = 132
            Height = 21
            Style = csDropDownList
            ItemHeight = 13
            TabOrder = 5
          end
          object edtPorta: TEdit
            Left = 357
            Top = 32
            Width = 132
            Height = 21
            TabOrder = 6
            Text = 'COM7'
          end
        end
        object tsDadosEmit: TTabSheet
          Caption = 'Dados Emitente'
          object Label11: TLabel
            Left = 12
            Top = 23
            Width = 25
            Height = 13
            Caption = 'CNPJ'
            Color = clBtnFace
            ParentColor = False
          end
          object Label12: TLabel
            Left = 192
            Top = 23
            Width = 65
            Height = 13
            Caption = 'Insc.Estadual'
            Color = clBtnFace
            ParentColor = False
          end
          object Label14: TLabel
            Left = 336
            Top = 23
            Width = 67
            Height = 13
            Caption = 'Insc.Municipal'
            Color = clBtnFace
            ParentColor = False
          end
          object Label15: TLabel
            Left = 192
            Top = 79
            Width = 94
            Height = 13
            Caption = 'Regime Trib. ISSQN'
            Color = clBtnFace
            ParentColor = False
          end
          object Label16: TLabel
            Left = 336
            Top = 79
            Width = 72
            Height = 13
            Caption = 'Ind.Rat.ISSQN'
            Color = clBtnFace
            ParentColor = False
          end
          object Label17: TLabel
            Left = 12
            Top = 81
            Width = 84
            Height = 13
            Caption = 'Regime Tributario'
            Color = clBtnFace
            ParentColor = False
          end
          object edtEmitCNPJ: TEdit
            Left = 12
            Top = 38
            Width = 166
            Height = 21
            Cursor = crIBeam
            TabOrder = 0
          end
          object edtEmitIE: TEdit
            Left = 192
            Top = 38
            Width = 134
            Height = 21
            Cursor = crIBeam
            TabOrder = 1
          end
          object edtEmitIM: TEdit
            Left = 336
            Top = 38
            Width = 134
            Height = 21
            Cursor = crIBeam
            TabOrder = 2
          end
          object cbxRegTribISSQN: TComboBox
            Left = 192
            Top = 95
            Width = 134
            Height = 21
            Style = csDropDownList
            ItemHeight = 13
            TabOrder = 4
          end
          object cbxIndRatISSQN: TComboBox
            Left = 336
            Top = 95
            Width = 134
            Height = 21
            Style = csDropDownList
            ItemHeight = 13
            TabOrder = 5
          end
          object cbxRegTributario: TComboBox
            Left = 12
            Top = 95
            Width = 166
            Height = 21
            Style = csDropDownList
            ItemHeight = 13
            TabOrder = 3
          end
        end
        object tsDadosSwHouse: TTabSheet
          Caption = 'Dados Sw.House'
          DesignSize = (
            523
            137)
          object Label2: TLabel
            Left = 10
            Top = 15
            Width = 25
            Height = 13
            Caption = 'CNPJ'
            Color = clBtnFace
            ParentColor = False
          end
          object Label5: TLabel
            Left = 10
            Top = 71
            Width = 185
            Height = 13
            Caption = 'Assinatura Sw.House (344 caracteres)'
            Color = clBtnFace
            ParentColor = False
          end
          object edtSwHCNPJ: TEdit
            Left = 10
            Top = 31
            Width = 208
            Height = 21
            Anchors = [akLeft, akTop, akRight]
            TabOrder = 0
          end
          object edtSwHAssinatura: TEdit
            Left = 10
            Top = 89
            Width = 473
            Height = 21
            Anchors = [akLeft, akTop, akRight]
            TabOrder = 1
          end
        end
      end
    end
  end
  object MainMenu1: TMainMenu
    Left = 61
    Top = 328
    object MenuItem1: TMenuItem
      Caption = 'Ativa'#231#227'o'
      object mAtivarSAT: TMenuItem
        Caption = 'Ativar SAT'
        OnClick = mAtivarSATClick
      end
      object mComunicarCertificado: TMenuItem
        Caption = 'Comunicar Certificado'
        OnClick = mComunicarCertificadoClick
      end
      object mAssociarAssinatura: TMenuItem
        Caption = 'Associar Assinatura'
        OnClick = mAssociarAssinaturaClick
      end
      object MenuItem3: TMenuItem
        Caption = '-'
      end
      object mBloquearSAT: TMenuItem
        Caption = 'Bloquear SAT'
        OnClick = mBloquearSATClick
      end
      object mDesbloquearSAT: TMenuItem
        Caption = 'Desbloquear SAT'
        OnClick = mDesbloquearSATClick
      end
      object MenuItem4: TMenuItem
        Caption = '-'
      end
      object MenuItem5: TMenuItem
        Caption = 'Trocar  C'#243'digo de Ativa'#231#227'o'
        OnClick = MenuItem5Click
      end
    end
    object MenuItem2: TMenuItem
      Caption = 'Venda'
      object mGerarVenda: TMenuItem
        Caption = 'Gerar Venda'
        OnClick = mGerarVendaClick
      end
      object mEnviarVenda: TMenuItem
        Caption = 'Enviar Venda'
        OnClick = mEnviarVendaClick
      end
      object ImprimirExtratoVenda1: TMenuItem
        Caption = 'Imprimir Extrato Venda'
        OnClick = ImprimirExtratoVenda1Click
      end
      object ImprimirExtratoVendaResumido1: TMenuItem
        Caption = 'Imprimir Extrato Venda Resumido'
        OnClick = ImprimirExtratoVendaResumido1Click
      end
      object N1: TMenuItem
        Caption = '-'
      end
      object mCancelarUltimaVenda: TMenuItem
        Caption = 'Cancelar '#218'ltima Venda'
        OnClick = mCancelarUltimaVendaClick
      end
      object ImprimirExtratoCancelamento1: TMenuItem
        Caption = 'Imprimir Extrato Cancelamento'
        OnClick = ImprimirExtratoCancelamento1Click
      end
    end
    object MenuItem6: TMenuItem
      Caption = 'Consultas'
      object mConsultarStatusOperacional: TMenuItem
        Caption = 'Consultar Status Operacional'
        OnClick = mConsultarStatusOperacionalClick
      end
      object mConsultarSAT: TMenuItem
        Caption = 'Consultar SAT'
        OnClick = mConsultarSATClick
      end
      object mConsultarNumeroSessao: TMenuItem
        Caption = 'Consultar Numero Sess'#227'o'
        OnClick = mConsultarNumeroSessaoClick
      end
    end
    object MenuItem7: TMenuItem
      Caption = 'Configura'#231#227'o'
      object mAtaulizarSoftwareSAT: TMenuItem
        Caption = 'Ataulizar Software SAT'
        OnClick = mAtaulizarSoftwareSATClick
      end
      object mConfigurarInterfaceRede: TMenuItem
        Caption = 'Configurar Interface Rede'
        OnClick = mConfigurarInterfaceRedeClick
      end
    end
    object MenuItem8: TMenuItem
      Caption = 'Diversos'
      object mTesteFimAFim: TMenuItem
        Caption = 'Teste Fim a Fim'
      end
      object mExtrairLogs: TMenuItem
        Caption = 'Extrair Logs'
        OnClick = mExtrairLogsClick
      end
      object MenuItem9: TMenuItem
        Caption = '-'
      end
      object mDesligarSAT: TMenuItem
        Caption = 'Desligar SAT'
        OnClick = mDesligarSATClick
      end
    end
    object Limpar1: TMenuItem
      Caption = 'Limpar'
      OnClick = Limpar1Click
    end
  end
  object OpenDialog1: TOpenDialog
    Left = 97
    Top = 328
  end
  object ACBrSAT1: TACBrSAT
    Modelo = satEmuladorSP
    Extrato = ACBrSATExtratoESCPOS1
    OnLog = ACBrSAT1Log
    Config.infCFe_versaoDadosEnt = 0.030000000000000000
    Config.ide_numeroCaixa = 0
    Config.ide_tpAmb = taHomologacao
    Config.emit_cRegTrib = RTSimplesNacional
    Config.emit_cRegTribISSQN = RTISSMicroempresaMunicipal
    Config.emit_indRatISSQN = irSim
    OnGetcodigoDeAtivacao = ACBrSAT1GetcodigoDeAtivacao
    OnGetsignAC = ACBrSAT1GetsignAC
    Left = 24
    Top = 330
  end
  object ACBrSATExtratoESCPOS1: TACBrSATExtratoESCPOS
    ACBrSAT = ACBrSAT1
    ImprimeQRCode = False
    Left = 140
    Top = 325
  end
end
