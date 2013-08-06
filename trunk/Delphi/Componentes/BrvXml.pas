unit BrvXml;

interface

uses
  Windows, Messages, SysUtils, Classes, Graphics, Controls, Forms, Dialogs,
  ComCtrls, DbClient, Db, ACBrNFe, ACBrCTe, pcnConversao;

type
  TBrvXML = class(TComponent)
  private
    { Private declarations }
    gNoPagDan  : Integer;
    gNoIteDan  : Integer;
    gQtIteDan  : Integer;
    gDsXmlOri  : TStrings;
    gDsXmlFin  : TStrings;
    gCdsCorCTE : TClientDataSet;
    gCdsInfCte : TClientDataSet;
    gCdsPreCte : TClientDataSet;
    gCdsCarCte : TClientDataSet;
    gCdsCorNFE : TClientDataSet;
    gCdsFatura : TClientDataSet;
    gCdsProdut : TClientDataSet;
    gSnGerBan  : Boolean;
    ACBrCTe: TACBrCTe;
    ACBrNFe: TACBrNFe;
    gTpXML     : String;
    procedure SetXMLOrigem(Value: TStrings);
    procedure SetXMLFinal(Value: TStrings);
    procedure CriarTabelaTemporaria;
    procedure CriarTabelaTemporariaCorpoNFe;
    procedure CriarTabelaTemporariaFatura;
    procedure CriarTabelaTemporariaProduto;
    function  DeterminarTipoXML(pDsXml: String): String;
    procedure CriarTabelaTemporariaCorpoCTE;
    procedure CriarTabelaTemporariaChavesCTE;
    procedure CriarTabelaTemporariaPrestacoesCTE;
    procedure CriarTabelaTemporariaCargasCTE;
    procedure CriarTabelaTemporariaXMLNFE;
    procedure CriarTabelaTemporariaXMLCTE;
  protected
    { Protected declarations }
  public
    { Public declarations }
    constructor Create(AOwner : TComponent); override;
    destructor  Destroy;                     override;
    procedure   ProcessarXml;
  published
    { Published declarations }
    property BrXMLOriginal   : TStrings       read gDsXmlOri  write SetXMLOrigem;
    property BrXMLFinal      : TStrings       read gDsXmlFin  write SetXMLFinal;
    property BrCdsNfeDet     : TClientDataSet read gCdsCorNFE write gCdsCorNFE;
    property BrCdsNfeFat     : TClientDataSet read gCdsFatura write gCdsFatura;
    property BrCdsNfePro     : TClientDataSet read gCdsProdut write gCdsProdut;
    property BrCdsCteDet     : TClientDataSet read gCdsCorCTE write gCdsCorCTE;
    property BrCdsCteNFs     : TClientDataSet read gCdsInfCte write gCdsInfCte;
    property BrCdsCtePre     : TClientDataSet read gCdsPreCte write gCdsPreCte;
    property BrCdsCteCar     : TClientDataSet read gCdsCarCte write gCdsCarCte;
    property BrGerarBanco    : Boolean        read gSnGerBan  write gSnGerBan;
    property BrQtdePagDanfe  : Integer        read gNoPagDan  write gNoPagDan;
  end;

procedure Register;

implementation

procedure Register;
begin
      RegisterComponents('Bravo Exporta', [TBrvXML]);
end;

constructor TBrvXML.Create(AOwner: TComponent);
begin
      inherited;
      gDsXmlOri := TStringList.Create;
      gDsXmlFin := TStringList.Create;
end;

destructor TBrvXML.Destroy;
begin
      gDsXmlOri.Destroy;
      gDsXmlFin.Destroy;
      inherited;
end;

procedure TBrvXML.CriarTabelaTemporariaXMLCTE;
var
    Itens : Integer;
    Count : Integer;
begin
      try
            ACBrCTe := TACBrCTe.Create(Self);
            ACBrCTe.Conhecimentos.Clear;
            ACBrCTe.Conhecimentos.LoadFromString(gDsXmlOri.Text) ;
            for Itens :=0 to ACBrCTe.Conhecimentos.Count-1 do
            begin
                  gCdsCorCTE.Append;
                  gCdsCorCTE.FieldByName('ide_cUF').AsString            :=
                                           IntToStr(ACBrCTe.Conhecimentos.Items[Itens].CTe.ide.cUF);
                  gCdsCorCTE.FieldByName('ide_cCT').AsString            :=
                                           IntToStr(ACBrCTe.Conhecimentos.Items[Itens].CTe.ide.cCT);
                  gCdsCorCTE.FieldByName('ide_CFOP').AsString           :=
                                          IntToStr(ACBrCTe.Conhecimentos.Items[Itens].CTe.ide.CFOP);
                  gCdsCorCTE.FieldByName('ide_natOp').AsString          :=
                                                   ACBrCTe.Conhecimentos.Items[Itens].CTe.ide.natOp;
                  gCdsCorCTE.FieldByName('ide_forPag').AsString         :=
                                   tpforPagToStr(ACBrCTe.Conhecimentos.Items[Itens].CTe.ide.forPag);
                  gCdsCorCTE.FieldByName('ide_mod').AsString            :=
                                                  ACBrCTe.Conhecimentos.Items[Itens].CTe.ide.modelo;
                  gCdsCorCTE.FieldByName('ide_serie').AsString          :=
                                         IntToStr(ACBrCTe.Conhecimentos.Items[Itens].CTe.ide.serie);
                  gCdsCorCTE.FieldByName('ide_nCT').AsString            :=
                                           IntToStr(ACBrCTe.Conhecimentos.Items[Itens].CTe.ide.nCT);
                  gCdsCorCTE.FieldByName('ide_dhEmi').AsString          :=
                                        DateToStr(ACBrCTe.Conhecimentos.Items[Itens].CTe.ide.dhEmi);
                  gCdsCorCTE.FieldByName('ide_tpImp').AsString          :=
                                        DateToStr(ACBrCTe.Conhecimentos.Items[Itens].CTe.ide.dhEmi);
                  gCdsCorCTE.FieldByName('ide_tpEmis').AsString         :=
                                     TpEmisToStr(ACBrCTe.Conhecimentos.Items[Itens].CTe.ide.tpEmis);
                  gCdsCorCTE.FieldByName('ide_cDV').AsString            :=
                                           IntToStr(ACBrCTe.Conhecimentos.Items[Itens].CTe.ide.cDV);
                  gCdsCorCTE.FieldByName('ide_tpAmb').AsString          :=
                                       TpAmbToStr(ACBrCTe.Conhecimentos.Items[Itens].CTe.ide.tpAmb);
                  gCdsCorCTE.FieldByName('ide_tpCTE').AsString          :=
                                    tpCTToStrText(ACBrCTe.Conhecimentos.Items[Itens].CTe.ide.tpCTE);
                  gCdsCorCTE.FieldByName('ide_procEmi').AsString        :=
                                   procEmiToStr(ACBrCTe.Conhecimentos.Items[Itens].CTe.ide.procEmi);
                  gCdsCorCTE.FieldByName('ide_refCTE').AsString         :=
                                                  ACBrCTe.Conhecimentos.Items[Itens].CTe.ide.refCTE;
                  gCdsCorCTE.FieldByName('ide_cMunEmi').AsString        :=
                                       IntToStr(ACBrCTe.Conhecimentos.Items[Itens].CTe.ide.cMunEnv);
                  gCdsCorCTE.FieldByName('ide_xMunEmi').AsString        :=
                                                 ACBrCTe.Conhecimentos.Items[Itens].CTe.ide.xMunEnv;
                  gCdsCorCTE.FieldByName('ide_UFEmi').AsString          :=
                                                   ACBrCTe.Conhecimentos.Items[Itens].CTe.ide.UFEnv;
                  gCdsCorCTE.FieldByName('ide_modal').AsString          :=
                                     TpModalToStr(ACBrCTe.Conhecimentos.Items[Itens].CTe.ide.modal);
                  gCdsCorCTE.FieldByName('ide_TpServ').AsString         :=
                                  TpServPagToStr(ACBrCTe.Conhecimentos.Items[Itens].CTe.ide.TpServ);
                  gCdsCorCTE.FieldByName('ide_cMunIni').AsString        :=
                                       IntToStr(ACBrCTe.Conhecimentos.Items[Itens].CTe.ide.cMunIni);
                  gCdsCorCTE.FieldByName('ide_xMunIni').AsString        :=
                                                 ACBrCTe.Conhecimentos.Items[Itens].CTe.ide.xMunIni;
                  gCdsCorCTE.FieldByName('ide_UFIni').AsString          :=
                                                   ACBrCTe.Conhecimentos.Items[Itens].CTe.ide.UFIni;
                  gCdsCorCTE.FieldByName('ide_cMunFim').AsString        :=
                                       IntToStr(ACBrCTe.Conhecimentos.Items[Itens].CTe.ide.cMunFim);
                  gCdsCorCTE.FieldByName('ide_xMunFim').AsString        :=
                                                 ACBrCTe.Conhecimentos.Items[Itens].CTe.ide.xMunFim;
                  gCdsCorCTE.FieldByName('ide_UFFim').AsString          :=
                                                   ACBrCTe.Conhecimentos.Items[Itens].CTe.ide.UFFim;
                  gCdsCorCTE.FieldByName('ide_Retira').AsString         :=
                                TpRetiraPagToStr(ACBrCTe.Conhecimentos.Items[Itens].CTe.ide.retira);
                  gCdsCorCTE.FieldByName('toma03_toma').AsString        :=
                             TpTomadorToStr(ACBrCTe.Conhecimentos.Items[Itens].CTe.Ide.toma03.Toma);
                  gCdsCorCTE.FieldByName('toma4_toma').AsString         :=
                              TpTomadorToStr(ACBrCTe.Conhecimentos.Items[Itens].CTe.Ide.toma4.toma);
                  gCdsCorCTE.FieldByName('toma4_CNPJ').AsString         :=
                                           ACBrCTe.Conhecimentos.Items[Itens].CTe.Ide.toma4.CNPJCPF;
                  gCdsCorCTE.FieldByName('toma4_IE').AsString           :=
                                                ACBrCTe.Conhecimentos.Items[Itens].CTe.Ide.toma4.IE;
                  gCdsCorCTE.FieldByName('toma4_xNome').AsString        :=
                                             ACBrCTe.Conhecimentos.Items[Itens].CTe.Ide.toma4.xNome;
                  gCdsCorCTE.FieldByName('toma4_xFant').AsString        :=
                                             ACBrCTe.Conhecimentos.Items[Itens].CTe.Ide.toma4.xFant;
                  gCdsCorCTE.FieldByName('toma4_fone').AsString         :=
                                              ACBrCTe.Conhecimentos.Items[Itens].CTe.Ide.toma4.fone;
                  gCdsCorCTE.FieldByName('enderToma_xLgr').AsString     :=
                                    ACBrCTe.Conhecimentos.Items[Itens].CTe.Ide.toma4.enderToma.xLgr;
                  gCdsCorCTE.FieldByName('enderToma_nro').AsString      :=
                                     ACBrCTe.Conhecimentos.Items[Itens].CTe.Ide.toma4.enderToma.nro;
                  gCdsCorCTE.FieldByName('enderToma_xBairro').AsString  :=
                                 ACBrCTe.Conhecimentos.Items[Itens].CTe.Ide.toma4.enderToma.xBairro;
                  gCdsCorCTE.FieldByName('enderToma_cMun').AsString     :=
                          IntToStr(ACBrCTe.Conhecimentos.Items[Itens].CTe.Ide.toma4.enderToma.cMun);
                  gCdsCorCTE.FieldByName('enderToma_xMun').AsString     :=
                                    ACBrCTe.Conhecimentos.Items[Itens].CTe.Ide.toma4.enderToma.xMun;
                  gCdsCorCTE.FieldByName('enderToma_CEP').AsString      :=
                           IntToStr(ACBrCTe.Conhecimentos.Items[Itens].CTe.Ide.toma4.enderToma.CEP);
                  gCdsCorCTE.FieldByName('enderToma_UF').AsString       :=
                                      ACBrCTe.Conhecimentos.Items[Itens].CTe.Ide.toma4.enderToma.UF;
                  gCdsCorCTE.FieldByName('enderToma_cPais').AsString    :=
                         IntToStr(ACBrCTe.Conhecimentos.Items[Itens].CTe.Ide.toma4.enderToma.cPais);
                  gCdsCorCTE.FieldByName('enderToma_xPais').AsString    :=
                                   ACBrCTe.Conhecimentos.Items[Itens].CTe.Ide.toma4.enderToma.xPais;
                  gCdsCorCTE.FieldByName('compl_xCaracAd').AsString     :=
                                              ACBrCTe.Conhecimentos.Items[Itens].CTe.compl.xCaracAd;
                  gCdsCorCTE.FieldByName('compl_xCaracSer').AsString    :=
                                             ACBrCTe.Conhecimentos.Items[Itens].CTe.compl.xCaracSer;
                  gCdsCorCTE.FieldByName('compl_xEmi').AsString         :=
                                                  ACBrCTe.Conhecimentos.Items[Itens].CTe.compl.xEmi;
                  gCdsCorCTE.FieldByName('compl_xObs').AsString         :=
                                                  ACBrCTe.Conhecimentos.Items[Itens].CTe.compl.xObs;
                  gCdsCorCTE.FieldByName('fluxo_xOrig').AsString        :=
                                           ACBrCTe.Conhecimentos.Items[Itens].CTe.compl.fluxo.xOrig;
                  gCdsCorCTE.FieldByName('fluxo_xDest').AsString        :=
                                           ACBrCTe.Conhecimentos.Items[Itens].CTe.compl.fluxo.xDest;
                  gCdsCorCTE.FieldByName('fluxo_xRota').AsString        :=
                                           ACBrCTe.Conhecimentos.Items[Itens].CTe.compl.fluxo.xRota;
                  gCdsCorCTE.FieldByName('comData_tpPer').AsString      :=
                                           TpDataPeriodoToStr(ACBrCTe.Conhecimentos.Items[Itens].CTe
                                                                      .compl.Entrega.comData.tpPer);
                  gCdsCorCTE.FieldByName('comData_dProg').AsString      :=
                      DateToStr(ACBrCTe.Conhecimentos.Items[Itens].CTe.compl.Entrega.comData.dProg);
                  gCdsCorCTE.FieldByName('semHora_tpHor').AsString      :=
                                      TpHorarioIntervaloToStr(ACBrCTe.Conhecimentos.Items[Itens].CTe
                                                                      .compl.Entrega.semHora.tpHor);
                  gCdsCorCTE.FieldByName('emit_CNPJ').AsString          :=
                                                   ACBrCTe.Conhecimentos.Items[Itens].CTe.Emit.CNPJ;
                  gCdsCorCTE.FieldByName('emit_IE').AsString            :=
                                                     ACBrCTe.Conhecimentos.Items[Itens].CTe.emit.IE;
                  gCdsCorCTE.FieldByName('emit_xNome').AsString         :=
                                                  ACBrCTe.Conhecimentos.Items[Itens].CTe.emit.xNome;
                  gCdsCorCTE.FieldByName('emit_xFant').AsString         :=
                                                  ACBrCTe.Conhecimentos.Items[Itens].CTe.emit.xFant;
                  gCdsCorCTE.FieldByName('enderEmit_xLgr').AsString     :=
                                         ACBrCTe.Conhecimentos.Items[Itens].CTe.Emit.EnderEmit.xLgr;
                  gCdsCorCTE.FieldByName('enderEmit_nro').AsString      :=
                                          ACBrCTe.Conhecimentos.Items[Itens].CTe.Emit.enderEmit.nro;
                  gCdsCorCTE.FieldByName('enderEmit_xCpl').AsString     :=
                                         ACBrCTe.Conhecimentos.Items[Itens].CTe.Emit.enderEmit.xCpl;
                  gCdsCorCTE.FieldByName('enderEmit_xBairro').AsString  :=
                                      ACBrCTe.Conhecimentos.Items[Itens].CTe.Emit.enderEmit.xBairro;
                  gCdsCorCTE.FieldByName('enderEmit_cMun').AsString     :=
                               IntToStr(ACBrCTe.Conhecimentos.Items[Itens].CTe.Emit.enderEmit.cMun);
                  gCdsCorCTE.FieldByName('enderEmit_xMun').AsString     :=
                                        ACBrCTe.Conhecimentos.Items[Itens].CTe.Emit.enderEmit.xMun ;
                  gCdsCorCTE.FieldByName('enderEmit_CEP').AsString      :=
                                IntToStr(ACBrCTe.Conhecimentos.Items[Itens].CTe.Emit.enderEmit.CEP);
                  gCdsCorCTE.FieldByName('enderEmit_UF').AsString       :=
                                           ACBrCTe.Conhecimentos.Items[Itens].CTe.Emit.enderEmit.UF;
                  gCdsCorCTE.FieldByName('enderEmit_fone').AsString     :=
                                        ACBrCTe.Conhecimentos.Items[Itens].CTe.Emit.enderEmit.fone ;
                  gCdsCorCTE.FieldByName('rem_CNPJ').AsString           :=
                                                 ACBrCTe.Conhecimentos.Items[Itens].CTe.Rem.CNPJCPF;
                  gCdsCorCTE.FieldByName('rem_IE').AsString             :=
                                                      ACBrCTe.Conhecimentos.Items[Itens].CTe.rem.IE;
                  gCdsCorCTE.FieldByName('rem_xNome').AsString          :=
                                                   ACBrCTe.Conhecimentos.Items[Itens].CTe.rem.xNome;
                  gCdsCorCTE.FieldByName('rem_xFant').AsString          :=
                                                   ACBrCTe.Conhecimentos.Items[Itens].CTe.rem.xFant;
                  gCdsCorCTE.FieldByName('rem_fone').AsString           :=
                                                    ACBrCTe.Conhecimentos.Items[Itens].CTe.rem.fone;
                  gCdsCorCTE.FieldByName('enderReme_xLgr').AsString     :=
                                          ACBrCTe.Conhecimentos.Items[Itens].CTe.rem.enderReme.xLgr;
                  gCdsCorCTE.FieldByName('enderReme_nro').AsString      :=
                                           ACBrCTe.Conhecimentos.Items[Itens].CTe.rem.enderReme.nro;
                  gCdsCorCTE.FieldByName('enderReme_xCpl').AsString     :=
                                          ACBrCTe.Conhecimentos.Items[Itens].CTe.rem.enderReme.xCpl;
                  gCdsCorCTE.FieldByName('enderReme_xBairro').AsString  :=
                                       ACBrCTe.Conhecimentos.Items[Itens].CTe.rem.enderReme.xBairro;
                  gCdsCorCTE.FieldByName('enderReme_cMun').AsString     :=
                                IntToStr(ACBrCTe.Conhecimentos.Items[Itens].CTe.rem.enderReme.cMun);
                  gCdsCorCTE.FieldByName('enderReme_xMun').AsString     :=
                                          ACBrCTe.Conhecimentos.Items[Itens].CTe.rem.enderReme.xMun;
                  gCdsCorCTE.FieldByName('enderReme_CEP').AsString      :=
                                 IntToStr(ACBrCTe.Conhecimentos.Items[Itens].CTe.rem.enderReme.CEP);
                  gCdsCorCTE.FieldByName('enderReme_UF').AsString       :=
                                            ACBrCTe.Conhecimentos.Items[Itens].CTe.rem.enderReme.UF;
                  gCdsCorCTE.FieldByName('enderReme_cPais').AsString    :=
                               IntToStr(ACBrCTe.Conhecimentos.Items[Itens].CTe.rem.enderReme.cPais);
                  gCdsCorCTE.FieldByName('enderReme_xPais').AsString    :=
                                         ACBrCTe.Conhecimentos.Items[Itens].CTe.rem.enderReme.xPais;
                  gCdsCorCTE.FieldByName('exped_CNPJ').AsString         :=
                                               ACBrCTe.Conhecimentos.Items[Itens].CTe.exped.CNPJCPF;
                  gCdsCorCTE.FieldByName('exped_IE').AsString           :=
                                                    ACBrCTe.Conhecimentos.Items[Itens].CTe.exped.IE;
                  gCdsCorCTE.FieldByName('exped_xNome').AsString        :=
                                                 ACBrCTe.Conhecimentos.Items[Itens].CTe.exped.xNome;
                  gCdsCorCTE.FieldByName('exped_fone').AsString         :=
                                                  ACBrCTe.Conhecimentos.Items[Itens].CTe.exped.fone;
                  gCdsCorCTE.FieldByName('enderExped_xLgr').AsString    :=
                                       ACBrCTe.Conhecimentos.Items[Itens].CTe.Exped.EnderExped.xLgr;
                  gCdsCorCTE.FieldByName('enderExped_nro').AsString     :=
                                        ACBrCTe.Conhecimentos.Items[Itens].CTe.Exped.enderExped.nro;
                  gCdsCorCTE.FieldByName('enderExped_xCpl').AsString    :=
                                       ACBrCTe.Conhecimentos.Items[Itens].CTe.Exped.enderExped.xCpl;
                  gCdsCorCTE.FieldByName('enderExped_xBairro').AsString :=
                                    ACBrCTe.Conhecimentos.Items[Itens].CTe.Exped.enderExped.xBairro;
                  gCdsCorCTE.FieldByName('enderExped_cMun').AsString    :=
                             IntToStr(ACBrCTe.Conhecimentos.Items[Itens].CTe.Exped.enderExped.cMun);
                  gCdsCorCTE.FieldByName('enderExped_xMun').AsString    :=
                                       ACBrCTe.Conhecimentos.Items[Itens].CTe.Exped.enderExped.xMun;
                  gCdsCorCTE.FieldByName('enderExped_CEP').AsString     :=
                              IntToStr(ACBrCTe.Conhecimentos.Items[Itens].CTe.Exped.enderExped.CEP);
                  gCdsCorCTE.FieldByName('enderExped_UF').AsString      :=
                                         ACBrCTe.Conhecimentos.Items[Itens].CTe.Exped.enderExped.UF;
                  gCdsCorCTE.FieldByName('enderExped_cPais').AsString   :=
                            IntToStr(ACBrCTe.Conhecimentos.Items[Itens].CTe.Exped.enderExped.cPais);
                  gCdsCorCTE.FieldByName('enderExped_xPais').AsString   :=
                                      ACBrCTe.Conhecimentos.Items[Itens].CTe.Exped.enderExped.xPais;
                  gCdsCorCTE.FieldByName('receb_CNPJ').AsString         :=
                                               ACBrCTe.Conhecimentos.Items[Itens].CTe.receb.CNPJCPF;
                  gCdsCorCTE.FieldByName('receb_IE').AsString           :=
                                                    ACBrCTe.Conhecimentos.Items[Itens].CTe.receb.IE;
                  gCdsCorCTE.FieldByName('receb_xNome').AsString        :=
                                                 ACBrCTe.Conhecimentos.Items[Itens].CTe.receb.xNome;
                  gCdsCorCTE.FieldByName('receb_fone').AsString         :=
                                                  ACBrCTe.Conhecimentos.Items[Itens].CTe.receb.fone;
                  gCdsCorCTE.FieldByName('enderReceb_xLgr').AsString    :=
                                       ACBrCTe.Conhecimentos.Items[Itens].CTe.Receb.EnderReceb.xLgr;
                  gCdsCorCTE.FieldByName('enderReceb_nro').AsString     :=
                                        ACBrCTe.Conhecimentos.Items[Itens].CTe.Receb.enderReceb.nro;
                  gCdsCorCTE.FieldByName('enderReceb_xCpl').AsString    :=
                                       ACBrCTe.Conhecimentos.Items[Itens].CTe.Receb.enderReceb.xCpl;
                  gCdsCorCTE.FieldByName('enderReceb_xBairro').AsString :=
                                    ACBrCTe.Conhecimentos.Items[Itens].CTe.Receb.enderReceb.xBairro;
                  gCdsCorCTE.FieldByName('enderReceb_cMun').AsString    :=
                             IntToStr(ACBrCTe.Conhecimentos.Items[Itens].CTe.Receb.enderReceb.cMun);
                  gCdsCorCTE.FieldByName('enderReceb_xMun').AsString    :=
                                       ACBrCTe.Conhecimentos.Items[Itens].CTe.Receb.enderReceb.xMun;
                  gCdsCorCTE.FieldByName('enderReceb_CEP').AsString     :=
                              IntToStr(ACBrCTe.Conhecimentos.Items[Itens].CTe.Receb.enderReceb.CEP);
                  gCdsCorCTE.FieldByName('enderReceb_UF').AsString      :=
                                         ACBrCTe.Conhecimentos.Items[Itens].CTe.Receb.enderReceb.UF;
                  gCdsCorCTE.FieldByName('enderReceb_cPais').AsString   :=
                           IntToStr(ACBrCTe.Conhecimentos.Items[Itens].CTe.Receb.enderReceb.cPais) ;
                  gCdsCorCTE.FieldByName('enderReceb_xPais').AsString   :=
                                     ACBrCTe.Conhecimentos.Items[Itens].CTe.Receb.enderReceb.xPais ;
                  gCdsCorCTE.FieldByName('dest_CNPJ').AsString          :=
                                                ACBrCTe.Conhecimentos.Items[Itens].CTe.dest.CNPJCPF;
                  gCdsCorCTE.FieldByName('dest_IE').AsString            :=
                                                     ACBrCTe.Conhecimentos.Items[Itens].CTe.dest.IE;
                  gCdsCorCTE.FieldByName('dest_xNome').AsString         :=
                                                  ACBrCTe.Conhecimentos.Items[Itens].CTe.dest.xNome;
                  gCdsCorCTE.FieldByName('dest_fone').AsString          :=
                                                   ACBrCTe.Conhecimentos.Items[Itens].CTe.dest.fone;
                  gCdsCorCTE.FieldByName('enderDest_xLgr').AsString     :=
                                         ACBrCTe.Conhecimentos.Items[Itens].CTe.Dest.enderDest.xLgr;
                  gCdsCorCTE.FieldByName('enderDest_nro').AsString      :=
                                          ACBrCTe.Conhecimentos.Items[Itens].CTe.Dest.enderDest.nro;
                  gCdsCorCTE.FieldByName('enderDest_xCpl').AsString     :=
                                         ACBrCTe.Conhecimentos.Items[Itens].CTe.Dest.enderDest.xCpl;
                  gCdsCorCTE.FieldByName('enderDest_xBairro').AsString  :=
                                      ACBrCTe.Conhecimentos.Items[Itens].CTe.Dest.enderDest.xBairro;
                  gCdsCorCTE.FieldByName('enderDest_cMun').AsString     :=
                               IntToStr(ACBrCTe.Conhecimentos.Items[Itens].CTe.Dest.enderDest.cMun);
                  gCdsCorCTE.FieldByName('enderDest_xMun').AsString     :=
                                         ACBrCTe.Conhecimentos.Items[Itens].CTe.Dest.enderDest.xMun;
                  gCdsCorCTE.FieldByName('enderDest_CEP').AsString      :=
                                IntToStr(ACBrCTe.Conhecimentos.Items[Itens].CTe.Dest.enderDest.CEP);
                  gCdsCorCTE.FieldByName('enderDest_UF').AsString       :=
                                           ACBrCTe.Conhecimentos.Items[Itens].CTe.Dest.enderDest.UF;
                  gCdsCorCTE.FieldByName('enderDest_cPais').AsString    :=
                              IntToStr(ACBrCTe.Conhecimentos.Items[Itens].CTe.Dest.enderDest.cPais);
                  gCdsCorCTE.FieldByName('enderDest_xPais').AsString    :=
                                       ACBrCTe.Conhecimentos.Items[Itens].CTe.Dest.enderDest.xPais ;
                  gCdsCorCTE.FieldByName('vPrest_vTPrest').AsString     :=
                                  FloatToStr(ACBrCTe.Conhecimentos.Items[Itens].CTe.vPrest.vTPrest);
                  gCdsCorCTE.FieldByName('vPrest_vRec').AsString        :=
                                     FloatToStr(ACBrCTe.Conhecimentos.Items[Itens].CTe.vPrest.vRec);
                  gCdsCorCTE.FieldByName('infCarga_vMerc').AsString     :=
                                 FloatToStr(ACBrCTe.Conhecimentos.Items[Itens].CTe.infCarga.vCarga);
                  gCdsCorCTE.FieldByName('infCarga_ProPred').AsString   :=
                                            ACBrCTe.Conhecimentos.Items[Itens].CTe.infCarga.ProPred;

             //     gCdsCorCTE.FieldByName('ICMS_CST').AsString :=
           //                              ACBrCTe.Conhecimentos.Items[Itens].CTe.Imp.ICMS.SituTrib;
             //     gCdsCorCTE.FieldByName('ICMS_vBC').AsString :=
              //                             ACBrCTe.Conhecimentos.Items[Itens].CTe.Imp.ICMS.ICMS60;
                 // gCdsCorCTE.FieldByName('ICMS_pICMS').AsString :=
                //                            ACBrCTe.Conhecimentos.Items[Itens].CTe.Imp.ICMS.pICMS;
                 // gCdsCorCTE.FieldByName('ICMS_vICMS').AsString :=
                  //                          ACBrCTe.Conhecimentos.Items[Itens].CTe.Imp.ICMS.vICMS;

                  for count :=0 to ACBrCTe.Conhecimentos.Items[Itens].CTe.infSeg.Count-1 do
                  begin
                        gCdsCorCTE.FieldByName('seg_respSeg').AsString   :=
                        TpRspSeguroToStr(ACBrCTe.Conhecimentos.Items[Itens].CTe.infSeg
                                                                             .Items[count].respSeg);
                        gCdsCorCTE.FieldByName('seg_xSeg').AsString      :=
                        ACBrCTe.Conhecimentos.Items[Itens].CTe.infSeg.Items[count].xSeg;
                        gCdsCorCTE.FieldByName('seg_nApol').AsString     :=
                        ACBrCTe.Conhecimentos.Items[Itens].CTe.infSeg.Items[count].nApol;
                        gCdsCorCTE.FieldByName('seg_vMerc').AsString     :=
                        FloatToStr(ACBrCTe.Conhecimentos.Items[Itens].CTe.infSeg
                                                                              .Items[count].vCarga);
                  end;

                  gCdsCorCTE.FieldByName('rodo_RNTRC').AsString       :=
                                                  ACBrCTe.Conhecimentos.Items[Itens].CTe.rodo.RNTRC;
                  gCdsCorCTE.FieldByName('rodo_dPrev').AsString       :=
                                       DateToStr(ACBrCTe.Conhecimentos.Items[Itens].CTe.rodo.dPrev);
                  gCdsCorCTE.FieldByName('rodo_lota').AsString        :=
                                   TpLotacaoToStr(ACBrCTe.Conhecimentos.Items[Itens].CTe.rodo.lota);
                  for count :=0 to ACBrCTe.Conhecimentos.Items[Itens].CTe.vPrest.comp.Count-1 do
                  begin
                        gCdsPreCte.Append;
                        gCdsPreCte.FieldByName('Comp_xNome').AsString :=
                                   ACBrCTe.Conhecimentos.Items[Itens].CTe.vPrest.comp[count].xNome ;
                        gCdsPreCte.FieldByName('Comp_vComp').AsString :=
                        FloatToStr(ACBrCTe.Conhecimentos.Items[Itens].CTe.vPrest.comp[count].vComp);
                        gCdsPreCte.Post;
                  end;
                  for count :=0 to ACBrCTe.Conhecimentos.Items[Itens].CTe.infCarga.infQ.Count-1 do
                  begin
                        gCdsCarCte.Append;
                        gCdsCarCte.FieldByName('infQ_cUnid').AsString  :=
                        UnidMedToStr(ACBrCTe.Conhecimentos.Items[Itens].CTe.infCarga.infQ
                                                                               .Items[count].cUnid);
                        gCdsCarCte.FieldByName('infQ_tpMed').AsString  :=
                        ACBrCTe.Conhecimentos.Items[Itens].CTe.infCarga.infQ.Items[count].tpMed;
                        gCdsCarCte.FieldByName('infQ_qCarga').AsString :=
                        FloatToStr(ACBrCTe.Conhecimentos.Items[Itens].CTe.infCarga.infQ
                                                                              .Items[count].qCarga);
                        gCdsCarCte.Post;
                  end;
                  for count :=0 to ACBrCTe.Conhecimentos.Items[Itens].CTe.Rem.InfNFe.Count-1 do
                  begin
                        gCdsInfCte.Append;
                        gCdsInfCte.FieldByName('infNFe_chave').AsString := ACBrCTe.Conhecimentos.
                                                     Items[Itens].CTe.Rem.InfNFe.Items[count].chave;
//                        gCdsInfCte.FieldByName('infNF_serie').AsString :=
//                              ACBrCTe.Conhecimentos.Items[Itens].CTe.Rem.infNF.Items[count].serie;
//                        gCdsInfCte.FieldByName('infNF_nDoc').AsString :=
//                               ACBrCTe.Conhecimentos.Items[Itens].CTe.Rem.InfNF.Items[count].nDoc;
//                        gCdsInfCte.FieldByName('infNF_dEmi').AsString :=
//                                DateToStr(ACBrCTe.Conhecimentos.Items[Itens].CTe.Rem.
//                                                                         InfNF.Items[count].dEmi);
//                        gCdsInfCte.FieldByName('infNF_vBC').AsString :=
//                                  FloatToStr(ACBrCTe.Conhecimentos.Items[Itens].CTe.Rem.
//                                                                          InfNF.Items[count].vBC);
//                        gCdsInfCte.FieldByName('infNF_vICMS').AsString :=
//                                  FloatToStr(ACBrCTe.Conhecimentos.Items[Itens].CTe.
//                                                                    Rem.InfNF.Items[count].vICMS);
//                        gCdsInfCte.FieldByName('infNF_vBCST').AsString :=
//                                  FloatToStr(ACBrCTe.Conhecimentos.Items[Itens].CTe.
//                                                                    Rem.InfNF.Items[count].vBCST);
//                        gCdsInfCte.FieldByName('infNF_vST').AsString :=
//                                  FloatToStr(ACBrCTe.Conhecimentos.Items[Itens].CTe.
//                                                                      Rem.InfNF.Items[count].vST);
//                        gCdsInfCte.FieldByName('infNF_vProd').AsString :=
//                                                FloatToStr(ACBrCTe.Conhecimentos.Items[Itens].CTe.
//                                                                    Rem.InfNF.Items[count].vProd);
//                        gCdsInfCte.FieldByName('infNF_vNF').AsString :=
//                                                FloatToStr(ACBrCTe.Conhecimentos.Items[Itens].CTe.
//                                                                      Rem.InfNF.Items[count].vNF);
//                        gCdsInfCte.FieldByName('infNF_nCFOP').AsString :=
//                                                  IntToStr(ACBrCTe.Conhecimentos.Items[Itens].CTe.
//                                                                    Rem.InfNF.Items[count].nCFOP);
                       gCdsInfCte.Post;
                  end;
                  gCdsCorCTE.post;
            end;
      finally
             FreeAndNil(ACBrCTe);
      end;
end;

procedure TBrvXML.CriarTabelaTemporariaXMLNFE;
var
  itens   : Integer;
  count   : Integer;
  ACBrNFe : TACBrNFe;
begin
      try

      ACBrNFe := TACBrNFe.Create(Self);
      ACBrNFe.NotasFiscais.Clear;
      ACBrNFe.NotasFiscais.LoadFromString(gDsXmlOri.Text) ;
      CriarTabelaTemporariaCorpoNFe;
      CriarTabelaTemporariaFatura;
      CriarTabelaTemporariaProduto;

      for itens:=0 to ACBrNFe.NotasFiscais.Count-1 do
      begin
            gCdsCorNFE.Append;
            gCdsCorNFE.FieldByName('xMotivo').AsString            :=
                                              ACBrNFe.NotasFiscais.Items[itens].NFe.procNFe.xMotivo;
            gCdsCorNFE.FieldByName('chNFe').AsString              :=
                                                ACBrNFe.NotasFiscais.Items[itens].NFe.procNFe.chNFe;
            gCdsCorNFE.FieldByName('dhRecbto').AsString           :=
                                  DateToStr(ACBrNFe.NotasFiscais.Items[itens].NFe.procNFe.dhRecbto);
            gCdsCorNFE.FieldByName('nProt').AsString              :=
                                                ACBrNFe.NotasFiscais.Items[itens].NFe.procNFe.nProt;
            gCdsCorNFE.FieldByName('ide_mod').AsString            :=
                                         IntToStr(ACBrNFe.NotasFiscais.Items[itens].NFe.ide.modelo);
            gCdsCorNFE.FieldByName('ide_natOp').AsString          :=
                                                    ACBrNFe.NotasFiscais.Items[itens].NFe.ide.natOp;
            gCdsCorNFE.FieldByName('ide_nNF').AsString            :=
                                            IntToStr(ACBrNFe.NotasFiscais.Items[itens].NFe.ide.nNF);
            gCdsCorNFE.FieldByName('ide_serie').AsString          :=
                                          IntToStr(ACBrNFe.NotasFiscais.Items[itens].NFe.ide.serie);
            gCdsCorNFE.FieldByName('ide_tpImp').AsString          :=
                                        TpImpToStr(ACBrNFe.NotasFiscais.Items[itens].NFe.ide.tpImp);
            gCdsCorNFE.FieldByName('ide_tpEmis').AsString         :=
                                      TpEmisToStr(ACBrNFe.NotasFiscais.Items[itens].NFe.ide.tpEmis);
            gCdsCorNFE.FieldByName('ide_cDV').AsString            :=
                                            IntToStr(ACBrNFe.NotasFiscais.Items[itens].NFe.ide.cDV);
            gCdsCorNFE.FieldByName('ide_tpAmb').AsString          :=
                                        TpAmbToStr(ACBrNFe.NotasFiscais.Items[itens].NFe.ide.tpAmb);
            gCdsCorNFE.FieldByName('ide_dEmi').AsString           :=
                                          DateToStr(ACBrNFe.NotasFiscais.Items[itens].NFe.ide.dEmi);
            gCdsCorNFE.FieldByName('ide_dSaiEnt').AsString        :=
                                       DateToStr(ACBrNFe.NotasFiscais.Items[itens].NFe.ide.dSaiEnt);
            gCdsCorNFE.FieldByName('ide_hSaiEnt').AsString        :=
                                       DateToStr(ACBrNFe.NotasFiscais.Items[itens].NFe.ide.hSaiEnt);
            gCdsCorNFE.FieldByName('emit_xNome').AsString         :=
                                                   ACBrNFe.NotasFiscais.Items[itens].NFe.emit.xNome;
            gCdsCorNFE.FieldByName('emit_CNPJ').AsString          :=
                                                 ACBrNFe.NotasFiscais.Items[itens].NFe.emit.CNPJCPF;
            gCdsCorNFE.FieldByName('emit_IE').AsString            :=
                                                      ACBrNFe.NotasFiscais.Items[itens].NFe.emit.IE;
            gCdsCorNFE.FieldByName('emit_CRT').AsString           :=
                                           CRTToStr(ACBrNFe.NotasFiscais.Items[itens].NFe.emit.CRT);


            gCdsCorNFE.FieldByName('enderEmit_xLgr').AsString     :=
                                          ACBrNFe.NotasFiscais.Items[itens].NFe.Emit.EnderEmit.xLgr;
            gCdsCorNFE.FieldByName('enderEmit_nro').AsString      :=
                                           ACBrNFe.NotasFiscais.Items[itens].NFe.Emit.enderEmit.nro;
            gCdsCorNFE.FieldByName('enderEmit_xBairro').AsString  :=
                                       ACBrNFe.NotasFiscais.Items[itens].NFe.Emit.enderEmit.xBairro;
            gCdsCorNFE.FieldByName('enderEmit_xMun').AsString     :=
                                          ACBrNFe.NotasFiscais.Items[itens].NFe.Emit.enderEmit.xMun;
            gCdsCorNFE.FieldByName('enderEmit_CEP').AsString      :=
                                 IntToStr(ACBrNFe.NotasFiscais.Items[itens].NFe.Emit.enderEmit.CEP);
            gCdsCorNFE.FieldByName('enderEmit_fone').AsString     :=
                                          ACBrNFe.NotasFiscais.Items[itens].NFe.Emit.enderEmit.fone;
            gCdsCorNFE.FieldByName('enderEmit_UF').AsString       :=
                                            ACBrNFe.NotasFiscais.Items[itens].NFe.Emit.enderEmit.UF;
            gCdsCorNFE.FieldByName('dest_xNome').AsString         :=
                                                   ACBrNFe.NotasFiscais.Items[itens].NFe.Dest.xNome;
            gCdsCorNFE.FieldByName('dest_CNPJ').AsString          :=
                                                 ACBrNFe.NotasFiscais.Items[itens].NFe.dest.CNPJCPF;
            gCdsCorNFE.FieldByName('dest_CPF').AsString           :=
                                                 ACBrNFe.NotasFiscais.Items[itens].NFe.dest.CNPJCPF;
            gCdsCorNFE.FieldByName('dest_IE').AsString            :=
                                                      ACBrNFe.NotasFiscais.Items[itens].NFe.dest.IE;
            gCdsCorNFE.FieldByName('dest_email').AsString         :=
                                                   ACBrNFe.NotasFiscais.Items[itens].NFe.dest.email;
            gCdsCorNFE.FieldByName('enderDest_xLgr').AsString     :=
                                          ACBrNFe.NotasFiscais.Items[itens].NFe.Dest.EnderDest.xLgr;
            gCdsCorNFE.FieldByName('enderDest_nro').AsString      :=
                                           ACBrNFe.NotasFiscais.Items[itens].NFe.Dest.enderDest.nro;
            gCdsCorNFE.FieldByName('enderDest_xBairro').AsString  :=
                                       ACBrNFe.NotasFiscais.Items[itens].NFe.Dest.enderDest.xBairro;
            gCdsCorNFE.FieldByName('enderDest_xMun').AsString     :=
                                          ACBrNFe.NotasFiscais.Items[itens].NFe.Dest.enderDest.xMun;
            gCdsCorNFE.FieldByName('enderDest_CEP').AsString      :=
                                 IntToStr(ACBrNFe.NotasFiscais.Items[itens].NFe.Dest.enderDest.CEP);
            gCdsCorNFE.FieldByName('enderDest_fone').AsString     :=
                                         ACBrNFe.NotasFiscais.Items[itens].NFe.Dest.enderDest.fone ;
            gCdsCorNFE.FieldByName('enderDest_UF').AsString       :=
                                           ACBrNFe.NotasFiscais.Items[itens].NFe.Dest.enderDest.UF ;
            gCdsCorNFE.FieldByName('ICMSTot_vBc').AsString        :=
                                FloatToStr(ACBrNFe.NotasFiscais.Items[itens].NFe.Total.ICMSTot.vBC);
            gCdsCorNFE.FieldByName('ICMSTot_vICMS').AsString      :=
                              FloatToStr(ACBrNFe.NotasFiscais.Items[itens].NFe.Total.ICMSTot.vICMS);
            gCdsCorNFE.FieldByName('ICMSTot_vBCST').AsString      :=
                              FloatToStr(ACBrNFe.NotasFiscais.Items[itens].NFe.Total.ICMSTot.vBCST);
            gCdsCorNFE.FieldByName('ICMSTot_vST').AsString        :=
                                FloatToStr(ACBrNFe.NotasFiscais.Items[itens].NFe.Total.ICMSTot.vST);
            gCdsCorNFE.FieldByName('ICMSTot_vProd').AsString      :=
                              FloatToStr(ACBrNFe.NotasFiscais.Items[itens].NFe.Total.ICMSTot.vProd);
            gCdsCorNFE.FieldByName('ICMSTot_vFrete').AsString     :=
                             FloatToStr(ACBrNFe.NotasFiscais.Items[itens].NFe.Total.ICMSTot.vFrete);
            gCdsCorNFE.FieldByName('ICMSTot_vSeg').AsString       :=
                               FloatToStr(ACBrNFe.NotasFiscais.Items[itens].NFe.Total.ICMSTot.vSeg);
            gCdsCorNFE.FieldByName('ICMSTot_vOutro').AsString     :=
                             FloatToStr(ACBrNFe.NotasFiscais.Items[itens].NFe.Total.ICMSTot.vOutro);
            gCdsCorNFE.FieldByName('ICMSTot_vIPI').AsString       :=
                               FloatToStr(ACBrNFe.NotasFiscais.Items[itens].NFe.Total.ICMSTot.vIPI);
            gCdsCorNFE.FieldByName('ICMSTot_vDesc').AsString      :=
                              FloatToStr(ACBrNFe.NotasFiscais.Items[itens].NFe.Total.ICMSTot.vDesc);
            gCdsCorNFE.FieldByName('ICMSTot_vNF').AsString        :=
                                FloatToStr(ACBrNFe.NotasFiscais.Items[itens].NFe.Total.ICMSTot.vNF);
            gCdsCorNFE.FieldByName('transp_modFrete').AsString    :=
                               modFreteToStr(ACBrNFe.NotasFiscais.Items[itens].NFe.Transp.modFrete);
            gCdsCorNFE.FieldByName('transporta_xNome').AsString   :=
                                      ACBrNFe.NotasFiscais.Items[itens].NFe.Transp.transporta.xNome;
            gCdsCorNFE.FieldByName('transporta_CNPJ').AsString    :=
                                    ACBrNFe.NotasFiscais.Items[itens].NFe.Transp.transporta.CNPJCPF;
            gCdsCorNFE.FieldByName('transporta_IE').AsString      :=
                                         ACBrNFe.NotasFiscais.Items[itens].NFe.Transp.transporta.IE;
            gCdsCorNFE.FieldByName('transporta_xEnder').AsString  :=
                                     ACBrNFe.NotasFiscais.Items[itens].NFe.Transp.transporta.xEnder;
            gCdsCorNFE.FieldByName('transporta_xMun').AsString    :=
                                       ACBrNFe.NotasFiscais.Items[itens].NFe.Transp.transporta.xMun;
            gCdsCorNFE.FieldByName('transporta_UF').AsString      :=
                                         ACBrNFe.NotasFiscais.Items[itens].NFe.Transp.transporta.UF;
            gCdsCorNFE.FieldByName('veicTransp_placa').AsString   :=
                                     ACBrNFe.NotasFiscais.Items[itens].NFe.Transp.veicTransp.placa ;
            gCdsCorNFE.FieldByName('veicTransp_UF').AsString      :=
                                         ACBrNFe.NotasFiscais.Items[itens].NFe.Transp.veicTransp.UF;
            gCdsCorNFE.FieldByName('xml').AsString      := gDsXmlOri.Text;

            if ACBrNFe.NotasFiscais.Items[itens].NFe.Transp.vol.Count > 0 then
            begin
                  gCdsCorNFE.FieldByName('vol_qVol').AsString           :=
                           IntToStr(ACBrNFe.NotasFiscais.Items[itens].NFe.Transp.vol.Items[0].qVol);
                  gCdsCorNFE.FieldByName('vol_esp').AsString            :=
                                      ACBrNFe.NotasFiscais.Items[itens].NFe.Transp.vol.Items[0].esp;
                  gCdsCorNFE.FieldByName('vol_marca').AsString          :=
                                    ACBrNFe.NotasFiscais.Items[itens].NFe.Transp.vol.Items[0].marca;
                  gCdsCorNFE.FieldByName('vol_nVol').AsString           :=
                                     ACBrNFe.NotasFiscais.Items[itens].NFe.Transp.vol.Items[0].nVol;
                  gCdsCorNFE.FieldByName('vol_pesoL').AsString          :=
                        FloatToStr(ACBrNFe.NotasFiscais.Items[itens].NFe.Transp.vol.Items[0].pesoL);
                  gCdsCorNFE.FieldByName('vol_pesoB').AsString          :=
                        FloatToStr(ACBrNFe.NotasFiscais.Items[itens].NFe.Transp.vol.Items[0].pesoB);
            end;
            for Count := 0 to ACBrNFe.NotasFiscais.Items[itens].NFe.Det.Count-1 do
            begin
                  gCdsProdut.Append;
                  gCdsProdut.FieldByName('prod_cProd').AsString      :=
                                  ACBrNFe.NotasFiscais.Items[itens].NFe.Det.Items[Count].Prod.cProd;
                  gCdsProdut.FieldByName('prod_cEAN').AsString       :=
                                   ACBrNFe.NotasFiscais.Items[itens].NFe.Det.Items[Count].Prod.cEAN;
                  gCdsProdut.FieldByName('prod_xProd').AsString      :=
                                 ACBrNFe.NotasFiscais.Items[itens].NFe.Det.Items[Count].Prod.xProd ;
                  gCdsProdut.FieldByName('prod_NCM').AsString        :=
                                    ACBrNFe.NotasFiscais.Items[itens].NFe.Det.Items[Count].Prod.NCM;
                  gCdsProdut.FieldByName('prod_CFOP').AsString       :=
                                   ACBrNFe.NotasFiscais.Items[itens].NFe.Det.Items[Count].Prod.CFOP;
                  gCdsProdut.FieldByName('prod_uCom').AsString       :=
                                   ACBrNFe.NotasFiscais.Items[itens].NFe.Det.Items[Count].Prod.uCom;
                  gCdsProdut.FieldByName('prod_qCom').AsString       :=
                       FloatToStr(ACBrNFe.NotasFiscais.Items[itens].NFe.Det.Items[Count].Prod.qCom);
                  gCdsProdut.FieldByName('prod_vUnCom').AsString     :=
                     FloatToStr(ACBrNFe.NotasFiscais.Items[itens].NFe.Det.Items[Count].Prod.vUnCom);
                  gCdsProdut.FieldByName('prod_vProd').AsString      :=
                      FloatToStr(ACBrNFe.NotasFiscais.Items[itens].NFe.Det.Items[Count].Prod.vProd);
                  gCdsProdut.FieldByName('prod_cEANTrib').AsString   :=
                               ACBrNFe.NotasFiscais.Items[itens].NFe.Det.Items[Count].Prod.cEANTrib;
                  gCdsProdut.FieldByName('prod_uTrib').AsString      :=
                                  ACBrNFe.NotasFiscais.Items[itens].NFe.Det.Items[Count].Prod.uTrib;
                  gCdsProdut.FieldByName('prod_qTrib').AsString      :=
                      FloatToStr(ACBrNFe.NotasFiscais.Items[itens].NFe.Det.Items[Count].Prod.qTrib);
                  gCdsProdut.FieldByName('prod_vUnTrib').AsString    :=
                    FloatToStr(ACBrNFe.NotasFiscais.Items[itens].NFe.Det.Items[Count].Prod.vUnTrib);
                  gCdsProdut.FieldByName('prod_vFrete').AsString     :=
                     FloatToStr(ACBrNFe.NotasFiscais.Items[itens].NFe.Det.Items[Count].Prod.vFrete);
                  gCdsProdut.FieldByName('prod_vSeg').AsString       :=
                       FloatToStr(ACBrNFe.NotasFiscais.Items[itens].NFe.Det.Items[Count].Prod.vSeg);
                  gCdsProdut.FieldByName('prod_vDesc').AsString      :=
                      FloatToStr(ACBrNFe.NotasFiscais.Items[itens].NFe.Det.Items[Count].Prod.vDesc);
                  gCdsProdut.FieldByName('ICMS_pICMS').AsString      :=
                          FloatToStr(ACBrNFe.NotasFiscais.Items[itens].NFe.Det.Items[Count].
                                                                                Imposto.ICMS.pICMS);
//                  gCdsProdut.FieldByName('ICMS_CST').AsString        :=
//                            ACBrNFe.NotasFiscais.Items[itens].NFe.Det.Items[Count].
//                                                         Imposto.ICMS.CST.cst40.cst10.cst30.cst20;
                  gCdsProdut.FieldByName('ICMS_orig').AsString       :=
                                    OrigToStr(ACBrNFe.NotasFiscais.Items[itens].NFe.Det.Items[Count]
                                                                                .Imposto.ICMS.orig);
                  gCdsProdut.FieldByName('ICMS_vBC').AsString        :=
                                   FloatToStr(ACBrNFe.NotasFiscais.Items[itens].NFe.Det.Items[Count]
                                                                                 .Imposto.ICMS.vBC);
                  gCdsProdut.FieldByName('ICMS_vICMS').AsString      :=
                                   FloatToStr(ACBrNFe.NotasFiscais.Items[itens].NFe.Det.Items[Count]
                                                                               .Imposto.ICMS.vICMS);
                  gCdsProdut.FieldByName('ICMS_vBCSTRet').AsString   :=
                                   FloatToStr(ACBrNFe.NotasFiscais.Items[itens].NFe.Det.Items[Count]
                                                                            .Imposto.ICMS.vBCSTRet);
                  gCdsProdut.FieldByName('ICMS_vICMSSTRet').AsString :=
                                   FloatToStr(ACBrNFe.NotasFiscais.Items[itens].NFe.Det.Items[Count]
                                                                          .Imposto.ICMS.vICMSSTRet);
                  gCdsProdut.FieldByName('ICMS_vBCST').AsString      :=
                                   FloatToStr(ACBrNFe.NotasFiscais.Items[itens].NFe.Det.Items[Count]
                                                                               .Imposto.ICMS.vBCST);
                  gCdsProdut.FieldByName('ICMS_pICMSST').AsString    :=
                                   FloatToStr(ACBrNFe.NotasFiscais.Items[itens].NFe.Det.Items[Count]
                                                                             .Imposto.ICMS.pICMSST);
                  gCdsProdut.FieldByName('ICMS_vICMSST').AsString    :=
                                   FloatToStr(ACBrNFe.NotasFiscais.Items[itens].NFe.Det.Items[Count]
                                                                             .Imposto.ICMS.vICMSST);
                  gCdsProdut.FieldByName('ICMS_pRedBC').AsString     :=
                                   FloatToStr(ACBrNFe.NotasFiscais.Items[itens].NFe.Det.Items[Count]
                                                                              .Imposto.ICMS.pRedBC);
                  gCdsProdut.FieldByName('IPITrib_pIPI').AsString    :=
                                   FloatToStr(ACBrNFe.NotasFiscais.Items[itens].NFe.Det.Items[Count]
                                                                                 .Imposto.IPI.pIPI);
                  gCdsProdut.FieldByName('IPITrib_vIPI').AsString    :=
                                   FloatToStr(ACBrNFe.NotasFiscais.Items[itens].NFe.Det.Items[Count]
                                                                                 .Imposto.IPI.vIPI);
                  gCdsProdut.Post;
            end;
            for count:=0 to ACBrNFe.NotasFiscais.Items[itens].NFe.Cobr.Dup.Count-1 do
            begin
                  gCdsFatura.Append;
                  gCdsFatura.FieldByName('dup_nDup').AsString  :=
                                   ACBrNFe.NotasFiscais.Items[itens].NFe.Cobr.Dup.Items[count].nDup;
                  gCdsFatura.FieldByName('dup_dVenc').AsString :=
                       DateToStr(ACBrNFe.NotasFiscais.Items[itens].NFe.Cobr.Dup.Items[count].dVenc);
                  gCdsFatura.FieldByName('dup_vDup').AsString  :=
                       FloatToStr(ACBrNFe.NotasFiscais.Items[itens].NFe.Cobr.Dup.Items[count].vDup);
                  gCdsFatura.Post;
            end;
            gCdsCorNFE.FieldByName('infAdic_infCpl').AsString :=
                                               ACBrNFe.NotasFiscais.Items[itens].NFe.InfAdic.infCpl;
            gCdsCorNFE.Post;
      end;
      finally
            FreeAndNil(ACBrNFe);
      end;
end;
procedure TBrvXML.SetXMLOrigem(Value: TStrings);
begin
      gDsXmlOri.Assign(Value);
end;

procedure TBrvXML.SetXMLFinal(Value: TStrings);
begin
      gDsXmlFin.Assign(Value);
end;

procedure TBrvXML.ProcessarXml;
var lNoCaract : Integer;
begin
      gDsXmlFin.Clear;
      lNoCaract := 1;
      gCdsProdut.tag     := 0;
      gNoPagDan          := 1;
      gQtIteDan          := 11;
      gNoIteDan          := 0;

      if true then
      begin
            gTpXML := DeterminarTipoXML(gDsXmlOri.Text);

            if gTpXML = 'NFE' then
            begin
                  CriarTabelaTemporaria;
            end else
            if gTpXML = 'CTE' then
            begin
                 CriarTabelaTemporaria;
            end;
      end;

      for lNoCaract := 0 to gDsXmlFin.Count -1 do
      begin
            gDsXmlFin.Strings[lNoCaract] := FormatFloat('0000', lNoCaract + 1) + ' '+
                                            gDsXmlFin.Strings[lNoCaract];
      end;
end;

function TBrvXML.DeterminarTipoXML(pDsXml : String) : String;
begin
      Result := 'Desconhecido';

      if Pos('<CHNFE>', UpperCase(pDsXML)) > 0 then
      begin
            if Pos('<INFCANC>', UpperCase(pDsXML)) > 0 then
            begin
                  Result := 'NFC';
            end else
            begin
                  Result := 'NFE';
            end;
      end else
      begin
            if Pos('<REFCTE>', UpperCase(pDsXML)) > 0 then
            begin
                  Result := 'CTE';
            end;
      end;
end;

procedure TBrvXML.CriarTabelaTemporaria;
begin
      if gTpXML = 'NFE' then
      begin
            if gCdsCorNFE = nil then
            begin
                  raise Exception.Create('Client Data Set corpo NFE não informado');
            end;

            if gCdsProdut  = nil then
            begin
                  raise Exception.Create('Client Data Set produto NFE não informado');
            end;

            if gCdsFatura  = nil then
            begin
                  raise Exception.Create('Client Data Set fatura NFE não informado');
            end;
            CriarTabelaTemporariaXMLNFE;
//            CriarTabelaTemporariaCorpoNFe;
//            CriarTabelaTemporariaFatura;
//            CriarTabelaTemporariaProduto;
      end else
      if gTpXML = 'CTE' then
      begin
            if gCdsCorCTE = nil then
            begin
                  raise Exception.Create('Client Data Set corpo CTE não informado');
            end;

            if gCdsInfCte = nil then
            begin
                  raise Exception.Create('Client Data Set Chaves do CTE não informado');
            end;

            if gCdsPreCte = nil then
            begin
                  raise Exception.Create('Client Data Set Prestações do CTE não informado');
            end;

            if gCdsCarCte = nil then
            begin
                  raise Exception.Create('Client Data Set Cargas do CTE não informado');
            end;

            CriarTabelaTemporariaCorpoCTE;
            CriarTabelaTemporariaChavesCTE;
            CriarTabelaTemporariaPrestacoesCTE;
            CriarTabelaTemporariaCargasCTE;
            CriarTabelaTemporariaXMLCTE;
      end;
end;

procedure TBrvXML.CriarTabelaTemporariaProduto;
begin

      gCdsProdut.Close;
      gCdsProdut.FieldDefs.Clear;

      gCdsProdut.FieldDefs.Add('prod_cProd',      ftString,   20, False);
      gCdsProdut.FieldDefs.Add('prod_cEAN',       ftString,   15, False);
      gCdsProdut.FieldDefs.Add('prod_xProd',      ftString,   99, False);
      gCdsProdut.FieldDefs.Add('prod_NCM',        ftString,   20, False);
      gCdsProdut.FieldDefs.Add('prod_CFOP',       ftString,   05, False);
      gCdsProdut.FieldDefs.Add('prod_uCom',       ftString,   10, False);
      gCdsProdut.FieldDefs.Add('prod_qCom',       ftString,   20, False);
      gCdsProdut.FieldDefs.Add('prod_vUnCom',     ftString,   20, False);
      gCdsProdut.FieldDefs.Add('prod_vProd',      ftString,   20, False);
      gCdsProdut.FieldDefs.Add('prod_cEANTrib',   ftString,   20, False);
      gCdsProdut.FieldDefs.Add('prod_uTrib',      ftString,   05, False);
      gCdsProdut.FieldDefs.Add('prod_qTrib',      ftString,   20, False);
      gCdsProdut.FieldDefs.Add('prod_vUnTrib',    ftString,   20, False);
      gCdsProdut.FieldDefs.Add('prod_vFrete',     ftString,   20, False);
      gCdsProdut.FieldDefs.Add('prod_vSeg',       ftString,   20, False);
      gCdsProdut.FieldDefs.Add('prod_vDesc',      ftString,   20, False);
      gCdsProdut.FieldDefs.Add('prod_quebra',     ftInteger,   0, False);
      gCdsProdut.FieldDefs.Add('ICMS_pICMS',      ftString,   10, False);
      gCdsProdut.FieldDefs.Add('ICMS_CST',        ftString,    5, False);
      gCdsProdut.FieldDefs.Add('ICMS_orig',       ftString,    1, False);
      gCdsProdut.FieldDefs.Add('ICMS_vBC',        ftString,   20, False);
      gCdsProdut.FieldDefs.Add('ICMS_vICMS',      ftString,   20, False);
      gCdsProdut.FieldDefs.Add('ICMS_vBCSTRet',   ftString,   20, False);
      gCdsProdut.FieldDefs.Add('ICMS_vICMSSTRet', ftString,   20, False);
      gCdsProdut.FieldDefs.Add('ICMS_vBCST',      ftString,   20, False);
      gCdsProdut.FieldDefs.Add('ICMS_pICMSST',    ftString,   20, False);
      gCdsProdut.FieldDefs.Add('ICMS_vICMSST',    ftString,   20, False);
      gCdsProdut.FieldDefs.Add('ICMS_pRedBC',     ftString,   10, False);
      gCdsProdut.FieldDefs.Add('IPITrib_pIPI',    ftString,   10, False);
      gCdsProdut.FieldDefs.Add('IPITrib_vIPI',    ftString,   10, False);

      gCdsProdut.CreateDataSet;
      gCdsProdut.Open;
end;

procedure TBrvXML.CriarTabelaTemporariaFatura;
begin
      gCdsFatura.Close;
      gCdsFatura.FieldDefs.Clear;

      gCdsFatura.FieldDefs.Add('dup_nDup',    ftString,   50, False);
      gCdsFatura.FieldDefs.Add('dup_dVenc',   ftString,   50, False);
      gCdsFatura.FieldDefs.Add('dup_vDup',    ftString,   50, False);

      gCdsFatura.CreateDataSet;
      gCdsFatura.Open;
end;

procedure TBrvXML.CriarTabelaTemporariaCorpoCTE;
begin
      gCdsCorCTE.Close;
      gCdsCorCTE.FieldDefs.Clear;

      gCdsCorCTE.FieldDefs.Add('ide_cUF',            ftString,   05, False);
      gCdsCorCTE.FieldDefs.Add('ide_cCT',            ftString,   20, False);
      gCdsCorCTE.FieldDefs.Add('ide_CFOP',           ftString,   05, False);
      gCdsCorCTE.FieldDefs.Add('ide_natOp',          ftString,   99, False);
      gCdsCorCTE.FieldDefs.Add('ide_forPag',         ftString,   05, False);
      gCdsCorCTE.FieldDefs.Add('ide_mod',            ftString,   05, False);
      gCdsCorCTE.FieldDefs.Add('ide_serie',          ftString,   20, False);
      gCdsCorCTE.FieldDefs.Add('ide_nCT',            ftString,   20, False);
      gCdsCorCTE.FieldDefs.Add('ide_dhEmi',          ftString,   20, False);
      gCdsCorCTE.FieldDefs.Add('ide_tpImp',          ftString,   05, False);
      gCdsCorCTE.FieldDefs.Add('ide_tpEmis',         ftString,   05, False);
      gCdsCorCTE.FieldDefs.Add('ide_cDV',            ftString,   05, False);
      gCdsCorCTE.FieldDefs.Add('ide_tpAmb',          ftString,   05, False);
      gCdsCorCTE.FieldDefs.Add('ide_tpCTE',          ftString,   05, False);
      gCdsCorCTE.FieldDefs.Add('ide_procEmi',        ftString,   05, False);
      gCdsCorCTE.FieldDefs.Add('ide_refCTE',         ftString,   50, False);
      gCdsCorCTE.FieldDefs.Add('ide_cMunEmi',        ftString,   20, False);
      gCdsCorCTE.FieldDefs.Add('ide_xMunEmi',        ftString,   99, False);
      gCdsCorCTE.FieldDefs.Add('ide_UFEmi',          ftString,   02, False);
      gCdsCorCTE.FieldDefs.Add('ide_modal',          ftString,   05, False);
      gCdsCorCTE.FieldDefs.Add('ide_TpServ',         ftString,   05, False);
      gCdsCorCTE.FieldDefs.Add('ide_cMunIni',        ftString,   20, False);
      gCdsCorCTE.FieldDefs.Add('ide_xMunIni',        ftString,   99, False);
      gCdsCorCTE.FieldDefs.Add('ide_UFIni',          ftString,   02, False);
      gCdsCorCTE.FieldDefs.Add('ide_cMunFim',        ftString,   20, False);
      gCdsCorCTE.FieldDefs.Add('ide_xMunFim',        ftString,   99, False);
      gCdsCorCTE.FieldDefs.Add('ide_UFFim',          ftString,   02, False);
      gCdsCorCTE.FieldDefs.Add('ide_Retira',         ftString,   05, False);

      gCdsCorCTE.FieldDefs.Add('toma03_toma',        ftString,   05, False);

      gCdsCorCTE.FieldDefs.Add('toma4_toma',         ftString,   05, False);
      gCdsCorCTE.FieldDefs.Add('toma4_CNPJ',         ftString,   30, False);
      gCdsCorCTE.FieldDefs.Add('toma4_IE',           ftString,   30, False);
      gCdsCorCTE.FieldDefs.Add('toma4_xNome',        ftString,   99, False);
      gCdsCorCTE.FieldDefs.Add('toma4_xFant',        ftString,   99, False);
      gCdsCorCTE.FieldDefs.Add('toma4_fone',         ftString,   20, False);

      gCdsCorCTE.FieldDefs.Add('enderToma_xLgr',     ftString,   99, False);
      gCdsCorCTE.FieldDefs.Add('enderToma_nro',      ftString,   20, False);
      gCdsCorCTE.FieldDefs.Add('enderToma_xBairro',  ftString,   99, False);
      gCdsCorCTE.FieldDefs.Add('enderToma_cMun',     ftString,   20, False);
      gCdsCorCTE.FieldDefs.Add('enderToma_xMun',     ftString,   99, False);
      gCdsCorCTE.FieldDefs.Add('enderToma_CEP',      ftString,   20, False);
      gCdsCorCTE.FieldDefs.Add('enderToma_UF',       ftString,   02, False);
      gCdsCorCTE.FieldDefs.Add('enderToma_cPais',    ftString,   20, False);
      gCdsCorCTE.FieldDefs.Add('enderToma_xPais',    ftString,   99, False);

      gCdsCorCTE.FieldDefs.Add('compl_xCaracAd',     ftString,   99, False);
      gCdsCorCTE.FieldDefs.Add('compl_xCaracSer',    ftString,   99, False);
      gCdsCorCTE.FieldDefs.Add('compl_xEmi',         ftString,   99, False);
      gCdsCorCTE.FieldDefs.Add('compl_xObs',         ftString,   99, False);

      gCdsCorCTE.FieldDefs.Add('fluxo_xOrig',        ftString,   99, False);
      gCdsCorCTE.FieldDefs.Add('fluxo_xDest',        ftString,   99, False);
      gCdsCorCTE.FieldDefs.Add('fluxo_xRota',        ftString,   99, False);

      gCdsCorCTE.FieldDefs.Add('comData_tpPer',      ftString,   05, False);
      gCdsCorCTE.FieldDefs.Add('comData_dProg',      ftString,   10, False);
      gCdsCorCTE.FieldDefs.Add('semHora_tpHor',      ftString,   05, False);

      gCdsCorCTE.FieldDefs.Add('emit_CNPJ',          ftString,   30, False);
      gCdsCorCTE.FieldDefs.Add('emit_IE',            ftString,   30, False);
      gCdsCorCTE.FieldDefs.Add('emit_xNome',         ftString,   99, False);
      gCdsCorCTE.FieldDefs.Add('emit_xFant',         ftString,   99, False);

      gCdsCorCTE.FieldDefs.Add('enderEmit_xLgr',     ftString,   99, False);
      gCdsCorCTE.FieldDefs.Add('enderEmit_nro',      ftString,   20, False);
      gCdsCorCTE.FieldDefs.Add('enderEmit_xCpl',     ftString,   99, False);
      gCdsCorCTE.FieldDefs.Add('enderEmit_xBairro',  ftString,   99, False);
      gCdsCorCTE.FieldDefs.Add('enderEmit_cMun',     ftString,   20, False);
      gCdsCorCTE.FieldDefs.Add('enderEmit_xMun',     ftString,   99, False);
      gCdsCorCTE.FieldDefs.Add('enderEmit_CEP',      ftString,   20, False);
      gCdsCorCTE.FieldDefs.Add('enderEmit_UF',       ftString,   05, False);
      gCdsCorCTE.FieldDefs.Add('enderEmit_cPais',    ftString,   20, False);
      gCdsCorCTE.FieldDefs.Add('enderEmit_xPais',    ftString,   99, False);
      gCdsCorCTE.FieldDefs.Add('enderEmit_fone',     ftString,   20, False);

      gCdsCorCTE.FieldDefs.Add('rem_CNPJ',           ftString,   30, False);
      gCdsCorCTE.FieldDefs.Add('rem_IE',             ftString,   30, False);
      gCdsCorCTE.FieldDefs.Add('rem_xNome',          ftString,   99, False);
      gCdsCorCTE.FieldDefs.Add('rem_xFant',          ftString,   99, False);
      gCdsCorCTE.FieldDefs.Add('rem_fone',           ftString,   20, False);

      gCdsCorCTE.FieldDefs.Add('enderReme_xLgr',     ftString,   99, False);
      gCdsCorCTE.FieldDefs.Add('enderReme_nro',      ftString,   20, False);
      gCdsCorCTE.FieldDefs.Add('enderReme_xCpl',     ftString,   99, False);
      gCdsCorCTE.FieldDefs.Add('enderReme_xBairro',  ftString,   99, False);
      gCdsCorCTE.FieldDefs.Add('enderReme_cMun',     ftString,   20, False);
      gCdsCorCTE.FieldDefs.Add('enderReme_xMun',     ftString,   99, False);
      gCdsCorCTE.FieldDefs.Add('enderReme_CEP',      ftString,   20, False);
      gCdsCorCTE.FieldDefs.Add('enderReme_UF',       ftString,   05, False);
      gCdsCorCTE.FieldDefs.Add('enderReme_cPais',    ftString,   20, False);
      gCdsCorCTE.FieldDefs.Add('enderReme_xPais',    ftString,   99, False);

      gCdsCorCTE.FieldDefs.Add('exped_CNPJ',         ftString,   30, False);
      gCdsCorCTE.FieldDefs.Add('exped_IE',           ftString,   30, False);
      gCdsCorCTE.FieldDefs.Add('exped_xNome',        ftString,   99, False);
      gCdsCorCTE.FieldDefs.Add('exped_xFant',        ftString,   99, False);
      gCdsCorCTE.FieldDefs.Add('exped_fone',         ftString,   20, False);

      gCdsCorCTE.FieldDefs.Add('enderExped_xLgr',    ftString,   99, False);
      gCdsCorCTE.FieldDefs.Add('enderExped_nro',     ftString,   20, False);
      gCdsCorCTE.FieldDefs.Add('enderExped_xCpl',    ftString,   99, False);
      gCdsCorCTE.FieldDefs.Add('enderExped_xBairro', ftString,   99, False);
      gCdsCorCTE.FieldDefs.Add('enderExped_cMun',    ftString,   20, False);
      gCdsCorCTE.FieldDefs.Add('enderExped_xMun',    ftString,   99, False);
      gCdsCorCTE.FieldDefs.Add('enderExped_CEP',     ftString,   20, False);
      gCdsCorCTE.FieldDefs.Add('enderExped_UF',      ftString,   05, False);
      gCdsCorCTE.FieldDefs.Add('enderExped_cPais',   ftString,   20, False);
      gCdsCorCTE.FieldDefs.Add('enderExped_xPais',   ftString,   99, False);

      gCdsCorCTE.FieldDefs.Add('receb_CNPJ',         ftString,   30, False);
      gCdsCorCTE.FieldDefs.Add('receb_IE',           ftString,   30, False);
      gCdsCorCTE.FieldDefs.Add('receb_xNome',        ftString,   99, False);
      gCdsCorCTE.FieldDefs.Add('receb_xFant',        ftString,   99, False);
      gCdsCorCTE.FieldDefs.Add('receb_fone',         ftString,   20, False);

      gCdsCorCTE.FieldDefs.Add('enderReceb_xLgr',    ftString,   99, False);
      gCdsCorCTE.FieldDefs.Add('enderReceb_nro',     ftString,   20, False);
      gCdsCorCTE.FieldDefs.Add('enderReceb_xCpl',    ftString,   99, False);
      gCdsCorCTE.FieldDefs.Add('enderReceb_xBairro', ftString,   99, False);
      gCdsCorCTE.FieldDefs.Add('enderReceb_cMun',    ftString,   20, False);
      gCdsCorCTE.FieldDefs.Add('enderReceb_xMun',    ftString,   99, False);
      gCdsCorCTE.FieldDefs.Add('enderReceb_CEP',     ftString,   20, False);
      gCdsCorCTE.FieldDefs.Add('enderReceb_UF',      ftString,   05, False);
      gCdsCorCTE.FieldDefs.Add('enderReceb_cPais',   ftString,   20, False);
      gCdsCorCTE.FieldDefs.Add('enderReceb_xPais',   ftString,   99, False);

      gCdsCorCTE.FieldDefs.Add('dest_CNPJ',          ftString,   30, False);
      gCdsCorCTE.FieldDefs.Add('dest_IE',            ftString,   30, False);
      gCdsCorCTE.FieldDefs.Add('dest_xNome',         ftString,   99, False);
      gCdsCorCTE.FieldDefs.Add('dest_xFant',         ftString,   99, False);
      gCdsCorCTE.FieldDefs.Add('dest_fone',          ftString,   20, False);

      gCdsCorCTE.FieldDefs.Add('enderDest_xLgr',     ftString,   99, False);
      gCdsCorCTE.FieldDefs.Add('enderDest_nro',      ftString,   20, False);
      gCdsCorCTE.FieldDefs.Add('enderDest_xCpl',     ftString,   99, False);
      gCdsCorCTE.FieldDefs.Add('enderDest_xBairro',  ftString,   99, False);
      gCdsCorCTE.FieldDefs.Add('enderDest_cMun',     ftString,   20, False);
      gCdsCorCTE.FieldDefs.Add('enderDest_xMun',     ftString,   99, False);
      gCdsCorCTE.FieldDefs.Add('enderDest_CEP',      ftString,   20, False);
      gCdsCorCTE.FieldDefs.Add('enderDest_UF',       ftString,   05, False);
      gCdsCorCTE.FieldDefs.Add('enderDest_cPais',    ftString,   20, False);
      gCdsCorCTE.FieldDefs.Add('enderDest_xPais',    ftString,   99, False);

      gCdsCorCTE.FieldDefs.Add('vPrest_vTPrest',     ftString,   20, False);
      gCdsCorCTE.FieldDefs.Add('vPrest_vRec',        ftString,   20, False);

      gCdsCorCTE.FieldDefs.Add('infCarga_vMerc',     ftString,   20, False);
      gCdsCorCTE.FieldDefs.Add('infCarga_ProPred',   ftString,   99, False);

      gCdsCorCTE.FieldDefs.Add('ICMS_CST',           ftString,    5, False);
      gCdsCorCTE.FieldDefs.Add('ICMS_vBC',           ftString,   20, False);
      gCdsCorCTE.FieldDefs.Add('ICMS_pICMS',         ftString,    5, False);
      gCdsCorCTE.FieldDefs.Add('ICMS_vICMS',         ftString,   20, False);

      gCdsCorCTE.FieldDefs.Add('seg_respSeg',        ftString,    5, False);
      gCdsCorCTE.FieldDefs.Add('seg_xSeg',           ftString,   99, False);
      gCdsCorCTE.FieldDefs.Add('seg_nApol',          ftString,   50, False);
      gCdsCorCTE.FieldDefs.Add('seg_vMerc',          ftString,   20, False);

      gCdsCorCTE.FieldDefs.Add('rodo_RNTRC',         ftString,   30, False);
      gCdsCorCTE.FieldDefs.Add('rodo_dPrev',         ftString,   10, False);
      gCdsCorCTE.FieldDefs.Add('rodo_lota',          ftString,    5, False);

      gCdsCorCTE.CreateDataSet;
      gCdsCorCTE.Open;
end;

procedure TBrvXML.CriarTabelaTemporariaPrestacoesCTE;
begin
      gCdsPreCte.Close;
      gCdsPreCte.FieldDefs.Clear;

      gCdsPreCte.FieldDefs.Add('Comp_xNome',         ftString,   99, False);
      gCdsPreCte.FieldDefs.Add('Comp_vComp',         ftString,   20, False);
      gCdsPreCte.CreateDataSet;
      gCdsPreCte.Open;
end;

procedure TBrvXML.CriarTabelaTemporariaCargasCTE;
begin
      gCdsCarCte.Close;
      gCdsCarCte.FieldDefs.Clear;

      gCdsCarCte.FieldDefs.Add('infQ_cUnid',         ftString,   05, False);
      gCdsCarCte.FieldDefs.Add('infQ_tpMed',         ftString,   50, False);
      gCdsCarCte.FieldDefs.Add('infQ_qCarga',        ftString,   20, False);
      gCdsCarCte.CreateDataSet;
      gCdsCarCte.Open;
end;

procedure TBrvXML.CriarTabelaTemporariaChavesCTE;
begin
      gCdsInfCte.Close;
      gCdsInfCte.FieldDefs.Clear;

      gCdsInfCte.FieldDefs.Add('infNFe_chave',       ftString,   50, False);
      gCdsInfCte.FieldDefs.Add('infNF_serie',        ftString,   20, False);
      gCdsInfCte.FieldDefs.Add('infNF_nDoc',         ftString,   20, False);
      gCdsInfCte.FieldDefs.Add('infNF_dEmi',         ftString,   10, False);
      gCdsInfCte.FieldDefs.Add('infNF_vBC',          ftString,   20, False);
      gCdsInfCte.FieldDefs.Add('infNF_vICMS',        ftString,   20, False);
      gCdsInfCte.FieldDefs.Add('infNF_vBCST',        ftString,   20, False);
      gCdsInfCte.FieldDefs.Add('infNF_vST',          ftString,   20, False);
      gCdsInfCte.FieldDefs.Add('infNF_vProd',        ftString,   20, False);
      gCdsInfCte.FieldDefs.Add('infNF_vNF',          ftString,   20, False);
      gCdsInfCte.FieldDefs.Add('infNF_nCFOP',        ftString,   05, False);
      gCdsInfCte.CreateDataSet;
      gCdsInfCte.Open;
end;


procedure TBrvXML.CriarTabelaTemporariaCorpoNFe;
begin
      gCdsCorNFE.Close;
      gCdsCorNFE.FieldDefs.Clear;

      gCdsCorNFE.FieldDefs.Add('xMotivo',            ftString,   99, False);
      gCdsCorNFE.FieldDefs.Add('chNFe',              ftString,   50, False);
      gCdsCorNFE.FieldDefs.Add('dhRecbto',           ftString,   50, False);
      gCdsCorNFE.FieldDefs.Add('nProt',              ftString,   50, False);
      gCdsCorNFE.FieldDefs.Add('ide_mod',            ftString,   05, False);
      gCdsCorNFE.FieldDefs.Add('ide_natOp',          ftString,   99, False);
      gCdsCorNFE.FieldDefs.Add('ide_nNF',            ftString,   20, False);
      gCdsCorNFE.FieldDefs.Add('ide_serie',          ftString,   20, False);
      gCdsCorNFE.FieldDefs.Add('ide_tpImp',          ftString,   20, False);
      gCdsCorNFE.FieldDefs.Add('ide_tpEmis',         ftString,   20, False);
      gCdsCorNFE.FieldDefs.Add('ide_cDV',            ftString,   20, False);
      gCdsCorNFE.FieldDefs.Add('ide_tpAmb',          ftString,   20, False);
      gCdsCorNFE.FieldDefs.Add('ide_dEmi',           ftString,   20, False);
      gCdsCorNFE.FieldDefs.Add('ide_dSaiEnt',        ftString,   20, False);
      gCdsCorNFE.FieldDefs.Add('ide_hSaiEnt',        ftString,   20, False);
      gCdsCorNFE.FieldDefs.Add('emit_xNome',         ftString,   99, False);
      gCdsCorNFE.FieldDefs.Add('emit_CNPJ',          ftString,   30, False);
      gCdsCorNFE.FieldDefs.Add('emit_IE',            ftString,   30, False);
      gCdsCorNFE.FieldDefs.Add('emit_CRT',           ftString,   05, False);


      gCdsCorNFE.FieldDefs.Add('enderEmit_xLgr',     ftString,   99, False);
      gCdsCorNFE.FieldDefs.Add('enderEmit_nro',      ftString,   20, False);
      gCdsCorNFE.FieldDefs.Add('enderEmit_xBairro',  ftString,   99, False);
      gCdsCorNFE.FieldDefs.Add('enderEmit_xMun',     ftString,   99, False);
      gCdsCorNFE.FieldDefs.Add('enderEmit_CEP',      ftString,   20, False);
      gCdsCorNFE.FieldDefs.Add('enderEmit_fone',     ftString,   20, False);
      gCdsCorNFE.FieldDefs.Add('enderEmit_UF',       ftString,   05, False);

      gCdsCorNFE.FieldDefs.Add('dest_xNome',         ftString,   99, False);
      gCdsCorNFE.FieldDefs.Add('dest_CNPJ',          ftString,   30, False);
      gCdsCorNFE.FieldDefs.Add('dest_CPF',           ftString,   30, False);
      gCdsCorNFE.FieldDefs.Add('dest_IE',            ftString,   30, False);
      gCdsCorNFE.FieldDefs.Add('dest_email',         ftString,   99, False);

      gCdsCorNFE.FieldDefs.Add('enderDest_xLgr',     ftString,   99, False);
      gCdsCorNFE.FieldDefs.Add('enderDest_nro',      ftString,   20, False);
      gCdsCorNFE.FieldDefs.Add('enderDest_xBairro',  ftString,   99, False);
      gCdsCorNFE.FieldDefs.Add('enderDest_xMun',     ftString,   99, False);
      gCdsCorNFE.FieldDefs.Add('enderDest_CEP',      ftString,   20, False);
      gCdsCorNFE.FieldDefs.Add('enderDest_fone',     ftString,   30, False);
      gCdsCorNFE.FieldDefs.Add('enderDest_UF',       ftString,   05, False);

      gCdsCorNFE.FieldDefs.Add('ICMSTot_vBc',        ftString,   20, False);
      gCdsCorNFE.FieldDefs.Add('ICMSTot_vICMS',      ftString,   20, False);
      gCdsCorNFE.FieldDefs.Add('ICMSTot_vBCST',      ftString,   20, False);
      gCdsCorNFE.FieldDefs.Add('ICMSTot_vST',        ftString,   20, False);
      gCdsCorNFE.FieldDefs.Add('ICMSTot_vProd',      ftString,   20, False);
      gCdsCorNFE.FieldDefs.Add('ICMSTot_vFrete',     ftString,   20, False);
      gCdsCorNFE.FieldDefs.Add('ICMSTot_vSeg',       ftString,   20, False);
      gCdsCorNFE.FieldDefs.Add('ICMSTot_vOutro',     ftString,   20, False);
      gCdsCorNFE.FieldDefs.Add('ICMSTot_vIPI',       ftString,   20, False);
      gCdsCorNFE.FieldDefs.Add('ICMSTot_vDesc',      ftString,   20, False);
      gCdsCorNFE.FieldDefs.Add('ICMSTot_vNF',        ftString,   20, False);


      gCdsCorNFE.FieldDefs.Add('transp_modFrete',    ftString,   10, False);
      gCdsCorNFE.FieldDefs.Add('transporta_xNome',   ftString,   99, False);
      gCdsCorNFE.FieldDefs.Add('transporta_CNPJ',    ftString,   30, False);
      gCdsCorNFE.FieldDefs.Add('transporta_IE',      ftString,   30, False);
      gCdsCorNFE.FieldDefs.Add('transporta_xEnder',  ftString,   99, False);
      gCdsCorNFE.FieldDefs.Add('transporta_xMun',    ftString,   99, False);
      gCdsCorNFE.FieldDefs.Add('transporta_UF',      ftString,   05, False);
      gCdsCorNFE.FieldDefs.Add('veicTransp_placa',   ftString,   20, False);
      gCdsCorNFE.FieldDefs.Add('veicTransp_UF',      ftString,   05, False);
      gCdsCorNFE.FieldDefs.Add('vol_qVol',           ftString,   20, False);
      gCdsCorNFE.FieldDefs.Add('vol_esp',            ftString,   50, False);
      gCdsCorNFE.FieldDefs.Add('vol_marca',          ftString,   50, False);
      gCdsCorNFE.FieldDefs.Add('vol_nVol',           ftString,   50, False);
      gCdsCorNFE.FieldDefs.Add('vol_pesoL',          ftString,   20, False);
      gCdsCorNFE.FieldDefs.Add('vol_pesoB',          ftString,   20, False);
      gCdsCorNFE.FieldDefs.Add('infAdic_infCpl',     ftMemo,      0, False);
      gCdsCorNFE.FieldDefs.Add('xml',     ftMemo,      0, False);

      gCdsCorNFE.CreateDataSet;
      gCdsCorNFE.Open;
end;

end.
