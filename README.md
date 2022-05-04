This is microservice for the manage endpoints by REST API and ping them. You can add new endpoints and configure execution interval of pings (in second). 

For start:
1. Configure your MySql connection
2. Change application.properties properly
3. Add user by SQL like: 
INSERT INTO user(id, access_token, email, name, deleted)
   VALUES(1, '', 'bob@email.com', 'bob', false);


REST API

- Create new monitored endpoint:

POST: /monitored-endpoint/create

BODY(example): 
{
"name": "Cats",
"url": "https://catfact.ninja/fact",
"monitoredInterval": "30",
"ownerId": 1
}

- Find monitored endpoint by id:

GET: /monitored-endpoint/{id}

- Update monitored endpoint by id:

PUT: /monitored-endpoint/{id}/update

BODY(example):
{
"name": "Dogs",
"url": "https://dog.ceo/api/breeds/image/random",
"monitoredInterval": "20",
"ownerId": 1
}

- Delete monitored endpoint by id:

PUT: /monitored-endpoint/{id}/delete

- Get last 10 monitoring results for particular monitoring endpoint id:

GET: monitoring-result/{id}