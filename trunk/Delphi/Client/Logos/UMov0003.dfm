inherited Mov0003: TMov0003
  Left = 307
  Top = 168
  Caption = 'Mov0003 - Controle de Atendimentos'
  ClientHeight = 536
  ClientWidth = 932
  KeyPreview = True
  OnKeyDown = FormKeyDown
  ExplicitTop = -80
  ExplicitWidth = 940
  ExplicitHeight = 563
  PixelsPerInch = 96
  TextHeight = 13
  inherited NavBarra: TBrvDbNavCop
    Width = 932
    ExplicitWidth = 932
    inherited BrvSpeedButton1: TBrvSpeedButton
      Left = 905
      ExplicitLeft = 777
    end
    inherited SbtAjuda: TBrvSpeedButton
      Left = 880
      ExplicitLeft = 752
    end
  end
  inherited PnlFundo: TPanel
    Width = 932
    Height = 506
    ExplicitWidth = 932
    ExplicitHeight = 506
    object pnlHeader: TPanel
      AlignWithMargins = True
      Left = 4
      Top = 4
      Width = 920
      Height = 31
      Align = alTop
      BevelEdges = [beBottom]
      BevelKind = bkTile
      BevelOuter = bvNone
      TabOrder = 0
      DesignSize = (
        920
        29)
      object lblAtendimento: TLabel
        Left = 3
        Top = 6
        Width = 71
        Height = 13
        Anchors = [akLeft]
        Caption = 'Atendimento'
        Font.Charset = DEFAULT_CHARSET
        Font.Color = clBlack
        Font.Height = -11
        Font.Name = 'MS Sans Serif'
        Font.Style = [fsBold]
        ParentFont = False
      end
      object BrvLabel1: TBrvLabel
        Left = 170
        Top = 6
        Width = 47
        Height = 13
        Caption = 'BrvLabel1'
        Visible = False
      end
      object BtnProsse: TBrvBitBtn
        Left = 815
        Top = 1
        Width = 100
        Height = 25
        Hint = 'Prosseguir'
        Align = alCustom
        Anchors = [akTop, akRight]
        Caption = 'Prosseguir'
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
        TabOrder = 1
        OnClick = BtnProsseClick
        BrTipoBotao = BrBtnOk
      end
      object EdtNrAtendi: TBrvEditNum
        Left = 80
        Top = 2
        Width = 80
        Height = 21
        TabOrder = 0
        Text = '0'
        BrAlignment = taRightJustify
        BrCasasDecimais = 0
        BrSepararMilhar = False
        BrAsInteger = 0
        BrAsFloatSQL = '0'
        BrVisibleButton = True
        BrFunctionButton = TpConsulta
        BrConfigurar.Strings = (
          'EdtNrAtendi;NrAtendi;S;S;')
        BrDicionario = DmSrvApl.BrvDicionario
        BrQueryCode = 21
        BrRecordar = False
      end
    end
    object pnlBody: TPanel
      AlignWithMargins = True
      Left = 4
      Top = 41
      Width = 920
      Height = 457
      Align = alClient
      BevelKind = bkTile
      BevelOuter = bvNone
      PopupMenu = PopLog
      TabOrder = 1
      Visible = False
      ExplicitTop = 37
      ExplicitHeight = 461
      object pnlAtend: TPanel
        AlignWithMargins = True
        Left = 3
        Top = 3
        Width = 910
        Height = 220
        Align = alTop
        BevelEdges = [beBottom]
        BevelKind = bkSoft
        BevelOuter = bvNone
        TabOrder = 0
        DesignSize = (
          910
          218)
        object lblDescricao: TLabel
          Left = 0
          Top = 111
          Width = 58
          Height = 13
          Caption = 'Descri'#231#227'o'
          Font.Charset = DEFAULT_CHARSET
          Font.Color = clBlack
          Font.Height = -11
          Font.Name = 'MS Sans Serif'
          Font.Style = [fsBold]
          ParentFont = False
        end
        object lblnicio: TLabel
          Left = 480
          Top = 57
          Width = 34
          Height = 13
          Anchors = [akTop, akRight]
          Caption = 'In'#237'cio'
          Font.Charset = DEFAULT_CHARSET
          Font.Color = clBlack
          Font.Height = -11
          Font.Name = 'MS Sans Serif'
          Font.Style = [fsBold]
          ParentFont = False
        end
        object Label1: TLabel
          Left = 707
          Top = 57
          Width = 46
          Height = 13
          Anchors = [akTop, akRight]
          Caption = 'T'#233'rmino'
          Font.Charset = DEFAULT_CHARSET
          Font.Color = clBlack
          Font.Height = -11
          Font.Name = 'MS Sans Serif'
          Font.Style = [fsBold]
          ParentFont = False
        end
        object Label2: TLabel
          Left = 0
          Top = 5
          Width = 49
          Height = 13
          Caption = 'Empresa'
          Font.Charset = DEFAULT_CHARSET
          Font.Color = clBlue
          Font.Height = -11
          Font.Name = 'MS Sans Serif'
          Font.Style = [fsBold]
          ParentFont = False
        end
        object lblTxt: TLabel
          Left = 0
          Top = 132
          Width = 171
          Height = 13
          Caption = 'Detalhamento do Atendimento'
          Font.Charset = DEFAULT_CHARSET
          Font.Color = clBlack
          Font.Height = -11
          Font.Name = 'MS Sans Serif'
          Font.Style = [fsBold]
          ParentFont = False
        end
        object Label3: TLabel
          Left = 480
          Top = 5
          Width = 26
          Height = 13
          Anchors = [akTop, akRight]
          Caption = 'Tipo'
          Font.Charset = DEFAULT_CHARSET
          Font.Color = clBlue
          Font.Height = -11
          Font.Name = 'MS Sans Serif'
          Font.Style = [fsBold]
          ParentFont = False
        end
        object Label4: TLabel
          Left = 0
          Top = 31
          Width = 34
          Height = 13
          Caption = 'Carga'
          Font.Charset = DEFAULT_CHARSET
          Font.Color = clBlack
          Font.Height = -11
          Font.Name = 'MS Sans Serif'
          Font.Style = [fsBold]
          ParentFont = False
        end
        object Label5: TLabel
          Left = 480
          Top = 31
          Width = 45
          Height = 13
          Anchors = [akTop, akRight]
          Caption = 'Ve'#237'culo'
          Font.Charset = DEFAULT_CHARSET
          Font.Color = clBlack
          Font.Height = -11
          Font.Name = 'MS Sans Serif'
          Font.Style = [fsBold]
          ParentFont = False
        end
        object Label6: TLabel
          Left = 0
          Top = 57
          Width = 53
          Height = 13
          Caption = 'Motorista'
          Font.Charset = DEFAULT_CHARSET
          Font.Color = clBlack
          Font.Height = -11
          Font.Name = 'MS Sans Serif'
          Font.Style = [fsBold]
          ParentFont = False
        end
        object Label7: TLabel
          Left = 0
          Top = 84
          Width = 44
          Height = 13
          Caption = 'Usu'#225'rio'
          Font.Charset = DEFAULT_CHARSET
          Font.Color = clBlack
          Font.Height = -11
          Font.Name = 'MS Sans Serif'
          Font.Style = [fsBold]
          ParentFont = False
        end
        object Label8: TLabel
          Left = 480
          Top = 84
          Width = 51
          Height = 13
          Anchors = [akTop, akRight]
          Caption = 'Situa'#231#227'o'
          Font.Charset = DEFAULT_CHARSET
          Font.Color = clBlack
          Font.Height = -11
          Font.Name = 'MS Sans Serif'
          Font.Style = [fsBold]
          ParentFont = False
        end
        object EdtDsAtendi: TBrvDbEdit
          Left = 75
          Top = 108
          Width = 835
          Height = 21
          Anchors = [akLeft, akTop, akRight]
          DataField = 'DsAtendi'
          DataSource = DsT010
          ParentShowHint = False
          ShowHint = False
          TabOrder = 8
          BrAlignment = taLeftJustify
          BrVisibleButton = False
          BrFunctionButton = TpDbConsulta
          BrDicionario = DmSrvApl.BrvDicionario
          BrQueryConsulta = 0
        end
        object EdtDtAbertu: TBrvDbEdit
          Left = 536
          Top = 54
          Width = 135
          Height = 21
          Anchors = [akTop, akRight]
          DataField = 'DtAbertu'
          DataSource = DsT010
          TabOrder = 6
          BrAlignment = taRightJustify
          BrVisibleButton = True
          BrFunctionButton = TpDbDataHora
          BrDicionario = DmSrvApl.BrvDicionario
          BrQueryConsulta = 0
        end
        object EdtDtFecham: TBrvDbEdit
          Left = 775
          Top = 54
          Width = 135
          Height = 21
          Anchors = [akTop, akRight]
          DataField = 'DtFecham'
          DataSource = DsT010
          TabOrder = 7
          BrAlignment = taRightJustify
          BrVisibleButton = True
          BrFunctionButton = TpDbDataHora
          BrDicionario = DmSrvApl.BrvDicionario
          BrQueryConsulta = 0
        end
        object EdtCdEmpres: TBrvDbEdit
          Left = 75
          Top = 2
          Width = 80
          Height = 21
          DataField = 'CdEmpres'
          DataSource = DsT010
          TabOrder = 0
          BrAlignment = taRightJustify
          BrVisibleButton = True
          BrFunctionButton = TpDbConsulta
          BrOnBeforeConsulta = EdtCdEmpresBrOnBeforeConsulta
          BrConfigurar.Strings = (
            'CdEmpres;CdEmpres;S;S;'
            'RsEmpres;RsEmpres;S;N;')
          BrDicionario = DmSrvApl.BrvDicionario
          BrQueryConsulta = 12
        end
        object RedTxAtendi: TDBRichEdit
          AlignWithMargins = True
          Left = 0
          Top = 146
          Width = 910
          Height = 69
          Margins.Left = 0
          Margins.Right = 0
          Align = alBottom
          DataField = 'TxAtendi'
          DataSource = DsT010
          Font.Charset = ANSI_CHARSET
          Font.Color = clWindowText
          Font.Height = -11
          Font.Name = 'Tahoma'
          Font.Style = []
          ParentFont = False
          ScrollBars = ssVertical
          TabOrder = 9
        end
        object EdtDsEmpr: TBrvDBRetCon
          Left = 160
          Top = 2
          Width = 309
          Height = 21
          TabStop = False
          Anchors = [akLeft, akTop, akRight]
          BevelInner = bvLowered
          BevelKind = bkFlat
          BevelWidth = 2
          BorderStyle = bsNone
          DataField = 'rsempres'
          DataSource = DsT010
          ParentColor = True
          ReadOnly = True
          TabOrder = 1
        end
        object EdtCdTipAte: TBrvDbEdit
          Left = 536
          Top = 2
          Width = 80
          Height = 21
          Anchors = [akTop, akRight]
          DataField = 'CdTipAte'
          DataSource = DsT010
          TabOrder = 2
          BrAlignment = taRightJustify
          BrVisibleButton = True
          BrFunctionButton = TpDbConsulta
          BrOnBeforeConsulta = EdtCdEmpresBrOnBeforeConsulta
          BrConfigurar.Strings = (
            'CdTipAte;CdTipAte;S;S;'
            'DsTipAte;DsTipAte;S;N;')
          BrDicionario = DmSrvApl.BrvDicionario
          BrQueryConsulta = 23
        end
        object EdtDsTipAte: TBrvDBRetCon
          Left = 622
          Top = 2
          Width = 288
          Height = 21
          TabStop = False
          Anchors = [akTop, akRight]
          BevelInner = bvLowered
          BevelKind = bkFlat
          BevelWidth = 2
          BorderStyle = bsNone
          DataField = 'DsTipAte'
          DataSource = DsT010
          ParentColor = True
          ReadOnly = True
          TabOrder = 3
        end
        object EdtCdCarga: TBrvDbEdit
          Left = 75
          Top = 28
          Width = 80
          Height = 21
          DataField = 'CdCarga'
          DataSource = DsT010
          TabOrder = 4
          BrAlignment = taRightJustify
          BrVisibleButton = True
          BrFunctionButton = TpDbConsulta
          BrOnBeforeConsulta = EdtCdEmpresBrOnBeforeConsulta
          BrConfigurar.Strings = (
            'CdCarga;CdCarga;S;S;'
            'DsDesCar;DsDesCar;S;N;'
            'CdMotori;CdMotori;S;N;'
            'NmMotori;NmMotori;S;N;'
            'CdVeicul;CdVeicul;S;N;')
          BrDicionario = DmSrvApl.BrvDicionario
          BrQueryConsulta = 24
        end
        object EdtDsDescar: TBrvDBRetCon
          Left = 161
          Top = 28
          Width = 308
          Height = 21
          TabStop = False
          Anchors = [akLeft, akTop, akRight]
          BevelInner = bvLowered
          BevelKind = bkFlat
          BevelWidth = 2
          BorderStyle = bsNone
          DataField = 'DSDESCAR'
          DataSource = DsT010
          ParentColor = True
          ReadOnly = True
          TabOrder = 5
        end
        object DbEdtCdVeicul: TBrvDBRetCon
          Left = 536
          Top = 28
          Width = 80
          Height = 21
          TabStop = False
          Anchors = [akTop, akRight]
          BevelInner = bvLowered
          BevelKind = bkFlat
          BevelWidth = 2
          BorderStyle = bsNone
          DataField = 'CdVeicul'
          DataSource = DsT010
          ParentColor = True
          ReadOnly = True
          TabOrder = 10
        end
        object DbEdtNmMotori: TBrvDBRetCon
          Left = 161
          Top = 54
          Width = 308
          Height = 21
          TabStop = False
          Anchors = [akLeft, akTop, akRight]
          BevelInner = bvLowered
          BevelKind = bkFlat
          BevelWidth = 2
          BorderStyle = bsNone
          DataField = 'NmMotori'
          DataSource = DsT010
          ParentColor = True
          ReadOnly = True
          TabOrder = 11
        end
        object DbEdtCdMotori: TBrvDBRetCon
          Left = 75
          Top = 54
          Width = 80
          Height = 21
          TabStop = False
          BevelInner = bvLowered
          BevelKind = bkFlat
          BevelWidth = 2
          BorderStyle = bsNone
          DataField = 'CdMotori'
          DataSource = DsT010
          ParentColor = True
          ReadOnly = True
          TabOrder = 12
        end
        object DbEdtdsVeicul: TBrvDBRetCon
          Left = 622
          Top = 28
          Width = 288
          Height = 21
          TabStop = False
          Anchors = [akTop, akRight]
          BevelInner = bvLowered
          BevelKind = bkFlat
          BevelWidth = 2
          BorderStyle = bsNone
          DataField = 'DsVeicul'
          DataSource = DsT010
          ParentColor = True
          ReadOnly = True
          TabOrder = 13
        end
        object DbEdtCdUsuari: TBrvDbEdit
          Left = 75
          Top = 81
          Width = 80
          Height = 21
          DataField = 'CdUsuari'
          DataSource = DsT010
          ParentColor = True
          ReadOnly = True
          TabOrder = 14
          BrAlignment = taRightJustify
          BrVisibleButton = False
          BrFunctionButton = TpDbConsulta
          BrOnBeforeConsulta = EdtCdEmpresBrOnBeforeConsulta
          BrConfigurar.Strings = (
            'CdCarga;CdCarga;S;S;'
            'DsDesCar;DsDesCar;S;N;'
            'CdMotori;CdMotori;S;N;'
            'NmMotori;NmMotori;S;N;'
            'CdVeicul;CdVeicul;S;N;')
          BrDicionario = DmSrvApl.BrvDicionario
          BrQueryConsulta = 24
        end
        object DbEdtNmUsuari: TBrvDBRetCon
          Left = 161
          Top = 81
          Width = 308
          Height = 21
          TabStop = False
          Anchors = [akLeft, akTop, akRight]
          BevelInner = bvLowered
          BevelKind = bkFlat
          BevelWidth = 2
          BorderStyle = bsNone
          DataField = 'NmComUsu'
          DataSource = DsT010
          ParentColor = True
          ReadOnly = True
          TabOrder = 15
        end
        object BrvDBRetCon1: TBrvDBRetCon
          Left = 536
          Top = 81
          Width = 135
          Height = 21
          TabStop = False
          Anchors = [akTop, akRight]
          BevelInner = bvLowered
          BevelKind = bkFlat
          BevelWidth = 2
          BorderStyle = bsNone
          DataField = 'DsStAten'
          DataSource = DsT010
          ParentColor = True
          ReadOnly = True
          TabOrder = 16
        end
      end
      object pnlFooter: TPanel
        AlignWithMargins = True
        Left = 3
        Top = 419
        Width = 910
        Height = 31
        Align = alBottom
        BevelEdges = [beTop]
        BevelKind = bkTile
        BevelOuter = bvNone
        TabOrder = 2
        object BtnVoltar: TBrvBitBtn
          Left = 810
          Top = 4
          Width = 100
          Height = 25
          Align = alCustom
          Anchors = [akTop, akRight]
          Caption = 'Voltar'
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
          TabOrder = 3
          OnClick = BtnVoltarClick
          BrTipoBotao = BrBtnSetaEsquerda
        end
        object BtnGravar: TBrvBitBtn
          Left = 706
          Top = 4
          Width = 100
          Height = 25
          Hint = 'Salvar'
          Align = alCustom
          Anchors = [akTop, akRight]
          Caption = 'Gravar'
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
          TabOrder = 2
          OnClick = BtnGravarClick
          BrTipoBotao = BrBtnSalvar
        end
        object BtnEmail: TBrvBitBtn
          Left = 0
          Top = 4
          Width = 100
          Height = 25
          Hint = 'Imprimir'
          Align = alCustom
          Caption = 'Imprimir'
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
            80008080008080F0D8DCD3C4C6C1AAA9066E6E4C8787D9C5C5F6E2E2E9D6D6C3
            C0C0008080008080008080008080008080008080008080008080008080008080
            65A0A0EFDADAD2C5C5C1A9A9066E6E4C8787D9C5C5F3E5E5E7D8D8C3C0C00080
            80008080008080008080008080008080008080008080008080E5D4D4F9F3F3FF
            FFFFD0CCCCBDB8B8443B3A443D3D6565658F8C8CBBB5B5DEDCDCF0E1E1AEC9C9
            008080008080008080008080008080008080008080E6D3D3FAF3F3FFFFFFCECE
            CEBABABA453A3A443D3D6565658D8D8DB8B8B8DEDCDCF0E1E1AEC9C900808000
            8080008080008080008080BEBEBEF7EBEBFEF8F8FFFFFFFFFEFECAC8C8BCB9B9
            766F763E3F3E2A2A2A262626393A3A656565969696C8BEBE0080800080800080
            80008080008080BEBEBEF6ECECFBFBFBFFFFFFFFFFFFC9C9C9BBBBBB7272723E
            3E3E2A2A2A2626263A3A3A656565969696C8BDBD008080008080008080ABACAC
            DBDEDEFCF9F9FEFFFFFFFEFEE3E4E4BCBCBC9D9D9DA3A3A3B0B0B1B4B1B1A29E
            9E7778784D4D4D212121202020807070008080008080008080ABACACDBDEDEFC
            F9F9FFFFFFFEFEFEE4E4E4BCBCBC9D9D9DA3A3A3B0B0B0B3B3B3A0A0A0777777
            4D4D4D2121212020207D7272008080008080008080D1C1C1FFFEFEF1F0F0D8D9
            D9AEADADAAA3A3CBCCCCC7C7C7A9A9A9A29A9A9C9A9AA9A0A0B7B5B5BFC0C0AD
            ADAD848484998A8A008080008080008080D1C1C1FFFEFEF0F0F0D8D8D8AEAEAE
            A6A6A6CCCCCCC7C7C7A9A9A99E9E9E9A9A9AA5A5A5B6B6B6C0C0C0ADADAD8484
            84978C8C008080008080008080C5B7B7D5D0D0BBB4B4ABABABCBC5C5DCDDDDE5
            E3E3F2F2F2EAEBEBD4D5D5C6C1C1B2B4B4AAA5A59E9D9DA6A3A3B1B0B0BAAEAE
            008080008080008080C5B7B7D2D2D2B7B7B7AAAAAAC8C8C8DDDDDDE4E4E4F2F2
            F2EBEBEBD5D5D5C4C4C4B3B3B3A7A7A79E9E9EA4A4A4B0B0B0B9AEAE00808000
            8080008080B9AAAAB6B4B4C3C4C4D8D8D8DCDDDDD5D6D6EDEAEAE9E8E8E4DAE5
            E5E5E5E9EAEAE4E4E4D7D7D7C8C9C9BBB6B6B1B0B0B3A5A50080800080800080
            80B8ABABB5B5B5C3C3C3D8D8D8DCDCDCD5D5D5EBEBEBE8E8E8DFDFDFE5E5E5E9
            E9E9E4E4E4D7D7D7C8C8C8B8B8B8B0B0B0B1A7A7008080008080008080CDB7B7
            D8D8D8D8D8D8D6D6D6D3D3D3E6E6E6E8E8E8B5B7B589B989B8B4B8B4B6B7C1C4
            C4CECFCFDADBDBE1E1E1DBDBDBCCC2C2008080008080008080CEB7B7D8D8D8D8
            D8D8D6D6D6D3D3D3E6E6E6E8E8E8B6B6B6A1A1A1B6B6B6B5B5B5C2C2C2CFCFCF
            DADADAE1E1E1DBDBDBCCC2C2008080008080008080008080EDDBDBE1DEDED5D5
            D5EDEDEDDCDCDCB0B0B0DBD8DBC9F6C9EBE9EDE6D1CED4B3AEAFACACAAA1A1B0
            B1B1C6C5C5D7C3C3008080008080008080008080EDDBDBE1DEDED5D5D5EDEDED
            DCDCDCB0B0B0D9D9D9DEDEDEEAEAEADADADAC2C2C2AEAEAEA5A5A5B1B1B1C6C5
            C5D7C3C3008080008080008080008080008080B2B9B9D1D0D0BBBBBBB1ADADE5
            E0E0F7F8F8FBF7FBEFEFEFE7E9E9EAEDEEE8E8E8DEDFDFCDCBCBAFA1A1008080
            008080008080008080008080008080B2B8B8D0D0D0BBBBBBB0B0B0E3E3E3F7F7
            F7FAFAFAEFEFEFE8E8E8ECECECE8E8E8DFDFDFCECCCCACA5A500808000808000
            8080008080008080008080008080959393DAD9D9E6E8E9B8BABBB0B2B2CED0D0
            E1E2E3E0E1E1E1E2E2E0E0E0DCD5D5CBC0C00080800080800080800080800080
            80008080008080008080909797DBD8D8E8E8E8BABABAB1B1B1CFCFCFE2E2E2E1
            E1E1E1E1E1E0E0E0DCD5D5CAC2C2008080008080008080008080008080008080
            008080008080008080FFFCFCFBEFE8E7D9D2D9CBC8DAD0CDE5DBD8DDDADCDADB
            DCDFD2D25E969600808000808000808000808000808000808000808000808000
            8080008080008080F1F1F1DCDCDCD0D0D0D3D3D3DEDEDEDDDDDDDBDBDBDFD2D2
            5E96960080800080800080800080800080800080800080800080800080800080
            80FFD8D0FFE7DCFFE0D3FFDCCEFFD4C5FECCBEFBCDBFFFDAD07CACAD00808000
            8080008080008080008080008080008080008080008080008080008080F4E4E4
            EFEFEFEAEAEAE6E6E6E3E3E3DEDEDEDEDEDEF4E7E779AFAF0080800080800080
            80008080008080008080008080008080008080008080008080FFD0C6FFE7DFFF
            DDD1FFD9C8FFCEBCFFC4B2FFC2A9899E95008080008080008080008080008080
            008080008080008080008080008080008080008080EEDEDEF0F0F0E7E7E7E2E2
            E2DEDEDED8D8D8DDD6D672ABAB00808000808000808000808000808000808000
            8080008080008080008080008080008080FFDAD2FFE7DEFFDDD2FFD9C9FFCEBD
            FFC5B3FFC4AB7899920080800080800080800080800080800080800080800080
            80008080008080008080008080F3E3E3F0F0F0E8E8E8E2E2E2DFDFDFD8D8D8DF
            D7D764A5A5008080008080008080008080008080008080008080008080008080
            008080008080008080FFD8CFFFE7DEFFDDD2FFD9C9FFCEBDFFC4B3FFC6B583A0
            9E00808000808000808000808000808000808000808000808000808000808000
            8080008080F1E1E1F0F0F0E8E8E8E2E2E2DFDFDFD8D8D8E7DDDD72AEAE008080
            0080800080800080800080800080800080800080800080800080800080800080
            80FFE7DDFFE5DCFFDDD2FFD9C9FFCEBDFFCAB5D9B4AF00808000808000808000
            8080008080008080008080008080008080008080008080008080008080F6EDED
            EEEEEEE8E8E8E2E2E2DFDFDFDDDADABDCBCB0080800080800080800080800080
            80008080008080008080008080008080008080008080D9C3BDFFFFF7FFE8DDFF
            E5D4FFDBCAFFD8C7FFC3B6BBAEA7008080008080008080008080008080008080
            008080008080008080008080008080008080C7CECEFFFFFFEFEFEFEAEAEAE4E4
            E4E4E4E4E2DADAA2C0C000808000808000808000808000808000808000808000
            8080008080008080008080008080EECEC5E9CAC9E8C8BEE8C4BAE8BEB6EBB8AF
            E4B7B00080800080800080800080800080800080800080800080800080800080
            80008080008080008080DCD7D7D9D8D8D3D3D3D1D1D1CFCFCFCFCCCCC5D0D000
            8080008080008080008080008080008080008080008080008080}
          NumGlyphs = 2
          ParentDoubleBuffered = False
          ParentFont = False
          ParentShowHint = False
          ShowHint = True
          TabOrder = 0
          OnClick = BtnEmailClick
          BrTipoBotao = BrBtnImprim
        end
        object bbb1: TBrvBitBtn
          Left = 498
          Top = 4
          Width = 100
          Height = 25
          Hint = 'Fechar'
          Align = alCustom
          Anchors = [akTop, akRight]
          Caption = 'Encerrar'
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
          OnClick = bbb1Click
          BrTipoBotao = BrBtnFechar
        end
        object BtnCancelar: TBrvBitBtn
          Left = 602
          Top = 4
          Width = 100
          Height = 25
          Hint = 'Cancelar'
          Align = alCustom
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
          TabOrder = 4
          OnClick = BtnCancelarClick
          BrTipoBotao = BrBtnCancel
        end
      end
      object PnlOcorrencias: TPanel
        AlignWithMargins = True
        Left = 3
        Top = 229
        Width = 910
        Height = 184
        Align = alClient
        BevelOuter = bvNone
        TabOrder = 1
        ExplicitHeight = 192
        object BdgConhec: TBrvDbGrid
          Left = 0
          Top = 0
          Width = 910
          Height = 84
          Margins.Top = 0
          BrShowMemo = True
          BrReadOnlyStyle = [fsItalic]
          BrReadOnlyColor = clMaroon
          Align = alClient
          Ctl3D = True
          DataSource = DsT011
          ParentCtl3D = False
          TabOrder = 0
          TitleFont.Charset = DEFAULT_CHARSET
          TitleFont.Color = clWindowText
          TitleFont.Height = -11
          TitleFont.Name = 'Tahoma'
          TitleFont.Style = []
          BrDicionario = DmSrvApl.BrvDicionario
          BrDrawColumn.Strings = (
            'N'#227'o remova essas duas linhas de formata'#231#227'o:'
            'CampoTabela;Operador;ValorComparativo;Cor;')
          BrGradeZebrada = True
          Columns = <
            item
              ButtonStyle = cbsConsulta
              Expanded = False
              FieldName = 'CdEmpres'
              Title.Caption = 'Empresa'
              Width = 48
              Visible = True
              BrOnBeforeConsul = BdgConhecColumns0BrOnBeforeConsul
              BrConsulta = 12
              BrConfigurar.Strings = (
                'CdEmpres;CdEmpres;S;S;')
              BrPermissao = []
              BrValueHalfChecked = False
              BrSaveOnClick = False
            end
            item
              ButtonStyle = cbsConsulta
              Expanded = False
              FieldName = 'CdCTRC'
              Title.Caption = 'Conhecimento'
              Width = 72
              Visible = True
              BrOnBeforeConsul = BdgConhecColumns1BrOnBeforeConsul
              BrConsulta = 22
              BrConfigurar.Strings = (
                'CdEmpres;CdEmpres;S;N;'
                'CdCtrc;CdCtrc;S;S;'
                'DsModeNF;DsModeNF;S;N;'
                'NrSeriNF;NrSeriNF;S;N;'
                'DtEmissa;DtEmissa;S;N;'
                'CdRemete;CdRemete;S;N;'
                'NmRemete;NmRemete;S;N;'
                'CdDestin;CdDestin;S;N;'
                'NmDestin;NmDestin;S;N;'
                ''
                ''
                '')
              BrPermissao = []
              BrValueHalfChecked = False
              BrSaveOnClick = False
            end
            item
              Expanded = False
              FieldName = 'DtEmissa'
              Title.Caption = 'Dt. Emiss'#227'o'
              Width = 62
              Visible = True
              BrConsulta = 0
              BrPermissao = []
              BrValueHalfChecked = False
              BrSaveOnClick = False
            end
            item
              Expanded = False
              FieldName = 'CdRemete'
              Title.Caption = 'Remetente'
              Width = 62
              Visible = True
              BrConsulta = 0
              BrPermissao = []
              BrValueHalfChecked = False
              BrSaveOnClick = False
            end
            item
              Expanded = False
              FieldName = 'NmRemete'
              Title.Caption = 'Nome Remetente'
              Width = 248
              Visible = True
              BrConsulta = 0
              BrPermissao = []
              BrValueHalfChecked = False
              BrSaveOnClick = False
            end
            item
              Expanded = False
              FieldName = 'CdDestin'
              Title.Caption = 'Destinat'#225'rio'
              Width = 62
              Visible = True
              BrConsulta = 0
              BrPermissao = []
              BrValueHalfChecked = False
              BrSaveOnClick = False
            end
            item
              Expanded = False
              FieldName = 'NmDestin'
              Title.Caption = 'Nome Destinat'#225'rio'
              Width = 249
              Visible = True
              BrConsulta = 0
              BrPermissao = []
              BrValueHalfChecked = False
              BrSaveOnClick = False
            end>
        end
        object EdtNotas: TBrvDbEdit
          AlignWithMargins = True
          Left = 0
          Top = 87
          Width = 910
          Height = 21
          Margins.Left = 0
          Margins.Right = 0
          Margins.Bottom = 0
          TabStop = False
          Align = alBottom
          Color = clBtnFace
          DataField = 'Notas'
          DataSource = DsT011
          ReadOnly = True
          TabOrder = 1
          BrAlignment = taLeftJustify
          BrVisibleButton = False
          BrFunctionButton = TpDbConsulta
          BrDicionario = DmSrvApl.BrvDicionario
          BrQueryConsulta = 0
          ExplicitTop = 95
        end
        object BdgOcorrencias: TBrvDbGrid
          AlignWithMargins = True
          Left = 0
          Top = 111
          Width = 910
          Height = 73
          Margins.Left = 0
          Margins.Right = 0
          Margins.Bottom = 0
          BrShowMemo = True
          BrReadOnlyStyle = [fsItalic]
          BrReadOnlyColor = clMaroon
          Align = alBottom
          DataSource = DsT012
          PopupMenu = PopConcluir
          TabOrder = 2
          TitleFont.Charset = DEFAULT_CHARSET
          TitleFont.Color = clWindowText
          TitleFont.Height = -11
          TitleFont.Name = 'Tahoma'
          TitleFont.Style = []
          BrDicionario = DmSrvApl.BrvDicionario
          BrDrawColumn.Strings = (
            'N'#227'o remova essas duas linhas de formata'#231#227'o:'
            'CampoTabela;Operador;ValorComparativo;Cor;')
          BrGradeZebrada = True
          Columns = <
            item
              Expanded = False
              FieldName = 'TxOcorre'
              Title.Caption = 'Descri'#231#227'o da Ocorr'#234'ncia'
              Width = 493
              Visible = True
              BrConsulta = 0
              BrPermissao = []
              BrValueHalfChecked = False
              BrSaveOnClick = False
            end
            item
              Expanded = False
              FieldName = 'StOCorre'
              Title.Caption = 'Situa'#231#227'o'
              Width = 105
              Visible = True
              BrConsulta = 0
              BrPermissao = []
              BrValueHalfChecked = False
              BrSaveOnClick = False
            end
            item
              ButtonStyle = cbsData
              Expanded = False
              FieldName = 'DtOcorre'
              Width = 135
              Visible = True
              BrConsulta = 0
              BrPermissao = []
              BrValueHalfChecked = False
              BrSaveOnClick = False
            end
            item
              ButtonStyle = cbsData
              Expanded = False
              FieldName = 'DtConclu'
              Width = 135
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
  inherited ImlPopFrm: TImageList
    Top = 168
    Bitmap = {
      494C010103000500100014001400FFFFFFFFFF10FFFFFFFFFFFFFFFF424D3600
      0000000000003600000028000000500000001400000001002000000000000019
      0000000000000000000000000000000000000000000000000000000000000000
      0000000000000000000000000000000000000000000000000000000000000000
      0000000000000000000000000000000000000000000000000000000000000000
      0000000000000000000000000000000000000000000000000000000000000000
      0000000000000000000000000000000000000000000000000000000000000000
      000000000000000000000000000000000000000000000000000000000000B08B
      7B00BC888700BA868600BA868500BA868500BA868500BA868400BA858400BA85
      8400BA858400BA858400BA858400BA858400BB868400B28E7900000000000000
      0000000000000000000000000000000000000000000000000000000000000000
      0000000000000000000000000000000000000000000000000000000000000000
      0000000000000000000000000000000000000000000000000000000000000000
      000000000000000000008D7B47007E784700AC763300B0773500AF773400AC75
      3300827947006C7D550000000000000000000000000000000000000000000000
      0000000000000000000000000000000000000000000000000000000000000000
      0000000000000000000000000000000000000000000000000000000000000000
      000000000000000000000000000000000000000000000000000000000000DDCC
      AE00FFF1D100FCE7C400FBE4BB00FAE1B400F8DDAD00F7DAA500F5D69C00F5D5
      9900F5D59900F5D59900F5D59900F5D59900F8DA9B00D0B58300000000000000
      0000000000000000000000000000000000000000000000000000000000000000
      0000000000000000000000000000000000000000000000000000000000000000
      0000000000000000000000000000000000000000000000000000000000000000
      000000000000BB783300AA7B3D00C29F6F00D0BA9800D7C5A700D4C1A200CCB5
      9000BC956200AB763500C67A3300000000000000000000000000000000000000
      000000000000000000000000000000000000000000000000000000BB6D0000C4
      6800009676000000000000000000000000000000000000000000000000000000
      000000000000000000000000000000000000000000000000000000000000D7C7
      AE00F9E9D000CFB68D00CFB68D00CFB68D00F1D6AF00CFB68D00CFB68D00CFB6
      8D00CFB68D00EDCB9600CFB68D00CFB68D00CFB68D00CDB08200000000000000
      0000000000000000000000000000000000000000000000000000000000000000
      0000000000000000000000000000000000000000000000000000000000000000
      0000000000000000000000000000000000000000000000000000000000000000
      0000B57C3B00C2A67A00E9DFCD00F1EBDE00F1EDE000F1ECDF00F1ECDF00F1ED
      E000F0EBDE00E0D2BA00B7936200B77D3C000000000000000000000000000000
      0000000000000000000000000000000000000000000002C46F0001BD6E0000BB
      6E0000BD6A00009A750000000000000000000000000000000000000000000000
      000000000000000000000000000000000000000000000000000000000000D8C9
      B100FBEDD600F5E2CA00F4DEC300F4DCBD00F2D9B600F1D5AF00F0D3A700F0CF
      A100EECD9B00EDCB9600EECC9600EECC9700F1D09800CDB08200000000000000
      0000000000000000000000000000000000000000000000000000000000000000
      0000000000000000000000000000000000000000000000000000000000000000
      000000000000000000000000000000000000000000000000000000000000BA83
      4400C9AF8800F4F2E600F0EBDE00EEE9DA00EFEADD00F2EEE300F4F1E600EEE9
      DA00EEE9DB00F1EDE100EFEADD00BB986500BC83450000000000000000000000
      00000000000000000000000000000000000006C1750004C2720002BE700001BC
      6F0000BA6D0000C0680000927800000000000000000000000000000000000000
      000000000000000000000000000000000000000000000000000000000000DACB
      B600FCF0DD00CFB68D00CFB68D00CFB68D00CFB68D00CFB68D00CFB68D00CFB6
      8D00CFB68D00CFB68D00CFB68D00CFB68D00CFB68D00CDB08200000000000000
      0000000000000000000000000000000000000000000000000000000000000000
      0000000000000000000000000000000000000000000000000000000000000000
      0000000000000000000000000000000000000000000000000000CA8B4B00CBB1
      8800F6F4EB00F0EBDE00F0EADD00F1ECE000EBE1D200A5793C00B7946200F3ED
      E100F0EBDE00F0EADD00F2EDE100F2ECE000BE9B6900D68F4C00000000000000
      00000000000000000000000000000EC9770009C4750006C2750004C0720002BE
      710001BC6F0000BA6D0000C16900009A76000000000000000000000000000000
      000000000000000000000000000000000000000000000000000000000000DBCE
      BB00FDF4E600F7E9D900F7E5D000F6E3CC00F3DFC500F4DCBE00F3D9B500F2D6
      B000F1D3AA00F0D0A300EDCB9A00EDCB9600F1D09800CDB08200000000000000
      0000000000000000000000000000000000000000000000000000000000000000
      0000000000000000000000000000000000000000000000000000000000000000
      000000000000000000000000000000000000000000009A8F6100BC925A00EDE7
      D800F3EFE400F1ECE100F1ECE100F2EEE300F0E9DC00C3A67C00D1BC9B00F4F0
      E500F1EDE100F1ECE100F1ECE100F5F2E800E0D3BA00BC8C5100000000000000
      0000000000000000000017CA7B0011C879000DC3770008C3760005C1740004C1
      720001BE700000BB6E0000BB6D0000BD69000096770000000000000000000000
      000000000000000000000000000000000000000000000000000000000000DDD3
      C100FDF7ED00CFB68D00CFB68D00CFB68D00CFB68D00CFB68D00CFB68D00CFB6
      8D00CFB68D00CFB68D00CFB68D00CFB68D00CFB68D00CDB08200000000000000
      0000000000000000000000000000000000000000000000000000000000000000
      0000000000000000000000000000000000000000000000000000000000000000
      00000000000000000000000000000000000000000000AD905E00D1B69000F6F3
      EB00F3EFE600F3EFE500F3EFE500F3F0E600F4F0E600DDCEB200EBE2D200F5F2
      EA00F3EFE500F3EFE500F3EFE500F3F0E600F5F2E900C7A06F00999065000000
      00000000000024CB7D001DCB7D0016C87B0012C679000CC4780009C5760007C7
      750004C0720002BE700001BC6F0000BA6D0000C1690000967600000000000000
      000000000000000000000000000000000000000000000000000000000000E0D7
      C500FEFBF300F9EFE600F8ECDD00F8E9D800F6E6D100F5E2CA00F5DFC200F4DC
      BC00F2D9B600F1D5AF00F0D3A800F0D0A000F1D19B00CDB08200000000000000
      0000000000000000000000000000000000000000000000000000000000000000
      0000000000000000000000000000000000000000000000000000000000000000
      00000000000000000000000000000000000000000000CC975E00E1D2BA00F8F6
      F000F5F2E900F5F2E900F5F2E900F6F3EB00F1ECE000BD996700CAAE8500F9F8
      F200F6F2EA00F5F2E900F5F2E900F6F2E900F8F5EF00D7BB9600969065000000
      00000000000030D4810023CB7F001DC97D0017C87B0012CA79000BB579000000
      000007C7740004C0730002BE710001BB6F0000BB6E0000BD6A00009A75000000
      000000000000000000000000000000000000000000000000000000000000E3DC
      CB00FFFFFA00CFB68D00CFB68D00CFB68D00CFB68D00CFB68D00CFB68D00CFB6
      8D00CFB68D00CFB68D00CFB68D00CFB68D00CFB68D00CCB08400000000000000
      0000000000000000000000000000000000000000000000000000000000000000
      0000000000000000000000000000000000000000000000000000000000000000
      00000000000000000000000000000000000000000000CC9B6400E9DDCC00F8F7
      F100F6F3EC00F6F3EC00F6F3EC00F6F4ED00F8F7F100D1B89300B7925A00DFCE
      B400FCFDFB00F6F3EC00F6F3EC00F6F3EC00F9F9F400E0C7A800979165000000
      00000000000030CC82002CD0810023CA7E001FCD7C0014C07C00000000000000
      00000000000007CB730004C0720002BE700001BC6F0000BA6D0000C068000092
      780000000000000000000000000000000000000000000000000000000000E5DF
      CF00FFFFFF00FEF8F300FAF3EB00F9F0E600F8ECDD00F8E8D800F7E5CF00F5E2
      CA00F4DFC200F3DCBD00F2D8B600F1D6AD00F4D7A900CDB38900000000000000
      0000000000000000000000000000000000000000000000000000000000000000
      0000000000000000000000000000000000000000000000000000000000000000
      00000000000000000000000000000000000000000000CE9E6800EADECD00FAF8
      F300F8F5EE00F8F5EE00F8F6F000FBFBF700FAF7F200FEFEFC00CFB38C00BC95
      6000DECCB100FAF8F300F8F5EF00F8F5EE00FBFBF600E0C8A900999369000000
      000000000000000000002FC9830031D782001FC27E0000000000000000000000
      0000000000000000000007CC740004C0720002BE710001BC6F0000BA6D0000C1
      6900009A7600000000000000000000000000000000000000000000000000E7E0
      CF00FFFFFF00CFB68D00CFB68D00CFB68D00CFB68D00CFB68D00CFB68D00CFB6
      8D00CFB68D00CFB68D00CFB68D00CFB68D00CFB68D00CFB68D00000000000000
      0000000000000000000000000000000000000000000000000000000000000000
      0000000000000000000000000000000000000000000000000000000000000000
      00000000000000000000000000000000000000000000D19E6800E2D3BC00FBFB
      F800F8F6F100F9F8F200F5F1E800CAAB8000F1EADE00FBFBF700FBF9F500CEB3
      8A00C19D6B00F7F4EE00F9F8F200F9F7F100FAFAF500DABE9A009B966D000000
      0000000000000000000000000000000000000000000000000000000000000000
      000000000000000000000000000006C7740004C1720001BE700000BB6E0000BB
      6D0000BD6900009677000000000000000000000000000000000000000000E9E1
      D000FFFFFF00FFFFFF00FFFDFC00FDF9F500FBF4ED00FAF1E700F9ECDD00F8E9
      D700F7E5D000F6E2C900F5DEC200F5DDBC00F6DDB700C6B38E00000000000000
      0000000000000000000000000000000000000000000000000000000000000000
      0000000000000000000000000000000000000000000000000000000000000000
      00000000000000000000000000000000000000000000BA9D6D00D7BD9900FCFC
      F900FAF8F400FBF9F600F4EEE500C0996300D4B99500FFFFFF00FFFFFF00D5BA
      9600C39E6900F7F4EF00FAF9F500FAF9F500FBF9F600CEA87900AB9D72000000
      0000000000000000000000000000000000000000000000000000000000000000
      00000000000000000000000000000000000007CC740004C0720002BE700001BC
      6F0000BA6D0000C169000098760000000000000000000000000000000000EBE3
      D000FFFFFF00FFFFFF00FFFFFF00FFFEFC00FDF9F300FBF4ED00FAEFE400F9ED
      DD00F8E9D600F7E4CF00F7E4CB00F1DCBF00DBC9AD00A8A08300000000000000
      0000000000000000000000000000000000000000000000000000000000000000
      0000000000000000000000000000000000000000000000000000000000000000
      000000000000000000000000000000000000000000007F967800CEA16D00F4EE
      E500FDFCFA00FBF9F600FDFCFA00E1CEB400C6A27100CEAF8500CEAF8500C6A2
      7100E0CCB000FCFBFA00FBF9F600FEFEFE00E6D6C000DEA26A00000000000000
      0000000000000000000000000000000000000000000000000000000000000000
      0000000000000000000000000000000000000000000007C7740004C0730002BE
      710001BB6F0000BD6D0000B36D0000000000000000000000000000000000EDE5
      D100FFFFFF00CFB68D00CFB68D00CFB68D00CFB68D00CFB68D00CFB68D00CFB6
      8D00FAEDDE00FFF5E200F5EBD700D0C6B500BCB6A400A09E8500000000000000
      0000000000000000000000000000000000000000000000000000000000000000
      0000000000000000000000000000000000000000000000000000000000000000
      0000000000000000000000000000000000000000000000000000ECAA7000D6BC
      9700FFFFFF00FDFCFA00FCFBF800FDFDFC00EEE3D300DDC8AA00DDC7A900ECE2
      D200FDFCFA00FCFBF800FEFEFD00F8F4EF00D4AD7D00929B7700000000000000
      0000000000000000000000000000000000000000000000000000000000000000
      000000000000000000000000000000000000000000000000000007CB730004C0
      720002BE700002C66C000000000000000000000000000000000000000000EFE6
      D200FFFFFF00CFB68D00FFFFFF00FFFFFF00FFFFFF00FFFEFC00FCF6F100CFB6
      8D00FAF0E400C8A39600AA7E7800A27C7B00A37E7E00A18A6F00000000000000
      0000000000000000000000000000000000000000000000000000000000000000
      0000000000000000000000000000000000000000000000000000000000000000
      000000000000000000000000000000000000000000000000000000000000CFA4
      7100D8BD9A00FFFFFF00FFFFFF00FDFDFB00FFFFFF00FFFFFF00FFFFFF00FFFF
      FF00FDFDFC00FFFFFF00F9F6F200CDAB7D00DAA9750000000000000000000000
      0000000000000000000000000000000000000000000000000000000000000000
      00000000000000000000000000000000000000000000000000000000000007CE
      740005C77100000000000000000000000000000000000000000000000000F1E9
      D300FFFFFF00CFB68D00FFFFFF00FFFFFF00FFFFFF00FFFFFF00FFFCFB00CFB6
      8D00F4EBE300B88D8700E1B38500EFB36100F5AD3C00D2965700000000000000
      0000000000000000000000000000000000000000000000000000000000000000
      0000000000000000000000000000000000000000000000000000000000000000
      000000000000000000000000000000000000000000000000000000000000B8A6
      7C00D0A67300D5B99400F2EBE000FFFFFF00FFFFFF00FFFFFF00FFFFFF00FFFF
      FF00FFFFFF00EADCCB00D6B08300DBAA76000000000000000000000000000000
      0000000000000000000000000000000000000000000000000000000000000000
      0000000000000000000000000000000000000000000000000000000000000000
      000000000000000000000000000000000000000000000000000000000000F1EA
      D300FFFFFF00CFB68D00CFB68D00CFB68D00CFB68D00CFB68D00CFB68D00CFB6
      8D00F6EFEB00BA8F8900E8BD8B00F8C26200D5986C0000000000000000000000
      0000000000000000000000000000000000000000000000000000000000000000
      0000000000000000000000000000000000000000000000000000000000000000
      0000000000000000000000000000000000000000000000000000000000000000
      000000000000F1B07800D1A67200D9B99000E7CFB300EBD6BF00EBD5BD00E4CB
      AC00D4B18500E3AA7300959E7B00000000000000000000000000000000000000
      0000000000000000000000000000000000000000000000000000000000000000
      0000000000000000000000000000000000000000000000000000000000000000
      000000000000000000000000000000000000000000000000000000000000F4EE
      DA00FFFFFF00FFFFFF00FFFFFF00FFFFFF00FFFFFF00FFFFFF00FFFFFF00FFFF
      FF00F9F7F700BB928E00F0C68700CC9D71000000000000000000000000000000
      0000000000000000000000000000000000000000000000000000000000000000
      0000000000000000000000000000000000000000000000000000000000000000
      0000000000000000000000000000000000000000000000000000000000000000
      00000000000000000000859B7E00A8A17A00A39E7700A29D7600A29D7600A39F
      7800B2A47B000000000000000000000000000000000000000000000000000000
      0000000000000000000000000000000000000000000000000000000000000000
      0000000000000000000000000000000000000000000000000000000000000000
      000000000000000000000000000000000000000000000000000000000000E7EB
      AD00F1F8CF00F0F5CC00EEF4CC00EDF2CC00EBF0CA00E9EFCA00E6EDCA00E5EC
      CB00DDE1C000B29C7800BAAE6B00000000000000000000000000000000000000
      0000000000000000000000000000000000000000000000000000000000000000
      0000000000000000000000000000000000000000000000000000000000000000
      0000000000000000000000000000000000000000000000000000000000000000
      0000000000000000000000000000000000000000000000000000000000000000
      0000000000000000000000000000000000000000000000000000000000000000
      0000000000000000000000000000000000000000000000000000000000000000
      0000000000000000000000000000000000000000000000000000000000000000
      0000000000000000000000000000000000000000000000000000000000000000
      0000000000000000000000000000000000000000000000000000000000000000
      0000000000000000000000000000000000000000000000000000000000000000
      0000000000000000000000000000000000000000000000000000000000000000
      0000000000000000000000000000000000000000000000000000000000000000
      000000000000000000000000000000000000424D3E000000000000003E000000
      2800000050000000140000000100010000000000F00000000000000000000000
      000000000000000000000000FFFFFF00FFFFFFFFFFE0003000000000FC03FFFF
      FFE0003000000000F801FFC7FFE0003000000000F000FF83FFE0003000000000
      E0007F01FFE0003000000000C0003E00FFE000300000000080003C007FE00030
      00000000800018003FE0003000000000800018101FE000300000000080001838
      0FE000300000000080001C7C07E000300000000080001FFE03E0003000000000
      80001FFF01E000300000000080003FFF81E0003000000000C0003FFFC3E00030
      00000000E0007FFFE7E0003000000000E000FFFFFFE0007000000000F801FFFF
      FFE000F000000000FC07FFFFFFE001F000000000FFFFFFFFFFFFFFF000000000
      00000000000000000000000000000000000000000000}
  end
  object DsT010: TDataSource [4]
    DataSet = QcT010
    OnDataChange = DsT010DataChange
    Left = 384
    Top = 8
  end
  object QcT010: TBrvClientDataSet [5]
    Aggregates = <>
    Params = <>
    ProviderName = 'DcAtend'
    RemoteServer = DmSrvApl.PvcSDmTra
    OnNewRecord = QcT010NewRecord
    OnReconcileError = QcT011ReconcileError
    BrShowFieldName = False
    BrQueryCode = 40
    BrDicionario = DmSrvApl.BrvDicionario
    BrType = VqNormal
    BrGravaLog = True
    BrUserCode = 0
    Left = 464
    Top = 8
  end
  object DsT011: TDataSource [6]
    DataSet = QcT011
    Left = 280
    Top = 226
  end
  object QcT011: TBrvClientDataSet [7]
    Aggregates = <>
    Params = <>
    ProviderName = 'DcAtenCon'
    RemoteServer = DmSrvApl.PvcSDmTra
    OnNewRecord = QcT011NewRecord
    OnReconcileError = QcT011ReconcileError
    BrShowFieldName = False
    BrQueryCode = 41
    BrDicionario = DmSrvApl.BrvDicionario
    BrType = VqNormal
    BrGravaLog = True
    BrUserCode = 41
    Left = 384
    Top = 226
  end
  object QcT012: TBrvClientDataSet [8]
    Aggregates = <>
    Params = <>
    ProviderName = 'DcOcorrenc'
    RemoteServer = DmSrvApl.PvcSDmTra
    OnNewRecord = QcT012NewRecord
    OnReconcileError = QcT011ReconcileError
    BrShowFieldName = False
    BrQueryCode = 42
    BrDicionario = DmSrvApl.BrvDicionario
    BrType = VqNormal
    BrGravaLog = True
    BrUserCode = 42
    Left = 528
    Top = 226
  end
  object DsT012: TDataSource [9]
    DataSet = QcT012
    Left = 456
    Top = 226
  end
  object PopConcluir: TPopupMenu [10]
    Left = 224
    Top = 226
    object ConcluirOcorrncia1: TMenuItem
      Caption = 'Concluir Ocorr'#234'ncia'
      OnClick = ConcluirOcorrncia1Click
    end
  end
  object RelAsc: TBrvRelAsc [11]
    Linha = 0
    Pagina = 0
    PaginaInicial = 0
    LinhasPorPagina = 60
    ColunasPorLinha = Rel080Col
    GerarCabecalho = True
    Left = 128
    Top = 226
  end
  object PopLog: TPopupMenu [12]
    Left = 125
    Top = 295
    object VerLog1: TMenuItem
      Caption = 'Ver Log'
      OnClick = VerLog1Click
    end
    object ReprogramarDatadeEntrega1: TMenuItem
      Caption = 'Reprogramar Data de Entrega'
      OnClick = ReprogramarDatadeEntrega1Click
    end
  end
end
