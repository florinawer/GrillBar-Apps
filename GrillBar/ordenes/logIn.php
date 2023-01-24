<?php
if(!(isset($_POST['usuario']) && isset($_POST['contrasenya']))){die("ko");}

include 'conexion.php';
$usuario=isset($_POST['usuario']) ? $_POST['usuario'] : "";
$contrasenya=isset($_POST['contrasenya']) ? $_POST['contrasenya'] : "";
//$usuario=$_GET['usuario'];
//$contrasenya=$_GET['contrasenya'];


$stmt = $conexion->prepare("SELECT nombre, codigo FROM empleados WHERE nombre = ? AND codigo = ?");
//https://www.php.net/manual/es/mysqli-stmt.bind-param.php
if($stmt === false){
	echo 0;
	die();
}
$stmt->bind_param('ss', $usuario, $contrasenya);
if($stmt->execute()){
	$result = $stmt->get_result();
	$usersArray = $result->fetch_all(MYSQLI_ASSOC);	
	//echo json_encode(["done"=>true,"data"=>$usersArray]) . "\n\n";
	
	//echo "ok";
	//
	echo count($usersArray) === 0 ? 0 : 1; 
} else {
	//echo "ko";
	//echo json_encode(["done"=>false,"data"=>null]);
	echo 0;
}
$stmt->close();

die();
?>
