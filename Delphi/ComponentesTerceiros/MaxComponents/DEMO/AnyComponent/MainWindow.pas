Unit MainWindow;

Interface

Uses
     Windows,
     Messages,
     SysUtils,
     Classes,
     Graphics,
     Controls,
     Forms,
     Dialogs,
     StdCtrls,
     mxExport,
     Grids,
     ExtCtrls;

Type
     Tfrm_MainWindow = Class( TForm )
          Panel1: TPanel;
          StringGrid: TStringGrid;
          Label1: TLabel;
          btn_Export: TButton;
          btn_Close: TButton;
          Label2: TLabel;
          mxAnyExport: TmxAnyExport;
          Procedure btn_CloseClick( Sender: TObject );
          Procedure FormCreate( Sender: TObject );
          Procedure btn_ExportClick( Sender: TObject );
          Procedure mxAnyExportGetExportInfo( Sender: TObject;
               Var RecordCount: Cardinal; Var FieldCount: Integer );
          Procedure mxAnyExportGetFieldLength( Sender: TObject; Index: Integer;
               Var FieldLength: Integer );
          Procedure mxAnyExportGetFieldName( Sender: TObject; Index: Integer;
               Var FieldName: String );
          Procedure mxAnyExportGetExportData( Sender: TObject; Index: Integer;
               Var Data: TmxValues );
     Private
    { Private declarations }
     Public
    { Public declarations }
     End;

Var
     frm_MainWindow: Tfrm_MainWindow;

Implementation

{$R *.DFM}

Procedure Tfrm_MainWindow.btn_CloseClick( Sender: TObject );
Begin
     Close;
End;

Procedure Tfrm_MainWindow.FormCreate( Sender: TObject );
Var
     X, I: Integer;
Begin
     For I := 0 To StringGrid.ColCount - 1 Do
          StringGrid.Cells[ I, 0 ] := Format( 'Field %d', [ I ] );

     For I := 0 To StringGrid.ColCount - 1 Do
          For X := 1 To StringGrid.RowCount - 1 Do
               StringGrid.Cells[ I, X ] := IntToStr( ( I + 1 ) * X )+'.'+IntToStr( ( I + 1 ) * X );
End;

Procedure Tfrm_MainWindow.btn_ExportClick( Sender: TObject );
Begin
     mxAnyExport.Select;
End;

Procedure Tfrm_MainWindow.mxAnyExportGetExportInfo( Sender: TObject;
     Var RecordCount: Cardinal; Var FieldCount: Integer );
Begin
     RecordCount := StringGrid.RowCount - 1;
     FieldCount := StringGrid.ColCount;
End;

Procedure Tfrm_MainWindow.mxAnyExportGetFieldLength( Sender: TObject;
     Index: Integer; Var FieldLength: Integer );
Begin
     FieldLength := 7;
End;

Procedure Tfrm_MainWindow.mxAnyExportGetFieldName( Sender: TObject;
     Index: Integer; Var FieldName: String );
Begin
     FieldName := StringGrid.Cells[ Index, 0 ];
End;

Procedure Tfrm_MainWindow.mxAnyExportGetExportData( Sender: TObject;
     Index: Integer; Var Data: TmxValues );
Var
     I: Integer;
Begin
     For I := 0 To StringGrid.ColCount - 1 Do
          Data[ I ] := StringGrid.Cells[ I, Index  + 1];
End;

End.

