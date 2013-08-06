using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Text;
using System.Windows.Forms;

namespace LogosMobile
{
    public partial class SIS004 : Form
    {
        string nrip;                

        public string SnPreEtiq
        {
            get { if (cbxSnPreEtiq.Checked) { return "Sim"; } else { return "Nao"; } }
            set { cbxSnPreEtiq.Checked = (value == "Sim");}
        }        

        public string LastUser
        {
            get { return edtUsuario.Text; }
            set { edtUsuario.Text = value; }
        }

        public string TmpAtv
        {
            get { return edtTmpAtv.Text; }
            set { edtTmpAtv.Text = value; }
        }

        public string Nrip
        {
            get { return nrip; }
            set { nrip = value; }
        }

        public SIS004(string NrIP, string nmusuario, string snpreetiq, bool snip, bool snusu, bool snetiq, string nrtmpatv)
        {
            InitializeComponent();
            lblUsuario.Text = nmusuario;

            edtOct1.Enabled  = snip; 
            edtOct2.Enabled  = snip; 
            edtOct3.Enabled  = snip; 
            edtOct4.Enabled  = snip; 
            edtPorta.Enabled = snip;

            EdtMac.Text = Controller.GetMacAddress();

            edtUsuario.Enabled   = snusu; 
            cbxSnPreEtiq.Enabled = snetiq;

            cbxSnPreEtiq.Checked = (snpreetiq == "Sim");
            edtUsuario.Text = nmusuario;

            if (nrtmpatv.Trim() == string.Empty)
            {
                edtTmpAtv.Text = "10000";
            }
            else
            {
                edtTmpAtv.Text = nrtmpatv;
            }

            if (NrIP.Trim() != string.Empty)
            {
                string[] Octetos;
                string[] Porta;
                Octetos = NrIP.Split('.');

                edtOct1.Text  = Octetos[0];
                edtOct2.Text  = Octetos[1];
                edtOct3.Text  = Octetos[2];

                Porta = Octetos[3].Split(':');

                edtOct4.Text  = Porta[0];
                edtPorta.Text = Porta[1];
            }

            if (snusu)
            {
                edtUsuario.SelectAll();
                edtUsuario.Focus();
            }
            else
            {
                if (snip)
                {
                    edtOct1.SelectAll();
                    edtOct1.Focus();
                }
                else
                {
                    cbxSnPreEtiq.Focus();
                }
            }
        }

        private void Finaliza()
        {
            Nrip = edtOct1.Text + "." + edtOct2.Text + "." + edtOct3.Text + "." + edtOct4.Text + ":" + edtPorta.Text;
            this.DialogResult = DialogResult.OK;
        }

        private void SIS004_KeyDown(object sender, KeyEventArgs e)
        {
            if ((e.KeyCode == System.Windows.Forms.Keys.Enter))
            {
                if (edtUsuario.Focused)
                {
                    edtOct1.Focus();
                }
                else
                {
                    if (edtOct1.Focused)
                    {
                        if (edtOct1.Text.Trim() != string.Empty)
                        {
                            edtOct2.SelectAll();
                            edtOct2.Focus();
                        }
                    }
                    else
                    {
                        if (edtOct2.Focused)
                        {
                            if (edtOct2.Text.Trim() != string.Empty)
                            {
                                edtOct3.SelectAll();
                                edtOct3.Focus();
                            }
                        }
                        else
                        {
                            if (edtOct3.Focused)
                            {
                                if (edtOct3.Text.Trim() != string.Empty)
                                {
                                    edtOct4.SelectAll();
                                    edtOct4.Focus();
                                }
                            }
                            else
                            {
                                if (edtOct4.Focused)
                                {
                                    if (edtOct4.Text.Trim() != string.Empty)
                                    {
                                        edtPorta.SelectAll();
                                        edtPorta.Focus();
                                    }
                                }
                                else
                                {
                                    if (edtPorta.Focused)
                                    {
                                        if (edtPorta.Text.Trim() != string.Empty)
                                        {
                                            cbxSnPreEtiq.Focus();                                            
                                        }
                                    }
                                    else
                                    {
                                        if (cbxSnPreEtiq.Focused)
                                        {
                                            edtTmpAtv.Focus();                                            
                                        }
                                        else
                                        {
                                            if (edtTmpAtv.Focused)
                                            {
                                                Finaliza();
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }

        private void BrnConfirma_Click(object sender, EventArgs e)
        {
            Finaliza();
        }

        private void btnSair_Click(object sender, EventArgs e)
        {
            this.DialogResult = DialogResult.Cancel;
        }

        private void ImgTeclado_Click(object sender, EventArgs e)
        {
            IptPnlSenha.Enabled = !IptPnlSenha.Enabled;
        }

        private void edtUsuario_KeyPress(object sender, KeyPressEventArgs e)
        {
            lblUsuario.Text = edtUsuario.Text;
        }
                   
    }
}