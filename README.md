# Share-Smiles

✨🥜Wayne-and-Robert🥜 ✨

## 1. Overview
Wayne and Robert are optimistic that "Share Smiles" will be a hit among the student community, offering them an enhanced online collaborative platform. Within this, it can help us familiarize with and apply platforms and technologies such as MySQL, MongoDB, Spring Boot, Redis, and Sentry. They sincerely hope that everyone will appreciate the effort and love the experience😊.

# Contents

| Name & Description   | HTTP method | Data Types                                           | Exceptions                                                   |
|----------------------|-------------|------------------------------------------------------|--------------------------------------------------------------|

| `user/register`<br>Given a user's first and last name, email address, and password, creates a new account for them and returns a new authUserId.        | POST        | **Body Parameters:**<br>`( email, name, password )`<br>**Return type if no error:**<br>`{ User }` | **400 Error** when any of:<br>· email entered is not a valid email<br>· email is already being used by another user<br>· length of password is less than 6 characters<br>· length of name is not between 1 and 50 characters 

| `user/login`<br>Given a registered user's email and password, returns user.        | POST        | **Body Parameters:**<br>`( email, password )`<br>**Return type if no error:**<br>`{ User }` | **400 Error** when password is not correct<br><br> **404 Error** when email entered does not belong to a user

| `user/profile`<br>For a valid user, returns information about their user ID, email, first name, last name, and handle       | GET        | **Body Parameters:**<br>`( userId )`<br>**Return type if no error:**<br>`{ User }` | **404 Error** when:<br>· userId does not refer to a valid user.

| `user/profile/setname`<br>Updates the authorised user's first and last name       | PUT         | **Body Parameters:**<br>`( userId, name )`<br>**Return type if no error:**<br>`{ }` | **400 Error** when:<br>· length of name is not between 1 and 50 characters inclusive<br><br> **404 Error** when:<br>· userId does not refer to a valid user.

| `user/profile/setemail`<br>Updates the authorised user's email address       | PUT         | **Body Parameters:**<br>`( userId, email )`<br>**Return type if no error:**<br>`{ }` | **400 Error** when:<br>· email entered is not a valid email<br>·email is already being used by another user<br><br> **404 Error** when:<br>· userId does not refer to a valid user.**500 Error** when:<br>· Email is already in use by another user.

| `channels/create`<br>Creates a new channel with the given name that is either a public or private channel. The user who created it automatically joins the channel.        | POST        | **Body Parameters:**<br>`( name )`<br>**Return type if no error:**<br>`{ channelId }` | **400 Error** when any of:<br>· length of name is less than 1 or more than 20 characters

| `channel/messages`<br>Given a channel with ID channelId that the authorised user is a member of, returns up to 50 messages between index start and "end".       | GET        | **Body Parameters:**<br>`( channelId, start, end )`<br>**Return type if no error:**<br>`{ messages }` | **400 Error** when any of:<br>· channelId does not refer to a valid channel

| `message/send`<br>Sends a message from the authorised user to the channel specified by channelId. Note: Each message should have its own unique ID, i.e. no messages should share an ID with another message, even if that other message is in a different channel or DM.       | POST        | **Body Parameters:**<br>`( channelId, message )`<br>**Return type if no error:**<br>`{ messageId }` | **400 Error** when:<br>· channelId does not refer to a valid channel<br>·length of message is less than 1 or over 1000 characters

| `message/edit`<br>Given a message with ID messageId, updates its text with new text given in message. If the new message is an empty string, the message is deleted.       | PUT        | **Body Parameters:**<br>`( Uid, messageId, message )`<br>**Return type if no error:**<br>`{ messageId }` | **400 Error** when:<br>· length of message is over 1000 characters<br>·messageId does not refer to a valid message | **403 Error** when: <br>message was not sent by this user
