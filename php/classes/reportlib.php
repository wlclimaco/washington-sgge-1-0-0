<?php


$report_classfields = array();
function create_reportfield($name, $type, $interval, $alias, $table)
{
	global $report_classfields;
	if($type)
	{
		if(isset($report_classfields[$type]))
		{
			return new $report_classfields[$type]($name, $interval, $alias, $table);
		}
		else
		{
			die('Unsupported group field type: `'.$type.'`');
		}
	}
}

class ReportField
{
	var $_interval = 0;
	var $_tName = '';
	var $_name = '';
	var $_alias = '';
	var $_sqlname = '';
	var $_start = 0;
	var $_caseSensitive = false;
	var $_recordBasedRequest = false;
	var $_rowsInSummary = 0;
	var $_rowsInHeader = 0;
	var $_viewFormat = '';
	var $_orderBy = 'ASC';
	var $_oldAlgorithm = false;
	// Instance of ProjectSettings
	var $pSet = null;
	
	function ReportField($name, $interval, $alias, $table)
	{
		$this->_name = $name;
		$this->_interval = $interval;
		$this->_alias = $alias;
		$this->_sqlname = $alias;
		$this->_tName = $table;
		if($table != '')
			$this->pSet = new ProjectSettings($table);
	}
	
	
	function getStringSql($forGroupedField = false) { die; }
	
	
	function getFieldName($fieldValue, $data = null) { die; }

	
	function getSelectSql($hasGrouping = false)
	{
        return $this->getStringSql(true) . ($this->alias() ? ' as ' . cached_ffn($this->alias()) : '');
	}
	
	
	function getGroupSql()
	{
		return $this->getStringSql();
	}
	
	
	function getOrderSql()
	{
		//return $this->alias().' ASC';
		return $this->getStringSql().' '.$this->_orderBy.' ';
	}
	
	
	function getWhereSql($groups) { die; }
	
	
	function getGroup($data)
	{
		return $data[$this->alias()];
	}
	
	
	function getKey($data)
	{
		return $data[$this->alias()];
	}
	
	
	function setStart($start)
	{
		$this->_start = $start;
		$this->_sqlname = $this->alias();
		return $start + 1;
	}
	
	
	function name()
	{
		return $this->_name;
	}
	
	
	function alias()
	{
		return $this->_alias . $this->_start;
	}
	
	
	function overrideFormat()
	{
		return false;
	}
	
	function setCaseSensitive($cs)
	{
		$this->_caseSensitive = $cs;
	}
	
	function cutNull(&$range, $checkEmty = false)
	{
		$ret = false;
		$out = array();
		for($nCnt = 0; $nCnt < count($range); $nCnt++)
		{
			$b = false;
			if($range[$nCnt] === null)
			{
				$b = true;
				$ret = true;
			}
			else
			{
				if($checkEmty && (!$range[$nCnt] || strcasecmp($range[$nCnt], 'null') == 0))
				{
					$b = true;
					$ret = true;
				}
			}
			
			if(!$b)
			{
				$out []= $range[$nCnt];
			}
		}
		$range = $out;
		return $ret;
	}
	
	function getLtGt(&$lt, &$gt)
	{
		if($this->_orderBy != 'ASC')
		{
			$lt = ' >= ';
			$gt = ' <= ';
		}
		else
		{
			$lt = ' <= ';
			$gt = ' >= ';
		}
	}
}

$report_classfields['numeric'] = 'ReportNumericField';
class ReportNumericField extends ReportField
{
	function ReportNumericField($name, $interval, $alias, $table)
	{
		ReportField::ReportField($name, $interval, $alias, $table);
	}
	
	function getStringSql($forGroupedField = false)
	{
		$fname = $this->_oldAlgorithm ? GetFullFieldNameForInsert($this->pSet, $this->_name) : cached_ffn($this->_name, true);
		if($this->_interval > 0)
		{
		}
		else
		{
			return $fname;
		}
	}
	
	function getFieldName($fieldValue, $data)
	{
		$value = $data[$this->_recordBasedRequest ? $this->_name : $this->_sqlname];
		if($value == null)
			return 'NULL';
		if($this->_interval > 0)
			return intval($value) . ' - ' . (intval($value) + $this->_interval);
		else
			return intval($value);
	}
	
	function getKey($data)
	{
		if($this->_recordBasedRequest)
		{
			if($this->_interval > 0)
				return intval($data[$this->_name]/$this->_interval)*$this->_interval;
			else
				return $data[$this->_name];
		}
		else
			return ReportField::getKey($data);
	}
	
	function getWhereSql($groups)
	{
		$ret = '';
		$ssql = $this->getStringSql();
		$hasNull = $this->cutNull($groups);
	
		if(count($groups) > 0)
		{
			$lt = '';
			$gt = '';
			$this->getLtGt($lt, $gt);
			$ret = '('.$ssql.$gt.$groups[0].' AND '.$ssql.$lt.$groups[count($groups) - 1].')';
		}
		
		if($hasNull)
		{
			$ret .= ($ret ? ' OR ' : '').$ssql.' IS NULL';
		}
		
		return $ret ? '('.$ret.')' : '';
	}
}

$report_classfields['char'] = 'ReportCharField';
class ReportCharField extends ReportField
{
	function ReportCharField($name, $interval, $alias, $table)
	{
		ReportField::ReportField($name, $interval, $alias, $table);
	}
	
	function getStringSql($forGroupedField = false)
	{
		$fname = $this->_oldAlgorithm && !$forGroupedField ? GetFullFieldNameForInsert($this->pSet, $this->_name) : cached_ffn($this->_name, $forGroupedField);
		if($this->_interval > 0)
		{
		}
		else
		{
			return $fname;
		}
	}
	
	function getFieldName($fieldValue, $data)
	{
		$value = $data[$this->_recordBasedRequest ? $this->_name : $this->_sqlname];
		if($value == null)
			return 'NULL';
		if($this->_interval > 0)
	        return substr($value, 0, $this->_interval);
		else
			return $value;
	}
	
	function getKey($data)
	{
		if($this->_recordBasedRequest)
		{
			if($this->_interval > 0)
			{
				if($this->_caseSensitive)
					return substr($data[$this->_name], 0, $this->_interval);
				else
					return strtolower(substr($data[$this->_name], 0, $this->_interval));
			}
			else
			{
				if($this->_caseSensitive)
					return $data[$this->_name];
				else
					return strtolower($data[$this->_name]);
			}
		}
		else
		{
			if($this->_caseSensitive)
				return $data[$this->alias()];
			else
				return strtolower($data[$this->alias()]);
		}
	}
	
	function getWhereSql($groups)
	{
		$ret = '';
		$ssql = $this->getStringSql();
		$hasNull = $this->cutNull($groups);
		
		if(count($groups) > 0)
		{
			$gr = array();
			foreach($groups as $g)
				$gr []= '\''.$g.'\'';
				
			$lt = '';
			$gt = '';
			$this->getLtGt($lt, $gt);
			
			$ret = "(".$ssql.$gt.db_prepare_string($groups[0])." AND ".$ssql.$lt.db_prepare_string($groups[count($groups) - 1]).")";
		}
		
		if($hasNull)
		{
			$ret .= ($ret ? ' OR ' : '').$ssql.' IS NULL';
			$ret .= ' OR '.$ssql.'=\'\'';
		}
		
		return $ret ? '('.$ret.')' : '';
	}
}

$report_classfields['date'] = 'ReportDateField';
class ReportDateField extends ReportField
{
	function ReportDateField($name, $interval, $alias, $table)
	{
		ReportField::ReportField($name, $interval, $alias, $table);
	}
	
	function setStart($start)
	{
		$this->_start = $start;
		if($this->_interval == 0)
			$this->_sqlname = $this->alias();
		else
			$this->_sqlname = $this->alias().'MIN';
		return $start + ($this->_interval > 0 ? $this->_interval : 1);
	}
	
	function getSqlList($all = true)
	{
	    $grp = array();

		$fname = $this->_oldAlgorithm ? GetFullFieldName($this->_name) : cached_ffn($this->_name);
		if($all)
		{
		// array = (year, quarter, month, week, day of week, full date, hour, minute)
		// each element = array(sql begin, sql end, prev index)

			$idx = $this->_interval - 1;
			do
			{
				array_unshift($grp, $symbols[$idx][0].cached_ffn($this->_name).$symbols[$idx][1]);
				$idx = $symbols[$idx][2];
			}
			while($idx >= 0);
		}

		return $grp;
	}
	
	function getSelectSql($hasGrouping)
	{
		$fname = $this->_oldAlgorithm ? GetFullFieldName($this->_name) : cached_ffn($this->_name, true);
		if($this->_interval == 0)
		{
			return $fname . ' as ' . cached_ffn($this->alias());
		}
		else
		{
			$grp = $this->getSqlList();
		
			for($nCnt = 0; $nCnt < count($grp); $nCnt ++)
			{
				$grp[$nCnt] .=  ' as ' . cached_ffn($this->_alias.($nCnt + $this->_start));
			}
		
			if($hasGrouping)
			{
				$grp[] = 'MIN('.$fname.') as '.cached_ffn($this->alias().'MIN');
				$grp[] = 'MAX('.$fname.') as '.cached_ffn($this->alias().'MAX');
			}
			else
				$grp[] = $fname.' as '.cached_ffn($this->alias().'MIN');
			
	        return join(', ', $grp);
		}
	}
	
	function getGroupSql()
	{
		if($this->_interval == 0)
		{
			return cached_ffn($this->_name);
		}
		else
		{
			$grp = $this->getSqlList();
	        return join(', ', $grp);
		}
	}
	
	function getOrderSql()
	{
		if($this->_interval == 0)
		{
			$fname = $this->_oldAlgorithm ? GetFullFieldName($this->_name) : cached_ffn($this->_name);
			return $fname.' '.$this->_orderBy.' ';
		}
		else
		{
			$grp = $this->getSqlList();
			$newgrp = array();
			foreach($grp as $g)
				$newgrp[] = $g . ' '.$this->_orderBy.' ';
	        return join(', ', $newgrp);
		}
	}
	
	function getWhereSql($groups)
	{
		$ret = '';
		$hasNull = $this->cutNull($groups, true);
		
		if(count($groups) > 0)
		{
			$lt = '';
			$gt = '';
			
			if($this->_interval == 0)
			{
				$this->getLtGt($lt, $gt);
				$ret = '('.cached_ffn($this->_name).' '.$gt.' '.db_datequotes($groups[0]).
					' AND '.cached_ffn($this->_name).' '.$lt.' '.db_datequotes($groups[count($groups)-1]).')';
			}
			else
			{
				if($groups[0]['MIN'] <= $groups[count($groups) - 1]['MAX'])
				{
					$ret = '('.cached_ffn($this->_name).' >= '.db_datequotes($groups[0]['MIN']).
						' AND '.cached_ffn($this->_name).' <= '.db_datequotes($groups[count($groups)-1]['MAX']).')';
				}
				else
				{
					$ret = '('.cached_ffn($this->_name).' <= '.db_datequotes($groups[0]['MAX']).
						' AND '.cached_ffn($this->_name).' >= '.db_datequotes($groups[count($groups)-1]['MIN']).')';
				}
			}
		}
			
		if($hasNull)
			$ret .= ($ret ? ' OR ' : '').cached_ffn($this->_name).' IS NULL ';
		
		return $ret ? '('.$ret.')' : '';
	}
	
	function getFieldName($fieldValue, $data)
	{
		global $locale_info;
		
		$value = $data[$this->_recordBasedRequest ? $this->_name : $this->_sqlname];
		if($value == null || !$value || strcasecmp($value, 'null') == 0)
			return 'NULL';
		
		if($this->_interval == 0)
		{
			if($this->_viewFormat)
			{
				global $pageObject;
				return $pageObject->viewControls->showDBValue($this->_name, $data);
			}
			else
			{
				$date = db2time($value);
				return str_format_datetime($date);
			}
		}
		
		switch($this->_interval)
		{
			case 1:
				$date = cached_db2time($value);
				return $date[0];
				
			case 2:
				$date = cached_db2time($value);
				return $date[0] . '/Q' . intval($date[1]/3);
						
			case 3:
				$date = cached_db2time($value);
				return @$locale_info["LOCALE_SABBREVMONTHNAME".$date[1]]." ".$date[0];
					
			case 4:
				return cached_formatweekstart($value);
					
			case 5:
				$date = cached_db2time($value);
				return format_shortdate($date);
					
			case 6:
				$date = db2time($value);
				$date[4]=0;
				$date[5]=0;
				return str_format_datetime($date);
					
			case 7:
				$date = db2time($value);
				$date[5]=0;
				return str_format_datetime($date);
		}
	}
	
	function getGroup($data)
	{
		if($this->_interval == 0)
			return $data[$this->alias()];
		else
		{
			if($data[$this->alias().'MIN'] == null || $data[$this->alias().'MAX'] == null)
				return null;
			else
				return array('MIN' => $data[$this->alias().'MIN'], 'MAX' => $data[$this->alias().'MAX']);
		}
	}

	function getKey($data)
	{
		if(!$this->_recordBasedRequest)
		{
			if($this->_interval == 0)
			{
				return $data[$this->alias()];
			}
			else
			{
		        $key = array();
				for($nCnt = $this->_start; $nCnt < $this->_interval + $this->_start; $nCnt++)
				{
		            $key []= $data[$this->_alias.$nCnt];
		        }
		        return join('-', $key);
			}
		}
		else
		{
			$strdate = $data[$this->_name];
			if($strdate == null)
				return 'NULL';
			
			if($this->_interval == 0)
			{
				return $strdate;
			}
			else
			{
				switch($this->_interval)
				{
					case 1:
						$date = cached_db2time($strdate);
						return $date[0];
						
					case 2:
						$date = cached_db2time($strdate);
						return $date[0].'-'.intval($date[1]/3);
						
					case 3:
						$date = cached_db2time($strdate);
						return $date[0].'-'.$date[1];
					
					case 4:
						$start = cached_getweekstart($strdate);
						return $start[0].'-'.$start[1].'-'.$start[2];
					
					case 5:
						$date = cached_db2time($strdate);
						return $date[0].'-'.$date[1].'-'.$date[2];
					
					case 6:
						$date = db2time($strdate);
						return $date[0].'-'.$date[1].'-'.$date[2].'-'.$date[3];
					
					case 7:
						$date = db2time($strdate);
						return $date[0].'-'.$date[1].'-'.$date[2].'-'.$date[3].'-'.$date[4];
				}
			}
		}
	}
	
	function overrideFormat()
	{
		return true;
	}

	function cutNull(&$range, $checkEmpty = false)
	{
		$ret = false;
		$out = array();
		for($nCnt = 0; $nCnt < count($range); $nCnt++)
		{
			$b = false;
			if($range[$nCnt] === null)
			{
				$b = true;
				$ret = true;
			}
			else
			{
				if($checkEmpty)
				{
					if(is_array($range[$nCnt]))
					{
						if(!$range[$nCnt]['MIN'] || strcasecmp($range[$nCnt]['MIN'], 'null') == 0)
						{
							$b = true;
							$ret = true;
						}
					}
					else
					{
						if(!$range[$nCnt] || strcasecmp($range[$nCnt], 'null') == 0)
						{
							$b = true;
							$ret = true;
						}
					}
				}
			}
			
			if(!$b)
			{
				$out []= $range[$nCnt];
			}
		}
		$range = $out;
		return $ret;
	}
	
}

function getFormattedValue($pageObject, $value, $fieldName, $strViewFormat, $strEditFormat = '', $mode = MODE_LIST)
{
	if($strViewFormat == FORMAT_TIME && is_numeric($value))
	{
		$val = '';
		
		$d = intval($value / 86400);
		$h = intval(($value % 86400) / 3600);
		$m = intval((($value % 86400) % 3600) / 60);
		$s = (($value % 86400) % 3600) % 60;
		
		$val .= $d > 0 ? $d . 'd ' : '';
		$val .= str_format_time(array(0, 0, 0, $h, $m, $s));
	}
	else
	{
		$arrValue = array($fieldName => $value);
		$val = $pageObject->getViewControl($fieldName, $strViewFormat)->showDBValue($arrValue,"");
	}
		
	return $val;
}

$cache_db2time = array();
function cached_db2time($strtime)
{
	global $cache_db2time;
	if(!isset($cache_db2time[$strtime]))
	{
		$res = db2time($strtime);
		$cache_db2time[$strtime] = $res;
		return $res;
	}
	else
		return $cache_db2time[$strtime];
}

$cache_getdayofweek = array();
function cached_getdayofweek($strtime)
{
	global $cache_getdayofweek;
	if(!isset($cache_getdayofweek[$strtime]))
	{
		$date = cached_db2time($strtime);
		$res = getdayofweek($date);
		$cache_getdayofweek[$strtime] = $res;
		return $res;
	}
	else
		return $cache_getdayofweek[$strtime];
}

$cache_getweekstart = array();
function cached_getweekstart($strtime)
{
	global $cache_getweekstart;
	if(!isset($cache_getweekstart[$strtime]))
	{
		$date = cached_db2time($strtime);
		$res = getweekstart($date);
		$cache_getweekstart[$strtime] = $res;
		return $res;
	}
	else
		return $cache_getweekstart[$strtime];
}

$cache_formatweekstart = array();
function cached_formatweekstart($strtime)
{
	global $cache_formatweekstart;
	if(!isset($cache_formatweekstart[$strtime]))
	{
		$start = cached_getweekstart($strtime);
		$end = adddays($start, 6);
		$res = format_shortdate($start)." - ".format_shortdate($end);
		$cache_formatweekstart[$strtime] = $res;
		return $res;
	}
	else
		return $cache_formatweekstart[$strtime];
}

$cache_fullfieldname = array();
function cached_ffn($field, $forGroupedField = false)
{
	global $cache_fullfieldname, $strTableName;
	if(!isset($cache_fullfieldname[$field]))
	{
		// commented for bug 6660. Correct fix needed
		//
		if(!$wr_is_standalone && !$forGroupedField)
			$res = GetFullFieldName($field,$strTableName,false);
		else
			$res = AddFieldWrappers($field);
		$cache_fullfieldname[$field] = $res;
		return $res;
	}
	else
		return $cache_fullfieldname[$field];
}

///////////////////////////////////////////////////////////////////////////////////
class SQLStatement
{
	var $_fields = array();
	var $_hasDetails = true;
	var $_originalSql = null;
	var $_order_in;
	var $_order_out;
	var $_order_old;
	var $_aggregates = array();
	var $_skipCount = 0;
	
	var $_reportGlobalSummary = true;
	
	var $_reportSummary = true;
	var $_details = true;
	var $_from = 0;
	var $_pagesize = 20;
	var $_limitLevel = 0;
	var $_hasGroups = true;
	var $searchClauseObj = null;
	var $_recordBasedRequest = false;
	var $_oldAlgorithm = false;
	
	
	// report table info
	var $tName = '';
	var $shortTName = '';	
	var $repGroupFieldsCount = 0;
	var $repPageSummary = 0;
	var $repGlobalSummary = 0;
	var $repLayout = 0;
	var $showGroupSummaryCount = 0;
	var $repShowDet = 0;
	// report field info
	var $repGroupFields = array();
	// current table key fields
	var $tKeyFields = array();
	// if any field used for totals
	var $isExistTotalFields = false;
	// table fields list
	var $fieldsArr = array();
	// Order by fields info
	var $orderIndexes;
	// Instance of ProjectSettings
	var $pSet = null;
	
	
	function SQLStatement($sql, $order, $pagesize, $connection, &$searchClauseObj, &$params)
	{
		// copy properties to object
		RunnerApply($this, $params);	
		$this->searchClauseObj = $searchClauseObj;
		$this->pSet = new ProjectSettings($this->tName, PAGE_REPORT);
		if(!is_array($sql))
			die ('Invalid sql parameter');
			
		global $reportCaseSensitiveGroupFields;
			
		$this->_originalSql = $sql;
		$start = 0;
		
		$fields = array();
       
        for($i=0; $i<count($this->repGroupFields); $i++)
        {
	        for($j=0; $j<count($this->fieldsArr); $j++)
	        {
		        if ($this->repGroupFields[$i]['strGroupField'] == $this->fieldsArr[$j]['name'])
		        {
					$add = array();
					$add['name'] = $this->fieldsArr[$j]['name'];
					if (IsNumberType($this->pSet->getFieldType($this->fieldsArr[$j]['name'])))
					{
						$add['type'] = 'numeric';
					}elseif (IsCharType($this->pSet->getFieldType($this->fieldsArr[$j]['name']))){
						$add['type'] = 'char';
						$add['case_sensitive'] = $reportCaseSensitiveGroupFields;
					}elseif (IsDateFieldType($this->pSet->getFieldType($this->fieldsArr[$j]['name']))){
						$add['type'] = 'date';
					}else{
						$add['type'] = 'char';
					}
					$add['interval'] = $this->repGroupFields[$i]['groupInterval'];
					$add['viewformat'] = $this->fieldsArr[$j]['viewFormat'];
					
					$add['rowsinsummary'] = 1;
					
					
					if ($this->fieldsArr[$j]['totalMax'] || $this->fieldsArr[$j]['totalMin'] || $this->fieldsArr[$j]['totalAvg'] || $this->fieldsArr[$j]['totalSum'])
					{
						$add['rowsinsummary'] ++;
					}
					
					
					if ($this->repLayout == REPORT_STEPPED)
					{					
						$add['rowsinheader'] = 1;
					}elseif ($this->repLayout == REPORT_BLOCK){
						$add['rowsinheader'] = 0;
					}elseif ($this->repLayout == REPORT_OUTLINE || $this->repLayout == REPORT_ALIGN){
						if ($j == count($this->fieldsArr)-1)
						{
							$add['rowsinheader'] = 2;
						}else{
							$add['rowsinheader'] = 1;
						}
					}elseif ($this->repLayout == REPORT_TABULAR){
						$add['rowsinheader'] = 0;
					}
					
					$fields []= $add;
				}
			}
        }
			
		$this->_hasGroups = count($fields) > 0;

		foreach($fields as $field)
		{
			$f = create_reportfield($field['name'], $field['type'], $field['interval'], 'grp', $this->tName);
			$start = $f->setStart($start);
			if(isset($field['case_sensitive']))
				$f->setCaseSensitive($field['case_sensitive']);
			if(isset($field['rowsinsummary']))
				$f->_rowsInSummary = $field['rowsinsummary'];
			if(isset($field['rowsinheader']))
				$f->_rowsInHeader = $field['rowsinheader'];
			$f->_viewFormat = $field['viewformat'];
			$this->_fields []= $f;
		}
		
		// order
		if($order)
		{
			$order_in = array();
			$order_out = array();
			$order_old = array();
			
			foreach($order as $o)
			{
				$order_in []= $o[2] . ' as ' . cached_ffn('originalorder'.$o[0]);
				$order_out []= cached_ffn('originalorder'.$o[0]).' '.$o[1];
				$groupField = false;
				
				for($i=0; $i<count($this->repGroupFields); $i++)
	        	{
			        for($j=0; $j<count($this->fieldsArr); $j++)
			        {
				        if ($this->repGroupFields[$i]['strGroupField'] == $this->fieldsArr[$j]['name'])
				        {
							$fieldIndex = $this->pSet->GetFieldIndex($this->repGroupFields[$i]['strGroupField']);
							if($fieldIndex == $o[0])
							{
								$n = $this->repGroupFields[$i]['groupOrder']-1;
								$this->_fields[$n]->_orderBy = $o[1];
								$groupField = true;
							}
	        			}
	        		}
	        	}
//	don't add group fields to the $order_old
				if(!$groupField)
					$order_old []= $o[2].' '.$o[1];
			}
			$this->_order_in = join(', ', $order_in);
			$this->_order_out = join(', ', $order_out);
			$this->_order_old = join(', ', $order_old);
		}
		
        for($i=0; $i<count($this->fieldsArr); $i++)
        {	
			if ($this->fieldsArr[$i]['totalMax'])
			{
				$this->_aggregates []= 'MAX('.cached_ffn($this->fieldsArr[$i]['name'], true).') as '.cached_ffn($this->fieldsArr[$i]['name']."MAX");
            }
            if ($this->fieldsArr[$i]['totalMin']){		
				$this->_aggregates []= 'MIN('.cached_ffn($this->fieldsArr[$i]['name'], true).') as '.cached_ffn($this->fieldsArr[$i]['name']."MIN");
            }
            if ($this->fieldsArr[$i]['totalAvg']){	
	            if (!IsDateFieldType($this->pSet->getFieldType($this->fieldsArr[$i]['name'])))
	            {  
					$this->_aggregates []= 'AVG('.cached_ffn($this->fieldsArr[$i]['name'], true).') as '.cached_ffn($this->fieldsArr[$i]['name']."AVG");
					$this->_aggregates []= 'COUNT('.cached_ffn($this->fieldsArr[$i]['name'], true).') as '.cached_ffn($this->fieldsArr[$i]['name']."NAVG");
	            }
			}
			if ($this->fieldsArr[$i]['totalSum']){
				if (!IsDateFieldType($this->pSet->getFieldType($this->fieldsArr[$i]['name'])))
	            {
	          		$this->_aggregates []= 'SUM('.cached_ffn($this->fieldsArr[$i]['name'], true).') as '.cached_ffn($this->fieldsArr[$i]['name']."SUM");
	            }
			}
        }
        		
		
		$this->_reportSummary = $this->repPageSummary || $this->repGlobalSummary;
		$this->_pagesize = $pagesize;
	}
	
	function getOriginal($useOriginalOrder = true)
	{
		global $strTableName;
		$sql = $this->applyWhere($this->_originalSql);


		if(tableEventExists("BeforeQueryReport",$strTableName)) 
		{
			$hwhere = $sql[2];
			$eventObj = getEventObject($strTableName);
			$eventObj->BeforeQueryReport($hwhere);
			$sql[2] = $hwhere;
		}
		return $sql[0].' '.($useOriginalOrder && $this->_order_in && !$this->_oldAlgorithm ? ', ' . $this->_order_in . ' ' : '').$sql[1].' '.
			($sql[2] ? ' WHERE ' . $sql[2] : '').' '.$sql[3].' '.($sql[4] ? ' HAVING ' . $sql[4] : '');
	}
	
	function setRecordBasedRequest($recordBasedRequest)
	{
		$this->_recordBasedRequest = $recordBasedRequest;
		for($nCnt = 0; $nCnt < count($this->_fields); $nCnt++)
			$this->_fields[$nCnt]->_recordBasedRequest = $recordBasedRequest;
	}
	
	function getGroup($data)
	{
		return $this->_fields[0]->getGroup($data);
	}
	
	function field($num)
	{
		return $this->_fields[$num];
	}
	
	function getSQLLimits($sql, $from)
	{
		if($from >= 0 && $this->_pagesize >= 0)
		{
						return $out;
		}
		else
		{
			return $sql;
		}
	}
	
	function sqlg($donotlimit = false, $doorder = true)
	{
		$hsql = array();
		
		$s = array();
		$g = array();
		$o = array();
		
		if($this->_hasGroups)
		{
			$s []= $this->_fields[0]->getSelectSql(true);
			$g []= $this->_fields[0]->getGroupSql();
			$o []= $this->_fields[0]->getOrderSql();				
		}
		
		if(count($s))
			$hsql['select'] []= join(', ', $s);
		if(count($g))
			$hsql['groupby'] []= join(', ', $g);
		if(count($o) && $doorder)
			$hsql['orderby'] = join(', ', $o);
			
		if($this->_limitLevel == 1 && !$donotlimit)
			$hsql['limits'] = 1;
			
		return $this->buildsql($hsql);
	}
	
	function sqlcg()
	{
		$gsql = $this->sqlg(true, false);
		return 'select count(*) as '.cached_ffn("c").' from ('.$gsql.') countgroups';
	}
	
	function sqlt()
	{
		$hsql = array();
		$hsql['select'] []= 'count(1) as '.cached_ffn('countField');
		if(count($this->_aggregates))
			$hsql['select'] []= join(', ', $this->_aggregates);
		return $this->buildsql($hsql);
	}
	
	function sql2($groups = null)
	{
		$hsql = array();
			
		if(!$this->_hasGroups || $this->_recordBasedRequest)
		{
			$hsql['original'] = true;
			
			$o = array();
			foreach($this->_fields as $f)
				$o []= $f->getOrderSql();
			if(count($o))
				$hsql['orderby'] = join(', ', $o);
		}
		else
		{
			if($this->repShowDet)
			{
				$hsql['select'] []= 'original.*';
			}
			else
			{
				if(count($this->_aggregates))
					$hsql['select'] []= join(', ', $this->_aggregates);
			}
			
			$s = array();
			$g = array();
			$o = array();
			
			foreach($this->_fields as $f)
			{
				$s []= $f->getSelectSql(!$this->repShowDet);
				if(!$this->repShowDet)
					$g []= $f->getGroupSql();
				$o []= $f->getOrderSql();
			}
			
			if($this->_reportSummary && $this->_hasGroups && !$this->repShowDet)
			{
				$hsql['select'] []= 'count(1) as '.cached_ffn('countField');
			}
			
			if(count($s))
			{
				$hsql['select'] []= join(', ', $s);
			}
			
			if($groups !== null && count($groups))
			{
				$where = $this->_fields[0]->getWhereSql($groups);
				if($where)
					$hsql['where'] = $where;
			}
			
			if(count($g))
			{
				$hsql['groupby'] = $g;
			}
			
			if(count($o))
			{
				$hsql['orderby'] = join(', ', $o);
			}
		}
		
		if($this->_limitLevel == 2)
		{
			$hsql['limits'] = 1;
		}
			
		if($this->repShowDet)
		{
			$hsql['origorder'] = 1;
		}
		
//		return $this->buildsql($hsql);
		return $hsql;
	}
	
	function buildsql($hsql)
	{
		$this->_skipCount = 0;
		$ordered = false;
		
		if(count($hsql) == 0 || $hsql['original'])
		{
			$sql = $this->getOriginal();
		}
		else
		{
			$sql = 'SELECT ';
			if($hsql['select'] && count($hsql['select']) > 0)
				$sql .= join(', ', $hsql['select']);
			else
				$sql .= ' * ';
			$sql .= ' FROM ('.$this->getOriginal($hsql['origorder']).') original';
			if($hsql['where'] && count($hsql['where']) > 0)
				$sql .= ' WHERE '.$hsql['where'];
			if($hsql['groupby'] && count($hsql['groupby']) > 0)
				$sql .= ' GROUP BY '.join(', ', $hsql['groupby']);
		}
		
		$osql = '';
		if($hsql['orderby'] && count($hsql['orderby']) > 0)
		{
			$osql .= $hsql['orderby'];
			$ordered = true;
		}
		if($hsql['origorder'])
		{
			if(!$this->_oldAlgorithm)
			{
				if($this->_order_out)
				{
					$osql .= ($osql ? ', ' : '').$this->_order_out;
				}
			}
			else
			{
				if($this->_order_old)
				{
					$osql .= ($osql ? ', ' : '').$this->_order_old;
				}
			}
		}
		if($osql)
		{
			$sql .= ' ORDER BY '.$osql;
		}
		
		if($hsql['limits'])
			$sql = $this->getSQLLimits($sql, $this->_from);
			
		return $sql;
	}
	
	function initWhere()
	{
		//$this->searchClauseObj->parseRequest();
	}
	
	function getWhere()
	{
		return $this->searchClauseObj;
	}	
	
	function applyWhere(&$sql)
	{
		global $gQuery;
		include_once getabspath('classes/controls/EditControlsContainer.php');
		$editControls = new EditControlsContainer(null, $this->pSet, PAGE_REPORT, new RunnerCipherer($this->tName, $this->pSet));
		return $this->searchClauseObj->applyWhere($sql, $this->pSet->getListOfFieldsByExprType(false), 
			$this->pSet->getListOfFieldsByExprType(true), $editControls);
	}
	
	function setOldAlgorithm($useOldAlgorithm = true)
	{
		for($nCnt = 0; $nCnt < count($this->_fields); $nCnt ++)
		{
			$this->_fields[$nCnt]->_oldAlgorithm = $useOldAlgorithm;
		}
		
		$this->_oldAlgorithm = $useOldAlgorithm;
	}
}

////////////////////////////////////////////////////////////////////////////////////////
class Summarable
{
	var $_summary = array();
	
	// report table info
	var $tName = '';
	var $shortTName = '';	
	var $repGroupFieldsCount = 0;
	var $repPageSummary = 0;
	var $repGlobalSummary = 0;
	var $repLayout = 0;
	var $showGroupSummaryCount = 0;
	var $repShowDet = 0;
	// report field info
	var $repGroupFields = array();
	// current table key fields
	var $tKeyFields = array();
	// if any field used for totals
	var $isExistTotalFields = false;
	// table fields list
	var $fieldsArr = array();
	
	var $cipherer = null;
	
    function Summarable(&$params)
    {
    	RunnerApply($this, $params);
		Summarable::init();
	}
	
	function init($from = 0)
	{
		$this->_from = $from;
		$this->cipherer = new RunnerCipherer($this->tName);
	}
	
	function writeGroup(&$begin, &$end, $gkey, $grp, $nField) {}
	
	function addSummary($recordsMode, &$summary, $data, &$nTotalRecords)
	{
		$countInGroup = isset($summary['count']) ? $summary['count'] : 0;
		if ($this->isExistTotalFields)
		{
			if(!is_array($summary['summary']))
				$summary['summary']=array();
			$s =& $summary['summary'];
		}
		if($recordsMode)
		{
			for($i=0; $i<count($this->fieldsArr); $i++)
			{
		   		if (!$this->fieldsArr[$i]['totalMax']&&!$this->fieldsArr[$i]['totalMin']&&!$this->fieldsArr[$i]['totalAvg']&&!$this->fieldsArr[$i]['totalSum'])
		   		{
		   			continue;
		   		}
				if($data[$this->fieldsArr[$i]['name']] !== null)
				{
					if(!is_array($s[$this->fieldsArr[$i]['name']]))
						$s[$this->fieldsArr[$i]['name']] = array();
													
						
					if ($this->fieldsArr[$i]['totalMax'])
					{
				        if(!isset($s[$this->fieldsArr[$i]['name']]['MAX']) || $s[$this->fieldsArr[$i]['name']]['MAX'] < $data[$this->fieldsArr[$i]['name']])
							$s[$this->fieldsArr[$i]['name']]['MAX'] = $data[$this->fieldsArr[$i]['name']];
			        }
			        if ($this->fieldsArr[$i]['totalMin']){	
				        if(!isset($s[$this->fieldsArr[$i]['name']]['MIN']) || $s[$this->fieldsArr[$i]['name']]['MIN'] > $data[$this->fieldsArr[$i]['name']])
							$s[$this->fieldsArr[$i]['name']]['MIN'] = $data[$this->fieldsArr[$i]['name']];
			       	}
			       	if ($this->fieldsArr[$i]['totalAvg']){	
						if ($this->fieldsArr[$i]['viewFormat'] == "Time")
						{
							$avg_value = $this->value2time($data[$this->fieldsArr[$i]['name']]);
						}else{
							$avg_value = $data[$this->fieldsArr[$i]['name']];
						}
						$s[$this->fieldsArr[$i]['name']]['AVG'] = $s[$this->fieldsArr[$i]['name']]['AVG']*$s[$this->fieldsArr[$i]['name']]['count'] + $avg_value;
				        $s[$this->fieldsArr[$i]['name']]['count'] ++;
						if($s[$this->fieldsArr[$i]['name']]['count']!=0)
							$s[$this->fieldsArr[$i]['name']]['AVG'] = $s[$this->fieldsArr[$i]['name']]['AVG']/$s[$this->fieldsArr[$i]['name']]['count'];
					}
					if ($this->fieldsArr[$i]['totalSum']){
						if ($this->fieldsArr[$i]['viewFormat'] == "Time")
						{						
			       			$s[$this->fieldsArr[$i]['name']]['SUM'] += $this->value2time($data[$this->fieldsArr[$i]['name']]);
						}else{
			        		$s[$this->fieldsArr[$i]['name']]['SUM'] += $data[$this->fieldsArr[$i]['name']];
						}
					}
				}
			}
		    $nTotalRecords ++;
			$countInGroup ++;
		}
		else
		{
			for($i=0; $i<count($this->fieldsArr); $i++)
			{
		   		if (!$this->fieldsArr[$i]['totalMax']&&!$this->fieldsArr[$i]['totalMin']&&!$this->fieldsArr[$i]['totalAvg']&&!$this->fieldsArr[$i]['totalSum'])
		   		{
		   			continue;
		   		}
				if(!is_array($s[$this->fieldsArr[$i]['name']]))
					$s[$this->fieldsArr[$i]['name']] = array();	
								
					
				if ($this->fieldsArr[$i]['totalMax'])
				{
			        if($data[$this->fieldsArr[$i]['name']."MAX"] !== null)
					{
						if(!isset($s[$this->fieldsArr[$i]['name']]['MAX']) || $s[$this->fieldsArr[$i]['name']]['MAX'] < $data[$this->fieldsArr[$i]['name']."MAX"])
							$s[$this->fieldsArr[$i]['name']]['MAX'] = $data[$this->fieldsArr[$i]['name']."MAX"];
					}
		        }
		        if ($this->fieldsArr[$i]['totalMin']){	
			        if($data[$this->fieldsArr[$i]['name']."MIN"] !== null)
					{
						if(!isset($s[$this->fieldsArr[$i]['name']]['MIN']) || $s[$this->fieldsArr[$i]['name']]['MIN'] > $data[$this->fieldsArr[$i]['name']."MIN"])
							$s[$this->fieldsArr[$i]['name']]['MIN'] = $data[$this->fieldsArr[$i]['name']."MIN"];
					}
				}
				if ($this->fieldsArr[$i]['totalAvg']){
					if($data[$this->fieldsArr[$i]['name']."AVG"] !== null)
					{
						if ($this->fieldsArr[$i]['viewFormat'] == "Time")
						{
							$avg_value = $this->value2time($data[$this->fieldsArr[$i]['name']."AVG"]);
						}else{
							$avg_value = $data[$this->fieldsArr[$i]['name']."AVG"];
						}
						$s[$this->fieldsArr[$i]['name']]['AVG'] = $s[$this->fieldsArr[$i]['name']]['AVG']*$s[$this->fieldsArr[$i]['name']]['count'] + $avg_value*$data[$this->fieldsArr[$i]['name']."NAVG"];
						$s[$this->fieldsArr[$i]['name']]['count'] += $data[$this->fieldsArr[$i]['name']."NAVG"];
						if($s[$this->fieldsArr[$i]['name']]['count']!=0)
							$s[$this->fieldsArr[$i]['name']]['AVG'] = $s[$this->fieldsArr[$i]['name']]['AVG']/$s[$this->fieldsArr[$i]['name']]['count'];
					}
				}
				if ($this->fieldsArr[$i]['totalSum']){
			        if($data[$this->fieldsArr[$i]['name']."SUM"] !== null)
					{
						if ($this->fieldsArr[$i]['viewFormat'] == "Time")
						{
				       		$s[$this->fieldsArr[$i]['name']]['SUM'] += $this->value2time($data[$this->fieldsArr[$i]['name']."SUM"]);
			       		}else{
				        	$s[$this->fieldsArr[$i]['name']]['SUM'] += $data[$this->fieldsArr[$i]['name']."SUM"];
			       		}
					}
				}
			}
	        $nTotalRecords += $data['countField'];
			$countInGroup += $data['countField'];
		}
        $summary['count'] = $countInGroup;
	}
	
	function _makeSummary(&$summary, $deep)
	{
		if($summary['values'])
		{
	        foreach($summary['values'] as $gkey => $group)
	        {
				$grp =& $summary['values'][$gkey];
	            if(isset($grp['values']))
	                $this->_makeSummary($grp, $deep + 1);
					
				if(isset($grp['_begin']) && isset($grp['_end']))
					$this->writeGroup($grp['_begin'], $grp['_end'], $gkey, $grp, $deep);
					
				if(!is_array($summary['summary']))
					$summary['summary']=array();
	            for($i=0; $i<count($this->fieldsArr); $i++)
	            {
					if(!is_array($summary['summary'][$this->fieldsArr[$i]['name']]))
						$summary['summary'][$this->fieldsArr[$i]['name']]=array();
	                if(is_array($grp['summary']))
					{
						if(is_array($grp['summary'][$this->fieldsArr[$i]['name']]))
						{
							if ($this->fieldsArr[$i]['totalMax'])
							{
								if(isset($grp['summary'][$this->fieldsArr[$i]['name']]['MAX']))
								{
									if(!isset($summary['summary'][$this->fieldsArr[$i]['name']]['MAX']) || $summary['summary'][$this->fieldsArr[$i]['name']]['MAX'] < $grp['summary'][$this->fieldsArr[$i]['name']]['MAX'])
										$summary['summary'][$this->fieldsArr[$i]['name']]['MAX'] = $grp['summary'][$this->fieldsArr[$i]['name']]['MAX'];
								}
			                }
			                if ($this->fieldsArr[$i]['totalMin']){	
			                	if(isset($grp['summary'][$this->fieldsArr[$i]['name']]['MIN']))
								{
									if(!isset($summary['summary'][$this->fieldsArr[$i]['name']]['MIN']) || $summary['summary'][$this->fieldsArr[$i]['name']]['MIN'] > $grp['summary'][$this->fieldsArr[$i]['name']]['MIN'])
										$summary['summary'][$this->fieldsArr[$i]['name']]['MIN'] = $grp['summary'][$this->fieldsArr[$i]['name']]['MIN'];
								}
							}
							if ($this->fieldsArr[$i]['totalAvg']){
			    	            if(isset($grp['summary'][$this->fieldsArr[$i]['name']]['AVG']))
								{
				        	        $summary['summary'][$this->fieldsArr[$i]['name']]['AVG'] =
										$summary['summary'][$this->fieldsArr[$i]['name']]['AVG']*$summary['summary'][$this->fieldsArr[$i]['name']]['count'] +
										$grp['summary'][$this->fieldsArr[$i]['name']]['AVG']*$grp['summary'][$this->fieldsArr[$i]['name']]['count'];
									$summary['summary'][$this->fieldsArr[$i]['name']]['count'] += $grp['summary'][$this->fieldsArr[$i]['name']]['count'];
									if($summary['summary'][$this->fieldsArr[$i]['name']]['count']!=0)
										$summary['summary'][$this->fieldsArr[$i]['name']]['AVG'] = $summary['summary'][$this->fieldsArr[$i]['name']]['AVG']/$summary['summary'][$this->fieldsArr[$i]['name']]['count'];
								}
							}if ($this->fieldsArr[$i]['totalSum']){
			        	        if($grp['summary'][$this->fieldsArr[$i]['name']]['SUM'])
									$summary['summary'][$this->fieldsArr[$i]['name']]['SUM'] += $grp['summary'][$this->fieldsArr[$i]['name']]['SUM'];
							}
						}
					}
	            }
		        
		        $summary['count'] += $grp['count'];
		    }
		}
	}
	
	function value2time($value)
	{
		$res = 0;
		$arr = parsenumbers($value);
		if(isset($arr[0]))
			$res += $arr[0] * 60 * 60;
		if(isset($arr[1]))
			$res += $arr[1] * 60;
		if(isset($arr[2]))
			$res += $arr[2];
		return $res;
	}
	
	function time2printable($time)
	{
		return array(intval($time / (60 * 60)), intval($time / 60), $time % 60);
	}
}

class ReportGroups extends Summarable
{
	var $_global;
	var $_pagesize;
	var $_totalRecords;
	var $_maxpages;
	var $_nGroup;
	var	$_oldFirst;
	var $_from;
	var $_sql;
	var $_connection;
	var $_allGroupsUsed;
	var $_countGroups;
	
	// report table info
	var $tName = '';
	var $shortTName = '';	
	var $repGroupFieldsCount = 0;
	var $repPageSummary = 0;
	var $repGlobalSummary = 0;
	var $repLayout = 0;
	var $showGroupSummaryCount = 0;
	var $repShowDet = 0;
	// report field info
	var $repGroupFields = array();
	// current table key fields
	var $tKeyFields = array();
	// if any field used for totals
	var $isExistTotalFields = false;
	// table fields list
	var $fieldsArr = array();
	
	function ReportGroups(&$sql, $connection, $pagesize, &$params)
	{
		// copy properties to object
		RunnerApply($this, $params);
		Summarable::Summarable($params);
		$this->init();
		$this->_pagesize = $pagesize;
		$this->_sql =& $sql;
		$this->_connection = $connection;
	}
	
	function init($from = 0)
	{		
		Summarable::init($from);
		$this->_global = array();
		$this->_totalRecords = 0;
		$this->_maxpages = -1;
		$this->_from = $from;
		$this->_nGroup = -1;
		$this->_oldFirst = '';
		$this->_allGroupsUsed = false;
		$this->_countGroups = 0;
	}
	
	function setGlobalSummary($recordsMode, $data)
	{
		$this->addSummary($recordsMode, $this->_global, $data, $this->_totalRecords);
	}
	
	function setGroup($data)
	{
		$field = $this->_sql->field(0);
		$firstKey = $field->getKey($data);
		if($firstKey != $this->_oldFirst)
		{
			$this->_nGroup ++;
			$this->_oldFirst = $firstKey;
		}
	}
	
	function isVisibleGroup()
	{
		return $this->_nGroup >= $this->_from && $this->_nGroup < $this->_from + $this->_pagesize;
	}

	function getDisplayGroups($from)
	{
		$this->init($from);
		
		if($this->_pagesize == -1)
		{
			// request 'All Groups'
			return array();
		}
		else
		{
			$groups = array();
			$this->_allGroupsUsed = false;
		
			if ($this->repGroupFieldsCount)
			{
				$sql = $this->_sql->sqlg();
		        $cursor = db_query($sql, $this->_connection);
		        while($data = $this->cipherer->DecryptFetchedArray($cursor))
				{
					$groups[] = $this->_sql->getGroup($data);
				}
				if(count($groups) < $this->_pagesize)
					$this->_allGroupsUsed = true;
			}
			
			if($this->_sql->_skipCount > 0)
			{
				array_splice($groups, 0, $this->_sql->_skipCount);
				$this->_allGroupsUsed = false;
			}
			
			if($from > 0)
			{
				$this->_allGroupsUsed = false;
			}
		
			$this->_countGroups = count($groups);
	
			return $groups;
		}
	}
	
	function getCountGroups($fullRequest = false)
	{
		if ($this->repGroupFieldsCount)
		{
			if($this->_nGroup >= 0 && $fullRequest)
			{
				return $this->_nGroup + 1;
			}
			else
			{
				if($this->_allGroupsUsed)
				{
					return $this->_countGroups;
				}
				else
				{
					$sql = $this->_sql->sqlcg();
			        $cursor = db_query($sql, $this->_connection);
			        $data = $this->cipherer->DecryptFetchedArray($cursor);
					return $data['c'];
				}
			}
		}else{
			return 0;
		}
	}
	
	function getSummary()
	{
		return $this->_global;
	}
	
	function allGroupsUsed()
	{
		return $this->_allGroupsUsed;
	}
}

class ReportLogic extends Summarable
{
	var $_list;
	var $_totalRecords;
	var $_pages;
	var $_pagesize;
	var $_printpagesize;
	var $_from = 0;
    var $_connection;
	var	$_sql;
	var $_groups;
	var $_groupKeys;
	
	var $_fullRequest = false;
	var $_recordBasedRequest = false;
	var $_doPaging = false;
	var $_lastPageNumber = 0;
	var $_pageSummary;
	var $_printRecordCount = 0;
	var $_listedRows = 0;
	var $_oldLevels;
	
	
	// report table info
	var $tName = '';
	var $shortTName = '';	
	var $repGroupFieldsCount = 0;
	var $repPageSummary = 0;
	var $repGlobalSummary = 0;
	var $repLayout = 0;
	var $showGroupSummaryCount = 0;
	var $repShowDet = 0;
	// report field info
	var $repGroupFields = array();
	// current table key fields
	var $tKeyFields = array();
	// if any field used for totals
	var $isExistTotalFields = false;
	// table fields list
	var $fieldsArr = array();
	
	var $searchClauseObj = null;
	
	var $cipherer = null;
		
	/**
	 * Instance of RunnerPage or ViewControlsContainer
	 * @var {object}
	 */
	var $pageObject = null;
	var $pSet = null;
	
    function ReportLogic($sql, $order, &$searchClauseObj, $connection, $pagesize, $printpagesize, &$params, $pageObject = null)
    {
    	RunnerApply($this, $params);
		Summarable::Summarable($params);
        $this->_connection = $connection;
        $this->searchClauseObj = $searchClauseObj;
		$this->_sql = new SQLStatement($sql, $order, $pagesize, $connection, $searchClauseObj, $params);
		$this->_groups = new ReportGroups($this->_sql, $connection, $pagesize, $params);
		$this->_pagesize = $pagesize;
		$this->_printpagesize = $printpagesize < 0 ? $pagesize : $printpagesize;
		if($this->searchClauseObj->isUsedSrch())
			$this->_sql->initWhere();
		$this->pSet = new ProjectSettings($this->tName, PAGE_REPORT);
		$this->cipherer = new RunnerCipherer($this->tName);

		if(is_null($pageObject))
		{
			include_once getabspath('classes/controls/ViewControlsContainer.php');
			$this->pageObject = new ViewControlsContainer($this->pSet, PAGE_REPORT);
		}
		else 
			$this->pageObject = $pageObject;
			
		$this->init();
	}
	
	function init($from = 0)
	{
		Summarable::init($from);
		$this->_sql->_from = $from;
		$this->_list = array();
		$this->_totalRecords = 0;
		$this->_pages = array();
		$this->_groupKeys = array();
		$this->_lastPageNumber = 0;
		$this->_pageSummary = array();
		$this->_printRecordCount = 0;
		$this->_listedRows = 0;
		$this->_oldLevels = array();
		
		$this->cipherer = new RunnerCipherer($this->tName);
	}
	
	//// accessors
	function getWhere()
	{
		return $this->_sql->getWhere();
	}
		
	function getPages()
	{
		return $this->_pages;
	}
	
	/// logic
	function getFormattedRow($value) {}
	function writeGroup(&$begin, &$end, $gkey, $grp, $nField) {}
	function _writePage(&$page, $src, $count) {}
	function writeGlobalSummary($source) {}

	function writePageSummary()
	{
		if($this->_doPaging)
		{
			for($nCnt = 0; $nCnt < count($this->_list); $nCnt++)
			{
				if(!isset($this->_pages[$nCnt]))
					$this->_pages[$nCnt]=array();
				$result =& $this->_pages[$nCnt];
				if(isset($this->_pageSummary[$nCnt]))
				{
					$page = $this->_pageSummary[$nCnt];
					$this->_writePage($result,
							  isset($page['summary']) ? $page['summary'] : array(),
							  isset($page['count']) ? $page['count'] : 0);
				}
				else
					$this->_writePage($result, array(), 0);
			}
		}
		else
		{
			$result = array();
			$page = $this->_summary;
			$this->_writePage($result,
							  isset($page['summary']) ? $page['summary'] : array(),
							  isset($page['count']) ? $page['count'] : 0);
			$this->_summary = $result;
		}
		
		if(0 == count($this->_pages) && count($this->_list) > 0)
			$this->_pages []= $this->_summary;
	}
	
	function makeSummary()
	{
		$this->_makeSummary($this->_summary, 0);
	}
	
	function setSummary($recordsMode, $data, $rowToAppend = null)
	{
		$level =& $this->_summary;
		
		$setBegin = false;
		if ($this->repGroupFieldsCount)
		{
			// get array of keys for current record
			$recordkeys = array();
			for($i=0;$i<count($this->repGroupFields); $i++)
      		{
				$field = $this->_sql->field($this->repGroupFields[$i]['groupOrder'] - 1);
				$recordkeys[$this->repGroupFields[$i]['groupOrder'] - 1] = $field->getKey($data);
			}
				
			// add empty record for each closed group to hold its summary
			if(count($this->_groupKeys) > 0)
			{
				$changed = false;
				$nKey = 0;
				for(; $nKey < count($recordkeys); $nKey ++)
				{
					if($recordkeys[$nKey] != $this->_groupKeys[$nKey])
					{
						$changed = true;
						break;
					}
				}
				if($changed)
				{
					// in reverse order
					for($nKey2 = count($recordkeys) - 1; $nKey2 >= $nKey; $nKey2 --)
					{
						$emptyRow =& $this->appendRow(array());
						$field = $this->_sql->field($nKey2);
						$this->_printRecordCount += $field->_rowsInSummary;
						$this->_listedRows ++;
						$this->_oldLevels[$nKey2]['_end'] =& $emptyRow;
					}
				}
			}
				
			// store new record keys
			$this->_groupKeys = $recordkeys;
				
			// find current summary
			$levels = array();
			for($i=0;$i<count($this->repGroupFields); $i++)
      		{
				if(!isset($level['values'][$recordkeys[$this->repGroupFields[$i]['groupOrder'] - 1]]))
				{
					$level['values'][$recordkeys[$this->repGroupFields[$i]['groupOrder'] - 1]] = array();
					$level =& $level['values'][$recordkeys[$this->repGroupFields[$i]['groupOrder'] - 1]];
					$field = $this->_sql->field($this->repGroupFields[$i]['groupOrder'] - 1);
					$this->_printRecordCount +=  $field->_rowsInHeader;
					$setBegin = true;
					$level['_first'] = $data;
				}
				else
				{
					$level =& $level['values'][$recordkeys[$this->repGroupFields[$i]['groupOrder'] - 1]];
				}
				$levels[] =& $level;
			}
			
			// and finally calculate summary
			$this->addSummary($recordsMode, $level, $data, $this->_totalRecords);
			$this->_oldLevels =& $levels;
		}else{
			$this->addSummary($recordsMode, $level, $data, $this->_totalRecords);
		}
		
		if($rowToAppend)
		{
			$added =& $this->appendRow($rowToAppend);
			$this->_printRecordCount ++;
			$this->_listedRows ++;
			
			if($setBegin && $this->repGroupFieldsCount)
			{
				for($nCnt = 0; $nCnt < count($levels); $nCnt ++)
					if(!isset($levels[$nCnt]['_begin']))
						$levels[$nCnt]['_begin'] =& $added;
			}
		}
		
		if ($this->repPageSummary)
		{
			if($this->_doPaging && $rowToAppend)
			{
				$nPage = count($this->_list) - 1;
				if(!isset($this->_pageSummary[$nPage]))
					$this->_pageSummary[$nPage]["count"]=0;
				$this->addSummary($recordsMode, $this->_pageSummary[$nPage], $data, $this->_pageSummary[$nPage]['count']);
			}
		}
	}
	
	function setFinish()
	{
		if(count($this->_groupKeys) > 0)
		{
			for($nKey = count($this->_groupKeys) - 1; $nKey >= 0; $nKey --)
			{
				$field = $this->_sql->field($nKey);
				$this->_printRecordCount += $field->_rowsInSummary;
				$emptyRow =& $this->appendRow(array());
				$this->_listedRows ++;
				$this->_oldLevels[$nKey]['_end'] =& $emptyRow;
			}
		}
	}
	
	function & appendRow($row)
	{
		if($this->_doPaging)
		{
			$page = intval($this->_printRecordCount / $this->_printpagesize);
			// check we have no gaps in page list
			if($page > 0 && !isset($this->_list[$page - 1]))
				die("Increase number of records per print page to display report properly");
			$this->_list[$page][] = $row;
			return $this->_list[$page][count($this->_list[$page]) - 1];
		}
		else
		{
			$this->_list[] = $row;
			return $this->_list[count($this->_list) - 1];
		}
	}
	
	function recordVisible($nRecord)
	{
		return
			$this->_doPaging ||  // all records are printed, so there is no invisible records
			$this->_sql->_limitLevel == 1 || // only visible records were selected due to group filtering
			$this->_pagesize == -1 || // 'show all' mode
			($this->_sql->_limitLevel == 2 && // DB specific record filter
				($nRecord - $this->_sql->_skipCount >= 0 && $nRecord - $this->_sql->_skipCount < $this->_pagesize)) ||
			($this->_sql->_limitLevel == 0 && // fullmode record filter
				($nRecord - $this->_from >= 0 && $nRecord - $this->_sql->_skipCount < $this->_from + $this->_pagesize))
			;
	}
	
	function getTotals()
	{
		if($this->_fullRequest)
		{
			return $this->_groups->getSummary();
		}
		else
		{
			if($this->_groups->allGroupsUsed())
			{
				return $this->_summary;
			}
			else
			{
				$totals = array();
				$sql = $this->_sql->sqlt();
				if($sql !== false)
				{
					$totalRecords = 0;
			        $cursor = db_query($sql, $this->_connection);
					$data = $this->cipherer->DecryptFetchedArray($cursor);
					$this->addSummary(false, $totals, $data, $totalRecords);	
				}
				return $totals;
			}
		}
	}
	
	function getReport($from = 0)
	{
		global $bSubqueriesSupported;
		
		$this->init($from);
		
		// include all records in report with pagination
		$this->_doPaging = $from == -1;
		
		$isExistTimeFormatField = false;
		for($i=0;$i<count($this->fieldsArr); $i++)
      	{
      		if ($this->fieldsArr[$i]['viewFormat'] == "Time")
      		{
      			$isExistTimeFormatField = true;
      			break;	
      		}	
      	}
		
		// retrieve ALL records from table
		$this->_fullRequest = $this->_doPaging || ($this->repGlobalSummary && $isExistTimeFormatField);
		
		// MYSQL version < 5.0
		// a very bad thing to do, but we need this for global summary and pagination
		if(!$bSubqueriesSupported)
			$this->_fullRequest = true;
		
			$this->_fullRequest = true;
		
		// use non-optimized algorithm
		$this->_recordBasedRequest = $this->_fullRequest; 
		
		// request records if there is no grouping
		if(!$this->repGroupFieldsCount)
			$this->_recordBasedRequest=true;
		
		//////////////////////////////// start building report
		$this->_sql->setRecordBasedRequest($this->_recordBasedRequest);
		if($this->_doPaging || $this->_fullRequest)
		{
			$this->_sql->_limitLevel = 0; // no limits
		}
		else
		{
			if(!$this->repGroupFieldsCount)
				$this->_sql->_limitLevel = 2; // limit records
			else
				$this->_sql->_limitLevel = 1; // limit groups
		}
		
		$page = -1;
		$nRow = 0;
		if(!$this->_recordBasedRequest)
		{
			// get groups to show
			$groups = $this->_groups->getDisplayGroups($from);
			
			// iterate through records in these groups
			$hsql = $this->_sql->sql2($groups);
			if(tableEventExists('BeforeQueryReport',$this->tName))
			{
				$hwhere = $hsql['where'];
				$eventsObj = getEventObject($this->tName);
				$eventsObj->BeforeQueryReport($hwhere);
				$hsql['where'] = $hwhere;
			} 
			$sql=$this->_sql->buildsql($hsql);
		    $cursor = db_query($sql, $this->_connection);
		    while($data = $this->cipherer->DecryptFetchedArray($cursor))
		    {
		    	$this->pageObject->recId = $nRow;
				$this->setSummary($this->repShowDet, $data,
					$this->recordVisible($nRow) ? $this->getFormattedRow($data) : null);
				$nRow ++;
		    }
		}
		else
		{
			$this->_groups->init($from);
			$this->_sql->setOldAlgorithm();
			
			$hsql = $this->_sql->sql2(null);
			$sql=$this->_sql->buildsql($hsql);
			
	        $cursor = db_query($sql, $this->_connection);
	        while($data = $this->cipherer->DecryptFetchedArray($cursor))
			{
				if ($this->repGroupFieldsCount)
				{
					// take a record group into account
					$this->_groups->setGroup($data);
				}
				
				if($this->_fullRequest)
					$this->_groups->setGlobalSummary(true, $data);
				
				if ($this->repGroupFieldsCount)
				{
					$visible = $this->_doPaging || $this->_groups->isVisibleGroup() || $this->_pagesize==-1;
				}else{
					$visible = $this->recordVisible($nRow);
				}
				
				if($visible)
				{
					$this->pageObject->recId = $nRow;
					$this->setSummary(true, $data, $this->getFormattedRow($data));
				}
				else if(!$this->_fullRequest && count($this->_list) > 0)
				{
					// exit loop at the end of visible recordset
					break;
				}
				
				$nRow ++;
			}
			
			$this->_sql->setOldAlgorithm(false);
		}
		$this->setFinish();
		
		$this->makeSummary();
		$global_totals = $this->getTotals();
		
		$this->writePageSummary();
		$globals = $this->writeGlobalSummary($global_totals);
		if ($this->repGroupFieldsCount)
		{			
			$countrows = $this->_groups->getCountGroups($this->_fullRequest);
			$countGroups = $countrows;
		}else{
			$countrows = $global_totals['count'];
			$countGroups = 1;
		}

		$maxpages=1;
		if($this->_pagesize>0)
			$maxpages=ceil($countrows/$this->_pagesize);
		$returnthis = array('list' => $this->_list, 'global' => $globals, 'page' => $this->_summary,
					'maxpages' => $maxpages, 'countRows'=>$countrows, 'countGroups'=>$countGroups);
		return $returnthis;
	}
}

//////////////////////////////////////////////////////////////////////////////
class Report extends ReportLogic
{
	var $forExport = false;
	// report table info
	var $tName = '';
	var $shortTName = '';	
	var $repGroupFieldsCount = 0;
	var $repPageSummary = 0;
	var $repGlobalSummary = 0;
	var $repLayout = 0;
	var $showGroupSummaryCount = 0;
	var $repShowDet = 0;
	var $mode = MODE_LIST;
	// report field info
	var $repGroupFields = array();
	// current table key fields
	var $tKeyFields = array();
	// if any field used for totals
	var $isExistTotalFields = false;
	// table fields list
	var $fieldsArr = array();
	
    function Report($sql, $order, &$searchClauseObj, $connection, $pagesize, $printpagesize, &$params, $pageObject = null)
    {
    	// copy properties to object
		RunnerApply($this, $params);
		ReportLogic::ReportLogic($sql, $order, $searchClauseObj, $connection, $pagesize, $printpagesize, $params, $pageObject);
    }
	
	function getFormattedRow($value)
	{
		global $strTableName;
		$row =  array('row_data' => true);
			
		$keylink = "";
		for($i=0; $i<count($this->tKeyFields); $i++)
		{
			$keylink .= "&key".($i+1)."=".htmlspecialchars(rawurlencode(@$value[$this->tKeyFields[$i]]));
		}

		if($this->forExport)
			$this->pageObject->setForExportVar("export");
		for($i=0; $i<count($this->fieldsArr); $i++)
		{		
			// for change pseudo foreach with condition with PHP for
			// foreach Fields as @f filter @f.bReportPage && (@TABLE.bReportShowDetails || @TABLE.arrReportGroupFields[strGroupField==@f.strName && nGroupInterval==0].len) order nReportPageOrder
			
			$pass = false;
			
			for($j=0; $j<count($this->repGroupFields); $j++)
			{
				if (!$this->fieldsArr[$i]['repPage'] || !($this->repShowDet 
					|| ($this->repGroupFields[$j]['strGroupField'] == $this->fieldsArr[$i]['name'] 
					&& $this->repGroupFields[$j]['groupInterval'] === 0)))
				{
					$pass = true;
				}
			}
			
			if ($pass)
			{
				continue;
			}
			$row[$this->fieldsArr[$i]['goodName']."_value"] = $this->pageObject->showDBValue($this->fieldsArr[$i]['name'], $value, $keylink);
		}
			
		if ($this->repLayout == REPORT_BLOCK)
		{
			$row[GoodFieldName('nonewgroup')] = true;
		}
			
		return $row;
	}
	
	function writeGroup(&$begin, &$end, $gkey, $grp, $nField)
	{
		$field = $this->_sql->field($nField);
        $gname = $field->name();
		
        for($i=0;$i<count($this->repGroupFields); $i++)
        {			
			if($gname == $this->repGroupFields[$i]['strGroupField'])
			{
				if ($this->repLayout == REPORT_BLOCK)
				{
					
					$bFound = false;
					for($nG = 0; $nG < $this->repGroupFieldsCount; $nG ++)
					{
						$field = $this->_sql->field($nG);
						$gname2 = $field->name();
						if($nG < $nField)
						{
							if(isset($begin[GoodFieldName($gname2.'_firstnewgroup')]))
								$bFound = true;
						}
						else
							unset($begin[GoodFieldName($gname2.'_firstnewgroup')]);
					}
					if(!$bFound)
						$begin[GoodFieldName($gname.'_firstnewgroup')] = true;
					unset($begin[GoodFieldName('nonewgroup')]);
				}
				else 
				{
					$begin[GoodFieldName($gname.'_newgroup')] = true;
				}
				$end[GoodFieldName($gname.'_endgroup')] = true;
				
				if ($this->repGroupFields[$i]['showGroupSummary'])
				{
					$end[GoodFieldName('group'.$gname.'_total_cnt')] = $grp['count'];
				}
				
				for($j=0; $j<count($this->fieldsArr); $j++)
				{
					if(is_array($grp['summary']))
					{
						if(is_array($grp['summary'][$this->fieldsArr[$j]['name']]))
						{
							if ($this->fieldsArr[$j]['totalMax'])
							{
								$end["group".GoodFieldName($gname)."_total".$this->fieldsArr[$j]['goodName']."_max"] =
									getFormattedValue($this->pageObject, $grp['summary'][$this->fieldsArr[$j]['name']]['MAX'], $this->fieldsArr[$j]['name'], $this->fieldsArr[$j]['viewFormat'], $this->fieldsArr[$j]['editFormat'], $this->mode);
							}
							if ($this->fieldsArr[$j]['totalMin']){							
								$end["group".GoodFieldName($gname)."_total".$this->fieldsArr[$j]['goodName']."_min"] =
									getFormattedValue($this->pageObject, $grp['summary'][$this->fieldsArr[$j]['name']]['MIN'], $this->fieldsArr[$j]['name'], $this->fieldsArr[$j]['viewFormat'], $this->fieldsArr[$j]['editFormat'], $this->mode);
							}
							if ($this->fieldsArr[$j]['totalAvg']){							
								$end["group".GoodFieldName($gname)."_total".$this->fieldsArr[$j]['goodName']."_avg"] =
									getFormattedValue($this->pageObject, $grp['summary'][$this->fieldsArr[$j]['name']]['AVG'], $this->fieldsArr[$j]['name'], $this->fieldsArr[$j]['viewFormat'], $this->fieldsArr[$j]['editFormat'], $this->mode);
							}
							if ($this->fieldsArr[$j]['totalSum']){		
								$end["group".GoodFieldName($gname)."_total".$this->fieldsArr[$j]['goodName']."_sum"] =
									getFormattedValue($this->pageObject, $grp['summary'][$this->fieldsArr[$j]['name']]['SUM'], $this->fieldsArr[$j]['name'], $this->fieldsArr[$j]['viewFormat'], $this->fieldsArr[$j]['editFormat'], $this->mode);
							}
						}
					}
					if ($this->fieldsArr[$j]['name'] == $this->repGroupFields[$i]['strGroupField'])
					{
						$field = $this->_sql->field($nField);
						$gvalue = $field->getFieldName($gkey, $grp['_first']);
					    if($field->overrideFormat())
						{
							$begin[GoodFieldName(GoodFieldName($gname).'_grval')] = htmlspecialchars($gvalue);
							if ($this->showGroupSummaryCount)
							{
								$end[GoodFieldName(GoodFieldName($gname).'_grval')] = htmlspecialchars($gvalue);
							}
						}
						else
						{
							$formattedValue = getFormattedValue($this->pageObject, $gvalue, $this->fieldsArr[$j]['name'], $this->fieldsArr[$j]['viewFormat'], $this->fieldsArr[$j]['editFormat'], $this->mode);
							$begin[GoodFieldName($gname.'_grval')] = htmlspecialchars($formattedValue);
							if ($this->showGroupSummaryCount)
							{
								$end[GoodFieldName($gname.'_grval')] = htmlspecialchars($formattedValue);
							}
						}
					}
				}                        
			}
        }
	}
	
	function _writePage(&$page, $src, $count)
	{
		$page['page_summary'] = true;
		if ($this->repPageSummary)
		{
	        for($i=0; $i<count($this->fieldsArr); $i++)
	        {
				if(is_array($src[$this->fieldsArr[$i]['name']]))
				{
					if ($this->fieldsArr[$i]['totalSum'])
					{
						$page["page_total".$this->fieldsArr[$i]['goodName']."_sum"] = getFormattedValue($this->pageObject, $src[$this->fieldsArr[$i]['name']]['SUM'], $this->fieldsArr[$i]['name'], $this->fieldsArr[$i]['viewFormat'], $this->fieldsArr[$i]['editFormat'], $this->mode);
					}
					if ($this->fieldsArr[$i]['totalAvg']){	
						$page["page_total".$this->fieldsArr[$i]['goodName']."_avg"] = getFormattedValue($this->pageObject, $src[$this->fieldsArr[$i]['name']]['AVG'], $this->fieldsArr[$i]['name'], $this->fieldsArr[$i]['viewFormat'], $this->fieldsArr[$i]['editFormat'], $this->mode);
					}
					if ($this->fieldsArr[$i]['totalMin']){	
						$page["page_total".$this->fieldsArr[$i]['goodName']."_min"] = getFormattedValue($this->pageObject, $src[$this->fieldsArr[$i]['name']]['MIN'], $this->fieldsArr[$i]['name'], $this->fieldsArr[$i]['viewFormat'], $this->fieldsArr[$i]['editFormat'], $this->mode);
					}
					if ($this->fieldsArr[$i]['totalMax']){	
						$page["page_total".$this->fieldsArr[$i]['goodName']."_max"] = getFormattedValue($this->pageObject, $src[$this->fieldsArr[$i]['name']]['MAX'], $this->fieldsArr[$i]['name'], $this->fieldsArr[$i]['viewFormat'], $this->fieldsArr[$i]['editFormat'], $this->mode);
					}
				}
			}
			$page['page_total_cnt'] = $count;
		}
	}
	
	function writeGlobalSummary($source)
	{
		$result = array();
			
		if ($this->repGlobalSummary)
		{			
			if(is_array($source["summary"]))
			{
				for($i=0; $i<count($this->fieldsArr); $i++)
				{
					if(is_array($source["summary"][$this->fieldsArr[$i]['name']]))
					{
						if ($this->fieldsArr[$i]['totalMax'])
						{
						    $result["global_total".$this->fieldsArr[$i]['goodName']."_max"] = getFormattedValue($this->pageObject, $source['summary'][$this->fieldsArr[$i]['name']]['MAX'], $this->fieldsArr[$i]['name'], $this->fieldsArr[$i]['viewFormat'], $this->fieldsArr[$i]['editFormat'], $this->mode);
						}
						if ($this->fieldsArr[$i]['totalMin']){	
							$result["global_total".$this->fieldsArr[$i]['goodName']."_min"] = getFormattedValue($this->pageObject, $source['summary'][$this->fieldsArr[$i]['name']]['MIN'], $this->fieldsArr[$i]['name'], $this->fieldsArr[$i]['viewFormat'], $this->fieldsArr[$i]['editFormat'], $this->mode);
						}
						if ($this->fieldsArr[$i]['totalAvg']){	
							$result["global_total".$this->fieldsArr[$i]['goodName']."_avg"] = getFormattedValue($this->pageObject, $source['summary'][$this->fieldsArr[$i]['name']]['AVG'], $this->fieldsArr[$i]['name'], $this->fieldsArr[$i]['viewFormat'], $this->fieldsArr[$i]['editFormat'], $this->mode);
						}
						if ($this->fieldsArr[$i]['totalSum']){	
							$result["global_total".$this->fieldsArr[$i]['goodName']."_sum"] = getFormattedValue($this->pageObject, $source['summary'][$this->fieldsArr[$i]['name']]['SUM'], $this->fieldsArr[$i]['name'], $this->fieldsArr[$i]['viewFormat'], $this->fieldsArr[$i]['editFormat'], $this->mode);
						}
					}
				}
			}
			$result["global_total_cnt"] = $source['count'];
		}
		
		return $result;
	}
}


?>
