<?php

include 'conexion.php';

$stmt = $conexion->prepare("SELECT idProducto, img FROM productos ");
//https://www.php.net/manual/es/mysqli-stmt.bind-param.php

if($stmt->execute()){
	$result = $stmt->get_result();
	$usersArray = $result->fetch_all(MYSQLI_ASSOC);	
	foreach($usersArray as $index => $element){
		$usersArray[$index]["img"] = base64_encode($element["img"]);
	}
	echo json_encode(array("imageList"=>$usersArray));
	
} else {	
	echo 0;
}
$stmt->close();

die();
?>
