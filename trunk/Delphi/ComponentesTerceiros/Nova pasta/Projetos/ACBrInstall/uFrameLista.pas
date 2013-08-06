{******************************************************************************}
{ Projeto: Componentes ACBr                                                    }
{  Biblioteca multiplataforma de componentes Delphi para intera��o com equipa- }
{ mentos de Automa��o Comercial utilizados no Brasil                           }
{                                                                              }
{ Direitos Autorais Reservados (c) 2009   Isaque Pinheiro                      }
{                                                                              }
{ Colaboradores nesse arquivo:                                                 }
{                                                                              }
{  Voc� pode obter a �ltima vers�o desse arquivo na pagina do  Projeto ACBr    }
{ Componentes localizado em      http://www.sourceforge.net/projects/acbr      }
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
|* 29/03/2012: Isaque Pinheiro / R�gys Borges da Silveira
|*  - Cria��o e distribui��o da Primeira Versao
*******************************************************************************}
unit uFrameLista;

interface

uses
  Generics.Collections,
  
  Windows, Messages, SysUtils, Variants, Classes, Graphics, Controls, Forms, 
  Dialogs, Buttons, ExtCtrls, StdCtrls;

type
  TPacotes = TList<TCheckBox>;

  TframePacotes = class(TFrame)
    pnlBotoesPacotes: TPanel;
    btnPacotesDesmarcarTodos: TSpeedButton;
    btnPacotesMarcarTodos: TSpeedButton;
    Label1: TLabel;
    Label2: TLabel;
    Label3: TLabel;
    Label4: TLabel;
    synapse_dpk: TCheckBox;
    ACBrComum_dpk: TCheckBox;
    ACBrDiversos_dpk: TCheckBox;
    ACBrSerial_dpk: TCheckBox;
    ACBrTCP_dpk: TCheckBox;
    ACBr_TEFD_dpk: TCheckBox;
    ACBr_Boleto_dpk: TCheckBox;
    ACBr_Sintegra_dpk: TCheckBox;
    ACBr_SPED_dpk: TCheckBox;
    ACBr_PAF_dpk: TCheckBox;
    ACBrOpenSSL_dpk: TCheckBox;
    ACBrCapicom_dpk: TCheckBox;
    PCN2_dpk: TCheckBox;
    ACBr_NFe2_dpk: TCheckBox;
    ACBr_CTe_dpk: TCheckBox;
    Label5: TLabel;
    ACBrNFeDanfeFR_dpk: TCheckBox;
    ACBr_BoletoFC_FR_dpk: TCheckBox;
    Label6: TLabel;
    ACBrCTeDacteFR_dpk: TCheckBox;
    Label7: TLabel;
    ACBrNFeDanfeQR_dpk: TCheckBox;
    ACBrNFeDanfeRL_dpk: TCheckBox;
    ACBrNFeDanfeRV_dpk: TCheckBox;
    ACBrCTeDacteQR_dpk: TCheckBox;
    ACBr_BoletoFC_Fortes_dpk: TCheckBox;
    ACBr_BoletoFC_Quick_dpk: TCheckBox;
    ACBrNFeDanfeRVCodeBase_dpk: TCheckBox;
    Label8: TLabel;
    ACBr_NFSe_dpk: TCheckBox;
    Label9: TLabel;
    Label10: TLabel;
    Label11: TLabel;
    Label12: TLabel;
    Label13: TLabel;
    Label14: TLabel;
    Label15: TLabel;
    Label16: TLabel;
    Label17: TLabel;
    Label18: TLabel;
    ACBr_MDFe_dpk: TCheckBox;
    ACBr_LFD_dpk: TCheckBox;
    procedure btnPacotesMarcarTodosClick(Sender: TObject);
    procedure btnPacotesDesmarcarTodosClick(Sender: TObject);
    procedure VerificarCheckboxes(Sender: TObject);
  private
    FPacotes: TPacotes;
    FUtilizarBotoesMarcar: Boolean;
  public
    constructor Create(AOwner: TComponent); override;
    destructor Destroy; override;

    function IsPacoteNF2(const ANomePacote: String): Boolean;

    property Pacotes: TPacotes read FPacotes write FPacotes;
  end;

implementation

uses
  StrUtils;

{$R *.dfm}

constructor TframePacotes.Create(AOwner: TComponent);
var
  I: Integer;
begin
  inherited;

  // variavel para controle do verificar checkboxes
  // utilizada para evitar estouro de pilha por conta da redund�ncia
  // e tamb�m para que pacotes dependentes n�o atrapalhem a rotina
  FUtilizarBotoesMarcar := False;

  // lista de pacotes (checkboxes) disponiveis
  FPacotes := TPacotes.Create;

  // popular a lista de pacotes com os pacotes dispon�veis
  // colocar todos os checkboxes dispon�veis na lista
  FPacotes.Clear;
  for I := 0 to Self.ComponentCount - 1 do
  begin
    if Self.Components[I] is TCheckBox then
    begin
      if TCheckBox(Self.Components[I]).Tag = 0 then
        FPacotes.Add(TCheckBox(Self.Components[I]));
    end;
  end;
end;

destructor TframePacotes.Destroy;
begin
  FreeAndNil(FPacotes);

  inherited;
end;

function TframePacotes.IsPacoteNF2(const ANomePacote: String): Boolean;
const
  PACOTES_NF2: array [0..8] of String =
    ('PCN2.dpk', 'ACBr_NFe2.dpk', 'ACBr_CTe.dpk', 'ACBr_NFSe.dpk', 'ACBr_MDFe.dpk',
     'ACBrNFeDanfeFRpkg.dpk', 'ACBrCTeDacteFRpkg.dpk', 'ACBrNFeDanfeRV.dpk',
     'ACBrNFeDanfeRVCodeBase.dpk');
begin
  Result := MatchText(ANomePacote, PACOTES_NF2);
end;

// bot�o para marcar todos os checkboxes
procedure TframePacotes.btnPacotesMarcarTodosClick(Sender: TObject);
var
  I: Integer;
begin
  FUtilizarBotoesMarcar := True;
  try
    for I := 0 to Self.ComponentCount -1 do
    begin
      if Self.Components[I] is TCheckBox then
      begin
        if TCheckBox(Self.Components[I]).Enabled then
          TCheckBox(Self.Components[I]).Checked := True;
      end;
    end;
  finally
    FUtilizarBotoesMarcar := False;
    VerificarCheckboxes(Sender);
  end;
end;

// bot�o para desmarcar todos os checkboxes
procedure TframePacotes.btnPacotesDesmarcarTodosClick(Sender: TObject);
var
  I: Integer;
begin
  FUtilizarBotoesMarcar := True;
  try
    for I := 0 to Self.ComponentCount -1 do
    begin
      if Self.Components[I] is TCheckBox then
      begin
        if TCheckBox(Self.Components[I]).Enabled then
          TCheckBox(Self.Components[I]).Checked := False;
      end;
    end;
  finally
    FUtilizarBotoesMarcar := False;
    VerificarCheckboxes(Sender);
  end;
end;

// rotina de verifica��o de depend�ncia e marca��o dos pacotes base
procedure TframePacotes.VerificarCheckboxes(Sender: TObject);
begin
  // pacotes base n�o podem ser desmarcados
  // instala��o m�nima do ACBr
  synapse_dpk.Checked      := True;
  ACBrComum_dpk.Checked    := True;
  ACBrDiversos_dpk.Checked := True;

  if not FUtilizarBotoesMarcar then
  begin
    // dependencias da NFe e CTe
    if (ACBr_NFe2_dpk.Checked) or (ACBr_CTe_dpk.Checked) or (ACBr_NFSe_dpk.Checked) or (ACBr_MDFe_dpk.Checked) then
    begin
      PCN2_dpk.Checked        := True;
      ACBrCapicom_dpk.Checked := True;
      ACBrOpenSSL_dpk.Checked := True;
    end;

    // dependencias do ACBrTEFD
    if not(ACBrTCP_dpk.Checked) and ACBr_TEFD_dpk.Checked  then
      ACBrTCP_dpk.Checked := True;

    // Dependencias do ACBrPaf
    if not(ACBr_SPED_dpk.Checked) and ACBr_PAF_dpk.Checked then
      ACBr_SPED_dpk.Checked := True;

    // Dependencia
    ACBrNFeDanfeRVCodeBase_dpk.Checked := ACBrNFeDanfeRV_dpk.Checked;
  end;
end;

end.

{
  --------------------------------------
  -- LEMBRETE N�O APAGAR POR ENQUANTO --
  --------------------------------------
  synapse.dpk
  ACBrComum.dpk
  ACBrDiversos.dpk

  ACBrSerial.dpk
  ACBrTCP.dpk
  ACBr_TEFD.dpk

  ACBr_Boleto.dpk
  ACBr_PAF.dpk
  ACBr_Sintegra.dpk
  ACBr_SPED.dpk

  ACBrCapicom.dpk
  ACBrOpenSSL.dpk
  ACBrNFe2\PCN2.dpk
  ACBrNFe2\ACBr_NFe2.dpk
  ACBrNFe2\ACBr_CTe.dpk

}
