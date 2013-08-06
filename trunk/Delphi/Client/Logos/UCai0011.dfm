inherited Cai0011: TCai0011
  Left = 368
  Top = 207
  Caption = 'Cai0011 - Atualizar Aplica'#231#227'o Cliente'
  ClientHeight = 266
  ClientWidth = 790
  OnCreate = FormCreate
  ExplicitWidth = 796
  ExplicitHeight = 291
  PixelsPerInch = 96
  TextHeight = 13
  inherited Panel1: TPanel
    Width = 790
    Height = 266
    ExplicitWidth = 790
    ExplicitHeight = 295
    inherited Panel2: TPanel
      Top = 260
      Width = 784
      Height = 1
      Visible = False
      ExplicitTop = 289
      ExplicitWidth = 784
      ExplicitHeight = 1
    end
    object FlbDireto: TFileListBox
      Left = 460
      Top = 122
      Width = 145
      Height = 97
      ItemHeight = 13
      TabOrder = 1
      Visible = False
    end
    object PgcAtuali: TPageControl
      Left = 1
      Top = 1
      Width = 784
      Height = 259
      ActivePage = TbsClient
      Align = alClient
      TabOrder = 2
      ExplicitHeight = 288
      object TbsDesenv: TTabSheet
        Caption = 'TbsDesenv'
        ExplicitHeight = 260
        object DBGrid1: TDBGrid
          Left = 0
          Top = 0
          Width = 703
          Height = 231
          Align = alLeft
          DataSource = DtsAplCli
          TabOrder = 0
          TitleFont.Charset = DEFAULT_CHARSET
          TitleFont.Color = clWindowText
          TitleFont.Height = -11
          TitleFont.Name = 'Tahoma'
          TitleFont.Style = []
          Columns = <
            item
              Expanded = False
              FieldName = 'DtArquiv'
              Visible = True
            end
            item
              Expanded = False
              FieldName = 'NmArqCli'
              Width = 255
              Visible = True
            end
            item
              Expanded = False
              FieldName = 'TpArquiv'
              Visible = True
            end
            item
              Expanded = False
              FieldName = 'SnCompac'
              Visible = True
            end
            item
              Expanded = False
              FieldName = 'TpOperac'
              Visible = True
            end
            item
              Expanded = False
              FieldName = 'BiArquiv'
              Visible = True
            end
            item
              Expanded = False
              FieldName = 'QtCaract'
              Visible = True
            end>
        end
        object Button3: TButton
          Left = 703
          Top = 56
          Width = 75
          Height = 25
          Caption = 'Processar'
          TabOrder = 1
          OnClick = Button3Click
        end
        object Button2: TButton
          Left = 703
          Top = 28
          Width = 75
          Height = 25
          Caption = 'Validar Server'
          TabOrder = 2
          OnClick = Button2Click
        end
        object Button1: TButton
          Left = 703
          Top = 1
          Width = 75
          Height = 25
          Caption = 'Arquivos'
          TabOrder = 3
          OnClick = Button1Click
        end
        object Button4: TButton
          Left = 703
          Top = 87
          Width = 75
          Height = 25
          Caption = 'Tudo'
          TabOrder = 4
          OnClick = Button4Click
        end
      end
      object TbsClient: TTabSheet
        Caption = 'TbsClient'
        ImageIndex = 1
        ExplicitHeight = 260
        object LblProces: TLabel
          Left = 8
          Top = 78
          Width = 753
          Height = 13
          AutoSize = False
          Caption = 'Processando ...'
          Font.Charset = DEFAULT_CHARSET
          Font.Color = clBlue
          Font.Height = -11
          Font.Name = 'Tahoma'
          Font.Style = [fsBold]
          ParentFont = False
        end
        object Label2: TLabel
          Left = 8
          Top = 32
          Width = 256
          Height = 13
          Caption = 'Essa opera'#231#227'o pode demorar alguns minutos.'
          Font.Charset = DEFAULT_CHARSET
          Font.Color = clWindowText
          Font.Height = -11
          Font.Name = 'Tahoma'
          Font.Style = [fsBold]
          ParentFont = False
        end
        object Label3: TLabel
          Left = 8
          Top = 55
          Width = 448
          Height = 13
          Caption = 
            'Para evitar transtornos, pedimos por favor que aguarde o t'#233'rmino' +
            ' do processo.'
          Font.Charset = DEFAULT_CHARSET
          Font.Color = clWindowText
          Font.Height = -11
          Font.Name = 'Tahoma'
          Font.Style = [fsBold]
          ParentFont = False
        end
        object Label1: TLabel
          Left = 8
          Top = 9
          Width = 274
          Height = 13
          Caption = 'Procurando por novas atualiza'#231#245'es da aplica'#231#227'o.'
          Font.Charset = DEFAULT_CHARSET
          Font.Color = clWindowText
          Font.Height = -11
          Font.Name = 'Tahoma'
          Font.Style = [fsBold]
          ParentFont = False
        end
        object AmtCopia: TAnimate
          Left = 7
          Top = 118
          Width = 272
          Height = 60
          Active = True
          Color = clMenu
          CommonAVI = aviCopyFiles
          ParentColor = False
          StopFrame = 31
        end
        object PgbProces: TProgressBar
          Left = 5
          Top = 190
          Width = 765
          Height = 16
          Step = 1
          TabOrder = 1
          Visible = False
        end
        object PgbCompac: TProgressBar
          Left = 5
          Top = 211
          Width = 765
          Height = 16
          TabOrder = 2
          Visible = False
        end
      end
    end
  end
  object CdsAplCli: TClientDataSet
    Aggregates = <>
    Filtered = True
    Params = <>
    Left = 709
    Top = 144
    object CdsAplCliDtArquiv: TDateTimeField
      FieldName = 'DtArquiv'
    end
    object CdsAplCliNmArqCli: TStringField
      FieldName = 'NmArqCli'
      Size = 100
    end
    object CdsAplCliTpArquiv: TStringField
      FieldName = 'TpArquiv'
      Size = 1
    end
    object CdsAplCliSnCompac: TStringField
      FieldName = 'SnCompac'
      Size = 1
    end
    object CdsAplCliTpOperac: TStringField
      FieldName = 'TpOperac'
      Size = 3
    end
    object CdsAplCliBiArquiv: TBlobField
      FieldName = 'BiArquiv'
    end
    object CdsAplCliQtCaract: TIntegerField
      FieldName = 'QtCaract'
    end
  end
  object DtsAplCli: TDataSource
    DataSet = CdsAplCli
    Left = 709
    Top = 176
  end
end
