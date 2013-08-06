inherited Rel0004: TRel0004
  Left = 543
  Top = 137
  Caption = 'Rel0004 - Livro raz'#227'o cont'#225'bil'
  ClientHeight = 418
  ExplicitHeight = 445
  PixelsPerInch = 96
  TextHeight = 13
  inherited NavBarra: TBrvDbNavCop
    ExplicitWidth = 540
    inherited BrvSpeedButton1: TBrvSpeedButton
      Left = 543
      ExplicitLeft = 515
    end
    inherited SbtAjuda: TBrvSpeedButton
      Left = 518
      ExplicitLeft = 490
    end
  end
  inherited PnlFundo: TPanel
    Top = 182
    Height = 202
    Align = alBottom
    Visible = False
    ExplicitTop = 182
    ExplicitHeight = 202
    object Label7: TLabel
      Left = 8
      Top = 153
      Width = 83
      Height = 13
      Caption = 'Per'#237'odo inicial'
      Font.Charset = DEFAULT_CHARSET
      Font.Color = clBlue
      Font.Height = -11
      Font.Name = 'MS Sans Serif'
      Font.Style = [fsBold]
      ParentFont = False
    end
    object Label1: TLabel
      Left = 8
      Top = 9
      Width = 71
      Height = 13
      Caption = 'Conta inicial'
      Font.Charset = DEFAULT_CHARSET
      Font.Color = clWindowText
      Font.Height = -11
      Font.Name = 'MS Sans Serif'
      Font.Style = [fsBold]
      ParentFont = False
    end
    object Label5: TLabel
      Left = 8
      Top = 58
      Width = 62
      Height = 13
      Caption = 'Conta final'
      Font.Charset = DEFAULT_CHARSET
      Font.Color = clWindowText
      Font.Height = -11
      Font.Name = 'MS Sans Serif'
      Font.Style = [fsBold]
      ParentFont = False
    end
    object Label8: TLabel
      Left = 8
      Top = 129
      Width = 77
      Height = 13
      Caption = 'P'#225'gina inicial'
      Font.Charset = DEFAULT_CHARSET
      Font.Color = clBlue
      Font.Height = -11
      Font.Name = 'MS Sans Serif'
      Font.Style = [fsBold]
      ParentFont = False
    end
    object Label2: TLabel
      Left = 8
      Top = 177
      Width = 74
      Height = 13
      Caption = 'Per'#237'odo final'
      Font.Charset = DEFAULT_CHARSET
      Font.Color = clBlue
      Font.Height = -11
      Font.Name = 'MS Sans Serif'
      Font.Style = [fsBold]
      ParentFont = False
    end
    object Label3: TLabel
      Left = 8
      Top = 105
      Width = 92
      Height = 13
      Caption = 'Centro de Custo'
      Font.Charset = DEFAULT_CHARSET
      Font.Color = clWindowText
      Font.Height = -11
      Font.Name = 'MS Sans Serif'
      Font.Style = [fsBold]
      ParentFont = False
    end
    object BntLimConIni: TSpeedButton
      Left = 203
      Top = 3
      Width = 23
      Height = 22
      Hint = 'Limpar Conta de D'#233'bito'
      Glyph.Data = {
        DE010000424DDE01000000000000760000002800000024000000120000000100
        0400000000006801000000000000000000001000000000000000000000000000
        80000080000000808000800000008000800080800000C0C0C000808080000000
        FF0000FF000000FFFF00FF000000FF00FF00FFFF0000FFFFFF00333333333333
        333333333333333333333333000033338833333333333333333F333333333333
        0000333911833333983333333388F333333F3333000033391118333911833333
        38F38F333F88F33300003339111183911118333338F338F3F8338F3300003333
        911118111118333338F3338F833338F3000033333911111111833333338F3338
        3333F8330000333333911111183333333338F333333F83330000333333311111
        8333333333338F3333383333000033333339111183333333333338F333833333
        00003333339111118333333333333833338F3333000033333911181118333333
        33338333338F333300003333911183911183333333383338F338F33300003333
        9118333911183333338F33838F338F33000033333913333391113333338FF833
        38F338F300003333333333333919333333388333338FFF830000333333333333
        3333333333333333333888330000333333333333333333333333333333333333
        0000}
      NumGlyphs = 2
      ParentShowHint = False
      ShowHint = True
      OnClick = BntLimConIniClick
    end
    object BntLimConFim: TSpeedButton
      Left = 203
      Top = 52
      Width = 23
      Height = 22
      Hint = 'Limpar Conta de D'#233'bito'
      Glyph.Data = {
        DE010000424DDE01000000000000760000002800000024000000120000000100
        0400000000006801000000000000000000001000000000000000000000000000
        80000080000000808000800000008000800080800000C0C0C000808080000000
        FF0000FF000000FFFF00FF000000FF00FF00FFFF0000FFFFFF00333333333333
        333333333333333333333333000033338833333333333333333F333333333333
        0000333911833333983333333388F333333F3333000033391118333911833333
        38F38F333F88F33300003339111183911118333338F338F3F8338F3300003333
        911118111118333338F3338F833338F3000033333911111111833333338F3338
        3333F8330000333333911111183333333338F333333F83330000333333311111
        8333333333338F3333383333000033333339111183333333333338F333833333
        00003333339111118333333333333833338F3333000033333911181118333333
        33338333338F333300003333911183911183333333383338F338F33300003333
        9118333911183333338F33838F338F33000033333913333391113333338FF833
        38F338F300003333333333333919333333388333338FFF830000333333333333
        3333333333333333333888330000333333333333333333333333333333333333
        0000}
      NumGlyphs = 2
      ParentShowHint = False
      ShowHint = True
      OnClick = BntLimConFimClick
    end
    object EdtNrPagIni: TBrvEditNum
      Left = 112
      Top = 124
      Width = 28
      Height = 21
      TabOrder = 8
      Text = '1'
      BrvReadOnlyColor = 14541539
      BrAlignment = taRightJustify
      BrCasasDecimais = 0
      BrSepararMilhar = False
      BrAsFloat = 1.000000000000000000
      BrAsInteger = 1
      BrAsFloatSQL = '1'
      BrVisibleButton = False
      BrFunctionButton = TpConsulta
      BrDicionario = DmSrvApl.BrvDicionario
      BrQueryCode = 0
      BrRecordar = False
    end
    object CbxEmpDat: TCheckBox
      Left = 228
      Top = 146
      Width = 241
      Height = 17
      Caption = 'Imprimir empresa e data'
      Font.Charset = DEFAULT_CHARSET
      Font.Color = clWindowText
      Font.Height = -11
      Font.Name = 'MS Sans Serif'
      Font.Style = [fsBold]
      ParentFont = False
      ParentShowHint = False
      ShowHint = False
      TabOrder = 11
    end
    object CbxSalZer: TCheckBox
      Left = 228
      Top = 162
      Width = 241
      Height = 17
      Caption = 'Imprimir contas com saldos zerados'
      Font.Charset = DEFAULT_CHARSET
      Font.Color = clWindowText
      Font.Height = -11
      Font.Name = 'MS Sans Serif'
      Font.Style = [fsBold]
      ParentFont = False
      ParentShowHint = False
      ShowHint = False
      TabOrder = 12
    end
    object CbxTotDat: TCheckBox
      Left = 228
      Top = 177
      Width = 241
      Height = 17
      Caption = 'Totalizar por dia'
      Font.Charset = DEFAULT_CHARSET
      Font.Color = clWindowText
      Font.Height = -11
      Font.Name = 'MS Sans Serif'
      Font.Style = [fsBold]
      ParentFont = False
      ParentShowHint = False
      ShowHint = False
      TabOrder = 13
    end
    object EdtDtInicia: TBrvEditDate
      Left = 112
      Top = 149
      Width = 87
      Height = 21
      EditMask = '99/99/9999;1; '
      MaxLength = 10
      TabOrder = 9
      Text = '  /  /    '
      BrvReadOnlyColor = 14541539
      BrDataValida = False
      BrDataVazia = True
      BrFunctionButton = TVdData
      BrAlignment = taLeftJustify
      BrDicionario = DmSrvApl.BrvDicionario
      BrRecordar = False
    end
    object EdtDtFinal: TBrvEditDate
      Left = 112
      Top = 173
      Width = 87
      Height = 21
      EditMask = '99/99/9999;1; '
      MaxLength = 10
      TabOrder = 10
      Text = '  /  /    '
      BrvReadOnlyColor = 14541539
      BrDataValida = False
      BrDataVazia = True
      BrFunctionButton = TVdData
      BrAlignment = taLeftJustify
      BrDicionario = DmSrvApl.BrvDicionario
      BrRecordar = False
    end
    object EdtNrConIni: TBrvEditNum
      Left = 112
      Top = 4
      Width = 89
      Height = 21
      TabOrder = 0
      Text = '0'
      OnDragDrop = EdtNrConIniDragDrop
      OnDragOver = EdtNrConIniDragOver
      OnExit = EdtNrConIniExit
      BrvReadOnlyColor = 14541539
      BrAlignment = taRightJustify
      BrCasasDecimais = 0
      BrSepararMilhar = False
      BrAsInteger = 0
      BrAsFloatSQL = '0'
      BrVisibleButton = True
      BrFunctionButton = TpConsulta
      BrOnBeforeConsulta = EdtNrConIniBrOnBeforeConsulta
      BrDicionario = DmSrvApl.BrvDicionario
      BrQueryCode = 0
      BrRecordar = False
    end
    object LblDsConIni: TBrvRetCon
      Left = 228
      Top = 3
      Width = 304
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
    object EdtMasConIni: TMaskEdit
      Left = 112
      Top = 28
      Width = 89
      Height = 22
      Font.Charset = ANSI_CHARSET
      Font.Color = clWindowText
      Font.Height = -11
      Font.Name = 'Courier New'
      Font.Style = []
      ParentFont = False
      TabOrder = 1
      OnDragDrop = EdtNrConIniDragDrop
      OnDragOver = EdtNrConIniDragOver
      OnExit = EdtNrConIniExit
    end
    object EdtNrConFim: TBrvEditNum
      Left = 112
      Top = 53
      Width = 89
      Height = 21
      TabOrder = 3
      Text = '0'
      OnDragDrop = EdtNrConFimDragDrop
      OnDragOver = EdtNrConIniDragOver
      OnExit = EdtNrConFimExit
      BrvReadOnlyColor = 14541539
      BrAlignment = taRightJustify
      BrCasasDecimais = 0
      BrSepararMilhar = False
      BrAsInteger = 0
      BrAsFloatSQL = '0'
      BrVisibleButton = True
      BrFunctionButton = TpConsulta
      BrOnBeforeConsulta = EdtNrConFimBrOnBeforeConsulta
      BrDicionario = DmSrvApl.BrvDicionario
      BrQueryCode = 0
      BrRecordar = False
    end
    object LblDsConFim: TBrvRetCon
      Left = 228
      Top = 52
      Width = 304
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
    object EdtMasConFim: TMaskEdit
      Left = 112
      Top = 77
      Width = 89
      Height = 22
      Font.Charset = ANSI_CHARSET
      Font.Color = clWindowText
      Font.Height = -11
      Font.Name = 'Courier New'
      Font.Style = []
      ParentFont = False
      TabOrder = 4
      OnDragDrop = EdtNrConFimDragDrop
      OnDragOver = EdtNrConIniDragOver
      OnExit = EdtNrConFimExit
    end
    object EdtCdCenCus: TBrvEditNum
      Left = 112
      Top = 102
      Width = 89
      Height = 21
      TabOrder = 6
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
        'EdtCdCenCus;CdCenCus;S;S;'
        'LblDsCenCus;DsCenCus;S;N;')
      BrDicionario = DmSrvApl.BrvDicionario
      BrQueryCode = 35
      BrRecordar = False
    end
    object LblDsCenCus: TBrvRetCon
      Left = 206
      Top = 102
      Width = 326
      Height = 19
      TabStop = False
      BevelInner = bvLowered
      BevelKind = bkFlat
      BevelWidth = 2
      BorderStyle = bsNone
      ParentColor = True
      ReadOnly = True
      TabOrder = 7
    end
  end
  object Panel1: TPanel [2]
    Left = 0
    Top = 30
    Width = 568
    Height = 152
    Align = alClient
    BorderStyle = bsSingle
    TabOrder = 3
    ExplicitWidth = 540
    object GbxEmpres: TGroupBox
      Left = 1
      Top = 1
      Width = 562
      Height = 146
      Align = alClient
      Caption = 'Empresa'
      Font.Charset = DEFAULT_CHARSET
      Font.Color = clBlue
      Font.Height = -11
      Font.Name = 'MS Sans Serif'
      Font.Style = [fsBold]
      ParentFont = False
      TabOrder = 0
      ExplicitWidth = 534
      object ClbEmpres: TBrvCheckListBox
        Left = 2
        Top = 15
        Width = 558
        Height = 129
        Align = alClient
        Columns = 2
        Font.Charset = DEFAULT_CHARSET
        Font.Color = clWindowText
        Font.Height = -11
        Font.Name = 'MS Sans Serif'
        Font.Style = []
        ItemHeight = 13
        ParentFont = False
        TabOrder = 0
        ExplicitWidth = 530
      end
      object EdtCdEmpres: TBrvEditNum
        Left = 18
        Top = 36
        Width = 66
        Height = 21
        TabOrder = 1
        Text = '0'
        Visible = False
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
          'EdtDsEmpres;RsEmpres;S;N;'
          'LblNrPlano;NrPlano;S;N;')
        BrDicionario = DmSrvApl.BrvDicionario
        BrQueryCode = 12
        BrRecordar = False
      end
      object EdtDsEmpres: TBrvRetCon
        Left = 90
        Top = 36
        Width = 271
        Height = 19
        TabStop = False
        BevelInner = bvLowered
        BevelKind = bkFlat
        BevelWidth = 2
        BorderStyle = bsNone
        ParentColor = True
        ReadOnly = True
        TabOrder = 2
        Visible = False
      end
      object LblNrPlano: TBrvRetCon
        Left = 367
        Top = 36
        Width = 57
        Height = 19
        TabStop = False
        BevelInner = bvLowered
        BevelKind = bkFlat
        BevelWidth = 2
        BorderStyle = bsNone
        ParentColor = True
        ReadOnly = True
        TabOrder = 3
        Visible = False
      end
    end
  end
  inherited PnlRodape: TPanel
    Top = 384
    ExplicitLeft = -8
    ExplicitTop = 403
    ExplicitWidth = 540
    inherited BtnGeraRel: TBrvBitBtn
      Enabled = False
    end
    object BtnAbrCon: TBrvBitBtn
      Left = 226
      Top = 6
      Width = 105
      Height = 25
      Hint = 'Abrir'
      Anchors = [akTop, akRight]
      Caption = 'Abrir'
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
        800080800080800080800080800080800094C600C6FF00C0FF00C0FF00C0FF00
        C0FF00C0FF00C0FF00C0FF00C0FF00C0FF00C0FF00C0FF00C0FF00C6FF0095C8
        0080800080800080800080809C9C9CD1D1D1CCCCCCCACACACBCBCBCBCBCBCBCB
        CBCACACACBCBCBCACACACBCBCBCBCBCBCBCBCBCBCBCBD1D1D19D9D9D00808000
        80800080800083AC03BAF702AAE105B0EB04B0EA04B0EA04B0EA04B0EA04B0EA
        04B0EA04B0EA04B0EA04B0EA04B0EA04B0EA04B0EA05C8FF0080800080800080
        80898989C4C4C4B3B3B3B9B9B9B9B9B9B9B9B9BABABAB9B9B9B8B8B8B9B9B9B9
        B9B9B9B9B9B9B9B9B9B9B9B9B9B9B9B9B9D3D3D3008080008080008080008CB9
        00B0EB02A8DA12B6EF10B5ED10B5ED10B5ED10B5ED10B5ED10B5ED10B5ED10B5
        ED10B5ED10B5ED10B5ED10B5ED12C9FF008080008080008080929292B9B9B9AF
        AFAFBFBFBFBDBDBDBDBDBDBDBDBDBEBEBEBCBCBCBDBDBDBCBCBCBDBDBDBDBDBD
        BDBDBDBDBDBDBDBDBDD3D3D3008080008080008080038FBF03B6F309ABDE23BE
        F520BCF320BCF320BCF320BCF320BCF320BCF320BCF320BCF320BCF320BCF320
        BCF320BCF324D1FF008080008080008080979797BFBFBFB2B2B2C6C6C6C3C3C3
        C4C4C4C4C4C4C4C4C4C4C4C4C4C4C4C4C4C4C5C5C5C4C4C4C4C4C4C3C3C3C5C5
        C5D9D9D90080800080800080800A91C00BB9F510ADE036C5FA32C3F732C3F732
        C3F732C3F732C3F732C3F732C3F732C3F732C3F732C3F732C3F732C3F737D8FF
        008080008080008080999999C2C2C2B4B4B4CDCDCDCACACACACACACACACACACA
        CACACACACACACACBCBCBCACACACACACACBCBCBCACACACACACAE1E1E100808000
        80800080801295C315BDF81AB1E349CEFE44CBFC44CBFC44CBFC44CBFC44CBFC
        44CBFC44CBFC44CBFC44CBFC44CBFC44CBFC44CBFC4CE1FF0080800080800080
        809B9B9BC6C6C6B8B8B8D4D4D4D0D0D0D1D1D1D1D1D1D1D1D1D0D0D0D1D1D1D0
        D0D0D1D1D1D1D1D1D0D0D0D1D1D1D1D1D1E8E8E80080800080800080801C9AC5
        22C3FC24B5E55AD4FF55D1FF55D1FF55D1FF55D1FF55D1FF55D1FF55D1FF55D1
        FF55D1FF55D1FF55D1FF55D1FF5EE8FF0080800080800080809F9F9FCCCCCCBC
        BCBCD9D9D9D7D7D7D6D6D6D7D7D7D6D6D6D7D7D7D7D7D7D7D7D7D7D7D7D6D6D6
        D7D7D7D7D7D7D7D7D7EEEEEE008080008080008080269DC82FC8FF2CB9E766D7
        FF60D4FF60D4FF60D4FF60D4FF60D4FF60D4FF60D4FF60D4FF60D4FF60D4FF60
        D4FF60D4FF6BECFF008080008080008080A3A3A3D1D1D1BFBFBFDBDBDBDADADA
        D9D9D9D9D9D9D9D9D9D9D9D9D9D9D9D9D9D9D9D9D9D9D9D9D9D9D9DADADAD8D8
        D8F1F1F100808000808000808032A2CC3ED0FF38BDE976DCFF70D9FF70D9FF70
        D9FF70D9FF70D9FF70D9FF70D9FF70D9FF70D9FF70D9FF70D9FF70D9FF7CF0FF
        008080008080008080A8A8A8D6D6D6C3C3C3DFDFDFDCDCDCDEDEDEDDDDDDDDDD
        DDDDDDDDDDDDDDDCDCDCDEDEDEDCDCDCDDDDDDDCDCDCDDDDDDF5F5F500808000
        80800080803BA6CD4BD4FF40C0EB82E0FF7BDCFF7BDCFF7BDCFF7BDCFF7BDCFF
        7BDCFF7BDCFF7BDCFF7BDCFF7BDCFF7BDCFF7BDCFF89F4FF0080800080800080
        80ABABABDADADAC5C5C5E2E2E2DFDFDFE0E0E0E0E0E0DFDFDFE0E0E0E0E0E0DF
        DFDFDFDFDFE0E0E0DFDFDFE0E0E0DFDFDFF8F8F800808000808000808045AACF
        58D8FF48C2EB90E2FF8BE0FF8BE0FF8BE0FF8BE0FF8BE0FF8BE0FF8BE0FF8BE0
        FF8BE0FF8BE0FF8BE0FF8DE2FFA2FFFF008080008080008080AFAFAFDFDFDFC6
        C6C6E5E5E5E3E3E3E3E3E3E3E3E3E2E2E2E3E3E3E3E3E3E3E3E3E3E3E3E3E3E3
        E2E2E2E3E3E3E4E4E4FFFFFF0080800080800080804DACCE63DCFF47C4EE5ECA
        ED84DBF981D9F881D9F881D9F881D9F881D9F881D9F881D9F881D9F881D9F882
        DBFA7DE2FF3A5C67008080008080008080B0B0B0E1E1E1C8C8C8CDCDCDDDDDDD
        DDDDDDDCDCDCDCDCDCDCDCDCDCDCDCDCDCDCDDDDDDDCDCDCDCDCDCDDDDDDE5E5
        E55D5D5D00808000808000808054AFCE6BDEFF67D7FF59CEF555CCF455CCF455
        CCF455CCF455CCF455CCF455CCF455CCF455CCF455CCF460DFFF074859008080
        008080008080008080B2B2B2E2E2E2DBDBDBD2D2D2D0D0D0D0D0D0D0D0D0D0D0
        D0D0D0D0D0D0D0CFCFCFD0D0D0D0D0D0D0D0D0E4E4E449494900808000808000
        80800080805DB1CE75E0FF72D9FF73DAFF73DAFF73DAFF73DAFF7EECFF7FEFFF
        7FEFFF7FEFFF7FEFFF7FEFFF81F3FF74DAFF0080800080800080800080800080
        80B3B3B3E4E4E4DDDDDDDEDEDEDEDEDEDEDEDEDEDEDEF0F0F0F3F3F3F3F3F3F3
        F3F3F3F3F3F3F3F3F7F7F7DEDEDE00808000808000808000808000808064B2CE
        7FE2FF7BDBFF7BDBFF7BDBFF7BDBFF85EEFF0080800080800080800080800080
        80008080008080008080008080008080008080008080008080B5B5B5E6E6E6DE
        DEDEE0E0E0DFDFDFDEDEDEF2F2F2008080008080008080008080008080008080
        0080800080800080800080800080800080800080806EB9D388E5FF84DEFF84DE
        FF84DEFF84DEFF95FAFF00808000808000808000808000808000808000808000
        8080008080008080008080008080008080BCBCBCE8E8E8E1E1E1E1E1E1E1E1E1
        E1E1E1FDFDFD0080800080800080800080800080800080800080800080800080
        800080800080800080800080805E95A9A0FEFF99F2FF99F2FF99F2FF9BF6FF91
        E7FF008080008080008080008080008080008080008080008080008080008080
        008080008080008080979797FFFFFFF4F4F4F5F5F5F6F6F6F8F8F8EAEAEA0080
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
      OnClick = BtnAbrConClick
      BrTipoBotao = BrBtnAbir
    end
    object BtnFecCon: TBrvBitBtn
      Left = 337
      Top = 5
      Width = 105
      Height = 25
      Hint = 'Cancelar'
      Anchors = [akTop, akRight]
      Caption = 'Fechar'
      DoubleBuffered = True
      Enabled = False
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
      OnClick = BtnFecConClick
      BrTipoBotao = BrBtnCancel
    end
  end
  inherited PopCfgFrm: TPopupMenu
    Left = 21
    Top = 102
  end
  inherited ImlPopFrm: TImageList
    Left = 69
    Top = 102
  end
  inherited LspS049: TBrvListParam
    Left = 117
    Top = 102
  end
  inherited BrvGerRel: TBrvGeraRelatorio
    BrCdsData = TblTemp
    Left = 165
    Top = 102
  end
  object TblTemp: TClientDataSet
    Aggregates = <>
    Params = <>
    Left = 269
    Top = 102
    object TblTempNrPagIni: TIntegerField
      FieldName = 'NrPagIni'
    end
    object TblTempDsMasLim: TStringField
      FieldName = 'DsMasLim'
      Size = 90
    end
    object TblTempDsMasIni: TStringField
      FieldName = 'DsMasIni'
      Size = 90
    end
    object TblTempNrConIni: TIntegerField
      FieldName = 'NrConIni'
    end
    object TblTempDsMasFim: TStringField
      FieldName = 'DsMasFim'
      Size = 90
    end
    object TblTempNrConFim: TIntegerField
      FieldName = 'NrConFim'
    end
    object TblTempCdEmpres: TStringField
      FieldName = 'CdEmpres'
      Size = 90
    end
    object TblTempDtInicia: TDateField
      FieldName = 'DtInicia'
    end
    object TblTempDtFinal: TDateField
      FieldName = 'DtFinal'
    end
    object TblTempCdCenCus: TIntegerField
      FieldName = 'CdCenCus'
    end
    object TblTempDsCencus: TStringField
      FieldName = 'DsCencus'
      Size = 90
    end
    object TblTempSnEmpDat: TBooleanField
      FieldName = 'SnEmpDat'
    end
    object TblTempSnSalZer: TBooleanField
      FieldName = 'SnSalZer'
    end
    object TblTempNrPlano: TStringField
      FieldName = 'NrPlano'
      Size = 10
    end
    object TblTempSnTotDat: TBooleanField
      FieldName = 'SnTotDat'
    end
  end
  object CdsPlaCon: TBrvClientDataSet
    Aggregates = <>
    Params = <>
    BrShowFieldName = False
    BrQueryCode = 0
    BrDicionario = DmSrvApl.BrvDicionario
    BrType = VqNormal
    BrGravaLog = True
    BrUserCode = 0
    Left = 221
    Top = 102
  end
end
