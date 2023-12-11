```shell
USERNAME=test
CLIENT_ID=44h52jga891c10s1oj8qsard7l
CLIENT_SECRET=cchrbbtcag9e4lh47nn0aif7kbrj64bfvfj2jhdda43slg0q79f
PASSWORD=Password@123

SECRET_HASH=$(echo -n $USERNAME$CLIENT_ID | openssl sha256 -binary -mac HMAC -macopt key:$CLIENT_SECRET | base64)

RESPONSE=$(aws cognito-idp initiate-auth      \
  --client-id $CLIENT_ID                 \
  --auth-flow USER_PASSWORD_AUTH   \
  --auth-parameters USERNAME=$USERNAME,PASSWORD="$PASSWORD",SECRET_HASH=$SECRET_HASH)
  
if [ $? -ne 0 ]; then
  echo invalid credentials
  exit 1
fi

ACCESS_TOKEN=$(echo $RESPONSE | jq -r '.AuthenticationResult.AccessToken')
echo $ACCESS_TOKEN
  ```