<?
	include 'query.php';

	$wNoIpInte = $_POST['NoIpInte'];
	$wNoIpExte = $_POST['NoIpExte'];
	$wNoPorta  = $_POST['NoPorta'];

	$gQryExecut = new TQuery();
    $gQryExecut->Sql("Update srvconexa set NoIpInte = '$wNoIpInte', NoIpExte = '$wNoIpExte', NoPorta = $wNoPorta");
	$gQryExecut->Execute();
	echo "OK";
?>	
