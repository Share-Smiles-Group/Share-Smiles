# Share-Smiles

✨🥜Wayne-and-Robert🥜 ✨

# Contents

| Name & Description   | HTTP method | Data Types                                           | Exceptions                                                   |
|----------------------|-------------|------------------------------------------------------|--------------------------------------------------------------|
| user/register        | POST        | **Body Parameters:**<br>( email, name, password )<br>**Return type if no error:**<br>{ token, authUserId } | **400 Error** when any of:<br>· email entered is not a valid email<br>· email is already being used by another user<br>· length of password is less than 6 characters<br>· length of name is not between 1 and 50 characters 
| user/login        | POST        | **Body Parameters:**<br>( email, password )<br>**Return type if no error:**<br>{ token, authUserId } | **400 Error** when any of:<br>· email entered does not belong to a user<br>· password is not correct
| channels/create        | POST        | **Body Parameters:**<br>( name )<br>**Return type if no error:**<br>{ channelId } | **400 Error** when any of:<br>· length of name is less than 1 or more than 20 characters

