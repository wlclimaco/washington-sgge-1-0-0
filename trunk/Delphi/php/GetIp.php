<?
	include 'query.php';

	$wNoIpInte = $_POST['NoIpInte'];
	$wNoIpExte = $_POST['NoIpExte'];
	$wNoPorta  = $_POST['NoPorta'];
	
	$gQryExecut = new TQuery();
    $gQryExecut->Sql("Select * From srvconexa");
	$gQryExecut->Open();
	echo $gQryExecut->FieldByName('NoIpInte') . '<BR>' . 
	     $gQryExecut->FieldByName('NoIpExte') . '<BR>' .
		 $gQryExecut->FieldByName('NoPorta');
?>	
