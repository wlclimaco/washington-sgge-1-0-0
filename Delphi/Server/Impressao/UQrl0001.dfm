inherited Qrl0001: TQrl0001
  DataSet = CdsRelato
  Functions.DATA = (
    '0'
    '0'
    #39#39)
  Page.Values = (
    100.000000000000000000
    2970.000000000000000000
    100.000000000000000000
    2100.000000000000000000
    100.000000000000000000
    100.000000000000000000
    0.000000000000000000)
  inherited PageHeaderBand1: TQRBand
    Size.Values = (
      124.354166666666700000
      1899.708333333333000000)
    inherited QRSysDataPagina: TQRSysData
      Size.Values = (
        44.979166666666670000
        1770.062500000000000000
        5.291666666666667000
        129.645833333333300000)
      FontSize = 10
    end
    inherited QRSysDataDateTime: TQRSysData
      Size.Values = (
        44.979166666666670000
        1703.916666666667000000
        63.500000000000000000
        195.791666666666700000)
      FontSize = 10
    end
    inherited QRLabel2: TQRLabel
      Size.Values = (
        44.979166666666670000
        1688.041666666667000000
        5.291666666666667000
        121.708333333333400000)
      FontSize = 10
    end
    inherited QrLblTitle: TQRLabel
      Left = 193
      Size.Values = (
        52.916666666666670000
        510.645833333333300000
        5.291666666666667000
        1166.812500000000000000)
      Caption = 'Teste servidor de impressao'
      FontSize = 12
      ExplicitLeft = 193
    end
    inherited QrImage1: TQRImage
      Top = 2
      Size.Values = (
        113.770833333333300000
        13.229166666666670000
        5.291666666666667000
        476.250000000000000000)
      ExplicitTop = 2
    end
    inherited QrLblSubTit: TQRLabel
      Size.Values = (
        52.916666666666670000
        505.354166666666700000
        63.500000000000000000
        1164.166666666667000000)
      FontSize = 12
    end
  end
  inherited QrLblNmRelato: TQRLabel
    Left = 644
    Width = 111
    Size.Values = (
      29.104166666666670000
      1703.916666666667000000
      71.437500000000000000
      293.687500000000000000)
    FontSize = 6
    ExplicitLeft = 644
    ExplicitWidth = 111
  end
  object QRBand1: TQRBand
    Left = 38
    Top = 85
    Width = 718
    Height = 40
    Frame.Color = clBlack
    Frame.DrawTop = False
    Frame.DrawBottom = False
    Frame.DrawLeft = False
    Frame.DrawRight = False
    AlignToBottom = False
    Color = clWhite
    TransparentBand = False
    ForceNewColumn = False
    ForceNewPage = False
    Size.Values = (
      105.833333333333300000
      1899.708333333333000000)
    PreCaluculateBandHeight = False
    KeepOnOnePage = False
    BandType = rbDetail
    object QRLabel1: TQRLabel
      Left = 8
      Top = 0
      Width = 579
      Height = 17
      Frame.Color = clBlack
      Frame.DrawTop = False
      Frame.DrawBottom = False
      Frame.DrawLeft = False
      Frame.DrawRight = False
      Size.Values = (
        44.979166666666670000
        21.166666666666670000
        0.000000000000000000
        1531.937500000000000000)
      XLColumn = 0
      Alignment = taLeftJustify
      AlignToBand = False
      AutoSize = True
      AutoStretch = False
      Caption = 
        'Se o teste com servidor de aplicacao estiver correto, o nome do ' +
        'usuario aparecera na proxima linha:'
      Color = clWhite
      Transparent = False
      WordWrap = True
      ExportAs = exptText
      WrapStyle = BreakOnSpaces
      FontSize = 10
    end
    object QRDBText1: TQRDBText
      Left = 8
      Top = 23
      Width = 705
      Height = 17
      Frame.Color = clBlack
      Frame.DrawTop = False
      Frame.DrawBottom = False
      Frame.DrawLeft = False
      Frame.DrawRight = False
      Size.Values = (
        44.979166666666670000
        21.166666666666670000
        60.854166666666680000
        1865.312500000000000000)
      XLColumn = 0
      Alignment = taLeftJustify
      AlignToBand = False
      AutoSize = False
      AutoStretch = False
      Color = clWhite
      DataSet = CdsRelato
      DataField = 'NmComUsu'
      Transparent = False
      WordWrap = True
      ExportAs = exptText
      WrapStyle = BreakOnSpaces
      FullJustify = False
      MaxBreakChars = 0
      FontSize = 10
    end
  end
  object CdsRelato: TClientDataSet
    Aggregates = <>
    Params = <>
    Left = 40
    Top = 136
  end
end
