unit LogosWSIntf;

interface

uses InvokeRegistry, Types, XSBuiltIns;

type

  ILogosWS = interface(IInvokable)
  ['{F834357E-A7C6-4C81-B028-1A1A0A3B9176}']
    function AutenticaUsuario(pDsLogin : WideString;
                              pDsSenha : WideString;
                          var pCdUsuari: Integer): WideString; stdcall;

    function ProximaAtividade(pCdUsuari : Integer;
                          var pNrMapa   : WideString;
                          var pDsAtivid : WideString): WideString; stdcall;

    function ConsultaAtividade(pNrFornec: WideString;
                                    pSgTipAti: WideString;
                                var pNrOpeLog: WideString): WideString; stdcall;

    function AtividadeNaoConvocada(pCdUsuari: Integer;
                                   var pDsLista : WideString): WideString; stdcall;

    function ConferenciaEntradaIniciar(pCdUsuari : Integer;
                                       pNrMapa   : WideString;
                                   var pNrNota   : WideString;
                                   var pNrFornec : WideString;
                                   var pDsLista  : WideString): WideString; stdcall;

   function ConferenciaEntradaFinalizar(pCdUsuari  : Integer;
                                        pNrMapa    : WideString;
                                        pdslista   : WideString;
                                        pSnCancel  : WideString;
                                        pQtProSid  : Integer): WideString; stdcall;

    function ConferenciaSaidaIniciar(pCdUsuari : Integer;
                                     pNrMapa   : WideString;
                                 var pNrNota   : WideString;
                                 var pNrFornec : WideString;
                                 var pDsLista  : WideString): WideString; stdcall;

   function ConferenciaSaidaFinalizar(pCdUsuari  : Integer;
                                      pNrMapa    : WideString;
                                      pdslista   : WideString;
                                      pCdClient  : WideString;
                                      pSnCancel  : WideString;
                                      pQtProSid  : Integer): WideString; stdcall;

    function Ping: WideString; stdcall;

    function TestaConexao(pIDTest: WideString; pMsnTest: WideString): WideString; stdcall;

    function ConsultaPalletEnderecamento(pCdUsuari: Integer; pNrOrdem: WideString;
                                         var pDsLista: WideString): WideString; stdcall;

    function FechaPalletEnderecamento(pNsPallet: AnsiString): AnsiString; stdcall;
  end;

implementation

initialization
  InvRegistry.RegisterInterface(TypeInfo(ILogosWS));

end.
