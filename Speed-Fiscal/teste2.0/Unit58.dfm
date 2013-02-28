object FRMMENU: TFRMMENU
  Left = 187
  Top = 129
  Width = 1036
  Height = 780
  Caption = 'FRMMENU'
  Color = clBtnFace
  Font.Charset = DEFAULT_CHARSET
  Font.Color = clWindowText
  Font.Height = -11
  Font.Name = 'MS Sans Serif'
  Font.Style = []
  Menu = MainMenu1
  OldCreateOrder = False
  OnActivate = FormActivate
  OnCreate = FormCreate
  PixelsPerInch = 96
  TextHeight = 13
  object ProgressBar1: TProgressBar
    Left = 0
    Top = 720
    Width = 1089
    Height = 17
    TabOrder = 0
  end
  object Panel1: TPanel
    Left = 0
    Top = 0
    Width = 1089
    Height = 25
    Align = alTop
    Color = clScrollBar
    TabOrder = 1
  end
  object Panel2: TPanel
    Left = 0
    Top = 25
    Width = 1089
    Height = 712
    Align = alClient
    Color = clWhite
    TabOrder = 2
    object GroupBox1: TGroupBox
      Left = 872
      Top = 89
      Width = 225
      Height = 513
      Caption = 'Contas vencidas'
      TabOrder = 0
      object Memo3: TMemo
        Left = 8
        Top = 16
        Width = 201
        Height = 481
        Lines.Strings = (
          'Memo1')
        TabOrder = 0
      end
    end
    object GroupBox2: TGroupBox
      Left = 632
      Top = 88
      Width = 225
      Height = 505
      Caption = 'Contas que vencem hoje'
      TabOrder = 1
      object Memo2: TMemo
        Left = 8
        Top = 16
        Width = 201
        Height = 481
        Lines.Strings = (
          'Memo1')
        TabOrder = 0
      end
    end
    object GroupBox3: TGroupBox
      Left = 384
      Top = 90
      Width = 241
      Height = 503
      Caption = 'Contas que vencem amanha'
      TabOrder = 2
      object Memo1: TMemo
        Left = 8
        Top = 16
        Width = 217
        Height = 481
        Lines.Strings = (
          'Memo1')
        TabOrder = 0
      end
    end
  end
  object MainMenu1: TMainMenu
    Left = 104
    Top = 112
    object CADASTROS1: TMenuItem
      Caption = 'CADASTROS'
      object LOJAS1: TMenuItem
        Caption = 'LOJAS'
        object MANUTENO2: TMenuItem
          Caption = 'MANUTEN'#199#195'O'
          OnClick = MANUTENO2Click
        end
        object CONSULTA2: TMenuItem
          Caption = 'CONSULTA'
          OnClick = CONSULTA2Click
        end
      end
      object CLIFORM1: TMenuItem
        Caption = 'CLI/FORMEC'
        object MANUTENO3: TMenuItem
          Caption = 'MANUTEN'#199#195'O'
          OnClick = MANUTENO3Click
        end
        object CONSULTA3: TMenuItem
          Caption = 'CONSULTA'
          OnClick = CONSULTA3Click
        end
      end
      object EMPREGADO1: TMenuItem
        Caption = 'EMPREGADO'
        object MANUTENO8: TMenuItem
          Caption = 'MANUTEN'#199#195'O'
          OnClick = MANUTENO8Click
        end
        object CONSULTA8: TMenuItem
          Caption = 'CONSULTA'
          OnClick = CONSULTA8Click
        end
      end
      object NATUREZA1: TMenuItem
        Caption = 'NATUREZA'
        object MANUTENO20: TMenuItem
          Caption = 'MANUTEN'#199#195'O'
          OnClick = MANUTENO20Click
        end
        object CONSULTA21: TMenuItem
          Caption = 'CONSULTA'
        end
      end
    end
    object ENTRADAS1: TMenuItem
      Caption = 'ENTRADAS'
      object ENTRADANOTAFISCAL1: TMenuItem
        Caption = 'ENTRADA NOTA FISCAL'
        object ENTRADA1: TMenuItem
          Caption = 'ENTRADA'
          OnClick = ENTRADA1Click
        end
        object CONSULTAR2: TMenuItem
          Caption = 'CONSULTAR'
          OnClick = CONSULTAR2Click
        end
      end
      object RELA1: TMenuItem
        Caption = 'RELATORIO ENTRADAS'
        OnClick = RELA1Click
      end
      object RELATORIODEENTRADADEPRODUTOS1: TMenuItem
        Caption = 'RELATORIO DE ENTRADA DE PRODUTOS'
        OnClick = RELATORIODEENTRADADEPRODUTOS1Click
      end
    end
    object ESTOQUE1: TMenuItem
      Caption = 'ESTOQUE'
      object PRODUTOS1: TMenuItem
        Caption = 'PRODUTOS'
        object MANUTENO10: TMenuItem
          Caption = 'MANUTEN'#199#195'O'
          OnClick = MANUTENO10Click
        end
        object CONSULTA10: TMenuItem
          Caption = 'CONSULTA'
          OnClick = CONSULTA10Click
        end
        object teste1: TMenuItem
          Caption = 'teste'
          OnClick = teste1Click
        end
      end
      object INVENTARIO1: TMenuItem
        Caption = 'INVENTARIO'
        object CONSULTA11: TMenuItem
          Caption = 'CONSULTA'
          OnClick = CONSULTA11Click
        end
      end
      object PEDIDOS1: TMenuItem
        Caption = 'PEDIDOS'
        object MANUTENO13: TMenuItem
          Caption = 'MANUTEN'#199#195'O'
        end
        object CONSULTAR1: TMenuItem
          Caption = 'CONSULTAR'
        end
      end
      object GRAFICOSAIDAPRODUTO1: TMenuItem
        Caption = 'GRAFICO SAIDA PRODUTO'
        OnClick = GRAFICOSAIDAPRODUTO1Click
      end
    end
    object FINANAS1: TMenuItem
      Caption = 'FINAN'#199'AS'
      object CAIXA1: TMenuItem
        Caption = 'CAIXA'
        object MANUTENO15: TMenuItem
          Caption = 'MANUTEN'#199#195'O'
          OnClick = MANUTENO15Click
        end
        object CONSULTA15: TMenuItem
          Caption = 'CONSULTA'
          OnClick = CONSULTA15Click
        end
      end
      object CAP1: TMenuItem
        Caption = 'RELATORIO VENDEDORAS'
        OnClick = CAP1Click
      end
      object IMPRBOLETOS1: TMenuItem
        Caption = 'INTEGRA'#199#195'O'
        OnClick = IMPRBOLETOS1Click
      end
    end
    object ETIQUETA1: TMenuItem
      Caption = 'ETIQUETA'
      object IMPRIMIRETIQUETA1: TMenuItem
        Caption = 'IMPRIMIR ETIQUETA'
        OnClick = IMPRIMIRETIQUETA1Click
      end
    end
    object FINANCEIRO1: TMenuItem
      Caption = 'FINANCEIRO'
      object BAIXARTITULOS1: TMenuItem
        Caption = 'BAIXAR TITULOS'
        object MANUTENO21: TMenuItem
          Caption = 'MANUTEN'#199#195'O'
          OnClick = MANUTENO21Click
        end
        object RELATORIO1: TMenuItem
          Caption = 'BAIXAR TITULOS'
          OnClick = RELATORIO1Click
        end
        object RELATORIO2: TMenuItem
          Caption = 'RELATORIO'
          OnClick = RELATORIO2Click
        end
      end
    end
    object Sintegra1: TMenuItem
      Caption = 'SINTEGRA'
      object LESINTEGRA1: TMenuItem
        Caption = 'LE SINTEGRA'
        OnClick = LESINTEGRA1Click
      end
      object RELATORIOSINTEGRA1: TMenuItem
        Caption = 'RELATORIO SINTEGRA'
      end
    end
    object SAIDAS1: TMenuItem
      Caption = 'SAIDAS'
      object BAIXAS2: TMenuItem
        Caption = 'BAIXAS'
        OnClick = BAIXAS2Click
      end
      object SAIDASDEPRODUTOS1: TMenuItem
        Caption = 'SAIDAS DE PRODUTOS'
        object SAIDASDEPRODUTO1: TMenuItem
          Caption = 'SAIDAS DE PRODUTO'
          OnClick = SAIDASDEPRODUTO1Click
        end
        object GRAFICOSAIDAS1: TMenuItem
          Caption = 'GRAFICO SAIDAS'
          OnClick = GRAFICOSAIDAS1Click
        end
      end
      object REDUOZ1: TMenuItem
        Caption = 'REDU'#199#195'O Z'
      end
    end
    object UTIL1: TMenuItem
      Caption = 'UTIL'
      object form771: TMenuItem
        Caption = 'form77'
        OnClick = form771Click
      end
      object Form651: TMenuItem
        Caption = 'Form65'
        OnClick = Form651Click
      end
      object form661: TMenuItem
        Caption = 'form66'
        OnClick = form661Click
      end
      object form621: TMenuItem
        Caption = 'form62'
        OnClick = form621Click
      end
      object form611: TMenuItem
        Caption = 'form61'
        OnClick = form611Click
      end
      object form751: TMenuItem
        Caption = 'form75'
        OnClick = form751Click
      end
      object inventario2: TMenuItem
        Caption = 'inventario'
        OnClick = inventario2Click
      end
      object este1: TMenuItem
        Caption = 'Teste'
        OnClick = este1Click
      end
      object acertardatatitulos1: TMenuItem
        Caption = 'acertar data titulos'
        OnClick = acertardatatitulos1Click
      end
    end
    object SAIR1: TMenuItem
      Caption = 'SAIR'
      OnClick = SAIR1Click
    end
  end
end
