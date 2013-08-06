inherited Con0003: TCon0003
  BorderIcons = [biSystemMenu, biMinimize]
  BorderStyle = bsSingle
  Caption = 'Con0003'
  ClientHeight = 460
  ClientWidth = 836
  FormStyle = fsNormal
  Visible = False
  ExplicitWidth = 842
  ExplicitHeight = 485
  PixelsPerInch = 96
  TextHeight = 13
  inherited NavBarra: TBrvDbNavCop
    Width = 836
    ExplicitWidth = 836
    inherited BrvSpeedButton1: TBrvSpeedButton
      Left = 809
      ExplicitLeft = 801
    end
    inherited SbtAjuda: TBrvSpeedButton
      Left = 784
      ExplicitLeft = 776
    end
  end
  inherited PnlFundo: TPanel
    Width = 836
    Height = 430
    ExplicitWidth = 836
    ExplicitHeight = 430
    object pnlBody: TPanel
      AlignWithMargins = True
      Left = 4
      Top = 4
      Width = 824
      Height = 418
      Align = alClient
      BevelKind = bkTile
      BevelOuter = bvNone
      TabOrder = 0
      Visible = False
      object pnlAtend: TPanel
        AlignWithMargins = True
        Left = 3
        Top = 3
        Width = 814
        Height = 164
        Align = alTop
        Anchors = []
        BevelEdges = [beBottom]
        BevelKind = bkSoft
        BevelOuter = bvNone
        TabOrder = 0
        DesignSize = (
          814
          162)
        object lblDescricao: TLabel
          Left = 0
          Top = 32
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
          Left = 440
          Top = 58
          Width = 34
          Height = 13
          Caption = 'In'#237'cio'
          Font.Charset = DEFAULT_CHARSET
          Font.Color = clBlack
          Font.Height = -11
          Font.Name = 'MS Sans Serif'
          Font.Style = [fsBold]
          ParentFont = False
        end
        object Label1: TLabel
          Left = 621
          Top = 58
          Width = 46
          Height = 13
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
          Font.Color = clBlack
          Font.Height = -11
          Font.Name = 'MS Sans Serif'
          Font.Style = [fsBold]
          ParentFont = False
        end
        object lblTxt: TLabel
          Left = 0
          Top = 81
          Width = 171
          Height = 13
          Anchors = []
          Caption = 'Detalhamento do Atendimento'
          Font.Charset = DEFAULT_CHARSET
          Font.Color = clBlack
          Font.Height = -11
          Font.Name = 'MS Sans Serif'
          Font.Style = [fsBold]
          ParentFont = False
          ExplicitTop = 84
        end
        object Label3: TLabel
          Left = 439
          Top = 4
          Width = 26
          Height = 13
          Caption = 'Tipo'
          Font.Charset = DEFAULT_CHARSET
          Font.Color = clBlack
          Font.Height = -11
          Font.Name = 'MS Sans Serif'
          Font.Style = [fsBold]
          ParentFont = False
        end
        object Label4: TLabel
          Left = 0
          Top = 58
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
        object EdtDsAtendi: TBrvDbEdit
          Left = 64
          Top = 29
          Width = 748
          Height = 21
          Color = clBtnFace
          DataField = 'DsAtendi'
          DataSource = DsT010
          ParentShowHint = False
          ShowHint = False
          TabOrder = 4
          BrAlignment = taLeftJustify
          BrVisibleButton = False
          BrFunctionButton = TpDbConsulta
          BrDicionario = DmSrvApl.BrvDicionario
          BrQueryConsulta = 0
          BrvReadOnlyColor = 14541539
        end
        object EdtDtAbertu: TBrvDbEdit
          Left = 480
          Top = 55
          Width = 135
          Height = 21
          Color = clBtnFace
          DataField = 'DtAbertu'
          DataSource = DsT010
          TabOrder = 7
          BrAlignment = taRightJustify
          BrVisibleButton = False
          BrFunctionButton = TpDbDataHora
          BrDicionario = DmSrvApl.BrvDicionario
          BrQueryConsulta = 0
          BrvReadOnlyColor = 14541539
        end
        object EdtDtFecham: TBrvDbEdit
          Left = 677
          Top = 55
          Width = 135
          Height = 21
          Color = clBtnFace
          DataField = 'DtFecham'
          DataSource = DsT010
          TabOrder = 8
          BrAlignment = taRightJustify
          BrVisibleButton = False
          BrFunctionButton = TpDbDataHora
          BrDicionario = DmSrvApl.BrvDicionario
          BrQueryConsulta = 0
          BrvReadOnlyColor = 14541539
        end
        object EdtCdEmpres: TBrvDbEdit
          Left = 64
          Top = 2
          Width = 80
          Height = 21
          Color = clBtnFace
          DataField = 'CdEmpres'
          DataSource = DsT010
          TabOrder = 0
          BrAlignment = taRightJustify
          BrVisibleButton = False
          BrFunctionButton = TpDbConsulta
          BrConfigurar.Strings = (
            'CdEmpres;CdEmpres;S;S;'
            'RsEmpres;RsEmpres;S;N;')
          BrDicionario = DmSrvApl.BrvDicionario
          BrQueryConsulta = 12
          BrvReadOnlyColor = 14541539
        end
        object EdtDsEmpr: TBrvDBRetCon
          Left = 148
          Top = 2
          Width = 289
          Height = 21
          TabStop = False
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
          Left = 480
          Top = 1
          Width = 80
          Height = 21
          Color = clBtnFace
          DataField = 'CdTipAte'
          DataSource = DsT010
          TabOrder = 2
          BrAlignment = taRightJustify
          BrVisibleButton = False
          BrFunctionButton = TpDbConsulta
          BrConfigurar.Strings = (
            'CdTipAte;CdTipAte;S;S;'
            'DsTipAte;DsTipAte;S;N;')
          BrDicionario = DmSrvApl.BrvDicionario
          BrQueryConsulta = 23
          BrvReadOnlyColor = 14541539
        end
        object EdtDsTipAte: TBrvDBRetCon
          Left = 564
          Top = 1
          Width = 248
          Height = 21
          TabStop = False
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
          Left = 64
          Top = 55
          Width = 80
          Height = 21
          Color = clBtnFace
          DataField = 'CdCarga'
          DataSource = DsT010
          TabOrder = 5
          BrAlignment = taRightJustify
          BrVisibleButton = False
          BrFunctionButton = TpDbConsulta
          BrConfigurar.Strings = (
            'CdCarga;CdCarga;S;S;'
            'DsDesCar;DsDesCar;S;N;')
          BrDicionario = DmSrvApl.BrvDicionario
          BrQueryConsulta = 24
          BrvReadOnlyColor = 14541539
        end
        object EdtDsDescar: TBrvDBRetCon
          Left = 148
          Top = 55
          Width = 288
          Height = 21
          TabStop = False
          BevelInner = bvLowered
          BevelKind = bkFlat
          BevelWidth = 2
          BorderStyle = bsNone
          DataField = 'DSDESCAR'
          DataSource = DsT010
          ParentColor = True
          ReadOnly = True
          TabOrder = 6
        end
        object DBMemo1: TDBMemo
          Left = 0
          Top = 103
          Width = 812
          Height = 57
          Color = clBtnFace
          DataField = 'TxAtendi'
          DataSource = DsT010
          TabOrder = 9
        end
      end
      object PnlOcorrencias: TPanel
        AlignWithMargins = True
        Left = 3
        Top = 173
        Width = 814
        Height = 238
        Align = alClient
        BevelOuter = bvNone
        TabOrder = 1
        object BdgConhec: TBrvDbGrid
          Left = 0
          Top = 0
          Width = 814
          Height = 102
          Margins.Top = 0
          BrShowMemo = True
          BrReadOnlyStyle = [fsItalic]
          BrReadOnlyColor = clMaroon
          Align = alTop
          Color = clBtnFace
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
          Top = 105
          Width = 814
          Height = 21
          Margins.Left = 0
          Margins.Right = 0
          Margins.Bottom = 0
          TabStop = False
          Align = alTop
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
          BrvReadOnlyColor = 14541539
        end
        object BdgOcorrencias: TBrvDbGrid
          AlignWithMargins = True
          Left = 0
          Top = 129
          Width = 814
          Height = 109
          Margins.Left = 0
          Margins.Right = 0
          Margins.Bottom = 0
          BrShowMemo = True
          BrReadOnlyStyle = [fsItalic]
          BrReadOnlyColor = clMaroon
          Align = alClient
          Color = clBtnFace
          DataSource = DsT012
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
              Width = 433
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
    Height = 16
    Width = 16
    Bitmap = {
      494C0101030005000C0010001000FFFFFFFFFF10FFFFFFFFFFFFFFFF424D3600
      0000000000003600000028000000400000001000000001002000000000000010
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
      0000000000000000000000000000000000000000000000000000000000000000
      0000000000000000000000000000000000000000000000000000000000000000
      0000000000000000000000000000000000000000000000000000000000000000
      0000000000000000000000000000000000000000000000000000000000000000
      0000000000000000000000000000000000000000000000000000000000000000
      00000000000000000000000000007F007F007F007F007F7F7F00000000000000
      0000000000000000000000000000000000000000000000000000000000000000
      0000000000000000000000000000000000000000000000000000000000000000
      00000000000000000000000000000000000000000000000000007F7F7F000000
      0000000000000000000000000000000000000000000000000000000000000000
      0000000000000000000000000000000000000000000000000000000000000000
      0000000000000000000000000000000000000000000000000000000000000000
      0000000000000000000000000000000000000000000000000000000000000000
      0000000000007F007F007F007F00FFFFFF00FFFFFF00BFBFBF007F7F7F000000
      000000000000000000000000000000000000000000000000000000000000FFFF
      FF00FFFFFF00FFFFFF00FFFFFF00FFFFFF00FFFFFF00FFFFFF00FFFFFF00FFFF
      FF00FFFFFF0000000000000000000000000000000000000000007F7F7F00FFFF
      FF00FFFFFF00FFFFFF00FFFFFF00FFFFFF00FFFFFF00FFFFFF00FFFFFF00FFFF
      FF00000000000000000000000000000000000000000000000000000000000000
      0000000000000000000000000000000000000000000000000000000000000000
      0000000000000000000000000000000000000000000000000000000000007F00
      7F007F007F00FFFFFF00FFFFFF000000000000000000BFBFBF00BFBFBF007F7F
      7F0000000000000000000000000000000000000000000000000000000000FFFF
      FF00FFFFFF00FFFFFF00FFFFFF00FFFFFF00FFFFFF00FFFFFF00FFFFFF00FFFF
      FF00FFFFFF0000000000000000000000000000000000000000007F7F7F00FFFF
      FF00FFFFFF00FFFFFF00FFFFFF00FFFFFF00FFFFFF00FFFFFF00FFFFFF00FFFF
      FF00000000000000000000000000000000000000000000000000000000000000
      0000000000000000000000000000000000000000000000000000000000000000
      000000000000000000000000000000000000000000007F007F007F007F00FFFF
      FF00FFFFFF0000000000000000007F007F007F007F0000000000BFBFBF00BFBF
      BF007F7F7F00000000000000000000000000000000000000000000000000FFFF
      FF00FFFFFF00FFFFFF0000000000FFFFFF00FFFFFF00FFFFFF00FFFFFF00FFFF
      FF00FFFFFF0000000000000000000000000000000000000000007F7F7F007F00
      00007F0000007F0000007F0000007F0000007F0000007F0000007F0000007F00
      0000000000000000000000000000000000000000000000000000000000000000
      0000000000000000000000000000000000000000000000000000000000000000
      000000000000000000000000000000000000000000007F007F00FFFFFF000000
      0000000000007F007F007F007F007F007F007F007F007F007F0000000000BFBF
      BF00BFBFBF007F7F7F000000000000000000000000000000000000000000FFFF
      FF00FFFFFF0000000000000000000000000000000000FFFFFF00FFFFFF00FFFF
      FF00FFFFFF0000000000000000000000000000000000000000007F7F7F007F00
      0000FFFFFF00FFFFFF00FFFFFF00FFFFFF00FFFFFF00FFFFFF00FFFFFF007F00
      0000000000000000000000000000000000000000000000000000000000000000
      0000000000000000000000000000000000000000000000000000000000000000
      0000000000000000000000000000000000000000000000000000000000007F00
      7F007F007F007F007F00007F7F0000FFFF007F007F007F007F007F007F000000
      0000BFBFBF00BFBFBF007F7F7F0000000000000000000000000000000000FFFF
      FF00FFFFFF0000000000FFFFFF00000000000000000000000000FFFFFF00FFFF
      FF00FFFFFF0000000000000000000000000000000000000000007F7F7F007F00
      00007F0000007F0000007F0000007F0000007F0000007F0000007F0000007F00
      0000000000000000000000000000000000000000000000000000000000000000
      0000000000000000000000000000000000000000000000000000000000000000
      000000000000000000000000000000000000000000007F007F007F007F007F00
      7F007F007F007F007F007F007F00007F7F007F007F007F007F007F007F007F00
      7F0000000000BFBFBF000000000000000000000000000000000000000000FFFF
      FF00FFFFFF0000000000FFFFFF00FFFFFF000000000000000000FFFFFF00FFFF
      FF00FFFFFF0000000000000000000000000000000000000000007F7F7F00FFFF
      FF00FFFFFF00FFFFFF00FFFFFF00FFFFFF00FFFFFF00FFFFFF00FFFFFF00FFFF
      FF00000000000000000000000000000000000000000000000000000000000000
      0000000000000000000000000000000000000000000000000000000000000000
      0000000000000000000000000000000000000000000000000000FFFFFF007F00
      7F007F007F007F007F007F007F007F007F0000FFFF0000FFFF007F007F007F00
      7F007F007F00000000000000000000000000000000000000000000000000FFFF
      FF00FFFFFF00FFFFFF00FFFFFF00FFFFFF00FFFFFF000000000000000000FFFF
      FF00FFFFFF0000000000000000000000000000000000000000007F7F7F00FFFF
      FF00FFFFFF00FFFFFF00FFFFFF00FFFFFF00FFFFFF00FFFFFF00FFFFFF00FFFF
      FF00000000000000000000000000000000000000000000000000000000000000
      0000000000000000000000000000000000000000000000000000000000000000
      000000000000000000000000000000000000000000000000000000000000FFFF
      FF007F007F007F007F007F007F007F007F007F007F00007F7F0000FFFF0000FF
      FF007F007F007F007F000000000000000000000000000000000000000000FFFF
      FF00FFFFFF00FFFFFF00FFFFFF00FFFFFF00FFFFFF00FFFFFF0000000000FFFF
      FF00FFFFFF0000000000000000000000000000000000000000007F7F7F00FFFF
      FF007F0000007F0000007F0000007F000000FFFFFF00FFFFFF00FFFFFF00FFFF
      FF00000000000000000000000000000000000000000000000000000000000000
      0000000000000000000000000000000000000000000000000000000000000000
      0000000000000000000000000000000000000000000000000000000000000000
      0000FFFFFF007F007F007F007F007F007F00007F7F007F007F0000FFFF0000FF
      FF007F007F007F007F007F007F0000000000000000000000000000000000FFFF
      FF00FFFFFF00FFFFFF00FFFFFF00FFFFFF00FFFFFF00FFFFFF00FFFFFF00FFFF
      FF00FFFFFF0000000000000000000000000000000000000000007F7F7F00FFFF
      FF00FFFFFF00FFFFFF00FFFFFF00FFFFFF00FFFFFF00FFFFFF00FFFFFF00FFFF
      FF00000000000000000000000000000000000000000000000000000000000000
      0000000000000000000000000000000000000000000000000000000000000000
      0000000000000000000000000000000000000000000000000000000000000000
      000000000000FFFFFF007F007F007F007F0000FFFF0000FFFF0000FFFF007F00
      7F007F007F007F007F000000000000000000000000000000000000000000FFFF
      FF00FFFFFF00FFFFFF00FFFFFF00FFFFFF00FFFFFF00FFFFFF00FFFFFF00FFFF
      FF00FFFFFF0000000000000000000000000000000000000000007F7F7F00FFFF
      FF007F0000007F0000007F0000007F0000007F000000FFFFFF00FFFFFF00FFFF
      FF00000000000000000000000000000000000000000000000000000000000000
      0000000000000000000000000000000000000000000000000000000000000000
      0000000000000000000000000000000000000000000000000000000000000000
      00000000000000000000FFFFFF007F007F007F007F007F007F007F007F007F00
      7F00000000000000000000000000000000000000000000000000000000000000
      0000000000000000000000000000000000000000000000000000000000000000
      00000000000000000000000000000000000000000000000000007F7F7F00FFFF
      FF00FFFFFF00FFFFFF00FFFFFF00FFFFFF00FFFFFF00FFFFFF00FFFFFF00FFFF
      FF00000000000000000000000000000000000000000000000000000000000000
      0000000000000000000000000000000000000000000000000000000000000000
      0000000000000000000000000000000000000000000000000000000000000000
      0000000000000000000000000000FFFFFF007F007F007F007F00000000000000
      0000000000000000000000000000000000000000000000000000000000000000
      0000000000000000000000000000000000000000000000000000000000000000
      00000000000000000000000000000000000000000000000000007F7F7F007F7F
      7F007F7F7F007F7F7F007F7F7F007F7F7F007F7F7F007F7F7F007F7F7F007F7F
      7F007F7F7F000000000000000000000000000000000000000000000000000000
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
      2800000040000000100000000100010000000000800000000000000000000000
      000000000000000000000000FFFFFF00FFFFFFFFFFFF0000FE3FFFFFFFFF0000
      F81FC003C0070000E00FC003C00700008007C003C00700000003C003C0070000
      0001C003C00700000000C003C00700000001C003C00700008001C003C0070000
      C001C003C0070000E000C003C0070000F000C003C0070000F803C003C0070000
      FC0FFFFFC0070000FE3FFFFFFFFF000000000000000000000000000000000000
      000000000000}
  end
  object DsT010: TDataSource
    DataSet = QcT010
    Left = 208
    Top = 152
  end
  object QcT010: TBrvClientDataSet
    Aggregates = <>
    Params = <>
    ReadOnly = True
    RemoteServer = DmSrvApl.PvcSDmTra
    BrShowFieldName = False
    BrQueryCode = 0
    BrDicionario = DmSrvApl.BrvDicionario
    BrType = VqNormal
    BrGravaLog = True
    BrUserCode = 0
    Left = 288
    Top = 152
  end
  object DsT011: TDataSource
    DataSet = QcT011
    Left = 360
    Top = 152
  end
  object QcT011: TBrvClientDataSet
    Aggregates = <>
    Params = <>
    ReadOnly = True
    RemoteServer = DmSrvApl.PvcSDmTra
    BrShowFieldName = False
    BrQueryCode = 0
    BrDicionario = DmSrvApl.BrvDicionario
    BrType = VqNormal
    BrGravaLog = True
    BrUserCode = 0
    Left = 440
    Top = 152
  end
  object DsT012: TDataSource
    DataSet = QcT012
    Left = 512
    Top = 152
  end
  object QcT012: TBrvClientDataSet
    Aggregates = <>
    Params = <>
    ReadOnly = True
    RemoteServer = DmSrvApl.PvcSDmTra
    BrShowFieldName = False
    BrQueryCode = 0
    BrDicionario = DmSrvApl.BrvDicionario
    BrType = VqNormal
    BrGravaLog = True
    BrUserCode = 0
    Left = 592
    Top = 152
  end
end
