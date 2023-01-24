<?php
if(!(isset($_POST['nombre']) && isset($_POST['salsa']) && isset($_POST['punto']) && isset($_POST['side']) && isset($_POST['numero']) && isset($_POST['atiende']))){die("ko");}

include 'conexion.php';
$nombre=isset($_POST['nombre']) ? $_POST['nombre'] : "";
$salsa=isset($_POST['salsa']) ? $_POST['salsa'] : "";
$punto=isset($_POST['punto']) ? $_POST['punto'] : "";
$side=isset($_POST['side']) ? $_POST['side'] : "";
$numero=isset($_POST['numero']) ? $_POST['numero'] : "";
$atiende=isset($_POST['atiende']) ? $_POST['atiende'] : "";

$check = 0;
$consulta="insert into mesa values (default, '".$nombre."','".$salsa."', '".$punto."','".$side."', '".$numero."', '".$atiende."')";
if(mysqli_query($conexion, $consulta)){
	$check++;
} else {
	die("ko");
}
$consulta="insert into back_up_mesas values (default, '".$nombre."','".$salsa."', '".$punto."','".$side."', '".$numero."', '".$atiende."')";
if(mysqli_query($conexion, $consulta)){
	$check++;
} else {
	die("ko");
}

$consulta="update productos SET cantidad = IF(cantidad < 1, 0, cantidad -1)  WHERE nombre='".$nombre."'";
if(mysqli_query($conexion, $consulta)){
	$check++;
} else {
	die("ko");
}
if($check === 2){
	echo "ok";
}

mysqli_close($conexion);

?>
