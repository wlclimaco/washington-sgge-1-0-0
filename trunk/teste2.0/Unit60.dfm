inherited FRMNFENTRADAS: TFRMNFENTRADAS
  Left = 8
  Top = 109
  Width = 1024
  Height = 708
  Caption = 'FRMNFENTRADAS'
  OldCreateOrder = True
  PixelsPerInch = 96
  TextHeight = 13
  object Label31: TLabel [0]
    Left = 552
    Top = 8
    Width = 25
    Height = 13
    Caption = 'Desc'
  end
  inherited Panel1: TPanel
    Width = 1016
    Height = 609
    object PageControl1: TPageControl
      Left = 1
      Top = 1
      Width = 1014
      Height = 607
      ActivePage = TabSheet1
      Align = alClient
      TabOrder = 0
      object TabSheet1: TTabSheet
        Caption = 'Nota Fiscal'
        object Label1: TLabel
          Left = 32
          Top = 0
          Width = 49
          Height = 13
          Caption = 'Dcnumero'
          FocusControl = DBEdit1
        end
        object Label2: TLabel
          Left = 184
          Top = 0
          Width = 36
          Height = 13
          Caption = 'Dcserie'
          FocusControl = DBEdit2
        end
        object Label3: TLabel
          Left = 248
          Top = 0
          Width = 43
          Height = 13
          Caption = 'Dcordem'
          FocusControl = DBEdit3
        end
        object Label4: TLabel
          Left = 320
          Top = 0
          Width = 31
          Height = 13
          Caption = 'Dctipo'
        end
        object Label5: TLabel
          Left = 432
          Top = 0
          Width = 30
          Height = 13
          Caption = 'Vlnota'
          FocusControl = DBEdit5
        end
        object Label6: TLabel
          Left = 32
          Top = 48
          Width = 30
          Height = 13
          Caption = 'Vlicms'
          FocusControl = DBEdit6
        end
        object Label7: TLabel
          Left = 139
          Top = 48
          Width = 19
          Height = 13
          Caption = 'Vlipi'
          FocusControl = DBEdit7
        end
        object Label8: TLabel
          Left = 336
          Top = 48
          Width = 30
          Height = 13
          Caption = 'Vlfrete'
          FocusControl = DBEdit8
        end
        object Label9: TLabel
          Left = 32
          Top = 144
          Width = 64
          Height = 13
          Caption = 'Cdfornecedor'
          FocusControl = DBEdit9
        end
        object Label10: TLabel
          Left = 480
          Top = 48
          Width = 22
          Height = 13
          Caption = 'Cfop'
          FocusControl = DBEdit10
        end
        object Label11: TLabel
          Left = 32
          Top = 96
          Width = 47
          Height = 13
          Caption = 'Dtentrada'
          FocusControl = DBEdit11
        end
        object Label12: TLabel
          Left = 192
          Top = 96
          Width = 49
          Height = 13
          Caption = 'Dtemissao'
          FocusControl = DBEdit12
        end
        object Label14: TLabel
          Left = 344
          Top = 96
          Width = 45
          Height = 13
          Caption = 'Cdpedido'
          FocusControl = DBEdit14
        end
        object Label15: TLabel
          Left = 32
          Top = 184
          Width = 43
          Height = 13
          Caption = 'Natureza'
          FocusControl = DBEdit15
        end
        object Label16: TLabel
          Left = 240
          Top = 48
          Width = 17
          Height = 13
          Caption = 'Vlst'
          FocusControl = DBEdit16
        end
        object DBText3: TDBText
          Left = 184
          Top = 160
          Width = 489
          Height = 17
          DataField = 'RAZAO'
          DataSource = DataSource5
          Font.Charset = DEFAULT_CHARSET
          Font.Color = clWindowText
          Font.Height = -13
          Font.Name = 'MS Sans Serif'
          Font.Style = []
          ParentFont = False
        end
        object DBText4: TDBText
          Left = 184
          Top = 200
          Width = 489
          Height = 17
          DataField = 'Descricao'
          DataSource = DataSource6
          Font.Charset = DEFAULT_CHARSET
          Font.Color = clWindowText
          Font.Height = -13
          Font.Name = 'MS Sans Serif'
          Font.Style = []
          ParentFont = False
        end
        object DBEdit1: TDBEdit
          Left = 32
          Top = 16
          Width = 134
          Height = 21
          DataField = 'Dcnumero'
          DataSource = DataSource1
          TabOrder = 0
          OnChange = DBEdit4Change
          OnExit = DBEdit1Exit
        end
        object DBEdit2: TDBEdit
          Left = 184
          Top = 16
          Width = 43
          Height = 21
          DataField = 'Dcserie'
          DataSource = DataSource1
          TabOrder = 1
          OnChange = DBEdit2Exit
          OnExit = DBEdit2Exit
        end
        object DBEdit3: TDBEdit
          Left = 248
          Top = 16
          Width = 30
          Height = 21
          DataField = 'Dcordem'
          DataSource = DataSource1
          TabOrder = 2
          OnChange = DBEdit3Exit
          OnExit = DBEdit3Exit
        end
        object DBEdit5: TDBEdit
          Left = 432
          Top = 16
          Width = 134
          Height = 21
          DataField = 'Vlnota'
          DataSource = DataSource1
          TabOrder = 3
        end
        object DBEdit6: TDBEdit
          Left = 32
          Top = 64
          Width = 89
          Height = 21
          DataField = 'Vlicms'
          DataSource = DataSource1
          TabOrder = 4
        end
        object DBEdit7: TDBEdit
          Left = 136
          Top = 64
          Width = 89
          Height = 21
          DataField = 'Vlipi'
          DataSource = DataSource1
          TabOrder = 5
        end
        object DBEdit8: TDBEdit
          Left = 336
          Top = 64
          Width = 134
          Height = 21
          DataField = 'Vlfrete'
          DataSource = DataSource1
          TabOrder = 6
        end
        object DBEdit9: TDBEdit
          Left = 32
          Top = 160
          Width = 134
          Height = 21
          DataField = 'Cdfornecedor'
          DataSource = DataSource1
          TabOrder = 7
          OnChange = DBEdit9Exit
          OnExit = DBEdit9Exit
          OnKeyDown = DBEdit9KeyDown
        end
        object DBEdit10: TDBEdit
          Left = 480
          Top = 64
          Width = 87
          Height = 21
          DataField = 'Cfop'
          DataSource = DataSource1
          TabOrder = 8
        end
        object DBEdit11: TDBEdit
          Left = 32
          Top = 112
          Width = 134
          Height = 21
          DataField = 'Dtentrada'
          DataSource = DataSource1
          TabOrder = 9
        end
        object DBEdit12: TDBEdit
          Left = 192
          Top = 112
          Width = 134
          Height = 21
          DataField = 'Dtemissao'
          DataSource = DataSource1
          TabOrder = 10
        end
        object DBEdit14: TDBEdit
          Left = 344
          Top = 112
          Width = 89
          Height = 21
          DataField = 'Cdpedido'
          DataSource = DataSource1
          TabOrder = 11
        end
        object DBEdit15: TDBEdit
          Left = 32
          Top = 200
          Width = 134
          Height = 21
          DataField = 'Natureza'
          DataSource = DataSource1
          TabOrder = 12
          OnChange = DBEdit15Exit
          OnExit = DBEdit15Exit
          OnKeyDown = DBEdit15KeyDown
        end
        object DBEdit16: TDBEdit
          Left = 240
          Top = 64
          Width = 73
          Height = 21
          DataField = 'Vlst'
          DataSource = DataSource1
          TabOrder = 13
        end
        object GroupBox1: TGroupBox
          Left = 8
          Top = 240
          Width = 817
          Height = 289
          Caption = 'Itens'
          TabOrder = 14
          object Panel4: TPanel
            Left = 4
            Top = 12
            Width = 809
            Height = 57
            TabOrder = 0
            object DBText1: TDBText
              Left = 191
              Top = 27
              Width = 146
              Height = 15
              DataField = 'PRODUTO'
              DataSource = DataSource2
            end
            object DBText2: TDBText
              Left = 379
              Top = 27
              Width = 33
              Height = 18
              DataField = 'UNIDADE'
              DataSource = DataSource2
            end
            object Label23: TLabel
              Left = 8
              Top = 8
              Width = 37
              Height = 13
              Caption = 'Produto'
            end
            object Label27: TLabel
              Left = 168
              Top = 8
              Width = 87
              Height = 13
              Caption = 'Descri'#231#227'o produto'
            end
            object Label28: TLabel
              Left = 376
              Top = 8
              Width = 37
              Height = 13
              Caption = 'uni med'
            end
            object Label29: TLabel
              Left = 424
              Top = 8
              Width = 55
              Height = 13
              Caption = 'Quantidade'
            end
            object Label30: TLabel
              Left = 488
              Top = 8
              Width = 61
              Height = 13
              Caption = 'Valor unitario'
            end
            object Label32: TLabel
              Left = 568
              Top = 8
              Width = 22
              Height = 13
              Caption = 'Porc'
            end
            object Label33: TLabel
              Left = 632
              Top = 8
              Width = 42
              Height = 13
              Caption = 'Valor Suj'
            end
            object Label34: TLabel
              Left = 691
              Top = 8
              Width = 58
              Height = 13
              Caption = 'Valor Venda'
            end
            object SpeedButton2: TSpeedButton
              Left = 120
              Top = 24
              Width = 33
              Height = 22
              Caption = 'I'
              OnClick = SpeedButton2Click
            end
            object SpeedButton3: TSpeedButton
              Left = 752
              Top = 24
              Width = 49
              Height = 22
              OnClick = SpeedButton3Click
            end
            object Edit1: TEdit
              Left = 8
              Top = 24
              Width = 105
              Height = 21
              TabOrder = 0
              OnExit = Edit1Exit
              OnKeyDown = Edit1KeyDown
            end
            object Edit2: TEdit
              Left = 424
              Top = 24
              Width = 57
              Height = 21
              TabOrder = 1
            end
            object Edit3: TEdit
              Left = 488
              Top = 24
              Width = 57
              Height = 21
              TabOrder = 2
              OnExit = Edit3Exit
            end
          end
          object DBGrid1: TDBGrid
            Left = 8
            Top = 72
            Width = 793
            Height = 201
            DataSource = DataSource3
            TabOrder = 1
            TitleFont.Charset = DEFAULT_CHARSET
            TitleFont.Color = clWindowText
            TitleFont.Height = -11
            TitleFont.Name = 'MS Sans Serif'
            TitleFont.Style = []
            OnDblClick = DBGrid1DblClick
          end
        end
        object DBEdit4: TDBEdit
          Left = 320
          Top = 16
          Width = 57
          Height = 21
          DataField = 'Dctipo'
          DataSource = DataSource1
          TabOrder = 15
          OnChange = DBEdit4Change
          OnExit = DBEdit4Change
        end
      end
      object TabSheet2: TTabSheet
        Caption = 'Titulos pagar'
        ImageIndex = 1
        object Panel5: TPanel
          Left = 0
          Top = 8
          Width = 833
          Height = 473
          TabOrder = 0
          object Label17: TLabel
            Left = 16
            Top = 8
            Width = 49
            Height = 13
            Caption = 'Dcnumero'
          end
          object Label18: TLabel
            Left = 160
            Top = 8
            Width = 36
            Height = 13
            Caption = 'Dcserie'
          end
          object Label19: TLabel
            Left = 216
            Top = 8
            Width = 43
            Height = 13
            Caption = 'Dcordem'
          end
          object Label20: TLabel
            Left = 280
            Top = 8
            Width = 31
            Height = 13
            Caption = 'Dctipo'
          end
          object Label21: TLabel
            Left = 368
            Top = 8
            Width = 36
            Height = 13
            Caption = 'Parcela'
          end
          object Label22: TLabel
            Left = 448
            Top = 8
            Width = 66
            Height = 13
            Caption = 'Dtvencimento'
          end
          object Label24: TLabel
            Left = 576
            Top = 8
            Width = 44
            Height = 13
            Caption = 'Vlparcela'
          end
          object Label25: TLabel
            Left = 16
            Top = 56
            Width = 19
            Height = 13
            Caption = 'Obs'
          end
          object Label26: TLabel
            Left = 672
            Top = 8
            Width = 64
            Height = 13
            Caption = 'Cdfornecedor'
          end
          object DBGrid2: TDBGrid
            Left = 16
            Top = 104
            Width = 809
            Height = 361
            DataSource = DataSource4
            TabOrder = 0
            TitleFont.Charset = DEFAULT_CHARSET
            TitleFont.Color = clWindowText
            TitleFont.Height = -11
            TitleFont.Name = 'MS Sans Serif'
            TitleFont.Style = []
            OnDblClick = DBGrid2DblClick
          end
          object BitBtn1: TBitBtn
            Left = 624
            Top = 72
            Width = 129
            Height = 25
            Caption = 'Inserir Titulo'
            TabOrder = 1
            OnClick = BitBtn1Click
          end
          object Edit5: TEdit
            Left = 16
            Top = 32
            Width = 65
            Height = 21
            TabOrder = 2
          end
          object Edit6: TEdit
            Left = 160
            Top = 32
            Width = 41
            Height = 21
            TabOrder = 3
          end
          object Edit7: TEdit
            Left = 216
            Top = 32
            Width = 49
            Height = 21
            TabOrder = 4
          end
          object Edit8: TEdit
            Left = 280
            Top = 32
            Width = 73
            Height = 21
            TabOrder = 5
          end
          object Edit9: TEdit
            Left = 368
            Top = 32
            Width = 65
            Height = 21
            TabOrder = 6
          end
          object Edit10: TEdit
            Left = 456
            Top = 32
            Width = 73
            Height = 21
            TabOrder = 7
          end
          object Edit11: TEdit
            Left = 568
            Top = 32
            Width = 73
            Height = 21
            TabOrder = 8
          end
          object Edit12: TEdit
            Left = 672
            Top = 32
            Width = 81
            Height = 21
            TabOrder = 9
          end
          object Edit13: TEdit
            Left = 16
            Top = 72
            Width = 553
            Height = 21
            TabOrder = 10
          end
        end
      end
    end
  end
  inherited Panel3: TPanel
    Width = 1016
    object Label13: TLabel
      Left = 24
      Top = 24
      Width = 230
      Height = 29
      Caption = 'Entrada Nota Fiscal'
      Font.Charset = DEFAULT_CHARSET
      Font.Color = clWindowText
      Font.Height = -24
      Font.Name = 'MS Sans Serif'
      Font.Style = [fsBold]
      ParentFont = False
    end
  end
  inherited Panel2: TPanel
    Left = 880
    Width = 139
    Height = 592
    Align = alCustom
    inherited SpeedButton1: TSpeedButton
      Top = 184
      OnClick = SpeedButton1Click
    end
    inherited DBButton1: TDBButton
      Enabled = True
      DataSource = DataSource1
    end
    inherited DBButton2: TDBButton
      DataSource = DataSource1
    end
    inherited DBButton3: TDBButton
      Enabled = True
      DataSource = DataSource1
    end
    inherited DBButton4: TDBButton
      Left = 752
      Top = 3
      Enabled = True
      DataSource = DataSource1
    end
    inherited DBButton5: TDBButton
      Left = 664
      Top = 3
      DataSource = DataSource1
    end
    inherited DBButton6: TDBButton
      Top = 160
      Enabled = True
      DataSource = DataSource1
    end
    inherited DBNavigator1: TDBNavigator
      DataSource = DataSource1
      Hints.Strings = ()
    end
    object DBButton7: TDBButton
      Left = 24
      Top = 136
      Width = 97
      Height = 25
      Caption = 'Gravar'
      Enabled = False
      TabOrder = 7
      Glyph.Data = {
        360C0000424D360C000000000000360000002800000030000000100000000100
        200000000000000C000000000000000000000000000000000000FF00FF00FF00
        FF00FF00FF00FF00FF00FF00FF00FF00FF00FF00FF00FF00FF00FF00FF00FF00
        FF00FF00FF00FF00FF00FF00FF00FF00FF00FF00FF00FF00FF00FF00FF00FF00
        FF00FF00FF00FF00FF00FF00FF00FF00FF00FF00FF00FF00FF00FF00FF00FF00
        FF00FF00FF00FF00FF00FF00FF00FF00FF00FF00FF00FF00FF00FF00FF00FF00
        FF00FF00FF00FF00FF00FF00FF00FF00FF00FF00FF00FF00FF00FF00FF00FF00
        FF00FF00FF00FF00FF00FF00FF00FF00FF00FF00FF00FF00FF00FF00FF00FF00
        FF00FF00FF00FF00FF00FF00FF00FF00FF00FF00FF00FF00FF00FF00FF00FF00
        FF00FF00FF00FF00FF00FF00FF00FF00FF00FF00FF00FF00FF00FF00FF00FF00
        FF00FF00FF00FF00FF00FF00FF00FF00FF00FF00FF00FF00FF00FF00FF00FF00
        FF00FF00FF00FF00FF00FF00FF00FF00FF00FF00FF00FF00FF00FF00FF00FF00
        FF00FF00FF00FF00FF00FF00FF00FF00FF00FF00FF00FF00FF00FF00FF00FF00
        FF00FF00FF00FF00FF00FF00FF00FF00FF00FF00FF00FF00FF00FF00FF00FF00
        FF00FF00FF00FF00FF00FF00FF000066000000660000FF00FF00FF00FF00FF00
        FF00FF00FF00FF00FF00FF00FF00FF00FF00FF00FF00FF00FF00FF00FF00FF00
        FF00FF00FF00FF00FF00FF00FF006565650065656500FF00FF00FF00FF00FF00
        FF00FF00FF00FF00FF00FF00FF00FF00FF00FF00FF00FF00FF00FF00FF00FF00
        FF00FF00FF00FF00FF00FF00FF00004B0000004B0000FF00FF00FF00FF00FF00
        FF00FF00FF00FF00FF00FF00FF00FF00FF00FF00FF00FF00FF00FF00FF00FF00
        FF00FF00FF00FF00FF00006600001EB231001FB1330000660000FF00FF00FF00
        FF00FF00FF00FF00FF00FF00FF00FF00FF00FF00FF00FF00FF00FF00FF00FF00
        FF00FF00FF00FF00FF00656565009A9A9A009A9A9A0065656500FF00FF00FF00
        FF00FF00FF00FF00FF00FF00FF00FF00FF00FF00FF00FF00FF00FF00FF00FF00
        FF00FF00FF00FF00FF00004B00000F9E1C000F9D1E00004B0000FF00FF00FF00
        FF00FF00FF00FF00FF00FF00FF00FF00FF00FF00FF00FF00FF00FF00FF00FF00
        FF00FF00FF000066000031C24F0022B738001AB02D0021B4370000660000FF00
        FF00FF00FF00FF00FF00FF00FF00FF00FF00FF00FF00FF00FF00FF00FF00FF00
        FF00FF00FF0065656500ABABAB009E9E9E00979797009C9C9C0065656500FF00
        FF00FF00FF00FF00FF00FF00FF00FF00FF00FF00FF00FF00FF00FF00FF00FF00
        FF00FF00FF00004B00001CB1350011A422000C9C190011A02100004B0000FF00
        FF00FF00FF00FF00FF00FF00FF00FF00FF00FF00FF00FF00FF00FF00FF00FF00
        FF000066000047D36D003BCB5E0025A83B00006600001BA92E001DB132000066
        0000FF00FF00FF00FF00FF00FF00FF00FF00FF00FF00FF00FF00FF00FF00FF00
        FF0065656500BFBFBF00B5B5B500989898006565650094949400999999006565
        6500FF00FF00FF00FF00FF00FF00FF00FF00FF00FF00FF00FF00FF00FF00FF00
        FF00004B00002EC6520024BC430013922400004B00000D931A000E9D1D00004B
        0000FF00FF00FF00FF00FF00FF00FF00FF00FF00FF00FF00FF00FF00FF000066
        00004FD6790053DE7F0031B54D0000660000FF00FF0000660000179D27001EAE
        310000660000FF00FF00FF00FF00FF00FF00FF00FF00FF00FF00FF00FF006565
        6500C4C4C400CACACA00A5A5A50065656500FF00FF00656565008C8C8C009898
        980065656500FF00FF00FF00FF00FF00FF00FF00FF00FF00FF00FF00FF00004B
        000035CA5E0039D465001CA13400004B0000FF00FF00004B00000A8615000F99
        1C00004B0000FF00FF00FF00FF00FF00FF00FF00FF00FF00FF00FF00FF00FF00
        FF000066000041C5630000660000FF00FF00FF00FF00FF00FF00FF00FF000066
        000019AA2B0000660000FF00FF00FF00FF00FF00FF00FF00FF00FF00FF00FF00
        FF0065656500B5B5B50065656500FF00FF00FF00FF00FF00FF00FF00FF006565
        65009393930065656500FF00FF00FF00FF00FF00FF00FF00FF00FF00FF00FF00
        FF00004B000029B54800004B0000FF00FF00FF00FF00FF00FF00FF00FF00004B
        00000C951800004B0000FF00FF00FF00FF00FF00FF00FF00FF00FF00FF00FF00
        FF00FF00FF0000660000FF00FF00FF00FF00FF00FF00FF00FF00FF00FF00FF00
        FF0000660000149D210000660000FF00FF00FF00FF00FF00FF00FF00FF00FF00
        FF00FF00FF0065656500FF00FF00FF00FF00FF00FF00FF00FF00FF00FF00FF00
        FF00656565008A8A8A0065656500FF00FF00FF00FF00FF00FF00FF00FF00FF00
        FF00FF00FF00004B0000FF00FF00FF00FF00FF00FF00FF00FF00FF00FF00FF00
        FF00004B000009861100004B0000FF00FF00FF00FF00FF00FF00FF00FF00FF00
        FF00FF00FF00FF00FF00FF00FF00FF00FF00FF00FF00FF00FF00FF00FF00FF00
        FF00FF00FF00FF00FF000066000000660000FF00FF00FF00FF00FF00FF00FF00
        FF00FF00FF00FF00FF00FF00FF00FF00FF00FF00FF00FF00FF00FF00FF00FF00
        FF00FF00FF00FF00FF006565650065656500FF00FF00FF00FF00FF00FF00FF00
        FF00FF00FF00FF00FF00FF00FF00FF00FF00FF00FF00FF00FF00FF00FF00FF00
        FF00FF00FF00FF00FF00004B0000004B0000FF00FF00FF00FF00FF00FF00FF00
        FF00FF00FF00FF00FF00FF00FF00FF00FF00FF00FF00FF00FF00FF00FF00FF00
        FF00FF00FF00FF00FF00FF00FF000066000000660000FF00FF00FF00FF00FF00
        FF00FF00FF00FF00FF00FF00FF00FF00FF00FF00FF00FF00FF00FF00FF00FF00
        FF00FF00FF00FF00FF00FF00FF006565650065656500FF00FF00FF00FF00FF00
        FF00FF00FF00FF00FF00FF00FF00FF00FF00FF00FF00FF00FF00FF00FF00FF00
        FF00FF00FF00FF00FF00FF00FF00004B0000004B0000FF00FF00FF00FF00FF00
        FF00FF00FF00FF00FF00FF00FF00FF00FF00FF00FF00FF00FF00FF00FF00FF00
        FF00FF00FF00FF00FF00FF00FF00FF00FF00FF00FF00FF00FF00FF00FF00FF00
        FF00FF00FF00FF00FF00FF00FF00FF00FF00FF00FF00FF00FF00FF00FF00FF00
        FF00FF00FF00FF00FF00FF00FF00FF00FF00FF00FF00FF00FF00FF00FF00FF00
        FF00FF00FF00FF00FF00FF00FF00FF00FF00FF00FF00FF00FF00FF00FF00FF00
        FF00FF00FF00FF00FF00FF00FF00FF00FF00FF00FF00FF00FF00FF00FF00FF00
        FF00FF00FF00FF00FF00FF00FF00FF00FF00FF00FF00FF00FF00FF00FF00FF00
        FF00FF00FF00FF00FF00FF00FF00FF00FF00FF00FF00FF00FF00FF00FF00FF00
        FF00FF00FF00FF00FF00FF00FF00FF00FF00FF00FF00FF00FF00FF00FF00FF00
        FF00FF00FF00FF00FF00FF00FF00FF00FF00FF00FF00FF00FF00FF00FF00FF00
        FF00FF00FF00FF00FF00FF00FF00FF00FF00FF00FF00FF00FF00FF00FF00FF00
        FF00FF00FF00FF00FF00FF00FF00FF00FF00FF00FF00FF00FF00FF00FF00FF00
        FF00FF00FF00FF00FF00FF00FF00FF00FF00FF00FF00FF00FF00FF00FF00FF00
        FF00FF00FF00FF00FF00FF00FF00FF00FF00FF00FF00FF00FF00FF00FF00FF00
        FF00FF00FF00FF00FF00FF00FF00FF00FF00FF00FF00FF00FF00FF00FF00FF00
        FF00FF00FF00FF00FF00FF00FF00FF00FF00FF00FF00FF00FF00FF00FF00FF00
        FF00FF00FF00FF00FF00FF00FF00FF00FF00FF00FF00FF00FF00FF00FF00FF00
        FF00FF00FF00FF00FF00FF00FF00FF00FF00FF00FF00FF00FF00FF00FF00FF00
        FF00FF00FF00FF00FF00FF00FF00FF00FF00FF00FF00FF00FF00FF00FF00FF00
        FF00FF00FF00FF00FF00FF00FF00FF00FF00FF00FF00FF00FF00FF00FF00FF00
        FF00FF00FF00FF00FF00FF00FF00FF00FF00FF00FF00FF00FF00FF00FF00FF00
        FF00FF00FF00FF00FF00FF00FF00FF00FF00FF00FF00FF00FF00FF00FF00FF00
        FF00FF00FF00FF00FF00FF00FF00FF00FF00FF00FF00FF00FF00FF00FF00FF00
        FF00FF00FF00FF00FF00FF00FF00FF00FF00FF00FF00FF00FF00FF00FF00FF00
        FF00FF00FF00FF00FF00FF00FF00FF00FF00FF00FF00FF00FF00FF00FF00FF00
        FF00FF00FF00FF00FF00FF00FF00FF00FF00FF00FF00FF00FF00FF00FF00FF00
        FF00FF00FF00FF00FF00FF00FF00FF00FF00FF00FF00FF00FF00FF00FF00FF00
        FF00FF00FF00FF00FF00FF00FF00FF00FF00FF00FF00FF00FF00FF00FF00FF00
        FF00FF00FF00FF00FF00FF00FF00FF00FF00FF00FF00FF00FF00FF00FF00FF00
        FF00FF00FF00FF00FF00FF00FF00FF00FF00FF00FF00FF00FF00}
      NumGlyphs = 3
      Acao = Gravar
      DataSource = DataSource1
    end
    object Button1: TButton
      Left = 24
      Top = 224
      Width = 97
      Height = 25
      Caption = 'Button1'
      TabOrder = 8
      OnClick = Button1Click
    end
  end
  object Edit14: TEdit
    Left = 590
    Top = 370
    Width = 51
    Height = 21
    TabOrder = 3
    OnExit = Edit14Exit
  end
  object Edit15: TEdit
    Left = 656
    Top = 370
    Width = 41
    Height = 21
    TabOrder = 4
  end
  object Edit16: TEdit
    Left = 709
    Top = 370
    Width = 42
    Height = 21
    TabOrder = 5
  end
  object DataSource1: TDataSource
    DataSet = DataModule2.tblnfentradas
    Left = 560
    Top = 48
  end
  object Query1: TQuery
    DatabaseName = 'laluna1'
    SQL.Strings = (
      'select *from produtos')
    Left = 592
    Top = 16
  end
  object Query2: TQuery
    DatabaseName = 'laluna1'
    Left = 544
    Top = 16
  end
  object DataSource2: TDataSource
    DataSet = Query1
    Left = 600
    Top = 48
  end
  object DataSource3: TDataSource
    DataSet = Query3
    Left = 640
    Top = 48
  end
  object Query3: TQuery
    DatabaseName = 'laluna1'
    Left = 640
    Top = 16
  end
  object Query4: TQuery
    BeforeInsert = Query4AfterPost
    BeforePost = Query4BeforePost
    AfterPost = Query4AfterPost
    DatabaseName = 'laluna1'
    Left = 680
    Top = 16
  end
  object Query5: TQuery
    DatabaseName = 'laluna1'
    Left = 728
    Top = 16
  end
  object DataSource4: TDataSource
    DataSet = Query5
    Left = 728
    Top = 48
  end
  object Query6: TQuery
    DatabaseName = 'laluna1'
    Left = 504
    Top = 24
  end
  object DataSource5: TDataSource
    DataSet = Query7
    Left = 597
    Top = 114
  end
  object DataSource6: TDataSource
    DataSet = Query8
    Left = 645
    Top = 114
  end
  object Query7: TQuery
    DatabaseName = 'laluna1'
    SQL.Strings = (
      'select *from cliente')
    Left = 597
    Top = 82
  end
  object Query8: TQuery
    DatabaseName = 'laluna1'
    SQL.Strings = (
      'select *from natureza')
    Left = 645
    Top = 82
  end
  object Query9: TQuery
    DatabaseName = 'laluna1'
    Left = 733
    Top = 98
  end
  object IBQuery1: TIBQuery
    Database = DataModule2.IBDatabase1
    Transaction = DataModule2.IBTransaction1
    BufferChunks = 1000
    CachedUpdates = False
    SQL.Strings = (
      'select *from nffiscal')
    Left = 912
    Top = 329
  end
  object IBUpdateSQL1: TIBUpdateSQL
    RefreshSQL.Strings = (
      'Select '
      'from nffiscal '
      'where'
      '  DCNUMERO = :DCNUMERO')
    ModifySQL.Strings = (
      'update nffiscal'
      'set'
      '  IND_OPER = :IND_OPER,'
      '  IND_EMIT = :IND_EMIT,'
      '  COD_PART = :COD_PART,'
      '  COD_SIT = :COD_SIT,'
      '  DT_DOC = :DT_DOC,'
      '  DT_E_S = :DT_E_S,'
      '  VL_DOC = :VL_DOC,'
      '  VL_DESC = :VL_DESC,'
      '  VL_FORN = :VL_FORN,'
      '  VL_SERV_NT = :VL_SERV_NT,'
      '  VL_TERC = :VL_TERC,'
      '  VL_DA = :VL_DA,'
      '  VL_BC_ICMS = :VL_BC_ICMS,'
      '  VL_ICMS = :VL_ICMS,'
      '  VL_BC_ICMS_ST = :VL_BC_ICMS_ST,'
      '  VL_ICMS_ST = :VL_ICMS_ST,'
      '  COD_INF = :COD_INF,'
      '  VL_COFINS = :VL_COFINS,'
      '  TP_LIGACAO = :TP_LIGACAO,'
      '  COD_GRUPO_TENSAO = :COD_GRUPO_TENSAO,'
      '  CHV_CTE = :CHV_CTE,'
      '  TP_CT = :TP_CT,'
      '  CHV_CTE_REF = :CHV_CTE_REF,'
      '  IND_FRT = :IND_FRT,'
      '  CODNFE = :CODNFE,'
      '  COD_EMPRESA = :COD_EMPRESA,'
      '  COD_FILIAL = :COD_FILIAL,'
      '  DCNUMERO = :DCNUMERO,'
      '  DCSERIE = :DCSERIE,'
      '  DCORDEM = :DCORDEM,'
      '  DCTIPO = :DCTIPO,'
      '  CFOP = :CFOP,'
      '  CDPEDIDO = :CDPEDIDO,'
      '  NATUREZA = :NATUREZA'
      'where'
      '  DCNUMERO = :OLD_DCNUMERO')
    InsertSQL.Strings = (
      'insert into nffiscal'
      
        '  (IND_OPER, IND_EMIT, COD_PART, COD_SIT, DT_DOC, DT_E_S, VL_DOC' +
        ', '
      'VL_DESC, '
      '   VL_FORN, VL_SERV_NT, VL_TERC, VL_DA, VL_BC_ICMS, VL_ICMS, '
      'VL_BC_ICMS_ST, '
      
        '   VL_ICMS_ST, COD_INF, VL_COFINS, TP_LIGACAO, COD_GRUPO_TENSAO,' +
        ' '
      'CHV_CTE, '
      
        '   TP_CT, CHV_CTE_REF, IND_FRT, CODNFE, COD_EMPRESA, COD_FILIAL,' +
        ' '
      'DCNUMERO, '
      '   DCSERIE, DCORDEM, DCTIPO, CFOP, CDPEDIDO, NATUREZA)'
      'values'
      
        '  (:IND_OPER, :IND_EMIT, :COD_PART, :COD_SIT, :DT_DOC, :DT_E_S, ' +
        ':VL_DOC, '
      
        '   :VL_DESC, :VL_FORN, :VL_SERV_NT, :VL_TERC, :VL_DA, :VL_BC_ICM' +
        'S, '
      ':VL_ICMS, '
      
        '   :VL_BC_ICMS_ST, :VL_ICMS_ST, :COD_INF, :VL_COFINS, :TP_LIGACA' +
        'O, '
      ':COD_GRUPO_TENSAO, '
      
        '   :CHV_CTE, :TP_CT, :CHV_CTE_REF, :IND_FRT, :CODNFE, :COD_EMPRE' +
        'SA, '
      ':COD_FILIAL, '
      '   :DCNUMERO, :DCSERIE, :DCORDEM, :DCTIPO, :CFOP, :CDPEDIDO, '
      ':NATUREZA)')
    DeleteSQL.Strings = (
      'delete from nffiscal'
      'where'
      '  DCNUMERO = :OLD_DCNUMERO')
    Left = 944
    Top = 329
  end
  object IBQuery2: TIBQuery
    Database = DataModule2.IBDatabase1
    Transaction = DataModule2.IBTransaction1
    BufferChunks = 1000
    CachedUpdates = False
    SQL.Strings = (
      'select *from nffiscalitens')
    Left = 912
    Top = 361
  end
  object IBUpdateSQL2: TIBUpdateSQL
    RefreshSQL.Strings = (
      'Select '
      'from nffiscalitens '
      'where'
      '  CODNFE = :CODNFE')
    ModifySQL.Strings = (
      'update nffiscalitens'
      'set'
      '  CODNFE = :CODNFE,'
      '  COD_EMPRESA = :COD_EMPRESA,'
      '  NUM_ITEM = :NUM_ITEM,'
      '  COD_ITEM = :COD_ITEM,'
      '  DESCR_COMPL = :DESCR_COMPL,'
      '  QTD = :QTD,'
      '  UNID = :UNID,'
      '  VL_ITEM = :VL_ITEM,'
      '  VL_DESC = :VL_DESC,'
      '  IND_MOV = :IND_MOV,'
      '  CST_ICMS = :CST_ICMS,'
      '  CFOP = :CFOP,'
      '  ALIQ_ICMS = :ALIQ_ICMS,'
      '  VL_ICMS = :VL_ICMS,'
      '  VL_BC_ICMS_ST = :VL_BC_ICMS_ST,'
      '  ALIQ_ST = :ALIQ_ST,'
      '  VL_ICMS_ST = :VL_ICMS_ST,'
      '  IND_APUR = :IND_APUR,'
      '  CST_IPI = :CST_IPI,'
      '  COD_ENQ = :COD_ENQ,'
      '  VL_BC_IPI = :VL_BC_IPI,'
      '  ALIQ_IPI = :ALIQ_IPI,'
      '  VL_IPI = :VL_IPI,'
      '  CST_PIS = :CST_PIS,'
      '  VL_BC_PIS = :VL_BC_PIS,'
      '  ALIQ_PIS = :ALIQ_PIS,'
      '  QUANT_BC_PIS = :QUANT_BC_PIS,'
      '  VL_PIS = :VL_PIS,'
      '  CST_COFINS = :CST_COFINS,'
      '  VL_BC_COFINS = :VL_BC_COFINS,'
      '  ALIQ_COFINS = :ALIQ_COFINS,'
      '  QUANT_BC_COFINS = :QUANT_BC_COFINS,'
      '  VL_COFINS = :VL_COFINS,'
      '  COD_CTA = :COD_CTA,'
      '  COD_FILIAL = :COD_FILIAL,'
      '  DCNUMERO = :DCNUMERO,'
      '  DCORDEM = :DCORDEM,'
      '  DCSERIE = :DCSERIE,'
      '  DCTIPO = :DCTIPO'
      'where'
      '  CODNFE = :OLD_CODNFE')
    InsertSQL.Strings = (
      'insert into nffiscalitens'
      '  (CODNFE, COD_EMPRESA, NUM_ITEM, COD_ITEM, DESCR_COMPL, QTD, '
      'UNID, VL_ITEM, '
      '   VL_DESC, IND_MOV, CST_ICMS, CFOP, ALIQ_ICMS, VL_ICMS, '
      'VL_BC_ICMS_ST, '
      
        '   ALIQ_ST, VL_ICMS_ST, IND_APUR, CST_IPI, COD_ENQ, VL_BC_IPI, A' +
        'LIQ_IPI, '
      
        '   VL_IPI, CST_PIS, VL_BC_PIS, ALIQ_PIS, QUANT_BC_PIS, VL_PIS, C' +
        'ST_COFINS, '
      
        '   VL_BC_COFINS, ALIQ_COFINS, QUANT_BC_COFINS, VL_COFINS, COD_CT' +
        'A, '
      'COD_FILIAL, '
      '   DCNUMERO, DCORDEM, DCSERIE, DCTIPO)'
      'values'
      
        '  (:CODNFE, :COD_EMPRESA, :NUM_ITEM, :COD_ITEM, :DESCR_COMPL, :Q' +
        'TD, '
      ':UNID, '
      
        '   :VL_ITEM, :VL_DESC, :IND_MOV, :CST_ICMS, :CFOP, :ALIQ_ICMS, :' +
        'VL_ICMS, '
      
        '   :VL_BC_ICMS_ST, :ALIQ_ST, :VL_ICMS_ST, :IND_APUR, :CST_IPI, :' +
        'COD_ENQ, '
      
        '   :VL_BC_IPI, :ALIQ_IPI, :VL_IPI, :CST_PIS, :VL_BC_PIS, :ALIQ_P' +
        'IS, '
      ':QUANT_BC_PIS, '
      
        '   :VL_PIS, :CST_COFINS, :VL_BC_COFINS, :ALIQ_COFINS, :QUANT_BC_' +
        'COFINS, '
      
        '   :VL_COFINS, :COD_CTA, :COD_FILIAL, :DCNUMERO, :DCORDEM, :DCSE' +
        'RIE, '
      ':DCTIPO)')
    DeleteSQL.Strings = (
      'delete from nffiscalitens'
      'where'
      '  CODNFE = :OLD_CODNFE')
    Left = 944
    Top = 361
  end
  object Table1: TTable
    Active = True
    DatabaseName = 'laluna1'
    TableName = 'nfentradas.DB'
    Left = 976
    Top = 329
    object Table1Dcnumero: TFloatField
      FieldName = 'Dcnumero'
    end
    object Table1Dcserie: TStringField
      FieldName = 'Dcserie'
      Size = 3
    end
    object Table1Dcordem: TStringField
      FieldName = 'Dcordem'
      Size = 2
    end
    object Table1Dctipo: TStringField
      FieldName = 'Dctipo'
      Size = 6
    end
    object Table1Vlnota: TCurrencyField
      FieldName = 'Vlnota'
    end
    object Table1Vlicms: TCurrencyField
      FieldName = 'Vlicms'
    end
    object Table1Vlipi: TCurrencyField
      FieldName = 'Vlipi'
    end
    object Table1Vlfrete: TCurrencyField
      FieldName = 'Vlfrete'
    end
    object Table1Cdfornecedor: TFloatField
      FieldName = 'Cdfornecedor'
    end
    object Table1Cfop: TFloatField
      FieldName = 'Cfop'
    end
    object Table1Dtentrada: TDateField
      FieldName = 'Dtentrada'
    end
    object Table1Dtemissao: TDateField
      FieldName = 'Dtemissao'
    end
    object Table1Transportado: TFloatField
      FieldName = 'Transportado'
    end
    object Table1Cdpedido: TFloatField
      FieldName = 'Cdpedido'
    end
    object Table1Natureza: TFloatField
      FieldName = 'Natureza'
    end
    object Table1Idnfentradas: TIntegerField
      FieldName = 'Idnfentradas'
    end
    object Table1Vlst: TCurrencyField
      FieldName = 'Vlst'
    end
    object Table1Tpsituacao: TStringField
      FieldName = 'Tpsituacao'
      Size = 1
    end
  end
  object Table2: TTable
    Active = True
    DatabaseName = 'laluna1'
    TableName = 'nfentradasitens.db'
    Left = 976
    Top = 361
    object Table2Dcnumero: TFloatField
      FieldName = 'Dcnumero'
    end
    object Table2Dcserie: TStringField
      FieldName = 'Dcserie'
      Size = 3
    end
    object Table2Dcordem: TStringField
      FieldName = 'Dcordem'
      Size = 2
    end
    object Table2Dctipo: TStringField
      FieldName = 'Dctipo'
      Size = 6
    end
    object Table2Cdfornecedor: TFloatField
      FieldName = 'Cdfornecedor'
    end
    object Table2Cdproduto: TFloatField
      FieldName = 'Cdproduto'
    end
    object Table2Quantidade: TFloatField
      FieldName = 'Quantidade'
    end
    object Table2Ipi: TCurrencyField
      FieldName = 'Ipi'
    end
    object Table2St: TCurrencyField
      FieldName = 'St'
    end
    object Table2Icms: TCurrencyField
      FieldName = 'Icms'
    end
    object Table2Vlunitario: TCurrencyField
      FieldName = 'Vlunitario'
    end
    object Table2Vldesconto: TCurrencyField
      FieldName = 'Vldesconto'
    end
  end
  object IBQuery3: TIBQuery
    Database = DataModule2.IBDatabase1
    Transaction = DataModule2.IBTransaction1
    BufferChunks = 1000
    CachedUpdates = False
    SQL.Strings = (
      'select *from titulospagar2')
    UpdateObject = IBUpdateSQL3
    Left = 912
    Top = 393
  end
  object IBUpdateSQL3: TIBUpdateSQL
    RefreshSQL.Strings = (
      'Select '
      'from nffiscalitens '
      'where'
      '  CODNFE = :CODNFE')
    ModifySQL.Strings = (
      'update nffiscalitens'
      'set'
      '  CODNFE = :CODNFE,'
      '  COD_EMPRESA = :COD_EMPRESA,'
      '  NUM_ITEM = :NUM_ITEM,'
      '  COD_ITEM = :COD_ITEM,'
      '  DESCR_COMPL = :DESCR_COMPL,'
      '  QTD = :QTD,'
      '  UNID = :UNID,'
      '  VL_ITEM = :VL_ITEM,'
      '  VL_DESC = :VL_DESC,'
      '  IND_MOV = :IND_MOV,'
      '  CST_ICMS = :CST_ICMS,'
      '  CFOP = :CFOP,'
      '  ALIQ_ICMS = :ALIQ_ICMS,'
      '  VL_ICMS = :VL_ICMS,'
      '  VL_BC_ICMS_ST = :VL_BC_ICMS_ST,'
      '  ALIQ_ST = :ALIQ_ST,'
      '  VL_ICMS_ST = :VL_ICMS_ST,'
      '  IND_APUR = :IND_APUR,'
      '  CST_IPI = :CST_IPI,'
      '  COD_ENQ = :COD_ENQ,'
      '  VL_BC_IPI = :VL_BC_IPI,'
      '  ALIQ_IPI = :ALIQ_IPI,'
      '  VL_IPI = :VL_IPI,'
      '  CST_PIS = :CST_PIS,'
      '  VL_BC_PIS = :VL_BC_PIS,'
      '  ALIQ_PIS = :ALIQ_PIS,'
      '  QUANT_BC_PIS = :QUANT_BC_PIS,'
      '  VL_PIS = :VL_PIS,'
      '  CST_COFINS = :CST_COFINS,'
      '  VL_BC_COFINS = :VL_BC_COFINS,'
      '  ALIQ_COFINS = :ALIQ_COFINS,'
      '  QUANT_BC_COFINS = :QUANT_BC_COFINS,'
      '  VL_COFINS = :VL_COFINS,'
      '  COD_CTA = :COD_CTA,'
      '  COD_FILIAL = :COD_FILIAL,'
      '  DCNUMERO = :DCNUMERO,'
      '  DCORDEM = :DCORDEM,'
      '  DCSERIE = :DCSERIE,'
      '  DCTIPO = :DCTIPO'
      'where'
      '  CODNFE = :OLD_CODNFE')
    InsertSQL.Strings = (
      'insert into nffiscalitens'
      '  (CODNFE, COD_EMPRESA, NUM_ITEM, COD_ITEM, DESCR_COMPL, QTD, '
      'UNID, VL_ITEM, '
      '   VL_DESC, IND_MOV, CST_ICMS, CFOP, ALIQ_ICMS, VL_ICMS, '
      'VL_BC_ICMS_ST, '
      
        '   ALIQ_ST, VL_ICMS_ST, IND_APUR, CST_IPI, COD_ENQ, VL_BC_IPI, A' +
        'LIQ_IPI, '
      
        '   VL_IPI, CST_PIS, VL_BC_PIS, ALIQ_PIS, QUANT_BC_PIS, VL_PIS, C' +
        'ST_COFINS, '
      
        '   VL_BC_COFINS, ALIQ_COFINS, QUANT_BC_COFINS, VL_COFINS, COD_CT' +
        'A, '
      'COD_FILIAL, '
      '   DCNUMERO, DCORDEM, DCSERIE, DCTIPO)'
      'values'
      
        '  (:CODNFE, :COD_EMPRESA, :NUM_ITEM, :COD_ITEM, :DESCR_COMPL, :Q' +
        'TD, '
      ':UNID, '
      
        '   :VL_ITEM, :VL_DESC, :IND_MOV, :CST_ICMS, :CFOP, :ALIQ_ICMS, :' +
        'VL_ICMS, '
      
        '   :VL_BC_ICMS_ST, :ALIQ_ST, :VL_ICMS_ST, :IND_APUR, :CST_IPI, :' +
        'COD_ENQ, '
      
        '   :VL_BC_IPI, :ALIQ_IPI, :VL_IPI, :CST_PIS, :VL_BC_PIS, :ALIQ_P' +
        'IS, '
      ':QUANT_BC_PIS, '
      
        '   :VL_PIS, :CST_COFINS, :VL_BC_COFINS, :ALIQ_COFINS, :QUANT_BC_' +
        'COFINS, '
      
        '   :VL_COFINS, :COD_CTA, :COD_FILIAL, :DCNUMERO, :DCORDEM, :DCSE' +
        'RIE, '
      ':DCTIPO)')
    DeleteSQL.Strings = (
      'delete from nffiscalitens'
      'where'
      '  CODNFE = :OLD_CODNFE')
    Left = 944
    Top = 393
  end
  object Table3: TTable
    Active = True
    DatabaseName = 'laluna1'
    TableName = 'titulospagar2.DB'
    Left = 976
    Top = 393
    object Table3Dcnumero: TFloatField
      FieldName = 'Dcnumero'
    end
    object Table3Dcserie: TStringField
      FieldName = 'Dcserie'
      Size = 3
    end
    object Table3Dcordem: TStringField
      FieldName = 'Dcordem'
      Size = 2
    end
    object Table3Dctipo: TStringField
      FieldName = 'Dctipo'
      Size = 6
    end
    object Table3Parcela: TFloatField
      FieldName = 'Parcela'
    end
    object Table3Dtvencimento: TStringField
      FieldName = 'Dtvencimento'
      Size = 10
    end
    object Table3Dtlancamento: TStringField
      FieldName = 'Dtlancamento'
      Size = 10
    end
    object Table3Status: TStringField
      FieldName = 'Status'
      Size = 1
    end
    object Table3Tpsituacao: TStringField
      FieldName = 'Tpsituacao'
      Size = 1
    end
    object Table3Vlparcela: TFloatField
      FieldName = 'Vlparcela'
    end
    object Table3Fornecedor: TFloatField
      FieldName = 'Fornecedor'
    end
    object Table3Obs: TStringField
      FieldName = 'Obs'
      Size = 255
    end
    object Table3DATAPAGAMENTO: TDateField
      FieldName = 'DATAPAGAMENTO'
    end
  end
end
