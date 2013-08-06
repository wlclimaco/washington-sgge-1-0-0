using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Text;
using System.Windows.Forms;

namespace LogosMobile
{
    public partial class SIS007 : Form
    {        
        private string NrMapa;
        private bool snfinaliza = false;
        private bool snSemId = false;
        private string tpoperac;

        public string TpOperac
        {
            get { return tpoperac; }
            set { tpoperac = value; }
        }

        public bool SnSemId
        {
            get { return snSemId; }
            set { snSemId = value; }
        }

        public bool Snfinaliza
        {
            get { return snfinaliza; }
            set { snfinaliza = value; }
        }

        public string Etiqueta()
        {            
            //Modelo Definitivo
            return "FNC1" + "00" + edtSSCC.Text.PadLeft(18,'0') + 
                            "01" + edtCodigo.Text.PadLeft(14, '0') +
                            "11" + edtProducao.Text.PadLeft(6, ' ') +
                            "17" + edtValidade.Text.PadLeft(6, ' ') +
                            "10" + edtLote.Text.PadLeft(10, ' ');

            //return "FNC1" + "00" + edtCodigo.Text.PadLeft(10, '0') + 
            //                       edtSSCC.Text.PadLeft(10, '0') +
            //                "11" + edtProducao.Text.PadLeft(6, ' ') +
            //                "17" + edtValidade.Text.PadLeft(6, ' ') +
            //                "10" + edtLote.Text.PadLeft(10, ' ');
        }

        public SIS007(string nrmapa, bool snfinaliza, string dsLabel, string Tpoperac, string nrnota, string nmusuario)
        {
            InitializeComponent();
            lblUsuario.Text = nmusuario;
            NrMapa = nrmapa;
            lblNrMapa.Text = nrmapa;
            lblNrNota.Text = nrnota;
            btnFinalizar.Visible = snfinaliza;
            LblOperac.Text = dsLabel;
            TpOperac = Tpoperac;

            edtCodigo.Focus();
        }

        private void SIS007_KeyDown(object sender, KeyEventArgs e)
        {            
            if ((e.KeyCode == System.Windows.Forms.Keys.Enter))
            {
                if (edtCodigo.Focused)
                {
                    try
                    {
                        edtCodigo.Text = edtCodigo.Text.Substring(5, 8);
                        edtSSCC.Focus();
                    }
                    catch(Exception E)
                    {                        
                        Controller.ShowMessage("Etiqueta inválida!!! Verifique");
                        edtCodigo.SelectAll();
                        edtCodigo.Focus();
                    }
                }
                else
                {
                    if (edtSSCC.Focused)
                    {
                        edtLote.SelectAll();
                        edtLote.Focus();
                    }
                    else
                    {
                        if (edtLote.Focused)
                        {
                            edtValidade.SelectAll();
                            edtValidade.Focus();
                        }
                        else
                        {
                            if (edtValidade.Focused)
                            {
                                if (edtValidade.Text.Trim() != string.Empty)
                                {
                                    edtValidade.Text = edtValidade.Text.PadRight(6, '0');
                                }

                                edtProducao.SelectAll();
                                edtProducao.Focus();
                            }
                            else
                            {
                                if (edtProducao.Focused)
                                {
                                    if (edtProducao.Text.Trim() != string.Empty)
                                    {
                                        edtProducao.Text = edtProducao.Text.PadRight(6, '0');
                                    }

                                    try
                                    {
                                        edtSSCC.Text = edtSSCC.Text.Substring(5, 18);
                                        this.Refresh();
                                        pnlAguarde.Visible = true;
                                        pnlAguarde.Refresh();
                                        DialogResult = DialogResult.OK;
                                    }
                                    catch (Exception E)
                                    {
                                        Controller.ShowMessage("Etiqueta inválida!!! Verifique");
                                        edtSSCC.SelectAll();
                                        edtSSCC.Focus();
                                    }
                                }
                            }
                        }
                    }
                }
            }
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
                    if (TpOperac == "S")
                    {
                        if (Controller.WsLogos.WsSaidaFinalizar(NrMapa, string.Empty, "", "S", 0))
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

        private void BtnFinalizar_Click(object sender, EventArgs e)
        {
            edtCodigo.Text = string.Empty;
            edtSSCC.Text = string.Empty;

            if (Controller.MessageDlg("Deseja realmente finalizar esta operação?"))
            {
                Snfinaliza = true;
                this.DialogResult = DialogResult.OK;
            }
            else
            {
                edtCodigo.Focus();
            }
        }

        private void BtnSemIdent_Click(object sender, EventArgs e)
        {
            SnSemId = true;
            this.DialogResult = DialogResult.OK;
        }
                       
    }
}