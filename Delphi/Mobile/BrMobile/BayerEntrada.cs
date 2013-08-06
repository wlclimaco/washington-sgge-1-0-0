using System;
using System.Collections.Generic;
using System.Text;
using System.Data.SqlServerCe;

namespace LogosMobileEntradaBayer
{
    public class BayerEntrada    
    {
        public bool Clear(ref SqlCeConnection conexao)
        {
            bool vlresult = false;
            SqlCeCommand cmd;

            string sql = "Delete From Entrada";

            try
            {
                cmd = new SqlCeCommand(sql, conexao);
                cmd.ExecuteNonQuery();
                vlresult = true;
            }
            catch (SqlCeException sqlexception)
            {
                Controller.ShowMessage("Erro durante comando CLEAR: " + sqlexception.Message);
            }
            return vlresult;
        }

        public bool Insert(ref SqlCeConnection conexao, string[] Registro, string nrmapa, int idx)
        {
            bool vlresult = false;
            SqlCeCommand cmd;            

            string sql = "Insert Into Entrada " +
                         "( NRGTIN,  NRLOTE,  DTVALID,  DSPRODUT,  QTPRODUT,  NRCONTAG,  NRCONATU,  STITEM, DTPRODUC,  NRSSCC, DTCOLETA) " +
                         "Values " +
                         "(@NRGTIN, @NRLOTE, @DTVALID, @DSPRODUT, @QTPRODUT, @NRCONTAG, @NRCONATU, @STITEM,     NULL, @NRSSCC,     NULL) ";
            try
            {
                string nrsscc = Registro[0] + Registro[3] + idx.ToString();

                cmd = new SqlCeCommand(sql, conexao);
                cmd.Parameters.AddWithValue("@NRGTIN"  , Registro[0]);
                cmd.Parameters.AddWithValue("@NRLOTE"  , Registro[3]);
                cmd.Parameters.AddWithValue("@DTVALID" , Registro[4]);                
                cmd.Parameters.AddWithValue("@DSPRODUT", Registro[1]);
                cmd.Parameters.AddWithValue("@QTPRODUT", Registro[2]);
                cmd.Parameters.AddWithValue("@NRSSCC",   nrsscc);
                cmd.Parameters.AddWithValue("@NRCONTAG", "0");
                cmd.Parameters.AddWithValue("@NRCONATU", "1");
                cmd.Parameters.AddWithValue("@STITEM"  , "R");
                cmd.ExecuteNonQuery();
                vlresult = true;
            }
            catch (SqlCeException sqlexception)
            {
                Controller.ShowMessage("Erro durante comando Insert: " + sqlexception.Message);
            }
            return vlresult;        
        }

        public int TotalContado(ref SqlCeConnection conexao)
        {
            string qtsumaux = "0";

            if (conexao.State == System.Data.ConnectionState.Open)
            {
                SqlCeCommand cmd;

                string sql = "Select Sum(QTPRODUT) " +
                             "From entrada " +
                             "Where STITEM  in ('X', 'C', 'D', 'E')";


                cmd = new SqlCeCommand(sql, conexao);
                cmd.CommandType = System.Data.CommandType.Text;
                cmd.Parameters.Clear();



                if (cmd.ExecuteScalar() != null)
                {
                    qtsumaux = cmd.ExecuteScalar().ToString();
                    if (qtsumaux.Trim() != string.Empty)
                    {
                        qtsumaux = qtsumaux.Substring(0, qtsumaux.Length - 4);
                    }
                }
            }
            if (qtsumaux.Trim() == string.Empty)
            {
                qtsumaux = "0";
            }

            return Convert.ToInt32(qtsumaux);
        }

        public string GeraListagem(ref SqlCeConnection conexao, string nrmapa)
        {
            string vlresult = string.Empty;
            SqlCeCommand cmd;

            string  nrgtin   = string.Empty;
            string  nrlote   = string.Empty;
            string  dtvalid  = string.Empty;
            string  nrsscc   = string.Empty;
            string  dtproduc = string.Empty;
            string  dtcoleta = string.Empty;
            string  stitem   = string.Empty;
            decimal qtprodut = 0;

            string sql = "Select NRGTIN,NRLOTE,DTVALID,NRSSCC,DTPRODUC,DTCOLETA,QTPRODUT,STITEM,STCONSOL " + 
                         "From   Entrada " +
                         "Where  STITEM in ('C', 'D') and " + 
                         "       QTPRODUT  > 0 ";
            try
            {
                cmd = new SqlCeCommand(sql, conexao);                
                cmd.CommandType = System.Data.CommandType.Text;
                SqlCeResultSet rs = cmd.ExecuteResultSet(ResultSetOptions.Scrollable);

                if (rs.HasRows)
                {
                    vlresult = "NRMAPA;NRGTIN;NRLOTE;DTVALID;NRSSCC;DTPRODUC;DTCOLETA;QTPRODUT;STITEM\r\n";

                    rs.ReadFirst();
                    nrgtin   = rs.GetString(0);
                    nrlote   = rs.GetString(1).Replace(";", "/");;
                    dtvalid  = rs.GetString(2);
                    nrsscc   = rs.GetString(3);
                    dtproduc = rs.GetString(4);
                    dtcoleta = rs.GetString(5);
                    qtprodut = rs.GetDecimal(6);
                    stitem   = rs.GetString(7);

                    if (! rs.IsDBNull(8))
                    {
                        stitem = rs.GetString(8);
                    }                    

                    vlresult += nrmapa   + ";" + nrgtin   + ";" + nrlote   + ";" + dtvalid + ";" + nrsscc + ";" +
                                dtproduc + ";" + dtcoleta + ";" + qtprodut.ToString() + ";" + stitem  + "\r\n";

                    while (rs.Read())
                    {
                        nrgtin   = rs.GetString(0);
                        nrlote   = rs.GetString(1).Replace(";", "/"); ;
                        dtvalid  = rs.GetString(2);
                        nrsscc   = rs.GetString(3);
                        dtproduc = rs.GetString(4);
                        dtcoleta = rs.GetString(5);
                        qtprodut = rs.GetDecimal(6);
                        stitem   = rs.GetString(7);

                        if (!rs.IsDBNull(8))
                        {
                            stitem = rs.GetString(8);
                        }   

                        vlresult += nrmapa   + ";" + nrgtin   + ";" + nrlote   + ";" + dtvalid + ";" + nrsscc + ";" +
                                    dtproduc + ";" + dtcoleta + ";" + qtprodut.ToString() + ";" + stitem + "\r\n";
                    }
                    return vlresult;
                }
            }
            catch (SqlCeException sqlexception)
            {
                Controller.ShowMessage("Erro durante geração da lista: " + sqlexception.Message);
            }
            return vlresult;
        }

        public bool FindItemExt(ref SqlCeConnection conexao, string cdgtin, string nrlote, string dtproduc,
                                     string dtvalid, string nrsscc)
        {
            bool vlresult = false;
            SqlCeCommand cmd;

            string sql = "Select * From Entrada " + 
                         "Where NRGTIN   = @NRGTIN   and " +
                         "      NRLOTE   = @NRLOTE   and " +
                         "      DTPRODUC = @DTPRODUC and " +
                         "      DTVALID  = @DTVALID  and " +
                         "      NRSSCC   = @NRSSCC   and " +
                         "      STITEM  in ('C', 'E') ";
            try
            {
                cmd = new SqlCeCommand(sql, conexao);
                cmd.Parameters.AddWithValue("@NRGTIN"  , cdgtin);
                cmd.Parameters.AddWithValue("@NRLOTE"  , nrlote);
                cmd.Parameters.AddWithValue("@DTPRODUC", dtproduc);
                cmd.Parameters.AddWithValue("@DTVALID" , dtvalid);
                cmd.Parameters.AddWithValue("@NRSSCC"  , nrsscc);                
                cmd.CommandType = System.Data.CommandType.Text;
                SqlCeResultSet rs = cmd.ExecuteResultSet(ResultSetOptions.Scrollable);

                if (rs.HasRows)
                {
                    vlresult = true;
                }
                else
                {
                    vlresult = false;
                }
            }
            catch (SqlCeException sqlexception)
            {
                Controller.ShowMessage("Erro durante comando busca extendida: " + sqlexception.Message);
            }
            return vlresult;
        }

        public int FindItemContag(ref SqlCeConnection conexao, string cdgtin, string nrlote, string dtvalid)
        {
            int vlresult = 0;
            SqlCeCommand cmd;

            string sql = string.Empty;

            if ((nrlote.Trim() == string.Empty) && (dtvalid.Trim() == string.Empty))
            {
                sql = "Select NRCONATU From Entrada " +
                      "Where NRGTIN  = @NRGTIN  and " +
                      "      STITEM  in ('R', 'X') ";
            }
            else
            {
                sql = "Select NRCONATU From Entrada " +
                      "Where NRGTIN  = @NRGTIN  and " +
                      "      NRLOTE  = @NRLOTE  and " +
                      "      DTVALID = @DTVALID and " +
                      "      STITEM  in ('R', 'X') ";
            }            

            try
            {
                cmd = new SqlCeCommand(sql, conexao);

                if ((nrlote.Trim() == string.Empty) && (dtvalid.Trim() == string.Empty))
                {
                    cmd.Parameters.AddWithValue("@NRGTIN", cdgtin);
                }
                else
                {
                    cmd.Parameters.AddWithValue("@NRGTIN", cdgtin);
                    cmd.Parameters.AddWithValue("@NRLOTE", nrlote);
                    cmd.Parameters.AddWithValue("@DTVALID", dtvalid);
                }                

                string vlCount = "";
                try
                {
                    if (cmd.ExecuteScalar() != null)
                    {
                        vlCount = cmd.ExecuteScalar().ToString();
                    }

                }
                catch (SqlCeException sqlexception)
                {
                    vlCount = string.Empty;                    
                }

                if (vlCount.Trim() == string.Empty)
                {
                    vlresult = 1;
                }
                else
                {
                    vlresult = int.Parse(vlCount);
                }
                
            }
            catch (SqlCeException sqlexception)
            {
                Controller.ShowMessage("Erro durante busca de contagem: " + sqlexception.Message);
            }
            return vlresult;
        }        

        public bool FindItem(ref SqlCeConnection conexao, string cdgtin, string nrlote, string dtvalid, string sqlcompl)
        {
            bool vlresult = false;
            SqlCeCommand cmd;
            string sql = string.Empty;

            try
            {
                if ((nrlote.Trim() == string.Empty) && (dtvalid.Trim() == string.Empty))
                {
                    sql = "Select * From Entrada " +
                          "Where NRGTIN  = @NRGTIN " +
                          sqlcompl;
                }
                else
                {
                    sql = "Select * From Entrada " +
                          "Where NRGTIN  = @NRGTIN and " +
                          "      NRLOTE  = @NRLOTE and " +
                          "      DTVALID = @DTVALID " +
                          sqlcompl;                
                }

                cmd = new SqlCeCommand(sql, conexao);

                if ((nrlote.Trim() == string.Empty) && (dtvalid.Trim() == string.Empty))
                {
                    cmd.Parameters.AddWithValue("@NRGTIN", cdgtin);
                }
                else
                {
                    cmd.Parameters.AddWithValue("@NRGTIN", cdgtin);
                    cmd.Parameters.AddWithValue("@NRLOTE", nrlote);
                    cmd.Parameters.AddWithValue("@DTVALID", dtvalid);
                }                
                
                cmd.CommandType = System.Data.CommandType.Text;
                SqlCeResultSet rs = cmd.ExecuteResultSet(ResultSetOptions.Scrollable);

                if (rs.HasRows)
                {
                    vlresult = true;
                }
                else
                {
                    vlresult = false;                
                }
            }
            catch (SqlCeException sqlexception)
            {
                Controller.ShowMessage("Erro durante busca de Item: " + sqlexception.Message);
            }
            return vlresult;               
        }

        public bool FinalizarContagem(ref SqlCeConnection conexao)
        {
            bool dsresult = true;
            try
            {
                SqlCeCommand cmd;                             

                string sql = "Select nrgtin, nrlote, dsprodut, dtvalid, qtprodut from entrada where stitem = 'R'";

                cmd = new SqlCeCommand(sql, conexao);
                cmd.CommandType = System.Data.CommandType.Text;
                SqlCeResultSet RsReferencia = cmd.ExecuteResultSet(ResultSetOptions.Scrollable);

                if (RsReferencia.HasRows)
                {
                    string  nrgtin   = string.Empty; 
                    string  nrlote   = string.Empty;
                    string  dsprodut = string.Empty;
                    string  dtvalid  = string.Empty;
                    decimal qtprodut = 0;

                    //Consolidando todos os itens não esperados
                    cmd.CommandText = "Update entrada set STITEM = 'C', STCONSOL = 'N' Where STITEM  = 'X' ";
                    cmd.ExecuteNonQuery();

                    while (RsReferencia.Read())
                    {                    
                        nrgtin   = RsReferencia.GetString(0);
                        nrlote   = RsReferencia.GetString(1);
                        dsprodut = RsReferencia.GetString(2);
                        dtvalid  = RsReferencia.GetString(3);
                        qtprodut = RsReferencia.GetDecimal(4);

                        cmd.CommandText = "Select Sum(QTPRODUT) " +
                                          "from entrada " +
                                          "where STITEM  = @STITEM and " +
                                          "      NRGTIN  = @NRGTIN  and " +
                                          "      NRLOTE  = @NRLOTE  and " +
                                          "      DTVALID = @DTVALID ";
                        cmd.Parameters.Clear();
                        cmd.Parameters.AddWithValue("@NRGTIN", nrgtin);
                        cmd.Parameters.AddWithValue("@NRLOTE", nrlote);
                        cmd.Parameters.AddWithValue("@DTVALID", dtvalid);
                        cmd.Parameters.AddWithValue("@STITEM", "E");
                        
                        string qtcountaux = cmd.ExecuteScalar().ToString();
                        decimal qtcount = 0;

                        if (qtcountaux.Trim() != string.Empty)
                        {
                            qtcount = Decimal.Parse(qtcountaux);
                        }

                        if (qtprodut != qtcount)
                        {
                            if (!Controller.MessageDlg("Produto " + nrgtin + " : " + dsprodut + "\n" +
                                                       "Lote [ " + nrlote + " ] \n DIVERGENTE!!! \n" +
                                "Deseja continuar?"))                            
                            {
                                //Atualizando itens contados - Histórico
                                cmd.CommandText = "Update entrada set STITEM = @STITEMNEW" +
                                                  " Where STITEM  = @STITEM and " +
                                                  "       NRGTIN  = @NRGTIN  and " +
                                                  "       NRLOTE  = @NRLOTE  and " +
                                                  "       DTVALID = @DTVALID ";
                                cmd.Parameters.Clear();
                                cmd.Parameters.AddWithValue("@NRGTIN", nrgtin);
                                cmd.Parameters.AddWithValue("@NRLOTE", nrlote);
                                cmd.Parameters.AddWithValue("@DTVALID", dtvalid);
                                cmd.Parameters.AddWithValue("@STITEM", "E");
                                cmd.Parameters.AddWithValue("@STITEMNEW", "H");
                                cmd.ExecuteNonQuery();

                                //Atualizando itens contados - Histórico (nao validados)
                                cmd.CommandText = "Update entrada set STITEM = @STITEMNEW" +
                                                  " Where STITEM  = @STITEM and " +
                                                  "       NRGTIN  = @NRGTIN ";
                                cmd.Parameters.Clear();
                                cmd.Parameters.AddWithValue("@NRGTIN", nrgtin);                                
                                cmd.Parameters.AddWithValue("@STITEM", "E");
                                cmd.Parameters.AddWithValue("@STITEMNEW", "H");
                                cmd.ExecuteNonQuery();

                                //Atualizando referencia
                                cmd.CommandText = "Update entrada set NRCONATU = NRCONATU + 1 " +
                                                  " Where STITEM  = @STITEM and " +
                                                  "       NRGTIN  = @NRGTIN  and " +
                                                  "       NRLOTE  = @NRLOTE  and " +
                                                  "       DTVALID = @DTVALID ";
                                cmd.Parameters.Clear();
                                cmd.Parameters.AddWithValue("@NRGTIN", nrgtin);
                                cmd.Parameters.AddWithValue("@NRLOTE", nrlote);
                                cmd.Parameters.AddWithValue("@DTVALID", dtvalid);
                                cmd.Parameters.AddWithValue("@STITEM", "R");                            
                                cmd.ExecuteNonQuery();

                                dsresult = false;
                                RsReferencia.ReadLast();
                            }
                            else
                            {
                                if (qtcount == 0)
                                {
                                    InsertContagem(ref conexao, nrgtin, "", "", "", "", false, 0, false);
                                }

                                //Atualizando itens contados e validados
                                cmd.CommandText = " Update entrada set STITEM = @STITEMNEW" +
                                                  " Where STITEM  = @STITEM and " +
                                                  "       NRGTIN  = @NRGTIN  and " +
                                                  "       NRLOTE  = @NRLOTE  and " +
                                                  "       DTVALID = @DTVALID ";
                                cmd.Parameters.Clear();
                                cmd.Parameters.AddWithValue("@NRGTIN", nrgtin);
                                cmd.Parameters.AddWithValue("@NRLOTE", nrlote);
                                cmd.Parameters.AddWithValue("@DTVALID", dtvalid);
                                cmd.Parameters.AddWithValue("@STITEM", "E");
                                cmd.Parameters.AddWithValue("@STITEMNEW", "D");
                                cmd.ExecuteNonQuery();

                                //Atualizando itens contados e nao validados
                                cmd.CommandText = " Update entrada set STITEM = @STITEMNEW" +
                                                  " Where STITEM  = @STITEM and " +
                                                  "       NRGTIN  = @NRGTIN ";
                                cmd.Parameters.Clear();
                                cmd.Parameters.AddWithValue("@NRGTIN", nrgtin);                                
                                cmd.Parameters.AddWithValue("@STITEM", "E");
                                cmd.Parameters.AddWithValue("@STITEMNEW", "D");
                                cmd.ExecuteNonQuery();

                                //Atualizando referencia
                                cmd.CommandText = "Update entrada set STITEM = @STITEMNEW" +
                                                  " Where STITEM  = @STITEM and " +
                                                  "       NRGTIN  = @NRGTIN  and " +
                                                  "       NRLOTE  = @NRLOTE  and " +
                                                  "       DTVALID = @DTVALID ";
                                cmd.Parameters.Clear();
                                cmd.Parameters.AddWithValue("@NRGTIN", nrgtin);
                                cmd.Parameters.AddWithValue("@NRLOTE", nrlote);
                                cmd.Parameters.AddWithValue("@DTVALID", dtvalid);
                                cmd.Parameters.AddWithValue("@STITEM", "R");
                                cmd.Parameters.AddWithValue("@STITEMNEW", "V");
                                cmd.ExecuteNonQuery();
                            }
                        }
                        else
                        {
                            //Atualizando itens contados e Validados
                            cmd.CommandText = " Update entrada set STITEM = @STITEMNEW" +
                                              " Where STITEM  = @STITEM and " +
                                              "       NRGTIN  = @NRGTIN  and " +
                                              "       NRLOTE  = @NRLOTE  and " +
                                              "       DTVALID = @DTVALID ";
                            cmd.Parameters.Clear();
                            cmd.Parameters.AddWithValue("@NRGTIN", nrgtin);
                            cmd.Parameters.AddWithValue("@NRLOTE", nrlote);
                            cmd.Parameters.AddWithValue("@DTVALID", dtvalid);
                            cmd.Parameters.AddWithValue("@STITEM", "E");
                            cmd.Parameters.AddWithValue("@STITEMNEW", "C");
                            cmd.ExecuteNonQuery();

                            //Atualizando itens contados e não Validados
                            //cmd.CommandText = " Update entrada set STITEM = @STITEMNEW" +
                            //                  " Where STITEM  = @STITEM  and " +
                            //                  "       NRGTIN  = @NRGTIN  ";
                            //cmd.Parameters.Clear();
                            //cmd.Parameters.AddWithValue("@NRGTIN", nrgtin);                            
                            //cmd.Parameters.AddWithValue("@STITEM", "E");
                            //cmd.Parameters.AddWithValue("@STITEMNEW", "C");
                            //cmd.ExecuteNonQuery();

                            //Atualizando referencia
                            cmd.CommandText = "Update Entrada set STITEM = @STITEMNEW" +
                                              " Where STITEM  = @STITEM and " +
                                              "       NRGTIN  = @NRGTIN  and " +
                                              "       NRLOTE  = @NRLOTE  and " +
                                              "       DTVALID = @DTVALID ";
                            cmd.Parameters.Clear();
                            cmd.Parameters.AddWithValue("@NRGTIN", nrgtin);
                            cmd.Parameters.AddWithValue("@NRLOTE", nrlote);
                            cmd.Parameters.AddWithValue("@DTVALID", dtvalid);
                            cmd.Parameters.AddWithValue("@STITEM", "R");
                            cmd.Parameters.AddWithValue("@STITEMNEW", "F");
                            cmd.ExecuteNonQuery();
                        }
                    }
                }                
            }
            catch (SqlCeException sqlexception)
            {
                Controller.ShowMessage("Erro durante finalizacao: " + sqlexception.Message);
                dsresult = false;
            }
            return dsresult;
        }

        public void AlimentaEntrada(ref SqlCeConnection conexao, string lista, string nrmapa)
        {
            string DsList = lista;
            const string dscabec = "NRGTIN;DSPRODUT;QTPRODUT;NRLOTE;DTVALID";
            char[] DsSepLin = { '#' };
            char[] DsSepVal = { ';' };
            string[] lines;
            string[] values;

            DsList = DsList.Replace("\r\n", "#");
            DsList = DsList.Replace("##", "#");
            
            lines = DsList.Split(DsSepLin);

            if (dscabec.ToUpper() != lines[0].ToUpper())
            {
                throw new Exception(String.Format("Lista de Entrada não conforme: {0}", lines[0]));
            }

            Clear(ref conexao);

            for (int idx = 1; idx < lines.Length; idx++)
            {
                if (lines[idx].Trim() != string.Empty)
                {
                    values = lines[idx].Split(DsSepVal);
                    Insert(ref conexao, values, nrmapa, idx);
                }
            }
        }

        public bool InsertContagem(ref SqlCeConnection conexao, string cdgtin, string nrlote, string dtproduc,
                                   string dtvalid, string nrsscc, bool snconsol, float qtprodut, bool SNAvisaPK)
        {
            bool vlresult = false;
            SqlCeCommand cmd;            

            string sql = "Insert Into Entrada " +
                         "( NRGTIN,  NRLOTE,  DTVALID, DSPRODUT,  QTPRODUT,  DTPRODUC,  NRSSCC,  DTCOLETA,  NRCONTAG,  STITEM) " +
                         "Values " +
                         "(@NRGTIN, @NRLOTE, @DTVALID,     NULL, @QTPRODUT, @DTPRODUC, @NRSSCC, @DTCOLETA, @NRCONTAG, @STITEM) ";

            try
            {
                string dtcoleta = DateTime.Now.ToString("dd/MM/yyyy HH:mm:ss");
                int nrcontag = 1;

                cmd = new SqlCeCommand(sql, conexao);
                cmd.Parameters.AddWithValue("@NRGTIN"  , cdgtin);
                cmd.Parameters.AddWithValue("@NRLOTE"  , nrlote);
                cmd.Parameters.AddWithValue("@DTPRODUC", dtproduc);
                cmd.Parameters.AddWithValue("@DTVALID" , dtvalid);
                cmd.Parameters.AddWithValue("@NRSSCC"  , nrsscc);
                cmd.Parameters.AddWithValue("@QTPRODUT", qtprodut);
                cmd.Parameters.AddWithValue("@DTCOLETA", dtcoleta);                

                if (snconsol)
                {                    
                    cmd.Parameters.AddWithValue("@STITEM", "X");
                }
                else
                {
                    nrcontag = FindItemContag(ref conexao, cdgtin, nrlote, dtvalid);
                    cmd.Parameters.AddWithValue("@STITEM", "E");                
                }

                cmd.Parameters.AddWithValue("@NRCONTAG", nrcontag);
                cmd.ExecuteNonQuery();
                vlresult = true;
            }
            catch (SqlCeException sqlexception)
            {
                if (sqlexception.Message.IndexOf("PK_ENTRADA") > 0)
                {
                    if (SNAvisaPK == true)
                    {
                        Controller.ShowMessage("Etiqueta já registrada!");
                    }
                }
                else
                {
                    Controller.ShowMessage("Erro durante Insercao de contagem: " + sqlexception.Message);
                }
            }            

            return vlresult;

        }
    }
}
