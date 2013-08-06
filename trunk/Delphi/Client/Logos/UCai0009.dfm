inherited Cai0009: TCai0009
  Left = 271
  Top = 187
  Caption = 'Cai0009 - Selecionar Empresas'
  ClientHeight = 312
  ClientWidth = 501
  OnCreate = FormCreate
  OnShow = FormShow
  ExplicitWidth = 507
  ExplicitHeight = 337
  PixelsPerInch = 96
  TextHeight = 13
  inherited Panel1: TPanel
    Width = 501
    Height = 312
    ExplicitWidth = 501
    ExplicitHeight = 312
    object Splitter1: TSplitter [0]
      Left = 247
      Top = 1
      Height = 276
      ExplicitLeft = 223
      ExplicitTop = 36
      ExplicitHeight = 439
    end
    inherited Panel2: TPanel
      Top = 277
      Width = 497
      ExplicitTop = 277
      ExplicitWidth = 497
      inherited BbtOk: TBrvBitBtn
        Left = 162
        Width = 82
        OnClick = BbtOkClick
        ExplicitLeft = 162
        ExplicitWidth = 82
      end
      inherited BbtCancel: TBrvBitBtn
        Left = 251
        Width = 82
        ExplicitLeft = 251
        ExplicitWidth = 82
      end
    end
    object Panel3: TPanel
      Left = 1
      Top = 1
      Width = 246
      Height = 276
      Align = alLeft
      BevelOuter = bvNone
      TabOrder = 1
      object Panel5: TPanel
        Left = 0
        Top = 0
        Width = 246
        Height = 22
        Align = alTop
        Caption = 'Grupo'
        Font.Charset = DEFAULT_CHARSET
        Font.Color = clBlue
        Font.Height = -11
        Font.Name = 'Tahoma'
        Font.Style = [fsBold]
        ParentFont = False
        TabOrder = 0
      end
      object DbgGrupo: TBrvDbGrid
        Left = 0
        Top = 22
        Width = 246
        Height = 254
        BrShowMemo = True
        BrReadOnlyStyle = []
        BrReadOnlyColor = clBlack
        Align = alClient
        DataSource = DpGruEmp
        Options = [dgEditing, dgTitles, dgIndicator, dgColumnResize, dgColLines, dgRowLines, dgTabs, dgCancelOnExit]
        TabOrder = 1
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
            ButtonStyle = cbsNone
            Expanded = False
            FieldName = 'SnMarcad'
            Title.Caption = 'S/N'
            Title.Font.Charset = DEFAULT_CHARSET
            Title.Font.Color = clWindowText
            Title.Font.Height = -11
            Title.Font.Name = 'Tahoma'
            Title.Font.Style = [fsBold]
            Width = 26
            Visible = True
            BrConsulta = 0
            BrPermissao = []
            BrValueChecked = 'S'
            BrValueUnchecked = 'N'
            BrValueHalfChecked = False
            BrSaveOnClick = True
          end
          item
            Expanded = False
            FieldName = 'DsGruEmp'
            ReadOnly = True
            Title.Caption = 'Grupo'
            Title.Font.Charset = DEFAULT_CHARSET
            Title.Font.Color = clWindowText
            Title.Font.Height = -11
            Title.Font.Name = 'Tahoma'
            Title.Font.Style = [fsBold]
            Width = 180
            Visible = True
            BrConsulta = 0
            BrPermissao = []
            BrValueHalfChecked = False
            BrSaveOnClick = False
          end>
      end
    end
    object Panel4: TPanel
      Left = 250
      Top = 1
      Width = 248
      Height = 276
      Align = alClient
      BevelOuter = bvNone
      TabOrder = 2
      object Panel6: TPanel
        Left = 0
        Top = 0
        Width = 248
        Height = 22
        Align = alTop
        Caption = 'Empresas'
        Font.Charset = DEFAULT_CHARSET
        Font.Color = clBlue
        Font.Height = -11
        Font.Name = 'Tahoma'
        Font.Style = [fsBold]
        ParentFont = False
        TabOrder = 0
      end
      object DbgEmpres: TBrvDbGrid
        Left = 0
        Top = 22
        Width = 248
        Height = 254
        BrShowMemo = True
        BrReadOnlyStyle = []
        BrReadOnlyColor = clBlack
        Align = alClient
        DataSource = DpUsrEmp
        Options = [dgEditing, dgTitles, dgIndicator, dgColumnResize, dgColLines, dgRowLines, dgTabs, dgCancelOnExit]
        TabOrder = 1
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
            ButtonStyle = cbsNone
            Expanded = False
            FieldName = 'SnEmpIni'
            Title.Caption = 'S/N'
            Title.Font.Charset = DEFAULT_CHARSET
            Title.Font.Color = clWindowText
            Title.Font.Height = -11
            Title.Font.Name = 'Tahoma'
            Title.Font.Style = [fsBold]
            Width = 26
            Visible = True
            BrConsulta = 0
            BrPermissao = []
            BrValueChecked = 'S'
            BrValueUnchecked = 'N'
            BrValueHalfChecked = False
            BrSaveOnClick = True
          end
          item
            Expanded = False
            FieldName = 'DsEmpres'
            ReadOnly = True
            Title.Caption = 'Empresa'
            Title.Font.Charset = DEFAULT_CHARSET
            Title.Font.Color = clWindowText
            Title.Font.Height = -11
            Title.Font.Name = 'Tahoma'
            Title.Font.Style = [fsBold]
            Width = 180
            Visible = True
            BrConsulta = 0
            BrPermissao = []
            BrValueHalfChecked = False
            BrSaveOnClick = False
          end>
      end
    end
  end
  object QpGruEmp: TBrvClientDataSet
    Aggregates = <>
    Params = <>
    BeforeInsert = QpGruEmpBeforeInsert
    AfterPost = QpGruEmpAfterPost
    BeforeDelete = QpGruEmpBeforeInsert
    BrShowFieldName = False
    BrQueryCode = 0
    BrDicionario = DmSrvApl.BrvDicionario
    BrType = VqNormal
    BrGravaLog = True
    BrUserCode = 0
    Left = 40
    Top = 120
  end
  object DpGruEmp: TDataSource
    DataSet = QpGruEmp
    Left = 104
    Top = 120
  end
  object QpUsrEmp: TBrvClientDataSet
    Aggregates = <>
    Filtered = True
    Params = <>
    BeforeInsert = QpGruEmpBeforeInsert
    BeforeDelete = QpGruEmpBeforeInsert
    BrShowFieldName = False
    BrQueryCode = 0
    BrDicionario = DmSrvApl.BrvDicionario
    BrType = VqNormal
    BrGravaLog = True
    BrUserCode = 0
    Left = 328
    Top = 120
  end
  object DpUsrEmp: TDataSource
    DataSet = QpUsrEmp
    Left = 392
    Top = 120
  end
end
