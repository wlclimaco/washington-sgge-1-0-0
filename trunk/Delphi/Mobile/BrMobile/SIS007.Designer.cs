namespace LogosMobile
{
    partial class SIS007
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
            this.lblEtiqueta = new System.Windows.Forms.Label();
            this.edtCodigo = new System.Windows.Forms.TextBox();
            this.label1 = new System.Windows.Forms.Label();
            this.edtSSCC = new System.Windows.Forms.TextBox();
            this.panel2 = new System.Windows.Forms.Panel();
            this.lblUsuario = new System.Windows.Forms.Label();
            this.btnSair = new System.Windows.Forms.Button();
            this.btnFinalizar = new System.Windows.Forms.Button();
            this.BtnSemIdent = new System.Windows.Forms.Button();
            this.pnlAguarde = new System.Windows.Forms.Panel();
            this.lblStatus = new System.Windows.Forms.Label();
            this.LblOperac = new System.Windows.Forms.Label();
            this.lblNrNota = new System.Windows.Forms.Label();
            this.label2 = new System.Windows.Forms.Label();
            this.lblNrMapa = new System.Windows.Forms.Label();
            this.lblMapa = new System.Windows.Forms.Label();
            this.label3 = new System.Windows.Forms.Label();
            this.edtLote = new System.Windows.Forms.TextBox();
            this.label4 = new System.Windows.Forms.Label();
            this.edtValidade = new System.Windows.Forms.TextBox();
            this.label5 = new System.Windows.Forms.Label();
            this.edtProducao = new System.Windows.Forms.TextBox();
            this.panel2.SuspendLayout();
            this.pnlAguarde.SuspendLayout();
            this.SuspendLayout();
            // 
            // lblEtiqueta
            // 
            this.lblEtiqueta.Font = new System.Drawing.Font("Tahoma", 12F, System.Drawing.FontStyle.Bold);
            this.lblEtiqueta.Location = new System.Drawing.Point(0, 105);
            this.lblEtiqueta.Name = "lblEtiqueta";
            this.lblEtiqueta.Size = new System.Drawing.Size(72, 20);
            this.lblEtiqueta.Text = "Produto";
            // 
            // edtCodigo
            // 
            this.edtCodigo.Font = new System.Drawing.Font("Tahoma", 12F, System.Drawing.FontStyle.Regular);
            this.edtCodigo.Location = new System.Drawing.Point(74, 103);
            this.edtCodigo.Name = "edtCodigo";
            this.edtCodigo.Size = new System.Drawing.Size(163, 26);
            this.edtCodigo.TabIndex = 40;
            // 
            // label1
            // 
            this.label1.Font = new System.Drawing.Font("Tahoma", 12F, System.Drawing.FontStyle.Bold);
            this.label1.Location = new System.Drawing.Point(21, 138);
            this.label1.Name = "label1";
            this.label1.Size = new System.Drawing.Size(49, 20);
            this.label1.Text = "SSCC";
            // 
            // edtSSCC
            // 
            this.edtSSCC.Font = new System.Drawing.Font("Tahoma", 12F, System.Drawing.FontStyle.Regular);
            this.edtSSCC.Location = new System.Drawing.Point(74, 135);
            this.edtSSCC.Name = "edtSSCC";
            this.edtSSCC.Size = new System.Drawing.Size(163, 26);
            this.edtSSCC.TabIndex = 42;
            // 
            // panel2
            // 
            this.panel2.BackColor = System.Drawing.Color.Navy;
            this.panel2.Controls.Add(this.lblUsuario);
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
            // btnFinalizar
            // 
            this.btnFinalizar.BackColor = System.Drawing.Color.White;
            this.btnFinalizar.Location = new System.Drawing.Point(3, 252);
            this.btnFinalizar.Name = "btnFinalizar";
            this.btnFinalizar.Size = new System.Drawing.Size(72, 20);
            this.btnFinalizar.TabIndex = 46;
            this.btnFinalizar.Text = "Finalizar";
            this.btnFinalizar.Click += new System.EventHandler(this.BtnFinalizar_Click);
            // 
            // BtnSemIdent
            // 
            this.BtnSemIdent.BackColor = System.Drawing.Color.White;
            this.BtnSemIdent.Location = new System.Drawing.Point(112, 252);
            this.BtnSemIdent.Name = "BtnSemIdent";
            this.BtnSemIdent.Size = new System.Drawing.Size(125, 20);
            this.BtnSemIdent.TabIndex = 47;
            this.BtnSemIdent.Text = "Sem Identifica��o";
            this.BtnSemIdent.Click += new System.EventHandler(this.BtnSemIdent_Click);
            // 
            // pnlAguarde
            // 
            this.pnlAguarde.Controls.Add(this.lblStatus);
            this.pnlAguarde.Location = new System.Drawing.Point(0, 97);
            this.pnlAguarde.Name = "pnlAguarde";
            this.pnlAguarde.Size = new System.Drawing.Size(240, 186);
            this.pnlAguarde.Visible = false;
            // 
            // lblStatus
            // 
            this.lblStatus.Font = new System.Drawing.Font("Tahoma", 18F, System.Drawing.FontStyle.Bold);
            this.lblStatus.Location = new System.Drawing.Point(1, 36);
            this.lblStatus.Name = "lblStatus";
            this.lblStatus.Size = new System.Drawing.Size(238, 38);
            this.lblStatus.Text = "Aguarde...";
            this.lblStatus.TextAlign = System.Drawing.ContentAlignment.TopCenter;
            // 
            // LblOperac
            // 
            this.LblOperac.Font = new System.Drawing.Font("Tahoma", 12F, System.Drawing.FontStyle.Bold);
            this.LblOperac.Location = new System.Drawing.Point(0, 30);
            this.LblOperac.Name = "LblOperac";
            this.LblOperac.Size = new System.Drawing.Size(240, 20);
            this.LblOperac.Text = "Opera��o";
            this.LblOperac.TextAlign = System.Drawing.ContentAlignment.TopCenter;
            // 
            // lblNrNota
            // 
            this.lblNrNota.Font = new System.Drawing.Font("Tahoma", 12F, System.Drawing.FontStyle.Bold);
            this.lblNrNota.Location = new System.Drawing.Point(95, 75);
            this.lblNrNota.Name = "lblNrNota";
            this.lblNrNota.Size = new System.Drawing.Size(61, 20);
            this.lblNrNota.Text = "00000";
            // 
            // label2
            // 
            this.label2.Font = new System.Drawing.Font("Tahoma", 12F, System.Drawing.FontStyle.Bold);
            this.label2.Location = new System.Drawing.Point(40, 75);
            this.label2.Name = "label2";
            this.label2.Size = new System.Drawing.Size(51, 20);
            this.label2.Text = "Nota:";
            // 
            // lblNrMapa
            // 
            this.lblNrMapa.Font = new System.Drawing.Font("Tahoma", 12F, System.Drawing.FontStyle.Bold);
            this.lblNrMapa.Location = new System.Drawing.Point(95, 55);
            this.lblNrMapa.Name = "lblNrMapa";
            this.lblNrMapa.Size = new System.Drawing.Size(61, 20);
            this.lblNrMapa.Text = "00000";
            // 
            // lblMapa
            // 
            this.lblMapa.Font = new System.Drawing.Font("Tahoma", 12F, System.Drawing.FontStyle.Bold);
            this.lblMapa.Location = new System.Drawing.Point(2, 55);
            this.lblMapa.Name = "lblMapa";
            this.lblMapa.Size = new System.Drawing.Size(93, 20);
            this.lblMapa.Text = "Opera��o:";
            // 
            // label3
            // 
            this.label3.Font = new System.Drawing.Font("Tahoma", 12F, System.Drawing.FontStyle.Bold);
            this.label3.Location = new System.Drawing.Point(25, 168);
            this.label3.Name = "label3";
            this.label3.Size = new System.Drawing.Size(43, 20);
            this.label3.Text = "Lote";
            // 
            // edtLote
            // 
            this.edtLote.Font = new System.Drawing.Font("Tahoma", 12F, System.Drawing.FontStyle.Regular);
            this.edtLote.Location = new System.Drawing.Point(74, 167);
            this.edtLote.Name = "edtLote";
            this.edtLote.Size = new System.Drawing.Size(163, 26);
            this.edtLote.TabIndex = 52;
            // 
            // label4
            // 
            this.label4.Font = new System.Drawing.Font("Tahoma", 12F, System.Drawing.FontStyle.Bold);
            this.label4.Location = new System.Drawing.Point(70, 196);
            this.label4.Name = "label4";
            this.label4.Size = new System.Drawing.Size(77, 20);
            this.label4.Text = "Validade";
            // 
            // edtValidade
            // 
            this.edtValidade.Font = new System.Drawing.Font("Tahoma", 12F, System.Drawing.FontStyle.Regular);
            this.edtValidade.Location = new System.Drawing.Point(73, 216);
            this.edtValidade.Name = "edtValidade";
            this.edtValidade.Size = new System.Drawing.Size(74, 26);
            this.edtValidade.TabIndex = 55;
            // 
            // label5
            // 
            this.label5.Font = new System.Drawing.Font("Tahoma", 12F, System.Drawing.FontStyle.Bold);
            this.label5.Location = new System.Drawing.Point(156, 196);
            this.label5.Name = "label5";
            this.label5.Size = new System.Drawing.Size(84, 20);
            this.label5.Text = "Produ��o";
            // 
            // edtProducao
            // 
            this.edtProducao.Font = new System.Drawing.Font("Tahoma", 12F, System.Drawing.FontStyle.Regular);
            this.edtProducao.Location = new System.Drawing.Point(156, 216);
            this.edtProducao.Name = "edtProducao";
            this.edtProducao.Size = new System.Drawing.Size(74, 26);
            this.edtProducao.TabIndex = 58;
            // 
            // SIS007
            // 
            this.AutoScaleDimensions = new System.Drawing.SizeF(96F, 96F);
            this.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Dpi;
            this.AutoScroll = true;
            this.ClientSize = new System.Drawing.Size(240, 320);
            this.Controls.Add(this.pnlAguarde);
            this.Controls.Add(this.label5);
            this.Controls.Add(this.edtProducao);
            this.Controls.Add(this.label4);
            this.Controls.Add(this.edtValidade);
            this.Controls.Add(this.label3);
            this.Controls.Add(this.edtLote);
            this.Controls.Add(this.lblNrNota);
            this.Controls.Add(this.label2);
            this.Controls.Add(this.lblNrMapa);
            this.Controls.Add(this.lblMapa);
            this.Controls.Add(this.LblOperac);
            this.Controls.Add(this.BtnSemIdent);
            this.Controls.Add(this.btnFinalizar);
            this.Controls.Add(this.panel2);
            this.Controls.Add(this.label1);
            this.Controls.Add(this.edtSSCC);
            this.Controls.Add(this.lblEtiqueta);
            this.Controls.Add(this.edtCodigo);
            this.KeyPreview = true;
            this.Location = new System.Drawing.Point(0, 0);
            this.Name = "SIS007";
            this.Text = "SIS007";
            this.WindowState = System.Windows.Forms.FormWindowState.Maximized;
            this.KeyDown += new System.Windows.Forms.KeyEventHandler(this.SIS007_KeyDown);
            this.panel2.ResumeLayout(false);
            this.pnlAguarde.ResumeLayout(false);
            this.ResumeLayout(false);

        }

        #endregion

        private System.Windows.Forms.Label lblEtiqueta;
        private System.Windows.Forms.TextBox edtCodigo;
        private System.Windows.Forms.Label label1;
        private System.Windows.Forms.TextBox edtSSCC;
        private System.Windows.Forms.Panel panel2;
        private System.Windows.Forms.Button btnSair;
        private System.Windows.Forms.Button btnFinalizar;
        private System.Windows.Forms.Button BtnSemIdent;
        private System.Windows.Forms.Panel pnlAguarde;
        private System.Windows.Forms.Label lblStatus;
        private System.Windows.Forms.Label LblOperac;
        private System.Windows.Forms.Label lblNrNota;
        private System.Windows.Forms.Label label2;
        private System.Windows.Forms.Label lblNrMapa;
        private System.Windows.Forms.Label lblMapa;
        private System.Windows.Forms.Label lblUsuario;
        private System.Windows.Forms.Label label3;
        private System.Windows.Forms.TextBox edtLote;
        private System.Windows.Forms.Label label4;
        private System.Windows.Forms.TextBox edtValidade;
        private System.Windows.Forms.Label label5;
        private System.Windows.Forms.TextBox edtProducao;
    }
}