object FrmCalculadora: TFrmCalculadora
  Left = 333
  Top = 130
  BorderIcons = [biSystemMenu]
  BorderStyle = bsSingle
  Caption = 'Calculadora'
  ClientHeight = 242
  ClientWidth = 387
  Color = clBtnFace
  Font.Charset = DEFAULT_CHARSET
  Font.Color = clWindowText
  Font.Height = -11
  Font.Name = 'MS Sans Serif'
  Font.Style = []
  KeyPreview = True
  OldCreateOrder = True
  Position = poScreenCenter
  OnClose = FormClose
  OnCreate = FormCreate
  OnKeyPress = FormKeyPress
  OnKeyUp = FormKeyUp
  PixelsPerInch = 96
  TextHeight = 13
  object PnlFundo: TPanel
    Left = 0
    Top = 0
    Width = 387
    Height = 242
    Align = alClient
    BorderStyle = bsSingle
    PopupMenu = PumCalcula
    TabOrder = 0
    object SbtSete: TSpeedButton
      Left = 10
      Top = 105
      Width = 33
      Height = 31
      Caption = '7'
      Font.Charset = ANSI_CHARSET
      Font.Color = clWindowText
      Font.Height = -13
      Font.Name = 'Verdana'
      Font.Style = [fsBold]
      ParentFont = False
      PopupMenu = PumCalcula
      OnClick = SbtUmClick
    end
    object SbtOito: TSpeedButton
      Left = 47
      Top = 105
      Width = 33
      Height = 31
      Caption = '8'
      Font.Charset = ANSI_CHARSET
      Font.Color = clWindowText
      Font.Height = -13
      Font.Name = 'Verdana'
      Font.Style = [fsBold]
      ParentFont = False
      PopupMenu = PumCalcula
      OnClick = SbtUmClick
    end
    object SbtQuatro: TSpeedButton
      Left = 10
      Top = 138
      Width = 33
      Height = 31
      Caption = '4'
      Font.Charset = ANSI_CHARSET
      Font.Color = clWindowText
      Font.Height = -13
      Font.Name = 'Verdana'
      Font.Style = [fsBold]
      ParentFont = False
      PopupMenu = PumCalcula
      OnClick = SbtUmClick
    end
    object SbtNove: TSpeedButton
      Left = 84
      Top = 105
      Width = 33
      Height = 31
      Caption = '9'
      Font.Charset = ANSI_CHARSET
      Font.Color = clWindowText
      Font.Height = -13
      Font.Name = 'Verdana'
      Font.Style = [fsBold]
      ParentFont = False
      PopupMenu = PumCalcula
      OnClick = SbtUmClick
    end
    object SbtSeis: TSpeedButton
      Left = 84
      Top = 138
      Width = 33
      Height = 31
      Caption = '6'
      Font.Charset = ANSI_CHARSET
      Font.Color = clWindowText
      Font.Height = -13
      Font.Name = 'Verdana'
      Font.Style = [fsBold]
      ParentFont = False
      PopupMenu = PumCalcula
      OnClick = SbtUmClick
    end
    object SbtCinco: TSpeedButton
      Left = 47
      Top = 138
      Width = 33
      Height = 31
      Caption = '5'
      Font.Charset = ANSI_CHARSET
      Font.Color = clWindowText
      Font.Height = -13
      Font.Name = 'Verdana'
      Font.Style = [fsBold]
      ParentFont = False
      PopupMenu = PumCalcula
      OnClick = SbtUmClick
    end
    object SbtUm: TSpeedButton
      Left = 10
      Top = 170
      Width = 33
      Height = 31
      Caption = '1'
      Font.Charset = ANSI_CHARSET
      Font.Color = clWindowText
      Font.Height = -13
      Font.Name = 'Verdana'
      Font.Style = [fsBold]
      ParentFont = False
      PopupMenu = PumCalcula
      OnClick = SbtUmClick
    end
    object SbtDois: TSpeedButton
      Left = 47
      Top = 170
      Width = 33
      Height = 31
      Caption = '2'
      Font.Charset = ANSI_CHARSET
      Font.Color = clWindowText
      Font.Height = -13
      Font.Name = 'Verdana'
      Font.Style = [fsBold]
      ParentFont = False
      PopupMenu = PumCalcula
      OnClick = SbtUmClick
    end
    object SbtTres: TSpeedButton
      Left = 84
      Top = 170
      Width = 33
      Height = 31
      Caption = '3'
      Font.Charset = ANSI_CHARSET
      Font.Color = clWindowText
      Font.Height = -13
      Font.Name = 'Verdana'
      Font.Style = [fsBold]
      ParentFont = False
      PopupMenu = PumCalcula
      OnClick = SbtUmClick
    end
    object SbtZero: TSpeedButton
      Left = 10
      Top = 202
      Width = 69
      Height = 31
      Caption = '0'
      Font.Charset = ANSI_CHARSET
      Font.Color = clWindowText
      Font.Height = -13
      Font.Name = 'Verdana'
      Font.Style = [fsBold]
      ParentFont = False
      PopupMenu = PumCalcula
      OnClick = SbtUmClick
    end
    object SbtDecima: TSpeedButton
      Left = 84
      Top = 202
      Width = 33
      Height = 31
      Caption = ','
      Font.Charset = ANSI_CHARSET
      Font.Color = clWindowText
      Font.Height = -13
      Font.Name = 'Verdana'
      Font.Style = [fsBold]
      ParentFont = False
      PopupMenu = PumCalcula
      OnClick = SbtUmClick
    end
    object SbtDivide: TSpeedButton
      Left = 120
      Top = 105
      Width = 33
      Height = 31
      Caption = '/'
      Font.Charset = ANSI_CHARSET
      Font.Color = clBlue
      Font.Height = -13
      Font.Name = 'Verdana'
      Font.Style = [fsBold]
      ParentFont = False
      PopupMenu = PumCalcula
      OnClick = SbtDivideClick
    end
    object SbtMultip: TSpeedButton
      Left = 120
      Top = 138
      Width = 33
      Height = 31
      Caption = '*'
      Font.Charset = ANSI_CHARSET
      Font.Color = clBlue
      Font.Height = -13
      Font.Name = 'Verdana'
      Font.Style = [fsBold]
      ParentFont = False
      PopupMenu = PumCalcula
      OnClick = SbtDivideClick
    end
    object SbtSubtra: TSpeedButton
      Left = 120
      Top = 170
      Width = 33
      Height = 31
      Caption = '-'
      Font.Charset = ANSI_CHARSET
      Font.Color = clBlue
      Font.Height = -13
      Font.Name = 'Verdana'
      Font.Style = [fsBold]
      ParentFont = False
      PopupMenu = PumCalcula
      OnClick = SbtDivideClick
    end
    object SbtAdicio: TSpeedButton
      Left = 120
      Top = 202
      Width = 33
      Height = 31
      Caption = '+'
      Font.Charset = ANSI_CHARSET
      Font.Color = clBlue
      Font.Height = -13
      Font.Name = 'Verdana'
      Font.Style = [fsBold]
      ParentFont = False
      PopupMenu = PumCalcula
      OnClick = SbtDivideClick
    end
    object SbtC: TSpeedButton
      Left = 84
      Top = 39
      Width = 70
      Height = 31
      Caption = '&C'
      Font.Charset = ANSI_CHARSET
      Font.Color = clRed
      Font.Height = -13
      Font.Name = 'Verdana'
      Font.Style = [fsBold]
      ParentFont = False
      PopupMenu = PumCalcula
      OnClick = SbtCClick
    end
    object SbtCE: TSpeedButton
      Left = 10
      Top = 39
      Width = 70
      Height = 31
      Caption = 'C&E'
      Font.Charset = ANSI_CHARSET
      Font.Color = clRed
      Font.Height = -13
      Font.Name = 'Verdana'
      Font.Style = [fsBold]
      ParentFont = False
      PopupMenu = PumCalcula
      OnClick = SbtCEClick
    end
    object SbtIgual: TSpeedButton
      Left = 157
      Top = 169
      Width = 33
      Height = 64
      Caption = '='
      Font.Charset = ANSI_CHARSET
      Font.Color = clBlue
      Font.Height = -13
      Font.Name = 'Verdana'
      Font.Style = [fsBold]
      ParentFont = False
      PopupMenu = PumCalcula
      OnClick = SbtIgualClick
    end
    object SbtPorcen: TSpeedButton
      Left = 157
      Top = 137
      Width = 33
      Height = 31
      Caption = '%'
      Font.Charset = ANSI_CHARSET
      Font.Color = clWindowText
      Font.Height = -13
      Font.Name = 'Verdana'
      Font.Style = [fsBold]
      ParentFont = False
      PopupMenu = PumCalcula
      OnClick = SbtDivideClick
    end
    object SbtFita: TSpeedButton
      Left = 174
      Top = 10
      Width = 23
      Height = 22
      Hint = 'Exibe a "fita" das operaes realizadas'
      Glyph.Data = {
        E6040000424DE604000000000000360000002800000014000000140000000100
        180000000000B0040000C40E0000C40E00000000000000000000408080408080
        4080804080804080804080804080804080804080804080804080804080804080
        8040808040808040808040808040808040808040808040808040808040808040
        8080408080408080408080408080408080408080408080408080408080408080
        4080804080804080804080804080804080804080804080804080804080804080
        8040808040808040808040808040808040808040808040808040808040808040
        8080408080408080408080408080408080408080408080408080000000000000
        0000000000000000000000000000000000000000000000000000000000004080
        80408080408080408080408080408080408080408080000000FFFFFFFFFFFFFF
        FFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFF000000408080408080
        408080408080408080408080408080408080000000FFFFFFFFFFFFFFFFFFFFFF
        FFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFF00000040808040808040808040
        8080408080408080408080408080000000FFFFFF000000000000000000000000
        000000000000000000000000FFFFFF0000004080804080804080804080804080
        80408080408080408080000000FFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFF
        FFFFFFFFFFFFFFFFFFFFFF000000408080408080408080408080408080408080
        408080408080000000FFFFFF0000000000000000000000000000000000000000
        00000000FFFFFF00000040808040808040808040808040808040808040808040
        8080000000FFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFF
        FFFFFF0000004080804080804080804080804080804080804080804080800000
        00FFFFFF000000000000000000000000000000000000000000000000FFFFFF00
        0000408080408080408080408080408080408080408080408080000000FFFFFF
        FFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFF0000004080
        80408080408080408080408080408080408080408080000000FFFFFF00000000
        0000000000000000000000000000000000000000FFFFFF000000408080408080
        408080408080408080408080408080408080000000FFFFFFFFFFFFFFFFFFFFFF
        FFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFF00000040808040808040808040
        8080408080408080408080408080000000FFFFFF000000000000000000000000
        000000000000000000000000FFFFFF0000004080804080804080804080804080
        80408080408080408080000000FFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFF
        FFFFFFFFFFFFFFFFFFFFFF000000408080408080408080408080408080408080
        4080804080800000000000000000000000000000000000000000000000000000
        0000000000000000000040808040808040808040808040808040808040808040
        8080408080408080408080408080408080408080408080408080408080408080
        4080804080804080804080804080804080804080804080804080804080804080
        8040808040808040808040808040808040808040808040808040808040808040
        8080408080408080408080408080408080408080408080408080408080408080
        4080804080804080804080804080804080804080804080804080804080804080
        80408080408080408080}
      ParentShowHint = False
      PopupMenu = PumCalcula
      ShowHint = True
      OnClick = SbtFitaClick
    end
    object BbtTransf: TSpeedButton
      Left = 157
      Top = 73
      Width = 33
      Height = 31
      Hint = 'Transfere valor do visor para formulrio anterior ( F9 )'
      Font.Charset = DEFAULT_CHARSET
      Font.Color = clRed
      Font.Height = -13
      Font.Name = 'MS Sans Serif'
      Font.Style = []
      Glyph.Data = {
        36020000424D360200000000000076000000280000001C0000001C0000000100
        040000000000C0010000C40E0000C40E00001000000000000000000000000000
        80000080000000808000800000008000800080800000C0C0C000808080000000
        FF0000FF000000FFFF00FF000000FF00FF00FFFF0000FFFFFF00333333333333
        333333333333333300003333333333333333333333333333000038FFFFFFFFFF
        FFFFFFFFFFFF3333000038077777777777777777777F33330000380FFFFFFFFF
        FFFFFFFFFF7F33330000380F444444F4444400F00F7F33330000380FFF44FFF4
        4FFFFF0FFF7F33330000380FFF44FFFF44FFFF0FFF7F33330000380FFF44FFFF
        F44FFF0FFF7F33330000380FFF44FFFFFF44FF0FFF7F33330000380F4444FFF4
        4F44FF0FFF7F33330000380FF444FFF44F44FF0FFF7F33330000380FFF44FFFF
        444F00F00F7F33330000380FFFFFFFFFFFFFFFFFFF7F33330000380000000000
        00000000000F3333000038888888888888888888888833330000333333333333
        3333333333333333000033333333333333333333333333330000333333333333
        3333333333333333000033333333334444443333333333330000333333333342
        2222A333333444A30000333333333342222A3333333422A30000333333333342
        2224433333422A3300003333333333422222244444222A330000333333333342
        AA2222222222A333000033333333333A33AA222222AA33330000333333333333
        3333AAAAAA333333000033333333333333333333333333330000}
      ParentFont = False
      ParentShowHint = False
      PopupMenu = PumCalcula
      ShowHint = True
      OnClick = BbtTransfClick
    end
    object SbtMemMai: TSpeedButton
      Left = 10
      Top = 73
      Width = 33
      Height = 31
      Hint = 'Memria mais ( Ctrl + )'
      Caption = 'M+'
      Font.Charset = ANSI_CHARSET
      Font.Color = clBlue
      Font.Height = -13
      Font.Name = 'Verdana'
      Font.Style = [fsBold]
      ParentFont = False
      ParentShowHint = False
      PopupMenu = PumCalcula
      ShowHint = True
      OnClick = SbtMemMaiClick
    end
    object SbtMemMen: TSpeedButton
      Left = 47
      Top = 73
      Width = 33
      Height = 31
      Hint = 'Memria menos  ( Ctrl - )'
      Caption = 'M-'
      Font.Charset = ANSI_CHARSET
      Font.Color = clBlue
      Font.Height = -13
      Font.Name = 'Verdana'
      Font.Style = [fsBold]
      ParentFont = False
      ParentShowHint = False
      PopupMenu = PumCalcula
      ShowHint = True
      OnClick = SbtMemMenClick
    end
    object SbtMemRec: TSpeedButton
      Left = 84
      Top = 73
      Width = 33
      Height = 31
      Hint = 'Recuperar memria  ( Ctrl R )'
      Caption = 'MR'
      Font.Charset = ANSI_CHARSET
      Font.Color = clBlue
      Font.Height = -13
      Font.Name = 'Verdana'
      Font.Style = [fsBold]
      ParentFont = False
      ParentShowHint = False
      PopupMenu = PumCalcula
      ShowHint = True
      OnClick = SbtMemRecClick
    end
    object SbtMemCle: TSpeedButton
      Left = 120
      Top = 73
      Width = 33
      Height = 31
      Hint = 'Limpar memria  ( Ctrl C )'
      Caption = 'MC'
      Font.Charset = ANSI_CHARSET
      Font.Color = clBlue
      Font.Height = -13
      Font.Name = 'Verdana'
      Font.Style = [fsBold]
      ParentFont = False
      ParentShowHint = False
      PopupMenu = PumCalcula
      ShowHint = True
      OnClick = SbtMemCleClick
    end
    object SbtBack: TSpeedButton
      Left = 157
      Top = 39
      Width = 33
      Height = 31
      Hint = 'Backspace'
      Font.Charset = ANSI_CHARSET
      Font.Color = clBlue
      Font.Height = -13
      Font.Name = 'Verdana'
      Font.Style = [fsBold]
      Glyph.Data = {
        E6040000424DE604000000000000360000002800000014000000140000000100
        180000000000B0040000C40E0000C40E00000000000000000000408080408080
        4080804080804080804080804080804080804080804080804080804080804080
        8040808040808040808040808040808040808040808040808040808040808040
        8080408080408080408080408080408080408080408080408080408080408080
        4080804080804080804080804080804080804080804080804080804080804080
        8040808040808040808040808040808000000040808040808040808040808040
        8080408080408080408080408080408080408080408080408080408080408080
        4080804080804080800000000000004080804080804080804080804080804080
        8040808040808040808040808040808040808040808040808040808040808040
        8080000000FF0000000000408080408080408080408080408080408080408080
        408080408080408080408080408080408080408080408080408080000000FF00
        00FF000000000040808040808040808040808040808040808040808040808040
        8080408080408080408080408080408080408080000000FF0000FF0000FF0000
        0000000000000000000000000000000000000000004080804080804080804080
        80408080408080408080408080000000FF0000FF0000FF0000FF0000FF0000FF
        0000FF0000FF0000FF0000FF0000000000408080408080408080408080408080
        408080408080000000FF0000FF0000FF0000FF0000FF0000FF0000FF0000FF00
        00FF0000FF0000FF000000000040808040808040808040808040808040808000
        0000FF0000FF0000FF0000FF0000FF0000FF0000FF0000FF0000FF0000FF0000
        FF0000FF0000000000408080408080408080408080408080408080000000FF00
        00FF0000FF0000FF0000FF0000FF0000FF0000FF0000FF0000FF0000FF0000FF
        0000000000408080408080408080408080408080408080408080000000FF0000
        FF0000FF0000FF0000FF0000FF0000FF0000FF0000FF0000FF0000FF00000000
        00408080408080408080408080408080408080408080408080000000FF0000FF
        0000FF0000FF0000FF0000FF0000FF0000FF0000FF0000FF0000000000408080
        408080408080408080408080408080408080408080408080000000FF0000FF00
        00FF000000000000000000000000000000000000000000000040808040808040
        8080408080408080408080408080408080408080408080000000FF0000FF0000
        0000004080804080804080804080804080804080804080804080804080804080
        80408080408080408080408080408080408080408080000000FF000000000040
        8080408080408080408080408080408080408080408080408080408080408080
        4080804080804080804080804080804080804080800000000000004080804080
        8040808040808040808040808040808040808040808040808040808040808040
        8080408080408080408080408080408080408080000000408080408080408080
        4080804080804080804080804080804080804080804080804080804080804080
        8040808040808040808040808040808040808040808040808040808040808040
        8080408080408080408080408080408080408080408080408080408080408080
        4080804080804080804080804080804080804080804080804080804080804080
        80408080408080408080}
      ParentFont = False
      ParentShowHint = False
      PopupMenu = PumCalcula
      ShowHint = True
      OnClick = SbtBackClick
    end
    object SbtMaiMen: TSpeedButton
      Left = 157
      Top = 105
      Width = 33
      Height = 31
      Caption = '+/-'
      Font.Charset = ANSI_CHARSET
      Font.Color = clBlack
      Font.Height = -13
      Font.Name = 'Verdana'
      Font.Style = [fsBold]
      ParentFont = False
      ParentShowHint = False
      PopupMenu = PumCalcula
      ShowHint = False
      OnClick = SbtMaiMenClick
    end
    object MemVisor: TMemo
      Left = 215
      Top = 1
      Width = 167
      Height = 236
      TabStop = False
      Align = alRight
      Alignment = taRightJustify
      PopupMenu = PumCalcula
      ReadOnly = True
      ScrollBars = ssVertical
      TabOrder = 0
      Visible = False
    end
    object EdtVrTotal: TBrvEditNum
      Left = 5
      Top = 10
      Width = 166
      Height = 21
      TabStop = False
      MaxLength = 8
      PopupMenu = PumCalcula
      ReadOnly = True
      TabOrder = 1
      Text = '0,00'
      BrAlignment = taRightJustify
      BrCasasDecimais = 2
      BrSepararMilhar = True
      BrAsInteger = 0
      BrAsFloatSQL = '0.00'
      BrVisibleButton = False
      BrFunctionButton = TpConsulta
      BrQueryCode = 0
    end
    object EdtVrMemori: TBrvEditNum
      Left = 224
      Top = 63
      Width = 139
      Height = 21
      TabStop = False
      MaxLength = 8
      ReadOnly = True
      TabOrder = 2
      Text = '0,00'
      Visible = False
      BrAlignment = taRightJustify
      BrCasasDecimais = 2
      BrSepararMilhar = True
      BrAsInteger = 0
      BrAsFloatSQL = '0.00'
      BrVisibleButton = False
      BrFunctionButton = TpConsulta
      BrQueryCode = 0
    end
  end
  object PumCalcula: TPopupMenu
    Left = 258
    Top = 106
    object Sair1: TMenuItem
      Caption = '&Sair'
      OnClick = Sair1Click
    end
    object MenRetVal: TMenuItem
      Caption = '&Retornar valor'
      OnClick = MenRetValClick
    end
    object Configurar1: TMenuItem
      Caption = '&Configurar'
      object Precisodecimal1: TMenuItem
        Caption = '&Preciso decimal'
        object MenSemDec: TMenuItem
          Caption = '&Sem decimais'
          GroupIndex = 1
          RadioItem = True
          OnClick = MenSemDecClick
        end
        object MenUmaCas: TMenuItem
          Caption = '&1 casa'
          GroupIndex = 1
          RadioItem = True
          OnClick = MenUmaCasClick
        end
        object MenDuaCas: TMenuItem
          Caption = '&2 casas'
          Checked = True
          GroupIndex = 1
          RadioItem = True
          OnClick = MenDuaCasClick
        end
        object MenTreCas: TMenuItem
          Caption = '&3 casas'
          GroupIndex = 1
          RadioItem = True
          OnClick = MenTreCasClick
        end
        object MenQuaCas: TMenuItem
          Caption = '&4 casas'
          GroupIndex = 1
          RadioItem = True
          OnClick = MenQuaCasClick
        end
        object MenCinCas: TMenuItem
          Caption = '&5 casas'
          GroupIndex = 1
          RadioItem = True
          OnClick = MenCinCasClick
        end
      end
      object MenSnSepMil: TMenuItem
        Caption = '&Separar milhar'
        Checked = True
        OnClick = MenSnSepMilClick
      end
    end
    object Funo2: TMenuItem
      Caption = '&Funo'
      object MenSubDat: TMenuItem
        Caption = '&Subtrao de datas'
        OnClick = MenSubDatClick
      end
    end
    object Exibir1: TMenuItem
      Caption = '&Exibir'
      object MenExiFit: TMenuItem
        Caption = '&Fita'
        OnClick = MenExiFitClick
      end
    end
  end
end
