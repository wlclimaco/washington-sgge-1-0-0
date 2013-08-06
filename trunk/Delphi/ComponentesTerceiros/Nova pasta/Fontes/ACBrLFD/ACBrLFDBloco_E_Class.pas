{******************************************************************************}
{ Projeto: Componentes ACBr                                                    }
{  Biblioteca multiplataforma de componentes Delphi para intera��o com equipa- }
{ mentos de Automa��o Comercial utilizados no Brasil                           }
{                                                                              }
{ Direitos Autorais Reservados (c) 2009   Juliana Tamizou                      }
{                                                                              }
{ Colaboradores nesse arquivo: Isaque Pinheiro                                 }
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
|* 26/01/2013: Nilson Sergio
|*  - Cria��o e distribui��o da Primeira Versao
*******************************************************************************}

unit ACBrLFDBloco_E_Class;

interface

uses SysUtils, Classes, DateUtils, ACBrLFD3505, ACBrLFDBlocos, ACBrLFDBloco_E,
     ACBrTXTClass;


type
  { TBloco_E }

  TBloco_E = class(TACBrLFD3505)
  private
    FRegistroE001: TRegistroE001;
    FRegistroE990: TRegistroE990;

    FRegistroE020Count: Integer;
    FRegistroE025Count: Integer;
    FRegistroE310Count: Integer;
    FRegistroE365Count: Integer;

    procedure WriteRegistroE020(RegE001: TRegistroE001);
    procedure WriteRegistroE025(RegE020: TRegistroE020);
    procedure WriteRegistroE300(RegE001: TRegistroE001);
    procedure WriteRegistroE310(RegE300: TRegistroE300);
    procedure WriteRegistroE360(RegE300: TRegistroE300);
    procedure WriteRegistroE365(RegE360: TRegistroE360);
    procedure WriteRegistroE500(RegE001: TRegistroE001);
    procedure WriteRegistroE530(RegE500: TRegistroE500);

    procedure CriaRegistros;
    procedure LiberaRegistros;
  public
    constructor Create;           /// Create
    destructor Destroy; override; /// Destroy
    procedure LimpaRegistros;

    {function RegistroE001New: TRegistroE001;
    function RegistroE005New: TRegistroE005;}
    function RegistroE020New: TRegistroE020;
    function RegistroE025New: TRegistroE025;
    function RegistroE300New: TRegistroE300;
    function RegistroE310New: TRegistroE310;
    function RegistroE360New: TRegistroE360;
    function RegistroE365New: TRegistroE365;
    function RegistroE500New: TRegistroE500;
    function RegistroE530New: TRegistroE530;

    procedure WriteRegistroE001;
    procedure WriteRegistroE990;

    property RegistroE001: TRegistroE001 read FRegistroE001 write FRegistroE001;
    property RegistroE990: TRegistroE990 read FRegistroE990 write FRegistroE990;

    property RegistroE020Count: Integer read FRegistroE020Count write FRegistroE020Count;
    property RegistroE025Count: Integer read FRegistroE025Count write FRegistroE025Count;
    property RegistroE310Count: Integer read FRegistroE310Count write FRegistroE310Count;
    property RegistroE365Count: Integer read FRegistroE365Count write FRegistroE365Count;
  end;

implementation

uses ACBrLFDUtils, StrUtils;

{ TBloco_E }

constructor TBloco_E.Create ;
begin
  inherited ;
  CriaRegistros;
end;

destructor TBloco_E.Destroy;
begin
  LiberaRegistros;
  inherited;
end;

procedure TBloco_E.WriteRegistroE020(RegE001: TRegistroE001);
var
  intFor: Integer;
  RegE020: TRegistroE020;
  wIndCompl: String;
begin
   for intFor := 0 to RegE001.RegistroE020.Count - 1 do
   begin
      RegE020 := RegE001.RegistroE020.Items[intFor];
      with RegE020 do
      begin
         case IND_COMPL of
           cSemValor         : wIndCompl:= '00';
           cDifICMSST        : wIndCompl:= '01';
           cDifAliqAtivoFixo : wIndCompl:= '02';
           cAliqUsoConsumo   : wIndCompl:= '03';
           cAliqOutrasSitu   : wIndCompl:= '04';
           cAntecTributaria  : wIndCompl:= '05';
           cProgBenFical     : wIndCompl:= '06';
           cOutrasSituacoes  : wIndCompl:= '99';
         end;

         Add( LFill('E020') +
              LFill(Integer(IND_OPER),1) +
              LFill(Integer(IND_EMIT),1) +
              LFill(COD_PART) +
              LFill(COD_MOD)  +
              LFill(Integer(COD_SIT),2) +
              LFill(SERIE) +
              LFill(NUMDOCTO,0) +
              LFill(DT_EMISSAO) +
              LFill(NUM_LCTO) +
              LFill(DT_ES) +
              LFill(VALOR_DOC,2) +
              LFill(VALOR_BC_ICMS,2) +
              LFill(VALOR_ICMS,2) +
              LFill(VALOR_ST,2) +
              LFill(VL_ICMS_COMPL,2) +
              LFill(wIndCompl) +
              LFill(VALOR_IN_ICMS,2) +
              LFill(VALOR_O_ICMS,2) +
              LFill(VALOR_BC_IPI,2) +
              LFill(VALOR_IPI,2) +
              LFill(VALOR_IN_IPI,2) +
              LFill(VALOR_O_IPI,2) +
              LFill(COD_INF_OBS));
      end;

      WriteRegistroE025(RegE020);

      RegistroE990.QTD_LIN_E := RegistroE990.QTD_LIN_E + 1;
   end;

   FRegistroE020Count := FRegistroE020Count + RegE001.RegistroE020.Count;
end;

procedure TBloco_E.WriteRegistroE025(RegE020: TRegistroE020);
var
  intFor: Integer;
begin
   if Assigned(RegE020.RegistroE025) then
   begin
      for intFor := 0 to RegE020.RegistroE025.Count - 1 do
      begin
         with RegE020.RegistroE025.Items[intFor] do
         begin
            Add( LFill('E025') +
                 LFill(CFOP) +
                 LFill(VALOR_CONT,2) +
                 LFill(VALOR_BC_ICMS,2) +
                 LFill(ALIQ_ICMS,2) +
                 LFill(VALOR_ICMS,2) +
                 LFill(VALOR_ST,2) +
                 LFill(VL_ICMS_COMPL,2) +
                 LFill(VALOR_IN_ICMS,2) +
                 LFill(VALOR_O_ICMS,2) +
                 LFill(VALOR_BC_IPI,2) +
                 LFill(VALOR_IPI,2) +
                 LFill(VALOR_IN_IPI,2) +
                 LFill(VALOR_O_IPI,2));
         end;
         RegistroE990.QTD_LIN_E := RegistroE990.QTD_LIN_E + 1;
      end;
      FRegistroE025Count := FRegistroE025Count + RegE020.RegistroE025.Count;
  end;
end;

procedure TBloco_E.WriteRegistroE300(RegE001: TRegistroE001);
begin
   if Assigned(RegE001.RegistroE300) then
   begin
     with RegE001.RegistroE300 do
     begin
        Add( LFill('E300')     +
             LFill(DT_INI)     +
             LFill(DT_FIM));


        WriteRegistroE310( RegE001.RegistroE300 );
        WriteRegistroE360( RegE001.RegistroE300 );

        RegistroE990.QTD_LIN_E := RegistroE990.QTD_LIN_E + 1;
     end;
  end;
end;

procedure TBloco_E.WriteRegistroE310(RegE300: TRegistroE300);
var
  intFor: Integer;
begin
   if Assigned(RegE300.RegistroE310) then
   begin
      for intFor := 0 to RegE300.RegistroE310.Count - 1 do
      begin
         with RegE300.RegistroE310.Items[intFor] do
         begin
            Add( LFill('E310') +
                 LFill(CFOP) +
                 LFill(VALOR_CONT,2) +
                 LFill(VALOR_BC_ICMS,2) +
                 LFill(VALOR_ICMS,2) +
                 LFill(VALOR_ST,2) +
                 LFill(VL_ICMS_COMPL,2) +
                 LFill(VALOR_IN_ICMS,2) +
                 LFill(VALOR_O_ICMS,2));
         end;
         RegistroE990.QTD_LIN_E := RegistroE990.QTD_LIN_E + 1;
      end;
      FRegistroE310Count := FRegistroE310Count + RegE300.RegistroE310.Count;
  end;
end;

procedure TBloco_E.WriteRegistroE360(RegE300: TRegistroE300);
var
  wVLSubTotalCred: Extended;
  wVLTotalDebito: Extended;
  wVLTotalCreditos: Extended;
begin
  if Assigned(RegE300.RegistroE360) then
  begin
     with RegE300.RegistroE360 do
     begin
       wVLSubTotalCred  := (VL_CRED_ENT + VL_OCRED + VL_EST_DEB);
       wVLTotalDebito   := (VL_DEB_SAIDA + VL_ODEB + VL_EST_CRED);
       wVLTotalCreditos := (wVLSubTotalCred + VL_SALDO_CREDANT);

       Add( LFill('E360')       +
            IfThen(VL_DEB_SAIDA > 0,
                   LFill(VL_DEB_SAIDA,2),
                   LFill(VL_DEB_SAIDA,0,0,false,'0', '#0.##')) +
            LFill(VL_ODEB,0,0,false,'0','#0.##')      +
            IfThen(VL_EST_CRED > 0,
                   LFill(VL_EST_CRED,2),
                   LFill(VL_EST_CRED,0,0,false,'0', '#0.##')) +
            IfThen(wVLTotalDebito > 0,
                   LFill(wVLTotalDebito,2),
                   LFill(wVLTotalDebito,0,0,false,'0', '#0.##')) +
            LFill(VL_CRED_ENT,0,0,false,'0','#0.##')  +
            LFill(VL_OCRED,0,0,false,'0','#0.##')     +
            LFill(VL_EST_DEB,0,0,false,'0','#0.##' )  +
            LFill(wVLSubTotalCred,0,0,false,'0','#0.##')  +
            IfThen(VL_SALDO_CREDANT > 0,
                   LFill(VL_SALDO_CREDANT,2),
                   LFill(VL_SALDO_CREDANT,0,0,false,'0', '#0.##')) +
            LFill(wVLTotalCreditos,0,0,false,'0','#0.##') +
            IfThen(wVLTotalCreditos > 0,
                   LFill(wVLTotalCreditos,2),
                   LFill(wVLTotalCreditos,0,0,false,'0', '#0.##')) +
            IfThen(wVLTotalDebito > 0,
                   LFill(wVLTotalDebito,2),
                   LFill(wVLTotalDebito,0,0,false,'0', '#0.##')) +
            LFill(abs(VL_DEDUCOES),0,0,false,'0','#0.##')     +
            IfThen((abs((wVLTotalCreditos - wVLTotalDebito) - VL_DEDUCOES)) > 0,
                   LFill(abs((wVLTotalCreditos - wVLTotalDebito) - VL_DEDUCOES),2),
                   LFill(abs((wVLTotalCreditos - wVLTotalDebito) - VL_DEDUCOES),0,0,false,'0', '#0.##')) +
            LFill(VL_ICMS_ST_ENT,0,0,false,'0','#0.##')  +
            LFill(VL_ICMS_ST_SAI,0,0,false,'0','#0.##')  +
            LFill(VL_DIF_ICMS,0,0,false,'0','#0.##' )    +
            LFill(VL_ICMS_IMP,0,0,false,'0','#0.##')     +
            LFill(VL_ICMS_OO,0,0,false,'0','#0.##')      +
            LFill(VL_ICMS_OREC,0,0,false,'0','#0.##' )   +
            LFill(VL_ICMS_ST_FORAUF,0,0,false,'0','#0.##' ));


       // Registros Filhos
       WriteRegistroE365(RegE300.RegistroE360);

       RegistroE990.QTD_LIN_E := RegistroE990.QTD_LIN_E + 1;
     end;
  end;
end;

procedure TBloco_E.WriteRegistroE365(RegE360: TRegistroE360);
var
  intFor: Integer;
begin
   if Assigned( RegE360.RegistroE365 ) then
   begin
      for intFor := 0 to RegE360.RegistroE365.Count - 1 do
      begin
         with RegE360.RegistroE365.Items[intFor] do
         begin
            Add( LFill('E365')           +
                 LFill( VL_ICMS_ST_SAI ) +
                 LFill( UF ));
         end;

         RegistroE990.QTD_LIN_E := RegistroE990.QTD_LIN_E + 1;
      end;

     // Variav�l para armazenar a quantidade de registro do tipo.
     FRegistroE365Count := FRegistroE365Count + RegE360.RegistroE365.Count;
  end;
end;

procedure TBloco_E.WriteRegistroE500(RegE001: TRegistroE001);
begin
   if Assigned(RegE001.RegistroE500) then
   begin
     with RegE001.RegistroE500 do
     begin
        Add( LFill('E500')     +
             LFill(DT_INI)     +
             LFill(DT_FIM));

        WriteRegistroE530( RegE001.RegistroE500 );

        RegistroE990.QTD_LIN_E := RegistroE990.QTD_LIN_E + 1;
     end;
   end;
end;

procedure TBloco_E.WriteRegistroE530(RegE500: TRegistroE500);
begin
  if Assigned(RegE500.RegistroE530) then
  begin
     with RegE500.RegistroE530 do
     begin
        Add( LFill('E530')       +
             LFill(VL_SD_ANT_IPI,0,0,false,'0','#0.##') +
             LFill(VL_DEB_IPI,0,0,false,'0','#0.##')      +
             LFill(VL_CRED_IPI,0,0,false,'0','#0.##')  +
             LFill(VL_OD_IPI,0,0,false,'0','#0.##')     +
             LFill(VL_OC_IPI,0,0,false,'0','#0.##' )  +
             LFill(VL_SC_IPI,0,0,false,'0','#0.##') +
             LFill(VL_SD_IPI,0,0,false,'0','#0.##'));



       RegistroE990.QTD_LIN_E := RegistroE990.QTD_LIN_E + 1;
     end;
  end;
end;

procedure TBloco_E.CriaRegistros;
begin
  FRegistroE001 := TRegistroE001.Create;
  FRegistroE990 := TRegistroE990.Create;

  FRegistroE020Count := 0;
  FRegistroE025Count := 0;
  FRegistroE310Count := 0;
  FRegistroE365Count := 0;
  FRegistroE990.QTD_LIN_E := 0;
end;

procedure TBloco_E.LiberaRegistros;
begin
  FRegistroE001.Free;
  FRegistroE990.Free;
end;

procedure TBloco_E.LimpaRegistros;
begin
  /// Limpa os Registros
  LiberaRegistros;
  Conteudo.Clear;

  /// Recriar os Registros Limpos
  CriaRegistros;
end;

function TBloco_E.RegistroE020New: TRegistroE020;
begin
   Result := FRegistroE001.RegistroE020.New(FRegistroE001);
end;

function TBloco_E.RegistroE025New: TRegistroE025;
var
  E020: TRegistroE020;
begin
  with FRegistroE001.RegistroE020 do
    E020 := Items[ AchaUltimoPai('E020', 'E025') ];
  Result := E020.RegistroE025.New(E020);

end;

{function TBloco_E.RegistroE001New: TRegistroE001;
begin
  Result := FRegistroE001;
end;

function TBloco_E.RegistroE005New: TRegistroE005;
begin

end; }

function TBloco_E.RegistroE300New: TRegistroE300;
begin
  Result := FRegistroE001.RegistroE300;
end;

function TBloco_E.RegistroE310New: TRegistroE310;
begin

end;

function TBloco_E.RegistroE360New: TRegistroE360;
begin

end;

function TBloco_E.RegistroE365New: TRegistroE365;
begin
   Result := FRegistroE001.RegistroE300.RegistroE360.RegistroE365.New(FRegistroE001.RegistroE300.RegistroE360);
end;

function TBloco_E.RegistroE500New: TRegistroE500;
begin
  Result := FRegistroE001.RegistroE500;
end;

function TBloco_E.RegistroE530New: TRegistroE530;
begin

end;

procedure TBloco_E.WriteRegistroE001;
var
  Astr: String;
begin
   if Assigned(FRegistroE001) then
   begin
      with FRegistroE001 do
      begin
         Astr:=  LFill( 'E001' ) +
                 LFill( Integer(IND_MOV), 1 );

         Add(Astr);

         WriteRegistroE020(FRegistroE001);
         WriteRegistroE300(FRegistroE001);
         WriteRegistroE500(FRegistroE001);
      end;

      RegistroE990.QTD_LIN_E := RegistroE990.QTD_LIN_E + 1;
   end;
end;

procedure TBloco_E.WriteRegistroE990;
var
  strLinha: String;
begin
   //--Before
   strLinha := '';

   if Assigned(RegistroE990) then
   begin
      with RegistroE990 do
      begin
        QTD_LIN_E := QTD_LIN_E + 1;
        ///
        strLinha := LFill('E990') +
                    LFill(QTD_LIN_E,0);
        Add(strLinha);
     end;
  end;
end;

end.
