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

unit ACBrHTTPDownload;

interface

uses
  Classes, SysUtils,
  ACBrUtil, ACBrBase, ACBrDownloadClass,
  httpsend, ftpsend, blcksock, synautil;

type
  TACBrHTTPDownload = class(TACBrDownloadClass)
  private
    fHTTPSend: THTTPSend;

  protected
    procedure DoHookStatus(Sender: TObject; Reason: THookSocketReason;
      const Value: String); override;
  public
    constructor Create(AOwner: TComponent);
    destructor Destroy; override;

    procedure StartDownload; override;
  published
  end;

implementation

{ TACBrHTTPDownload }

constructor TACBrHTTPDownload.Create(AOwner: TComponent);
begin
  fHTTPSend                := THTTPSend.Create;
  fHTTPSend.Sock.OnStatus  := DoHookStatus;
  fHTTPSend.Sock.OnMonitor := DoHookMonitor;

  fDocument := fHTTPSend.Document;
  fSock     := fHTTPSend.Sock;
  inherited Create(AOwner);
end;

destructor TACBrHTTPDownload.Destroy;
begin
  fHTTPSend.Sock.OnStatus  := nil;
  fHTTPSend.Sock.OnMonitor := nil;

  fHTTPSend.Free;
  inherited Destroy;
end;

procedure TACBrHTTPDownload.DoHookStatus(Sender: TObject;
  Reason: THookSocketReason; const Value: String);
var
  sLineHeader, sLocation: String;
  iCount: Integer;
begin
  fResultCode   := fHTTPSend.ResultCode;
  fDownloadSize := fHTTPSend.DownloadSize;
  case Reason of
     HR_SocketClose:
     begin
       if fResultCode = 500  then // Inicializando
          Exit;

       if fResultCode = 302 then  // Redirecionando
       begin
          fDownloadStatus := stRedirect;
          sLocation := '';
          iCount    := 0;

          while (sLocation = '') and (iCount < fHTTPSend.Headers.Count) do
          begin
            sLineHeader := fHTTPSend.Headers[iCount];
            if Pos('Location: ', sLineHeader) = 1 then
               sLocation := Copy(sLineHeader, 11, Length(sLineHeader));

            Inc(iCount);
          end;
          FreeAndNil(fFileStream);
          fDownloadUrl := sLocation;
          // Inicia Download
          StartDownload;

          Exit;
       end;
     end;
  end;
  inherited;
end;

procedure TACBrHTTPDownload.StartDownload;
var
  iCount, iPos: Integer;
  sHeaderLine: string;
  Prot, User, Pass, Host, Port, Path, Para : String;
begin
  fFilePart:=fDownloadNomeArq;
  if (fFilePart = '') and (fDownloadStatus <> stRedirect) then
  begin
     Prot := '';
     User := '';
     Pass := '';
     Host := '';
     Port := '';
     Path := '';
     Para := '';
     ParseURL(fDownloadUrl, Prot, User, Pass, Host, Port, Path, Para);

     // Defini��o do Proxy
     fHTTPSend.ProxyHost := fProxy.ProxyHost;
     fHTTPSend.ProxyPort := fProxy.ProxyPort;
     fHTTPSend.ProxyUser := fProxy.ProxyUser;
     fHTTPSend.ProxyPass := fProxy.ProxyPass;

     // ReplaceString foi chamado aqui porque no Delphi n�o reconhece barra "/"
     // como o Lazarus que reconhece os dois.
     fFilePart := ExtractFileName(ReplaceString(Path, '/', '\'));
     if fFilePart = '' then
     begin
        fHTTPSend.Clear;
        fHTTPSend.RangeEnd := 1024; // Read 1k only
        fHTTPSend.HTTPMethod('GET', fDownloadUrl);

        iCount := 0;
        while (fFilePart = '') and (iCount < fHTTPSend.Headers.Count) do
        begin
          sHeaderLine := fHTTPSend.Headers[iCount];
          iPos := Pos('filename=', sHeaderLine);
          if iPos > 0 then
          begin
             fFilePart := Copy( sHeaderLine, iPos+9, Length(sHeaderLine) );
             if fFilePart[1] = '"' then
                fFilePart := Copy(fFilePart, 2, Length(fFilePart) -2);
          end;
          Inc(iCount);
        end;
     end;
     if fFilePart <> '' then
        fFilePart := fFilePart + '.part';
  end;
  if  (pos('.part',fFilePart)<=0) and (fDownloadStatus <> stRedirect) and (fFilePart<>'') then
       fFilePart := fFilePart + '.part';


  if fFilePart = '' then
     raise Exception.Create('Favor informar o nome do arquivo para download!');

  // Abrir ou Criar o arquivo de download
  OpenCreateFile;

  fHTTPSend.Clear;
  fHTTPSend.RangeStart := fBytesResumed;

  // Evento
  if Assigned(fOnBeforeDownload) then
     fOnBeforeDownload(Self);

  fHTTPSend.HTTPMethod('GET', fDownloadUrl);

  // Evento
  if Assigned(fOnAfterDownload) then
     fOnAfterDownload(Self);
end;

end.
