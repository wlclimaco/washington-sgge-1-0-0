{******************************************************************************}
{ Projeto: Componentes ACBr                                                    }
{  Biblioteca multiplataforma de componentes Delphi para intera��o com equipa- }
{ mentos de Automa��o Comercial utilizados no Brasil                           }
{                                                                              }
{ Direitos Autorais Reservados (c) 2006 Vinicius de Oliveira                   }
{                                        Daniel Sim�es de Almeida              }
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
{ http://www.opensource.org/licenses/gpl-license.php                           }
{                                                                              }
{ Daniel Sim�es de Almeida  -  daniel@djsystem.com.br  -  www.djsystem.com.br  }
{              Pra�a Anita Costa, 34 - Tatu� - SP - 18270-410                  }
{                                                                              }
{******************************************************************************}

{******************************************************************************
|* Observa�ao LINUX: Os arquivos de SOM no Linux sao processados por um
|* Utilit�rio externo como por exemplo o "play" disponivel no Pacote
|* sox*.rpm  -  http://sox.sourceforge.net
******************************************************************************}

{******************************************************************************
|* Historico
|*
|* 23/02/2006:  Vinicius de Oliveira
|*   Primeira Versao: Cria�ao e Distribui�ao da Primeira Versao FalaValor
|* 04/03/2006:  Daniel Sim�es de Almeida
|*   - Modificado FalaValor para ACBrFala
|*   - Adicionado suporte a Kylix e Lazarus (Win32 / Linux)
|* 30/09/2006:  Daniel Sim�es de Almeida
|*   - Corrigido Bug na fala de Valores com apenas centavos
******************************************************************************}

{$I ACBr.inc}

unit ACBrFala;

interface

uses ACBrBase,
  {$IFDEF MSWINDOWS} Windows, MMSystem, {$ENDIF}
  {$IFDEF COMPILER6_UP} Types, {$ENDIF}
  {$IFDEF FPC} LResources, {$ENDIF}
  SysUtils, Classes ;

const
  cCharsSeparadores = ' ,;|' ;

type
  TLocalSons = (lsDiretorio, lsRecurso);

  TACBrFala = class( TACBrComponent )
  private
    { Vari�veis internas }
    FListaArquivos : TStringList ;
    { Propriedades Read/Write }
    FOrigemArquivos: string;
    FLocalSons: TLocalSons;
    FStrFalar: string;
    FValorFalar : Double ;
    FCharsSeparadores: String;
    FRemoveAvencos: Boolean;
    FIgnorarCaixa: Boolean;
    FExtensaoSons: String;
    FLinuxComando: String;
    FValorDinheiro: Boolean;
    { Procedures e functions internos }
    procedure ProcessaSom(Palavra: string);
    procedure SetStrFalar(const Value: string);
    procedure SetValorFalar(const Value: Double);
    procedure SetOrigemArquivos(const Value: string);
    procedure SetExtensaoSons(const Value: String);
    procedure SetLocalSons(const Value: TLocalSons);
  public
    constructor Create(AOwner: TComponent); override;
    destructor Destroy; override;
    { M�todos }
    procedure Falar ;
    Procedure CarregaArquivosSons ;

  Published
    property OrigemArquivos : string read FOrigemArquivos
       write SetOrigemArquivos;
    property LocalSons  : TLocalSons read FLocalSons write SetLocalSons
       default lsDiretorio ;
    property ExtensaoSons : String   read FExtensaoSons   write SetExtensaoSons ;
    property StrFalar   : string read FStrFalar   write SetStrFalar
       stored false ;
    property ValorFalar : Double  read FValorFalar write SetValorFalar
       stored false ;
    property CharsSeparadores : String read FCharsSeparadores
       write FCharsSeparadores ;
    property RemoveAcentos  : Boolean read FRemoveAvencos
       write FRemoveAvencos default true ;
    property IgnorarCaixa   : Boolean read FIgnorarCaixa
       write FIgnorarCaixa  default true ;
    property LinuxComando : String read FLinuxComando write FLinuxComando ;
    property ValorDinheiro : Boolean read FValorDinheiro write FValorDinheiro
       default True ; 
  end;

implementation
Uses {$IFNDEF COMPILER6_UP} ACBrD5,{$ENDIF} ACBrUtil, ACBrExtenso ;

{ **** TACBrFala **** }
constructor TACBrFala.Create(AOwner: TComponent); 
begin
  inherited Create( AOwner ) ;
  
  FOrigemArquivos   := '';
  FLocalSons        := lsDiretorio;
  FExtensaoSons     := '.wav' ;
  FStrFalar         := '' ;
  FValorFalar       := 0 ;
  FCharsSeparadores := cCharsSeparadores ;
  FRemoveAvencos    := True ;
  FIgnorarCaixa     := True ;
  FListaArquivos    := TStringList.Create ;
  FLinuxComando     := 'play' ;
  FValorDinheiro    := True ; 
end;

destructor TACBrFala.Destroy;
begin
  FListaArquivos.Free ;
  
  inherited;
end;

procedure TACBrFala.SetStrFalar(const Value: string);
begin
  FValorFalar := 0 ;
  FStrFalar   := Trim( Value );

  if RemoveAcentos then
     FStrFalar := TiraAcentos(FStrFalar) ;
end;

procedure TACBrFala.SetValorFalar(const Value: Double);
Var ACBrExtenso1 : TACBrExtenso ;
begin
  FValorFalar  := 0;
  ACBrExtenso1 := TACBrExtenso.Create(Self);
  try
     if not FValorDinheiro then
      begin
        ACBrExtenso1.StrMoeda   := '' ;
        ACBrExtenso1.StrMoedas  := '' ;
        ACBrExtenso1.StrCentavo := '' ;
        ACBrExtenso1.StrCentavos:= '' ;
      end
     else
        ACBrExtenso1.ZeroAEsquerda := False ;
     
     ACBrExtenso1.Valor := Value ;
     StrFalar := ACBrExtenso1.Texto ;
     FValorFalar := Value;
  except
     ACBrExtenso1.Free ;
  end ;
end;

procedure TACBrFala.SetOrigemArquivos(const Value: string);
begin
  if FOrigemArquivos = Value then exit ;

  FOrigemArquivos := Trim(Value) ;
  FListaArquivos.Clear ;
end;

procedure TACBrFala.SetExtensaoSons(const Value: String);
begin
  if FExtensaoSons = Value then exit ;

  FExtensaoSons := Trim(Value) ;
  FListaArquivos.Clear ;
end;

procedure TACBrFala.SetLocalSons(const Value: TLocalSons);
begin
  if FLocalSons = Value then exit ;

  FLocalSons := Value;
  FListaArquivos.Clear ;
end;

procedure TACBrFala.CarregaArquivosSons;
Var SearchRec : TSearchRec ;
    RetFind   : Integer ;
    LastFile  : string ;
begin
  FListaArquivos.Clear ;
  
  if FLocalSons = lsDiretorio then
  begin
     RetFind := SysUtils.FindFirst( FOrigemArquivos+PathDelim+'*'+FExtensaoSons,
                                    faAnyFile, SearchRec) ;
     try
        while (RetFind = 0) and (LastFile <> SearchRec.Name) do
        begin
           LastFile := SearchRec.Name ;
           if (pos(LastFile, '..') = 0) then   { ignora . e .. }
	          {$IFNDEF COMPILER6_UP}
              if FIgnorarCaixa then
                 FListaArquivos.Add(LowerCase(Trim(LastFile))) ;
              {$ELSE}
              FListaArquivos.Add(Trim(LastFile)) ;
              {$ENDIF}

           SysUtils.FindNext(SearchRec) ;
        end ;
     finally
        SysUtils.FindClose(SearchRec) ;
     end ;

     if FListaArquivos.Count = 0 then
        raise Exception.Create('Nenhum arquivo *'+FExtensaoSons + sLineBreak +
                               ' encontrado na pasta: '+FOrigemArquivos) ;
  end ;
end;

procedure TACBrFala.Falar ;
var
  I: Integer;
  Palavra : string;
begin
  if FStrFalar = '' then exit ;

  I       := 0 ;
  Palavra := '' ;
  while (I < Length(FStrFalar)) do
  begin
     Inc( I ) ;
     if pos(FStrFalar[I], FCharsSeparadores) > 0 then  { Achou um seprador }
     begin
        if Palavra <> '' then
           ProcessaSom( Palavra );

        Palavra := '' ;
        Continue ;
     end ;

     Palavra := Palavra + FStrFalar[I]
  end ;

  if Palavra <> '' then
     ProcessaSom( Palavra );

 {$IFDEF LINUX}
  if FLocalSons = lsRecurso then
     SysUtils.DeleteFile('ACBr_SomTemp.wav') ;
 {$ENDIF}
end;

procedure TACBrFala.ProcessaSom(Palavra: string);
var
 Pos : Integer ;
 {$IFDEF FPC}
    Stream : TLResource ;
 {$ELSE}
   {$IFDEF LINUX}
      Stream: TResourceStream;
   {$ENDIF}
 {$ENDIF}
 {$IFDEF MSWINDOWS}
  HFind, HRes: THandle;
  Som: PChar;
 {$ENDIF}
begin
  Palavra := Trim(Palavra) ;
  if FIgnorarCaixa then
     Palavra := LowerCase( Palavra ) ;
     
  case FLocalSons of
    lsDiretorio:
      begin
        Palavra := Palavra + FExtensaoSons ;

        if FListaArquivos.Count = 0 then
           CarregaArquivosSons ;

        {$IFDEF COMPILER6_UP}
        FListaArquivos.CaseSensitive := not FIgnorarCaixa ;
        {$ENDIF}
        Pos := FListaArquivos.IndexOf(Palavra) ;

        if Pos >= 0 then
           {$IFDEF MSWINDOWS}
             SndPlaySound(Pchar(FOrigemArquivos+PathDelim+FListaArquivos[Pos])
                          , snd_Sync or snd_FileName);
           {$ELSE}
             Runcommand( FLinuxComando ,
                         FOrigemArquivos+PathDelim+FListaArquivos[Pos], true)
           {$ENDIF}
      end;

    lsRecurso:
      begin
        { Nota: Kylix apenas suporta Resources do tipo RCDATA  Veja detalhes em:
          http://www.swissdelphicenter.ch/en/showcode.php?id=1060 }

      {$IFDEF MSWINDOWS}
        HFind := FindResource(HInstance, PChar(Palavra), RT_RCDATA );
        {$IFDEF FPC}
         if HFind = 0 then  { Nao Achou como .RES... Tenta ler como .LRS  }
         begin              { (LazarusResource) compilada com LAZRES }
            Stream := LazarusResources.Find(Palavra) ;
            if Assigned(Stream) then
            begin
               Som := Pchar(Stream.Value);
               SndPlaySound(Som, snd_Sync or snd_Memory);
            end ;
         end ;
        {$ENDIF}

        if HFind <> 0 then
        begin
          HRes := LoadResource(HInstance, HFind);
          if HRes <> 0 then
          begin
            Som := LockResource(HRes);
            if Assigned(Som) then
              SndPlaySound(Som, snd_Sync or snd_Memory);
            UnlockResource(HRes);
          end;
          FreeResource(HFind);
        end;
      {$ELSE}
        {$IFDEF FPC}
         Stream := LazarusResources.Find(Palavra);
         if Assigned(Stream) then
            with TFileStream.Create('ACBr_SomTemp.wav', fmCreate) do
            begin
               Write( Stream.Value[1], Length(Stream.Value ));
               Free;
            end ;
        {$ELSE}
         Stream := TResourceStream.Create(HInstance, PChar(Palavra), RT_RCDATA);
         Try
            with TFileStream.Create('ACBr_SomTemp.wav', fmCreate) do
            begin
               CopyFrom(Stream, Stream.Size);
               Free;
            end;
         finally
            Stream.Free ;
         end ;
        {$ENDIF}
        if FileExists('ACBr_SomTemp.wav') then
           Runcommand(FLinuxComando ,'ACBr_SomTemp.wav', true) ;

      {$ENDIF}
      end;
  end;
end;

end.
