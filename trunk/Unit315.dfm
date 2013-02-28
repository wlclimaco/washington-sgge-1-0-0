object FrmConsNfe: TFrmConsNfe
  Left = 212
  Top = 201
  Width = 783
  Height = 540
  Caption = 'FrmConsNfe'
  Color = clBtnFace
  Font.Charset = DEFAULT_CHARSET
  Font.Color = clWindowText
  Font.Height = -11
  Font.Name = 'MS Sans Serif'
  Font.Style = []
  OldCreateOrder = False
  OnCreate = FormCreate
  PixelsPerInch = 96
  TextHeight = 13
  object Panel1: TPanel
    Left = 0
    Top = 0
    Width = 775
    Height = 66
    Align = alTop
    TabOrder = 0
  end
  object Panel2: TPanel
    Left = 0
    Top = 66
    Width = 775
    Height = 339
    Align = alClient
    TabOrder = 1
    object GridView1: TGridView
      Left = 1
      Top = 1
      Width = 773
      Height = 337
      Align = alClient
      BorderStyle = bsSingle
      GridStyle = gsReport
      GridLinesStyle = lsNormal
      HeaderSize = 18
      HeaderStyle = hsAuto
      HideScrollBar = True
      InputSize = 16
      Options = [goHeader, goHighlightTextSelection]
      RowSize = 16
      ParentColor = False
      SelectionMoveDirection = mdDown
      SlideSize = 80
      TabOrder = 0
      TabStop = True
      WantReturns = False
      object TNumericColumn
        Alignment = taLeftJustify
        Color = clWindow
        Cursor = crDefault
        DefaultValue = '0'
        DrawingOptions = doNormal
        DefaultWidth = 80
        Font.Charset = DEFAULT_CHARSET
        Font.Color = clWindowText
        Font.Height = -11
        Font.Name = 'MS Sans Serif'
        Font.Style = []
        Footer.Color = clWindow
        Footer.FormulaKind = fkNone
        FormulaKind = fkNone
        Header.Color = clBtnFace
        Header.DisplayMode = dmTextOnly
        Options = [coCanClick, coCanSort, coCanInput, coEditorAutoSelect, coPublicUsing]
        Position = 1
        SlideBounds.Height = 0
        SlideBounds.Left = 0
        SlideBounds.Top = 0
        SlideBounds.Width = 0
        Sorted = False
        SortKind = skAscending
        SortType = stNumeric
        VerticalAlignment = vaMiddle
        Visible = True
        Width = 80
        WrapKind = wkEllipsis
        Max = 0
        Min = 0
      end
      object TNumericColumn
        Alignment = taLeftJustify
        Color = clWindow
        Cursor = crDefault
        DefaultValue = '0'
        DrawingOptions = doNormal
        DefaultWidth = 80
        Font.Charset = DEFAULT_CHARSET
        Font.Color = clWindowText
        Font.Height = -11
        Font.Name = 'MS Sans Serif'
        Font.Style = []
        Footer.Color = clWindow
        Footer.FormulaKind = fkNone
        FormulaKind = fkNone
        Header.Color = clBtnFace
        Header.DisplayMode = dmTextOnly
        Options = [coCanClick, coCanSort, coCanInput, coEditorAutoSelect, coPublicUsing]
        Position = 2
        SlideBounds.Height = 0
        SlideBounds.Left = 0
        SlideBounds.Top = 0
        SlideBounds.Width = 0
        Sorted = False
        SortKind = skAscending
        SortType = stNumeric
        VerticalAlignment = vaMiddle
        Visible = True
        Width = 80
        WrapKind = wkEllipsis
        Max = 0
        Min = 0
      end
      object TTextualColumn
        Alignment = taLeftJustify
        Color = clWindow
        Cursor = crDefault
        DrawingOptions = doNormal
        DefaultWidth = 80
        Font.Charset = DEFAULT_CHARSET
        Font.Color = clWindowText
        Font.Height = -11
        Font.Name = 'MS Sans Serif'
        Font.Style = []
        Footer.Color = clWindow
        Footer.FormulaKind = fkNone
        FormulaKind = fkNone
        Header.Color = clBtnFace
        Header.DisplayMode = dmTextOnly
        Options = [coCanClick, coCanSort, coCanInput, coEditorAutoSelect, coPublicUsing]
        Position = 3
        SlideBounds.Height = 0
        SlideBounds.Left = 0
        SlideBounds.Top = 0
        SlideBounds.Width = 0
        Sorted = False
        SortKind = skAscending
        SortType = stAlphabetic
        VerticalAlignment = vaMiddle
        Visible = True
        Width = 80
        WrapKind = wkEllipsis
        AutoExecute = False
      end
      object TTextualColumn
        Alignment = taLeftJustify
        Color = clWindow
        Cursor = crDefault
        DrawingOptions = doNormal
        DefaultWidth = 80
        Font.Charset = DEFAULT_CHARSET
        Font.Color = clWindowText
        Font.Height = -11
        Font.Name = 'MS Sans Serif'
        Font.Style = []
        Footer.Color = clWindow
        Footer.FormulaKind = fkNone
        FormulaKind = fkNone
        Header.Color = clBtnFace
        Header.DisplayMode = dmTextOnly
        Options = [coCanClick, coCanSort, coCanInput, coEditorAutoSelect, coPublicUsing]
        Position = 4
        SlideBounds.Height = 0
        SlideBounds.Left = 0
        SlideBounds.Top = 0
        SlideBounds.Width = 0
        Sorted = False
        SortKind = skAscending
        SortType = stAlphabetic
        VerticalAlignment = vaMiddle
        Visible = True
        Width = 80
        WrapKind = wkEllipsis
        AutoExecute = False
      end
      object TDateColumn
        Alignment = taLeftJustify
        Color = clWindow
        Cursor = crDefault
        DefaultValue = '14/02/2011'
        DrawingOptions = doNormal
        DefaultWidth = 80
        Font.Charset = DEFAULT_CHARSET
        Font.Color = clWindowText
        Font.Height = -11
        Font.Name = 'MS Sans Serif'
        Font.Style = []
        Footer.Color = clWindow
        Footer.FormulaKind = fkNone
        FormulaKind = fkNone
        Header.Color = clBtnFace
        Header.DisplayMode = dmTextOnly
        Options = [coCanClick, coCanSort, coCanInput, coEditorAutoSelect, coPublicUsing]
        Position = 5
        SlideBounds.Height = 0
        SlideBounds.Left = 0
        SlideBounds.Top = 0
        SlideBounds.Width = 0
        Sorted = False
        SortKind = skAscending
        SortType = stDate
        VerticalAlignment = vaMiddle
        Visible = True
        Width = 80
        WrapKind = wkEllipsis
        HideWhenEmpty = False
      end
      object TDateColumn
        Alignment = taLeftJustify
        Color = clWindow
        Cursor = crDefault
        DefaultValue = '14/02/2011'
        DrawingOptions = doNormal
        DefaultWidth = 80
        Font.Charset = DEFAULT_CHARSET
        Font.Color = clWindowText
        Font.Height = -11
        Font.Name = 'MS Sans Serif'
        Font.Style = []
        Footer.Color = clWindow
        Footer.FormulaKind = fkNone
        FormulaKind = fkNone
        Header.Color = clBtnFace
        Header.DisplayMode = dmTextOnly
        Options = [coCanClick, coCanSort, coCanInput, coEditorAutoSelect, coPublicUsing]
        Position = 6
        SlideBounds.Height = 0
        SlideBounds.Left = 0
        SlideBounds.Top = 0
        SlideBounds.Width = 0
        Sorted = False
        SortKind = skAscending
        SortType = stDate
        VerticalAlignment = vaMiddle
        Visible = True
        Width = 80
        WrapKind = wkEllipsis
        HideWhenEmpty = False
      end
      object TNumericColumn
        Alignment = taLeftJustify
        Color = clWindow
        Cursor = crDefault
        DefaultValue = '0'
        DrawingOptions = doNormal
        DefaultWidth = 80
        Font.Charset = DEFAULT_CHARSET
        Font.Color = clWindowText
        Font.Height = -11
        Font.Name = 'MS Sans Serif'
        Font.Style = []
        Footer.Color = clWindow
        Footer.FormulaKind = fkNone
        FormulaKind = fkNone
        Header.Color = clBtnFace
        Header.DisplayMode = dmTextOnly
        Options = [coCanClick, coCanSort, coCanInput, coEditorAutoSelect, coPublicUsing]
        Position = 7
        SlideBounds.Height = 0
        SlideBounds.Left = 0
        SlideBounds.Top = 0
        SlideBounds.Width = 0
        Sorted = False
        SortKind = skAscending
        SortType = stNumeric
        VerticalAlignment = vaMiddle
        Visible = True
        Width = 80
        WrapKind = wkEllipsis
        Max = 0
        Min = 0
      end
      object TNumericColumn
        Alignment = taLeftJustify
        Color = clWindow
        Cursor = crDefault
        DefaultValue = '0'
        DrawingOptions = doNormal
        DefaultWidth = 80
        Font.Charset = DEFAULT_CHARSET
        Font.Color = clWindowText
        Font.Height = -11
        Font.Name = 'MS Sans Serif'
        Font.Style = []
        Footer.Color = clWindow
        Footer.FormulaKind = fkNone
        FormulaKind = fkNone
        Header.Color = clBtnFace
        Header.DisplayMode = dmTextOnly
        Options = [coCanClick, coCanSort, coCanInput, coEditorAutoSelect, coPublicUsing]
        Position = 8
        SlideBounds.Height = 0
        SlideBounds.Left = 0
        SlideBounds.Top = 0
        SlideBounds.Width = 0
        Sorted = False
        SortKind = skAscending
        SortType = stNumeric
        VerticalAlignment = vaMiddle
        Visible = True
        Width = 80
        WrapKind = wkEllipsis
        Max = 0
        Min = 0
      end
      object TNumericColumn
        Alignment = taLeftJustify
        Color = clWindow
        Cursor = crDefault
        DefaultValue = '0'
        DrawingOptions = doNormal
        DefaultWidth = 80
        Font.Charset = DEFAULT_CHARSET
        Font.Color = clWindowText
        Font.Height = -11
        Font.Name = 'MS Sans Serif'
        Font.Style = []
        Footer.Color = clWindow
        Footer.FormulaKind = fkNone
        FormulaKind = fkNone
        Header.Color = clBtnFace
        Header.DisplayMode = dmTextOnly
        Options = [coCanClick, coCanSort, coCanInput, coEditorAutoSelect, coPublicUsing]
        Position = 9
        SlideBounds.Height = 0
        SlideBounds.Left = 0
        SlideBounds.Top = 0
        SlideBounds.Width = 0
        Sorted = False
        SortKind = skAscending
        SortType = stNumeric
        VerticalAlignment = vaMiddle
        Visible = True
        Width = 80
        WrapKind = wkEllipsis
        Max = 0
        Min = 0
      end
      object TNumericColumn
        Alignment = taLeftJustify
        Color = clWindow
        Cursor = crDefault
        DefaultValue = '0'
        DrawingOptions = doNormal
        DefaultWidth = 80
        Font.Charset = DEFAULT_CHARSET
        Font.Color = clWindowText
        Font.Height = -11
        Font.Name = 'MS Sans Serif'
        Font.Style = []
        Footer.Color = clWindow
        Footer.FormulaKind = fkNone
        FormulaKind = fkNone
        Header.Color = clBtnFace
        Header.DisplayMode = dmTextOnly
        Options = [coCanClick, coCanSort, coCanInput, coEditorAutoSelect, coPublicUsing]
        Position = 10
        SlideBounds.Height = 0
        SlideBounds.Left = 0
        SlideBounds.Top = 0
        SlideBounds.Width = 0
        Sorted = False
        SortKind = skAscending
        SortType = stNumeric
        VerticalAlignment = vaMiddle
        Visible = True
        Width = 80
        WrapKind = wkEllipsis
        Max = 0
        Min = 0
      end
      object TNumericColumn
        Alignment = taLeftJustify
        Color = clWindow
        Cursor = crDefault
        DefaultValue = '0'
        DrawingOptions = doNormal
        DefaultWidth = 80
        Font.Charset = DEFAULT_CHARSET
        Font.Color = clWindowText
        Font.Height = -11
        Font.Name = 'MS Sans Serif'
        Font.Style = []
        Footer.Color = clWindow
        Footer.FormulaKind = fkNone
        FormulaKind = fkNone
        Header.Color = clBtnFace
        Header.DisplayMode = dmTextOnly
        Options = [coCanClick, coCanSort, coCanInput, coEditorAutoSelect, coPublicUsing]
        Position = 11
        SlideBounds.Height = 0
        SlideBounds.Left = 0
        SlideBounds.Top = 0
        SlideBounds.Width = 0
        Sorted = False
        SortKind = skAscending
        SortType = stNumeric
        VerticalAlignment = vaMiddle
        Visible = True
        Width = 80
        WrapKind = wkEllipsis
        Max = 0
        Min = 0
      end
      object TNumericColumn
        Alignment = taLeftJustify
        Color = clWindow
        Cursor = crDefault
        DefaultValue = '0'
        DrawingOptions = doNormal
        DefaultWidth = 80
        Font.Charset = DEFAULT_CHARSET
        Font.Color = clWindowText
        Font.Height = -11
        Font.Name = 'MS Sans Serif'
        Font.Style = []
        Footer.Color = clWindow
        Footer.FormulaKind = fkNone
        FormulaKind = fkNone
        Header.Color = clBtnFace
        Header.DisplayMode = dmTextOnly
        Options = [coCanClick, coCanSort, coCanInput, coEditorAutoSelect, coPublicUsing]
        Position = 12
        SlideBounds.Height = 0
        SlideBounds.Left = 0
        SlideBounds.Top = 0
        SlideBounds.Width = 0
        Sorted = False
        SortKind = skAscending
        SortType = stNumeric
        VerticalAlignment = vaMiddle
        Visible = True
        Width = 80
        WrapKind = wkEllipsis
        Max = 0
        Min = 0
      end
      object TNumericColumn
        Alignment = taLeftJustify
        Color = clWindow
        Cursor = crDefault
        DefaultValue = '0'
        DrawingOptions = doNormal
        DefaultWidth = 80
        Font.Charset = DEFAULT_CHARSET
        Font.Color = clWindowText
        Font.Height = -11
        Font.Name = 'MS Sans Serif'
        Font.Style = []
        Footer.Color = clWindow
        Footer.FormulaKind = fkNone
        FormulaKind = fkNone
        Header.Color = clBtnFace
        Header.DisplayMode = dmTextOnly
        Options = [coCanClick, coCanSort, coCanInput, coEditorAutoSelect, coPublicUsing]
        Position = 13
        SlideBounds.Height = 0
        SlideBounds.Left = 0
        SlideBounds.Top = 0
        SlideBounds.Width = 0
        Sorted = False
        SortKind = skAscending
        SortType = stNumeric
        VerticalAlignment = vaMiddle
        Visible = True
        Width = 80
        WrapKind = wkEllipsis
        Max = 0
        Min = 0
      end
      object TNumericColumn
        Alignment = taLeftJustify
        Color = clWindow
        Cursor = crDefault
        DefaultValue = '0'
        DrawingOptions = doNormal
        DefaultWidth = 80
        Font.Charset = DEFAULT_CHARSET
        Font.Color = clWindowText
        Font.Height = -11
        Font.Name = 'MS Sans Serif'
        Font.Style = []
        Footer.Color = clWindow
        Footer.FormulaKind = fkNone
        FormulaKind = fkNone
        Header.Color = clBtnFace
        Header.DisplayMode = dmTextOnly
        Options = [coCanClick, coCanSort, coCanInput, coEditorAutoSelect, coPublicUsing]
        Position = 14
        SlideBounds.Height = 0
        SlideBounds.Left = 0
        SlideBounds.Top = 0
        SlideBounds.Width = 0
        Sorted = False
        SortKind = skAscending
        SortType = stNumeric
        VerticalAlignment = vaMiddle
        Visible = True
        Width = 80
        WrapKind = wkEllipsis
        Max = 0
        Min = 0
      end
      object TNumericColumn
        Alignment = taLeftJustify
        Color = clWindow
        Cursor = crDefault
        DefaultValue = '0'
        DrawingOptions = doNormal
        DefaultWidth = 80
        Font.Charset = DEFAULT_CHARSET
        Font.Color = clWindowText
        Font.Height = -11
        Font.Name = 'MS Sans Serif'
        Font.Style = []
        Footer.Color = clWindow
        Footer.FormulaKind = fkNone
        FormulaKind = fkNone
        Header.Color = clBtnFace
        Header.DisplayMode = dmTextOnly
        Options = [coCanClick, coCanSort, coCanInput, coEditorAutoSelect, coPublicUsing]
        Position = 15
        SlideBounds.Height = 0
        SlideBounds.Left = 0
        SlideBounds.Top = 0
        SlideBounds.Width = 0
        Sorted = False
        SortKind = skAscending
        SortType = stNumeric
        VerticalAlignment = vaMiddle
        Visible = True
        Width = 80
        WrapKind = wkEllipsis
        Max = 0
        Min = 0
      end
      object TNumericColumn
        Alignment = taLeftJustify
        Color = clWindow
        Cursor = crDefault
        DefaultValue = '0'
        DrawingOptions = doNormal
        DefaultWidth = 80
        Font.Charset = DEFAULT_CHARSET
        Font.Color = clWindowText
        Font.Height = -11
        Font.Name = 'MS Sans Serif'
        Font.Style = []
        Footer.Color = clWindow
        Footer.FormulaKind = fkNone
        FormulaKind = fkNone
        Header.Color = clBtnFace
        Header.DisplayMode = dmTextOnly
        Options = [coCanClick, coCanSort, coCanInput, coEditorAutoSelect, coPublicUsing]
        Position = 16
        SlideBounds.Height = 0
        SlideBounds.Left = 0
        SlideBounds.Top = 0
        SlideBounds.Width = 0
        Sorted = False
        SortKind = skAscending
        SortType = stNumeric
        VerticalAlignment = vaMiddle
        Visible = True
        Width = 80
        WrapKind = wkEllipsis
        Max = 0
        Min = 0
      end
      object TNumericColumn
        Alignment = taLeftJustify
        Color = clWindow
        Cursor = crDefault
        DefaultValue = '0'
        DrawingOptions = doNormal
        DefaultWidth = 80
        Font.Charset = DEFAULT_CHARSET
        Font.Color = clWindowText
        Font.Height = -11
        Font.Name = 'MS Sans Serif'
        Font.Style = []
        Footer.Color = clWindow
        Footer.FormulaKind = fkNone
        FormulaKind = fkNone
        Header.Color = clBtnFace
        Header.DisplayMode = dmTextOnly
        Options = [coCanClick, coCanSort, coCanInput, coEditorAutoSelect, coPublicUsing]
        Position = 17
        SlideBounds.Height = 0
        SlideBounds.Left = 0
        SlideBounds.Top = 0
        SlideBounds.Width = 0
        Sorted = False
        SortKind = skAscending
        SortType = stNumeric
        VerticalAlignment = vaMiddle
        Visible = True
        Width = 80
        WrapKind = wkEllipsis
        Max = 0
        Min = 0
      end
      object TNumericColumn
        Alignment = taLeftJustify
        Color = clWindow
        Cursor = crDefault
        DefaultValue = '0'
        DrawingOptions = doNormal
        DefaultWidth = 80
        Font.Charset = DEFAULT_CHARSET
        Font.Color = clWindowText
        Font.Height = -11
        Font.Name = 'MS Sans Serif'
        Font.Style = []
        Footer.Color = clWindow
        Footer.FormulaKind = fkNone
        FormulaKind = fkNone
        Header.Color = clBtnFace
        Header.DisplayMode = dmTextOnly
        Options = [coCanClick, coCanSort, coCanInput, coEditorAutoSelect, coPublicUsing]
        Position = 18
        SlideBounds.Height = 0
        SlideBounds.Left = 0
        SlideBounds.Top = 0
        SlideBounds.Width = 0
        Sorted = False
        SortKind = skAscending
        SortType = stNumeric
        VerticalAlignment = vaMiddle
        Visible = True
        Width = 80
        WrapKind = wkEllipsis
        Max = 0
        Min = 0
      end
      object TNumericColumn
        Alignment = taLeftJustify
        Color = clWindow
        Cursor = crDefault
        DefaultValue = '0'
        DrawingOptions = doNormal
        DefaultWidth = 80
        Font.Charset = DEFAULT_CHARSET
        Font.Color = clWindowText
        Font.Height = -11
        Font.Name = 'MS Sans Serif'
        Font.Style = []
        Footer.Color = clWindow
        Footer.FormulaKind = fkNone
        FormulaKind = fkNone
        Header.Color = clBtnFace
        Header.DisplayMode = dmTextOnly
        Options = [coCanClick, coCanSort, coCanInput, coEditorAutoSelect, coPublicUsing]
        Position = 19
        SlideBounds.Height = 0
        SlideBounds.Left = 0
        SlideBounds.Top = 0
        SlideBounds.Width = 0
        Sorted = False
        SortKind = skAscending
        SortType = stNumeric
        VerticalAlignment = vaMiddle
        Visible = True
        Width = 80
        WrapKind = wkEllipsis
        Max = 0
        Min = 0
      end
      object TTextualColumn
        Alignment = taLeftJustify
        Color = clWindow
        Cursor = crDefault
        DrawingOptions = doNormal
        DefaultWidth = 80
        Font.Charset = DEFAULT_CHARSET
        Font.Color = clWindowText
        Font.Height = -11
        Font.Name = 'MS Sans Serif'
        Font.Style = []
        Footer.Color = clWindow
        Footer.FormulaKind = fkNone
        FormulaKind = fkNone
        Header.Color = clBtnFace
        Header.DisplayMode = dmTextOnly
        Options = [coCanClick, coCanSort, coCanInput, coEditorAutoSelect, coPublicUsing]
        Position = 20
        SlideBounds.Height = 0
        SlideBounds.Left = 0
        SlideBounds.Top = 0
        SlideBounds.Width = 0
        Sorted = False
        SortKind = skAscending
        SortType = stAlphabetic
        VerticalAlignment = vaMiddle
        Visible = True
        Width = 80
        WrapKind = wkEllipsis
        AutoExecute = False
      end
      object TTextualColumn
        Alignment = taLeftJustify
        Color = clWindow
        Cursor = crDefault
        DrawingOptions = doNormal
        DefaultWidth = 80
        Font.Charset = DEFAULT_CHARSET
        Font.Color = clWindowText
        Font.Height = -11
        Font.Name = 'MS Sans Serif'
        Font.Style = []
        Footer.Color = clWindow
        Footer.FormulaKind = fkNone
        FormulaKind = fkNone
        Header.Color = clBtnFace
        Header.DisplayMode = dmTextOnly
        Options = [coCanClick, coCanSort, coCanInput, coEditorAutoSelect, coPublicUsing]
        Position = 21
        SlideBounds.Height = 0
        SlideBounds.Left = 0
        SlideBounds.Top = 0
        SlideBounds.Width = 0
        Sorted = False
        SortKind = skAscending
        SortType = stAlphabetic
        VerticalAlignment = vaMiddle
        Visible = True
        Width = 80
        WrapKind = wkEllipsis
        AutoExecute = False
      end
      object TTextualColumn
        Alignment = taLeftJustify
        Color = clWindow
        Cursor = crDefault
        DrawingOptions = doNormal
        DefaultWidth = 80
        Font.Charset = DEFAULT_CHARSET
        Font.Color = clWindowText
        Font.Height = -11
        Font.Name = 'MS Sans Serif'
        Font.Style = []
        Footer.Color = clWindow
        Footer.FormulaKind = fkNone
        FormulaKind = fkNone
        Header.Color = clBtnFace
        Header.DisplayMode = dmTextOnly
        Options = [coCanClick, coCanSort, coCanInput, coEditorAutoSelect, coPublicUsing]
        Position = 22
        SlideBounds.Height = 0
        SlideBounds.Left = 0
        SlideBounds.Top = 0
        SlideBounds.Width = 0
        Sorted = False
        SortKind = skAscending
        SortType = stAlphabetic
        VerticalAlignment = vaMiddle
        Visible = True
        Width = 80
        WrapKind = wkEllipsis
        AutoExecute = False
      end
      object TTextualColumn
        Alignment = taLeftJustify
        Color = clWindow
        Cursor = crDefault
        DrawingOptions = doNormal
        DefaultWidth = 80
        Font.Charset = DEFAULT_CHARSET
        Font.Color = clWindowText
        Font.Height = -11
        Font.Name = 'MS Sans Serif'
        Font.Style = []
        Footer.Color = clWindow
        Footer.FormulaKind = fkNone
        FormulaKind = fkNone
        Header.Color = clBtnFace
        Header.DisplayMode = dmTextOnly
        Options = [coCanClick, coCanSort, coCanInput, coEditorAutoSelect, coPublicUsing]
        Position = 23
        SlideBounds.Height = 0
        SlideBounds.Left = 0
        SlideBounds.Top = 0
        SlideBounds.Width = 0
        Sorted = False
        SortKind = skAscending
        SortType = stAlphabetic
        VerticalAlignment = vaMiddle
        Visible = True
        Width = 80
        WrapKind = wkEllipsis
        AutoExecute = False
      end
      object TTextualColumn
        Alignment = taLeftJustify
        Color = clWindow
        Cursor = crDefault
        DrawingOptions = doNormal
        DefaultWidth = 80
        Font.Charset = DEFAULT_CHARSET
        Font.Color = clWindowText
        Font.Height = -11
        Font.Name = 'MS Sans Serif'
        Font.Style = []
        Footer.Color = clWindow
        Footer.FormulaKind = fkNone
        FormulaKind = fkNone
        Header.Color = clBtnFace
        Header.DisplayMode = dmTextOnly
        Options = [coCanClick, coCanSort, coCanInput, coEditorAutoSelect, coPublicUsing]
        Position = 24
        SlideBounds.Height = 0
        SlideBounds.Left = 0
        SlideBounds.Top = 0
        SlideBounds.Width = 0
        Sorted = False
        SortKind = skAscending
        SortType = stAlphabetic
        VerticalAlignment = vaMiddle
        Visible = True
        Width = 80
        WrapKind = wkEllipsis
        AutoExecute = False
      end
      object TNumericColumn
        Alignment = taLeftJustify
        Color = clWindow
        Cursor = crDefault
        DefaultValue = '0'
        DrawingOptions = doNormal
        DefaultWidth = 80
        Font.Charset = DEFAULT_CHARSET
        Font.Color = clWindowText
        Font.Height = -11
        Font.Name = 'MS Sans Serif'
        Font.Style = []
        Footer.Color = clWindow
        Footer.FormulaKind = fkNone
        FormulaKind = fkNone
        Header.Color = clBtnFace
        Header.DisplayMode = dmTextOnly
        Options = [coCanClick, coCanSort, coCanInput, coEditorAutoSelect, coPublicUsing]
        Position = 25
        SlideBounds.Height = 0
        SlideBounds.Left = 0
        SlideBounds.Top = 0
        SlideBounds.Width = 0
        Sorted = False
        SortKind = skAscending
        SortType = stNumeric
        VerticalAlignment = vaMiddle
        Visible = True
        Width = 80
        WrapKind = wkEllipsis
        Max = 0
        Min = 0
      end
      object TNumericColumn
        Alignment = taLeftJustify
        Color = clWindow
        Cursor = crDefault
        DefaultValue = '0'
        DrawingOptions = doNormal
        DefaultWidth = 80
        Font.Charset = DEFAULT_CHARSET
        Font.Color = clWindowText
        Font.Height = -11
        Font.Name = 'MS Sans Serif'
        Font.Style = []
        Footer.Color = clWindow
        Footer.FormulaKind = fkNone
        FormulaKind = fkNone
        Header.Color = clBtnFace
        Header.DisplayMode = dmTextOnly
        Options = [coCanClick, coCanSort, coCanInput, coEditorAutoSelect, coPublicUsing]
        Position = 26
        SlideBounds.Height = 0
        SlideBounds.Left = 0
        SlideBounds.Top = 0
        SlideBounds.Width = 0
        Sorted = False
        SortKind = skAscending
        SortType = stNumeric
        VerticalAlignment = vaMiddle
        Visible = True
        Width = 80
        WrapKind = wkEllipsis
        Max = 0
        Min = 0
      end
      object TNumericColumn
        Alignment = taLeftJustify
        Color = clWindow
        Cursor = crDefault
        DefaultValue = '0'
        DrawingOptions = doNormal
        DefaultWidth = 80
        Font.Charset = DEFAULT_CHARSET
        Font.Color = clWindowText
        Font.Height = -11
        Font.Name = 'MS Sans Serif'
        Font.Style = []
        Footer.Color = clWindow
        Footer.FormulaKind = fkNone
        FormulaKind = fkNone
        Header.Color = clBtnFace
        Header.DisplayMode = dmTextOnly
        Options = [coCanClick, coCanSort, coCanInput, coEditorAutoSelect, coPublicUsing]
        Position = 27
        SlideBounds.Height = 0
        SlideBounds.Left = 0
        SlideBounds.Top = 0
        SlideBounds.Width = 0
        Sorted = False
        SortKind = skAscending
        SortType = stNumeric
        VerticalAlignment = vaMiddle
        Visible = True
        Width = 80
        WrapKind = wkEllipsis
        Max = 0
        Min = 0
      end
      object TTextualColumn
        Alignment = taLeftJustify
        Color = clWindow
        Cursor = crDefault
        DrawingOptions = doNormal
        DefaultWidth = 80
        Font.Charset = DEFAULT_CHARSET
        Font.Color = clWindowText
        Font.Height = -11
        Font.Name = 'MS Sans Serif'
        Font.Style = []
        Footer.Color = clWindow
        Footer.FormulaKind = fkNone
        FormulaKind = fkNone
        Header.Color = clBtnFace
        Header.DisplayMode = dmTextOnly
        Options = [coCanClick, coCanSort, coCanInput, coEditorAutoSelect, coPublicUsing]
        Position = 28
        SlideBounds.Height = 0
        SlideBounds.Left = 0
        SlideBounds.Top = 0
        SlideBounds.Width = 0
        Sorted = False
        SortKind = skAscending
        SortType = stAlphabetic
        VerticalAlignment = vaMiddle
        Visible = True
        Width = 80
        WrapKind = wkEllipsis
        AutoExecute = False
      end
      object TTextualColumn
        Alignment = taLeftJustify
        Color = clWindow
        Cursor = crDefault
        DrawingOptions = doNormal
        DefaultWidth = 80
        Font.Charset = DEFAULT_CHARSET
        Font.Color = clWindowText
        Font.Height = -11
        Font.Name = 'MS Sans Serif'
        Font.Style = []
        Footer.Color = clWindow
        Footer.FormulaKind = fkNone
        FormulaKind = fkNone
        Header.Color = clBtnFace
        Header.DisplayMode = dmTextOnly
        Options = [coCanClick, coCanSort, coCanInput, coEditorAutoSelect, coPublicUsing]
        Position = 29
        SlideBounds.Height = 0
        SlideBounds.Left = 0
        SlideBounds.Top = 0
        SlideBounds.Width = 0
        Sorted = False
        SortKind = skAscending
        SortType = stAlphabetic
        VerticalAlignment = vaMiddle
        Visible = True
        Width = 80
        WrapKind = wkEllipsis
        AutoExecute = False
      end
      object TTextualColumn
        Alignment = taLeftJustify
        Color = clWindow
        Cursor = crDefault
        DrawingOptions = doNormal
        DefaultWidth = 80
        Font.Charset = DEFAULT_CHARSET
        Font.Color = clWindowText
        Font.Height = -11
        Font.Name = 'MS Sans Serif'
        Font.Style = []
        Footer.Color = clWindow
        Footer.FormulaKind = fkNone
        FormulaKind = fkNone
        Header.Color = clBtnFace
        Header.DisplayMode = dmTextOnly
        Options = [coCanClick, coCanSort, coCanInput, coEditorAutoSelect, coPublicUsing]
        Position = 30
        SlideBounds.Height = 0
        SlideBounds.Left = 0
        SlideBounds.Top = 0
        SlideBounds.Width = 0
        Sorted = False
        SortKind = skAscending
        SortType = stAlphabetic
        VerticalAlignment = vaMiddle
        Visible = True
        Width = 80
        WrapKind = wkEllipsis
        AutoExecute = False
      end
      object TNumericColumn
        Alignment = taLeftJustify
        Color = clWindow
        Cursor = crDefault
        DefaultValue = '0'
        DrawingOptions = doNormal
        DefaultWidth = 80
        Font.Charset = DEFAULT_CHARSET
        Font.Color = clWindowText
        Font.Height = -11
        Font.Name = 'MS Sans Serif'
        Font.Style = []
        Footer.Color = clWindow
        Footer.FormulaKind = fkNone
        FormulaKind = fkNone
        Header.Color = clBtnFace
        Header.DisplayMode = dmTextOnly
        Options = [coCanClick, coCanSort, coCanInput, coEditorAutoSelect, coPublicUsing]
        Position = 31
        SlideBounds.Height = 0
        SlideBounds.Left = 0
        SlideBounds.Top = 0
        SlideBounds.Width = 0
        Sorted = False
        SortKind = skAscending
        SortType = stNumeric
        VerticalAlignment = vaMiddle
        Visible = True
        Width = 80
        WrapKind = wkEllipsis
        Max = 0
        Min = 0
      end
      object TNumericColumn
        Alignment = taLeftJustify
        Color = clWindow
        Cursor = crDefault
        DefaultValue = '0'
        DrawingOptions = doNormal
        DefaultWidth = 80
        Font.Charset = DEFAULT_CHARSET
        Font.Color = clWindowText
        Font.Height = -11
        Font.Name = 'MS Sans Serif'
        Font.Style = []
        Footer.Color = clWindow
        Footer.FormulaKind = fkNone
        FormulaKind = fkNone
        Header.Color = clBtnFace
        Header.DisplayMode = dmTextOnly
        Options = [coCanClick, coCanSort, coCanInput, coEditorAutoSelect, coPublicUsing]
        Position = 32
        SlideBounds.Height = 0
        SlideBounds.Left = 0
        SlideBounds.Top = 0
        SlideBounds.Width = 0
        Sorted = False
        SortKind = skAscending
        SortType = stNumeric
        VerticalAlignment = vaMiddle
        Visible = True
        Width = 80
        WrapKind = wkEllipsis
        Max = 0
        Min = 0
      end
      object TTextualColumn
        Alignment = taLeftJustify
        Color = clWindow
        Cursor = crDefault
        DrawingOptions = doNormal
        DefaultWidth = 80
        Font.Charset = DEFAULT_CHARSET
        Font.Color = clWindowText
        Font.Height = -11
        Font.Name = 'MS Sans Serif'
        Font.Style = []
        Footer.Color = clWindow
        Footer.FormulaKind = fkNone
        FormulaKind = fkNone
        Header.Color = clBtnFace
        Header.DisplayMode = dmTextOnly
        Options = [coCanClick, coCanSort, coCanInput, coEditorAutoSelect, coPublicUsing]
        Position = 33
        SlideBounds.Height = 0
        SlideBounds.Left = 0
        SlideBounds.Top = 0
        SlideBounds.Width = 0
        Sorted = False
        SortKind = skAscending
        SortType = stAlphabetic
        VerticalAlignment = vaMiddle
        Visible = True
        Width = 80
        WrapKind = wkEllipsis
        AutoExecute = False
      end
      object TNumericColumn
        Alignment = taLeftJustify
        Color = clWindow
        Cursor = crDefault
        DefaultValue = '0'
        DrawingOptions = doNormal
        DefaultWidth = 80
        Font.Charset = DEFAULT_CHARSET
        Font.Color = clWindowText
        Font.Height = -11
        Font.Name = 'MS Sans Serif'
        Font.Style = []
        Footer.Color = clWindow
        Footer.FormulaKind = fkNone
        FormulaKind = fkNone
        Header.Color = clBtnFace
        Header.DisplayMode = dmTextOnly
        Options = [coCanClick, coCanSort, coCanInput, coEditorAutoSelect, coPublicUsing]
        Position = 34
        SlideBounds.Height = 0
        SlideBounds.Left = 0
        SlideBounds.Top = 0
        SlideBounds.Width = 0
        Sorted = False
        SortKind = skAscending
        SortType = stNumeric
        VerticalAlignment = vaMiddle
        Visible = True
        Width = 80
        WrapKind = wkEllipsis
        Max = 0
        Min = 0
      end
      object TCheckBoxColumn
        Alignment = taLeftJustify
        Color = clWindow
        Cursor = crDefault
        DrawingOptions = doNormal
        DefaultWidth = 80
        Font.Charset = DEFAULT_CHARSET
        Font.Color = clWindowText
        Font.Height = -11
        Font.Name = 'MS Sans Serif'
        Font.Style = []
        Footer.Color = clWindow
        Footer.FormulaKind = fkNone
        FormulaKind = fkNone
        Header.Color = clBtnFace
        Header.DisplayMode = dmTextOnly
        Options = [coCanClick, coCanSort, coCanInput, coEditorAutoSelect, coPublicUsing]
        Position = 0
        SlideBounds.Height = 0
        SlideBounds.Left = 0
        SlideBounds.Top = 0
        SlideBounds.Width = 0
        Sorted = False
        SortKind = skAscending
        SortType = stNumeric
        VerticalAlignment = vaMiddle
        Visible = True
        Width = 80
        WrapKind = wkEllipsis
      end
    end
  end
  object Panel3: TPanel
    Left = 0
    Top = 405
    Width = 775
    Height = 101
    Align = alBottom
    TabOrder = 2
  end
  object GridPrint1: TGridPrint
    FromRow = 0
    Grid = GridView1
    HeaderSize = 100
    Margins.Bottom = 20
    Margins.Left = 10
    Margins.Right = 20
    Margins.Top = 10
    Options = [gpoHorzGridLines]
    RowSize = 86
    ToRow = 0
    Left = 149
    Top = 27
  end
  object DataCellSource1: TDataCellSource
    Active = True
    Grid = GridView1
    CalculateFooter = False
    DataSet = IBQuery1
    Left = 186
    Top = 25
  end
  object IBQuery1: TIBQuery
    Database = DataModule1.DBPrincipal
    Transaction = DataModule1.IBTransaction1
    Active = True
    BufferChunks = 1000
    CachedUpdates = False
    SQL.Strings = (
      'select * from NFFISCAL')
    Left = 252
    Top = 22
    object IBQuery1IND_OPER: TIntegerField
      FieldName = 'IND_OPER'
      Origin = 'NFFISCAL.IND_OPER'
    end
    object IBQuery1IND_EMIT: TIntegerField
      FieldName = 'IND_EMIT'
      Origin = 'NFFISCAL.IND_EMIT'
    end
    object IBQuery1COD_PART: TIBStringField
      FieldName = 'COD_PART'
      Origin = 'NFFISCAL.COD_PART'
      Size = 100
    end
    object IBQuery1COD_SIT: TIBStringField
      FieldName = 'COD_SIT'
      Origin = 'NFFISCAL.COD_SIT'
      Size = 100
    end
    object IBQuery1DT_DOC: TDateField
      FieldName = 'DT_DOC'
      Origin = 'NFFISCAL.DT_DOC'
    end
    object IBQuery1DT_E_S: TDateField
      FieldName = 'DT_E_S'
      Origin = 'NFFISCAL.DT_E_S'
    end
    object IBQuery1VL_DOC: TIntegerField
      FieldName = 'VL_DOC'
      Origin = 'NFFISCAL.VL_DOC'
    end
    object IBQuery1VL_DESC: TIntegerField
      FieldName = 'VL_DESC'
      Origin = 'NFFISCAL.VL_DESC'
    end
    object IBQuery1VL_FORN: TIntegerField
      FieldName = 'VL_FORN'
      Origin = 'NFFISCAL.VL_FORN'
    end
    object IBQuery1VL_SERV_NT: TIntegerField
      FieldName = 'VL_SERV_NT'
      Origin = 'NFFISCAL.VL_SERV_NT'
    end
    object IBQuery1VL_TERC: TIntegerField
      FieldName = 'VL_TERC'
      Origin = 'NFFISCAL.VL_TERC'
    end
    object IBQuery1VL_DA: TIntegerField
      FieldName = 'VL_DA'
      Origin = 'NFFISCAL.VL_DA'
    end
    object IBQuery1VL_BC_ICMS: TIntegerField
      FieldName = 'VL_BC_ICMS'
      Origin = 'NFFISCAL.VL_BC_ICMS'
    end
    object IBQuery1VL_ICMS: TIntegerField
      FieldName = 'VL_ICMS'
      Origin = 'NFFISCAL.VL_ICMS'
    end
    object IBQuery1VL_BC_ICMS_ST: TIntegerField
      FieldName = 'VL_BC_ICMS_ST'
      Origin = 'NFFISCAL.VL_BC_ICMS_ST'
    end
    object IBQuery1VL_ICMS_ST: TIntegerField
      FieldName = 'VL_ICMS_ST'
      Origin = 'NFFISCAL.VL_ICMS_ST'
    end
    object IBQuery1COD_INF: TIntegerField
      FieldName = 'COD_INF'
      Origin = 'NFFISCAL.COD_INF'
    end
    object IBQuery1VL_COFINS: TIntegerField
      FieldName = 'VL_COFINS'
      Origin = 'NFFISCAL.VL_COFINS'
    end
    object IBQuery1TP_LIGACAO: TIntegerField
      FieldName = 'TP_LIGACAO'
      Origin = 'NFFISCAL.TP_LIGACAO'
    end
    object IBQuery1COD_GRUPO_TENSAO: TIBStringField
      FieldName = 'COD_GRUPO_TENSAO'
      Origin = 'NFFISCAL.COD_GRUPO_TENSAO'
      Size = 100
    end
    object IBQuery1CHV_CTE: TIBStringField
      FieldName = 'CHV_CTE'
      Origin = 'NFFISCAL.CHV_CTE'
      Size = 100
    end
    object IBQuery1TP_CT: TIBStringField
      FieldName = 'TP_CT'
      Origin = 'NFFISCAL.TP_CT'
      Size = 100
    end
    object IBQuery1CHV_CTE_REF: TIBStringField
      FieldName = 'CHV_CTE_REF'
      Origin = 'NFFISCAL.CHV_CTE_REF'
      Size = 100
    end
    object IBQuery1IND_FRT: TIBStringField
      FieldName = 'IND_FRT'
      Origin = 'NFFISCAL.IND_FRT'
      Size = 100
    end
    object IBQuery1CODNFE: TIntegerField
      FieldName = 'CODNFE'
      Origin = 'NFFISCAL.CODNFE'
      Required = True
    end
    object IBQuery1COD_EMPRESA: TIntegerField
      FieldName = 'COD_EMPRESA'
      Origin = 'NFFISCAL.COD_EMPRESA'
      Required = True
    end
    object IBQuery1DCNUMERO: TIntegerField
      FieldName = 'DCNUMERO'
      Origin = 'NFFISCAL.DCNUMERO'
    end
    object IBQuery1DCSERIE: TIBStringField
      FieldName = 'DCSERIE'
      Origin = 'NFFISCAL.DCSERIE'
      Size = 4
    end
    object IBQuery1DCORDEM: TIBStringField
      FieldName = 'DCORDEM'
      Origin = 'NFFISCAL.DCORDEM'
      Size = 2
    end
    object IBQuery1DCTIPO: TIBStringField
      FieldName = 'DCTIPO'
      Origin = 'NFFISCAL.DCTIPO'
      Size = 5
    end
    object IBQuery1CDPEDIDO: TIntegerField
      FieldName = 'CDPEDIDO'
      Origin = 'NFFISCAL.CDPEDIDO'
    end
    object IBQuery1NATUREZA: TIntegerField
      FieldName = 'NATUREZA'
      Origin = 'NFFISCAL.NATUREZA'
    end
    object IBQuery1CFOP: TIBStringField
      FieldName = 'CFOP'
      Origin = 'NFFISCAL.CFOP'
      Size = 4
    end
    object IBQuery1COD_FILIAL: TIntegerField
      FieldName = 'COD_FILIAL'
      Origin = 'NFFISCAL.COD_FILIAL'
    end
  end
end
