using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Text;
using System.Windows.Forms;

namespace LogosMobile
{
    public partial class SIS006 : Form
    {
        private string nrclient    = string.Empty;
        private string nrmapa      = string.Empty;
        private string nrfornec    = string.Empty;
        private bool   snFinaliza  = false;
        private string tpMovimento = "";

        public bool   SnFinaliza {get { return snFinaliza; } set { snFinaliza = value; }}
        public string NrMapa     {get { return nrmapa;     } set { nrmapa     = value; }}
        public string NrFornec   {get { return nrfornec;   } set { nrfornec   = value; }}
        public string NrClient   {get { return nrclient;   } set { nrclient   = value; }}

        public SIS006(string Nrmapa, string Nrfornec, bool snfinaliza, string nmusuario, string dstitulo, string TpMovimento)
        {
            InitializeComponent();
            tpMovimento = TpMovimento;
            lblTitulo.Text = dstitulo;
            lblUsuario.Text = nmusuario;
            nrmapa = Nrmapa;
            nrfornec = Nrfornec;
            lblMapa.Text = "Mapa: " + nrmapa;
            LblFornec.Text = nrfornec;
            BtnFinalizar.Visible = snfinaliza;
            edtEtiqueta.Focus();
        }

        private void btnSair_Click(object sender, EventArgs e)
        {
            if (Controller.MessageDlg("Deseja realmente cancelar esta operacao?"))
            {
                this.Refresh();
                pnlAguarde.Visible = true;
                pnlAguarde.Refresh();

                if (Controller.WsLogos.WsPing())
                {
                    if (tpMovimento == "S")
                    {
                        if (Controller.WsLogos.WsSaidaFinalizar(NrMapa, string.Empty, NrClient, "S", 0))
                        {
                            this.DialogResult = DialogResult.Cancel;
                        }
                        else
                        {
                            Controller.ShowMessage("Falha durante procedimento!");
                            lblStatus.Text = "Tente novamente...";
                            pnlAguarde.Refresh();
                        }
                    }
                    else
                    {
                        if (Controller.WsLogos.WsEntradaFinalizar(NrMapa, string.Empty, "S", 0))
                        {
                            this.DialogResult = DialogResult.Cancel;
                        }
                        else
                        {
                            Controller.ShowMessage("Falha durante procedimento!");
                            lblStatus.Text = "Tente novamente...";
                            pnlAguarde.Refresh();
                        }
                    }
                }
            }            
        }           

        private void SIS006_KeyDown(object sender, KeyEventArgs e)
        {
            // Enter
            if ((e.KeyCode == System.Windows.Forms.Keys.Enter))
            {
                pnlAguarde.Visible = true;
                pnlAguarde.Refresh();

                if (edtEtiqueta.Text.Length > 38)
                {
                    string NrFornecAux = edtEtiqueta.Text.Substring(37, 10).TrimStart('0');

                    if (NrFornecAux == NrFornec.TrimStart('0'))
                    {
                        NrClient = edtEtiqueta.Text.Substring(27, 10).TrimStart('0');
                        edtEtiqueta.Text = string.Empty;
                        this.DialogResult = DialogResult.OK;
                    }
                    else
                    {
                        Controller.ShowMessage("Fornecimento inválido!!!");
                        pnlAguarde.Visible = false;
                        this.Refresh();
                        edtEtiqueta.Text = string.Empty;
                        edtEtiqueta.Focus();
                    }
                }
                else
                {
                    edtEtiqueta.Text = string.Empty;
                    edtEtiqueta.Focus();
                    pnlAguarde.Visible = false;
                    Controller.ShowMessage("Etiqueta invalida!!!");
                }
            }    
        }

        private void BtnFinalizar_Click(object sender, EventArgs e)
        {
            edtEtiqueta.Text = string.Empty;

            if (Controller.MessageDlg("Deseja realmente finalizar esta operação?"))
            {
                snFinaliza = true;
                this.DialogResult = DialogResult.OK;
            }
            else
            {
                edtEtiqueta.Focus();
            }
        }

        private void button1_Click(object sender, EventArgs e)
        {
            edtEtiqueta.Text = "MT000000005102678 78840-000000915518308016682110007";
            edtEtiqueta.Focus();
        }        
    }
}