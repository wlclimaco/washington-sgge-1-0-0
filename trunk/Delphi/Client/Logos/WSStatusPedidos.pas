// ************************************************************************ //
// The types declared in this file were generated from data read from the
// WSDL File described below:
// WSDL     : https://www.projetodistribuicao.com.br/WSStatuspedido/wstracktrace.asmx?wsdl
//  >Import : https://www.projetodistribuicao.com.br/WSStatuspedido/wstracktrace.asmx?wsdl>0
// Encoding : utf-8
// Version  : 1.0
// (17/10/2012 14:57:56 - - $Rev: 28374 $)
// ************************************************************************ //

unit WSStatusPedidos;

interface

uses InvokeRegistry, SOAPHTTPClient, Types, XSBuiltIns;

const
  IS_OPTN = $0001;
  IS_REF  = $0080;


type

  // ************************************************************************ //
  // The following types, referred to in the WSDL document are not being represented
  // in this file. They are either aliases[@] of other types represented or were referred
  // to but never[!] declared in the document. The types from the latter category
  // typically map to predefined/known XML or Embarcadero types; however, they could also
  // indicate incorrect WSDL documents that failed to declare or import a schema type.
  // ************************************************************************ //
  // !:string          - "http://www.w3.org/2001/XMLSchema"[Gbl]
  // !:boolean         - "http://www.w3.org/2001/XMLSchema"[Gbl]
  // !:base64Binary    - "http://www.w3.org/2001/XMLSchema"[Gbl]

  tbl                  = class;                 { "http://tempuri.org/"[Cplx] }
  ImportDataTableTrackTrace = class;            { "http://tempuri.org/"[Lit][GblElm] }
  ImportDataTableTrackTraceResponse = class;    { "http://tempuri.org/"[Lit][GblElm] }
  ImportStringTrackTrace = class;               { "http://tempuri.org/"[Lit][GblElm] }
  ImportStringTrackTraceResponse = class;       { "http://tempuri.org/"[Lit][GblElm] }
  ImportBytesTrackTrace = class;                { "http://tempuri.org/"[Lit][GblElm] }
  ImportBytesTrackTraceResponse = class;        { "http://tempuri.org/"[Lit][GblElm] }
  ExportStringTrackTrace = class;               { "http://tempuri.org/"[Lit][GblElm] }
  ExportStringTrackTraceResponse = class;       { "http://tempuri.org/"[Lit][GblElm] }



  // ************************************************************************ //
  // XML       : tbl, <complexType>
  // Namespace : http://tempuri.org/
  // ************************************************************************ //
  tbl = class(TRemotable)
  private
  published
  end;



  // ************************************************************************ //
  // XML       : ImportDataTableTrackTrace, global, <element>
  // Namespace : http://tempuri.org/
  // Serializtn: [xoLiteralParam]
  // Info      : Wrapper
  // ************************************************************************ //
  ImportDataTableTrackTrace = class(TRemotable)
  private
    Ftbl: tbl;
    Ftbl_Specified: boolean;
    FCarrierEDI: string;
    FCarrierEDI_Specified: boolean;
    Fpassword: string;
    Fpassword_Specified: boolean;
    procedure Settbl(Index: Integer; const Atbl: tbl);
    function  tbl_Specified(Index: Integer): boolean;
    procedure SetCarrierEDI(Index: Integer; const Astring: string);
    function  CarrierEDI_Specified(Index: Integer): boolean;
    procedure Setpassword(Index: Integer; const Astring: string);
    function  password_Specified(Index: Integer): boolean;
  public
    constructor Create; override;
    destructor Destroy; override;
  published
    property tbl:        tbl     Index (IS_OPTN) read Ftbl write Settbl stored tbl_Specified;
    property CarrierEDI: string  Index (IS_OPTN) read FCarrierEDI write SetCarrierEDI stored CarrierEDI_Specified;
    property password:   string  Index (IS_OPTN) read Fpassword write Setpassword stored password_Specified;
  end;



  // ************************************************************************ //
  // XML       : ImportDataTableTrackTraceResponse, global, <element>
  // Namespace : http://tempuri.org/
  // Serializtn: [xoLiteralParam]
  // Info      : Wrapper
  // ************************************************************************ //
  ImportDataTableTrackTraceResponse = class(TRemotable)
  private
    FImportDataTableTrackTraceResult: Boolean;
    Fmensagens: string;
    Fmensagens_Specified: boolean;
    procedure Setmensagens(Index: Integer; const Astring: string);
    function  mensagens_Specified(Index: Integer): boolean;
  public
    constructor Create; override;
  published
    property ImportDataTableTrackTraceResult: Boolean  read FImportDataTableTrackTraceResult write FImportDataTableTrackTraceResult;
    property mensagens:                       string   Index (IS_OPTN) read Fmensagens write Setmensagens stored mensagens_Specified;
  end;



  // ************************************************************************ //
  // XML       : ImportStringTrackTrace, global, <element>
  // Namespace : http://tempuri.org/
  // Serializtn: [xoLiteralParam]
  // Info      : Wrapper
  // ************************************************************************ //
  ImportStringTrackTrace = class(TRemotable)
  private
    Fstr: string;
    Fstr_Specified: boolean;
    FCarrierEDI: string;
    FCarrierEDI_Specified: boolean;
    Fpassword: string;
    Fpassword_Specified: boolean;
    procedure Setstr(Index: Integer; const Astring: string);
    function  str_Specified(Index: Integer): boolean;
    procedure SetCarrierEDI(Index: Integer; const Astring: string);
    function  CarrierEDI_Specified(Index: Integer): boolean;
    procedure Setpassword(Index: Integer; const Astring: string);
    function  password_Specified(Index: Integer): boolean;
  public
    constructor Create; override;
  published
    property str:        string  Index (IS_OPTN) read Fstr write Setstr stored str_Specified;
    property CarrierEDI: string  Index (IS_OPTN) read FCarrierEDI write SetCarrierEDI stored CarrierEDI_Specified;
    property password:   string  Index (IS_OPTN) read Fpassword write Setpassword stored password_Specified;
  end;



  // ************************************************************************ //
  // XML       : ImportStringTrackTraceResponse, global, <element>
  // Namespace : http://tempuri.org/
  // Serializtn: [xoLiteralParam]
  // Info      : Wrapper
  // ************************************************************************ //
  ImportStringTrackTraceResponse = class(TRemotable)
  private
    FImportStringTrackTraceResult: Boolean;
    Fmensagens: string;
    Fmensagens_Specified: boolean;
    procedure Setmensagens(Index: Integer; const Astring: string);
    function  mensagens_Specified(Index: Integer): boolean;
  public
    constructor Create; override;
  published
    property ImportStringTrackTraceResult: Boolean  read FImportStringTrackTraceResult write FImportStringTrackTraceResult;
    property mensagens:                    string   Index (IS_OPTN) read Fmensagens write Setmensagens stored mensagens_Specified;
  end;



  // ************************************************************************ //
  // XML       : ImportBytesTrackTrace, global, <element>
  // Namespace : http://tempuri.org/
  // Serializtn: [xoLiteralParam]
  // Info      : Wrapper
  // ************************************************************************ //
  ImportBytesTrackTrace = class(TRemotable)
  private
    Ffile_: TByteDynArray;
    Ffile__Specified: boolean;
    FCarrierEDI: string;
    FCarrierEDI_Specified: boolean;
    Fpassword: string;
    Fpassword_Specified: boolean;
    procedure Setfile_(Index: Integer; const ATByteDynArray: TByteDynArray);
    function  file__Specified(Index: Integer): boolean;
    procedure SetCarrierEDI(Index: Integer; const Astring: string);
    function  CarrierEDI_Specified(Index: Integer): boolean;
    procedure Setpassword(Index: Integer; const Astring: string);
    function  password_Specified(Index: Integer): boolean;
  public
    constructor Create; override;
  published
    property file_:      TByteDynArray  Index (IS_OPTN) read Ffile_ write Setfile_ stored file__Specified;
    property CarrierEDI: string         Index (IS_OPTN) read FCarrierEDI write SetCarrierEDI stored CarrierEDI_Specified;
    property password:   string         Index (IS_OPTN) read Fpassword write Setpassword stored password_Specified;
  end;



  // ************************************************************************ //
  // XML       : ImportBytesTrackTraceResponse, global, <element>
  // Namespace : http://tempuri.org/
  // Serializtn: [xoLiteralParam]
  // Info      : Wrapper
  // ************************************************************************ //
  ImportBytesTrackTraceResponse = class(TRemotable)
  private
    FImportBytesTrackTraceResult: Boolean;
    Fmensagens: string;
    Fmensagens_Specified: boolean;
    procedure Setmensagens(Index: Integer; const Astring: string);
    function  mensagens_Specified(Index: Integer): boolean;
  public
    constructor Create; override;
  published
    property ImportBytesTrackTraceResult: Boolean  read FImportBytesTrackTraceResult write FImportBytesTrackTraceResult;
    property mensagens:                   string   Index (IS_OPTN) read Fmensagens write Setmensagens stored mensagens_Specified;
  end;



  // ************************************************************************ //
  // XML       : ExportStringTrackTrace, global, <element>
  // Namespace : http://tempuri.org/
  // Serializtn: [xoLiteralParam]
  // Info      : Wrapper
  // ************************************************************************ //
  ExportStringTrackTrace = class(TRemotable)
  private
    FstrIdTransportadora: string;
    FstrIdTransportadora_Specified: boolean;
    FstrSenha: string;
    FstrSenha_Specified: boolean;
    procedure SetstrIdTransportadora(Index: Integer; const Astring: string);
    function  strIdTransportadora_Specified(Index: Integer): boolean;
    procedure SetstrSenha(Index: Integer; const Astring: string);
    function  strSenha_Specified(Index: Integer): boolean;
  public
    constructor Create; override;
  published
    property strIdTransportadora: string  Index (IS_OPTN) read FstrIdTransportadora write SetstrIdTransportadora stored strIdTransportadora_Specified;
    property strSenha:            string  Index (IS_OPTN) read FstrSenha write SetstrSenha stored strSenha_Specified;
  end;



  // ************************************************************************ //
  // XML       : ExportStringTrackTraceResponse, global, <element>
  // Namespace : http://tempuri.org/
  // Serializtn: [xoLiteralParam]
  // Info      : Wrapper
  // ************************************************************************ //
  ExportStringTrackTraceResponse = class(TRemotable)
  private
    FExportStringTrackTraceResult: string;
    FExportStringTrackTraceResult_Specified: boolean;
    procedure SetExportStringTrackTraceResult(Index: Integer; const Astring: string);
    function  ExportStringTrackTraceResult_Specified(Index: Integer): boolean;
  public
    constructor Create; override;
  published
    property ExportStringTrackTraceResult: string  Index (IS_OPTN) read FExportStringTrackTraceResult write SetExportStringTrackTraceResult stored ExportStringTrackTraceResult_Specified;
  end;


  // ************************************************************************ //
  // Namespace : http://tempuri.org/
  // soapAction: http://tempuri.org/%operationName%
  // transport : http://schemas.xmlsoap.org/soap/http
  // style     : document
  // binding   : WSTrackTraceSoap12
  // service   : WSTrackTrace
  // port      : WSTrackTraceSoap12
  // URL       : https://www.projetodistribuicao.com.br/WSStatuspedido/wstracktrace.asmx
  // ************************************************************************ //
  WSTrackTraceSoap = interface(IInvokable)
  ['{8B570622-1DC7-AF7E-728F-5EDC6D723636}']

    // Cannot unwrap:
    //     - More than one strictly out element was found
    function  ImportDataTableTrackTrace(const parameters: ImportDataTableTrackTrace): ImportDataTableTrackTraceResponse; stdcall;

    // Cannot unwrap:
    //     - More than one strictly out element was found
    function  ImportStringTrackTrace(const parameters: ImportStringTrackTrace): ImportStringTrackTraceResponse; stdcall;

    // Cannot unwrap:
    //     - More than one strictly out element was found
    function  ImportBytesTrackTrace(const parameters: ImportBytesTrackTrace): ImportBytesTrackTraceResponse; stdcall;
    function  ExportStringTrackTrace(const parameters: ExportStringTrackTrace): ExportStringTrackTraceResponse; stdcall;
  end;

function GetWSTrackTraceSoap(UseWSDL: Boolean=System.False; Addr: string=''; HTTPRIO: THTTPRIO = nil): WSTrackTraceSoap;


implementation
  uses SysUtils;

function GetWSTrackTraceSoap(UseWSDL: Boolean; Addr: string; HTTPRIO: THTTPRIO): WSTrackTraceSoap;
const
  defWSDL = 'https://www.projetodistribuicao.com.br/WSStatuspedido/wstracktrace.asmx?wsdl';
  defURL  = 'https://www.projetodistribuicao.com.br/WSStatuspedido/wstracktrace.asmx';
  defSvc  = 'WSTrackTrace';
  defPrt  = 'WSTrackTraceSoap12';
var
  RIO: THTTPRIO;
begin
  Result := nil;
  if (Addr = '') then
  begin
    if UseWSDL then
      Addr := defWSDL
    else
      Addr := defURL;
  end;
  if HTTPRIO = nil then
    RIO := THTTPRIO.Create(nil)
  else
    RIO := HTTPRIO;
  try
    Result := (RIO as WSTrackTraceSoap);
    if UseWSDL then
    begin
      RIO.WSDLLocation := Addr;
      RIO.Service := defSvc;
      RIO.Port := defPrt;
    end else
      RIO.URL := Addr;
  finally
    if (Result = nil) and (HTTPRIO = nil) then
      RIO.Free;
  end;
end;


constructor ImportDataTableTrackTrace.Create;
begin
  inherited Create;
  FSerializationOptions := [xoLiteralParam];
end;

destructor ImportDataTableTrackTrace.Destroy;
begin
  SysUtils.FreeAndNil(Ftbl);
  inherited Destroy;
end;

procedure ImportDataTableTrackTrace.Settbl(Index: Integer; const Atbl: tbl);
begin
  Ftbl := Atbl;
  Ftbl_Specified := True;
end;

function ImportDataTableTrackTrace.tbl_Specified(Index: Integer): boolean;
begin
  Result := Ftbl_Specified;
end;

procedure ImportDataTableTrackTrace.SetCarrierEDI(Index: Integer; const Astring: string);
begin
  FCarrierEDI := Astring;
  FCarrierEDI_Specified := True;
end;

function ImportDataTableTrackTrace.CarrierEDI_Specified(Index: Integer): boolean;
begin
  Result := FCarrierEDI_Specified;
end;

procedure ImportDataTableTrackTrace.Setpassword(Index: Integer; const Astring: string);
begin
  Fpassword := Astring;
  Fpassword_Specified := True;
end;

function ImportDataTableTrackTrace.password_Specified(Index: Integer): boolean;
begin
  Result := Fpassword_Specified;
end;

constructor ImportDataTableTrackTraceResponse.Create;
begin
  inherited Create;
  FSerializationOptions := [xoLiteralParam];
end;

procedure ImportDataTableTrackTraceResponse.Setmensagens(Index: Integer; const Astring: string);
begin
  Fmensagens := Astring;
  Fmensagens_Specified := True;
end;

function ImportDataTableTrackTraceResponse.mensagens_Specified(Index: Integer): boolean;
begin
  Result := Fmensagens_Specified;
end;

constructor ImportStringTrackTrace.Create;
begin
  inherited Create;
  FSerializationOptions := [xoLiteralParam];
end;

procedure ImportStringTrackTrace.Setstr(Index: Integer; const Astring: string);
begin
  Fstr := Astring;
  Fstr_Specified := True;
end;

function ImportStringTrackTrace.str_Specified(Index: Integer): boolean;
begin
  Result := Fstr_Specified;
end;

procedure ImportStringTrackTrace.SetCarrierEDI(Index: Integer; const Astring: string);
begin
  FCarrierEDI := Astring;
  FCarrierEDI_Specified := True;
end;

function ImportStringTrackTrace.CarrierEDI_Specified(Index: Integer): boolean;
begin
  Result := FCarrierEDI_Specified;
end;

procedure ImportStringTrackTrace.Setpassword(Index: Integer; const Astring: string);
begin
  Fpassword := Astring;
  Fpassword_Specified := True;
end;

function ImportStringTrackTrace.password_Specified(Index: Integer): boolean;
begin
  Result := Fpassword_Specified;
end;

constructor ImportStringTrackTraceResponse.Create;
begin
  inherited Create;
  FSerializationOptions := [xoLiteralParam];
end;

procedure ImportStringTrackTraceResponse.Setmensagens(Index: Integer; const Astring: string);
begin
  Fmensagens := Astring;
  Fmensagens_Specified := True;
end;

function ImportStringTrackTraceResponse.mensagens_Specified(Index: Integer): boolean;
begin
  Result := Fmensagens_Specified;
end;

constructor ImportBytesTrackTrace.Create;
begin
  inherited Create;
  FSerializationOptions := [xoLiteralParam];
end;

procedure ImportBytesTrackTrace.Setfile_(Index: Integer; const ATByteDynArray: TByteDynArray);
begin
  Ffile_ := ATByteDynArray;
  Ffile__Specified := True;
end;

function ImportBytesTrackTrace.file__Specified(Index: Integer): boolean;
begin
  Result := Ffile__Specified;
end;

procedure ImportBytesTrackTrace.SetCarrierEDI(Index: Integer; const Astring: string);
begin
  FCarrierEDI := Astring;
  FCarrierEDI_Specified := True;
end;

function ImportBytesTrackTrace.CarrierEDI_Specified(Index: Integer): boolean;
begin
  Result := FCarrierEDI_Specified;
end;

procedure ImportBytesTrackTrace.Setpassword(Index: Integer; const Astring: string);
begin
  Fpassword := Astring;
  Fpassword_Specified := True;
end;

function ImportBytesTrackTrace.password_Specified(Index: Integer): boolean;
begin
  Result := Fpassword_Specified;
end;

constructor ImportBytesTrackTraceResponse.Create;
begin
  inherited Create;
  FSerializationOptions := [xoLiteralParam];
end;

procedure ImportBytesTrackTraceResponse.Setmensagens(Index: Integer; const Astring: string);
begin
  Fmensagens := Astring;
  Fmensagens_Specified := True;
end;

function ImportBytesTrackTraceResponse.mensagens_Specified(Index: Integer): boolean;
begin
  Result := Fmensagens_Specified;
end;

constructor ExportStringTrackTrace.Create;
begin
  inherited Create;
  FSerializationOptions := [xoLiteralParam];
end;

procedure ExportStringTrackTrace.SetstrIdTransportadora(Index: Integer; const Astring: string);
begin
  FstrIdTransportadora := Astring;
  FstrIdTransportadora_Specified := True;
end;

function ExportStringTrackTrace.strIdTransportadora_Specified(Index: Integer): boolean;
begin
  Result := FstrIdTransportadora_Specified;
end;

procedure ExportStringTrackTrace.SetstrSenha(Index: Integer; const Astring: string);
begin
  FstrSenha := Astring;
  FstrSenha_Specified := True;
end;

function ExportStringTrackTrace.strSenha_Specified(Index: Integer): boolean;
begin
  Result := FstrSenha_Specified;
end;

constructor ExportStringTrackTraceResponse.Create;
begin
  inherited Create;
  FSerializationOptions := [xoLiteralParam];
end;

procedure ExportStringTrackTraceResponse.SetExportStringTrackTraceResult(Index: Integer; const Astring: string);
begin
  FExportStringTrackTraceResult := Astring;
  FExportStringTrackTraceResult_Specified := True;
end;

function ExportStringTrackTraceResponse.ExportStringTrackTraceResult_Specified(Index: Integer): boolean;
begin
  Result := FExportStringTrackTraceResult_Specified;
end;

initialization
  InvRegistry.RegisterInterface(TypeInfo(WSTrackTraceSoap), 'http://tempuri.org/', 'utf-8');
  InvRegistry.RegisterDefaultSOAPAction(TypeInfo(WSTrackTraceSoap), 'http://tempuri.org/%operationName%');
  InvRegistry.RegisterInvokeOptions(TypeInfo(WSTrackTraceSoap), ioDocument);
  InvRegistry.RegisterInvokeOptions(TypeInfo(WSTrackTraceSoap), ioLiteral);
  InvRegistry.RegisterInvokeOptions(TypeInfo(WSTrackTraceSoap), ioSOAP12);
  InvRegistry.RegisterMethodInfo(TypeInfo(WSTrackTraceSoap), 'ImportDataTableTrackTrace', '', 'ImportDataTableTrackTraceResponse');
  InvRegistry.RegisterParamInfo(TypeInfo(WSTrackTraceSoap), 'ImportDataTableTrackTrace', 'parameters1', 'parameters', '');
  InvRegistry.RegisterMethodInfo(TypeInfo(WSTrackTraceSoap), 'ImportStringTrackTrace', '', 'ImportStringTrackTraceResponse');
  InvRegistry.RegisterParamInfo(TypeInfo(WSTrackTraceSoap), 'ImportStringTrackTrace', 'parameters1', 'parameters', '');
  InvRegistry.RegisterMethodInfo(TypeInfo(WSTrackTraceSoap), 'ImportBytesTrackTrace', '', 'ImportBytesTrackTraceResponse');
  InvRegistry.RegisterParamInfo(TypeInfo(WSTrackTraceSoap), 'ImportBytesTrackTrace', 'parameters1', 'parameters', '');
  InvRegistry.RegisterMethodInfo(TypeInfo(WSTrackTraceSoap), 'ExportStringTrackTrace', '', 'ExportStringTrackTraceResponse');
  InvRegistry.RegisterParamInfo(TypeInfo(WSTrackTraceSoap), 'ExportStringTrackTrace', 'parameters1', 'parameters', '');
  RemClassRegistry.RegisterXSClass(tbl, 'http://tempuri.org/', 'tbl');
  RemClassRegistry.RegisterXSClass(ImportDataTableTrackTrace, 'http://tempuri.org/', 'ImportDataTableTrackTrace');
  RemClassRegistry.RegisterSerializeOptions(ImportDataTableTrackTrace, [xoLiteralParam]);
  RemClassRegistry.RegisterXSClass(ImportDataTableTrackTraceResponse, 'http://tempuri.org/', 'ImportDataTableTrackTraceResponse');
  RemClassRegistry.RegisterSerializeOptions(ImportDataTableTrackTraceResponse, [xoLiteralParam]);
  RemClassRegistry.RegisterXSClass(ImportStringTrackTrace, 'http://tempuri.org/', 'ImportStringTrackTrace');
  RemClassRegistry.RegisterSerializeOptions(ImportStringTrackTrace, [xoLiteralParam]);
  RemClassRegistry.RegisterXSClass(ImportStringTrackTraceResponse, 'http://tempuri.org/', 'ImportStringTrackTraceResponse');
  RemClassRegistry.RegisterSerializeOptions(ImportStringTrackTraceResponse, [xoLiteralParam]);
  RemClassRegistry.RegisterXSClass(ImportBytesTrackTrace, 'http://tempuri.org/', 'ImportBytesTrackTrace');
  RemClassRegistry.RegisterExternalPropName(TypeInfo(ImportBytesTrackTrace), 'file_', 'file');
  RemClassRegistry.RegisterSerializeOptions(ImportBytesTrackTrace, [xoLiteralParam]);
  RemClassRegistry.RegisterXSClass(ImportBytesTrackTraceResponse, 'http://tempuri.org/', 'ImportBytesTrackTraceResponse');
  RemClassRegistry.RegisterSerializeOptions(ImportBytesTrackTraceResponse, [xoLiteralParam]);
  RemClassRegistry.RegisterXSClass(ExportStringTrackTrace, 'http://tempuri.org/', 'ExportStringTrackTrace');
  RemClassRegistry.RegisterSerializeOptions(ExportStringTrackTrace, [xoLiteralParam]);
  RemClassRegistry.RegisterXSClass(ExportStringTrackTraceResponse, 'http://tempuri.org/', 'ExportStringTrackTraceResponse');
  RemClassRegistry.RegisterSerializeOptions(ExportStringTrackTraceResponse, [xoLiteralParam]);

end.
