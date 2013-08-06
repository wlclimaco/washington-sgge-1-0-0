{******************************************************************************}
{ Projeto: Componente ACBrNFe                                                  }
{  Biblioteca multiplataforma de componentes Delphi para emiss�o de Nota Fiscal}
{ eletr�nica - NFe - http://www.nfe.fazenda.gov.br                          }
{                                                                              }
{ Direitos Autorais Reservados (c) 2008 Wemerson Souto                         }
{                                       Daniel Simoes de Almeida               }
{                                       Andr� Ferreira de Moraes               }
{                                                                              }
{ Colaboradores nesse arquivo:                                                 }
{                                                                              }
{  Voc� pode obter a �ltima vers�o desse arquivo na pagina do Projeto ACBr     }
{ Componentes localizado em http://www.sourceforge.net/projects/acbr           }
{                                                                              }
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
|* 16/12/2008: Wemerson Souto
|*  - Doa��o do componente para o Projeto ACBr
|* 20/08/2009: Jo�o Paulo
|*  - Doa��o units para gera��o do Danfe via c�digo usando Rave
******************************************************************************}
{$I ACBr.inc}
unit ACBrNFeDANFERaveCB;

interface

uses Forms, SysUtils, Classes,
  RpDefine, RpDevice, RVClass, RVProj, RVCsBars, RVCsStd, RVCsData,
  RvDirectDataView, RVDataField,
  {$IFNDEF COMPILER16} JPEG, {$ELSE} Vcl.Imaging.jpeg, {$ENDIF}
  ACBrNFeDANFEClass, ACBrDANFeCBRave, pcnNFe, pcnConversao;

type
  TFont=(ftTimes,ftCourier);

  TACBrNFeDANFERaveCB = class( TACBrNFeDANFEClass )
   private
     FTamanhoCampoCodigo: integer;
     FTamanhoFonte_ANTT: integer;
     FFonte : TFont;
     FEspessuraBorda: Integer;
    FMostrarSetup: boolean;

     function SeSenaoJPEG(ACondicao: Boolean; ATrue, AFalse: TJPEGImage): TJPEGImage;
   public
    constructor Create(AOwner: TComponent); override;
    destructor Destroy; override;
    procedure ImprimirDANFE(NFE : TNFe = nil); override ;
    procedure ImprimirDANFEPDF(NFE : TNFe = nil); override ;
    procedure ImprimirEVENTO(NFE : TNFe = nil); override ;
    procedure ImprimirEVENTOPDF(NFE : TNFe = nil); override ;
  published
     property TamanhoCampoCodigo:integer read FTamanhoCampoCodigo write FTamanhoCampoCodigo;
     property TamanhoFonte_ANTT:integer read FTamanhoFonte_ANTT write FTamanhoFonte_ANTT;
     property Fonte:TFont read FFonte write FFonte;
     property EspessuraBorda:Integer read FEspessuraBorda write FEspessuraBorda;
     property MostrarSetup: boolean read FMostrarSetup write FMostrarSetup;
  end;

implementation

uses ACBrNFe, ACBrNFeUtil, ACBrUtil, ACBrDFeUtil, StrUtils, Dialogs;

constructor TACBrNFeDANFERaveCB.Create(AOwner: TComponent);
begin
  inherited create( AOwner );

  FTamanhoCampoCodigo:=0;
  FTamanhoFonte_ANTT:=10;
  FEspessuraBorda:=2;
  FMostrarSetup:=False;
end;

destructor TACBrNFeDANFERaveCB.Destroy;
begin
  inherited Destroy ;
end;


procedure TACBrNFeDANFERaveCB.ImprimirDANFE(NFE : TNFe = nil);
var
 LogoMarcaEmpresa:TJPEGImage;
 ExisteLogoMarca: Boolean;
 vStringStream: TStringStream;
begin
    ExisteLogoMarca:=True;
    LogoMarcaEmpresa:=TJPEGImage.Create;
    try
      if DFeUtil.NaoEstaVazio(Logo) then
       begin
         if FileExists(Logo) then
            LogoMarcaEmpresa.LoadFromFile(Logo)
         else
         begin
            vStringStream:= TStringStream.Create(Logo);
            try
               LogoMarcaEmpresa.LoadFromStream(vStringStream);
            finally
               vStringStream.Free;
            end;
         end;
       end
       else
        ExisteLogoMarca:=False;

      ImprimirDANFeRave(TACBrNFe(ACBrNFe),
                       Site,
                       Email,
                       Fax,
                       Sistema,
                       Usuario,
                       ProtocoloNFe,
                       SeSenaoJPEG(ExisteLogoMarca,LogoMarcaEmpresa,nil),
                       DFeUtil.SeSenao((TipoDANFE=tiRetrato),poPortrait,poLandScape),
                       DFeUtil.SeSenao(MostrarPreview,tsPreview,tsPrint),
                       MostrarStatus,
                       MostrarSetup,
                       NumCopias,
                       Impressora,
                       '',
                       MargemInferior*10,
                       MargemSuperior*10,
                       MargemEsquerda*10,
                       MargemDireita*10,
                       CasasDecimais._qCom,
                       CasasDecimais._vUnCom,
                       CasasDecimais._Mask_qCom,
                       CasasDecimais._Mask_vUnCom,
                       TamanhoCampoCodigo,
                       TamanhoFonte_DemaisCampos,
                       TamanhoFonte_ANTT,
                       ProdutosPorPagina,
                       EspessuraBorda,
                       ExibirResumoCanhoto,
                       ExibirResumoCanhoto_Texto,
                       ImprimirDescPorc,
                       ImprimirTotalLiquido,
                       ImprimirDetalhamentoEspecifico,
                       FormularioContinuo,
                       ExpandirLogoMarca,
                       NFeCancelada,
                       NFe);
    finally
      LogoMarcaEmpresa.Free;
    end;
end;

procedure TACBrNFeDANFERaveCB.ImprimirDANFEPDF(NFE : TNFe = nil);
var
 LogoMarcaEmpresa:TJPEGImage;
 ExisteLogoMarca: Boolean;
 NomeArq : String;
 vStringStream: TStringStream;
begin
    ExisteLogoMarca:=True;
    LogoMarcaEmpresa:=TJPEGImage.Create;
    try
      if DFeUtil.NaoEstaVazio(Logo) then
       begin
         if FileExists(Logo) then
            LogoMarcaEmpresa.LoadFromFile(Logo)
         else
         begin
            vStringStream:= TStringStream.Create(Logo);
            try
               LogoMarcaEmpresa.LoadFromStream(vStringStream);
            finally
               vStringStream.Free;
            end;
         end;
       end
       else
         ExisteLogoMarca:=False;

      if NFE = nil then
         NomeArq := StringReplace(TACBrNFe(ACBrNFe).NotasFiscais.Items[0].NFe.infNFe.ID,'NFe', '', [rfIgnoreCase])
      else
         NomeArq := StringReplace(NFE.infNFe.ID,'NFe', '', [rfIgnoreCase]);

      NomeArq := PathWithDelim(Self.PathPDF)+NomeArq+'.pdf';

      ImprimirDANFeRave(TACBrNFe(ACBrNFe),
                       Site,
                       Email,
                       Fax,
                       Sistema,
                       Usuario,
                       ProtocoloNFe,
                       SeSenaoJPEG(ExisteLogoMarca,LogoMarcaEmpresa,nil),
                       DFeUtil.SeSenao((TipoDANFE=tiRetrato),poPortrait,poLandScape),
                       tsPDF,
                       MostrarStatus,
                       MostrarSetup,
                       NumCopias,
                       Impressora,
                       NomeArq,
                       MargemInferior*10,
                       MargemSuperior*10,
                       MargemEsquerda*10,
                       MargemDireita*10,
                       CasasDecimais._qCom,
                       CasasDecimais._vUnCom,
                       CasasDecimais._Mask_qCom,
                       CasasDecimais._Mask_vUnCom,
                       TamanhoCampoCodigo,
                       TamanhoFonte_DemaisCampos,
                       TamanhoFonte_ANTT,
                       ProdutosPorPagina,
                       EspessuraBorda,
                       ExibirResumoCanhoto,
                       ExibirResumoCanhoto_Texto,
                       ImprimirDescPorc,
                       ImprimirTotalLiquido,
                       ImprimirDetalhamentoEspecifico,
                       FormularioContinuo,
                       ExpandirLogoMarca,
                       NFeCancelada,
                       NFE);
    finally
      LogoMarcaEmpresa.Free;
    end;
end;


procedure TACBrNFeDANFERaveCB.ImprimirEVENTO(NFE: TNFe);
var
 LogoMarcaEmpresa:TJPEGImage;
 ExisteLogoMarca: Boolean;
 vStringStream: TStringStream;
begin
    ExisteLogoMarca:=True;
    LogoMarcaEmpresa:=TJPEGImage.Create;
    try
      if DFeUtil.NaoEstaVazio(Logo) then
       begin
         if FileExists(Logo) then
            LogoMarcaEmpresa.LoadFromFile(Logo)
         else
         begin
            vStringStream:= TStringStream.Create(Logo);
            try
               LogoMarcaEmpresa.LoadFromStream(vStringStream);
            finally
               vStringStream.Free;
            end;
         end;
       end
       else
        ExisteLogoMarca:=False;

      ImprimirEventoRave(TACBrNFe(ACBrNFe),
                       Site,
                       Email,
                       Fax,
                       Sistema,
                       Usuario,
                       SeSenaoJPEG(ExisteLogoMarca,LogoMarcaEmpresa,nil),
                       DFeUtil.SeSenao((TipoDANFE=tiRetrato),poPortrait,poLandScape),
                       DFeUtil.SeSenao(MostrarPreview,tsPreview,tsPrint),
                       MostrarStatus,
                       MostrarSetup,
                       NumCopias,
                       Impressora,
                       '',
                       MargemInferior*10,
                       MargemSuperior*10,
                       MargemEsquerda*10,
                       MargemDireita*10,
                       TamanhoFonte_DemaisCampos,
                       EspessuraBorda,
                       FormularioContinuo,
                       ExpandirLogoMarca,
                       NFe);
    finally
      LogoMarcaEmpresa.Free;
    end;
end;


procedure TACBrNFeDANFERaveCB.ImprimirEVENTOPDF(NFE: TNFe);
var
 LogoMarcaEmpresa:TJPEGImage;
 ExisteLogoMarca: Boolean;
 vStringStream: TStringStream;
 NomeArq : String;
begin
    ExisteLogoMarca:=True;
    LogoMarcaEmpresa:=TJPEGImage.Create;
    try
      if DFeUtil.NaoEstaVazio(Logo) then
       begin
         if FileExists(Logo) then
            LogoMarcaEmpresa.LoadFromFile(Logo)
         else
         begin
            vStringStream:= TStringStream.Create(Logo);
            try
               LogoMarcaEmpresa.LoadFromStream(vStringStream);
            finally
               vStringStream.Free;
            end;
         end;
       end
       else
        ExisteLogoMarca:=False;

      NomeArq := StringReplace(TACBrNFe(ACBrNFe).EventoNFe.Evento[0].InfEvento.id,'ID', '', [rfIgnoreCase]);
      NomeArq := PathWithDelim(Self.PathPDF)+NomeArq+'evento.pdf';

      ImprimirEventoRave(TACBrNFe(ACBrNFe),
                       Site,
                       Email,
                       Fax,
                       Sistema,
                       Usuario,
                       SeSenaoJPEG(ExisteLogoMarca,LogoMarcaEmpresa,nil),
                       DFeUtil.SeSenao((TipoDANFE=tiRetrato),poPortrait,poLandScape),
                       tsPDF,
                       MostrarStatus,
                       MostrarSetup,
                       NumCopias,
                       Impressora,
                       NomeArq,
                       MargemInferior*10,
                       MargemSuperior*10,
                       MargemEsquerda*10,
                       MargemDireita*10,
                       TamanhoFonte_DemaisCampos,
                       EspessuraBorda,
                       FormularioContinuo,
                       ExpandirLogoMarca,
                       NFe);
    finally
      LogoMarcaEmpresa.Free;
    end;
end;

function TACBrNFeDANFERaveCB.SeSenaoJPEG(ACondicao: Boolean; ATrue,
  AFalse: TJPEGImage): TJPEGImage;
begin
  Result := AFalse;
  if ACondicao then
    Result := ATrue;
end;

end.
