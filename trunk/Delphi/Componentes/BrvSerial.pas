unit BrvSerial;
{********************************************************************
  TBrvSerial component for Delphi

  Author: Jason M. Perry 534 Denver Ave Chesapeake, VA 23320
  E-Mail: jmperr@visi.net

  Freeware: Feel free to use and improve.
  FreeInfo: Jesus Loves You!

  Recommendation:  Put the GetData method in a timer to continually
                   monitor the port.  Or, even better, put it on a
                   separate thread!!
*********************************************************************
Instructions
This component was intended to demonstrate serial communications, not
to use for a business application.  I highly recommend that if needs
are business, that you purchase a proven component such as AsyncPro
from TurboPower.
*********************************************************************
1.  Open the port
    Syntax:
            SerialPort1.OpenPort(cpCOM2);
            or
            if SerialPort1.OpenPort(cpCOM2) then do something.

2.  Send some data
    Syntax:
            var
              s : PChar;
            begin
              s := PChar(Edit1.Text);
              SerialPort1.SendData(s, SizeOf(s));

              // Send in the carriage return. (if necessary)
              SerialPort1.SendData((chr(13)), 1);
            end;

3.  Read a response, etc.
    Syntax:
       memo1.text := SerialPort1.GetData;
       (note, the GetData procedure call also calls the
       AfterReceive and OnReceive events).  This method
       should be on a separate thread, or in a timer.

4.  Flush the receive buffer, if you want to.
    Syntax:
            SerialPort1.FlushRX;

5.  Flush the transmit buffer, if you want to.
    Syntax:
            SerialPort1.FlushTX;

6.  Check to see if the port is open.
    Syntax:
            if SerialPort1.PortIsOpen then do something.

7.  Close the port when done, or let the destroy do it for you.
    Syntax:
            SerialPort1.ClosePort;
*********************************************************************}


interface

uses
  Windows, Messages, SysUtils, Classes,
  Graphics, Controls, Forms, Dialogs;

// This is the Device Control Block record.
// It is the structure that contains the
// serial port setup parameters. This
// structure must be initialized before the
// serial port can be used.  It is declared
// in the windows.pas unit and looks like this:
{type TDCB = record
       DCBLength:DWord;
       Baudrate:DWord;
       Flags:LongInt;
       wReserved:Word;
       XONLim:Word;
       XOFFLim:Word;
       ByteSize:Byte;
       Parity:Byte;
       StopBits:Byte;
       XONChar:Char;
       XOFFChar:Char;
       ErrorChar:Char;
       EOFChar:Char;
       EvtChar:Char;
       wReserved1:Word;
end;}

type
  // You can't do anything without a comm port.
  TCommPort = (cpCOM1, cpCOM2, cpCOM3, cpCOM4,
               cpCOM5, cpCOM6, cpCOM7, cpCOM8);

  // All of the baud rates that the DCB supports.
  TBaudRate = (br110, br300, br600, br1200, br2400, br4800, br9600,
               br14400, br19200, br38400, br56000, br128000, br256000);

  // Parity types for parity error checking
  TParityType = (pcNone, pcEven, pcOdd, pcMark, pcSpace);

  TStopBits = (sbOne, sbOnePtFive, sbTwo);

  TDataBits = (db4, db5, db6, db7, db8);

  TFlowControl = (fcNone, fcXON_XOFF, fcRTS_CTS, fsDSR_DTR);

  // Two new notify events.
  TNotifyTXEvent = procedure(Sender : TObject; data : string) of object;
  TNotifyRXEvent = procedure(Sender : TObject; data : string) of object;

  // Set some constant defaults.
// These are the qquivalent of
// COM2:9600,N,8,1;
const
  dflt_CommPort = cpCOM2;
  dflt_BaudRate = br9600;
  dflt_ParityType = pcNone;
  dflt_ParityErrorChecking = False;
  dflt_ParityErrorChar = 0;
  dflt_ParityErrorReplacement = False;
  dflt_StopBits = sbOne;
  dflt_DataBits = db8;
  dflt_XONChar = $11;  {ASCII 11h}
  dflt_XOFFChar = $13; {ASCII 13h}
  dflt_XONLim = 1024;
  dflt_XOFFLim = 2048;
  dflt_ErrorChar = 0; // For parity checking.
  dflt_FlowControl = fcNone;
  dflt_StripNullChars = False;
  dflt_EOFChar = 0;

type
  TBrvSerial = class(TComponent)
  private
    hCommPort : THandle; // Handle to the serial port.
    fCommPort : TCommPort;
    fBaudRate : TBaudRate;
    fParityType : TParityType;
    fParityErrorChecking : Boolean;
    fParityErrorChar : Byte;
    fParityErrorReplacement : Boolean;
    fStopBits : TStopBits;
    fDataBits : TDataBits;
    fXONChar : byte;  {0..255}
    fXOFFChar : byte; {0..255}
    fXONLim : word;  {0..65535}
    fXOFFLim : word; {0..65535}
    fErrorChar : byte;
    fFlowControl : TFlowControl;
    fStripNullChars : Boolean;  // Strip null chars?
    fEOFChar : Byte;
    fOnTransmit : TNotifyTXEvent;
    fOnReceive : TNotifyRXEvent;
    fAfterTransmit : TNotifyTXEvent;
    fAfterReceive : TNotifyRXEvent;
    ReadBuffer : String; // Where the results from the read goes.
    procedure SetCommPort(value : TCommPort);
    procedure SetBaudRate(value : TBaudRate);
    procedure SetParityType(value : TParityType);
    procedure SetParityErrorChecking(value : Boolean);
    procedure SetParityErrorChar(value : Byte);
    procedure SetParityErrorReplacement(value : Boolean);
    procedure SetStopBits(value : TStopBits);
    procedure SetDataBits(value : TDataBits);
    procedure SetXONChar(value : byte);
    procedure SetXOFFChar(value : byte);
    procedure SetXONLim(value : word);
    procedure SetXOFFLim(value : word);
    procedure SetErrorChar(value : byte);
    procedure SetFlowControl(value : TFlowControl);
    procedure SetStripNullChars(value : Boolean);
    procedure SetEOFChar(value : Byte);
    procedure Initialize_DCB;
  protected
  public
    constructor Create(AOwner : TComponent); override;
    destructor Destroy; override;
    function OpenPort(MyCommPort : TCommPort) : Boolean;
    function ClosePort : boolean;
    procedure SendData(data : PChar; size : DWord);
    function GetData : String;
    function PortIsOpen : boolean;
    procedure FlushTX;
    procedure FlushRX;
  published
    property CommPort : TCommport read fCommPort
                                  write SetCommPort
                                  default dflt_CommPort;
    property BaudRate : TBaudRate read fBaudRate
                                  write SetBaudRate
                                  default dflt_BaudRate;
    property ParityType : TParityType read fParityType
                                      write SetParityType
                                      default dflt_ParityType;
    property ParityErrorChecking : Boolean read fParityErrorChecking
                                           write SetParityErrorChecking
                                           default dflt_ParityErrorChecking;
    property ParityErrorChar : Byte read fParityErrorChar
                                    write SetParityErrorChar
                                    default dflt_ParityErrorChar;
    property ParityErrorReplacement : Boolean read fParityErrorReplacement
                                              write SetParityErrorReplacement
                                              default dflt_ParityErrorReplacement;
    property StopBits : TStopBits read fStopBits
                                  write SetStopBits
                                  default dflt_StopBits;
    property DataBits : TDataBits read fDataBits
                                  write SetDataBits
                                  default dflt_DataBits;
    property XONChar : byte read fXONChar
                            write SetXONChar
                            default dflt_XONChar;
    property XOFFChar : byte read fXOFFChar
                            write SetXOFFChar
                            default dflt_XOFFChar;
    property XONLim : word read fXONLim
                           write SetXONLim
                           default dflt_XONLim;
    property XOFFLim : word read fXOFFLim
                           write SetXOFFLim
                           default dflt_XOFFLim;
    property ErrorChar : byte read fErrorChar
                              write SetErrorChar
                              default dflt_ErrorChar;
    property FlowControl : TFlowControl read fFlowControl
                                        write SetFlowControl
                                        default dflt_FlowControl;
    property StripNullChars : Boolean read fStripNullChars
                                      write SetStripNullChars
                                      default dflt_StripNullChars;
    property EOFChar : byte read fEOFChar
                            write SetEOFChar
                            default dflt_EOFChar;
    property OnTransmit : TNotifyTXEvent read fOnTransmit
                                         write fOnTransmit;
    property OnReceive : TNotifyRXEvent read fOnReceive
                                        write fOnReceive;
    property AfterTransmit : TNotifyTXEvent read fAfterTransmit
                                            write fAfterTransmit;
    property AfterReceive : TNotifyRXEvent read fAfterReceive
                                            write fAfterReceive;
  end;

procedure Register;

implementation

// Public method to open the port and
// assign the handle to it.
function TBrvSerial.OpenPort(MyCommPort : TCommPort) : Boolean;
var
  MyPort : PChar;
begin
  // Make sure that the port is Closed first.
  ClosePort;

  MyPort := PChar('COM' + IntToStr(ord(fCommPort)));
  hCommPort := CreateFile(MyPort,
                          GENERIC_READ OR GENERIC_WRITE,
                          0,
                          nil,
                          OPEN_EXISTING,
                          0,0);
  // Initialize the port.
  Initialize_DCB;
  // Was successful if not and invalid handle.
  result := hCommPort <> INVALID_HANDLE_VALUE;
end;

// Public method to Close the port.
function TBrvSerial.ClosePort : boolean;
begin
  FlushTX;
  FlushRX;
  // Close the handle to the port.
  result := CloseHandle(hCommPort);
  hCommPort := INVALID_HANDLE_VALUE;
end;

// Public Send data method.
procedure TBrvSerial.SendData(data : PChar; size : DWord);
var
  NumBytesWritten : DWord;
begin
  if hCommPort = INVALID_HANDLE_VALUE then exit;

  if assigned(fOnTransmit) then fONTransmit(self, Data);

  WriteFile(hCommPort,
            Data^,
            Size,
            NumBytesWritten,
            nil);

  // Fire the transmit event.
  if assigned(fAfterTransmit) then fAfterTransmit(self, Data);
end;

// Public Get data method.
function TBrvSerial.GetData : String;
var
  NumBytesRead : DWord;
  // <<cbInQue>> Specifies the number
  // of bytes received by the serial
  // provider but not yet read by a
  // ReadFile operation.
  BytesInQueue : LongInt;  // Number of bytes in the input buffer
	oStatus: TComStat;       // Variable for the ComStat structure.
	dwErrorCode: DWord;      // Variable to put the error codes in.
begin
  if hCommPort = INVALID_HANDLE_VALUE then exit;

  if assigned(fOnReceive) then fONReceive(self, ReadBuffer);
  // Get the total number of bytes that
  // are waiting to be read from the
  // input buffer.
  ClearCommError(hCommPort, dwErrorCode, @oStatus);
	BytesInQueue := oStatus.cbInQue;

	if BytesInQueue > 0 then begin
		SetLength(ReadBuffer, BytesInQueue + 1);
		ReadFile(hCommPort,
             PChar(ReadBuffer)^,
             BytesInQueue,
             NumBytesRead,
             nil);
		SetLength(ReadBuffer, StrLen(PChar(ReadBuffer)));
	end;

  if assigned(fAfterReceive) then fAfterReceive(self, ReadBuffer);
  result := ReadBuffer;
end;

// Create method.
constructor TBrvSerial.Create(AOwner : TComponent);
begin
  inherited Create(AOwner);
  // Initalize the handle to the port as
  // an invalid handle value.  We do this
  // because the port hasn't been opened
  // yet, and it allows us to test for
  // this condition in some functions,
  // thereby controlling the behavior
  // of the function.

  hCommPort := INVALID_HANDLE_VALUE;

  // Set initial settings.  Even though
  // the default parameter was specified
  // in the property, if you were to
  // create a component at runtime, the
  // defaults would not get set.  So it
  // is important to call them again in
  // the create of the component.
  fCommPort := dflt_CommPort;
  fBaudRate := dflt_BaudRate;
  fParityType := dflt_ParityType;
  fParityErrorChecking := dflt_ParityErrorChecking;
  fParityErrorChar := dflt_ParityErrorChar;
  fParityErrorReplacement := dflt_ParityErrorReplacement;
  fStopBits := dflt_StopBits;
  fDataBits := dflt_DataBits;
  fXONChar := dflt_XONChar;
  fXOFFChar := dflt_XOFFChar;
  fXONLim := dflt_XONLim;
  fXOFFLim := dflt_XOFFLim;
  fErrorChar := dflt_ErrorChar;
  fFlowControl := dflt_FlowControl;
  fStripNullChars := dflt_StripNullChars;
  fEOFChar := dflt_EOFChar;
  fOnTransmit := nil;
  fOnReceive := nil;
end;

// Destroy method.
destructor TBrvSerial.Destroy;
begin
  // Close the port first;
  ClosePort;
  inherited Destroy;
end;

// Initialize the device control block.
procedure TBrvSerial.Initialize_DCB;
var
  MyDCB : TDCB;
  //MyCommTimeouts : TCommTimeouts;
begin

  // Only want to perform the setup
  // if the port has been opened and
  // the handle assigned.
  if hCommPort = INVALID_HANDLE_VALUE then exit;

  // The GetCommState function fills in a
  // device-control block (a DCB structure)
  // with the current control settings for
  // a specified communications device.
  // (Win32 Developers Reference)
  // Get a default fill of the DCB.
  GetCommState(hCommPort, MyDCB);

  case fBaudRate of
    br110 : MyDCB.BaudRate := 110;
    br300 : MyDCB.BaudRate := 300;
    br600 : MyDCB.BaudRate := 600;
    br1200 : MyDCB.BaudRate := 1200;
    br2400 : MyDCB.BaudRate := 2400;
    br4800 : MyDCB.BaudRate := 4800;
    br9600 : MyDCB.BaudRate := 9600;
    br14400 : MyDCB.BaudRate := 14400;
    br19200 : MyDCB.BaudRate := 19200;
    br38400 : MyDCB.BaudRate := 38400;
    br56000 : MyDCB.BaudRate := 56000;
    br128000 : MyDCB.BaudRate := 128000;
    br256000 : MyDCB.BaudRate := 256000;
  end;

  // Parity error checking parameters.
  case fParityType of
    pcNone : MyDCB.Parity := NOPARITY;
    pcEven : MyDCB.Parity := EVENPARITY;
    pcOdd : MyDCB.Parity := ODDPARITY;
    pcMark : MyDCB.Parity := MARKPARITY;
    pcSpace : MyDCB.Parity := SPACEPARITY;
  end;
  if fParityErrorChecking then inc(MyDCB.Flags, $0002);
  if fParityErrorReplacement then inc(MyDCB.Flags, $0021);
  MyDCB.ErrorChar := AnsiChar(fErrorChar);

  case fStopBits of
    sbOne : MyDCB.StopBits := ONESTOPBIT;
    sbOnePtFive : MyDCB.StopBits := ONE5STOPBITS;
    sbTwo : MyDCB.StopBits := TWOSTOPBITS;
  end;

  case fDataBits of
    db4 : MyDCB.ByteSize := 4;
    db5 : MyDCB.ByteSize := 5;
    db6 : MyDCB.ByteSize := 6;
    db7 : MyDCB.ByteSize := 7;
    db8 : MyDCB.ByteSize := 8;
  end;

  // The 'flags' are bit flags,
  // which means that the flags
  // either turn on or off the
  // desired flow control type.
  case fFlowControl of
    fcXON_XOFF : MyDCB.Flags := MyDCB.Flags or $0020 or $0018;
    fcRTS_CTS : MyDCB.Flags := MyDCB.Flags or $0004 or $0024*RTS_CONTROL_HANDSHAKE;
    fsDSR_DTR : MyDCB.Flags := MyDCB.Flags or $0008 or $0010*DTR_CONTROL_HANDSHAKE;
  end;

  if fStripNullChars then inc(MyDCB.Flags,$0022);

  MyDCB.XONChar := AnsiChar(fXONChar);
  MyDCB.XOFFChar := AnsiChar(fXONChar);

  // The XON Limit is the number of
  // bytes that the data in the
  // receive buffer must fall below
  // before sending the XON character,
  // there for resuming the flow
  // of data.
  MyDCB.XONLim := fXONLim;
  // The XOFF limit is the max number
  // of bytes that the receive buffer
  // can contain before sending the
  // XOFF character, therefore
  // stopping the flow of data.
  MyDCB.XOFFLim := fXOFFLim;

  // Character that signals the end of file.
  if fEOFChar <> 0 then MyDCB.EOFChar := Ansichar(EOFChar);

  // The SetCommTimeouts function sets
  // the time-out parameters for all
  // read and write operations on a
  // specified communications device.
  // (Win32 Developers Reference)
  // The GetCommTimeouts function retrieves
  // the time-out parameters for all read
  // and write operations on a specified
  // communications device.
  // GetCommTimeouts(hCommPort, MyCommTimeouts);
  // MyCommTimeouts.ReadIntervalTimeout := ...
  // MyCommTimeouts.ReadTotalTimeoutMultiplier := ...
  // MyCommTimeouts.etc...................
  // SetCommTimeouts(hCommPort, MyCommTimeouts);

  SetCommState(hCommPort, MyDCB);
end;

// Set the comm port.
procedure TBrvSerial.SetCommPort(value : TCommPort);
begin
  if fCommPort <> value then begin
    fCommPort := value;
    Initialize_DCB;
  end;
end;

// Set the baud rate.
procedure TBrvSerial.SetBaudRate(value : TBaudRate);
begin
  if fBaudRate <> value then begin
    fBaudRate := value;
    Initialize_DCB;
  end;
end;

// Set the parity check type.
procedure TBrvSerial.SetParityType(value : TParityType);
begin
  if fParityType <> value then begin
    fParityType := value;
    Initialize_DCB;
  end;
end;

// Do we want to do parity error checking?
procedure TBrvSerial.SetParityErrorChecking(value : Boolean);
begin
  if fParityErrorChecking <> value then
  begin
    fParityErrorChecking := value;
    Initialize_DCB;
  end;
end;

// Set the parity error char.
procedure TBrvSerial.SetParityErrorChar(value : Byte);
begin
  if fParityErrorChar <> value then
  begin
    fParityErrorChar := value;
    Initialize_DCB;
  end;
end;

// Set wether to replace parity errors with error char.
procedure TBrvSerial.SetParityErrorReplacement(value : Boolean);
begin
  if fParityErrorReplacement <> value then begin
    fParityErrorReplacement := value;
    Initialize_DCB;
  end;
end;

// Set the stop bits.
procedure TBrvSerial.SetStopBits(value : TStopBits);
begin
  if fStopBits <> value then begin
    fStopBits := value;
    Initialize_DCB;
  end;
end;

// Set the data bits.
procedure TBrvSerial.SetDataBits(value : TDataBits);
begin
  if fDataBits <> value then begin
    fDataBits := value;
    Initialize_DCB;
  end;
end;

// Set the XON Char.
procedure TBrvSerial.SetXONChar(value : byte);
begin
  if fXONChar <> value then begin
    fXONChar := value;
    Initialize_DCB;
  end;
end;

// Set the XOFF Char.
procedure TBrvSerial.SetXOFFChar(value : byte);
begin
  if fXOFFChar <> value then begin
    fXOFFChar := value;
    Initialize_DCB;
  end;
end;

// Set the XON Limit.
procedure TBrvSerial.SetXONLim(value : word);
begin
  if fXONLim <> value then begin
    fXONLim := value;
    Initialize_DCB;
  end;
end;

// Set the XOFF Limit.
procedure TBrvSerial.SetXOFFLim(value : word);
begin
  if fXOFFLim <> value then begin
    fXOFFLim := value;
    Initialize_DCB;
  end;
end;

// Set the error character.
procedure TBrvSerial.SetErrorChar(value : byte);
begin
  if fErrorChar <> value then begin
    fErrorChar := value;
    Initialize_DCB;
  end;
end;

// Set the type of flow control desired.
procedure TBrvSerial.SetFlowControl(value : TFlowControl);
begin
  if fFlowControl <> value then begin
    fFlowControl := value;
    Initialize_DCB;
  end;
end;

// Do we want to strip off the null characters?
procedure TBrvSerial.SetStripNullChars(value : Boolean);
begin
  if fStripNullChars <> value then begin
    fStripNullChars := value;
    Initialize_DCB;
  end;
end;

// Set the EOF char.
procedure TBrvSerial.SetEOFChar(value : Byte);
begin
  if fEOFChar <> value then begin
    fEOFChar := value;
    Initialize_DCB;
  end;
end;

// Public function to check if the port is open.
function TBrvSerial.PortIsOpen : boolean;
begin
  Result := hCommPort <> INVALID_HANDLE_VALUE;
end;

// Public method to cancel and flush the receive buffer.
procedure TBrvSerial.FlushRx;
begin
  if hCommPort = INVALID_HANDLE_VALUE then exit;
  PurgeComm(hCommPort, PURGE_RXABORT or PURGE_RXCLEAR);
  ReadBuffer := '';
end;

// Public method to cancel and flush the transmit buffer.
procedure TBrvSerial.FlushTx;
begin
  if hCommPort = INVALID_HANDLE_VALUE then exit;
  PurgeComm(hCommPort, PURGE_TXABORT or PURGE_TXCLEAR);
end;

// Register the component in its own menu selection.
procedure Register;
begin
  RegisterComponents('Bravo Utils', [TBrvSerial]);
end;

end.
