inherited Con0008: TCon0008
  Left = 300
  Top = 30
  Caption = 'Con0008'
  ClientHeight = 542
  ClientWidth = 742
  Position = poDesigned
  ExplicitWidth = 750
  ExplicitHeight = 569
  PixelsPerInch = 96
  TextHeight = 13
  inherited NavBarra: TBrvDbNavCop
    Width = 742
    ExplicitWidth = 742
    inherited BrvSpeedButton1: TBrvSpeedButton
      Left = 715
      ExplicitLeft = 715
    end
    inherited SbtAjuda: TBrvSpeedButton
      Left = 690
      ExplicitLeft = 690
    end
  end
  inherited PnlFundo: TPanel
    Top = 71
    Width = 742
    Height = 471
    Visible = False
    ExplicitTop = 71
    ExplicitWidth = 742
    ExplicitHeight = 471
    object Label1: TLabel
      Left = 6
      Top = 11
      Width = 36
      Height = 13
      Caption = 'Destino'
    end
    object Label76: TLabel
      Left = 6
      Top = 37
      Width = 45
      Height = 13
      Caption = 'Motorista'
    end
    object Label43: TLabel
      Left = 6
      Top = 63
      Width = 33
      Height = 13
      Caption = 'Ve'#237'culo'
    end
    object Label2: TLabel
      Left = 6
      Top = 89
      Width = 40
      Height = 13
      Caption = 'Gera'#231#227'o'
    end
    object Label3: TLabel
      Left = 180
      Top = 89
      Width = 26
      Height = 13
      Caption = 'Sa'#237'da'
    end
    object Label4: TLabel
      Left = 381
      Top = 89
      Width = 41
      Height = 13
      Caption = 'Previs'#227'o'
    end
    object Label6: TLabel
      Left = 568
      Top = 89
      Width = 39
      Height = 13
      Caption = 'Retorno'
    end
    object Label7: TLabel
      Left = 6
      Top = 115
      Width = 58
      Height = 13
      Caption = 'Km de Sa'#237'da'
    end
    object Label8: TLabel
      Left = 180
      Top = 115
      Width = 56
      Height = 13
      Caption = 'Km Previsto'
    end
    object Label9: TLabel
      Left = 381
      Top = 115
      Width = 71
      Height = 13
      Caption = 'Km Percorridos'
    end
    object Label12: TLabel
      Left = 568
      Top = 115
      Width = 46
      Height = 13
      Caption = 'EDI. Seg.'
    end
    object Label10: TLabel
      Left = 6
      Top = 142
      Width = 62
      Height = 13
      Caption = 'N'#186' do Malote'
    end
    object Label17: TLabel
      Left = 6
      Top = 168
      Width = 57
      Height = 13
      Caption = 'Qtd. CTRCs'
    end
    object Label15: TLabel
      Left = 6
      Top = 195
      Width = 73
      Height = 13
      Caption = 'Empresa Carga'
    end
    object Label13: TLabel
      Left = 180
      Top = 142
      Width = 89
      Height = 13
      Caption = 'Peso Base Comiss.'
    end
    object Label16: TLabel
      Left = 180
      Top = 168
      Width = 103
      Height = 13
      Caption = 'Qtd. Entrega Comiss.'
    end
    object Label11: TLabel
      Left = 381
      Top = 142
      Width = 74
      Height = 13
      Caption = 'Pgto. Comiss'#227'o'
    end
    object Label18: TLabel
      Left = 381
      Top = 168
      Width = 57
      Height = 13
      Caption = 'Peso. Merc.'
    end
    object Label14: TLabel
      Left = 568
      Top = 142
      Width = 31
      Height = 13
      Caption = 'Status'
    end
    object Label19: TLabel
      Left = 568
      Top = 168
      Width = 27
      Height = 13
      Caption = 'Valor '
    end
    object PgcDados: TPageControl
      AlignWithMargins = True
      Left = 4
      Top = 219
      Width = 730
      Height = 244
      ActivePage = TbsConhec
      Align = alBottom
      Anchors = [akLeft, akTop, akRight, akBottom]
      TabHeight = 26
      TabOrder = 0
      OnChange = PgcDadosChange
      object TbsConhec: TTabSheet
        Caption = 'Conhecimentos da Carga'
        ImageIndex = 1
        ExplicitLeft = 0
        ExplicitTop = 0
        ExplicitWidth = 0
        ExplicitHeight = 0
        object BdgRegistros: TBrvDbGrid
          AlignWithMargins = True
          Left = 3
          Top = 3
          Width = 716
          Height = 202
          BrShowMemo = True
          BrReadOnlyStyle = []
          BrReadOnlyColor = clBlack
          Align = alClient
          DataSource = DsT017
          Options = [dgTitles, dgIndicator, dgColumnResize, dgColLines, dgRowLines, dgTabs, dgAlwaysShowSelection, dgConfirmDelete, dgCancelOnExit]
          PopupMenu = PopCTRC
          TabOrder = 0
          TitleFont.Charset = DEFAULT_CHARSET
          TitleFont.Color = clWindowText
          TitleFont.Height = -11
          TitleFont.Name = 'Tahoma'
          TitleFont.Style = []
          OnDblClick = BdgRegistrosDblClick
          BrDicionario = DmSrvApl.BrvDicionario
          BrDrawColumn.Strings = (
            'N'#227'o remova essas duas linhas de formata'#231#227'o:'
            'CampoTabela;Operador;ValorComparativo;Cor;')
          BrGradeZebrada = True
          Columns = <
            item
              Expanded = False
              FieldName = 'CdEmpres'
              Title.Caption = 'Empresa'
              Width = 68
              Visible = True
              BrConsulta = 0
              BrPermissao = []
              BrValueHalfChecked = False
              BrSaveOnClick = False
            end
            item
              Expanded = False
              FieldName = 'DtEmissa'
              Title.Caption = 'Dt. Emiss'#227'o'
              Width = 68
              Visible = True
              BrConsulta = 0
              BrPermissao = []
              BrValueHalfChecked = False
              BrSaveOnClick = False
            end
            item
              Expanded = False
              FieldName = 'CdCTRC'
              Title.Caption = 'CTRC'
              Width = 68
              Visible = True
              BrConsulta = 0
              BrPermissao = []
              BrValueHalfChecked = False
              BrSaveOnClick = False
            end
            item
              Expanded = False
              FieldName = 'NrSeriNf'
              Title.Caption = 'N'#186' S'#233'rie'
              Width = 68
              Visible = True
              BrConsulta = 0
              BrPermissao = []
              BrValueHalfChecked = False
              BrSaveOnClick = False
            end
            item
              Expanded = False
              FieldName = 'StCtrc'
              Title.Caption = 'Situa'#231#227'o'
              Width = 68
              Visible = True
              BrConsulta = 0
              BrPermissao = []
              BrValueHalfChecked = False
              BrSaveOnClick = False
            end
            item
              Expanded = False
              FieldName = 'DtColeta'
              Title.Caption = 'Dt. Coleta'
              Width = 68
              Visible = True
              BrConsulta = 0
              BrPermissao = []
              BrValueHalfChecked = False
              BrSaveOnClick = False
            end
            item
              Expanded = False
              FieldName = 'DtMovto'
              Title.Caption = 'Dt. Movimento'
              Width = 112
              Visible = True
              BrConsulta = 0
              BrPermissao = []
              BrValueHalfChecked = False
              BrSaveOnClick = False
            end
            item
              Expanded = False
              FieldName = 'DtPreEnt'
              Title.Caption = 'Dt. Previs'#227'o'
              Width = 68
              Visible = True
              BrConsulta = 0
              BrPermissao = []
              BrValueHalfChecked = False
              BrSaveOnClick = False
            end
            item
              Expanded = False
              FieldName = 'DtEntreg'
              Title.Caption = 'Dt. Entrega'
              Width = 68
              Visible = True
              BrConsulta = 0
              BrPermissao = []
              BrValueHalfChecked = False
              BrSaveOnClick = False
            end
            item
              Expanded = False
              FieldName = 'DtEntMot'
              Title.Caption = 'Dt. Rastreador'
              Width = 112
              Visible = True
              BrConsulta = 0
              BrPermissao = []
              BrValueHalfChecked = False
              BrSaveOnClick = False
            end
            item
              Expanded = False
              FieldName = 'CdCarga'
              Title.Caption = 'Cd. Carga'
              Width = 68
              Visible = True
              BrConsulta = 0
              BrPermissao = []
              BrValueHalfChecked = False
              BrSaveOnClick = False
            end
            item
              Expanded = False
              FieldName = 'CdVeicul'
              Title.Caption = 'Cd. Ve'#237'culo'
              Width = 68
              Visible = True
              BrConsulta = 0
              BrPermissao = []
              BrValueHalfChecked = False
              BrSaveOnClick = False
            end
            item
              Expanded = False
              FieldName = 'NrFatura'
              Title.Caption = 'Nr. Fatura'
              Width = 68
              Visible = True
              BrConsulta = 0
              BrPermissao = []
              BrValueHalfChecked = False
              BrSaveOnClick = False
            end
            item
              Expanded = False
              FieldName = 'RsRemete'
              Title.Caption = 'Remetente'
              Width = 168
              Visible = True
              BrConsulta = 0
              BrPermissao = []
              BrValueHalfChecked = False
              BrSaveOnClick = False
            end
            item
              Expanded = False
              FieldName = 'RsDestin'
              Title.Caption = 'Destinat'#225'rio'
              Width = 168
              Visible = True
              BrConsulta = 0
              BrPermissao = []
              BrValueHalfChecked = False
              BrSaveOnClick = False
            end
            item
              Expanded = False
              FieldName = 'NmCidRem'
              Title.Caption = 'Cidade Destinat'#225'rio'
              Width = 168
              Visible = True
              BrConsulta = 0
              BrPermissao = []
              BrValueHalfChecked = False
              BrSaveOnClick = False
            end
            item
              Expanded = False
              FieldName = 'CdEstRem'
              Title.Caption = 'Cd. Estado'
              Width = 68
              Visible = True
              BrConsulta = 0
              BrPermissao = []
              BrValueHalfChecked = False
              BrSaveOnClick = False
            end
            item
              Alignment = taRightJustify
              Expanded = False
              FieldName = 'VrMercad'
              Title.Caption = 'Vlr. Mercadoria'
              Width = 68
              Visible = True
              BrConsulta = 0
              BrPermissao = []
              BrValueHalfChecked = False
              BrSaveOnClick = False
            end
            item
              Alignment = taRightJustify
              Expanded = False
              FieldName = 'NrPeso'
              Title.Caption = 'Peso'
              Width = 68
              Visible = True
              BrConsulta = 0
              BrPermissao = []
              BrValueHalfChecked = False
              BrSaveOnClick = False
            end
            item
              Alignment = taRightJustify
              Expanded = False
              FieldName = 'NrChvCte'
              Title.Caption = 'Chave CTe'
              Width = 320
              Visible = True
              BrConsulta = 0
              BrPermissao = []
              BrValueHalfChecked = False
              BrSaveOnClick = False
            end>
        end
      end
      object TbsEmpres: TTabSheet
        Caption = 'Empresa da Carga'
        ImageIndex = 2
        ExplicitLeft = 0
        ExplicitTop = 0
        ExplicitWidth = 0
        ExplicitHeight = 0
        object BdgCarEmp: TBrvDbGrid
          AlignWithMargins = True
          Left = 3
          Top = 3
          Width = 716
          Height = 202
          BrShowMemo = True
          BrReadOnlyStyle = []
          BrReadOnlyColor = clBlack
          Align = alClient
          DataSource = DsT014
          TabOrder = 0
          TitleFont.Charset = DEFAULT_CHARSET
          TitleFont.Color = clWindowText
          TitleFont.Height = -11
          TitleFont.Name = 'Tahoma'
          TitleFont.Style = []
          BrDrawColumn.Strings = (
            'N'#227'o remova essas duas linhas de formata'#231#227'o:'
            'CampoTabela;Operador;ValorComparativo;Cor;')
          BrGradeZebrada = True
          Columns = <
            item
              Expanded = False
              FieldName = 'CdEmpres'
              Title.Caption = 'Empresa'
              Width = 50
              Visible = True
              BrConsulta = 0
              BrPermissao = []
              BrValueHalfChecked = False
              BrSaveOnClick = False
            end
            item
              Expanded = False
              FieldName = 'DsEmpres'
              Title.Caption = 'Descri'#231#227'o'
              Width = 350
              Visible = True
              BrConsulta = 0
              BrPermissao = []
              BrValueHalfChecked = False
              BrSaveOnClick = False
            end
            item
              Expanded = False
              FieldName = 'DtAceCar'
              Title.Caption = 'Data do Acerto'
              Width = 110
              Visible = True
              BrConsulta = 0
              BrPermissao = []
              BrValueHalfChecked = False
              BrSaveOnClick = False
            end>
        end
      end
    end
    object BrvDbEdit8: TBrvDbEdit
      Left = 88
      Top = 8
      Width = 642
      Height = 21
      Color = 14541539
      DataField = 'DsDesCar'
      DataSource = DsT016
      TabOrder = 1
      BrAlignment = taLeftJustify
      BrVisibleButton = False
      BrFunctionButton = TpDbConsulta
      BrQueryConsulta = 0
    end
    object BrvDbEdit7: TBrvDbEdit
      Left = 88
      Top = 34
      Width = 86
      Height = 21
      Color = 14541539
      DataField = 'CdMotori'
      DataSource = DsT016
      TabOrder = 2
      BrAlignment = taRightJustify
      BrVisibleButton = False
      BrFunctionButton = TpDbConsulta
      BrQueryConsulta = 0
    end
    object BrvDbEdit1: TBrvDbEdit
      Left = 180
      Top = 34
      Width = 550
      Height = 21
      Color = 14541539
      DataField = 'NmMotori'
      DataSource = DsT016
      TabOrder = 3
      BrAlignment = taLeftJustify
      BrVisibleButton = False
      BrFunctionButton = TpDbConsulta
      BrQueryConsulta = 0
    end
    object BrvDbEdit9: TBrvDbEdit
      Left = 88
      Top = 60
      Width = 86
      Height = 21
      Color = 14541539
      DataField = 'CdVeicul'
      DataSource = DsT016
      TabOrder = 4
      BrAlignment = taRightJustify
      BrVisibleButton = False
      BrFunctionButton = TpDbConsulta
      BrQueryConsulta = 0
    end
    object BrvDbEdit10: TBrvDbEdit
      Left = 180
      Top = 60
      Width = 550
      Height = 21
      Color = 14541539
      DataField = 'NrPlaVei'
      DataSource = DsT016
      TabOrder = 5
      BrAlignment = taLeftJustify
      BrVisibleButton = False
      BrFunctionButton = TpDbConsulta
      BrQueryConsulta = 0
    end
    object EdtDtFecham: TBrvDbEdit
      Left = 88
      Top = 86
      Width = 86
      Height = 21
      Color = 14541539
      DataField = 'DtCarga'
      DataSource = DsT016
      TabOrder = 6
      BrAlignment = taLeftJustify
      BrVisibleButton = False
      BrFunctionButton = TpDbDataHora
      BrDicionario = DmSrvApl.BrvDicionario
      BrQueryConsulta = 0
    end
    object BrvDbEdit2: TBrvDbEdit
      Left = 288
      Top = 86
      Width = 86
      Height = 21
      Color = 14541539
      DataField = 'DtSaiCar'
      DataSource = DsT016
      TabOrder = 7
      BrAlignment = taLeftJustify
      BrVisibleButton = False
      BrFunctionButton = TpDbDataHora
      BrDicionario = DmSrvApl.BrvDicionario
      BrQueryConsulta = 0
    end
    object BrvDbEdit3: TBrvDbEdit
      Left = 461
      Top = 86
      Width = 100
      Height = 21
      Color = 14541539
      DataField = 'DtPreRet'
      DataSource = DsT016
      TabOrder = 8
      BrAlignment = taLeftJustify
      BrVisibleButton = False
      BrFunctionButton = TpDbDataHora
      BrDicionario = DmSrvApl.BrvDicionario
      BrQueryConsulta = 0
    end
    object BrvDbEdit4: TBrvDbEdit
      Left = 619
      Top = 86
      Width = 111
      Height = 21
      Color = 14541539
      DataField = 'DtRetCar'
      DataSource = DsT016
      TabOrder = 9
      BrAlignment = taLeftJustify
      BrVisibleButton = False
      BrFunctionButton = TpDbDataHora
      BrDicionario = DmSrvApl.BrvDicionario
      BrQueryConsulta = 0
    end
    object BrvDbEdit5: TBrvDbEdit
      Left = 88
      Top = 112
      Width = 86
      Height = 21
      Color = 14541539
      DataField = 'KmSaiCar'
      DataSource = DsT016
      TabOrder = 10
      BrAlignment = taRightJustify
      BrVisibleButton = False
      BrFunctionButton = TpDbDataHora
      BrDicionario = DmSrvApl.BrvDicionario
      BrQueryConsulta = 0
    end
    object BrvDbEdit6: TBrvDbEdit
      Left = 288
      Top = 112
      Width = 86
      Height = 21
      Color = 14541539
      DataField = 'KmPreCar'
      DataSource = DsT016
      TabOrder = 11
      BrAlignment = taRightJustify
      BrVisibleButton = False
      BrFunctionButton = TpDbDataHora
      BrDicionario = DmSrvApl.BrvDicionario
      BrQueryConsulta = 0
    end
    object BrvDbEdit11: TBrvDbEdit
      Left = 461
      Top = 112
      Width = 100
      Height = 21
      Color = 14541539
      DataField = 'KmPerCar'
      DataSource = DsT016
      TabOrder = 12
      BrAlignment = taRightJustify
      BrVisibleButton = False
      BrFunctionButton = TpDbDataHora
      BrDicionario = DmSrvApl.BrvDicionario
      BrQueryConsulta = 0
    end
    object BrvDbEdit14: TBrvDbEdit
      Left = 619
      Top = 112
      Width = 111
      Height = 21
      Color = 14541539
      DataField = 'NmArqSeg'
      DataSource = DsT016
      TabOrder = 13
      BrAlignment = taRightJustify
      BrVisibleButton = False
      BrFunctionButton = TpDbDataHora
      BrDicionario = DmSrvApl.BrvDicionario
      BrQueryConsulta = 0
    end
    object BrvDbEdit12: TBrvDbEdit
      Left = 88
      Top = 139
      Width = 86
      Height = 21
      Color = 14541539
      DataField = 'NrMalote'
      DataSource = DsT016
      TabOrder = 14
      BrAlignment = taRightJustify
      BrVisibleButton = False
      BrFunctionButton = TpDbDataHora
      BrDicionario = DmSrvApl.BrvDicionario
      BrQueryConsulta = 0
    end
    object BrvDbEdit20: TBrvDbEdit
      Left = 88
      Top = 165
      Width = 86
      Height = 21
      Color = 14541539
      DataField = 'QtCtrc'
      DataSource = DsT016
      TabOrder = 15
      BrAlignment = taRightJustify
      BrVisibleButton = False
      BrFunctionButton = TpDbDataHora
      BrDicionario = DmSrvApl.BrvDicionario
      BrQueryConsulta = 0
    end
    object BrvDbEdit17: TBrvDbEdit
      Left = 88
      Top = 192
      Width = 86
      Height = 21
      Color = 14541539
      DataField = 'CdEmpres'
      DataSource = DsT016
      TabOrder = 16
      BrAlignment = taRightJustify
      BrVisibleButton = False
      BrFunctionButton = TpDbDataHora
      BrDicionario = DmSrvApl.BrvDicionario
      BrQueryConsulta = 0
    end
    object BrvDbEdit18: TBrvDbEdit
      Left = 180
      Top = 192
      Width = 550
      Height = 21
      Color = 14541539
      DataField = 'DsEmpres'
      DataSource = DsT016
      TabOrder = 17
      BrAlignment = taLeftJustify
      BrVisibleButton = False
      BrFunctionButton = TpDbConsulta
      BrQueryConsulta = 0
    end
    object BrvDbEdit16: TBrvDbEdit
      Left = 288
      Top = 139
      Width = 86
      Height = 21
      Color = 14541539
      DataField = 'NrPesCom'
      DataSource = DsT016
      TabOrder = 18
      BrAlignment = taRightJustify
      BrVisibleButton = False
      BrFunctionButton = TpDbDataHora
      BrDicionario = DmSrvApl.BrvDicionario
      BrQueryConsulta = 0
    end
    object BrvDbEdit19: TBrvDbEdit
      Left = 288
      Top = 165
      Width = 86
      Height = 21
      Color = 14541539
      DataField = 'QtEntCar'
      DataSource = DsT016
      TabOrder = 19
      BrAlignment = taRightJustify
      BrVisibleButton = False
      BrFunctionButton = TpDbDataHora
      BrDicionario = DmSrvApl.BrvDicionario
      BrQueryConsulta = 0
    end
    object BrvDbEdit13: TBrvDbEdit
      Left = 461
      Top = 139
      Width = 100
      Height = 21
      Color = 14541539
      DataField = 'SnComiss'
      DataSource = DsT016
      TabOrder = 20
      BrAlignment = taLeftJustify
      BrVisibleButton = False
      BrFunctionButton = TpDbDataHora
      BrDicionario = DmSrvApl.BrvDicionario
      BrQueryConsulta = 0
    end
    object BrvDbEdit21: TBrvDbEdit
      Left = 461
      Top = 165
      Width = 100
      Height = 21
      Color = 14541539
      DataField = 'PsCarga'
      DataSource = DsT016
      TabOrder = 21
      BrAlignment = taRightJustify
      BrVisibleButton = False
      BrFunctionButton = TpDbDataHora
      BrDicionario = DmSrvApl.BrvDicionario
      BrQueryConsulta = 0
    end
    object BrvDbEdit15: TBrvDbEdit
      Left = 619
      Top = 139
      Width = 111
      Height = 21
      Color = 14541539
      DataField = 'StCarga'
      DataSource = DsT016
      TabOrder = 22
      BrAlignment = taLeftJustify
      BrVisibleButton = False
      BrFunctionButton = TpDbDataHora
      BrDicionario = DmSrvApl.BrvDicionario
      BrQueryConsulta = 0
    end
    object BrvDbEdit22: TBrvDbEdit
      Left = 619
      Top = 165
      Width = 111
      Height = 21
      Color = 14541539
      DataField = 'VrCarga'
      DataSource = DsT016
      TabOrder = 23
      BrAlignment = taRightJustify
      BrVisibleButton = False
      BrFunctionButton = TpDbDataHora
      BrDicionario = DmSrvApl.BrvDicionario
      BrQueryConsulta = 0
    end
  end
  object PnlCabec: TPanel [2]
    Left = 0
    Top = 30
    Width = 742
    Height = 41
    Align = alTop
    BorderStyle = bsSingle
    TabOrder = 2
    DesignSize = (
      738
      37)
    object Label5: TLabel
      AlignWithMargins = True
      Left = 6
      Top = 11
      Width = 34
      Height = 13
      Caption = 'Carga'
      Font.Charset = DEFAULT_CHARSET
      Font.Color = clBlue
      Font.Height = -11
      Font.Name = 'MS Sans Serif'
      Font.Style = [fsBold]
      ParentFont = False
    end
    object BtnLocali: TBrvBitBtn
      Left = 630
      Top = 6
      Width = 100
      Height = 25
      Hint = 'Localizar'
      Anchors = [akTop, akRight]
      Caption = 'Localizar'
      DoubleBuffered = True
      Font.Charset = DEFAULT_CHARSET
      Font.Color = clWindowText
      Font.Height = -11
      Font.Name = 'Tahoma'
      Font.Style = [fsBold]
      Glyph.Data = {
        96090000424D9609000000000000360000002800000028000000140000000100
        1800000000006009000000000000000000000000000000000000008080008080
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
        8080008080AD4416B95E3CB95E3CB95E3CB95E3CB46C49008080008080008080
        008080008080008080B95E3CB95E3CB95E3CB95E3CB46C490080800080800080
        80646360808080808080808080808080C0C0C000808000808000808000808000
        8080008080808080808080808080808080C0C0C0008080008080008080AD4416
        B95E3CE8D1C6B95E3CB95E3CB46C490080800080800080800080800080800080
        80B95E3CE8D1C6B95E3CB95E3CB46C49008080008080008080646360808080E8
        D1C6808080808080C0C0C0008080008080008080008080008080008080808080
        E8D1C6808080808080C0C0C0008080008080008080AD4416B95E3CE8D1C6B95E
        3CB95E3CB46C49008080008080008080008080008080008080B95E3CE8D1C6B9
        5E3CB95E3CB46C49008080008080008080646360808080E8D1C6808080808080
        C0C0C0008080008080008080008080008080008080808080E8D1C68080808080
        80C0C0C0008080008080008080AD4416B95E3CE8D1C6B95E3CB95E3CB46C4900
        8080008080008080008080008080008080B95E3CE8D1C6B95E3CB95E3CB46C49
        008080008080008080646360808080E8D1C6808080808080C0C0C00080800080
        80008080008080008080008080808080E8D1C6808080808080C0C0C000808000
        8080008080AD4416B95E3CB95E3CB95E3CB95E3CB95E3CB95E3CB46C49008080
        008080B95E3CB95E3CB95E3CB95E3CB95E3CB95E3CB46C490080800080800080
        80646360808080808080808080808080808080808080C0C0C000808000808080
        8080808080808080808080808080808080C0C0C0008080008080008080AD4416
        B95E3CB95E3CE8D1C6B95E3CB95E3CB95E3CB46C49B95E3CB95E3CB95E3CE8D1
        C6B95E3CB95E3CB95E3CB95E3CB46C4900808000808000808064636080808080
        8080E8D1C6808080808080808080C0C0C0808080808080808080E8D1C6808080
        808080808080808080C0C0C0008080008080008080AD4416B95E3CB95E3CE8D1
        C6B95E3CB95E3CB95E3C808080B95E3CB95E3CB95E3CE8D1C6B95E3CB95E3CB9
        5E3CB95E3CB46C49008080008080008080646360808080808080E8D1C6808080
        808080808080808080808080808080808080E8D1C68080808080808080808080
        80C0C0C0008080008080008080AD4416B95E3CB95E3CE8D1C6B95E3CB95E3CB9
        5E3C808080B95E3CB95E3CB95E3CE8D1C6B95E3CB95E3CB95E3CB95E3CB46C49
        008080008080008080646360808080808080E8D1C68080808080808080808080
        80808080808080808080E8D1C6808080808080808080808080C0C0C000808000
        8080008080008080AD4416B95E3CB95E3CB95E3CB95E3CB95E3CB46C49B95E3C
        B95E3CB95E3CB95E3CB95E3CB95E3CB95E3CB95E3C0080800080800080800080
        80008080646360808080808080808080808080808080C0C0C080808080808080
        8080808080808080808080808080808080008080008080008080008080008080
        008080AD4416B95E3CE8D1C6B95E3CB95E3CB46C49008080AD4416B95E3CE8D1
        C6B95E3CB95E3CB46C4900808000808000808000808000808000808000808064
        6360808080E8D1C6808080808080C0C0C0008080646360808080E8D1C6808080
        808080C0C0C0008080008080008080008080008080008080008080AD4416B95E
        3CB95E3CB95E3CB95E3CB46C49008080AD4416B95E3CB95E3CB95E3CB95E3CB4
        6C49008080008080008080008080008080008080008080646360808080808080
        808080808080C0C0C0008080646360808080808080808080808080C0C0C00080
        80008080008080008080008080008080008080008080AD4416B95E3CB95E3CB9
        5E3C008080008080008080AD4416B95E3CB95E3CB95E3C008080008080008080
        0080800080800080800080800080806463606463608080808080808080800080
        8000808000808064636080808080808080808000808000808000808000808000
        8080008080008080008080008080AD4416B95E3CE8D1C6B95E3C008080008080
        008080AD4416B95E3CE8D1C6B95E3C0080800080800080800080800080800080
        80008080008080008080646360808080E8D1C680808000808000808000808064
        6360808080E8D1C6808080008080008080008080008080008080008080008080
        008080008080AD4416B95E3CB95E3CB95E3C008080008080008080AD4416B95E
        3CB95E3CB95E3C00808000808000808000808000808000808000808000808000
        8080646360808080808080808080008080008080008080646360808080808080
        8080800080800080800080800080800080800080800080800080800080800080
        80AD4416AD4416AD4416008080008080008080AD4416AD4416AD4416AD441600
        8080008080008080008080008080008080008080008080008080008080646360
        6463606463600080800080800080800080806463606463606463600080800080
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
      OnClick = BtnLocaliClick
      BrTipoBotao = BrBtnLocalizar
      ExplicitLeft = 634
    end
    object EdtCdCarga: TBrvEditNum
      AlignWithMargins = True
      Left = 48
      Top = 8
      Width = 88
      Height = 21
      Font.Charset = ANSI_CHARSET
      Font.Color = clWindowText
      Font.Height = -11
      Font.Name = 'MS Sans Serif'
      Font.Style = []
      ParentFont = False
      TabOrder = 1
      Text = '0'
      BrAlignment = taRightJustify
      BrCasasDecimais = 0
      BrSepararMilhar = False
      BrAsInteger = 0
      BrAsFloatSQL = '0'
      BrVisibleButton = True
      BrFunctionButton = TpConsulta
      BrConfigurar.Strings = (
        'EdtCdCarga;CdCarga;S;S;'
        'EdtDsDescar;DsDescar;S;N;')
      BrDicionario = DmSrvApl.BrvDicionario
      BrQueryCode = 25
      BrRecordar = False
    end
    object EdtDsDescar: TBrvRetCon
      AlignWithMargins = True
      Left = 142
      Top = 8
      Width = 482
      Height = 21
      TabStop = False
      Anchors = [akLeft, akTop, akRight]
      BevelInner = bvLowered
      BevelKind = bkFlat
      BevelWidth = 2
      BorderStyle = bsNone
      Font.Charset = ANSI_CHARSET
      Font.Color = clWindowText
      Font.Height = -11
      Font.Name = 'MS Sans Serif'
      Font.Style = []
      ParentColor = True
      ParentFont = False
      ReadOnly = True
      TabOrder = 2
      ExplicitWidth = 486
    end
  end
  inherited PopCfgFrm: TPopupMenu
    Left = 48
    Top = 0
  end
  inherited ImlPopFrm: TImageList
    Left = 104
    Top = 0
    Bitmap = {
      494C0101030005001C0014001400FFFFFFFFFF10FFFFFFFFFFFFFFFF424D3600
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
  inherited LspS049: TBrvListParam
    Left = 160
    Top = 0
  end
  object DsT016: TDataSource
    DataSet = CPT016
    Left = 350
    Top = 3
  end
  object CPT016: TBrvClientDataSet
    Aggregates = <>
    Params = <>
    ReadOnly = True
    BrShowFieldName = False
    BrQueryCode = 0
    BrDicionario = DmSrvApl.BrvDicionario
    BrType = VqNormal
    BrGravaLog = True
    BrUserCode = 0
    Left = 396
    Top = 3
  end
  object DsT017: TDataSource
    DataSet = CPT017
    Left = 446
    Top = 3
  end
  object CPT017: TBrvClientDataSet
    Aggregates = <>
    Params = <>
    ReadOnly = True
    BrShowFieldName = False
    BrQueryCode = 45
    BrDicionario = DmSrvApl.BrvDicionario
    BrType = VqNormal
    BrGravaLog = True
    BrUserCode = 0
    Left = 492
    Top = 3
  end
  object CPT014: TBrvClientDataSet
    Aggregates = <>
    Params = <>
    ReadOnly = True
    BrShowFieldName = False
    BrQueryCode = 45
    BrDicionario = DmSrvApl.BrvDicionario
    BrType = VqNormal
    BrGravaLog = True
    BrUserCode = 0
    Left = 576
    Top = 3
  end
  object DsT014: TDataSource
    DataSet = CPT014
    Left = 534
    Top = 3
  end
  object BrvListParam: TBrvListParam
    Left = 632
    Top = 3
  end
  object PopCTRC: TPopupMenu
    Left = 568
    Top = 344
    object Detalhar1: TMenuItem
      Caption = 'Detalhar'
      OnClick = Detalhar1Click
    end
  end
end
