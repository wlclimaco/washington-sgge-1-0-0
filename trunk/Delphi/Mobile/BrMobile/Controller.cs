using System;
using System.Windows.Forms;
using System.IO;
using System.Net;
using LogosMobile;

public static class Controller
{
    public static LogosWS WsLogos = new LogosWS();
    
    public static void ShowMessage(string pDsTexto)
    {        
        MessageBox.Show(pDsTexto, "Bravo Mobile", MessageBoxButtons.OK, MessageBoxIcon.Exclamation, MessageBoxDefaultButton.Button1);
    }

    public static string GetMacAddress()
    {
        string strHostName = Dns.GetHostName();
        IPHostEntry ipEntry = Dns.GetHostByName(strHostName);
        IPAddress[] addr = ipEntry.AddressList;
        return addr[0].ToString();
    }         

    public static void SetFormaLeitura(string SnPreEtiq)
    {
        string nrip = string.Empty;
        string lastuser = string.Empty;
        string snpreetiq = string.Empty;
        string nrtmpatv = string.Empty;

        GetConfig(ref nrip, ref lastuser, ref snpreetiq, ref nrtmpatv);
        SetConfig(nrip, lastuser, SnPreEtiq, nrtmpatv);
    }

    public static void SetLastUser(string NmLastUser)
    {
        string nrip = string.Empty;
        string lastuser = string.Empty;
        string snpreetiq = string.Empty;
        string nrtmpatv = string.Empty;

        GetConfig(ref nrip, ref lastuser, ref snpreetiq, ref nrtmpatv);
        SetConfig(    nrip,   NmLastUser, snpreetiq, nrtmpatv);
    }

    public static void SetNrIP(string NrIp)
    {
        string nrip = string.Empty;
        string lastuser = string.Empty;
        string snpreetiq = string.Empty;
        string nrtmpatv = string.Empty;

        GetConfig(ref nrip, ref lastuser, ref snpreetiq, ref nrtmpatv);
        SetConfig(NrIp, lastuser, snpreetiq, nrtmpatv);
    }

    public static void SetNrTMPAtv(string NrTMPAtv)
    {
        string nrip = string.Empty;
        string lastuser = string.Empty;
        string snpreetiq = string.Empty;
        string nrtmpatv = string.Empty;

        GetConfig(ref nrip, ref lastuser, ref snpreetiq, ref nrtmpatv);
        SetConfig(nrip, lastuser, snpreetiq, NrTMPAtv);
    }

    public static void SetConfig(string NrIP, string NmLastUser, string SnPreEtiq, string NrTmpAtv)
    {
        File.Delete("LogosConfig.txt");
        System.IO.StreamWriter config = new System.IO.StreamWriter(@"LogosConfig.txt", true);
        config.WriteLine(NrIP);
        config.WriteLine(NmLastUser);
        config.WriteLine(SnPreEtiq);
        config.WriteLine(NrTmpAtv);
        config.Close();
    }

    public static void GetConfig(ref string NrIP, ref string NmLastUser, ref string SnPreEtiq, ref string NrTmpAtv)
    {
        if (File.Exists("LogosConfig.txt"))
        {
            StreamReader objReader = new StreamReader("LogosConfig.txt");
            NrIP = objReader.ReadLine();

            if (objReader.EndOfStream == false)
            {
                NmLastUser = objReader.ReadLine();
            }

            if (objReader.EndOfStream == false)
            {
                SnPreEtiq = objReader.ReadLine();
            }

            if (objReader.EndOfStream == false)
            {
                NrTmpAtv = objReader.ReadLine();
            }            

            objReader.Close();
        }    
    }

    public static bool MessageDlg(string pDsTexto)
    {
        DialogResult result = MessageBox.Show(pDsTexto, "Bravo Mobile", MessageBoxButtons.YesNo, MessageBoxIcon.Question, MessageBoxDefaultButton.Button1);
        bool dsresult;

        if (result == DialogResult.Yes)
        {
            dsresult = true;
        }
        else
        {
            dsresult = false;
        }

        return dsresult;
    }

    public static void ProcessaEntrada(string nrmapa, string nmusuario, bool sntransf, string dstitoper, bool snleprieti)
    {
        string dslista = "";
        string nrnota = "";
        string nrfornec = "";

        if (WsLogos.WsPing())
        {
            if (WsLogos.WsEntradaInciar(nrmapa, ref nrnota, ref nrfornec, ref dslista))
            {
                if (snleprieti)
                {
                    SIS006 FrmValidaEtiqueta = new SIS006(nrmapa, nrfornec, false, nmusuario, dstitoper, "E");
                    FrmValidaEtiqueta.ShowDialog();

                    if (FrmValidaEtiqueta.DialogResult == DialogResult.OK)
                    {
                        FrmValidaEtiqueta.Dispose();

                        SIS002 FrmEntrada = new SIS002(nrmapa, nrnota, dslista, nmusuario, sntransf, dstitoper);
                        FrmEntrada.ShowDialog();
                    }
                    else
                    {
                        FrmValidaEtiqueta.Dispose();
                    }
                }
                else
                {
                    SIS002 FrmEntrada = new SIS002(nrmapa, nrnota, dslista, nmusuario, sntransf, dstitoper);
                    FrmEntrada.ShowDialog();
                }
            }
        }
    }

    public static void ProcessaSaida(string nrmapa, bool sntransf, string nmusuario, string dstitoper, bool snleprieti, string nrcliAux)
    {        
        string dslista  = "";
        string nrnota   = "";
        string nrfornec = "";

        if (WsLogos.WsPing())
        {
            if (WsLogos.WsSaidaInciar(nrmapa, ref nrnota, ref nrfornec, ref dslista))
            {
                if (snleprieti)
                {
                    SIS006 FrmValidaEtiqueta = new SIS006(nrmapa, nrfornec, false, nmusuario, dstitoper, "S");
                    FrmValidaEtiqueta.ShowDialog();

                    if (FrmValidaEtiqueta.DialogResult == DialogResult.OK)
                    {
                        FrmValidaEtiqueta.Dispose();
                        SIS005 FrmSaida = new SIS005(nrmapa, nrnota, dslista, FrmValidaEtiqueta.NrClient, nrfornec, sntransf, nmusuario, dstitoper);
                        FrmSaida.ShowDialog();
                    }
                    else
                    {
                        FrmValidaEtiqueta.Dispose();
                    }
                }
                else
                {
                    SIS005 FrmSaida = new SIS005(nrmapa, nrnota, dslista, nrcliAux, nrfornec, sntransf, nmusuario, dstitoper);
                    FrmSaida.ShowDialog();                
                }
            }
        }
    }
}
