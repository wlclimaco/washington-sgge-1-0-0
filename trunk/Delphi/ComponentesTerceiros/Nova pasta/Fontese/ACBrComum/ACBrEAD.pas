{******************************************************************************}
{ Projeto: Componentes ACBr                                                    }
{  Biblioteca multiplataforma de componentes Delphi para intera��o com equipa- }
{ mentos de Automa��o Comercial utilizados no Brasil                           }
{                                                                              }
{ Direitos Autorais Reservados (c) 2010                                        }
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
|* 05/07/2010: Elton M. Barbosa
|*  - Baseado em m�todos j� existentes no ACBrRFD.
|*  - Esbo�o da Primeira Versao para avalia��o.
*******************************************************************************}

{$I ACBr.inc}

unit ACBrEAD;

interface

{$IFNDEF FPC}
 {$DEFINE USE_libeay32}
{$ENDIF}

uses
   Classes, SysUtils, strutils, ACBrConsts,
   ACBrUtil, {$IFDEF USE_libeay32}libeay32{$ELSE} OpenSSLExt{$ENDIF};

const
   CACBrEAD_Versao = '0.3.0' ;
   cRFDRSAKey = '-----BEGIN RSA PRIVATE KEY-----' + sLineBreak +
                'MIICXQIBAAKBgQCtpPqcoOX4rwgdoKi6zJwPX9PA2iX2KxgvyxjE+daI5ZmYxcg0'+ sLineBreak +
                'NScjX59nXRaLmtltVRfsRc1n4+mLSXiWMh3jIbw+TWn+GXKQhS2GitpLVhO3A6Ns'+ sLineBreak +
                'vO1+RuP77s+uGYhqVvbD0Pziq+I2r4oktsjTbpnC7Mof3BjJdIUFsTHKYwIDAQAB'+ sLineBreak +
                'AoGAXXqwU7umsi8ADnsb+pwF85zh8TM/NnvSpIAQkJHzNXVtL7ph4gEvVbK3rLyH'+ sLineBreak +
                'U5aEMICbxV16i9A9PPfLjAfk4CuPpZlTibgfBRIG3MXirum0tjcyzbPyiDrk0qwM'+ sLineBreak +
                'e83MyRkrnGlss6cRT3mZk67txEamqTVmDwz/Sfo1fVlCQAkCQQDW3N/EKyT+8tPW'+ sLineBreak +
                '1EuPXafRONMel4xB1RiBmHYJP1bo/sDebLpocL6oiVlUX/k/zPRo1wMvlXJxPyiz'+ sLineBreak +
                'mnf37cZ9AkEAzuPcDvGxwawr7EPGmPQ0f7aWv87tS/rt9L3nKiz8HfrT6WT0R1Bh'+ sLineBreak +
                'I7lLGq4VFWE29I6hQ2lPNGX9IGFjiflKXwJBALgsO+J62QtwOgU7lEkfjmnYu57N'+ sLineBreak +
                'aHxFnOv5M7RZhrXRKKF/sYk0mzj8AoZAffYiSJ5VL3XqNF6+NLU/AvaR6kECQQCV'+ sLineBreak +
                'nY6sd/kWmA4DhFgAkMnOehq2h0xwH/0pepPLmlCQ1a2eIVXOpMA692rq1m2E0pLN'+ sLineBreak +
                'dMAGYgfXWtIdMpCrXM59AkB5npcELeGBv1K8B41fmrlA6rEq4aqmfwAFRKcQTj8a'+ sLineBreak +
                'n09FVtccLVPJ42AM1/QXK6a8DGCtB9R+j5j3UO/iL0+3'+ sLineBreak +
                '-----END RSA PRIVATE KEY-----' ;

{    **** IMPORTANTE ****
    Por motivos de seguran�a, GERE A SUA PROPRIA CHAVE e informe-a em:
     "OnGetChavePrivada" }

type
  TACBrEADDgst = (dgstMD2, dgstMD4, dgstMD5, dgstRMD160, dgstSHA, dgstSHA1) ;

  TACBrEADCalc = procedure(Arquivo: String) of object ;
  TACBrEADGetChave = procedure(var Chave: AnsiString) of object ;

  EACBrEADException = class(Exception);

  { TACBrEAD }

  TACBrEAD = class(TComponent)
  private
    fsOnGetChavePrivada: TACBrEADGetChave;
    fsOnGetChavePublica : TACBrEADGetChave ;

    fsInicializado : Boolean ;

    fsKey : pEVP_PKEY ;
    fsIsXMLeECFc : Boolean ;

    function GetOpenSSL_Version: String;
    procedure InitOpenSSL ;
    procedure FreeOpenSSL ;

    Function GetChavePrivada : AnsiString;
    procedure LerChavePrivada ;
    Function GetChavePublica : AnsiString;
    procedure LerChavePublica ;
    Procedure LerChave_eECFc( ConteudoXML: AnsiString) ;
    Procedure LerChaveModuloExpoente( Modulo, Expoente: AnsiString) ;
    procedure LerChave(const Chave : AnsiString; Privada: Boolean) ;
    procedure LiberarChave ;

    function CriarMemBIO: pBIO ;
    procedure LiberarBIO( Bio : pBIO);

    function BioToStr( ABio : pBIO) : String ;

    function GetAbout: String;

    procedure VerificaNomeArquivo( NomeArquivo : String ) ;
  public
    constructor Create(AOwner: TComponent); override;
    Destructor Destroy  ; override;

    Procedure GerarChaves( var AChavePublica : AnsiString;
       var AChavePrivada : AnsiString ) ;

    function AssinarArquivoComEAD( const NomeArquivo: String;
       RemoveEADSeExistir : Boolean = False ) : AnsiString ;
    Procedure RemoveEADArquivo( const NomeArquivo: String) ;
    Function RemoveEAD( MS : TMemoryStream ) : String;

    function VerificarEADArquivo( const NomeArquivo: String): Boolean ; overload ;
    function VerificarEAD( const AString : AnsiString): Boolean ; overload ;
    function VerificarEAD( const AStringList : TStringList): Boolean ; overload ;
    function VerificarEAD(const MS : TMemoryStream ; EAD : String = '') : Boolean ;
      overload ;

    Function GerarXMLeECFc(const NomeSwHouse, Diretorio: String): Boolean; overload;
    Function GerarXMLeECFc(const NomeSwHouse: String): AnsiString; overload;
    Procedure CalcularModuloeExpoente( var Modulo, Expoente : AnsiString );
    Function CalcularChavePublica : AnsiString ;
    Function ConverteXMLeECFcParaOpenSSL( ArquivoXML: String) : AnsiString;

    function CalcularHashArquivo( const NomeArquivo: String;
       const Digest: TACBrEADDgst ): AnsiString ; overload ;
    function CalcularHash( const AString : AnsiString;
       const Digest: TACBrEADDgst ): AnsiString ; overload ;
    function CalcularHash( const AStringList : TStringList;
       const Digest: TACBrEADDgst ): AnsiString ; overload ;
    function CalcularHash( const MS : TMemoryStream;
       const Digest: TACBrEADDgst ): AnsiString ; overload ;

    function CalcularEADArquivo( const NomeArquivo: String): AnsiString ; overload ;
    function CalcularEAD( const AString : AnsiString): AnsiString ; overload ;
    function CalcularEAD( const AStringList : TStringList): AnsiString ; overload ;
    function CalcularEAD( const MS : TMemoryStream): AnsiString ; overload ;

    property OpenSSL_Version : String read GetOpenSSL_Version ;

    function MD5FromFile(const APathArquivo: String): String;
    function MD5FromString(const AString: String): String;
  published
    property About: String read GetAbout stored False;

    property OnGetChavePrivada: TACBrEADGetChave read fsOnGetChavePrivada
       write fsOnGetChavePrivada;
    property OnGetChavePublica: TACBrEADGetChave read fsOnGetChavePublica
       write fsOnGetChavePublica;
  end;

implementation


function TACBrEAD.MD5FromFile(const APathArquivo: String): String;
begin
  Result := String(CalcularHashArquivo(APathArquivo, dgstMD5));
end;

function TACBrEAD.MD5FromString(const AString: String): String;
begin
  Result := String(CalcularHash(AnsiString(AString), dgstMD5));
end;

{ ------------------------------ TACBrEAD ------------------------------ }

function TACBrEAD.GetAbout: String;
begin
   Result := 'ACBrEAD Ver: ' + CACBrEAD_Versao;
end;

constructor TACBrEAD.Create(AOwner : TComponent) ;
begin
   inherited Create(AOwner) ;

   fsInicializado := False ;
   fsIsXMLeECFc   := False ;

   fsOnGetChavePrivada := nil;
   fsOnGetChavePublica := nil;
end ;

destructor TACBrEAD.Destroy ;
begin
   FreeOpenSSL;

   inherited Destroy ;
end ;

procedure TACBrEAD.InitOpenSSL;
begin
  if fsInicializado then
     exit ;

  OpenSSL_add_all_algorithms;
  OpenSSL_add_all_ciphers;
  OpenSSL_add_all_digests;
  ERR_load_crypto_strings;

  fsInicializado := True;
end;

function TACBrEAD.GetOpenSSL_Version: String;
begin
   Result := String(SSLeay_version( 0 ));
end;

procedure TACBrEAD.FreeOpenSSL;
begin
  LiberarChave;
  {$IFDEF USE_libeay32}
   EVP_cleanup();
  {$ELSE}
   EVPcleanup();
  {$ENDIF}
end;

function TACBrEAD.BioToStr(ABio : pBIO) : String ;
Var
  {$IFDEF USE_libeay32}
   Buf : array [0..1023] of AnsiChar;
  {$ELSE}
   Ret : Integer ;
  {$ENDIF}
  Lin : AnsiString ;
begin
  Result := '';

  {$IFDEF USE_libeay32}
   while BIO_eof( ABio ) = 0 do
   begin
     BIO_gets( ABio, Buf, 1024 );
     Lin := StrPas( Buf );
     Result := Result + String(Lin);
   end ;
  {$ELSE}
   repeat
      SetLength(Lin,1024);
      Ret := BioRead( ABio, Lin, 1024);
      if Ret > 0 then
      begin
         Lin := copy(Lin,1,Ret) ;
         Result := Result + Lin;
      end ;
   until (Ret <= 0);
  {$ENDIF}
end ;

procedure TACBrEAD.GerarChaves(var AChavePublica : AnsiString ;
  var AChavePrivada : AnsiString) ;

  {$WARN SYMBOL_PLATFORM OFF}
  function FindFileSeed : String ;
  var
    TmpFile : TSearchRec ;
    TmpDir : String ;
  begin
    Result := '';
    TmpDir := GetEnvironmentVariable('TEMP');
    if FindFirst(TmpDir + '\*', faReadOnly and faHidden and faSysFile and faArchive, TmpFile) = 0 then
       Result := TmpFile.Name
    else
       if FindFirst(ExtractFileDir(ParamStr(0)) + '*', faReadOnly and faHidden and faSysFile and faArchive, TmpFile) = 0 then
          Result := TmpFile.Name ;

    FindClose(TmpFile);
  end ;
  {$WARN SYMBOL_PLATFORM ON}

Var
  FileSeed : String ;
  BioKey : pBIO;
  RSAKey : pRSA ;
begin
  InitOpenSSL;

  AChavePublica := '';
  AChavePrivada := '';

  // Load a pseudo random file
  FileSeed := FindFileSeed;
  RAND_load_file(PAnsiChar(AnsiString(FileSeed)), -1);

  // Gera a Chave RSA
  LiberarChave;
  {$IFDEF USE_libeay32}
   RSAKey := RSA_generate_key( 1024, RSA_F4, nil, nil);
  {$ELSE}
   RSAKey := RsaGenerateKey( 1024, RSA_F4, nil, nil);
  {$ENDIF}
  if RSAKey = nil then
     raise EACBrEADException.Create( 'Erro ao gerar par de Chaves RSA');

  // Lendo Conteudo da Chave
  BioKey := CriarMemBIO;
  try
     PEM_write_bio_RSAPrivateKey(BioKey, RSAKey, nil, nil, 0, nil, nil);
     AChavePrivada := AnsiString(BioToStr( BioKey ));

     LerChave( AChavePrivada, True );

     BIO_reset( BioKey );
     PEM_write_bio_PUBKEY( BioKey, fsKey);
     AChavePublica := AnsiString(BioToStr( BioKey ));
  finally
     LiberarBIO( BioKey );
  end ;
end ;

function TACBrEAD.GetChavePrivada : AnsiString ;
begin
  Result := '';
  if Assigned( fsOnGetChavePrivada ) then
     fsOnGetChavePrivada( Result ) ;

  if Result = '' then
     Result := cRFDRSAKey;
     //raise EACBrEADException.Create( ACBrStr('Chave RSA Privada n�o especificada no evento: "OnGetChavePrivada"') ) ;
end ;

procedure TACBrEAD.LerChavePrivada ;
var
  Chave : AnsiString ;
begin
  Chave := GetChavePrivada;

  if pos('<modulo>', String( Chave ) ) > 0 then
     LerChave_eECFc( Chave )
  else
     LerChave(Chave, True) ;
end ;

function TACBrEAD.GetChavePublica : AnsiString ;
begin
  Result := '';
  if Assigned( fsOnGetChavePublica ) then
    fsOnGetChavePublica( Result ) ;

  if Result = '' then
    raise EACBrEADException.Create( ACBrStr('Chave RSA Publica n�o especificada no evento: "OnGetChavePublica"') ) ;
end ;

procedure TACBrEAD.LerChavePublica ;
var
  Chave : AnsiString ;
begin
  Chave := GetChavePublica;

  if pos('<modulo>', String( Chave ) ) > 0 then
     LerChave_eECFc( Chave )
  else
     LerChave( Chave, False ) ;
end ;

function TACBrEAD.ConverteXMLeECFcParaOpenSSL(ArquivoXML: String): AnsiString;
Var
  SL : TStringList ;
  Bio : PBIO ;
begin
  Result := '';

  if not FileExists( ArquivoXML ) then
     raise EACBrEADException.Create( ACBrStr(AnsiString('Arquivo: ' + ArquivoXML + ' n�o encontrado!')) );

  SL := TStringList.Create;
  try
     SL.LoadFromFile( ArquivoXML );
     LerChave_eECFc( AnsiString( SL.Text ) )
  finally
     SL.Free ;
  end ;

  Bio := CriarMemBIO;
  try
     if PEM_write_bio_PUBKEY(Bio, fsKey) = 1 then
        Result := AnsiString( BioToStr( Bio ) );
  finally
    LiberarBIO( Bio ) ;
  end ;
end ;

procedure TACBrEAD.LerChave_eECFc(ConteudoXML : AnsiString ) ;
Var
  Modulo, Expoente : AnsiString ;
begin
  Modulo   := LerTagXML( ConteudoXML, 'modulo' );
  Expoente := LerTagXML( ConteudoXML, 'expoente_publico' );
  fsIsXMLeECFc := True;

  LerChaveModuloExpoente( Modulo, Expoente );
end ;

procedure TACBrEAD.LerChaveModuloExpoente(Modulo, Expoente: AnsiString);
var
  bnMod, bnExp : PBIGNUM;
  RSAKey : pRSA ;
  Erro   : longint ;
begin
  Modulo := AnsiString(Trim(String(Modulo)));
  if Modulo = '' then
     raise EACBrEADException.Create( ACBrStr('Erro: Modulo n�o informada') ) ;

  Expoente := AnsiString(Trim(String(Expoente)));
  if Expoente = '' then
     raise EACBrEADException.Create( ACBrStr('Erro: Expoente n�o informado') ) ;

  InitOpenSSL ;

  LiberarChave ;

  bnExp := BN_new();
  Erro := BN_hex2bn( bnExp, PAnsiChar(Expoente) );
  if Erro < 1 then
     raise EACBrEADException.Create( ACBrStr('Erro: Expoente inv�lido') ) ;

  bnMod := BN_new();
  Erro := BN_hex2bn( bnMod, PAnsiChar(Modulo) );
  if Erro < 1 then
     raise EACBrEADException.Create( ACBrStr('Erro: Modulo inv�lido') ) ;

  {$IFDEF USE_libeay32}
   fsKey := EVP_PKEY_new;
  {$ELSE}
   fsKey := EvpPkeyNew;
  {$ENDIF}

  RSAKey := RSA_new;
  RSAKey.e := bnMod;
  RSAKey.d := bnExp;

  {$IFDEF USE_libeay32}
    Erro := EVP_PKEY_set1_RSA( fsKey, RSAKey );
  {$ELSE}
    Erro := EvpPkeyAssign( fsKey, EVP_PKEY_RSA, RSAKey );
  {$ENDIF}
  if Erro < 1 then
     raise EACBrEADException.Create('Erro ao atribuir Chave lida');
end ;

procedure TACBrEAD.LerChave(const Chave : AnsiString; Privada: Boolean) ;
var
   A : pEVP_PKEY ;
   BioKey : pBIO ;
   Buffer : AnsiString;
begin
  InitOpenSSL ;
  fsIsXMLeECFc := False;

  Buffer := AnsiString(Trim(String(Chave)));
  if (sLineBreak <> #10) then
     Buffer := AnsiString(StringReplace(String(Buffer), sLineBreak, #10, [rfReplaceAll] ) ) ;

  LiberarChave ;

  BioKey := BIO_new_mem_buf( PAnsiChar(Buffer), Length(Buffer) + 1 ) ;
  try
     A := nil ;
     if Privada then
        fsKey := PEM_read_bio_PrivateKey( BioKey, {$IFDEF USE_libeay32}A{$ELSE}nil{$ENDIF}, nil, nil)
     else
        fsKey := PEM_read_bio_PUBKEY( BioKey, A, nil, nil) ;
  finally
     LiberarBIO( BioKey );
  end ;

  if fsKey = nil then
     raise EACBrEADException.Create('Erro ao ler a Chave');
end ;

procedure TACBrEAD.LiberarChave ;
begin
  if fsKey <> Nil then
  begin
     EVP_PKEY_free( fsKey );
     fsKey := nil;
  end ;
end ;

function TACBrEAD.CriarMemBIO : pBIO ;
begin
  {$IFDEF USE_libeay32}
   Result := Bio_New(Bio_S_Mem());
  {$ELSE}
   Result := BioNew(BioSMem());
  {$ENDIF}
end ;

procedure TACBrEAD.LiberarBIO( Bio : pBIO);
begin
  {$IFDEF USE_libeay32}
   BIO_free_all( Bio );
  {$ELSE}
   BioFreeAll( Bio );
  {$ENDIF}
end ;

function TACBrEAD.GerarXMLeECFc(const NomeSwHouse, Diretorio: String): Boolean;
Var
  Modulo, Expoente : AnsiString ;
  SL : TStringList ;
begin
  Modulo   := '';
  Expoente := '';
  CalcularModuloeExpoente( Modulo, Expoente );

  SL := TStringList.Create;
  try
    SL.Add( '<?xml version="1.0"?>' ) ;
    SL.Add( '' );
    SL.Add( '<empresa_desenvolvedora>' ) ;
    SL.Add( '  <nome>'+NomeSwHouse+'</nome>' ) ;
    SL.Add( '  <chave>' ) ;
    SL.Add( '    <modulo>'+String(Modulo)+'</modulo>' ) ;
    SL.Add( '    <expoente_publico>'+String(Expoente)+'</expoente_publico>' ) ;
    SL.Add( '  </chave>' );
    SL.Add( '</empresa_desenvolvedora>' ) ;

    try
       SL.SaveToFile( PathWithDelim(Diretorio)+NomeSwHouse+'.xml' );
       Result := True;
    except
       Result := False;
    end ;
  finally
     SL.Free;
  end ;
end ;

function TACBrEAD.GerarXMLeECFc(const NomeSwHouse: String): AnsiString;
Var
  Modulo, Expoente : AnsiString ;
  SL : TStringList ;
begin
  Modulo   := '';
  Expoente := '';
  CalcularModuloeExpoente( Modulo, Expoente );

  SL := TStringList.Create;
  try
    SL.Add( '<?xml version="1.0"?>' ) ;
    SL.Add( '' );
    SL.Add( '<empresa_desenvolvedora>' ) ;
    SL.Add( '  <nome>'+NomeSwHouse+'</nome>' ) ;
    SL.Add( '  <chave>' ) ;
    SL.Add( '    <modulo>'+String(Modulo)+'</modulo>' ) ;
    SL.Add( '    <expoente_publico>'+String(Expoente)+'</expoente_publico>' ) ;
    SL.Add( '  </chave>' );
    SL.Add( '</empresa_desenvolvedora>' ) ;

    Result := AnsiString(SL.Text);
  finally
    SL.Free;
  end ;
end ;

Procedure TACBrEAD.CalcularModuloeExpoente( var Modulo, Expoente : AnsiString );
Var
  Bio : pBIO;
  Ver : String ;
begin
  Ver := OpenSSL_Version;
  if pos('1.0',Ver) > 0 then
     raise EACBrEADException.Create( ACBrStr('M�todo CalcularModuloeExpoente ainda n�o � '+
                                     'compat�vel com OpenSSL 1.0.0 ou superior'));

  LerChavePublica;

  Modulo   := '';
  Expoente := '';
  Bio := CriarMemBIO;
  try
    BN_print( Bio , fsKey.pkey.rsa.e);
    Modulo := AnsiString(BioToStr( Bio ));

    BIO_reset( Bio );
    BN_print( Bio , fsKey.pkey.rsa.d);
    Expoente := AnsiString(BioToStr( Bio ));
  finally
    LiberarBIO( Bio ) ;
    LiberarChave;
  end ;
end ;

function TACBrEAD.CalcularChavePublica : AnsiString ;
Var
  Bio : pBIO;
begin
  LerChavePrivada();

  Result := '';
  Bio    := CriarMemBIO;
  try
    if PEM_write_bio_PUBKEY( Bio, fsKey) = 1 then
       Result := AnsiString(BioToStr( Bio ));
  finally
    LiberarBIO( Bio );
    LiberarChave;
  end ;
end ;

function TACBrEAD.CalcularHashArquivo(const NomeArquivo : String;
   const Digest: TACBrEADDgst) : AnsiString ;
Var
   MS : TMemoryStream ;
begin
  VerificaNomeArquivo( NomeArquivo );

  MS := TMemoryStream.Create;
  try
    MS.LoadFromFile( NomeArquivo );
    Result := CalcularHash( MS, Digest );
  finally
    MS.Free ;
  end ;
end ;

function TACBrEAD.CalcularHash(const AString : AnsiString;
   const Digest: TACBrEADDgst) : AnsiString ;
Var
   MS : TMemoryStream ;
begin
  MS := TMemoryStream.Create;
  try
    MS.Write( Pointer(AString)^, Length(AString) );
    Result := CalcularHash( MS, Digest );
  finally
    MS.Free ;
  end ;
end ;

function TACBrEAD.CalcularHash(const AStringList : TStringList;
   const Digest: TACBrEADDgst) : AnsiString ;
Var
  MS : TMemoryStream ;
begin
  MS := TMemoryStream.Create;
  try
    AStringList.SaveToStream( MS );
    Result := CalcularHash( MS, Digest );
  finally
    MS.Free ;
  end ;
end ;

function TACBrEAD.CalcularHash(const MS : TMemoryStream;
   const Digest: TACBrEADDgst) : AnsiString ;
Var
  md : PEVP_MD ;
  md_len: cardinal;
  md_ctx: EVP_MD_CTX;
  md_value_bin : array [0..EVP_MAX_MD_SIZE] of AnsiChar;
  md_value_hex : array [0..1023] of AnsiChar;
  NameDgst : PAnsiChar;
begin
  InitOpenSSL ;
  Result   := '';
  NameDgst := '';

  case Digest of
    dgstMD2    : NameDgst := 'md2';
    dgstMD4    : NameDgst := 'md4';
    dgstMD5    : NameDgst := 'md5';
    dgstRMD160 : NameDgst := 'rmd160';
    dgstSHA    : NameDgst := 'sha';
    dgstSHA1   : NameDgst := 'sha1';
 end ;

  MS.Position := 0;
  md := EVP_get_digestbyname( NameDgst );
  EVP_DigestInit( @md_ctx, md );
  EVP_DigestUpdate( @md_ctx, MS.Memory, MS.Size );
  EVP_DigestFinal( @md_ctx, @md_value_bin, {$IFNDEF USE_libeay32}@{$ENDIF}md_len);

  BinToHex( md_value_bin, md_value_hex, md_len);
  md_value_hex[2 * md_len] := #0;
  Result := AnsiString(StrPas(md_value_hex));
end ;

function TACBrEAD.CalcularEADArquivo(const NomeArquivo : String) : AnsiString ;
Var
   MS : TMemoryStream ;
begin
  VerificaNomeArquivo( NomeArquivo );

  MS := TMemoryStream.Create;
  try
    MS.LoadFromFile( NomeArquivo );
    Result := CalcularEAD( MS );
  finally
    MS.Free ;
  end ;
end ;

function TACBrEAD.CalcularEAD(const AString : AnsiString) : AnsiString ;
Var
   MS : TMemoryStream ;
begin
  MS := TMemoryStream.Create;
  try
    MS.Write( Pointer(AString)^, Length(AString) );
    Result := CalcularEAD( MS );
  finally
    MS.Free ;
  end ;
end ;

function TACBrEAD.CalcularEAD(const AStringList : TStringList) : AnsiString ;
Var
   MS : TMemoryStream ;
begin
  MS := TMemoryStream.Create;
  try
    AStringList.SaveToStream( MS );
    Result := CalcularEAD( MS );
  finally
    MS.Free ;
  end ;
end ;

function TACBrEAD.CalcularEAD(const MS : TMemoryStream) : AnsiString ;
Var
  md : PEVP_MD ;
  md_len: cardinal;
  md_ctx: EVP_MD_CTX;
  //md5_bin : array [0..15] of AnsiChar;
  md_value_hex : array [0..1023] of AnsiChar;
  EADCrypt : array [0..127] of AnsiChar;
  LB : AnsiString ;
  Buffer: AnsiChar;
begin
  Result := '';
  // Verificando se j� existe LF no final do arquivo //
  Buffer := #0;
  MS.Seek(-1, soFromEnd);  // vai para EOF - 1
  MS.Read(Buffer, 1);
  if Buffer <> LF then
  begin
     LB := sLineBreak;
     MS.Write(Pointer(LB)^,Length(LB));
  end ;

  LerChavePrivada;

  try
    MS.Position := 0;
    md_len  := 0;
    md := EVP_get_digestbyname('md5');
    EVP_DigestInit( @md_ctx, md ) ;
    EVP_DigestUpdate( @md_ctx, MS.Memory, MS.Size ) ;
    EVP_SignFinal( @md_ctx, @EADCrypt, md_len, fsKey);

    (*
    // Calculando o Bloco EAD e criptografando-o semelhante ao EVP_SignFinal //

    EVP_DigestFinal( @md_ctx, @md5_bin, {$IFNDEF USE_libeay32}@{$ENDIF}md_len);
    EAD := padL( #16 + copy(StrPas(md5_bin),0,md_len), 128, #0) ;
    EADCrypt := StringOfChar(#0,128);
    md_len := RSA_private_encrypt( 128, @EAD, @EADCrypt, fsKey.pkey.rsa, RSA_NO_PADDING );

    *)
    if md_len <> 128 then
       raise EACBrEADException.Create( 'Erro ao criptografar EAD');

    BinToHex( EADCrypt, md_value_hex, md_len);
    md_value_hex[2 * md_len] := #0;
    Result := AnsiString(StrPas(md_value_hex));

  finally
     LiberarChave;
  end ;
end ;

procedure TACBrEAD.VerificaNomeArquivo( NomeArquivo : String ) ;
begin
  if ( Trim(NomeArquivo) = '' ) then
     raise EACBrEADException.Create( ACBrStr('Nome do arquivo n�o informado!') );

  if not FileExists( NomeArquivo ) then
     raise EACBrEADException.Create( ACBrStr(AnsiString('Arquivo: ' + NomeArquivo + ' n�o encontrado!')) );
end ;


function TACBrEAD.AssinarArquivoComEAD(const NomeArquivo : String ;
  RemoveEADSeExistir : Boolean) : AnsiString ;
Var
  MS : TMemoryStream ;
begin
  // Abrindo o arquivo com FileStream //
  MS := TMemoryStream.Create;
  try
     MS.LoadFromFile( NomeArquivo );

     if RemoveEADSeExistir then
        RemoveEAD( MS );

     Result := CalcularEAD( MS );

     if Result <> '' then
     begin
        MS.Seek(0,soFromEnd);
        MS.Write(Pointer('EAD'+Result)^,Length(Result)+3);

        MS.SaveToFile( NomeArquivo );
     end ;
  finally
     MS.Free ;
  end;
end;

procedure TACBrEAD.RemoveEADArquivo(const NomeArquivo : String) ;
Var
  MS : TMemoryStream ;
  EAD : AnsiString;
begin
  VerificaNomeArquivo( NomeArquivo );

  MS := TMemoryStream.Create;
  try
    MS.LoadFromFile( NomeArquivo );
    EAD := AnsiString( RemoveEAD( MS ) );
    if EAD <> '' then
       MS.SaveToFile( NomeArquivo );
  finally
    MS.Free;
  end;
end ;

function TACBrEAD.RemoveEAD(MS : TMemoryStream) : String ;
Var
  Buffer: array[0..259] of AnsiChar;
begin
  // Verificando se tem CRLF no final da linha do EAD //
  Buffer := #0;
  MS.Seek(-1, soFromEnd);  // vai para EOF - 1
  MS.Read(Buffer, 1);
  while (Buffer[0] in [CR, LF]) do
  begin
     Buffer := #0;
     MS.Seek(-2, soFromCurrent);  // Volta 2
     MS.Read(Buffer, 1);
  end ;

  // Procurando por ultimo EAD //
  Buffer[0] := #0;
  MS.Seek(-259,soFromCurrent);     // 259 = Tamanho da Linha EAD
  MS.Read(Buffer, 259 );
  Result := UpperCase( Trim( String( Buffer ) ) );

  // Removendo o EAD do MemoryStream //
  if copy(Result,1,3) = 'EAD' then
     MS.Size := MS.Position-259
  else
     Result := '';
end ;

function TACBrEAD.VerificarEADArquivo(const NomeArquivo : String) : Boolean ;
Var
  MS : TMemoryStream ;
begin
  VerificaNomeArquivo( NomeArquivo );

  MS := TMemoryStream.Create;
  try
    MS.LoadFromFile( NomeArquivo );
    Result := VerificarEAD( MS );
  finally
    MS.Free;
  end;
end ;

function TACBrEAD.VerificarEAD(const AString : AnsiString) : Boolean ;
Var
  MS : TMemoryStream ;
begin
  MS := TMemoryStream.Create;
  try
    MS.Write( Pointer(AString)^, Length(AString) );
    Result := VerificarEAD( MS );
  finally
    MS.Free;
  end;
end ;

function TACBrEAD.VerificarEAD(const AStringList : TStringList) : Boolean ;
Var
  MS : TMemoryStream ;
  EAD : AnsiString ;
  SLBottom : Integer ;
begin
  if AStringList.Count < 1 then
     raise EACBrEADException.Create( ACBrStr('Conteudo Informado � vazio' ) );

  SLBottom := AStringList.Count-1;                 // Pega a �ltima linha do arquivo,
  EAD := AnsiString( AStringList[ SLBottom ] ) ;   // pois ela contem o EAD, e depois,
  AStringList.Delete( SLBottom );                  // remove a linha do EAD

  MS := TMemoryStream.Create;
  try
    AStringList.SaveToStream( MS );
    Result := VerificarEAD( MS, String( EAD ) );
  finally
    MS.Free ;
  end ;
end ;

function TACBrEAD.VerificarEAD(const MS : TMemoryStream ; EAD : String
  ) : Boolean ;
Var
  md : PEVP_MD ;
  md_len: cardinal;
  md_ctx: EVP_MD_CTX;
  EAD_crypt, EAD_decrypt : array [0..127] of AnsiChar;
  md5_bin : array [0..15] of AnsiChar;
  Ret : LongInt ;
begin
  //Result := False;

  EAD := Trim(EAD);

  // N�o enviou EAD ?, ent�o ache e Remova a linha do EAD no MemoryStream
  if EAD = '' then
     EAD := RemoveEAD( MS );

  // Remove "EAD" do inicio da linha
  if UpperCase(String(copy(EAD,1,3))) = 'EAD' then
     EAD := copy(EAD,4,Length(EAD));

  if EAD = '' then
     raise EACBrEADException.Create( ACBrStr('Registro EAD n�o informado') );

  // Convertendo o EAD para bin�rio //
  md_len := trunc(Length(EAD) / 2);
  if md_len <> 128 then
     raise EACBrEADException.Create('EAD deve conter 256 caracteres');
  HexToBin( PAnsiChar(AnsiString(EAD)), EAD_crypt, md_len );

  LerChavePublica;

  try
    // Fazendo verifica��o tradicional de SignDigest
    MS.Position := 0;
    md := EVP_get_digestbyname('md5');
    EVP_DigestInit( @md_ctx, md ) ;
    EVP_DigestUpdate( @md_ctx, MS.Memory, MS.Size ) ;
    Ret := EVP_VerifyFinal( @md_ctx, @EAD_crypt, md_len, fsKey) ;

    Result := (Ret = 1);

    // Se falhou, faz verifica��o manual do EAD
    if not Result then
    begin
       // Calculando o MD5 do arquivo sem a linha do EAD salva em "md5_bin" //
       EVP_DigestFinal( @md_ctx, @md5_bin, {$IFNDEF USE_libeay32}@{$ENDIF}md_len);
       if md_len <> 16 then
          raise EACBrEADException.Create('Erro ao calcular MD5 do arquivo sem EAD');

       // Descriptografando o EAD //
       md_len := RSA_public_decrypt( 128, @EAD_crypt, @EAD_decrypt,
                                     fsKey.pkey.rsa, RSA_NO_PADDING);
       if md_len <> 128 then
          raise EACBrEADException.Create('Erro ao descriptografar EAD');

       Result := (pos( md5_bin, EAD_decrypt ) > 0) ;
    end ;

    if (not Result)  then
    begin
       { Se o aqruivo foi assinado pelos fabricantes como: Bematech, Itautec,
         etc, ent�o o MD5 � criptografado antes de rodar a criptografia do RSA
         (sic)... nesse caso n�o temos como conferir o MD5 a n�o ser usando a
         DLL do eECFc (que ser� desenvolvida) }
       raise EACBrEADException.Create(
        'N�o foi poss�vel verificar a assinatura do arquivo:' + sLineBreak +
        sLineBreak +
        'Verifique se a chave informada � mesmo a chave correta antes de continuar.' + sLineBreak +
        sLineBreak +
        'Verifique tamb�m se o arquivo foi assinado com a DLL de algum fabricante de ' +
        'impressoras fiscais, os fabricantes de impressoras fiscais criptografam o MD5 ' +
        'do arquivo antes de efetuar a criptografia para a assinatura EAD o que torna ' +
        'poss�vel a verifica��o da assinatura somente utilizando o aplicativo eECFc, ' +
        'somente este aplicativo possui as rotinas de descriptografia para cada fabricante.'
       );
    end ;

  finally
     LiberarChave;
  end ;
end ;

end.

