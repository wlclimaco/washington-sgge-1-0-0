inherited SRA0001: TSRA0001
  Left = 317
  Top = 178
  Caption = 'SRA0001 - Atualiza'#231#227'o Cliente'
  ClientWidth = 809
  FormStyle = fsNormal
  Visible = False
  ExplicitLeft = 317
  ExplicitTop = 178
  ExplicitWidth = 817
  PixelsPerInch = 96
  TextHeight = 13
  inherited NavBarra: TBrvDbNavCop
    Width = 809
    ExplicitWidth = 702
    inherited BrvSpeedButton1: TBrvSpeedButton
      Left = 782
      ExplicitLeft = 675
    end
    inherited SbtAjuda: TBrvSpeedButton
      Left = 732
      ExplicitLeft = 625
    end
    object SbrConfirm: TBrvSpeedButton
      Left = 757
      Top = 2
      Width = 25
      Height = 25
      Hint = 'Confirmar'
      Anchors = [akTop, akRight]
      Enabled = False
      Flat = True
      Glyph.Data = {
        06020000424D0602000000000000760000002800000028000000140000000100
        0400000000009001000000000000000000001000000000000000000000000000
        8000008000000080800080000000800080008080000080808000C0C0C0000000
        FF0000FF000000FFFF00FF000000FF00FF00FFFF0000FFFFFF00333333333333
        3333333333333333333333333333333333333333333333333333333333333333
        333333333333333333333333333333F333333333333333333334433333333333
        3333377F3333333333333333334224333333333333337337F333333333333333
        3422224333333333333733337F33333333333333422222243333333333733333
        37F3333333333334222A22224333333337F337F3337F33333333333222A3A222
        4333333337F3737F337F33333333333A2A333A222433333337F73337F337F333
        33333333A33333A222433333337333337F337F33333333333333333A22243333
        3333333337F337F33333333333333333A222433333333333337F337F33333333
        333333333A222433333333333337F337F33333333333333333A2224333333333
        33337F337F33333333333333333A224333333333333337F37F33333333333333
        3333A223333333333333337F733333333333333333333A333333333333333337
        3333333333333333333333333333333333333333333333333333333333333333
        33333333333333333333}
      NumGlyphs = 2
      ParentShowHint = False
      ShowHint = True
      OnClick = SbrConfirmClick
      BrTipoBotao = BrBtnOk
      ExplicitLeft = 650
    end
    object Label1: TLabel
      Left = 7
      Top = 6
      Width = 690
      Height = 16
      Caption = 
        'Recomenda-se a n'#227'o utiliza'#231#227'o de espa'#231'os nos nomes de diret'#243'rios' +
        ' e arquivos, principalmente no cliente!!'
      Font.Charset = DEFAULT_CHARSET
      Font.Color = clRed
      Font.Height = -13
      Font.Name = 'Tahoma'
      Font.Style = [fsBold]
      ParentFont = False
    end
  end
  inherited PnlFundo: TPanel
    Width = 809
    ExplicitTop = 33
    ExplicitWidth = 669
    object Panel1: TPanel
      Left = 1
      Top = 1
      Width = 803
      Height = 29
      Align = alTop
      TabOrder = 0
      object Label8: TLabel
        Left = 7
        Top = 6
        Width = 85
        Height = 13
        Caption = 'Carregar Pasta'
        Font.Charset = DEFAULT_CHARSET
        Font.Color = clWindowText
        Font.Height = -11
        Font.Name = 'Tahoma'
        Font.Style = [fsBold]
        ParentFont = False
      end
      object BrvSpeedButton2: TBrvSpeedButton
        Left = 96
        Top = 3
        Width = 23
        Height = 22
        Hint = 'Abrir'
        Flat = True
        Glyph.Data = {
          56070000424D5607000000000000360400002800000028000000140000000100
          0800000000002003000000000000000000000001000000000000000000000000
          80000080000000808000800000008000800080800000C0C0C000C0DCC000F0CA
          A6000020400000206000002080000020A0000020C0000020E000004000000040
          20000040400000406000004080000040A0000040C0000040E000006000000060
          20000060400000606000006080000060A0000060C0000060E000008000000080
          20000080400000806000008080000080A0000080C0000080E00000A0000000A0
          200000A0400000A0600000A0800000A0A00000A0C00000A0E00000C0000000C0
          200000C0400000C0600000C0800000C0A00000C0C00000C0E00000E0000000E0
          200000E0400000E0600000E0800000E0A00000E0C00000E0E000400000004000
          20004000400040006000400080004000A0004000C0004000E000402000004020
          20004020400040206000402080004020A0004020C0004020E000404000004040
          20004040400040406000404080004040A0004040C0004040E000406000004060
          20004060400040606000406080004060A0004060C0004060E000408000004080
          20004080400040806000408080004080A0004080C0004080E00040A0000040A0
          200040A0400040A0600040A0800040A0A00040A0C00040A0E00040C0000040C0
          200040C0400040C0600040C0800040C0A00040C0C00040C0E00040E0000040E0
          200040E0400040E0600040E0800040E0A00040E0C00040E0E000800000008000
          20008000400080006000800080008000A0008000C0008000E000802000008020
          20008020400080206000802080008020A0008020C0008020E000804000008040
          20008040400080406000804080008040A0008040C0008040E000806000008060
          20008060400080606000806080008060A0008060C0008060E000808000008080
          20008080400080806000808080008080A0008080C0008080E00080A0000080A0
          200080A0400080A0600080A0800080A0A00080A0C00080A0E00080C0000080C0
          200080C0400080C0600080C0800080C0A00080C0C00080C0E00080E0000080E0
          200080E0400080E0600080E0800080E0A00080E0C00080E0E000C0000000C000
          2000C0004000C0006000C0008000C000A000C000C000C000E000C0200000C020
          2000C0204000C0206000C0208000C020A000C020C000C020E000C0400000C040
          2000C0404000C0406000C0408000C040A000C040C000C040E000C0600000C060
          2000C0604000C0606000C0608000C060A000C060C000C060E000C0800000C080
          2000C0804000C0806000C0808000C080A000C080C000C080E000C0A00000C0A0
          2000C0A04000C0A06000C0A08000C0A0A000C0A0C000C0A0E000C0C00000C0C0
          2000C0C04000C0C06000C0C08000C0C0A000F0FBFF00A4A0A000808080000000
          FF0000FF000000FFFF00FF000000FF00FF00FFFF0000FFFFFF00030303030303
          0303030303030303030303030303030303030303030303030303030303030303
          0303030303030303030303030303030303030303030303030303030303030303
          0303030303030303030303030303030303030303030303030303030303030303
          03FFFFFFFFFFFFFFFFFFFFFF0303030303030303030303030303030303030303
          0303030303030303A4A4A4A4A4A4A4A4A4A4A408FF0303030303030300000000
          00000000000000030303030303030303A4A408FF08080808080808A408FF0303
          030303030000030303030303030303000303030303030303A4FFA408FF080808
          08080808A408FF030303030300FB000303030303030303030003030303030303
          A4FF08A408FF08080808080808A408FF0303030300FFFB000303030303030303
          0300030303030303A4FF0808A408FFFFFFFFFFFFFFFFA4FFFF03030300FBFFFB
          00030303030303030303000303030303A4FF080808A4A4A4A4A4A4A4A4A4A4A4
          0303030300FFFBFFFB000000000000000000000003030303A4FF080808080808
          0808A4FF030303030303030300FBFFFBFFFBFFFBFFFB00030303030303030303
          A4FF080808FFFFFFFFFFA4FF030303030303030300FFFBFFFBFFFBFFFBFF0003
          0303030303030303A408FFFFA4A4A4A4A4A4A40303FFFFFF0303030300FBFFFB
          0000000000000003030303030303030303A4A4A40303030303030303A4A4A4FF
          0303030303000000030303030303030300000003030303030303030303030303
          03FF030303A4A4FF030303030303030303030303030303030300000303030303
          0303030303030303A408FFFFA403A40303030303030303030303030300030303
          0003000303030303030303030303030303A4A4A4030303030303030303030303
          0303030303000000030303030303030303030303030303030303030303030303
          0303030303030303030303030303030303030303030303030303030303030303
          0303030303030303030303030303030303030303030303030303030303030303
          0303030303030303030303030303030303030303030303030303030303030303
          0303030303030303030303030303030303030303030303030303}
        NumGlyphs = 2
        ParentShowHint = False
        ShowHint = True
        OnClick = BrvSpeedButton2Click
        BrTipoBotao = BrBtnAbir
      end
      object LblPasta: TBrvRetCon
        Left = 125
        Top = 4
        Width = 507
        Height = 19
        TabStop = False
        BevelInner = bvLowered
        BevelKind = bkFlat
        BevelWidth = 2
        BorderStyle = bsNone
        ParentColor = True
        ReadOnly = True
        TabOrder = 0
      end
      object BotProces: TBrvBitBtn
        Left = 638
        Top = 1
        Width = 161
        Height = 25
        Caption = 'Processar pasta'
        Enabled = False
        Font.Charset = DEFAULT_CHARSET
        Font.Color = clWindowText
        Font.Height = -11
        Font.Name = 'Tahoma'
        Font.Style = [fsBold]
        NumGlyphs = 2
        ParentFont = False
        TabOrder = 1
        OnClick = BotProcesClick
        BrTipoBotao = BrBtnOk
      end
    end
    object DbgAtuali: TBrvDbGrid
      Left = 1
      Top = 30
      Width = 803
      Height = 267
      BrShowMemo = True
      BrReadOnlyStyle = [fsItalic]
      BrReadOnlyColor = clMaroon
      Align = alClient
      DataSource = DtsAplCli
      TabOrder = 1
      TitleFont.Charset = DEFAULT_CHARSET
      TitleFont.Color = clWindowText
      TitleFont.Height = -11
      TitleFont.Name = 'Tahoma'
      TitleFont.Style = []
      BrDrawColumn.Strings = (
        'N'#227'o remova essas duas linhas de formata'#231#227'o:'
        'CampoTabela;Operador;ValorComparativo;Cor;')
      Columns = <
        item
          Expanded = False
          FieldName = 'TpArquiv'
          ReadOnly = True
          Title.Caption = 'Tipo'
          Title.Font.Charset = DEFAULT_CHARSET
          Title.Font.Color = clWindowText
          Title.Font.Height = -11
          Title.Font.Name = 'Tahoma'
          Title.Font.Style = [fsBold]
          Width = 29
          Visible = True
          BrConsulta = 0
          BrPermissao = []
          BrValueHalfChecked = False
          BrSaveOnClick = False
        end
        item
          ButtonStyle = cbsNone
          Expanded = False
          FieldName = 'SnObriga'
          Title.Caption = 'Obrigar'
          Title.Font.Charset = DEFAULT_CHARSET
          Title.Font.Color = clBlue
          Title.Font.Height = -11
          Title.Font.Name = 'Tahoma'
          Title.Font.Style = [fsBold]
          Width = 44
          Visible = True
          BrConsulta = 0
          BrPermissao = []
          BrValueChecked = 'S'
          BrValueUnchecked = 'N'
          BrValueHalfChecked = False
          BrSaveOnClick = False
        end
        item
          ButtonStyle = cbsNone
          Expanded = False
          FieldName = 'SnCompac'
          Title.Caption = 'Compactar'
          Title.Font.Charset = DEFAULT_CHARSET
          Title.Font.Color = clBlue
          Title.Font.Height = -11
          Title.Font.Name = 'Tahoma'
          Title.Font.Style = [fsBold]
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
          FieldName = 'DtArquiv'
          ReadOnly = True
          Title.Caption = 'Data'
          Title.Font.Charset = DEFAULT_CHARSET
          Title.Font.Color = clWindowText
          Title.Font.Height = -11
          Title.Font.Name = 'Tahoma'
          Title.Font.Style = [fsBold]
          Width = 108
          Visible = True
          BrConsulta = 0
          BrPermissao = []
          BrValueHalfChecked = False
          BrSaveOnClick = False
        end
        item
          Expanded = False
          FieldName = 'NmArqCli'
          ReadOnly = True
          Title.Caption = 'Nome'
          Title.Font.Charset = DEFAULT_CHARSET
          Title.Font.Color = clWindowText
          Title.Font.Height = -11
          Title.Font.Name = 'Tahoma'
          Title.Font.Style = [fsBold]
          Width = 249
          Visible = True
          BrConsulta = 0
          BrPermissao = []
          BrValueHalfChecked = False
          BrSaveOnClick = False
        end
        item
          Expanded = False
          FieldName = 'NmZipFil'
          ReadOnly = True
          Title.Caption = 'Nome compactado'
          Title.Font.Charset = DEFAULT_CHARSET
          Title.Font.Color = clWindowText
          Title.Font.Height = -11
          Title.Font.Name = 'Tahoma'
          Title.Font.Style = [fsBold]
          Width = 270
          Visible = True
          BrConsulta = 0
          BrPermissao = []
          BrValueHalfChecked = False
          BrSaveOnClick = False
        end>
    end
    object FlbDireto: TFileListBox
      Left = 532
      Top = 194
      Width = 145
      Height = 97
      ItemHeight = 13
      TabOrder = 2
      Visible = False
    end
    object PnlProces: TPanel
      Left = 1
      Top = 297
      Width = 803
      Height = 41
      Align = alBottom
      TabOrder = 3
      object Panel3: TPanel
        Left = 1
        Top = 21
        Width = 801
        Height = 19
        Align = alBottom
        Alignment = taLeftJustify
        Caption = 'Processo de compacta'#231#227'o'
        Font.Charset = DEFAULT_CHARSET
        Font.Color = clWindowText
        Font.Height = -11
        Font.Name = 'Tahoma'
        Font.Style = [fsBold]
        ParentFont = False
        TabOrder = 0
        object PgbCompac: TProgressBar
          Left = 152
          Top = 1
          Width = 648
          Height = 17
          Align = alRight
          Anchors = [akLeft, akTop, akRight, akBottom]
          TabOrder = 0
        end
      end
      object Panel4: TPanel
        Left = 1
        Top = 1
        Width = 801
        Height = 20
        Align = alClient
        Alignment = taLeftJustify
        Caption = 'Processo geral'
        Font.Charset = DEFAULT_CHARSET
        Font.Color = clWindowText
        Font.Height = -11
        Font.Name = 'Tahoma'
        Font.Style = [fsBold]
        ParentFont = False
        TabOrder = 1
        object PgbGeral: TProgressBar
          Left = 152
          Top = 1
          Width = 648
          Height = 18
          Align = alRight
          Anchors = [akLeft, akTop, akRight, akBottom]
          Step = 1
          TabOrder = 0
        end
      end
    end
  end
  object CdsAplCli: TClientDataSet
    Aggregates = <>
    Filtered = True
    Params = <>
    BeforeInsert = CdsAplCliBeforeInsert
    Left = 240
    Top = 144
  end
  object DtsAplCli: TDataSource
    DataSet = CdsAplCli
    Left = 184
    Top = 142
  end
  object DlgDireto: TOpenDialog
    Left = 80
    Top = 110
  end
  object SevenZip: TSevenZip
    SFXCreate = False
    SFXModule = '7z.sfx'
    AddOptions = []
    ExtractOptions = []
    LZMACompressType = LZMA
    LZMACompressStrength = ULTRA
    LZMAStrength = 0
    LPPMDmem = 30
    LPPMDsize = 10
    NumberOfFiles = -1
    VolumeSize = 0
    OnProgress = SevenZipProgress
    OnPreProgress = SevenZipPreProgress
    Left = 576
    Top = 88
  end
end
