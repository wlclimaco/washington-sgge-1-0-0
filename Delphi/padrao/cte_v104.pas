unit cte_v104;

interface

uses xmldom, XMLDoc, XMLIntf;

type

{ Forward Decls }

  IXMLTCTe = interface;
  IXMLInfCte = interface;
  IXMLIde = interface;
  IXMLToma03 = interface;
  IXMLToma4 = interface;
  IXMLTEndereco = interface;
  IXMLCompl = interface;
  IXMLFluxo = interface;
  IXMLPass = interface;
  IXMLPassList = interface;
  IXMLEntrega = interface;
  IXMLSemData = interface;
  IXMLComData = interface;
  IXMLNoPeriodo = interface;
  IXMLSemHora = interface;
  IXMLComHora = interface;
  IXMLNoInter = interface;
  IXMLObsCont = interface;
  IXMLObsFisco = interface;
  IXMLEmit = interface;
  IXMLTEndeEmi = interface;
  IXMLRem = interface;
  IXMLInfNF = interface;
  IXMLInfNFList = interface;
  IXMLTEndReEnt = interface;
  IXMLInfNFe = interface;
  IXMLInfNFeList = interface;
  IXMLInfOutros = interface;
  IXMLInfOutrosList = interface;
  IXMLExped = interface;
  IXMLReceb = interface;
  IXMLDest = interface;
  IXMLVPrest = interface;
  IXMLComp = interface;
  IXMLCompList = interface;
  IXMLImp = interface;
  IXMLTCST = interface;

  IXMLCST00 = interface; //1.03
  IXMLCST20 = interface; //1.03
  IXMLCST45 = interface; //1.03
  IXMLCST80 = interface; //1.03
  IXMLCST81 = interface; //1.03
  IXMLCST90 = interface; //1.03

  IXMLICMS00 = interface; //1.04
  IXMLICMS20 = interface; //1.04
  IXMLICMS45 = interface; //1.04
  IXMLICMS60 = interface; //1.04
  IXMLICMS90 = interface; //1.04
  IXMLICMSOutraUF = interface; //1.04
  IXMLICMSSN = interface; //1.04

  IXMLInfCTeNorm = interface;
  IXMLInfCarga = interface;
  IXMLInfQ = interface;
  IXMLInfQList = interface;
  IXMLContQt = interface;
  IXMLContQtList = interface;
  IXMLLacContQt = interface;
  IXMLLacContQtList = interface;
  IXMLDocAnt = interface;
  IXMLEmiDocAnt = interface;
  IXMLIdDocAnt = interface;
  IXMLIdDocAntList = interface;
  IXMLIdDocAntPap = interface;
  IXMLIdDocAntPapList = interface;
  IXMLIdDocAntEle = interface;
  IXMLIdDocAntEleList = interface;
  IXMLSeg = interface;
  IXMLSegList = interface;
  IXMLinfModal = interface;
  IXMLRodo = interface;
  IXMLCTRB = interface;
  IXMLOcc = interface;
  IXMLEmiOcc = interface;
  IXMLValePed = interface;
  IXMLDisp = interface;
  IXMLDispList = interface;
  IXMLVeic = interface;
  IXMLVeicList = interface;
  IXMLProp = interface;
  IXMLLacRodo = interface;
  IXMLLacRodoList = interface;
  IXMLMoto = interface;
  IXMLMotoList = interface;
  IXMLAereo = interface;
  IXMLTarifa = interface;
  IXMLAquav = interface;
  IXMLLacre = interface;
  IXMLLacreList = interface;
  IXMLFerrov = interface;
  IXMLFerroSub = interface;
  IXMLTEnderFer = interface;
  IXMLDCL = interface;
  IXMLDCLList = interface;
  IXMLDetVagDCL = interface;
  IXMLDetVagDCLList = interface;
  IXMLLacDetVagDCL = interface;
  IXMLLacDetVagDCLList = interface;
  IXMLContDCL = interface;
  IXMLContDCLList = interface;
  IXMLDetVag = interface;
  IXMLDetVagList = interface;
  IXMLLacDetVag = interface;
  IXMLLacDetVagList = interface;
  IXMLContVag = interface;
  IXMLContVagList = interface;
  IXMLDuto = interface;
  IXMLPeri = interface;
  IXMLPeriList = interface;
  IXMLVeicNovos = interface;
  IXMLVeicNovosList = interface;
  IXMLInfCteSub = interface;
  IXMLTomaICMS = interface;
  IXMLRefNF = interface;
  IXMLTomaNaoICMS = interface;
  IXMLInfCteComp = interface;
  IXMLVPresComp = interface;
  IXMLCompComp = interface;
  IXMLCompCompList = interface;
  IXMLImpComp = interface;
  IXMLInfCteAnu = interface;
  IXMLSignatureType = interface;
  IXMLSignedInfoType = interface;
  IXMLCanonicalizationMethod = interface;
  IXMLSignatureMethod = interface;
  IXMLReferenceType = interface;
  IXMLTransformsType = interface;
  IXMLTransformType = interface;
  IXMLDigestMethod = interface;
  IXMLSignatureValueType = interface;
  IXMLKeyInfoType = interface;
  IXMLX509DataType = interface;

{ IXMLTCTe }

  IXMLTCTe = interface(IXMLNode)
    ['{8A8214C0-4968-4B52-807B-7BF69F4DEF53}']
    { Property Accessors }
    function Get_InfCte: IXMLInfCte;
    function Get_Signature: IXMLSignatureType;
    { Methods & Properties }
    property InfCte: IXMLInfCte read Get_InfCte;
    property Signature: IXMLSignatureType read Get_Signature;
  end;

{ IXMLInfCte }

  IXMLInfCte = interface(IXMLNode)
    ['{93C1275D-1D64-4D70-9761-9E040C9FC7D2}']
    { Property Accessors }
    function Get_Versao: WideString;
    function Get_Id: WideString;
    function Get_Ide: IXMLIde;
    function Get_Compl: IXMLCompl;
    function Get_Emit: IXMLEmit;
    function Get_Rem: IXMLRem;
    function Get_Exped: IXMLExped;
    function Get_Receb: IXMLReceb;
    function Get_Dest: IXMLDest;
    function Get_VPrest: IXMLVPrest;
    function Get_Imp: IXMLImp;
    function Get_InfCTeNorm: IXMLInfCTeNorm;
    function Get_InfCteComp: IXMLInfCteComp;
    function Get_InfCteAnu: IXMLInfCteAnu;
    procedure Set_Versao(Value: WideString);
    procedure Set_Id(Value: WideString);
    { Methods & Properties }
    property Versao: WideString read Get_Versao write Set_Versao;
    property Id: WideString read Get_Id write Set_Id;
    property Ide: IXMLIde read Get_Ide;
    property Compl: IXMLCompl read Get_Compl;
    property Emit: IXMLEmit read Get_Emit;
    property Rem: IXMLRem read Get_Rem;
    property Exped: IXMLExped read Get_Exped;
    property Receb: IXMLReceb read Get_Receb;
    property Dest: IXMLDest read Get_Dest;
    property VPrest: IXMLVPrest read Get_VPrest;
    property Imp: IXMLImp read Get_Imp;
    property InfCTeNorm: IXMLInfCTeNorm read Get_InfCTeNorm;
    property InfCteComp: IXMLInfCteComp read Get_InfCteComp;
    property InfCteAnu: IXMLInfCteAnu read Get_InfCteAnu;
  end;

{ IXMLIde }

  IXMLIde = interface(IXMLNode)
    ['{FA8EBF23-3D8F-47F9-9584-AB92F255B60D}']
    { Property Accessors }
    function Get_CUF: WideString;
    function Get_CCT: WideString;
    function Get_CFOP: WideString;
    function Get_NatOp: WideString;
    function Get_ForPag: WideString;
    function Get_Mod_: WideString;
    function Get_Serie: WideString;
    function Get_NCT: WideString;
    function Get_DhEmi: WideString;
    function Get_TpImp: WideString;
    function Get_TpEmis: WideString;
    function Get_CDV: WideString;
    function Get_TpAmb: WideString;
    function Get_TpCTe: WideString;
    function Get_ProcEmi: WideString;
    function Get_VerProc: WideString;
    function Get_RefCTE: WideString;

    function Get_cMunEmi: WideString; //1.03
    function Get_xMunEmi: WideString; //1.03
    function Get_UFEmi: WideString;   //1.03

    function Get_cMunEnv: WideString; //1.04
    function Get_xMunEnv: WideString; //1.04
    function Get_UFEnv: WideString;   //1.04

    function Get_Modal: WideString;
    function Get_TpServ: WideString;
    function Get_CMunIni: WideString;
    function Get_XMunIni: WideString;
    function Get_UFIni: WideString;
    function Get_CMunFim: WideString;
    function Get_XMunFim: WideString;
    function Get_UFFim: WideString;
    function Get_Retira: WideString;
    function Get_XDetRetira: WideString;
    function Get_Toma03: IXMLToma03;
    function Get_Toma4: IXMLToma4;
    function Get_dhCont: WideString;//1.04
    function Get_xJust: WideString; //1.04

    procedure Set_CUF(Value: WideString);
    procedure Set_CCT(Value: WideString);
    procedure Set_CFOP(Value: WideString);
    procedure Set_NatOp(Value: WideString);
    procedure Set_ForPag(Value: WideString);
    procedure Set_Mod_(Value: WideString);
    procedure Set_Serie(Value: WideString);
    procedure Set_NCT(Value: WideString);
    procedure Set_DhEmi(Value: WideString);
    procedure Set_TpImp(Value: WideString);
    procedure Set_TpEmis(Value: WideString);
    procedure Set_CDV(Value: WideString);
    procedure Set_TpAmb(Value: WideString);
    procedure Set_TpCTe(Value: WideString);
    procedure Set_ProcEmi(Value: WideString);
    procedure Set_VerProc(Value: WideString);
    procedure Set_RefCTE(Value: WideString);

    procedure Set_cMunEmi(Value: WideString); //1.03
    procedure Set_xMunEmi(Value: WideString); //1.03
    procedure Set_UFEmi(Value: WideString);   //1.03

    procedure Set_cMunEnv(Value: WideString); //1.04
    procedure Set_xMunEnv(Value: WideString); //1.04
    procedure Set_UFEnv(Value: WideString);   //1.04

    procedure Set_Modal(Value: WideString);
    procedure Set_TpServ(Value: WideString);
    procedure Set_CMunIni(Value: WideString);
    procedure Set_XMunIni(Value: WideString);
    procedure Set_UFIni(Value: WideString);
    procedure Set_CMunFim(Value: WideString);
    procedure Set_XMunFim(Value: WideString);
    procedure Set_UFFim(Value: WideString);
    procedure Set_Retira(Value: WideString);
    procedure Set_XDetRetira(Value: WideString);
    procedure Set_dhCont(Value: WideString);//1.04
    procedure Set_xJust(Value: WideString); //1.04

    { Methods & Properties }
    property CUF: WideString read Get_CUF write Set_CUF;
    property CCT: WideString read Get_CCT write Set_CCT;
    property CFOP: WideString read Get_CFOP write Set_CFOP;
    property NatOp: WideString read Get_NatOp write Set_NatOp;
    property ForPag: WideString read Get_ForPag write Set_ForPag;
    property Mod_: WideString read Get_Mod_ write Set_Mod_;
    property Serie: WideString read Get_Serie write Set_Serie;
    property NCT: WideString read Get_NCT write Set_NCT;
    property DhEmi: WideString read Get_DhEmi write Set_DhEmi;
    property TpImp: WideString read Get_TpImp write Set_TpImp;
    property TpEmis: WideString read Get_TpEmis write Set_TpEmis;
    property CDV: WideString read Get_CDV write Set_CDV;
    property TpAmb: WideString read Get_TpAmb write Set_TpAmb;
    property TpCTe: WideString read Get_TpCTe write Set_TpCTe;
    property ProcEmi: WideString read Get_ProcEmi write Set_ProcEmi;
    property VerProc: WideString read Get_VerProc write Set_VerProc;
    property RefCTE: WideString read Get_RefCTE write Set_RefCTE;

    property cMunEmi: WideString read Get_CMunEmi write Set_CMunEmi; //1.03
    property xMunEmi: WideString read Get_XMunEmi write Set_XMunEmi; //1.03
    property UFEmi: WideString read Get_UFEmi write Set_UFEmi;       //1.03

    property cMunEnv: WideString read Get_cMunEnv write Set_cMunEnv; //1.04
    property xMunEnv: WideString read Get_xMunEnv write Set_xMunEnv; //1.04
    property UFEnv: WideString read Get_UFEnv write Set_UFEnv;       //1.04

    property Modal: WideString read Get_Modal write Set_Modal;
    property TpServ: WideString read Get_TpServ write Set_TpServ;
    property CMunIni: WideString read Get_CMunIni write Set_CMunIni;
    property XMunIni: WideString read Get_XMunIni write Set_XMunIni;
    property UFIni: WideString read Get_UFIni write Set_UFIni;
    property CMunFim: WideString read Get_CMunFim write Set_CMunFim;
    property XMunFim: WideString read Get_XMunFim write Set_XMunFim;
    property UFFim: WideString read Get_UFFim write Set_UFFim;
    property Retira: WideString read Get_Retira write Set_Retira;
    property XDetRetira: WideString read Get_XDetRetira write Set_XDetRetira;
    property Toma03: IXMLToma03 read Get_Toma03;
    property Toma4: IXMLToma4 read Get_Toma4;
    property DhCont: WideString read Get_DhCont write Set_dhCont; //1.04
    property xJust : WideString read Get_xJust  write Set_xJust;  //1.04
  end;

{ IXMLToma03 }

  IXMLToma03 = interface(IXMLNode)
    ['{6EC4AA72-8166-4CBA-8344-7E3AB452C491}']
    { Property Accessors }
    function Get_Toma: WideString;
    procedure Set_Toma(Value: WideString);
    { Methods & Properties }
    property Toma: WideString read Get_Toma write Set_Toma;
  end;

{ IXMLToma4 }

  IXMLToma4 = interface(IXMLNode)
    ['{6B25F25E-BA44-4BB6-B0DA-2CFA502CC9F6}']
    { Property Accessors }
    function Get_Toma: WideString;
    function Get_CNPJ: WideString;
    function Get_CPF: WideString;
    function Get_IE: WideString;
    function Get_XNome: WideString;
    function Get_XFant: WideString;
    function Get_Fone: WideString;
    function Get_EnderToma: IXMLTEndereco;
    procedure Set_Toma(Value: WideString);
    procedure Set_CNPJ(Value: WideString);
    procedure Set_CPF(Value: WideString);
    procedure Set_IE(Value: WideString);
    procedure Set_XNome(Value: WideString);
    procedure Set_XFant(Value: WideString);
    procedure Set_Fone(Value: WideString);
    { Methods & Properties }
    property Toma: WideString read Get_Toma write Set_Toma;
    property CNPJ: WideString read Get_CNPJ write Set_CNPJ;
    property CPF: WideString read Get_CPF write Set_CPF;
    property IE: WideString read Get_IE write Set_IE;
    property XNome: WideString read Get_XNome write Set_XNome;
    property XFant: WideString read Get_XFant write Set_XFant;
    property Fone: WideString read Get_Fone write Set_Fone;
    property EnderToma: IXMLTEndereco read Get_EnderToma;
  end;

{ IXMLTEndereco }

  IXMLTEndereco = interface(IXMLNode)
    ['{CADDB302-3A6F-4E8D-88CD-C4EFF79EFBE8}']
    { Property Accessors }
    function Get_XLgr: WideString;
    function Get_Nro: WideString;
    function Get_XCpl: WideString;
    function Get_XBairro: WideString;
    function Get_CMun: WideString;
    function Get_XMun: WideString;
    function Get_CEP: WideString;
    function Get_UF: WideString;
    function Get_CPais: WideString;
    function Get_XPais: WideString;
    procedure Set_XLgr(Value: WideString);
    procedure Set_Nro(Value: WideString);
    procedure Set_XCpl(Value: WideString);
    procedure Set_XBairro(Value: WideString);
    procedure Set_CMun(Value: WideString);
    procedure Set_XMun(Value: WideString);
    procedure Set_CEP(Value: WideString);
    procedure Set_UF(Value: WideString);
    procedure Set_CPais(Value: WideString);
    procedure Set_XPais(Value: WideString);
    { Methods & Properties }
    property XLgr: WideString read Get_XLgr write Set_XLgr;
    property Nro: WideString read Get_Nro write Set_Nro;
    property XCpl: WideString read Get_XCpl write Set_XCpl;
    property XBairro: WideString read Get_XBairro write Set_XBairro;
    property CMun: WideString read Get_CMun write Set_CMun;
    property XMun: WideString read Get_XMun write Set_XMun;
    property CEP: WideString read Get_CEP write Set_CEP;
    property UF: WideString read Get_UF write Set_UF;
    property CPais: WideString read Get_CPais write Set_CPais;
    property XPais: WideString read Get_XPais write Set_XPais;
  end;

{ IXMLCompl }

  IXMLCompl = interface(IXMLNode)
    ['{2221E5B9-0AC2-4917-978E-8BDBB802CDF6}']
    { Property Accessors }
    function Get_XCaracAd: WideString;
    function Get_XCaracSer: WideString;
    function Get_XEmi: WideString;
    function Get_Fluxo: IXMLFluxo;
    function Get_Entrega: IXMLEntrega;
    function Get_OrigCalc: WideString;
    function Get_DestCalc: WideString;
    function Get_XObs: WideString;
    function Get_ObsCont: IXMLObsCont;
    function Get_ObsFisco: IXMLObsFisco;
    procedure Set_XCaracAd(Value: WideString);
    procedure Set_XCaracSer(Value: WideString);
    procedure Set_XEmi(Value: WideString);
    procedure Set_OrigCalc(Value: WideString);
    procedure Set_DestCalc(Value: WideString);
    procedure Set_XObs(Value: WideString);
    { Methods & Properties }
    property XCaracAd: WideString read Get_XCaracAd write Set_XCaracAd;
    property XCaracSer: WideString read Get_XCaracSer write Set_XCaracSer;
    property XEmi: WideString read Get_XEmi write Set_XEmi;
    property Fluxo: IXMLFluxo read Get_Fluxo;
    property Entrega: IXMLEntrega read Get_Entrega;
    property OrigCalc: WideString read Get_OrigCalc write Set_OrigCalc;
    property DestCalc: WideString read Get_DestCalc write Set_DestCalc;
    property XObs: WideString read Get_XObs write Set_XObs;
    property ObsCont: IXMLObsCont read Get_ObsCont;
    property ObsFisco: IXMLObsFisco read Get_ObsFisco;
  end;

{ IXMLFluxo }

  IXMLFluxo = interface(IXMLNode)
    ['{D199DE05-1326-4D7C-830F-9C091E18603B}']
    { Property Accessors }
    function Get_XOrig: WideString;
    function Get_Pass: IXMLPassList;
    function Get_XDest: WideString;
    function Get_XRota: WideString;
    procedure Set_XOrig(Value: WideString);
    procedure Set_XDest(Value: WideString);
    procedure Set_XRota(Value: WideString);
    { Methods & Properties }
    property XOrig: WideString read Get_XOrig write Set_XOrig;
    property Pass: IXMLPassList read Get_Pass;
    property XDest: WideString read Get_XDest write Set_XDest;
    property XRota: WideString read Get_XRota write Set_XRota;
  end;

{ IXMLPass }

  IXMLPass = interface(IXMLNode)
    ['{FD5DEEB9-A959-4DAE-840C-2BF96231CA07}']
    { Property Accessors }
    function Get_XPass: WideString;
    procedure Set_XPass(Value: WideString);
    { Methods & Properties }
    property XPass: WideString read Get_XPass write Set_XPass;
  end;

{ IXMLPassList }

  IXMLPassList = interface(IXMLNodeCollection)
    ['{191D247C-2DEB-42DA-AA30-833E9ECA2C26}']
    { Methods & Properties }
    function Add: IXMLPass;
    function Insert(const Index: Integer): IXMLPass;
    function Get_Item(Index: Integer): IXMLPass;
    property Items[Index: Integer]: IXMLPass read Get_Item; default;
  end;

{ IXMLEntrega }

  IXMLEntrega = interface(IXMLNode)
    ['{EBEB9401-87E6-4B4D-964C-9782FF021EC9}']
    { Property Accessors }
    function Get_SemData: IXMLSemData;
    function Get_ComData: IXMLComData;
    function Get_NoPeriodo: IXMLNoPeriodo;
    function Get_SemHora: IXMLSemHora;
    function Get_ComHora: IXMLComHora;
    function Get_NoInter: IXMLNoInter;
    { Methods & Properties }
    property SemData: IXMLSemData read Get_SemData;
    property ComData: IXMLComData read Get_ComData;
    property NoPeriodo: IXMLNoPeriodo read Get_NoPeriodo;
    property SemHora: IXMLSemHora read Get_SemHora;
    property ComHora: IXMLComHora read Get_ComHora;
    property NoInter: IXMLNoInter read Get_NoInter;
  end;

{ IXMLSemData }

  IXMLSemData = interface(IXMLNode)
    ['{C441EE4F-C136-4AC3-8D17-D92F68D88617}']
    { Property Accessors }
    function Get_TpPer: WideString;
    procedure Set_TpPer(Value: WideString);
    { Methods & Properties }
    property TpPer: WideString read Get_TpPer write Set_TpPer;
  end;

{ IXMLComData }

  IXMLComData = interface(IXMLNode)
    ['{4DE9B85C-8D29-43CD-B51F-B9E7789037CA}']
    { Property Accessors }
    function Get_TpPer: WideString;
    function Get_DProg: WideString;
    procedure Set_TpPer(Value: WideString);
    procedure Set_DProg(Value: WideString);
    { Methods & Properties }
    property TpPer: WideString read Get_TpPer write Set_TpPer;
    property DProg: WideString read Get_DProg write Set_DProg;
  end;

{ IXMLNoPeriodo }

  IXMLNoPeriodo = interface(IXMLNode)
    ['{65939681-0CFA-4BDC-A8C1-F53B4A0C3546}']
    { Property Accessors }
    function Get_TpPer: WideString;
    function Get_DIni: WideString;
    function Get_DFim: WideString;
    procedure Set_TpPer(Value: WideString);
    procedure Set_DIni(Value: WideString);
    procedure Set_DFim(Value: WideString);
    { Methods & Properties }
    property TpPer: WideString read Get_TpPer write Set_TpPer;
    property DIni: WideString read Get_DIni write Set_DIni;
    property DFim: WideString read Get_DFim write Set_DFim;
  end;

{ IXMLSemHora }

  IXMLSemHora = interface(IXMLNode)
    ['{09059A5E-A69D-44AF-A850-CAB21A3C1AA1}']
    { Property Accessors }
    function Get_TpHor: WideString;
    procedure Set_TpHor(Value: WideString);
    { Methods & Properties }
    property TpHor: WideString read Get_TpHor write Set_TpHor;
  end;

{ IXMLComHora }

  IXMLComHora = interface(IXMLNode)
    ['{D031AE5F-A144-4F27-8D28-DD778CBB0077}']
    { Property Accessors }
    function Get_TpHor: WideString;
    function Get_HProg: WideString;
    procedure Set_TpHor(Value: WideString);
    procedure Set_HProg(Value: WideString);
    { Methods & Properties }
    property TpHor: WideString read Get_TpHor write Set_TpHor;
    property HProg: WideString read Get_HProg write Set_HProg;
  end;

{ IXMLNoInter }

  IXMLNoInter = interface(IXMLNode)
    ['{5695589F-4589-4A2E-B0C6-7E70820AD8F9}']
    { Property Accessors }
    function Get_Tphor: WideString;
    function Get_HIni: WideString;
    function Get_HFim: WideString;
    procedure Set_Tphor(Value: WideString);
    procedure Set_HIni(Value: WideString);
    procedure Set_HFim(Value: WideString);
    { Methods & Properties }
    property Tphor: WideString read Get_Tphor write Set_Tphor;
    property HIni: WideString read Get_HIni write Set_HIni;
    property HFim: WideString read Get_HFim write Set_HFim;
  end;

{ IXMLObsCont }

  IXMLObsCont = interface(IXMLNode)
    ['{D31817E1-2F0C-4177-94B4-7FB2349584D1}']
    { Property Accessors }
    function Get_XCampo: WideString;
    function Get_XTexto: WideString;
    procedure Set_XCampo(Value: WideString);
    procedure Set_XTexto(Value: WideString);
    { Methods & Properties }
    property XCampo: WideString read Get_XCampo write Set_XCampo;
    property XTexto: WideString read Get_XTexto write Set_XTexto;
  end;

{ IXMLObsFisco }

  IXMLObsFisco = interface(IXMLNode)
    ['{92E58BD9-35C1-4DE3-90B0-B13EB7921BFF}']
    { Property Accessors }
    function Get_XCampo: WideString;
    function Get_XTexto: WideString;
    procedure Set_XCampo(Value: WideString);
    procedure Set_XTexto(Value: WideString);
    { Methods & Properties }
    property XCampo: WideString read Get_XCampo write Set_XCampo;
    property XTexto: WideString read Get_XTexto write Set_XTexto;
  end;

{ IXMLEmit }

  IXMLEmit = interface(IXMLNode)
    ['{4B7AED4F-BAFA-4984-B673-4DA708BFABF5}']
    { Property Accessors }
    function Get_CNPJ: WideString;
    function Get_IE: WideString;
    function Get_XNome: WideString;
    function Get_XFant: WideString;
    function Get_EnderEmit: IXMLTEndeEmi;
    procedure Set_CNPJ(Value: WideString);
    procedure Set_IE(Value: WideString);
    procedure Set_XNome(Value: WideString);
    procedure Set_XFant(Value: WideString);
    { Methods & Properties }
    property CNPJ: WideString read Get_CNPJ write Set_CNPJ;
    property IE: WideString read Get_IE write Set_IE;
    property XNome: WideString read Get_XNome write Set_XNome;
    property XFant: WideString read Get_XFant write Set_XFant;
    property EnderEmit: IXMLTEndeEmi read Get_EnderEmit;
  end;

{ IXMLTEndeEmi }

  IXMLTEndeEmi = interface(IXMLNode)
    ['{CDB19B81-D564-49E9-9A05-001EECC66718}']
    { Property Accessors }
    function Get_XLgr: WideString;
    function Get_Nro: WideString;
    function Get_XCpl: WideString;
    function Get_XBairro: WideString;
    function Get_CMun: WideString;
    function Get_XMun: WideString;
    function Get_CEP: WideString;
    function Get_UF: WideString;
    function Get_CPais: WideString;
    function Get_XPais: WideString;
    function Get_Fone: WideString;
    procedure Set_XLgr(Value: WideString);
    procedure Set_Nro(Value: WideString);
    procedure Set_XCpl(Value: WideString);
    procedure Set_XBairro(Value: WideString);
    procedure Set_CMun(Value: WideString);
    procedure Set_XMun(Value: WideString);
    procedure Set_CEP(Value: WideString);
    procedure Set_UF(Value: WideString);
    procedure Set_CPais(Value: WideString);
    procedure Set_XPais(Value: WideString);
    procedure Set_Fone(Value: WideString);
    { Methods & Properties }
    property XLgr: WideString read Get_XLgr write Set_XLgr;
    property Nro: WideString read Get_Nro write Set_Nro;
    property XCpl: WideString read Get_XCpl write Set_XCpl;
    property XBairro: WideString read Get_XBairro write Set_XBairro;
    property CMun: WideString read Get_CMun write Set_CMun;
    property XMun: WideString read Get_XMun write Set_XMun;
    property CEP: WideString read Get_CEP write Set_CEP;
    property UF: WideString read Get_UF write Set_UF;
    property CPais: WideString read Get_CPais write Set_CPais;
    property XPais: WideString read Get_XPais write Set_XPais;
    property Fone: WideString read Get_Fone write Set_Fone;
  end;

{ IXMLRem }

  IXMLRem = interface(IXMLNode)
    ['{ED5F079B-C2FE-4FE0-9716-0D6C742B3059}']
    { Property Accessors }
    function Get_CNPJ: WideString;
    function Get_CPF: WideString;
    function Get_IE: WideString;
    function Get_XNome: WideString;
    function Get_XFant: WideString;
    function Get_Fone: WideString;
    function Get_EnderReme: IXMLTEndereco;
    function Get_InfNF: IXMLInfNFList;
    function Get_InfNFe: IXMLInfNFeList;
    function Get_InfOutros: IXMLInfOutrosList;
    procedure Set_CNPJ(Value: WideString);
    procedure Set_CPF(Value: WideString);
    procedure Set_IE(Value: WideString);
    procedure Set_XNome(Value: WideString);
    procedure Set_XFant(Value: WideString);
    procedure Set_Fone(Value: WideString);
    { Methods & Properties }
    property CNPJ: WideString read Get_CNPJ write Set_CNPJ;
    property CPF: WideString read Get_CPF write Set_CPF;
    property IE: WideString read Get_IE write Set_IE;
    property XNome: WideString read Get_XNome write Set_XNome;
    property XFant: WideString read Get_XFant write Set_XFant;
    property Fone: WideString read Get_Fone write Set_Fone;
    property EnderReme: IXMLTEndereco read Get_EnderReme;
    property InfNF: IXMLInfNFList read Get_InfNF;
    property InfNFe: IXMLInfNFeList read Get_InfNFe;
    property InfOutros: IXMLInfOutrosList read Get_InfOutros;
  end;

{ IXMLInfNF }

  IXMLInfNF = interface(IXMLNode)
    ['{B1A8719B-AA8C-40A5-932A-6DB61F9A0131}']
    { Property Accessors }
    function Get_NRoma: WideString;
    function Get_NPed: WideString;
    function Get_Serie: WideString;
    function Get_NDoc: WideString;
    function Get_DEmi: WideString;
    function Get_VBC: WideString;
    function Get_vICMS: WideString;
    function Get_vBCST: WideString;
    function Get_VST: WideString;
    function Get_VProd: WideString;
    function Get_VNF: WideString;
    function Get_NCFOP: WideString;
    function Get_NPeso: WideString;
    function Get_PIN: WideString;
    function Get_LocRet: IXMLTEndReEnt;
    procedure Set_NRoma(Value: WideString);
    procedure Set_NPed(Value: WideString);
    procedure Set_Serie(Value: WideString);
    procedure Set_NDoc(Value: WideString);
    procedure Set_DEmi(Value: WideString);
    procedure Set_vBC(Value: WideString);
    procedure Set_vICMS(Value: WideString);
    procedure Set_vBCST(Value: WideString);
    procedure Set_VST(Value: WideString);
    procedure Set_VProd(Value: WideString);
    procedure Set_VNF(Value: WideString);
    procedure Set_NCFOP(Value: WideString);
    procedure Set_NPeso(Value: WideString);
    procedure Set_PIN(Value: WideString);
    { Methods & Properties }
    property NRoma: WideString read Get_NRoma write Set_NRoma;
    property NPed: WideString read Get_NPed write Set_NPed;
    property Serie: WideString read Get_Serie write Set_Serie;
    property NDoc: WideString read Get_NDoc write Set_NDoc;
    property DEmi: WideString read Get_DEmi write Set_DEmi;
    property vBC: WideString read Get_vBC write Set_vBC;
    property vICMS: WideString read Get_vICMS write Set_vICMS;
    property vBCST: WideString read Get_vBCST write Set_vBCST;
    property VST: WideString read Get_VST write Set_VST;
    property VProd: WideString read Get_VProd write Set_VProd;
    property VNF: WideString read Get_VNF write Set_VNF;
    property NCFOP: WideString read Get_NCFOP write Set_NCFOP;
    property NPeso: WideString read Get_NPeso write Set_NPeso;
    property PIN: WideString read Get_PIN write Set_PIN;
    property LocRet: IXMLTEndReEnt read Get_LocRet;
  end;

{ IXMLInfNFList }

  IXMLInfNFList = interface(IXMLNodeCollection)
    ['{75CFB596-5F0E-427D-8F4F-CCD7A595F109}']
    { Methods & Properties }
    function Add: IXMLInfNF;
    function Insert(const Index: Integer): IXMLInfNF;
    function Get_Item(Index: Integer): IXMLInfNF;
    property Items[Index: Integer]: IXMLInfNF read Get_Item; default;
  end;

{ IXMLTEndReEnt }

  IXMLTEndReEnt = interface(IXMLNode)
    ['{F29741E2-E67D-4A9B-88EC-F8B5391C898F}']
    { Property Accessors }
    function Get_CNPJ: WideString;
    function Get_CPF: WideString;
    function Get_XNome: WideString;
    function Get_XLgr: WideString;
    function Get_Nro: WideString;
    function Get_XCpl: WideString;
    function Get_XBairro: WideString;
    function Get_CMun: WideString;
    function Get_XMun: WideString;
    function Get_UF: WideString;
    procedure Set_CNPJ(Value: WideString);
    procedure Set_CPF(Value: WideString);
    procedure Set_XNome(Value: WideString);
    procedure Set_XLgr(Value: WideString);
    procedure Set_Nro(Value: WideString);
    procedure Set_XCpl(Value: WideString);
    procedure Set_XBairro(Value: WideString);
    procedure Set_CMun(Value: WideString);
    procedure Set_XMun(Value: WideString);
    procedure Set_UF(Value: WideString);
    { Methods & Properties }
    property CNPJ: WideString read Get_CNPJ write Set_CNPJ;
    property CPF: WideString read Get_CPF write Set_CPF;
    property XNome: WideString read Get_XNome write Set_XNome;
    property XLgr: WideString read Get_XLgr write Set_XLgr;
    property Nro: WideString read Get_Nro write Set_Nro;
    property XCpl: WideString read Get_XCpl write Set_XCpl;
    property XBairro: WideString read Get_XBairro write Set_XBairro;
    property CMun: WideString read Get_CMun write Set_CMun;
    property XMun: WideString read Get_XMun write Set_XMun;
    property UF: WideString read Get_UF write Set_UF;
  end;

{ IXMLInfNFe }

  IXMLInfNFe = interface(IXMLNode)
    ['{856C035A-0291-4475-A722-3166E4365654}']
    { Property Accessors }
    function Get_Chave: WideString;
    function Get_PIN: WideString;
    procedure Set_Chave(Value: WideString);
    procedure Set_PIN(Value: WideString);
    { Methods & Properties }
    property Chave: WideString read Get_Chave write Set_Chave;
    property PIN: WideString read Get_PIN write Set_PIN;
  end;

{ IXMLInfNFeList }

  IXMLInfNFeList = interface(IXMLNodeCollection)
    ['{4AB7327F-F14B-4B3A-BD3E-4183569C0C09}']
    { Methods & Properties }
    function Add: IXMLInfNFe;
    function Insert(const Index: Integer): IXMLInfNFe;
    function Get_Item(Index: Integer): IXMLInfNFe;
    property Items[Index: Integer]: IXMLInfNFe read Get_Item; default;
  end;

{ IXMLInfOutros }

  IXMLInfOutros = interface(IXMLNode)
    ['{64CF8357-6CBC-4EFA-878B-A67B32D898E2}']
    { Property Accessors }
    function Get_TpDoc: WideString;
    function Get_DescOutros: WideString;
    function Get_NDoc: WideString;
    function Get_DEmi: WideString;
    function Get_VDocFisc: WideString;
    procedure Set_TpDoc(Value: WideString);
    procedure Set_DescOutros(Value: WideString);
    procedure Set_NDoc(Value: WideString);
    procedure Set_DEmi(Value: WideString);
    procedure Set_VDocFisc(Value: WideString);
    { Methods & Properties }
    property TpDoc: WideString read Get_TpDoc write Set_TpDoc;
    property DescOutros: WideString read Get_DescOutros write Set_DescOutros;
    property NDoc: WideString read Get_NDoc write Set_NDoc;
    property DEmi: WideString read Get_DEmi write Set_DEmi;
    property VDocFisc: WideString read Get_VDocFisc write Set_VDocFisc;
  end;

{ IXMLInfOutrosList }

  IXMLInfOutrosList = interface(IXMLNodeCollection)
    ['{AB9FF434-2500-4690-9591-31B743FF067E}']
    { Methods & Properties }
    function Add: IXMLInfOutros;
    function Insert(const Index: Integer): IXMLInfOutros;
    function Get_Item(Index: Integer): IXMLInfOutros;
    property Items[Index: Integer]: IXMLInfOutros read Get_Item; default;
  end;

{ IXMLExped }

  IXMLExped = interface(IXMLNode)
    ['{E599EEAE-E500-4F32-9CF2-336E07698AF9}']
    { Property Accessors }
    function Get_CNPJ: WideString;
    function Get_CPF: WideString;
    function Get_IE: WideString;
    function Get_XNome: WideString;
    function Get_Fone: WideString;
    function Get_EnderExped: IXMLTEndereco;
    procedure Set_CNPJ(Value: WideString);
    procedure Set_CPF(Value: WideString);
    procedure Set_IE(Value: WideString);
    procedure Set_XNome(Value: WideString);
    procedure Set_Fone(Value: WideString);
    { Methods & Properties }
    property CNPJ: WideString read Get_CNPJ write Set_CNPJ;
    property CPF: WideString read Get_CPF write Set_CPF;
    property IE: WideString read Get_IE write Set_IE;
    property XNome: WideString read Get_XNome write Set_XNome;
    property Fone: WideString read Get_Fone write Set_Fone;
    property EnderExped: IXMLTEndereco read Get_EnderExped;
  end;

{ IXMLReceb }

  IXMLReceb = interface(IXMLNode)
    ['{22778FFA-52C4-4129-AE3C-77779672A7A8}']
    { Property Accessors }
    function Get_CNPJ: WideString;
    function Get_CPF: WideString;
    function Get_IE: WideString;
    function Get_XNome: WideString;
    function Get_Fone: WideString;
    function Get_EnderReceb: IXMLTEndereco;
    procedure Set_CNPJ(Value: WideString);
    procedure Set_CPF(Value: WideString);
    procedure Set_IE(Value: WideString);
    procedure Set_XNome(Value: WideString);
    procedure Set_Fone(Value: WideString);
    { Methods & Properties }
    property CNPJ: WideString read Get_CNPJ write Set_CNPJ;
    property CPF: WideString read Get_CPF write Set_CPF;
    property IE: WideString read Get_IE write Set_IE;
    property XNome: WideString read Get_XNome write Set_XNome;
    property Fone: WideString read Get_Fone write Set_Fone;
    property EnderReceb: IXMLTEndereco read Get_EnderReceb;
  end;

{ IXMLDest }

  IXMLDest = interface(IXMLNode)
    ['{F181D000-2338-4988-B815-0921C917CD3A}']
    { Property Accessors }
    function Get_CNPJ: WideString;
    function Get_CPF: WideString;
    function Get_IE: WideString;
    function Get_XNome: WideString;
    function Get_Fone: WideString;
    function Get_ISUF: WideString;
    function Get_EnderDest: IXMLTEndereco;
    function Get_LocEnt: IXMLTEndReEnt;
    procedure Set_CNPJ(Value: WideString);
    procedure Set_CPF(Value: WideString);
    procedure Set_IE(Value: WideString);
    procedure Set_XNome(Value: WideString);
    procedure Set_Fone(Value: WideString);
    procedure Set_ISUF(Value: WideString);
    { Methods & Properties }
    property CNPJ: WideString read Get_CNPJ write Set_CNPJ;
    property CPF: WideString read Get_CPF write Set_CPF;
    property IE: WideString read Get_IE write Set_IE;
    property XNome: WideString read Get_XNome write Set_XNome;
    property Fone: WideString read Get_Fone write Set_Fone;
    property ISUF: WideString read Get_ISUF write Set_ISUF;
    property EnderDest: IXMLTEndereco read Get_EnderDest;
    property LocEnt: IXMLTEndReEnt read Get_LocEnt;
  end;

{ IXMLVPrest }

  IXMLVPrest = interface(IXMLNode)
    ['{8773BDE3-139B-4766-A305-94120A2FFE16}']
    { Property Accessors }
    function Get_VTPrest: WideString;
    function Get_VRec: WideString;
    function Get_Comp: IXMLCompList;
    procedure Set_VTPrest(Value: WideString);
    procedure Set_VRec(Value: WideString);
    { Methods & Properties }
    property VTPrest: WideString read Get_VTPrest write Set_VTPrest;
    property VRec: WideString read Get_VRec write Set_VRec;
    property Comp: IXMLCompList read Get_Comp;
  end;

{ IXMLComp }

  IXMLComp = interface(IXMLNode)
    ['{6306283D-45A2-4DE9-A067-150C662000CD}']
    { Property Accessors }
    function Get_XNome: WideString;
    function Get_VComp: WideString;
    procedure Set_XNome(Value: WideString);
    procedure Set_VComp(Value: WideString);
    { Methods & Properties }
    property XNome: WideString read Get_XNome write Set_XNome;
    property VComp: WideString read Get_VComp write Set_VComp;
  end;

{ IXMLCompList }

  IXMLCompList = interface(IXMLNodeCollection)
    ['{34518B98-D82E-4C5E-9ACE-9F7A9B0C3623}']
    { Methods & Properties }
    function Add: IXMLComp;
    function Insert(const Index: Integer): IXMLComp;
    function Get_Item(Index: Integer): IXMLComp;
    property Items[Index: Integer]: IXMLComp read Get_Item; default;
  end;

{ IXMLImp }

  IXMLImp = interface(IXMLNode)
    ['{769BC087-BA21-41C6-A641-83ECCAE8F081}']
    { Property Accessors }
    function Get_ICMS: IXMLTCST;
    function Get_InfAdFisco: WideString;
    procedure Set_InfAdFisco(Value: WideString);
    { Methods & Properties }
    property ICMS: IXMLTCST read Get_ICMS;
    property InfAdFisco: WideString read Get_InfAdFisco write Set_InfAdFisco;
  end;

{ IXMLTCST }

  IXMLTCST = interface(IXMLNode)
    ['{D2D62CB2-4E49-4933-B8C5-2DD777B38727}']
    { Property Accessors }
    function Get_CST00: IXMLCST00;
    function Get_CST20: IXMLCST20;
    function Get_CST45: IXMLCST45;
    function Get_CST80: IXMLCST80;
    function Get_CST81: IXMLCST81;
    function Get_CST90: IXMLCST90;

    function Get_ICMS00 :      IXMLICMS00; //1.04
    function Get_ICMS20 :      IXMLICMS20; //1.04
    function Get_ICMS45 :      IXMLICMS45; //1.04
    function Get_ICMS60 :      IXMLICMS60; //1.04
    function Get_ICMS90 :      IXMLICMS90; //1.04
    function Get_ICMSOutraUF : IXMLICMSOutraUF; //1.04
    function Get_ICMSSN :      IXMLICMSSN; //1.04

    { Methods & Properties }
    property CST00: IXMLCST00 read Get_CST00;
    property CST20: IXMLCST20 read Get_CST20;
    property CST45: IXMLCST45 read Get_CST45;
    property CST80: IXMLCST80 read Get_CST80;
    property CST81: IXMLCST81 read Get_CST81;
    property CST90: IXMLCST90 read Get_CST90;

    property ICMS00 :      IXMLICMS00      read Get_ICMS00;      //1.04
    property ICMS20 :      IXMLICMS20      read Get_ICMS20;      //1.04
    property ICMS45 :      IXMLICMS45      read Get_ICMS45;      //1.04
    property ICMS60 :      IXMLICMS60      read Get_ICMS60;      //1.04
    property ICMS90 :      IXMLICMS90      read Get_ICMS90;      //1.04
    property ICMSOutraUF : IXMLICMSOutraUF read Get_ICMSOutraUF; //1.04
    property ICMSSN :      IXMLICMSSN      read Get_ICMSSN;      //1.04

  end;

{ IXMLCST00 }

  IXMLCST00 = interface(IXMLNode)
    ['{1077D03E-B9CF-420D-8654-56C7141C27C6}']
    { Property Accessors }
    function Get_CST: WideString;
    function Get_vBC: WideString;
    function Get_pICMS: WideString;
    function Get_vICMS: WideString;
    procedure Set_CST(Value: WideString);
    procedure Set_vBC(Value: WideString);
    procedure Set_pICMS(Value: WideString);
    procedure Set_vICMS(Value: WideString);
    { Methods & Properties }
    property CST: WideString read Get_CST write Set_CST;
    property vBC: WideString read Get_vBC write Set_vBC;
    property pICMS: WideString read Get_pICMS write Set_pICMS;
    property vICMS: WideString read Get_vICMS write Set_vICMS;
  end;

{ IXMLCST20 }

  IXMLCST20 = interface(IXMLNode)
    ['{162301C7-8DB2-4833-9ABC-A63DCE696F7C}']
    { Property Accessors }
    function Get_CST: WideString;
    function Get_PRedBC: WideString;
    function Get_vBC: WideString;
    function Get_pICMS: WideString;
    function Get_vICMS: WideString;
    procedure Set_CST(Value: WideString);
    procedure Set_PRedBC(Value: WideString);
    procedure Set_vBC(Value: WideString);
    procedure Set_pICMS(Value: WideString);
    procedure Set_vICMS(Value: WideString);
    { Methods & Properties }
    property CST: WideString read Get_CST write Set_CST;
    property PRedBC: WideString read Get_PRedBC write Set_PRedBC;
    property vBC: WideString read Get_vBC write Set_vBC;
    property pICMS: WideString read Get_pICMS write Set_pICMS;
    property vICMS: WideString read Get_vICMS write Set_vICMS;
  end;

{ IXMLCST45 }

  IXMLCST45 = interface(IXMLNode)
    ['{4C551FAD-148D-4D38-AF03-240D9ED19AD0}']
    { Property Accessors }
    function Get_CST: WideString;
    procedure Set_CST(Value: WideString);
    { Methods & Properties }
    property CST: WideString read Get_CST write Set_CST;
  end;

{ IXMLCST80 }

  IXMLCST80 = interface(IXMLNode)
    ['{F4F447AA-C99A-437A-9690-C7F991CBE7A4}']
    { Property Accessors }
    function Get_CST: WideString;
    function Get_vBC: WideString;
    function Get_pICMS: WideString;
    function Get_vICMS: WideString;
    function Get_VCred: WideString;
    procedure Set_CST(Value: WideString);
    procedure Set_vBC(Value: WideString);
    procedure Set_pICMS(Value: WideString);
    procedure Set_vICMS(Value: WideString);
    procedure Set_VCred(Value: WideString);
    { Methods & Properties }
    property CST: WideString read Get_CST write Set_CST;
    property vBC: WideString read Get_vBC write Set_vBC;
    property pICMS: WideString read Get_pICMS write Set_pICMS;
    property vICMS: WideString read Get_vICMS write Set_vICMS;
    property VCred: WideString read Get_VCred write Set_VCred;
  end;

{ IXMLCST81 }

  IXMLCST81 = interface(IXMLNode)
    ['{24BA8039-62FB-4EF7-A39B-63F2BD4F1A39}']
    { Property Accessors }
    function Get_CST: WideString;
    function Get_PRedBC: WideString;
    function Get_vBC: WideString;
    function Get_pICMS: WideString;
    function Get_vICMS: WideString;
    procedure Set_CST(Value: WideString);
    procedure Set_PRedBC(Value: WideString);
    procedure Set_vBC(Value: WideString);
    procedure Set_pICMS(Value: WideString);
    procedure Set_vICMS(Value: WideString);
    { Methods & Properties }
    property CST: WideString read Get_CST write Set_CST;
    property PRedBC: WideString read Get_PRedBC write Set_PRedBC;
    property vBC: WideString read Get_vBC write Set_vBC;
    property pICMS: WideString read Get_pICMS write Set_pICMS;
    property vICMS: WideString read Get_vICMS write Set_vICMS;
  end;

{ IXMLCST90 }

  IXMLCST90 = interface(IXMLNode)
    ['{DFC56030-7739-4AE6-B455-D316706361E1}']
    { Property Accessors }
    function Get_CST: WideString;
    function Get_PRedBC: WideString;
    function Get_vBC: WideString;
    function Get_pICMS: WideString;
    function Get_vICMS: WideString;
    function Get_VCred: WideString;
    procedure Set_CST(Value: WideString);
    procedure Set_PRedBC(Value: WideString);
    procedure Set_vBC(Value: WideString);
    procedure Set_pICMS(Value: WideString);
    procedure Set_vICMS(Value: WideString);
    procedure Set_VCred(Value: WideString);
    { Methods & Properties }
    property CST: WideString read Get_CST write Set_CST;
    property PRedBC: WideString read Get_PRedBC write Set_PRedBC;
    property vBC: WideString read Get_vBC write Set_vBC;
    property pICMS: WideString read Get_pICMS write Set_pICMS;
    property vICMS: WideString read Get_vICMS write Set_vICMS;
    property VCred: WideString read Get_VCred write Set_VCred;
  end;


{ IXMLICMS00 }

  IXMLICMS00 = interface(IXMLNode)
    ['{F8F3C807-7987-4F82-BF8B-2EE9197C3924}']
    { Property Accessors }
    function  Get_CST: WideString;
    function  Get_vBC: WideString;
    function  Get_pICMS: WideString;
    function  Get_vICMS: WideString;
    procedure Set_CST(Value: WideString);
    procedure Set_vBC(Value: WideString);
    procedure Set_pICMS(Value: WideString);
    procedure Set_vICMS(Value: WideString);
    { Methods & Properties }
    property CST: WideString read Get_CST write Set_CST;
    property vBC: WideString read Get_vBC write Set_vBC;
    property pICMS: WideString read Get_pICMS write Set_pICMS;
    property vICMS: WideString read Get_vICMS write Set_vICMS;
  end;

{ IXMLICMS20 }

  IXMLICMS20 = interface(IXMLNode)
    ['{21221BAA-F04E-42A3-A031-B2741CCD2670}']
    { Property Accessors }
    function Get_CST: WideString;
    function Get_PRedBC: WideString;
    function Get_vBC: WideString;
    function Get_pICMS: WideString;
    function Get_vICMS: WideString;

    procedure Set_CST(Value: WideString);
    procedure Set_PRedBC(Value: WideString);
    procedure Set_vBC(Value: WideString);
    procedure Set_pICMS(Value: WideString);
    procedure Set_vICMS(Value: WideString);
    { Methods & Properties }
    property CST: WideString    read Get_CST    write Set_CST;
    property PRedBC: WideString read Get_PRedBC write Set_PRedBC;
    property vBC: WideString    read Get_vBC    write Set_vBC;
    property pICMS: WideString  read Get_pICMS  write Set_pICMS;
    property vICMS: WideString  read Get_vICMS  write Set_vICMS;
  end;

{ IXMLICMS45 }

  IXMLICMS45 = interface(IXMLNode)
    ['{F49C57E1-BA97-4D4C-A9A0-D798957647F0}']
    { Property Accessors }
    function  Get_CST: WideString;
    procedure Set_CST(Value: WideString);
    { Methods & Properties }
    property CST: WideString read Get_CST write Set_CST;
  end;

{ IXMLICMS60 }

  IXMLICMS60 = interface(IXMLNode)
    ['{CB29DA73-6E6A-42A6-A3EE-084DAEB7125F}']
    { Property Accessors }
    function Get_CST: WideString;
    function Get_vBCSTRet: WideString;
    function Get_vICMSSTRet: WideString;
    function Get_pICMSSTRet: WideString;
    function Get_vCred: WideString;

    procedure Set_CST(Value: WideString);
    procedure Set_vBCSTRet(Value: WideString);
    procedure Set_vICMSSTRet(Value: WideString);
    procedure Set_pICMSSTRet(Value: WideString);
    procedure Set_vCred(Value: WideString);

    { Methods & Properties }
    property CST        : WideString read Get_CST        write Set_CST;
    property vBCSTRet   : WideString read Get_vBCSTRet   write Set_vBCSTRet;
    property vICMSSTRet : WideString read Get_vICMSSTRet write Set_vICMSSTRet;
    property pICMSSTRet : WideString read Get_pICMSSTRet write Set_pICMSSTRet;
    property vCred      : WideString read Get_vCred      write Set_vCred;
  end;

{ IXMLCST90 }

  IXMLICMS90 = interface(IXMLNode)
    ['{C4DBB857-F6DA-461B-A788-428CC4C8116C}']
    { Property Accessors }
    function Get_CST   : WideString;
    function Get_PRedBC: WideString;
    function Get_vBC   : WideString;
    function Get_pICMS : WideString;
    function Get_vICMS : WideString;
    function Get_VCred : WideString;

    procedure Set_CST(Value   : WideString);
    procedure Set_PRedBC(Value: WideString);
    procedure Set_vBC(Value   : WideString);
    procedure Set_pICMS(Value : WideString);
    procedure Set_vICMS(Value : WideString);
    procedure Set_VCred(Value : WideString);
    { Methods & Properties }
    property CST:    WideString read Get_CST    write Set_CST;
    property PRedBC: WideString read Get_PRedBC write Set_PRedBC;
    property vBC:    WideString read Get_vBC    write Set_vBC;
    property pICMS:  WideString read Get_pICMS  write Set_pICMS;
    property vICMS:  WideString read Get_vICMS  write Set_vICMS;
    property VCred:  WideString read Get_VCred  write Set_VCred;
  end;

{ IXMLICMSOutraUF }

  IXMLICMSOutraUF = interface(IXMLNode)
    ['{7902DA20-FB29-4708-A4AB-D573DA116D98}']
    { Property Accessors }
    function Get_CST   : WideString;
    function Get_PRedBC: WideString;
    function Get_vBC   : WideString;
    function Get_pICMS : WideString;
    function Get_vICMS : WideString;
    function Get_VCred : WideString;

    procedure Set_CST(Value   : WideString);
    procedure Set_PRedBC(Value: WideString);
    procedure Set_vBC(Value   : WideString);
    procedure Set_pICMS(Value : WideString);
    procedure Set_vICMS(Value : WideString);
    procedure Set_VCred(Value : WideString);
    { Methods & Properties }
    property CST:           WideString read Get_CST    write Set_CST;
    property PRedBCOutraUF: WideString read Get_PRedBC write Set_PRedBC;
    property vBCOutraUF:    WideString read Get_vBC    write Set_vBC;
    property pICMSOutraUF:  WideString read Get_pICMS  write Set_pICMS;
    property vICMSOutraUF:  WideString read Get_vICMS  write Set_vICMS;
  end;

{ IXMLICMSSN }

  IXMLICMSSN = interface(IXMLNode)
    ['{4A13B70D-8C31-4ECE-8225-19795745996B}']
    { Property Accessors }
    function  Get_indSN: WideString;
    function  Get_infAdFisco: WideString;
    procedure Set_indSN(Value: WideString);
    procedure Set_infAdFisco(Value: WideString);
    { Methods & Properties }
    property indSN:      WideString read Get_indSN      write Set_indSN;
    property infAdFisco: WideString read Get_infAdFisco write Set_infAdFisco;
  end;

{ IXMLInfCTeNorm }

  IXMLInfCTeNorm = interface(IXMLNode)
    ['{1266F86E-F0F8-483F-B469-D121DA993BAF}']
    { Property Accessors }
    function Get_InfCarga: IXMLInfCarga;
    function Get_ContQt: IXMLContQtList;
    function Get_DocAnt: IXMLDocAnt;
    function Get_Seg: IXMLSegList;

    function Get_Rodo: IXMLRodo;
    function Get_infModal: IXMLinfModal;

    function Get_Aereo: IXMLAereo;
    function Get_Aquav: IXMLAquav;
    function Get_Ferrov: IXMLFerrov;
    function Get_Duto: IXMLDuto;
    function Get_Peri: IXMLPeriList;
    function Get_VeicNovos: IXMLVeicNovosList;
    function Get_InfCteSub: IXMLInfCteSub;
    { Methods & Properties }
    property InfCarga: IXMLInfCarga read Get_InfCarga;
    property ContQt: IXMLContQtList read Get_ContQt;
    property DocAnt: IXMLDocAnt read Get_DocAnt;
    property Seg: IXMLSegList read Get_Seg;

    property infModal: IXMLinfModal read Get_infModal;
    property Rodo:     IXMLRodo     read Get_Rodo;

    property Aereo: IXMLAereo read Get_Aereo;
    property Aquav: IXMLAquav read Get_Aquav;
    property Ferrov: IXMLFerrov read Get_Ferrov;
    property Duto: IXMLDuto read Get_Duto;
    property Peri: IXMLPeriList read Get_Peri;
    property VeicNovos: IXMLVeicNovosList read Get_VeicNovos;
    property InfCteSub: IXMLInfCteSub read Get_InfCteSub;
  end;

{ IXMLInfCarga }

  IXMLInfCarga = interface(IXMLNode)
    ['{5B16C242-CA93-416D-8ACE-46676D8F7470}']
    { Property Accessors }
    function Get_VMerc: WideString;
    function Get_vCarga: WideString;
    function Get_ProPred: WideString;
    function Get_XOutCat: WideString;
    function Get_InfQ: IXMLInfQList;
    procedure Set_VMerc(Value: WideString);
    procedure Set_vCarga(Value: WideString);
    procedure Set_ProPred(Value: WideString);
    procedure Set_XOutCat(Value: WideString);
    { Methods & Properties }
    property VMerc: WideString   read Get_VMerc   write Set_VMerc;
    property vCarga: WideString  read Get_vCarga  write Set_vCarga;
    property ProPred: WideString read Get_ProPred write Set_ProPred;
    property XOutCat: WideString read Get_XOutCat write Set_XOutCat;
    property InfQ: IXMLInfQList  read Get_InfQ;
  end;

{ IXMLInfQ }

  IXMLInfQ = interface(IXMLNode)
    ['{0F5E6DE9-597C-456A-8452-DD714F90E752}']
    { Property Accessors }
    function Get_CUnid: WideString;
    function Get_TpMed: WideString;
    function Get_QCarga: WideString;
    procedure Set_CUnid(Value: WideString);
    procedure Set_TpMed(Value: WideString);
    procedure Set_QCarga(Value: WideString);
    { Methods & Properties }
    property CUnid: WideString read Get_CUnid write Set_CUnid;
    property TpMed: WideString read Get_TpMed write Set_TpMed;
    property QCarga: WideString read Get_QCarga write Set_QCarga;
  end;

{ IXMLInfQList }

  IXMLInfQList = interface(IXMLNodeCollection)
    ['{5ED83E5B-B568-46E9-8867-CB84BE114A92}']
    { Methods & Properties }
    function Add: IXMLInfQ;
    function Insert(const Index: Integer): IXMLInfQ;
    function Get_Item(Index: Integer): IXMLInfQ;
    property Items[Index: Integer]: IXMLInfQ read Get_Item; default;
  end;

{ IXMLContQt }

  IXMLContQt = interface(IXMLNode)
    ['{2362AC51-4967-4C78-A15E-FD2C3AFFB222}']
    { Property Accessors }
    function Get_NCont: WideString;
    function Get_LacContQt: IXMLLacContQtList;
    function Get_DPrev: WideString;
    procedure Set_NCont(Value: WideString);
    procedure Set_DPrev(Value: WideString);
    { Methods & Properties }
    property NCont: WideString read Get_NCont write Set_NCont;
    property LacContQt: IXMLLacContQtList read Get_LacContQt;
    property DPrev: WideString read Get_DPrev write Set_DPrev;
  end;

{ IXMLContQtList }

  IXMLContQtList = interface(IXMLNodeCollection)
    ['{D6DBC163-14F7-47B0-81A7-83E3406D5E11}']
    { Methods & Properties }
    function Add: IXMLContQt;
    function Insert(const Index: Integer): IXMLContQt;
    function Get_Item(Index: Integer): IXMLContQt;
    property Items[Index: Integer]: IXMLContQt read Get_Item; default;
  end;

{ IXMLLacContQt }

  IXMLLacContQt = interface(IXMLNode)
    ['{B6B9D30D-4204-4454-A5D8-5661DF986FF9}']
    { Property Accessors }
    function Get_NLacre: WideString;
    procedure Set_NLacre(Value: WideString);
    { Methods & Properties }
    property NLacre: WideString read Get_NLacre write Set_NLacre;
  end;

{ IXMLLacContQtList }

  IXMLLacContQtList = interface(IXMLNodeCollection)
    ['{2BCFE8A8-D816-44C0-9648-7F418700F426}']
    { Methods & Properties }
    function Add: IXMLLacContQt;
    function Insert(const Index: Integer): IXMLLacContQt;
    function Get_Item(Index: Integer): IXMLLacContQt;
    property Items[Index: Integer]: IXMLLacContQt read Get_Item; default;
  end;

{ IXMLDocAnt }

  IXMLDocAnt = interface(IXMLNodeCollection)
    ['{B1BEDB8D-27DC-4454-8A51-D763149BB7AB}']
    { Property Accessors }
    function Get_EmiDocAnt(Index: Integer): IXMLEmiDocAnt;
    { Methods & Properties }
    function Add: IXMLEmiDocAnt;
    function Insert(const Index: Integer): IXMLEmiDocAnt;
    property EmiDocAnt[Index: Integer]: IXMLEmiDocAnt read Get_EmiDocAnt; default;
  end;

{ IXMLEmiDocAnt }

  IXMLEmiDocAnt = interface(IXMLNode)
    ['{A1DDE1B3-97A0-4F9A-9C48-9BBDFE26F342}']
    { Property Accessors }
    function Get_CNPJ: WideString;
    function Get_CPF: WideString;
    function Get_IE: WideString;
    function Get_UF: WideString;
    function Get_XNome: WideString;
    function Get_IdDocAnt: IXMLIdDocAntList;
    procedure Set_CNPJ(Value: WideString);
    procedure Set_CPF(Value: WideString);
    procedure Set_IE(Value: WideString);
    procedure Set_UF(Value: WideString);
    procedure Set_XNome(Value: WideString);
    { Methods & Properties }
    property CNPJ: WideString read Get_CNPJ write Set_CNPJ;
    property CPF: WideString read Get_CPF write Set_CPF;
    property IE: WideString read Get_IE write Set_IE;
    property UF: WideString read Get_UF write Set_UF;
    property XNome: WideString read Get_XNome write Set_XNome;
    property IdDocAnt: IXMLIdDocAntList read Get_IdDocAnt;
  end;

{ IXMLIdDocAnt }

  IXMLIdDocAnt = interface(IXMLNode)
    ['{B78B39DA-7C03-4D4B-9EBC-7AFC3C278BFA}']
    { Property Accessors }
    function Get_IdDocAntPap: IXMLIdDocAntPapList;
    function Get_IdDocAntEle: IXMLIdDocAntEleList;
    { Methods & Properties }
    property IdDocAntPap: IXMLIdDocAntPapList read Get_IdDocAntPap;
    property IdDocAntEle: IXMLIdDocAntEleList read Get_IdDocAntEle;
  end;

{ IXMLIdDocAntList }

  IXMLIdDocAntList = interface(IXMLNodeCollection)
    ['{C41349F3-82B4-4271-9422-50EEF5E7FD6B}']
    { Methods & Properties }
    function Add: IXMLIdDocAnt;
    function Insert(const Index: Integer): IXMLIdDocAnt;
    function Get_Item(Index: Integer): IXMLIdDocAnt;
    property Items[Index: Integer]: IXMLIdDocAnt read Get_Item; default;
  end;

{ IXMLIdDocAntPap }

  IXMLIdDocAntPap = interface(IXMLNode)
    ['{04774C21-7F5F-45B8-8E2A-157E7AD36B0A}']
    { Property Accessors }
    function Get_TpDoc: WideString;
    function Get_Serie: WideString;
    function Get_Subser: WideString;
    function Get_NDoc: WideString;
    function Get_DEmi: WideString;
    procedure Set_TpDoc(Value: WideString);
    procedure Set_Serie(Value: WideString);
    procedure Set_Subser(Value: WideString);
    procedure Set_NDoc(Value: WideString);
    procedure Set_DEmi(Value: WideString);
    { Methods & Properties }
    property TpDoc: WideString read Get_TpDoc write Set_TpDoc;
    property Serie: WideString read Get_Serie write Set_Serie;
    property Subser: WideString read Get_Subser write Set_Subser;
    property NDoc: WideString read Get_NDoc write Set_NDoc;
    property DEmi: WideString read Get_DEmi write Set_DEmi;
  end;

{ IXMLIdDocAntPapList }

  IXMLIdDocAntPapList = interface(IXMLNodeCollection)
    ['{D0CE7692-B977-494A-8404-2E55476B1A5B}']
    { Methods & Properties }
    function Add: IXMLIdDocAntPap;
    function Insert(const Index: Integer): IXMLIdDocAntPap;
    function Get_Item(Index: Integer): IXMLIdDocAntPap;
    property Items[Index: Integer]: IXMLIdDocAntPap read Get_Item; default;
  end;

{ IXMLIdDocAntEle }

  IXMLIdDocAntEle = interface(IXMLNode)
    ['{FCDF349E-4533-473F-B072-35C89F22C1E1}']
    { Property Accessors }
    function Get_Chave: WideString;
    procedure Set_Chave(Value: WideString);
    { Methods & Properties }
    property Chave: WideString read Get_Chave write Set_Chave;
  end;

{ IXMLIdDocAntEleList }

  IXMLIdDocAntEleList = interface(IXMLNodeCollection)
    ['{E18700D5-14EF-46A6-9C95-13421AC4145A}']
    { Methods & Properties }
    function Add: IXMLIdDocAntEle;
    function Insert(const Index: Integer): IXMLIdDocAntEle;
    function Get_Item(Index: Integer): IXMLIdDocAntEle;
    property Items[Index: Integer]: IXMLIdDocAntEle read Get_Item; default;
  end;

{ IXMLSeg }

  IXMLSeg = interface(IXMLNode)
    ['{39592E2F-37BD-477B-8D08-FE3E13877BB6}']
    { Property Accessors }
    function Get_RespSeg: WideString;
    function Get_XSeg: WideString;
    function Get_NApol: WideString;
    function Get_NAver: WideString;
    function Get_VMerc: WideString;
    function Get_vCarga: WideString;
    procedure Set_RespSeg(Value: WideString);
    procedure Set_XSeg(Value: WideString);
    procedure Set_NApol(Value: WideString);
    procedure Set_NAver(Value: WideString);
    procedure Set_VMerc(Value: WideString);
    procedure Set_vCarga(Value: WideString);
    { Methods & Properties }
    property RespSeg: WideString read Get_RespSeg write Set_RespSeg;
    property XSeg: WideString    read Get_XSeg    write Set_XSeg;
    property NApol: WideString   read Get_NApol   write Set_NApol;
    property NAver: WideString   read Get_NAver   write Set_NAver;
    property VMerc: WideString   read Get_VMerc   write Set_VMerc;
    property vCarga: WideString  read Get_vCarga  write Set_vCarga;
  end;

{ IXMLSegList }

  IXMLSegList = interface(IXMLNodeCollection)
    ['{B12F534B-880A-4024-B395-51E8DF3E29AA}']
    { Methods & Properties }
    function Add: IXMLSeg;
    function Insert(const Index: Integer): IXMLSeg;
    function Get_Item(Index: Integer): IXMLSeg;
    property Items[Index: Integer]: IXMLSeg read Get_Item; default;
  end;

{ IXMLinfModal }

  IXMLinfModal = interface(IXMLNode)
    ['{2CD55AA6-E506-42C4-8A7C-AD7D9CDDA443}']
    { Property Accessors }
    function Get_Rodo : IXMLRodo;
    { Methods & Properties }
    property Rodo: IXMLRodo read Get_Rodo;
  end;

{ IXMLRodo }

  IXMLRodo = interface(IXMLNode)
    ['{6B1AAD18-2A3E-4FD3-9D14-D716B05379F4}']
    { Property Accessors }
    function Get_RNTRC: WideString;
    function Get_DPrev: WideString;
    function Get_Lota: WideString;
    function Get_CTRB: IXMLCTRB;
    function Get_Occ: IXMLOcc;
    function Get_ValePed: IXMLValePed;
    function Get_Veic: IXMLVeicList;
    function Get_LacRodo: IXMLLacRodoList;
    function Get_Moto: IXMLMotoList;
    procedure Set_RNTRC(Value: WideString);
    procedure Set_DPrev(Value: WideString);
    procedure Set_Lota(Value: WideString);
    { Methods & Properties }
    property RNTRC: WideString read Get_RNTRC write Set_RNTRC;
    property DPrev: WideString read Get_DPrev write Set_DPrev;
    property Lota: WideString read Get_Lota write Set_Lota;
    property CTRB: IXMLCTRB read Get_CTRB;
    property Occ: IXMLOcc read Get_Occ;
    property ValePed: IXMLValePed read Get_ValePed;
    property Veic: IXMLVeicList read Get_Veic;
    property LacRodo: IXMLLacRodoList read Get_LacRodo;
    property Moto: IXMLMotoList read Get_Moto;
  end;

{ IXMLCTRB }

  IXMLCTRB = interface(IXMLNode)
    ['{7C2D0D75-3A02-4DCC-A87E-293386C9C99F}']
    { Property Accessors }
    function Get_Serie: WideString;
    function Get_NCTRB: WideString;
    procedure Set_Serie(Value: WideString);
    procedure Set_NCTRB(Value: WideString);
    { Methods & Properties }
    property Serie: WideString read Get_Serie write Set_Serie;
    property NCTRB: WideString read Get_NCTRB write Set_NCTRB;
  end;

{ IXMLOcc }

  IXMLOcc = interface(IXMLNode)
    ['{EB8331D6-A3F9-4E6A-919E-E7AE2128314D}']
    { Property Accessors }
    function Get_Serie: WideString;
    function Get_NOcc: WideString;
    function Get_DEmi: WideString;
    function Get_EmiOcc: IXMLEmiOcc;
    procedure Set_Serie(Value: WideString);
    procedure Set_NOcc(Value: WideString);
    procedure Set_DEmi(Value: WideString);
    { Methods & Properties }
    property Serie: WideString read Get_Serie write Set_Serie;
    property NOcc: WideString read Get_NOcc write Set_NOcc;
    property DEmi: WideString read Get_DEmi write Set_DEmi;
    property EmiOcc: IXMLEmiOcc read Get_EmiOcc;
  end;

{ IXMLEmiOcc }

  IXMLEmiOcc = interface(IXMLNode)
    ['{501AE78C-FD38-4786-BF98-C2113661A8D8}']
    { Property Accessors }
    function Get_CNPJ: WideString;
    function Get_CInt: WideString;
    function Get_IE: WideString;
    function Get_UF: WideString;
    function Get_Fone: WideString;
    procedure Set_CNPJ(Value: WideString);
    procedure Set_CInt(Value: WideString);
    procedure Set_IE(Value: WideString);
    procedure Set_UF(Value: WideString);
    procedure Set_Fone(Value: WideString);
    { Methods & Properties }
    property CNPJ: WideString read Get_CNPJ write Set_CNPJ;
    property CInt: WideString read Get_CInt write Set_CInt;
    property IE: WideString read Get_IE write Set_IE;
    property UF: WideString read Get_UF write Set_UF;
    property Fone: WideString read Get_Fone write Set_Fone;
  end;

{ IXMLValePed }

  IXMLValePed = interface(IXMLNode)
    ['{F835AC4A-0AC6-4729-A094-3A1BB2EEFA84}']
    { Property Accessors }
    function Get_NroRE: WideString;
    function Get_VTValePed: WideString;
    function Get_RespPg: WideString;
    function Get_Disp: IXMLDispList;
    procedure Set_NroRE(Value: WideString);
    procedure Set_VTValePed(Value: WideString);
    procedure Set_RespPg(Value: WideString);
    { Methods & Properties }
    property NroRE: WideString read Get_NroRE write Set_NroRE;
    property VTValePed: WideString read Get_VTValePed write Set_VTValePed;
    property RespPg: WideString read Get_RespPg write Set_RespPg;
    property Disp: IXMLDispList read Get_Disp;
  end;

{ IXMLDisp }

  IXMLDisp = interface(IXMLNode)
    ['{F28D7706-092C-43C7-B4CA-BF8A7D754D3C}']
    { Property Accessors }
    function Get_TpDisp: WideString;
    function Get_XEmp: WideString;
    function Get_DVig: WideString;
    function Get_NDisp: WideString;
    function Get_NCompC: WideString;
    procedure Set_TpDisp(Value: WideString);
    procedure Set_XEmp(Value: WideString);
    procedure Set_DVig(Value: WideString);
    procedure Set_NDisp(Value: WideString);
    procedure Set_NCompC(Value: WideString);
    { Methods & Properties }
    property TpDisp: WideString read Get_TpDisp write Set_TpDisp;
    property XEmp: WideString read Get_XEmp write Set_XEmp;
    property DVig: WideString read Get_DVig write Set_DVig;
    property NDisp: WideString read Get_NDisp write Set_NDisp;
    property NCompC: WideString read Get_NCompC write Set_NCompC;
  end;

{ IXMLDispList }

  IXMLDispList = interface(IXMLNodeCollection)
    ['{1BB3A441-75AE-47FC-8CB3-A95CE024FC06}']
    { Methods & Properties }
    function Add: IXMLDisp;
    function Insert(const Index: Integer): IXMLDisp;
    function Get_Item(Index: Integer): IXMLDisp;
    property Items[Index: Integer]: IXMLDisp read Get_Item; default;
  end;

{ IXMLVeic }

  IXMLVeic = interface(IXMLNode)
    ['{1F6BD44C-ECF9-4868-9E03-4707DCC3581D}']
    { Property Accessors }
    function Get_CInt: WideString;
    function Get_RENAVAM: WideString;
    function Get_Placa: WideString;
    function Get_Tara: WideString;
    function Get_CapKG: WideString;
    function Get_CapM3: WideString;
    function Get_TpProp: WideString;
    function Get_TpVeic: WideString;
    function Get_TpRod: WideString;
    function Get_TpCar: WideString;
    function Get_UF: WideString;
    function Get_Prop: IXMLProp;
    procedure Set_CInt(Value: WideString);
    procedure Set_RENAVAM(Value: WideString);
    procedure Set_Placa(Value: WideString);
    procedure Set_Tara(Value: WideString);
    procedure Set_CapKG(Value: WideString);
    procedure Set_CapM3(Value: WideString);
    procedure Set_TpProp(Value: WideString);
    procedure Set_TpVeic(Value: WideString);
    procedure Set_TpRod(Value: WideString);
    procedure Set_TpCar(Value: WideString);
    procedure Set_UF(Value: WideString);
    { Methods & Properties }
    property CInt: WideString read Get_CInt write Set_CInt;
    property RENAVAM: WideString read Get_RENAVAM write Set_RENAVAM;
    property Placa: WideString read Get_Placa write Set_Placa;
    property Tara: WideString read Get_Tara write Set_Tara;
    property CapKG: WideString read Get_CapKG write Set_CapKG;
    property CapM3: WideString read Get_CapM3 write Set_CapM3;
    property TpProp: WideString read Get_TpProp write Set_TpProp;
    property TpVeic: WideString read Get_TpVeic write Set_TpVeic;
    property TpRod: WideString read Get_TpRod write Set_TpRod;
    property TpCar: WideString read Get_TpCar write Set_TpCar;
    property UF: WideString read Get_UF write Set_UF;
    property Prop: IXMLProp read Get_Prop;
  end;

{ IXMLVeicList }

  IXMLVeicList = interface(IXMLNodeCollection)
    ['{34A3CBA2-2582-4ACA-9802-D403E9C3A500}']
    { Methods & Properties }
    function Add: IXMLVeic;
    function Insert(const Index: Integer): IXMLVeic;
    function Get_Item(Index: Integer): IXMLVeic;
    property Items[Index: Integer]: IXMLVeic read Get_Item; default;
  end;

{ IXMLProp }

  IXMLProp = interface(IXMLNode)
    ['{7EA95881-A3C3-4B68-86DE-7C9DBEB4E3FE}']
    { Property Accessors }
    function Get_CPF: WideString;
    function Get_CNPJ: WideString;
    function Get_RNTRC: WideString;
    function Get_XNome: WideString;
    function Get_IE: WideString;
    function Get_UF: WideString;
    function Get_TpProp: WideString;
    procedure Set_CPF(Value: WideString);
    procedure Set_CNPJ(Value: WideString);
    procedure Set_RNTRC(Value: WideString);
    procedure Set_XNome(Value: WideString);
    procedure Set_IE(Value: WideString);
    procedure Set_UF(Value: WideString);
    procedure Set_TpProp(Value: WideString);
    { Methods & Properties }
    property CPF: WideString read Get_CPF write Set_CPF;
    property CNPJ: WideString read Get_CNPJ write Set_CNPJ;
    property RNTRC: WideString read Get_RNTRC write Set_RNTRC;
    property XNome: WideString read Get_XNome write Set_XNome;
    property IE: WideString read Get_IE write Set_IE;
    property UF: WideString read Get_UF write Set_UF;
    property TpProp: WideString read Get_TpProp write Set_TpProp;
  end;

{ IXMLLacRodo }

  IXMLLacRodo = interface(IXMLNode)
    ['{F7273B8C-16D4-4D34-840B-E128428106B5}']
    { Property Accessors }
    function Get_NLacre: WideString;
    procedure Set_NLacre(Value: WideString);
    { Methods & Properties }
    property NLacre: WideString read Get_NLacre write Set_NLacre;
  end;

{ IXMLLacRodoList }

  IXMLLacRodoList = interface(IXMLNodeCollection)
    ['{E7EA637B-9804-4565-82F4-07E7BB8A3914}']
    { Methods & Properties }
    function Add: IXMLLacRodo;
    function Insert(const Index: Integer): IXMLLacRodo;
    function Get_Item(Index: Integer): IXMLLacRodo;
    property Items[Index: Integer]: IXMLLacRodo read Get_Item; default;
  end;

{ IXMLMoto }

  IXMLMoto = interface(IXMLNode)
    ['{575B4315-6415-49E9-8075-80042F5BF60E}']
    { Property Accessors }
    function Get_XNome: WideString;
    function Get_CPF: WideString;
    procedure Set_XNome(Value: WideString);
    procedure Set_CPF(Value: WideString);
    { Methods & Properties }
    property XNome: WideString read Get_XNome write Set_XNome;
    property CPF: WideString read Get_CPF write Set_CPF;
  end;

{ IXMLMotoList }

  IXMLMotoList = interface(IXMLNodeCollection)
    ['{D36E47AA-C8B4-4945-9553-A42140CD603F}']
    { Methods & Properties }
    function Add: IXMLMoto;
    function Insert(const Index: Integer): IXMLMoto;
    function Get_Item(Index: Integer): IXMLMoto;
    property Items[Index: Integer]: IXMLMoto read Get_Item; default;
  end;

{ IXMLAereo }

  IXMLAereo = interface(IXMLNode)
    ['{687CE276-7FB3-4C64-9489-B53D04342814}']
    { Property Accessors }
    function Get_NMinu: WideString;
    function Get_NOCA: WideString;
    function Get_DPrev: WideString;
    function Get_XLAgEmi: WideString;
    function Get_CIATA: WideString;
    function Get_Tarifa: IXMLTarifa;
    procedure Set_NMinu(Value: WideString);
    procedure Set_NOCA(Value: WideString);
    procedure Set_DPrev(Value: WideString);
    procedure Set_XLAgEmi(Value: WideString);
    procedure Set_CIATA(Value: WideString);
    { Methods & Properties }
    property NMinu: WideString read Get_NMinu write Set_NMinu;
    property NOCA: WideString read Get_NOCA write Set_NOCA;
    property DPrev: WideString read Get_DPrev write Set_DPrev;
    property XLAgEmi: WideString read Get_XLAgEmi write Set_XLAgEmi;
    property CIATA: WideString read Get_CIATA write Set_CIATA;
    property Tarifa: IXMLTarifa read Get_Tarifa;
  end;

{ IXMLTarifa }

  IXMLTarifa = interface(IXMLNode)
    ['{9C3F1913-5BC6-4624-B180-1F3C14403D32}']
    { Property Accessors }
    function Get_Trecho: WideString;
    function Get_CL: WideString;
    function Get_CTar: WideString;
    function Get_VTar: WideString;
    procedure Set_Trecho(Value: WideString);
    procedure Set_CL(Value: WideString);
    procedure Set_CTar(Value: WideString);
    procedure Set_VTar(Value: WideString);
    { Methods & Properties }
    property Trecho: WideString read Get_Trecho write Set_Trecho;
    property CL: WideString read Get_CL write Set_CL;
    property CTar: WideString read Get_CTar write Set_CTar;
    property VTar: WideString read Get_VTar write Set_VTar;
  end;

{ IXMLAquav }

  IXMLAquav = interface(IXMLNode)
    ['{A0FAAFB6-2693-4D74-8844-FB989EB3A467}']
    { Property Accessors }
    function Get_VPrest: WideString;
    function Get_VAFRMM: WideString;
    function Get_NBooking: WideString;
    function Get_NCtrl: WideString;
    function Get_XNavio: WideString;
    function Get_NViag: WideString;
    function Get_Direc: WideString;
    function Get_PrtEmb: WideString;
    function Get_PrtTrans: WideString;
    function Get_PrtDest: WideString;
    function Get_TpNav: WideString;
    function Get_Irin: WideString;
    function Get_Lacre: IXMLLacreList;
    procedure Set_VPrest(Value: WideString);
    procedure Set_VAFRMM(Value: WideString);
    procedure Set_NBooking(Value: WideString);
    procedure Set_NCtrl(Value: WideString);
    procedure Set_XNavio(Value: WideString);
    procedure Set_NViag(Value: WideString);
    procedure Set_Direc(Value: WideString);
    procedure Set_PrtEmb(Value: WideString);
    procedure Set_PrtTrans(Value: WideString);
    procedure Set_PrtDest(Value: WideString);
    procedure Set_TpNav(Value: WideString);
    procedure Set_Irin(Value: WideString);
    { Methods & Properties }
    property VPrest: WideString read Get_VPrest write Set_VPrest;
    property VAFRMM: WideString read Get_VAFRMM write Set_VAFRMM;
    property NBooking: WideString read Get_NBooking write Set_NBooking;
    property NCtrl: WideString read Get_NCtrl write Set_NCtrl;
    property XNavio: WideString read Get_XNavio write Set_XNavio;
    property NViag: WideString read Get_NViag write Set_NViag;
    property Direc: WideString read Get_Direc write Set_Direc;
    property PrtEmb: WideString read Get_PrtEmb write Set_PrtEmb;
    property PrtTrans: WideString read Get_PrtTrans write Set_PrtTrans;
    property PrtDest: WideString read Get_PrtDest write Set_PrtDest;
    property TpNav: WideString read Get_TpNav write Set_TpNav;
    property Irin: WideString read Get_Irin write Set_Irin;
    property Lacre: IXMLLacreList read Get_Lacre;
  end;

{ IXMLLacre }

  IXMLLacre = interface(IXMLNode)
    ['{A9AA5469-149E-4FDA-AB48-0A6675E5A2A3}']
    { Property Accessors }
    function Get_NLacre: WideString;
    procedure Set_NLacre(Value: WideString);
    { Methods & Properties }
    property NLacre: WideString read Get_NLacre write Set_NLacre;
  end;

{ IXMLLacreList }

  IXMLLacreList = interface(IXMLNodeCollection)
    ['{86C44B05-CB85-4E2E-BE3C-21D8E9AA31EE}']
    { Methods & Properties }
    function Add: IXMLLacre;
    function Insert(const Index: Integer): IXMLLacre;
    function Get_Item(Index: Integer): IXMLLacre;
    property Items[Index: Integer]: IXMLLacre read Get_Item; default;
  end;

{ IXMLFerrov }

  IXMLFerrov = interface(IXMLNode)
    ['{457CD0D0-0610-4CC1-A9C5-C31F35E220AE}']
    { Property Accessors }
    function Get_TpTraf: WideString;
    function Get_Fluxo: WideString;
    function Get_IdTrem: WideString;
    function Get_VFrete: WideString;
    function Get_FerroSub: IXMLFerroSub;
    function Get_DCL: IXMLDCLList;
    function Get_DetVag: IXMLDetVagList;
    procedure Set_TpTraf(Value: WideString);
    procedure Set_Fluxo(Value: WideString);
    procedure Set_IdTrem(Value: WideString);
    procedure Set_VFrete(Value: WideString);
    { Methods & Properties }
    property TpTraf: WideString read Get_TpTraf write Set_TpTraf;
    property Fluxo: WideString read Get_Fluxo write Set_Fluxo;
    property IdTrem: WideString read Get_IdTrem write Set_IdTrem;
    property VFrete: WideString read Get_VFrete write Set_VFrete;
    property FerroSub: IXMLFerroSub read Get_FerroSub;
    property DCL: IXMLDCLList read Get_DCL;
    property DetVag: IXMLDetVagList read Get_DetVag;
  end;

{ IXMLFerroSub }

  IXMLFerroSub = interface(IXMLNode)
    ['{568EF39C-233D-4ECD-9A00-3C41787BEA9D}']
    { Property Accessors }
    function Get_CNPJ: WideString;
    function Get_CInt: WideString;
    function Get_IE: WideString;
    function Get_XNome: WideString;
    function Get_EnderFerro: IXMLTEnderFer;
    procedure Set_CNPJ(Value: WideString);
    procedure Set_CInt(Value: WideString);
    procedure Set_IE(Value: WideString);
    procedure Set_XNome(Value: WideString);
    { Methods & Properties }
    property CNPJ: WideString read Get_CNPJ write Set_CNPJ;
    property CInt: WideString read Get_CInt write Set_CInt;
    property IE: WideString read Get_IE write Set_IE;
    property XNome: WideString read Get_XNome write Set_XNome;
    property EnderFerro: IXMLTEnderFer read Get_EnderFerro;
  end;

{ IXMLTEnderFer }

  IXMLTEnderFer = interface(IXMLNode)
    ['{D6DB87B6-43EE-4909-8D66-4601A8A81759}']
    { Property Accessors }
    function Get_XLgr: WideString;
    function Get_Nro: WideString;
    function Get_XCpl: WideString;
    function Get_XBairro: WideString;
    function Get_CMun: WideString;
    function Get_XMun: WideString;
    function Get_CEP: WideString;
    function Get_UF: WideString;
    procedure Set_XLgr(Value: WideString);
    procedure Set_Nro(Value: WideString);
    procedure Set_XCpl(Value: WideString);
    procedure Set_XBairro(Value: WideString);
    procedure Set_CMun(Value: WideString);
    procedure Set_XMun(Value: WideString);
    procedure Set_CEP(Value: WideString);
    procedure Set_UF(Value: WideString);
    { Methods & Properties }
    property XLgr: WideString read Get_XLgr write Set_XLgr;
    property Nro: WideString read Get_Nro write Set_Nro;
    property XCpl: WideString read Get_XCpl write Set_XCpl;
    property XBairro: WideString read Get_XBairro write Set_XBairro;
    property CMun: WideString read Get_CMun write Set_CMun;
    property XMun: WideString read Get_XMun write Set_XMun;
    property CEP: WideString read Get_CEP write Set_CEP;
    property UF: WideString read Get_UF write Set_UF;
  end;

{ IXMLDCL }

  IXMLDCL = interface(IXMLNode)
    ['{CAFC2EE0-1C9C-458F-B4FF-28549D559C97}']
    { Property Accessors }
    function Get_Serie: WideString;
    function Get_NDCL: WideString;
    function Get_DEmi: WideString;
    function Get_QVag: WideString;
    function Get_PCalc: WideString;
    function Get_VTar: WideString;
    function Get_VFrete: WideString;
    function Get_VSAcess: WideString;
    function Get_VTServ: WideString;
    function Get_IdTrem: WideString;
    function Get_DetVagDCL: IXMLDetVagDCLList;
    procedure Set_Serie(Value: WideString);
    procedure Set_NDCL(Value: WideString);
    procedure Set_DEmi(Value: WideString);
    procedure Set_QVag(Value: WideString);
    procedure Set_PCalc(Value: WideString);
    procedure Set_VTar(Value: WideString);
    procedure Set_VFrete(Value: WideString);
    procedure Set_VSAcess(Value: WideString);
    procedure Set_VTServ(Value: WideString);
    procedure Set_IdTrem(Value: WideString);
    { Methods & Properties }
    property Serie: WideString read Get_Serie write Set_Serie;
    property NDCL: WideString read Get_NDCL write Set_NDCL;
    property DEmi: WideString read Get_DEmi write Set_DEmi;
    property QVag: WideString read Get_QVag write Set_QVag;
    property PCalc: WideString read Get_PCalc write Set_PCalc;
    property VTar: WideString read Get_VTar write Set_VTar;
    property VFrete: WideString read Get_VFrete write Set_VFrete;
    property VSAcess: WideString read Get_VSAcess write Set_VSAcess;
    property VTServ: WideString read Get_VTServ write Set_VTServ;
    property IdTrem: WideString read Get_IdTrem write Set_IdTrem;
    property DetVagDCL: IXMLDetVagDCLList read Get_DetVagDCL;
  end;

{ IXMLDCLList }

  IXMLDCLList = interface(IXMLNodeCollection)
    ['{08DDB59F-6DA6-400E-BA72-4BCE1577E6F8}']
    { Methods & Properties }
    function Add: IXMLDCL;
    function Insert(const Index: Integer): IXMLDCL;
    function Get_Item(Index: Integer): IXMLDCL;
    property Items[Index: Integer]: IXMLDCL read Get_Item; default;
  end;

{ IXMLDetVagDCL }

  IXMLDetVagDCL = interface(IXMLNode)
    ['{6FCBBD53-1D35-4C18-87FC-66619E25DF48}']
    { Property Accessors }
    function Get_NVag: WideString;
    function Get_Cap: WideString;
    function Get_TpVag: WideString;
    function Get_PesoR: WideString;
    function Get_PesoBC: WideString;
    function Get_LacDetVagDCL: IXMLLacDetVagDCLList;
    function Get_ContDCL: IXMLContDCLList;
    procedure Set_NVag(Value: WideString);
    procedure Set_Cap(Value: WideString);
    procedure Set_TpVag(Value: WideString);
    procedure Set_PesoR(Value: WideString);
    procedure Set_PesoBC(Value: WideString);
    { Methods & Properties }
    property NVag: WideString read Get_NVag write Set_NVag;
    property Cap: WideString read Get_Cap write Set_Cap;
    property TpVag: WideString read Get_TpVag write Set_TpVag;
    property PesoR: WideString read Get_PesoR write Set_PesoR;
    property PesoBC: WideString read Get_PesoBC write Set_PesoBC;
    property LacDetVagDCL: IXMLLacDetVagDCLList read Get_LacDetVagDCL;
    property ContDCL: IXMLContDCLList read Get_ContDCL;
  end;

{ IXMLDetVagDCLList }

  IXMLDetVagDCLList = interface(IXMLNodeCollection)
    ['{F3AF5ABD-CD64-4FB6-AB7A-FC7D1DAD7F48}']
    { Methods & Properties }
    function Add: IXMLDetVagDCL;
    function Insert(const Index: Integer): IXMLDetVagDCL;
    function Get_Item(Index: Integer): IXMLDetVagDCL;
    property Items[Index: Integer]: IXMLDetVagDCL read Get_Item; default;
  end;

{ IXMLLacDetVagDCL }

  IXMLLacDetVagDCL = interface(IXMLNode)
    ['{2D31647F-B074-4E1B-974F-926AE5CB6A4D}']
    { Property Accessors }
    function Get_NLacre: WideString;
    procedure Set_NLacre(Value: WideString);
    { Methods & Properties }
    property NLacre: WideString read Get_NLacre write Set_NLacre;
  end;

{ IXMLLacDetVagDCLList }

  IXMLLacDetVagDCLList = interface(IXMLNodeCollection)
    ['{7B97737D-671C-400A-82EA-88D9BD39892E}']
    { Methods & Properties }
    function Add: IXMLLacDetVagDCL;
    function Insert(const Index: Integer): IXMLLacDetVagDCL;
    function Get_Item(Index: Integer): IXMLLacDetVagDCL;
    property Items[Index: Integer]: IXMLLacDetVagDCL read Get_Item; default;
  end;

{ IXMLContDCL }

  IXMLContDCL = interface(IXMLNode)
    ['{A75FF6C0-074E-4264-B456-0C5D568A0C1D}']
    { Property Accessors }
    function Get_NCont: WideString;
    function Get_DPrev: WideString;
    procedure Set_NCont(Value: WideString);
    procedure Set_DPrev(Value: WideString);
    { Methods & Properties }
    property NCont: WideString read Get_NCont write Set_NCont;
    property DPrev: WideString read Get_DPrev write Set_DPrev;
  end;

{ IXMLContDCLList }

  IXMLContDCLList = interface(IXMLNodeCollection)
    ['{1BF5A05E-B081-4DAF-AE2A-A0A57273D20D}']
    { Methods & Properties }
    function Add: IXMLContDCL;
    function Insert(const Index: Integer): IXMLContDCL;
    function Get_Item(Index: Integer): IXMLContDCL;
    property Items[Index: Integer]: IXMLContDCL read Get_Item; default;
  end;

{ IXMLDetVag }

  IXMLDetVag = interface(IXMLNode)
    ['{28A98B3C-2413-407D-928F-E296ECA2C07E}']
    { Property Accessors }
    function Get_NVag: WideString;
    function Get_Cap: WideString;
    function Get_TpVag: WideString;
    function Get_PesoR: WideString;
    function Get_PesoBC: WideString;
    function Get_LacDetVag: IXMLLacDetVagList;
    function Get_ContVag: IXMLContVagList;
    procedure Set_NVag(Value: WideString);
    procedure Set_Cap(Value: WideString);
    procedure Set_TpVag(Value: WideString);
    procedure Set_PesoR(Value: WideString);
    procedure Set_PesoBC(Value: WideString);
    { Methods & Properties }
    property NVag: WideString read Get_NVag write Set_NVag;
    property Cap: WideString read Get_Cap write Set_Cap;
    property TpVag: WideString read Get_TpVag write Set_TpVag;
    property PesoR: WideString read Get_PesoR write Set_PesoR;
    property PesoBC: WideString read Get_PesoBC write Set_PesoBC;
    property LacDetVag: IXMLLacDetVagList read Get_LacDetVag;
    property ContVag: IXMLContVagList read Get_ContVag;
  end;

{ IXMLDetVagList }

  IXMLDetVagList = interface(IXMLNodeCollection)
    ['{A8E1E2FD-FAEC-439D-986D-00C21C1FF139}']
    { Methods & Properties }
    function Add: IXMLDetVag;
    function Insert(const Index: Integer): IXMLDetVag;
    function Get_Item(Index: Integer): IXMLDetVag;
    property Items[Index: Integer]: IXMLDetVag read Get_Item; default;
  end;

{ IXMLLacDetVag }

  IXMLLacDetVag = interface(IXMLNode)
    ['{72DDC98F-2FEC-4886-895A-66EC08935988}']
    { Property Accessors }
    function Get_NLacre: WideString;
    procedure Set_NLacre(Value: WideString);
    { Methods & Properties }
    property NLacre: WideString read Get_NLacre write Set_NLacre;
  end;

{ IXMLLacDetVagList }

  IXMLLacDetVagList = interface(IXMLNodeCollection)
    ['{4033CA2F-C266-49B6-BDE6-78E982802BDC}']
    { Methods & Properties }
    function Add: IXMLLacDetVag;
    function Insert(const Index: Integer): IXMLLacDetVag;
    function Get_Item(Index: Integer): IXMLLacDetVag;
    property Items[Index: Integer]: IXMLLacDetVag read Get_Item; default;
  end;

{ IXMLContVag }

  IXMLContVag = interface(IXMLNode)
    ['{B3C9D33C-59D9-498B-8CD4-3FE3F1D77C8B}']
    { Property Accessors }
    function Get_NCont: WideString;
    function Get_DPrev: WideString;
    procedure Set_NCont(Value: WideString);
    procedure Set_DPrev(Value: WideString);
    { Methods & Properties }
    property NCont: WideString read Get_NCont write Set_NCont;
    property DPrev: WideString read Get_DPrev write Set_DPrev;
  end;

{ IXMLContVagList }

  IXMLContVagList = interface(IXMLNodeCollection)
    ['{A5DECE37-5099-4DA3-A876-25EB93AD1F75}']
    { Methods & Properties }
    function Add: IXMLContVag;
    function Insert(const Index: Integer): IXMLContVag;
    function Get_Item(Index: Integer): IXMLContVag;
    property Items[Index: Integer]: IXMLContVag read Get_Item; default;
  end;

{ IXMLDuto }

  IXMLDuto = interface(IXMLNode)
    ['{D01F6884-1A13-4B8B-9DFE-A36C6B6CD572}']
    { Property Accessors }
    function Get_VTar: WideString;
    procedure Set_VTar(Value: WideString);
    { Methods & Properties }
    property VTar: WideString read Get_VTar write Set_VTar;
  end;

{ IXMLPeri }

  IXMLPeri = interface(IXMLNode)
    ['{682FAD51-A742-4BE7-92C7-CAE4B8A40890}']
    { Property Accessors }
    function Get_NONU: WideString;
    function Get_XNomeAE: WideString;
    function Get_XClaRisco: WideString;
    function Get_GrEmb: WideString;
    function Get_QTotProd: WideString;
    function Get_QVolTipo: WideString;
    function Get_PontoFulgor: WideString;
    procedure Set_NONU(Value: WideString);
    procedure Set_XNomeAE(Value: WideString);
    procedure Set_XClaRisco(Value: WideString);
    procedure Set_GrEmb(Value: WideString);
    procedure Set_QTotProd(Value: WideString);
    procedure Set_QVolTipo(Value: WideString);
    procedure Set_PontoFulgor(Value: WideString);
    { Methods & Properties }
    property NONU: WideString read Get_NONU write Set_NONU;
    property XNomeAE: WideString read Get_XNomeAE write Set_XNomeAE;
    property XClaRisco: WideString read Get_XClaRisco write Set_XClaRisco;
    property GrEmb: WideString read Get_GrEmb write Set_GrEmb;
    property QTotProd: WideString read Get_QTotProd write Set_QTotProd;
    property QVolTipo: WideString read Get_QVolTipo write Set_QVolTipo;
    property PontoFulgor: WideString read Get_PontoFulgor write Set_PontoFulgor;
  end;

{ IXMLPeriList }

  IXMLPeriList = interface(IXMLNodeCollection)
    ['{44A235DE-148A-4F16-8227-09D3DE123F3A}']
    { Methods & Properties }
    function Add: IXMLPeri;
    function Insert(const Index: Integer): IXMLPeri;
    function Get_Item(Index: Integer): IXMLPeri;
    property Items[Index: Integer]: IXMLPeri read Get_Item; default;
  end;

{ IXMLVeicNovos }

  IXMLVeicNovos = interface(IXMLNode)
    ['{6F4F5CFE-CB7E-48DB-816B-F296AA248500}']
    { Property Accessors }
    function Get_Chassi: WideString;
    function Get_CCor: WideString;
    function Get_XCor: WideString;
    function Get_CMod: WideString;
    function Get_VUnit: WideString;
    function Get_VFrete: WideString;
    procedure Set_Chassi(Value: WideString);
    procedure Set_CCor(Value: WideString);
    procedure Set_XCor(Value: WideString);
    procedure Set_CMod(Value: WideString);
    procedure Set_VUnit(Value: WideString);
    procedure Set_VFrete(Value: WideString);
    { Methods & Properties }
    property Chassi: WideString read Get_Chassi write Set_Chassi;
    property CCor: WideString read Get_CCor write Set_CCor;
    property XCor: WideString read Get_XCor write Set_XCor;
    property CMod: WideString read Get_CMod write Set_CMod;
    property VUnit: WideString read Get_VUnit write Set_VUnit;
    property VFrete: WideString read Get_VFrete write Set_VFrete;
  end;

{ IXMLVeicNovosList }

  IXMLVeicNovosList = interface(IXMLNodeCollection)
    ['{F4590CB4-BFC5-4CA6-A049-245DA4FC5608}']
    { Methods & Properties }
    function Add: IXMLVeicNovos;
    function Insert(const Index: Integer): IXMLVeicNovos;
    function Get_Item(Index: Integer): IXMLVeicNovos;
    property Items[Index: Integer]: IXMLVeicNovos read Get_Item; default;
  end;

{ IXMLInfCteSub }

  IXMLInfCteSub = interface(IXMLNode)
    ['{8C613C35-C8B0-43A8-A86B-BDE1901011C5}']
    { Property Accessors }
    function Get_ChCte: WideString;
    function Get_TomaICMS: IXMLTomaICMS;
    function Get_TomaNaoICMS: IXMLTomaNaoICMS;
    procedure Set_ChCte(Value: WideString);
    { Methods & Properties }
    property ChCte: WideString read Get_ChCte write Set_ChCte;
    property TomaICMS: IXMLTomaICMS read Get_TomaICMS;
    property TomaNaoICMS: IXMLTomaNaoICMS read Get_TomaNaoICMS;
  end;

{ IXMLTomaICMS }

  IXMLTomaICMS = interface(IXMLNode)
    ['{D60B0E2A-EA9A-4D08-869B-2EE025296F8B}']
    { Property Accessors }
    function Get_RefNFe: WideString;
    function Get_RefNF: IXMLRefNF;
    function Get_RefCte: WideString;
    procedure Set_RefNFe(Value: WideString);
    procedure Set_RefCte(Value: WideString);
    { Methods & Properties }
    property RefNFe: WideString read Get_RefNFe write Set_RefNFe;
    property RefNF: IXMLRefNF read Get_RefNF;
    property RefCte: WideString read Get_RefCte write Set_RefCte;
  end;

{ IXMLRefNF }

  IXMLRefNF = interface(IXMLNode)
    ['{3A60178A-DD27-4390-8834-BF57A2CA16A5}']
    { Property Accessors }
    function Get_CNPJ: WideString;
    function Get_Mod_: WideString;
    function Get_Serie: WideString;
    function Get_Subserie: WideString;
    function Get_Nro: WideString;
    function Get_Valor: WideString;
    function Get_DEmi: WideString;
    procedure Set_CNPJ(Value: WideString);
    procedure Set_Mod_(Value: WideString);
    procedure Set_Serie(Value: WideString);
    procedure Set_Subserie(Value: WideString);
    procedure Set_Nro(Value: WideString);
    procedure Set_Valor(Value: WideString);
    procedure Set_DEmi(Value: WideString);
    { Methods & Properties }
    property CNPJ: WideString read Get_CNPJ write Set_CNPJ;
    property Mod_: WideString read Get_Mod_ write Set_Mod_;
    property Serie: WideString read Get_Serie write Set_Serie;
    property Subserie: WideString read Get_Subserie write Set_Subserie;
    property Nro: WideString read Get_Nro write Set_Nro;
    property Valor: WideString read Get_Valor write Set_Valor;
    property DEmi: WideString read Get_DEmi write Set_DEmi;
  end;

{ IXMLTomaNaoICMS }

  IXMLTomaNaoICMS = interface(IXMLNode)
    ['{6BB7313E-ABB7-43AA-9C6B-559721F69C81}']
    { Property Accessors }
    function Get_RefCteAnu: WideString;
    procedure Set_RefCteAnu(Value: WideString);
    { Methods & Properties }
    property RefCteAnu: WideString read Get_RefCteAnu write Set_RefCteAnu;
  end;

{ IXMLInfCteComp }

  IXMLInfCteComp = interface(IXMLNode)
    ['{952ABDE1-9CE9-44CB-9FDE-AB910DE89EAF}']
    { Property Accessors }
    function Get_Chave: WideString;
    function Get_VPresComp: IXMLVPresComp;
    function Get_ImpComp: IXMLImpComp;
    procedure Set_Chave(Value: WideString);
    { Methods & Properties }
    property Chave: WideString read Get_Chave write Set_Chave;
    property VPresComp: IXMLVPresComp read Get_VPresComp;
    property ImpComp: IXMLImpComp read Get_ImpComp;
  end;

{ IXMLVPresComp }

  IXMLVPresComp = interface(IXMLNode)
    ['{988E8FCC-8225-486E-8012-50428BA4D50A}']
    { Property Accessors }
    function Get_VTPrest: WideString;
    function Get_CompComp: IXMLCompCompList;
    procedure Set_VTPrest(Value: WideString);
    { Methods & Properties }
    property VTPrest: WideString read Get_VTPrest write Set_VTPrest;
    property CompComp: IXMLCompCompList read Get_CompComp;
  end;

{ IXMLCompComp }

  IXMLCompComp = interface(IXMLNode)
    ['{F48DC393-5172-4F5F-AC03-1E9B34F75895}']
    { Property Accessors }
    function Get_XNome: WideString;
    function Get_VComp: WideString;
    procedure Set_XNome(Value: WideString);
    procedure Set_VComp(Value: WideString);
    { Methods & Properties }
    property XNome: WideString read Get_XNome write Set_XNome;
    property VComp: WideString read Get_VComp write Set_VComp;
  end;

{ IXMLCompCompList }

  IXMLCompCompList = interface(IXMLNodeCollection)
    ['{491E0BB1-97BD-4CD9-B284-A9A9F9D55453}']
    { Methods & Properties }
    function Add: IXMLCompComp;
    function Insert(const Index: Integer): IXMLCompComp;
    function Get_Item(Index: Integer): IXMLCompComp;
    property Items[Index: Integer]: IXMLCompComp read Get_Item; default;
  end;

{ IXMLImpComp }

  IXMLImpComp = interface(IXMLNode)
    ['{E92E2CA1-D8CD-4A48-ADCD-6BEB9A47340D}']
    { Property Accessors }
    function Get_ICMSComp: IXMLTCST;
    function Get_InfAdFisco: WideString;
    procedure Set_InfAdFisco(Value: WideString);
    { Methods & Properties }
    property ICMSComp: IXMLTCST read Get_ICMSComp;
    property InfAdFisco: WideString read Get_InfAdFisco write Set_InfAdFisco;
  end;

{ IXMLInfCteAnu }

  IXMLInfCteAnu = interface(IXMLNode)
    ['{336A50BB-5B48-46F7-A402-A2F8CB4CA848}']
    { Property Accessors }
    function Get_ChCte: WideString;
    function Get_DEmi: WideString;
    procedure Set_ChCte(Value: WideString);
    procedure Set_DEmi(Value: WideString);
    { Methods & Properties }
    property ChCte: WideString read Get_ChCte write Set_ChCte;
    property DEmi: WideString read Get_DEmi write Set_DEmi;
  end;

{ IXMLSignatureType }

  IXMLSignatureType = interface(IXMLNode)
    ['{9EAA9508-30A7-4CD6-A6D5-FB30CC6ADEA0}']
    { Property Accessors }
    function Get_Id: WideString;
    function Get_SignedInfo: IXMLSignedInfoType;
    function Get_SignatureValue: IXMLSignatureValueType;
    function Get_KeyInfo: IXMLKeyInfoType;
    procedure Set_Id(Value: WideString);
    { Methods & Properties }
    property Id: WideString read Get_Id write Set_Id;
    property SignedInfo: IXMLSignedInfoType read Get_SignedInfo;
    property SignatureValue: IXMLSignatureValueType read Get_SignatureValue;
    property KeyInfo: IXMLKeyInfoType read Get_KeyInfo;
  end;

{ IXMLSignedInfoType }

  IXMLSignedInfoType = interface(IXMLNode)
    ['{38A8FB2C-157C-442A-9C70-014F99C47FD4}']
    { Property Accessors }
    function Get_Id: WideString;
    function Get_CanonicalizationMethod: IXMLCanonicalizationMethod;
    function Get_SignatureMethod: IXMLSignatureMethod;
    function Get_Reference: IXMLReferenceType;
    procedure Set_Id(Value: WideString);
    { Methods & Properties }
    property Id: WideString read Get_Id write Set_Id;
    property CanonicalizationMethod: IXMLCanonicalizationMethod read Get_CanonicalizationMethod;
    property SignatureMethod: IXMLSignatureMethod read Get_SignatureMethod;
    property Reference: IXMLReferenceType read Get_Reference;
  end;

{ IXMLCanonicalizationMethod }

  IXMLCanonicalizationMethod = interface(IXMLNode)
    ['{778E9991-F790-4067-963A-5B2E6F4D052B}']
    { Property Accessors }
    function Get_Algorithm: WideString;
    procedure Set_Algorithm(Value: WideString);
    { Methods & Properties }
    property Algorithm: WideString read Get_Algorithm write Set_Algorithm;
  end;

{ IXMLSignatureMethod }

  IXMLSignatureMethod = interface(IXMLNode)
    ['{8DA401D9-F565-4FD8-9F08-72D8D312ACA2}']
    { Property Accessors }
    function Get_Algorithm: WideString;
    procedure Set_Algorithm(Value: WideString);
    { Methods & Properties }
    property Algorithm: WideString read Get_Algorithm write Set_Algorithm;
  end;

{ IXMLReferenceType }

  IXMLReferenceType = interface(IXMLNode)
    ['{A30419E6-017F-499E-B67C-53EC50154998}']
    { Property Accessors }
    function Get_Id: WideString;
    function Get_URI: WideString;
    function Get_Type_: WideString;
    function Get_Transforms: IXMLTransformsType;
    function Get_DigestMethod: IXMLDigestMethod;
    function Get_DigestValue: WideString;
    procedure Set_Id(Value: WideString);
    procedure Set_URI(Value: WideString);
    procedure Set_Type_(Value: WideString);
    procedure Set_DigestValue(Value: WideString);
    { Methods & Properties }
    property Id: WideString read Get_Id write Set_Id;
    property URI: WideString read Get_URI write Set_URI;
    property Type_: WideString read Get_Type_ write Set_Type_;
    property Transforms: IXMLTransformsType read Get_Transforms;
    property DigestMethod: IXMLDigestMethod read Get_DigestMethod;
    property DigestValue: WideString read Get_DigestValue write Set_DigestValue;
  end;

{ IXMLTransformsType }

  IXMLTransformsType = interface(IXMLNodeCollection)
    ['{CA495896-BD4E-4C7A-A62F-F6BC131C0019}']
    { Property Accessors }
    function Get_Transform(Index: Integer): IXMLTransformType;
    { Methods & Properties }
    function Add: IXMLTransformType;
    function Insert(const Index: Integer): IXMLTransformType;
    property Transform[Index: Integer]: IXMLTransformType read Get_Transform; default;
  end;

{ IXMLTransformType }

  IXMLTransformType = interface(IXMLNodeCollection)
    ['{5EF83C38-754C-4691-916C-011B745407EC}']
    { Property Accessors }
    function Get_Algorithm: WideString;
    function Get_XPath(Index: Integer): WideString;
    procedure Set_Algorithm(Value: WideString);
    { Methods & Properties }
    function Add(const XPath: WideString): IXMLNode;
    function Insert(const Index: Integer; const XPath: WideString): IXMLNode;
    property Algorithm: WideString read Get_Algorithm write Set_Algorithm;
    property XPath[Index: Integer]: WideString read Get_XPath; default;
  end;

{ IXMLDigestMethod }

  IXMLDigestMethod = interface(IXMLNode)
    ['{B35F4645-0D42-47C6-9F74-D448F8785556}']
    { Property Accessors }
    function Get_Algorithm: WideString;
    procedure Set_Algorithm(Value: WideString);
    { Methods & Properties }
    property Algorithm: WideString read Get_Algorithm write Set_Algorithm;
  end;

{ IXMLSignatureValueType }

  IXMLSignatureValueType = interface(IXMLNode)
    ['{B50D770C-AEEB-458B-90D0-D50C1609F744}']
    { Property Accessors }
    function Get_Id: WideString;
    procedure Set_Id(Value: WideString);
    { Methods & Properties }
    property Id: WideString read Get_Id write Set_Id;
  end;

{ IXMLKeyInfoType }

  IXMLKeyInfoType = interface(IXMLNode)
    ['{47AB7650-9A09-4C3B-8855-B07139760A44}']
    { Property Accessors }
    function Get_Id: WideString;
    function Get_X509Data: IXMLX509DataType;
    procedure Set_Id(Value: WideString);
    { Methods & Properties }
    property Id: WideString read Get_Id write Set_Id;
    property X509Data: IXMLX509DataType read Get_X509Data;
  end;

{ IXMLX509DataType }

  IXMLX509DataType = interface(IXMLNode)
    ['{0C3CC8D9-1742-4BB2-A821-2E1C057FE6E6}']
    { Property Accessors }
    function Get_X509Certificate: WideString;
    procedure Set_X509Certificate(Value: WideString);
    { Methods & Properties }
    property X509Certificate: WideString read Get_X509Certificate write Set_X509Certificate;
  end;

{ Forward Decls }

  TXMLTCTe = class;
  TXMLInfCte = class;
  TXMLIde = class;
  TXMLToma03 = class;
  TXMLToma4 = class;
  TXMLTEndereco = class;
  TXMLCompl = class;
  TXMLFluxo = class;
  TXMLPass = class;
  TXMLPassList = class;
  TXMLEntrega = class;
  TXMLSemData = class;
  TXMLComData = class;
  TXMLNoPeriodo = class;
  TXMLSemHora = class;
  TXMLComHora = class;
  TXMLNoInter = class;
  TXMLObsCont = class;
  TXMLObsFisco = class;
  TXMLEmit = class;
  TXMLTEndeEmi = class;
  TXMLRem = class;
  TXMLInfNF = class;
  TXMLInfNFList = class;
  TXMLTEndReEnt = class;
  TXMLInfNFe = class;
  TXMLInfNFeList = class;
  TXMLInfOutros = class;
  TXMLInfOutrosList = class;
  TXMLExped = class;
  TXMLReceb = class;
  TXMLDest = class;
  TXMLVPrest = class;
  TXMLComp = class;
  TXMLCompList = class;
  TXMLImp = class;
  TXMLTCST = class;
  TXMLCST00 = class;
  TXMLCST20 = class;
  TXMLCST45 = class;
  TXMLCST80 = class;
  TXMLCST81 = class;
  TXMLCST90 = class;
  TXMLInfCTeNorm = class;
  TXMLInfCarga = class;
  TXMLInfQ = class;
  TXMLInfQList = class;
  TXMLContQt = class;
  TXMLContQtList = class;
  TXMLLacContQt = class;
  TXMLLacContQtList = class;
  TXMLDocAnt = class;
  TXMLEmiDocAnt = class;
  TXMLIdDocAnt = class;
  TXMLIdDocAntList = class;
  TXMLIdDocAntPap = class;
  TXMLIdDocAntPapList = class;
  TXMLIdDocAntEle = class;
  TXMLIdDocAntEleList = class;
  TXMLSeg = class;
  TXMLSegList = class;
  TXMLRodo = class;
  TXMLCTRB = class;
  TXMLOcc = class;
  TXMLEmiOcc = class;
  TXMLValePed = class;
  TXMLDisp = class;
  TXMLDispList = class;
  TXMLVeic = class;
  TXMLVeicList = class;
  TXMLProp = class;
  TXMLLacRodo = class;
  TXMLLacRodoList = class;
  TXMLMoto = class;
  TXMLMotoList = class;
  TXMLAereo = class;
  TXMLTarifa = class;
  TXMLAquav = class;
  TXMLLacre = class;
  TXMLLacreList = class;
  TXMLFerrov = class;
  TXMLFerroSub = class;
  TXMLTEnderFer = class;
  TXMLDCL = class;
  TXMLDCLList = class;
  TXMLDetVagDCL = class;
  TXMLDetVagDCLList = class;
  TXMLLacDetVagDCL = class;
  TXMLLacDetVagDCLList = class;
  TXMLContDCL = class;
  TXMLContDCLList = class;
  TXMLDetVag = class;
  TXMLDetVagList = class;
  TXMLLacDetVag = class;
  TXMLLacDetVagList = class;
  TXMLContVag = class;
  TXMLContVagList = class;
  TXMLDuto = class;
  TXMLPeri = class;
  TXMLPeriList = class;
  TXMLVeicNovos = class;
  TXMLVeicNovosList = class;
  TXMLInfCteSub = class;
  TXMLTomaICMS = class;
  TXMLRefNF = class;
  TXMLTomaNaoICMS = class;
  TXMLInfCteComp = class;
  TXMLVPresComp = class;
  TXMLCompComp = class;
  TXMLCompCompList = class;
  TXMLImpComp = class;
  TXMLInfCteAnu = class;
  TXMLSignatureType = class;
  TXMLSignedInfoType = class;
  TXMLCanonicalizationMethod = class;
  TXMLSignatureMethod = class;
  TXMLReferenceType = class;
  TXMLTransformsType = class;
  TXMLTransformType = class;
  TXMLDigestMethod = class;
  TXMLSignatureValueType = class;
  TXMLKeyInfoType = class;
  TXMLX509DataType = class;

{ TXMLTCTe }

  TXMLTCTe = class(TXMLNode, IXMLTCTe)
  protected
    { IXMLTCTe }
    function Get_InfCte: IXMLInfCte;
    function Get_Signature: IXMLSignatureType;
  public
    procedure AfterConstruction; override;
  end;

{ TXMLInfCte }

  TXMLInfCte = class(TXMLNode, IXMLInfCte)
  protected
    { IXMLInfCte }
    function Get_Versao: WideString;
    function Get_Id: WideString;
    function Get_Ide: IXMLIde;
    function Get_Compl: IXMLCompl;
    function Get_Emit: IXMLEmit;
    function Get_Rem: IXMLRem;
    function Get_Exped: IXMLExped;
    function Get_Receb: IXMLReceb;
    function Get_Dest: IXMLDest;
    function Get_VPrest: IXMLVPrest;
    function Get_Imp: IXMLImp;
    function Get_InfCTeNorm: IXMLInfCTeNorm;
    function Get_InfCteComp: IXMLInfCteComp;
    function Get_InfCteAnu: IXMLInfCteAnu;
    procedure Set_Versao(Value: WideString);
    procedure Set_Id(Value: WideString);
  public
    procedure AfterConstruction; override;
  end;

{ TXMLIde }

  TXMLIde = class(TXMLNode, IXMLIde)
  protected
    { IXMLIde }
    function Get_CUF: WideString;
    function Get_CCT: WideString;
    function Get_CFOP: WideString;
    function Get_NatOp: WideString;
    function Get_ForPag: WideString;
    function Get_Mod_: WideString;
    function Get_Serie: WideString;
    function Get_NCT: WideString;
    function Get_DhEmi: WideString;
    function Get_TpImp: WideString;
    function Get_TpEmis: WideString;
    function Get_CDV: WideString;
    function Get_TpAmb: WideString;
    function Get_TpCTe: WideString;
    function Get_ProcEmi: WideString;
    function Get_VerProc: WideString;
    function Get_RefCTE: WideString;

    function Get_cMunEmi: WideString;//1.03
    function Get_xMunEmi: WideString;//1.03
    function Get_UFEmi: WideString;  //1.03

    function Get_cMunEnv: WideString;//1.04
    function Get_xMunEnv: WideString;//1.04
    function Get_UFEnv: WideString;  //1.04

    function Get_Modal: WideString;
    function Get_TpServ: WideString;
    function Get_CMunIni: WideString;
    function Get_XMunIni: WideString;
    function Get_UFIni: WideString;
    function Get_CMunFim: WideString;
    function Get_XMunFim: WideString;
    function Get_UFFim: WideString;
    function Get_Retira: WideString;
    function Get_XDetRetira: WideString;
    function Get_Toma03: IXMLToma03;
    function Get_Toma4: IXMLToma4;

    function Get_dhCont: WideString;//1.04
    function Get_xJust: WideString; //1.04

    procedure Set_CUF(Value: WideString);
    procedure Set_CCT(Value: WideString);
    procedure Set_CFOP(Value: WideString);
    procedure Set_NatOp(Value: WideString);
    procedure Set_ForPag(Value: WideString);
    procedure Set_Mod_(Value: WideString);
    procedure Set_Serie(Value: WideString);
    procedure Set_NCT(Value: WideString);
    procedure Set_DhEmi(Value: WideString);
    procedure Set_TpImp(Value: WideString);
    procedure Set_TpEmis(Value: WideString);
    procedure Set_CDV(Value: WideString);
    procedure Set_TpAmb(Value: WideString);
    procedure Set_TpCTe(Value: WideString);
    procedure Set_ProcEmi(Value: WideString);
    procedure Set_VerProc(Value: WideString);
    procedure Set_RefCTE(Value: WideString);

    procedure Set_cMunEmi(Value: WideString); //1.03
    procedure Set_xMunEmi(Value: WideString); //1.03
    procedure Set_UFEmi(Value: WideString);   //1.03

    procedure Set_cMunEnv(Value: WideString); //1.04
    procedure Set_xMunEnv(Value: WideString); //1.04
    procedure Set_UFEnv(Value: WideString);   //1.04

    procedure Set_Modal(Value: WideString);
    procedure Set_TpServ(Value: WideString);
    procedure Set_CMunIni(Value: WideString);
    procedure Set_XMunIni(Value: WideString);
    procedure Set_UFIni(Value: WideString);
    procedure Set_CMunFim(Value: WideString);
    procedure Set_XMunFim(Value: WideString);
    procedure Set_UFFim(Value: WideString);
    procedure Set_Retira(Value: WideString);
    procedure Set_XDetRetira(Value: WideString);

    procedure Set_dhCont(Value: WideString);//1.04
    procedure Set_xJust(Value: WideString); //1.04
  public
    procedure AfterConstruction; override;
  end;

{ TXMLToma03 }

  TXMLToma03 = class(TXMLNode, IXMLToma03)
  protected
    { IXMLToma03 }
    function Get_Toma: WideString;
    procedure Set_Toma(Value: WideString);
  end;

{ TXMLToma4 }

  TXMLToma4 = class(TXMLNode, IXMLToma4)
  protected
    { IXMLToma4 }
    function Get_Toma: WideString;
    function Get_CNPJ: WideString;
    function Get_CPF: WideString;
    function Get_IE: WideString;
    function Get_XNome: WideString;
    function Get_XFant: WideString;
    function Get_Fone: WideString;
    function Get_EnderToma: IXMLTEndereco;
    procedure Set_Toma(Value: WideString);
    procedure Set_CNPJ(Value: WideString);
    procedure Set_CPF(Value: WideString);
    procedure Set_IE(Value: WideString);
    procedure Set_XNome(Value: WideString);
    procedure Set_XFant(Value: WideString);
    procedure Set_Fone(Value: WideString);
  public
    procedure AfterConstruction; override;
  end;

{ TXMLTEndereco }

  TXMLTEndereco = class(TXMLNode, IXMLTEndereco)
  protected
    { IXMLTEndereco }
    function Get_XLgr: WideString;
    function Get_Nro: WideString;
    function Get_XCpl: WideString;
    function Get_XBairro: WideString;
    function Get_CMun: WideString;
    function Get_XMun: WideString;
    function Get_CEP: WideString;
    function Get_UF: WideString;
    function Get_CPais: WideString;
    function Get_XPais: WideString;
    procedure Set_XLgr(Value: WideString);
    procedure Set_Nro(Value: WideString);
    procedure Set_XCpl(Value: WideString);
    procedure Set_XBairro(Value: WideString);
    procedure Set_CMun(Value: WideString);
    procedure Set_XMun(Value: WideString);
    procedure Set_CEP(Value: WideString);
    procedure Set_UF(Value: WideString);
    procedure Set_CPais(Value: WideString);
    procedure Set_XPais(Value: WideString);
  end;

{ TXMLCompl }

  TXMLCompl = class(TXMLNode, IXMLCompl)
  protected
    { IXMLCompl }
    function Get_XCaracAd: WideString;
    function Get_XCaracSer: WideString;
    function Get_XEmi: WideString;
    function Get_Fluxo: IXMLFluxo;
    function Get_Entrega: IXMLEntrega;
    function Get_OrigCalc: WideString;
    function Get_DestCalc: WideString;
    function Get_XObs: WideString;
    function Get_ObsCont: IXMLObsCont;
    function Get_ObsFisco: IXMLObsFisco;
    procedure Set_XCaracAd(Value: WideString);
    procedure Set_XCaracSer(Value: WideString);
    procedure Set_XEmi(Value: WideString);
    procedure Set_OrigCalc(Value: WideString);
    procedure Set_DestCalc(Value: WideString);
    procedure Set_XObs(Value: WideString);
  public
    procedure AfterConstruction; override;
  end;

{ TXMLFluxo }

  TXMLFluxo = class(TXMLNode, IXMLFluxo)
  private
    FPass: IXMLPassList;
  protected
    { IXMLFluxo }
    function Get_XOrig: WideString;
    function Get_Pass: IXMLPassList;
    function Get_XDest: WideString;
    function Get_XRota: WideString;
    procedure Set_XOrig(Value: WideString);
    procedure Set_XDest(Value: WideString);
    procedure Set_XRota(Value: WideString);
  public
    procedure AfterConstruction; override;
  end;

{ TXMLPass }

  TXMLPass = class(TXMLNode, IXMLPass)
  protected
    { IXMLPass }
    function Get_XPass: WideString;
    procedure Set_XPass(Value: WideString);
  end;

{ TXMLPassList }

  TXMLPassList = class(TXMLNodeCollection, IXMLPassList)
  protected
    { IXMLPassList }
    function Add: IXMLPass;
    function Insert(const Index: Integer): IXMLPass;
    function Get_Item(Index: Integer): IXMLPass;
  end;

{ TXMLEntrega }

  TXMLEntrega = class(TXMLNode, IXMLEntrega)
  protected
    { IXMLEntrega }
    function Get_SemData: IXMLSemData;
    function Get_ComData: IXMLComData;
    function Get_NoPeriodo: IXMLNoPeriodo;
    function Get_SemHora: IXMLSemHora;
    function Get_ComHora: IXMLComHora;
    function Get_NoInter: IXMLNoInter;
  public
    procedure AfterConstruction; override;
  end;

{ TXMLSemData }

  TXMLSemData = class(TXMLNode, IXMLSemData)
  protected
    { IXMLSemData }
    function Get_TpPer: WideString;
    procedure Set_TpPer(Value: WideString);
  end;

{ TXMLComData }

  TXMLComData = class(TXMLNode, IXMLComData)
  protected
    { IXMLComData }
    function Get_TpPer: WideString;
    function Get_DProg: WideString;
    procedure Set_TpPer(Value: WideString);
    procedure Set_DProg(Value: WideString);
  end;

{ TXMLNoPeriodo }

  TXMLNoPeriodo = class(TXMLNode, IXMLNoPeriodo)
  protected
    { IXMLNoPeriodo }
    function Get_TpPer: WideString;
    function Get_DIni: WideString;
    function Get_DFim: WideString;
    procedure Set_TpPer(Value: WideString);
    procedure Set_DIni(Value: WideString);
    procedure Set_DFim(Value: WideString);
  end;

{ TXMLSemHora }

  TXMLSemHora = class(TXMLNode, IXMLSemHora)
  protected
    { IXMLSemHora }
    function Get_TpHor: WideString;
    procedure Set_TpHor(Value: WideString);
  end;

{ TXMLComHora }

  TXMLComHora = class(TXMLNode, IXMLComHora)
  protected
    { IXMLComHora }
    function Get_TpHor: WideString;
    function Get_HProg: WideString;
    procedure Set_TpHor(Value: WideString);
    procedure Set_HProg(Value: WideString);
  end;

{ TXMLNoInter }

  TXMLNoInter = class(TXMLNode, IXMLNoInter)
  protected
    { IXMLNoInter }
    function Get_Tphor: WideString;
    function Get_HIni: WideString;
    function Get_HFim: WideString;
    procedure Set_Tphor(Value: WideString);
    procedure Set_HIni(Value: WideString);
    procedure Set_HFim(Value: WideString);
  end;

{ TXMLObsCont }

  TXMLObsCont = class(TXMLNode, IXMLObsCont)
  protected
    { IXMLObsCont }
    function Get_XCampo: WideString;
    function Get_XTexto: WideString;
    procedure Set_XCampo(Value: WideString);
    procedure Set_XTexto(Value: WideString);
  end;

{ TXMLObsFisco }

  TXMLObsFisco = class(TXMLNode, IXMLObsFisco)
  protected
    { IXMLObsFisco }
    function Get_XCampo: WideString;
    function Get_XTexto: WideString;
    procedure Set_XCampo(Value: WideString);
    procedure Set_XTexto(Value: WideString);
  end;

{ TXMLEmit }

  TXMLEmit = class(TXMLNode, IXMLEmit)
  protected
    { IXMLEmit }
    function Get_CNPJ: WideString;
    function Get_IE: WideString;
    function Get_XNome: WideString;
    function Get_XFant: WideString;
    function Get_EnderEmit: IXMLTEndeEmi;
    procedure Set_CNPJ(Value: WideString);
    procedure Set_IE(Value: WideString);
    procedure Set_XNome(Value: WideString);
    procedure Set_XFant(Value: WideString);
  public
    procedure AfterConstruction; override;
  end;

{ TXMLTEndeEmi }

  TXMLTEndeEmi = class(TXMLNode, IXMLTEndeEmi)
  protected
    { IXMLTEndeEmi }
    function Get_XLgr: WideString;
    function Get_Nro: WideString;
    function Get_XCpl: WideString;
    function Get_XBairro: WideString;
    function Get_CMun: WideString;
    function Get_XMun: WideString;
    function Get_CEP: WideString;
    function Get_UF: WideString;
    function Get_CPais: WideString;
    function Get_XPais: WideString;
    function Get_Fone: WideString;
    procedure Set_XLgr(Value: WideString);
    procedure Set_Nro(Value: WideString);
    procedure Set_XCpl(Value: WideString);
    procedure Set_XBairro(Value: WideString);
    procedure Set_CMun(Value: WideString);
    procedure Set_XMun(Value: WideString);
    procedure Set_CEP(Value: WideString);
    procedure Set_UF(Value: WideString);
    procedure Set_CPais(Value: WideString);
    procedure Set_XPais(Value: WideString);
    procedure Set_Fone(Value: WideString);
  end;

{ TXMLRem }

  TXMLRem = class(TXMLNode, IXMLRem)
  private
    FInfNF: IXMLInfNFList;
    FInfNFe: IXMLInfNFeList;
    FInfOutros: IXMLInfOutrosList;
  protected
    { IXMLRem }
    function Get_CNPJ: WideString;
    function Get_CPF: WideString;
    function Get_IE: WideString;
    function Get_XNome: WideString;
    function Get_XFant: WideString;
    function Get_Fone: WideString;
    function Get_EnderReme: IXMLTEndereco;
    function Get_InfNF: IXMLInfNFList;
    function Get_InfNFe: IXMLInfNFeList;
    function Get_InfOutros: IXMLInfOutrosList;
    procedure Set_CNPJ(Value: WideString);
    procedure Set_CPF(Value: WideString);
    procedure Set_IE(Value: WideString);
    procedure Set_XNome(Value: WideString);
    procedure Set_XFant(Value: WideString);
    procedure Set_Fone(Value: WideString);
  public
    procedure AfterConstruction; override;
  end;

{ TXMLInfNF }

  TXMLInfNF = class(TXMLNode, IXMLInfNF)
  protected
    { IXMLInfNF }
    function Get_NRoma: WideString;
    function Get_NPed: WideString;
    function Get_Serie: WideString;
    function Get_NDoc: WideString;
    function Get_DEmi: WideString;
    function Get_vBC: WideString;
    function Get_vICMS: WideString;
    function Get_vBCST: WideString;
    function Get_VST: WideString;
    function Get_VProd: WideString;
    function Get_VNF: WideString;
    function Get_NCFOP: WideString;
    function Get_NPeso: WideString;
    function Get_PIN: WideString;
    function Get_LocRet: IXMLTEndReEnt;
    procedure Set_NRoma(Value: WideString);
    procedure Set_NPed(Value: WideString);
    procedure Set_Serie(Value: WideString);
    procedure Set_NDoc(Value: WideString);
    procedure Set_DEmi(Value: WideString);
    procedure Set_vBC(Value: WideString);
    procedure Set_vICMS(Value: WideString);
    procedure Set_vBCST(Value: WideString);
    procedure Set_VST(Value: WideString);
    procedure Set_VProd(Value: WideString);
    procedure Set_VNF(Value: WideString);
    procedure Set_NCFOP(Value: WideString);
    procedure Set_NPeso(Value: WideString);
    procedure Set_PIN(Value: WideString);
  public
    procedure AfterConstruction; override;
  end;

{ TXMLInfNFList }

  TXMLInfNFList = class(TXMLNodeCollection, IXMLInfNFList)
  protected
    { IXMLInfNFList }
    function Add: IXMLInfNF;
    function Insert(const Index: Integer): IXMLInfNF;
    function Get_Item(Index: Integer): IXMLInfNF;
  end;

{ TXMLTEndReEnt }

  TXMLTEndReEnt = class(TXMLNode, IXMLTEndReEnt)
  protected
    { IXMLTEndReEnt }
    function Get_CNPJ: WideString;
    function Get_CPF: WideString;
    function Get_XNome: WideString;
    function Get_XLgr: WideString;
    function Get_Nro: WideString;
    function Get_XCpl: WideString;
    function Get_XBairro: WideString;
    function Get_CMun: WideString;
    function Get_XMun: WideString;
    function Get_UF: WideString;
    procedure Set_CNPJ(Value: WideString);
    procedure Set_CPF(Value: WideString);
    procedure Set_XNome(Value: WideString);
    procedure Set_XLgr(Value: WideString);
    procedure Set_Nro(Value: WideString);
    procedure Set_XCpl(Value: WideString);
    procedure Set_XBairro(Value: WideString);
    procedure Set_CMun(Value: WideString);
    procedure Set_XMun(Value: WideString);
    procedure Set_UF(Value: WideString);
  end;

{ TXMLInfNFe }

  TXMLInfNFe = class(TXMLNode, IXMLInfNFe)
  protected
    { IXMLInfNFe }
    function Get_Chave: WideString;
    function Get_PIN: WideString;
    procedure Set_Chave(Value: WideString);
    procedure Set_PIN(Value: WideString);
  end;

{ TXMLInfNFeList }

  TXMLInfNFeList = class(TXMLNodeCollection, IXMLInfNFeList)
  protected
    { IXMLInfNFeList }
    function Add: IXMLInfNFe;
    function Insert(const Index: Integer): IXMLInfNFe;
    function Get_Item(Index: Integer): IXMLInfNFe;
  end;

{ TXMLInfOutros }

  TXMLInfOutros = class(TXMLNode, IXMLInfOutros)
  protected
    { IXMLInfOutros }
    function Get_TpDoc: WideString;
    function Get_DescOutros: WideString;
    function Get_NDoc: WideString;
    function Get_DEmi: WideString;
    function Get_VDocFisc: WideString;
    procedure Set_TpDoc(Value: WideString);
    procedure Set_DescOutros(Value: WideString);
    procedure Set_NDoc(Value: WideString);
    procedure Set_DEmi(Value: WideString);
    procedure Set_VDocFisc(Value: WideString);
  end;

{ TXMLInfOutrosList }

  TXMLInfOutrosList = class(TXMLNodeCollection, IXMLInfOutrosList)
  protected
    { IXMLInfOutrosList }
    function Add: IXMLInfOutros;
    function Insert(const Index: Integer): IXMLInfOutros;
    function Get_Item(Index: Integer): IXMLInfOutros;
  end;

{ TXMLExped }

  TXMLExped = class(TXMLNode, IXMLExped)
  protected
    { IXMLExped }
    function Get_CNPJ: WideString;
    function Get_CPF: WideString;
    function Get_IE: WideString;
    function Get_XNome: WideString;
    function Get_Fone: WideString;
    function Get_EnderExped: IXMLTEndereco;
    procedure Set_CNPJ(Value: WideString);
    procedure Set_CPF(Value: WideString);
    procedure Set_IE(Value: WideString);
    procedure Set_XNome(Value: WideString);
    procedure Set_Fone(Value: WideString);
  public
    procedure AfterConstruction; override;
  end;

{ TXMLReceb }

  TXMLReceb = class(TXMLNode, IXMLReceb)
  protected
    { IXMLReceb }
    function Get_CNPJ: WideString;
    function Get_CPF: WideString;
    function Get_IE: WideString;
    function Get_XNome: WideString;
    function Get_Fone: WideString;
    function Get_EnderReceb: IXMLTEndereco;
    procedure Set_CNPJ(Value: WideString);
    procedure Set_CPF(Value: WideString);
    procedure Set_IE(Value: WideString);
    procedure Set_XNome(Value: WideString);
    procedure Set_Fone(Value: WideString);
  public
    procedure AfterConstruction; override;
  end;

{ TXMLDest }

  TXMLDest = class(TXMLNode, IXMLDest)
  protected
    { IXMLDest }
    function Get_CNPJ: WideString;
    function Get_CPF: WideString;
    function Get_IE: WideString;
    function Get_XNome: WideString;
    function Get_Fone: WideString;
    function Get_ISUF: WideString;
    function Get_EnderDest: IXMLTEndereco;
    function Get_LocEnt: IXMLTEndReEnt;
    procedure Set_CNPJ(Value: WideString);
    procedure Set_CPF(Value: WideString);
    procedure Set_IE(Value: WideString);
    procedure Set_XNome(Value: WideString);
    procedure Set_Fone(Value: WideString);
    procedure Set_ISUF(Value: WideString);
  public
    procedure AfterConstruction; override;
  end;

{ TXMLVPrest }

  TXMLVPrest = class(TXMLNode, IXMLVPrest)
  private
    FComp: IXMLCompList;
  protected
    { IXMLVPrest }
    function Get_VTPrest: WideString;
    function Get_VRec: WideString;
    function Get_Comp: IXMLCompList;
    procedure Set_VTPrest(Value: WideString);
    procedure Set_VRec(Value: WideString);
  public
    procedure AfterConstruction; override;
  end;

{ TXMLComp }

  TXMLComp = class(TXMLNode, IXMLComp)
  protected
    { IXMLComp }
    function Get_XNome: WideString;
    function Get_VComp: WideString;
    procedure Set_XNome(Value: WideString);
    procedure Set_VComp(Value: WideString);
  end;

{ TXMLCompList }

  TXMLCompList = class(TXMLNodeCollection, IXMLCompList)
  protected
    { IXMLCompList }
    function Add: IXMLComp;
    function Insert(const Index: Integer): IXMLComp;
    function Get_Item(Index: Integer): IXMLComp;
  end;

{ TXMLImp }

  TXMLImp = class(TXMLNode, IXMLImp)
  protected
    { IXMLImp }
    function Get_ICMS: IXMLTCST;
    function Get_InfAdFisco: WideString;
    procedure Set_InfAdFisco(Value: WideString);
  public
    procedure AfterConstruction; override;
  end;

{ TXMLTCST }

  TXMLTCST = class(TXMLNode, IXMLTCST)
  protected
    { IXMLTCST }
    function Get_CST00: IXMLCST00;
    function Get_CST20: IXMLCST20;
    function Get_CST45: IXMLCST45;
    function Get_CST80: IXMLCST80;
    function Get_CST81: IXMLCST81;
    function Get_CST90: IXMLCST90;

    function Get_ICMS00 :      IXMLICMS00; //1.04
    function Get_ICMS20 :      IXMLICMS20; //1.04
    function Get_ICMS45 :      IXMLICMS45; //1.04
    function Get_ICMS60 :      IXMLICMS60; //1.04
    function Get_ICMS90 :      IXMLICMS90; //1.04
    function Get_ICMSOutraUF : IXMLICMSOutraUF; //1.04
    function Get_ICMSSN :      IXMLICMSSN; //1.04
  public
    procedure AfterConstruction; override;
  end;

{ TXMLCST00 }

  TXMLCST00 = class(TXMLNode, IXMLCST00)
  protected
    { IXMLCST00 }
    function Get_CST: WideString;
    function Get_vBC: WideString;
    function Get_pICMS: WideString;
    function Get_vICMS: WideString;
    procedure Set_CST(Value: WideString);
    procedure Set_vBC(Value: WideString);
    procedure Set_pICMS(Value: WideString);
    procedure Set_vICMS(Value: WideString);
  end;

{ TXMLCST20 }

  TXMLCST20 = class(TXMLNode, IXMLCST20)
  protected
    { IXMLCST20 }
    function Get_CST: WideString;
    function Get_PRedBC: WideString;
    function Get_vBC: WideString;
    function Get_pICMS: WideString;
    function Get_vICMS: WideString;
    procedure Set_CST(Value: WideString);
    procedure Set_PRedBC(Value: WideString);
    procedure Set_vBC(Value: WideString);
    procedure Set_pICMS(Value: WideString);
    procedure Set_vICMS(Value: WideString);
  end;

{ TXMLCST45 }

  TXMLCST45 = class(TXMLNode, IXMLCST45)
  protected
    { IXMLCST45 }
    function Get_CST: WideString;
    procedure Set_CST(Value: WideString);
  end;

{ TXMLCST80 }

  TXMLCST80 = class(TXMLNode, IXMLCST80)
  protected
    { IXMLCST80 }
    function Get_CST: WideString;
    function Get_vBC: WideString;
    function Get_pICMS: WideString;
    function Get_vICMS: WideString;
    function Get_VCred: WideString;
    procedure Set_CST(Value: WideString);
    procedure Set_vBC(Value: WideString);
    procedure Set_pICMS(Value: WideString);
    procedure Set_vICMS(Value: WideString);
    procedure Set_VCred(Value: WideString);
  end;

{ TXMLCST81 }

  TXMLCST81 = class(TXMLNode, IXMLCST81)
  protected
    { IXMLCST81 }
    function Get_CST: WideString;
    function Get_PRedBC: WideString;
    function Get_vBC: WideString;
    function Get_pICMS: WideString;
    function Get_vICMS: WideString;
    procedure Set_CST(Value: WideString);
    procedure Set_PRedBC(Value: WideString);
    procedure Set_vBC(Value: WideString);
    procedure Set_pICMS(Value: WideString);
    procedure Set_vICMS(Value: WideString);
  end;

{ TXMLCST90 }

  TXMLCST90 = class(TXMLNode, IXMLCST90)
  protected
    { IXMLCST90 }
    function Get_CST: WideString;
    function Get_PRedBC: WideString;
    function Get_vBC: WideString;
    function Get_pICMS: WideString;
    function Get_vICMS: WideString;
    function Get_VCred: WideString;
    procedure Set_CST(Value: WideString);
    procedure Set_PRedBC(Value: WideString);
    procedure Set_vBC(Value: WideString);
    procedure Set_pICMS(Value: WideString);
    procedure Set_vICMS(Value: WideString);
    procedure Set_VCred(Value: WideString);
  end;

{ TXMLICMS00 }

  TXMLICMS00 = Class(TXMLNode, IXMLICMS00)
   { IXMLICMS00 }
    function  Get_CST: WideString;
    function  Get_vBC: WideString;
    function  Get_pICMS: WideString;
    function  Get_vICMS: WideString;
    procedure Set_CST(Value: WideString);
    procedure Set_vBC(Value: WideString);
    procedure Set_pICMS(Value: WideString);
    procedure Set_vICMS(Value: WideString);
  end;

{ TXMLICMS20 }

  TXMLICMS20 = Class(TXMLNode, IXMLICMS20)
    { IXMLICMS20 }
    function  Get_CST: WideString;
    function  Get_PRedBC: WideString;
    function  Get_vBC: WideString;
    function  Get_pICMS: WideString;
    function  Get_vICMS: WideString;
    procedure Set_CST(Value: WideString);
    procedure Set_PRedBC(Value: WideString);
    procedure Set_vBC(Value: WideString);
    procedure Set_pICMS(Value: WideString);
    procedure Set_vICMS(Value: WideString);
  end;

{ TXMLICMS45 }

  TXMLICMS45 = Class(TXMLNode, IXMLICMS45)
    { IXMLICMS45 }
    function  Get_CST: WideString;
    procedure Set_CST(Value: WideString);
  end;

{ TXMLICMS60 }

  TXMLICMS60 = Class(TXMLNode, IXMLICMS60)
    { IXMLICMS60 }
    function  Get_CST: WideString;
    function  Get_vBCSTRet: WideString;
    function  Get_vICMSSTRet: WideString;
    function  Get_pICMSSTRet: WideString;
    function  Get_vCred: WideString;
    procedure Set_CST(Value: WideString);
    procedure Set_vBCSTRet(Value: WideString);
    procedure Set_vICMSSTRet(Value: WideString);
    procedure Set_pICMSSTRet(Value: WideString);
    procedure Set_vCred(Value: WideString);
  end;

{ TXMLICMS90 }

  TXMLICMS90 = Class(TXMLNode, IXMLICMS90)
    { IXMLCST90 }
    function  Get_CST   : WideString;
    function  Get_PRedBC: WideString;
    function  Get_vBC   : WideString;
    function  Get_pICMS : WideString;
    function  Get_vICMS : WideString;
    function  Get_VCred : WideString;
    procedure Set_CST(Value   : WideString);
    procedure Set_PRedBC(Value: WideString);
    procedure Set_vBC(Value   : WideString);
    procedure Set_pICMS(Value : WideString);
    procedure Set_vICMS(Value : WideString);
    procedure Set_VCred(Value : WideString);
  end;

{ TXMLICMSOutraUF }

  TXMLICMSOutraUF = Class(TXMLNode, IXMLICMSOutraUF)
    { IXMLICMSOutraUF }
    function Get_CST   : WideString;
    function Get_PRedBC: WideString;
    function Get_vBC   : WideString;
    function Get_pICMS : WideString;
    function Get_vICMS : WideString;
    function Get_VCred : WideString;
    procedure Set_CST(Value   : WideString);
    procedure Set_PRedBC(Value: WideString);
    procedure Set_vBC(Value   : WideString);
    procedure Set_pICMS(Value : WideString);
    procedure Set_vICMS(Value : WideString);
    procedure Set_VCred(Value : WideString);
  end;

{ IXMLICMSSN }

  TXMLICMSSN = Class(TXMLNode, IXMLICMSSN)
    { IXMLICMSSN }
    function  Get_indSN: WideString;
    function  Get_infAdFisco: WideString;
    procedure Set_indSN(Value: WideString);
    procedure Set_infAdFisco(Value: WideString);
  end;

{ TXMLInfCTeNorm }

  TXMLInfCTeNorm = class(TXMLNode, IXMLInfCTeNorm)
  private
    FContQt: IXMLContQtList;
    FSeg: IXMLSegList;
    FPeri: IXMLPeriList;
    FVeicNovos: IXMLVeicNovosList;
  protected
    { IXMLInfCTeNorm }
    function Get_InfCarga: IXMLInfCarga;
    function Get_ContQt: IXMLContQtList;
    function Get_DocAnt: IXMLDocAnt;
    function Get_Seg: IXMLSegList;
    function Get_Rodo: IXMLRodo;
    function Get_infModal: IXMLinfModal;
    function Get_Aereo: IXMLAereo;
    function Get_Aquav: IXMLAquav;
    function Get_Ferrov: IXMLFerrov;
    function Get_Duto: IXMLDuto;
    function Get_Peri: IXMLPeriList;
    function Get_VeicNovos: IXMLVeicNovosList;
    function Get_InfCteSub: IXMLInfCteSub;
  public
    procedure AfterConstruction; override;
  end;

{ TXMLInfCarga }

  TXMLInfCarga = class(TXMLNode, IXMLInfCarga)
  private
    FInfQ: IXMLInfQList;
  protected
    { IXMLInfCarga }
    function Get_VMerc: WideString;
    function Get_vCarga: WideString;
    function Get_ProPred: WideString;
    function Get_XOutCat: WideString;
    function Get_InfQ: IXMLInfQList;
    procedure Set_VMerc(Value: WideString);
    procedure Set_vCarga(Value: WideString);
    procedure Set_ProPred(Value: WideString);
    procedure Set_XOutCat(Value: WideString);
  public
    procedure AfterConstruction; override;
  end;

{ TXMLInfQ }

  TXMLInfQ = class(TXMLNode, IXMLInfQ)
  protected
    { IXMLInfQ }
    function Get_CUnid: WideString;
    function Get_TpMed: WideString;
    function Get_QCarga: WideString;
    procedure Set_CUnid(Value: WideString);
    procedure Set_TpMed(Value: WideString);
    procedure Set_QCarga(Value: WideString);
  end;

{ TXMLInfQList }

  TXMLInfQList = class(TXMLNodeCollection, IXMLInfQList)
  protected
    { IXMLInfQList }
    function Add: IXMLInfQ;
    function Insert(const Index: Integer): IXMLInfQ;
    function Get_Item(Index: Integer): IXMLInfQ;
  end;

{ TXMLContQt }

  TXMLContQt = class(TXMLNode, IXMLContQt)
  private
    FLacContQt: IXMLLacContQtList;
  protected
    { IXMLContQt }
    function Get_NCont: WideString;
    function Get_LacContQt: IXMLLacContQtList;
    function Get_DPrev: WideString;
    procedure Set_NCont(Value: WideString);
    procedure Set_DPrev(Value: WideString);
  public
    procedure AfterConstruction; override;
  end;

{ TXMLContQtList }

  TXMLContQtList = class(TXMLNodeCollection, IXMLContQtList)
  protected
    { IXMLContQtList }
    function Add: IXMLContQt;
    function Insert(const Index: Integer): IXMLContQt;
    function Get_Item(Index: Integer): IXMLContQt;
  end;

{ TXMLLacContQt }

  TXMLLacContQt = class(TXMLNode, IXMLLacContQt)
  protected
    { IXMLLacContQt }
    function Get_NLacre: WideString;
    procedure Set_NLacre(Value: WideString);
  end;

{ TXMLLacContQtList }

  TXMLLacContQtList = class(TXMLNodeCollection, IXMLLacContQtList)
  protected
    { IXMLLacContQtList }
    function Add: IXMLLacContQt;
    function Insert(const Index: Integer): IXMLLacContQt;
    function Get_Item(Index: Integer): IXMLLacContQt;
  end;

{ TXMLDocAnt }

  TXMLDocAnt = class(TXMLNodeCollection, IXMLDocAnt)
  protected
    { IXMLDocAnt }
    function Get_EmiDocAnt(Index: Integer): IXMLEmiDocAnt;
    function Add: IXMLEmiDocAnt;
    function Insert(const Index: Integer): IXMLEmiDocAnt;
  public
    procedure AfterConstruction; override;
  end;

{ TXMLEmiDocAnt }

  TXMLEmiDocAnt = class(TXMLNode, IXMLEmiDocAnt)
  private
    FIdDocAnt: IXMLIdDocAntList;
  protected
    { IXMLEmiDocAnt }
    function Get_CNPJ: WideString;
    function Get_CPF: WideString;
    function Get_IE: WideString;
    function Get_UF: WideString;
    function Get_XNome: WideString;
    function Get_IdDocAnt: IXMLIdDocAntList;
    procedure Set_CNPJ(Value: WideString);
    procedure Set_CPF(Value: WideString);
    procedure Set_IE(Value: WideString);
    procedure Set_UF(Value: WideString);
    procedure Set_XNome(Value: WideString);
  public
    procedure AfterConstruction; override;
  end;

{ TXMLIdDocAnt }

  TXMLIdDocAnt = class(TXMLNode, IXMLIdDocAnt)
  private
    FIdDocAntPap: IXMLIdDocAntPapList;
    FIdDocAntEle: IXMLIdDocAntEleList;
  protected
    { IXMLIdDocAnt }
    function Get_IdDocAntPap: IXMLIdDocAntPapList;
    function Get_IdDocAntEle: IXMLIdDocAntEleList;
  public
    procedure AfterConstruction; override;
  end;

{ TXMLIdDocAntList }

  TXMLIdDocAntList = class(TXMLNodeCollection, IXMLIdDocAntList)
  protected
    { IXMLIdDocAntList }
    function Add: IXMLIdDocAnt;
    function Insert(const Index: Integer): IXMLIdDocAnt;
    function Get_Item(Index: Integer): IXMLIdDocAnt;
  end;

{ TXMLIdDocAntPap }

  TXMLIdDocAntPap = class(TXMLNode, IXMLIdDocAntPap)
  protected
    { IXMLIdDocAntPap }
    function Get_TpDoc: WideString;
    function Get_Serie: WideString;
    function Get_Subser: WideString;
    function Get_NDoc: WideString;
    function Get_DEmi: WideString;
    procedure Set_TpDoc(Value: WideString);
    procedure Set_Serie(Value: WideString);
    procedure Set_Subser(Value: WideString);
    procedure Set_NDoc(Value: WideString);
    procedure Set_DEmi(Value: WideString);
  end;

{ TXMLIdDocAntPapList }

  TXMLIdDocAntPapList = class(TXMLNodeCollection, IXMLIdDocAntPapList)
  protected
    { IXMLIdDocAntPapList }
    function Add: IXMLIdDocAntPap;
    function Insert(const Index: Integer): IXMLIdDocAntPap;
    function Get_Item(Index: Integer): IXMLIdDocAntPap;
  end;

{ TXMLIdDocAntEle }

  TXMLIdDocAntEle = class(TXMLNode, IXMLIdDocAntEle)
  protected
    { IXMLIdDocAntEle }
    function Get_Chave: WideString;
    procedure Set_Chave(Value: WideString);
  end;

{ TXMLIdDocAntEleList }

  TXMLIdDocAntEleList = class(TXMLNodeCollection, IXMLIdDocAntEleList)
  protected
    { IXMLIdDocAntEleList }
    function Add: IXMLIdDocAntEle;
    function Insert(const Index: Integer): IXMLIdDocAntEle;
    function Get_Item(Index: Integer): IXMLIdDocAntEle;
  end;

{ TXMLSeg }

  TXMLSeg = class(TXMLNode, IXMLSeg)
  protected
    { IXMLSeg }
    function Get_RespSeg: WideString;
    function Get_XSeg: WideString;
    function Get_NApol: WideString;
    function Get_NAver: WideString;
    function Get_VMerc: WideString;
    function Get_vCarga: WideString;
    procedure Set_RespSeg(Value: WideString);
    procedure Set_XSeg(Value: WideString);
    procedure Set_NApol(Value: WideString);
    procedure Set_NAver(Value: WideString);
    procedure Set_VMerc(Value: WideString);
    procedure Set_vCarga(Value: WideString);
  end;

{ TXMLSegList }

  TXMLSegList = class(TXMLNodeCollection, IXMLSegList)
  protected
    { IXMLSegList }
    function Add: IXMLSeg;
    function Insert(const Index: Integer): IXMLSeg;
    function Get_Item(Index: Integer): IXMLSeg;
  end;

{ infModal }
  TXMLinfModal = Class(TXMLNode, IXMLinfModal)
    { Property Accessors }
    function Get_Rodo : IXMLRodo;
    property Rodo: IXMLRodo read Get_Rodo;
  public
    procedure AfterConstruction; override;
  end;

{ TXMLRodo }

  TXMLRodo = class(TXMLNode, IXMLRodo)
  private
    FVeic: IXMLVeicList;
    FLacRodo: IXMLLacRodoList;
    FMoto: IXMLMotoList;
  protected
    { IXMLRodo }
    function Get_RNTRC: WideString;
    function Get_DPrev: WideString;
    function Get_Lota: WideString;
    function Get_CTRB: IXMLCTRB;
    function Get_Occ: IXMLOcc;
    function Get_ValePed: IXMLValePed;
    function Get_Veic: IXMLVeicList;
    function Get_LacRodo: IXMLLacRodoList;
    function Get_Moto: IXMLMotoList;
    procedure Set_RNTRC(Value: WideString);
    procedure Set_DPrev(Value: WideString);
    procedure Set_Lota(Value: WideString);
  public
    procedure AfterConstruction; override;
  end;

{ TXMLCTRB }

  TXMLCTRB = class(TXMLNode, IXMLCTRB)
  protected
    { IXMLCTRB }
    function Get_Serie: WideString;
    function Get_NCTRB: WideString;
    procedure Set_Serie(Value: WideString);
    procedure Set_NCTRB(Value: WideString);
  end;

{ TXMLOcc }

  TXMLOcc = class(TXMLNode, IXMLOcc)
  protected
    { IXMLOcc }
    function Get_Serie: WideString;
    function Get_NOcc: WideString;
    function Get_DEmi: WideString;
    function Get_EmiOcc: IXMLEmiOcc;
    procedure Set_Serie(Value: WideString);
    procedure Set_NOcc(Value: WideString);
    procedure Set_DEmi(Value: WideString);
  public
    procedure AfterConstruction; override;
  end;

{ TXMLEmiOcc }

  TXMLEmiOcc = class(TXMLNode, IXMLEmiOcc)
  protected
    { IXMLEmiOcc }
    function Get_CNPJ: WideString;
    function Get_CInt: WideString;
    function Get_IE: WideString;
    function Get_UF: WideString;
    function Get_Fone: WideString;
    procedure Set_CNPJ(Value: WideString);
    procedure Set_CInt(Value: WideString);
    procedure Set_IE(Value: WideString);
    procedure Set_UF(Value: WideString);
    procedure Set_Fone(Value: WideString);
  end;

{ TXMLValePed }

  TXMLValePed = class(TXMLNode, IXMLValePed)
  private
    FDisp: IXMLDispList;
  protected
    { IXMLValePed }
    function Get_NroRE: WideString;
    function Get_VTValePed: WideString;
    function Get_RespPg: WideString;
    function Get_Disp: IXMLDispList;
    procedure Set_NroRE(Value: WideString);
    procedure Set_VTValePed(Value: WideString);
    procedure Set_RespPg(Value: WideString);
  public
    procedure AfterConstruction; override;
  end;

{ TXMLDisp }

  TXMLDisp = class(TXMLNode, IXMLDisp)
  protected
    { IXMLDisp }
    function Get_TpDisp: WideString;
    function Get_XEmp: WideString;
    function Get_DVig: WideString;
    function Get_NDisp: WideString;
    function Get_NCompC: WideString;
    procedure Set_TpDisp(Value: WideString);
    procedure Set_XEmp(Value: WideString);
    procedure Set_DVig(Value: WideString);
    procedure Set_NDisp(Value: WideString);
    procedure Set_NCompC(Value: WideString);
  end;

{ TXMLDispList }

  TXMLDispList = class(TXMLNodeCollection, IXMLDispList)
  protected
    { IXMLDispList }
    function Add: IXMLDisp;
    function Insert(const Index: Integer): IXMLDisp;
    function Get_Item(Index: Integer): IXMLDisp;
  end;

{ TXMLVeic }

  TXMLVeic = class(TXMLNode, IXMLVeic)
  protected
    { IXMLVeic }
    function Get_CInt: WideString;
    function Get_RENAVAM: WideString;
    function Get_Placa: WideString;
    function Get_Tara: WideString;
    function Get_CapKG: WideString;
    function Get_CapM3: WideString;
    function Get_TpProp: WideString;
    function Get_TpVeic: WideString;
    function Get_TpRod: WideString;
    function Get_TpCar: WideString;
    function Get_UF: WideString;
    function Get_Prop: IXMLProp;
    procedure Set_CInt(Value: WideString);
    procedure Set_RENAVAM(Value: WideString);
    procedure Set_Placa(Value: WideString);
    procedure Set_Tara(Value: WideString);
    procedure Set_CapKG(Value: WideString);
    procedure Set_CapM3(Value: WideString);
    procedure Set_TpProp(Value: WideString);
    procedure Set_TpVeic(Value: WideString);
    procedure Set_TpRod(Value: WideString);
    procedure Set_TpCar(Value: WideString);
    procedure Set_UF(Value: WideString);
  public
    procedure AfterConstruction; override;
  end;

{ TXMLVeicList }

  TXMLVeicList = class(TXMLNodeCollection, IXMLVeicList)
  protected
    { IXMLVeicList }
    function Add: IXMLVeic;
    function Insert(const Index: Integer): IXMLVeic;
    function Get_Item(Index: Integer): IXMLVeic;
  end;

{ TXMLProp }

  TXMLProp = class(TXMLNode, IXMLProp)
  protected
    { IXMLProp }
    function Get_CPF: WideString;
    function Get_CNPJ: WideString;
    function Get_RNTRC: WideString;
    function Get_XNome: WideString;
    function Get_IE: WideString;
    function Get_UF: WideString;
    function Get_TpProp: WideString;
    procedure Set_CPF(Value: WideString);
    procedure Set_CNPJ(Value: WideString);
    procedure Set_RNTRC(Value: WideString);
    procedure Set_XNome(Value: WideString);
    procedure Set_IE(Value: WideString);
    procedure Set_UF(Value: WideString);
    procedure Set_TpProp(Value: WideString);
  end;

{ TXMLLacRodo }

  TXMLLacRodo = class(TXMLNode, IXMLLacRodo)
  protected
    { IXMLLacRodo }
    function Get_NLacre: WideString;
    procedure Set_NLacre(Value: WideString);
  end;

{ TXMLLacRodoList }

  TXMLLacRodoList = class(TXMLNodeCollection, IXMLLacRodoList)
  protected
    { IXMLLacRodoList }
    function Add: IXMLLacRodo;
    function Insert(const Index: Integer): IXMLLacRodo;
    function Get_Item(Index: Integer): IXMLLacRodo;
  end;

{ TXMLMoto }

  TXMLMoto = class(TXMLNode, IXMLMoto)
  protected
    { IXMLMoto }
    function Get_XNome: WideString;
    function Get_CPF: WideString;
    procedure Set_XNome(Value: WideString);
    procedure Set_CPF(Value: WideString);
  end;

{ TXMLMotoList }

  TXMLMotoList = class(TXMLNodeCollection, IXMLMotoList)
  protected
    { IXMLMotoList }
    function Add: IXMLMoto;
    function Insert(const Index: Integer): IXMLMoto;
    function Get_Item(Index: Integer): IXMLMoto;
  end;

{ TXMLAereo }

  TXMLAereo = class(TXMLNode, IXMLAereo)
  protected
    { IXMLAereo }
    function Get_NMinu: WideString;
    function Get_NOCA: WideString;
    function Get_DPrev: WideString;
    function Get_XLAgEmi: WideString;
    function Get_CIATA: WideString;
    function Get_Tarifa: IXMLTarifa;
    procedure Set_NMinu(Value: WideString);
    procedure Set_NOCA(Value: WideString);
    procedure Set_DPrev(Value: WideString);
    procedure Set_XLAgEmi(Value: WideString);
    procedure Set_CIATA(Value: WideString);
  public
    procedure AfterConstruction; override;
  end;

{ TXMLTarifa }

  TXMLTarifa = class(TXMLNode, IXMLTarifa)
  protected
    { IXMLTarifa }
    function Get_Trecho: WideString;
    function Get_CL: WideString;
    function Get_CTar: WideString;
    function Get_VTar: WideString;
    procedure Set_Trecho(Value: WideString);
    procedure Set_CL(Value: WideString);
    procedure Set_CTar(Value: WideString);
    procedure Set_VTar(Value: WideString);
  end;

{ TXMLAquav }

  TXMLAquav = class(TXMLNode, IXMLAquav)
  private
    FLacre: IXMLLacreList;
  protected
    { IXMLAquav }
    function Get_VPrest: WideString;
    function Get_VAFRMM: WideString;
    function Get_NBooking: WideString;
    function Get_NCtrl: WideString;
    function Get_XNavio: WideString;
    function Get_NViag: WideString;
    function Get_Direc: WideString;
    function Get_PrtEmb: WideString;
    function Get_PrtTrans: WideString;
    function Get_PrtDest: WideString;
    function Get_TpNav: WideString;
    function Get_Irin: WideString;
    function Get_Lacre: IXMLLacreList;
    procedure Set_VPrest(Value: WideString);
    procedure Set_VAFRMM(Value: WideString);
    procedure Set_NBooking(Value: WideString);
    procedure Set_NCtrl(Value: WideString);
    procedure Set_XNavio(Value: WideString);
    procedure Set_NViag(Value: WideString);
    procedure Set_Direc(Value: WideString);
    procedure Set_PrtEmb(Value: WideString);
    procedure Set_PrtTrans(Value: WideString);
    procedure Set_PrtDest(Value: WideString);
    procedure Set_TpNav(Value: WideString);
    procedure Set_Irin(Value: WideString);
  public
    procedure AfterConstruction; override;
  end;

{ TXMLLacre }

  TXMLLacre = class(TXMLNode, IXMLLacre)
  protected
    { IXMLLacre }
    function Get_NLacre: WideString;
    procedure Set_NLacre(Value: WideString);
  end;

{ TXMLLacreList }

  TXMLLacreList = class(TXMLNodeCollection, IXMLLacreList)
  protected
    { IXMLLacreList }
    function Add: IXMLLacre;
    function Insert(const Index: Integer): IXMLLacre;
    function Get_Item(Index: Integer): IXMLLacre;
  end;

{ TXMLFerrov }

  TXMLFerrov = class(TXMLNode, IXMLFerrov)
  private
    FDCL: IXMLDCLList;
    FDetVag: IXMLDetVagList;
  protected
    { IXMLFerrov }
    function Get_TpTraf: WideString;
    function Get_Fluxo: WideString;
    function Get_IdTrem: WideString;
    function Get_VFrete: WideString;
    function Get_FerroSub: IXMLFerroSub;
    function Get_DCL: IXMLDCLList;
    function Get_DetVag: IXMLDetVagList;
    procedure Set_TpTraf(Value: WideString);
    procedure Set_Fluxo(Value: WideString);
    procedure Set_IdTrem(Value: WideString);
    procedure Set_VFrete(Value: WideString);
  public
    procedure AfterConstruction; override;
  end;

{ TXMLFerroSub }

  TXMLFerroSub = class(TXMLNode, IXMLFerroSub)
  protected
    { IXMLFerroSub }
    function Get_CNPJ: WideString;
    function Get_CInt: WideString;
    function Get_IE: WideString;
    function Get_XNome: WideString;
    function Get_EnderFerro: IXMLTEnderFer;
    procedure Set_CNPJ(Value: WideString);
    procedure Set_CInt(Value: WideString);
    procedure Set_IE(Value: WideString);
    procedure Set_XNome(Value: WideString);
  public
    procedure AfterConstruction; override;
  end;

{ TXMLTEnderFer }

  TXMLTEnderFer = class(TXMLNode, IXMLTEnderFer)
  protected
    { IXMLTEnderFer }
    function Get_XLgr: WideString;
    function Get_Nro: WideString;
    function Get_XCpl: WideString;
    function Get_XBairro: WideString;
    function Get_CMun: WideString;
    function Get_XMun: WideString;
    function Get_CEP: WideString;
    function Get_UF: WideString;
    procedure Set_XLgr(Value: WideString);
    procedure Set_Nro(Value: WideString);
    procedure Set_XCpl(Value: WideString);
    procedure Set_XBairro(Value: WideString);
    procedure Set_CMun(Value: WideString);
    procedure Set_XMun(Value: WideString);
    procedure Set_CEP(Value: WideString);
    procedure Set_UF(Value: WideString);
  end;

{ TXMLDCL }

  TXMLDCL = class(TXMLNode, IXMLDCL)
  private
    FDetVagDCL: IXMLDetVagDCLList;
  protected
    { IXMLDCL }
    function Get_Serie: WideString;
    function Get_NDCL: WideString;
    function Get_DEmi: WideString;
    function Get_QVag: WideString;
    function Get_PCalc: WideString;
    function Get_VTar: WideString;
    function Get_VFrete: WideString;
    function Get_VSAcess: WideString;
    function Get_VTServ: WideString;
    function Get_IdTrem: WideString;
    function Get_DetVagDCL: IXMLDetVagDCLList;
    procedure Set_Serie(Value: WideString);
    procedure Set_NDCL(Value: WideString);
    procedure Set_DEmi(Value: WideString);
    procedure Set_QVag(Value: WideString);
    procedure Set_PCalc(Value: WideString);
    procedure Set_VTar(Value: WideString);
    procedure Set_VFrete(Value: WideString);
    procedure Set_VSAcess(Value: WideString);
    procedure Set_VTServ(Value: WideString);
    procedure Set_IdTrem(Value: WideString);
  public
    procedure AfterConstruction; override;
  end;

{ TXMLDCLList }

  TXMLDCLList = class(TXMLNodeCollection, IXMLDCLList)
  protected
    { IXMLDCLList }
    function Add: IXMLDCL;
    function Insert(const Index: Integer): IXMLDCL;
    function Get_Item(Index: Integer): IXMLDCL;
  end;

{ TXMLDetVagDCL }

  TXMLDetVagDCL = class(TXMLNode, IXMLDetVagDCL)
  private
    FLacDetVagDCL: IXMLLacDetVagDCLList;
    FContDCL: IXMLContDCLList;
  protected
    { IXMLDetVagDCL }
    function Get_NVag: WideString;
    function Get_Cap: WideString;
    function Get_TpVag: WideString;
    function Get_PesoR: WideString;
    function Get_PesoBC: WideString;
    function Get_LacDetVagDCL: IXMLLacDetVagDCLList;
    function Get_ContDCL: IXMLContDCLList;
    procedure Set_NVag(Value: WideString);
    procedure Set_Cap(Value: WideString);
    procedure Set_TpVag(Value: WideString);
    procedure Set_PesoR(Value: WideString);
    procedure Set_PesoBC(Value: WideString);
  public
    procedure AfterConstruction; override;
  end;

{ TXMLDetVagDCLList }

  TXMLDetVagDCLList = class(TXMLNodeCollection, IXMLDetVagDCLList)
  protected
    { IXMLDetVagDCLList }
    function Add: IXMLDetVagDCL;
    function Insert(const Index: Integer): IXMLDetVagDCL;
    function Get_Item(Index: Integer): IXMLDetVagDCL;
  end;

{ TXMLLacDetVagDCL }

  TXMLLacDetVagDCL = class(TXMLNode, IXMLLacDetVagDCL)
  protected
    { IXMLLacDetVagDCL }
    function Get_NLacre: WideString;
    procedure Set_NLacre(Value: WideString);
  end;

{ TXMLLacDetVagDCLList }

  TXMLLacDetVagDCLList = class(TXMLNodeCollection, IXMLLacDetVagDCLList)
  protected
    { IXMLLacDetVagDCLList }
    function Add: IXMLLacDetVagDCL;
    function Insert(const Index: Integer): IXMLLacDetVagDCL;
    function Get_Item(Index: Integer): IXMLLacDetVagDCL;
  end;

{ TXMLContDCL }

  TXMLContDCL = class(TXMLNode, IXMLContDCL)
  protected
    { IXMLContDCL }
    function Get_NCont: WideString;
    function Get_DPrev: WideString;
    procedure Set_NCont(Value: WideString);
    procedure Set_DPrev(Value: WideString);
  end;

{ TXMLContDCLList }

  TXMLContDCLList = class(TXMLNodeCollection, IXMLContDCLList)
  protected
    { IXMLContDCLList }
    function Add: IXMLContDCL;
    function Insert(const Index: Integer): IXMLContDCL;
    function Get_Item(Index: Integer): IXMLContDCL;
  end;

{ TXMLDetVag }

  TXMLDetVag = class(TXMLNode, IXMLDetVag)
  private
    FLacDetVag: IXMLLacDetVagList;
    FContVag: IXMLContVagList;
  protected
    { IXMLDetVag }
    function Get_NVag: WideString;
    function Get_Cap: WideString;
    function Get_TpVag: WideString;
    function Get_PesoR: WideString;
    function Get_PesoBC: WideString;
    function Get_LacDetVag: IXMLLacDetVagList;
    function Get_ContVag: IXMLContVagList;
    procedure Set_NVag(Value: WideString);
    procedure Set_Cap(Value: WideString);
    procedure Set_TpVag(Value: WideString);
    procedure Set_PesoR(Value: WideString);
    procedure Set_PesoBC(Value: WideString);
  public
    procedure AfterConstruction; override;
  end;

{ TXMLDetVagList }

  TXMLDetVagList = class(TXMLNodeCollection, IXMLDetVagList)
  protected
    { IXMLDetVagList }
    function Add: IXMLDetVag;
    function Insert(const Index: Integer): IXMLDetVag;
    function Get_Item(Index: Integer): IXMLDetVag;
  end;

{ TXMLLacDetVag }

  TXMLLacDetVag = class(TXMLNode, IXMLLacDetVag)
  protected
    { IXMLLacDetVag }
    function Get_NLacre: WideString;
    procedure Set_NLacre(Value: WideString);
  end;

{ TXMLLacDetVagList }

  TXMLLacDetVagList = class(TXMLNodeCollection, IXMLLacDetVagList)
  protected
    { IXMLLacDetVagList }
    function Add: IXMLLacDetVag;
    function Insert(const Index: Integer): IXMLLacDetVag;
    function Get_Item(Index: Integer): IXMLLacDetVag;
  end;

{ TXMLContVag }

  TXMLContVag = class(TXMLNode, IXMLContVag)
  protected
    { IXMLContVag }
    function Get_NCont: WideString;
    function Get_DPrev: WideString;
    procedure Set_NCont(Value: WideString);
    procedure Set_DPrev(Value: WideString);
  end;

{ TXMLContVagList }

  TXMLContVagList = class(TXMLNodeCollection, IXMLContVagList)
  protected
    { IXMLContVagList }
    function Add: IXMLContVag;
    function Insert(const Index: Integer): IXMLContVag;
    function Get_Item(Index: Integer): IXMLContVag;
  end;

{ TXMLDuto }

  TXMLDuto = class(TXMLNode, IXMLDuto)
  protected
    { IXMLDuto }
    function Get_VTar: WideString;
    procedure Set_VTar(Value: WideString);
  end;

{ TXMLPeri }

  TXMLPeri = class(TXMLNode, IXMLPeri)
  protected
    { IXMLPeri }
    function Get_NONU: WideString;
    function Get_XNomeAE: WideString;
    function Get_XClaRisco: WideString;
    function Get_GrEmb: WideString;
    function Get_QTotProd: WideString;
    function Get_QVolTipo: WideString;
    function Get_PontoFulgor: WideString;
    procedure Set_NONU(Value: WideString);
    procedure Set_XNomeAE(Value: WideString);
    procedure Set_XClaRisco(Value: WideString);
    procedure Set_GrEmb(Value: WideString);
    procedure Set_QTotProd(Value: WideString);
    procedure Set_QVolTipo(Value: WideString);
    procedure Set_PontoFulgor(Value: WideString);
  end;

{ TXMLPeriList }

  TXMLPeriList = class(TXMLNodeCollection, IXMLPeriList)
  protected
    { IXMLPeriList }
    function Add: IXMLPeri;
    function Insert(const Index: Integer): IXMLPeri;
    function Get_Item(Index: Integer): IXMLPeri;
  end;

{ TXMLVeicNovos }

  TXMLVeicNovos = class(TXMLNode, IXMLVeicNovos)
  protected
    { IXMLVeicNovos }
    function Get_Chassi: WideString;
    function Get_CCor: WideString;
    function Get_XCor: WideString;
    function Get_CMod: WideString;
    function Get_VUnit: WideString;
    function Get_VFrete: WideString;
    procedure Set_Chassi(Value: WideString);
    procedure Set_CCor(Value: WideString);
    procedure Set_XCor(Value: WideString);
    procedure Set_CMod(Value: WideString);
    procedure Set_VUnit(Value: WideString);
    procedure Set_VFrete(Value: WideString);
  end;

{ TXMLVeicNovosList }

  TXMLVeicNovosList = class(TXMLNodeCollection, IXMLVeicNovosList)
  protected
    { IXMLVeicNovosList }
    function Add: IXMLVeicNovos;
    function Insert(const Index: Integer): IXMLVeicNovos;
    function Get_Item(Index: Integer): IXMLVeicNovos;
  end;

{ TXMLInfCteSub }

  TXMLInfCteSub = class(TXMLNode, IXMLInfCteSub)
  protected
    { IXMLInfCteSub }
    function Get_ChCte: WideString;
    function Get_TomaICMS: IXMLTomaICMS;
    function Get_TomaNaoICMS: IXMLTomaNaoICMS;
    procedure Set_ChCte(Value: WideString);
  public
    procedure AfterConstruction; override;
  end;

{ TXMLTomaICMS }

  TXMLTomaICMS = class(TXMLNode, IXMLTomaICMS)
  protected
    { IXMLTomaICMS }
    function Get_RefNFe: WideString;
    function Get_RefNF: IXMLRefNF;
    function Get_RefCte: WideString;
    procedure Set_RefNFe(Value: WideString);
    procedure Set_RefCte(Value: WideString);
  public
    procedure AfterConstruction; override;
  end;

{ TXMLRefNF }

  TXMLRefNF = class(TXMLNode, IXMLRefNF)
  protected
    { IXMLRefNF }
    function Get_CNPJ: WideString;
    function Get_Mod_: WideString;
    function Get_Serie: WideString;
    function Get_Subserie: WideString;
    function Get_Nro: WideString;
    function Get_Valor: WideString;
    function Get_DEmi: WideString;
    procedure Set_CNPJ(Value: WideString);
    procedure Set_Mod_(Value: WideString);
    procedure Set_Serie(Value: WideString);
    procedure Set_Subserie(Value: WideString);
    procedure Set_Nro(Value: WideString);
    procedure Set_Valor(Value: WideString);
    procedure Set_DEmi(Value: WideString);
  end;

{ TXMLTomaNaoICMS }

  TXMLTomaNaoICMS = class(TXMLNode, IXMLTomaNaoICMS)
  protected
    { IXMLTomaNaoICMS }
    function Get_RefCteAnu: WideString;
    procedure Set_RefCteAnu(Value: WideString);
  end;

{ TXMLInfCteComp }

  TXMLInfCteComp = class(TXMLNode, IXMLInfCteComp)
  protected
    { IXMLInfCteComp }
    function Get_Chave: WideString;
    function Get_VPresComp: IXMLVPresComp;
    function Get_ImpComp: IXMLImpComp;
    procedure Set_Chave(Value: WideString);
  public
    procedure AfterConstruction; override;
  end;

{ TXMLVPresComp }

  TXMLVPresComp = class(TXMLNode, IXMLVPresComp)
  private
    FCompComp: IXMLCompCompList;
  protected
    { IXMLVPresComp }
    function Get_VTPrest: WideString;
    function Get_CompComp: IXMLCompCompList;
    procedure Set_VTPrest(Value: WideString);
  public
    procedure AfterConstruction; override;
  end;

{ TXMLCompComp }

  TXMLCompComp = class(TXMLNode, IXMLCompComp)
  protected
    { IXMLCompComp }
    function Get_XNome: WideString;
    function Get_VComp: WideString;
    procedure Set_XNome(Value: WideString);
    procedure Set_VComp(Value: WideString);
  end;

{ TXMLCompCompList }

  TXMLCompCompList = class(TXMLNodeCollection, IXMLCompCompList)
  protected
    { IXMLCompCompList }
    function Add: IXMLCompComp;
    function Insert(const Index: Integer): IXMLCompComp;
    function Get_Item(Index: Integer): IXMLCompComp;
  end;

{ TXMLImpComp }

  TXMLImpComp = class(TXMLNode, IXMLImpComp)
  protected
    { IXMLImpComp }
    function Get_ICMSComp: IXMLTCST;
    function Get_InfAdFisco: WideString;
    procedure Set_InfAdFisco(Value: WideString);
  public
    procedure AfterConstruction; override;
  end;

{ TXMLInfCteAnu }

  TXMLInfCteAnu = class(TXMLNode, IXMLInfCteAnu)
  protected
    { IXMLInfCteAnu }
    function Get_ChCte: WideString;
    function Get_DEmi: WideString;
    procedure Set_ChCte(Value: WideString);
    procedure Set_DEmi(Value: WideString);
  end;

{ TXMLSignatureType }

  TXMLSignatureType = class(TXMLNode, IXMLSignatureType)
  protected
    { IXMLSignatureType }
    function Get_Id: WideString;
    function Get_SignedInfo: IXMLSignedInfoType;
    function Get_SignatureValue: IXMLSignatureValueType;
    function Get_KeyInfo: IXMLKeyInfoType;
    procedure Set_Id(Value: WideString);
  public
    procedure AfterConstruction; override;
  end;

{ TXMLSignedInfoType }

  TXMLSignedInfoType = class(TXMLNode, IXMLSignedInfoType)
  protected
    { IXMLSignedInfoType }
    function Get_Id: WideString;
    function Get_CanonicalizationMethod: IXMLCanonicalizationMethod;
    function Get_SignatureMethod: IXMLSignatureMethod;
    function Get_Reference: IXMLReferenceType;
    procedure Set_Id(Value: WideString);
  public
    procedure AfterConstruction; override;
  end;

{ TXMLCanonicalizationMethod }

  TXMLCanonicalizationMethod = class(TXMLNode, IXMLCanonicalizationMethod)
  protected
    { IXMLCanonicalizationMethod }
    function Get_Algorithm: WideString;
    procedure Set_Algorithm(Value: WideString);
  end;

{ TXMLSignatureMethod }

  TXMLSignatureMethod = class(TXMLNode, IXMLSignatureMethod)
  protected
    { IXMLSignatureMethod }
    function Get_Algorithm: WideString;
    procedure Set_Algorithm(Value: WideString);
  end;

{ TXMLReferenceType }

  TXMLReferenceType = class(TXMLNode, IXMLReferenceType)
  protected
    { IXMLReferenceType }
    function Get_Id: WideString;
    function Get_URI: WideString;
    function Get_Type_: WideString;
    function Get_Transforms: IXMLTransformsType;
    function Get_DigestMethod: IXMLDigestMethod;
    function Get_DigestValue: WideString;
    procedure Set_Id(Value: WideString);
    procedure Set_URI(Value: WideString);
    procedure Set_Type_(Value: WideString);
    procedure Set_DigestValue(Value: WideString);
  public
    procedure AfterConstruction; override;
  end;

{ TXMLTransformsType }

  TXMLTransformsType = class(TXMLNodeCollection, IXMLTransformsType)
  protected
    { IXMLTransformsType }
    function Get_Transform(Index: Integer): IXMLTransformType;
    function Add: IXMLTransformType;
    function Insert(const Index: Integer): IXMLTransformType;
  public
    procedure AfterConstruction; override;
  end;

{ TXMLTransformType }

  TXMLTransformType = class(TXMLNodeCollection, IXMLTransformType)
  protected
    { IXMLTransformType }
    function Get_Algorithm: WideString;
    function Get_XPath(Index: Integer): WideString;
    procedure Set_Algorithm(Value: WideString);
    function Add(const XPath: WideString): IXMLNode;
    function Insert(const Index: Integer; const XPath: WideString): IXMLNode;
  public
    procedure AfterConstruction; override;
  end;

{ TXMLDigestMethod }

  TXMLDigestMethod = class(TXMLNode, IXMLDigestMethod)
  protected
    { IXMLDigestMethod }
    function Get_Algorithm: WideString;
    procedure Set_Algorithm(Value: WideString);
  end;

{ TXMLSignatureValueType }

  TXMLSignatureValueType = class(TXMLNode, IXMLSignatureValueType)
  protected
    { IXMLSignatureValueType }
    function Get_Id: WideString;
    procedure Set_Id(Value: WideString);
  end;

{ TXMLKeyInfoType }

  TXMLKeyInfoType = class(TXMLNode, IXMLKeyInfoType)
  protected
    { IXMLKeyInfoType }
    function Get_Id: WideString;
    function Get_X509Data: IXMLX509DataType;
    procedure Set_Id(Value: WideString);
  public
    procedure AfterConstruction; override;
  end;

{ TXMLX509DataType }

  TXMLX509DataType = class(TXMLNode, IXMLX509DataType)
  protected
    { IXMLX509DataType }
    function Get_X509Certificate: WideString;
    procedure Set_X509Certificate(Value: WideString);
  end;

{ Global Functions }

function GetCTe(Doc: IXMLDocument): IXMLTCTe;
function LoadCTe(const FileName: WideString): IXMLTCTe;
function NewCTe: IXMLTCTe;

const
  TargetNamespace = 'http://www.portalfiscal.inf.br/cte';

implementation

{ Global Functions }

function GetCTe(Doc: IXMLDocument): IXMLTCTe;
begin
  Result := Doc.GetDocBinding('CTe', TXMLTCTe, TargetNamespace) as IXMLTCTe;
end;

function LoadCTe(const FileName: WideString): IXMLTCTe;
begin
  Result := LoadXMLDocument(FileName).GetDocBinding('CTe', TXMLTCTe, TargetNamespace) as IXMLTCTe;
end;

function NewCTe: IXMLTCTe;
begin
  Result := NewXMLDocument.GetDocBinding('CTe', TXMLTCTe, TargetNamespace) as IXMLTCTe;
end;

{ TXMLTCTe }

procedure TXMLTCTe.AfterConstruction;
begin
  RegisterChildNode('infCte', TXMLInfCte);
  RegisterChildNode('Signature', TXMLSignatureType);
  inherited;
end;

function TXMLTCTe.Get_InfCte: IXMLInfCte;
begin
  Result := ChildNodes['infCte'] as IXMLInfCte;
end;

function TXMLTCTe.Get_Signature: IXMLSignatureType;
begin
  Result := ChildNodes['Signature'] as IXMLSignatureType;
end;

{ TXMLInfCte }

procedure TXMLInfCte.AfterConstruction;
begin
  RegisterChildNode('ide', TXMLIde);
  RegisterChildNode('compl', TXMLCompl);
  RegisterChildNode('emit', TXMLEmit);
  RegisterChildNode('rem', TXMLRem);
  RegisterChildNode('exped', TXMLExped);
  RegisterChildNode('receb', TXMLReceb);
  RegisterChildNode('dest', TXMLDest);
  RegisterChildNode('vPrest', TXMLVPrest);
  RegisterChildNode('imp', TXMLImp);
  RegisterChildNode('infCTeNorm', TXMLInfCTeNorm);
  RegisterChildNode('infCteComp', TXMLInfCteComp);
  RegisterChildNode('infCteAnu', TXMLInfCteAnu);
  inherited;
end;

function TXMLInfCte.Get_Versao: WideString;
begin
  Result := AttributeNodes['versao'].Text;
end;

procedure TXMLInfCte.Set_Versao(Value: WideString);
begin
  SetAttribute('versao', Value);
end;

function TXMLInfCte.Get_Id: WideString;
begin
  Result := AttributeNodes['Id'].Text;
end;

procedure TXMLInfCte.Set_Id(Value: WideString);
begin
  SetAttribute('Id', Value);
end;

function TXMLInfCte.Get_Ide: IXMLIde;
begin
  Result := ChildNodes['ide'] as IXMLIde;
end;

function TXMLInfCte.Get_Compl: IXMLCompl;
begin
  Result := ChildNodes['compl'] as IXMLCompl;
end;

function TXMLInfCte.Get_Emit: IXMLEmit;
begin
  Result := ChildNodes['emit'] as IXMLEmit;
end;

function TXMLInfCte.Get_Rem: IXMLRem;
begin
  Result := ChildNodes['rem'] as IXMLRem;
end;

function TXMLInfCte.Get_Exped: IXMLExped;
begin
  Result := ChildNodes['exped'] as IXMLExped;
end;

function TXMLInfCte.Get_Receb: IXMLReceb;
begin
  Result := ChildNodes['receb'] as IXMLReceb;
end;

function TXMLInfCte.Get_Dest: IXMLDest;
begin
  Result := ChildNodes['dest'] as IXMLDest;
end;

function TXMLInfCte.Get_VPrest: IXMLVPrest;
begin
  Result := ChildNodes['vPrest'] as IXMLVPrest;
end;

function TXMLInfCte.Get_Imp: IXMLImp;
begin
  Result := ChildNodes['imp'] as IXMLImp;
end;

function TXMLInfCte.Get_InfCTeNorm: IXMLInfCTeNorm;
begin
  Result := ChildNodes['infCTeNorm'] as IXMLInfCTeNorm;
end;

function TXMLInfCte.Get_InfCteComp: IXMLInfCteComp;
begin
  Result := ChildNodes['infCteComp'] as IXMLInfCteComp;
end;

function TXMLInfCte.Get_InfCteAnu: IXMLInfCteAnu;
begin
  Result := ChildNodes['infCteAnu'] as IXMLInfCteAnu;
end;

{ TXMLIde }

procedure TXMLIde.AfterConstruction;
begin
  RegisterChildNode('toma03', TXMLToma03);
  RegisterChildNode('toma4', TXMLToma4);
  inherited;
end;

function TXMLIde.Get_CUF: WideString;
begin
  Result := ChildNodes['cUF'].Text;
end;

procedure TXMLIde.Set_CUF(Value: WideString);
begin
  ChildNodes['cUF'].NodeValue := Value;
end;

function TXMLIde.Get_CCT: WideString;
begin
  Result := ChildNodes['cCT'].Text;
end;

procedure TXMLIde.Set_CCT(Value: WideString);
begin
  ChildNodes['cCT'].NodeValue := Value;
end;

function TXMLIde.Get_CFOP: WideString;
begin
  Result := ChildNodes['CFOP'].Text;
end;

procedure TXMLIde.Set_CFOP(Value: WideString);
begin
  ChildNodes['CFOP'].NodeValue := Value;
end;

function TXMLIde.Get_NatOp: WideString;
begin
  Result := ChildNodes['natOp'].Text;
end;

procedure TXMLIde.Set_NatOp(Value: WideString);
begin
  ChildNodes['natOp'].NodeValue := Value;
end;

function TXMLIde.Get_ForPag: WideString;
begin
  Result := ChildNodes['forPag'].Text;
end;

procedure TXMLIde.Set_ForPag(Value: WideString);
begin
  ChildNodes['forPag'].NodeValue := Value;
end;

function TXMLIde.Get_Mod_: WideString;
begin
  Result := ChildNodes['mod'].Text;
end;

procedure TXMLIde.Set_Mod_(Value: WideString);
begin
  ChildNodes['mod'].NodeValue := Value;
end;

function TXMLIde.Get_Serie: WideString;
begin
  Result := ChildNodes['serie'].Text;
end;

procedure TXMLIde.Set_Serie(Value: WideString);
begin
  ChildNodes['serie'].NodeValue := Value;
end;

function TXMLIde.Get_NCT: WideString;
begin
  Result := ChildNodes['nCT'].Text;
end;

procedure TXMLIde.Set_NCT(Value: WideString);
begin
  ChildNodes['nCT'].NodeValue := Value;
end;

function TXMLIde.Get_DhEmi: WideString;
begin
  Result := ChildNodes['dhEmi'].Text;
end;

procedure TXMLIde.Set_DhEmi(Value: WideString);
begin
  ChildNodes['dhEmi'].NodeValue := Value;
end;

function TXMLIde.Get_TpImp: WideString;
begin
  Result := ChildNodes['tpImp'].Text;
end;

procedure TXMLIde.Set_TpImp(Value: WideString);
begin
  ChildNodes['tpImp'].NodeValue := Value;
end;

function TXMLIde.Get_TpEmis: WideString;
begin
  Result := ChildNodes['tpEmis'].Text;
end;

function TXMLIde.Get_dhCont: WideString;
begin
  Result := ChildNodes['dhCont'].Text;
end;

function TXMLIde.Get_xJust: WideString;
begin
  Result := ChildNodes['xJust'].Text;
end;

procedure TXMLIde.Set_TpEmis(Value: WideString);
begin
  ChildNodes['tpEmis'].NodeValue := Value;
end;

procedure TXMLIde.Set_dhCont(Value: WideString);  //1.04
begin
  ChildNodes['dhCont'].NodeValue := Value;
end;

procedure TXMLIde.Set_xJust(Value: WideString); //1.04
begin
  ChildNodes['xJust'].NodeValue := Value;
end;

function TXMLIde.Get_CDV: WideString;
begin
  Result := ChildNodes['cDV'].Text;
end;

procedure TXMLIde.Set_CDV(Value: WideString);
begin
  ChildNodes['cDV'].NodeValue := Value;
end;

function TXMLIde.Get_TpAmb: WideString;
begin
  Result := ChildNodes['tpAmb'].Text;
end;

procedure TXMLIde.Set_TpAmb(Value: WideString);
begin
  ChildNodes['tpAmb'].NodeValue := Value;
end;

function TXMLIde.Get_TpCTe: WideString;
begin
  Result := ChildNodes['tpCTe'].Text;
end;

procedure TXMLIde.Set_TpCTe(Value: WideString);
begin
  ChildNodes['tpCTe'].NodeValue := Value;
end;

function TXMLIde.Get_ProcEmi: WideString;
begin
  Result := ChildNodes['procEmi'].Text;
end;

procedure TXMLIde.Set_ProcEmi(Value: WideString);
begin
  ChildNodes['procEmi'].NodeValue := Value;
end;

function TXMLIde.Get_VerProc: WideString;
begin
  Result := ChildNodes['verProc'].Text;
end;

procedure TXMLIde.Set_VerProc(Value: WideString);
begin
  ChildNodes['verProc'].NodeValue := Value;
end;

function TXMLIde.Get_RefCTE: WideString;
begin
  Result := ChildNodes['refCTE'].Text;
end;

procedure TXMLIde.Set_RefCTE(Value: WideString);
begin
  ChildNodes['refCTE'].NodeValue := Value;
end;

function TXMLIde.Get_cMunEnv: WideString;  //1.04
begin
  Result := ChildNodes['cMunEnv'].Text;
end;

function TXMLIde.Get_CMunEmi: WideString;  //1.03
begin
  Result := ChildNodes['cMunEmi'].Text;
end;

procedure TXMLIde.Set_cMunEnv(Value: WideString);  //1.04
begin
  ChildNodes['cMunEnv'].NodeValue := Value;
end;

procedure TXMLIde.Set_CMunEmi(Value: WideString);  //1.03
begin
  ChildNodes['cMunEmi'].NodeValue := Value;
end;

function TXMLIde.Get_xMunEnv: WideString;  //1.04
begin
  Result := ChildNodes['xMunEnv'].Text;
end;

function TXMLIde.Get_XMunEmi: WideString;  //1.03
begin
  Result := ChildNodes['xMunEmi'].Text;
end;

procedure TXMLIde.Set_xMunEnv(Value: WideString); //1.04
begin
  ChildNodes['xMunEnv'].NodeValue := Value;
end;

procedure TXMLIde.Set_XMunEmi(Value: WideString); //1.03
begin
  ChildNodes['xMunEmi'].NodeValue := Value;
end;

function TXMLIde.Get_UFEnv: WideString; //1.04
begin
  Result := ChildNodes['UFEnv'].Text;
end;

function TXMLIde.Get_UFEmi: WideString; //1.03
begin
  Result := ChildNodes['UFEmi'].Text;
end;

procedure TXMLIde.Set_UFEnv(Value: WideString); //1.04
begin
  ChildNodes['UFEnv'].NodeValue := Value;
end;

procedure TXMLIde.Set_UFEmi(Value: WideString); //1.03
begin
  ChildNodes['UFEmi'].NodeValue := Value;
end;

function TXMLIde.Get_Modal: WideString;
begin
  Result := ChildNodes['modal'].Text;
end;

procedure TXMLIde.Set_Modal(Value: WideString);
begin
  ChildNodes['modal'].NodeValue := Value;
end;

function TXMLIde.Get_TpServ: WideString;
begin
  Result := ChildNodes['tpServ'].Text;
end;

procedure TXMLIde.Set_TpServ(Value: WideString);
begin
  ChildNodes['tpServ'].NodeValue := Value;
end;

function TXMLIde.Get_CMunIni: WideString;
begin
  Result := ChildNodes['cMunIni'].Text;
end;

procedure TXMLIde.Set_CMunIni(Value: WideString);
begin
  ChildNodes['cMunIni'].NodeValue := Value;
end;

function TXMLIde.Get_XMunIni: WideString;
begin
  Result := ChildNodes['xMunIni'].Text;
end;

procedure TXMLIde.Set_XMunIni(Value: WideString);
begin
  ChildNodes['xMunIni'].NodeValue := Value;
end;

function TXMLIde.Get_UFIni: WideString;
begin
  Result := ChildNodes['UFIni'].Text;
end;

procedure TXMLIde.Set_UFIni(Value: WideString);
begin
  ChildNodes['UFIni'].NodeValue := Value;
end;

function TXMLIde.Get_CMunFim: WideString;
begin
  Result := ChildNodes['cMunFim'].Text;
end;

procedure TXMLIde.Set_CMunFim(Value: WideString);
begin
  ChildNodes['cMunFim'].NodeValue := Value;
end;

function TXMLIde.Get_XMunFim: WideString;
begin
  Result := ChildNodes['xMunFim'].Text;
end;

procedure TXMLIde.Set_XMunFim(Value: WideString);
begin
  ChildNodes['xMunFim'].NodeValue := Value;
end;

function TXMLIde.Get_UFFim: WideString;
begin
  Result := ChildNodes['UFFim'].Text;
end;

procedure TXMLIde.Set_UFFim(Value: WideString);
begin
  ChildNodes['UFFim'].NodeValue := Value;
end;

function TXMLIde.Get_Retira: WideString;
begin
  Result := ChildNodes['retira'].Text;
end;

procedure TXMLIde.Set_Retira(Value: WideString);
begin
  ChildNodes['retira'].NodeValue := Value;
end;

function TXMLIde.Get_XDetRetira: WideString;
begin
  Result := ChildNodes['xDetRetira'].Text;
end;

procedure TXMLIde.Set_XDetRetira(Value: WideString);
begin
  ChildNodes['xDetRetira'].NodeValue := Value;
end;

function TXMLIde.Get_Toma03: IXMLToma03;
begin
  Result := ChildNodes['toma03'] as IXMLToma03;
end;

function TXMLIde.Get_Toma4: IXMLToma4;
begin
  Result := ChildNodes['toma4'] as IXMLToma4;
end;

{ TXMLToma03 }

function TXMLToma03.Get_Toma: WideString;
begin
  Result := ChildNodes['toma'].Text;
end;

procedure TXMLToma03.Set_Toma(Value: WideString);
begin
  ChildNodes['toma'].NodeValue := Value;
end;

{ TXMLToma4 }

procedure TXMLToma4.AfterConstruction;
begin
  RegisterChildNode('enderToma', TXMLTEndereco);
  inherited;
end;

function TXMLToma4.Get_Toma: WideString;
begin
  Result := ChildNodes['toma'].Text;
end;

procedure TXMLToma4.Set_Toma(Value: WideString);
begin
  ChildNodes['toma'].NodeValue := Value;
end;

function TXMLToma4.Get_CNPJ: WideString;
begin
  Result := ChildNodes['CNPJ'].Text;
end;

procedure TXMLToma4.Set_CNPJ(Value: WideString);
begin
  ChildNodes['CNPJ'].NodeValue := Value;
end;

function TXMLToma4.Get_CPF: WideString;
begin
  Result := ChildNodes['CPF'].Text;
end;

procedure TXMLToma4.Set_CPF(Value: WideString);
begin
  ChildNodes['CPF'].NodeValue := Value;
end;

function TXMLToma4.Get_IE: WideString;
begin
  Result := ChildNodes['IE'].Text;
end;

procedure TXMLToma4.Set_IE(Value: WideString);
begin
  ChildNodes['IE'].NodeValue := Value;
end;

function TXMLToma4.Get_XNome: WideString;
begin
  Result := ChildNodes['xNome'].Text;
end;

procedure TXMLToma4.Set_XNome(Value: WideString);
begin
  ChildNodes['xNome'].NodeValue := Value;
end;

function TXMLToma4.Get_XFant: WideString;
begin
  Result := ChildNodes['xFant'].Text;
end;

procedure TXMLToma4.Set_XFant(Value: WideString);
begin
  ChildNodes['xFant'].NodeValue := Value;
end;

function TXMLToma4.Get_Fone: WideString;
begin
  Result := ChildNodes['fone'].Text;
end;

procedure TXMLToma4.Set_Fone(Value: WideString);
begin
  ChildNodes['fone'].NodeValue := Value;
end;

function TXMLToma4.Get_EnderToma: IXMLTEndereco;
begin
  Result := ChildNodes['enderToma'] as IXMLTEndereco;
end;

{ TXMLTEndereco }

function TXMLTEndereco.Get_XLgr: WideString;
begin
  Result := ChildNodes['xLgr'].Text;
end;

procedure TXMLTEndereco.Set_XLgr(Value: WideString);
begin
  ChildNodes['xLgr'].NodeValue := Value;
end;

function TXMLTEndereco.Get_Nro: WideString;
begin
  Result := ChildNodes['nro'].Text;
end;

procedure TXMLTEndereco.Set_Nro(Value: WideString);
begin
  ChildNodes['nro'].NodeValue := Value;
end;

function TXMLTEndereco.Get_XCpl: WideString;
begin
  Result := ChildNodes['xCpl'].Text;
end;

procedure TXMLTEndereco.Set_XCpl(Value: WideString);
begin
  ChildNodes['xCpl'].NodeValue := Value;
end;

function TXMLTEndereco.Get_XBairro: WideString;
begin
  Result := ChildNodes['xBairro'].Text;
end;

procedure TXMLTEndereco.Set_XBairro(Value: WideString);
begin
  ChildNodes['xBairro'].NodeValue := Value;
end;

function TXMLTEndereco.Get_CMun: WideString;
begin
  Result := ChildNodes['cMun'].Text;
end;

procedure TXMLTEndereco.Set_CMun(Value: WideString);
begin
  ChildNodes['cMun'].NodeValue := Value;
end;

function TXMLTEndereco.Get_XMun: WideString;
begin
  Result := ChildNodes['xMun'].Text;
end;

procedure TXMLTEndereco.Set_XMun(Value: WideString);
begin
  ChildNodes['xMun'].NodeValue := Value;
end;

function TXMLTEndereco.Get_CEP: WideString;
begin
  Result := ChildNodes['CEP'].Text;
end;

procedure TXMLTEndereco.Set_CEP(Value: WideString);
begin
  ChildNodes['CEP'].NodeValue := Value;
end;

function TXMLTEndereco.Get_UF: WideString;
begin
  Result := ChildNodes['UF'].Text;
end;

procedure TXMLTEndereco.Set_UF(Value: WideString);
begin
  ChildNodes['UF'].NodeValue := Value;
end;

function TXMLTEndereco.Get_CPais: WideString;
begin
  Result := ChildNodes['cPais'].Text;
end;

procedure TXMLTEndereco.Set_CPais(Value: WideString);
begin
  ChildNodes['cPais'].NodeValue := Value;
end;

function TXMLTEndereco.Get_XPais: WideString;
begin
  Result := ChildNodes['xPais'].Text;
end;

procedure TXMLTEndereco.Set_XPais(Value: WideString);
begin
  ChildNodes['xPais'].NodeValue := Value;
end;

{ TXMLCompl }

procedure TXMLCompl.AfterConstruction;
begin
  RegisterChildNode('fluxo', TXMLFluxo);
  RegisterChildNode('Entrega', TXMLEntrega);
  RegisterChildNode('ObsCont', TXMLObsCont);
  RegisterChildNode('ObsFisco', TXMLObsFisco);
  inherited;
end;

function TXMLCompl.Get_XCaracAd: WideString;
begin
  Result := ChildNodes['xCaracAd'].Text;
end;

procedure TXMLCompl.Set_XCaracAd(Value: WideString);
begin
  ChildNodes['xCaracAd'].NodeValue := Value;
end;

function TXMLCompl.Get_XCaracSer: WideString;
begin
  Result := ChildNodes['xCaracSer'].Text;
end;

procedure TXMLCompl.Set_XCaracSer(Value: WideString);
begin
  ChildNodes['xCaracSer'].NodeValue := Value;
end;

function TXMLCompl.Get_XEmi: WideString;
begin
  Result := ChildNodes['xEmi'].Text;
end;

procedure TXMLCompl.Set_XEmi(Value: WideString);
begin
  ChildNodes['xEmi'].NodeValue := Value;
end;

function TXMLCompl.Get_Fluxo: IXMLFluxo;
begin
  Result := ChildNodes['fluxo'] as IXMLFluxo;
end;

function TXMLCompl.Get_Entrega: IXMLEntrega;
begin
  Result := ChildNodes['Entrega'] as IXMLEntrega;
end;

function TXMLCompl.Get_OrigCalc: WideString;
begin
  Result := ChildNodes['origCalc'].Text;
end;

procedure TXMLCompl.Set_OrigCalc(Value: WideString);
begin
  ChildNodes['origCalc'].NodeValue := Value;
end;

function TXMLCompl.Get_DestCalc: WideString;
begin
  Result := ChildNodes['destCalc'].Text;
end;

procedure TXMLCompl.Set_DestCalc(Value: WideString);
begin
  ChildNodes['destCalc'].NodeValue := Value;
end;

function TXMLCompl.Get_XObs: WideString;
begin
  Result := ChildNodes['xObs'].Text;
end;

procedure TXMLCompl.Set_XObs(Value: WideString);
begin
  ChildNodes['xObs'].NodeValue := Value;
end;

function TXMLCompl.Get_ObsCont: IXMLObsCont;
begin
  Result := ChildNodes['ObsCont'] as IXMLObsCont;
end;

function TXMLCompl.Get_ObsFisco: IXMLObsFisco;
begin
  Result := ChildNodes['ObsFisco'] as IXMLObsFisco;
end;

{ TXMLFluxo }

procedure TXMLFluxo.AfterConstruction;
begin
  RegisterChildNode('pass', TXMLPass);
  FPass := CreateCollection(TXMLPassList, IXMLPass, 'pass') as IXMLPassList;
  inherited;
end;

function TXMLFluxo.Get_XOrig: WideString;
begin
  Result := ChildNodes['xOrig'].Text;
end;

procedure TXMLFluxo.Set_XOrig(Value: WideString);
begin
  ChildNodes['xOrig'].NodeValue := Value;
end;

function TXMLFluxo.Get_Pass: IXMLPassList;
begin
  Result := FPass;
end;

function TXMLFluxo.Get_XDest: WideString;
begin
  Result := ChildNodes['xDest'].Text;
end;

procedure TXMLFluxo.Set_XDest(Value: WideString);
begin
  ChildNodes['xDest'].NodeValue := Value;
end;

function TXMLFluxo.Get_XRota: WideString;
begin
  Result := ChildNodes['xRota'].Text;
end;

procedure TXMLFluxo.Set_XRota(Value: WideString);
begin
  ChildNodes['xRota'].NodeValue := Value;
end;

{ TXMLPass }

function TXMLPass.Get_XPass: WideString;
begin
  Result := ChildNodes['xPass'].Text;
end;

procedure TXMLPass.Set_XPass(Value: WideString);
begin
  ChildNodes['xPass'].NodeValue := Value;
end;

{ TXMLPassList }

function TXMLPassList.Add: IXMLPass;
begin
  Result := AddItem(-1) as IXMLPass;
end;

function TXMLPassList.Insert(const Index: Integer): IXMLPass;
begin
  Result := AddItem(Index) as IXMLPass;
end;
function TXMLPassList.Get_Item(Index: Integer): IXMLPass;
begin
  Result := List[Index] as IXMLPass;
end;

{ TXMLEntrega }

procedure TXMLEntrega.AfterConstruction;
begin
  RegisterChildNode('semData', TXMLSemData);
  RegisterChildNode('comData', TXMLComData);
  RegisterChildNode('noPeriodo', TXMLNoPeriodo);
  RegisterChildNode('semHora', TXMLSemHora);
  RegisterChildNode('comHora', TXMLComHora);
  RegisterChildNode('noInter', TXMLNoInter);
  inherited;
end;

function TXMLEntrega.Get_SemData: IXMLSemData;
begin
  Result := ChildNodes['semData'] as IXMLSemData;
end;

function TXMLEntrega.Get_ComData: IXMLComData;
begin
  Result := ChildNodes['comData'] as IXMLComData;
end;

function TXMLEntrega.Get_NoPeriodo: IXMLNoPeriodo;
begin
  Result := ChildNodes['noPeriodo'] as IXMLNoPeriodo;
end;

function TXMLEntrega.Get_SemHora: IXMLSemHora;
begin
  Result := ChildNodes['semHora'] as IXMLSemHora;
end;

function TXMLEntrega.Get_ComHora: IXMLComHora;
begin
  Result := ChildNodes['comHora'] as IXMLComHora;
end;

function TXMLEntrega.Get_NoInter: IXMLNoInter;
begin
  Result := ChildNodes['noInter'] as IXMLNoInter;
end;

{ TXMLSemData }

function TXMLSemData.Get_TpPer: WideString;
begin
  Result := ChildNodes['tpPer'].Text;
end;

procedure TXMLSemData.Set_TpPer(Value: WideString);
begin
  ChildNodes['tpPer'].NodeValue := Value;
end;

{ TXMLComData }

function TXMLComData.Get_TpPer: WideString;
begin
  Result := ChildNodes['tpPer'].Text;
end;

procedure TXMLComData.Set_TpPer(Value: WideString);
begin
  ChildNodes['tpPer'].NodeValue := Value;
end;

function TXMLComData.Get_DProg: WideString;
begin
  Result := ChildNodes['dProg'].Text;
end;

procedure TXMLComData.Set_DProg(Value: WideString);
begin
  ChildNodes['dProg'].NodeValue := Value;
end;

{ TXMLNoPeriodo }

function TXMLNoPeriodo.Get_TpPer: WideString;
begin
  Result := ChildNodes['tpPer'].Text;
end;

procedure TXMLNoPeriodo.Set_TpPer(Value: WideString);
begin
  ChildNodes['tpPer'].NodeValue := Value;
end;

function TXMLNoPeriodo.Get_DIni: WideString;
begin
  Result := ChildNodes['dIni'].Text;
end;

procedure TXMLNoPeriodo.Set_DIni(Value: WideString);
begin
  ChildNodes['dIni'].NodeValue := Value;
end;

function TXMLNoPeriodo.Get_DFim: WideString;
begin
  Result := ChildNodes['dFim'].Text;
end;

procedure TXMLNoPeriodo.Set_DFim(Value: WideString);
begin
  ChildNodes['dFim'].NodeValue := Value;
end;

{ TXMLSemHora }

function TXMLSemHora.Get_TpHor: WideString;
begin
  Result := ChildNodes['tpHor'].Text;
end;

procedure TXMLSemHora.Set_TpHor(Value: WideString);
begin
  ChildNodes['tpHor'].NodeValue := Value;
end;

{ TXMLComHora }

function TXMLComHora.Get_TpHor: WideString;
begin
  Result := ChildNodes['tpHor'].Text;
end;

procedure TXMLComHora.Set_TpHor(Value: WideString);
begin
  ChildNodes['tpHor'].NodeValue := Value;
end;

function TXMLComHora.Get_HProg: WideString;
begin
  Result := ChildNodes['hProg'].Text;
end;

procedure TXMLComHora.Set_HProg(Value: WideString);
begin
  ChildNodes['hProg'].NodeValue := Value;
end;

{ TXMLNoInter }

function TXMLNoInter.Get_Tphor: WideString;
begin
  Result := ChildNodes['tphor'].Text;
end;

procedure TXMLNoInter.Set_Tphor(Value: WideString);
begin
  ChildNodes['tphor'].NodeValue := Value;
end;

function TXMLNoInter.Get_HIni: WideString;
begin
  Result := ChildNodes['hIni'].Text;
end;

procedure TXMLNoInter.Set_HIni(Value: WideString);
begin
  ChildNodes['hIni'].NodeValue := Value;
end;

function TXMLNoInter.Get_HFim: WideString;
begin
  Result := ChildNodes['hFim'].Text;
end;

procedure TXMLNoInter.Set_HFim(Value: WideString);
begin
  ChildNodes['hFim'].NodeValue := Value;
end;

{ TXMLObsCont }

function TXMLObsCont.Get_XCampo: WideString;
begin
  Result := AttributeNodes['xCampo'].Text;
end;

procedure TXMLObsCont.Set_XCampo(Value: WideString);
begin
  SetAttribute('xCampo', Value);
end;

function TXMLObsCont.Get_XTexto: WideString;
begin
  Result := ChildNodes['xTexto'].Text;
end;

procedure TXMLObsCont.Set_XTexto(Value: WideString);
begin
  ChildNodes['xTexto'].NodeValue := Value;
end;

{ TXMLObsFisco }

function TXMLObsFisco.Get_XCampo: WideString;
begin
  Result := AttributeNodes['xCampo'].Text;
end;

procedure TXMLObsFisco.Set_XCampo(Value: WideString);
begin
  SetAttribute('xCampo', Value);
end;

function TXMLObsFisco.Get_XTexto: WideString;
begin
  Result := ChildNodes['xTexto'].Text;
end;

procedure TXMLObsFisco.Set_XTexto(Value: WideString);
begin
  ChildNodes['xTexto'].NodeValue := Value;
end;

{ TXMLEmit }

procedure TXMLEmit.AfterConstruction;
begin
  RegisterChildNode('enderEmit', TXMLTEndeEmi);
  inherited;
end;

function TXMLEmit.Get_CNPJ: WideString;
begin
  Result := ChildNodes['CNPJ'].Text;
end;

procedure TXMLEmit.Set_CNPJ(Value: WideString);
begin
  ChildNodes['CNPJ'].NodeValue := Value;
end;

function TXMLEmit.Get_IE: WideString;
begin
  Result := ChildNodes['IE'].Text;
end;

procedure TXMLEmit.Set_IE(Value: WideString);
begin
  ChildNodes['IE'].NodeValue := Value;
end;

function TXMLEmit.Get_XNome: WideString;
begin
  Result := ChildNodes['xNome'].Text;
end;

procedure TXMLEmit.Set_XNome(Value: WideString);
begin
  ChildNodes['xNome'].NodeValue := Value;
end;

function TXMLEmit.Get_XFant: WideString;
begin
  Result := ChildNodes['xFant'].Text;
end;

procedure TXMLEmit.Set_XFant(Value: WideString);
begin
  ChildNodes['xFant'].NodeValue := Value;
end;

function TXMLEmit.Get_EnderEmit: IXMLTEndeEmi;
begin
  Result := ChildNodes['enderEmit'] as IXMLTEndeEmi;
end;

{ TXMLTEndeEmi }

function TXMLTEndeEmi.Get_XLgr: WideString;
begin
  Result := ChildNodes['xLgr'].Text;
end;

procedure TXMLTEndeEmi.Set_XLgr(Value: WideString);
begin
  ChildNodes['xLgr'].NodeValue := Value;
end;

function TXMLTEndeEmi.Get_Nro: WideString;
begin
  Result := ChildNodes['nro'].Text;
end;

procedure TXMLTEndeEmi.Set_Nro(Value: WideString);
begin
  ChildNodes['nro'].NodeValue := Value;
end;

function TXMLTEndeEmi.Get_XCpl: WideString;
begin
  Result := ChildNodes['xCpl'].Text;
end;

procedure TXMLTEndeEmi.Set_XCpl(Value: WideString);
begin
  ChildNodes['xCpl'].NodeValue := Value;
end;

function TXMLTEndeEmi.Get_XBairro: WideString;
begin
  Result := ChildNodes['xBairro'].Text;
end;

procedure TXMLTEndeEmi.Set_XBairro(Value: WideString);
begin
  ChildNodes['xBairro'].NodeValue := Value;
end;

function TXMLTEndeEmi.Get_CMun: WideString;
begin
  Result := ChildNodes['cMun'].Text;
end;

procedure TXMLTEndeEmi.Set_CMun(Value: WideString);
begin
  ChildNodes['cMun'].NodeValue := Value;
end;

function TXMLTEndeEmi.Get_XMun: WideString;
begin
  Result := ChildNodes['xMun'].Text;
end;

procedure TXMLTEndeEmi.Set_XMun(Value: WideString);
begin
  ChildNodes['xMun'].NodeValue := Value;
end;

function TXMLTEndeEmi.Get_CEP: WideString;
begin
  Result := ChildNodes['CEP'].Text;
end;

procedure TXMLTEndeEmi.Set_CEP(Value: WideString);
begin
  ChildNodes['CEP'].NodeValue := Value;
end;

function TXMLTEndeEmi.Get_UF: WideString;
begin
  Result := ChildNodes['UF'].Text;
end;

procedure TXMLTEndeEmi.Set_UF(Value: WideString);
begin
  ChildNodes['UF'].NodeValue := Value;
end;

function TXMLTEndeEmi.Get_CPais: WideString;
begin
  Result := ChildNodes['cPais'].Text;
end;

procedure TXMLTEndeEmi.Set_CPais(Value: WideString);
begin
  ChildNodes['cPais'].NodeValue := Value;
end;

function TXMLTEndeEmi.Get_XPais: WideString;
begin
  Result := ChildNodes['xPais'].Text;
end;

procedure TXMLTEndeEmi.Set_XPais(Value: WideString);
begin
  ChildNodes['xPais'].NodeValue := Value;
end;

function TXMLTEndeEmi.Get_Fone: WideString;
begin
  Result := ChildNodes['fone'].Text;
end;

procedure TXMLTEndeEmi.Set_Fone(Value: WideString);
begin
  ChildNodes['fone'].NodeValue := Value;
end;

{ TXMLRem }

procedure TXMLRem.AfterConstruction;
begin
  RegisterChildNode('enderReme', TXMLTEndereco);
  RegisterChildNode('infNF', TXMLInfNF);
  RegisterChildNode('infNFe', TXMLInfNFe);
  RegisterChildNode('infOutros', TXMLInfOutros);
  FInfNF := CreateCollection(TXMLInfNFList, IXMLInfNF, 'infNF') as IXMLInfNFList;
  FInfNFe := CreateCollection(TXMLInfNFeList, IXMLInfNFe, 'infNFe') as IXMLInfNFeList;
  FInfOutros := CreateCollection(TXMLInfOutrosList, IXMLInfOutros, 'infOutros') as IXMLInfOutrosList;
  inherited;
end;

function TXMLRem.Get_CNPJ: WideString;
begin
  Result := ChildNodes['CNPJ'].Text;
end;

procedure TXMLRem.Set_CNPJ(Value: WideString);
begin
  ChildNodes['CNPJ'].NodeValue := Value;
end;

function TXMLRem.Get_CPF: WideString;
begin
  Result := ChildNodes['CPF'].Text;
end;

procedure TXMLRem.Set_CPF(Value: WideString);
begin
  ChildNodes['CPF'].NodeValue := Value;
end;

function TXMLRem.Get_IE: WideString;
begin
  Result := ChildNodes['IE'].Text;
end;

procedure TXMLRem.Set_IE(Value: WideString);
begin
  ChildNodes['IE'].NodeValue := Value;
end;

function TXMLRem.Get_XNome: WideString;
begin
  Result := ChildNodes['xNome'].Text;
end;

procedure TXMLRem.Set_XNome(Value: WideString);
begin
  ChildNodes['xNome'].NodeValue := Value;
end;

function TXMLRem.Get_XFant: WideString;
begin
  Result := ChildNodes['xFant'].Text;
end;

procedure TXMLRem.Set_XFant(Value: WideString);
begin
  ChildNodes['xFant'].NodeValue := Value;
end;

function TXMLRem.Get_Fone: WideString;
begin
  Result := ChildNodes['fone'].Text;
end;

procedure TXMLRem.Set_Fone(Value: WideString);
begin
  ChildNodes['fone'].NodeValue := Value;
end;

function TXMLRem.Get_EnderReme: IXMLTEndereco;
begin
  Result := ChildNodes['enderReme'] as IXMLTEndereco;
end;

function TXMLRem.Get_InfNF: IXMLInfNFList;
begin
  Result := FInfNF;
end;

function TXMLRem.Get_InfNFe: IXMLInfNFeList;
begin
  Result := FInfNFe;
end;

function TXMLRem.Get_InfOutros: IXMLInfOutrosList;
begin
  Result := FInfOutros;
end;

{ TXMLInfNF }

procedure TXMLInfNF.AfterConstruction;
begin
  RegisterChildNode('locRet', TXMLTEndReEnt);
  inherited;
end;

function TXMLInfNF.Get_NRoma: WideString;
begin
  Result := ChildNodes['nRoma'].Text;
end;

procedure TXMLInfNF.Set_NRoma(Value: WideString);
begin
  ChildNodes['nRoma'].NodeValue := Value;
end;

function TXMLInfNF.Get_NPed: WideString;
begin
  Result := ChildNodes['nPed'].Text;
end;

procedure TXMLInfNF.Set_NPed(Value: WideString);
begin
  ChildNodes['nPed'].NodeValue := Value;
end;

function TXMLInfNF.Get_Serie: WideString;
begin
  Result := ChildNodes['serie'].Text;
end;

procedure TXMLInfNF.Set_Serie(Value: WideString);
begin
  ChildNodes['serie'].NodeValue := Value;
end;

function TXMLInfNF.Get_NDoc: WideString;
begin
  Result := ChildNodes['nDoc'].Text;
end;

procedure TXMLInfNF.Set_NDoc(Value: WideString);
begin
  ChildNodes['nDoc'].NodeValue := Value;
end;

function TXMLInfNF.Get_DEmi: WideString;
begin
  Result := ChildNodes['dEmi'].Text;
end;

procedure TXMLInfNF.Set_DEmi(Value: WideString);
begin
  ChildNodes['dEmi'].NodeValue := Value;
end;

function TXMLInfNF.Get_vBC: WideString;
begin
  Result := ChildNodes['vBC'].Text;
end;

procedure TXMLInfNF.Set_vBC(Value: WideString);
begin
  ChildNodes['vBC'].NodeValue := Value;
end;

function TXMLInfNF.Get_vICMS: WideString;
begin
  Result := ChildNodes['vICMS'].Text;
end;

procedure TXMLInfNF.Set_vICMS(Value: WideString);
begin
  ChildNodes['vICMS'].NodeValue := Value;
end;

function TXMLInfNF.Get_vBCST: WideString;
begin
  Result := ChildNodes['vBCST'].Text;
end;

procedure TXMLInfNF.Set_vBCST(Value: WideString);
begin
  ChildNodes['vBCST'].NodeValue := Value;
end;

function TXMLInfNF.Get_VST: WideString;
begin
  Result := ChildNodes['vST'].Text;
end;

procedure TXMLInfNF.Set_VST(Value: WideString);
begin
  ChildNodes['vST'].NodeValue := Value;
end;

function TXMLInfNF.Get_VProd: WideString;
begin
  Result := ChildNodes['vProd'].Text;
end;

procedure TXMLInfNF.Set_VProd(Value: WideString);
begin
  ChildNodes['vProd'].NodeValue := Value;
end;

function TXMLInfNF.Get_VNF: WideString;
begin
  Result := ChildNodes['vNF'].Text;
end;

procedure TXMLInfNF.Set_VNF(Value: WideString);
begin
  ChildNodes['vNF'].NodeValue := Value;
end;

function TXMLInfNF.Get_NCFOP: WideString;
begin
  Result := ChildNodes['nCFOP'].Text;
end;

procedure TXMLInfNF.Set_NCFOP(Value: WideString);
begin
  ChildNodes['nCFOP'].NodeValue := Value;
end;

function TXMLInfNF.Get_NPeso: WideString;
begin
  Result := ChildNodes['nPeso'].Text;
end;

procedure TXMLInfNF.Set_NPeso(Value: WideString);
begin
  ChildNodes['nPeso'].NodeValue := Value;
end;

function TXMLInfNF.Get_PIN: WideString;
begin
  Result := ChildNodes['PIN'].Text;
end;

procedure TXMLInfNF.Set_PIN(Value: WideString);
begin
  ChildNodes['PIN'].NodeValue := Value;
end;

function TXMLInfNF.Get_LocRet: IXMLTEndReEnt;
begin
  Result := ChildNodes['locRet'] as IXMLTEndReEnt;
end;

{ TXMLInfNFList }

function TXMLInfNFList.Add: IXMLInfNF;
begin
  Result := AddItem(-1) as IXMLInfNF;
end;

function TXMLInfNFList.Insert(const Index: Integer): IXMLInfNF;
begin
  Result := AddItem(Index) as IXMLInfNF;
end;
function TXMLInfNFList.Get_Item(Index: Integer): IXMLInfNF;
begin
  Result := List[Index] as IXMLInfNF;
end;

{ TXMLTEndReEnt }

function TXMLTEndReEnt.Get_CNPJ: WideString;
begin
  Result := ChildNodes['CNPJ'].Text;
end;

procedure TXMLTEndReEnt.Set_CNPJ(Value: WideString);
begin
  ChildNodes['CNPJ'].NodeValue := Value;
end;

function TXMLTEndReEnt.Get_CPF: WideString;
begin
  Result := ChildNodes['CPF'].Text;
end;

procedure TXMLTEndReEnt.Set_CPF(Value: WideString);
begin
  ChildNodes['CPF'].NodeValue := Value;
end;

function TXMLTEndReEnt.Get_XNome: WideString;
begin
  Result := ChildNodes['xNome'].Text;
end;

procedure TXMLTEndReEnt.Set_XNome(Value: WideString);
begin
  ChildNodes['xNome'].NodeValue := Value;
end;

function TXMLTEndReEnt.Get_XLgr: WideString;
begin
  Result := ChildNodes['xLgr'].Text;
end;

procedure TXMLTEndReEnt.Set_XLgr(Value: WideString);
begin
  ChildNodes['xLgr'].NodeValue := Value;
end;

function TXMLTEndReEnt.Get_Nro: WideString;
begin
  Result := ChildNodes['nro'].Text;
end;

procedure TXMLTEndReEnt.Set_Nro(Value: WideString);
begin
  ChildNodes['nro'].NodeValue := Value;
end;

function TXMLTEndReEnt.Get_XCpl: WideString;
begin
  Result := ChildNodes['xCpl'].Text;
end;

procedure TXMLTEndReEnt.Set_XCpl(Value: WideString);
begin
  ChildNodes['xCpl'].NodeValue := Value;
end;

function TXMLTEndReEnt.Get_XBairro: WideString;
begin
  Result := ChildNodes['xBairro'].Text;
end;

procedure TXMLTEndReEnt.Set_XBairro(Value: WideString);
begin
  ChildNodes['xBairro'].NodeValue := Value;
end;

function TXMLTEndReEnt.Get_CMun: WideString;
begin
  Result := ChildNodes['cMun'].Text;
end;

procedure TXMLTEndReEnt.Set_CMun(Value: WideString);
begin
  ChildNodes['cMun'].NodeValue := Value;
end;

function TXMLTEndReEnt.Get_XMun: WideString;
begin
  Result := ChildNodes['xMun'].Text;
end;

procedure TXMLTEndReEnt.Set_XMun(Value: WideString);
begin
  ChildNodes['xMun'].NodeValue := Value;
end;

function TXMLTEndReEnt.Get_UF: WideString;
begin
  Result := ChildNodes['UF'].Text;
end;

procedure TXMLTEndReEnt.Set_UF(Value: WideString);
begin
  ChildNodes['UF'].NodeValue := Value;
end;

{ TXMLInfNFe }

function TXMLInfNFe.Get_Chave: WideString;
begin
  Result := ChildNodes['chave'].Text;
end;

procedure TXMLInfNFe.Set_Chave(Value: WideString);
begin
  ChildNodes['chave'].NodeValue := Value;
end;

function TXMLInfNFe.Get_PIN: WideString;
begin
  Result := ChildNodes['PIN'].Text;
end;

procedure TXMLInfNFe.Set_PIN(Value: WideString);
begin
  ChildNodes['PIN'].NodeValue := Value;
end;

{ TXMLInfNFeList }

function TXMLInfNFeList.Add: IXMLInfNFe;
begin
  Result := AddItem(-1) as IXMLInfNFe;
end;

function TXMLInfNFeList.Insert(const Index: Integer): IXMLInfNFe;
begin
  Result := AddItem(Index) as IXMLInfNFe;
end;
function TXMLInfNFeList.Get_Item(Index: Integer): IXMLInfNFe;
begin
  Result := List[Index] as IXMLInfNFe;
end;

{ TXMLInfOutros }

function TXMLInfOutros.Get_TpDoc: WideString;
begin
  Result := ChildNodes['tpDoc'].Text;
end;

procedure TXMLInfOutros.Set_TpDoc(Value: WideString);
begin
  ChildNodes['tpDoc'].NodeValue := Value;
end;

function TXMLInfOutros.Get_DescOutros: WideString;
begin
  Result := ChildNodes['descOutros'].Text;
end;

procedure TXMLInfOutros.Set_DescOutros(Value: WideString);
begin
  ChildNodes['descOutros'].NodeValue := Value;
end;

function TXMLInfOutros.Get_NDoc: WideString;
begin
  Result := ChildNodes['nDoc'].Text;
end;

procedure TXMLInfOutros.Set_NDoc(Value: WideString);
begin
  ChildNodes['nDoc'].NodeValue := Value;
end;

function TXMLInfOutros.Get_DEmi: WideString;
begin
  Result := ChildNodes['dEmi'].Text;
end;

procedure TXMLInfOutros.Set_DEmi(Value: WideString);
begin
  ChildNodes['dEmi'].NodeValue := Value;
end;

function TXMLInfOutros.Get_VDocFisc: WideString;
begin
  Result := ChildNodes['vDocFisc'].Text;
end;

procedure TXMLInfOutros.Set_VDocFisc(Value: WideString);
begin
  ChildNodes['vDocFisc'].NodeValue := Value;
end;

{ TXMLInfOutrosList }

function TXMLInfOutrosList.Add: IXMLInfOutros;
begin
  Result := AddItem(-1) as IXMLInfOutros;
end;

function TXMLInfOutrosList.Insert(const Index: Integer): IXMLInfOutros;
begin
  Result := AddItem(Index) as IXMLInfOutros;
end;
function TXMLInfOutrosList.Get_Item(Index: Integer): IXMLInfOutros;
begin
  Result := List[Index] as IXMLInfOutros;
end;

{ TXMLExped }

procedure TXMLExped.AfterConstruction;
begin
  RegisterChildNode('enderExped', TXMLTEndereco);
  inherited;
end;

function TXMLExped.Get_CNPJ: WideString;
begin
  Result := ChildNodes['CNPJ'].Text;
end;

procedure TXMLExped.Set_CNPJ(Value: WideString);
begin
  ChildNodes['CNPJ'].NodeValue := Value;
end;

function TXMLExped.Get_CPF: WideString;
begin
  Result := ChildNodes['CPF'].Text;
end;

procedure TXMLExped.Set_CPF(Value: WideString);
begin
  ChildNodes['CPF'].NodeValue := Value;
end;

function TXMLExped.Get_IE: WideString;
begin
  Result := ChildNodes['IE'].Text;
end;

procedure TXMLExped.Set_IE(Value: WideString);
begin
  ChildNodes['IE'].NodeValue := Value;
end;

function TXMLExped.Get_XNome: WideString;
begin
  Result := ChildNodes['xNome'].Text;
end;

procedure TXMLExped.Set_XNome(Value: WideString);
begin
  ChildNodes['xNome'].NodeValue := Value;
end;

function TXMLExped.Get_Fone: WideString;
begin
  Result := ChildNodes['fone'].Text;
end;

procedure TXMLExped.Set_Fone(Value: WideString);
begin
  ChildNodes['fone'].NodeValue := Value;
end;

function TXMLExped.Get_EnderExped: IXMLTEndereco;
begin
  Result := ChildNodes['enderExped'] as IXMLTEndereco;
end;

{ TXMLReceb }

procedure TXMLReceb.AfterConstruction;
begin
  RegisterChildNode('enderReceb', TXMLTEndereco);
  inherited;
end;

function TXMLReceb.Get_CNPJ: WideString;
begin
  Result := ChildNodes['CNPJ'].Text;
end;

procedure TXMLReceb.Set_CNPJ(Value: WideString);
begin
  ChildNodes['CNPJ'].NodeValue := Value;
end;

function TXMLReceb.Get_CPF: WideString;
begin
  Result := ChildNodes['CPF'].Text;
end;

procedure TXMLReceb.Set_CPF(Value: WideString);
begin
  ChildNodes['CPF'].NodeValue := Value;
end;

function TXMLReceb.Get_IE: WideString;
begin
  Result := ChildNodes['IE'].Text;
end;

procedure TXMLReceb.Set_IE(Value: WideString);
begin
  ChildNodes['IE'].NodeValue := Value;
end;

function TXMLReceb.Get_XNome: WideString;
begin
  Result := ChildNodes['xNome'].Text;
end;

procedure TXMLReceb.Set_XNome(Value: WideString);
begin
  ChildNodes['xNome'].NodeValue := Value;
end;

function TXMLReceb.Get_Fone: WideString;
begin
  Result := ChildNodes['fone'].Text;
end;

procedure TXMLReceb.Set_Fone(Value: WideString);
begin
  ChildNodes['fone'].NodeValue := Value;
end;

function TXMLReceb.Get_EnderReceb: IXMLTEndereco;
begin
  Result := ChildNodes['enderReceb'] as IXMLTEndereco;
end;

{ TXMLDest }

procedure TXMLDest.AfterConstruction;
begin
  RegisterChildNode('enderDest', TXMLTEndereco);
  RegisterChildNode('locEnt', TXMLTEndReEnt);
  inherited;
end;

function TXMLDest.Get_CNPJ: WideString;
begin
  Result := ChildNodes['CNPJ'].Text;
end;

procedure TXMLDest.Set_CNPJ(Value: WideString);
begin
  ChildNodes['CNPJ'].NodeValue := Value;
end;

function TXMLDest.Get_CPF: WideString;
begin
  Result := ChildNodes['CPF'].Text;
end;

procedure TXMLDest.Set_CPF(Value: WideString);
begin
  ChildNodes['CPF'].NodeValue := Value;
end;

function TXMLDest.Get_IE: WideString;
begin
  Result := ChildNodes['IE'].Text;
end;

procedure TXMLDest.Set_IE(Value: WideString);
begin
  ChildNodes['IE'].NodeValue := Value;
end;

function TXMLDest.Get_XNome: WideString;
begin
  Result := ChildNodes['xNome'].Text;
end;

procedure TXMLDest.Set_XNome(Value: WideString);
begin
  ChildNodes['xNome'].NodeValue := Value;
end;

function TXMLDest.Get_Fone: WideString;
begin
  Result := ChildNodes['fone'].Text;
end;

procedure TXMLDest.Set_Fone(Value: WideString);
begin
  ChildNodes['fone'].NodeValue := Value;
end;

function TXMLDest.Get_ISUF: WideString;
begin
  Result := ChildNodes['ISUF'].Text;
end;

procedure TXMLDest.Set_ISUF(Value: WideString);
begin
  ChildNodes['ISUF'].NodeValue := Value;
end;

function TXMLDest.Get_EnderDest: IXMLTEndereco;
begin
  Result := ChildNodes['enderDest'] as IXMLTEndereco;
end;

function TXMLDest.Get_LocEnt: IXMLTEndReEnt;
begin
  Result := ChildNodes['locEnt'] as IXMLTEndReEnt;
end;

{ TXMLVPrest }

procedure TXMLVPrest.AfterConstruction;
begin
  RegisterChildNode('Comp', TXMLComp);
  FComp := CreateCollection(TXMLCompList, IXMLComp, 'Comp') as IXMLCompList;
  inherited;
end;

function TXMLVPrest.Get_VTPrest: WideString;
begin
  Result := ChildNodes['vTPrest'].Text;
end;

procedure TXMLVPrest.Set_VTPrest(Value: WideString);
begin
  ChildNodes['vTPrest'].NodeValue := Value;
end;

function TXMLVPrest.Get_VRec: WideString;
begin
  Result := ChildNodes['vRec'].Text;
end;

procedure TXMLVPrest.Set_VRec(Value: WideString);
begin
  ChildNodes['vRec'].NodeValue := Value;
end;

function TXMLVPrest.Get_Comp: IXMLCompList;
begin
  Result := FComp;
end;

{ TXMLComp }

function TXMLComp.Get_XNome: WideString;
begin
  Result := ChildNodes['xNome'].Text;
end;

procedure TXMLComp.Set_XNome(Value: WideString);
begin
  ChildNodes['xNome'].NodeValue := Value;
end;

function TXMLComp.Get_VComp: WideString;
begin
  Result := ChildNodes['vComp'].Text;
end;

procedure TXMLComp.Set_VComp(Value: WideString);
begin
  ChildNodes['vComp'].NodeValue := Value;
end;

{ TXMLCompList }

function TXMLCompList.Add: IXMLComp;
begin
  Result := AddItem(-1) as IXMLComp;
end;

function TXMLCompList.Insert(const Index: Integer): IXMLComp;
begin
  Result := AddItem(Index) as IXMLComp;
end;
function TXMLCompList.Get_Item(Index: Integer): IXMLComp;
begin
  Result := List[Index] as IXMLComp;
end;

{ TXMLImp }

procedure TXMLImp.AfterConstruction;
begin
  RegisterChildNode('ICMS', TXMLTCST);
  inherited;
end;

function TXMLImp.Get_ICMS: IXMLTCST;
begin
  Result := ChildNodes['ICMS'] as IXMLTCST;
end;

function TXMLImp.Get_InfAdFisco: WideString;
begin
  Result := ChildNodes['infAdFisco'].Text;
end;

procedure TXMLImp.Set_InfAdFisco(Value: WideString);
begin
  ChildNodes['infAdFisco'].NodeValue := Value;
end;

{ TXMLTCST }

procedure TXMLTCST.AfterConstruction;
begin
  RegisterChildNode('CST00', TXMLCST00);
  RegisterChildNode('CST20', TXMLCST20);
  RegisterChildNode('CST45', TXMLCST45);
  RegisterChildNode('CST80', TXMLCST80);
  RegisterChildNode('CST81', TXMLCST81);
  RegisterChildNode('CST90', TXMLCST90);

  RegisterChildNode('ICMS00', TXMLICMS00);
  RegisterChildNode('ICMS20', TXMLICMS20);
  RegisterChildNode('ICMS45', TXMLICMS45);
  RegisterChildNode('ICMS60', TXMLICMS60);
  RegisterChildNode('ICMS90', TXMLICMS90);
  RegisterChildNode('ICMSOutraUF', TXMLICMSOutraUF);
  RegisterChildNode('IXMLICMSSN', TXMLICMSSN);

  inherited;
end;

function TXMLTCST.Get_CST00: IXMLCST00;
begin
  Result := ChildNodes['CST00'] as IXMLCST00;
end;

function TXMLTCST.Get_CST20: IXMLCST20;
begin
  Result := ChildNodes['CST20'] as IXMLCST20;
end;

function TXMLTCST.Get_CST45: IXMLCST45;
begin
  Result := ChildNodes['CST45'] as IXMLCST45;
end;

function TXMLTCST.Get_CST80: IXMLCST80;
begin
  Result := ChildNodes['CST80'] as IXMLCST80;
end;

function TXMLTCST.Get_CST81: IXMLCST81;
begin
  Result := ChildNodes['CST81'] as IXMLCST81;
end;

function TXMLTCST.Get_CST90: IXMLCST90;
begin
  Result := ChildNodes['CST90'] as IXMLCST90;
end;

function TXMLTCST.Get_ICMS00: IXMLICMS00;
begin
  Result := ChildNodes['ICMS00'] as IXMLICMS00;
end;

function TXMLTCST.Get_ICMS20: IXMLICMS20;
begin
  Result := ChildNodes['ICMS20'] as IXMLICMS20;
end;

function TXMLTCST.Get_ICMS45: IXMLICMS45;
begin
  Result := ChildNodes['ICMS45'] as IXMLICMS45;
end;

function TXMLTCST.Get_ICMS60: IXMLICMS60;
begin
  Result := ChildNodes['ICMS60'] as IXMLICMS60;
end;

function TXMLTCST.Get_ICMS90: IXMLICMS90;
begin
  Result := ChildNodes['ICMS90'] as IXMLICMS90;
end;

function TXMLTCST.Get_ICMSOutraUF: IXMLICMSOutraUF;
begin
  Result := ChildNodes['ICMSOutraUF'] as IXMLICMSOutraUF;
end;

function TXMLTCST.Get_ICMSSN: IXMLICMSSN;
begin
  Result := ChildNodes['ICMSSN'] as IXMLICMSSN;
end;

{ TXMLCST00 }

function TXMLCST00.Get_CST: WideString;
begin
  Result := ChildNodes['CST'].Text;
end;

procedure TXMLCST00.Set_CST(Value: WideString);
begin
  ChildNodes['CST'].NodeValue := Value;
end;

function TXMLCST00.Get_vBC: WideString;
begin
  Result := ChildNodes['vBC'].Text;
end;

procedure TXMLCST00.Set_vBC(Value: WideString);
begin
  ChildNodes['vBC'].NodeValue := Value;
end;

function TXMLCST00.Get_pICMS: WideString;
begin
  Result := ChildNodes['pICMS'].Text;
end;

procedure TXMLCST00.Set_pICMS(Value: WideString);
begin
  ChildNodes['pICMS'].NodeValue := Value;
end;

function TXMLCST00.Get_vICMS: WideString;
begin
  Result := ChildNodes['vICMS'].Text;
end;

procedure TXMLCST00.Set_vICMS(Value: WideString);
begin
  ChildNodes['vICMS'].NodeValue := Value;
end;

{ TXMLCST20 }

function TXMLCST20.Get_CST: WideString;
begin
  Result := ChildNodes['CST'].Text;
end;

procedure TXMLCST20.Set_CST(Value: WideString);
begin
  ChildNodes['CST'].NodeValue := Value;
end;

function TXMLCST20.Get_PRedBC: WideString;
begin
  Result := ChildNodes['pRedBC'].Text;
end;

procedure TXMLCST20.Set_PRedBC(Value: WideString);
begin
  ChildNodes['pRedBC'].NodeValue := Value;
end;

function TXMLCST20.Get_vBC: WideString;
begin
  Result := ChildNodes['vBC'].Text;
end;

procedure TXMLCST20.Set_vBC(Value: WideString);
begin
  ChildNodes['vBC'].NodeValue := Value;
end;

function TXMLCST20.Get_pICMS: WideString;
begin
  Result := ChildNodes['pICMS'].Text;
end;

procedure TXMLCST20.Set_pICMS(Value: WideString);
begin
  ChildNodes['pICMS'].NodeValue := Value;
end;

function TXMLCST20.Get_vICMS: WideString;
begin
  Result := ChildNodes['vICMS'].Text;
end;

procedure TXMLCST20.Set_vICMS(Value: WideString);
begin
  ChildNodes['vICMS'].NodeValue := Value;
end;

{ TXMLCST45 }

function TXMLCST45.Get_CST: WideString;
begin
  Result := ChildNodes['CST'].Text;
end;

procedure TXMLCST45.Set_CST(Value: WideString);
begin
  ChildNodes['CST'].NodeValue := Value;
end;

{ TXMLCST80 }

function TXMLCST80.Get_CST: WideString;
begin
  Result := ChildNodes['CST'].Text;
end;

procedure TXMLCST80.Set_CST(Value: WideString);
begin
  ChildNodes['CST'].NodeValue := Value;
end;

function TXMLCST80.Get_vBC: WideString;
begin
  Result := ChildNodes['vBC'].Text;
end;

procedure TXMLCST80.Set_vBC(Value: WideString);
begin
  ChildNodes['vBC'].NodeValue := Value;
end;

function TXMLCST80.Get_pICMS: WideString;
begin
  Result := ChildNodes['pICMS'].Text;
end;

procedure TXMLCST80.Set_pICMS(Value: WideString);
begin
  ChildNodes['pICMS'].NodeValue := Value;
end;

function TXMLCST80.Get_vICMS: WideString;
begin
  Result := ChildNodes['vICMS'].Text;
end;

procedure TXMLCST80.Set_vICMS(Value: WideString);
begin
  ChildNodes['vICMS'].NodeValue := Value;
end;

function TXMLCST80.Get_VCred: WideString;
begin
  Result := ChildNodes['vCred'].Text;
end;

procedure TXMLCST80.Set_VCred(Value: WideString);
begin
  ChildNodes['vCred'].NodeValue := Value;
end;

{ TXMLCST81 }

function TXMLCST81.Get_CST: WideString;
begin
  Result := ChildNodes['CST'].Text;
end;

procedure TXMLCST81.Set_CST(Value: WideString);
begin
  ChildNodes['CST'].NodeValue := Value;
end;

function TXMLCST81.Get_PRedBC: WideString;
begin
  Result := ChildNodes['pRedBC'].Text;
end;

procedure TXMLCST81.Set_PRedBC(Value: WideString);
begin
  ChildNodes['pRedBC'].NodeValue := Value;
end;

function TXMLCST81.Get_vBC: WideString;
begin
  Result := ChildNodes['vBC'].Text;
end;

procedure TXMLCST81.Set_vBC(Value: WideString);
begin
  ChildNodes['vBC'].NodeValue := Value;
end;

function TXMLCST81.Get_pICMS: WideString;
begin
  Result := ChildNodes['pICMS'].Text;
end;

procedure TXMLCST81.Set_pICMS(Value: WideString);
begin
  ChildNodes['pICMS'].NodeValue := Value;
end;

function TXMLCST81.Get_vICMS: WideString;
begin
  Result := ChildNodes['vICMS'].Text;
end;

procedure TXMLCST81.Set_vICMS(Value: WideString);
begin
  ChildNodes['vICMS'].NodeValue := Value;
end;

{ TXMLCST90 }

function TXMLCST90.Get_CST: WideString;
begin
  Result := ChildNodes['CST'].Text;
end;

procedure TXMLCST90.Set_CST(Value: WideString);
begin
  ChildNodes['CST'].NodeValue := Value;
end;

function TXMLCST90.Get_PRedBC: WideString;
begin
  Result := ChildNodes['pRedBC'].Text;
end;

procedure TXMLCST90.Set_PRedBC(Value: WideString);
begin
  ChildNodes['pRedBC'].NodeValue := Value;
end;

function TXMLCST90.Get_vBC: WideString;
begin
  Result := ChildNodes['vBC'].Text;
end;

procedure TXMLCST90.Set_vBC(Value: WideString);
begin
  ChildNodes['vBC'].NodeValue := Value;
end;

function TXMLCST90.Get_pICMS: WideString;
begin
  Result := ChildNodes['pICMS'].Text;
end;

procedure TXMLCST90.Set_pICMS(Value: WideString);
begin
  ChildNodes['pICMS'].NodeValue := Value;
end;

function TXMLCST90.Get_vICMS: WideString;
begin
  Result := ChildNodes['vICMS'].Text;
end;

procedure TXMLCST90.Set_vICMS(Value: WideString);
begin
  ChildNodes['vICMS'].NodeValue := Value;
end;

function TXMLCST90.Get_VCred: WideString;
begin
  Result := ChildNodes['vCred'].Text;
end;

procedure TXMLCST90.Set_VCred(Value: WideString);
begin
  ChildNodes['vCred'].NodeValue := Value;
end;
//---Enio

{ TXMLICMS00 }
function  TXMLICMS00.Get_CST: WideString;
begin
      Result := ChildNodes['CST'].Text;
end;

procedure TXMLICMS00.Set_CST(Value: WideString);
begin
      ChildNodes['CST'].NodeValue := Value;
end;

function  TXMLICMS00.Get_vBC: WideString;
begin
      Result := ChildNodes['vBC'].Text;
end;

procedure TXMLICMS00.Set_vBC(Value: WideString);
begin
      ChildNodes['vBC'].NodeValue := Value;
end;

function  TXMLICMS00.Get_pICMS: WideString;
begin
      Result := ChildNodes['pICMS'].Text;
end;

procedure TXMLICMS00.Set_pICMS(Value: WideString);
begin
      ChildNodes['pICMS'].NodeValue := Value;
end;

function  TXMLICMS00.Get_vICMS: WideString;
begin
      Result := ChildNodes['vICMS'].Text;
end;

procedure TXMLICMS00.Set_vICMS(Value: WideString);
begin
      ChildNodes['vICMS'].NodeValue := Value;
end;

{ TXMLICMS20 }
function  TXMLICMS20.Get_CST: WideString;
begin
      Result := ChildNodes['CST'].Text;
end;

procedure TXMLICMS20.Set_CST(Value: WideString);
begin
      ChildNodes['CST'].NodeValue := Value;
end;

function  TXMLICMS20.Get_PRedBC: WideString;
begin
      Result := ChildNodes['PRedBC'].Text;
end;

procedure TXMLICMS20.Set_PRedBC(Value: WideString);
begin
      ChildNodes['PRedBC'].NodeValue := Value;
end;

function  TXMLICMS20.Get_vBC: WideString;
begin
      Result := ChildNodes['vBC'].Text;
end;

procedure TXMLICMS20.Set_vBC(Value: WideString);
begin
      ChildNodes['vBC'].NodeValue := Value;
end;

function  TXMLICMS20.Get_pICMS: WideString;
begin
      Result := ChildNodes['pICMS'].Text;
end;

procedure TXMLICMS20.Set_pICMS(Value: WideString);
begin
      ChildNodes['pICMS'].NodeValue := Value;
end;

function  TXMLICMS20.Get_vICMS: WideString;
begin
      Result := ChildNodes['vICMS'].Text;
end;

procedure TXMLICMS20.Set_vICMS(Value: WideString);
begin
      ChildNodes['vICMS'].NodeValue := Value;
end;

{ TXMLICMS45 }
function  TXMLICMS45.Get_CST: WideString;
begin
      Result := ChildNodes['CST'].Text;
end;

procedure TXMLICMS45.Set_CST(Value: WideString);
begin
      ChildNodes['CST'].NodeValue := Value;
end;

{ TXMLICMS60 }
function  TXMLICMS60.Get_CST: WideString;
begin
      Result := ChildNodes['CST'].Text;
end;

procedure TXMLICMS60.Set_CST(Value: WideString);
begin
      ChildNodes['CST'].NodeValue := Value;
end;

function  TXMLICMS60.Get_vBCSTRet: WideString;
begin
      Result := ChildNodes['vBCSTRet'].Text;
end;

procedure TXMLICMS60.Set_vBCSTRet(Value: WideString);
begin
      ChildNodes['vBCSTRet'].NodeValue := Value;
end;

function  TXMLICMS60.Get_vICMSSTRet: WideString;
begin
      Result := ChildNodes['vICMSSTRet'].Text;
end;

procedure TXMLICMS60.Set_vICMSSTRet(Value: WideString);
begin
      ChildNodes['vICMSSTRet'].NodeValue := Value;
end;

function  TXMLICMS60.Get_pICMSSTRet: WideString;
begin
      Result := ChildNodes['pICMSSTRet'].Text;
end;

procedure TXMLICMS60.Set_pICMSSTRet(Value: WideString);
begin
      ChildNodes['pICMSSTRet'].NodeValue := Value;
end;

function  TXMLICMS60.Get_vCred: WideString;
begin
      Result := ChildNodes['vCred'].Text;
end;

procedure TXMLICMS60.Set_vCred(Value: WideString);
begin
      ChildNodes['vCred'].NodeValue := Value;
end;


{ TXMLCST90 }
function  TXMLICMS90.Get_CST   : WideString;
begin
      Result := ChildNodes['CST'].Text;
end;

procedure TXMLICMS90.Set_CST(Value   : WideString);
begin
      ChildNodes['CST'].NodeValue := Value;
end;

function  TXMLICMS90.Get_PRedBC: WideString;
begin
      Result := ChildNodes['PRedBC'].Text;
end;

procedure TXMLICMS90.Set_PRedBC(Value: WideString);
begin
      ChildNodes['PRedBC'].NodeValue := Value;
end;

function  TXMLICMS90.Get_vBC   : WideString;
begin
      Result := ChildNodes['vBC'].Text;
end;

procedure TXMLICMS90.Set_vBC(Value   : WideString);
begin
      ChildNodes['vBC'].NodeValue := Value;
end;

function  TXMLICMS90.Get_pICMS : WideString;
begin
      Result := ChildNodes['pICMS'].Text;
end;

procedure TXMLICMS90.Set_pICMS(Value : WideString);
begin
      ChildNodes['pICMS'].NodeValue := Value;
end;

function  TXMLICMS90.Get_vICMS : WideString;
begin
      Result := ChildNodes['vICMS'].Text;
end;

procedure TXMLICMS90.Set_vICMS(Value : WideString);
begin
      ChildNodes['vICMS'].NodeValue := Value;
end;

function  TXMLICMS90.Get_VCred : WideString;
begin
      Result := ChildNodes['vCred'].Text;
end;

procedure TXMLICMS90.Set_VCred(Value : WideString);
begin
      ChildNodes['vCred'].NodeValue := Value;
end;

{ TXMLICMSOutraUF }
function  TXMLICMSOutraUF.Get_CST   : WideString;
begin
      Result := ChildNodes['CST'].Text;
end;

procedure TXMLICMSOutraUF.Set_CST(Value   : WideString);
begin
      ChildNodes['CST'].NodeValue := Value;
end;

function  TXMLICMSOutraUF.Get_PRedBC: WideString;
begin
      Result := ChildNodes['PRedBC'].Text;
end;

procedure TXMLICMSOutraUF.Set_PRedBC(Value: WideString);
begin
      ChildNodes['PRedBC'].NodeValue := Value;
end;

function  TXMLICMSOutraUF.Get_vBC   : WideString;
begin
      Result := ChildNodes['vBC'].Text;
end;

procedure TXMLICMSOutraUF.Set_vBC(Value   : WideString);
begin
      ChildNodes['vBC'].NodeValue := Value;
end;

function  TXMLICMSOutraUF.Get_pICMS : WideString;
begin
      Result := ChildNodes['pICMS'].Text;
end;

procedure TXMLICMSOutraUF.Set_pICMS(Value : WideString);
begin
      ChildNodes['pICMS'].NodeValue := Value;
end;

function  TXMLICMSOutraUF.Get_vICMS : WideString;
begin
      Result := ChildNodes['vICMS'].Text;
end;

procedure TXMLICMSOutraUF.Set_vICMS(Value : WideString);
begin
      ChildNodes['vICMS'].NodeValue := Value;
end;

function  TXMLICMSOutraUF.Get_VCred : WideString;
begin
      Result := ChildNodes['VCred'].Text;
end;

procedure TXMLICMSOutraUF.Set_VCred(Value : WideString);
begin
      ChildNodes['VCred'].NodeValue := Value;
end;

{ TXMLICMSSN }
function  TXMLICMSSN.Get_indSN: WideString;
begin
      Result := ChildNodes['indSN'].Text;
end;

procedure TXMLICMSSN.Set_indSN(Value: WideString);
begin
      ChildNodes['indSN'].NodeValue := Value;
end;

function  TXMLICMSSN.Get_infAdFisco: WideString;
begin
      Result := ChildNodes['infAdFisco'].Text;
end;

procedure TXMLICMSSN.Set_infAdFisco(Value: WideString);
begin
      ChildNodes['infAdFisco'].NodeValue := Value;
end;
//---Enio

{ TXMLInfCTeNorm }

procedure TXMLInfCTeNorm.AfterConstruction;
begin
  RegisterChildNode('infCarga', TXMLInfCarga);
  RegisterChildNode('contQt', TXMLContQt);
  RegisterChildNode('docAnt', TXMLDocAnt);
  RegisterChildNode('seg', TXMLSeg);
  RegisterChildNode('rodo', TXMLRodo);
  RegisterChildNode('infModal', TXMLinfModal);
  RegisterChildNode('aereo', TXMLAereo);
  RegisterChildNode('aquav', TXMLAquav);
  RegisterChildNode('ferrov', TXMLFerrov);
  RegisterChildNode('duto', TXMLDuto);
  RegisterChildNode('peri', TXMLPeri);
  RegisterChildNode('veicNovos', TXMLVeicNovos);
  RegisterChildNode('infCteSub', TXMLInfCteSub);
  FContQt := CreateCollection(TXMLContQtList, IXMLContQt, 'contQt') as IXMLContQtList;
  FSeg := CreateCollection(TXMLSegList, IXMLSeg, 'seg') as IXMLSegList;
  FPeri := CreateCollection(TXMLPeriList, IXMLPeri, 'peri') as IXMLPeriList;
  FVeicNovos := CreateCollection(TXMLVeicNovosList, IXMLVeicNovos, 'veicNovos') as IXMLVeicNovosList;
  inherited;
end;

function TXMLInfCTeNorm.Get_InfCarga: IXMLInfCarga;
begin
  Result := ChildNodes['infCarga'] as IXMLInfCarga;
end;

function TXMLInfCTeNorm.Get_ContQt: IXMLContQtList;
begin
  Result := FContQt;
end;

function TXMLInfCTeNorm.Get_DocAnt: IXMLDocAnt;
begin
  Result := ChildNodes['docAnt'] as IXMLDocAnt;
end;

function TXMLInfCTeNorm.Get_Seg: IXMLSegList;
begin
  Result := FSeg;
end;

function TXMLInfCTeNorm.Get_Rodo: IXMLRodo;
begin
  Result := ChildNodes['rodo'] as IXMLRodo;
end;

function TXMLInfCTeNorm.Get_infModal: IXMLinfModal;
begin
  Result := ChildNodes['infModal'] as IXMLinfModal;
end;

function TXMLInfCTeNorm.Get_Aereo: IXMLAereo;
begin
  Result := ChildNodes['aereo'] as IXMLAereo;
end;

function TXMLInfCTeNorm.Get_Aquav: IXMLAquav;
begin
  Result := ChildNodes['aquav'] as IXMLAquav;
end;

function TXMLInfCTeNorm.Get_Ferrov: IXMLFerrov;
begin
  Result := ChildNodes['ferrov'] as IXMLFerrov;
end;

function TXMLInfCTeNorm.Get_Duto: IXMLDuto;
begin
  Result := ChildNodes['duto'] as IXMLDuto;
end;

function TXMLInfCTeNorm.Get_Peri: IXMLPeriList;
begin
  Result := FPeri;
end;

function TXMLInfCTeNorm.Get_VeicNovos: IXMLVeicNovosList;
begin
  Result := FVeicNovos;
end;

function TXMLInfCTeNorm.Get_InfCteSub: IXMLInfCteSub;
begin
  Result := ChildNodes['infCteSub'] as IXMLInfCteSub;
end;

{ TXMLInfCarga }

procedure TXMLInfCarga.AfterConstruction;
begin
  RegisterChildNode('infQ', TXMLInfQ);
  FInfQ := CreateCollection(TXMLInfQList, IXMLInfQ, 'infQ') as IXMLInfQList;
  inherited;
end;

function TXMLInfCarga.Get_VMerc: WideString;
begin
  Result := ChildNodes['vMerc'].Text;
end;

function TXMLInfCarga.Get_VCarga: WideString;
begin
  Result := ChildNodes['vCarga'].Text;
end;

procedure TXMLInfCarga.Set_VMerc(Value: WideString);
begin
  ChildNodes['vMerc'].NodeValue := Value;
end;

procedure TXMLInfCarga.Set_vCarga(Value: WideString);
begin
  ChildNodes['vCarga'].NodeValue := Value;
end;

function TXMLInfCarga.Get_ProPred: WideString;
begin
  Result := ChildNodes['proPred'].Text;
end;

procedure TXMLInfCarga.Set_ProPred(Value: WideString);
begin
  ChildNodes['proPred'].NodeValue := Value;
end;

function TXMLInfCarga.Get_XOutCat: WideString;
begin
  Result := ChildNodes['xOutCat'].Text;
end;

procedure TXMLInfCarga.Set_XOutCat(Value: WideString);
begin
  ChildNodes['xOutCat'].NodeValue := Value;
end;

function TXMLInfCarga.Get_InfQ: IXMLInfQList;
begin
  Result := FInfQ;
end;

{ TXMLInfQ }

function TXMLInfQ.Get_CUnid: WideString;
begin
  Result := ChildNodes['cUnid'].Text;
end;

procedure TXMLInfQ.Set_CUnid(Value: WideString);
begin
  ChildNodes['cUnid'].NodeValue := Value;
end;

function TXMLInfQ.Get_TpMed: WideString;
begin
  Result := ChildNodes['tpMed'].Text;
end;

procedure TXMLInfQ.Set_TpMed(Value: WideString);
begin
  ChildNodes['tpMed'].NodeValue := Value;
end;

function TXMLInfQ.Get_QCarga: WideString;
begin
  Result := ChildNodes['qCarga'].Text;
end;

procedure TXMLInfQ.Set_QCarga(Value: WideString);
begin
  ChildNodes['qCarga'].NodeValue := Value;
end;

{ TXMLInfQList }

function TXMLInfQList.Add: IXMLInfQ;
begin
  Result := AddItem(-1) as IXMLInfQ;
end;

function TXMLInfQList.Insert(const Index: Integer): IXMLInfQ;
begin
  Result := AddItem(Index) as IXMLInfQ;
end;
function TXMLInfQList.Get_Item(Index: Integer): IXMLInfQ;
begin
  Result := List[Index] as IXMLInfQ;
end;

{ TXMLContQt }

procedure TXMLContQt.AfterConstruction;
begin
  RegisterChildNode('lacContQt', TXMLLacContQt);
  FLacContQt := CreateCollection(TXMLLacContQtList, IXMLLacContQt, 'lacContQt') as IXMLLacContQtList;
  inherited;
end;

function TXMLContQt.Get_NCont: WideString;
begin
  Result := ChildNodes['nCont'].Text;
end;

procedure TXMLContQt.Set_NCont(Value: WideString);
begin
  ChildNodes['nCont'].NodeValue := Value;
end;

function TXMLContQt.Get_LacContQt: IXMLLacContQtList;
begin
  Result := FLacContQt;
end;

function TXMLContQt.Get_DPrev: WideString;
begin
  Result := ChildNodes['dPrev'].Text;
end;

procedure TXMLContQt.Set_DPrev(Value: WideString);
begin
  ChildNodes['dPrev'].NodeValue := Value;
end;

{ TXMLContQtList }

function TXMLContQtList.Add: IXMLContQt;
begin
  Result := AddItem(-1) as IXMLContQt;
end;

function TXMLContQtList.Insert(const Index: Integer): IXMLContQt;
begin
  Result := AddItem(Index) as IXMLContQt;
end;
function TXMLContQtList.Get_Item(Index: Integer): IXMLContQt;
begin
  Result := List[Index] as IXMLContQt;
end;

{ TXMLLacContQt }

function TXMLLacContQt.Get_NLacre: WideString;
begin
  Result := ChildNodes['nLacre'].Text;
end;

procedure TXMLLacContQt.Set_NLacre(Value: WideString);
begin
  ChildNodes['nLacre'].NodeValue := Value;
end;

{ TXMLLacContQtList }

function TXMLLacContQtList.Add: IXMLLacContQt;
begin
  Result := AddItem(-1) as IXMLLacContQt;
end;

function TXMLLacContQtList.Insert(const Index: Integer): IXMLLacContQt;
begin
  Result := AddItem(Index) as IXMLLacContQt;
end;
function TXMLLacContQtList.Get_Item(Index: Integer): IXMLLacContQt;
begin
  Result := List[Index] as IXMLLacContQt;
end;

{ TXMLDocAnt }

procedure TXMLDocAnt.AfterConstruction;
begin
  RegisterChildNode('emiDocAnt', TXMLEmiDocAnt);
  ItemTag := 'emiDocAnt';
  ItemInterface := IXMLEmiDocAnt;
  inherited;
end;

function TXMLDocAnt.Get_EmiDocAnt(Index: Integer): IXMLEmiDocAnt;
begin
  Result := List[Index] as IXMLEmiDocAnt;
end;

function TXMLDocAnt.Add: IXMLEmiDocAnt;
begin
  Result := AddItem(-1) as IXMLEmiDocAnt;
end;

function TXMLDocAnt.Insert(const Index: Integer): IXMLEmiDocAnt;
begin
  Result := AddItem(Index) as IXMLEmiDocAnt;
end;

{ TXMLEmiDocAnt }

procedure TXMLEmiDocAnt.AfterConstruction;
begin
  RegisterChildNode('idDocAnt', TXMLIdDocAnt);
  FIdDocAnt := CreateCollection(TXMLIdDocAntList, IXMLIdDocAnt, 'idDocAnt') as IXMLIdDocAntList;
  inherited;
end;

function TXMLEmiDocAnt.Get_CNPJ: WideString;
begin
  Result := ChildNodes['CNPJ'].Text;
end;

procedure TXMLEmiDocAnt.Set_CNPJ(Value: WideString);
begin
  ChildNodes['CNPJ'].NodeValue := Value;
end;

function TXMLEmiDocAnt.Get_CPF: WideString;
begin
  Result := ChildNodes['CPF'].Text;
end;

procedure TXMLEmiDocAnt.Set_CPF(Value: WideString);
begin
  ChildNodes['CPF'].NodeValue := Value;
end;

function TXMLEmiDocAnt.Get_IE: WideString;
begin
  Result := ChildNodes['IE'].Text;
end;

procedure TXMLEmiDocAnt.Set_IE(Value: WideString);
begin
  ChildNodes['IE'].NodeValue := Value;
end;

function TXMLEmiDocAnt.Get_UF: WideString;
begin
  Result := ChildNodes['UF'].Text;
end;

procedure TXMLEmiDocAnt.Set_UF(Value: WideString);
begin
  ChildNodes['UF'].NodeValue := Value;
end;

function TXMLEmiDocAnt.Get_XNome: WideString;
begin
  Result := ChildNodes['xNome'].Text;
end;

procedure TXMLEmiDocAnt.Set_XNome(Value: WideString);
begin
  ChildNodes['xNome'].NodeValue := Value;
end;

function TXMLEmiDocAnt.Get_IdDocAnt: IXMLIdDocAntList;
begin
  Result := FIdDocAnt;
end;

{ TXMLIdDocAnt }

procedure TXMLIdDocAnt.AfterConstruction;
begin
  RegisterChildNode('idDocAntPap', TXMLIdDocAntPap);
  RegisterChildNode('idDocAntEle', TXMLIdDocAntEle);
  FIdDocAntPap := CreateCollection(TXMLIdDocAntPapList, IXMLIdDocAntPap, 'idDocAntPap') as IXMLIdDocAntPapList;
  FIdDocAntEle := CreateCollection(TXMLIdDocAntEleList, IXMLIdDocAntEle, 'idDocAntEle') as IXMLIdDocAntEleList;
  inherited;
end;

function TXMLIdDocAnt.Get_IdDocAntPap: IXMLIdDocAntPapList;
begin
  Result := FIdDocAntPap;
end;

function TXMLIdDocAnt.Get_IdDocAntEle: IXMLIdDocAntEleList;
begin
  Result := FIdDocAntEle;
end;

{ TXMLIdDocAntList }

function TXMLIdDocAntList.Add: IXMLIdDocAnt;
begin
  Result := AddItem(-1) as IXMLIdDocAnt;
end;

function TXMLIdDocAntList.Insert(const Index: Integer): IXMLIdDocAnt;
begin
  Result := AddItem(Index) as IXMLIdDocAnt;
end;
function TXMLIdDocAntList.Get_Item(Index: Integer): IXMLIdDocAnt;
begin
  Result := List[Index] as IXMLIdDocAnt;
end;

{ TXMLIdDocAntPap }

function TXMLIdDocAntPap.Get_TpDoc: WideString;
begin
  Result := ChildNodes['tpDoc'].Text;
end;

procedure TXMLIdDocAntPap.Set_TpDoc(Value: WideString);
begin
  ChildNodes['tpDoc'].NodeValue := Value;
end;

function TXMLIdDocAntPap.Get_Serie: WideString;
begin
  Result := ChildNodes['serie'].Text;
end;

procedure TXMLIdDocAntPap.Set_Serie(Value: WideString);
begin
  ChildNodes['serie'].NodeValue := Value;
end;

function TXMLIdDocAntPap.Get_Subser: WideString;
begin
  Result := ChildNodes['subser'].Text;
end;

procedure TXMLIdDocAntPap.Set_Subser(Value: WideString);
begin
  ChildNodes['subser'].NodeValue := Value;
end;

function TXMLIdDocAntPap.Get_NDoc: WideString;
begin
  Result := ChildNodes['nDoc'].Text;
end;

procedure TXMLIdDocAntPap.Set_NDoc(Value: WideString);
begin
  ChildNodes['nDoc'].NodeValue := Value;
end;

function TXMLIdDocAntPap.Get_DEmi: WideString;
begin
  Result := ChildNodes['dEmi'].Text;
end;

procedure TXMLIdDocAntPap.Set_DEmi(Value: WideString);
begin
  ChildNodes['dEmi'].NodeValue := Value;
end;

{ TXMLIdDocAntPapList }

function TXMLIdDocAntPapList.Add: IXMLIdDocAntPap;
begin
  Result := AddItem(-1) as IXMLIdDocAntPap;
end;

function TXMLIdDocAntPapList.Insert(const Index: Integer): IXMLIdDocAntPap;
begin
  Result := AddItem(Index) as IXMLIdDocAntPap;
end;
function TXMLIdDocAntPapList.Get_Item(Index: Integer): IXMLIdDocAntPap;
begin
  Result := List[Index] as IXMLIdDocAntPap;
end;

{ TXMLIdDocAntEle }

function TXMLIdDocAntEle.Get_Chave: WideString;
begin
  Result := ChildNodes['chave'].Text;
end;

procedure TXMLIdDocAntEle.Set_Chave(Value: WideString);
begin
  ChildNodes['chave'].NodeValue := Value;
end;

{ TXMLIdDocAntEleList }

function TXMLIdDocAntEleList.Add: IXMLIdDocAntEle;
begin
  Result := AddItem(-1) as IXMLIdDocAntEle;
end;

function TXMLIdDocAntEleList.Insert(const Index: Integer): IXMLIdDocAntEle;
begin
  Result := AddItem(Index) as IXMLIdDocAntEle;
end;
function TXMLIdDocAntEleList.Get_Item(Index: Integer): IXMLIdDocAntEle;
begin
  Result := List[Index] as IXMLIdDocAntEle;
end;

{ TXMLSeg }

function TXMLSeg.Get_RespSeg: WideString;
begin
  Result := ChildNodes['respSeg'].Text;
end;

procedure TXMLSeg.Set_RespSeg(Value: WideString);
begin
  ChildNodes['respSeg'].NodeValue := Value;
end;

function TXMLSeg.Get_XSeg: WideString;
begin
  Result := ChildNodes['xSeg'].Text;
end;

procedure TXMLSeg.Set_XSeg(Value: WideString);
begin
  ChildNodes['xSeg'].NodeValue := Value;
end;

function TXMLSeg.Get_NApol: WideString;
begin
  Result := ChildNodes['nApol'].Text;
end;

procedure TXMLSeg.Set_NApol(Value: WideString);
begin
  ChildNodes['nApol'].NodeValue := Value;
end;

function TXMLSeg.Get_NAver: WideString;
begin
  Result := ChildNodes['nAver'].Text;
end;

procedure TXMLSeg.Set_NAver(Value: WideString);
begin
  ChildNodes['nAver'].NodeValue := Value;
end;

function TXMLSeg.Get_VMerc: WideString;
begin
  Result := ChildNodes['vMerc'].Text;
end;

procedure TXMLSeg.Set_VMerc(Value: WideString);
begin
  ChildNodes['vMerc'].NodeValue := Value;
end;

function TXMLSeg.Get_VCarga: WideString;
begin
  Result := ChildNodes['vCarga'].Text;
end;

procedure TXMLSeg.Set_VCarga(Value: WideString);
begin
  ChildNodes['vCarga'].NodeValue := Value;
end;

{ TXMLSegList }

function TXMLSegList.Add: IXMLSeg;
begin
  Result := AddItem(-1) as IXMLSeg;
end;

function TXMLSegList.Insert(const Index: Integer): IXMLSeg;
begin
  Result := AddItem(Index) as IXMLSeg;
end;
function TXMLSegList.Get_Item(Index: Integer): IXMLSeg;
begin
  Result := List[Index] as IXMLSeg;
end;

{ TXMLRodo }

procedure TXMLRodo.AfterConstruction;
begin
  RegisterChildNode('CTRB', TXMLCTRB);
  RegisterChildNode('occ', TXMLOcc);
  RegisterChildNode('valePed', TXMLValePed);
  RegisterChildNode('veic', TXMLVeic);
  RegisterChildNode('lacRodo', TXMLLacRodo);
  RegisterChildNode('moto', TXMLMoto);
  FVeic := CreateCollection(TXMLVeicList, IXMLVeic, 'veic') as IXMLVeicList;
  FLacRodo := CreateCollection(TXMLLacRodoList, IXMLLacRodo, 'lacRodo') as IXMLLacRodoList;
  FMoto := CreateCollection(TXMLMotoList, IXMLMoto, 'moto') as IXMLMotoList;
  inherited;
end;

function TXMLRodo.Get_RNTRC: WideString;
begin
  Result := ChildNodes['RNTRC'].Text;
end;

procedure TXMLRodo.Set_RNTRC(Value: WideString);
begin
  ChildNodes['RNTRC'].NodeValue := Value;
end;

function TXMLRodo.Get_DPrev: WideString;
begin
  Result := ChildNodes['dPrev'].Text;
end;

procedure TXMLRodo.Set_DPrev(Value: WideString);
begin
  ChildNodes['dPrev'].NodeValue := Value;
end;

function TXMLRodo.Get_Lota: WideString;
begin
  Result := ChildNodes['lota'].Text;
end;

procedure TXMLRodo.Set_Lota(Value: WideString);
begin
  ChildNodes['lota'].NodeValue := Value;
end;

function TXMLRodo.Get_CTRB: IXMLCTRB;
begin
  Result := ChildNodes['CTRB'] as IXMLCTRB;
end;

function TXMLRodo.Get_Occ: IXMLOcc;
begin
  Result := ChildNodes['occ'] as IXMLOcc;
end;

function TXMLRodo.Get_ValePed: IXMLValePed;
begin
  Result := ChildNodes['valePed'] as IXMLValePed;
end;

function TXMLRodo.Get_Veic: IXMLVeicList;
begin
  Result := FVeic;
end;

function TXMLRodo.Get_LacRodo: IXMLLacRodoList;
begin
  Result := FLacRodo;
end;

function TXMLRodo.Get_Moto: IXMLMotoList;
begin
  Result := FMoto;
end;

{ TXMLinfModal }
procedure TXMLinfModal.AfterConstruction;
begin
  RegisterChildNode('rodo', TXMLRodo);
  inherited;
end;

function TXMLinfModal.Get_Rodo: IXMLRodo;
begin
  Result := ChildNodes['rodo'] as IXMLRodo;
end;

{ TXMLCTRB }

function TXMLCTRB.Get_Serie: WideString;
begin
  Result := ChildNodes['serie'].Text;
end;

procedure TXMLCTRB.Set_Serie(Value: WideString);
begin
  ChildNodes['serie'].NodeValue := Value;
end;

function TXMLCTRB.Get_NCTRB: WideString;
begin
  Result := ChildNodes['nCTRB'].Text;
end;

procedure TXMLCTRB.Set_NCTRB(Value: WideString);
begin
  ChildNodes['nCTRB'].NodeValue := Value;
end;

{ TXMLOcc }

procedure TXMLOcc.AfterConstruction;
begin
  RegisterChildNode('emiOcc', TXMLEmiOcc);
  inherited;
end;

function TXMLOcc.Get_Serie: WideString;
begin
  Result := ChildNodes['serie'].Text;
end;

procedure TXMLOcc.Set_Serie(Value: WideString);
begin
  ChildNodes['serie'].NodeValue := Value;
end;

function TXMLOcc.Get_NOcc: WideString;
begin
  Result := ChildNodes['nOcc'].Text;
end;

procedure TXMLOcc.Set_NOcc(Value: WideString);
begin
  ChildNodes['nOcc'].NodeValue := Value;
end;

function TXMLOcc.Get_DEmi: WideString;
begin
  Result := ChildNodes['dEmi'].Text;
end;

procedure TXMLOcc.Set_DEmi(Value: WideString);
begin
  ChildNodes['dEmi'].NodeValue := Value;
end;

function TXMLOcc.Get_EmiOcc: IXMLEmiOcc;
begin
  Result := ChildNodes['emiOcc'] as IXMLEmiOcc;
end;

{ TXMLEmiOcc }

function TXMLEmiOcc.Get_CNPJ: WideString;
begin
  Result := ChildNodes['CNPJ'].Text;
end;

procedure TXMLEmiOcc.Set_CNPJ(Value: WideString);
begin
  ChildNodes['CNPJ'].NodeValue := Value;
end;

function TXMLEmiOcc.Get_CInt: WideString;
begin
  Result := ChildNodes['cInt'].Text;
end;

procedure TXMLEmiOcc.Set_CInt(Value: WideString);
begin
  ChildNodes['cInt'].NodeValue := Value;
end;

function TXMLEmiOcc.Get_IE: WideString;
begin
  Result := ChildNodes['IE'].Text;
end;

procedure TXMLEmiOcc.Set_IE(Value: WideString);
begin
  ChildNodes['IE'].NodeValue := Value;
end;

function TXMLEmiOcc.Get_UF: WideString;
begin
  Result := ChildNodes['UF'].Text;
end;

procedure TXMLEmiOcc.Set_UF(Value: WideString);
begin
  ChildNodes['UF'].NodeValue := Value;
end;

function TXMLEmiOcc.Get_Fone: WideString;
begin
  Result := ChildNodes['fone'].Text;
end;

procedure TXMLEmiOcc.Set_Fone(Value: WideString);
begin
  ChildNodes['fone'].NodeValue := Value;
end;

{ TXMLValePed }

procedure TXMLValePed.AfterConstruction;
begin
  RegisterChildNode('disp', TXMLDisp);
  FDisp := CreateCollection(TXMLDispList, IXMLDisp, 'disp') as IXMLDispList;
  inherited;
end;

function TXMLValePed.Get_NroRE: WideString;
begin
  Result := ChildNodes['nroRE'].Text;
end;

procedure TXMLValePed.Set_NroRE(Value: WideString);
begin
  ChildNodes['nroRE'].NodeValue := Value;
end;

function TXMLValePed.Get_VTValePed: WideString;
begin
  Result := ChildNodes['vTValePed'].Text;
end;

procedure TXMLValePed.Set_VTValePed(Value: WideString);
begin
  ChildNodes['vTValePed'].NodeValue := Value;
end;

function TXMLValePed.Get_RespPg: WideString;
begin
  Result := ChildNodes['respPg'].Text;
end;

procedure TXMLValePed.Set_RespPg(Value: WideString);
begin
  ChildNodes['respPg'].NodeValue := Value;
end;

function TXMLValePed.Get_Disp: IXMLDispList;
begin
  Result := FDisp;
end;

{ TXMLDisp }

function TXMLDisp.Get_TpDisp: WideString;
begin
  Result := ChildNodes['tpDisp'].Text;
end;

procedure TXMLDisp.Set_TpDisp(Value: WideString);
begin
  ChildNodes['tpDisp'].NodeValue := Value;
end;

function TXMLDisp.Get_XEmp: WideString;
begin
  Result := ChildNodes['xEmp'].Text;
end;

procedure TXMLDisp.Set_XEmp(Value: WideString);
begin
  ChildNodes['xEmp'].NodeValue := Value;
end;

function TXMLDisp.Get_DVig: WideString;
begin
  Result := ChildNodes['dVig'].Text;
end;

procedure TXMLDisp.Set_DVig(Value: WideString);
begin
  ChildNodes['dVig'].NodeValue := Value;
end;

function TXMLDisp.Get_NDisp: WideString;
begin
  Result := ChildNodes['nDisp'].Text;
end;

procedure TXMLDisp.Set_NDisp(Value: WideString);
begin
  ChildNodes['nDisp'].NodeValue := Value;
end;

function TXMLDisp.Get_NCompC: WideString;
begin
  Result := ChildNodes['nCompC'].Text;
end;

procedure TXMLDisp.Set_NCompC(Value: WideString);
begin
  ChildNodes['nCompC'].NodeValue := Value;
end;

{ TXMLDispList }

function TXMLDispList.Add: IXMLDisp;
begin
  Result := AddItem(-1) as IXMLDisp;
end;

function TXMLDispList.Insert(const Index: Integer): IXMLDisp;
begin
  Result := AddItem(Index) as IXMLDisp;
end;
function TXMLDispList.Get_Item(Index: Integer): IXMLDisp;
begin
  Result := List[Index] as IXMLDisp;
end;

{ TXMLVeic }

procedure TXMLVeic.AfterConstruction;
begin
  RegisterChildNode('prop', TXMLProp);
  inherited;
end;

function TXMLVeic.Get_CInt: WideString;
begin
  Result := ChildNodes['cInt'].Text;
end;

procedure TXMLVeic.Set_CInt(Value: WideString);
begin
  ChildNodes['cInt'].NodeValue := Value;
end;

function TXMLVeic.Get_RENAVAM: WideString;
begin
  Result := ChildNodes['RENAVAM'].Text;
end;

procedure TXMLVeic.Set_RENAVAM(Value: WideString);
begin
  ChildNodes['RENAVAM'].NodeValue := Value;
end;

function TXMLVeic.Get_Placa: WideString;
begin
  Result := ChildNodes['placa'].Text;
end;

procedure TXMLVeic.Set_Placa(Value: WideString);
begin
  ChildNodes['placa'].NodeValue := Value;
end;

function TXMLVeic.Get_Tara: WideString;
begin
  Result := ChildNodes['tara'].Text;
end;

procedure TXMLVeic.Set_Tara(Value: WideString);
begin
  ChildNodes['tara'].NodeValue := Value;
end;

function TXMLVeic.Get_CapKG: WideString;
begin
  Result := ChildNodes['capKG'].Text;
end;

procedure TXMLVeic.Set_CapKG(Value: WideString);
begin
  ChildNodes['capKG'].NodeValue := Value;
end;

function TXMLVeic.Get_CapM3: WideString;
begin
  Result := ChildNodes['capM3'].Text;
end;

procedure TXMLVeic.Set_CapM3(Value: WideString);
begin
  ChildNodes['capM3'].NodeValue := Value;
end;

function TXMLVeic.Get_TpProp: WideString;
begin
  Result := ChildNodes['tpProp'].Text;
end;

procedure TXMLVeic.Set_TpProp(Value: WideString);
begin
  ChildNodes['tpProp'].NodeValue := Value;
end;

function TXMLVeic.Get_TpVeic: WideString;
begin
  Result := ChildNodes['tpVeic'].Text;
end;

procedure TXMLVeic.Set_TpVeic(Value: WideString);
begin
  ChildNodes['tpVeic'].NodeValue := Value;
end;

function TXMLVeic.Get_TpRod: WideString;
begin
  Result := ChildNodes['tpRod'].Text;
end;

procedure TXMLVeic.Set_TpRod(Value: WideString);
begin
  ChildNodes['tpRod'].NodeValue := Value;
end;

function TXMLVeic.Get_TpCar: WideString;
begin
  Result := ChildNodes['tpCar'].Text;
end;

procedure TXMLVeic.Set_TpCar(Value: WideString);
begin
  ChildNodes['tpCar'].NodeValue := Value;
end;

function TXMLVeic.Get_UF: WideString;
begin
  Result := ChildNodes['UF'].Text;
end;

procedure TXMLVeic.Set_UF(Value: WideString);
begin
  ChildNodes['UF'].NodeValue := Value;
end;

function TXMLVeic.Get_Prop: IXMLProp;
begin
  Result := ChildNodes['prop'] as IXMLProp;
end;

{ TXMLVeicList }

function TXMLVeicList.Add: IXMLVeic;
begin
  Result := AddItem(-1) as IXMLVeic;
end;

function TXMLVeicList.Insert(const Index: Integer): IXMLVeic;
begin
  Result := AddItem(Index) as IXMLVeic;
end;
function TXMLVeicList.Get_Item(Index: Integer): IXMLVeic;
begin
  Result := List[Index] as IXMLVeic;
end;

{ TXMLProp }

function TXMLProp.Get_CPF: WideString;
begin
  Result := ChildNodes['CPF'].Text;
end;

procedure TXMLProp.Set_CPF(Value: WideString);
begin
  ChildNodes['CPF'].NodeValue := Value;
end;

function TXMLProp.Get_CNPJ: WideString;
begin
  Result := ChildNodes['CNPJ'].Text;
end;

procedure TXMLProp.Set_CNPJ(Value: WideString);
begin
  ChildNodes['CNPJ'].NodeValue := Value;
end;

function TXMLProp.Get_RNTRC: WideString;
begin
  Result := ChildNodes['RNTRC'].Text;
end;

procedure TXMLProp.Set_RNTRC(Value: WideString);
begin
  ChildNodes['RNTRC'].NodeValue := Value;
end;

function TXMLProp.Get_XNome: WideString;
begin
  Result := ChildNodes['xNome'].Text;
end;

procedure TXMLProp.Set_XNome(Value: WideString);
begin
  ChildNodes['xNome'].NodeValue := Value;
end;

function TXMLProp.Get_IE: WideString;
begin
  Result := ChildNodes['IE'].Text;
end;

procedure TXMLProp.Set_IE(Value: WideString);
begin
  ChildNodes['IE'].NodeValue := Value;
end;

function TXMLProp.Get_UF: WideString;
begin
  Result := ChildNodes['UF'].Text;
end;

procedure TXMLProp.Set_UF(Value: WideString);
begin
  ChildNodes['UF'].NodeValue := Value;
end;

function TXMLProp.Get_TpProp: WideString;
begin
  Result := ChildNodes['tpProp'].Text;
end;

procedure TXMLProp.Set_TpProp(Value: WideString);
begin
  ChildNodes['tpProp'].NodeValue := Value;
end;

{ TXMLLacRodo }

function TXMLLacRodo.Get_NLacre: WideString;
begin
  Result := ChildNodes['nLacre'].Text;
end;

procedure TXMLLacRodo.Set_NLacre(Value: WideString);
begin
  ChildNodes['nLacre'].NodeValue := Value;
end;

{ TXMLLacRodoList }

function TXMLLacRodoList.Add: IXMLLacRodo;
begin
  Result := AddItem(-1) as IXMLLacRodo;
end;

function TXMLLacRodoList.Insert(const Index: Integer): IXMLLacRodo;
begin
  Result := AddItem(Index) as IXMLLacRodo;
end;
function TXMLLacRodoList.Get_Item(Index: Integer): IXMLLacRodo;
begin
  Result := List[Index] as IXMLLacRodo;
end;

{ TXMLMoto }

function TXMLMoto.Get_XNome: WideString;
begin
  Result := ChildNodes['xNome'].Text;
end;

procedure TXMLMoto.Set_XNome(Value: WideString);
begin
  ChildNodes['xNome'].NodeValue := Value;
end;

function TXMLMoto.Get_CPF: WideString;
begin
  Result := ChildNodes['CPF'].Text;
end;

procedure TXMLMoto.Set_CPF(Value: WideString);
begin
  ChildNodes['CPF'].NodeValue := Value;
end;

{ TXMLMotoList }

function TXMLMotoList.Add: IXMLMoto;
begin
  Result := AddItem(-1) as IXMLMoto;
end;

function TXMLMotoList.Insert(const Index: Integer): IXMLMoto;
begin
  Result := AddItem(Index) as IXMLMoto;
end;
function TXMLMotoList.Get_Item(Index: Integer): IXMLMoto;
begin
  Result := List[Index] as IXMLMoto;
end;

{ TXMLAereo }

procedure TXMLAereo.AfterConstruction;
begin
  RegisterChildNode('tarifa', TXMLTarifa);
  inherited;
end;

function TXMLAereo.Get_NMinu: WideString;
begin
  Result := ChildNodes['nMinu'].Text;
end;

procedure TXMLAereo.Set_NMinu(Value: WideString);
begin
  ChildNodes['nMinu'].NodeValue := Value;
end;

function TXMLAereo.Get_NOCA: WideString;
begin
  Result := ChildNodes['nOCA'].Text;
end;

procedure TXMLAereo.Set_NOCA(Value: WideString);
begin
  ChildNodes['nOCA'].NodeValue := Value;
end;

function TXMLAereo.Get_DPrev: WideString;
begin
  Result := ChildNodes['dPrev'].Text;
end;

procedure TXMLAereo.Set_DPrev(Value: WideString);
begin
  ChildNodes['dPrev'].NodeValue := Value;
end;

function TXMLAereo.Get_XLAgEmi: WideString;
begin
  Result := ChildNodes['xLAgEmi'].Text;
end;

procedure TXMLAereo.Set_XLAgEmi(Value: WideString);
begin
  ChildNodes['xLAgEmi'].NodeValue := Value;
end;

function TXMLAereo.Get_CIATA: WideString;
begin
  Result := ChildNodes['cIATA'].Text;
end;

procedure TXMLAereo.Set_CIATA(Value: WideString);
begin
  ChildNodes['cIATA'].NodeValue := Value;
end;

function TXMLAereo.Get_Tarifa: IXMLTarifa;
begin
  Result := ChildNodes['tarifa'] as IXMLTarifa;
end;

{ TXMLTarifa }

function TXMLTarifa.Get_Trecho: WideString;
begin
  Result := ChildNodes['trecho'].Text;
end;

procedure TXMLTarifa.Set_Trecho(Value: WideString);
begin
  ChildNodes['trecho'].NodeValue := Value;
end;

function TXMLTarifa.Get_CL: WideString;
begin
  Result := ChildNodes['CL'].Text;
end;

procedure TXMLTarifa.Set_CL(Value: WideString);
begin
  ChildNodes['CL'].NodeValue := Value;
end;

function TXMLTarifa.Get_CTar: WideString;
begin
  Result := ChildNodes['cTar'].Text;
end;

procedure TXMLTarifa.Set_CTar(Value: WideString);
begin
  ChildNodes['cTar'].NodeValue := Value;
end;

function TXMLTarifa.Get_VTar: WideString;
begin
  Result := ChildNodes['vTar'].Text;
end;

procedure TXMLTarifa.Set_VTar(Value: WideString);
begin
  ChildNodes['vTar'].NodeValue := Value;
end;

{ TXMLAquav }

procedure TXMLAquav.AfterConstruction;
begin
  RegisterChildNode('lacre', TXMLLacre);
  FLacre := CreateCollection(TXMLLacreList, IXMLLacre, 'lacre') as IXMLLacreList;
  inherited;
end;

function TXMLAquav.Get_VPrest: WideString;
begin
  Result := ChildNodes['vPrest'].Text;
end;

procedure TXMLAquav.Set_VPrest(Value: WideString);
begin
  ChildNodes['vPrest'].NodeValue := Value;
end;

function TXMLAquav.Get_VAFRMM: WideString;
begin
  Result := ChildNodes['vAFRMM'].Text;
end;

procedure TXMLAquav.Set_VAFRMM(Value: WideString);
begin
  ChildNodes['vAFRMM'].NodeValue := Value;
end;

function TXMLAquav.Get_NBooking: WideString;
begin
  Result := ChildNodes['nBooking'].Text;
end;

procedure TXMLAquav.Set_NBooking(Value: WideString);
begin
  ChildNodes['nBooking'].NodeValue := Value;
end;

function TXMLAquav.Get_NCtrl: WideString;
begin
  Result := ChildNodes['nCtrl'].Text;
end;

procedure TXMLAquav.Set_NCtrl(Value: WideString);
begin
  ChildNodes['nCtrl'].NodeValue := Value;
end;

function TXMLAquav.Get_XNavio: WideString;
begin
  Result := ChildNodes['xNavio'].Text;
end;

procedure TXMLAquav.Set_XNavio(Value: WideString);
begin
  ChildNodes['xNavio'].NodeValue := Value;
end;

function TXMLAquav.Get_NViag: WideString;
begin
  Result := ChildNodes['nViag'].Text;
end;

procedure TXMLAquav.Set_NViag(Value: WideString);
begin
  ChildNodes['nViag'].NodeValue := Value;
end;

function TXMLAquav.Get_Direc: WideString;
begin
  Result := ChildNodes['direc'].Text;
end;

procedure TXMLAquav.Set_Direc(Value: WideString);
begin
  ChildNodes['direc'].NodeValue := Value;
end;

function TXMLAquav.Get_PrtEmb: WideString;
begin
  Result := ChildNodes['prtEmb'].Text;
end;

procedure TXMLAquav.Set_PrtEmb(Value: WideString);
begin
  ChildNodes['prtEmb'].NodeValue := Value;
end;

function TXMLAquav.Get_PrtTrans: WideString;
begin
  Result := ChildNodes['prtTrans'].Text;
end;

procedure TXMLAquav.Set_PrtTrans(Value: WideString);
begin
  ChildNodes['prtTrans'].NodeValue := Value;
end;

function TXMLAquav.Get_PrtDest: WideString;
begin
  Result := ChildNodes['prtDest'].Text;
end;

procedure TXMLAquav.Set_PrtDest(Value: WideString);
begin
  ChildNodes['prtDest'].NodeValue := Value;
end;

function TXMLAquav.Get_TpNav: WideString;
begin
  Result := ChildNodes['tpNav'].Text;
end;

procedure TXMLAquav.Set_TpNav(Value: WideString);
begin
  ChildNodes['tpNav'].NodeValue := Value;
end;

function TXMLAquav.Get_Irin: WideString;
begin
  Result := ChildNodes['irin'].Text;
end;

procedure TXMLAquav.Set_Irin(Value: WideString);
begin
  ChildNodes['irin'].NodeValue := Value;
end;

function TXMLAquav.Get_Lacre: IXMLLacreList;
begin
  Result := FLacre;
end;

{ TXMLLacre }

function TXMLLacre.Get_NLacre: WideString;
begin
  Result := ChildNodes['nLacre'].Text;
end;

procedure TXMLLacre.Set_NLacre(Value: WideString);
begin
  ChildNodes['nLacre'].NodeValue := Value;
end;

{ TXMLLacreList }

function TXMLLacreList.Add: IXMLLacre;
begin
  Result := AddItem(-1) as IXMLLacre;
end;

function TXMLLacreList.Insert(const Index: Integer): IXMLLacre;
begin
  Result := AddItem(Index) as IXMLLacre;
end;
function TXMLLacreList.Get_Item(Index: Integer): IXMLLacre;
begin
  Result := List[Index] as IXMLLacre;
end;

{ TXMLFerrov }

procedure TXMLFerrov.AfterConstruction;
begin
  RegisterChildNode('ferroSub', TXMLFerroSub);
  RegisterChildNode('DCL', TXMLDCL);
  RegisterChildNode('detVag', TXMLDetVag);
  FDCL := CreateCollection(TXMLDCLList, IXMLDCL, 'DCL') as IXMLDCLList;
  FDetVag := CreateCollection(TXMLDetVagList, IXMLDetVag, 'detVag') as IXMLDetVagList;
  inherited;
end;

function TXMLFerrov.Get_TpTraf: WideString;
begin
  Result := ChildNodes['tpTraf'].Text;
end;

procedure TXMLFerrov.Set_TpTraf(Value: WideString);
begin
  ChildNodes['tpTraf'].NodeValue := Value;
end;

function TXMLFerrov.Get_Fluxo: WideString;
begin
  Result := ChildNodes['fluxo'].Text;
end;

procedure TXMLFerrov.Set_Fluxo(Value: WideString);
begin
  ChildNodes['fluxo'].NodeValue := Value;
end;

function TXMLFerrov.Get_IdTrem: WideString;
begin
  Result := ChildNodes['idTrem'].Text;
end;

procedure TXMLFerrov.Set_IdTrem(Value: WideString);
begin
  ChildNodes['idTrem'].NodeValue := Value;
end;

function TXMLFerrov.Get_VFrete: WideString;
begin
  Result := ChildNodes['vFrete'].Text;
end;

procedure TXMLFerrov.Set_VFrete(Value: WideString);
begin
  ChildNodes['vFrete'].NodeValue := Value;
end;

function TXMLFerrov.Get_FerroSub: IXMLFerroSub;
begin
  Result := ChildNodes['ferroSub'] as IXMLFerroSub;
end;

function TXMLFerrov.Get_DCL: IXMLDCLList;
begin
  Result := FDCL;
end;

function TXMLFerrov.Get_DetVag: IXMLDetVagList;
begin
  Result := FDetVag;
end;

{ TXMLFerroSub }

procedure TXMLFerroSub.AfterConstruction;
begin
  RegisterChildNode('enderFerro', TXMLTEnderFer);
  inherited;
end;

function TXMLFerroSub.Get_CNPJ: WideString;
begin
  Result := ChildNodes['CNPJ'].Text;
end;

procedure TXMLFerroSub.Set_CNPJ(Value: WideString);
begin
  ChildNodes['CNPJ'].NodeValue := Value;
end;

function TXMLFerroSub.Get_CInt: WideString;
begin
  Result := ChildNodes['cInt'].Text;
end;

procedure TXMLFerroSub.Set_CInt(Value: WideString);
begin
  ChildNodes['cInt'].NodeValue := Value;
end;

function TXMLFerroSub.Get_IE: WideString;
begin
  Result := ChildNodes['IE'].Text;
end;

procedure TXMLFerroSub.Set_IE(Value: WideString);
begin
  ChildNodes['IE'].NodeValue := Value;
end;

function TXMLFerroSub.Get_XNome: WideString;
begin
  Result := ChildNodes['xNome'].Text;
end;

procedure TXMLFerroSub.Set_XNome(Value: WideString);
begin
  ChildNodes['xNome'].NodeValue := Value;
end;

function TXMLFerroSub.Get_EnderFerro: IXMLTEnderFer;
begin
  Result := ChildNodes['enderFerro'] as IXMLTEnderFer;
end;

{ TXMLTEnderFer }

function TXMLTEnderFer.Get_XLgr: WideString;
begin
  Result := ChildNodes['xLgr'].Text;
end;

procedure TXMLTEnderFer.Set_XLgr(Value: WideString);
begin
  ChildNodes['xLgr'].NodeValue := Value;
end;

function TXMLTEnderFer.Get_Nro: WideString;
begin
  Result := ChildNodes['nro'].Text;
end;

procedure TXMLTEnderFer.Set_Nro(Value: WideString);
begin
  ChildNodes['nro'].NodeValue := Value;
end;

function TXMLTEnderFer.Get_XCpl: WideString;
begin
  Result := ChildNodes['xCpl'].Text;
end;

procedure TXMLTEnderFer.Set_XCpl(Value: WideString);
begin
  ChildNodes['xCpl'].NodeValue := Value;
end;

function TXMLTEnderFer.Get_XBairro: WideString;
begin
  Result := ChildNodes['xBairro'].Text;
end;

procedure TXMLTEnderFer.Set_XBairro(Value: WideString);
begin
  ChildNodes['xBairro'].NodeValue := Value;
end;

function TXMLTEnderFer.Get_CMun: WideString;
begin
  Result := ChildNodes['cMun'].Text;
end;

procedure TXMLTEnderFer.Set_CMun(Value: WideString);
begin
  ChildNodes['cMun'].NodeValue := Value;
end;

function TXMLTEnderFer.Get_XMun: WideString;
begin
  Result := ChildNodes['xMun'].Text;
end;

procedure TXMLTEnderFer.Set_XMun(Value: WideString);
begin
  ChildNodes['xMun'].NodeValue := Value;
end;

function TXMLTEnderFer.Get_CEP: WideString;
begin
  Result := ChildNodes['CEP'].Text;
end;

procedure TXMLTEnderFer.Set_CEP(Value: WideString);
begin
  ChildNodes['CEP'].NodeValue := Value;
end;

function TXMLTEnderFer.Get_UF: WideString;
begin
  Result := ChildNodes['UF'].Text;
end;

procedure TXMLTEnderFer.Set_UF(Value: WideString);
begin
  ChildNodes['UF'].NodeValue := Value;
end;

{ TXMLDCL }

procedure TXMLDCL.AfterConstruction;
begin
  RegisterChildNode('detVagDCL', TXMLDetVagDCL);
  FDetVagDCL := CreateCollection(TXMLDetVagDCLList, IXMLDetVagDCL, 'detVagDCL') as IXMLDetVagDCLList;
  inherited;
end;

function TXMLDCL.Get_Serie: WideString;
begin
  Result := ChildNodes['serie'].Text;
end;

procedure TXMLDCL.Set_Serie(Value: WideString);
begin
  ChildNodes['serie'].NodeValue := Value;
end;

function TXMLDCL.Get_NDCL: WideString;
begin
  Result := ChildNodes['nDCL'].Text;
end;

procedure TXMLDCL.Set_NDCL(Value: WideString);
begin
  ChildNodes['nDCL'].NodeValue := Value;
end;

function TXMLDCL.Get_DEmi: WideString;
begin
  Result := ChildNodes['dEmi'].Text;
end;

procedure TXMLDCL.Set_DEmi(Value: WideString);
begin
  ChildNodes['dEmi'].NodeValue := Value;
end;

function TXMLDCL.Get_QVag: WideString;
begin
  Result := ChildNodes['qVag'].Text;
end;

procedure TXMLDCL.Set_QVag(Value: WideString);
begin
  ChildNodes['qVag'].NodeValue := Value;
end;

function TXMLDCL.Get_PCalc: WideString;
begin
  Result := ChildNodes['pCalc'].Text;
end;

procedure TXMLDCL.Set_PCalc(Value: WideString);
begin
  ChildNodes['pCalc'].NodeValue := Value;
end;

function TXMLDCL.Get_VTar: WideString;
begin
  Result := ChildNodes['vTar'].Text;
end;

procedure TXMLDCL.Set_VTar(Value: WideString);
begin
  ChildNodes['vTar'].NodeValue := Value;
end;

function TXMLDCL.Get_VFrete: WideString;
begin
  Result := ChildNodes['vFrete'].Text;
end;

procedure TXMLDCL.Set_VFrete(Value: WideString);
begin
  ChildNodes['vFrete'].NodeValue := Value;
end;

function TXMLDCL.Get_VSAcess: WideString;
begin
  Result := ChildNodes['vSAcess'].Text;
end;

procedure TXMLDCL.Set_VSAcess(Value: WideString);
begin
  ChildNodes['vSAcess'].NodeValue := Value;
end;

function TXMLDCL.Get_VTServ: WideString;
begin
  Result := ChildNodes['vTServ'].Text;
end;

procedure TXMLDCL.Set_VTServ(Value: WideString);
begin
  ChildNodes['vTServ'].NodeValue := Value;
end;

function TXMLDCL.Get_IdTrem: WideString;
begin
  Result := ChildNodes['idTrem'].Text;
end;

procedure TXMLDCL.Set_IdTrem(Value: WideString);
begin
  ChildNodes['idTrem'].NodeValue := Value;
end;

function TXMLDCL.Get_DetVagDCL: IXMLDetVagDCLList;
begin
  Result := FDetVagDCL;
end;

{ TXMLDCLList }

function TXMLDCLList.Add: IXMLDCL;
begin
  Result := AddItem(-1) as IXMLDCL;
end;

function TXMLDCLList.Insert(const Index: Integer): IXMLDCL;
begin
  Result := AddItem(Index) as IXMLDCL;
end;
function TXMLDCLList.Get_Item(Index: Integer): IXMLDCL;
begin
  Result := List[Index] as IXMLDCL;
end;

{ TXMLDetVagDCL }

procedure TXMLDetVagDCL.AfterConstruction;
begin
  RegisterChildNode('lacDetVagDCL', TXMLLacDetVagDCL);
  RegisterChildNode('contDCL', TXMLContDCL);
  FLacDetVagDCL := CreateCollection(TXMLLacDetVagDCLList, IXMLLacDetVagDCL, 'lacDetVagDCL') as IXMLLacDetVagDCLList;
  FContDCL := CreateCollection(TXMLContDCLList, IXMLContDCL, 'contDCL') as IXMLContDCLList;
  inherited;
end;

function TXMLDetVagDCL.Get_NVag: WideString;
begin
  Result := ChildNodes['nVag'].Text;
end;

procedure TXMLDetVagDCL.Set_NVag(Value: WideString);
begin
  ChildNodes['nVag'].NodeValue := Value;
end;

function TXMLDetVagDCL.Get_Cap: WideString;
begin
  Result := ChildNodes['cap'].Text;
end;

procedure TXMLDetVagDCL.Set_Cap(Value: WideString);
begin
  ChildNodes['cap'].NodeValue := Value;
end;

function TXMLDetVagDCL.Get_TpVag: WideString;
begin
  Result := ChildNodes['tpVag'].Text;
end;

procedure TXMLDetVagDCL.Set_TpVag(Value: WideString);
begin
  ChildNodes['tpVag'].NodeValue := Value;
end;

function TXMLDetVagDCL.Get_PesoR: WideString;
begin
  Result := ChildNodes['pesoR'].Text;
end;

procedure TXMLDetVagDCL.Set_PesoR(Value: WideString);
begin
  ChildNodes['pesoR'].NodeValue := Value;
end;

function TXMLDetVagDCL.Get_PesoBC: WideString;
begin
  Result := ChildNodes['pesoBC'].Text;
end;

procedure TXMLDetVagDCL.Set_PesoBC(Value: WideString);
begin
  ChildNodes['pesoBC'].NodeValue := Value;
end;

function TXMLDetVagDCL.Get_LacDetVagDCL: IXMLLacDetVagDCLList;
begin
  Result := FLacDetVagDCL;
end;

function TXMLDetVagDCL.Get_ContDCL: IXMLContDCLList;
begin
  Result := FContDCL;
end;

{ TXMLDetVagDCLList }

function TXMLDetVagDCLList.Add: IXMLDetVagDCL;
begin
  Result := AddItem(-1) as IXMLDetVagDCL;
end;

function TXMLDetVagDCLList.Insert(const Index: Integer): IXMLDetVagDCL;
begin
  Result := AddItem(Index) as IXMLDetVagDCL;
end;
function TXMLDetVagDCLList.Get_Item(Index: Integer): IXMLDetVagDCL;
begin
  Result := List[Index] as IXMLDetVagDCL;
end;

{ TXMLLacDetVagDCL }

function TXMLLacDetVagDCL.Get_NLacre: WideString;
begin
  Result := ChildNodes['nLacre'].Text;
end;

procedure TXMLLacDetVagDCL.Set_NLacre(Value: WideString);
begin
  ChildNodes['nLacre'].NodeValue := Value;
end;

{ TXMLLacDetVagDCLList }

function TXMLLacDetVagDCLList.Add: IXMLLacDetVagDCL;
begin
  Result := AddItem(-1) as IXMLLacDetVagDCL;
end;

function TXMLLacDetVagDCLList.Insert(const Index: Integer): IXMLLacDetVagDCL;
begin
  Result := AddItem(Index) as IXMLLacDetVagDCL;
end;
function TXMLLacDetVagDCLList.Get_Item(Index: Integer): IXMLLacDetVagDCL;
begin
  Result := List[Index] as IXMLLacDetVagDCL;
end;

{ TXMLContDCL }

function TXMLContDCL.Get_NCont: WideString;
begin
  Result := ChildNodes['nCont'].Text;
end;

procedure TXMLContDCL.Set_NCont(Value: WideString);
begin
  ChildNodes['nCont'].NodeValue := Value;
end;

function TXMLContDCL.Get_DPrev: WideString;
begin
  Result := ChildNodes['dPrev'].Text;
end;

procedure TXMLContDCL.Set_DPrev(Value: WideString);
begin
  ChildNodes['dPrev'].NodeValue := Value;
end;

{ TXMLContDCLList }

function TXMLContDCLList.Add: IXMLContDCL;
begin
  Result := AddItem(-1) as IXMLContDCL;
end;

function TXMLContDCLList.Insert(const Index: Integer): IXMLContDCL;
begin
  Result := AddItem(Index) as IXMLContDCL;
end;
function TXMLContDCLList.Get_Item(Index: Integer): IXMLContDCL;
begin
  Result := List[Index] as IXMLContDCL;
end;

{ TXMLDetVag }

procedure TXMLDetVag.AfterConstruction;
begin
  RegisterChildNode('lacDetVag', TXMLLacDetVag);
  RegisterChildNode('contVag', TXMLContVag);
  FLacDetVag := CreateCollection(TXMLLacDetVagList, IXMLLacDetVag, 'lacDetVag') as IXMLLacDetVagList;
  FContVag := CreateCollection(TXMLContVagList, IXMLContVag, 'contVag') as IXMLContVagList;
  inherited;
end;

function TXMLDetVag.Get_NVag: WideString;
begin
  Result := ChildNodes['nVag'].Text;
end;

procedure TXMLDetVag.Set_NVag(Value: WideString);
begin
  ChildNodes['nVag'].NodeValue := Value;
end;

function TXMLDetVag.Get_Cap: WideString;
begin
  Result := ChildNodes['cap'].Text;
end;

procedure TXMLDetVag.Set_Cap(Value: WideString);
begin
  ChildNodes['cap'].NodeValue := Value;
end;

function TXMLDetVag.Get_TpVag: WideString;
begin
  Result := ChildNodes['tpVag'].Text;
end;

procedure TXMLDetVag.Set_TpVag(Value: WideString);
begin
  ChildNodes['tpVag'].NodeValue := Value;
end;

function TXMLDetVag.Get_PesoR: WideString;
begin
  Result := ChildNodes['pesoR'].Text;
end;

procedure TXMLDetVag.Set_PesoR(Value: WideString);
begin
  ChildNodes['pesoR'].NodeValue := Value;
end;

function TXMLDetVag.Get_PesoBC: WideString;
begin
  Result := ChildNodes['pesoBC'].Text;
end;

procedure TXMLDetVag.Set_PesoBC(Value: WideString);
begin
  ChildNodes['pesoBC'].NodeValue := Value;
end;

function TXMLDetVag.Get_LacDetVag: IXMLLacDetVagList;
begin
  Result := FLacDetVag;
end;

function TXMLDetVag.Get_ContVag: IXMLContVagList;
begin
  Result := FContVag;
end;

{ TXMLDetVagList }

function TXMLDetVagList.Add: IXMLDetVag;
begin
  Result := AddItem(-1) as IXMLDetVag;
end;

function TXMLDetVagList.Insert(const Index: Integer): IXMLDetVag;
begin
  Result := AddItem(Index) as IXMLDetVag;
end;
function TXMLDetVagList.Get_Item(Index: Integer): IXMLDetVag;
begin
  Result := List[Index] as IXMLDetVag;
end;

{ TXMLLacDetVag }

function TXMLLacDetVag.Get_NLacre: WideString;
begin
  Result := ChildNodes['nLacre'].Text;
end;

procedure TXMLLacDetVag.Set_NLacre(Value: WideString);
begin
  ChildNodes['nLacre'].NodeValue := Value;
end;

{ TXMLLacDetVagList }

function TXMLLacDetVagList.Add: IXMLLacDetVag;
begin
  Result := AddItem(-1) as IXMLLacDetVag;
end;

function TXMLLacDetVagList.Insert(const Index: Integer): IXMLLacDetVag;
begin
  Result := AddItem(Index) as IXMLLacDetVag;
end;
function TXMLLacDetVagList.Get_Item(Index: Integer): IXMLLacDetVag;
begin
  Result := List[Index] as IXMLLacDetVag;
end;

{ TXMLContVag }

function TXMLContVag.Get_NCont: WideString;
begin
  Result := ChildNodes['nCont'].Text;
end;

procedure TXMLContVag.Set_NCont(Value: WideString);
begin
  ChildNodes['nCont'].NodeValue := Value;
end;

function TXMLContVag.Get_DPrev: WideString;
begin
  Result := ChildNodes['dPrev'].Text;
end;

procedure TXMLContVag.Set_DPrev(Value: WideString);
begin
  ChildNodes['dPrev'].NodeValue := Value;
end;

{ TXMLContVagList }

function TXMLContVagList.Add: IXMLContVag;
begin
  Result := AddItem(-1) as IXMLContVag;
end;

function TXMLContVagList.Insert(const Index: Integer): IXMLContVag;
begin
  Result := AddItem(Index) as IXMLContVag;
end;
function TXMLContVagList.Get_Item(Index: Integer): IXMLContVag;
begin
  Result := List[Index] as IXMLContVag;
end;

{ TXMLDuto }

function TXMLDuto.Get_VTar: WideString;
begin
  Result := ChildNodes['vTar'].Text;
end;

procedure TXMLDuto.Set_VTar(Value: WideString);
begin
  ChildNodes['vTar'].NodeValue := Value;
end;

{ TXMLPeri }

function TXMLPeri.Get_NONU: WideString;
begin
  Result := ChildNodes['nONU'].Text;
end;

procedure TXMLPeri.Set_NONU(Value: WideString);
begin
  ChildNodes['nONU'].NodeValue := Value;
end;

function TXMLPeri.Get_XNomeAE: WideString;
begin
  Result := ChildNodes['xNomeAE'].Text;
end;

procedure TXMLPeri.Set_XNomeAE(Value: WideString);
begin
  ChildNodes['xNomeAE'].NodeValue := Value;
end;

function TXMLPeri.Get_XClaRisco: WideString;
begin
  Result := ChildNodes['xClaRisco'].Text;
end;

procedure TXMLPeri.Set_XClaRisco(Value: WideString);
begin
  ChildNodes['xClaRisco'].NodeValue := Value;
end;

function TXMLPeri.Get_GrEmb: WideString;
begin
  Result := ChildNodes['grEmb'].Text;
end;

procedure TXMLPeri.Set_GrEmb(Value: WideString);
begin
  ChildNodes['grEmb'].NodeValue := Value;
end;

function TXMLPeri.Get_QTotProd: WideString;
begin
  Result := ChildNodes['qTotProd'].Text;
end;

procedure TXMLPeri.Set_QTotProd(Value: WideString);
begin
  ChildNodes['qTotProd'].NodeValue := Value;
end;

function TXMLPeri.Get_QVolTipo: WideString;
begin
  Result := ChildNodes['qVolTipo'].Text;
end;

procedure TXMLPeri.Set_QVolTipo(Value: WideString);
begin
  ChildNodes['qVolTipo'].NodeValue := Value;
end;

function TXMLPeri.Get_PontoFulgor: WideString;
begin
  Result := ChildNodes['pontoFulgor'].Text;
end;

procedure TXMLPeri.Set_PontoFulgor(Value: WideString);
begin
  ChildNodes['pontoFulgor'].NodeValue := Value;
end;

{ TXMLPeriList }

function TXMLPeriList.Add: IXMLPeri;
begin
  Result := AddItem(-1) as IXMLPeri;
end;

function TXMLPeriList.Insert(const Index: Integer): IXMLPeri;
begin
  Result := AddItem(Index) as IXMLPeri;
end;
function TXMLPeriList.Get_Item(Index: Integer): IXMLPeri;
begin
  Result := List[Index] as IXMLPeri;
end;

{ TXMLVeicNovos }

function TXMLVeicNovos.Get_Chassi: WideString;
begin
  Result := ChildNodes['chassi'].Text;
end;

procedure TXMLVeicNovos.Set_Chassi(Value: WideString);
begin
  ChildNodes['chassi'].NodeValue := Value;
end;

function TXMLVeicNovos.Get_CCor: WideString;
begin
  Result := ChildNodes['cCor'].Text;
end;

procedure TXMLVeicNovos.Set_CCor(Value: WideString);
begin
  ChildNodes['cCor'].NodeValue := Value;
end;

function TXMLVeicNovos.Get_XCor: WideString;
begin
  Result := ChildNodes['xCor'].Text;
end;

procedure TXMLVeicNovos.Set_XCor(Value: WideString);
begin
  ChildNodes['xCor'].NodeValue := Value;
end;

function TXMLVeicNovos.Get_CMod: WideString;
begin
  Result := ChildNodes['cMod'].Text;
end;

procedure TXMLVeicNovos.Set_CMod(Value: WideString);
begin
  ChildNodes['cMod'].NodeValue := Value;
end;

function TXMLVeicNovos.Get_VUnit: WideString;
begin
  Result := ChildNodes['vUnit'].Text;
end;

procedure TXMLVeicNovos.Set_VUnit(Value: WideString);
begin
  ChildNodes['vUnit'].NodeValue := Value;
end;

function TXMLVeicNovos.Get_VFrete: WideString;
begin
  Result := ChildNodes['vFrete'].Text;
end;

procedure TXMLVeicNovos.Set_VFrete(Value: WideString);
begin
  ChildNodes['vFrete'].NodeValue := Value;
end;

{ TXMLVeicNovosList }

function TXMLVeicNovosList.Add: IXMLVeicNovos;
begin
  Result := AddItem(-1) as IXMLVeicNovos;
end;

function TXMLVeicNovosList.Insert(const Index: Integer): IXMLVeicNovos;
begin
  Result := AddItem(Index) as IXMLVeicNovos;
end;
function TXMLVeicNovosList.Get_Item(Index: Integer): IXMLVeicNovos;
begin
  Result := List[Index] as IXMLVeicNovos;
end;

{ TXMLInfCteSub }

procedure TXMLInfCteSub.AfterConstruction;
begin
  RegisterChildNode('tomaICMS', TXMLTomaICMS);
  RegisterChildNode('tomaNaoICMS', TXMLTomaNaoICMS);
  inherited;
end;

function TXMLInfCteSub.Get_ChCte: WideString;
begin
  Result := ChildNodes['chCte'].Text;
end;

procedure TXMLInfCteSub.Set_ChCte(Value: WideString);
begin
  ChildNodes['chCte'].NodeValue := Value;
end;

function TXMLInfCteSub.Get_TomaICMS: IXMLTomaICMS;
begin
  Result := ChildNodes['tomaICMS'] as IXMLTomaICMS;
end;

function TXMLInfCteSub.Get_TomaNaoICMS: IXMLTomaNaoICMS;
begin
  Result := ChildNodes['tomaNaoICMS'] as IXMLTomaNaoICMS;
end;

{ TXMLTomaICMS }

procedure TXMLTomaICMS.AfterConstruction;
begin
  RegisterChildNode('refNF', TXMLRefNF);
  inherited;
end;

function TXMLTomaICMS.Get_RefNFe: WideString;
begin
  Result := ChildNodes['refNFe'].Text;
end;

procedure TXMLTomaICMS.Set_RefNFe(Value: WideString);
begin
  ChildNodes['refNFe'].NodeValue := Value;
end;

function TXMLTomaICMS.Get_RefNF: IXMLRefNF;
begin
  Result := ChildNodes['refNF'] as IXMLRefNF;
end;

function TXMLTomaICMS.Get_RefCte: WideString;
begin
  Result := ChildNodes['refCte'].Text;
end;

procedure TXMLTomaICMS.Set_RefCte(Value: WideString);
begin
  ChildNodes['refCte'].NodeValue := Value;
end;

{ TXMLRefNF }

function TXMLRefNF.Get_CNPJ: WideString;
begin
  Result := ChildNodes['CNPJ'].Text;
end;

procedure TXMLRefNF.Set_CNPJ(Value: WideString);
begin
  ChildNodes['CNPJ'].NodeValue := Value;
end;

function TXMLRefNF.Get_Mod_: WideString;
begin
  Result := ChildNodes['mod'].Text;
end;

procedure TXMLRefNF.Set_Mod_(Value: WideString);
begin
  ChildNodes['mod'].NodeValue := Value;
end;

function TXMLRefNF.Get_Serie: WideString;
begin
  Result := ChildNodes['serie'].Text;
end;

procedure TXMLRefNF.Set_Serie(Value: WideString);
begin
  ChildNodes['serie'].NodeValue := Value;
end;

function TXMLRefNF.Get_Subserie: WideString;
begin
  Result := ChildNodes['subserie'].Text;
end;

procedure TXMLRefNF.Set_Subserie(Value: WideString);
begin
  ChildNodes['subserie'].NodeValue := Value;
end;

function TXMLRefNF.Get_Nro: WideString;
begin
  Result := ChildNodes['nro'].Text;
end;

procedure TXMLRefNF.Set_Nro(Value: WideString);
begin
  ChildNodes['nro'].NodeValue := Value;
end;

function TXMLRefNF.Get_Valor: WideString;
begin
  Result := ChildNodes['valor'].Text;
end;

procedure TXMLRefNF.Set_Valor(Value: WideString);
begin
  ChildNodes['valor'].NodeValue := Value;
end;

function TXMLRefNF.Get_DEmi: WideString;
begin
  Result := ChildNodes['dEmi'].Text;
end;

procedure TXMLRefNF.Set_DEmi(Value: WideString);
begin
  ChildNodes['dEmi'].NodeValue := Value;
end;

{ TXMLTomaNaoICMS }

function TXMLTomaNaoICMS.Get_RefCteAnu: WideString;
begin
  Result := ChildNodes['refCteAnu'].Text;
end;

procedure TXMLTomaNaoICMS.Set_RefCteAnu(Value: WideString);
begin
  ChildNodes['refCteAnu'].NodeValue := Value;
end;

{ TXMLInfCteComp }

procedure TXMLInfCteComp.AfterConstruction;
begin
  RegisterChildNode('vPresComp', TXMLVPresComp);
  RegisterChildNode('impComp', TXMLImpComp);
  inherited;
end;

function TXMLInfCteComp.Get_Chave: WideString;
begin
  Result := ChildNodes['chave'].Text;
end;

procedure TXMLInfCteComp.Set_Chave(Value: WideString);
begin
  ChildNodes['chave'].NodeValue := Value;
end;

function TXMLInfCteComp.Get_VPresComp: IXMLVPresComp;
begin
  Result := ChildNodes['vPresComp'] as IXMLVPresComp;
end;

function TXMLInfCteComp.Get_ImpComp: IXMLImpComp;
begin
  Result := ChildNodes['impComp'] as IXMLImpComp;
end;

{ TXMLVPresComp }

procedure TXMLVPresComp.AfterConstruction;
begin
  RegisterChildNode('compComp', TXMLCompComp);
  FCompComp := CreateCollection(TXMLCompCompList, IXMLCompComp, 'compComp') as IXMLCompCompList;
  inherited;
end;

function TXMLVPresComp.Get_VTPrest: WideString;
begin
  Result := ChildNodes['vTPrest'].Text;
end;

procedure TXMLVPresComp.Set_VTPrest(Value: WideString);
begin
  ChildNodes['vTPrest'].NodeValue := Value;
end;

function TXMLVPresComp.Get_CompComp: IXMLCompCompList;
begin
  Result := FCompComp;
end;

{ TXMLCompComp }

function TXMLCompComp.Get_XNome: WideString;
begin
  Result := ChildNodes['xNome'].Text;
end;

procedure TXMLCompComp.Set_XNome(Value: WideString);
begin
  ChildNodes['xNome'].NodeValue := Value;
end;

function TXMLCompComp.Get_VComp: WideString;
begin
  Result := ChildNodes['vComp'].Text;
end;

procedure TXMLCompComp.Set_VComp(Value: WideString);
begin
  ChildNodes['vComp'].NodeValue := Value;
end;

{ TXMLCompCompList }

function TXMLCompCompList.Add: IXMLCompComp;
begin
  Result := AddItem(-1) as IXMLCompComp;
end;

function TXMLCompCompList.Insert(const Index: Integer): IXMLCompComp;
begin
  Result := AddItem(Index) as IXMLCompComp;
end;
function TXMLCompCompList.Get_Item(Index: Integer): IXMLCompComp;
begin
  Result := List[Index] as IXMLCompComp;
end;

{ TXMLImpComp }

procedure TXMLImpComp.AfterConstruction;
begin
  RegisterChildNode('ICMSComp', TXMLTCST);
  inherited;
end;

function TXMLImpComp.Get_ICMSComp: IXMLTCST;
begin
  Result := ChildNodes['ICMSComp'] as IXMLTCST;
end;

function TXMLImpComp.Get_InfAdFisco: WideString;
begin
  Result := ChildNodes['infAdFisco'].Text;
end;

procedure TXMLImpComp.Set_InfAdFisco(Value: WideString);
begin
  ChildNodes['infAdFisco'].NodeValue := Value;
end;

{ TXMLInfCteAnu }

function TXMLInfCteAnu.Get_ChCte: WideString;
begin
  Result := ChildNodes['chCte'].Text;
end;

procedure TXMLInfCteAnu.Set_ChCte(Value: WideString);
begin
  ChildNodes['chCte'].NodeValue := Value;
end;

function TXMLInfCteAnu.Get_DEmi: WideString;
begin
  Result := ChildNodes['dEmi'].Text;
end;

procedure TXMLInfCteAnu.Set_DEmi(Value: WideString);
begin
  ChildNodes['dEmi'].NodeValue := Value;
end;

{ TXMLSignatureType }

procedure TXMLSignatureType.AfterConstruction;
begin
  RegisterChildNode('SignedInfo', TXMLSignedInfoType);
  RegisterChildNode('SignatureValue', TXMLSignatureValueType);
  RegisterChildNode('KeyInfo', TXMLKeyInfoType);
  inherited;
end;

function TXMLSignatureType.Get_Id: WideString;
begin
  Result := AttributeNodes['Id'].Text;
end;

procedure TXMLSignatureType.Set_Id(Value: WideString);
begin
  SetAttribute('Id', Value);
end;

function TXMLSignatureType.Get_SignedInfo: IXMLSignedInfoType;
begin
  Result := ChildNodes['SignedInfo'] as IXMLSignedInfoType;
end;

function TXMLSignatureType.Get_SignatureValue: IXMLSignatureValueType;
begin
  Result := ChildNodes['SignatureValue'] as IXMLSignatureValueType;
end;

function TXMLSignatureType.Get_KeyInfo: IXMLKeyInfoType;
begin
  Result := ChildNodes['KeyInfo'] as IXMLKeyInfoType;
end;

{ TXMLSignedInfoType }

procedure TXMLSignedInfoType.AfterConstruction;
begin
  RegisterChildNode('CanonicalizationMethod', TXMLCanonicalizationMethod);
  RegisterChildNode('SignatureMethod', TXMLSignatureMethod);
  RegisterChildNode('Reference', TXMLReferenceType);
  inherited;
end;

function TXMLSignedInfoType.Get_Id: WideString;
begin
  Result := AttributeNodes['Id'].Text;
end;

procedure TXMLSignedInfoType.Set_Id(Value: WideString);
begin
  SetAttribute('Id', Value);
end;

function TXMLSignedInfoType.Get_CanonicalizationMethod: IXMLCanonicalizationMethod;
begin
  Result := ChildNodes['CanonicalizationMethod'] as IXMLCanonicalizationMethod;
end;

function TXMLSignedInfoType.Get_SignatureMethod: IXMLSignatureMethod;
begin
  Result := ChildNodes['SignatureMethod'] as IXMLSignatureMethod;
end;

function TXMLSignedInfoType.Get_Reference: IXMLReferenceType;
begin
  Result := ChildNodes['Reference'] as IXMLReferenceType;
end;

{ TXMLCanonicalizationMethod }

function TXMLCanonicalizationMethod.Get_Algorithm: WideString;
begin
  Result := AttributeNodes['Algorithm'].Text;
end;

procedure TXMLCanonicalizationMethod.Set_Algorithm(Value: WideString);
begin
  SetAttribute('Algorithm', Value);
end;

{ TXMLSignatureMethod }

function TXMLSignatureMethod.Get_Algorithm: WideString;
begin
  Result := AttributeNodes['Algorithm'].Text;
end;

procedure TXMLSignatureMethod.Set_Algorithm(Value: WideString);
begin
  SetAttribute('Algorithm', Value);
end;

{ TXMLReferenceType }

procedure TXMLReferenceType.AfterConstruction;
begin
  RegisterChildNode('Transforms', TXMLTransformsType);
  RegisterChildNode('DigestMethod', TXMLDigestMethod);
  inherited;
end;

function TXMLReferenceType.Get_Id: WideString;
begin
  Result := AttributeNodes['Id'].Text;
end;

procedure TXMLReferenceType.Set_Id(Value: WideString);
begin
  SetAttribute('Id', Value);
end;

function TXMLReferenceType.Get_URI: WideString;
begin
  Result := AttributeNodes['URI'].Text;
end;

procedure TXMLReferenceType.Set_URI(Value: WideString);
begin
  SetAttribute('URI', Value);
end;

function TXMLReferenceType.Get_Type_: WideString;
begin
  Result := AttributeNodes['Type'].Text;
end;

procedure TXMLReferenceType.Set_Type_(Value: WideString);
begin
  SetAttribute('Type', Value);
end;

function TXMLReferenceType.Get_Transforms: IXMLTransformsType;
begin
  Result := ChildNodes['Transforms'] as IXMLTransformsType;
end;

function TXMLReferenceType.Get_DigestMethod: IXMLDigestMethod;
begin
  Result := ChildNodes['DigestMethod'] as IXMLDigestMethod;
end;

function TXMLReferenceType.Get_DigestValue: WideString;
begin
  Result := ChildNodes['DigestValue'].Text;
end;

procedure TXMLReferenceType.Set_DigestValue(Value: WideString);
begin
  ChildNodes['DigestValue'].NodeValue := Value;
end;

{ TXMLTransformsType }

procedure TXMLTransformsType.AfterConstruction;
begin
  RegisterChildNode('Transform', TXMLTransformType);
  ItemTag := 'Transform';
  ItemInterface := IXMLTransformType;
  inherited;
end;

function TXMLTransformsType.Get_Transform(Index: Integer): IXMLTransformType;
begin
  Result := List[Index] as IXMLTransformType;
end;

function TXMLTransformsType.Add: IXMLTransformType;
begin
  Result := AddItem(-1) as IXMLTransformType;
end;

function TXMLTransformsType.Insert(const Index: Integer): IXMLTransformType;
begin
  Result := AddItem(Index) as IXMLTransformType;
end;

{ TXMLTransformType }

procedure TXMLTransformType.AfterConstruction;
begin
  ItemTag := 'XPath';
  ItemInterface := IXMLNode;
  inherited;
end;

function TXMLTransformType.Get_Algorithm: WideString;
begin
  Result := AttributeNodes['Algorithm'].Text;
end;

procedure TXMLTransformType.Set_Algorithm(Value: WideString);
begin
  SetAttribute('Algorithm', Value);
end;

function TXMLTransformType.Get_XPath(Index: Integer): WideString;
begin
  Result := List[Index].Text;
end;

function TXMLTransformType.Add(const XPath: WideString): IXMLNode;
begin
  Result := AddItem(-1);
  Result.NodeValue := XPath;
end;

function TXMLTransformType.Insert(const Index: Integer; const XPath: WideString): IXMLNode;
begin
  Result := AddItem(Index);
  Result.NodeValue := XPath;
end;

{ TXMLDigestMethod }

function TXMLDigestMethod.Get_Algorithm: WideString;
begin
  Result := AttributeNodes['Algorithm'].Text;
end;

procedure TXMLDigestMethod.Set_Algorithm(Value: WideString);
begin
  SetAttribute('Algorithm', Value);
end;

{ TXMLSignatureValueType }

function TXMLSignatureValueType.Get_Id: WideString;
begin
  Result := AttributeNodes['Id'].Text;
end;

procedure TXMLSignatureValueType.Set_Id(Value: WideString);
begin
  SetAttribute('Id', Value);
end;

{ TXMLKeyInfoType }

procedure TXMLKeyInfoType.AfterConstruction;
begin
  RegisterChildNode('X509Data', TXMLX509DataType);
  inherited;
end;

function TXMLKeyInfoType.Get_Id: WideString;
begin
  Result := AttributeNodes['Id'].Text;
end;

procedure TXMLKeyInfoType.Set_Id(Value: WideString);
begin
  SetAttribute('Id', Value);
end;

function TXMLKeyInfoType.Get_X509Data: IXMLX509DataType;
begin
  Result := ChildNodes['X509Data'] as IXMLX509DataType;
end;

{ TXMLX509DataType }

function TXMLX509DataType.Get_X509Certificate: WideString;
begin
  Result := ChildNodes['X509Certificate'].Text;
end;

procedure TXMLX509DataType.Set_X509Certificate(Value: WideString);
begin
  ChildNodes['X509Certificate'].NodeValue := Value;
end;

end.