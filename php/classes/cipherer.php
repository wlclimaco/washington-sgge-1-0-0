<?php
class RunnerCipherer{
	public $key = '';
	var $strTableName = '';
	/**
	 * Instance of RunnerCiphererDES class for code-based ciphering
	 */
	var $DESFunctions = null;
	/**
	 * Instance of ProjectSettings class
	 */
	var $pSet = null;
	/**
	 * Array of fields which encrypted status already determined
	 */
	var $encryptedFields = array();
	
	function RunnerCipherer($strTableName, $pSet = null){
		$this->key = GetGlobalData("encryptionKey", 'emptykey');
		$this->strTableName = $strTableName;
		if($pSet != null)
			$this->pSet = $pSet;
		else 
			$this->pSet = new ProjectSettings($strTableName);
	}
	
	/**
	 * DecryptFetchedArray
	 * Fetching record from sql result, looking through array of fetched values and decrypted all encrypted fields
	 * @param {reference} link to sql result
	 * @return {array} fetched array
	 */
	function DecryptFetchedArray($sqlResult, $table = ""){
		$pSet = $this->pSet;
		if($table == "")
			$table = $this->strTableName;
		else
			if($table != $this->strTableName)
				$pSet = new ProjectSettings($table);
		
		$result = array();
		$fetchedArray = db_fetch_array($sqlResult);
		if($fetchedArray){
			if(!$pSet->hasEncryptedFields() || !isEncryptionByPHPEnabled())
				return $fetchedArray;
			foreach ($fetchedArray as $fieldName => $fieldValue){
				$result[$fieldName] = $this->DecryptField($fieldName, $fieldValue);
			}
		}
		return $result;
	}
	
	function isFieldEncrypted($field, $table = ""){
		$pSet = $this->pSet;
		if($table == "")
			$table = $this->strTableName;
		if(array_key_exists($table, $this->encryptedFields) && array_key_exists($field, $this->encryptedFields[$table]))
			return $this->encryptedFields[$table][$field];
		if($table != $this->strTableName)
			$pSet = new ProjectSettings($table);
		if(!array_key_exists($table, $this->encryptedFields))
			$this->encryptedFields[$table] = array();
		$this->encryptedFields[$table][$field] = $pSet->isFieldEncrypted($field);
		return $this->encryptedFields[$table][$field];
	}
	
	function isFieldPHPEncrypted($field){
		return isEncryptionByPHPEnabled() && $this->isFieldEncrypted($field);
	}
	
	function MakeDBValue($field, $value, $controltype = "", $table = "", $phpEncryptionOnly = false){
		$ret = prepare_for_db($field, $value, $controltype, "", $table == "" ? $this->strTableName : $table);
		if($ret===false)
			return $ret;
		$ret = add_db_quotes($field, $this->EncryptField($field, $ret, $table), $table == "" ? $this->strTableName : $table);	
		if($phpEncryptionOnly)
			return $ret;
		return $this->EncryptValueByDB($field, $ret, $table);
	} 
	
	function PrepareForDB($field, $value, $controltype = ""){
		return $this->EncryptValueByDB($field, $this->EncryptField($field, prepare_for_db($field, $value, $controltype, "", $this->strTableName)));	
	}
	
	function AddDBQuotes($field, $value){
		return $this->EncryptValueByDB($field, add_db_quotes($field, $this->EncryptField($field, $value), $this->strTableName));
	}
	
	function GetLikeClause($field, $value){
		if(isEncryptionByPHPEnabled() && $this->isFieldEncrypted($field))
			return "=".db_prepare_string($this->EncryptField($field, $value));
		else 
			return " LIKE ".db_prepare_string($value."%");
	}
	
	/**
	 * GetLookupFieldName
	 * Add to lookup and autofil field name decryption function if master field is encrypted by database 
	 * @param {string} field name
	 * @param {string} master table name
	 * @param {string} master field name
	 * @param {string} alias of field name
	 * @param {bool} shows if 'as' construction needed
	 * @return {string}
	 */
	function GetLookupFieldName($field, $table, $fieldForCheck, $alias = null, $addAs = false){
		if(isEncryptionByPHPEnabled() || !$this->isFieldEncrypted($fieldForCheck, $table))
			return $field;

		return $this->GetEncryptedFieldName($field, $alias, $addAs);
	}
	
	/**
	 * GetFieldName
	 * Add to field name decryption function if field is encrypted by database 
	 * @param {string} field name
	 * @param {string} alias of field name
	 * @param {bool} shows if 'as' construction needed
	 * @return {string}
	 */
	function GetFieldName($field, $alias = null, $addAs = false){
		if(isEncryptionByPHPEnabled() || !$this->isFieldEncrypted($alias != null ? $alias : $field))
			return $field;

		return $this->GetEncryptedFieldName($field, $alias, $addAs);
	}
	
	function GetEncryptedFieldName($field, $alias = null, $addAs = false){
		$result = "";
		
		
		
				
				
		if($result != "")
		{
						$result = mysprintf($result, array($field, $this->key));
		}
		else 
			$result = $field;
			
		return $result != $field 
			? ($addAs ? $result." as ".AddFieldWrappers($alias != null ? $alias : $field) : $result)
			: AddFieldWrappers($field);
	}
	
	/**
	 * EncryptValueByDB
	 * Add to field name encryption function if field is encrypted by database 
	 * @param {string} field name
	 * @return {string}
	 */
	function EncryptValueByDB($field, $value, $table = ""){
		if(!$this->isFieldEncrypted($field, $table) || isEncryptionByPHPEnabled())
			return $value;
			
		$result = "";
		
		
		
				
				
		if($result != "")
		{
						$result = mysprintf($result, array($value, $this->key));
		}
		else 
			$result = $value;
			
		return $result;
	}
	
	/**
	 * EncryptField
	 * Determine if field need to be encrypted and encrypt value if it so 
	 * @param {string} field name
	 * @param {string} value
	 * @return {string} encrypted or plain value
	 */
	function EncryptField($field, $value, $table = ''){
		if($table == '')
			$table = $this->strTableName;
		if($this->isFieldEncrypted($field, $table) && isEncryptionByPHPEnabled()){
			if(is_null($this->DESFunctions))
				$this->DESFunctions = new RunnerCiphererDES($this->key);
			return $this->DESFunctions->DESEncrypt($value);
		}
		
		return $value; 
	}
	
	/**
	 * DecryptField
	 * Determine if field encrypted and decrypt value if it so 
	 * @param {string} field name
	 * @param {string} value
	 * @return {string} decrypted or plain value
	 */
	function DecryptField($field, $value){
		if($this->isFieldEncrypted($field) && isEncryptionByPHPEnabled())
		{
			if(is_null($this->DESFunctions))
				$this->DESFunctions = new RunnerCiphererDES($this->key);
			return $this->DESFunctions->DESDecrypt($value);
		}
		return $value; 
	}
}
?>