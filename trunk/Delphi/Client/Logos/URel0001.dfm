inherited Rel0001: TRel0001
  Left = 539
  Top = 187
  Caption = 'REL0001 - Teste servidor de impress'#227'o'
  PixelsPerInch = 96
  TextHeight = 13
  inherited PnlFundo: TPanel
    object Label1: TLabel
      Left = 8
      Top = 16
      Width = 74
      Height = 13
      Caption = 'Tipo de teste'
      Font.Charset = DEFAULT_CHARSET
      Font.Color = clBlue
      Font.Height = -11
      Font.Name = 'Tahoma'
      Font.Style = [fsBold]
      ParentFont = False
    end
    object CbxTpTeste: TBrvComboBox
      Left = 88
      Top = 13
      Width = 145
      Height = 21
      Style = csDropDownList
      TabOrder = 0
      Items.Strings = (
        'Caracter'
        'Gr'#225'fico (PDF)')
      Values.Strings = (
        'C'
        'G')
    end
  end
  inherited LspS049: TBrvListParam
    Top = 144
  end
end
