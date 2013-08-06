using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Text;
using System.Windows.Forms;

namespace LogosMobile
{
    public partial class SIS008 : Form
    {
        string nrfornec = string.Empty;
        string nrclient = string.Empty;

        public string NrFornec {get { return nrfornec; } set { nrfornec = value; }}
        public string NrClient { get { return nrclient; } set { nrclient = value; } }

        public SIS008(string nmusuario)
        {
            InitializeComponent();
            lblUsuario.Text = nmusuario;
            edtEtiqueta.Focus();
        }        

        private void btnSair_Click(object sender, EventArgs e)
        {
            this.Close();
        }

        private void ProcessaEntrada()
        {
            if (edtEtiqueta.Focused)
            {
                if (edtEtiqueta.Text.Length > 38)
                {
                    nrfornec = edtEtiqueta.Text.Substring(37, 10);
                    nrclient = edtEtiqueta.Text.Substring(27, 10).TrimStart('0');
                    this.DialogResult = DialogResult.OK;
                }
            }        
        }

        private void SIS008_KeyDown(object sender, KeyEventArgs e)
        {            
            if ((e.KeyCode == System.Windows.Forms.Keys.Enter))
            {
                ProcessaEntrada();
            }
        }

        private void BtnEtiCli_Click(object sender, EventArgs e)
        {
            edtEtiqueta.Text = "MT000000005102678 78840-000000915518308016682110007";
            edtEtiqueta.Focus();
            ProcessaEntrada();
        }
    }
}