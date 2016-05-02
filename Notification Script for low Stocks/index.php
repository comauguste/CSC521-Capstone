<?php

	require 'PHPMailer-master/PHPMailerAutoload.php';
	
	$conn = mysqli_connect('localhost', 'comauguste','Journey1','inventory_system');
	
	if($conn->connect_error){
		die('Error : ('.$conn->connect_errno.')'. $conn->connect_error);
	}else
	{
		echo "Connected Successfully <br><br>";
	}
	
	$results = $conn -> query("SELECT * FROM inventory_system.inventory_items where QUANTITY_IN_STOCK <= REORDER_LEVEL;");
	
	if($results == true)
	{	
		$array = array();
		
		
		
		while($row = $results ->fetch_assoc())
		{
			add_to_array($array,$row ['FK_USER_ID'],$row['PK_ITEM_ID']);	
				
		}

		$keys = array_keys($array);
		foreach($keys as $key)
		{
			$message= "The following product(s) are running low:<br>";
			$mail = "";
			$value =  $array[$key];
			$inner_keys = array_keys($value); 
			foreach($inner_keys as $inner_key)
			{
				$item_id = $value[$inner_key];
				//print "Key : $key   Value: $item_id <br>";				
				$result2 = $conn -> query("SELECT * FROM inventory_system.inventory_items where PK_ITEM_ID = $item_id ");
				$row = $result2 -> fetch_assoc();
				$message2= "Item Name:".$row['ITEM_NAME']."| Remaining Stocks: ".$row['QUANTITY_IN_STOCK']."|Defined Reordering Treshold:". $row['REORDER_LEVEL']."<br>";
				$mail .=$message2;
			}
			
			echo $mail;
			
			$message.= $mail;
			$user_email = retrieve_user_email($key, $conn);
			
			if(retrieve_notification_status ($key, $conn) == 1)
			{
			send_email($user_email, $message);
			}
			//$mail = "";
			
			//2-Retrieve user email using user ID 
			//1- Retrieve Item name, quantity and reorder treshold using its key
			//Send email here
		}
			
	}
	
	
	function add_to_array($array, $key, $value)
	{

		global $array;
		if(array_key_exists($key, $array)) {
		
			if(is_array($array[$key])) {
				
				$array[$key][] = $value;
				
			}
			else {
				$array[$key] = array($array[$key], $value);           
			}
		}
		else {
		
        $array[$key] = array($value);
		}
	
	}	
	
	function send_email($user_email, $message)
	{
		
		

		$mail = new PHPMailer;

		//$mail->SMTPDebug = 3;                               // Enable verbose debug output

		$mail->isSMTP();                                      // Set mailer to use SMTP
		$mail->Host = 'smtp.gmail.com';  // Specify main and backup SMTP servers
		$mail->SMTPAuth = true;                               // Enable SMTP authentication
		$mail->Username = 'comauguste@gmail.com';                 // SMTP username
		$mail->Password = 'Energystar1@';                           // SMTP password
		$mail->SMTPSecure = 'tls';                            // Enable TLS encryption, `ssl` also accepted
		$mail->Port = 587;                                    // TCP port to connect to

		$mail->setFrom('no-reply@ics.com', 'ICS Notification Manager');
		$mail->addAddress($user_email, 'Joe User');     // Add a recipient
		$mail->addReplyTo('comauguste@gmail.com', 'Information');
		

		$mail->isHTML(true);                                  // Set email format to HTML

		$mail->Subject = 'Low Stocks Level';
		$mail->Body    = $message;
		$mail->AltBody = 'This is the body in plain text for non-HTML mail clients';

		if(!$mail->send()) {
			echo 'Message could not be sent.';
			echo 'Mailer Error: ' . $mail->ErrorInfo;
		} else {
			echo 'Message has been sent';
		}
	}
	
	function retrieve_user_email($id, $conn)
	{
				
		$result = $conn -> query("SELECT * FROM inventory_system.users where PK_USER_ID = $id; ");
		$row = $result -> fetch_assoc();
		$email = $row['EMAIL_ADDRESS'];
		
		//echo $email;
		
		return $email;
	}
	
	function retrieve_notification_status ($id, $conn)
	{
				
		$result = $conn -> query("SELECT * FROM inventory_system.credentials where FK_CR_USER_ID = $id; ");
		$row = $result -> fetch_assoc();
		$notification_status = $row['RECEIVE_NOTIFICATION'];
		
		echo $notification_status;
		
		return $notification_status;
	}
	
	
	 
	$results->free();
	$conn ->close();

?>