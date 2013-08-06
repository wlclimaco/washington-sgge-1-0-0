object frmPrincipal: TfrmPrincipal
  Left = 0
  Top = 0
  BorderStyle = bsDialog
  Caption = 'Emulador de balan'#231'a (Filizola / Toledo)'
  ClientHeight = 235
  ClientWidth = 459
  Color = clBtnFace
  Font.Charset = DEFAULT_CHARSET
  Font.Color = clWindowText
  Font.Height = -11
  Font.Name = 'Tahoma'
  Font.Style = []
  OldCreateOrder = False
  Position = poScreenCenter
  OnClose = FormClose
  OnCreate = FormCreate
  PixelsPerInch = 96
  TextHeight = 13
  object gbxConfigAplicativo: TGroupBox
    AlignWithMargins = True
    Left = 3
    Top = 3
    Width = 453
    Height = 78
    Align = alTop
    Caption = ' Configura'#231#245'es do aplicativo '
    TabOrder = 0
    object Label1: TLabel
      Left = 12
      Top = 25
      Width = 105
      Height = 13
      Caption = 'Porta de comunica'#231#227'o'
      Transparent = True
    end
    object ckbMonitorarPorta: TCheckBox
      Left = 153
      Top = 43
      Width = 281
      Height = 17
      Caption = 'Monitorar porta aguardando pedido de leitura do peso.'
      TabOrder = 1
      OnClick = ckbMonitorarPortaClick
    end
    object cbxPortaComunicacao: TComboBox
      Left = 12
      Top = 41
      Width = 135
      Height = 21
      Style = csDropDownList
      TabOrder = 0
      Items.Strings = (
        'COM1'
        'COM2'
        'COM3'
        'COM4'
        'COM5'
        'COM6'
        'COM7'
        'COM8'
        'COM9'
        'COM10')
    end
  end
  object gbxControlePeso: TGroupBox
    AlignWithMargins = True
    Left = 3
    Top = 87
    Width = 453
    Height = 74
    Align = alTop
    Caption = ' Controle de peso atual '
    TabOrder = 1
    object Label2: TLabel
      Left = 12
      Top = 23
      Width = 65
      Height = 13
      Caption = 'Peso a enviar'
    end
    object btnPesoGerar: TButton
      Left = 159
      Top = 37
      Width = 138
      Height = 25
      Caption = 'Gerar peso'
      TabOrder = 0
      OnClick = btnPesoGerarClick
    end
    object btnPesoEnviar: TButton
      Left = 303
      Top = 37
      Width = 138
      Height = 25
      Caption = 'Enviar peso'
      TabOrder = 1
      OnClick = btnPesoEnviarClick
    end
    object edtPesoAtual: TEdit
      Left = 12
      Top = 39
      Width = 141
      Height = 21
      Enabled = False
      TabOrder = 2
      Text = '1,000'
    end
  end
  object gbxSimulacoes: TGroupBox
    AlignWithMargins = True
    Left = 3
    Top = 167
    Width = 453
    Height = 65
    Align = alClient
    Caption = ' Simula'#231#245'es '
    TabOrder = 2
    object btnSimularSobrepeso: TButton
      Left = 12
      Top = 26
      Width = 141
      Height = 25
      Caption = 'Sobrepeso'
      TabOrder = 0
      OnClick = btnSimularSobrepesoClick
    end
    object btnSimularPesoInstavel: TButton
      Left = 159
      Top = 26
      Width = 138
      Height = 25
      Caption = 'Peso inst'#225'vel'
      TabOrder = 1
      OnClick = btnSimularPesoInstavelClick
    end
    object btnSimularPesoNegativo: TButton
      Left = 303
      Top = 26
      Width = 138
      Height = 25
      Caption = 'Peso Negativo'
      TabOrder = 2
      OnClick = btnSimularPesoNegativoClick
    end
  end
end
