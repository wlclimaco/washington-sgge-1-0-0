<? 
$nome = $_POST["nome"]; 
$email = $_POST["email"]; 
$sim = $_POST["sim"];
$nao = $_POST["nao"];  
$telefone = $_POST["telefone"];  
$mensagem = $_POST["menssage"]; 

global $email; 
$data = date("d/m/y"); 
$ip = $_SERVER['REMOTE_ADDR']; 
$navegador = $_SERVER['HTTP_USER_AGENT']; 
$hora = date("H:i"); 

 
$msg = "Mensagem enviada em ".date("d/m/Y").", os dados seguem abaixo:n nome :".$nome." email: ".$email." sim".$sim." nao".$nao." telefone".$telefone." message".$mensagem." n"; 

mail("administrador@cosmeedamiaocontabilidade.com.br", "Contato_".date("d/m/Y")."",$msg,"From: $REMOTE_ADDR"); 
echo "Seu e-mail foi enviado com sucesso. Obrigado"; 
error_log("Oracle database not available!", 0);
print_r($debug_array);
header("Location: http://cosmeedamiaocontabilidade.com.br/");
echo("<script>alert('PHP: ".implode(',', $data)."');</script>");
console.log("The", animal, "jumped over", count, "tall buildings");
exit;
?>