using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Text;
using System.Windows.Forms;
using System.IO;
using LogosMobile;

namespace LogosMobile
{
    public partial class SIS001 : Form
    {
        string nmusuario = string.Empty;

        public string NmUsuario
        {
            get { return nmusuario; }
            set { nmusuario = value; }
        }

        public SIS001()
        {
            InitializeComponent();
            ImgBackground.Image = LogosMobile.Properties.Resources.Background;            

            string nrip      = string.Empty;
            string lastuser  = string.Empty;
            string snpreetiq = string.Empty;
            string nrtmpatv  = string.Empty;

            Controller.GetConfig(ref nrip, ref lastuser, ref snpreetiq, ref nrtmpatv);
                
            EdtCdUsuari.Text = lastuser;
            EdtCdUsuari.Focus();
        }

        private void ValidaEntrada()
        {
            Cursor.Current = Cursors.WaitCursor;

            if (EdtCdUsuari.Focused == true)
            {
                if (EdtCdUsuari.Text.Trim() != string.Empty)
                {
                    EdtDsSenha.Focus();
                    Cursor.Current = Cursors.Default;
                }
                else
                {
                    Cursor.Current = Cursors.Default;
                    Controller.ShowMessage("Informe o usuario para acessar!");
                }
            }
            else
            {
                if (EdtDsSenha.Focused == true)
                {
                    if (EdtDsSenha.Text.Trim() != string.Empty)
                    {
                        if (Controller.WsLogos.WsAutenticaUsuario(EdtCdUsuari.Text, EdtDsSenha.Text))
                        {                            
                            NmUsuario = EdtCdUsuari.Text;
                            Controller.SetLastUser(NmUsuario);
                            DialogResult = DialogResult.OK;
                        }
                        else
                        {
                            EdtDsSenha.Focus();
                            EdtDsSenha.SelectAll();
                        }
                    }
                    else
                    {
                        Controller.ShowMessage("Senha não informada!!!");
                        EdtDsSenha.Focus();
                    }
                }
            }
        }

        private void BtnConfirma_Click(object sender, EventArgs e)
        {
            EdtDsSenha.Focus();
            ValidaEntrada();
        }        

        private void SIS000_KeyDown(object sender, KeyEventArgs e)
        {
            if ((e.KeyCode == System.Windows.Forms.Keys.Enter))
            {
                ValidaEntrada();
            }
        }       
        
        private void btnSair_Click(object sender, EventArgs e)
        {
            this.Close();
        }

        private void ImgTeclado_Click(object sender, EventArgs e)
        {
            IptPnlSenha.Enabled = !IptPnlSenha.Enabled;
        }

        private void BtnVersao_Click(object sender, EventArgs e)
        {
            SIS003 FrmAbout = new SIS003(EdtCdUsuari.Text);
            FrmAbout.ShowDialog();
            FrmAbout.Dispose();
            EdtCdUsuari.Focus();
        }

        private void imgConfNet_Click(object sender, EventArgs e)
        {
            string nrip      = string.Empty;
            string lastuser  = string.Empty;
            string snpreetiq = string.Empty;
            string nrtmpatv  = string.Empty;

            Controller.GetConfig(ref nrip, ref lastuser, ref snpreetiq, ref nrtmpatv);

            SIS004 FrmIP = new SIS004(nrip, EdtCdUsuari.Text, snpreetiq, true, true, true, nrtmpatv);
            FrmIP.ShowDialog();

            if (FrmIP.DialogResult == DialogResult.OK)
            {
                Controller.SetConfig(FrmIP.Nrip, FrmIP.LastUser, FrmIP.SnPreEtiq, FrmIP.TmpAtv);
                EdtCdUsuari.Text = FrmIP.LastUser;
                Controller.WsLogos.SetURL(FrmIP.Nrip);                
            }

            FrmIP.Dispose();
        }

        private void pictureBox1_Click(object sender, EventArgs e)
        {
            if (Controller.MessageDlg("Realizar teste de rede agora?"))
            {
                SIS009 frmtestacon = new SIS009();
                frmtestacon.ShowDialog();
                frmtestacon.Dispose();            
            }
        }        
    }
}