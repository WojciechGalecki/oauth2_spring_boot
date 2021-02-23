# OAuth2

## Keycloak 
Keycloak is an Open Source Identity and Access Management solution for modern Applications and Services.

### Running
```bash
docker-compose -f keycloak-postgres.yml up
```

Server will start by default on http://localhost:8080

### Admin user
- username: `admin`
- password: `password`

### Realm & Client example
- realm: `app`

- user account - http://localhost:8080/auth/realms/app/account

users [app]:
- user1 [user1@user.com, 12345]

client:
- client_id: app-code-flow-client
- client_secret: 56807fdb-be60-4787-87e6-0bb2cccf848b
- access type: confidential
- standard flow: enabled
- direct access grants: disabled
- (Optional) PKCE - Proof Key for Code: S256
- redirect uri: http://localhost:8083/callback

requests:

1. 
```bash
curl --request GET 'http://localhost:8080/auth/realms/app/protocol/openid-connect/auth?client_id=app-code-flow-client&response_type=code&scope=openid%20profile&redirect_uri=http://localhost:8083/callback&state=csad3vgh4543n43'
```
2. 
```bash
curl --request POST 'http://localhost:8080/auth/realms/app/protocol/openid-connect/token' \
--header 'Content-Type: application/x-www-form-urlencoded' \
--data-urlencode 'grant_type=authorization_code' \
--data-urlencode 'client_id=app-code-flow-client' \
--data-urlencode 'client_secret=56807fdb-be60-4787-87e6-0bb2cccf848b' \
--data-urlencode 'code=c020982c-6a10-45c6-8b4b-975ac15eaf9e.2916e936-1817-4f26-bee8-197b46a56985.68755830-c5d1-4228-a4cf-f63f2470fd10' \
--data-urlencode 'redirect_uri=http://localhost:8083/callback' \
--data-urlencode 'scope=openid profile'
```

## Resource Server

Server will start by default on http://localhost:8000

### Endpoints
- `/users/status`
- `/token`
