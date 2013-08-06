object Form1: TForm1
  Left = 223
  Top = 151
  BorderIcons = [biSystemMenu, biMinimize]
  BorderStyle = bsSingle
  Caption = 'Form1'
  ClientHeight = 564
  ClientWidth = 785
  Color = clBtnFace
  Font.Charset = DEFAULT_CHARSET
  Font.Color = clWindowText
  Font.Height = -11
  Font.Name = 'MS Sans Serif'
  Font.Style = []
  OldCreateOrder = False
  PixelsPerInch = 96
  TextHeight = 13
  object Label1: TLabel
    Left = 96
    Top = 88
    Width = 32
    Height = 13
    Caption = 'Label1'
  end
  object Label2: TLabel
    Left = 24
    Top = 112
    Width = 57
    Height = 13
    Caption = 'FileProgress'
  end
  object Label3: TLabel
    Left = 24
    Top = 141
    Width = 65
    Height = 13
    Caption = 'TotalProgress'
  end
  object Label4: TLabel
    Left = 24
    Top = 88
    Width = 16
    Height = 13
    Caption = 'File'
  end
  object Label5: TLabel
    Left = 16
    Top = 58
    Width = 245
    Height = 13
    Caption = 'Volumesize in bytes (leave 0 for disable multivolume)'
  end
  object Label6: TLabel
    Left = 392
    Top = 58
    Width = 46
    Height = 13
    Caption = 'Password'
  end
  object newarchive: TButton
    Left = 8
    Top = 0
    Width = 129
    Height = 25
    Caption = 'New archive'
    TabOrder = 2
    OnClick = newarchiveClick
  end
  object listarchive: TButton
    Left = 136
    Top = 0
    Width = 129
    Height = 25
    Caption = 'List archive'
    TabOrder = 0
    OnClick = listarchiveClick
  end
  object extractarchive: TButton
    Left = 264
    Top = 0
    Width = 129
    Height = 25
    Caption = 'Extract'
    TabOrder = 1
    OnClick = extractarchiveClick
  end
  object setdebughandle: TButton
    Left = 632
    Top = 0
    Width = 129
    Height = 25
    Caption = 'SetFmainhandle (Debug)'
    TabOrder = 3
    OnClick = setdebughandleClick
  end
  object ProgressBar1: TProgressBar
    Left = 96
    Top = 136
    Width = 665
    Height = 25
    Min = 0
    Max = 100
    TabOrder = 4
  end
  object ProgressBar2: TProgressBar
    Left = 96
    Top = 112
    Width = 665
    Height = 17
    Min = 0
    Max = 100
    TabOrder = 5
  end
  object ListView1: TListView
    Left = 24
    Top = 168
    Width = 737
    Height = 249
    Columns = <
      item
        Caption = 'Filename'
        Width = 130
      end
      item
        Caption = 'Dir'
        Width = 110
      end
      item
        Caption = 'Attr'
        Width = 30
      end
      item
        Alignment = taRightJustify
        Caption = 'FS Packed'
        Width = 80
      end
      item
        Alignment = taRightJustify
        Caption = 'FS Unpacked'
        Width = 80
      end
      item
        Alignment = taRightJustify
        Caption = 'CRC'
        Width = 70
      end
      item
        Caption = 'Method'
        Width = 60
      end
      item
        Caption = 'ID'
        Width = 30
      end
      item
        Caption = 'Date-Time'
        Width = 120
      end>
    MultiSelect = True
    ReadOnly = True
    RowSelect = True
    TabOrder = 6
    ViewStyle = vsReport
  end
  object Memo1: TMemo
    Left = 24
    Top = 424
    Width = 737
    Height = 137
    Font.Charset = ANSI_CHARSET
    Font.Color = clWindowText
    Font.Height = -11
    Font.Name = 'Courier'
    Font.Style = []
    ParentFont = False
    ScrollBars = ssBoth
    TabOrder = 7
  end
  object Cancelop: TButton
    Left = 632
    Top = 24
    Width = 129
    Height = 25
    Caption = 'Cancel'
    TabOrder = 8
    OnClick = CancelopClick
  end
  object Button2: TButton
    Left = 8
    Top = 24
    Width = 129
    Height = 25
    Caption = 'Convert 7z to SFX'
    TabOrder = 10
    OnClick = Button2Click
  end
  object Button1: TButton
    Left = 136
    Top = 24
    Width = 129
    Height = 25
    Caption = 'Convert SXF to 7z'
    TabOrder = 9
    OnClick = Button1Click
  end
  object volsize: TEdit
    Left = 272
    Top = 56
    Width = 81
    Height = 21
    TabOrder = 11
    Text = '0'
  end
  object Password7z: TEdit
    Left = 448
    Top = 56
    Width = 81
    Height = 21
    TabOrder = 12
  end
  object SevenZip1: TSevenZip
    SFXCreate = False
    SFXModule = '7z.sfx'
    AddOptions = [AddRecurseDirs]
    ExtractOptions = []
    LZMACompressType = LZMA
    LZMACompressStrength = FAST
    LZMAStrength = 0
    LPPMDmem = 0
    LPPMDsize = 0
    NumberOfFiles = -1
    VolumeSize = 0
    OnListfile = SevenZip1listfile
    OnAddfile = SevenZip1Addfile
    OnExtractfile = SevenZip1Extractfile
    OnProgress = SevenZip1Progress
    OnPreProgress = SevenZip1PreProgress
    OnMessage = SevenZip1Message
    OnExtractOverwrite = SevenZip1ExtractOverwrite
    OnOpenVolume = SevenZip1OpenVolume
    Left = 696
    Top = 72
  end
  object OpenDialog1: TOpenDialog
    Filter = '*.*|*.*'
    Options = [ofHideReadOnly, ofAllowMultiSelect, ofEnableSizing]
    Left = 728
    Top = 72
  end
  object OpenDialog2: TOpenDialog
    Filter = '*.7z;*.exe;*.001|*.7z;*.exe;*.001|*.exe|*.exe'
    Left = 664
    Top = 72
  end
end
