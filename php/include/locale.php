<?php 

//	locale settings

//	locale settings

$locale_info = array();

//	date settings
$locale_info["LOCALE_ICENTURY"]="1";
$locale_info["LOCALE_IDATE"]="1";
$locale_info["LOCALE_ILDATE"]="1";
$locale_info["LOCALE_SDATE"]="/";
$locale_info["LOCALE_SLONGDATE"]="dddd, d' de 'MMMM' de 'yyyy";
$locale_info["LOCALE_SSHORTDATE"]="d/M/yyyy";
//	weekday names
$locale_info["LOCALE_IFIRSTDAYOFWEEK"]="6";
$locale_info["LOCALE_SDAYNAME1"]="segunda-feira";
$locale_info["LOCALE_SDAYNAME2"]="terça-feira";
$locale_info["LOCALE_SDAYNAME3"]="quarta-feira";
$locale_info["LOCALE_SDAYNAME4"]="quinta-feira";
$locale_info["LOCALE_SDAYNAME5"]="sexta-feira";
$locale_info["LOCALE_SDAYNAME6"]="sábado";
$locale_info["LOCALE_SDAYNAME7"]="domingo";
$locale_info["LOCALE_SABBREVDAYNAME1"]="seg";
$locale_info["LOCALE_SABBREVDAYNAME2"]="ter";
$locale_info["LOCALE_SABBREVDAYNAME3"]="qua";
$locale_info["LOCALE_SABBREVDAYNAME4"]="qui";
$locale_info["LOCALE_SABBREVDAYNAME5"]="sex";
$locale_info["LOCALE_SABBREVDAYNAME6"]="sáb";
$locale_info["LOCALE_SABBREVDAYNAME7"]="dom";
//	month names
$locale_info["LOCALE_SMONTHNAME1"]="janeiro";
$locale_info["LOCALE_SMONTHNAME2"]="fevereiro";
$locale_info["LOCALE_SMONTHNAME3"]="março";
$locale_info["LOCALE_SMONTHNAME4"]="abril";
$locale_info["LOCALE_SMONTHNAME5"]="maio";
$locale_info["LOCALE_SMONTHNAME6"]="junho";
$locale_info["LOCALE_SMONTHNAME7"]="julho";
$locale_info["LOCALE_SMONTHNAME8"]="agosto";
$locale_info["LOCALE_SMONTHNAME9"]="setembro";
$locale_info["LOCALE_SMONTHNAME10"]="outubro";
$locale_info["LOCALE_SMONTHNAME11"]="novembro";
$locale_info["LOCALE_SMONTHNAME12"]="dezembro";
$locale_info["LOCALE_SABBREVMONTHNAME1"]="jan";
$locale_info["LOCALE_SABBREVMONTHNAME2"]="fev";
$locale_info["LOCALE_SABBREVMONTHNAME3"]="mar";
$locale_info["LOCALE_SABBREVMONTHNAME4"]="abr";
$locale_info["LOCALE_SABBREVMONTHNAME5"]="mai";
$locale_info["LOCALE_SABBREVMONTHNAME6"]="jun";
$locale_info["LOCALE_SABBREVMONTHNAME7"]="jul";
$locale_info["LOCALE_SABBREVMONTHNAME8"]="ago";
$locale_info["LOCALE_SABBREVMONTHNAME9"]="set";
$locale_info["LOCALE_SABBREVMONTHNAME10"]="out";
$locale_info["LOCALE_SABBREVMONTHNAME11"]="nov";
$locale_info["LOCALE_SABBREVMONTHNAME12"]="dez";
//	time settings
$locale_info["LOCALE_ITIME"]="1";
$locale_info["LOCALE_ITIMEMARKPOSN"]="0";
$locale_info["LOCALE_ITLZERO"]="1";
$locale_info["LOCALE_S1159"]="";
$locale_info["LOCALE_S2359"]="";
$locale_info["LOCALE_STIME"]=":";
$locale_info["LOCALE_STIMEFORMAT"]="HH:mm:ss";
//	currency settings
$locale_info["LOCALE_ICURRDIGITS"]="2";
$locale_info["LOCALE_ICURRENCY"]="0";
$locale_info["LOCALE_INEGCURR"]="0";
$locale_info["LOCALE_SCURRENCY"]="R\$ ";
$locale_info["LOCALE_SMONDECIMALSEP"]=",";
$locale_info["LOCALE_SMONGROUPING"]="3;0";
$locale_info["LOCALE_SMONTHOUSANDSEP"]=".";
//	numbers formatting settings
$locale_info["LOCALE_IDIGITS"]="2";
$locale_info["LOCALE_INEGNUMBER"]="1";
$locale_info["LOCALE_SDECIMAL"]=",";
$locale_info["LOCALE_SGROUPING"]="3;0";
$locale_info["LOCALE_SNEGATIVESIGN"]="-";
$locale_info["LOCALE_SPOSITIVESIGN"]="";
$locale_info["LOCALE_STHOUSAND"]=".";
;

$locale_info["LOCALE_ILONGDATE"]=GetLongDateFormat();
 
//	locale functions
//	number, currency, date & time functions
//	valDigits - parameter from program, user can set decimal digets 
function str_format_number($val,$valDigits = false)
{
	global $locale_info;
	if(!is_numeric($val))
	  return $val;
 
	$iDigits = $valDigits;
	if($iDigits === false)  
		$iDigits = $locale_info["LOCALE_IDIGITS"];
	
	$val = round($val,$iDigits);
	if($val>=0)
	{
	  $sign=1;
	  $int = floor($val);
	  $frac = $val-$int;
	} else {
	  $sign=-1;
	  $int = floor(-$val);
	  $frac = -$val-$int;
	}
	$out = number_format($int,0,'','');
//	grouping
    $grouping=explode(";",$locale_info["LOCALE_SGROUPING"]);
	if(count($grouping) && $grouping[0])
	{
		$ptr=strlen($out);
		for($gi=0;$gi<count($grouping);$gi++)
		{
			if(!$grouping[$gi])
				$gi--;
			if($ptr<=$grouping[$gi])
			{
				$ptr=0;
				break;
			}
			$out=substr($out,0,$ptr-$grouping[$gi]).$locale_info["LOCALE_STHOUSAND"].substr($out,$ptr-$grouping[$gi]);
			$ptr-=$grouping[$gi];
		}
	}
//	fractional digits
    if($iDigits>0)
    {
      $fmul=1;
      for($i=0;$i<$iDigits;$i++)
        $fmul*=10;
      $frac=round($frac*$fmul);
	  $sfrac=mysprintf("%.0f",array($frac));
	  while(strlen($sfrac)<$iDigits)
	    $sfrac="0".$sfrac;
	  $out.=$locale_info["LOCALE_SDECIMAL"].$sfrac;
    }
//	format output
	if($sign>0)
		return $locale_info["LOCALE_SPOSITIVESIGN"].$out;
	else
	{
		switch($locale_info["LOCALE_INEGNUMBER"])
		{
			case 0:
				return "(".$out.")";
			case 1:
				return "-".$out;
			case 2:
				return "- ".$out;
			case 3:
				return $out."-";
			case 4:
				return $out." -";
		}
	}
	return $val;
}


function str_format_currency($val)
{
	global $locale_info;
	if(!is_numeric($val))
	  return $val;
	$val=round($val,$locale_info["LOCALE_ICURRDIGITS"]);
	if($val>=0)
	{
	  $sign=1;
	  $int = floor($val);
	  $frac = $val-$int;
	} else {
	  $sign=-1;
	  $int = floor(-$val);
	  $frac = -$val-$int;
	}
	$out = number_format($int,0,'','');
//	grouping
    $grouping=explode(";",$locale_info["LOCALE_SMONGROUPING"]);
	if(count($grouping) && $grouping[0])
	{
		$ptr=strlen($out);
		for($gi=0;$gi<count($grouping);$gi++)
		{
			if(!$grouping[$gi])
				$gi--;
			if($ptr<=$grouping[$gi])
			{
				$ptr=0;
				break;
			}
			$out=substr($out,0,$ptr-$grouping[$gi]).$locale_info["LOCALE_SMONTHOUSANDSEP"].substr($out,$ptr-$grouping[$gi]);
			$ptr-=$grouping[$gi];
		}
	}
//	fractional digits
    if($locale_info["LOCALE_ICURRDIGITS"]>0)
    {
      $fmul=1;
      for($i=0;$i<$locale_info["LOCALE_ICURRDIGITS"];$i++)
        $fmul*=10;
      $frac=round($frac*$fmul);
	  $sfrac=mysprintf("%d",array($frac));
	  while(strlen($sfrac)<$locale_info["LOCALE_ICURRDIGITS"])
	    $sfrac="0".$sfrac;
	  $out.=$locale_info["LOCALE_SMONDECIMALSEP"].$sfrac;
    }
//	format output
	if($sign>0)
	{
		switch($locale_info["LOCALE_ICURRENCY"])
		{
			case 0:
				return mysprintf("%s%s",array($locale_info["LOCALE_SCURRENCY"],$out));
			case 1:
				return mysprintf("%s%s",array($out,$locale_info["LOCALE_SCURRENCY"]));
			case 2:
				return mysprintf("%s %s",array($locale_info["LOCALE_SCURRENCY"],$out));
			case 3:
				return mysprintf("%s %s",array($out,$locale_info["LOCALE_SCURRENCY"]));
		}
	}
	else
	{
		switch($locale_info["LOCALE_INEGCURR"])
		{
			case 0:
				return mysprintf("(%s%s)",array($locale_info["LOCALE_SCURRENCY"],$out));
			case 1:
				return mysprintf("-%s%s",array($locale_info["LOCALE_SCURRENCY"],$out));
			case 2:
				return mysprintf("%s-%s",array($locale_info["LOCALE_SCURRENCY"],$out));
			case 3:
				return mysprintf("%s%s-",array($locale_info["LOCALE_SCURRENCY"],$out));
			case 4:
				return mysprintf("(%s%s)",array($out,$locale_info["LOCALE_SCURRENCY"]));
			case 5:
				return mysprintf("-%s%s",array($out,$locale_info["LOCALE_SCURRENCY"]));
			case 6:
				return mysprintf("%s-%s",array($out,$locale_info["LOCALE_SCURRENCY"]));
			case 7:
				return mysprintf("%s%s-",array($out,$locale_info["LOCALE_SCURRENCY"]));
			case 8:
				return mysprintf("-%s %s",array($out,$locale_info["LOCALE_SCURRENCY"]));
			case 9:
				return mysprintf("-%s %s",array($locale_info["LOCALE_SCURRENCY"],$out));
			case 10:
				return mysprintf("%s %s-",array($out,$locale_info["LOCALE_SCURRENCY"]));
			case 11:
				return mysprintf("%s %s-",array($locale_info["LOCALE_SCURRENCY"],$out));
			case 12:
				return mysprintf("%s -%s",array($locale_info["LOCALE_SCURRENCY"],$out));
			case 13:
				return mysprintf("%s- %s",array($out,$locale_info["LOCALE_SCURRENCY"]));
			case 14:
				return mysprintf("(%s %s)",array($locale_info["LOCALE_SCURRENCY"],$out));
			case 15:
				return mysprintf("(%s %s)",array($out,$locale_info["LOCALE_SCURRENCY"]));
				
		}
	}
	return $val;
}


//	converts mysql datetime to array(year,month,day,hour,minute,second)
function db2time($str)
{
	$now=localtime(time(),1);
    $isdst=$now["tm_isdst"];
    $havedate=0;
	$havetime=0;
	if(is_numeric($str))
//	timestamp
	{
		$havedate=1;
		$len=strlen($str);
		if($len>=10)
		  $havetime=1;
		switch($len)
		{
		  case 14:
		  	$pattern="/(\d{4})(\d{2})(\d{2})(\d{2})(\d{2})(\d{2})/";
			break;
		  case 12:
		  	$pattern="/(\d{2})(\d{2})(\d{2})(\d{2})(\d{2})(\d{2})/";
			break;
		  case 10:
		  	$pattern="/(\d{2})(\d{2})(\d{2})(\d{2})(\d{2})/";
			break;
		  case 18:
		  	$pattern="/(\d{4})(\d{2})(\d{2})/";
			break;
		  case 6:
		  	$pattern="/(\d{2})(\d{2})(\d{2})/";
			break;
		  case 4:
		  	$pattern="/(\d{2})(\d{2})/";
			break;
		  case 2:
		  	$pattern="/(\d{2})/";
			break;
	      default: 
		    return array();
	    }
		if(preg_match($pattern,$str,$parsed))
		{
		  $y=$parsed[1];
		  $mo=(count($parsed)>2)?$parsed[2]:1;
		  $d=(count($parsed)>3)?$parsed[3]:1;
		  $h=(count($parsed)>4)?$parsed[4]:0;
		  $mi=(count($parsed)>5)?$parsed[5]:0;
		  $s=(count($parsed)>6)?$parsed[6]:0;
		}
		else
		  return array();
		  
	}
	else if(is_string($str))
// date,time,datetime
	{
	  if(preg_match("/(\d{4})-(\d{1,2})-(\d{1,2}) (\d{1,2}):(\d{1,2}):(\d{1,2})/", $str, $parsed)) // datetime
		{
			$y = $parsed[1];
			$mo = $parsed[2];
			$d = $parsed[3];
			$h = $parsed[4];
			$mi = $parsed[5];
			$s = $parsed[6];
		    $havedate=1;
			$havetime=1;
		}
		else if(preg_match("/(\d{4})-(\d{1,2})-(\d{1,2})/", $str, $parsed)) // date
		{
			$y = $parsed[1];
			$mo = $parsed[2];
			$d = $parsed[3];
			$h = 0;
			$mi = 0;
			$s = 0;
		    $havedate=1;
		}
		else if(preg_match("/(\d{2})-(\d{1,2})-(\d{1,2})/", $str, $parsed)) // time
		{
		  $y=$now["tm_year"];
		  $mo=$now["tm_mon"]+1;
		  $d=$now["tm_mday"];
		  $h = $parsed[1];
		  $mi = $parsed[2];
		  $s = $parsed[3];
		  $havetime=1;
		}
		else 
			return array();
	}
	else
	{
		return array();
	}
	if(!$havetime)
	{
		$h=0;
		$mi=0;
		$s=0;
	}
	if(!$havedate)
	{
		$y=$now["tm_year"]+1900;
		$mo=$now["tm_mon"]+1;
		$d=$now["tm_mday"];
	}
//	return mktime($h,$mi,$s,$mo,$d,$y);
	return array((integer)$y,(integer)$mo,(integer)$d,(integer)$h,(integer)$mi,(integer)$s);
}


function format_datetime_custom($time,$format)
{
	global $locale_info;
	if(count($time)<3)
		return "";
	$i=0;
	$subst=array();
	$weekday=getdayofweek($time);
	
	
	$subst["dddd"]=$locale_info["LOCALE_SDAYNAME".$weekday];
	$subst["ddd"]=$locale_info["LOCALE_SABBREVDAYNAME".$weekday];
	$subst["dd"]=mysprintf("%02d",array($time[2]));
	$subst["d"]=$time[2];
	if(array_key_exists("LOCALE_SMONTHNAME".$time[1],$locale_info))
	{
		$subst["MMMM"]=$locale_info["LOCALE_SMONTHNAME".$time[1]];
		$subst["MMM"]=$locale_info["LOCALE_SABBREVMONTHNAME".$time[1]];
		$subst["MM"]=mysprintf("%02d",array($time[1]));
	}
	else
	{
		$subst["MMMM"]="";
		$subst["MMM"]="";
		$subst["MM"]="00";
	}
	$subst["M"]=$time[1];
	$subst["yyyy"]=mysprintf("%04d",array($time[0]));
	$subst["yy"]=mysprintf("%02d",array($time[0]%100));
	$subst["y"]=$time[0]%10;
	$subst["gg"]="";
	$subst["HH"]=mysprintf("%02d",array($time[3]));
	$subst["H"]=$time[3];
	$subst["mm"]=mysprintf("%02d",array($time[4]));
	$subst["m"]=$time[4];
	$subst["ss"]=mysprintf("%02d",array($time[5]));
	$subst["s"]=$time[5];
	$hour12=$time[3];
	$am=1;
	if($hour12>=12)
	{
		$am=0;
		$hour12-=12;
	}
	if(!$hour12)
		$hour12=12;
	$subst["hh"]=mysprintf("%02d",array($hour12));
	$subst["h"]=$hour12;
	if($am)
	{
		$subst["tt"]=$locale_info["LOCALE_S1159"];
		$subst["t"]=substr($locale_info["LOCALE_S1159"],0,1);
	}
	else
	{
		$subst["tt"]=$locale_info["LOCALE_S2359"];
		$subst["t"]=substr($locale_info["LOCALE_S2359"],0,1);
	}
	$out=$format;
	$inquot=0;
	while($i<strlen($out))
	{
		if($out[$i]=="'")
		{
			$inquot=1-$inquot;
			$out=substr($out,0,$i).substr($out,$i+1);
			continue;
		}
		else if(!$inquot)
		{
			foreach($subst as $key=>$value)
				if(substr($out,$i,strlen($key))==$key)
				{
					$out=substr($out,0,$i).$value.substr($out,strlen($key)+$i);
					$i+=strlen($value)-1;
					break;
				}
		}
		$i++;
	}
	return $out;
}

function str_format_datetime($time)
{
	global $locale_info;
	return format_datetime_custom($time,$locale_info["LOCALE_SSHORTDATE"]." ".$locale_info["LOCALE_STIMEFORMAT"]);
}

function str_format_time($time)
{
	global $locale_info;
	return format_datetime_custom($time,$locale_info["LOCALE_STIMEFORMAT"]);
}

function format_shortdate($time)
{
	global $locale_info;
	return format_datetime_custom($time,$locale_info["LOCALE_SSHORTDATE"]);
}

function format_longdate($time)
{
	global $locale_info;
	return format_datetime_custom($time,$locale_info["LOCALE_SLONGDATE"]);
}

function simpledate2db($strdate,$formatid)
{
	$str=$strdate;
	$numbers=parsenumbers($str);
	if(!count($numbers))
		return $strdate;
	while(count($numbers)<3)
		$numbers[]=1;
	if(!$formatid)
	{
		$month=$numbers[0];
		$day=$numbers[1];
		$year=$numbers[2];
//		list($month,$day,$year)=$numbers;
		
	}
	else if($formatid==1)
	{
		$day=$numbers[0];
		$month=$numbers[1];
		$year=$numbers[2];
//		list($day,$month,$year)=$numbers;
	}
	else if($formatid==2)
	{
		$year=$numbers[0];
		$month=$numbers[1];
		$day=$numbers[2];
//		list($year,$month,$day)=$numbers;
	}
	else
		return $strdate;
	if($year<100)
	{
		if($year<60)
			$year+=2000;
		else
			$year+=1900;
	}
	return mysprintf("%04d-%02d-%02d",array($year,$month,$day));
}


function localdate2db($strdate)
{
	global $locale_info;
	return simpledate2db($strdate,$locale_info["LOCALE_IDATE"]);
}

function localtime2db($strtime)
{
	global $locale_info;
//	check if we use 12hours clock
	$use12=0;
	$strtime = strtoupper($strtime);
	$pos=strpos($locale_info["LOCALE_STIMEFORMAT"],"h".$locale_info["LOCALE_STIME"]);
	if(!($pos===false)  or (strpos($strtime,"AM")!==false or strpos($strtime,"PM")!==false))
	{
		$use12=1;
//	determine am/pm
		$pm=0;
		
		$amstr= $locale_info["LOCALE_S1159"] == "" ? "AM" :  $locale_info["LOCALE_S1159"];
		$pmstr=$locale_info["LOCALE_S2359"] == "" ? "PM" : $locale_info["LOCALE_S2359"];
		
		$posam=false;
		$pospm=false;
		if(strlen($amstr))
			$posam=strpos($strtime,$amstr);
		if(strlen($pmstr))
			$pospm=strpos($strtime,$pmstr);
		if($posam===false && $pospm!==false)
			$pm=1;
		elseif($posam!==false && $pospm===false)
			$pm=0;
		elseif($posam===false && $pospm===false)
			$use12=0;
		else
		{
			if($posam>$pospm)
				$pm=1;
		}
	}
	$str=$strtime;
	$numbers=parsenumbers($str);
	while(count($numbers)<3)
		$numbers[]=0;
	$h=$numbers[0];
	$m=$numbers[1];
	$s=$numbers[2];
//	list($h,$m,$s)=$numbers;
	if($use12 && $h)
	{
		if(!$pm && $h==12)
			$h=0;
		if($pm && $h<12)
			$h+=12;
	}
	return mysprintf("%02d:%02d:%02d",array($h,$m,$s));
}


function localdatetime2db($strdatetime,$format="")
{
	global $locale_info;
	$locale_idate=$locale_info["LOCALE_IDATE"];
	if($format=="dmy")
		$locale_idate=1;
	if($format=="mdy")
		$locale_idate=0;
	if($format=="ymd")
		$locale_idate=2;

//	check if we use 12hours clock
	$strtime = strtoupper($strdatetime);
	$use12=0;
	$pos=strpos($locale_info["LOCALE_STIMEFORMAT"],"h".$locale_info["LOCALE_STIME"]);
	if(!($pos===false)  or (strpos($strtime,"AM")!==false or strpos($strtime,"PM")!==false))
	{
		$use12=1;
//	determine am/pm
		$pm=0;
		
		$amstr= $locale_info["LOCALE_S1159"] == "" ? "AM" :  $locale_info["LOCALE_S1159"];
		$pmstr=$locale_info["LOCALE_S2359"] == "" ? "PM" : $locale_info["LOCALE_S2359"];
		
		$posam=strpos($strdatetime,$amstr);
		$pospm=strpos($strdatetime,$pmstr);
		if($posam===false && $pospm!==false)
			$pm=1;
		elseif($posam!==false && $pospm===false)
			$pm=0;
		elseif($posam===false && $pospm===false)
			$use12=0;
		else
		{
			if($posam>$pospm)
				$pm=1;
		}
	}
	$numbers=parsenumbers($strdatetime);
	if(!$numbers || count($numbers)<2)
		return "null";
//	add current year if not specified
	if(count($numbers)<3)
	{	
		if($locale_idate!=1)
		{
			$month=$numbers[0];
			$day=$numbers[1];
		}
		else
		{
			$month=$numbers[1];
			$day=$numbers[0];
		}
		$tm=localtime(time(),true);
		$year=1900+$tm["tm_year"];
	}
	else
	{
		if(!$locale_idate)
		{
			$month=$numbers[0];
			$day=$numbers[1];
			$year=$numbers[2];
//			list($month,$day,$year)=$numbers;
		}
		else if($locale_idate==1)
		{
			$day=$numbers[0];
			$month=$numbers[1];
			$year=$numbers[2];
//			list($day,$month,$year)=$numbers;
		}
		else if($locale_idate==2)
		{
			$year=$numbers[0];
			$month=$numbers[1];
			$day=$numbers[2];
//			list($year,$month,$day)=$numbers;
		}
	}		
	if(!$month || !$day)
		return "null";
	while(count($numbers)<6)
		$numbers[]=0;
	$h=$numbers[3];
	$m=$numbers[4];
	$s=$numbers[5];
	if($use12 && $h)
	{
		if(!$pm && $h==12)
			$h=0;
		if($pm && $h<12)
			$h+=12;
	}
	if($year<100)
	{
		if($year<60)
			$year+=2000;
		else
			$year+=1900;
	}
	return mysprintf("%04d-%02d-%02d",array($year,$month,$day))." ".mysprintf("%02d:%02d:%02d",array($h,$m,$s));
}




function parsenumbers($str)
{
	$ret=array();
	$i=0;
	$num=0;
	$pos=0;
	while($i<strlen($str))
	{
		if(is_numeric($str[$i]) && !$num)
		{
			$num=1;
			$pos=$i;
		}
		else if(!is_numeric($str[$i]) && $num)
		{
			$ret[]=(integer)substr($str,$pos,$i-$pos);
			$num=0;
		}
		$i++;
	}
	if($num)
		$ret[]=(integer)substr($str,$pos,$i-$pos);
	return $ret;
}

//	returns day of week (1-7) for (monday-sunday)
function getdayofweek($time)
{
//	January 1, 2004 - Thursday
//	Get the differewnce in days between January 1, 2004 and January 1 of given year
	$daydif=0;
	if($time[0]>=2004)
	{
		for($i=2004;$i<$time[0];$i++)
			if(isleapyear($i))
				$daydif+=366;
			else
				$daydif+=365;
	}
	else
		for($i=2003;$i>=$time[0];$i--)
			if(isleapyear($i))
				$daydif-=366;
			else
				$daydif-=365;
//	to given month
	$mdays=array(1=>31,28,31,30,31,30,31,31,30,31,30,31);
	if(isleapyear($time[0]))
		$mdays[2]=29;
	for($i=1;$i<$time[1] && $i<13;$i++)
		$daydif+=$mdays[$i];
//	to given day
	$daydif+=$time[2]-1;
	if($daydif>0)
		return (4+$daydif-1)%7 + 1;
	return 7-(3-$daydif)%7;
}

//	returns abstract week number, 0 - January 1, 2004 (Thursday)
function getweeknumber($time)
{
//	January 1, 2004 - Thursday
	global $locale_info;
	if($locale_info["LOCALE_IFIRSTDAYOFWEEK"]<=3)
		$startweekday=3-$locale_info["LOCALE_IFIRSTDAYOFWEEK"];
	else
		$startweekday=10-$locale_info["LOCALE_IFIRSTDAYOFWEEK"];
//	Get the differewnce in days between January 1, 2004 and January 1 of given year
	$daydif=0;
	if($time[0]>=2004)
	{
		for($i=2004;$i<$time[0];$i++)
			if(isleapyear($i))
				$daydif+=366;
			else
				$daydif+=365;
	}
	else
		for($i=2003;$i>=$time[0];$i--)
			if(isleapyear($i))
				$daydif-=366;
			else
				$daydif-=365;
//	to given month
	$mdays=array(1=>31,28,31,30,31,30,31,31,30,31,30,31);
	if(isleapyear($time[0]))
		$mdays[2]=29;
	for($i=1;$i<$time[1];$i++)
		$daydif+=$mdays[$i];
//	to given day
	$daydif+=$time[2]-1;
	
	$daydif+=$startweekday;
	$daydif = $daydif-($daydif%7);
	return $daydif/7;
}

function adddays($tm,$days)
{
	$mdays=array(1=>31,28,31,30,31,30,31,31,30,31,30,31);
	$time=$tm;
	if(isleapyear($time[0]))
		$mdays[2]=29;

	if($days>0)
		for($i=0;$i<$days;$i++)
		{
			if($time[2]<$mdays[$time[1]])
				$time[2]++;
			else
			{
				$time[2]=1;
				$time[1]++;
				if($time[1]>12)
				{
					$time[1]=1;
					$time[0]++;
					if(isleapyear($time[0]))
						$mdays[2]=29;
					else
						$mdays[2]=28;
				}
			}
		}
	else
		for($i=0;$i<-$days;$i++)
		{
			if($time[2]>1)
				$time[2]--;
			else
			{
				$time[1]--;
				if($time[1]<1)
				{
					$time[0]--;
					if(isleapyear($time[0]))
						$mdays[2]=29;
					else
						$mdays[2]=28;
					$time[1]=12;
				}
				$time[2]=$mdays[$time[1]];
			}
		}
	return $time;
}

function getweekstart($time)
{
	global $locale_info;
	$wday = getdayofweek($time);
	if($wday>=$locale_info["LOCALE_IFIRSTDAYOFWEEK"]+1)
		$diff=$wday - $locale_info["LOCALE_IFIRSTDAYOFWEEK"]-1;
	else
		$diff=$wday+7 - $locale_info["LOCALE_IFIRSTDAYOFWEEK"]-1;
	return adddays($time,-$diff);
}


function isleapyear($y)
{
	if($y % 4)
		return false;
	if($y % 100)
		return true;
	if(($y/100)%4)
		return false;
	return true;
}

function GetLongDateFormat()
{
	global $locale_info;
	$format=$locale_info["LOCALE_SLONGDATE"];

//	dd,d - day
//	MMMM, MMM, MM, M - month
//	yyyy, yy, y - year
//	dddd, ddd - day of week, ignore it
//	'sdsd' - quoted string, ignore it.
	$dstart=-1;
	$inquote=false;
	$dindex=-1;
	$mindex=-1;
	$yindex=-1;
	for($i=0;true;$i++)
	{
		$c="";
		if($i<strlen($format))
			$c=substr($format,$i,1);
		if($dstart>=0 && $c!='d')
		{
			if($i-$dstart<=2)
				$dindex=$dstart;
			$dstart=-1;
		}
		if(!$inquote && $c=='\'')
			$inquote=true;
		else if($c=='\'')
			$inquote=false;
		else if(!$inquote)
		{
			if($dindex<0 && $c=='d')
			{
				if($dstart<0)
					$dstart=$i;
			}
			if($yindex<0 && $c=='y')
				$yindex=$i;
			if($mindex<0 && $c=='M')
				$mindex=$i;
		}
		if($i>=strlen($format))
			break;
	}
	if($dindex<0 || $mindex<0 || $yindex<0)
		return -1;
	if($dindex<$mindex && $mindex<$yindex)	// DMY 
		return 1;
	if($mindex<$dindex && $dindex<$yindex)	// MDY
		return 0;
	if($yindex<$mindex && $mindex<$dindex)	// YMD
		return 2;
	if($yindex<$dindex && $dindex<$mindex)	// YDM
		return 1;
	return -1;
}

?>
