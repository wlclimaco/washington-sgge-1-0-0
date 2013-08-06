inherited frmRelatorioGerencialFormatado: TfrmRelatorioGerencialFormatado
  Left = 305
  Top = 176
  Caption = 'Relat'#243'rio gerencial com formata'#231#227'o'
  ClientHeight = 450
  ClientWidth = 400
  PixelsPerInch = 96
  TextHeight = 13
  inherited pnlCliente: TPanel
    Width = 400
    Height = 420
    object memRelatorio: TMemo
      Left = 1
      Top = 1
      Width = 398
      Height = 418
      Align = alClient
      Lines.Strings = (
        '<e>TEXTO EXPANDIDO</e>'
        '<n>TEXTO NEGRITO</n>'
        '<s>TEXTO SUBLINHADO</s>'
        '<c>TEXTO CONDENSADO</c>'
        '<i>TEXTO ITALICO</i>'
        '</linha_dupla>'
        ''
        'CODIGOS DE BARRA'
        '</linha_simples>'
        'EAN 8'
        '<ean8>1234567</ean8>'
        ''
        'EAN 13'
        '<ean13>123456789012</ean13>'
        ''
        'STANDART 2 0F 5'
        '<std>12345678</std>'
        ''
        'INTERLEAVE 2 OF 5'
        '<inter>12345678</inter>'
        ''
        'CODE 11'
        '<code11>123456789</code11>'
        ''
        'CODE 39'
        '<code39>123456789ABCD</code39>'
        ''
        'CODE 93'
        '<code93>123456789ABCD</code93>'
        ''
        'CODE 128'
        '<code128>123456789ABCD</code128>'
        ''
        'UPCA'
        '<upca>12345678901</upca>'
        ''
        'CODABAR'
        '<codabar>A123456789A</codabar>'
        ''
        'MSI'
        '<msi>1234567890</msi>')
      ScrollBars = ssVertical
      TabOrder = 0
    end
  end
  inherited pnlRodape: TPanel
    Top = 420
    Width = 400
    inherited btnExecutar: TButton
      Left = 190
    end
    inherited btnCancelar: TButton
      Left = 296
    end
  end
end
