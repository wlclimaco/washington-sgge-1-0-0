//===============================================================================
// Copyright (c) Serhiy Perevoznyk.  All rights reserved.
// THIS CODE AND INFORMATION IS PROVIDED "AS IS" WITHOUT WARRANTY
// OF ANY KIND, EITHER EXPRESSED OR IMPLIED, INCLUDING BUT NOT
// LIMITED TO THE IMPLIED WARRANTIES OF MERCHANTABILITY AND
// FITNESS FOR A PARTICULAR PURPOSE.
//===============================================================================

{$I Quricol.inc}

unit QuricolCode;

interface

uses
  Windows, SysUtils, Classes, Graphics;

type

{ Summary
  QR Code generator
  Example
  <code lang="delphi">
  var
    bmp : TBitmap;
    MS : TMemoryStream;
  begin
    try
      //Generate Windows bitmap and save to file
      TQRCode.GenerateBitmapFile('delphi1.bmp', 'http://delphi32.blogspot.com');
  
      //Generate PNG image and save to file
      TQRCode.GeneratePngFile('delphi1.png', 'http://delphi32.blogspot.com');
  
      //Generate TBitmap
      bmp := TQRCode.GetBitmapImage('http://www.krento.net');
      bmp.SaveToFile('delphi2.bmp');
  
      //Generate PNG to the memory stream
      MS := TMemoryStream.Create;
      TQRCode.GetPngStream(MS, 'http://www.krento.net');
      MS.Position := 0;
      MS.SaveToFile('delphi2.png');
      MS.Free;
  
    except
      on E: Exception do
        Writeln(E.ClassName, ': ', E.Message);
    end;
  </code>                                                                        }

  TQRCode = class
  public
    { Summary
      Generates Windows Bitmap file with the text encoded as QR
      code
      Parameters
      FileName :   The name of the bitmap file<p />
      Text :       The text to encode<p />
      Margin :     The margin from the border<p />
      PixelSize :  The size of the one image point.             }
    class procedure GenerateBitmapFile(const FileName : string; const Text : string; Margin : integer = 4; PixelSize : integer = 3);
    { Summary
      Generates PNG file with the text encoded as QR code
      Parameters
      FileName :   The name of the png file<p />
      Text :       The text to encode<p />
      Margin :     The margin from the border<p />
      PixelSize :  The size of the one image point.       }
    class procedure GeneratePngFile(const FileName : string; const Text : string; Margin : integer = 4; PixelSize : integer = 3);
    { Summary
      Created Windows Bitmap image with the text encoded as QR code
      Parameters
      Text - The text to encode
      
      Margin - The margin from the image border
      
      PixelSize - The size of the one point of the image
                                                                    }
    class function  GetBitmapImage(const Text : string; Margin : integer = 4; PixelSize : integer = 3) : TBitmap;
    { Summary
      Writes PNG image to the stream. Can be useful for web
      development
      Parameters
      Stream - The stream where image will be stored

      Text - The text to encode

      Margin - The margin from the image border

      PixelSize - The size of the one image point           }
    class procedure GetPngStream(Stream : TStream; const Text : string; Margin : integer = 4; PixelSize : integer = 3);
  end;

implementation

const quricol32 = 'quricol32.dll';

procedure GeneratePNGW(fileName: PWChar; text : PWChar; margin : integer; size : integer); stdcall; external quricol32 {$IFDEF DELPHI2010}delayed{$ENDIF};
procedure GeneratePNGA(fileName: PChar; text : PChar; margin : integer; size : integer); stdcall; external quricol32 {$IFDEF DELPHI2010}delayed{$ENDIF};
{$IFDEF UNICODE}
procedure GeneratePNG(fileName: PWChar; text : PWChar; margin : integer; size : integer); stdcall; external quricol32 name 'GeneratePNGW' {$IFDEF DELPHI2010}delayed{$ENDIF};
{$ELSE}
procedure GeneratePNG(fileName: PChar; text : PChar; margin : integer; size : integer); stdcall; external quricol32 name 'GeneratePNGA' {$IFDEF DELPHI2010}delayed{$ENDIF};
{$ENDIF}

function GetHBitmapW(text : PWChar; margin : integer; size : integer) : HBITMAP; stdcall; external quricol32 {$IFDEF DELPHI2010}delayed{$ENDIF};
function GetHBitmapA(text : PChar; margin : integer; size : integer) : HBITMAP; stdcall; external quricol32 {$IFDEF DELPHI2010}delayed{$ENDIF};
{$IFDEF UNICODE}
function GetHBitmap(text : PWChar; margin : integer; size : integer) : HBITMAP; stdcall; external quricol32 name 'GetHBitmapW' {$IFDEF DELPHI2010}delayed{$ENDIF};
{$ELSE}
function GetHBitmap(text : PChar; margin : integer; size : integer) : HBITMAP; stdcall; external quricol32 name 'GetHBitmapA' {$IFDEF DELPHI2010}delayed{$ENDIF};
{$ENDIF}


procedure GenerateBMPW(fileName: PWChar; text : PWChar; margin : integer; size : integer); stdcall; external quricol32 {$IFDEF DELPHI2010}delayed{$ENDIF};
procedure GenerateBMPA(fileName: PChar; text : PChar; margin : integer; size : integer); stdcall; external quricol32 {$IFDEF DELPHI2010}delayed{$ENDIF};
{$IFDEF UNICODE}
procedure GenerateBMP(fileName: PWChar; text : PWChar; margin : integer; size : integer); stdcall; external quricol32 name 'GenerateBMPW' {$IFDEF DELPHI2010}delayed{$ENDIF};
{$ELSE}
procedure GenerateBMP(fileName: PChar; text : PChar; margin : integer; size : integer); stdcall; external quricol32 name 'GenerateBMPA' {$IFDEF DELPHI2010}delayed{$ENDIF};
{$ENDIF}

procedure GetPNGW(text : PWChar; margin : integer; size : integer; var bufSize : integer; out ppvBits : PByte); stdcall; external quricol32 {$IFDEF DELPHI2010}delayed{$ENDIF};
procedure GetPNGA(text : PChar; margin : integer; size : integer; var bufSize : integer; out ppvBits : PByte); stdcall; external quricol32 {$IFDEF DELPHI2010}delayed{$ENDIF};
{$IFDEF UNICODE}
procedure GetPNG(text : PWChar; margin : integer; size : integer; var bufSize : integer; out ppvBits : PByte); stdcall; external quricol32 name 'GetPNGW' {$IFDEF DELPHI2010}delayed{$ENDIF};
{$ELSE}
procedure GetPNG(text : PChar; margin : integer; size : integer; var bufSize : integer; out ppvBits : PByte); stdcall; external quricol32 name 'GetPNGA' {$IFDEF DELPHI2010}delayed{$ENDIF};
{$ENDIF}

procedure DestroyBuffer(Buffer : PByte); stdcall; external quricol32 {$IFDEF DELPHI2010}delayed{$ENDIF};

{ TQRCode }

class procedure TQRCode.GenerateBitmapFile(const FileName, Text: string; Margin,
  PixelSize: integer);
begin
  GenerateBMP(PChar(FileName), PChar(Text), Margin, PixelSize);
end;

class procedure TQRCode.GeneratePngFile(const FileName, Text: string; Margin,
  PixelSize: integer);
begin
  GeneratePNG(PChar(FileName), PChar(Text), Margin, PixelSize);
end;

class function TQRCode.GetBitmapImage(const Text: string; Margin,
  PixelSize: integer): TBitmap;
var
  Bmp : HBITMAP;
  DIB: TDIBSection;
  ScreenDC : THandle;
  DC : THandle;
begin
  Bmp := GetHBitmap(PChar(Text), Margin, PixelSize);
  GetObject(Bmp, SizeOf(DIB), @DIB);
  Result := TBitmap.Create();
  Result.Width := DIB.dsBmih.biWidth;
  Result.Height := DIB.dsBmih.biHeight;
  Result.PixelFormat := pf32bit;
  ScreenDC := GetDC(0);
  DC := CreateCompatibleDC(ScreenDC);
  SelectObject(DC, Bmp);
  ReleaseDC(0, ScreenDC);
  BitBlt(Result.Canvas.Handle, 0, 0, Result.Width, Result.Height, DC, 0, 0, SRCCOPY);
  DeleteDC(DC);
  DeleteObject(Bmp);
end;

class procedure TQRCode.GetPngStream(Stream: TStream; const Text: string; Margin,
  PixelSize: integer);
var
  size : integer;
  i : integer;
  buffer : PByte;
  ps : PByte;
begin
  size := 0;
  GetPNG(PChar(Text), Margin, PixelSize, size, buffer);
  if (size > 0) then
   begin
     ps := buffer;
     for I := 0 to size - 1 do
       begin
         Stream.Write(ps^, 1);
         inc(ps);
       end;
      DestroyBuffer(buffer);
   end;

end;

end.
