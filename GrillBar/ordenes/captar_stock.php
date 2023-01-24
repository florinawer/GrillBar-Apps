<?php

include 'conexion.php';

$stmt = $conexion->prepare("SELECT idProducto, nombre, cantidad FROM productos ");
//https://www.php.net/manual/es/mysqli-stmt.bind-param.php

if($stmt->execute()){
	$result = $stmt->get_result();
	$usersArray = $result->fetch_all(MYSQLI_ASSOC);	
	echo json_encode(array("productList"=>$usersArray));
	
	
} else {	
	echo 0;
}
$stmt->close();

die();
?>
