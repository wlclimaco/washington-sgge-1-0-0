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

unit ACBrDownload;

interface

uses
  SysUtils, Classes,
  ACBrBase, ACBrDownloadClass ;

const
   CACBrDownload_Versao = '1.0.0';

type
  TACBrProtocolo = (protNenhum, protHTTP, protFTP);

  TACBrDownload = class(TACBrComponent)
  private
    { Private declarations }
    fDown: TACBrDownloadClass;
    fProtocolo: TACBrProtocolo;
    fDownloadDest: string;
    fDownloadStatus: TDownloadStatus;
    fDownloadUrl: string;
    fDownloadNomeArq : string;
    fProxy: TACBrProxy;
    fSizeRecvBuffer: Integer;
    fFTP: TACBrFTP;

    fOnBeforeDownload: TACBrBeforeDownload;
    fOnHookStatus: TACBrHookStatus;
    fOnDownloadStatus: TACBrDownloadStatus;
    fOnAfterDownload: TACBrAfterDownload;
    fOnHookMonitor: TACBrHookMonitor;

    procedure SetDownloadDest(const Value: string);
    procedure SetDownloadStatus(const Value: TDownloadStatus);
    procedure SetDownloadUrl(const Value: string);
    procedure SetDownloadNomeArq(const Value:string); 
    procedure SetFTP(const Value: TACBrFTP);
    procedure SetProxy(const Value: TACBrProxy);
    procedure SetSizeRecvBuffer(const Value: Integer);
    procedure SetOnAfterDownload(const Value: TACBrAfterDownload);
    procedure SetOnBeforeDownload(const Value: TACBrBeforeDownload);
    procedure SetOnDownloadStatus(const Value: TACBrDownloadStatus);
    procedure SetOnHookMonitor(const Value: TACBrHookMonitor);
    procedure SetOnHookStatus(const Value: TACBrHookStatus);
  protected
    { Protected declarations }
  public
    { Public declarations }
    constructor Create(AOwner: TComponent); override;
    destructor Destroy; override;

    procedure StartDownload;

    property DownloadStatus: TDownloadStatus read fDownloadStatus write SetDownloadStatus;
  published
    { Published declarations }
    property DownloadDest: string read fDownloadDest write SetDownloadDest;
    property DownloadUrl: string read fDownloadUrl write SetDownloadUrl;
    property DownloadNomeArq : string read fDownloadNomeArq write SetDownloadNomeArq;
    property SizeRecvBuffer: Integer read fSizeRecvBuffer write SetSizeRecvBuffer;
    property Proxy: TACBrProxy read fProxy write SetProxy;
    property FTP: TACBrFTP read fFTP write SetFTP;
    property Protocolo: TACBrProtocolo read fProtocolo write fProtocolo default protNenhum;
    // Eventos
    property OnBeforeDownload: TACBrBeforeDownload read fOnBeforeDownload write SetOnBeforeDownload;
    property OnAfterDownload: TACBrAfterDownload read fOnAfterDownload write SetOnAfterDownload;
    property OnHookStatus: TACBrHookStatus read fOnHookStatus write SetOnHookStatus;
    property OnHookMonitor: TACBrHookMonitor read fOnHookMonitor write SetOnHookMonitor;
    property OnDownloadStatus: TACBrDownloadStatus read fOnDownloadStatus write SetOnDownloadStatus;
  end;

implementation

Uses ACBrHTTPDownload, ACBrFTPDownload ;

{$IFNDEF FPC}
 {$R ACBrDownload.dcr}
{$ENDIF}

{ TACBrDownload }

constructor TACBrDownload.Create(AOwner: TComponent);
begin
  inherited Create(AOwner);
  fDown  := TACBrDownloadClass.Create(Self);
  fProxy := TACBrProxy.Create;
  fFTP   := TACBrFTP.Create;

  fDownloadDest   := '';
  fDownloadUrl    := '';
  fDownloadNomeArq := '';
  fDownloadStatus := stNone;
  fProtocolo      := protNenhum;
end;

destructor TACBrDownload.Destroy;
begin
  // Esta class tem que ser liberada antes das outras.
  FreeAndNil(fDown);

  fProxy.Free;
  fFTP.Free;
  inherited Destroy;
end;

procedure TACBrDownload.SetDownloadDest(const Value: string);
begin
  fDownloadDest := Value;
  fDown.DownloadDest := Value;
end;

procedure TACBrDownload.SetDownloadStatus(const Value: TDownloadStatus);
begin
  fDownloadStatus := Value;
  fDown.DownloadStatus := Value;
end;

procedure TACBrDownload.SetDownloadUrl(const Value: string);
begin
  fDownloadUrl := Value;
  fDown.DownloadUrl := Value;
end;

procedure TACBrDownload.SetDownloadNomeArq(const Value: string);
begin
  fDownloadNomeArq := Value;
  fDown.DownloadNomeArq := Value;
end;


procedure TACBrDownload.SetFTP(const Value: TACBrFTP);
begin
  fFTP := Value;
  if fProtocolo = protFTP then
     TACBrFTPDownload(fDown).FTP := Value;
end;

procedure TACBrDownload.SetOnAfterDownload(const Value: TACBrAfterDownload);
begin
  fOnAfterDownload := Value;
  fDown.OnAfterDownload := Value;
end;

procedure TACBrDownload.SetOnBeforeDownload(const Value: TACBrBeforeDownload);
begin
  fOnBeforeDownload := Value;
  fDown.OnBeforeDownload := Value;
end;

procedure TACBrDownload.SetOnDownloadStatus(const Value: TACBrDownloadStatus);
begin
  fOnDownloadStatus := Value;
  fDown.OnDownloadStatus := Value;
end;

procedure TACBrDownload.SetOnHookMonitor(const Value: TACBrHookMonitor);
begin
  fOnHookMonitor := Value;
  fDown.OnHookMonitor := Value;
end;

procedure TACBrDownload.SetOnHookStatus(const Value: TACBrHookStatus);
begin
  fOnHookStatus := Value;
  fDown.OnHookStatus := Value;
end;

procedure TACBrDownload.SetProxy(const Value: TACBrProxy);
begin
  fProxy := Value;
  fDown.Proxy := Value;
end;

procedure TACBrDownload.SetSizeRecvBuffer(const Value: Integer);
begin
  fSizeRecvBuffer := Value;
  fDown.SizeRecvBuffer := Value;
end;

procedure TACBrDownload.StartDownload;
begin
   if not (fDown is TACBrDownloadClass) then
      raise Exception.Create('Antes de iniciar o download, defina o protocolo a ser usado HTTP ou FTP!');

   if Assigned(fDown) then
      FreeAndNil(fDown);

   case fProtocolo of
     protHTTP: fDown := TACBrHTTPDownload.Create(Self);
     protFTP : fDown := TACBrFTPDownload.Create(Self);
   end;

   fDownloadStatus := stDownload;

   if fProtocolo <> protNenhum then
      fDown.StartDownload;
end;

end.
