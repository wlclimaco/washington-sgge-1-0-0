namespace LogosMobile
{
    partial class SIS002
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
            System.ComponentModel.ComponentResourceManager resources = new System.ComponentModel.ComponentResourceManager(typeof(SIS002));
            this.lblMapa = new System.Windows.Forms.Label();
            this.lblNrMapa = new System.Windows.Forms.Label();
            this.edtCodigo = new System.Windows.Forms.TextBox();
            this.lblEtiqueta = new System.Windows.Forms.Label();
            this.panel2 = new System.Windows.Forms.Panel();
            this.lblUsuario = new System.Windows.Forms.Label();
            this.ImgTeclado = new System.Windows.Forms.PictureBox();
            this.btnSair = new System.Windows.Forms.Button();
            this.lblNrNota = new System.Windows.Forms.Label();
            this.label2 = new System.Windows.Forms.Label();
            this.BtnFinalizar = new System.Windows.Forms.Button();
            this.lblAviso = new System.Windows.Forms.Label();
            this.LblTitulo = new System.Windows.Forms.Label();
            this.label3 = new System.Windows.Forms.Label();
            this.edtQtde = new System.Windows.Forms.TextBox();
            this.BtnCanItem = new System.Windows.Forms.Button();
            this.label4 = new System.Windows.Forms.Label();
            this.edtLote = new System.Windows.Forms.TextBox();
            this.label5 = new System.Windows.Forms.Label();
            this.edtValidade = new System.Windows.Forms.TextBox();
            this.IptPnlSenha = new Microsoft.WindowsCE.Forms.InputPanel();
            this.timerFecha = new System.Windows.Forms.Timer();
            this.LblSemId = new System.Windows.Forms.Label();
            this.edtTTSemID = new System.Windows.Forms.TextBox();
            this.btnSemID = new System.Windows.Forms.Button();
            this.pnlStatus = new System.Windows.Forms.Panel();
            this.lblStatus = new System.Windows.Forms.Label();
            this.BtnProd2 = new System.Windows.Forms.Button();
            this.BtnProd1 = new System.Windows.Forms.Button();
            this.label1 = new System.Windows.Forms.Label();
            this.edtTT = new System.Windows.Forms.TextBox();
            this.BtnProd3 = new System.Windows.Forms.Button();
            this.panel2.SuspendLayout();
            this.pnlStatus.SuspendLayout();
            this.SuspendLayout();
            // 
            // lblMapa
            // 
            this.lblMapa.Font = new System.Drawing.Font("Tahoma", 12F, System.Drawing.FontStyle.Bold);
            this.lblMapa.Location = new System.Drawing.Point(0, 51);
            this.lblMapa.Name = "lblMapa";
            this.lblMapa.Size = new System.Drawing.Size(93, 20);
            this.lblMapa.Text = "Operação:";
            // 
            // lblNrMapa
            // 
            this.lblNrMapa.Font = new System.Drawing.Font("Tahoma", 12F, System.Drawing.FontStyle.Bold);
            this.lblNrMapa.Location = new System.Drawing.Point(93, 51);
            this.lblNrMapa.Name = "lblNrMapa";
            this.lblNrMapa.Size = new System.Drawing.Size(61, 20);
            this.lblNrMapa.Text = "00000";
            // 
            // edtCodigo
            // 
            this.edtCodigo.Font = new System.Drawing.Font("Tahoma", 12F, System.Drawing.FontStyle.Regular);
            this.edtCodigo.Location = new System.Drawing.Point(3, 108);
            this.edtCodigo.Name = "edtCodigo";
            this.edtCodigo.Size = new System.Drawing.Size(237, 26);
            this.edtCodigo.TabIndex = 3;
            // 
            // lblEtiqueta
            // 
            this.lblEtiqueta.Location = new System.Drawing.Point(3, 92);
            this.lblEtiqueta.Name = "lblEtiqueta";
            this.lblEtiqueta.Size = new System.Drawing.Size(61, 15);
            this.lblEtiqueta.Text = "Etiqueta";
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
            this.lblUsuario.Location = new System.Drawing.Point(60, 6);
            this.lblUsuario.Name = "lblUsuario";
            this.lblUsuario.Size = new System.Drawing.Size(139, 17);
            this.lblUsuario.Text = "User";
            // 
            // ImgTeclado
            // 
            this.ImgTeclado.Image = ((System.Drawing.Image)(resources.GetObject("ImgTeclado.Image")));
            this.ImgTeclado.Location = new System.Drawing.Point(4, 3);
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
            // lblNrNota
            // 
            this.lblNrNota.Font = new System.Drawing.Font("Tahoma", 12F, System.Drawing.FontStyle.Bold);
            this.lblNrNota.Location = new System.Drawing.Point(93, 71);
            this.lblNrNota.Name = "lblNrNota";
            this.lblNrNota.Size = new System.Drawing.Size(61, 20);
            this.lblNrNota.Text = "00000";
            // 
            // label2
            // 
            this.label2.Font = new System.Drawing.Font("Tahoma", 12F, System.Drawing.FontStyle.Bold);
            this.label2.Location = new System.Drawing.Point(38, 71);
            this.label2.Name = "label2";
            this.label2.Size = new System.Drawing.Size(51, 20);
            this.label2.Text = "Nota:";
            // 
            // BtnFinalizar
            // 
            this.BtnFinalizar.BackColor = System.Drawing.Color.White;
            this.BtnFinalizar.Location = new System.Drawing.Point(3, 232);
            this.BtnFinalizar.Name = "BtnFinalizar";
            this.BtnFinalizar.Size = new System.Drawing.Size(131, 20);
            this.BtnFinalizar.TabIndex = 14;
            this.BtnFinalizar.Text = "Finalizar";
            this.BtnFinalizar.Visible = false;
            this.BtnFinalizar.Click += new System.EventHandler(this.Finalizar_Click);
            // 
            // lblAviso
            // 
            this.lblAviso.Font = new System.Drawing.Font("Tahoma", 12F, System.Drawing.FontStyle.Bold);
            this.lblAviso.Location = new System.Drawing.Point(3, 255);
            this.lblAviso.Name = "lblAviso";
            this.lblAviso.Size = new System.Drawing.Size(209, 44);
            this.lblAviso.Text = "Favor, localizar sinal de rede e repetir operação.";
            this.lblAviso.Visible = false;
            // 
            // LblTitulo
            // 
            this.LblTitulo.Font = new System.Drawing.Font("Tahoma", 12F, System.Drawing.FontStyle.Bold);
            this.LblTitulo.Location = new System.Drawing.Point(0, 30);
            this.LblTitulo.Name = "LblTitulo";
            this.LblTitulo.Size = new System.Drawing.Size(240, 18);
            this.LblTitulo.Text = "Conferência de Entrada";
            this.LblTitulo.TextAlign = System.Drawing.ContentAlignment.TopCenter;
            // 
            // label3
            // 
            this.label3.Location = new System.Drawing.Point(135, 183);
            this.label3.Name = "label3";
            this.label3.Size = new System.Drawing.Size(70, 16);
            this.label3.Text = "Quantidade";
            // 
            // edtQtde
            // 
            this.edtQtde.Enabled = false;
            this.edtQtde.Font = new System.Drawing.Font("Tahoma", 12F, System.Drawing.FontStyle.Regular);
            this.edtQtde.Location = new System.Drawing.Point(135, 200);
            this.edtQtde.Name = "edtQtde";
            this.edtQtde.Size = new System.Drawing.Size(105, 26);
            this.edtQtde.TabIndex = 38;
            // 
            // BtnCanItem
            // 
            this.BtnCanItem.BackColor = System.Drawing.Color.White;
            this.BtnCanItem.Location = new System.Drawing.Point(135, 232);
            this.BtnCanItem.Name = "BtnCanItem";
            this.BtnCanItem.Size = new System.Drawing.Size(105, 20);
            this.BtnCanItem.TabIndex = 40;
            this.BtnCanItem.Text = "Cancelar Item";
            this.BtnCanItem.Visible = false;
            this.BtnCanItem.Click += new System.EventHandler(this.BtnCanItem_Click);
            // 
            // label4
            // 
            this.label4.Location = new System.Drawing.Point(3, 137);
            this.label4.Name = "label4";
            this.label4.Size = new System.Drawing.Size(70, 16);
            this.label4.Text = "Lote";
            // 
            // edtLote
            // 
            this.edtLote.Enabled = false;
            this.edtLote.Font = new System.Drawing.Font("Tahoma", 12F, System.Drawing.FontStyle.Regular);
            this.edtLote.Location = new System.Drawing.Point(3, 153);
            this.edtLote.Name = "edtLote";
            this.edtLote.Size = new System.Drawing.Size(131, 26);
            this.edtLote.TabIndex = 42;
            // 
            // label5
            // 
            this.label5.Location = new System.Drawing.Point(135, 137);
            this.label5.Name = "label5";
            this.label5.Size = new System.Drawing.Size(56, 16);
            this.label5.Text = "Validade";
            // 
            // edtValidade
            // 
            this.edtValidade.Enabled = false;
            this.edtValidade.Font = new System.Drawing.Font("Tahoma", 12F, System.Drawing.FontStyle.Regular);
            this.edtValidade.Location = new System.Drawing.Point(135, 153);
            this.edtValidade.Name = "edtValidade";
            this.edtValidade.Size = new System.Drawing.Size(105, 26);
            this.edtValidade.TabIndex = 45;
            // 
            // timerFecha
            // 
            this.timerFecha.Tick += new System.EventHandler(this.timerFecha_Tick);
            // 
            // LblSemId
            // 
            this.LblSemId.Location = new System.Drawing.Point(3, 183);
            this.LblSemId.Name = "LblSemId";
            this.LblSemId.Size = new System.Drawing.Size(132, 16);
            this.LblSemId.Text = "Total sem Identificação";
            this.LblSemId.Visible = false;
            // 
            // edtTTSemID
            // 
            this.edtTTSemID.Enabled = false;
            this.edtTTSemID.Font = new System.Drawing.Font("Tahoma", 12F, System.Drawing.FontStyle.Regular);
            this.edtTTSemID.Location = new System.Drawing.Point(4, 200);
            this.edtTTSemID.Name = "edtTTSemID";
            this.edtTTSemID.ReadOnly = true;
            this.edtTTSemID.Size = new System.Drawing.Size(130, 26);
            this.edtTTSemID.TabIndex = 73;
            this.edtTTSemID.Text = "0";
            this.edtTTSemID.Visible = false;
            // 
            // btnSemID
            // 
            this.btnSemID.BackColor = System.Drawing.Color.White;
            this.btnSemID.Location = new System.Drawing.Point(3, 200);
            this.btnSemID.Name = "btnSemID";
            this.btnSemID.Size = new System.Drawing.Size(131, 26);
            this.btnSemID.TabIndex = 85;
            this.btnSemID.Text = "Sem Identificação";
            this.btnSemID.Click += new System.EventHandler(this.btnSemID_Click);
            // 
            // pnlStatus
            // 
            this.pnlStatus.Controls.Add(this.lblStatus);
            this.pnlStatus.Location = new System.Drawing.Point(0, 95);
            this.pnlStatus.Name = "pnlStatus";
            this.pnlStatus.Size = new System.Drawing.Size(240, 158);
            this.pnlStatus.Visible = false;
            // 
            // lblStatus
            // 
            this.lblStatus.Font = new System.Drawing.Font("Tahoma", 18F, System.Drawing.FontStyle.Bold);
            this.lblStatus.Location = new System.Drawing.Point(3, 87);
            this.lblStatus.Name = "lblStatus";
            this.lblStatus.Size = new System.Drawing.Size(235, 38);
            this.lblStatus.Text = "Aguarde...";
            this.lblStatus.TextAlign = System.Drawing.ContentAlignment.TopCenter;
            // 
            // BtnProd2
            // 
            this.BtnProd2.Location = new System.Drawing.Point(193, 297);
            this.BtnProd2.Name = "BtnProd2";
            this.BtnProd2.Size = new System.Drawing.Size(20, 20);
            this.BtnProd2.TabIndex = 99;
            this.BtnProd2.Text = "..";
            this.BtnProd2.Visible = false;
            this.BtnProd2.Click += new System.EventHandler(this.BtnProd2_Click);
            // 
            // BtnProd1
            // 
            this.BtnProd1.Location = new System.Drawing.Point(171, 297);
            this.BtnProd1.Name = "BtnProd1";
            this.BtnProd1.Size = new System.Drawing.Size(20, 20);
            this.BtnProd1.TabIndex = 98;
            this.BtnProd1.Text = ".";
            this.BtnProd1.Visible = false;
            this.BtnProd1.Click += new System.EventHandler(this.BtnProd1_Click);
            // 
            // label1
            // 
            this.label1.Location = new System.Drawing.Point(4, 297);
            this.label1.Name = "label1";
            this.label1.Size = new System.Drawing.Size(41, 20);
            this.label1.Text = "Total:";
            // 
            // edtTT
            // 
            this.edtTT.Font = new System.Drawing.Font("Tahoma", 12F, System.Drawing.FontStyle.Regular);
            this.edtTT.Location = new System.Drawing.Point(38, 291);
            this.edtTT.Name = "edtTT";
            this.edtTT.Size = new System.Drawing.Size(116, 26);
            this.edtTT.TabIndex = 113;
            // 
            // BtnProd3
            // 
            this.BtnProd3.Location = new System.Drawing.Point(215, 297);
            this.BtnProd3.Name = "BtnProd3";
            this.BtnProd3.Size = new System.Drawing.Size(20, 20);
            this.BtnProd3.TabIndex = 127;
            this.BtnProd3.Text = "...";
            this.BtnProd3.Visible = false;
            this.BtnProd3.Click += new System.EventHandler(this.button1_Click);
            // 
            // SIS002
            // 
            this.AutoScaleDimensions = new System.Drawing.SizeF(96F, 96F);
            this.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Dpi;
            this.AutoScroll = true;
            this.ClientSize = new System.Drawing.Size(240, 320);
            this.Controls.Add(this.BtnProd3);
            this.Controls.Add(this.pnlStatus);
            this.Controls.Add(this.edtTT);
            this.Controls.Add(this.label1);
            this.Controls.Add(this.BtnProd2);
            this.Controls.Add(this.BtnProd1);
            this.Controls.Add(this.btnSemID);
            this.Controls.Add(this.LblSemId);
            this.Controls.Add(this.edtTTSemID);
            this.Controls.Add(this.label5);
            this.Controls.Add(this.edtValidade);
            this.Controls.Add(this.label4);
            this.Controls.Add(this.edtLote);
            this.Controls.Add(this.BtnCanItem);
            this.Controls.Add(this.label3);
            this.Controls.Add(this.edtQtde);
            this.Controls.Add(this.LblTitulo);
            this.Controls.Add(this.lblAviso);
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
            this.Name = "SIS002";
            this.Text = "S002";
            this.WindowState = System.Windows.Forms.FormWindowState.Maximized;
            this.Closed += new System.EventHandler(this.SIS002_Closed);
            this.KeyDown += new System.Windows.Forms.KeyEventHandler(this.SIS002_KeyDown);
            this.panel2.ResumeLayout(false);
            this.pnlStatus.ResumeLayout(false);
            this.ResumeLayout(false);

        }

        #endregion

        private System.Windows.Forms.Label lblMapa;
        private System.Windows.Forms.Label lblNrMapa;
        private System.Windows.Forms.TextBox edtCodigo;
        private System.Windows.Forms.Label lblEtiqueta;
        private System.Windows.Forms.Panel panel2;
        private System.Windows.Forms.Button btnSair;
        private System.Windows.Forms.Label lblNrNota;
        private System.Windows.Forms.Label label2;
        private System.Windows.Forms.Button BtnFinalizar;
        private System.Windows.Forms.Label lblAviso;
        private System.Windows.Forms.Label LblTitulo;
        private System.Windows.Forms.Label label3;
        private System.Windows.Forms.TextBox edtQtde;
        private System.Windows.Forms.Button BtnCanItem;
        private System.Windows.Forms.Label label4;
        private System.Windows.Forms.TextBox edtLote;
        private System.Windows.Forms.Label label5;
        private System.Windows.Forms.TextBox edtValidade;
        private System.Windows.Forms.PictureBox ImgTeclado;
        private Microsoft.WindowsCE.Forms.InputPanel IptPnlSenha;
        private System.Windows.Forms.Timer timerFecha;
        private System.Windows.Forms.Label LblSemId;
        private System.Windows.Forms.TextBox edtTTSemID;
        private System.Windows.Forms.Button btnSemID;
        private System.Windows.Forms.Panel pnlStatus;
        private System.Windows.Forms.Label lblStatus;
        private System.Windows.Forms.Label lblUsuario;
        private System.Windows.Forms.Button BtnProd2;
        private System.Windows.Forms.Button BtnProd1;
        private System.Windows.Forms.Label label1;
        private System.Windows.Forms.TextBox edtTT;
        private System.Windows.Forms.Button BtnProd3;
    }
}