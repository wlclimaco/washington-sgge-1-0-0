<?php
$group_sort_y=array();
class CrossTableReport
{
	var $TableName;
	var $col_summary=array();
	var $group_header=array();
	var $rowinfo=array();
	var $total_summary;
	var $xml_array;
	var $is_value_empty;
	var $index_field_x;
	var $index_field_y;
	var $table_type;
	/*
	 * true if report launched from runner project
	 */
	var $fromWizard = false;
	// Instance of ProjectSettings
	var $pSet = null;
	function CrossTableReport($rpt_array)
	{
		global $conn;
		$this->xml_array=$rpt_array;
		$arrdata=array();
		$arravgsum=array();
		$arravgcount=array();
		$group_y=array();
		$group_x=array();
		$sort_y=array();
		$grid_row=array();
		$avgsumx=array();
		$avgcountx=array();
		$this->total_summary=0;
		$this->is_value_empty=true;
		$this->table_type=$rpt_array["table_type"];
		if(!$this->table_type)
			$this->table_type="project";
		if($rpt_array["fromWizard"])
			$this->fromWizard = true;
		$this->TableName=$this->xml_array["tables"][0];
		$this->pSet = new ProjectSettings(GetTableByShort($this->TableName), PAGE_REPORT);
		$sum_x=$this->xml_array["group_fields"][count($this->xml_array["group_fields"])-1]["sum_x"];
		$sum_y=$this->xml_array["group_fields"][count($this->xml_array["group_fields"])-1]["sum_y"];
		$sum_total=$this->xml_array["group_fields"][count($this->xml_array["group_fields"])-1]["sum_total"];
		
		if(postvalue("group_func")!="")
			$_SESSION[$this->TableName."_group_func"]=postvalue("group_func");
		if(postvalue("field")!="")
			$_SESSION[$this->TableName."_field"]=postvalue("field");
		if(postvalue("axis_x")!="")
			$_SESSION[$this->TableName."_gr_x"]=postvalue("axis_x");
		if(postvalue("axis_y")!="")
			$_SESSION[$this->TableName."_gr_y"]=postvalue("axis_y");
		if(postvalue("rname")!="")
			$_SESSION[$this->TableName."_rname"]=postvalue("rname");
		
		$crtableSQL=$this->getstrSQL();
		$rs=db_query($crtableSQL,$conn);
		while($data=db_fetch_numarray($rs))
		{
			if(!in_array($data[1],$group_y))
			{
				$group_y[]=$data[1];
				$sort_y[]=count($sort_y);
			}
			if(!in_array($data[2],$group_x))
			{
				$group_x[]=$data[2];
				$this->col_summary["data"][count($group_x)-1]["col_summary"]="&nbsp;";
				$this->col_summary["data"][count($group_x)-1]["id_col_summary"]="total_x_".(count($group_x)-1);
			}
			for($i=0;$i<count($group_y);$i++)
			{
				if($group_y[$i]==$data[1])
					$key_y=$i;
			}
			for($i=0;$i<count($group_x);$i++)
			{
				if($group_x[$i]==$data[2])
				{
					$key_x=$i;
					$avgsumx[$key_x] = 0;
					$avgcountx[$key_x] = 0;
				}
			}
			if(!$this->is_value_empty)
			{
				$arrdata[$key_y][$key_x]=$data[0];
				$arravgsum[$key_y][$key_x]=$data[3];
				$arravgcount[$key_y][$key_x]=$data[4];
			}
			else
				$arrdata[$key_y][$key_x]="&nbsp;";
		}
		global $group_sort_y;
		$group_sort_y=$group_y;
		usort($sort_y,array("CrossTableReport","sort_arr_y"));
		$group_func=$_SESSION[$this->TableName."_group_func"];
		$idx_field=$_SESSION[$this->TableName."_field"];
		if(!$idx_field)
			$idx_field=0;
		if($group_func=="")
		{
			$arr_value=$this->getSelectedValue();
			if(empty($arr_value))
			{
				$field=$_SESSION['webreports']['group_fields'][0]["name"];
				$arr_value[]=$field;
			}
			else
				$field=$arr_value[$idx_field];
			$group_func=$this->getGroupFunction($field,"");
		}
		else
		{
			$arr_value=$this->getSelectedValue();
			$field=$arr_value[$idx_field];
			$isGroupFuncExists = false;
			foreach($this->xml_array["totals"] as $key=>$value)
			{
				if($this->FullFieldName($value["name"],$value["table"])==$field)
				{
					if($value["sum"]==true && $group_func=="sum")
					{
						$isGroupFuncExists = true;
						break;
					}
					if($value["max"]==true && $group_func=="max")
					{
						$isGroupFuncExists = true;
						break;
					}
					if($value["min"]==true && $group_func=="min")
					{
						$isGroupFuncExists = true;
						break;
					}
					if($value["avg"]==true && $group_func=="avg")
					{
						$isGroupFuncExists = true;
						break;
					}
				}
			}
			if(!$isGroupFuncExists)
				$group_func=$this->getGroupFunction($arr_value[$idx_field],"");
		}
		foreach($sort_y as $key_y)
		{
			$value_y = $group_y[$key_y];
			$this->rowinfo[$key_y]["row_summary"]="&nbsp;";
			$this->rowinfo[$key_y]["group_y"]=$this->getDisplayValue($this->index_field_y,$value_y);
			foreach($group_x as $key_x=>$value_x)
			{
				if(array_key_exists($key_y,$arrdata))
				{
					if(array_key_exists($key_x,$arrdata[$key_y]) && !$this->is_value_empty && !is_null($arrdata[$key_y][$key_x]))
						if($group_func=="avg")
							$this->rowinfo[$key_y]["row_record"]["data"][$key_x]["row_value"]=round($arrdata[$key_y][$key_x],2);
						else
							$this->rowinfo[$key_y]["row_record"]["data"][$key_x]["row_value"]=$arrdata[$key_y][$key_x];
					else
						$this->rowinfo[$key_y]["row_record"]["data"][$key_x]["row_value"]="&nbsp;";
					$this->rowinfo[$key_y]["row_record"]["data"][$key_x]["id_data"]=$key_y."_".$key_x;
				}
			}
			$this->rowinfo[$key_y]["id_row_summary"]="total_y_".$key_y;
		}

		foreach($group_x as $key_x=>$value_x)
		{
			if($value_x!="")
				$this->group_header["data"][$key_x]["gr_value"]=$this->getDisplayValue($this->index_field_x,$value_x);
			else
				$this->group_header["data"][$key_x]["gr_value"]="&nbsp;";
				
		}
		$this->total_summary="&nbsp;";
		foreach($this->rowinfo as $key_y=>$obj_y)
		{
			$obj_x=$obj_y["row_record"]["data"];
			foreach($obj_x as $key_x=>$value)
			{
				if($value["row_value"]!=="&nbsp;")
				{
					switch($group_func)
					{
						case "sum":
							if(!is_null($value["row_value"]))
							{
								$this->rowinfo[$key_y]["row_summary"]+=$value["row_value"];
								$this->col_summary["data"][$key_x]["col_summary"]+=$value["row_value"];
								$this->total_summary+=$value["row_value"];
							}
						break;
						case "min":
							if(($this->rowinfo[$key_y]["row_summary"]==="&nbsp;" || $value["row_value"]<$this->rowinfo[$key_y]["row_summary"]) && !is_null($value["row_value"]))
								$this->rowinfo[$key_y]["row_summary"]=$value["row_value"];
							if(($this->col_summary["data"][$key_x]["col_summary"]==="&nbsp;" || $this->col_summary["data"][$key_x]["col_summary"]>$value["row_value"]) && !is_null($value["row_value"]))
								$this->col_summary["data"][$key_x]["col_summary"]=$value["row_value"];
							if(($this->total_summary==="&nbsp;" || $this->total_summary>$value["row_value"]) && !is_null($value["row_value"]))
								$this->total_summary=$value["row_value"];
								
						break;
						case "max":
							if($this->rowinfo[$key_y]["row_summary"]==="&nbsp;" || $value["row_value"]>$this->rowinfo[$key_y]["row_summary"])
								$this->rowinfo[$key_y]["row_summary"]=$value["row_value"];
							if($this->col_summary["data"][$key_x]["col_summary"]==="&nbsp;" || $this->col_summary["data"][$key_x]["col_summary"]<$value["row_value"])								
								$this->col_summary["data"][$key_x]["col_summary"]=$value["row_value"];
							if($this->total_summary==="&nbsp;" || $this->total_summary<$value["row_value"])
								$this->total_summary=$value["row_value"];
						break;
						case "avg":
							$this->rowinfo[$key_y]["avgsumy"]+=$arravgsum[$key_y][$key_x];
							$this->rowinfo[$key_y]["avgcounty"]+=$arravgcount[$key_y][$key_x];
							$this->rowinfo[$key_y]["row_record"]["data"][$key_x]["avgsumx"]+=$arravgsum[$key_y][$key_x];
							$this->rowinfo[$key_y]["row_record"]["data"][$key_x]["avgcountx"]+=$arravgcount[$key_y][$key_x];
						break;
					}
					if($sum_x==true && !$this->is_value_empty && !is_null($this->col_summary["data"][$key_x]["col_summary"]))
					{
						if(is_numeric($this->col_summary["data"][$key_x]["col_summary"]))
							$this->col_summary["data"][$key_x]["col_summary"]=round($this->col_summary["data"][$key_x]["col_summary"],2);
					}
					else
						$this->col_summary["data"][$key_x]["col_summary"]="&nbsp;";
				}
			}
			if($sum_y==true && !$this->is_value_empty && !is_null($this->rowinfo[$key_y]["row_summary"]))
			{
				if(is_numeric($this->rowinfo[$key_y]["row_summary"]))
					$this->rowinfo[$key_y]["row_summary"]=round($this->rowinfo[$key_y]["row_summary"],2);
			}
			else
				$this->rowinfo[$key_y]["row_summary"]="&nbsp;";
		}
		
		if($group_func=="avg")
		{
			$total_sum=0;
			$total_count=0;
			
			foreach($this->rowinfo as $key_y=>$valuey)
			{
				if($valuey["avgcounty"])
				{
					$this->rowinfo[$key_y]["row_summary"]=round($valuey["avgsumy"]/$valuey["avgcounty"],2);
					$total_sum+=$valuey["avgsumy"];
					$total_count+=$valuey["avgcounty"];
				}
				foreach($valuey["row_record"]["data"] as $key_x=>$valuex)
				{
					if($valuex["avgcountx"])
					{
						$avgsumx[$key_x]+=$valuex["avgsumx"];
						$avgcountx[$key_x]+=$valuex["avgcountx"];
						$total_sum+=$valuex["avgsumx"];
						$total_count+=$valuex["avgcountx"];
					}
				}
			}
			foreach($avgsumx as $key=>$value)
				if($avgcountx[$key])
					$this->col_summary["data"][$key]["col_summary"]=round($value/$avgcountx[$key],2);
			if($total_count)
				$this->total_summary=$total_sum/$total_count;
		}
		
		if($sum_total==true && !$this->is_value_empty)
		{
			if(is_numeric($this->total_summary))
				$this->total_summary=round($this->total_summary,2);
		}
		else
			$this->total_summary="&nbsp;";
		
		$prefix="";
		if ($this->table_type!="db") 
		{
			if(!$this->fromWizard)
				$prefix=$this->xml_array["tables"][0]."_";
			$field=$arr_value[$idx_field];
		}
		else
		{
			$field=GoodFieldName($arr_value[$idx_field]);
		}
			
		if($this->xml_array['totals'][$prefix.$field]['curr']==true && count($this->rowinfo))
		{
			foreach($this->rowinfo as $arrkey=>$arrfield)
			{
				foreach($arrfield["row_record"]["data"] as $fieldkey=>$fieldvalue)
				{
					if(is_numeric($fieldvalue["row_value"]))
					{
						$this->rowinfo[$arrkey]["row_record"]["data"][$fieldkey]["row_value"]=str_format_currency($fieldvalue["row_value"]);
					}
				}
				if(is_numeric($arrfield["row_summary"]))
				{
					$this->rowinfo[$arrkey]["row_summary"]=str_format_currency($arrfield["row_summary"]);
				}
			}
			if(is_numeric($this->total_summary))
			{
				$this->total_summary=str_format_currency($this->total_summary);
			}
			foreach($this->col_summary["data"] as $arrkey=>$arrvalue)
			{
				if(is_numeric($arrvalue["col_summary"]))
				{
					$this->col_summary["data"][$arrkey]["col_summary"]=str_format_currency($arrvalue["col_summary"]);
				}
			}
		}
	}
	function sort_arr_y($a,$b)
	{
		global $group_sort_y;
		if($group_sort_y[$a]>$group_sort_y[$b])
			return true;
		else
			return false;
	}
	function getCrossTableData()
	{
		return $this->rowinfo;
	}
	function getCrossTableHeader()
	{
		return $this->group_header;
	}
	function getCrossTableSummary()
	{
		return $this->col_summary;
	}
	function getTotalSummary()
	{
		return $this->total_summary;
	}
	function getstrSQL()
	{
		global $strSQL;
		$gr_x=$_SESSION[$this->TableName."_gr_x"];
		$gr_y=$_SESSION[$this->TableName."_gr_y"];
		$index_field=$_SESSION[$this->TableName."_field"];
		$group_func=$_SESSION[$this->TableName."_group_func"];
		
		$group_x=array();
		$group_y=array();
		$arr_value=array();
		if($gr_x=="")
		{
			$this->index_field_x=$this->getFirstGroupField("x");
			$group_x=$this->getIntervalType($this->index_field_x);
		}
		else
		{
			$group_x=$this->getIntervalType($gr_x);
			$this->index_field_x=$gr_x;
		}
		if($gr_y=="")
		{
			$this->index_field_y=$this->getFirstGroupField("y");
			$group_y=$this->getIntervalType($this->index_field_y);
		}
		else
		{
			$group_y=$this->getIntervalType($gr_y);
			$this->index_field_y=$gr_y;
		}
	
		$arr_value=$this->getSelectedValue();
		
		if($index_field=="")
			if(!empty($arr_value))
			{
				$field=$arr_value[0];
				$val=AddFieldWrappers($this->CrossGoodFieldName($field));
			}
			else
			{
				$field=$_SESSION['webreports']['group_fields'][0]["name"];
				$val=AddFieldWrappers($this->CrossGoodFieldName($field));
			}
		else 
		{
			$val=AddFieldWrappers($this->CrossGoodFieldName($arr_value[$index_field]));
			$field=$arr_value[$index_field];
		}

		if($group_func=="")
			$group_func="sum";
			
		$gr_func=$this->getGroupFunction($field,$group_func);
		
		$select_field="' ', ";
		
		$avg_func="";
		if($val!=" ")
		{
			$select_field=$gr_func."(".AddFieldWrappers($val)."), ";
			$this->is_value_empty=false;
			$ftype = $this->getFieldType($field);
			if($group_func=="avg" && !IsDateFieldType($ftype)) 
				$avg_func=", sum(".AddFieldWrappers($val).") as ".AddFieldWrappers("avg_sum").", count(".AddFieldWrappers($val).") as ".AddFieldWrappers("avg_count");
			else
				$avg_func=", 1 as ".AddFieldWrappers("avg_sum").", 1 as ".AddFieldWrappers("avg_count");
			
		}
		global $strTableName;
		$hwhere="";
		if(tableEventExists("BeforeQueryReport",$strTableName)) 
		{
			$eventObj = getEventObject($strTableName);
			$eventObj->BeforeQueryReport($hwhere);
			if($hwhere)
				$hwhere=" where ".$hwhere;
		}
								$crtableSQL="select ".$select_field.$group_y[0].", ".$group_x[0].$avg_func." from (".$strSQL.") as cross_table".$hwhere." group by ".$group_x[1].", ".$group_y[1]." Order by ".$group_x[1];
		//echo $crtableSQL;
		return $crtableSQL;
	}
	function getIntervalType($index)
	{
		$field=$this->xml_array["group_fields"][$index]["name"];
		$ftype = $this->getFieldType($field);
		$arr=array();
		
		$arr=$this->xml_array["group_fields"];
		for($i=0;$i<count($arr)-1;$i++)
		{
			if($field==$arr[$i]["name"] && $index==$i)
			{
				$int_type=$arr[$i]["int_type"];
				break;
			}
		}
		if ($int_type == 0 ) 
			return array(AddFieldWrappers($this->CrossGoodFieldName($field)),AddFieldWrappers($this->CrossGoodFieldName($field)));
		elseif ( IsNumberType($ftype) )
			return $this->getNumberTypeInterval($field,$int_type);
		elseif ( IsCharType( $ftype ) )
			return $this->getCharTypeInterval($field,$int_type);
		elseif ( IsDateFieldType( $ftype ) )
			return $this->getDateTypeInterval($field,$int_type);
	}
	function getDateTypeInterval($field,$int_type)
	{
		$field=AddFieldWrappers($this->CrossGoodFieldName($field));
		switch(GetDatabaseType())
		{
			case 0://MySQL
				if($int_type==1) // DATE_INTERVAL_YEAR
					return array("year(".$field.")*10000+0101","YEAR(".$field.")");
				elseif($int_type==2) // DATE_INTERVAL_QUARTER
					return array("year(".$field.")*10000+QUARTER(".$field.")*100+1","year(".$field."),QUARTER(".$field.")");
				elseif($int_type==3) // DATE_INTERVAL_MONTH
					return array("year(".$field.")*10000+month(".$field.")*100+1","year(".$field."),month(".$field.")");
				elseif($int_type==4) // DATE_INTERVAL_WEEK
					return array("year(".$field.")*10000+week(".$field.")*100+01","year(".$field."),WEEK(".$field.")");
				elseif($int_type==5) // DATE_INTERVAL_DAY
					return array("year(".$field.")*10000+month(".$field.")*100+day(".$field.")","year(".$field."),month(".$field."),day(".$field.")");
				elseif($int_type==6) // DATE_INTERVAL_HOUR
					return array("year(".$field.")*1000000+month(".$field.")*10000+day(".$field.")*100+HOUR(".$field.")","year(".$field."),month(".$field."),day(".$field."),hour(".$field.")");
				elseif($int_type==7) // DATE_INTERVAL_MINUTE
					return array("year(".$field.")*1000000+month(".$field.")*1000000+day(".$field.")*10000+HOUR(".$field.")*100+minute(".$field.")","year(".$field."),month(".$field."),day(".$field."),hour(".$field."),minute(".$field.")");
				break;
			case 1://Oracle
				if($int_type==1) // DATE_INTERVAL_YEAR
					return array("TO_CHAR(".$field.", 'YYYY')*10000+0101","TO_CHAR(".$field.", 'YYYY')");
				elseif($int_type==2) // DATE_INTERVAL_QUARTER
					return array("TO_CHAR(".$field.", 'YYYY')*10000+TO_CHAR(".$field.",'Q')*100+1","TO_CHAR(".$field.", 'YYYY'),TO_CHAR(".$field.",'Q')");
				elseif($int_type==3) // DATE_INTERVAL_MONTH
					return array("TO_CHAR(".$field.", 'YYYY')*10000+TO_CHAR(".$field.".'MM')*100+1","TO_CHAR(".$field.", 'YYYY'),TO_CHAR(".$field.".'MM')");
				elseif($int_type==4) // DATE_INTERVAL_WEEK
					return array("TO_CHAR(".$field.", 'YYYY')*10000+TO_CHAR(".$field.",'W')*100+01","TO_CHAR(".$field.", 'YYYY'),TO_CHAR(".$field.",'W')");
				elseif($int_type==5) // DATE_INTERVAL_DAY
					return array("TO_CHAR(".$field.", 'YYYY')*10000+TO_CHAR(".$field.",'MM')*100+TO_CHAR(".$field.",'DD')","TO_CHAR(".$field.", 'YYYY'),TO_CHAR(".$field.",'MM'),TO_CHAR(".$field.",'DD')");
				elseif($int_type==6) // DATE_INTERVAL_HOUR
					return array("TO_CHAR(".$field.", 'YYYY')*1000000+TO_CHAR(".$field.",'MM')*10000+TO_CHAR(".$field.",'DD')*100+TO_CHAR(".$field.",'HH')","TO_CHAR(".$field.", 'YYYY'),TO_CHAR(".$field.",'MM'),TO_CHAR(".$field.",'DD'),TO_CHAR(".$field.",'HH')");
				elseif($int_type==7) // DATE_INTERVAL_MINUTE
					return array("TO_CHAR(".$field.", 'YYYY')*1000000+TO_CHAR(".$field.",'MM')*1000000+TO_CHAR(".$field.",'DD')*10000+TO_CHAR(".$field.",'HH')*100+TO_CHAR(".$field.",'MI')","TO_CHAR(".$field.", 'YYYY'),TO_CHAR(".$field.",'MM'),TO_CHAR(".$field.",'DD'),TO_CHAR(".$field.",'HH'),TO_CHAR(".$field.",'MI')");
				break;
			case 2://MSSQL
				if($int_type==1) // DATE_INTERVAL_YEAR
					return array("datepart(yyyy,".$field.")*10000+0101","datepart(yyyy,".$field.")");
				elseif($int_type==2) // DATE_INTERVAL_QUARTER
					return array("datepart(yyyy,".$field.")*10000+datepart(qq,".$field.")*100+1","datepart(yyyy,".$field."),datepart(qq,".$field.")");
				elseif($int_type==3) // DATE_INTERVAL_MONTH
					return array("datepart(yyyy,".$field.")*10000+datepart(mm,".$field.")*100+1","datepart(yyyy,".$field."),datepart(mm,".$field.")");
				elseif($int_type==4) // DATE_INTERVAL_WEEK
					return array("datepart(yyyy,".$field.")*10000+(datepart(ww,".$field.")-1)*100+01","datepart(yyyy,".$field."),datepart(ww,".$field.")");
				elseif($int_type==5) // DATE_INTERVAL_DAY
					return array("datepart(yyyy,".$field.")*10000+datepart(mm,".$field.")*100+datepart(dd,".$field.")","datepart(yyyy,".$field."),datepart(mm,".$field."),datepart(dd,".$field.")");
				elseif($int_type==6) // DATE_INTERVAL_HOUR
					return array("datepart(yyyy,".$field.")*1000000+datepart(mm,".$field.")*10000+datepart(dd,".$field.")*100+datepart(hh,".$field.")","datepart(yyyy,".$field."),datepart(mm,".$field."),datepart(dd,".$field."),datepart(hh,".$field.")");
				elseif($int_type==7) // DATE_INTERVAL_MINUTE
					return array("datepart(yyyy,".$field.")*1000000+datepart(mm,".$field.")*1000000+datepart(dd,".$field.")*10000+datepart(hh,".$field.")*100+datepart(mi,".$field.")","datepart(yyyy,".$field."),datepart(mm,".$field."),datepart(dd,".$field."),datepart(hh,".$field."),datepart(mi,".$field.")");
				break;
			case 3://Access
				if($int_type==1) // DATE_INTERVAL_YEAR
					return array("datepart('yyyy',".$field.")*10000+0101","datepart('yyyy',".$field.")");
				elseif($int_type==2) // DATE_INTERVAL_QUARTER
					return array("datepart('yyyy',".$field.")*10000+datepart('q',".$field.")*100+1","datepart('yyyy',".$field."),datepart('q',".$field.")");
				elseif($int_type==3) // DATE_INTERVAL_MONTH
					return array("datepart('yyyy',".$field.")*10000+datepart('m',".$field.")*100+1","datepart('yyyy',".$field."),datepart('m',".$field.")");
				elseif($int_type==4) // DATE_INTERVAL_WEEK
					return array("datepart('yyyy',".$field.")*10000+(datepart('ww',".$field.")-1)*100+01","datepart('yyyy',".$field."),datepart('ww',".$field.")");
				elseif($int_type==5) // DATE_INTERVAL_DAY
					return array("datepart('yyyy',".$field.")*10000+datepart('m',".$field.")*100+datepart('d',".$field.")","datepart('yyyy',".$field."),datepart('m',".$field."),datepart('d',".$field.")");
				elseif($int_type==6) // DATE_INTERVAL_HOUR
					return array("datepart('yyyy',".$field.")*1000000+datepart('m',".$field.")*10000+datepart('d',".$field.")*100+datepart('h',".$field.")","datepart('yyyy',".$field."),datepart('m',".$field."),datepart('d',".$field."),datepart('h',".$field.")");
				elseif($int_type==7) // DATE_INTERVAL_MINUTE
					return array("datepart('yyyy',".$field.")*1000000+datepart('m',".$field.")*1000000+datepart('d',".$field.")*10000+datepart('h',".$field.")*100+datepart('n',".$field.")","datepart('yyyy',".$field."),datepart('m',".$field."),datepart('d',".$field."),datepart('h',".$field."),datepart('n',".$field.")");
				break;
			case 4: // Postgree 
				if($int_type==1) // DATE_INTERVAL_YEAR
					return array("date_part('year',".$field.")*10000+0101","date_part('year',".$field.")");
				elseif($int_type==2) // DATE_INTERVAL_QUARTER
					return array("date_part('year',".$field.")*10000+date_part('quarter',".$field.")*100+1","date_part('year',".$field."),date_part('quarter',".$field.")");
				elseif($int_type==3) // DATE_INTERVAL_MONTH
					return array("date_part('year',".$field.")*10000+date_part('month',".$field.")*100+1","date_part('year',".$field."),date_part('month',".$field.")");
				elseif($int_type==4) // DATE_INTERVAL_WEEK
					return array("date_part('year',".$field.")*10000+(date_part('week',".$field.")-1)*100+01","date_part('year',".$field."),date_part('week',".$field.")");
				elseif($int_type==5) // DATE_INTERVAL_DAY
					return array("date_part('year',".$field.")*10000+date_part('month',".$field.")*100+date_part('days',".$field.")","date_part('year',".$field."),date_part('month',".$field."),date_part('days',".$field.")");
				elseif($int_type==6) // DATE_INTERVAL_HOUR
					return array("date_part('year',".$field.")*1000000+date_part('month',".$field.")*10000+date_part('days',".$field.")*100+date_part('hour',".$field.")","date_part('year',".$field."),date_part('month',".$field."),date_part('days',".$field."),date_part('hour',".$field.")");
				elseif($int_type==7) // DATE_INTERVAL_MINUTE
					return array("date_part('year',".$field.")*1000000+date_part('month',".$field.")*1000000+date_part('days',".$field.")*10000+date_part('hour',".$field.")*100+date_part('minute',".$field.")","date_part('year',".$field."),date_part('month',".$field."),date_part('days',".$field."),date_part('hour',".$field."),date_part('minute',".$field.")");
				break;
			case 5:
				return "substring(".$field." from 1 for ".$int_type.")"; //Informix
				break;
			case 6: //SQLite3 
				return array($field,$field);
			case 7: //DB2
				if($int_type==1) // DATE_INTERVAL_YEAR
					return array("year(".$field.")*10000+0101","YEAR(".$field.")");
				elseif($int_type==2) // DATE_INTERVAL_QUARTER
					return array("year(".$field.")*10000+QUARTER(".$field.")*100+1","year(".$field."),QUARTER(".$field.")");
				elseif($int_type==3) // DATE_INTERVAL_MONTH
					return array("year(".$field.")*10000+month(".$field.")*100+1","year(".$field."),month(".$field.")");
				elseif($int_type==4) // DATE_INTERVAL_WEEK
					return array("year(".$field.")*10000+week(".$field.")*100+01","year(".$field."),WEEK(".$field.")");
				elseif($int_type==5) // DATE_INTERVAL_DAY
					return array("year(".$field.")*10000+month(".$field.")*100+day(".$field.")","year(".$field."),month(".$field."),day(".$field.")");
				elseif($int_type==6) // DATE_INTERVAL_HOUR
					return array("year(".$field.")*1000000+month(".$field.")*10000+day(".$field.")*100+HOUR(".$field.")","year(".$field."),month(".$field."),day(".$field."),hour(".$field.")");
				elseif($int_type==7) // DATE_INTERVAL_MINUTE
					return array("year(".$field.")*1000000+month(".$field.")*1000000+day(".$field.")*10000+HOUR(".$field.")*100+minute(".$field.")","year(".$field."),month(".$field."),day(".$field."),hour(".$field."),minute(".$field.")");
				break;
		}
	}
	function getNumberTypeInterval($field,$int_type)
	{
		return array("floor(".AddFieldWrappers($this->CrossGoodFieldName($field))."/".$int_type.")*".$int_type,"floor(".AddFieldWrappers($this->CrossGoodFieldName($field))."/".$int_type.")*".$int_type);
	}
	function getCharTypeInterval($field,$int_type)
	{
		$field=AddFieldWrappers($this->CrossGoodFieldName($field));
		switch(GetDatabaseType())
		{
			case 0:
				return array("left(".$field.",".$int_type.")","left(".$field.",".$int_type.")"); //MySQL
				break;
			case 1:
				return array("substr(".$field.",1,".$int_type.")","substr(".$field.",1,".$int_type.")"); //Oracle
				break;
			case 2:
				return array("left(".$field.",".$int_type.")","left(".$field.",".$int_type.")"); //MSSSQL
				break;
			case 3:
				return array("left(".$field.",".$int_type.")","left(".$field.",".$int_type.")"); //MS Access
				break;
			case 4:
				return array("substring(".$field." from 1 for ".$int_type.")","substring(".$field." from 1 for ".$int_type.")"); //Postgree
				break;
			case 5:
				return array("substring(".$field." from 1 for ".$int_type.")","substring(".$field." from 1 for ".$int_type.")"); //Informix
				break;
			case 6:
				return array("substr(".$field.",1,".$int_type.")","substr(".$field.",1,".$int_type.")"); //SQLite3
				break;
			case 7:
				return array("substr(".$field.",1,".$int_type.")","substr(".$field.",1,".$int_type.")"); //DB2
				break;
		}
	}
	function getSelectedValue()
	{
		$arr = array();
		$firstarr = array();
		foreach($this->xml_array["totals"] as $key=>$value)
		{
			if(empty($firstarr))
				$firstarr[]=$this->FullFieldName($value["name"],$value["table"]);
			if($value["min"]==true || $value["max"]==true || $value["sum"]==true || $value["avg"]==true)
			{
				$arr[]=$this->FullFieldName($value["name"],$value["table"]);
			}
		}
		if(empty($arr))
			$arr = $firstarr;
		return $arr;
	}
	function getRadioGroupFunctions($index_field,$gr_func)
	{
		$arr = array();
		$arrDisplay = array();
		$i=0;
		$res="";
		$arr_selected_value=$this->getSelectedValue();
		$field=$arr_selected_value[$index_field];
		foreach($this->xml_array["totals"] as $key=>$value)
		{
			if($this->FullFieldName($value["name"],$value["table"])==$field)
			{
				if($value["sum"]==true)
				{
					$arrDisplay[$i][]="Soma";
					$arr[$i][]="sum";
				}
				if($value["max"]==true)
				{
					$arrDisplay[$i][]="Max";
					$arr[$i][]="max";
				}
				if($value["min"]==true)
				{
					$arrDisplay[$i][]="Min";
					$arr[$i][]="min";
				}
				if($value["avg"]==true)
				{
					$arrDisplay[$i][]="Média";
					$arr[$i][]="avg";
				}
				$arr[$i]["name"]=$field;
				$i++;
			}
		}
		if(!isset($arr[0][0]))
		{
				$arr[0][0]="sum";
				$arrDisplay[0][0]="Soma";
		}
		foreach($arr as $key=>$value)
		{
			if($value["name"]==$field)
			{
				$res="";
				for($j=0;$j<count($value)-1;$j++)
				{
					$s="";
					if($res=="" || $gr_func==$value[$j])
						$s="checked";
					$res.="<input type=radio value='".$value[$j]."' name=group_func ".$s." onclick='refresh_crosstable(false);return false;'> ".$arrDisplay[$key][$j]."&nbsp;&nbsp;";
				}
			}
		}
		
		return $res;
	}
	function getGroupFunction($field,$grfunc)
	{
		$arr=array();
		foreach($this->xml_array["totals"] as $key=>$value)
		{
			$field_value=$this->FullFieldName($value["name"],$value["table"]);
			if($field_value==$field)
			{
				if($value["sum"]==true && $grfunc=="sum")
					return "sum";
				if($value["max"]==true && $grfunc=="max")
					return "max";
				if($value["min"]==true && $grfunc=="min")
					return "min";
				if($value["avg"]==true && $grfunc=="avg")
					return "avg";
					
				if($value["sum"]==true)
					return "sum";
				if($value["max"]==true)
					return "max";
				if($value["min"]==true)
					return "min";
				if($value["avg"]==true)
					return "avg";
				
			}
		}
		return "sum";
	}
	function ajax_refresh_crosstable()
	{
		$arr_value=$this->getSelectedValue();
		$field=$arr_value[$_SESSION[$this->TableName."_field"]];
		echo my_json_encode(array($this->rowinfo,$this->col_summary,$this->total_summary,$this->getRadioGroupFunctions($_SESSION[$this->TableName."_field"],$_SESSION[$this->TableName."_group_func"]),$this->getTotalsName($this->getGroupFunction($field,$_SESSION[$this->TableName."_group_func"]))));
	}
	function getGroupFields($axis)
	{
		$res="";
		$label=$this->xml_array["totals"];
		$arr=$this->xml_array["group_fields"];
		for($i=0;$i<count($arr)-1;$i++)
		{
			$s="";
			if($axis=="x" && $arr[$i]["group_type"]=="x" || $axis=="y" && $arr[$i]["group_type"]=="y" || $arr[$i]["group_type"]=="all")
			{
				if($axis=="x" && $this->index_field_y!=$i || $axis=="y" && $this->index_field_x!=$i)
				{
					if($this->index_field_x==$i && $axis=="x" || $this->index_field_y==$i && $axis=="y")
						$s="selected";
					$strlabel="";
					foreach($label as $val)
					{
						if($arr[$i]["name"]==$this->FullFieldName($val["name"],$val["table"]))
						{
							$strlabel=$val["label"];
							break;
						}
					}
					$res.="<option value='".$i."' ".$s.">".$strlabel."</option>";
				}
			}
		}
		return $res;
	}
	function getFirstGroupField($axis)
	{
		$arr=$this->xml_array["group_fields"];
		$arrX=array();
		$arrY=array();
		$arrAll=array();
		for($i=0;$i<count($arr)-1;$i++)
		{
			if($arr[$i]["group_type"]=="x")
				$arrX[]=$i;
			if($arr[$i]["group_type"]=="y")
				$arrY[]=$i;
			if($arr[$i]["group_type"]=="all")
				$arrAll[]=$i;
		}	
		if(count($arrX)>0 && $axis=="x")
			return $arrX[0];
		if(count($arrY)>0 && $axis=="y")
			return $arrY[0];
		if(count($arrX)==0 && $axis=="x")
			return $arrAll[0];
		if(count($arrY)==0 && $axis=="y")
		{
			if(count($arrX)==0)
				return $arrAll[1];
			else
				return $arrAll[0];
		}
	}
	function getDisplayValue($index,$value)
	{
		global $locale_info;
		$field=$this->xml_array["group_fields"][$index]["name"];
		if($value=="" || is_null($value))
			return "";
		
		$ftype = $this->getFieldType($field);
		$arr=array();
		
		$arr=$this->xml_array["group_fields"];
		for($i=0;$i<count($arr)-1;$i++)
		{
			if($field==$arr[$i]["name"] && $index==$i)
			{
				$int_type=$arr[$i]["int_type"];
				break;
			}
		}
		if ( $int_type == 0 ) {
			$prefix="";
			if ($this->table_type!="db")
			{
				if(!$this->fromWizard)
					$prefix=$this->xml_array["tables"][0]."_";
			}
			else
				$field=$this->CrossGoodFieldName($field);
			if($this->xml_array['totals'][$prefix.$field]['curr']==true)
				return str_format_currency($value);
			else
				return $value;
		} elseif ( IsNumberType( $ftype ) ) {
			$start=$value-($value%$int_type);
			$end=$start+$int_type;
			$prefix="";
			if ($this->table_type!="db")
			{
				if(!$this->fromWizard)
					$prefix=$this->xml_array["tables"][0]."_";
			}
			else
				$field=$this->CrossGoodFieldName($field);
			if($this->xml_array['totals'][$prefix.$field]['curr']==true)
				return str_format_currency($start)." - ".str_format_currency($end);
			else
				return $start." - ".$end;
		} elseif ( IsCharType( $ftype ) ) {
			return substr($value,0,$int_type);
		} elseif ( IsDateFieldType( $ftype ) ) {
			$dvalue=substr($value,0,4).'-'.substr($value,4,2).'-'.substr($value,6,2);
			if(strlen($value)==10)
				$dvalue.=" ".substr($value,8,2)."00:00";
			elseif(strlen($value)==12)
				$dvalue.=" ".substr($value,8,2).":".substr($value,10,2).":00";
			$tm = db2time($dvalue);
			if(!count($tm))
				return "";
			if ( $int_type == 1 ) { // DATE_INTERVAL_YEAR
				return $tm[0];
			} elseif ( $int_type == 2 ) { // DATE_INTERVAL_QUARTER
				return $tm[0]."/Q".$tm[1];
			} elseif ( $int_type == 3 ) { // DATE_INTERVAL_MONTH
				return @$locale_info["LOCALE_SABBREVMONTHNAME".$tm[1]]." ".$tm[0];
			} elseif ( $int_type == 4 ) { // DATE_INTERVAL_WEEK
				$dates=$this->getDatesByWeek($tm[1]+1,$tm[0]);
				return format_shortdate(db2time($dates[0])) . ' - ' . format_shortdate(db2time($dates[1]));
			} elseif ( $int_type == 5 ) { // DATE_INTERVAL_DAY
				return format_shortdate($tm);
			} elseif ( $int_type == 6 ) { // DATE_INTERVAL_HOUR
				$tm[4]=0;
				$tm[5]=0;
				return str_format_datetime($tm);
			} elseif ( $int_type == 7 ) { // DATE_INTERVAL_MINUTE
				$tm[5]=0;
				return str_format_datetime($tm);
			} else {
				return str_format_datetime($tm);
			}
		}
	}
	function getDatesByWeek($week, $year) 
	{
		global $locale_info;
		$startweekday=0;
		if($locale_info["LOCALE_IFIRSTDAYOFWEEK"]>0)
			$startweekday=7-$locale_info["LOCALE_IFIRSTDAYOFWEEK"];
        /*$week_number = sprintf('%02d', $_week_number);
        $date_base = strtotime($year . 'W' . $week_number . (1-$startweekday).' 00:00:00');
        $date_limit = strtotime($year . 'W' . $week_number . (7-$startweekday).' 23:59:59');
        return array($date_base, $date_limit);*/
	
	 // ?????????? ??? ??? ???
	 $L = date("L", mktime(1,1,1,1,1, $year)); // ??????? ????? ???????? ?????? ???? ???
	 $months = array(31, 28+$L, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31);
	 // ????? ?????? * 7 ????
	 $total_days = ($week-1)*7; // -1 ????? ???????? ?????? ?????? ??????
	 $i = 0;
	 $sum=0;
	 while($sum <= $total_days){
	 $sum += $months[$i++];
	 }
	 // ????? ???? ? ???? ???????:
	 $sum -= $months[$i-1];
	 // ????? ??????
	 $month = $i;
	 // ???? ?? ???? ? ???? ??????
	 $day = $total_days - $sum;
	 // ???? ?????? ????? ???
	 $day_of_week = date("w", mktime(0,0,0, $month, $day, $year));
	 // ???? ??? ???????????
	 if ($day_of_week==0) $day_of_week=7;
	 // ???????? ?????? ??????
	 $day = $day - ($day_of_week - 1) - $startweekday;
	 $dates = array();
	 $dates[0] = date("Y-m-d", mktime(0,0,0, $month, $day, $year));
	 $dates[1] = date("Y-m-d", mktime(1,1,1, $month, $day+6, $year));
	 return $dates;
	}
	function getValuesControl()
	{
		$arr_list=array();
		$arr_list=$this->getSelectedValue();
		$arr_label=$this->xml_array["totals"];
		$res="";
		$first_field=0;
		$i=0;
		if(!empty($arr_list))
		{
			foreach($arr_list as $value)
			{
				$s="";
				if($i==0 || $i==$_SESSION[$this->TableName."_field"])
				{
					$first_field=$i;
					$s="selected";
				}
				$strlabel="";
				foreach($arr_label as $val)
					if($value==$this->FullFieldName($val["name"],$val["table"]))
					{
						$strlabel=$val["label"];
						break;
					}
				$res.="<option value=".$i." ".$s.">".htmlspecialchars($strlabel)."</option>";
				$i++;
			}
		}
		return array($res,$first_field);
	}
	function FullFieldName($field,$table="")
	{
		if(!$table)
			$table=$this->TableName;
		if($this->table_type=="db")
			if(strpos($field,".")===false)
				$res=$table.".".$field;
			else
				$res=$field;
		else
			$res=$field;
		return $res;
	}
	function CrossGoodFieldName($field)
	{
		if($this->table_type=="db")
			return GoodFieldName($field);
		else 
			return $field;
	}
	function getPrintCrossHeader($axis_x,$axis_y,$field,$grfunc)
	{
		$arr_value=array();
		$arr_value=$this->getSelectedValue();
		$field_value=$arr_value[$field];
		$prefix="";
		if($this->table_type!="db" && !$this->fromWizard)
			$prefix=$this->xml_array["tables"][0]."_";
		$fieldNameX = $this->xml_array["group_fields"][$axis_x]["name"];
		$fieldNameY = $this->xml_array["group_fields"][$axis_y]["name"];
		if(!$this->fromWizard)
		{
			$fieldNameX = $prefix.GoodFieldName($fieldNameX);
			$fieldNameY = $prefix.GoodFieldName($fieldNameY);
			$field_value = $prefix.GoodFieldName($field_value);
		}
		return "Grupo X"
			.":<b>".$this->xml_array["totals"][$fieldNameX]["label"]."</b>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"."Grupo Y"
			.":<b>".$this->xml_array["totals"][$fieldNameY]["label"]."</b>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"."Campo"
			.":<b>".$this->xml_array["totals"][$field_value]["label"]."</b>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"."Função Grupo".":<b>".$grfunc."</b>";
	}
	function getTotalsName($grfunc)
	{
		switch($grfunc)
		{
			case "sum":
				return "Soma";
				break;
			case "min":
				return "Min";
				break;
			case "max":
				return "Max";
				break;
			case "avg":
				return "Média";
				break;
		}
	}
	function getFieldType($field)
	{
		if($this->table_type=="db")
			$ftype=WRGetFieldType($this->FullFieldName($field));
		elseif($this->table_type=="project")
			$ftype = $this->pSet->getFieldType($field);
		else
		{ 
			$fields_type=WRGetAllCustomFieldType();
			$ftype=$fields_type[$field];
		}
		return $ftype;
	}
}

?>