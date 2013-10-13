<?php
 
// response json
$json = array();

if (isset($_GET["message"])) {
    $message = $_GET["message"]; 
    
    include_once './db_functions.php';
    include_once './GCM.php';
	
    $gcm = new GCM();    
	$users = mysql_query("select gcm_regid FROM gcm_users");
    if (mysql_num_rows($users) > 0) {
        $user_array =  mysql_fetch_array($users);
		foreach ($user_array as $value)
		{
			$registatoin_ids = $value;
			$result = $gcm->send_notification($registatoin_ids, $message);
			echo $result;
			echo $value;
		} 
    } 
 
} else {
    // user details missing
}
?>