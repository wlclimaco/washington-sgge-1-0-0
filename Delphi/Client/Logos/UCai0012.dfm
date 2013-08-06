inherited Cai0012: TCai0012
  Left = 752
  Top = 260
  Caption = 'Cai0012 - Descompacta Arquivos'
  ExplicitLeft = 752
  ExplicitTop = 260
  ExplicitWidth = 436
  ExplicitHeight = 252
  PixelsPerInch = 96
  TextHeight = 13
  object SevenZip: TSevenZip
    SFXCreate = False
    SFXModule = '7z.sfx'
    AddOptions = []
    ExtractOptions = [ExtractNoPath, ExtractOverwrite]
    LZMACompressType = LZMA
    LZMACompressStrength = ULTRA
    LZMAStrength = 0
    LPPMDmem = 30
    LPPMDsize = 10
    NumberOfFiles = -1
    VolumeSize = 0
    OnProgress = SevenZipProgress
    OnPreProgress = SevenZipPreProgress
    Left = 296
    Top = 96
  end
end
