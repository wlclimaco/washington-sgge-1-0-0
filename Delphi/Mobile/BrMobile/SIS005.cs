using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Text;
using System.Windows.Forms;
using System.Data.SqlServerCe;
using LogosMobileSaidaBayer;

namespace LogosMobile
{
    public partial class SIS005 : Form
    {
        private SqlCeConnection conexao = new SqlCeConnection();
        private LogosMobile.AcessoBD Acesso;
        private BayerSaida Saida;

        public string NrMapa;
        public string DsList;
        public bool   snfecha;
        public bool   snpreetiqueta;

        private int QtProSid = 0;
        
        string nrgtin   = string.Empty;
        string nrlote   = string.Empty;
        string dtproduc = string.Empty;
        string dtvalid  = string.Empty;
        string nrsscc   = string.Empty;
        float  qtprodut = 0;
        string NrClient = string.Empty;
        string NrFornec = string.Empty;
        bool   SnTransf = false;
        
        public SIS005(string nrmapa, string nrnota, string dslist, string nrclient, string nrfornec, bool sntransf, 
                      string nmusuario, string dstitoper)
        {
            InitializeComponent();
            LblTitulo.Text  = dstitoper;
            lblUsuario.Text = nmusuario;

            NrFornec = nrfornec;
            SnTransf = sntransf;

            NrClient = nrclient;
            snfecha = false;
            Acesso = new LogosMobile.AcessoBD();
            Acesso.CriaBD(ref conexao, "LogosMobileBD");
            Acesso.AbreConexao(ref conexao, "LogosMobileBD");

            Saida = new BayerSaida();

            NrMapa = nrmapa;
            Saida.AlimentaSaida(ref conexao, dslist, nrmapa);

            lblNrMapa.Text = nrmapa;
            lblNrNota.Text = nrnota;

            string nrip = string.Empty;
            string lastuser = string.Empty;
            string snpreetiq = string.Empty;
            string nrtmpatv = string.Empty;

            Controller.GetConfig(ref nrip, ref lastuser, ref snpreetiq, ref nrtmpatv);

            snpreetiqueta = (snpreetiq == "Sim");
            
            LePreEtiqueta(BtnFinalizar.Visible);            
        }        

        private void ProcessaSaida()
        {
            if (Controller.MessageDlg("Deseja realmente cancelar esta operacao?"))
            {
                this.Refresh();
                pnlAguarde.Visible = true;
                pnlAguarde.Refresh();

                if (Controller.WsLogos.WsPing())
                {
                    if (Controller.WsLogos.WsSaidaFinalizar(NrMapa, string.Empty, NrClient, "S", 0))
                    {
                        this.Close();
                    }
                    else
                    {
                        Controller.ShowMessage("Tente novamente...");
                        lblAviso.Visible = true;
                        pnlAguarde.Visible = false;
                        this.Refresh();
                    }
                }
                else
                {
                    Controller.ShowMessage("Tente novamente...");
                    lblAviso.Visible = true;
                    pnlAguarde.Visible = false;
                    this.Refresh();
                }
            }        
        }

        private void btnSair_Click(object sender, EventArgs e)
        {
            ProcessaSaida();
        }        

        private void Finalizar()
        {
            if (!snfecha)
            {
                if (Saida.FinalizarContagem(ref conexao))
                {
                    this.Refresh();
                    pnlAguarde.Visible = true;
                    pnlAguarde.Refresh();

                    edtCodigo.Enabled = false;
                    snfecha = true;

                    if (Controller.WsLogos.WsPing())
                    {
                        if (Controller.WsLogos.WsSaidaFinalizar(NrMapa, Saida.GeraListagem(ref conexao, NrMapa), NrClient, "N", QtProSid))
                        {
                            this.Close();
                        }
                        else
                        {
                            Controller.ShowMessage("Tente novamente...");
                            lblAviso.Visible = true;
                            pnlAguarde.Visible = false;
                            this.Refresh();
                        }
                    }
                    else
                    {
                        Controller.ShowMessage("Sem acesso a rede!!! Tente novamente...");
                        lblAviso.Visible = true;
                        pnlAguarde.Visible = false;
                        this.Refresh();
                    }
                }
                else
                {
                    int qtaux = (Saida.TotalContado(ref conexao) + QtProSid);
                    edtTT.Text = qtaux.ToString();

                    PreparaEntradaNovoItem();                    
                    BtnFinalizar.Visible = true;
                    VerificaEtiquetaCliente();                    
                }
            }
            else
            {
                edtCodigo.Enabled = false;

                if (Controller.WsLogos.WsPing())
                {
                    if (Controller.WsLogos.WsSaidaFinalizar(NrMapa, Saida.GeraListagem(ref conexao, NrMapa), NrClient, "N", QtProSid))
                    {
                        this.Close();
                    }
                    else
                    {
                        Controller.ShowMessage("Tente novamente...");
                        lblAviso.Visible = true;
                        pnlAguarde.Visible = false;
                        this.Refresh();
                    }
                }
                else
                {
                    Controller.ShowMessage("Sem acesso a rede!!! Tente novamente...");
                    lblAviso.Visible = true;
                    pnlAguarde.Visible = false;
                    this.Refresh();
                }
            }   
        }         

        private void Finalizar_Click(object sender, EventArgs e)
        {
            Finalizar();
        }

        private void VerificaEtiquetaCliente()
        {
            if (SnTransf == false)
            {
                SIS006 FrmValidaEtiqueta = new SIS006(NrMapa, NrFornec, true, lblUsuario.Text, LblTitulo.Text, "S");
                FrmValidaEtiqueta.ShowDialog();

                if (FrmValidaEtiqueta.DialogResult == DialogResult.OK)
                {
                    FrmValidaEtiqueta.Dispose();
                    if (FrmValidaEtiqueta.SnFinaliza)
                    {
                        Finalizar();
                    }
                    else
                    {
                        LePreEtiqueta(BtnFinalizar.Visible);
                    }
                }
                else
                {
                    ProcessaSaida();
                }
            }
            else
            {
                LePreEtiqueta(BtnFinalizar.Visible);
            }
        }


        private void LePreEtiqueta(bool snfinaliza)
        {
            if (snpreetiqueta)
            {
                SIS007 FrmPreEtiqueta = new SIS007(NrMapa, snfinaliza, LblTitulo.Text, "S", lblNrNota.Text, lblUsuario.Text);
                FrmPreEtiqueta.ShowDialog();

                if (FrmPreEtiqueta.DialogResult == DialogResult.Cancel)
                {
                    pnlAguarde.Visible = true;
                    pnlAguarde.Refresh();
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
                            ProcessarEntrada();
                        }
                    }
                }
            }
            else
            {
                edtCodigo.Focus();
            }
        }

        private void PreparaEntradaNovoItem()
        {
            btnSemID.Visible = true;
            LblSemId.Visible = false;
            edtTTSemID.Visible = false;
            edtTTSemID.Text = string.Empty;

            edtQtde.Text = string.Empty;
            edtQtde.Enabled = false;
            edtCodigo.Enabled = true;
            edtCodigo.Text = string.Empty;
            edtCodigo.Focus();                    
        }

        private void ProcessarEntrada()
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

                            edtQtde.Enabled = true;
                            edtQtde.Text = "1";
                            edtQtde.Focus();
                            ProcessarEntrada();
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

                                edtQtde.Enabled = true;
                                edtQtde.Text = "1";
                                edtQtde.Focus();
                                ProcessarEntrada();
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
                                    edtQtde.Enabled = true;
                                    edtQtde.Focus();
                                    ProcessarEntrada();
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
                        Controller.ShowMessage("Etiqueta inválida!");
                        edtCodigo.SelectAll();
                        edtCodigo.Focus();
                    }
                }
            }
            else
            {
                if (edtQtde.Focused)
                {
                    if (edtQtde.Text.Trim() != string.Empty)
                    {
                        if (edtCodigo.Text == "Sem Identificação")
                        {
                            QtProSid += int.Parse(edtQtde.Text);

                            if (QtProSid > 0)
                            {
                                BtnFinalizar.Visible = true;
                            }

                            PreparaEntradaNovoItem();                            
                            VerificaEtiquetaCliente();
                        }
                        else
                        {
                            qtprodut = float.Parse(edtQtde.Text);

                            if (Saida.FindItem(ref conexao, nrgtin, nrlote, dtvalid, " and STITEM <> 'X' ")) //Verifica se produto é válido na lista
                            {
                                if (Saida.FindItemExt(ref conexao, nrgtin, nrlote, dtproduc, dtvalid, nrsscc)) //Verifica se produto já foi registrado na lista
                                {
                                    Controller.ShowMessage("Item já conferido! Verifique...");
                                    PreparaEntradaNovoItem();
                                    VerificaEtiquetaCliente();
                                }
                                else
                                {
                                    //Insere contagem
                                    Saida.InsertContagem(ref conexao, nrgtin, nrlote, dtproduc, dtvalid, nrsscc, false, qtprodut, true);
                                    PreparaEntradaNovoItem();
                                    BtnFinalizar.Visible = true;
                                    VerificaEtiquetaCliente();
                                }
                            }
                            else
                            {
                                Saida.InsertContagem(ref conexao, nrgtin, nrlote, dtproduc, dtvalid, nrsscc, true, qtprodut, true);
                                PreparaEntradaNovoItem();
                                BtnFinalizar.Visible = true;
                                VerificaEtiquetaCliente();

                              /*if (Controller.MessageDlg("Produto não encontrado! Deseja incluir?"))
                                {
                                    Saida.InsertContagem(ref conexao, nrgtin, nrlote, dtproduc, dtvalid, nrsscc, true, qtprodut);
                                    PreparaEntradaNovoItem();                                    
                                    BtnFinalizar.Visible = true;
                                    VerificaEtiquetaCliente();
                                }
                                else
                                {
                                    PreparaEntradaNovoItem();
                                    BtnFinalizar.Visible = true;
                                    VerificaEtiquetaCliente();
                                }
                              */
                            }
                        }

                        int qtaux = (Saida.TotalContado(ref conexao) + QtProSid);
                        edtTT.Text = qtaux.ToString();                        
                    }
                    else
                    {
                        Controller.ShowMessage("Quantidade Invalida!");
                    }
                }
            }
        }

        private void SIS005_KeyDown(object sender, KeyEventArgs e)
        {
            if ((e.KeyCode == System.Windows.Forms.Keys.Enter))
            {
                ProcessarEntrada();    
            }
        }

        private void SIS005_Closed(object sender, EventArgs e)
        {
            Acesso.FechaConexao(ref conexao);
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

        private void btnQtSemID_Click(object sender, EventArgs e)
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

        private void BtnCancelIte_Click(object sender, EventArgs e)
        {
            if (Controller.MessageDlg("Cancelar contagem deste item?"))            
            {
                PreparaEntradaNovoItem();
                VerificaEtiquetaCliente();
            }
        }        

        private void BtnProd2_Click(object sender, EventArgs e)
        {                                               
            edtCodigo.Text = "è0003526550003158597402278920581125243775è10EV36000368è110602009104222015";
            edtCodigo.Focus();
        }

        private void BtnProd1_Click(object sender, EventArgs e)
        {                                                
            edtCodigo.Text = "FNC10004051391000210072901378920581054001112060017130600101234;12";
            edtCodigo.Focus();
        }

        private void button1_Click(object sender, EventArgs e)
        {
            edtCodigo.Text = "è000405139100477843420107892058114098111207001714070010033;12";
            edtCodigo.Focus();
        }

        private void button1_Click_1(object sender, EventArgs e)
        {                                               
            edtCodigo.Text = "è000405139100477843420127892058112524111207001714070010033;12";
            edtCodigo.Focus();
        }
        
    }
}