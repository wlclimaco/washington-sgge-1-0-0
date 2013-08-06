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
          mxStringGridExport: TmxStringGridExport;
          Label1: TLabel;
          btn_Export: TButton;
          btn_Close: TButton;
          Procedure btn_CloseClick( Sender: TObject );
          Procedure FormCreate( Sender: TObject );
          Procedure btn_ExportClick( Sender: TObject );
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
               StringGrid.Cells[ I, X ] := IntToStr( ( I + 1 ) * X );
End;

Procedure Tfrm_MainWindow.btn_ExportClick( Sender: TObject );
Begin
     mxStringGridExport.Select;
End;

End.

