<?
	class TQuery {
	// *********************
	// ******* privados
	// *********************
		var $gDsSql    = "";
		var $gNmUsuari = "";
		var $gDsResult;
		var $gDsDados;
		var $gSnEof    = true;
		var $gNoRecord = -1;
		var $gQtRecord  = 0;

 		var $gTpBanco   = "M";
		var $gDsConexa  = false;
/*
 		var $gNmServer  = "localhost";
 		var $gNmBanco   = "bravo";
 		var $gNmUser    = "root";
		var $gDsSenha   = "masterkey";
*/
 		var $gNmServer  = "mysql-g13a.mysqldbserver.com";
// 		var $gNmServer  = "192.10.10";
 		var $gNmBanco   = "bravol";
 		var $gNmUser    = "bravol";
		var $gDsSenha   = "tebhg+Br";

		
		function TQuery() {
		}

		function ConectaBanco() {
			$pDsErrSer = 'Erro ao conectar ao servidor de banco de dados ' . 
						$this->gNmServer . '<br>';
						
			$pDsErrBan = "Não foi possível estabelecer conexão com o banco de dados " . $this->gNmBanco;
						
			if ($this->gTpBanco == 'M') { // MySQL
				$this->gDsConexa = mysql_pconnect($this->gNmServer, $this->gNmUser, $this->gDsSenha) 
									or die ($pDsErrSer . mysql_error());
				mysql_select_db($this->gNmBanco, $this->gDsConexa) or die ($pDsErrBan);
				
			} elseif ($this->gTpBanco == 'S') { // SQL Server
				$this->gDsConexa = mssql_connect($this->gNmServer, $this->gNmUser, $this->gDsSenha) 
									or die ($pDsErrSer);
				mssql_select_db($this->gNmBanco);
				
			} elseif ($this->gTpBanco == 'F') { // Firebird
				$lNmBanco = $this->gNmServer . $this->gNmBanco;
				$this->gDsConexa = ibase_connect($lNmBanco, $this->gNmUser, $this->gDsSenha) 
									or die ($pDsErrSer);
									
			} else {
				die('Tipo de banco de dados não definido << TQuery >>');
			}
		}

		function SetEof($pTpEof) {
			if ($pTpEof == "F") {
			   $this->gSnEof = false;
			} else {
			   $this->gSnEof = true;
			}
		}
		
		function RetornaDados() {
			if ($this->gTpBanco == 'M') { // MySQL
				if ($this->gDsResult = mysql_fetch_array($this->gDsDados)) {
                    $this->SetEof('F');
                } else {
                    $this->SetEof('T');
				}
			} elseif ($this->gTpBanco == 'S') { // SQL Server
				if ($this->gDsResult = mssql_fetch_array($this->gDsDados)) {
                    $this->SetEof('F');
				} else {
                    $this->SetEof('T');
				}
			} elseif ($this->gTpBanco == 'F') { // Firebird
				if ($this->gDsResult = ibase_fetch_assoc($this->gDsDados)) {
                    $this->SetEof('F');
				} else {
                    $this->SetEof('T');
				}
			} else {
				die('Tipo de banco de dados não definido');
			}
		}

		function ibase_data_seek($pNoRecord) {
			// =-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=
			// =-=-= Firebird não possui data_seek
			// =-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=
			$this->Execute();

			$lNoRecord = 0;
			while ($lNoRecord < $pNoRecord) {
				$lDsResult = ibase_fetch_assoc($this->gDsDados);
				$lNoRecord++;
			}
			
			return;
		}	
		
		function SeekRecord($pNoRecord) {
			if ($this->gTpBanco == 'M') { // MySQL
				mysql_data_seek($this->gDsDados, $pNoRecord);
			} elseif ($this->gTpBanco == 'S') { // SQL Server
				mssql_data_seek($this->gDsDados, $pNoRecord);
			} elseif ($this->gTpBanco == 'F') { // Firebird
			    $this->ibase_data_seek($pNoRecord);
			} else {
				die('Tipo de banco de dados não definido');
			}
		}
		
		function GravarLog($pDsComand, $pDsSql) {
			if ($pDsComand == "INSERT") {
				$this->GravarLogInsert($pDsSql);
			} elseif ($pDsComand == "UPDATE") {
				$this->GravarLogUpdate($pDsSql);
			} elseif ($pDsComand == "DELETE") {
				$this->GravarLogDelete($pDsSql);
			} 
		}
		
		function GravarLogInsert($pDsSql) {
			$pDsSql = str_replace(")", "", $pDsSql);
//			$pDsSql = str_replace(",", "", $pDsSql);
			$pDsSql = str_replace("VALUES", "", $pDsSql);
			
			$lDsSql    = explode("(", $pDsSql);
			$lNmTabela = explode(" ", $lDsSql[0]);

			$lDsSql[1] = str_replace(",", "", $lDsSql[1]);
			$lNmColuna = explode(" ", $lDsSql[1]);
			$lDsValor  = explode(",", $lDsSql[2]);
			$lDsChave  = explode("@", $this->ChavePrimaria($lNmTabela[2]) );
			$lDsChaPri = "";
			
			for ($i = 0; $i < sizeof($lDsChave); $i++) {
				$lDsChave[$i] = trim($lDsChave[$i]);
				
				if ($lDsChave[$i] != "") {
					for ($x = 0; $x < sizeof($lNmColuna); $x++) {
						$lNmColuna[$x] = trim($lNmColuna[$x]);

						if ($lNmColuna[$x] == $lDsChave[$i]) {
							$lDsChaPri = $lDsChaPri . $lDsValor[$x] . "@";
						}
					}
				}
			}
			
			$lDsChaPri = str_replace("'", "", $lDsChaPri);

			for ($i = 0; $i < sizeof($lDsValor); $i++) {
				if ($lDsValor[$i] != "NULL") {
					$this->EfetivarLog($lNmTabela[2], $lDsChaPri, $lNmColuna[$i], $lDsValor[$i], 1);
				}
			}
		}
		
		function GravarLogUpdate($pDsSql) {
			$lDsSql    = explode("SET", $pDsSql);
			$lNmTabela = explode(" ", $lDsSql[0]);
			$lDsSql    = explode("WHERE", $lDsSql[1]);
			$lNmColuna = explode(",", $lDsSql[0]);
			$lVlChave  = explode("AND", $lDsSql[1]);
			
			$lDsChave  = explode("@", $this->ChavePrimaria($lNmTabela[1]) );
			$lDsChaPri = "";
			
			for ($i = 0; $i < sizeof($lDsChave); $i++) {
				$lDsChave[$i] = trim($lDsChave[$i]);
				if ($lDsChave[$i] != "") {
					for ($x = 0; $x < sizeof($lVlChave); $x++) {
					    $lDsValor = explode("=", $lVlChave[$x]);
						$lDsValor[0] = trim($lDsValor[0]);

						if ($lDsValor[0] == $lDsChave[$i]) {
							$lDsChaPri = $lDsChaPri . trim($lDsValor[1]) . "@";
						}
					}
				}
			}
			
			$lDsChaPri = str_replace("'", "", $lDsChaPri);

			for ($i = 0; $i < sizeof($lNmColuna); $i++) {
			    $lVlColuna    = explode("=", $lNmColuna[$i]);
				$lVlColuna[0] = trim($lVlColuna[0]);
				$lVlColuna[1] = trim($lVlColuna[1]);

				if ($lVlColuna[1] == "NULL") {
					$lVlColuna[1] = "''";
				}

				$this->EfetivarLog($lNmTabela[1], $lDsChaPri, $lVlColuna[0], $lVlColuna[1], 2);
			}
		}
		
		function GravarLogDelete($pDsSql) {
			$lNmTabela = explode(" ", $pDsSql);

			$lDsSql    = explode("WHERE", $pDsSql);
			$lVlChave  = explode("AND", $lDsSql[1]);
			
			$lDsChave  = explode("@", $this->ChavePrimaria($lNmTabela[2]) );
			$lDsChaPri = "";
			
			for ($i = 0; $i < sizeof($lDsChave); $i++) {
				$lDsChave[$i] = trim($lDsChave[$i]);
				if ($lDsChave[$i] != "") {
					for ($x = 0; $x < sizeof($lVlChave); $x++) {
					    $lDsValor = explode("=", $lVlChave[$x]);
						$lDsValor[0] = trim($lDsValor[0]);

						if ($lDsValor[0] == $lDsChave[$i]) {
							$lDsChaPri = $lDsChaPri . trim($lDsValor[1]) . "@";
						}
					}
				}
			}
			
			$lDsChaPri = str_replace("'", "", $lDsChaPri);

			for ($i = 0; $i < sizeof($lDsChave); $i++) {
				$lDsChave[$i] = trim($lDsChave[$i]);
				if ($lDsChave[$i] != "") {
					for ($x = 0; $x < sizeof($lVlChave); $x++) {
					    $lDsValor = explode("=", $lVlChave[$x]);
						$lDsValor[0] = trim($lDsValor[0]);

						if ($lDsValor[0] == $lDsChave[$i]) {
							$this->EfetivarLog($lNmTabela[2], $lDsChaPri, $lDsValor[0], $lDsValor[1], 3);
						}
					}
				}
			}
		}
		
		function ChavePrimaria($pNmTabela) {
			$lDsChaPri = "";
			$QryChaPri = new TQuery($this->gTpBanco, $this->gNmServer, $this->gNmBanco, $this->gNmUsuari);
			$QryChaPri->Sql("Select NmColuna From TbChavePrimaria Where NmTabela = '$pNmTabela' Order by NoSequen");
			$QryChaPri->Open();
			
			while (!$QryChaPri->EOF()) { 
            	$lDsChaPri = $lDsChaPri . $QryChaPri->FieldByName('NmColuna') . "@";
				$QryChaPri->Next();
            }
			
			return $lDsChaPri;
		}
		
		function EfetivarLog($pNmTabela, $pDsChave, $pNmColuna, $pDsValor, $pTpOperac) {
			$QryGraLog = new TQuery($this->gTpBanco, $this->gNmServer, $this->gNmBanco, $this->gNmUsuari);
			$QryGraLog->Sql("Select SnGraLog From TbTabela Where NmTabela = '$pNmTabela'");
			$QryGraLog->Open();
			
			if ($QryGraLog->FieldByName('SnGraLog') == 'S') {
			    $lDtAno  = date("Y",time());
			    $lDtMes  = date("m",time());
			    $lDtDia  = date("d",time());

				$lHoHora = date("H:i:s");
				$lDtAltera = $lDtAno . "-" . $lDtMes . "-" . $lDtDia . " " . $lHoHora;
				
				$pDsValor = str_replace("'", "", $pDsValor);
				$pDsValor = trim($pDsValor);
		
				$lDsSql = "Insert Into S020 (NmTabela,     DtAltera,     NmColuna,   NmUsuari, 
											 TpOperac,     VlAtual,      VlChave)
						   values        ('$pNmTabela', '$lDtAltera',  '$pNmColuna', '$this->gNmUsuari', 
						   				  '$pTpOperac',  '$pDsValor',  '$pDsChave')";

				if ($this->gTpBanco == 'M') { // MySQL
					mysql_query($lDsSql) or die("Erro atualização do log: $lDsSql");
				} elseif ($this->gTpBanco == 'S') { // SQL Server
					mssql_query($lDsSql) or die("Erro atualização do log: $lDsSql");
				} elseif ($this->gTpBanco == 'F') { // Firebird
					ibase_query($lDsSql) or die("Erro atualização do log: $lDsSql");
				} else {
					die('Tipo de banco de dados não definido');
				}
			}
		}
		
	// *********************
	// ******* publicos
	// *********************
		function ShowSql() {
			echo $this->gDsSql;
		}
		
		function Sql($pDsSql) {
			// =-=-=-=-=-=-=-=-=-=
			// =-= Recebe a instrução SQL
			// =-=-=-=-=-=-=-=-=-=
			$this->gDsSql    = str_replace("\\", "", $pDsSql);
		}
		
		function Execute() {
			// =-=-=-=-=-=-=-=-=-=
			// =-= Abre a instrução SQL
			// =-=-=-=-=-=-=-=-=-=
			if (!$this->gDsConexa) {
				$this->ConectaBanco();
			}		
			
			$pDsErrSql	= "<font color='#FF0000'>Erro ao executar instrução sql: <br>" . $this->gDsSql . "<font>";

			if ($this->gTpBanco == 'M') { // MySQL
				$this->gDsDados = mysql_query($this->gDsSql) or die ($pDsErrSql);
			} elseif ($this->gTpBanco == 'S') { // SQL Server
				$this->gDsDados = mssql_query($this->gDsSql) or die ($pDsErrSql);
			} elseif ($this->gTpBanco == 'F') { // Firebird
				$this->gDsDados = ibase_query($this->gDsSql) or die ($pDsErrSql);
			} else {
				die('Tipo de banco de dados não definido');
			}
			/*
			$lDsSql     = strtoupper(trim($this->gDsSql));
			$lDsComand = explode(" ", $lDsSql);
			
			if (($lDsComand[0] == 'INSERT') or ($lDsComand[0] == 'UPDATE') or ($lDsComand[0] == 'DELETE')) {
				$this->GravarLog($lDsComand[0], $lDsSql);
			}
			*/
		}
		
		function ibase_num_rows() {
			// =-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=
			// =-=-= Firebird não possui num_rows
			// =-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=
			$lQtRecord = 0;
			while (ibase_fetch_assoc($this->gDsDados)) {
				$lQtRecord++;
			}
			$this->Execute();
			return $lQtRecord;
		}
		
		function Open() {
			$this->Execute();

			if ($this->gTpBanco == 'M') { // MySQL
				$this->gQtRecord = mysql_num_rows($this->gDsDados);
			} elseif ($this->gTpBanco == 'S') { // SQL Server
				$this->gQtRecord = mssql_num_rows($this->gDsDados);
			} elseif ($this->gTpBanco == 'F') { // Firebird
				$this->gQtRecord = $this->ibase_num_rows();
			} else {
				die('Tipo de banco de dados não definido. Método Open()');
			}

			$this->gNoRecord = -1;
			$this->Next();
		}
		
		function Close() {
			if ($this->gTpBanco == 'M') { // MySQL
				mysql_close();			
			} else {
				die('Tipo de banco de dados não definido. Método Close()');
			}
		}
		
		function First() {
			// =-=-=-=-=-=-=-=-=-=
			// =-= Vai pro primeiro registro
			// =-=-=-=-=-=-=-=-=-=
			if ($this->RecordCount() >= 1) {

				$this->SeekRecord(0);
				$this->gNoRecord = -1;
				$this->Next();
			}
		}
		
		function Next() {
			// =-=-=-=-=-=-=-=-=-=
			// =-= Vai pro último registro
			// =-=-=-=-=-=-=-=-=-=
			$this->RetornaDados();
			$this->gNoRecord++;;
		}
		
		function Previous() {
			// =-=-=-=-=-=-=-=-=-=
			// =-= Vai para o registro anterior
			// =-=-=-=-=-=-=-=-=-=
			if ($this->RecordCount() > 1) {
				if ($this->gNoRecord > 0) {
					$this->gNoRecord--;
					$this->SeekRecord($this->gNoRecord);
					$this->RetornaDados();
				}
			} elseif ($this->RecordCount() == 1) {
					$this->gNoRecord = $this->RecordCount() -1;
					$this->SeekRecord($this->gNoRecord);
					$this->RetornaDados();
			}
		}

		function Last() {
			// =-=-=-=-=-=-=-=-=-=
			// =-= Vai para o último registro
			// =-=-=-=-=-=-=-=-=-=
			if ($this->RecordCount() > 1) {
				$this->SeekRecord($this->RecordCount() - 1);
				$this->gNoRecord = $this->RecordCount() - 1;
				$this->RetornaDados();
			}
		}
		
		function FieldByName($pNmColuna) {
			// =-=-=-=-=-=-=-=-=-=
			// =-= Retorna o conteúdo de uma coluna
			// =-=-=-=-=-=-=-=-=-=
			if ($this->gTpBanco == 'F') { // Firebird
				$pNmColuna = strtoupper($pNmColuna);
				
				// se for blob $col_info['type'] lascou, tem que achar a coluna pelo nome e devolver ibase_blob_echo
			}
			return $this->gDsResult[$pNmColuna];
		}
		
		function EOF() {
			// =-=-=-=-=-=-=-=-=-=
			// =-= Retorna true se estiver no fim do arquivo
			// =-=-=-=-=-=-=-=-=-=
			return $this->gSnEof;
		}
		
		function RecordCount() {
			// =-=-=-=-=-=-=-=-=-=
			// =-= Retorna a quantidade de registros do resultado
			// =-= da pesquisa SQL
			// =-=-=-=-=-=-=-=-=-=
			return $this->gQtRecord;
		}
			
		function FieldCount() {
			if ($this->gTpBanco == 'M') { // MySQL
				return mysql_num_fields($this->gDsDados); 
			} elseif ($this->gTpBanco == 'S') { // SQL Server
				return mssql_num_fields($this->gDsDados);
			} elseif ($this->gTpBanco == 'F') { // Firebird
				return ibase_num_fields($this->gDsDados);
			} else {
				die('Tipo de banco de dados não definido');
			}
		}
		
		function FieldName($pNoIndex) {
			if ($this->gTpBanco == 'M') { // MySQL
				return mysql_field_name($this->gDsDados, $pNoIndex); 
			} elseif ($this->gTpBanco == 'S') { // SQL Server
				return mssql_field_name($this->gDsDados, $pNoIndex);
			} elseif ($this->gTpBanco == 'F') { // Firebird
				$lDsColInf = ibase_field_info($this->gDsDados, $pNoIndex);
				return $lDsColInf['name'];
			} else {
				die('Tipo de banco de dados não definido');
			}
		}
		
	}

?>