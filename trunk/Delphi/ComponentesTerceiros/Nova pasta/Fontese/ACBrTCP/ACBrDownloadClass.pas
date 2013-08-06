{******************************************************************************}
{ Projeto: Componentes ACBr                                                    }
{  Biblioteca multiplataforma de componentes Delphi para intera��o com equipa- }
{ mentos de Automa��o Comercial utilizados no Brasil                           }
{                                                                              }
{ Direitos Autorais Reservados (c) 2004 Daniel Simoes de Almeida               }
{                                                                              }
{ Colaboradores nesse arquivo: Isaque Pinheiro                                 }
{                                                                              }
{  Voc� pode obter a �ltima vers�o desse arquivo na pagina do  Projeto ACBr    }
{ Componentes localizado em      http://www.sourceforge.net/projects/acbr      }
{                                                                              }
{ Esse arquivo usa a classe  SynaSer   Copyright (c)2001-2003, Lukas Gebauer   }
{  Project : Ararat Synapse     (Found at URL: http://www.ararat.cz/synapse/)  }
{                                                                              }
{  Esta biblioteca � software livre; voc� pode redistribu�-la e/ou modific�-la }
{ sob os termos da Licen�a P�blica Geral Menor do GNU conforme publicada pela  }
{ Free Software Foundation; tanto a vers�o 2.1 da Licen�a, ou (a seu crit�rio) }
{ qualquer vers�o posterior.                                                   }
{                                                                              }
{  Esta biblioteca � distribu�da na expectativa de que seja �til, por�m, SEM   }
{ NENHUMA GARANTIA; nem mesmo a garantia impl�cita de COMERCIABILIDADE OU      }
{ ADEQUA��O A UMA FINALIDADE ESPEC�FICA. Consulte a Licen�a P�blica Geral Menor}
{ do GNU para mais detalhes. (Arquivo LICEN�A.TXT ou LICENSE.TXT)              }
{                                                                              }
{  Voc� deve ter recebido uma c�pia da Licen�a P�blica Geral Menor do GNU junto}
{ com esta biblioteca; se n�o, escreva para a Free Software Foundation, Inc.,  }
{ no endere�o 59 Temple Street, Suite 330, Boston, MA 02111-1307 USA.          }
{ Voc� tamb�m pode obter uma copia da licen�a em:                              }
{ http://www.opensource.org/licenses/lgpl-license.php                          }
{                                                                              }
{ Daniel Sim�es de Almeida  -  daniel@djsystem.com.br  -  www.djsystem.com.br  }
{              Pra�a Anita Costa, 34 - Tatu� - SP - 18270-410                  }
{                                                                              }
{******************************************************************************}

{******************************************************************************
|* Historico
|*
|* 27/10/2011: Primeira Versao
|*    Isaque Pinheiro e Daniel Simoes de Almeida
|*    Cria�ao do componente ACBrDownload, que implementa de fazer download de
|*    arquivos via http e ftp, com recurso de pausa e continua��o do download.
******************************************************************************}

{$I ACBr.inc}

unit ACBrDownloadClass;

interface

uses
  Classes, SysUtils, Forms,
  ACBrUtil, ACBrBase,
  httpsend, ftpsend, blcksock, synautil;

type
  TMemory = pointer;

  TDownloadStatus = (stNone, stStop, stDownload, stPause, stResume, stRedirect);

  TACBrHookMonitor = procedure(Sender: TObject; const BytesToDownload: Integer;
     const BytesDownloaded: Integer; const AverageSpeed: Double;
     const Hour: Word; const Min: Word; const Sec: Word) of object;

  TACBrHookStatus = procedure(Sender: TObject; Reason: THookSocketReason;
     const BytesToDownload: Integer; const BytesDownloaded: Integer) of object;
  TACBrDownloadStatus = procedure(Sender: TObject;
     const DownloadStatus: TDownloadStatus) of object;

  TACBrBeforeDownload = procedure(Sender: TObject) of object;
  TACBrAfterDownload = procedure(Sender: TObject) of object;

  TACBrFTP = class(TPersistent)
  private
    fFtpHost: string;
    fFtpPort: string;
    fFtpUser: string;
    fFtpPass: string;
  public
    constructor Create;

  published
    property FtpHost: string read fFtpHost write fFtpHost;
    property FtpPort: string read fFtpPort write fFtpPort;
    property FtpUser: string read fFtpUser write fFtpUser;
    property FtpPass: string read fFtpPass write fFtpPass;
  end;

  TACBrProxy = class(TPersistent)
  private
    fProxyHost: string;
    fProxyPort: string;
    fProxyUser: string;
    fProxyPass: string;
  public
    constructor Create;

  published
    property ProxyHost: string read fProxyHost write fProxyHost;
    property ProxyPort: string read fProxyPort write fProxyPort;
    property ProxyUser: string read fProxyUser write fProxyUser;
    property ProxyPass: string read fProxyPass write fProxyPass;
  end;

  TACBrDownloadClass = class(TPersistent)
  private
    fBytesToDownload: Integer;
    fStartTime: TDateTime;
    fAverageSpeed: Double;
    fSizeRecvBuffer: Integer;
    fDownloadDest: string;

    fOnHookStatus: TACBrHookStatus;
    fOnHookMonitor: TACBrHookMonitor;
    fOnDownloadStatus: TACBrDownloadStatus;

    procedure SyncDocToFile;
    procedure SetSizeRecvBuffer(const Value: Integer);
    procedure SetDownloadStatus(const Value: TDownloadStatus);
  protected
    fOwner: TComponent;
    fProxy: TACBrProxy;
    fSock: TTCPBlockSocket;
    fDocument: TMemoryStream;

    fFileStream: TFileStream;
    fDownloadStatus: TDownloadStatus;
    fBytesDownloaded: Integer;
    fFilePart: String;
    fDownloadSize: Integer;
    fResultCode: Integer;
    fDownloadUrl: string;
    fDownloadNomeArq :string;
    fBytesResumed: Integer;

    fOnBeforeDownload: TACBrBeforeDownload;
    fOnAfterDownload: TACBrAfterDownload;

    procedure DoHookStatus(Sender: TObject; Reason: THookSocketReason;
      const Value: String); virtual;
    procedure DoHookMonitor(Sender: TObject; Writing: Boolean;
      const Buffer: TMemory; Len: Integer); virtual;
    procedure OpenCreateFile;
  public
    constructor Create(AOwner: TComponent);
    destructor Destroy; override;

    procedure StartDownload; virtual;
  published
    property DownloadDest: string read fDownloadDest write fDownloadDest;
    property DownloadUrl: string read fDownloadUrl write fDownloadUrl;
    property DownloadNomeArq : string read fDownloadNomeArq write fDownloadNomeArq;
    property DownloadStatus: TDownloadStatus read fDownloadStatus write SetDownloadStatus;
    property SizeRecvBuffer: Integer read fSizeRecvBuffer write SetSizeRecvBuffer;
    property Proxy: TACBrProxy read fProxy write fProxy;
    // Eventos
    property OnBeforeDownload: TACBrBeforeDownload read fOnBeforeDownload write fOnBeforeDownload;
    property OnAfterDownload: TACBrAfterDownload read fOnAfterDownload write fOnAfterDownload;
    property OnHookStatus: TACBrHookStatus read fOnHookStatus write fOnHookStatus;
    property OnHookMonitor: TACBrHookMonitor read fOnHookMonitor write fOnHookMonitor;
    property OnDownloadStatus: TACBrDownloadStatus read fOnDownloadStatus write fOnDownloadStatus;
  end;

implementation

uses ACBrDownload;

{ TACBrDownloadClass }

constructor TACBrDownloadClass.Create(AOwner: TComponent);
begin
   if not (AOwner is TACBrDownload) then
      raise Exception.Create('Essa Classe deve ser instanciada por TACBrDownload');

   fOwner := AOwner;

   fFileStream := nil;

   fBytesDownloaded := 0;
   fBytesToDownload := 0;
   fBytesResumed    := 0;
   fStartTime       := 0;
   fAverageSpeed    := 0;
   fDownloadSize    := 0;
   fFilePart        := '';

   // Propriedades
   fProxy           := TACBrDownload(fOwner).Proxy;
   fDownloadDest    := TACBrDownload(fOwner).DownloadDest;
   fDownloadUrl     := TACBrDownload(fOwner).DownloadUrl;
   fDownloadNomeArq := TACBrDownload(fOwner).DownloadNomeArq;
   fDownloadStatus  := TACBrDownload(fOwner).DownloadStatus;
   if Assigned(fSock) then
      SizeRecvBuffer := TACBrDownload(fOwner).SizeRecvBuffer;
   // Eventos
   fOnBeforeDownload := TACBrDownload(fOwner).OnBeforeDownload;
   fOnAfterDownload  := TACBrDownload(fOwner).OnAfterDownload;
   fOnHookStatus     := TACBrDownload(fOwner).OnHookStatus;
   fOnHookMonitor    := TACBrDownload(fOwner).OnHookMonitor;
   fOnDownloadStatus := TACBrDownload(fOwner).OnDownloadStatus;
end;

destructor TACBrDownloadClass.Destroy;
begin
   if Assigned(fSock) then
   begin
      fSock.OnStatus  := nil;
      fSock.OnMonitor := nil;
      fSock := nil;
   end;
   if Assigned(fDocument) then
      fDocument := nil;

   if Assigned(fProxy) then
      fProxy := nil;

   if Assigned(fOwner) then
      fOwner := nil;

   if Assigned(fFileStream) then
      fFileStream.Free;

  inherited Destroy;
end;

procedure TACBrDownloadClass.DoHookMonitor(Sender: TObject; Writing: Boolean;
  const Buffer: TMemory; Len: Integer);
var
  dTotalTime: TDateTime;
  wHour, wMin, wSec, wMSec: Word;
  nDLTime: Double;
begin
  if Writing then
     Exit;

  fBytesDownloaded := fBytesDownloaded + Len;

  if Assigned(fFileStream) then
  begin
     if fDocument.Size > (fFileStream.Size - fBytesResumed) then
        SyncDocToFile;
  end;

  dTotalTime := Now - fStartTime;
  DecodeTime(dTotalTime, wHour, wMin, wSec, wMSec);
  wSec := wSec + wMin * 60 + wHour * 3600;
  nDLTime := wSec + wMSec / 1000;
  if nDLTime > 0 then
     fAverageSpeed := (fBytesDownloaded / 1024) / nDLTime;

  if fAverageSpeed > 0 then
  begin
     wSec := Trunc(((fBytesToDownload - fBytesDownloaded) / 1024) / fAverageSpeed);
  end;
  if fDownloadStatus in [stPause, stStop] then
     fSock.CloseSocket;

  // Evento
  if Assigned(fOnHookMonitor) then
     fOnHookMonitor(Sender,
                    fBytesToDownload,
                    fBytesDownloaded,
                    fAverageSpeed,
                    wHour,
                    wMin,
                    wSec);
  Application.ProcessMessages;
end;

procedure TACBrDownloadClass.DoHookStatus(Sender: TObject; Reason: THookSocketReason;
  const Value: String);
var
  sFileName: String;
begin
  case Reason of
     HR_Connect:
     begin
        // Evento
        if Assigned(fOnHookStatus) then
           fOnHookStatus(Sender, Reason, fBytesToDownload, fBytesDownloaded);
     end;
     HR_ReadCount:
     begin
       if not (fDownloadStatus in [stNone, stResume]) then
          Exit;

       if fDownloadSize = 0  then
          Exit;

       if (fDownloadStatus = stNone) or (fStartTime = 0) then
       begin
          fStartTime    := Now;
          fAverageSpeed := 0;
       end;
       fDownloadStatus  := stDownload;
       fBytesDownloaded := fBytesResumed;
       fBytesToDownload := fBytesResumed + fDownloadSize;
       // Evento
       if Assigned(fOnHookStatus) then
          fOnHookStatus(Sender, Reason, fBytesToDownload, fBytesDownloaded);
     end;
     HR_SocketClose:
     begin
       // Codigos
       // 150 Pause e Stop FTP
       // 200 Stop HTTP ou fim de download HTTP
       // 206 Pause HTTP ou fim de download HTTP
       // 226 Fim de download FTP
       if (fResultCode = 206) or (fResultCode = 200) or
          (fResultCode = 150) then
       begin
          if Assigned(fFileStream) then
          begin
             SyncDocToFile;
             FreeAndNil(fFileStream);
          end;
       end;

       case fDownloadStatus of
         stStop:
         begin
            DeleteFile(fFilePart);
         end;
         stPause:
         begin

         end;
         stDownload, stNone:
         begin
            // S� renomeia o arquivo se finalizou o download, sen�o deixa
            // o arquivo com o nome.ext.part para tentar continuar de onde parou
            if (fResultCode = 200) or (fResultCode = 206) or (fResultCode = 226) then
            begin
               sFileName := Copy(fFilePart, 1, Length(fFilePart) -5);
               DeleteFile(sFileName);
               RenameFile(fFilePart, sFileName);
            end;
         end
       end;
       // Evento
       if Assigned(fOnHookStatus) then
          fOnHookStatus(Sender, Reason, fBytesToDownload, fBytesDownloaded);
     end;
  end;
end;

procedure TACBrDownloadClass.SetSizeRecvBuffer(const Value: Integer);
begin
  fSizeRecvBuffer := Value;
  if Assigned(fSock) then
     fSock.SizeRecvBuffer := Value;
end;

procedure TACBrDownloadClass.SetDownloadStatus(const Value: TDownloadStatus);
begin
  fDownloadStatus := Value;
  // Evento
  if Assigned(fOnDownloadStatus) then
     fOnDownloadStatus(Self, Value);
end;

procedure TACBrDownloadClass.StartDownload;
begin

end;

procedure TACBrDownloadClass.OpenCreateFile;
begin
  fFilePart := PathWithDelim(fDownloadDest) + fFilePart;
  if FileExists(fFilePart) then
  begin
    fDownloadStatus := stResume;
    fFileStream := TFileStream.Create(fFilePart, fmOpenReadWrite or fmShareExclusive);
    fFileStream.Seek(0, soFromEnd);
    fBytesResumed := fFileStream.Size;
  end
  else
  begin
    fDownloadStatus := stNone;
    fFileStream := TFileStream.Create(fFilePart, fmCreate or fmShareExclusive);
    fFileStream.Seek(0, soFromBeginning);
    fBytesResumed := 0;
  end;
end;

procedure TACBrDownloadClass.SyncDocToFile;
var
  iDiff: Integer;
  iDocSize : Integer;
begin
   try
     iDocSize := fDocument.Size;
     if iDocSize = 0 then
        Exit;

     iDiff := iDocSize - (fFileStream.Size-fBytesResumed);
     if iDiff > 0 then
     begin
        fDocument.Seek(-iDiff, soFromEnd);
        fFileStream.CopyFrom(fDocument, iDiff );
     end;
   finally
     fDocument.Seek(0, soFromEnd);
   end;
end;

{ TACBrProxy }

constructor TACBrProxy.Create;
begin
   fProxyHost := '';
   fProxyPort := '';
   fProxyUser := '';
   fProxyPass := '';
end;

{ TACBrFTP }

constructor TACBrFTP.Create;
begin
   fFtpHost := '';
   fFtpPort := '';
   fFtpUser := '';
   fFtpPass := '';
end;

end.
