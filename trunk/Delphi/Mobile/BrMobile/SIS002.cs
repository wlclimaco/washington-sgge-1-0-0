using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Text;
using System.Windows.Forms;
using System.Data.SqlServerCe;
using LogosMobileEntradaBayer;

namespace LogosMobile
{
    public partial class SIS002 : Form
    {
        private SqlCeConnection conexao = new SqlCeConnection();
        private LogosMobile.AcessoBD Acesso;
        private BayerEntrada Entrada;

        public string NrMapa;
        public string DsList;
        public bool   snfecha;
        public bool   snpreetiqueta;
        public bool   snTransf;

        private int QtProSid = 0;
        string nrgtin;
        string nrlote;
        string dtproduc;
        string dtvalid;
        string nrsscc;
        float qtprodut;        

        public SIS002(string nrmapa, string nrnota, string dslist, string nmusuario, bool sntransf, string dstitoper)
        {
            InitializeComponent();

            LblTitulo.Text = dstitoper;
            snTransf = sntransf;

            lblUsuario.Text = nmusuario;
            snfecha = false;
            Acesso = new LogosMobile.AcessoBD();
            Acesso.CriaBD(ref conexao, "LogosMobileBD");
            Acesso.AbreConexao(ref conexao, "LogosMobileBD");

            Entrada = new BayerEntrada();

            NrMapa = nrmapa;            
            Entrada.AlimentaEntrada(ref conexao, dslist, nrmapa);

            lblNrMapa.Text = nrmapa;
            lblNrNota.Text = nrnota;

            edtCodigo.Focus();

            string nrip      = string.Empty;
            string lastuser  = string.Empty;
            string snpreetiq = string.Empty;
            string nrtmpatv  = string.Empty;

            Controller.GetConfig(ref nrip, ref lastuser, ref snpreetiq, ref nrtmpatv);

            snpreetiqueta = (snpreetiq == "Sim");

            LePreEtiqueta(false);
        }

        private void LePreEtiqueta(bool snfinaliza)
        {
            if (snpreetiqueta == true)
            {
                SIS007 FrmPreEtiqueta = new SIS007(NrMapa, snfinaliza, LblTitulo.Text, "E", lblNrNota.Text, lblUsuario.Text);
                FrmPreEtiqueta.ShowDialog();

                if (FrmPreEtiqueta.DialogResult == DialogResult.Cancel)
                {
                    pnlStatus.Visible = true;
                    pnlStatus.Refresh();
                    timerFecha.Enabled = true;
                }
                else
                {
                    if (FrmPreEtiqueta.Snfinaliza)
                    {
                        Finalizar();
                    }
                    else
                    {
                        if (FrmPreEtiqueta.SnSemId)
                        {
                            btnSemID.Visible = false;
                            LblSemId.Visible = true;
                            edtTTSemID.Visible = true;
                            edtTTSemID.Text = QtProSid.ToString();

                            edtCodigo.Text = "Sem Identificação";
                            edtCodigo.Enabled = false;
                            edtQtde.Enabled = true;
                            edtQtde.Text = string.Empty;
                            edtQtde.SelectAll();
                            edtQtde.Focus();
                        }
                        else
                        {
                            edtCodigo.Enabled = true;
                            edtCodigo.Text = FrmPreEtiqueta.Etiqueta();
                            edtCodigo.Focus();
                            ProcessaEntrada();
                            edtLote.Enabled = false;
                            edtValidade.Enabled = false;
                            edtQtde.Enabled = true;
                            edtQtde.SelectAll();
                            edtQtde.Focus();
                        }
                    }
                }
            }
        }

        private void SIS002_Closed(object sender, EventArgs e)
        {
            Acesso.FechaConexao(ref conexao);
        }

        private void btnSair_Click(object sender, EventArgs e)
        {            
            if (Controller.MessageDlg("Deseja realmente cancelar esta conferencia?"))
            {
                pnlStatus.Visible = true;
                pnlStatus.Refresh();
                this.Refresh();

                if (Controller.WsLogos.WsPing())
                {
                    if (Controller.WsLogos.WsEntradaFinalizar(NrMapa, string.Empty, "S", 0))
                    {
                        this.Close();
                    }
                    else
                    {
                        Controller.ShowMessage("Tente novamente...");
                        lblAviso.Visible = true;
                        pnlStatus.Visible = false;                        
                        this.Refresh();
                    }                    
                }
                else
                {
                    Controller.ShowMessage("Tente novamente...");
                    lblAviso.Visible = true;
                    pnlStatus.Visible = false;
                    this.Refresh();
                }
            }
        }

        private void PreparaEntradaScanner()
        {
            LblSemId.Visible = false;
            edtTTSemID.Visible = false;
            btnSemID.Visible = true;

            BtnCanItem.Visible = false;
            edtQtde.Enabled = false;
            edtQtde.Text = string.Empty;

            edtLote.Enabled = false;
            edtLote.Text = string.Empty;

            edtValidade.Enabled = false;
            edtValidade.Text = string.Empty;

            edtCodigo.Enabled = true;
            edtCodigo.Text = string.Empty;
            edtCodigo.Focus();
        }

        private void ProcessaEntrada()
        {
            if (edtCodigo.Focused)
                {
                    if (edtCodigo.Text.Trim() != string.Empty)
                    {
                        if (edtCodigo.Text.Substring(0, 3).ToUpper() != "]D2")
                        {
                            if (edtCodigo.Text.Substring(0, 4) == "FNC1")
                            {
                                //Modelo Caixa
                                nrgtin = edtCodigo.Text.Substring(26, 14).TrimStart('0');

                                int tmlote = edtCodigo.Text.Length - 58;
                                nrlote = edtCodigo.Text.Substring(58, tmlote).Trim();

                                dtproduc = edtCodigo.Text.Substring(42, 6).Trim();
                                dtvalid = edtCodigo.Text.Substring(50, 6).Trim();
                                nrsscc = edtCodigo.Text.Substring(6, 18).Trim();

                                edtLote.Text = nrlote;
                                edtValidade.Text = dtvalid;
                                edtQtde.Text = "1";
                                edtQtde.Enabled = true;
                                edtQtde.Focus();
                                ProcessaEntrada();

                                //edtCodigo.Enabled = false;
                                //BtnCanItem.Visible = true;
                            }
                            else
                            {
                                string auxstrcc = edtCodigo.Text.Substring(21, 2);

                                if ((edtCodigo.Text.Substring(0, 1) == "è") && (auxstrcc == "01"))
                                {
                                    //Modelo Caixa Novo
                                    nrgtin = edtCodigo.Text.Substring(23, 14).TrimStart('0');

                                    int tmlote = edtCodigo.Text.Length - 58;
                                    nrlote = edtCodigo.Text.Substring(58, tmlote).Trim();

                                    dtproduc = edtCodigo.Text.Substring(39, 6).Trim();
                                    dtvalid = edtCodigo.Text.Substring(47, 6).Trim();
                                    nrsscc = edtCodigo.Text.Substring(3, 18).Trim();

                                    edtLote.Text = nrlote;
                                    edtValidade.Text = dtvalid;
                                    edtQtde.Text = "1";
                                    edtQtde.Enabled = true;
                                    edtQtde.Focus();
                                    ProcessaEntrada();
                                }
                                else
                                {
                                    if (edtCodigo.Text.Substring(0, 1) == "è")
                                    {
                                        string auxcode = edtCodigo.Text;
                                        //è0003526550003158597402035265516470153764è10EV36000368è110602009104222015
                                        //removendo Caracter de identificação                        
                                        auxcode = auxcode.Substring(1, auxcode.Length - 1);
                                        //0003526550003158597402035265516470153764è10EV36000368è110602009104222015
                                        //removendo codigo de identificação (00)                        
                                        auxcode = auxcode.Substring(2, auxcode.Length - 2);
                                        //03526550003158597402035265516470153764è10EV36000368è110602009104222015
                                        //extraindo SSCC                        
                                        nrsscc = auxcode.Substring(0, 18);
                                        //removendo SSCC
                                        auxcode = auxcode.Substring(18, auxcode.Length - 18);
                                        //02035265516470153764è10EV36000368è110602009104222015
                                        //removendo codigo de identificação (02)
                                        auxcode = auxcode.Substring(2, auxcode.Length - 2);
                                        //035265516470153764è10EV36000368è110602009104222015
                                        //extraindo GTIN
                                        nrgtin = auxcode.Substring(0, 14).TrimStart('0');
                                        //removendo GTIN
                                        auxcode = auxcode.Substring(14, auxcode.Length - 14);
                                        //3764è10EV36000368è110602009104222015
                                        //removendo codigo de identificação (37)
                                        auxcode = auxcode.Substring(2, auxcode.Length - 2);
                                        //64è10EV36000368è110602009104222015
                                        //extraindo Quantidade
                                        edtQtde.Text = auxcode.Substring(0, auxcode.IndexOf('è'));
                                        //removendo Quantidade
                                        //10EV36000368è110602009104222015
                                        auxcode = auxcode.Substring(auxcode.IndexOf('è') + 1, auxcode.Length - (auxcode.IndexOf('è') + 1));
                                        //removendo codigo de identificação (10) 
                                        //EV36000368è110602009104222015
                                        auxcode = auxcode.Substring(2, auxcode.Length - 2);
                                        //extraindo Lote
                                        nrlote = auxcode.Substring(0, auxcode.IndexOf('è'));
                                        //removendo Lote
                                        //110602009104222015
                                        auxcode = auxcode.Substring(auxcode.IndexOf('è') + 1, auxcode.Length - (auxcode.IndexOf('è') + 1));
                                        //removendo codigo de identificação (11) 
                                        auxcode = auxcode.Substring(2, auxcode.Length - 2);
                                        //0602009104222015
                                        dtproduc = auxcode.Substring(0, 6);
                                        dtvalid = "";
                                        //removendo Data de Produçao
                                        auxcode = auxcode.Substring(6, auxcode.Length - 6);
                                        //removendo codigo de identificação (91) 
                                        auxcode = auxcode.Substring(2, auxcode.Length - 2);

                                        edtLote.Text = nrlote;
                                        edtQtde.Enabled = true;
                                        edtQtde.Focus();
                                        ProcessaEntrada();

                                        /*edtLote.Enabled = true;
                                          edtLote.Focus();
                                          edtCodigo.Enabled = false;
                                          BtnCanItem.Visible = true;*/
                                    }
                                    else
                                    {
                                        Controller.ShowMessage("Etiqueta inválida!");
                                        edtCodigo.SelectAll();
                                        edtCodigo.Focus();
                                    }
                                }
                            }
                        }
                        else
                        {
                            //]D2
                            string auxcode = edtCodigo.Text;                            
                            //]D20003526550003158597402035265516470153764è10EV36000368è110602009104222015
                            //removendo Caracter de identificação                        
                            auxcode = auxcode.Substring(3, auxcode.Length - 1);
                            //0003526550003158597402035265516470153764è10EV36000368è110602009104222015
                            //removendo codigo de identificação (00)                        
                            auxcode = auxcode.Substring(2, auxcode.Length - 2);
                            //03526550003158597402035265516470153764è10EV36000368è110602009104222015
                            //extraindo SSCC                        
                            nrsscc = auxcode.Substring(0, 18);
                            //removendo SSCC
                            auxcode = auxcode.Substring(18, auxcode.Length - 18);
                            //02035265516470153764è10EV36000368è110602009104222015
                            //removendo codigo de identificação (02)
                            auxcode = auxcode.Substring(2, auxcode.Length - 2);
                            //035265516470153764è10EV36000368è110602009104222015
                            //extraindo GTIN
                            nrgtin = auxcode.Substring(0, 14).TrimStart('0');
                            //removendo GTIN
                            auxcode = auxcode.Substring(14, auxcode.Length - 14);
                            //3764è10EV36000368è110602009104222015
                            //removendo codigo de identificação (37)
                            auxcode = auxcode.Substring(2, auxcode.Length - 2);
                            //64è10EV36000368è110602009104222015
                            //extraindo Quantidade
                            edtQtde.Text = auxcode.Substring(0, auxcode.IndexOf('è'));
                            //removendo Quantidade
                            //10EV36000368è110602009104222015
                            auxcode = auxcode.Substring(auxcode.IndexOf('è') + 1, auxcode.Length - (auxcode.IndexOf('è') + 1));
                            //removendo codigo de identificação (10) 
                            //EV36000368è110602009104222015
                            auxcode = auxcode.Substring(2, auxcode.Length - 2);
                            //extraindo Lote
                            nrlote = auxcode.Substring(0, auxcode.IndexOf('è'));
                            //removendo Lote
                            //110602009104222015
                            auxcode = auxcode.Substring(auxcode.IndexOf('è') + 1, auxcode.Length - (auxcode.IndexOf('è') + 1));
                            //removendo codigo de identificação (11) 
                            auxcode = auxcode.Substring(2, auxcode.Length - 2);
                            //0602009104222015
                            dtproduc = auxcode.Substring(0, 6);
                            dtvalid = "";
                            //removendo Data de Produçao
                            auxcode = auxcode.Substring(6, auxcode.Length - 6);
                            //removendo codigo de identificação (91) 
                            auxcode = auxcode.Substring(2, auxcode.Length - 2);

                            edtLote.Text = nrlote;
                            edtQtde.Enabled = true;
                            edtQtde.Focus();
                            ProcessaEntrada();
                        }
                    }
                }
                else
                if (edtLote.Focused)
                {
                    if (nrlote == edtLote.Text)
                    {
                        edtValidade.Enabled = true;
                        edtValidade.Focus();
                    }
                    else
                    {
                        edtLote.SelectAll();
                        edtLote.Focus();
                        Controller.ShowMessage("Lote nao Confere!");
                    }
                }
                else
                if (edtValidade.Focused)
                {
                    if (dtvalid.Trim() == edtValidade.Text.Trim())
                    {
                        edtQtde.Enabled = true;
                        edtQtde.Text = string.Empty;
                        edtQtde.Focus();
                    }
                    else
                    {
                        edtValidade.SelectAll();
                        edtValidade.Focus();
                        Controller.ShowMessage("Validade nao Confere!");
                    }                    
                }
                else
                if (edtQtde.Focused)
                {
                    if (edtQtde.Text != string.Empty)
                    {
                        if (edtCodigo.Text == "Sem Identificação")
                        {
                            QtProSid += int.Parse(edtQtde.Text);                            

                            if (QtProSid > 0)
                            {
                                BtnFinalizar.Visible = true;
                            }

                            PreparaEntradaScanner();
                            LePreEtiqueta(BtnFinalizar.Visible);
                        }
                        else
                        {
                            qtprodut = float.Parse(edtQtde.Text);

                            if (Entrada.FindItem(ref conexao, nrgtin, nrlote, dtvalid, " and STITEM <> 'X' ")) //Verifica se produto é válido na lista
                            {
                                if (Entrada.FindItemExt(ref conexao, nrgtin, nrlote, dtproduc, dtvalid, nrsscc)) //Verifica se produto já foi registrado na lista
                                {
                                    Controller.ShowMessage("Item já conferido! Verifique...");
                                    PreparaEntradaScanner();
                                    LePreEtiqueta(BtnFinalizar.Visible);
                                }
                                else
                                {
                                    //Insere contagem
                                    Entrada.InsertContagem(ref conexao, nrgtin, nrlote, dtproduc, dtvalid, nrsscc, false, qtprodut, true);
                                    BtnFinalizar.Visible = true;
                                    PreparaEntradaScanner();
                                    LePreEtiqueta(BtnFinalizar.Visible);
                                }
                            }
                            else
                            {
                              /*if (Controller.MessageDlg("Produto não encontrado! Deseja incluir?"))
                                {*/
                                    Entrada.InsertContagem(ref conexao, nrgtin, nrlote, dtproduc, dtvalid, nrsscc, true, qtprodut, true);
                                    BtnFinalizar.Visible = true;
                                    PreparaEntradaScanner();
                                    LePreEtiqueta(BtnFinalizar.Visible);
                              /*}*/
                            }
                        }
                        int qtaux = (Entrada.TotalContado(ref conexao) + QtProSid);
                        edtTT.Text = qtaux.ToString();
                    }
                    else
                    {
                        Controller.ShowMessage("Favor informar quantidade!");
                        edtQtde.Focus();
                    }                    
                }
        }

        private void SIS002_KeyDown(object sender, KeyEventArgs e)
        {            
            if ((e.KeyCode == System.Windows.Forms.Keys.Enter))
            {
                ProcessaEntrada();
            }
        }

        private void Finalizar()
        {
            if (!snfecha)
            {
                if (Entrada.FinalizarContagem(ref conexao))
                {
                    edtCodigo.Enabled = false;
                    snfecha = true;

                    pnlStatus.Visible = true;
                    pnlStatus.Refresh();
                    this.Refresh();

                    if (Controller.WsLogos.WsPing())
                    {
                        if (Controller.WsLogos.WsEntradaFinalizar(NrMapa, Entrada.GeraListagem(ref conexao, NrMapa), "N", QtProSid))
                        {
                            this.Close();
                        }
                        else
                        {
                            Controller.ShowMessage("Tente novamente...");
                            lblAviso.Visible = true;
                            pnlStatus.Visible = false;
                            this.Refresh();
                        }
                    }
                    else
                    {
                        Controller.ShowMessage("Sem acesso a rede!!! Tente novamente...");
                        lblAviso.Visible = true;
                        pnlStatus.Visible = false;
                        this.Refresh();
                    }
                }
                else
                {
                    int qtaux = (Entrada.TotalContado(ref conexao) + QtProSid);
                    edtTT.Text = qtaux.ToString();

                    PreparaEntradaScanner();
                    LePreEtiqueta(BtnFinalizar.Visible);
                }
            }
            else
            {
                edtCodigo.Enabled = false;

                if (Controller.WsLogos.WsPing())
                {
                    if (Controller.WsLogos.WsEntradaFinalizar(NrMapa, Entrada.GeraListagem(ref conexao, NrMapa), "N", QtProSid))
                    {
                        this.Close();
                    }
                    else
                    {
                        Controller.ShowMessage("Tente novamente...");
                        lblAviso.Visible = true;
                        pnlStatus.Visible = false;
                        this.Refresh();
                    }
                }
                else
                {
                    Controller.ShowMessage("Sem acesso a rede!!! Tente novamente...");
                    lblAviso.Visible = true;
                    pnlStatus.Visible = false;
                    this.Refresh();
                }
            }        
        }

        private void Finalizar_Click(object sender, EventArgs e)
        {
            Finalizar();
        }        

        private void BtnCanItem_Click(object sender, EventArgs e)
        {
            if (Controller.MessageDlg("Cancelar contagem deste item?"))
            {
                PreparaEntradaScanner();                
            }
        }

        private void ImgTeclado_Click(object sender, EventArgs e)
        {
            IptPnlSenha.Enabled = !IptPnlSenha.Enabled;
        }

        private void timerFecha_Tick(object sender, EventArgs e)
        {
            timerFecha.Enabled = false;
            this.Close();
        }       

        private void btnSemID_Click(object sender, EventArgs e)
        {
            btnSemID.Visible = false;
            LblSemId.Visible = true;
            edtTTSemID.Visible = true;
            edtTTSemID.Text = QtProSid.ToString();

            edtCodigo.Text = "Sem Identificação";
            edtCodigo.Enabled = false;
            edtQtde.Enabled = true;
            edtQtde.Text = string.Empty;
            edtQtde.Focus();
        }

        private void BtnProd2_Click(object sender, EventArgs e)
        {
            //edtCodigo.Text = "è0003526550003158597402035265516470153764è10EV36000368è110602009104222015";
            edtCodigo.Text = "è0004051391004776901102378920581127503730è10017;12è111207009179290462";
            edtCodigo.Focus();
        }

        private void BtnProd1_Click(object sender, EventArgs e)
        {
            //edtCodigo.Text = "FNC10004051391000210072901378920581054001112060017130600101234;12";
            edtCodigo.Text = "]D20003526550003158597402035265516470153764è10EV36000368è110602009104222015";
            edtCodigo.Focus();
        }

        private void button1_Click(object sender, EventArgs e)
        {
            edtCodigo.Text = "è000405139100477843420137892058111579111207001714070010033;12";            
            edtCodigo.Focus();
        }                 
        
    }
}