{******************************************************************************}
{ Projeto: Componentes ACBr                                                    }
{  Biblioteca multiplataforma de componentes Delphi para intera��o com equipa- }
{ mentos de Automa��o Comercial utilizados no Brasil                           }
{                                                                              }
{ Direitos Autorais Reservados (c) 2004 Daniel Simoes de Almeida               }
{                                                                              }
{ Colaboradores nesse arquivo:                                                 }
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
|* 28/01/2013: Primeira Versao
|*    Daniel Simoes de Almeida e Andr� Moraes
******************************************************************************}

unit ACBrNFPws ;

{$I ACBr.inc}

interface

uses
  Classes, SysUtils, ACBrSocket;

const
  CNFPws_URLNF = 'https://www.nfp.fazenda.sp.gov.br/ws/arquivonf_mod1.asmx' ;
  CNFPws_URLCF = 'https://www.nfp.fazenda.sp.gov.br/ws/arquivocf.asmx' ;

type

  EACBrNFPwsException = class ( Exception );

  TACBrNFPwsCategoriaUsuario = (nfpNenhum, nfpContribuinte, nfpContabilista,
                                nfpConsumidor) ;

  TACBrNFPwsTipoDocto = (docCupomFiscal, docNF_Mod1) ;

  { TACBrNFPws }

  TACBrNFPws = class( TACBrHTTP )
    private
      fCategoriaUsuario: TACBrNFPwsCategoriaUsuario;
      fCNPJ: String;
      fModoTeste: Boolean;
      fOnBuscaEfetuada : TNotifyEvent ;
      fSenha: String;
      fTipoDocto: TACBrNFPwsTipoDocto;
      fURL: String;
      fUsuario: String;
      procedure SetTipoDocto(AValue: TACBrNFPwsTipoDocto);
    protected
      function SoapEnvelope( const Body: String ) : String ;
      procedure DoPOST( const SoapBody: String);

    public
      constructor Create(AOwner: TComponent); override;
      Destructor Destroy ; override ;

    published
      property URL     : String read fURL     write fURL ;
      property Usuario : String read fUsuario write fUsuario ;
      property Senha   : String read fSenha   write fSenha ;
      property CNPJ    : String read fCNPJ    write fCNPJ ;
      property CategoriaUsuario : TACBrNFPwsCategoriaUsuario
         read fCategoriaUsuario write fCategoriaUsuario;
      property ModoTeste: Boolean read fModoTeste write fModoTeste ;
      property TipoDocto: TACBrNFPwsTipoDocto read fTipoDocto write SetTipoDocto ;

      property OnBuscaEfetuada : TNotifyEvent read fOnBuscaEfetuada
         write fOnBuscaEfetuada ;

      function Consultar( const Protocolo: String ): String;
      function Enviar( const NomeArquivo: String; const Observacoes: String = '' ): String;
  end ;

implementation

uses ACBrUtil, strutils, ssl_openssl ;

constructor TACBrNFPws.Create(AOwner : TComponent) ;
begin
  inherited Create(AOwner) ;

  ParseText := False;

  fOnBuscaEfetuada := nil ;
  fURL             := CNFPws_URLCF;
  fTipoDocto       := docCupomFiscal;
  fUsuario         := '';
  fSenha           := '';
  fCNPJ            := '';
  fCategoriaUsuario:= nfpNenhum;
  fModoTeste       := True;
end ;

destructor TACBrNFPws.Destroy;
begin
  inherited Destroy ;
end ;

procedure TACBrNFPws.SetTipoDocto(AValue: TACBrNFPwsTipoDocto);
begin
  fTipoDocto:=AValue;

  if fTipoDocto = docNF_Mod1 then
     URL := CNFPws_URLNF
  else
     URL := CNFPws_URLCF;
end;

function TACBrNFPws.SoapEnvelope(const Body: String): String;
begin
  Result :=
  '<?xml version="1.0" encoding="utf-8"?>' +
  '<soap12:Envelope xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" '+
    'xmlns:xsd="http://www.w3.org/2001/XMLSchema" '+
    'xmlns:soap12="http://www.w3.org/2003/05/soap-envelope">'+
    '<soap12:Header>'+
      '<Autenticacao Usuario="'+trim(Usuario)+
        '" Senha="'+trim(Senha)+
        '" CNPJ="'+trim(CNPJ)+
        '" CategoriaUsuario="'+IntToStr(Integer(CategoriaUsuario))+
        '" xmlns="https://www.nfp.sp.gov.br/ws" />'+
    '</soap12:Header>'+
    '<soap12:Body>'+
      Body+
    '</soap12:Body>'+
  '</soap12:Envelope>' ;
end;

procedure TACBrNFPws.DoPOST(const SoapBody: String);
var
  PostData: AnsiString;
begin
  PostData := SoapEnvelope( SoapBody ) ;

  HTTPSend.Clear;
  HTTPSend.Protocol := '1.1';
  HTTPSend.MimeType := 'application/soap+xml; charset=utf-8';

  HTTPSend.Document.Write(Pointer(PostData)^,Length(PostData));
  HTTPPost( URL );
end;


function TACBrNFPws.Consultar(const Protocolo: String): String;
begin
  DoPOST( '<Consultar xmlns="https://www.nfp.sp.gov.br/ws">'+
          '<Protocolo>'+Protocolo+'</Protocolo>'+
          '</Consultar>' ) ;

  Result := LerTagXML( RespHTTP.Text, 'ConsultarResult' ) ;
end;

function TACBrNFPws.Enviar(const NomeArquivo: String; const Observacoes: String
  ): String;
Var
  SL : TStringList ;
begin
  if not FileExists( NomeArquivo ) then
    raise EACBrNFPwsException.Create( ACBrStr('Arquivo ' + NomeArquivo + 'n�o encontrado') );

  SL := TStringList.Create;
  try
    SL.LoadFromFile( NomeArquivo );

    DoPOST( '<Enviar xmlns="https://www.nfp.sp.gov.br/ws">'+
              '<NomeArquivo>' + ExtractFileName(NomeArquivo) + '</NomeArquivo>'+
              '<EnvioNormal>'+IfThen(ModoTeste,'false','true')+'</EnvioNormal>'+
              '<ConteudoArquivo>' +
                ACBrUtil.ParseText( SL.Text, False, False )+
              '</ConteudoArquivo>'+
              '<Observacoes>'+Trim(Observacoes)+'</Observacoes>'+
            '</Enviar>' );

  finally
    SL.Free;
  end ;

  Result := LerTagXML( RespHTTP.Text, 'EnviarResult' ) ;
end;

end.

