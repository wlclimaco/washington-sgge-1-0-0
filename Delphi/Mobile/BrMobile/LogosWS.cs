using System;
using System.Windows.Forms;
using System.IO;
using LogosMobile;

public class LogosWS
{
    private LogosMobile.LogosWebService.ILogosWSservice LogosWebService;

    private int    cdusuari;
    private string dsLogin;
    private string dsSenha;
    private int    nrerroconexao = 0;

    public string DsLogin { get { return dsLogin; } set { dsLogin = value; } }
    public string DsSenha { get { return dsSenha; } set { dsSenha = value; } }

    private string dscreden;
    private string nrip;
    private string noport;
    private string nmserver;

    public int    CdUsuari { get { return cdusuari; } set { cdusuari = value; } }
    public string DsCreden { get { return dscreden; } set { dscreden = value; } }
    public string Nrip     { get { return nrip;     } set { nrip     = value; } }
    public string Noport   { get { return noport;   } set { noport   = value; } }
    public string Nmserver { get { return nmserver; } set { nmserver = value; } }

    public void SetURL(string NrIP)
    {
        LogosWebService.Url = "http://" + NrIP + "/logos/logosws.exe/soap/ILogosWS";
    }

    public LogosWS()
	{
        LogosWebService = new LogosMobile.LogosWebService.ILogosWSservice();

        string nrip      = string.Empty;
        string lastuser  = string.Empty;
        string snpreetiq = string.Empty;
        string nrtmpatv  = string.Empty;

        Controller.GetConfig(ref nrip, ref lastuser, ref snpreetiq, ref nrtmpatv);

        if (nrip.Trim() != string.Empty)
        {
            SetURL(nrip);
            Cursor.Current = Cursors.Default;
        }
        else
        {
            Cursor.Current = Cursors.Default;
            SIS004 FrmIP = new SIS004(nrip, lastuser, snpreetiq, true, true, true, nrtmpatv);
            FrmIP.ShowDialog();

            if (FrmIP.DialogResult == DialogResult.OK)
            {
                nrip      = FrmIP.Nrip;
                lastuser  = FrmIP.LastUser;
                snpreetiq = FrmIP.SnPreEtiq;
                nrtmpatv  = FrmIP.TmpAtv;

                Controller.SetConfig(nrip, lastuser, snpreetiq, nrtmpatv);

                LogosWebService.Url = "http://" + FrmIP.Nrip + "/logos/logosws.exe/soap/ILogosWS";
            }
            else
            {
                Controller.ShowMessage("Não foi possível completar a configuracao!!! Esta aplicação será encerrada.");
                Application.Exit();
            }

            FrmIP.Dispose();
        }
	}

    public bool WsPing()
    {
        bool dsreturn = false;
        try
        {
            if (nrerroconexao < 3)
            {
                Cursor.Current = Cursors.WaitCursor;
                string dsresult = string.Empty;
                dsresult = LogosWebService.Ping();
                dsreturn = dsresult == "OK";
                Cursor.Current = Cursors.Default;
                nrerroconexao = 0;
            }
            else
            {
                if (Controller.MessageDlg("Falha grave de comunicação!\nDeseja abandonar a aplicação?"))
                {
                    Application.Exit();
                }
                else
                {
                    nrerroconexao = 0;
                }
            }
        }
        catch (Exception e)
        {
            dsreturn = false;
            nrerroconexao++;
            Controller.ShowMessage(e.Message);
            Cursor.Current = Cursors.Default;            
        }
        return dsreturn;
    }

    public bool WsAutenticaUsuario(string  dslogin, string dssenha)
    {
        bool dsreturn = false;
        try
        {           
            Cursor.Current  = Cursors.WaitCursor;
            string dsresult = string.Empty;
            
            dsresult = LogosWebService.AutenticaUsuario(dslogin, dssenha, ref cdusuari);

            if (dsresult.Trim() != string.Empty)
            {
                Controller.ShowMessage(dsresult);
            }
            else
            {
                dsreturn = true;
                DsLogin = dslogin;
                DsSenha = dssenha;
            }            

            Cursor.Current = Cursors.Default;
        }
        catch(Exception e)
        {
            Cursor.Current = Cursors.Default;
            Controller.ShowMessage("Autentica Usuario - Erro " + e.Message);
        }
        return dsreturn;
    }

    public bool WsProximaAtividade(ref string nrmapa, ref string dsativid)
    {
        bool dsreturn = false;
        try
        {
            Cursor.Current = Cursors.WaitCursor;
            string dsresult = string.Empty;
            dsresult = LogosWebService.ProximaAtividade(CdUsuari, ref nrmapa, ref dsativid);

            if (dsresult.Trim() != string.Empty)
            {
                Controller.ShowMessage(dsresult);
            }
            else
            {
                dsreturn = true;
            }

            Cursor.Current = Cursors.Default;
        }
        catch (Exception e)
        {
            Cursor.Current = Cursors.Default;
            Controller.ShowMessage("Proxima Atividade - Erro " + e.Message);
        }
        return dsreturn;
    }

    public bool WsAtividadeNaoConvocada(ref string dslist)
    {
        bool dsreturn = false;
        try
        {            
            string dsresult = string.Empty;
            dsresult = LogosWebService.AtividadeNaoConvocada(CdUsuari, ref dslist);            

            if (dsresult.Trim() != string.Empty)
            {
                Controller.ShowMessage(dsresult);
            }
            else
            {
                dsreturn = true;
            }
            
        }
        catch (Exception e)
        {
            Controller.ShowMessage(e.Message);
            dsreturn = false;    
        }
        return dsreturn;
    }

    public bool WsTestaConexao(string idteste, string dsmensagem)
    {
        bool dsreturn = false;
        try
        {
            string dsresult = string.Empty;
            dsresult = LogosWebService.TestaConexao(idteste, dsmensagem);

            if (dsresult.Trim() != string.Empty)
            {
                Controller.ShowMessage(dsresult);
            }
            else
            {
                dsreturn = true;
            }

        }
        catch (Exception e)
        {
            Controller.ShowMessage(e.Message);
            dsreturn = false;
        }
        return dsreturn;
    }

    public bool ConsultaAtividade(string NrFornec, string SgTipAti, ref string NrOpeLog)
    {
        bool dsreturn = false;
        try
        {
            Cursor.Current = Cursors.WaitCursor;
            string dsresult = string.Empty;
            dsresult = LogosWebService.ConsultaAtividade(NrFornec, SgTipAti, ref NrOpeLog);

            if (dsresult.Trim() != string.Empty)
            {
                Controller.ShowMessage(dsresult);
            }
            else
            {
                dsreturn = true;
            }

            Cursor.Current = Cursors.Default;
        }
        catch (Exception e)
        {
            Cursor.Current = Cursors.Default;
            Controller.ShowMessage("Consulta Atividade - Erro " + e.Message);
        }
        return dsreturn;
    }

    public bool WsSaidaInciar(string nrmapa, ref string nrnota, ref string nrfornec, ref string dslist)
    {
        bool dsreturn = false;
        try
        {
            Cursor.Current = Cursors.WaitCursor;
            string dsresult = string.Empty;
            dsresult = LogosWebService.ConferenciaSaidaIniciar(CdUsuari, nrmapa, ref nrnota, ref nrfornec, ref dslist);

            if (dsresult.Trim() != string.Empty)
            {
                Controller.ShowMessage(dsresult);
            }
            else
            {
                dsreturn = true;
            }

            Cursor.Current = Cursors.Default;
        }
        catch (Exception e)
        {
            Cursor.Current = Cursors.Default;
            Controller.ShowMessage("Iniciar Saida - Erro " + e.Message);
        }
        return dsreturn;
    }

    public bool WsSaidaFinalizar(string nrmapa, string dslist, string nrclient, string dsoperac, int QtProSid)
    {
        bool dsreturn = false;
        try
        {
            Cursor.Current = Cursors.WaitCursor;
            string dsresult = string.Empty;
            dsresult = LogosWebService.ConferenciaSaidaFinalizar(CdUsuari, nrmapa, dslist, nrclient, dsoperac, QtProSid);

            if (dsresult.Trim() != string.Empty)
            {
                Controller.ShowMessage(dsresult);
            }
            else
            {
                dsreturn = true;
            }

            Cursor.Current = Cursors.Default;
        }
        catch (Exception e)
        {
            Cursor.Current = Cursors.Default;
            Controller.ShowMessage("Finalizacao de Saida - Erro " + e.Message);
        }
        return dsreturn;
    }

    public bool WsEntradaInciar(string nrmapa, ref string nrnota, ref string nrfornec, ref string dslist)
    {
        bool dsreturn = false;
        try
        {
            Cursor.Current = Cursors.WaitCursor;
            string dsresult = string.Empty;
            dsresult = LogosWebService.ConferenciaEntradaIniciar(CdUsuari, nrmapa, ref nrnota, ref nrfornec, ref dslist);
                
            if (dsresult.Trim() != string.Empty)
            {
                Controller.ShowMessage(dsresult);
            }
            else
            {
                dsreturn = true;
            }

            Cursor.Current = Cursors.Default;
        }
        catch (Exception e)
        {
            Cursor.Current = Cursors.Default;
            Controller.ShowMessage("Entrada Inciar - Erro " + e.Message);
        }
        return dsreturn;
    }

    public bool WsEntradaFinalizar(string nrmapa, string dslist, string dsoperac, int QtProSid)
    {
        bool dsreturn = false;
        try
        {
            Cursor.Current = Cursors.WaitCursor;
            string dsresult = string.Empty;
            dsresult = LogosWebService.ConferenciaEntradaFinalizar(CdUsuari, nrmapa, dslist, dsoperac, QtProSid);

            if (dsresult.Trim() != string.Empty)
            {
                Controller.ShowMessage(dsresult);
            }
            else
            {
                dsreturn = true;
            }

            Cursor.Current = Cursors.Default;
        }
        catch (Exception e)
        {
            Cursor.Current = Cursors.Default;
            Controller.ShowMessage("Finalização de Entrada - Erro " + e.Message);
        }
        return dsreturn;
    }    
}
