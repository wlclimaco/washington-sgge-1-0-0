using System;
using System.Collections.Generic;
using System.Text;
using System.Data.SqlServerCe;

namespace LogosMobile
{
    public class AcessoBD
    {
        public void AbreConexao(ref SqlCeConnection conexao, string nomeBD)
        {
            try
            {
                if (conexao.State == System.Data.ConnectionState.Closed) 
                {
                    string strConexao = "data source=" + nomeBD + ".sdf";

                    conexao = new SqlCeConnection(strConexao);

                    conexao.Open();                    
                }
            }
            catch(Exception e)
            {
                Controller.ShowMessage("Erro na abertura do banco de dados: " + e.Message);
            }         
        }

        public void FechaConexao(ref SqlCeConnection conexao)
        {
            try
            {
                if (conexao.State == System.Data.ConnectionState.Open)
                {
                    conexao.Close();
                }
            }
            catch (Exception e)
            {
                Controller.ShowMessage("Erro no fechamento do banco de dados: " + e.Message);
            }
        }

        public void CriaBD(ref SqlCeConnection conexao, string nomeBD)
        {
            if (!System.IO.File.Exists(nomeBD + ".sdf"))
            {
                SqlCeEngine sqlengine = new SqlCeEngine("data source=" + nomeBD + ".sdf");
                sqlengine.CreateDatabase();
                
                AbreConexao(ref conexao, nomeBD);

                System.Data.SqlServerCe.SqlCeCommand sqlcommand = conexao.CreateCommand();
                sqlcommand.CommandText = "CREATE TABLE ENTRADA (NRGTIN    NVARCHAR(18), " +
                                         "                      NRLOTE    NVARCHAR(10), " +
                                         "                      DTVALID   NVARCHAR(06), " +
                                         "                      NRSSCC    NVARCHAR(50), " +
                                         "                      DSPRODUT  NVARCHAR(50), " +
                                         "                      QTPRODUT  NUMERIC(10,3)," +
                                         "                      DTPRODUC  NVARCHAR(06), " +
                                         "                      DTCOLETA  NVARCHAR(20), " +
                                         "                      NRCONTAG  NUMERIC(5,0), " +
                                         "                      NRCONATU  NUMERIC(5,0), " +
                                         "                      STITEM    NVARCHAR(01), " +
                                         "                      STCONSOL  NVARCHAR(01), " +
                                         "CONSTRAINT PK_ENTRADA PRIMARY KEY (NRGTIN, NRLOTE, DTVALID, NRSSCC, NRCONTAG))";
                sqlcommand.ExecuteNonQuery();
                                
                sqlcommand.CommandText = "CREATE TABLE SAIDA (NRGTIN    NVARCHAR(18), " +
                                         "                    NRLOTE    NVARCHAR(10), " +
                                         "                    DTVALID   NVARCHAR(06), " +
                                         "                    NRSSCC    NVARCHAR(50), " +
                                         "                    DSPRODUT  NVARCHAR(50), " +
                                         "                    QTPRODUT  NUMERIC(10,3)," +
                                         "                    DTPRODUC  NVARCHAR(06), " +
                                         "                    DTCOLETA  NVARCHAR(20), " +
                                         "                    NRCONTAG  NUMERIC(5,0), " +
                                         "                    NRCONATU  NUMERIC(5,0), " +
                                         "                    STITEM    NVARCHAR(01), " +
                                         "                    STCONSOL  NVARCHAR(01), " +
                                         "CONSTRAINT PK_SAIDA PRIMARY KEY (NRGTIN, NRLOTE, DTVALID, NRSSCC, NRCONTAG))";
                sqlcommand.ExecuteNonQuery();

                FechaConexao(ref conexao);
            }
        }
    }
}
