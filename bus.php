<?php
  	if(isset($_POST['stopNum'])){ //if you already have a stop number
  		$stopNum = $_POST['stopNum']; //set it
  	}else{
  		$stopNum = 317; //else it equals 317
  	}
  	
	$url = 'https://data.dublinked.ie/cgi-bin/rtpi/realtimebusinformation?stopid='.$stopNum.'&format=xml'; //we're going to be looking at stop number 317 on load else at whatever the user enters for stopNum
	$xmlstr = simplexml_load_file($url); //load in the link above as an xml file.. its called xmlstr
?>

<html>
	<head>
		<title style>Dublin Bus</title>
		<meta charset="utf-8">
	    <meta http-equiv="X-UA-Compatible" content="IE=edge">
	    <meta name="viewport" content="width=device-width, initial-scale=1">
	    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
            <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.0/jquery.min.js"></script>
            <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
	</head>
	<body>
		<nav>
		<h1 class="title" style="text-align:center;">Dublin Bus Live</h1>
		<form action="bus.php" method="POST">
			
				<input type="text" class="form-control" placeholder="Enter Stop Number" name="stopNum" required>
				</br>
				<input type="submit" style="padding: 10px 10px 10px;" class="btn btn-primary btn-block" value="Search">
			
		</form>
		</nav>
		<section>
		<table class="table table-striped table-bordered">
		  <thead>
		    <th>Stop No.</th>
		    <th>Route</th> 
		    <th>Origin</th>
		    <th>Destination</th>
		    <th>Departing</th>
		    <th>Operator</th>
		  </thead>
		  <tbody>
		  <?php
		  	foreach($xmlstr->results->result as $result){ //keep going in till you get inside results
		  		echo//for each of them, print the following
		  		"<tr>
				  	<td>". $stopNum ."</td>
					<td>". $result->route ."</td>
				  	<td>". $result->origin ."</td>
				    <td>". $result->destination ."</td>
				    <td>". $result->departureduetime ." minutes</td>
				    <td>". $result->operator ."</td> 
			  	</tr>";
		  	}
		  ?>
		 </tbody>
		</table>
		</section>
        <p>Last Updated: <?= $xmlstr->timestamp ?> </p> <!-- last updated with an xml timestamp -->
	</body>
</html>
