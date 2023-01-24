<?php
$hostname='localhost';
$database='ordenes';
$username='root';
$password='';

$conexion=new mysqli($hostname,$username,$password,$database);
if($conexion->connect_errno){
	echo "experimentando problemas";
}

?>
