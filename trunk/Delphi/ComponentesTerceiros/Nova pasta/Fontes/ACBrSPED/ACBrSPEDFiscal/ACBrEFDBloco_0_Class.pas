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
|* 10/04/2009: Isaque Pinheiro
|*  - Cria��o e distribui��o da Primeira Versao
|* 15/03/2010: Alessandro Yamasaki
|*  - Adicionado o REGISTRO 0500: PLANO DE CONTAS CONT�BEIS
*******************************************************************************}

unit ACBrEFDBloco_0_Class;

interface

uses SysUtils, Classes, DateUtils, ACBrSped, ACBrEFDBloco_0, ACBrEFDBlocos,
     ACBrTXTClass;


type
  /// TBLOCO_0 - Abertura, Identifica��o e Refer�ncias

  { TBloco_0 }

  TBloco_0 = class(TACBrSPED)
  private
    FOnBeforeWriteRegistro0200: TWriteRegistroEvent;
    FOnBeforeWriteRegistro0206: TWriteRegistroEvent;
    FOnBeforeWriteRegistro0500: TWriteRegistroEvent;
    FOnBeforeWriteRegistro0600: TWriteRegistroEvent;
    FOnBeforeWriteRegistro0990: TWriteRegistroEvent;

    FOnWriteRegistro0200: TWriteRegistroEvent;
    FOnWriteRegistro0206: TWriteRegistroEvent;
    FOnWriteRegistro0500: TWriteRegistroEvent;
    FOnWriteRegistro0600: TWriteRegistroEvent;
    FOnWriteRegistro0990: TWriteRegistroEvent;

    FOnAfterWriteRegistro0200: TWriteRegistroEvent;
    FOnAfterWriteRegistro0206: TWriteRegistroEvent;
    FOnAfterWriteRegistro0500: TWriteRegistroEvent;
    FOnAfterWriteRegistro0600: TWriteRegistroEvent;
    FOnAfterWriteRegistro0990: TWriteRegistroEvent;

    FRegistro0000: TRegistro0000;      /// BLOCO 0 - Registro0000
    FRegistro0001: TRegistro0001;      /// BLOCO 0 - Registro0001
    FRegistro0990: TRegistro0990;      /// BLOCO 0 - Registro0990

    FRegistro0005Count: Integer;
    FRegistro0015Count: Integer;
    FRegistro0150Count: Integer;
    FRegistro0175Count: Integer;
    FRegistro0200Count: Integer;
    FRegistro0190Count: Integer;
    FRegistro0205Count: Integer;
    FRegistro0206Count: Integer;
    FRegistro0220Count: Integer;
    FRegistro0400Count: Integer;
    FRegistro0300Count: Integer;
    FRegistro0305Count: Integer;
    FRegistro0450Count: Integer;
    FRegistro0460Count: Integer;
    FRegistro0500Count: Integer;
    FRegistro0600Count: Integer;

    procedure WriteRegistro0005(Reg0001: TRegistro0001);
    procedure WriteRegistro0015(Reg0001: TRegistro0001);
    procedure WriteRegistro0100(Reg0001: TRegistro0001);
    procedure WriteRegistro0150(Reg0001: TRegistro0001);
    procedure WriteRegistro0175(Reg0150: TRegistro0150);
    procedure WriteRegistro0190(Reg0001: TRegistro0001);
    procedure WriteRegistro0200(Reg0001: TRegistro0001);
    procedure WriteRegistro0205(Reg0200: TRegistro0200);
    procedure WriteRegistro0206(Reg0200: TRegistro0200);
    procedure WriteRegistro0220(Reg0200: TRegistro0200);
    procedure WriteRegistro0300(Reg0001: TRegistro0001);
    procedure WriteRegistro0305(Reg0300: TRegistro0300);
    procedure WriteRegistro0400(Reg0001: TRegistro0001);
    procedure WriteRegistro0450(Reg0001: TRegistro0001);
    procedure WriteRegistro0460(Reg0001: TRegistro0001);
    procedure WriteRegistro0500(Reg0001: TRegistro0001);
    procedure WriteRegistro0600(Reg0001: TRegistro0001);

    procedure CriaRegistros;
    procedure LiberaRegistros;
  public
    constructor Create;           /// Create
    destructor Destroy; override; /// Destroy
    procedure LimpaRegistros;

    function Registro0000New: TRegistro0000;
    function Registro0001New: TRegistro0001;
    function Registro0005New: TRegistro0005;
    function Registro0015New: TRegistro0015;
    function Registro0100New: TRegistro0100;
    function Registro0150New: TRegistro0150;
    function Registro0175New: TRegistro0175;
    function Registro0190New: TRegistro0190;
    function Registro0200New: TRegistro0200;
    function Registro0205New: TRegistro0205;
    function Registro0206New: TRegistro0206;
    function Registro0220New: TRegistro0220;
    function Registro0300New: TRegistro0300;
    function Registro0305New: TRegistro0305;
    function Registro0400New: TRegistro0400;
    function Registro0450New: TRegistro0450;
    function Registro0460New: TRegistro0460;
    function Registro0500New: TRegistro0500;
    function Registro0600New: TRegistro0600;

    procedure WriteRegistro0000;
    procedure WriteRegistro0001;
    procedure WriteRegistro0990;

    property Registro0000: TRegistro0000 read FRegistro0000 write FRegistro0000;
    property Registro0001: TRegistro0001 read FRegistro0001 write FRegistro0001;
    property Registro0990: TRegistro0990 read FRegistro0990 write FRegistro0990;

    property Registro0005Count: Integer read FRegistro0005Count write FRegistro0005Count;
    property Registro0015Count: Integer read FRegistro0015Count write FRegistro0015Count;
    property Registro0150Count: Integer read FRegistro0150Count write FRegistro0150Count;
    property Registro0175Count: Integer read FRegistro0175Count write FRegistro0175Count;
    property Registro0200Count: Integer read FRegistro0200Count write FRegistro0200Count;
    property Registro0190Count: Integer read FRegistro0190Count write FRegistro0190Count;
    property Registro0205Count: Integer read FRegistro0205Count write FRegistro0205Count;
    property Registro0206Count: Integer read FRegistro0206Count write FRegistro0206Count;
    property Registro0220Count: Integer read FRegistro0220Count write FRegistro0220Count;
    property Registro0300Count: Integer read FRegistro0300Count write FRegistro0300Count;
    property Registro0305Count: Integer read FRegistro0305Count write FRegistro0305Count;
    property Registro0400Count: Integer read FRegistro0400Count write FRegistro0400Count;
    property Registro0450Count: Integer read FRegistro0450Count write FRegistro0450Count;
    property Registro0460Count: Integer read FRegistro0460Count write FRegistro0460Count;
    property Registro0500Count: Integer read FRegistro0500Count write FRegistro0500Count;
    property Registro0600Count: Integer read FRegistro0600Count write FRegistro0600Count;

    property OnBeforeWriteRegistro0200: TWriteRegistroEvent read FOnBeforeWriteRegistro0200 write FOnBeforeWriteRegistro0200;
    property OnBeforeWriteRegistro0206: TWriteRegistroEvent read FOnBeforeWriteRegistro0206 write FOnBeforeWriteRegistro0206;
    property OnBeforeWriteRegistro0500: TWriteRegistroEvent read FOnBeforeWriteRegistro0500 write FOnBeforeWriteRegistro0500;
    property OnBeforeWriteRegistro0600: TWriteRegistroEvent read FOnBeforeWriteRegistro0600 write FOnBeforeWriteRegistro0600;
    property OnBeforeWriteRegistro0990: TWriteRegistroEvent read FOnBeforeWriteRegistro0990 write FOnBeforeWriteRegistro0990;

    property OnWriteRegistro0200      : TWriteRegistroEvent read FOnWriteRegistro0200       write FOnWriteRegistro0200;
    property OnWriteRegistro0206      : TWriteRegistroEvent read FOnWriteRegistro0206       write FOnWriteRegistro0206;
    property OnWriteRegistro0500      : TWriteRegistroEvent read FOnWriteRegistro0500       write FOnWriteRegistro0500;
    property OnWriteRegistro0600      : TWriteRegistroEvent read FOnWriteRegistro0600       write FOnWriteRegistro0600;
    property OnWriteRegistro0990      : TWriteRegistroEvent read FOnWriteRegistro0990       write FOnWriteRegistro0990;

    property OnAfterWriteRegistro0200 : TWriteRegistroEvent read FOnAfterWriteRegistro0200  write FOnAfterWriteRegistro0200;
    property OnAfterWriteRegistro0206 : TWriteRegistroEvent read FOnAfterWriteRegistro0206  write FOnAfterWriteRegistro0206;
    property OnAfterWriteRegistro0500 : TWriteRegistroEvent read FOnAfterWriteRegistro0500  write FOnAfterWriteRegistro0500;
    property OnAfterWriteRegistro0600 : TWriteRegistroEvent read FOnAfterWriteRegistro0600  write FOnAfterWriteRegistro0600;
    property OnAfterWriteRegistro0990 : TWriteRegistroEvent read FOnAfterWriteRegistro0990  write FOnAfterWriteRegistro0990;
  end;

implementation

uses ACBrSpedUtils, StrUtils;

{ TBloco_0 }

constructor TBloco_0.Create ;
begin
  inherited ;
  CriaRegistros;
end;

destructor TBloco_0.Destroy;
begin
  LiberaRegistros;
  inherited;
end;

procedure TBloco_0.CriaRegistros;
begin
  FRegistro0000 := TRegistro0000.Create;
  FRegistro0001 := TRegistro0001.Create;
  FRegistro0990 := TRegistro0990.Create;

  FRegistro0005Count := 0;
  FRegistro0015Count := 0;
  FRegistro0150Count := 0;
  FRegistro0175Count := 0;
  FRegistro0200Count := 0;
  FRegistro0190Count := 0;
  FRegistro0205Count := 0;
  FRegistro0206Count := 0;
  FRegistro0220Count := 0;
  FRegistro0300Count := 0;
  FRegistro0305Count := 0;
  FRegistro0400Count := 0;
  FRegistro0450Count := 0;
  FRegistro0460Count := 0;
  FRegistro0500Count := 0;
  FRegistro0600Count := 0;

  FRegistro0990.QTD_LIN_0 := 0;
end;

procedure TBloco_0.LiberaRegistros;
begin
  FRegistro0000.Free;
  FRegistro0001.Free;
  FRegistro0990.Free;
end;

procedure TBloco_0.LimpaRegistros;
begin
  /// Limpa os Registros
  LiberaRegistros;
  Conteudo.Clear;

  /// Recriar os Registros Limpos
  CriaRegistros;
end;

function TBloco_0.Registro0000New: TRegistro0000;
begin
   Result := FRegistro0000;
end;

function TBloco_0.Registro0001New: TRegistro0001;
begin
   Result := FRegistro0001;
end;

function TBloco_0.Registro0005New: TRegistro0005;
begin
   Result := FRegistro0001.Registro0005;
end;

function TBloco_0.Registro0015New: TRegistro0015;
begin
   Result := FRegistro0001.Registro0015.New(FRegistro0001);
end;

function TBloco_0.Registro0100New: TRegistro0100;
begin
   Result := FRegistro0001.Registro0100;
end;

function TBloco_0.Registro0150New: TRegistro0150;
begin
   Result := FRegistro0001.Registro0150.New(FRegistro0001);
end;

function TBloco_0.Registro0175New: TRegistro0175;
var
U0150: TRegistro0150;
U0150Count: Integer;
begin
   U0150Count := FRegistro0001.Registro0150.Count -1;
   if U0150Count = -1 then
      raise Exception.Create('O registro 0175 deve ser filho do registro 0150, e n�o existe nenhum 0150 pai!');

   U0150 := FRegistro0001.Registro0150.Items[U0150Count];
   Result  := U0150.Registro0175.New(U0150);
end;

function TBloco_0.Registro0190New: TRegistro0190;
begin
   Result := FRegistro0001.Registro0190.New(FRegistro0001);
end;

function TBloco_0.Registro0200New: TRegistro0200;
begin
   Result := FRegistro0001.Registro0200.New(FRegistro0001);
end;

function TBloco_0.Registro0205New: TRegistro0205;
var
U0200: TRegistro0200;
U0200Count: Integer;
begin
   U0200Count := FRegistro0001.Registro0200.Count -1;
   if U0200Count = -1 then
      raise Exception.Create('O registro 0205 deve ser filho do registro 0200, e n�o existe nenhum 0200 pai!');

   U0200 := FRegistro0001.Registro0200.Items[U0200Count];
   Result  := U0200.Registro0205.New(U0200);
end;

function TBloco_0.Registro0206New: TRegistro0206;
var
U0200: TRegistro0200;
U0200Count: Integer;
begin
   U0200Count := FRegistro0001.Registro0200.Count -1;
   if U0200Count = -1 then
      raise Exception.Create('O registro 0206 deve ser filho do registro 0200, e n�o existe nenhum 0200 pai!');

   U0200 := FRegistro0001.Registro0200.Items[U0200Count];
   Result  := U0200.Registro0206.New(U0200);
end;

function TBloco_0.Registro0220New: TRegistro0220;
var
U0200: TRegistro0200;
U0200Count: Integer;
begin
   U0200Count := FRegistro0001.Registro0200.Count -1;
   if U0200Count = -1 then
      raise Exception.Create('O registro 0220 deve ser filho do registro 0200, e n�o existe nenhum 0200 pai!');

   U0200 := FRegistro0001.Registro0200.Items[U0200Count];
   Result  := U0200.Registro0220.New(U0200);
end;

function TBloco_0.Registro0300New: TRegistro0300;
begin
   Result := FRegistro0001.Registro0300.New(FRegistro0001);
end;

function TBloco_0.Registro0305New: TRegistro0305;
begin
   Result := FRegistro0001.Registro0300.Items[FRegistro0001.Registro0300.Count -1].Registro0305;
end;

function TBloco_0.Registro0400New: TRegistro0400;
begin
   Result := FRegistro0001.Registro0400.New(FRegistro0001);
end;

function TBloco_0.Registro0450New: TRegistro0450;
begin
   Result := FRegistro0001.Registro0450.New(FRegistro0001);
end;

function TBloco_0.Registro0460New: TRegistro0460;
begin
   Result := FRegistro0001.Registro0460.New(FRegistro0001);
end;

function TBloco_0.Registro0500New: TRegistro0500;
begin
   Result := FRegistro0001.Registro0500.New(FRegistro0001);
end;

function TBloco_0.Registro0600New: TRegistro0600;
begin
   Result := FRegistro0001.Registro0600.New(FRegistro0001);
end;

procedure TBloco_0.WriteRegistro0000 ;
var
  strIND_PERFIL: AnsiString;
  strCOD_VER: AnsiString;
begin
  if Assigned(Registro0000) then
  begin
     with Registro0000 do
     begin
       case IND_PERFIL of
        pfPerfilA: strIND_PERFIL := 'A';
        pfPerfilB: strIND_PERFIL := 'B';
        pfPerfilC: strIND_PERFIL := 'C';
       end;
       case COD_VER of
         vlVersao100: strCOD_VER := '001';
         vlVersao101: strCOD_VER := '002';
         vlVersao102: strCOD_VER := '003';
         vlVersao103: strCOD_VER := '004';
         vlVersao104: strCOD_VER := '005';
         vlVersao105: strCOD_VER := '006';
         vlVersao106: strCOD_VER := '007';
       end;

       Check(funChecaCNPJ(CNPJ), '(0-0000) ENTIDADE: O CNPJ "%s" digitado � inv�lido!', [CNPJ]);
       Check(funChecaCPF(CPF), '(0-0000) ENTIDADE: O CPF "%s" digitado � inv�lido!', [CPF]);
       Check(funChecaUF(UF), '(0-0000) ENTIDADE: A UF "%s" digitada � inv�lido!', [UF]);
       Check(funChecaIE(IE, UF), '(0-0000) ENTIDADE: A inscri��o estadual "%s" digitada � inv�lida!', [IE]);
       Check(funChecaMUN(COD_MUN), '(0-0000) ENTIDADE: O c�digo do munic�pio "%s" digitado � inv�lido!', [IntToStr(COD_MUN)]);
       ///
       Add( LFill( '0000' ) +
            LFill( strCOD_VER ) +
            LFill( Integer(COD_FIN), 1 ) +
            LFill( DT_INI ) +
            LFill( DT_FIN ) +
            LFill( NOME ) +
            LFill( CNPJ ) +
            LFill( CPF ) +
            LFill( UF ) +
            LFill( IE ) +
            LFill( COD_MUN, 7 ) +
            LFill( IM ) +
            LFill( SUFRAMA ) +
            LFill( strIND_PERFIL ) +
            LFill( Integer(IND_ATIV), 1 ) ) ;
       ///
       Registro0990.QTD_LIN_0 := Registro0990.QTD_LIN_0 + 1;
     end;
  end;
end;

procedure TBloco_0.WriteRegistro0001 ;
begin
  if Assigned(FRegistro0001) then
  begin
     with FRegistro0001 do
     begin
        Add( LFill( '0001' ) +
             LFill( Integer(IND_MOV), 0 ) ) ;

        if IND_MOV = imComDados then
        begin
          WriteRegistro0005(FRegistro0001) ;
          WriteRegistro0015(FRegistro0001) ;
          WriteRegistro0100(FRegistro0001) ;
          WriteRegistro0150(FRegistro0001) ;
          WriteRegistro0190(FRegistro0001) ;
          WriteRegistro0200(FRegistro0001) ;
          WriteRegistro0300(FRegistro0001) ;
          WriteRegistro0400(FRegistro0001) ;
          WriteRegistro0450(FRegistro0001) ;
          WriteRegistro0460(FRegistro0001) ;
          WriteRegistro0500(FRegistro0001) ;
          WriteRegistro0600(FRegistro0001) ;
        end;
     end;

     Registro0990.QTD_LIN_0 := Registro0990.QTD_LIN_0 + 1;
  end;
end;

procedure TBloco_0.WriteRegistro0005(Reg0001: TRegistro0001) ;
begin
  if Assigned(Reg0001.Registro0005) then
  begin
     with Reg0001.Registro0005 do
     begin
       Check(funChecaCEP(CEP, Registro0000.UF), '(0-0005) COMPLEMENTO DA ENTIDADE "%s": O CEP "%s" digitada � inv�lido para a unidade de federa��o "%s"!', [FANTASIA, CEP, Registro0000.UF]);
       ///
       Add( LFill('0005') +
            LFill(FANTASIA) +
            LFill(CEP, 8) +
            LFill(ENDERECO) +
            LFill(NUM) +
            LFill(COMPL) +
            LFill(BAIRRO) +
            LFill(FONE) +
            LFill(FAX) +
            LFill(EMAIL) ) ;
       ///
       Registro0990.QTD_LIN_0 := Registro0990.QTD_LIN_0 + 1;
     end;
     /// Variav�l para armazenar a quantidade de registro do tipo.
     FRegistro0005Count := FRegistro0005Count + 1;
  end;
end;

procedure TBloco_0.WriteRegistro0015(Reg0001: TRegistro0001) ;
var
  intFor: integer;
begin
  if Assigned(Reg0001.Registro0015) then
  begin
     for intFor := 0 to Reg0001.Registro0015.Count - 1 do
     begin
        with Reg0001.Registro0015.Items[intFor] do
        begin
           Check(funChecaUF(UF_ST),        '(0-0015) CONTRIBUINTE SUBSTITUTO: A UF "%s" digitada � inv�lido!', [UF_ST]);
           Check(funChecaIE(IE_ST, UF_ST), '(0-0015) CONTRIBUINTE SUBSTITUTO: A Inscri��o Estadual "%s" digitada � inv�lida!', [IE_ST]);
           ///
           Add( LFill('0015') +
                LFill(UF_ST) +
                LFill(IE_ST) ) ;
        end;
        Registro0990.QTD_LIN_0 := Registro0990.QTD_LIN_0 + 1;
     end;
     /// Variav�l para armazenar a quantidade de registro do tipo.
     FRegistro0015Count := FRegistro0015Count + Reg0001.Registro0015.Count;
  end;
end;

procedure TBloco_0.WriteRegistro0100(Reg0001: TRegistro0001) ;
begin
  if Assigned(Reg0001.Registro0100) then
  begin
     with Reg0001.Registro0100 do
     begin
       Check(funChecaCPF(CPF),     '(0-0100) CONTADOR: %s, o CPF "%s" digitado � inv�lido!', [NOME, CPF]);
       Check(funChecaCNPJ(CNPJ),   '(0-0100) CONTADOR: %s, o CNPJ "%s" digitado � inv�lido!', [NOME, CNPJ]);
//       Check(funChecaCEP(CEP, Registro0000.UF), '(0-0100) CONTADOR: %s, o CEP "%s" digitada � inv�lido para a unidade de federa��o "%s"!', [NOME, CEP, Registro0000.UF]);
       Check(funChecaMUN(COD_MUN), '(0-0100) CONTADOR: %s, o c�digo do munic�pio "%s" digitado � inv�lido!', [NOME, IntToStr(COD_MUN)]);
       Check(NOME <> '', '(0-0100) CONTADOR: O nome do contabilista/escrit�rio � obrigat�rio!');
       ///
       Add( LFill('0100') +
            LFill(NOME) +
            LFill(CPF) +
            LFill(CRC) +
            LFill(CNPJ) +
            LFill(CEP, 8) +
            LFill(ENDERECO) +
            LFill(NUM) +
            LFill(COMPL) +
            LFill(BAIRRO) +
            LFill(FONE) +
            LFill(FAX) +
            LFill(EMAIL) +
            LFill(COD_MUN, 7) ) ;
       ///
       Registro0990.QTD_LIN_0 := Registro0990.QTD_LIN_0 + 1;
     end;
  end;
end;

procedure TBloco_0.WriteRegistro0150(Reg0001: TRegistro0001) ;
var
  intFor: integer;
  booExterior: Boolean;
begin
  if Assigned(Reg0001.Registro0150) then
  begin
     for intFor := 0 to Reg0001.Registro0150.Count - 1 do
     begin
        with Reg0001.Registro0150.Items[intFor] do
        begin
          booExterior := ((COD_PAIS <> '01058') and (COD_PAIS <> '1058'));

//          Check(funChecaPAISIBGE(COD_PAIS), '(0-0150) %s-%s, o c�digo do pa�s "%s" digitado � inv�lido!', [COD_PART, NOME, COD_PAIS]);
          if Length(CNPJ) > 0 then
             Check(funChecaCNPJ(CNPJ), '(0-0150) %s-%s, o CNPJ "%s" digitado � inv�lido!', [COD_PART, NOME, CNPJ]);
          if Length(CPF)  > 0 then
             Check(funChecaCPF(CPF),   '(0-0150) %s-%s, o CPF "%s" digitado � inv�lido!', [COD_PART, NOME, CPF]);
//          Check(funChecaIE(IE, UF),         '(0-0150) %s-%s, a Inscri��o Estadual "%s" digitada � inv�lida!', [COD_PART, NOME, IE]);
//          Check(funChecaMUN(COD_MUN),       '(0-0150) %s-%s, o c�digo do munic�pio "%s" digitado � inv�lido!', [COD_PART, NOME, IntToStr(COD_MUN)]);
          Check(NOME <> '',            '(0-0150) O nome do participante com CPF/CNPJ %s � obrigat�rio!', [CNPJ + CPF]);
          Check(ENDERECO <> EmptyStr,  '(0-150) O Endere�o do participante "%s - %s - CPF/CNPJ %s" � obrigat�rio!', [COD_PART, NOME, CNPJ + CPF]);
          ///
          Add( LFill('0150') +
               LFill(COD_PART) +
               LFill(NOME) +
               LFill(COD_PAIS) +
               LFill(CNPJ) +
               LFill(CPF) +
               LFill(IE) +
               IfThen(booExterior, LFill(''), LFill(COD_MUN, 7)) +
               LFill(SUFRAMA) +
               LFill(ENDERECO) +
               LFill(NUM) +
               LFill(COMPL) +
               LFill(BAIRRO) ) ;
        end;
        /// Registros FILHOS
        WriteRegistro0175( Reg0001.Registro0150.Items[intFor] );

        Registro0990.QTD_LIN_0 := Registro0990.QTD_LIN_0 + 1;
     end;
     /// Variav�l para armazenar a quantidade de registro do tipo.
     FRegistro0150Count := FRegistro0150Count + Reg0001.Registro0150.Count;
  end;
end;

procedure TBloco_0.WriteRegistro0175(Reg0150: TRegistro0150) ;
var
  intFor: integer;
begin
  if Assigned(Reg0150.Registro0175) then
  begin
     for intFor := 0 to Reg0150.Registro0175.Count - 1 do
     begin
        with Reg0150.Registro0175.Items[intFor] do
        begin
          Check(((DT_ALT >= DT_INI) and (DT_ALT <= DT_FIN)),  '(0-0175) ALTERA��O NO CADASTRO DE CLIENTES/FORNECEDORES: A data da altera��o deve estar no intervalo de: %s a %s!', [DateToStr(DT_INI), DateToStr(DT_FIN)]);
          ///
          Add( LFill('0175') +
               LFill(DT_ALT) +
               LFill(NR_CAMPO, 2) +
               LFill(CONT_ANT) ) ;
        end;
        Registro0990.QTD_LIN_0 := Registro0990.QTD_LIN_0 + 1;
     end;
     /// Variav�l para armazenar a quantidade de registro do tipo.
     FRegistro0175Count := FRegistro0175Count + Reg0150.Registro0175.Count;
  end;
end;

procedure TBloco_0.WriteRegistro0190(Reg0001: TRegistro0001) ;
var
  intFor: integer;
begin
  if Assigned(Reg0001.Registro0190) then
  begin
     for intFor := 0 to Reg0001.Registro0190.Count - 1 do
     begin
        with Reg0001.Registro0190.Items[intFor] do
        begin
//          Check(Reg0001.Registro0190.LocalizaRegistro(UNID), '(0-0190) UNIDADE MEDIDA: A unidade de medida "%s" foi duplicada na lista de registros 0190!', [UNID]);

          Add( LFill('0190') +
               LFill(UNID) +
               LFill(DESCR) ) ;
        end;
        Registro0990.QTD_LIN_0 := Registro0990.QTD_LIN_0 + 1;
     end;
     /// Variav�l para armazenar a quantidade de registro do tipo.
     FRegistro0190Count := FRegistro0190Count + Reg0001.Registro0190.Count;
  end;
end;

procedure TBloco_0.WriteRegistro0200(Reg0001: TRegistro0001) ;
var
  intFor: integer;
  strTIPO_ITEM: AnsiString;
  strLinha: AnsiString;
begin
  if Assigned( Reg0001.Registro0200 ) then
  begin
     //-- Before
     strLinha := '';
     if Assigned(FOnBeforeWriteRegistro0200) then
     begin
        FOnBeforeWriteRegistro0200(strLinha);
        if strLinha <> EmptyStr then
           Add(strLinha);
     end;
     for intFor := 0 to Reg0001.Registro0200.Count - 1 do
     begin
        strLinha := '';
        with Reg0001.Registro0200.Items[intFor] do
        begin
          case TIPO_ITEM of
            tiMercadoriaRevenda    : strTIPO_ITEM := '00';
            tiMateriaPrima         : strTIPO_ITEM := '01';
            tiEmbalagem            : strTIPO_ITEM := '02';
            tiProdutoProcesso      : strTIPO_ITEM := '03';
            tiProdutoAcabado       : strTIPO_ITEM := '04';
            tiSubproduto           : strTIPO_ITEM := '05';
            tiProdutoIntermediario : strTIPO_ITEM := '06';
            tiMaterialConsumo      : strTIPO_ITEM := '07';
            tiAtivoImobilizado     : strTIPO_ITEM := '08';
            tiServicos             : strTIPO_ITEM := '09';
            tiOutrosInsumos        : strTIPO_ITEM := '10';
            tiOutras               : strTIPO_ITEM := '99';
          end;
          if COD_GEN <> EmptyStr then
          begin
            COD_GEN := funStrZero(COD_GEN, 2);
            Check(funChecaGENERO(COD_GEN), '(0-0200) O c�digo do g�nero "%s" digitado � inv�lido! ' +
              'Produto %s %s', [COD_GEN, COD_BARRA, DESCR_ITEM]);
          end;

          strLinha := LFill('0200') +
                      LFill( COD_ITEM ) +
                      LFill( DESCR_ITEM ) +
                      LFill( COD_BARRA ) +
                      LFill( COD_ANT_ITEM ) +
                      LFill( UNID_INV ) +
                      LFill( strTIPO_ITEM ) +
                      LFill( COD_NCM ) +
                      LFill( EX_IPI ) +
                      LFill( COD_GEN ) +
                      LFill( COD_LST ) +
                      LFill( ALIQ_ICMS,0,2 );
          //-- Write
          if Assigned(FOnWriteRegistro0200) then
             FOnWriteRegistro0200(strLinha);

          Add(strLinha);
        end;
        /// Registros FILHOS
        WriteRegistro0205( Reg0001.Registro0200.Items[intFor] ) ;
        WriteRegistro0206( Reg0001.Registro0200.Items[intFor] ) ;
        WriteRegistro0220( Reg0001.Registro0200.Items[intFor] );

        Registro0990.QTD_LIN_0 := Registro0990.QTD_LIN_0 + 1;
     end;
     //-- After
     strLinha := '';
     if Assigned(FOnAfterWriteRegistro0200) then
     begin
        FOnAfterWriteRegistro0200(strLinha);
        if strLinha <> EmptyStr then
           Add(strLinha);
     end;
     /// Vari�vel para armazenar a quantidade de registro do tipo.
     FRegistro0200Count := FRegistro0200Count + Reg0001.Registro0200.Count;
  end;
end;

procedure TBloco_0.WriteRegistro0205(Reg0200: TRegistro0200) ;
var
  intFor: integer;
begin
  if Assigned( Reg0200.Registro0205 ) then
  begin
     for intFor := 0 to Reg0200.Registro0205.Count - 1 do
     begin
        with Reg0200.Registro0205.Items[intFor] do
        begin
          Add( LFill('0205') +
               LFill( DESCR_ANT_ITEM ) +
               LFill( DT_INI ) +
               LFill( DT_FIN ) +
               LFill( COD_ANT_ITEM) ) ;
        end;
        Registro0990.QTD_LIN_0 := Registro0990.QTD_LIN_0 + 1;
     end;
     /// Variav�l para armazenar a quantidade de registro do tipo.
     FRegistro0205Count := FRegistro0205Count + Reg0200.Registro0205.Count;
  end;
end;

procedure TBloco_0.WriteRegistro0206(Reg0200: TRegistro0200) ;
var
  intFor: integer;
  strLinha: AnsiString;
begin
  if Assigned( Reg0200.Registro0206 ) then
  begin
     //-- Before
     strLinha := '';
     if Assigned(FOnBeforeWriteRegistro0206) then
     begin
        FOnBeforeWriteRegistro0206(strLinha);
        if strLinha <> EmptyStr then
           Add(strLinha);
     end;

     for intFor := 0 to Reg0200.Registro0206.Count - 1 do
     begin
        strLinha := '';
        with Reg0200.Registro0206.Items[intFor] do
        begin
          strLinha := LFill('0206') +
                      LFill( COD_COMB );
          //-- After
          if Assigned(FOnWriteRegistro0206) then
             FOnWriteRegistro0206(strLinha);

          Add(strLinha);
        end;
        Registro0990.QTD_LIN_0 := Registro0990.QTD_LIN_0 + 1;
     end;
     //-- After
     strLinha := '';
     if Assigned(FOnAfterWriteRegistro0206) then
     begin
        FOnAfterWriteRegistro0206(strLinha);
        if strLinha <> EmptyStr then
           Add(strLinha);
     end;
     /// Variav�l para armazenar a quantidade de registro do tipo.
     FRegistro0206Count := FRegistro0206Count + Reg0200.Registro0206.Count;
  end;
end;

procedure TBloco_0.WriteRegistro0220(Reg0200: TRegistro0200) ;
var
  intFor: integer;
begin
  if Assigned( Reg0200.Registro0220 ) then
  begin
     for intFor := 0 to Reg0200.Registro0220.Count - 1 do
     begin
        with Reg0200.Registro0220.Items[intFor] do
        begin
          Add( LFill('0220') +
               LFill( UNID_CONV ) +
               DFill( FAT_CONV, 6 ) ) ;
        end;
        Registro0990.QTD_LIN_0 := Registro0990.QTD_LIN_0 + 1;
     end;
     /// Variav�l para armazenar a quantidade de registro do tipo.
     FRegistro0220Count := FRegistro0220Count + Reg0200.Registro0220.Count;
  end;
end;

procedure TBloco_0.WriteRegistro0300(Reg0001: TRegistro0001) ;
var
  intFor: integer;
begin
  /// Exig�ncia do Art. 3� do AC 09/08
  if DT_INI >= EncodeDate(2010,07,01) then
  begin
     if Assigned(Reg0001.Registro0300) then
     begin
        for intFor := 0 to Reg0001.Registro0300.Count - 1 do
        begin
           with Reg0001.Registro0300.Items[intFor] do
           begin
             ///
             Add( LFill('0300') +
                  LFill( COD_IND_BEM ) +
                  LFill( IDENT_MERC, 1) +
                  LFill( DESCR_ITEM ) +
                  LFill( COD_PRNC ) +
                  LFill( COD_CTA ) +
                  DFill( NR_PARC, 0 ) ) ;
           end;
           /// Registros FILHOS
           WriteRegistro0305( Reg0001.Registro0300.Items[intFor] );

           Registro0990.QTD_LIN_0 := Registro0990.QTD_LIN_0 + 1;
        end;
        /// Variav�l para armazenar a quantidade de registro do tipo.
        FRegistro0300Count := FRegistro0300Count  + Reg0001.Registro0300.Count;
     end;
  end;
end;

procedure TBloco_0.WriteRegistro0305(Reg0300: TRegistro0300) ;
begin
  /// Exig�ncia do Art. 3� do AC 09/08
  if DT_INI >= EncodeDate(2010,07,01) then
  begin
     if Assigned(Reg0300.Registro0305) then
     begin
        if (Length(Reg0300.Registro0305.COD_CCUS) > 0) and (Length(Reg0300.Registro0305.FUNC) > 0) then
        begin
           with Reg0300.Registro0305 do
           begin
             Add( LFill('0305') +
                  LFill(COD_CCUS) +
                  LFill(FUNC) +
                  DFill(VIDA_UTIL, 0) ) ;
             ///
             Registro0990.QTD_LIN_0 := Registro0990.QTD_LIN_0 + 1;
           end;
           /// Variav�l para armazenar a quantidade de registro do tipo.
           FRegistro0305Count := FRegistro0305Count + 1;
        end;
     end;
  end;
end;

procedure TBloco_0.WriteRegistro0400(Reg0001: TRegistro0001) ;
var
  intFor: integer;
begin
  if Assigned(Reg0001.Registro0400) then
  begin
     for intFor := 0 to Reg0001.Registro0400.Count - 1 do
     begin
        with Reg0001.Registro0400.Items[intFor] do
        begin
          ///
          Add( LFill('0400') +
               LFill( COD_NAT ) +
               LFill( DESCR_NAT ) ) ;
        end;
        Registro0990.QTD_LIN_0 := Registro0990.QTD_LIN_0 + 1;
     end;
     /// Variav�l para armazenar a quantidade de registro do tipo.
     FRegistro0400Count := FRegistro0400Count + Reg0001.Registro0400.Count;
  end;
end;

procedure TBloco_0.WriteRegistro0450(Reg0001: TRegistro0001) ;
var
  intFor: integer;
begin
  if Assigned( Reg0001.Registro0450 ) then
  begin
     for intFor := 0 to Reg0001.Registro0450.Count - 1 do
     begin
        with Reg0001.Registro0450.Items[intFor] do
        begin
          Add( LFill('0450') +
               LFill( COD_INF ) +
               LFill( TXT ) ) ;
        end;
        Registro0990.QTD_LIN_0 := Registro0990.QTD_LIN_0 + 1;
     end;
     /// Variav�l para armazenar a quantidade de registro do tipo.
     FRegistro0450Count := FRegistro0450Count + Reg0001.Registro0450.Count;
  end;
end;

procedure TBloco_0.WriteRegistro0460(Reg0001: TRegistro0001) ;
var
  intFor: integer;
begin
  if Assigned( Reg0001.Registro0460 ) then
  begin
     for intFor := 0 to Reg0001.Registro0460.Count - 1 do
     begin
        with Reg0001.Registro0460.Items[intFor] do
        begin
          Add( LFill('0460') +
               LFill( COD_OBS ) +
               LFill( TXT ) ) ;
        end;
        Registro0990.QTD_LIN_0 := Registro0990.QTD_LIN_0 + 1;
     end;
     /// Variav�l para armazenar a quantidade de registro do tipo.
     FRegistro0460Count := FRegistro0460Count + Reg0001.Registro0460.Count;
  end;
end;

procedure TBloco_0.WriteRegistro0500(Reg0001: TRegistro0001) ;
var
  intFor: integer;
  strLinha: AnsiString;
begin
  /// Exig�ncia do Art. 3� do AC 09/08
  if DT_INI >= EncodeDate(2010,07,01) then
  begin
     if Assigned( Reg0001.Registro0500 ) then
     begin
        //-- Before
        strLinha := '';
        if Assigned(FOnBeforeWriteRegistro0500) then
        begin
           FOnBeforeWriteRegistro0500(strLinha);
           if strLinha <> EmptyStr then
              Add(strLinha);
        end;

        for intFor := 0 to Reg0001.Registro0500.Count - 1 do
        begin
           strLinha := '';
           with Reg0001.Registro0500.Items[intFor] do
           begin
              Check(Pos(COD_NAT_CC, '01,02,03,04,05,09,10,99') > 0, '(0-0500) O c�digo da natureza da conta/grupo de contas "%s" digitado � inv�lido!', [COD_NAT_CC]);
              Check(((IND_CTA = 'S') or (IND_CTA = 'A')), '(0-0500) O indicador "%s" do tipo de conta, deve ser informado  S ou A!', [IND_CTA]);

              strLinha := LFill('0500') +
                          LFill( DT_ALT ) +
                          LFill( COD_NAT_CC, 2) +
                          LFill( IND_CTA, 1) +
                          LFill( NIVEL ) +
                          LFill( COD_CTA ) +
                          LFill( NOME_CTA ) ;

              //-- Write
              if Assigned(FOnWriteRegistro0500) then
                 FOnWriteRegistro0500(strLinha);

             Add(strLinha);
           end;
           Registro0990.QTD_LIN_0 := Registro0990.QTD_LIN_0 + 1;
        end;
        //-- After
        strLinha := '';
        if Assigned(FOnAfterWriteRegistro0500) then
        begin
           FOnAfterWriteRegistro0500(strLinha);
           if strLinha <> EmptyStr then
              Add(strLinha);
        end;
        /// Variav�l para armazenar a quantidade de registro do tipo.
        FRegistro0500Count := FRegistro0500Count + Reg0001.Registro0500.Count;
     end;
  end;
end;

procedure TBloco_0.WriteRegistro0600(Reg0001: TRegistro0001) ;
var
  intFor: integer;
  strLinha: AnsiString;
begin
  if Assigned( Reg0001.Registro0600 ) then
  begin
     //-- Before
     strLinha := '';
     if Assigned(FOnBeforeWriteRegistro0600) then
     begin
        FOnBeforeWriteRegistro0600(strLinha);
        if strLinha <> EmptyStr then
           Add(strLinha);
     end;

     for intFor := 0 to Reg0001.Registro0600.Count - 1 do
     begin
        strLinha := '';
        with Reg0001.Registro0600.Items[intFor] do
        begin
          strLinha := LFill( '0600'   ) +
                      LFill( DT_ALT   ) +
                      LFill( COD_CCUS ) +
                      LFill( CCUS     );

          //-- Write
          if Assigned(FOnWriteRegistro0600) then
             FOnWriteRegistro0600(strLinha);

          Add(strLinha);
        end;
        Registro0990.QTD_LIN_0 := Registro0990.QTD_LIN_0 + 1;
     end;
     //-- After
     strLinha := '';
     if Assigned(FOnAfterWriteRegistro0600) then
     begin
        FOnAfterWriteRegistro0600(strLinha);
        if strLinha <> EmptyStr then
           Add(strLinha);
     end;
     /// Variav�l para armazenar a quantidade de registro do tipo.
     FRegistro0600Count := FRegistro0600Count + Reg0001.Registro0600.Count;
  end;
end;

procedure TBloco_0.WriteRegistro0990 ;
  var strLinha : AnsiString;
begin
  //--Before
  strLinha := '';
  if Assigned(FOnBeforeWriteRegistro0990) then
  begin
    FOnBeforeWriteRegistro0990(strLinha);
    if strLinha <> EmptyStr then
       Add(strLinha);
  end;

  if Assigned(Registro0990) then
  begin
     with Registro0990 do
     begin
       QTD_LIN_0 := QTD_LIN_0 + 1;
       ///
       strLinha := LFill('0990') +
                   LFill(QTD_LIN_0,0);

       if Assigned(FOnWriteRegistro0990) then FOnWriteRegistro0990(strLinha);

       Add(strLinha);
     end;
  end;

  //-- After
  strLinha := '';
  if Assigned(FOnAfterWriteRegistro0990) then
  begin
    FOnAfterWriteRegistro0990(strLinha);
    if strLinha <> EmptyStr then
       Add(strLinha);
  end;
end;

end.
