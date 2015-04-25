<?php
class ViewDatabaseFileField extends ViewControl
{
	public function showDBValue(&$data, $keylink)
	{
		if($this->container->forExport)
			return "Dados Binários longos demais, Não pode ser exibido";
		$value = "";
		$fileNameF = $this->container->pSet->getFilenameField($this->field);
		if($fileNameF) {
			$fileName = $data[$fileNameF];
			if(! $fileName)
				$fileName = "file.bin";
		} else {
			$fileName = "file.bin";
		}
		if(strlen($data[$this->field])) {
			$value = "<a href='getfile.php?table=".GetTableURL($this->container->pSet->_table)
				."&filename=".rawurlencode($fileName)."&field="
				.rawurlencode($this->field).$keylink."'>";
			$value.= htmlspecialchars($fileName);
			$value.= "</a>";
		}
		return $value;
	}
}
?>