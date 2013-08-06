namespace LogosMobile
{
    partial class SIS009
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
            System.ComponentModel.ComponentResourceManager resources = new System.ComponentModel.ComponentResourceManager(typeof(SIS009));
            this.panel2 = new System.Windows.Forms.Panel();
            this.ImgTeclado = new System.Windows.Forms.PictureBox();
            this.btnSair = new System.Windows.Forms.Button();
            this.btnTestar = new System.Windows.Forms.Button();
            this.edtMsg = new System.Windows.Forms.TextBox();
            this.edtID = new System.Windows.Forms.TextBox();
            this.label1 = new System.Windows.Forms.Label();
            this.IptPnlSenha = new Microsoft.WindowsCE.Forms.InputPanel();
            this.panel2.SuspendLayout();
            this.SuspendLayout();
            // 
            // panel2
            // 
            this.panel2.BackColor = System.Drawing.Color.Navy;
            this.panel2.Controls.Add(this.btnTestar);
            this.panel2.Controls.Add(this.ImgTeclado);
            this.panel2.Controls.Add(this.btnSair);
            this.panel2.Location = new System.Drawing.Point(0, 0);
            this.panel2.Name = "panel2";
            this.panel2.Size = new System.Drawing.Size(240, 27);
            // 
            // ImgTeclado
            // 
            this.ImgTeclado.Image = ((System.Drawing.Image)(resources.GetObject("ImgTeclado.Image")));
            this.ImgTeclado.Location = new System.Drawing.Point(160, 4);
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
            // btnTestar
            // 
            this.btnTestar.BackColor = System.Drawing.Color.White;
            this.btnTestar.Location = new System.Drawing.Point(3, 3);
            this.btnTestar.Name = "btnTestar";
            this.btnTestar.Size = new System.Drawing.Size(53, 20);
            this.btnTestar.TabIndex = 12;
            this.btnTestar.Text = "Testar";
            this.btnTestar.Click += new System.EventHandler(this.btnTestar_Click);
            // 
            // edtMsg
            // 
            this.edtMsg.Font = new System.Drawing.Font("Courier New", 9F, System.Drawing.FontStyle.Regular);
            this.edtMsg.Location = new System.Drawing.Point(0, 57);
            this.edtMsg.Multiline = true;
            this.edtMsg.Name = "edtMsg";
            this.edtMsg.Size = new System.Drawing.Size(240, 258);
            this.edtMsg.TabIndex = 2;
            // 
            // edtID
            // 
            this.edtID.Location = new System.Drawing.Point(86, 30);
            this.edtID.Name = "edtID";
            this.edtID.Size = new System.Drawing.Size(151, 21);
            this.edtID.TabIndex = 3;
            // 
            // label1
            // 
            this.label1.Location = new System.Drawing.Point(3, 30);
            this.label1.Name = "label1";
            this.label1.Size = new System.Drawing.Size(77, 20);
            this.label1.Text = "Identificador";
            // 
            // SIS009
            // 
            this.AutoScaleDimensions = new System.Drawing.SizeF(96F, 96F);
            this.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Dpi;
            this.AutoScroll = true;
            this.ClientSize = new System.Drawing.Size(240, 320);
            this.Controls.Add(this.label1);
            this.Controls.Add(this.edtID);
            this.Controls.Add(this.edtMsg);
            this.Controls.Add(this.panel2);
            this.Location = new System.Drawing.Point(0, 0);
            this.Name = "SIS009";
            this.Text = "SIS009";
            this.WindowState = System.Windows.Forms.FormWindowState.Maximized;
            this.panel2.ResumeLayout(false);
            this.ResumeLayout(false);

        }

        #endregion

        private System.Windows.Forms.Panel panel2;
        private System.Windows.Forms.Button btnTestar;
        private System.Windows.Forms.PictureBox ImgTeclado;
        private System.Windows.Forms.Button btnSair;
        private System.Windows.Forms.TextBox edtMsg;
        private System.Windows.Forms.TextBox edtID;
        private System.Windows.Forms.Label label1;
        private Microsoft.WindowsCE.Forms.InputPanel IptPnlSenha;
    }
}