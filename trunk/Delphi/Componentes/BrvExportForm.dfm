object FrmExpRes: TFrmExpRes
  Left = 620
  Top = 229
  Caption = 'Colunas para exporta'#231#227'o'
  ClientHeight = 400
  ClientWidth = 255
  Color = clBtnFace
  Font.Charset = DEFAULT_CHARSET
  Font.Color = clWindowText
  Font.Height = -11
  Font.Name = 'MS Sans Serif'
  Font.Style = []
  OldCreateOrder = False
  Position = poScreenCenter
  OnCreate = FormCreate
  PixelsPerInch = 96
  TextHeight = 13
  object Panel1: TPanel
    Left = 0
    Top = 0
    Width = 255
    Height = 400
    Align = alClient
    BorderStyle = bsSingle
    TabOrder = 0
    object Panel2: TPanel
      Left = 1
      Top = 360
      Width = 249
      Height = 35
      Align = alBottom
      BorderStyle = bsSingle
      TabOrder = 0
      object BitBtn1: TBrvBitBtn
        Left = 46
        Top = 2
        Width = 75
        Height = 25
        Caption = '&Ok'
        Default = True
        DoubleBuffered = True
        NumGlyphs = 2
        ParentDoubleBuffered = False
        TabOrder = 0
        OnClick = BitBtn1Click
        BrTipoBotao = BrBtnOk
      end
      object BitBtn2: TBrvBitBtn
        Left = 126
        Top = 2
        Width = 75
        Height = 25
        Caption = '&Cancelar'
        DoubleBuffered = True
        ModalResult = 2
        NumGlyphs = 2
        ParentDoubleBuffered = False
        TabOrder = 1
        BrTipoBotao = BrBtnCancel
      end
    end
    object LcbColunas: TCheckListBox
      Left = 1
      Top = 106
      Width = 249
      Height = 254
      Align = alClient
      ItemHeight = 13
      PopupMenu = PopupMenu1
      TabOrder = 1
    end
    object Panel3: TPanel
      Left = 1
      Top = 1
      Width = 249
      Height = 105
      Align = alTop
      BorderStyle = bsSingle
      TabOrder = 2
      object Panel4: TPanel
        Left = 129
        Top = 1
        Width = 115
        Height = 99
        Align = alClient
        BevelOuter = bvNone
        TabOrder = 0
        object Memo1: TMemo
          Left = 0
          Top = 31
          Width = 115
          Height = 68
          TabStop = False
          Align = alBottom
          Alignment = taCenter
          BevelInner = bvNone
          BevelOuter = bvNone
          BorderStyle = bsNone
          Font.Charset = DEFAULT_CHARSET
          Font.Color = clRed
          Font.Height = -11
          Font.Name = 'MS Sans Serif'
          Font.Style = [fsBold]
          Lines.Strings = (
            'Somente o formato '
            'Bravo permite que '
            'os dados sejam '
            'importados '
            'novamente!')
          ParentColor = True
          ParentFont = False
          ReadOnly = True
          TabOrder = 0
        end
        object PnlSepara: TPanel
          Left = 0
          Top = 0
          Width = 115
          Height = 31
          Align = alTop
          BevelOuter = bvNone
          TabOrder = 1
          Visible = False
          object Label1: TLabel
            Left = 7
            Top = 9
            Width = 59
            Height = 13
            Caption = 'Separador'
            Font.Charset = DEFAULT_CHARSET
            Font.Color = clBlue
            Font.Height = -11
            Font.Name = 'MS Sans Serif'
            Font.Style = [fsBold]
            ParentFont = False
          end
          object EdtTpCarSep: TEdit
            Left = 73
            Top = 6
            Width = 39
            Height = 21
            MaxLength = 3
            TabOrder = 0
            Text = '@@'
          end
        end
      end
      object RgpTpExport: TRadioGroup
        Left = 1
        Top = 1
        Width = 128
        Height = 99
        Align = alLeft
        Caption = 'Exportar no formato:'
        Font.Charset = DEFAULT_CHARSET
        Font.Color = clWindowText
        Font.Height = -11
        Font.Name = 'MS Sans Serif'
        Font.Style = [fsBold]
        ItemIndex = 0
        Items.Strings = (
          'Bravo'
          'Colunar'
          'Linear'
          'MsExcel'
          'MsWord'
          'HTML')
        ParentFont = False
        TabOrder = 1
        OnClick = RgpTpExportClick
      end
    end
  end
  object PopupMenu1: TPopupMenu
    Left = 42
    Top = 194
    object Marcartodos1: TMenuItem
      Caption = '&Marcar todos'
      OnClick = Marcartodos1Click
    end
    object Desmarcartodos1: TMenuItem
      Caption = '&Desmarcar todos'
      OnClick = Desmarcartodos1Click
    end
  end
end
