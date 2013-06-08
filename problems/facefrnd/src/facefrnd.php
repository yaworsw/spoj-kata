<?php

$stdin = fopen('php://stdin', 'r');

fgets($stdin); // throw away first line

$friends = array();
$friends_of_friends = array();

while ($line = fgets($stdin)) {

  $parts = explode(' ',$line);

  $friends[trim($parts[0])] = true;

  for($i = 2; $i < count($parts); $i++) {
    $friends_of_friends[trim($parts[$i])] = true;
  }

}

echo count(array_diff_key($friends_of_friends, $friends));