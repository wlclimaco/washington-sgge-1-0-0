unit BrvDacte;

interface

uses Classes, DBClient, RpRave, RpSystem, RpRenderPDF, Windows, SysUtils, DB, cte_v104, Forms,
     RpDefine;


type
  TDefaultDest = (DdPrint, DdPDF, DdPreview);
  TBrvDacte = class(TComponent)
  private
    { Private declarations }
    FDataSet  : TClientDataset;
    FDsPatDac : String; //path file DACTE
    FDsPatLog : String; //path file Logo
    FDsPatTmp : String; //path temp
    FDefDest  : TDefaultDest;
    FNrCopia  : Integer;

    FRvProjec  : TRvProject;
    FRvSystem  : TRvSystem;
    FRvRenPDF  : TRvRenderPDF;

    function GetTempDir : string;
    function ImprimeDacte(pDsPatXML : String; pDsPatLog : String;
                          pDsDACCon : String; pNrProtoc : String) : PChar;

    procedure MoverDadosTomador(pMyCTE    : IXMLTCTe;   pRvProjec  : TRvProject;
                                pDsTomado : AnsiString; pNmTomado  : AnsiString;
                                pNmCidTom : AnsiString; pUfTomado  : AnsiString;
                                pCpTomado : AnsiString; pEdTomado  : AnsiString;
                                pNmPaisTo : AnsiString; pNrCnpj    : AnsiString;
                                pIeTomado : AnsiString; pNrFonTom  : AnsiString;
                                pCdTomado : AnsiString);

    procedure SituacaoTributaria103(pMyCTE    : IXMLTCTe; var pDsCst  : String;
                                var pVrBasCal : String;   var pPcIcms : String;
                                var pVrIcms   : String);

    procedure SituacaoTributaria104(pMyCTE    : IXMLTCTe; var pDsCst    : String;
                                var pVrBasCal : String;   var pPcIcms   : String;
                                var pVrIcms   : String;   var pPcRedBas : String);

    function  RetornaUnidade(pNrPosica : String) : String;
    function  ResponsavelSeguro(pNrRespon : String) : String;
    procedure ReturnConfig;

  protected
    { Protected declarations }
  public
    { Public declarations }
    constructor Create(AOwner: TComponent); override;
    destructor  Destroy;                    override;
    procedure   GenerateDacte;
  published
    { Published declarations }
    property DataSet        : TClientDataSet read FDataSet  write FDataSet;
    property PathFile       : String         read FDsPatDac write FDsPatDac;
    property PathLogo       : String         read FDsPatLog write FDsPatLog;
    property DefaultDest    : TDefaultDest   read FDefDest  write FDefDest;
    property CopiesNumber   : Integer        read FNrCopia  write FNrCopia;
  end;

procedure Register;

implementation

//uses

procedure Register;
begin
      RegisterComponents('Bravo Relatorio', [TBrvDacte]);
end;

constructor TBrvDacte.Create(AOwner: TComponent);
begin
      inherited Create(AOwner);
end;

destructor TBrvDacte.Destroy;
begin
      inherited  destroy;
end;

procedure TBrvDacte.GenerateDacte;
var lDsPatXML : String;
    lTxXml    : TStrings;
    lDsXml    : String;
begin
      try
          lDsXml := FDataSet.FieldByName('DsXmlCte').AsString;
          Delete(lDsXml, 1, Pos('<CTe', lDsXml) -1);
          Delete(lDsXml, Pos('</CTe>',  lDsXml) + 6, 1000);

          lTxXml      := TStringList.Create;
          lTxXml.Text := lDsXml;
          FDsPatTmp   := GetTempDir;
          lDsPatXML   := FDsPatTmp + 'CT-e' + FDataSet.FieldByName('CdCtrc').AsString + '.XML';

          //TMemoField(FDataSet.FieldByName('DsXmlCte')).SaveToFile(lDsPatXML);
          lTxXml.SaveToFile(lDsPatXML);

          ImprimeDacte(lDsPatXML, FDsPatLog, '', FDataSet.FieldByName('DsProCTe').AsString);
      finally
          if FileExists(lDsPatXML) then
          begin
                DeleteFile(lDsPatXML);
          end;
      end;
end;

function TBrvDacte.ImprimeDacte(pDsPatXML : String; pDsPatLog : String;
                                pDsDACCon : String; pNrProtoc : String) : PChar;
var lNrLinIte  : Integer;
    lRvProjec  : TRvProject;
    lRvSystem  : TRvSystem;
    lRvRenPDF  : TRvRenderPDF;

    lMyCTE     : IXMLTCTe;
    lDsValAux  : String;
    lDtEmissa  : String;
    lNrIndice  : Integer;
    lNrIndAux  : Integer;

    lDsCst     : String;
    lVrBasCal  : String;
    lPcIcms    : String;
    lVrIcms    : String;
    lPcRedBas  : String;
begin
      try
          RPDefine.DataID := IntToStr(Application.Handle);
          //Carrega as informacoes do arquivo XML da NFe que serao usadas para preencher o DACTE

          lMyCTE := NewCTe;
          lMyCTE := LoadCTe(pDsPatXML);

          //Instancia o objeto que conterá o relatório do RAVE
          lRvProjec        := TRvProject.Create(nil);
          lRvSystem        := TRvSystem.Create(nil);
          lRvRenPDF        := TRvRenderPDF.Create(nil);
          lRvProjec.Engine := lRvSystem;

          lRvProjec.Close;

          //Arquivos DACTE_Paisagem.rav e DACTE_Retrato.rav devem estar no mesmo diretório da dll e do executável
          lRvProjec.ProjectFile := ExtractFilePath(Application.ExeName) + 'DACTE_Retrato.rav';

          if not FileExists(lRvProjec.ProjectFile) then
          begin
                raise Exception.Create('Erro');
          end;
          
          lNrLinIte             := 28;//Limite atual de 28 itens (produtos) para o DACTE Retrato

          lRvProjec.SetParam('chave_acesso', lMyCTE.InfCTe.Id);       //TODO: Teste
          lRvProjec.SetParam('Barcode', Copy(lMyCTE.InfCTe.Id, 4, Length(lMyCTE.InfCTe.Id)));

          lRvProjec.SetParam('Razao', lMyCTE.InfCTe.Emit.XNome); //Nome

          lRvProjec.SetParam('End',  lMyCTE.InfCTe.Emit.EnderEmit.XLgr   + ' ' +  // Logradouro
                                     lMyCTE.InfCTe.Emit.EnderEmit.Nro  + ' ' +  // Número
                                     lMyCTE.InfCTe.Emit.EnderEmit.XCpl + ' ' +  // Complemento
                                     lMyCTE.InfCTe.Emit.EnderEmit.XBairro);     // Bairro

          lRvProjec.SetParam('Mun',      lMyCTE.InfCTe.Emit.EnderEmit.XMun); // Nome Municipio
          lRvProjec.SetParam('UF',       lMyCTE.InfCTe.Emit.EnderEmit.UF);   // UF
          lRvProjec.SetParam('IE',       'Inscrição estadual: ' + lMyCTE.InfCTe.Emit.IE);  // Inscrição estadual
          lRvProjec.SetParam('CNPJ',     'CNPJ: '     + lMyCTE.InfCTe.Emit.CNPJ);  // CNPJ
          lRvProjec.SetParam('Telefone', 'Telefone: ' + lMyCTE.InfCTe.Emit.EnderEmit.fone);  // Numero do telefone
          lRvProjec.SetParam('CEP',      'CEP: '      + lMyCTE.InfCTe.Emit.EnderEmit.CEP);  // CEP

          lRvProjec.SetParam('pFoto',         pDsPatLog); // tamanho da imagem: 259/158       ok
          lRvProjec.SetParam('pFoto2',        pDsPatLog); // tamanho da imagem: 259/158       ok
          lRvProjec.SetParam('NUM_PROTOCOLO', pNrProtoc); // Numero de protocolo

//          lRvProjec.SetParam('CCT',   lMyCTE.InfCTe.Ide.CCT);  // Codigo de barra
          lRvProjec.SetParam('CCT',   lMyCTE.InfCTe.Ide.RefCTE);  // Codigo de barra

          lRvProjec.SetParam('CFOP',  lMyCTE.InfCTe.Ide.CFOP + ' - ' +
                                     lMyCTE.InfCTe.Ide.NatOp);// CFOP

          lRvProjec.SetParam('Mod_',  lMyCTE.InfCTe.Ide.Mod_);  // Modelo do Documento Fiscal
          lRvProjec.SetParam('Serie', lMyCTE.InfCTe.Ide.Serie);  // Série do Documento Fiscal
          lRvProjec.SetParam('NCT',   lMyCTE.InfCTe.Ide.NCT);  // Número do Documento Fiscal

          lDtEmissa := lMyCTE.InfCTe.Ide.DhEmi;

          lDtEmissa := Copy(lDtEmissa, 9, 2) + '/' + Copy(lDtEmissa, 6, 2) + '/' +
                       Copy(lDtEmissa, 1, 4) + ' ' + Copy(lDtEmissa, 12, 8);

          lRvProjec.SetParam('DhEmi', lDtEmissa);  // Data e Hora de emissão

          if lMyCTE.InfCTe.Ide.TpCTe = '0' then  // Tipo do Conhecimento
          begin
                lRvProjec.SetParam('TpCTe', 'NORMAL');
          end else
          begin
                if lMyCTE.InfCTe.Ide.TpCTe = '1' then
                begin
                      lRvProjec.SetParam('TpCTe', 'COMPLEMENTO DE VALORES');
                end else
                begin
                      if lMyCTE.InfCTe.Ide.TpCTe = '2' then
                      begin
                            lRvProjec.SetParam('TpCTe', 'ANULAÇÃO DE VALORES');
                      end else
                      begin
                            if lMyCTE.InfCTe.Ide.TpCTe = '3' then
                            begin
                                  lRvProjec.SetParam('TpCTe', 'SUBSTITUTO');
                            end;
                      end;
                end;
          end;

          if lMyCTE.InfCTe.Ide.Modal = '01' then   // Modal
          begin
                lRvProjec.SetParam('Modal', 'Rodoviário');
          end else
          begin
                if lMyCTE.InfCTe.Ide.Modal = '02' then
                begin
                      lRvProjec.SetParam('Modal', 'Aéreo');
                end else
                begin
                      if lMyCTE.InfCTe.Ide.Modal = '03' then
                      begin
                            lRvProjec.SetParam('Modal', 'Aquaviário');
                      end else
                      begin
                            if lMyCTE.InfCTe.Ide.Modal = '04' then
                            begin
                                  lRvProjec.SetParam('Modal', 'Ferroviário');
                            end else
                            begin
                                  lRvProjec.SetParam('Modal', 'Dutoviário');

                            end;
                      end;
                end;
          end;

          lRvProjec.SetParam('TpServ',  lMyCTE.InfCTe.Ide.TpServ);


          if lMyCTE.InfCTe.Ide.TpServ = '0' then  // Tipo do Serviço
          begin
                lRvProjec.SetParam('TpServ', 'NORMAL');
          end else
          begin
                if lMyCTE.InfCTe.Ide.TpServ = '1' then
                begin
                      lRvProjec.SetParam('TpServ', 'SUBCONTRATAÇÃO');
                end else
                begin
                      if lMyCTE.InfCTe.Ide.TpServ = '2' then
                      begin
                            lRvProjec.SetParam('TpServ', 'REDESPACHO');
                      end else
                      begin
                            if lMyCTE.InfCTe.Ide.TpServ = '3' then
                            begin
                                  lRvProjec.SetParam('TpServ', 'REDESPACHO INTERMEDIÁRIO');
                            end;
                      end;
                end;
          end;


          lRvProjec.SetParam('XMunIni', lMyCTE.InfCTe.Ide.XMunIni);  // Nome do Município do inicio da prestação
          lRvProjec.SetParam('UFIni', lMyCTE.InfCTe.Ide.UFIni);  // Sigla do Estado inicio da prestação

          lRvProjec.SetParam('XMunFim', lMyCTE.InfCTe.Ide.XMunFim);  // Nome do Município do término da prestação
          lRvProjec.SetParam('UFFim', lMyCTE.InfCTe.Ide.UFFim);  // Sigla do Estado término da prestação

          lRvProjec.SetParam('ForPag',  lMyCTE.InfCTe.Ide.ForPag);  // Forma de pagamento

          if lMyCTE.InfCTe.Ide.ForPag = '0' then  // Forma de pagamento
          begin
                lRvProjec.SetParam('ForPag',  'PAGO');
          end else
          begin
                if lMyCTE.InfCTe.Ide.ForPag = '1' then  // Forma de pagamento
                begin
                      lRvProjec.SetParam('ForPag',  'A PAGAR');
                end else
                begin
                      if lMyCTE.InfCTe.Ide.ForPag = '2' then  // Forma de pagamento
                      begin
                            lRvProjec.SetParam('ForPag',  'OUTROS');
                      end;
                end;
          end;


          lRvProjec.SetParam('ISUF_Dest', lMyCTE.InfCTe.dest.ISUF);  // Inscrição na SUFRAMA

          if lMyCTE.InfCTe.Ide.Toma03.Toma = '0' then // Tomador do serviço
          begin
                MoverDadosTomador(lMyCTE, lRvProjec,
                                 'REMETENTE',
                                  lMyCTE.InfCTe.Rem.XNome,
                                  lMyCTE.InfCte.Rem.EnderReme.XMun,
                                  lMyCTE.InfCte.Rem.EnderReme.UF,
                                  lMyCTE.InfCte.Rem.EnderReme.CEP,
                                  lMyCTE.InfCte.Rem.EnderReme.XLgr + ' ' +
                                  lMyCTE.InfCte.Rem.EnderReme.Nro,
                                  lMyCTE.InfCte.Rem.EnderReme.XPais,
                                  lMyCTE.InfCTe.Rem.CNPJ + lMyCTE.InfCTe.Rem.CPF,
                                  lMyCTE.InfCTe.Rem.IE,
                                  lMyCTE.InfCTe.Rem.Fone,
                                  FDataSet.FieldByName('CdRemete').AsString);


          end else
          begin
                if lMyCTE.InfCTe.Ide.Toma03.Toma = '1' then
                begin
                      MoverDadosTomador(lMyCTE, lRvProjec,
                                       'EXPEDIDOR',
                                        lMyCTE.InfCTe.Exped.XNome,
                                        lMyCTE.InfCte.Exped.EnderExped.XMun,
                                        lMyCTE.InfCte.Exped.EnderExped.UF,
                                        lMyCTE.InfCte.Exped.EnderExped.CEP,
                                        lMyCTE.InfCte.Exped.EnderExped.XLgr + ' ' +
                                        lMyCTE.InfCte.Exped.EnderExped.Nro,
                                        lMyCTE.InfCte.Exped.EnderExped.XPais,
                                        lMyCTE.InfCTe.Exped.CNPJ + lMyCTE.InfCTe.Exped.CPF,
                                        lMyCTE.InfCTe.Exped.IE,
                                        lMyCTE.InfCTe.Exped.Fone,
                                        FDataSet.FieldByName('CdRemete').AsString);
                end else
                begin
                      if lMyCTE.InfCTe.Ide.Toma03.Toma = '2' then
                      begin
                            MoverDadosTomador(lMyCTE, lRvProjec,
                                              'RECEBEDOR',
                                              lMyCTE.InfCTe.Receb.XNome,
                                              lMyCTE.InfCte.Receb.EnderReceb.XMun,
                                              lMyCTE.InfCte.Receb.EnderReceb.UF,
                                              lMyCTE.InfCte.Receb.EnderReceb.CEP,

                                              lMyCTE.InfCte.Receb.EnderReceb.XLgr + ' ' +
                                              lMyCTE.InfCte.Receb.EnderReceb.Nro,

                                              lMyCTE.InfCte.Receb.EnderReceb.XPais,

                                              lMyCTE.InfCTe.Receb.CNPJ +
                                              lMyCTE.InfCTe.Receb.CPF,

                                              lMyCTE.InfCTe.Receb.IE,
                                              lMyCTE.InfCTe.Receb.Fone,
                                              FDataSet.FieldByName('CdDestin').AsString);
                      end else
                      begin
                            if lMyCTE.InfCTe.Ide.Toma03.Toma = '3' then
                            begin
                                  MoverDadosTomador(
                                              lMyCTE, lRvProjec,
                                              'DESTINATARIO',
                                              lMyCTE.InfCTe.Dest.XNome,
                                              lMyCTE.InfCte.Dest.EnderDest.XMun,
                                              lMyCTE.InfCte.Dest.EnderDest.UF,
                                              lMyCTE.InfCte.Dest.EnderDest.CEP,

                                              lMyCTE.InfCte.Dest.EnderDest.XLgr + ' ' +
                                              lMyCTE.InfCte.Dest.EnderDest.Nro,

                                              lMyCTE.InfCte.Dest.EnderDest.XPais,

                                              lMyCTE.InfCTe.Dest.CNPJ +
                                              lMyCTE.InfCTe.Dest.CPF,

                                              lMyCTE.InfCTe.Dest.IE,
                                              lMyCTE.InfCTe.Dest.Fone,
                                              FDataSet.FieldByName('CdDestin').AsString);
                            end else
                            begin
                                  if lMyCTE.InfCTe.Ide.Toma4.Toma = '4' then
                                  begin
                                        MoverDadosTomador(
                                             lMyCTE, lRvProjec,
                                             'OUTROS',
                                             lMyCTE.InfCTe.Ide.Toma4.XNome,
                                             lMyCTE.InfCTe.Ide.Toma4.EnderToma.XMun,
                                             lMyCTE.InfCTe.Ide.Toma4.EnderToma.UF,
                                             lMyCTE.InfCTe.Ide.Toma4.EnderToma.CEP,

                                             lMyCTE.InfCTe.Ide.Toma4.EnderToma.XLgr + ' ' +
                                             lMyCTE.InfCTe.Ide.Toma4.EnderToma.Nro,

                                             lMyCTE.InfCTe.Ide.Toma4.EnderToma.XPais,

                                             lMyCTE.InfCTe.Ide.Toma4.CNPJ +
                                             lMyCTE.InfCTe.Ide.Toma4.CPF,

                                             lMyCTE.InfCTe.Ide.Toma4.IE,
                                             lMyCTE.InfCTe.Ide.Toma4.Fone,
                                             FDataSet.FieldByName('CdConsig').AsString);
                                  end;
                            end;
                      end;
                end;
          end;

          lRvProjec.SetParam('CPF_CNPJ_Rem', lMyCTE.InfCTe.Rem.CNPJ +
                                            lMyCTE.InfCTe.Rem.CPF ); // CNPJ ou CPF

          lRvProjec.SetParam('IE_Rem',    lMyCTE.InfCTe.Rem.IE);  // Inscrição Estadual
          lRvProjec.SetParam('XNome_Rem', lMyCTE.InfCTe.Rem.XNome);  // Razão social
          lRvProjec.SetParam('Fone_Rem',  lMyCTE.InfCTe.Rem.Fone);  // telefone
          lRvProjec.SetParam('XLgr_Rem',  lMyCTE.InfCte.Rem.EnderReme.XLgr + ' ' +                                                        // Logradouro
                                           lMyCTE.InfCte.Rem.EnderReme.Nro  + ' ' +                                                        // Número
                                           lMyCTE.InfCte.Rem.EnderReme.XCpl);
          lRvProjec.SetParam('XBaiRem',   lMyCTE.InfCte.Rem.EnderReme.XBairro);// Bairro
          lRvProjec.SetParam('XMun_Rem',  lMyCTE.InfCte.Rem.EnderReme.XMun);   // Nome Município
          lRvProjec.SetParam('CEP_Rem',   lMyCTE.InfCte.Rem.EnderReme.CEP);    // CEP
          lRvProjec.SetParam('XPais_Rem', lMyCTE.InfCte.Rem.EnderReme.XPais);  // Nome do País
          lRvProjec.SetParam('CdRemete',  FDataSet.FieldByName('CdRemete').AsString);

          lRvProjec.SetParam('CPF_CNPJ_Exped', lMyCTE.InfCte.Exped.CNPJ +
                                              lMyCTE.InfCte.Exped.CPF);  // CNPJ ou CPF

          lRvProjec.SetParam('IE_Exped',    lMyCTE.InfCte.Exped.IE);  // IE
          lRvProjec.SetParam('XNome_Exped', lMyCTE.InfCte.Exped.XNome);  // Nome / razão social
          lRvProjec.SetParam('Fone_Exped',  lMyCTE.InfCte.Exped.Fone);  // Telefone
          lRvProjec.SetParam('XLgr_Exped',  lMyCTE.InfCte.Exped.EnderExped.XLgr  + ' ' +                                                 // Logradouro
                                             lMyCTE.InfCte.Exped.EnderExped.Nro + ' ' +                                                 // Número
                                             lMyCTE.InfCte.Exped.EnderExped.XCpl);
          lRvProjec.SetParam('XBaiExp',     lMyCTE.InfCte.Exped.EnderExped.XBairro);// Bairro
          lRvProjec.SetParam('XMun_Exped',  lMyCTE.InfCte.Exped.EnderExped.XMun);  // Nome Município
          lRvProjec.SetParam('CEP_Exped',   lMyCTE.InfCte.Exped.EnderExped.CEP);  // CEP
          lRvProjec.SetParam('XPais_Exped', lMyCTE.InfCte.Exped.EnderExped.XPais);  // Nome do País


          lRvProjec.SetParam('CPF_CNPJ_Receb', lMyCTE.InfCte.Receb.CNPJ +
                                              lMyCTE.InfCte.Receb.CPF);  // CNPJ ou  CPF

          lRvProjec.SetParam('IE_Receb',    lMyCTE.InfCte.Receb.IE);  // IE
          lRvProjec.SetParam('XNome_Receb', lMyCTE.InfCte.Receb.XNome);  // Nome / razão social
          lRvProjec.SetParam('Fone_Receb',  lMyCTE.InfCte.Receb.Fone);  // Telefone
          lRvProjec.SetParam('XLgr_Receb',  lMyCTE.InfCte.Receb.EnderReceb.XLgr + ' ' +                                              // Logradouro
                                             lMyCTE.InfCte.Receb.EnderReceb.Nro  + ' ' +                                              // Número
                                             lMyCTE.InfCte.Receb.EnderReceb.XCpl);
          lRvProjec.SetParam('XBaiRec',     lMyCTE.InfCte.Receb.EnderReceb.XBairro);// Bairro
          lRvProjec.SetParam('XMun_Receb',  lMyCTE.InfCte.Receb.EnderReceb.XMun);  // Nome Município
          lRvProjec.SetParam('CEP_Receb',   lMyCTE.InfCte.Receb.EnderReceb.CEP);  // CEP
          lRvProjec.SetParam('XPais_Receb', lMyCTE.InfCte.Receb.EnderReceb.XPais);  // Nome do País

          lRvProjec.SetParam('CPF_CNPJ_Dest', lMyCTE.infCTE.Dest.CNPJ +
                                             lMyCTE.InfCTe.Dest.CPF);  // CNPJ ou  // CPF

          lRvProjec.SetParam('IE_Dest',    lMyCTE.InfCTe.Dest.IE);  // IE
          lRvProjec.SetParam('XNome_Dest', lMyCTE.InfCTe.Dest.XNome);  // Nome / razão social
          lRvProjec.SetParam('Fone_Dest',  lMyCTE.InfCTe.Dest.Fone);  // Telefone

          lRvProjec.SetParam('XLgr_Dest',  lMyCTE.InfCTe.Dest.EnderDest.XLgr + ' ' + // Logradouro
                                           lMyCTE.InfCTe.Dest.EnderDest.Nro  + ' ' + // Número
                                           lMyCTE.InfCTe.Dest.EnderDest.XCpl);  // Bairro

          lRvProjec.SetParam('XBaiDes',    lMyCTE.InfCte.Dest.EnderDest.XBairro);// Bairro
          lRvProjec.SetParam('XMun_Dest',  lMyCTE.InfCTe.Dest.EnderDest.XMun);  // Nome Município
          lRvProjec.SetParam('CEP_Dest',   lMyCTE.InfCTe.Dest.EnderDest.CEP);  // CEP
          lRvProjec.SetParam('XPais_Dest', lMyCTE.InfCTe.Dest.EnderDest.XPais);  // Nome do País
          lRvProjec.SetParam('CdDestin',   FDataSet.FieldByName('CdDestin').AsString);

          if lMyCTE.InfCte.Rem.InfNF.Count > 0 then
          begin
                lRvProjec.SetParam('VST_Rem', lMyCTE.InfCTe.Rem.InfNF.Items[0].VST);  // Valor total do ICMS ST
          end;

          lDsValAux := StringReplace(lMyCTE.InfCTe.VPrest.VTPrest, '.', ',', [rfReplaceAll]);
          lDsValAux := FormatFloat('#,###,##0.00', StrToFloatDef(lDsValAux, 0));

          lRvProjec.SetParam('VTPrest', lDsValAux);  // Valor total da prestação do serviço

          lDsValAux := StringReplace(lMyCTE.InfCTe.VPrest.VRec, '.', ',', [rfReplaceAll]);
          lDsValAux := FormatFloat('#,###,##0.00', StrToFloatDef(lDsValAux, 0));
          lRvProjec.SetParam('VRec', lDsValAux);  // Valor a Receber

          if lMyCTE.InfCTe.VPrest.Comp.Count > 0 then
          begin
                for lNrIndice := 0 to lMyCTE.InfCTe.VPrest.Comp.Count-1 do
                begin
                      lRvProjec.SetParam('COMP_NOM_SERV' + IntToStr(lNrIndice),
                                lMyCTE.InfCTe.VPrest.Comp.Items[lNrIndice].XNome);// Nome do componente

                      lDsValAux := StringReplace(
                                 lMyCTE.InfCTe.VPrest.Comp.Items[lNrIndice].VComp, '.', ',',
                                                                          [rfReplaceAll]);

                      lDsValAux := FormatFloat('#,###,##0.00', StrToFloatDef(lDsValAux, 0));

                      // Valor do componente
                      lRvProjec.SetParam('COMP_VAL_SERV' + IntToStr(lNrIndice), lDsValAux);

                      if lNrIndice >= 12 then
                      begin
                            Break;
                      end;
                end;
          end;

          lNrIndAux := 0;
          for lNrIndice := 0 to lMyCTE.InfCTe.Rem.InfNFe.Count - 1 do
          begin
                lRvProjec.SetParam('TpDoc_Rem' + IntToStr(lNrIndAux), 'NF-e');  // Tipo documento NF-e
                lRvProjec.SetParam('Chave_Rem' + IntToStr(lNrIndAux), 'CHAVE: ' +
                                     lMyCTE.InfCTe.Rem.InfNFe.Items[lNrIndice].Chave);// Chave de acesso da NF-e

                Inc(lNrIndAux);

                lRvProjec.SetParam('TpDoc_Rem' + IntToStr(lNrIndAux), '');
                lRvProjec.SetParam('Chave_Rem' + IntToStr(lNrIndAux), 'DANFE: ' +
                                   Copy(lMyCTE.InfCTe.Rem.InfNFe.Items[lNrIndice].Chave, 26, 9));
                Inc(lNrIndAux);
          end;

          for lNrIndice := 0 to lMyCTE.InfCTe.Rem.InfNF.Count - 1 do
          begin
                lRvProjec.SetParam('TpDoc_Rem' + IntToStr(lNrIndAux), 'NF');  // Tipo documento NF
                lRvProjec.SetParam('Chave_Rem' + IntToStr(lNrIndAux),
                                     lMyCTE.InfCTe.Rem.CNPJ + StringOfChar(' ', 18) +
                                     lMyCTE.InfCTe.Rem.InfNF.Items[lNrIndice].Serie + '/' +
                                     lMyCTE.InfCTe.Rem.InfNF.Items[lNrIndice].NDoc);  // Série / Chave de acesso da NF
                Inc(lNrIndAux);
          end;

          for lNrIndice := 0 to lMyCTE.InfCTe.Rem.InfOutros.Count - 1 do
          begin
                lDsValAux := 'OUTROS';

                if lMyCTE.InfCTe.Rem.InfOutros.Items[lNrIndice].TpDoc = '00' then
                begin
                      lDsValAux := 'DECLARAÇÃO';
                end;


                lRvProjec.SetParam('TpDoc_Rem' + IntToStr(lNrIndAux), lDsValAux);  // Tipo documento outros
                lRvProjec.SetParam('Chave_Rem' + IntToStr(lNrIndAux),
                                        lMyCTE.InfCTe.Rem.CNPJ + StringOfChar(' ', 18) +
                                        lMyCTE.InfCTe.Rem.InfOutros.Items[lNrIndice].NDoc);  // Numero do documento
                Inc(lNrIndAux);
          end;

          if lMyCTE.InfCte.Versao = '1.03' then
          begin
                SituacaoTributaria103(lMyCTE, lDsCst, lVrBasCal, lPcIcms, lVrIcms);
                lRvProjec.SetParam('RED_BC_CALC', lMyCTE.InfCTe.Imp.ICMS.CST20.PRedBC);  // Percentual de redução da BC do ICMS
          end else
          begin
                SituacaoTributaria104(lMyCTE, lDsCst, lVrBasCal, lPcIcms, lVrIcms, lPcRedBas);
                lPcRedBas := FormatFloat('#,###,##0.00', StrToFloatDef(lPcRedBas, 0));
                lRvProjec.SetParam('RED_BC_CALC', lPcRedBas);  // Percentual de redução da BC do ICMS
          end;

          lVrBasCal := FormatFloat('#,###,##0.00', StrToFloatDef(lVrBasCal, 0));
          lPcIcms   := FormatFloat('#,###,##0.00', StrToFloatDef(lPcIcms, 0));
          lVrIcms   := FormatFloat('#,###,##0.00', StrToFloatDef(lVrIcms, 0));

          lRvProjec.SetParam('CST_CST00',   lDsCst);  // Código de Situação Tributária
          lRvProjec.SetParam('VBC_CST00',   lVrBasCal);  // Valor da BC do ICMS
          lRvProjec.SetParam('PICMS_CST00', lPcIcms);  // Alíquota do ICMS
          lRvProjec.SetParam('VICMS_CST00', lVrIcms);  // Valor do ICMS

          // Percentual de redução da BC do ICMS
          lDsValAux := StringReplace(
                            lMyCTE.InfCTe.Imp.ICMS.CST20.PRedBC, '.', ',', [rfReplaceAll]);
          lDsValAux := FormatFloat('#,###,##0.00', StrToFloatDef(lDsValAux, 0));

          lRvProjec.SetParam('RED_BC_CALC', lDsValAux);

          if lMyCTE.InfCte.Versao = '1.03' then
          begin
                lDsValAux := StringReplace(lMyCTE.InfCTe.InfCTeNorm.InfCarga.VMerc, '.', ',',
                                                                          [rfReplaceAll]);
          end else
          begin
                lDsValAux := StringReplace(lMyCTE.InfCTe.InfCTeNorm.InfCarga.vCarga, '.', ',',
                                                                          [rfReplaceAll]);
          end;

          lDsValAux := FormatFloat('#,###,##0.00', StrToFloatDef(lDsValAux, 0));

          lRvProjec.SetParam('VMerc',   lDsValAux);  // Valor total da mercadoria
          lRvProjec.SetParam('ProPred', lMyCTE.InfCTe.InfCTeNorm.InfCarga.ProPred);  // Produto predominante
          lRvProjec.SetParam('XOutCat', lMyCTE.InfCTE.InfCTeNorm.InfCarga.XOutCat);  // Outras características da carga

          if lMyCTE.InfCTe.InfCTeNorm.InfCarga.InfQ.Count > 0 then
          begin
                for lNrIndice := 0 to lMyCTE.InfCTe.InfCTeNorm.InfCarga.InfQ.Count-1 do
                begin
                      // Código da Unidade de Medida
                      lDsValAux :=
                              lMyCTE.InfCTe.InfCTeNorm.InfCarga.InfQ.Items[lNrIndice].CUnid;

                      lRvProjec.SetParam('CUnid' + IntToStr(lNrIndice),
                                                                RetornaUnidade(lDsValAux));

                      // Tipo da Medida
                      lRvProjec.SetParam('TpMed' + IntToStr(lNrIndice),
                            lMyCTE.InfCTe.InfCTeNorm.InfCarga.InfQ.Items[lNrIndice].TpMed);

                      // Quantidade
                      lDsValAux :=
                             lMyCTE.InfCTe.InfCTeNorm.InfCarga.InfQ.Items[lNrIndice].QCarga;
                      lDsValAux := StringReplace(lDsValAux, '.', ',', [rfReplaceAll]);
                      lDsValAux := FormatFloat('#,###,##0.0000', StrToFloatDef(lDsValAux, 0));

                      lRvProjec.SetParam('QCarga' + IntToStr(lNrIndice), lDsValAux);

                      if lNrIndice >= 5 then
                      begin
                            Break;
                      end;
                end;
          end;

          lRvProjec.SetParam('XObs', lMyCTE.InfCTe.Compl.XObs);

          if lMyCTE.InfCTe.InfCTeNorm.Seg.Count > 0 then
          begin
                lRvProjec.SetParam('RespSeg', ResponsavelSeguro(
                                           lMyCTE.InfCTe.InfCTeNorm.Seg.Items[0].RespSeg));

                lRvProjec.SetParam('XSeg',  lMyCTE.InfCTe.InfCTeNorm.Seg.Items[0].XSeg);  // Nome da Seguradora
                lRvProjec.SetParam('NApol', lMyCTE.InfCTe.InfCTeNorm.Seg.Items[0].NApol);  // Número da Apólice
                lRvProjec.SetParam('NAver', lMyCTE.InfCTe.InfCTeNorm.Seg.Items[0].NAver);  // Número da Averbação
                //lRvProjec.SetParam('VMerc', lMyCTE.InfCTe.InfCTeNorm.Seg.Items[0].VMerc);  // Valor da mercadoria para efeito de averbação
          end;

          lRvProjec.SetParam('Fluxo',   lMyCTE.InfCTe.InfCTeNorm.Ferrov.Fluxo);  // Fluxo Ferroviário
          lRvProjec.SetParam('DtPreEnt',FDataSet.FieldByName('DtPreEnt').AsString);
          lRvProjec.SetParam('CdFaixa', FDataSet.FieldByName('CdFaixa').AsString);
          lRvProjec.SetParam('NmUsuari',FDataSet.FieldByName('CdUsuari').AsString + ' - ' +
                                       FDataSet.FieldByName('NmComUsu').AsString);
          lRvProjec.SetParam('CdRntc',  FDataSet.FieldByName('CdRntc').AsString);

          if FDataSet.FieldByName('SnLotaca').AsString = 'S' then
          begin
                lRvProjec.SetParam('SnLotaca', 'SIM');
          end else
          begin
                lRvProjec.SetParam('SnLotaca', 'NÃO');
          end;

          case FDefDest of
               DdPrint   : lRvSystem.DefaultDest  := rdPrinter;
               DdPDF     : begin
                                 lRvSystem.DefaultDest    := rdFile;
                                 lRvSystem.DoNativeOutput := False;
                                 lRvSystem.RenderObject   := lRvRenPDF;
                           end;
               DdPreview : lRvSystem.DefaultDest  := rdPreview;
          end;

          lRvSystem.SystemSetups         := lRvSystem.SystemSetups - [ssAllowSetup];
          lRvSystem.SystemPrinter.Copies := FNrCopia;
          lRvSystem.OutputFileName       := FDsPatDac;

          //Executa a primeira página do relatorio DACTE
          if lNrIndAux > 244 then
          begin
                lRvProjec.SetParam('Pagina',  '1/4');
                lRvProjec.SetParam('Pagina2', '2/4');
                lRvProjec.SetParam('Pagina3', '3/4');
                lRvProjec.SetParam('Pagina4', '4/4');

                lRvProjec.ExecuteReport('Report1');
                lRvProjec.ExecuteReport('Report2');
                lRvProjec.ExecuteReport('Report3');
                lRvProjec.ExecuteReport('Report4');
          end else
          begin
                if lNrIndAux > 132 then
                begin
                      lRvProjec.SetParam('Pagina',  '1/3');
                      lRvProjec.SetParam('Pagina2', '2/3');
                      lRvProjec.SetParam('Pagina3', '3/3');

                      lRvProjec.ExecuteReport('Report1');
                      lRvProjec.ExecuteReport('Report2');
                      lRvProjec.ExecuteReport('Report3');
                end else
                begin
                      if lNrIndAux > 20 then
                      begin
                            lRvProjec.SetParam('Pagina',  '1/2');
                            lRvProjec.SetParam('Pagina2', '2/2');

                            lRvProjec.ExecuteReport('Report1');
                            lRvProjec.ExecuteReport('Report2');
                      end else
                      begin
                            lRvProjec.SetParam('Pagina',  '1/1');

                           // if FDefDest = DdPDF then
                           // begin
                           //       lRvProjec.xecuteReport('Report1');
                           // end else
                           // begin
                                  lRvProjec.ExecuteReport('Report1');
                           // end;


                      end;
                end;
          end;
          //Libera o relatorio da memória
      finally
          FreeAndNil(lRvSystem);
          FreeAndNil(lRvProjec);
      end;
end;

procedure TBrvDacte.MoverDadosTomador(pMyCTE    : IXMLTCTe;   pRvProjec  : TRvProject;
                                      pDsTomado : AnsiString; pNmTomado  : AnsiString;
                                      pNmCidTom : AnsiString; pUfTomado  : AnsiString;
                                      pCpTomado : AnsiString; pEdTomado  : AnsiString;
                                      pNmPaisTo : AnsiString; pNrCnpj    : AnsiString;
                                      pIeTomado : AnsiString; pNrFonTom  : AnsiString;
                                      pCdTomado : AnsiString);
begin
      pRvProjec.SetParam('TOM_SERV',    pDsTomado);
      pRvProjec.SetParam('Nome_TOM',    pNmTomado);
      pRvProjec.SetParam('XMun_TO',     pNmCidTom);
      pRvProjec.SetParam('UF_TO',       pUfTomado);
      pRvProjec.SetParam('CEP_TO',      pCpTomado);
      pRvProjec.SetParam('XLgr_TO',     pEdTomado);
      pRvProjec.SetParam('XPais_TO',    pNmPaisTo);
      pRvProjec.SetParam('CPF_CNPJ_TO', pNrCnpj);
      pRvProjec.SetParam('IE_TO',       pIeTomado);
      pRvProjec.SetParam('Fone_TO',     pNrFonTom);
      pRvProjec.SetParam('CdTomado',    pCdTomado);
end;

procedure TBrvDacte.SituacaoTributaria103(pMyCTE    : IXMLTCTe; var pDsCst  : String;
                                      var pVrBasCal : String;   var pPcIcms : String;
                                      var pVrIcms   : String);
begin
      if pMyCTE.InfCte.Versao = '1.03' then
      begin
            pVrBasCal := '0,00';
            pPcIcms   := '0,00';
            pVrIcms   := '0,00';

            if pMyCTE.InfCTe.Imp.ICMS.CST00.CST = '00' then
            begin
                  pDsCst    := '00 - ICMS Normal';  // Código de Situação Tributária

                  pVrBasCal := StringReplace(pMyCTE.InfCTe.Imp.ICMS.CST00.VBC, '.', ',',
                                                                                [rfReplaceAll]);
                  pPcIcms   := StringReplace(
                                   pMyCTE.InfCTe.Imp.ICMS.CST00.PICMS, '.', ',', [rfReplaceAll]);
                  pVrIcms   := StringReplace(
                                   pMyCTE.InfCTe.Imp.ICMS.CST00.VICMS, '.', ',', [rfReplaceAll]);
            end;

            if pMyCTE.InfCTe.Imp.ICMS.CST20.CST = '20' then
            begin
                  pDsCst    := '20 - ICMS com redução de Base de Cálculo';  // Código de Situação Tributária

                  pVrBasCal := StringReplace(pMyCTE.InfCTe.Imp.ICMS.CST20.VBC, '.', ',',
                                                                                [rfReplaceAll]);
                  pPcIcms   := StringReplace(
                                   pMyCTE.InfCTe.Imp.ICMS.CST20.PICMS, '.', ',', [rfReplaceAll]);
                  pVrIcms   := StringReplace(
                                   pMyCTE.InfCTe.Imp.ICMS.CST20.VICMS, '.', ',', [rfReplaceAll]);
            end;

            if pMyCTE.InfCTe.Imp.ICMS.CST45.CST = '40' then
            begin
                  pDsCst := '40 - ICMS isenção';
            end;

            if pMyCTE.InfCTe.Imp.ICMS.CST45.CST = '41' then
            begin
                  pDsCst := '41 - ICMS não tributada';
            end;

            if pMyCTE.InfCTe.Imp.ICMS.CST45.CST = '51' then
            begin
                  pDsCst := '51 - ICMS diferido';
            end;

            if pMyCTE.InfCTe.Imp.ICMS.CST80.CST = '80' then
            begin
                  pDsCst    := '80 - ICMS atribuído ao tomador o ao terceiro previsto para ST';// Código de Situação Tributária

                  pVrBasCal := StringReplace(pMyCTE.InfCTe.Imp.ICMS.CST80.VBC, '.', ',',
                                                                                [rfReplaceAll]);
                  pPcIcms   := StringReplace(
                                   pMyCTE.InfCTe.Imp.ICMS.CST80.PICMS, '.', ',', [rfReplaceAll]);
                  pVrIcms   := StringReplace(
                                   pMyCTE.InfCTe.Imp.ICMS.CST80.VICMS, '.', ',', [rfReplaceAll]);
            end;

            if pMyCTE.InfCTe.Imp.ICMS.CST81.CST = '81' then
            begin
                  pDsCst    := '81 - ICMS devido para outras UF';  // Código de Situação Tributária

                  pVrBasCal := StringReplace(pMyCTE.InfCTe.Imp.ICMS.CST81.VBC, '.', ',',
                                                                                [rfReplaceAll]);
                  pPcIcms   := StringReplace(
                                   pMyCTE.InfCTe.Imp.ICMS.CST81.PICMS, '.', ',', [rfReplaceAll]);
                  pVrIcms   := StringReplace(
                                   pMyCTE.InfCTe.Imp.ICMS.CST81.VICMS, '.', ',', [rfReplaceAll]);
            end;

            if pMyCTE.InfCTe.Imp.ICMS.CST81.CST = '90' then
            begin
                  pDsCst    := '90 - ICMS outras situações';  // Código de Situação Tributária

                  pVrBasCal := StringReplace(pMyCTE.InfCTe.Imp.ICMS.CST90.VBC, '.', ',',
                                                                                [rfReplaceAll]);
                  pPcIcms   := StringReplace(
                                   pMyCTE.InfCTe.Imp.ICMS.CST90.PICMS, '.', ',', [rfReplaceAll]);
                  pVrIcms   := StringReplace(
                                   pMyCTE.InfCTe.Imp.ICMS.CST90.VICMS, '.', ',', [rfReplaceAll]);
            end;
      end;
end;

procedure TBrvDacte.SituacaoTributaria104(pMyCTE    : IXMLTCTe; var pDsCst    : String;
                                      var pVrBasCal : String;   var pPcIcms   : String;
                                      var pVrIcms   : String;   var pPcRedBas : String);
begin
      pVrBasCal := '0,00';
      pPcIcms   := '0,00';
      pVrIcms   := '0,00';
      pPcRedBas := '0,00';

      if pMyCTE.InfCTe.Imp.ICMS.ICMS00.CST = '00' then
      begin
            pDsCst    := '00 - ICMS Normal';  // Código de Situação Tributária

            pVrBasCal := StringReplace(pMyCTE.InfCTe.Imp.ICMS.ICMS00.VBC, '.', ',',
                                                                          [rfReplaceAll]);
            pPcIcms   := StringReplace(
                             pMyCTE.InfCTe.Imp.ICMS.ICMS00.PICMS, '.', ',', [rfReplaceAll]);
            pVrIcms   := StringReplace(
                             pMyCTE.InfCTe.Imp.ICMS.ICMS00.VICMS, '.', ',', [rfReplaceAll]);
      end;

      if pMyCTE.InfCte.Imp.ICMS.ICMS20.CST = '20' then
      begin
            pDsCst    := '20 - ICMS com redução de Base de Cálculo';  // Código de Situação Tributária

            pVrBasCal := StringReplace(pMyCTE.InfCTe.Imp.ICMS.ICMS20.VBC, '.', ',',
                                                                          [rfReplaceAll]);
            pPcIcms   := StringReplace(
                             pMyCTE.InfCTe.Imp.ICMS.ICMS20.PICMS, '.', ',', [rfReplaceAll]);
            pVrIcms   := StringReplace(
                             pMyCTE.InfCTe.Imp.ICMS.ICMS20.VICMS, '.', ',', [rfReplaceAll]);
            pPcRedBas := StringReplace(
                             pMyCTE.InfCTe.Imp.ICMS.ICMS20.PRedBC, '.', ',', [rfReplaceAll]);

      end;

      if (pMyCTE.InfCTe.Imp.ICMS.ICMS45.CST = '40') then
      begin
            pDsCst := '40 - ICMS isenção';
      end;

      if pMyCTE.InfCTe.Imp.ICMS.ICMS45.CST = '41' then
      begin
            pDsCst := '41 - ICMS não tributada';
      end;

      if pMyCTE.InfCTe.Imp.ICMS.ICMS45.CST = '51' then
      begin
            pDsCst := '51 - ICMS diferido';
      end;

      if pMyCTE.InfCte.Imp.ICMS.ICMS60.CST = '60' then
      begin
            pDsCst    := '60 - ICMS cobrado anteriormente por substituição tributária';  // Código de Situação Tributária

            pVrBasCal := StringReplace(pMyCTE.InfCTe.Imp.ICMS.ICMS60.vBCSTRet, '.', ',',
                                                                          [rfReplaceAll]);
            pPcIcms   := StringReplace(
                             pMyCTE.InfCTe.Imp.ICMS.ICMS60.pICMSSTRet, '.', ',', [rfReplaceAll]);
            pVrIcms   := StringReplace(
                             pMyCTE.InfCTe.Imp.ICMS.ICMS60.vICMSSTRet, '.', ',', [rfReplaceAll]);
      end;

      if pMyCTE.InfCTe.Imp.ICMS.ICMS90.CST = '90' then
      begin
            pDsCst    := '90 - ICMS outros ';  // Código de Situação Tributária

            pVrBasCal := StringReplace(pMyCTE.InfCTe.Imp.ICMS.ICMS90.VBC, '.', ',',
                                                                            [rfReplaceAll]);
            pPcIcms   := StringReplace(
                             pMyCTE.InfCTe.Imp.ICMS.ICMS90.PICMS, '.', ',',  [rfReplaceAll]);
            pVrIcms   := StringReplace(
                             pMyCTE.InfCTe.Imp.ICMS.ICMS90.VICMS, '.', ',',  [rfReplaceAll]);
            pPcRedBas := StringReplace(
                             pMyCTE.InfCTe.Imp.ICMS.ICMS90.PRedBC, '.', ',', [rfReplaceAll]);
      end;

      if pMyCTE.InfCTe.Imp.ICMS.ICMSOutraUF.CST = '90' then
      begin
            pDsCst    := '90 - ICMS outros ';  // Código de Situação Tributária

            pVrBasCal := StringReplace(pMyCTE.InfCTe.Imp.ICMS.ICMSOutraUF.vBCOutraUF, '.', ',',
                                                                            [rfReplaceAll]);
            pPcIcms   := StringReplace(
                             pMyCTE.InfCTe.Imp.ICMS.ICMSOutraUF.pICMSOutraUF, '.', ',',
                                                                            [rfReplaceAll]);
            pVrIcms   := StringReplace(
                             pMyCTE.InfCTe.Imp.ICMS.ICMSOutraUF.vICMSOutraUF, '.', ',',
                                                                            [rfReplaceAll]);
            pPcRedBas := StringReplace(
                             pMyCTE.InfCTe.Imp.ICMS.ICMSOutraUF.PRedBCOutraUF, '.', ',',
                                                                            [rfReplaceAll]);
      end;
end;

function TBrvDacte.RetornaUnidade(pNrPosica : String) : String;
begin
      case StrToIntDef(pNrPosica, 5) of
           0: Result := 'M3';
           1: Result := 'KG';
           2: Result := 'TON';
           3: Result := 'UNID';
           4: Result := 'LITR';
      end;
end;

function TBrvDacte.ResponsavelSeguro(pNrRespon : String) : String;
begin
      case StrToIntDef(pNrRespon, 6) of
           0: Result := 'REMETENTE';
           1: Result := 'EXPEDIDOR';
           2: Result := 'RECEBEDOR';
           3: Result := 'DESTINATÁRIO';
           4: Result := 'EMITENTE';
           5: Result := 'TOMADOR';
      end;
end;


function TBrvDacte.GetTempDir: string;
var Temp : array[0..144] of Char;
begin
      GetTempPath(144, Temp);
      Result := StrPas(Temp);
end;

procedure TBrvDacte.ReturnConfig;
begin

end;

end.



