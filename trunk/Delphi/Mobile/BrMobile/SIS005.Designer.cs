namespace LogosMobile
{
    partial class SIS005
    {
        /// <summary>
        /// Required designer variable.
        /// </summary>
        private System.ComponentModel.IContainer components = null;

        /// <summary>
        /// Clean up any resources being used.
        /// </summary>
        /// <param name="disposing">true if managed resources should be disposed; otherwise, false.</param>
        protected override void Dispose(bool disposing)
        {
            if (disposing && (components != null))
            {
                components.Dispose();
            }
            base.Dispose(disposing);
        }

        #region Windows Form Designer generated code

        /// <summary>
        /// Required method for Designer support - do not modify
        /// the contents of this method with the code editor.
        /// </summary>
        private void InitializeComponent()
        {
            System.ComponentModel.ComponentResourceManager resources = new System.ComponentModel.ComponentResourceManager(typeof(SIS005));
            this.lblAviso = new System.Windows.Forms.Label();
            this.BtnFinalizar = new System.Windows.Forms.Button();
            this.lblNrNota = new System.Windows.Forms.Label();
            this.label2 = new System.Windows.Forms.Label();
            this.panel2 = new System.Windows.Forms.Panel();
            this.lblUsuario = new System.Windows.Forms.Label();
            this.ImgTeclado = new System.Windows.Forms.PictureBox();
            this.btnSair = new System.Windows.Forms.Button();
            this.BtnProd1 = new System.Windows.Forms.Button();
            this.lblEtiqueta = new System.Windows.Forms.Label();
            this.edtCodigo = new System.Windows.Forms.TextBox();
            this.lblNrMapa = new System.Windows.Forms.Label();
            this.lblMapa = new System.Windows.Forms.Label();
            this.LblTitulo = new System.Windows.Forms.Label();
            this.label3 = new System.Windows.Forms.Label();
            this.IptPnlSenha = new Microsoft.WindowsCE.Forms.InputPanel();
            this.timerFecha = new System.Windows.Forms.Timer();
            this.btnSemID = new System.Windows.Forms.Button();
            this.BtnCancelIte = new System.Windows.Forms.Button();
            this.edtQtde = new System.Windows.Forms.TextBox();
            this.LblSemId = new System.Windows.Forms.Label();
            this.edtTTSemID = new System.Windows.Forms.TextBox();
            this.pnlAguarde = new System.Windows.Forms.Panel();
            this.lblStatus = new System.Windows.Forms.Label();
            this.BtnProd2 = new System.Windows.Forms.Button();
            this.edtTT = new System.Windows.Forms.TextBox();
            this.label1 = new System.Windows.Forms.Label();
            this.BtnProd3 = new System.Windows.Forms.Button();
            this.button1 = new System.Windows.Forms.Button();
            this.panel2.SuspendLayout();
            this.pnlAguarde.SuspendLayout();
            this.SuspendLayout();
            // 
            // lblAviso
            // 
            this.lblAviso.Font = new System.Drawing.Font("Tahoma", 12F, System.Drawing.FontStyle.Bold);
            this.lblAviso.Location = new System.Drawing.Point(2, 249);
            this.lblAviso.Name = "lblAviso";
            this.lblAviso.Size = new System.Drawing.Size(209, 44);
            this.lblAviso.Text = "Favor, localizar sinal de rede e repetir operação.";
            this.lblAviso.Visible = false;
            // 
            // BtnFinalizar
            // 
            this.BtnFinalizar.BackColor = System.Drawing.Color.White;
            this.BtnFinalizar.Location = new System.Drawing.Point(2, 212);
            this.BtnFinalizar.Name = "BtnFinalizar";
            this.BtnFinalizar.Size = new System.Drawing.Size(131, 20);
            this.BtnFinalizar.TabIndex = 39;
            this.BtnFinalizar.Text = "Finalizar";
            this.BtnFinalizar.Visible = false;
            this.BtnFinalizar.Click += new System.EventHandler(this.Finalizar_Click);
            // 
            // lblNrNota
            // 
            this.lblNrNota.Font = new System.Drawing.Font("Tahoma", 12F, System.Drawing.FontStyle.Bold);
            this.lblNrNota.Location = new System.Drawing.Point(67, 87);
            this.lblNrNota.Name = "lblNrNota";
            this.lblNrNota.Size = new System.Drawing.Size(61, 20);
            this.lblNrNota.Text = "00000";
            // 
            // label2
            // 
            this.label2.Font = new System.Drawing.Font("Tahoma", 12F, System.Drawing.FontStyle.Bold);
            this.label2.Location = new System.Drawing.Point(5, 87);
            this.label2.Name = "label2";
            this.label2.Size = new System.Drawing.Size(61, 20);
            this.label2.Text = "Nota:";
            // 
            // panel2
            // 
            this.panel2.BackColor = System.Drawing.Color.Navy;
            this.panel2.Controls.Add(this.lblUsuario);
            this.panel2.Controls.Add(this.ImgTeclado);
            this.panel2.Controls.Add(this.btnSair);
            this.panel2.Location = new System.Drawing.Point(0, 0);
            this.panel2.Name = "panel2";
            this.panel2.Size = new System.Drawing.Size(240, 27);
            // 
            // lblUsuario
            // 
            this.lblUsuario.Font = new System.Drawing.Font("Tahoma", 9F, System.Drawing.FontStyle.Bold);
            this.lblUsuario.ForeColor = System.Drawing.Color.White;
            this.lblUsuario.Location = new System.Drawing.Point(51, 6);
            this.lblUsuario.Name = "lblUsuario";
            this.lblUsuario.Size = new System.Drawing.Size(139, 17);
            this.lblUsuario.Text = "User";
            // 
            // ImgTeclado
            // 
            this.ImgTeclado.Image = ((System.Drawing.Image)(resources.GetObject("ImgTeclado.Image")));
            this.ImgTeclado.Location = new System.Drawing.Point(5, 4);
            this.ImgTeclado.Name = "ImgTeclado";
            this.ImgTeclado.Size = new System.Drawing.Size(41, 20);
            this.ImgTeclado.SizeMode = System.Windows.Forms.PictureBoxSizeMode.StretchImage;
            this.ImgTeclado.Click += new System.EventHandler(this.ImgTeclado_Click);
            // 
            // btnSair
            // 
            this.btnSair.BackColor = System.Drawing.Color.White;
            this.btnSair.Location = new System.Drawing.Point(205, 4);
            this.btnSair.Name = "btnSair";
            this.btnSair.Size = new System.Drawing.Size(32, 20);
            this.btnSair.TabIndex = 11;
            this.btnSair.Text = "Sair";
            this.btnSair.Click += new System.EventHandler(this.btnSair_Click);
            // 
            // BtnProd1
            // 
            this.BtnProd1.Location = new System.Drawing.Point(154, 297);
            this.BtnProd1.Name = "BtnProd1";
            this.BtnProd1.Size = new System.Drawing.Size(20, 20);
            this.BtnProd1.TabIndex = 12;
            this.BtnProd1.Text = ".";
            this.BtnProd1.Visible = false;
            this.BtnProd1.Click += new System.EventHandler(this.BtnProd1_Click);
            // 
            // lblEtiqueta
            // 
            this.lblEtiqueta.Location = new System.Drawing.Point(3, 113);
            this.lblEtiqueta.Name = "lblEtiqueta";
            this.lblEtiqueta.Size = new System.Drawing.Size(61, 20);
            this.lblEtiqueta.Text = "Código";
            // 
            // edtCodigo
            // 
            this.edtCodigo.Font = new System.Drawing.Font("Tahoma", 12F, System.Drawing.FontStyle.Regular);
            this.edtCodigo.Location = new System.Drawing.Point(3, 136);
            this.edtCodigo.Name = "edtCodigo";
            this.edtCodigo.Size = new System.Drawing.Size(237, 26);
            this.edtCodigo.TabIndex = 38;
            // 
            // lblNrMapa
            // 
            this.lblNrMapa.Font = new System.Drawing.Font("Tahoma", 12F, System.Drawing.FontStyle.Bold);
            this.lblNrMapa.Location = new System.Drawing.Point(67, 58);
            this.lblNrMapa.Name = "lblNrMapa";
            this.lblNrMapa.Size = new System.Drawing.Size(61, 20);
            this.lblNrMapa.Text = "00000";
            // 
            // lblMapa
            // 
            this.lblMapa.Font = new System.Drawing.Font("Tahoma", 12F, System.Drawing.FontStyle.Bold);
            this.lblMapa.Location = new System.Drawing.Point(0, 58);
            this.lblMapa.Name = "lblMapa";
            this.lblMapa.Size = new System.Drawing.Size(61, 20);
            this.lblMapa.Text = "Mapa:";
            // 
            // LblTitulo
            // 
            this.LblTitulo.Font = new System.Drawing.Font("Tahoma", 12F, System.Drawing.FontStyle.Bold);
            this.LblTitulo.Location = new System.Drawing.Point(0, 30);
            this.LblTitulo.Name = "LblTitulo";
            this.LblTitulo.Size = new System.Drawing.Size(240, 18);
            this.LblTitulo.Text = "Conferência de Saída";
            this.LblTitulo.TextAlign = System.Drawing.ContentAlignment.TopCenter;
            // 
            // label3
            // 
            this.label3.Location = new System.Drawing.Point(139, 165);
            this.label3.Name = "label3";
            this.label3.Size = new System.Drawing.Size(89, 16);
            this.label3.Text = "Quantidade";
            // 
            // timerFecha
            // 
            this.timerFecha.Tick += new System.EventHandler(this.timerFecha_Tick);
            // 
            // btnSemID
            // 
            this.btnSemID.BackColor = System.Drawing.Color.White;
            this.btnSemID.Location = new System.Drawing.Point(3, 182);
            this.btnSemID.Name = "btnSemID";
            this.btnSemID.Size = new System.Drawing.Size(130, 26);
            this.btnSemID.TabIndex = 60;
            this.btnSemID.Text = "Sem Identificação";
            this.btnSemID.Click += new System.EventHandler(this.btnQtSemID_Click);
            // 
            // BtnCancelIte
            // 
            this.BtnCancelIte.BackColor = System.Drawing.Color.White;
            this.BtnCancelIte.Location = new System.Drawing.Point(139, 212);
            this.BtnCancelIte.Name = "BtnCancelIte";
            this.BtnCancelIte.Size = new System.Drawing.Size(101, 20);
            this.BtnCancelIte.TabIndex = 70;
            this.BtnCancelIte.Text = "Cancelar Item";
            this.BtnCancelIte.Click += new System.EventHandler(this.BtnCancelIte_Click);
            // 
            // edtQtde
            // 
            this.edtQtde.Enabled = false;
            this.edtQtde.Font = new System.Drawing.Font("Tahoma", 12F, System.Drawing.FontStyle.Regular);
            this.edtQtde.Location = new System.Drawing.Point(139, 182);
            this.edtQtde.Name = "edtQtde";
            this.edtQtde.Size = new System.Drawing.Size(101, 26);
            this.edtQtde.TabIndex = 72;
            // 
            // LblSemId
            // 
            this.LblSemId.Location = new System.Drawing.Point(3, 165);
            this.LblSemId.Name = "LblSemId";
            this.LblSemId.Size = new System.Drawing.Size(135, 16);
            this.LblSemId.Text = "Total sem Identificação";
            this.LblSemId.Visible = false;
            // 
            // edtTTSemID
            // 
            this.edtTTSemID.Enabled = false;
            this.edtTTSemID.Font = new System.Drawing.Font("Tahoma", 12F, System.Drawing.FontStyle.Regular);
            this.edtTTSemID.Location = new System.Drawing.Point(3, 182);
            this.edtTTSemID.Name = "edtTTSemID";
            this.edtTTSemID.ReadOnly = true;
            this.edtTTSemID.Size = new System.Drawing.Size(130, 26);
            this.edtTTSemID.TabIndex = 83;
            this.edtTTSemID.Text = "0";
            this.edtTTSemID.Visible = false;
            // 
            // pnlAguarde
            // 
            this.pnlAguarde.Controls.Add(this.lblStatus);
            this.pnlAguarde.Location = new System.Drawing.Point(0, 113);
            this.pnlAguarde.Name = "pnlAguarde";
            this.pnlAguarde.Size = new System.Drawing.Size(240, 142);
            this.pnlAguarde.Visible = false;
            // 
            // lblStatus
            // 
            this.lblStatus.Font = new System.Drawing.Font("Tahoma", 18F, System.Drawing.FontStyle.Bold);
            this.lblStatus.Location = new System.Drawing.Point(2, 81);
            this.lblStatus.Name = "lblStatus";
            this.lblStatus.Size = new System.Drawing.Size(236, 38);
            this.lblStatus.Text = "Aguarde...";
            this.lblStatus.TextAlign = System.Drawing.ContentAlignment.TopCenter;
            // 
            // BtnProd2
            // 
            this.BtnProd2.Location = new System.Drawing.Point(176, 297);
            this.BtnProd2.Name = "BtnProd2";
            this.BtnProd2.Size = new System.Drawing.Size(20, 20);
            this.BtnProd2.TabIndex = 93;
            this.BtnProd2.Text = "..";
            this.BtnProd2.Visible = false;
            this.BtnProd2.Click += new System.EventHandler(this.BtnProd2_Click);
            // 
            // edtTT
            // 
            this.edtTT.Font = new System.Drawing.Font("Tahoma", 12F, System.Drawing.FontStyle.Regular);
            this.edtTT.Location = new System.Drawing.Point(35, 291);
            this.edtTT.Name = "edtTT";
            this.edtTT.Size = new System.Drawing.Size(116, 26);
            this.edtTT.TabIndex = 115;
            // 
            // label1
            // 
            this.label1.Location = new System.Drawing.Point(1, 297);
            this.label1.Name = "label1";
            this.label1.Size = new System.Drawing.Size(41, 20);
            this.label1.Text = "Total:";
            // 
            // BtnProd3
            // 
            this.BtnProd3.Location = new System.Drawing.Point(198, 297);
            this.BtnProd3.Name = "BtnProd3";
            this.BtnProd3.Size = new System.Drawing.Size(20, 20);
            this.BtnProd3.TabIndex = 128;
            this.BtnProd3.Text = "...";
            this.BtnProd3.Visible = false;
            this.BtnProd3.Click += new System.EventHandler(this.button1_Click);
            // 
            // button1
            // 
            this.button1.Location = new System.Drawing.Point(220, 297);
            this.button1.Name = "button1";
            this.button1.Size = new System.Drawing.Size(20, 20);
            this.button1.TabIndex = 141;
            this.button1.Text = "IV";
            this.button1.Visible = false;
            this.button1.Click += new System.EventHandler(this.button1_Click_1);
            // 
            // SIS005
            // 
            this.AutoScaleDimensions = new System.Drawing.SizeF(96F, 96F);
            this.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Dpi;
            this.AutoScroll = true;
            this.ClientSize = new System.Drawing.Size(240, 320);
            this.Controls.Add(this.button1);
            this.Controls.Add(this.BtnProd3);
            this.Controls.Add(this.pnlAguarde);
            this.Controls.Add(this.edtTT);
            this.Controls.Add(this.label1);
            this.Controls.Add(this.BtnProd2);
            this.Controls.Add(this.BtnProd1);
            this.Controls.Add(this.LblSemId);
            this.Controls.Add(this.edtTTSemID);
            this.Controls.Add(this.edtQtde);
            this.Controls.Add(this.BtnCancelIte);
            this.Controls.Add(this.btnSemID);
            this.Controls.Add(this.lblAviso);
            this.Controls.Add(this.label3);
            this.Controls.Add(this.LblTitulo);
            this.Controls.Add(this.BtnFinalizar);
            this.Controls.Add(this.lblNrNota);
            this.Controls.Add(this.label2);
            this.Controls.Add(this.panel2);
            this.Controls.Add(this.lblEtiqueta);
            this.Controls.Add(this.edtCodigo);
            this.Controls.Add(this.lblNrMapa);
            this.Controls.Add(this.lblMapa);
            this.KeyPreview = true;
            this.Location = new System.Drawing.Point(0, 0);
            this.Name = "SIS005";
            this.Text = "SIS005";
            this.WindowState = System.Windows.Forms.FormWindowState.Maximized;
            this.Closed += new System.EventHandler(this.SIS005_Closed);
            this.KeyDown += new System.Windows.Forms.KeyEventHandler(this.SIS005_KeyDown);
            this.panel2.ResumeLayout(false);
            this.pnlAguarde.ResumeLayout(false);
            this.ResumeLayout(false);

        }

        #endregion

        private System.Windows.Forms.Label lblAviso;
        private System.Windows.Forms.Button BtnFinalizar;
        private System.Windows.Forms.Label lblNrNota;
        private System.Windows.Forms.Label label2;
        private System.Windows.Forms.Panel panel2;
        private System.Windows.Forms.Button btnSair;
        private System.Windows.Forms.Label lblEtiqueta;
        private System.Windows.Forms.TextBox edtCodigo;
        private System.Windows.Forms.Label lblNrMapa;
        private System.Windows.Forms.Label lblMapa;
        private System.Windows.Forms.Label LblTitulo;
        private System.Windows.Forms.Label label3;
        private Microsoft.WindowsCE.Forms.InputPanel IptPnlSenha;
        private System.Windows.Forms.PictureBox ImgTeclado;
        private System.Windows.Forms.Timer timerFecha;
        private System.Windows.Forms.Button btnSemID;
        private System.Windows.Forms.Button BtnCancelIte;
        private System.Windows.Forms.TextBox edtQtde;
        private System.Windows.Forms.Label LblSemId;
        private System.Windows.Forms.TextBox edtTTSemID;
        private System.Windows.Forms.Panel pnlAguarde;
        private System.Windows.Forms.Label lblStatus;
        private System.Windows.Forms.Label lblUsuario;
        private System.Windows.Forms.Button BtnProd1;
        private System.Windows.Forms.Button BtnProd2;
        private System.Windows.Forms.TextBox edtTT;
        private System.Windows.Forms.Label label1;
        private System.Windows.Forms.Button BtnProd3;
        private System.Windows.Forms.Button button1;
    }
}