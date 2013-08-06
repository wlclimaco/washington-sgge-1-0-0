unit Unit1;

interface

uses
  Windows, Messages, SysUtils, Classes, Graphics, Controls, Forms, Dialogs,
  StdCtrls, SevenZipVCL, ComCtrls;

type
  TForm1 = class(TForm)
    SevenZip1: TSevenZip;
    listarchive: TButton;
    extractarchive: TButton;
    newarchive: TButton;
    setdebughandle: TButton;
    Label1: TLabel;
    ProgressBar1: TProgressBar;
    ProgressBar2: TProgressBar;
    OpenDialog1: TOpenDialog;
    Label2: TLabel;
    Label3: TLabel;
    OpenDialog2: TOpenDialog;
    ListView1: TListView;
    Memo1: TMemo;
    Label4: TLabel;
    Cancelop: TButton;
    Button1: TButton;
    Button2: TButton;
    volsize: TEdit;
    Label5: TLabel;
    Label6: TLabel;
    Password7z: TEdit;
    procedure listarchiveClick(Sender: TObject);
    procedure extractarchiveClick(Sender: TObject);
    procedure newarchiveClick(Sender: TObject);
    procedure SevenZip1PreProgress(Sender: TObject; MaxProgress: Int64);
    procedure SevenZip1Progress(Sender: TObject; Filename: WideString;
      FilePosArc, FilePosFile: Int64);
    procedure SevenZip1Addfile(Sender: TObject; Filename: WideString;
      Filesize: Int64);
    procedure setdebughandleClick(Sender: TObject);
    procedure SevenZip1Listfile(Sender: TObject; Filename: WideString;
      Fileindex, FileSizeU, FileSizeP, Fileattr, Filecrc: int64;
      Filemethod: WideString; FileTime: Double);
    procedure SevenZip1Extractfile(Sender: TObject; Filename: WideString;
      Filesize: Int64);
    procedure SevenZip1Message(Sender: TObject; ErrCode: Integer;
      Message: String; Filename: WideString);
    procedure CancelopClick(Sender: TObject);
    procedure Button1Click(Sender: TObject);
    procedure Button2Click(Sender: TObject);
    procedure SevenZip1ExtractOverwrite(Sender: TObject;
      FileName: WideString; var DoOverwrite: Boolean);
    procedure SevenZip1OpenVolume(var FileName: WideString;
      Removable: Boolean; out Cancel: Boolean);
  private
    { Private-Deklarationen }
  public
// for debug only
    procedure LogMessage(var msg: TMessage); message 9999;

    { Public-Deklarationen }
  end;

var
  Form1: TForm1;
  co:integer;
implementation

{$R *.DFM}

procedure TForm1.listarchiveClick(Sender: TObject);
begin
 memo1.Clear;
 listview1.items.Clear;
 opendialog2.InitialDir := 'c:\';
 if opendialog2.Execute then
  begin
   sevenzip1.SZFileName := opendialog2.filename;
   label1.caption := inttostr(sevenzip1.List);
  end;
end;

procedure TForm1.extractarchiveClick(Sender: TObject);
var i : integer;
begin
//select extract dir
 sevenzip1.ExtrBaseDir := 'c:\7ztest';

//Overwrite existing files during extrect ?
 sevenzip1.ExtractOptions := sevenzip1.ExtractOptions + [ExtractOverwrite];

//Clear Extractlist - for all files do nothing else
 sevenzip1.Files.clear;

 if listview1.SelCount > 0 then
  for i := 0 to listview1.Items.Count -1 do
     if listview1.Items[i].Selected then
       sevenzip1.Files.AddString(listview1.Items[i].SubItems[6]);

//extract files
 i := sevenzip1.Extract;

// Show result
 label1.caption := inttostr(i);
end;

procedure TForm1.newarchiveClick(Sender: TObject);
var i:integer;
begin
 opendialog1.InitialDir := 'c:\';
// if not opendialog1.Execute then exit;

//Set archive filname
 sevenzip1.SZFileName := 'c:\testvcl1_test.7z';

//Set Volumesize
 sevenzip1.VolumeSize := strtoint(volsize.text);

//Set rootdir for recuresive folder (if wanted)
 sevenzip1.AddRootDir := 'c:\demo';

 If password7z.Text <> '' then Sevenzip1.Password := password7z.Text;

//Clear filelist
 sevenzip1.Files.Clear;
 sevenzip1.Files.AddString('c:\demo2\*.*');

// for i := 0 to opendialog1.Files.count-1 do
//  sevenzip1.Files.AddString(opendialog1.Files[i]);

//add files
 i := Sevenzip1.Add;

// Show result
 label1.caption := inttostr(i);
end;

procedure Tform1.LogMessage(var msg: TMessage);
begin
//show all messages from dll, for debug only !
  memo1.lines.add(PString(msg.LParam)^);
  Dispose(PString(msg.LParam));
  application.ProcessMessages;
end;


procedure TForm1.SevenZip1PreProgress(Sender: TObject; MaxProgress: Int64);
begin
//set max progess (if wanted)
 if Maxprogress > 0 then progressbar1.Max := Maxprogress;
end;

procedure TForm1.SevenZip1Progress(Sender: TObject; Filename: WideString;
  FilePosArc, FilePosFile: Int64);
begin
//set progress during extraction

//progress of all files
 progressbar1.Position := fileposArc;

//progress of current file
 progressbar2.Position := fileposfile;
 application.ProcessMessages;
end;

procedure TForm1.SevenZip1Addfile(Sender: TObject; Filename: WideString;
  Filesize: Int64);
begin
// reset progress of a new file during adding
 memo1.lines.add(filename + inttostr(co));
 label1.caption := filename;
 progressbar2.position := 0;
 progressbar2.Max := filesize;
end;

procedure TForm1.setdebughandleClick(Sender: TObject);
begin
 sevenzipvcl.FMainhandle := form1.Handle;
end;

procedure TForm1.SevenZip1Listfile(Sender: TObject; Filename: WideString;
  Fileindex, FileSizeU, FileSizeP, Fileattr, Filecrc: int64;
  Filemethod: WideString; FileTime: Double);
var
  l :tlistitem;
begin
 if fileattr = fadirectory then exit;

 l := listview1.Items.Add;
 l.Caption := extractfilename(filename);
 l.SubItems.Add(extractfilepath(filename));
 l.SubItems.Add(inttostr(Fileattr));
 l.SubItems.Add(inttostr(filesizep));
 l.SubItems.Add(inttostr(filesizeu));
 l.SubItems.Add(inttohex(filecrc,8));
 l.SubItems.Add(filemethod);
 l.SubItems.Add(inttostr(fileindex));
 l.SubItems.Add(Datetimetostr(filetime));
end;

procedure TForm1.SevenZip1Extractfile(Sender: TObject;
  Filename: WideString; Filesize: Int64);
begin
// reset progress of a new file during adding
 memo1.lines.add(filename);
 label1.caption := filename;
 progressbar2.position := 0;
 progressbar2.Max := filesize;
end;

procedure TForm1.SevenZip1Message(Sender: TObject; ErrCode: Integer;
  Message: String; Filename: WideString);
begin
// all non dedug messages
memo1.lines.add(message + ' - ' + Filename);
end;

procedure TForm1.CancelopClick(Sender: TObject);
begin
sevenzip1.Cancel;
end;

procedure TForm1.Button1Click(Sender: TObject);
begin
sevenzip1.ConvertSFXto7z(sevenzip1.szfilename);
end;

procedure TForm1.Button2Click(Sender: TObject);
begin
sevenzip1.Convert7ztosfx(sevenzip1.szfilename);
end;

procedure TForm1.SevenZip1ExtractOverwrite(Sender: TObject;
  FileName: WideString; var DoOverwrite: Boolean);
begin
   DOOverwrite := true;
end;

procedure TForm1.SevenZip1OpenVolume(var FileName: WideString;
  Removable: Boolean; out Cancel: Boolean);
begin
memo1.lines.add(filename);
end;

end.
