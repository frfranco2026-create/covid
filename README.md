# Local Setup

# local jenkins instance
1. Make sure docker instance is running locally
2. Open browser to http://localhost:8080/

# ngrok
Point ngrok to local Jenkins instance
`ngrok http 8080 --url https://reactive-gyration-calibrate.ngrok-free.dev`

# github
Define a webhook with
Payload URL: https://reactive-gyration-calibrate.ngrok-free.dev/github-webhook/
Content type: application/json

# Local Restful APIs
Get all covid records
`curl http://localhost:8081/covid`

Get covid record by id
`curl http://localhost:8081/covid/{id}`

Get top5 covid records by field
`curl 'http://localhost:8081/covid/top5?by=active'`


# EC2 Restful APIs
Get all covid records
`curl http://34.222.160.63:8081/covid`

Get covid record by id
`curl http://34.222.160.63:8081/covid/{id}`

Get top5 covid records by field
`curl 'http://34.222.160.63:8081/covid/top5?by=active'`