<?php
require_once "plugins/PHPExcel/IOFactory.php";

function ExportExcelInit($arrdata,$arrwidth)
{
	global $cCharset;
	$objPHPExcel = new PHPExcel();
	$objProp = $objPHPExcel->getProperties();
	$objProp->setCreator("PHP");
	$objASIndex = $objPHPExcel->setActiveSheetIndex(0);
	$objASIndex->setTitle("Export");
	$col = 0;
	foreach($arrdata as $field=>$data)
	{
		$data = PHPExcel_Shared_String::ConvertEncoding($data, 'UTF-8', $cCharset);
		$objASIndex->setCellValueByColumnAndRow($col,1,$data);
		$colLetter = PHPExcel_Cell::stringFromColumnIndex($col);
		$objASheet = $objPHPExcel->getActiveSheet();
		$objDim = $objASheet->getColumnDimension($colLetter);
		$objDim->setWidth($arrwidth[$field]);
		$col++;
	}

	return $objPHPExcel;
}

function ExportExcelRecord($arrdata, $datatype, $row, $objPHPExcel,$pageObj)
{
	global $cCharset, $locale_info;
	$col = -1;
	$objASIndex = $objPHPExcel->setActiveSheetIndex(0);
	$objASheet = $objPHPExcel->getActiveSheet();
	$rowDim = $objASIndex->getRowDimension($row+1);
	
	foreach($arrdata as $field => $data)
	{
		$col++;
		$colLetter = PHPExcel_Cell::stringFromColumnIndex($col);
		$colDim = $objASIndex->getColumnDimension($colLetter);
		if($datatype[$field] == "binary")
		{
			if(!$data)
				continue;
			if(!function_exists("imagecreatefromstring"))
			{
				$objASIndex->setCellValueByColumnAndRow($col,$row+1,"Dados Binários longos demais, Não pode ser exibido");
				continue;
			}
			$error_handler = set_error_handler("empty_error_handler");
			$gdImage = imagecreatefromstring($data);
			if($error_handler)
				set_error_handler($error_handler);
			if($gdImage)
			{
				$objDrawing = new PHPExcel_Worksheet_MemoryDrawing();
				$objDrawing->setImageResource($gdImage);
				$objDrawing->setCoordinates($colLetter.($row+1));
				$objDrawing->setWorksheet($objASheet);
				
				$width = $objDrawing->getWidth()*0.143;
				$height = $objDrawing->getHeight()*0.75;
				
				if($rowDim->getRowHeight() < $height)
					$rowDim->setRowHeight($height);
				
				$colDimSh = $objASheet->getColumnDimension($colLetter);
				$colDimSh->setAutoSize(false);
				
				if($colDim->getWidth() < $width)
					$colDim->setWidth($width);
			}
		}
		elseif($datatype[$field] == "file")
		{
			if(!file_exists($pageObj->pSet->getUploadFolder($field).$data) || !$data)
				continue;
			$objDrawing = new PHPExcel_Worksheet_Drawing();
			$objDrawing->setPath($pageObj->pSet->getUploadFolder($field).$data);
			$objDrawing->setCoordinates($colLetter.($row+1));
			$objDrawing->setWorksheet($objPHPExcel->getActiveSheet());
			
			$width = $objDrawing->getWidth()*0.143;
			$height = $objDrawing->getHeight()*0.75;
			
			if($rowDim->getRowHeight() < $height)
				$rowDim->setRowHeight($height);
			
			$colDimSh = $objASheet->getColumnDimension($colLetter);
			$colDimSh->setAutoSize(false);
			
			if($colDim->getWidth() < $width)
				$colDim->setWidth($width);
		}
		else
		{
			$data = PHPExcel_Shared_String::ConvertEncoding($data, 'UTF-8', $cCharset);
			$objASIndex->setCellValueByColumnAndRow($col,$row+1,$data);
			if($datatype[$field] == "date")
			{
				$objStyle = $objASIndex->getStyle($colLetter.($row+1));
				$objNumFrm = $objStyle->getNumberFormat();
				$objNumFrm->setFormatCode($locale_info["LOCALE_SSHORTDATE"]." hh:mm:ss");
			}
		}
	}
}

function ExportExcelTotals($arrTotal, $arrTotalMessage, $row, $objPHPExcel)
{
	global $cCharset;
	$col = 0;
	$objASIndex = $objPHPExcel->setActiveSheetIndex(0);
	foreach($arrTotal as $key => $value)
	{
		if($value)
			$objASIndex->setCellValueByColumnAndRow($col,$row+1,$arrTotalMessage[$key].PHPExcel_Shared_String::ConvertEncoding($value, 'UTF-8', $cCharset));
		$col++;
	}
}
function ExportExcelSave($filename,$format,$objPHPExcel)
{
	global $cCharset;
	$filename = PHPExcel_Shared_String::ConvertEncoding($filename, 'UTF-8', $cCharset);
	if($format == "Excel2007")
		header('Content-Type: application/vnd.openxmlformats-officedocument.spreadsheetml.sheet');
	else
		header('Content-Type: application/vnd.ms-excel');
	
	header('Content-Disposition: attachment;filename="'.$filename.'";');
	header('Cache-Control: max-age=0');	
	
	$objWriter = PHPExcel_IOFactory::createWriter($objPHPExcel, $format);
	$objWriter->save('php://output'); 
}

function ExportToExcel($cipherer, $pageObj)
{
	global $rs,$nPageSize,$conn,$eventObj;

	if($eventObj->exists("ListFetchArray"))
		$row = $eventObj->ListFetchArray($rs, $pageObj);
	else
		$row = $cipherer->DecryptFetchedArray($rs);
	
	$tmpArr = array();
	$totals = array();
	$arrLabel = array();
	$arrTotal = array();
	$arrFields = array();
	$arrTmpTotal = array();
	$arrColumnWidth = array();
	$arrTotalMessage = array();
	
	$tmpArr = $pageObj->pSet->getExportFields();
	
	foreach($tmpArr as $value)
		if($pageObj->pSet->appearOnExportPage($value))
			$arrFields[] = $value;
	
	$arrTmpTotal = $pageObj->pSet->getTotalsFields();
	$pageObj->viewControls->forExport = "excel";
	foreach($arrFields as $value)
	{
		$arrLabel[$value] = GetFieldLabel(GoodFieldName($pageObj->tName), GoodFieldName($value)); 
		$arrColumnWidth[$value] = 10;
		$totals[$value] = array("value" => 0, "numRows" => 0);
		$totalsType = "";
		foreach($arrTmpTotal as $tvalue)
		{
			if($tvalue["fName"] == $value) {
				$totalsType = $tvalue["totalsType"];
				$totalsFields[] = array('fName'=>$value, 'totalsType'=>$totalsType, 'viewFormat'=>$pageObj->pSet->getViewFormat($value));
			}	
		}

	}
	
// write data rows
	$iNumberOfRows = 0;
	
	$objPHPExcel = ExportExcelInit($arrLabel,$arrColumnWidth);
	
	while((!$nPageSize || $iNumberOfRows<$nPageSize) && $row)
	{
		countTotals($totals, $totalsFields, $row);
		
		$values = array();
		$arrData = array();	
		$arrDataType = array();	
		
		foreach($arrFields as $value)
		{
			if(IsBinaryType($pageObj->pSet->getFieldType($value)))
				$values[$value] = $row[$value];
			else
			{
				$values[$value] = $pageObj->showDBValue($value, $row);
			}
		}
		
		$eventRes = true;
		if ($eventObj->exists('BeforeOut'))
			$eventRes = $eventObj->BeforeOut($row, $values, $pageObj);
		
		if ($eventRes)
		{
			$iNumberOfRows++;
			$i = 0;
			foreach($arrFields as $value)
			{
				if(IsBinaryType($pageObj->pSet->getFieldType($value)))
					$arrDataType[$value] = "binary";
				elseif($pageObj->pSet->getViewFormat($value)==FORMAT_DATE_SHORT || $pageObj->pSet->getViewFormat($value)==FORMAT_DATE_LONG || $pageObj->pSet->getViewFormat($value)==FORMAT_DATE_TIME)
					$arrDataType[$value] = "date";
				else
					$arrDataType[$value] = "";
				$arrData[$value] = $values[$value];
			}
			ExportExcelRecord($arrData, $arrDataType, $iNumberOfRows, $objPHPExcel,$pageObj);
		}
		
		if($eventObj->exists("ListFetchArray"))
			$row = $eventObj->ListFetchArray($rs, $pageObj);
		else
			$row = $cipherer->DecryptFetchedArray($rs);	
	}
	
	if(count($arrTmpTotal))
	{
		foreach($arrFields as $fName)
		{
			$value = array();
			foreach($arrTmpTotal as $tvalue)
			{
				if($tvalue["fName"] == $fName)
					$value = $tvalue;
			}
			$total = "";
			$totalMess = "";
			if($value["totalsType"])
			{
				if($value["totalsType"]=="COUNT")
					$totalMess = "Count".": ";
				elseif($value["totalsType"]=="TOTAL")
					$totalMess = "Total".": ";
				elseif($value["totalsType"]=="AVERAGE")
					$totalMess = "Average".": ";
				$total = GetTotals($fName, $totals[$fName]["value"], $value["totalsType"], $totals[$fName]["numRows"], $value["viewFormat"], "export");
			}
			$arrTotal[$fName] = $total;
			$arrTotalMessage[$fName] = $totalMess;
		}
	}

	ExportExcelTotals($arrTotal,$arrTotalMessage,++$iNumberOfRows,$objPHPExcel);
	
	$extExcel = ".xlsx";
	$formatExcel = "Excel2007";
	if(@$_REQUEST["type"] == "excel5")
	{
		$formatExcel = "Excel5";
		$extExcel = ".xls";
	}
	ExportExcelSave(GoodFieldName($pageObj->tName).$extExcel,$formatExcel,$objPHPExcel);
}

?>