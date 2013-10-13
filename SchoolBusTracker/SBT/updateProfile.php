<?php
$db_host = "localhost";
$db_user = "root";
$db_pass = "";
$db_db = "schoolbustracker";

$conn = mysql_connect($db_host,$db_user,$db_pass) or die("connection error");

mysql_select_db($db_db) or die("database selection error");

$uname = $_POST['username'];
$fullname = $_POST['fullname'];

$address = $_POST['address'];
$phone =$_POST['phone'];

$email = $_POST['email'];
$bus_hault =$_POST['bus_hault'];

// $uname = "100467";//$_POST['username'];
// $fullname = "Premini Barosingam";//$_POST['fullname'];

// $address = "Moratuwa";//$_POST['address'];
// $phone ="0775371646";//$_POST['phone'];

// $email = "mini@yahoo.com";//$_POST['email'];
// $bus_hault ="Moratuwa";//$_POST['bus_hault'];


$query = mysql_query("UPDATE sbt_users SET full_name='$fullname',address='$address',phone_no='$phone',email='$email',bus_hault='$bus_hault' WHERE username = '$uname'");

print json_encode($query);

mysql_close($conn);


?>